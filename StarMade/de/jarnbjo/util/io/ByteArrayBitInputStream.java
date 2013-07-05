/*     */ package de.jarnbjo.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class ByteArrayBitInputStream
/*     */   implements BitInputStream
/*     */ {
/*     */   private byte[] source;
/*     */   private byte currentByte;
/*     */   private int endian;
/*  45 */   private int byteIndex = 0;
/*  46 */   private int bitIndex = 0;
/*     */ 
/*     */   public ByteArrayBitInputStream(byte[] source) {
/*  49 */     this(source, 0);
/*     */   }
/*     */ 
/*     */   public ByteArrayBitInputStream(byte[] source, int endian) {
/*  53 */     this.endian = endian;
/*  54 */     this.source = source;
/*  55 */     this.currentByte = source[0];
/*  56 */     this.bitIndex = (endian == 0 ? 0 : 7);
/*     */   }
/*     */ 
/*     */   public boolean getBit() throws IOException {
/*  60 */     if (this.endian == 0) {
/*  61 */       if (this.bitIndex > 7) {
/*  62 */         this.bitIndex = 0;
/*  63 */         this.currentByte = this.source[(++this.byteIndex)];
/*     */       }
/*  65 */       return (this.currentByte & 1 << this.bitIndex++) != 0;
/*     */     }
/*     */ 
/*  68 */     if (this.bitIndex < 0) {
/*  69 */       this.bitIndex = 7;
/*  70 */       this.currentByte = this.source[(++this.byteIndex)];
/*     */     }
/*  72 */     return (this.currentByte & 1 << this.bitIndex--) != 0;
/*     */   }
/*     */ 
/*     */   public int getInt(int bits) throws IOException
/*     */   {
/*  77 */     if (bits > 32) {
/*  78 */       throw new IllegalArgumentException("Argument \"bits\" must be <= 32");
/*     */     }
/*  80 */     int res = 0;
/*  81 */     if (this.endian == 0) {
/*  82 */       for (int i = 0; i < bits; i++) {
/*  83 */         if (getBit())
/*  84 */           res |= 1 << i;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*  89 */       if (this.bitIndex < 0) {
/*  90 */         this.bitIndex = 7;
/*  91 */         this.currentByte = this.source[(++this.byteIndex)];
/*     */       }
/*  93 */       if (bits <= this.bitIndex + 1) {
/*  94 */         int ci = this.currentByte & 0xFF;
/*  95 */         int offset = 1 + this.bitIndex - bits;
/*  96 */         int mask = (1 << bits) - 1 << offset;
/*  97 */         res = (ci & mask) >> offset;
/*  98 */         this.bitIndex -= bits;
/*     */       }
/*     */       else {
/* 101 */         res = (this.currentByte & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
/* 102 */         bits -= this.bitIndex + 1;
/* 103 */         this.currentByte = this.source[(++this.byteIndex)];
/* 104 */         while (bits >= 8) {
/* 105 */           bits -= 8;
/* 106 */           res |= (this.source[this.byteIndex] & 0xFF) << bits;
/* 107 */           this.currentByte = this.source[(++this.byteIndex)];
/*     */         }
/* 109 */         if (bits > 0) {
/* 110 */           int ci = this.source[this.byteIndex] & 0xFF;
/* 111 */           res |= ci >> 8 - bits & (1 << bits) - 1;
/* 112 */           this.bitIndex = (7 - bits);
/*     */         }
/*     */         else {
/* 115 */           this.currentByte = this.source[(--this.byteIndex)];
/* 116 */           this.bitIndex = -1;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 121 */     return res;
/*     */   }
/*     */ 
/*     */   public int getSignedInt(int bits) throws IOException {
/* 125 */     int raw = getInt(bits);
/* 126 */     if (raw >= 1 << bits - 1) {
/* 127 */       raw -= (1 << bits);
/*     */     }
/* 129 */     return raw;
/*     */   }
/*     */ 
/*     */   public int getInt(HuffmanNode root) throws IOException {
/* 133 */     while (root.value == null) {
/* 134 */       if (this.bitIndex > 7) {
/* 135 */         this.bitIndex = 0;
/* 136 */         this.currentByte = this.source[(++this.byteIndex)];
/*     */       }
/* 138 */       root = (this.currentByte & 1 << this.bitIndex++) != 0 ? root.o1 : root.o0;
/*     */     }
/* 140 */     return root.value.intValue();
/*     */   }
/*     */ 
/*     */   public long getLong(int bits) throws IOException {
/* 144 */     if (bits > 64) {
/* 145 */       throw new IllegalArgumentException("Argument \"bits\" must be <= 64");
/*     */     }
/* 147 */     long res = 0L;
/* 148 */     if (this.endian == 0) {
/* 149 */       for (int i = 0; i < bits; i++) {
/* 150 */         if (getBit()) {
/* 151 */           res |= 1L << i;
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 156 */       for (int i = bits - 1; i >= 0; i--) {
/* 157 */         if (getBit()) {
/* 158 */           res |= 1L << i;
/*     */         }
/*     */       }
/*     */     }
/* 162 */     return res;
/*     */   }
/*     */ 
/*     */   public int readSignedRice(int order)
/*     */     throws IOException
/*     */   {
/* 180 */     int msbs = -1; int lsbs = 0; int res = 0;
/*     */ 
/* 182 */     if (this.endian == 0)
/*     */     {
/* 184 */       throw new UnsupportedOperationException("ByteArrayBitInputStream.readSignedRice() is only supported in big endian mode");
/*     */     }
/*     */ 
/* 189 */     byte cb = this.source[this.byteIndex];
/*     */     do {
/* 191 */       msbs++;
/* 192 */       if (this.bitIndex < 0) {
/* 193 */         this.bitIndex = 7;
/* 194 */         this.byteIndex += 1;
/* 195 */         cb = this.source[this.byteIndex];
/*     */       }
/*     */     }
/* 197 */     while ((cb & 1 << this.bitIndex--) == 0);
/*     */ 
/* 199 */     int bits = order;
/*     */ 
/* 201 */     if (this.bitIndex < 0) {
/* 202 */       this.bitIndex = 7;
/* 203 */       this.byteIndex += 1;
/*     */     }
/* 205 */     if (bits <= this.bitIndex + 1) {
/* 206 */       int ci = this.source[this.byteIndex] & 0xFF;
/* 207 */       int offset = 1 + this.bitIndex - bits;
/* 208 */       int mask = (1 << bits) - 1 << offset;
/* 209 */       lsbs = (ci & mask) >> offset;
/* 210 */       this.bitIndex -= bits;
/*     */     }
/*     */     else {
/* 213 */       lsbs = (this.source[this.byteIndex] & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
/* 214 */       bits -= this.bitIndex + 1;
/* 215 */       this.byteIndex += 1;
/* 216 */       while (bits >= 8) {
/* 217 */         bits -= 8;
/* 218 */         lsbs |= (this.source[this.byteIndex] & 0xFF) << bits;
/* 219 */         this.byteIndex += 1;
/*     */       }
/* 221 */       if (bits > 0) {
/* 222 */         int ci = this.source[this.byteIndex] & 0xFF;
/* 223 */         lsbs |= ci >> 8 - bits & (1 << bits) - 1;
/* 224 */         this.bitIndex = (7 - bits);
/*     */       }
/*     */       else {
/* 227 */         this.byteIndex -= 1;
/* 228 */         this.bitIndex = -1;
/*     */       }
/*     */     }
/*     */ 
/* 232 */     res = msbs << order | lsbs;
/*     */ 
/* 235 */     return (res & 0x1) == 1 ? -(res >> 1) - 1 : res >> 1;
/*     */   }
/*     */ 
/*     */   public void readSignedRice(int order, int[] buffer, int off, int len)
/*     */     throws IOException
/*     */   {
/* 257 */     if (this.endian == 0)
/*     */     {
/* 259 */       throw new UnsupportedOperationException("ByteArrayBitInputStream.readSignedRice() is only supported in big endian mode");
/*     */     }
/*     */ 
/* 263 */     for (int i = off; i < off + len; i++)
/*     */     {
/* 265 */       int msbs = -1; int lsbs = 0;
/*     */ 
/* 267 */       byte cb = this.source[this.byteIndex];
/*     */       do {
/* 269 */         msbs++;
/* 270 */         if (this.bitIndex < 0) {
/* 271 */           this.bitIndex = 7;
/* 272 */           this.byteIndex += 1;
/* 273 */           cb = this.source[this.byteIndex];
/*     */         }
/*     */       }
/* 275 */       while ((cb & 1 << this.bitIndex--) == 0);
/*     */ 
/* 277 */       int bits = order;
/*     */ 
/* 279 */       if (this.bitIndex < 0) {
/* 280 */         this.bitIndex = 7;
/* 281 */         this.byteIndex += 1;
/*     */       }
/* 283 */       if (bits <= this.bitIndex + 1) {
/* 284 */         int ci = this.source[this.byteIndex] & 0xFF;
/* 285 */         int offset = 1 + this.bitIndex - bits;
/* 286 */         int mask = (1 << bits) - 1 << offset;
/* 287 */         lsbs = (ci & mask) >> offset;
/* 288 */         this.bitIndex -= bits;
/*     */       }
/*     */       else {
/* 291 */         lsbs = (this.source[this.byteIndex] & 0xFF & (1 << this.bitIndex + 1) - 1) << bits - this.bitIndex - 1;
/* 292 */         bits -= this.bitIndex + 1;
/* 293 */         this.byteIndex += 1;
/* 294 */         while (bits >= 8) {
/* 295 */           bits -= 8;
/* 296 */           lsbs |= (this.source[this.byteIndex] & 0xFF) << bits;
/* 297 */           this.byteIndex += 1;
/*     */         }
/* 299 */         if (bits > 0) {
/* 300 */           int ci = this.source[this.byteIndex] & 0xFF;
/* 301 */           lsbs |= ci >> 8 - bits & (1 << bits) - 1;
/* 302 */           this.bitIndex = (7 - bits);
/*     */         }
/*     */         else {
/* 305 */           this.byteIndex -= 1;
/* 306 */           this.bitIndex = -1;
/*     */         }
/*     */       }
/*     */ 
/* 310 */       int res = msbs << order | lsbs;
/* 311 */       buffer[i] = ((res & 0x1) == 1 ? -(res >> 1) - 1 : res >> 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void align()
/*     */   {
/* 317 */     if ((this.endian == 1) && (this.bitIndex >= 0)) {
/* 318 */       this.bitIndex = 7;
/* 319 */       this.byteIndex += 1;
/*     */     }
/* 321 */     else if ((this.endian == 0) && (this.bitIndex <= 7)) {
/* 322 */       this.bitIndex = 0;
/* 323 */       this.byteIndex += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setEndian(int endian) {
/* 328 */     if ((this.endian == 1) && (endian == 0)) {
/* 329 */       this.bitIndex = 0;
/* 330 */       this.byteIndex += 1;
/*     */     }
/* 332 */     else if ((this.endian == 0) && (endian == 1)) {
/* 333 */       this.bitIndex = 7;
/* 334 */       this.byteIndex += 1;
/*     */     }
/* 336 */     this.endian = endian;
/*     */   }
/*     */ 
/*     */   public byte[] getSource()
/*     */   {
/* 344 */     return this.source;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.util.io.ByteArrayBitInputStream
 * JD-Core Version:    0.6.2
 */