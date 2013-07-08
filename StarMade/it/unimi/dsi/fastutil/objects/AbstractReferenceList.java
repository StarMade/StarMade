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
/*  56:    */public abstract class AbstractReferenceList<K>
/*  57:    */  extends AbstractReferenceCollection<K>
/*  58:    */  implements ReferenceList<K>, Stack<K>
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
/* 118:118 */      public boolean hasNext() { return this.pos < AbstractReferenceList.this.size(); }
/* 119:119 */      public boolean hasPrevious() { return this.pos > 0; }
/* 120:120 */      public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceList.this.get(this.last = this.pos++); }
/* 121:121 */      public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceList.this.get(this.last = --this.pos); }
/* 122:122 */      public int nextIndex() { return this.pos; }
/* 123:123 */      public int previousIndex() { return this.pos - 1; }
/* 124:    */      
/* 125:125 */      public void add(K k) { if (this.last == -1) throw new IllegalStateException();
/* 126:126 */        AbstractReferenceList.this.add(this.pos++, k);
/* 127:127 */        this.last = -1;
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void set(K k) { if (this.last == -1) throw new IllegalStateException();
/* 131:131 */        AbstractReferenceList.this.set(this.last, k);
/* 132:    */      }
/* 133:    */      
/* 134:134 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 135:135 */        AbstractReferenceList.this.remove(this.last);
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
/* 151:    */    
/* 152:152 */    while (i.hasNext()) {
/* 153:153 */      K e = i.next();
/* 154:154 */      if (k == e) return i.previousIndex();
/* 155:    */    }
/* 156:156 */    return -1;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public int lastIndexOf(Object k) {
/* 160:160 */    ObjectListIterator<K> i = listIterator(size());
/* 161:    */    
/* 162:162 */    while (i.hasPrevious()) {
/* 163:163 */      K e = i.previous();
/* 164:164 */      if (k == e) return i.nextIndex();
/* 165:    */    }
/* 166:166 */    return -1;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void size(int size) {
/* 170:170 */    int i = size();
/* 171:171 */    for (size <= i; i++ < size; add(null)) {}
/* 172:172 */    while (i-- != size) remove(i);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public ReferenceList<K> subList(int from, int to)
/* 176:    */  {
/* 177:177 */    ensureIndex(from);
/* 178:178 */    ensureIndex(to);
/* 179:179 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 180:    */    }
/* 181:181 */    return new ReferenceSubList(this, from, to);
/* 182:    */  }
/* 183:    */  
/* 185:    */  @Deprecated
/* 186:    */  public ReferenceList<K> referenceSubList(int from, int to)
/* 187:    */  {
/* 188:188 */    return subList(from, to);
/* 189:    */  }
/* 190:    */  
/* 200:    */  public void removeElements(int from, int to)
/* 201:    */  {
/* 202:202 */    ensureIndex(to);
/* 203:203 */    ObjectListIterator<K> i = listIterator(from);
/* 204:204 */    int n = to - from;
/* 205:205 */    if (n < 0) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 206:206 */    while (n-- != 0) {
/* 207:207 */      i.next();
/* 208:208 */      i.remove();
/* 209:    */    }
/* 210:    */  }
/* 211:    */  
/* 222:    */  public void addElements(int index, K[] a, int offset, int length)
/* 223:    */  {
/* 224:224 */    ensureIndex(index);
/* 225:225 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 226:226 */    if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 227:227 */    while (length-- != 0) add(index++, a[(offset++)]);
/* 228:    */  }
/* 229:    */  
/* 230:    */  public void addElements(int index, K[] a) {
/* 231:231 */    addElements(index, a, 0, a.length);
/* 232:    */  }
/* 233:    */  
/* 244:    */  public void getElements(int from, Object[] a, int offset, int length)
/* 245:    */  {
/* 246:246 */    ObjectListIterator<K> i = listIterator(from);
/* 247:247 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("Offset (").append(offset).append(") is negative").toString());
/* 248:248 */    if (offset + length > a.length) throw new ArrayIndexOutOfBoundsException(new StringBuilder().append("End index (").append(offset + length).append(") is greater than array length (").append(a.length).append(")").toString());
/* 249:249 */    if (from + length > size()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size()).append(")").toString());
/* 250:250 */    while (length-- != 0) { a[(offset++)] = i.next();
/* 251:    */    }
/* 252:    */  }
/* 253:    */  
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
/* 269:269 */    while (s-- != 0) { if (i1.next() != i2.next()) { return false;
/* 270:    */      }
/* 271:    */    }
/* 272:    */    
/* 273:273 */    return true;
/* 274:    */  }
/* 275:    */  
/* 278:    */  public int hashCode()
/* 279:    */  {
/* 280:280 */    ObjectIterator<K> i = iterator();
/* 281:281 */    int h = 1;int s = size();
/* 282:282 */    while (s-- != 0) {
/* 283:283 */      K k = i.next();
/* 284:284 */      h = 31 * h + (k == null ? 0 : System.identityHashCode(k));
/* 285:    */    }
/* 286:286 */    return h;
/* 287:    */  }
/* 288:    */  
/* 289:289 */  public void push(K o) { add(o); }
/* 290:    */  
/* 291:    */  public K pop() {
/* 292:292 */    if (isEmpty()) throw new NoSuchElementException();
/* 293:293 */    return remove(size() - 1);
/* 294:    */  }
/* 295:    */  
/* 296:296 */  public K top() { if (isEmpty()) throw new NoSuchElementException();
/* 297:297 */    return get(size() - 1);
/* 298:    */  }
/* 299:    */  
/* 300:300 */  public K peek(int i) { return get(size() - 1 - i); }
/* 301:    */  
/* 302:    */  public String toString() {
/* 303:303 */    StringBuilder s = new StringBuilder();
/* 304:304 */    ObjectIterator<K> i = iterator();
/* 305:305 */    int n = size();
/* 306:    */    
/* 307:307 */    boolean first = true;
/* 308:308 */    s.append("[");
/* 309:309 */    while (n-- != 0) {
/* 310:310 */      if (first) first = false; else
/* 311:311 */        s.append(", ");
/* 312:312 */      K k = i.next();
/* 313:313 */      if (this == k) s.append("(this list)"); else
/* 314:314 */        s.append(String.valueOf(k));
/* 315:    */    }
/* 316:316 */    s.append("]");
/* 317:317 */    return s.toString();
/* 318:    */  }
/* 319:    */  
/* 320:    */  public static class ReferenceSubList<K> extends AbstractReferenceList<K> implements Serializable
/* 321:    */  {
/* 322:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 323:    */    protected final ReferenceList<K> l;
/* 324:    */    protected final int from;
/* 325:    */    protected int to;
/* 326:    */    private static final boolean ASSERTS = false;
/* 327:    */    
/* 328:    */    public ReferenceSubList(ReferenceList<K> l, int from, int to) {
/* 329:329 */      this.l = l;
/* 330:330 */      this.from = from;
/* 331:331 */      this.to = to;
/* 332:    */    }
/* 333:    */    
/* 336:    */    private void assertRange() {}
/* 337:    */    
/* 339:    */    public boolean add(K k)
/* 340:    */    {
/* 341:341 */      this.l.add(this.to, k);
/* 342:342 */      this.to += 1;
/* 343:    */      
/* 344:344 */      return true;
/* 345:    */    }
/* 346:    */    
/* 347:347 */    public void add(int index, K k) { ensureIndex(index);
/* 348:348 */      this.l.add(this.from + index, k);
/* 349:349 */      this.to += 1;
/* 350:    */    }
/* 351:    */    
/* 352:    */    public boolean addAll(int index, Collection<? extends K> c) {
/* 353:353 */      ensureIndex(index);
/* 354:354 */      this.to += c.size();
/* 355:    */      
/* 360:360 */      return this.l.addAll(this.from + index, c);
/* 361:    */    }
/* 362:    */    
/* 363:363 */    public K get(int index) { ensureRestrictedIndex(index);
/* 364:364 */      return this.l.get(this.from + index);
/* 365:    */    }
/* 366:    */    
/* 367:367 */    public K remove(int index) { ensureRestrictedIndex(index);
/* 368:368 */      this.to -= 1;
/* 369:369 */      return this.l.remove(this.from + index);
/* 370:    */    }
/* 371:    */    
/* 372:372 */    public K set(int index, K k) { ensureRestrictedIndex(index);
/* 373:373 */      return this.l.set(this.from + index, k);
/* 374:    */    }
/* 375:    */    
/* 376:376 */    public void clear() { removeElements(0, size()); }
/* 377:    */    
/* 380:380 */    public int size() { return this.to - this.from; }
/* 381:    */    
/* 382:    */    public void getElements(int from, Object[] a, int offset, int length) {
/* 383:383 */      ensureIndex(from);
/* 384:384 */      if (from + length > size()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size() + ")");
/* 385:385 */      this.l.getElements(this.from + from, a, offset, length);
/* 386:    */    }
/* 387:    */    
/* 388:388 */    public void removeElements(int from, int to) { ensureIndex(from);
/* 389:389 */      ensureIndex(to);
/* 390:390 */      this.l.removeElements(this.from + from, this.from + to);
/* 391:391 */      this.to -= to - from;
/* 392:    */    }
/* 393:    */    
/* 394:    */    public void addElements(int index, K[] a, int offset, int length) {
/* 395:395 */      ensureIndex(index);
/* 396:396 */      this.l.addElements(this.from + index, a, offset, length);
/* 397:397 */      this.to += length;
/* 398:    */    }
/* 399:    */    
/* 400:    */    public ObjectListIterator<K> listIterator(final int index) {
/* 401:401 */      ensureIndex(index);
/* 402:402 */      new AbstractObjectListIterator() {
/* 403:403 */        int last = -1; int pos = index;
/* 404:404 */        public boolean hasNext() { return this.pos < AbstractReferenceList.ReferenceSubList.this.size(); }
/* 405:405 */        public boolean hasPrevious() { return this.pos > 0; }
/* 406:406 */        public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceList.ReferenceSubList.this.l.get(AbstractReferenceList.ReferenceSubList.this.from + (this.last = this.pos++)); }
/* 407:407 */        public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceList.ReferenceSubList.this.l.get(AbstractReferenceList.ReferenceSubList.this.from + (this.last = --this.pos)); }
/* 408:408 */        public int nextIndex() { return this.pos; }
/* 409:409 */        public int previousIndex() { return this.pos - 1; }
/* 410:    */        
/* 411:411 */        public void add(K k) { if (this.last == -1) throw new IllegalStateException();
/* 412:412 */          AbstractReferenceList.ReferenceSubList.this.add(this.pos++, k);
/* 413:413 */          this.last = -1;
/* 414:    */        }
/* 415:    */        
/* 416:    */        public void set(K k) {
/* 417:417 */          if (this.last == -1) throw new IllegalStateException();
/* 418:418 */          AbstractReferenceList.ReferenceSubList.this.set(this.last, k);
/* 419:    */        }
/* 420:    */        
/* 421:421 */        public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 422:422 */          AbstractReferenceList.ReferenceSubList.this.remove(this.last);
/* 423:    */          
/* 424:424 */          if (this.last < this.pos) this.pos -= 1;
/* 425:425 */          this.last = -1;
/* 426:    */        }
/* 427:    */      };
/* 428:    */    }
/* 429:    */    
/* 430:    */    public ReferenceList<K> subList(int from, int to) {
/* 431:431 */      ensureIndex(from);
/* 432:432 */      ensureIndex(to);
/* 433:433 */      if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 434:434 */      return new ReferenceSubList(this, from, to);
/* 435:    */    }
/* 436:    */    
/* 437:    */    public boolean remove(Object o) {
/* 438:438 */      int index = indexOf(o);
/* 439:439 */      if (index == -1) return false;
/* 440:440 */      remove(index);
/* 441:441 */      return true;
/* 442:    */    }
/* 443:    */  }
/* 444:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */