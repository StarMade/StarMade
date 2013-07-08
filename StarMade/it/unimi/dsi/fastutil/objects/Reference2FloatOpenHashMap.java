/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*   7:    */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*   8:    */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*   9:    */import java.io.IOException;
/*  10:    */import java.io.ObjectInputStream;
/*  11:    */import java.io.ObjectOutputStream;
/*  12:    */import java.io.Serializable;
/*  13:    */import java.util.Map;
/*  14:    */import java.util.Map.Entry;
/*  15:    */import java.util.NoSuchElementException;
/*  16:    */
/*  86:    */public class Reference2FloatOpenHashMap<K>
/*  87:    */  extends AbstractReference2FloatMap<K>
/*  88:    */  implements Serializable, Cloneable, Hash
/*  89:    */{
/*  90:    */  public static final long serialVersionUID = 0L;
/*  91:    */  private static final boolean ASSERTS = false;
/*  92:    */  protected transient K[] key;
/*  93:    */  protected transient float[] value;
/*  94:    */  protected transient boolean[] used;
/*  95:    */  protected final float f;
/*  96:    */  protected transient int n;
/*  97:    */  protected transient int maxFill;
/*  98:    */  protected transient int mask;
/*  99:    */  protected int size;
/* 100:    */  protected volatile transient Reference2FloatMap.FastEntrySet<K> entries;
/* 101:    */  protected volatile transient ReferenceSet<K> keys;
/* 102:    */  protected volatile transient FloatCollection values;
/* 103:    */  
/* 104:    */  public Reference2FloatOpenHashMap(int expected, float f)
/* 105:    */  {
/* 106:106 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 107:107 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 108:108 */    this.f = f;
/* 109:109 */    this.n = HashCommon.arraySize(expected, f);
/* 110:110 */    this.mask = (this.n - 1);
/* 111:111 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 112:112 */    this.key = ((Object[])new Object[this.n]);
/* 113:113 */    this.value = new float[this.n];
/* 114:114 */    this.used = new boolean[this.n];
/* 115:    */  }
/* 116:    */  
/* 119:    */  public Reference2FloatOpenHashMap(int expected)
/* 120:    */  {
/* 121:121 */    this(expected, 0.75F);
/* 122:    */  }
/* 123:    */  
/* 125:    */  public Reference2FloatOpenHashMap()
/* 126:    */  {
/* 127:127 */    this(16, 0.75F);
/* 128:    */  }
/* 129:    */  
/* 133:    */  public Reference2FloatOpenHashMap(Map<? extends K, ? extends Float> m, float f)
/* 134:    */  {
/* 135:135 */    this(m.size(), f);
/* 136:136 */    putAll(m);
/* 137:    */  }
/* 138:    */  
/* 141:    */  public Reference2FloatOpenHashMap(Map<? extends K, ? extends Float> m)
/* 142:    */  {
/* 143:143 */    this(m, 0.75F);
/* 144:    */  }
/* 145:    */  
/* 149:    */  public Reference2FloatOpenHashMap(Reference2FloatMap<K> m, float f)
/* 150:    */  {
/* 151:151 */    this(m.size(), f);
/* 152:152 */    putAll(m);
/* 153:    */  }
/* 154:    */  
/* 157:    */  public Reference2FloatOpenHashMap(Reference2FloatMap<K> m)
/* 158:    */  {
/* 159:159 */    this(m, 0.75F);
/* 160:    */  }
/* 161:    */  
/* 167:    */  public Reference2FloatOpenHashMap(K[] k, float[] v, float f)
/* 168:    */  {
/* 169:169 */    this(k.length, f);
/* 170:170 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 171:171 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 178:    */  public Reference2FloatOpenHashMap(K[] k, float[] v)
/* 179:    */  {
/* 180:180 */    this(k, v, 0.75F);
/* 181:    */  }
/* 182:    */  
/* 186:    */  public float put(K k, float v)
/* 187:    */  {
/* 188:188 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 189:    */    
/* 190:190 */    while (this.used[pos] != 0) {
/* 191:191 */      if (this.key[pos] == k) {
/* 192:192 */        float oldValue = this.value[pos];
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
/* 206:206 */  public Float put(K ok, Float ov) { float v = ov.floatValue();
/* 207:207 */    K k = ok;
/* 208:    */    
/* 209:209 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 210:    */    
/* 211:211 */    while (this.used[pos] != 0) {
/* 212:212 */      if (this.key[pos] == k) {
/* 213:213 */        Float oldValue = Float.valueOf(this.value[pos]);
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
/* 224:224 */    return null;
/* 225:    */  }
/* 226:    */  
/* 237:    */  public float add(K k, float incr)
/* 238:    */  {
/* 239:239 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 240:    */    
/* 241:241 */    while (this.used[pos] != 0) {
/* 242:242 */      if (this.key[pos] == k) {
/* 243:243 */        float oldValue = this.value[pos];
/* 244:244 */        this.value[pos] += incr;
/* 245:245 */        return oldValue;
/* 246:    */      }
/* 247:247 */      pos = pos + 1 & this.mask;
/* 248:    */    }
/* 249:249 */    this.used[pos] = true;
/* 250:250 */    this.key[pos] = k;
/* 251:251 */    this.value[pos] = (this.defRetValue + incr);
/* 252:252 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/* 253:    */    }
/* 254:254 */    return this.defRetValue;
/* 255:    */  }
/* 256:    */  
/* 259:    */  protected final int shiftKeys(int pos)
/* 260:    */  {
/* 261:    */    int last;
/* 262:    */    
/* 264:    */    for (;;)
/* 265:    */    {
/* 266:266 */      pos = (last = pos) + 1 & this.mask;
/* 267:267 */      while (this.used[pos] != 0) {
/* 268:268 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 269:269 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 270:270 */        pos = pos + 1 & this.mask;
/* 271:    */      }
/* 272:272 */      if (this.used[pos] == 0) break;
/* 273:273 */      this.key[last] = this.key[pos];
/* 274:274 */      this.value[last] = this.value[pos];
/* 275:    */    }
/* 276:276 */    this.used[last] = false;
/* 277:277 */    this.key[last] = null;
/* 278:278 */    return last;
/* 279:    */  }
/* 280:    */  
/* 281:    */  public float removeFloat(Object k)
/* 282:    */  {
/* 283:283 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
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
/* 298:298 */    K k = ok;
/* 299:    */    
/* 300:300 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
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
/* 314:    */  public float getFloat(Object k)
/* 315:    */  {
/* 316:316 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 317:    */    
/* 318:318 */    while (this.used[pos] != 0) {
/* 319:319 */      if (this.key[pos] == k) return this.value[pos];
/* 320:320 */      pos = pos + 1 & this.mask;
/* 321:    */    }
/* 322:322 */    return this.defRetValue;
/* 323:    */  }
/* 324:    */  
/* 325:    */  public boolean containsKey(Object k)
/* 326:    */  {
/* 327:327 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 328:    */    
/* 329:329 */    while (this.used[pos] != 0) {
/* 330:330 */      if (this.key[pos] == k) return true;
/* 331:331 */      pos = pos + 1 & this.mask;
/* 332:    */    }
/* 333:333 */    return false;
/* 334:    */  }
/* 335:    */  
/* 336:336 */  public boolean containsValue(float v) { float[] value = this.value;
/* 337:337 */    boolean[] used = this.used;
/* 338:338 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/* 339:339 */    return false;
/* 340:    */  }
/* 341:    */  
/* 346:    */  public void clear()
/* 347:    */  {
/* 348:348 */    if (this.size == 0) return;
/* 349:349 */    this.size = 0;
/* 350:350 */    BooleanArrays.fill(this.used, false);
/* 351:    */    
/* 352:352 */    ObjectArrays.fill(this.key, null);
/* 353:    */  }
/* 354:    */  
/* 355:355 */  public int size() { return this.size; }
/* 356:    */  
/* 357:    */  public boolean isEmpty() {
/* 358:358 */    return this.size == 0;
/* 359:    */  }
/* 360:    */  
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
/* 379:    */    implements Reference2FloatMap.Entry<K>, Map.Entry<K, Float>
/* 380:    */  {
/* 381:    */    private int index;
/* 382:    */    
/* 383:    */    MapEntry(int index)
/* 384:    */    {
/* 385:385 */      this.index = index;
/* 386:    */    }
/* 387:    */    
/* 388:388 */    public K getKey() { return Reference2FloatOpenHashMap.this.key[this.index]; }
/* 389:    */    
/* 390:    */    public Float getValue() {
/* 391:391 */      return Float.valueOf(Reference2FloatOpenHashMap.this.value[this.index]);
/* 392:    */    }
/* 393:    */    
/* 394:394 */    public float getFloatValue() { return Reference2FloatOpenHashMap.this.value[this.index]; }
/* 395:    */    
/* 396:    */    public float setValue(float v) {
/* 397:397 */      float oldValue = Reference2FloatOpenHashMap.this.value[this.index];
/* 398:398 */      Reference2FloatOpenHashMap.this.value[this.index] = v;
/* 399:399 */      return oldValue;
/* 400:    */    }
/* 401:    */    
/* 402:402 */    public Float setValue(Float v) { return Float.valueOf(setValue(v.floatValue())); }
/* 403:    */    
/* 404:    */    public boolean equals(Object o)
/* 405:    */    {
/* 406:406 */      if (!(o instanceof Map.Entry)) return false;
/* 407:407 */      Map.Entry<K, Float> e = (Map.Entry)o;
/* 408:408 */      return (Reference2FloatOpenHashMap.this.key[this.index] == e.getKey()) && (Reference2FloatOpenHashMap.this.value[this.index] == ((Float)e.getValue()).floatValue());
/* 409:    */    }
/* 410:    */    
/* 411:411 */    public int hashCode() { return (Reference2FloatOpenHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2FloatOpenHashMap.this.key[this.index])) ^ HashCommon.float2int(Reference2FloatOpenHashMap.this.value[this.index]); }
/* 412:    */    
/* 414:414 */    public String toString() { return Reference2FloatOpenHashMap.this.key[this.index] + "=>" + Reference2FloatOpenHashMap.this.value[this.index]; } }
/* 415:    */  
/* 416:    */  private class MapIterator { int pos;
/* 417:    */    int last;
/* 418:    */    int c;
/* 419:    */    ReferenceArrayList<K> wrapped;
/* 420:    */    
/* 421:421 */    private MapIterator() { this.pos = Reference2FloatOpenHashMap.this.n;
/* 422:    */      
/* 424:424 */      this.last = -1;
/* 425:    */      
/* 426:426 */      this.c = Reference2FloatOpenHashMap.this.size;
/* 427:    */      
/* 431:431 */      boolean[] used = Reference2FloatOpenHashMap.this.used;
/* 432:432 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 433:    */    }
/* 434:    */    
/* 435:435 */    public boolean hasNext() { return this.c != 0; }
/* 436:    */    
/* 437:    */    public int nextEntry() {
/* 438:438 */      if (!hasNext()) throw new NoSuchElementException();
/* 439:439 */      this.c -= 1;
/* 440:    */      
/* 441:441 */      if (this.pos < 0) {
/* 442:442 */        Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/* 443:    */        
/* 444:444 */        int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2FloatOpenHashMap.this.mask;
/* 445:    */        
/* 446:446 */        while (Reference2FloatOpenHashMap.this.used[pos] != 0) {
/* 447:447 */          if (Reference2FloatOpenHashMap.this.key[pos] == k) return pos;
/* 448:448 */          pos = pos + 1 & Reference2FloatOpenHashMap.this.mask;
/* 449:    */        }
/* 450:    */      }
/* 451:451 */      this.last = this.pos;
/* 452:    */      
/* 453:453 */      if (this.c != 0) {
/* 454:454 */        boolean[] used = Reference2FloatOpenHashMap.this.used;
/* 455:455 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 456:    */      }
/* 457:    */      
/* 458:458 */      return this.last;
/* 459:    */    }
/* 460:    */    
/* 464:    */    protected final int shiftKeys(int pos)
/* 465:    */    {
/* 466:    */      int last;
/* 467:    */      
/* 469:    */      for (;;)
/* 470:    */      {
/* 471:471 */        pos = (last = pos) + 1 & Reference2FloatOpenHashMap.this.mask;
/* 472:472 */        while (Reference2FloatOpenHashMap.this.used[pos] != 0) {
/* 473:473 */          int slot = (Reference2FloatOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2FloatOpenHashMap.this.key[pos]))) & Reference2FloatOpenHashMap.this.mask;
/* 474:474 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 475:475 */          pos = pos + 1 & Reference2FloatOpenHashMap.this.mask;
/* 476:    */        }
/* 477:477 */        if (Reference2FloatOpenHashMap.this.used[pos] == 0) break;
/* 478:478 */        if (pos < last)
/* 479:    */        {
/* 480:480 */          if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 481:481 */          this.wrapped.add(Reference2FloatOpenHashMap.this.key[pos]);
/* 482:    */        }
/* 483:483 */        Reference2FloatOpenHashMap.this.key[last] = Reference2FloatOpenHashMap.this.key[pos];
/* 484:484 */        Reference2FloatOpenHashMap.this.value[last] = Reference2FloatOpenHashMap.this.value[pos];
/* 485:    */      }
/* 486:486 */      Reference2FloatOpenHashMap.this.used[last] = false;
/* 487:487 */      Reference2FloatOpenHashMap.this.key[last] = null;
/* 488:488 */      return last;
/* 489:    */    }
/* 490:    */    
/* 491:    */    public void remove() {
/* 492:492 */      if (this.last == -1) throw new IllegalStateException();
/* 493:493 */      if (this.pos < -1)
/* 494:    */      {
/* 495:495 */        Reference2FloatOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 496:496 */        this.last = -1;
/* 497:497 */        return;
/* 498:    */      }
/* 499:499 */      Reference2FloatOpenHashMap.this.size -= 1;
/* 500:500 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 501:501 */        this.c += 1;
/* 502:502 */        nextEntry();
/* 503:    */      }
/* 504:504 */      this.last = -1;
/* 505:    */    }
/* 506:    */    
/* 507:    */    public int skip(int n) {
/* 508:508 */      int i = n;
/* 509:509 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 510:510 */      return n - i - 1;
/* 511:    */    } }
/* 512:    */  
/* 513:513 */  private class EntryIterator extends Reference2FloatOpenHashMap<K>.MapIterator implements ObjectIterator<Reference2FloatMap.Entry<K>> { private EntryIterator() { super(null); }
/* 514:    */    
/* 515:    */    private Reference2FloatOpenHashMap<K>.MapEntry entry;
/* 516:516 */    public Reference2FloatMap.Entry<K> next() { return this.entry = new Reference2FloatOpenHashMap.MapEntry(Reference2FloatOpenHashMap.this, nextEntry()); }
/* 517:    */    
/* 518:    */    public void remove()
/* 519:    */    {
/* 520:520 */      super.remove();
/* 521:521 */      Reference2FloatOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 522:    */    } }
/* 523:    */  
/* 524:524 */  private class FastEntryIterator extends Reference2FloatOpenHashMap<K>.MapIterator implements ObjectIterator<Reference2FloatMap.Entry<K>> { private FastEntryIterator() { super(null); }
/* 525:525 */    final AbstractReference2FloatMap.BasicEntry<K> entry = new AbstractReference2FloatMap.BasicEntry(null, 0.0F);
/* 526:    */    
/* 527:527 */    public AbstractReference2FloatMap.BasicEntry<K> next() { int e = nextEntry();
/* 528:528 */      this.entry.key = Reference2FloatOpenHashMap.this.key[e];
/* 529:529 */      this.entry.value = Reference2FloatOpenHashMap.this.value[e];
/* 530:530 */      return this.entry;
/* 531:    */    } }
/* 532:    */  
/* 533:    */  private final class MapEntrySet extends AbstractObjectSet<Reference2FloatMap.Entry<K>> implements Reference2FloatMap.FastEntrySet<K> { private MapEntrySet() {}
/* 534:    */    
/* 535:535 */    public ObjectIterator<Reference2FloatMap.Entry<K>> iterator() { return new Reference2FloatOpenHashMap.EntryIterator(Reference2FloatOpenHashMap.this, null); }
/* 536:    */    
/* 537:    */    public ObjectIterator<Reference2FloatMap.Entry<K>> fastIterator() {
/* 538:538 */      return new Reference2FloatOpenHashMap.FastEntryIterator(Reference2FloatOpenHashMap.this, null);
/* 539:    */    }
/* 540:    */    
/* 541:    */    public boolean contains(Object o) {
/* 542:542 */      if (!(o instanceof Map.Entry)) return false;
/* 543:543 */      Map.Entry<K, Float> e = (Map.Entry)o;
/* 544:544 */      K k = e.getKey();
/* 545:    */      
/* 546:546 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2FloatOpenHashMap.this.mask;
/* 547:    */      
/* 548:548 */      while (Reference2FloatOpenHashMap.this.used[pos] != 0) {
/* 549:549 */        if (Reference2FloatOpenHashMap.this.key[pos] == k) return Reference2FloatOpenHashMap.this.value[pos] == ((Float)e.getValue()).floatValue();
/* 550:550 */        pos = pos + 1 & Reference2FloatOpenHashMap.this.mask;
/* 551:    */      }
/* 552:552 */      return false;
/* 553:    */    }
/* 554:    */    
/* 555:    */    public boolean remove(Object o) {
/* 556:556 */      if (!(o instanceof Map.Entry)) return false;
/* 557:557 */      Map.Entry<K, Float> e = (Map.Entry)o;
/* 558:558 */      K k = e.getKey();
/* 559:    */      
/* 560:560 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2FloatOpenHashMap.this.mask;
/* 561:    */      
/* 562:562 */      while (Reference2FloatOpenHashMap.this.used[pos] != 0) {
/* 563:563 */        if (Reference2FloatOpenHashMap.this.key[pos] == k) {
/* 564:564 */          Reference2FloatOpenHashMap.this.remove(e.getKey());
/* 565:565 */          return true;
/* 566:    */        }
/* 567:567 */        pos = pos + 1 & Reference2FloatOpenHashMap.this.mask;
/* 568:    */      }
/* 569:569 */      return false;
/* 570:    */    }
/* 571:    */    
/* 572:572 */    public int size() { return Reference2FloatOpenHashMap.this.size; }
/* 573:    */    
/* 575:575 */    public void clear() { Reference2FloatOpenHashMap.this.clear(); }
/* 576:    */  }
/* 577:    */  
/* 578:    */  public Reference2FloatMap.FastEntrySet<K> reference2FloatEntrySet() {
/* 579:579 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 580:580 */    return this.entries;
/* 581:    */  }
/* 582:    */  
/* 585:    */  private final class KeyIterator
/* 586:    */    extends Reference2FloatOpenHashMap<K>.MapIterator
/* 587:    */    implements ObjectIterator<K>
/* 588:    */  {
/* 589:589 */    public KeyIterator() { super(null); }
/* 590:590 */    public K next() { return Reference2FloatOpenHashMap.this.key[nextEntry()]; } }
/* 591:    */  
/* 592:    */  private final class KeySet extends AbstractReferenceSet<K> { private KeySet() {}
/* 593:    */    
/* 594:594 */    public ObjectIterator<K> iterator() { return new Reference2FloatOpenHashMap.KeyIterator(Reference2FloatOpenHashMap.this); }
/* 595:    */    
/* 596:    */    public int size() {
/* 597:597 */      return Reference2FloatOpenHashMap.this.size;
/* 598:    */    }
/* 599:    */    
/* 600:600 */    public boolean contains(Object k) { return Reference2FloatOpenHashMap.this.containsKey(k); }
/* 601:    */    
/* 602:    */    public boolean remove(Object k) {
/* 603:603 */      int oldSize = Reference2FloatOpenHashMap.this.size;
/* 604:604 */      Reference2FloatOpenHashMap.this.remove(k);
/* 605:605 */      return Reference2FloatOpenHashMap.this.size != oldSize;
/* 606:    */    }
/* 607:    */    
/* 608:608 */    public void clear() { Reference2FloatOpenHashMap.this.clear(); }
/* 609:    */  }
/* 610:    */  
/* 611:    */  public ReferenceSet<K> keySet() {
/* 612:612 */    if (this.keys == null) this.keys = new KeySet(null);
/* 613:613 */    return this.keys;
/* 614:    */  }
/* 615:    */  
/* 618:    */  private final class ValueIterator
/* 619:    */    extends Reference2FloatOpenHashMap.MapIterator
/* 620:    */    implements FloatIterator
/* 621:    */  {
/* 622:622 */    public ValueIterator() { super(null); }
/* 623:623 */    public float nextFloat() { return Reference2FloatOpenHashMap.this.value[nextEntry()]; }
/* 624:624 */    public Float next() { return Float.valueOf(Reference2FloatOpenHashMap.this.value[nextEntry()]); }
/* 625:    */  }
/* 626:    */  
/* 627:627 */  public FloatCollection values() { if (this.values == null) { this.values = new AbstractFloatCollection() {
/* 628:    */        public FloatIterator iterator() {
/* 629:629 */          return new Reference2FloatOpenHashMap.ValueIterator(Reference2FloatOpenHashMap.this);
/* 630:    */        }
/* 631:    */        
/* 632:632 */        public int size() { return Reference2FloatOpenHashMap.this.size; }
/* 633:    */        
/* 634:    */        public boolean contains(float v) {
/* 635:635 */          return Reference2FloatOpenHashMap.this.containsValue(v);
/* 636:    */        }
/* 637:    */        
/* 638:638 */        public void clear() { Reference2FloatOpenHashMap.this.clear(); }
/* 639:    */      };
/* 640:    */    }
/* 641:641 */    return this.values;
/* 642:    */  }
/* 643:    */  
/* 652:    */  @Deprecated
/* 653:    */  public boolean rehash()
/* 654:    */  {
/* 655:655 */    return true;
/* 656:    */  }
/* 657:    */  
/* 668:    */  public boolean trim()
/* 669:    */  {
/* 670:670 */    int l = HashCommon.arraySize(this.size, this.f);
/* 671:671 */    if (l >= this.n) return true;
/* 672:    */    try {
/* 673:673 */      rehash(l);
/* 674:    */    } catch (OutOfMemoryError cantDoIt) {
/* 675:675 */      return false; }
/* 676:676 */    return true;
/* 677:    */  }
/* 678:    */  
/* 695:    */  public boolean trim(int n)
/* 696:    */  {
/* 697:697 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 698:698 */    if (this.n <= l) return true;
/* 699:    */    try {
/* 700:700 */      rehash(l);
/* 701:    */    } catch (OutOfMemoryError cantDoIt) {
/* 702:702 */      return false; }
/* 703:703 */    return true;
/* 704:    */  }
/* 705:    */  
/* 714:    */  protected void rehash(int newN)
/* 715:    */  {
/* 716:716 */    int i = 0;
/* 717:717 */    boolean[] used = this.used;
/* 718:    */    
/* 719:719 */    K[] key = this.key;
/* 720:720 */    float[] value = this.value;
/* 721:721 */    int newMask = newN - 1;
/* 722:722 */    K[] newKey = (Object[])new Object[newN];
/* 723:723 */    float[] newValue = new float[newN];
/* 724:724 */    boolean[] newUsed = new boolean[newN];
/* 725:725 */    for (int j = this.size; j-- != 0;) {
/* 726:726 */      while (used[i] == 0) i++;
/* 727:727 */      K k = key[i];
/* 728:728 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 729:729 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 730:730 */      newUsed[pos] = true;
/* 731:731 */      newKey[pos] = k;
/* 732:732 */      newValue[pos] = value[i];
/* 733:733 */      i++;
/* 734:    */    }
/* 735:735 */    this.n = newN;
/* 736:736 */    this.mask = newMask;
/* 737:737 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 738:738 */    this.key = newKey;
/* 739:739 */    this.value = newValue;
/* 740:740 */    this.used = newUsed;
/* 741:    */  }
/* 742:    */  
/* 746:    */  public Reference2FloatOpenHashMap<K> clone()
/* 747:    */  {
/* 748:    */    Reference2FloatOpenHashMap<K> c;
/* 749:    */    
/* 751:    */    try
/* 752:    */    {
/* 753:753 */      c = (Reference2FloatOpenHashMap)super.clone();
/* 754:    */    }
/* 755:    */    catch (CloneNotSupportedException cantHappen) {
/* 756:756 */      throw new InternalError();
/* 757:    */    }
/* 758:758 */    c.keys = null;
/* 759:759 */    c.values = null;
/* 760:760 */    c.entries = null;
/* 761:761 */    c.key = ((Object[])this.key.clone());
/* 762:762 */    c.value = ((float[])this.value.clone());
/* 763:763 */    c.used = ((boolean[])this.used.clone());
/* 764:764 */    return c;
/* 765:    */  }
/* 766:    */  
/* 774:    */  public int hashCode()
/* 775:    */  {
/* 776:776 */    int h = 0;
/* 777:777 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 778:778 */      while (this.used[i] == 0) i++;
/* 779:779 */      if (this != this.key[i])
/* 780:780 */        t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 781:781 */      t ^= HashCommon.float2int(this.value[i]);
/* 782:782 */      h += t;
/* 783:783 */      i++;
/* 784:    */    }
/* 785:785 */    return h;
/* 786:    */  }
/* 787:    */  
/* 788:788 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 789:789 */    float[] value = this.value;
/* 790:790 */    Reference2FloatOpenHashMap<K>.MapIterator i = new MapIterator(null);
/* 791:791 */    s.defaultWriteObject();
/* 792:792 */    for (int j = this.size; j-- != 0;) {
/* 793:793 */      int e = i.nextEntry();
/* 794:794 */      s.writeObject(key[e]);
/* 795:795 */      s.writeFloat(value[e]);
/* 796:    */    }
/* 797:    */  }
/* 798:    */  
/* 799:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 800:800 */    s.defaultReadObject();
/* 801:801 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 802:802 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 803:803 */    this.mask = (this.n - 1);
/* 804:804 */    K[] key = this.key = (Object[])new Object[this.n];
/* 805:805 */    float[] value = this.value = new float[this.n];
/* 806:806 */    boolean[] used = this.used = new boolean[this.n];
/* 807:    */    
/* 809:809 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 810:810 */      K k = s.readObject();
/* 811:811 */      float v = s.readFloat();
/* 812:812 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 813:813 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 814:814 */      used[pos] = true;
/* 815:815 */      key[pos] = k;
/* 816:816 */      value[pos] = v;
/* 817:    */    }
/* 818:    */  }
/* 819:    */  
/* 820:    */  private void checkTable() {}
/* 821:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2FloatOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */