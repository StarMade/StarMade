/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.ObjectOutputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Map;
/*  11:    */import java.util.Map.Entry;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/*  84:    */public class Reference2ObjectOpenHashMap<K, V>
/*  85:    */  extends AbstractReference2ObjectMap<K, V>
/*  86:    */  implements Serializable, Cloneable, Hash
/*  87:    */{
/*  88:    */  public static final long serialVersionUID = 0L;
/*  89:    */  private static final boolean ASSERTS = false;
/*  90:    */  protected transient K[] key;
/*  91:    */  protected transient V[] value;
/*  92:    */  protected transient boolean[] used;
/*  93:    */  protected final float f;
/*  94:    */  protected transient int n;
/*  95:    */  protected transient int maxFill;
/*  96:    */  protected transient int mask;
/*  97:    */  protected int size;
/*  98:    */  protected volatile transient Reference2ObjectMap.FastEntrySet<K, V> entries;
/*  99:    */  protected volatile transient ReferenceSet<K> keys;
/* 100:    */  protected volatile transient ObjectCollection<V> values;
/* 101:    */  
/* 102:    */  public Reference2ObjectOpenHashMap(int expected, float f)
/* 103:    */  {
/* 104:104 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 105:105 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 106:106 */    this.f = f;
/* 107:107 */    this.n = HashCommon.arraySize(expected, f);
/* 108:108 */    this.mask = (this.n - 1);
/* 109:109 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 110:110 */    this.key = ((Object[])new Object[this.n]);
/* 111:111 */    this.value = ((Object[])new Object[this.n]);
/* 112:112 */    this.used = new boolean[this.n];
/* 113:    */  }
/* 114:    */  
/* 117:    */  public Reference2ObjectOpenHashMap(int expected)
/* 118:    */  {
/* 119:119 */    this(expected, 0.75F);
/* 120:    */  }
/* 121:    */  
/* 123:    */  public Reference2ObjectOpenHashMap()
/* 124:    */  {
/* 125:125 */    this(16, 0.75F);
/* 126:    */  }
/* 127:    */  
/* 131:    */  public Reference2ObjectOpenHashMap(Map<? extends K, ? extends V> m, float f)
/* 132:    */  {
/* 133:133 */    this(m.size(), f);
/* 134:134 */    putAll(m);
/* 135:    */  }
/* 136:    */  
/* 139:    */  public Reference2ObjectOpenHashMap(Map<? extends K, ? extends V> m)
/* 140:    */  {
/* 141:141 */    this(m, 0.75F);
/* 142:    */  }
/* 143:    */  
/* 147:    */  public Reference2ObjectOpenHashMap(Reference2ObjectMap<K, V> m, float f)
/* 148:    */  {
/* 149:149 */    this(m.size(), f);
/* 150:150 */    putAll(m);
/* 151:    */  }
/* 152:    */  
/* 155:    */  public Reference2ObjectOpenHashMap(Reference2ObjectMap<K, V> m)
/* 156:    */  {
/* 157:157 */    this(m, 0.75F);
/* 158:    */  }
/* 159:    */  
/* 165:    */  public Reference2ObjectOpenHashMap(K[] k, V[] v, float f)
/* 166:    */  {
/* 167:167 */    this(k.length, f);
/* 168:168 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 169:169 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 176:    */  public Reference2ObjectOpenHashMap(K[] k, V[] v)
/* 177:    */  {
/* 178:178 */    this(k, v, 0.75F);
/* 179:    */  }
/* 180:    */  
/* 184:    */  public V put(K k, V v)
/* 185:    */  {
/* 186:186 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 187:    */    
/* 188:188 */    while (this.used[pos] != 0) {
/* 189:189 */      if (this.key[pos] == k) {
/* 190:190 */        V oldValue = this.value[pos];
/* 191:191 */        this.value[pos] = v;
/* 192:192 */        return oldValue;
/* 193:    */      }
/* 194:194 */      pos = pos + 1 & this.mask;
/* 195:    */    }
/* 196:196 */    this.used[pos] = true;
/* 197:197 */    this.key[pos] = k;
/* 198:198 */    this.value[pos] = v;
/* 199:199 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 200:    */    }
/* 201:201 */    return this.defRetValue;
/* 202:    */  }
/* 203:    */  
/* 206:    */  protected final int shiftKeys(int pos)
/* 207:    */  {
/* 208:    */    int last;
/* 209:    */    
/* 211:    */    for (;;)
/* 212:    */    {
/* 213:213 */      pos = (last = pos) + 1 & this.mask;
/* 214:214 */      while (this.used[pos] != 0) {
/* 215:215 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 216:216 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 217:217 */        pos = pos + 1 & this.mask;
/* 218:    */      }
/* 219:219 */      if (this.used[pos] == 0) break;
/* 220:220 */      this.key[last] = this.key[pos];
/* 221:221 */      this.value[last] = this.value[pos];
/* 222:    */    }
/* 223:223 */    this.used[last] = false;
/* 224:224 */    this.key[last] = null;
/* 225:225 */    this.value[last] = null;
/* 226:226 */    return last;
/* 227:    */  }
/* 228:    */  
/* 229:    */  public V remove(Object k)
/* 230:    */  {
/* 231:231 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 232:    */    
/* 233:233 */    while (this.used[pos] != 0) {
/* 234:234 */      if (this.key[pos] == k) {
/* 235:235 */        this.size -= 1;
/* 236:236 */        V v = this.value[pos];
/* 237:237 */        shiftKeys(pos);
/* 238:238 */        return v;
/* 239:    */      }
/* 240:240 */      pos = pos + 1 & this.mask;
/* 241:    */    }
/* 242:242 */    return this.defRetValue;
/* 243:    */  }
/* 244:    */  
/* 245:    */  public V get(Object k)
/* 246:    */  {
/* 247:247 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 248:    */    
/* 249:249 */    while (this.used[pos] != 0) {
/* 250:250 */      if (this.key[pos] == k) return this.value[pos];
/* 251:251 */      pos = pos + 1 & this.mask;
/* 252:    */    }
/* 253:253 */    return this.defRetValue;
/* 254:    */  }
/* 255:    */  
/* 256:    */  public boolean containsKey(Object k)
/* 257:    */  {
/* 258:258 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 259:    */    
/* 260:260 */    while (this.used[pos] != 0) {
/* 261:261 */      if (this.key[pos] == k) return true;
/* 262:262 */      pos = pos + 1 & this.mask;
/* 263:    */    }
/* 264:264 */    return false;
/* 265:    */  }
/* 266:    */  
/* 267:267 */  public boolean containsValue(Object v) { V[] value = this.value;
/* 268:268 */    boolean[] used = this.used;
/* 269:269 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v))) break label16;
/* 270:270 */    return false;
/* 271:    */  }
/* 272:    */  
/* 277:    */  public void clear()
/* 278:    */  {
/* 279:279 */    if (this.size == 0) return;
/* 280:280 */    this.size = 0;
/* 281:281 */    BooleanArrays.fill(this.used, false);
/* 282:    */    
/* 283:283 */    ObjectArrays.fill(this.key, null);
/* 284:284 */    ObjectArrays.fill(this.value, null);
/* 285:    */  }
/* 286:    */  
/* 287:287 */  public int size() { return this.size; }
/* 288:    */  
/* 289:    */  public boolean isEmpty() {
/* 290:290 */    return this.size == 0;
/* 291:    */  }
/* 292:    */  
/* 297:    */  @Deprecated
/* 298:    */  public void growthFactor(int growthFactor) {}
/* 299:    */  
/* 304:    */  @Deprecated
/* 305:    */  public int growthFactor()
/* 306:    */  {
/* 307:307 */    return 16;
/* 308:    */  }
/* 309:    */  
/* 310:    */  private final class MapEntry
/* 311:    */    implements Reference2ObjectMap.Entry<K, V>, Map.Entry<K, V>
/* 312:    */  {
/* 313:    */    private int index;
/* 314:    */    
/* 315:    */    MapEntry(int index)
/* 316:    */    {
/* 317:317 */      this.index = index;
/* 318:    */    }
/* 319:    */    
/* 320:320 */    public K getKey() { return Reference2ObjectOpenHashMap.this.key[this.index]; }
/* 321:    */    
/* 323:323 */    public V getValue() { return Reference2ObjectOpenHashMap.this.value[this.index]; }
/* 324:    */    
/* 325:    */    public V setValue(V v) {
/* 326:326 */      V oldValue = Reference2ObjectOpenHashMap.this.value[this.index];
/* 327:327 */      Reference2ObjectOpenHashMap.this.value[this.index] = v;
/* 328:328 */      return oldValue;
/* 329:    */    }
/* 330:    */    
/* 331:    */    public boolean equals(Object o) {
/* 332:332 */      if (!(o instanceof Map.Entry)) return false;
/* 333:333 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 334:334 */      return (Reference2ObjectOpenHashMap.this.key[this.index] == e.getKey()) && (Reference2ObjectOpenHashMap.this.value[this.index] == null ? e.getValue() == null : Reference2ObjectOpenHashMap.this.value[this.index].equals(e.getValue()));
/* 335:    */    }
/* 336:    */    
/* 337:337 */    public int hashCode() { return (Reference2ObjectOpenHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2ObjectOpenHashMap.this.key[this.index])) ^ (Reference2ObjectOpenHashMap.this.value[this.index] == null ? 0 : Reference2ObjectOpenHashMap.this.value[this.index].hashCode()); }
/* 338:    */    
/* 340:340 */    public String toString() { return Reference2ObjectOpenHashMap.this.key[this.index] + "=>" + Reference2ObjectOpenHashMap.this.value[this.index]; } }
/* 341:    */  
/* 342:    */  private class MapIterator { int pos;
/* 343:    */    int last;
/* 344:    */    int c;
/* 345:    */    ReferenceArrayList<K> wrapped;
/* 346:    */    
/* 347:347 */    private MapIterator() { this.pos = Reference2ObjectOpenHashMap.this.n;
/* 348:    */      
/* 350:350 */      this.last = -1;
/* 351:    */      
/* 352:352 */      this.c = Reference2ObjectOpenHashMap.this.size;
/* 353:    */      
/* 357:357 */      boolean[] used = Reference2ObjectOpenHashMap.this.used;
/* 358:358 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 359:    */    }
/* 360:    */    
/* 361:361 */    public boolean hasNext() { return this.c != 0; }
/* 362:    */    
/* 363:    */    public int nextEntry() {
/* 364:364 */      if (!hasNext()) throw new NoSuchElementException();
/* 365:365 */      this.c -= 1;
/* 366:    */      
/* 367:367 */      if (this.pos < 0) {
/* 368:368 */        Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/* 369:    */        
/* 370:370 */        int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ObjectOpenHashMap.this.mask;
/* 371:    */        
/* 372:372 */        while (Reference2ObjectOpenHashMap.this.used[pos] != 0) {
/* 373:373 */          if (Reference2ObjectOpenHashMap.this.key[pos] == k) return pos;
/* 374:374 */          pos = pos + 1 & Reference2ObjectOpenHashMap.this.mask;
/* 375:    */        }
/* 376:    */      }
/* 377:377 */      this.last = this.pos;
/* 378:    */      
/* 379:379 */      if (this.c != 0) {
/* 380:380 */        boolean[] used = Reference2ObjectOpenHashMap.this.used;
/* 381:381 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 382:    */      }
/* 383:    */      
/* 384:384 */      return this.last;
/* 385:    */    }
/* 386:    */    
/* 390:    */    protected final int shiftKeys(int pos)
/* 391:    */    {
/* 392:    */      int last;
/* 393:    */      
/* 395:    */      for (;;)
/* 396:    */      {
/* 397:397 */        pos = (last = pos) + 1 & Reference2ObjectOpenHashMap.this.mask;
/* 398:398 */        while (Reference2ObjectOpenHashMap.this.used[pos] != 0) {
/* 399:399 */          int slot = (Reference2ObjectOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2ObjectOpenHashMap.this.key[pos]))) & Reference2ObjectOpenHashMap.this.mask;
/* 400:400 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 401:401 */          pos = pos + 1 & Reference2ObjectOpenHashMap.this.mask;
/* 402:    */        }
/* 403:403 */        if (Reference2ObjectOpenHashMap.this.used[pos] == 0) break;
/* 404:404 */        if (pos < last)
/* 405:    */        {
/* 406:406 */          if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 407:407 */          this.wrapped.add(Reference2ObjectOpenHashMap.this.key[pos]);
/* 408:    */        }
/* 409:409 */        Reference2ObjectOpenHashMap.this.key[last] = Reference2ObjectOpenHashMap.this.key[pos];
/* 410:410 */        Reference2ObjectOpenHashMap.this.value[last] = Reference2ObjectOpenHashMap.this.value[pos];
/* 411:    */      }
/* 412:412 */      Reference2ObjectOpenHashMap.this.used[last] = false;
/* 413:413 */      Reference2ObjectOpenHashMap.this.key[last] = null;
/* 414:414 */      Reference2ObjectOpenHashMap.this.value[last] = null;
/* 415:415 */      return last;
/* 416:    */    }
/* 417:    */    
/* 418:    */    public void remove() {
/* 419:419 */      if (this.last == -1) throw new IllegalStateException();
/* 420:420 */      if (this.pos < -1)
/* 421:    */      {
/* 422:422 */        Reference2ObjectOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 423:423 */        this.last = -1;
/* 424:424 */        return;
/* 425:    */      }
/* 426:426 */      Reference2ObjectOpenHashMap.this.size -= 1;
/* 427:427 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 428:428 */        this.c += 1;
/* 429:429 */        nextEntry();
/* 430:    */      }
/* 431:431 */      this.last = -1;
/* 432:    */    }
/* 433:    */    
/* 434:    */    public int skip(int n) {
/* 435:435 */      int i = n;
/* 436:436 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 437:437 */      return n - i - 1;
/* 438:    */    } }
/* 439:    */  
/* 440:440 */  private class EntryIterator extends Reference2ObjectOpenHashMap<K, V>.MapIterator implements ObjectIterator<Reference2ObjectMap.Entry<K, V>> { private EntryIterator() { super(null); }
/* 441:    */    
/* 442:    */    private Reference2ObjectOpenHashMap<K, V>.MapEntry entry;
/* 443:443 */    public Reference2ObjectMap.Entry<K, V> next() { return this.entry = new Reference2ObjectOpenHashMap.MapEntry(Reference2ObjectOpenHashMap.this, nextEntry()); }
/* 444:    */    
/* 445:    */    public void remove()
/* 446:    */    {
/* 447:447 */      super.remove();
/* 448:448 */      Reference2ObjectOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 449:    */    } }
/* 450:    */  
/* 451:451 */  private class FastEntryIterator extends Reference2ObjectOpenHashMap<K, V>.MapIterator implements ObjectIterator<Reference2ObjectMap.Entry<K, V>> { private FastEntryIterator() { super(null); }
/* 452:452 */    final AbstractReference2ObjectMap.BasicEntry<K, V> entry = new AbstractReference2ObjectMap.BasicEntry(null, null);
/* 453:    */    
/* 454:454 */    public AbstractReference2ObjectMap.BasicEntry<K, V> next() { int e = nextEntry();
/* 455:455 */      this.entry.key = Reference2ObjectOpenHashMap.this.key[e];
/* 456:456 */      this.entry.value = Reference2ObjectOpenHashMap.this.value[e];
/* 457:457 */      return this.entry;
/* 458:    */    } }
/* 459:    */  
/* 460:    */  private final class MapEntrySet extends AbstractObjectSet<Reference2ObjectMap.Entry<K, V>> implements Reference2ObjectMap.FastEntrySet<K, V> { private MapEntrySet() {}
/* 461:    */    
/* 462:462 */    public ObjectIterator<Reference2ObjectMap.Entry<K, V>> iterator() { return new Reference2ObjectOpenHashMap.EntryIterator(Reference2ObjectOpenHashMap.this, null); }
/* 463:    */    
/* 464:    */    public ObjectIterator<Reference2ObjectMap.Entry<K, V>> fastIterator() {
/* 465:465 */      return new Reference2ObjectOpenHashMap.FastEntryIterator(Reference2ObjectOpenHashMap.this, null);
/* 466:    */    }
/* 467:    */    
/* 468:    */    public boolean contains(Object o) {
/* 469:469 */      if (!(o instanceof Map.Entry)) return false;
/* 470:470 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 471:471 */      K k = e.getKey();
/* 472:    */      
/* 473:473 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ObjectOpenHashMap.this.mask;
/* 474:    */      
/* 475:475 */      while (Reference2ObjectOpenHashMap.this.used[pos] != 0) {
/* 476:476 */        if (Reference2ObjectOpenHashMap.this.key[pos] == k) return Reference2ObjectOpenHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Reference2ObjectOpenHashMap.this.value[pos].equals(e.getValue());
/* 477:477 */        pos = pos + 1 & Reference2ObjectOpenHashMap.this.mask;
/* 478:    */      }
/* 479:479 */      return false;
/* 480:    */    }
/* 481:    */    
/* 482:    */    public boolean remove(Object o) {
/* 483:483 */      if (!(o instanceof Map.Entry)) return false;
/* 484:484 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 485:485 */      K k = e.getKey();
/* 486:    */      
/* 487:487 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ObjectOpenHashMap.this.mask;
/* 488:    */      
/* 489:489 */      while (Reference2ObjectOpenHashMap.this.used[pos] != 0) {
/* 490:490 */        if (Reference2ObjectOpenHashMap.this.key[pos] == k) {
/* 491:491 */          Reference2ObjectOpenHashMap.this.remove(e.getKey());
/* 492:492 */          return true;
/* 493:    */        }
/* 494:494 */        pos = pos + 1 & Reference2ObjectOpenHashMap.this.mask;
/* 495:    */      }
/* 496:496 */      return false;
/* 497:    */    }
/* 498:    */    
/* 499:499 */    public int size() { return Reference2ObjectOpenHashMap.this.size; }
/* 500:    */    
/* 502:502 */    public void clear() { Reference2ObjectOpenHashMap.this.clear(); }
/* 503:    */  }
/* 504:    */  
/* 505:    */  public Reference2ObjectMap.FastEntrySet<K, V> reference2ObjectEntrySet() {
/* 506:506 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 507:507 */    return this.entries;
/* 508:    */  }
/* 509:    */  
/* 512:    */  private final class KeyIterator
/* 513:    */    extends Reference2ObjectOpenHashMap<K, V>.MapIterator
/* 514:    */    implements ObjectIterator<K>
/* 515:    */  {
/* 516:516 */    public KeyIterator() { super(null); }
/* 517:517 */    public K next() { return Reference2ObjectOpenHashMap.this.key[nextEntry()]; } }
/* 518:    */  
/* 519:    */  private final class KeySet extends AbstractReferenceSet<K> { private KeySet() {}
/* 520:    */    
/* 521:521 */    public ObjectIterator<K> iterator() { return new Reference2ObjectOpenHashMap.KeyIterator(Reference2ObjectOpenHashMap.this); }
/* 522:    */    
/* 523:    */    public int size() {
/* 524:524 */      return Reference2ObjectOpenHashMap.this.size;
/* 525:    */    }
/* 526:    */    
/* 527:527 */    public boolean contains(Object k) { return Reference2ObjectOpenHashMap.this.containsKey(k); }
/* 528:    */    
/* 529:    */    public boolean remove(Object k) {
/* 530:530 */      int oldSize = Reference2ObjectOpenHashMap.this.size;
/* 531:531 */      Reference2ObjectOpenHashMap.this.remove(k);
/* 532:532 */      return Reference2ObjectOpenHashMap.this.size != oldSize;
/* 533:    */    }
/* 534:    */    
/* 535:535 */    public void clear() { Reference2ObjectOpenHashMap.this.clear(); }
/* 536:    */  }
/* 537:    */  
/* 538:    */  public ReferenceSet<K> keySet() {
/* 539:539 */    if (this.keys == null) this.keys = new KeySet(null);
/* 540:540 */    return this.keys;
/* 541:    */  }
/* 542:    */  
/* 545:    */  private final class ValueIterator
/* 546:    */    extends Reference2ObjectOpenHashMap<K, V>.MapIterator
/* 547:    */    implements ObjectIterator<V>
/* 548:    */  {
/* 549:549 */    public ValueIterator() { super(null); }
/* 550:550 */    public V next() { return Reference2ObjectOpenHashMap.this.value[nextEntry()]; }
/* 551:    */  }
/* 552:    */  
/* 553:553 */  public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 554:    */        public ObjectIterator<V> iterator() {
/* 555:555 */          return new Reference2ObjectOpenHashMap.ValueIterator(Reference2ObjectOpenHashMap.this);
/* 556:    */        }
/* 557:    */        
/* 558:558 */        public int size() { return Reference2ObjectOpenHashMap.this.size; }
/* 559:    */        
/* 560:    */        public boolean contains(Object v) {
/* 561:561 */          return Reference2ObjectOpenHashMap.this.containsValue(v);
/* 562:    */        }
/* 563:    */        
/* 564:564 */        public void clear() { Reference2ObjectOpenHashMap.this.clear(); }
/* 565:    */      };
/* 566:    */    }
/* 567:567 */    return this.values;
/* 568:    */  }
/* 569:    */  
/* 578:    */  @Deprecated
/* 579:    */  public boolean rehash()
/* 580:    */  {
/* 581:581 */    return true;
/* 582:    */  }
/* 583:    */  
/* 594:    */  public boolean trim()
/* 595:    */  {
/* 596:596 */    int l = HashCommon.arraySize(this.size, this.f);
/* 597:597 */    if (l >= this.n) return true;
/* 598:    */    try {
/* 599:599 */      rehash(l);
/* 600:    */    } catch (OutOfMemoryError cantDoIt) {
/* 601:601 */      return false; }
/* 602:602 */    return true;
/* 603:    */  }
/* 604:    */  
/* 621:    */  public boolean trim(int n)
/* 622:    */  {
/* 623:623 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 624:624 */    if (this.n <= l) return true;
/* 625:    */    try {
/* 626:626 */      rehash(l);
/* 627:    */    } catch (OutOfMemoryError cantDoIt) {
/* 628:628 */      return false; }
/* 629:629 */    return true;
/* 630:    */  }
/* 631:    */  
/* 640:    */  protected void rehash(int newN)
/* 641:    */  {
/* 642:642 */    int i = 0;
/* 643:643 */    boolean[] used = this.used;
/* 644:    */    
/* 645:645 */    K[] key = this.key;
/* 646:646 */    V[] value = this.value;
/* 647:647 */    int newMask = newN - 1;
/* 648:648 */    K[] newKey = (Object[])new Object[newN];
/* 649:649 */    V[] newValue = (Object[])new Object[newN];
/* 650:650 */    boolean[] newUsed = new boolean[newN];
/* 651:651 */    for (int j = this.size; j-- != 0;) {
/* 652:652 */      while (used[i] == 0) i++;
/* 653:653 */      K k = key[i];
/* 654:654 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 655:655 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 656:656 */      newUsed[pos] = true;
/* 657:657 */      newKey[pos] = k;
/* 658:658 */      newValue[pos] = value[i];
/* 659:659 */      i++;
/* 660:    */    }
/* 661:661 */    this.n = newN;
/* 662:662 */    this.mask = newMask;
/* 663:663 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 664:664 */    this.key = newKey;
/* 665:665 */    this.value = newValue;
/* 666:666 */    this.used = newUsed;
/* 667:    */  }
/* 668:    */  
/* 672:    */  public Reference2ObjectOpenHashMap<K, V> clone()
/* 673:    */  {
/* 674:    */    Reference2ObjectOpenHashMap<K, V> c;
/* 675:    */    
/* 677:    */    try
/* 678:    */    {
/* 679:679 */      c = (Reference2ObjectOpenHashMap)super.clone();
/* 680:    */    }
/* 681:    */    catch (CloneNotSupportedException cantHappen) {
/* 682:682 */      throw new InternalError();
/* 683:    */    }
/* 684:684 */    c.keys = null;
/* 685:685 */    c.values = null;
/* 686:686 */    c.entries = null;
/* 687:687 */    c.key = ((Object[])this.key.clone());
/* 688:688 */    c.value = ((Object[])this.value.clone());
/* 689:689 */    c.used = ((boolean[])this.used.clone());
/* 690:690 */    return c;
/* 691:    */  }
/* 692:    */  
/* 700:    */  public int hashCode()
/* 701:    */  {
/* 702:702 */    int h = 0;
/* 703:703 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 704:704 */      while (this.used[i] == 0) i++;
/* 705:705 */      if (this != this.key[i])
/* 706:706 */        t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 707:707 */      if (this != this.value[i])
/* 708:708 */        t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 709:709 */      h += t;
/* 710:710 */      i++;
/* 711:    */    }
/* 712:712 */    return h;
/* 713:    */  }
/* 714:    */  
/* 715:715 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 716:716 */    V[] value = this.value;
/* 717:717 */    Reference2ObjectOpenHashMap<K, V>.MapIterator i = new MapIterator(null);
/* 718:718 */    s.defaultWriteObject();
/* 719:719 */    for (int j = this.size; j-- != 0;) {
/* 720:720 */      int e = i.nextEntry();
/* 721:721 */      s.writeObject(key[e]);
/* 722:722 */      s.writeObject(value[e]);
/* 723:    */    }
/* 724:    */  }
/* 725:    */  
/* 726:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 727:727 */    s.defaultReadObject();
/* 728:728 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 729:729 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 730:730 */    this.mask = (this.n - 1);
/* 731:731 */    K[] key = this.key = (Object[])new Object[this.n];
/* 732:732 */    V[] value = this.value = (Object[])new Object[this.n];
/* 733:733 */    boolean[] used = this.used = new boolean[this.n];
/* 734:    */    
/* 736:736 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 737:737 */      K k = s.readObject();
/* 738:738 */      V v = s.readObject();
/* 739:739 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 740:740 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 741:741 */      used[pos] = true;
/* 742:742 */      key[pos] = k;
/* 743:743 */      value[pos] = v;
/* 744:    */    }
/* 745:    */  }
/* 746:    */  
/* 747:    */  private void checkTable() {}
/* 748:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */