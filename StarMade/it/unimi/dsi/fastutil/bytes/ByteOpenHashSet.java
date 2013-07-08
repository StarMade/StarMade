/*   1:    */package it.unimi.dsi.fastutil.bytes;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Collection;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  78:    */public class ByteOpenHashSet
/*  79:    */  extends AbstractByteSet
/*  80:    */  implements Serializable, Cloneable, Hash
/*  81:    */{
/*  82:    */  public static final long serialVersionUID = 0L;
/*  83:    */  private static final boolean ASSERTS = false;
/*  84:    */  protected transient byte[] key;
/*  85:    */  protected transient boolean[] used;
/*  86:    */  protected final float f;
/*  87:    */  protected transient int n;
/*  88:    */  protected transient int maxFill;
/*  89:    */  protected transient int mask;
/*  90:    */  protected int size;
/*  91:    */  
/*  92:    */  public ByteOpenHashSet(int expected, float f)
/*  93:    */  {
/*  94: 94 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  95: 95 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  96: 96 */    this.f = f;
/*  97: 97 */    this.n = HashCommon.arraySize(expected, f);
/*  98: 98 */    this.mask = (this.n - 1);
/*  99: 99 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 100:100 */    this.key = new byte[this.n];
/* 101:101 */    this.used = new boolean[this.n];
/* 102:    */  }
/* 103:    */  
/* 106:    */  public ByteOpenHashSet(int expected)
/* 107:    */  {
/* 108:108 */    this(expected, 0.75F);
/* 109:    */  }
/* 110:    */  
/* 112:    */  public ByteOpenHashSet()
/* 113:    */  {
/* 114:114 */    this(16, 0.75F);
/* 115:    */  }
/* 116:    */  
/* 120:    */  public ByteOpenHashSet(Collection<? extends Byte> c, float f)
/* 121:    */  {
/* 122:122 */    this(c.size(), f);
/* 123:123 */    addAll(c);
/* 124:    */  }
/* 125:    */  
/* 129:    */  public ByteOpenHashSet(Collection<? extends Byte> c)
/* 130:    */  {
/* 131:131 */    this(c, 0.75F);
/* 132:    */  }
/* 133:    */  
/* 137:    */  public ByteOpenHashSet(ByteCollection c, float f)
/* 138:    */  {
/* 139:139 */    this(c.size(), f);
/* 140:140 */    addAll(c);
/* 141:    */  }
/* 142:    */  
/* 146:    */  public ByteOpenHashSet(ByteCollection c)
/* 147:    */  {
/* 148:148 */    this(c, 0.75F);
/* 149:    */  }
/* 150:    */  
/* 154:    */  public ByteOpenHashSet(ByteIterator i, float f)
/* 155:    */  {
/* 156:156 */    this(16, f);
/* 157:157 */    while (i.hasNext()) { add(i.nextByte());
/* 158:    */    }
/* 159:    */  }
/* 160:    */  
/* 162:    */  public ByteOpenHashSet(ByteIterator i)
/* 163:    */  {
/* 164:164 */    this(i, 0.75F);
/* 165:    */  }
/* 166:    */  
/* 170:    */  public ByteOpenHashSet(Iterator<?> i, float f)
/* 171:    */  {
/* 172:172 */    this(ByteIterators.asByteIterator(i), f);
/* 173:    */  }
/* 174:    */  
/* 177:    */  public ByteOpenHashSet(Iterator<?> i)
/* 178:    */  {
/* 179:179 */    this(ByteIterators.asByteIterator(i));
/* 180:    */  }
/* 181:    */  
/* 187:    */  public ByteOpenHashSet(byte[] a, int offset, int length, float f)
/* 188:    */  {
/* 189:189 */    this(length < 0 ? 0 : length, f);
/* 190:190 */    ByteArrays.ensureOffsetLength(a, offset, length);
/* 191:191 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 192:    */    }
/* 193:    */  }
/* 194:    */  
/* 198:    */  public ByteOpenHashSet(byte[] a, int offset, int length)
/* 199:    */  {
/* 200:200 */    this(a, offset, length, 0.75F);
/* 201:    */  }
/* 202:    */  
/* 206:    */  public ByteOpenHashSet(byte[] a, float f)
/* 207:    */  {
/* 208:208 */    this(a, 0, a.length, f);
/* 209:    */  }
/* 210:    */  
/* 214:    */  public ByteOpenHashSet(byte[] a)
/* 215:    */  {
/* 216:216 */    this(a, 0.75F);
/* 217:    */  }
/* 218:    */  
/* 222:    */  public boolean add(byte k)
/* 223:    */  {
/* 224:224 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 225:    */    
/* 226:226 */    while (this.used[pos] != 0) {
/* 227:227 */      if (this.key[pos] == k) return false;
/* 228:228 */      pos = pos + 1 & this.mask;
/* 229:    */    }
/* 230:230 */    this.used[pos] = true;
/* 231:231 */    this.key[pos] = k;
/* 232:232 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 233:    */    }
/* 234:234 */    return true;
/* 235:    */  }
/* 236:    */  
/* 239:    */  protected final int shiftKeys(int pos)
/* 240:    */  {
/* 241:    */    int last;
/* 242:    */    
/* 244:    */    for (;;)
/* 245:    */    {
/* 246:246 */      pos = (last = pos) + 1 & this.mask;
/* 247:247 */      while (this.used[pos] != 0) {
/* 248:248 */        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 249:249 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 250:250 */        pos = pos + 1 & this.mask;
/* 251:    */      }
/* 252:252 */      if (this.used[pos] == 0) break;
/* 253:253 */      this.key[last] = this.key[pos];
/* 254:    */    }
/* 255:255 */    this.used[last] = false;
/* 256:256 */    return last;
/* 257:    */  }
/* 258:    */  
/* 259:    */  public boolean remove(byte k)
/* 260:    */  {
/* 261:261 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 262:    */    
/* 263:263 */    while (this.used[pos] != 0) {
/* 264:264 */      if (this.key[pos] == k) {
/* 265:265 */        this.size -= 1;
/* 266:266 */        shiftKeys(pos);
/* 267:    */        
/* 268:268 */        return true;
/* 269:    */      }
/* 270:270 */      pos = pos + 1 & this.mask;
/* 271:    */    }
/* 272:272 */    return false;
/* 273:    */  }
/* 274:    */  
/* 275:    */  public boolean contains(byte k)
/* 276:    */  {
/* 277:277 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 278:    */    
/* 279:279 */    while (this.used[pos] != 0) {
/* 280:280 */      if (this.key[pos] == k) return true;
/* 281:281 */      pos = pos + 1 & this.mask;
/* 282:    */    }
/* 283:283 */    return false;
/* 284:    */  }
/* 285:    */  
/* 290:    */  public void clear()
/* 291:    */  {
/* 292:292 */    if (this.size == 0) return;
/* 293:293 */    this.size = 0;
/* 294:294 */    BooleanArrays.fill(this.used, false);
/* 295:    */  }
/* 296:    */  
/* 297:297 */  public int size() { return this.size; }
/* 298:    */  
/* 299:    */  public boolean isEmpty() {
/* 300:300 */    return this.size == 0;
/* 301:    */  }
/* 302:    */  
/* 308:    */  @Deprecated
/* 309:    */  public void growthFactor(int growthFactor) {}
/* 310:    */  
/* 316:    */  @Deprecated
/* 317:317 */  public int growthFactor() { return 16; }
/* 318:    */  
/* 319:    */  private class SetIterator extends AbstractByteIterator {
/* 320:    */    int pos;
/* 321:    */    int last;
/* 322:    */    
/* 323:323 */    private SetIterator() { this.pos = ByteOpenHashSet.this.n;
/* 324:    */      
/* 326:326 */      this.last = -1;
/* 327:    */      
/* 328:328 */      this.c = ByteOpenHashSet.this.size;
/* 329:    */      
/* 333:333 */      boolean[] used = ByteOpenHashSet.this.used;
/* 334:334 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 335:    */    }
/* 336:    */    
/* 337:337 */    public boolean hasNext() { return this.c != 0; }
/* 338:    */    
/* 339:    */    public byte nextByte() {
/* 340:340 */      if (!hasNext()) throw new NoSuchElementException();
/* 341:341 */      this.c -= 1;
/* 342:    */      
/* 343:343 */      if (this.pos < 0) return this.wrapped.getByte(-(this.last = --this.pos) - 2);
/* 344:344 */      byte retVal = ByteOpenHashSet.this.key[(this.last = this.pos)];
/* 345:    */      
/* 346:346 */      if (this.c != 0) {
/* 347:347 */        boolean[] used = ByteOpenHashSet.this.used;
/* 348:348 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 349:    */      }
/* 350:    */      
/* 351:351 */      return retVal;
/* 352:    */    }
/* 353:    */    
/* 355:    */    int c;
/* 356:    */    
/* 357:    */    ByteArrayList wrapped;
/* 358:    */    
/* 359:    */    final int shiftKeys(int pos)
/* 360:    */    {
/* 361:    */      int last;
/* 362:    */      for (;;)
/* 363:    */      {
/* 364:364 */        pos = (last = pos) + 1 & ByteOpenHashSet.this.mask;
/* 365:365 */        while (ByteOpenHashSet.this.used[pos] != 0) {
/* 366:366 */          int slot = HashCommon.murmurHash3(ByteOpenHashSet.this.key[pos]) & ByteOpenHashSet.this.mask;
/* 367:367 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 368:368 */          pos = pos + 1 & ByteOpenHashSet.this.mask;
/* 369:    */        }
/* 370:370 */        if (ByteOpenHashSet.this.used[pos] == 0) break;
/* 371:371 */        if (pos < last)
/* 372:    */        {
/* 373:373 */          if (this.wrapped == null) this.wrapped = new ByteArrayList();
/* 374:374 */          this.wrapped.add(ByteOpenHashSet.this.key[pos]);
/* 375:    */        }
/* 376:376 */        ByteOpenHashSet.this.key[last] = ByteOpenHashSet.this.key[pos];
/* 377:    */      }
/* 378:378 */      ByteOpenHashSet.this.used[last] = false;
/* 379:379 */      return last;
/* 380:    */    }
/* 381:    */    
/* 382:    */    public void remove() {
/* 383:383 */      if (this.last == -1) throw new IllegalStateException();
/* 384:384 */      if (this.pos < -1)
/* 385:    */      {
/* 386:386 */        ByteOpenHashSet.this.remove(this.wrapped.getByte(-this.pos - 2));
/* 387:387 */        this.last = -1;
/* 388:388 */        return;
/* 389:    */      }
/* 390:390 */      ByteOpenHashSet.this.size -= 1;
/* 391:391 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 392:392 */        this.c += 1;
/* 393:393 */        nextByte();
/* 394:    */      }
/* 395:395 */      this.last = -1;
/* 396:    */    }
/* 397:    */  }
/* 398:    */  
/* 399:    */  public ByteIterator iterator() {
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
/* 478:478 */    byte[] key = this.key;
/* 479:479 */    int newMask = newN - 1;
/* 480:480 */    byte[] newKey = new byte[newN];
/* 481:481 */    boolean[] newUsed = new boolean[newN];
/* 482:482 */    for (int j = this.size; j-- != 0;) {
/* 483:483 */      while (used[i] == 0) i++;
/* 484:484 */      byte k = key[i];
/* 485:485 */      int pos = HashCommon.murmurHash3(k) & newMask;
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
/* 501:    */  public ByteOpenHashSet clone()
/* 502:    */  {
/* 503:    */    ByteOpenHashSet c;
/* 504:    */    
/* 506:    */    try
/* 507:    */    {
/* 508:508 */      c = (ByteOpenHashSet)super.clone();
/* 509:    */    }
/* 510:    */    catch (CloneNotSupportedException cantHappen) {
/* 511:511 */      throw new InternalError();
/* 512:    */    }
/* 513:513 */    c.key = ((byte[])this.key.clone());
/* 514:514 */    c.used = ((boolean[])this.used.clone());
/* 515:515 */    return c;
/* 516:    */  }
/* 517:    */  
/* 525:    */  public int hashCode()
/* 526:    */  {
/* 527:527 */    int h = 0;int i = 0;int j = this.size;
/* 528:528 */    while (j-- != 0) {
/* 529:529 */      while (this.used[i] == 0) i++;
/* 530:530 */      h += this.key[i];
/* 531:531 */      i++;
/* 532:    */    }
/* 533:533 */    return h;
/* 534:    */  }
/* 535:    */  
/* 536:536 */  private void writeObject(ObjectOutputStream s) throws IOException { ByteIterator i = iterator();
/* 537:537 */    s.defaultWriteObject();
/* 538:538 */    for (int j = this.size; j-- != 0; s.writeByte(i.nextByte())) {}
/* 539:    */  }
/* 540:    */  
/* 541:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 542:542 */    s.defaultReadObject();
/* 543:543 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 544:544 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 545:545 */    this.mask = (this.n - 1);
/* 546:546 */    byte[] key = this.key = new byte[this.n];
/* 547:547 */    boolean[] used = this.used = new boolean[this.n];
/* 548:    */    
/* 549:549 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 550:550 */      byte k = s.readByte();
/* 551:551 */      pos = HashCommon.murmurHash3(k) & this.mask;
/* 552:552 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 553:553 */      used[pos] = true;
/* 554:554 */      key[pos] = k;
/* 555:    */    }
/* 556:    */  }
/* 557:    */  
/* 558:    */  private void checkTable() {}
/* 559:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteOpenHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */