/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   7:    */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*   8:    */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*   9:    */import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*  10:    */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*  11:    */import java.io.IOException;
/*  12:    */import java.io.ObjectInputStream;
/*  13:    */import java.io.ObjectOutputStream;
/*  14:    */import java.io.Serializable;
/*  15:    */import java.util.Map;
/*  16:    */import java.util.Map.Entry;
/*  17:    */import java.util.NoSuchElementException;
/*  18:    */
/*  87:    */public class Long2BooleanOpenHashMap
/*  88:    */  extends AbstractLong2BooleanMap
/*  89:    */  implements Serializable, Cloneable, Hash
/*  90:    */{
/*  91:    */  public static final long serialVersionUID = 0L;
/*  92:    */  private static final boolean ASSERTS = false;
/*  93:    */  protected transient long[] key;
/*  94:    */  protected transient boolean[] value;
/*  95:    */  protected transient boolean[] used;
/*  96:    */  protected final float f;
/*  97:    */  protected transient int n;
/*  98:    */  protected transient int maxFill;
/*  99:    */  protected transient int mask;
/* 100:    */  protected int size;
/* 101:    */  protected volatile transient Long2BooleanMap.FastEntrySet entries;
/* 102:    */  protected volatile transient LongSet keys;
/* 103:    */  protected volatile transient BooleanCollection values;
/* 104:    */  
/* 105:    */  public Long2BooleanOpenHashMap(int expected, float f)
/* 106:    */  {
/* 107:107 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 108:108 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 109:109 */    this.f = f;
/* 110:110 */    this.n = HashCommon.arraySize(expected, f);
/* 111:111 */    this.mask = (this.n - 1);
/* 112:112 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 113:113 */    this.key = new long[this.n];
/* 114:114 */    this.value = new boolean[this.n];
/* 115:115 */    this.used = new boolean[this.n];
/* 116:    */  }
/* 117:    */  
/* 120:    */  public Long2BooleanOpenHashMap(int expected)
/* 121:    */  {
/* 122:122 */    this(expected, 0.75F);
/* 123:    */  }
/* 124:    */  
/* 126:    */  public Long2BooleanOpenHashMap()
/* 127:    */  {
/* 128:128 */    this(16, 0.75F);
/* 129:    */  }
/* 130:    */  
/* 134:    */  public Long2BooleanOpenHashMap(Map<? extends Long, ? extends Boolean> m, float f)
/* 135:    */  {
/* 136:136 */    this(m.size(), f);
/* 137:137 */    putAll(m);
/* 138:    */  }
/* 139:    */  
/* 142:    */  public Long2BooleanOpenHashMap(Map<? extends Long, ? extends Boolean> m)
/* 143:    */  {
/* 144:144 */    this(m, 0.75F);
/* 145:    */  }
/* 146:    */  
/* 150:    */  public Long2BooleanOpenHashMap(Long2BooleanMap m, float f)
/* 151:    */  {
/* 152:152 */    this(m.size(), f);
/* 153:153 */    putAll(m);
/* 154:    */  }
/* 155:    */  
/* 158:    */  public Long2BooleanOpenHashMap(Long2BooleanMap m)
/* 159:    */  {
/* 160:160 */    this(m, 0.75F);
/* 161:    */  }
/* 162:    */  
/* 168:    */  public Long2BooleanOpenHashMap(long[] k, boolean[] v, float f)
/* 169:    */  {
/* 170:170 */    this(k.length, f);
/* 171:171 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 172:172 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 179:    */  public Long2BooleanOpenHashMap(long[] k, boolean[] v)
/* 180:    */  {
/* 181:181 */    this(k, v, 0.75F);
/* 182:    */  }
/* 183:    */  
/* 187:    */  public boolean put(long k, boolean v)
/* 188:    */  {
/* 189:189 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 190:    */    
/* 191:191 */    while (this.used[pos] != 0) {
/* 192:192 */      if (this.key[pos] == k) {
/* 193:193 */        boolean oldValue = this.value[pos];
/* 194:194 */        this.value[pos] = v;
/* 195:195 */        return oldValue;
/* 196:    */      }
/* 197:197 */      pos = pos + 1 & this.mask;
/* 198:    */    }
/* 199:199 */    this.used[pos] = true;
/* 200:200 */    this.key[pos] = k;
/* 201:201 */    this.value[pos] = v;
/* 202:202 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 203:    */    }
/* 204:204 */    return this.defRetValue;
/* 205:    */  }
/* 206:    */  
/* 207:207 */  public Boolean put(Long ok, Boolean ov) { boolean v = ov.booleanValue();
/* 208:208 */    long k = ok.longValue();
/* 209:    */    
/* 210:210 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 211:    */    
/* 212:212 */    while (this.used[pos] != 0) {
/* 213:213 */      if (this.key[pos] == k) {
/* 214:214 */        Boolean oldValue = Boolean.valueOf(this.value[pos]);
/* 215:215 */        this.value[pos] = v;
/* 216:216 */        return oldValue;
/* 217:    */      }
/* 218:218 */      pos = pos + 1 & this.mask;
/* 219:    */    }
/* 220:220 */    this.used[pos] = true;
/* 221:221 */    this.key[pos] = k;
/* 222:222 */    this.value[pos] = v;
/* 223:223 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 224:    */    }
/* 225:225 */    return null;
/* 226:    */  }
/* 227:    */  
/* 230:    */  protected final int shiftKeys(int pos)
/* 231:    */  {
/* 232:    */    int last;
/* 233:    */    
/* 235:    */    for (;;)
/* 236:    */    {
/* 237:237 */      pos = (last = pos) + 1 & this.mask;
/* 238:238 */      while (this.used[pos] != 0) {
/* 239:239 */        int slot = (int)HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 240:240 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 241:241 */        pos = pos + 1 & this.mask;
/* 242:    */      }
/* 243:243 */      if (this.used[pos] == 0) break;
/* 244:244 */      this.key[last] = this.key[pos];
/* 245:245 */      this.value[last] = this.value[pos];
/* 246:    */    }
/* 247:247 */    this.used[last] = false;
/* 248:248 */    return last;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public boolean remove(long k)
/* 252:    */  {
/* 253:253 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 254:    */    
/* 255:255 */    while (this.used[pos] != 0) {
/* 256:256 */      if (this.key[pos] == k) {
/* 257:257 */        this.size -= 1;
/* 258:258 */        boolean v = this.value[pos];
/* 259:259 */        shiftKeys(pos);
/* 260:260 */        return v;
/* 261:    */      }
/* 262:262 */      pos = pos + 1 & this.mask;
/* 263:    */    }
/* 264:264 */    return this.defRetValue;
/* 265:    */  }
/* 266:    */  
/* 267:    */  public Boolean remove(Object ok) {
/* 268:268 */    long k = ((Long)ok).longValue();
/* 269:    */    
/* 270:270 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 271:    */    
/* 272:272 */    while (this.used[pos] != 0) {
/* 273:273 */      if (this.key[pos] == k) {
/* 274:274 */        this.size -= 1;
/* 275:275 */        boolean v = this.value[pos];
/* 276:276 */        shiftKeys(pos);
/* 277:277 */        return Boolean.valueOf(v);
/* 278:    */      }
/* 279:279 */      pos = pos + 1 & this.mask;
/* 280:    */    }
/* 281:281 */    return null;
/* 282:    */  }
/* 283:    */  
/* 284:284 */  public Boolean get(Long ok) { long k = ok.longValue();
/* 285:    */    
/* 286:286 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 287:    */    
/* 288:288 */    while (this.used[pos] != 0) {
/* 289:289 */      if (this.key[pos] == k) return Boolean.valueOf(this.value[pos]);
/* 290:290 */      pos = pos + 1 & this.mask;
/* 291:    */    }
/* 292:292 */    return null;
/* 293:    */  }
/* 294:    */  
/* 295:    */  public boolean get(long k)
/* 296:    */  {
/* 297:297 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 298:    */    
/* 299:299 */    while (this.used[pos] != 0) {
/* 300:300 */      if (this.key[pos] == k) return this.value[pos];
/* 301:301 */      pos = pos + 1 & this.mask;
/* 302:    */    }
/* 303:303 */    return this.defRetValue;
/* 304:    */  }
/* 305:    */  
/* 306:    */  public boolean containsKey(long k)
/* 307:    */  {
/* 308:308 */    int pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 309:    */    
/* 310:310 */    while (this.used[pos] != 0) {
/* 311:311 */      if (this.key[pos] == k) return true;
/* 312:312 */      pos = pos + 1 & this.mask;
/* 313:    */    }
/* 314:314 */    return false;
/* 315:    */  }
/* 316:    */  
/* 317:317 */  public boolean containsValue(boolean v) { boolean[] value = this.value;
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
/* 332:    */  }
/* 333:    */  
/* 334:    */  public int size() {
/* 335:335 */    return this.size;
/* 336:    */  }
/* 337:    */  
/* 338:338 */  public boolean isEmpty() { return this.size == 0; }
/* 339:    */  
/* 345:    */  @Deprecated
/* 346:    */  public void growthFactor(int growthFactor) {}
/* 347:    */  
/* 352:    */  @Deprecated
/* 353:    */  public int growthFactor()
/* 354:    */  {
/* 355:355 */    return 16;
/* 356:    */  }
/* 357:    */  
/* 358:    */  private final class MapEntry
/* 359:    */    implements Long2BooleanMap.Entry, Map.Entry<Long, Boolean>
/* 360:    */  {
/* 361:    */    private int index;
/* 362:    */    
/* 363:    */    MapEntry(int index)
/* 364:    */    {
/* 365:365 */      this.index = index;
/* 366:    */    }
/* 367:    */    
/* 368:368 */    public Long getKey() { return Long.valueOf(Long2BooleanOpenHashMap.this.key[this.index]); }
/* 369:    */    
/* 370:    */    public long getLongKey() {
/* 371:371 */      return Long2BooleanOpenHashMap.this.key[this.index];
/* 372:    */    }
/* 373:    */    
/* 374:374 */    public Boolean getValue() { return Boolean.valueOf(Long2BooleanOpenHashMap.this.value[this.index]); }
/* 375:    */    
/* 377:377 */    public boolean getBooleanValue() { return Long2BooleanOpenHashMap.this.value[this.index]; }
/* 378:    */    
/* 379:    */    public boolean setValue(boolean v) {
/* 380:380 */      boolean oldValue = Long2BooleanOpenHashMap.this.value[this.index];
/* 381:381 */      Long2BooleanOpenHashMap.this.value[this.index] = v;
/* 382:382 */      return oldValue;
/* 383:    */    }
/* 384:    */    
/* 385:385 */    public Boolean setValue(Boolean v) { return Boolean.valueOf(setValue(v.booleanValue())); }
/* 386:    */    
/* 387:    */    public boolean equals(Object o)
/* 388:    */    {
/* 389:389 */      if (!(o instanceof Map.Entry)) return false;
/* 390:390 */      Map.Entry<Long, Boolean> e = (Map.Entry)o;
/* 391:391 */      return (Long2BooleanOpenHashMap.this.key[this.index] == ((Long)e.getKey()).longValue()) && (Long2BooleanOpenHashMap.this.value[this.index] == ((Boolean)e.getValue()).booleanValue());
/* 392:    */    }
/* 393:    */    
/* 394:394 */    public int hashCode() { return HashCommon.long2int(Long2BooleanOpenHashMap.this.key[this.index]) ^ (Long2BooleanOpenHashMap.this.value[this.index] != 0 ? 1231 : 1237); }
/* 395:    */    
/* 397:397 */    public String toString() { return Long2BooleanOpenHashMap.this.key[this.index] + "=>" + Long2BooleanOpenHashMap.this.value[this.index]; } }
/* 398:    */  
/* 399:    */  private class MapIterator { int pos;
/* 400:    */    int last;
/* 401:    */    int c;
/* 402:    */    LongArrayList wrapped;
/* 403:    */    
/* 404:404 */    private MapIterator() { this.pos = Long2BooleanOpenHashMap.this.n;
/* 405:    */      
/* 407:407 */      this.last = -1;
/* 408:    */      
/* 409:409 */      this.c = Long2BooleanOpenHashMap.this.size;
/* 410:    */      
/* 414:414 */      boolean[] used = Long2BooleanOpenHashMap.this.used;
/* 415:415 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 416:    */    }
/* 417:    */    
/* 418:418 */    public boolean hasNext() { return this.c != 0; }
/* 419:    */    
/* 420:    */    public int nextEntry() {
/* 421:421 */      if (!hasNext()) throw new NoSuchElementException();
/* 422:422 */      this.c -= 1;
/* 423:    */      
/* 424:424 */      if (this.pos < 0) {
/* 425:425 */        long k = this.wrapped.getLong(-(this.last = --this.pos) - 2);
/* 426:    */        
/* 427:427 */        int pos = (int)HashCommon.murmurHash3(k) & Long2BooleanOpenHashMap.this.mask;
/* 428:    */        
/* 429:429 */        while (Long2BooleanOpenHashMap.this.used[pos] != 0) {
/* 430:430 */          if (Long2BooleanOpenHashMap.this.key[pos] == k) return pos;
/* 431:431 */          pos = pos + 1 & Long2BooleanOpenHashMap.this.mask;
/* 432:    */        }
/* 433:    */      }
/* 434:434 */      this.last = this.pos;
/* 435:    */      
/* 436:436 */      if (this.c != 0) {
/* 437:437 */        boolean[] used = Long2BooleanOpenHashMap.this.used;
/* 438:438 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 439:    */      }
/* 440:    */      
/* 441:441 */      return this.last;
/* 442:    */    }
/* 443:    */    
/* 447:    */    protected final int shiftKeys(int pos)
/* 448:    */    {
/* 449:    */      int last;
/* 450:    */      
/* 452:    */      for (;;)
/* 453:    */      {
/* 454:454 */        pos = (last = pos) + 1 & Long2BooleanOpenHashMap.this.mask;
/* 455:455 */        while (Long2BooleanOpenHashMap.this.used[pos] != 0) {
/* 456:456 */          int slot = (int)HashCommon.murmurHash3(Long2BooleanOpenHashMap.this.key[pos]) & Long2BooleanOpenHashMap.this.mask;
/* 457:457 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 458:458 */          pos = pos + 1 & Long2BooleanOpenHashMap.this.mask;
/* 459:    */        }
/* 460:460 */        if (Long2BooleanOpenHashMap.this.used[pos] == 0) break;
/* 461:461 */        if (pos < last)
/* 462:    */        {
/* 463:463 */          if (this.wrapped == null) this.wrapped = new LongArrayList();
/* 464:464 */          this.wrapped.add(Long2BooleanOpenHashMap.this.key[pos]);
/* 465:    */        }
/* 466:466 */        Long2BooleanOpenHashMap.this.key[last] = Long2BooleanOpenHashMap.this.key[pos];
/* 467:467 */        Long2BooleanOpenHashMap.this.value[last] = Long2BooleanOpenHashMap.this.value[pos];
/* 468:    */      }
/* 469:469 */      Long2BooleanOpenHashMap.this.used[last] = false;
/* 470:470 */      return last;
/* 471:    */    }
/* 472:    */    
/* 473:    */    public void remove() {
/* 474:474 */      if (this.last == -1) throw new IllegalStateException();
/* 475:475 */      if (this.pos < -1)
/* 476:    */      {
/* 477:477 */        Long2BooleanOpenHashMap.this.remove(this.wrapped.getLong(-this.pos - 2));
/* 478:478 */        this.last = -1;
/* 479:479 */        return;
/* 480:    */      }
/* 481:481 */      Long2BooleanOpenHashMap.this.size -= 1;
/* 482:482 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 483:483 */        this.c += 1;
/* 484:484 */        nextEntry();
/* 485:    */      }
/* 486:486 */      this.last = -1;
/* 487:    */    }
/* 488:    */    
/* 489:    */    public int skip(int n) {
/* 490:490 */      int i = n;
/* 491:491 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 492:492 */      return n - i - 1;
/* 493:    */    } }
/* 494:    */  
/* 495:495 */  private class EntryIterator extends Long2BooleanOpenHashMap.MapIterator implements ObjectIterator<Long2BooleanMap.Entry> { private EntryIterator() { super(null); }
/* 496:    */    
/* 497:    */    private Long2BooleanOpenHashMap.MapEntry entry;
/* 498:498 */    public Long2BooleanMap.Entry next() { return this.entry = new Long2BooleanOpenHashMap.MapEntry(Long2BooleanOpenHashMap.this, nextEntry()); }
/* 499:    */    
/* 500:    */    public void remove()
/* 501:    */    {
/* 502:502 */      super.remove();
/* 503:503 */      Long2BooleanOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 504:    */    } }
/* 505:    */  
/* 506:506 */  private class FastEntryIterator extends Long2BooleanOpenHashMap.MapIterator implements ObjectIterator<Long2BooleanMap.Entry> { private FastEntryIterator() { super(null); }
/* 507:507 */    final AbstractLong2BooleanMap.BasicEntry entry = new AbstractLong2BooleanMap.BasicEntry(0L, false);
/* 508:    */    
/* 509:509 */    public AbstractLong2BooleanMap.BasicEntry next() { int e = nextEntry();
/* 510:510 */      this.entry.key = Long2BooleanOpenHashMap.this.key[e];
/* 511:511 */      this.entry.value = Long2BooleanOpenHashMap.this.value[e];
/* 512:512 */      return this.entry;
/* 513:    */    } }
/* 514:    */  
/* 515:    */  private final class MapEntrySet extends AbstractObjectSet<Long2BooleanMap.Entry> implements Long2BooleanMap.FastEntrySet { private MapEntrySet() {}
/* 516:    */    
/* 517:517 */    public ObjectIterator<Long2BooleanMap.Entry> iterator() { return new Long2BooleanOpenHashMap.EntryIterator(Long2BooleanOpenHashMap.this, null); }
/* 518:    */    
/* 519:    */    public ObjectIterator<Long2BooleanMap.Entry> fastIterator() {
/* 520:520 */      return new Long2BooleanOpenHashMap.FastEntryIterator(Long2BooleanOpenHashMap.this, null);
/* 521:    */    }
/* 522:    */    
/* 523:    */    public boolean contains(Object o) {
/* 524:524 */      if (!(o instanceof Map.Entry)) return false;
/* 525:525 */      Map.Entry<Long, Boolean> e = (Map.Entry)o;
/* 526:526 */      long k = ((Long)e.getKey()).longValue();
/* 527:    */      
/* 528:528 */      int pos = (int)HashCommon.murmurHash3(k) & Long2BooleanOpenHashMap.this.mask;
/* 529:    */      
/* 530:530 */      while (Long2BooleanOpenHashMap.this.used[pos] != 0) {
/* 531:531 */        if (Long2BooleanOpenHashMap.this.key[pos] == k) return Long2BooleanOpenHashMap.this.value[pos] == ((Boolean)e.getValue()).booleanValue();
/* 532:532 */        pos = pos + 1 & Long2BooleanOpenHashMap.this.mask;
/* 533:    */      }
/* 534:534 */      return false;
/* 535:    */    }
/* 536:    */    
/* 537:    */    public boolean remove(Object o) {
/* 538:538 */      if (!(o instanceof Map.Entry)) return false;
/* 539:539 */      Map.Entry<Long, Boolean> e = (Map.Entry)o;
/* 540:540 */      long k = ((Long)e.getKey()).longValue();
/* 541:    */      
/* 542:542 */      int pos = (int)HashCommon.murmurHash3(k) & Long2BooleanOpenHashMap.this.mask;
/* 543:    */      
/* 544:544 */      while (Long2BooleanOpenHashMap.this.used[pos] != 0) {
/* 545:545 */        if (Long2BooleanOpenHashMap.this.key[pos] == k) {
/* 546:546 */          Long2BooleanOpenHashMap.this.remove(e.getKey());
/* 547:547 */          return true;
/* 548:    */        }
/* 549:549 */        pos = pos + 1 & Long2BooleanOpenHashMap.this.mask;
/* 550:    */      }
/* 551:551 */      return false;
/* 552:    */    }
/* 553:    */    
/* 554:554 */    public int size() { return Long2BooleanOpenHashMap.this.size; }
/* 555:    */    
/* 557:557 */    public void clear() { Long2BooleanOpenHashMap.this.clear(); }
/* 558:    */  }
/* 559:    */  
/* 560:    */  public Long2BooleanMap.FastEntrySet long2BooleanEntrySet() {
/* 561:561 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 562:562 */    return this.entries;
/* 563:    */  }
/* 564:    */  
/* 567:    */  private final class KeyIterator
/* 568:    */    extends Long2BooleanOpenHashMap.MapIterator
/* 569:    */    implements LongIterator
/* 570:    */  {
/* 571:571 */    public KeyIterator() { super(null); }
/* 572:572 */    public long nextLong() { return Long2BooleanOpenHashMap.this.key[nextEntry()]; }
/* 573:573 */    public Long next() { return Long.valueOf(Long2BooleanOpenHashMap.this.key[nextEntry()]); } }
/* 574:    */  
/* 575:    */  private final class KeySet extends AbstractLongSet { private KeySet() {}
/* 576:    */    
/* 577:577 */    public LongIterator iterator() { return new Long2BooleanOpenHashMap.KeyIterator(Long2BooleanOpenHashMap.this); }
/* 578:    */    
/* 579:    */    public int size() {
/* 580:580 */      return Long2BooleanOpenHashMap.this.size;
/* 581:    */    }
/* 582:    */    
/* 583:583 */    public boolean contains(long k) { return Long2BooleanOpenHashMap.this.containsKey(k); }
/* 584:    */    
/* 585:    */    public boolean remove(long k) {
/* 586:586 */      int oldSize = Long2BooleanOpenHashMap.this.size;
/* 587:587 */      Long2BooleanOpenHashMap.this.remove(k);
/* 588:588 */      return Long2BooleanOpenHashMap.this.size != oldSize;
/* 589:    */    }
/* 590:    */    
/* 591:591 */    public void clear() { Long2BooleanOpenHashMap.this.clear(); }
/* 592:    */  }
/* 593:    */  
/* 594:    */  public LongSet keySet() {
/* 595:595 */    if (this.keys == null) this.keys = new KeySet(null);
/* 596:596 */    return this.keys;
/* 597:    */  }
/* 598:    */  
/* 601:    */  private final class ValueIterator
/* 602:    */    extends Long2BooleanOpenHashMap.MapIterator
/* 603:    */    implements BooleanIterator
/* 604:    */  {
/* 605:605 */    public ValueIterator() { super(null); }
/* 606:606 */    public boolean nextBoolean() { return Long2BooleanOpenHashMap.this.value[nextEntry()]; }
/* 607:607 */    public Boolean next() { return Boolean.valueOf(Long2BooleanOpenHashMap.this.value[nextEntry()]); }
/* 608:    */  }
/* 609:    */  
/* 610:610 */  public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 611:    */        public BooleanIterator iterator() {
/* 612:612 */          return new Long2BooleanOpenHashMap.ValueIterator(Long2BooleanOpenHashMap.this);
/* 613:    */        }
/* 614:    */        
/* 615:615 */        public int size() { return Long2BooleanOpenHashMap.this.size; }
/* 616:    */        
/* 617:    */        public boolean contains(boolean v) {
/* 618:618 */          return Long2BooleanOpenHashMap.this.containsValue(v);
/* 619:    */        }
/* 620:    */        
/* 621:621 */        public void clear() { Long2BooleanOpenHashMap.this.clear(); }
/* 622:    */      };
/* 623:    */    }
/* 624:624 */    return this.values;
/* 625:    */  }
/* 626:    */  
/* 635:    */  @Deprecated
/* 636:    */  public boolean rehash()
/* 637:    */  {
/* 638:638 */    return true;
/* 639:    */  }
/* 640:    */  
/* 651:    */  public boolean trim()
/* 652:    */  {
/* 653:653 */    int l = HashCommon.arraySize(this.size, this.f);
/* 654:654 */    if (l >= this.n) return true;
/* 655:    */    try {
/* 656:656 */      rehash(l);
/* 657:    */    } catch (OutOfMemoryError cantDoIt) {
/* 658:658 */      return false; }
/* 659:659 */    return true;
/* 660:    */  }
/* 661:    */  
/* 678:    */  public boolean trim(int n)
/* 679:    */  {
/* 680:680 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 681:681 */    if (this.n <= l) return true;
/* 682:    */    try {
/* 683:683 */      rehash(l);
/* 684:    */    } catch (OutOfMemoryError cantDoIt) {
/* 685:685 */      return false; }
/* 686:686 */    return true;
/* 687:    */  }
/* 688:    */  
/* 697:    */  protected void rehash(int newN)
/* 698:    */  {
/* 699:699 */    int i = 0;
/* 700:700 */    boolean[] used = this.used;
/* 701:    */    
/* 702:702 */    long[] key = this.key;
/* 703:703 */    boolean[] value = this.value;
/* 704:704 */    int newMask = newN - 1;
/* 705:705 */    long[] newKey = new long[newN];
/* 706:706 */    boolean[] newValue = new boolean[newN];
/* 707:707 */    boolean[] newUsed = new boolean[newN];
/* 708:708 */    for (int j = this.size; j-- != 0;) {
/* 709:709 */      while (used[i] == 0) i++;
/* 710:710 */      long k = key[i];
/* 711:711 */      int pos = (int)HashCommon.murmurHash3(k) & newMask;
/* 712:712 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 713:713 */      newUsed[pos] = true;
/* 714:714 */      newKey[pos] = k;
/* 715:715 */      newValue[pos] = value[i];
/* 716:716 */      i++;
/* 717:    */    }
/* 718:718 */    this.n = newN;
/* 719:719 */    this.mask = newMask;
/* 720:720 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 721:721 */    this.key = newKey;
/* 722:722 */    this.value = newValue;
/* 723:723 */    this.used = newUsed;
/* 724:    */  }
/* 725:    */  
/* 729:    */  public Long2BooleanOpenHashMap clone()
/* 730:    */  {
/* 731:    */    Long2BooleanOpenHashMap c;
/* 732:    */    
/* 734:    */    try
/* 735:    */    {
/* 736:736 */      c = (Long2BooleanOpenHashMap)super.clone();
/* 737:    */    }
/* 738:    */    catch (CloneNotSupportedException cantHappen) {
/* 739:739 */      throw new InternalError();
/* 740:    */    }
/* 741:741 */    c.keys = null;
/* 742:742 */    c.values = null;
/* 743:743 */    c.entries = null;
/* 744:744 */    c.key = ((long[])this.key.clone());
/* 745:745 */    c.value = ((boolean[])this.value.clone());
/* 746:746 */    c.used = ((boolean[])this.used.clone());
/* 747:747 */    return c;
/* 748:    */  }
/* 749:    */  
/* 757:    */  public int hashCode()
/* 758:    */  {
/* 759:759 */    int h = 0;
/* 760:760 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 761:761 */      while (this.used[i] == 0) i++;
/* 762:762 */      t = HashCommon.long2int(this.key[i]);
/* 763:763 */      t ^= (this.value[i] != 0 ? 1231 : 1237);
/* 764:764 */      h += t;
/* 765:765 */      i++;
/* 766:    */    }
/* 767:767 */    return h;
/* 768:    */  }
/* 769:    */  
/* 770:770 */  private void writeObject(ObjectOutputStream s) throws IOException { long[] key = this.key;
/* 771:771 */    boolean[] value = this.value;
/* 772:772 */    MapIterator i = new MapIterator(null);
/* 773:773 */    s.defaultWriteObject();
/* 774:774 */    for (int j = this.size; j-- != 0;) {
/* 775:775 */      int e = i.nextEntry();
/* 776:776 */      s.writeLong(key[e]);
/* 777:777 */      s.writeBoolean(value[e]);
/* 778:    */    }
/* 779:    */  }
/* 780:    */  
/* 781:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 782:782 */    s.defaultReadObject();
/* 783:783 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 784:784 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 785:785 */    this.mask = (this.n - 1);
/* 786:786 */    long[] key = this.key = new long[this.n];
/* 787:787 */    boolean[] value = this.value = new boolean[this.n];
/* 788:788 */    boolean[] used = this.used = new boolean[this.n];
/* 789:    */    
/* 791:791 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 792:792 */      long k = s.readLong();
/* 793:793 */      boolean v = s.readBoolean();
/* 794:794 */      pos = (int)HashCommon.murmurHash3(k) & this.mask;
/* 795:795 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 796:796 */      used[pos] = true;
/* 797:797 */      key[pos] = k;
/* 798:798 */      value[pos] = v;
/* 799:    */    }
/* 800:    */  }
/* 801:    */  
/* 802:    */  private void checkTable() {}
/* 803:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2BooleanOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */