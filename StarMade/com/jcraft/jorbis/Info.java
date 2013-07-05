/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ import com.jcraft.jogg.Packet;
/*     */ 
/*     */ public class Info
/*     */ {
/*     */   private static final int OV_EBADPACKET = -136;
/*     */   private static final int OV_ENOTAUDIO = -135;
/*  35 */   private static byte[] _vorbis = "vorbis".getBytes();
/*     */   private static final int VI_TIMEB = 1;
/*     */   private static final int VI_FLOORB = 2;
/*     */   private static final int VI_RESB = 3;
/*     */   private static final int VI_MAPB = 1;
/*     */   private static final int VI_WINDOWB = 1;
/*     */   public int version;
/*     */   public int channels;
/*     */   public int rate;
/*     */   int bitrate_upper;
/*     */   int bitrate_nominal;
/*     */   int bitrate_lower;
/*  69 */   int[] blocksizes = new int[2];
/*     */   int modes;
/*     */   int maps;
/*     */   int times;
/*     */   int floors;
/*     */   int residues;
/*     */   int books;
/*     */   int psys;
/*  84 */   InfoMode[] mode_param = null;
/*     */ 
/*  86 */   int[] map_type = null;
/*  87 */   Object[] map_param = null;
/*     */ 
/*  89 */   int[] time_type = null;
/*  90 */   Object[] time_param = null;
/*     */ 
/*  92 */   int[] floor_type = null;
/*  93 */   Object[] floor_param = null;
/*     */ 
/*  95 */   int[] residue_type = null;
/*  96 */   Object[] residue_param = null;
/*     */ 
/*  98 */   StaticCodeBook[] book_param = null;
/*     */ 
/* 100 */   PsyInfo[] psy_param = new PsyInfo[64];
/*     */   int envelopesa;
/*     */   float preecho_thresh;
/*     */   float preecho_clamp;
/*     */ 
/*     */   public void init()
/*     */   {
/* 109 */     this.rate = 0;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 113 */     for (int i = 0; i < this.modes; i++) {
/* 114 */       this.mode_param[i] = null;
/*     */     }
/* 116 */     this.mode_param = null;
/*     */ 
/* 118 */     for (int i = 0; i < this.maps; i++) {
/* 119 */       FuncMapping.mapping_P[this.map_type[i]].free_info(this.map_param[i]);
/*     */     }
/* 121 */     this.map_param = null;
/*     */ 
/* 123 */     for (int i = 0; i < this.times; i++) {
/* 124 */       FuncTime.time_P[this.time_type[i]].free_info(this.time_param[i]);
/*     */     }
/* 126 */     this.time_param = null;
/*     */ 
/* 128 */     for (int i = 0; i < this.floors; i++) {
/* 129 */       FuncFloor.floor_P[this.floor_type[i]].free_info(this.floor_param[i]);
/*     */     }
/* 131 */     this.floor_param = null;
/*     */ 
/* 133 */     for (int i = 0; i < this.residues; i++) {
/* 134 */       FuncResidue.residue_P[this.residue_type[i]].free_info(this.residue_param[i]);
/*     */     }
/* 136 */     this.residue_param = null;
/*     */ 
/* 142 */     for (int i = 0; i < this.books; i++)
/*     */     {
/* 144 */       if (this.book_param[i] != null) {
/* 145 */         this.book_param[i].clear();
/* 146 */         this.book_param[i] = null;
/*     */       }
/*     */     }
/*     */ 
/* 150 */     this.book_param = null;
/*     */ 
/* 152 */     for (int i = 0; i < this.psys; i++)
/* 153 */       this.psy_param[i].free();
/*     */   }
/*     */ 
/*     */   int unpack_info(Buffer opb)
/*     */   {
/* 160 */     this.version = opb.read(32);
/* 161 */     if (this.version != 0) {
/* 162 */       return -1;
/*     */     }
/* 164 */     this.channels = opb.read(8);
/* 165 */     this.rate = opb.read(32);
/*     */ 
/* 167 */     this.bitrate_upper = opb.read(32);
/* 168 */     this.bitrate_nominal = opb.read(32);
/* 169 */     this.bitrate_lower = opb.read(32);
/*     */ 
/* 171 */     this.blocksizes[0] = (1 << opb.read(4));
/* 172 */     this.blocksizes[1] = (1 << opb.read(4));
/*     */ 
/* 174 */     if ((this.rate < 1) || (this.channels < 1) || (this.blocksizes[0] < 8) || (this.blocksizes[1] < this.blocksizes[0]) || (opb.read(1) != 1))
/*     */     {
/* 176 */       clear();
/* 177 */       return -1;
/*     */     }
/* 179 */     return 0;
/*     */   }
/*     */ 
/*     */   int unpack_books(Buffer opb)
/*     */   {
/* 186 */     this.books = (opb.read(8) + 1);
/*     */ 
/* 188 */     if ((this.book_param == null) || (this.book_param.length != this.books))
/* 189 */       this.book_param = new StaticCodeBook[this.books];
/* 190 */     for (int i = 0; i < this.books; i++) {
/* 191 */       this.book_param[i] = new StaticCodeBook();
/* 192 */       if (this.book_param[i].unpack(opb) != 0) {
/* 193 */         clear();
/* 194 */         return -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 199 */     this.times = (opb.read(6) + 1);
/* 200 */     if ((this.time_type == null) || (this.time_type.length != this.times))
/* 201 */       this.time_type = new int[this.times];
/* 202 */     if ((this.time_param == null) || (this.time_param.length != this.times))
/* 203 */       this.time_param = new Object[this.times];
/* 204 */     for (int i = 0; i < this.times; i++) {
/* 205 */       this.time_type[i] = opb.read(16);
/* 206 */       if ((this.time_type[i] < 0) || (this.time_type[i] >= 1)) {
/* 207 */         clear();
/* 208 */         return -1;
/*     */       }
/* 210 */       this.time_param[i] = FuncTime.time_P[this.time_type[i]].unpack(this, opb);
/* 211 */       if (this.time_param[i] == null) {
/* 212 */         clear();
/* 213 */         return -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 218 */     this.floors = (opb.read(6) + 1);
/* 219 */     if ((this.floor_type == null) || (this.floor_type.length != this.floors))
/* 220 */       this.floor_type = new int[this.floors];
/* 221 */     if ((this.floor_param == null) || (this.floor_param.length != this.floors)) {
/* 222 */       this.floor_param = new Object[this.floors];
/*     */     }
/* 224 */     for (int i = 0; i < this.floors; i++) {
/* 225 */       this.floor_type[i] = opb.read(16);
/* 226 */       if ((this.floor_type[i] < 0) || (this.floor_type[i] >= 2)) {
/* 227 */         clear();
/* 228 */         return -1;
/*     */       }
/*     */ 
/* 231 */       this.floor_param[i] = FuncFloor.floor_P[this.floor_type[i]].unpack(this, opb);
/* 232 */       if (this.floor_param[i] == null) {
/* 233 */         clear();
/* 234 */         return -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 239 */     this.residues = (opb.read(6) + 1);
/*     */ 
/* 241 */     if ((this.residue_type == null) || (this.residue_type.length != this.residues)) {
/* 242 */       this.residue_type = new int[this.residues];
/*     */     }
/* 244 */     if ((this.residue_param == null) || (this.residue_param.length != this.residues)) {
/* 245 */       this.residue_param = new Object[this.residues];
/*     */     }
/* 247 */     for (int i = 0; i < this.residues; i++) {
/* 248 */       this.residue_type[i] = opb.read(16);
/* 249 */       if ((this.residue_type[i] < 0) || (this.residue_type[i] >= 3)) {
/* 250 */         clear();
/* 251 */         return -1;
/*     */       }
/* 253 */       this.residue_param[i] = FuncResidue.residue_P[this.residue_type[i]].unpack(this, opb);
/* 254 */       if (this.residue_param[i] == null) {
/* 255 */         clear();
/* 256 */         return -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 261 */     this.maps = (opb.read(6) + 1);
/* 262 */     if ((this.map_type == null) || (this.map_type.length != this.maps))
/* 263 */       this.map_type = new int[this.maps];
/* 264 */     if ((this.map_param == null) || (this.map_param.length != this.maps))
/* 265 */       this.map_param = new Object[this.maps];
/* 266 */     for (int i = 0; i < this.maps; i++) {
/* 267 */       this.map_type[i] = opb.read(16);
/* 268 */       if ((this.map_type[i] < 0) || (this.map_type[i] >= 1)) {
/* 269 */         clear();
/* 270 */         return -1;
/*     */       }
/* 272 */       this.map_param[i] = FuncMapping.mapping_P[this.map_type[i]].unpack(this, opb);
/* 273 */       if (this.map_param[i] == null) {
/* 274 */         clear();
/* 275 */         return -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 280 */     this.modes = (opb.read(6) + 1);
/* 281 */     if ((this.mode_param == null) || (this.mode_param.length != this.modes))
/* 282 */       this.mode_param = new InfoMode[this.modes];
/* 283 */     for (int i = 0; i < this.modes; i++) {
/* 284 */       this.mode_param[i] = new InfoMode();
/* 285 */       this.mode_param[i].blockflag = opb.read(1);
/* 286 */       this.mode_param[i].windowtype = opb.read(16);
/* 287 */       this.mode_param[i].transformtype = opb.read(16);
/* 288 */       this.mode_param[i].mapping = opb.read(8);
/*     */ 
/* 290 */       if ((this.mode_param[i].windowtype >= 1) || (this.mode_param[i].transformtype >= 1) || (this.mode_param[i].mapping >= this.maps))
/*     */       {
/* 293 */         clear();
/* 294 */         return -1;
/*     */       }
/*     */     }
/*     */ 
/* 298 */     if (opb.read(1) != 1) {
/* 299 */       clear();
/* 300 */       return -1;
/*     */     }
/*     */ 
/* 303 */     return 0;
/*     */   }
/*     */ 
/*     */   public int synthesis_headerin(Comment vc, Packet op)
/*     */   {
/* 312 */     Buffer opb = new Buffer();
/*     */ 
/* 314 */     if (op != null) {
/* 315 */       opb.readinit(op.packet_base, op.packet, op.bytes);
/*     */ 
/* 320 */       byte[] buffer = new byte[6];
/* 321 */       int packtype = opb.read(8);
/* 322 */       opb.read(buffer, 6);
/* 323 */       if ((buffer[0] != 118) || (buffer[1] != 111) || (buffer[2] != 114) || (buffer[3] != 98) || (buffer[4] != 105) || (buffer[5] != 115))
/*     */       {
/* 326 */         return -1;
/*     */       }
/* 328 */       switch (packtype) {
/*     */       case 1:
/* 330 */         if (op.b_o_s == 0)
/*     */         {
/* 332 */           return -1;
/*     */         }
/* 334 */         if (this.rate != 0)
/*     */         {
/* 336 */           return -1;
/*     */         }
/* 338 */         return unpack_info(opb);
/*     */       case 3:
/* 340 */         if (this.rate == 0)
/*     */         {
/* 342 */           return -1;
/*     */         }
/* 344 */         return vc.unpack(opb);
/*     */       case 5:
/* 346 */         if ((this.rate == 0) || (vc.vendor == null))
/*     */         {
/* 348 */           return -1;
/*     */         }
/* 350 */         return unpack_books(opb);
/*     */       case 2:
/*     */       case 4:
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 358 */     return -1;
/*     */   }
/*     */ 
/*     */   int pack_info(Buffer opb)
/*     */   {
/* 364 */     opb.write(1, 8);
/* 365 */     opb.write(_vorbis);
/*     */ 
/* 368 */     opb.write(0, 32);
/* 369 */     opb.write(this.channels, 8);
/* 370 */     opb.write(this.rate, 32);
/*     */ 
/* 372 */     opb.write(this.bitrate_upper, 32);
/* 373 */     opb.write(this.bitrate_nominal, 32);
/* 374 */     opb.write(this.bitrate_lower, 32);
/*     */ 
/* 376 */     opb.write(Util.ilog2(this.blocksizes[0]), 4);
/* 377 */     opb.write(Util.ilog2(this.blocksizes[1]), 4);
/* 378 */     opb.write(1, 1);
/* 379 */     return 0;
/*     */   }
/*     */ 
/*     */   int pack_books(Buffer opb) {
/* 383 */     opb.write(5, 8);
/* 384 */     opb.write(_vorbis);
/*     */ 
/* 387 */     opb.write(this.books - 1, 8);
/* 388 */     for (int i = 0; i < this.books; i++) {
/* 389 */       if (this.book_param[i].pack(opb) != 0)
/*     */       {
/* 391 */         return -1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 396 */     opb.write(this.times - 1, 6);
/* 397 */     for (int i = 0; i < this.times; i++) {
/* 398 */       opb.write(this.time_type[i], 16);
/* 399 */       FuncTime.time_P[this.time_type[i]].pack(this.time_param[i], opb);
/*     */     }
/*     */ 
/* 403 */     opb.write(this.floors - 1, 6);
/* 404 */     for (int i = 0; i < this.floors; i++) {
/* 405 */       opb.write(this.floor_type[i], 16);
/* 406 */       FuncFloor.floor_P[this.floor_type[i]].pack(this.floor_param[i], opb);
/*     */     }
/*     */ 
/* 410 */     opb.write(this.residues - 1, 6);
/* 411 */     for (int i = 0; i < this.residues; i++) {
/* 412 */       opb.write(this.residue_type[i], 16);
/* 413 */       FuncResidue.residue_P[this.residue_type[i]].pack(this.residue_param[i], opb);
/*     */     }
/*     */ 
/* 417 */     opb.write(this.maps - 1, 6);
/* 418 */     for (int i = 0; i < this.maps; i++) {
/* 419 */       opb.write(this.map_type[i], 16);
/* 420 */       FuncMapping.mapping_P[this.map_type[i]].pack(this, this.map_param[i], opb);
/*     */     }
/*     */ 
/* 424 */     opb.write(this.modes - 1, 6);
/* 425 */     for (int i = 0; i < this.modes; i++) {
/* 426 */       opb.write(this.mode_param[i].blockflag, 1);
/* 427 */       opb.write(this.mode_param[i].windowtype, 16);
/* 428 */       opb.write(this.mode_param[i].transformtype, 16);
/* 429 */       opb.write(this.mode_param[i].mapping, 8);
/*     */     }
/* 431 */     opb.write(1, 1);
/* 432 */     return 0;
/*     */   }
/*     */ 
/*     */   public int blocksize(Packet op)
/*     */   {
/* 437 */     Buffer opb = new Buffer();
/*     */ 
/* 441 */     opb.readinit(op.packet_base, op.packet, op.bytes);
/*     */ 
/* 444 */     if (opb.read(1) != 0)
/*     */     {
/* 446 */       return -135;
/*     */     }
/*     */ 
/* 449 */     int modebits = 0;
/* 450 */     int v = this.modes;
/* 451 */     while (v > 1) {
/* 452 */       modebits++;
/* 453 */       v >>>= 1;
/*     */     }
/*     */ 
/* 457 */     int mode = opb.read(modebits);
/*     */ 
/* 459 */     if (mode == -1)
/* 460 */       return -136;
/* 461 */     return this.blocksizes[this.mode_param[mode].blockflag];
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 465 */     return "version:" + new Integer(this.version) + ", channels:" + new Integer(this.channels) + ", rate:" + new Integer(this.rate) + ", bitrate:" + new Integer(this.bitrate_upper) + "," + new Integer(this.bitrate_nominal) + "," + new Integer(this.bitrate_lower);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Info
 * JD-Core Version:    0.6.2
 */