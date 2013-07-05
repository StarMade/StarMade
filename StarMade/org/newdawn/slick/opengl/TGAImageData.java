/*     */ package org.newdawn.slick.opengl;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import org.newdawn.slick.util.GlUtil;
/*     */ 
/*     */ public class TGAImageData
/*     */   implements LoadableImageData
/*     */ {
/*     */   private int texWidth;
/*     */   private int texHeight;
/*     */   private int width;
/*     */   private int height;
/*     */   private short pixelDepth;
/*     */ 
/*     */   private short flipEndian(short signedShort)
/*     */   {
/*  48 */     int input = signedShort & 0xFFFF;
/*  49 */     return (short)(input << 8 | (input & 0xFF00) >>> 8);
/*     */   }
/*     */ 
/*     */   public int getDepth()
/*     */   {
/*  56 */     return this.pixelDepth;
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/*  63 */     return this.width;
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  70 */     return this.height;
/*     */   }
/*     */ 
/*     */   public int getTexWidth()
/*     */   {
/*  77 */     return this.texWidth;
/*     */   }
/*     */ 
/*     */   public int getTexHeight()
/*     */   {
/*  84 */     return this.texHeight;
/*     */   }
/*     */ 
/*     */   public ByteBuffer loadImage(InputStream fis)
/*     */     throws IOException
/*     */   {
/*  91 */     return loadImage(fis, true, null);
/*     */   }
/*     */ 
/*     */   public ByteBuffer loadImage(InputStream fis, boolean flipped, int[] transparent)
/*     */     throws IOException
/*     */   {
/*  98 */     return loadImage(fis, flipped, false, transparent);
/*     */   }
/*     */ 
/*     */   public ByteBuffer loadImage(InputStream fis, boolean flipped, boolean forceAlpha, int[] transparent)
/*     */     throws IOException
/*     */   {
/* 105 */     if (transparent != null) {
/* 106 */       forceAlpha = true;
/*     */     }
/* 108 */     byte red = 0;
/* 109 */     byte green = 0;
/* 110 */     byte blue = 0;
/* 111 */     byte alpha = 0;
/*     */ 
/* 113 */     BufferedInputStream bis = new BufferedInputStream(fis, 100000);
/* 114 */     DataInputStream dis = new DataInputStream(bis);
/*     */ 
/* 117 */     short idLength = (short)dis.read();
/* 118 */     short colorMapType = (short)dis.read();
/* 119 */     short imageType = (short)dis.read();
/* 120 */     short cMapStart = flipEndian(dis.readShort());
/* 121 */     short cMapLength = flipEndian(dis.readShort());
/* 122 */     short cMapDepth = (short)dis.read();
/* 123 */     short xOffset = flipEndian(dis.readShort());
/* 124 */     short yOffset = flipEndian(dis.readShort());
/*     */ 
/* 126 */     if (imageType != 2) {
/* 127 */       throw new IOException("Slick only supports uncompressed RGB(A) TGA images");
/*     */     }
/*     */ 
/* 130 */     this.width = flipEndian(dis.readShort());
/* 131 */     this.height = flipEndian(dis.readShort());
/* 132 */     this.pixelDepth = ((short)dis.read());
/* 133 */     if (this.pixelDepth == 32) {
/* 134 */       forceAlpha = false;
/*     */     }
/*     */ 
/* 137 */     this.texWidth = get2Fold(this.width);
/* 138 */     this.texHeight = get2Fold(this.height);
/*     */ 
/* 140 */     short imageDescriptor = (short)dis.read();
/* 141 */     if ((imageDescriptor & 0x20) == 0) {
/* 142 */       flipped = !flipped;
/*     */     }
/*     */ 
/* 146 */     if (idLength > 0) {
/* 147 */       bis.skip(idLength);
/*     */     }
/*     */ 
/* 150 */     byte[] rawData = null;
/* 151 */     if ((this.pixelDepth == 32) || (forceAlpha)) {
/* 152 */       this.pixelDepth = 32;
/* 153 */       rawData = new byte[this.texWidth * this.texHeight * 4];
/* 154 */     } else if (this.pixelDepth == 24) {
/* 155 */       rawData = new byte[this.texWidth * this.texHeight * 3];
/*     */     } else {
/* 157 */       throw new RuntimeException("Only 24 and 32 bit TGAs are supported");
/*     */     }
/*     */ 
/* 160 */     if (this.pixelDepth == 24) {
/* 161 */       if (flipped) {
/* 162 */         for (int i = this.height - 1; i >= 0; i--)
/* 163 */           for (int j = 0; j < this.width; j++) {
/* 164 */             blue = dis.readByte();
/* 165 */             green = dis.readByte();
/* 166 */             red = dis.readByte();
/*     */ 
/* 168 */             int ofs = (j + i * this.texWidth) * 3;
/* 169 */             rawData[ofs] = red;
/* 170 */             rawData[(ofs + 1)] = green;
/* 171 */             rawData[(ofs + 2)] = blue;
/*     */           }
/*     */       }
/*     */       else {
/* 175 */         for (int i = 0; i < this.height; i++)
/* 176 */           for (int j = 0; j < this.width; j++) {
/* 177 */             blue = dis.readByte();
/* 178 */             green = dis.readByte();
/* 179 */             red = dis.readByte();
/*     */ 
/* 181 */             int ofs = (j + i * this.texWidth) * 3;
/* 182 */             rawData[ofs] = red;
/* 183 */             rawData[(ofs + 1)] = green;
/* 184 */             rawData[(ofs + 2)] = blue;
/*     */           }
/*     */       }
/*     */     }
/* 188 */     else if (this.pixelDepth == 32) {
/* 189 */       if (flipped) {
/* 190 */         for (int i = this.height - 1; i >= 0; i--)
/* 191 */           for (int j = 0; j < this.width; j++) {
/* 192 */             blue = dis.readByte();
/* 193 */             green = dis.readByte();
/* 194 */             red = dis.readByte();
/* 195 */             if (forceAlpha)
/* 196 */               alpha = -1;
/*     */             else {
/* 198 */               alpha = dis.readByte();
/*     */             }
/*     */ 
/* 201 */             int ofs = (j + i * this.texWidth) * 4;
/*     */ 
/* 203 */             rawData[ofs] = red;
/* 204 */             rawData[(ofs + 1)] = green;
/* 205 */             rawData[(ofs + 2)] = blue;
/* 206 */             rawData[(ofs + 3)] = alpha;
/*     */ 
/* 208 */             if (alpha == 0) {
/* 209 */               rawData[(ofs + 2)] = 0;
/* 210 */               rawData[(ofs + 1)] = 0;
/* 211 */               rawData[ofs] = 0;
/*     */             }
/*     */           }
/*     */       }
/*     */       else {
/* 216 */         for (int i = 0; i < this.height; i++) {
/* 217 */           for (int j = 0; j < this.width; j++) {
/* 218 */             blue = dis.readByte();
/* 219 */             green = dis.readByte();
/* 220 */             red = dis.readByte();
/* 221 */             if (forceAlpha)
/* 222 */               alpha = -1;
/*     */             else {
/* 224 */               alpha = dis.readByte();
/*     */             }
/*     */ 
/* 227 */             int ofs = (j + i * this.texWidth) * 4;
/*     */ 
/* 229 */             if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
/* 230 */               rawData[ofs] = red;
/* 231 */               rawData[(ofs + 1)] = green;
/* 232 */               rawData[(ofs + 2)] = blue;
/* 233 */               rawData[(ofs + 3)] = alpha;
/*     */             } else {
/* 235 */               rawData[ofs] = red;
/* 236 */               rawData[(ofs + 1)] = green;
/* 237 */               rawData[(ofs + 2)] = blue;
/* 238 */               rawData[(ofs + 3)] = alpha;
/*     */             }
/*     */ 
/* 241 */             if (alpha == 0) {
/* 242 */               rawData[(ofs + 2)] = 0;
/* 243 */               rawData[(ofs + 1)] = 0;
/* 244 */               rawData[ofs] = 0;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 250 */     fis.close();
/*     */ 
/* 252 */     if (transparent != null) {
/* 253 */       for (int i = 0; i < rawData.length; i += 4) {
/* 254 */         boolean match = true;
/* 255 */         for (int c = 0; c < 3; c++) {
/* 256 */           if (rawData[(i + c)] != transparent[c]) {
/* 257 */             match = false;
/*     */           }
/*     */         }
/*     */ 
/* 261 */         if (match) {
/* 262 */           rawData[(i + 3)] = 0;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 268 */     ByteBuffer scratch = GlUtil.getDynamicByteBuffer(rawData.length);
/* 269 */     scratch.put(rawData);
/*     */ 
/* 271 */     int perPixel = this.pixelDepth / 8;
/* 272 */     if (this.height < this.texHeight - 1) {
/* 273 */       int topOffset = (this.texHeight - 1) * (this.texWidth * perPixel);
/* 274 */       int bottomOffset = (this.height - 1) * (this.texWidth * perPixel);
/* 275 */       for (int x = 0; x < this.texWidth * perPixel; x++) {
/* 276 */         scratch.put(topOffset + x, scratch.get(x));
/* 277 */         scratch.put(bottomOffset + this.texWidth * perPixel + x, scratch.get(this.texWidth * perPixel + x));
/*     */       }
/*     */     }
/* 280 */     if (this.width < this.texWidth - 1) {
/* 281 */       for (int y = 0; y < this.texHeight; y++) {
/* 282 */         for (int i = 0; i < perPixel; i++) {
/* 283 */           scratch.put((y + 1) * (this.texWidth * perPixel) - perPixel + i, scratch.get(y * (this.texWidth * perPixel) + i));
/* 284 */           scratch.put(y * (this.texWidth * perPixel) + this.width * perPixel + i, scratch.get(y * (this.texWidth * perPixel) + (this.width - 1) * perPixel + i));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 289 */     scratch.flip();
/*     */ 
/* 291 */     return scratch;
/*     */   }
/*     */ 
/*     */   private int get2Fold(int fold)
/*     */   {
/* 301 */     int ret = 2;
/* 302 */     while (ret < fold) {
/* 303 */       ret *= 2;
/*     */     }
/* 305 */     return ret;
/*     */   }
/*     */ 
/*     */   public ByteBuffer getImageBufferData()
/*     */   {
/* 312 */     throw new RuntimeException("TGAImageData doesn't store it's image.");
/*     */   }
/*     */ 
/*     */   public void configureEdging(boolean edging)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.TGAImageData
 * JD-Core Version:    0.6.2
 */