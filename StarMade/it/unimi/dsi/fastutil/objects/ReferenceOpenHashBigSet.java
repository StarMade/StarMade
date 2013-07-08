/*   1:    */package it.unimi.dsi.fastutil.objects;
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
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  73:    */public class ReferenceOpenHashBigSet<K>
/*  74:    */  extends AbstractReferenceSet<K>
/*  75:    */  implements Serializable, Cloneable, Hash, Size64
/*  76:    */{
/*  77:    */  public static final long serialVersionUID = 0L;
/*  78:    */  private static final boolean ASSERTS = false;
/*  79:    */  protected transient K[][] key;
/*  80:    */  protected transient boolean[][] used;
/*  81:    */  protected final float f;
/*  82:    */  protected transient long n;
/*  83:    */  protected transient long maxFill;
/*  84:    */  protected transient long mask;
/*  85:    */  protected transient int segmentMask;
/*  86:    */  protected transient int baseMask;
/*  87:    */  protected long size;
/*  88:    */  
/*  89:    */  private void initMasks()
/*  90:    */  {
/*  91: 91 */    this.mask = (this.n - 1L);
/*  92:    */    
/*  95: 95 */    this.segmentMask = (this.key[0].length - 1);
/*  96: 96 */    this.baseMask = (this.key.length - 1);
/*  97:    */  }
/*  98:    */  
/* 105:    */  public ReferenceOpenHashBigSet(long expected, float f)
/* 106:    */  {
/* 107:107 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 108:108 */    if (this.n < 0L) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 109:109 */    this.f = f;
/* 110:110 */    this.n = HashCommon.bigArraySize(expected, f);
/* 111:111 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 112:112 */    this.key = ((Object[][])ObjectBigArrays.newBigArray(this.n));
/* 113:113 */    this.used = BooleanBigArrays.newBigArray(this.n);
/* 114:114 */    initMasks();
/* 115:    */  }
/* 116:    */  
/* 122:    */  public ReferenceOpenHashBigSet(long expected)
/* 123:    */  {
/* 124:124 */    this(expected, 0.75F);
/* 125:    */  }
/* 126:    */  
/* 130:    */  public ReferenceOpenHashBigSet()
/* 131:    */  {
/* 132:132 */    this(16L, 0.75F);
/* 133:    */  }
/* 134:    */  
/* 140:    */  public ReferenceOpenHashBigSet(Collection<? extends K> c, float f)
/* 141:    */  {
/* 142:142 */    this(c.size(), f);
/* 143:143 */    addAll(c);
/* 144:    */  }
/* 145:    */  
/* 151:    */  public ReferenceOpenHashBigSet(Collection<? extends K> c)
/* 152:    */  {
/* 153:153 */    this(c, 0.75F);
/* 154:    */  }
/* 155:    */  
/* 161:    */  public ReferenceOpenHashBigSet(ReferenceCollection<? extends K> c, float f)
/* 162:    */  {
/* 163:163 */    this(c.size(), f);
/* 164:164 */    addAll(c);
/* 165:    */  }
/* 166:    */  
/* 172:    */  public ReferenceOpenHashBigSet(ReferenceCollection<? extends K> c)
/* 173:    */  {
/* 174:174 */    this(c, 0.75F);
/* 175:    */  }
/* 176:    */  
/* 182:    */  public ReferenceOpenHashBigSet(ObjectIterator<K> i, float f)
/* 183:    */  {
/* 184:184 */    this(16L, f);
/* 185:185 */    while (i.hasNext()) { add(i.next());
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 192:    */  public ReferenceOpenHashBigSet(ObjectIterator<K> i)
/* 193:    */  {
/* 194:194 */    this(i, 0.75F);
/* 195:    */  }
/* 196:    */  
/* 202:    */  public ReferenceOpenHashBigSet(K[] a, int offset, int length, float f)
/* 203:    */  {
/* 204:204 */    this(length < 0 ? 0L : length, f);
/* 205:205 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 206:206 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 213:    */  public ReferenceOpenHashBigSet(K[] a, int offset, int length)
/* 214:    */  {
/* 215:215 */    this(a, offset, length, 0.75F);
/* 216:    */  }
/* 217:    */  
/* 221:    */  public ReferenceOpenHashBigSet(K[] a, float f)
/* 222:    */  {
/* 223:223 */    this(a, 0, a.length, f);
/* 224:    */  }
/* 225:    */  
/* 231:231 */  public ReferenceOpenHashBigSet(K[] a) { this(a, 0.75F); }
/* 232:    */  
/* 233:    */  public boolean add(K k) {
/* 234:234 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/* 235:    */    
/* 236:236 */    int displ = (int)(h & this.segmentMask);
/* 237:237 */    int base = (int)((h & this.mask) >>> 27);
/* 238:    */    
/* 239:239 */    while (this.used[base][displ] != 0) {
/* 240:240 */      if (this.key[base][displ] == k) return false;
/* 241:241 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 242:    */    }
/* 243:243 */    this.used[base][displ] = 1;
/* 244:244 */    this.key[base][displ] = k;
/* 245:245 */    if (++this.size >= this.maxFill) { rehash(2L * this.n);
/* 246:    */    }
/* 247:247 */    return true;
/* 248:    */  }
/* 249:    */  
/* 254:    */  protected final long shiftKeys(long pos)
/* 255:    */  {
/* 256:    */    long last;
/* 257:    */    
/* 261:    */    for (;;)
/* 262:    */    {
/* 263:263 */      pos = (last = pos) + 1L & this.mask;
/* 264:264 */      while (BooleanBigArrays.get(this.used, pos)) {
/* 265:265 */        long slot = (ObjectBigArrays.get(this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(ObjectBigArrays.get(this.key, pos)))) & this.mask;
/* 266:266 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 267:267 */        pos = pos + 1L & this.mask;
/* 268:    */      }
/* 269:269 */      if (!BooleanBigArrays.get(this.used, pos)) break;
/* 270:270 */      ObjectBigArrays.set(this.key, last, ObjectBigArrays.get(this.key, pos));
/* 271:    */    }
/* 272:272 */    BooleanBigArrays.set(this.used, last, false);
/* 273:273 */    ObjectBigArrays.set(this.key, last, null);
/* 274:274 */    return last;
/* 275:    */  }
/* 276:    */  
/* 277:    */  public boolean remove(Object k) {
/* 278:278 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/* 279:    */    
/* 280:280 */    int displ = (int)(h & this.segmentMask);
/* 281:281 */    int base = (int)((h & this.mask) >>> 27);
/* 282:    */    
/* 283:283 */    while (this.used[base][displ] != 0) {
/* 284:284 */      if (this.key[base][displ] == k) {
/* 285:285 */        this.size -= 1L;
/* 286:286 */        shiftKeys(base * 134217728L + displ);
/* 287:    */        
/* 288:288 */        return true;
/* 289:    */      }
/* 290:290 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 291:    */    }
/* 292:292 */    return false;
/* 293:    */  }
/* 294:    */  
/* 295:    */  public boolean contains(Object k) {
/* 296:296 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/* 297:    */    
/* 298:298 */    int displ = (int)(h & this.segmentMask);
/* 299:299 */    int base = (int)((h & this.mask) >>> 27);
/* 300:    */    
/* 301:301 */    while (this.used[base][displ] != 0) {
/* 302:302 */      if (this.key[base][displ] == k) return true;
/* 303:303 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 304:    */    }
/* 305:305 */    return false;
/* 306:    */  }
/* 307:    */  
/* 312:    */  public void clear()
/* 313:    */  {
/* 314:314 */    if (this.size == 0L) return;
/* 315:315 */    this.size = 0L;
/* 316:316 */    BooleanBigArrays.fill(this.used, false);
/* 317:317 */    ObjectBigArrays.fill(this.key, null);
/* 318:    */  }
/* 319:    */  
/* 321:    */  private class SetIterator
/* 322:    */    extends AbstractObjectIterator<K>
/* 323:    */  {
/* 324:    */    int base;
/* 325:    */    int displ;
/* 326:    */    int lastBase;
/* 327:    */    int lastDispl;
/* 328:    */    long c;
/* 329:    */    ReferenceArrayList<K> wrapped;
/* 330:    */    
/* 331:    */    private SetIterator()
/* 332:    */    {
/* 333:333 */      this.c = ReferenceOpenHashBigSet.this.size;
/* 334:    */      
/* 338:338 */      this.base = ReferenceOpenHashBigSet.this.key.length;
/* 339:339 */      this.lastBase = -1;
/* 340:340 */      boolean[][] used = ReferenceOpenHashBigSet.this.used;
/* 341:341 */      if (this.c != 0L)
/* 342:342 */        do { if (this.displ-- == 0) {
/* 343:343 */            this.base -= 1;
/* 344:344 */            this.displ = ((int)ReferenceOpenHashBigSet.this.mask);
/* 345:    */          }
/* 346:346 */        } while (used[this.base][this.displ] == 0);
/* 347:    */    }
/* 348:    */    
/* 349:349 */    public boolean hasNext() { return this.c != 0L; }
/* 350:    */    
/* 351:    */    public K next() {
/* 352:352 */      if (!hasNext()) throw new NoSuchElementException();
/* 353:353 */      this.c -= 1L;
/* 354:    */      
/* 355:355 */      if (this.base < 0) return this.wrapped.get(-(this.lastBase = --this.base) - 2);
/* 356:356 */      K retVal = ReferenceOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
/* 357:357 */      if (this.c != 0L) {
/* 358:358 */        boolean[][] used = ReferenceOpenHashBigSet.this.used;
/* 359:    */        do {
/* 360:360 */          if (this.displ-- == 0) {
/* 361:361 */            if (this.base-- == 0) break;
/* 362:362 */            this.displ = ((int)ReferenceOpenHashBigSet.this.mask);
/* 363:    */          }
/* 364:364 */        } while (used[this.base][this.displ] == 0);
/* 365:    */      }
/* 366:    */      
/* 367:367 */      return retVal;
/* 368:    */    }
/* 369:    */    
/* 375:    */    protected final long shiftKeys(long pos)
/* 376:    */    {
/* 377:    */      long last;
/* 378:    */      
/* 382:    */      for (;;)
/* 383:    */      {
/* 384:384 */        pos = (last = pos) + 1L & ReferenceOpenHashBigSet.this.mask;
/* 385:385 */        while (BooleanBigArrays.get(ReferenceOpenHashBigSet.this.used, pos)) {
/* 386:386 */          long slot = (ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos)))) & ReferenceOpenHashBigSet.this.mask;
/* 387:387 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 388:388 */          pos = pos + 1L & ReferenceOpenHashBigSet.this.mask;
/* 389:    */        }
/* 390:390 */        if (!BooleanBigArrays.get(ReferenceOpenHashBigSet.this.used, pos)) break;
/* 391:391 */        if (pos < last)
/* 392:    */        {
/* 393:393 */          if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 394:394 */          this.wrapped.add(ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos));
/* 395:    */        }
/* 396:396 */        ObjectBigArrays.set(ReferenceOpenHashBigSet.this.key, last, ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos));
/* 397:    */      }
/* 398:398 */      BooleanBigArrays.set(ReferenceOpenHashBigSet.this.used, last, false);
/* 399:399 */      ObjectBigArrays.set(ReferenceOpenHashBigSet.this.key, last, null);
/* 400:400 */      return last;
/* 401:    */    }
/* 402:    */    
/* 403:    */    public void remove() {
/* 404:404 */      if (this.lastBase == -1) throw new IllegalStateException();
/* 405:405 */      if (this.base < -1)
/* 406:    */      {
/* 407:407 */        ReferenceOpenHashBigSet.this.remove(this.wrapped.set(-this.base - 2, null));
/* 408:408 */        this.lastBase = -1;
/* 409:409 */        return;
/* 410:    */      }
/* 411:411 */      ReferenceOpenHashBigSet.this.size -= 1L;
/* 412:412 */      if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.c > 0L)) {
/* 413:413 */        this.c += 1L;
/* 414:414 */        next();
/* 415:    */      }
/* 416:416 */      this.lastBase = -1;
/* 417:    */    }
/* 418:    */  }
/* 419:    */  
/* 420:    */  public ObjectIterator<K> iterator() {
/* 421:421 */    return new SetIterator(null);
/* 422:    */  }
/* 423:    */  
/* 432:    */  @Deprecated
/* 433:    */  public boolean rehash()
/* 434:    */  {
/* 435:435 */    return true;
/* 436:    */  }
/* 437:    */  
/* 448:    */  public boolean trim()
/* 449:    */  {
/* 450:450 */    long l = HashCommon.bigArraySize(this.size, this.f);
/* 451:451 */    if (l >= this.n) return true;
/* 452:    */    try {
/* 453:453 */      rehash(l);
/* 454:    */    } catch (OutOfMemoryError cantDoIt) {
/* 455:455 */      return false; }
/* 456:456 */    return true;
/* 457:    */  }
/* 458:    */  
/* 475:    */  public boolean trim(long n)
/* 476:    */  {
/* 477:477 */    long l = HashCommon.bigArraySize(n, this.f);
/* 478:478 */    if (this.n <= l) return true;
/* 479:    */    try {
/* 480:480 */      rehash(l);
/* 481:    */    } catch (OutOfMemoryError cantDoIt) {
/* 482:482 */      return false; }
/* 483:483 */    return true;
/* 484:    */  }
/* 485:    */  
/* 494:    */  protected void rehash(long newN)
/* 495:    */  {
/* 496:496 */    boolean[][] used = this.used;
/* 497:497 */    K[][] key = this.key;
/* 498:498 */    boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
/* 499:499 */    K[][] newKey = (Object[][])ObjectBigArrays.newBigArray(newN);
/* 500:500 */    long newMask = newN - 1L;
/* 501:501 */    int newSegmentMask = newKey[0].length - 1;
/* 502:502 */    int newBaseMask = newKey.length - 1;
/* 503:503 */    int base = 0;int displ = 0;
/* 504:    */    
/* 506:506 */    for (long i = this.size; i-- != 0L;) {
/* 507:507 */      while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 508:508 */      K k = key[base][displ];
/* 509:509 */      long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/* 510:    */      
/* 511:511 */      int d = (int)(h & newSegmentMask);
/* 512:512 */      int b = (int)((h & newMask) >>> 27);
/* 513:513 */      while (newUsed[b][d] != 0) b = b + ((d = d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask;
/* 514:514 */      newUsed[b][d] = 1;
/* 515:515 */      newKey[b][d] = k;
/* 516:516 */      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 517:    */    }
/* 518:518 */    this.n = newN;
/* 519:519 */    this.key = newKey;
/* 520:520 */    this.used = newUsed;
/* 521:521 */    initMasks();
/* 522:522 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 523:    */  }
/* 524:    */  
/* 525:    */  @Deprecated
/* 526:526 */  public int size() { return (int)Math.min(2147483647L, this.size); }
/* 527:    */  
/* 528:    */  public long size64() {
/* 529:529 */    return this.size;
/* 530:    */  }
/* 531:    */  
/* 532:532 */  public boolean isEmpty() { return this.size == 0L; }
/* 533:    */  
/* 537:    */  public ReferenceOpenHashBigSet<K> clone()
/* 538:    */  {
/* 539:    */    ReferenceOpenHashBigSet<K> c;
/* 540:    */    
/* 543:    */    try
/* 544:    */    {
/* 545:545 */      c = (ReferenceOpenHashBigSet)super.clone();
/* 546:    */    }
/* 547:    */    catch (CloneNotSupportedException cantHappen) {
/* 548:548 */      throw new InternalError();
/* 549:    */    }
/* 550:550 */    c.key = ObjectBigArrays.copy(this.key);
/* 551:551 */    c.used = BooleanBigArrays.copy(this.used);
/* 552:552 */    return c;
/* 553:    */  }
/* 554:    */  
/* 562:    */  public int hashCode()
/* 563:    */  {
/* 564:564 */    boolean[][] used = this.used;
/* 565:565 */    K[][] key = this.key;
/* 566:566 */    int h = 0;
/* 567:567 */    int base = 0;int displ = 0;
/* 568:568 */    for (long j = this.size; j-- != 0L;) {
/* 569:569 */      while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 570:570 */      if (this != key[base][displ])
/* 571:571 */        h += (key[base][displ] == null ? 0 : System.identityHashCode(key[base][displ]));
/* 572:572 */      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 573:    */    }
/* 574:574 */    return h;
/* 575:    */  }
/* 576:    */  
/* 577:577 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 578:578 */    s.defaultWriteObject();
/* 579:579 */    for (long j = this.size; j-- != 0L; s.writeObject(i.next())) {}
/* 580:    */  }
/* 581:    */  
/* 582:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 583:583 */    s.defaultReadObject();
/* 584:584 */    this.n = HashCommon.bigArraySize(this.size, this.f);
/* 585:585 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 586:586 */    K[][] key = this.key = (Object[][])ObjectBigArrays.newBigArray(this.n);
/* 587:587 */    boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.n);
/* 588:588 */    initMasks();
/* 589:    */    
/* 592:592 */    for (long i = this.size; i-- != 0L;) {
/* 593:593 */      K k = s.readObject();
/* 594:594 */      long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/* 595:595 */      int base = (int)((h & this.mask) >>> 27);
/* 596:596 */      int displ = (int)(h & this.segmentMask);
/* 597:597 */      while (used[base][displ] != 0) base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 598:598 */      used[base][displ] = 1;
/* 599:599 */      key[base][displ] = k;
/* 600:    */    }
/* 601:    */  }
/* 602:    */  
/* 603:    */  private void checkTable() {}
/* 604:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceOpenHashBigSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */