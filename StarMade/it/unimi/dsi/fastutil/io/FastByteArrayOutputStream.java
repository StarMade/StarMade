/*     */ package it.unimi.dsi.fastutil.io;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class FastByteArrayOutputStream extends MeasurableOutputStream
/*     */   implements RepositionableStream
/*     */ {
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*     */   public byte[] array;
/*     */   public int length;
/*     */   private int position;
/*     */ 
/*     */   public FastByteArrayOutputStream()
/*     */   {
/*  57 */     this(16);
/*     */   }
/*     */ 
/*     */   public FastByteArrayOutputStream(int initialCapacity)
/*     */   {
/*  65 */     this.array = new byte[initialCapacity];
/*     */   }
/*     */ 
/*     */   public FastByteArrayOutputStream(byte[] a)
/*     */   {
/*  73 */     this.array = a;
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/*  78 */     this.length = 0;
/*  79 */     this.position = 0;
/*     */   }
/*     */ 
/*     */   public void trim()
/*     */   {
/*  84 */     this.array = ByteArrays.trim(this.array, this.length);
/*     */   }
/*     */ 
/*     */   public void write(int b) {
/*  88 */     if (this.position == this.length) {
/*  89 */       this.length += 1;
/*  90 */       if (this.position == this.array.length) this.array = ByteArrays.grow(this.array, this.length);
/*     */     }
/*  92 */     this.array[(this.position++)] = ((byte)b);
/*     */   }
/*     */ 
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  96 */     ByteArrays.ensureOffsetLength(b, off, len);
/*  97 */     if (this.position + len > this.array.length) this.array = ByteArrays.grow(this.array, this.position + len, this.position);
/*  98 */     System.arraycopy(b, off, this.array, this.position, len);
/*  99 */     if (this.position + len > this.length) this.length = (this.position += len); 
/*     */   }
/*     */ 
/*     */   public void position(long newPosition)
/*     */   {
/* 103 */     if (this.position > 2147483647) throw new IllegalArgumentException("Position too large: " + newPosition);
/* 104 */     this.position = ((int)newPosition);
/*     */   }
/*     */ 
/*     */   public long position() {
/* 108 */     return this.position;
/*     */   }
/*     */ 
/*     */   public long length() throws IOException
/*     */   {
/* 113 */     return this.length;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastByteArrayOutputStream
 * JD-Core Version:    0.6.2
 */