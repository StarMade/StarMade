/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   5:    */import it.unimi.dsi.fastutil.HashCommon;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.ObjectInputStream;
/*   9:    */import java.io.ObjectOutputStream;
/*  10:    */import java.io.Serializable;
/*  11:    */import java.util.Collection;
/*  12:    */import java.util.Comparator;
/*  13:    */import java.util.NoSuchElementException;
/*  14:    */
/*  88:    */public class ObjectLinkedOpenCustomHashSet<K>
/*  89:    */  extends AbstractObjectSortedSet<K>
/*  90:    */  implements Serializable, Cloneable, Hash
/*  91:    */{
/*  92:    */  public static final long serialVersionUID = 0L;
/*  93:    */  private static final boolean ASSERTS = false;
/*  94:    */  protected transient K[] key;
/*  95:    */  protected transient boolean[] used;
/*  96:    */  protected final float f;
/*  97:    */  protected transient int n;
/*  98:    */  protected transient int maxFill;
/*  99:    */  protected transient int mask;
/* 100:    */  protected int size;
/* 101:101 */  protected transient int first = -1;
/* 102:    */  
/* 103:103 */  protected transient int last = -1;
/* 104:    */  
/* 108:    */  protected transient long[] link;
/* 109:    */  
/* 113:    */  protected Hash.Strategy<K> strategy;
/* 114:    */  
/* 119:    */  public ObjectLinkedOpenCustomHashSet(int expected, float f, Hash.Strategy<K> strategy)
/* 120:    */  {
/* 121:121 */    this.strategy = strategy;
/* 122:122 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 123:123 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 124:124 */    this.f = f;
/* 125:125 */    this.n = HashCommon.arraySize(expected, f);
/* 126:126 */    this.mask = (this.n - 1);
/* 127:127 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 128:128 */    this.key = ((Object[])new Object[this.n]);
/* 129:129 */    this.used = new boolean[this.n];
/* 130:130 */    this.link = new long[this.n];
/* 131:    */  }
/* 132:    */  
/* 136:    */  public ObjectLinkedOpenCustomHashSet(int expected, Hash.Strategy<K> strategy)
/* 137:    */  {
/* 138:138 */    this(expected, 0.75F, strategy);
/* 139:    */  }
/* 140:    */  
/* 143:    */  public ObjectLinkedOpenCustomHashSet(Hash.Strategy<K> strategy)
/* 144:    */  {
/* 145:145 */    this(16, 0.75F, strategy);
/* 146:    */  }
/* 147:    */  
/* 152:    */  public ObjectLinkedOpenCustomHashSet(Collection<? extends K> c, float f, Hash.Strategy<K> strategy)
/* 153:    */  {
/* 154:154 */    this(c.size(), f, strategy);
/* 155:155 */    addAll(c);
/* 156:    */  }
/* 157:    */  
/* 162:    */  public ObjectLinkedOpenCustomHashSet(Collection<? extends K> c, Hash.Strategy<K> strategy)
/* 163:    */  {
/* 164:164 */    this(c, 0.75F, strategy);
/* 165:    */  }
/* 166:    */  
/* 171:    */  public ObjectLinkedOpenCustomHashSet(ObjectCollection<? extends K> c, float f, Hash.Strategy<K> strategy)
/* 172:    */  {
/* 173:173 */    this(c.size(), f, strategy);
/* 174:174 */    addAll(c);
/* 175:    */  }
/* 176:    */  
/* 181:    */  public ObjectLinkedOpenCustomHashSet(ObjectCollection<? extends K> c, Hash.Strategy<K> strategy)
/* 182:    */  {
/* 183:183 */    this(c, 0.75F, strategy);
/* 184:    */  }
/* 185:    */  
/* 190:    */  public ObjectLinkedOpenCustomHashSet(ObjectIterator<K> i, float f, Hash.Strategy<K> strategy)
/* 191:    */  {
/* 192:192 */    this(16, f, strategy);
/* 193:193 */    while (i.hasNext()) { add(i.next());
/* 194:    */    }
/* 195:    */  }
/* 196:    */  
/* 199:    */  public ObjectLinkedOpenCustomHashSet(ObjectIterator<K> i, Hash.Strategy<K> strategy)
/* 200:    */  {
/* 201:201 */    this(i, 0.75F, strategy);
/* 202:    */  }
/* 203:    */  
/* 210:    */  public ObjectLinkedOpenCustomHashSet(K[] a, int offset, int length, float f, Hash.Strategy<K> strategy)
/* 211:    */  {
/* 212:212 */    this(length < 0 ? 0 : length, f, strategy);
/* 213:213 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 214:214 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 215:    */    }
/* 216:    */  }
/* 217:    */  
/* 222:    */  public ObjectLinkedOpenCustomHashSet(K[] a, int offset, int length, Hash.Strategy<K> strategy)
/* 223:    */  {
/* 224:224 */    this(a, offset, length, 0.75F, strategy);
/* 225:    */  }
/* 226:    */  
/* 231:    */  public ObjectLinkedOpenCustomHashSet(K[] a, float f, Hash.Strategy<K> strategy)
/* 232:    */  {
/* 233:233 */    this(a, 0, a.length, f, strategy);
/* 234:    */  }
/* 235:    */  
/* 240:    */  public ObjectLinkedOpenCustomHashSet(K[] a, Hash.Strategy<K> strategy)
/* 241:    */  {
/* 242:242 */    this(a, 0.75F, strategy);
/* 243:    */  }
/* 244:    */  
/* 247:    */  public Hash.Strategy<K> strategy()
/* 248:    */  {
/* 249:249 */    return this.strategy;
/* 250:    */  }
/* 251:    */  
/* 255:    */  public boolean add(K k)
/* 256:    */  {
/* 257:257 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 258:    */    
/* 259:259 */    while (this.used[pos] != 0) {
/* 260:260 */      if (this.strategy.equals(this.key[pos], k)) return false;
/* 261:261 */      pos = pos + 1 & this.mask;
/* 262:    */    }
/* 263:263 */    this.used[pos] = true;
/* 264:264 */    this.key[pos] = k;
/* 265:265 */    if (this.size == 0) {
/* 266:266 */      this.first = (this.last = pos);
/* 267:    */      
/* 268:268 */      this.link[pos] = -1L;
/* 269:    */    }
/* 270:    */    else {
/* 271:271 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 272:272 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 273:273 */      this.last = pos;
/* 274:    */    }
/* 275:275 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 276:    */    }
/* 277:277 */    return true;
/* 278:    */  }
/* 279:    */  
/* 282:    */  protected final int shiftKeys(int pos)
/* 283:    */  {
/* 284:    */    int last;
/* 285:    */    
/* 287:    */    for (;;)
/* 288:    */    {
/* 289:289 */      pos = (last = pos) + 1 & this.mask;
/* 290:290 */      while (this.used[pos] != 0) {
/* 291:291 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 292:292 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 293:293 */        pos = pos + 1 & this.mask;
/* 294:    */      }
/* 295:295 */      if (this.used[pos] == 0) break;
/* 296:296 */      this.key[last] = this.key[pos];
/* 297:297 */      fixPointers(pos, last);
/* 298:    */    }
/* 299:299 */    this.used[last] = false;
/* 300:300 */    this.key[last] = null;
/* 301:301 */    return last;
/* 302:    */  }
/* 303:    */  
/* 304:    */  public boolean remove(Object k)
/* 305:    */  {
/* 306:306 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 307:    */    
/* 308:308 */    while (this.used[pos] != 0) {
/* 309:309 */      if (this.strategy.equals(this.key[pos], k)) {
/* 310:310 */        this.size -= 1;
/* 311:311 */        fixPointers(pos);
/* 312:312 */        shiftKeys(pos);
/* 313:    */        
/* 314:314 */        return true;
/* 315:    */      }
/* 316:316 */      pos = pos + 1 & this.mask;
/* 317:    */    }
/* 318:318 */    return false;
/* 319:    */  }
/* 320:    */  
/* 321:    */  public boolean contains(Object k)
/* 322:    */  {
/* 323:323 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 324:    */    
/* 325:325 */    while (this.used[pos] != 0) {
/* 326:326 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 327:327 */      pos = pos + 1 & this.mask;
/* 328:    */    }
/* 329:329 */    return false;
/* 330:    */  }
/* 331:    */  
/* 335:    */  public K get(Object k)
/* 336:    */  {
/* 337:337 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 338:    */    
/* 339:339 */    while (this.used[pos] != 0) {
/* 340:340 */      if (this.strategy.equals(this.key[pos], k)) return this.key[pos];
/* 341:341 */      pos = pos + 1 & this.mask;
/* 342:    */    }
/* 343:343 */    return null;
/* 344:    */  }
/* 345:    */  
/* 348:    */  public K removeFirst()
/* 349:    */  {
/* 350:350 */    if (this.size == 0) throw new NoSuchElementException();
/* 351:351 */    this.size -= 1;
/* 352:352 */    int pos = this.first;
/* 353:    */    
/* 354:354 */    this.first = ((int)this.link[pos]);
/* 355:355 */    if (0 <= this.first)
/* 356:    */    {
/* 357:357 */      this.link[this.first] |= -4294967296L;
/* 358:    */    }
/* 359:359 */    K k = this.key[pos];
/* 360:360 */    shiftKeys(pos);
/* 361:361 */    return k;
/* 362:    */  }
/* 363:    */  
/* 366:    */  public K removeLast()
/* 367:    */  {
/* 368:368 */    if (this.size == 0) throw new NoSuchElementException();
/* 369:369 */    this.size -= 1;
/* 370:370 */    int pos = this.last;
/* 371:    */    
/* 372:372 */    this.last = ((int)(this.link[pos] >>> 32));
/* 373:373 */    if (0 <= this.last)
/* 374:    */    {
/* 375:375 */      this.link[this.last] |= 4294967295L;
/* 376:    */    }
/* 377:377 */    K k = this.key[pos];
/* 378:378 */    shiftKeys(pos);
/* 379:379 */    return k;
/* 380:    */  }
/* 381:    */  
/* 382:382 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/* 383:383 */    if (this.last == i) {
/* 384:384 */      this.last = ((int)(this.link[i] >>> 32));
/* 385:    */      
/* 386:386 */      this.link[this.last] |= 4294967295L;
/* 387:    */    }
/* 388:    */    else {
/* 389:389 */      long linki = this.link[i];
/* 390:390 */      int prev = (int)(linki >>> 32);
/* 391:391 */      int next = (int)linki;
/* 392:392 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 393:393 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 394:    */    }
/* 395:395 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 396:396 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 397:397 */    this.first = i;
/* 398:    */  }
/* 399:    */  
/* 400:400 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/* 401:401 */    if (this.first == i) {
/* 402:402 */      this.first = ((int)this.link[i]);
/* 403:    */      
/* 404:404 */      this.link[this.first] |= -4294967296L;
/* 405:    */    }
/* 406:    */    else {
/* 407:407 */      long linki = this.link[i];
/* 408:408 */      int prev = (int)(linki >>> 32);
/* 409:409 */      int next = (int)linki;
/* 410:410 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 411:411 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 412:    */    }
/* 413:413 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 414:414 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 415:415 */    this.last = i;
/* 416:    */  }
/* 417:    */  
/* 421:    */  public boolean addAndMoveToFirst(K k)
/* 422:    */  {
/* 423:423 */    K[] key = this.key;
/* 424:424 */    boolean[] used = this.used;
/* 425:425 */    int mask = this.mask;
/* 426:    */    
/* 427:427 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/* 428:    */    
/* 429:429 */    while (used[pos] != 0) {
/* 430:430 */      if (this.strategy.equals(k, key[pos])) {
/* 431:431 */        moveIndexToFirst(pos);
/* 432:432 */        return false;
/* 433:    */      }
/* 434:434 */      pos = pos + 1 & mask;
/* 435:    */    }
/* 436:436 */    used[pos] = true;
/* 437:437 */    key[pos] = k;
/* 438:438 */    if (this.size == 0) {
/* 439:439 */      this.first = (this.last = pos);
/* 440:    */      
/* 441:441 */      this.link[pos] = -1L;
/* 442:    */    }
/* 443:    */    else {
/* 444:444 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 445:445 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 446:446 */      this.first = pos;
/* 447:    */    }
/* 448:448 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 449:    */    }
/* 450:450 */    return true;
/* 451:    */  }
/* 452:    */  
/* 456:    */  public boolean addAndMoveToLast(K k)
/* 457:    */  {
/* 458:458 */    K[] key = this.key;
/* 459:459 */    boolean[] used = this.used;
/* 460:460 */    int mask = this.mask;
/* 461:    */    
/* 462:462 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/* 463:    */    
/* 464:464 */    while (used[pos] != 0) {
/* 465:465 */      if (this.strategy.equals(k, key[pos])) {
/* 466:466 */        moveIndexToLast(pos);
/* 467:467 */        return false;
/* 468:    */      }
/* 469:469 */      pos = pos + 1 & mask;
/* 470:    */    }
/* 471:471 */    used[pos] = true;
/* 472:472 */    key[pos] = k;
/* 473:473 */    if (this.size == 0) {
/* 474:474 */      this.first = (this.last = pos);
/* 475:    */      
/* 476:476 */      this.link[pos] = -1L;
/* 477:    */    }
/* 478:    */    else {
/* 479:479 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 480:480 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 481:481 */      this.last = pos;
/* 482:    */    }
/* 483:483 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 484:    */    }
/* 485:485 */    return true;
/* 486:    */  }
/* 487:    */  
/* 492:    */  public void clear()
/* 493:    */  {
/* 494:494 */    if (this.size == 0) return;
/* 495:495 */    this.size = 0;
/* 496:496 */    BooleanArrays.fill(this.used, false);
/* 497:497 */    ObjectArrays.fill(this.key, null);
/* 498:498 */    this.first = (this.last = -1);
/* 499:    */  }
/* 500:    */  
/* 501:501 */  public int size() { return this.size; }
/* 502:    */  
/* 503:    */  public boolean isEmpty() {
/* 504:504 */    return this.size == 0;
/* 505:    */  }
/* 506:    */  
/* 511:    */  @Deprecated
/* 512:    */  public void growthFactor(int growthFactor) {}
/* 513:    */  
/* 518:    */  @Deprecated
/* 519:    */  public int growthFactor()
/* 520:    */  {
/* 521:521 */    return 16;
/* 522:    */  }
/* 523:    */  
/* 529:    */  protected void fixPointers(int i)
/* 530:    */  {
/* 531:531 */    if (this.size == 0) {
/* 532:532 */      this.first = (this.last = -1);
/* 533:533 */      return;
/* 534:    */    }
/* 535:535 */    if (this.first == i) {
/* 536:536 */      this.first = ((int)this.link[i]);
/* 537:537 */      if (0 <= this.first)
/* 538:    */      {
/* 539:539 */        this.link[this.first] |= -4294967296L;
/* 540:    */      }
/* 541:541 */      return;
/* 542:    */    }
/* 543:543 */    if (this.last == i) {
/* 544:544 */      this.last = ((int)(this.link[i] >>> 32));
/* 545:545 */      if (0 <= this.last)
/* 546:    */      {
/* 547:547 */        this.link[this.last] |= 4294967295L;
/* 548:    */      }
/* 549:549 */      return;
/* 550:    */    }
/* 551:551 */    long linki = this.link[i];
/* 552:552 */    int prev = (int)(linki >>> 32);
/* 553:553 */    int next = (int)linki;
/* 554:554 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 555:555 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 556:    */  }
/* 557:    */  
/* 564:    */  protected void fixPointers(int s, int d)
/* 565:    */  {
/* 566:566 */    if (this.size == 1) {
/* 567:567 */      this.first = (this.last = d);
/* 568:    */      
/* 569:569 */      this.link[d] = -1L;
/* 570:570 */      return;
/* 571:    */    }
/* 572:572 */    if (this.first == s) {
/* 573:573 */      this.first = d;
/* 574:574 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 575:575 */      this.link[d] = this.link[s];
/* 576:576 */      return;
/* 577:    */    }
/* 578:578 */    if (this.last == s) {
/* 579:579 */      this.last = d;
/* 580:580 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 581:581 */      this.link[d] = this.link[s];
/* 582:582 */      return;
/* 583:    */    }
/* 584:584 */    long links = this.link[s];
/* 585:585 */    int prev = (int)(links >>> 32);
/* 586:586 */    int next = (int)links;
/* 587:587 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 588:588 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 589:589 */    this.link[d] = links;
/* 590:    */  }
/* 591:    */  
/* 594:    */  public K first()
/* 595:    */  {
/* 596:596 */    if (this.size == 0) throw new NoSuchElementException();
/* 597:597 */    return this.key[this.first];
/* 598:    */  }
/* 599:    */  
/* 602:    */  public K last()
/* 603:    */  {
/* 604:604 */    if (this.size == 0) throw new NoSuchElementException();
/* 605:605 */    return this.key[this.last]; }
/* 606:    */  
/* 607:607 */  public ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/* 608:608 */  public ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/* 609:609 */  public ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/* 610:610 */  public Comparator<? super K> comparator() { return null; }
/* 611:    */  
/* 616:    */  private class SetIterator
/* 617:    */    extends AbstractObjectListIterator<K>
/* 618:    */  {
/* 619:619 */    int prev = -1;
/* 620:    */    
/* 621:621 */    int next = -1;
/* 622:    */    
/* 623:623 */    int curr = -1;
/* 624:    */    
/* 625:625 */    int index = -1;
/* 626:    */    
/* 627:627 */    SetIterator() { this.next = ObjectLinkedOpenCustomHashSet.this.first;
/* 628:628 */      this.index = 0;
/* 629:    */    }
/* 630:    */    
/* 631:631 */    SetIterator() { if (ObjectLinkedOpenCustomHashSet.this.strategy.equals(ObjectLinkedOpenCustomHashSet.this.key[ObjectLinkedOpenCustomHashSet.this.last], from)) {
/* 632:632 */        this.prev = ObjectLinkedOpenCustomHashSet.this.last;
/* 633:633 */        this.index = ObjectLinkedOpenCustomHashSet.this.size;
/* 634:    */      }
/* 635:    */      else
/* 636:    */      {
/* 637:637 */        int pos = HashCommon.murmurHash3(ObjectLinkedOpenCustomHashSet.this.strategy.hashCode(from)) & ObjectLinkedOpenCustomHashSet.this.mask;
/* 638:    */        
/* 639:639 */        while (ObjectLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 640:640 */          if (ObjectLinkedOpenCustomHashSet.this.strategy.equals(ObjectLinkedOpenCustomHashSet.this.key[pos], from))
/* 641:    */          {
/* 642:642 */            this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[pos]);
/* 643:643 */            this.prev = pos;
/* 644:644 */            return;
/* 645:    */          }
/* 646:646 */          pos = pos + 1 & ObjectLinkedOpenCustomHashSet.this.mask;
/* 647:    */        }
/* 648:648 */        throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/* 649:    */      } }
/* 650:    */    
/* 651:651 */    public boolean hasNext() { return this.next != -1; }
/* 652:652 */    public boolean hasPrevious() { return this.prev != -1; }
/* 653:    */    
/* 654:654 */    public K next() { if (!hasNext()) throw new NoSuchElementException();
/* 655:655 */      this.curr = this.next;
/* 656:656 */      this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[this.curr]);
/* 657:657 */      this.prev = this.curr;
/* 658:658 */      if (this.index >= 0) { this.index += 1;
/* 659:    */      }
/* 660:660 */      return ObjectLinkedOpenCustomHashSet.this.key[this.curr];
/* 661:    */    }
/* 662:    */    
/* 663:663 */    public K previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 664:664 */      this.curr = this.prev;
/* 665:665 */      this.prev = ((int)(ObjectLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/* 666:666 */      this.next = this.curr;
/* 667:667 */      if (this.index >= 0) this.index -= 1;
/* 668:668 */      return ObjectLinkedOpenCustomHashSet.this.key[this.curr];
/* 669:    */    }
/* 670:    */    
/* 671:671 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/* 672:672 */      if (this.prev == -1) {
/* 673:673 */        this.index = 0;
/* 674:674 */        return;
/* 675:    */      }
/* 676:676 */      if (this.next == -1) {
/* 677:677 */        this.index = ObjectLinkedOpenCustomHashSet.this.size;
/* 678:678 */        return;
/* 679:    */      }
/* 680:680 */      int pos = ObjectLinkedOpenCustomHashSet.this.first;
/* 681:681 */      this.index = 1;
/* 682:682 */      while (pos != this.prev) {
/* 683:683 */        pos = (int)ObjectLinkedOpenCustomHashSet.this.link[pos];
/* 684:684 */        this.index += 1;
/* 685:    */      }
/* 686:    */    }
/* 687:    */    
/* 688:688 */    public int nextIndex() { ensureIndexKnown();
/* 689:689 */      return this.index;
/* 690:    */    }
/* 691:    */    
/* 692:692 */    public int previousIndex() { ensureIndexKnown();
/* 693:693 */      return this.index - 1;
/* 694:    */    }
/* 695:    */    
/* 696:    */    public void remove() {
/* 697:697 */      ensureIndexKnown();
/* 698:698 */      if (this.curr == -1) throw new IllegalStateException();
/* 699:699 */      if (this.curr == this.prev)
/* 700:    */      {
/* 702:702 */        this.index -= 1;
/* 703:703 */        this.prev = ((int)(ObjectLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/* 704:    */      }
/* 705:    */      else {
/* 706:706 */        this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[this.curr]); }
/* 707:707 */      ObjectLinkedOpenCustomHashSet.this.size -= 1;
/* 708:    */      
/* 710:710 */      if (this.prev == -1) { ObjectLinkedOpenCustomHashSet.this.first = this.next;
/* 711:    */      } else
/* 712:712 */        ObjectLinkedOpenCustomHashSet.this.link[this.prev] ^= (ObjectLinkedOpenCustomHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 713:713 */      if (this.next == -1) { ObjectLinkedOpenCustomHashSet.this.last = this.prev;
/* 714:    */      } else
/* 715:715 */        ObjectLinkedOpenCustomHashSet.this.link[this.next] ^= (ObjectLinkedOpenCustomHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/* 716:716 */      int pos = this.curr;
/* 717:    */      int last;
/* 718:    */      for (;;) {
/* 719:719 */        pos = (last = pos) + 1 & ObjectLinkedOpenCustomHashSet.this.mask;
/* 720:720 */        while (ObjectLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 721:721 */          int slot = HashCommon.murmurHash3(ObjectLinkedOpenCustomHashSet.this.strategy.hashCode(ObjectLinkedOpenCustomHashSet.this.key[pos])) & ObjectLinkedOpenCustomHashSet.this.mask;
/* 722:722 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 723:723 */          pos = pos + 1 & ObjectLinkedOpenCustomHashSet.this.mask;
/* 724:    */        }
/* 725:725 */        if (ObjectLinkedOpenCustomHashSet.this.used[pos] == 0) break;
/* 726:726 */        ObjectLinkedOpenCustomHashSet.this.key[last] = ObjectLinkedOpenCustomHashSet.this.key[pos];
/* 727:727 */        if (this.next == pos) this.next = last;
/* 728:728 */        if (this.prev == pos) this.prev = last;
/* 729:729 */        ObjectLinkedOpenCustomHashSet.this.fixPointers(pos, last);
/* 730:    */      }
/* 731:731 */      ObjectLinkedOpenCustomHashSet.this.used[last] = false;
/* 732:732 */      ObjectLinkedOpenCustomHashSet.this.key[last] = null;
/* 733:733 */      this.curr = -1;
/* 734:    */    }
/* 735:    */  }
/* 736:    */  
/* 742:    */  public ObjectListIterator<K> iterator(K from)
/* 743:    */  {
/* 744:744 */    return new SetIterator(from);
/* 745:    */  }
/* 746:    */  
/* 747:747 */  public ObjectListIterator<K> iterator() { return new SetIterator(); }
/* 748:    */  
/* 758:    */  @Deprecated
/* 759:    */  public boolean rehash()
/* 760:    */  {
/* 761:761 */    return true;
/* 762:    */  }
/* 763:    */  
/* 774:    */  public boolean trim()
/* 775:    */  {
/* 776:776 */    int l = HashCommon.arraySize(this.size, this.f);
/* 777:777 */    if (l >= this.n) return true;
/* 778:    */    try {
/* 779:779 */      rehash(l);
/* 780:    */    } catch (OutOfMemoryError cantDoIt) {
/* 781:781 */      return false; }
/* 782:782 */    return true;
/* 783:    */  }
/* 784:    */  
/* 801:    */  public boolean trim(int n)
/* 802:    */  {
/* 803:803 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 804:804 */    if (this.n <= l) return true;
/* 805:    */    try {
/* 806:806 */      rehash(l);
/* 807:    */    } catch (OutOfMemoryError cantDoIt) {
/* 808:808 */      return false; }
/* 809:809 */    return true;
/* 810:    */  }
/* 811:    */  
/* 820:    */  protected void rehash(int newN)
/* 821:    */  {
/* 822:822 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 823:    */    
/* 824:824 */    K[] key = this.key;
/* 825:825 */    int newMask = newN - 1;
/* 826:826 */    K[] newKey = (Object[])new Object[newN];
/* 827:827 */    boolean[] newUsed = new boolean[newN];
/* 828:828 */    long[] link = this.link;
/* 829:829 */    long[] newLink = new long[newN];
/* 830:830 */    this.first = -1;
/* 831:831 */    for (int j = this.size; j-- != 0;) {
/* 832:832 */      K k = key[i];
/* 833:833 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 834:834 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 835:835 */      newUsed[pos] = true;
/* 836:836 */      newKey[pos] = k;
/* 837:837 */      if (prev != -1) {
/* 838:838 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 839:839 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 840:840 */        newPrev = pos;
/* 841:    */      }
/* 842:    */      else {
/* 843:843 */        newPrev = this.first = pos;
/* 844:    */        
/* 845:845 */        newLink[pos] = -1L;
/* 846:    */      }
/* 847:847 */      int t = i;
/* 848:848 */      i = (int)link[i];
/* 849:849 */      prev = t;
/* 850:    */    }
/* 851:851 */    this.n = newN;
/* 852:852 */    this.mask = newMask;
/* 853:853 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 854:854 */    this.key = newKey;
/* 855:855 */    this.used = newUsed;
/* 856:856 */    this.link = newLink;
/* 857:857 */    this.last = newPrev;
/* 858:858 */    if (newPrev != -1)
/* 859:    */    {
/* 860:860 */      newLink[newPrev] |= 4294967295L;
/* 861:    */    }
/* 862:    */  }
/* 863:    */  
/* 866:    */  public ObjectLinkedOpenCustomHashSet<K> clone()
/* 867:    */  {
/* 868:    */    ObjectLinkedOpenCustomHashSet<K> c;
/* 869:    */    
/* 871:    */    try
/* 872:    */    {
/* 873:873 */      c = (ObjectLinkedOpenCustomHashSet)super.clone();
/* 874:    */    }
/* 875:    */    catch (CloneNotSupportedException cantHappen) {
/* 876:876 */      throw new InternalError();
/* 877:    */    }
/* 878:878 */    c.key = ((Object[])this.key.clone());
/* 879:879 */    c.used = ((boolean[])this.used.clone());
/* 880:880 */    c.link = ((long[])this.link.clone());
/* 881:881 */    c.strategy = this.strategy;
/* 882:882 */    return c;
/* 883:    */  }
/* 884:    */  
/* 892:    */  public int hashCode()
/* 893:    */  {
/* 894:894 */    int h = 0;int i = 0;int j = this.size;
/* 895:895 */    while (j-- != 0) {
/* 896:896 */      while (this.used[i] == 0) i++;
/* 897:897 */      if (this != this.key[i])
/* 898:898 */        h += this.strategy.hashCode(this.key[i]);
/* 899:899 */      i++;
/* 900:    */    }
/* 901:901 */    return h;
/* 902:    */  }
/* 903:    */  
/* 904:904 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 905:905 */    s.defaultWriteObject();
/* 906:906 */    for (int j = this.size; j-- != 0; s.writeObject(i.next())) {}
/* 907:    */  }
/* 908:    */  
/* 909:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 910:910 */    s.defaultReadObject();
/* 911:911 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 912:912 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 913:913 */    this.mask = (this.n - 1);
/* 914:914 */    K[] key = this.key = (Object[])new Object[this.n];
/* 915:915 */    boolean[] used = this.used = new boolean[this.n];
/* 916:916 */    long[] link = this.link = new long[this.n];
/* 917:917 */    int prev = -1;
/* 918:918 */    this.first = (this.last = -1);
/* 919:    */    
/* 920:920 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 921:921 */      K k = s.readObject();
/* 922:922 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 923:923 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 924:924 */      used[pos] = true;
/* 925:925 */      key[pos] = k;
/* 926:926 */      if (this.first != -1) {
/* 927:927 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 928:928 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 929:929 */        prev = pos;
/* 930:    */      }
/* 931:    */      else {
/* 932:932 */        prev = this.first = pos;
/* 933:    */        
/* 934:934 */        link[pos] |= -4294967296L;
/* 935:    */      }
/* 936:    */    }
/* 937:937 */    this.last = prev;
/* 938:938 */    if (prev != -1)
/* 939:    */    {
/* 940:940 */      link[prev] |= 4294967295L;
/* 941:    */    }
/* 942:    */  }
/* 943:    */  
/* 944:    */  private void checkTable() {}
/* 945:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLinkedOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */