/*     */ package it.unimi.dsi.fastutil.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.channels.FileChannel;
/*     */ 
/*     */ public class FastBufferedOutputStream extends MeasurableOutputStream
/*     */   implements RepositionableStream
/*     */ {
/*     */   private static final boolean ASSERTS = false;
/*     */   public static final int DEFAULT_BUFFER_SIZE = 8192;
/*     */   protected byte[] buffer;
/*     */   protected int pos;
/*     */   protected int avail;
/*     */   protected OutputStream os;
/*     */   private FileChannel fileChannel;
/*     */   private RepositionableStream repositionableStream;
/*     */   private MeasurableStream measurableStream;
/*     */ 
/*     */   public FastBufferedOutputStream(OutputStream os, int bufferSize)
/*     */   {
/*  89 */     this.os = os;
/*  90 */     this.buffer = new byte[bufferSize];
/*  91 */     this.avail = bufferSize;
/*     */ 
/*  93 */     if ((os instanceof RepositionableStream)) this.repositionableStream = ((RepositionableStream)os);
/*  94 */     if ((os instanceof MeasurableStream)) this.measurableStream = ((MeasurableStream)os);
/*     */ 
/*  96 */     if (this.repositionableStream == null)
/*     */       try
/*     */       {
/*  99 */         this.fileChannel = ((FileChannel)os.getClass().getMethod("getChannel", new Class[0]).invoke(os, new Object[0]));
/*     */       }
/*     */       catch (IllegalAccessException e)
/*     */       {
/*     */       }
/*     */       catch (IllegalArgumentException e) {
/*     */       }
/*     */       catch (NoSuchMethodException e) {
/*     */       }
/*     */       catch (InvocationTargetException e) {
/*     */       }
/*     */       catch (ClassCastException e) {
/*     */       }
/*     */   }
/*     */ 
/*     */   public FastBufferedOutputStream(OutputStream os) {
/* 115 */     this(os, 8192);
/*     */   }
/*     */ 
/*     */   private void dumpBuffer(boolean ifFull) throws IOException {
/* 119 */     if ((!ifFull) || (this.avail == 0)) {
/* 120 */       this.os.write(this.buffer, 0, this.pos);
/* 121 */       this.pos = 0;
/* 122 */       this.avail = this.buffer.length;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(int b) throws IOException
/*     */   {
/* 128 */     this.avail -= 1;
/* 129 */     this.buffer[(this.pos++)] = ((byte)b);
/* 130 */     dumpBuffer(true);
/*     */   }
/*     */ 
/*     */   public void write(byte[] b, int offset, int length) throws IOException
/*     */   {
/* 135 */     if (length >= this.buffer.length) {
/* 136 */       dumpBuffer(false);
/* 137 */       this.os.write(b, offset, length);
/* 138 */       return;
/*     */     }
/*     */ 
/* 141 */     if (length <= this.avail)
/*     */     {
/* 143 */       System.arraycopy(b, offset, this.buffer, this.pos, length);
/* 144 */       this.pos += length;
/* 145 */       this.avail -= length;
/* 146 */       dumpBuffer(true);
/* 147 */       return;
/*     */     }
/*     */ 
/* 150 */     dumpBuffer(false);
/* 151 */     System.arraycopy(b, offset, this.buffer, 0, length);
/* 152 */     this.pos = length;
/* 153 */     this.avail -= length;
/*     */   }
/*     */ 
/*     */   public void flush() throws IOException {
/* 157 */     dumpBuffer(false);
/* 158 */     this.os.flush();
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 162 */     if (this.os == null) return;
/* 163 */     flush();
/* 164 */     if (this.os != System.out) this.os.close();
/* 165 */     this.os = null;
/* 166 */     this.buffer = null;
/*     */   }
/*     */ 
/*     */   public long position() throws IOException {
/* 170 */     if (this.repositionableStream != null) return this.repositionableStream.position() + this.pos;
/* 171 */     if (this.measurableStream != null) return this.measurableStream.position() + this.pos;
/* 172 */     if (this.fileChannel != null) return this.fileChannel.position() + this.pos;
/* 173 */     throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the MeasurableStream or RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
/*     */   }
/*     */ 
/*     */   public void position(long newPosition)
/*     */     throws IOException
/*     */   {
/* 182 */     flush();
/* 183 */     if (this.repositionableStream != null) this.repositionableStream.position(newPosition);
/* 184 */     else if (this.fileChannel != null) this.fileChannel.position(newPosition); else
/* 185 */       throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
/*     */   }
/*     */ 
/*     */   public long length()
/*     */     throws IOException
/*     */   {
/* 198 */     flush();
/* 199 */     if (this.measurableStream != null) return this.measurableStream.length();
/* 200 */     if (this.fileChannel != null) return this.fileChannel.size();
/* 201 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastBufferedOutputStream
 * JD-Core Version:    0.6.2
 */