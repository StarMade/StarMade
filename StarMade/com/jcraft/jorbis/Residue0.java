/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ 
/*     */ class Residue0 extends FuncResidue
/*     */ {
/* 156 */   private static int[][][] _01inverse_partword = new int[2][][];
/*     */ 
/* 229 */   static int[][] _2inverse_partword = (int[][])null;
/*     */ 
/*     */   void pack(Object vr, Buffer opb)
/*     */   {
/*  33 */     InfoResidue0 info = (InfoResidue0)vr;
/*  34 */     int acc = 0;
/*  35 */     opb.write(info.begin, 24);
/*  36 */     opb.write(info.end, 24);
/*     */ 
/*  38 */     opb.write(info.grouping - 1, 24);
/*     */ 
/*  40 */     opb.write(info.partitions - 1, 6);
/*  41 */     opb.write(info.groupbook, 8);
/*     */ 
/*  46 */     for (int j = 0; j < info.partitions; j++) {
/*  47 */       int i = info.secondstages[j];
/*  48 */       if (Util.ilog(i) > 3)
/*     */       {
/*  50 */         opb.write(i, 3);
/*  51 */         opb.write(1, 1);
/*  52 */         opb.write(i >>> 3, 5);
/*     */       }
/*     */       else {
/*  55 */         opb.write(i, 4);
/*     */       }
/*  57 */       acc += Util.icount(i);
/*     */     }
/*  59 */     for (int j = 0; j < acc; j++)
/*  60 */       opb.write(info.booklist[j], 8);
/*     */   }
/*     */ 
/*     */   Object unpack(Info vi, Buffer opb)
/*     */   {
/*  65 */     int acc = 0;
/*  66 */     InfoResidue0 info = new InfoResidue0();
/*  67 */     info.begin = opb.read(24);
/*  68 */     info.end = opb.read(24);
/*  69 */     info.grouping = (opb.read(24) + 1);
/*  70 */     info.partitions = (opb.read(6) + 1);
/*  71 */     info.groupbook = opb.read(8);
/*     */ 
/*  73 */     for (int j = 0; j < info.partitions; j++) {
/*  74 */       int cascade = opb.read(3);
/*  75 */       if (opb.read(1) != 0) {
/*  76 */         cascade |= opb.read(5) << 3;
/*     */       }
/*  78 */       info.secondstages[j] = cascade;
/*  79 */       acc += Util.icount(cascade);
/*     */     }
/*     */ 
/*  82 */     for (int j = 0; j < acc; j++) {
/*  83 */       info.booklist[j] = opb.read(8);
/*     */     }
/*     */ 
/*  86 */     if (info.groupbook >= vi.books) {
/*  87 */       free_info(info);
/*  88 */       return null;
/*     */     }
/*     */ 
/*  91 */     for (int j = 0; j < acc; j++) {
/*  92 */       if (info.booklist[j] >= vi.books) {
/*  93 */         free_info(info);
/*  94 */         return null;
/*     */       }
/*     */     }
/*  97 */     return info;
/*     */   }
/*     */ 
/*     */   Object look(DspState vd, InfoMode vm, Object vr) {
/* 101 */     InfoResidue0 info = (InfoResidue0)vr;
/* 102 */     LookResidue0 look = new LookResidue0();
/* 103 */     int acc = 0;
/*     */ 
/* 105 */     int maxstage = 0;
/* 106 */     look.info = info;
/* 107 */     look.map = vm.mapping;
/*     */ 
/* 109 */     look.parts = info.partitions;
/* 110 */     look.fullbooks = vd.fullbooks;
/* 111 */     look.phrasebook = vd.fullbooks[info.groupbook];
/*     */ 
/* 113 */     int dim = look.phrasebook.dim;
/*     */ 
/* 115 */     look.partbooks = new int[look.parts][];
/*     */ 
/* 117 */     for (int j = 0; j < look.parts; j++) {
/* 118 */       int i = info.secondstages[j];
/* 119 */       int stages = Util.ilog(i);
/* 120 */       if (stages != 0) {
/* 121 */         if (stages > maxstage)
/* 122 */           maxstage = stages;
/* 123 */         look.partbooks[j] = new int[stages];
/* 124 */         for (int k = 0; k < stages; k++) {
/* 125 */           if ((i & 1 << k) != 0) {
/* 126 */             look.partbooks[j][k] = info.booklist[(acc++)];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 132 */     look.partvals = ((int)Math.rint(Math.pow(look.parts, dim)));
/* 133 */     look.stages = maxstage;
/* 134 */     look.decodemap = new int[look.partvals][];
/* 135 */     for (int j = 0; j < look.partvals; j++) {
/* 136 */       int val = j;
/* 137 */       int mult = look.partvals / look.parts;
/* 138 */       look.decodemap[j] = new int[dim];
/*     */ 
/* 140 */       for (int k = 0; k < dim; k++) {
/* 141 */         int deco = val / mult;
/* 142 */         val -= deco * mult;
/* 143 */         mult /= look.parts;
/* 144 */         look.decodemap[j][k] = deco;
/*     */       }
/*     */     }
/* 147 */     return look;
/*     */   }
/*     */ 
/*     */   void free_info(Object i)
/*     */   {
/*     */   }
/*     */ 
/*     */   void free_look(Object i)
/*     */   {
/*     */   }
/*     */ 
/*     */   static synchronized int _01inverse(Block vb, Object vl, float[][] in, int ch, int decodepart)
/*     */   {
/* 162 */     LookResidue0 look = (LookResidue0)vl;
/* 163 */     InfoResidue0 info = look.info;
/*     */ 
/* 166 */     int samples_per_partition = info.grouping;
/* 167 */     int partitions_per_word = look.phrasebook.dim;
/* 168 */     int n = info.end - info.begin;
/*     */ 
/* 170 */     int partvals = n / samples_per_partition;
/* 171 */     int partwords = (partvals + partitions_per_word - 1) / partitions_per_word;
/*     */ 
/* 173 */     if (_01inverse_partword.length < ch) {
/* 174 */       _01inverse_partword = new int[ch][][];
/*     */     }
/*     */ 
/* 177 */     for (int j = 0; j < ch; j++) {
/* 178 */       if ((_01inverse_partword[j] == null) || (_01inverse_partword[j].length < partwords)) {
/* 179 */         _01inverse_partword[j] = new int[partwords][];
/*     */       }
/*     */     }
/*     */ 
/* 183 */     for (int s = 0; s < look.stages; s++)
/*     */     {
/* 186 */       int i = 0; for (int l = 0; i < partvals; l++) {
/* 187 */         if (s == 0)
/*     */         {
/* 189 */           for (j = 0; j < ch; j++) {
/* 190 */             int temp = look.phrasebook.decode(vb.opb);
/* 191 */             if (temp == -1) {
/* 192 */               return 0;
/*     */             }
/* 194 */             _01inverse_partword[j][l] = look.decodemap[temp];
/* 195 */             if (_01inverse_partword[j][l] == null) {
/* 196 */               return 0;
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 202 */         for (int k = 0; (k < partitions_per_word) && (i < partvals); i++) {
/* 203 */           for (j = 0; j < ch; j++) {
/* 204 */             int offset = info.begin + i * samples_per_partition;
/* 205 */             int index = _01inverse_partword[j][l][k];
/* 206 */             if ((info.secondstages[index] & 1 << s) != 0) {
/* 207 */               CodeBook stagebook = look.fullbooks[look.partbooks[index][s]];
/* 208 */               if (stagebook != null)
/* 209 */                 if (decodepart == 0) {
/* 210 */                   if (stagebook.decodevs_add(in[j], offset, vb.opb, samples_per_partition) == -1)
/*     */                   {
/* 212 */                     return 0;
/*     */                   }
/*     */                 }
/* 215 */                 else if ((decodepart == 1) && 
/* 216 */                   (stagebook.decodev_add(in[j], offset, vb.opb, samples_per_partition) == -1))
/*     */                 {
/* 218 */                   return 0;
/*     */                 }
/*     */             }
/*     */           }
/* 202 */           k++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 226 */     return 0;
/*     */   }
/*     */ 
/*     */   static synchronized int _2inverse(Block vb, Object vl, float[][] in, int ch)
/*     */   {
/* 233 */     LookResidue0 look = (LookResidue0)vl;
/* 234 */     InfoResidue0 info = look.info;
/*     */ 
/* 237 */     int samples_per_partition = info.grouping;
/* 238 */     int partitions_per_word = look.phrasebook.dim;
/* 239 */     int n = info.end - info.begin;
/*     */ 
/* 241 */     int partvals = n / samples_per_partition;
/* 242 */     int partwords = (partvals + partitions_per_word - 1) / partitions_per_word;
/*     */ 
/* 244 */     if ((_2inverse_partword == null) || (_2inverse_partword.length < partwords)) {
/* 245 */       _2inverse_partword = new int[partwords][];
/*     */     }
/* 247 */     for (int s = 0; s < look.stages; s++) {
/* 248 */       int i = 0; for (int l = 0; i < partvals; l++) {
/* 249 */         if (s == 0)
/*     */         {
/* 251 */           int temp = look.phrasebook.decode(vb.opb);
/* 252 */           if (temp == -1) {
/* 253 */             return 0;
/*     */           }
/* 255 */           _2inverse_partword[l] = look.decodemap[temp];
/* 256 */           if (_2inverse_partword[l] == null) {
/* 257 */             return 0;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 262 */         for (int k = 0; (k < partitions_per_word) && (i < partvals); i++) {
/* 263 */           int offset = info.begin + i * samples_per_partition;
/* 264 */           int index = _2inverse_partword[l][k];
/* 265 */           if ((info.secondstages[index] & 1 << s) != 0) {
/* 266 */             CodeBook stagebook = look.fullbooks[look.partbooks[index][s]];
/* 267 */             if ((stagebook != null) && 
/* 268 */               (stagebook.decodevv_add(in, offset, ch, vb.opb, samples_per_partition) == -1))
/*     */             {
/* 270 */               return 0;
/*     */             }
/*     */           }
/* 262 */           k++;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 277 */     return 0;
/*     */   }
/*     */ 
/*     */   int inverse(Block vb, Object vl, float[][] in, int[] nonzero, int ch) {
/* 281 */     int used = 0;
/* 282 */     for (int i = 0; i < ch; i++) {
/* 283 */       if (nonzero[i] != 0) {
/* 284 */         in[(used++)] = in[i];
/*     */       }
/*     */     }
/* 287 */     if (used != 0) {
/* 288 */       return _01inverse(vb, vl, in, used, 0);
/*     */     }
/* 290 */     return 0;
/*     */   }
/*     */ 
/*     */   class InfoResidue0
/*     */   {
/*     */     int begin;
/*     */     int end;
/*     */     int grouping;
/*     */     int partitions;
/*     */     int groupbook;
/* 320 */     int[] secondstages = new int[64];
/* 321 */     int[] booklist = new int[256];
/*     */ 
/* 324 */     float[] entmax = new float[64];
/* 325 */     float[] ampmax = new float[64];
/* 326 */     int[] subgrp = new int[64];
/* 327 */     int[] blimit = new int[64];
/*     */ 
/*     */     InfoResidue0()
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   class LookResidue0
/*     */   {
/*     */     Residue0.InfoResidue0 info;
/*     */     int map;
/*     */     int parts;
/*     */     int stages;
/*     */     CodeBook[] fullbooks;
/*     */     CodeBook phrasebook;
/*     */     int[][] partbooks;
/*     */     int partvals;
/*     */     int[][] decodemap;
/*     */     int postbits;
/*     */     int phrasebits;
/*     */     int frames;
/*     */ 
/*     */     LookResidue0()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Residue0
 * JD-Core Version:    0.6.2
 */