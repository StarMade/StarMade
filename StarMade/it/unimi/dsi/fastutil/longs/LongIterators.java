/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*   4:    */import it.unimi.dsi.fastutil.ints.IntIterator;
/*   5:    */import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.ListIterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  54:    */public class LongIterators
/*  55:    */{
/*  56:    */  public static class EmptyIterator
/*  57:    */    extends AbstractLongListIterator
/*  58:    */    implements Serializable, Cloneable
/*  59:    */  {
/*  60:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  61:    */    
/*  62: 62 */    public boolean hasNext() { return false; }
/*  63: 63 */    public boolean hasPrevious() { return false; }
/*  64: 64 */    public long nextLong() { throw new NoSuchElementException(); }
/*  65: 65 */    public long previousLong() { throw new NoSuchElementException(); }
/*  66: 66 */    public int nextIndex() { return 0; }
/*  67: 67 */    public int previousIndex() { return -1; }
/*  68: 68 */    public int skip(int n) { return 0; }
/*  69: 69 */    public int back(int n) { return 0; }
/*  70: 70 */    public Object clone() { return LongIterators.EMPTY_ITERATOR; }
/*  71: 71 */    private Object readResolve() { return LongIterators.EMPTY_ITERATOR; }
/*  72:    */  }
/*  73:    */  
/*  79: 79 */  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*  80:    */  
/*  81:    */  private static class SingletonIterator extends AbstractLongListIterator {
/*  82:    */    private final long element;
/*  83:    */    private int curr;
/*  84:    */    
/*  85: 85 */    public SingletonIterator(long element) { this.element = element; }
/*  86:    */    
/*  87: 87 */    public boolean hasNext() { return this.curr == 0; }
/*  88: 88 */    public boolean hasPrevious() { return this.curr == 1; }
/*  89:    */    
/*  90: 90 */    public long nextLong() { if (!hasNext()) throw new NoSuchElementException();
/*  91: 91 */      this.curr = 1;
/*  92: 92 */      return this.element;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    public long previousLong() { if (!hasPrevious()) throw new NoSuchElementException();
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
/* 110:    */  public static LongListIterator singleton(long element)
/* 111:    */  {
/* 112:112 */    return new SingletonIterator(element);
/* 113:    */  }
/* 114:    */  
/* 115:    */  private static class ArrayIterator extends AbstractLongListIterator {
/* 116:    */    private final long[] array;
/* 117:    */    private final int offset;
/* 118:    */    private final int length;
/* 119:    */    private int curr;
/* 120:    */    
/* 121:    */    public ArrayIterator(long[] array, int offset, int length) {
/* 122:122 */      this.array = array;
/* 123:123 */      this.offset = offset;
/* 124:124 */      this.length = length;
/* 125:    */    }
/* 126:    */    
/* 127:127 */    public boolean hasNext() { return this.curr < this.length; }
/* 128:128 */    public boolean hasPrevious() { return this.curr > 0; }
/* 129:    */    
/* 130:    */    public long nextLong() {
/* 131:131 */      if (!hasNext()) throw new NoSuchElementException();
/* 132:132 */      return this.array[(this.offset + this.curr++)];
/* 133:    */    }
/* 134:    */    
/* 135:    */    public long previousLong() {
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
/* 179:    */  public static LongListIterator wrap(long[] array, int offset, int length)
/* 180:    */  {
/* 181:181 */    LongArrays.ensureOffsetLength(array, offset, length);
/* 182:182 */    return new ArrayIterator(array, offset, length);
/* 183:    */  }
/* 184:    */  
/* 191:    */  public static LongListIterator wrap(long[] array)
/* 192:    */  {
/* 193:193 */    return new ArrayIterator(array, 0, array.length);
/* 194:    */  }
/* 195:    */  
/* 209:    */  public static int unwrap(LongIterator i, long[] array, int offset, int max)
/* 210:    */  {
/* 211:211 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212:212 */    if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213:213 */    int j = max;
/* 214:214 */    while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextLong();
/* 215:215 */    return max - j - 1;
/* 216:    */  }
/* 217:    */  
/* 227:    */  public static int unwrap(LongIterator i, long[] array)
/* 228:    */  {
/* 229:229 */    return unwrap(i, array, 0, array.length);
/* 230:    */  }
/* 231:    */  
/* 242:    */  public static long[] unwrap(LongIterator i, int max)
/* 243:    */  {
/* 244:244 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245:245 */    long[] array = new long[16];
/* 246:246 */    int j = 0;
/* 247:    */    
/* 248:248 */    while ((max-- != 0) && (i.hasNext())) {
/* 249:249 */      if (j == array.length) array = LongArrays.grow(array, j + 1);
/* 250:250 */      array[(j++)] = i.nextLong();
/* 251:    */    }
/* 252:    */    
/* 253:253 */    return LongArrays.trim(array, j);
/* 254:    */  }
/* 255:    */  
/* 265:    */  public static long[] unwrap(LongIterator i)
/* 266:    */  {
/* 267:267 */    return unwrap(i, 2147483647);
/* 268:    */  }
/* 269:    */  
/* 284:    */  public static int unwrap(LongIterator i, LongCollection c, int max)
/* 285:    */  {
/* 286:286 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287:287 */    int j = max;
/* 288:288 */    while ((j-- != 0) && (i.hasNext())) c.add(i.nextLong());
/* 289:289 */    return max - j - 1;
/* 290:    */  }
/* 291:    */  
/* 303:    */  public static long unwrap(LongIterator i, LongCollection c)
/* 304:    */  {
/* 305:305 */    long n = 0L;
/* 306:306 */    while (i.hasNext()) {
/* 307:307 */      c.add(i.nextLong());
/* 308:308 */      n += 1L;
/* 309:    */    }
/* 310:310 */    return n;
/* 311:    */  }
/* 312:    */  
/* 326:    */  public static int pour(LongIterator i, LongCollection s, int max)
/* 327:    */  {
/* 328:328 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329:329 */    int j = max;
/* 330:330 */    while ((j-- != 0) && (i.hasNext())) s.add(i.nextLong());
/* 331:331 */    return max - j - 1;
/* 332:    */  }
/* 333:    */  
/* 345:    */  public static int pour(LongIterator i, LongCollection s)
/* 346:    */  {
/* 347:347 */    return pour(i, s, 2147483647);
/* 348:    */  }
/* 349:    */  
/* 362:    */  public static LongList pour(LongIterator i, int max)
/* 363:    */  {
/* 364:364 */    LongArrayList l = new LongArrayList();
/* 365:365 */    pour(i, l, max);
/* 366:366 */    l.trim();
/* 367:367 */    return l;
/* 368:    */  }
/* 369:    */  
/* 380:    */  public static LongList pour(LongIterator i)
/* 381:    */  {
/* 382:382 */    return pour(i, 2147483647);
/* 383:    */  }
/* 384:    */  
/* 385:    */  private static class IteratorWrapper extends AbstractLongIterator {
/* 386:    */    final Iterator<Long> i;
/* 387:    */    
/* 388:    */    public IteratorWrapper(Iterator<Long> i) {
/* 389:389 */      this.i = i;
/* 390:    */    }
/* 391:    */    
/* 392:392 */    public boolean hasNext() { return this.i.hasNext(); }
/* 393:393 */    public void remove() { this.i.remove(); }
/* 394:    */    
/* 395:395 */    public long nextLong() { return ((Long)this.i.next()).longValue(); }
/* 396:    */  }
/* 397:    */  
/* 412:    */  public static LongIterator asLongIterator(Iterator i)
/* 413:    */  {
/* 414:414 */    if ((i instanceof LongIterator)) return (LongIterator)i;
/* 415:415 */    return new IteratorWrapper(i);
/* 416:    */  }
/* 417:    */  
/* 418:    */  private static class ListIteratorWrapper extends AbstractLongListIterator
/* 419:    */  {
/* 420:    */    final ListIterator<Long> i;
/* 421:    */    
/* 422:    */    public ListIteratorWrapper(ListIterator<Long> i) {
/* 423:423 */      this.i = i;
/* 424:    */    }
/* 425:    */    
/* 426:426 */    public boolean hasNext() { return this.i.hasNext(); }
/* 427:427 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 428:428 */    public int nextIndex() { return this.i.nextIndex(); }
/* 429:429 */    public int previousIndex() { return this.i.previousIndex(); }
/* 430:    */    
/* 431:431 */    public void set(long k) { this.i.set(Long.valueOf(k)); }
/* 432:    */    
/* 433:433 */    public void add(long k) { this.i.add(Long.valueOf(k)); }
/* 434:434 */    public void remove() { this.i.remove(); }
/* 435:    */    
/* 436:436 */    public long nextLong() { return ((Long)this.i.next()).longValue(); }
/* 437:437 */    public long previousLong() { return ((Long)this.i.previous()).longValue(); }
/* 438:    */  }
/* 439:    */  
/* 455:    */  public static LongListIterator asLongIterator(ListIterator i)
/* 456:    */  {
/* 457:457 */    if ((i instanceof LongListIterator)) return (LongListIterator)i;
/* 458:458 */    return new ListIteratorWrapper(i);
/* 459:    */  }
/* 460:    */  
/* 462:    */  private static class IntervalIterator
/* 463:    */    extends AbstractLongBidirectionalIterator
/* 464:    */  {
/* 465:    */    private final long from;
/* 466:    */    
/* 467:    */    private final long to;
/* 468:    */    
/* 469:    */    long curr;
/* 470:    */    
/* 471:    */    public IntervalIterator(long from, long to)
/* 472:    */    {
/* 473:473 */      this.from = (this.curr = from);
/* 474:474 */      this.to = to;
/* 475:    */    }
/* 476:    */    
/* 477:477 */    public boolean hasNext() { return this.curr < this.to; }
/* 478:478 */    public boolean hasPrevious() { return this.curr > this.from; }
/* 479:    */    
/* 480:    */    public long nextLong() {
/* 481:481 */      if (!hasNext()) throw new NoSuchElementException();
/* 482:482 */      return this.curr++;
/* 483:    */    }
/* 484:    */    
/* 485:485 */    public long previousLong() { if (!hasPrevious()) throw new NoSuchElementException();
/* 486:486 */      return --this.curr;
/* 487:    */    }
/* 488:    */    
/* 493:    */    public int skip(int n)
/* 494:    */    {
/* 495:495 */      if (this.curr + n <= this.to) {
/* 496:496 */        this.curr += n;
/* 497:497 */        return n;
/* 498:    */      }
/* 499:    */      
/* 502:502 */      n = (int)(this.to - this.curr);
/* 503:    */      
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
/* 516:516 */      n = (int)(this.curr - this.from);
/* 517:    */      
/* 518:518 */      this.curr = this.from;
/* 519:519 */      return n;
/* 520:    */    }
/* 521:    */  }
/* 522:    */  
/* 539:539 */  public static LongBidirectionalIterator fromTo(long from, long to) { return new IntervalIterator(from, to); }
/* 540:    */  
/* 541:    */  private static class IteratorConcatenator extends AbstractLongIterator { final LongIterator[] a;
/* 542:    */    int offset;
/* 543:543 */    int length; int lastOffset = -1;
/* 544:    */    
/* 545:545 */    public IteratorConcatenator(LongIterator[] a, int offset, int length) { this.a = a;
/* 546:546 */      this.offset = offset;
/* 547:547 */      this.length = length;
/* 548:548 */      advance();
/* 549:    */    }
/* 550:    */    
/* 551:551 */    private void advance() { while ((this.length != 0) && 
/* 552:552 */        (!this.a[this.offset].hasNext())) {
/* 553:553 */        this.length -= 1;
/* 554:554 */        this.offset += 1;
/* 555:    */      }
/* 556:    */    }
/* 557:    */    
/* 559:559 */    public boolean hasNext() { return this.length > 0; }
/* 560:    */    
/* 561:    */    public long nextLong() {
/* 562:562 */      if (!hasNext()) throw new NoSuchElementException();
/* 563:563 */      long next = this.a[(this.lastOffset = this.offset)].nextLong();
/* 564:564 */      advance();
/* 565:565 */      return next;
/* 566:    */    }
/* 567:    */    
/* 568:568 */    public void remove() { if (this.lastOffset == -1) throw new IllegalStateException();
/* 569:569 */      this.a[this.lastOffset].remove();
/* 570:    */    }
/* 571:    */    
/* 572:572 */    public int skip(int n) { this.lastOffset = -1;
/* 573:573 */      int skipped = 0;
/* 574:574 */      while ((skipped < n) && (this.length != 0)) {
/* 575:575 */        skipped += this.a[this.offset].skip(n - skipped);
/* 576:576 */        if (this.a[this.offset].hasNext()) break;
/* 577:577 */        this.length -= 1;
/* 578:578 */        this.offset += 1;
/* 579:    */      }
/* 580:580 */      return skipped;
/* 581:    */    }
/* 582:    */  }
/* 583:    */  
/* 590:    */  public static LongIterator concat(LongIterator[] a)
/* 591:    */  {
/* 592:592 */    return concat(a, 0, a.length);
/* 593:    */  }
/* 594:    */  
/* 605:    */  public static LongIterator concat(LongIterator[] a, int offset, int length)
/* 606:    */  {
/* 607:607 */    return new IteratorConcatenator(a, offset, length);
/* 608:    */  }
/* 609:    */  
/* 610:    */  public static class UnmodifiableIterator
/* 611:    */    extends AbstractLongIterator {
/* 612:    */    protected final LongIterator i;
/* 613:    */    
/* 614:614 */    public UnmodifiableIterator(LongIterator i) { this.i = i; }
/* 615:    */    
/* 616:616 */    public boolean hasNext() { return this.i.hasNext(); }
/* 617:617 */    public long nextLong() { return this.i.nextLong(); }
/* 618:618 */    public Long next() { return (Long)this.i.next(); }
/* 619:    */  }
/* 620:    */  
/* 623:    */  public static LongIterator unmodifiable(LongIterator i)
/* 624:    */  {
/* 625:625 */    return new UnmodifiableIterator(i);
/* 626:    */  }
/* 627:    */  
/* 628:    */  public static class UnmodifiableBidirectionalIterator extends AbstractLongBidirectionalIterator {
/* 629:    */    protected final LongBidirectionalIterator i;
/* 630:    */    
/* 631:631 */    public UnmodifiableBidirectionalIterator(LongBidirectionalIterator i) { this.i = i; }
/* 632:    */    
/* 633:633 */    public boolean hasNext() { return this.i.hasNext(); }
/* 634:634 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 635:635 */    public long nextLong() { return this.i.nextLong(); }
/* 636:636 */    public long previousLong() { return this.i.previousLong(); }
/* 637:637 */    public Long next() { return (Long)this.i.next(); }
/* 638:638 */    public Long previous() { return (Long)this.i.previous(); }
/* 639:    */  }
/* 640:    */  
/* 643:    */  public static LongBidirectionalIterator unmodifiable(LongBidirectionalIterator i)
/* 644:    */  {
/* 645:645 */    return new UnmodifiableBidirectionalIterator(i);
/* 646:    */  }
/* 647:    */  
/* 648:    */  public static class UnmodifiableListIterator extends AbstractLongListIterator {
/* 649:    */    protected final LongListIterator i;
/* 650:    */    
/* 651:651 */    public UnmodifiableListIterator(LongListIterator i) { this.i = i; }
/* 652:    */    
/* 653:653 */    public boolean hasNext() { return this.i.hasNext(); }
/* 654:654 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 655:655 */    public long nextLong() { return this.i.nextLong(); }
/* 656:656 */    public long previousLong() { return this.i.previousLong(); }
/* 657:657 */    public int nextIndex() { return this.i.nextIndex(); }
/* 658:658 */    public int previousIndex() { return this.i.previousIndex(); }
/* 659:659 */    public Long next() { return (Long)this.i.next(); }
/* 660:660 */    public Long previous() { return (Long)this.i.previous(); }
/* 661:    */  }
/* 662:    */  
/* 667:667 */  public static LongListIterator unmodifiable(LongListIterator i) { return new UnmodifiableListIterator(i); }
/* 668:    */  
/* 669:    */  protected static class ByteIteratorWrapper implements LongIterator {
/* 670:    */    final ByteIterator iterator;
/* 671:    */    
/* 672:672 */    public ByteIteratorWrapper(ByteIterator iterator) { this.iterator = iterator; }
/* 673:    */    
/* 674:674 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 675:675 */    public Long next() { return Long.valueOf(this.iterator.nextByte()); }
/* 676:676 */    public long nextLong() { return this.iterator.nextByte(); }
/* 677:677 */    public void remove() { this.iterator.remove(); }
/* 678:678 */    public int skip(int n) { return this.iterator.skip(n); }
/* 679:    */  }
/* 680:    */  
/* 682:    */  public static LongIterator wrap(ByteIterator iterator)
/* 683:    */  {
/* 684:684 */    return new ByteIteratorWrapper(iterator);
/* 685:    */  }
/* 686:    */  
/* 687:    */  protected static class ShortIteratorWrapper implements LongIterator {
/* 688:    */    final ShortIterator iterator;
/* 689:    */    
/* 690:690 */    public ShortIteratorWrapper(ShortIterator iterator) { this.iterator = iterator; }
/* 691:    */    
/* 692:692 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 693:693 */    public Long next() { return Long.valueOf(this.iterator.nextShort()); }
/* 694:694 */    public long nextLong() { return this.iterator.nextShort(); }
/* 695:695 */    public void remove() { this.iterator.remove(); }
/* 696:696 */    public int skip(int n) { return this.iterator.skip(n); }
/* 697:    */  }
/* 698:    */  
/* 700:    */  public static LongIterator wrap(ShortIterator iterator)
/* 701:    */  {
/* 702:702 */    return new ShortIteratorWrapper(iterator);
/* 703:    */  }
/* 704:    */  
/* 705:    */  protected static class IntIteratorWrapper implements LongIterator {
/* 706:    */    final IntIterator iterator;
/* 707:    */    
/* 708:708 */    public IntIteratorWrapper(IntIterator iterator) { this.iterator = iterator; }
/* 709:    */    
/* 710:710 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 711:711 */    public Long next() { return Long.valueOf(this.iterator.nextInt()); }
/* 712:712 */    public long nextLong() { return this.iterator.nextInt(); }
/* 713:713 */    public void remove() { this.iterator.remove(); }
/* 714:714 */    public int skip(int n) { return this.iterator.skip(n); }
/* 715:    */  }
/* 716:    */  
/* 718:    */  public static LongIterator wrap(IntIterator iterator)
/* 719:    */  {
/* 720:720 */    return new IntIteratorWrapper(iterator);
/* 721:    */  }
/* 722:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */