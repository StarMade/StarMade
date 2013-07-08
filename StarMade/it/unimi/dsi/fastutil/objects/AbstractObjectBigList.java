/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigList;
/*   4:    */import it.unimi.dsi.fastutil.BigListIterator;
/*   5:    */import it.unimi.dsi.fastutil.Stack;
/*   6:    */import java.io.Serializable;
/*   7:    */import java.util.Collection;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  53:    */public abstract class AbstractObjectBigList<K>
/*  54:    */  extends AbstractObjectCollection<K>
/*  55:    */  implements ObjectBigList<K>, Stack<K>
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
/*  72: 72 */  public void add(long index, K k) { throw new UnsupportedOperationException(); }
/*  73:    */  
/*  74:    */  public boolean add(K k) {
/*  75: 75 */    add(size64(), k);
/*  76: 76 */    return true;
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public K remove(long i) { throw new UnsupportedOperationException(); }
/*  80:    */  
/*  81:    */  public K remove(int i) {
/*  82: 82 */    return remove(i);
/*  83:    */  }
/*  84:    */  
/*  85: 85 */  public K set(long index, K k) { throw new UnsupportedOperationException(); }
/*  86:    */  
/*  88: 88 */  public K set(int index, K k) { return set(index, k); }
/*  89:    */  
/*  90:    */  public boolean addAll(long index, Collection<? extends K> c) {
/*  91: 91 */    ensureIndex(index);
/*  92: 92 */    int n = c.size();
/*  93: 93 */    if (n == 0) return false;
/*  94: 94 */    Iterator<? extends K> i = c.iterator();
/*  95: 95 */    while (n-- != 0) add(index++, i.next());
/*  96: 96 */    return true;
/*  97:    */  }
/*  98:    */  
/*  99: 99 */  public boolean addAll(int index, Collection<? extends K> c) { return addAll(index, c); }
/* 100:    */  
/* 101:    */  public boolean addAll(Collection<? extends K> c)
/* 102:    */  {
/* 103:103 */    return addAll(size64(), c);
/* 104:    */  }
/* 105:    */  
/* 106:106 */  public ObjectBigListIterator<K> iterator() { return listIterator(); }
/* 107:    */  
/* 108:    */  public ObjectBigListIterator<K> listIterator() {
/* 109:109 */    return listIterator(0L);
/* 110:    */  }
/* 111:    */  
/* 112:112 */  public ObjectBigListIterator<K> listIterator(final long index) { new AbstractObjectBigListIterator() {
/* 113:113 */      long last = -1L; long pos = index;
/* 114:114 */      public boolean hasNext() { return this.pos < AbstractObjectBigList.this.size64(); }
/* 115:115 */      public boolean hasPrevious() { return this.pos > 0L; }
/* 116:116 */      public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractObjectBigList.this.get(this.last = this.pos++); }
/* 117:117 */      public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractObjectBigList.this.get(this.last = --this.pos); }
/* 118:118 */      public long nextIndex() { return this.pos; }
/* 119:119 */      public long previousIndex() { return this.pos - 1L; }
/* 120:    */      
/* 121:121 */      public void add(K k) { if (this.last == -1L) throw new IllegalStateException();
/* 122:122 */        AbstractObjectBigList.this.add(this.pos++, k);
/* 123:123 */        this.last = -1L;
/* 124:    */      }
/* 125:    */      
/* 126:126 */      public void set(K k) { if (this.last == -1L) throw new IllegalStateException();
/* 127:127 */        AbstractObjectBigList.this.set(this.last, k);
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 131:131 */        AbstractObjectBigList.this.remove(this.last);
/* 132:    */        
/* 133:133 */        if (this.last < this.pos) this.pos -= 1L;
/* 134:134 */        this.last = -1L;
/* 135:    */      }
/* 136:    */    }; }
/* 137:    */  
/* 139:    */  public ObjectBigListIterator<K> listIterator(int index)
/* 140:    */  {
/* 141:141 */    return listIterator(index);
/* 142:    */  }
/* 143:    */  
/* 144:    */  public boolean contains(Object k)
/* 145:    */  {
/* 146:146 */    return indexOf(k) >= 0L;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public long indexOf(Object k) {
/* 150:150 */    ObjectBigListIterator<K> i = listIterator();
/* 151:152 */    for (; 
/* 152:152 */        i.hasNext(); 
/* 153:    */        
/* 154:154 */        return i.previousIndex())
/* 155:    */    {
/* 156:    */      label5:
/* 157:153 */      K e = i.next();
/* 158:154 */      if (k == null ? e != null : !k.equals(e)) break label5;
/* 159:    */    }
/* 160:156 */    return -1L;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public long lastIndexOf(Object k) {
/* 164:160 */    ObjectBigListIterator<K> i = listIterator(size64());
/* 165:162 */    for (; 
/* 166:162 */        i.hasPrevious(); 
/* 167:    */        
/* 168:164 */        return i.nextIndex())
/* 169:    */    {
/* 170:    */      label9:
/* 171:163 */      K e = i.previous();
/* 172:164 */      if (k == null ? e != null : !k.equals(e)) break label9;
/* 173:    */    }
/* 174:166 */    return -1L;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public void size(long size) {
/* 178:170 */    long i = size64();
/* 179:171 */    for (size <= i; i++ < size; add(null)) {}
/* 180:172 */    while (i-- != size) remove(i);
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void size(int size) {
/* 184:176 */    size(size);
/* 185:    */  }
/* 186:    */  
/* 187:    */  public ObjectBigList<K> subList(long from, long to) {
/* 188:180 */    ensureIndex(from);
/* 189:181 */    ensureIndex(to);
/* 190:182 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 191:    */    }
/* 192:184 */    return new ObjectSubList(this, from, to);
/* 193:    */  }
/* 194:    */  
/* 203:    */  public void removeElements(long from, long to)
/* 204:    */  {
/* 205:197 */    ensureIndex(to);
/* 206:198 */    ObjectBigListIterator<K> i = listIterator(from);
/* 207:199 */    long n = to - from;
/* 208:200 */    if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 209:201 */    while (n-- != 0L) {
/* 210:202 */      i.next();
/* 211:203 */      i.remove();
/* 212:    */    }
/* 213:    */  }
/* 214:    */  
/* 225:    */  public void addElements(long index, K[][] a, long offset, long length)
/* 226:    */  {
/* 227:219 */    ensureIndex(index);
/* 228:220 */    ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 229:221 */    while (length-- != 0L) add(index++, ObjectBigArrays.get(a, offset++));
/* 230:    */  }
/* 231:    */  
/* 232:    */  public void addElements(long index, K[][] a) {
/* 233:225 */    addElements(index, a, 0L, ObjectBigArrays.length(a));
/* 234:    */  }
/* 235:    */  
/* 246:    */  public void getElements(long from, Object[][] a, long offset, long length)
/* 247:    */  {
/* 248:240 */    ObjectBigListIterator<K> i = listIterator(from);
/* 249:241 */    ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 250:242 */    if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 251:243 */    while (length-- != 0L) ObjectBigArrays.set(a, offset++, i.next());
/* 252:    */  }
/* 253:    */  
/* 254:    */  @Deprecated
/* 255:    */  public int size() {
/* 256:248 */    return (int)Math.min(2147483647L, size64());
/* 257:    */  }
/* 258:    */  
/* 259:    */  private boolean valEquals(Object a, Object b)
/* 260:    */  {
/* 261:253 */    return a == null ? false : b == null ? true : a.equals(b);
/* 262:    */  }
/* 263:    */  
/* 265:    */  public boolean equals(Object o)
/* 266:    */  {
/* 267:259 */    if (o == this) return true;
/* 268:260 */    if (!(o instanceof BigList)) return false;
/* 269:261 */    BigList<?> l = (BigList)o;
/* 270:262 */    long s = size64();
/* 271:263 */    if (s != l.size64()) { return false;
/* 272:    */    }
/* 273:265 */    BigListIterator<?> i1 = listIterator();BigListIterator<?> i2 = l.listIterator();
/* 274:    */    
/* 278:270 */    while (s-- != 0L) if (!valEquals(i1.next(), i2.next())) { return false;
/* 279:    */      }
/* 280:272 */    return true;
/* 281:    */  }
/* 282:    */  
/* 295:    */  public int compareTo(BigList<? extends K> l)
/* 296:    */  {
/* 297:289 */    if (l == this) { return 0;
/* 298:    */    }
/* 299:291 */    if ((l instanceof ObjectBigList))
/* 300:    */    {
/* 301:293 */      ObjectBigListIterator<K> i1 = listIterator();ObjectBigListIterator<K> i2 = ((ObjectBigList)l).listIterator();
/* 302:    */      
/* 305:297 */      while ((i1.hasNext()) && (i2.hasNext())) {
/* 306:298 */        K e1 = i1.next();
/* 307:299 */        K e2 = i2.next();
/* 308:300 */        int r; if ((r = ((Comparable)e1).compareTo(e2)) != 0) return r;
/* 309:    */      }
/* 310:302 */      return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 311:    */    }
/* 312:    */    
/* 313:305 */    BigListIterator<? extends K> i1 = listIterator();BigListIterator<? extends K> i2 = l.listIterator();
/* 314:    */    
/* 316:308 */    while ((i1.hasNext()) && (i2.hasNext())) { int r;
/* 317:309 */      if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/* 318:    */    }
/* 319:311 */    return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 320:    */  }
/* 321:    */  
/* 326:    */  public int hashCode()
/* 327:    */  {
/* 328:320 */    ObjectIterator<K> i = iterator();
/* 329:321 */    int h = 1;
/* 330:322 */    long s = size64();
/* 331:323 */    while (s-- != 0L) {
/* 332:324 */      K k = i.next();
/* 333:325 */      h = 31 * h + (k == null ? 0 : k.hashCode());
/* 334:    */    }
/* 335:327 */    return h;
/* 336:    */  }
/* 337:    */  
/* 338:    */  public void push(K o) {
/* 339:331 */    add(o);
/* 340:    */  }
/* 341:    */  
/* 342:    */  public K pop() {
/* 343:335 */    if (isEmpty()) throw new NoSuchElementException();
/* 344:336 */    return remove(size64() - 1L);
/* 345:    */  }
/* 346:    */  
/* 347:    */  public K top() {
/* 348:340 */    if (isEmpty()) throw new NoSuchElementException();
/* 349:341 */    return get(size64() - 1L);
/* 350:    */  }
/* 351:    */  
/* 352:    */  public K peek(int i) {
/* 353:345 */    return get(size64() - 1L - i);
/* 354:    */  }
/* 355:    */  
/* 356:348 */  public K get(int index) { return get(index); }
/* 357:    */  
/* 358:    */  public String toString() {
/* 359:351 */    StringBuilder s = new StringBuilder();
/* 360:352 */    ObjectIterator<K> i = iterator();
/* 361:353 */    long n = size64();
/* 362:    */    
/* 363:355 */    boolean first = true;
/* 364:356 */    s.append("[");
/* 365:357 */    while (n-- != 0L) {
/* 366:358 */      if (first) first = false; else
/* 367:359 */        s.append(", ");
/* 368:360 */      K k = i.next();
/* 369:361 */      if (this == k) s.append("(this big list)"); else
/* 370:362 */        s.append(String.valueOf(k));
/* 371:    */    }
/* 372:364 */    s.append("]");
/* 373:365 */    return s.toString();
/* 374:    */  }
/* 375:    */  
/* 376:    */  public static class ObjectSubList<K> extends AbstractObjectBigList<K> implements Serializable
/* 377:    */  {
/* 378:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 379:    */    protected final ObjectBigList<K> l;
/* 380:    */    protected final long from;
/* 381:    */    protected long to;
/* 382:    */    private static final boolean ASSERTS = false;
/* 383:    */    
/* 384:    */    public ObjectSubList(ObjectBigList<K> l, long from, long to) {
/* 385:377 */      this.l = l;
/* 386:378 */      this.from = from;
/* 387:379 */      this.to = to;
/* 388:    */    }
/* 389:    */    
/* 392:    */    private void assertRange() {}
/* 393:    */    
/* 395:    */    public boolean add(K k)
/* 396:    */    {
/* 397:389 */      this.l.add(this.to, k);
/* 398:390 */      this.to += 1L;
/* 399:    */      
/* 400:392 */      return true;
/* 401:    */    }
/* 402:    */    
/* 403:395 */    public void add(long index, K k) { ensureIndex(index);
/* 404:396 */      this.l.add(this.from + index, k);
/* 405:397 */      this.to += 1L;
/* 406:    */    }
/* 407:    */    
/* 408:    */    public boolean addAll(long index, Collection<? extends K> c) {
/* 409:401 */      ensureIndex(index);
/* 410:402 */      this.to += c.size();
/* 411:    */      
/* 416:408 */      return this.l.addAll(this.from + index, c);
/* 417:    */    }
/* 418:    */    
/* 419:411 */    public K get(long index) { ensureRestrictedIndex(index);
/* 420:412 */      return this.l.get(this.from + index);
/* 421:    */    }
/* 422:    */    
/* 423:415 */    public K remove(long index) { ensureRestrictedIndex(index);
/* 424:416 */      this.to -= 1L;
/* 425:417 */      return this.l.remove(this.from + index);
/* 426:    */    }
/* 427:    */    
/* 428:420 */    public K set(long index, K k) { ensureRestrictedIndex(index);
/* 429:421 */      return this.l.set(this.from + index, k);
/* 430:    */    }
/* 431:    */    
/* 432:424 */    public void clear() { removeElements(0L, size64()); }
/* 433:    */    
/* 436:428 */    public long size64() { return this.to - this.from; }
/* 437:    */    
/* 438:    */    public void getElements(long from, Object[][] a, long offset, long length) {
/* 439:431 */      ensureIndex(from);
/* 440:432 */      if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
/* 441:433 */      this.l.getElements(this.from + from, a, offset, length);
/* 442:    */    }
/* 443:    */    
/* 444:436 */    public void removeElements(long from, long to) { ensureIndex(from);
/* 445:437 */      ensureIndex(to);
/* 446:438 */      this.l.removeElements(this.from + from, this.from + to);
/* 447:439 */      this.to -= to - from;
/* 448:    */    }
/* 449:    */    
/* 450:    */    public void addElements(long index, K[][] a, long offset, long length) {
/* 451:443 */      ensureIndex(index);
/* 452:444 */      this.l.addElements(this.from + index, a, offset, length);
/* 453:445 */      this.to += length;
/* 454:    */    }
/* 455:    */    
/* 456:    */    public ObjectBigListIterator<K> listIterator(final long index) {
/* 457:449 */      ensureIndex(index);
/* 458:450 */      new AbstractObjectBigListIterator() {
/* 459:451 */        long last = -1L; long pos = index;
/* 460:452 */        public boolean hasNext() { return this.pos < AbstractObjectBigList.ObjectSubList.this.size64(); }
/* 461:453 */        public boolean hasPrevious() { return this.pos > 0L; }
/* 462:454 */        public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractObjectBigList.ObjectSubList.this.l.get(AbstractObjectBigList.ObjectSubList.this.from + (this.last = this.pos++)); }
/* 463:455 */        public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractObjectBigList.ObjectSubList.this.l.get(AbstractObjectBigList.ObjectSubList.this.from + (this.last = --this.pos)); }
/* 464:456 */        public long nextIndex() { return this.pos; }
/* 465:457 */        public long previousIndex() { return this.pos - 1L; }
/* 466:    */        
/* 467:459 */        public void add(K k) { if (this.last == -1L) throw new IllegalStateException();
/* 468:460 */          AbstractObjectBigList.ObjectSubList.this.add(this.pos++, k);
/* 469:461 */          this.last = -1L;
/* 470:    */        }
/* 471:    */        
/* 472:    */        public void set(K k) {
/* 473:465 */          if (this.last == -1L) throw new IllegalStateException();
/* 474:466 */          AbstractObjectBigList.ObjectSubList.this.set(this.last, k);
/* 475:    */        }
/* 476:    */        
/* 477:469 */        public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 478:470 */          AbstractObjectBigList.ObjectSubList.this.remove(this.last);
/* 479:    */          
/* 480:472 */          if (this.last < this.pos) this.pos -= 1L;
/* 481:473 */          this.last = -1L;
/* 482:    */        }
/* 483:    */      };
/* 484:    */    }
/* 485:    */    
/* 486:    */    public ObjectBigList<K> subList(long from, long to) {
/* 487:479 */      ensureIndex(from);
/* 488:480 */      ensureIndex(to);
/* 489:481 */      if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 490:482 */      return new ObjectSubList(this, from, to);
/* 491:    */    }
/* 492:    */    
/* 493:    */    public boolean remove(Object o) {
/* 494:486 */      long index = indexOf(o);
/* 495:487 */      if (index == -1L) return false;
/* 496:488 */      remove(index);
/* 497:489 */      return true;
/* 498:    */    }
/* 499:    */  }
/* 500:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */