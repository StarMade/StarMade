/*     */ package it.unimi.dsi.fastutil.io;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class FastMultiByteArrayInputStream extends MeasurableInputStream
/*     */   implements RepositionableStream
/*     */ {
/*     */   public static final int SLICE_BITS = 30;
/*     */   public static final int SLICE_SIZE = 1073741824;
/*     */   public static final int SLICE_MASK = 1073741823;
/*     */   public byte[][] array;
/*     */   public long length;
/*     */   private long position;
/*     */   private long mark;
/*     */ 
/*     */   public FastMultiByteArrayInputStream(MeasurableInputStream is)
/*     */     throws IOException
/*     */   {
/*  68 */     this(is, is.length());
/*     */   }
/*     */ 
/*     */   public FastMultiByteArrayInputStream(InputStream is, long size)
/*     */     throws IOException
/*     */   {
/*  78 */     this.length = size;
/*  79 */     this.array = new byte[(int)((size + 1073741824L - 1L) / 1073741824L)][];
/*     */ 
/*  81 */     for (int i = 0; i < this.array.length; i++) {
/*  82 */       this.array[i] = new byte[size >= 1073741824L ? 1073741824 : (int)size];
/*  83 */       if (is.read(this.array[i]) != this.array[i].length) throw new EOFException();
/*  84 */       size -= this.array[i].length;
/*     */     }
/*     */   }
/*     */ 
/*     */   public FastMultiByteArrayInputStream(FastMultiByteArrayInputStream is)
/*     */   {
/*  93 */     this.array = is.array;
/*  94 */     this.length = is.length;
/*     */   }
/*     */ 
/*     */   public FastMultiByteArrayInputStream(byte[] array)
/*     */   {
/* 103 */     this.array = new byte[1][];
/* 104 */     this.array[0] = array;
/* 105 */     this.length = array.length;
/*     */   }
/*     */ 
/*     */   public boolean markSupported()
/*     */   {
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   public void reset() {
/* 115 */     this.position = this.mark;
/*     */   }
/*     */ 
/*     */   public void close() {
/*     */   }
/*     */ 
/*     */   public void mark(int dummy) {
/* 122 */     this.mark = this.position;
/*     */   }
/*     */ 
/*     */   public int available()
/*     */   {
/* 134 */     if (this.length - this.position > 2147483647L) return 2147483647;
/* 135 */     return (int)(this.length - this.position);
/*     */   }
/*     */ 
/*     */   public long skip(long n) {
/* 139 */     if (n <= this.length - this.position) {
/* 140 */       this.position += n;
/*     */ 
/* 142 */       return n;
/*     */     }
/* 144 */     n = this.length - this.position;
/* 145 */     this.position = this.length;
/* 146 */     return n;
/*     */   }
/*     */ 
/*     */   public int read() {
/* 150 */     if (this.length == this.position) return -1;
/* 151 */     return this.array[((int)(this.position >>> 30))][((int)(this.position++ & 0x3FFFFFFF))] & 0xFF;
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int offset, int length) {
/* 155 */     if (this.length == this.position) return length == 0 ? 0 : -1;
/* 156 */     int n = (int)Math.min(length, this.length - this.position); int m = n;
/*     */     do
/*     */     {
/* 159 */       int res = Math.min(n, this.array[((int)(this.position >>> 30))].length - (int)(this.position & 0x3FFFFFFF));
/* 160 */       System.arraycopy(this.array[((int)(this.position >>> 30))], (int)(this.position & 0x3FFFFFFF), b, offset, res);
/*     */ 
/* 163 */       n -= res;
/* 164 */       offset += res;
/* 165 */       this.position += res;
/*     */     }
/* 167 */     while (n > 0);
/*     */ 
/* 169 */     return m;
/*     */   }
/*     */ 
/*     */   public long position() {
/* 173 */     return this.position;
/*     */   }
/*     */ 
/*     */   public void position(long newPosition) {
/* 177 */     this.position = Math.min(newPosition, this.length);
/*     */   }
/*     */ 
/*     */   public long length() throws IOException
/*     */   {
/* 182 */     return this.length;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastMultiByteArrayInputStream
 * JD-Core Version:    0.6.2
 */