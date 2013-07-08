/*   1:    */package it.unimi.dsi.fastutil.doubles;
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
/*  77:    */public class DoubleArrayList
/*  78:    */  extends AbstractDoubleList
/*  79:    */  implements RandomAccess, Cloneable, Serializable
/*  80:    */{
/*  81:    */  public static final long serialVersionUID = -7046029254386353130L;
/*  82:    */  public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*  83:    */  protected static final long ONEOVERPHI = 106039L;
/*  84:    */  protected transient double[] a;
/*  85:    */  protected int size;
/*  86:    */  private static final boolean ASSERTS = false;
/*  87:    */  
/*  88:    */  protected DoubleArrayList(double[] a, boolean dummy)
/*  89:    */  {
/*  90: 90 */    this.a = a;
/*  91:    */  }
/*  92:    */  
/*  96:    */  public DoubleArrayList(int capacity)
/*  97:    */  {
/*  98: 98 */    if (capacity < 0) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  99: 99 */    this.a = new double[capacity];
/* 100:    */  }
/* 101:    */  
/* 102:    */  public DoubleArrayList()
/* 103:    */  {
/* 104:104 */    this(16);
/* 105:    */  }
/* 106:    */  
/* 109:    */  public DoubleArrayList(Collection<? extends Double> c)
/* 110:    */  {
/* 111:111 */    this(c.size());
/* 112:112 */    this.size = DoubleIterators.unwrap(DoubleIterators.asDoubleIterator(c.iterator()), this.a);
/* 113:    */  }
/* 114:    */  
/* 117:    */  public DoubleArrayList(DoubleCollection c)
/* 118:    */  {
/* 119:119 */    this(c.size());
/* 120:120 */    this.size = DoubleIterators.unwrap(c.iterator(), this.a);
/* 121:    */  }
/* 122:    */  
/* 125:    */  public DoubleArrayList(DoubleList l)
/* 126:    */  {
/* 127:127 */    this(l.size());
/* 128:128 */    l.getElements(0, this.a, 0, this.size = l.size());
/* 129:    */  }
/* 130:    */  
/* 133:    */  public DoubleArrayList(double[] a)
/* 134:    */  {
/* 135:135 */    this(a, 0, a.length);
/* 136:    */  }
/* 137:    */  
/* 142:    */  public DoubleArrayList(double[] a, int offset, int length)
/* 143:    */  {
/* 144:144 */    this(length);
/* 145:145 */    System.arraycopy(a, offset, this.a, 0, length);
/* 146:146 */    this.size = length;
/* 147:    */  }
/* 148:    */  
/* 151:    */  public DoubleArrayList(Iterator<? extends Double> i)
/* 152:    */  {
/* 153:153 */    this();
/* 154:154 */    while (i.hasNext()) { add((Double)i.next());
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 159:    */  public DoubleArrayList(DoubleIterator i)
/* 160:    */  {
/* 161:161 */    this();
/* 162:162 */    while (i.hasNext()) { add(i.nextDouble());
/* 163:    */    }
/* 164:    */  }
/* 165:    */  
/* 167:    */  public double[] elements()
/* 168:    */  {
/* 169:169 */    return this.a;
/* 170:    */  }
/* 171:    */  
/* 176:    */  public static DoubleArrayList wrap(double[] a, int length)
/* 177:    */  {
/* 178:178 */    if (length > a.length) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + a.length + ")");
/* 179:179 */    DoubleArrayList l = new DoubleArrayList(a, false);
/* 180:180 */    l.size = length;
/* 181:181 */    return l;
/* 182:    */  }
/* 183:    */  
/* 187:    */  public static DoubleArrayList wrap(double[] a)
/* 188:    */  {
/* 189:189 */    return wrap(a, a.length);
/* 190:    */  }
/* 191:    */  
/* 195:    */  public void ensureCapacity(int capacity)
/* 196:    */  {
/* 197:197 */    this.a = DoubleArrays.ensureCapacity(this.a, capacity, this.size);
/* 198:    */  }
/* 199:    */  
/* 205:    */  private void grow(int capacity)
/* 206:    */  {
/* 207:207 */    this.a = DoubleArrays.grow(this.a, capacity, this.size);
/* 208:    */  }
/* 209:    */  
/* 210:    */  public void add(int index, double k) {
/* 211:211 */    ensureIndex(index);
/* 212:212 */    grow(this.size + 1);
/* 213:213 */    if (index != this.size) System.arraycopy(this.a, index, this.a, index + 1, this.size - index);
/* 214:214 */    this.a[index] = k;
/* 215:215 */    this.size += 1;
/* 216:    */  }
/* 217:    */  
/* 218:    */  public boolean add(double k) {
/* 219:219 */    grow(this.size + 1);
/* 220:220 */    this.a[(this.size++)] = k;
/* 221:    */    
/* 222:222 */    return true;
/* 223:    */  }
/* 224:    */  
/* 225:225 */  public double getDouble(int index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 226:226 */    return this.a[index];
/* 227:    */  }
/* 228:    */  
/* 229:229 */  public int indexOf(double k) { for (int i = 0; i < this.size; i++) if (k == this.a[i]) return i;
/* 230:230 */    return -1;
/* 231:    */  }
/* 232:    */  
/* 233:233 */  public int lastIndexOf(double k) { for (int i = this.size; i-- != 0;) if (k == this.a[i]) return i;
/* 234:234 */    return -1;
/* 235:    */  }
/* 236:    */  
/* 237:237 */  public double removeDouble(int index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 238:238 */    double old = this.a[index];
/* 239:239 */    this.size -= 1;
/* 240:240 */    if (index != this.size) { System.arraycopy(this.a, index + 1, this.a, index, this.size - index);
/* 241:    */    }
/* 242:242 */    return old;
/* 243:    */  }
/* 244:    */  
/* 245:245 */  public boolean rem(double k) { int index = indexOf(k);
/* 246:246 */    if (index == -1) return false;
/* 247:247 */    removeDouble(index);
/* 248:    */    
/* 249:249 */    return true;
/* 250:    */  }
/* 251:    */  
/* 252:252 */  public double set(int index, double k) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 253:253 */    double old = this.a[index];
/* 254:254 */    this.a[index] = k;
/* 255:255 */    return old;
/* 256:    */  }
/* 257:    */  
/* 258:258 */  public void clear() { this.size = 0; }
/* 259:    */  
/* 262:262 */  public int size() { return this.size; }
/* 263:    */  
/* 264:    */  public void size(int size) {
/* 265:265 */    if (size > this.a.length) ensureCapacity(size);
/* 266:266 */    if (size > this.size) DoubleArrays.fill(this.a, this.size, size, 0.0D);
/* 267:267 */    this.size = size;
/* 268:    */  }
/* 269:    */  
/* 270:270 */  public boolean isEmpty() { return this.size == 0; }
/* 271:    */  
/* 275:    */  public void trim()
/* 276:    */  {
/* 277:277 */    trim(0);
/* 278:    */  }
/* 279:    */  
/* 294:    */  public void trim(int n)
/* 295:    */  {
/* 296:296 */    if ((n >= this.a.length) || (this.size == this.a.length)) return;
/* 297:297 */    double[] t = new double[Math.max(n, this.size)];
/* 298:298 */    System.arraycopy(this.a, 0, t, 0, this.size);
/* 299:299 */    this.a = t;
/* 300:    */  }
/* 301:    */  
/* 308:    */  public void getElements(int from, double[] a, int offset, int length)
/* 309:    */  {
/* 310:310 */    DoubleArrays.ensureOffsetLength(a, offset, length);
/* 311:311 */    System.arraycopy(this.a, from, a, offset, length);
/* 312:    */  }
/* 313:    */  
/* 317:    */  public void removeElements(int from, int to)
/* 318:    */  {
/* 319:319 */    Arrays.ensureFromTo(this.size, from, to);
/* 320:320 */    System.arraycopy(this.a, to, this.a, from, this.size - to);
/* 321:321 */    this.size -= to - from;
/* 322:    */  }
/* 323:    */  
/* 329:    */  public void addElements(int index, double[] a, int offset, int length)
/* 330:    */  {
/* 331:331 */    ensureIndex(index);
/* 332:332 */    DoubleArrays.ensureOffsetLength(a, offset, length);
/* 333:333 */    grow(this.size + length);
/* 334:334 */    System.arraycopy(this.a, index, this.a, index + length, this.size - index);
/* 335:335 */    System.arraycopy(a, offset, this.a, index, length);
/* 336:336 */    this.size += length;
/* 337:    */  }
/* 338:    */  
/* 339:339 */  public double[] toDoubleArray(double[] a) { if ((a == null) || (a.length < this.size)) a = new double[this.size];
/* 340:340 */    System.arraycopy(this.a, 0, a, 0, this.size);
/* 341:341 */    return a;
/* 342:    */  }
/* 343:    */  
/* 344:344 */  public boolean addAll(int index, DoubleCollection c) { ensureIndex(index);
/* 345:345 */    int n = c.size();
/* 346:346 */    if (n == 0) return false;
/* 347:347 */    grow(this.size + n);
/* 348:348 */    if (index != this.size) System.arraycopy(this.a, index, this.a, index + n, this.size - index);
/* 349:349 */    DoubleIterator i = c.iterator();
/* 350:350 */    this.size += n;
/* 351:351 */    while (n-- != 0) { this.a[(index++)] = i.nextDouble();
/* 352:    */    }
/* 353:353 */    return true;
/* 354:    */  }
/* 355:    */  
/* 356:356 */  public boolean addAll(int index, DoubleList l) { ensureIndex(index);
/* 357:357 */    int n = l.size();
/* 358:358 */    if (n == 0) return false;
/* 359:359 */    grow(this.size + n);
/* 360:360 */    if (index != this.size) System.arraycopy(this.a, index, this.a, index + n, this.size - index);
/* 361:361 */    l.getElements(0, this.a, index, n);
/* 362:362 */    this.size += n;
/* 363:    */    
/* 364:364 */    return true;
/* 365:    */  }
/* 366:    */  
/* 367:367 */  public DoubleListIterator listIterator(final int index) { ensureIndex(index);
/* 368:368 */    new AbstractDoubleListIterator() {
/* 369:369 */      int last = -1; int pos = index;
/* 370:370 */      public boolean hasNext() { return this.pos < DoubleArrayList.this.size; }
/* 371:371 */      public boolean hasPrevious() { return this.pos > 0; }
/* 372:372 */      public double nextDouble() { if (!hasNext()) throw new NoSuchElementException(); return DoubleArrayList.this.a[(this.last = this.pos++)]; }
/* 373:373 */      public double previousDouble() { if (!hasPrevious()) throw new NoSuchElementException(); return DoubleArrayList.this.a[(this.last = --this.pos)]; }
/* 374:374 */      public int nextIndex() { return this.pos; }
/* 375:375 */      public int previousIndex() { return this.pos - 1; }
/* 376:    */      
/* 377:377 */      public void add(double k) { if (this.last == -1) throw new IllegalStateException();
/* 378:378 */        DoubleArrayList.this.add(this.pos++, k);
/* 379:379 */        this.last = -1;
/* 380:    */      }
/* 381:    */      
/* 382:382 */      public void set(double k) { if (this.last == -1) throw new IllegalStateException();
/* 383:383 */        DoubleArrayList.this.set(this.last, k);
/* 384:    */      }
/* 385:    */      
/* 386:386 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 387:387 */        DoubleArrayList.this.removeDouble(this.last);
/* 388:    */        
/* 389:389 */        if (this.last < this.pos) this.pos -= 1;
/* 390:390 */        this.last = -1;
/* 391:    */      }
/* 392:    */    };
/* 393:    */  }
/* 394:    */  
/* 395:    */  public DoubleArrayList clone() {
/* 396:396 */    DoubleArrayList c = new DoubleArrayList(this.size);
/* 397:397 */    System.arraycopy(this.a, 0, c.a, 0, this.size);
/* 398:398 */    c.size = this.size;
/* 399:399 */    return c;
/* 400:    */  }
/* 401:    */  
/* 408:    */  public boolean equals(DoubleArrayList l)
/* 409:    */  {
/* 410:410 */    if (l == this) return true;
/* 411:411 */    int s = size();
/* 412:412 */    if (s != l.size()) return false;
/* 413:413 */    double[] a1 = this.a;
/* 414:414 */    double[] a2 = l.a;
/* 415:415 */    while (s-- != 0) if (a1[s] != a2[s]) return false;
/* 416:416 */    return true;
/* 417:    */  }
/* 418:    */  
/* 428:    */  public int compareTo(DoubleArrayList l)
/* 429:    */  {
/* 430:430 */    int s1 = size();int s2 = l.size();
/* 431:431 */    double[] a1 = this.a;double[] a2 = l.a;
/* 432:    */    
/* 434:434 */    for (int i = 0; (i < s1) && (i < s2); i++) {
/* 435:435 */      double e1 = a1[i];
/* 436:436 */      double e2 = a2[i];
/* 437:437 */      int r; if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/* 438:    */    }
/* 439:439 */    return i < s1 ? 1 : i < s2 ? -1 : 0;
/* 440:    */  }
/* 441:    */  
/* 442:442 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 443:443 */    for (int i = 0; i < this.size; i++) s.writeDouble(this.a[i]);
/* 444:    */  }
/* 445:    */  
/* 446:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 447:447 */    s.defaultReadObject();
/* 448:448 */    this.a = new double[this.size];
/* 449:449 */    for (int i = 0; i < this.size; i++) this.a[i] = s.readDouble();
/* 450:    */  }
/* 451:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */