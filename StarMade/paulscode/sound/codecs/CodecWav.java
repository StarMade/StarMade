/*   1:    */package paulscode.sound.codecs;
/*   2:    */
/*   3:    */import java.io.BufferedInputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.net.URL;
/*   6:    */import java.nio.ByteBuffer;
/*   7:    */import java.nio.ByteOrder;
/*   8:    */import java.nio.ShortBuffer;
/*   9:    */import javax.sound.sampled.AudioFormat;
/*  10:    */import javax.sound.sampled.AudioInputStream;
/*  11:    */import javax.sound.sampled.AudioSystem;
/*  12:    */import javax.sound.sampled.UnsupportedAudioFileException;
/*  13:    */import paulscode.sound.ICodec;
/*  14:    */import paulscode.sound.SoundBuffer;
/*  15:    */import paulscode.sound.SoundSystemConfig;
/*  16:    */import paulscode.sound.SoundSystemLogger;
/*  17:    */
/*  70:    */public class CodecWav
/*  71:    */  implements ICodec
/*  72:    */{
/*  73:    */  private static final boolean GET = false;
/*  74:    */  private static final boolean SET = true;
/*  75:    */  private static final boolean XXX = false;
/*  76: 76 */  private boolean endOfStream = false;
/*  77:    */  
/*  81: 81 */  private boolean initialized = false;
/*  82:    */  
/*  86: 86 */  private AudioInputStream myAudioInputStream = null;
/*  87:    */  
/*  91:    */  private SoundSystemLogger logger;
/*  92:    */  
/*  97:    */  public void reverseByteOrder(boolean b) {}
/*  98:    */  
/* 103:    */  public CodecWav()
/* 104:    */  {
/* 105:105 */    this.logger = SoundSystemConfig.getLogger();
/* 106:    */  }
/* 107:    */  
/* 114:    */  public boolean initialize(URL url)
/* 115:    */  {
/* 116:116 */    initialized(true, false);
/* 117:117 */    cleanup();
/* 118:    */    
/* 119:119 */    if (url == null)
/* 120:    */    {
/* 121:121 */      errorMessage("url null in method 'initialize'");
/* 122:122 */      cleanup();
/* 123:123 */      return false;
/* 124:    */    }
/* 125:    */    
/* 126:    */    try
/* 127:    */    {
/* 128:128 */      this.myAudioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(url.openStream()));
/* 130:    */    }
/* 131:    */    catch (UnsupportedAudioFileException uafe)
/* 132:    */    {
/* 133:133 */      errorMessage("Unsupported audio format in method 'initialize'");
/* 134:134 */      printStackTrace(uafe);
/* 135:135 */      return false;
/* 136:    */    }
/* 137:    */    catch (IOException ioe)
/* 138:    */    {
/* 139:139 */      errorMessage("Error setting up audio input stream in method 'initialize'");
/* 140:    */      
/* 141:141 */      printStackTrace(ioe);
/* 142:142 */      return false;
/* 143:    */    }
/* 144:    */    
/* 145:145 */    endOfStream(true, false);
/* 146:146 */    initialized(true, true);
/* 147:147 */    return true;
/* 148:    */  }
/* 149:    */  
/* 154:    */  public boolean initialized()
/* 155:    */  {
/* 156:156 */    return initialized(false, false);
/* 157:    */  }
/* 158:    */  
/* 165:    */  public SoundBuffer read()
/* 166:    */  {
/* 167:167 */    if (this.myAudioInputStream == null) {
/* 168:168 */      return null;
/* 169:    */    }
/* 170:    */    
/* 171:171 */    AudioFormat audioFormat = this.myAudioInputStream.getFormat();
/* 172:    */    
/* 174:174 */    if (audioFormat == null)
/* 175:    */    {
/* 176:176 */      errorMessage("Audio Format null in method 'read'");
/* 177:177 */      return null;
/* 178:    */    }
/* 179:    */    
/* 181:181 */    int bytesRead = 0;int cnt = 0;
/* 182:    */    
/* 184:184 */    byte[] streamBuffer = new byte[SoundSystemConfig.getStreamingBufferSize()];
/* 185:    */    
/* 189:    */    try
/* 190:    */    {
/* 191:191 */      while ((!endOfStream(false, false)) && (bytesRead < streamBuffer.length))
/* 192:    */      {
/* 193:193 */        if ((cnt = this.myAudioInputStream.read(streamBuffer, bytesRead, streamBuffer.length - bytesRead)) <= 0)
/* 194:    */        {
/* 197:197 */          endOfStream(true, true);
/* 198:198 */          break;
/* 199:    */        }
/* 200:    */        
/* 201:201 */        bytesRead += cnt;
/* 202:    */      }
/* 203:    */      
/* 204:    */    }
/* 205:    */    catch (IOException ioe)
/* 206:    */    {
/* 207:207 */      endOfStream(true, true);
/* 208:208 */      return null;
/* 209:    */    }
/* 210:    */    
/* 212:212 */    if (bytesRead <= 0) {
/* 213:213 */      return null;
/* 214:    */    }
/* 215:    */    
/* 216:216 */    if (bytesRead < streamBuffer.length) {
/* 217:217 */      streamBuffer = trimArray(streamBuffer, bytesRead);
/* 218:    */    }
/* 219:    */    
/* 221:221 */    byte[] data = convertAudioBytes(streamBuffer, audioFormat.getSampleSizeInBits() == 16);
/* 222:    */    
/* 225:225 */    SoundBuffer buffer = new SoundBuffer(data, audioFormat);
/* 226:    */    
/* 228:228 */    return buffer;
/* 229:    */  }
/* 230:    */  
/* 239:    */  public SoundBuffer readAll()
/* 240:    */  {
/* 241:241 */    if (this.myAudioInputStream == null)
/* 242:    */    {
/* 243:243 */      errorMessage("Audio input stream null in method 'readAll'");
/* 244:244 */      return null;
/* 245:    */    }
/* 246:246 */    AudioFormat myAudioFormat = this.myAudioInputStream.getFormat();
/* 247:    */    
/* 249:249 */    if (myAudioFormat == null)
/* 250:    */    {
/* 251:251 */      errorMessage("Audio Format null in method 'readAll'");
/* 252:252 */      return null;
/* 253:    */    }
/* 254:    */    
/* 256:256 */    byte[] fullBuffer = null;
/* 257:    */    
/* 259:259 */    int fileSize = myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * myAudioFormat.getSampleSizeInBits() / 8;
/* 260:    */    
/* 262:262 */    if (fileSize > 0)
/* 263:    */    {
/* 265:265 */      fullBuffer = new byte[myAudioFormat.getChannels() * (int)this.myAudioInputStream.getFrameLength() * myAudioFormat.getSampleSizeInBits() / 8];
/* 266:    */      
/* 269:269 */      int read = 0;int total = 0;
/* 270:    */      
/* 274:    */      try
/* 275:    */      {
/* 276:276 */        while (((read = this.myAudioInputStream.read(fullBuffer, total, fullBuffer.length - total)) != -1) && (total < fullBuffer.length))
/* 277:    */        {
/* 278:278 */          total += read;
/* 279:    */        }
/* 280:    */      }
/* 281:    */      catch (IOException e)
/* 282:    */      {
/* 283:283 */        errorMessage("Exception thrown while reading from the AudioInputStream (location #1).");
/* 284:    */        
/* 285:285 */        printStackTrace(e);
/* 286:286 */        return null;
/* 287:    */      }
/* 288:    */      
/* 290:    */    }
/* 291:    */    else
/* 292:    */    {
/* 294:294 */      int totalBytes = 0;int bytesRead = 0;int cnt = 0;
/* 295:295 */      byte[] smallBuffer = null;
/* 296:    */      
/* 298:298 */      smallBuffer = new byte[SoundSystemConfig.getFileChunkSize()];
/* 299:    */      
/* 301:301 */      while ((!endOfStream(false, false)) && (totalBytes < SoundSystemConfig.getMaxFileSize()))
/* 302:    */      {
/* 304:304 */        bytesRead = 0;
/* 305:305 */        cnt = 0;
/* 306:    */        
/* 308:    */        try
/* 309:    */        {
/* 310:310 */          while (bytesRead < smallBuffer.length)
/* 311:    */          {
/* 312:312 */            if ((cnt = this.myAudioInputStream.read(smallBuffer, bytesRead, smallBuffer.length - bytesRead)) <= 0)
/* 313:    */            {
/* 316:316 */              endOfStream(true, true);
/* 317:317 */              break;
/* 318:    */            }
/* 319:319 */            bytesRead += cnt;
/* 320:    */          }
/* 321:    */        }
/* 322:    */        catch (IOException e)
/* 323:    */        {
/* 324:324 */          errorMessage("Exception thrown while reading from the AudioInputStream (location #2).");
/* 325:    */          
/* 326:326 */          printStackTrace(e);
/* 327:327 */          return null;
/* 328:    */        }
/* 329:    */        
/* 331:331 */        totalBytes += bytesRead;
/* 332:    */        
/* 334:334 */        fullBuffer = appendByteArrays(fullBuffer, smallBuffer, bytesRead);
/* 335:    */      }
/* 336:    */    }
/* 337:    */    
/* 340:340 */    byte[] data = convertAudioBytes(fullBuffer, myAudioFormat.getSampleSizeInBits() == 16);
/* 341:    */    
/* 344:344 */    SoundBuffer soundBuffer = new SoundBuffer(data, myAudioFormat);
/* 345:    */    
/* 347:    */    try
/* 348:    */    {
/* 349:349 */      this.myAudioInputStream.close();
/* 350:    */    }
/* 351:    */    catch (IOException e) {}
/* 352:    */    
/* 355:355 */    return soundBuffer;
/* 356:    */  }
/* 357:    */  
/* 362:    */  public boolean endOfStream()
/* 363:    */  {
/* 364:364 */    return endOfStream(false, false);
/* 365:    */  }
/* 366:    */  
/* 370:    */  public void cleanup()
/* 371:    */  {
/* 372:372 */    if (this.myAudioInputStream != null) {
/* 373:    */      try
/* 374:    */      {
/* 375:375 */        this.myAudioInputStream.close();
/* 376:    */      }
/* 377:    */      catch (Exception e) {}
/* 378:    */    }
/* 379:379 */    this.myAudioInputStream = null;
/* 380:    */  }
/* 381:    */  
/* 387:    */  public AudioFormat getAudioFormat()
/* 388:    */  {
/* 389:389 */    if (this.myAudioInputStream == null)
/* 390:390 */      return null;
/* 391:391 */    return this.myAudioInputStream.getFormat();
/* 392:    */  }
/* 393:    */  
/* 400:    */  private synchronized boolean initialized(boolean action, boolean value)
/* 401:    */  {
/* 402:402 */    if (action == true)
/* 403:403 */      this.initialized = value;
/* 404:404 */    return this.initialized;
/* 405:    */  }
/* 406:    */  
/* 413:    */  private synchronized boolean endOfStream(boolean action, boolean value)
/* 414:    */  {
/* 415:415 */    if (action == true)
/* 416:416 */      this.endOfStream = value;
/* 417:417 */    return this.endOfStream;
/* 418:    */  }
/* 419:    */  
/* 427:    */  private static byte[] trimArray(byte[] array, int maxLength)
/* 428:    */  {
/* 429:429 */    byte[] trimmedArray = null;
/* 430:430 */    if ((array != null) && (array.length > maxLength))
/* 431:    */    {
/* 432:432 */      trimmedArray = new byte[maxLength];
/* 433:433 */      System.arraycopy(array, 0, trimmedArray, 0, maxLength);
/* 434:    */    }
/* 435:435 */    return trimmedArray;
/* 436:    */  }
/* 437:    */  
/* 445:    */  private static byte[] convertAudioBytes(byte[] audio_bytes, boolean two_bytes_data)
/* 446:    */  {
/* 447:447 */    ByteBuffer dest = ByteBuffer.allocateDirect(audio_bytes.length);
/* 448:448 */    dest.order(ByteOrder.nativeOrder());
/* 449:449 */    ByteBuffer src = ByteBuffer.wrap(audio_bytes);
/* 450:450 */    src.order(ByteOrder.LITTLE_ENDIAN);
/* 451:451 */    if (two_bytes_data)
/* 452:    */    {
/* 453:453 */      ShortBuffer dest_short = dest.asShortBuffer();
/* 454:454 */      ShortBuffer src_short = src.asShortBuffer();
/* 455:455 */      while (src_short.hasRemaining())
/* 456:    */      {
/* 457:457 */        dest_short.put(src_short.get());
/* 458:    */      }
/* 459:    */    }
/* 460:    */    else
/* 461:    */    {
/* 462:462 */      while (src.hasRemaining())
/* 463:    */      {
/* 464:464 */        dest.put(src.get());
/* 465:    */      }
/* 466:    */    }
/* 467:467 */    dest.rewind();
/* 468:    */    
/* 469:469 */    if (!dest.hasArray())
/* 470:    */    {
/* 471:471 */      byte[] arrayBackedBuffer = new byte[dest.capacity()];
/* 472:472 */      dest.get(arrayBackedBuffer);
/* 473:473 */      dest.clear();
/* 474:    */      
/* 475:475 */      return arrayBackedBuffer;
/* 476:    */    }
/* 477:    */    
/* 478:478 */    return dest.array();
/* 479:    */  }
/* 480:    */  
/* 491:    */  private static byte[] appendByteArrays(byte[] arrayOne, byte[] arrayTwo, int length)
/* 492:    */  {
/* 493:493 */    if ((arrayOne == null) && (arrayTwo == null))
/* 494:    */    {
/* 496:496 */      return null; }
/* 497:    */    byte[] newArray;
/* 498:498 */    if (arrayOne == null)
/* 499:    */    {
/* 501:501 */      byte[] newArray = new byte[length];
/* 502:    */      
/* 503:503 */      System.arraycopy(arrayTwo, 0, newArray, 0, length);
/* 504:504 */      arrayTwo = null;
/* 505:    */    }
/* 506:506 */    else if (arrayTwo == null)
/* 507:    */    {
/* 509:509 */      byte[] newArray = new byte[arrayOne.length];
/* 510:    */      
/* 511:511 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 512:512 */      arrayOne = null;
/* 514:    */    }
/* 515:    */    else
/* 516:    */    {
/* 517:517 */      newArray = new byte[arrayOne.length + length];
/* 518:518 */      System.arraycopy(arrayOne, 0, newArray, 0, arrayOne.length);
/* 519:    */      
/* 520:520 */      System.arraycopy(arrayTwo, 0, newArray, arrayOne.length, length);
/* 521:    */      
/* 522:522 */      arrayOne = null;
/* 523:523 */      arrayTwo = null;
/* 524:    */    }
/* 525:    */    
/* 526:526 */    return newArray;
/* 527:    */  }
/* 528:    */  
/* 533:    */  private void errorMessage(String message)
/* 534:    */  {
/* 535:535 */    this.logger.errorMessage("CodecWav", message, 0);
/* 536:    */  }
/* 537:    */  
/* 542:    */  private void printStackTrace(Exception e)
/* 543:    */  {
/* 544:544 */    this.logger.printStackTrace(e, 1);
/* 545:    */  }
/* 546:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.codecs.CodecWav
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */