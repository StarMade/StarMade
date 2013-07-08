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
/*  73:    */public class ObjectOpenHashBigSet<K>
/*  74:    */  extends AbstractObjectSet<K>
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
/* 105:    */  public ObjectOpenHashBigSet(long expected, float f)
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
/* 122:    */  public ObjectOpenHashBigSet(long expected)
/* 123:    */  {
/* 124:124 */    this(expected, 0.75F);
/* 125:    */  }
/* 126:    */  
/* 130:    */  public ObjectOpenHashBigSet()
/* 131:    */  {
/* 132:132 */    this(16L, 0.75F);
/* 133:    */  }
/* 134:    */  
/* 140:    */  public ObjectOpenHashBigSet(Collection<? extends K> c, float f)
/* 141:    */  {
/* 142:142 */    this(c.size(), f);
/* 143:143 */    addAll(c);
/* 144:    */  }
/* 145:    */  
/* 151:    */  public ObjectOpenHashBigSet(Collection<? extends K> c)
/* 152:    */  {
/* 153:153 */    this(c, 0.75F);
/* 154:    */  }
/* 155:    */  
/* 161:    */  public ObjectOpenHashBigSet(ObjectCollection<? extends K> c, float f)
/* 162:    */  {
/* 163:163 */    this(c.size(), f);
/* 164:164 */    addAll(c);
/* 165:    */  }
/* 166:    */  
/* 172:    */  public ObjectOpenHashBigSet(ObjectCollection<? extends K> c)
/* 173:    */  {
/* 174:174 */    this(c, 0.75F);
/* 175:    */  }
/* 176:    */  
/* 182:    */  public ObjectOpenHashBigSet(ObjectIterator<K> i, float f)
/* 183:    */  {
/* 184:184 */    this(16L, f);
/* 185:185 */    while (i.hasNext()) { add(i.next());
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 192:    */  public ObjectOpenHashBigSet(ObjectIterator<K> i)
/* 193:    */  {
/* 194:194 */    this(i, 0.75F);
/* 195:    */  }
/* 196:    */  
/* 202:    */  public ObjectOpenHashBigSet(K[] a, int offset, int length, float f)
/* 203:    */  {
/* 204:204 */    this(length < 0 ? 0L : length, f);
/* 205:205 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 206:206 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 213:    */  public ObjectOpenHashBigSet(K[] a, int offset, int length)
/* 214:    */  {
/* 215:215 */    this(a, offset, length, 0.75F);
/* 216:    */  }
/* 217:    */  
/* 221:    */  public ObjectOpenHashBigSet(K[] a, float f)
/* 222:    */  {
/* 223:223 */    this(a, 0, a.length, f);
/* 224:    */  }
/* 225:    */  
/* 231:231 */  public ObjectOpenHashBigSet(K[] a) { this(a, 0.75F); }
/* 232:    */  
/* 233:    */  public boolean add(K k) {
/* 234:234 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 235:    */    
/* 236:236 */    int displ = (int)(h & this.segmentMask);
/* 237:237 */    int base = (int)((h & this.mask) >>> 27);
/* 238:    */    
/* 239:239 */    while (this.used[base][displ] != 0) {
/* 240:240 */      if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) return false;
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
/* 265:265 */        long slot = (ObjectBigArrays.get(this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(ObjectBigArrays.get(this.key, pos).hashCode())) & this.mask;
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
/* 278:278 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 279:    */    
/* 280:280 */    int displ = (int)(h & this.segmentMask);
/* 281:281 */    int base = (int)((h & this.mask) >>> 27);
/* 282:    */    
/* 283:283 */    while (this.used[base][displ] != 0) {
/* 284:284 */      if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) {
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
/* 296:296 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 297:    */    
/* 298:298 */    int displ = (int)(h & this.segmentMask);
/* 299:299 */    int base = (int)((h & this.mask) >>> 27);
/* 300:    */    
/* 301:301 */    while (this.used[base][displ] != 0) {
/* 302:302 */      if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) return true;
/* 303:303 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 304:    */    }
/* 305:305 */    return false;
/* 306:    */  }
/* 307:    */  
/* 309:    */  public K get(Object k)
/* 310:    */  {
/* 311:311 */    long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 312:    */    
/* 313:313 */    int displ = (int)(h & this.segmentMask);
/* 314:314 */    int base = (int)((h & this.mask) >>> 27);
/* 315:    */    
/* 316:316 */    while (this.used[base][displ] != 0) {
/* 317:317 */      if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) return this.key[base][displ];
/* 318:318 */      base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 319:    */    }
/* 320:320 */    return null;
/* 321:    */  }
/* 322:    */  
/* 327:    */  public void clear()
/* 328:    */  {
/* 329:329 */    if (this.size == 0L) return;
/* 330:330 */    this.size = 0L;
/* 331:331 */    BooleanBigArrays.fill(this.used, false);
/* 332:332 */    ObjectBigArrays.fill(this.key, null);
/* 333:    */  }
/* 334:    */  
/* 336:    */  private class SetIterator
/* 337:    */    extends AbstractObjectIterator<K>
/* 338:    */  {
/* 339:    */    int base;
/* 340:    */    int displ;
/* 341:    */    int lastBase;
/* 342:    */    int lastDispl;
/* 343:    */    long c;
/* 344:    */    ObjectArrayList<K> wrapped;
/* 345:    */    
/* 346:    */    private SetIterator()
/* 347:    */    {
/* 348:348 */      this.c = ObjectOpenHashBigSet.this.size;
/* 349:    */      
/* 353:353 */      this.base = ObjectOpenHashBigSet.this.key.length;
/* 354:354 */      this.lastBase = -1;
/* 355:355 */      boolean[][] used = ObjectOpenHashBigSet.this.used;
/* 356:356 */      if (this.c != 0L)
/* 357:357 */        do { if (this.displ-- == 0) {
/* 358:358 */            this.base -= 1;
/* 359:359 */            this.displ = ((int)ObjectOpenHashBigSet.this.mask);
/* 360:    */          }
/* 361:361 */        } while (used[this.base][this.displ] == 0);
/* 362:    */    }
/* 363:    */    
/* 364:364 */    public boolean hasNext() { return this.c != 0L; }
/* 365:    */    
/* 366:    */    public K next() {
/* 367:367 */      if (!hasNext()) throw new NoSuchElementException();
/* 368:368 */      this.c -= 1L;
/* 369:    */      
/* 370:370 */      if (this.base < 0) return this.wrapped.get(-(this.lastBase = --this.base) - 2);
/* 371:371 */      K retVal = ObjectOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
/* 372:372 */      if (this.c != 0L) {
/* 373:373 */        boolean[][] used = ObjectOpenHashBigSet.this.used;
/* 374:    */        do {
/* 375:375 */          if (this.displ-- == 0) {
/* 376:376 */            if (this.base-- == 0) break;
/* 377:377 */            this.displ = ((int)ObjectOpenHashBigSet.this.mask);
/* 378:    */          }
/* 379:379 */        } while (used[this.base][this.displ] == 0);
/* 380:    */      }
/* 381:    */      
/* 382:382 */      return retVal;
/* 383:    */    }
/* 384:    */    
/* 390:    */    protected final long shiftKeys(long pos)
/* 391:    */    {
/* 392:    */      long last;
/* 393:    */      
/* 397:    */      for (;;)
/* 398:    */      {
/* 399:399 */        pos = (last = pos) + 1L & ObjectOpenHashBigSet.this.mask;
/* 400:400 */        while (BooleanBigArrays.get(ObjectOpenHashBigSet.this.used, pos)) {
/* 401:401 */          long slot = (ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos).hashCode())) & ObjectOpenHashBigSet.this.mask;
/* 402:402 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 403:403 */          pos = pos + 1L & ObjectOpenHashBigSet.this.mask;
/* 404:    */        }
/* 405:405 */        if (!BooleanBigArrays.get(ObjectOpenHashBigSet.this.used, pos)) break;
/* 406:406 */        if (pos < last)
/* 407:    */        {
/* 408:408 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 409:409 */          this.wrapped.add(ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos));
/* 410:    */        }
/* 411:411 */        ObjectBigArrays.set(ObjectOpenHashBigSet.this.key, last, ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos));
/* 412:    */      }
/* 413:413 */      BooleanBigArrays.set(ObjectOpenHashBigSet.this.used, last, false);
/* 414:414 */      ObjectBigArrays.set(ObjectOpenHashBigSet.this.key, last, null);
/* 415:415 */      return last;
/* 416:    */    }
/* 417:    */    
/* 418:    */    public void remove() {
/* 419:419 */      if (this.lastBase == -1) throw new IllegalStateException();
/* 420:420 */      if (this.base < -1)
/* 421:    */      {
/* 422:422 */        ObjectOpenHashBigSet.this.remove(this.wrapped.set(-this.base - 2, null));
/* 423:423 */        this.lastBase = -1;
/* 424:424 */        return;
/* 425:    */      }
/* 426:426 */      ObjectOpenHashBigSet.this.size -= 1L;
/* 427:427 */      if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.c > 0L)) {
/* 428:428 */        this.c += 1L;
/* 429:429 */        next();
/* 430:    */      }
/* 431:431 */      this.lastBase = -1;
/* 432:    */    }
/* 433:    */  }
/* 434:    */  
/* 435:    */  public ObjectIterator<K> iterator() {
/* 436:436 */    return new SetIterator(null);
/* 437:    */  }
/* 438:    */  
/* 447:    */  @Deprecated
/* 448:    */  public boolean rehash()
/* 449:    */  {
/* 450:450 */    return true;
/* 451:    */  }
/* 452:    */  
/* 463:    */  public boolean trim()
/* 464:    */  {
/* 465:465 */    long l = HashCommon.bigArraySize(this.size, this.f);
/* 466:466 */    if (l >= this.n) return true;
/* 467:    */    try {
/* 468:468 */      rehash(l);
/* 469:    */    } catch (OutOfMemoryError cantDoIt) {
/* 470:470 */      return false; }
/* 471:471 */    return true;
/* 472:    */  }
/* 473:    */  
/* 490:    */  public boolean trim(long n)
/* 491:    */  {
/* 492:492 */    long l = HashCommon.bigArraySize(n, this.f);
/* 493:493 */    if (this.n <= l) return true;
/* 494:    */    try {
/* 495:495 */      rehash(l);
/* 496:    */    } catch (OutOfMemoryError cantDoIt) {
/* 497:497 */      return false; }
/* 498:498 */    return true;
/* 499:    */  }
/* 500:    */  
/* 509:    */  protected void rehash(long newN)
/* 510:    */  {
/* 511:511 */    boolean[][] used = this.used;
/* 512:512 */    K[][] key = this.key;
/* 513:513 */    boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
/* 514:514 */    K[][] newKey = (Object[][])ObjectBigArrays.newBigArray(newN);
/* 515:515 */    long newMask = newN - 1L;
/* 516:516 */    int newSegmentMask = newKey[0].length - 1;
/* 517:517 */    int newBaseMask = newKey.length - 1;
/* 518:518 */    int base = 0;int displ = 0;
/* 519:    */    
/* 521:521 */    for (long i = this.size; i-- != 0L;) {
/* 522:522 */      while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 523:523 */      K k = key[base][displ];
/* 524:524 */      long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 525:    */      
/* 526:526 */      int d = (int)(h & newSegmentMask);
/* 527:527 */      int b = (int)((h & newMask) >>> 27);
/* 528:528 */      while (newUsed[b][d] != 0) b = b + ((d = d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask;
/* 529:529 */      newUsed[b][d] = 1;
/* 530:530 */      newKey[b][d] = k;
/* 531:531 */      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 532:    */    }
/* 533:533 */    this.n = newN;
/* 534:534 */    this.key = newKey;
/* 535:535 */    this.used = newUsed;
/* 536:536 */    initMasks();
/* 537:537 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 538:    */  }
/* 539:    */  
/* 540:    */  @Deprecated
/* 541:541 */  public int size() { return (int)Math.min(2147483647L, this.size); }
/* 542:    */  
/* 543:    */  public long size64() {
/* 544:544 */    return this.size;
/* 545:    */  }
/* 546:    */  
/* 547:547 */  public boolean isEmpty() { return this.size == 0L; }
/* 548:    */  
/* 552:    */  public ObjectOpenHashBigSet<K> clone()
/* 553:    */  {
/* 554:    */    ObjectOpenHashBigSet<K> c;
/* 555:    */    
/* 558:    */    try
/* 559:    */    {
/* 560:560 */      c = (ObjectOpenHashBigSet)super.clone();
/* 561:    */    }
/* 562:    */    catch (CloneNotSupportedException cantHappen) {
/* 563:563 */      throw new InternalError();
/* 564:    */    }
/* 565:565 */    c.key = ObjectBigArrays.copy(this.key);
/* 566:566 */    c.used = BooleanBigArrays.copy(this.used);
/* 567:567 */    return c;
/* 568:    */  }
/* 569:    */  
/* 577:    */  public int hashCode()
/* 578:    */  {
/* 579:579 */    boolean[][] used = this.used;
/* 580:580 */    K[][] key = this.key;
/* 581:581 */    int h = 0;
/* 582:582 */    int base = 0;int displ = 0;
/* 583:583 */    for (long j = this.size; j-- != 0L;) {
/* 584:584 */      while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 585:585 */      if (this != key[base][displ])
/* 586:586 */        h += (key[base][displ] == null ? 0 : key[base][displ].hashCode());
/* 587:587 */      base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 588:    */    }
/* 589:589 */    return h;
/* 590:    */  }
/* 591:    */  
/* 592:592 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 593:593 */    s.defaultWriteObject();
/* 594:594 */    for (long j = this.size; j-- != 0L; s.writeObject(i.next())) {}
/* 595:    */  }
/* 596:    */  
/* 597:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 598:598 */    s.defaultReadObject();
/* 599:599 */    this.n = HashCommon.bigArraySize(this.size, this.f);
/* 600:600 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 601:601 */    K[][] key = this.key = (Object[][])ObjectBigArrays.newBigArray(this.n);
/* 602:602 */    boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.n);
/* 603:603 */    initMasks();
/* 604:    */    
/* 607:607 */    for (long i = this.size; i-- != 0L;) {
/* 608:608 */      K k = s.readObject();
/* 609:609 */      long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 610:610 */      int base = (int)((h & this.mask) >>> 27);
/* 611:611 */      int displ = (int)(h & this.segmentMask);
/* 612:612 */      while (used[base][displ] != 0) base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 613:613 */      used[base][displ] = 1;
/* 614:614 */      key[base][displ] = k;
/* 615:    */    }
/* 616:    */  }
/* 617:    */  
/* 618:    */  private void checkTable() {}
/* 619:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */