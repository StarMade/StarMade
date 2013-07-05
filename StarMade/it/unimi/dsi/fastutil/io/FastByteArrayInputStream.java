/*     */ package it.unimi.dsi.fastutil.io;
/*     */ 
/*     */ public class FastByteArrayInputStream extends MeasurableInputStream
/*     */   implements RepositionableStream
/*     */ {
/*     */   public byte[] array;
/*     */   public int offset;
/*     */   public int length;
/*     */   private int position;
/*     */   private int mark;
/*     */ 
/*     */   public FastByteArrayInputStream(byte[] array, int offset, int length)
/*     */   {
/*  58 */     this.array = array;
/*  59 */     this.offset = offset;
/*  60 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public FastByteArrayInputStream(byte[] array)
/*     */   {
/*  68 */     this(array, 0, array.length);
/*     */   }
/*     */ 
/*     */   public boolean markSupported() {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   public void reset() {
/*  76 */     this.position = this.mark;
/*     */   }
/*     */ 
/*     */   public void close() {
/*     */   }
/*     */ 
/*     */   public void mark(int dummy) {
/*  83 */     this.mark = this.position;
/*     */   }
/*     */ 
/*     */   public int available() {
/*  87 */     return this.length - this.position;
/*     */   }
/*     */ 
/*     */   public long skip(long n) {
/*  91 */     if (n <= this.length - this.position) {
/*  92 */       this.position += (int)n;
/*  93 */       return n;
/*     */     }
/*  95 */     n = this.length - this.position;
/*  96 */     this.position = this.length;
/*  97 */     return n;
/*     */   }
/*     */ 
/*     */   public int read() {
/* 101 */     if (this.length == this.position) return -1;
/* 102 */     return this.array[(this.offset + this.position++)] & 0xFF;
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int offset, int length)
/*     */   {
/* 112 */     if (this.length == this.position) return length == 0 ? 0 : -1;
/* 113 */     int n = Math.min(length, this.length - this.position);
/* 114 */     System.arraycopy(this.array, this.offset + this.position, b, offset, n);
/* 115 */     this.position += n;
/* 116 */     return n;
/*     */   }
/*     */ 
/*     */   public long position() {
/* 120 */     return this.position;
/*     */   }
/*     */ 
/*     */   public void position(long newPosition) {
/* 124 */     this.position = ((int)Math.min(newPosition, this.length));
/*     */   }
/*     */ 
/*     */   public long length()
/*     */   {
/* 129 */     return this.length;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastByteArrayInputStream
 * JD-Core Version:    0.6.2
 */