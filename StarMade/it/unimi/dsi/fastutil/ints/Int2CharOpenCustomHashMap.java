/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*   7:    */import it.unimi.dsi.fastutil.chars.CharCollection;
/*   8:    */import it.unimi.dsi.fastutil.chars.CharIterator;
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
/*  90:    */public class Int2CharOpenCustomHashMap
/*  91:    */  extends AbstractInt2CharMap
/*  92:    */  implements Serializable, Cloneable, Hash
/*  93:    */{
/*  94:    */  public static final long serialVersionUID = 0L;
/*  95:    */  private static final boolean ASSERTS = false;
/*  96:    */  protected transient int[] key;
/*  97:    */  protected transient char[] value;
/*  98:    */  protected transient boolean[] used;
/*  99:    */  protected final float f;
/* 100:    */  protected transient int n;
/* 101:    */  protected transient int maxFill;
/* 102:    */  protected transient int mask;
/* 103:    */  protected int size;
/* 104:    */  protected volatile transient Int2CharMap.FastEntrySet entries;
/* 105:    */  protected volatile transient IntSet keys;
/* 106:    */  protected volatile transient CharCollection values;
/* 107:    */  protected IntHash.Strategy strategy;
/* 108:    */  
/* 109:    */  public Int2CharOpenCustomHashMap(int expected, float f, IntHash.Strategy strategy)
/* 110:    */  {
/* 111:111 */    this.strategy = strategy;
/* 112:112 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 113:113 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 114:114 */    this.f = f;
/* 115:115 */    this.n = HashCommon.arraySize(expected, f);
/* 116:116 */    this.mask = (this.n - 1);
/* 117:117 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 118:118 */    this.key = new int[this.n];
/* 119:119 */    this.value = new char[this.n];
/* 120:120 */    this.used = new boolean[this.n];
/* 121:    */  }
/* 122:    */  
/* 126:    */  public Int2CharOpenCustomHashMap(int expected, IntHash.Strategy strategy)
/* 127:    */  {
/* 128:128 */    this(expected, 0.75F, strategy);
/* 129:    */  }
/* 130:    */  
/* 133:    */  public Int2CharOpenCustomHashMap(IntHash.Strategy strategy)
/* 134:    */  {
/* 135:135 */    this(16, 0.75F, strategy);
/* 136:    */  }
/* 137:    */  
/* 142:    */  public Int2CharOpenCustomHashMap(Map<? extends Integer, ? extends Character> m, float f, IntHash.Strategy strategy)
/* 143:    */  {
/* 144:144 */    this(m.size(), f, strategy);
/* 145:145 */    putAll(m);
/* 146:    */  }
/* 147:    */  
/* 151:    */  public Int2CharOpenCustomHashMap(Map<? extends Integer, ? extends Character> m, IntHash.Strategy strategy)
/* 152:    */  {
/* 153:153 */    this(m, 0.75F, strategy);
/* 154:    */  }
/* 155:    */  
/* 160:    */  public Int2CharOpenCustomHashMap(Int2CharMap m, float f, IntHash.Strategy strategy)
/* 161:    */  {
/* 162:162 */    this(m.size(), f, strategy);
/* 163:163 */    putAll(m);
/* 164:    */  }
/* 165:    */  
/* 169:    */  public Int2CharOpenCustomHashMap(Int2CharMap m, IntHash.Strategy strategy)
/* 170:    */  {
/* 171:171 */    this(m, 0.75F, strategy);
/* 172:    */  }
/* 173:    */  
/* 180:    */  public Int2CharOpenCustomHashMap(int[] k, char[] v, float f, IntHash.Strategy strategy)
/* 181:    */  {
/* 182:182 */    this(k.length, f, strategy);
/* 183:183 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 184:184 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 185:    */    }
/* 186:    */  }
/* 187:    */  
/* 192:    */  public Int2CharOpenCustomHashMap(int[] k, char[] v, IntHash.Strategy strategy)
/* 193:    */  {
/* 194:194 */    this(k, v, 0.75F, strategy);
/* 195:    */  }
/* 196:    */  
/* 199:    */  public IntHash.Strategy strategy()
/* 200:    */  {
/* 201:201 */    return this.strategy;
/* 202:    */  }
/* 203:    */  
/* 207:    */  public char put(int k, char v)
/* 208:    */  {
/* 209:209 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 210:    */    
/* 211:211 */    while (this.used[pos] != 0) {
/* 212:212 */      if (this.strategy.equals(this.key[pos], k)) {
/* 213:213 */        char oldValue = this.value[pos];
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
/* 227:227 */  public Character put(Integer ok, Character ov) { char v = ov.charValue();
/* 228:228 */    int k = ok.intValue();
/* 229:    */    
/* 230:230 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 231:    */    
/* 232:232 */    while (this.used[pos] != 0) {
/* 233:233 */      if (this.strategy.equals(this.key[pos], k)) {
/* 234:234 */        Character oldValue = Character.valueOf(this.value[pos]);
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
/* 250:    */  protected final int shiftKeys(int pos)
/* 251:    */  {
/* 252:    */    int last;
/* 253:    */    
/* 255:    */    for (;;)
/* 256:    */    {
/* 257:257 */      pos = (last = pos) + 1 & this.mask;
/* 258:258 */      while (this.used[pos] != 0) {
/* 259:259 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 260:260 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 261:261 */        pos = pos + 1 & this.mask;
/* 262:    */      }
/* 263:263 */      if (this.used[pos] == 0) break;
/* 264:264 */      this.key[last] = this.key[pos];
/* 265:265 */      this.value[last] = this.value[pos];
/* 266:    */    }
/* 267:267 */    this.used[last] = false;
/* 268:268 */    return last;
/* 269:    */  }
/* 270:    */  
/* 271:    */  public char remove(int k)
/* 272:    */  {
/* 273:273 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 274:    */    
/* 275:275 */    while (this.used[pos] != 0) {
/* 276:276 */      if (this.strategy.equals(this.key[pos], k)) {
/* 277:277 */        this.size -= 1;
/* 278:278 */        char v = this.value[pos];
/* 279:279 */        shiftKeys(pos);
/* 280:280 */        return v;
/* 281:    */      }
/* 282:282 */      pos = pos + 1 & this.mask;
/* 283:    */    }
/* 284:284 */    return this.defRetValue;
/* 285:    */  }
/* 286:    */  
/* 287:    */  public Character remove(Object ok) {
/* 288:288 */    int k = ((Integer)ok).intValue();
/* 289:    */    
/* 290:290 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 291:    */    
/* 292:292 */    while (this.used[pos] != 0) {
/* 293:293 */      if (this.strategy.equals(this.key[pos], k)) {
/* 294:294 */        this.size -= 1;
/* 295:295 */        char v = this.value[pos];
/* 296:296 */        shiftKeys(pos);
/* 297:297 */        return Character.valueOf(v);
/* 298:    */      }
/* 299:299 */      pos = pos + 1 & this.mask;
/* 300:    */    }
/* 301:301 */    return null;
/* 302:    */  }
/* 303:    */  
/* 304:304 */  public Character get(Integer ok) { int k = ok.intValue();
/* 305:    */    
/* 306:306 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 307:    */    
/* 308:308 */    while (this.used[pos] != 0) {
/* 309:309 */      if (this.strategy.equals(this.key[pos], k)) return Character.valueOf(this.value[pos]);
/* 310:310 */      pos = pos + 1 & this.mask;
/* 311:    */    }
/* 312:312 */    return null;
/* 313:    */  }
/* 314:    */  
/* 315:    */  public char get(int k)
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
/* 326:    */  public boolean containsKey(int k)
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
/* 337:337 */  public boolean containsValue(char v) { char[] value = this.value;
/* 338:338 */    boolean[] used = this.used;
/* 339:339 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/* 340:340 */    return false;
/* 341:    */  }
/* 342:    */  
/* 347:    */  public void clear()
/* 348:    */  {
/* 349:349 */    if (this.size == 0) return;
/* 350:350 */    this.size = 0;
/* 351:351 */    BooleanArrays.fill(this.used, false);
/* 352:    */  }
/* 353:    */  
/* 354:    */  public int size() {
/* 355:355 */    return this.size;
/* 356:    */  }
/* 357:    */  
/* 358:358 */  public boolean isEmpty() { return this.size == 0; }
/* 359:    */  
/* 365:    */  @Deprecated
/* 366:    */  public void growthFactor(int growthFactor) {}
/* 367:    */  
/* 372:    */  @Deprecated
/* 373:    */  public int growthFactor()
/* 374:    */  {
/* 375:375 */    return 16;
/* 376:    */  }
/* 377:    */  
/* 378:    */  private final class MapEntry
/* 379:    */    implements Int2CharMap.Entry, Map.Entry<Integer, Character>
/* 380:    */  {
/* 381:    */    private int index;
/* 382:    */    
/* 383:    */    MapEntry(int index)
/* 384:    */    {
/* 385:385 */      this.index = index;
/* 386:    */    }
/* 387:    */    
/* 388:388 */    public Integer getKey() { return Integer.valueOf(Int2CharOpenCustomHashMap.this.key[this.index]); }
/* 389:    */    
/* 390:    */    public int getIntKey() {
/* 391:391 */      return Int2CharOpenCustomHashMap.this.key[this.index];
/* 392:    */    }
/* 393:    */    
/* 394:394 */    public Character getValue() { return Character.valueOf(Int2CharOpenCustomHashMap.this.value[this.index]); }
/* 395:    */    
/* 397:397 */    public char getCharValue() { return Int2CharOpenCustomHashMap.this.value[this.index]; }
/* 398:    */    
/* 399:    */    public char setValue(char v) {
/* 400:400 */      char oldValue = Int2CharOpenCustomHashMap.this.value[this.index];
/* 401:401 */      Int2CharOpenCustomHashMap.this.value[this.index] = v;
/* 402:402 */      return oldValue;
/* 403:    */    }
/* 404:    */    
/* 405:405 */    public Character setValue(Character v) { return Character.valueOf(setValue(v.charValue())); }
/* 406:    */    
/* 407:    */    public boolean equals(Object o)
/* 408:    */    {
/* 409:409 */      if (!(o instanceof Map.Entry)) return false;
/* 410:410 */      Map.Entry<Integer, Character> e = (Map.Entry)o;
/* 411:411 */      return (Int2CharOpenCustomHashMap.this.strategy.equals(Int2CharOpenCustomHashMap.this.key[this.index], ((Integer)e.getKey()).intValue())) && (Int2CharOpenCustomHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/* 412:    */    }
/* 413:    */    
/* 414:414 */    public int hashCode() { return Int2CharOpenCustomHashMap.this.strategy.hashCode(Int2CharOpenCustomHashMap.this.key[this.index]) ^ Int2CharOpenCustomHashMap.this.value[this.index]; }
/* 415:    */    
/* 417:417 */    public String toString() { return Int2CharOpenCustomHashMap.this.key[this.index] + "=>" + Int2CharOpenCustomHashMap.this.value[this.index]; } }
/* 418:    */  
/* 419:    */  private class MapIterator { int pos;
/* 420:    */    int last;
/* 421:    */    int c;
/* 422:    */    IntArrayList wrapped;
/* 423:    */    
/* 424:424 */    private MapIterator() { this.pos = Int2CharOpenCustomHashMap.this.n;
/* 425:    */      
/* 427:427 */      this.last = -1;
/* 428:    */      
/* 429:429 */      this.c = Int2CharOpenCustomHashMap.this.size;
/* 430:    */      
/* 434:434 */      boolean[] used = Int2CharOpenCustomHashMap.this.used;
/* 435:435 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 436:    */    }
/* 437:    */    
/* 438:438 */    public boolean hasNext() { return this.c != 0; }
/* 439:    */    
/* 440:    */    public int nextEntry() {
/* 441:441 */      if (!hasNext()) throw new NoSuchElementException();
/* 442:442 */      this.c -= 1;
/* 443:    */      
/* 444:444 */      if (this.pos < 0) {
/* 445:445 */        int k = this.wrapped.getInt(-(this.last = --this.pos) - 2);
/* 446:    */        
/* 447:447 */        int pos = HashCommon.murmurHash3(Int2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Int2CharOpenCustomHashMap.this.mask;
/* 448:    */        
/* 449:449 */        while (Int2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 450:450 */          if (Int2CharOpenCustomHashMap.this.strategy.equals(Int2CharOpenCustomHashMap.this.key[pos], k)) return pos;
/* 451:451 */          pos = pos + 1 & Int2CharOpenCustomHashMap.this.mask;
/* 452:    */        }
/* 453:    */      }
/* 454:454 */      this.last = this.pos;
/* 455:    */      
/* 456:456 */      if (this.c != 0) {
/* 457:457 */        boolean[] used = Int2CharOpenCustomHashMap.this.used;
/* 458:458 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 459:    */      }
/* 460:    */      
/* 461:461 */      return this.last;
/* 462:    */    }
/* 463:    */    
/* 467:    */    protected final int shiftKeys(int pos)
/* 468:    */    {
/* 469:    */      int last;
/* 470:    */      
/* 472:    */      for (;;)
/* 473:    */      {
/* 474:474 */        pos = (last = pos) + 1 & Int2CharOpenCustomHashMap.this.mask;
/* 475:475 */        while (Int2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 476:476 */          int slot = HashCommon.murmurHash3(Int2CharOpenCustomHashMap.this.strategy.hashCode(Int2CharOpenCustomHashMap.this.key[pos])) & Int2CharOpenCustomHashMap.this.mask;
/* 477:477 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 478:478 */          pos = pos + 1 & Int2CharOpenCustomHashMap.this.mask;
/* 479:    */        }
/* 480:480 */        if (Int2CharOpenCustomHashMap.this.used[pos] == 0) break;
/* 481:481 */        if (pos < last)
/* 482:    */        {
/* 483:483 */          if (this.wrapped == null) this.wrapped = new IntArrayList();
/* 484:484 */          this.wrapped.add(Int2CharOpenCustomHashMap.this.key[pos]);
/* 485:    */        }
/* 486:486 */        Int2CharOpenCustomHashMap.this.key[last] = Int2CharOpenCustomHashMap.this.key[pos];
/* 487:487 */        Int2CharOpenCustomHashMap.this.value[last] = Int2CharOpenCustomHashMap.this.value[pos];
/* 488:    */      }
/* 489:489 */      Int2CharOpenCustomHashMap.this.used[last] = false;
/* 490:490 */      return last;
/* 491:    */    }
/* 492:    */    
/* 493:    */    public void remove() {
/* 494:494 */      if (this.last == -1) throw new IllegalStateException();
/* 495:495 */      if (this.pos < -1)
/* 496:    */      {
/* 497:497 */        Int2CharOpenCustomHashMap.this.remove(this.wrapped.getInt(-this.pos - 2));
/* 498:498 */        this.last = -1;
/* 499:499 */        return;
/* 500:    */      }
/* 501:501 */      Int2CharOpenCustomHashMap.this.size -= 1;
/* 502:502 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 503:503 */        this.c += 1;
/* 504:504 */        nextEntry();
/* 505:    */      }
/* 506:506 */      this.last = -1;
/* 507:    */    }
/* 508:    */    
/* 509:    */    public int skip(int n) {
/* 510:510 */      int i = n;
/* 511:511 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 512:512 */      return n - i - 1;
/* 513:    */    } }
/* 514:    */  
/* 515:515 */  private class EntryIterator extends Int2CharOpenCustomHashMap.MapIterator implements ObjectIterator<Int2CharMap.Entry> { private EntryIterator() { super(null); }
/* 516:    */    
/* 517:    */    private Int2CharOpenCustomHashMap.MapEntry entry;
/* 518:518 */    public Int2CharMap.Entry next() { return this.entry = new Int2CharOpenCustomHashMap.MapEntry(Int2CharOpenCustomHashMap.this, nextEntry()); }
/* 519:    */    
/* 520:    */    public void remove()
/* 521:    */    {
/* 522:522 */      super.remove();
/* 523:523 */      Int2CharOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/* 524:    */    } }
/* 525:    */  
/* 526:526 */  private class FastEntryIterator extends Int2CharOpenCustomHashMap.MapIterator implements ObjectIterator<Int2CharMap.Entry> { private FastEntryIterator() { super(null); }
/* 527:527 */    final AbstractInt2CharMap.BasicEntry entry = new AbstractInt2CharMap.BasicEntry(0, '\000');
/* 528:    */    
/* 529:529 */    public AbstractInt2CharMap.BasicEntry next() { int e = nextEntry();
/* 530:530 */      this.entry.key = Int2CharOpenCustomHashMap.this.key[e];
/* 531:531 */      this.entry.value = Int2CharOpenCustomHashMap.this.value[e];
/* 532:532 */      return this.entry;
/* 533:    */    } }
/* 534:    */  
/* 535:    */  private final class MapEntrySet extends AbstractObjectSet<Int2CharMap.Entry> implements Int2CharMap.FastEntrySet { private MapEntrySet() {}
/* 536:    */    
/* 537:537 */    public ObjectIterator<Int2CharMap.Entry> iterator() { return new Int2CharOpenCustomHashMap.EntryIterator(Int2CharOpenCustomHashMap.this, null); }
/* 538:    */    
/* 539:    */    public ObjectIterator<Int2CharMap.Entry> fastIterator() {
/* 540:540 */      return new Int2CharOpenCustomHashMap.FastEntryIterator(Int2CharOpenCustomHashMap.this, null);
/* 541:    */    }
/* 542:    */    
/* 543:    */    public boolean contains(Object o) {
/* 544:544 */      if (!(o instanceof Map.Entry)) return false;
/* 545:545 */      Map.Entry<Integer, Character> e = (Map.Entry)o;
/* 546:546 */      int k = ((Integer)e.getKey()).intValue();
/* 547:    */      
/* 548:548 */      int pos = HashCommon.murmurHash3(Int2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Int2CharOpenCustomHashMap.this.mask;
/* 549:    */      
/* 550:550 */      while (Int2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 551:551 */        if (Int2CharOpenCustomHashMap.this.strategy.equals(Int2CharOpenCustomHashMap.this.key[pos], k)) return Int2CharOpenCustomHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/* 552:552 */        pos = pos + 1 & Int2CharOpenCustomHashMap.this.mask;
/* 553:    */      }
/* 554:554 */      return false;
/* 555:    */    }
/* 556:    */    
/* 557:    */    public boolean remove(Object o) {
/* 558:558 */      if (!(o instanceof Map.Entry)) return false;
/* 559:559 */      Map.Entry<Integer, Character> e = (Map.Entry)o;
/* 560:560 */      int k = ((Integer)e.getKey()).intValue();
/* 561:    */      
/* 562:562 */      int pos = HashCommon.murmurHash3(Int2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Int2CharOpenCustomHashMap.this.mask;
/* 563:    */      
/* 564:564 */      while (Int2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 565:565 */        if (Int2CharOpenCustomHashMap.this.strategy.equals(Int2CharOpenCustomHashMap.this.key[pos], k)) {
/* 566:566 */          Int2CharOpenCustomHashMap.this.remove(e.getKey());
/* 567:567 */          return true;
/* 568:    */        }
/* 569:569 */        pos = pos + 1 & Int2CharOpenCustomHashMap.this.mask;
/* 570:    */      }
/* 571:571 */      return false;
/* 572:    */    }
/* 573:    */    
/* 574:574 */    public int size() { return Int2CharOpenCustomHashMap.this.size; }
/* 575:    */    
/* 577:577 */    public void clear() { Int2CharOpenCustomHashMap.this.clear(); }
/* 578:    */  }
/* 579:    */  
/* 580:    */  public Int2CharMap.FastEntrySet int2CharEntrySet() {
/* 581:581 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 582:582 */    return this.entries;
/* 583:    */  }
/* 584:    */  
/* 587:    */  private final class KeyIterator
/* 588:    */    extends Int2CharOpenCustomHashMap.MapIterator
/* 589:    */    implements IntIterator
/* 590:    */  {
/* 591:591 */    public KeyIterator() { super(null); }
/* 592:592 */    public int nextInt() { return Int2CharOpenCustomHashMap.this.key[nextEntry()]; }
/* 593:593 */    public Integer next() { return Integer.valueOf(Int2CharOpenCustomHashMap.this.key[nextEntry()]); } }
/* 594:    */  
/* 595:    */  private final class KeySet extends AbstractIntSet { private KeySet() {}
/* 596:    */    
/* 597:597 */    public IntIterator iterator() { return new Int2CharOpenCustomHashMap.KeyIterator(Int2CharOpenCustomHashMap.this); }
/* 598:    */    
/* 599:    */    public int size() {
/* 600:600 */      return Int2CharOpenCustomHashMap.this.size;
/* 601:    */    }
/* 602:    */    
/* 603:603 */    public boolean contains(int k) { return Int2CharOpenCustomHashMap.this.containsKey(k); }
/* 604:    */    
/* 605:    */    public boolean remove(int k) {
/* 606:606 */      int oldSize = Int2CharOpenCustomHashMap.this.size;
/* 607:607 */      Int2CharOpenCustomHashMap.this.remove(k);
/* 608:608 */      return Int2CharOpenCustomHashMap.this.size != oldSize;
/* 609:    */    }
/* 610:    */    
/* 611:611 */    public void clear() { Int2CharOpenCustomHashMap.this.clear(); }
/* 612:    */  }
/* 613:    */  
/* 614:    */  public IntSet keySet() {
/* 615:615 */    if (this.keys == null) this.keys = new KeySet(null);
/* 616:616 */    return this.keys;
/* 617:    */  }
/* 618:    */  
/* 621:    */  private final class ValueIterator
/* 622:    */    extends Int2CharOpenCustomHashMap.MapIterator
/* 623:    */    implements CharIterator
/* 624:    */  {
/* 625:625 */    public ValueIterator() { super(null); }
/* 626:626 */    public char nextChar() { return Int2CharOpenCustomHashMap.this.value[nextEntry()]; }
/* 627:627 */    public Character next() { return Character.valueOf(Int2CharOpenCustomHashMap.this.value[nextEntry()]); }
/* 628:    */  }
/* 629:    */  
/* 630:630 */  public CharCollection values() { if (this.values == null) { this.values = new AbstractCharCollection() {
/* 631:    */        public CharIterator iterator() {
/* 632:632 */          return new Int2CharOpenCustomHashMap.ValueIterator(Int2CharOpenCustomHashMap.this);
/* 633:    */        }
/* 634:    */        
/* 635:635 */        public int size() { return Int2CharOpenCustomHashMap.this.size; }
/* 636:    */        
/* 637:    */        public boolean contains(char v) {
/* 638:638 */          return Int2CharOpenCustomHashMap.this.containsValue(v);
/* 639:    */        }
/* 640:    */        
/* 641:641 */        public void clear() { Int2CharOpenCustomHashMap.this.clear(); }
/* 642:    */      };
/* 643:    */    }
/* 644:644 */    return this.values;
/* 645:    */  }
/* 646:    */  
/* 655:    */  @Deprecated
/* 656:    */  public boolean rehash()
/* 657:    */  {
/* 658:658 */    return true;
/* 659:    */  }
/* 660:    */  
/* 671:    */  public boolean trim()
/* 672:    */  {
/* 673:673 */    int l = HashCommon.arraySize(this.size, this.f);
/* 674:674 */    if (l >= this.n) return true;
/* 675:    */    try {
/* 676:676 */      rehash(l);
/* 677:    */    } catch (OutOfMemoryError cantDoIt) {
/* 678:678 */      return false; }
/* 679:679 */    return true;
/* 680:    */  }
/* 681:    */  
/* 698:    */  public boolean trim(int n)
/* 699:    */  {
/* 700:700 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 701:701 */    if (this.n <= l) return true;
/* 702:    */    try {
/* 703:703 */      rehash(l);
/* 704:    */    } catch (OutOfMemoryError cantDoIt) {
/* 705:705 */      return false; }
/* 706:706 */    return true;
/* 707:    */  }
/* 708:    */  
/* 717:    */  protected void rehash(int newN)
/* 718:    */  {
/* 719:719 */    int i = 0;
/* 720:720 */    boolean[] used = this.used;
/* 721:    */    
/* 722:722 */    int[] key = this.key;
/* 723:723 */    char[] value = this.value;
/* 724:724 */    int newMask = newN - 1;
/* 725:725 */    int[] newKey = new int[newN];
/* 726:726 */    char[] newValue = new char[newN];
/* 727:727 */    boolean[] newUsed = new boolean[newN];
/* 728:728 */    for (int j = this.size; j-- != 0;) {
/* 729:729 */      while (used[i] == 0) i++;
/* 730:730 */      int k = key[i];
/* 731:731 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 732:732 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 733:733 */      newUsed[pos] = true;
/* 734:734 */      newKey[pos] = k;
/* 735:735 */      newValue[pos] = value[i];
/* 736:736 */      i++;
/* 737:    */    }
/* 738:738 */    this.n = newN;
/* 739:739 */    this.mask = newMask;
/* 740:740 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 741:741 */    this.key = newKey;
/* 742:742 */    this.value = newValue;
/* 743:743 */    this.used = newUsed;
/* 744:    */  }
/* 745:    */  
/* 749:    */  public Int2CharOpenCustomHashMap clone()
/* 750:    */  {
/* 751:    */    Int2CharOpenCustomHashMap c;
/* 752:    */    
/* 754:    */    try
/* 755:    */    {
/* 756:756 */      c = (Int2CharOpenCustomHashMap)super.clone();
/* 757:    */    }
/* 758:    */    catch (CloneNotSupportedException cantHappen) {
/* 759:759 */      throw new InternalError();
/* 760:    */    }
/* 761:761 */    c.keys = null;
/* 762:762 */    c.values = null;
/* 763:763 */    c.entries = null;
/* 764:764 */    c.key = ((int[])this.key.clone());
/* 765:765 */    c.value = ((char[])this.value.clone());
/* 766:766 */    c.used = ((boolean[])this.used.clone());
/* 767:767 */    c.strategy = this.strategy;
/* 768:768 */    return c;
/* 769:    */  }
/* 770:    */  
/* 778:    */  public int hashCode()
/* 779:    */  {
/* 780:780 */    int h = 0;
/* 781:781 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 782:782 */      while (this.used[i] == 0) i++;
/* 783:783 */      t = this.strategy.hashCode(this.key[i]);
/* 784:784 */      t ^= this.value[i];
/* 785:785 */      h += t;
/* 786:786 */      i++;
/* 787:    */    }
/* 788:788 */    return h;
/* 789:    */  }
/* 790:    */  
/* 791:791 */  private void writeObject(ObjectOutputStream s) throws IOException { int[] key = this.key;
/* 792:792 */    char[] value = this.value;
/* 793:793 */    MapIterator i = new MapIterator(null);
/* 794:794 */    s.defaultWriteObject();
/* 795:795 */    for (int j = this.size; j-- != 0;) {
/* 796:796 */      int e = i.nextEntry();
/* 797:797 */      s.writeInt(key[e]);
/* 798:798 */      s.writeChar(value[e]);
/* 799:    */    }
/* 800:    */  }
/* 801:    */  
/* 802:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 803:803 */    s.defaultReadObject();
/* 804:804 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 805:805 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 806:806 */    this.mask = (this.n - 1);
/* 807:807 */    int[] key = this.key = new int[this.n];
/* 808:808 */    char[] value = this.value = new char[this.n];
/* 809:809 */    boolean[] used = this.used = new boolean[this.n];
/* 810:    */    
/* 812:812 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 813:813 */      int k = s.readInt();
/* 814:814 */      char v = s.readChar();
/* 815:815 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 816:816 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 817:817 */      used[pos] = true;
/* 818:818 */      key[pos] = k;
/* 819:819 */      value[pos] = v;
/* 820:    */    }
/* 821:    */  }
/* 822:    */  
/* 823:    */  private void checkTable() {}
/* 824:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2CharOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */