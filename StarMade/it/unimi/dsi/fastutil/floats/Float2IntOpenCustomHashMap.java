/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*   7:    */import it.unimi.dsi.fastutil.ints.IntCollection;
/*   8:    */import it.unimi.dsi.fastutil.ints.IntIterator;
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
/*  90:    */public class Float2IntOpenCustomHashMap
/*  91:    */  extends AbstractFloat2IntMap
/*  92:    */  implements Serializable, Cloneable, Hash
/*  93:    */{
/*  94:    */  public static final long serialVersionUID = 0L;
/*  95:    */  private static final boolean ASSERTS = false;
/*  96:    */  protected transient float[] key;
/*  97:    */  protected transient int[] value;
/*  98:    */  protected transient boolean[] used;
/*  99:    */  protected final float f;
/* 100:    */  protected transient int n;
/* 101:    */  protected transient int maxFill;
/* 102:    */  protected transient int mask;
/* 103:    */  protected int size;
/* 104:    */  protected volatile transient Float2IntMap.FastEntrySet entries;
/* 105:    */  protected volatile transient FloatSet keys;
/* 106:    */  protected volatile transient IntCollection values;
/* 107:    */  protected FloatHash.Strategy strategy;
/* 108:    */  
/* 109:    */  public Float2IntOpenCustomHashMap(int expected, float f, FloatHash.Strategy strategy)
/* 110:    */  {
/* 111:111 */    this.strategy = strategy;
/* 112:112 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 113:113 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 114:114 */    this.f = f;
/* 115:115 */    this.n = HashCommon.arraySize(expected, f);
/* 116:116 */    this.mask = (this.n - 1);
/* 117:117 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 118:118 */    this.key = new float[this.n];
/* 119:119 */    this.value = new int[this.n];
/* 120:120 */    this.used = new boolean[this.n];
/* 121:    */  }
/* 122:    */  
/* 126:    */  public Float2IntOpenCustomHashMap(int expected, FloatHash.Strategy strategy)
/* 127:    */  {
/* 128:128 */    this(expected, 0.75F, strategy);
/* 129:    */  }
/* 130:    */  
/* 133:    */  public Float2IntOpenCustomHashMap(FloatHash.Strategy strategy)
/* 134:    */  {
/* 135:135 */    this(16, 0.75F, strategy);
/* 136:    */  }
/* 137:    */  
/* 142:    */  public Float2IntOpenCustomHashMap(Map<? extends Float, ? extends Integer> m, float f, FloatHash.Strategy strategy)
/* 143:    */  {
/* 144:144 */    this(m.size(), f, strategy);
/* 145:145 */    putAll(m);
/* 146:    */  }
/* 147:    */  
/* 151:    */  public Float2IntOpenCustomHashMap(Map<? extends Float, ? extends Integer> m, FloatHash.Strategy strategy)
/* 152:    */  {
/* 153:153 */    this(m, 0.75F, strategy);
/* 154:    */  }
/* 155:    */  
/* 160:    */  public Float2IntOpenCustomHashMap(Float2IntMap m, float f, FloatHash.Strategy strategy)
/* 161:    */  {
/* 162:162 */    this(m.size(), f, strategy);
/* 163:163 */    putAll(m);
/* 164:    */  }
/* 165:    */  
/* 169:    */  public Float2IntOpenCustomHashMap(Float2IntMap m, FloatHash.Strategy strategy)
/* 170:    */  {
/* 171:171 */    this(m, 0.75F, strategy);
/* 172:    */  }
/* 173:    */  
/* 180:    */  public Float2IntOpenCustomHashMap(float[] k, int[] v, float f, FloatHash.Strategy strategy)
/* 181:    */  {
/* 182:182 */    this(k.length, f, strategy);
/* 183:183 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 184:184 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 192:    */  public Float2IntOpenCustomHashMap(float[] k, int[] v, FloatHash.Strategy strategy)
/* 193:    */  {
/* 194:194 */    this(k, v, 0.75F, strategy);
/* 195:    */  }
/* 196:    */  
/* 199:    */  public FloatHash.Strategy strategy()
/* 200:    */  {
/* 201:201 */    return this.strategy;
/* 202:    */  }
/* 203:    */  
/* 207:    */  public int put(float k, int v)
/* 208:    */  {
/* 209:209 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 210:    */    
/* 211:211 */    while (this.used[pos] != 0) {
/* 212:212 */      if (this.strategy.equals(this.key[pos], k)) {
/* 213:213 */        int oldValue = this.value[pos];
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
/* 227:227 */  public Integer put(Float ok, Integer ov) { int v = ov.intValue();
/* 228:228 */    float k = ok.floatValue();
/* 229:    */    
/* 230:230 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 231:    */    
/* 232:232 */    while (this.used[pos] != 0) {
/* 233:233 */      if (this.strategy.equals(this.key[pos], k)) {
/* 234:234 */        Integer oldValue = Integer.valueOf(this.value[pos]);
/* 235:235 */        this.value[pos] = v;
/* 236:236 */        return oldValue;
/* 237:    */      }
/* 238:238 */      pos = pos + 1 & this.mask;
/* 239:    */    }
/* 240:240 */    this.used[pos] = true;
/* 241:241 */    this.key[pos] = k;
/* 242:242 */    this.value[pos] = v;
/* 243:243 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 244:    */    }
/* 245:245 */    return null;
/* 246:    */  }
/* 247:    */  
/* 258:    */  public int add(float k, int incr)
/* 259:    */  {
/* 260:260 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 261:    */    
/* 262:262 */    while (this.used[pos] != 0) {
/* 263:263 */      if (this.strategy.equals(this.key[pos], k)) {
/* 264:264 */        int oldValue = this.value[pos];
/* 265:265 */        this.value[pos] += incr;
/* 266:266 */        return oldValue;
/* 267:    */      }
/* 268:268 */      pos = pos + 1 & this.mask;
/* 269:    */    }
/* 270:270 */    this.used[pos] = true;
/* 271:271 */    this.key[pos] = k;
/* 272:272 */    this.value[pos] = (this.defRetValue + incr);
/* 273:273 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 274:    */    }
/* 275:275 */    return this.defRetValue;
/* 276:    */  }
/* 277:    */  
/* 280:    */  protected final int shiftKeys(int pos)
/* 281:    */  {
/* 282:    */    int last;
/* 283:    */    
/* 285:    */    for (;;)
/* 286:    */    {
/* 287:287 */      pos = (last = pos) + 1 & this.mask;
/* 288:288 */      while (this.used[pos] != 0) {
/* 289:289 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 290:290 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 291:291 */        pos = pos + 1 & this.mask;
/* 292:    */      }
/* 293:293 */      if (this.used[pos] == 0) break;
/* 294:294 */      this.key[last] = this.key[pos];
/* 295:295 */      this.value[last] = this.value[pos];
/* 296:    */    }
/* 297:297 */    this.used[last] = false;
/* 298:298 */    return last;
/* 299:    */  }
/* 300:    */  
/* 301:    */  public int remove(float k)
/* 302:    */  {
/* 303:303 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 304:    */    
/* 305:305 */    while (this.used[pos] != 0) {
/* 306:306 */      if (this.strategy.equals(this.key[pos], k)) {
/* 307:307 */        this.size -= 1;
/* 308:308 */        int v = this.value[pos];
/* 309:309 */        shiftKeys(pos);
/* 310:310 */        return v;
/* 311:    */      }
/* 312:312 */      pos = pos + 1 & this.mask;
/* 313:    */    }
/* 314:314 */    return this.defRetValue;
/* 315:    */  }
/* 316:    */  
/* 317:    */  public Integer remove(Object ok) {
/* 318:318 */    float k = ((Float)ok).floatValue();
/* 319:    */    
/* 320:320 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 321:    */    
/* 322:322 */    while (this.used[pos] != 0) {
/* 323:323 */      if (this.strategy.equals(this.key[pos], k)) {
/* 324:324 */        this.size -= 1;
/* 325:325 */        int v = this.value[pos];
/* 326:326 */        shiftKeys(pos);
/* 327:327 */        return Integer.valueOf(v);
/* 328:    */      }
/* 329:329 */      pos = pos + 1 & this.mask;
/* 330:    */    }
/* 331:331 */    return null;
/* 332:    */  }
/* 333:    */  
/* 334:334 */  public Integer get(Float ok) { float k = ok.floatValue();
/* 335:    */    
/* 336:336 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 337:    */    
/* 338:338 */    while (this.used[pos] != 0) {
/* 339:339 */      if (this.strategy.equals(this.key[pos], k)) return Integer.valueOf(this.value[pos]);
/* 340:340 */      pos = pos + 1 & this.mask;
/* 341:    */    }
/* 342:342 */    return null;
/* 343:    */  }
/* 344:    */  
/* 345:    */  public int get(float k)
/* 346:    */  {
/* 347:347 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 348:    */    
/* 349:349 */    while (this.used[pos] != 0) {
/* 350:350 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 351:351 */      pos = pos + 1 & this.mask;
/* 352:    */    }
/* 353:353 */    return this.defRetValue;
/* 354:    */  }
/* 355:    */  
/* 356:    */  public boolean containsKey(float k)
/* 357:    */  {
/* 358:358 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 359:    */    
/* 360:360 */    while (this.used[pos] != 0) {
/* 361:361 */      if (this.strategy.equals(this.key[pos], k)) return true;
/* 362:362 */      pos = pos + 1 & this.mask;
/* 363:    */    }
/* 364:364 */    return false;
/* 365:    */  }
/* 366:    */  
/* 367:367 */  public boolean containsValue(int v) { int[] value = this.value;
/* 368:368 */    boolean[] used = this.used;
/* 369:369 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/* 370:370 */    return false;
/* 371:    */  }
/* 372:    */  
/* 377:    */  public void clear()
/* 378:    */  {
/* 379:379 */    if (this.size == 0) return;
/* 380:380 */    this.size = 0;
/* 381:381 */    BooleanArrays.fill(this.used, false);
/* 382:    */  }
/* 383:    */  
/* 384:    */  public int size() {
/* 385:385 */    return this.size;
/* 386:    */  }
/* 387:    */  
/* 388:388 */  public boolean isEmpty() { return this.size == 0; }
/* 389:    */  
/* 395:    */  @Deprecated
/* 396:    */  public void growthFactor(int growthFactor) {}
/* 397:    */  
/* 402:    */  @Deprecated
/* 403:    */  public int growthFactor()
/* 404:    */  {
/* 405:405 */    return 16;
/* 406:    */  }
/* 407:    */  
/* 408:    */  private final class MapEntry
/* 409:    */    implements Float2IntMap.Entry, Map.Entry<Float, Integer>
/* 410:    */  {
/* 411:    */    private int index;
/* 412:    */    
/* 413:    */    MapEntry(int index)
/* 414:    */    {
/* 415:415 */      this.index = index;
/* 416:    */    }
/* 417:    */    
/* 418:418 */    public Float getKey() { return Float.valueOf(Float2IntOpenCustomHashMap.this.key[this.index]); }
/* 419:    */    
/* 420:    */    public float getFloatKey() {
/* 421:421 */      return Float2IntOpenCustomHashMap.this.key[this.index];
/* 422:    */    }
/* 423:    */    
/* 424:424 */    public Integer getValue() { return Integer.valueOf(Float2IntOpenCustomHashMap.this.value[this.index]); }
/* 425:    */    
/* 427:427 */    public int getIntValue() { return Float2IntOpenCustomHashMap.this.value[this.index]; }
/* 428:    */    
/* 429:    */    public int setValue(int v) {
/* 430:430 */      int oldValue = Float2IntOpenCustomHashMap.this.value[this.index];
/* 431:431 */      Float2IntOpenCustomHashMap.this.value[this.index] = v;
/* 432:432 */      return oldValue;
/* 433:    */    }
/* 434:    */    
/* 435:435 */    public Integer setValue(Integer v) { return Integer.valueOf(setValue(v.intValue())); }
/* 436:    */    
/* 437:    */    public boolean equals(Object o)
/* 438:    */    {
/* 439:439 */      if (!(o instanceof Map.Entry)) return false;
/* 440:440 */      Map.Entry<Float, Integer> e = (Map.Entry)o;
/* 441:441 */      return (Float2IntOpenCustomHashMap.this.strategy.equals(Float2IntOpenCustomHashMap.this.key[this.index], ((Float)e.getKey()).floatValue())) && (Float2IntOpenCustomHashMap.this.value[this.index] == ((Integer)e.getValue()).intValue());
/* 442:    */    }
/* 443:    */    
/* 444:444 */    public int hashCode() { return Float2IntOpenCustomHashMap.this.strategy.hashCode(Float2IntOpenCustomHashMap.this.key[this.index]) ^ Float2IntOpenCustomHashMap.this.value[this.index]; }
/* 445:    */    
/* 447:447 */    public String toString() { return Float2IntOpenCustomHashMap.this.key[this.index] + "=>" + Float2IntOpenCustomHashMap.this.value[this.index]; } }
/* 448:    */  
/* 449:    */  private class MapIterator { int pos;
/* 450:    */    int last;
/* 451:    */    int c;
/* 452:    */    FloatArrayList wrapped;
/* 453:    */    
/* 454:454 */    private MapIterator() { this.pos = Float2IntOpenCustomHashMap.this.n;
/* 455:    */      
/* 457:457 */      this.last = -1;
/* 458:    */      
/* 459:459 */      this.c = Float2IntOpenCustomHashMap.this.size;
/* 460:    */      
/* 464:464 */      boolean[] used = Float2IntOpenCustomHashMap.this.used;
/* 465:465 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 466:    */    }
/* 467:    */    
/* 468:468 */    public boolean hasNext() { return this.c != 0; }
/* 469:    */    
/* 470:    */    public int nextEntry() {
/* 471:471 */      if (!hasNext()) throw new NoSuchElementException();
/* 472:472 */      this.c -= 1;
/* 473:    */      
/* 474:474 */      if (this.pos < 0) {
/* 475:475 */        float k = this.wrapped.getFloat(-(this.last = --this.pos) - 2);
/* 476:    */        
/* 477:477 */        int pos = HashCommon.murmurHash3(Float2IntOpenCustomHashMap.this.strategy.hashCode(k)) & Float2IntOpenCustomHashMap.this.mask;
/* 478:    */        
/* 479:479 */        while (Float2IntOpenCustomHashMap.this.used[pos] != 0) {
/* 480:480 */          if (Float2IntOpenCustomHashMap.this.strategy.equals(Float2IntOpenCustomHashMap.this.key[pos], k)) return pos;
/* 481:481 */          pos = pos + 1 & Float2IntOpenCustomHashMap.this.mask;
/* 482:    */        }
/* 483:    */      }
/* 484:484 */      this.last = this.pos;
/* 485:    */      
/* 486:486 */      if (this.c != 0) {
/* 487:487 */        boolean[] used = Float2IntOpenCustomHashMap.this.used;
/* 488:488 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 489:    */      }
/* 490:    */      
/* 491:491 */      return this.last;
/* 492:    */    }
/* 493:    */    
/* 497:    */    protected final int shiftKeys(int pos)
/* 498:    */    {
/* 499:    */      int last;
/* 500:    */      
/* 502:    */      for (;;)
/* 503:    */      {
/* 504:504 */        pos = (last = pos) + 1 & Float2IntOpenCustomHashMap.this.mask;
/* 505:505 */        while (Float2IntOpenCustomHashMap.this.used[pos] != 0) {
/* 506:506 */          int slot = HashCommon.murmurHash3(Float2IntOpenCustomHashMap.this.strategy.hashCode(Float2IntOpenCustomHashMap.this.key[pos])) & Float2IntOpenCustomHashMap.this.mask;
/* 507:507 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 508:508 */          pos = pos + 1 & Float2IntOpenCustomHashMap.this.mask;
/* 509:    */        }
/* 510:510 */        if (Float2IntOpenCustomHashMap.this.used[pos] == 0) break;
/* 511:511 */        if (pos < last)
/* 512:    */        {
/* 513:513 */          if (this.wrapped == null) this.wrapped = new FloatArrayList();
/* 514:514 */          this.wrapped.add(Float2IntOpenCustomHashMap.this.key[pos]);
/* 515:    */        }
/* 516:516 */        Float2IntOpenCustomHashMap.this.key[last] = Float2IntOpenCustomHashMap.this.key[pos];
/* 517:517 */        Float2IntOpenCustomHashMap.this.value[last] = Float2IntOpenCustomHashMap.this.value[pos];
/* 518:    */      }
/* 519:519 */      Float2IntOpenCustomHashMap.this.used[last] = false;
/* 520:520 */      return last;
/* 521:    */    }
/* 522:    */    
/* 523:    */    public void remove() {
/* 524:524 */      if (this.last == -1) throw new IllegalStateException();
/* 525:525 */      if (this.pos < -1)
/* 526:    */      {
/* 527:527 */        Float2IntOpenCustomHashMap.this.remove(this.wrapped.getFloat(-this.pos - 2));
/* 528:528 */        this.last = -1;
/* 529:529 */        return;
/* 530:    */      }
/* 531:531 */      Float2IntOpenCustomHashMap.this.size -= 1;
/* 532:532 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 533:533 */        this.c += 1;
/* 534:534 */        nextEntry();
/* 535:    */      }
/* 536:536 */      this.last = -1;
/* 537:    */    }
/* 538:    */    
/* 539:    */    public int skip(int n) {
/* 540:540 */      int i = n;
/* 541:541 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 542:542 */      return n - i - 1;
/* 543:    */    } }
/* 544:    */  
/* 545:545 */  private class EntryIterator extends Float2IntOpenCustomHashMap.MapIterator implements ObjectIterator<Float2IntMap.Entry> { private EntryIterator() { super(null); }
/* 546:    */    
/* 547:    */    private Float2IntOpenCustomHashMap.MapEntry entry;
/* 548:548 */    public Float2IntMap.Entry next() { return this.entry = new Float2IntOpenCustomHashMap.MapEntry(Float2IntOpenCustomHashMap.this, nextEntry()); }
/* 549:    */    
/* 550:    */    public void remove()
/* 551:    */    {
/* 552:552 */      super.remove();
/* 553:553 */      Float2IntOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/* 554:    */    } }
/* 555:    */  
/* 556:556 */  private class FastEntryIterator extends Float2IntOpenCustomHashMap.MapIterator implements ObjectIterator<Float2IntMap.Entry> { private FastEntryIterator() { super(null); }
/* 557:557 */    final AbstractFloat2IntMap.BasicEntry entry = new AbstractFloat2IntMap.BasicEntry(0.0F, 0);
/* 558:    */    
/* 559:559 */    public AbstractFloat2IntMap.BasicEntry next() { int e = nextEntry();
/* 560:560 */      this.entry.key = Float2IntOpenCustomHashMap.this.key[e];
/* 561:561 */      this.entry.value = Float2IntOpenCustomHashMap.this.value[e];
/* 562:562 */      return this.entry;
/* 563:    */    } }
/* 564:    */  
/* 565:    */  private final class MapEntrySet extends AbstractObjectSet<Float2IntMap.Entry> implements Float2IntMap.FastEntrySet { private MapEntrySet() {}
/* 566:    */    
/* 567:567 */    public ObjectIterator<Float2IntMap.Entry> iterator() { return new Float2IntOpenCustomHashMap.EntryIterator(Float2IntOpenCustomHashMap.this, null); }
/* 568:    */    
/* 569:    */    public ObjectIterator<Float2IntMap.Entry> fastIterator() {
/* 570:570 */      return new Float2IntOpenCustomHashMap.FastEntryIterator(Float2IntOpenCustomHashMap.this, null);
/* 571:    */    }
/* 572:    */    
/* 573:    */    public boolean contains(Object o) {
/* 574:574 */      if (!(o instanceof Map.Entry)) return false;
/* 575:575 */      Map.Entry<Float, Integer> e = (Map.Entry)o;
/* 576:576 */      float k = ((Float)e.getKey()).floatValue();
/* 577:    */      
/* 578:578 */      int pos = HashCommon.murmurHash3(Float2IntOpenCustomHashMap.this.strategy.hashCode(k)) & Float2IntOpenCustomHashMap.this.mask;
/* 579:    */      
/* 580:580 */      while (Float2IntOpenCustomHashMap.this.used[pos] != 0) {
/* 581:581 */        if (Float2IntOpenCustomHashMap.this.strategy.equals(Float2IntOpenCustomHashMap.this.key[pos], k)) return Float2IntOpenCustomHashMap.this.value[pos] == ((Integer)e.getValue()).intValue();
/* 582:582 */        pos = pos + 1 & Float2IntOpenCustomHashMap.this.mask;
/* 583:    */      }
/* 584:584 */      return false;
/* 585:    */    }
/* 586:    */    
/* 587:    */    public boolean remove(Object o) {
/* 588:588 */      if (!(o instanceof Map.Entry)) return false;
/* 589:589 */      Map.Entry<Float, Integer> e = (Map.Entry)o;
/* 590:590 */      float k = ((Float)e.getKey()).floatValue();
/* 591:    */      
/* 592:592 */      int pos = HashCommon.murmurHash3(Float2IntOpenCustomHashMap.this.strategy.hashCode(k)) & Float2IntOpenCustomHashMap.this.mask;
/* 593:    */      
/* 594:594 */      while (Float2IntOpenCustomHashMap.this.used[pos] != 0) {
/* 595:595 */        if (Float2IntOpenCustomHashMap.this.strategy.equals(Float2IntOpenCustomHashMap.this.key[pos], k)) {
/* 596:596 */          Float2IntOpenCustomHashMap.this.remove(e.getKey());
/* 597:597 */          return true;
/* 598:    */        }
/* 599:599 */        pos = pos + 1 & Float2IntOpenCustomHashMap.this.mask;
/* 600:    */      }
/* 601:601 */      return false;
/* 602:    */    }
/* 603:    */    
/* 604:604 */    public int size() { return Float2IntOpenCustomHashMap.this.size; }
/* 605:    */    
/* 607:607 */    public void clear() { Float2IntOpenCustomHashMap.this.clear(); }
/* 608:    */  }
/* 609:    */  
/* 610:    */  public Float2IntMap.FastEntrySet float2IntEntrySet() {
/* 611:611 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 612:612 */    return this.entries;
/* 613:    */  }
/* 614:    */  
/* 617:    */  private final class KeyIterator
/* 618:    */    extends Float2IntOpenCustomHashMap.MapIterator
/* 619:    */    implements FloatIterator
/* 620:    */  {
/* 621:621 */    public KeyIterator() { super(null); }
/* 622:622 */    public float nextFloat() { return Float2IntOpenCustomHashMap.this.key[nextEntry()]; }
/* 623:623 */    public Float next() { return Float.valueOf(Float2IntOpenCustomHashMap.this.key[nextEntry()]); } }
/* 624:    */  
/* 625:    */  private final class KeySet extends AbstractFloatSet { private KeySet() {}
/* 626:    */    
/* 627:627 */    public FloatIterator iterator() { return new Float2IntOpenCustomHashMap.KeyIterator(Float2IntOpenCustomHashMap.this); }
/* 628:    */    
/* 629:    */    public int size() {
/* 630:630 */      return Float2IntOpenCustomHashMap.this.size;
/* 631:    */    }
/* 632:    */    
/* 633:633 */    public boolean contains(float k) { return Float2IntOpenCustomHashMap.this.containsKey(k); }
/* 634:    */    
/* 635:    */    public boolean remove(float k) {
/* 636:636 */      int oldSize = Float2IntOpenCustomHashMap.this.size;
/* 637:637 */      Float2IntOpenCustomHashMap.this.remove(k);
/* 638:638 */      return Float2IntOpenCustomHashMap.this.size != oldSize;
/* 639:    */    }
/* 640:    */    
/* 641:641 */    public void clear() { Float2IntOpenCustomHashMap.this.clear(); }
/* 642:    */  }
/* 643:    */  
/* 644:    */  public FloatSet keySet() {
/* 645:645 */    if (this.keys == null) this.keys = new KeySet(null);
/* 646:646 */    return this.keys;
/* 647:    */  }
/* 648:    */  
/* 651:    */  private final class ValueIterator
/* 652:    */    extends Float2IntOpenCustomHashMap.MapIterator
/* 653:    */    implements IntIterator
/* 654:    */  {
/* 655:655 */    public ValueIterator() { super(null); }
/* 656:656 */    public int nextInt() { return Float2IntOpenCustomHashMap.this.value[nextEntry()]; }
/* 657:657 */    public Integer next() { return Integer.valueOf(Float2IntOpenCustomHashMap.this.value[nextEntry()]); }
/* 658:    */  }
/* 659:    */  
/* 660:660 */  public IntCollection values() { if (this.values == null) { this.values = new AbstractIntCollection() {
/* 661:    */        public IntIterator iterator() {
/* 662:662 */          return new Float2IntOpenCustomHashMap.ValueIterator(Float2IntOpenCustomHashMap.this);
/* 663:    */        }
/* 664:    */        
/* 665:665 */        public int size() { return Float2IntOpenCustomHashMap.this.size; }
/* 666:    */        
/* 667:    */        public boolean contains(int v) {
/* 668:668 */          return Float2IntOpenCustomHashMap.this.containsValue(v);
/* 669:    */        }
/* 670:    */        
/* 671:671 */        public void clear() { Float2IntOpenCustomHashMap.this.clear(); }
/* 672:    */      };
/* 673:    */    }
/* 674:674 */    return this.values;
/* 675:    */  }
/* 676:    */  
/* 685:    */  @Deprecated
/* 686:    */  public boolean rehash()
/* 687:    */  {
/* 688:688 */    return true;
/* 689:    */  }
/* 690:    */  
/* 701:    */  public boolean trim()
/* 702:    */  {
/* 703:703 */    int l = HashCommon.arraySize(this.size, this.f);
/* 704:704 */    if (l >= this.n) return true;
/* 705:    */    try {
/* 706:706 */      rehash(l);
/* 707:    */    } catch (OutOfMemoryError cantDoIt) {
/* 708:708 */      return false; }
/* 709:709 */    return true;
/* 710:    */  }
/* 711:    */  
/* 728:    */  public boolean trim(int n)
/* 729:    */  {
/* 730:730 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 731:731 */    if (this.n <= l) return true;
/* 732:    */    try {
/* 733:733 */      rehash(l);
/* 734:    */    } catch (OutOfMemoryError cantDoIt) {
/* 735:735 */      return false; }
/* 736:736 */    return true;
/* 737:    */  }
/* 738:    */  
/* 747:    */  protected void rehash(int newN)
/* 748:    */  {
/* 749:749 */    int i = 0;
/* 750:750 */    boolean[] used = this.used;
/* 751:    */    
/* 752:752 */    float[] key = this.key;
/* 753:753 */    int[] value = this.value;
/* 754:754 */    int newMask = newN - 1;
/* 755:755 */    float[] newKey = new float[newN];
/* 756:756 */    int[] newValue = new int[newN];
/* 757:757 */    boolean[] newUsed = new boolean[newN];
/* 758:758 */    for (int j = this.size; j-- != 0;) {
/* 759:759 */      while (used[i] == 0) i++;
/* 760:760 */      float k = key[i];
/* 761:761 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 762:762 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 763:763 */      newUsed[pos] = true;
/* 764:764 */      newKey[pos] = k;
/* 765:765 */      newValue[pos] = value[i];
/* 766:766 */      i++;
/* 767:    */    }
/* 768:768 */    this.n = newN;
/* 769:769 */    this.mask = newMask;
/* 770:770 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 771:771 */    this.key = newKey;
/* 772:772 */    this.value = newValue;
/* 773:773 */    this.used = newUsed;
/* 774:    */  }
/* 775:    */  
/* 779:    */  public Float2IntOpenCustomHashMap clone()
/* 780:    */  {
/* 781:    */    Float2IntOpenCustomHashMap c;
/* 782:    */    
/* 784:    */    try
/* 785:    */    {
/* 786:786 */      c = (Float2IntOpenCustomHashMap)super.clone();
/* 787:    */    }
/* 788:    */    catch (CloneNotSupportedException cantHappen) {
/* 789:789 */      throw new InternalError();
/* 790:    */    }
/* 791:791 */    c.keys = null;
/* 792:792 */    c.values = null;
/* 793:793 */    c.entries = null;
/* 794:794 */    c.key = ((float[])this.key.clone());
/* 795:795 */    c.value = ((int[])this.value.clone());
/* 796:796 */    c.used = ((boolean[])this.used.clone());
/* 797:797 */    c.strategy = this.strategy;
/* 798:798 */    return c;
/* 799:    */  }
/* 800:    */  
/* 808:    */  public int hashCode()
/* 809:    */  {
/* 810:810 */    int h = 0;
/* 811:811 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 812:812 */      while (this.used[i] == 0) i++;
/* 813:813 */      t = this.strategy.hashCode(this.key[i]);
/* 814:814 */      t ^= this.value[i];
/* 815:815 */      h += t;
/* 816:816 */      i++;
/* 817:    */    }
/* 818:818 */    return h;
/* 819:    */  }
/* 820:    */  
/* 821:821 */  private void writeObject(ObjectOutputStream s) throws IOException { float[] key = this.key;
/* 822:822 */    int[] value = this.value;
/* 823:823 */    MapIterator i = new MapIterator(null);
/* 824:824 */    s.defaultWriteObject();
/* 825:825 */    for (int j = this.size; j-- != 0;) {
/* 826:826 */      int e = i.nextEntry();
/* 827:827 */      s.writeFloat(key[e]);
/* 828:828 */      s.writeInt(value[e]);
/* 829:    */    }
/* 830:    */  }
/* 831:    */  
/* 832:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 833:833 */    s.defaultReadObject();
/* 834:834 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 835:835 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 836:836 */    this.mask = (this.n - 1);
/* 837:837 */    float[] key = this.key = new float[this.n];
/* 838:838 */    int[] value = this.value = new int[this.n];
/* 839:839 */    boolean[] used = this.used = new boolean[this.n];
/* 840:    */    
/* 842:842 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 843:843 */      float k = s.readFloat();
/* 844:844 */      int v = s.readInt();
/* 845:845 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 846:846 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 847:847 */      used[pos] = true;
/* 848:848 */      key[pos] = k;
/* 849:849 */      value[pos] = v;
/* 850:    */    }
/* 851:    */  }
/* 852:    */  
/* 853:    */  private void checkTable() {}
/* 854:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2IntOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */