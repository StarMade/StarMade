/*     */ package paulscode.sound.codecs;
/*     */ 
/*     */ import com.jcraft.jogg.Packet;
/*     */ import com.jcraft.jogg.Page;
/*     */ import com.jcraft.jogg.StreamState;
/*     */ import com.jcraft.jogg.SyncState;
/*     */ import com.jcraft.jorbis.Block;
/*     */ import com.jcraft.jorbis.Comment;
/*     */ import com.jcraft.jorbis.DspState;
/*     */ import com.jcraft.jorbis.Info;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.UnknownServiceException;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import paulscode.sound.ICodec;
/*     */ import paulscode.sound.SoundBuffer;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ 
/*     */ public class CodecJOrbis
/*     */   implements ICodec
/*     */ {
/*     */   private static final boolean GET = false;
/*     */   private static final boolean SET = true;
/*     */   private static final boolean XXX = false;
/*     */   private URL url;
/*  97 */   private URLConnection urlConnection = null;
/*     */   private InputStream inputStream;
/*     */   private AudioFormat audioFormat;
/* 112 */   private boolean endOfStream = false;
/*     */ 
/* 117 */   private boolean initialized = false;
/*     */ 
/* 122 */   private byte[] buffer = null;
/*     */   private int bufferSize;
/* 132 */   private int count = 0;
/*     */ 
/* 137 */   private int index = 0;
/*     */   private int convertedBufferSize;
/* 147 */   private byte[] convertedBuffer = null;
/*     */   private float[][][] pcmInfo;
/*     */   private int[] pcmIndex;
/* 162 */   private Packet joggPacket = new Packet();
/*     */ 
/* 166 */   private Page joggPage = new Page();
/*     */ 
/* 170 */   private StreamState joggStreamState = new StreamState();
/*     */ 
/* 174 */   private SyncState joggSyncState = new SyncState();
/*     */ 
/* 178 */   private DspState jorbisDspState = new DspState();
/*     */ 
/* 182 */   private Block jorbisBlock = new Block(this.jorbisDspState);
/*     */ 
/* 186 */   private Comment jorbisComment = new Comment();
/*     */ 
/* 190 */   private Info jorbisInfo = new Info();
/*     */   private SoundSystemLogger logger;
/*     */ 
/*     */   public CodecJOrbis()
/*     */   {
/* 202 */     this.logger = SoundSystemConfig.getLogger();
/*     */   }
/*     */ 
/*     */   public void reverseByteOrder(boolean b)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean initialize(URL url)
/*     */   {
/* 220 */     initialized(true, false);
/*     */ 
/* 222 */     if (this.joggStreamState != null)
/* 223 */       this.joggStreamState.clear();
/* 224 */     if (this.jorbisBlock != null)
/* 225 */       this.jorbisBlock.clear();
/* 226 */     if (this.jorbisDspState != null)
/* 227 */       this.jorbisDspState.clear();
/* 228 */     if (this.jorbisInfo != null)
/* 229 */       this.jorbisInfo.clear();
/* 230 */     if (this.joggSyncState != null) {
/* 231 */       this.joggSyncState.clear();
/*     */     }
/* 233 */     if (this.inputStream != null)
/*     */     {
/*     */       try
/*     */       {
/* 237 */         this.inputStream.close();
/*     */       }
/*     */       catch (IOException ioe)
/*     */       {
/*     */       }
/*     */     }
/* 243 */     this.url = url;
/*     */ 
/* 245 */     this.bufferSize = 8192;
/*     */ 
/* 247 */     this.buffer = null;
/* 248 */     this.count = 0;
/* 249 */     this.index = 0;
/*     */ 
/* 251 */     this.joggStreamState = new StreamState();
/* 252 */     this.jorbisBlock = new Block(this.jorbisDspState);
/* 253 */     this.jorbisDspState = new DspState();
/* 254 */     this.jorbisInfo = new Info();
/* 255 */     this.joggSyncState = new SyncState();
/*     */     try
/*     */     {
/* 259 */       this.urlConnection = url.openConnection();
/*     */     }
/*     */     catch (UnknownServiceException use)
/*     */     {
/* 263 */       errorMessage("Unable to create a UrlConnection in method 'initialize'.");
/*     */ 
/* 265 */       printStackTrace(use);
/* 266 */       cleanup();
/* 267 */       return false;
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 271 */       errorMessage("Unable to create a UrlConnection in method 'initialize'.");
/*     */ 
/* 273 */       printStackTrace(ioe);
/* 274 */       cleanup();
/* 275 */       return false;
/*     */     }
/* 277 */     if (this.urlConnection != null)
/*     */     {
/*     */       try
/*     */       {
/* 281 */         this.inputStream = this.urlConnection.getInputStream();
/*     */       }
/*     */       catch (IOException ioe)
/*     */       {
/* 285 */         errorMessage("Unable to acquire inputstream in method 'initialize'.");
/*     */ 
/* 287 */         printStackTrace(ioe);
/* 288 */         cleanup();
/* 289 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 293 */     endOfStream(true, false);
/*     */ 
/* 295 */     this.joggSyncState.init();
/* 296 */     this.joggSyncState.buffer(this.bufferSize);
/* 297 */     this.buffer = this.joggSyncState.data;
/*     */     try
/*     */     {
/* 301 */       if (!readHeader())
/*     */       {
/* 303 */         errorMessage("Error reading the header");
/* 304 */         return false;
/*     */       }
/*     */     }
/*     */     catch (IOException ioe)
/*     */     {
/* 309 */       errorMessage("Error reading the header");
/* 310 */       return false;
/*     */     }
/*     */ 
/* 313 */     this.convertedBufferSize = (this.bufferSize * 2);
/*     */ 
/* 315 */     this.jorbisDspState.synthesis_init(this.jorbisInfo);
/* 316 */     this.jorbisBlock.init(this.jorbisDspState);
/*     */ 
/* 318 */     int channels = this.jorbisInfo.channels;
/* 319 */     int rate = this.jorbisInfo.rate;
/*     */ 
/* 321 */     this.audioFormat = new AudioFormat(rate, 16, channels, true, false);
/*     */ 
/* 323 */     this.pcmInfo = new float[1][][];
/* 324 */     this.pcmIndex = new int[this.jorbisInfo.channels];
/*     */ 
/* 326 */     initialized(true, true);
/*     */ 
/* 328 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean initialized()
/*     */   {
/* 337 */     return initialized(false, false);
/*     */   }
/*     */ 
/*     */   public SoundBuffer read()
/*     */   {
/* 348 */     byte[] returnBuffer = null;
/*     */ 
/* 350 */     while ((!endOfStream(false, false)) && ((returnBuffer == null) || (returnBuffer.length < SoundSystemConfig.getStreamingBufferSize())))
/*     */     {
/* 353 */       if (returnBuffer == null)
/* 354 */         returnBuffer = readBytes();
/*     */       else {
/* 356 */         returnBuffer = appendByteArrays(returnBuffer, readBytes());
/*     */       }
/*     */     }
/* 359 */     if (returnBuffer == null) {
/* 360 */       return null;
/*     */     }
/* 362 */     return new SoundBuffer(returnBuffer, this.audioFormat);
/*     */   }
/*     */ 
/*     */   public SoundBuffer readAll()
/*     */   {
/* 374 */     byte[] returnBuffer = null;
/*     */ 
/* 376 */     while (!endOfStream(false, false))
/*     */     {
/* 378 */       if (returnBuffer == null)
/* 379 */         returnBuffer = readBytes();
/*     */       else {
/* 381 */         returnBuffer = appendByteArrays(returnBuffer, readBytes());
/*     */       }
/*     */     }
/* 384 */     if (returnBuffer == null) {
/* 385 */       return null;
/*     */     }
/* 387 */     return new SoundBuffer(returnBuffer, this.audioFormat);
/*     */   }
/*     */ 
/*     */   public boolean endOfStream()
/*     */   {
/* 396 */     return endOfStream(false, false);
/*     */   }
/*     */ 
/*     */   public void cleanup()
/*     */   {
/* 404 */     this.joggStreamState.clear();
/* 405 */     this.jorbisBlock.clear();
/* 406 */     this.jorbisDspState.clear();
/* 407 */     this.jorbisInfo.clear();
/* 408 */     this.joggSyncState.clear();
/*     */ 
/* 410 */     if (this.inputStream != null)
/*     */     {
/*     */       try
/*     */       {
/* 414 */         this.inputStream.close();
/*     */       }
/*     */       catch (IOException ioe)
/*     */       {
/*     */       }
/*     */     }
/* 420 */     this.joggStreamState = null;
/* 421 */     this.jorbisBlock = null;
/* 422 */     this.jorbisDspState = null;
/* 423 */     this.jorbisInfo = null;
/* 424 */     this.joggSyncState = null;
/* 425 */     this.inputStream = null;
/*     */   }
/*     */ 
/*     */   public AudioFormat getAudioFormat()
/*     */   {
/* 435 */     return this.audioFormat;
/*     */   }
/*     */ 
/*     */   private boolean readHeader()
/*     */     throws IOException
/*     */   {
/* 446 */     this.index = this.joggSyncState.buffer(this.bufferSize);
/*     */ 
/* 448 */     int bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
/* 449 */     if (bytes < 0) {
/* 450 */       bytes = 0;
/*     */     }
/* 452 */     this.joggSyncState.wrote(bytes);
/*     */ 
/* 454 */     if (this.joggSyncState.pageout(this.joggPage) != 1)
/*     */     {
/* 457 */       if (bytes < this.bufferSize) {
/* 458 */         return true;
/*     */       }
/* 460 */       errorMessage("Ogg header not recognized in method 'readHeader'.");
/* 461 */       return false;
/*     */     }
/*     */ 
/* 465 */     this.joggStreamState.init(this.joggPage.serialno());
/*     */ 
/* 467 */     this.jorbisInfo.init();
/* 468 */     this.jorbisComment.init();
/* 469 */     if (this.joggStreamState.pagein(this.joggPage) < 0)
/*     */     {
/* 471 */       errorMessage("Problem with first Ogg header page in method 'readHeader'.");
/*     */ 
/* 473 */       return false;
/*     */     }
/*     */ 
/* 476 */     if (this.joggStreamState.packetout(this.joggPacket) != 1)
/*     */     {
/* 478 */       errorMessage("Problem with first Ogg header packet in method 'readHeader'.");
/*     */ 
/* 480 */       return false;
/*     */     }
/*     */ 
/* 483 */     if (this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket) < 0)
/*     */     {
/* 485 */       errorMessage("File does not contain Vorbis header in method 'readHeader'.");
/*     */ 
/* 487 */       return false;
/*     */     }
/*     */ 
/* 490 */     int i = 0;
/* 491 */     while (i < 2)
/*     */     {
/* 493 */       while (i < 2)
/*     */       {
/* 495 */         int result = this.joggSyncState.pageout(this.joggPage);
/* 496 */         if (result == 0)
/*     */           break;
/* 498 */         if (result == 1)
/*     */         {
/* 500 */           this.joggStreamState.pagein(this.joggPage);
/* 501 */           while (i < 2)
/*     */           {
/* 503 */             result = this.joggStreamState.packetout(this.joggPacket);
/* 504 */             if (result == 0) {
/*     */               break;
/*     */             }
/* 507 */             if (result == -1)
/*     */             {
/* 509 */               errorMessage("Secondary Ogg header corrupt in method 'readHeader'.");
/*     */ 
/* 511 */               return false;
/*     */             }
/*     */ 
/* 514 */             this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket);
/*     */ 
/* 516 */             i++;
/*     */           }
/*     */         }
/*     */       }
/* 520 */       this.index = this.joggSyncState.buffer(this.bufferSize);
/* 521 */       bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
/* 522 */       if (bytes < 0)
/* 523 */         bytes = 0;
/* 524 */       if ((bytes == 0) && (i < 2))
/*     */       {
/* 526 */         errorMessage("End of file reached before finished readingOgg header in method 'readHeader'");
/*     */ 
/* 528 */         return false;
/*     */       }
/*     */ 
/* 531 */       this.joggSyncState.wrote(bytes);
/*     */     }
/*     */ 
/* 534 */     this.index = this.joggSyncState.buffer(this.bufferSize);
/* 535 */     this.buffer = this.joggSyncState.data;
/*     */ 
/* 537 */     return true;
/*     */   }
/*     */ 
/*     */   private byte[] readBytes()
/*     */   {
/* 546 */     if (!initialized(false, false)) {
/* 547 */       return null;
/*     */     }
/* 549 */     if (endOfStream(false, false)) {
/* 550 */       return null;
/*     */     }
/* 552 */     if (this.convertedBuffer == null)
/* 553 */       this.convertedBuffer = new byte[this.convertedBufferSize];
/* 554 */     byte[] returnBuffer = null;
/*     */ 
/* 559 */     switch (this.joggSyncState.pageout(this.joggPage))
/*     */     {
/*     */     case -1:
/*     */     case 0:
/* 563 */       break;
/*     */     default:
/* 566 */       this.joggStreamState.pagein(this.joggPage);
/* 567 */       if (this.joggPage.granulepos() == 0L)
/*     */       {
/* 569 */         endOfStream(true, true);
/* 570 */         return null;
/*     */       }
/*     */ 
/*     */       while (true)
/*     */       {
/* 575 */         switch (this.joggStreamState.packetout(this.joggPacket))
/*     */         {
/*     */         case 0:
/* 578 */           break;
/*     */         case -1:
/* 580 */           break;
/*     */         default:
/* 583 */           if (this.jorbisBlock.synthesis(this.joggPacket) == 0)
/* 584 */             this.jorbisDspState.synthesis_blockin(this.jorbisBlock);
/*     */           int samples;
/* 587 */           while ((samples = this.jorbisDspState.synthesis_pcmout(this.pcmInfo, this.pcmIndex)) > 0)
/*     */           {
/* 589 */             float[][] pcmf = this.pcmInfo[0];
/* 590 */             int bout = samples < this.convertedBufferSize ? samples : this.convertedBufferSize;
/*     */ 
/* 592 */             for (int i = 0; i < this.jorbisInfo.channels; i++)
/*     */             {
/* 594 */               int ptr = i * 2;
/* 595 */               int mono = this.pcmIndex[i];
/* 596 */               for (int j = 0; j < bout; j++)
/*     */               {
/* 598 */                 int val = (int)(pcmf[i][(mono + j)] * 32767.0D);
/*     */ 
/* 600 */                 if (val > 32767)
/* 601 */                   val = 32767;
/* 602 */                 if (val < -32768)
/* 603 */                   val = -32768;
/* 604 */                 if (val < 0)
/* 605 */                   val |= 32768;
/* 606 */                 this.convertedBuffer[ptr] = ((byte)val);
/* 607 */                 this.convertedBuffer[(ptr + 1)] = ((byte)(val >>> 8));
/*     */ 
/* 609 */                 ptr += 2 * this.jorbisInfo.channels;
/*     */               }
/*     */             }
/* 612 */             this.jorbisDspState.synthesis_read(bout);
/*     */ 
/* 614 */             returnBuffer = appendByteArrays(returnBuffer, this.convertedBuffer, 2 * this.jorbisInfo.channels * bout);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 622 */       if (this.joggPage.eos() != 0) {
/* 623 */         endOfStream(true, true);
/*     */       }
/*     */       break;
/*     */     }
/* 627 */     if (!endOfStream(false, false))
/*     */     {
/* 629 */       this.index = this.joggSyncState.buffer(this.bufferSize);
/* 630 */       this.buffer = this.joggSyncState.data;
/*     */       try
/*     */       {
/* 633 */         this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 637 */         printStackTrace(e);
/* 638 */         return null;
/*     */       }
/* 640 */       if (this.count == -1) {
/* 641 */         return returnBuffer;
/*     */       }
/* 643 */       this.joggSyncState.wrote(this.count);
/* 644 */       if (this.count == 0) {
/* 645 */         endOfStream(true, true);
/*     */       }
/*     */     }
/* 648 */     return returnBuffer;
/*     */   }
/*     */ 
/*     */   private synchronized boolean initialized(boolean action, boolean value)
/*     */   {
/* 659 */     if (action == true)
/* 660 */       this.initialized = value;
/* 661 */     return this.initialized;
/*     */   }
/*     */ 
/*     */   private synchronized boolean endOfStream(boolean action, boolean value)
/*     */   {
/* 672 */     if (action == true)
/* 673 */       this.endOfStream = value;
/* 674 */     return this.endOfStream;
/*     */   }
/*     */ 
/*     */   private static byte[] trimArray(byte[] array, int maxLength)
/*     */   {
/* 686 */     byte[] trimmedArray = null;
/* 687 */     if ((array != null) && (array.length > maxLength))
/*     */     {
/* 689 */       trimmedArray = new byte[maxLength];
/* 690 */       System.arraycopy(array, 0, trimmedArray, 0, maxLength);
/*     */     }
/* 692 */     return trimmedArray;
/*     */   }
/*     */ 
/*     */   private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int arrayTwoBytes)
/*     */   {
/* 707 */     int bytes = arrayTwoBytes;
/*     */ 
/* 710 */     if ((arrayTwo == null) || (arrayTwo.length == 0))
/* 711 */       bytes = 0;
/* 712 */     else if (arrayTwo.length < arrayTwoBytes) {
/* 713 */       bytes = arrayTwo.length;
/*     */     }
/* 715 */     if ((arrayOne == null) && ((arrayTwo == null) || (bytes <= 0)))
/*     */     {
/* 718 */       return null;
/*     */     }
/*     */     byte[] newArray;
/* 720 */     if (arrayOne == null)
/*     */     {
/* 723 */       byte[] newArray = new byte[bytes];
/*     */ 
/* 725 */       System.arraycopy(arrayTwo, 0, newArray, 0, bytes);
/* 726 */       arrayTwo = null;
/*     */     }
/* 728 */     else if ((arrayTwo == null) || (bytes <= 0))
/*     */     {
/* 731 */       byte[] newArray = new byte[arrayOne.length];
/*     */ 
/* 733 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 734 */       arrayOne = null;
/*     */     }
/*     */     else
/*     */     {
/* 739 */       newArray = new byte[arrayOne.length + bytes];
/* 740 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/*     */ 
/* 742 */       System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, bytes);
/*     */ 
/* 744 */       arrayOne = null;
/* 745 */       arrayTwo = null;
/*     */     }
/*     */ 
/* 748 */     return newArray;
/*     */   }
/*     */ 
/*     */   private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo)
/*     */   {
/* 761 */     if ((arrayOne == null) && (arrayTwo == null))
/*     */     {
/* 764 */       return null;
/*     */     }
/*     */     byte[] newArray;
/* 766 */     if (arrayOne == null)
/*     */     {
/* 769 */       byte[] newArray = new byte[arrayTwo.length];
/*     */ 
/* 771 */       System.arraycopy(arrayTwo, 0, newArray, 0, arrayTwo.length);
/* 772 */       arrayTwo = null;
/*     */     }
/* 774 */     else if (arrayTwo == null)
/*     */     {
/* 777 */       byte[] newArray = new byte[arrayOne.length];
/*     */ 
/* 779 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 780 */       arrayOne = null;
/*     */     }
/*     */     else
/*     */     {
/* 785 */       newArray = new byte[arrayOne.length + arrayTwo.length];
/* 786 */       System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/*     */ 
/* 788 */       System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, arrayTwo.length);
/*     */ 
/* 790 */       arrayOne = null;
/* 791 */       arrayTwo = null;
/*     */     }
/*     */ 
/* 794 */     return newArray;
/*     */   }
/*     */ 
/*     */   private void errorMessage(String message)
/*     */   {
/* 803 */     this.logger.errorMessage("CodecJOrbis", message, 0);
/*     */   }
/*     */ 
/*     */   private void printStackTrace(Exception e)
/*     */   {
/* 812 */     this.logger.printStackTrace(e, 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.codecs.CodecJOrbis
 * JD-Core Version:    0.6.2
 */