/*   1:    */package it.unimi.dsi.fastutil.floats;
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
/*  89:    */public class FloatLinkedOpenHashSet
/*  90:    */  extends AbstractFloatSortedSet
/*  91:    */  implements Serializable, Cloneable, Hash
/*  92:    */{
/*  93:    */  public static final long serialVersionUID = 0L;
/*  94:    */  private static final boolean ASSERTS = false;
/*  95:    */  protected transient float[] key;
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
/* 110:    */  protected transient long[] link;
/* 111:    */  
/* 117:    */  public FloatLinkedOpenHashSet(int expected, float f)
/* 118:    */  {
/* 119:119 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 120:120 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 121:121 */    this.f = f;
/* 122:122 */    this.n = HashCommon.arraySize(expected, f);
/* 123:123 */    this.mask = (this.n - 1);
/* 124:124 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 125:125 */    this.key = new float[this.n];
/* 126:126 */    this.used = new boolean[this.n];
/* 127:127 */    this.link = new long[this.n];
/* 128:    */  }
/* 129:    */  
/* 132:    */  public FloatLinkedOpenHashSet(int expected)
/* 133:    */  {
/* 134:134 */    this(expected, 0.75F);
/* 135:    */  }
/* 136:    */  
/* 138:    */  public FloatLinkedOpenHashSet()
/* 139:    */  {
/* 140:140 */    this(16, 0.75F);
/* 141:    */  }
/* 142:    */  
/* 146:    */  public FloatLinkedOpenHashSet(Collection<? extends Float> c, float f)
/* 147:    */  {
/* 148:148 */    this(c.size(), f);
/* 149:149 */    addAll(c);
/* 150:    */  }
/* 151:    */  
/* 155:    */  public FloatLinkedOpenHashSet(Collection<? extends Float> c)
/* 156:    */  {
/* 157:157 */    this(c, 0.75F);
/* 158:    */  }
/* 159:    */  
/* 163:    */  public FloatLinkedOpenHashSet(FloatCollection c, float f)
/* 164:    */  {
/* 165:165 */    this(c.size(), f);
/* 166:166 */    addAll(c);
/* 167:    */  }
/* 168:    */  
/* 172:    */  public FloatLinkedOpenHashSet(FloatCollection c)
/* 173:    */  {
/* 174:174 */    this(c, 0.75F);
/* 175:    */  }
/* 176:    */  
/* 180:    */  public FloatLinkedOpenHashSet(FloatIterator i, float f)
/* 181:    */  {
/* 182:182 */    this(16, f);
/* 183:183 */    while (i.hasNext()) { add(i.nextFloat());
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 188:    */  public FloatLinkedOpenHashSet(FloatIterator i)
/* 189:    */  {
/* 190:190 */    this(i, 0.75F);
/* 191:    */  }
/* 192:    */  
/* 196:    */  public FloatLinkedOpenHashSet(Iterator<?> i, float f)
/* 197:    */  {
/* 198:198 */    this(FloatIterators.asFloatIterator(i), f);
/* 199:    */  }
/* 200:    */  
/* 203:    */  public FloatLinkedOpenHashSet(Iterator<?> i)
/* 204:    */  {
/* 205:205 */    this(FloatIterators.asFloatIterator(i));
/* 206:    */  }
/* 207:    */  
/* 213:    */  public FloatLinkedOpenHashSet(float[] a, int offset, int length, float f)
/* 214:    */  {
/* 215:215 */    this(length < 0 ? 0 : length, f);
/* 216:216 */    FloatArrays.ensureOffsetLength(a, offset, length);
/* 217:217 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 218:    */    }
/* 219:    */  }
/* 220:    */  
/* 224:    */  public FloatLinkedOpenHashSet(float[] a, int offset, int length)
/* 225:    */  {
/* 226:226 */    this(a, offset, length, 0.75F);
/* 227:    */  }
/* 228:    */  
/* 232:    */  public FloatLinkedOpenHashSet(float[] a, float f)
/* 233:    */  {
/* 234:234 */    this(a, 0, a.length, f);
/* 235:    */  }
/* 236:    */  
/* 240:    */  public FloatLinkedOpenHashSet(float[] a)
/* 241:    */  {
/* 242:242 */    this(a, 0.75F);
/* 243:    */  }
/* 244:    */  
/* 248:    */  public boolean add(float k)
/* 249:    */  {
/* 250:250 */    int pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & this.mask;
/* 251:    */    
/* 252:252 */    while (this.used[pos] != 0) {
/* 253:253 */      if (this.key[pos] == k) return false;
/* 254:254 */      pos = pos + 1 & this.mask;
/* 255:    */    }
/* 256:256 */    this.used[pos] = true;
/* 257:257 */    this.key[pos] = k;
/* 258:258 */    if (this.size == 0) {
/* 259:259 */      this.first = (this.last = pos);
/* 260:    */      
/* 261:261 */      this.link[pos] = -1L;
/* 262:    */    }
/* 263:    */    else {
/* 264:264 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 265:265 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 266:266 */      this.last = pos;
/* 267:    */    }
/* 268:268 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 269:    */    }
/* 270:270 */    return true;
/* 271:    */  }
/* 272:    */  
/* 275:    */  protected final int shiftKeys(int pos)
/* 276:    */  {
/* 277:    */    int last;
/* 278:    */    
/* 280:    */    for (;;)
/* 281:    */    {
/* 282:282 */      pos = (last = pos) + 1 & this.mask;
/* 283:283 */      while (this.used[pos] != 0) {
/* 284:284 */        int slot = HashCommon.murmurHash3(HashCommon.float2int(this.key[pos])) & this.mask;
/* 285:285 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 286:286 */        pos = pos + 1 & this.mask;
/* 287:    */      }
/* 288:288 */      if (this.used[pos] == 0) break;
/* 289:289 */      this.key[last] = this.key[pos];
/* 290:290 */      fixPointers(pos, last);
/* 291:    */    }
/* 292:292 */    this.used[last] = false;
/* 293:293 */    return last;
/* 294:    */  }
/* 295:    */  
/* 296:    */  public boolean remove(float k)
/* 297:    */  {
/* 298:298 */    int pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & this.mask;
/* 299:    */    
/* 300:300 */    while (this.used[pos] != 0) {
/* 301:301 */      if (this.key[pos] == k) {
/* 302:302 */        this.size -= 1;
/* 303:303 */        fixPointers(pos);
/* 304:304 */        shiftKeys(pos);
/* 305:    */        
/* 306:306 */        return true;
/* 307:    */      }
/* 308:308 */      pos = pos + 1 & this.mask;
/* 309:    */    }
/* 310:310 */    return false;
/* 311:    */  }
/* 312:    */  
/* 313:    */  public boolean contains(float k)
/* 314:    */  {
/* 315:315 */    int pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & this.mask;
/* 316:    */    
/* 317:317 */    while (this.used[pos] != 0) {
/* 318:318 */      if (this.key[pos] == k) return true;
/* 319:319 */      pos = pos + 1 & this.mask;
/* 320:    */    }
/* 321:321 */    return false;
/* 322:    */  }
/* 323:    */  
/* 326:    */  public float removeFirstFloat()
/* 327:    */  {
/* 328:328 */    if (this.size == 0) throw new NoSuchElementException();
/* 329:329 */    this.size -= 1;
/* 330:330 */    int pos = this.first;
/* 331:    */    
/* 332:332 */    this.first = ((int)this.link[pos]);
/* 333:333 */    if (0 <= this.first)
/* 334:    */    {
/* 335:335 */      this.link[this.first] |= -4294967296L;
/* 336:    */    }
/* 337:337 */    float k = this.key[pos];
/* 338:338 */    shiftKeys(pos);
/* 339:339 */    return k;
/* 340:    */  }
/* 341:    */  
/* 344:    */  public float removeLastFloat()
/* 345:    */  {
/* 346:346 */    if (this.size == 0) throw new NoSuchElementException();
/* 347:347 */    this.size -= 1;
/* 348:348 */    int pos = this.last;
/* 349:    */    
/* 350:350 */    this.last = ((int)(this.link[pos] >>> 32));
/* 351:351 */    if (0 <= this.last)
/* 352:    */    {
/* 353:353 */      this.link[this.last] |= 4294967295L;
/* 354:    */    }
/* 355:355 */    float k = this.key[pos];
/* 356:356 */    shiftKeys(pos);
/* 357:357 */    return k;
/* 358:    */  }
/* 359:    */  
/* 360:360 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/* 361:361 */    if (this.last == i) {
/* 362:362 */      this.last = ((int)(this.link[i] >>> 32));
/* 363:    */      
/* 364:364 */      this.link[this.last] |= 4294967295L;
/* 365:    */    }
/* 366:    */    else {
/* 367:367 */      long linki = this.link[i];
/* 368:368 */      int prev = (int)(linki >>> 32);
/* 369:369 */      int next = (int)linki;
/* 370:370 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 371:371 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 372:    */    }
/* 373:373 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 374:374 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 375:375 */    this.first = i;
/* 376:    */  }
/* 377:    */  
/* 378:378 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/* 379:379 */    if (this.first == i) {
/* 380:380 */      this.first = ((int)this.link[i]);
/* 381:    */      
/* 382:382 */      this.link[this.first] |= -4294967296L;
/* 383:    */    }
/* 384:    */    else {
/* 385:385 */      long linki = this.link[i];
/* 386:386 */      int prev = (int)(linki >>> 32);
/* 387:387 */      int next = (int)linki;
/* 388:388 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 389:389 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/* 390:    */    }
/* 391:391 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 392:392 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 393:393 */    this.last = i;
/* 394:    */  }
/* 395:    */  
/* 399:    */  public boolean addAndMoveToFirst(float k)
/* 400:    */  {
/* 401:401 */    float[] key = this.key;
/* 402:402 */    boolean[] used = this.used;
/* 403:403 */    int mask = this.mask;
/* 404:    */    
/* 405:405 */    int pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & mask;
/* 406:    */    
/* 407:407 */    while (used[pos] != 0) {
/* 408:408 */      if (k == key[pos]) {
/* 409:409 */        moveIndexToFirst(pos);
/* 410:410 */        return false;
/* 411:    */      }
/* 412:412 */      pos = pos + 1 & mask;
/* 413:    */    }
/* 414:414 */    used[pos] = true;
/* 415:415 */    key[pos] = k;
/* 416:416 */    if (this.size == 0) {
/* 417:417 */      this.first = (this.last = pos);
/* 418:    */      
/* 419:419 */      this.link[pos] = -1L;
/* 420:    */    }
/* 421:    */    else {
/* 422:422 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 423:423 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 424:424 */      this.first = pos;
/* 425:    */    }
/* 426:426 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 427:    */    }
/* 428:428 */    return true;
/* 429:    */  }
/* 430:    */  
/* 434:    */  public boolean addAndMoveToLast(float k)
/* 435:    */  {
/* 436:436 */    float[] key = this.key;
/* 437:437 */    boolean[] used = this.used;
/* 438:438 */    int mask = this.mask;
/* 439:    */    
/* 440:440 */    int pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & mask;
/* 441:    */    
/* 442:442 */    while (used[pos] != 0) {
/* 443:443 */      if (k == key[pos]) {
/* 444:444 */        moveIndexToLast(pos);
/* 445:445 */        return false;
/* 446:    */      }
/* 447:447 */      pos = pos + 1 & mask;
/* 448:    */    }
/* 449:449 */    used[pos] = true;
/* 450:450 */    key[pos] = k;
/* 451:451 */    if (this.size == 0) {
/* 452:452 */      this.first = (this.last = pos);
/* 453:    */      
/* 454:454 */      this.link[pos] = -1L;
/* 455:    */    }
/* 456:    */    else {
/* 457:457 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 458:458 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 459:459 */      this.last = pos;
/* 460:    */    }
/* 461:461 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/* 462:    */    }
/* 463:463 */    return true;
/* 464:    */  }
/* 465:    */  
/* 470:    */  public void clear()
/* 471:    */  {
/* 472:472 */    if (this.size == 0) return;
/* 473:473 */    this.size = 0;
/* 474:474 */    BooleanArrays.fill(this.used, false);
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
/* 571:    */  public float firstFloat()
/* 572:    */  {
/* 573:573 */    if (this.size == 0) throw new NoSuchElementException();
/* 574:574 */    return this.key[this.first];
/* 575:    */  }
/* 576:    */  
/* 579:    */  public float lastFloat()
/* 580:    */  {
/* 581:581 */    if (this.size == 0) throw new NoSuchElementException();
/* 582:582 */    return this.key[this.last]; }
/* 583:    */  
/* 584:584 */  public FloatSortedSet tailSet(float from) { throw new UnsupportedOperationException(); }
/* 585:585 */  public FloatSortedSet headSet(float to) { throw new UnsupportedOperationException(); }
/* 586:586 */  public FloatSortedSet subSet(float from, float to) { throw new UnsupportedOperationException(); }
/* 587:587 */  public FloatComparator comparator() { return null; }
/* 588:    */  
/* 593:    */  private class SetIterator
/* 594:    */    extends AbstractFloatListIterator
/* 595:    */  {
/* 596:596 */    int prev = -1;
/* 597:    */    
/* 598:598 */    int next = -1;
/* 599:    */    
/* 600:600 */    int curr = -1;
/* 601:    */    
/* 602:602 */    int index = -1;
/* 603:    */    
/* 604:604 */    SetIterator() { this.next = FloatLinkedOpenHashSet.this.first;
/* 605:605 */      this.index = 0;
/* 606:    */    }
/* 607:    */    
/* 608:608 */    SetIterator(float from) { if (FloatLinkedOpenHashSet.this.key[FloatLinkedOpenHashSet.this.last] == from) {
/* 609:609 */        this.prev = FloatLinkedOpenHashSet.this.last;
/* 610:610 */        this.index = FloatLinkedOpenHashSet.this.size;
/* 611:    */      }
/* 612:    */      else
/* 613:    */      {
/* 614:614 */        int pos = HashCommon.murmurHash3(HashCommon.float2int(from)) & FloatLinkedOpenHashSet.this.mask;
/* 615:    */        
/* 616:616 */        while (FloatLinkedOpenHashSet.this.used[pos] != 0) {
/* 617:617 */          if (FloatLinkedOpenHashSet.this.key[pos] == from)
/* 618:    */          {
/* 619:619 */            this.next = ((int)FloatLinkedOpenHashSet.this.link[pos]);
/* 620:620 */            this.prev = pos;
/* 621:621 */            return;
/* 622:    */          }
/* 623:623 */          pos = pos + 1 & FloatLinkedOpenHashSet.this.mask;
/* 624:    */        }
/* 625:625 */        throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/* 626:    */      } }
/* 627:    */    
/* 628:628 */    public boolean hasNext() { return this.next != -1; }
/* 629:629 */    public boolean hasPrevious() { return this.prev != -1; }
/* 630:    */    
/* 631:631 */    public float nextFloat() { if (!hasNext()) throw new NoSuchElementException();
/* 632:632 */      this.curr = this.next;
/* 633:633 */      this.next = ((int)FloatLinkedOpenHashSet.this.link[this.curr]);
/* 634:634 */      this.prev = this.curr;
/* 635:635 */      if (this.index >= 0) { this.index += 1;
/* 636:    */      }
/* 637:637 */      return FloatLinkedOpenHashSet.this.key[this.curr];
/* 638:    */    }
/* 639:    */    
/* 640:640 */    public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException();
/* 641:641 */      this.curr = this.prev;
/* 642:642 */      this.prev = ((int)(FloatLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 643:643 */      this.next = this.curr;
/* 644:644 */      if (this.index >= 0) this.index -= 1;
/* 645:645 */      return FloatLinkedOpenHashSet.this.key[this.curr];
/* 646:    */    }
/* 647:    */    
/* 648:648 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/* 649:649 */      if (this.prev == -1) {
/* 650:650 */        this.index = 0;
/* 651:651 */        return;
/* 652:    */      }
/* 653:653 */      if (this.next == -1) {
/* 654:654 */        this.index = FloatLinkedOpenHashSet.this.size;
/* 655:655 */        return;
/* 656:    */      }
/* 657:657 */      int pos = FloatLinkedOpenHashSet.this.first;
/* 658:658 */      this.index = 1;
/* 659:659 */      while (pos != this.prev) {
/* 660:660 */        pos = (int)FloatLinkedOpenHashSet.this.link[pos];
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
/* 680:680 */        this.prev = ((int)(FloatLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 681:    */      }
/* 682:    */      else {
/* 683:683 */        this.next = ((int)FloatLinkedOpenHashSet.this.link[this.curr]); }
/* 684:684 */      FloatLinkedOpenHashSet.this.size -= 1;
/* 685:    */      
/* 687:687 */      if (this.prev == -1) { FloatLinkedOpenHashSet.this.first = this.next;
/* 688:    */      } else
/* 689:689 */        FloatLinkedOpenHashSet.this.link[this.prev] ^= (FloatLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 690:690 */      if (this.next == -1) { FloatLinkedOpenHashSet.this.last = this.prev;
/* 691:    */      } else
/* 692:692 */        FloatLinkedOpenHashSet.this.link[this.next] ^= (FloatLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/* 693:693 */      int pos = this.curr;
/* 694:    */      int last;
/* 695:    */      for (;;) {
/* 696:696 */        pos = (last = pos) + 1 & FloatLinkedOpenHashSet.this.mask;
/* 697:697 */        while (FloatLinkedOpenHashSet.this.used[pos] != 0) {
/* 698:698 */          int slot = HashCommon.murmurHash3(HashCommon.float2int(FloatLinkedOpenHashSet.this.key[pos])) & FloatLinkedOpenHashSet.this.mask;
/* 699:699 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 700:700 */          pos = pos + 1 & FloatLinkedOpenHashSet.this.mask;
/* 701:    */        }
/* 702:702 */        if (FloatLinkedOpenHashSet.this.used[pos] == 0) break;
/* 703:703 */        FloatLinkedOpenHashSet.this.key[last] = FloatLinkedOpenHashSet.this.key[pos];
/* 704:704 */        if (this.next == pos) this.next = last;
/* 705:705 */        if (this.prev == pos) this.prev = last;
/* 706:706 */        FloatLinkedOpenHashSet.this.fixPointers(pos, last);
/* 707:    */      }
/* 708:708 */      FloatLinkedOpenHashSet.this.used[last] = false;
/* 709:709 */      this.curr = -1;
/* 710:    */    }
/* 711:    */  }
/* 712:    */  
/* 718:    */  public FloatListIterator iterator(float from)
/* 719:    */  {
/* 720:720 */    return new SetIterator(from);
/* 721:    */  }
/* 722:    */  
/* 723:723 */  public FloatListIterator iterator() { return new SetIterator(); }
/* 724:    */  
/* 734:    */  @Deprecated
/* 735:    */  public boolean rehash()
/* 736:    */  {
/* 737:737 */    return true;
/* 738:    */  }
/* 739:    */  
/* 750:    */  public boolean trim()
/* 751:    */  {
/* 752:752 */    int l = HashCommon.arraySize(this.size, this.f);
/* 753:753 */    if (l >= this.n) return true;
/* 754:    */    try {
/* 755:755 */      rehash(l);
/* 756:    */    } catch (OutOfMemoryError cantDoIt) {
/* 757:757 */      return false; }
/* 758:758 */    return true;
/* 759:    */  }
/* 760:    */  
/* 777:    */  public boolean trim(int n)
/* 778:    */  {
/* 779:779 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 780:780 */    if (this.n <= l) return true;
/* 781:    */    try {
/* 782:782 */      rehash(l);
/* 783:    */    } catch (OutOfMemoryError cantDoIt) {
/* 784:784 */      return false; }
/* 785:785 */    return true;
/* 786:    */  }
/* 787:    */  
/* 796:    */  protected void rehash(int newN)
/* 797:    */  {
/* 798:798 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 799:    */    
/* 800:800 */    float[] key = this.key;
/* 801:801 */    int newMask = newN - 1;
/* 802:802 */    float[] newKey = new float[newN];
/* 803:803 */    boolean[] newUsed = new boolean[newN];
/* 804:804 */    long[] link = this.link;
/* 805:805 */    long[] newLink = new long[newN];
/* 806:806 */    this.first = -1;
/* 807:807 */    for (int j = this.size; j-- != 0;) {
/* 808:808 */      float k = key[i];
/* 809:809 */      int pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & newMask;
/* 810:810 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 811:811 */      newUsed[pos] = true;
/* 812:812 */      newKey[pos] = k;
/* 813:813 */      if (prev != -1) {
/* 814:814 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 815:815 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 816:816 */        newPrev = pos;
/* 817:    */      }
/* 818:    */      else {
/* 819:819 */        newPrev = this.first = pos;
/* 820:    */        
/* 821:821 */        newLink[pos] = -1L;
/* 822:    */      }
/* 823:823 */      int t = i;
/* 824:824 */      i = (int)link[i];
/* 825:825 */      prev = t;
/* 826:    */    }
/* 827:827 */    this.n = newN;
/* 828:828 */    this.mask = newMask;
/* 829:829 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 830:830 */    this.key = newKey;
/* 831:831 */    this.used = newUsed;
/* 832:832 */    this.link = newLink;
/* 833:833 */    this.last = newPrev;
/* 834:834 */    if (newPrev != -1)
/* 835:    */    {
/* 836:836 */      newLink[newPrev] |= 4294967295L;
/* 837:    */    }
/* 838:    */  }
/* 839:    */  
/* 842:    */  public FloatLinkedOpenHashSet clone()
/* 843:    */  {
/* 844:    */    FloatLinkedOpenHashSet c;
/* 845:    */    
/* 847:    */    try
/* 848:    */    {
/* 849:849 */      c = (FloatLinkedOpenHashSet)super.clone();
/* 850:    */    }
/* 851:    */    catch (CloneNotSupportedException cantHappen) {
/* 852:852 */      throw new InternalError();
/* 853:    */    }
/* 854:854 */    c.key = ((float[])this.key.clone());
/* 855:855 */    c.used = ((boolean[])this.used.clone());
/* 856:856 */    c.link = ((long[])this.link.clone());
/* 857:857 */    return c;
/* 858:    */  }
/* 859:    */  
/* 867:    */  public int hashCode()
/* 868:    */  {
/* 869:869 */    int h = 0;int i = 0;int j = this.size;
/* 870:870 */    while (j-- != 0) {
/* 871:871 */      while (this.used[i] == 0) i++;
/* 872:872 */      h += HashCommon.float2int(this.key[i]);
/* 873:873 */      i++;
/* 874:    */    }
/* 875:875 */    return h;
/* 876:    */  }
/* 877:    */  
/* 878:878 */  private void writeObject(ObjectOutputStream s) throws IOException { FloatIterator i = iterator();
/* 879:879 */    s.defaultWriteObject();
/* 880:880 */    for (int j = this.size; j-- != 0; s.writeFloat(i.nextFloat())) {}
/* 881:    */  }
/* 882:    */  
/* 883:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 884:884 */    s.defaultReadObject();
/* 885:885 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 886:886 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 887:887 */    this.mask = (this.n - 1);
/* 888:888 */    float[] key = this.key = new float[this.n];
/* 889:889 */    boolean[] used = this.used = new boolean[this.n];
/* 890:890 */    long[] link = this.link = new long[this.n];
/* 891:891 */    int prev = -1;
/* 892:892 */    this.first = (this.last = -1);
/* 893:    */    
/* 894:894 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 895:895 */      float k = s.readFloat();
/* 896:896 */      pos = HashCommon.murmurHash3(HashCommon.float2int(k)) & this.mask;
/* 897:897 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 898:898 */      used[pos] = true;
/* 899:899 */      key[pos] = k;
/* 900:900 */      if (this.first != -1) {
/* 901:901 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 902:902 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 903:903 */        prev = pos;
/* 904:    */      }
/* 905:    */      else {
/* 906:906 */        prev = this.first = pos;
/* 907:    */        
/* 908:908 */        link[pos] |= -4294967296L;
/* 909:    */      }
/* 910:    */    }
/* 911:911 */    this.last = prev;
/* 912:912 */    if (prev != -1)
/* 913:    */    {
/* 914:914 */      link[prev] |= 4294967295L;
/* 915:    */    }
/* 916:    */  }
/* 917:    */  
/* 918:    */  private void checkTable() {}
/* 919:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatLinkedOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */