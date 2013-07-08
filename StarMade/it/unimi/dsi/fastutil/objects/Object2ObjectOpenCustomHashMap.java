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
/*  11:    */import java.util.Map;
/*  12:    */import java.util.Map.Entry;
/*  13:    */import java.util.NoSuchElementException;
/*  14:    */
/*  87:    */public class Object2ObjectOpenCustomHashMap<K, V>
/*  88:    */  extends AbstractObject2ObjectMap<K, V>
/*  89:    */  implements Serializable, Cloneable, Hash
/*  90:    */{
/*  91:    */  public static final long serialVersionUID = 0L;
/*  92:    */  private static final boolean ASSERTS = false;
/*  93:    */  protected transient K[] key;
/*  94:    */  protected transient V[] value;
/*  95:    */  protected transient boolean[] used;
/*  96:    */  protected final float f;
/*  97:    */  protected transient int n;
/*  98:    */  protected transient int maxFill;
/*  99:    */  protected transient int mask;
/* 100:    */  protected int size;
/* 101:    */  protected volatile transient Object2ObjectMap.FastEntrySet<K, V> entries;
/* 102:    */  protected volatile transient ObjectSet<K> keys;
/* 103:    */  protected volatile transient ObjectCollection<V> values;
/* 104:    */  protected Hash.Strategy<K> strategy;
/* 105:    */  
/* 106:    */  public Object2ObjectOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/* 107:    */  {
/* 108:108 */    this.strategy = strategy;
/* 109:109 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 110:110 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 111:111 */    this.f = f;
/* 112:112 */    this.n = HashCommon.arraySize(expected, f);
/* 113:113 */    this.mask = (this.n - 1);
/* 114:114 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 115:115 */    this.key = ((Object[])new Object[this.n]);
/* 116:116 */    this.value = ((Object[])new Object[this.n]);
/* 117:117 */    this.used = new boolean[this.n];
/* 118:    */  }
/* 119:    */  
/* 123:    */  public Object2ObjectOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/* 124:    */  {
/* 125:125 */    this(expected, 0.75F, strategy);
/* 126:    */  }
/* 127:    */  
/* 130:    */  public Object2ObjectOpenCustomHashMap(Hash.Strategy<K> strategy)
/* 131:    */  {
/* 132:132 */    this(16, 0.75F, strategy);
/* 133:    */  }
/* 134:    */  
/* 139:    */  public Object2ObjectOpenCustomHashMap(Map<? extends K, ? extends V> m, float f, Hash.Strategy<K> strategy)
/* 140:    */  {
/* 141:141 */    this(m.size(), f, strategy);
/* 142:142 */    putAll(m);
/* 143:    */  }
/* 144:    */  
/* 148:    */  public Object2ObjectOpenCustomHashMap(Map<? extends K, ? extends V> m, Hash.Strategy<K> strategy)
/* 149:    */  {
/* 150:150 */    this(m, 0.75F, strategy);
/* 151:    */  }
/* 152:    */  
/* 157:    */  public Object2ObjectOpenCustomHashMap(Object2ObjectMap<K, V> m, float f, Hash.Strategy<K> strategy)
/* 158:    */  {
/* 159:159 */    this(m.size(), f, strategy);
/* 160:160 */    putAll(m);
/* 161:    */  }
/* 162:    */  
/* 166:    */  public Object2ObjectOpenCustomHashMap(Object2ObjectMap<K, V> m, Hash.Strategy<K> strategy)
/* 167:    */  {
/* 168:168 */    this(m, 0.75F, strategy);
/* 169:    */  }
/* 170:    */  
/* 177:    */  public Object2ObjectOpenCustomHashMap(K[] k, V[] v, float f, Hash.Strategy<K> strategy)
/* 178:    */  {
/* 179:179 */    this(k.length, f, strategy);
/* 180:180 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 181:181 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 189:    */  public Object2ObjectOpenCustomHashMap(K[] k, V[] v, Hash.Strategy<K> strategy)
/* 190:    */  {
/* 191:191 */    this(k, v, 0.75F, strategy);
/* 192:    */  }
/* 193:    */  
/* 196:    */  public Hash.Strategy<K> strategy()
/* 197:    */  {
/* 198:198 */    return this.strategy;
/* 199:    */  }
/* 200:    */  
/* 204:    */  public V put(K k, V v)
/* 205:    */  {
/* 206:206 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 207:    */    
/* 208:208 */    while (this.used[pos] != 0) {
/* 209:209 */      if (this.strategy.equals(this.key[pos], k)) {
/* 210:210 */        V oldValue = this.value[pos];
/* 211:211 */        this.value[pos] = v;
/* 212:212 */        return oldValue;
/* 213:    */      }
/* 214:214 */      pos = pos + 1 & this.mask;
/* 215:    */    }
/* 216:216 */    this.used[pos] = true;
/* 217:217 */    this.key[pos] = k;
/* 218:218 */    this.value[pos] = v;
/* 219:219 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 220:    */    }
/* 221:221 */    return this.defRetValue;
/* 222:    */  }
/* 223:    */  
/* 226:    */  protected final int shiftKeys(int pos)
/* 227:    */  {
/* 228:    */    int last;
/* 229:    */    
/* 231:    */    for (;;)
/* 232:    */    {
/* 233:233 */      pos = (last = pos) + 1 & this.mask;
/* 234:234 */      while (this.used[pos] != 0) {
/* 235:235 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 236:236 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 237:237 */        pos = pos + 1 & this.mask;
/* 238:    */      }
/* 239:239 */      if (this.used[pos] == 0) break;
/* 240:240 */      this.key[last] = this.key[pos];
/* 241:241 */      this.value[last] = this.value[pos];
/* 242:    */    }
/* 243:243 */    this.used[last] = false;
/* 244:244 */    this.key[last] = null;
/* 245:245 */    this.value[last] = null;
/* 246:246 */    return last;
/* 247:    */  }
/* 248:    */  
/* 249:    */  public V remove(Object k)
/* 250:    */  {
/* 251:251 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 252:    */    
/* 253:253 */    while (this.used[pos] != 0) {
/* 254:254 */      if (this.strategy.equals(this.key[pos], k)) {
/* 255:255 */        this.size -= 1;
/* 256:256 */        V v = this.value[pos];
/* 257:257 */        shiftKeys(pos);
/* 258:258 */        return v;
/* 259:    */      }
/* 260:260 */      pos = pos + 1 & this.mask;
/* 261:    */    }
/* 262:262 */    return this.defRetValue;
/* 263:    */  }
/* 264:    */  
/* 265:    */  public V get(Object k)
/* 266:    */  {
/* 267:267 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 268:    */    
/* 269:269 */    while (this.used[pos] != 0) {
/* 270:270 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 271:271 */      pos = pos + 1 & this.mask;
/* 272:    */    }
/* 273:273 */    return this.defRetValue;
/* 274:    */  }
/* 275:    */  
/* 276:    */  public boolean containsKey(Object k)
/* 277:    */  {
/* 278:278 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 279:    */    
/* 280:280 */    while (this.used[pos] != 0) {
/* 281:281 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 282:282 */      pos = pos + 1 & this.mask;
/* 283:    */    }
/* 284:284 */    return false;
/* 285:    */  }
/* 286:    */  
/* 287:287 */  public boolean containsValue(Object v) { V[] value = this.value;
/* 288:288 */    boolean[] used = this.used;
/* 289:289 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v))) break label16;
/* 290:290 */    return false;
/* 291:    */  }
/* 292:    */  
/* 297:    */  public void clear()
/* 298:    */  {
/* 299:299 */    if (this.size == 0) return;
/* 300:300 */    this.size = 0;
/* 301:301 */    BooleanArrays.fill(this.used, false);
/* 302:    */    
/* 303:303 */    ObjectArrays.fill(this.key, null);
/* 304:304 */    ObjectArrays.fill(this.value, null);
/* 305:    */  }
/* 306:    */  
/* 307:307 */  public int size() { return this.size; }
/* 308:    */  
/* 309:    */  public boolean isEmpty() {
/* 310:310 */    return this.size == 0;
/* 311:    */  }
/* 312:    */  
/* 317:    */  @Deprecated
/* 318:    */  public void growthFactor(int growthFactor) {}
/* 319:    */  
/* 324:    */  @Deprecated
/* 325:    */  public int growthFactor()
/* 326:    */  {
/* 327:327 */    return 16;
/* 328:    */  }
/* 329:    */  
/* 330:    */  private final class MapEntry
/* 331:    */    implements Object2ObjectMap.Entry<K, V>, Map.Entry<K, V>
/* 332:    */  {
/* 333:    */    private int index;
/* 334:    */    
/* 335:    */    MapEntry(int index)
/* 336:    */    {
/* 337:337 */      this.index = index;
/* 338:    */    }
/* 339:    */    
/* 340:340 */    public K getKey() { return Object2ObjectOpenCustomHashMap.this.key[this.index]; }
/* 341:    */    
/* 343:343 */    public V getValue() { return Object2ObjectOpenCustomHashMap.this.value[this.index]; }
/* 344:    */    
/* 345:    */    public V setValue(V v) {
/* 346:346 */      V oldValue = Object2ObjectOpenCustomHashMap.this.value[this.index];
/* 347:347 */      Object2ObjectOpenCustomHashMap.this.value[this.index] = v;
/* 348:348 */      return oldValue;
/* 349:    */    }
/* 350:    */    
/* 351:    */    public boolean equals(Object o) {
/* 352:352 */      if (!(o instanceof Map.Entry)) return false;
/* 353:353 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 354:354 */      return (Object2ObjectOpenCustomHashMap.this.strategy.equals(Object2ObjectOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2ObjectOpenCustomHashMap.this.value[this.index] == null ? e.getValue() == null : Object2ObjectOpenCustomHashMap.this.value[this.index].equals(e.getValue()));
/* 355:    */    }
/* 356:    */    
/* 357:357 */    public int hashCode() { return Object2ObjectOpenCustomHashMap.this.strategy.hashCode(Object2ObjectOpenCustomHashMap.this.key[this.index]) ^ (Object2ObjectOpenCustomHashMap.this.value[this.index] == null ? 0 : Object2ObjectOpenCustomHashMap.this.value[this.index].hashCode()); }
/* 358:    */    
/* 360:360 */    public String toString() { return Object2ObjectOpenCustomHashMap.this.key[this.index] + "=>" + Object2ObjectOpenCustomHashMap.this.value[this.index]; } }
/* 361:    */  
/* 362:    */  private class MapIterator { int pos;
/* 363:    */    int last;
/* 364:    */    int c;
/* 365:    */    ObjectArrayList<K> wrapped;
/* 366:    */    
/* 367:367 */    private MapIterator() { this.pos = Object2ObjectOpenCustomHashMap.this.n;
/* 368:    */      
/* 370:370 */      this.last = -1;
/* 371:    */      
/* 372:372 */      this.c = Object2ObjectOpenCustomHashMap.this.size;
/* 373:    */      
/* 377:377 */      boolean[] used = Object2ObjectOpenCustomHashMap.this.used;
/* 378:378 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 379:    */    }
/* 380:    */    
/* 381:381 */    public boolean hasNext() { return this.c != 0; }
/* 382:    */    
/* 383:    */    public int nextEntry() {
/* 384:384 */      if (!hasNext()) throw new NoSuchElementException();
/* 385:385 */      this.c -= 1;
/* 386:    */      
/* 387:387 */      if (this.pos < 0) {
/* 388:388 */        Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/* 389:    */        
/* 390:390 */        int pos = HashCommon.murmurHash3(Object2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectOpenCustomHashMap.this.mask;
/* 391:    */        
/* 392:392 */        while (Object2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 393:393 */          if (Object2ObjectOpenCustomHashMap.this.strategy.equals(Object2ObjectOpenCustomHashMap.this.key[pos], k)) return pos;
/* 394:394 */          pos = pos + 1 & Object2ObjectOpenCustomHashMap.this.mask;
/* 395:    */        }
/* 396:    */      }
/* 397:397 */      this.last = this.pos;
/* 398:    */      
/* 399:399 */      if (this.c != 0) {
/* 400:400 */        boolean[] used = Object2ObjectOpenCustomHashMap.this.used;
/* 401:401 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 402:    */      }
/* 403:    */      
/* 404:404 */      return this.last;
/* 405:    */    }
/* 406:    */    
/* 410:    */    protected final int shiftKeys(int pos)
/* 411:    */    {
/* 412:    */      int last;
/* 413:    */      
/* 415:    */      for (;;)
/* 416:    */      {
/* 417:417 */        pos = (last = pos) + 1 & Object2ObjectOpenCustomHashMap.this.mask;
/* 418:418 */        while (Object2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 419:419 */          int slot = HashCommon.murmurHash3(Object2ObjectOpenCustomHashMap.this.strategy.hashCode(Object2ObjectOpenCustomHashMap.this.key[pos])) & Object2ObjectOpenCustomHashMap.this.mask;
/* 420:420 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 421:421 */          pos = pos + 1 & Object2ObjectOpenCustomHashMap.this.mask;
/* 422:    */        }
/* 423:423 */        if (Object2ObjectOpenCustomHashMap.this.used[pos] == 0) break;
/* 424:424 */        if (pos < last)
/* 425:    */        {
/* 426:426 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 427:427 */          this.wrapped.add(Object2ObjectOpenCustomHashMap.this.key[pos]);
/* 428:    */        }
/* 429:429 */        Object2ObjectOpenCustomHashMap.this.key[last] = Object2ObjectOpenCustomHashMap.this.key[pos];
/* 430:430 */        Object2ObjectOpenCustomHashMap.this.value[last] = Object2ObjectOpenCustomHashMap.this.value[pos];
/* 431:    */      }
/* 432:432 */      Object2ObjectOpenCustomHashMap.this.used[last] = false;
/* 433:433 */      Object2ObjectOpenCustomHashMap.this.key[last] = null;
/* 434:434 */      Object2ObjectOpenCustomHashMap.this.value[last] = null;
/* 435:435 */      return last;
/* 436:    */    }
/* 437:    */    
/* 438:    */    public void remove() {
/* 439:439 */      if (this.last == -1) throw new IllegalStateException();
/* 440:440 */      if (this.pos < -1)
/* 441:    */      {
/* 442:442 */        Object2ObjectOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 443:443 */        this.last = -1;
/* 444:444 */        return;
/* 445:    */      }
/* 446:446 */      Object2ObjectOpenCustomHashMap.this.size -= 1;
/* 447:447 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 448:448 */        this.c += 1;
/* 449:449 */        nextEntry();
/* 450:    */      }
/* 451:451 */      this.last = -1;
/* 452:    */    }
/* 453:    */    
/* 454:    */    public int skip(int n) {
/* 455:455 */      int i = n;
/* 456:456 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 457:457 */      return n - i - 1;
/* 458:    */    } }
/* 459:    */  
/* 460:460 */  private class EntryIterator extends Object2ObjectOpenCustomHashMap<K, V>.MapIterator implements ObjectIterator<Object2ObjectMap.Entry<K, V>> { private EntryIterator() { super(null); }
/* 461:    */    
/* 462:    */    private Object2ObjectOpenCustomHashMap<K, V>.MapEntry entry;
/* 463:463 */    public Object2ObjectMap.Entry<K, V> next() { return this.entry = new Object2ObjectOpenCustomHashMap.MapEntry(Object2ObjectOpenCustomHashMap.this, nextEntry()); }
/* 464:    */    
/* 465:    */    public void remove()
/* 466:    */    {
/* 467:467 */      super.remove();
/* 468:468 */      Object2ObjectOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/* 469:    */    } }
/* 470:    */  
/* 471:471 */  private class FastEntryIterator extends Object2ObjectOpenCustomHashMap<K, V>.MapIterator implements ObjectIterator<Object2ObjectMap.Entry<K, V>> { private FastEntryIterator() { super(null); }
/* 472:472 */    final AbstractObject2ObjectMap.BasicEntry<K, V> entry = new AbstractObject2ObjectMap.BasicEntry(null, null);
/* 473:    */    
/* 474:474 */    public AbstractObject2ObjectMap.BasicEntry<K, V> next() { int e = nextEntry();
/* 475:475 */      this.entry.key = Object2ObjectOpenCustomHashMap.this.key[e];
/* 476:476 */      this.entry.value = Object2ObjectOpenCustomHashMap.this.value[e];
/* 477:477 */      return this.entry;
/* 478:    */    } }
/* 479:    */  
/* 480:    */  private final class MapEntrySet extends AbstractObjectSet<Object2ObjectMap.Entry<K, V>> implements Object2ObjectMap.FastEntrySet<K, V> { private MapEntrySet() {}
/* 481:    */    
/* 482:482 */    public ObjectIterator<Object2ObjectMap.Entry<K, V>> iterator() { return new Object2ObjectOpenCustomHashMap.EntryIterator(Object2ObjectOpenCustomHashMap.this, null); }
/* 483:    */    
/* 484:    */    public ObjectIterator<Object2ObjectMap.Entry<K, V>> fastIterator() {
/* 485:485 */      return new Object2ObjectOpenCustomHashMap.FastEntryIterator(Object2ObjectOpenCustomHashMap.this, null);
/* 486:    */    }
/* 487:    */    
/* 488:    */    public boolean contains(Object o) {
/* 489:489 */      if (!(o instanceof Map.Entry)) return false;
/* 490:490 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 491:491 */      K k = e.getKey();
/* 492:    */      
/* 493:493 */      int pos = HashCommon.murmurHash3(Object2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectOpenCustomHashMap.this.mask;
/* 494:    */      
/* 495:495 */      while (Object2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 496:496 */        if (Object2ObjectOpenCustomHashMap.this.strategy.equals(Object2ObjectOpenCustomHashMap.this.key[pos], k)) return Object2ObjectOpenCustomHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Object2ObjectOpenCustomHashMap.this.value[pos].equals(e.getValue());
/* 497:497 */        pos = pos + 1 & Object2ObjectOpenCustomHashMap.this.mask;
/* 498:    */      }
/* 499:499 */      return false;
/* 500:    */    }
/* 501:    */    
/* 502:    */    public boolean remove(Object o) {
/* 503:503 */      if (!(o instanceof Map.Entry)) return false;
/* 504:504 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 505:505 */      K k = e.getKey();
/* 506:    */      
/* 507:507 */      int pos = HashCommon.murmurHash3(Object2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectOpenCustomHashMap.this.mask;
/* 508:    */      
/* 509:509 */      while (Object2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 510:510 */        if (Object2ObjectOpenCustomHashMap.this.strategy.equals(Object2ObjectOpenCustomHashMap.this.key[pos], k)) {
/* 511:511 */          Object2ObjectOpenCustomHashMap.this.remove(e.getKey());
/* 512:512 */          return true;
/* 513:    */        }
/* 514:514 */        pos = pos + 1 & Object2ObjectOpenCustomHashMap.this.mask;
/* 515:    */      }
/* 516:516 */      return false;
/* 517:    */    }
/* 518:    */    
/* 519:519 */    public int size() { return Object2ObjectOpenCustomHashMap.this.size; }
/* 520:    */    
/* 522:522 */    public void clear() { Object2ObjectOpenCustomHashMap.this.clear(); }
/* 523:    */  }
/* 524:    */  
/* 525:    */  public Object2ObjectMap.FastEntrySet<K, V> object2ObjectEntrySet() {
/* 526:526 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 527:527 */    return this.entries;
/* 528:    */  }
/* 529:    */  
/* 532:    */  private final class KeyIterator
/* 533:    */    extends Object2ObjectOpenCustomHashMap<K, V>.MapIterator
/* 534:    */    implements ObjectIterator<K>
/* 535:    */  {
/* 536:536 */    public KeyIterator() { super(null); }
/* 537:537 */    public K next() { return Object2ObjectOpenCustomHashMap.this.key[nextEntry()]; } }
/* 538:    */  
/* 539:    */  private final class KeySet extends AbstractObjectSet<K> { private KeySet() {}
/* 540:    */    
/* 541:541 */    public ObjectIterator<K> iterator() { return new Object2ObjectOpenCustomHashMap.KeyIterator(Object2ObjectOpenCustomHashMap.this); }
/* 542:    */    
/* 543:    */    public int size() {
/* 544:544 */      return Object2ObjectOpenCustomHashMap.this.size;
/* 545:    */    }
/* 546:    */    
/* 547:547 */    public boolean contains(Object k) { return Object2ObjectOpenCustomHashMap.this.containsKey(k); }
/* 548:    */    
/* 549:    */    public boolean remove(Object k) {
/* 550:550 */      int oldSize = Object2ObjectOpenCustomHashMap.this.size;
/* 551:551 */      Object2ObjectOpenCustomHashMap.this.remove(k);
/* 552:552 */      return Object2ObjectOpenCustomHashMap.this.size != oldSize;
/* 553:    */    }
/* 554:    */    
/* 555:555 */    public void clear() { Object2ObjectOpenCustomHashMap.this.clear(); }
/* 556:    */  }
/* 557:    */  
/* 558:    */  public ObjectSet<K> keySet() {
/* 559:559 */    if (this.keys == null) this.keys = new KeySet(null);
/* 560:560 */    return this.keys;
/* 561:    */  }
/* 562:    */  
/* 565:    */  private final class ValueIterator
/* 566:    */    extends Object2ObjectOpenCustomHashMap<K, V>.MapIterator
/* 567:    */    implements ObjectIterator<V>
/* 568:    */  {
/* 569:569 */    public ValueIterator() { super(null); }
/* 570:570 */    public V next() { return Object2ObjectOpenCustomHashMap.this.value[nextEntry()]; }
/* 571:    */  }
/* 572:    */  
/* 573:573 */  public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 574:    */        public ObjectIterator<V> iterator() {
/* 575:575 */          return new Object2ObjectOpenCustomHashMap.ValueIterator(Object2ObjectOpenCustomHashMap.this);
/* 576:    */        }
/* 577:    */        
/* 578:578 */        public int size() { return Object2ObjectOpenCustomHashMap.this.size; }
/* 579:    */        
/* 580:    */        public boolean contains(Object v) {
/* 581:581 */          return Object2ObjectOpenCustomHashMap.this.containsValue(v);
/* 582:    */        }
/* 583:    */        
/* 584:584 */        public void clear() { Object2ObjectOpenCustomHashMap.this.clear(); }
/* 585:    */      };
/* 586:    */    }
/* 587:587 */    return this.values;
/* 588:    */  }
/* 589:    */  
/* 598:    */  @Deprecated
/* 599:    */  public boolean rehash()
/* 600:    */  {
/* 601:601 */    return true;
/* 602:    */  }
/* 603:    */  
/* 614:    */  public boolean trim()
/* 615:    */  {
/* 616:616 */    int l = HashCommon.arraySize(this.size, this.f);
/* 617:617 */    if (l >= this.n) return true;
/* 618:    */    try {
/* 619:619 */      rehash(l);
/* 620:    */    } catch (OutOfMemoryError cantDoIt) {
/* 621:621 */      return false; }
/* 622:622 */    return true;
/* 623:    */  }
/* 624:    */  
/* 641:    */  public boolean trim(int n)
/* 642:    */  {
/* 643:643 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 644:644 */    if (this.n <= l) return true;
/* 645:    */    try {
/* 646:646 */      rehash(l);
/* 647:    */    } catch (OutOfMemoryError cantDoIt) {
/* 648:648 */      return false; }
/* 649:649 */    return true;
/* 650:    */  }
/* 651:    */  
/* 660:    */  protected void rehash(int newN)
/* 661:    */  {
/* 662:662 */    int i = 0;
/* 663:663 */    boolean[] used = this.used;
/* 664:    */    
/* 665:665 */    K[] key = this.key;
/* 666:666 */    V[] value = this.value;
/* 667:667 */    int newMask = newN - 1;
/* 668:668 */    K[] newKey = (Object[])new Object[newN];
/* 669:669 */    V[] newValue = (Object[])new Object[newN];
/* 670:670 */    boolean[] newUsed = new boolean[newN];
/* 671:671 */    for (int j = this.size; j-- != 0;) {
/* 672:672 */      while (used[i] == 0) i++;
/* 673:673 */      K k = key[i];
/* 674:674 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 675:675 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 676:676 */      newUsed[pos] = true;
/* 677:677 */      newKey[pos] = k;
/* 678:678 */      newValue[pos] = value[i];
/* 679:679 */      i++;
/* 680:    */    }
/* 681:681 */    this.n = newN;
/* 682:682 */    this.mask = newMask;
/* 683:683 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 684:684 */    this.key = newKey;
/* 685:685 */    this.value = newValue;
/* 686:686 */    this.used = newUsed;
/* 687:    */  }
/* 688:    */  
/* 692:    */  public Object2ObjectOpenCustomHashMap<K, V> clone()
/* 693:    */  {
/* 694:    */    Object2ObjectOpenCustomHashMap<K, V> c;
/* 695:    */    
/* 697:    */    try
/* 698:    */    {
/* 699:699 */      c = (Object2ObjectOpenCustomHashMap)super.clone();
/* 700:    */    }
/* 701:    */    catch (CloneNotSupportedException cantHappen) {
/* 702:702 */      throw new InternalError();
/* 703:    */    }
/* 704:704 */    c.keys = null;
/* 705:705 */    c.values = null;
/* 706:706 */    c.entries = null;
/* 707:707 */    c.key = ((Object[])this.key.clone());
/* 708:708 */    c.value = ((Object[])this.value.clone());
/* 709:709 */    c.used = ((boolean[])this.used.clone());
/* 710:710 */    c.strategy = this.strategy;
/* 711:711 */    return c;
/* 712:    */  }
/* 713:    */  
/* 721:    */  public int hashCode()
/* 722:    */  {
/* 723:723 */    int h = 0;
/* 724:724 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 725:725 */      while (this.used[i] == 0) i++;
/* 726:726 */      if (this != this.key[i])
/* 727:727 */        t = this.strategy.hashCode(this.key[i]);
/* 728:728 */      if (this != this.value[i])
/* 729:729 */        t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 730:730 */      h += t;
/* 731:731 */      i++;
/* 732:    */    }
/* 733:733 */    return h;
/* 734:    */  }
/* 735:    */  
/* 736:736 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 737:737 */    V[] value = this.value;
/* 738:738 */    Object2ObjectOpenCustomHashMap<K, V>.MapIterator i = new MapIterator(null);
/* 739:739 */    s.defaultWriteObject();
/* 740:740 */    for (int j = this.size; j-- != 0;) {
/* 741:741 */      int e = i.nextEntry();
/* 742:742 */      s.writeObject(key[e]);
/* 743:743 */      s.writeObject(value[e]);
/* 744:    */    }
/* 745:    */  }
/* 746:    */  
/* 747:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 748:748 */    s.defaultReadObject();
/* 749:749 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 750:750 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 751:751 */    this.mask = (this.n - 1);
/* 752:752 */    K[] key = this.key = (Object[])new Object[this.n];
/* 753:753 */    V[] value = this.value = (Object[])new Object[this.n];
/* 754:754 */    boolean[] used = this.used = new boolean[this.n];
/* 755:    */    
/* 757:757 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 758:758 */      K k = s.readObject();
/* 759:759 */      V v = s.readObject();
/* 760:760 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 761:761 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 762:762 */      used[pos] = true;
/* 763:763 */      key[pos] = k;
/* 764:764 */      value[pos] = v;
/* 765:    */    }
/* 766:    */  }
/* 767:    */  
/* 768:    */  private void checkTable() {}
/* 769:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */