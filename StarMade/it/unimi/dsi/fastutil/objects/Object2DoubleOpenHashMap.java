/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   6:    */import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*   7:    */import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*   8:    */import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*   9:    */import java.io.IOException;
/*  10:    */import java.io.ObjectInputStream;
/*  11:    */import java.io.ObjectOutputStream;
/*  12:    */import java.io.Serializable;
/*  13:    */import java.util.Map;
/*  14:    */import java.util.Map.Entry;
/*  15:    */import java.util.NoSuchElementException;
/*  16:    */
/*  86:    */public class Object2DoubleOpenHashMap<K>
/*  87:    */  extends AbstractObject2DoubleMap<K>
/*  88:    */  implements Serializable, Cloneable, Hash
/*  89:    */{
/*  90:    */  public static final long serialVersionUID = 0L;
/*  91:    */  private static final boolean ASSERTS = false;
/*  92:    */  protected transient K[] key;
/*  93:    */  protected transient double[] value;
/*  94:    */  protected transient boolean[] used;
/*  95:    */  protected final float f;
/*  96:    */  protected transient int n;
/*  97:    */  protected transient int maxFill;
/*  98:    */  protected transient int mask;
/*  99:    */  protected int size;
/* 100:    */  protected volatile transient Object2DoubleMap.FastEntrySet<K> entries;
/* 101:    */  protected volatile transient ObjectSet<K> keys;
/* 102:    */  protected volatile transient DoubleCollection values;
/* 103:    */  
/* 104:    */  public Object2DoubleOpenHashMap(int expected, float f)
/* 105:    */  {
/* 106:106 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 107:107 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 108:108 */    this.f = f;
/* 109:109 */    this.n = HashCommon.arraySize(expected, f);
/* 110:110 */    this.mask = (this.n - 1);
/* 111:111 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 112:112 */    this.key = ((Object[])new Object[this.n]);
/* 113:113 */    this.value = new double[this.n];
/* 114:114 */    this.used = new boolean[this.n];
/* 115:    */  }
/* 116:    */  
/* 119:    */  public Object2DoubleOpenHashMap(int expected)
/* 120:    */  {
/* 121:121 */    this(expected, 0.75F);
/* 122:    */  }
/* 123:    */  
/* 125:    */  public Object2DoubleOpenHashMap()
/* 126:    */  {
/* 127:127 */    this(16, 0.75F);
/* 128:    */  }
/* 129:    */  
/* 133:    */  public Object2DoubleOpenHashMap(Map<? extends K, ? extends Double> m, float f)
/* 134:    */  {
/* 135:135 */    this(m.size(), f);
/* 136:136 */    putAll(m);
/* 137:    */  }
/* 138:    */  
/* 141:    */  public Object2DoubleOpenHashMap(Map<? extends K, ? extends Double> m)
/* 142:    */  {
/* 143:143 */    this(m, 0.75F);
/* 144:    */  }
/* 145:    */  
/* 149:    */  public Object2DoubleOpenHashMap(Object2DoubleMap<K> m, float f)
/* 150:    */  {
/* 151:151 */    this(m.size(), f);
/* 152:152 */    putAll(m);
/* 153:    */  }
/* 154:    */  
/* 157:    */  public Object2DoubleOpenHashMap(Object2DoubleMap<K> m)
/* 158:    */  {
/* 159:159 */    this(m, 0.75F);
/* 160:    */  }
/* 161:    */  
/* 167:    */  public Object2DoubleOpenHashMap(K[] k, double[] v, float f)
/* 168:    */  {
/* 169:169 */    this(k.length, f);
/* 170:170 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 171:171 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 178:    */  public Object2DoubleOpenHashMap(K[] k, double[] v)
/* 179:    */  {
/* 180:180 */    this(k, v, 0.75F);
/* 181:    */  }
/* 182:    */  
/* 186:    */  public double put(K k, double v)
/* 187:    */  {
/* 188:188 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 189:    */    
/* 190:190 */    while (this.used[pos] != 0) {
/* 191:191 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 192:192 */        double oldValue = this.value[pos];
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
/* 206:206 */  public Double put(K ok, Double ov) { double v = ov.doubleValue();
/* 207:207 */    K k = ok;
/* 208:    */    
/* 209:209 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 210:    */    
/* 211:211 */    while (this.used[pos] != 0) {
/* 212:212 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 213:213 */        Double oldValue = Double.valueOf(this.value[pos]);
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
/* 237:    */  public double add(K k, double incr)
/* 238:    */  {
/* 239:239 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 240:    */    
/* 241:241 */    while (this.used[pos] != 0) {
/* 242:242 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 243:243 */        double oldValue = this.value[pos];
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
/* 268:268 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
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
/* 281:    */  public double removeDouble(Object k)
/* 282:    */  {
/* 283:283 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 284:    */    
/* 285:285 */    while (this.used[pos] != 0) {
/* 286:286 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 287:287 */        this.size -= 1;
/* 288:288 */        double v = this.value[pos];
/* 289:289 */        shiftKeys(pos);
/* 290:290 */        return v;
/* 291:    */      }
/* 292:292 */      pos = pos + 1 & this.mask;
/* 293:    */    }
/* 294:294 */    return this.defRetValue;
/* 295:    */  }
/* 296:    */  
/* 297:    */  public Double remove(Object ok) {
/* 298:298 */    K k = ok;
/* 299:    */    
/* 300:300 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 301:    */    
/* 302:302 */    while (this.used[pos] != 0) {
/* 303:303 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 304:304 */        this.size -= 1;
/* 305:305 */        double v = this.value[pos];
/* 306:306 */        shiftKeys(pos);
/* 307:307 */        return Double.valueOf(v);
/* 308:    */      }
/* 309:309 */      pos = pos + 1 & this.mask;
/* 310:    */    }
/* 311:311 */    return null;
/* 312:    */  }
/* 313:    */  
/* 314:    */  public double getDouble(Object k)
/* 315:    */  {
/* 316:316 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 317:    */    
/* 318:318 */    while (this.used[pos] != 0) {
/* 319:319 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.value[pos];
/* 320:320 */      pos = pos + 1 & this.mask;
/* 321:    */    }
/* 322:322 */    return this.defRetValue;
/* 323:    */  }
/* 324:    */  
/* 325:    */  public boolean containsKey(Object k)
/* 326:    */  {
/* 327:327 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 328:    */    
/* 329:329 */    while (this.used[pos] != 0) {
/* 330:330 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 331:331 */      pos = pos + 1 & this.mask;
/* 332:    */    }
/* 333:333 */    return false;
/* 334:    */  }
/* 335:    */  
/* 336:336 */  public boolean containsValue(double v) { double[] value = this.value;
/* 337:337 */    boolean[] used = this.used;
/* 338:338 */    for (int i = this.n; i-- != 0; return true) label17: if ((used[i] == 0) || (value[i] != v)) break label17;
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
/* 379:    */    implements Object2DoubleMap.Entry<K>, Map.Entry<K, Double>
/* 380:    */  {
/* 381:    */    private int index;
/* 382:    */    
/* 383:    */    MapEntry(int index)
/* 384:    */    {
/* 385:385 */      this.index = index;
/* 386:    */    }
/* 387:    */    
/* 388:388 */    public K getKey() { return Object2DoubleOpenHashMap.this.key[this.index]; }
/* 389:    */    
/* 390:    */    public Double getValue() {
/* 391:391 */      return Double.valueOf(Object2DoubleOpenHashMap.this.value[this.index]);
/* 392:    */    }
/* 393:    */    
/* 394:394 */    public double getDoubleValue() { return Object2DoubleOpenHashMap.this.value[this.index]; }
/* 395:    */    
/* 396:    */    public double setValue(double v) {
/* 397:397 */      double oldValue = Object2DoubleOpenHashMap.this.value[this.index];
/* 398:398 */      Object2DoubleOpenHashMap.this.value[this.index] = v;
/* 399:399 */      return oldValue;
/* 400:    */    }
/* 401:    */    
/* 402:402 */    public Double setValue(Double v) { return Double.valueOf(setValue(v.doubleValue())); }
/* 403:    */    
/* 404:    */    public boolean equals(Object o)
/* 405:    */    {
/* 406:406 */      if (!(o instanceof Map.Entry)) return false;
/* 407:407 */      Map.Entry<K, Double> e = (Map.Entry)o;
/* 408:408 */      return (Object2DoubleOpenHashMap.this.key[this.index] == null ? e.getKey() == null : Object2DoubleOpenHashMap.this.key[this.index].equals(e.getKey())) && (Object2DoubleOpenHashMap.this.value[this.index] == ((Double)e.getValue()).doubleValue());
/* 409:    */    }
/* 410:    */    
/* 411:411 */    public int hashCode() { return (Object2DoubleOpenHashMap.this.key[this.index] == null ? 0 : Object2DoubleOpenHashMap.this.key[this.index].hashCode()) ^ HashCommon.double2int(Object2DoubleOpenHashMap.this.value[this.index]); }
/* 412:    */    
/* 414:414 */    public String toString() { return Object2DoubleOpenHashMap.this.key[this.index] + "=>" + Object2DoubleOpenHashMap.this.value[this.index]; } }
/* 415:    */  
/* 416:    */  private class MapIterator { int pos;
/* 417:    */    int last;
/* 418:    */    int c;
/* 419:    */    ObjectArrayList<K> wrapped;
/* 420:    */    
/* 421:421 */    private MapIterator() { this.pos = Object2DoubleOpenHashMap.this.n;
/* 422:    */      
/* 424:424 */      this.last = -1;
/* 425:    */      
/* 426:426 */      this.c = Object2DoubleOpenHashMap.this.size;
/* 427:    */      
/* 431:431 */      boolean[] used = Object2DoubleOpenHashMap.this.used;
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
/* 444:444 */        int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2DoubleOpenHashMap.this.mask;
/* 445:    */        
/* 446:446 */        while (Object2DoubleOpenHashMap.this.used[pos] != 0) {
/* 447:447 */          if (Object2DoubleOpenHashMap.this.key[pos] == null ? k == null : Object2DoubleOpenHashMap.this.key[pos].equals(k)) return pos;
/* 448:448 */          pos = pos + 1 & Object2DoubleOpenHashMap.this.mask;
/* 449:    */        }
/* 450:    */      }
/* 451:451 */      this.last = this.pos;
/* 452:    */      
/* 453:453 */      if (this.c != 0) {
/* 454:454 */        boolean[] used = Object2DoubleOpenHashMap.this.used;
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
/* 471:471 */        pos = (last = pos) + 1 & Object2DoubleOpenHashMap.this.mask;
/* 472:472 */        while (Object2DoubleOpenHashMap.this.used[pos] != 0) {
/* 473:473 */          int slot = (Object2DoubleOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2DoubleOpenHashMap.this.key[pos].hashCode())) & Object2DoubleOpenHashMap.this.mask;
/* 474:474 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 475:475 */          pos = pos + 1 & Object2DoubleOpenHashMap.this.mask;
/* 476:    */        }
/* 477:477 */        if (Object2DoubleOpenHashMap.this.used[pos] == 0) break;
/* 478:478 */        if (pos < last)
/* 479:    */        {
/* 480:480 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 481:481 */          this.wrapped.add(Object2DoubleOpenHashMap.this.key[pos]);
/* 482:    */        }
/* 483:483 */        Object2DoubleOpenHashMap.this.key[last] = Object2DoubleOpenHashMap.this.key[pos];
/* 484:484 */        Object2DoubleOpenHashMap.this.value[last] = Object2DoubleOpenHashMap.this.value[pos];
/* 485:    */      }
/* 486:486 */      Object2DoubleOpenHashMap.this.used[last] = false;
/* 487:487 */      Object2DoubleOpenHashMap.this.key[last] = null;
/* 488:488 */      return last;
/* 489:    */    }
/* 490:    */    
/* 491:    */    public void remove() {
/* 492:492 */      if (this.last == -1) throw new IllegalStateException();
/* 493:493 */      if (this.pos < -1)
/* 494:    */      {
/* 495:495 */        Object2DoubleOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 496:496 */        this.last = -1;
/* 497:497 */        return;
/* 498:    */      }
/* 499:499 */      Object2DoubleOpenHashMap.this.size -= 1;
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
/* 513:513 */  private class EntryIterator extends Object2DoubleOpenHashMap<K>.MapIterator implements ObjectIterator<Object2DoubleMap.Entry<K>> { private EntryIterator() { super(null); }
/* 514:    */    
/* 515:    */    private Object2DoubleOpenHashMap<K>.MapEntry entry;
/* 516:516 */    public Object2DoubleMap.Entry<K> next() { return this.entry = new Object2DoubleOpenHashMap.MapEntry(Object2DoubleOpenHashMap.this, nextEntry()); }
/* 517:    */    
/* 518:    */    public void remove()
/* 519:    */    {
/* 520:520 */      super.remove();
/* 521:521 */      Object2DoubleOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 522:    */    } }
/* 523:    */  
/* 524:524 */  private class FastEntryIterator extends Object2DoubleOpenHashMap<K>.MapIterator implements ObjectIterator<Object2DoubleMap.Entry<K>> { private FastEntryIterator() { super(null); }
/* 525:525 */    final AbstractObject2DoubleMap.BasicEntry<K> entry = new AbstractObject2DoubleMap.BasicEntry(null, 0.0D);
/* 526:    */    
/* 527:527 */    public AbstractObject2DoubleMap.BasicEntry<K> next() { int e = nextEntry();
/* 528:528 */      this.entry.key = Object2DoubleOpenHashMap.this.key[e];
/* 529:529 */      this.entry.value = Object2DoubleOpenHashMap.this.value[e];
/* 530:530 */      return this.entry;
/* 531:    */    } }
/* 532:    */  
/* 533:    */  private final class MapEntrySet extends AbstractObjectSet<Object2DoubleMap.Entry<K>> implements Object2DoubleMap.FastEntrySet<K> { private MapEntrySet() {}
/* 534:    */    
/* 535:535 */    public ObjectIterator<Object2DoubleMap.Entry<K>> iterator() { return new Object2DoubleOpenHashMap.EntryIterator(Object2DoubleOpenHashMap.this, null); }
/* 536:    */    
/* 537:    */    public ObjectIterator<Object2DoubleMap.Entry<K>> fastIterator() {
/* 538:538 */      return new Object2DoubleOpenHashMap.FastEntryIterator(Object2DoubleOpenHashMap.this, null);
/* 539:    */    }
/* 540:    */    
/* 541:    */    public boolean contains(Object o) {
/* 542:542 */      if (!(o instanceof Map.Entry)) return false;
/* 543:543 */      Map.Entry<K, Double> e = (Map.Entry)o;
/* 544:544 */      K k = e.getKey();
/* 545:    */      
/* 546:546 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2DoubleOpenHashMap.this.mask;
/* 547:    */      
/* 548:548 */      while (Object2DoubleOpenHashMap.this.used[pos] != 0) {
/* 549:549 */        if (Object2DoubleOpenHashMap.this.key[pos] == null ? k == null : Object2DoubleOpenHashMap.this.key[pos].equals(k)) return Object2DoubleOpenHashMap.this.value[pos] == ((Double)e.getValue()).doubleValue();
/* 550:550 */        pos = pos + 1 & Object2DoubleOpenHashMap.this.mask;
/* 551:    */      }
/* 552:552 */      return false;
/* 553:    */    }
/* 554:    */    
/* 555:    */    public boolean remove(Object o) {
/* 556:556 */      if (!(o instanceof Map.Entry)) return false;
/* 557:557 */      Map.Entry<K, Double> e = (Map.Entry)o;
/* 558:558 */      K k = e.getKey();
/* 559:    */      
/* 560:560 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2DoubleOpenHashMap.this.mask;
/* 561:    */      
/* 562:562 */      while (Object2DoubleOpenHashMap.this.used[pos] != 0) {
/* 563:563 */        if (Object2DoubleOpenHashMap.this.key[pos] == null ? k == null : Object2DoubleOpenHashMap.this.key[pos].equals(k)) {
/* 564:564 */          Object2DoubleOpenHashMap.this.remove(e.getKey());
/* 565:565 */          return true;
/* 566:    */        }
/* 567:567 */        pos = pos + 1 & Object2DoubleOpenHashMap.this.mask;
/* 568:    */      }
/* 569:569 */      return false;
/* 570:    */    }
/* 571:    */    
/* 572:572 */    public int size() { return Object2DoubleOpenHashMap.this.size; }
/* 573:    */    
/* 575:575 */    public void clear() { Object2DoubleOpenHashMap.this.clear(); }
/* 576:    */  }
/* 577:    */  
/* 578:    */  public Object2DoubleMap.FastEntrySet<K> object2DoubleEntrySet() {
/* 579:579 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 580:580 */    return this.entries;
/* 581:    */  }
/* 582:    */  
/* 585:    */  private final class KeyIterator
/* 586:    */    extends Object2DoubleOpenHashMap<K>.MapIterator
/* 587:    */    implements ObjectIterator<K>
/* 588:    */  {
/* 589:589 */    public KeyIterator() { super(null); }
/* 590:590 */    public K next() { return Object2DoubleOpenHashMap.this.key[nextEntry()]; } }
/* 591:    */  
/* 592:    */  private final class KeySet extends AbstractObjectSet<K> { private KeySet() {}
/* 593:    */    
/* 594:594 */    public ObjectIterator<K> iterator() { return new Object2DoubleOpenHashMap.KeyIterator(Object2DoubleOpenHashMap.this); }
/* 595:    */    
/* 596:    */    public int size() {
/* 597:597 */      return Object2DoubleOpenHashMap.this.size;
/* 598:    */    }
/* 599:    */    
/* 600:600 */    public boolean contains(Object k) { return Object2DoubleOpenHashMap.this.containsKey(k); }
/* 601:    */    
/* 602:    */    public boolean remove(Object k) {
/* 603:603 */      int oldSize = Object2DoubleOpenHashMap.this.size;
/* 604:604 */      Object2DoubleOpenHashMap.this.remove(k);
/* 605:605 */      return Object2DoubleOpenHashMap.this.size != oldSize;
/* 606:    */    }
/* 607:    */    
/* 608:608 */    public void clear() { Object2DoubleOpenHashMap.this.clear(); }
/* 609:    */  }
/* 610:    */  
/* 611:    */  public ObjectSet<K> keySet() {
/* 612:612 */    if (this.keys == null) this.keys = new KeySet(null);
/* 613:613 */    return this.keys;
/* 614:    */  }
/* 615:    */  
/* 618:    */  private final class ValueIterator
/* 619:    */    extends Object2DoubleOpenHashMap.MapIterator
/* 620:    */    implements DoubleIterator
/* 621:    */  {
/* 622:622 */    public ValueIterator() { super(null); }
/* 623:623 */    public double nextDouble() { return Object2DoubleOpenHashMap.this.value[nextEntry()]; }
/* 624:624 */    public Double next() { return Double.valueOf(Object2DoubleOpenHashMap.this.value[nextEntry()]); }
/* 625:    */  }
/* 626:    */  
/* 627:627 */  public DoubleCollection values() { if (this.values == null) { this.values = new AbstractDoubleCollection() {
/* 628:    */        public DoubleIterator iterator() {
/* 629:629 */          return new Object2DoubleOpenHashMap.ValueIterator(Object2DoubleOpenHashMap.this);
/* 630:    */        }
/* 631:    */        
/* 632:632 */        public int size() { return Object2DoubleOpenHashMap.this.size; }
/* 633:    */        
/* 634:    */        public boolean contains(double v) {
/* 635:635 */          return Object2DoubleOpenHashMap.this.containsValue(v);
/* 636:    */        }
/* 637:    */        
/* 638:638 */        public void clear() { Object2DoubleOpenHashMap.this.clear(); }
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
/* 720:720 */    double[] value = this.value;
/* 721:721 */    int newMask = newN - 1;
/* 722:722 */    K[] newKey = (Object[])new Object[newN];
/* 723:723 */    double[] newValue = new double[newN];
/* 724:724 */    boolean[] newUsed = new boolean[newN];
/* 725:725 */    for (int j = this.size; j-- != 0;) {
/* 726:726 */      while (used[i] == 0) i++;
/* 727:727 */      K k = key[i];
/* 728:728 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
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
/* 746:    */  public Object2DoubleOpenHashMap<K> clone()
/* 747:    */  {
/* 748:    */    Object2DoubleOpenHashMap<K> c;
/* 749:    */    
/* 751:    */    try
/* 752:    */    {
/* 753:753 */      c = (Object2DoubleOpenHashMap)super.clone();
/* 754:    */    }
/* 755:    */    catch (CloneNotSupportedException cantHappen) {
/* 756:756 */      throw new InternalError();
/* 757:    */    }
/* 758:758 */    c.keys = null;
/* 759:759 */    c.values = null;
/* 760:760 */    c.entries = null;
/* 761:761 */    c.key = ((Object[])this.key.clone());
/* 762:762 */    c.value = ((double[])this.value.clone());
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
/* 780:780 */        t = this.key[i] == null ? 0 : this.key[i].hashCode();
/* 781:781 */      t ^= HashCommon.double2int(this.value[i]);
/* 782:782 */      h += t;
/* 783:783 */      i++;
/* 784:    */    }
/* 785:785 */    return h;
/* 786:    */  }
/* 787:    */  
/* 788:788 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 789:789 */    double[] value = this.value;
/* 790:790 */    Object2DoubleOpenHashMap<K>.MapIterator i = new MapIterator(null);
/* 791:791 */    s.defaultWriteObject();
/* 792:792 */    for (int j = this.size; j-- != 0;) {
/* 793:793 */      int e = i.nextEntry();
/* 794:794 */      s.writeObject(key[e]);
/* 795:795 */      s.writeDouble(value[e]);
/* 796:    */    }
/* 797:    */  }
/* 798:    */  
/* 799:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 800:800 */    s.defaultReadObject();
/* 801:801 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 802:802 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 803:803 */    this.mask = (this.n - 1);
/* 804:804 */    K[] key = this.key = (Object[])new Object[this.n];
/* 805:805 */    double[] value = this.value = new double[this.n];
/* 806:806 */    boolean[] used = this.used = new boolean[this.n];
/* 807:    */    
/* 809:809 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 810:810 */      K k = s.readObject();
/* 811:811 */      double v = s.readDouble();
/* 812:812 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */