/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ public class DspState
/*     */ {
/*     */   static final float M_PI = 3.141593F;
/*     */   static final int VI_TRANSFORMB = 1;
/*     */   static final int VI_WINDOWB = 1;
/*     */   int analysisp;
/*     */   Info vi;
/*     */   int modebits;
/*     */   float[][] pcm;
/*     */   int pcm_storage;
/*     */   int pcm_current;
/*     */   int pcm_returned;
/*     */   float[] multipliers;
/*     */   int envelope_storage;
/*     */   int envelope_current;
/*     */   int eofflag;
/*     */   int lW;
/*     */   int W;
/*     */   int nW;
/*     */   int centerW;
/*     */   long granulepos;
/*     */   long sequence;
/*     */   long glue_bits;
/*     */   long time_bits;
/*     */   long floor_bits;
/*     */   long res_bits;
/*     */   float[][][][][] window;
/*     */   Object[][] transform;
/*     */   CodeBook[] fullbooks;
/*     */   Object[] mode;
/*     */   byte[] header;
/*     */   byte[] header1;
/*     */   byte[] header2;
/*     */ 
/*     */   public DspState()
/*     */   {
/*  78 */     this.transform = new Object[2][];
/*  79 */     this.window = new float[2][][][][];
/*  80 */     this.window[0] = new float[2][][][];
/*  81 */     this.window[0][0] = new float[2][][];
/*  82 */     this.window[0][1] = new float[2][][];
/*  83 */     this.window[0][0][0] = new float[2][];
/*  84 */     this.window[0][0][1] = new float[2][];
/*  85 */     this.window[0][1][0] = new float[2][];
/*  86 */     this.window[0][1][1] = new float[2][];
/*  87 */     this.window[1] = new float[2][][][];
/*  88 */     this.window[1][0] = new float[2][][];
/*  89 */     this.window[1][1] = new float[2][][];
/*  90 */     this.window[1][0][0] = new float[2][];
/*  91 */     this.window[1][0][1] = new float[2][];
/*  92 */     this.window[1][1][0] = new float[2][];
/*  93 */     this.window[1][1][1] = new float[2][];
/*     */   }
/*     */ 
/*     */   static float[] window(int type, int window, int left, int right) {
/*  97 */     float[] ret = new float[window];
/*  98 */     switch (type)
/*     */     {
/*     */     case 0:
/* 102 */       int leftbegin = window / 4 - left / 2;
/* 103 */       int rightbegin = window - window / 4 - right / 2;
/*     */ 
/* 105 */       for (int i = 0; i < left; i++) {
/* 106 */         float x = (float)((i + 0.5D) / left * 3.141592741012573D / 2.0D);
/* 107 */         x = (float)Math.sin(x);
/* 108 */         x *= x;
/* 109 */         x = (float)(x * 1.570796370506287D);
/* 110 */         x = (float)Math.sin(x);
/* 111 */         ret[(i + leftbegin)] = x;
/*     */       }
/*     */ 
/* 114 */       for (int i = leftbegin + left; i < rightbegin; i++) {
/* 115 */         ret[i] = 1.0F;
/*     */       }
/*     */ 
/* 118 */       for (int i = 0; i < right; i++) {
/* 119 */         float x = (float)((right - i - 0.5D) / right * 3.141592741012573D / 2.0D);
/* 120 */         x = (float)Math.sin(x);
/* 121 */         x *= x;
/* 122 */         x = (float)(x * 1.570796370506287D);
/* 123 */         x = (float)Math.sin(x);
/* 124 */         ret[(i + rightbegin)] = x;
/*     */       }
/*     */ 
/* 127 */       break;
/*     */     default:
/* 130 */       return null;
/*     */     }
/* 132 */     return ret;
/*     */   }
/*     */ 
/*     */   int init(Info vi, boolean encp)
/*     */   {
/* 140 */     this.vi = vi;
/* 141 */     this.modebits = Util.ilog2(vi.modes);
/*     */ 
/* 143 */     this.transform[0] = new Object[1];
/* 144 */     this.transform[1] = new Object[1];
/*     */ 
/* 148 */     this.transform[0][0] = new Mdct();
/* 149 */     this.transform[1][0] = new Mdct();
/* 150 */     ((Mdct)this.transform[0][0]).init(vi.blocksizes[0]);
/* 151 */     ((Mdct)this.transform[1][0]).init(vi.blocksizes[1]);
/*     */ 
/* 153 */     this.window[0][0][0] = new float[1][];
/* 154 */     this.window[0][0][1] = this.window[0][0][0];
/* 155 */     this.window[0][1][0] = this.window[0][0][0];
/* 156 */     this.window[0][1][1] = this.window[0][0][0];
/* 157 */     this.window[1][0][0] = new float[1][];
/* 158 */     this.window[1][0][1] = new float[1][];
/* 159 */     this.window[1][1][0] = new float[1][];
/* 160 */     this.window[1][1][1] = new float[1][];
/*     */ 
/* 162 */     for (int i = 0; i < 1; i++) {
/* 163 */       this.window[0][0][0][i] = window(i, vi.blocksizes[0], vi.blocksizes[0] / 2, vi.blocksizes[0] / 2);
/*     */ 
/* 165 */       this.window[1][0][0][i] = window(i, vi.blocksizes[1], vi.blocksizes[0] / 2, vi.blocksizes[0] / 2);
/*     */ 
/* 167 */       this.window[1][0][1][i] = window(i, vi.blocksizes[1], vi.blocksizes[0] / 2, vi.blocksizes[1] / 2);
/*     */ 
/* 169 */       this.window[1][1][0][i] = window(i, vi.blocksizes[1], vi.blocksizes[1] / 2, vi.blocksizes[0] / 2);
/*     */ 
/* 171 */       this.window[1][1][1][i] = window(i, vi.blocksizes[1], vi.blocksizes[1] / 2, vi.blocksizes[1] / 2);
/*     */     }
/*     */ 
/* 175 */     this.fullbooks = new CodeBook[vi.books];
/* 176 */     for (int i = 0; i < vi.books; i++) {
/* 177 */       this.fullbooks[i] = new CodeBook();
/* 178 */       this.fullbooks[i].init_decode(vi.book_param[i]);
/*     */     }
/*     */ 
/* 184 */     this.pcm_storage = 8192;
/*     */ 
/* 187 */     this.pcm = new float[vi.channels][];
/*     */ 
/* 189 */     for (int i = 0; i < vi.channels; i++) {
/* 190 */       this.pcm[i] = new float[this.pcm_storage];
/*     */     }
/*     */ 
/* 196 */     this.lW = 0;
/* 197 */     this.W = 0;
/*     */ 
/* 200 */     this.centerW = (vi.blocksizes[1] / 2);
/*     */ 
/* 202 */     this.pcm_current = this.centerW;
/*     */ 
/* 205 */     this.mode = new Object[vi.modes];
/* 206 */     for (int i = 0; i < vi.modes; i++) {
/* 207 */       int mapnum = vi.mode_param[i].mapping;
/* 208 */       int maptype = vi.map_type[mapnum];
/* 209 */       this.mode[i] = FuncMapping.mapping_P[maptype].look(this, vi.mode_param[i], vi.map_param[mapnum]);
/*     */     }
/*     */ 
/* 212 */     return 0;
/*     */   }
/*     */ 
/*     */   public int synthesis_init(Info vi) {
/* 216 */     init(vi, false);
/*     */ 
/* 218 */     this.pcm_returned = this.centerW;
/* 219 */     this.centerW -= vi.blocksizes[this.W] / 4 + vi.blocksizes[this.lW] / 4;
/* 220 */     this.granulepos = -1L;
/* 221 */     this.sequence = -1L;
/* 222 */     return 0;
/*     */   }
/*     */ 
/*     */   DspState(Info vi) {
/* 226 */     this();
/* 227 */     init(vi, false);
/*     */ 
/* 229 */     this.pcm_returned = this.centerW;
/* 230 */     this.centerW -= vi.blocksizes[this.W] / 4 + vi.blocksizes[this.lW] / 4;
/* 231 */     this.granulepos = -1L;
/* 232 */     this.sequence = -1L;
/*     */   }
/*     */ 
/*     */   public int synthesis_blockin(Block vb)
/*     */   {
/* 242 */     if ((this.centerW > this.vi.blocksizes[1] / 2) && (this.pcm_returned > 8192))
/*     */     {
/* 246 */       int shiftPCM = this.centerW - this.vi.blocksizes[1] / 2;
/* 247 */       shiftPCM = this.pcm_returned < shiftPCM ? this.pcm_returned : shiftPCM;
/*     */ 
/* 249 */       this.pcm_current -= shiftPCM;
/* 250 */       this.centerW -= shiftPCM;
/* 251 */       this.pcm_returned -= shiftPCM;
/* 252 */       if (shiftPCM != 0) {
/* 253 */         for (int i = 0; i < this.vi.channels; i++) {
/* 254 */           System.arraycopy(this.pcm[i], shiftPCM, this.pcm[i], 0, this.pcm_current);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 259 */     this.lW = this.W;
/* 260 */     this.W = vb.W;
/* 261 */     this.nW = -1;
/*     */ 
/* 263 */     this.glue_bits += vb.glue_bits;
/* 264 */     this.time_bits += vb.time_bits;
/* 265 */     this.floor_bits += vb.floor_bits;
/* 266 */     this.res_bits += vb.res_bits;
/*     */ 
/* 268 */     if (this.sequence + 1L != vb.sequence) {
/* 269 */       this.granulepos = -1L;
/*     */     }
/* 271 */     this.sequence = vb.sequence;
/*     */ 
/* 274 */     int sizeW = this.vi.blocksizes[this.W];
/* 275 */     int _centerW = this.centerW + this.vi.blocksizes[this.lW] / 4 + sizeW / 4;
/* 276 */     int beginW = _centerW - sizeW / 2;
/* 277 */     int endW = beginW + sizeW;
/* 278 */     int beginSl = 0;
/* 279 */     int endSl = 0;
/*     */ 
/* 282 */     if (endW > this.pcm_storage)
/*     */     {
/* 284 */       this.pcm_storage = (endW + this.vi.blocksizes[1]);
/* 285 */       for (int i = 0; i < this.vi.channels; i++) {
/* 286 */         float[] foo = new float[this.pcm_storage];
/* 287 */         System.arraycopy(this.pcm[i], 0, foo, 0, this.pcm[i].length);
/* 288 */         this.pcm[i] = foo;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 293 */     switch (this.W) {
/*     */     case 0:
/* 295 */       beginSl = 0;
/* 296 */       endSl = this.vi.blocksizes[0] / 2;
/* 297 */       break;
/*     */     case 1:
/* 299 */       beginSl = this.vi.blocksizes[1] / 4 - this.vi.blocksizes[this.lW] / 4;
/* 300 */       endSl = beginSl + this.vi.blocksizes[this.lW] / 2;
/*     */     }
/*     */ 
/* 304 */     for (int j = 0; j < this.vi.channels; j++) {
/* 305 */       int _pcm = beginW;
/*     */ 
/* 307 */       int i = 0;
/* 308 */       for (i = beginSl; i < endSl; i++) {
/* 309 */         this.pcm[j][(_pcm + i)] += vb.pcm[j][i];
/*     */       }
/*     */ 
/* 312 */       for (; i < sizeW; i++) {
/* 313 */         this.pcm[j][(_pcm + i)] = vb.pcm[j][i];
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 328 */     if (this.granulepos == -1L) {
/* 329 */       this.granulepos = vb.granulepos;
/*     */     }
/*     */     else {
/* 332 */       this.granulepos += _centerW - this.centerW;
/* 333 */       if ((vb.granulepos != -1L) && (this.granulepos != vb.granulepos)) {
/* 334 */         if ((this.granulepos > vb.granulepos) && (vb.eofflag != 0))
/*     */         {
/* 336 */           _centerW = (int)(_centerW - (this.granulepos - vb.granulepos));
/*     */         }
/*     */ 
/* 339 */         this.granulepos = vb.granulepos;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 345 */     this.centerW = _centerW;
/* 346 */     this.pcm_current = endW;
/* 347 */     if (vb.eofflag != 0) {
/* 348 */       this.eofflag = 1;
/*     */     }
/* 350 */     return 0;
/*     */   }
/*     */ 
/*     */   public int synthesis_pcmout(float[][][] _pcm, int[] index)
/*     */   {
/* 355 */     if (this.pcm_returned < this.centerW) {
/* 356 */       if (_pcm != null) {
/* 357 */         for (int i = 0; i < this.vi.channels; i++) {
/* 358 */           index[i] = this.pcm_returned;
/*     */         }
/* 360 */         _pcm[0] = this.pcm;
/*     */       }
/* 362 */       return this.centerW - this.pcm_returned;
/*     */     }
/* 364 */     return 0;
/*     */   }
/*     */ 
/*     */   public int synthesis_read(int bytes) {
/* 368 */     if ((bytes != 0) && (this.pcm_returned + bytes > this.centerW))
/* 369 */       return -1;
/* 370 */     this.pcm_returned += bytes;
/* 371 */     return 0;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.DspState
 * JD-Core Version:    0.6.2
 */