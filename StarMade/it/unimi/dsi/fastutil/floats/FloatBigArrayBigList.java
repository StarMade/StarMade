/*   1:    */package it.unimi.dsi.fastutil.floats;
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
/*  76:    */public class FloatBigArrayBigList
/*  77:    */  extends AbstractFloatBigList
/*  78:    */  implements RandomAccess, Cloneable, Serializable
/*  79:    */{
/*  80:    */  public static final long serialVersionUID = -7046029254386353130L;
/*  81:    */  public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*  82:    */  protected static final long ONEOVERPHI = 106039L;
/*  83:    */  protected transient float[][] a;
/*  84:    */  protected long size;
/*  85:    */  private static final boolean ASSERTS = false;
/*  86:    */  
/*  87:    */  protected FloatBigArrayBigList(float[][] a, boolean dummy)
/*  88:    */  {
/*  89: 89 */    this.a = a;
/*  90:    */  }
/*  91:    */  
/*  95:    */  public FloatBigArrayBigList(long capacity)
/*  96:    */  {
/*  97: 97 */    if (capacity < 0L) throw new IllegalArgumentException("Initial capacity (" + capacity + ") is negative");
/*  98: 98 */    this.a = FloatBigArrays.newBigArray(capacity);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public FloatBigArrayBigList()
/* 102:    */  {
/* 103:103 */    this(16L);
/* 104:    */  }
/* 105:    */  
/* 108:    */  public FloatBigArrayBigList(FloatCollection c)
/* 109:    */  {
/* 110:110 */    this(c.size());
/* 111:111 */    for (FloatIterator i = c.iterator(); i.hasNext(); add(i.nextFloat())) {}
/* 112:    */  }
/* 113:    */  
/* 116:    */  public FloatBigArrayBigList(FloatBigList l)
/* 117:    */  {
/* 118:118 */    this(l.size64());
/* 119:119 */    l.getElements(0L, this.a, 0L, this.size = l.size64());
/* 120:    */  }
/* 121:    */  
/* 129:    */  public FloatBigArrayBigList(float[][] a)
/* 130:    */  {
/* 131:131 */    this(a, 0L, FloatBigArrays.length(a));
/* 132:    */  }
/* 133:    */  
/* 143:    */  public FloatBigArrayBigList(float[][] a, long offset, long length)
/* 144:    */  {
/* 145:145 */    this(length);
/* 146:146 */    FloatBigArrays.copy(a, offset, this.a, 0L, length);
/* 147:147 */    this.size = length;
/* 148:    */  }
/* 149:    */  
/* 152:    */  public FloatBigArrayBigList(Iterator<? extends Float> i)
/* 153:    */  {
/* 154:154 */    this();
/* 155:155 */    while (i.hasNext()) { add((Float)i.next());
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 160:    */  public FloatBigArrayBigList(FloatIterator i)
/* 161:    */  {
/* 162:162 */    this();
/* 163:163 */    while (i.hasNext()) { add(i.nextFloat());
/* 164:    */    }
/* 165:    */  }
/* 166:    */  
/* 168:    */  public float[][] elements()
/* 169:    */  {
/* 170:170 */    return this.a;
/* 171:    */  }
/* 172:    */  
/* 177:    */  public static FloatBigArrayBigList wrap(float[][] a, long length)
/* 178:    */  {
/* 179:179 */    if (length > FloatBigArrays.length(a)) throw new IllegalArgumentException("The specified length (" + length + ") is greater than the array size (" + FloatBigArrays.length(a) + ")");
/* 180:180 */    FloatBigArrayBigList l = new FloatBigArrayBigList(a, false);
/* 181:181 */    l.size = length;
/* 182:182 */    return l;
/* 183:    */  }
/* 184:    */  
/* 188:    */  public static FloatBigArrayBigList wrap(float[][] a)
/* 189:    */  {
/* 190:190 */    return wrap(a, FloatBigArrays.length(a));
/* 191:    */  }
/* 192:    */  
/* 196:    */  public void ensureCapacity(long capacity)
/* 197:    */  {
/* 198:198 */    this.a = FloatBigArrays.ensureCapacity(this.a, capacity, this.size);
/* 199:    */  }
/* 200:    */  
/* 206:    */  private void grow(long capacity)
/* 207:    */  {
/* 208:208 */    this.a = FloatBigArrays.grow(this.a, capacity, this.size);
/* 209:    */  }
/* 210:    */  
/* 211:    */  public void add(long index, float k) {
/* 212:212 */    ensureIndex(index);
/* 213:213 */    grow(this.size + 1L);
/* 214:214 */    if (index != this.size) FloatBigArrays.copy(this.a, index, this.a, index + 1L, this.size - index);
/* 215:215 */    FloatBigArrays.set(this.a, index, k);
/* 216:216 */    this.size += 1L;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public boolean add(float k) {
/* 220:220 */    grow(this.size + 1L);
/* 221:221 */    FloatBigArrays.set(this.a, this.size++, k);
/* 222:    */    
/* 223:223 */    return true;
/* 224:    */  }
/* 225:    */  
/* 226:226 */  public float getFloat(long index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 227:227 */    return FloatBigArrays.get(this.a, index);
/* 228:    */  }
/* 229:    */  
/* 230:230 */  public long indexOf(float k) { for (long i = 0L; i < this.size; i += 1L) if (k == FloatBigArrays.get(this.a, i)) return i;
/* 231:231 */    return -1L;
/* 232:    */  }
/* 233:    */  
/* 234:234 */  public long lastIndexOf(float k) { for (long i = this.size; i-- != 0L;) if (k == FloatBigArrays.get(this.a, i)) return i;
/* 235:235 */    return -1L;
/* 236:    */  }
/* 237:    */  
/* 238:238 */  public float removeFloat(long index) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 239:239 */    float old = FloatBigArrays.get(this.a, index);
/* 240:240 */    this.size -= 1L;
/* 241:241 */    if (index != this.size) { FloatBigArrays.copy(this.a, index + 1L, this.a, index, this.size - index);
/* 242:    */    }
/* 243:243 */    return old;
/* 244:    */  }
/* 245:    */  
/* 246:246 */  public boolean rem(float k) { long index = indexOf(k);
/* 247:247 */    if (index == -1L) return false;
/* 248:248 */    removeFloat(index);
/* 249:    */    
/* 250:250 */    return true;
/* 251:    */  }
/* 252:    */  
/* 253:253 */  public float set(long index, float k) { if (index >= this.size) throw new IndexOutOfBoundsException("Index (" + index + ") is greater than or equal to list size (" + this.size + ")");
/* 254:254 */    float old = FloatBigArrays.get(this.a, index);
/* 255:255 */    FloatBigArrays.set(this.a, index, k);
/* 256:256 */    return old;
/* 257:    */  }
/* 258:    */  
/* 259:259 */  public void clear() { this.size = 0L; }
/* 260:    */  
/* 263:263 */  public long size64() { return this.size; }
/* 264:    */  
/* 265:    */  public void size(long size) {
/* 266:266 */    if (size > FloatBigArrays.length(this.a)) ensureCapacity(size);
/* 267:267 */    if (size > this.size) FloatBigArrays.fill(this.a, this.size, size, 0.0F);
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
/* 296:296 */    long arrayLength = FloatBigArrays.length(this.a);
/* 297:297 */    if ((n >= arrayLength) || (this.size == arrayLength)) return;
/* 298:298 */    this.a = FloatBigArrays.trim(this.a, Math.max(n, this.size));
/* 299:    */  }
/* 300:    */  
/* 307:    */  public void getElements(int from, float[][] a, long offset, long length)
/* 308:    */  {
/* 309:309 */    FloatBigArrays.copy(this.a, from, a, offset, length);
/* 310:    */  }
/* 311:    */  
/* 315:    */  public void removeElements(int from, int to)
/* 316:    */  {
/* 317:317 */    BigArrays.ensureFromTo(this.size, from, to);
/* 318:318 */    FloatBigArrays.copy(this.a, to, this.a, from, this.size - to);
/* 319:319 */    this.size -= to - from;
/* 320:    */  }
/* 321:    */  
/* 327:    */  public void addElements(int index, float[][] a, long offset, long length)
/* 328:    */  {
/* 329:329 */    ensureIndex(index);
/* 330:330 */    FloatBigArrays.ensureOffsetLength(a, offset, length);
/* 331:331 */    grow(this.size + length);
/* 332:332 */    FloatBigArrays.copy(this.a, index, this.a, index + length, this.size - index);
/* 333:333 */    FloatBigArrays.copy(a, offset, this.a, index, length);
/* 334:334 */    this.size += length;
/* 335:    */  }
/* 336:    */  
/* 337:337 */  public FloatBigListIterator listIterator(final int index) { ensureIndex(index);
/* 338:338 */    new AbstractFloatBigListIterator() {
/* 339:339 */      int last = -1; int pos = index;
/* 340:340 */      public boolean hasNext() { return this.pos < FloatBigArrayBigList.this.size; }
/* 341:341 */      public boolean hasPrevious() { return this.pos > 0; }
/* 342:342 */      public float nextFloat() { if (!hasNext()) throw new NoSuchElementException(); return FloatBigArrays.get(FloatBigArrayBigList.this.a, this.last = this.pos++); }
/* 343:343 */      public float previousFloat() { if (!hasPrevious()) throw new NoSuchElementException(); return FloatBigArrays.get(FloatBigArrayBigList.this.a, this.last = --this.pos); }
/* 344:344 */      public long nextIndex() { return this.pos; }
/* 345:345 */      public long previousIndex() { return this.pos - 1; }
/* 346:    */      
/* 347:347 */      public void add(float k) { if (this.last == -1) throw new IllegalStateException();
/* 348:348 */        FloatBigArrayBigList.this.add(this.pos++, k);
/* 349:349 */        this.last = -1;
/* 350:    */      }
/* 351:    */      
/* 352:352 */      public void set(float k) { if (this.last == -1) throw new IllegalStateException();
/* 353:353 */        FloatBigArrayBigList.this.set(this.last, k);
/* 354:    */      }
/* 355:    */      
/* 356:356 */      public void remove() { if (this.last == -1) throw new IllegalStateException();
/* 357:357 */        FloatBigArrayBigList.this.removeFloat(this.last);
/* 358:    */        
/* 359:359 */        if (this.last < this.pos) this.pos -= 1;
/* 360:360 */        this.last = -1;
/* 361:    */      }
/* 362:    */    };
/* 363:    */  }
/* 364:    */  
/* 365:    */  public FloatBigArrayBigList clone() {
/* 366:366 */    FloatBigArrayBigList c = new FloatBigArrayBigList(this.size);
/* 367:367 */    FloatBigArrays.copy(this.a, 0L, c.a, 0L, this.size);
/* 368:368 */    c.size = this.size;
/* 369:369 */    return c;
/* 370:    */  }
/* 371:    */  
/* 378:    */  public boolean equals(FloatBigArrayBigList l)
/* 379:    */  {
/* 380:380 */    if (l == this) return true;
/* 381:381 */    long s = size64();
/* 382:382 */    if (s != l.size64()) return false;
/* 383:383 */    float[][] a1 = this.a;
/* 384:384 */    float[][] a2 = l.a;
/* 385:385 */    while (s-- != 0L) if (FloatBigArrays.get(a1, s) != FloatBigArrays.get(a2, s)) return false;
/* 386:386 */    return true;
/* 387:    */  }
/* 388:    */  
/* 398:    */  public int compareTo(FloatBigArrayBigList l)
/* 399:    */  {
/* 400:400 */    long s1 = size64();long s2 = l.size64();
/* 401:401 */    float[][] a1 = this.a;float[][] a2 = l.a;
/* 402:    */    
/* 404:404 */    for (int i = 0; (i < s1) && (i < s2); i++) {
/* 405:405 */      float e1 = FloatBigArrays.get(a1, i);
/* 406:406 */      float e2 = FloatBigArrays.get(a2, i);
/* 407:407 */      int r; if ((r = e1 == e2 ? 0 : e1 < e2 ? -1 : 1) != 0) return r;
/* 408:    */    }
/* 409:409 */    return i < s1 ? 1 : i < s2 ? -1 : 0;
/* 410:    */  }
/* 411:    */  
/* 412:412 */  private void writeObject(ObjectOutputStream s) throws IOException { s.defaultWriteObject();
/* 413:413 */    for (int i = 0; i < this.size; i++) s.writeFloat(FloatBigArrays.get(this.a, i));
/* 414:    */  }
/* 415:    */  
/* 416:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 417:417 */    s.defaultReadObject();
/* 418:418 */    this.a = FloatBigArrays.newBigArray(this.size);
/* 419:419 */    for (int i = 0; i < this.size; i++) FloatBigArrays.set(this.a, i, s.readFloat());
/* 420:    */  }
/* 421:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigArrayBigList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */