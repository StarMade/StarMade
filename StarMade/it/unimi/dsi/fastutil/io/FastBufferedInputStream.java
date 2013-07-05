/*     */ package it.unimi.dsi.fastutil.io;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.EnumSet;
/*     */ 
/*     */ public class FastBufferedInputStream extends MeasurableInputStream
/*     */   implements RepositionableStream
/*     */ {
/*     */   public static final int DEFAULT_BUFFER_SIZE = 8192;
/* 101 */   public static final EnumSet<LineTerminator> ALL_TERMINATORS = EnumSet.allOf(LineTerminator.class);
/*     */   protected InputStream is;
/*     */   protected byte[] buffer;
/*     */   protected int pos;
/*     */   protected long readBytes;
/*     */   protected int avail;
/*     */   private FileChannel fileChannel;
/*     */   private RepositionableStream repositionableStream;
/*     */   private MeasurableStream measurableStream;
/*     */ 
/*     */   public FastBufferedInputStream(InputStream is, int bufSize)
/*     */   {
/* 136 */     if (bufSize <= 0) throw new IllegalArgumentException("Illegal buffer size: " + bufSize);
/* 137 */     this.is = is;
/* 138 */     this.buffer = new byte[bufSize];
/*     */ 
/* 140 */     if ((is instanceof RepositionableStream)) this.repositionableStream = ((RepositionableStream)is);
/* 141 */     if ((is instanceof MeasurableStream)) this.measurableStream = ((MeasurableStream)is);
/*     */ 
/* 143 */     if (this.repositionableStream == null)
/*     */       try
/*     */       {
/* 146 */         this.fileChannel = ((FileChannel)is.getClass().getMethod("getChannel", new Class[0]).invoke(is, new Object[0]));
/*     */       }
/*     */       catch (IllegalAccessException e) {
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
/*     */   public FastBufferedInputStream(InputStream is) {
/* 161 */     this(is, 8192);
/*     */   }
/*     */ 
/*     */   protected boolean noMoreCharacters()
/*     */     throws IOException
/*     */   {
/* 173 */     if (this.avail == 0) {
/* 174 */       this.avail = this.is.read(this.buffer);
/* 175 */       if (this.avail <= 0) {
/* 176 */         this.avail = 0;
/* 177 */         return true;
/*     */       }
/* 179 */       this.pos = 0;
/*     */     }
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/* 187 */     if (noMoreCharacters()) return -1;
/* 188 */     this.avail -= 1;
/* 189 */     this.readBytes += 1L;
/* 190 */     return this.buffer[(this.pos++)] & 0xFF;
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int offset, int length) throws IOException
/*     */   {
/* 195 */     if (length <= this.avail) {
/* 196 */       System.arraycopy(this.buffer, this.pos, b, offset, length);
/* 197 */       this.pos += length;
/* 198 */       this.avail -= length;
/* 199 */       this.readBytes += length;
/* 200 */       return length;
/*     */     }
/*     */ 
/* 203 */     int head = this.avail;
/*     */ 
/* 205 */     System.arraycopy(this.buffer, this.pos, b, offset, head);
/* 206 */     this.pos = (this.avail = 0);
/* 207 */     this.readBytes += head;
/*     */ 
/* 209 */     if (length > this.buffer.length)
/*     */     {
/* 211 */       int result = this.is.read(b, offset + head, length - head);
/* 212 */       if (result > 0) this.readBytes += result;
/* 213 */       return result < 0 ? head : head == 0 ? -1 : result + head;
/*     */     }
/*     */ 
/* 216 */     if (noMoreCharacters()) return head == 0 ? -1 : head;
/*     */ 
/* 218 */     int toRead = Math.min(length - head, this.avail);
/* 219 */     this.readBytes += toRead;
/* 220 */     System.arraycopy(this.buffer, 0, b, offset + head, toRead);
/* 221 */     this.pos = toRead;
/* 222 */     this.avail -= toRead;
/*     */ 
/* 225 */     return toRead + head;
/*     */   }
/*     */ 
/*     */   public int readLine(byte[] array)
/*     */     throws IOException
/*     */   {
/* 236 */     return readLine(array, 0, array.length, ALL_TERMINATORS);
/*     */   }
/*     */ 
/*     */   public int readLine(byte[] array, EnumSet<LineTerminator> terminators)
/*     */     throws IOException
/*     */   {
/* 249 */     return readLine(array, 0, array.length, terminators);
/*     */   }
/*     */ 
/*     */   public int readLine(byte[] array, int off, int len)
/*     */     throws IOException
/*     */   {
/* 261 */     return readLine(array, off, len, ALL_TERMINATORS);
/*     */   }
/*     */ 
/*     */   public int readLine(byte[] array, int off, int len, EnumSet<LineTerminator> terminators)
/*     */     throws IOException
/*     */   {
/* 312 */     ByteArrays.ensureOffsetLength(array, off, len);
/* 313 */     if (len == 0) return 0;
/* 314 */     if (noMoreCharacters()) return -1;
/* 315 */     int k = 0; int remaining = len; int read = 0;
/*     */     do while (true) {
/* 317 */         for (int i = 0; (i < this.avail) && (i < remaining) && ((k = this.buffer[(this.pos + i)]) != 10) && (k != 13); i++);
/* 318 */         System.arraycopy(this.buffer, this.pos, array, off + read, i);
/* 319 */         this.pos += i;
/* 320 */         this.avail -= i;
/* 321 */         read += i;
/* 322 */         remaining -= i;
/* 323 */         if (remaining == 0) {
/* 324 */           this.readBytes += read;
/* 325 */           return read;
/*     */         }
/*     */ 
/* 328 */         if (this.avail <= 0) break;
/* 329 */         if (k == 10) {
/* 330 */           this.pos += 1;
/* 331 */           this.avail -= 1;
/* 332 */           if (terminators.contains(LineTerminator.LF)) {
/* 333 */             this.readBytes += read + 1;
/* 334 */             return read;
/*     */           }
/*     */ 
/* 337 */           array[(off + read++)] = 10;
/* 338 */           remaining--;
/*     */         }
/* 341 */         else if (k == 13) {
/* 342 */           this.pos += 1;
/* 343 */           this.avail -= 1;
/*     */ 
/* 345 */           if (terminators.contains(LineTerminator.CR_LF)) {
/* 346 */             if (this.avail > 0) {
/* 347 */               if (this.buffer[this.pos] == 10) {
/* 348 */                 this.pos += 1;
/* 349 */                 this.avail -= 1;
/* 350 */                 this.readBytes += read + 2;
/* 351 */                 return read;
/*     */               }
/*     */             }
/*     */             else {
/* 355 */               if (noMoreCharacters())
/*     */               {
/* 358 */                 if (!terminators.contains(LineTerminator.CR)) {
/* 359 */                   array[(off + read++)] = 13;
/* 360 */                   remaining--;
/* 361 */                   this.readBytes += read;
/*     */                 } else {
/* 363 */                   this.readBytes += read + 1;
/*     */                 }
/* 365 */                 return read;
/*     */               }
/* 367 */               if (this.buffer[0] == 10)
/*     */               {
/* 369 */                 this.pos += 1;
/* 370 */                 this.avail -= 1;
/* 371 */                 this.readBytes += read + 2;
/* 372 */                 return read;
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/* 377 */           if (terminators.contains(LineTerminator.CR)) {
/* 378 */             this.readBytes += read + 1;
/* 379 */             return read;
/*     */           }
/*     */ 
/* 382 */           array[(off + read++)] = 13;
/* 383 */           remaining--;
/*     */         }
/*     */       }
/* 386 */     while (!noMoreCharacters());
/* 387 */     this.readBytes += read;
/* 388 */     return read;
/*     */   }
/*     */ 
/*     */   public void position(long newPosition)
/*     */     throws IOException
/*     */   {
/* 397 */     long position = this.readBytes;
/*     */ 
/* 404 */     if ((newPosition <= position + this.avail) && (newPosition >= position - this.pos)) {
/* 405 */       this.pos = ((int)(this.pos + (newPosition - position)));
/* 406 */       this.avail = ((int)(this.avail - (newPosition - position)));
/* 407 */       this.readBytes = newPosition;
/* 408 */       return;
/*     */     }
/*     */ 
/* 411 */     if (this.repositionableStream != null) this.repositionableStream.position(newPosition);
/* 412 */     else if (this.fileChannel != null) this.fileChannel.position(newPosition); else
/* 413 */       throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
/* 414 */     this.readBytes = newPosition;
/*     */ 
/* 416 */     this.avail = (this.pos = 0);
/*     */   }
/*     */ 
/*     */   public long position() throws IOException {
/* 420 */     return this.readBytes;
/*     */   }
/*     */ 
/*     */   public long length()
/*     */     throws IOException
/*     */   {
/* 431 */     if (this.measurableStream != null) return this.measurableStream.length();
/* 432 */     if (this.fileChannel != null) return this.fileChannel.size();
/* 433 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   private long skipByReading(long n)
/*     */     throws IOException
/*     */   {
/*     */     int len;
/* 447 */     for (long toSkip = n; 
/* 449 */       toSkip > 0L; 
/* 451 */       toSkip -= len)
/*     */     {
/* 450 */       len = this.is.read(this.buffer, 0, (int)Math.min(this.buffer.length, toSkip));
/* 451 */       if (len <= 0) {
/*     */         break;
/*     */       }
/*     */     }
/* 455 */     return n - toSkip;
/*     */   }
/*     */ 
/*     */   public long skip(long n)
/*     */     throws IOException
/*     */   {
/* 475 */     if (n <= this.avail) {
/* 476 */       int m = (int)n;
/* 477 */       this.pos += m;
/* 478 */       this.avail -= m;
/* 479 */       this.readBytes += n;
/* 480 */       return n;
/*     */     }
/*     */ 
/* 483 */     long toSkip = n - this.avail; long result = 0L;
/* 484 */     this.avail = 0;
/*     */ 
/* 486 */     while (toSkip != 0L) { if ((result = this.is == System.in ? skipByReading(toSkip) : this.is.skip(toSkip)) >= toSkip) break;
/* 487 */       if (result == 0L) {
/* 488 */         if (this.is.read() == -1) break;
/* 489 */         toSkip -= 1L;
/*     */       } else {
/* 491 */         toSkip -= result;
/*     */       }
/*     */     }
/* 494 */     long t = n - (toSkip - result);
/* 495 */     this.readBytes += t;
/* 496 */     return t;
/*     */   }
/*     */ 
/*     */   public int available() throws IOException
/*     */   {
/* 501 */     return (int)Math.min(this.is.available() + this.avail, 2147483647L);
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 505 */     if (this.is == null) return;
/* 506 */     if (this.is != System.in) this.is.close();
/* 507 */     this.is = null;
/* 508 */     this.buffer = null;
/*     */   }
/*     */ 
/*     */   public void flush()
/*     */   {
/* 522 */     if (this.is == null) return;
/* 523 */     this.readBytes += this.avail;
/* 524 */     this.avail = (this.pos = 0);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public void reset()
/*     */   {
/* 536 */     flush();
/*     */   }
/*     */ 
/*     */   public static enum LineTerminator
/*     */   {
/*  93 */     CR, 
/*     */ 
/*  95 */     LF, 
/*     */ 
/*  97 */     CR_LF;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastBufferedInputStream
 * JD-Core Version:    0.6.2
 */