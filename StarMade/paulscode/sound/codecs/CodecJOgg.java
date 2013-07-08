/*   1:    */package paulscode.sound.codecs;
/*   2:    */
/*   3:    */import de.jarnbjo.ogg.CachedUrlStream;
/*   4:    */import de.jarnbjo.ogg.EndOfOggStreamException;
/*   5:    */import de.jarnbjo.ogg.LogicalOggStream;
/*   6:    */import de.jarnbjo.vorbis.IdentificationHeader;
/*   7:    */import de.jarnbjo.vorbis.VorbisStream;
/*   8:    */import java.io.IOException;
/*   9:    */import java.io.InputStream;
/*  10:    */import java.net.URL;
/*  11:    */import java.nio.ByteBuffer;
/*  12:    */import java.nio.ByteOrder;
/*  13:    */import java.nio.ShortBuffer;
/*  14:    */import java.util.Collection;
/*  15:    */import java.util.Iterator;
/*  16:    */import javax.sound.sampled.AudioFormat;
/*  17:    */import javax.sound.sampled.AudioFormat.Encoding;
/*  18:    */import javax.sound.sampled.AudioInputStream;
/*  19:    */import paulscode.sound.ICodec;
/*  20:    */import paulscode.sound.SoundBuffer;
/*  21:    */import paulscode.sound.SoundSystemConfig;
/*  22:    */import paulscode.sound.SoundSystemLogger;
/*  23:    */
/*  92:    */public class CodecJOgg
/*  93:    */  implements ICodec
/*  94:    */{
/*  95:    */  private static final boolean GET = false;
/*  96:    */  private static final boolean SET = true;
/*  97:    */  private static final boolean XXX = false;
/*  98: 98 */  private boolean endOfStream = false;
/*  99:    */  
/* 103:103 */  private boolean initialized = false;
/* 104:    */  
/* 109:109 */  private boolean reverseBytes = false;
/* 110:    */  
/* 114:114 */  private CachedUrlStream cachedUrlStream = null;
/* 115:    */  
/* 119:119 */  private LogicalOggStream myLogicalOggStream = null;
/* 120:    */  
/* 124:124 */  private VorbisStream myVorbisStream = null;
/* 125:    */  
/* 129:129 */  private OggInputStream myOggInputStream = null;
/* 130:    */  
/* 134:134 */  private IdentificationHeader myIdentificationHeader = null;
/* 135:    */  
/* 139:139 */  private AudioFormat myAudioFormat = null;
/* 140:    */  
/* 144:144 */  private AudioInputStream myAudioInputStream = null;
/* 145:    */  
/* 149:    */  private SoundSystemLogger logger;
/* 150:    */  
/* 154:    */  public CodecJOgg()
/* 155:    */  {
/* 156:156 */    this.logger = SoundSystemConfig.getLogger();
/* 157:    */  }
/* 158:    */  
/* 168:    */  public void reverseByteOrder(boolean b)
/* 169:    */  {
/* 170:170 */    this.reverseBytes = b;
/* 171:    */  }
/* 172:    */  
/* 179:    */  public boolean initialize(URL url)
/* 180:    */  {
/* 181:181 */    initialized(true, false);
/* 182:182 */    cleanup();
/* 183:    */    
/* 184:184 */    if (url == null)
/* 185:    */    {
/* 186:186 */      errorMessage("url null in method 'initialize'");
/* 187:187 */      cleanup();
/* 188:188 */      return false;
/* 189:    */    }
/* 190:    */    
/* 192:    */    try
/* 193:    */    {
/* 194:194 */      this.cachedUrlStream = new CachedUrlStream(url);
/* 195:195 */      this.myLogicalOggStream = ((LogicalOggStream)this.cachedUrlStream.getLogicalStreams().iterator().next());
/* 196:    */      
/* 197:197 */      this.myVorbisStream = new VorbisStream(this.myLogicalOggStream);
/* 198:198 */      this.myOggInputStream = new OggInputStream(this.myVorbisStream);
/* 199:    */      
/* 201:201 */      this.myIdentificationHeader = this.myVorbisStream.getIdentificationHeader();
/* 202:    */      
/* 205:205 */      this.myAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.myIdentificationHeader.getSampleRate(), 16, this.myIdentificationHeader.getChannels(), this.myIdentificationHeader.getChannels() * 2, this.myIdentificationHeader.getSampleRate(), true);
/* 206:    */      
/* 215:215 */      this.myAudioInputStream = new AudioInputStream(this.myOggInputStream, this.myAudioFormat, -1L);
/* 217:    */    }
/* 218:    */    catch (Exception e)
/* 219:    */    {
/* 220:220 */      errorMessage("Unable to set up input streams in method 'initialize'");
/* 221:    */      
/* 222:222 */      printStackTrace(e);
/* 223:223 */      cleanup();
/* 224:224 */      return false;
/* 225:    */    }
/* 226:226 */    if (this.myAudioInputStream == null)
/* 227:    */    {
/* 228:228 */      errorMessage("Unable to set up audio input stream in method 'initialize'");
/* 229:    */      
/* 230:230 */      cleanup();
/* 231:231 */      return false;
/* 232:    */    }
/* 233:    */    
/* 234:234 */    endOfStream(true, false);
/* 235:235 */    initialized(true, true);
/* 236:236 */    return true;
/* 237:    */  }
/* 238:    */  
/* 243:    */  public boolean initialized()
/* 244:    */  {
/* 245:245 */    return initialized(false, false);
/* 246:    */  }
/* 247:    */  
/* 254:    */  public SoundBuffer read()
/* 255:    */  {
/* 256:256 */    if (this.myAudioInputStream == null)
/* 257:    */    {
/* 258:258 */      endOfStream(true, true);
/* 259:259 */      return null;
/* 260:    */    }
/* 261:    */    
/* 263:263 */    AudioFormat audioFormat = this.myAudioInputStream.getFormat();
/* 264:    */    
/* 266:266 */    if (audioFormat == null)
/* 267:    */    {
/* 268:268 */      errorMessage("Audio Format null in method 'read'");
/* 269:269 */      endOfStream(true, true);
/* 270:270 */      return null;
/* 271:    */    }
/* 272:    */    
/* 274:274 */    int bytesRead = 0;int cnt = 0;
/* 275:    */    
/* 277:277 */    byte[] streamBuffer = new byte[SoundSystemConfig.getStreamingBufferSize()];
/* 278:    */    
/* 282:    */    try
/* 283:    */    {
/* 284:284 */      while ((!endOfStream(false, false)) && (bytesRead < streamBuffer.length))
/* 285:    */      {
/* 286:286 */        if ((cnt = this.myAudioInputStream.read(streamBuffer, bytesRead, streamBuffer.length - bytesRead)) <= 0)
/* 287:    */        {
/* 290:290 */          endOfStream(true, true);
/* 291:291 */          break;
/* 292:    */        }
/* 293:    */        
/* 294:294 */        bytesRead += cnt;
/* 296:    */      }
/* 297:    */      
/* 300:    */    }
/* 301:    */    catch (IOException ioe)
/* 302:    */    {
/* 305:305 */      endOfStream(true, true);
/* 306:306 */      return null;
/* 307:    */    }
/* 308:    */    
/* 310:310 */    if (bytesRead <= 0)
/* 311:    */    {
/* 312:312 */      endOfStream(true, true);
/* 313:313 */      return null;
/* 314:    */    }
/* 315:    */    
/* 317:317 */    if (this.reverseBytes) {
/* 318:318 */      reverseBytes(streamBuffer, 0, bytesRead);
/* 319:    */    }
/* 320:    */    
/* 321:321 */    if (bytesRead < streamBuffer.length) {
/* 322:322 */      streamBuffer = trimArray(streamBuffer, bytesRead);
/* 323:    */    }
/* 324:    */    
/* 326:326 */    byte[] data = convertAudioBytes(streamBuffer, audioFormat.getSampleSizeInBits() == 16);
/* 327:    */    
/* 330:330 */    SoundBuffer buffer = new SoundBuffer(data, audioFormat);
/* 331:    */    
/* 333:333 */    return buffer;
/* 334:    */  }
/* 335:    */  
/* 344:    */  public SoundBuffer readAll()
/* 345:    */  {
/* 346:346 */    if (this.myAudioFormat == null)
/* 347:    */    {
/* 348:348 */      errorMessage("Audio Format null in method 'readAll'");
/* 349:349 */      return null;
/* 350:    */    }
/* 351:    */    
/* 353:353 */    byte[] fullBuffer = null;
/* 354:    */    
/* 356:356 */    int fileSize = this.myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * this.myAudioFormat.getSampleSizeInBits() / 8;
/* 357:    */    
/* 359:359 */    if (fileSize > 0)
/* 360:    */    {
/* 362:362 */      fullBuffer = new byte[this.myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * this.myAudioFormat.getSampleSizeInBits() / 8];
/* 363:    */      
/* 366:366 */      int read = 0;int total = 0;
/* 367:    */      
/* 371:    */      try
/* 372:    */      {
/* 373:373 */        while (((read = this.myAudioInputStream.read(fullBuffer, total, fullBuffer.length - total)) != -1) && (total < fullBuffer.length))
/* 374:    */        {
/* 375:375 */          total += read;
/* 376:    */        }
/* 377:    */      }
/* 378:    */      catch (IOException e)
/* 379:    */      {
/* 380:380 */        errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
/* 381:    */        
/* 382:382 */        printStackTrace(e);
/* 383:383 */        return null;
/* 384:    */      }
/* 385:    */      
/* 387:    */    }
/* 388:    */    else
/* 389:    */    {
/* 391:391 */      int totalBytes = 0;int bytesRead = 0;int cnt = 0;
/* 392:392 */      byte[] smallBuffer = null;
/* 393:    */      
/* 395:395 */      smallBuffer = new byte[SoundSystemConfig.getFileChunkSize()];
/* 396:    */      
/* 398:398 */      while ((!endOfStream(false, false)) && (totalBytes < SoundSystemConfig.getMaxFileSize()))
/* 399:    */      {
/* 401:401 */        bytesRead = 0;
/* 402:402 */        cnt = 0;
/* 403:    */        
/* 405:    */        try
/* 406:    */        {
/* 407:407 */          while (bytesRead < smallBuffer.length)
/* 408:    */          {
/* 409:409 */            if ((cnt = this.myAudioInputStream.read(smallBuffer, bytesRead, smallBuffer.length - bytesRead)) <= 0)
/* 410:    */            {
/* 413:413 */              endOfStream(true, true);
/* 414:414 */              break;
/* 415:    */            }
/* 416:416 */            bytesRead += cnt;
/* 417:    */          }
/* 418:    */        }
/* 419:    */        catch (IOException e)
/* 420:    */        {
/* 421:421 */          errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
/* 422:    */          
/* 423:423 */          printStackTrace(e);
/* 424:424 */          return null;
/* 425:    */        }
/* 426:    */        
/* 428:428 */        if (this.reverseBytes) {
/* 429:429 */          reverseBytes(smallBuffer, 0, bytesRead);
/* 430:    */        }
/* 431:    */        
/* 432:432 */        totalBytes += bytesRead;
/* 433:    */        
/* 435:435 */        fullBuffer = appendByteArrays(fullBuffer, smallBuffer, bytesRead);
/* 436:    */      }
/* 437:    */    }
/* 438:    */    
/* 441:441 */    byte[] data = convertAudioBytes(fullBuffer, this.myAudioFormat.getSampleSizeInBits() == 16);
/* 442:    */    
/* 445:445 */    SoundBuffer soundBuffer = new SoundBuffer(data, this.myAudioFormat);
/* 446:    */    
/* 448:    */    try
/* 449:    */    {
/* 450:450 */      this.myAudioInputStream.close();
/* 451:    */    }
/* 452:    */    catch (IOException e) {}
/* 453:    */    
/* 456:456 */    return soundBuffer;
/* 457:    */  }
/* 458:    */  
/* 463:    */  public boolean endOfStream()
/* 464:    */  {
/* 465:465 */    return endOfStream(false, false);
/* 466:    */  }
/* 467:    */  
/* 471:    */  public void cleanup()
/* 472:    */  {
/* 473:473 */    if (this.myLogicalOggStream != null) {
/* 474:    */      try
/* 475:    */      {
/* 476:476 */        this.myLogicalOggStream.close();
/* 477:    */      }
/* 478:    */      catch (Exception e) {}
/* 479:    */    }
/* 480:480 */    if (this.myVorbisStream != null) {
/* 481:    */      try
/* 482:    */      {
/* 483:483 */        this.myVorbisStream.close();
/* 484:    */      }
/* 485:    */      catch (Exception e) {}
/* 486:    */    }
/* 487:487 */    if (this.myOggInputStream != null) {
/* 488:    */      try
/* 489:    */      {
/* 490:490 */        this.myOggInputStream.close();
/* 491:    */      }
/* 492:    */      catch (Exception e) {}
/* 493:    */    }
/* 494:494 */    if (this.myAudioInputStream != null) {
/* 495:    */      try
/* 496:    */      {
/* 497:497 */        this.myAudioInputStream.close();
/* 498:    */      }
/* 499:    */      catch (Exception e) {}
/* 500:    */    }
/* 501:501 */    this.myLogicalOggStream = null;
/* 502:502 */    this.myVorbisStream = null;
/* 503:503 */    this.myOggInputStream = null;
/* 504:504 */    this.myAudioInputStream = null;
/* 505:    */  }
/* 506:    */  
/* 512:    */  public AudioFormat getAudioFormat()
/* 513:    */  {
/* 514:514 */    return this.myAudioFormat;
/* 515:    */  }
/* 516:    */  
/* 523:    */  private synchronized boolean initialized(boolean action, boolean value)
/* 524:    */  {
/* 525:525 */    if (action == true)
/* 526:526 */      this.initialized = value;
/* 527:527 */    return this.initialized;
/* 528:    */  }
/* 529:    */  
/* 536:    */  private synchronized boolean endOfStream(boolean action, boolean value)
/* 537:    */  {
/* 538:538 */    if (action == true)
/* 539:539 */      this.endOfStream = value;
/* 540:540 */    return this.endOfStream;
/* 541:    */  }
/* 542:    */  
/* 550:    */  private static byte[] trimArray(byte[] array, int maxLength)
/* 551:    */  {
/* 552:552 */    byte[] trimmedArray = null;
/* 553:553 */    if ((array != null) && (array.length > maxLength))
/* 554:    */    {
/* 555:555 */      trimmedArray = new byte[maxLength];
/* 556:556 */      System.arraycopy(array, 0, trimmedArray, 0, maxLength);
/* 557:    */    }
/* 558:    */    
/* 559:559 */    return trimmedArray;
/* 560:    */  }
/* 561:    */  
/* 569:    */  private static byte[] convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data)
/* 570:    */  {
/* 571:571 */    ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 572:572 */    dest.order(ByteOrder.nativeOrder());
/* 573:573 */    ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 574:574 */    src.order(ByteOrder.LITTLE_ENDIAN);
/* 575:575 */    if (two_bytes_data)
/* 576:    */    {
/* 577:577 */      ShortBuffer dest_short = dest.asShortBuffer();
/* 578:578 */      ShortBuffer src_short = src.asShortBuffer();
/* 579:579 */      while (src_short.hasRemaining())
/* 580:    */      {
/* 581:581 */        dest_short.put(src_short.get());
/* 582:    */      }
/* 583:    */    }
/* 584:    */    else
/* 585:    */    {
/* 586:586 */      while (src.hasRemaining())
/* 587:    */      {
/* 588:588 */        dest.put(src.get());
/* 589:    */      }
/* 590:    */    }
/* 591:591 */    dest.rewind();
/* 592:    */    
/* 593:593 */    if (!dest.hasArray())
/* 594:    */    {
/* 595:595 */      byte[] arrayBackedBuffer = new byte[dest.capacity()];
/* 596:596 */      dest.get(arrayBackedBuffer);
/* 597:597 */      dest.clear();
/* 598:    */      
/* 599:599 */      return arrayBackedBuffer;
/* 600:    */    }
/* 601:    */    
/* 602:602 */    return dest.array();
/* 603:    */  }
/* 604:    */  
/* 615:    */  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int length)
/* 616:    */  {
/* 617:617 */    if ((arrayOne == null) && (arrayTwo == null))
/* 618:    */    {
/* 620:620 */      return null; }
/* 621:    */    byte[] newArray;
/* 622:622 */    if (arrayOne == null)
/* 623:    */    {
/* 625:625 */      byte[] newArray = new byte[length];
/* 626:    */      
/* 627:627 */      System.arraycopy(arrayTwo, 0, newArray, 0, length);
/* 628:628 */      arrayTwo = null;
/* 629:    */    }
/* 630:630 */    else if (arrayTwo == null)
/* 631:    */    {
/* 633:633 */      byte[] newArray = new byte[arrayOne.length];
/* 634:    */      
/* 635:635 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 636:636 */      arrayOne = null;
/* 638:    */    }
/* 639:    */    else
/* 640:    */    {
/* 641:641 */      newArray = new byte[arrayOne.length + length];
/* 642:642 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 643:    */      
/* 644:644 */      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, length);
/* 645:    */      
/* 646:646 */      arrayOne = null;
/* 647:647 */      arrayTwo = null;
/* 648:    */    }
/* 649:    */    
/* 650:650 */    return newArray;
/* 651:    */  }
/* 652:    */  
/* 657:    */  public static void reverseBytes(byte[] buffer)
/* 658:    */  {
/* 659:659 */    reverseBytes(buffer, 0, buffer.length);
/* 660:    */  }
/* 661:    */  
/* 670:    */  public static void reverseBytes(byte[] buffer, int offset, int size)
/* 671:    */  {
/* 672:672 */    for (int i = offset; i < offset + size; i += 2)
/* 673:    */    {
/* 674:674 */      byte b = buffer[i];
/* 675:675 */      buffer[i] = buffer[(i + 1)];
/* 676:676 */      buffer[(i + 1)] = b;
/* 677:    */    }
/* 678:    */  }
/* 679:    */  
/* 685:    */  private class OggInputStream
/* 686:    */    extends InputStream
/* 687:    */  {
/* 688:    */    private VorbisStream myVorbisStream;
/* 689:    */    
/* 695:    */    public OggInputStream(VorbisStream source)
/* 696:    */    {
/* 697:697 */      this.myVorbisStream = source;
/* 698:    */    }
/* 699:    */    
/* 704:    */    public int read()
/* 705:    */      throws IOException
/* 706:    */    {
/* 707:707 */      return 0;
/* 708:    */    }
/* 709:    */    
/* 716:    */    public int read(byte[] buffer)
/* 717:    */      throws IOException
/* 718:    */    {
/* 719:719 */      return read(buffer, 0, buffer.length);
/* 720:    */    }
/* 721:    */    
/* 730:    */    public int read(byte[] buffer, int offset, int length)
/* 731:    */      throws IOException
/* 732:    */    {
/* 733:    */      try
/* 734:    */      {
/* 735:735 */        return this.myVorbisStream.readPcm(buffer, offset, length);
/* 736:    */      }
/* 737:    */      catch (EndOfOggStreamException e) {}
/* 738:    */      
/* 740:740 */      return -1;
/* 741:    */    }
/* 742:    */  }
/* 743:    */  
/* 749:    */  private void errorMessage(String message)
/* 750:    */  {
/* 751:751 */    this.logger.errorMessage("CodecJOgg", message, 0);
/* 752:    */  }
/* 753:    */  
/* 758:    */  private void printStackTrace(Exception e)
/* 759:    */  {
/* 760:760 */    this.logger.printStackTrace(e, 1);
/* 761:    */  }
/* 762:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.codecs.CodecJOgg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */