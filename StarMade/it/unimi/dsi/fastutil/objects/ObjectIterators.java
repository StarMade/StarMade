/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.ListIterator;
/*   6:    */import java.util.NoSuchElementException;
/*   7:    */
/*  53:    */public class ObjectIterators
/*  54:    */{
/*  55:    */  public static class EmptyIterator<K>
/*  56:    */    extends AbstractObjectListIterator<K>
/*  57:    */    implements Serializable, Cloneable
/*  58:    */  {
/*  59:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  60:    */    
/*  61: 61 */    public boolean hasNext() { return false; }
/*  62: 62 */    public boolean hasPrevious() { return false; }
/*  63: 63 */    public K next() { throw new NoSuchElementException(); }
/*  64: 64 */    public K previous() { throw new NoSuchElementException(); }
/*  65: 65 */    public int nextIndex() { return 0; }
/*  66: 66 */    public int previousIndex() { return -1; }
/*  67: 67 */    public int skip(int n) { return 0; }
/*  68: 68 */    public int back(int n) { return 0; }
/*  69: 69 */    public Object clone() { return ObjectIterators.EMPTY_ITERATOR; }
/*  70: 70 */    private Object readResolve() { return ObjectIterators.EMPTY_ITERATOR; }
/*  71:    */  }
/*  72:    */  
/*  78: 78 */  public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();
/*  79:    */  
/*  80:    */  private static class SingletonIterator<K> extends AbstractObjectListIterator<K> {
/*  81:    */    private final K element;
/*  82:    */    private int curr;
/*  83:    */    
/*  84: 84 */    public SingletonIterator(K element) { this.element = element; }
/*  85:    */    
/*  86: 86 */    public boolean hasNext() { return this.curr == 0; }
/*  87: 87 */    public boolean hasPrevious() { return this.curr == 1; }
/*  88:    */    
/*  89: 89 */    public K next() { if (!hasNext()) throw new NoSuchElementException();
/*  90: 90 */      this.curr = 1;
/*  91: 91 */      return this.element;
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    public K previous() { if (!hasPrevious()) throw new NoSuchElementException();
/*  95: 95 */      this.curr = 0;
/*  96: 96 */      return this.element;
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    public int nextIndex() { return this.curr; }
/* 100:    */    
/* 101:    */    public int previousIndex() {
/* 102:102 */      return this.curr - 1;
/* 103:    */    }
/* 104:    */  }
/* 105:    */  
/* 109:    */  public static <K> ObjectListIterator<K> singleton(K element)
/* 110:    */  {
/* 111:111 */    return new SingletonIterator(element);
/* 112:    */  }
/* 113:    */  
/* 114:    */  private static class ArrayIterator<K> extends AbstractObjectListIterator<K>
/* 115:    */  {
/* 116:    */    private final K[] array;
/* 117:    */    private final int offset;
/* 118:    */    private final int length;
/* 119:    */    private int curr;
/* 120:    */    
/* 121:    */    public ArrayIterator(K[] array, int offset, int length) {
/* 122:122 */      this.array = array;
/* 123:123 */      this.offset = offset;
/* 124:124 */      this.length = length;
/* 125:    */    }
/* 126:    */    
/* 127:127 */    public boolean hasNext() { return this.curr < this.length; }
/* 128:128 */    public boolean hasPrevious() { return this.curr > 0; }
/* 129:    */    
/* 130:    */    public K next() {
/* 131:131 */      if (!hasNext()) throw new NoSuchElementException();
/* 132:132 */      return this.array[(this.offset + this.curr++)];
/* 133:    */    }
/* 134:    */    
/* 135:    */    public K previous() {
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
/* 179:    */  public static <K> ObjectListIterator<K> wrap(K[] array, int offset, int length)
/* 180:    */  {
/* 181:181 */    ObjectArrays.ensureOffsetLength(array, offset, length);
/* 182:182 */    return new ArrayIterator(array, offset, length);
/* 183:    */  }
/* 184:    */  
/* 191:    */  public static <K> ObjectListIterator<K> wrap(K[] array)
/* 192:    */  {
/* 193:193 */    return new ArrayIterator(array, 0, array.length);
/* 194:    */  }
/* 195:    */  
/* 209:    */  public static <K> int unwrap(Iterator<? extends K> i, K[] array, int offset, int max)
/* 210:    */  {
/* 211:211 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 212:212 */    if ((offset < 0) || (offset + max > array.length)) throw new IllegalArgumentException();
/* 213:213 */    int j = max;
/* 214:214 */    while ((j-- != 0) && (i.hasNext())) array[(offset++)] = i.next();
/* 215:215 */    return max - j - 1;
/* 216:    */  }
/* 217:    */  
/* 227:    */  public static <K> int unwrap(Iterator<? extends K> i, K[] array)
/* 228:    */  {
/* 229:229 */    return unwrap(i, array, 0, array.length);
/* 230:    */  }
/* 231:    */  
/* 242:    */  public static <K> K[] unwrap(Iterator<? extends K> i, int max)
/* 243:    */  {
/* 244:244 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 245:245 */    K[] array = (Object[])new Object[16];
/* 246:246 */    int j = 0;
/* 247:    */    
/* 248:248 */    while ((max-- != 0) && (i.hasNext())) {
/* 249:249 */      if (j == array.length) array = ObjectArrays.grow(array, j + 1);
/* 250:250 */      array[(j++)] = i.next();
/* 251:    */    }
/* 252:    */    
/* 253:253 */    return ObjectArrays.trim(array, j);
/* 254:    */  }
/* 255:    */  
/* 265:    */  public static <K> K[] unwrap(Iterator<? extends K> i)
/* 266:    */  {
/* 267:267 */    return unwrap(i, 2147483647);
/* 268:    */  }
/* 269:    */  
/* 284:    */  public static <K> int unwrap(Iterator<K> i, ObjectCollection<? super K> c, int max)
/* 285:    */  {
/* 286:286 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 287:287 */    int j = max;
/* 288:288 */    while ((j-- != 0) && (i.hasNext())) c.add(i.next());
/* 289:289 */    return max - j - 1;
/* 290:    */  }
/* 291:    */  
/* 303:    */  public static <K> long unwrap(Iterator<K> i, ObjectCollection<? super K> c)
/* 304:    */  {
/* 305:305 */    long n = 0L;
/* 306:306 */    while (i.hasNext()) {
/* 307:307 */      c.add(i.next());
/* 308:308 */      n += 1L;
/* 309:    */    }
/* 310:310 */    return n;
/* 311:    */  }
/* 312:    */  
/* 326:    */  public static <K> int pour(Iterator<K> i, ObjectCollection<? super K> s, int max)
/* 327:    */  {
/* 328:328 */    if (max < 0) throw new IllegalArgumentException("The maximum number of elements (" + max + ") is negative");
/* 329:329 */    int j = max;
/* 330:330 */    while ((j-- != 0) && (i.hasNext())) s.add(i.next());
/* 331:331 */    return max - j - 1;
/* 332:    */  }
/* 333:    */  
/* 345:    */  public static <K> int pour(Iterator<K> i, ObjectCollection<? super K> s)
/* 346:    */  {
/* 347:347 */    return pour(i, s, 2147483647);
/* 348:    */  }
/* 349:    */  
/* 362:    */  public static <K> ObjectList<K> pour(Iterator<K> i, int max)
/* 363:    */  {
/* 364:364 */    ObjectArrayList<K> l = new ObjectArrayList();
/* 365:365 */    pour(i, l, max);
/* 366:366 */    l.trim();
/* 367:367 */    return l;
/* 368:    */  }
/* 369:    */  
/* 380:    */  public static <K> ObjectList<K> pour(Iterator<K> i)
/* 381:    */  {
/* 382:382 */    return pour(i, 2147483647);
/* 383:    */  }
/* 384:    */  
/* 385:    */  private static class IteratorWrapper<K> extends AbstractObjectIterator<K> {
/* 386:    */    final Iterator<K> i;
/* 387:    */    
/* 388:    */    public IteratorWrapper(Iterator<K> i) {
/* 389:389 */      this.i = i;
/* 390:    */    }
/* 391:    */    
/* 392:392 */    public boolean hasNext() { return this.i.hasNext(); }
/* 393:393 */    public void remove() { this.i.remove(); }
/* 394:    */    
/* 395:395 */    public K next() { return this.i.next(); }
/* 396:    */  }
/* 397:    */  
/* 412:    */  public static <K> ObjectIterator<K> asObjectIterator(Iterator<K> i)
/* 413:    */  {
/* 414:414 */    if ((i instanceof ObjectIterator)) return (ObjectIterator)i;
/* 415:415 */    return new IteratorWrapper(i);
/* 416:    */  }
/* 417:    */  
/* 418:    */  private static class ListIteratorWrapper<K> extends AbstractObjectListIterator<K>
/* 419:    */  {
/* 420:    */    final ListIterator<K> i;
/* 421:    */    
/* 422:    */    public ListIteratorWrapper(ListIterator<K> i) {
/* 423:423 */      this.i = i;
/* 424:    */    }
/* 425:    */    
/* 426:426 */    public boolean hasNext() { return this.i.hasNext(); }
/* 427:427 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 428:428 */    public int nextIndex() { return this.i.nextIndex(); }
/* 429:429 */    public int previousIndex() { return this.i.previousIndex(); }
/* 430:    */    
/* 431:431 */    public void set(K k) { this.i.set(k); }
/* 432:    */    
/* 433:433 */    public void add(K k) { this.i.add(k); }
/* 434:434 */    public void remove() { this.i.remove(); }
/* 435:    */    
/* 436:436 */    public K next() { return this.i.next(); }
/* 437:437 */    public K previous() { return this.i.previous(); }
/* 438:    */  }
/* 439:    */  
/* 455:    */  public static <K> ObjectListIterator<K> asObjectIterator(ListIterator<K> i)
/* 456:    */  {
/* 457:457 */    if ((i instanceof ObjectListIterator)) return (ObjectListIterator)i;
/* 458:458 */    return new ListIteratorWrapper(i); }
/* 459:    */  
/* 460:    */  private static class IteratorConcatenator<K> extends AbstractObjectIterator<K> { final ObjectIterator<? extends K>[] a;
/* 461:    */    int offset;
/* 462:462 */    int length; int lastOffset = -1;
/* 463:    */    
/* 464:464 */    public IteratorConcatenator(ObjectIterator<? extends K>[] a, int offset, int length) { this.a = a;
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
/* 480:    */    public K next() {
/* 481:481 */      if (!hasNext()) throw new NoSuchElementException();
/* 482:482 */      K next = this.a[(this.lastOffset = this.offset)].next();
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
/* 509:    */  public static <K> ObjectIterator<K> concat(ObjectIterator<? extends K>[] a)
/* 510:    */  {
/* 511:511 */    return concat(a, 0, a.length);
/* 512:    */  }
/* 513:    */  
/* 524:    */  public static <K> ObjectIterator<K> concat(ObjectIterator<? extends K>[] a, int offset, int length)
/* 525:    */  {
/* 526:526 */    return new IteratorConcatenator(a, offset, length);
/* 527:    */  }
/* 528:    */  
/* 529:    */  public static class UnmodifiableIterator<K>
/* 530:    */    extends AbstractObjectIterator<K> {
/* 531:    */    protected final ObjectIterator<K> i;
/* 532:    */    
/* 533:533 */    public UnmodifiableIterator(ObjectIterator<K> i) { this.i = i; }
/* 534:    */    
/* 535:535 */    public boolean hasNext() { return this.i.hasNext(); }
/* 536:536 */    public K next() { return this.i.next(); }
/* 537:    */  }
/* 538:    */  
/* 541:    */  public static <K> ObjectIterator<K> unmodifiable(ObjectIterator<K> i)
/* 542:    */  {
/* 543:543 */    return new UnmodifiableIterator(i);
/* 544:    */  }
/* 545:    */  
/* 546:    */  public static class UnmodifiableBidirectionalIterator<K> extends AbstractObjectBidirectionalIterator<K> {
/* 547:    */    protected final ObjectBidirectionalIterator<K> i;
/* 548:    */    
/* 549:549 */    public UnmodifiableBidirectionalIterator(ObjectBidirectionalIterator<K> i) { this.i = i; }
/* 550:    */    
/* 551:551 */    public boolean hasNext() { return this.i.hasNext(); }
/* 552:552 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 553:553 */    public K next() { return this.i.next(); }
/* 554:554 */    public K previous() { return this.i.previous(); }
/* 555:    */  }
/* 556:    */  
/* 559:    */  public static <K> ObjectBidirectionalIterator<K> unmodifiable(ObjectBidirectionalIterator<K> i)
/* 560:    */  {
/* 561:561 */    return new UnmodifiableBidirectionalIterator(i);
/* 562:    */  }
/* 563:    */  
/* 564:    */  public static class UnmodifiableListIterator<K> extends AbstractObjectListIterator<K> {
/* 565:    */    protected final ObjectListIterator<K> i;
/* 566:    */    
/* 567:567 */    public UnmodifiableListIterator(ObjectListIterator<K> i) { this.i = i; }
/* 568:    */    
/* 569:569 */    public boolean hasNext() { return this.i.hasNext(); }
/* 570:570 */    public boolean hasPrevious() { return this.i.hasPrevious(); }
/* 571:571 */    public K next() { return this.i.next(); }
/* 572:572 */    public K previous() { return this.i.previous(); }
/* 573:573 */    public int nextIndex() { return this.i.nextIndex(); }
/* 574:574 */    public int previousIndex() { return this.i.previousIndex(); }
/* 575:    */  }
/* 576:    */  
/* 579:    */  public static <K> ObjectListIterator<K> unmodifiable(ObjectListIterator<K> i)
/* 580:    */  {
/* 581:581 */    return new UnmodifiableListIterator(i);
/* 582:    */  }
/* 583:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectIterators
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */