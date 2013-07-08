/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*   7:    */import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*   8:    */import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*   9:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*  10:    */import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*  11:    */import java.io.IOException;
/*  12:    */import java.io.ObjectInputStream;
/*  13:    */import java.io.ObjectOutputStream;
/*  14:    */import java.io.Serializable;
/*  15:    */import java.util.Map;
/*  16:    */import java.util.Map.Entry;
/*  17:    */import java.util.NoSuchElementException;
/*  18:    */
/*  86:    */public class Short2ReferenceOpenHashMap<V>
/*  87:    */  extends AbstractShort2ReferenceMap<V>
/*  88:    */  implements Serializable, Cloneable, Hash
/*  89:    */{
/*  90:    */  public static final long serialVersionUID = 0L;
/*  91:    */  private static final boolean ASSERTS = false;
/*  92:    */  protected transient short[] key;
/*  93:    */  protected transient V[] value;
/*  94:    */  protected transient boolean[] used;
/*  95:    */  protected final float f;
/*  96:    */  protected transient int n;
/*  97:    */  protected transient int maxFill;
/*  98:    */  protected transient int mask;
/*  99:    */  protected int size;
/* 100:    */  protected volatile transient Short2ReferenceMap.FastEntrySet<V> entries;
/* 101:    */  protected volatile transient ShortSet keys;
/* 102:    */  protected volatile transient ReferenceCollection<V> values;
/* 103:    */  
/* 104:    */  public Short2ReferenceOpenHashMap(int expected, float f)
/* 105:    */  {
/* 106:106 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 107:107 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 108:108 */    this.f = f;
/* 109:109 */    this.n = HashCommon.arraySize(expected, f);
/* 110:110 */    this.mask = (this.n - 1);
/* 111:111 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 112:112 */    this.key = new short[this.n];
/* 113:113 */    this.value = ((Object[])new Object[this.n]);
/* 114:114 */    this.used = new boolean[this.n];
/* 115:    */  }
/* 116:    */  
/* 119:    */  public Short2ReferenceOpenHashMap(int expected)
/* 120:    */  {
/* 121:121 */    this(expected, 0.75F);
/* 122:    */  }
/* 123:    */  
/* 125:    */  public Short2ReferenceOpenHashMap()
/* 126:    */  {
/* 127:127 */    this(16, 0.75F);
/* 128:    */  }
/* 129:    */  
/* 133:    */  public Short2ReferenceOpenHashMap(Map<? extends Short, ? extends V> m, float f)
/* 134:    */  {
/* 135:135 */    this(m.size(), f);
/* 136:136 */    putAll(m);
/* 137:    */  }
/* 138:    */  
/* 141:    */  public Short2ReferenceOpenHashMap(Map<? extends Short, ? extends V> m)
/* 142:    */  {
/* 143:143 */    this(m, 0.75F);
/* 144:    */  }
/* 145:    */  
/* 149:    */  public Short2ReferenceOpenHashMap(Short2ReferenceMap<V> m, float f)
/* 150:    */  {
/* 151:151 */    this(m.size(), f);
/* 152:152 */    putAll(m);
/* 153:    */  }
/* 154:    */  
/* 157:    */  public Short2ReferenceOpenHashMap(Short2ReferenceMap<V> m)
/* 158:    */  {
/* 159:159 */    this(m, 0.75F);
/* 160:    */  }
/* 161:    */  
/* 167:    */  public Short2ReferenceOpenHashMap(short[] k, V[] v, float f)
/* 168:    */  {
/* 169:169 */    this(k.length, f);
/* 170:170 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 171:171 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 178:    */  public Short2ReferenceOpenHashMap(short[] k, V[] v)
/* 179:    */  {
/* 180:180 */    this(k, v, 0.75F);
/* 181:    */  }
/* 182:    */  
/* 186:    */  public V put(short k, V v)
/* 187:    */  {
/* 188:188 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 189:    */    
/* 190:190 */    while (this.used[pos] != 0) {
/* 191:191 */      if (this.key[pos] == k) {
/* 192:192 */        V oldValue = this.value[pos];
/* 193:193 */        this.value[pos] = v;
/* 194:194 */        return oldValue;
/* 195:    */      }
/* 196:196 */      pos = pos + 1 & this.mask;
/* 197:    */    }
/* 198:198 */    this.used[pos] = true;
/* 199:199 */    this.key[pos] = k;
/* 200:200 */    this.value[pos] = v;
/* 201:201 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 202:    */    }
/* 203:203 */    return this.defRetValue;
/* 204:    */  }
/* 205:    */  
/* 206:206 */  public V put(Short ok, V ov) { V v = ov;
/* 207:207 */    short k = ok.shortValue();
/* 208:    */    
/* 209:209 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 210:    */    
/* 211:211 */    while (this.used[pos] != 0) {
/* 212:212 */      if (this.key[pos] == k) {
/* 213:213 */        V oldValue = this.value[pos];
/* 214:214 */        this.value[pos] = v;
/* 215:215 */        return oldValue;
/* 216:    */      }
/* 217:217 */      pos = pos + 1 & this.mask;
/* 218:    */    }
/* 219:219 */    this.used[pos] = true;
/* 220:220 */    this.key[pos] = k;
/* 221:221 */    this.value[pos] = v;
/* 222:222 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 223:    */    }
/* 224:224 */    return this.defRetValue;
/* 225:    */  }
/* 226:    */  
/* 229:    */  protected final int shiftKeys(int pos)
/* 230:    */  {
/* 231:    */    int last;
/* 232:    */    
/* 234:    */    for (;;)
/* 235:    */    {
/* 236:236 */      pos = (last = pos) + 1 & this.mask;
/* 237:237 */      while (this.used[pos] != 0) {
/* 238:238 */        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 239:239 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 240:240 */        pos = pos + 1 & this.mask;
/* 241:    */      }
/* 242:242 */      if (this.used[pos] == 0) break;
/* 243:243 */      this.key[last] = this.key[pos];
/* 244:244 */      this.value[last] = this.value[pos];
/* 245:    */    }
/* 246:246 */    this.used[last] = false;
/* 247:247 */    this.value[last] = null;
/* 248:248 */    return last;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public V remove(short k)
/* 252:    */  {
/* 253:253 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 254:    */    
/* 255:255 */    while (this.used[pos] != 0) {
/* 256:256 */      if (this.key[pos] == k) {
/* 257:257 */        this.size -= 1;
/* 258:258 */        V v = this.value[pos];
/* 259:259 */        shiftKeys(pos);
/* 260:260 */        return v;
/* 261:    */      }
/* 262:262 */      pos = pos + 1 & this.mask;
/* 263:    */    }
/* 264:264 */    return this.defRetValue;
/* 265:    */  }
/* 266:    */  
/* 267:    */  public V remove(Object ok) {
/* 268:268 */    short k = ((Short)ok).shortValue();
/* 269:    */    
/* 270:270 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 271:    */    
/* 272:272 */    while (this.used[pos] != 0) {
/* 273:273 */      if (this.key[pos] == k) {
/* 274:274 */        this.size -= 1;
/* 275:275 */        V v = this.value[pos];
/* 276:276 */        shiftKeys(pos);
/* 277:277 */        return v;
/* 278:    */      }
/* 279:279 */      pos = pos + 1 & this.mask;
/* 280:    */    }
/* 281:281 */    return this.defRetValue;
/* 282:    */  }
/* 283:    */  
/* 284:284 */  public V get(Short ok) { short k = ok.shortValue();
/* 285:    */    
/* 286:286 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 287:    */    
/* 288:288 */    while (this.used[pos] != 0) {
/* 289:289 */      if (this.key[pos] == k) return this.value[pos];
/* 290:290 */      pos = pos + 1 & this.mask;
/* 291:    */    }
/* 292:292 */    return this.defRetValue;
/* 293:    */  }
/* 294:    */  
/* 295:    */  public V get(short k)
/* 296:    */  {
/* 297:297 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 298:    */    
/* 299:299 */    while (this.used[pos] != 0) {
/* 300:300 */      if (this.key[pos] == k) return this.value[pos];
/* 301:301 */      pos = pos + 1 & this.mask;
/* 302:    */    }
/* 303:303 */    return this.defRetValue;
/* 304:    */  }
/* 305:    */  
/* 306:    */  public boolean containsKey(short k)
/* 307:    */  {
/* 308:308 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 309:    */    
/* 310:310 */    while (this.used[pos] != 0) {
/* 311:311 */      if (this.key[pos] == k) return true;
/* 312:312 */      pos = pos + 1 & this.mask;
/* 313:    */    }
/* 314:314 */    return false;
/* 315:    */  }
/* 316:    */  
/* 317:317 */  public boolean containsValue(Object v) { V[] value = this.value;
/* 318:318 */    boolean[] used = this.used;
/* 319:319 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/* 320:320 */    return false;
/* 321:    */  }
/* 322:    */  
/* 327:    */  public void clear()
/* 328:    */  {
/* 329:329 */    if (this.size == 0) return;
/* 330:330 */    this.size = 0;
/* 331:331 */    BooleanArrays.fill(this.used, false);
/* 332:    */    
/* 333:333 */    ObjectArrays.fill(this.value, null);
/* 334:    */  }
/* 335:    */  
/* 336:336 */  public int size() { return this.size; }
/* 337:    */  
/* 338:    */  public boolean isEmpty() {
/* 339:339 */    return this.size == 0;
/* 340:    */  }
/* 341:    */  
/* 346:    */  @Deprecated
/* 347:    */  public void growthFactor(int growthFactor) {}
/* 348:    */  
/* 353:    */  @Deprecated
/* 354:    */  public int growthFactor()
/* 355:    */  {
/* 356:356 */    return 16;
/* 357:    */  }
/* 358:    */  
/* 359:    */  private final class MapEntry
/* 360:    */    implements Short2ReferenceMap.Entry<V>, Map.Entry<Short, V>
/* 361:    */  {
/* 362:    */    private int index;
/* 363:    */    
/* 364:    */    MapEntry(int index)
/* 365:    */    {
/* 366:366 */      this.index = index;
/* 367:    */    }
/* 368:    */    
/* 369:369 */    public Short getKey() { return Short.valueOf(Short2ReferenceOpenHashMap.this.key[this.index]); }
/* 370:    */    
/* 371:    */    public short getShortKey() {
/* 372:372 */      return Short2ReferenceOpenHashMap.this.key[this.index];
/* 373:    */    }
/* 374:    */    
/* 375:375 */    public V getValue() { return Short2ReferenceOpenHashMap.this.value[this.index]; }
/* 376:    */    
/* 377:    */    public V setValue(V v) {
/* 378:378 */      V oldValue = Short2ReferenceOpenHashMap.this.value[this.index];
/* 379:379 */      Short2ReferenceOpenHashMap.this.value[this.index] = v;
/* 380:380 */      return oldValue;
/* 381:    */    }
/* 382:    */    
/* 383:    */    public boolean equals(Object o) {
/* 384:384 */      if (!(o instanceof Map.Entry)) return false;
/* 385:385 */      Map.Entry<Short, V> e = (Map.Entry)o;
/* 386:386 */      return (Short2ReferenceOpenHashMap.this.key[this.index] == ((Short)e.getKey()).shortValue()) && (Short2ReferenceOpenHashMap.this.value[this.index] == e.getValue());
/* 387:    */    }
/* 388:    */    
/* 389:389 */    public int hashCode() { return Short2ReferenceOpenHashMap.this.key[this.index] ^ (Short2ReferenceOpenHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Short2ReferenceOpenHashMap.this.value[this.index])); }
/* 390:    */    
/* 392:392 */    public String toString() { return Short2ReferenceOpenHashMap.this.key[this.index] + "=>" + Short2ReferenceOpenHashMap.this.value[this.index]; } }
/* 393:    */  
/* 394:    */  private class MapIterator { int pos;
/* 395:    */    int last;
/* 396:    */    int c;
/* 397:    */    ShortArrayList wrapped;
/* 398:    */    
/* 399:399 */    private MapIterator() { this.pos = Short2ReferenceOpenHashMap.this.n;
/* 400:    */      
/* 402:402 */      this.last = -1;
/* 403:    */      
/* 404:404 */      this.c = Short2ReferenceOpenHashMap.this.size;
/* 405:    */      
/* 409:409 */      boolean[] used = Short2ReferenceOpenHashMap.this.used;
/* 410:410 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 411:    */    }
/* 412:    */    
/* 413:413 */    public boolean hasNext() { return this.c != 0; }
/* 414:    */    
/* 415:    */    public int nextEntry() {
/* 416:416 */      if (!hasNext()) throw new NoSuchElementException();
/* 417:417 */      this.c -= 1;
/* 418:    */      
/* 419:419 */      if (this.pos < 0) {
/* 420:420 */        short k = this.wrapped.getShort(-(this.last = --this.pos) - 2);
/* 421:    */        
/* 422:422 */        int pos = HashCommon.murmurHash3(k) & Short2ReferenceOpenHashMap.this.mask;
/* 423:    */        
/* 424:424 */        while (Short2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 425:425 */          if (Short2ReferenceOpenHashMap.this.key[pos] == k) return pos;
/* 426:426 */          pos = pos + 1 & Short2ReferenceOpenHashMap.this.mask;
/* 427:    */        }
/* 428:    */      }
/* 429:429 */      this.last = this.pos;
/* 430:    */      
/* 431:431 */      if (this.c != 0) {
/* 432:432 */        boolean[] used = Short2ReferenceOpenHashMap.this.used;
/* 433:433 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 434:    */      }
/* 435:    */      
/* 436:436 */      return this.last;
/* 437:    */    }
/* 438:    */    
/* 442:    */    protected final int shiftKeys(int pos)
/* 443:    */    {
/* 444:    */      int last;
/* 445:    */      
/* 447:    */      for (;;)
/* 448:    */      {
/* 449:449 */        pos = (last = pos) + 1 & Short2ReferenceOpenHashMap.this.mask;
/* 450:450 */        while (Short2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 451:451 */          int slot = HashCommon.murmurHash3(Short2ReferenceOpenHashMap.this.key[pos]) & Short2ReferenceOpenHashMap.this.mask;
/* 452:452 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 453:453 */          pos = pos + 1 & Short2ReferenceOpenHashMap.this.mask;
/* 454:    */        }
/* 455:455 */        if (Short2ReferenceOpenHashMap.this.used[pos] == 0) break;
/* 456:456 */        if (pos < last)
/* 457:    */        {
/* 458:458 */          if (this.wrapped == null) this.wrapped = new ShortArrayList();
/* 459:459 */          this.wrapped.add(Short2ReferenceOpenHashMap.this.key[pos]);
/* 460:    */        }
/* 461:461 */        Short2ReferenceOpenHashMap.this.key[last] = Short2ReferenceOpenHashMap.this.key[pos];
/* 462:462 */        Short2ReferenceOpenHashMap.this.value[last] = Short2ReferenceOpenHashMap.this.value[pos];
/* 463:    */      }
/* 464:464 */      Short2ReferenceOpenHashMap.this.used[last] = false;
/* 465:465 */      Short2ReferenceOpenHashMap.this.value[last] = null;
/* 466:466 */      return last;
/* 467:    */    }
/* 468:    */    
/* 469:    */    public void remove() {
/* 470:470 */      if (this.last == -1) throw new IllegalStateException();
/* 471:471 */      if (this.pos < -1)
/* 472:    */      {
/* 473:473 */        Short2ReferenceOpenHashMap.this.remove(this.wrapped.getShort(-this.pos - 2));
/* 474:474 */        this.last = -1;
/* 475:475 */        return;
/* 476:    */      }
/* 477:477 */      Short2ReferenceOpenHashMap.this.size -= 1;
/* 478:478 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 479:479 */        this.c += 1;
/* 480:480 */        nextEntry();
/* 481:    */      }
/* 482:482 */      this.last = -1;
/* 483:    */    }
/* 484:    */    
/* 485:    */    public int skip(int n) {
/* 486:486 */      int i = n;
/* 487:487 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 488:488 */      return n - i - 1;
/* 489:    */    } }
/* 490:    */  
/* 491:491 */  private class EntryIterator extends Short2ReferenceOpenHashMap<V>.MapIterator implements ObjectIterator<Short2ReferenceMap.Entry<V>> { private EntryIterator() { super(null); }
/* 492:    */    
/* 493:    */    private Short2ReferenceOpenHashMap<V>.MapEntry entry;
/* 494:494 */    public Short2ReferenceMap.Entry<V> next() { return this.entry = new Short2ReferenceOpenHashMap.MapEntry(Short2ReferenceOpenHashMap.this, nextEntry()); }
/* 495:    */    
/* 496:    */    public void remove()
/* 497:    */    {
/* 498:498 */      super.remove();
/* 499:499 */      Short2ReferenceOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 500:    */    } }
/* 501:    */  
/* 502:502 */  private class FastEntryIterator extends Short2ReferenceOpenHashMap<V>.MapIterator implements ObjectIterator<Short2ReferenceMap.Entry<V>> { private FastEntryIterator() { super(null); }
/* 503:503 */    final AbstractShort2ReferenceMap.BasicEntry<V> entry = new AbstractShort2ReferenceMap.BasicEntry((short)0, null);
/* 504:    */    
/* 505:505 */    public AbstractShort2ReferenceMap.BasicEntry<V> next() { int e = nextEntry();
/* 506:506 */      this.entry.key = Short2ReferenceOpenHashMap.this.key[e];
/* 507:507 */      this.entry.value = Short2ReferenceOpenHashMap.this.value[e];
/* 508:508 */      return this.entry;
/* 509:    */    } }
/* 510:    */  
/* 511:    */  private final class MapEntrySet extends AbstractObjectSet<Short2ReferenceMap.Entry<V>> implements Short2ReferenceMap.FastEntrySet<V> { private MapEntrySet() {}
/* 512:    */    
/* 513:513 */    public ObjectIterator<Short2ReferenceMap.Entry<V>> iterator() { return new Short2ReferenceOpenHashMap.EntryIterator(Short2ReferenceOpenHashMap.this, null); }
/* 514:    */    
/* 515:    */    public ObjectIterator<Short2ReferenceMap.Entry<V>> fastIterator() {
/* 516:516 */      return new Short2ReferenceOpenHashMap.FastEntryIterator(Short2ReferenceOpenHashMap.this, null);
/* 517:    */    }
/* 518:    */    
/* 519:    */    public boolean contains(Object o) {
/* 520:520 */      if (!(o instanceof Map.Entry)) return false;
/* 521:521 */      Map.Entry<Short, V> e = (Map.Entry)o;
/* 522:522 */      short k = ((Short)e.getKey()).shortValue();
/* 523:    */      
/* 524:524 */      int pos = HashCommon.murmurHash3(k) & Short2ReferenceOpenHashMap.this.mask;
/* 525:    */      
/* 526:526 */      while (Short2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 527:527 */        if (Short2ReferenceOpenHashMap.this.key[pos] == k) return Short2ReferenceOpenHashMap.this.value[pos] == e.getValue();
/* 528:528 */        pos = pos + 1 & Short2ReferenceOpenHashMap.this.mask;
/* 529:    */      }
/* 530:530 */      return false;
/* 531:    */    }
/* 532:    */    
/* 533:    */    public boolean remove(Object o) {
/* 534:534 */      if (!(o instanceof Map.Entry)) return false;
/* 535:535 */      Map.Entry<Short, V> e = (Map.Entry)o;
/* 536:536 */      short k = ((Short)e.getKey()).shortValue();
/* 537:    */      
/* 538:538 */      int pos = HashCommon.murmurHash3(k) & Short2ReferenceOpenHashMap.this.mask;
/* 539:    */      
/* 540:540 */      while (Short2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 541:541 */        if (Short2ReferenceOpenHashMap.this.key[pos] == k) {
/* 542:542 */          Short2ReferenceOpenHashMap.this.remove(e.getKey());
/* 543:543 */          return true;
/* 544:    */        }
/* 545:545 */        pos = pos + 1 & Short2ReferenceOpenHashMap.this.mask;
/* 546:    */      }
/* 547:547 */      return false;
/* 548:    */    }
/* 549:    */    
/* 550:550 */    public int size() { return Short2ReferenceOpenHashMap.this.size; }
/* 551:    */    
/* 553:553 */    public void clear() { Short2ReferenceOpenHashMap.this.clear(); }
/* 554:    */  }
/* 555:    */  
/* 556:    */  public Short2ReferenceMap.FastEntrySet<V> short2ReferenceEntrySet() {
/* 557:557 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 558:558 */    return this.entries;
/* 559:    */  }
/* 560:    */  
/* 563:    */  private final class KeyIterator
/* 564:    */    extends Short2ReferenceOpenHashMap.MapIterator
/* 565:    */    implements ShortIterator
/* 566:    */  {
/* 567:567 */    public KeyIterator() { super(null); }
/* 568:568 */    public short nextShort() { return Short2ReferenceOpenHashMap.this.key[nextEntry()]; }
/* 569:569 */    public Short next() { return Short.valueOf(Short2ReferenceOpenHashMap.this.key[nextEntry()]); } }
/* 570:    */  
/* 571:    */  private final class KeySet extends AbstractShortSet { private KeySet() {}
/* 572:    */    
/* 573:573 */    public ShortIterator iterator() { return new Short2ReferenceOpenHashMap.KeyIterator(Short2ReferenceOpenHashMap.this); }
/* 574:    */    
/* 575:    */    public int size() {
/* 576:576 */      return Short2ReferenceOpenHashMap.this.size;
/* 577:    */    }
/* 578:    */    
/* 579:579 */    public boolean contains(short k) { return Short2ReferenceOpenHashMap.this.containsKey(k); }
/* 580:    */    
/* 581:    */    public boolean remove(short k) {
/* 582:582 */      int oldSize = Short2ReferenceOpenHashMap.this.size;
/* 583:583 */      Short2ReferenceOpenHashMap.this.remove(k);
/* 584:584 */      return Short2ReferenceOpenHashMap.this.size != oldSize;
/* 585:    */    }
/* 586:    */    
/* 587:587 */    public void clear() { Short2ReferenceOpenHashMap.this.clear(); }
/* 588:    */  }
/* 589:    */  
/* 590:    */  public ShortSet keySet() {
/* 591:591 */    if (this.keys == null) this.keys = new KeySet(null);
/* 592:592 */    return this.keys;
/* 593:    */  }
/* 594:    */  
/* 597:    */  private final class ValueIterator
/* 598:    */    extends Short2ReferenceOpenHashMap<V>.MapIterator
/* 599:    */    implements ObjectIterator<V>
/* 600:    */  {
/* 601:601 */    public ValueIterator() { super(null); }
/* 602:602 */    public V next() { return Short2ReferenceOpenHashMap.this.value[nextEntry()]; }
/* 603:    */  }
/* 604:    */  
/* 605:605 */  public ReferenceCollection<V> values() { if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 606:    */        public ObjectIterator<V> iterator() {
/* 607:607 */          return new Short2ReferenceOpenHashMap.ValueIterator(Short2ReferenceOpenHashMap.this);
/* 608:    */        }
/* 609:    */        
/* 610:610 */        public int size() { return Short2ReferenceOpenHashMap.this.size; }
/* 611:    */        
/* 612:    */        public boolean contains(Object v) {
/* 613:613 */          return Short2ReferenceOpenHashMap.this.containsValue(v);
/* 614:    */        }
/* 615:    */        
/* 616:616 */        public void clear() { Short2ReferenceOpenHashMap.this.clear(); }
/* 617:    */      };
/* 618:    */    }
/* 619:619 */    return this.values;
/* 620:    */  }
/* 621:    */  
/* 630:    */  @Deprecated
/* 631:    */  public boolean rehash()
/* 632:    */  {
/* 633:633 */    return true;
/* 634:    */  }
/* 635:    */  
/* 646:    */  public boolean trim()
/* 647:    */  {
/* 648:648 */    int l = HashCommon.arraySize(this.size, this.f);
/* 649:649 */    if (l >= this.n) return true;
/* 650:    */    try {
/* 651:651 */      rehash(l);
/* 652:    */    } catch (OutOfMemoryError cantDoIt) {
/* 653:653 */      return false; }
/* 654:654 */    return true;
/* 655:    */  }
/* 656:    */  
/* 673:    */  public boolean trim(int n)
/* 674:    */  {
/* 675:675 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 676:676 */    if (this.n <= l) return true;
/* 677:    */    try {
/* 678:678 */      rehash(l);
/* 679:    */    } catch (OutOfMemoryError cantDoIt) {
/* 680:680 */      return false; }
/* 681:681 */    return true;
/* 682:    */  }
/* 683:    */  
/* 692:    */  protected void rehash(int newN)
/* 693:    */  {
/* 694:694 */    int i = 0;
/* 695:695 */    boolean[] used = this.used;
/* 696:    */    
/* 697:697 */    short[] key = this.key;
/* 698:698 */    V[] value = this.value;
/* 699:699 */    int newMask = newN - 1;
/* 700:700 */    short[] newKey = new short[newN];
/* 701:701 */    V[] newValue = (Object[])new Object[newN];
/* 702:702 */    boolean[] newUsed = new boolean[newN];
/* 703:703 */    for (int j = this.size; j-- != 0;) {
/* 704:704 */      while (used[i] == 0) i++;
/* 705:705 */      short k = key[i];
/* 706:706 */      int pos = HashCommon.murmurHash3(k) & newMask;
/* 707:707 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 708:708 */      newUsed[pos] = true;
/* 709:709 */      newKey[pos] = k;
/* 710:710 */      newValue[pos] = value[i];
/* 711:711 */      i++;
/* 712:    */    }
/* 713:713 */    this.n = newN;
/* 714:714 */    this.mask = newMask;
/* 715:715 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 716:716 */    this.key = newKey;
/* 717:717 */    this.value = newValue;
/* 718:718 */    this.used = newUsed;
/* 719:    */  }
/* 720:    */  
/* 724:    */  public Short2ReferenceOpenHashMap<V> clone()
/* 725:    */  {
/* 726:    */    Short2ReferenceOpenHashMap<V> c;
/* 727:    */    
/* 729:    */    try
/* 730:    */    {
/* 731:731 */      c = (Short2ReferenceOpenHashMap)super.clone();
/* 732:    */    }
/* 733:    */    catch (CloneNotSupportedException cantHappen) {
/* 734:734 */      throw new InternalError();
/* 735:    */    }
/* 736:736 */    c.keys = null;
/* 737:737 */    c.values = null;
/* 738:738 */    c.entries = null;
/* 739:739 */    c.key = ((short[])this.key.clone());
/* 740:740 */    c.value = ((Object[])this.value.clone());
/* 741:741 */    c.used = ((boolean[])this.used.clone());
/* 742:742 */    return c;
/* 743:    */  }
/* 744:    */  
/* 752:    */  public int hashCode()
/* 753:    */  {
/* 754:754 */    int h = 0;
/* 755:755 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 756:756 */      while (this.used[i] == 0) i++;
/* 757:757 */      t = this.key[i];
/* 758:758 */      if (this != this.value[i])
/* 759:759 */        t ^= (this.value[i] == null ? 0 : System.identityHashCode(this.value[i]));
/* 760:760 */      h += t;
/* 761:761 */      i++;
/* 762:    */    }
/* 763:763 */    return h;
/* 764:    */  }
/* 765:    */  
/* 766:766 */  private void writeObject(ObjectOutputStream s) throws IOException { short[] key = this.key;
/* 767:767 */    V[] value = this.value;
/* 768:768 */    Short2ReferenceOpenHashMap<V>.MapIterator i = new MapIterator(null);
/* 769:769 */    s.defaultWriteObject();
/* 770:770 */    for (int j = this.size; j-- != 0;) {
/* 771:771 */      int e = i.nextEntry();
/* 772:772 */      s.writeShort(key[e]);
/* 773:773 */      s.writeObject(value[e]);
/* 774:    */    }
/* 775:    */  }
/* 776:    */  
/* 777:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 778:778 */    s.defaultReadObject();
/* 779:779 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 780:780 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 781:781 */    this.mask = (this.n - 1);
/* 782:782 */    short[] key = this.key = new short[this.n];
/* 783:783 */    V[] value = this.value = (Object[])new Object[this.n];
/* 784:784 */    boolean[] used = this.used = new boolean[this.n];
/* 785:    */    
/* 787:787 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 788:788 */      short k = s.readShort();
/* 789:789 */      V v = s.readObject();
/* 790:790 */      pos = HashCommon.murmurHash3(k) & this.mask;
/* 791:791 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 792:792 */      used[pos] = true;
/* 793:793 */      key[pos] = k;
/* 794:794 */      value[pos] = v;
/* 795:    */    }
/* 796:    */  }
/* 797:    */  
/* 798:    */  private void checkTable() {}
/* 799:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */