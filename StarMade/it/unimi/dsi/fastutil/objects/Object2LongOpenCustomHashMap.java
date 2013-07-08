/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   5:    */import it.unimi.dsi.fastutil.HashCommon;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   7:    */import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*   8:    */import it.unimi.dsi.fastutil.longs.LongCollection;
/*   9:    */import it.unimi.dsi.fastutil.longs.LongIterator;
/*  10:    */import java.io.IOException;
/*  11:    */import java.io.ObjectInputStream;
/*  12:    */import java.io.ObjectOutputStream;
/*  13:    */import java.io.Serializable;
/*  14:    */import java.util.Map;
/*  15:    */import java.util.Map.Entry;
/*  16:    */import java.util.NoSuchElementException;
/*  17:    */
/*  89:    */public class Object2LongOpenCustomHashMap<K>
/*  90:    */  extends AbstractObject2LongMap<K>
/*  91:    */  implements Serializable, Cloneable, Hash
/*  92:    */{
/*  93:    */  public static final long serialVersionUID = 0L;
/*  94:    */  private static final boolean ASSERTS = false;
/*  95:    */  protected transient K[] key;
/*  96:    */  protected transient long[] value;
/*  97:    */  protected transient boolean[] used;
/*  98:    */  protected final float f;
/*  99:    */  protected transient int n;
/* 100:    */  protected transient int maxFill;
/* 101:    */  protected transient int mask;
/* 102:    */  protected int size;
/* 103:    */  protected volatile transient Object2LongMap.FastEntrySet<K> entries;
/* 104:    */  protected volatile transient ObjectSet<K> keys;
/* 105:    */  protected volatile transient LongCollection values;
/* 106:    */  protected Hash.Strategy<K> strategy;
/* 107:    */  
/* 108:    */  public Object2LongOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/* 109:    */  {
/* 110:110 */    this.strategy = strategy;
/* 111:111 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 112:112 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 113:113 */    this.f = f;
/* 114:114 */    this.n = HashCommon.arraySize(expected, f);
/* 115:115 */    this.mask = (this.n - 1);
/* 116:116 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 117:117 */    this.key = ((Object[])new Object[this.n]);
/* 118:118 */    this.value = new long[this.n];
/* 119:119 */    this.used = new boolean[this.n];
/* 120:    */  }
/* 121:    */  
/* 125:    */  public Object2LongOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/* 126:    */  {
/* 127:127 */    this(expected, 0.75F, strategy);
/* 128:    */  }
/* 129:    */  
/* 132:    */  public Object2LongOpenCustomHashMap(Hash.Strategy<K> strategy)
/* 133:    */  {
/* 134:134 */    this(16, 0.75F, strategy);
/* 135:    */  }
/* 136:    */  
/* 141:    */  public Object2LongOpenCustomHashMap(Map<? extends K, ? extends Long> m, float f, Hash.Strategy<K> strategy)
/* 142:    */  {
/* 143:143 */    this(m.size(), f, strategy);
/* 144:144 */    putAll(m);
/* 145:    */  }
/* 146:    */  
/* 150:    */  public Object2LongOpenCustomHashMap(Map<? extends K, ? extends Long> m, Hash.Strategy<K> strategy)
/* 151:    */  {
/* 152:152 */    this(m, 0.75F, strategy);
/* 153:    */  }
/* 154:    */  
/* 159:    */  public Object2LongOpenCustomHashMap(Object2LongMap<K> m, float f, Hash.Strategy<K> strategy)
/* 160:    */  {
/* 161:161 */    this(m.size(), f, strategy);
/* 162:162 */    putAll(m);
/* 163:    */  }
/* 164:    */  
/* 168:    */  public Object2LongOpenCustomHashMap(Object2LongMap<K> m, Hash.Strategy<K> strategy)
/* 169:    */  {
/* 170:170 */    this(m, 0.75F, strategy);
/* 171:    */  }
/* 172:    */  
/* 179:    */  public Object2LongOpenCustomHashMap(K[] k, long[] v, float f, Hash.Strategy<K> strategy)
/* 180:    */  {
/* 181:181 */    this(k.length, f, strategy);
/* 182:182 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 183:183 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 191:    */  public Object2LongOpenCustomHashMap(K[] k, long[] v, Hash.Strategy<K> strategy)
/* 192:    */  {
/* 193:193 */    this(k, v, 0.75F, strategy);
/* 194:    */  }
/* 195:    */  
/* 198:    */  public Hash.Strategy<K> strategy()
/* 199:    */  {
/* 200:200 */    return this.strategy;
/* 201:    */  }
/* 202:    */  
/* 206:    */  public long put(K k, long v)
/* 207:    */  {
/* 208:208 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 209:    */    
/* 210:210 */    while (this.used[pos] != 0) {
/* 211:211 */      if (this.strategy.equals(this.key[pos], k)) {
/* 212:212 */        long oldValue = this.value[pos];
/* 213:213 */        this.value[pos] = v;
/* 214:214 */        return oldValue;
/* 215:    */      }
/* 216:216 */      pos = pos + 1 & this.mask;
/* 217:    */    }
/* 218:218 */    this.used[pos] = true;
/* 219:219 */    this.key[pos] = k;
/* 220:220 */    this.value[pos] = v;
/* 221:221 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 222:    */    }
/* 223:223 */    return this.defRetValue;
/* 224:    */  }
/* 225:    */  
/* 226:226 */  public Long put(K ok, Long ov) { long v = ov.longValue();
/* 227:227 */    K k = ok;
/* 228:    */    
/* 229:229 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 230:    */    
/* 231:231 */    while (this.used[pos] != 0) {
/* 232:232 */      if (this.strategy.equals(this.key[pos], k)) {
/* 233:233 */        Long oldValue = Long.valueOf(this.value[pos]);
/* 234:234 */        this.value[pos] = v;
/* 235:235 */        return oldValue;
/* 236:    */      }
/* 237:237 */      pos = pos + 1 & this.mask;
/* 238:    */    }
/* 239:239 */    this.used[pos] = true;
/* 240:240 */    this.key[pos] = k;
/* 241:241 */    this.value[pos] = v;
/* 242:242 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 243:    */    }
/* 244:244 */    return null;
/* 245:    */  }
/* 246:    */  
/* 257:    */  public long add(K k, long incr)
/* 258:    */  {
/* 259:259 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 260:    */    
/* 261:261 */    while (this.used[pos] != 0) {
/* 262:262 */      if (this.strategy.equals(this.key[pos], k)) {
/* 263:263 */        long oldValue = this.value[pos];
/* 264:264 */        this.value[pos] += incr;
/* 265:265 */        return oldValue;
/* 266:    */      }
/* 267:267 */      pos = pos + 1 & this.mask;
/* 268:    */    }
/* 269:269 */    this.used[pos] = true;
/* 270:270 */    this.key[pos] = k;
/* 271:271 */    this.value[pos] = (this.defRetValue + incr);
/* 272:272 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 273:    */    }
/* 274:274 */    return this.defRetValue;
/* 275:    */  }
/* 276:    */  
/* 279:    */  protected final int shiftKeys(int pos)
/* 280:    */  {
/* 281:    */    int last;
/* 282:    */    
/* 284:    */    for (;;)
/* 285:    */    {
/* 286:286 */      pos = (last = pos) + 1 & this.mask;
/* 287:287 */      while (this.used[pos] != 0) {
/* 288:288 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 289:289 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 290:290 */        pos = pos + 1 & this.mask;
/* 291:    */      }
/* 292:292 */      if (this.used[pos] == 0) break;
/* 293:293 */      this.key[last] = this.key[pos];
/* 294:294 */      this.value[last] = this.value[pos];
/* 295:    */    }
/* 296:296 */    this.used[last] = false;
/* 297:297 */    this.key[last] = null;
/* 298:298 */    return last;
/* 299:    */  }
/* 300:    */  
/* 301:    */  public long removeLong(Object k)
/* 302:    */  {
/* 303:303 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 304:    */    
/* 305:305 */    while (this.used[pos] != 0) {
/* 306:306 */      if (this.strategy.equals(this.key[pos], k)) {
/* 307:307 */        this.size -= 1;
/* 308:308 */        long v = this.value[pos];
/* 309:309 */        shiftKeys(pos);
/* 310:310 */        return v;
/* 311:    */      }
/* 312:312 */      pos = pos + 1 & this.mask;
/* 313:    */    }
/* 314:314 */    return this.defRetValue;
/* 315:    */  }
/* 316:    */  
/* 317:    */  public Long remove(Object ok) {
/* 318:318 */    K k = ok;
/* 319:    */    
/* 320:320 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 321:    */    
/* 322:322 */    while (this.used[pos] != 0) {
/* 323:323 */      if (this.strategy.equals(this.key[pos], k)) {
/* 324:324 */        this.size -= 1;
/* 325:325 */        long v = this.value[pos];
/* 326:326 */        shiftKeys(pos);
/* 327:327 */        return Long.valueOf(v);
/* 328:    */      }
/* 329:329 */      pos = pos + 1 & this.mask;
/* 330:    */    }
/* 331:331 */    return null;
/* 332:    */  }
/* 333:    */  
/* 334:    */  public long getLong(Object k)
/* 335:    */  {
/* 336:336 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 337:    */    
/* 338:338 */    while (this.used[pos] != 0) {
/* 339:339 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 340:340 */      pos = pos + 1 & this.mask;
/* 341:    */    }
/* 342:342 */    return this.defRetValue;
/* 343:    */  }
/* 344:    */  
/* 345:    */  public boolean containsKey(Object k)
/* 346:    */  {
/* 347:347 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 348:    */    
/* 349:349 */    while (this.used[pos] != 0) {
/* 350:350 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 351:351 */      pos = pos + 1 & this.mask;
/* 352:    */    }
/* 353:353 */    return false;
/* 354:    */  }
/* 355:    */  
/* 356:356 */  public boolean containsValue(long v) { long[] value = this.value;
/* 357:357 */    boolean[] used = this.used;
/* 358:358 */    for (int i = this.n; i-- != 0; return true) label17: if ((used[i] == 0) || (value[i] != v)) break label17;
/* 359:359 */    return false;
/* 360:    */  }
/* 361:    */  
/* 366:    */  public void clear()
/* 367:    */  {
/* 368:368 */    if (this.size == 0) return;
/* 369:369 */    this.size = 0;
/* 370:370 */    BooleanArrays.fill(this.used, false);
/* 371:    */    
/* 372:372 */    ObjectArrays.fill(this.key, null);
/* 373:    */  }
/* 374:    */  
/* 375:375 */  public int size() { return this.size; }
/* 376:    */  
/* 377:    */  public boolean isEmpty() {
/* 378:378 */    return this.size == 0;
/* 379:    */  }
/* 380:    */  
/* 385:    */  @Deprecated
/* 386:    */  public void growthFactor(int growthFactor) {}
/* 387:    */  
/* 392:    */  @Deprecated
/* 393:    */  public int growthFactor()
/* 394:    */  {
/* 395:395 */    return 16;
/* 396:    */  }
/* 397:    */  
/* 398:    */  private final class MapEntry
/* 399:    */    implements Object2LongMap.Entry<K>, Map.Entry<K, Long>
/* 400:    */  {
/* 401:    */    private int index;
/* 402:    */    
/* 403:    */    MapEntry(int index)
/* 404:    */    {
/* 405:405 */      this.index = index;
/* 406:    */    }
/* 407:    */    
/* 408:408 */    public K getKey() { return Object2LongOpenCustomHashMap.this.key[this.index]; }
/* 409:    */    
/* 410:    */    public Long getValue() {
/* 411:411 */      return Long.valueOf(Object2LongOpenCustomHashMap.this.value[this.index]);
/* 412:    */    }
/* 413:    */    
/* 414:414 */    public long getLongValue() { return Object2LongOpenCustomHashMap.this.value[this.index]; }
/* 415:    */    
/* 416:    */    public long setValue(long v) {
/* 417:417 */      long oldValue = Object2LongOpenCustomHashMap.this.value[this.index];
/* 418:418 */      Object2LongOpenCustomHashMap.this.value[this.index] = v;
/* 419:419 */      return oldValue;
/* 420:    */    }
/* 421:    */    
/* 422:422 */    public Long setValue(Long v) { return Long.valueOf(setValue(v.longValue())); }
/* 423:    */    
/* 424:    */    public boolean equals(Object o)
/* 425:    */    {
/* 426:426 */      if (!(o instanceof Map.Entry)) return false;
/* 427:427 */      Map.Entry<K, Long> e = (Map.Entry)o;
/* 428:428 */      return (Object2LongOpenCustomHashMap.this.strategy.equals(Object2LongOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2LongOpenCustomHashMap.this.value[this.index] == ((Long)e.getValue()).longValue());
/* 429:    */    }
/* 430:    */    
/* 431:431 */    public int hashCode() { return Object2LongOpenCustomHashMap.this.strategy.hashCode(Object2LongOpenCustomHashMap.this.key[this.index]) ^ HashCommon.long2int(Object2LongOpenCustomHashMap.this.value[this.index]); }
/* 432:    */    
/* 434:434 */    public String toString() { return Object2LongOpenCustomHashMap.this.key[this.index] + "=>" + Object2LongOpenCustomHashMap.this.value[this.index]; } }
/* 435:    */  
/* 436:    */  private class MapIterator { int pos;
/* 437:    */    int last;
/* 438:    */    int c;
/* 439:    */    ObjectArrayList<K> wrapped;
/* 440:    */    
/* 441:441 */    private MapIterator() { this.pos = Object2LongOpenCustomHashMap.this.n;
/* 442:    */      
/* 444:444 */      this.last = -1;
/* 445:    */      
/* 446:446 */      this.c = Object2LongOpenCustomHashMap.this.size;
/* 447:    */      
/* 451:451 */      boolean[] used = Object2LongOpenCustomHashMap.this.used;
/* 452:452 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 453:    */    }
/* 454:    */    
/* 455:455 */    public boolean hasNext() { return this.c != 0; }
/* 456:    */    
/* 457:    */    public int nextEntry() {
/* 458:458 */      if (!hasNext()) throw new NoSuchElementException();
/* 459:459 */      this.c -= 1;
/* 460:    */      
/* 461:461 */      if (this.pos < 0) {
/* 462:462 */        Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/* 463:    */        
/* 464:464 */        int pos = HashCommon.murmurHash3(Object2LongOpenCustomHashMap.this.strategy.hashCode(k)) & Object2LongOpenCustomHashMap.this.mask;
/* 465:    */        
/* 466:466 */        while (Object2LongOpenCustomHashMap.this.used[pos] != 0) {
/* 467:467 */          if (Object2LongOpenCustomHashMap.this.strategy.equals(Object2LongOpenCustomHashMap.this.key[pos], k)) return pos;
/* 468:468 */          pos = pos + 1 & Object2LongOpenCustomHashMap.this.mask;
/* 469:    */        }
/* 470:    */      }
/* 471:471 */      this.last = this.pos;
/* 472:    */      
/* 473:473 */      if (this.c != 0) {
/* 474:474 */        boolean[] used = Object2LongOpenCustomHashMap.this.used;
/* 475:475 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 476:    */      }
/* 477:    */      
/* 478:478 */      return this.last;
/* 479:    */    }
/* 480:    */    
/* 484:    */    protected final int shiftKeys(int pos)
/* 485:    */    {
/* 486:    */      int last;
/* 487:    */      
/* 489:    */      for (;;)
/* 490:    */      {
/* 491:491 */        pos = (last = pos) + 1 & Object2LongOpenCustomHashMap.this.mask;
/* 492:492 */        while (Object2LongOpenCustomHashMap.this.used[pos] != 0) {
/* 493:493 */          int slot = HashCommon.murmurHash3(Object2LongOpenCustomHashMap.this.strategy.hashCode(Object2LongOpenCustomHashMap.this.key[pos])) & Object2LongOpenCustomHashMap.this.mask;
/* 494:494 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 495:495 */          pos = pos + 1 & Object2LongOpenCustomHashMap.this.mask;
/* 496:    */        }
/* 497:497 */        if (Object2LongOpenCustomHashMap.this.used[pos] == 0) break;
/* 498:498 */        if (pos < last)
/* 499:    */        {
/* 500:500 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 501:501 */          this.wrapped.add(Object2LongOpenCustomHashMap.this.key[pos]);
/* 502:    */        }
/* 503:503 */        Object2LongOpenCustomHashMap.this.key[last] = Object2LongOpenCustomHashMap.this.key[pos];
/* 504:504 */        Object2LongOpenCustomHashMap.this.value[last] = Object2LongOpenCustomHashMap.this.value[pos];
/* 505:    */      }
/* 506:506 */      Object2LongOpenCustomHashMap.this.used[last] = false;
/* 507:507 */      Object2LongOpenCustomHashMap.this.key[last] = null;
/* 508:508 */      return last;
/* 509:    */    }
/* 510:    */    
/* 511:    */    public void remove() {
/* 512:512 */      if (this.last == -1) throw new IllegalStateException();
/* 513:513 */      if (this.pos < -1)
/* 514:    */      {
/* 515:515 */        Object2LongOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 516:516 */        this.last = -1;
/* 517:517 */        return;
/* 518:    */      }
/* 519:519 */      Object2LongOpenCustomHashMap.this.size -= 1;
/* 520:520 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 521:521 */        this.c += 1;
/* 522:522 */        nextEntry();
/* 523:    */      }
/* 524:524 */      this.last = -1;
/* 525:    */    }
/* 526:    */    
/* 527:    */    public int skip(int n) {
/* 528:528 */      int i = n;
/* 529:529 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 530:530 */      return n - i - 1;
/* 531:    */    } }
/* 532:    */  
/* 533:533 */  private class EntryIterator extends Object2LongOpenCustomHashMap<K>.MapIterator implements ObjectIterator<Object2LongMap.Entry<K>> { private EntryIterator() { super(null); }
/* 534:    */    
/* 535:    */    private Object2LongOpenCustomHashMap<K>.MapEntry entry;
/* 536:536 */    public Object2LongMap.Entry<K> next() { return this.entry = new Object2LongOpenCustomHashMap.MapEntry(Object2LongOpenCustomHashMap.this, nextEntry()); }
/* 537:    */    
/* 538:    */    public void remove()
/* 539:    */    {
/* 540:540 */      super.remove();
/* 541:541 */      Object2LongOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/* 542:    */    } }
/* 543:    */  
/* 544:544 */  private class FastEntryIterator extends Object2LongOpenCustomHashMap<K>.MapIterator implements ObjectIterator<Object2LongMap.Entry<K>> { private FastEntryIterator() { super(null); }
/* 545:545 */    final AbstractObject2LongMap.BasicEntry<K> entry = new AbstractObject2LongMap.BasicEntry(null, 0L);
/* 546:    */    
/* 547:547 */    public AbstractObject2LongMap.BasicEntry<K> next() { int e = nextEntry();
/* 548:548 */      this.entry.key = Object2LongOpenCustomHashMap.this.key[e];
/* 549:549 */      this.entry.value = Object2LongOpenCustomHashMap.this.value[e];
/* 550:550 */      return this.entry;
/* 551:    */    } }
/* 552:    */  
/* 553:    */  private final class MapEntrySet extends AbstractObjectSet<Object2LongMap.Entry<K>> implements Object2LongMap.FastEntrySet<K> { private MapEntrySet() {}
/* 554:    */    
/* 555:555 */    public ObjectIterator<Object2LongMap.Entry<K>> iterator() { return new Object2LongOpenCustomHashMap.EntryIterator(Object2LongOpenCustomHashMap.this, null); }
/* 556:    */    
/* 557:    */    public ObjectIterator<Object2LongMap.Entry<K>> fastIterator() {
/* 558:558 */      return new Object2LongOpenCustomHashMap.FastEntryIterator(Object2LongOpenCustomHashMap.this, null);
/* 559:    */    }
/* 560:    */    
/* 561:    */    public boolean contains(Object o) {
/* 562:562 */      if (!(o instanceof Map.Entry)) return false;
/* 563:563 */      Map.Entry<K, Long> e = (Map.Entry)o;
/* 564:564 */      K k = e.getKey();
/* 565:    */      
/* 566:566 */      int pos = HashCommon.murmurHash3(Object2LongOpenCustomHashMap.this.strategy.hashCode(k)) & Object2LongOpenCustomHashMap.this.mask;
/* 567:    */      
/* 568:568 */      while (Object2LongOpenCustomHashMap.this.used[pos] != 0) {
/* 569:569 */        if (Object2LongOpenCustomHashMap.this.strategy.equals(Object2LongOpenCustomHashMap.this.key[pos], k)) return Object2LongOpenCustomHashMap.this.value[pos] == ((Long)e.getValue()).longValue();
/* 570:570 */        pos = pos + 1 & Object2LongOpenCustomHashMap.this.mask;
/* 571:    */      }
/* 572:572 */      return false;
/* 573:    */    }
/* 574:    */    
/* 575:    */    public boolean remove(Object o) {
/* 576:576 */      if (!(o instanceof Map.Entry)) return false;
/* 577:577 */      Map.Entry<K, Long> e = (Map.Entry)o;
/* 578:578 */      K k = e.getKey();
/* 579:    */      
/* 580:580 */      int pos = HashCommon.murmurHash3(Object2LongOpenCustomHashMap.this.strategy.hashCode(k)) & Object2LongOpenCustomHashMap.this.mask;
/* 581:    */      
/* 582:582 */      while (Object2LongOpenCustomHashMap.this.used[pos] != 0) {
/* 583:583 */        if (Object2LongOpenCustomHashMap.this.strategy.equals(Object2LongOpenCustomHashMap.this.key[pos], k)) {
/* 584:584 */          Object2LongOpenCustomHashMap.this.remove(e.getKey());
/* 585:585 */          return true;
/* 586:    */        }
/* 587:587 */        pos = pos + 1 & Object2LongOpenCustomHashMap.this.mask;
/* 588:    */      }
/* 589:589 */      return false;
/* 590:    */    }
/* 591:    */    
/* 592:592 */    public int size() { return Object2LongOpenCustomHashMap.this.size; }
/* 593:    */    
/* 595:595 */    public void clear() { Object2LongOpenCustomHashMap.this.clear(); }
/* 596:    */  }
/* 597:    */  
/* 598:    */  public Object2LongMap.FastEntrySet<K> object2LongEntrySet() {
/* 599:599 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 600:600 */    return this.entries;
/* 601:    */  }
/* 602:    */  
/* 605:    */  private final class KeyIterator
/* 606:    */    extends Object2LongOpenCustomHashMap<K>.MapIterator
/* 607:    */    implements ObjectIterator<K>
/* 608:    */  {
/* 609:609 */    public KeyIterator() { super(null); }
/* 610:610 */    public K next() { return Object2LongOpenCustomHashMap.this.key[nextEntry()]; } }
/* 611:    */  
/* 612:    */  private final class KeySet extends AbstractObjectSet<K> { private KeySet() {}
/* 613:    */    
/* 614:614 */    public ObjectIterator<K> iterator() { return new Object2LongOpenCustomHashMap.KeyIterator(Object2LongOpenCustomHashMap.this); }
/* 615:    */    
/* 616:    */    public int size() {
/* 617:617 */      return Object2LongOpenCustomHashMap.this.size;
/* 618:    */    }
/* 619:    */    
/* 620:620 */    public boolean contains(Object k) { return Object2LongOpenCustomHashMap.this.containsKey(k); }
/* 621:    */    
/* 622:    */    public boolean remove(Object k) {
/* 623:623 */      int oldSize = Object2LongOpenCustomHashMap.this.size;
/* 624:624 */      Object2LongOpenCustomHashMap.this.remove(k);
/* 625:625 */      return Object2LongOpenCustomHashMap.this.size != oldSize;
/* 626:    */    }
/* 627:    */    
/* 628:628 */    public void clear() { Object2LongOpenCustomHashMap.this.clear(); }
/* 629:    */  }
/* 630:    */  
/* 631:    */  public ObjectSet<K> keySet() {
/* 632:632 */    if (this.keys == null) this.keys = new KeySet(null);
/* 633:633 */    return this.keys;
/* 634:    */  }
/* 635:    */  
/* 638:    */  private final class ValueIterator
/* 639:    */    extends Object2LongOpenCustomHashMap.MapIterator
/* 640:    */    implements LongIterator
/* 641:    */  {
/* 642:642 */    public ValueIterator() { super(null); }
/* 643:643 */    public long nextLong() { return Object2LongOpenCustomHashMap.this.value[nextEntry()]; }
/* 644:644 */    public Long next() { return Long.valueOf(Object2LongOpenCustomHashMap.this.value[nextEntry()]); }
/* 645:    */  }
/* 646:    */  
/* 647:647 */  public LongCollection values() { if (this.values == null) { this.values = new AbstractLongCollection() {
/* 648:    */        public LongIterator iterator() {
/* 649:649 */          return new Object2LongOpenCustomHashMap.ValueIterator(Object2LongOpenCustomHashMap.this);
/* 650:    */        }
/* 651:    */        
/* 652:652 */        public int size() { return Object2LongOpenCustomHashMap.this.size; }
/* 653:    */        
/* 654:    */        public boolean contains(long v) {
/* 655:655 */          return Object2LongOpenCustomHashMap.this.containsValue(v);
/* 656:    */        }
/* 657:    */        
/* 658:658 */        public void clear() { Object2LongOpenCustomHashMap.this.clear(); }
/* 659:    */      };
/* 660:    */    }
/* 661:661 */    return this.values;
/* 662:    */  }
/* 663:    */  
/* 672:    */  @Deprecated
/* 673:    */  public boolean rehash()
/* 674:    */  {
/* 675:675 */    return true;
/* 676:    */  }
/* 677:    */  
/* 688:    */  public boolean trim()
/* 689:    */  {
/* 690:690 */    int l = HashCommon.arraySize(this.size, this.f);
/* 691:691 */    if (l >= this.n) return true;
/* 692:    */    try {
/* 693:693 */      rehash(l);
/* 694:    */    } catch (OutOfMemoryError cantDoIt) {
/* 695:695 */      return false; }
/* 696:696 */    return true;
/* 697:    */  }
/* 698:    */  
/* 715:    */  public boolean trim(int n)
/* 716:    */  {
/* 717:717 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 718:718 */    if (this.n <= l) return true;
/* 719:    */    try {
/* 720:720 */      rehash(l);
/* 721:    */    } catch (OutOfMemoryError cantDoIt) {
/* 722:722 */      return false; }
/* 723:723 */    return true;
/* 724:    */  }
/* 725:    */  
/* 734:    */  protected void rehash(int newN)
/* 735:    */  {
/* 736:736 */    int i = 0;
/* 737:737 */    boolean[] used = this.used;
/* 738:    */    
/* 739:739 */    K[] key = this.key;
/* 740:740 */    long[] value = this.value;
/* 741:741 */    int newMask = newN - 1;
/* 742:742 */    K[] newKey = (Object[])new Object[newN];
/* 743:743 */    long[] newValue = new long[newN];
/* 744:744 */    boolean[] newUsed = new boolean[newN];
/* 745:745 */    for (int j = this.size; j-- != 0;) {
/* 746:746 */      while (used[i] == 0) i++;
/* 747:747 */      K k = key[i];
/* 748:748 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 749:749 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 750:750 */      newUsed[pos] = true;
/* 751:751 */      newKey[pos] = k;
/* 752:752 */      newValue[pos] = value[i];
/* 753:753 */      i++;
/* 754:    */    }
/* 755:755 */    this.n = newN;
/* 756:756 */    this.mask = newMask;
/* 757:757 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 758:758 */    this.key = newKey;
/* 759:759 */    this.value = newValue;
/* 760:760 */    this.used = newUsed;
/* 761:    */  }
/* 762:    */  
/* 766:    */  public Object2LongOpenCustomHashMap<K> clone()
/* 767:    */  {
/* 768:    */    Object2LongOpenCustomHashMap<K> c;
/* 769:    */    
/* 771:    */    try
/* 772:    */    {
/* 773:773 */      c = (Object2LongOpenCustomHashMap)super.clone();
/* 774:    */    }
/* 775:    */    catch (CloneNotSupportedException cantHappen) {
/* 776:776 */      throw new InternalError();
/* 777:    */    }
/* 778:778 */    c.keys = null;
/* 779:779 */    c.values = null;
/* 780:780 */    c.entries = null;
/* 781:781 */    c.key = ((Object[])this.key.clone());
/* 782:782 */    c.value = ((long[])this.value.clone());
/* 783:783 */    c.used = ((boolean[])this.used.clone());
/* 784:784 */    c.strategy = this.strategy;
/* 785:785 */    return c;
/* 786:    */  }
/* 787:    */  
/* 795:    */  public int hashCode()
/* 796:    */  {
/* 797:797 */    int h = 0;
/* 798:798 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 799:799 */      while (this.used[i] == 0) i++;
/* 800:800 */      if (this != this.key[i])
/* 801:801 */        t = this.strategy.hashCode(this.key[i]);
/* 802:802 */      t ^= HashCommon.long2int(this.value[i]);
/* 803:803 */      h += t;
/* 804:804 */      i++;
/* 805:    */    }
/* 806:806 */    return h;
/* 807:    */  }
/* 808:    */  
/* 809:809 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 810:810 */    long[] value = this.value;
/* 811:811 */    Object2LongOpenCustomHashMap<K>.MapIterator i = new MapIterator(null);
/* 812:812 */    s.defaultWriteObject();
/* 813:813 */    for (int j = this.size; j-- != 0;) {
/* 814:814 */      int e = i.nextEntry();
/* 815:815 */      s.writeObject(key[e]);
/* 816:816 */      s.writeLong(value[e]);
/* 817:    */    }
/* 818:    */  }
/* 819:    */  
/* 820:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 821:821 */    s.defaultReadObject();
/* 822:822 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 823:823 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 824:824 */    this.mask = (this.n - 1);
/* 825:825 */    K[] key = this.key = (Object[])new Object[this.n];
/* 826:826 */    long[] value = this.value = new long[this.n];
/* 827:827 */    boolean[] used = this.used = new boolean[this.n];
/* 828:    */    
/* 830:830 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 831:831 */      K k = s.readObject();
/* 832:832 */      long v = s.readLong();
/* 833:833 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 834:834 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 835:835 */      used[pos] = true;
/* 836:836 */      key[pos] = k;
/* 837:837 */      value[pos] = v;
/* 838:    */    }
/* 839:    */  }
/* 840:    */  
/* 841:    */  private void checkTable() {}
/* 842:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */