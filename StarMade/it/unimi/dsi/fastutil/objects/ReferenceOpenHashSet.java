/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Collection;
/*  11:    */import java.util.NoSuchElementException;
/*  12:    */
/*  76:    */public class ReferenceOpenHashSet<K>
/*  77:    */  extends AbstractReferenceSet<K>
/*  78:    */  implements Serializable, Cloneable, Hash
/*  79:    */{
/*  80:    */  public static final long serialVersionUID = 0L;
/*  81:    */  private static final boolean ASSERTS = false;
/*  82:    */  protected transient K[] key;
/*  83:    */  protected transient boolean[] used;
/*  84:    */  protected final float f;
/*  85:    */  protected transient int n;
/*  86:    */  protected transient int maxFill;
/*  87:    */  protected transient int mask;
/*  88:    */  protected int size;
/*  89:    */  
/*  90:    */  public ReferenceOpenHashSet(int expected, float f)
/*  91:    */  {
/*  92: 92 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  93: 93 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  94: 94 */    this.f = f;
/*  95: 95 */    this.n = HashCommon.arraySize(expected, f);
/*  96: 96 */    this.mask = (this.n - 1);
/*  97: 97 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  98: 98 */    this.key = ((Object[])new Object[this.n]);
/*  99: 99 */    this.used = new boolean[this.n];
/* 100:    */  }
/* 101:    */  
/* 104:    */  public ReferenceOpenHashSet(int expected)
/* 105:    */  {
/* 106:106 */    this(expected, 0.75F);
/* 107:    */  }
/* 108:    */  
/* 110:    */  public ReferenceOpenHashSet()
/* 111:    */  {
/* 112:112 */    this(16, 0.75F);
/* 113:    */  }
/* 114:    */  
/* 118:    */  public ReferenceOpenHashSet(Collection<? extends K> c, float f)
/* 119:    */  {
/* 120:120 */    this(c.size(), f);
/* 121:121 */    addAll(c);
/* 122:    */  }
/* 123:    */  
/* 127:    */  public ReferenceOpenHashSet(Collection<? extends K> c)
/* 128:    */  {
/* 129:129 */    this(c, 0.75F);
/* 130:    */  }
/* 131:    */  
/* 135:    */  public ReferenceOpenHashSet(ReferenceCollection<? extends K> c, float f)
/* 136:    */  {
/* 137:137 */    this(c.size(), f);
/* 138:138 */    addAll(c);
/* 139:    */  }
/* 140:    */  
/* 144:    */  public ReferenceOpenHashSet(ReferenceCollection<? extends K> c)
/* 145:    */  {
/* 146:146 */    this(c, 0.75F);
/* 147:    */  }
/* 148:    */  
/* 152:    */  public ReferenceOpenHashSet(ObjectIterator<K> i, float f)
/* 153:    */  {
/* 154:154 */    this(16, f);
/* 155:155 */    while (i.hasNext()) { add(i.next());
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 160:    */  public ReferenceOpenHashSet(ObjectIterator<K> i)
/* 161:    */  {
/* 162:162 */    this(i, 0.75F);
/* 163:    */  }
/* 164:    */  
/* 170:    */  public ReferenceOpenHashSet(K[] a, int offset, int length, float f)
/* 171:    */  {
/* 172:172 */    this(length < 0 ? 0 : length, f);
/* 173:173 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 174:174 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 181:    */  public ReferenceOpenHashSet(K[] a, int offset, int length)
/* 182:    */  {
/* 183:183 */    this(a, offset, length, 0.75F);
/* 184:    */  }
/* 185:    */  
/* 189:    */  public ReferenceOpenHashSet(K[] a, float f)
/* 190:    */  {
/* 191:191 */    this(a, 0, a.length, f);
/* 192:    */  }
/* 193:    */  
/* 197:    */  public ReferenceOpenHashSet(K[] a)
/* 198:    */  {
/* 199:199 */    this(a, 0.75F);
/* 200:    */  }
/* 201:    */  
/* 205:    */  public boolean add(K k)
/* 206:    */  {
/* 207:207 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 208:    */    
/* 209:209 */    while (this.used[pos] != 0) {
/* 210:210 */      if (this.key[pos] == k) return false;
/* 211:211 */      pos = pos + 1 & this.mask;
/* 212:    */    }
/* 213:213 */    this.used[pos] = true;
/* 214:214 */    this.key[pos] = k;
/* 215:215 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 216:    */    }
/* 217:217 */    return true;
/* 218:    */  }
/* 219:    */  
/* 222:    */  protected final int shiftKeys(int pos)
/* 223:    */  {
/* 224:    */    int last;
/* 225:    */    
/* 227:    */    for (;;)
/* 228:    */    {
/* 229:229 */      pos = (last = pos) + 1 & this.mask;
/* 230:230 */      while (this.used[pos] != 0) {
/* 231:231 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 232:232 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 233:233 */        pos = pos + 1 & this.mask;
/* 234:    */      }
/* 235:235 */      if (this.used[pos] == 0) break;
/* 236:236 */      this.key[last] = this.key[pos];
/* 237:    */    }
/* 238:238 */    this.used[last] = false;
/* 239:239 */    this.key[last] = null;
/* 240:240 */    return last;
/* 241:    */  }
/* 242:    */  
/* 243:    */  public boolean remove(Object k)
/* 244:    */  {
/* 245:245 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 246:    */    
/* 247:247 */    while (this.used[pos] != 0) {
/* 248:248 */      if (this.key[pos] == k) {
/* 249:249 */        this.size -= 1;
/* 250:250 */        shiftKeys(pos);
/* 251:    */        
/* 252:252 */        return true;
/* 253:    */      }
/* 254:254 */      pos = pos + 1 & this.mask;
/* 255:    */    }
/* 256:256 */    return false;
/* 257:    */  }
/* 258:    */  
/* 259:    */  public boolean contains(Object k)
/* 260:    */  {
/* 261:261 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 262:    */    
/* 263:263 */    while (this.used[pos] != 0) {
/* 264:264 */      if (this.key[pos] == k) return true;
/* 265:265 */      pos = pos + 1 & this.mask;
/* 266:    */    }
/* 267:267 */    return false;
/* 268:    */  }
/* 269:    */  
/* 274:    */  public void clear()
/* 275:    */  {
/* 276:276 */    if (this.size == 0) return;
/* 277:277 */    this.size = 0;
/* 278:278 */    BooleanArrays.fill(this.used, false);
/* 279:279 */    ObjectArrays.fill(this.key, null);
/* 280:    */  }
/* 281:    */  
/* 282:282 */  public int size() { return this.size; }
/* 283:    */  
/* 284:    */  public boolean isEmpty() {
/* 285:285 */    return this.size == 0;
/* 286:    */  }
/* 287:    */  
/* 293:    */  @Deprecated
/* 294:    */  public void growthFactor(int growthFactor) {}
/* 295:    */  
/* 301:    */  @Deprecated
/* 302:302 */  public int growthFactor() { return 16; }
/* 303:    */  
/* 304:    */  private class SetIterator extends AbstractObjectIterator<K> {
/* 305:    */    int pos;
/* 306:    */    int last;
/* 307:    */    
/* 308:308 */    private SetIterator() { this.pos = ReferenceOpenHashSet.this.n;
/* 309:    */      
/* 311:311 */      this.last = -1;
/* 312:    */      
/* 313:313 */      this.c = ReferenceOpenHashSet.this.size;
/* 314:    */      
/* 318:318 */      boolean[] used = ReferenceOpenHashSet.this.used;
/* 319:319 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 320:    */    }
/* 321:    */    
/* 322:322 */    public boolean hasNext() { return this.c != 0; }
/* 323:    */    
/* 324:    */    public K next() {
/* 325:325 */      if (!hasNext()) throw new NoSuchElementException();
/* 326:326 */      this.c -= 1;
/* 327:    */      
/* 328:328 */      if (this.pos < 0) return this.wrapped.get(-(this.last = --this.pos) - 2);
/* 329:329 */      K retVal = ReferenceOpenHashSet.this.key[(this.last = this.pos)];
/* 330:    */      
/* 331:331 */      if (this.c != 0) {
/* 332:332 */        boolean[] used = ReferenceOpenHashSet.this.used;
/* 333:333 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 334:    */      }
/* 335:    */      
/* 336:336 */      return retVal;
/* 337:    */    }
/* 338:    */    
/* 340:    */    int c;
/* 341:    */    
/* 342:    */    ReferenceArrayList<K> wrapped;
/* 343:    */    
/* 344:    */    final int shiftKeys(int pos)
/* 345:    */    {
/* 346:    */      int last;
/* 347:    */      for (;;)
/* 348:    */      {
/* 349:349 */        pos = (last = pos) + 1 & ReferenceOpenHashSet.this.mask;
/* 350:350 */        while (ReferenceOpenHashSet.this.used[pos] != 0) {
/* 351:351 */          int slot = (ReferenceOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(ReferenceOpenHashSet.this.key[pos]))) & ReferenceOpenHashSet.this.mask;
/* 352:352 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 353:353 */          pos = pos + 1 & ReferenceOpenHashSet.this.mask;
/* 354:    */        }
/* 355:355 */        if (ReferenceOpenHashSet.this.used[pos] == 0) break;
/* 356:356 */        if (pos < last)
/* 357:    */        {
/* 358:358 */          if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 359:359 */          this.wrapped.add(ReferenceOpenHashSet.this.key[pos]);
/* 360:    */        }
/* 361:361 */        ReferenceOpenHashSet.this.key[last] = ReferenceOpenHashSet.this.key[pos];
/* 362:    */      }
/* 363:363 */      ReferenceOpenHashSet.this.used[last] = false;
/* 364:364 */      ReferenceOpenHashSet.this.key[last] = null;
/* 365:365 */      return last;
/* 366:    */    }
/* 367:    */    
/* 368:    */    public void remove() {
/* 369:369 */      if (this.last == -1) throw new IllegalStateException();
/* 370:370 */      if (this.pos < -1)
/* 371:    */      {
/* 372:372 */        ReferenceOpenHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 373:373 */        this.last = -1;
/* 374:374 */        return;
/* 375:    */      }
/* 376:376 */      ReferenceOpenHashSet.this.size -= 1;
/* 377:377 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 378:378 */        this.c += 1;
/* 379:379 */        next();
/* 380:    */      }
/* 381:381 */      this.last = -1;
/* 382:    */    }
/* 383:    */  }
/* 384:    */  
/* 385:    */  public ObjectIterator<K> iterator() {
/* 386:386 */    return new SetIterator(null);
/* 387:    */  }
/* 388:    */  
/* 397:    */  @Deprecated
/* 398:    */  public boolean rehash()
/* 399:    */  {
/* 400:400 */    return true;
/* 401:    */  }
/* 402:    */  
/* 413:    */  public boolean trim()
/* 414:    */  {
/* 415:415 */    int l = HashCommon.arraySize(this.size, this.f);
/* 416:416 */    if (l >= this.n) return true;
/* 417:    */    try {
/* 418:418 */      rehash(l);
/* 419:    */    } catch (OutOfMemoryError cantDoIt) {
/* 420:420 */      return false; }
/* 421:421 */    return true;
/* 422:    */  }
/* 423:    */  
/* 440:    */  public boolean trim(int n)
/* 441:    */  {
/* 442:442 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 443:443 */    if (this.n <= l) return true;
/* 444:    */    try {
/* 445:445 */      rehash(l);
/* 446:    */    } catch (OutOfMemoryError cantDoIt) {
/* 447:447 */      return false; }
/* 448:448 */    return true;
/* 449:    */  }
/* 450:    */  
/* 459:    */  protected void rehash(int newN)
/* 460:    */  {
/* 461:461 */    int i = 0;
/* 462:462 */    boolean[] used = this.used;
/* 463:    */    
/* 464:464 */    K[] key = this.key;
/* 465:465 */    int newMask = newN - 1;
/* 466:466 */    K[] newKey = (Object[])new Object[newN];
/* 467:467 */    boolean[] newUsed = new boolean[newN];
/* 468:468 */    for (int j = this.size; j-- != 0;) {
/* 469:469 */      while (used[i] == 0) i++;
/* 470:470 */      K k = key[i];
/* 471:471 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 472:472 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 473:473 */      newUsed[pos] = true;
/* 474:474 */      newKey[pos] = k;
/* 475:475 */      i++;
/* 476:    */    }
/* 477:477 */    this.n = newN;
/* 478:478 */    this.mask = newMask;
/* 479:479 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 480:480 */    this.key = newKey;
/* 481:481 */    this.used = newUsed;
/* 482:    */  }
/* 483:    */  
/* 487:    */  public ReferenceOpenHashSet<K> clone()
/* 488:    */  {
/* 489:    */    ReferenceOpenHashSet<K> c;
/* 490:    */    
/* 492:    */    try
/* 493:    */    {
/* 494:494 */      c = (ReferenceOpenHashSet)super.clone();
/* 495:    */    }
/* 496:    */    catch (CloneNotSupportedException cantHappen) {
/* 497:497 */      throw new InternalError();
/* 498:    */    }
/* 499:499 */    c.key = ((Object[])this.key.clone());
/* 500:500 */    c.used = ((boolean[])this.used.clone());
/* 501:501 */    return c;
/* 502:    */  }
/* 503:    */  
/* 511:    */  public int hashCode()
/* 512:    */  {
/* 513:513 */    int h = 0;int i = 0;int j = this.size;
/* 514:514 */    while (j-- != 0) {
/* 515:515 */      while (this.used[i] == 0) i++;
/* 516:516 */      if (this != this.key[i])
/* 517:517 */        h += (this.key[i] == null ? 0 : System.identityHashCode(this.key[i]));
/* 518:518 */      i++;
/* 519:    */    }
/* 520:520 */    return h;
/* 521:    */  }
/* 522:    */  
/* 523:523 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 524:524 */    s.defaultWriteObject();
/* 525:525 */    for (int j = this.size; j-- != 0; s.writeObject(i.next())) {}
/* 526:    */  }
/* 527:    */  
/* 528:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 529:529 */    s.defaultReadObject();
/* 530:530 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 531:531 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 532:532 */    this.mask = (this.n - 1);
/* 533:533 */    K[] key = this.key = (Object[])new Object[this.n];
/* 534:534 */    boolean[] used = this.used = new boolean[this.n];
/* 535:    */    
/* 536:536 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 537:537 */      K k = s.readObject();
/* 538:538 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 539:539 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 540:540 */      used[pos] = true;
/* 541:541 */      key[pos] = k;
/* 542:    */    }
/* 543:    */  }
/* 544:    */  
/* 545:    */  private void checkTable() {}
/* 546:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */