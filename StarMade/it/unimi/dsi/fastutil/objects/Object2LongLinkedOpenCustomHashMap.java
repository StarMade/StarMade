/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    5:     */import it.unimi.dsi.fastutil.HashCommon;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    7:     */import it.unimi.dsi.fastutil.longs.AbstractLongCollection;
/*    8:     */import it.unimi.dsi.fastutil.longs.LongCollection;
/*    9:     */import it.unimi.dsi.fastutil.longs.LongIterator;
/*   10:     */import it.unimi.dsi.fastutil.longs.LongListIterator;
/*   11:     */import java.io.IOException;
/*   12:     */import java.io.ObjectInputStream;
/*   13:     */import java.io.ObjectOutputStream;
/*   14:     */import java.io.Serializable;
/*   15:     */import java.util.Comparator;
/*   16:     */import java.util.Map;
/*   17:     */import java.util.Map.Entry;
/*   18:     */import java.util.NoSuchElementException;
/*   19:     */
/*  113:     */public class Object2LongLinkedOpenCustomHashMap<K>
/*  114:     */  extends AbstractObject2LongSortedMap<K>
/*  115:     */  implements Serializable, Cloneable, Hash
/*  116:     */{
/*  117:     */  public static final long serialVersionUID = 0L;
/*  118:     */  private static final boolean ASSERTS = false;
/*  119:     */  protected transient K[] key;
/*  120:     */  protected transient long[] value;
/*  121:     */  protected transient boolean[] used;
/*  122:     */  protected final float f;
/*  123:     */  protected transient int n;
/*  124:     */  protected transient int maxFill;
/*  125:     */  protected transient int mask;
/*  126:     */  protected int size;
/*  127:     */  protected volatile transient Object2LongSortedMap.FastSortedEntrySet<K> entries;
/*  128:     */  protected volatile transient ObjectSortedSet<K> keys;
/*  129:     */  protected volatile transient LongCollection values;
/*  130: 130 */  protected transient int first = -1;
/*  131:     */  
/*  132: 132 */  protected transient int last = -1;
/*  133:     */  
/*  139:     */  protected transient long[] link;
/*  140:     */  
/*  145:     */  protected Hash.Strategy<K> strategy;
/*  146:     */  
/*  152:     */  public Object2LongLinkedOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*  153:     */  {
/*  154: 154 */    this.strategy = strategy;
/*  155: 155 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  156: 156 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  157: 157 */    this.f = f;
/*  158: 158 */    this.n = HashCommon.arraySize(expected, f);
/*  159: 159 */    this.mask = (this.n - 1);
/*  160: 160 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  161: 161 */    this.key = ((Object[])new Object[this.n]);
/*  162: 162 */    this.value = new long[this.n];
/*  163: 163 */    this.used = new boolean[this.n];
/*  164: 164 */    this.link = new long[this.n];
/*  165:     */  }
/*  166:     */  
/*  170:     */  public Object2LongLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*  171:     */  {
/*  172: 172 */    this(expected, 0.75F, strategy);
/*  173:     */  }
/*  174:     */  
/*  177:     */  public Object2LongLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
/*  178:     */  {
/*  179: 179 */    this(16, 0.75F, strategy);
/*  180:     */  }
/*  181:     */  
/*  186:     */  public Object2LongLinkedOpenCustomHashMap(Map<? extends K, ? extends Long> m, float f, Hash.Strategy<K> strategy)
/*  187:     */  {
/*  188: 188 */    this(m.size(), f, strategy);
/*  189: 189 */    putAll(m);
/*  190:     */  }
/*  191:     */  
/*  195:     */  public Object2LongLinkedOpenCustomHashMap(Map<? extends K, ? extends Long> m, Hash.Strategy<K> strategy)
/*  196:     */  {
/*  197: 197 */    this(m, 0.75F, strategy);
/*  198:     */  }
/*  199:     */  
/*  204:     */  public Object2LongLinkedOpenCustomHashMap(Object2LongMap<K> m, float f, Hash.Strategy<K> strategy)
/*  205:     */  {
/*  206: 206 */    this(m.size(), f, strategy);
/*  207: 207 */    putAll(m);
/*  208:     */  }
/*  209:     */  
/*  213:     */  public Object2LongLinkedOpenCustomHashMap(Object2LongMap<K> m, Hash.Strategy<K> strategy)
/*  214:     */  {
/*  215: 215 */    this(m, 0.75F, strategy);
/*  216:     */  }
/*  217:     */  
/*  224:     */  public Object2LongLinkedOpenCustomHashMap(K[] k, long[] v, float f, Hash.Strategy<K> strategy)
/*  225:     */  {
/*  226: 226 */    this(k.length, f, strategy);
/*  227: 227 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  228: 228 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  229:     */    }
/*  230:     */  }
/*  231:     */  
/*  236:     */  public Object2LongLinkedOpenCustomHashMap(K[] k, long[] v, Hash.Strategy<K> strategy)
/*  237:     */  {
/*  238: 238 */    this(k, v, 0.75F, strategy);
/*  239:     */  }
/*  240:     */  
/*  243:     */  public Hash.Strategy<K> strategy()
/*  244:     */  {
/*  245: 245 */    return this.strategy;
/*  246:     */  }
/*  247:     */  
/*  251:     */  public long put(K k, long v)
/*  252:     */  {
/*  253: 253 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  254:     */    
/*  255: 255 */    while (this.used[pos] != 0) {
/*  256: 256 */      if (this.strategy.equals(this.key[pos], k)) {
/*  257: 257 */        long oldValue = this.value[pos];
/*  258: 258 */        this.value[pos] = v;
/*  259: 259 */        return oldValue;
/*  260:     */      }
/*  261: 261 */      pos = pos + 1 & this.mask;
/*  262:     */    }
/*  263: 263 */    this.used[pos] = true;
/*  264: 264 */    this.key[pos] = k;
/*  265: 265 */    this.value[pos] = v;
/*  266: 266 */    if (this.size == 0) {
/*  267: 267 */      this.first = (this.last = pos);
/*  268:     */      
/*  269: 269 */      this.link[pos] = -1L;
/*  270:     */    }
/*  271:     */    else {
/*  272: 272 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  273: 273 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  274: 274 */      this.last = pos;
/*  275:     */    }
/*  276: 276 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  277:     */    }
/*  278: 278 */    return this.defRetValue;
/*  279:     */  }
/*  280:     */  
/*  281: 281 */  public Long put(K ok, Long ov) { long v = ov.longValue();
/*  282: 282 */    K k = ok;
/*  283:     */    
/*  284: 284 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  285:     */    
/*  286: 286 */    while (this.used[pos] != 0) {
/*  287: 287 */      if (this.strategy.equals(this.key[pos], k)) {
/*  288: 288 */        Long oldValue = Long.valueOf(this.value[pos]);
/*  289: 289 */        this.value[pos] = v;
/*  290: 290 */        return oldValue;
/*  291:     */      }
/*  292: 292 */      pos = pos + 1 & this.mask;
/*  293:     */    }
/*  294: 294 */    this.used[pos] = true;
/*  295: 295 */    this.key[pos] = k;
/*  296: 296 */    this.value[pos] = v;
/*  297: 297 */    if (this.size == 0) {
/*  298: 298 */      this.first = (this.last = pos);
/*  299:     */      
/*  300: 300 */      this.link[pos] = -1L;
/*  301:     */    }
/*  302:     */    else {
/*  303: 303 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  304: 304 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  305: 305 */      this.last = pos;
/*  306:     */    }
/*  307: 307 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  308:     */    }
/*  309: 309 */    return null;
/*  310:     */  }
/*  311:     */  
/*  322:     */  public long add(K k, long incr)
/*  323:     */  {
/*  324: 324 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  325:     */    
/*  326: 326 */    while (this.used[pos] != 0) {
/*  327: 327 */      if (this.strategy.equals(this.key[pos], k)) {
/*  328: 328 */        long oldValue = this.value[pos];
/*  329: 329 */        this.value[pos] += incr;
/*  330: 330 */        return oldValue;
/*  331:     */      }
/*  332: 332 */      pos = pos + 1 & this.mask;
/*  333:     */    }
/*  334: 334 */    this.used[pos] = true;
/*  335: 335 */    this.key[pos] = k;
/*  336: 336 */    this.value[pos] = (this.defRetValue + incr);
/*  337: 337 */    if (this.size == 0) {
/*  338: 338 */      this.first = (this.last = pos);
/*  339:     */      
/*  340: 340 */      this.link[pos] = -1L;
/*  341:     */    }
/*  342:     */    else {
/*  343: 343 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  344: 344 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  345: 345 */      this.last = pos;
/*  346:     */    }
/*  347: 347 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  348:     */    }
/*  349: 349 */    return this.defRetValue;
/*  350:     */  }
/*  351:     */  
/*  354:     */  protected final int shiftKeys(int pos)
/*  355:     */  {
/*  356:     */    int last;
/*  357:     */    
/*  359:     */    for (;;)
/*  360:     */    {
/*  361: 361 */      pos = (last = pos) + 1 & this.mask;
/*  362: 362 */      while (this.used[pos] != 0) {
/*  363: 363 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/*  364: 364 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  365: 365 */        pos = pos + 1 & this.mask;
/*  366:     */      }
/*  367: 367 */      if (this.used[pos] == 0) break;
/*  368: 368 */      this.key[last] = this.key[pos];
/*  369: 369 */      this.value[last] = this.value[pos];
/*  370: 370 */      fixPointers(pos, last);
/*  371:     */    }
/*  372: 372 */    this.used[last] = false;
/*  373: 373 */    this.key[last] = null;
/*  374: 374 */    return last;
/*  375:     */  }
/*  376:     */  
/*  377:     */  public long removeLong(Object k)
/*  378:     */  {
/*  379: 379 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  380:     */    
/*  381: 381 */    while (this.used[pos] != 0) {
/*  382: 382 */      if (this.strategy.equals(this.key[pos], k)) {
/*  383: 383 */        this.size -= 1;
/*  384: 384 */        fixPointers(pos);
/*  385: 385 */        long v = this.value[pos];
/*  386: 386 */        shiftKeys(pos);
/*  387: 387 */        return v;
/*  388:     */      }
/*  389: 389 */      pos = pos + 1 & this.mask;
/*  390:     */    }
/*  391: 391 */    return this.defRetValue;
/*  392:     */  }
/*  393:     */  
/*  394:     */  public Long remove(Object ok) {
/*  395: 395 */    K k = ok;
/*  396:     */    
/*  397: 397 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  398:     */    
/*  399: 399 */    while (this.used[pos] != 0) {
/*  400: 400 */      if (this.strategy.equals(this.key[pos], k)) {
/*  401: 401 */        this.size -= 1;
/*  402: 402 */        fixPointers(pos);
/*  403: 403 */        long v = this.value[pos];
/*  404: 404 */        shiftKeys(pos);
/*  405: 405 */        return Long.valueOf(v);
/*  406:     */      }
/*  407: 407 */      pos = pos + 1 & this.mask;
/*  408:     */    }
/*  409: 409 */    return null;
/*  410:     */  }
/*  411:     */  
/*  414:     */  public long removeFirstLong()
/*  415:     */  {
/*  416: 416 */    if (this.size == 0) throw new NoSuchElementException();
/*  417: 417 */    this.size -= 1;
/*  418: 418 */    int pos = this.first;
/*  419:     */    
/*  420: 420 */    this.first = ((int)this.link[pos]);
/*  421: 421 */    if (0 <= this.first)
/*  422:     */    {
/*  423: 423 */      this.link[this.first] |= -4294967296L;
/*  424:     */    }
/*  425: 425 */    long v = this.value[pos];
/*  426: 426 */    shiftKeys(pos);
/*  427: 427 */    return v;
/*  428:     */  }
/*  429:     */  
/*  432:     */  public long removeLastLong()
/*  433:     */  {
/*  434: 434 */    if (this.size == 0) throw new NoSuchElementException();
/*  435: 435 */    this.size -= 1;
/*  436: 436 */    int pos = this.last;
/*  437:     */    
/*  438: 438 */    this.last = ((int)(this.link[pos] >>> 32));
/*  439: 439 */    if (0 <= this.last)
/*  440:     */    {
/*  441: 441 */      this.link[this.last] |= 4294967295L;
/*  442:     */    }
/*  443: 443 */    long v = this.value[pos];
/*  444: 444 */    shiftKeys(pos);
/*  445: 445 */    return v;
/*  446:     */  }
/*  447:     */  
/*  448: 448 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  449: 449 */    if (this.last == i) {
/*  450: 450 */      this.last = ((int)(this.link[i] >>> 32));
/*  451:     */      
/*  452: 452 */      this.link[this.last] |= 4294967295L;
/*  453:     */    }
/*  454:     */    else {
/*  455: 455 */      long linki = this.link[i];
/*  456: 456 */      int prev = (int)(linki >>> 32);
/*  457: 457 */      int next = (int)linki;
/*  458: 458 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  459: 459 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  460:     */    }
/*  461: 461 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  462: 462 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  463: 463 */    this.first = i;
/*  464:     */  }
/*  465:     */  
/*  466: 466 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  467: 467 */    if (this.first == i) {
/*  468: 468 */      this.first = ((int)this.link[i]);
/*  469:     */      
/*  470: 470 */      this.link[this.first] |= -4294967296L;
/*  471:     */    }
/*  472:     */    else {
/*  473: 473 */      long linki = this.link[i];
/*  474: 474 */      int prev = (int)(linki >>> 32);
/*  475: 475 */      int next = (int)linki;
/*  476: 476 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  477: 477 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  478:     */    }
/*  479: 479 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  480: 480 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  481: 481 */    this.last = i;
/*  482:     */  }
/*  483:     */  
/*  487:     */  public long getAndMoveToFirst(K k)
/*  488:     */  {
/*  489: 489 */    K[] key = this.key;
/*  490: 490 */    boolean[] used = this.used;
/*  491: 491 */    int mask = this.mask;
/*  492:     */    
/*  493: 493 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  494:     */    
/*  495: 495 */    while (used[pos] != 0) {
/*  496: 496 */      if (this.strategy.equals(k, key[pos])) {
/*  497: 497 */        moveIndexToFirst(pos);
/*  498: 498 */        return this.value[pos];
/*  499:     */      }
/*  500: 500 */      pos = pos + 1 & mask;
/*  501:     */    }
/*  502: 502 */    return this.defRetValue;
/*  503:     */  }
/*  504:     */  
/*  508:     */  public long getAndMoveToLast(K k)
/*  509:     */  {
/*  510: 510 */    K[] key = this.key;
/*  511: 511 */    boolean[] used = this.used;
/*  512: 512 */    int mask = this.mask;
/*  513:     */    
/*  514: 514 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  515:     */    
/*  516: 516 */    while (used[pos] != 0) {
/*  517: 517 */      if (this.strategy.equals(k, key[pos])) {
/*  518: 518 */        moveIndexToLast(pos);
/*  519: 519 */        return this.value[pos];
/*  520:     */      }
/*  521: 521 */      pos = pos + 1 & mask;
/*  522:     */    }
/*  523: 523 */    return this.defRetValue;
/*  524:     */  }
/*  525:     */  
/*  530:     */  public long putAndMoveToFirst(K k, long v)
/*  531:     */  {
/*  532: 532 */    K[] key = this.key;
/*  533: 533 */    boolean[] used = this.used;
/*  534: 534 */    int mask = this.mask;
/*  535:     */    
/*  536: 536 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  537:     */    
/*  538: 538 */    while (used[pos] != 0) {
/*  539: 539 */      if (this.strategy.equals(k, key[pos])) {
/*  540: 540 */        long oldValue = this.value[pos];
/*  541: 541 */        this.value[pos] = v;
/*  542: 542 */        moveIndexToFirst(pos);
/*  543: 543 */        return oldValue;
/*  544:     */      }
/*  545: 545 */      pos = pos + 1 & mask;
/*  546:     */    }
/*  547: 547 */    used[pos] = true;
/*  548: 548 */    key[pos] = k;
/*  549: 549 */    this.value[pos] = v;
/*  550: 550 */    if (this.size == 0) {
/*  551: 551 */      this.first = (this.last = pos);
/*  552:     */      
/*  553: 553 */      this.link[pos] = -1L;
/*  554:     */    }
/*  555:     */    else {
/*  556: 556 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  557: 557 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  558: 558 */      this.first = pos;
/*  559:     */    }
/*  560: 560 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  561:     */    }
/*  562: 562 */    return this.defRetValue;
/*  563:     */  }
/*  564:     */  
/*  569:     */  public long putAndMoveToLast(K k, long v)
/*  570:     */  {
/*  571: 571 */    K[] key = this.key;
/*  572: 572 */    boolean[] used = this.used;
/*  573: 573 */    int mask = this.mask;
/*  574:     */    
/*  575: 575 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  576:     */    
/*  577: 577 */    while (used[pos] != 0) {
/*  578: 578 */      if (this.strategy.equals(k, key[pos])) {
/*  579: 579 */        long oldValue = this.value[pos];
/*  580: 580 */        this.value[pos] = v;
/*  581: 581 */        moveIndexToLast(pos);
/*  582: 582 */        return oldValue;
/*  583:     */      }
/*  584: 584 */      pos = pos + 1 & mask;
/*  585:     */    }
/*  586: 586 */    used[pos] = true;
/*  587: 587 */    key[pos] = k;
/*  588: 588 */    this.value[pos] = v;
/*  589: 589 */    if (this.size == 0) {
/*  590: 590 */      this.first = (this.last = pos);
/*  591:     */      
/*  592: 592 */      this.link[pos] = -1L;
/*  593:     */    }
/*  594:     */    else {
/*  595: 595 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  596: 596 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  597: 597 */      this.last = pos;
/*  598:     */    }
/*  599: 599 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  600:     */    }
/*  601: 601 */    return this.defRetValue;
/*  602:     */  }
/*  603:     */  
/*  604:     */  public long getLong(Object k)
/*  605:     */  {
/*  606: 606 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  607:     */    
/*  608: 608 */    while (this.used[pos] != 0) {
/*  609: 609 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/*  610: 610 */      pos = pos + 1 & this.mask;
/*  611:     */    }
/*  612: 612 */    return this.defRetValue;
/*  613:     */  }
/*  614:     */  
/*  615:     */  public boolean containsKey(Object k)
/*  616:     */  {
/*  617: 617 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  618:     */    
/*  619: 619 */    while (this.used[pos] != 0) {
/*  620: 620 */      if (this.strategy.equals(this.key[pos], k)) return true;
/*  621: 621 */      pos = pos + 1 & this.mask;
/*  622:     */    }
/*  623: 623 */    return false;
/*  624:     */  }
/*  625:     */  
/*  626: 626 */  public boolean containsValue(long v) { long[] value = this.value;
/*  627: 627 */    boolean[] used = this.used;
/*  628: 628 */    for (int i = this.n; i-- != 0; return true) label17: if ((used[i] == 0) || (value[i] != v)) break label17;
/*  629: 629 */    return false;
/*  630:     */  }
/*  631:     */  
/*  636:     */  public void clear()
/*  637:     */  {
/*  638: 638 */    if (this.size == 0) return;
/*  639: 639 */    this.size = 0;
/*  640: 640 */    BooleanArrays.fill(this.used, false);
/*  641:     */    
/*  642: 642 */    ObjectArrays.fill(this.key, null);
/*  643: 643 */    this.first = (this.last = -1);
/*  644:     */  }
/*  645:     */  
/*  646: 646 */  public int size() { return this.size; }
/*  647:     */  
/*  648:     */  public boolean isEmpty() {
/*  649: 649 */    return this.size == 0;
/*  650:     */  }
/*  651:     */  
/*  656:     */  @Deprecated
/*  657:     */  public void growthFactor(int growthFactor) {}
/*  658:     */  
/*  663:     */  @Deprecated
/*  664:     */  public int growthFactor()
/*  665:     */  {
/*  666: 666 */    return 16;
/*  667:     */  }
/*  668:     */  
/*  669:     */  private final class MapEntry
/*  670:     */    implements Object2LongMap.Entry<K>, Map.Entry<K, Long>
/*  671:     */  {
/*  672:     */    private int index;
/*  673:     */    
/*  674:     */    MapEntry(int index)
/*  675:     */    {
/*  676: 676 */      this.index = index;
/*  677:     */    }
/*  678:     */    
/*  679: 679 */    public K getKey() { return Object2LongLinkedOpenCustomHashMap.this.key[this.index]; }
/*  680:     */    
/*  681:     */    public Long getValue() {
/*  682: 682 */      return Long.valueOf(Object2LongLinkedOpenCustomHashMap.this.value[this.index]);
/*  683:     */    }
/*  684:     */    
/*  685: 685 */    public long getLongValue() { return Object2LongLinkedOpenCustomHashMap.this.value[this.index]; }
/*  686:     */    
/*  687:     */    public long setValue(long v) {
/*  688: 688 */      long oldValue = Object2LongLinkedOpenCustomHashMap.this.value[this.index];
/*  689: 689 */      Object2LongLinkedOpenCustomHashMap.this.value[this.index] = v;
/*  690: 690 */      return oldValue;
/*  691:     */    }
/*  692:     */    
/*  693: 693 */    public Long setValue(Long v) { return Long.valueOf(setValue(v.longValue())); }
/*  694:     */    
/*  695:     */    public boolean equals(Object o)
/*  696:     */    {
/*  697: 697 */      if (!(o instanceof Map.Entry)) return false;
/*  698: 698 */      Map.Entry<K, Long> e = (Map.Entry)o;
/*  699: 699 */      return (Object2LongLinkedOpenCustomHashMap.this.strategy.equals(Object2LongLinkedOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2LongLinkedOpenCustomHashMap.this.value[this.index] == ((Long)e.getValue()).longValue());
/*  700:     */    }
/*  701:     */    
/*  702: 702 */    public int hashCode() { return Object2LongLinkedOpenCustomHashMap.this.strategy.hashCode(Object2LongLinkedOpenCustomHashMap.this.key[this.index]) ^ HashCommon.long2int(Object2LongLinkedOpenCustomHashMap.this.value[this.index]); }
/*  703:     */    
/*  704:     */    public String toString() {
/*  705: 705 */      return Object2LongLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2LongLinkedOpenCustomHashMap.this.value[this.index];
/*  706:     */    }
/*  707:     */  }
/*  708:     */  
/*  714:     */  protected void fixPointers(int i)
/*  715:     */  {
/*  716: 716 */    if (this.size == 0) {
/*  717: 717 */      this.first = (this.last = -1);
/*  718: 718 */      return;
/*  719:     */    }
/*  720: 720 */    if (this.first == i) {
/*  721: 721 */      this.first = ((int)this.link[i]);
/*  722: 722 */      if (0 <= this.first)
/*  723:     */      {
/*  724: 724 */        this.link[this.first] |= -4294967296L;
/*  725:     */      }
/*  726: 726 */      return;
/*  727:     */    }
/*  728: 728 */    if (this.last == i) {
/*  729: 729 */      this.last = ((int)(this.link[i] >>> 32));
/*  730: 730 */      if (0 <= this.last)
/*  731:     */      {
/*  732: 732 */        this.link[this.last] |= 4294967295L;
/*  733:     */      }
/*  734: 734 */      return;
/*  735:     */    }
/*  736: 736 */    long linki = this.link[i];
/*  737: 737 */    int prev = (int)(linki >>> 32);
/*  738: 738 */    int next = (int)linki;
/*  739: 739 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  740: 740 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  741:     */  }
/*  742:     */  
/*  749:     */  protected void fixPointers(int s, int d)
/*  750:     */  {
/*  751: 751 */    if (this.size == 1) {
/*  752: 752 */      this.first = (this.last = d);
/*  753:     */      
/*  754: 754 */      this.link[d] = -1L;
/*  755: 755 */      return;
/*  756:     */    }
/*  757: 757 */    if (this.first == s) {
/*  758: 758 */      this.first = d;
/*  759: 759 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  760: 760 */      this.link[d] = this.link[s];
/*  761: 761 */      return;
/*  762:     */    }
/*  763: 763 */    if (this.last == s) {
/*  764: 764 */      this.last = d;
/*  765: 765 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  766: 766 */      this.link[d] = this.link[s];
/*  767: 767 */      return;
/*  768:     */    }
/*  769: 769 */    long links = this.link[s];
/*  770: 770 */    int prev = (int)(links >>> 32);
/*  771: 771 */    int next = (int)links;
/*  772: 772 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  773: 773 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  774: 774 */    this.link[d] = links;
/*  775:     */  }
/*  776:     */  
/*  779:     */  public K firstKey()
/*  780:     */  {
/*  781: 781 */    if (this.size == 0) throw new NoSuchElementException();
/*  782: 782 */    return this.key[this.first];
/*  783:     */  }
/*  784:     */  
/*  787:     */  public K lastKey()
/*  788:     */  {
/*  789: 789 */    if (this.size == 0) throw new NoSuchElementException();
/*  790: 790 */    return this.key[this.last]; }
/*  791:     */  
/*  792: 792 */  public Comparator<? super K> comparator() { return null; }
/*  793: 793 */  public Object2LongSortedMap<K> tailMap(K from) { throw new UnsupportedOperationException(); }
/*  794: 794 */  public Object2LongSortedMap<K> headMap(K to) { throw new UnsupportedOperationException(); }
/*  795: 795 */  public Object2LongSortedMap<K> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*  796:     */  
/*  802:     */  private class MapIterator
/*  803:     */  {
/*  804: 804 */    int prev = -1;
/*  805:     */    
/*  806: 806 */    int next = -1;
/*  807:     */    
/*  808: 808 */    int curr = -1;
/*  809:     */    
/*  810: 810 */    int index = -1;
/*  811:     */    
/*  812: 812 */    private MapIterator() { this.next = Object2LongLinkedOpenCustomHashMap.this.first;
/*  813: 813 */      this.index = 0;
/*  814:     */    }
/*  815:     */    
/*  816: 816 */    private MapIterator() { if (Object2LongLinkedOpenCustomHashMap.this.strategy.equals(Object2LongLinkedOpenCustomHashMap.this.key[Object2LongLinkedOpenCustomHashMap.this.last], from)) {
/*  817: 817 */        this.prev = Object2LongLinkedOpenCustomHashMap.this.last;
/*  818: 818 */        this.index = Object2LongLinkedOpenCustomHashMap.this.size;
/*  819:     */      }
/*  820:     */      else
/*  821:     */      {
/*  822: 822 */        int pos = HashCommon.murmurHash3(Object2LongLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  823:     */        
/*  824: 824 */        while (Object2LongLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  825: 825 */          if (Object2LongLinkedOpenCustomHashMap.this.strategy.equals(Object2LongLinkedOpenCustomHashMap.this.key[pos], from))
/*  826:     */          {
/*  827: 827 */            this.next = ((int)Object2LongLinkedOpenCustomHashMap.this.link[pos]);
/*  828: 828 */            this.prev = pos;
/*  829: 829 */            return;
/*  830:     */          }
/*  831: 831 */          pos = pos + 1 & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  832:     */        }
/*  833: 833 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  834:     */      } }
/*  835:     */    
/*  836: 836 */    public boolean hasNext() { return this.next != -1; }
/*  837: 837 */    public boolean hasPrevious() { return this.prev != -1; }
/*  838:     */    
/*  839: 839 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  840: 840 */      if (this.prev == -1) {
/*  841: 841 */        this.index = 0;
/*  842: 842 */        return;
/*  843:     */      }
/*  844: 844 */      if (this.next == -1) {
/*  845: 845 */        this.index = Object2LongLinkedOpenCustomHashMap.this.size;
/*  846: 846 */        return;
/*  847:     */      }
/*  848: 848 */      int pos = Object2LongLinkedOpenCustomHashMap.this.first;
/*  849: 849 */      this.index = 1;
/*  850: 850 */      while (pos != this.prev) {
/*  851: 851 */        pos = (int)Object2LongLinkedOpenCustomHashMap.this.link[pos];
/*  852: 852 */        this.index += 1;
/*  853:     */      }
/*  854:     */    }
/*  855:     */    
/*  856: 856 */    public int nextIndex() { ensureIndexKnown();
/*  857: 857 */      return this.index;
/*  858:     */    }
/*  859:     */    
/*  860: 860 */    public int previousIndex() { ensureIndexKnown();
/*  861: 861 */      return this.index - 1;
/*  862:     */    }
/*  863:     */    
/*  864: 864 */    public int nextEntry() { if (!hasNext()) return Object2LongLinkedOpenCustomHashMap.this.size();
/*  865: 865 */      this.curr = this.next;
/*  866: 866 */      this.next = ((int)Object2LongLinkedOpenCustomHashMap.this.link[this.curr]);
/*  867: 867 */      this.prev = this.curr;
/*  868: 868 */      if (this.index >= 0) this.index += 1;
/*  869: 869 */      return this.curr;
/*  870:     */    }
/*  871:     */    
/*  872: 872 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  873: 873 */      this.curr = this.prev;
/*  874: 874 */      this.prev = ((int)(Object2LongLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  875: 875 */      this.next = this.curr;
/*  876: 876 */      if (this.index >= 0) this.index -= 1;
/*  877: 877 */      return this.curr;
/*  878:     */    }
/*  879:     */    
/*  880:     */    public void remove() {
/*  881: 881 */      ensureIndexKnown();
/*  882: 882 */      if (this.curr == -1) throw new IllegalStateException();
/*  883: 883 */      if (this.curr == this.prev)
/*  884:     */      {
/*  886: 886 */        this.index -= 1;
/*  887: 887 */        this.prev = ((int)(Object2LongLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  888:     */      }
/*  889:     */      else {
/*  890: 890 */        this.next = ((int)Object2LongLinkedOpenCustomHashMap.this.link[this.curr]); }
/*  891: 891 */      Object2LongLinkedOpenCustomHashMap.this.size -= 1;
/*  892:     */      
/*  894: 894 */      if (this.prev == -1) { Object2LongLinkedOpenCustomHashMap.this.first = this.next;
/*  895:     */      } else
/*  896: 896 */        Object2LongLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2LongLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  897: 897 */      if (this.next == -1) { Object2LongLinkedOpenCustomHashMap.this.last = this.prev;
/*  898:     */      } else
/*  899: 899 */        Object2LongLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2LongLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  900: 900 */      int pos = this.curr;
/*  901:     */      int last;
/*  902:     */      for (;;) {
/*  903: 903 */        pos = (last = pos) + 1 & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  904: 904 */        while (Object2LongLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  905: 905 */          int slot = HashCommon.murmurHash3(Object2LongLinkedOpenCustomHashMap.this.strategy.hashCode(Object2LongLinkedOpenCustomHashMap.this.key[pos])) & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  906: 906 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  907: 907 */          pos = pos + 1 & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  908:     */        }
/*  909: 909 */        if (Object2LongLinkedOpenCustomHashMap.this.used[pos] == 0) break;
/*  910: 910 */        Object2LongLinkedOpenCustomHashMap.this.key[last] = Object2LongLinkedOpenCustomHashMap.this.key[pos];
/*  911: 911 */        Object2LongLinkedOpenCustomHashMap.this.value[last] = Object2LongLinkedOpenCustomHashMap.this.value[pos];
/*  912: 912 */        if (this.next == pos) this.next = last;
/*  913: 913 */        if (this.prev == pos) this.prev = last;
/*  914: 914 */        Object2LongLinkedOpenCustomHashMap.this.fixPointers(pos, last);
/*  915:     */      }
/*  916: 916 */      Object2LongLinkedOpenCustomHashMap.this.used[last] = false;
/*  917: 917 */      Object2LongLinkedOpenCustomHashMap.this.key[last] = null;
/*  918: 918 */      this.curr = -1;
/*  919:     */    }
/*  920:     */    
/*  921: 921 */    public int skip(int n) { int i = n;
/*  922: 922 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  923: 923 */      return n - i - 1;
/*  924:     */    }
/*  925:     */    
/*  926: 926 */    public int back(int n) { int i = n;
/*  927: 927 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  928: 928 */      return n - i - 1;
/*  929:     */    } }
/*  930:     */  
/*  931:     */  private class EntryIterator extends Object2LongLinkedOpenCustomHashMap<K>.MapIterator implements ObjectListIterator<Object2LongMap.Entry<K>> { private Object2LongLinkedOpenCustomHashMap<K>.MapEntry entry;
/*  932:     */    
/*  933: 933 */    public EntryIterator() { super(null); }
/*  934:     */    
/*  935: 935 */    public EntryIterator() { super(from, null); }
/*  936:     */    
/*  937:     */    public Object2LongLinkedOpenCustomHashMap<K>.MapEntry next() {
/*  938: 938 */      return this.entry = new Object2LongLinkedOpenCustomHashMap.MapEntry(Object2LongLinkedOpenCustomHashMap.this, nextEntry());
/*  939:     */    }
/*  940:     */    
/*  941: 941 */    public Object2LongLinkedOpenCustomHashMap<K>.MapEntry previous() { return this.entry = new Object2LongLinkedOpenCustomHashMap.MapEntry(Object2LongLinkedOpenCustomHashMap.this, previousEntry()); }
/*  942:     */    
/*  943:     */    public void remove()
/*  944:     */    {
/*  945: 945 */      super.remove();
/*  946: 946 */      Object2LongLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1); }
/*  947:     */    
/*  948: 948 */    public void set(Object2LongMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  949: 949 */    public void add(Object2LongMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  950:     */  }
/*  951:     */  
/*  952: 952 */  private class FastEntryIterator extends Object2LongLinkedOpenCustomHashMap<K>.MapIterator implements ObjectListIterator<Object2LongMap.Entry<K>> { final AbstractObject2LongMap.BasicEntry<K> entry = new AbstractObject2LongMap.BasicEntry(null, 0L);
/*  953: 953 */    public FastEntryIterator() { super(null); }
/*  954:     */    
/*  955: 955 */    public FastEntryIterator() { super(from, null); }
/*  956:     */    
/*  957:     */    public AbstractObject2LongMap.BasicEntry<K> next() {
/*  958: 958 */      int e = nextEntry();
/*  959: 959 */      this.entry.key = Object2LongLinkedOpenCustomHashMap.this.key[e];
/*  960: 960 */      this.entry.value = Object2LongLinkedOpenCustomHashMap.this.value[e];
/*  961: 961 */      return this.entry;
/*  962:     */    }
/*  963:     */    
/*  964: 964 */    public AbstractObject2LongMap.BasicEntry<K> previous() { int e = previousEntry();
/*  965: 965 */      this.entry.key = Object2LongLinkedOpenCustomHashMap.this.key[e];
/*  966: 966 */      this.entry.value = Object2LongLinkedOpenCustomHashMap.this.value[e];
/*  967: 967 */      return this.entry; }
/*  968:     */    
/*  969: 969 */    public void set(Object2LongMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  970: 970 */    public void add(Object2LongMap.Entry<K> ok) { throw new UnsupportedOperationException(); } }
/*  971:     */  
/*  972:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Object2LongMap.Entry<K>> implements Object2LongSortedMap.FastSortedEntrySet<K> { private MapEntrySet() {}
/*  973:     */    
/*  974: 974 */    public ObjectBidirectionalIterator<Object2LongMap.Entry<K>> iterator() { return new Object2LongLinkedOpenCustomHashMap.EntryIterator(Object2LongLinkedOpenCustomHashMap.this); }
/*  975:     */    
/*  976: 976 */    public Comparator<? super Object2LongMap.Entry<K>> comparator() { return null; }
/*  977: 977 */    public ObjectSortedSet<Object2LongMap.Entry<K>> subSet(Object2LongMap.Entry<K> fromElement, Object2LongMap.Entry<K> toElement) { throw new UnsupportedOperationException(); }
/*  978: 978 */    public ObjectSortedSet<Object2LongMap.Entry<K>> headSet(Object2LongMap.Entry<K> toElement) { throw new UnsupportedOperationException(); }
/*  979: 979 */    public ObjectSortedSet<Object2LongMap.Entry<K>> tailSet(Object2LongMap.Entry<K> fromElement) { throw new UnsupportedOperationException(); }
/*  980:     */    
/*  981: 981 */    public Object2LongMap.Entry<K> first() { if (Object2LongLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  982: 982 */      return new Object2LongLinkedOpenCustomHashMap.MapEntry(Object2LongLinkedOpenCustomHashMap.this, Object2LongLinkedOpenCustomHashMap.this.first);
/*  983:     */    }
/*  984:     */    
/*  985: 985 */    public Object2LongMap.Entry<K> last() { if (Object2LongLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  986: 986 */      return new Object2LongLinkedOpenCustomHashMap.MapEntry(Object2LongLinkedOpenCustomHashMap.this, Object2LongLinkedOpenCustomHashMap.this.last);
/*  987:     */    }
/*  988:     */    
/*  989:     */    public boolean contains(Object o) {
/*  990: 990 */      if (!(o instanceof Map.Entry)) return false;
/*  991: 991 */      Map.Entry<K, Long> e = (Map.Entry)o;
/*  992: 992 */      K k = e.getKey();
/*  993:     */      
/*  994: 994 */      int pos = HashCommon.murmurHash3(Object2LongLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  995:     */      
/*  996: 996 */      while (Object2LongLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  997: 997 */        if (Object2LongLinkedOpenCustomHashMap.this.strategy.equals(Object2LongLinkedOpenCustomHashMap.this.key[pos], k)) return Object2LongLinkedOpenCustomHashMap.this.value[pos] == ((Long)e.getValue()).longValue();
/*  998: 998 */        pos = pos + 1 & Object2LongLinkedOpenCustomHashMap.this.mask;
/*  999:     */      }
/* 1000:1000 */      return false;
/* 1001:     */    }
/* 1002:     */    
/* 1003:     */    public boolean remove(Object o) {
/* 1004:1004 */      if (!(o instanceof Map.Entry)) return false;
/* 1005:1005 */      Map.Entry<K, Long> e = (Map.Entry)o;
/* 1006:1006 */      K k = e.getKey();
/* 1007:     */      
/* 1008:1008 */      int pos = HashCommon.murmurHash3(Object2LongLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2LongLinkedOpenCustomHashMap.this.mask;
/* 1009:     */      
/* 1010:1010 */      while (Object2LongLinkedOpenCustomHashMap.this.used[pos] != 0) {
/* 1011:1011 */        if (Object2LongLinkedOpenCustomHashMap.this.strategy.equals(Object2LongLinkedOpenCustomHashMap.this.key[pos], k)) {
/* 1012:1012 */          Object2LongLinkedOpenCustomHashMap.this.remove(e.getKey());
/* 1013:1013 */          return true;
/* 1014:     */        }
/* 1015:1015 */        pos = pos + 1 & Object2LongLinkedOpenCustomHashMap.this.mask;
/* 1016:     */      }
/* 1017:1017 */      return false;
/* 1018:     */    }
/* 1019:     */    
/* 1020:1020 */    public int size() { return Object2LongLinkedOpenCustomHashMap.this.size; }
/* 1021:     */    
/* 1022:     */    public void clear() {
/* 1023:1023 */      Object2LongLinkedOpenCustomHashMap.this.clear();
/* 1024:     */    }
/* 1025:     */    
/* 1026:1026 */    public ObjectBidirectionalIterator<Object2LongMap.Entry<K>> iterator(Object2LongMap.Entry<K> from) { return new Object2LongLinkedOpenCustomHashMap.EntryIterator(Object2LongLinkedOpenCustomHashMap.this, from.getKey()); }
/* 1027:     */    
/* 1028:     */    public ObjectBidirectionalIterator<Object2LongMap.Entry<K>> fastIterator() {
/* 1029:1029 */      return new Object2LongLinkedOpenCustomHashMap.FastEntryIterator(Object2LongLinkedOpenCustomHashMap.this);
/* 1030:     */    }
/* 1031:     */    
/* 1032:1032 */    public ObjectBidirectionalIterator<Object2LongMap.Entry<K>> fastIterator(Object2LongMap.Entry<K> from) { return new Object2LongLinkedOpenCustomHashMap.FastEntryIterator(Object2LongLinkedOpenCustomHashMap.this, from.getKey()); }
/* 1033:     */  }
/* 1034:     */  
/* 1035:     */  public Object2LongSortedMap.FastSortedEntrySet<K> object2LongEntrySet() {
/* 1036:1036 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 1037:1037 */    return this.entries;
/* 1038:     */  }
/* 1039:     */  
/* 1042:     */  private final class KeyIterator
/* 1043:     */    extends Object2LongLinkedOpenCustomHashMap<K>.MapIterator
/* 1044:     */    implements ObjectListIterator<K>
/* 1045:     */  {
/* 1046:1046 */    public KeyIterator() { super(k, null); }
/* 1047:1047 */    public K previous() { return Object2LongLinkedOpenCustomHashMap.this.key[previousEntry()]; }
/* 1048:1048 */    public void set(K k) { throw new UnsupportedOperationException(); }
/* 1049:1049 */    public void add(K k) { throw new UnsupportedOperationException(); }
/* 1050:1050 */    public KeyIterator() { super(null); }
/* 1051:1051 */    public K next() { return Object2LongLinkedOpenCustomHashMap.this.key[nextEntry()]; } }
/* 1052:     */  
/* 1053:     */  private final class KeySet extends AbstractObjectSortedSet<K> { private KeySet() {}
/* 1054:     */    
/* 1055:1055 */    public ObjectListIterator<K> iterator(K from) { return new Object2LongLinkedOpenCustomHashMap.KeyIterator(Object2LongLinkedOpenCustomHashMap.this, from); }
/* 1056:     */    
/* 1057:     */    public ObjectListIterator<K> iterator() {
/* 1058:1058 */      return new Object2LongLinkedOpenCustomHashMap.KeyIterator(Object2LongLinkedOpenCustomHashMap.this);
/* 1059:     */    }
/* 1060:     */    
/* 1061:1061 */    public int size() { return Object2LongLinkedOpenCustomHashMap.this.size; }
/* 1062:     */    
/* 1064:1064 */    public boolean contains(Object k) { return Object2LongLinkedOpenCustomHashMap.this.containsKey(k); }
/* 1065:     */    
/* 1066:     */    public boolean remove(Object k) {
/* 1067:1067 */      int oldSize = Object2LongLinkedOpenCustomHashMap.this.size;
/* 1068:1068 */      Object2LongLinkedOpenCustomHashMap.this.remove(k);
/* 1069:1069 */      return Object2LongLinkedOpenCustomHashMap.this.size != oldSize;
/* 1070:     */    }
/* 1071:     */    
/* 1072:1072 */    public void clear() { Object2LongLinkedOpenCustomHashMap.this.clear(); }
/* 1073:     */    
/* 1074:     */    public K first() {
/* 1075:1075 */      if (Object2LongLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/* 1076:1076 */      return Object2LongLinkedOpenCustomHashMap.this.key[Object2LongLinkedOpenCustomHashMap.this.first];
/* 1077:     */    }
/* 1078:     */    
/* 1079:1079 */    public K last() { if (Object2LongLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/* 1080:1080 */      return Object2LongLinkedOpenCustomHashMap.this.key[Object2LongLinkedOpenCustomHashMap.this.last]; }
/* 1081:     */    
/* 1082:1082 */    public Comparator<? super K> comparator() { return null; }
/* 1083:1083 */    public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/* 1084:1084 */    public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/* 1085:1085 */    public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/* 1086:     */  }
/* 1087:     */  
/* 1088:1088 */  public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1089:1089 */    return this.keys;
/* 1090:     */  }
/* 1091:     */  
/* 1094:     */  private final class ValueIterator
/* 1095:     */    extends Object2LongLinkedOpenCustomHashMap.MapIterator
/* 1096:     */    implements LongListIterator
/* 1097:     */  {
/* 1098:1098 */    public long previousLong() { return Object2LongLinkedOpenCustomHashMap.this.value[previousEntry()]; }
/* 1099:1099 */    public Long previous() { return Long.valueOf(Object2LongLinkedOpenCustomHashMap.this.value[previousEntry()]); }
/* 1100:1100 */    public void set(Long ok) { throw new UnsupportedOperationException(); }
/* 1101:1101 */    public void add(Long ok) { throw new UnsupportedOperationException(); }
/* 1102:1102 */    public void set(long v) { throw new UnsupportedOperationException(); }
/* 1103:1103 */    public void add(long v) { throw new UnsupportedOperationException(); }
/* 1104:1104 */    public ValueIterator() { super(null); }
/* 1105:1105 */    public long nextLong() { return Object2LongLinkedOpenCustomHashMap.this.value[nextEntry()]; }
/* 1106:1106 */    public Long next() { return Long.valueOf(Object2LongLinkedOpenCustomHashMap.this.value[nextEntry()]); }
/* 1107:     */  }
/* 1108:     */  
/* 1109:1109 */  public LongCollection values() { if (this.values == null) { this.values = new AbstractLongCollection() {
/* 1110:     */        public LongIterator iterator() {
/* 1111:1111 */          return new Object2LongLinkedOpenCustomHashMap.ValueIterator(Object2LongLinkedOpenCustomHashMap.this);
/* 1112:     */        }
/* 1113:     */        
/* 1114:1114 */        public int size() { return Object2LongLinkedOpenCustomHashMap.this.size; }
/* 1115:     */        
/* 1116:     */        public boolean contains(long v) {
/* 1117:1117 */          return Object2LongLinkedOpenCustomHashMap.this.containsValue(v);
/* 1118:     */        }
/* 1119:     */        
/* 1120:1120 */        public void clear() { Object2LongLinkedOpenCustomHashMap.this.clear(); }
/* 1121:     */      };
/* 1122:     */    }
/* 1123:1123 */    return this.values;
/* 1124:     */  }
/* 1125:     */  
/* 1134:     */  @Deprecated
/* 1135:     */  public boolean rehash()
/* 1136:     */  {
/* 1137:1137 */    return true;
/* 1138:     */  }
/* 1139:     */  
/* 1150:     */  public boolean trim()
/* 1151:     */  {
/* 1152:1152 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1153:1153 */    if (l >= this.n) return true;
/* 1154:     */    try {
/* 1155:1155 */      rehash(l);
/* 1156:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1157:1157 */      return false; }
/* 1158:1158 */    return true;
/* 1159:     */  }
/* 1160:     */  
/* 1177:     */  public boolean trim(int n)
/* 1178:     */  {
/* 1179:1179 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1180:1180 */    if (this.n <= l) return true;
/* 1181:     */    try {
/* 1182:1182 */      rehash(l);
/* 1183:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1184:1184 */      return false; }
/* 1185:1185 */    return true;
/* 1186:     */  }
/* 1187:     */  
/* 1196:     */  protected void rehash(int newN)
/* 1197:     */  {
/* 1198:1198 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1199:     */    
/* 1200:1200 */    K[] key = this.key;
/* 1201:1201 */    long[] value = this.value;
/* 1202:1202 */    int newMask = newN - 1;
/* 1203:1203 */    K[] newKey = (Object[])new Object[newN];
/* 1204:1204 */    long[] newValue = new long[newN];
/* 1205:1205 */    boolean[] newUsed = new boolean[newN];
/* 1206:1206 */    long[] link = this.link;
/* 1207:1207 */    long[] newLink = new long[newN];
/* 1208:1208 */    this.first = -1;
/* 1209:1209 */    for (int j = this.size; j-- != 0;) {
/* 1210:1210 */      K k = key[i];
/* 1211:1211 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 1212:1212 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1213:1213 */      newUsed[pos] = true;
/* 1214:1214 */      newKey[pos] = k;
/* 1215:1215 */      newValue[pos] = value[i];
/* 1216:1216 */      if (prev != -1) {
/* 1217:1217 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1218:1218 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1219:1219 */        newPrev = pos;
/* 1220:     */      }
/* 1221:     */      else {
/* 1222:1222 */        newPrev = this.first = pos;
/* 1223:     */        
/* 1224:1224 */        newLink[pos] = -1L;
/* 1225:     */      }
/* 1226:1226 */      int t = i;
/* 1227:1227 */      i = (int)link[i];
/* 1228:1228 */      prev = t;
/* 1229:     */    }
/* 1230:1230 */    this.n = newN;
/* 1231:1231 */    this.mask = newMask;
/* 1232:1232 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1233:1233 */    this.key = newKey;
/* 1234:1234 */    this.value = newValue;
/* 1235:1235 */    this.used = newUsed;
/* 1236:1236 */    this.link = newLink;
/* 1237:1237 */    this.last = newPrev;
/* 1238:1238 */    if (newPrev != -1)
/* 1239:     */    {
/* 1240:1240 */      newLink[newPrev] |= 4294967295L;
/* 1241:     */    }
/* 1242:     */  }
/* 1243:     */  
/* 1246:     */  public Object2LongLinkedOpenCustomHashMap<K> clone()
/* 1247:     */  {
/* 1248:     */    Object2LongLinkedOpenCustomHashMap<K> c;
/* 1249:     */    
/* 1251:     */    try
/* 1252:     */    {
/* 1253:1253 */      c = (Object2LongLinkedOpenCustomHashMap)super.clone();
/* 1254:     */    }
/* 1255:     */    catch (CloneNotSupportedException cantHappen) {
/* 1256:1256 */      throw new InternalError();
/* 1257:     */    }
/* 1258:1258 */    c.keys = null;
/* 1259:1259 */    c.values = null;
/* 1260:1260 */    c.entries = null;
/* 1261:1261 */    c.key = ((Object[])this.key.clone());
/* 1262:1262 */    c.value = ((long[])this.value.clone());
/* 1263:1263 */    c.used = ((boolean[])this.used.clone());
/* 1264:1264 */    c.link = ((long[])this.link.clone());
/* 1265:1265 */    c.strategy = this.strategy;
/* 1266:1266 */    return c;
/* 1267:     */  }
/* 1268:     */  
/* 1276:     */  public int hashCode()
/* 1277:     */  {
/* 1278:1278 */    int h = 0;
/* 1279:1279 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1280:1280 */      while (this.used[i] == 0) i++;
/* 1281:1281 */      if (this != this.key[i])
/* 1282:1282 */        t = this.strategy.hashCode(this.key[i]);
/* 1283:1283 */      t ^= HashCommon.long2int(this.value[i]);
/* 1284:1284 */      h += t;
/* 1285:1285 */      i++;
/* 1286:     */    }
/* 1287:1287 */    return h;
/* 1288:     */  }
/* 1289:     */  
/* 1290:1290 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 1291:1291 */    long[] value = this.value;
/* 1292:1292 */    Object2LongLinkedOpenCustomHashMap<K>.MapIterator i = new MapIterator(null);
/* 1293:1293 */    s.defaultWriteObject();
/* 1294:1294 */    for (int j = this.size; j-- != 0;) {
/* 1295:1295 */      int e = i.nextEntry();
/* 1296:1296 */      s.writeObject(key[e]);
/* 1297:1297 */      s.writeLong(value[e]);
/* 1298:     */    }
/* 1299:     */  }
/* 1300:     */  
/* 1301:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1302:1302 */    s.defaultReadObject();
/* 1303:1303 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1304:1304 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1305:1305 */    this.mask = (this.n - 1);
/* 1306:1306 */    K[] key = this.key = (Object[])new Object[this.n];
/* 1307:1307 */    long[] value = this.value = new long[this.n];
/* 1308:1308 */    boolean[] used = this.used = new boolean[this.n];
/* 1309:1309 */    long[] link = this.link = new long[this.n];
/* 1310:1310 */    int prev = -1;
/* 1311:1311 */    this.first = (this.last = -1);
/* 1312:     */    
/* 1314:1314 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1315:1315 */      K k = s.readObject();
/* 1316:1316 */      long v = s.readLong();
/* 1317:1317 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 1318:1318 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1319:1319 */      used[pos] = true;
/* 1320:1320 */      key[pos] = k;
/* 1321:1321 */      value[pos] = v;
/* 1322:1322 */      if (this.first != -1) {
/* 1323:1323 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1324:1324 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1325:1325 */        prev = pos;
/* 1326:     */      }
/* 1327:     */      else {
/* 1328:1328 */        prev = this.first = pos;
/* 1329:     */        
/* 1330:1330 */        link[pos] |= -4294967296L;
/* 1331:     */      }
/* 1332:     */    }
/* 1333:1333 */    this.last = prev;
/* 1334:1334 */    if (prev != -1)
/* 1335:     */    {
/* 1336:1336 */      link[prev] |= 4294967295L;
/* 1337:     */    }
/* 1338:     */  }
/* 1339:     */  
/* 1340:     */  private void checkTable() {}
/* 1341:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2LongLinkedOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */