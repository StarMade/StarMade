/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.Size64;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.ObjectInputStream;
/*   9:    */import java.io.ObjectOutputStream;
/*  10:    */import java.io.Serializable;
/*  11:    */import java.util.Collection;
/*  12:    */import java.util.Iterator;
/*  13:    */import java.util.NoSuchElementException;
/*  14:    */
/*  75:    */public class DoubleOpenHashBigSet
/*  76:    */  extends AbstractDoubleSet
/*  77:    */  implements Serializable, Cloneable, Hash, Size64
/*  78:    */{
/*  79:    */  public static final long serialVersionUID = 0L;
/*  80:    */  private static final boolean ASSERTS = false;
/*  81:    */  protected transient double[][] key;
/*  82:    */  protected transient boolean[][] used;
/*  83:    */  protected final float f;
/*  84:    */  protected transient long n;
/*  85:    */  protected transient long maxFill;
/*  86:    */  protected transient long mask;
/*  87:    */  protected transient int segmentMask;
/*  88:    */  protected transient int baseMask;
/*  89:    */  protected long size;
/*  90:    */  
/*  91:    */  private void initMasks()
/*  92:    */  {
/*  93: 93 */    this.mask = (this.n - 1L);
/*  94:    */    
/*  97: 97 */    this.segmentMask = (this.key[0].length - 1);
/*  98: 98 */    this.baseMask = (this.key.length - 1);
/*  99:    */  }
/* 100:    */  
/* 107:    */  public DoubleOpenHashBigSet(long expected, float f)
/* 108:    */  {
/* 109:109 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 110:110 */    if (this.n < 0L) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 111:111 */    this.f = f;
/* 112:112 */    this.n = HashCommon.bigArraySize(expected, f);
/* 113:113 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 114:114 */    this.key = DoubleBigArrays.newBigArray(this.n);
/* 115:115 */    this.used = BooleanBigArrays.newBigArray(this.n);
/* 116:116 */    initMasks();
/* 117:    */  }
/* 118:    */  
/* 122:    */  public DoubleOpenHashBigSet(long expected)
/* 123:    */  {
/* 124:124 */    this(expected, 0.75F);
/* 125:    */  }
/* 126:    */  
/* 130:    */  public DoubleOpenHashBigSet()
/* 131:    */  {
/* 132:132 */    this(16L, 0.75F);
/* 133:    */  }
/* 134:    */  
/* 140:    */  public DoubleOpenHashBigSet(Collection<? extends Double> c, float f)
/* 141:    */  {
/* 142:142 */    this(c.size(), f);
/* 143:143 */    addAll(c);
/* 144:    */  }
/* 145:    */  
/* 151:    */  public DoubleOpenHashBigSet(Collection<? extends Double> c)
/* 152:    */  {
/* 153:153 */    this(c, 0.75F);
/* 154:    */  }
/* 155:    */  
/* 161:    */  public DoubleOpenHashBigSet(DoubleCollection c, float f)
/* 162:    */  {
/* 163:163 */    this(c.size(), f);
/* 164:164 */    addAll(c);
/* 165:    */  }
/* 166:    */  
/* 172:    */  public DoubleOpenHashBigSet(DoubleCollection c)
/* 173:    */  {
/* 174:174 */    this(c, 0.75F);
/* 175:    */  }
/* 176:    */  
/* 182:    */  public DoubleOpenHashBigSet(DoubleIterator i, float f)
/* 183:    */  {
/* 184:184 */    this(16L, f);
/* 185:185 */    while (i.hasNext()) { add(i.nextDouble());
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 192:    */  public DoubleOpenHashBigSet(DoubleIterator i)
/* 193:    */  {
/* 194:194 */    this(i, 0.75F);
/* 195:    */  }
/* 196:    */  
/* 205:    */  public DoubleOpenHashBigSet(Iterator<?> i, float f)
/* 206:    */  {
/* 207:207 */    this(DoubleIterators.asDoubleIterator(i), f);
/* 208:    */  }
/* 209:    */  
/* 213:    */  public DoubleOpenHashBigSet(Iterator<?> i)
/* 214:    */  {
/* 215:215 */    this(DoubleIterators.asDoubleIterator(i));
/* 216:    */  }
/* 217:    */  
/* 228:    */  public DoubleOpenHashBigSet(double[] a, int offset, int length, float f)
/* 229:    */  {
/* 230:230 */    this(length < 0 ? 0L : length, f);
/* 231:231 */    DoubleArrays.ensureOffsetLength(a, offset, length);
/* 232:232 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 233:    */    }
/* 234:    */  }
/* 235:    */  
/* 241:    */  public DoubleOpenHashBigSet(double[] a, int offset, int length)
/* 242:    */  {
/* 243:243 */    this(a, offset, length, 0.75F);
/* 244:    */  }
/* 245:    */  
/* 251:    */  public DoubleOpenHashBigSet(double[] a, float f)
/* 252:    */  {
/* 253:253 */    this(a, 0, a.length, f);
/* 254:    */  }
/* 255:    */  
/* 261:    */  public DoubleOpenHashBigSet(double[] a)
/* 262:    */  {
/* 263:263 */    this(a, 0.75F);
/* 264:    */  }
/* 265:    */  
/* 266:    */  public boolean add(double k) {
/* 267:267 */    long h = HashCommon.murmurHash3(Double.doubleToRawLongBits(k));
/* 268:    */    
/* 270:270 */    int displ = (int)(h & this.segmentMask);
/* 271:271 */    int base = (int)((h & this.mask) >>> 27);
/* 272:    */    
/* 274:274 */    while (this.used[base][displ] != 0) {
/* 275:275 */      if (this.key[base][displ] == k) return false;
/* 276:276 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 277:    */    }
/* 278:    */    
/* 279:279 */    this.used[base][displ] = 1;
/* 280:280 */    this.key[base][displ] = k;
/* 281:    */    
/* 282:282 */    if (++this.size >= this.maxFill) { rehash(2L * this.n);
/* 283:    */    }
/* 284:284 */    return true;
/* 285:    */  }
/* 286:    */  
/* 292:    */  protected final long shiftKeys(long pos)
/* 293:    */  {
/* 294:    */    long last;
/* 295:    */    
/* 300:    */    for (;;)
/* 301:    */    {
/* 302:302 */      pos = (last = pos) + 1L & this.mask;
/* 303:    */      
/* 304:304 */      while (BooleanBigArrays.get(this.used, pos)) {
/* 305:305 */        long slot = HashCommon.murmurHash3(Double.doubleToRawLongBits(DoubleBigArrays.get(this.key, pos))) & this.mask;
/* 306:306 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 307:307 */        pos = pos + 1L & this.mask;
/* 308:    */      }
/* 309:    */      
/* 310:310 */      if (!BooleanBigArrays.get(this.used, pos))
/* 311:    */        break;
/* 312:312 */      DoubleBigArrays.set(this.key, last, DoubleBigArrays.get(this.key, pos));
/* 313:    */    }
/* 314:    */    
/* 315:315 */    BooleanBigArrays.set(this.used, last, false);
/* 316:    */    
/* 319:319 */    return last;
/* 320:    */  }
/* 321:    */  
/* 322:    */  public boolean remove(double k)
/* 323:    */  {
/* 324:324 */    long h = HashCommon.murmurHash3(Double.doubleToRawLongBits(k));
/* 325:    */    
/* 327:327 */    int displ = (int)(h & this.segmentMask);
/* 328:328 */    int base = (int)((h & this.mask) >>> 27);
/* 329:    */    
/* 331:331 */    while (this.used[base][displ] != 0) {
/* 332:332 */      if (this.key[base][displ] == k) {
/* 333:333 */        this.size -= 1L;
/* 334:334 */        shiftKeys(base * 134217728L + displ);
/* 335:    */        
/* 336:336 */        return true;
/* 337:    */      }
/* 338:338 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 339:    */    }
/* 340:    */    
/* 341:341 */    return false;
/* 342:    */  }
/* 343:    */  
/* 344:    */  public boolean contains(double k)
/* 345:    */  {
/* 346:346 */    long h = HashCommon.murmurHash3(Double.doubleToRawLongBits(k));
/* 347:    */    
/* 349:349 */    int displ = (int)(h & this.segmentMask);
/* 350:350 */    int base = (int)((h & this.mask) >>> 27);
/* 351:    */    
/* 353:353 */    while (this.used[base][displ] != 0) {
/* 354:354 */      if (this.key[base][displ] == k) return true;
/* 355:355 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 356:    */    }
/* 357:    */    
/* 358:358 */    return false;
/* 359:    */  }
/* 360:    */  
/* 365:    */  public void clear()
/* 366:    */  {
/* 367:367 */    if (this.size == 0L) return;
/* 368:368 */    this.size = 0L;
/* 369:369 */    BooleanBigArrays.fill(this.used, false);
/* 370:    */  }
/* 371:    */  
/* 373:    */  private class SetIterator
/* 374:    */    extends AbstractDoubleIterator
/* 375:    */  {
/* 376:    */    int base;
/* 377:    */    int displ;
/* 378:    */    int lastBase;
/* 379:    */    int lastDispl;
/* 380:    */    long c;
/* 381:    */    DoubleArrayList wrapped;
/* 382:    */    
/* 383:    */    private SetIterator()
/* 384:    */    {
/* 385:385 */      this.c = DoubleOpenHashBigSet.this.size;
/* 386:    */      
/* 390:390 */      this.base = DoubleOpenHashBigSet.this.key.length;
/* 391:391 */      this.lastBase = -1;
/* 392:392 */      boolean[][] used = DoubleOpenHashBigSet.this.used;
/* 393:393 */      if (this.c != 0L)
/* 394:394 */        do { if (this.displ-- == 0) {
/* 395:395 */            this.base -= 1;
/* 396:396 */            this.displ = ((int)DoubleOpenHashBigSet.this.mask);
/* 397:    */          }
/* 398:398 */        } while (used[this.base][this.displ] == 0);
/* 399:    */    }
/* 400:    */    
/* 401:401 */    public boolean hasNext() { return this.c != 0L; }
/* 402:    */    
/* 403:    */    public double nextDouble() {
/* 404:404 */      if (!hasNext()) throw new NoSuchElementException();
/* 405:405 */      this.c -= 1L;
/* 406:    */      
/* 407:407 */      if (this.base < 0) return this.wrapped.getDouble(-(this.lastBase = --this.base) - 2);
/* 408:408 */      double retVal = DoubleOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
/* 409:409 */      if (this.c != 0L) {
/* 410:410 */        boolean[][] used = DoubleOpenHashBigSet.this.used;
/* 411:    */        do {
/* 412:412 */          if (this.displ-- == 0) {
/* 413:413 */            if (this.base-- == 0) break;
/* 414:414 */            this.displ = ((int)DoubleOpenHashBigSet.this.mask);
/* 415:    */          }
/* 416:416 */        } while (used[this.base][this.displ] == 0);
/* 417:    */      }
/* 418:    */      
/* 419:419 */      return retVal;
/* 420:    */    }
/* 421:    */    
/* 427:    */    protected final long shiftKeys(long pos)
/* 428:    */    {
/* 429:    */      long last;
/* 430:    */      
/* 434:    */      for (;;)
/* 435:    */      {
/* 436:436 */        pos = (last = pos) + 1L & DoubleOpenHashBigSet.this.mask;
/* 437:437 */        while (BooleanBigArrays.get(DoubleOpenHashBigSet.this.used, pos)) {
/* 438:438 */          long slot = HashCommon.murmurHash3(Double.doubleToRawLongBits(DoubleBigArrays.get(DoubleOpenHashBigSet.this.key, pos))) & DoubleOpenHashBigSet.this.mask;
/* 439:439 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 440:440 */          pos = pos + 1L & DoubleOpenHashBigSet.this.mask;
/* 441:    */        }
/* 442:442 */        if (!BooleanBigArrays.get(DoubleOpenHashBigSet.this.used, pos)) break;
/* 443:443 */        if (pos < last)
/* 444:    */        {
/* 445:445 */          if (this.wrapped == null) this.wrapped = new DoubleArrayList();
/* 446:446 */          this.wrapped.add(DoubleBigArrays.get(DoubleOpenHashBigSet.this.key, pos));
/* 447:    */        }
/* 448:448 */        DoubleBigArrays.set(DoubleOpenHashBigSet.this.key, last, DoubleBigArrays.get(DoubleOpenHashBigSet.this.key, pos));
/* 449:    */      }
/* 450:450 */      BooleanBigArrays.set(DoubleOpenHashBigSet.this.used, last, false);
/* 451:451 */      return last;
/* 452:    */    }
/* 453:    */    
/* 454:    */    public void remove() {
/* 455:455 */      if (this.lastBase == -1) throw new IllegalStateException();
/* 456:456 */      if (this.base < -1)
/* 457:    */      {
/* 458:458 */        DoubleOpenHashBigSet.this.remove(this.wrapped.getDouble(-this.base - 2));
/* 459:459 */        this.lastBase = -1;
/* 460:460 */        return;
/* 461:    */      }
/* 462:462 */      DoubleOpenHashBigSet.this.size -= 1L;
/* 463:463 */      if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.c > 0L)) {
/* 464:464 */        this.c += 1L;
/* 465:465 */        nextDouble();
/* 466:    */      }
/* 467:467 */      this.lastBase = -1;
/* 468:    */    }
/* 469:    */  }
/* 470:    */  
/* 471:    */  public DoubleIterator iterator() {
/* 472:472 */    return new SetIterator(null);
/* 473:    */  }
/* 474:    */  
/* 483:    */  @Deprecated
/* 484:    */  public boolean rehash()
/* 485:    */  {
/* 486:486 */    return true;
/* 487:    */  }
/* 488:    */  
/* 499:    */  public boolean trim()
/* 500:    */  {
/* 501:501 */    long l = HashCommon.bigArraySize(this.size, this.f);
/* 502:502 */    if (l >= this.n) return true;
/* 503:    */    try {
/* 504:504 */      rehash(l);
/* 505:    */    } catch (OutOfMemoryError cantDoIt) {
/* 506:506 */      return false; }
/* 507:507 */    return true;
/* 508:    */  }
/* 509:    */  
/* 526:    */  public boolean trim(long n)
/* 527:    */  {
/* 528:528 */    long l = HashCommon.bigArraySize(n, this.f);
/* 529:529 */    if (this.n <= l) return true;
/* 530:    */    try {
/* 531:531 */      rehash(l);
/* 532:    */    } catch (OutOfMemoryError cantDoIt) {
/* 533:533 */      return false; }
/* 534:534 */    return true;
/* 535:    */  }
/* 536:    */  
/* 545:    */  protected void rehash(long newN)
/* 546:    */  {
/* 547:547 */    boolean[][] used = this.used;
/* 548:548 */    double[][] key = this.key;
/* 549:549 */    boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
/* 550:550 */    double[][] newKey = DoubleBigArrays.newBigArray(newN);
/* 551:551 */    long newMask = newN - 1L;
/* 552:552 */    int newSegmentMask = newKey[0].length - 1;
/* 553:553 */    int newBaseMask = newKey.length - 1;
/* 554:554 */    int base = 0;int displ = 0;
/* 555:    */    
/* 557:557 */    for (long i = this.size; i-- != 0L;) {
/* 558:558 */      while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 559:559 */      double k = key[base][displ];
/* 560:560 */      long h = HashCommon.murmurHash3(Double.doubleToRawLongBits(k));
/* 561:    */      
/* 562:562 */      int d = (int)(h & newSegmentMask);
/* 563:563 */      int b = (int)((h & newMask) >>> 27);
/* 564:564 */      while (newUsed[b][d] != 0) b = b + ((d = d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask;
/* 565:565 */      newUsed[b][d] = 1;
/* 566:566 */      newKey[b][d] = k;
/* 567:567 */      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 568:    */    }
/* 569:569 */    this.n = newN;
/* 570:570 */    this.key = newKey;
/* 571:571 */    this.used = newUsed;
/* 572:572 */    initMasks();
/* 573:573 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 574:    */  }
/* 575:    */  
/* 576:    */  @Deprecated
/* 577:577 */  public int size() { return (int)Math.min(2147483647L, this.size); }
/* 578:    */  
/* 579:    */  public long size64() {
/* 580:580 */    return this.size;
/* 581:    */  }
/* 582:    */  
/* 583:583 */  public boolean isEmpty() { return this.size == 0L; }
/* 584:    */  
/* 588:    */  public DoubleOpenHashBigSet clone()
/* 589:    */  {
/* 590:    */    DoubleOpenHashBigSet c;
/* 591:    */    
/* 594:    */    try
/* 595:    */    {
/* 596:596 */      c = (DoubleOpenHashBigSet)super.clone();
/* 597:    */    }
/* 598:    */    catch (CloneNotSupportedException cantHappen) {
/* 599:599 */      throw new InternalError();
/* 600:    */    }
/* 601:601 */    c.key = DoubleBigArrays.copy(this.key);
/* 602:602 */    c.used = BooleanBigArrays.copy(this.used);
/* 603:603 */    return c;
/* 604:    */  }
/* 605:    */  
/* 613:    */  public int hashCode()
/* 614:    */  {
/* 615:615 */    boolean[][] used = this.used;
/* 616:616 */    double[][] key = this.key;
/* 617:617 */    int h = 0;
/* 618:618 */    int base = 0;int displ = 0;
/* 619:619 */    for (long j = this.size; j-- != 0L;) {
/* 620:620 */      while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 621:621 */      h += HashCommon.double2int(key[base][displ]);
/* 622:622 */      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 623:    */    }
/* 624:624 */    return h;
/* 625:    */  }
/* 626:    */  
/* 627:627 */  private void writeObject(ObjectOutputStream s) throws IOException { DoubleIterator i = iterator();
/* 628:628 */    s.defaultWriteObject();
/* 629:629 */    for (long j = this.size; j-- != 0L; s.writeDouble(i.nextDouble())) {}
/* 630:    */  }
/* 631:    */  
/* 632:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 633:633 */    s.defaultReadObject();
/* 634:634 */    this.n = HashCommon.bigArraySize(this.size, this.f);
/* 635:635 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 636:636 */    double[][] key = this.key = DoubleBigArrays.newBigArray(this.n);
/* 637:637 */    boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.n);
/* 638:638 */    initMasks();
/* 639:    */    
/* 642:642 */    for (long i = this.size; i-- != 0L;) {
/* 643:643 */      double k = s.readDouble();
/* 644:644 */      long h = HashCommon.murmurHash3(Double.doubleToRawLongBits(k));
/* 645:645 */      int base = (int)((h & this.mask) >>> 27);
/* 646:646 */      int displ = (int)(h & this.segmentMask);
/* 647:647 */      while (used[base][displ] != 0) base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 648:648 */      used[base][displ] = 1;
/* 649:649 */      key[base][displ] = k;
/* 650:    */    }
/* 651:    */  }
/* 652:    */  
/* 653:    */  private void checkTable() {}
/* 654:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleOpenHashBigSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */