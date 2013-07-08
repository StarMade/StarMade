/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Stack;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.ListIterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */
/*  56:    */public abstract class AbstractObjectList<K>
/*  57:    */  extends AbstractObjectCollection<K>
/*  58:    */  implements ObjectList<K>, Stack<K>
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
/*  75: 75 */  public void add(int index, K k) { throw new UnsupportedOperationException(); }
/*  76:    */  
/*  77:    */  public boolean add(K k) {
/*  78: 78 */    add(size(), k);
/*  79: 79 */    return true;
/*  80:    */  }
/*  81:    */  
/*  82: 82 */  public K remove(int i) { throw new UnsupportedOperationException(); }
/*  83:    */  
/*  85: 85 */  public K set(int index, K k) { throw new UnsupportedOperationException(); }
/*  86:    */  
/*  87:    */  public boolean addAll(int index, Collection<? extends K> c) {
/*  88: 88 */    ensureIndex(index);
/*  89: 89 */    int n = c.size();
/*  90: 90 */    if (n == 0) return false;
/*  91: 91 */    Iterator<? extends K> i = c.iterator();
/*  92: 92 */    while (n-- != 0) add(index++, i.next());
/*  93: 93 */    return true;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public boolean addAll(Collection<? extends K> c) {
/*  97: 97 */    return addAll(size(), c);
/*  98:    */  }
/*  99:    */  
/* 100:    */  @Deprecated
/* 101:    */  public ObjectListIterator<K> objectListIterator() {
/* 102:102 */    return listIterator();
/* 103:    */  }
/* 104:    */  
/* 105:    */  @Deprecated
/* 106:    */  public ObjectListIterator<K> objectListIterator(int index) {
/* 107:107 */    return listIterator(index);
/* 108:    */  }
/* 109:    */  
/* 110:110 */  public ObjectListIterator<K> iterator() { return listIterator(); }
/* 111:    */  
/* 112:    */  public ObjectListIterator<K> listIterator() {
/* 113:113 */    return listIterator(0);
/* 114:    */  }
/* 115:    */  
/* 116:116 */  public ObjectListIterator<K> listIterator(final int index) { new AbstractObjectListIterator() {
/* 117:117 */      int last = -1; int pos = index;
/* 118:118 */      public boolean hasNext() { return this.pos < AbstractObjectList.this.size(); }
/* 119:119 */      public boolean hasPrevious() { return this.pos > 0; }
/* 120:120 */      public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractObjectList.this.get(this.last = this.pos++); }
/* 121:121 */      public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractObjectList.this.get(this.last = --this.pos); }
/* 122:122 */      public int nextIndex() { return this.pos; }
/* 123:123 */      public int previousIndex() { return this.pos - 1; }
/* 124:    */      
/* 125:125 */      public void add(K k) { if (this.last == -1) throw new IllegalStateException();
/* 126:126 */        AbstractObjectList.this.add(this.pos++, k);
/* 127:127 */        this.last = -1;
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void set(K k) { if (this.last == -1) throw new IllegalStateException();
/* 131:131 */        AbstractObjectList.this.set(this.last, k);
/* 132:    */      }
/* 133:    */      
/* 134:134 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 135:135 */        AbstractObjectList.this.remove(this.last);
/* 136:    */        
/* 137:137 */        if (this.last < this.pos) this.pos -= 1;
/* 138:138 */        this.last = -1;
/* 139:    */      }
/* 140:    */    }; }
/* 141:    */  
/* 144:    */  public boolean contains(Object k)
/* 145:    */  {
/* 146:146 */    return indexOf(k) >= 0;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public int indexOf(Object k) {
/* 150:150 */    ObjectListIterator<K> i = listIterator();
/* 151:152 */    for (; 
/* 152:152 */        i.hasNext(); 
/* 153:    */        
/* 154:154 */        return i.previousIndex())
/* 155:    */    {
/* 156:    */      label5:
/* 157:153 */      K e = i.next();
/* 158:154 */      if (k == null ? e != null : !k.equals(e)) break label5;
/* 159:    */    }
/* 160:156 */    return -1;
/* 161:    */  }
/* 162:    */  
/* 163:    */  public int lastIndexOf(Object k) {
/* 164:160 */    ObjectListIterator<K> i = listIterator(size());
/* 165:162 */    for (; 
/* 166:162 */        i.hasPrevious(); 
/* 167:    */        
/* 168:164 */        return i.nextIndex())
/* 169:    */    {
/* 170:    */      label9:
/* 171:163 */      K e = i.previous();
/* 172:164 */      if (k == null ? e != null : !k.equals(e)) break label9;
/* 173:    */    }
/* 174:166 */    return -1;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public void size(int size) {
/* 178:170 */    int i = size();
/* 179:171 */    for (size <= i; i++ < size; add(null)) {}
/* 180:172 */    while (i-- != size) remove(i);
/* 181:    */  }
/* 182:    */  
/* 183:    */  public ObjectList<K> subList(int from, int to)
/* 184:    */  {
/* 185:177 */    ensureIndex(from);
/* 186:178 */    ensureIndex(to);
/* 187:179 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 188:    */    }
/* 189:181 */    return new ObjectSubList(this, from, to);
/* 190:    */  }
/* 191:    */  
/* 193:    */  @Deprecated
/* 194:    */  public ObjectList<K> objectSubList(int from, int to)
/* 195:    */  {
/* 196:188 */    return subList(from, to);
/* 197:    */  }
/* 198:    */  
/* 208:    */  public void removeElements(int from, int to)
/* 209:    */  {
/* 210:202 */    ensureIndex(to);
/* 211:203 */    ObjectListIterator<K> i = listIterator(from);
/* 212:204 */    int n = to - from;
/* 213:205 */    if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 214:206 */    while (n-- != 0) {
/* 215:207 */      i.next();
/* 216:208 */      i.remove();
/* 217:    */    }
/* 218:    */  }
/* 219:    */  
/* 230:    */  public void addElements(int index, K[] a, int offset, int length)
/* 231:    */  {
/* 232:224 */    ensureIndex(index);
/* 233:225 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 234:226 */    if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 235:227 */    while (length-- != 0) add(index++, a[(offset++)]);
/* 236:    */  }
/* 237:    */  
/* 238:    */  public void addElements(int index, K[] a) {
/* 239:231 */    addElements(index, a, 0, a.length);
/* 240:    */  }
/* 241:    */  
/* 252:    */  public void getElements(int from, Object[] a, int offset, int length)
/* 253:    */  {
/* 254:246 */    ObjectListIterator<K> i = listIterator(from);
/* 255:247 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 256:248 */    if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 257:249 */    if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 258:250 */    while (length-- != 0) a[(offset++)] = i.next();
/* 259:    */  }
/* 260:    */  
/* 261:    */  private boolean valEquals(Object a, Object b)
/* 262:    */  {
/* 263:255 */    return a == null ? false : b == null ? true : a.equals(b);
/* 264:    */  }
/* 265:    */  
/* 266:    */  public boolean equals(Object o)
/* 267:    */  {
/* 268:260 */    if (o == this) return true;
/* 269:261 */    if (!(o instanceof List)) return false;
/* 270:262 */    List<?> l = (List)o;
/* 271:263 */    int s = size();
/* 272:264 */    if (s != l.size()) { return false;
/* 273:    */    }
/* 274:266 */    ListIterator<?> i1 = listIterator();ListIterator<?> i2 = l.listIterator();
/* 275:    */    
/* 279:271 */    while (s-- != 0) if (!valEquals(i1.next(), i2.next())) { return false;
/* 280:    */      }
/* 281:273 */    return true;
/* 282:    */  }
/* 283:    */  
/* 296:    */  public int compareTo(List<? extends K> l)
/* 297:    */  {
/* 298:290 */    if (l == this) { return 0;
/* 299:    */    }
/* 300:292 */    if ((l instanceof ObjectList))
/* 301:    */    {
/* 302:294 */      ObjectListIterator<K> i1 = listIterator();ObjectListIterator<K> i2 = ((ObjectList)l).listIterator();
/* 303:    */      
/* 306:298 */      while ((i1.hasNext()) && (i2.hasNext())) {
/* 307:299 */        K e1 = i1.next();
/* 308:300 */        K e2 = i2.next();
/* 309:301 */        int r; if ((r = ((Comparable)e1).compareTo(e2)) != 0) return r;
/* 310:    */      }
/* 311:303 */      return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 312:    */    }
/* 313:    */    
/* 314:306 */    ListIterator<? extends K> i1 = listIterator();ListIterator<? extends K> i2 = l.listIterator();
/* 315:    */    
/* 317:309 */    while ((i1.hasNext()) && (i2.hasNext())) { int r;
/* 318:310 */      if ((r = ((Comparable)i1.next()).compareTo(i2.next())) != 0) return r;
/* 319:    */    }
/* 320:312 */    return i1.hasNext() ? 1 : i2.hasNext() ? -1 : 0;
/* 321:    */  }
/* 322:    */  
/* 327:    */  public int hashCode()
/* 328:    */  {
/* 329:321 */    ObjectIterator<K> i = iterator();
/* 330:322 */    int h = 1;int s = size();
/* 331:323 */    while (s-- != 0) {
/* 332:324 */      K k = i.next();
/* 333:325 */      h = 31 * h + (k == null ? 0 : k.hashCode());
/* 334:    */    }
/* 335:327 */    return h;
/* 336:    */  }
/* 337:    */  
/* 338:    */  public void push(K o)
/* 339:    */  {
/* 340:332 */    add(o);
/* 341:    */  }
/* 342:    */  
/* 343:    */  public K pop() {
/* 344:336 */    if (isEmpty()) throw new NoSuchElementException();
/* 345:337 */    return remove(size() - 1);
/* 346:    */  }
/* 347:    */  
/* 348:    */  public K top() {
/* 349:341 */    if (isEmpty()) throw new NoSuchElementException();
/* 350:342 */    return get(size() - 1);
/* 351:    */  }
/* 352:    */  
/* 354:346 */  public K peek(int i) { return get(size() - 1 - i); }
/* 355:    */  
/* 356:    */  public String toString() {
/* 357:349 */    StringBuilder s = new StringBuilder();
/* 358:350 */    ObjectIterator<K> i = iterator();
/* 359:351 */    int n = size();
/* 360:    */    
/* 361:353 */    boolean first = true;
/* 362:354 */    s.append("[");
/* 363:355 */    while (n-- != 0) {
/* 364:356 */      if (first) first = false; else
/* 365:357 */        s.append(", ");
/* 366:358 */      K k = i.next();
/* 367:359 */      if (this == k) s.append("(this list)"); else
/* 368:360 */        s.append(String.valueOf(k));
/* 369:    */    }
/* 370:362 */    s.append("]");
/* 371:363 */    return s.toString();
/* 372:    */  }
/* 373:    */  
/* 374:    */  public static class ObjectSubList<K> extends AbstractObjectList<K> implements Serializable
/* 375:    */  {
/* 376:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 377:    */    protected final ObjectList<K> l;
/* 378:    */    protected final int from;
/* 379:    */    protected int to;
/* 380:    */    private static final boolean ASSERTS = false;
/* 381:    */    
/* 382:    */    public ObjectSubList(ObjectList<K> l, int from, int to) {
/* 383:375 */      this.l = l;
/* 384:376 */      this.from = from;
/* 385:377 */      this.to = to;
/* 386:    */    }
/* 387:    */    
/* 390:    */    private void assertRange() {}
/* 391:    */    
/* 393:    */    public boolean add(K k)
/* 394:    */    {
/* 395:387 */      this.l.add(this.to, k);
/* 396:388 */      this.to += 1;
/* 397:    */      
/* 398:390 */      return true;
/* 399:    */    }
/* 400:    */    
/* 401:393 */    public void add(int index, K k) { ensureIndex(index);
/* 402:394 */      this.l.add(this.from + index, k);
/* 403:395 */      this.to += 1;
/* 404:    */    }
/* 405:    */    
/* 406:    */    public boolean addAll(int index, Collection<? extends K> c) {
/* 407:399 */      ensureIndex(index);
/* 408:400 */      this.to += c.size();
/* 409:    */      
/* 414:406 */      return this.l.addAll(this.from + index, c);
/* 415:    */    }
/* 416:    */    
/* 417:409 */    public K get(int index) { ensureRestrictedIndex(index);
/* 418:410 */      return this.l.get(this.from + index);
/* 419:    */    }
/* 420:    */    
/* 421:413 */    public K remove(int index) { ensureRestrictedIndex(index);
/* 422:414 */      this.to -= 1;
/* 423:415 */      return this.l.remove(this.from + index);
/* 424:    */    }
/* 425:    */    
/* 426:418 */    public K set(int index, K k) { ensureRestrictedIndex(index);
/* 427:419 */      return this.l.set(this.from + index, k);
/* 428:    */    }
/* 429:    */    
/* 430:422 */    public void clear() { removeElements(0, size()); }
/* 431:    */    
/* 434:426 */    public int size() { return this.to - this.from; }
/* 435:    */    
/* 436:    */    public void getElements(int from, Object[] a, int offset, int length) {
/* 437:429 */      ensureIndex(from);
/* 438:430 */      if (from + length > size()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
/* 439:431 */      this.l.getElements(this.from + from, a, offset, length);
/* 440:    */    }
/* 441:    */    
/* 442:434 */    public void removeElements(int from, int to) { ensureIndex(from);
/* 443:435 */      ensureIndex(to);
/* 444:436 */      this.l.removeElements(this.from + from, this.from + to);
/* 445:437 */      this.to -= to - from;
/* 446:    */    }
/* 447:    */    
/* 448:    */    public void addElements(int index, K[] a, int offset, int length) {
/* 449:441 */      ensureIndex(index);
/* 450:442 */      this.l.addElements(this.from + index, a, offset, length);
/* 451:443 */      this.to += length;
/* 452:    */    }
/* 453:    */    
/* 454:    */    public ObjectListIterator<K> listIterator(final int index) {
/* 455:447 */      ensureIndex(index);
/* 456:448 */      new AbstractObjectListIterator() {
/* 457:449 */        int last = -1; int pos = index;
/* 458:450 */        public boolean hasNext() { return this.pos < AbstractObjectList.ObjectSubList.this.size(); }
/* 459:451 */        public boolean hasPrevious() { return this.pos > 0; }
/* 460:452 */        public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractObjectList.ObjectSubList.this.l.get(AbstractObjectList.ObjectSubList.this.from + (this.last = this.pos++)); }
/* 461:453 */        public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractObjectList.ObjectSubList.this.l.get(AbstractObjectList.ObjectSubList.this.from + (this.last = --this.pos)); }
/* 462:454 */        public int nextIndex() { return this.pos; }
/* 463:455 */        public int previousIndex() { return this.pos - 1; }
/* 464:    */        
/* 465:457 */        public void add(K k) { if (this.last == -1) throw new IllegalStateException();
/* 466:458 */          AbstractObjectList.ObjectSubList.this.add(this.pos++, k);
/* 467:459 */          this.last = -1;
/* 468:    */        }
/* 469:    */        
/* 470:    */        public void set(K k) {
/* 471:463 */          if (this.last == -1) throw new IllegalStateException();
/* 472:464 */          AbstractObjectList.ObjectSubList.this.set(this.last, k);
/* 473:    */        }
/* 474:    */        
/* 475:467 */        public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 476:468 */          AbstractObjectList.ObjectSubList.this.remove(this.last);
/* 477:    */          
/* 478:470 */          if (this.last < this.pos) this.pos -= 1;
/* 479:471 */          this.last = -1;
/* 480:    */        }
/* 481:    */      };
/* 482:    */    }
/* 483:    */    
/* 484:    */    public ObjectList<K> subList(int from, int to) {
/* 485:477 */      ensureIndex(from);
/* 486:478 */      ensureIndex(to);
/* 487:479 */      if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 488:480 */      return new ObjectSubList(this, from, to);
/* 489:    */    }
/* 490:    */    
/* 491:    */    public boolean remove(Object o) {
/* 492:484 */      int index = indexOf(o);
/* 493:485 */      if (index == -1) return false;
/* 494:486 */      remove(index);
/* 495:487 */      return true;
/* 496:    */    }
/* 497:    */  }
/* 498:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObjectList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */