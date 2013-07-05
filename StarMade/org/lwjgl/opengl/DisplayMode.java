/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ public final class DisplayMode
/*     */ {
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final int bpp;
/*     */   private final int freq;
/*     */   private final boolean fullscreen;
/*     */ 
/*     */   public DisplayMode(int width, int height)
/*     */   {
/*  63 */     this(width, height, 0, 0, false);
/*     */   }
/*     */ 
/*     */   DisplayMode(int width, int height, int bpp, int freq) {
/*  67 */     this(width, height, bpp, freq, true);
/*     */   }
/*     */ 
/*     */   private DisplayMode(int width, int height, int bpp, int freq, boolean fullscreen) {
/*  71 */     this.width = width;
/*  72 */     this.height = height;
/*  73 */     this.bpp = bpp;
/*  74 */     this.freq = freq;
/*  75 */     this.fullscreen = fullscreen;
/*     */   }
/*     */ 
/*     */   public boolean isFullscreenCapable()
/*     */   {
/*  80 */     return this.fullscreen;
/*     */   }
/*     */ 
/*     */   public int getWidth() {
/*  84 */     return this.width;
/*     */   }
/*     */ 
/*     */   public int getHeight() {
/*  88 */     return this.height;
/*     */   }
/*     */ 
/*     */   public int getBitsPerPixel() {
/*  92 */     return this.bpp;
/*     */   }
/*     */ 
/*     */   public int getFrequency() {
/*  96 */     return this.freq;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 105 */     if ((obj == null) || (!(obj instanceof DisplayMode))) {
/* 106 */       return false;
/*     */     }
/*     */ 
/* 109 */     DisplayMode dm = (DisplayMode)obj;
/* 110 */     return (dm.width == this.width) && (dm.height == this.height) && (dm.bpp == this.bpp) && (dm.freq == this.freq);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 122 */     return this.width ^ this.height ^ this.freq ^ this.bpp;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 131 */     StringBuilder sb = new StringBuilder(32);
/* 132 */     sb.append(this.width);
/* 133 */     sb.append(" x ");
/* 134 */     sb.append(this.height);
/* 135 */     sb.append(" x ");
/* 136 */     sb.append(this.bpp);
/* 137 */     sb.append(" @");
/* 138 */     sb.append(this.freq);
/* 139 */     sb.append("Hz");
/* 140 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DisplayMode
 * JD-Core Version:    0.6.2
 */