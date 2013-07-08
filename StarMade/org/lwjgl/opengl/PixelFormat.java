/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   9:    */public final class PixelFormat
/*  10:    */  implements PixelFormatLWJGL
/*  11:    */{
/*  12:    */  private int bpp;
/*  13:    */  
/*  19:    */  private int alpha;
/*  20:    */  
/*  26:    */  private int depth;
/*  27:    */  
/*  33:    */  private int stencil;
/*  34:    */  
/*  40:    */  private int samples;
/*  41:    */  
/*  47:    */  private int colorSamples;
/*  48:    */  
/*  54:    */  private int num_aux_buffers;
/*  55:    */  
/*  61:    */  private int accum_bpp;
/*  62:    */  
/*  68:    */  private int accum_alpha;
/*  69:    */  
/*  75:    */  private boolean stereo;
/*  76:    */  
/*  81:    */  private boolean floating_point;
/*  82:    */  
/*  87:    */  private boolean floating_point_packed;
/*  88:    */  
/*  93:    */  private boolean sRGB;
/*  94:    */  
/* 100:    */  public PixelFormat()
/* 101:    */  {
/* 102:102 */    this(0, 8, 0);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public PixelFormat(int alpha, int depth, int stencil) {
/* 106:106 */    this(alpha, depth, stencil, 0);
/* 107:    */  }
/* 108:    */  
/* 109:    */  public PixelFormat(int alpha, int depth, int stencil, int samples) {
/* 110:110 */    this(0, alpha, depth, stencil, samples);
/* 111:    */  }
/* 112:    */  
/* 113:    */  public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples) {
/* 114:114 */    this(bpp, alpha, depth, stencil, samples, 0, 0, 0, false);
/* 115:    */  }
/* 116:    */  
/* 117:    */  public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo) {
/* 118:118 */    this(bpp, alpha, depth, stencil, samples, num_aux_buffers, accum_bpp, accum_alpha, stereo, false);
/* 119:    */  }
/* 120:    */  
/* 121:    */  public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo, boolean floating_point) {
/* 122:122 */    this.bpp = bpp;
/* 123:123 */    this.alpha = alpha;
/* 124:124 */    this.depth = depth;
/* 125:125 */    this.stencil = stencil;
/* 126:    */    
/* 127:127 */    this.samples = samples;
/* 128:    */    
/* 129:129 */    this.num_aux_buffers = num_aux_buffers;
/* 130:    */    
/* 131:131 */    this.accum_bpp = accum_bpp;
/* 132:132 */    this.accum_alpha = accum_alpha;
/* 133:    */    
/* 134:134 */    this.stereo = stereo;
/* 135:    */    
/* 136:136 */    this.floating_point = floating_point;
/* 137:137 */    this.floating_point_packed = false;
/* 138:138 */    this.sRGB = false;
/* 139:    */  }
/* 140:    */  
/* 141:    */  private PixelFormat(PixelFormat pf) {
/* 142:142 */    this.bpp = pf.bpp;
/* 143:143 */    this.alpha = pf.alpha;
/* 144:144 */    this.depth = pf.depth;
/* 145:145 */    this.stencil = pf.stencil;
/* 146:    */    
/* 147:147 */    this.samples = pf.samples;
/* 148:148 */    this.colorSamples = pf.colorSamples;
/* 149:    */    
/* 150:150 */    this.num_aux_buffers = pf.num_aux_buffers;
/* 151:    */    
/* 152:152 */    this.accum_bpp = pf.accum_bpp;
/* 153:153 */    this.accum_alpha = pf.accum_alpha;
/* 154:    */    
/* 155:155 */    this.stereo = pf.stereo;
/* 156:    */    
/* 157:157 */    this.floating_point = pf.floating_point;
/* 158:158 */    this.floating_point_packed = pf.floating_point_packed;
/* 159:159 */    this.sRGB = pf.sRGB;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public int getBitsPerPixel() {
/* 163:163 */    return this.bpp;
/* 164:    */  }
/* 165:    */  
/* 172:    */  public PixelFormat withBitsPerPixel(int bpp)
/* 173:    */  {
/* 174:174 */    if (bpp < 0) {
/* 175:175 */      throw new IllegalArgumentException("Invalid number of bits per pixel specified: " + bpp);
/* 176:    */    }
/* 177:177 */    PixelFormat pf = new PixelFormat(this);
/* 178:178 */    pf.bpp = bpp;
/* 179:179 */    return pf;
/* 180:    */  }
/* 181:    */  
/* 182:    */  public int getAlphaBits() {
/* 183:183 */    return this.alpha;
/* 184:    */  }
/* 185:    */  
/* 192:    */  public PixelFormat withAlphaBits(int alpha)
/* 193:    */  {
/* 194:194 */    if (alpha < 0) {
/* 195:195 */      throw new IllegalArgumentException("Invalid number of alpha bits specified: " + alpha);
/* 196:    */    }
/* 197:197 */    PixelFormat pf = new PixelFormat(this);
/* 198:198 */    pf.alpha = alpha;
/* 199:199 */    return pf;
/* 200:    */  }
/* 201:    */  
/* 202:    */  public int getDepthBits() {
/* 203:203 */    return this.depth;
/* 204:    */  }
/* 205:    */  
/* 212:    */  public PixelFormat withDepthBits(int depth)
/* 213:    */  {
/* 214:214 */    if (depth < 0) {
/* 215:215 */      throw new IllegalArgumentException("Invalid number of depth bits specified: " + depth);
/* 216:    */    }
/* 217:217 */    PixelFormat pf = new PixelFormat(this);
/* 218:218 */    pf.depth = depth;
/* 219:219 */    return pf;
/* 220:    */  }
/* 221:    */  
/* 222:    */  public int getStencilBits() {
/* 223:223 */    return this.stencil;
/* 224:    */  }
/* 225:    */  
/* 232:    */  public PixelFormat withStencilBits(int stencil)
/* 233:    */  {
/* 234:234 */    if (stencil < 0) {
/* 235:235 */      throw new IllegalArgumentException("Invalid number of stencil bits specified: " + stencil);
/* 236:    */    }
/* 237:237 */    PixelFormat pf = new PixelFormat(this);
/* 238:238 */    pf.stencil = stencil;
/* 239:239 */    return pf;
/* 240:    */  }
/* 241:    */  
/* 242:    */  public int getSamples() {
/* 243:243 */    return this.samples;
/* 244:    */  }
/* 245:    */  
/* 252:    */  public PixelFormat withSamples(int samples)
/* 253:    */  {
/* 254:254 */    if (samples < 0) {
/* 255:255 */      throw new IllegalArgumentException("Invalid number of samples specified: " + samples);
/* 256:    */    }
/* 257:257 */    PixelFormat pf = new PixelFormat(this);
/* 258:258 */    pf.samples = samples;
/* 259:259 */    return pf;
/* 260:    */  }
/* 261:    */  
/* 270:    */  public PixelFormat withCoverageSamples(int colorSamples)
/* 271:    */  {
/* 272:272 */    return withCoverageSamples(colorSamples, this.samples);
/* 273:    */  }
/* 274:    */  
/* 283:    */  public PixelFormat withCoverageSamples(int colorSamples, int coverageSamples)
/* 284:    */  {
/* 285:285 */    if ((coverageSamples < 0) || (colorSamples < 0) || ((coverageSamples == 0) && (0 < colorSamples)) || (coverageSamples < colorSamples)) {
/* 286:286 */      throw new IllegalArgumentException("Invalid number of coverage samples specified: " + coverageSamples + " - " + colorSamples);
/* 287:    */    }
/* 288:288 */    PixelFormat pf = new PixelFormat(this);
/* 289:289 */    pf.samples = coverageSamples;
/* 290:290 */    pf.colorSamples = colorSamples;
/* 291:291 */    return pf;
/* 292:    */  }
/* 293:    */  
/* 294:    */  public int getAuxBuffers() {
/* 295:295 */    return this.num_aux_buffers;
/* 296:    */  }
/* 297:    */  
/* 304:    */  public PixelFormat withAuxBuffers(int num_aux_buffers)
/* 305:    */  {
/* 306:306 */    if (num_aux_buffers < 0) {
/* 307:307 */      throw new IllegalArgumentException("Invalid number of auxiliary buffers specified: " + num_aux_buffers);
/* 308:    */    }
/* 309:309 */    PixelFormat pf = new PixelFormat(this);
/* 310:310 */    pf.num_aux_buffers = num_aux_buffers;
/* 311:311 */    return pf;
/* 312:    */  }
/* 313:    */  
/* 314:    */  public int getAccumulationBitsPerPixel() {
/* 315:315 */    return this.accum_bpp;
/* 316:    */  }
/* 317:    */  
/* 324:    */  public PixelFormat withAccumulationBitsPerPixel(int accum_bpp)
/* 325:    */  {
/* 326:326 */    if (accum_bpp < 0) {
/* 327:327 */      throw new IllegalArgumentException("Invalid number of bits per pixel in the accumulation buffer specified: " + accum_bpp);
/* 328:    */    }
/* 329:329 */    PixelFormat pf = new PixelFormat(this);
/* 330:330 */    pf.accum_bpp = accum_bpp;
/* 331:331 */    return pf;
/* 332:    */  }
/* 333:    */  
/* 334:    */  public int getAccumulationAlpha() {
/* 335:335 */    return this.accum_alpha;
/* 336:    */  }
/* 337:    */  
/* 344:    */  public PixelFormat withAccumulationAlpha(int accum_alpha)
/* 345:    */  {
/* 346:346 */    if (accum_alpha < 0) {
/* 347:347 */      throw new IllegalArgumentException("Invalid number of alpha bits in the accumulation buffer specified: " + accum_alpha);
/* 348:    */    }
/* 349:349 */    PixelFormat pf = new PixelFormat(this);
/* 350:350 */    pf.accum_alpha = accum_alpha;
/* 351:351 */    return pf;
/* 352:    */  }
/* 353:    */  
/* 354:    */  public boolean isStereo() {
/* 355:355 */    return this.stereo;
/* 356:    */  }
/* 357:    */  
/* 364:    */  public PixelFormat withStereo(boolean stereo)
/* 365:    */  {
/* 366:366 */    PixelFormat pf = new PixelFormat(this);
/* 367:367 */    pf.stereo = stereo;
/* 368:368 */    return pf;
/* 369:    */  }
/* 370:    */  
/* 371:    */  public boolean isFloatingPoint() {
/* 372:372 */    return this.floating_point;
/* 373:    */  }
/* 374:    */  
/* 382:    */  public PixelFormat withFloatingPoint(boolean floating_point)
/* 383:    */  {
/* 384:384 */    PixelFormat pf = new PixelFormat(this);
/* 385:385 */    pf.floating_point = floating_point;
/* 386:386 */    if (floating_point)
/* 387:387 */      pf.floating_point_packed = false;
/* 388:388 */    return pf;
/* 389:    */  }
/* 390:    */  
/* 398:    */  public PixelFormat withFloatingPointPacked(boolean floating_point_packed)
/* 399:    */  {
/* 400:400 */    PixelFormat pf = new PixelFormat(this);
/* 401:401 */    pf.floating_point_packed = floating_point_packed;
/* 402:402 */    if (floating_point_packed)
/* 403:403 */      pf.floating_point = false;
/* 404:404 */    return pf;
/* 405:    */  }
/* 406:    */  
/* 407:    */  public boolean isSRGB() {
/* 408:408 */    return this.sRGB;
/* 409:    */  }
/* 410:    */  
/* 417:    */  public PixelFormat withSRGB(boolean sRGB)
/* 418:    */  {
/* 419:419 */    PixelFormat pf = new PixelFormat(this);
/* 420:420 */    pf.sRGB = sRGB;
/* 421:421 */    return pf;
/* 422:    */  }
/* 423:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.PixelFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */