/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ 
/*     */ class StaticCodeBook
/*     */ {
/*     */   int dim;
/*     */   int entries;
/*     */   int[] lengthlist;
/*     */   int maptype;
/*     */   int q_min;
/*     */   int q_delta;
/*     */   int q_quant;
/*     */   int q_sequencep;
/*     */   int[] quantlist;
/*     */   static final int VQ_FEXP = 10;
/*     */   static final int VQ_FMAN = 21;
/*     */   static final int VQ_FEXP_BIAS = 768;
/*     */ 
/*     */   int pack(Buffer opb)
/*     */   {
/*  62 */     boolean ordered = false;
/*     */ 
/*  64 */     opb.write(5653314, 24);
/*  65 */     opb.write(this.dim, 16);
/*  66 */     opb.write(this.entries, 24);
/*     */ 
/*  71 */     for (int i = 1; (i < this.entries) && 
/*  72 */       (this.lengthlist[i] >= this.lengthlist[(i - 1)]); i++);
/*  75 */     if (i == this.entries) {
/*  76 */       ordered = true;
/*     */     }
/*  78 */     if (ordered)
/*     */     {
/*  83 */       int count = 0;
/*  84 */       opb.write(1, 1);
/*  85 */       opb.write(this.lengthlist[0] - 1, 5);
/*     */ 
/*  87 */       for (i = 1; i < this.entries; i++) {
/*  88 */         int _this = this.lengthlist[i];
/*  89 */         int _last = this.lengthlist[(i - 1)];
/*  90 */         if (_this > _last) {
/*  91 */           for (int j = _last; j < _this; j++) {
/*  92 */             opb.write(i - count, Util.ilog(this.entries - count));
/*  93 */             count = i;
/*     */           }
/*     */         }
/*     */       }
/*  97 */       opb.write(i - count, Util.ilog(this.entries - count));
/*     */     }
/*     */     else
/*     */     {
/* 102 */       opb.write(0, 1);
/*     */ 
/* 107 */       for (i = 0; (i < this.entries) && 
/* 108 */         (this.lengthlist[i] != 0); i++);
/* 112 */       if (i == this.entries) {
/* 113 */         opb.write(0, 1);
/* 114 */         for (i = 0; i < this.entries; i++) {
/* 115 */           opb.write(this.lengthlist[i] - 1, 5);
/*     */         }
/*     */       }
/*     */ 
/* 119 */       opb.write(1, 1);
/* 120 */       for (i = 0; i < this.entries; i++) {
/* 121 */         if (this.lengthlist[i] == 0) {
/* 122 */           opb.write(0, 1);
/*     */         }
/*     */         else {
/* 125 */           opb.write(1, 1);
/* 126 */           opb.write(this.lengthlist[i] - 1, 5);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 134 */     opb.write(this.maptype, 4);
/* 135 */     switch (this.maptype)
/*     */     {
/*     */     case 0:
/* 138 */       break;
/*     */     case 1:
/*     */     case 2:
/* 143 */       if (this.quantlist == null)
/*     */       {
/* 145 */         return -1;
/*     */       }
/*     */ 
/* 149 */       opb.write(this.q_min, 32);
/* 150 */       opb.write(this.q_delta, 32);
/* 151 */       opb.write(this.q_quant - 1, 4);
/* 152 */       opb.write(this.q_sequencep, 1);
/*     */ 
/* 155 */       int quantvals = 0;
/* 156 */       switch (this.maptype)
/*     */       {
/*     */       case 1:
/* 160 */         quantvals = maptype1_quantvals();
/* 161 */         break;
/*     */       case 2:
/* 164 */         quantvals = this.entries * this.dim;
/*     */       }
/*     */ 
/* 169 */       for (i = 0; i < quantvals; i++) {
/* 170 */         opb.write(Math.abs(this.quantlist[i]), this.q_quant);
/*     */       }
/*     */ 
/* 173 */       break;
/*     */     default:
/* 176 */       return -1;
/*     */     }
/* 178 */     return 0;
/*     */   }
/*     */ 
/*     */   int unpack(Buffer opb)
/*     */   {
/* 188 */     if (opb.read(24) != 5653314)
/*     */     {
/* 190 */       clear();
/* 191 */       return -1;
/*     */     }
/*     */ 
/* 195 */     this.dim = opb.read(16);
/* 196 */     this.entries = opb.read(24);
/* 197 */     if (this.entries == -1)
/*     */     {
/* 199 */       clear();
/* 200 */       return -1;
/*     */     }
/*     */     int i;
/* 204 */     switch (opb.read(1))
/*     */     {
/*     */     case 0:
/* 207 */       this.lengthlist = new int[this.entries];
/*     */ 
/* 210 */       if (opb.read(1) != 0)
/*     */       {
/* 213 */         for (int i = 0; i < this.entries; i++) {
/* 214 */           if (opb.read(1) != 0) {
/* 215 */             int num = opb.read(5);
/* 216 */             if (num == -1)
/*     */             {
/* 218 */               clear();
/* 219 */               return -1;
/*     */             }
/* 221 */             this.lengthlist[i] = (num + 1);
/*     */           }
/*     */           else {
/* 224 */             this.lengthlist[i] = 0;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 230 */       for (i = 0; i < this.entries; ) {
/* 231 */         int num = opb.read(5);
/* 232 */         if (num == -1)
/*     */         {
/* 234 */           clear();
/* 235 */           return -1;
/*     */         }
/* 237 */         this.lengthlist[i] = (num + 1);
/*     */ 
/* 230 */         i++; continue;
/*     */ 
/* 244 */         int length = opb.read(5) + 1;
/* 245 */         this.lengthlist = new int[this.entries];
/*     */ 
/* 247 */         for (i = 0; i < this.entries; ) {
/* 248 */           int num = opb.read(Util.ilog(this.entries - i));
/* 249 */           if (num == -1)
/*     */           {
/* 251 */             clear();
/* 252 */             return -1;
/*     */           }
/* 254 */           for (int j = 0; j < num; i++) {
/* 255 */             this.lengthlist[i] = length;
/*     */ 
/* 254 */             j++;
/*     */           }
/*     */ 
/* 257 */           length++;
/*     */         }
/*     */ 
/* 260 */         break;
/*     */ 
/* 263 */         return -1;
/*     */       }
/*     */     case 1:
/*     */     }
/* 267 */     switch (this.maptype = opb.read(4))
/*     */     {
/*     */     case 0:
/* 270 */       break;
/*     */     case 1:
/*     */     case 2:
/* 275 */       this.q_min = opb.read(32);
/* 276 */       this.q_delta = opb.read(32);
/* 277 */       this.q_quant = (opb.read(4) + 1);
/* 278 */       this.q_sequencep = opb.read(1);
/*     */ 
/* 281 */       int quantvals = 0;
/* 282 */       switch (this.maptype) {
/*     */       case 1:
/* 284 */         quantvals = maptype1_quantvals();
/* 285 */         break;
/*     */       case 2:
/* 287 */         quantvals = this.entries * this.dim;
/*     */       }
/*     */ 
/* 292 */       this.quantlist = new int[quantvals];
/* 293 */       for (i = 0; i < quantvals; i++) {
/* 294 */         this.quantlist[i] = opb.read(this.q_quant);
/*     */       }
/* 296 */       if (this.quantlist[(quantvals - 1)] == -1)
/*     */       {
/* 298 */         clear();
/* 299 */         return -1;
/*     */       }
/*     */ 
/* 302 */       break;
/*     */     default:
/* 305 */       clear();
/* 306 */       return -1;
/*     */     }
/*     */ 
/* 309 */     return 0;
/*     */   }
/*     */ 
/*     */   private int maptype1_quantvals()
/*     */   {
/* 320 */     int vals = (int)Math.floor(Math.pow(this.entries, 1.0D / this.dim));
/*     */     while (true)
/*     */     {
/* 328 */       int acc = 1;
/* 329 */       int acc1 = 1;
/* 330 */       for (int i = 0; i < this.dim; i++) {
/* 331 */         acc *= vals;
/* 332 */         acc1 *= (vals + 1);
/*     */       }
/* 334 */       if ((acc <= this.entries) && (acc1 > this.entries)) {
/* 335 */         return vals;
/*     */       }
/*     */ 
/* 338 */       if (acc > this.entries) {
/* 339 */         vals--;
/*     */       }
/*     */       else
/* 342 */         vals++;
/*     */     }
/*     */   }
/*     */ 
/*     */   void clear()
/*     */   {
/*     */   }
/*     */ 
/*     */   float[] unquantize()
/*     */   {
/* 358 */     if ((this.maptype == 1) || (this.maptype == 2))
/*     */     {
/* 360 */       float mindel = float32_unpack(this.q_min);
/* 361 */       float delta = float32_unpack(this.q_delta);
/* 362 */       float[] r = new float[this.entries * this.dim];
/*     */ 
/* 366 */       switch (this.maptype)
/*     */       {
/*     */       case 1:
/* 374 */         int quantvals = maptype1_quantvals();
/* 375 */         for (int j = 0; j < this.entries; j++) {
/* 376 */           float last = 0.0F;
/* 377 */           int indexdiv = 1;
/* 378 */           for (int k = 0; k < this.dim; k++) {
/* 379 */             int index = j / indexdiv % quantvals;
/* 380 */             float val = this.quantlist[index];
/* 381 */             val = Math.abs(val) * delta + mindel + last;
/* 382 */             if (this.q_sequencep != 0)
/* 383 */               last = val;
/* 384 */             r[(j * this.dim + k)] = val;
/* 385 */             indexdiv *= quantvals;
/*     */           }
/*     */         }
/* 388 */         break;
/*     */       case 2:
/* 390 */         for (int j = 0; j < this.entries; j++) {
/* 391 */           float last = 0.0F;
/* 392 */           for (int k = 0; k < this.dim; k++) {
/* 393 */             float val = this.quantlist[(j * this.dim + k)];
/*     */ 
/* 395 */             val = Math.abs(val) * delta + mindel + last;
/* 396 */             if (this.q_sequencep != 0)
/* 397 */               last = val;
/* 398 */             r[(j * this.dim + k)] = val;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 404 */       return r;
/*     */     }
/* 406 */     return null;
/*     */   }
/*     */ 
/*     */   static long float32_pack(float val)
/*     */   {
/* 419 */     int sign = 0;
/*     */ 
/* 422 */     if (val < 0.0F) {
/* 423 */       sign = -2147483648;
/* 424 */       val = -val;
/*     */     }
/* 426 */     int exp = (int)Math.floor(Math.log(val) / Math.log(2.0D));
/* 427 */     int mant = (int)Math.rint(Math.pow(val, 20 - exp));
/* 428 */     exp = exp + 768 << 21;
/* 429 */     return sign | exp | mant;
/*     */   }
/*     */ 
/*     */   static float float32_unpack(int val) {
/* 433 */     float mant = val & 0x1FFFFF;
/* 434 */     float exp = (val & 0x7FE00000) >>> 21;
/* 435 */     if ((val & 0x80000000) != 0)
/* 436 */       mant = -mant;
/* 437 */     return ldexp(mant, (int)exp - 20 - 768);
/*     */   }
/*     */ 
/*     */   static float ldexp(float foo, int e) {
/* 441 */     return (float)(foo * Math.pow(2.0D, e));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.StaticCodeBook
 * JD-Core Version:    0.6.2
 */