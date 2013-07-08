/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Collection;
/*  11:    */import java.util.Comparator;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  88:    */public class ReferenceLinkedOpenHashSet<K>
/*  89:    */  extends AbstractReferenceSortedSet<K>
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
/* 109:    */  protected transient long[] link;
/* 110:    */  
/* 116:    */  public ReferenceLinkedOpenHashSet(int expected, float f)
/* 117:    */  {
/* 118:118 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 119:119 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 120:120 */    this.f = f;
/* 121:121 */    this.n = HashCommon.arraySize(expected, f);
/* 122:122 */    this.mask = (this.n - 1);
/* 123:123 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 124:124 */    this.key = ((Object[])new Object[this.n]);
/* 125:125 */    this.used = new boolean[this.n];
/* 126:126 */    this.link = new long[this.n];
/* 127:    */  }
/* 128:    */  
/* 131:    */  public ReferenceLinkedOpenHashSet(int expected)
/* 132:    */  {
/* 133:133 */    this(expected, 0.75F);
/* 134:    */  }
/* 135:    */  
/* 137:    */  public ReferenceLinkedOpenHashSet()
/* 138:    */  {
/* 139:139 */    this(16, 0.75F);
/* 140:    */  }
/* 141:    */  
/* 145:    */  public ReferenceLinkedOpenHashSet(Collection<? extends K> c, float f)
/* 146:    */  {
/* 147:147 */    this(c.size(), f);
/* 148:148 */    addAll(c);
/* 149:    */  }
/* 150:    */  
/* 154:    */  public ReferenceLinkedOpenHashSet(Collection<? extends K> c)
/* 155:    */  {
/* 156:156 */    this(c, 0.75F);
/* 157:    */  }
/* 158:    */  
/* 162:    */  public ReferenceLinkedOpenHashSet(ReferenceCollection<? extends K> c, float f)
/* 163:    */  {
/* 164:164 */    this(c.size(), f);
/* 165:165 */    addAll(c);
/* 166:    */  }
/* 167:    */  
/* 171:    */  public ReferenceLinkedOpenHashSet(ReferenceCollection<? extends K> c)
/* 172:    */  {
/* 173:173 */    this(c, 0.75F);
/* 174:    */  }
/* 175:    */  
/* 179:    */  public ReferenceLinkedOpenHashSet(ObjectIterator<K> i, float f)
/* 180:    */  {
/* 181:181 */    this(16, f);
/* 182:182 */    while (i.hasNext()) { add(i.next());
/* 183:    */    }
/* 184:    */  }
/* 185:    */  
/* 187:    */  public ReferenceLinkedOpenHashSet(ObjectIterator<K> i)
/* 188:    */  {
/* 189:189 */    this(i, 0.75F);
/* 190:    */  }
/* 191:    */  
/* 197:    */  public ReferenceLinkedOpenHashSet(K[] a, int offset, int length, float f)
/* 198:    */  {
/* 199:199 */    this(length < 0 ? 0 : length, f);
/* 200:200 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 201:201 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 202:    */    }
/* 203:    */  }
/* 204:    */  
/* 208:    */  public ReferenceLinkedOpenHashSet(K[] a, int offset, int length)
/* 209:    */  {
/* 210:210 */    this(a, offset, length, 0.75F);
/* 211:    */  }
/* 212:    */  
/* 216:    */  public ReferenceLinkedOpenHashSet(K[] a, float f)
/* 217:    */  {
/* 218:218 */    this(a, 0, a.length, f);
/* 219:    */  }
/* 220:    */  
/* 224:    */  public ReferenceLinkedOpenHashSet(K[] a)
/* 225:    */  {
/* 226:226 */    this(a, 0.75F);
/* 227:    */  }
/* 228:    */  
/* 232:    */  public boolean add(K k)
/* 233:    */  {
/* 234:234 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 235:    */    
/* 236:236 */    while (this.used[pos] != 0) {
/* 237:237 */      if (this.key[pos] == k) return false;
/* 238:238 */      pos = pos + 1 & this.mask;
/* 239:    */    }
/* 240:240 */    this.used[pos] = true;
/* 241:241 */    this.key[pos] = k;
/* 242:242 */    if (this.size == 0) {
/* 243:243 */      this.first = (this.last = pos);
/* 244:    */      
/* 245:245 */      this.link[pos] = -1L;
/* 246:    */    }
/* 247:    */    else {
/* 248:248 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 249:249 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 250:250 */      this.last = pos;
/* 251:    */    }
/* 252:252 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 253:    */    }
/* 254:254 */    return true;
/* 255:    */  }
/* 256:    */  
/* 259:    */  protected final int shiftKeys(int pos)
/* 260:    */  {
/* 261:    */    int last;
/* 262:    */    
/* 264:    */    for (;;)
/* 265:    */    {
/* 266:266 */      pos = (last = pos) + 1 & this.mask;
/* 267:267 */      while (this.used[pos] != 0) {
/* 268:268 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 269:269 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 270:270 */        pos = pos + 1 & this.mask;
/* 271:    */      }
/* 272:272 */      if (this.used[pos] == 0) break;
/* 273:273 */      this.key[last] = this.key[pos];
/* 274:274 */      fixPointers(pos, last);
/* 275:    */    }
/* 276:276 */    this.used[last] = false;
/* 277:277 */    this.key[last] = null;
/* 278:278 */    return last;
/* 279:    */  }
/* 280:    */  
/* 281:    */  public boolean remove(Object k)
/* 282:    */  {
/* 283:283 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 284:    */    
/* 285:285 */    while (this.used[pos] != 0) {
/* 286:286 */      if (this.key[pos] == k) {
/* 287:287 */        this.size -= 1;
/* 288:288 */        fixPointers(pos);
/* 289:289 */        shiftKeys(pos);
/* 290:    */        
/* 291:291 */        return true;
/* 292:    */      }
/* 293:293 */      pos = pos + 1 & this.mask;
/* 294:    */    }
/* 295:295 */    return false;
/* 296:    */  }
/* 297:    */  
/* 298:    */  public boolean contains(Object k)
/* 299:    */  {
/* 300:300 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 301:    */    
/* 302:302 */    while (this.used[pos] != 0) {
/* 303:303 */      if (this.key[pos] == k) return true;
/* 304:304 */      pos = pos + 1 & this.mask;
/* 305:    */    }
/* 306:306 */    return false;
/* 307:    */  }
/* 308:    */  
/* 311:    */  public K removeFirst()
/* 312:    */  {
/* 313:313 */    if (this.size == 0) throw new NoSuchElementException();
/* 314:314 */    this.size -= 1;
/* 315:315 */    int pos = this.first;
/* 316:    */    
/* 317:317 */    this.first = ((int)this.link[pos]);
/* 318:318 */    if (0 <= this.first)
/* 319:    */    {
/* 320:320 */      this.link[this.first] |= -4294967296L;
/* 321:    */    }
/* 322:322 */    K k = this.key[pos];
/* 323:323 */    shiftKeys(pos);
/* 324:324 */    return k;
/* 325:    */  }
/* 326:    */  
/* 329:    */  public K removeLast()
/* 330:    */  {
/* 331:331 */    if (this.size == 0) throw new NoSuchElementException();
/* 332:332 */    this.size -= 1;
/* 333:333 */    int pos = this.last;
/* 334:    */    
/* 335:335 */    this.last = ((int)(this.link[pos] >>> 32));
/* 336:336 */    if (0 <= this.last)
/* 337:    */    {
/* 338:338 */      this.link[this.last] |= 4294967295L;
/* 339:    */    }
/* 340:340 */    K k = this.key[pos];
/* 341:341 */    shiftKeys(pos);
/* 342:342 */    return k;
/* 343:    */  }
/* 344:    */  
/* 345:345 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/* 346:346 */    if (this.last == i) {
/* 347:347 */      this.last = ((int)(this.link[i] >>> 32));
/* 348:    */      
/* 349:349 */      this.link[this.last] |= 4294967295L;
/* 350:    */    }
/* 351:    */    else {
/* 352:352 */      long linki = this.link[i];
/* 353:353 */      int prev = (int)(linki >>> 32);
/* 354:354 */      int next = (int)linki;
/* 355:355 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 356:356 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 357:    */    }
/* 358:358 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 359:359 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 360:360 */    this.first = i;
/* 361:    */  }
/* 362:    */  
/* 363:363 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/* 364:364 */    if (this.first == i) {
/* 365:365 */      this.first = ((int)this.link[i]);
/* 366:    */      
/* 367:367 */      this.link[this.first] |= -4294967296L;
/* 368:    */    }
/* 369:    */    else {
/* 370:370 */      long linki = this.link[i];
/* 371:371 */      int prev = (int)(linki >>> 32);
/* 372:372 */      int next = (int)linki;
/* 373:373 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 374:374 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 375:    */    }
/* 376:376 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 377:377 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 378:378 */    this.last = i;
/* 379:    */  }
/* 380:    */  
/* 384:    */  public boolean addAndMoveToFirst(K k)
/* 385:    */  {
/* 386:386 */    K[] key = this.key;
/* 387:387 */    boolean[] used = this.used;
/* 388:388 */    int mask = this.mask;
/* 389:    */    
/* 390:390 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/* 391:    */    
/* 392:392 */    while (used[pos] != 0) {
/* 393:393 */      if (k == key[pos]) {
/* 394:394 */        moveIndexToFirst(pos);
/* 395:395 */        return false;
/* 396:    */      }
/* 397:397 */      pos = pos + 1 & mask;
/* 398:    */    }
/* 399:399 */    used[pos] = true;
/* 400:400 */    key[pos] = k;
/* 401:401 */    if (this.size == 0) {
/* 402:402 */      this.first = (this.last = pos);
/* 403:    */      
/* 404:404 */      this.link[pos] = -1L;
/* 405:    */    }
/* 406:    */    else {
/* 407:407 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 408:408 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 409:409 */      this.first = pos;
/* 410:    */    }
/* 411:411 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 412:    */    }
/* 413:413 */    return true;
/* 414:    */  }
/* 415:    */  
/* 419:    */  public boolean addAndMoveToLast(K k)
/* 420:    */  {
/* 421:421 */    K[] key = this.key;
/* 422:422 */    boolean[] used = this.used;
/* 423:423 */    int mask = this.mask;
/* 424:    */    
/* 425:425 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/* 426:    */    
/* 427:427 */    while (used[pos] != 0) {
/* 428:428 */      if (k == key[pos]) {
/* 429:429 */        moveIndexToLast(pos);
/* 430:430 */        return false;
/* 431:    */      }
/* 432:432 */      pos = pos + 1 & mask;
/* 433:    */    }
/* 434:434 */    used[pos] = true;
/* 435:435 */    key[pos] = k;
/* 436:436 */    if (this.size == 0) {
/* 437:437 */      this.first = (this.last = pos);
/* 438:    */      
/* 439:439 */      this.link[pos] = -1L;
/* 440:    */    }
/* 441:    */    else {
/* 442:442 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 443:443 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 444:444 */      this.last = pos;
/* 445:    */    }
/* 446:446 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 447:    */    }
/* 448:448 */    return true;
/* 449:    */  }
/* 450:    */  
/* 455:    */  public void clear()
/* 456:    */  {
/* 457:457 */    if (this.size == 0) return;
/* 458:458 */    this.size = 0;
/* 459:459 */    BooleanArrays.fill(this.used, false);
/* 460:460 */    ObjectArrays.fill(this.key, null);
/* 461:461 */    this.first = (this.last = -1);
/* 462:    */  }
/* 463:    */  
/* 464:464 */  public int size() { return this.size; }
/* 465:    */  
/* 466:    */  public boolean isEmpty() {
/* 467:467 */    return this.size == 0;
/* 468:    */  }
/* 469:    */  
/* 474:    */  @Deprecated
/* 475:    */  public void growthFactor(int growthFactor) {}
/* 476:    */  
/* 481:    */  @Deprecated
/* 482:    */  public int growthFactor()
/* 483:    */  {
/* 484:484 */    return 16;
/* 485:    */  }
/* 486:    */  
/* 492:    */  protected void fixPointers(int i)
/* 493:    */  {
/* 494:494 */    if (this.size == 0) {
/* 495:495 */      this.first = (this.last = -1);
/* 496:496 */      return;
/* 497:    */    }
/* 498:498 */    if (this.first == i) {
/* 499:499 */      this.first = ((int)this.link[i]);
/* 500:500 */      if (0 <= this.first)
/* 501:    */      {
/* 502:502 */        this.link[this.first] |= -4294967296L;
/* 503:    */      }
/* 504:504 */      return;
/* 505:    */    }
/* 506:506 */    if (this.last == i) {
/* 507:507 */      this.last = ((int)(this.link[i] >>> 32));
/* 508:508 */      if (0 <= this.last)
/* 509:    */      {
/* 510:510 */        this.link[this.last] |= 4294967295L;
/* 511:    */      }
/* 512:512 */      return;
/* 513:    */    }
/* 514:514 */    long linki = this.link[i];
/* 515:515 */    int prev = (int)(linki >>> 32);
/* 516:516 */    int next = (int)linki;
/* 517:517 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 518:518 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 519:    */  }
/* 520:    */  
/* 527:    */  protected void fixPointers(int s, int d)
/* 528:    */  {
/* 529:529 */    if (this.size == 1) {
/* 530:530 */      this.first = (this.last = d);
/* 531:    */      
/* 532:532 */      this.link[d] = -1L;
/* 533:533 */      return;
/* 534:    */    }
/* 535:535 */    if (this.first == s) {
/* 536:536 */      this.first = d;
/* 537:537 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 538:538 */      this.link[d] = this.link[s];
/* 539:539 */      return;
/* 540:    */    }
/* 541:541 */    if (this.last == s) {
/* 542:542 */      this.last = d;
/* 543:543 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 544:544 */      this.link[d] = this.link[s];
/* 545:545 */      return;
/* 546:    */    }
/* 547:547 */    long links = this.link[s];
/* 548:548 */    int prev = (int)(links >>> 32);
/* 549:549 */    int next = (int)links;
/* 550:550 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 551:551 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 552:552 */    this.link[d] = links;
/* 553:    */  }
/* 554:    */  
/* 557:    */  public K first()
/* 558:    */  {
/* 559:559 */    if (this.size == 0) throw new NoSuchElementException();
/* 560:560 */    return this.key[this.first];
/* 561:    */  }
/* 562:    */  
/* 565:    */  public K last()
/* 566:    */  {
/* 567:567 */    if (this.size == 0) throw new NoSuchElementException();
/* 568:568 */    return this.key[this.last]; }
/* 569:    */  
/* 570:570 */  public ReferenceSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/* 571:571 */  public ReferenceSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/* 572:572 */  public ReferenceSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/* 573:573 */  public Comparator<? super K> comparator() { return null; }
/* 574:    */  
/* 579:    */  private class SetIterator
/* 580:    */    extends AbstractObjectListIterator<K>
/* 581:    */  {
/* 582:582 */    int prev = -1;
/* 583:    */    
/* 584:584 */    int next = -1;
/* 585:    */    
/* 586:586 */    int curr = -1;
/* 587:    */    
/* 588:588 */    int index = -1;
/* 589:    */    
/* 590:590 */    SetIterator() { this.next = ReferenceLinkedOpenHashSet.this.first;
/* 591:591 */      this.index = 0;
/* 592:    */    }
/* 593:    */    
/* 594:594 */    SetIterator() { if (ReferenceLinkedOpenHashSet.this.key[ReferenceLinkedOpenHashSet.this.last] == from) {
/* 595:595 */        this.prev = ReferenceLinkedOpenHashSet.this.last;
/* 596:596 */        this.index = ReferenceLinkedOpenHashSet.this.size;
/* 597:    */      }
/* 598:    */      else
/* 599:    */      {
/* 600:600 */        int pos = (from == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(from))) & ReferenceLinkedOpenHashSet.this.mask;
/* 601:    */        
/* 602:602 */        while (ReferenceLinkedOpenHashSet.this.used[pos] != 0) {
/* 603:603 */          if (ReferenceLinkedOpenHashSet.this.key[pos] == from)
/* 604:    */          {
/* 605:605 */            this.next = ((int)ReferenceLinkedOpenHashSet.this.link[pos]);
/* 606:606 */            this.prev = pos;
/* 607:607 */            return;
/* 608:    */          }
/* 609:609 */          pos = pos + 1 & ReferenceLinkedOpenHashSet.this.mask;
/* 610:    */        }
/* 611:611 */        throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/* 612:    */      } }
/* 613:    */    
/* 614:614 */    public boolean hasNext() { return this.next != -1; }
/* 615:615 */    public boolean hasPrevious() { return this.prev != -1; }
/* 616:    */    
/* 617:617 */    public K next() { if (!hasNext()) throw new NoSuchElementException();
/* 618:618 */      this.curr = this.next;
/* 619:619 */      this.next = ((int)ReferenceLinkedOpenHashSet.this.link[this.curr]);
/* 620:620 */      this.prev = this.curr;
/* 621:621 */      if (this.index >= 0) { this.index += 1;
/* 622:    */      }
/* 623:623 */      return ReferenceLinkedOpenHashSet.this.key[this.curr];
/* 624:    */    }
/* 625:    */    
/* 626:626 */    public K previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 627:627 */      this.curr = this.prev;
/* 628:628 */      this.prev = ((int)(ReferenceLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 629:629 */      this.next = this.curr;
/* 630:630 */      if (this.index >= 0) this.index -= 1;
/* 631:631 */      return ReferenceLinkedOpenHashSet.this.key[this.curr];
/* 632:    */    }
/* 633:    */    
/* 634:634 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/* 635:635 */      if (this.prev == -1) {
/* 636:636 */        this.index = 0;
/* 637:637 */        return;
/* 638:    */      }
/* 639:639 */      if (this.next == -1) {
/* 640:640 */        this.index = ReferenceLinkedOpenHashSet.this.size;
/* 641:641 */        return;
/* 642:    */      }
/* 643:643 */      int pos = ReferenceLinkedOpenHashSet.this.first;
/* 644:644 */      this.index = 1;
/* 645:645 */      while (pos != this.prev) {
/* 646:646 */        pos = (int)ReferenceLinkedOpenHashSet.this.link[pos];
/* 647:647 */        this.index += 1;
/* 648:    */      }
/* 649:    */    }
/* 650:    */    
/* 651:651 */    public int nextIndex() { ensureIndexKnown();
/* 652:652 */      return this.index;
/* 653:    */    }
/* 654:    */    
/* 655:655 */    public int previousIndex() { ensureIndexKnown();
/* 656:656 */      return this.index - 1;
/* 657:    */    }
/* 658:    */    
/* 659:    */    public void remove() {
/* 660:660 */      ensureIndexKnown();
/* 661:661 */      if (this.curr == -1) throw new IllegalStateException();
/* 662:662 */      if (this.curr == this.prev)
/* 663:    */      {
/* 665:665 */        this.index -= 1;
/* 666:666 */        this.prev = ((int)(ReferenceLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 667:    */      }
/* 668:    */      else {
/* 669:669 */        this.next = ((int)ReferenceLinkedOpenHashSet.this.link[this.curr]); }
/* 670:670 */      ReferenceLinkedOpenHashSet.this.size -= 1;
/* 671:    */      
/* 673:673 */      if (this.prev == -1) { ReferenceLinkedOpenHashSet.this.first = this.next;
/* 674:    */      } else
/* 675:675 */        ReferenceLinkedOpenHashSet.this.link[this.prev] ^= (ReferenceLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 676:676 */      if (this.next == -1) { ReferenceLinkedOpenHashSet.this.last = this.prev;
/* 677:    */      } else
/* 678:678 */        ReferenceLinkedOpenHashSet.this.link[this.next] ^= (ReferenceLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/* 679:679 */      int pos = this.curr;
/* 680:    */      int last;
/* 681:    */      for (;;) {
/* 682:682 */        pos = (last = pos) + 1 & ReferenceLinkedOpenHashSet.this.mask;
/* 683:683 */        while (ReferenceLinkedOpenHashSet.this.used[pos] != 0) {
/* 684:684 */          int slot = (ReferenceLinkedOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(ReferenceLinkedOpenHashSet.this.key[pos]))) & ReferenceLinkedOpenHashSet.this.mask;
/* 685:685 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 686:686 */          pos = pos + 1 & ReferenceLinkedOpenHashSet.this.mask;
/* 687:    */        }
/* 688:688 */        if (ReferenceLinkedOpenHashSet.this.used[pos] == 0) break;
/* 689:689 */        ReferenceLinkedOpenHashSet.this.key[last] = ReferenceLinkedOpenHashSet.this.key[pos];
/* 690:690 */        if (this.next == pos) this.next = last;
/* 691:691 */        if (this.prev == pos) this.prev = last;
/* 692:692 */        ReferenceLinkedOpenHashSet.this.fixPointers(pos, last);
/* 693:    */      }
/* 694:694 */      ReferenceLinkedOpenHashSet.this.used[last] = false;
/* 695:695 */      ReferenceLinkedOpenHashSet.this.key[last] = null;
/* 696:696 */      this.curr = -1;
/* 697:    */    }
/* 698:    */  }
/* 699:    */  
/* 705:    */  public ObjectListIterator<K> iterator(K from)
/* 706:    */  {
/* 707:707 */    return new SetIterator(from);
/* 708:    */  }
/* 709:    */  
/* 710:710 */  public ObjectListIterator<K> iterator() { return new SetIterator(); }
/* 711:    */  
/* 721:    */  @Deprecated
/* 722:    */  public boolean rehash()
/* 723:    */  {
/* 724:724 */    return true;
/* 725:    */  }
/* 726:    */  
/* 737:    */  public boolean trim()
/* 738:    */  {
/* 739:739 */    int l = HashCommon.arraySize(this.size, this.f);
/* 740:740 */    if (l >= this.n) return true;
/* 741:    */    try {
/* 742:742 */      rehash(l);
/* 743:    */    } catch (OutOfMemoryError cantDoIt) {
/* 744:744 */      return false; }
/* 745:745 */    return true;
/* 746:    */  }
/* 747:    */  
/* 764:    */  public boolean trim(int n)
/* 765:    */  {
/* 766:766 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 767:767 */    if (this.n <= l) return true;
/* 768:    */    try {
/* 769:769 */      rehash(l);
/* 770:    */    } catch (OutOfMemoryError cantDoIt) {
/* 771:771 */      return false; }
/* 772:772 */    return true;
/* 773:    */  }
/* 774:    */  
/* 783:    */  protected void rehash(int newN)
/* 784:    */  {
/* 785:785 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 786:    */    
/* 787:787 */    K[] key = this.key;
/* 788:788 */    int newMask = newN - 1;
/* 789:789 */    K[] newKey = (Object[])new Object[newN];
/* 790:790 */    boolean[] newUsed = new boolean[newN];
/* 791:791 */    long[] link = this.link;
/* 792:792 */    long[] newLink = new long[newN];
/* 793:793 */    this.first = -1;
/* 794:794 */    for (int j = this.size; j-- != 0;) {
/* 795:795 */      K k = key[i];
/* 796:796 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 797:797 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 798:798 */      newUsed[pos] = true;
/* 799:799 */      newKey[pos] = k;
/* 800:800 */      if (prev != -1) {
/* 801:801 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 802:802 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 803:803 */        newPrev = pos;
/* 804:    */      }
/* 805:    */      else {
/* 806:806 */        newPrev = this.first = pos;
/* 807:    */        
/* 808:808 */        newLink[pos] = -1L;
/* 809:    */      }
/* 810:810 */      int t = i;
/* 811:811 */      i = (int)link[i];
/* 812:812 */      prev = t;
/* 813:    */    }
/* 814:814 */    this.n = newN;
/* 815:815 */    this.mask = newMask;
/* 816:816 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 817:817 */    this.key = newKey;
/* 818:818 */    this.used = newUsed;
/* 819:819 */    this.link = newLink;
/* 820:820 */    this.last = newPrev;
/* 821:821 */    if (newPrev != -1)
/* 822:    */    {
/* 823:823 */      newLink[newPrev] |= 4294967295L;
/* 824:    */    }
/* 825:    */  }
/* 826:    */  
/* 829:    */  public ReferenceLinkedOpenHashSet<K> clone()
/* 830:    */  {
/* 831:    */    ReferenceLinkedOpenHashSet<K> c;
/* 832:    */    
/* 834:    */    try
/* 835:    */    {
/* 836:836 */      c = (ReferenceLinkedOpenHashSet)super.clone();
/* 837:    */    }
/* 838:    */    catch (CloneNotSupportedException cantHappen) {
/* 839:839 */      throw new InternalError();
/* 840:    */    }
/* 841:841 */    c.key = ((Object[])this.key.clone());
/* 842:842 */    c.used = ((boolean[])this.used.clone());
/* 843:843 */    c.link = ((long[])this.link.clone());
/* 844:844 */    return c;
/* 845:    */  }
/* 846:    */  
/* 854:    */  public int hashCode()
/* 855:    */  {
/* 856:856 */    int h = 0;int i = 0;int j = this.size;
/* 857:857 */    while (j-- != 0) {
/* 858:858 */      while (this.used[i] == 0) i++;
/* 859:859 */      if (this != this.key[i])
/* 860:860 */        h += (this.key[i] == null ? 0 : System.identityHashCode(this.key[i]));
/* 861:861 */      i++;
/* 862:    */    }
/* 863:863 */    return h;
/* 864:    */  }
/* 865:    */  
/* 866:866 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 867:867 */    s.defaultWriteObject();
/* 868:868 */    for (int j = this.size; j-- != 0; s.writeObject(i.next())) {}
/* 869:    */  }
/* 870:    */  
/* 871:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 872:872 */    s.defaultReadObject();
/* 873:873 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 874:874 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 875:875 */    this.mask = (this.n - 1);
/* 876:876 */    K[] key = this.key = (Object[])new Object[this.n];
/* 877:877 */    boolean[] used = this.used = new boolean[this.n];
/* 878:878 */    long[] link = this.link = new long[this.n];
/* 879:879 */    int prev = -1;
/* 880:880 */    this.first = (this.last = -1);
/* 881:    */    
/* 882:882 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 883:883 */      K k = s.readObject();
/* 884:884 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 885:885 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 886:886 */      used[pos] = true;
/* 887:887 */      key[pos] = k;
/* 888:888 */      if (this.first != -1) {
/* 889:889 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 890:890 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 891:891 */        prev = pos;
/* 892:    */      }
/* 893:    */      else {
/* 894:894 */        prev = this.first = pos;
/* 895:    */        
/* 896:896 */        link[pos] |= -4294967296L;
/* 897:    */      }
/* 898:    */    }
/* 899:899 */    this.last = prev;
/* 900:900 */    if (prev != -1)
/* 901:    */    {
/* 902:902 */      link[prev] |= 4294967295L;
/* 903:    */    }
/* 904:    */  }
/* 905:    */  
/* 906:    */  private void checkTable() {}
/* 907:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */