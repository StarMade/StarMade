/*     */ package paulscode.sound.codecs;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.ShortBuffer;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.UnsupportedAudioFileException;
/*     */ import paulscode.sound.ICodec;
/*     */ import paulscode.sound.SoundBuffer;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ 
/*     */ public class CodecWav
/*     */   implements ICodec
/*     */ {
/*     */   private static final boolean GET = false;
/*     */   private static final boolean SET = true;
/*     */   private static final boolean XXX = false;
/*  76 */   private boolean endOfStream = false;
/*     */ 
/*  81 */   private boolean initialized = false;
/*     */ 
/*  86 */   private AudioInputStream myAudioInputStream = null;
/*     */   private SoundSystemLogger logger;
/*     */ 
/*     */   public void reverseByteOrder(boolean b)
/*     */   {
/*     */   }
/*     */ 
/*     */   public CodecWav()
/*     */   {
/* 105 */     this.logger = SoundSystemConfig.getLogger();
/*     */   }
/*     */ 
/*     */   public boolean initialize(URL url)
/*     */   {
/* 116 */     initialized(true, false);
/* 117 */     cleanup();
/*     */ 
/* 119 */     if (url == null)
/*     */     {
/* 121 */       errorMessage("url null in method 'initialize'");
/* 122 */       cleanup();
/* 123 */       return false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 128 */       this.myAudioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(url.openStream()));
/*     */     }
/*     */     catch (UnsupportedAudioFileException uafe)
/*     */     {
/* 133 */       errorMessage("Unsupported audio format in method 'initialize'");
/* 134 */       printStackTrace(uafe);
/* 135 */       return false;
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 139 */       errorMessage("Error setting up audio input stream in method 'initialize'");
/*     */ 
/* 141 */       printStackTrace(ioe);
/* 142 */       return false;
/*     */     }
/*     */ 
/* 145 */     endOfStream(true, false);
/* 146 */     initialized(true, true);
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean initialized()
/*     */   {
/* 156 */     return initialized(false, false);
/*     */   }
/*     */ 
/*     */   public SoundBuffer read()
/*     */   {
/* 167 */     if (this.myAudioInputStream == null) {
/* 168 */       return null;
/*     */     }
/*     */ 
/* 171 */     AudioFormat audioFormat = this.myAudioInputStream.getFormat();
/*     */ 
/* 174 */     if (audioFormat == null)
/*     */     {
/* 176 */       errorMessage("Audio Format null in method 'read'");
/* 177 */       return null;
/*     */     }
/*     */ 
/* 181 */     int bytesRead = 0; int cnt = 0;
/*     */ 
/* 184 */     byte[] streamBuffer = new byte[SoundSystemConfig.getStreamingBufferSize()];
/*     */     try
/*     */     {
/* 191 */       while ((!endOfStream(false, false)) && (bytesRead < streamBuffer.length))
/*     */       {
/* 193 */         if ((cnt = this.myAudioInputStream.read(streamBuffer, bytesRead, streamBuffer.length - bytesRead)) <= 0)
/*     */         {
/* 197 */           endOfStream(true, true);
/* 198 */           break;
/*     */         }
/*     */ 
/* 201 */         bytesRead += cnt;
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 207 */       endOfStream(true, true);
/* 208 */       return null;
/*     */     }
/*     */ 
/* 212 */     if (bytesRead <= 0) {
/* 213 */       return null;
/*     */     }
/*     */ 
/* 216 */     if (bytesRead < streamBuffer.length) {
/* 217 */       streamBuffer = trimArray(streamBuffer, bytesRead);
/*     */     }
/*     */ 
/* 221 */     byte[] data = convertAudioBytes(streamBuffer, audioFormat.getSampleSizeInBits() == 16);
/*     */ 
/* 225 */     SoundBuffer buffer = new SoundBuffer(data, audioFormat);
/*     */ 
/* 228 */     return buffer;
/*     */   }
/*     */ 
/*     */   public SoundBuffer readAll()
/*     */   {
/* 241 */     if (this.myAudioInputStream == null)
/*     */     {
/* 243 */       errorMessage("Audio input stream null in method 'readAll'");
/* 244 */       return null;
/*     */     }
/* 246 */     AudioFormat myAudioFormat = this.myAudioInputStream.getFormat();
/*     */ 
/* 249 */     if (myAudioFormat == null)
/*     */     {
/* 251 */       errorMessage("Audio Format null in method 'readAll'");
/* 252 */       return null;
/*     */     }
/*     */ 
/* 256 */     byte[] fullBuffer = null;
/*     */ 
/* 259 */     int fileSize = myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * myAudioFormat.getSampleSizeInBits() / 8;
/*     */ 
/* 262 */     if (fileSize > 0)
/*     */     {
/* 265 */       fullBuffer = new byte[myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * myAudioFormat.getSampleSizeInBits() / 8];
/*     */ 
/* 269 */       int read = 0; int total = 0;
/*     */       try
/*     */       {
/* 276 */         while (((read = this.myAudioInputStream.read(fullBuffer, total, fullBuffer.length - total)) != -1) && (total < fullBuffer.length))
/*     */         {
/* 278 */           total += read;
/*     */         }
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/* 283 */         errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
/*     */ 
/* 285 */         printStackTrace(e);
/* 286 */         return null;
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 294 */       int totalBytes = 0; int bytesRead = 0; int cnt = 0;
/* 295 */       byte[] smallBuffer = null;
/*     */ 
/* 298 */       smallBuffer = new byte[SoundSystemConfig.getFileChunkSize()];
/*     */ 
/* 301 */       while ((!endOfStream(false, false)) && (totalBytes < SoundSystemConfig.getMaxFileSize()))
/*     */       {
/* 304 */         bytesRead = 0;
/* 305 */         cnt = 0;
/*     */         try
/*     */         {
/* 310 */           while (bytesRead < smallBuffer.length)
/*     */           {
/* 312 */             if ((cnt = this.myAudioInputStream.read(smallBuffer, bytesRead, smallBuffer.length - bytesRead)) <= 0)
/*     */             {
/* 316 */               endOfStream(true, true);
/* 317 */               break;
/*     */             }
/* 319 */             bytesRead += cnt;
/*     */           }
/*     */         }
/*     */         catch (IOException e)
/*     */         {
/* 324 */           errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
/*     */ 
/* 326 */           printStackTrace(e);
/* 327 */           return null;
/*     */         }
/*     */ 
/* 331 */         totalBytes += bytesRead;
/*     */ 
/* 334 */         fullBuffer = appendByteArrays(fullBuffer, smallBuffer, bytesRead);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 340 */     byte[] data = convertAudioBytes(fullBuffer, myAudioFormat.getSampleSizeInBits() == 16);
/*     */ 
/* 344 */     SoundBuffer soundBuffer = new SoundBuffer(data, myAudioFormat);
/*     */     try
/*     */     {
/* 349 */       this.myAudioInputStream.close();
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/*     */     }
/*     */ 
/* 355 */     return soundBuffer;
/*     */   }
/*     */ 
/*     */   public boolean endOfStream()
/*     */   {
/* 364 */     return endOfStream(false, false);
/*     */   }
/*     */ 
/*     */   public void cleanup()
/*     */   {
/* 372 */     if (this.myAudioInputStream != null)
/*     */       try
/*     */       {
/* 375 */         this.myAudioInputStream.close();
/*     */       }
/*     */       catch (Exception e) {
/*     */       }
/* 379 */     this.myAudioInputStream = null;
/*     */   }
/*     */ 
/*     */   public AudioFormat getAudioFormat()
/*     */   {
/* 389 */     if (this.myAudioInputStream == null)
/* 390 */       return null;
/* 391 */     return this.myAudioInputStream.getFormat();
/*     */   }
/*     */ 
/*     */   private synchronized boolean initialized(boolean action, boolean value)
/*     */   {
/* 402 */     if (action == true)
/* 403 */       this.initialized = value;
/* 404 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   private synchronized boolean endOfStream(boolean action, boolean value)
/*     */   {
/* 415 */     if (action == true)
/* 416 */       this.endOfStream = value;
/* 417 */     return this.endOfStream;
/*     */   }
/*     */ 
/*     */   private static byte[] trimArray(byte[] array, int maxLength)
/*     */   {
/* 429 */     byte[] trimmedArray = null;
/* 430 */     if ((array != null) && (array.length > maxLength))
/*     */     {
/* 432 */       trimmedArray = new byte[maxLength];
/* 433 */       System.arraycopy(array, 0, trimmedArray, 0, maxLength);
/*     */     }
/* 435 */     return trimmedArray;
/*     */   }
/*     */ 
/*     */   private static byte[] convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data)
/*     */   {
/* 447 */     ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 448 */     dest.order(ByteOrder.nativeOrder());
/* 449 */     ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 450 */     src.order(ByteOrder.LITTLE_ENDIAN);
/* 451 */     if (two_bytes_data)
/*     */     {
/* 453 */       ShortBuffer dest_short = dest.asShortBuffer();
/* 454 */       ShortBuffer src_short = src.asShortBuffer();
/* 455 */       while (src_short.hasRemaining())
/*     */       {
/* 457 */         dest_short.put(src_short.get());
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 462 */       while (src.hasRemaining())
/*     */       {
/* 464 */         dest.put(src.get());
/*     */       }
/*     */     }
/* 467 */     dest.rewind();
/*     */ 
/* 469 */     if (!dest.hasArray())
/*     */     {
/* 471 */       byte[] arrayBackedBuffer = new byte[dest.capacity()];
/* 472 */       dest.get(arrayBackedBuffer);
/* 473 */       dest.clear();
/*     */ 
/* 475 */       return arrayBackedBuffer;
/*     */     }
/*     */ 
/* 478 */     return dest.array();
/*     */   }
/*     */ 
/*     */   private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int length)
/*     */   {
/* 493 */     if ((arrayOne == null) && (arrayTwo == null))
/*     */     {
/* 496 */       return null;
/*     */     }
/*     */     byte[] newArray;
/* 498 */     if (arrayOne == null)
/*     */     {
/* 501 */       byte[] newArray = new byte[length];
/*     */ 
/* 503 */       System.arraycopy(arrayTwo, 0, newArray, 0, length);
/* 504 */       arrayTwo = null;
/*     */     }
/* 506 */     else if (arrayTwo == null)
/*     */     {
/* 509 */       byte[] newArray = new byte[arrayOne.length];
/*     */ 
/* 511 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 512 */       arrayOne = null;
/*     */     }
/*     */     else
/*     */     {
/* 517 */       newArray = new byte[arrayOne.length + length];
/* 518 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/*     */ 
/* 520 */       System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, length);
/*     */ 
/* 522 */       arrayOne = null;
/* 523 */       arrayTwo = null;
/*     */     }
/*     */ 
/* 526 */     return newArray;
/*     */   }
/*     */ 
/*     */   private void errorMessage(String message)
/*     */   {
/* 535 */     this.logger.errorMessage("CodecWav", message, 0);
/*     */   }
/*     */ 
/*     */   private void printStackTrace(Exception e)
/*     */   {
/* 544 */     this.logger.printStackTrace(e, 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.codecs.CodecWav
 * JD-Core Version:    0.6.2
 */