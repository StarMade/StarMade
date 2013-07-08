/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigList;
/*   4:    */import it.unimi.dsi.fastutil.BigListIterator;
/*   5:    */import it.unimi.dsi.fastutil.HashCommon;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  53:    */public abstract class AbstractFloatBigList
/*  54:    */  extends AbstractFloatCollection
/*  55:    */  implements FloatBigList, FloatStack
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
/*  72: 72 */  public void add(long index, float k) { throw new UnsupportedOperationException(); }
/*  73:    */  
/*  74:    */  public boolean add(float k) {
/*  75: 75 */    add(size64(), k);
/*  76: 76 */    return true;
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public float removeFloat(long i) { throw new UnsupportedOperationException(); }
/*  80:    */  
/*  81:    */  public float removeFloat(int i) {
/*  82: 82 */    return removeFloat(i);
/*  83:    */  }
/*  84:    */  
/*  85: 85 */  public float set(long index, float k) { throw new UnsupportedOperationException(); }
/*  86:    */  
/*  88: 88 */  public float set(int index, float k) { return set(index, k); }
/*  89:    */  
/*  90:    */  public boolean addAll(long index, Collection<? extends Float> c) {
/*  91: 91 */    ensureIndex(index);
/*  92: 92 */    int n = c.size();
/*  93: 93 */    if (n == 0) return false;
/*  94: 94 */    Iterator<? extends Float> i = c.iterator();
/*  95: 95 */    while (n-- != 0) add(index++, (Float)i.next());
/*  96: 96 */    return true;
/*  97:    */  }
/*  98:    */  
/*  99: 99 */  public boolean addAll(int index, Collection<? extends Float> c) { return addAll(index, c); }
/* 100:    */  
/* 101:    */  public boolean addAll(Collection<? extends Float> c)
/* 102:    */  {
/* 103:103 */    return addAll(size64(), c);
/* 104:    */  }
/* 105:    */  
/* 106:106 */  public FloatBigListIterator iterator() { return listIterator(); }
/* 107:    */  
/* 108:    */  public FloatBigListIterator listIterator() {
/* 109:109 */    return listIterator(0L);
/* 110:    */  }
/* 111:    */  
/* 112:112 */  public FloatBigListIterator listIterator(final long index) { new AbstractFloatBigListIterator() {
/* 113:113 */      long last = -1L; long pos = index;
/* 114:114 */      public boolean hasNext() { return this.pos < AbstractFloatBigList.this.size64(); }
/* 115:115 */      public boolean hasPrevious() { return this.pos > 0L; }
/* 116:116 */      public float nextFloat() { if (!hasNext()) throw new NoSuchElementException(); return AbstractFloatBigList.this.getFloat(this.last = this.pos++); }
/* 117:117 */      public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractFloatBigList.this.getFloat(this.last = --this.pos); }
/* 118:118 */      public long nextIndex() { return this.pos; }
/* 119:119 */      public long previousIndex() { return this.pos - 1L; }
/* 120:    */      
/* 121:121 */      public void add(float k) { if (this.last == -1L) throw new IllegalStateException();
/* 122:122 */        AbstractFloatBigList.this.add(this.pos++, k);
/* 123:123 */        this.last = -1L;
/* 124:    */      }
/* 125:    */      
/* 126:126 */      public void set(float k) { if (this.last == -1L) throw new IllegalStateException();
/* 127:127 */        AbstractFloatBigList.this.set(this.last, k);
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 131:131 */        AbstractFloatBigList.this.removeFloat(this.last);
/* 132:    */        
/* 133:133 */        if (this.last < this.pos) this.pos -= 1L;
/* 134:134 */        this.last = -1L;
/* 135:    */      }
/* 136:    */    }; }
/* 137:    */  
/* 139:    */  public FloatBigListIterator listIterator(int index)
/* 140:    */  {
/* 141:141 */    return listIterator(index);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public boolean contains(float k)
/* 145:    */  {
/* 146:146 */    return indexOf(k) >= 0L;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public long indexOf(float k) {
/* 150:150 */    FloatBigListIterator i = listIterator();
/* 151:    */    
/* 152:152 */    while (i.hasNext()) {
/* 153:153 */      float e = i.nextFloat();
/* 154:154 */      if (k == e) return i.previousIndex();
/* 155:    */    }
/* 156:156 */    return -1L;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public long lastIndexOf(float k) {
/* 160:160 */    FloatBigListIterator i = listIterator(size64());
/* 161:    */    
/* 162:162 */    while (i.hasPrevious()) {
/* 163:163 */      float e = i.previousFloat();
/* 164:164 */      if (k == e) return i.nextIndex();
/* 165:    */    }
/* 166:166 */    return -1L;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void size(long size) {
/* 170:170 */    long i = size64();
/* 171:171 */    for (size <= i; i++ < size; add(0.0F)) {}
/* 172:172 */    while (i-- != size) remove(i);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void size(int size) {
/* 176:176 */    size(size);
/* 177:    */  }
/* 178:    */  
/* 179:    */  public FloatBigList subList(long from, long to) {
/* 180:180 */    ensureIndex(from);
/* 181:181 */    ensureIndex(to);
/* 182:182 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 183:    */    }
/* 184:184 */    return new FloatSubList(this, from, to);
/* 185:    */  }
/* 186:    */  
/* 195:    */  public void removeElements(long from, long to)
/* 196:    */  {
/* 197:197 */    ensureIndex(to);
/* 198:198 */    FloatBigListIterator i = listIterator(from);
/* 199:199 */    long n = to - from;
/* 200:200 */    if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201:201 */    while (n-- != 0L) {
/* 202:202 */      i.nextFloat();
/* 203:203 */      i.remove();
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 217:    */  public void addElements(long index, float[][] a, long offset, long length)
/* 218:    */  {
/* 219:219 */    ensureIndex(index);
/* 220:220 */    FloatBigArrays.ensureOffsetLength(a, offset, length);
/* 221:221 */    while (length-- != 0L) add(index++, FloatBigArrays.get(a, offset++));
/* 222:    */  }
/* 223:    */  
/* 224:    */  public void addElements(long index, float[][] a) {
/* 225:225 */    addElements(index, a, 0L, FloatBigArrays.length(a));
/* 226:    */  }
/* 227:    */  
/* 238:    */  public void getElements(long from, float[][] a, long offset, long length)
/* 239:    */  {
/* 240:240 */    FloatBigListIterator i = listIterator(from);
/* 241:241 */    FloatBigArrays.ensureOffsetLength(a, offset, length);
/* 242:242 */    if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243:243 */    while (length-- != 0L) FloatBigArrays.set(a, offset++, i.nextFloat());
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
/* 287:    */  public int compareTo(BigList<? extends Float> l)
/* 288:    */  {
/* 289:289 */    if (l == this) { return 0;
/* 290:    */    }
/* 291:291 */    if ((l instanceof FloatBigList))
/* 292:    */    {
/* 293:293 */      FloatBigListIterator i1 = listIterator();FloatBigListIterator i2 = ((FloatBigList)l).listIterator();
/* 294:    */      
/* 297:297 */      while ((i1.hasNext()) && (i2.hasNext())) {
/* 298:298 */        float e1 = i1.nextFloat();
/* 299:299 */        float e2 = i2.nextFloat();
/* 300:300 */        int r; if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/* 301:    */      }
/* 302:302 */      return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 303:    */    }
/* 304:    */    
/* 305:305 */    BigListIterator<? extends Float> i1 = listIterator();BigListIterator<? extends Float> i2 = l.listIterator();
/* 306:    */    
/* 308:308 */    while ((i1.hasNext()) && (i2.hasNext())) { int r;
/* 309:309 */      if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/* 310:    */    }
/* 311:311 */    return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 312:    */  }
/* 313:    */  
/* 318:    */  public int hashCode()
/* 319:    */  {
/* 320:320 */    FloatIterator i = iterator();
/* 321:321 */    int h = 1;
/* 322:322 */    long s = size64();
/* 323:323 */    while (s-- != 0L) {
/* 324:324 */      float k = i.nextFloat();
/* 325:325 */      h = 31 * h + HashCommon.float2int(k);
/* 326:    */    }
/* 327:327 */    return h;
/* 328:    */  }
/* 329:    */  
/* 330:    */  public void push(float o) {
/* 331:331 */    add(o);
/* 332:    */  }
/* 333:    */  
/* 334:    */  public float popFloat() {
/* 335:335 */    if (isEmpty()) throw new NoSuchElementException();
/* 336:336 */    return removeFloat(size64() - 1L);
/* 337:    */  }
/* 338:    */  
/* 339:    */  public float topFloat() {
/* 340:340 */    if (isEmpty()) throw new NoSuchElementException();
/* 341:341 */    return getFloat(size64() - 1L);
/* 342:    */  }
/* 343:    */  
/* 344:    */  public float peekFloat(int i) {
/* 345:345 */    return getFloat(size64() - 1L - i);
/* 346:    */  }
/* 347:    */  
/* 349:    */  public float getFloat(int index)
/* 350:    */  {
/* 351:351 */    return getFloat(index);
/* 352:    */  }
/* 353:    */  
/* 354:    */  public boolean rem(float k) {
/* 355:355 */    long index = indexOf(k);
/* 356:356 */    if (index == -1L) return false;
/* 357:357 */    removeFloat(index);
/* 358:358 */    return true;
/* 359:    */  }
/* 360:    */  
/* 361:    */  public boolean addAll(long index, FloatCollection c)
/* 362:    */  {
/* 363:363 */    return addAll(index, c);
/* 364:    */  }
/* 365:    */  
/* 366:    */  public boolean addAll(long index, FloatBigList l)
/* 367:    */  {
/* 368:368 */    return addAll(index, l);
/* 369:    */  }
/* 370:    */  
/* 371:    */  public boolean addAll(FloatCollection c) {
/* 372:372 */    return addAll(size64(), c);
/* 373:    */  }
/* 374:    */  
/* 375:    */  public boolean addAll(FloatBigList l) {
/* 376:376 */    return addAll(size64(), l);
/* 377:    */  }
/* 378:    */  
/* 379:    */  public void add(long index, Float ok)
/* 380:    */  {
/* 381:381 */    add(index, ok.floatValue());
/* 382:    */  }
/* 383:    */  
/* 384:    */  public Float set(long index, Float ok)
/* 385:    */  {
/* 386:386 */    return Float.valueOf(set(index, ok.floatValue()));
/* 387:    */  }
/* 388:    */  
/* 389:    */  public Float get(long index)
/* 390:    */  {
/* 391:391 */    return Float.valueOf(getFloat(index));
/* 392:    */  }
/* 393:    */  
/* 394:    */  public long indexOf(Object ok)
/* 395:    */  {
/* 396:396 */    return indexOf(((Float)ok).floatValue());
/* 397:    */  }
/* 398:    */  
/* 399:    */  public long lastIndexOf(Object ok)
/* 400:    */  {
/* 401:401 */    return lastIndexOf(((Float)ok).floatValue());
/* 402:    */  }
/* 403:    */  
/* 404:    */  public Float remove(int index)
/* 405:    */  {
/* 406:406 */    return Float.valueOf(removeFloat(index));
/* 407:    */  }
/* 408:    */  
/* 409:    */  public Float remove(long index)
/* 410:    */  {
/* 411:411 */    return Float.valueOf(removeFloat(index));
/* 412:    */  }
/* 413:    */  
/* 414:    */  public void push(Float o)
/* 415:    */  {
/* 416:416 */    push(o.floatValue());
/* 417:    */  }
/* 418:    */  
/* 419:    */  public Float pop()
/* 420:    */  {
/* 421:421 */    return Float.valueOf(popFloat());
/* 422:    */  }
/* 423:    */  
/* 424:    */  public Float top()
/* 425:    */  {
/* 426:426 */    return Float.valueOf(topFloat());
/* 427:    */  }
/* 428:    */  
/* 431:431 */  public Float peek(int i) { return Float.valueOf(peekFloat(i)); }
/* 432:    */  
/* 433:    */  public String toString() {
/* 434:434 */    StringBuilder s = new StringBuilder();
/* 435:435 */    FloatIterator i = iterator();
/* 436:436 */    long n = size64();
/* 437:    */    
/* 438:438 */    boolean first = true;
/* 439:439 */    s.append("[");
/* 440:440 */    while (n-- != 0L) {
/* 441:441 */      if (first) first = false; else
/* 442:442 */        s.append(", ");
/* 443:443 */      float k = i.nextFloat();
/* 444:444 */      s.append(String.valueOf(k));
/* 445:    */    }
/* 446:446 */    s.append("]");
/* 447:447 */    return s.toString();
/* 448:    */  }
/* 449:    */  
/* 450:    */  public static class FloatSubList extends AbstractFloatBigList implements Serializable
/* 451:    */  {
/* 452:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 453:    */    protected final FloatBigList l;
/* 454:    */    protected final long from;
/* 455:    */    protected long to;
/* 456:    */    private static final boolean ASSERTS = false;
/* 457:    */    
/* 458:    */    public FloatSubList(FloatBigList l, long from, long to) {
/* 459:459 */      this.l = l;
/* 460:460 */      this.from = from;
/* 461:461 */      this.to = to;
/* 462:    */    }
/* 463:    */    
/* 466:    */    private void assertRange() {}
/* 467:    */    
/* 469:    */    public boolean add(float k)
/* 470:    */    {
/* 471:471 */      this.l.add(this.to, k);
/* 472:472 */      this.to += 1L;
/* 473:    */      
/* 474:474 */      return true;
/* 475:    */    }
/* 476:    */    
/* 477:477 */    public void add(long index, float k) { ensureIndex(index);
/* 478:478 */      this.l.add(this.from + index, k);
/* 479:479 */      this.to += 1L;
/* 480:    */    }
/* 481:    */    
/* 482:    */    public boolean addAll(long index, Collection<? extends Float> c) {
/* 483:483 */      ensureIndex(index);
/* 484:484 */      this.to += c.size();
/* 485:    */      
/* 490:490 */      return this.l.addAll(this.from + index, c);
/* 491:    */    }
/* 492:    */    
/* 493:493 */    public float getFloat(long index) { ensureRestrictedIndex(index);
/* 494:494 */      return this.l.getFloat(this.from + index);
/* 495:    */    }
/* 496:    */    
/* 497:497 */    public float removeFloat(long index) { ensureRestrictedIndex(index);
/* 498:498 */      this.to -= 1L;
/* 499:499 */      return this.l.removeFloat(this.from + index);
/* 500:    */    }
/* 501:    */    
/* 502:502 */    public float set(long index, float k) { ensureRestrictedIndex(index);
/* 503:503 */      return this.l.set(this.from + index, k);
/* 504:    */    }
/* 505:    */    
/* 506:506 */    public void clear() { removeElements(0L, size64()); }
/* 507:    */    
/* 510:510 */    public long size64() { return this.to - this.from; }
/* 511:    */    
/* 512:    */    public void getElements(long from, float[][] a, long offset, long length) {
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
/* 524:    */    public void addElements(long index, float[][] a, long offset, long length) {
/* 525:525 */      ensureIndex(index);
/* 526:526 */      this.l.addElements(this.from + index, a, offset, length);
/* 527:527 */      this.to += length;
/* 528:    */    }
/* 529:    */    
/* 530:    */    public FloatBigListIterator listIterator(final long index) {
/* 531:531 */      ensureIndex(index);
/* 532:532 */      new AbstractFloatBigListIterator() {
/* 533:533 */        long last = -1L; long pos = index;
/* 534:534 */        public boolean hasNext() { return this.pos < AbstractFloatBigList.FloatSubList.this.size64(); }
/* 535:535 */        public boolean hasPrevious() { return this.pos > 0L; }
/* 536:536 */        public float nextFloat() { if (!hasNext()) throw new NoSuchElementException(); return AbstractFloatBigList.FloatSubList.this.l.getFloat(AbstractFloatBigList.FloatSubList.this.from + (this.last = this.pos++)); }
/* 537:537 */        public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractFloatBigList.FloatSubList.this.l.getFloat(AbstractFloatBigList.FloatSubList.this.from + (this.last = --this.pos)); }
/* 538:538 */        public long nextIndex() { return this.pos; }
/* 539:539 */        public long previousIndex() { return this.pos - 1L; }
/* 540:    */        
/* 541:541 */        public void add(float k) { if (this.last == -1L) throw new IllegalStateException();
/* 542:542 */          AbstractFloatBigList.FloatSubList.this.add(this.pos++, k);
/* 543:543 */          this.last = -1L;
/* 544:    */        }
/* 545:    */        
/* 546:    */        public void set(float k) {
/* 547:547 */          if (this.last == -1L) throw new IllegalStateException();
/* 548:548 */          AbstractFloatBigList.FloatSubList.this.set(this.last, k);
/* 549:    */        }
/* 550:    */        
/* 551:551 */        public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 552:552 */          AbstractFloatBigList.FloatSubList.this.removeFloat(this.last);
/* 553:    */          
/* 554:554 */          if (this.last < this.pos) this.pos -= 1L;
/* 555:555 */          this.last = -1L;
/* 556:    */        }
/* 557:    */      };
/* 558:    */    }
/* 559:    */    
/* 560:    */    public FloatBigList subList(long from, long to) {
/* 561:561 */      ensureIndex(from);
/* 562:562 */      ensureIndex(to);
/* 563:563 */      if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 564:564 */      return new FloatSubList(this, from, to);
/* 565:    */    }
/* 566:    */    
/* 567:567 */    public boolean rem(float k) { long index = indexOf(k);
/* 568:568 */      if (index == -1L) return false;
/* 569:569 */      this.to -= 1L;
/* 570:570 */      this.l.removeFloat(this.from + index);
/* 571:    */      
/* 572:572 */      return true;
/* 573:    */    }
/* 574:    */    
/* 575:575 */    public boolean remove(Object o) { return rem(((Float)o).floatValue()); }
/* 576:    */    
/* 577:    */    public boolean addAll(long index, FloatCollection c) {
/* 578:578 */      ensureIndex(index);
/* 579:579 */      this.to += c.size();
/* 580:    */      
/* 585:585 */      return this.l.addAll(this.from + index, c);
/* 586:    */    }
/* 587:    */    
/* 588:588 */    public boolean addAll(long index, FloatList l) { ensureIndex(index);
/* 589:589 */      this.to += l.size();
/* 590:    */      
/* 595:595 */      return this.l.addAll(this.from + index, l);
/* 596:    */    }
/* 597:    */  }
/* 598:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */