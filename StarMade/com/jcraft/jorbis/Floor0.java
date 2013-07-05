/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ 
/*     */ class Floor0 extends FuncFloor
/*     */ {
/*     */   float[] lsp;
/*     */ 
/*     */   Floor0()
/*     */   {
/* 125 */     this.lsp = null;
/*     */   }
/*     */ 
/*     */   void pack(Object i, Buffer opb)
/*     */   {
/*  34 */     InfoFloor0 info = (InfoFloor0)i;
/*  35 */     opb.write(info.order, 8);
/*  36 */     opb.write(info.rate, 16);
/*  37 */     opb.write(info.barkmap, 16);
/*  38 */     opb.write(info.ampbits, 6);
/*  39 */     opb.write(info.ampdB, 8);
/*  40 */     opb.write(info.numbooks - 1, 4);
/*  41 */     for (int j = 0; j < info.numbooks; j++)
/*  42 */       opb.write(info.books[j], 8);
/*     */   }
/*     */ 
/*     */   Object unpack(Info vi, Buffer opb) {
/*  46 */     InfoFloor0 info = new InfoFloor0();
/*  47 */     info.order = opb.read(8);
/*  48 */     info.rate = opb.read(16);
/*  49 */     info.barkmap = opb.read(16);
/*  50 */     info.ampbits = opb.read(6);
/*  51 */     info.ampdB = opb.read(8);
/*  52 */     info.numbooks = (opb.read(4) + 1);
/*     */ 
/*  54 */     if ((info.order < 1) || (info.rate < 1) || (info.barkmap < 1) || (info.numbooks < 1)) {
/*  55 */       return null;
/*     */     }
/*     */ 
/*  58 */     for (int j = 0; j < info.numbooks; j++) {
/*  59 */       info.books[j] = opb.read(8);
/*  60 */       if ((info.books[j] < 0) || (info.books[j] >= vi.books)) {
/*  61 */         return null;
/*     */       }
/*     */     }
/*  64 */     return info;
/*     */   }
/*     */ 
/*     */   Object look(DspState vd, InfoMode mi, Object i)
/*     */   {
/*  69 */     Info vi = vd.vi;
/*  70 */     InfoFloor0 info = (InfoFloor0)i;
/*  71 */     LookFloor0 look = new LookFloor0();
/*  72 */     look.m = info.order;
/*  73 */     look.n = (vi.blocksizes[mi.blockflag] / 2);
/*  74 */     look.ln = info.barkmap;
/*  75 */     look.vi = info;
/*  76 */     look.lpclook.init(look.ln, look.m);
/*     */ 
/*  79 */     float scale = look.ln / toBARK((float)(info.rate / 2.0D));
/*     */ 
/*  87 */     look.linearmap = new int[look.n];
/*  88 */     for (int j = 0; j < look.n; j++) {
/*  89 */       int val = (int)Math.floor(toBARK((float)(info.rate / 2.0D / look.n * j)) * scale);
/*  90 */       if (val >= look.ln)
/*  91 */         val = look.ln;
/*  92 */       look.linearmap[j] = val;
/*     */     }
/*  94 */     return look;
/*     */   }
/*     */ 
/*     */   static float toBARK(float f) {
/*  98 */     return (float)(13.1D * Math.atan(0.00074D * f) + 2.24D * Math.atan(f * f * 1.85E-008D) + 0.0001D * f);
/*     */   }
/*     */ 
/*     */   Object state(Object i) {
/* 102 */     EchstateFloor0 state = new EchstateFloor0();
/* 103 */     InfoFloor0 info = (InfoFloor0)i;
/*     */ 
/* 106 */     state.codewords = new int[info.order];
/* 107 */     state.curve = new float[info.barkmap];
/* 108 */     state.frameno = -1L;
/* 109 */     return state;
/*     */   }
/*     */ 
/*     */   void free_info(Object i) {
/*     */   }
/*     */ 
/*     */   void free_look(Object i) {
/*     */   }
/*     */ 
/*     */   void free_state(Object vs) {
/*     */   }
/*     */ 
/*     */   int forward(Block vb, Object i, float[] in, float[] out, Object vs) {
/* 122 */     return 0;
/*     */   }
/*     */ 
/*     */   int inverse(Block vb, Object i, float[] out)
/*     */   {
/* 129 */     LookFloor0 look = (LookFloor0)i;
/* 130 */     InfoFloor0 info = look.vi;
/* 131 */     int ampraw = vb.opb.read(info.ampbits);
/* 132 */     if (ampraw > 0) {
/* 133 */       int maxval = (1 << info.ampbits) - 1;
/* 134 */       float amp = ampraw / maxval * info.ampdB;
/* 135 */       int booknum = vb.opb.read(Util.ilog(info.numbooks));
/*     */ 
/* 137 */       if ((booknum != -1) && (booknum < info.numbooks))
/*     */       {
/* 139 */         synchronized (this) {
/* 140 */           if ((this.lsp == null) || (this.lsp.length < look.m)) {
/* 141 */             this.lsp = new float[look.m];
/*     */           }
/*     */           else {
/* 144 */             for (int j = 0; j < look.m; j++) {
/* 145 */               this.lsp[j] = 0.0F;
/*     */             }
/*     */           }
/* 148 */           CodeBook b = vb.vd.fullbooks[info.books[booknum]];
/* 149 */           float last = 0.0F;
/*     */ 
/* 151 */           for (int j = 0; j < look.m; j++) {
/* 152 */             out[j] = 0.0F;
/*     */           }
/* 154 */           for (int j = 0; j < look.m; j += b.dim) {
/* 155 */             if (b.decodevs(this.lsp, j, vb.opb, 1, -1) == -1) {
/* 156 */               for (int k = 0; k < look.n; k++)
/* 157 */                 out[k] = 0.0F;
/* 158 */               return 0;
/*     */             }
/*     */           }
/* 161 */           for (int j = 0; j < look.m; ) {
/* 162 */             for (int k = 0; k < b.dim; j++) {
/* 163 */               this.lsp[j] += last;
/*     */ 
/* 162 */               k++;
/*     */             }
/* 164 */             last = this.lsp[(j - 1)];
/*     */           }
/*     */ 
/* 167 */           Lsp.lsp_to_curve(out, look.linearmap, look.n, look.ln, this.lsp, look.m, amp, info.ampdB);
/*     */ 
/* 170 */           return 1;
/*     */         }
/*     */       }
/*     */     }
/* 174 */     return 0;
/*     */   }
/*     */ 
/*     */   Object inverse1(Block vb, Object i, Object memo) {
/* 178 */     LookFloor0 look = (LookFloor0)i;
/* 179 */     InfoFloor0 info = look.vi;
/* 180 */     float[] lsp = null;
/* 181 */     if ((memo instanceof float[])) {
/* 182 */       lsp = (float[])memo;
/*     */     }
/*     */ 
/* 185 */     int ampraw = vb.opb.read(info.ampbits);
/* 186 */     if (ampraw > 0) {
/* 187 */       int maxval = (1 << info.ampbits) - 1;
/* 188 */       float amp = ampraw / maxval * info.ampdB;
/* 189 */       int booknum = vb.opb.read(Util.ilog(info.numbooks));
/*     */ 
/* 191 */       if ((booknum != -1) && (booknum < info.numbooks)) {
/* 192 */         CodeBook b = vb.vd.fullbooks[info.books[booknum]];
/* 193 */         float last = 0.0F;
/*     */ 
/* 195 */         if ((lsp == null) || (lsp.length < look.m + 1)) {
/* 196 */           lsp = new float[look.m + 1];
/*     */         }
/*     */         else {
/* 199 */           for (int j = 0; j < lsp.length; j++) {
/* 200 */             lsp[j] = 0.0F;
/*     */           }
/*     */         }
/* 203 */         for (int j = 0; j < look.m; j += b.dim) {
/* 204 */           if (b.decodev_set(lsp, j, vb.opb, b.dim) == -1) {
/* 205 */             return null;
/*     */           }
/*     */         }
/*     */ 
/* 209 */         for (int j = 0; j < look.m; ) {
/* 210 */           for (int k = 0; k < b.dim; j++) {
/* 211 */             lsp[j] += last;
/*     */ 
/* 210 */             k++;
/*     */           }
/* 212 */           last = lsp[(j - 1)];
/*     */         }
/* 214 */         lsp[look.m] = amp;
/* 215 */         return lsp;
/*     */       }
/*     */     }
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */   int inverse2(Block vb, Object i, Object memo, float[] out) {
/* 222 */     LookFloor0 look = (LookFloor0)i;
/* 223 */     InfoFloor0 info = look.vi;
/*     */ 
/* 225 */     if (memo != null) {
/* 226 */       float[] lsp = (float[])memo;
/* 227 */       float amp = lsp[look.m];
/*     */ 
/* 229 */       Lsp.lsp_to_curve(out, look.linearmap, look.n, look.ln, lsp, look.m, amp, info.ampdB);
/*     */ 
/* 231 */       return 1;
/*     */     }
/* 233 */     for (int j = 0; j < look.n; j++) {
/* 234 */       out[j] = 0.0F;
/*     */     }
/* 236 */     return 0;
/*     */   }
/*     */ 
/*     */   static float fromdB(float x) {
/* 240 */     return (float)Math.exp(x * 0.11512925D);
/*     */   }
/*     */ 
/*     */   static void lsp_to_lpc(float[] lsp, float[] lpc, int m) {
/* 244 */     int m2 = m / 2;
/* 245 */     float[] O = new float[m2];
/* 246 */     float[] E = new float[m2];
/*     */ 
/* 248 */     float[] Ae = new float[m2 + 1];
/* 249 */     float[] Ao = new float[m2 + 1];
/*     */ 
/* 251 */     float[] Be = new float[m2];
/* 252 */     float[] Bo = new float[m2];
/*     */ 
/* 256 */     for (int i = 0; i < m2; i++) {
/* 257 */       O[i] = ((float)(-2.0D * Math.cos(lsp[(i * 2)])));
/* 258 */       E[i] = ((float)(-2.0D * Math.cos(lsp[(i * 2 + 1)])));
/*     */     }
/*     */ 
/* 262 */     for (int j = 0; j < m2; j++) {
/* 263 */       Ae[j] = 0.0F;
/* 264 */       Ao[j] = 1.0F;
/* 265 */       Be[j] = 0.0F;
/* 266 */       Bo[j] = 1.0F;
/*     */     }
/* 268 */     Ao[j] = 1.0F;
/* 269 */     Ae[j] = 1.0F;
/*     */ 
/* 272 */     for (i = 1; i < m + 1; i++)
/*     */     {
/*     */       float B;
/* 273 */       float A = B = 0.0F;
/* 274 */       for (j = 0; j < m2; j++) {
/* 275 */         float temp = O[j] * Ao[j] + Ae[j];
/* 276 */         Ae[j] = Ao[j];
/* 277 */         Ao[j] = A;
/* 278 */         A += temp;
/*     */ 
/* 280 */         temp = E[j] * Bo[j] + Be[j];
/* 281 */         Be[j] = Bo[j];
/* 282 */         Bo[j] = B;
/* 283 */         B += temp;
/*     */       }
/* 285 */       lpc[(i - 1)] = ((A + Ao[j] + B - Ae[j]) / 2.0F);
/* 286 */       Ao[j] = A;
/* 287 */       Ae[j] = B;
/*     */     }
/*     */   }
/*     */ 
/*     */   static void lpc_to_curve(float[] curve, float[] lpc, float amp, LookFloor0 l, String name, int frameno)
/*     */   {
/* 294 */     float[] lcurve = new float[Math.max(l.ln * 2, l.m * 2 + 2)];
/*     */ 
/* 296 */     if (amp == 0.0F) {
/* 297 */       for (int j = 0; j < l.n; j++)
/* 298 */         curve[j] = 0.0F;
/* 299 */       return;
/*     */     }
/* 301 */     l.lpclook.lpc_to_curve(lcurve, lpc, amp);
/*     */ 
/* 303 */     for (int i = 0; i < l.n; i++)
/* 304 */       curve[i] = lcurve[l.linearmap[i]];
/*     */   }
/*     */ 
/*     */   class EchstateFloor0
/*     */   {
/*     */     int[] codewords;
/*     */     float[] curve;
/*     */     long frameno;
/*     */     long codes;
/*     */ 
/*     */     EchstateFloor0()
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   class LookFloor0
/*     */   {
/*     */     int n;
/*     */     int ln;
/*     */     int m;
/*     */     int[] linearmap;
/*     */     Floor0.InfoFloor0 vi;
/* 326 */     Lpc lpclook = new Lpc();
/*     */ 
/*     */     LookFloor0()
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   class InfoFloor0
/*     */   {
/*     */     int order;
/*     */     int rate;
/*     */     int barkmap;
/*     */     int ampbits;
/*     */     int ampdB;
/*     */     int numbooks;
/* 316 */     int[] books = new int[16];
/*     */ 
/*     */     InfoFloor0()
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Floor0
 * JD-Core Version:    0.6.2
 */