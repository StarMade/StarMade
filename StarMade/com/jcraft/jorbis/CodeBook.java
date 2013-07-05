/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ 
/*     */ class CodeBook
/*     */ {
/*     */   int dim;
/*     */   int entries;
/*     */   StaticCodeBook c;
/*     */   float[] valuelist;
/*     */   int[] codelist;
/*     */   DecodeAux decode_tree;
/*     */   private int[] t;
/*     */ 
/*     */   CodeBook()
/*     */   {
/*  34 */     this.c = new StaticCodeBook();
/*     */ 
/*  84 */     this.t = new int[15];
/*     */   }
/*     */ 
/*     */   int encode(int a, Buffer b)
/*     */   {
/*  42 */     b.write(this.codelist[a], this.c.lengthlist[a]);
/*  43 */     return this.c.lengthlist[a];
/*     */   }
/*     */ 
/*     */   int errorv(float[] a)
/*     */   {
/*  62 */     int best = best(a, 1);
/*  63 */     for (int k = 0; k < this.dim; k++) {
/*  64 */       a[k] = this.valuelist[(best * this.dim + k)];
/*     */     }
/*  66 */     return best;
/*     */   }
/*     */ 
/*     */   int encodev(int best, float[] a, Buffer b)
/*     */   {
/*  71 */     for (int k = 0; k < this.dim; k++) {
/*  72 */       a[k] = this.valuelist[(best * this.dim + k)];
/*     */     }
/*  74 */     return encode(best, b);
/*     */   }
/*     */ 
/*     */   int encodevs(float[] a, Buffer b, int step, int addmul)
/*     */   {
/*  80 */     int best = besterror(a, step, addmul);
/*  81 */     return encode(best, b);
/*     */   }
/*     */ 
/*     */   synchronized int decodevs_add(float[] a, int offset, Buffer b, int n)
/*     */   {
/*  87 */     int step = n / this.dim;
/*     */ 
/*  91 */     if (this.t.length < step) {
/*  92 */       this.t = new int[step];
/*     */     }
/*     */ 
/*  95 */     for (int i = 0; i < step; i++) {
/*  96 */       int entry = decode(b);
/*  97 */       if (entry == -1)
/*  98 */         return -1;
/*  99 */       this.t[i] = (entry * this.dim);
/*     */     }
/* 101 */     i = 0; for (int o = 0; i < this.dim; o += step) {
/* 102 */       for (int j = 0; j < step; j++)
/* 103 */         a[(offset + o + j)] += this.valuelist[(this.t[j] + i)];
/* 101 */       i++;
/*     */     }
/*     */ 
/* 107 */     return 0;
/*     */   }
/*     */ 
/*     */   int decodev_add(float[] a, int offset, Buffer b, int n)
/*     */   {
/*     */     int i;
/* 114 */     if (this.dim > 8)
/* 115 */       for (i = 0; i < n; ) {
/* 116 */         int entry = decode(b);
/* 117 */         if (entry == -1)
/* 118 */           return -1;
/* 119 */         t = entry * this.dim;
/* 120 */         for (j = 0; j < this.dim; )
/* 121 */           a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       }
/*     */     int t;
/*     */     int j;
/* 126 */     for (int i = 0; i < n; ) {
/* 127 */       int entry = decode(b);
/* 128 */       if (entry == -1)
/* 129 */         return -1;
/* 130 */       int t = entry * this.dim;
/* 131 */       int j = 0;
/* 132 */       switch (this.dim) {
/*     */       case 8:
/* 134 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 7:
/* 136 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 6:
/* 138 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 5:
/* 140 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 4:
/* 142 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 3:
/* 144 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 2:
/* 146 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 1:
/* 148 */         a[(offset + i++)] += this.valuelist[(t + j++)];
/*     */       case 0:
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 154 */     return 0;
/*     */   }
/*     */ 
/*     */   int decodev_set(float[] a, int offset, Buffer b, int n)
/*     */   {
/* 161 */     for (int i = 0; i < n; ) {
/* 162 */       int entry = decode(b);
/* 163 */       if (entry == -1)
/* 164 */         return -1;
/* 165 */       t = entry * this.dim;
/* 166 */       for (j = 0; j < this.dim; )
/* 167 */         a[(offset + i++)] = this.valuelist[(t + j++)];
/*     */     }
/*     */     int t;
/*     */     int j;
/* 170 */     return 0;
/*     */   }
/*     */ 
/*     */   int decodevv_add(float[][] a, int offset, int ch, Buffer b, int n)
/*     */   {
/* 175 */     int chptr = 0;
/*     */ 
/* 177 */     for (int i = offset / ch; i < (offset + n) / ch; ) {
/* 178 */       int entry = decode(b);
/* 179 */       if (entry == -1) {
/* 180 */         return -1;
/*     */       }
/* 182 */       int t = entry * this.dim;
/* 183 */       for (int j = 0; j < this.dim; j++) {
/* 184 */         a[(chptr++)][i] += this.valuelist[(t + j)];
/* 185 */         if (chptr == ch) {
/* 186 */           chptr = 0;
/* 187 */           i++;
/*     */         }
/*     */       }
/*     */     }
/* 191 */     return 0;
/*     */   }
/*     */ 
/*     */   int decode(Buffer b)
/*     */   {
/* 210 */     int ptr = 0;
/* 211 */     DecodeAux t = this.decode_tree;
/* 212 */     int lok = b.look(t.tabn);
/*     */ 
/* 214 */     if (lok >= 0) {
/* 215 */       ptr = t.tab[lok];
/* 216 */       b.adv(t.tabl[lok]);
/* 217 */       if (ptr <= 0) {
/* 218 */         return -ptr;
/*     */       }
/*     */     }
/*     */     do {
/* 222 */       switch (b.read1()) {
/*     */       case 0:
/* 224 */         ptr = t.ptr0[ptr];
/* 225 */         break;
/*     */       case 1:
/* 227 */         ptr = t.ptr1[ptr];
/* 228 */         break;
/*     */       case -1:
/*     */       default:
/* 231 */         return -1;
/*     */       }
/*     */     }
/* 234 */     while (ptr > 0);
/* 235 */     return -ptr;
/*     */   }
/*     */ 
/*     */   int decodevs(float[] a, int index, Buffer b, int step, int addmul)
/*     */   {
/* 240 */     int entry = decode(b);
/* 241 */     if (entry == -1)
/* 242 */       return -1;
/* 243 */     switch (addmul) {
/*     */     case -1:
/* 245 */       int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 246 */         a[(index + o)] = this.valuelist[(entry * this.dim + i)];
/*     */ 
/* 245 */         i++;
/*     */       }
/* 247 */       break;
/*     */     case 0:
/* 249 */       int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 250 */         a[(index + o)] += this.valuelist[(entry * this.dim + i)];
/*     */ 
/* 249 */         i++;
/*     */       }
/* 251 */       break;
/*     */     case 1:
/* 253 */       int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 254 */         a[(index + o)] *= this.valuelist[(entry * this.dim + i)];
/*     */ 
/* 253 */         i++;
/*     */       }
/* 255 */       break;
/*     */     }
/*     */ 
/* 259 */     return entry;
/*     */   }
/*     */ 
/*     */   int best(float[] a, int step)
/*     */   {
/* 265 */     int besti = -1;
/* 266 */     float best = 0.0F;
/* 267 */     int e = 0;
/* 268 */     for (int i = 0; i < this.entries; i++) {
/* 269 */       if (this.c.lengthlist[i] > 0) {
/* 270 */         float _this = dist(this.dim, this.valuelist, e, a, step);
/* 271 */         if ((besti == -1) || (_this < best)) {
/* 272 */           best = _this;
/* 273 */           besti = i;
/*     */         }
/*     */       }
/* 276 */       e += this.dim;
/*     */     }
/* 278 */     return besti;
/*     */   }
/*     */ 
/*     */   int besterror(float[] a, int step, int addmul)
/*     */   {
/* 284 */     int best = best(a, step);
/* 285 */     switch (addmul) {
/*     */     case 0:
/* 287 */       int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 288 */         a[o] -= this.valuelist[(best * this.dim + i)];
/*     */ 
/* 287 */         i++;
/*     */       }
/* 289 */       break;
/*     */     case 1:
/* 291 */       int i = 0; for (int o = 0; i < this.dim; o += step) {
/* 292 */         float val = this.valuelist[(best * this.dim + i)];
/* 293 */         if (val == 0.0F) {
/* 294 */           a[o] = 0.0F;
/*     */         }
/*     */         else
/* 297 */           a[o] /= val;
/* 291 */         i++;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 302 */     return best;
/*     */   }
/*     */ 
/*     */   void clear() {
/*     */   }
/*     */ 
/*     */   private static float dist(int el, float[] ref, int index, float[] b, int step) {
/* 309 */     float acc = 0.0F;
/* 310 */     for (int i = 0; i < el; i++) {
/* 311 */       float val = ref[(index + i)] - b[(i * step)];
/* 312 */       acc += val * val;
/*     */     }
/* 314 */     return acc;
/*     */   }
/*     */ 
/*     */   int init_decode(StaticCodeBook s) {
/* 318 */     this.c = s;
/* 319 */     this.entries = s.entries;
/* 320 */     this.dim = s.dim;
/* 321 */     this.valuelist = s.unquantize();
/*     */ 
/* 323 */     this.decode_tree = make_decode_tree();
/* 324 */     if (this.decode_tree == null) {
/* 325 */       clear();
/* 326 */       return -1;
/*     */     }
/* 328 */     return 0;
/*     */   }
/*     */ 
/*     */   static int[] make_words(int[] l, int n)
/*     */   {
/* 335 */     int[] marker = new int[33];
/* 336 */     int[] r = new int[n];
/*     */ 
/* 338 */     for (int i = 0; i < n; i++) {
/* 339 */       int length = l[i];
/* 340 */       if (length > 0) {
/* 341 */         int entry = marker[length];
/*     */ 
/* 349 */         if ((length < 32) && (entry >>> length != 0))
/*     */         {
/* 352 */           return null;
/*     */         }
/* 354 */         r[i] = entry;
/*     */ 
/* 359 */         for (int j = length; j > 0; j--) {
/* 360 */           if ((marker[j] & 0x1) != 0)
/*     */           {
/* 362 */             if (j == 1) {
/* 363 */               marker[1] += 1; break;
/*     */             }
/* 365 */             marker[j] = (marker[(j - 1)] << 1);
/* 366 */             break;
/*     */           }
/*     */ 
/* 369 */           marker[j] += 1;
/*     */         }
/*     */ 
/* 376 */         for (int j = length + 1; (j < 33) && 
/* 377 */           (marker[j] >>> 1 == entry); j++)
/*     */         {
/* 378 */           entry = marker[j];
/* 379 */           marker[j] = (marker[(j - 1)] << 1);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 390 */     for (int i = 0; i < n; i++) {
/* 391 */       int temp = 0;
/* 392 */       for (int j = 0; j < l[i]; j++) {
/* 393 */         temp <<= 1;
/* 394 */         temp |= r[i] >>> j & 0x1;
/*     */       }
/* 396 */       r[i] = temp;
/*     */     }
/*     */ 
/* 399 */     return r;
/*     */   }
/*     */ 
/*     */   DecodeAux make_decode_tree()
/*     */   {
/* 404 */     int top = 0;
/* 405 */     DecodeAux t = new DecodeAux();
/* 406 */     int[] ptr0 = t.ptr0 = new int[this.entries * 2];
/* 407 */     int[] ptr1 = t.ptr1 = new int[this.entries * 2];
/* 408 */     int[] codelist = make_words(this.c.lengthlist, this.c.entries);
/*     */ 
/* 410 */     if (codelist == null)
/* 411 */       return null;
/* 412 */     t.aux = (this.entries * 2);
/*     */ 
/* 414 */     for (int i = 0; i < this.entries; i++) {
/* 415 */       if (this.c.lengthlist[i] > 0) {
/* 416 */         int ptr = 0;
/*     */ 
/* 418 */         for (int j = 0; j < this.c.lengthlist[i] - 1; j++) {
/* 419 */           int bit = codelist[i] >>> j & 0x1;
/* 420 */           if (bit == 0) {
/* 421 */             if (ptr0[ptr] == 0) {
/* 422 */               ptr0[ptr] = (++top);
/*     */             }
/* 424 */             ptr = ptr0[ptr];
/*     */           }
/*     */           else {
/* 427 */             if (ptr1[ptr] == 0) {
/* 428 */               ptr1[ptr] = (++top);
/*     */             }
/* 430 */             ptr = ptr1[ptr];
/*     */           }
/*     */         }
/*     */ 
/* 434 */         if ((codelist[i] >>> j & 0x1) == 0) {
/* 435 */           ptr0[ptr] = (-i);
/*     */         }
/*     */         else {
/* 438 */           ptr1[ptr] = (-i);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 444 */     t.tabn = (Util.ilog(this.entries) - 4);
/*     */ 
/* 446 */     if (t.tabn < 5)
/* 447 */       t.tabn = 5;
/* 448 */     int n = 1 << t.tabn;
/* 449 */     t.tab = new int[n];
/* 450 */     t.tabl = new int[n];
/* 451 */     for (int i = 0; i < n; i++) {
/* 452 */       int p = 0;
/* 453 */       int j = 0;
/* 454 */       for (j = 0; (j < t.tabn) && ((p > 0) || (j == 0)); j++) {
/* 455 */         if ((i & 1 << j) != 0) {
/* 456 */           p = ptr1[p];
/*     */         }
/*     */         else {
/* 459 */           p = ptr0[p];
/*     */         }
/*     */       }
/* 462 */       t.tab[i] = p;
/* 463 */       t.tabl[i] = j;
/*     */     }
/*     */ 
/* 466 */     return t;
/*     */   }
/*     */ 
/*     */   class DecodeAux
/*     */   {
/*     */     int[] tab;
/*     */     int[] tabl;
/*     */     int tabn;
/*     */     int[] ptr0;
/*     */     int[] ptr1;
/*     */     int aux;
/*     */ 
/*     */     DecodeAux()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.CodeBook
 * JD-Core Version:    0.6.2
 */