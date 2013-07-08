/*   1:    */package paulscode.sound.libraries;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.util.LinkedList;
/*   6:    */import javax.sound.sampled.AudioFormat;
/*   7:    */import org.lwjgl.BufferUtils;
/*   8:    */import org.lwjgl.openal.AL10;
/*   9:    */import paulscode.sound.Channel;
/*  10:    */import paulscode.sound.SoundBuffer;
/*  11:    */import paulscode.sound.Source;
/*  12:    */
/* 106:    */public class ChannelLWJGLOpenAL
/* 107:    */  extends Channel
/* 108:    */{
/* 109:    */  public IntBuffer ALSource;
/* 110:    */  public int ALformat;
/* 111:    */  public int sampleRate;
/* 112:112 */  public float millisPreviouslyPlayed = 0.0F;
/* 113:    */  
/* 122:    */  public ChannelLWJGLOpenAL(int type, IntBuffer src)
/* 123:    */  {
/* 124:124 */    super(type);
/* 125:125 */    this.libraryType = LibraryLWJGLOpenAL.class;
/* 126:126 */    this.ALSource = src;
/* 127:    */  }
/* 128:    */  
/* 134:    */  public void cleanup()
/* 135:    */  {
/* 136:136 */    if (this.ALSource != null)
/* 137:    */    {
/* 139:    */      try
/* 140:    */      {
/* 141:141 */        AL10.alSourceStop(this.ALSource);
/* 142:142 */        AL10.alGetError();
/* 143:    */      }
/* 144:    */      catch (Exception localException) {}
/* 145:    */      
/* 147:    */      try
/* 148:    */      {
/* 149:149 */        AL10.alDeleteSources(this.ALSource);
/* 150:150 */        AL10.alGetError();
/* 151:    */      }
/* 152:    */      catch (Exception localException1) {}
/* 153:    */      
/* 154:154 */      this.ALSource.clear();
/* 155:    */    }
/* 156:156 */    this.ALSource = null;
/* 157:    */    
/* 158:158 */    super.cleanup();
/* 159:    */  }
/* 160:    */  
/* 170:    */  public boolean attachBuffer(IntBuffer buf)
/* 171:    */  {
/* 172:172 */    if (errorCheck(this.channelType != 0, "Sound buffers may only be attached to normal sources.")) {
/* 173:173 */      return false;
/* 174:    */    }
/* 175:    */    
/* 176:176 */    AL10.alSourcei(this.ALSource.get(0), 4105, 
/* 177:177 */      buf.get(0));
/* 178:    */    
/* 181:181 */    if ((this.attachedSource != null) && (this.attachedSource.soundBuffer != null) && 
/* 182:182 */      (this.attachedSource.soundBuffer.audioFormat != null)) {
/* 183:183 */      setAudioFormat(this.attachedSource.soundBuffer.audioFormat);
/* 184:    */    }
/* 185:    */    
/* 186:186 */    return checkALError();
/* 187:    */  }
/* 188:    */  
/* 193:    */  public void setAudioFormat(AudioFormat audioFormat)
/* 194:    */  {
/* 195:195 */    int soundFormat = 0;
/* 196:196 */    if (audioFormat.getChannels() == 1)
/* 197:    */    {
/* 198:198 */      if (audioFormat.getSampleSizeInBits() == 8)
/* 199:    */      {
/* 200:200 */        soundFormat = 4352;
/* 201:    */      }
/* 202:202 */      else if (audioFormat.getSampleSizeInBits() == 16)
/* 203:    */      {
/* 204:204 */        soundFormat = 4353;
/* 205:    */      }
/* 206:    */      else
/* 207:    */      {
/* 208:208 */        errorMessage("Illegal sample size in method 'setAudioFormat'");
/* 209:    */      }
/* 210:    */      
/* 212:    */    }
/* 213:213 */    else if (audioFormat.getChannels() == 2)
/* 214:    */    {
/* 215:215 */      if (audioFormat.getSampleSizeInBits() == 8)
/* 216:    */      {
/* 217:217 */        soundFormat = 4354;
/* 218:    */      }
/* 219:219 */      else if (audioFormat.getSampleSizeInBits() == 16)
/* 220:    */      {
/* 221:221 */        soundFormat = 4355;
/* 222:    */      }
/* 223:    */      else
/* 224:    */      {
/* 225:225 */        errorMessage("Illegal sample size in method 'setAudioFormat'");
/* 226:    */      }
/* 227:    */      
/* 229:    */    }
/* 230:    */    else
/* 231:    */    {
/* 232:232 */      errorMessage("Audio data neither mono nor stereo in method 'setAudioFormat'");
/* 233:    */      
/* 234:234 */      return;
/* 235:    */    }
/* 236:236 */    this.ALformat = soundFormat;
/* 237:237 */    this.sampleRate = ((int)audioFormat.getSampleRate());
/* 238:    */  }
/* 239:    */  
/* 245:    */  public void setFormat(int format, int rate)
/* 246:    */  {
/* 247:247 */    this.ALformat = format;
/* 248:248 */    this.sampleRate = rate;
/* 249:    */  }
/* 250:    */  
/* 259:    */  public boolean preLoadBuffers(LinkedList<byte[]> bufferList)
/* 260:    */  {
/* 261:261 */    if (errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
/* 262:262 */      return false;
/* 263:    */    }
/* 264:    */    
/* 265:265 */    if (errorCheck(bufferList == null, "Buffer List null in method 'preLoadBuffers'")) {
/* 266:266 */      return false;
/* 267:    */    }
/* 268:    */    
/* 271:271 */    boolean playing = playing();
/* 272:    */    
/* 273:273 */    if (playing)
/* 274:    */    {
/* 275:275 */      AL10.alSourceStop(this.ALSource.get(0));
/* 276:276 */      checkALError();
/* 277:    */    }
/* 278:    */    
/* 279:279 */    int processed = AL10.alGetSourcei(this.ALSource.get(0), 
/* 280:280 */      4118);
/* 281:281 */    if (processed > 0)
/* 282:    */    {
/* 283:283 */      IntBuffer streamBuffers = BufferUtils.createIntBuffer(processed);
/* 284:284 */      AL10.alGenBuffers(streamBuffers);
/* 285:    */      
/* 286:286 */      if (errorCheck(checkALError(), "Error clearing stream buffers in method 'preLoadBuffers'"))
/* 287:287 */        return false;
/* 288:288 */      AL10.alSourceUnqueueBuffers(this.ALSource.get(0), streamBuffers);
/* 289:    */      
/* 290:290 */      if (errorCheck(checkALError(), "Error unqueuing stream buffers in method 'preLoadBuffers'")) {
/* 291:291 */        return false;
/* 292:    */      }
/* 293:    */    }
/* 294:    */    
/* 295:295 */    if (playing)
/* 296:    */    {
/* 297:297 */      AL10.alSourcePlay(this.ALSource.get(0));
/* 298:298 */      checkALError();
/* 299:    */    }
/* 300:    */    
/* 301:301 */    IntBuffer streamBuffers = BufferUtils.createIntBuffer(bufferList.size());
/* 302:302 */    AL10.alGenBuffers(streamBuffers);
/* 303:    */    
/* 304:304 */    if (errorCheck(checkALError(), "Error generating stream buffers in method 'preLoadBuffers'")) {
/* 305:305 */      return false;
/* 306:    */    }
/* 307:307 */    ByteBuffer byteBuffer = null;
/* 308:308 */    for (int i = 0; i < bufferList.size(); i++)
/* 309:    */    {
/* 312:312 */      byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(
/* 313:313 */        ((byte[])bufferList.get(i)).length).put((byte[])bufferList.get(i)).flip();
/* 314:    */      
/* 315:    */      try
/* 316:    */      {
/* 317:317 */        AL10.alBufferData(streamBuffers.get(i), this.ALformat, byteBuffer, 
/* 318:318 */          this.sampleRate);
/* 319:    */      }
/* 320:    */      catch (Exception e)
/* 321:    */      {
/* 322:322 */        errorMessage("Error creating buffers in method 'preLoadBuffers'");
/* 323:    */        
/* 324:324 */        printStackTrace(e);
/* 325:325 */        return false;
/* 326:    */      }
/* 327:    */      
/* 328:328 */      if (errorCheck(checkALError(), "Error creating buffers in method 'preLoadBuffers'")) {
/* 329:329 */        return false;
/* 330:    */      }
/* 331:    */    }
/* 332:    */    
/* 333:    */    try
/* 334:    */    {
/* 335:335 */      AL10.alSourceQueueBuffers(this.ALSource.get(0), streamBuffers);
/* 336:    */    }
/* 337:    */    catch (Exception e)
/* 338:    */    {
/* 339:339 */      errorMessage("Error queuing buffers in method 'preLoadBuffers'");
/* 340:340 */      printStackTrace(e);
/* 341:341 */      return false;
/* 342:    */    }
/* 343:    */    
/* 344:344 */    if (errorCheck(checkALError(), "Error queuing buffers in method 'preLoadBuffers'")) {
/* 345:345 */      return false;
/* 346:    */    }
/* 347:347 */    AL10.alSourcePlay(this.ALSource.get(0));
/* 348:    */    
/* 349:349 */    if (errorCheck(checkALError(), "Error playing source in method 'preLoadBuffers'")) {
/* 350:350 */      return false;
/* 351:    */    }
/* 352:    */    
/* 353:353 */    return true;
/* 354:    */  }
/* 355:    */  
/* 364:    */  public boolean queueBuffer(byte[] buffer)
/* 365:    */  {
/* 366:366 */    if (errorCheck(this.channelType != 1, "Buffers may only be queued for streaming sources.")) {
/* 367:367 */      return false;
/* 368:    */    }
/* 369:    */    
/* 370:370 */    ByteBuffer byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(
/* 371:371 */      buffer.length).put(buffer).flip();
/* 372:    */    
/* 373:373 */    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/* 374:    */    
/* 375:375 */    AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
/* 376:376 */    if (checkALError()) {
/* 377:377 */      return false;
/* 378:    */    }
/* 379:379 */    if (AL10.alIsBuffer(intBuffer.get(0)))
/* 380:380 */      this.millisPreviouslyPlayed += millisInBuffer(intBuffer.get(0));
/* 381:381 */    checkALError();
/* 382:    */    
/* 383:383 */    AL10.alBufferData(intBuffer.get(0), this.ALformat, byteBuffer, this.sampleRate);
/* 384:384 */    if (checkALError()) {
/* 385:385 */      return false;
/* 386:    */    }
/* 387:387 */    AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer);
/* 388:388 */    if (checkALError()) {
/* 389:389 */      return false;
/* 390:    */    }
/* 391:391 */    return true;
/* 392:    */  }
/* 393:    */  
/* 402:    */  public int feedRawAudioData(byte[] buffer)
/* 403:    */  {
/* 404:404 */    if (errorCheck(this.channelType != 1, "Raw audio data can only be fed to streaming sources.")) {
/* 405:405 */      return -1;
/* 406:    */    }
/* 407:    */    
/* 408:408 */    ByteBuffer byteBuffer = (ByteBuffer)BufferUtils.createByteBuffer(
/* 409:409 */      buffer.length).put(buffer).flip();
/* 410:    */    
/* 414:414 */    int processed = AL10.alGetSourcei(this.ALSource.get(0), 
/* 415:415 */      4118);
/* 416:416 */    if (processed > 0)
/* 417:    */    {
/* 418:418 */      IntBuffer intBuffer = BufferUtils.createIntBuffer(processed);
/* 419:419 */      AL10.alGenBuffers(intBuffer);
/* 420:    */      
/* 421:421 */      if (errorCheck(checkALError(), "Error clearing stream buffers in method 'feedRawAudioData'"))
/* 422:422 */        return -1;
/* 423:423 */      AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
/* 424:    */      
/* 425:425 */      if (errorCheck(checkALError(), "Error unqueuing stream buffers in method 'feedRawAudioData'")) {
/* 426:426 */        return -1;
/* 427:    */      }
/* 428:428 */      intBuffer.rewind();
/* 429:429 */      while (intBuffer.hasRemaining())
/* 430:    */      {
/* 431:431 */        int i = intBuffer.get();
/* 432:432 */        if (AL10.alIsBuffer(i))
/* 433:    */        {
/* 434:434 */          this.millisPreviouslyPlayed += millisInBuffer(i);
/* 435:    */        }
/* 436:436 */        checkALError();
/* 437:    */      }
/* 438:438 */      AL10.alDeleteBuffers(intBuffer);
/* 439:439 */      checkALError();
/* 440:    */    }
/* 441:441 */    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/* 442:442 */    AL10.alGenBuffers(intBuffer);
/* 443:    */    
/* 444:444 */    if (errorCheck(checkALError(), "Error generating stream buffers in method 'preLoadBuffers'")) {
/* 445:445 */      return -1;
/* 446:    */    }
/* 447:447 */    AL10.alBufferData(intBuffer.get(0), this.ALformat, byteBuffer, this.sampleRate);
/* 448:448 */    if (checkALError()) {
/* 449:449 */      return -1;
/* 450:    */    }
/* 451:451 */    AL10.alSourceQueueBuffers(this.ALSource.get(0), intBuffer);
/* 452:452 */    if (checkALError()) {
/* 453:453 */      return -1;
/* 454:    */    }
/* 455:455 */    if ((this.attachedSource != null) && (this.attachedSource.channel == this) && 
/* 456:456 */      (this.attachedSource.active()))
/* 457:    */    {
/* 459:459 */      if (!playing())
/* 460:    */      {
/* 461:461 */        AL10.alSourcePlay(this.ALSource.get(0));
/* 462:462 */        checkALError();
/* 463:    */      }
/* 464:    */    }
/* 465:    */    
/* 466:466 */    return processed;
/* 467:    */  }
/* 468:    */  
/* 473:    */  public float millisInBuffer(int alBufferi)
/* 474:    */  {
/* 475:475 */    return AL10.alGetBufferi(alBufferi, 8196) / 
/* 476:476 */      AL10.alGetBufferi(alBufferi, 8195) / (
/* 477:477 */      AL10.alGetBufferi(alBufferi, 8194) / 8.0F) / 
/* 478:478 */      this.sampleRate * 1000.0F;
/* 479:    */  }
/* 480:    */  
/* 487:    */  public float millisecondsPlayed()
/* 488:    */  {
/* 489:489 */    float offset = AL10.alGetSourcei(this.ALSource.get(0), 
/* 490:490 */      4134);
/* 491:    */    
/* 492:492 */    float bytesPerFrame = 1.0F;
/* 493:493 */    switch (this.ALformat)
/* 494:    */    {
/* 495:    */    case 4352: 
/* 496:496 */      bytesPerFrame = 1.0F;
/* 497:497 */      break;
/* 498:    */    case 4353: 
/* 499:499 */      bytesPerFrame = 2.0F;
/* 500:500 */      break;
/* 501:    */    case 4354: 
/* 502:502 */      bytesPerFrame = 2.0F;
/* 503:503 */      break;
/* 504:    */    case 4355: 
/* 505:505 */      bytesPerFrame = 4.0F;
/* 506:506 */      break;
/* 507:    */    }
/* 508:    */    
/* 509:    */    
/* 511:511 */    offset = offset / bytesPerFrame / this.sampleRate * 
/* 512:512 */      1000.0F;
/* 513:    */    
/* 515:515 */    if (this.channelType == 1) {
/* 516:516 */      offset += this.millisPreviouslyPlayed;
/* 517:    */    }
/* 518:    */    
/* 519:519 */    return offset;
/* 520:    */  }
/* 521:    */  
/* 528:    */  public int buffersProcessed()
/* 529:    */  {
/* 530:530 */    if (this.channelType != 1) {
/* 531:531 */      return 0;
/* 532:    */    }
/* 533:    */    
/* 534:534 */    int processed = AL10.alGetSourcei(this.ALSource.get(0), 
/* 535:535 */      4118);
/* 536:    */    
/* 538:538 */    if (checkALError()) {
/* 539:539 */      return 0;
/* 540:    */    }
/* 541:    */    
/* 542:542 */    return processed;
/* 543:    */  }
/* 544:    */  
/* 551:    */  public void flush()
/* 552:    */  {
/* 553:553 */    if (this.channelType != 1) {
/* 554:554 */      return;
/* 555:    */    }
/* 556:    */    
/* 557:557 */    int queued = AL10.alGetSourcei(this.ALSource.get(0), 
/* 558:558 */      4117);
/* 559:    */    
/* 560:560 */    if (checkALError()) {
/* 561:561 */      return;
/* 562:    */    }
/* 563:563 */    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/* 564:564 */    while (queued > 0)
/* 565:    */    {
/* 566:    */      try
/* 567:    */      {
/* 568:568 */        AL10.alSourceUnqueueBuffers(this.ALSource.get(0), intBuffer);
/* 569:    */      }
/* 570:    */      catch (Exception e)
/* 571:    */      {
/* 572:572 */        return;
/* 573:    */      }
/* 574:574 */      if (checkALError())
/* 575:575 */        return;
/* 576:576 */      queued--;
/* 577:    */    }
/* 578:578 */    this.millisPreviouslyPlayed = 0.0F;
/* 579:    */  }
/* 580:    */  
/* 585:    */  public void close()
/* 586:    */  {
/* 587:    */    try
/* 588:    */    {
/* 589:589 */      AL10.alSourceStop(this.ALSource.get(0));
/* 590:590 */      AL10.alGetError();
/* 591:    */    }
/* 592:    */    catch (Exception localException) {}
/* 593:    */    
/* 595:595 */    if (this.channelType == 1) {
/* 596:596 */      flush();
/* 597:    */    }
/* 598:    */  }
/* 599:    */  
/* 604:    */  public void play()
/* 605:    */  {
/* 606:606 */    AL10.alSourcePlay(this.ALSource.get(0));
/* 607:607 */    checkALError();
/* 608:    */  }
/* 609:    */  
/* 614:    */  public void pause()
/* 615:    */  {
/* 616:616 */    AL10.alSourcePause(this.ALSource.get(0));
/* 617:617 */    checkALError();
/* 618:    */  }
/* 619:    */  
/* 625:    */  public void stop()
/* 626:    */  {
/* 627:627 */    AL10.alSourceStop(this.ALSource.get(0));
/* 628:628 */    if (!checkALError()) {
/* 629:629 */      this.millisPreviouslyPlayed = 0.0F;
/* 630:    */    }
/* 631:    */  }
/* 632:    */  
/* 638:    */  public void rewind()
/* 639:    */  {
/* 640:640 */    if (this.channelType == 1) {
/* 641:641 */      return;
/* 642:    */    }
/* 643:643 */    AL10.alSourceRewind(this.ALSource.get(0));
/* 644:644 */    if (!checkALError()) {
/* 645:645 */      this.millisPreviouslyPlayed = 0.0F;
/* 646:    */    }
/* 647:    */  }
/* 648:    */  
/* 656:    */  public boolean playing()
/* 657:    */  {
/* 658:658 */    int state = AL10.alGetSourcei(this.ALSource.get(0), 
/* 659:659 */      4112);
/* 660:660 */    if (checkALError()) {
/* 661:661 */      return false;
/* 662:    */    }
/* 663:663 */    return state == 4114;
/* 664:    */  }
/* 665:    */  
/* 670:    */  private boolean checkALError()
/* 671:    */  {
/* 672:672 */    switch ()
/* 673:    */    {
/* 674:    */    case 0: 
/* 675:675 */      return false;
/* 676:    */    case 40961: 
/* 677:677 */      errorMessage("Invalid name parameter.");
/* 678:678 */      return true;
/* 679:    */    case 40962: 
/* 680:680 */      errorMessage("Invalid parameter.");
/* 681:681 */      return true;
/* 682:    */    case 40963: 
/* 683:683 */      errorMessage("Invalid enumerated parameter value.");
/* 684:684 */      return true;
/* 685:    */    case 40964: 
/* 686:686 */      errorMessage("Illegal call.");
/* 687:687 */      return true;
/* 688:    */    case 40965: 
/* 689:689 */      errorMessage("Unable to allocate memory.");
/* 690:690 */      return true;
/* 691:    */    }
/* 692:692 */    errorMessage("An unrecognized error occurred.");
/* 693:693 */    return true;
/* 694:    */  }
/* 695:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.libraries.ChannelLWJGLOpenAL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */