/*   1:    */package it.unimi.dsi.fastutil.booleans;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.ListIterator;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */
/*  54:    */public class BooleanIterators
/*  55:    */{
/*  56:    */  public static class EmptyIterator
/*  57:    */    extends AbstractBooleanListIterator
/*  58:    */    implements Serializable, Cloneable
/*  59:    */  {
/*  60:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  61:    */    
/*  62: 62 */    public boolean hasNext() { return false; }
/*  63: 63 */    public boolean hasPrevious() { return false; }
/*  64: 64 */    public boolean nextBoolean() { throw new NoSuchElementException(); }
/*  65: 65 */    public boolean previousBoolean() { throw new NoSuchElementException(); }
/*  66: 66 */    public int nextIndex() { return 0; }
/*  67: 67 */    public int previousIndex() { return -1; }
/*  68: 68 */    public int skip(int n) { return 0; }
/*  69: 69 */    public int back(int n) { return 0; }
/*  70: 70 */    public Object clone() { return BooleanIterators.EMPTY_ITERATOR; }
/*  71: 71 */    private Object readResolve() { return BooleanIterators.EMPTY_ITERATOR; }
/*  72:    */  }
/*  73:    */  
/*  79: 79 */  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*  80:    */  
/*  81:    */  private static class SingletonIterator extends AbstractBooleanListIterator {
/*  82:    */    private final boolean element;
/*  83:    */    private int curr;
/*  84:    */    
/*  85: 85 */    public SingletonIterator(boolean element) { this.element = element; }
/*  86:    */    
/*  87: 87 */    public boolean hasNext() { return this.curr == 0; }
/*  88: 88 */    public boolean hasPrevious() { return this.curr == 1; }
/*  89:    */    
/*  90: 90 */    public boolean nextBoolean() { if (!hasNext()) throw new NoSuchElementException();
/*  91: 91 */      this.curr = 1;
/*  92: 92 */      return this.element;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    public boolean previousBoolean() { if (!hasPrevious()) throw new NoSuchElementException();
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
/* 110:    */  public static BooleanListIterator singleton(boolean element)
/* 111:    */  {
/* 112:112 */    return new SingletonIterator(element);
/* 113:    */  }
/* 114:    */  
/* 115:    */  private static class ArrayIterator extends AbstractBooleanListIterator {
/* 116:    */    private final boolean[] array;
/* 117:    */    private final int offset;
/* 118:    */    private final int length;
/* 119:    */    private int curr;
/* 120:    */    
/* 121:    */    public ArrayIterator(boolean[] array, int offset, int length) {
/* 122:122 */      this.array = array;
/* 123:123 */      this.offset = offset;
/* 124:124 */      this.length = length;
/* 125:    */    }
/* 126:    */    
/* 127:127 */    public boolean hasNext() { return this.curr < this.length; }
/* 128:128 */    public boolean hasPrevious() { return this.curr > 0; }
/* 129:    */    
/* 130:    */    public boolean nextBoolean() {
/* 131:131 */      if (!hasNext()) throw new NoSuchElementException();
/* 132:132 */      return this.array[(this.offset + this.curr++)];
/* 133:    */    }
/* 134:    */    
/* 135:    */    public boolean previousBoolean() {
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
/* 179:    */  public static BooleanListIterator wrap(boolean[] array, int offset, int length)
/* 180:    */  {
/* 181:181 */    BooleanArrays.ensureOffsetLength(array, offset, length);
/* 182:182 */    return new ArrayIterator(array, offset, length);
/* 183:    */  }
/* 184:    */  
/* 191:    */  public static BooleanListIterator wrap(boolean[] array)
/* 192:    */  {
/* 193:193 */    return new ArrayIterator(array, 0, array.length);
/* 194:    */  }
/* 195:    */  
/* 209:    */  public static int unwrap(BooleanIterator i, boolean[] array, int offset, int max)
/* 210:    */  {
/* 211:211 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212:212 */    if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213:213 */    int j = max;
/* 214:214 */    while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.nextBoolean();
/* 215:215 */    return max - j - 1;
/* 216:    */  }
/* 217:    */  
/* 227:    */  public static int unwrap(BooleanIterator i, boolean[] array)
/* 228:    */  {
/* 229:229 */    return unwrap(i, array, 0, array.length);
/* 230:    */  }
/* 231:    */  
/* 242:    */  public static boolean[] unwrap(BooleanIterator i, int max)
/* 243:    */  {
/* 244:244 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245:245 */    boolean[] array = new boolean[16];
/* 246:246 */    int j = 0;
/* 247:    */    
/* 248:248 */    while ((max-- != 0) && (i.hasNext())) {
/* 249:249 */      if (j == array.length) array = BooleanArrays.grow(array, j + 1);
/* 250:250 */      array[(j++)] = i.nextBoolean();
/* 251:    */    }
/* 252:    */    
/* 253:253 */    return BooleanArrays.trim(array, j);
/* 254:    */  }
/* 255:    */  
/* 265:    */  public static boolean[] unwrap(BooleanIterator i)
/* 266:    */  {
/* 267:267 */    return unwrap(i, 2147483647);
/* 268:    */  }
/* 269:    */  
/* 284:    */  public static int unwrap(BooleanIterator i, BooleanCollection c, int max)
/* 285:    */  {
/* 286:286 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287:287 */    int j = max;
/* 288:288 */    while ((j-- != 0) && (i.hasNext())) c.add(i.nextBoolean());
/* 289:289 */    return max - j - 1;
/* 290:    */  }
/* 291:    */  
/* 303:    */  public static long unwrap(BooleanIterator i, BooleanCollection c)
/* 304:    */  {
/* 305:305 */    long n = 0L;
/* 306:306 */    while (i.hasNext()) {
/* 307:307 */      c.add(i.nextBoolean());
/* 308:308 */      n += 1L;
/* 309:    */    }
/* 310:310 */    return n;
/* 311:    */  }
/* 312:    */  
/* 326:    */  public static int pour(BooleanIterator i, BooleanCollection s, int max)
/* 327:    */  {
/* 328:328 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329:329 */    int j = max;
/* 330:330 */    while ((j-- != 0) && (i.hasNext())) s.add(i.nextBoolean());
/* 331:331 */    return max - j - 1;
/* 332:    */  }
/* 333:    */  
/* 345:    */  public static int pour(BooleanIterator i, BooleanCollection s)
/* 346:    */  {
/* 347:347 */    return pour(i, s, 2147483647);
/* 348:    */  }
/* 349:    */  
/* 362:    */  public static BooleanList pour(BooleanIterator i, int max)
/* 363:    */  {
/* 364:364 */    BooleanArrayList l = new BooleanArrayList();
/* 365:365 */    pour(i, l, max);
/* 366:366 */    l.trim();
/* 367:367 */    return l;
/* 368:    */  }
/* 369:    */  
/* 380:    */  public static BooleanList pour(BooleanIterator i)
/* 381:    */  {
/* 382:382 */    return pour(i, 2147483647);
/* 383:    */  }
/* 384:    */  
/* 385:    */  private static class IteratorWrapper extends AbstractBooleanIterator {
/* 386:    */    final Iterator<Boolean> i;
/* 387:    */    
/* 388:    */    public IteratorWrapper(Iterator<Boolean> i) {
/* 389:389 */      this.i = i;
/* 390:    */    }
/* 391:    */    
/* 392:392 */    public boolean hasNext() { return this.i.hasNext(); }
/* 393:393 */    public void remove() { this.i.remove(); }
/* 394:    */    
/* 395:395 */    public boolean nextBoolean() { return ((Boolean)this.i.next()).booleanValue(); }
/* 396:    */  }
/* 397:    */  
/* 412:    */  public static BooleanIterator asBooleanIterator(Iterator i)
/* 413:    */  {
/* 414:414 */    if ((i instanceof BooleanIterator)) return (BooleanIterator)i;
/* 415:415 */    return new IteratorWrapper(i);
/* 416:    */  }
/* 417:    */  
/* 418:    */  private static class ListIteratorWrapper extends AbstractBooleanListIterator
/* 419:    */  {
/* 420:    */    final ListIterator<Boolean> i;
/* 421:    */    
/* 422:    */    public ListIteratorWrapper(ListIterator<Boolean> i) {
/* 423:423 */      this.i = i;
/* 424:    */    }
/* 425:    */    
/* 426:426 */    public boolean hasNext() { return this.i.hasNext(); }
/* 427:427 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 428:428 */    public int nextIndex() { return this.i.nextIndex(); }
/* 429:429 */    public int previousIndex() { return this.i.previousIndex(); }
/* 430:    */    
/* 431:431 */    public void set(boolean k) { this.i.set(Boolean.valueOf(k)); }
/* 432:    */    
/* 433:433 */    public void add(boolean k) { this.i.add(Boolean.valueOf(k)); }
/* 434:434 */    public void remove() { this.i.remove(); }
/* 435:    */    
/* 436:436 */    public boolean nextBoolean() { return ((Boolean)this.i.next()).booleanValue(); }
/* 437:437 */    public boolean previousBoolean() { return ((Boolean)this.i.previous()).booleanValue(); }
/* 438:    */  }
/* 439:    */  
/* 455:    */  public static BooleanListIterator asBooleanIterator(ListIterator i)
/* 456:    */  {
/* 457:457 */    if ((i instanceof BooleanListIterator)) return (BooleanListIterator)i;
/* 458:458 */    return new ListIteratorWrapper(i); }
/* 459:    */  
/* 460:    */  private static class IteratorConcatenator extends AbstractBooleanIterator { final BooleanIterator[] a;
/* 461:    */    int offset;
/* 462:462 */    int length; int lastOffset = -1;
/* 463:    */    
/* 464:464 */    public IteratorConcatenator(BooleanIterator[] a, int offset, int length) { this.a = a;
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
/* 480:    */    public boolean nextBoolean() {
/* 481:481 */      if (!hasNext()) throw new NoSuchElementException();
/* 482:482 */      boolean next = this.a[(this.lastOffset = this.offset)].nextBoolean();
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
/* 509:    */  public static BooleanIterator concat(BooleanIterator[] a)
/* 510:    */  {
/* 511:511 */    return concat(a, 0, a.length);
/* 512:    */  }
/* 513:    */  
/* 524:    */  public static BooleanIterator concat(BooleanIterator[] a, int offset, int length)
/* 525:    */  {
/* 526:526 */    return new IteratorConcatenator(a, offset, length);
/* 527:    */  }
/* 528:    */  
/* 529:    */  public static class UnmodifiableIterator
/* 530:    */    extends AbstractBooleanIterator {
/* 531:    */    protected final BooleanIterator i;
/* 532:    */    
/* 533:533 */    public UnmodifiableIterator(BooleanIterator i) { this.i = i; }
/* 534:    */    
/* 535:535 */    public boolean hasNext() { return this.i.hasNext(); }
/* 536:536 */    public boolean nextBoolean() { return this.i.nextBoolean(); }
/* 537:537 */    public Boolean next() { return (Boolean)this.i.next(); }
/* 538:    */  }
/* 539:    */  
/* 542:    */  public static BooleanIterator unmodifiable(BooleanIterator i)
/* 543:    */  {
/* 544:544 */    return new UnmodifiableIterator(i);
/* 545:    */  }
/* 546:    */  
/* 547:    */  public static class UnmodifiableBidirectionalIterator extends AbstractBooleanBidirectionalIterator {
/* 548:    */    protected final BooleanBidirectionalIterator i;
/* 549:    */    
/* 550:550 */    public UnmodifiableBidirectionalIterator(BooleanBidirectionalIterator i) { this.i = i; }
/* 551:    */    
/* 552:552 */    public boolean hasNext() { return this.i.hasNext(); }
/* 553:553 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 554:554 */    public boolean nextBoolean() { return this.i.nextBoolean(); }
/* 555:555 */    public boolean previousBoolean() { return this.i.previousBoolean(); }
/* 556:556 */    public Boolean next() { return (Boolean)this.i.next(); }
/* 557:557 */    public Boolean previous() { return (Boolean)this.i.previous(); }
/* 558:    */  }
/* 559:    */  
/* 562:    */  public static BooleanBidirectionalIterator unmodifiable(BooleanBidirectionalIterator i)
/* 563:    */  {
/* 564:564 */    return new UnmodifiableBidirectionalIterator(i);
/* 565:    */  }
/* 566:    */  
/* 567:    */  public static class UnmodifiableListIterator extends AbstractBooleanListIterator {
/* 568:    */    protected final BooleanListIterator i;
/* 569:    */    
/* 570:570 */    public UnmodifiableListIterator(BooleanListIterator i) { this.i = i; }
/* 571:    */    
/* 572:572 */    public boolean hasNext() { return this.i.hasNext(); }
/* 573:573 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 574:574 */    public boolean nextBoolean() { return this.i.nextBoolean(); }
/* 575:575 */    public boolean previousBoolean() { return this.i.previousBoolean(); }
/* 576:576 */    public int nextIndex() { return this.i.nextIndex(); }
/* 577:577 */    public int previousIndex() { return this.i.previousIndex(); }
/* 578:578 */    public Boolean next() { return (Boolean)this.i.next(); }
/* 579:579 */    public Boolean previous() { return (Boolean)this.i.previous(); }
/* 580:    */  }
/* 581:    */  
/* 584:    */  public static BooleanListIterator unmodifiable(BooleanListIterator i)
/* 585:    */  {
/* 586:586 */    return new UnmodifiableListIterator(i);
/* 587:    */  }
/* 588:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */