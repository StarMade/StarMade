/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public final class Color
/*     */   implements ReadableColor, Serializable, WritableColor
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*     */   private byte red;
/*     */   private byte green;
/*     */   private byte blue;
/*     */   private byte alpha;
/*     */ 
/*     */   public Color()
/*     */   {
/*  53 */     this(0, 0, 0, 255);
/*     */   }
/*     */ 
/*     */   public Color(int r, int g, int b)
/*     */   {
/*  60 */     this(r, g, b, 255);
/*     */   }
/*     */ 
/*     */   public Color(byte r, byte g, byte b)
/*     */   {
/*  67 */     this(r, g, b, (byte)-1);
/*     */   }
/*     */ 
/*     */   public Color(int r, int g, int b, int a)
/*     */   {
/*  74 */     set(r, g, b, a);
/*     */   }
/*     */ 
/*     */   public Color(byte r, byte g, byte b, byte a)
/*     */   {
/*  81 */     set(r, g, b, a);
/*     */   }
/*     */ 
/*     */   public Color(ReadableColor c)
/*     */   {
/*  88 */     setColor(c);
/*     */   }
/*     */ 
/*     */   public void set(int r, int g, int b, int a)
/*     */   {
/*  95 */     this.red = ((byte)r);
/*  96 */     this.green = ((byte)g);
/*  97 */     this.blue = ((byte)b);
/*  98 */     this.alpha = ((byte)a);
/*     */   }
/*     */ 
/*     */   public void set(byte r, byte g, byte b, byte a)
/*     */   {
/* 105 */     this.red = r;
/* 106 */     this.green = g;
/* 107 */     this.blue = b;
/* 108 */     this.alpha = a;
/*     */   }
/*     */ 
/*     */   public void set(int r, int g, int b)
/*     */   {
/* 115 */     set(r, g, b, 255);
/*     */   }
/*     */ 
/*     */   public void set(byte r, byte g, byte b)
/*     */   {
/* 122 */     set(r, g, b, (byte)-1);
/*     */   }
/*     */ 
/*     */   public int getRed()
/*     */   {
/* 129 */     return this.red & 0xFF;
/*     */   }
/*     */ 
/*     */   public int getGreen()
/*     */   {
/* 136 */     return this.green & 0xFF;
/*     */   }
/*     */ 
/*     */   public int getBlue()
/*     */   {
/* 143 */     return this.blue & 0xFF;
/*     */   }
/*     */ 
/*     */   public int getAlpha()
/*     */   {
/* 150 */     return this.alpha & 0xFF;
/*     */   }
/*     */ 
/*     */   public void setRed(int red)
/*     */   {
/* 157 */     this.red = ((byte)red);
/*     */   }
/*     */ 
/*     */   public void setGreen(int green)
/*     */   {
/* 164 */     this.green = ((byte)green);
/*     */   }
/*     */ 
/*     */   public void setBlue(int blue)
/*     */   {
/* 171 */     this.blue = ((byte)blue);
/*     */   }
/*     */ 
/*     */   public void setAlpha(int alpha)
/*     */   {
/* 178 */     this.alpha = ((byte)alpha);
/*     */   }
/*     */ 
/*     */   public void setRed(byte red)
/*     */   {
/* 185 */     this.red = red;
/*     */   }
/*     */ 
/*     */   public void setGreen(byte green)
/*     */   {
/* 192 */     this.green = green;
/*     */   }
/*     */ 
/*     */   public void setBlue(byte blue)
/*     */   {
/* 199 */     this.blue = blue;
/*     */   }
/*     */ 
/*     */   public void setAlpha(byte alpha)
/*     */   {
/* 206 */     this.alpha = alpha;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 213 */     return "Color [" + getRed() + ", " + getGreen() + ", " + getBlue() + ", " + getAlpha() + "]";
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 220 */     return (o != null) && ((o instanceof WritableColor)) && (((WritableColor)o).getRed() == getRed()) && (((WritableColor)o).getGreen() == getGreen()) && (((WritableColor)o).getBlue() == getBlue()) && (((WritableColor)o).getAlpha() == getAlpha());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 232 */     return this.red << 24 | this.green << 16 | this.blue << 8 | this.alpha;
/*     */   }
/*     */ 
/*     */   public byte getAlphaByte()
/*     */   {
/* 239 */     return this.alpha;
/*     */   }
/*     */ 
/*     */   public byte getBlueByte()
/*     */   {
/* 246 */     return this.blue;
/*     */   }
/*     */ 
/*     */   public byte getGreenByte()
/*     */   {
/* 253 */     return this.green;
/*     */   }
/*     */ 
/*     */   public byte getRedByte()
/*     */   {
/* 260 */     return this.red;
/*     */   }
/*     */ 
/*     */   public void writeRGBA(ByteBuffer dest)
/*     */   {
/* 267 */     dest.put(this.red);
/* 268 */     dest.put(this.green);
/* 269 */     dest.put(this.blue);
/* 270 */     dest.put(this.alpha);
/*     */   }
/*     */ 
/*     */   public void writeRGB(ByteBuffer dest)
/*     */   {
/* 277 */     dest.put(this.red);
/* 278 */     dest.put(this.green);
/* 279 */     dest.put(this.blue);
/*     */   }
/*     */ 
/*     */   public void writeABGR(ByteBuffer dest)
/*     */   {
/* 286 */     dest.put(this.alpha);
/* 287 */     dest.put(this.blue);
/* 288 */     dest.put(this.green);
/* 289 */     dest.put(this.red);
/*     */   }
/*     */ 
/*     */   public void writeARGB(ByteBuffer dest)
/*     */   {
/* 296 */     dest.put(this.alpha);
/* 297 */     dest.put(this.red);
/* 298 */     dest.put(this.green);
/* 299 */     dest.put(this.blue);
/*     */   }
/*     */ 
/*     */   public void writeBGR(ByteBuffer dest)
/*     */   {
/* 306 */     dest.put(this.blue);
/* 307 */     dest.put(this.green);
/* 308 */     dest.put(this.red);
/*     */   }
/*     */ 
/*     */   public void writeBGRA(ByteBuffer dest)
/*     */   {
/* 315 */     dest.put(this.blue);
/* 316 */     dest.put(this.green);
/* 317 */     dest.put(this.red);
/* 318 */     dest.put(this.alpha);
/*     */   }
/*     */ 
/*     */   public void readRGBA(ByteBuffer src)
/*     */   {
/* 326 */     this.red = src.get();
/* 327 */     this.green = src.get();
/* 328 */     this.blue = src.get();
/* 329 */     this.alpha = src.get();
/*     */   }
/*     */ 
/*     */   public void readRGB(ByteBuffer src)
/*     */   {
/* 337 */     this.red = src.get();
/* 338 */     this.green = src.get();
/* 339 */     this.blue = src.get();
/*     */   }
/*     */ 
/*     */   public void readARGB(ByteBuffer src)
/*     */   {
/* 347 */     this.alpha = src.get();
/* 348 */     this.red = src.get();
/* 349 */     this.green = src.get();
/* 350 */     this.blue = src.get();
/*     */   }
/*     */ 
/*     */   public void readBGRA(ByteBuffer src)
/*     */   {
/* 358 */     this.blue = src.get();
/* 359 */     this.green = src.get();
/* 360 */     this.red = src.get();
/* 361 */     this.alpha = src.get();
/*     */   }
/*     */ 
/*     */   public void readBGR(ByteBuffer src)
/*     */   {
/* 369 */     this.blue = src.get();
/* 370 */     this.green = src.get();
/* 371 */     this.red = src.get();
/*     */   }
/*     */ 
/*     */   public void readABGR(ByteBuffer src)
/*     */   {
/* 379 */     this.alpha = src.get();
/* 380 */     this.blue = src.get();
/* 381 */     this.green = src.get();
/* 382 */     this.red = src.get();
/*     */   }
/*     */ 
/*     */   public void setColor(ReadableColor src)
/*     */   {
/* 390 */     this.red = src.getRedByte();
/* 391 */     this.green = src.getGreenByte();
/* 392 */     this.blue = src.getBlueByte();
/* 393 */     this.alpha = src.getAlphaByte();
/*     */   }
/*     */ 
/*     */   public void fromHSB(float hue, float saturation, float brightness)
/*     */   {
/* 403 */     if (saturation == 0.0F) {
/* 404 */       this.red = (this.green = this.blue = (byte)(int)(brightness * 255.0F + 0.5F));
/*     */     } else {
/* 406 */       float f3 = (hue - (float)Math.floor(hue)) * 6.0F;
/* 407 */       float f4 = f3 - (float)Math.floor(f3);
/* 408 */       float f5 = brightness * (1.0F - saturation);
/* 409 */       float f6 = brightness * (1.0F - saturation * f4);
/* 410 */       float f7 = brightness * (1.0F - saturation * (1.0F - f4));
/* 411 */       switch ((int)f3) {
/*     */       case 0:
/* 413 */         this.red = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 414 */         this.green = ((byte)(int)(f7 * 255.0F + 0.5F));
/* 415 */         this.blue = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 416 */         break;
/*     */       case 1:
/* 418 */         this.red = ((byte)(int)(f6 * 255.0F + 0.5F));
/* 419 */         this.green = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 420 */         this.blue = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 421 */         break;
/*     */       case 2:
/* 423 */         this.red = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 424 */         this.green = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 425 */         this.blue = ((byte)(int)(f7 * 255.0F + 0.5F));
/* 426 */         break;
/*     */       case 3:
/* 428 */         this.red = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 429 */         this.green = ((byte)(int)(f6 * 255.0F + 0.5F));
/* 430 */         this.blue = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 431 */         break;
/*     */       case 4:
/* 433 */         this.red = ((byte)(int)(f7 * 255.0F + 0.5F));
/* 434 */         this.green = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 435 */         this.blue = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 436 */         break;
/*     */       case 5:
/* 438 */         this.red = ((byte)(int)(brightness * 255.0F + 0.5F));
/* 439 */         this.green = ((byte)(int)(f5 * 255.0F + 0.5F));
/* 440 */         this.blue = ((byte)(int)(f6 * 255.0F + 0.5F));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public float[] toHSB(float[] dest)
/*     */   {
/* 454 */     int r = getRed();
/* 455 */     int g = getGreen();
/* 456 */     int b = getBlue();
/* 457 */     if (dest == null)
/* 458 */       dest = new float[3];
/* 459 */     int l = r <= g ? g : r;
/* 460 */     if (b > l)
/* 461 */       l = b;
/* 462 */     int i1 = r >= g ? g : r;
/* 463 */     if (b < i1)
/* 464 */       i1 = b;
/* 465 */     float brightness = l / 255.0F;
/*     */     float saturation;
/*     */     float saturation;
/* 467 */     if (l != 0)
/* 468 */       saturation = (l - i1) / l;
/*     */     else
/* 470 */       saturation = 0.0F;
/*     */     float hue;
/*     */     float hue;
/* 472 */     if (saturation == 0.0F) {
/* 473 */       hue = 0.0F;
/*     */     } else {
/* 475 */       float f3 = (l - r) / (l - i1);
/* 476 */       float f4 = (l - g) / (l - i1);
/* 477 */       float f5 = (l - b) / (l - i1);
/*     */       float hue;
/* 478 */       if (r == l) {
/* 479 */         hue = f5 - f4;
/*     */       }
/*     */       else
/*     */       {
/*     */         float hue;
/* 480 */         if (g == l)
/* 481 */           hue = 2.0F + f3 - f5;
/*     */         else
/* 483 */           hue = 4.0F + f4 - f3; 
/*     */       }
/* 484 */       hue /= 6.0F;
/* 485 */       if (hue < 0.0F)
/* 486 */         hue += 1.0F;
/*     */     }
/* 488 */     dest[0] = hue;
/* 489 */     dest[1] = saturation;
/* 490 */     dest[2] = brightness;
/* 491 */     return dest;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.Color
 * JD-Core Version:    0.6.2
 */