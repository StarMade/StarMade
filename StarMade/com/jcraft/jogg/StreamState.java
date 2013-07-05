/*     */ package com.jcraft.jogg;
/*     */ 
/*     */ public class StreamState
/*     */ {
/*     */   byte[] body_data;
/*     */   int body_storage;
/*     */   int body_fill;
/*     */   private int body_returned;
/*     */   int[] lacing_vals;
/*     */   long[] granule_vals;
/*     */   int lacing_storage;
/*     */   int lacing_fill;
/*     */   int lacing_packet;
/*     */   int lacing_returned;
/*  44 */   byte[] header = new byte[282];
/*     */   int header_fill;
/*     */   public int e_o_s;
/*     */   int b_o_s;
/*     */   int serialno;
/*     */   int pageno;
/*     */   long packetno;
/*     */   long granulepos;
/*     */ 
/*     */   public StreamState()
/*     */   {
/*  61 */     init();
/*     */   }
/*     */ 
/*     */   StreamState(int serialno) {
/*  65 */     this();
/*  66 */     init(serialno);
/*     */   }
/*     */ 
/*     */   void init() {
/*  70 */     this.body_storage = 16384;
/*  71 */     this.body_data = new byte[this.body_storage];
/*  72 */     this.lacing_storage = 1024;
/*  73 */     this.lacing_vals = new int[this.lacing_storage];
/*  74 */     this.granule_vals = new long[this.lacing_storage];
/*     */   }
/*     */ 
/*     */   public void init(int serialno) {
/*  78 */     if (this.body_data == null) {
/*  79 */       init();
/*     */     }
/*     */     else {
/*  82 */       for (int i = 0; i < this.body_data.length; i++)
/*  83 */         this.body_data[i] = 0;
/*  84 */       for (int i = 0; i < this.lacing_vals.length; i++)
/*  85 */         this.lacing_vals[i] = 0;
/*  86 */       for (int i = 0; i < this.granule_vals.length; i++)
/*  87 */         this.granule_vals[i] = 0L;
/*     */     }
/*  89 */     this.serialno = serialno;
/*     */   }
/*     */ 
/*     */   public void clear() {
/*  93 */     this.body_data = null;
/*  94 */     this.lacing_vals = null;
/*  95 */     this.granule_vals = null;
/*     */   }
/*     */ 
/*     */   void destroy() {
/*  99 */     clear();
/*     */   }
/*     */ 
/*     */   void body_expand(int needed) {
/* 103 */     if (this.body_storage <= this.body_fill + needed) {
/* 104 */       this.body_storage += needed + 1024;
/* 105 */       byte[] foo = new byte[this.body_storage];
/* 106 */       System.arraycopy(this.body_data, 0, foo, 0, this.body_data.length);
/* 107 */       this.body_data = foo;
/*     */     }
/*     */   }
/*     */ 
/*     */   void lacing_expand(int needed) {
/* 112 */     if (this.lacing_storage <= this.lacing_fill + needed) {
/* 113 */       this.lacing_storage += needed + 32;
/* 114 */       int[] foo = new int[this.lacing_storage];
/* 115 */       System.arraycopy(this.lacing_vals, 0, foo, 0, this.lacing_vals.length);
/* 116 */       this.lacing_vals = foo;
/*     */ 
/* 118 */       long[] bar = new long[this.lacing_storage];
/* 119 */       System.arraycopy(this.granule_vals, 0, bar, 0, this.granule_vals.length);
/* 120 */       this.granule_vals = bar;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int packetin(Packet op)
/*     */   {
/* 126 */     int lacing_val = op.bytes / 255 + 1;
/*     */ 
/* 128 */     if (this.body_returned != 0)
/*     */     {
/* 133 */       this.body_fill -= this.body_returned;
/* 134 */       if (this.body_fill != 0) {
/* 135 */         System.arraycopy(this.body_data, this.body_returned, this.body_data, 0, this.body_fill);
/*     */       }
/* 137 */       this.body_returned = 0;
/*     */     }
/*     */ 
/* 141 */     body_expand(op.bytes);
/* 142 */     lacing_expand(lacing_val);
/*     */ 
/* 149 */     System.arraycopy(op.packet_base, op.packet, this.body_data, this.body_fill, op.bytes);
/* 150 */     this.body_fill += op.bytes;
/*     */ 
/* 154 */     for (int j = 0; j < lacing_val - 1; j++) {
/* 155 */       this.lacing_vals[(this.lacing_fill + j)] = 255;
/* 156 */       this.granule_vals[(this.lacing_fill + j)] = this.granulepos;
/*     */     }
/* 158 */     this.lacing_vals[(this.lacing_fill + j)] = (op.bytes % 255);
/* 159 */     this.granulepos = (this.granule_vals[(this.lacing_fill + j)] = op.granulepos);
/*     */ 
/* 162 */     this.lacing_vals[this.lacing_fill] |= 256;
/*     */ 
/* 164 */     this.lacing_fill += lacing_val;
/*     */ 
/* 167 */     this.packetno += 1L;
/*     */ 
/* 169 */     if (op.e_o_s != 0)
/* 170 */       this.e_o_s = 1;
/* 171 */     return 0;
/*     */   }
/*     */ 
/*     */   public int packetout(Packet op)
/*     */   {
/* 180 */     int ptr = this.lacing_returned;
/*     */ 
/* 182 */     if (this.lacing_packet <= ptr) {
/* 183 */       return 0;
/*     */     }
/*     */ 
/* 186 */     if ((this.lacing_vals[ptr] & 0x400) != 0)
/*     */     {
/* 188 */       this.lacing_returned += 1;
/*     */ 
/* 192 */       this.packetno += 1L;
/* 193 */       return -1;
/*     */     }
/*     */ 
/* 198 */     int size = this.lacing_vals[ptr] & 0xFF;
/* 199 */     int bytes = 0;
/*     */ 
/* 201 */     op.packet_base = this.body_data;
/* 202 */     op.packet = this.body_returned;
/* 203 */     op.e_o_s = (this.lacing_vals[ptr] & 0x200);
/* 204 */     op.b_o_s = (this.lacing_vals[ptr] & 0x100);
/* 205 */     bytes += size;
/*     */ 
/* 207 */     while (size == 255) {
/* 208 */       int val = this.lacing_vals[(++ptr)];
/* 209 */       size = val & 0xFF;
/* 210 */       if ((val & 0x200) != 0)
/* 211 */         op.e_o_s = 512;
/* 212 */       bytes += size;
/*     */     }
/*     */ 
/* 215 */     op.packetno = this.packetno;
/* 216 */     op.granulepos = this.granule_vals[ptr];
/* 217 */     op.bytes = bytes;
/*     */ 
/* 219 */     this.body_returned += bytes;
/*     */ 
/* 221 */     this.lacing_returned = (ptr + 1);
/*     */ 
/* 223 */     this.packetno += 1L;
/* 224 */     return 1;
/*     */   }
/*     */ 
/*     */   public int pagein(Page og)
/*     */   {
/* 231 */     byte[] header_base = og.header_base;
/* 232 */     int header = og.header;
/* 233 */     byte[] body_base = og.body_base;
/* 234 */     int body = og.body;
/* 235 */     int bodysize = og.body_len;
/* 236 */     int segptr = 0;
/*     */ 
/* 238 */     int version = og.version();
/* 239 */     int continued = og.continued();
/* 240 */     int bos = og.bos();
/* 241 */     int eos = og.eos();
/* 242 */     long granulepos = og.granulepos();
/* 243 */     int _serialno = og.serialno();
/* 244 */     int _pageno = og.pageno();
/* 245 */     int segments = header_base[(header + 26)] & 0xFF;
/*     */ 
/* 249 */     int lr = this.lacing_returned;
/* 250 */     int br = this.body_returned;
/*     */ 
/* 253 */     if (br != 0) {
/* 254 */       this.body_fill -= br;
/* 255 */       if (this.body_fill != 0) {
/* 256 */         System.arraycopy(this.body_data, br, this.body_data, 0, this.body_fill);
/*     */       }
/* 258 */       this.body_returned = 0;
/*     */     }
/*     */ 
/* 261 */     if (lr != 0)
/*     */     {
/* 263 */       if (this.lacing_fill - lr != 0) {
/* 264 */         System.arraycopy(this.lacing_vals, lr, this.lacing_vals, 0, this.lacing_fill - lr);
/* 265 */         System.arraycopy(this.granule_vals, lr, this.granule_vals, 0, this.lacing_fill - lr);
/*     */       }
/* 267 */       this.lacing_fill -= lr;
/* 268 */       this.lacing_packet -= lr;
/* 269 */       this.lacing_returned = 0;
/*     */     }
/*     */ 
/* 274 */     if (_serialno != this.serialno)
/* 275 */       return -1;
/* 276 */     if (version > 0) {
/* 277 */       return -1;
/*     */     }
/* 279 */     lacing_expand(segments + 1);
/*     */ 
/* 282 */     if (_pageno != this.pageno)
/*     */     {
/* 286 */       for (int i = this.lacing_packet; i < this.lacing_fill; i++) {
/* 287 */         this.body_fill -= (this.lacing_vals[i] & 0xFF);
/*     */       }
/*     */ 
/* 290 */       this.lacing_fill = this.lacing_packet;
/*     */ 
/* 293 */       if (this.pageno != -1) {
/* 294 */         this.lacing_vals[(this.lacing_fill++)] = 1024;
/* 295 */         this.lacing_packet += 1;
/*     */       }
/*     */ 
/* 300 */       if (continued != 0) {
/* 301 */         bos = 0;
/* 302 */         for (; segptr < segments; segptr++) {
/* 303 */           int val = header_base[(header + 27 + segptr)] & 0xFF;
/* 304 */           body += val;
/* 305 */           bodysize -= val;
/* 306 */           if (val < 255) {
/* 307 */             segptr++;
/* 308 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 314 */     if (bodysize != 0) {
/* 315 */       body_expand(bodysize);
/* 316 */       System.arraycopy(body_base, body, this.body_data, this.body_fill, bodysize);
/* 317 */       this.body_fill += bodysize;
/*     */     }
/*     */ 
/* 321 */     int saved = -1;
/* 322 */     while (segptr < segments) {
/* 323 */       int val = header_base[(header + 27 + segptr)] & 0xFF;
/* 324 */       this.lacing_vals[this.lacing_fill] = val;
/* 325 */       this.granule_vals[this.lacing_fill] = -1L;
/*     */ 
/* 327 */       if (bos != 0) {
/* 328 */         this.lacing_vals[this.lacing_fill] |= 256;
/* 329 */         bos = 0;
/*     */       }
/*     */ 
/* 332 */       if (val < 255) {
/* 333 */         saved = this.lacing_fill;
/*     */       }
/* 335 */       this.lacing_fill += 1;
/* 336 */       segptr++;
/*     */ 
/* 338 */       if (val < 255) {
/* 339 */         this.lacing_packet = this.lacing_fill;
/*     */       }
/*     */     }
/*     */ 
/* 343 */     if (saved != -1) {
/* 344 */       this.granule_vals[saved] = granulepos;
/*     */     }
/*     */ 
/* 348 */     if (eos != 0) {
/* 349 */       this.e_o_s = 1;
/* 350 */       if (this.lacing_fill > 0) {
/* 351 */         this.lacing_vals[(this.lacing_fill - 1)] |= 512;
/*     */       }
/*     */     }
/* 354 */     this.pageno = (_pageno + 1);
/* 355 */     return 0;
/*     */   }
/*     */ 
/*     */   public int flush(Page og)
/*     */   {
/* 375 */     int vals = 0;
/* 376 */     int maxvals = this.lacing_fill > 255 ? 255 : this.lacing_fill;
/* 377 */     int bytes = 0;
/* 378 */     int acc = 0;
/* 379 */     long granule_pos = this.granule_vals[0];
/*     */ 
/* 381 */     if (maxvals == 0) {
/* 382 */       return 0;
/*     */     }
/*     */ 
/* 389 */     if (this.b_o_s == 0) {
/* 390 */       granule_pos = 0L;
/* 391 */       for (vals = 0; vals < maxvals; vals++) {
/* 392 */         if ((this.lacing_vals[vals] & 0xFF) < 255) {
/* 393 */           vals++;
/* 394 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 399 */     for (vals = 0; (vals < maxvals) && 
/* 400 */       (acc <= 4096); vals++)
/*     */     {
/* 402 */       acc += (this.lacing_vals[vals] & 0xFF);
/* 403 */       granule_pos = this.granule_vals[vals];
/*     */     }
/*     */ 
/* 408 */     System.arraycopy("OggS".getBytes(), 0, this.header, 0, 4);
/*     */ 
/* 411 */     this.header[4] = 0;
/*     */ 
/* 414 */     this.header[5] = 0;
/* 415 */     if ((this.lacing_vals[0] & 0x100) == 0)
/*     */     {
/*     */       int tmp186_185 = 5;
/*     */       byte[] tmp186_182 = this.header; tmp186_182[tmp186_185] = ((byte)(tmp186_182[tmp186_185] | 0x1));
/*     */     }
/* 418 */     if (this.b_o_s == 0)
/*     */     {
/*     */       int tmp204_203 = 5;
/*     */       byte[] tmp204_200 = this.header; tmp204_200[tmp204_203] = ((byte)(tmp204_200[tmp204_203] | 0x2));
/*     */     }
/* 421 */     if ((this.e_o_s != 0) && (this.lacing_fill == vals))
/*     */     {
/*     */       int tmp230_229 = 5;
/*     */       byte[] tmp230_226 = this.header; tmp230_226[tmp230_229] = ((byte)(tmp230_226[tmp230_229] | 0x4));
/* 423 */     }this.b_o_s = 1;
/*     */ 
/* 426 */     for (int i = 6; i < 14; i++) {
/* 427 */       this.header[i] = ((byte)(int)granule_pos);
/* 428 */       granule_pos >>>= 8;
/*     */     }
/*     */ 
/* 433 */     int _serialno = this.serialno;
/* 434 */     for (i = 14; i < 18; i++) {
/* 435 */       this.header[i] = ((byte)_serialno);
/* 436 */       _serialno >>>= 8;
/*     */     }
/*     */ 
/* 442 */     if (this.pageno == -1) {
/* 443 */       this.pageno = 0;
/*     */     }
/*     */ 
/* 449 */     int _pageno = this.pageno++;
/* 450 */     for (i = 18; i < 22; i++) {
/* 451 */       this.header[i] = ((byte)_pageno);
/* 452 */       _pageno >>>= 8;
/*     */     }
/*     */ 
/* 457 */     this.header[22] = 0;
/* 458 */     this.header[23] = 0;
/* 459 */     this.header[24] = 0;
/* 460 */     this.header[25] = 0;
/*     */ 
/* 463 */     this.header[26] = ((byte)vals);
/* 464 */     for (i = 0; i < vals; i++) {
/* 465 */       this.header[(i + 27)] = ((byte)this.lacing_vals[i]);
/* 466 */       bytes += (this.header[(i + 27)] & 0xFF);
/*     */     }
/*     */ 
/* 470 */     og.header_base = this.header;
/* 471 */     og.header = 0;
/* 472 */     og.header_len = (this.header_fill = vals + 27);
/* 473 */     og.body_base = this.body_data;
/* 474 */     og.body = this.body_returned;
/* 475 */     og.body_len = bytes;
/*     */ 
/* 479 */     this.lacing_fill -= vals;
/* 480 */     System.arraycopy(this.lacing_vals, vals, this.lacing_vals, 0, this.lacing_fill * 4);
/* 481 */     System.arraycopy(this.granule_vals, vals, this.granule_vals, 0, this.lacing_fill * 8);
/* 482 */     this.body_returned += bytes;
/*     */ 
/* 486 */     og.checksum();
/*     */ 
/* 489 */     return 1;
/*     */   }
/*     */ 
/*     */   public int pageout(Page og)
/*     */   {
/* 496 */     if (((this.e_o_s != 0) && (this.lacing_fill != 0)) || (this.body_fill - this.body_returned > 4096) || (this.lacing_fill >= 255) || ((this.lacing_fill != 0) && (this.b_o_s == 0)))
/*     */     {
/* 500 */       return flush(og);
/*     */     }
/* 502 */     return 0;
/*     */   }
/*     */ 
/*     */   public int eof() {
/* 506 */     return this.e_o_s;
/*     */   }
/*     */ 
/*     */   public int reset() {
/* 510 */     this.body_fill = 0;
/* 511 */     this.body_returned = 0;
/*     */ 
/* 513 */     this.lacing_fill = 0;
/* 514 */     this.lacing_packet = 0;
/* 515 */     this.lacing_returned = 0;
/*     */ 
/* 517 */     this.header_fill = 0;
/*     */ 
/* 519 */     this.e_o_s = 0;
/* 520 */     this.b_o_s = 0;
/* 521 */     this.pageno = -1;
/* 522 */     this.packetno = 0L;
/* 523 */     this.granulepos = 0L;
/* 524 */     return 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.StreamState
 * JD-Core Version:    0.6.2
 */