/*     */ package org.newdawn.slick.opengl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.newdawn.slick.util.GlUtil;
/*     */ 
/*     */ public class PNGImageData
/*     */   implements LoadableImageData
/*     */ {
/*     */   private int width;
/*     */   private int height;
/*     */   private int texHeight;
/*     */   private int texWidth;
/*     */   private PNGDecoder decoder;
/*     */   private int bitDepth;
/*     */   private ByteBuffer scratch;
/*     */ 
/*     */   public int getDepth()
/*     */   {
/*  35 */     return this.bitDepth;
/*     */   }
/*     */ 
/*     */   public ByteBuffer getImageBufferData()
/*     */   {
/*  42 */     return this.scratch;
/*     */   }
/*     */ 
/*     */   public int getTexHeight()
/*     */   {
/*  49 */     return this.texHeight;
/*     */   }
/*     */ 
/*     */   public int getTexWidth()
/*     */   {
/*  56 */     return this.texWidth;
/*     */   }
/*     */ 
/*     */   public ByteBuffer loadImage(InputStream fis)
/*     */     throws IOException
/*     */   {
/*  63 */     return loadImage(fis, false, null);
/*     */   }
/*     */ 
/*     */   public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
/*     */     throws IOException
/*     */   {
/*  70 */     return loadImage(fis, flipped, false, transparent);
/*     */   }
/*     */ 
/*     */   public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
/*     */     throws IOException
/*     */   {
/*  77 */     if (transparent != null) {
/*  78 */       forceAlpha = true;
/*  79 */       throw new IOException("Transparent color not support in custom PNG Decoder");
/*     */     }
/*     */ 
/*  82 */     PNGDecoder decoder = new PNGDecoder(fis);
/*     */ 
/*  84 */     if (!decoder.isRGB()) {
/*  85 */       throw new IOException("Only RGB formatted images are supported by the PNGLoader");
/*     */     }
/*     */ 
/*  88 */     this.width = decoder.getWidth();
/*  89 */     this.height = decoder.getHeight();
/*  90 */     this.texWidth = get2Fold(this.width);
/*  91 */     this.texHeight = get2Fold(this.height);
/*     */ 
/*  93 */     int perPixel = decoder.hasAlpha() ? 4 : 3;
/*  94 */     this.bitDepth = (decoder.hasAlpha() ? 32 : 24);
/*     */ 
/*  97 */     this.scratch = GlUtil.getDynamicByteBuffer(this.texWidth * this.texHeight * perPixel);
/*  98 */     decoder.decode(this.scratch, this.texWidth * perPixel, perPixel == 4 ? PNGDecoder.RGBA : PNGDecoder.RGB);
/*     */ 
/* 100 */     if (this.height < this.texHeight - 1) {
/* 101 */       int topOffset = (this.texHeight - 1) * (this.texWidth * perPixel);
/* 102 */       int bottomOffset = (this.height - 1) * (this.texWidth * perPixel);
/* 103 */       for (int x = 0; x < this.texWidth; x++) {
/* 104 */         for (int i = 0; i < perPixel; i++) {
/* 105 */           this.scratch.put(topOffset + x + i, this.scratch.get(x + i));
/* 106 */           this.scratch.put(bottomOffset + this.texWidth * perPixel + x + i, this.scratch.get(bottomOffset + x + i));
/*     */         }
/*     */       }
/*     */     }
/* 110 */     if (this.width < this.texWidth - 1) {
/* 111 */       for (int y = 0; y < this.texHeight; y++) {
/* 112 */         for (int i = 0; i < perPixel; i++) {
/* 113 */           this.scratch.put((y + 1) * (this.texWidth * perPixel) - perPixel + i, this.scratch.get(y * (this.texWidth * perPixel) + i));
/* 114 */           this.scratch.put(y * (this.texWidth * perPixel) + this.width * perPixel + i, this.scratch.get(y * (this.texWidth * perPixel) + (this.width - 1) * perPixel + i));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 119 */     if ((!decoder.hasAlpha()) && (forceAlpha)) {
/* 120 */       ByteBuffer temp = GlUtil.getDynamicByteBuffer(this.texWidth * this.texHeight * 4);
/* 121 */       for (int x = 0; x < this.texWidth; x++) {
/* 122 */         for (int y = 0; y < this.texHeight; y++) {
/* 123 */           int srcOffset = y * 3 + x * this.texHeight * 3;
/* 124 */           int dstOffset = y * 4 + x * this.texHeight * 4;
/*     */ 
/* 126 */           temp.put(dstOffset, this.scratch.get(srcOffset));
/* 127 */           temp.put(dstOffset + 1, this.scratch.get(srcOffset + 1));
/* 128 */           temp.put(dstOffset + 2, this.scratch.get(srcOffset + 2));
/* 129 */           if ((x < getHeight()) && (y < getWidth()))
/* 130 */             temp.put(dstOffset + 3, (byte)-1);
/*     */           else {
/* 132 */             temp.put(dstOffset + 3, (byte)0);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 137 */       this.bitDepth = 32;
/* 138 */       this.scratch = temp;
/*     */     }
/*     */ 
/* 141 */     if (transparent != null) {
/* 142 */       for (int i = 0; i < this.texWidth * this.texHeight * 4; i += 4) {
/* 143 */         boolean match = true;
/* 144 */         for (int c = 0; c < 3; c++) {
/* 145 */           if (toInt(this.scratch.get(i + c)) != transparent[c]) {
/* 146 */             match = false;
/*     */           }
/*     */         }
/*     */ 
/* 150 */         if (match) {
/* 151 */           this.scratch.put(i + 3, (byte)0);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 156 */     this.scratch.position(0);
/*     */ 
/* 158 */     return this.scratch;
/*     */   }
/*     */ 
/*     */   private int toInt(byte b)
/*     */   {
/* 168 */     if (b < 0) {
/* 169 */       return 256 + b;
/*     */     }
/*     */ 
/* 172 */     return b;
/*     */   }
/*     */ 
/*     */   private int get2Fold(int fold)
/*     */   {
/* 182 */     int ret = 2;
/* 183 */     while (ret < fold) {
/* 184 */       ret *= 2;
/*     */     }
/* 186 */     return ret;
/*     */   }
/*     */ 
/*     */   public void configureEdging(boolean edging)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/* 196 */     return this.width;
/*     */   }
/*     */ 
/*     */   public int getHeight() {
/* 200 */     return this.height;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.PNGImageData
 * JD-Core Version:    0.6.2
 */