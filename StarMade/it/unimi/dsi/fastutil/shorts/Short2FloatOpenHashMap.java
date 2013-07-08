/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*   7:    */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*   8:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
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
/*  87:    */public class Short2FloatOpenHashMap
/*  88:    */  extends AbstractShort2FloatMap
/*  89:    */  implements Serializable, Cloneable, Hash
/*  90:    */{
/*  91:    */  public static final long serialVersionUID = 0L;
/*  92:    */  private static final boolean ASSERTS = false;
/*  93:    */  protected transient short[] key;
/*  94:    */  protected transient float[] value;
/*  95:    */  protected transient boolean[] used;
/*  96:    */  protected final float f;
/*  97:    */  protected transient int n;
/*  98:    */  protected transient int maxFill;
/*  99:    */  protected transient int mask;
/* 100:    */  protected int size;
/* 101:    */  protected volatile transient Short2FloatMap.FastEntrySet entries;
/* 102:    */  protected volatile transient ShortSet keys;
/* 103:    */  protected volatile transient FloatCollection values;
/* 104:    */  
/* 105:    */  public Short2FloatOpenHashMap(int expected, float f)
/* 106:    */  {
/* 107:107 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 108:108 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 109:109 */    this.f = f;
/* 110:110 */    this.n = HashCommon.arraySize(expected, f);
/* 111:111 */    this.mask = (this.n - 1);
/* 112:112 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 113:113 */    this.key = new short[this.n];
/* 114:114 */    this.value = new float[this.n];
/* 115:115 */    this.used = new boolean[this.n];
/* 116:    */  }
/* 117:    */  
/* 120:    */  public Short2FloatOpenHashMap(int expected)
/* 121:    */  {
/* 122:122 */    this(expected, 0.75F);
/* 123:    */  }
/* 124:    */  
/* 126:    */  public Short2FloatOpenHashMap()
/* 127:    */  {
/* 128:128 */    this(16, 0.75F);
/* 129:    */  }
/* 130:    */  
/* 134:    */  public Short2FloatOpenHashMap(Map<? extends Short, ? extends Float> m, float f)
/* 135:    */  {
/* 136:136 */    this(m.size(), f);
/* 137:137 */    putAll(m);
/* 138:    */  }
/* 139:    */  
/* 142:    */  public Short2FloatOpenHashMap(Map<? extends Short, ? extends Float> m)
/* 143:    */  {
/* 144:144 */    this(m, 0.75F);
/* 145:    */  }
/* 146:    */  
/* 150:    */  public Short2FloatOpenHashMap(Short2FloatMap m, float f)
/* 151:    */  {
/* 152:152 */    this(m.size(), f);
/* 153:153 */    putAll(m);
/* 154:    */  }
/* 155:    */  
/* 158:    */  public Short2FloatOpenHashMap(Short2FloatMap m)
/* 159:    */  {
/* 160:160 */    this(m, 0.75F);
/* 161:    */  }
/* 162:    */  
/* 168:    */  public Short2FloatOpenHashMap(short[] k, float[] v, float f)
/* 169:    */  {
/* 170:170 */    this(k.length, f);
/* 171:171 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 172:172 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 179:    */  public Short2FloatOpenHashMap(short[] k, float[] v)
/* 180:    */  {
/* 181:181 */    this(k, v, 0.75F);
/* 182:    */  }
/* 183:    */  
/* 187:    */  public float put(short k, float v)
/* 188:    */  {
/* 189:189 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 190:    */    
/* 191:191 */    while (this.used[pos] != 0) {
/* 192:192 */      if (this.key[pos] == k) {
/* 193:193 */        float oldValue = this.value[pos];
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
/* 207:207 */  public Float put(Short ok, Float ov) { float v = ov.floatValue();
/* 208:208 */    short k = ok.shortValue();
/* 209:    */    
/* 210:210 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 211:    */    
/* 212:212 */    while (this.used[pos] != 0) {
/* 213:213 */      if (this.key[pos] == k) {
/* 214:214 */        Float oldValue = Float.valueOf(this.value[pos]);
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
/* 238:    */  public float add(short k, float incr)
/* 239:    */  {
/* 240:240 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 241:    */    
/* 242:242 */    while (this.used[pos] != 0) {
/* 243:243 */      if (this.key[pos] == k) {
/* 244:244 */        float oldValue = this.value[pos];
/* 245:245 */        this.value[pos] += incr;
/* 246:246 */        return oldValue;
/* 247:    */      }
/* 248:248 */      pos = pos + 1 & this.mask;
/* 249:    */    }
/* 250:250 */    this.used[pos] = true;
/* 251:251 */    this.key[pos] = k;
/* 252:252 */    this.value[pos] = (this.defRetValue + incr);
/* 253:253 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 254:    */    }
/* 255:255 */    return this.defRetValue;
/* 256:    */  }
/* 257:    */  
/* 260:    */  protected final int shiftKeys(int pos)
/* 261:    */  {
/* 262:    */    int last;
/* 263:    */    
/* 265:    */    for (;;)
/* 266:    */    {
/* 267:267 */      pos = (last = pos) + 1 & this.mask;
/* 268:268 */      while (this.used[pos] != 0) {
/* 269:269 */        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 270:270 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 271:271 */        pos = pos + 1 & this.mask;
/* 272:    */      }
/* 273:273 */      if (this.used[pos] == 0) break;
/* 274:274 */      this.key[last] = this.key[pos];
/* 275:275 */      this.value[last] = this.value[pos];
/* 276:    */    }
/* 277:277 */    this.used[last] = false;
/* 278:278 */    return last;
/* 279:    */  }
/* 280:    */  
/* 281:    */  public float remove(short k)
/* 282:    */  {
/* 283:283 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 284:    */    
/* 285:285 */    while (this.used[pos] != 0) {
/* 286:286 */      if (this.key[pos] == k) {
/* 287:287 */        this.size -= 1;
/* 288:288 */        float v = this.value[pos];
/* 289:289 */        shiftKeys(pos);
/* 290:290 */        return v;
/* 291:    */      }
/* 292:292 */      pos = pos + 1 & this.mask;
/* 293:    */    }
/* 294:294 */    return this.defRetValue;
/* 295:    */  }
/* 296:    */  
/* 297:    */  public Float remove(Object ok) {
/* 298:298 */    short k = ((Short)ok).shortValue();
/* 299:    */    
/* 300:300 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 301:    */    
/* 302:302 */    while (this.used[pos] != 0) {
/* 303:303 */      if (this.key[pos] == k) {
/* 304:304 */        this.size -= 1;
/* 305:305 */        float v = this.value[pos];
/* 306:306 */        shiftKeys(pos);
/* 307:307 */        return Float.valueOf(v);
/* 308:    */      }
/* 309:309 */      pos = pos + 1 & this.mask;
/* 310:    */    }
/* 311:311 */    return null;
/* 312:    */  }
/* 313:    */  
/* 314:314 */  public Float get(Short ok) { short k = ok.shortValue();
/* 315:    */    
/* 316:316 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 317:    */    
/* 318:318 */    while (this.used[pos] != 0) {
/* 319:319 */      if (this.key[pos] == k) return Float.valueOf(this.value[pos]);
/* 320:320 */      pos = pos + 1 & this.mask;
/* 321:    */    }
/* 322:322 */    return null;
/* 323:    */  }
/* 324:    */  
/* 325:    */  public float get(short k)
/* 326:    */  {
/* 327:327 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 328:    */    
/* 329:329 */    while (this.used[pos] != 0) {
/* 330:330 */      if (this.key[pos] == k) return this.value[pos];
/* 331:331 */      pos = pos + 1 & this.mask;
/* 332:    */    }
/* 333:333 */    return this.defRetValue;
/* 334:    */  }
/* 335:    */  
/* 336:    */  public boolean containsKey(short k)
/* 337:    */  {
/* 338:338 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/* 339:    */    
/* 340:340 */    while (this.used[pos] != 0) {
/* 341:341 */      if (this.key[pos] == k) return true;
/* 342:342 */      pos = pos + 1 & this.mask;
/* 343:    */    }
/* 344:344 */    return false;
/* 345:    */  }
/* 346:    */  
/* 347:347 */  public boolean containsValue(float v) { float[] value = this.value;
/* 348:348 */    boolean[] used = this.used;
/* 349:349 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/* 350:350 */    return false;
/* 351:    */  }
/* 352:    */  
/* 357:    */  public void clear()
/* 358:    */  {
/* 359:359 */    if (this.size == 0) return;
/* 360:360 */    this.size = 0;
/* 361:361 */    BooleanArrays.fill(this.used, false);
/* 362:    */  }
/* 363:    */  
/* 364:    */  public int size() {
/* 365:365 */    return this.size;
/* 366:    */  }
/* 367:    */  
/* 368:368 */  public boolean isEmpty() { return this.size == 0; }
/* 369:    */  
/* 375:    */  @Deprecated
/* 376:    */  public void growthFactor(int growthFactor) {}
/* 377:    */  
/* 382:    */  @Deprecated
/* 383:    */  public int growthFactor()
/* 384:    */  {
/* 385:385 */    return 16;
/* 386:    */  }
/* 387:    */  
/* 388:    */  private final class MapEntry
/* 389:    */    implements Short2FloatMap.Entry, Map.Entry<Short, Float>
/* 390:    */  {
/* 391:    */    private int index;
/* 392:    */    
/* 393:    */    MapEntry(int index)
/* 394:    */    {
/* 395:395 */      this.index = index;
/* 396:    */    }
/* 397:    */    
/* 398:398 */    public Short getKey() { return Short.valueOf(Short2FloatOpenHashMap.this.key[this.index]); }
/* 399:    */    
/* 400:    */    public short getShortKey() {
/* 401:401 */      return Short2FloatOpenHashMap.this.key[this.index];
/* 402:    */    }
/* 403:    */    
/* 404:404 */    public Float getValue() { return Float.valueOf(Short2FloatOpenHashMap.this.value[this.index]); }
/* 405:    */    
/* 407:407 */    public float getFloatValue() { return Short2FloatOpenHashMap.this.value[this.index]; }
/* 408:    */    
/* 409:    */    public float setValue(float v) {
/* 410:410 */      float oldValue = Short2FloatOpenHashMap.this.value[this.index];
/* 411:411 */      Short2FloatOpenHashMap.this.value[this.index] = v;
/* 412:412 */      return oldValue;
/* 413:    */    }
/* 414:    */    
/* 415:415 */    public Float setValue(Float v) { return Float.valueOf(setValue(v.floatValue())); }
/* 416:    */    
/* 417:    */    public boolean equals(Object o)
/* 418:    */    {
/* 419:419 */      if (!(o instanceof Map.Entry)) return false;
/* 420:420 */      Map.Entry<Short, Float> e = (Map.Entry)o;
/* 421:421 */      return (Short2FloatOpenHashMap.this.key[this.index] == ((Short)e.getKey()).shortValue()) && (Short2FloatOpenHashMap.this.value[this.index] == ((Float)e.getValue()).floatValue());
/* 422:    */    }
/* 423:    */    
/* 424:424 */    public int hashCode() { return Short2FloatOpenHashMap.this.key[this.index] ^ HashCommon.float2int(Short2FloatOpenHashMap.this.value[this.index]); }
/* 425:    */    
/* 427:427 */    public String toString() { return Short2FloatOpenHashMap.this.key[this.index] + "=>" + Short2FloatOpenHashMap.this.value[this.index]; } }
/* 428:    */  
/* 429:    */  private class MapIterator { int pos;
/* 430:    */    int last;
/* 431:    */    int c;
/* 432:    */    ShortArrayList wrapped;
/* 433:    */    
/* 434:434 */    private MapIterator() { this.pos = Short2FloatOpenHashMap.this.n;
/* 435:    */      
/* 437:437 */      this.last = -1;
/* 438:    */      
/* 439:439 */      this.c = Short2FloatOpenHashMap.this.size;
/* 440:    */      
/* 444:444 */      boolean[] used = Short2FloatOpenHashMap.this.used;
/* 445:445 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 446:    */    }
/* 447:    */    
/* 448:448 */    public boolean hasNext() { return this.c != 0; }
/* 449:    */    
/* 450:    */    public int nextEntry() {
/* 451:451 */      if (!hasNext()) throw new NoSuchElementException();
/* 452:452 */      this.c -= 1;
/* 453:    */      
/* 454:454 */      if (this.pos < 0) {
/* 455:455 */        short k = this.wrapped.getShort(-(this.last = --this.pos) - 2);
/* 456:    */        
/* 457:457 */        int pos = HashCommon.murmurHash3(k) & Short2FloatOpenHashMap.this.mask;
/* 458:    */        
/* 459:459 */        while (Short2FloatOpenHashMap.this.used[pos] != 0) {
/* 460:460 */          if (Short2FloatOpenHashMap.this.key[pos] == k) return pos;
/* 461:461 */          pos = pos + 1 & Short2FloatOpenHashMap.this.mask;
/* 462:    */        }
/* 463:    */      }
/* 464:464 */      this.last = this.pos;
/* 465:    */      
/* 466:466 */      if (this.c != 0) {
/* 467:467 */        boolean[] used = Short2FloatOpenHashMap.this.used;
/* 468:468 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 469:    */      }
/* 470:    */      
/* 471:471 */      return this.last;
/* 472:    */    }
/* 473:    */    
/* 477:    */    protected final int shiftKeys(int pos)
/* 478:    */    {
/* 479:    */      int last;
/* 480:    */      
/* 482:    */      for (;;)
/* 483:    */      {
/* 484:484 */        pos = (last = pos) + 1 & Short2FloatOpenHashMap.this.mask;
/* 485:485 */        while (Short2FloatOpenHashMap.this.used[pos] != 0) {
/* 486:486 */          int slot = HashCommon.murmurHash3(Short2FloatOpenHashMap.this.key[pos]) & Short2FloatOpenHashMap.this.mask;
/* 487:487 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 488:488 */          pos = pos + 1 & Short2FloatOpenHashMap.this.mask;
/* 489:    */        }
/* 490:490 */        if (Short2FloatOpenHashMap.this.used[pos] == 0) break;
/* 491:491 */        if (pos < last)
/* 492:    */        {
/* 493:493 */          if (this.wrapped == null) this.wrapped = new ShortArrayList();
/* 494:494 */          this.wrapped.add(Short2FloatOpenHashMap.this.key[pos]);
/* 495:    */        }
/* 496:496 */        Short2FloatOpenHashMap.this.key[last] = Short2FloatOpenHashMap.this.key[pos];
/* 497:497 */        Short2FloatOpenHashMap.this.value[last] = Short2FloatOpenHashMap.this.value[pos];
/* 498:    */      }
/* 499:499 */      Short2FloatOpenHashMap.this.used[last] = false;
/* 500:500 */      return last;
/* 501:    */    }
/* 502:    */    
/* 503:    */    public void remove() {
/* 504:504 */      if (this.last == -1) throw new IllegalStateException();
/* 505:505 */      if (this.pos < -1)
/* 506:    */      {
/* 507:507 */        Short2FloatOpenHashMap.this.remove(this.wrapped.getShort(-this.pos - 2));
/* 508:508 */        this.last = -1;
/* 509:509 */        return;
/* 510:    */      }
/* 511:511 */      Short2FloatOpenHashMap.this.size -= 1;
/* 512:512 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 513:513 */        this.c += 1;
/* 514:514 */        nextEntry();
/* 515:    */      }
/* 516:516 */      this.last = -1;
/* 517:    */    }
/* 518:    */    
/* 519:    */    public int skip(int n) {
/* 520:520 */      int i = n;
/* 521:521 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 522:522 */      return n - i - 1;
/* 523:    */    } }
/* 524:    */  
/* 525:525 */  private class EntryIterator extends Short2FloatOpenHashMap.MapIterator implements ObjectIterator<Short2FloatMap.Entry> { private EntryIterator() { super(null); }
/* 526:    */    
/* 527:    */    private Short2FloatOpenHashMap.MapEntry entry;
/* 528:528 */    public Short2FloatMap.Entry next() { return this.entry = new Short2FloatOpenHashMap.MapEntry(Short2FloatOpenHashMap.this, nextEntry()); }
/* 529:    */    
/* 530:    */    public void remove()
/* 531:    */    {
/* 532:532 */      super.remove();
/* 533:533 */      Short2FloatOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 534:    */    } }
/* 535:    */  
/* 536:536 */  private class FastEntryIterator extends Short2FloatOpenHashMap.MapIterator implements ObjectIterator<Short2FloatMap.Entry> { private FastEntryIterator() { super(null); }
/* 537:537 */    final AbstractShort2FloatMap.BasicEntry entry = new AbstractShort2FloatMap.BasicEntry((short)0, 0.0F);
/* 538:    */    
/* 539:539 */    public AbstractShort2FloatMap.BasicEntry next() { int e = nextEntry();
/* 540:540 */      this.entry.key = Short2FloatOpenHashMap.this.key[e];
/* 541:541 */      this.entry.value = Short2FloatOpenHashMap.this.value[e];
/* 542:542 */      return this.entry;
/* 543:    */    } }
/* 544:    */  
/* 545:    */  private final class MapEntrySet extends AbstractObjectSet<Short2FloatMap.Entry> implements Short2FloatMap.FastEntrySet { private MapEntrySet() {}
/* 546:    */    
/* 547:547 */    public ObjectIterator<Short2FloatMap.Entry> iterator() { return new Short2FloatOpenHashMap.EntryIterator(Short2FloatOpenHashMap.this, null); }
/* 548:    */    
/* 549:    */    public ObjectIterator<Short2FloatMap.Entry> fastIterator() {
/* 550:550 */      return new Short2FloatOpenHashMap.FastEntryIterator(Short2FloatOpenHashMap.this, null);
/* 551:    */    }
/* 552:    */    
/* 553:    */    public boolean contains(Object o) {
/* 554:554 */      if (!(o instanceof Map.Entry)) return false;
/* 555:555 */      Map.Entry<Short, Float> e = (Map.Entry)o;
/* 556:556 */      short k = ((Short)e.getKey()).shortValue();
/* 557:    */      
/* 558:558 */      int pos = HashCommon.murmurHash3(k) & Short2FloatOpenHashMap.this.mask;
/* 559:    */      
/* 560:560 */      while (Short2FloatOpenHashMap.this.used[pos] != 0) {
/* 561:561 */        if (Short2FloatOpenHashMap.this.key[pos] == k) return Short2FloatOpenHashMap.this.value[pos] == ((Float)e.getValue()).floatValue();
/* 562:562 */        pos = pos + 1 & Short2FloatOpenHashMap.this.mask;
/* 563:    */      }
/* 564:564 */      return false;
/* 565:    */    }
/* 566:    */    
/* 567:    */    public boolean remove(Object o) {
/* 568:568 */      if (!(o instanceof Map.Entry)) return false;
/* 569:569 */      Map.Entry<Short, Float> e = (Map.Entry)o;
/* 570:570 */      short k = ((Short)e.getKey()).shortValue();
/* 571:    */      
/* 572:572 */      int pos = HashCommon.murmurHash3(k) & Short2FloatOpenHashMap.this.mask;
/* 573:    */      
/* 574:574 */      while (Short2FloatOpenHashMap.this.used[pos] != 0) {
/* 575:575 */        if (Short2FloatOpenHashMap.this.key[pos] == k) {
/* 576:576 */          Short2FloatOpenHashMap.this.remove(e.getKey());
/* 577:577 */          return true;
/* 578:    */        }
/* 579:579 */        pos = pos + 1 & Short2FloatOpenHashMap.this.mask;
/* 580:    */      }
/* 581:581 */      return false;
/* 582:    */    }
/* 583:    */    
/* 584:584 */    public int size() { return Short2FloatOpenHashMap.this.size; }
/* 585:    */    
/* 587:587 */    public void clear() { Short2FloatOpenHashMap.this.clear(); }
/* 588:    */  }
/* 589:    */  
/* 590:    */  public Short2FloatMap.FastEntrySet short2FloatEntrySet() {
/* 591:591 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 592:592 */    return this.entries;
/* 593:    */  }
/* 594:    */  
/* 597:    */  private final class KeyIterator
/* 598:    */    extends Short2FloatOpenHashMap.MapIterator
/* 599:    */    implements ShortIterator
/* 600:    */  {
/* 601:601 */    public KeyIterator() { super(null); }
/* 602:602 */    public short nextShort() { return Short2FloatOpenHashMap.this.key[nextEntry()]; }
/* 603:603 */    public Short next() { return Short.valueOf(Short2FloatOpenHashMap.this.key[nextEntry()]); } }
/* 604:    */  
/* 605:    */  private final class KeySet extends AbstractShortSet { private KeySet() {}
/* 606:    */    
/* 607:607 */    public ShortIterator iterator() { return new Short2FloatOpenHashMap.KeyIterator(Short2FloatOpenHashMap.this); }
/* 608:    */    
/* 609:    */    public int size() {
/* 610:610 */      return Short2FloatOpenHashMap.this.size;
/* 611:    */    }
/* 612:    */    
/* 613:613 */    public boolean contains(short k) { return Short2FloatOpenHashMap.this.containsKey(k); }
/* 614:    */    
/* 615:    */    public boolean remove(short k) {
/* 616:616 */      int oldSize = Short2FloatOpenHashMap.this.size;
/* 617:617 */      Short2FloatOpenHashMap.this.remove(k);
/* 618:618 */      return Short2FloatOpenHashMap.this.size != oldSize;
/* 619:    */    }
/* 620:    */    
/* 621:621 */    public void clear() { Short2FloatOpenHashMap.this.clear(); }
/* 622:    */  }
/* 623:    */  
/* 624:    */  public ShortSet keySet() {
/* 625:625 */    if (this.keys == null) this.keys = new KeySet(null);
/* 626:626 */    return this.keys;
/* 627:    */  }
/* 628:    */  
/* 631:    */  private final class ValueIterator
/* 632:    */    extends Short2FloatOpenHashMap.MapIterator
/* 633:    */    implements FloatIterator
/* 634:    */  {
/* 635:635 */    public ValueIterator() { super(null); }
/* 636:636 */    public float nextFloat() { return Short2FloatOpenHashMap.this.value[nextEntry()]; }
/* 637:637 */    public Float next() { return Float.valueOf(Short2FloatOpenHashMap.this.value[nextEntry()]); }
/* 638:    */  }
/* 639:    */  
/* 640:640 */  public FloatCollection values() { if (this.values == null) { this.values = new AbstractFloatCollection() {
/* 641:    */        public FloatIterator iterator() {
/* 642:642 */          return new Short2FloatOpenHashMap.ValueIterator(Short2FloatOpenHashMap.this);
/* 643:    */        }
/* 644:    */        
/* 645:645 */        public int size() { return Short2FloatOpenHashMap.this.size; }
/* 646:    */        
/* 647:    */        public boolean contains(float v) {
/* 648:648 */          return Short2FloatOpenHashMap.this.containsValue(v);
/* 649:    */        }
/* 650:    */        
/* 651:651 */        public void clear() { Short2FloatOpenHashMap.this.clear(); }
/* 652:    */      };
/* 653:    */    }
/* 654:654 */    return this.values;
/* 655:    */  }
/* 656:    */  
/* 665:    */  @Deprecated
/* 666:    */  public boolean rehash()
/* 667:    */  {
/* 668:668 */    return true;
/* 669:    */  }
/* 670:    */  
/* 681:    */  public boolean trim()
/* 682:    */  {
/* 683:683 */    int l = HashCommon.arraySize(this.size, this.f);
/* 684:684 */    if (l >= this.n) return true;
/* 685:    */    try {
/* 686:686 */      rehash(l);
/* 687:    */    } catch (OutOfMemoryError cantDoIt) {
/* 688:688 */      return false; }
/* 689:689 */    return true;
/* 690:    */  }
/* 691:    */  
/* 708:    */  public boolean trim(int n)
/* 709:    */  {
/* 710:710 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 711:711 */    if (this.n <= l) return true;
/* 712:    */    try {
/* 713:713 */      rehash(l);
/* 714:    */    } catch (OutOfMemoryError cantDoIt) {
/* 715:715 */      return false; }
/* 716:716 */    return true;
/* 717:    */  }
/* 718:    */  
/* 727:    */  protected void rehash(int newN)
/* 728:    */  {
/* 729:729 */    int i = 0;
/* 730:730 */    boolean[] used = this.used;
/* 731:    */    
/* 732:732 */    short[] key = this.key;
/* 733:733 */    float[] value = this.value;
/* 734:734 */    int newMask = newN - 1;
/* 735:735 */    short[] newKey = new short[newN];
/* 736:736 */    float[] newValue = new float[newN];
/* 737:737 */    boolean[] newUsed = new boolean[newN];
/* 738:738 */    for (int j = this.size; j-- != 0;) {
/* 739:739 */      while (used[i] == 0) i++;
/* 740:740 */      short k = key[i];
/* 741:741 */      int pos = HashCommon.murmurHash3(k) & newMask;
/* 742:742 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 743:743 */      newUsed[pos] = true;
/* 744:744 */      newKey[pos] = k;
/* 745:745 */      newValue[pos] = value[i];
/* 746:746 */      i++;
/* 747:    */    }
/* 748:748 */    this.n = newN;
/* 749:749 */    this.mask = newMask;
/* 750:750 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 751:751 */    this.key = newKey;
/* 752:752 */    this.value = newValue;
/* 753:753 */    this.used = newUsed;
/* 754:    */  }
/* 755:    */  
/* 759:    */  public Short2FloatOpenHashMap clone()
/* 760:    */  {
/* 761:    */    Short2FloatOpenHashMap c;
/* 762:    */    
/* 764:    */    try
/* 765:    */    {
/* 766:766 */      c = (Short2FloatOpenHashMap)super.clone();
/* 767:    */    }
/* 768:    */    catch (CloneNotSupportedException cantHappen) {
/* 769:769 */      throw new InternalError();
/* 770:    */    }
/* 771:771 */    c.keys = null;
/* 772:772 */    c.values = null;
/* 773:773 */    c.entries = null;
/* 774:774 */    c.key = ((short[])this.key.clone());
/* 775:775 */    c.value = ((float[])this.value.clone());
/* 776:776 */    c.used = ((boolean[])this.used.clone());
/* 777:777 */    return c;
/* 778:    */  }
/* 779:    */  
/* 787:    */  public int hashCode()
/* 788:    */  {
/* 789:789 */    int h = 0;
/* 790:790 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 791:791 */      while (this.used[i] == 0) i++;
/* 792:792 */      t = this.key[i];
/* 793:793 */      t ^= HashCommon.float2int(this.value[i]);
/* 794:794 */      h += t;
/* 795:795 */      i++;
/* 796:    */    }
/* 797:797 */    return h;
/* 798:    */  }
/* 799:    */  
/* 800:800 */  private void writeObject(ObjectOutputStream s) throws IOException { short[] key = this.key;
/* 801:801 */    float[] value = this.value;
/* 802:802 */    MapIterator i = new MapIterator(null);
/* 803:803 */    s.defaultWriteObject();
/* 804:804 */    for (int j = this.size; j-- != 0;) {
/* 805:805 */      int e = i.nextEntry();
/* 806:806 */      s.writeShort(key[e]);
/* 807:807 */      s.writeFloat(value[e]);
/* 808:    */    }
/* 809:    */  }
/* 810:    */  
/* 811:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 812:812 */    s.defaultReadObject();
/* 813:813 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 814:814 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 815:815 */    this.mask = (this.n - 1);
/* 816:816 */    short[] key = this.key = new short[this.n];
/* 817:817 */    float[] value = this.value = new float[this.n];
/* 818:818 */    boolean[] used = this.used = new boolean[this.n];
/* 819:    */    
/* 821:821 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 822:822 */      short k = s.readShort();
/* 823:823 */      float v = s.readFloat();
/* 824:824 */      pos = HashCommon.murmurHash3(k) & this.mask;
/* 825:825 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 826:826 */      used[pos] = true;
/* 827:827 */      key[pos] = k;
/* 828:828 */      value[pos] = v;
/* 829:    */    }
/* 830:    */  }
/* 831:    */  
/* 832:    */  private void checkTable() {}
/* 833:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2FloatOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */