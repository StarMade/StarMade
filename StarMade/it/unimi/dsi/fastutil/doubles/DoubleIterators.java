/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*   4:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   5:    */import it.unimi.dsi.fastutil.ints.IntIterator;
/*   6:    */import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*   7:    */import java.io.Serializable;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.ListIterator;
/*  10:    */import java.util.NoSuchElementException;
/*  11:    */
/*  54:    */public class DoubleIterators
/*  55:    */{
/*  56:    */  public static class EmptyIterator
/*  57:    */    extends AbstractDoubleListIterator
/*  58:    */    implements Serializable, Cloneable
/*  59:    */  {
/*  60:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  61:    */    
/*  62: 62 */    public boolean hasNext() { return false; }
/*  63: 63 */    public boolean hasPrevious() { return false; }
/*  64: 64 */    public double nextDouble() { throw new NoSuchElementException(); }
/*  65: 65 */    public double previousDouble() { throw new NoSuchElementException(); }
/*  66: 66 */    public int nextIndex() { return 0; }
/*  67: 67 */    public int previousIndex() { return -1; }
/*  68: 68 */    public int skip(int n) { return 0; }
/*  69: 69 */    public int back(int n) { return 0; }
/*  70: 70 */    public Object clone() { return DoubleIterators.EMPTY_ITERATOR; }
/*  71: 71 */    private Object readResolve() { return DoubleIterators.EMPTY_ITERATOR; }
/*  72:    */  }
/*  73:    */  
/*  79: 79 */  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*  80:    */  
/*  81:    */  private static class SingletonIterator extends AbstractDoubleListIterator {
/*  82:    */    private final double element;
/*  83:    */    private int curr;
/*  84:    */    
/*  85: 85 */    public SingletonIterator(double element) { this.element = element; }
/*  86:    */    
/*  87: 87 */    public boolean hasNext() { return this.curr == 0; }
/*  88: 88 */    public boolean hasPrevious() { return this.curr == 1; }
/*  89:    */    
/*  90: 90 */    public double nextDouble() { if (!hasNext()) throw new NoSuchElementException();
/*  91: 91 */      this.curr = 1;
/*  92: 92 */      return this.element;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException();
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
/* 110:    */  public static DoubleListIterator singleton(double element)
/* 111:    */  {
/* 112:112 */    return new SingletonIterator(element);
/* 113:    */  }
/* 114:    */  
/* 115:    */  private static class ArrayIterator extends AbstractDoubleListIterator {
/* 116:    */    private final double[] array;
/* 117:    */    private final int offset;
/* 118:    */    private final int length;
/* 119:    */    private int curr;
/* 120:    */    
/* 121:    */    public ArrayIterator(double[] array, int offset, int length) {
/* 122:122 */      this.array = array;
/* 123:123 */      this.offset = offset;
/* 124:124 */      this.length = length;
/* 125:    */    }
/* 126:    */    
/* 127:127 */    public boolean hasNext() { return this.curr < this.length; }
/* 128:128 */    public boolean hasPrevious() { return this.curr > 0; }
/* 129:    */    
/* 130:    */    public double nextDouble() {
/* 131:131 */      if (!hasNext()) throw new NoSuchElementException();
/* 132:132 */      return this.array[(this.offset + this.curr++)];
/* 133:    */    }
/* 134:    */    
/* 135:    */    public double previousDouble() {
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
/* 179:    */  public static DoubleListIterator wrap(double[] array, int offset, int length)
/* 180:    */  {
/* 181:181 */    DoubleArrays.ensureOffsetLength(array, offset, length);
/* 182:182 */    return new ArrayIterator(array, offset, length);
/* 183:    */  }
/* 184:    */  
/* 191:    */  public static DoubleListIterator wrap(double[] array)
/* 192:    */  {
/* 193:193 */    return new ArrayIterator(array, 0, array.length);
/* 194:    */  }
/* 195:    */  
/* 209:    */  public static int unwrap(DoubleIterator i, double[] array, int offset, int max)
/* 210:    */  {
/* 211:211 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212:212 */    if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213:213 */    int j = max;
/* 214:214 */    while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextDouble();
/* 215:215 */    return max - j - 1;
/* 216:    */  }
/* 217:    */  
/* 227:    */  public static int unwrap(DoubleIterator i, double[] array)
/* 228:    */  {
/* 229:229 */    return unwrap(i, array, 0, array.length);
/* 230:    */  }
/* 231:    */  
/* 242:    */  public static double[] unwrap(DoubleIterator i, int max)
/* 243:    */  {
/* 244:244 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245:245 */    double[] array = new double[16];
/* 246:246 */    int j = 0;
/* 247:    */    
/* 248:248 */    while ((max-- != 0) && (i.hasNext())) {
/* 249:249 */      if (j == array.length) array = DoubleArrays.grow(array, j + 1);
/* 250:250 */      array[(j++)] = i.nextDouble();
/* 251:    */    }
/* 252:    */    
/* 253:253 */    return DoubleArrays.trim(array, j);
/* 254:    */  }
/* 255:    */  
/* 265:    */  public static double[] unwrap(DoubleIterator i)
/* 266:    */  {
/* 267:267 */    return unwrap(i, 2147483647);
/* 268:    */  }
/* 269:    */  
/* 284:    */  public static int unwrap(DoubleIterator i, DoubleCollection c, int max)
/* 285:    */  {
/* 286:286 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287:287 */    int j = max;
/* 288:288 */    while ((j-- != 0) && (i.hasNext())) c.add(i.nextDouble());
/* 289:289 */    return max - j - 1;
/* 290:    */  }
/* 291:    */  
/* 303:    */  public static long unwrap(DoubleIterator i, DoubleCollection c)
/* 304:    */  {
/* 305:305 */    long n = 0L;
/* 306:306 */    while (i.hasNext()) {
/* 307:307 */      c.add(i.nextDouble());
/* 308:308 */      n += 1L;
/* 309:    */    }
/* 310:310 */    return n;
/* 311:    */  }
/* 312:    */  
/* 326:    */  public static int pour(DoubleIterator i, DoubleCollection s, int max)
/* 327:    */  {
/* 328:328 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329:329 */    int j = max;
/* 330:330 */    while ((j-- != 0) && (i.hasNext())) s.add(i.nextDouble());
/* 331:331 */    return max - j - 1;
/* 332:    */  }
/* 333:    */  
/* 345:    */  public static int pour(DoubleIterator i, DoubleCollection s)
/* 346:    */  {
/* 347:347 */    return pour(i, s, 2147483647);
/* 348:    */  }
/* 349:    */  
/* 362:    */  public static DoubleList pour(DoubleIterator i, int max)
/* 363:    */  {
/* 364:364 */    DoubleArrayList l = new DoubleArrayList();
/* 365:365 */    pour(i, l, max);
/* 366:366 */    l.trim();
/* 367:367 */    return l;
/* 368:    */  }
/* 369:    */  
/* 380:    */  public static DoubleList pour(DoubleIterator i)
/* 381:    */  {
/* 382:382 */    return pour(i, 2147483647);
/* 383:    */  }
/* 384:    */  
/* 385:    */  private static class IteratorWrapper extends AbstractDoubleIterator {
/* 386:    */    final Iterator<Double> i;
/* 387:    */    
/* 388:    */    public IteratorWrapper(Iterator<Double> i) {
/* 389:389 */      this.i = i;
/* 390:    */    }
/* 391:    */    
/* 392:392 */    public boolean hasNext() { return this.i.hasNext(); }
/* 393:393 */    public void remove() { this.i.remove(); }
/* 394:    */    
/* 395:395 */    public double nextDouble() { return ((Double)this.i.next()).doubleValue(); }
/* 396:    */  }
/* 397:    */  
/* 412:    */  public static DoubleIterator asDoubleIterator(Iterator i)
/* 413:    */  {
/* 414:414 */    if ((i instanceof DoubleIterator)) return (DoubleIterator)i;
/* 415:415 */    return new IteratorWrapper(i);
/* 416:    */  }
/* 417:    */  
/* 418:    */  private static class ListIteratorWrapper extends AbstractDoubleListIterator
/* 419:    */  {
/* 420:    */    final ListIterator<Double> i;
/* 421:    */    
/* 422:    */    public ListIteratorWrapper(ListIterator<Double> i) {
/* 423:423 */      this.i = i;
/* 424:    */    }
/* 425:    */    
/* 426:426 */    public boolean hasNext() { return this.i.hasNext(); }
/* 427:427 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 428:428 */    public int nextIndex() { return this.i.nextIndex(); }
/* 429:429 */    public int previousIndex() { return this.i.previousIndex(); }
/* 430:    */    
/* 431:431 */    public void set(double k) { this.i.set(Double.valueOf(k)); }
/* 432:    */    
/* 433:433 */    public void add(double k) { this.i.add(Double.valueOf(k)); }
/* 434:434 */    public void remove() { this.i.remove(); }
/* 435:    */    
/* 436:436 */    public double nextDouble() { return ((Double)this.i.next()).doubleValue(); }
/* 437:437 */    public double previousDouble() { return ((Double)this.i.previous()).doubleValue(); }
/* 438:    */  }
/* 439:    */  
/* 455:    */  public static DoubleListIterator asDoubleIterator(ListIterator i)
/* 456:    */  {
/* 457:457 */    if ((i instanceof DoubleListIterator)) return (DoubleListIterator)i;
/* 458:458 */    return new ListIteratorWrapper(i); }
/* 459:    */  
/* 460:    */  private static class IteratorConcatenator extends AbstractDoubleIterator { final DoubleIterator[] a;
/* 461:    */    int offset;
/* 462:462 */    int length; int lastOffset = -1;
/* 463:    */    
/* 464:464 */    public IteratorConcatenator(DoubleIterator[] a, int offset, int length) { this.a = a;
/* 465:465 */      this.offset = offset;
/* 466:466 */      this.length = length;
/* 467:467 */      advance();
/* 468:    */    }
/* 469:    */    
/* 470:470 */    private void advance() { while ((this.length != 0) && 
/* 471:471 */        (!this.a[this.offset].hasNext())) {
/* 472:472 */        this.length -= 1;
/* 473:473 */        this.offset += 1;
/* 474:    */      }
/* 475:    */    }
/* 476:    */    
/* 478:478 */    public boolean hasNext() { return this.length > 0; }
/* 479:    */    
/* 480:    */    public double nextDouble() {
/* 481:481 */      if (!hasNext()) throw new NoSuchElementException();
/* 482:482 */      double next = this.a[(this.lastOffset = this.offset)].nextDouble();
/* 483:483 */      advance();
/* 484:484 */      return next;
/* 485:    */    }
/* 486:    */    
/* 487:487 */    public void remove() { if (this.lastOffset == -1) throw new IllegalStateException();
/* 488:488 */      this.a[this.lastOffset].remove();
/* 489:    */    }
/* 490:    */    
/* 491:491 */    public int skip(int n) { this.lastOffset = -1;
/* 492:492 */      int skipped = 0;
/* 493:493 */      while ((skipped < n) && (this.length != 0)) {
/* 494:494 */        skipped += this.a[this.offset].skip(n - skipped);
/* 495:495 */        if (this.a[this.offset].hasNext()) break;
/* 496:496 */        this.length -= 1;
/* 497:497 */        this.offset += 1;
/* 498:    */      }
/* 499:499 */      return skipped;
/* 500:    */    }
/* 501:    */  }
/* 502:    */  
/* 509:    */  public static DoubleIterator concat(DoubleIterator[] a)
/* 510:    */  {
/* 511:511 */    return concat(a, 0, a.length);
/* 512:    */  }
/* 513:    */  
/* 524:    */  public static DoubleIterator concat(DoubleIterator[] a, int offset, int length)
/* 525:    */  {
/* 526:526 */    return new IteratorConcatenator(a, offset, length);
/* 527:    */  }
/* 528:    */  
/* 529:    */  public static class UnmodifiableIterator
/* 530:    */    extends AbstractDoubleIterator {
/* 531:    */    protected final DoubleIterator i;
/* 532:    */    
/* 533:533 */    public UnmodifiableIterator(DoubleIterator i) { this.i = i; }
/* 534:    */    
/* 535:535 */    public boolean hasNext() { return this.i.hasNext(); }
/* 536:536 */    public double nextDouble() { return this.i.nextDouble(); }
/* 537:537 */    public Double next() { return (Double)this.i.next(); }
/* 538:    */  }
/* 539:    */  
/* 542:    */  public static DoubleIterator unmodifiable(DoubleIterator i)
/* 543:    */  {
/* 544:544 */    return new UnmodifiableIterator(i);
/* 545:    */  }
/* 546:    */  
/* 547:    */  public static class UnmodifiableBidirectionalIterator extends AbstractDoubleBidirectionalIterator {
/* 548:    */    protected final DoubleBidirectionalIterator i;
/* 549:    */    
/* 550:550 */    public UnmodifiableBidirectionalIterator(DoubleBidirectionalIterator i) { this.i = i; }
/* 551:    */    
/* 552:552 */    public boolean hasNext() { return this.i.hasNext(); }
/* 553:553 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 554:554 */    public double nextDouble() { return this.i.nextDouble(); }
/* 555:555 */    public double previousDouble() { return this.i.previousDouble(); }
/* 556:556 */    public Double next() { return (Double)this.i.next(); }
/* 557:557 */    public Double previous() { return (Double)this.i.previous(); }
/* 558:    */  }
/* 559:    */  
/* 562:    */  public static DoubleBidirectionalIterator unmodifiable(DoubleBidirectionalIterator i)
/* 563:    */  {
/* 564:564 */    return new UnmodifiableBidirectionalIterator(i);
/* 565:    */  }
/* 566:    */  
/* 567:    */  public static class UnmodifiableListIterator extends AbstractDoubleListIterator {
/* 568:    */    protected final DoubleListIterator i;
/* 569:    */    
/* 570:570 */    public UnmodifiableListIterator(DoubleListIterator i) { this.i = i; }
/* 571:    */    
/* 572:572 */    public boolean hasNext() { return this.i.hasNext(); }
/* 573:573 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 574:574 */    public double nextDouble() { return this.i.nextDouble(); }
/* 575:575 */    public double previousDouble() { return this.i.previousDouble(); }
/* 576:576 */    public int nextIndex() { return this.i.nextIndex(); }
/* 577:577 */    public int previousIndex() { return this.i.previousIndex(); }
/* 578:578 */    public Double next() { return (Double)this.i.next(); }
/* 579:579 */    public Double previous() { return (Double)this.i.previous(); }
/* 580:    */  }
/* 581:    */  
/* 586:586 */  public static DoubleListIterator unmodifiable(DoubleListIterator i) { return new UnmodifiableListIterator(i); }
/* 587:    */  
/* 588:    */  protected static class ByteIteratorWrapper implements DoubleIterator {
/* 589:    */    final ByteIterator iterator;
/* 590:    */    
/* 591:591 */    public ByteIteratorWrapper(ByteIterator iterator) { this.iterator = iterator; }
/* 592:    */    
/* 593:593 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 594:594 */    public Double next() { return Double.valueOf(this.iterator.nextByte()); }
/* 595:595 */    public double nextDouble() { return this.iterator.nextByte(); }
/* 596:596 */    public void remove() { this.iterator.remove(); }
/* 597:597 */    public int skip(int n) { return this.iterator.skip(n); }
/* 598:    */  }
/* 599:    */  
/* 601:    */  public static DoubleIterator wrap(ByteIterator iterator)
/* 602:    */  {
/* 603:603 */    return new ByteIteratorWrapper(iterator);
/* 604:    */  }
/* 605:    */  
/* 606:    */  protected static class ShortIteratorWrapper implements DoubleIterator {
/* 607:    */    final ShortIterator iterator;
/* 608:    */    
/* 609:609 */    public ShortIteratorWrapper(ShortIterator iterator) { this.iterator = iterator; }
/* 610:    */    
/* 611:611 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 612:612 */    public Double next() { return Double.valueOf(this.iterator.nextShort()); }
/* 613:613 */    public double nextDouble() { return this.iterator.nextShort(); }
/* 614:614 */    public void remove() { this.iterator.remove(); }
/* 615:615 */    public int skip(int n) { return this.iterator.skip(n); }
/* 616:    */  }
/* 617:    */  
/* 619:    */  public static DoubleIterator wrap(ShortIterator iterator)
/* 620:    */  {
/* 621:621 */    return new ShortIteratorWrapper(iterator);
/* 622:    */  }
/* 623:    */  
/* 624:    */  protected static class IntIteratorWrapper implements DoubleIterator {
/* 625:    */    final IntIterator iterator;
/* 626:    */    
/* 627:627 */    public IntIteratorWrapper(IntIterator iterator) { this.iterator = iterator; }
/* 628:    */    
/* 629:629 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 630:630 */    public Double next() { return Double.valueOf(this.iterator.nextInt()); }
/* 631:631 */    public double nextDouble() { return this.iterator.nextInt(); }
/* 632:632 */    public void remove() { this.iterator.remove(); }
/* 633:633 */    public int skip(int n) { return this.iterator.skip(n); }
/* 634:    */  }
/* 635:    */  
/* 637:    */  public static DoubleIterator wrap(IntIterator iterator)
/* 638:    */  {
/* 639:639 */    return new IntIteratorWrapper(iterator);
/* 640:    */  }
/* 641:    */  
/* 642:    */  protected static class FloatIteratorWrapper implements DoubleIterator {
/* 643:    */    final FloatIterator iterator;
/* 644:    */    
/* 645:645 */    public FloatIteratorWrapper(FloatIterator iterator) { this.iterator = iterator; }
/* 646:    */    
/* 647:647 */    public boolean hasNext() { return this.iterator.hasNext(); }
/* 648:648 */    public Double next() { return Double.valueOf(this.iterator.nextFloat()); }
/* 649:649 */    public double nextDouble() { return this.iterator.nextFloat(); }
/* 650:650 */    public void remove() { this.iterator.remove(); }
/* 651:651 */    public int skip(int n) { return this.iterator.skip(n); }
/* 652:    */  }
/* 653:    */  
/* 655:    */  public static DoubleIterator wrap(FloatIterator iterator)
/* 656:    */  {
/* 657:657 */    return new FloatIteratorWrapper(iterator);
/* 658:    */  }
/* 659:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */