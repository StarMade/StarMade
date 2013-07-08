/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Arrays;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.ObjectInputStream;
/*   6:    */import java.io.ObjectOutputStream;
/*   7:    */import java.io.Serializable;
/*   8:    */import java.util.Collection;
/*   9:    */import java.util.Iterator;
/*  10:    */import java.util.NoSuchElementException;
/*  11:    */import java.util.RandomAccess;
/*  12:    */
/*  83:    */public class ObjectArrayList<K>
/*  84:    */  extends AbstractObjectList<K>
/*  85:    */  implements RandomAccess, Cloneable, Serializable
/*  86:    */{
/*  87:    */  public static final long serialVersionUID = -7046029254386353131L;
/*  88:    */  public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*  89:    */  protected static final long ONEOVERPHI = 106039L;
/*  90:    */  protected final boolean wrapped;
/*  91:    */  protected transient K[] a;
/*  92:    */  protected int size;
/*  93:    */  private static final boolean ASSERTS = false;
/*  94:    */  
/*  95:    */  protected ObjectArrayList(K[] a, boolean dummy)
/*  96:    */  {
/*  97: 97 */    this.a = a;
/*  98: 98 */    this.wrapped = true;
/*  99:    */  }
/* 100:    */  
/* 104:    */  public ObjectArrayList(int capacity)
/* 105:    */  {
/* 106:106 */    if (capacity < 0) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/* 107:107 */    this.a = ((Object[])new Object[capacity]);
/* 108:108 */    this.wrapped = false;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public ObjectArrayList()
/* 112:    */  {
/* 113:113 */    this(16);
/* 114:    */  }
/* 115:    */  
/* 118:    */  public ObjectArrayList(Collection<? extends K> c)
/* 119:    */  {
/* 120:120 */    this(c.size());
/* 121:121 */    this.size = ObjectIterators.unwrap(c.iterator(), this.a);
/* 122:    */  }
/* 123:    */  
/* 126:    */  public ObjectArrayList(ObjectCollection<? extends K> c)
/* 127:    */  {
/* 128:128 */    this(c.size());
/* 129:129 */    this.size = ObjectIterators.unwrap(c.iterator(), this.a);
/* 130:    */  }
/* 131:    */  
/* 134:    */  public ObjectArrayList(ObjectList<? extends K> l)
/* 135:    */  {
/* 136:136 */    this(l.size());
/* 137:137 */    l.getElements(0, this.a, 0, this.size = l.size());
/* 138:    */  }
/* 139:    */  
/* 142:    */  public ObjectArrayList(K[] a)
/* 143:    */  {
/* 144:144 */    this(a, 0, a.length);
/* 145:    */  }
/* 146:    */  
/* 151:    */  public ObjectArrayList(K[] a, int offset, int length)
/* 152:    */  {
/* 153:153 */    this(length);
/* 154:154 */    System.arraycopy(a, offset, this.a, 0, length);
/* 155:155 */    this.size = length;
/* 156:    */  }
/* 157:    */  
/* 160:    */  public ObjectArrayList(Iterator<? extends K> i)
/* 161:    */  {
/* 162:162 */    this();
/* 163:163 */    while (i.hasNext()) { add(i.next());
/* 164:    */    }
/* 165:    */  }
/* 166:    */  
/* 168:    */  public ObjectArrayList(ObjectIterator<? extends K> i)
/* 169:    */  {
/* 170:170 */    this();
/* 171:171 */    while (i.hasNext()) { add(i.next());
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 185:    */  public K[] elements()
/* 186:    */  {
/* 187:187 */    return this.a;
/* 188:    */  }
/* 189:    */  
/* 194:    */  public static <K> ObjectArrayList<K> wrap(K[] a, int length)
/* 195:    */  {
/* 196:196 */    if (length > a.length) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
/* 197:197 */    ObjectArrayList<K> l = new ObjectArrayList(a, false);
/* 198:198 */    l.size = length;
/* 199:199 */    return l;
/* 200:    */  }
/* 201:    */  
/* 205:    */  public static <K> ObjectArrayList<K> wrap(K[] a)
/* 206:    */  {
/* 207:207 */    return wrap(a, a.length);
/* 208:    */  }
/* 209:    */  
/* 213:    */  public void ensureCapacity(int capacity)
/* 214:    */  {
/* 215:215 */    if (this.wrapped) { this.a = ObjectArrays.ensureCapacity(this.a, capacity, this.size);
/* 216:    */    }
/* 217:217 */    else if (capacity > this.a.length) {
/* 218:218 */      Object[] t = new Object[capacity];
/* 219:219 */      System.arraycopy(this.a, 0, t, 0, this.size);
/* 220:220 */      this.a = ((Object[])t);
/* 221:    */    }
/* 222:    */  }
/* 223:    */  
/* 230:    */  private void grow(int capacity)
/* 231:    */  {
/* 232:232 */    if (this.wrapped) { this.a = ObjectArrays.grow(this.a, capacity, this.size);
/* 233:    */    }
/* 234:234 */    else if (capacity > this.a.length) {
/* 235:235 */      int newLength = (int)Math.min(Math.max(106039L * this.a.length >>> 16, capacity), 2147483647L);
/* 236:236 */      Object[] t = new Object[newLength];
/* 237:237 */      System.arraycopy(this.a, 0, t, 0, this.size);
/* 238:238 */      this.a = ((Object[])t);
/* 239:    */    }
/* 240:    */  }
/* 241:    */  
/* 242:    */  public void add(int index, K k)
/* 243:    */  {
/* 244:244 */    ensureIndex(index);
/* 245:245 */    grow(this.size + 1);
/* 246:246 */    if (index != this.size) System.arraycopy(this.a, index, this.a, index + 1, this.size - index);
/* 247:247 */    this.a[index] = k;
/* 248:248 */    this.size += 1;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public boolean add(K k) {
/* 252:252 */    grow(this.size + 1);
/* 253:253 */    this.a[(this.size++)] = k;
/* 254:    */    
/* 255:255 */    return true;
/* 256:    */  }
/* 257:    */  
/* 258:258 */  public K get(int index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 259:259 */    return this.a[index];
/* 260:    */  }
/* 261:    */  
/* 262:262 */  public int indexOf(Object k) { for (int i = 0; i < this.size; i++) if (k == null ? this.a[i] == null : k.equals(this.a[i])) return i;
/* 263:263 */    return -1;
/* 264:    */  }
/* 265:    */  
/* 266:266 */  public int lastIndexOf(Object k) { for (int i = this.size; i-- != 0; return i) label5: if (k == null ? this.a[i] != null : !k.equals(this.a[i])) break label5;
/* 267:267 */    return -1;
/* 268:    */  }
/* 269:    */  
/* 270:270 */  public K remove(int index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 271:271 */    K old = this.a[index];
/* 272:272 */    this.size -= 1;
/* 273:273 */    if (index != this.size) System.arraycopy(this.a, index + 1, this.a, index, this.size - index);
/* 274:274 */    this.a[this.size] = null;
/* 275:    */    
/* 276:276 */    return old;
/* 277:    */  }
/* 278:    */  
/* 279:279 */  public boolean rem(Object k) { int index = indexOf(k);
/* 280:280 */    if (index == -1) return false;
/* 281:281 */    remove(index);
/* 282:    */    
/* 283:283 */    return true;
/* 284:    */  }
/* 285:    */  
/* 286:286 */  public boolean remove(Object o) { return rem(o); }
/* 287:    */  
/* 288:    */  public K set(int index, K k) {
/* 289:289 */    if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 290:290 */    K old = this.a[index];
/* 291:291 */    this.a[index] = k;
/* 292:292 */    return old;
/* 293:    */  }
/* 294:    */  
/* 295:295 */  public void clear() { ObjectArrays.fill(this.a, 0, this.size, null);
/* 296:296 */    this.size = 0;
/* 297:    */  }
/* 298:    */  
/* 300:300 */  public int size() { return this.size; }
/* 301:    */  
/* 302:    */  public void size(int size) {
/* 303:303 */    if (size > this.a.length) ensureCapacity(size);
/* 304:304 */    if (size > this.size) ObjectArrays.fill(this.a, this.size, size, null); else
/* 305:305 */      ObjectArrays.fill(this.a, size, this.size, null);
/* 306:306 */    this.size = size;
/* 307:    */  }
/* 308:    */  
/* 309:309 */  public boolean isEmpty() { return this.size == 0; }
/* 310:    */  
/* 314:    */  public void trim()
/* 315:    */  {
/* 316:316 */    trim(0);
/* 317:    */  }
/* 318:    */  
/* 333:    */  public void trim(int n)
/* 334:    */  {
/* 335:335 */    if ((n >= this.a.length) || (this.size == this.a.length)) return;
/* 336:336 */    K[] t = (Object[])new Object[Math.max(n, this.size)];
/* 337:337 */    System.arraycopy(this.a, 0, t, 0, this.size);
/* 338:338 */    this.a = t;
/* 339:    */  }
/* 340:    */  
/* 347:    */  public void getElements(int from, Object[] a, int offset, int length)
/* 348:    */  {
/* 349:349 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 350:350 */    System.arraycopy(this.a, from, a, offset, length);
/* 351:    */  }
/* 352:    */  
/* 356:    */  public void removeElements(int from, int to)
/* 357:    */  {
/* 358:358 */    Arrays.ensureFromTo(this.size, from, to);
/* 359:359 */    System.arraycopy(this.a, to, this.a, from, this.size - to);
/* 360:360 */    this.size -= to - from;
/* 361:361 */    int i = to - from;
/* 362:362 */    while (i-- != 0) { this.a[(this.size + i)] = null;
/* 363:    */    }
/* 364:    */  }
/* 365:    */  
/* 370:    */  public void addElements(int index, K[] a, int offset, int length)
/* 371:    */  {
/* 372:372 */    ensureIndex(index);
/* 373:373 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 374:374 */    grow(this.size + length);
/* 375:375 */    System.arraycopy(this.a, index, this.a, index + length, this.size - index);
/* 376:376 */    System.arraycopy(a, offset, this.a, index, length);
/* 377:377 */    this.size += length;
/* 378:    */  }
/* 379:    */  
/* 380:380 */  public ObjectListIterator<K> listIterator(final int index) { ensureIndex(index);
/* 381:381 */    new AbstractObjectListIterator() {
/* 382:382 */      int last = -1; int pos = index;
/* 383:383 */      public boolean hasNext() { return this.pos < ObjectArrayList.this.size; }
/* 384:384 */      public boolean hasPrevious() { return this.pos > 0; }
/* 385:385 */      public K next() { if (!hasNext()) throw new NoSuchElementException(); return ObjectArrayList.this.a[(this.last = this.pos++)]; }
/* 386:386 */      public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return ObjectArrayList.this.a[(this.last = --this.pos)]; }
/* 387:387 */      public int nextIndex() { return this.pos; }
/* 388:388 */      public int previousIndex() { return this.pos - 1; }
/* 389:    */      
/* 390:390 */      public void add(K k) { if (this.last == -1) throw new IllegalStateException();
/* 391:391 */        ObjectArrayList.this.add(this.pos++, k);
/* 392:392 */        this.last = -1;
/* 393:    */      }
/* 394:    */      
/* 395:395 */      public void set(K k) { if (this.last == -1) throw new IllegalStateException();
/* 396:396 */        ObjectArrayList.this.set(this.last, k);
/* 397:    */      }
/* 398:    */      
/* 399:399 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 400:400 */        ObjectArrayList.this.remove(this.last);
/* 401:    */        
/* 402:402 */        if (this.last < this.pos) this.pos -= 1;
/* 403:403 */        this.last = -1;
/* 404:    */      }
/* 405:    */    };
/* 406:    */  }
/* 407:    */  
/* 408:    */  public ObjectArrayList<K> clone() {
/* 409:409 */    ObjectArrayList<K> c = new ObjectArrayList(this.size);
/* 410:410 */    System.arraycopy(this.a, 0, c.a, 0, this.size);
/* 411:411 */    c.size = this.size;
/* 412:412 */    return c;
/* 413:    */  }
/* 414:    */  
/* 415:415 */  private boolean valEquals(K a, K b) { return a == null ? false : b == null ? true : a.equals(b); }
/* 416:    */  
/* 424:    */  public boolean equals(ObjectArrayList<K> l)
/* 425:    */  {
/* 426:426 */    if (l == this) return true;
/* 427:427 */    int s = size();
/* 428:428 */    if (s != l.size()) return false;
/* 429:429 */    K[] a1 = this.a;
/* 430:430 */    K[] a2 = l.a;
/* 431:431 */    while (s-- != 0) if (!valEquals(a1[s], a2[s])) return false;
/* 432:432 */    return true;
/* 433:    */  }
/* 434:    */  
/* 444:    */  public int compareTo(ObjectArrayList<? extends K> l)
/* 445:    */  {
/* 446:446 */    int s1 = size();int s2 = l.size();
/* 447:447 */    K[] a1 = this.a;K[] a2 = l.a;
/* 448:    */    
/* 450:450 */    for (int i = 0; (i < s1) && (i < s2); i++) {
/* 451:451 */      K e1 = a1[i];
/* 452:452 */      K e2 = a2[i];
/* 453:453 */      int r; if ((r = ((Comparable)e1).compareTo(e2)) != 0) return r;
/* 454:    */    }
/* 455:455 */    return i < s1 ? 1 : i < s2 ? -1 : 0;
/* 456:    */  }
/* 457:    */  
/* 458:458 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 459:459 */    for (int i = 0; i < this.size; i++) s.writeObject(this.a[i]);
/* 460:    */  }
/* 461:    */  
/* 462:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 463:463 */    s.defaultReadObject();
/* 464:464 */    this.a = ((Object[])new Object[this.size]);
/* 465:465 */    for (int i = 0; i < this.size; i++) this.a[i] = s.readObject();
/* 466:    */  }
/* 467:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */