/*    1:     */package paulscode.sound.libraries;
/*    2:     */
/*    3:     */import java.net.URL;
/*    4:     */import java.nio.ByteBuffer;
/*    5:     */import java.nio.FloatBuffer;
/*    6:     */import java.nio.IntBuffer;
/*    7:     */import java.util.HashMap;
/*    8:     */import java.util.Iterator;
/*    9:     */import java.util.List;
/*   10:     */import java.util.Set;
/*   11:     */import javax.sound.sampled.AudioFormat;
/*   12:     */import org.lwjgl.BufferUtils;
/*   13:     */import org.lwjgl.LWJGLException;
/*   14:     */import org.lwjgl.openal.AL;
/*   15:     */import org.lwjgl.openal.AL10;
/*   16:     */import paulscode.sound.Channel;
/*   17:     */import paulscode.sound.FilenameURL;
/*   18:     */import paulscode.sound.ICodec;
/*   19:     */import paulscode.sound.Library;
/*   20:     */import paulscode.sound.ListenerData;
/*   21:     */import paulscode.sound.SoundBuffer;
/*   22:     */import paulscode.sound.SoundSystemConfig;
/*   23:     */import paulscode.sound.SoundSystemException;
/*   24:     */import paulscode.sound.Source;
/*   25:     */import paulscode.sound.Vector3D;
/*   26:     */
/*  116:     */public class LibraryLWJGLOpenAL
/*  117:     */  extends Library
/*  118:     */{
/*  119:     */  private static final boolean GET = false;
/*  120:     */  private static final boolean SET = true;
/*  121:     */  private static final boolean XXX = false;
/*  122: 122 */  private FloatBuffer listenerPositionAL = null;
/*  123:     */  
/*  126: 126 */  private FloatBuffer listenerOrientation = null;
/*  127:     */  
/*  130: 130 */  private FloatBuffer listenerVelocity = null;
/*  131:     */  
/*  134: 134 */  private HashMap<String, IntBuffer> ALBufferMap = null;
/*  135:     */  
/*  139: 139 */  private static boolean alPitchSupported = true;
/*  140:     */  
/*  146:     */  public LibraryLWJGLOpenAL()
/*  147:     */    throws SoundSystemException
/*  148:     */  {
/*  149: 149 */    this.ALBufferMap = new HashMap();
/*  150: 150 */    this.reverseByteOrder = true;
/*  151:     */  }
/*  152:     */  
/*  156:     */  public void init()
/*  157:     */    throws SoundSystemException
/*  158:     */  {
/*  159: 159 */    boolean errors = false;
/*  160:     */    
/*  162:     */    try
/*  163:     */    {
/*  164: 164 */      AL.create();
/*  165: 165 */      errors = checkALError();
/*  167:     */    }
/*  168:     */    catch (LWJGLException e)
/*  169:     */    {
/*  170: 170 */      errorMessage("Unable to initialize OpenAL.  Probable cause: OpenAL not supported.");
/*  171:     */      
/*  172: 172 */      printStackTrace(e);
/*  173: 173 */      throw new Exception(e.getMessage(), 
/*  174: 174 */        101);
/*  175:     */    }
/*  176:     */    
/*  178: 178 */    if (errors) {
/*  179: 179 */      importantMessage("OpenAL did not initialize properly!");
/*  180:     */    } else {
/*  181: 181 */      message("OpenAL initialized.");
/*  182:     */    }
/*  183:     */    
/*  184: 184 */    this.listenerPositionAL = BufferUtils.createFloatBuffer(3).put(
/*  185: 185 */      new float[] { this.listener.position.x, 
/*  186: 186 */      this.listener.position.y, 
/*  187: 187 */      this.listener.position.z });
/*  188: 188 */    this.listenerOrientation = BufferUtils.createFloatBuffer(6).put(
/*  189: 189 */      new float[] { this.listener.lookAt.x, this.listener.lookAt.y, 
/*  190: 190 */      this.listener.lookAt.z, this.listener.up.x, this.listener.up.y, 
/*  191: 191 */      this.listener.up.z });
/*  192: 192 */    this.listenerVelocity = BufferUtils.createFloatBuffer(3).put(
/*  193: 193 */      new float[] { 0.0F, 0.0F, 0.0F });
/*  194:     */    
/*  196: 196 */    this.listenerPositionAL.flip();
/*  197: 197 */    this.listenerOrientation.flip();
/*  198: 198 */    this.listenerVelocity.flip();
/*  199:     */    
/*  201: 201 */    AL10.alListener(4100, this.listenerPositionAL);
/*  202: 202 */    errors = (checkALError()) || (errors);
/*  203: 203 */    AL10.alListener(4111, this.listenerOrientation);
/*  204: 204 */    errors = (checkALError()) || (errors);
/*  205: 205 */    AL10.alListener(4102, this.listenerVelocity);
/*  206: 206 */    errors = (checkALError()) || (errors);
/*  207:     */    
/*  208: 208 */    AL10.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
/*  209: 209 */    errors = (checkALError()) || (errors);
/*  210:     */    
/*  211: 211 */    AL10.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
/*  212: 212 */    errors = (checkALError()) || (errors);
/*  213:     */    
/*  215: 215 */    if (errors)
/*  216:     */    {
/*  217: 217 */      importantMessage("OpenAL did not initialize properly!");
/*  218: 218 */      throw new Exception("Problem encountered while loading OpenAL or creating the listener.  Probable cause:  OpenAL not supported", 
/*  219:     */      
/*  223: 223 */        101);
/*  224:     */    }
/*  225:     */    
/*  226: 226 */    super.init();
/*  227:     */    
/*  229: 229 */    ChannelLWJGLOpenAL channel = 
/*  230: 230 */      (ChannelLWJGLOpenAL)this.normalChannels.get(1);
/*  231:     */    try
/*  232:     */    {
/*  233: 233 */      AL10.alSourcef(channel.ALSource.get(0), 
/*  234: 234 */        4099, 1.0F);
/*  235: 235 */      if (checkALError())
/*  236:     */      {
/*  237: 237 */        alPitchSupported(true, false);
/*  238: 238 */        throw new Exception("OpenAL: AL_PITCH not supported.", 
/*  239: 239 */          108);
/*  240:     */      }
/*  241:     */      
/*  243: 243 */      alPitchSupported(true, true);
/*  245:     */    }
/*  246:     */    catch (Exception e)
/*  247:     */    {
/*  248: 248 */      alPitchSupported(true, false);
/*  249: 249 */      throw new Exception("OpenAL: AL_PITCH not supported.", 
/*  250: 250 */        108);
/*  251:     */    }
/*  252:     */  }
/*  253:     */  
/*  258:     */  public static boolean libraryCompatible()
/*  259:     */  {
/*  260: 260 */    if (AL.isCreated()) {
/*  261: 261 */      return true;
/*  262:     */    }
/*  263:     */    try
/*  264:     */    {
/*  265: 265 */      AL.create();
/*  266:     */    }
/*  267:     */    catch (Exception e)
/*  268:     */    {
/*  269: 269 */      return false;
/*  270:     */    }
/*  271:     */    
/*  272:     */    try
/*  273:     */    {
/*  274: 274 */      AL.destroy();
/*  275:     */    }
/*  276:     */    catch (Exception localException1) {}
/*  277:     */    
/*  279: 279 */    return true;
/*  280:     */  }
/*  281:     */  
/*  292:     */  protected Channel createChannel(int type)
/*  293:     */  {
/*  294: 294 */    IntBuffer ALSource = BufferUtils.createIntBuffer(1);
/*  295:     */    try
/*  296:     */    {
/*  297: 297 */      AL10.alGenSources(ALSource);
/*  298:     */    }
/*  299:     */    catch (Exception e)
/*  300:     */    {
/*  301: 301 */      AL10.alGetError();
/*  302: 302 */      return null;
/*  303:     */    }
/*  304:     */    
/*  305: 305 */    if (AL10.alGetError() != 0) {
/*  306: 306 */      return null;
/*  307:     */    }
/*  308: 308 */    ChannelLWJGLOpenAL channel = new ChannelLWJGLOpenAL(type, ALSource);
/*  309: 309 */    return channel;
/*  310:     */  }
/*  311:     */  
/*  317:     */  public void cleanup()
/*  318:     */  {
/*  319: 319 */    super.cleanup();
/*  320:     */    
/*  321: 321 */    Set<String> keys = this.bufferMap.keySet();
/*  322: 322 */    Iterator<String> iter = keys.iterator();
/*  323:     */    
/*  327: 327 */    while (iter.hasNext())
/*  328:     */    {
/*  329: 329 */      String filename = (String)iter.next();
/*  330: 330 */      IntBuffer buffer = (IntBuffer)this.ALBufferMap.get(filename);
/*  331: 331 */      if (buffer != null)
/*  332:     */      {
/*  333: 333 */        AL10.alDeleteBuffers(buffer);
/*  334: 334 */        checkALError();
/*  335: 335 */        buffer.clear();
/*  336:     */      }
/*  337:     */    }
/*  338:     */    
/*  339: 339 */    this.bufferMap.clear();
/*  340: 340 */    AL.destroy();
/*  341:     */    
/*  342: 342 */    this.bufferMap = null;
/*  343: 343 */    this.listenerPositionAL = null;
/*  344: 344 */    this.listenerOrientation = null;
/*  345: 345 */    this.listenerVelocity = null;
/*  346:     */  }
/*  347:     */  
/*  355:     */  public boolean loadSound(FilenameURL filenameURL)
/*  356:     */  {
/*  357: 357 */    if (this.bufferMap == null)
/*  358:     */    {
/*  359: 359 */      this.bufferMap = new HashMap();
/*  360: 360 */      importantMessage("Buffer Map was null in method 'loadSound'");
/*  361:     */    }
/*  362:     */    
/*  363: 363 */    if (this.ALBufferMap == null)
/*  364:     */    {
/*  365: 365 */      this.ALBufferMap = new HashMap();
/*  366: 366 */      importantMessage("Open AL Buffer Map was null in method'loadSound'");
/*  367:     */    }
/*  368:     */    
/*  372: 372 */    if (errorCheck(filenameURL == null, "Filename/URL not specified in method 'loadSound'")) {
/*  373: 373 */      return false;
/*  374:     */    }
/*  375:     */    
/*  376: 376 */    if (this.bufferMap.get(filenameURL.getFilename()) != null) {
/*  377: 377 */      return true;
/*  378:     */    }
/*  379: 379 */    ICodec codec = SoundSystemConfig.getCodec(filenameURL.getFilename());
/*  380:     */    
/*  382: 382 */    if (errorCheck(codec == null, "No codec found for file '" + filenameURL.getFilename() + "' in method 'loadSound'"))
/*  383: 383 */      return false;
/*  384: 384 */    codec.reverseByteOrder(true);
/*  385:     */    
/*  386: 386 */    URL url = filenameURL.getURL();
/*  387:     */    
/*  389: 389 */    if (errorCheck(url == null, "Unable to open file '" + filenameURL.getFilename() + "' in method 'loadSound'")) {
/*  390: 390 */      return false;
/*  391:     */    }
/*  392: 392 */    codec.initialize(url);
/*  393: 393 */    SoundBuffer buffer = codec.readAll();
/*  394: 394 */    codec.cleanup();
/*  395: 395 */    codec = null;
/*  396:     */    
/*  397: 397 */    if (errorCheck(buffer == null, "Sound buffer null in method 'loadSound'")) {
/*  398: 398 */      return false;
/*  399:     */    }
/*  400: 400 */    this.bufferMap.put(filenameURL.getFilename(), buffer);
/*  401:     */    
/*  402: 402 */    AudioFormat audioFormat = buffer.audioFormat;
/*  403: 403 */    int soundFormat = 0;
/*  404: 404 */    if (audioFormat.getChannels() == 1)
/*  405:     */    {
/*  406: 406 */      if (audioFormat.getSampleSizeInBits() == 8)
/*  407:     */      {
/*  408: 408 */        soundFormat = 4352;
/*  409:     */      }
/*  410: 410 */      else if (audioFormat.getSampleSizeInBits() == 16)
/*  411:     */      {
/*  412: 412 */        soundFormat = 4353;
/*  413:     */      }
/*  414:     */      else
/*  415:     */      {
/*  416: 416 */        errorMessage("Illegal sample size in method 'loadSound'");
/*  417: 417 */        return false;
/*  418:     */      }
/*  419:     */    }
/*  420: 420 */    else if (audioFormat.getChannels() == 2)
/*  421:     */    {
/*  422: 422 */      if (audioFormat.getSampleSizeInBits() == 8)
/*  423:     */      {
/*  424: 424 */        soundFormat = 4354;
/*  425:     */      }
/*  426: 426 */      else if (audioFormat.getSampleSizeInBits() == 16)
/*  427:     */      {
/*  428: 428 */        soundFormat = 4355;
/*  429:     */      }
/*  430:     */      else
/*  431:     */      {
/*  432: 432 */        errorMessage("Illegal sample size in method 'loadSound'");
/*  433: 433 */        return false;
/*  434:     */      }
/*  435:     */    }
/*  436:     */    else
/*  437:     */    {
/*  438: 438 */      errorMessage("File neither mono nor stereo in method 'loadSound'");
/*  439:     */      
/*  440: 440 */      return false;
/*  441:     */    }
/*  442:     */    
/*  443: 443 */    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/*  444: 444 */    AL10.alGenBuffers(intBuffer);
/*  445:     */    
/*  447: 447 */    if (errorCheck(AL10.alGetError() != 0, "alGenBuffers error when loading " + filenameURL.getFilename())) {
/*  448: 448 */      return false;
/*  449:     */    }
/*  450:     */    
/*  453: 453 */    AL10.alBufferData(intBuffer.get(0), soundFormat, 
/*  454: 454 */      (ByteBuffer)BufferUtils.createByteBuffer(
/*  455: 455 */      buffer.audioData.length).put(
/*  456: 456 */      buffer.audioData).flip(), 
/*  457: 457 */      (int)audioFormat.getSampleRate());
/*  458:     */    
/*  461: 461 */    if (errorCheck(AL10.alGetError() != 0, "alBufferData error when loading " + filenameURL.getFilename()))
/*  462:     */    {
/*  466: 466 */      if (errorCheck(intBuffer == null, "Sound buffer was not created for " + filenameURL.getFilename()))
/*  467: 467 */        return false;
/*  468:     */    }
/*  469: 469 */    this.ALBufferMap.put(filenameURL.getFilename(), intBuffer);
/*  470:     */    
/*  471: 471 */    return true;
/*  472:     */  }
/*  473:     */  
/*  484:     */  public boolean loadSound(SoundBuffer buffer, String identifier)
/*  485:     */  {
/*  486: 486 */    if (this.bufferMap == null)
/*  487:     */    {
/*  488: 488 */      this.bufferMap = new HashMap();
/*  489: 489 */      importantMessage("Buffer Map was null in method 'loadSound'");
/*  490:     */    }
/*  491:     */    
/*  492: 492 */    if (this.ALBufferMap == null)
/*  493:     */    {
/*  494: 494 */      this.ALBufferMap = new HashMap();
/*  495: 495 */      importantMessage("Open AL Buffer Map was null in method'loadSound'");
/*  496:     */    }
/*  497:     */    
/*  501: 501 */    if (errorCheck(identifier == null, "Identifier not specified in method 'loadSound'")) {
/*  502: 502 */      return false;
/*  503:     */    }
/*  504:     */    
/*  505: 505 */    if (this.bufferMap.get(identifier) != null) {
/*  506: 506 */      return true;
/*  507:     */    }
/*  508:     */    
/*  509: 509 */    if (errorCheck(buffer == null, "Sound buffer null in method 'loadSound'")) {
/*  510: 510 */      return false;
/*  511:     */    }
/*  512: 512 */    this.bufferMap.put(identifier, buffer);
/*  513:     */    
/*  514: 514 */    AudioFormat audioFormat = buffer.audioFormat;
/*  515: 515 */    int soundFormat = 0;
/*  516: 516 */    if (audioFormat.getChannels() == 1)
/*  517:     */    {
/*  518: 518 */      if (audioFormat.getSampleSizeInBits() == 8)
/*  519:     */      {
/*  520: 520 */        soundFormat = 4352;
/*  521:     */      }
/*  522: 522 */      else if (audioFormat.getSampleSizeInBits() == 16)
/*  523:     */      {
/*  524: 524 */        soundFormat = 4353;
/*  525:     */      }
/*  526:     */      else
/*  527:     */      {
/*  528: 528 */        errorMessage("Illegal sample size in method 'loadSound'");
/*  529: 529 */        return false;
/*  530:     */      }
/*  531:     */    }
/*  532: 532 */    else if (audioFormat.getChannels() == 2)
/*  533:     */    {
/*  534: 534 */      if (audioFormat.getSampleSizeInBits() == 8)
/*  535:     */      {
/*  536: 536 */        soundFormat = 4354;
/*  537:     */      }
/*  538: 538 */      else if (audioFormat.getSampleSizeInBits() == 16)
/*  539:     */      {
/*  540: 540 */        soundFormat = 4355;
/*  541:     */      }
/*  542:     */      else
/*  543:     */      {
/*  544: 544 */        errorMessage("Illegal sample size in method 'loadSound'");
/*  545: 545 */        return false;
/*  546:     */      }
/*  547:     */    }
/*  548:     */    else
/*  549:     */    {
/*  550: 550 */      errorMessage("File neither mono nor stereo in method 'loadSound'");
/*  551:     */      
/*  552: 552 */      return false;
/*  553:     */    }
/*  554:     */    
/*  555: 555 */    IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
/*  556: 556 */    AL10.alGenBuffers(intBuffer);
/*  557:     */    
/*  559: 559 */    if (errorCheck(AL10.alGetError() != 0, "alGenBuffers error when saving " + identifier)) {
/*  560: 560 */      return false;
/*  561:     */    }
/*  562:     */    
/*  565: 565 */    AL10.alBufferData(intBuffer.get(0), soundFormat, 
/*  566: 566 */      (ByteBuffer)BufferUtils.createByteBuffer(
/*  567: 567 */      buffer.audioData.length).put(
/*  568: 568 */      buffer.audioData).flip(), 
/*  569: 569 */      (int)audioFormat.getSampleRate());
/*  570:     */    
/*  573: 573 */    if (errorCheck(AL10.alGetError() != 0, "alBufferData error when saving " + identifier))
/*  574:     */    {
/*  578: 578 */      if (errorCheck(intBuffer == null, "Sound buffer was not created for " + identifier))
/*  579: 579 */        return false;
/*  580:     */    }
/*  581: 581 */    this.ALBufferMap.put(identifier, intBuffer);
/*  582:     */    
/*  583: 583 */    return true;
/*  584:     */  }
/*  585:     */  
/*  594:     */  public void unloadSound(String filename)
/*  595:     */  {
/*  596: 596 */    this.ALBufferMap.remove(filename);
/*  597: 597 */    super.unloadSound(filename);
/*  598:     */  }
/*  599:     */  
/*  605:     */  public void setMasterVolume(float value)
/*  606:     */  {
/*  607: 607 */    super.setMasterVolume(value);
/*  608:     */    
/*  609: 609 */    AL10.alListenerf(4106, value);
/*  610: 610 */    checkALError();
/*  611:     */  }
/*  612:     */  
/*  629:     */  public void newSource(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distOrRoll)
/*  630:     */  {
/*  631: 631 */    IntBuffer myBuffer = null;
/*  632: 632 */    if (!toStream)
/*  633:     */    {
/*  635: 635 */      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*  636:     */      
/*  638: 638 */      if (myBuffer == null)
/*  639:     */      {
/*  640: 640 */        if (!loadSound(filenameURL))
/*  641:     */        {
/*  642: 642 */          errorMessage("Source '" + sourcename + "' was not created " + 
/*  643: 643 */            "because an error occurred while loading " + 
/*  644: 644 */            filenameURL.getFilename());
/*  645: 645 */          return;
/*  646:     */        }
/*  647:     */      }
/*  648:     */      
/*  650: 650 */      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*  651:     */      
/*  652: 652 */      if (myBuffer == null)
/*  653:     */      {
/*  654: 654 */        errorMessage("Source '" + sourcename + "' was not created " + 
/*  655: 655 */          "because a sound buffer was not found for " + 
/*  656: 656 */          filenameURL.getFilename());
/*  657: 657 */        return;
/*  658:     */      }
/*  659:     */    }
/*  660: 660 */    SoundBuffer buffer = null;
/*  661:     */    
/*  662: 662 */    if (!toStream)
/*  663:     */    {
/*  665: 665 */      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*  666:     */      
/*  667: 667 */      if (buffer == null)
/*  668:     */      {
/*  669: 669 */        if (!loadSound(filenameURL))
/*  670:     */        {
/*  671: 671 */          errorMessage("Source '" + sourcename + "' was not created " + 
/*  672: 672 */            "because an error occurred while loading " + 
/*  673: 673 */            filenameURL.getFilename());
/*  674: 674 */          return;
/*  675:     */        }
/*  676:     */      }
/*  677:     */      
/*  678: 678 */      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*  679:     */      
/*  680: 680 */      if (buffer == null)
/*  681:     */      {
/*  682: 682 */        errorMessage("Source '" + sourcename + "' was not created " + 
/*  683: 683 */          "because audio data was not found for " + 
/*  684: 684 */          filenameURL.getFilename());
/*  685: 685 */        return;
/*  686:     */      }
/*  687:     */    }
/*  688:     */    
/*  689: 689 */    this.sourceMap.put(sourcename, 
/*  690: 690 */      new SourceLWJGLOpenAL(this.listenerPositionAL, myBuffer, 
/*  691: 691 */      priority, toStream, toLoop, 
/*  692: 692 */      sourcename, filenameURL, buffer, x, 
/*  693: 693 */      y, z, attModel, distOrRoll, 
/*  694: 694 */      false));
/*  695:     */  }
/*  696:     */  
/*  711:     */  public void rawDataStream(AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
/*  712:     */  {
/*  713: 713 */    this.sourceMap.put(sourcename, 
/*  714: 714 */      new SourceLWJGLOpenAL(this.listenerPositionAL, audioFormat, 
/*  715: 715 */      priority, sourcename, x, y, z, 
/*  716: 716 */      attModel, distOrRoll));
/*  717:     */  }
/*  718:     */  
/*  737:     */  public void quickPlay(boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
/*  738:     */  {
/*  739: 739 */    IntBuffer myBuffer = null;
/*  740: 740 */    if (!toStream)
/*  741:     */    {
/*  743: 743 */      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*  744:     */      
/*  745: 745 */      if (myBuffer == null) {
/*  746: 746 */        loadSound(filenameURL);
/*  747:     */      }
/*  748: 748 */      myBuffer = (IntBuffer)this.ALBufferMap.get(filenameURL.getFilename());
/*  749:     */      
/*  750: 750 */      if (myBuffer == null)
/*  751:     */      {
/*  752: 752 */        errorMessage("Sound buffer was not created for " + 
/*  753: 753 */          filenameURL.getFilename());
/*  754: 754 */        return;
/*  755:     */      }
/*  756:     */    }
/*  757:     */    
/*  758: 758 */    SoundBuffer buffer = null;
/*  759:     */    
/*  760: 760 */    if (!toStream)
/*  761:     */    {
/*  763: 763 */      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*  764:     */      
/*  765: 765 */      if (buffer == null)
/*  766:     */      {
/*  767: 767 */        if (!loadSound(filenameURL))
/*  768:     */        {
/*  769: 769 */          errorMessage("Source '" + sourcename + "' was not created " + 
/*  770: 770 */            "because an error occurred while loading " + 
/*  771: 771 */            filenameURL.getFilename());
/*  772: 772 */          return;
/*  773:     */        }
/*  774:     */      }
/*  775:     */      
/*  776: 776 */      buffer = (SoundBuffer)this.bufferMap.get(filenameURL.getFilename());
/*  777:     */      
/*  778: 778 */      if (buffer == null)
/*  779:     */      {
/*  780: 780 */        errorMessage("Source '" + sourcename + "' was not created " + 
/*  781: 781 */          "because audio data was not found for " + 
/*  782: 782 */          filenameURL.getFilename());
/*  783: 783 */        return;
/*  784:     */      }
/*  785:     */    }
/*  786: 786 */    SourceLWJGLOpenAL s = new SourceLWJGLOpenAL(this.listenerPositionAL, 
/*  787: 787 */      myBuffer, priority, 
/*  788: 788 */      toStream, toLoop, 
/*  789: 789 */      sourcename, filenameURL, 
/*  790: 790 */      buffer, x, y, z, 
/*  791: 791 */      attModel, distOrRoll, 
/*  792: 792 */      false);
/*  793:     */    
/*  794: 794 */    this.sourceMap.put(sourcename, s);
/*  795: 795 */    play(s);
/*  796: 796 */    if (temporary) {
/*  797: 797 */      s.setTemporary(true);
/*  798:     */    }
/*  799:     */  }
/*  800:     */  
/*  805:     */  public void copySources(HashMap<String, Source> srcMap)
/*  806:     */  {
/*  807: 807 */    if (srcMap == null)
/*  808: 808 */      return;
/*  809: 809 */    Set<String> keys = srcMap.keySet();
/*  810: 810 */    Iterator<String> iter = keys.iterator();
/*  811:     */    
/*  815: 815 */    if (this.bufferMap == null)
/*  816:     */    {
/*  817: 817 */      this.bufferMap = new HashMap();
/*  818: 818 */      importantMessage("Buffer Map was null in method 'copySources'");
/*  819:     */    }
/*  820:     */    
/*  821: 821 */    if (this.ALBufferMap == null)
/*  822:     */    {
/*  823: 823 */      this.ALBufferMap = new HashMap();
/*  824: 824 */      importantMessage("Open AL Buffer Map was null in method'copySources'");
/*  825:     */    }
/*  826:     */    
/*  829: 829 */    this.sourceMap.clear();
/*  830:     */    
/*  833: 833 */    while (iter.hasNext())
/*  834:     */    {
/*  835: 835 */      String sourcename = (String)iter.next();
/*  836: 836 */      Source source = (Source)srcMap.get(sourcename);
/*  837: 837 */      if (source != null)
/*  838:     */      {
/*  839: 839 */        SoundBuffer buffer = null;
/*  840: 840 */        if (!source.toStream)
/*  841:     */        {
/*  842: 842 */          loadSound(source.filenameURL);
/*  843: 843 */          buffer = (SoundBuffer)this.bufferMap.get(source.filenameURL.getFilename());
/*  844:     */        }
/*  845: 845 */        if ((source.toStream) || (buffer != null)) {
/*  846: 846 */          this.sourceMap.put(sourcename, new SourceLWJGLOpenAL(
/*  847: 847 */            this.listenerPositionAL, 
/*  848: 848 */            (IntBuffer)this.ALBufferMap.get(
/*  849: 849 */            source.filenameURL.getFilename()), 
/*  850: 850 */            source, buffer));
/*  851:     */        }
/*  852:     */      }
/*  853:     */    }
/*  854:     */  }
/*  855:     */  
/*  862:     */  public void setListenerPosition(float x, float y, float z)
/*  863:     */  {
/*  864: 864 */    super.setListenerPosition(x, y, z);
/*  865:     */    
/*  866: 866 */    this.listenerPositionAL.put(0, x);
/*  867: 867 */    this.listenerPositionAL.put(1, y);
/*  868: 868 */    this.listenerPositionAL.put(2, z);
/*  869:     */    
/*  871: 871 */    AL10.alListener(4100, this.listenerPositionAL);
/*  872:     */    
/*  873: 873 */    checkALError();
/*  874:     */  }
/*  875:     */  
/*  882:     */  public void setListenerAngle(float angle)
/*  883:     */  {
/*  884: 884 */    super.setListenerAngle(angle);
/*  885:     */    
/*  886: 886 */    this.listenerOrientation.put(0, this.listener.lookAt.x);
/*  887: 887 */    this.listenerOrientation.put(2, this.listener.lookAt.z);
/*  888:     */    
/*  890: 890 */    AL10.alListener(4111, this.listenerOrientation);
/*  891:     */    
/*  892: 892 */    checkALError();
/*  893:     */  }
/*  894:     */  
/*  906:     */  public void setListenerOrientation(float lookX, float lookY, float lookZ, float upX, float upY, float upZ)
/*  907:     */  {
/*  908: 908 */    super.setListenerOrientation(lookX, lookY, lookZ, upX, upY, upZ);
/*  909: 909 */    this.listenerOrientation.put(0, lookX);
/*  910: 910 */    this.listenerOrientation.put(1, lookY);
/*  911: 911 */    this.listenerOrientation.put(2, lookZ);
/*  912: 912 */    this.listenerOrientation.put(3, upX);
/*  913: 913 */    this.listenerOrientation.put(4, upY);
/*  914: 914 */    this.listenerOrientation.put(5, upZ);
/*  915: 915 */    AL10.alListener(4111, this.listenerOrientation);
/*  916: 916 */    checkALError();
/*  917:     */  }
/*  918:     */  
/*  925:     */  public void setListenerData(ListenerData l)
/*  926:     */  {
/*  927: 927 */    super.setListenerData(l);
/*  928:     */    
/*  929: 929 */    this.listenerPositionAL.put(0, l.position.x);
/*  930: 930 */    this.listenerPositionAL.put(1, l.position.y);
/*  931: 931 */    this.listenerPositionAL.put(2, l.position.z);
/*  932: 932 */    AL10.alListener(4100, this.listenerPositionAL);
/*  933: 933 */    checkALError();
/*  934:     */    
/*  935: 935 */    this.listenerOrientation.put(0, l.lookAt.x);
/*  936: 936 */    this.listenerOrientation.put(1, l.lookAt.y);
/*  937: 937 */    this.listenerOrientation.put(2, l.lookAt.z);
/*  938: 938 */    this.listenerOrientation.put(3, l.up.x);
/*  939: 939 */    this.listenerOrientation.put(4, l.up.y);
/*  940: 940 */    this.listenerOrientation.put(5, l.up.z);
/*  941: 941 */    AL10.alListener(4111, this.listenerOrientation);
/*  942: 942 */    checkALError();
/*  943:     */    
/*  944: 944 */    this.listenerVelocity.put(0, l.velocity.x);
/*  945: 945 */    this.listenerVelocity.put(1, l.velocity.y);
/*  946: 946 */    this.listenerVelocity.put(2, l.velocity.z);
/*  947: 947 */    AL10.alListener(4102, this.listenerVelocity);
/*  948: 948 */    checkALError();
/*  949:     */  }
/*  950:     */  
/*  958:     */  public void setListenerVelocity(float x, float y, float z)
/*  959:     */  {
/*  960: 960 */    super.setListenerVelocity(x, y, z);
/*  961:     */    
/*  962: 962 */    this.listenerVelocity.put(0, this.listener.velocity.x);
/*  963: 963 */    this.listenerVelocity.put(1, this.listener.velocity.y);
/*  964: 964 */    this.listenerVelocity.put(2, this.listener.velocity.z);
/*  965: 965 */    AL10.alListener(4102, this.listenerVelocity);
/*  966:     */  }
/*  967:     */  
/*  972:     */  public void dopplerChanged()
/*  973:     */  {
/*  974: 974 */    super.dopplerChanged();
/*  975:     */    
/*  976: 976 */    AL10.alDopplerFactor(SoundSystemConfig.getDopplerFactor());
/*  977: 977 */    checkALError();
/*  978: 978 */    AL10.alDopplerVelocity(SoundSystemConfig.getDopplerVelocity());
/*  979: 979 */    checkALError();
/*  980:     */  }
/*  981:     */  
/*  986:     */  private boolean checkALError()
/*  987:     */  {
/*  988: 988 */    switch ()
/*  989:     */    {
/*  990:     */    case 0: 
/*  991: 991 */      return false;
/*  992:     */    case 40961: 
/*  993: 993 */      errorMessage("Invalid name parameter.");
/*  994: 994 */      return true;
/*  995:     */    case 40962: 
/*  996: 996 */      errorMessage("Invalid parameter.");
/*  997: 997 */      return true;
/*  998:     */    case 40963: 
/*  999: 999 */      errorMessage("Invalid enumerated parameter value.");
/* 1000:1000 */      return true;
/* 1001:     */    case 40964: 
/* 1002:1002 */      errorMessage("Illegal call.");
/* 1003:1003 */      return true;
/* 1004:     */    case 40965: 
/* 1005:1005 */      errorMessage("Unable to allocate memory.");
/* 1006:1006 */      return true;
/* 1007:     */    }
/* 1008:1008 */    errorMessage("An unrecognized error occurred.");
/* 1009:1009 */    return true;
/* 1010:     */  }
/* 1011:     */  
/* 1017:     */  public static boolean alPitchSupported()
/* 1018:     */  {
/* 1019:1019 */    return alPitchSupported(false, false);
/* 1020:     */  }
/* 1021:     */  
/* 1028:     */  private static synchronized boolean alPitchSupported(boolean action, boolean value)
/* 1029:     */  {
/* 1030:1030 */    if (action)
/* 1031:1031 */      alPitchSupported = value;
/* 1032:1032 */    return alPitchSupported;
/* 1033:     */  }
/* 1034:     */  
/* 1039:     */  public static String getTitle()
/* 1040:     */  {
/* 1041:1041 */    return "LWJGL OpenAL";
/* 1042:     */  }
/* 1043:     */  
/* 1048:     */  public static String getDescription()
/* 1049:     */  {
/* 1050:1050 */    return "The LWJGL binding of OpenAL.  For more information, see http://www.lwjgl.org";
/* 1051:     */  }
/* 1052:     */  
/* 1059:     */  public String getClassName()
/* 1060:     */  {
/* 1061:1061 */    return "LibraryLWJGLOpenAL";
/* 1062:     */  }
/* 1063:     */  
/* 1068:     */  public static class Exception
/* 1069:     */    extends SoundSystemException
/* 1070:     */  {
/* 1071:     */    private static final long serialVersionUID = -7502452059037798035L;
/* 1072:     */    
/* 1076:     */    public static final int CREATE = 101;
/* 1077:     */    
/* 1081:     */    public static final int INVALID_NAME = 102;
/* 1082:     */    
/* 1085:     */    public static final int INVALID_ENUM = 103;
/* 1086:     */    
/* 1089:     */    public static final int INVALID_VALUE = 104;
/* 1090:     */    
/* 1093:     */    public static final int INVALID_OPERATION = 105;
/* 1094:     */    
/* 1097:     */    public static final int OUT_OF_MEMORY = 106;
/* 1098:     */    
/* 1101:     */    public static final int LISTENER = 107;
/* 1102:     */    
/* 1105:     */    public static final int NO_AL_PITCH = 108;
/* 1106:     */    
/* 1110:     */    public Exception(String message)
/* 1111:     */    {
/* 1112:1112 */      super();
/* 1113:     */    }
/* 1114:     */    
/* 1121:     */    public Exception(String message, int type)
/* 1122:     */    {
/* 1123:1123 */      super(type);
/* 1124:     */    }
/* 1125:     */  }
/* 1126:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.libraries.LibraryLWJGLOpenAL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */