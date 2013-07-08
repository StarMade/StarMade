/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   5:    */import it.unimi.dsi.fastutil.HashCommon;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.ObjectInputStream;
/*   9:    */import java.io.ObjectOutputStream;
/*  10:    */import java.io.Serializable;
/*  11:    */import java.util.Collection;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  79:    */public class ObjectOpenCustomHashSet<K>
/*  80:    */  extends AbstractObjectSet<K>
/*  81:    */  implements Serializable, Cloneable, Hash
/*  82:    */{
/*  83:    */  public static final long serialVersionUID = 0L;
/*  84:    */  private static final boolean ASSERTS = false;
/*  85:    */  protected transient K[] key;
/*  86:    */  protected transient boolean[] used;
/*  87:    */  protected final float f;
/*  88:    */  protected transient int n;
/*  89:    */  protected transient int maxFill;
/*  90:    */  protected transient int mask;
/*  91:    */  protected int size;
/*  92:    */  protected Hash.Strategy<K> strategy;
/*  93:    */  
/*  94:    */  public ObjectOpenCustomHashSet(int expected, float f, Hash.Strategy<K> strategy)
/*  95:    */  {
/*  96: 96 */    this.strategy = strategy;
/*  97: 97 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  98: 98 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  99: 99 */    this.f = f;
/* 100:100 */    this.n = HashCommon.arraySize(expected, f);
/* 101:101 */    this.mask = (this.n - 1);
/* 102:102 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 103:103 */    this.key = ((Object[])new Object[this.n]);
/* 104:104 */    this.used = new boolean[this.n];
/* 105:    */  }
/* 106:    */  
/* 110:    */  public ObjectOpenCustomHashSet(int expected, Hash.Strategy<K> strategy)
/* 111:    */  {
/* 112:112 */    this(expected, 0.75F, strategy);
/* 113:    */  }
/* 114:    */  
/* 117:    */  public ObjectOpenCustomHashSet(Hash.Strategy<K> strategy)
/* 118:    */  {
/* 119:119 */    this(16, 0.75F, strategy);
/* 120:    */  }
/* 121:    */  
/* 126:    */  public ObjectOpenCustomHashSet(Collection<? extends K> c, float f, Hash.Strategy<K> strategy)
/* 127:    */  {
/* 128:128 */    this(c.size(), f, strategy);
/* 129:129 */    addAll(c);
/* 130:    */  }
/* 131:    */  
/* 136:    */  public ObjectOpenCustomHashSet(Collection<? extends K> c, Hash.Strategy<K> strategy)
/* 137:    */  {
/* 138:138 */    this(c, 0.75F, strategy);
/* 139:    */  }
/* 140:    */  
/* 145:    */  public ObjectOpenCustomHashSet(ObjectCollection<? extends K> c, float f, Hash.Strategy<K> strategy)
/* 146:    */  {
/* 147:147 */    this(c.size(), f, strategy);
/* 148:148 */    addAll(c);
/* 149:    */  }
/* 150:    */  
/* 155:    */  public ObjectOpenCustomHashSet(ObjectCollection<? extends K> c, Hash.Strategy<K> strategy)
/* 156:    */  {
/* 157:157 */    this(c, 0.75F, strategy);
/* 158:    */  }
/* 159:    */  
/* 164:    */  public ObjectOpenCustomHashSet(ObjectIterator<K> i, float f, Hash.Strategy<K> strategy)
/* 165:    */  {
/* 166:166 */    this(16, f, strategy);
/* 167:167 */    while (i.hasNext()) { add(i.next());
/* 168:    */    }
/* 169:    */  }
/* 170:    */  
/* 173:    */  public ObjectOpenCustomHashSet(ObjectIterator<K> i, Hash.Strategy<K> strategy)
/* 174:    */  {
/* 175:175 */    this(i, 0.75F, strategy);
/* 176:    */  }
/* 177:    */  
/* 184:    */  public ObjectOpenCustomHashSet(K[] a, int offset, int length, float f, Hash.Strategy<K> strategy)
/* 185:    */  {
/* 186:186 */    this(length < 0 ? 0 : length, f, strategy);
/* 187:187 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 188:188 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 189:    */    }
/* 190:    */  }
/* 191:    */  
/* 196:    */  public ObjectOpenCustomHashSet(K[] a, int offset, int length, Hash.Strategy<K> strategy)
/* 197:    */  {
/* 198:198 */    this(a, offset, length, 0.75F, strategy);
/* 199:    */  }
/* 200:    */  
/* 205:    */  public ObjectOpenCustomHashSet(K[] a, float f, Hash.Strategy<K> strategy)
/* 206:    */  {
/* 207:207 */    this(a, 0, a.length, f, strategy);
/* 208:    */  }
/* 209:    */  
/* 214:    */  public ObjectOpenCustomHashSet(K[] a, Hash.Strategy<K> strategy)
/* 215:    */  {
/* 216:216 */    this(a, 0.75F, strategy);
/* 217:    */  }
/* 218:    */  
/* 221:    */  public Hash.Strategy<K> strategy()
/* 222:    */  {
/* 223:223 */    return this.strategy;
/* 224:    */  }
/* 225:    */  
/* 229:    */  public boolean add(K k)
/* 230:    */  {
/* 231:231 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 232:    */    
/* 233:233 */    while (this.used[pos] != 0) {
/* 234:234 */      if (this.strategy.equals(this.key[pos], k)) return false;
/* 235:235 */      pos = pos + 1 & this.mask;
/* 236:    */    }
/* 237:237 */    this.used[pos] = true;
/* 238:238 */    this.key[pos] = k;
/* 239:239 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 240:    */    }
/* 241:241 */    return true;
/* 242:    */  }
/* 243:    */  
/* 246:    */  protected final int shiftKeys(int pos)
/* 247:    */  {
/* 248:    */    int last;
/* 249:    */    
/* 251:    */    for (;;)
/* 252:    */    {
/* 253:253 */      pos = (last = pos) + 1 & this.mask;
/* 254:254 */      while (this.used[pos] != 0) {
/* 255:255 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 256:256 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 257:257 */        pos = pos + 1 & this.mask;
/* 258:    */      }
/* 259:259 */      if (this.used[pos] == 0) break;
/* 260:260 */      this.key[last] = this.key[pos];
/* 261:    */    }
/* 262:262 */    this.used[last] = false;
/* 263:263 */    this.key[last] = null;
/* 264:264 */    return last;
/* 265:    */  }
/* 266:    */  
/* 267:    */  public boolean remove(Object k)
/* 268:    */  {
/* 269:269 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 270:    */    
/* 271:271 */    while (this.used[pos] != 0) {
/* 272:272 */      if (this.strategy.equals(this.key[pos], k)) {
/* 273:273 */        this.size -= 1;
/* 274:274 */        shiftKeys(pos);
/* 275:    */        
/* 276:276 */        return true;
/* 277:    */      }
/* 278:278 */      pos = pos + 1 & this.mask;
/* 279:    */    }
/* 280:280 */    return false;
/* 281:    */  }
/* 282:    */  
/* 283:    */  public boolean contains(Object k)
/* 284:    */  {
/* 285:285 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 286:    */    
/* 287:287 */    while (this.used[pos] != 0) {
/* 288:288 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 289:289 */      pos = pos + 1 & this.mask;
/* 290:    */    }
/* 291:291 */    return false;
/* 292:    */  }
/* 293:    */  
/* 297:    */  public K get(Object k)
/* 298:    */  {
/* 299:299 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 300:    */    
/* 301:301 */    while (this.used[pos] != 0) {
/* 302:302 */      if (this.strategy.equals(this.key[pos], k)) return this.key[pos];
/* 303:303 */      pos = pos + 1 & this.mask;
/* 304:    */    }
/* 305:305 */    return null;
/* 306:    */  }
/* 307:    */  
/* 312:    */  public void clear()
/* 313:    */  {
/* 314:314 */    if (this.size == 0) return;
/* 315:315 */    this.size = 0;
/* 316:316 */    BooleanArrays.fill(this.used, false);
/* 317:317 */    ObjectArrays.fill(this.key, null);
/* 318:    */  }
/* 319:    */  
/* 320:320 */  public int size() { return this.size; }
/* 321:    */  
/* 322:    */  public boolean isEmpty() {
/* 323:323 */    return this.size == 0;
/* 324:    */  }
/* 325:    */  
/* 331:    */  @Deprecated
/* 332:    */  public void growthFactor(int growthFactor) {}
/* 333:    */  
/* 339:    */  @Deprecated
/* 340:340 */  public int growthFactor() { return 16; }
/* 341:    */  
/* 342:    */  private class SetIterator extends AbstractObjectIterator<K> {
/* 343:    */    int pos;
/* 344:    */    int last;
/* 345:    */    
/* 346:346 */    private SetIterator() { this.pos = ObjectOpenCustomHashSet.this.n;
/* 347:    */      
/* 349:349 */      this.last = -1;
/* 350:    */      
/* 351:351 */      this.c = ObjectOpenCustomHashSet.this.size;
/* 352:    */      
/* 356:356 */      boolean[] used = ObjectOpenCustomHashSet.this.used;
/* 357:357 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 358:    */    }
/* 359:    */    
/* 360:360 */    public boolean hasNext() { return this.c != 0; }
/* 361:    */    
/* 362:    */    public K next() {
/* 363:363 */      if (!hasNext()) throw new NoSuchElementException();
/* 364:364 */      this.c -= 1;
/* 365:    */      
/* 366:366 */      if (this.pos < 0) return this.wrapped.get(-(this.last = --this.pos) - 2);
/* 367:367 */      K retVal = ObjectOpenCustomHashSet.this.key[(this.last = this.pos)];
/* 368:    */      
/* 369:369 */      if (this.c != 0) {
/* 370:370 */        boolean[] used = ObjectOpenCustomHashSet.this.used;
/* 371:371 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 372:    */      }
/* 373:    */      
/* 374:374 */      return retVal;
/* 375:    */    }
/* 376:    */    
/* 378:    */    int c;
/* 379:    */    
/* 380:    */    ObjectArrayList<K> wrapped;
/* 381:    */    
/* 382:    */    final int shiftKeys(int pos)
/* 383:    */    {
/* 384:    */      int last;
/* 385:    */      for (;;)
/* 386:    */      {
/* 387:387 */        pos = (last = pos) + 1 & ObjectOpenCustomHashSet.this.mask;
/* 388:388 */        while (ObjectOpenCustomHashSet.this.used[pos] != 0) {
/* 389:389 */          int slot = HashCommon.murmurHash3(ObjectOpenCustomHashSet.this.strategy.hashCode(ObjectOpenCustomHashSet.this.key[pos])) & ObjectOpenCustomHashSet.this.mask;
/* 390:390 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 391:391 */          pos = pos + 1 & ObjectOpenCustomHashSet.this.mask;
/* 392:    */        }
/* 393:393 */        if (ObjectOpenCustomHashSet.this.used[pos] == 0) break;
/* 394:394 */        if (pos < last)
/* 395:    */        {
/* 396:396 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 397:397 */          this.wrapped.add(ObjectOpenCustomHashSet.this.key[pos]);
/* 398:    */        }
/* 399:399 */        ObjectOpenCustomHashSet.this.key[last] = ObjectOpenCustomHashSet.this.key[pos];
/* 400:    */      }
/* 401:401 */      ObjectOpenCustomHashSet.this.used[last] = false;
/* 402:402 */      ObjectOpenCustomHashSet.this.key[last] = null;
/* 403:403 */      return last;
/* 404:    */    }
/* 405:    */    
/* 406:    */    public void remove() {
/* 407:407 */      if (this.last == -1) throw new IllegalStateException();
/* 408:408 */      if (this.pos < -1)
/* 409:    */      {
/* 410:410 */        ObjectOpenCustomHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 411:411 */        this.last = -1;
/* 412:412 */        return;
/* 413:    */      }
/* 414:414 */      ObjectOpenCustomHashSet.this.size -= 1;
/* 415:415 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 416:416 */        this.c += 1;
/* 417:417 */        next();
/* 418:    */      }
/* 419:419 */      this.last = -1;
/* 420:    */    }
/* 421:    */  }
/* 422:    */  
/* 423:    */  public ObjectIterator<K> iterator() {
/* 424:424 */    return new SetIterator(null);
/* 425:    */  }
/* 426:    */  
/* 435:    */  @Deprecated
/* 436:    */  public boolean rehash()
/* 437:    */  {
/* 438:438 */    return true;
/* 439:    */  }
/* 440:    */  
/* 451:    */  public boolean trim()
/* 452:    */  {
/* 453:453 */    int l = HashCommon.arraySize(this.size, this.f);
/* 454:454 */    if (l >= this.n) return true;
/* 455:    */    try {
/* 456:456 */      rehash(l);
/* 457:    */    } catch (OutOfMemoryError cantDoIt) {
/* 458:458 */      return false; }
/* 459:459 */    return true;
/* 460:    */  }
/* 461:    */  
/* 478:    */  public boolean trim(int n)
/* 479:    */  {
/* 480:480 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 481:481 */    if (this.n <= l) return true;
/* 482:    */    try {
/* 483:483 */      rehash(l);
/* 484:    */    } catch (OutOfMemoryError cantDoIt) {
/* 485:485 */      return false; }
/* 486:486 */    return true;
/* 487:    */  }
/* 488:    */  
/* 497:    */  protected void rehash(int newN)
/* 498:    */  {
/* 499:499 */    int i = 0;
/* 500:500 */    boolean[] used = this.used;
/* 501:    */    
/* 502:502 */    K[] key = this.key;
/* 503:503 */    int newMask = newN - 1;
/* 504:504 */    K[] newKey = (Object[])new Object[newN];
/* 505:505 */    boolean[] newUsed = new boolean[newN];
/* 506:506 */    for (int j = this.size; j-- != 0;) {
/* 507:507 */      while (used[i] == 0) i++;
/* 508:508 */      K k = key[i];
/* 509:509 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 510:510 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 511:511 */      newUsed[pos] = true;
/* 512:512 */      newKey[pos] = k;
/* 513:513 */      i++;
/* 514:    */    }
/* 515:515 */    this.n = newN;
/* 516:516 */    this.mask = newMask;
/* 517:517 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 518:518 */    this.key = newKey;
/* 519:519 */    this.used = newUsed;
/* 520:    */  }
/* 521:    */  
/* 525:    */  public ObjectOpenCustomHashSet<K> clone()
/* 526:    */  {
/* 527:    */    ObjectOpenCustomHashSet<K> c;
/* 528:    */    
/* 530:    */    try
/* 531:    */    {
/* 532:532 */      c = (ObjectOpenCustomHashSet)super.clone();
/* 533:    */    }
/* 534:    */    catch (CloneNotSupportedException cantHappen) {
/* 535:535 */      throw new InternalError();
/* 536:    */    }
/* 537:537 */    c.key = ((Object[])this.key.clone());
/* 538:538 */    c.used = ((boolean[])this.used.clone());
/* 539:539 */    c.strategy = this.strategy;
/* 540:540 */    return c;
/* 541:    */  }
/* 542:    */  
/* 550:    */  public int hashCode()
/* 551:    */  {
/* 552:552 */    int h = 0;int i = 0;int j = this.size;
/* 553:553 */    while (j-- != 0) {
/* 554:554 */      while (this.used[i] == 0) i++;
/* 555:555 */      if (this != this.key[i])
/* 556:556 */        h += this.strategy.hashCode(this.key[i]);
/* 557:557 */      i++;
/* 558:    */    }
/* 559:559 */    return h;
/* 560:    */  }
/* 561:    */  
/* 562:562 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 563:563 */    s.defaultWriteObject();
/* 564:564 */    for (int j = this.size; j-- != 0; s.writeObject(i.next())) {}
/* 565:    */  }
/* 566:    */  
/* 567:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 568:568 */    s.defaultReadObject();
/* 569:569 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 570:570 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 571:571 */    this.mask = (this.n - 1);
/* 572:572 */    K[] key = this.key = (Object[])new Object[this.n];
/* 573:573 */    boolean[] used = this.used = new boolean[this.n];
/* 574:    */    
/* 575:575 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 576:576 */      K k = s.readObject();
/* 577:577 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 578:578 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 579:579 */      used[pos] = true;
/* 580:580 */      key[pos] = k;
/* 581:    */    }
/* 582:    */  }
/* 583:    */  
/* 584:    */  private void checkTable() {}
/* 585:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */