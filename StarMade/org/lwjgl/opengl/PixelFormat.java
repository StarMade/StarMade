/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ public final class PixelFormat
/*     */   implements PixelFormatLWJGL
/*     */ {
/*     */   private int bpp;
/*     */   private int alpha;
/*     */   private int depth;
/*     */   private int stencil;
/*     */   private int samples;
/*     */   private int colorSamples;
/*     */   private int num_aux_buffers;
/*     */   private int accum_bpp;
/*     */   private int accum_alpha;
/*     */   private boolean stereo;
/*     */   private boolean floating_point;
/*     */   private boolean floating_point_packed;
/*     */   private boolean sRGB;
/*     */ 
/*     */   public PixelFormat()
/*     */   {
/* 102 */     this(0, 8, 0);
/*     */   }
/*     */ 
/*     */   public PixelFormat(int alpha, int depth, int stencil) {
/* 106 */     this(alpha, depth, stencil, 0);
/*     */   }
/*     */ 
/*     */   public PixelFormat(int alpha, int depth, int stencil, int samples) {
/* 110 */     this(0, alpha, depth, stencil, samples);
/*     */   }
/*     */ 
/*     */   public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples) {
/* 114 */     this(bpp, alpha, depth, stencil, samples, 0, 0, 0, false);
/*     */   }
/*     */ 
/*     */   public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo) {
/* 118 */     this(bpp, alpha, depth, stencil, samples, num_aux_buffers, accum_bpp, accum_alpha, stereo, false);
/*     */   }
/*     */ 
/*     */   public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo, boolean floating_point) {
/* 122 */     this.bpp = bpp;
/* 123 */     this.alpha = alpha;
/* 124 */     this.depth = depth;
/* 125 */     this.stencil = stencil;
/*     */ 
/* 127 */     this.samples = samples;
/*     */ 
/* 129 */     this.num_aux_buffers = num_aux_buffers;
/*     */ 
/* 131 */     this.accum_bpp = accum_bpp;
/* 132 */     this.accum_alpha = accum_alpha;
/*     */ 
/* 134 */     this.stereo = stereo;
/*     */ 
/* 136 */     this.floating_point = floating_point;
/* 137 */     this.floating_point_packed = false;
/* 138 */     this.sRGB = false;
/*     */   }
/*     */ 
/*     */   private PixelFormat(PixelFormat pf) {
/* 142 */     this.bpp = pf.bpp;
/* 143 */     this.alpha = pf.alpha;
/* 144 */     this.depth = pf.depth;
/* 145 */     this.stencil = pf.stencil;
/*     */ 
/* 147 */     this.samples = pf.samples;
/* 148 */     this.colorSamples = pf.colorSamples;
/*     */ 
/* 150 */     this.num_aux_buffers = pf.num_aux_buffers;
/*     */ 
/* 152 */     this.accum_bpp = pf.accum_bpp;
/* 153 */     this.accum_alpha = pf.accum_alpha;
/*     */ 
/* 155 */     this.stereo = pf.stereo;
/*     */ 
/* 157 */     this.floating_point = pf.floating_point;
/* 158 */     this.floating_point_packed = pf.floating_point_packed;
/* 159 */     this.sRGB = pf.sRGB;
/*     */   }
/*     */ 
/*     */   public int getBitsPerPixel() {
/* 163 */     return this.bpp;
/*     */   }
/*     */ 
/*     */   public PixelFormat withBitsPerPixel(int bpp)
/*     */   {
/* 174 */     if (bpp < 0) {
/* 175 */       throw new IllegalArgumentException("Invalid number of bits per pixel specified: " + bpp);
/*     */     }
/* 177 */     PixelFormat pf = new PixelFormat(this);
/* 178 */     pf.bpp = bpp;
/* 179 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getAlphaBits() {
/* 183 */     return this.alpha;
/*     */   }
/*     */ 
/*     */   public PixelFormat withAlphaBits(int alpha)
/*     */   {
/* 194 */     if (alpha < 0) {
/* 195 */       throw new IllegalArgumentException("Invalid number of alpha bits specified: " + alpha);
/*     */     }
/* 197 */     PixelFormat pf = new PixelFormat(this);
/* 198 */     pf.alpha = alpha;
/* 199 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getDepthBits() {
/* 203 */     return this.depth;
/*     */   }
/*     */ 
/*     */   public PixelFormat withDepthBits(int depth)
/*     */   {
/* 214 */     if (depth < 0) {
/* 215 */       throw new IllegalArgumentException("Invalid number of depth bits specified: " + depth);
/*     */     }
/* 217 */     PixelFormat pf = new PixelFormat(this);
/* 218 */     pf.depth = depth;
/* 219 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getStencilBits() {
/* 223 */     return this.stencil;
/*     */   }
/*     */ 
/*     */   public PixelFormat withStencilBits(int stencil)
/*     */   {
/* 234 */     if (stencil < 0) {
/* 235 */       throw new IllegalArgumentException("Invalid number of stencil bits specified: " + stencil);
/*     */     }
/* 237 */     PixelFormat pf = new PixelFormat(this);
/* 238 */     pf.stencil = stencil;
/* 239 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getSamples() {
/* 243 */     return this.samples;
/*     */   }
/*     */ 
/*     */   public PixelFormat withSamples(int samples)
/*     */   {
/* 254 */     if (samples < 0) {
/* 255 */       throw new IllegalArgumentException("Invalid number of samples specified: " + samples);
/*     */     }
/* 257 */     PixelFormat pf = new PixelFormat(this);
/* 258 */     pf.samples = samples;
/* 259 */     return pf;
/*     */   }
/*     */ 
/*     */   public PixelFormat withCoverageSamples(int colorSamples)
/*     */   {
/* 272 */     return withCoverageSamples(colorSamples, this.samples);
/*     */   }
/*     */ 
/*     */   public PixelFormat withCoverageSamples(int colorSamples, int coverageSamples)
/*     */   {
/* 285 */     if ((coverageSamples < 0) || (colorSamples < 0) || ((coverageSamples == 0) && (0 < colorSamples)) || (coverageSamples < colorSamples)) {
/* 286 */       throw new IllegalArgumentException("Invalid number of coverage samples specified: " + coverageSamples + " - " + colorSamples);
/*     */     }
/* 288 */     PixelFormat pf = new PixelFormat(this);
/* 289 */     pf.samples = coverageSamples;
/* 290 */     pf.colorSamples = colorSamples;
/* 291 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getAuxBuffers() {
/* 295 */     return this.num_aux_buffers;
/*     */   }
/*     */ 
/*     */   public PixelFormat withAuxBuffers(int num_aux_buffers)
/*     */   {
/* 306 */     if (num_aux_buffers < 0) {
/* 307 */       throw new IllegalArgumentException("Invalid number of auxiliary buffers specified: " + num_aux_buffers);
/*     */     }
/* 309 */     PixelFormat pf = new PixelFormat(this);
/* 310 */     pf.num_aux_buffers = num_aux_buffers;
/* 311 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getAccumulationBitsPerPixel() {
/* 315 */     return this.accum_bpp;
/*     */   }
/*     */ 
/*     */   public PixelFormat withAccumulationBitsPerPixel(int accum_bpp)
/*     */   {
/* 326 */     if (accum_bpp < 0) {
/* 327 */       throw new IllegalArgumentException("Invalid number of bits per pixel in the accumulation buffer specified: " + accum_bpp);
/*     */     }
/* 329 */     PixelFormat pf = new PixelFormat(this);
/* 330 */     pf.accum_bpp = accum_bpp;
/* 331 */     return pf;
/*     */   }
/*     */ 
/*     */   public int getAccumulationAlpha() {
/* 335 */     return this.accum_alpha;
/*     */   }
/*     */ 
/*     */   public PixelFormat withAccumulationAlpha(int accum_alpha)
/*     */   {
/* 346 */     if (accum_alpha < 0) {
/* 347 */       throw new IllegalArgumentException("Invalid number of alpha bits in the accumulation buffer specified: " + accum_alpha);
/*     */     }
/* 349 */     PixelFormat pf = new PixelFormat(this);
/* 350 */     pf.accum_alpha = accum_alpha;
/* 351 */     return pf;
/*     */   }
/*     */ 
/*     */   public boolean isStereo() {
/* 355 */     return this.stereo;
/*     */   }
/*     */ 
/*     */   public PixelFormat withStereo(boolean stereo)
/*     */   {
/* 366 */     PixelFormat pf = new PixelFormat(this);
/* 367 */     pf.stereo = stereo;
/* 368 */     return pf;
/*     */   }
/*     */ 
/*     */   public boolean isFloatingPoint() {
/* 372 */     return this.floating_point;
/*     */   }
/*     */ 
/*     */   public PixelFormat withFloatingPoint(boolean floating_point)
/*     */   {
/* 384 */     PixelFormat pf = new PixelFormat(this);
/* 385 */     pf.floating_point = floating_point;
/* 386 */     if (floating_point)
/* 387 */       pf.floating_point_packed = false;
/* 388 */     return pf;
/*     */   }
/*     */ 
/*     */   public PixelFormat withFloatingPointPacked(boolean floating_point_packed)
/*     */   {
/* 400 */     PixelFormat pf = new PixelFormat(this);
/* 401 */     pf.floating_point_packed = floating_point_packed;
/* 402 */     if (floating_point_packed)
/* 403 */       pf.floating_point = false;
/* 404 */     return pf;
/*     */   }
/*     */ 
/*     */   public boolean isSRGB() {
/* 408 */     return this.sRGB;
/*     */   }
/*     */ 
/*     */   public PixelFormat withSRGB(boolean sRGB)
/*     */   {
/* 419 */     PixelFormat pf = new PixelFormat(this);
/* 420 */     pf.sRGB = sRGB;
/* 421 */     return pf;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.PixelFormat
 * JD-Core Version:    0.6.2
 */