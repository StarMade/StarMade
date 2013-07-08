/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*   4:    */import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.ListIterator;
/*   8:    */import java.util.NoSuchElementException;
/*   9:    */
/*  54:    */public class IntIterators
/*  55:    */{
/*  56:    */  public static class EmptyIterator
/*  57:    */    extends AbstractIntListIterator
/*  58:    */    implements Serializable, Cloneable
/*  59:    */  {
/*  60:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  61:    */    
/*  62: 62 */    public boolean hasNext() { return false; }
/*  63: 63 */    public boolean hasPrevious() { return false; }
/*  64: 64 */    public int nextInt() { throw new NoSuchElementException(); }
/*  65: 65 */    public int previousInt() { throw new NoSuchElementException(); }
/*  66: 66 */    public int nextIndex() { return 0; }
/*  67: 67 */    public int previousIndex() { return -1; }
/*  68: 68 */    public int skip(int n) { return 0; }
/*  69: 69 */    public int back(int n) { return 0; }
/*  70: 70 */    public Object clone() { return IntIterators.EMPTY_ITERATOR; }
/*  71: 71 */    private Object readResolve() { return IntIterators.EMPTY_ITERATOR; }
/*  72:    */  }
/*  73:    */  
/*  79: 79 */  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*  80:    */  
/*  81:    */  private static class SingletonIterator extends AbstractIntListIterator {
/*  82:    */    private final int element;
/*  83:    */    private int curr;
/*  84:    */    
/*  85: 85 */    public SingletonIterator(int element) { this.element = element; }
/*  86:    */    
/*  87: 87 */    public boolean hasNext() { return this.curr == 0; }
/*  88: 88 */    public boolean hasPrevious() { return this.curr == 1; }
/*  89:    */    
/*  90: 90 */    public int nextInt() { if (!hasNext()) throw new NoSuchElementException();
/*  91: 91 */      this.curr = 1;
/*  92: 92 */      return this.element;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    public int previousInt() { if (!hasPrevious()) throw new NoSuchElementException();
/*  96: 96 */      this.curr = 0;
/*  97: 97 */      return this.element;
/*  98:    */    }
/*  99:    */    
/* 100:100 */    public int nextIndex() { return this.curr; }
/* 101:    */    
/* 102:    */    public int previousIndex() {
/* 103:103 */      return this.curr - 1;
/* 104:    */    }
/* 105:    */  }
/* 106:    */  
/* 110:    */  public static IntListIterator singleton(int element)
/* 111:    */  {
/* 112:112 */    return new SingletonIterator(element);
/* 113:    */  }
/* 114:    */  
/* 115:    */  private static class ArrayIterator extends AbstractIntListIterator {
/* 116:    */    private final int[] array;
/* 117:    */    private final int offset;
/* 118:    */    private final int length;
/* 119:    */    private int curr;
/* 120:    */    
/* 121:    */    public ArrayIterator(int[] array, int offset, int length) {
/* 122:122 */      this.array = array;
/* 123:123 */      this.offset = offset;
/* 124:124 */      this.length = length;
/* 125:    */    }
/* 126:    */    
/* 127:127 */    public boolean hasNext() { return this.curr < this.length; }
/* 128:128 */    public boolean hasPrevious() { return this.curr > 0; }
/* 129:    */    
/* 130:    */    public int nextInt() {
/* 131:131 */      if (!hasNext()) throw new NoSuchElementException();
/* 132:132 */      return this.array[(this.offset + this.curr++)];
/* 133:    */    }
/* 134:    */    
/* 135:    */    public int previousInt() {
/* 136:136 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 137:137 */      return this.array[(this.offset + --this.curr)];
/* 138:    */    }
/* 139:    */    
/* 140:    */    public int skip(int n) {
/* 141:141 */      if (n <= this.length - this.curr) {
/* 142:142 */        this.curr += n;
/* 143:143 */        return n;
/* 144:    */      }
/* 145:145 */      n = this.length - this.curr;
/* 146:146 */      this.curr = this.length;
/* 147:147 */      return n;
/* 148:    */    }
/* 149:    */    
/* 150:    */    public int back(int n) {
/* 151:151 */      if (n <= this.curr) {
/* 152:152 */        this.curr -= n;
/* 153:153 */        return n;
/* 154:    */      }
/* 155:155 */      n = this.curr;
/* 156:156 */      this.curr = 0;
/* 157:157 */      return n;
/* 158:    */    }
/* 159:    */    
/* 160:    */    public int nextIndex() {
/* 161:161 */      return this.curr;
/* 162:    */    }
/* 163:    */    
/* 164:    */    public int previousIndex() {
/* 165:165 */      return this.curr - 1;
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 179:    */  public static IntListIterator wrap(int[] array, int offset, int length)
/* 180:    */  {
/* 181:181 */    IntArrays.ensureOffsetLength(array, offset, length);
/* 182:182 */    return new ArrayIterator(array, offset, length);
/* 183:    */  }
/* 184:    */  
/* 191:    */  public static IntListIterator wrap(int[] array)
/* 192:    */  {
/* 193:193 */    return new ArrayIterator(array, 0, array.length);
/* 194:    */  }
/* 195:    */  
/* 209:    */  public static int unwrap(IntIterator i, int[] array, int offset, int max)
/* 210:    */  {
/* 211:211 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212:212 */    if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213:213 */    int j = max;
/* 214:214 */    while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextInt();
/* 215:215 */    return max - j - 1;
/* 216:    */  }
/* 217:    */  
/* 227:    */  public static int unwrap(IntIterator i, int[] array)
/* 228:    */  {
/* 229:229 */    return unwrap(i, array, 0, array.length);
/* 230:    */  }
/* 231:    */  
/* 242:    */  public static int[] unwrap(IntIterator i, int max)
/* 243:    */  {
/* 244:244 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245:245 */    int[] array = new int[16];
/* 246:246 */    int j = 0;
/* 247:    */    
/* 248:248 */    while ((max-- != 0) && (i.hasNext())) {
/* 249:249 */      if (j == array.length) array = IntArrays.grow(array, j + 1);
/* 250:250 */      array[(j++)] = i.nextInt();
/* 251:    */    }
/* 252:    */    
/* 253:253 */    return IntArrays.trim(array, j);
/* 254:    */  }
/* 255:    */  
/* 265:    */  public static int[] unwrap(IntIterator i)
/* 266:    */  {
/* 267:267 */    return unwrap(i, 2147483647);
/* 268:    */  }
/* 269:    */  
/* 284:    */  public static int unwrap(IntIterator i, IntCollection c, int max)
/* 285:    */  {
/* 286:286 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287:287 */    int j = max;
/* 288:288 */    while ((j-- != 0) && (i.hasNext())) c.add(i.nextInt());
/* 289:289 */    return max - j - 1;
/* 290:    */  }
/* 291:    */  
/* 303:    */  public static long unwrap(IntIterator i, IntCollection c)
/* 304:    */  {
/* 305:305 */    long n = 0L;
/* 306:306 */    while (i.hasNext()) {
/* 307:307 */      c.add(i.nextInt());
/* 308:308 */      n += 1L;
/* 309:    */    }
/* 310:310 */    return n;
/* 311:    */  }
/* 312:    */  
/* 326:    */  public static int pour(IntIterator i, IntCollection s, int max)
/* 327:    */  {
/* 328:328 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329:329 */    int j = max;
/* 330:330 */    while ((j-- != 0) && (i.hasNext())) s.add(i.nextInt());
/* 331:331 */    return max - j - 1;
/* 332:    */  }
/* 333:    */  
/* 345:    */  public static int pour(IntIterator i, IntCollection s)
/* 346:    */  {
/* 347:347 */    return pour(i, s, 2147483647);
/* 348:    */  }
/* 349:    */  
/* 362:    */  public static IntList pour(IntIterator i, int max)
/* 363:    */  {
/* 364:364 */    IntArrayList l = new IntArrayList();
/* 365:365 */    pour(i, l, max);
/* 366:366 */    l.trim();
/* 367:367 */    return l;
/* 368:    */  }
/* 369:    */  
/* 380:    */  public static IntList pour(IntIterator i)
/* 381:    */  {
/* 382:382 */    return pour(i, 2147483647);
/* 383:    */  }
/* 384:    */  
/* 385:    */  private static class IteratorWrapper extends AbstractIntIterator {
/* 386:    */    final Iterator<Integer> i;
/* 387:    */    
/* 388:    */    public IteratorWrapper(Iterator<Integer> i) {
/* 389:389 */      this.i = i;
/* 390:    */    }
/* 391:    */    
/* 392:392 */    public boolean hasNext() { return this.i.hasNext(); }
/* 393:393 */    public void remove() { this.i.remove(); }
/* 394:    */    
/* 395:395 */    public int nextInt() { return ((Integer)this.i.next()).intValue(); }
/* 396:    */  }
/* 397:    */  
/* 412:    */  public static IntIterator asIntIterator(Iterator i)
/* 413:    */  {
/* 414:414 */    if ((i instanceof IntIterator)) return (IntIterator)i;
/* 415:415 */    return new IteratorWrapper(i);
/* 416:    */  }
/* 417:    */  
/* 418:    */  private static class ListIteratorWrapper extends AbstractIntListIterator
/* 419:    */  {
/* 420:    */    final ListIterator<Integer> i;
/* 421:    */    
/* 422:    */    public ListIteratorWrapper(ListIterator<Integer> i) {
/* 423:423 */      this.i = i;
/* 424:    */    }
/* 425:    */    
/* 426:426 */    public boolean hasNext() { return this.i.hasNext(); }
/* 427:427 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 428:428 */    public int nextIndex() { return this.i.nextIndex(); }
/* 429:429 */    public int previousIndex() { return this.i.previousIndex(); }
/* 430:    */    
/* 431:431 */    public void set(int k) { this.i.set(Integer.valueOf(k)); }
/* 432:    */    
/* 433:433 */    public void add(int k) { this.i.add(Integer.valueOf(k)); }
/* 434:434 */    public void remove() { this.i.remove(); }
/* 435:    */    
/* 436:436 */    public int nextInt() { return ((Integer)this.i.next()).intValue(); }
/* 437:437 */    public int previousInt() { return ((Integer)this.i.previous()).intValue(); }
/* 438:    */  }
/* 439:    */  
/* 455:    */  public static IntListIterator asIntIterator(ListIterator i)
/* 456:    */  {
/* 457:457 */    if ((i instanceof IntListIterator)) return (IntListIterator)i;
/* 458:458 */    return new ListIteratorWrapper(i);
/* 459:    */  }
/* 460:    */  
/* 462:    */  private static class IntervalIterator
/* 463:    */    extends AbstractIntListIterator
/* 464:    */  {
/* 465:    */    private final int from;
/* 466:    */    
/* 467:    */    private final int to;
/* 468:    */    
/* 469:    */    int curr;
/* 470:    */    
/* 471:    */    public IntervalIterator(int from, int to)
/* 472:    */    {
/* 473:473 */      this.from = (this.curr = from);
/* 474:474 */      this.to = to;
/* 475:    */    }
/* 476:    */    
/* 477:477 */    public boolean hasNext() { return this.curr < this.to; }
/* 478:478 */    public boolean hasPrevious() { return this.curr > this.from; }
/* 479:    */    
/* 480:    */    public int nextInt() {
/* 481:481 */      if (!hasNext()) throw new NoSuchElementException();
/* 482:482 */      return this.curr++;
/* 483:    */    }
/* 484:    */    
/* 485:485 */    public int previousInt() { if (!hasPrevious()) throw new NoSuchElementException();
/* 486:486 */      return --this.curr;
/* 487:    */    }
/* 488:    */    
/* 490:490 */    public int nextIndex() { return this.curr - this.from; }
/* 491:491 */    public int previousIndex() { return this.curr - this.from - 1; }
/* 492:    */    
/* 493:    */    public int skip(int n)
/* 494:    */    {
/* 495:495 */      if (this.curr + n <= this.to) {
/* 496:496 */        this.curr += n;
/* 497:497 */        return n;
/* 498:    */      }
/* 499:    */      
/* 500:500 */      n = this.to - this.curr;
/* 501:    */      
/* 504:504 */      this.curr = this.to;
/* 505:505 */      return n;
/* 506:    */    }
/* 507:    */    
/* 508:    */    public int back(int n) {
/* 509:509 */      if (this.curr - n >= this.from) {
/* 510:510 */        this.curr -= n;
/* 511:511 */        return n;
/* 512:    */      }
/* 513:    */      
/* 514:514 */      n = this.curr - this.from;
/* 515:    */      
/* 518:518 */      this.curr = this.from;
/* 519:519 */      return n;
/* 520:    */    }
/* 521:    */  }
/* 522:    */  
/* 532:532 */  public static IntListIterator fromTo(int from, int to) { return new IntervalIterator(from, to); }
/* 533:    */  
/* 534:    */  private static class IteratorConcatenator extends AbstractIntIterator { final IntIterator[] a;
/* 535:    */    int offset;
/* 536:536 */    int length; int lastOffset = -1;
/* 537:    */    
/* 538:538 */    public IteratorConcatenator(IntIterator[] a, int offset, int length) { this.a = a;
/* 539:539 */      this.offset = offset;
/* 540:540 */      this.length = length;
/* 541:541 */      advance();
/* 542:    */    }
/* 543:    */    
/* 544:544 */    private void advance() { while ((this.length != 0) && 
/* 545:545 */        (!this.a[this.offset].hasNext())) {
/* 546:546 */        this.length -= 1;
/* 547:547 */        this.offset += 1;
/* 548:    */      }
/* 549:    */    }
/* 550:    */    
/* 552:552 */    public boolean hasNext() { return this.length > 0; }
/* 553:    */    
/* 554:    */    public int nextInt() {
/* 555:555 */      if (!hasNext()) throw new NoSuchElementException();
/* 556:556 */      int next = this.a[(this.lastOffset = this.offset)].nextInt();
/* 557:557 */      advance();
/* 558:558 */      return next;
/* 559:    */    }
/* 560:    */    
/* 561:561 */    public void remove() { if (this.lastOffset == -1) throw new IllegalStateException();
/* 562:562 */      this.a[this.lastOffset].remove();
/* 563:    */    }
/* 564:    */    
/* 565:565 */    public int skip(int n) { this.lastOffset = -1;
/* 566:566 */      int skipped = 0;
/* 567:567 */      while ((skipped < n) && (this.length != 0)) {
/* 568:568 */        skipped += this.a[this.offset].skip(n - skipped);
/* 569:569 */        if (this.a[this.offset].hasNext()) break;
/* 570:570 */        this.length -= 1;
/* 571:571 */        this.offset += 1;
/* 572:    */      }
/* 573:573 */      return skipped;
/* 574:    */    }
/* 575:    */  }
/* 576:    */  
/* 583:    */  public static IntIterator concat(IntIterator[] a)
/* 584:    */  {
/* 585:585 */    return concat(a, 0, a.length);
/* 586:    */  }
/* 587:    */  
/* 598:    */  public static IntIterator concat(IntIterator[] a, int offset, int length)
/* 599:    */  {
/* 600:600 */    return new IteratorConcatenator(a, offset, length);
/* 601:    */  }
/* 602:    */  
/* 603:    */  public static class UnmodifiableIterator
/* 604:    */    extends AbstractIntIterator {
/* 605:    */    protected final IntIterator i;
/* 606:    */    
/* 607:607 */    public UnmodifiableIterator(IntIterator i) { this.i = i; }
/* 608:    */    
/* 609:609 */    public boolean hasNext() { return this.i.hasNext(); }
/* 610:610 */    public int nextInt() { return this.i.nextInt(); }
/* 611:611 */    public Integer next() { return (Integer)this.i.next(); }
/* 612:    */  }
/* 613:    */  
/* 616:    */  public static IntIterator unmodifiable(IntIterator i)
/* 617:    */  {
/* 618:618 */    return new UnmodifiableIterator(i);
/* 619:    */  }
/* 620:    */  
/* 621:    */  public static class UnmodifiableBidirectionalIterator extends AbstractIntBidirectionalIterator {
/* 622:    */    protected final IntBidirectionalIterator i;
/* 623:    */    
/* 624:624 */    public UnmodifiableBidirectionalIterator(IntBidirectionalIterator i) { this.i = i; }
/* 625:    */    
/* 626:626 */    public boolean hasNext() { return this.i.hasNext(); }
/* 627:627 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 628:628 */    public int nextInt() { return this.i.nextInt(); }
/* 629:629 */    public int previousInt() { return this.i.previousInt(); }
/* 630:630 */    public Integer next() { return (Integer)this.i.next(); }
/* 631:631 */    public Integer previous() { return (Integer)this.i.previous(); }
/* 632:    */  }
/* 633:    */  
/* 636:    */  public static IntBidirectionalIterator unmodifiable(IntBidirectionalIterator i)
/* 637:    */  {
/* 638:638 */    return new UnmodifiableBidirectionalIterator(i);
/* 639:    */  }
/* 640:    */  
/* 641:    */  public static class UnmodifiableListIterator extends AbstractIntListIterator {
/* 642:    */    protected final IntListIterator i;
/* 643:    */    
/* 644:644 */    public UnmodifiableListIterator(IntListIterator i) { this.i = i; }
/* 645:    */    
/* 646:646 */    public boolean hasNext() { return this.i.hasNext(); }
/* 647:647 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 648:648 */    public int nextInt() { return this.i.nextInt(); }
/* 649:649 */    public int previousInt() { return this.i.previousInt(); }
/* 650:650 */    public int nextIndex() { return this.i.nextIndex(); }
/* 651:651 */    public int previousIndex() { return this.i.previousIndex(); }
/* 652:652 */    public Integer next() { return (Integer)this.i.next(); }
/* 653:653 */    public Integer previous() { return (Integer)this.i.previous(); }
/* 654:    */  }
/* 655:    */  
/* 660:660 */  public static IntListIterator unmodifiable(IntListIterator i) { return new UnmodifiableListIterator(i); }
/* 661:    */  
/* 662:    */  protected static class ByteIteratorWrapper implements IntIterator {
/* 663:    */    final ByteIterator iterator;
/* 664:    */    
/* 665:665 */    public ByteIteratorWrapper(ByteIterator iterator) { this.iterator = iterator; }
/* 666:    */    
/* 667:667 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 668:668 */    public Integer next() { return Integer.valueOf(this.iterator.nextByte()); }
/* 669:669 */    public int nextInt() { return this.iterator.nextByte(); }
/* 670:670 */    public void remove() { this.iterator.remove(); }
/* 671:671 */    public int skip(int n) { return this.iterator.skip(n); }
/* 672:    */  }
/* 673:    */  
/* 675:    */  public static IntIterator wrap(ByteIterator iterator)
/* 676:    */  {
/* 677:677 */    return new ByteIteratorWrapper(iterator);
/* 678:    */  }
/* 679:    */  
/* 680:    */  protected static class ShortIteratorWrapper implements IntIterator {
/* 681:    */    final ShortIterator iterator;
/* 682:    */    
/* 683:683 */    public ShortIteratorWrapper(ShortIterator iterator) { this.iterator = iterator; }
/* 684:    */    
/* 685:685 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 686:686 */    public Integer next() { return Integer.valueOf(this.iterator.nextShort()); }
/* 687:687 */    public int nextInt() { return this.iterator.nextShort(); }
/* 688:688 */    public void remove() { this.iterator.remove(); }
/* 689:689 */    public int skip(int n) { return this.iterator.skip(n); }
/* 690:    */  }
/* 691:    */  
/* 693:    */  public static IntIterator wrap(ShortIterator iterator)
/* 694:    */  {
/* 695:695 */    return new ShortIteratorWrapper(iterator);
/* 696:    */  }
/* 697:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */