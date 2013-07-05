/*     */ package org.lwjgl.util;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public abstract interface ReadableColor
/*     */ {
/* 131 */   public static final ReadableColor RED = new Color(255, 0, 0);
/* 132 */   public static final ReadableColor ORANGE = new Color(255, 128, 0);
/* 133 */   public static final ReadableColor YELLOW = new Color(255, 255, 0);
/* 134 */   public static final ReadableColor GREEN = new Color(0, 255, 0);
/* 135 */   public static final ReadableColor CYAN = new Color(0, 255, 255);
/* 136 */   public static final ReadableColor BLUE = new Color(0, 0, 255);
/* 137 */   public static final ReadableColor PURPLE = new Color(255, 0, 255);
/* 138 */   public static final ReadableColor WHITE = new Color(255, 255, 255);
/* 139 */   public static final ReadableColor BLACK = new Color(0, 0, 0);
/* 140 */   public static final ReadableColor LTGREY = new Color(192, 192, 192);
/* 141 */   public static final ReadableColor DKGREY = new Color(64, 64, 64);
/* 142 */   public static final ReadableColor GREY = new Color(128, 128, 128);
/*     */ 
/*     */   public abstract int getRed();
/*     */ 
/*     */   public abstract int getGreen();
/*     */ 
/*     */   public abstract int getBlue();
/*     */ 
/*     */   public abstract int getAlpha();
/*     */ 
/*     */   public abstract byte getRedByte();
/*     */ 
/*     */   public abstract byte getGreenByte();
/*     */ 
/*     */   public abstract byte getBlueByte();
/*     */ 
/*     */   public abstract byte getAlphaByte();
/*     */ 
/*     */   public abstract void writeRGBA(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public abstract void writeRGB(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public abstract void writeABGR(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public abstract void writeBGR(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public abstract void writeBGRA(ByteBuffer paramByteBuffer);
/*     */ 
/*     */   public abstract void writeARGB(ByteBuffer paramByteBuffer);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.ReadableColor
 * JD-Core Version:    0.6.2
 */