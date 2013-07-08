/*   1:    */package it.unimi.dsi.fastutil.io;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */import java.lang.reflect.InvocationTargetException;
/*   7:    */import java.lang.reflect.Method;
/*   8:    */import java.nio.channels.FileChannel;
/*   9:    */import java.util.EnumSet;
/*  10:    */
/*  85:    */public class FastBufferedInputStream
/*  86:    */  extends MeasurableInputStream
/*  87:    */  implements RepositionableStream
/*  88:    */{
/*  89:    */  public static final int DEFAULT_BUFFER_SIZE = 8192;
/*  90:    */  
/*  91:    */  public static enum LineTerminator
/*  92:    */  {
/*  93: 93 */    CR, 
/*  94:    */    
/*  95: 95 */    LF, 
/*  96:    */    
/*  97: 97 */    CR_LF;
/*  98:    */    
/*  99:    */    private LineTerminator() {} }
/* 100:    */  
/* 101:101 */  public static final EnumSet<LineTerminator> ALL_TERMINATORS = EnumSet.allOf(LineTerminator.class);
/* 102:    */  
/* 105:    */  protected InputStream is;
/* 106:    */  
/* 109:    */  protected byte[] buffer;
/* 110:    */  
/* 113:    */  protected int pos;
/* 114:    */  
/* 117:    */  protected long readBytes;
/* 118:    */  
/* 121:    */  protected int avail;
/* 122:    */  
/* 124:    */  private FileChannel fileChannel;
/* 125:    */  
/* 127:    */  private RepositionableStream repositionableStream;
/* 128:    */  
/* 130:    */  private MeasurableStream measurableStream;
/* 131:    */  
/* 134:    */  public FastBufferedInputStream(InputStream is, int bufSize)
/* 135:    */  {
/* 136:136 */    if (bufSize <= 0) throw new IllegalArgumentException("Illegal buffer size: " + bufSize);
/* 137:137 */    this.is = is;
/* 138:138 */    this.buffer = new byte[bufSize];
/* 139:    */    
/* 140:140 */    if ((is instanceof RepositionableStream)) this.repositionableStream = ((RepositionableStream)is);
/* 141:141 */    if ((is instanceof MeasurableStream)) { this.measurableStream = ((MeasurableStream)is);
/* 142:    */    }
/* 143:143 */    if (this.repositionableStream == null) {
/* 144:    */      try
/* 145:    */      {
/* 146:146 */        this.fileChannel = ((FileChannel)is.getClass().getMethod("getChannel", new Class[0]).invoke(is, new Object[0]));
/* 147:    */      }
/* 148:    */      catch (IllegalAccessException e) {}catch (IllegalArgumentException e) {}catch (NoSuchMethodException e) {}catch (InvocationTargetException e) {}catch (ClassCastException e) {}
/* 149:    */    }
/* 150:    */  }
/* 151:    */  
/* 159:    */  public FastBufferedInputStream(InputStream is)
/* 160:    */  {
/* 161:161 */    this(is, 8192);
/* 162:    */  }
/* 163:    */  
/* 170:    */  protected boolean noMoreCharacters()
/* 171:    */    throws IOException
/* 172:    */  {
/* 173:173 */    if (this.avail == 0) {
/* 174:174 */      this.avail = this.is.read(this.buffer);
/* 175:175 */      if (this.avail <= 0) {
/* 176:176 */        this.avail = 0;
/* 177:177 */        return true;
/* 178:    */      }
/* 179:179 */      this.pos = 0;
/* 180:    */    }
/* 181:181 */    return false;
/* 182:    */  }
/* 183:    */  
/* 184:    */  public int read()
/* 185:    */    throws IOException
/* 186:    */  {
/* 187:187 */    if (noMoreCharacters()) return -1;
/* 188:188 */    this.avail -= 1;
/* 189:189 */    this.readBytes += 1L;
/* 190:190 */    return this.buffer[(this.pos++)] & 0xFF;
/* 191:    */  }
/* 192:    */  
/* 193:    */  public int read(byte[] b, int offset, int length) throws IOException
/* 194:    */  {
/* 195:195 */    if (length <= this.avail) {
/* 196:196 */      System.arraycopy(this.buffer, this.pos, b, offset, length);
/* 197:197 */      this.pos += length;
/* 198:198 */      this.avail -= length;
/* 199:199 */      this.readBytes += length;
/* 200:200 */      return length;
/* 201:    */    }
/* 202:    */    
/* 203:203 */    int head = this.avail;
/* 204:    */    
/* 205:205 */    System.arraycopy(this.buffer, this.pos, b, offset, head);
/* 206:206 */    this.pos = (this.avail = 0);
/* 207:207 */    this.readBytes += head;
/* 208:    */    
/* 209:209 */    if (length > this.buffer.length)
/* 210:    */    {
/* 211:211 */      int result = this.is.read(b, offset + head, length - head);
/* 212:212 */      if (result > 0) this.readBytes += result;
/* 213:213 */      return result < 0 ? head : head == 0 ? -1 : result + head;
/* 214:    */    }
/* 215:    */    
/* 216:216 */    if (noMoreCharacters()) { return head == 0 ? -1 : head;
/* 217:    */    }
/* 218:218 */    int toRead = Math.min(length - head, this.avail);
/* 219:219 */    this.readBytes += toRead;
/* 220:220 */    System.arraycopy(this.buffer, 0, b, offset + head, toRead);
/* 221:221 */    this.pos = toRead;
/* 222:222 */    this.avail -= toRead;
/* 223:    */    
/* 225:225 */    return toRead + head;
/* 226:    */  }
/* 227:    */  
/* 233:    */  public int readLine(byte[] array)
/* 234:    */    throws IOException
/* 235:    */  {
/* 236:236 */    return readLine(array, 0, array.length, ALL_TERMINATORS);
/* 237:    */  }
/* 238:    */  
/* 246:    */  public int readLine(byte[] array, EnumSet<LineTerminator> terminators)
/* 247:    */    throws IOException
/* 248:    */  {
/* 249:249 */    return readLine(array, 0, array.length, terminators);
/* 250:    */  }
/* 251:    */  
/* 258:    */  public int readLine(byte[] array, int off, int len)
/* 259:    */    throws IOException
/* 260:    */  {
/* 261:261 */    return readLine(array, off, len, ALL_TERMINATORS);
/* 262:    */  }
/* 263:    */  
/* 309:    */  public int readLine(byte[] array, int off, int len, EnumSet<LineTerminator> terminators)
/* 310:    */    throws IOException
/* 311:    */  {
/* 312:312 */    ByteArrays.ensureOffsetLength(array, off, len);
/* 313:313 */    if (len == 0) return 0;
/* 314:314 */    if (noMoreCharacters()) return -1;
/* 315:315 */    int k = 0;int remaining = len;int read = 0;
/* 316:    */    do {
/* 317:317 */      for (;;) { for (int i = 0; (i < this.avail) && (i < remaining) && ((k = this.buffer[(this.pos + i)]) != 10) && (k != 13); i++) {}
/* 318:318 */        System.arraycopy(this.buffer, this.pos, array, off + read, i);
/* 319:319 */        this.pos += i;
/* 320:320 */        this.avail -= i;
/* 321:321 */        read += i;
/* 322:322 */        remaining -= i;
/* 323:323 */        if (remaining == 0) {
/* 324:324 */          this.readBytes += read;
/* 325:325 */          return read;
/* 326:    */        }
/* 327:    */        
/* 328:328 */        if (this.avail <= 0) break;
/* 329:329 */        if (k == 10) {
/* 330:330 */          this.pos += 1;
/* 331:331 */          this.avail -= 1;
/* 332:332 */          if (terminators.contains(LineTerminator.LF)) {
/* 333:333 */            this.readBytes += read + 1;
/* 334:334 */            return read;
/* 335:    */          }
/* 336:    */          
/* 337:337 */          array[(off + read++)] = 10;
/* 338:338 */          remaining--;
/* 340:    */        }
/* 341:341 */        else if (k == 13) {
/* 342:342 */          this.pos += 1;
/* 343:343 */          this.avail -= 1;
/* 344:    */          
/* 345:345 */          if (terminators.contains(LineTerminator.CR_LF)) {
/* 346:346 */            if (this.avail > 0) {
/* 347:347 */              if (this.buffer[this.pos] == 10) {
/* 348:348 */                this.pos += 1;
/* 349:349 */                this.avail -= 1;
/* 350:350 */                this.readBytes += read + 2;
/* 351:351 */                return read;
/* 352:    */              }
/* 353:    */            }
/* 354:    */            else {
/* 355:355 */              if (noMoreCharacters())
/* 356:    */              {
/* 358:358 */                if (!terminators.contains(LineTerminator.CR)) {
/* 359:359 */                  array[(off + read++)] = 13;
/* 360:360 */                  remaining--;
/* 361:361 */                  this.readBytes += read;
/* 362:    */                } else {
/* 363:363 */                  this.readBytes += read + 1;
/* 364:    */                }
/* 365:365 */                return read;
/* 366:    */              }
/* 367:367 */              if (this.buffer[0] == 10)
/* 368:    */              {
/* 369:369 */                this.pos += 1;
/* 370:370 */                this.avail -= 1;
/* 371:371 */                this.readBytes += read + 2;
/* 372:372 */                return read;
/* 373:    */              }
/* 374:    */            }
/* 375:    */          }
/* 376:    */          
/* 377:377 */          if (terminators.contains(LineTerminator.CR)) {
/* 378:378 */            this.readBytes += read + 1;
/* 379:379 */            return read;
/* 380:    */          }
/* 381:    */          
/* 382:382 */          array[(off + read++)] = 13;
/* 383:383 */          remaining--;
/* 384:    */        }
/* 385:    */      }
/* 386:386 */    } while (!noMoreCharacters());
/* 387:387 */    this.readBytes += read;
/* 388:388 */    return read;
/* 389:    */  }
/* 390:    */  
/* 394:    */  public void position(long newPosition)
/* 395:    */    throws IOException
/* 396:    */  {
/* 397:397 */    long position = this.readBytes;
/* 398:    */    
/* 404:404 */    if ((newPosition <= position + this.avail) && (newPosition >= position - this.pos)) {
/* 405:405 */      this.pos = ((int)(this.pos + (newPosition - position)));
/* 406:406 */      this.avail = ((int)(this.avail - (newPosition - position)));
/* 407:407 */      this.readBytes = newPosition;
/* 408:408 */      return;
/* 409:    */    }
/* 410:    */    
/* 411:411 */    if (this.repositionableStream != null) { this.repositionableStream.position(newPosition);
/* 412:412 */    } else if (this.fileChannel != null) this.fileChannel.position(newPosition); else
/* 413:413 */      throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
/* 414:414 */    this.readBytes = newPosition;
/* 415:    */    
/* 416:416 */    this.avail = (this.pos = 0);
/* 417:    */  }
/* 418:    */  
/* 419:    */  public long position() throws IOException {
/* 420:420 */    return this.readBytes;
/* 421:    */  }
/* 422:    */  
/* 428:    */  public long length()
/* 429:    */    throws IOException
/* 430:    */  {
/* 431:431 */    if (this.measurableStream != null) return this.measurableStream.length();
/* 432:432 */    if (this.fileChannel != null) return this.fileChannel.size();
/* 433:433 */    throw new UnsupportedOperationException();
/* 434:    */  }
/* 435:    */  
/* 440:    */  private long skipByReading(long n)
/* 441:    */    throws IOException
/* 442:    */  {
/* 443:    */    int len;
/* 444:    */    
/* 447:447 */    for (long toSkip = n; 
/* 448:    */        
/* 449:449 */        toSkip > 0L; 
/* 450:    */        
/* 451:451 */        toSkip -= len)
/* 452:    */    {
/* 453:450 */      len = this.is.read(this.buffer, 0, (int)Math.min(this.buffer.length, toSkip));
/* 454:451 */      if (len <= 0) {
/* 455:    */        break;
/* 456:    */      }
/* 457:    */    }
/* 458:455 */    return n - toSkip;
/* 459:    */  }
/* 460:    */  
/* 475:    */  public long skip(long n)
/* 476:    */    throws IOException
/* 477:    */  {
/* 478:475 */    if (n <= this.avail) {
/* 479:476 */      int m = (int)n;
/* 480:477 */      this.pos += m;
/* 481:478 */      this.avail -= m;
/* 482:479 */      this.readBytes += n;
/* 483:480 */      return n;
/* 484:    */    }
/* 485:    */    
/* 486:483 */    long toSkip = n - this.avail;long result = 0L;
/* 487:484 */    this.avail = 0;
/* 488:    */    
/* 489:486 */    while (toSkip != 0L) { if ((result = this.is == System.in ? skipByReading(toSkip) : this.is.skip(toSkip)) >= toSkip) break;
/* 490:487 */      if (result == 0L) {
/* 491:488 */        if (this.is.read() == -1) break;
/* 492:489 */        toSkip -= 1L;
/* 493:    */      } else {
/* 494:491 */        toSkip -= result;
/* 495:    */      }
/* 496:    */    }
/* 497:494 */    long t = n - (toSkip - result);
/* 498:495 */    this.readBytes += t;
/* 499:496 */    return t;
/* 500:    */  }
/* 501:    */  
/* 502:    */  public int available() throws IOException
/* 503:    */  {
/* 504:501 */    return (int)Math.min(this.is.available() + this.avail, 2147483647L);
/* 505:    */  }
/* 506:    */  
/* 507:    */  public void close() throws IOException {
/* 508:505 */    if (this.is == null) return;
/* 509:506 */    if (this.is != System.in) this.is.close();
/* 510:507 */    this.is = null;
/* 511:508 */    this.buffer = null;
/* 512:    */  }
/* 513:    */  
/* 523:    */  public void flush()
/* 524:    */  {
/* 525:522 */    if (this.is == null) return;
/* 526:523 */    this.readBytes += this.avail;
/* 527:524 */    this.avail = (this.pos = 0);
/* 528:    */  }
/* 529:    */  
/* 536:    */  @Deprecated
/* 537:    */  public void reset()
/* 538:    */  {
/* 539:536 */    flush();
/* 540:    */  }
/* 541:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastBufferedInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */