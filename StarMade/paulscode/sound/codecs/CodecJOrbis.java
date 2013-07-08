/*   1:    */package paulscode.sound.codecs;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Packet;
/*   4:    */import com.jcraft.jogg.Page;
/*   5:    */import com.jcraft.jogg.StreamState;
/*   6:    */import com.jcraft.jogg.SyncState;
/*   7:    */import com.jcraft.jorbis.Block;
/*   8:    */import com.jcraft.jorbis.Comment;
/*   9:    */import com.jcraft.jorbis.DspState;
/*  10:    */import com.jcraft.jorbis.Info;
/*  11:    */import java.io.IOException;
/*  12:    */import java.io.InputStream;
/*  13:    */import java.net.URL;
/*  14:    */import java.net.URLConnection;
/*  15:    */import java.net.UnknownServiceException;
/*  16:    */import javax.sound.sampled.AudioFormat;
/*  17:    */import paulscode.sound.ICodec;
/*  18:    */import paulscode.sound.SoundBuffer;
/*  19:    */import paulscode.sound.SoundSystemConfig;
/*  20:    */import paulscode.sound.SoundSystemLogger;
/*  21:    */
/*  90:    */public class CodecJOrbis
/*  91:    */  implements ICodec
/*  92:    */{
/*  93:    */  private static final boolean GET = false;
/*  94:    */  private static final boolean SET = true;
/*  95:    */  private static final boolean XXX = false;
/*  96:    */  private URL url;
/*  97: 97 */  private URLConnection urlConnection = null;
/*  98:    */  
/* 102:    */  private InputStream inputStream;
/* 103:    */  
/* 107:    */  private AudioFormat audioFormat;
/* 108:    */  
/* 112:112 */  private boolean endOfStream = false;
/* 113:    */  
/* 117:117 */  private boolean initialized = false;
/* 118:    */  
/* 122:122 */  private byte[] buffer = null;
/* 123:    */  
/* 127:    */  private int bufferSize;
/* 128:    */  
/* 132:132 */  private int count = 0;
/* 133:    */  
/* 137:137 */  private int index = 0;
/* 138:    */  
/* 142:    */  private int convertedBufferSize;
/* 143:    */  
/* 147:147 */  private byte[] convertedBuffer = null;
/* 148:    */  
/* 152:    */  private float[][][] pcmInfo;
/* 153:    */  
/* 157:    */  private int[] pcmIndex;
/* 158:    */  
/* 162:162 */  private Packet joggPacket = new Packet();
/* 163:    */  
/* 166:166 */  private Page joggPage = new Page();
/* 167:    */  
/* 170:170 */  private StreamState joggStreamState = new StreamState();
/* 171:    */  
/* 174:174 */  private SyncState joggSyncState = new SyncState();
/* 175:    */  
/* 178:178 */  private DspState jorbisDspState = new DspState();
/* 179:    */  
/* 182:182 */  private Block jorbisBlock = new Block(this.jorbisDspState);
/* 183:    */  
/* 186:186 */  private Comment jorbisComment = new Comment();
/* 187:    */  
/* 190:190 */  private Info jorbisInfo = new Info();
/* 191:    */  
/* 195:    */  private SoundSystemLogger logger;
/* 196:    */  
/* 200:    */  public CodecJOrbis()
/* 201:    */  {
/* 202:202 */    this.logger = SoundSystemConfig.getLogger();
/* 203:    */  }
/* 204:    */  
/* 211:    */  public void reverseByteOrder(boolean b) {}
/* 212:    */  
/* 218:    */  public boolean initialize(URL url)
/* 219:    */  {
/* 220:220 */    initialized(true, false);
/* 221:    */    
/* 222:222 */    if (this.joggStreamState != null)
/* 223:223 */      this.joggStreamState.clear();
/* 224:224 */    if (this.jorbisBlock != null)
/* 225:225 */      this.jorbisBlock.clear();
/* 226:226 */    if (this.jorbisDspState != null)
/* 227:227 */      this.jorbisDspState.clear();
/* 228:228 */    if (this.jorbisInfo != null)
/* 229:229 */      this.jorbisInfo.clear();
/* 230:230 */    if (this.joggSyncState != null) {
/* 231:231 */      this.joggSyncState.clear();
/* 232:    */    }
/* 233:233 */    if (this.inputStream != null)
/* 234:    */    {
/* 235:    */      try
/* 236:    */      {
/* 237:237 */        this.inputStream.close();
/* 238:    */      }
/* 239:    */      catch (IOException ioe) {}
/* 240:    */    }
/* 241:    */    
/* 243:243 */    this.url = url;
/* 244:    */    
/* 245:245 */    this.bufferSize = 8192;
/* 246:    */    
/* 247:247 */    this.buffer = null;
/* 248:248 */    this.count = 0;
/* 249:249 */    this.index = 0;
/* 250:    */    
/* 251:251 */    this.joggStreamState = new StreamState();
/* 252:252 */    this.jorbisBlock = new Block(this.jorbisDspState);
/* 253:253 */    this.jorbisDspState = new DspState();
/* 254:254 */    this.jorbisInfo = new Info();
/* 255:255 */    this.joggSyncState = new SyncState();
/* 256:    */    
/* 257:    */    try
/* 258:    */    {
/* 259:259 */      this.urlConnection = url.openConnection();
/* 260:    */    }
/* 261:    */    catch (UnknownServiceException use)
/* 262:    */    {
/* 263:263 */      errorMessage("Unable to create a UrlConnection in method 'initialize'.");
/* 264:    */      
/* 265:265 */      printStackTrace(use);
/* 266:266 */      cleanup();
/* 267:267 */      return false;
/* 268:    */    }
/* 269:    */    catch (IOException ioe)
/* 270:    */    {
/* 271:271 */      errorMessage("Unable to create a UrlConnection in method 'initialize'.");
/* 272:    */      
/* 273:273 */      printStackTrace(ioe);
/* 274:274 */      cleanup();
/* 275:275 */      return false;
/* 276:    */    }
/* 277:277 */    if (this.urlConnection != null)
/* 278:    */    {
/* 279:    */      try
/* 280:    */      {
/* 281:281 */        this.inputStream = this.urlConnection.getInputStream();
/* 282:    */      }
/* 283:    */      catch (IOException ioe)
/* 284:    */      {
/* 285:285 */        errorMessage("Unable to acquire inputstream in method 'initialize'.");
/* 286:    */        
/* 287:287 */        printStackTrace(ioe);
/* 288:288 */        cleanup();
/* 289:289 */        return false;
/* 290:    */      }
/* 291:    */    }
/* 292:    */    
/* 293:293 */    endOfStream(true, false);
/* 294:    */    
/* 295:295 */    this.joggSyncState.init();
/* 296:296 */    this.joggSyncState.buffer(this.bufferSize);
/* 297:297 */    this.buffer = this.joggSyncState.data;
/* 298:    */    
/* 299:    */    try
/* 300:    */    {
/* 301:301 */      if (!readHeader())
/* 302:    */      {
/* 303:303 */        errorMessage("Error reading the header");
/* 304:304 */        return false;
/* 305:    */      }
/* 306:    */    }
/* 307:    */    catch (IOException ioe)
/* 308:    */    {
/* 309:309 */      errorMessage("Error reading the header");
/* 310:310 */      return false;
/* 311:    */    }
/* 312:    */    
/* 313:313 */    this.convertedBufferSize = (this.bufferSize * 2);
/* 314:    */    
/* 315:315 */    this.jorbisDspState.synthesis_init(this.jorbisInfo);
/* 316:316 */    this.jorbisBlock.init(this.jorbisDspState);
/* 317:    */    
/* 318:318 */    int channels = this.jorbisInfo.channels;
/* 319:319 */    int rate = this.jorbisInfo.rate;
/* 320:    */    
/* 321:321 */    this.audioFormat = new AudioFormat(rate, 16, channels, true, false);
/* 322:    */    
/* 323:323 */    this.pcmInfo = new float[1][][];
/* 324:324 */    this.pcmIndex = new int[this.jorbisInfo.channels];
/* 325:    */    
/* 326:326 */    initialized(true, true);
/* 327:    */    
/* 328:328 */    return true;
/* 329:    */  }
/* 330:    */  
/* 335:    */  public boolean initialized()
/* 336:    */  {
/* 337:337 */    return initialized(false, false);
/* 338:    */  }
/* 339:    */  
/* 346:    */  public SoundBuffer read()
/* 347:    */  {
/* 348:348 */    byte[] returnBuffer = null;
/* 349:    */    
/* 350:350 */    while ((!endOfStream(false, false)) && ((returnBuffer == null) || (returnBuffer.length < SoundSystemConfig.getStreamingBufferSize())))
/* 351:    */    {
/* 353:353 */      if (returnBuffer == null) {
/* 354:354 */        returnBuffer = readBytes();
/* 355:    */      } else {
/* 356:356 */        returnBuffer = appendByteArrays(returnBuffer, readBytes());
/* 357:    */      }
/* 358:    */    }
/* 359:359 */    if (returnBuffer == null) {
/* 360:360 */      return null;
/* 361:    */    }
/* 362:362 */    return new SoundBuffer(returnBuffer, this.audioFormat);
/* 363:    */  }
/* 364:    */  
/* 372:    */  public SoundBuffer readAll()
/* 373:    */  {
/* 374:374 */    byte[] returnBuffer = null;
/* 375:    */    
/* 376:376 */    while (!endOfStream(false, false))
/* 377:    */    {
/* 378:378 */      if (returnBuffer == null) {
/* 379:379 */        returnBuffer = readBytes();
/* 380:    */      } else {
/* 381:381 */        returnBuffer = appendByteArrays(returnBuffer, readBytes());
/* 382:    */      }
/* 383:    */    }
/* 384:384 */    if (returnBuffer == null) {
/* 385:385 */      return null;
/* 386:    */    }
/* 387:387 */    return new SoundBuffer(returnBuffer, this.audioFormat);
/* 388:    */  }
/* 389:    */  
/* 394:    */  public boolean endOfStream()
/* 395:    */  {
/* 396:396 */    return endOfStream(false, false);
/* 397:    */  }
/* 398:    */  
/* 402:    */  public void cleanup()
/* 403:    */  {
/* 404:404 */    this.joggStreamState.clear();
/* 405:405 */    this.jorbisBlock.clear();
/* 406:406 */    this.jorbisDspState.clear();
/* 407:407 */    this.jorbisInfo.clear();
/* 408:408 */    this.joggSyncState.clear();
/* 409:    */    
/* 410:410 */    if (this.inputStream != null)
/* 411:    */    {
/* 412:    */      try
/* 413:    */      {
/* 414:414 */        this.inputStream.close();
/* 415:    */      }
/* 416:    */      catch (IOException ioe) {}
/* 417:    */    }
/* 418:    */    
/* 420:420 */    this.joggStreamState = null;
/* 421:421 */    this.jorbisBlock = null;
/* 422:422 */    this.jorbisDspState = null;
/* 423:423 */    this.jorbisInfo = null;
/* 424:424 */    this.joggSyncState = null;
/* 425:425 */    this.inputStream = null;
/* 426:    */  }
/* 427:    */  
/* 433:    */  public AudioFormat getAudioFormat()
/* 434:    */  {
/* 435:435 */    return this.audioFormat;
/* 436:    */  }
/* 437:    */  
/* 443:    */  private boolean readHeader()
/* 444:    */    throws IOException
/* 445:    */  {
/* 446:446 */    this.index = this.joggSyncState.buffer(this.bufferSize);
/* 447:    */    
/* 448:448 */    int bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
/* 449:449 */    if (bytes < 0) {
/* 450:450 */      bytes = 0;
/* 451:    */    }
/* 452:452 */    this.joggSyncState.wrote(bytes);
/* 453:    */    
/* 454:454 */    if (this.joggSyncState.pageout(this.joggPage) != 1)
/* 455:    */    {
/* 457:457 */      if (bytes < this.bufferSize) {
/* 458:458 */        return true;
/* 459:    */      }
/* 460:460 */      errorMessage("Ogg header not recognized in method 'readHeader'.");
/* 461:461 */      return false;
/* 462:    */    }
/* 463:    */    
/* 465:465 */    this.joggStreamState.init(this.joggPage.serialno());
/* 466:    */    
/* 467:467 */    this.jorbisInfo.init();
/* 468:468 */    this.jorbisComment.init();
/* 469:469 */    if (this.joggStreamState.pagein(this.joggPage) < 0)
/* 470:    */    {
/* 471:471 */      errorMessage("Problem with first Ogg header page in method 'readHeader'.");
/* 472:    */      
/* 473:473 */      return false;
/* 474:    */    }
/* 475:    */    
/* 476:476 */    if (this.joggStreamState.packetout(this.joggPacket) != 1)
/* 477:    */    {
/* 478:478 */      errorMessage("Problem with first Ogg header packet in method 'readHeader'.");
/* 479:    */      
/* 480:480 */      return false;
/* 481:    */    }
/* 482:    */    
/* 483:483 */    if (this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket) < 0)
/* 484:    */    {
/* 485:485 */      errorMessage("File does not contain Vorbis header in method 'readHeader'.");
/* 486:    */      
/* 487:487 */      return false;
/* 488:    */    }
/* 489:    */    
/* 490:490 */    int i = 0;
/* 491:491 */    while (i < 2)
/* 492:    */    {
/* 493:493 */      while (i < 2)
/* 494:    */      {
/* 495:495 */        int result = this.joggSyncState.pageout(this.joggPage);
/* 496:496 */        if (result == 0)
/* 497:    */          break;
/* 498:498 */        if (result == 1)
/* 499:    */        {
/* 500:500 */          this.joggStreamState.pagein(this.joggPage);
/* 501:501 */          while (i < 2)
/* 502:    */          {
/* 503:503 */            result = this.joggStreamState.packetout(this.joggPacket);
/* 504:504 */            if (result == 0) {
/* 505:    */              break;
/* 506:    */            }
/* 507:507 */            if (result == -1)
/* 508:    */            {
/* 509:509 */              errorMessage("Secondary Ogg header corrupt in method 'readHeader'.");
/* 510:    */              
/* 511:511 */              return false;
/* 512:    */            }
/* 513:    */            
/* 514:514 */            this.jorbisInfo.synthesis_headerin(this.jorbisComment, this.joggPacket);
/* 515:    */            
/* 516:516 */            i++;
/* 517:    */          }
/* 518:    */        }
/* 519:    */      }
/* 520:520 */      this.index = this.joggSyncState.buffer(this.bufferSize);
/* 521:521 */      bytes = this.inputStream.read(this.joggSyncState.data, this.index, this.bufferSize);
/* 522:522 */      if (bytes < 0)
/* 523:523 */        bytes = 0;
/* 524:524 */      if ((bytes == 0) && (i < 2))
/* 525:    */      {
/* 526:526 */        errorMessage("End of file reached before finished readingOgg header in method 'readHeader'");
/* 527:    */        
/* 528:528 */        return false;
/* 529:    */      }
/* 530:    */      
/* 531:531 */      this.joggSyncState.wrote(bytes);
/* 532:    */    }
/* 533:    */    
/* 534:534 */    this.index = this.joggSyncState.buffer(this.bufferSize);
/* 535:535 */    this.buffer = this.joggSyncState.data;
/* 536:    */    
/* 537:537 */    return true;
/* 538:    */  }
/* 539:    */  
/* 544:    */  private byte[] readBytes()
/* 545:    */  {
/* 546:546 */    if (!initialized(false, false)) {
/* 547:547 */      return null;
/* 548:    */    }
/* 549:549 */    if (endOfStream(false, false)) {
/* 550:550 */      return null;
/* 551:    */    }
/* 552:552 */    if (this.convertedBuffer == null)
/* 553:553 */      this.convertedBuffer = new byte[this.convertedBufferSize];
/* 554:554 */    byte[] returnBuffer = null;
/* 555:    */    
/* 559:559 */    switch (this.joggSyncState.pageout(this.joggPage))
/* 560:    */    {
/* 561:    */    case -1: 
/* 562:    */    case 0: 
/* 563:563 */      break;
/* 564:    */    
/* 565:    */    default: 
/* 566:566 */      this.joggStreamState.pagein(this.joggPage);
/* 567:567 */      if (this.joggPage.granulepos() == 0L)
/* 568:    */      {
/* 569:569 */        endOfStream(true, true);
/* 570:570 */        return null;
/* 571:    */      }
/* 572:    */      
/* 573:    */      for (;;)
/* 574:    */      {
/* 575:575 */        switch (this.joggStreamState.packetout(this.joggPacket))
/* 576:    */        {
/* 577:    */        case 0: 
/* 578:578 */          break;
/* 579:    */        case -1: 
/* 580:580 */          break;
/* 581:    */        
/* 582:    */        default: 
/* 583:583 */          if (this.jorbisBlock.synthesis(this.joggPacket) == 0) {
/* 584:584 */            this.jorbisDspState.synthesis_blockin(this.jorbisBlock);
/* 585:    */          }
/* 586:    */          int samples;
/* 587:587 */          while ((samples = this.jorbisDspState.synthesis_pcmout(this.pcmInfo, this.pcmIndex)) > 0)
/* 588:    */          {
/* 589:589 */            float[][] pcmf = this.pcmInfo[0];
/* 590:590 */            int bout = samples < this.convertedBufferSize ? samples : this.convertedBufferSize;
/* 591:    */            
/* 592:592 */            for (int i = 0; i < this.jorbisInfo.channels; i++)
/* 593:    */            {
/* 594:594 */              int ptr = i * 2;
/* 595:595 */              int mono = this.pcmIndex[i];
/* 596:596 */              for (int j = 0; j < bout; j++)
/* 597:    */              {
/* 598:598 */                int val = (int)(pcmf[i][(mono + j)] * 32767.0D);
/* 599:    */                
/* 600:600 */                if (val > 32767)
/* 601:601 */                  val = 32767;
/* 602:602 */                if (val < -32768)
/* 603:603 */                  val = -32768;
/* 604:604 */                if (val < 0)
/* 605:605 */                  val |= 32768;
/* 606:606 */                this.convertedBuffer[ptr] = ((byte)val);
/* 607:607 */                this.convertedBuffer[(ptr + 1)] = ((byte)(val >>> 8));
/* 608:    */                
/* 609:609 */                ptr += 2 * this.jorbisInfo.channels;
/* 610:    */              }
/* 611:    */            }
/* 612:612 */            this.jorbisDspState.synthesis_read(bout);
/* 613:    */            
/* 614:614 */            returnBuffer = appendByteArrays(returnBuffer, this.convertedBuffer, 2 * this.jorbisInfo.channels * bout);
/* 615:    */          }
/* 616:    */        }
/* 617:    */        
/* 618:    */      }
/* 619:    */      
/* 622:622 */      if (this.joggPage.eos() != 0) {
/* 623:623 */        endOfStream(true, true);
/* 624:    */      }
/* 625:    */      break;
/* 626:    */    }
/* 627:627 */    if (!endOfStream(false, false))
/* 628:    */    {
/* 629:629 */      this.index = this.joggSyncState.buffer(this.bufferSize);
/* 630:630 */      this.buffer = this.joggSyncState.data;
/* 631:    */      try
/* 632:    */      {
/* 633:633 */        this.count = this.inputStream.read(this.buffer, this.index, this.bufferSize);
/* 634:    */      }
/* 635:    */      catch (Exception e)
/* 636:    */      {
/* 637:637 */        printStackTrace(e);
/* 638:638 */        return null;
/* 639:    */      }
/* 640:640 */      if (this.count == -1) {
/* 641:641 */        return returnBuffer;
/* 642:    */      }
/* 643:643 */      this.joggSyncState.wrote(this.count);
/* 644:644 */      if (this.count == 0) {
/* 645:645 */        endOfStream(true, true);
/* 646:    */      }
/* 647:    */    }
/* 648:648 */    return returnBuffer;
/* 649:    */  }
/* 650:    */  
/* 657:    */  private synchronized boolean initialized(boolean action, boolean value)
/* 658:    */  {
/* 659:659 */    if (action == true)
/* 660:660 */      this.initialized = value;
/* 661:661 */    return this.initialized;
/* 662:    */  }
/* 663:    */  
/* 670:    */  private synchronized boolean endOfStream(boolean action, boolean value)
/* 671:    */  {
/* 672:672 */    if (action == true)
/* 673:673 */      this.endOfStream = value;
/* 674:674 */    return this.endOfStream;
/* 675:    */  }
/* 676:    */  
/* 684:    */  private static byte[] trimArray(byte[] array, int maxLength)
/* 685:    */  {
/* 686:686 */    byte[] trimmedArray = null;
/* 687:687 */    if ((array != null) && (array.length > maxLength))
/* 688:    */    {
/* 689:689 */      trimmedArray = new byte[maxLength];
/* 690:690 */      System.arraycopy(array, 0, trimmedArray, 0, maxLength);
/* 691:    */    }
/* 692:692 */    return trimmedArray;
/* 693:    */  }
/* 694:    */  
/* 705:    */  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int arrayTwoBytes)
/* 706:    */  {
/* 707:707 */    int bytes = arrayTwoBytes;
/* 708:    */    
/* 710:710 */    if ((arrayTwo == null) || (arrayTwo.length == 0)) {
/* 711:711 */      bytes = 0;
/* 712:712 */    } else if (arrayTwo.length < arrayTwoBytes) {
/* 713:713 */      bytes = arrayTwo.length;
/* 714:    */    }
/* 715:715 */    if ((arrayOne == null) && ((arrayTwo == null) || (bytes <= 0)))
/* 716:    */    {
/* 718:718 */      return null; }
/* 719:    */    byte[] newArray;
/* 720:720 */    if (arrayOne == null)
/* 721:    */    {
/* 723:723 */      byte[] newArray = new byte[bytes];
/* 724:    */      
/* 725:725 */      System.arraycopy(arrayTwo, 0, newArray, 0, bytes);
/* 726:726 */      arrayTwo = null;
/* 727:    */    }
/* 728:728 */    else if ((arrayTwo == null) || (bytes <= 0))
/* 729:    */    {
/* 731:731 */      byte[] newArray = new byte[arrayOne.length];
/* 732:    */      
/* 733:733 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 734:734 */      arrayOne = null;
/* 736:    */    }
/* 737:    */    else
/* 738:    */    {
/* 739:739 */      newArray = new byte[arrayOne.length + bytes];
/* 740:740 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 741:    */      
/* 742:742 */      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, bytes);
/* 743:    */      
/* 744:744 */      arrayOne = null;
/* 745:745 */      arrayTwo = null;
/* 746:    */    }
/* 747:    */    
/* 748:748 */    return newArray;
/* 749:    */  }
/* 750:    */  
/* 759:    */  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo)
/* 760:    */  {
/* 761:761 */    if ((arrayOne == null) && (arrayTwo == null))
/* 762:    */    {
/* 764:764 */      return null; }
/* 765:    */    byte[] newArray;
/* 766:766 */    if (arrayOne == null)
/* 767:    */    {
/* 769:769 */      byte[] newArray = new byte[arrayTwo.length];
/* 770:    */      
/* 771:771 */      System.arraycopy(arrayTwo, 0, newArray, 0, arrayTwo.length);
/* 772:772 */      arrayTwo = null;
/* 773:    */    }
/* 774:774 */    else if (arrayTwo == null)
/* 775:    */    {
/* 777:777 */      byte[] newArray = new byte[arrayOne.length];
/* 778:    */      
/* 779:779 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 780:780 */      arrayOne = null;
/* 782:    */    }
/* 783:    */    else
/* 784:    */    {
/* 785:785 */      newArray = new byte[arrayOne.length + arrayTwo.length];
/* 786:786 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 787:    */      
/* 788:788 */      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, arrayTwo.length);
/* 789:    */      
/* 790:790 */      arrayOne = null;
/* 791:791 */      arrayTwo = null;
/* 792:    */    }
/* 793:    */    
/* 794:794 */    return newArray;
/* 795:    */  }
/* 796:    */  
/* 801:    */  private void errorMessage(String message)
/* 802:    */  {
/* 803:803 */    this.logger.errorMessage("CodecJOrbis", message, 0);
/* 804:    */  }
/* 805:    */  
/* 810:    */  private void printStackTrace(Exception e)
/* 811:    */  {
/* 812:812 */    this.logger.printStackTrace(e, 1);
/* 813:    */  }
/* 814:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.codecs.CodecJOrbis
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */