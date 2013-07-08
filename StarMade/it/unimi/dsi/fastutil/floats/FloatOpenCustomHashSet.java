/*   1:    */package it.unimi.dsi.fastutil.floats;
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
/*  81:    */public class FloatOpenCustomHashSet
/*  82:    */  extends AbstractFloatSet
/*  83:    */  implements Serializable, Cloneable, Hash
/*  84:    */{
/*  85:    */  public static final long serialVersionUID = 0L;
/*  86:    */  private static final boolean ASSERTS = false;
/*  87:    */  protected transient float[] key;
/*  88:    */  protected transient boolean[] used;
/*  89:    */  protected final float f;
/*  90:    */  protected transient int n;
/*  91:    */  protected transient int maxFill;
/*  92:    */  protected transient int mask;
/*  93:    */  protected int size;
/*  94:    */  protected FloatHash.Strategy strategy;
/*  95:    */  
/*  96:    */  public FloatOpenCustomHashSet(int expected, float f, FloatHash.Strategy strategy)
/*  97:    */  {
/*  98: 98 */    this.strategy = strategy;
/*  99: 99 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 100:100 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 101:101 */    this.f = f;
/* 102:102 */    this.n = HashCommon.arraySize(expected, f);
/* 103:103 */    this.mask = (this.n - 1);
/* 104:104 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 105:105 */    this.key = new float[this.n];
/* 106:106 */    this.used = new boolean[this.n];
/* 107:    */  }
/* 108:    */  
/* 112:    */  public FloatOpenCustomHashSet(int expected, FloatHash.Strategy strategy)
/* 113:    */  {
/* 114:114 */    this(expected, 0.75F, strategy);
/* 115:    */  }
/* 116:    */  
/* 119:    */  public FloatOpenCustomHashSet(FloatHash.Strategy strategy)
/* 120:    */  {
/* 121:121 */    this(16, 0.75F, strategy);
/* 122:    */  }
/* 123:    */  
/* 128:    */  public FloatOpenCustomHashSet(Collection<? extends Float> c, float f, FloatHash.Strategy strategy)
/* 129:    */  {
/* 130:130 */    this(c.size(), f, strategy);
/* 131:131 */    addAll(c);
/* 132:    */  }
/* 133:    */  
/* 138:    */  public FloatOpenCustomHashSet(Collection<? extends Float> c, FloatHash.Strategy strategy)
/* 139:    */  {
/* 140:140 */    this(c, 0.75F, strategy);
/* 141:    */  }
/* 142:    */  
/* 147:    */  public FloatOpenCustomHashSet(FloatCollection c, float f, FloatHash.Strategy strategy)
/* 148:    */  {
/* 149:149 */    this(c.size(), f, strategy);
/* 150:150 */    addAll(c);
/* 151:    */  }
/* 152:    */  
/* 157:    */  public FloatOpenCustomHashSet(FloatCollection c, FloatHash.Strategy strategy)
/* 158:    */  {
/* 159:159 */    this(c, 0.75F, strategy);
/* 160:    */  }
/* 161:    */  
/* 166:    */  public FloatOpenCustomHashSet(FloatIterator i, float f, FloatHash.Strategy strategy)
/* 167:    */  {
/* 168:168 */    this(16, f, strategy);
/* 169:169 */    while (i.hasNext()) { add(i.nextFloat());
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 175:    */  public FloatOpenCustomHashSet(FloatIterator i, FloatHash.Strategy strategy)
/* 176:    */  {
/* 177:177 */    this(i, 0.75F, strategy);
/* 178:    */  }
/* 179:    */  
/* 184:    */  public FloatOpenCustomHashSet(Iterator<?> i, float f, FloatHash.Strategy strategy)
/* 185:    */  {
/* 186:186 */    this(FloatIterators.asFloatIterator(i), f, strategy);
/* 187:    */  }
/* 188:    */  
/* 192:    */  public FloatOpenCustomHashSet(Iterator<?> i, FloatHash.Strategy strategy)
/* 193:    */  {
/* 194:194 */    this(FloatIterators.asFloatIterator(i), strategy);
/* 195:    */  }
/* 196:    */  
/* 203:    */  public FloatOpenCustomHashSet(float[] a, int offset, int length, float f, FloatHash.Strategy strategy)
/* 204:    */  {
/* 205:205 */    this(length < 0 ? 0 : length, f, strategy);
/* 206:206 */    FloatArrays.ensureOffsetLength(a, offset, length);
/* 207:207 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 215:    */  public FloatOpenCustomHashSet(float[] a, int offset, int length, FloatHash.Strategy strategy)
/* 216:    */  {
/* 217:217 */    this(a, offset, length, 0.75F, strategy);
/* 218:    */  }
/* 219:    */  
/* 224:    */  public FloatOpenCustomHashSet(float[] a, float f, FloatHash.Strategy strategy)
/* 225:    */  {
/* 226:226 */    this(a, 0, a.length, f, strategy);
/* 227:    */  }
/* 228:    */  
/* 233:    */  public FloatOpenCustomHashSet(float[] a, FloatHash.Strategy strategy)
/* 234:    */  {
/* 235:235 */    this(a, 0.75F, strategy);
/* 236:    */  }
/* 237:    */  
/* 240:    */  public FloatHash.Strategy strategy()
/* 241:    */  {
/* 242:242 */    return this.strategy;
/* 243:    */  }
/* 244:    */  
/* 248:    */  public boolean add(float k)
/* 249:    */  {
/* 250:250 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 251:    */    
/* 252:252 */    while (this.used[pos] != 0) {
/* 253:253 */      if (this.strategy.equals(this.key[pos], k)) return false;
/* 254:254 */      pos = pos + 1 & this.mask;
/* 255:    */    }
/* 256:256 */    this.used[pos] = true;
/* 257:257 */    this.key[pos] = k;
/* 258:258 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 259:    */    }
/* 260:260 */    return true;
/* 261:    */  }
/* 262:    */  
/* 265:    */  protected final int shiftKeys(int pos)
/* 266:    */  {
/* 267:    */    int last;
/* 268:    */    
/* 270:    */    for (;;)
/* 271:    */    {
/* 272:272 */      pos = (last = pos) + 1 & this.mask;
/* 273:273 */      while (this.used[pos] != 0) {
/* 274:274 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 275:275 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 276:276 */        pos = pos + 1 & this.mask;
/* 277:    */      }
/* 278:278 */      if (this.used[pos] == 0) break;
/* 279:279 */      this.key[last] = this.key[pos];
/* 280:    */    }
/* 281:281 */    this.used[last] = false;
/* 282:282 */    return last;
/* 283:    */  }
/* 284:    */  
/* 285:    */  public boolean remove(float k)
/* 286:    */  {
/* 287:287 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 288:    */    
/* 289:289 */    while (this.used[pos] != 0) {
/* 290:290 */      if (this.strategy.equals(this.key[pos], k)) {
/* 291:291 */        this.size -= 1;
/* 292:292 */        shiftKeys(pos);
/* 293:    */        
/* 294:294 */        return true;
/* 295:    */      }
/* 296:296 */      pos = pos + 1 & this.mask;
/* 297:    */    }
/* 298:298 */    return false;
/* 299:    */  }
/* 300:    */  
/* 301:    */  public boolean contains(float k)
/* 302:    */  {
/* 303:303 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 304:    */    
/* 305:305 */    while (this.used[pos] != 0) {
/* 306:306 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 307:307 */      pos = pos + 1 & this.mask;
/* 308:    */    }
/* 309:309 */    return false;
/* 310:    */  }
/* 311:    */  
/* 316:    */  public void clear()
/* 317:    */  {
/* 318:318 */    if (this.size == 0) return;
/* 319:319 */    this.size = 0;
/* 320:320 */    BooleanArrays.fill(this.used, false);
/* 321:    */  }
/* 322:    */  
/* 323:323 */  public int size() { return this.size; }
/* 324:    */  
/* 325:    */  public boolean isEmpty() {
/* 326:326 */    return this.size == 0;
/* 327:    */  }
/* 328:    */  
/* 334:    */  @Deprecated
/* 335:    */  public void growthFactor(int growthFactor) {}
/* 336:    */  
/* 342:    */  @Deprecated
/* 343:343 */  public int growthFactor() { return 16; }
/* 344:    */  
/* 345:    */  private class SetIterator extends AbstractFloatIterator {
/* 346:    */    int pos;
/* 347:    */    int last;
/* 348:    */    
/* 349:349 */    private SetIterator() { this.pos = FloatOpenCustomHashSet.this.n;
/* 350:    */      
/* 352:352 */      this.last = -1;
/* 353:    */      
/* 354:354 */      this.c = FloatOpenCustomHashSet.this.size;
/* 355:    */      
/* 359:359 */      boolean[] used = FloatOpenCustomHashSet.this.used;
/* 360:360 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 361:    */    }
/* 362:    */    
/* 363:363 */    public boolean hasNext() { return this.c != 0; }
/* 364:    */    
/* 365:    */    public float nextFloat() {
/* 366:366 */      if (!hasNext()) throw new NoSuchElementException();
/* 367:367 */      this.c -= 1;
/* 368:    */      
/* 369:369 */      if (this.pos < 0) return this.wrapped.getFloat(-(this.last = --this.pos) - 2);
/* 370:370 */      float retVal = FloatOpenCustomHashSet.this.key[(this.last = this.pos)];
/* 371:    */      
/* 372:372 */      if (this.c != 0) {
/* 373:373 */        boolean[] used = FloatOpenCustomHashSet.this.used;
/* 374:374 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 375:    */      }
/* 376:    */      
/* 377:377 */      return retVal;
/* 378:    */    }
/* 379:    */    
/* 381:    */    int c;
/* 382:    */    
/* 383:    */    FloatArrayList wrapped;
/* 384:    */    
/* 385:    */    final int shiftKeys(int pos)
/* 386:    */    {
/* 387:    */      int last;
/* 388:    */      for (;;)
/* 389:    */      {
/* 390:390 */        pos = (last = pos) + 1 & FloatOpenCustomHashSet.this.mask;
/* 391:391 */        while (FloatOpenCustomHashSet.this.used[pos] != 0) {
/* 392:392 */          int slot = HashCommon.murmurHash3(FloatOpenCustomHashSet.this.strategy.hashCode(FloatOpenCustomHashSet.this.key[pos])) & FloatOpenCustomHashSet.this.mask;
/* 393:393 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 394:394 */          pos = pos + 1 & FloatOpenCustomHashSet.this.mask;
/* 395:    */        }
/* 396:396 */        if (FloatOpenCustomHashSet.this.used[pos] == 0) break;
/* 397:397 */        if (pos < last)
/* 398:    */        {
/* 399:399 */          if (this.wrapped == null) this.wrapped = new FloatArrayList();
/* 400:400 */          this.wrapped.add(FloatOpenCustomHashSet.this.key[pos]);
/* 401:    */        }
/* 402:402 */        FloatOpenCustomHashSet.this.key[last] = FloatOpenCustomHashSet.this.key[pos];
/* 403:    */      }
/* 404:404 */      FloatOpenCustomHashSet.this.used[last] = false;
/* 405:405 */      return last;
/* 406:    */    }
/* 407:    */    
/* 408:    */    public void remove() {
/* 409:409 */      if (this.last == -1) throw new IllegalStateException();
/* 410:410 */      if (this.pos < -1)
/* 411:    */      {
/* 412:412 */        FloatOpenCustomHashSet.this.remove(this.wrapped.getFloat(-this.pos - 2));
/* 413:413 */        this.last = -1;
/* 414:414 */        return;
/* 415:    */      }
/* 416:416 */      FloatOpenCustomHashSet.this.size -= 1;
/* 417:417 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 418:418 */        this.c += 1;
/* 419:419 */        nextFloat();
/* 420:    */      }
/* 421:421 */      this.last = -1;
/* 422:    */    }
/* 423:    */  }
/* 424:    */  
/* 425:    */  public FloatIterator iterator() {
/* 426:426 */    return new SetIterator(null);
/* 427:    */  }
/* 428:    */  
/* 437:    */  @Deprecated
/* 438:    */  public boolean rehash()
/* 439:    */  {
/* 440:440 */    return true;
/* 441:    */  }
/* 442:    */  
/* 453:    */  public boolean trim()
/* 454:    */  {
/* 455:455 */    int l = HashCommon.arraySize(this.size, this.f);
/* 456:456 */    if (l >= this.n) return true;
/* 457:    */    try {
/* 458:458 */      rehash(l);
/* 459:    */    } catch (OutOfMemoryError cantDoIt) {
/* 460:460 */      return false; }
/* 461:461 */    return true;
/* 462:    */  }
/* 463:    */  
/* 480:    */  public boolean trim(int n)
/* 481:    */  {
/* 482:482 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 483:483 */    if (this.n <= l) return true;
/* 484:    */    try {
/* 485:485 */      rehash(l);
/* 486:    */    } catch (OutOfMemoryError cantDoIt) {
/* 487:487 */      return false; }
/* 488:488 */    return true;
/* 489:    */  }
/* 490:    */  
/* 499:    */  protected void rehash(int newN)
/* 500:    */  {
/* 501:501 */    int i = 0;
/* 502:502 */    boolean[] used = this.used;
/* 503:    */    
/* 504:504 */    float[] key = this.key;
/* 505:505 */    int newMask = newN - 1;
/* 506:506 */    float[] newKey = new float[newN];
/* 507:507 */    boolean[] newUsed = new boolean[newN];
/* 508:508 */    for (int j = this.size; j-- != 0;) {
/* 509:509 */      while (used[i] == 0) i++;
/* 510:510 */      float k = key[i];
/* 511:511 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 512:512 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 513:513 */      newUsed[pos] = true;
/* 514:514 */      newKey[pos] = k;
/* 515:515 */      i++;
/* 516:    */    }
/* 517:517 */    this.n = newN;
/* 518:518 */    this.mask = newMask;
/* 519:519 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 520:520 */    this.key = newKey;
/* 521:521 */    this.used = newUsed;
/* 522:    */  }
/* 523:    */  
/* 527:    */  public FloatOpenCustomHashSet clone()
/* 528:    */  {
/* 529:    */    FloatOpenCustomHashSet c;
/* 530:    */    
/* 532:    */    try
/* 533:    */    {
/* 534:534 */      c = (FloatOpenCustomHashSet)super.clone();
/* 535:    */    }
/* 536:    */    catch (CloneNotSupportedException cantHappen) {
/* 537:537 */      throw new InternalError();
/* 538:    */    }
/* 539:539 */    c.key = ((float[])this.key.clone());
/* 540:540 */    c.used = ((boolean[])this.used.clone());
/* 541:541 */    c.strategy = this.strategy;
/* 542:542 */    return c;
/* 543:    */  }
/* 544:    */  
/* 552:    */  public int hashCode()
/* 553:    */  {
/* 554:554 */    int h = 0;int i = 0;int j = this.size;
/* 555:555 */    while (j-- != 0) {
/* 556:556 */      while (this.used[i] == 0) i++;
/* 557:557 */      h += this.strategy.hashCode(this.key[i]);
/* 558:558 */      i++;
/* 559:    */    }
/* 560:560 */    return h;
/* 561:    */  }
/* 562:    */  
/* 563:563 */  private void writeObject(ObjectOutputStream s) throws IOException { FloatIterator i = iterator();
/* 564:564 */    s.defaultWriteObject();
/* 565:565 */    for (int j = this.size; j-- != 0; s.writeFloat(i.nextFloat())) {}
/* 566:    */  }
/* 567:    */  
/* 568:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 569:569 */    s.defaultReadObject();
/* 570:570 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 571:571 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 572:572 */    this.mask = (this.n - 1);
/* 573:573 */    float[] key = this.key = new float[this.n];
/* 574:574 */    boolean[] used = this.used = new boolean[this.n];
/* 575:    */    
/* 576:576 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 577:577 */      float k = s.readFloat();
/* 578:578 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 579:579 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 580:580 */      used[pos] = true;
/* 581:581 */      key[pos] = k;
/* 582:    */    }
/* 583:    */  }
/* 584:    */  
/* 585:    */  private void checkTable() {}
/* 586:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatOpenCustomHashSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */