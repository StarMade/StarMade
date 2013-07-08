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
/*  84:    */public class Object2ReferenceOpenHashMap<K, V>
/*  85:    */  extends AbstractObject2ReferenceMap<K, V>
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
/*  98:    */  protected volatile transient Object2ReferenceMap.FastEntrySet<K, V> entries;
/*  99:    */  protected volatile transient ObjectSet<K> keys;
/* 100:    */  protected volatile transient ReferenceCollection<V> values;
/* 101:    */  
/* 102:    */  public Object2ReferenceOpenHashMap(int expected, float f)
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
/* 117:    */  public Object2ReferenceOpenHashMap(int expected)
/* 118:    */  {
/* 119:119 */    this(expected, 0.75F);
/* 120:    */  }
/* 121:    */  
/* 123:    */  public Object2ReferenceOpenHashMap()
/* 124:    */  {
/* 125:125 */    this(16, 0.75F);
/* 126:    */  }
/* 127:    */  
/* 131:    */  public Object2ReferenceOpenHashMap(Map<? extends K, ? extends V> m, float f)
/* 132:    */  {
/* 133:133 */    this(m.size(), f);
/* 134:134 */    putAll(m);
/* 135:    */  }
/* 136:    */  
/* 139:    */  public Object2ReferenceOpenHashMap(Map<? extends K, ? extends V> m)
/* 140:    */  {
/* 141:141 */    this(m, 0.75F);
/* 142:    */  }
/* 143:    */  
/* 147:    */  public Object2ReferenceOpenHashMap(Object2ReferenceMap<K, V> m, float f)
/* 148:    */  {
/* 149:149 */    this(m.size(), f);
/* 150:150 */    putAll(m);
/* 151:    */  }
/* 152:    */  
/* 155:    */  public Object2ReferenceOpenHashMap(Object2ReferenceMap<K, V> m)
/* 156:    */  {
/* 157:157 */    this(m, 0.75F);
/* 158:    */  }
/* 159:    */  
/* 165:    */  public Object2ReferenceOpenHashMap(K[] k, V[] v, float f)
/* 166:    */  {
/* 167:167 */    this(k.length, f);
/* 168:168 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 169:169 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 176:    */  public Object2ReferenceOpenHashMap(K[] k, V[] v)
/* 177:    */  {
/* 178:178 */    this(k, v, 0.75F);
/* 179:    */  }
/* 180:    */  
/* 184:    */  public V put(K k, V v)
/* 185:    */  {
/* 186:186 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 187:    */    
/* 188:188 */    while (this.used[pos] != 0) {
/* 189:189 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 215:215 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
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
/* 231:231 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 232:    */    
/* 233:233 */    while (this.used[pos] != 0) {
/* 234:234 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 247:247 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 248:    */    
/* 249:249 */    while (this.used[pos] != 0) {
/* 250:250 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.value[pos];
/* 251:251 */      pos = pos + 1 & this.mask;
/* 252:    */    }
/* 253:253 */    return this.defRetValue;
/* 254:    */  }
/* 255:    */  
/* 256:    */  public boolean containsKey(Object k)
/* 257:    */  {
/* 258:258 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 259:    */    
/* 260:260 */    while (this.used[pos] != 0) {
/* 261:261 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 262:262 */      pos = pos + 1 & this.mask;
/* 263:    */    }
/* 264:264 */    return false;
/* 265:    */  }
/* 266:    */  
/* 267:267 */  public boolean containsValue(Object v) { V[] value = this.value;
/* 268:268 */    boolean[] used = this.used;
/* 269:269 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
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
/* 311:    */    implements Object2ReferenceMap.Entry<K, V>, Map.Entry<K, V>
/* 312:    */  {
/* 313:    */    private int index;
/* 314:    */    
/* 315:    */    MapEntry(int index)
/* 316:    */    {
/* 317:317 */      this.index = index;
/* 318:    */    }
/* 319:    */    
/* 320:320 */    public K getKey() { return Object2ReferenceOpenHashMap.this.key[this.index]; }
/* 321:    */    
/* 323:323 */    public V getValue() { return Object2ReferenceOpenHashMap.this.value[this.index]; }
/* 324:    */    
/* 325:    */    public V setValue(V v) {
/* 326:326 */      V oldValue = Object2ReferenceOpenHashMap.this.value[this.index];
/* 327:327 */      Object2ReferenceOpenHashMap.this.value[this.index] = v;
/* 328:328 */      return oldValue;
/* 329:    */    }
/* 330:    */    
/* 331:    */    public boolean equals(Object o) {
/* 332:332 */      if (!(o instanceof Map.Entry)) return false;
/* 333:333 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 334:334 */      return (Object2ReferenceOpenHashMap.this.key[this.index] == null ? e.getKey() == null : Object2ReferenceOpenHashMap.this.key[this.index].equals(e.getKey())) && (Object2ReferenceOpenHashMap.this.value[this.index] == e.getValue());
/* 335:    */    }
/* 336:    */    
/* 337:337 */    public int hashCode() { return (Object2ReferenceOpenHashMap.this.key[this.index] == null ? 0 : Object2ReferenceOpenHashMap.this.key[this.index].hashCode()) ^ (Object2ReferenceOpenHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Object2ReferenceOpenHashMap.this.value[this.index])); }
/* 338:    */    
/* 340:340 */    public String toString() { return Object2ReferenceOpenHashMap.this.key[this.index] + "=>" + Object2ReferenceOpenHashMap.this.value[this.index]; } }
/* 341:    */  
/* 342:    */  private class MapIterator { int pos;
/* 343:    */    int last;
/* 344:    */    int c;
/* 345:    */    ObjectArrayList<K> wrapped;
/* 346:    */    
/* 347:347 */    private MapIterator() { this.pos = Object2ReferenceOpenHashMap.this.n;
/* 348:    */      
/* 350:350 */      this.last = -1;
/* 351:    */      
/* 352:352 */      this.c = Object2ReferenceOpenHashMap.this.size;
/* 353:    */      
/* 357:357 */      boolean[] used = Object2ReferenceOpenHashMap.this.used;
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
/* 370:370 */        int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ReferenceOpenHashMap.this.mask;
/* 371:    */        
/* 372:372 */        while (Object2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 373:373 */          if (Object2ReferenceOpenHashMap.this.key[pos] == null ? k == null : Object2ReferenceOpenHashMap.this.key[pos].equals(k)) return pos;
/* 374:374 */          pos = pos + 1 & Object2ReferenceOpenHashMap.this.mask;
/* 375:    */        }
/* 376:    */      }
/* 377:377 */      this.last = this.pos;
/* 378:    */      
/* 379:379 */      if (this.c != 0) {
/* 380:380 */        boolean[] used = Object2ReferenceOpenHashMap.this.used;
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
/* 397:397 */        pos = (last = pos) + 1 & Object2ReferenceOpenHashMap.this.mask;
/* 398:398 */        while (Object2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 399:399 */          int slot = (Object2ReferenceOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2ReferenceOpenHashMap.this.key[pos].hashCode())) & Object2ReferenceOpenHashMap.this.mask;
/* 400:400 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 401:401 */          pos = pos + 1 & Object2ReferenceOpenHashMap.this.mask;
/* 402:    */        }
/* 403:403 */        if (Object2ReferenceOpenHashMap.this.used[pos] == 0) break;
/* 404:404 */        if (pos < last)
/* 405:    */        {
/* 406:406 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 407:407 */          this.wrapped.add(Object2ReferenceOpenHashMap.this.key[pos]);
/* 408:    */        }
/* 409:409 */        Object2ReferenceOpenHashMap.this.key[last] = Object2ReferenceOpenHashMap.this.key[pos];
/* 410:410 */        Object2ReferenceOpenHashMap.this.value[last] = Object2ReferenceOpenHashMap.this.value[pos];
/* 411:    */      }
/* 412:412 */      Object2ReferenceOpenHashMap.this.used[last] = false;
/* 413:413 */      Object2ReferenceOpenHashMap.this.key[last] = null;
/* 414:414 */      Object2ReferenceOpenHashMap.this.value[last] = null;
/* 415:415 */      return last;
/* 416:    */    }
/* 417:    */    
/* 418:    */    public void remove() {
/* 419:419 */      if (this.last == -1) throw new IllegalStateException();
/* 420:420 */      if (this.pos < -1)
/* 421:    */      {
/* 422:422 */        Object2ReferenceOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 423:423 */        this.last = -1;
/* 424:424 */        return;
/* 425:    */      }
/* 426:426 */      Object2ReferenceOpenHashMap.this.size -= 1;
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
/* 440:440 */  private class EntryIterator extends Object2ReferenceOpenHashMap<K, V>.MapIterator implements ObjectIterator<Object2ReferenceMap.Entry<K, V>> { private EntryIterator() { super(null); }
/* 441:    */    
/* 442:    */    private Object2ReferenceOpenHashMap<K, V>.MapEntry entry;
/* 443:443 */    public Object2ReferenceMap.Entry<K, V> next() { return this.entry = new Object2ReferenceOpenHashMap.MapEntry(Object2ReferenceOpenHashMap.this, nextEntry()); }
/* 444:    */    
/* 445:    */    public void remove()
/* 446:    */    {
/* 447:447 */      super.remove();
/* 448:448 */      Object2ReferenceOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 449:    */    } }
/* 450:    */  
/* 451:451 */  private class FastEntryIterator extends Object2ReferenceOpenHashMap<K, V>.MapIterator implements ObjectIterator<Object2ReferenceMap.Entry<K, V>> { private FastEntryIterator() { super(null); }
/* 452:452 */    final AbstractObject2ReferenceMap.BasicEntry<K, V> entry = new AbstractObject2ReferenceMap.BasicEntry(null, null);
/* 453:    */    
/* 454:454 */    public AbstractObject2ReferenceMap.BasicEntry<K, V> next() { int e = nextEntry();
/* 455:455 */      this.entry.key = Object2ReferenceOpenHashMap.this.key[e];
/* 456:456 */      this.entry.value = Object2ReferenceOpenHashMap.this.value[e];
/* 457:457 */      return this.entry;
/* 458:    */    } }
/* 459:    */  
/* 460:    */  private final class MapEntrySet extends AbstractObjectSet<Object2ReferenceMap.Entry<K, V>> implements Object2ReferenceMap.FastEntrySet<K, V> { private MapEntrySet() {}
/* 461:    */    
/* 462:462 */    public ObjectIterator<Object2ReferenceMap.Entry<K, V>> iterator() { return new Object2ReferenceOpenHashMap.EntryIterator(Object2ReferenceOpenHashMap.this, null); }
/* 463:    */    
/* 464:    */    public ObjectIterator<Object2ReferenceMap.Entry<K, V>> fastIterator() {
/* 465:465 */      return new Object2ReferenceOpenHashMap.FastEntryIterator(Object2ReferenceOpenHashMap.this, null);
/* 466:    */    }
/* 467:    */    
/* 468:    */    public boolean contains(Object o) {
/* 469:469 */      if (!(o instanceof Map.Entry)) return false;
/* 470:470 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 471:471 */      K k = e.getKey();
/* 472:    */      
/* 473:473 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ReferenceOpenHashMap.this.mask;
/* 474:    */      
/* 475:475 */      while (Object2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 476:476 */        if (Object2ReferenceOpenHashMap.this.key[pos] == null ? k == null : Object2ReferenceOpenHashMap.this.key[pos].equals(k)) return Object2ReferenceOpenHashMap.this.value[pos] == e.getValue();
/* 477:477 */        pos = pos + 1 & Object2ReferenceOpenHashMap.this.mask;
/* 478:    */      }
/* 479:479 */      return false;
/* 480:    */    }
/* 481:    */    
/* 482:    */    public boolean remove(Object o) {
/* 483:483 */      if (!(o instanceof Map.Entry)) return false;
/* 484:484 */      Map.Entry<K, V> e = (Map.Entry)o;
/* 485:485 */      K k = e.getKey();
/* 486:    */      
/* 487:487 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ReferenceOpenHashMap.this.mask;
/* 488:    */      
/* 489:489 */      while (Object2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 490:490 */        if (Object2ReferenceOpenHashMap.this.key[pos] == null ? k == null : Object2ReferenceOpenHashMap.this.key[pos].equals(k)) {
/* 491:491 */          Object2ReferenceOpenHashMap.this.remove(e.getKey());
/* 492:492 */          return true;
/* 493:    */        }
/* 494:494 */        pos = pos + 1 & Object2ReferenceOpenHashMap.this.mask;
/* 495:    */      }
/* 496:496 */      return false;
/* 497:    */    }
/* 498:    */    
/* 499:499 */    public int size() { return Object2ReferenceOpenHashMap.this.size; }
/* 500:    */    
/* 502:502 */    public void clear() { Object2ReferenceOpenHashMap.this.clear(); }
/* 503:    */  }
/* 504:    */  
/* 505:    */  public Object2ReferenceMap.FastEntrySet<K, V> object2ReferenceEntrySet() {
/* 506:506 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 507:507 */    return this.entries;
/* 508:    */  }
/* 509:    */  
/* 512:    */  private final class KeyIterator
/* 513:    */    extends Object2ReferenceOpenHashMap<K, V>.MapIterator
/* 514:    */    implements ObjectIterator<K>
/* 515:    */  {
/* 516:516 */    public KeyIterator() { super(null); }
/* 517:517 */    public K next() { return Object2ReferenceOpenHashMap.this.key[nextEntry()]; } }
/* 518:    */  
/* 519:    */  private final class KeySet extends AbstractObjectSet<K> { private KeySet() {}
/* 520:    */    
/* 521:521 */    public ObjectIterator<K> iterator() { return new Object2ReferenceOpenHashMap.KeyIterator(Object2ReferenceOpenHashMap.this); }
/* 522:    */    
/* 523:    */    public int size() {
/* 524:524 */      return Object2ReferenceOpenHashMap.this.size;
/* 525:    */    }
/* 526:    */    
/* 527:527 */    public boolean contains(Object k) { return Object2ReferenceOpenHashMap.this.containsKey(k); }
/* 528:    */    
/* 529:    */    public boolean remove(Object k) {
/* 530:530 */      int oldSize = Object2ReferenceOpenHashMap.this.size;
/* 531:531 */      Object2ReferenceOpenHashMap.this.remove(k);
/* 532:532 */      return Object2ReferenceOpenHashMap.this.size != oldSize;
/* 533:    */    }
/* 534:    */    
/* 535:535 */    public void clear() { Object2ReferenceOpenHashMap.this.clear(); }
/* 536:    */  }
/* 537:    */  
/* 538:    */  public ObjectSet<K> keySet() {
/* 539:539 */    if (this.keys == null) this.keys = new KeySet(null);
/* 540:540 */    return this.keys;
/* 541:    */  }
/* 542:    */  
/* 545:    */  private final class ValueIterator
/* 546:    */    extends Object2ReferenceOpenHashMap<K, V>.MapIterator
/* 547:    */    implements ObjectIterator<V>
/* 548:    */  {
/* 549:549 */    public ValueIterator() { super(null); }
/* 550:550 */    public V next() { return Object2ReferenceOpenHashMap.this.value[nextEntry()]; }
/* 551:    */  }
/* 552:    */  
/* 553:553 */  public ReferenceCollection<V> values() { if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 554:    */        public ObjectIterator<V> iterator() {
/* 555:555 */          return new Object2ReferenceOpenHashMap.ValueIterator(Object2ReferenceOpenHashMap.this);
/* 556:    */        }
/* 557:    */        
/* 558:558 */        public int size() { return Object2ReferenceOpenHashMap.this.size; }
/* 559:    */        
/* 560:    */        public boolean contains(Object v) {
/* 561:561 */          return Object2ReferenceOpenHashMap.this.containsValue(v);
/* 562:    */        }
/* 563:    */        
/* 564:564 */        public void clear() { Object2ReferenceOpenHashMap.this.clear(); }
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
/* 654:654 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
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
/* 672:    */  public Object2ReferenceOpenHashMap<K, V> clone()
/* 673:    */  {
/* 674:    */    Object2ReferenceOpenHashMap<K, V> c;
/* 675:    */    
/* 677:    */    try
/* 678:    */    {
/* 679:679 */      c = (Object2ReferenceOpenHashMap)super.clone();
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
/* 706:706 */        t = this.key[i] == null ? 0 : this.key[i].hashCode();
/* 707:707 */      if (this != this.value[i])
/* 708:708 */        t ^= (this.value[i] == null ? 0 : System.identityHashCode(this.value[i]));
/* 709:709 */      h += t;
/* 710:710 */      i++;
/* 711:    */    }
/* 712:712 */    return h;
/* 713:    */  }
/* 714:    */  
/* 715:715 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 716:716 */    V[] value = this.value;
/* 717:717 */    Object2ReferenceOpenHashMap<K, V>.MapIterator i = new MapIterator(null);
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
/* 739:739 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */