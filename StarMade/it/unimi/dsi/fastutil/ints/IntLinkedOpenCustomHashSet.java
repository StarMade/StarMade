/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Collection;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  89:    */public class IntLinkedOpenCustomHashSet
/*  90:    */  extends AbstractIntSortedSet
/*  91:    */  implements Serializable, Cloneable, Hash
/*  92:    */{
/*  93:    */  public static final long serialVersionUID = 0L;
/*  94:    */  private static final boolean ASSERTS = false;
/*  95:    */  protected transient int[] key;
/*  96:    */  protected transient boolean[] used;
/*  97:    */  protected final float f;
/*  98:    */  protected transient int n;
/*  99:    */  protected transient int maxFill;
/* 100:    */  protected transient int mask;
/* 101:    */  protected int size;
/* 102:102 */  protected transient int first = -1;
/* 103:    */  
/* 104:104 */  protected transient int last = -1;
/* 105:    */  
/* 109:    */  protected transient long[] link;
/* 110:    */  
/* 114:    */  protected IntHash.Strategy strategy;
/* 115:    */  
/* 120:    */  public IntLinkedOpenCustomHashSet(int expected, float f, IntHash.Strategy strategy)
/* 121:    */  {
/* 122:122 */    this.strategy = strategy;
/* 123:123 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 124:124 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 125:125 */    this.f = f;
/* 126:126 */    this.n = HashCommon.arraySize(expected, f);
/* 127:127 */    this.mask = (this.n - 1);
/* 128:128 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 129:129 */    this.key = new int[this.n];
/* 130:130 */    this.used = new boolean[this.n];
/* 131:131 */    this.link = new long[this.n];
/* 132:    */  }
/* 133:    */  
/* 137:    */  public IntLinkedOpenCustomHashSet(int expected, IntHash.Strategy strategy)
/* 138:    */  {
/* 139:139 */    this(expected, 0.75F, strategy);
/* 140:    */  }
/* 141:    */  
/* 144:    */  public IntLinkedOpenCustomHashSet(IntHash.Strategy strategy)
/* 145:    */  {
/* 146:146 */    this(16, 0.75F, strategy);
/* 147:    */  }
/* 148:    */  
/* 153:    */  public IntLinkedOpenCustomHashSet(Collection<? extends Integer> c, float f, IntHash.Strategy strategy)
/* 154:    */  {
/* 155:155 */    this(c.size(), f, strategy);
/* 156:156 */    addAll(c);
/* 157:    */  }
/* 158:    */  
/* 163:    */  public IntLinkedOpenCustomHashSet(Collection<? extends Integer> c, IntHash.Strategy strategy)
/* 164:    */  {
/* 165:165 */    this(c, 0.75F, strategy);
/* 166:    */  }
/* 167:    */  
/* 172:    */  public IntLinkedOpenCustomHashSet(IntCollection c, float f, IntHash.Strategy strategy)
/* 173:    */  {
/* 174:174 */    this(c.size(), f, strategy);
/* 175:175 */    addAll(c);
/* 176:    */  }
/* 177:    */  
/* 182:    */  public IntLinkedOpenCustomHashSet(IntCollection c, IntHash.Strategy strategy)
/* 183:    */  {
/* 184:184 */    this(c, 0.75F, strategy);
/* 185:    */  }
/* 186:    */  
/* 191:    */  public IntLinkedOpenCustomHashSet(IntIterator i, float f, IntHash.Strategy strategy)
/* 192:    */  {
/* 193:193 */    this(16, f, strategy);
/* 194:194 */    while (i.hasNext()) { add(i.nextInt());
/* 195:    */    }
/* 196:    */  }
/* 197:    */  
/* 200:    */  public IntLinkedOpenCustomHashSet(IntIterator i, IntHash.Strategy strategy)
/* 201:    */  {
/* 202:202 */    this(i, 0.75F, strategy);
/* 203:    */  }
/* 204:    */  
/* 209:    */  public IntLinkedOpenCustomHashSet(Iterator<?> i, float f, IntHash.Strategy strategy)
/* 210:    */  {
/* 211:211 */    this(IntIterators.asIntIterator(i), f, strategy);
/* 212:    */  }
/* 213:    */  
/* 217:    */  public IntLinkedOpenCustomHashSet(Iterator<?> i, IntHash.Strategy strategy)
/* 218:    */  {
/* 219:219 */    this(IntIterators.asIntIterator(i), strategy);
/* 220:    */  }
/* 221:    */  
/* 228:    */  public IntLinkedOpenCustomHashSet(int[] a, int offset, int length, float f, IntHash.Strategy strategy)
/* 229:    */  {
/* 230:230 */    this(length < 0 ? 0 : length, f, strategy);
/* 231:231 */    IntArrays.ensureOffsetLength(a, offset, length);
/* 232:232 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 233:    */    }
/* 234:    */  }
/* 235:    */  
/* 240:    */  public IntLinkedOpenCustomHashSet(int[] a, int offset, int length, IntHash.Strategy strategy)
/* 241:    */  {
/* 242:242 */    this(a, offset, length, 0.75F, strategy);
/* 243:    */  }
/* 244:    */  
/* 249:    */  public IntLinkedOpenCustomHashSet(int[] a, float f, IntHash.Strategy strategy)
/* 250:    */  {
/* 251:251 */    this(a, 0, a.length, f, strategy);
/* 252:    */  }
/* 253:    */  
/* 258:    */  public IntLinkedOpenCustomHashSet(int[] a, IntHash.Strategy strategy)
/* 259:    */  {
/* 260:260 */    this(a, 0.75F, strategy);
/* 261:    */  }
/* 262:    */  
/* 265:    */  public IntHash.Strategy strategy()
/* 266:    */  {
/* 267:267 */    return this.strategy;
/* 268:    */  }
/* 269:    */  
/* 273:    */  public boolean add(int k)
/* 274:    */  {
/* 275:275 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 276:    */    
/* 277:277 */    while (this.used[pos] != 0) {
/* 278:278 */      if (this.strategy.equals(this.key[pos], k)) return false;
/* 279:279 */      pos = pos + 1 & this.mask;
/* 280:    */    }
/* 281:281 */    this.used[pos] = true;
/* 282:282 */    this.key[pos] = k;
/* 283:283 */    if (this.size == 0) {
/* 284:284 */      this.first = (this.last = pos);
/* 285:    */      
/* 286:286 */      this.link[pos] = -1L;
/* 287:    */    }
/* 288:    */    else {
/* 289:289 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 290:290 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 291:291 */      this.last = pos;
/* 292:    */    }
/* 293:293 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 294:    */    }
/* 295:295 */    return true;
/* 296:    */  }
/* 297:    */  
/* 300:    */  protected final int shiftKeys(int pos)
/* 301:    */  {
/* 302:    */    int last;
/* 303:    */    
/* 305:    */    for (;;)
/* 306:    */    {
/* 307:307 */      pos = (last = pos) + 1 & this.mask;
/* 308:308 */      while (this.used[pos] != 0) {
/* 309:309 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 310:310 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 311:311 */        pos = pos + 1 & this.mask;
/* 312:    */      }
/* 313:313 */      if (this.used[pos] == 0) break;
/* 314:314 */      this.key[last] = this.key[pos];
/* 315:315 */      fixPointers(pos, last);
/* 316:    */    }
/* 317:317 */    this.used[last] = false;
/* 318:318 */    return last;
/* 319:    */  }
/* 320:    */  
/* 321:    */  public boolean remove(int k)
/* 322:    */  {
/* 323:323 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 324:    */    
/* 325:325 */    while (this.used[pos] != 0) {
/* 326:326 */      if (this.strategy.equals(this.key[pos], k)) {
/* 327:327 */        this.size -= 1;
/* 328:328 */        fixPointers(pos);
/* 329:329 */        shiftKeys(pos);
/* 330:    */        
/* 331:331 */        return true;
/* 332:    */      }
/* 333:333 */      pos = pos + 1 & this.mask;
/* 334:    */    }
/* 335:335 */    return false;
/* 336:    */  }
/* 337:    */  
/* 338:    */  public boolean contains(int k)
/* 339:    */  {
/* 340:340 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 341:    */    
/* 342:342 */    while (this.used[pos] != 0) {
/* 343:343 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 344:344 */      pos = pos + 1 & this.mask;
/* 345:    */    }
/* 346:346 */    return false;
/* 347:    */  }
/* 348:    */  
/* 351:    */  public int removeFirstInt()
/* 352:    */  {
/* 353:353 */    if (this.size == 0) throw new NoSuchElementException();
/* 354:354 */    this.size -= 1;
/* 355:355 */    int pos = this.first;
/* 356:    */    
/* 357:357 */    this.first = ((int)this.link[pos]);
/* 358:358 */    if (0 <= this.first)
/* 359:    */    {
/* 360:360 */      this.link[this.first] |= -4294967296L;
/* 361:    */    }
/* 362:362 */    int k = this.key[pos];
/* 363:363 */    shiftKeys(pos);
/* 364:364 */    return k;
/* 365:    */  }
/* 366:    */  
/* 369:    */  public int removeLastInt()
/* 370:    */  {
/* 371:371 */    if (this.size == 0) throw new NoSuchElementException();
/* 372:372 */    this.size -= 1;
/* 373:373 */    int pos = this.last;
/* 374:    */    
/* 375:375 */    this.last = ((int)(this.link[pos] >>> 32));
/* 376:376 */    if (0 <= this.last)
/* 377:    */    {
/* 378:378 */      this.link[this.last] |= 4294967295L;
/* 379:    */    }
/* 380:380 */    int k = this.key[pos];
/* 381:381 */    shiftKeys(pos);
/* 382:382 */    return k;
/* 383:    */  }
/* 384:    */  
/* 385:385 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/* 386:386 */    if (this.last == i) {
/* 387:387 */      this.last = ((int)(this.link[i] >>> 32));
/* 388:    */      
/* 389:389 */      this.link[this.last] |= 4294967295L;
/* 390:    */    }
/* 391:    */    else {
/* 392:392 */      long linki = this.link[i];
/* 393:393 */      int prev = (int)(linki >>> 32);
/* 394:394 */      int next = (int)linki;
/* 395:395 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 396:396 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 397:    */    }
/* 398:398 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 399:399 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 400:400 */    this.first = i;
/* 401:    */  }
/* 402:    */  
/* 403:403 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/* 404:404 */    if (this.first == i) {
/* 405:405 */      this.first = ((int)this.link[i]);
/* 406:    */      
/* 407:407 */      this.link[this.first] |= -4294967296L;
/* 408:    */    }
/* 409:    */    else {
/* 410:410 */      long linki = this.link[i];
/* 411:411 */      int prev = (int)(linki >>> 32);
/* 412:412 */      int next = (int)linki;
/* 413:413 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 414:414 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 415:    */    }
/* 416:416 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 417:417 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 418:418 */    this.last = i;
/* 419:    */  }
/* 420:    */  
/* 424:    */  public boolean addAndMoveToFirst(int k)
/* 425:    */  {
/* 426:426 */    int[] key = this.key;
/* 427:427 */    boolean[] used = this.used;
/* 428:428 */    int mask = this.mask;
/* 429:    */    
/* 430:430 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/* 431:    */    
/* 432:432 */    while (used[pos] != 0) {
/* 433:433 */      if (this.strategy.equals(k, key[pos])) {
/* 434:434 */        moveIndexToFirst(pos);
/* 435:435 */        return false;
/* 436:    */      }
/* 437:437 */      pos = pos + 1 & mask;
/* 438:    */    }
/* 439:439 */    used[pos] = true;
/* 440:440 */    key[pos] = k;
/* 441:441 */    if (this.size == 0) {
/* 442:442 */      this.first = (this.last = pos);
/* 443:    */      
/* 444:444 */      this.link[pos] = -1L;
/* 445:    */    }
/* 446:    */    else {
/* 447:447 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 448:448 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 449:449 */      this.first = pos;
/* 450:    */    }
/* 451:451 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 452:    */    }
/* 453:453 */    return true;
/* 454:    */  }
/* 455:    */  
/* 459:    */  public boolean addAndMoveToLast(int k)
/* 460:    */  {
/* 461:461 */    int[] key = this.key;
/* 462:462 */    boolean[] used = this.used;
/* 463:463 */    int mask = this.mask;
/* 464:    */    
/* 465:465 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/* 466:    */    
/* 467:467 */    while (used[pos] != 0) {
/* 468:468 */      if (this.strategy.equals(k, key[pos])) {
/* 469:469 */        moveIndexToLast(pos);
/* 470:470 */        return false;
/* 471:    */      }
/* 472:472 */      pos = pos + 1 & mask;
/* 473:    */    }
/* 474:474 */    used[pos] = true;
/* 475:475 */    key[pos] = k;
/* 476:476 */    if (this.size == 0) {
/* 477:477 */      this.first = (this.last = pos);
/* 478:    */      
/* 479:479 */      this.link[pos] = -1L;
/* 480:    */    }
/* 481:    */    else {
/* 482:482 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 483:483 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 484:484 */      this.last = pos;
/* 485:    */    }
/* 486:486 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 487:    */    }
/* 488:488 */    return true;
/* 489:    */  }
/* 490:    */  
/* 495:    */  public void clear()
/* 496:    */  {
/* 497:497 */    if (this.size == 0) return;
/* 498:498 */    this.size = 0;
/* 499:499 */    BooleanArrays.fill(this.used, false);
/* 500:500 */    this.first = (this.last = -1);
/* 501:    */  }
/* 502:    */  
/* 503:503 */  public int size() { return this.size; }
/* 504:    */  
/* 505:    */  public boolean isEmpty() {
/* 506:506 */    return this.size == 0;
/* 507:    */  }
/* 508:    */  
/* 513:    */  @Deprecated
/* 514:    */  public void growthFactor(int growthFactor) {}
/* 515:    */  
/* 520:    */  @Deprecated
/* 521:    */  public int growthFactor()
/* 522:    */  {
/* 523:523 */    return 16;
/* 524:    */  }
/* 525:    */  
/* 531:    */  protected void fixPointers(int i)
/* 532:    */  {
/* 533:533 */    if (this.size == 0) {
/* 534:534 */      this.first = (this.last = -1);
/* 535:535 */      return;
/* 536:    */    }
/* 537:537 */    if (this.first == i) {
/* 538:538 */      this.first = ((int)this.link[i]);
/* 539:539 */      if (0 <= this.first)
/* 540:    */      {
/* 541:541 */        this.link[this.first] |= -4294967296L;
/* 542:    */      }
/* 543:543 */      return;
/* 544:    */    }
/* 545:545 */    if (this.last == i) {
/* 546:546 */      this.last = ((int)(this.link[i] >>> 32));
/* 547:547 */      if (0 <= this.last)
/* 548:    */      {
/* 549:549 */        this.link[this.last] |= 4294967295L;
/* 550:    */      }
/* 551:551 */      return;
/* 552:    */    }
/* 553:553 */    long linki = this.link[i];
/* 554:554 */    int prev = (int)(linki >>> 32);
/* 555:555 */    int next = (int)linki;
/* 556:556 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 557:557 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 558:    */  }
/* 559:    */  
/* 566:    */  protected void fixPointers(int s, int d)
/* 567:    */  {
/* 568:568 */    if (this.size == 1) {
/* 569:569 */      this.first = (this.last = d);
/* 570:    */      
/* 571:571 */      this.link[d] = -1L;
/* 572:572 */      return;
/* 573:    */    }
/* 574:574 */    if (this.first == s) {
/* 575:575 */      this.first = d;
/* 576:576 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 577:577 */      this.link[d] = this.link[s];
/* 578:578 */      return;
/* 579:    */    }
/* 580:580 */    if (this.last == s) {
/* 581:581 */      this.last = d;
/* 582:582 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 583:583 */      this.link[d] = this.link[s];
/* 584:584 */      return;
/* 585:    */    }
/* 586:586 */    long links = this.link[s];
/* 587:587 */    int prev = (int)(links >>> 32);
/* 588:588 */    int next = (int)links;
/* 589:589 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 590:590 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 591:591 */    this.link[d] = links;
/* 592:    */  }
/* 593:    */  
/* 596:    */  public int firstInt()
/* 597:    */  {
/* 598:598 */    if (this.size == 0) throw new NoSuchElementException();
/* 599:599 */    return this.key[this.first];
/* 600:    */  }
/* 601:    */  
/* 604:    */  public int lastInt()
/* 605:    */  {
/* 606:606 */    if (this.size == 0) throw new NoSuchElementException();
/* 607:607 */    return this.key[this.last]; }
/* 608:    */  
/* 609:609 */  public IntSortedSet tailSet(int from) { throw new UnsupportedOperationException(); }
/* 610:610 */  public IntSortedSet headSet(int to) { throw new UnsupportedOperationException(); }
/* 611:611 */  public IntSortedSet subSet(int from, int to) { throw new UnsupportedOperationException(); }
/* 612:612 */  public IntComparator comparator() { return null; }
/* 613:    */  
/* 618:    */  private class SetIterator
/* 619:    */    extends AbstractIntListIterator
/* 620:    */  {
/* 621:621 */    int prev = -1;
/* 622:    */    
/* 623:623 */    int next = -1;
/* 624:    */    
/* 625:625 */    int curr = -1;
/* 626:    */    
/* 627:627 */    int index = -1;
/* 628:    */    
/* 629:629 */    SetIterator() { this.next = IntLinkedOpenCustomHashSet.this.first;
/* 630:630 */      this.index = 0;
/* 631:    */    }
/* 632:    */    
/* 633:633 */    SetIterator(int from) { if (IntLinkedOpenCustomHashSet.this.strategy.equals(IntLinkedOpenCustomHashSet.this.key[IntLinkedOpenCustomHashSet.this.last], from)) {
/* 634:634 */        this.prev = IntLinkedOpenCustomHashSet.this.last;
/* 635:635 */        this.index = IntLinkedOpenCustomHashSet.this.size;
/* 636:    */      }
/* 637:    */      else
/* 638:    */      {
/* 639:639 */        int pos = HashCommon.murmurHash3(IntLinkedOpenCustomHashSet.this.strategy.hashCode(from)) & IntLinkedOpenCustomHashSet.this.mask;
/* 640:    */        
/* 641:641 */        while (IntLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 642:642 */          if (IntLinkedOpenCustomHashSet.this.strategy.equals(IntLinkedOpenCustomHashSet.this.key[pos], from))
/* 643:    */          {
/* 644:644 */            this.next = ((int)IntLinkedOpenCustomHashSet.this.link[pos]);
/* 645:645 */            this.prev = pos;
/* 646:646 */            return;
/* 647:    */          }
/* 648:648 */          pos = pos + 1 & IntLinkedOpenCustomHashSet.this.mask;
/* 649:    */        }
/* 650:650 */        throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/* 651:    */      } }
/* 652:    */    
/* 653:653 */    public boolean hasNext() { return this.next != -1; }
/* 654:654 */    public boolean hasPrevious() { return this.prev != -1; }
/* 655:    */    
/* 656:656 */    public int nextInt() { if (!hasNext()) throw new NoSuchElementException();
/* 657:657 */      this.curr = this.next;
/* 658:658 */      this.next = ((int)IntLinkedOpenCustomHashSet.this.link[this.curr]);
/* 659:659 */      this.prev = this.curr;
/* 660:660 */      if (this.index >= 0) { this.index += 1;
/* 661:    */      }
/* 662:662 */      return IntLinkedOpenCustomHashSet.this.key[this.curr];
/* 663:    */    }
/* 664:    */    
/* 665:665 */    public int previousInt() { if (!hasPrevious()) throw new NoSuchElementException();
/* 666:666 */      this.curr = this.prev;
/* 667:667 */      this.prev = ((int)(IntLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/* 668:668 */      this.next = this.curr;
/* 669:669 */      if (this.index >= 0) this.index -= 1;
/* 670:670 */      return IntLinkedOpenCustomHashSet.this.key[this.curr];
/* 671:    */    }
/* 672:    */    
/* 673:673 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/* 674:674 */      if (this.prev == -1) {
/* 675:675 */        this.index = 0;
/* 676:676 */        return;
/* 677:    */      }
/* 678:678 */      if (this.next == -1) {
/* 679:679 */        this.index = IntLinkedOpenCustomHashSet.this.size;
/* 680:680 */        return;
/* 681:    */      }
/* 682:682 */      int pos = IntLinkedOpenCustomHashSet.this.first;
/* 683:683 */      this.index = 1;
/* 684:684 */      while (pos != this.prev) {
/* 685:685 */        pos = (int)IntLinkedOpenCustomHashSet.this.link[pos];
/* 686:686 */        this.index += 1;
/* 687:    */      }
/* 688:    */    }
/* 689:    */    
/* 690:690 */    public int nextIndex() { ensureIndexKnown();
/* 691:691 */      return this.index;
/* 692:    */    }
/* 693:    */    
/* 694:694 */    public int previousIndex() { ensureIndexKnown();
/* 695:695 */      return this.index - 1;
/* 696:    */    }
/* 697:    */    
/* 698:    */    public void remove() {
/* 699:699 */      ensureIndexKnown();
/* 700:700 */      if (this.curr == -1) throw new IllegalStateException();
/* 701:701 */      if (this.curr == this.prev)
/* 702:    */      {
/* 704:704 */        this.index -= 1;
/* 705:705 */        this.prev = ((int)(IntLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/* 706:    */      }
/* 707:    */      else {
/* 708:708 */        this.next = ((int)IntLinkedOpenCustomHashSet.this.link[this.curr]); }
/* 709:709 */      IntLinkedOpenCustomHashSet.this.size -= 1;
/* 710:    */      
/* 712:712 */      if (this.prev == -1) { IntLinkedOpenCustomHashSet.this.first = this.next;
/* 713:    */      } else
/* 714:714 */        IntLinkedOpenCustomHashSet.this.link[this.prev] ^= (IntLinkedOpenCustomHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 715:715 */      if (this.next == -1) { IntLinkedOpenCustomHashSet.this.last = this.prev;
/* 716:    */      } else
/* 717:717 */        IntLinkedOpenCustomHashSet.this.link[this.next] ^= (IntLinkedOpenCustomHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/* 718:718 */      int pos = this.curr;
/* 719:    */      int last;
/* 720:    */      for (;;) {
/* 721:721 */        pos = (last = pos) + 1 & IntLinkedOpenCustomHashSet.this.mask;
/* 722:722 */        while (IntLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 723:723 */          int slot = HashCommon.murmurHash3(IntLinkedOpenCustomHashSet.this.strategy.hashCode(IntLinkedOpenCustomHashSet.this.key[pos])) & IntLinkedOpenCustomHashSet.this.mask;
/* 724:724 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 725:725 */          pos = pos + 1 & IntLinkedOpenCustomHashSet.this.mask;
/* 726:    */        }
/* 727:727 */        if (IntLinkedOpenCustomHashSet.this.used[pos] == 0) break;
/* 728:728 */        IntLinkedOpenCustomHashSet.this.key[last] = IntLinkedOpenCustomHashSet.this.key[pos];
/* 729:729 */        if (this.next == pos) this.next = last;
/* 730:730 */        if (this.prev == pos) this.prev = last;
/* 731:731 */        IntLinkedOpenCustomHashSet.this.fixPointers(pos, last);
/* 732:    */      }
/* 733:733 */      IntLinkedOpenCustomHashSet.this.used[last] = false;
/* 734:734 */      this.curr = -1;
/* 735:    */    }
/* 736:    */  }
/* 737:    */  
/* 743:    */  public IntListIterator iterator(int from)
/* 744:    */  {
/* 745:745 */    return new SetIterator(from);
/* 746:    */  }
/* 747:    */  
/* 748:748 */  public IntListIterator iterator() { return new SetIterator(); }
/* 749:    */  
/* 759:    */  @Deprecated
/* 760:    */  public boolean rehash()
/* 761:    */  {
/* 762:762 */    return true;
/* 763:    */  }
/* 764:    */  
/* 775:    */  public boolean trim()
/* 776:    */  {
/* 777:777 */    int l = HashCommon.arraySize(this.size, this.f);
/* 778:778 */    if (l >= this.n) return true;
/* 779:    */    try {
/* 780:780 */      rehash(l);
/* 781:    */    } catch (OutOfMemoryError cantDoIt) {
/* 782:782 */      return false; }
/* 783:783 */    return true;
/* 784:    */  }
/* 785:    */  
/* 802:    */  public boolean trim(int n)
/* 803:    */  {
/* 804:804 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 805:805 */    if (this.n <= l) return true;
/* 806:    */    try {
/* 807:807 */      rehash(l);
/* 808:    */    } catch (OutOfMemoryError cantDoIt) {
/* 809:809 */      return false; }
/* 810:810 */    return true;
/* 811:    */  }
/* 812:    */  
/* 821:    */  protected void rehash(int newN)
/* 822:    */  {
/* 823:823 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 824:    */    
/* 825:825 */    int[] key = this.key;
/* 826:826 */    int newMask = newN - 1;
/* 827:827 */    int[] newKey = new int[newN];
/* 828:828 */    boolean[] newUsed = new boolean[newN];
/* 829:829 */    long[] link = this.link;
/* 830:830 */    long[] newLink = new long[newN];
/* 831:831 */    this.first = -1;
/* 832:832 */    for (int j = this.size; j-- != 0;) {
/* 833:833 */      int k = key[i];
/* 834:834 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 835:835 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 836:836 */      newUsed[pos] = true;
/* 837:837 */      newKey[pos] = k;
/* 838:838 */      if (prev != -1) {
/* 839:839 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 840:840 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 841:841 */        newPrev = pos;
/* 842:    */      }
/* 843:    */      else {
/* 844:844 */        newPrev = this.first = pos;
/* 845:    */        
/* 846:846 */        newLink[pos] = -1L;
/* 847:    */      }
/* 848:848 */      int t = i;
/* 849:849 */      i = (int)link[i];
/* 850:850 */      prev = t;
/* 851:    */    }
/* 852:852 */    this.n = newN;
/* 853:853 */    this.mask = newMask;
/* 854:854 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 855:855 */    this.key = newKey;
/* 856:856 */    this.used = newUsed;
/* 857:857 */    this.link = newLink;
/* 858:858 */    this.last = newPrev;
/* 859:859 */    if (newPrev != -1)
/* 860:    */    {
/* 861:861 */      newLink[newPrev] |= 4294967295L;
/* 862:    */    }
/* 863:    */  }
/* 864:    */  
/* 867:    */  public IntLinkedOpenCustomHashSet clone()
/* 868:    */  {
/* 869:    */    IntLinkedOpenCustomHashSet c;
/* 870:    */    
/* 872:    */    try
/* 873:    */    {
/* 874:874 */      c = (IntLinkedOpenCustomHashSet)super.clone();
/* 875:    */    }
/* 876:    */    catch (CloneNotSupportedException cantHappen) {
/* 877:877 */      throw new InternalError();
/* 878:    */    }
/* 879:879 */    c.key = ((int[])this.key.clone());
/* 880:880 */    c.used = ((boolean[])this.used.clone());
/* 881:881 */    c.link = ((long[])this.link.clone());
/* 882:882 */    c.strategy = this.strategy;
/* 883:883 */    return c;
/* 884:    */  }
/* 885:    */  
/* 893:    */  public int hashCode()
/* 894:    */  {
/* 895:895 */    int h = 0;int i = 0;int j = this.size;
/* 896:896 */    while (j-- != 0) {
/* 897:897 */      while (this.used[i] == 0) i++;
/* 898:898 */      h += this.strategy.hashCode(this.key[i]);
/* 899:899 */      i++;
/* 900:    */    }
/* 901:901 */    return h;
/* 902:    */  }
/* 903:    */  
/* 904:904 */  private void writeObject(ObjectOutputStream s) throws IOException { IntIterator i = iterator();
/* 905:905 */    s.defaultWriteObject();
/* 906:906 */    for (int j = this.size; j-- != 0; s.writeInt(i.nextInt())) {}
/* 907:    */  }
/* 908:    */  
/* 909:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 910:910 */    s.defaultReadObject();
/* 911:911 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 912:912 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 913:913 */    this.mask = (this.n - 1);
/* 914:914 */    int[] key = this.key = new int[this.n];
/* 915:915 */    boolean[] used = this.used = new boolean[this.n];
/* 916:916 */    long[] link = this.link = new long[this.n];
/* 917:917 */    int prev = -1;
/* 918:918 */    this.first = (this.last = -1);
/* 919:    */    
/* 920:920 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 921:921 */      int k = s.readInt();
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
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntLinkedOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */