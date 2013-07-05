/*     */ package paulscode.sound.codecs;
/*     */ 
/*     */ import de.jarnbjo.ogg.CachedUrlStream;
/*     */ import de.jarnbjo.ogg.EndOfOggStreamException;
/*     */ import de.jarnbjo.ogg.LogicalOggStream;
/*     */ import de.jarnbjo.vorbis.IdentificationHeader;
/*     */ import de.jarnbjo.vorbis.VorbisStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioFormat.Encoding;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import paulscode.sound.ICodec;
/*     */ import paulscode.sound.SoundBuffer;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ 
/*     */ public class CodecJOgg
/*     */   implements ICodec
/*     */ {
/*     */   private static final boolean GET = false;
/*     */   private static final boolean SET = true;
/*     */   private static final boolean XXX = false;
/*  98 */   private boolean endOfStream = false;
/*     */ 
/* 103 */   private boolean initialized = false;
/*     */ 
/* 109 */   private boolean reverseBytes = false;
/*     */ 
/* 114 */   private CachedUrlStream cachedUrlStream = null;
/*     */ 
/* 119 */   private LogicalOggStream myLogicalOggStream = null;
/*     */ 
/* 124 */   private VorbisStream myVorbisStream = null;
/*     */ 
/* 129 */   private OggInputStream myOggInputStream = null;
/*     */ 
/* 134 */   private IdentificationHeader myIdentificationHeader = null;
/*     */ 
/* 139 */   private AudioFormat myAudioFormat = null;
/*     */ 
/* 144 */   private AudioInputStream myAudioInputStream = null;
/*     */   private SoundSystemLogger logger;
/*     */ 
/*     */   public CodecJOgg()
/*     */   {
/* 156 */     this.logger = SoundSystemConfig.getLogger();
/*     */   }
/*     */ 
/*     */   public void reverseByteOrder(boolean b)
/*     */   {
/* 170 */     this.reverseBytes = b;
/*     */   }
/*     */ 
/*     */   public boolean initialize(URL url)
/*     */   {
/* 181 */     initialized(true, false);
/* 182 */     cleanup();
/*     */ 
/* 184 */     if (url == null)
/*     */     {
/* 186 */       errorMessage("url null in method 'initialize'");
/* 187 */       cleanup();
/* 188 */       return false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 194 */       this.cachedUrlStream = new CachedUrlStream(url);
/* 195 */       this.myLogicalOggStream = ((LogicalOggStream)this.cachedUrlStream.getLogicalStreams().iterator().next());
/*     */ 
/* 197 */       this.myVorbisStream = new VorbisStream(this.myLogicalOggStream);
/* 198 */       this.myOggInputStream = new OggInputStream(this.myVorbisStream);
/*     */ 
/* 201 */       this.myIdentificationHeader = this.myVorbisStream.getIdentificationHeader();
/*     */ 
/* 205 */       this.myAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.myIdentificationHeader.getSampleRate(), 16, this.myIdentificationHeader.getChannels(), this.myIdentificationHeader.getChannels() * 2, this.myIdentificationHeader.getSampleRate(), true);
/*     */ 
/* 215 */       this.myAudioInputStream = new AudioInputStream(this.myOggInputStream, this.myAudioFormat, -1L);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 220 */       errorMessage("Unable to set up input streams in method 'initialize'");
/*     */ 
/* 222 */       printStackTrace(e);
/* 223 */       cleanup();
/* 224 */       return false;
/*     */     }
/* 226 */     if (this.myAudioInputStream == null)
/*     */     {
/* 228 */       errorMessage("Unable to set up audio input stream in method 'initialize'");
/*     */ 
/* 230 */       cleanup();
/* 231 */       return false;
/*     */     }
/*     */ 
/* 234 */     endOfStream(true, false);
/* 235 */     initialized(true, true);
/* 236 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean initialized()
/*     */   {
/* 245 */     return initialized(false, false);
/*     */   }
/*     */ 
/*     */   public SoundBuffer read()
/*     */   {
/* 256 */     if (this.myAudioInputStream == null)
/*     */     {
/* 258 */       endOfStream(true, true);
/* 259 */       return null;
/*     */     }
/*     */ 
/* 263 */     AudioFormat audioFormat = this.myAudioInputStream.getFormat();
/*     */ 
/* 266 */     if (audioFormat == null)
/*     */     {
/* 268 */       errorMessage("Audio Format null in method 'read'");
/* 269 */       endOfStream(true, true);
/* 270 */       return null;
/*     */     }
/*     */ 
/* 274 */     int bytesRead = 0; int cnt = 0;
/*     */ 
/* 277 */     byte[] streamBuffer = new byte[SoundSystemConfig.getStreamingBufferSize()];
/*     */     try
/*     */     {
/* 284 */       while ((!endOfStream(false, false)) && (bytesRead < streamBuffer.length))
/*     */       {
/* 286 */         if ((cnt = this.myAudioInputStream.read(streamBuffer, bytesRead, streamBuffer.length - bytesRead)) <= 0)
/*     */         {
/* 290 */           endOfStream(true, true);
/* 291 */           break;
/*     */         }
/*     */ 
/* 294 */         bytesRead += cnt;
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 305 */       endOfStream(true, true);
/* 306 */       return null;
/*     */     }
/*     */ 
/* 310 */     if (bytesRead <= 0)
/*     */     {
/* 312 */       endOfStream(true, true);
/* 313 */       return null;
/*     */     }
/*     */ 
/* 317 */     if (this.reverseBytes) {
/* 318 */       reverseBytes(streamBuffer, 0, bytesRead);
/*     */     }
/*     */ 
/* 321 */     if (bytesRead < streamBuffer.length) {
/* 322 */       streamBuffer = trimArray(streamBuffer, bytesRead);
/*     */     }
/*     */ 
/* 326 */     byte[] data = convertAudioBytes(streamBuffer, audioFormat.getSampleSizeInBits() == 16);
/*     */ 
/* 330 */     SoundBuffer buffer = new SoundBuffer(data, audioFormat);
/*     */ 
/* 333 */     return buffer;
/*     */   }
/*     */ 
/*     */   public SoundBuffer readAll()
/*     */   {
/* 346 */     if (this.myAudioFormat == null)
/*     */     {
/* 348 */       errorMessage("Audio Format null in method 'readAll'");
/* 349 */       return null;
/*     */     }
/*     */ 
/* 353 */     byte[] fullBuffer = null;
/*     */ 
/* 356 */     int fileSize = this.myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * this.myAudioFormat.getSampleSizeInBits() / 8;
/*     */ 
/* 359 */     if (fileSize > 0)
/*     */     {
/* 362 */       fullBuffer = new byte[this.myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * this.myAudioFormat.getSampleSizeInBits() / 8];
/*     */ 
/* 366 */       int read = 0; int total = 0;
/*     */       try
/*     */       {
/* 373 */         while (((read = this.myAudioInputStream.read(fullBuffer, total, fullBuffer.length - total)) != -1) && (total < fullBuffer.length))
/*     */         {
/* 375 */           total += read;
/*     */         }
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/* 380 */         errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
/*     */ 
/* 382 */         printStackTrace(e);
/* 383 */         return null;
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 391 */       int totalBytes = 0; int bytesRead = 0; int cnt = 0;
/* 392 */       byte[] smallBuffer = null;
/*     */ 
/* 395 */       smallBuffer = new byte[SoundSystemConfig.getFileChunkSize()];
/*     */ 
/* 398 */       while ((!endOfStream(false, false)) && (totalBytes < SoundSystemConfig.getMaxFileSize()))
/*     */       {
/* 401 */         bytesRead = 0;
/* 402 */         cnt = 0;
/*     */         try
/*     */         {
/* 407 */           while (bytesRead < smallBuffer.length)
/*     */           {
/* 409 */             if ((cnt = this.myAudioInputStream.read(smallBuffer, bytesRead, smallBuffer.length - bytesRead)) <= 0)
/*     */             {
/* 413 */               endOfStream(true, true);
/* 414 */               break;
/*     */             }
/* 416 */             bytesRead += cnt;
/*     */           }
/*     */         }
/*     */         catch (IOException e)
/*     */         {
/* 421 */           errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
/*     */ 
/* 423 */           printStackTrace(e);
/* 424 */           return null;
/*     */         }
/*     */ 
/* 428 */         if (this.reverseBytes) {
/* 429 */           reverseBytes(smallBuffer, 0, bytesRead);
/*     */         }
/*     */ 
/* 432 */         totalBytes += bytesRead;
/*     */ 
/* 435 */         fullBuffer = appendByteArrays(fullBuffer, smallBuffer, bytesRead);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 441 */     byte[] data = convertAudioBytes(fullBuffer, this.myAudioFormat.getSampleSizeInBits() == 16);
/*     */ 
/* 445 */     SoundBuffer soundBuffer = new SoundBuffer(data, this.myAudioFormat);
/*     */     try
/*     */     {
/* 450 */       this.myAudioInputStream.close();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*     */     }
/*     */ 
/* 456 */     return soundBuffer;
/*     */   }
/*     */ 
/*     */   public boolean endOfStream()
/*     */   {
/* 465 */     return endOfStream(false, false);
/*     */   }
/*     */ 
/*     */   public void cleanup()
/*     */   {
/* 473 */     if (this.myLogicalOggStream != null)
/*     */       try
/*     */       {
/* 476 */         this.myLogicalOggStream.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/* 480 */     if (this.myVorbisStream != null)
/*     */       try
/*     */       {
/* 483 */         this.myVorbisStream.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/* 487 */     if (this.myOggInputStream != null)
/*     */       try
/*     */       {
/* 490 */         this.myOggInputStream.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/* 494 */     if (this.myAudioInputStream != null)
/*     */       try
/*     */       {
/* 497 */         this.myAudioInputStream.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/* 501 */     this.myLogicalOggStream = null;
/* 502 */     this.myVorbisStream = null;
/* 503 */     this.myOggInputStream = null;
/* 504 */     this.myAudioInputStream = null;
/*     */   }
/*     */ 
/*     */   public AudioFormat getAudioFormat()
/*     */   {
/* 514 */     return this.myAudioFormat;
/*     */   }
/*     */ 
/*     */   private synchronized boolean initialized(boolean action, boolean value)
/*     */   {
/* 525 */     if (action == true)
/* 526 */       this.initialized = value;
/* 527 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   private synchronized boolean endOfStream(boolean action, boolean value)
/*     */   {
/* 538 */     if (action == true)
/* 539 */       this.endOfStream = value;
/* 540 */     return this.endOfStream;
/*     */   }
/*     */ 
/*     */   private static byte[] trimArray(byte[] array, int maxLength)
/*     */   {
/* 552 */     byte[] trimmedArray = null;
/* 553 */     if ((array != null) && (array.length > maxLength))
/*     */     {
/* 555 */       trimmedArray = new byte[maxLength];
/* 556 */       System.arraycopy(array, 0, trimmedArray, 0, maxLength);
/*     */     }
/*     */ 
/* 559 */     return trimmedArray;
/*     */   }
/*     */ 
/*     */   private static byte[] convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data)
/*     */   {
/* 571 */     ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 572 */     dest.order(ByteOrder.nativeOrder());
/* 573 */     ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 574 */     src.order(ByteOrder.LITTLE_ENDIAN);
/* 575 */     if (two_bytes_data)
/*     */     {
/* 577 */       ShortBuffer dest_short = dest.asShortBuffer();
/* 578 */       ShortBuffer src_short = src.asShortBuffer();
/* 579 */       while (src_short.hasRemaining())
/*     */       {
/* 581 */         dest_short.put(src_short.get());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 586 */       while (src.hasRemaining())
/*     */       {
/* 588 */         dest.put(src.get());
/*     */       }
/*     */     }
/* 591 */     dest.rewind();
/*     */ 
/* 593 */     if (!dest.hasArray())
/*     */     {
/* 595 */       byte[] arrayBackedBuffer = new byte[dest.capacity()];
/* 596 */       dest.get(arrayBackedBuffer);
/* 597 */       dest.clear();
/*     */ 
/* 599 */       return arrayBackedBuffer;
/*     */     }
/*     */ 
/* 602 */     return dest.array();
/*     */   }
/*     */ 
/*     */   private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int length)
/*     */   {
/* 617 */     if ((arrayOne == null) && (arrayTwo == null))
/*     */     {
/* 620 */       return null;
/*     */     }
/*     */     byte[] newArray;
/* 622 */     if (arrayOne == null)
/*     */     {
/* 625 */       byte[] newArray = new byte[length];
/*     */ 
/* 627 */       System.arraycopy(arrayTwo, 0, newArray, 0, length);
/* 628 */       arrayTwo = null;
/*     */     }
/* 630 */     else if (arrayTwo == null)
/*     */     {
/* 633 */       byte[] newArray = new byte[arrayOne.length];
/*     */ 
/* 635 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 636 */       arrayOne = null;
/*     */     }
/*     */     else
/*     */     {
/* 641 */       newArray = new byte[arrayOne.length + length];
/* 642 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/*     */ 
/* 644 */       System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, length);
/*     */ 
/* 646 */       arrayOne = null;
/* 647 */       arrayTwo = null;
/*     */     }
/*     */ 
/* 650 */     return newArray;
/*     */   }
/*     */ 
/*     */   public static void reverseBytes(byte[] buffer)
/*     */   {
/* 659 */     reverseBytes(buffer, 0, buffer.length);
/*     */   }
/*     */ 
/*     */   public static void reverseBytes(byte[] buffer, int offset, int size)
/*     */   {
/* 672 */     for (int i = offset; i < offset + size; i += 2)
/*     */     {
/* 674 */       byte b = buffer[i];
/* 675 */       buffer[i] = buffer[(i + 1)];
/* 676 */       buffer[(i + 1)] = b;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void errorMessage(String message)
/*     */   {
/* 751 */     this.logger.errorMessage("CodecJOgg", message, 0);
/*     */   }
/*     */ 
/*     */   private void printStackTrace(Exception e)
/*     */   {
/* 760 */     this.logger.printStackTrace(e, 1);
/*     */   }
/*     */ 
/*     */   private class OggInputStream extends InputStream
/*     */   {
/*     */     private VorbisStream myVorbisStream;
/*     */ 
/*     */     public OggInputStream(VorbisStream source)
/*     */     {
/* 697 */       this.myVorbisStream = source;
/*     */     }
/*     */ 
/*     */     public int read()
/*     */       throws IOException
/*     */     {
/* 707 */       return 0;
/*     */     }
/*     */ 
/*     */     public int read(byte[] buffer)
/*     */       throws IOException
/*     */     {
/* 719 */       return read(buffer, 0, buffer.length);
/*     */     }
/*     */ 
/*     */     public int read(byte[] buffer, int offset, int length)
/*     */       throws IOException
/*     */     {
/*     */       try
/*     */       {
/* 735 */         return this.myVorbisStream.readPcm(buffer, offset, length);
/*     */       }
/*     */       catch (EndOfOggStreamException e)
/*     */       {
/*     */       }
/* 740 */       return -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.codecs.CodecJOgg
 * JD-Core Version:    0.6.2
 */