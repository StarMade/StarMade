/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.HashCommon;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.ListIterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  56:    */public abstract class AbstractFloatList
/*  57:    */  extends AbstractFloatCollection
/*  58:    */  implements FloatList, FloatStack
/*  59:    */{
/*  60:    */  protected void ensureIndex(int index)
/*  61:    */  {
/*  62: 62 */    if (index < 0) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  63: 63 */    if (index > size()) { throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than list size (").append(size()).append(")").toString());
/*  64:    */    }
/*  65:    */  }
/*  66:    */  
/*  69:    */  protected void ensureRestrictedIndex(int index)
/*  70:    */  {
/*  71: 71 */    if (index < 0) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is negative").toString());
/*  72: 72 */    if (index >= size()) throw new IndexOutOfBoundsException(new StringBuilder().append("Index (").append(index).append(") is greater than or equal to list size (").append(size()).append(")").toString());
/*  73:    */  }
/*  74:    */  
/*  75: 75 */  public void add(int index, float k) { throw new UnsupportedOperationException(); }
/*  76:    */  
/*  77:    */  public boolean add(float k) {
/*  78: 78 */    add(size(), k);
/*  79: 79 */    return true;
/*  80:    */  }
/*  81:    */  
/*  82: 82 */  public float removeFloat(int i) { throw new UnsupportedOperationException(); }
/*  83:    */  
/*  85: 85 */  public float set(int index, float k) { throw new UnsupportedOperationException(); }
/*  86:    */  
/*  87:    */  public boolean addAll(int index, Collection<? extends Float> c) {
/*  88: 88 */    ensureIndex(index);
/*  89: 89 */    int n = c.size();
/*  90: 90 */    if (n == 0) return false;
/*  91: 91 */    Iterator<? extends Float> i = c.iterator();
/*  92: 92 */    while (n-- != 0) add(index++, (Float)i.next());
/*  93: 93 */    return true;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public boolean addAll(Collection<? extends Float> c) {
/*  97: 97 */    return addAll(size(), c);
/*  98:    */  }
/*  99:    */  
/* 100:    */  @Deprecated
/* 101:    */  public FloatListIterator floatListIterator() {
/* 102:102 */    return listIterator();
/* 103:    */  }
/* 104:    */  
/* 105:    */  @Deprecated
/* 106:    */  public FloatListIterator floatListIterator(int index) {
/* 107:107 */    return listIterator(index);
/* 108:    */  }
/* 109:    */  
/* 110:110 */  public FloatListIterator iterator() { return listIterator(); }
/* 111:    */  
/* 112:    */  public FloatListIterator listIterator() {
/* 113:113 */    return listIterator(0);
/* 114:    */  }
/* 115:    */  
/* 116:116 */  public FloatListIterator listIterator(final int index) { new AbstractFloatListIterator() {
/* 117:117 */      int last = -1; int pos = index;
/* 118:118 */      public boolean hasNext() { return this.pos < AbstractFloatList.this.size(); }
/* 119:119 */      public boolean hasPrevious() { return this.pos > 0; }
/* 120:120 */      public float nextFloat() { if (!hasNext()) throw new NoSuchElementException(); return AbstractFloatList.this.getFloat(this.last = this.pos++); }
/* 121:121 */      public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractFloatList.this.getFloat(this.last = --this.pos); }
/* 122:122 */      public int nextIndex() { return this.pos; }
/* 123:123 */      public int previousIndex() { return this.pos - 1; }
/* 124:    */      
/* 125:125 */      public void add(float k) { if (this.last == -1) throw new IllegalStateException();
/* 126:126 */        AbstractFloatList.this.add(this.pos++, k);
/* 127:127 */        this.last = -1;
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void set(float k) { if (this.last == -1) throw new IllegalStateException();
/* 131:131 */        AbstractFloatList.this.set(this.last, k);
/* 132:    */      }
/* 133:    */      
/* 134:134 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 135:135 */        AbstractFloatList.this.removeFloat(this.last);
/* 136:    */        
/* 137:137 */        if (this.last < this.pos) this.pos -= 1;
/* 138:138 */        this.last = -1;
/* 139:    */      }
/* 140:    */    }; }
/* 141:    */  
/* 144:    */  public boolean contains(float k)
/* 145:    */  {
/* 146:146 */    return indexOf(k) >= 0;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public int indexOf(float k) {
/* 150:150 */    FloatListIterator i = listIterator();
/* 151:    */    
/* 152:152 */    while (i.hasNext()) {
/* 153:153 */      float e = i.nextFloat();
/* 154:154 */      if (k == e) return i.previousIndex();
/* 155:    */    }
/* 156:156 */    return -1;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public int lastIndexOf(float k) {
/* 160:160 */    FloatListIterator i = listIterator(size());
/* 161:    */    
/* 162:162 */    while (i.hasPrevious()) {
/* 163:163 */      float e = i.previousFloat();
/* 164:164 */      if (k == e) return i.nextIndex();
/* 165:    */    }
/* 166:166 */    return -1;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void size(int size) {
/* 170:170 */    int i = size();
/* 171:171 */    for (size <= i; i++ < size; add(0.0F)) {}
/* 172:172 */    while (i-- != size) remove(i);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public FloatList subList(int from, int to)
/* 176:    */  {
/* 177:177 */    ensureIndex(from);
/* 178:178 */    ensureIndex(to);
/* 179:179 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 180:    */    }
/* 181:181 */    return new FloatSubList(this, from, to);
/* 182:    */  }
/* 183:    */  
/* 185:    */  @Deprecated
/* 186:    */  public FloatList floatSubList(int from, int to)
/* 187:    */  {
/* 188:188 */    return subList(from, to);
/* 189:    */  }
/* 190:    */  
/* 200:    */  public void removeElements(int from, int to)
/* 201:    */  {
/* 202:202 */    ensureIndex(to);
/* 203:203 */    FloatListIterator i = listIterator(from);
/* 204:204 */    int n = to - from;
/* 205:205 */    if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 206:206 */    while (n-- != 0) {
/* 207:207 */      i.nextFloat();
/* 208:208 */      i.remove();
/* 209:    */    }
/* 210:    */  }
/* 211:    */  
/* 222:    */  public void addElements(int index, float[] a, int offset, int length)
/* 223:    */  {
/* 224:224 */    ensureIndex(index);
/* 225:225 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 226:226 */    if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 227:227 */    while (length-- != 0) add(index++, a[(offset++)]);
/* 228:    */  }
/* 229:    */  
/* 230:    */  public void addElements(int index, float[] a) {
/* 231:231 */    addElements(index, a, 0, a.length);
/* 232:    */  }
/* 233:    */  
/* 244:    */  public void getElements(int from, float[] a, int offset, int length)
/* 245:    */  {
/* 246:246 */    FloatListIterator i = listIterator(from);
/* 247:247 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 248:248 */    if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 249:249 */    if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 250:250 */    while (length-- != 0) a[(offset++)] = i.nextFloat();
/* 251:    */  }
/* 252:    */  
/* 253:    */  private boolean valEquals(Object a, Object b)
/* 254:    */  {
/* 255:255 */    return a == null ? false : b == null ? true : a.equals(b);
/* 256:    */  }
/* 257:    */  
/* 258:    */  public boolean equals(Object o)
/* 259:    */  {
/* 260:260 */    if (o == this) return true;
/* 261:261 */    if (!(o instanceof List)) return false;
/* 262:262 */    List<?> l = (List)o;
/* 263:263 */    int s = size();
/* 264:264 */    if (s != l.size()) { return false;
/* 265:    */    }
/* 266:266 */    ListIterator<?> i1 = listIterator();ListIterator<?> i2 = l.listIterator();
/* 267:    */    
/* 271:271 */    while (s-- != 0) if (!valEquals(i1.next(), i2.next())) { return false;
/* 272:    */      }
/* 273:273 */    return true;
/* 274:    */  }
/* 275:    */  
/* 288:    */  public int compareTo(List<? extends Float> l)
/* 289:    */  {
/* 290:290 */    if (l == this) { return 0;
/* 291:    */    }
/* 292:292 */    if ((l instanceof FloatList))
/* 293:    */    {
/* 294:294 */      FloatListIterator i1 = listIterator();FloatListIterator i2 = ((FloatList)l).listIterator();
/* 295:    */      
/* 298:298 */      while ((i1.hasNext()) && (i2.hasNext())) {
/* 299:299 */        float e1 = i1.nextFloat();
/* 300:300 */        float e2 = i2.nextFloat();
/* 301:301 */        int r; if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/* 302:    */      }
/* 303:303 */      return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 304:    */    }
/* 305:    */    
/* 306:306 */    ListIterator<? extends Float> i1 = listIterator();ListIterator<? extends Float> i2 = l.listIterator();
/* 307:    */    
/* 309:309 */    while ((i1.hasNext()) && (i2.hasNext())) { int r;
/* 310:310 */      if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/* 311:    */    }
/* 312:312 */    return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 313:    */  }
/* 314:    */  
/* 319:    */  public int hashCode()
/* 320:    */  {
/* 321:321 */    FloatIterator i = iterator();
/* 322:322 */    int h = 1;int s = size();
/* 323:323 */    while (s-- != 0) {
/* 324:324 */      float k = i.nextFloat();
/* 325:325 */      h = 31 * h + HashCommon.float2int(k);
/* 326:    */    }
/* 327:327 */    return h;
/* 328:    */  }
/* 329:    */  
/* 330:    */  public void push(float o)
/* 331:    */  {
/* 332:332 */    add(o);
/* 333:    */  }
/* 334:    */  
/* 335:    */  public float popFloat() {
/* 336:336 */    if (isEmpty()) throw new NoSuchElementException();
/* 337:337 */    return removeFloat(size() - 1);
/* 338:    */  }
/* 339:    */  
/* 340:    */  public float topFloat() {
/* 341:341 */    if (isEmpty()) throw new NoSuchElementException();
/* 342:342 */    return getFloat(size() - 1);
/* 343:    */  }
/* 344:    */  
/* 345:    */  public float peekFloat(int i) {
/* 346:346 */    return getFloat(size() - 1 - i);
/* 347:    */  }
/* 348:    */  
/* 350:    */  public boolean rem(float k)
/* 351:    */  {
/* 352:352 */    int index = indexOf(k);
/* 353:353 */    if (index == -1) return false;
/* 354:354 */    removeFloat(index);
/* 355:355 */    return true;
/* 356:    */  }
/* 357:    */  
/* 358:    */  public boolean remove(Object o)
/* 359:    */  {
/* 360:360 */    return rem(((Float)o).floatValue());
/* 361:    */  }
/* 362:    */  
/* 363:    */  public boolean addAll(int index, FloatCollection c)
/* 364:    */  {
/* 365:365 */    return addAll(index, c);
/* 366:    */  }
/* 367:    */  
/* 368:    */  public boolean addAll(int index, FloatList l)
/* 369:    */  {
/* 370:370 */    return addAll(index, l);
/* 371:    */  }
/* 372:    */  
/* 373:    */  public boolean addAll(FloatCollection c) {
/* 374:374 */    return addAll(size(), c);
/* 375:    */  }
/* 376:    */  
/* 377:    */  public boolean addAll(FloatList l) {
/* 378:378 */    return addAll(size(), l);
/* 379:    */  }
/* 380:    */  
/* 381:    */  public void add(int index, Float ok)
/* 382:    */  {
/* 383:383 */    add(index, ok.floatValue());
/* 384:    */  }
/* 385:    */  
/* 386:    */  public Float set(int index, Float ok)
/* 387:    */  {
/* 388:388 */    return Float.valueOf(set(index, ok.floatValue()));
/* 389:    */  }
/* 390:    */  
/* 391:    */  public Float get(int index)
/* 392:    */  {
/* 393:393 */    return Float.valueOf(getFloat(index));
/* 394:    */  }
/* 395:    */  
/* 396:    */  public int indexOf(Object ok)
/* 397:    */  {
/* 398:398 */    return indexOf(((Float)ok).floatValue());
/* 399:    */  }
/* 400:    */  
/* 401:    */  public int lastIndexOf(Object ok)
/* 402:    */  {
/* 403:403 */    return lastIndexOf(((Float)ok).floatValue());
/* 404:    */  }
/* 405:    */  
/* 406:    */  public Float remove(int index)
/* 407:    */  {
/* 408:408 */    return Float.valueOf(removeFloat(index));
/* 409:    */  }
/* 410:    */  
/* 411:    */  public void push(Float o)
/* 412:    */  {
/* 413:413 */    push(o.floatValue());
/* 414:    */  }
/* 415:    */  
/* 416:    */  public Float pop()
/* 417:    */  {
/* 418:418 */    return Float.valueOf(popFloat());
/* 419:    */  }
/* 420:    */  
/* 421:    */  public Float top()
/* 422:    */  {
/* 423:423 */    return Float.valueOf(topFloat());
/* 424:    */  }
/* 425:    */  
/* 426:    */  public Float peek(int i)
/* 427:    */  {
/* 428:428 */    return Float.valueOf(peekFloat(i));
/* 429:    */  }
/* 430:    */  
/* 433:    */  public String toString()
/* 434:    */  {
/* 435:435 */    StringBuilder s = new StringBuilder();
/* 436:436 */    FloatIterator i = iterator();
/* 437:437 */    int n = size();
/* 438:    */    
/* 439:439 */    boolean first = true;
/* 440:    */    
/* 441:441 */    s.append("[");
/* 442:    */    
/* 443:443 */    while (n-- != 0) {
/* 444:444 */      if (first) first = false; else
/* 445:445 */        s.append(", ");
/* 446:446 */      float k = i.nextFloat();
/* 447:    */      
/* 450:450 */      s.append(String.valueOf(k));
/* 451:    */    }
/* 452:    */    
/* 453:453 */    s.append("]");
/* 454:454 */    return s.toString();
/* 455:    */  }
/* 456:    */  
/* 458:    */  public static class FloatSubList
/* 459:    */    extends AbstractFloatList
/* 460:    */    implements Serializable
/* 461:    */  {
/* 462:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 463:    */    protected final FloatList l;
/* 464:    */    protected final int from;
/* 465:    */    protected int to;
/* 466:    */    private static final boolean ASSERTS = false;
/* 467:    */    
/* 468:    */    public FloatSubList(FloatList l, int from, int to)
/* 469:    */    {
/* 470:470 */      this.l = l;
/* 471:471 */      this.from = from;
/* 472:472 */      this.to = to;
/* 473:    */    }
/* 474:    */    
/* 478:    */    private void assertRange() {}
/* 479:    */    
/* 482:    */    public boolean add(float k)
/* 483:    */    {
/* 484:484 */      this.l.add(this.to, k);
/* 485:485 */      this.to += 1;
/* 486:    */      
/* 487:487 */      return true;
/* 488:    */    }
/* 489:    */    
/* 490:    */    public void add(int index, float k) {
/* 491:491 */      ensureIndex(index);
/* 492:492 */      this.l.add(this.from + index, k);
/* 493:493 */      this.to += 1;
/* 494:    */    }
/* 495:    */    
/* 496:    */    public boolean addAll(int index, Collection<? extends Float> c)
/* 497:    */    {
/* 498:498 */      ensureIndex(index);
/* 499:499 */      this.to += c.size();
/* 500:    */      
/* 505:505 */      return this.l.addAll(this.from + index, c);
/* 506:    */    }
/* 507:    */    
/* 508:    */    public float getFloat(int index) {
/* 509:509 */      ensureRestrictedIndex(index);
/* 510:510 */      return this.l.getFloat(this.from + index);
/* 511:    */    }
/* 512:    */    
/* 513:    */    public float removeFloat(int index) {
/* 514:514 */      ensureRestrictedIndex(index);
/* 515:515 */      this.to -= 1;
/* 516:516 */      return this.l.removeFloat(this.from + index);
/* 517:    */    }
/* 518:    */    
/* 519:    */    public float set(int index, float k) {
/* 520:520 */      ensureRestrictedIndex(index);
/* 521:521 */      return this.l.set(this.from + index, k);
/* 522:    */    }
/* 523:    */    
/* 524:    */    public void clear() {
/* 525:525 */      removeElements(0, size());
/* 526:    */    }
/* 527:    */    
/* 528:    */    public int size()
/* 529:    */    {
/* 530:530 */      return this.to - this.from;
/* 531:    */    }
/* 532:    */    
/* 533:    */    public void getElements(int from, float[] a, int offset, int length) {
/* 534:534 */      ensureIndex(from);
/* 535:535 */      if (from + length > size()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
/* 536:536 */      this.l.getElements(this.from + from, a, offset, length);
/* 537:    */    }
/* 538:    */    
/* 539:    */    public void removeElements(int from, int to) {
/* 540:540 */      ensureIndex(from);
/* 541:541 */      ensureIndex(to);
/* 542:542 */      this.l.removeElements(this.from + from, this.from + to);
/* 543:543 */      this.to -= to - from;
/* 544:    */    }
/* 545:    */    
/* 546:    */    public void addElements(int index, float[] a, int offset, int length)
/* 547:    */    {
/* 548:548 */      ensureIndex(index);
/* 549:549 */      this.l.addElements(this.from + index, a, offset, length);
/* 550:550 */      this.to += length;
/* 551:    */    }
/* 552:    */    
/* 553:    */    public FloatListIterator listIterator(final int index)
/* 554:    */    {
/* 555:555 */      ensureIndex(index);
/* 556:    */      
/* 557:557 */      new AbstractFloatListIterator() {
/* 558:558 */        int last = -1; int pos = index;
/* 559:    */        
/* 560:560 */        public boolean hasNext() { return this.pos < AbstractFloatList.FloatSubList.this.size(); }
/* 561:561 */        public boolean hasPrevious() { return this.pos > 0; }
/* 562:562 */        public float nextFloat() { if (!hasNext()) throw new NoSuchElementException(); return AbstractFloatList.FloatSubList.this.l.getFloat(AbstractFloatList.FloatSubList.this.from + (this.last = this.pos++)); }
/* 563:563 */        public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractFloatList.FloatSubList.this.l.getFloat(AbstractFloatList.FloatSubList.this.from + (this.last = --this.pos)); }
/* 564:564 */        public int nextIndex() { return this.pos; }
/* 565:565 */        public int previousIndex() { return this.pos - 1; }
/* 566:    */        
/* 567:567 */        public void add(float k) { if (this.last == -1) throw new IllegalStateException();
/* 568:568 */          AbstractFloatList.FloatSubList.this.add(this.pos++, k);
/* 569:569 */          this.last = -1;
/* 570:    */        }
/* 571:    */        
/* 572:    */        public void set(float k) {
/* 573:573 */          if (this.last == -1) throw new IllegalStateException();
/* 574:574 */          AbstractFloatList.FloatSubList.this.set(this.last, k);
/* 575:    */        }
/* 576:    */        
/* 577:577 */        public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 578:578 */          AbstractFloatList.FloatSubList.this.removeFloat(this.last);
/* 579:    */          
/* 580:580 */          if (this.last < this.pos) this.pos -= 1;
/* 581:581 */          this.last = -1;
/* 582:    */        }
/* 583:    */      };
/* 584:    */    }
/* 585:    */    
/* 586:    */    public FloatList subList(int from, int to)
/* 587:    */    {
/* 588:588 */      ensureIndex(from);
/* 589:589 */      ensureIndex(to);
/* 590:590 */      if (from > to) { throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 591:    */      }
/* 592:592 */      return new FloatSubList(this, from, to);
/* 593:    */    }
/* 594:    */    
/* 596:    */    public boolean rem(float k)
/* 597:    */    {
/* 598:598 */      int index = indexOf(k);
/* 599:599 */      if (index == -1) return false;
/* 600:600 */      this.to -= 1;
/* 601:601 */      this.l.removeFloat(this.from + index);
/* 602:    */      
/* 603:603 */      return true;
/* 604:    */    }
/* 605:    */    
/* 606:    */    public boolean remove(Object o) {
/* 607:607 */      return rem(((Float)o).floatValue());
/* 608:    */    }
/* 609:    */    
/* 610:    */    public boolean addAll(int index, FloatCollection c) {
/* 611:611 */      ensureIndex(index);
/* 612:612 */      this.to += c.size();
/* 613:    */      
/* 618:618 */      return this.l.addAll(this.from + index, c);
/* 619:    */    }
/* 620:    */    
/* 621:    */    public boolean addAll(int index, FloatList l) {
/* 622:622 */      ensureIndex(index);
/* 623:623 */      this.to += l.size();
/* 624:    */      
/* 629:629 */      return this.l.addAll(this.from + index, l);
/* 630:    */    }
/* 631:    */  }
/* 632:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloatList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */