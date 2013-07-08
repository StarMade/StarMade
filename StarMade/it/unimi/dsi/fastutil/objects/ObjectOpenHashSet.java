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
/*  76:    */public class ObjectOpenHashSet<K>
/*  77:    */  extends AbstractObjectSet<K>
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
/*  90:    */  public ObjectOpenHashSet(int expected, float f)
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
/* 104:    */  public ObjectOpenHashSet(int expected)
/* 105:    */  {
/* 106:106 */    this(expected, 0.75F);
/* 107:    */  }
/* 108:    */  
/* 110:    */  public ObjectOpenHashSet()
/* 111:    */  {
/* 112:112 */    this(16, 0.75F);
/* 113:    */  }
/* 114:    */  
/* 118:    */  public ObjectOpenHashSet(Collection<? extends K> c, float f)
/* 119:    */  {
/* 120:120 */    this(c.size(), f);
/* 121:121 */    addAll(c);
/* 122:    */  }
/* 123:    */  
/* 127:    */  public ObjectOpenHashSet(Collection<? extends K> c)
/* 128:    */  {
/* 129:129 */    this(c, 0.75F);
/* 130:    */  }
/* 131:    */  
/* 135:    */  public ObjectOpenHashSet(ObjectCollection<? extends K> c, float f)
/* 136:    */  {
/* 137:137 */    this(c.size(), f);
/* 138:138 */    addAll(c);
/* 139:    */  }
/* 140:    */  
/* 144:    */  public ObjectOpenHashSet(ObjectCollection<? extends K> c)
/* 145:    */  {
/* 146:146 */    this(c, 0.75F);
/* 147:    */  }
/* 148:    */  
/* 152:    */  public ObjectOpenHashSet(ObjectIterator<K> i, float f)
/* 153:    */  {
/* 154:154 */    this(16, f);
/* 155:155 */    while (i.hasNext()) { add(i.next());
/* 156:    */    }
/* 157:    */  }
/* 158:    */  
/* 160:    */  public ObjectOpenHashSet(ObjectIterator<K> i)
/* 161:    */  {
/* 162:162 */    this(i, 0.75F);
/* 163:    */  }
/* 164:    */  
/* 170:    */  public ObjectOpenHashSet(K[] a, int offset, int length, float f)
/* 171:    */  {
/* 172:172 */    this(length < 0 ? 0 : length, f);
/* 173:173 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/* 174:174 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 181:    */  public ObjectOpenHashSet(K[] a, int offset, int length)
/* 182:    */  {
/* 183:183 */    this(a, offset, length, 0.75F);
/* 184:    */  }
/* 185:    */  
/* 189:    */  public ObjectOpenHashSet(K[] a, float f)
/* 190:    */  {
/* 191:191 */    this(a, 0, a.length, f);
/* 192:    */  }
/* 193:    */  
/* 197:    */  public ObjectOpenHashSet(K[] a)
/* 198:    */  {
/* 199:199 */    this(a, 0.75F);
/* 200:    */  }
/* 201:    */  
/* 205:    */  public boolean add(K k)
/* 206:    */  {
/* 207:207 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 208:    */    
/* 209:209 */    while (this.used[pos] != 0) {
/* 210:210 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return false;
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
/* 231:231 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
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
/* 245:245 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 246:    */    
/* 247:247 */    while (this.used[pos] != 0) {
/* 248:248 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 261:261 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 262:    */    
/* 263:263 */    while (this.used[pos] != 0) {
/* 264:264 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 265:265 */      pos = pos + 1 & this.mask;
/* 266:    */    }
/* 267:267 */    return false;
/* 268:    */  }
/* 269:    */  
/* 273:    */  public K get(Object k)
/* 274:    */  {
/* 275:275 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 276:    */    
/* 277:277 */    while (this.used[pos] != 0) {
/* 278:278 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.key[pos];
/* 279:279 */      pos = pos + 1 & this.mask;
/* 280:    */    }
/* 281:281 */    return null;
/* 282:    */  }
/* 283:    */  
/* 288:    */  public void clear()
/* 289:    */  {
/* 290:290 */    if (this.size == 0) return;
/* 291:291 */    this.size = 0;
/* 292:292 */    BooleanArrays.fill(this.used, false);
/* 293:293 */    ObjectArrays.fill(this.key, null);
/* 294:    */  }
/* 295:    */  
/* 296:296 */  public int size() { return this.size; }
/* 297:    */  
/* 298:    */  public boolean isEmpty() {
/* 299:299 */    return this.size == 0;
/* 300:    */  }
/* 301:    */  
/* 307:    */  @Deprecated
/* 308:    */  public void growthFactor(int growthFactor) {}
/* 309:    */  
/* 315:    */  @Deprecated
/* 316:316 */  public int growthFactor() { return 16; }
/* 317:    */  
/* 318:    */  private class SetIterator extends AbstractObjectIterator<K> {
/* 319:    */    int pos;
/* 320:    */    int last;
/* 321:    */    
/* 322:322 */    private SetIterator() { this.pos = ObjectOpenHashSet.this.n;
/* 323:    */      
/* 325:325 */      this.last = -1;
/* 326:    */      
/* 327:327 */      this.c = ObjectOpenHashSet.this.size;
/* 328:    */      
/* 332:332 */      boolean[] used = ObjectOpenHashSet.this.used;
/* 333:333 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 334:    */    }
/* 335:    */    
/* 336:336 */    public boolean hasNext() { return this.c != 0; }
/* 337:    */    
/* 338:    */    public K next() {
/* 339:339 */      if (!hasNext()) throw new NoSuchElementException();
/* 340:340 */      this.c -= 1;
/* 341:    */      
/* 342:342 */      if (this.pos < 0) return this.wrapped.get(-(this.last = --this.pos) - 2);
/* 343:343 */      K retVal = ObjectOpenHashSet.this.key[(this.last = this.pos)];
/* 344:    */      
/* 345:345 */      if (this.c != 0) {
/* 346:346 */        boolean[] used = ObjectOpenHashSet.this.used;
/* 347:347 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 348:    */      }
/* 349:    */      
/* 350:350 */      return retVal;
/* 351:    */    }
/* 352:    */    
/* 354:    */    int c;
/* 355:    */    
/* 356:    */    ObjectArrayList<K> wrapped;
/* 357:    */    
/* 358:    */    final int shiftKeys(int pos)
/* 359:    */    {
/* 360:    */      int last;
/* 361:    */      for (;;)
/* 362:    */      {
/* 363:363 */        pos = (last = pos) + 1 & ObjectOpenHashSet.this.mask;
/* 364:364 */        while (ObjectOpenHashSet.this.used[pos] != 0) {
/* 365:365 */          int slot = (ObjectOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(ObjectOpenHashSet.this.key[pos].hashCode())) & ObjectOpenHashSet.this.mask;
/* 366:366 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 367:367 */          pos = pos + 1 & ObjectOpenHashSet.this.mask;
/* 368:    */        }
/* 369:369 */        if (ObjectOpenHashSet.this.used[pos] == 0) break;
/* 370:370 */        if (pos < last)
/* 371:    */        {
/* 372:372 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 373:373 */          this.wrapped.add(ObjectOpenHashSet.this.key[pos]);
/* 374:    */        }
/* 375:375 */        ObjectOpenHashSet.this.key[last] = ObjectOpenHashSet.this.key[pos];
/* 376:    */      }
/* 377:377 */      ObjectOpenHashSet.this.used[last] = false;
/* 378:378 */      ObjectOpenHashSet.this.key[last] = null;
/* 379:379 */      return last;
/* 380:    */    }
/* 381:    */    
/* 382:    */    public void remove() {
/* 383:383 */      if (this.last == -1) throw new IllegalStateException();
/* 384:384 */      if (this.pos < -1)
/* 385:    */      {
/* 386:386 */        ObjectOpenHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 387:387 */        this.last = -1;
/* 388:388 */        return;
/* 389:    */      }
/* 390:390 */      ObjectOpenHashSet.this.size -= 1;
/* 391:391 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 392:392 */        this.c += 1;
/* 393:393 */        next();
/* 394:    */      }
/* 395:395 */      this.last = -1;
/* 396:    */    }
/* 397:    */  }
/* 398:    */  
/* 399:    */  public ObjectIterator<K> iterator() {
/* 400:400 */    return new SetIterator(null);
/* 401:    */  }
/* 402:    */  
/* 411:    */  @Deprecated
/* 412:    */  public boolean rehash()
/* 413:    */  {
/* 414:414 */    return true;
/* 415:    */  }
/* 416:    */  
/* 427:    */  public boolean trim()
/* 428:    */  {
/* 429:429 */    int l = HashCommon.arraySize(this.size, this.f);
/* 430:430 */    if (l >= this.n) return true;
/* 431:    */    try {
/* 432:432 */      rehash(l);
/* 433:    */    } catch (OutOfMemoryError cantDoIt) {
/* 434:434 */      return false; }
/* 435:435 */    return true;
/* 436:    */  }
/* 437:    */  
/* 454:    */  public boolean trim(int n)
/* 455:    */  {
/* 456:456 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 457:457 */    if (this.n <= l) return true;
/* 458:    */    try {
/* 459:459 */      rehash(l);
/* 460:    */    } catch (OutOfMemoryError cantDoIt) {
/* 461:461 */      return false; }
/* 462:462 */    return true;
/* 463:    */  }
/* 464:    */  
/* 473:    */  protected void rehash(int newN)
/* 474:    */  {
/* 475:475 */    int i = 0;
/* 476:476 */    boolean[] used = this.used;
/* 477:    */    
/* 478:478 */    K[] key = this.key;
/* 479:479 */    int newMask = newN - 1;
/* 480:480 */    K[] newKey = (Object[])new Object[newN];
/* 481:481 */    boolean[] newUsed = new boolean[newN];
/* 482:482 */    for (int j = this.size; j-- != 0;) {
/* 483:483 */      while (used[i] == 0) i++;
/* 484:484 */      K k = key[i];
/* 485:485 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
/* 486:486 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 487:487 */      newUsed[pos] = true;
/* 488:488 */      newKey[pos] = k;
/* 489:489 */      i++;
/* 490:    */    }
/* 491:491 */    this.n = newN;
/* 492:492 */    this.mask = newMask;
/* 493:493 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 494:494 */    this.key = newKey;
/* 495:495 */    this.used = newUsed;
/* 496:    */  }
/* 497:    */  
/* 501:    */  public ObjectOpenHashSet<K> clone()
/* 502:    */  {
/* 503:    */    ObjectOpenHashSet<K> c;
/* 504:    */    
/* 506:    */    try
/* 507:    */    {
/* 508:508 */      c = (ObjectOpenHashSet)super.clone();
/* 509:    */    }
/* 510:    */    catch (CloneNotSupportedException cantHappen) {
/* 511:511 */      throw new InternalError();
/* 512:    */    }
/* 513:513 */    c.key = ((Object[])this.key.clone());
/* 514:514 */    c.used = ((boolean[])this.used.clone());
/* 515:515 */    return c;
/* 516:    */  }
/* 517:    */  
/* 525:    */  public int hashCode()
/* 526:    */  {
/* 527:527 */    int h = 0;int i = 0;int j = this.size;
/* 528:528 */    while (j-- != 0) {
/* 529:529 */      while (this.used[i] == 0) i++;
/* 530:530 */      if (this != this.key[i])
/* 531:531 */        h += (this.key[i] == null ? 0 : this.key[i].hashCode());
/* 532:532 */      i++;
/* 533:    */    }
/* 534:534 */    return h;
/* 535:    */  }
/* 536:    */  
/* 537:537 */  private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator<K> i = iterator();
/* 538:538 */    s.defaultWriteObject();
/* 539:539 */    for (int j = this.size; j-- != 0; s.writeObject(i.next())) {}
/* 540:    */  }
/* 541:    */  
/* 542:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 543:543 */    s.defaultReadObject();
/* 544:544 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 545:545 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 546:546 */    this.mask = (this.n - 1);
/* 547:547 */    K[] key = this.key = (Object[])new Object[this.n];
/* 548:548 */    boolean[] used = this.used = new boolean[this.n];
/* 549:    */    
/* 550:550 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 551:551 */      K k = s.readObject();
/* 552:552 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 553:553 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 554:554 */      used[pos] = true;
/* 555:555 */      key[pos] = k;
/* 556:    */    }
/* 557:    */  }
/* 558:    */  
/* 559:    */  private void checkTable() {}
/* 560:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */