/*   1:    */package paulscode.sound.libraries;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.util.LinkedList;
/*   6:    */import javax.sound.sampled.AudioFormat;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.openal.AL10;
/*   9:    */import paulscode.sound.Channel;
/*  10:    */import paulscode.sound.FilenameURL;
/*  11:    */import paulscode.sound.ICodec;
/*  12:    */import paulscode.sound.SoundBuffer;
/*  13:    */import paulscode.sound.SoundSystemConfig;
/*  14:    */import paulscode.sound.Source;
/*  15:    */import paulscode.sound.Vector3D;
/*  16:    */
/*  94:    */public class SourceLWJGLOpenAL
/*  95:    */  extends Source
/*  96:    */{
/*  97: 97 */  private ChannelLWJGLOpenAL channelOpenAL = (ChannelLWJGLOpenAL)this.channel;
/*  98:    */  
/* 106:    */  private IntBuffer myBuffer;
/* 107:    */  
/* 115:    */  private FloatBuffer listenerPosition;
/* 116:    */  
/* 124:    */  private FloatBuffer sourcePosition;
/* 125:    */  
/* 133:    */  private FloatBuffer sourceVelocity;
/* 134:    */  
/* 143:    */  public SourceLWJGLOpenAL(FloatBuffer listenerPosition, IntBuffer myBuffer, boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/* 144:    */  {
/* 145:145 */    super(priority, toStream, toLoop, sourcename, filenameURL, soundBuffer, x, y, z, attModel, distOrRoll, temporary);
/* 146:146 */    if (this.codec != null)
/* 147:147 */      this.codec.reverseByteOrder(true);
/* 148:148 */    this.listenerPosition = listenerPosition;
/* 149:149 */    this.myBuffer = myBuffer;
/* 150:150 */    this.libraryType = LibraryLWJGLOpenAL.class;
/* 151:151 */    this.pitch = 1.0F;
/* 152:152 */    resetALInformation();
/* 153:    */  }
/* 154:    */  
/* 163:    */  public SourceLWJGLOpenAL(FloatBuffer listenerPosition, IntBuffer myBuffer, Source old, SoundBuffer soundBuffer)
/* 164:    */  {
/* 165:165 */    super(old, soundBuffer);
/* 166:166 */    if (this.codec != null)
/* 167:167 */      this.codec.reverseByteOrder(true);
/* 168:168 */    this.listenerPosition = listenerPosition;
/* 169:169 */    this.myBuffer = myBuffer;
/* 170:170 */    this.libraryType = LibraryLWJGLOpenAL.class;
/* 171:171 */    this.pitch = 1.0F;
/* 172:172 */    resetALInformation();
/* 173:    */  }
/* 174:    */  
/* 192:    */  public SourceLWJGLOpenAL(FloatBuffer listenerPosition, AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/* 193:    */  {
/* 194:194 */    super(audioFormat, priority, sourcename, x, y, z, attModel, distOrRoll);
/* 195:195 */    this.listenerPosition = listenerPosition;
/* 196:196 */    this.libraryType = LibraryLWJGLOpenAL.class;
/* 197:197 */    this.pitch = 1.0F;
/* 198:198 */    resetALInformation();
/* 199:    */  }
/* 200:    */  
/* 206:    */  public void cleanup()
/* 207:    */  {
/* 208:208 */    super.cleanup();
/* 209:    */  }
/* 210:    */  
/* 234:    */  public void changeSource(FloatBuffer listenerPosition, IntBuffer myBuffer, boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/* 235:    */  {
/* 236:236 */    super.changeSource(priority, toStream, toLoop, sourcename, 
/* 237:237 */      filenameURL, soundBuffer, x, y, z, attModel, 
/* 238:238 */      distOrRoll, temporary);
/* 239:239 */    this.listenerPosition = listenerPosition;
/* 240:240 */    this.myBuffer = myBuffer;
/* 241:241 */    this.pitch = 1.0F;
/* 242:242 */    resetALInformation();
/* 243:    */  }
/* 244:    */  
/* 253:    */  public boolean incrementSoundSequence()
/* 254:    */  {
/* 255:255 */    if (!this.toStream)
/* 256:    */    {
/* 257:257 */      errorMessage("Method 'incrementSoundSequence' may only be used for streaming sources.");
/* 258:    */      
/* 259:259 */      return false;
/* 260:    */    }
/* 261:261 */    synchronized (this.soundSequenceLock)
/* 262:    */    {
/* 263:263 */      if ((this.soundSequenceQueue != null) && (this.soundSequenceQueue.size() > 0))
/* 264:    */      {
/* 265:265 */        this.filenameURL = ((FilenameURL)this.soundSequenceQueue.remove(0));
/* 266:266 */        if (this.codec != null)
/* 267:267 */          this.codec.cleanup();
/* 268:268 */        this.codec = SoundSystemConfig.getCodec(this.filenameURL.getFilename());
/* 269:269 */        if (this.codec != null)
/* 270:    */        {
/* 271:271 */          this.codec.reverseByteOrder(true);
/* 272:272 */          if (this.codec.getAudioFormat() == null) {
/* 273:273 */            this.codec.initialize(this.filenameURL.getURL());
/* 274:    */          }
/* 275:275 */          AudioFormat audioFormat = this.codec.getAudioFormat();
/* 276:    */          
/* 277:277 */          if (audioFormat == null)
/* 278:    */          {
/* 279:279 */            errorMessage("Audio Format null in method 'incrementSoundSequence'");
/* 280:    */            
/* 281:281 */            return false;
/* 282:    */          }
/* 283:    */          
/* 284:284 */          int soundFormat = 0;
/* 285:285 */          if (audioFormat.getChannels() == 1)
/* 286:    */          {
/* 287:287 */            if (audioFormat.getSampleSizeInBits() == 8)
/* 288:    */            {
/* 289:289 */              soundFormat = 4352;
/* 290:    */            }
/* 291:291 */            else if (audioFormat.getSampleSizeInBits() == 16)
/* 292:    */            {
/* 293:293 */              soundFormat = 4353;
/* 294:    */            }
/* 295:    */            else
/* 296:    */            {
/* 297:297 */              errorMessage("Illegal sample size in method 'incrementSoundSequence'");
/* 298:    */              
/* 299:299 */              return false;
/* 300:    */            }
/* 301:    */          }
/* 302:302 */          else if (audioFormat.getChannels() == 2)
/* 303:    */          {
/* 304:304 */            if (audioFormat.getSampleSizeInBits() == 8)
/* 305:    */            {
/* 306:306 */              soundFormat = 4354;
/* 307:    */            }
/* 308:308 */            else if (audioFormat.getSampleSizeInBits() == 16)
/* 309:    */            {
/* 310:310 */              soundFormat = 4355;
/* 311:    */            }
/* 312:    */            else
/* 313:    */            {
/* 314:314 */              errorMessage("Illegal sample size in method 'incrementSoundSequence'");
/* 315:    */              
/* 316:316 */              return false;
/* 317:    */            }
/* 318:    */          }
/* 319:    */          else
/* 320:    */          {
/* 321:321 */            errorMessage("Audio data neither mono nor stereo in method 'incrementSoundSequence'");
/* 322:    */            
/* 323:323 */            return false;
/* 324:    */          }
/* 325:    */          
/* 327:327 */          this.channelOpenAL.setFormat(soundFormat, 
/* 328:328 */            (int)audioFormat.getSampleRate());
/* 329:329 */          this.preLoad = true;
/* 330:    */        }
/* 331:331 */        return true;
/* 332:    */      }
/* 333:    */    }
/* 334:334 */    return false;
/* 335:    */  }
/* 336:    */  
/* 341:    */  public void listenerMoved()
/* 342:    */  {
/* 343:343 */    positionChanged();
/* 344:    */  }
/* 345:    */  
/* 353:    */  public void setPosition(float x, float y, float z)
/* 354:    */  {
/* 355:355 */    super.setPosition(x, y, z);
/* 356:    */    
/* 358:358 */    if (this.sourcePosition == null) {
/* 359:359 */      resetALInformation();
/* 360:    */    } else {
/* 361:361 */      positionChanged();
/* 362:    */    }
/* 363:    */    
/* 364:364 */    this.sourcePosition.put(0, x);
/* 365:365 */    this.sourcePosition.put(1, y);
/* 366:366 */    this.sourcePosition.put(2, z);
/* 367:    */    
/* 369:369 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 370:370 */      (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 371:    */    {
/* 373:373 */      AL10.alSource(this.channelOpenAL.ALSource.get(0), 4100, 
/* 374:374 */        this.sourcePosition);
/* 375:375 */      checkALError();
/* 376:    */    }
/* 377:    */  }
/* 378:    */  
/* 383:    */  public void positionChanged()
/* 384:    */  {
/* 385:385 */    calculateDistance();
/* 386:386 */    calculateGain();
/* 387:    */    
/* 388:388 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 389:389 */      (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 390:    */    {
/* 391:391 */      AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 392:392 */        4106, this.gain * this.sourceVolume * 
/* 393:393 */        Math.abs(this.fadeOutGain) * 
/* 394:394 */        this.fadeInGain);
/* 395:395 */      checkALError();
/* 396:    */    }
/* 397:397 */    checkPitch();
/* 398:    */  }
/* 399:    */  
/* 403:    */  private void checkPitch()
/* 404:    */  {
/* 405:405 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 406:406 */      (LibraryLWJGLOpenAL.alPitchSupported()) && (this.channelOpenAL != null) && 
/* 407:407 */      (this.channelOpenAL.ALSource != null))
/* 408:    */    {
/* 409:409 */      AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 410:410 */        4099, this.pitch);
/* 411:411 */      checkALError();
/* 412:    */    }
/* 413:    */  }
/* 414:    */  
/* 420:    */  public void setLooping(boolean lp)
/* 421:    */  {
/* 422:422 */    super.setLooping(lp);
/* 423:    */    
/* 425:425 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 426:426 */      (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 427:    */    {
/* 428:428 */      if (lp) {
/* 429:429 */        AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 430:430 */          4103, 1);
/* 431:    */      } else
/* 432:432 */        AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 433:433 */          4103, 0);
/* 434:434 */      checkALError();
/* 435:    */    }
/* 436:    */  }
/* 437:    */  
/* 443:    */  public void setAttenuation(int model)
/* 444:    */  {
/* 445:445 */    super.setAttenuation(model);
/* 446:    */    
/* 447:447 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 448:448 */      (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 449:    */    {
/* 451:451 */      if (model == 1) {
/* 452:452 */        AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 453:453 */          4129, this.distOrRoll);
/* 454:    */      } else
/* 455:455 */        AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 456:456 */          4129, 0.0F);
/* 457:457 */      checkALError();
/* 458:    */    }
/* 459:    */  }
/* 460:    */  
/* 467:    */  public void setDistOrRoll(float dr)
/* 468:    */  {
/* 469:469 */    super.setDistOrRoll(dr);
/* 470:    */    
/* 471:471 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 472:472 */      (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 473:    */    {
/* 475:475 */      if (this.attModel == 1) {
/* 476:476 */        AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 477:477 */          4129, dr);
/* 478:    */      } else
/* 479:479 */        AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 480:480 */          4129, 0.0F);
/* 481:481 */      checkALError();
/* 482:    */    }
/* 483:    */  }
/* 484:    */  
/* 492:    */  public void setVelocity(float x, float y, float z)
/* 493:    */  {
/* 494:494 */    super.setVelocity(x, y, z);
/* 495:    */    
/* 496:496 */    this.sourceVelocity = BufferUtils.createFloatBuffer(3).put(
/* 497:497 */      new float[] { x, y, z });
/* 498:498 */    this.sourceVelocity.flip();
/* 499:    */    
/* 500:500 */    if ((this.channel != null) && (this.channel.attachedSource == this) && 
/* 501:501 */      (this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 502:    */    {
/* 503:503 */      AL10.alSource(this.channelOpenAL.ALSource.get(0), 
/* 504:504 */        4102, this.sourceVelocity);
/* 505:505 */      checkALError();
/* 506:    */    }
/* 507:    */  }
/* 508:    */  
/* 514:    */  public void setPitch(float value)
/* 515:    */  {
/* 516:516 */    super.setPitch(value);
/* 517:517 */    checkPitch();
/* 518:    */  }
/* 519:    */  
/* 525:    */  public void play(Channel c)
/* 526:    */  {
/* 527:527 */    if (!active())
/* 528:    */    {
/* 529:529 */      if (this.toLoop)
/* 530:530 */        this.toPlay = true;
/* 531:531 */      return;
/* 532:    */    }
/* 533:    */    
/* 534:534 */    if (c == null)
/* 535:    */    {
/* 536:536 */      errorMessage("Unable to play source, because channel was null");
/* 537:537 */      return;
/* 538:    */    }
/* 539:    */    
/* 540:540 */    boolean newChannel = this.channel != c;
/* 541:541 */    if ((this.channel != null) && (this.channel.attachedSource != this)) {
/* 542:542 */      newChannel = true;
/* 543:    */    }
/* 544:544 */    boolean wasPaused = paused();
/* 545:    */    
/* 546:546 */    super.play(c);
/* 547:    */    
/* 548:548 */    this.channelOpenAL = ((ChannelLWJGLOpenAL)this.channel);
/* 549:    */    
/* 552:552 */    if (newChannel)
/* 553:    */    {
/* 554:554 */      setPosition(this.position.x, this.position.y, this.position.z);
/* 555:555 */      checkPitch();
/* 556:    */      
/* 558:558 */      if ((this.channelOpenAL != null) && (this.channelOpenAL.ALSource != null))
/* 559:    */      {
/* 560:560 */        if (LibraryLWJGLOpenAL.alPitchSupported())
/* 561:    */        {
/* 562:562 */          AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 563:563 */            4099, this.pitch);
/* 564:564 */          checkALError();
/* 565:    */        }
/* 566:566 */        AL10.alSource(this.channelOpenAL.ALSource.get(0), 
/* 567:567 */          4100, this.sourcePosition);
/* 568:568 */        checkALError();
/* 569:    */        
/* 570:570 */        AL10.alSource(this.channelOpenAL.ALSource.get(0), 
/* 571:571 */          4102, this.sourceVelocity);
/* 572:    */        
/* 573:573 */        checkALError();
/* 574:    */        
/* 575:575 */        if (this.attModel == 1) {
/* 576:576 */          AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 577:577 */            4129, this.distOrRoll);
/* 578:    */        } else
/* 579:579 */          AL10.alSourcef(this.channelOpenAL.ALSource.get(0), 
/* 580:580 */            4129, 0.0F);
/* 581:581 */        checkALError();
/* 582:    */        
/* 583:583 */        if ((this.toLoop) && (!this.toStream)) {
/* 584:584 */          AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 585:585 */            4103, 1);
/* 586:    */        } else
/* 587:587 */          AL10.alSourcei(this.channelOpenAL.ALSource.get(0), 
/* 588:588 */            4103, 0);
/* 589:589 */        checkALError();
/* 590:    */      }
/* 591:591 */      if (!this.toStream)
/* 592:    */      {
/* 595:595 */        if (this.myBuffer == null)
/* 596:    */        {
/* 597:597 */          errorMessage("No sound buffer to play");
/* 598:598 */          return;
/* 599:    */        }
/* 600:    */        
/* 601:601 */        this.channelOpenAL.attachBuffer(this.myBuffer);
/* 602:    */      }
/* 603:    */    }
/* 604:    */    
/* 606:606 */    if (!playing())
/* 607:    */    {
/* 608:608 */      if ((this.toStream) && (!wasPaused))
/* 609:    */      {
/* 610:610 */        if (this.codec == null)
/* 611:    */        {
/* 612:612 */          errorMessage("Decoder null in method 'play'");
/* 613:613 */          return;
/* 614:    */        }
/* 615:615 */        if (this.codec.getAudioFormat() == null) {
/* 616:616 */          this.codec.initialize(this.filenameURL.getURL());
/* 617:    */        }
/* 618:618 */        AudioFormat audioFormat = this.codec.getAudioFormat();
/* 619:    */        
/* 620:620 */        if (audioFormat == null)
/* 621:    */        {
/* 622:622 */          errorMessage("Audio Format null in method 'play'");
/* 623:623 */          return;
/* 624:    */        }
/* 625:    */        
/* 626:626 */        int soundFormat = 0;
/* 627:627 */        if (audioFormat.getChannels() == 1)
/* 628:    */        {
/* 629:629 */          if (audioFormat.getSampleSizeInBits() == 8)
/* 630:    */          {
/* 631:631 */            soundFormat = 4352;
/* 632:    */          }
/* 633:633 */          else if (audioFormat.getSampleSizeInBits() == 16)
/* 634:    */          {
/* 635:635 */            soundFormat = 4353;
/* 636:    */          }
/* 637:    */          else
/* 638:    */          {
/* 639:639 */            errorMessage("Illegal sample size in method 'play'");
/* 640:    */          }
/* 641:    */          
/* 642:    */        }
/* 643:643 */        else if (audioFormat.getChannels() == 2)
/* 644:    */        {
/* 645:645 */          if (audioFormat.getSampleSizeInBits() == 8)
/* 646:    */          {
/* 647:647 */            soundFormat = 4354;
/* 648:    */          }
/* 649:649 */          else if (audioFormat.getSampleSizeInBits() == 16)
/* 650:    */          {
/* 651:651 */            soundFormat = 4355;
/* 652:    */          }
/* 653:    */          else
/* 654:    */          {
/* 655:655 */            errorMessage("Illegal sample size in method 'play'");
/* 656:    */          }
/* 657:    */          
/* 658:    */        }
/* 659:    */        else
/* 660:    */        {
/* 661:661 */          errorMessage("Audio data neither mono nor stereo in method 'play'");
/* 662:    */          
/* 663:663 */          return;
/* 664:    */        }
/* 665:    */        
/* 667:667 */        this.channelOpenAL.setFormat(soundFormat, 
/* 668:668 */          (int)audioFormat.getSampleRate());
/* 669:669 */        this.preLoad = true;
/* 670:    */      }
/* 671:671 */      this.channel.play();
/* 672:672 */      if (this.pitch != 1.0F) {
/* 673:673 */        checkPitch();
/* 674:    */      }
/* 675:    */    }
/* 676:    */  }
/* 677:    */  
/* 682:    */  public boolean preLoad()
/* 683:    */  {
/* 684:684 */    if (this.codec == null) {
/* 685:685 */      return false;
/* 686:    */    }
/* 687:687 */    this.codec.initialize(this.filenameURL.getURL());
/* 688:688 */    LinkedList<byte[]> preLoadBuffers = new LinkedList();
/* 689:689 */    for (int i = 0; i < SoundSystemConfig.getNumberStreamingBuffers(); i++)
/* 690:    */    {
/* 691:691 */      this.soundBuffer = this.codec.read();
/* 692:    */      
/* 693:693 */      if ((this.soundBuffer == null) || (this.soundBuffer.audioData == null)) {
/* 694:    */        break;
/* 695:    */      }
/* 696:696 */      preLoadBuffers.add(this.soundBuffer.audioData);
/* 697:    */    }
/* 698:698 */    positionChanged();
/* 699:    */    
/* 700:700 */    this.channel.preLoadBuffers(preLoadBuffers);
/* 701:    */    
/* 702:702 */    this.preLoad = false;
/* 703:703 */    return true;
/* 704:    */  }
/* 705:    */  
/* 710:    */  private void resetALInformation()
/* 711:    */  {
/* 712:712 */    this.sourcePosition = BufferUtils.createFloatBuffer(3).put(
/* 713:713 */      new float[] { this.position.x, this.position.y, this.position.z });
/* 714:714 */    this.sourceVelocity = BufferUtils.createFloatBuffer(3).put(
/* 715:715 */      new float[] { this.velocity.x, this.velocity.y, this.velocity.z });
/* 716:    */    
/* 718:718 */    this.sourcePosition.flip();
/* 719:719 */    this.sourceVelocity.flip();
/* 720:    */    
/* 721:721 */    positionChanged();
/* 722:    */  }
/* 723:    */  
/* 727:    */  private void calculateDistance()
/* 728:    */  {
/* 729:729 */    if (this.listenerPosition != null)
/* 730:    */    {
/* 732:732 */      double dX = this.position.x - this.listenerPosition.get(0);
/* 733:733 */      double dY = this.position.y - this.listenerPosition.get(1);
/* 734:734 */      double dZ = this.position.z - this.listenerPosition.get(2);
/* 735:735 */      this.distanceFromListener = ((float)Math.sqrt(dX * dX + dY * dY + dZ * dZ));
/* 736:    */    }
/* 737:    */  }
/* 738:    */  
/* 744:    */  private void calculateGain()
/* 745:    */  {
/* 746:746 */    if (this.attModel == 2)
/* 747:    */    {
/* 748:748 */      if (this.distanceFromListener <= 0.0F)
/* 749:    */      {
/* 750:750 */        this.gain = 1.0F;
/* 751:    */      }
/* 752:752 */      else if (this.distanceFromListener >= this.distOrRoll)
/* 753:    */      {
/* 754:754 */        this.gain = 0.0F;
/* 755:    */      }
/* 756:    */      else
/* 757:    */      {
/* 758:758 */        this.gain = (1.0F - this.distanceFromListener / this.distOrRoll);
/* 759:    */      }
/* 760:760 */      if (this.gain > 1.0F)
/* 761:761 */        this.gain = 1.0F;
/* 762:762 */      if (this.gain < 0.0F) {
/* 763:763 */        this.gain = 0.0F;
/* 764:    */      }
/* 765:    */    }
/* 766:    */    else {
/* 767:767 */      this.gain = 1.0F;
/* 768:    */    }
/* 769:    */  }
/* 770:    */  
/* 775:    */  private boolean checkALError()
/* 776:    */  {
/* 777:777 */    switch ()
/* 778:    */    {
/* 779:    */    case 0: 
/* 780:780 */      return false;
/* 781:    */    case 40961: 
/* 782:782 */      errorMessage("Invalid name parameter.");
/* 783:783 */      return true;
/* 784:    */    case 40962: 
/* 785:785 */      errorMessage("Invalid parameter.");
/* 786:786 */      return true;
/* 787:    */    case 40963: 
/* 788:788 */      errorMessage("Invalid enumerated parameter value.");
/* 789:789 */      return true;
/* 790:    */    case 40964: 
/* 791:791 */      errorMessage("Illegal call.");
/* 792:792 */      return true;
/* 793:    */    case 40965: 
/* 794:794 */      errorMessage("Unable to allocate memory.");
/* 795:795 */      return true;
/* 796:    */    }
/* 797:797 */    errorMessage("An unrecognized error occurred.");
/* 798:798 */    return true;
/* 799:    */  }
/* 800:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.libraries.SourceLWJGLOpenAL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */