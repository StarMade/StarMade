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
/*  53:    */public abstract class AbstractReferenceBigList<K>
/*  54:    */  extends AbstractReferenceCollection<K>
/*  55:    */  implements ReferenceBigList<K>, Stack<K>
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
/* 114:114 */      public boolean hasNext() { return this.pos < AbstractReferenceBigList.this.size64(); }
/* 115:115 */      public boolean hasPrevious() { return this.pos > 0L; }
/* 116:116 */      public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceBigList.this.get(this.last = this.pos++); }
/* 117:117 */      public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceBigList.this.get(this.last = --this.pos); }
/* 118:118 */      public long nextIndex() { return this.pos; }
/* 119:119 */      public long previousIndex() { return this.pos - 1L; }
/* 120:    */      
/* 121:121 */      public void add(K k) { if (this.last == -1L) throw new IllegalStateException();
/* 122:122 */        AbstractReferenceBigList.this.add(this.pos++, k);
/* 123:123 */        this.last = -1L;
/* 124:    */      }
/* 125:    */      
/* 126:126 */      public void set(K k) { if (this.last == -1L) throw new IllegalStateException();
/* 127:127 */        AbstractReferenceBigList.this.set(this.last, k);
/* 128:    */      }
/* 129:    */      
/* 130:130 */      public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 131:131 */        AbstractReferenceBigList.this.remove(this.last);
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
/* 151:    */    
/* 152:152 */    while (i.hasNext()) {
/* 153:153 */      K e = i.next();
/* 154:154 */      if (k == e) return i.previousIndex();
/* 155:    */    }
/* 156:156 */    return -1L;
/* 157:    */  }
/* 158:    */  
/* 159:    */  public long lastIndexOf(Object k) {
/* 160:160 */    ObjectBigListIterator<K> i = listIterator(size64());
/* 161:    */    
/* 162:162 */    while (i.hasPrevious()) {
/* 163:163 */      K e = i.previous();
/* 164:164 */      if (k == e) return i.nextIndex();
/* 165:    */    }
/* 166:166 */    return -1L;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public void size(long size) {
/* 170:170 */    long i = size64();
/* 171:171 */    for (size <= i; i++ < size; add(null)) {}
/* 172:172 */    while (i-- != size) remove(i);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void size(int size) {
/* 176:176 */    size(size);
/* 177:    */  }
/* 178:    */  
/* 179:    */  public ReferenceBigList<K> subList(long from, long to) {
/* 180:180 */    ensureIndex(from);
/* 181:181 */    ensureIndex(to);
/* 182:182 */    if (from > to) { throw new IndexOutOfBoundsException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 183:    */    }
/* 184:184 */    return new ReferenceSubList(this, from, to);
/* 185:    */  }
/* 186:    */  
/* 195:    */  public void removeElements(long from, long to)
/* 196:    */  {
/* 197:197 */    ensureIndex(to);
/* 198:198 */    ObjectBigListIterator<K> i = listIterator(from);
/* 199:199 */    long n = to - from;
/* 200:200 */    if (n < 0L) throw new IllegalArgumentException(new StringBuilder().append("Start index (").append(from).append(") is greater than end index (").append(to).append(")").toString());
/* 201:201 */    while (n-- != 0L) {
/* 202:202 */      i.next();
/* 203:203 */      i.remove();
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 217:    */  public void addElements(long index, K[][] a, long offset, long length)
/* 218:    */  {
/* 219:219 */    ensureIndex(index);
/* 220:220 */    ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 221:221 */    while (length-- != 0L) add(index++, ObjectBigArrays.get(a, offset++));
/* 222:    */  }
/* 223:    */  
/* 224:    */  public void addElements(long index, K[][] a) {
/* 225:225 */    addElements(index, a, 0L, ObjectBigArrays.length(a));
/* 226:    */  }
/* 227:    */  
/* 238:    */  public void getElements(long from, Object[][] a, long offset, long length)
/* 239:    */  {
/* 240:240 */    ObjectBigListIterator<K> i = listIterator(from);
/* 241:241 */    ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 242:242 */    if (from + length > size64()) throw new IndexOutOfBoundsException(new StringBuilder().append("End index (").append(from + length).append(") is greater than list size (").append(size64()).append(")").toString());
/* 243:243 */    while (length-- != 0L) ObjectBigArrays.set(a, offset++, i.next());
/* 244:    */  }
/* 245:    */  
/* 246:    */  @Deprecated
/* 247:    */  public int size() {
/* 248:248 */    return (int)Math.min(2147483647L, size64());
/* 249:    */  }
/* 250:    */  
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
/* 268:268 */    while (s-- != 0L) { if (i1.next() != i2.next()) { return false;
/* 269:    */      }
/* 270:    */    }
/* 271:    */    
/* 272:272 */    return true;
/* 273:    */  }
/* 274:    */  
/* 277:    */  public int hashCode()
/* 278:    */  {
/* 279:279 */    ObjectIterator<K> i = iterator();
/* 280:280 */    int h = 1;
/* 281:281 */    long s = size64();
/* 282:282 */    while (s-- != 0L) {
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
/* 293:293 */    return remove(size64() - 1L);
/* 294:    */  }
/* 295:    */  
/* 296:296 */  public K top() { if (isEmpty()) throw new NoSuchElementException();
/* 297:297 */    return get(size64() - 1L);
/* 298:    */  }
/* 299:    */  
/* 300:300 */  public K peek(int i) { return get(size64() - 1L - i); }
/* 301:    */  
/* 303:303 */  public K get(int index) { return get(index); }
/* 304:    */  
/* 305:    */  public String toString() {
/* 306:306 */    StringBuilder s = new StringBuilder();
/* 307:307 */    ObjectIterator<K> i = iterator();
/* 308:308 */    long n = size64();
/* 309:    */    
/* 310:310 */    boolean first = true;
/* 311:311 */    s.append("[");
/* 312:312 */    while (n-- != 0L) {
/* 313:313 */      if (first) first = false; else
/* 314:314 */        s.append(", ");
/* 315:315 */      K k = i.next();
/* 316:316 */      if (this == k) s.append("(this big list)"); else
/* 317:317 */        s.append(String.valueOf(k));
/* 318:    */    }
/* 319:319 */    s.append("]");
/* 320:320 */    return s.toString();
/* 321:    */  }
/* 322:    */  
/* 323:    */  public static class ReferenceSubList<K> extends AbstractReferenceBigList<K> implements Serializable
/* 324:    */  {
/* 325:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 326:    */    protected final ReferenceBigList<K> l;
/* 327:    */    protected final long from;
/* 328:    */    protected long to;
/* 329:    */    private static final boolean ASSERTS = false;
/* 330:    */    
/* 331:    */    public ReferenceSubList(ReferenceBigList<K> l, long from, long to) {
/* 332:332 */      this.l = l;
/* 333:333 */      this.from = from;
/* 334:334 */      this.to = to;
/* 335:    */    }
/* 336:    */    
/* 339:    */    private void assertRange() {}
/* 340:    */    
/* 342:    */    public boolean add(K k)
/* 343:    */    {
/* 344:344 */      this.l.add(this.to, k);
/* 345:345 */      this.to += 1L;
/* 346:    */      
/* 347:347 */      return true;
/* 348:    */    }
/* 349:    */    
/* 350:350 */    public void add(long index, K k) { ensureIndex(index);
/* 351:351 */      this.l.add(this.from + index, k);
/* 352:352 */      this.to += 1L;
/* 353:    */    }
/* 354:    */    
/* 355:    */    public boolean addAll(long index, Collection<? extends K> c) {
/* 356:356 */      ensureIndex(index);
/* 357:357 */      this.to += c.size();
/* 358:    */      
/* 363:363 */      return this.l.addAll(this.from + index, c);
/* 364:    */    }
/* 365:    */    
/* 366:366 */    public K get(long index) { ensureRestrictedIndex(index);
/* 367:367 */      return this.l.get(this.from + index);
/* 368:    */    }
/* 369:    */    
/* 370:370 */    public K remove(long index) { ensureRestrictedIndex(index);
/* 371:371 */      this.to -= 1L;
/* 372:372 */      return this.l.remove(this.from + index);
/* 373:    */    }
/* 374:    */    
/* 375:375 */    public K set(long index, K k) { ensureRestrictedIndex(index);
/* 376:376 */      return this.l.set(this.from + index, k);
/* 377:    */    }
/* 378:    */    
/* 379:379 */    public void clear() { removeElements(0L, size64()); }
/* 380:    */    
/* 383:383 */    public long size64() { return this.to - this.from; }
/* 384:    */    
/* 385:    */    public void getElements(long from, Object[][] a, long offset, long length) {
/* 386:386 */      ensureIndex(from);
/* 387:387 */      if (from + length > size64()) throw new IndexOutOfBoundsException("End index (" + from + length + ") is greater than list size (" + size64() + ")");
/* 388:388 */      this.l.getElements(this.from + from, a, offset, length);
/* 389:    */    }
/* 390:    */    
/* 391:391 */    public void removeElements(long from, long to) { ensureIndex(from);
/* 392:392 */      ensureIndex(to);
/* 393:393 */      this.l.removeElements(this.from + from, this.from + to);
/* 394:394 */      this.to -= to - from;
/* 395:    */    }
/* 396:    */    
/* 397:    */    public void addElements(long index, K[][] a, long offset, long length) {
/* 398:398 */      ensureIndex(index);
/* 399:399 */      this.l.addElements(this.from + index, a, offset, length);
/* 400:400 */      this.to += length;
/* 401:    */    }
/* 402:    */    
/* 403:    */    public ObjectBigListIterator<K> listIterator(final long index) {
/* 404:404 */      ensureIndex(index);
/* 405:405 */      new AbstractObjectBigListIterator() {
/* 406:406 */        long last = -1L; long pos = index;
/* 407:407 */        public boolean hasNext() { return this.pos < AbstractReferenceBigList.ReferenceSubList.this.size64(); }
/* 408:408 */        public boolean hasPrevious() { return this.pos > 0L; }
/* 409:409 */        public K next() { if (!hasNext()) throw new NoSuchElementException(); return AbstractReferenceBigList.ReferenceSubList.this.l.get(AbstractReferenceBigList.ReferenceSubList.this.from + (this.last = this.pos++)); }
/* 410:410 */        public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return AbstractReferenceBigList.ReferenceSubList.this.l.get(AbstractReferenceBigList.ReferenceSubList.this.from + (this.last = --this.pos)); }
/* 411:411 */        public long nextIndex() { return this.pos; }
/* 412:412 */        public long previousIndex() { return this.pos - 1L; }
/* 413:    */        
/* 414:414 */        public void add(K k) { if (this.last == -1L) throw new IllegalStateException();
/* 415:415 */          AbstractReferenceBigList.ReferenceSubList.this.add(this.pos++, k);
/* 416:416 */          this.last = -1L;
/* 417:    */        }
/* 418:    */        
/* 419:    */        public void set(K k) {
/* 420:420 */          if (this.last == -1L) throw new IllegalStateException();
/* 421:421 */          AbstractReferenceBigList.ReferenceSubList.this.set(this.last, k);
/* 422:    */        }
/* 423:    */        
/* 424:424 */        public void remove() { if (this.last == -1L) throw new IllegalStateException();
/* 425:425 */          AbstractReferenceBigList.ReferenceSubList.this.remove(this.last);
/* 426:    */          
/* 427:427 */          if (this.last < this.pos) this.pos -= 1L;
/* 428:428 */          this.last = -1L;
/* 429:    */        }
/* 430:    */      };
/* 431:    */    }
/* 432:    */    
/* 433:    */    public ReferenceBigList<K> subList(long from, long to) {
/* 434:434 */      ensureIndex(from);
/* 435:435 */      ensureIndex(to);
/* 436:436 */      if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 437:437 */      return new ReferenceSubList(this, from, to);
/* 438:    */    }
/* 439:    */    
/* 440:    */    public boolean remove(Object o) {
/* 441:441 */      long index = indexOf(o);
/* 442:442 */      if (index == -1L) return false;
/* 443:443 */      remove(index);
/* 444:444 */      return true;
/* 445:    */    }
/* 446:    */  }
/* 447:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReferenceBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */