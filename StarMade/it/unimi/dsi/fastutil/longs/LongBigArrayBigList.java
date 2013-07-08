/*   1:    */package it.unimi.dsi.fastutil.longs;
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
/*  76:    */public class LongBigArrayBigList
/*  77:    */  extends AbstractLongBigList
/*  78:    */  implements RandomAccess, Cloneable, Serializable
/*  79:    */{
/*  80:    */  public static final long serialVersionUID = -7046029254386353130L;
/*  81:    */  public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*  82:    */  protected static final long ONEOVERPHI = 106039L;
/*  83:    */  protected transient long[][] a;
/*  84:    */  protected long size;
/*  85:    */  private static final boolean ASSERTS = false;
/*  86:    */  
/*  87:    */  protected LongBigArrayBigList(long[][] a, boolean dummy)
/*  88:    */  {
/*  89: 89 */    this.a = a;
/*  90:    */  }
/*  91:    */  
/*  95:    */  public LongBigArrayBigList(long capacity)
/*  96:    */  {
/*  97: 97 */    if (capacity < 0L) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  98: 98 */    this.a = LongBigArrays.newBigArray(capacity);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public LongBigArrayBigList()
/* 102:    */  {
/* 103:103 */    this(16L);
/* 104:    */  }
/* 105:    */  
/* 108:    */  public LongBigArrayBigList(LongCollection c)
/* 109:    */  {
/* 110:110 */    this(c.size());
/* 111:111 */    for (LongIterator i = c.iterator(); i.hasNext(); add(i.nextLong())) {}
/* 112:    */  }
/* 113:    */  
/* 116:    */  public LongBigArrayBigList(LongBigList l)
/* 117:    */  {
/* 118:118 */    this(l.size64());
/* 119:119 */    l.getElements(0L, this.a, 0L, this.size = l.size64());
/* 120:    */  }
/* 121:    */  
/* 129:    */  public LongBigArrayBigList(long[][] a)
/* 130:    */  {
/* 131:131 */    this(a, 0L, LongBigArrays.length(a));
/* 132:    */  }
/* 133:    */  
/* 143:    */  public LongBigArrayBigList(long[][] a, long offset, long length)
/* 144:    */  {
/* 145:145 */    this(length);
/* 146:146 */    LongBigArrays.copy(a, offset, this.a, 0L, length);
/* 147:147 */    this.size = length;
/* 148:    */  }
/* 149:    */  
/* 152:    */  public LongBigArrayBigList(Iterator<? extends Long> i)
/* 153:    */  {
/* 154:154 */    this();
/* 155:155 */    while (i.hasNext()) { add((Long)i.next());
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 160:    */  public LongBigArrayBigList(LongIterator i)
/* 161:    */  {
/* 162:162 */    this();
/* 163:163 */    while (i.hasNext()) { add(i.nextLong());
/* 164:    */    }
/* 165:    */  }
/* 166:    */  
/* 168:    */  public long[][] elements()
/* 169:    */  {
/* 170:170 */    return this.a;
/* 171:    */  }
/* 172:    */  
/* 177:    */  public static LongBigArrayBigList wrap(long[][] a, long length)
/* 178:    */  {
/* 179:179 */    if (length > LongBigArrays.length(a)) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + LongBigArrays.length(a) + ")");
/* 180:180 */    LongBigArrayBigList l = new LongBigArrayBigList(a, false);
/* 181:181 */    l.size = length;
/* 182:182 */    return l;
/* 183:    */  }
/* 184:    */  
/* 188:    */  public static LongBigArrayBigList wrap(long[][] a)
/* 189:    */  {
/* 190:190 */    return wrap(a, LongBigArrays.length(a));
/* 191:    */  }
/* 192:    */  
/* 196:    */  public void ensureCapacity(long capacity)
/* 197:    */  {
/* 198:198 */    this.a = LongBigArrays.ensureCapacity(this.a, capacity, this.size);
/* 199:    */  }
/* 200:    */  
/* 206:    */  private void grow(long capacity)
/* 207:    */  {
/* 208:208 */    this.a = LongBigArrays.grow(this.a, capacity, this.size);
/* 209:    */  }
/* 210:    */  
/* 211:    */  public void add(long index, long k) {
/* 212:212 */    ensureIndex(index);
/* 213:213 */    grow(this.size + 1L);
/* 214:214 */    if (index != this.size) LongBigArrays.copy(this.a, index, this.a, index + 1L, this.size - index);
/* 215:215 */    LongBigArrays.set(this.a, index, k);
/* 216:216 */    this.size += 1L;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public boolean add(long k) {
/* 220:220 */    grow(this.size + 1L);
/* 221:221 */    LongBigArrays.set(this.a, this.size++, k);
/* 222:    */    
/* 223:223 */    return true;
/* 224:    */  }
/* 225:    */  
/* 226:226 */  public long getLong(long index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 227:227 */    return LongBigArrays.get(this.a, index);
/* 228:    */  }
/* 229:    */  
/* 230:230 */  public long indexOf(long k) { for (long i = 0L; i < this.size; i += 1L) if (k == LongBigArrays.get(this.a, i)) return i;
/* 231:231 */    return -1L;
/* 232:    */  }
/* 233:    */  
/* 234:234 */  public long lastIndexOf(long k) { for (long i = this.size; i-- != 0L;) if (k == LongBigArrays.get(this.a, i)) return i;
/* 235:235 */    return -1L;
/* 236:    */  }
/* 237:    */  
/* 238:238 */  public long removeLong(long index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 239:239 */    long old = LongBigArrays.get(this.a, index);
/* 240:240 */    this.size -= 1L;
/* 241:241 */    if (index != this.size) { LongBigArrays.copy(this.a, index + 1L, this.a, index, this.size - index);
/* 242:    */    }
/* 243:243 */    return old;
/* 244:    */  }
/* 245:    */  
/* 246:246 */  public boolean rem(long k) { long index = indexOf(k);
/* 247:247 */    if (index == -1L) return false;
/* 248:248 */    removeLong(index);
/* 249:    */    
/* 250:250 */    return true;
/* 251:    */  }
/* 252:    */  
/* 253:253 */  public long set(long index, long k) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 254:254 */    long old = LongBigArrays.get(this.a, index);
/* 255:255 */    LongBigArrays.set(this.a, index, k);
/* 256:256 */    return old;
/* 257:    */  }
/* 258:    */  
/* 259:259 */  public void clear() { this.size = 0L; }
/* 260:    */  
/* 263:263 */  public long size64() { return this.size; }
/* 264:    */  
/* 265:    */  public void size(long size) {
/* 266:266 */    if (size > LongBigArrays.length(this.a)) ensureCapacity(size);
/* 267:267 */    if (size > this.size) LongBigArrays.fill(this.a, this.size, size, 0L);
/* 268:268 */    this.size = size;
/* 269:    */  }
/* 270:    */  
/* 271:271 */  public boolean isEmpty() { return this.size == 0L; }
/* 272:    */  
/* 276:    */  public void trim()
/* 277:    */  {
/* 278:278 */    trim(0L);
/* 279:    */  }
/* 280:    */  
/* 294:    */  public void trim(long n)
/* 295:    */  {
/* 296:296 */    long arrayLength = LongBigArrays.length(this.a);
/* 297:297 */    if ((n >= arrayLength) || (this.size == arrayLength)) return;
/* 298:298 */    this.a = LongBigArrays.trim(this.a, Math.max(n, this.size));
/* 299:    */  }
/* 300:    */  
/* 307:    */  public void getElements(int from, long[][] a, long offset, long length)
/* 308:    */  {
/* 309:309 */    LongBigArrays.copy(this.a, from, a, offset, length);
/* 310:    */  }
/* 311:    */  
/* 315:    */  public void removeElements(int from, int to)
/* 316:    */  {
/* 317:317 */    BigArrays.ensureFromTo(this.size, from, to);
/* 318:318 */    LongBigArrays.copy(this.a, to, this.a, from, this.size - to);
/* 319:319 */    this.size -= to - from;
/* 320:    */  }
/* 321:    */  
/* 327:    */  public void addElements(int index, long[][] a, long offset, long length)
/* 328:    */  {
/* 329:329 */    ensureIndex(index);
/* 330:330 */    LongBigArrays.ensureOffsetLength(a, offset, length);
/* 331:331 */    grow(this.size + length);
/* 332:332 */    LongBigArrays.copy(this.a, index, this.a, index + length, this.size - index);
/* 333:333 */    LongBigArrays.copy(a, offset, this.a, index, length);
/* 334:334 */    this.size += length;
/* 335:    */  }
/* 336:    */  
/* 337:337 */  public LongBigListIterator listIterator(final int index) { ensureIndex(index);
/* 338:338 */    new AbstractLongBigListIterator() {
/* 339:339 */      int last = -1; int pos = index;
/* 340:340 */      public boolean hasNext() { return this.pos < LongBigArrayBigList.this.size; }
/* 341:341 */      public boolean hasPrevious() { return this.pos > 0; }
/* 342:342 */      public long nextLong() { if (!hasNext()) throw new NoSuchElementException(); return LongBigArrays.get(LongBigArrayBigList.this.a, this.last = this.pos++); }
/* 343:343 */      public long previousLong() { if (!hasPrevious()) throw new NoSuchElementException(); return LongBigArrays.get(LongBigArrayBigList.this.a, this.last = --this.pos); }
/* 344:344 */      public long nextIndex() { return this.pos; }
/* 345:345 */      public long previousIndex() { return this.pos - 1; }
/* 346:    */      
/* 347:347 */      public void add(long k) { if (this.last == -1) throw new IllegalStateException();
/* 348:348 */        LongBigArrayBigList.this.add(this.pos++, k);
/* 349:349 */        this.last = -1;
/* 350:    */      }
/* 351:    */      
/* 352:352 */      public void set(long k) { if (this.last == -1) throw new IllegalStateException();
/* 353:353 */        LongBigArrayBigList.this.set(this.last, k);
/* 354:    */      }
/* 355:    */      
/* 356:356 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 357:357 */        LongBigArrayBigList.this.removeLong(this.last);
/* 358:    */        
/* 359:359 */        if (this.last < this.pos) this.pos -= 1;
/* 360:360 */        this.last = -1;
/* 361:    */      }
/* 362:    */    };
/* 363:    */  }
/* 364:    */  
/* 365:    */  public LongBigArrayBigList clone() {
/* 366:366 */    LongBigArrayBigList c = new LongBigArrayBigList(this.size);
/* 367:367 */    LongBigArrays.copy(this.a, 0L, c.a, 0L, this.size);
/* 368:368 */    c.size = this.size;
/* 369:369 */    return c;
/* 370:    */  }
/* 371:    */  
/* 378:    */  public boolean equals(LongBigArrayBigList l)
/* 379:    */  {
/* 380:380 */    if (l == this) return true;
/* 381:381 */    long s = size64();
/* 382:382 */    if (s != l.size64()) return false;
/* 383:383 */    long[][] a1 = this.a;
/* 384:384 */    long[][] a2 = l.a;
/* 385:385 */    while (s-- != 0L) if (LongBigArrays.get(a1, s) != LongBigArrays.get(a2, s)) return false;
/* 386:386 */    return true;
/* 387:    */  }
/* 388:    */  
/* 398:    */  public int compareTo(LongBigArrayBigList l)
/* 399:    */  {
/* 400:400 */    long s1 = size64();long s2 = l.size64();
/* 401:401 */    long[][] a1 = this.a;long[][] a2 = l.a;
/* 402:    */    
/* 404:404 */    for (int i = 0; (i < s1) && (i < s2); i++) {
/* 405:405 */      long e1 = LongBigArrays.get(a1, i);
/* 406:406 */      long e2 = LongBigArrays.get(a2, i);
/* 407:407 */      int r; if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/* 408:    */    }
/* 409:409 */    return i < s1 ? 1 : i < s2 ? -1 : 0;
/* 410:    */  }
/* 411:    */  
/* 412:412 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 413:413 */    for (int i = 0; i < this.size; i++) s.writeLong(LongBigArrays.get(this.a, i));
/* 414:    */  }
/* 415:    */  
/* 416:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 417:417 */    s.defaultReadObject();
/* 418:418 */    this.a = LongBigArrays.newBigArray(this.size);
/* 419:419 */    for (int i = 0; i < this.size; i++) LongBigArrays.set(this.a, i, s.readLong());
/* 420:    */  }
/* 421:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */