/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigList;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.Random;
/*   7:    */
/*  59:    */public class ByteBigLists
/*  60:    */{
/*  61:    */  public static ByteBigList shuffle(ByteBigList l, Random random)
/*  62:    */  {
/*  63: 63 */    for (long i = l.size64(); i-- != 0L;) {
/*  64: 64 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/*  65: 65 */      byte t = l.getByte(i);
/*  66: 66 */      l.set(i, l.getByte(p));
/*  67: 67 */      l.set(p, t);
/*  68:    */    }
/*  69: 69 */    return l;
/*  70:    */  }
/*  71:    */  
/*  73:    */  public static class EmptyBigList
/*  74:    */    extends ByteCollections.EmptyCollection
/*  75:    */    implements ByteBigList, Serializable, Cloneable
/*  76:    */  {
/*  77:    */    public static final long serialVersionUID = -7046029254386353129L;
/*  78:    */    
/*  79: 79 */    public void add(long index, byte k) { throw new UnsupportedOperationException(); }
/*  80: 80 */    public boolean add(byte k) { throw new UnsupportedOperationException(); }
/*  81: 81 */    public byte removeByte(long i) { throw new UnsupportedOperationException(); }
/*  82: 82 */    public byte set(long index, byte k) { throw new UnsupportedOperationException(); }
/*  83: 83 */    public long indexOf(byte k) { return -1L; }
/*  84: 84 */    public long lastIndexOf(byte k) { return -1L; }
/*  85: 85 */    public boolean addAll(Collection<? extends Byte> c) { throw new UnsupportedOperationException(); }
/*  86: 86 */    public boolean addAll(long i, Collection<? extends Byte> c) { throw new UnsupportedOperationException(); }
/*  87: 87 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/*  88: 88 */    public Byte get(long i) { throw new IndexOutOfBoundsException(); }
/*  89: 89 */    public boolean addAll(ByteCollection c) { throw new UnsupportedOperationException(); }
/*  90: 90 */    public boolean addAll(ByteBigList c) { throw new UnsupportedOperationException(); }
/*  91: 91 */    public boolean addAll(long i, ByteCollection c) { throw new UnsupportedOperationException(); }
/*  92: 92 */    public boolean addAll(long i, ByteBigList c) { throw new UnsupportedOperationException(); }
/*  93: 93 */    public void add(long index, Byte k) { throw new UnsupportedOperationException(); }
/*  94: 94 */    public boolean add(Byte k) { throw new UnsupportedOperationException(); }
/*  95: 95 */    public Byte set(long index, Byte k) { throw new UnsupportedOperationException(); }
/*  96: 96 */    public byte getByte(long i) { throw new IndexOutOfBoundsException(); }
/*  97: 97 */    public Byte remove(long k) { throw new UnsupportedOperationException(); }
/*  98: 98 */    public long indexOf(Object k) { return -1L; }
/*  99: 99 */    public long lastIndexOf(Object k) { return -1L; }
/* 100:    */    
/* 101:101 */    public ByteBigListIterator listIterator() { return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/* 102:    */    
/* 103:103 */    public ByteBigListIterator iterator() { return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR; }
/* 104:    */    
/* 105:    */    public ByteBigListIterator listIterator(long i) {
/* 106:106 */      if (i == 0L) return ByteBigListIterators.EMPTY_BIG_LIST_ITERATOR; throw new IndexOutOfBoundsException(String.valueOf(i)); }
/* 107:    */    
/* 108:108 */    public ByteBigList subList(long from, long to) { if ((from == 0L) && (to == 0L)) return this; throw new IndexOutOfBoundsException(); }
/* 109:    */    
/* 110:110 */    public void getElements(long from, byte[][] a, long offset, long length) { ByteBigArrays.ensureOffsetLength(a, offset, length); if (from != 0L) throw new IndexOutOfBoundsException(); }
/* 111:111 */    public void removeElements(long from, long to) { throw new UnsupportedOperationException(); }
/* 112:    */    
/* 113:113 */    public void addElements(long index, byte[][] a, long offset, long length) { throw new UnsupportedOperationException(); }
/* 114:114 */    public void addElements(long index, byte[][] a) { throw new UnsupportedOperationException(); }
/* 115:    */    
/* 116:116 */    public void size(long s) { throw new UnsupportedOperationException(); }
/* 117:117 */    public long size64() { return 0L; }
/* 118:    */    
/* 119:    */    public int compareTo(BigList<? extends Byte> o) {
/* 120:120 */      if (o == this) return 0;
/* 121:121 */      return o.isEmpty() ? 0 : -1;
/* 122:    */    }
/* 123:    */    
/* 124:124 */    private Object readResolve() { return ByteBigLists.EMPTY_BIG_LIST; }
/* 125:125 */    public Object clone() { return ByteBigLists.EMPTY_BIG_LIST; }
/* 126:    */  }
/* 127:    */  
/* 136:136 */  public static final EmptyBigList EMPTY_BIG_LIST = new EmptyBigList();
/* 137:    */  
/* 141:    */  public static class Singleton
/* 142:    */    extends AbstractByteBigList
/* 143:    */    implements Serializable, Cloneable
/* 144:    */  {
/* 145:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 146:    */    
/* 149:    */    private final byte element;
/* 150:    */    
/* 153:153 */    private Singleton(byte element) { this.element = element; }
/* 154:    */    
/* 155:    */    public byte getByte(long i) {
/* 156:156 */      if (i == 0L) return this.element; throw new IndexOutOfBoundsException(); }
/* 157:157 */    public byte removeByte(long i) { throw new UnsupportedOperationException(); }
/* 158:158 */    public boolean contains(byte k) { return k == this.element; }
/* 159:    */    
/* 160:160 */    public boolean addAll(Collection<? extends Byte> c) { throw new UnsupportedOperationException(); }
/* 161:161 */    public boolean addAll(long i, Collection<? extends Byte> c) { throw new UnsupportedOperationException(); }
/* 162:162 */    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 163:163 */    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
/* 164:    */    
/* 166:    */    public byte[] toByteArray()
/* 167:    */    {
/* 168:168 */      byte[] a = new byte[1];
/* 169:169 */      a[0] = this.element;
/* 170:170 */      return a;
/* 171:    */    }
/* 172:    */    
/* 174:174 */    public ByteBigListIterator listIterator() { return ByteBigListIterators.singleton(this.element); }
/* 175:    */    
/* 176:176 */    public ByteBigListIterator iterator() { return listIterator(); }
/* 177:    */    
/* 178:    */    public ByteBigListIterator listIterator(long i) {
/* 179:179 */      if ((i > 1L) || (i < 0L)) throw new IndexOutOfBoundsException();
/* 180:180 */      ByteBigListIterator l = listIterator();
/* 181:181 */      if (i == 1L) l.next();
/* 182:182 */      return l;
/* 183:    */    }
/* 184:    */    
/* 185:    */    public ByteBigList subList(long from, long to)
/* 186:    */    {
/* 187:187 */      ensureIndex(from);
/* 188:188 */      ensureIndex(to);
/* 189:189 */      if (from > to) { throw new IndexOutOfBoundsException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 190:    */      }
/* 191:191 */      if ((from != 0L) || (to != 1L)) return ByteBigLists.EMPTY_BIG_LIST;
/* 192:192 */      return this;
/* 193:    */    }
/* 194:    */    
/* 195:    */    @Deprecated
/* 196:196 */    public int size() { return 1; }
/* 197:197 */    public long size64() { return 1L; }
/* 198:198 */    public void size(long size) { throw new UnsupportedOperationException(); }
/* 199:199 */    public void clear() { throw new UnsupportedOperationException(); }
/* 200:    */    
/* 201:201 */    public Object clone() { return this; }
/* 202:    */    
/* 204:204 */    public boolean rem(byte k) { throw new UnsupportedOperationException(); }
/* 205:205 */    public boolean addAll(ByteCollection c) { throw new UnsupportedOperationException(); }
/* 206:206 */    public boolean addAll(long i, ByteCollection c) { throw new UnsupportedOperationException(); }
/* 207:    */  }
/* 208:    */  
/* 217:    */  public static ByteBigList singleton(byte element)
/* 218:    */  {
/* 219:219 */    return new Singleton(element, null);
/* 220:    */  }
/* 221:    */  
/* 227:    */  public static ByteBigList singleton(Object element)
/* 228:    */  {
/* 229:229 */    return new Singleton(((Byte)element).byteValue(), null);
/* 230:    */  }
/* 231:    */  
/* 233:    */  public static class SynchronizedBigList
/* 234:    */    extends ByteCollections.SynchronizedCollection
/* 235:    */    implements ByteBigList, Serializable
/* 236:    */  {
/* 237:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 238:    */    
/* 239:    */    protected final ByteBigList list;
/* 240:    */    
/* 241:    */    protected SynchronizedBigList(ByteBigList l, Object sync)
/* 242:    */    {
/* 243:243 */      super(sync);
/* 244:244 */      this.list = l;
/* 245:    */    }
/* 246:    */    
/* 247:    */    protected SynchronizedBigList(ByteBigList l) {
/* 248:248 */      super();
/* 249:249 */      this.list = l;
/* 250:    */    }
/* 251:    */    
/* 252:252 */    public byte getByte(long i) { synchronized (this.sync) { return this.list.getByte(i); } }
/* 253:253 */    public byte set(long i, byte k) { synchronized (this.sync) { return this.list.set(i, k); } }
/* 254:254 */    public void add(long i, byte k) { synchronized (this.sync) { this.list.add(i, k); } }
/* 255:255 */    public byte removeByte(long i) { synchronized (this.sync) { return this.list.removeByte(i); } }
/* 256:    */    
/* 257:257 */    public long indexOf(byte k) { synchronized (this.sync) { return this.list.indexOf(k); } }
/* 258:258 */    public long lastIndexOf(byte k) { synchronized (this.sync) { return this.list.lastIndexOf(k); } }
/* 259:    */    
/* 260:260 */    public boolean addAll(long index, Collection<? extends Byte> c) { synchronized (this.sync) { return this.list.addAll(index, c); } }
/* 261:    */    
/* 262:262 */    public void getElements(long from, byte[][] a, long offset, long length) { synchronized (this.sync) { this.list.getElements(from, a, offset, length); } }
/* 263:263 */    public void removeElements(long from, long to) { synchronized (this.sync) { this.list.removeElements(from, to); } }
/* 264:264 */    public void addElements(long index, byte[][] a, long offset, long length) { synchronized (this.sync) { this.list.addElements(index, a, offset, length); } }
/* 265:265 */    public void addElements(long index, byte[][] a) { synchronized (this.sync) { this.list.addElements(index, a); } }
/* 266:266 */    public void size(long size) { synchronized (this.sync) { this.list.size(size); } }
/* 267:267 */    public long size64() { synchronized (this.sync) { return this.list.size64(); } }
/* 268:    */    
/* 269:269 */    public ByteBigListIterator iterator() { return this.list.listIterator(); }
/* 270:270 */    public ByteBigListIterator listIterator() { return this.list.listIterator(); }
/* 271:271 */    public ByteBigListIterator listIterator(long i) { return this.list.listIterator(i); }
/* 272:    */    
/* 273:273 */    public ByteBigList subList(long from, long to) { synchronized (this.sync) { return ByteBigLists.synchronize(this.list.subList(from, to), this.sync); } }
/* 274:    */    
/* 275:275 */    public boolean equals(Object o) { synchronized (this.sync) { return this.list.equals(o); } }
/* 276:276 */    public int hashCode() { synchronized (this.sync) { return this.list.hashCode();
/* 277:    */      } }
/* 278:    */    
/* 279:279 */    public int compareTo(BigList<? extends Byte> o) { synchronized (this.sync) { return this.list.compareTo(o);
/* 280:    */      }
/* 281:    */    }
/* 282:    */    
/* 283:283 */    public boolean addAll(long index, ByteCollection c) { synchronized (this.sync) { return this.list.addAll(index, c); } }
/* 284:284 */    public boolean addAll(long index, ByteBigList l) { synchronized (this.sync) { return this.list.addAll(index, l); } }
/* 285:285 */    public boolean addAll(ByteBigList l) { synchronized (this.sync) { return this.list.addAll(l); } }
/* 286:    */    
/* 287:287 */    public Byte get(long i) { synchronized (this.sync) { return (Byte)this.list.get(i); } }
/* 288:288 */    public void add(long i, Byte k) { synchronized (this.sync) { this.list.add(i, k); } }
/* 289:289 */    public Byte set(long index, Byte k) { synchronized (this.sync) { return (Byte)this.list.set(index, k); } }
/* 290:290 */    public Byte remove(long i) { synchronized (this.sync) { return (Byte)this.list.remove(i); } }
/* 291:291 */    public long indexOf(Object o) { synchronized (this.sync) { return this.list.indexOf(o); } }
/* 292:292 */    public long lastIndexOf(Object o) { synchronized (this.sync) { return this.list.lastIndexOf(o);
/* 293:    */      }
/* 294:    */    }
/* 295:    */  }
/* 296:    */  
/* 301:    */  public static ByteBigList synchronize(ByteBigList l)
/* 302:    */  {
/* 303:303 */    return new SynchronizedBigList(l);
/* 304:    */  }
/* 305:    */  
/* 311:    */  public static ByteBigList synchronize(ByteBigList l, Object sync)
/* 312:    */  {
/* 313:313 */    return new SynchronizedBigList(l, sync);
/* 314:    */  }
/* 315:    */  
/* 317:    */  public static class UnmodifiableBigList
/* 318:    */    extends ByteCollections.UnmodifiableCollection
/* 319:    */    implements ByteBigList, Serializable
/* 320:    */  {
/* 321:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 322:    */    protected final ByteBigList list;
/* 323:    */    
/* 324:    */    protected UnmodifiableBigList(ByteBigList l)
/* 325:    */    {
/* 326:326 */      super();
/* 327:327 */      this.list = l;
/* 328:    */    }
/* 329:    */    
/* 330:330 */    public byte getByte(long i) { return this.list.getByte(i); }
/* 331:331 */    public byte set(long i, byte k) { throw new UnsupportedOperationException(); }
/* 332:332 */    public void add(long i, byte k) { throw new UnsupportedOperationException(); }
/* 333:333 */    public byte removeByte(long i) { throw new UnsupportedOperationException(); }
/* 334:    */    
/* 335:335 */    public long indexOf(byte k) { return this.list.indexOf(k); }
/* 336:336 */    public long lastIndexOf(byte k) { return this.list.lastIndexOf(k); }
/* 337:    */    
/* 338:338 */    public boolean addAll(long index, Collection<? extends Byte> c) { throw new UnsupportedOperationException(); }
/* 339:    */    
/* 340:340 */    public void getElements(long from, byte[][] a, long offset, long length) { this.list.getElements(from, a, offset, length); }
/* 341:341 */    public void removeElements(long from, long to) { throw new UnsupportedOperationException(); }
/* 342:342 */    public void addElements(long index, byte[][] a, long offset, long length) { throw new UnsupportedOperationException(); }
/* 343:343 */    public void addElements(long index, byte[][] a) { throw new UnsupportedOperationException(); }
/* 344:344 */    public void size(long size) { this.list.size(size); }
/* 345:345 */    public long size64() { return this.list.size64(); }
/* 346:    */    
/* 347:347 */    public ByteBigListIterator iterator() { return listIterator(); }
/* 348:348 */    public ByteBigListIterator listIterator() { return ByteBigListIterators.unmodifiable(this.list.listIterator()); }
/* 349:349 */    public ByteBigListIterator listIterator(long i) { return ByteBigListIterators.unmodifiable(this.list.listIterator(i)); }
/* 350:    */    
/* 351:351 */    public ByteBigList subList(long from, long to) { return ByteBigLists.unmodifiable(this.list.subList(from, to)); }
/* 352:    */    
/* 353:353 */    public boolean equals(Object o) { return this.list.equals(o); }
/* 354:354 */    public int hashCode() { return this.list.hashCode(); }
/* 355:    */    
/* 356:    */    public int compareTo(BigList<? extends Byte> o) {
/* 357:357 */      return this.list.compareTo(o);
/* 358:    */    }
/* 359:    */    
/* 361:361 */    public boolean addAll(long index, ByteCollection c) { throw new UnsupportedOperationException(); }
/* 362:362 */    public boolean addAll(ByteBigList l) { throw new UnsupportedOperationException(); }
/* 363:363 */    public boolean addAll(long index, ByteBigList l) { throw new UnsupportedOperationException(); }
/* 364:364 */    public Byte get(long i) { return (Byte)this.list.get(i); }
/* 365:365 */    public void add(long i, Byte k) { throw new UnsupportedOperationException(); }
/* 366:366 */    public Byte set(long index, Byte k) { throw new UnsupportedOperationException(); }
/* 367:367 */    public Byte remove(long i) { throw new UnsupportedOperationException(); }
/* 368:368 */    public long indexOf(Object o) { return this.list.indexOf(o); }
/* 369:369 */    public long lastIndexOf(Object o) { return this.list.lastIndexOf(o); }
/* 370:    */  }
/* 371:    */  
/* 378:    */  public static ByteBigList unmodifiable(ByteBigList l)
/* 379:    */  {
/* 380:380 */    return new UnmodifiableBigList(l);
/* 381:    */  }
/* 382:    */  
/* 383:    */  public static class ListBigList
/* 384:    */    extends AbstractByteBigList implements Serializable
/* 385:    */  {
/* 386:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 387:    */    private final ByteList list;
/* 388:    */    
/* 389:    */    protected ListBigList(ByteList list)
/* 390:    */    {
/* 391:391 */      this.list = list;
/* 392:    */    }
/* 393:    */    
/* 394:    */    private int intIndex(long index) {
/* 395:395 */      if (index >= 2147483647L) throw new IndexOutOfBoundsException("This big list is restricted to 32-bit indices");
/* 396:396 */      return (int)index;
/* 397:    */    }
/* 398:    */    
/* 399:399 */    public long size64() { return this.list.size(); }
/* 400:    */    @Deprecated
/* 401:401 */    public int size() { return this.list.size(); }
/* 402:402 */    public void size(long size) { this.list.size(intIndex(size)); }
/* 403:403 */    public ByteBigListIterator iterator() { return ByteBigListIterators.asBigListIterator(this.list.iterator()); }
/* 404:404 */    public ByteBigListIterator listIterator() { return ByteBigListIterators.asBigListIterator(this.list.listIterator()); }
/* 405:405 */    public boolean addAll(long index, Collection<? extends Byte> c) { return this.list.addAll(intIndex(index), c); }
/* 406:406 */    public ByteBigListIterator listIterator(long index) { return ByteBigListIterators.asBigListIterator(this.list.listIterator(intIndex(index))); }
/* 407:407 */    public ByteBigList subList(long from, long to) { return new ListBigList(this.list.subList(intIndex(from), intIndex(to))); }
/* 408:408 */    public boolean contains(byte key) { return this.list.contains(key); }
/* 409:409 */    public byte[] toByteArray() { return this.list.toByteArray(); }
/* 410:410 */    public void removeElements(long from, long to) { this.list.removeElements(intIndex(from), intIndex(to)); }
/* 411:    */    
/* 412:412 */    public byte[] toByteArray(byte[] a) { return this.list.toByteArray(a); }
/* 413:    */    
/* 414:414 */    public void add(long index, byte key) { this.list.add(intIndex(index), key); }
/* 415:415 */    public boolean addAll(long index, ByteCollection c) { return this.list.addAll(intIndex(index), c); }
/* 416:416 */    public boolean addAll(long index, ByteBigList c) { return this.list.addAll(intIndex(index), c); }
/* 417:417 */    public boolean add(byte key) { return this.list.add(key); }
/* 418:418 */    public boolean addAll(ByteBigList c) { return this.list.addAll(c); }
/* 419:419 */    public byte getByte(long index) { return this.list.getByte(intIndex(index)); }
/* 420:420 */    public long indexOf(byte k) { return this.list.indexOf(k); }
/* 421:421 */    public long lastIndexOf(byte k) { return this.list.lastIndexOf(k); }
/* 422:422 */    public byte removeByte(long index) { return this.list.removeByte(intIndex(index)); }
/* 423:423 */    public byte set(long index, byte k) { return this.list.set(intIndex(index), k); }
/* 424:424 */    public boolean addAll(ByteCollection c) { return this.list.addAll(c); }
/* 425:425 */    public boolean containsAll(ByteCollection c) { return this.list.containsAll(c); }
/* 426:426 */    public boolean removeAll(ByteCollection c) { return this.list.removeAll(c); }
/* 427:427 */    public boolean retainAll(ByteCollection c) { return this.list.retainAll(c); }
/* 428:428 */    public boolean isEmpty() { return this.list.isEmpty(); }
/* 429:429 */    public <T> T[] toArray(T[] a) { return this.list.toArray(a); }
/* 430:430 */    public boolean containsAll(Collection<?> c) { return this.list.containsAll(c); }
/* 431:431 */    public boolean addAll(Collection<? extends Byte> c) { return this.list.addAll(c); }
/* 432:432 */    public boolean removeAll(Collection<?> c) { return this.list.removeAll(c); }
/* 433:433 */    public boolean retainAll(Collection<?> c) { return this.list.retainAll(c); }
/* 434:434 */    public void clear() { this.list.clear(); }
/* 435:435 */    public int hashCode() { return this.list.hashCode(); }
/* 436:    */  }
/* 437:    */  
/* 441:    */  public static ByteBigList asBigList(ByteList list)
/* 442:    */  {
/* 443:443 */    return new ListBigList(list);
/* 444:    */  }
/* 445:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteBigLists
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */