/*   1:    */package org.newdawn.slick.openal;
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
/*  13:    */import java.nio.ByteBuffer;
/*  14:    */import java.nio.ByteOrder;
/*  15:    */import org.lwjgl.BufferUtils;
/*  16:    */import org.newdawn.slick.util.Log;
/*  17:    */
/*  25:    */public class OggInputStream
/*  26:    */  extends InputStream
/*  27:    */  implements AudioInputStream
/*  28:    */{
/*  29: 29 */  private int convsize = 16384;
/*  30:    */  
/*  31: 31 */  private byte[] convbuffer = new byte[this.convsize];
/*  32:    */  
/*  33:    */  private InputStream input;
/*  34:    */  
/*  35: 35 */  private Info oggInfo = new Info();
/*  36:    */  
/*  38:    */  private boolean endOfStream;
/*  39:    */  
/*  40: 40 */  private SyncState syncState = new SyncState();
/*  41:    */  
/*  42: 42 */  private StreamState streamState = new StreamState();
/*  43:    */  
/*  44: 44 */  private Page page = new Page();
/*  45:    */  
/*  46: 46 */  private Packet packet = new Packet();
/*  47:    */  
/*  49: 49 */  private Comment comment = new Comment();
/*  50:    */  
/*  51: 51 */  private DspState dspState = new DspState();
/*  52:    */  
/*  53: 53 */  private Block vorbisBlock = new Block(this.dspState);
/*  54:    */  
/*  56:    */  byte[] buffer;
/*  57:    */  
/*  58: 58 */  int bytes = 0;
/*  59:    */  
/*  60: 60 */  boolean bigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
/*  61:    */  
/*  62: 62 */  boolean endOfBitStream = true;
/*  63:    */  
/*  64: 64 */  boolean inited = false;
/*  65:    */  
/*  67:    */  private int readIndex;
/*  68:    */  
/*  69: 69 */  private ByteBuffer pcmBuffer = BufferUtils.createByteBuffer(2048000);
/*  70:    */  
/*  73:    */  private int total;
/*  74:    */  
/*  77:    */  public OggInputStream(InputStream input)
/*  78:    */    throws IOException
/*  79:    */  {
/*  80: 80 */    this.input = input;
/*  81: 81 */    this.total = input.available();
/*  82:    */    
/*  83: 83 */    init();
/*  84:    */  }
/*  85:    */  
/*  90:    */  public int getLength()
/*  91:    */  {
/*  92: 92 */    return this.total;
/*  93:    */  }
/*  94:    */  
/*  97:    */  public int getChannels()
/*  98:    */  {
/*  99: 99 */    return this.oggInfo.channels;
/* 100:    */  }
/* 101:    */  
/* 104:    */  public int getRate()
/* 105:    */  {
/* 106:106 */    return this.oggInfo.rate;
/* 107:    */  }
/* 108:    */  
/* 112:    */  private void init()
/* 113:    */    throws IOException
/* 114:    */  {
/* 115:115 */    initVorbis();
/* 116:116 */    readPCM();
/* 117:    */  }
/* 118:    */  
/* 121:    */  public int available()
/* 122:    */  {
/* 123:123 */    return this.endOfStream ? 0 : 1;
/* 124:    */  }
/* 125:    */  
/* 128:    */  private void initVorbis()
/* 129:    */  {
/* 130:130 */    this.syncState.init();
/* 131:    */  }
/* 132:    */  
/* 143:    */  private boolean getPageAndPacket()
/* 144:    */  {
/* 145:145 */    int index = this.syncState.buffer(4096);
/* 146:    */    
/* 147:147 */    this.buffer = this.syncState.data;
/* 148:148 */    if (this.buffer == null) {
/* 149:149 */      this.endOfStream = true;
/* 150:150 */      return false;
/* 151:    */    }
/* 152:    */    try
/* 153:    */    {
/* 154:154 */      this.bytes = this.input.read(this.buffer, index, 4096);
/* 155:    */    } catch (Exception e) {
/* 156:156 */      Log.error("Failure reading in vorbis");
/* 157:157 */      Log.error(e);
/* 158:158 */      this.endOfStream = true;
/* 159:159 */      return false;
/* 160:    */    }
/* 161:161 */    this.syncState.wrote(this.bytes);
/* 162:    */    
/* 164:164 */    if (this.syncState.pageout(this.page) != 1)
/* 165:    */    {
/* 166:166 */      if (this.bytes < 4096) {
/* 167:167 */        return false;
/* 168:    */      }
/* 169:    */      
/* 170:170 */      Log.error("Input does not appear to be an Ogg bitstream.");
/* 171:171 */      this.endOfStream = true;
/* 172:172 */      return false;
/* 173:    */    }
/* 174:    */    
/* 177:177 */    this.streamState.init(this.page.serialno());
/* 178:    */    
/* 187:187 */    this.oggInfo.init();
/* 188:188 */    this.comment.init();
/* 189:189 */    if (this.streamState.pagein(this.page) < 0)
/* 190:    */    {
/* 191:191 */      Log.error("Error reading first page of Ogg bitstream data.");
/* 192:192 */      this.endOfStream = true;
/* 193:193 */      return false;
/* 194:    */    }
/* 195:    */    
/* 196:196 */    if (this.streamState.packetout(this.packet) != 1)
/* 197:    */    {
/* 198:198 */      Log.error("Error reading initial header packet.");
/* 199:199 */      this.endOfStream = true;
/* 200:200 */      return false;
/* 201:    */    }
/* 202:    */    
/* 203:203 */    if (this.oggInfo.synthesis_headerin(this.comment, this.packet) < 0)
/* 204:    */    {
/* 205:205 */      Log.error("This Ogg bitstream does not contain Vorbis audio data.");
/* 206:206 */      this.endOfStream = true;
/* 207:207 */      return false;
/* 208:    */    }
/* 209:    */    
/* 220:220 */    int i = 0;
/* 221:221 */    while (i < 2) {
/* 222:222 */      while (i < 2)
/* 223:    */      {
/* 224:224 */        int result = this.syncState.pageout(this.page);
/* 225:225 */        if (result == 0) {
/* 226:    */          break;
/* 227:    */        }
/* 228:    */        
/* 230:230 */        if (result == 1) {
/* 231:231 */          this.streamState.pagein(this.page);
/* 232:    */          
/* 234:234 */          while (i < 2) {
/* 235:235 */            result = this.streamState.packetout(this.packet);
/* 236:236 */            if (result == 0)
/* 237:    */              break;
/* 238:238 */            if (result == -1)
/* 239:    */            {
/* 241:241 */              Log.error("Corrupt secondary header.  Exiting.");
/* 242:242 */              this.endOfStream = true;
/* 243:243 */              return false;
/* 244:    */            }
/* 245:    */            
/* 246:246 */            this.oggInfo.synthesis_headerin(this.comment, this.packet);
/* 247:247 */            i++;
/* 248:    */          }
/* 249:    */        }
/* 250:    */      }
/* 251:    */      
/* 252:252 */      index = this.syncState.buffer(4096);
/* 253:253 */      this.buffer = this.syncState.data;
/* 254:    */      try {
/* 255:255 */        this.bytes = this.input.read(this.buffer, index, 4096);
/* 256:    */      } catch (Exception e) {
/* 257:257 */        Log.error("Failed to read Vorbis: ");
/* 258:258 */        Log.error(e);
/* 259:259 */        this.endOfStream = true;
/* 260:260 */        return false;
/* 261:    */      }
/* 262:262 */      if ((this.bytes == 0) && (i < 2)) {
/* 263:263 */        Log.error("End of file before finding all Vorbis headers!");
/* 264:264 */        this.endOfStream = true;
/* 265:265 */        return false;
/* 266:    */      }
/* 267:267 */      this.syncState.wrote(this.bytes);
/* 268:    */    }
/* 269:    */    
/* 270:270 */    this.convsize = (4096 / this.oggInfo.channels);
/* 271:    */    
/* 274:274 */    this.dspState.synthesis_init(this.oggInfo);
/* 275:275 */    this.vorbisBlock.init(this.dspState);
/* 276:    */    
/* 281:281 */    return true;
/* 282:    */  }
/* 283:    */  
/* 287:    */  private void readPCM()
/* 288:    */    throws IOException
/* 289:    */  {
/* 290:290 */    boolean wrote = false;
/* 291:    */    for (;;)
/* 292:    */    {
/* 293:293 */      if (this.endOfBitStream) {
/* 294:294 */        if (!getPageAndPacket()) {
/* 295:    */          break;
/* 296:    */        }
/* 297:297 */        this.endOfBitStream = false;
/* 298:    */      }
/* 299:    */      
/* 300:300 */      if (!this.inited) {
/* 301:301 */        this.inited = true;
/* 302:302 */        return;
/* 303:    */      }
/* 304:    */      
/* 305:305 */      float[][][] _pcm = new float[1][][];
/* 306:306 */      int[] _index = new int[this.oggInfo.channels];
/* 307:    */      
/* 308:308 */      while (!this.endOfBitStream) {
/* 309:309 */        while (!this.endOfBitStream) {
/* 310:310 */          int result = this.syncState.pageout(this.page);
/* 311:    */          
/* 312:312 */          if (result == 0) {
/* 313:    */            break;
/* 314:    */          }
/* 315:    */          
/* 316:316 */          if (result == -1) {
/* 317:317 */            Log.error("Corrupt or missing data in bitstream; continuing...");
/* 318:    */          } else {
/* 319:319 */            this.streamState.pagein(this.page);
/* 320:    */            for (;;)
/* 321:    */            {
/* 322:322 */              result = this.streamState.packetout(this.packet);
/* 323:    */              
/* 324:324 */              if (result == 0)
/* 325:    */                break;
/* 326:326 */              if (result != -1)
/* 327:    */              {
/* 331:331 */                if (this.vorbisBlock.synthesis(this.packet) == 0) {
/* 332:332 */                  this.dspState.synthesis_blockin(this.vorbisBlock);
/* 333:    */                }
/* 334:    */                
/* 337:    */                int samples;
/* 338:    */                
/* 341:341 */                while ((samples = this.dspState.synthesis_pcmout(_pcm, _index)) > 0) {
/* 342:342 */                  float[][] pcm = _pcm[0];
/* 343:    */                  
/* 344:344 */                  int bout = samples < this.convsize ? samples : this.convsize;
/* 345:    */                  
/* 349:349 */                  for (int i = 0; i < this.oggInfo.channels; i++) {
/* 350:350 */                    int ptr = i * 2;
/* 351:    */                    
/* 352:352 */                    int mono = _index[i];
/* 353:353 */                    for (int j = 0; j < bout; j++) {
/* 354:354 */                      int val = (int)(pcm[i][(mono + j)] * 32767.0D);
/* 355:    */                      
/* 356:356 */                      if (val > 32767) {
/* 357:357 */                        val = 32767;
/* 358:    */                      }
/* 359:359 */                      if (val < -32768) {
/* 360:360 */                        val = -32768;
/* 361:    */                      }
/* 362:362 */                      if (val < 0) {
/* 363:363 */                        val |= 32768;
/* 364:    */                      }
/* 365:365 */                      if (this.bigEndian) {
/* 366:366 */                        this.convbuffer[ptr] = ((byte)(val >>> 8));
/* 367:367 */                        this.convbuffer[(ptr + 1)] = ((byte)val);
/* 368:    */                      } else {
/* 369:369 */                        this.convbuffer[ptr] = ((byte)val);
/* 370:370 */                        this.convbuffer[(ptr + 1)] = ((byte)(val >>> 8));
/* 371:    */                      }
/* 372:372 */                      ptr += 2 * this.oggInfo.channels;
/* 373:    */                    }
/* 374:    */                  }
/* 375:    */                  
/* 376:376 */                  int bytesToWrite = 2 * this.oggInfo.channels * bout;
/* 377:377 */                  if (bytesToWrite >= this.pcmBuffer.remaining()) {
/* 378:378 */                    Log.warn("Read block from OGG that was too big to be buffered: " + bytesToWrite);
/* 379:    */                  } else {
/* 380:380 */                    this.pcmBuffer.put(this.convbuffer, 0, bytesToWrite);
/* 381:    */                  }
/* 382:    */                  
/* 383:383 */                  wrote = true;
/* 384:384 */                  this.dspState.synthesis_read(bout);
/* 385:    */                }
/* 386:    */              }
/* 387:    */            }
/* 388:    */            
/* 390:390 */            if (this.page.eos() != 0) {
/* 391:391 */              this.endOfBitStream = true;
/* 392:    */            }
/* 393:    */            
/* 394:394 */            if ((!this.endOfBitStream) && (wrote)) {
/* 395:395 */              return;
/* 396:    */            }
/* 397:    */          }
/* 398:    */        }
/* 399:    */        
/* 400:400 */        if (!this.endOfBitStream) {
/* 401:401 */          this.bytes = 0;
/* 402:402 */          int index = this.syncState.buffer(4096);
/* 403:403 */          if (index >= 0) {
/* 404:404 */            this.buffer = this.syncState.data;
/* 405:    */            try {
/* 406:406 */              this.bytes = this.input.read(this.buffer, index, 4096);
/* 407:    */            } catch (Exception e) {
/* 408:408 */              Log.error("Failure during vorbis decoding");
/* 409:409 */              Log.error(e);
/* 410:410 */              this.endOfStream = true;
/* 411:411 */              return;
/* 412:    */            }
/* 413:    */          } else {
/* 414:414 */            this.bytes = 0;
/* 415:    */          }
/* 416:416 */          this.syncState.wrote(this.bytes);
/* 417:417 */          if (this.bytes == 0) {
/* 418:418 */            this.endOfBitStream = true;
/* 419:    */          }
/* 420:    */        }
/* 421:    */      }
/* 422:    */      
/* 425:425 */      this.streamState.clear();
/* 426:    */      
/* 430:430 */      this.vorbisBlock.clear();
/* 431:431 */      this.dspState.clear();
/* 432:432 */      this.oggInfo.clear();
/* 433:    */    }
/* 434:    */    
/* 436:436 */    this.syncState.clear();
/* 437:437 */    this.endOfStream = true;
/* 438:    */  }
/* 439:    */  
/* 441:    */  public int read()
/* 442:    */    throws IOException
/* 443:    */  {
/* 444:444 */    if (this.readIndex >= this.pcmBuffer.position()) {
/* 445:445 */      this.pcmBuffer.clear();
/* 446:446 */      readPCM();
/* 447:447 */      this.readIndex = 0;
/* 448:    */    }
/* 449:449 */    if (this.readIndex >= this.pcmBuffer.position()) {
/* 450:450 */      return -1;
/* 451:    */    }
/* 452:    */    
/* 453:453 */    int value = this.pcmBuffer.get(this.readIndex);
/* 454:454 */    if (value < 0) {
/* 455:455 */      value = 256 + value;
/* 456:    */    }
/* 457:457 */    this.readIndex += 1;
/* 458:    */    
/* 459:459 */    return value;
/* 460:    */  }
/* 461:    */  
/* 464:    */  public boolean atEnd()
/* 465:    */  {
/* 466:466 */    return (this.endOfStream) && (this.readIndex >= this.pcmBuffer.position());
/* 467:    */  }
/* 468:    */  
/* 470:    */  public int read(byte[] b, int off, int len)
/* 471:    */    throws IOException
/* 472:    */  {
/* 473:473 */    for (int i = 0; i < len; i++) {
/* 474:    */      try {
/* 475:475 */        int value = read();
/* 476:476 */        if (value >= 0) {
/* 477:477 */          b[i] = ((byte)value);
/* 478:    */        } else {
/* 479:479 */          if (i == 0) {
/* 480:480 */            return -1;
/* 481:    */          }
/* 482:482 */          return i;
/* 483:    */        }
/* 484:    */      }
/* 485:    */      catch (IOException e) {
/* 486:486 */        Log.error(e);
/* 487:487 */        return i;
/* 488:    */      }
/* 489:    */    }
/* 490:    */    
/* 491:491 */    return len;
/* 492:    */  }
/* 493:    */  
/* 495:    */  public int read(byte[] b)
/* 496:    */    throws IOException
/* 497:    */  {
/* 498:498 */    return read(b, 0, b.length);
/* 499:    */  }
/* 500:    */  
/* 501:    */  public void close()
/* 502:    */    throws IOException
/* 503:    */  {}
/* 504:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.openal.OggInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */