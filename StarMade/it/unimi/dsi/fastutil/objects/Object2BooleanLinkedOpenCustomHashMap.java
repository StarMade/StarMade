/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    5:     */import it.unimi.dsi.fastutil.HashCommon;
/*    6:     */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*    7:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    8:     */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*    9:     */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*   10:     */import it.unimi.dsi.fastutil.booleans.BooleanListIterator;
/*   11:     */import java.io.IOException;
/*   12:     */import java.io.ObjectInputStream;
/*   13:     */import java.io.ObjectOutputStream;
/*   14:     */import java.io.Serializable;
/*   15:     */import java.util.Comparator;
/*   16:     */import java.util.Map;
/*   17:     */import java.util.Map.Entry;
/*   18:     */import java.util.NoSuchElementException;
/*   19:     */
/*  113:     */public class Object2BooleanLinkedOpenCustomHashMap<K>
/*  114:     */  extends AbstractObject2BooleanSortedMap<K>
/*  115:     */  implements Serializable, Cloneable, Hash
/*  116:     */{
/*  117:     */  public static final long serialVersionUID = 0L;
/*  118:     */  private static final boolean ASSERTS = false;
/*  119:     */  protected transient K[] key;
/*  120:     */  protected transient boolean[] value;
/*  121:     */  protected transient boolean[] used;
/*  122:     */  protected final float f;
/*  123:     */  protected transient int n;
/*  124:     */  protected transient int maxFill;
/*  125:     */  protected transient int mask;
/*  126:     */  protected int size;
/*  127:     */  protected volatile transient Object2BooleanSortedMap.FastSortedEntrySet<K> entries;
/*  128:     */  protected volatile transient ObjectSortedSet<K> keys;
/*  129:     */  protected volatile transient BooleanCollection values;
/*  130: 130 */  protected transient int first = -1;
/*  131:     */  
/*  132: 132 */  protected transient int last = -1;
/*  133:     */  
/*  139:     */  protected transient long[] link;
/*  140:     */  
/*  145:     */  protected Hash.Strategy<K> strategy;
/*  146:     */  
/*  152:     */  public Object2BooleanLinkedOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*  153:     */  {
/*  154: 154 */    this.strategy = strategy;
/*  155: 155 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  156: 156 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  157: 157 */    this.f = f;
/*  158: 158 */    this.n = HashCommon.arraySize(expected, f);
/*  159: 159 */    this.mask = (this.n - 1);
/*  160: 160 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  161: 161 */    this.key = ((Object[])new Object[this.n]);
/*  162: 162 */    this.value = new boolean[this.n];
/*  163: 163 */    this.used = new boolean[this.n];
/*  164: 164 */    this.link = new long[this.n];
/*  165:     */  }
/*  166:     */  
/*  170:     */  public Object2BooleanLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*  171:     */  {
/*  172: 172 */    this(expected, 0.75F, strategy);
/*  173:     */  }
/*  174:     */  
/*  177:     */  public Object2BooleanLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
/*  178:     */  {
/*  179: 179 */    this(16, 0.75F, strategy);
/*  180:     */  }
/*  181:     */  
/*  186:     */  public Object2BooleanLinkedOpenCustomHashMap(Map<? extends K, ? extends Boolean> m, float f, Hash.Strategy<K> strategy)
/*  187:     */  {
/*  188: 188 */    this(m.size(), f, strategy);
/*  189: 189 */    putAll(m);
/*  190:     */  }
/*  191:     */  
/*  195:     */  public Object2BooleanLinkedOpenCustomHashMap(Map<? extends K, ? extends Boolean> m, Hash.Strategy<K> strategy)
/*  196:     */  {
/*  197: 197 */    this(m, 0.75F, strategy);
/*  198:     */  }
/*  199:     */  
/*  204:     */  public Object2BooleanLinkedOpenCustomHashMap(Object2BooleanMap<K> m, float f, Hash.Strategy<K> strategy)
/*  205:     */  {
/*  206: 206 */    this(m.size(), f, strategy);
/*  207: 207 */    putAll(m);
/*  208:     */  }
/*  209:     */  
/*  213:     */  public Object2BooleanLinkedOpenCustomHashMap(Object2BooleanMap<K> m, Hash.Strategy<K> strategy)
/*  214:     */  {
/*  215: 215 */    this(m, 0.75F, strategy);
/*  216:     */  }
/*  217:     */  
/*  224:     */  public Object2BooleanLinkedOpenCustomHashMap(K[] k, boolean[] v, float f, Hash.Strategy<K> strategy)
/*  225:     */  {
/*  226: 226 */    this(k.length, f, strategy);
/*  227: 227 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  228: 228 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  229:     */    }
/*  230:     */  }
/*  231:     */  
/*  236:     */  public Object2BooleanLinkedOpenCustomHashMap(K[] k, boolean[] v, Hash.Strategy<K> strategy)
/*  237:     */  {
/*  238: 238 */    this(k, v, 0.75F, strategy);
/*  239:     */  }
/*  240:     */  
/*  243:     */  public Hash.Strategy<K> strategy()
/*  244:     */  {
/*  245: 245 */    return this.strategy;
/*  246:     */  }
/*  247:     */  
/*  251:     */  public boolean put(K k, boolean v)
/*  252:     */  {
/*  253: 253 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  254:     */    
/*  255: 255 */    while (this.used[pos] != 0) {
/*  256: 256 */      if (this.strategy.equals(this.key[pos], k)) {
/*  257: 257 */        boolean oldValue = this.value[pos];
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
/*  281: 281 */  public Boolean put(K ok, Boolean ov) { boolean v = ov.booleanValue();
/*  282: 282 */    K k = ok;
/*  283:     */    
/*  284: 284 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  285:     */    
/*  286: 286 */    while (this.used[pos] != 0) {
/*  287: 287 */      if (this.strategy.equals(this.key[pos], k)) {
/*  288: 288 */        Boolean oldValue = Boolean.valueOf(this.value[pos]);
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
/*  314:     */  protected final int shiftKeys(int pos)
/*  315:     */  {
/*  316:     */    int last;
/*  317:     */    
/*  319:     */    for (;;)
/*  320:     */    {
/*  321: 321 */      pos = (last = pos) + 1 & this.mask;
/*  322: 322 */      while (this.used[pos] != 0) {
/*  323: 323 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/*  324: 324 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  325: 325 */        pos = pos + 1 & this.mask;
/*  326:     */      }
/*  327: 327 */      if (this.used[pos] == 0) break;
/*  328: 328 */      this.key[last] = this.key[pos];
/*  329: 329 */      this.value[last] = this.value[pos];
/*  330: 330 */      fixPointers(pos, last);
/*  331:     */    }
/*  332: 332 */    this.used[last] = false;
/*  333: 333 */    this.key[last] = null;
/*  334: 334 */    return last;
/*  335:     */  }
/*  336:     */  
/*  337:     */  public boolean removeBoolean(Object k)
/*  338:     */  {
/*  339: 339 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  340:     */    
/*  341: 341 */    while (this.used[pos] != 0) {
/*  342: 342 */      if (this.strategy.equals(this.key[pos], k)) {
/*  343: 343 */        this.size -= 1;
/*  344: 344 */        fixPointers(pos);
/*  345: 345 */        boolean v = this.value[pos];
/*  346: 346 */        shiftKeys(pos);
/*  347: 347 */        return v;
/*  348:     */      }
/*  349: 349 */      pos = pos + 1 & this.mask;
/*  350:     */    }
/*  351: 351 */    return this.defRetValue;
/*  352:     */  }
/*  353:     */  
/*  354:     */  public Boolean remove(Object ok) {
/*  355: 355 */    K k = ok;
/*  356:     */    
/*  357: 357 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  358:     */    
/*  359: 359 */    while (this.used[pos] != 0) {
/*  360: 360 */      if (this.strategy.equals(this.key[pos], k)) {
/*  361: 361 */        this.size -= 1;
/*  362: 362 */        fixPointers(pos);
/*  363: 363 */        boolean v = this.value[pos];
/*  364: 364 */        shiftKeys(pos);
/*  365: 365 */        return Boolean.valueOf(v);
/*  366:     */      }
/*  367: 367 */      pos = pos + 1 & this.mask;
/*  368:     */    }
/*  369: 369 */    return null;
/*  370:     */  }
/*  371:     */  
/*  374:     */  public boolean removeFirstBoolean()
/*  375:     */  {
/*  376: 376 */    if (this.size == 0) throw new NoSuchElementException();
/*  377: 377 */    this.size -= 1;
/*  378: 378 */    int pos = this.first;
/*  379:     */    
/*  380: 380 */    this.first = ((int)this.link[pos]);
/*  381: 381 */    if (0 <= this.first)
/*  382:     */    {
/*  383: 383 */      this.link[this.first] |= -4294967296L;
/*  384:     */    }
/*  385: 385 */    boolean v = this.value[pos];
/*  386: 386 */    shiftKeys(pos);
/*  387: 387 */    return v;
/*  388:     */  }
/*  389:     */  
/*  392:     */  public boolean removeLastBoolean()
/*  393:     */  {
/*  394: 394 */    if (this.size == 0) throw new NoSuchElementException();
/*  395: 395 */    this.size -= 1;
/*  396: 396 */    int pos = this.last;
/*  397:     */    
/*  398: 398 */    this.last = ((int)(this.link[pos] >>> 32));
/*  399: 399 */    if (0 <= this.last)
/*  400:     */    {
/*  401: 401 */      this.link[this.last] |= 4294967295L;
/*  402:     */    }
/*  403: 403 */    boolean v = this.value[pos];
/*  404: 404 */    shiftKeys(pos);
/*  405: 405 */    return v;
/*  406:     */  }
/*  407:     */  
/*  408: 408 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  409: 409 */    if (this.last == i) {
/*  410: 410 */      this.last = ((int)(this.link[i] >>> 32));
/*  411:     */      
/*  412: 412 */      this.link[this.last] |= 4294967295L;
/*  413:     */    }
/*  414:     */    else {
/*  415: 415 */      long linki = this.link[i];
/*  416: 416 */      int prev = (int)(linki >>> 32);
/*  417: 417 */      int next = (int)linki;
/*  418: 418 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  419: 419 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  420:     */    }
/*  421: 421 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  422: 422 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  423: 423 */    this.first = i;
/*  424:     */  }
/*  425:     */  
/*  426: 426 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  427: 427 */    if (this.first == i) {
/*  428: 428 */      this.first = ((int)this.link[i]);
/*  429:     */      
/*  430: 430 */      this.link[this.first] |= -4294967296L;
/*  431:     */    }
/*  432:     */    else {
/*  433: 433 */      long linki = this.link[i];
/*  434: 434 */      int prev = (int)(linki >>> 32);
/*  435: 435 */      int next = (int)linki;
/*  436: 436 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  437: 437 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  438:     */    }
/*  439: 439 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  440: 440 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  441: 441 */    this.last = i;
/*  442:     */  }
/*  443:     */  
/*  447:     */  public boolean getAndMoveToFirst(K k)
/*  448:     */  {
/*  449: 449 */    K[] key = this.key;
/*  450: 450 */    boolean[] used = this.used;
/*  451: 451 */    int mask = this.mask;
/*  452:     */    
/*  453: 453 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  454:     */    
/*  455: 455 */    while (used[pos] != 0) {
/*  456: 456 */      if (this.strategy.equals(k, key[pos])) {
/*  457: 457 */        moveIndexToFirst(pos);
/*  458: 458 */        return this.value[pos];
/*  459:     */      }
/*  460: 460 */      pos = pos + 1 & mask;
/*  461:     */    }
/*  462: 462 */    return this.defRetValue;
/*  463:     */  }
/*  464:     */  
/*  468:     */  public boolean getAndMoveToLast(K k)
/*  469:     */  {
/*  470: 470 */    K[] key = this.key;
/*  471: 471 */    boolean[] used = this.used;
/*  472: 472 */    int mask = this.mask;
/*  473:     */    
/*  474: 474 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  475:     */    
/*  476: 476 */    while (used[pos] != 0) {
/*  477: 477 */      if (this.strategy.equals(k, key[pos])) {
/*  478: 478 */        moveIndexToLast(pos);
/*  479: 479 */        return this.value[pos];
/*  480:     */      }
/*  481: 481 */      pos = pos + 1 & mask;
/*  482:     */    }
/*  483: 483 */    return this.defRetValue;
/*  484:     */  }
/*  485:     */  
/*  490:     */  public boolean putAndMoveToFirst(K k, boolean v)
/*  491:     */  {
/*  492: 492 */    K[] key = this.key;
/*  493: 493 */    boolean[] used = this.used;
/*  494: 494 */    int mask = this.mask;
/*  495:     */    
/*  496: 496 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  497:     */    
/*  498: 498 */    while (used[pos] != 0) {
/*  499: 499 */      if (this.strategy.equals(k, key[pos])) {
/*  500: 500 */        boolean oldValue = this.value[pos];
/*  501: 501 */        this.value[pos] = v;
/*  502: 502 */        moveIndexToFirst(pos);
/*  503: 503 */        return oldValue;
/*  504:     */      }
/*  505: 505 */      pos = pos + 1 & mask;
/*  506:     */    }
/*  507: 507 */    used[pos] = true;
/*  508: 508 */    key[pos] = k;
/*  509: 509 */    this.value[pos] = v;
/*  510: 510 */    if (this.size == 0) {
/*  511: 511 */      this.first = (this.last = pos);
/*  512:     */      
/*  513: 513 */      this.link[pos] = -1L;
/*  514:     */    }
/*  515:     */    else {
/*  516: 516 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  517: 517 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  518: 518 */      this.first = pos;
/*  519:     */    }
/*  520: 520 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  521:     */    }
/*  522: 522 */    return this.defRetValue;
/*  523:     */  }
/*  524:     */  
/*  529:     */  public boolean putAndMoveToLast(K k, boolean v)
/*  530:     */  {
/*  531: 531 */    K[] key = this.key;
/*  532: 532 */    boolean[] used = this.used;
/*  533: 533 */    int mask = this.mask;
/*  534:     */    
/*  535: 535 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  536:     */    
/*  537: 537 */    while (used[pos] != 0) {
/*  538: 538 */      if (this.strategy.equals(k, key[pos])) {
/*  539: 539 */        boolean oldValue = this.value[pos];
/*  540: 540 */        this.value[pos] = v;
/*  541: 541 */        moveIndexToLast(pos);
/*  542: 542 */        return oldValue;
/*  543:     */      }
/*  544: 544 */      pos = pos + 1 & mask;
/*  545:     */    }
/*  546: 546 */    used[pos] = true;
/*  547: 547 */    key[pos] = k;
/*  548: 548 */    this.value[pos] = v;
/*  549: 549 */    if (this.size == 0) {
/*  550: 550 */      this.first = (this.last = pos);
/*  551:     */      
/*  552: 552 */      this.link[pos] = -1L;
/*  553:     */    }
/*  554:     */    else {
/*  555: 555 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  556: 556 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  557: 557 */      this.last = pos;
/*  558:     */    }
/*  559: 559 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  560:     */    }
/*  561: 561 */    return this.defRetValue;
/*  562:     */  }
/*  563:     */  
/*  564:     */  public boolean getBoolean(Object k)
/*  565:     */  {
/*  566: 566 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  567:     */    
/*  568: 568 */    while (this.used[pos] != 0) {
/*  569: 569 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/*  570: 570 */      pos = pos + 1 & this.mask;
/*  571:     */    }
/*  572: 572 */    return this.defRetValue;
/*  573:     */  }
/*  574:     */  
/*  575:     */  public boolean containsKey(Object k)
/*  576:     */  {
/*  577: 577 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  578:     */    
/*  579: 579 */    while (this.used[pos] != 0) {
/*  580: 580 */      if (this.strategy.equals(this.key[pos], k)) return true;
/*  581: 581 */      pos = pos + 1 & this.mask;
/*  582:     */    }
/*  583: 583 */    return false;
/*  584:     */  }
/*  585:     */  
/*  586: 586 */  public boolean containsValue(boolean v) { boolean[] value = this.value;
/*  587: 587 */    boolean[] used = this.used;
/*  588: 588 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/*  589: 589 */    return false;
/*  590:     */  }
/*  591:     */  
/*  596:     */  public void clear()
/*  597:     */  {
/*  598: 598 */    if (this.size == 0) return;
/*  599: 599 */    this.size = 0;
/*  600: 600 */    BooleanArrays.fill(this.used, false);
/*  601:     */    
/*  602: 602 */    ObjectArrays.fill(this.key, null);
/*  603: 603 */    this.first = (this.last = -1);
/*  604:     */  }
/*  605:     */  
/*  606: 606 */  public int size() { return this.size; }
/*  607:     */  
/*  608:     */  public boolean isEmpty() {
/*  609: 609 */    return this.size == 0;
/*  610:     */  }
/*  611:     */  
/*  616:     */  @Deprecated
/*  617:     */  public void growthFactor(int growthFactor) {}
/*  618:     */  
/*  623:     */  @Deprecated
/*  624:     */  public int growthFactor()
/*  625:     */  {
/*  626: 626 */    return 16;
/*  627:     */  }
/*  628:     */  
/*  629:     */  private final class MapEntry
/*  630:     */    implements Object2BooleanMap.Entry<K>, Map.Entry<K, Boolean>
/*  631:     */  {
/*  632:     */    private int index;
/*  633:     */    
/*  634:     */    MapEntry(int index)
/*  635:     */    {
/*  636: 636 */      this.index = index;
/*  637:     */    }
/*  638:     */    
/*  639: 639 */    public K getKey() { return Object2BooleanLinkedOpenCustomHashMap.this.key[this.index]; }
/*  640:     */    
/*  641:     */    public Boolean getValue() {
/*  642: 642 */      return Boolean.valueOf(Object2BooleanLinkedOpenCustomHashMap.this.value[this.index]);
/*  643:     */    }
/*  644:     */    
/*  645: 645 */    public boolean getBooleanValue() { return Object2BooleanLinkedOpenCustomHashMap.this.value[this.index]; }
/*  646:     */    
/*  647:     */    public boolean setValue(boolean v) {
/*  648: 648 */      boolean oldValue = Object2BooleanLinkedOpenCustomHashMap.this.value[this.index];
/*  649: 649 */      Object2BooleanLinkedOpenCustomHashMap.this.value[this.index] = v;
/*  650: 650 */      return oldValue;
/*  651:     */    }
/*  652:     */    
/*  653: 653 */    public Boolean setValue(Boolean v) { return Boolean.valueOf(setValue(v.booleanValue())); }
/*  654:     */    
/*  655:     */    public boolean equals(Object o)
/*  656:     */    {
/*  657: 657 */      if (!(o instanceof Map.Entry)) return false;
/*  658: 658 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  659: 659 */      return (Object2BooleanLinkedOpenCustomHashMap.this.strategy.equals(Object2BooleanLinkedOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2BooleanLinkedOpenCustomHashMap.this.value[this.index] == ((Boolean)e.getValue()).booleanValue());
/*  660:     */    }
/*  661:     */    
/*  662: 662 */    public int hashCode() { return Object2BooleanLinkedOpenCustomHashMap.this.strategy.hashCode(Object2BooleanLinkedOpenCustomHashMap.this.key[this.index]) ^ (Object2BooleanLinkedOpenCustomHashMap.this.value[this.index] != 0 ? 1231 : 1237); }
/*  663:     */    
/*  664:     */    public String toString() {
/*  665: 665 */      return Object2BooleanLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2BooleanLinkedOpenCustomHashMap.this.value[this.index];
/*  666:     */    }
/*  667:     */  }
/*  668:     */  
/*  674:     */  protected void fixPointers(int i)
/*  675:     */  {
/*  676: 676 */    if (this.size == 0) {
/*  677: 677 */      this.first = (this.last = -1);
/*  678: 678 */      return;
/*  679:     */    }
/*  680: 680 */    if (this.first == i) {
/*  681: 681 */      this.first = ((int)this.link[i]);
/*  682: 682 */      if (0 <= this.first)
/*  683:     */      {
/*  684: 684 */        this.link[this.first] |= -4294967296L;
/*  685:     */      }
/*  686: 686 */      return;
/*  687:     */    }
/*  688: 688 */    if (this.last == i) {
/*  689: 689 */      this.last = ((int)(this.link[i] >>> 32));
/*  690: 690 */      if (0 <= this.last)
/*  691:     */      {
/*  692: 692 */        this.link[this.last] |= 4294967295L;
/*  693:     */      }
/*  694: 694 */      return;
/*  695:     */    }
/*  696: 696 */    long linki = this.link[i];
/*  697: 697 */    int prev = (int)(linki >>> 32);
/*  698: 698 */    int next = (int)linki;
/*  699: 699 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  700: 700 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  701:     */  }
/*  702:     */  
/*  709:     */  protected void fixPointers(int s, int d)
/*  710:     */  {
/*  711: 711 */    if (this.size == 1) {
/*  712: 712 */      this.first = (this.last = d);
/*  713:     */      
/*  714: 714 */      this.link[d] = -1L;
/*  715: 715 */      return;
/*  716:     */    }
/*  717: 717 */    if (this.first == s) {
/*  718: 718 */      this.first = d;
/*  719: 719 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  720: 720 */      this.link[d] = this.link[s];
/*  721: 721 */      return;
/*  722:     */    }
/*  723: 723 */    if (this.last == s) {
/*  724: 724 */      this.last = d;
/*  725: 725 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  726: 726 */      this.link[d] = this.link[s];
/*  727: 727 */      return;
/*  728:     */    }
/*  729: 729 */    long links = this.link[s];
/*  730: 730 */    int prev = (int)(links >>> 32);
/*  731: 731 */    int next = (int)links;
/*  732: 732 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  733: 733 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  734: 734 */    this.link[d] = links;
/*  735:     */  }
/*  736:     */  
/*  739:     */  public K firstKey()
/*  740:     */  {
/*  741: 741 */    if (this.size == 0) throw new NoSuchElementException();
/*  742: 742 */    return this.key[this.first];
/*  743:     */  }
/*  744:     */  
/*  747:     */  public K lastKey()
/*  748:     */  {
/*  749: 749 */    if (this.size == 0) throw new NoSuchElementException();
/*  750: 750 */    return this.key[this.last]; }
/*  751:     */  
/*  752: 752 */  public Comparator<? super K> comparator() { return null; }
/*  753: 753 */  public Object2BooleanSortedMap<K> tailMap(K from) { throw new UnsupportedOperationException(); }
/*  754: 754 */  public Object2BooleanSortedMap<K> headMap(K to) { throw new UnsupportedOperationException(); }
/*  755: 755 */  public Object2BooleanSortedMap<K> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*  756:     */  
/*  762:     */  private class MapIterator
/*  763:     */  {
/*  764: 764 */    int prev = -1;
/*  765:     */    
/*  766: 766 */    int next = -1;
/*  767:     */    
/*  768: 768 */    int curr = -1;
/*  769:     */    
/*  770: 770 */    int index = -1;
/*  771:     */    
/*  772: 772 */    private MapIterator() { this.next = Object2BooleanLinkedOpenCustomHashMap.this.first;
/*  773: 773 */      this.index = 0;
/*  774:     */    }
/*  775:     */    
/*  776: 776 */    private MapIterator() { if (Object2BooleanLinkedOpenCustomHashMap.this.strategy.equals(Object2BooleanLinkedOpenCustomHashMap.this.key[Object2BooleanLinkedOpenCustomHashMap.this.last], from)) {
/*  777: 777 */        this.prev = Object2BooleanLinkedOpenCustomHashMap.this.last;
/*  778: 778 */        this.index = Object2BooleanLinkedOpenCustomHashMap.this.size;
/*  779:     */      }
/*  780:     */      else
/*  781:     */      {
/*  782: 782 */        int pos = HashCommon.murmurHash3(Object2BooleanLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  783:     */        
/*  784: 784 */        while (Object2BooleanLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  785: 785 */          if (Object2BooleanLinkedOpenCustomHashMap.this.strategy.equals(Object2BooleanLinkedOpenCustomHashMap.this.key[pos], from))
/*  786:     */          {
/*  787: 787 */            this.next = ((int)Object2BooleanLinkedOpenCustomHashMap.this.link[pos]);
/*  788: 788 */            this.prev = pos;
/*  789: 789 */            return;
/*  790:     */          }
/*  791: 791 */          pos = pos + 1 & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  792:     */        }
/*  793: 793 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  794:     */      } }
/*  795:     */    
/*  796: 796 */    public boolean hasNext() { return this.next != -1; }
/*  797: 797 */    public boolean hasPrevious() { return this.prev != -1; }
/*  798:     */    
/*  799: 799 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  800: 800 */      if (this.prev == -1) {
/*  801: 801 */        this.index = 0;
/*  802: 802 */        return;
/*  803:     */      }
/*  804: 804 */      if (this.next == -1) {
/*  805: 805 */        this.index = Object2BooleanLinkedOpenCustomHashMap.this.size;
/*  806: 806 */        return;
/*  807:     */      }
/*  808: 808 */      int pos = Object2BooleanLinkedOpenCustomHashMap.this.first;
/*  809: 809 */      this.index = 1;
/*  810: 810 */      while (pos != this.prev) {
/*  811: 811 */        pos = (int)Object2BooleanLinkedOpenCustomHashMap.this.link[pos];
/*  812: 812 */        this.index += 1;
/*  813:     */      }
/*  814:     */    }
/*  815:     */    
/*  816: 816 */    public int nextIndex() { ensureIndexKnown();
/*  817: 817 */      return this.index;
/*  818:     */    }
/*  819:     */    
/*  820: 820 */    public int previousIndex() { ensureIndexKnown();
/*  821: 821 */      return this.index - 1;
/*  822:     */    }
/*  823:     */    
/*  824: 824 */    public int nextEntry() { if (!hasNext()) return Object2BooleanLinkedOpenCustomHashMap.this.size();
/*  825: 825 */      this.curr = this.next;
/*  826: 826 */      this.next = ((int)Object2BooleanLinkedOpenCustomHashMap.this.link[this.curr]);
/*  827: 827 */      this.prev = this.curr;
/*  828: 828 */      if (this.index >= 0) this.index += 1;
/*  829: 829 */      return this.curr;
/*  830:     */    }
/*  831:     */    
/*  832: 832 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  833: 833 */      this.curr = this.prev;
/*  834: 834 */      this.prev = ((int)(Object2BooleanLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  835: 835 */      this.next = this.curr;
/*  836: 836 */      if (this.index >= 0) this.index -= 1;
/*  837: 837 */      return this.curr;
/*  838:     */    }
/*  839:     */    
/*  840:     */    public void remove() {
/*  841: 841 */      ensureIndexKnown();
/*  842: 842 */      if (this.curr == -1) throw new IllegalStateException();
/*  843: 843 */      if (this.curr == this.prev)
/*  844:     */      {
/*  846: 846 */        this.index -= 1;
/*  847: 847 */        this.prev = ((int)(Object2BooleanLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  848:     */      }
/*  849:     */      else {
/*  850: 850 */        this.next = ((int)Object2BooleanLinkedOpenCustomHashMap.this.link[this.curr]); }
/*  851: 851 */      Object2BooleanLinkedOpenCustomHashMap.this.size -= 1;
/*  852:     */      
/*  854: 854 */      if (this.prev == -1) { Object2BooleanLinkedOpenCustomHashMap.this.first = this.next;
/*  855:     */      } else
/*  856: 856 */        Object2BooleanLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2BooleanLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  857: 857 */      if (this.next == -1) { Object2BooleanLinkedOpenCustomHashMap.this.last = this.prev;
/*  858:     */      } else
/*  859: 859 */        Object2BooleanLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2BooleanLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  860: 860 */      int pos = this.curr;
/*  861:     */      int last;
/*  862:     */      for (;;) {
/*  863: 863 */        pos = (last = pos) + 1 & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  864: 864 */        while (Object2BooleanLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  865: 865 */          int slot = HashCommon.murmurHash3(Object2BooleanLinkedOpenCustomHashMap.this.strategy.hashCode(Object2BooleanLinkedOpenCustomHashMap.this.key[pos])) & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  866: 866 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  867: 867 */          pos = pos + 1 & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  868:     */        }
/*  869: 869 */        if (Object2BooleanLinkedOpenCustomHashMap.this.used[pos] == 0) break;
/*  870: 870 */        Object2BooleanLinkedOpenCustomHashMap.this.key[last] = Object2BooleanLinkedOpenCustomHashMap.this.key[pos];
/*  871: 871 */        Object2BooleanLinkedOpenCustomHashMap.this.value[last] = Object2BooleanLinkedOpenCustomHashMap.this.value[pos];
/*  872: 872 */        if (this.next == pos) this.next = last;
/*  873: 873 */        if (this.prev == pos) this.prev = last;
/*  874: 874 */        Object2BooleanLinkedOpenCustomHashMap.this.fixPointers(pos, last);
/*  875:     */      }
/*  876: 876 */      Object2BooleanLinkedOpenCustomHashMap.this.used[last] = false;
/*  877: 877 */      Object2BooleanLinkedOpenCustomHashMap.this.key[last] = null;
/*  878: 878 */      this.curr = -1;
/*  879:     */    }
/*  880:     */    
/*  881: 881 */    public int skip(int n) { int i = n;
/*  882: 882 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  883: 883 */      return n - i - 1;
/*  884:     */    }
/*  885:     */    
/*  886: 886 */    public int back(int n) { int i = n;
/*  887: 887 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  888: 888 */      return n - i - 1;
/*  889:     */    } }
/*  890:     */  
/*  891:     */  private class EntryIterator extends Object2BooleanLinkedOpenCustomHashMap<K>.MapIterator implements ObjectListIterator<Object2BooleanMap.Entry<K>> { private Object2BooleanLinkedOpenCustomHashMap<K>.MapEntry entry;
/*  892:     */    
/*  893: 893 */    public EntryIterator() { super(null); }
/*  894:     */    
/*  895: 895 */    public EntryIterator() { super(from, null); }
/*  896:     */    
/*  897:     */    public Object2BooleanLinkedOpenCustomHashMap<K>.MapEntry next() {
/*  898: 898 */      return this.entry = new Object2BooleanLinkedOpenCustomHashMap.MapEntry(Object2BooleanLinkedOpenCustomHashMap.this, nextEntry());
/*  899:     */    }
/*  900:     */    
/*  901: 901 */    public Object2BooleanLinkedOpenCustomHashMap<K>.MapEntry previous() { return this.entry = new Object2BooleanLinkedOpenCustomHashMap.MapEntry(Object2BooleanLinkedOpenCustomHashMap.this, previousEntry()); }
/*  902:     */    
/*  903:     */    public void remove()
/*  904:     */    {
/*  905: 905 */      super.remove();
/*  906: 906 */      Object2BooleanLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1); }
/*  907:     */    
/*  908: 908 */    public void set(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  909: 909 */    public void add(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  910:     */  }
/*  911:     */  
/*  912: 912 */  private class FastEntryIterator extends Object2BooleanLinkedOpenCustomHashMap<K>.MapIterator implements ObjectListIterator<Object2BooleanMap.Entry<K>> { final AbstractObject2BooleanMap.BasicEntry<K> entry = new AbstractObject2BooleanMap.BasicEntry(null, false);
/*  913: 913 */    public FastEntryIterator() { super(null); }
/*  914:     */    
/*  915: 915 */    public FastEntryIterator() { super(from, null); }
/*  916:     */    
/*  917:     */    public AbstractObject2BooleanMap.BasicEntry<K> next() {
/*  918: 918 */      int e = nextEntry();
/*  919: 919 */      this.entry.key = Object2BooleanLinkedOpenCustomHashMap.this.key[e];
/*  920: 920 */      this.entry.value = Object2BooleanLinkedOpenCustomHashMap.this.value[e];
/*  921: 921 */      return this.entry;
/*  922:     */    }
/*  923:     */    
/*  924: 924 */    public AbstractObject2BooleanMap.BasicEntry<K> previous() { int e = previousEntry();
/*  925: 925 */      this.entry.key = Object2BooleanLinkedOpenCustomHashMap.this.key[e];
/*  926: 926 */      this.entry.value = Object2BooleanLinkedOpenCustomHashMap.this.value[e];
/*  927: 927 */      return this.entry; }
/*  928:     */    
/*  929: 929 */    public void set(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  930: 930 */    public void add(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); } }
/*  931:     */  
/*  932:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Object2BooleanMap.Entry<K>> implements Object2BooleanSortedMap.FastSortedEntrySet<K> { private MapEntrySet() {}
/*  933:     */    
/*  934: 934 */    public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> iterator() { return new Object2BooleanLinkedOpenCustomHashMap.EntryIterator(Object2BooleanLinkedOpenCustomHashMap.this); }
/*  935:     */    
/*  936: 936 */    public Comparator<? super Object2BooleanMap.Entry<K>> comparator() { return null; }
/*  937: 937 */    public ObjectSortedSet<Object2BooleanMap.Entry<K>> subSet(Object2BooleanMap.Entry<K> fromElement, Object2BooleanMap.Entry<K> toElement) { throw new UnsupportedOperationException(); }
/*  938: 938 */    public ObjectSortedSet<Object2BooleanMap.Entry<K>> headSet(Object2BooleanMap.Entry<K> toElement) { throw new UnsupportedOperationException(); }
/*  939: 939 */    public ObjectSortedSet<Object2BooleanMap.Entry<K>> tailSet(Object2BooleanMap.Entry<K> fromElement) { throw new UnsupportedOperationException(); }
/*  940:     */    
/*  941: 941 */    public Object2BooleanMap.Entry<K> first() { if (Object2BooleanLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  942: 942 */      return new Object2BooleanLinkedOpenCustomHashMap.MapEntry(Object2BooleanLinkedOpenCustomHashMap.this, Object2BooleanLinkedOpenCustomHashMap.this.first);
/*  943:     */    }
/*  944:     */    
/*  945: 945 */    public Object2BooleanMap.Entry<K> last() { if (Object2BooleanLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  946: 946 */      return new Object2BooleanLinkedOpenCustomHashMap.MapEntry(Object2BooleanLinkedOpenCustomHashMap.this, Object2BooleanLinkedOpenCustomHashMap.this.last);
/*  947:     */    }
/*  948:     */    
/*  949:     */    public boolean contains(Object o) {
/*  950: 950 */      if (!(o instanceof Map.Entry)) return false;
/*  951: 951 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  952: 952 */      K k = e.getKey();
/*  953:     */      
/*  954: 954 */      int pos = HashCommon.murmurHash3(Object2BooleanLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  955:     */      
/*  956: 956 */      while (Object2BooleanLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  957: 957 */        if (Object2BooleanLinkedOpenCustomHashMap.this.strategy.equals(Object2BooleanLinkedOpenCustomHashMap.this.key[pos], k)) return Object2BooleanLinkedOpenCustomHashMap.this.value[pos] == ((Boolean)e.getValue()).booleanValue();
/*  958: 958 */        pos = pos + 1 & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  959:     */      }
/*  960: 960 */      return false;
/*  961:     */    }
/*  962:     */    
/*  963:     */    public boolean remove(Object o) {
/*  964: 964 */      if (!(o instanceof Map.Entry)) return false;
/*  965: 965 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  966: 966 */      K k = e.getKey();
/*  967:     */      
/*  968: 968 */      int pos = HashCommon.murmurHash3(Object2BooleanLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  969:     */      
/*  970: 970 */      while (Object2BooleanLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  971: 971 */        if (Object2BooleanLinkedOpenCustomHashMap.this.strategy.equals(Object2BooleanLinkedOpenCustomHashMap.this.key[pos], k)) {
/*  972: 972 */          Object2BooleanLinkedOpenCustomHashMap.this.remove(e.getKey());
/*  973: 973 */          return true;
/*  974:     */        }
/*  975: 975 */        pos = pos + 1 & Object2BooleanLinkedOpenCustomHashMap.this.mask;
/*  976:     */      }
/*  977: 977 */      return false;
/*  978:     */    }
/*  979:     */    
/*  980: 980 */    public int size() { return Object2BooleanLinkedOpenCustomHashMap.this.size; }
/*  981:     */    
/*  982:     */    public void clear() {
/*  983: 983 */      Object2BooleanLinkedOpenCustomHashMap.this.clear();
/*  984:     */    }
/*  985:     */    
/*  986: 986 */    public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> iterator(Object2BooleanMap.Entry<K> from) { return new Object2BooleanLinkedOpenCustomHashMap.EntryIterator(Object2BooleanLinkedOpenCustomHashMap.this, from.getKey()); }
/*  987:     */    
/*  988:     */    public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> fastIterator() {
/*  989: 989 */      return new Object2BooleanLinkedOpenCustomHashMap.FastEntryIterator(Object2BooleanLinkedOpenCustomHashMap.this);
/*  990:     */    }
/*  991:     */    
/*  992: 992 */    public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> fastIterator(Object2BooleanMap.Entry<K> from) { return new Object2BooleanLinkedOpenCustomHashMap.FastEntryIterator(Object2BooleanLinkedOpenCustomHashMap.this, from.getKey()); }
/*  993:     */  }
/*  994:     */  
/*  995:     */  public Object2BooleanSortedMap.FastSortedEntrySet<K> object2BooleanEntrySet() {
/*  996: 996 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/*  997: 997 */    return this.entries;
/*  998:     */  }
/*  999:     */  
/* 1002:     */  private final class KeyIterator
/* 1003:     */    extends Object2BooleanLinkedOpenCustomHashMap<K>.MapIterator
/* 1004:     */    implements ObjectListIterator<K>
/* 1005:     */  {
/* 1006:1006 */    public KeyIterator() { super(k, null); }
/* 1007:1007 */    public K previous() { return Object2BooleanLinkedOpenCustomHashMap.this.key[previousEntry()]; }
/* 1008:1008 */    public void set(K k) { throw new UnsupportedOperationException(); }
/* 1009:1009 */    public void add(K k) { throw new UnsupportedOperationException(); }
/* 1010:1010 */    public KeyIterator() { super(null); }
/* 1011:1011 */    public K next() { return Object2BooleanLinkedOpenCustomHashMap.this.key[nextEntry()]; } }
/* 1012:     */  
/* 1013:     */  private final class KeySet extends AbstractObjectSortedSet<K> { private KeySet() {}
/* 1014:     */    
/* 1015:1015 */    public ObjectListIterator<K> iterator(K from) { return new Object2BooleanLinkedOpenCustomHashMap.KeyIterator(Object2BooleanLinkedOpenCustomHashMap.this, from); }
/* 1016:     */    
/* 1017:     */    public ObjectListIterator<K> iterator() {
/* 1018:1018 */      return new Object2BooleanLinkedOpenCustomHashMap.KeyIterator(Object2BooleanLinkedOpenCustomHashMap.this);
/* 1019:     */    }
/* 1020:     */    
/* 1021:1021 */    public int size() { return Object2BooleanLinkedOpenCustomHashMap.this.size; }
/* 1022:     */    
/* 1024:1024 */    public boolean contains(Object k) { return Object2BooleanLinkedOpenCustomHashMap.this.containsKey(k); }
/* 1025:     */    
/* 1026:     */    public boolean remove(Object k) {
/* 1027:1027 */      int oldSize = Object2BooleanLinkedOpenCustomHashMap.this.size;
/* 1028:1028 */      Object2BooleanLinkedOpenCustomHashMap.this.remove(k);
/* 1029:1029 */      return Object2BooleanLinkedOpenCustomHashMap.this.size != oldSize;
/* 1030:     */    }
/* 1031:     */    
/* 1032:1032 */    public void clear() { Object2BooleanLinkedOpenCustomHashMap.this.clear(); }
/* 1033:     */    
/* 1034:     */    public K first() {
/* 1035:1035 */      if (Object2BooleanLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/* 1036:1036 */      return Object2BooleanLinkedOpenCustomHashMap.this.key[Object2BooleanLinkedOpenCustomHashMap.this.first];
/* 1037:     */    }
/* 1038:     */    
/* 1039:1039 */    public K last() { if (Object2BooleanLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/* 1040:1040 */      return Object2BooleanLinkedOpenCustomHashMap.this.key[Object2BooleanLinkedOpenCustomHashMap.this.last]; }
/* 1041:     */    
/* 1042:1042 */    public Comparator<? super K> comparator() { return null; }
/* 1043:1043 */    public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/* 1044:1044 */    public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/* 1045:1045 */    public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/* 1046:     */  }
/* 1047:     */  
/* 1048:1048 */  public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1049:1049 */    return this.keys;
/* 1050:     */  }
/* 1051:     */  
/* 1054:     */  private final class ValueIterator
/* 1055:     */    extends Object2BooleanLinkedOpenCustomHashMap.MapIterator
/* 1056:     */    implements BooleanListIterator
/* 1057:     */  {
/* 1058:1058 */    public boolean previousBoolean() { return Object2BooleanLinkedOpenCustomHashMap.this.value[previousEntry()]; }
/* 1059:1059 */    public Boolean previous() { return Boolean.valueOf(Object2BooleanLinkedOpenCustomHashMap.this.value[previousEntry()]); }
/* 1060:1060 */    public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1061:1061 */    public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1062:1062 */    public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1063:1063 */    public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1064:1064 */    public ValueIterator() { super(null); }
/* 1065:1065 */    public boolean nextBoolean() { return Object2BooleanLinkedOpenCustomHashMap.this.value[nextEntry()]; }
/* 1066:1066 */    public Boolean next() { return Boolean.valueOf(Object2BooleanLinkedOpenCustomHashMap.this.value[nextEntry()]); }
/* 1067:     */  }
/* 1068:     */  
/* 1069:1069 */  public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1070:     */        public BooleanIterator iterator() {
/* 1071:1071 */          return new Object2BooleanLinkedOpenCustomHashMap.ValueIterator(Object2BooleanLinkedOpenCustomHashMap.this);
/* 1072:     */        }
/* 1073:     */        
/* 1074:1074 */        public int size() { return Object2BooleanLinkedOpenCustomHashMap.this.size; }
/* 1075:     */        
/* 1076:     */        public boolean contains(boolean v) {
/* 1077:1077 */          return Object2BooleanLinkedOpenCustomHashMap.this.containsValue(v);
/* 1078:     */        }
/* 1079:     */        
/* 1080:1080 */        public void clear() { Object2BooleanLinkedOpenCustomHashMap.this.clear(); }
/* 1081:     */      };
/* 1082:     */    }
/* 1083:1083 */    return this.values;
/* 1084:     */  }
/* 1085:     */  
/* 1094:     */  @Deprecated
/* 1095:     */  public boolean rehash()
/* 1096:     */  {
/* 1097:1097 */    return true;
/* 1098:     */  }
/* 1099:     */  
/* 1110:     */  public boolean trim()
/* 1111:     */  {
/* 1112:1112 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1113:1113 */    if (l >= this.n) return true;
/* 1114:     */    try {
/* 1115:1115 */      rehash(l);
/* 1116:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1117:1117 */      return false; }
/* 1118:1118 */    return true;
/* 1119:     */  }
/* 1120:     */  
/* 1137:     */  public boolean trim(int n)
/* 1138:     */  {
/* 1139:1139 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1140:1140 */    if (this.n <= l) return true;
/* 1141:     */    try {
/* 1142:1142 */      rehash(l);
/* 1143:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1144:1144 */      return false; }
/* 1145:1145 */    return true;
/* 1146:     */  }
/* 1147:     */  
/* 1156:     */  protected void rehash(int newN)
/* 1157:     */  {
/* 1158:1158 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1159:     */    
/* 1160:1160 */    K[] key = this.key;
/* 1161:1161 */    boolean[] value = this.value;
/* 1162:1162 */    int newMask = newN - 1;
/* 1163:1163 */    K[] newKey = (Object[])new Object[newN];
/* 1164:1164 */    boolean[] newValue = new boolean[newN];
/* 1165:1165 */    boolean[] newUsed = new boolean[newN];
/* 1166:1166 */    long[] link = this.link;
/* 1167:1167 */    long[] newLink = new long[newN];
/* 1168:1168 */    this.first = -1;
/* 1169:1169 */    for (int j = this.size; j-- != 0;) {
/* 1170:1170 */      K k = key[i];
/* 1171:1171 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 1172:1172 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1173:1173 */      newUsed[pos] = true;
/* 1174:1174 */      newKey[pos] = k;
/* 1175:1175 */      newValue[pos] = value[i];
/* 1176:1176 */      if (prev != -1) {
/* 1177:1177 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1178:1178 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1179:1179 */        newPrev = pos;
/* 1180:     */      }
/* 1181:     */      else {
/* 1182:1182 */        newPrev = this.first = pos;
/* 1183:     */        
/* 1184:1184 */        newLink[pos] = -1L;
/* 1185:     */      }
/* 1186:1186 */      int t = i;
/* 1187:1187 */      i = (int)link[i];
/* 1188:1188 */      prev = t;
/* 1189:     */    }
/* 1190:1190 */    this.n = newN;
/* 1191:1191 */    this.mask = newMask;
/* 1192:1192 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1193:1193 */    this.key = newKey;
/* 1194:1194 */    this.value = newValue;
/* 1195:1195 */    this.used = newUsed;
/* 1196:1196 */    this.link = newLink;
/* 1197:1197 */    this.last = newPrev;
/* 1198:1198 */    if (newPrev != -1)
/* 1199:     */    {
/* 1200:1200 */      newLink[newPrev] |= 4294967295L;
/* 1201:     */    }
/* 1202:     */  }
/* 1203:     */  
/* 1206:     */  public Object2BooleanLinkedOpenCustomHashMap<K> clone()
/* 1207:     */  {
/* 1208:     */    Object2BooleanLinkedOpenCustomHashMap<K> c;
/* 1209:     */    
/* 1211:     */    try
/* 1212:     */    {
/* 1213:1213 */      c = (Object2BooleanLinkedOpenCustomHashMap)super.clone();
/* 1214:     */    }
/* 1215:     */    catch (CloneNotSupportedException cantHappen) {
/* 1216:1216 */      throw new InternalError();
/* 1217:     */    }
/* 1218:1218 */    c.keys = null;
/* 1219:1219 */    c.values = null;
/* 1220:1220 */    c.entries = null;
/* 1221:1221 */    c.key = ((Object[])this.key.clone());
/* 1222:1222 */    c.value = ((boolean[])this.value.clone());
/* 1223:1223 */    c.used = ((boolean[])this.used.clone());
/* 1224:1224 */    c.link = ((long[])this.link.clone());
/* 1225:1225 */    c.strategy = this.strategy;
/* 1226:1226 */    return c;
/* 1227:     */  }
/* 1228:     */  
/* 1236:     */  public int hashCode()
/* 1237:     */  {
/* 1238:1238 */    int h = 0;
/* 1239:1239 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1240:1240 */      while (this.used[i] == 0) i++;
/* 1241:1241 */      if (this != this.key[i])
/* 1242:1242 */        t = this.strategy.hashCode(this.key[i]);
/* 1243:1243 */      t ^= (this.value[i] != 0 ? 1231 : 1237);
/* 1244:1244 */      h += t;
/* 1245:1245 */      i++;
/* 1246:     */    }
/* 1247:1247 */    return h;
/* 1248:     */  }
/* 1249:     */  
/* 1250:1250 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 1251:1251 */    boolean[] value = this.value;
/* 1252:1252 */    Object2BooleanLinkedOpenCustomHashMap<K>.MapIterator i = new MapIterator(null);
/* 1253:1253 */    s.defaultWriteObject();
/* 1254:1254 */    for (int j = this.size; j-- != 0;) {
/* 1255:1255 */      int e = i.nextEntry();
/* 1256:1256 */      s.writeObject(key[e]);
/* 1257:1257 */      s.writeBoolean(value[e]);
/* 1258:     */    }
/* 1259:     */  }
/* 1260:     */  
/* 1261:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1262:1262 */    s.defaultReadObject();
/* 1263:1263 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1264:1264 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1265:1265 */    this.mask = (this.n - 1);
/* 1266:1266 */    K[] key = this.key = (Object[])new Object[this.n];
/* 1267:1267 */    boolean[] value = this.value = new boolean[this.n];
/* 1268:1268 */    boolean[] used = this.used = new boolean[this.n];
/* 1269:1269 */    long[] link = this.link = new long[this.n];
/* 1270:1270 */    int prev = -1;
/* 1271:1271 */    this.first = (this.last = -1);
/* 1272:     */    
/* 1274:1274 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1275:1275 */      K k = s.readObject();
/* 1276:1276 */      boolean v = s.readBoolean();
/* 1277:1277 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 1278:1278 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1279:1279 */      used[pos] = true;
/* 1280:1280 */      key[pos] = k;
/* 1281:1281 */      value[pos] = v;
/* 1282:1282 */      if (this.first != -1) {
/* 1283:1283 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1284:1284 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1285:1285 */        prev = pos;
/* 1286:     */      }
/* 1287:     */      else {
/* 1288:1288 */        prev = this.first = pos;
/* 1289:     */        
/* 1290:1290 */        link[pos] |= -4294967296L;
/* 1291:     */      }
/* 1292:     */    }
/* 1293:1293 */    this.last = prev;
/* 1294:1294 */    if (prev != -1)
/* 1295:     */    {
/* 1296:1296 */      link[prev] |= 4294967295L;
/* 1297:     */    }
/* 1298:     */  }
/* 1299:     */  
/* 1300:     */  private void checkTable() {}
/* 1301:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanLinkedOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */