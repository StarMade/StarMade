/*     */ package org.newdawn.slick;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import org.newdawn.slick.opengl.ImageData;
/*     */ import org.newdawn.slick.util.GlUtil;
/*     */ 
/*     */ public class ImageBuffer
/*     */   implements ImageData
/*     */ {
/*     */   private int width;
/*     */   private int height;
/*     */   private int texWidth;
/*     */   private int texHeight;
/*     */   private byte[] rawData;
/*     */ 
/*     */   public ImageBuffer(int width, int height)
/*     */   {
/*  41 */     this.width = width;
/*  42 */     this.height = height;
/*     */ 
/*  44 */     this.texWidth = get2Fold(width);
/*  45 */     this.texHeight = get2Fold(height);
/*     */ 
/*  47 */     this.rawData = new byte[this.texWidth * this.texHeight * 4];
/*     */   }
/*     */ 
/*     */   public byte[] getRGBA()
/*     */   {
/*  56 */     return this.rawData;
/*     */   }
/*     */ 
/*     */   public int getDepth()
/*     */   {
/*  63 */     return 32;
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  70 */     return this.height;
/*     */   }
/*     */ 
/*     */   public int getTexHeight()
/*     */   {
/*  77 */     return this.texHeight;
/*     */   }
/*     */ 
/*     */   public int getTexWidth()
/*     */   {
/*  84 */     return this.texWidth;
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/*  91 */     return this.width;
/*     */   }
/*     */ 
/*     */   public ByteBuffer getImageBufferData()
/*     */   {
/*  98 */     ByteBuffer scratch = GlUtil.getDynamicByteBuffer(this.rawData.length);
/*  99 */     scratch.put(this.rawData);
/* 100 */     scratch.flip();
/*     */ 
/* 102 */     return scratch;
/*     */   }
/*     */ 
/*     */   public void setRGBA(int x, int y, int r, int g, int b, int a)
/*     */   {
/* 116 */     if ((x < 0) || (x >= this.width) || (y < 0) || (y >= this.height)) {
/* 117 */       throw new RuntimeException("Specified location: " + x + "," + y + " outside of image");
/*     */     }
/*     */ 
/* 120 */     int ofs = (x + y * this.texWidth) * 4;
/*     */ 
/* 122 */     if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
/* 123 */       this.rawData[ofs] = ((byte)b);
/* 124 */       this.rawData[(ofs + 1)] = ((byte)g);
/* 125 */       this.rawData[(ofs + 2)] = ((byte)r);
/* 126 */       this.rawData[(ofs + 3)] = ((byte)a);
/*     */     } else {
/* 128 */       this.rawData[ofs] = ((byte)r);
/* 129 */       this.rawData[(ofs + 1)] = ((byte)g);
/* 130 */       this.rawData[(ofs + 2)] = ((byte)b);
/* 131 */       this.rawData[(ofs + 3)] = ((byte)a);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Image getImage()
/*     */   {
/* 141 */     return new Image(this);
/*     */   }
/*     */ 
/*     */   public Image getImage(int filter)
/*     */   {
/* 151 */     return new Image(this, filter);
/*     */   }
/*     */ 
/*     */   private int get2Fold(int fold)
/*     */   {
/* 161 */     int ret = 2;
/* 162 */     while (ret < fold) {
/* 163 */       ret *= 2;
/*     */     }
/* 165 */     return ret;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.ImageBuffer
 * JD-Core Version:    0.6.2
 */