/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigArrays;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.ObjectInputStream;
/*   6:    */import java.io.ObjectOutputStream;
/*   7:    */import java.io.Serializable;
/*   8:    */import java.util.Iterator;
/*   9:    */import java.util.NoSuchElementException;
/*  10:    */import java.util.RandomAccess;
/*  11:    */
/*  82:    */public class ReferenceBigArrayBigList<K>
/*  83:    */  extends AbstractReferenceBigList<K>
/*  84:    */  implements RandomAccess, Cloneable, Serializable
/*  85:    */{
/*  86:    */  public static final long serialVersionUID = -7046029254386353131L;
/*  87:    */  public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*  88:    */  protected static final long ONEOVERPHI = 106039L;
/*  89:    */  protected final boolean wrapped;
/*  90:    */  protected transient K[][] a;
/*  91:    */  protected long size;
/*  92:    */  private static final boolean ASSERTS = false;
/*  93:    */  
/*  94:    */  protected ReferenceBigArrayBigList(K[][] a, boolean dummy)
/*  95:    */  {
/*  96: 96 */    this.a = a;
/*  97: 97 */    this.wrapped = true;
/*  98:    */  }
/*  99:    */  
/* 103:    */  public ReferenceBigArrayBigList(long capacity)
/* 104:    */  {
/* 105:105 */    if (capacity < 0L) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/* 106:106 */    this.a = ((Object[][])ObjectBigArrays.newBigArray(capacity));
/* 107:107 */    this.wrapped = false;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public ReferenceBigArrayBigList()
/* 111:    */  {
/* 112:112 */    this(16L);
/* 113:    */  }
/* 114:    */  
/* 117:    */  public ReferenceBigArrayBigList(ReferenceCollection<? extends K> c)
/* 118:    */  {
/* 119:119 */    this(c.size());
/* 120:120 */    for (ObjectIterator<? extends K> i = c.iterator(); i.hasNext(); add(i.next())) {}
/* 121:    */  }
/* 122:    */  
/* 125:    */  public ReferenceBigArrayBigList(ReferenceBigList<? extends K> l)
/* 126:    */  {
/* 127:127 */    this(l.size64());
/* 128:128 */    l.getElements(0L, this.a, 0L, this.size = l.size64());
/* 129:    */  }
/* 130:    */  
/* 138:    */  public ReferenceBigArrayBigList(K[][] a)
/* 139:    */  {
/* 140:140 */    this(a, 0L, ObjectBigArrays.length(a));
/* 141:    */  }
/* 142:    */  
/* 152:    */  public ReferenceBigArrayBigList(K[][] a, long offset, long length)
/* 153:    */  {
/* 154:154 */    this(length);
/* 155:155 */    ObjectBigArrays.copy(a, offset, this.a, 0L, length);
/* 156:156 */    this.size = length;
/* 157:    */  }
/* 158:    */  
/* 161:    */  public ReferenceBigArrayBigList(Iterator<? extends K> i)
/* 162:    */  {
/* 163:163 */    this();
/* 164:164 */    while (i.hasNext()) { add(i.next());
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 169:    */  public ReferenceBigArrayBigList(ObjectIterator<? extends K> i)
/* 170:    */  {
/* 171:171 */    this();
/* 172:172 */    while (i.hasNext()) { add(i.next());
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 181:    */  public K[][] elements()
/* 182:    */  {
/* 183:183 */    return this.a;
/* 184:    */  }
/* 185:    */  
/* 190:    */  public static <K> ReferenceBigArrayBigList<K> wrap(K[][] a, long length)
/* 191:    */  {
/* 192:192 */    if (length > ObjectBigArrays.length(a)) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + ObjectBigArrays.length(a) + ")");
/* 193:193 */    ReferenceBigArrayBigList<K> l = new ReferenceBigArrayBigList(a, false);
/* 194:194 */    l.size = length;
/* 195:195 */    return l;
/* 196:    */  }
/* 197:    */  
/* 201:    */  public static <K> ReferenceBigArrayBigList<K> wrap(K[][] a)
/* 202:    */  {
/* 203:203 */    return wrap(a, ObjectBigArrays.length(a));
/* 204:    */  }
/* 205:    */  
/* 209:    */  public void ensureCapacity(long capacity)
/* 210:    */  {
/* 211:211 */    if (this.wrapped) { this.a = ObjectBigArrays.ensureCapacity(this.a, capacity, this.size);
/* 212:    */    }
/* 213:213 */    else if (capacity > ObjectBigArrays.length(this.a)) {
/* 214:214 */      Object[][] t = ObjectBigArrays.newBigArray(capacity);
/* 215:215 */      ObjectBigArrays.copy(this.a, 0L, t, 0L, this.size);
/* 216:216 */      this.a = ((Object[][])t);
/* 217:    */    }
/* 218:    */  }
/* 219:    */  
/* 226:    */  private void grow(long capacity)
/* 227:    */  {
/* 228:228 */    if (this.wrapped) { this.a = ObjectBigArrays.grow(this.a, capacity, this.size);
/* 229:    */    }
/* 230:230 */    else if (capacity > ObjectBigArrays.length(this.a)) {
/* 231:231 */      int newLength = (int)Math.min(Math.max(106039L * ObjectBigArrays.length(this.a) >>> 16, capacity), 2147483647L);
/* 232:232 */      Object[][] t = ObjectBigArrays.newBigArray(newLength);
/* 233:233 */      ObjectBigArrays.copy(this.a, 0L, t, 0L, this.size);
/* 234:234 */      this.a = ((Object[][])t);
/* 235:    */    }
/* 236:    */  }
/* 237:    */  
/* 238:    */  public void add(long index, K k)
/* 239:    */  {
/* 240:240 */    ensureIndex(index);
/* 241:241 */    grow(this.size + 1L);
/* 242:242 */    if (index != this.size) ObjectBigArrays.copy(this.a, index, this.a, index + 1L, this.size - index);
/* 243:243 */    ObjectBigArrays.set(this.a, index, k);
/* 244:244 */    this.size += 1L;
/* 245:    */  }
/* 246:    */  
/* 247:    */  public boolean add(K k) {
/* 248:248 */    grow(this.size + 1L);
/* 249:249 */    ObjectBigArrays.set(this.a, this.size++, k);
/* 250:    */    
/* 251:251 */    return true;
/* 252:    */  }
/* 253:    */  
/* 254:254 */  public K get(long index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 255:255 */    return ObjectBigArrays.get(this.a, index);
/* 256:    */  }
/* 257:    */  
/* 258:258 */  public long indexOf(Object k) { for (long i = 0L; i < this.size; i += 1L) if (k == ObjectBigArrays.get(this.a, i)) return i;
/* 259:259 */    return -1L;
/* 260:    */  }
/* 261:    */  
/* 262:262 */  public long lastIndexOf(Object k) { for (long i = this.size; i-- != 0L;) if (k == ObjectBigArrays.get(this.a, i)) return i;
/* 263:263 */    return -1L;
/* 264:    */  }
/* 265:    */  
/* 266:266 */  public K remove(long index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 267:267 */    K old = ObjectBigArrays.get(this.a, index);
/* 268:268 */    this.size -= 1L;
/* 269:269 */    if (index != this.size) ObjectBigArrays.copy(this.a, index + 1L, this.a, index, this.size - index);
/* 270:270 */    ObjectBigArrays.set(this.a, this.size, null);
/* 271:    */    
/* 272:272 */    return old;
/* 273:    */  }
/* 274:    */  
/* 275:275 */  public boolean rem(Object k) { long index = indexOf(k);
/* 276:276 */    if (index == -1L) return false;
/* 277:277 */    remove(index);
/* 278:    */    
/* 279:279 */    return true;
/* 280:    */  }
/* 281:    */  
/* 282:282 */  public boolean remove(Object o) { return rem(o); }
/* 283:    */  
/* 284:    */  public K set(long index, K k) {
/* 285:285 */    if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 286:286 */    K old = ObjectBigArrays.get(this.a, index);
/* 287:287 */    ObjectBigArrays.set(this.a, index, k);
/* 288:288 */    return old;
/* 289:    */  }
/* 290:    */  
/* 291:291 */  public void clear() { ObjectBigArrays.fill(this.a, 0L, this.size, null);
/* 292:292 */    this.size = 0L;
/* 293:    */  }
/* 294:    */  
/* 296:296 */  public long size64() { return this.size; }
/* 297:    */  
/* 298:    */  public void size(long size) {
/* 299:299 */    if (size > ObjectBigArrays.length(this.a)) ensureCapacity(size);
/* 300:300 */    if (size > this.size) ObjectBigArrays.fill(this.a, this.size, size, null); else
/* 301:301 */      ObjectBigArrays.fill(this.a, size, this.size, null);
/* 302:302 */    this.size = size;
/* 303:    */  }
/* 304:    */  
/* 305:305 */  public boolean isEmpty() { return this.size == 0L; }
/* 306:    */  
/* 310:    */  public void trim()
/* 311:    */  {
/* 312:312 */    trim(0L);
/* 313:    */  }
/* 314:    */  
/* 328:    */  public void trim(long n)
/* 329:    */  {
/* 330:330 */    long arrayLength = ObjectBigArrays.length(this.a);
/* 331:331 */    if ((n >= arrayLength) || (this.size == arrayLength)) return;
/* 332:332 */    this.a = ObjectBigArrays.trim(this.a, Math.max(n, this.size));
/* 333:    */  }
/* 334:    */  
/* 341:    */  public void getElements(int from, Object[][] a, long offset, long length)
/* 342:    */  {
/* 343:343 */    ObjectBigArrays.copy(this.a, from, a, offset, length);
/* 344:    */  }
/* 345:    */  
/* 349:    */  public void removeElements(int from, int to)
/* 350:    */  {
/* 351:351 */    BigArrays.ensureFromTo(this.size, from, to);
/* 352:352 */    ObjectBigArrays.copy(this.a, to, this.a, from, this.size - to);
/* 353:353 */    this.size -= to - from;
/* 354:354 */    ObjectBigArrays.fill(this.a, this.size, this.size + to - from, null);
/* 355:    */  }
/* 356:    */  
/* 362:    */  public void addElements(int index, K[][] a, long offset, long length)
/* 363:    */  {
/* 364:364 */    ensureIndex(index);
/* 365:365 */    ObjectBigArrays.ensureOffsetLength(a, offset, length);
/* 366:366 */    grow(this.size + length);
/* 367:367 */    ObjectBigArrays.copy(this.a, index, this.a, index + length, this.size - index);
/* 368:368 */    ObjectBigArrays.copy(a, offset, this.a, index, length);
/* 369:369 */    this.size += length;
/* 370:    */  }
/* 371:    */  
/* 372:372 */  public ObjectBigListIterator<K> listIterator(final int index) { ensureIndex(index);
/* 373:373 */    new AbstractObjectBigListIterator() {
/* 374:374 */      int last = -1; int pos = index;
/* 375:375 */      public boolean hasNext() { return this.pos < ReferenceBigArrayBigList.this.size; }
/* 376:376 */      public boolean hasPrevious() { return this.pos > 0; }
/* 377:377 */      public K next() { if (!hasNext()) throw new NoSuchElementException(); return ObjectBigArrays.get(ReferenceBigArrayBigList.this.a, this.last = this.pos++); }
/* 378:378 */      public K previous() { if (!hasPrevious()) throw new NoSuchElementException(); return ObjectBigArrays.get(ReferenceBigArrayBigList.this.a, this.last = --this.pos); }
/* 379:379 */      public long nextIndex() { return this.pos; }
/* 380:380 */      public long previousIndex() { return this.pos - 1; }
/* 381:    */      
/* 382:382 */      public void add(K k) { if (this.last == -1) throw new IllegalStateException();
/* 383:383 */        ReferenceBigArrayBigList.this.add(this.pos++, k);
/* 384:384 */        this.last = -1;
/* 385:    */      }
/* 386:    */      
/* 387:387 */      public void set(K k) { if (this.last == -1) throw new IllegalStateException();
/* 388:388 */        ReferenceBigArrayBigList.this.set(this.last, k);
/* 389:    */      }
/* 390:    */      
/* 391:391 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 392:392 */        ReferenceBigArrayBigList.this.remove(this.last);
/* 393:    */        
/* 394:394 */        if (this.last < this.pos) this.pos -= 1;
/* 395:395 */        this.last = -1;
/* 396:    */      }
/* 397:    */    };
/* 398:    */  }
/* 399:    */  
/* 400:    */  public ReferenceBigArrayBigList<K> clone() {
/* 401:401 */    ReferenceBigArrayBigList<K> c = new ReferenceBigArrayBigList(this.size);
/* 402:402 */    ObjectBigArrays.copy(this.a, 0L, c.a, 0L, this.size);
/* 403:403 */    c.size = this.size;
/* 404:404 */    return c;
/* 405:    */  }
/* 406:    */  
/* 413:    */  public boolean equals(ReferenceBigArrayBigList<K> l)
/* 414:    */  {
/* 415:415 */    if (l == this) return true;
/* 416:416 */    long s = size64();
/* 417:417 */    if (s != l.size64()) return false;
/* 418:418 */    K[][] a1 = this.a;
/* 419:419 */    K[][] a2 = l.a;
/* 420:420 */    while (s-- != 0L) if (ObjectBigArrays.get(a1, s) != ObjectBigArrays.get(a2, s)) return false;
/* 421:421 */    return true;
/* 422:    */  }
/* 423:    */  
/* 424:424 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 425:425 */    for (int i = 0; i < this.size; i++) s.writeObject(ObjectBigArrays.get(this.a, i));
/* 426:    */  }
/* 427:    */  
/* 428:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 429:429 */    s.defaultReadObject();
/* 430:430 */    this.a = ((Object[][])ObjectBigArrays.newBigArray(this.size));
/* 431:431 */    for (int i = 0; i < this.size; i++) ObjectBigArrays.set(this.a, i, s.readObject());
/* 432:    */  }
/* 433:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */