/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigList;
/*   4:    */import it.unimi.dsi.fastutil.BigListIterator;
/*   5:    */import it.unimi.dsi.fastutil.HashCommon;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  53:    */public abstract class AbstractDoubleBigList
/*  54:    */  extends AbstractDoubleCollection
/*  55:    */  implements DoubleBigList, DoubleStack
/*  56:    */{
/*  57:    */  protected void ensureIndex(long index)
/*  58:    */  {
/*  59: 59 */    if (index < 0L) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  60: 60 */    if (index > size64()) { throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than list size (").append(size64()).append(")").toString());
/*  61:    */    }
/*  62:    */  }
/*  63:    */  
/*  66:    */  protected void ensureRestrictedIndex(long index)
/*  67:    */  {
/*  68: 68 */    if (index < 0L) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  69: 69 */    if (index >= size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than or equal to list size (").append(size64()).append(")").toString());
/*  70:    */  }
/*  71:    */  
/*  72: 72 */  public void add(long index, double k) { throw new UnsupportedOperationException(); }
/*  73:    */  
/*  74:    */  public boolean add(double k) {
/*  75: 75 */    add(size64(), k);
/*  76: 76 */    return true;
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public double removeDouble(long i) { throw new UnsupportedOperationException(); }
/*  80:    */  
/*  81:    */  public double removeDouble(int i) {
/*  82: 82 */    return removeDouble(i);
/*  83:    */  }
/*  84:    */  
/*  85: 85 */  public double set(long index, double k) { throw new UnsupportedOperationException(); }
/*  86:    */  
/*  88: 88 */  public double set(int index, double k) { return set(index, k); }
/*  89:    */  
/*  90:    */  public boolean addAll(long index, Collection<? extends Double> c) {
/*  91: 91 */    ensureIndex(index);
/*  92: 92 */    int n = c.size();
/*  93: 93 */    if (n == 0) return false;
/*  94: 94 */    Iterator<? extends Double> i = c.iterator();
/*  95: 95 */    while (n-- != 0) add(index++, (Double)i.next());
/*  96: 96 */    return true;
/*  97:    */  }
/*  98:    */  
/*  99: 99 */  public boolean addAll(int index, Collection<? extends Double> c) { return addAll(index, c); }
/* 100:    */  
/* 101:    */  public boolean addAll(Collection<? extends Double> c)
/* 102:    */  {
/* 103:103 */    return addAll(size64(), c);
/* 104:    */  }
/* 105:    */  
/* 106:106 */  public DoubleBigListIterator iterator() { return listIterator(); }
/* 107:    */  
/* 108:    */  public DoubleBigListIterator listIterator() {
/* 109:109 */    return listIterator(0L);
/* 110:    */  }
/* 111:    */  
/* 112:112 */  public DoubleBigListIterator listIterator(final long index) { new AbstractDoubleBigListIterator() {
/* 113:113 */      long last = -1L; long pos = index;
/* 114:114 */      public boolean hasNext() { return this.pos < AbstractDoubleBigList.this.size64(); }
/* 115:115 */      public boolean hasPrevious() { return this.pos > 0L; }
/* 116:116 */      public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return AbstractDoubleBigList.this.getDouble(this.last = this.pos++); }
/* 117:117 */      public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractDoubleBigList.this.getDouble(this.last = --this.pos); }
/* 118:118 */      public long nextIndex() { return this.pos; }
/* 119:119 */      public long previousIndex() { return this.pos - 1L; }
/* 120:    */      
/* 121:121 */      public void add(double k) { if (this.last == -1L) throw new IllegalStateException();
/* 122:122 */        AbstractDoubleBigList.this.add(this.pos++, k);
/* 123:123 */        this.last = -1L;
/* 124:    */      }
/* 125:    */      
/* 126:126 */      public void set(double k) { if (this.last == -1L) throw new IllegalStateException();
/* 127:127 */        AbstractDoubleBigList.this.set(this.last, k);
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 131:131 */        AbstractDoubleBigList.this.removeDouble(this.last);
/* 132:    */        
/* 133:133 */        if (this.last < this.pos) this.pos -= 1L;
/* 134:134 */        this.last = -1L;
/* 135:    */      }
/* 136:    */    }; }
/* 137:    */  
/* 139:    */  public DoubleBigListIterator listIterator(int index)
/* 140:    */  {
/* 141:141 */    return listIterator(index);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public boolean contains(double k)
/* 145:    */  {
/* 146:146 */    return indexOf(k) >= 0L;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public long indexOf(double k) {
/* 150:150 */    DoubleBigListIterator i = listIterator();
/* 151:    */    
/* 152:152 */    while (i.hasNext()) {
/* 153:153 */      double e = i.nextDouble();
/* 154:154 */      if (k == e) return i.previousIndex();
/* 155:    */    }
/* 156:156 */    return -1L;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public long lastIndexOf(double k) {
/* 160:160 */    DoubleBigListIterator i = listIterator(size64());
/* 161:    */    
/* 162:162 */    while (i.hasPrevious()) {
/* 163:163 */      double e = i.previousDouble();
/* 164:164 */      if (k == e) return i.nextIndex();
/* 165:    */    }
/* 166:166 */    return -1L;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void size(long size) {
/* 170:170 */    long i = size64();
/* 171:171 */    for (size <= i; i++ < size; add(0.0D)) {}
/* 172:172 */    while (i-- != size) remove(i);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void size(int size) {
/* 176:176 */    size(size);
/* 177:    */  }
/* 178:    */  
/* 179:    */  public DoubleBigList subList(long from, long to) {
/* 180:180 */    ensureIndex(from);
/* 181:181 */    ensureIndex(to);
/* 182:182 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 183:    */    }
/* 184:184 */    return new DoubleSubList(this, from, to);
/* 185:    */  }
/* 186:    */  
/* 195:    */  public void removeElements(long from, long to)
/* 196:    */  {
/* 197:197 */    ensureIndex(to);
/* 198:198 */    DoubleBigListIterator i = listIterator(from);
/* 199:199 */    long n = to - from;
/* 200:200 */    if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201:201 */    while (n-- != 0L) {
/* 202:202 */      i.nextDouble();
/* 203:203 */      i.remove();
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 217:    */  public void addElements(long index, double[][] a, long offset, long length)
/* 218:    */  {
/* 219:219 */    ensureIndex(index);
/* 220:220 */    DoubleBigArrays.ensureOffsetLength(a, offset, length);
/* 221:221 */    while (length-- != 0L) add(index++, DoubleBigArrays.get(a, offset++));
/* 222:    */  }
/* 223:    */  
/* 224:    */  public void addElements(long index, double[][] a) {
/* 225:225 */    addElements(index, a, 0L, DoubleBigArrays.length(a));
/* 226:    */  }
/* 227:    */  
/* 238:    */  public void getElements(long from, double[][] a, long offset, long length)
/* 239:    */  {
/* 240:240 */    DoubleBigListIterator i = listIterator(from);
/* 241:241 */    DoubleBigArrays.ensureOffsetLength(a, offset, length);
/* 242:242 */    if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243:243 */    while (length-- != 0L) DoubleBigArrays.set(a, offset++, i.nextDouble());
/* 244:    */  }
/* 245:    */  
/* 246:    */  @Deprecated
/* 247:    */  public int size() {
/* 248:248 */    return (int)Math.min(2147483647L, size64());
/* 249:    */  }
/* 250:    */  
/* 251:    */  private boolean valEquals(Object a, Object b)
/* 252:    */  {
/* 253:253 */    return a == null ? false : b == null ? true : a.equals(b);
/* 254:    */  }
/* 255:    */  
/* 257:    */  public boolean equals(Object o)
/* 258:    */  {
/* 259:259 */    if (o == this) return true;
/* 260:260 */    if (!(o instanceof BigList)) return false;
/* 261:261 */    BigList<?> l = (BigList)o;
/* 262:262 */    long s = size64();
/* 263:263 */    if (s != l.size64()) { return false;
/* 264:    */    }
/* 265:265 */    BigListIterator<?> i1 = listIterator();BigListIterator<?> i2 = l.listIterator();
/* 266:    */    
/* 270:270 */    while (s-- != 0L) if (!valEquals(i1.next(), i2.next())) { return false;
/* 271:    */      }
/* 272:272 */    return true;
/* 273:    */  }
/* 274:    */  
/* 287:    */  public int compareTo(BigList<? extends Double> l)
/* 288:    */  {
/* 289:289 */    if (l == this) { return 0;
/* 290:    */    }
/* 291:291 */    if ((l instanceof DoubleBigList))
/* 292:    */    {
/* 293:293 */      DoubleBigListIterator i1 = listIterator();DoubleBigListIterator i2 = ((DoubleBigList)l).listIterator();
/* 294:    */      
/* 297:297 */      while ((i1.hasNext()) && (i2.hasNext())) {
/* 298:298 */        double e1 = i1.nextDouble();
/* 299:299 */        double e2 = i2.nextDouble();
/* 300:300 */        int r; if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/* 301:    */      }
/* 302:302 */      return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 303:    */    }
/* 304:    */    
/* 305:305 */    BigListIterator<? extends Double> i1 = listIterator();BigListIterator<? extends Double> i2 = l.listIterator();
/* 306:    */    
/* 308:308 */    while ((i1.hasNext()) && (i2.hasNext())) { int r;
/* 309:309 */      if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/* 310:    */    }
/* 311:311 */    return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 312:    */  }
/* 313:    */  
/* 318:    */  public int hashCode()
/* 319:    */  {
/* 320:320 */    DoubleIterator i = iterator();
/* 321:321 */    int h = 1;
/* 322:322 */    long s = size64();
/* 323:323 */    while (s-- != 0L) {
/* 324:324 */      double k = i.nextDouble();
/* 325:325 */      h = 31 * h + HashCommon.double2int(k);
/* 326:    */    }
/* 327:327 */    return h;
/* 328:    */  }
/* 329:    */  
/* 330:    */  public void push(double o) {
/* 331:331 */    add(o);
/* 332:    */  }
/* 333:    */  
/* 334:    */  public double popDouble() {
/* 335:335 */    if (isEmpty()) throw new NoSuchElementException();
/* 336:336 */    return removeDouble(size64() - 1L);
/* 337:    */  }
/* 338:    */  
/* 339:    */  public double topDouble() {
/* 340:340 */    if (isEmpty()) throw new NoSuchElementException();
/* 341:341 */    return getDouble(size64() - 1L);
/* 342:    */  }
/* 343:    */  
/* 344:    */  public double peekDouble(int i) {
/* 345:345 */    return getDouble(size64() - 1L - i);
/* 346:    */  }
/* 347:    */  
/* 349:    */  public double getDouble(int index)
/* 350:    */  {
/* 351:351 */    return getDouble(index);
/* 352:    */  }
/* 353:    */  
/* 354:    */  public boolean rem(double k) {
/* 355:355 */    long index = indexOf(k);
/* 356:356 */    if (index == -1L) return false;
/* 357:357 */    removeDouble(index);
/* 358:358 */    return true;
/* 359:    */  }
/* 360:    */  
/* 361:    */  public boolean addAll(long index, DoubleCollection c)
/* 362:    */  {
/* 363:363 */    return addAll(index, c);
/* 364:    */  }
/* 365:    */  
/* 366:    */  public boolean addAll(long index, DoubleBigList l)
/* 367:    */  {
/* 368:368 */    return addAll(index, l);
/* 369:    */  }
/* 370:    */  
/* 371:    */  public boolean addAll(DoubleCollection c) {
/* 372:372 */    return addAll(size64(), c);
/* 373:    */  }
/* 374:    */  
/* 375:    */  public boolean addAll(DoubleBigList l) {
/* 376:376 */    return addAll(size64(), l);
/* 377:    */  }
/* 378:    */  
/* 379:    */  public void add(long index, Double ok)
/* 380:    */  {
/* 381:381 */    add(index, ok.doubleValue());
/* 382:    */  }
/* 383:    */  
/* 384:    */  public Double set(long index, Double ok)
/* 385:    */  {
/* 386:386 */    return Double.valueOf(set(index, ok.doubleValue()));
/* 387:    */  }
/* 388:    */  
/* 389:    */  public Double get(long index)
/* 390:    */  {
/* 391:391 */    return Double.valueOf(getDouble(index));
/* 392:    */  }
/* 393:    */  
/* 394:    */  public long indexOf(Object ok)
/* 395:    */  {
/* 396:396 */    return indexOf(((Double)ok).doubleValue());
/* 397:    */  }
/* 398:    */  
/* 399:    */  public long lastIndexOf(Object ok)
/* 400:    */  {
/* 401:401 */    return lastIndexOf(((Double)ok).doubleValue());
/* 402:    */  }
/* 403:    */  
/* 404:    */  public Double remove(int index)
/* 405:    */  {
/* 406:406 */    return Double.valueOf(removeDouble(index));
/* 407:    */  }
/* 408:    */  
/* 409:    */  public Double remove(long index)
/* 410:    */  {
/* 411:411 */    return Double.valueOf(removeDouble(index));
/* 412:    */  }
/* 413:    */  
/* 414:    */  public void push(Double o)
/* 415:    */  {
/* 416:416 */    push(o.doubleValue());
/* 417:    */  }
/* 418:    */  
/* 419:    */  public Double pop()
/* 420:    */  {
/* 421:421 */    return Double.valueOf(popDouble());
/* 422:    */  }
/* 423:    */  
/* 424:    */  public Double top()
/* 425:    */  {
/* 426:426 */    return Double.valueOf(topDouble());
/* 427:    */  }
/* 428:    */  
/* 431:431 */  public Double peek(int i) { return Double.valueOf(peekDouble(i)); }
/* 432:    */  
/* 433:    */  public String toString() {
/* 434:434 */    StringBuilder s = new StringBuilder();
/* 435:435 */    DoubleIterator i = iterator();
/* 436:436 */    long n = size64();
/* 437:    */    
/* 438:438 */    boolean first = true;
/* 439:439 */    s.append("[");
/* 440:440 */    while (n-- != 0L) {
/* 441:441 */      if (first) first = false; else
/* 442:442 */        s.append(", ");
/* 443:443 */      double k = i.nextDouble();
/* 444:444 */      s.append(String.valueOf(k));
/* 445:    */    }
/* 446:446 */    s.append("]");
/* 447:447 */    return s.toString();
/* 448:    */  }
/* 449:    */  
/* 450:    */  public static class DoubleSubList extends AbstractDoubleBigList implements Serializable
/* 451:    */  {
/* 452:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 453:    */    protected final DoubleBigList l;
/* 454:    */    protected final long from;
/* 455:    */    protected long to;
/* 456:    */    private static final boolean ASSERTS = false;
/* 457:    */    
/* 458:    */    public DoubleSubList(DoubleBigList l, long from, long to) {
/* 459:459 */      this.l = l;
/* 460:460 */      this.from = from;
/* 461:461 */      this.to = to;
/* 462:    */    }
/* 463:    */    
/* 466:    */    private void assertRange() {}
/* 467:    */    
/* 469:    */    public boolean add(double k)
/* 470:    */    {
/* 471:471 */      this.l.add(this.to, k);
/* 472:472 */      this.to += 1L;
/* 473:    */      
/* 474:474 */      return true;
/* 475:    */    }
/* 476:    */    
/* 477:477 */    public void add(long index, double k) { ensureIndex(index);
/* 478:478 */      this.l.add(this.from + index, k);
/* 479:479 */      this.to += 1L;
/* 480:    */    }
/* 481:    */    
/* 482:    */    public boolean addAll(long index, Collection<? extends Double> c) {
/* 483:483 */      ensureIndex(index);
/* 484:484 */      this.to += c.size();
/* 485:    */      
/* 490:490 */      return this.l.addAll(this.from + index, c);
/* 491:    */    }
/* 492:    */    
/* 493:493 */    public double getDouble(long index) { ensureRestrictedIndex(index);
/* 494:494 */      return this.l.getDouble(this.from + index);
/* 495:    */    }
/* 496:    */    
/* 497:497 */    public double removeDouble(long index) { ensureRestrictedIndex(index);
/* 498:498 */      this.to -= 1L;
/* 499:499 */      return this.l.removeDouble(this.from + index);
/* 500:    */    }
/* 501:    */    
/* 502:502 */    public double set(long index, double k) { ensureRestrictedIndex(index);
/* 503:503 */      return this.l.set(this.from + index, k);
/* 504:    */    }
/* 505:    */    
/* 506:506 */    public void clear() { removeElements(0L, size64()); }
/* 507:    */    
/* 510:510 */    public long size64() { return this.to - this.from; }
/* 511:    */    
/* 512:    */    public void getElements(long from, double[][] a, long offset, long length) {
/* 513:513 */      ensureIndex(from);
/* 514:514 */      if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
/* 515:515 */      this.l.getElements(this.from + from, a, offset, length);
/* 516:    */    }
/* 517:    */    
/* 518:518 */    public void removeElements(long from, long to) { ensureIndex(from);
/* 519:519 */      ensureIndex(to);
/* 520:520 */      this.l.removeElements(this.from + from, this.from + to);
/* 521:521 */      this.to -= to - from;
/* 522:    */    }
/* 523:    */    
/* 524:    */    public void addElements(long index, double[][] a, long offset, long length) {
/* 525:525 */      ensureIndex(index);
/* 526:526 */      this.l.addElements(this.from + index, a, offset, length);
/* 527:527 */      this.to += length;
/* 528:    */    }
/* 529:    */    
/* 530:    */    public DoubleBigListIterator listIterator(final long index) {
/* 531:531 */      ensureIndex(index);
/* 532:532 */      new AbstractDoubleBigListIterator() {
/* 533:533 */        long last = -1L; long pos = index;
/* 534:534 */        public boolean hasNext() { return this.pos < AbstractDoubleBigList.DoubleSubList.this.size64(); }
/* 535:535 */        public boolean hasPrevious() { return this.pos > 0L; }
/* 536:536 */        public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return AbstractDoubleBigList.DoubleSubList.this.l.getDouble(AbstractDoubleBigList.DoubleSubList.this.from + (this.last = this.pos++)); }
/* 537:537 */        public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractDoubleBigList.DoubleSubList.this.l.getDouble(AbstractDoubleBigList.DoubleSubList.this.from + (this.last = --this.pos)); }
/* 538:538 */        public long nextIndex() { return this.pos; }
/* 539:539 */        public long previousIndex() { return this.pos - 1L; }
/* 540:    */        
/* 541:541 */        public void add(double k) { if (this.last == -1L) throw new IllegalStateException();
/* 542:542 */          AbstractDoubleBigList.DoubleSubList.this.add(this.pos++, k);
/* 543:543 */          this.last = -1L;
/* 544:    */        }
/* 545:    */        
/* 546:    */        public void set(double k) {
/* 547:547 */          if (this.last == -1L) throw new IllegalStateException();
/* 548:548 */          AbstractDoubleBigList.DoubleSubList.this.set(this.last, k);
/* 549:    */        }
/* 550:    */        
/* 551:551 */        public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 552:552 */          AbstractDoubleBigList.DoubleSubList.this.removeDouble(this.last);
/* 553:    */          
/* 554:554 */          if (this.last < this.pos) this.pos -= 1L;
/* 555:555 */          this.last = -1L;
/* 556:    */        }
/* 557:    */      };
/* 558:    */    }
/* 559:    */    
/* 560:    */    public DoubleBigList subList(long from, long to) {
/* 561:561 */      ensureIndex(from);
/* 562:562 */      ensureIndex(to);
/* 563:563 */      if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 564:564 */      return new DoubleSubList(this, from, to);
/* 565:    */    }
/* 566:    */    
/* 567:567 */    public boolean rem(double k) { long index = indexOf(k);
/* 568:568 */      if (index == -1L) return false;
/* 569:569 */      this.to -= 1L;
/* 570:570 */      this.l.removeDouble(this.from + index);
/* 571:    */      
/* 572:572 */      return true;
/* 573:    */    }
/* 574:    */    
/* 575:575 */    public boolean remove(Object o) { return rem(((Double)o).doubleValue()); }
/* 576:    */    
/* 577:    */    public boolean addAll(long index, DoubleCollection c) {
/* 578:578 */      ensureIndex(index);
/* 579:579 */      this.to += c.size();
/* 580:    */      
/* 585:585 */      return this.l.addAll(this.from + index, c);
/* 586:    */    }
/* 587:    */    
/* 588:588 */    public boolean addAll(long index, DoubleList l) { ensureIndex(index);
/* 589:589 */      this.to += l.size();
/* 590:    */      
/* 595:595 */      return this.l.addAll(this.from + index, l);
/* 596:    */    }
/* 597:    */  }
/* 598:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDoubleBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */