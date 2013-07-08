/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*   7:    */import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*   9:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*  10:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*  11:    */import java.io.IOException;
/*  12:    */import java.io.ObjectInputStream;
/*  13:    */import java.io.ObjectOutputStream;
/*  14:    */import java.io.Serializable;
/*  15:    */import java.util.Map;
/*  16:    */import java.util.Map.Entry;
/*  17:    */import java.util.NoSuchElementException;
/*  18:    */
/*  89:    */public class Short2ObjectOpenCustomHashMap<V>
/*  90:    */  extends AbstractShort2ObjectMap<V>
/*  91:    */  implements Serializable, Cloneable, Hash
/*  92:    */{
/*  93:    */  public static final long serialVersionUID = 0L;
/*  94:    */  private static final boolean ASSERTS = false;
/*  95:    */  protected transient short[] key;
/*  96:    */  protected transient V[] value;
/*  97:    */  protected transient boolean[] used;
/*  98:    */  protected final float f;
/*  99:    */  protected transient int n;
/* 100:    */  protected transient int maxFill;
/* 101:    */  protected transient int mask;
/* 102:    */  protected int size;
/* 103:    */  protected volatile transient Short2ObjectMap.FastEntrySet<V> entries;
/* 104:    */  protected volatile transient ShortSet keys;
/* 105:    */  protected volatile transient ObjectCollection<V> values;
/* 106:    */  protected ShortHash.Strategy strategy;
/* 107:    */  
/* 108:    */  public Short2ObjectOpenCustomHashMap(int expected, float f, ShortHash.Strategy strategy)
/* 109:    */  {
/* 110:110 */    this.strategy = strategy;
/* 111:111 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 112:112 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 113:113 */    this.f = f;
/* 114:114 */    this.n = HashCommon.arraySize(expected, f);
/* 115:115 */    this.mask = (this.n - 1);
/* 116:116 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 117:117 */    this.key = new short[this.n];
/* 118:118 */    this.value = ((Object[])new Object[this.n]);
/* 119:119 */    this.used = new boolean[this.n];
/* 120:    */  }
/* 121:    */  
/* 125:    */  public Short2ObjectOpenCustomHashMap(int expected, ShortHash.Strategy strategy)
/* 126:    */  {
/* 127:127 */    this(expected, 0.75F, strategy);
/* 128:    */  }
/* 129:    */  
/* 132:    */  public Short2ObjectOpenCustomHashMap(ShortHash.Strategy strategy)
/* 133:    */  {
/* 134:134 */    this(16, 0.75F, strategy);
/* 135:    */  }
/* 136:    */  
/* 141:    */  public Short2ObjectOpenCustomHashMap(Map<? extends Short, ? extends V> m, float f, ShortHash.Strategy strategy)
/* 142:    */  {
/* 143:143 */    this(m.size(), f, strategy);
/* 144:144 */    putAll(m);
/* 145:    */  }
/* 146:    */  
/* 150:    */  public Short2ObjectOpenCustomHashMap(Map<? extends Short, ? extends V> m, ShortHash.Strategy strategy)
/* 151:    */  {
/* 152:152 */    this(m, 0.75F, strategy);
/* 153:    */  }
/* 154:    */  
/* 159:    */  public Short2ObjectOpenCustomHashMap(Short2ObjectMap<V> m, float f, ShortHash.Strategy strategy)
/* 160:    */  {
/* 161:161 */    this(m.size(), f, strategy);
/* 162:162 */    putAll(m);
/* 163:    */  }
/* 164:    */  
/* 168:    */  public Short2ObjectOpenCustomHashMap(Short2ObjectMap<V> m, ShortHash.Strategy strategy)
/* 169:    */  {
/* 170:170 */    this(m, 0.75F, strategy);
/* 171:    */  }
/* 172:    */  
/* 179:    */  public Short2ObjectOpenCustomHashMap(short[] k, V[] v, float f, ShortHash.Strategy strategy)
/* 180:    */  {
/* 181:181 */    this(k.length, f, strategy);
/* 182:182 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 183:183 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 191:    */  public Short2ObjectOpenCustomHashMap(short[] k, V[] v, ShortHash.Strategy strategy)
/* 192:    */  {
/* 193:193 */    this(k, v, 0.75F, strategy);
/* 194:    */  }
/* 195:    */  
/* 198:    */  public ShortHash.Strategy strategy()
/* 199:    */  {
/* 200:200 */    return this.strategy;
/* 201:    */  }
/* 202:    */  
/* 206:    */  public V put(short k, V v)
/* 207:    */  {
/* 208:208 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 209:    */    
/* 210:210 */    while (this.used[pos] != 0) {
/* 211:211 */      if (this.strategy.equals(this.key[pos], k)) {
/* 212:212 */        V oldValue = this.value[pos];
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
/* 226:226 */  public V put(Short ok, V ov) { V v = ov;
/* 227:227 */    short k = ok.shortValue();
/* 228:    */    
/* 229:229 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 230:    */    
/* 231:231 */    while (this.used[pos] != 0) {
/* 232:232 */      if (this.strategy.equals(this.key[pos], k)) {
/* 233:233 */        V oldValue = this.value[pos];
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
/* 244:244 */    return this.defRetValue;
/* 245:    */  }
/* 246:    */  
/* 249:    */  protected final int shiftKeys(int pos)
/* 250:    */  {
/* 251:    */    int last;
/* 252:    */    
/* 254:    */    for (;;)
/* 255:    */    {
/* 256:256 */      pos = (last = pos) + 1 & this.mask;
/* 257:257 */      while (this.used[pos] != 0) {
/* 258:258 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 259:259 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 260:260 */        pos = pos + 1 & this.mask;
/* 261:    */      }
/* 262:262 */      if (this.used[pos] == 0) break;
/* 263:263 */      this.key[last] = this.key[pos];
/* 264:264 */      this.value[last] = this.value[pos];
/* 265:    */    }
/* 266:266 */    this.used[last] = false;
/* 267:267 */    this.value[last] = null;
/* 268:268 */    return last;
/* 269:    */  }
/* 270:    */  
/* 271:    */  public V remove(short k)
/* 272:    */  {
/* 273:273 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 274:    */    
/* 275:275 */    while (this.used[pos] != 0) {
/* 276:276 */      if (this.strategy.equals(this.key[pos], k)) {
/* 277:277 */        this.size -= 1;
/* 278:278 */        V v = this.value[pos];
/* 279:279 */        shiftKeys(pos);
/* 280:280 */        return v;
/* 281:    */      }
/* 282:282 */      pos = pos + 1 & this.mask;
/* 283:    */    }
/* 284:284 */    return this.defRetValue;
/* 285:    */  }
/* 286:    */  
/* 287:    */  public V remove(Object ok) {
/* 288:288 */    short k = ((Short)ok).shortValue();
/* 289:    */    
/* 290:290 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 291:    */    
/* 292:292 */    while (this.used[pos] != 0) {
/* 293:293 */      if (this.strategy.equals(this.key[pos], k)) {
/* 294:294 */        this.size -= 1;
/* 295:295 */        V v = this.value[pos];
/* 296:296 */        shiftKeys(pos);
/* 297:297 */        return v;
/* 298:    */      }
/* 299:299 */      pos = pos + 1 & this.mask;
/* 300:    */    }
/* 301:301 */    return this.defRetValue;
/* 302:    */  }
/* 303:    */  
/* 304:304 */  public V get(Short ok) { short k = ok.shortValue();
/* 305:    */    
/* 306:306 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 307:    */    
/* 308:308 */    while (this.used[pos] != 0) {
/* 309:309 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 310:310 */      pos = pos + 1 & this.mask;
/* 311:    */    }
/* 312:312 */    return this.defRetValue;
/* 313:    */  }
/* 314:    */  
/* 315:    */  public V get(short k)
/* 316:    */  {
/* 317:317 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 318:    */    
/* 319:319 */    while (this.used[pos] != 0) {
/* 320:320 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 321:321 */      pos = pos + 1 & this.mask;
/* 322:    */    }
/* 323:323 */    return this.defRetValue;
/* 324:    */  }
/* 325:    */  
/* 326:    */  public boolean containsKey(short k)
/* 327:    */  {
/* 328:328 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 329:    */    
/* 330:330 */    while (this.used[pos] != 0) {
/* 331:331 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 332:332 */      pos = pos + 1 & this.mask;
/* 333:    */    }
/* 334:334 */    return false;
/* 335:    */  }
/* 336:    */  
/* 337:337 */  public boolean containsValue(Object v) { V[] value = this.value;
/* 338:338 */    boolean[] used = this.used;
/* 339:339 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v))) break label16;
/* 340:340 */    return false;
/* 341:    */  }
/* 342:    */  
/* 347:    */  public void clear()
/* 348:    */  {
/* 349:349 */    if (this.size == 0) return;
/* 350:350 */    this.size = 0;
/* 351:351 */    BooleanArrays.fill(this.used, false);
/* 352:    */    
/* 353:353 */    ObjectArrays.fill(this.value, null);
/* 354:    */  }
/* 355:    */  
/* 356:356 */  public int size() { return this.size; }
/* 357:    */  
/* 358:    */  public boolean isEmpty() {
/* 359:359 */    return this.size == 0;
/* 360:    */  }
/* 361:    */  
/* 366:    */  @Deprecated
/* 367:    */  public void growthFactor(int growthFactor) {}
/* 368:    */  
/* 373:    */  @Deprecated
/* 374:    */  public int growthFactor()
/* 375:    */  {
/* 376:376 */    return 16;
/* 377:    */  }
/* 378:    */  
/* 379:    */  private final class MapEntry
/* 380:    */    implements Short2ObjectMap.Entry<V>, Map.Entry<Short, V>
/* 381:    */  {
/* 382:    */    private int index;
/* 383:    */    
/* 384:    */    MapEntry(int index)
/* 385:    */    {
/* 386:386 */      this.index = index;
/* 387:    */    }
/* 388:    */    
/* 389:389 */    public Short getKey() { return Short.valueOf(Short2ObjectOpenCustomHashMap.this.key[this.index]); }
/* 390:    */    
/* 391:    */    public short getShortKey() {
/* 392:392 */      return Short2ObjectOpenCustomHashMap.this.key[this.index];
/* 393:    */    }
/* 394:    */    
/* 395:395 */    public V getValue() { return Short2ObjectOpenCustomHashMap.this.value[this.index]; }
/* 396:    */    
/* 397:    */    public V setValue(V v) {
/* 398:398 */      V oldValue = Short2ObjectOpenCustomHashMap.this.value[this.index];
/* 399:399 */      Short2ObjectOpenCustomHashMap.this.value[this.index] = v;
/* 400:400 */      return oldValue;
/* 401:    */    }
/* 402:    */    
/* 403:    */    public boolean equals(Object o) {
/* 404:404 */      if (!(o instanceof Map.Entry)) return false;
/* 405:405 */      Map.Entry<Short, V> e = (Map.Entry)o;
/* 406:406 */      return (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[this.index], ((Short)e.getKey()).shortValue())) && (Short2ObjectOpenCustomHashMap.this.value[this.index] == null ? e.getValue() == null : Short2ObjectOpenCustomHashMap.this.value[this.index].equals(e.getValue()));
/* 407:    */    }
/* 408:    */    
/* 409:409 */    public int hashCode() { return Short2ObjectOpenCustomHashMap.this.strategy.hashCode(Short2ObjectOpenCustomHashMap.this.key[this.index]) ^ (Short2ObjectOpenCustomHashMap.this.value[this.index] == null ? 0 : Short2ObjectOpenCustomHashMap.this.value[this.index].hashCode()); }
/* 410:    */    
/* 412:412 */    public String toString() { return Short2ObjectOpenCustomHashMap.this.key[this.index] + "=>" + Short2ObjectOpenCustomHashMap.this.value[this.index]; } }
/* 413:    */  
/* 414:    */  private class MapIterator { int pos;
/* 415:    */    int last;
/* 416:    */    int c;
/* 417:    */    ShortArrayList wrapped;
/* 418:    */    
/* 419:419 */    private MapIterator() { this.pos = Short2ObjectOpenCustomHashMap.this.n;
/* 420:    */      
/* 422:422 */      this.last = -1;
/* 423:    */      
/* 424:424 */      this.c = Short2ObjectOpenCustomHashMap.this.size;
/* 425:    */      
/* 429:429 */      boolean[] used = Short2ObjectOpenCustomHashMap.this.used;
/* 430:430 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 431:    */    }
/* 432:    */    
/* 433:433 */    public boolean hasNext() { return this.c != 0; }
/* 434:    */    
/* 435:    */    public int nextEntry() {
/* 436:436 */      if (!hasNext()) throw new NoSuchElementException();
/* 437:437 */      this.c -= 1;
/* 438:    */      
/* 439:439 */      if (this.pos < 0) {
/* 440:440 */        short k = this.wrapped.getShort(-(this.last = --this.pos) - 2);
/* 441:    */        
/* 442:442 */        int pos = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Short2ObjectOpenCustomHashMap.this.mask;
/* 443:    */        
/* 444:444 */        while (Short2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 445:445 */          if (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[pos], k)) return pos;
/* 446:446 */          pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask;
/* 447:    */        }
/* 448:    */      }
/* 449:449 */      this.last = this.pos;
/* 450:    */      
/* 451:451 */      if (this.c != 0) {
/* 452:452 */        boolean[] used = Short2ObjectOpenCustomHashMap.this.used;
/* 453:453 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 454:    */      }
/* 455:    */      
/* 456:456 */      return this.last;
/* 457:    */    }
/* 458:    */    
/* 462:    */    protected final int shiftKeys(int pos)
/* 463:    */    {
/* 464:    */      int last;
/* 465:    */      
/* 467:    */      for (;;)
/* 468:    */      {
/* 469:469 */        pos = (last = pos) + 1 & Short2ObjectOpenCustomHashMap.this.mask;
/* 470:470 */        while (Short2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 471:471 */          int slot = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(Short2ObjectOpenCustomHashMap.this.key[pos])) & Short2ObjectOpenCustomHashMap.this.mask;
/* 472:472 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 473:473 */          pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask;
/* 474:    */        }
/* 475:475 */        if (Short2ObjectOpenCustomHashMap.this.used[pos] == 0) break;
/* 476:476 */        if (pos < last)
/* 477:    */        {
/* 478:478 */          if (this.wrapped == null) this.wrapped = new ShortArrayList();
/* 479:479 */          this.wrapped.add(Short2ObjectOpenCustomHashMap.this.key[pos]);
/* 480:    */        }
/* 481:481 */        Short2ObjectOpenCustomHashMap.this.key[last] = Short2ObjectOpenCustomHashMap.this.key[pos];
/* 482:482 */        Short2ObjectOpenCustomHashMap.this.value[last] = Short2ObjectOpenCustomHashMap.this.value[pos];
/* 483:    */      }
/* 484:484 */      Short2ObjectOpenCustomHashMap.this.used[last] = false;
/* 485:485 */      Short2ObjectOpenCustomHashMap.this.value[last] = null;
/* 486:486 */      return last;
/* 487:    */    }
/* 488:    */    
/* 489:    */    public void remove() {
/* 490:490 */      if (this.last == -1) throw new IllegalStateException();
/* 491:491 */      if (this.pos < -1)
/* 492:    */      {
/* 493:493 */        Short2ObjectOpenCustomHashMap.this.remove(this.wrapped.getShort(-this.pos - 2));
/* 494:494 */        this.last = -1;
/* 495:495 */        return;
/* 496:    */      }
/* 497:497 */      Short2ObjectOpenCustomHashMap.this.size -= 1;
/* 498:498 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 499:499 */        this.c += 1;
/* 500:500 */        nextEntry();
/* 501:    */      }
/* 502:502 */      this.last = -1;
/* 503:    */    }
/* 504:    */    
/* 505:    */    public int skip(int n) {
/* 506:506 */      int i = n;
/* 507:507 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 508:508 */      return n - i - 1;
/* 509:    */    } }
/* 510:    */  
/* 511:511 */  private class EntryIterator extends Short2ObjectOpenCustomHashMap<V>.MapIterator implements ObjectIterator<Short2ObjectMap.Entry<V>> { private EntryIterator() { super(null); }
/* 512:    */    
/* 513:    */    private Short2ObjectOpenCustomHashMap<V>.MapEntry entry;
/* 514:514 */    public Short2ObjectMap.Entry<V> next() { return this.entry = new Short2ObjectOpenCustomHashMap.MapEntry(Short2ObjectOpenCustomHashMap.this, nextEntry()); }
/* 515:    */    
/* 516:    */    public void remove()
/* 517:    */    {
/* 518:518 */      super.remove();
/* 519:519 */      Short2ObjectOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/* 520:    */    } }
/* 521:    */  
/* 522:522 */  private class FastEntryIterator extends Short2ObjectOpenCustomHashMap<V>.MapIterator implements ObjectIterator<Short2ObjectMap.Entry<V>> { private FastEntryIterator() { super(null); }
/* 523:523 */    final AbstractShort2ObjectMap.BasicEntry<V> entry = new AbstractShort2ObjectMap.BasicEntry((short)0, null);
/* 524:    */    
/* 525:525 */    public AbstractShort2ObjectMap.BasicEntry<V> next() { int e = nextEntry();
/* 526:526 */      this.entry.key = Short2ObjectOpenCustomHashMap.this.key[e];
/* 527:527 */      this.entry.value = Short2ObjectOpenCustomHashMap.this.value[e];
/* 528:528 */      return this.entry;
/* 529:    */    } }
/* 530:    */  
/* 531:    */  private final class MapEntrySet extends AbstractObjectSet<Short2ObjectMap.Entry<V>> implements Short2ObjectMap.FastEntrySet<V> { private MapEntrySet() {}
/* 532:    */    
/* 533:533 */    public ObjectIterator<Short2ObjectMap.Entry<V>> iterator() { return new Short2ObjectOpenCustomHashMap.EntryIterator(Short2ObjectOpenCustomHashMap.this, null); }
/* 534:    */    
/* 535:    */    public ObjectIterator<Short2ObjectMap.Entry<V>> fastIterator() {
/* 536:536 */      return new Short2ObjectOpenCustomHashMap.FastEntryIterator(Short2ObjectOpenCustomHashMap.this, null);
/* 537:    */    }
/* 538:    */    
/* 539:    */    public boolean contains(Object o) {
/* 540:540 */      if (!(o instanceof Map.Entry)) return false;
/* 541:541 */      Map.Entry<Short, V> e = (Map.Entry)o;
/* 542:542 */      short k = ((Short)e.getKey()).shortValue();
/* 543:    */      
/* 544:544 */      int pos = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Short2ObjectOpenCustomHashMap.this.mask;
/* 545:    */      
/* 546:546 */      while (Short2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 547:547 */        if (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[pos], k)) return Short2ObjectOpenCustomHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Short2ObjectOpenCustomHashMap.this.value[pos].equals(e.getValue());
/* 548:548 */        pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask;
/* 549:    */      }
/* 550:550 */      return false;
/* 551:    */    }
/* 552:    */    
/* 553:    */    public boolean remove(Object o) {
/* 554:554 */      if (!(o instanceof Map.Entry)) return false;
/* 555:555 */      Map.Entry<Short, V> e = (Map.Entry)o;
/* 556:556 */      short k = ((Short)e.getKey()).shortValue();
/* 557:    */      
/* 558:558 */      int pos = HashCommon.murmurHash3(Short2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Short2ObjectOpenCustomHashMap.this.mask;
/* 559:    */      
/* 560:560 */      while (Short2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 561:561 */        if (Short2ObjectOpenCustomHashMap.this.strategy.equals(Short2ObjectOpenCustomHashMap.this.key[pos], k)) {
/* 562:562 */          Short2ObjectOpenCustomHashMap.this.remove(e.getKey());
/* 563:563 */          return true;
/* 564:    */        }
/* 565:565 */        pos = pos + 1 & Short2ObjectOpenCustomHashMap.this.mask;
/* 566:    */      }
/* 567:567 */      return false;
/* 568:    */    }
/* 569:    */    
/* 570:570 */    public int size() { return Short2ObjectOpenCustomHashMap.this.size; }
/* 571:    */    
/* 573:573 */    public void clear() { Short2ObjectOpenCustomHashMap.this.clear(); }
/* 574:    */  }
/* 575:    */  
/* 576:    */  public Short2ObjectMap.FastEntrySet<V> short2ObjectEntrySet() {
/* 577:577 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 578:578 */    return this.entries;
/* 579:    */  }
/* 580:    */  
/* 583:    */  private final class KeyIterator
/* 584:    */    extends Short2ObjectOpenCustomHashMap.MapIterator
/* 585:    */    implements ShortIterator
/* 586:    */  {
/* 587:587 */    public KeyIterator() { super(null); }
/* 588:588 */    public short nextShort() { return Short2ObjectOpenCustomHashMap.this.key[nextEntry()]; }
/* 589:589 */    public Short next() { return Short.valueOf(Short2ObjectOpenCustomHashMap.this.key[nextEntry()]); } }
/* 590:    */  
/* 591:    */  private final class KeySet extends AbstractShortSet { private KeySet() {}
/* 592:    */    
/* 593:593 */    public ShortIterator iterator() { return new Short2ObjectOpenCustomHashMap.KeyIterator(Short2ObjectOpenCustomHashMap.this); }
/* 594:    */    
/* 595:    */    public int size() {
/* 596:596 */      return Short2ObjectOpenCustomHashMap.this.size;
/* 597:    */    }
/* 598:    */    
/* 599:599 */    public boolean contains(short k) { return Short2ObjectOpenCustomHashMap.this.containsKey(k); }
/* 600:    */    
/* 601:    */    public boolean remove(short k) {
/* 602:602 */      int oldSize = Short2ObjectOpenCustomHashMap.this.size;
/* 603:603 */      Short2ObjectOpenCustomHashMap.this.remove(k);
/* 604:604 */      return Short2ObjectOpenCustomHashMap.this.size != oldSize;
/* 605:    */    }
/* 606:    */    
/* 607:607 */    public void clear() { Short2ObjectOpenCustomHashMap.this.clear(); }
/* 608:    */  }
/* 609:    */  
/* 610:    */  public ShortSet keySet() {
/* 611:611 */    if (this.keys == null) this.keys = new KeySet(null);
/* 612:612 */    return this.keys;
/* 613:    */  }
/* 614:    */  
/* 617:    */  private final class ValueIterator
/* 618:    */    extends Short2ObjectOpenCustomHashMap<V>.MapIterator
/* 619:    */    implements ObjectIterator<V>
/* 620:    */  {
/* 621:621 */    public ValueIterator() { super(null); }
/* 622:622 */    public V next() { return Short2ObjectOpenCustomHashMap.this.value[nextEntry()]; }
/* 623:    */  }
/* 624:    */  
/* 625:625 */  public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 626:    */        public ObjectIterator<V> iterator() {
/* 627:627 */          return new Short2ObjectOpenCustomHashMap.ValueIterator(Short2ObjectOpenCustomHashMap.this);
/* 628:    */        }
/* 629:    */        
/* 630:630 */        public int size() { return Short2ObjectOpenCustomHashMap.this.size; }
/* 631:    */        
/* 632:    */        public boolean contains(Object v) {
/* 633:633 */          return Short2ObjectOpenCustomHashMap.this.containsValue(v);
/* 634:    */        }
/* 635:    */        
/* 636:636 */        public void clear() { Short2ObjectOpenCustomHashMap.this.clear(); }
/* 637:    */      };
/* 638:    */    }
/* 639:639 */    return this.values;
/* 640:    */  }
/* 641:    */  
/* 650:    */  @Deprecated
/* 651:    */  public boolean rehash()
/* 652:    */  {
/* 653:653 */    return true;
/* 654:    */  }
/* 655:    */  
/* 666:    */  public boolean trim()
/* 667:    */  {
/* 668:668 */    int l = HashCommon.arraySize(this.size, this.f);
/* 669:669 */    if (l >= this.n) return true;
/* 670:    */    try {
/* 671:671 */      rehash(l);
/* 672:    */    } catch (OutOfMemoryError cantDoIt) {
/* 673:673 */      return false; }
/* 674:674 */    return true;
/* 675:    */  }
/* 676:    */  
/* 693:    */  public boolean trim(int n)
/* 694:    */  {
/* 695:695 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 696:696 */    if (this.n <= l) return true;
/* 697:    */    try {
/* 698:698 */      rehash(l);
/* 699:    */    } catch (OutOfMemoryError cantDoIt) {
/* 700:700 */      return false; }
/* 701:701 */    return true;
/* 702:    */  }
/* 703:    */  
/* 712:    */  protected void rehash(int newN)
/* 713:    */  {
/* 714:714 */    int i = 0;
/* 715:715 */    boolean[] used = this.used;
/* 716:    */    
/* 717:717 */    short[] key = this.key;
/* 718:718 */    V[] value = this.value;
/* 719:719 */    int newMask = newN - 1;
/* 720:720 */    short[] newKey = new short[newN];
/* 721:721 */    V[] newValue = (Object[])new Object[newN];
/* 722:722 */    boolean[] newUsed = new boolean[newN];
/* 723:723 */    for (int j = this.size; j-- != 0;) {
/* 724:724 */      while (used[i] == 0) i++;
/* 725:725 */      short k = key[i];
/* 726:726 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 727:727 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 728:728 */      newUsed[pos] = true;
/* 729:729 */      newKey[pos] = k;
/* 730:730 */      newValue[pos] = value[i];
/* 731:731 */      i++;
/* 732:    */    }
/* 733:733 */    this.n = newN;
/* 734:734 */    this.mask = newMask;
/* 735:735 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 736:736 */    this.key = newKey;
/* 737:737 */    this.value = newValue;
/* 738:738 */    this.used = newUsed;
/* 739:    */  }
/* 740:    */  
/* 744:    */  public Short2ObjectOpenCustomHashMap<V> clone()
/* 745:    */  {
/* 746:    */    Short2ObjectOpenCustomHashMap<V> c;
/* 747:    */    
/* 749:    */    try
/* 750:    */    {
/* 751:751 */      c = (Short2ObjectOpenCustomHashMap)super.clone();
/* 752:    */    }
/* 753:    */    catch (CloneNotSupportedException cantHappen) {
/* 754:754 */      throw new InternalError();
/* 755:    */    }
/* 756:756 */    c.keys = null;
/* 757:757 */    c.values = null;
/* 758:758 */    c.entries = null;
/* 759:759 */    c.key = ((short[])this.key.clone());
/* 760:760 */    c.value = ((Object[])this.value.clone());
/* 761:761 */    c.used = ((boolean[])this.used.clone());
/* 762:762 */    c.strategy = this.strategy;
/* 763:763 */    return c;
/* 764:    */  }
/* 765:    */  
/* 773:    */  public int hashCode()
/* 774:    */  {
/* 775:775 */    int h = 0;
/* 776:776 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 777:777 */      while (this.used[i] == 0) i++;
/* 778:778 */      t = this.strategy.hashCode(this.key[i]);
/* 779:779 */      if (this != this.value[i])
/* 780:780 */        t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 781:781 */      h += t;
/* 782:782 */      i++;
/* 783:    */    }
/* 784:784 */    return h;
/* 785:    */  }
/* 786:    */  
/* 787:787 */  private void writeObject(ObjectOutputStream s) throws IOException { short[] key = this.key;
/* 788:788 */    V[] value = this.value;
/* 789:789 */    Short2ObjectOpenCustomHashMap<V>.MapIterator i = new MapIterator(null);
/* 790:790 */    s.defaultWriteObject();
/* 791:791 */    for (int j = this.size; j-- != 0;) {
/* 792:792 */      int e = i.nextEntry();
/* 793:793 */      s.writeShort(key[e]);
/* 794:794 */      s.writeObject(value[e]);
/* 795:    */    }
/* 796:    */  }
/* 797:    */  
/* 798:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 799:799 */    s.defaultReadObject();
/* 800:800 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 801:801 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 802:802 */    this.mask = (this.n - 1);
/* 803:803 */    short[] key = this.key = new short[this.n];
/* 804:804 */    V[] value = this.value = (Object[])new Object[this.n];
/* 805:805 */    boolean[] used = this.used = new boolean[this.n];
/* 806:    */    
/* 808:808 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 809:809 */      short k = s.readShort();
/* 810:810 */      V v = s.readObject();
/* 811:811 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 812:812 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 813:813 */      used[pos] = true;
/* 814:814 */      key[pos] = k;
/* 815:815 */      value[pos] = v;
/* 816:    */    }
/* 817:    */  }
/* 818:    */  
/* 819:    */  private void checkTable() {}
/* 820:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */