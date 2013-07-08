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
/*  88:    */public class ObjectLinkedOpenHashSet<K>
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
/* 109:    */  protected transient long[] link;
/* 110:    */  
/* 116:    */  public ObjectLinkedOpenHashSet(int expected, float f)
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
/* 131:    */  public ObjectLinkedOpenHashSet(int expected)
/* 132:    */  {
/* 133:133 */    this(expected, 0.75F);
/* 134:    */  }
/* 135:    */  
/* 137:    */  public ObjectLinkedOpenHashSet()
/* 138:    */  {
/* 139:139 */    this(16, 0.75F);
/* 140:    */  }
/* 141:    */  
/* 145:    */  public ObjectLinkedOpenHashSet(Collection<? extends K> c, float f)
/* 146:    */  {
/* 147:147 */    this(c.size(), f);
/* 148:148 */    addAll(c);
/* 149:    */  }
/* 150:    */  
/* 154:    */  public ObjectLinkedOpenHashSet(Collection<? extends K> c)
/* 155:    */  {
/* 156:156 */    this(c, 0.75F);
/* 157:    */  }
/* 158:    */  
/* 162:    */  public ObjectLinkedOpenHashSet(ObjectCollection<? extends K> c, float f)
/* 163:    */  {
/* 164:164 */    this(c.size(), f);
/* 165:165 */    addAll(c);
/* 166:    */  }
/* 167:    */  
/* 171:    */  public ObjectLinkedOpenHashSet(ObjectCollection<? extends K> c)
/* 172:    */  {
/* 173:173 */    this(c, 0.75F);
/* 174:    */  }
/* 175:    */  
/* 179:    */  public ObjectLinkedOpenHashSet(ObjectIterator<K> i, float f)
/* 180:    */  {
/* 181:181 */    this(16, f);
/* 182:182 */    while (i.hasNext()) { add(i.next());
/* 183:    */    }
/* 184:    */  }
/* 185:    */  
/* 187:    */  public ObjectLinkedOpenHashSet(ObjectIterator<K> i)
/* 188:    */  {
/* 189:189 */    this(i, 0.75F);
/* 190:    */  }
/* 191:    */  
/* 197:    */  public ObjectLinkedOpenHashSet(K[] a, int offset, int length, float f)
/* 198:    */  {
/* 199:199 */    this(length < 0 ? 0 : length, f);
/* 200:200 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 201:201 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 202:    */    }
/* 203:    */  }
/* 204:    */  
/* 208:    */  public ObjectLinkedOpenHashSet(K[] a, int offset, int length)
/* 209:    */  {
/* 210:210 */    this(a, offset, length, 0.75F);
/* 211:    */  }
/* 212:    */  
/* 216:    */  public ObjectLinkedOpenHashSet(K[] a, float f)
/* 217:    */  {
/* 218:218 */    this(a, 0, a.length, f);
/* 219:    */  }
/* 220:    */  
/* 224:    */  public ObjectLinkedOpenHashSet(K[] a)
/* 225:    */  {
/* 226:226 */    this(a, 0.75F);
/* 227:    */  }
/* 228:    */  
/* 232:    */  public boolean add(K k)
/* 233:    */  {
/* 234:234 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 235:    */    
/* 236:236 */    while (this.used[pos] != 0) {
/* 237:237 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return false;
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
/* 268:268 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
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
/* 283:283 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 284:    */    
/* 285:285 */    while (this.used[pos] != 0) {
/* 286:286 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 300:300 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 301:    */    
/* 302:302 */    while (this.used[pos] != 0) {
/* 303:303 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 304:304 */      pos = pos + 1 & this.mask;
/* 305:    */    }
/* 306:306 */    return false;
/* 307:    */  }
/* 308:    */  
/* 312:    */  public K get(Object k)
/* 313:    */  {
/* 314:314 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 315:    */    
/* 316:316 */    while (this.used[pos] != 0) {
/* 317:317 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.key[pos];
/* 318:318 */      pos = pos + 1 & this.mask;
/* 319:    */    }
/* 320:320 */    return null;
/* 321:    */  }
/* 322:    */  
/* 325:    */  public K removeFirst()
/* 326:    */  {
/* 327:327 */    if (this.size == 0) throw new NoSuchElementException();
/* 328:328 */    this.size -= 1;
/* 329:329 */    int pos = this.first;
/* 330:    */    
/* 331:331 */    this.first = ((int)this.link[pos]);
/* 332:332 */    if (0 <= this.first)
/* 333:    */    {
/* 334:334 */      this.link[this.first] |= -4294967296L;
/* 335:    */    }
/* 336:336 */    K k = this.key[pos];
/* 337:337 */    shiftKeys(pos);
/* 338:338 */    return k;
/* 339:    */  }
/* 340:    */  
/* 343:    */  public K removeLast()
/* 344:    */  {
/* 345:345 */    if (this.size == 0) throw new NoSuchElementException();
/* 346:346 */    this.size -= 1;
/* 347:347 */    int pos = this.last;
/* 348:    */    
/* 349:349 */    this.last = ((int)(this.link[pos] >>> 32));
/* 350:350 */    if (0 <= this.last)
/* 351:    */    {
/* 352:352 */      this.link[this.last] |= 4294967295L;
/* 353:    */    }
/* 354:354 */    K k = this.key[pos];
/* 355:355 */    shiftKeys(pos);
/* 356:356 */    return k;
/* 357:    */  }
/* 358:    */  
/* 359:359 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/* 360:360 */    if (this.last == i) {
/* 361:361 */      this.last = ((int)(this.link[i] >>> 32));
/* 362:    */      
/* 363:363 */      this.link[this.last] |= 4294967295L;
/* 364:    */    }
/* 365:    */    else {
/* 366:366 */      long linki = this.link[i];
/* 367:367 */      int prev = (int)(linki >>> 32);
/* 368:368 */      int next = (int)linki;
/* 369:369 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 370:370 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 371:    */    }
/* 372:372 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 373:373 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 374:374 */    this.first = i;
/* 375:    */  }
/* 376:    */  
/* 377:377 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/* 378:378 */    if (this.first == i) {
/* 379:379 */      this.first = ((int)this.link[i]);
/* 380:    */      
/* 381:381 */      this.link[this.first] |= -4294967296L;
/* 382:    */    }
/* 383:    */    else {
/* 384:384 */      long linki = this.link[i];
/* 385:385 */      int prev = (int)(linki >>> 32);
/* 386:386 */      int next = (int)linki;
/* 387:387 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 388:388 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 389:    */    }
/* 390:390 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 391:391 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 392:392 */    this.last = i;
/* 393:    */  }
/* 394:    */  
/* 398:    */  public boolean addAndMoveToFirst(K k)
/* 399:    */  {
/* 400:400 */    K[] key = this.key;
/* 401:401 */    boolean[] used = this.used;
/* 402:402 */    int mask = this.mask;
/* 403:    */    
/* 404:404 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/* 405:    */    
/* 406:406 */    while (used[pos] != 0) {
/* 407:407 */      if (k == null ? key[pos] == null : k.equals(key[pos])) {
/* 408:408 */        moveIndexToFirst(pos);
/* 409:409 */        return false;
/* 410:    */      }
/* 411:411 */      pos = pos + 1 & mask;
/* 412:    */    }
/* 413:413 */    used[pos] = true;
/* 414:414 */    key[pos] = k;
/* 415:415 */    if (this.size == 0) {
/* 416:416 */      this.first = (this.last = pos);
/* 417:    */      
/* 418:418 */      this.link[pos] = -1L;
/* 419:    */    }
/* 420:    */    else {
/* 421:421 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 422:422 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 423:423 */      this.first = pos;
/* 424:    */    }
/* 425:425 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 426:    */    }
/* 427:427 */    return true;
/* 428:    */  }
/* 429:    */  
/* 433:    */  public boolean addAndMoveToLast(K k)
/* 434:    */  {
/* 435:435 */    K[] key = this.key;
/* 436:436 */    boolean[] used = this.used;
/* 437:437 */    int mask = this.mask;
/* 438:    */    
/* 439:439 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/* 440:    */    
/* 441:441 */    while (used[pos] != 0) {
/* 442:442 */      if (k == null ? key[pos] == null : k.equals(key[pos])) {
/* 443:443 */        moveIndexToLast(pos);
/* 444:444 */        return false;
/* 445:    */      }
/* 446:446 */      pos = pos + 1 & mask;
/* 447:    */    }
/* 448:448 */    used[pos] = true;
/* 449:449 */    key[pos] = k;
/* 450:450 */    if (this.size == 0) {
/* 451:451 */      this.first = (this.last = pos);
/* 452:    */      
/* 453:453 */      this.link[pos] = -1L;
/* 454:    */    }
/* 455:    */    else {
/* 456:456 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 457:457 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 458:458 */      this.last = pos;
/* 459:    */    }
/* 460:460 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 461:    */    }
/* 462:462 */    return true;
/* 463:    */  }
/* 464:    */  
/* 469:    */  public void clear()
/* 470:    */  {
/* 471:471 */    if (this.size == 0) return;
/* 472:472 */    this.size = 0;
/* 473:473 */    BooleanArrays.fill(this.used, false);
/* 474:474 */    ObjectArrays.fill(this.key, null);
/* 475:475 */    this.first = (this.last = -1);
/* 476:    */  }
/* 477:    */  
/* 478:478 */  public int size() { return this.size; }
/* 479:    */  
/* 480:    */  public boolean isEmpty() {
/* 481:481 */    return this.size == 0;
/* 482:    */  }
/* 483:    */  
/* 488:    */  @Deprecated
/* 489:    */  public void growthFactor(int growthFactor) {}
/* 490:    */  
/* 495:    */  @Deprecated
/* 496:    */  public int growthFactor()
/* 497:    */  {
/* 498:498 */    return 16;
/* 499:    */  }
/* 500:    */  
/* 506:    */  protected void fixPointers(int i)
/* 507:    */  {
/* 508:508 */    if (this.size == 0) {
/* 509:509 */      this.first = (this.last = -1);
/* 510:510 */      return;
/* 511:    */    }
/* 512:512 */    if (this.first == i) {
/* 513:513 */      this.first = ((int)this.link[i]);
/* 514:514 */      if (0 <= this.first)
/* 515:    */      {
/* 516:516 */        this.link[this.first] |= -4294967296L;
/* 517:    */      }
/* 518:518 */      return;
/* 519:    */    }
/* 520:520 */    if (this.last == i) {
/* 521:521 */      this.last = ((int)(this.link[i] >>> 32));
/* 522:522 */      if (0 <= this.last)
/* 523:    */      {
/* 524:524 */        this.link[this.last] |= 4294967295L;
/* 525:    */      }
/* 526:526 */      return;
/* 527:    */    }
/* 528:528 */    long linki = this.link[i];
/* 529:529 */    int prev = (int)(linki >>> 32);
/* 530:530 */    int next = (int)linki;
/* 531:531 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 532:532 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 533:    */  }
/* 534:    */  
/* 541:    */  protected void fixPointers(int s, int d)
/* 542:    */  {
/* 543:543 */    if (this.size == 1) {
/* 544:544 */      this.first = (this.last = d);
/* 545:    */      
/* 546:546 */      this.link[d] = -1L;
/* 547:547 */      return;
/* 548:    */    }
/* 549:549 */    if (this.first == s) {
/* 550:550 */      this.first = d;
/* 551:551 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 552:552 */      this.link[d] = this.link[s];
/* 553:553 */      return;
/* 554:    */    }
/* 555:555 */    if (this.last == s) {
/* 556:556 */      this.last = d;
/* 557:557 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 558:558 */      this.link[d] = this.link[s];
/* 559:559 */      return;
/* 560:    */    }
/* 561:561 */    long links = this.link[s];
/* 562:562 */    int prev = (int)(links >>> 32);
/* 563:563 */    int next = (int)links;
/* 564:564 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 565:565 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 566:566 */    this.link[d] = links;
/* 567:    */  }
/* 568:    */  
/* 571:    */  public K first()
/* 572:    */  {
/* 573:573 */    if (this.size == 0) throw new NoSuchElementException();
/* 574:574 */    return this.key[this.first];
/* 575:    */  }
/* 576:    */  
/* 579:    */  public K last()
/* 580:    */  {
/* 581:581 */    if (this.size == 0) throw new NoSuchElementException();
/* 582:582 */    return this.key[this.last]; }
/* 583:    */  
/* 584:584 */  public ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/* 585:585 */  public ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/* 586:586 */  public ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/* 587:587 */  public Comparator<? super K> comparator() { return null; }
/* 588:    */  
/* 593:    */  private class SetIterator
/* 594:    */    extends AbstractObjectListIterator<K>
/* 595:    */  {
/* 596:596 */    int prev = -1;
/* 597:    */    
/* 598:598 */    int next = -1;
/* 599:    */    
/* 600:600 */    int curr = -1;
/* 601:    */    
/* 602:602 */    int index = -1;
/* 603:    */    
/* 604:604 */    SetIterator() { this.next = ObjectLinkedOpenHashSet.this.first;
/* 605:605 */      this.index = 0;
/* 606:    */    }
/* 607:    */    
/* 608:608 */    SetIterator() { if (ObjectLinkedOpenHashSet.this.key[ObjectLinkedOpenHashSet.this.last] == null ? from == null : ObjectLinkedOpenHashSet.this.key[ObjectLinkedOpenHashSet.this.last].equals(from)) {
/* 609:609 */        this.prev = ObjectLinkedOpenHashSet.this.last;
/* 610:610 */        this.index = ObjectLinkedOpenHashSet.this.size;
/* 611:    */      }
/* 612:    */      else
/* 613:    */      {
/* 614:614 */        int pos = (from == null ? 142593372 : HashCommon.murmurHash3(from.hashCode())) & ObjectLinkedOpenHashSet.this.mask;
/* 615:    */        
/* 616:616 */        while (ObjectLinkedOpenHashSet.this.used[pos] != 0) {
/* 617:617 */          if (ObjectLinkedOpenHashSet.this.key[pos] == null ? from == null : ObjectLinkedOpenHashSet.this.key[pos].equals(from))
/* 618:    */          {
/* 619:619 */            this.next = ((int)ObjectLinkedOpenHashSet.this.link[pos]);
/* 620:620 */            this.prev = pos;
/* 621:621 */            return;
/* 622:    */          }
/* 623:623 */          pos = pos + 1 & ObjectLinkedOpenHashSet.this.mask;
/* 624:    */        }
/* 625:625 */        throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/* 626:    */      } }
/* 627:    */    
/* 628:628 */    public boolean hasNext() { return this.next != -1; }
/* 629:629 */    public boolean hasPrevious() { return this.prev != -1; }
/* 630:    */    
/* 631:631 */    public K next() { if (!hasNext()) throw new NoSuchElementException();
/* 632:632 */      this.curr = this.next;
/* 633:633 */      this.next = ((int)ObjectLinkedOpenHashSet.this.link[this.curr]);
/* 634:634 */      this.prev = this.curr;
/* 635:635 */      if (this.index >= 0) { this.index += 1;
/* 636:    */      }
/* 637:637 */      return ObjectLinkedOpenHashSet.this.key[this.curr];
/* 638:    */    }
/* 639:    */    
/* 640:640 */    public K previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 641:641 */      this.curr = this.prev;
/* 642:642 */      this.prev = ((int)(ObjectLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 643:643 */      this.next = this.curr;
/* 644:644 */      if (this.index >= 0) this.index -= 1;
/* 645:645 */      return ObjectLinkedOpenHashSet.this.key[this.curr];
/* 646:    */    }
/* 647:    */    
/* 648:648 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/* 649:649 */      if (this.prev == -1) {
/* 650:650 */        this.index = 0;
/* 651:651 */        return;
/* 652:    */      }
/* 653:653 */      if (this.next == -1) {
/* 654:654 */        this.index = ObjectLinkedOpenHashSet.this.size;
/* 655:655 */        return;
/* 656:    */      }
/* 657:657 */      int pos = ObjectLinkedOpenHashSet.this.first;
/* 658:658 */      this.index = 1;
/* 659:659 */      while (pos != this.prev) {
/* 660:660 */        pos = (int)ObjectLinkedOpenHashSet.this.link[pos];
/* 661:661 */        this.index += 1;
/* 662:    */      }
/* 663:    */    }
/* 664:    */    
/* 665:665 */    public int nextIndex() { ensureIndexKnown();
/* 666:666 */      return this.index;
/* 667:    */    }
/* 668:    */    
/* 669:669 */    public int previousIndex() { ensureIndexKnown();
/* 670:670 */      return this.index - 1;
/* 671:    */    }
/* 672:    */    
/* 673:    */    public void remove() {
/* 674:674 */      ensureIndexKnown();
/* 675:675 */      if (this.curr == -1) throw new IllegalStateException();
/* 676:676 */      if (this.curr == this.prev)
/* 677:    */      {
/* 679:679 */        this.index -= 1;
/* 680:680 */        this.prev = ((int)(ObjectLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 681:    */      }
/* 682:    */      else {
/* 683:683 */        this.next = ((int)ObjectLinkedOpenHashSet.this.link[this.curr]); }
/* 684:684 */      ObjectLinkedOpenHashSet.this.size -= 1;
/* 685:    */      
/* 687:687 */      if (this.prev == -1) { ObjectLinkedOpenHashSet.this.first = this.next;
/* 688:    */      } else
/* 689:689 */        ObjectLinkedOpenHashSet.this.link[this.prev] ^= (ObjectLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 690:690 */      if (this.next == -1) { ObjectLinkedOpenHashSet.this.last = this.prev;
/* 691:    */      } else
/* 692:692 */        ObjectLinkedOpenHashSet.this.link[this.next] ^= (ObjectLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/* 693:693 */      int pos = this.curr;
/* 694:    */      int last;
/* 695:    */      for (;;) {
/* 696:696 */        pos = (last = pos) + 1 & ObjectLinkedOpenHashSet.this.mask;
/* 697:697 */        while (ObjectLinkedOpenHashSet.this.used[pos] != 0) {
/* 698:698 */          int slot = (ObjectLinkedOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(ObjectLinkedOpenHashSet.this.key[pos].hashCode())) & ObjectLinkedOpenHashSet.this.mask;
/* 699:699 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 700:700 */          pos = pos + 1 & ObjectLinkedOpenHashSet.this.mask;
/* 701:    */        }
/* 702:702 */        if (ObjectLinkedOpenHashSet.this.used[pos] == 0) break;
/* 703:703 */        ObjectLinkedOpenHashSet.this.key[last] = ObjectLinkedOpenHashSet.this.key[pos];
/* 704:704 */        if (this.next == pos) this.next = last;
/* 705:705 */        if (this.prev == pos) this.prev = last;
/* 706:706 */        ObjectLinkedOpenHashSet.this.fixPointers(pos, last);
/* 707:    */      }
/* 708:708 */      ObjectLinkedOpenHashSet.this.used[last] = false;
/* 709:709 */      ObjectLinkedOpenHashSet.this.key[last] = null;
/* 710:710 */      this.curr = -1;
/* 711:    */    }
/* 712:    */  }
/* 713:    */  
/* 719:    */  public ObjectListIterator<K> iterator(K from)
/* 720:    */  {
/* 721:721 */    return new SetIterator(from);
/* 722:    */  }
/* 723:    */  
/* 724:724 */  public ObjectListIterator<K> iterator() { return new SetIterator(); }
/* 725:    */  
/* 735:    */  @Deprecated
/* 736:    */  public boolean rehash()
/* 737:    */  {
/* 738:738 */    return true;
/* 739:    */  }
/* 740:    */  
/* 751:    */  public boolean trim()
/* 752:    */  {
/* 753:753 */    int l = HashCommon.arraySize(this.size, this.f);
/* 754:754 */    if (l >= this.n) return true;
/* 755:    */    try {
/* 756:756 */      rehash(l);
/* 757:    */    } catch (OutOfMemoryError cantDoIt) {
/* 758:758 */      return false; }
/* 759:759 */    return true;
/* 760:    */  }
/* 761:    */  
/* 778:    */  public boolean trim(int n)
/* 779:    */  {
/* 780:780 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 781:781 */    if (this.n <= l) return true;
/* 782:    */    try {
/* 783:783 */      rehash(l);
/* 784:    */    } catch (OutOfMemoryError cantDoIt) {
/* 785:785 */      return false; }
/* 786:786 */    return true;
/* 787:    */  }
/* 788:    */  
/* 797:    */  protected void rehash(int newN)
/* 798:    */  {
/* 799:799 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 800:    */    
/* 801:801 */    K[] key = this.key;
/* 802:802 */    int newMask = newN - 1;
/* 803:803 */    K[] newKey = (Object[])new Object[newN];
/* 804:804 */    boolean[] newUsed = new boolean[newN];
/* 805:805 */    long[] link = this.link;
/* 806:806 */    long[] newLink = new long[newN];
/* 807:807 */    this.first = -1;
/* 808:808 */    for (int j = this.size; j-- != 0;) {
/* 809:809 */      K k = key[i];
/* 810:810 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
/* 811:811 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 812:812 */      newUsed[pos] = true;
/* 813:813 */      newKey[pos] = k;
/* 814:814 */      if (prev != -1) {
/* 815:815 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 816:816 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 817:817 */        newPrev = pos;
/* 818:    */      }
/* 819:    */      else {
/* 820:820 */        newPrev = this.first = pos;
/* 821:    */        
/* 822:822 */        newLink[pos] = -1L;
/* 823:    */      }
/* 824:824 */      int t = i;
/* 825:825 */      i = (int)link[i];
/* 826:826 */      prev = t;
/* 827:    */    }
/* 828:828 */    this.n = newN;
/* 829:829 */    this.mask = newMask;
/* 830:830 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 831:831 */    this.key = newKey;
/* 832:832 */    this.used = newUsed;
/* 833:833 */    this.link = newLink;
/* 834:834 */    this.last = newPrev;
/* 835:835 */    if (newPrev != -1)
/* 836:    */    {
/* 837:837 */      newLink[newPrev] |= 4294967295L;
/* 838:    */    }
/* 839:    */  }
/* 840:    */  
/* 843:    */  public ObjectLinkedOpenHashSet<K> clone()
/* 844:    */  {
/* 845:    */    ObjectLinkedOpenHashSet<K> c;
/* 846:    */    
/* 848:    */    try
/* 849:    */    {
/* 850:850 */      c = (ObjectLinkedOpenHashSet)super.clone();
/* 851:    */    }
/* 852:    */    catch (CloneNotSupportedException cantHappen) {
/* 853:853 */      throw new InternalError();
/* 854:    */    }
/* 855:855 */    c.key = ((Object[])this.key.clone());
/* 856:856 */    c.used = ((boolean[])this.used.clone());
/* 857:857 */    c.link = ((long[])this.link.clone());
/* 858:858 */    return c;
/* 859:    */  }
/* 860:    */  
/* 868:    */  public int hashCode()
/* 869:    */  {
/* 870:870 */    int h = 0;int i = 0;int j = this.size;
/* 871:871 */    while (j-- != 0) {
/* 872:872 */      while (this.used[i] == 0) i++;
/* 873:873 */      if (this != this.key[i])
/* 874:874 */        h += (this.key[i] == null ? 0 : this.key[i].hashCode());
/* 875:875 */      i++;
/* 876:    */    }
/* 877:877 */    return h;
/* 878:    */  }
/* 879:    */  
/* 880:880 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 881:881 */    s.defaultWriteObject();
/* 882:882 */    for (int j = this.size; j-- != 0; s.writeObject(i.next())) {}
/* 883:    */  }
/* 884:    */  
/* 885:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 886:886 */    s.defaultReadObject();
/* 887:887 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 888:888 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 889:889 */    this.mask = (this.n - 1);
/* 890:890 */    K[] key = this.key = (Object[])new Object[this.n];
/* 891:891 */    boolean[] used = this.used = new boolean[this.n];
/* 892:892 */    long[] link = this.link = new long[this.n];
/* 893:893 */    int prev = -1;
/* 894:894 */    this.first = (this.last = -1);
/* 895:    */    
/* 896:896 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 897:897 */      K k = s.readObject();
/* 898:898 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 899:899 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 900:900 */      used[pos] = true;
/* 901:901 */      key[pos] = k;
/* 902:902 */      if (this.first != -1) {
/* 903:903 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 904:904 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 905:905 */        prev = pos;
/* 906:    */      }
/* 907:    */      else {
/* 908:908 */        prev = this.first = pos;
/* 909:    */        
/* 910:910 */        link[pos] |= -4294967296L;
/* 911:    */      }
/* 912:    */    }
/* 913:913 */    this.last = prev;
/* 914:914 */    if (prev != -1)
/* 915:    */    {
/* 916:916 */      link[prev] |= 4294967295L;
/* 917:    */    }
/* 918:    */  }
/* 919:    */  
/* 920:    */  private void checkTable() {}
/* 921:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */