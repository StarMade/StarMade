/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash;
/*   4:    */import it.unimi.dsi.fastutil.HashCommon;
/*   5:    */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*   6:    */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*   7:    */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*   8:    */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*   9:    */import java.io.IOException;
/*  10:    */import java.io.ObjectInputStream;
/*  11:    */import java.io.ObjectOutputStream;
/*  12:    */import java.io.Serializable;
/*  13:    */import java.util.Map;
/*  14:    */import java.util.Map.Entry;
/*  15:    */import java.util.NoSuchElementException;
/*  16:    */
/*  86:    */public class Object2BooleanOpenHashMap<K>
/*  87:    */  extends AbstractObject2BooleanMap<K>
/*  88:    */  implements Serializable, Cloneable, Hash
/*  89:    */{
/*  90:    */  public static final long serialVersionUID = 0L;
/*  91:    */  private static final boolean ASSERTS = false;
/*  92:    */  protected transient K[] key;
/*  93:    */  protected transient boolean[] value;
/*  94:    */  protected transient boolean[] used;
/*  95:    */  protected final float f;
/*  96:    */  protected transient int n;
/*  97:    */  protected transient int maxFill;
/*  98:    */  protected transient int mask;
/*  99:    */  protected int size;
/* 100:    */  protected volatile transient Object2BooleanMap.FastEntrySet<K> entries;
/* 101:    */  protected volatile transient ObjectSet<K> keys;
/* 102:    */  protected volatile transient BooleanCollection values;
/* 103:    */  
/* 104:    */  public Object2BooleanOpenHashMap(int expected, float f)
/* 105:    */  {
/* 106:106 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 107:107 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 108:108 */    this.f = f;
/* 109:109 */    this.n = HashCommon.arraySize(expected, f);
/* 110:110 */    this.mask = (this.n - 1);
/* 111:111 */    this.maxFill = HashCommon.maxFill(this.n, f);
/* 112:112 */    this.key = ((Object[])new Object[this.n]);
/* 113:113 */    this.value = new boolean[this.n];
/* 114:114 */    this.used = new boolean[this.n];
/* 115:    */  }
/* 116:    */  
/* 119:    */  public Object2BooleanOpenHashMap(int expected)
/* 120:    */  {
/* 121:121 */    this(expected, 0.75F);
/* 122:    */  }
/* 123:    */  
/* 125:    */  public Object2BooleanOpenHashMap()
/* 126:    */  {
/* 127:127 */    this(16, 0.75F);
/* 128:    */  }
/* 129:    */  
/* 133:    */  public Object2BooleanOpenHashMap(Map<? extends K, ? extends Boolean> m, float f)
/* 134:    */  {
/* 135:135 */    this(m.size(), f);
/* 136:136 */    putAll(m);
/* 137:    */  }
/* 138:    */  
/* 141:    */  public Object2BooleanOpenHashMap(Map<? extends K, ? extends Boolean> m)
/* 142:    */  {
/* 143:143 */    this(m, 0.75F);
/* 144:    */  }
/* 145:    */  
/* 149:    */  public Object2BooleanOpenHashMap(Object2BooleanMap<K> m, float f)
/* 150:    */  {
/* 151:151 */    this(m.size(), f);
/* 152:152 */    putAll(m);
/* 153:    */  }
/* 154:    */  
/* 157:    */  public Object2BooleanOpenHashMap(Object2BooleanMap<K> m)
/* 158:    */  {
/* 159:159 */    this(m, 0.75F);
/* 160:    */  }
/* 161:    */  
/* 167:    */  public Object2BooleanOpenHashMap(K[] k, boolean[] v, float f)
/* 168:    */  {
/* 169:169 */    this(k.length, f);
/* 170:170 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 171:171 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 178:    */  public Object2BooleanOpenHashMap(K[] k, boolean[] v)
/* 179:    */  {
/* 180:180 */    this(k, v, 0.75F);
/* 181:    */  }
/* 182:    */  
/* 186:    */  public boolean put(K k, boolean v)
/* 187:    */  {
/* 188:188 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 189:    */    
/* 190:190 */    while (this.used[pos] != 0) {
/* 191:191 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 192:192 */        boolean oldValue = this.value[pos];
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
/* 206:206 */  public Boolean put(K ok, Boolean ov) { boolean v = ov.booleanValue();
/* 207:207 */    K k = ok;
/* 208:    */    
/* 209:209 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 210:    */    
/* 211:211 */    while (this.used[pos] != 0) {
/* 212:212 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 213:213 */        Boolean oldValue = Boolean.valueOf(this.value[pos]);
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
/* 229:    */  protected final int shiftKeys(int pos)
/* 230:    */  {
/* 231:    */    int last;
/* 232:    */    
/* 234:    */    for (;;)
/* 235:    */    {
/* 236:236 */      pos = (last = pos) + 1 & this.mask;
/* 237:237 */      while (this.used[pos] != 0) {
/* 238:238 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
/* 239:239 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 240:240 */        pos = pos + 1 & this.mask;
/* 241:    */      }
/* 242:242 */      if (this.used[pos] == 0) break;
/* 243:243 */      this.key[last] = this.key[pos];
/* 244:244 */      this.value[last] = this.value[pos];
/* 245:    */    }
/* 246:246 */    this.used[last] = false;
/* 247:247 */    this.key[last] = null;
/* 248:248 */    return last;
/* 249:    */  }
/* 250:    */  
/* 251:    */  public boolean removeBoolean(Object k)
/* 252:    */  {
/* 253:253 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 254:    */    
/* 255:255 */    while (this.used[pos] != 0) {
/* 256:256 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 268:268 */    K k = ok;
/* 269:    */    
/* 270:270 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 271:    */    
/* 272:272 */    while (this.used[pos] != 0) {
/* 273:273 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 284:    */  public boolean getBoolean(Object k)
/* 285:    */  {
/* 286:286 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 287:    */    
/* 288:288 */    while (this.used[pos] != 0) {
/* 289:289 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.value[pos];
/* 290:290 */      pos = pos + 1 & this.mask;
/* 291:    */    }
/* 292:292 */    return this.defRetValue;
/* 293:    */  }
/* 294:    */  
/* 295:    */  public boolean containsKey(Object k)
/* 296:    */  {
/* 297:297 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 298:    */    
/* 299:299 */    while (this.used[pos] != 0) {
/* 300:300 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 301:301 */      pos = pos + 1 & this.mask;
/* 302:    */    }
/* 303:303 */    return false;
/* 304:    */  }
/* 305:    */  
/* 306:306 */  public boolean containsValue(boolean v) { boolean[] value = this.value;
/* 307:307 */    boolean[] used = this.used;
/* 308:308 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/* 309:309 */    return false;
/* 310:    */  }
/* 311:    */  
/* 316:    */  public void clear()
/* 317:    */  {
/* 318:318 */    if (this.size == 0) return;
/* 319:319 */    this.size = 0;
/* 320:320 */    BooleanArrays.fill(this.used, false);
/* 321:    */    
/* 322:322 */    ObjectArrays.fill(this.key, null);
/* 323:    */  }
/* 324:    */  
/* 325:325 */  public int size() { return this.size; }
/* 326:    */  
/* 327:    */  public boolean isEmpty() {
/* 328:328 */    return this.size == 0;
/* 329:    */  }
/* 330:    */  
/* 335:    */  @Deprecated
/* 336:    */  public void growthFactor(int growthFactor) {}
/* 337:    */  
/* 342:    */  @Deprecated
/* 343:    */  public int growthFactor()
/* 344:    */  {
/* 345:345 */    return 16;
/* 346:    */  }
/* 347:    */  
/* 348:    */  private final class MapEntry
/* 349:    */    implements Object2BooleanMap.Entry<K>, Map.Entry<K, Boolean>
/* 350:    */  {
/* 351:    */    private int index;
/* 352:    */    
/* 353:    */    MapEntry(int index)
/* 354:    */    {
/* 355:355 */      this.index = index;
/* 356:    */    }
/* 357:    */    
/* 358:358 */    public K getKey() { return Object2BooleanOpenHashMap.this.key[this.index]; }
/* 359:    */    
/* 360:    */    public Boolean getValue() {
/* 361:361 */      return Boolean.valueOf(Object2BooleanOpenHashMap.this.value[this.index]);
/* 362:    */    }
/* 363:    */    
/* 364:364 */    public boolean getBooleanValue() { return Object2BooleanOpenHashMap.this.value[this.index]; }
/* 365:    */    
/* 366:    */    public boolean setValue(boolean v) {
/* 367:367 */      boolean oldValue = Object2BooleanOpenHashMap.this.value[this.index];
/* 368:368 */      Object2BooleanOpenHashMap.this.value[this.index] = v;
/* 369:369 */      return oldValue;
/* 370:    */    }
/* 371:    */    
/* 372:372 */    public Boolean setValue(Boolean v) { return Boolean.valueOf(setValue(v.booleanValue())); }
/* 373:    */    
/* 374:    */    public boolean equals(Object o)
/* 375:    */    {
/* 376:376 */      if (!(o instanceof Map.Entry)) return false;
/* 377:377 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/* 378:378 */      return (Object2BooleanOpenHashMap.this.key[this.index] == null ? e.getKey() == null : Object2BooleanOpenHashMap.this.key[this.index].equals(e.getKey())) && (Object2BooleanOpenHashMap.this.value[this.index] == ((Boolean)e.getValue()).booleanValue());
/* 379:    */    }
/* 380:    */    
/* 381:381 */    public int hashCode() { return (Object2BooleanOpenHashMap.this.key[this.index] == null ? 0 : Object2BooleanOpenHashMap.this.key[this.index].hashCode()) ^ (Object2BooleanOpenHashMap.this.value[this.index] != 0 ? 1231 : 1237); }
/* 382:    */    
/* 384:384 */    public String toString() { return Object2BooleanOpenHashMap.this.key[this.index] + "=>" + Object2BooleanOpenHashMap.this.value[this.index]; } }
/* 385:    */  
/* 386:    */  private class MapIterator { int pos;
/* 387:    */    int last;
/* 388:    */    int c;
/* 389:    */    ObjectArrayList<K> wrapped;
/* 390:    */    
/* 391:391 */    private MapIterator() { this.pos = Object2BooleanOpenHashMap.this.n;
/* 392:    */      
/* 394:394 */      this.last = -1;
/* 395:    */      
/* 396:396 */      this.c = Object2BooleanOpenHashMap.this.size;
/* 397:    */      
/* 401:401 */      boolean[] used = Object2BooleanOpenHashMap.this.used;
/* 402:402 */      while ((this.c != 0) && (used[(--this.pos)] == 0)) {}
/* 403:    */    }
/* 404:    */    
/* 405:405 */    public boolean hasNext() { return this.c != 0; }
/* 406:    */    
/* 407:    */    public int nextEntry() {
/* 408:408 */      if (!hasNext()) throw new NoSuchElementException();
/* 409:409 */      this.c -= 1;
/* 410:    */      
/* 411:411 */      if (this.pos < 0) {
/* 412:412 */        Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/* 413:    */        
/* 414:414 */        int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2BooleanOpenHashMap.this.mask;
/* 415:    */        
/* 416:416 */        while (Object2BooleanOpenHashMap.this.used[pos] != 0) {
/* 417:417 */          if (Object2BooleanOpenHashMap.this.key[pos] == null ? k == null : Object2BooleanOpenHashMap.this.key[pos].equals(k)) return pos;
/* 418:418 */          pos = pos + 1 & Object2BooleanOpenHashMap.this.mask;
/* 419:    */        }
/* 420:    */      }
/* 421:421 */      this.last = this.pos;
/* 422:    */      
/* 423:423 */      if (this.c != 0) {
/* 424:424 */        boolean[] used = Object2BooleanOpenHashMap.this.used;
/* 425:425 */        while ((this.pos-- != 0) && (used[this.pos] == 0)) {}
/* 426:    */      }
/* 427:    */      
/* 428:428 */      return this.last;
/* 429:    */    }
/* 430:    */    
/* 434:    */    protected final int shiftKeys(int pos)
/* 435:    */    {
/* 436:    */      int last;
/* 437:    */      
/* 439:    */      for (;;)
/* 440:    */      {
/* 441:441 */        pos = (last = pos) + 1 & Object2BooleanOpenHashMap.this.mask;
/* 442:442 */        while (Object2BooleanOpenHashMap.this.used[pos] != 0) {
/* 443:443 */          int slot = (Object2BooleanOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2BooleanOpenHashMap.this.key[pos].hashCode())) & Object2BooleanOpenHashMap.this.mask;
/* 444:444 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 445:445 */          pos = pos + 1 & Object2BooleanOpenHashMap.this.mask;
/* 446:    */        }
/* 447:447 */        if (Object2BooleanOpenHashMap.this.used[pos] == 0) break;
/* 448:448 */        if (pos < last)
/* 449:    */        {
/* 450:450 */          if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 451:451 */          this.wrapped.add(Object2BooleanOpenHashMap.this.key[pos]);
/* 452:    */        }
/* 453:453 */        Object2BooleanOpenHashMap.this.key[last] = Object2BooleanOpenHashMap.this.key[pos];
/* 454:454 */        Object2BooleanOpenHashMap.this.value[last] = Object2BooleanOpenHashMap.this.value[pos];
/* 455:    */      }
/* 456:456 */      Object2BooleanOpenHashMap.this.used[last] = false;
/* 457:457 */      Object2BooleanOpenHashMap.this.key[last] = null;
/* 458:458 */      return last;
/* 459:    */    }
/* 460:    */    
/* 461:    */    public void remove() {
/* 462:462 */      if (this.last == -1) throw new IllegalStateException();
/* 463:463 */      if (this.pos < -1)
/* 464:    */      {
/* 465:465 */        Object2BooleanOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 466:466 */        this.last = -1;
/* 467:467 */        return;
/* 468:    */      }
/* 469:469 */      Object2BooleanOpenHashMap.this.size -= 1;
/* 470:470 */      if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 471:471 */        this.c += 1;
/* 472:472 */        nextEntry();
/* 473:    */      }
/* 474:474 */      this.last = -1;
/* 475:    */    }
/* 476:    */    
/* 477:    */    public int skip(int n) {
/* 478:478 */      int i = n;
/* 479:479 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 480:480 */      return n - i - 1;
/* 481:    */    } }
/* 482:    */  
/* 483:483 */  private class EntryIterator extends Object2BooleanOpenHashMap<K>.MapIterator implements ObjectIterator<Object2BooleanMap.Entry<K>> { private EntryIterator() { super(null); }
/* 484:    */    
/* 485:    */    private Object2BooleanOpenHashMap<K>.MapEntry entry;
/* 486:486 */    public Object2BooleanMap.Entry<K> next() { return this.entry = new Object2BooleanOpenHashMap.MapEntry(Object2BooleanOpenHashMap.this, nextEntry()); }
/* 487:    */    
/* 488:    */    public void remove()
/* 489:    */    {
/* 490:490 */      super.remove();
/* 491:491 */      Object2BooleanOpenHashMap.MapEntry.access$102(this.entry, -1);
/* 492:    */    } }
/* 493:    */  
/* 494:494 */  private class FastEntryIterator extends Object2BooleanOpenHashMap<K>.MapIterator implements ObjectIterator<Object2BooleanMap.Entry<K>> { private FastEntryIterator() { super(null); }
/* 495:495 */    final AbstractObject2BooleanMap.BasicEntry<K> entry = new AbstractObject2BooleanMap.BasicEntry(null, false);
/* 496:    */    
/* 497:497 */    public AbstractObject2BooleanMap.BasicEntry<K> next() { int e = nextEntry();
/* 498:498 */      this.entry.key = Object2BooleanOpenHashMap.this.key[e];
/* 499:499 */      this.entry.value = Object2BooleanOpenHashMap.this.value[e];
/* 500:500 */      return this.entry;
/* 501:    */    } }
/* 502:    */  
/* 503:    */  private final class MapEntrySet extends AbstractObjectSet<Object2BooleanMap.Entry<K>> implements Object2BooleanMap.FastEntrySet<K> { private MapEntrySet() {}
/* 504:    */    
/* 505:505 */    public ObjectIterator<Object2BooleanMap.Entry<K>> iterator() { return new Object2BooleanOpenHashMap.EntryIterator(Object2BooleanOpenHashMap.this, null); }
/* 506:    */    
/* 507:    */    public ObjectIterator<Object2BooleanMap.Entry<K>> fastIterator() {
/* 508:508 */      return new Object2BooleanOpenHashMap.FastEntryIterator(Object2BooleanOpenHashMap.this, null);
/* 509:    */    }
/* 510:    */    
/* 511:    */    public boolean contains(Object o) {
/* 512:512 */      if (!(o instanceof Map.Entry)) return false;
/* 513:513 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/* 514:514 */      K k = e.getKey();
/* 515:    */      
/* 516:516 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2BooleanOpenHashMap.this.mask;
/* 517:    */      
/* 518:518 */      while (Object2BooleanOpenHashMap.this.used[pos] != 0) {
/* 519:519 */        if (Object2BooleanOpenHashMap.this.key[pos] == null ? k == null : Object2BooleanOpenHashMap.this.key[pos].equals(k)) return Object2BooleanOpenHashMap.this.value[pos] == ((Boolean)e.getValue()).booleanValue();
/* 520:520 */        pos = pos + 1 & Object2BooleanOpenHashMap.this.mask;
/* 521:    */      }
/* 522:522 */      return false;
/* 523:    */    }
/* 524:    */    
/* 525:    */    public boolean remove(Object o) {
/* 526:526 */      if (!(o instanceof Map.Entry)) return false;
/* 527:527 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/* 528:528 */      K k = e.getKey();
/* 529:    */      
/* 530:530 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2BooleanOpenHashMap.this.mask;
/* 531:    */      
/* 532:532 */      while (Object2BooleanOpenHashMap.this.used[pos] != 0) {
/* 533:533 */        if (Object2BooleanOpenHashMap.this.key[pos] == null ? k == null : Object2BooleanOpenHashMap.this.key[pos].equals(k)) {
/* 534:534 */          Object2BooleanOpenHashMap.this.remove(e.getKey());
/* 535:535 */          return true;
/* 536:    */        }
/* 537:537 */        pos = pos + 1 & Object2BooleanOpenHashMap.this.mask;
/* 538:    */      }
/* 539:539 */      return false;
/* 540:    */    }
/* 541:    */    
/* 542:542 */    public int size() { return Object2BooleanOpenHashMap.this.size; }
/* 543:    */    
/* 545:545 */    public void clear() { Object2BooleanOpenHashMap.this.clear(); }
/* 546:    */  }
/* 547:    */  
/* 548:    */  public Object2BooleanMap.FastEntrySet<K> object2BooleanEntrySet() {
/* 549:549 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 550:550 */    return this.entries;
/* 551:    */  }
/* 552:    */  
/* 555:    */  private final class KeyIterator
/* 556:    */    extends Object2BooleanOpenHashMap<K>.MapIterator
/* 557:    */    implements ObjectIterator<K>
/* 558:    */  {
/* 559:559 */    public KeyIterator() { super(null); }
/* 560:560 */    public K next() { return Object2BooleanOpenHashMap.this.key[nextEntry()]; } }
/* 561:    */  
/* 562:    */  private final class KeySet extends AbstractObjectSet<K> { private KeySet() {}
/* 563:    */    
/* 564:564 */    public ObjectIterator<K> iterator() { return new Object2BooleanOpenHashMap.KeyIterator(Object2BooleanOpenHashMap.this); }
/* 565:    */    
/* 566:    */    public int size() {
/* 567:567 */      return Object2BooleanOpenHashMap.this.size;
/* 568:    */    }
/* 569:    */    
/* 570:570 */    public boolean contains(Object k) { return Object2BooleanOpenHashMap.this.containsKey(k); }
/* 571:    */    
/* 572:    */    public boolean remove(Object k) {
/* 573:573 */      int oldSize = Object2BooleanOpenHashMap.this.size;
/* 574:574 */      Object2BooleanOpenHashMap.this.remove(k);
/* 575:575 */      return Object2BooleanOpenHashMap.this.size != oldSize;
/* 576:    */    }
/* 577:    */    
/* 578:578 */    public void clear() { Object2BooleanOpenHashMap.this.clear(); }
/* 579:    */  }
/* 580:    */  
/* 581:    */  public ObjectSet<K> keySet() {
/* 582:582 */    if (this.keys == null) this.keys = new KeySet(null);
/* 583:583 */    return this.keys;
/* 584:    */  }
/* 585:    */  
/* 588:    */  private final class ValueIterator
/* 589:    */    extends Object2BooleanOpenHashMap.MapIterator
/* 590:    */    implements BooleanIterator
/* 591:    */  {
/* 592:592 */    public ValueIterator() { super(null); }
/* 593:593 */    public boolean nextBoolean() { return Object2BooleanOpenHashMap.this.value[nextEntry()]; }
/* 594:594 */    public Boolean next() { return Boolean.valueOf(Object2BooleanOpenHashMap.this.value[nextEntry()]); }
/* 595:    */  }
/* 596:    */  
/* 597:597 */  public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 598:    */        public BooleanIterator iterator() {
/* 599:599 */          return new Object2BooleanOpenHashMap.ValueIterator(Object2BooleanOpenHashMap.this);
/* 600:    */        }
/* 601:    */        
/* 602:602 */        public int size() { return Object2BooleanOpenHashMap.this.size; }
/* 603:    */        
/* 604:    */        public boolean contains(boolean v) {
/* 605:605 */          return Object2BooleanOpenHashMap.this.containsValue(v);
/* 606:    */        }
/* 607:    */        
/* 608:608 */        public void clear() { Object2BooleanOpenHashMap.this.clear(); }
/* 609:    */      };
/* 610:    */    }
/* 611:611 */    return this.values;
/* 612:    */  }
/* 613:    */  
/* 622:    */  @Deprecated
/* 623:    */  public boolean rehash()
/* 624:    */  {
/* 625:625 */    return true;
/* 626:    */  }
/* 627:    */  
/* 638:    */  public boolean trim()
/* 639:    */  {
/* 640:640 */    int l = HashCommon.arraySize(this.size, this.f);
/* 641:641 */    if (l >= this.n) return true;
/* 642:    */    try {
/* 643:643 */      rehash(l);
/* 644:    */    } catch (OutOfMemoryError cantDoIt) {
/* 645:645 */      return false; }
/* 646:646 */    return true;
/* 647:    */  }
/* 648:    */  
/* 665:    */  public boolean trim(int n)
/* 666:    */  {
/* 667:667 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 668:668 */    if (this.n <= l) return true;
/* 669:    */    try {
/* 670:670 */      rehash(l);
/* 671:    */    } catch (OutOfMemoryError cantDoIt) {
/* 672:672 */      return false; }
/* 673:673 */    return true;
/* 674:    */  }
/* 675:    */  
/* 684:    */  protected void rehash(int newN)
/* 685:    */  {
/* 686:686 */    int i = 0;
/* 687:687 */    boolean[] used = this.used;
/* 688:    */    
/* 689:689 */    K[] key = this.key;
/* 690:690 */    boolean[] value = this.value;
/* 691:691 */    int newMask = newN - 1;
/* 692:692 */    K[] newKey = (Object[])new Object[newN];
/* 693:693 */    boolean[] newValue = new boolean[newN];
/* 694:694 */    boolean[] newUsed = new boolean[newN];
/* 695:695 */    for (int j = this.size; j-- != 0;) {
/* 696:696 */      while (used[i] == 0) i++;
/* 697:697 */      K k = key[i];
/* 698:698 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
/* 699:699 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 700:700 */      newUsed[pos] = true;
/* 701:701 */      newKey[pos] = k;
/* 702:702 */      newValue[pos] = value[i];
/* 703:703 */      i++;
/* 704:    */    }
/* 705:705 */    this.n = newN;
/* 706:706 */    this.mask = newMask;
/* 707:707 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 708:708 */    this.key = newKey;
/* 709:709 */    this.value = newValue;
/* 710:710 */    this.used = newUsed;
/* 711:    */  }
/* 712:    */  
/* 716:    */  public Object2BooleanOpenHashMap<K> clone()
/* 717:    */  {
/* 718:    */    Object2BooleanOpenHashMap<K> c;
/* 719:    */    
/* 721:    */    try
/* 722:    */    {
/* 723:723 */      c = (Object2BooleanOpenHashMap)super.clone();
/* 724:    */    }
/* 725:    */    catch (CloneNotSupportedException cantHappen) {
/* 726:726 */      throw new InternalError();
/* 727:    */    }
/* 728:728 */    c.keys = null;
/* 729:729 */    c.values = null;
/* 730:730 */    c.entries = null;
/* 731:731 */    c.key = ((Object[])this.key.clone());
/* 732:732 */    c.value = ((boolean[])this.value.clone());
/* 733:733 */    c.used = ((boolean[])this.used.clone());
/* 734:734 */    return c;
/* 735:    */  }
/* 736:    */  
/* 744:    */  public int hashCode()
/* 745:    */  {
/* 746:746 */    int h = 0;
/* 747:747 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 748:748 */      while (this.used[i] == 0) i++;
/* 749:749 */      if (this != this.key[i])
/* 750:750 */        t = this.key[i] == null ? 0 : this.key[i].hashCode();
/* 751:751 */      t ^= (this.value[i] != 0 ? 1231 : 1237);
/* 752:752 */      h += t;
/* 753:753 */      i++;
/* 754:    */    }
/* 755:755 */    return h;
/* 756:    */  }
/* 757:    */  
/* 758:758 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 759:759 */    boolean[] value = this.value;
/* 760:760 */    Object2BooleanOpenHashMap<K>.MapIterator i = new MapIterator(null);
/* 761:761 */    s.defaultWriteObject();
/* 762:762 */    for (int j = this.size; j-- != 0;) {
/* 763:763 */      int e = i.nextEntry();
/* 764:764 */      s.writeObject(key[e]);
/* 765:765 */      s.writeBoolean(value[e]);
/* 766:    */    }
/* 767:    */  }
/* 768:    */  
/* 769:    */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 770:770 */    s.defaultReadObject();
/* 771:771 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 772:772 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 773:773 */    this.mask = (this.n - 1);
/* 774:774 */    K[] key = this.key = (Object[])new Object[this.n];
/* 775:775 */    boolean[] value = this.value = new boolean[this.n];
/* 776:776 */    boolean[] used = this.used = new boolean[this.n];
/* 777:    */    
/* 779:779 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 780:780 */      K k = s.readObject();
/* 781:781 */      boolean v = s.readBoolean();
/* 782:782 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 783:783 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 784:784 */      used[pos] = true;
/* 785:785 */      key[pos] = k;
/* 786:786 */      value[pos] = v;
/* 787:    */    }
/* 788:    */  }
/* 789:    */  
/* 790:    */  private void checkTable() {}
/* 791:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */