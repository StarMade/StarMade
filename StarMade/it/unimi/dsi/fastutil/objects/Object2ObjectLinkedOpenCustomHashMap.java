/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    5:     */import it.unimi.dsi.fastutil.HashCommon;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.ObjectInputStream;
/*    9:     */import java.io.ObjectOutputStream;
/*   10:     */import java.io.Serializable;
/*   11:     */import java.util.Comparator;
/*   12:     */import java.util.Map;
/*   13:     */import java.util.Map.Entry;
/*   14:     */import java.util.NoSuchElementException;
/*   15:     */
/*   99:     */public class Object2ObjectLinkedOpenCustomHashMap<K, V>
/*  100:     */  extends AbstractObject2ObjectSortedMap<K, V>
/*  101:     */  implements Serializable, Cloneable, Hash
/*  102:     */{
/*  103:     */  public static final long serialVersionUID = 0L;
/*  104:     */  private static final boolean ASSERTS = false;
/*  105:     */  protected transient K[] key;
/*  106:     */  protected transient V[] value;
/*  107:     */  protected transient boolean[] used;
/*  108:     */  protected final float f;
/*  109:     */  protected transient int n;
/*  110:     */  protected transient int maxFill;
/*  111:     */  protected transient int mask;
/*  112:     */  protected int size;
/*  113:     */  protected volatile transient Object2ObjectSortedMap.FastSortedEntrySet<K, V> entries;
/*  114:     */  protected volatile transient ObjectSortedSet<K> keys;
/*  115:     */  protected volatile transient ObjectCollection<V> values;
/*  116: 116 */  protected transient int first = -1;
/*  117:     */  
/*  118: 118 */  protected transient int last = -1;
/*  119:     */  
/*  125:     */  protected transient long[] link;
/*  126:     */  
/*  131:     */  protected Hash.Strategy<K> strategy;
/*  132:     */  
/*  138:     */  public Object2ObjectLinkedOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*  139:     */  {
/*  140: 140 */    this.strategy = strategy;
/*  141: 141 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  142: 142 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  143: 143 */    this.f = f;
/*  144: 144 */    this.n = HashCommon.arraySize(expected, f);
/*  145: 145 */    this.mask = (this.n - 1);
/*  146: 146 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  147: 147 */    this.key = ((Object[])new Object[this.n]);
/*  148: 148 */    this.value = ((Object[])new Object[this.n]);
/*  149: 149 */    this.used = new boolean[this.n];
/*  150: 150 */    this.link = new long[this.n];
/*  151:     */  }
/*  152:     */  
/*  156:     */  public Object2ObjectLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*  157:     */  {
/*  158: 158 */    this(expected, 0.75F, strategy);
/*  159:     */  }
/*  160:     */  
/*  163:     */  public Object2ObjectLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
/*  164:     */  {
/*  165: 165 */    this(16, 0.75F, strategy);
/*  166:     */  }
/*  167:     */  
/*  172:     */  public Object2ObjectLinkedOpenCustomHashMap(Map<? extends K, ? extends V> m, float f, Hash.Strategy<K> strategy)
/*  173:     */  {
/*  174: 174 */    this(m.size(), f, strategy);
/*  175: 175 */    putAll(m);
/*  176:     */  }
/*  177:     */  
/*  181:     */  public Object2ObjectLinkedOpenCustomHashMap(Map<? extends K, ? extends V> m, Hash.Strategy<K> strategy)
/*  182:     */  {
/*  183: 183 */    this(m, 0.75F, strategy);
/*  184:     */  }
/*  185:     */  
/*  190:     */  public Object2ObjectLinkedOpenCustomHashMap(Object2ObjectMap<K, V> m, float f, Hash.Strategy<K> strategy)
/*  191:     */  {
/*  192: 192 */    this(m.size(), f, strategy);
/*  193: 193 */    putAll(m);
/*  194:     */  }
/*  195:     */  
/*  199:     */  public Object2ObjectLinkedOpenCustomHashMap(Object2ObjectMap<K, V> m, Hash.Strategy<K> strategy)
/*  200:     */  {
/*  201: 201 */    this(m, 0.75F, strategy);
/*  202:     */  }
/*  203:     */  
/*  210:     */  public Object2ObjectLinkedOpenCustomHashMap(K[] k, V[] v, float f, Hash.Strategy<K> strategy)
/*  211:     */  {
/*  212: 212 */    this(k.length, f, strategy);
/*  213: 213 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  214: 214 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  215:     */    }
/*  216:     */  }
/*  217:     */  
/*  222:     */  public Object2ObjectLinkedOpenCustomHashMap(K[] k, V[] v, Hash.Strategy<K> strategy)
/*  223:     */  {
/*  224: 224 */    this(k, v, 0.75F, strategy);
/*  225:     */  }
/*  226:     */  
/*  229:     */  public Hash.Strategy<K> strategy()
/*  230:     */  {
/*  231: 231 */    return this.strategy;
/*  232:     */  }
/*  233:     */  
/*  237:     */  public V put(K k, V v)
/*  238:     */  {
/*  239: 239 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  240:     */    
/*  241: 241 */    while (this.used[pos] != 0) {
/*  242: 242 */      if (this.strategy.equals(this.key[pos], k)) {
/*  243: 243 */        V oldValue = this.value[pos];
/*  244: 244 */        this.value[pos] = v;
/*  245: 245 */        return oldValue;
/*  246:     */      }
/*  247: 247 */      pos = pos + 1 & this.mask;
/*  248:     */    }
/*  249: 249 */    this.used[pos] = true;
/*  250: 250 */    this.key[pos] = k;
/*  251: 251 */    this.value[pos] = v;
/*  252: 252 */    if (this.size == 0) {
/*  253: 253 */      this.first = (this.last = pos);
/*  254:     */      
/*  255: 255 */      this.link[pos] = -1L;
/*  256:     */    }
/*  257:     */    else {
/*  258: 258 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  259: 259 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  260: 260 */      this.last = pos;
/*  261:     */    }
/*  262: 262 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  263:     */    }
/*  264: 264 */    return this.defRetValue;
/*  265:     */  }
/*  266:     */  
/*  269:     */  protected final int shiftKeys(int pos)
/*  270:     */  {
/*  271:     */    int last;
/*  272:     */    
/*  274:     */    for (;;)
/*  275:     */    {
/*  276: 276 */      pos = (last = pos) + 1 & this.mask;
/*  277: 277 */      while (this.used[pos] != 0) {
/*  278: 278 */        int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/*  279: 279 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  280: 280 */        pos = pos + 1 & this.mask;
/*  281:     */      }
/*  282: 282 */      if (this.used[pos] == 0) break;
/*  283: 283 */      this.key[last] = this.key[pos];
/*  284: 284 */      this.value[last] = this.value[pos];
/*  285: 285 */      fixPointers(pos, last);
/*  286:     */    }
/*  287: 287 */    this.used[last] = false;
/*  288: 288 */    this.key[last] = null;
/*  289: 289 */    this.value[last] = null;
/*  290: 290 */    return last;
/*  291:     */  }
/*  292:     */  
/*  293:     */  public V remove(Object k)
/*  294:     */  {
/*  295: 295 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  296:     */    
/*  297: 297 */    while (this.used[pos] != 0) {
/*  298: 298 */      if (this.strategy.equals(this.key[pos], k)) {
/*  299: 299 */        this.size -= 1;
/*  300: 300 */        fixPointers(pos);
/*  301: 301 */        V v = this.value[pos];
/*  302: 302 */        shiftKeys(pos);
/*  303: 303 */        return v;
/*  304:     */      }
/*  305: 305 */      pos = pos + 1 & this.mask;
/*  306:     */    }
/*  307: 307 */    return this.defRetValue;
/*  308:     */  }
/*  309:     */  
/*  312:     */  public V removeFirst()
/*  313:     */  {
/*  314: 314 */    if (this.size == 0) throw new NoSuchElementException();
/*  315: 315 */    this.size -= 1;
/*  316: 316 */    int pos = this.first;
/*  317:     */    
/*  318: 318 */    this.first = ((int)this.link[pos]);
/*  319: 319 */    if (0 <= this.first)
/*  320:     */    {
/*  321: 321 */      this.link[this.first] |= -4294967296L;
/*  322:     */    }
/*  323: 323 */    V v = this.value[pos];
/*  324: 324 */    shiftKeys(pos);
/*  325: 325 */    return v;
/*  326:     */  }
/*  327:     */  
/*  330:     */  public V removeLast()
/*  331:     */  {
/*  332: 332 */    if (this.size == 0) throw new NoSuchElementException();
/*  333: 333 */    this.size -= 1;
/*  334: 334 */    int pos = this.last;
/*  335:     */    
/*  336: 336 */    this.last = ((int)(this.link[pos] >>> 32));
/*  337: 337 */    if (0 <= this.last)
/*  338:     */    {
/*  339: 339 */      this.link[this.last] |= 4294967295L;
/*  340:     */    }
/*  341: 341 */    V v = this.value[pos];
/*  342: 342 */    shiftKeys(pos);
/*  343: 343 */    return v;
/*  344:     */  }
/*  345:     */  
/*  346: 346 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  347: 347 */    if (this.last == i) {
/*  348: 348 */      this.last = ((int)(this.link[i] >>> 32));
/*  349:     */      
/*  350: 350 */      this.link[this.last] |= 4294967295L;
/*  351:     */    }
/*  352:     */    else {
/*  353: 353 */      long linki = this.link[i];
/*  354: 354 */      int prev = (int)(linki >>> 32);
/*  355: 355 */      int next = (int)linki;
/*  356: 356 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  357: 357 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  358:     */    }
/*  359: 359 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  360: 360 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  361: 361 */    this.first = i;
/*  362:     */  }
/*  363:     */  
/*  364: 364 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  365: 365 */    if (this.first == i) {
/*  366: 366 */      this.first = ((int)this.link[i]);
/*  367:     */      
/*  368: 368 */      this.link[this.first] |= -4294967296L;
/*  369:     */    }
/*  370:     */    else {
/*  371: 371 */      long linki = this.link[i];
/*  372: 372 */      int prev = (int)(linki >>> 32);
/*  373: 373 */      int next = (int)linki;
/*  374: 374 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  375: 375 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  376:     */    }
/*  377: 377 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  378: 378 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  379: 379 */    this.last = i;
/*  380:     */  }
/*  381:     */  
/*  385:     */  public V getAndMoveToFirst(K k)
/*  386:     */  {
/*  387: 387 */    K[] key = this.key;
/*  388: 388 */    boolean[] used = this.used;
/*  389: 389 */    int mask = this.mask;
/*  390:     */    
/*  391: 391 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  392:     */    
/*  393: 393 */    while (used[pos] != 0) {
/*  394: 394 */      if (this.strategy.equals(k, key[pos])) {
/*  395: 395 */        moveIndexToFirst(pos);
/*  396: 396 */        return this.value[pos];
/*  397:     */      }
/*  398: 398 */      pos = pos + 1 & mask;
/*  399:     */    }
/*  400: 400 */    return this.defRetValue;
/*  401:     */  }
/*  402:     */  
/*  406:     */  public V getAndMoveToLast(K k)
/*  407:     */  {
/*  408: 408 */    K[] key = this.key;
/*  409: 409 */    boolean[] used = this.used;
/*  410: 410 */    int mask = this.mask;
/*  411:     */    
/*  412: 412 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  413:     */    
/*  414: 414 */    while (used[pos] != 0) {
/*  415: 415 */      if (this.strategy.equals(k, key[pos])) {
/*  416: 416 */        moveIndexToLast(pos);
/*  417: 417 */        return this.value[pos];
/*  418:     */      }
/*  419: 419 */      pos = pos + 1 & mask;
/*  420:     */    }
/*  421: 421 */    return this.defRetValue;
/*  422:     */  }
/*  423:     */  
/*  428:     */  public V putAndMoveToFirst(K k, V v)
/*  429:     */  {
/*  430: 430 */    K[] key = this.key;
/*  431: 431 */    boolean[] used = this.used;
/*  432: 432 */    int mask = this.mask;
/*  433:     */    
/*  434: 434 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  435:     */    
/*  436: 436 */    while (used[pos] != 0) {
/*  437: 437 */      if (this.strategy.equals(k, key[pos])) {
/*  438: 438 */        V oldValue = this.value[pos];
/*  439: 439 */        this.value[pos] = v;
/*  440: 440 */        moveIndexToFirst(pos);
/*  441: 441 */        return oldValue;
/*  442:     */      }
/*  443: 443 */      pos = pos + 1 & mask;
/*  444:     */    }
/*  445: 445 */    used[pos] = true;
/*  446: 446 */    key[pos] = k;
/*  447: 447 */    this.value[pos] = v;
/*  448: 448 */    if (this.size == 0) {
/*  449: 449 */      this.first = (this.last = pos);
/*  450:     */      
/*  451: 451 */      this.link[pos] = -1L;
/*  452:     */    }
/*  453:     */    else {
/*  454: 454 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  455: 455 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  456: 456 */      this.first = pos;
/*  457:     */    }
/*  458: 458 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  459:     */    }
/*  460: 460 */    return this.defRetValue;
/*  461:     */  }
/*  462:     */  
/*  467:     */  public V putAndMoveToLast(K k, V v)
/*  468:     */  {
/*  469: 469 */    K[] key = this.key;
/*  470: 470 */    boolean[] used = this.used;
/*  471: 471 */    int mask = this.mask;
/*  472:     */    
/*  473: 473 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*  474:     */    
/*  475: 475 */    while (used[pos] != 0) {
/*  476: 476 */      if (this.strategy.equals(k, key[pos])) {
/*  477: 477 */        V oldValue = this.value[pos];
/*  478: 478 */        this.value[pos] = v;
/*  479: 479 */        moveIndexToLast(pos);
/*  480: 480 */        return oldValue;
/*  481:     */      }
/*  482: 482 */      pos = pos + 1 & mask;
/*  483:     */    }
/*  484: 484 */    used[pos] = true;
/*  485: 485 */    key[pos] = k;
/*  486: 486 */    this.value[pos] = v;
/*  487: 487 */    if (this.size == 0) {
/*  488: 488 */      this.first = (this.last = pos);
/*  489:     */      
/*  490: 490 */      this.link[pos] = -1L;
/*  491:     */    }
/*  492:     */    else {
/*  493: 493 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  494: 494 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  495: 495 */      this.last = pos;
/*  496:     */    }
/*  497: 497 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  498:     */    }
/*  499: 499 */    return this.defRetValue;
/*  500:     */  }
/*  501:     */  
/*  502:     */  public V get(Object k)
/*  503:     */  {
/*  504: 504 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  505:     */    
/*  506: 506 */    while (this.used[pos] != 0) {
/*  507: 507 */      if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/*  508: 508 */      pos = pos + 1 & this.mask;
/*  509:     */    }
/*  510: 510 */    return this.defRetValue;
/*  511:     */  }
/*  512:     */  
/*  513:     */  public boolean containsKey(Object k)
/*  514:     */  {
/*  515: 515 */    int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*  516:     */    
/*  517: 517 */    while (this.used[pos] != 0) {
/*  518: 518 */      if (this.strategy.equals(this.key[pos], k)) return true;
/*  519: 519 */      pos = pos + 1 & this.mask;
/*  520:     */    }
/*  521: 521 */    return false;
/*  522:     */  }
/*  523:     */  
/*  524: 524 */  public boolean containsValue(Object v) { V[] value = this.value;
/*  525: 525 */    boolean[] used = this.used;
/*  526: 526 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v))) break label16;
/*  527: 527 */    return false;
/*  528:     */  }
/*  529:     */  
/*  534:     */  public void clear()
/*  535:     */  {
/*  536: 536 */    if (this.size == 0) return;
/*  537: 537 */    this.size = 0;
/*  538: 538 */    BooleanArrays.fill(this.used, false);
/*  539:     */    
/*  540: 540 */    ObjectArrays.fill(this.key, null);
/*  541: 541 */    ObjectArrays.fill(this.value, null);
/*  542: 542 */    this.first = (this.last = -1);
/*  543:     */  }
/*  544:     */  
/*  545: 545 */  public int size() { return this.size; }
/*  546:     */  
/*  547:     */  public boolean isEmpty() {
/*  548: 548 */    return this.size == 0;
/*  549:     */  }
/*  550:     */  
/*  555:     */  @Deprecated
/*  556:     */  public void growthFactor(int growthFactor) {}
/*  557:     */  
/*  562:     */  @Deprecated
/*  563:     */  public int growthFactor()
/*  564:     */  {
/*  565: 565 */    return 16;
/*  566:     */  }
/*  567:     */  
/*  568:     */  private final class MapEntry
/*  569:     */    implements Object2ObjectMap.Entry<K, V>, Map.Entry<K, V>
/*  570:     */  {
/*  571:     */    private int index;
/*  572:     */    
/*  573:     */    MapEntry(int index)
/*  574:     */    {
/*  575: 575 */      this.index = index;
/*  576:     */    }
/*  577:     */    
/*  578: 578 */    public K getKey() { return Object2ObjectLinkedOpenCustomHashMap.this.key[this.index]; }
/*  579:     */    
/*  581: 581 */    public V getValue() { return Object2ObjectLinkedOpenCustomHashMap.this.value[this.index]; }
/*  582:     */    
/*  583:     */    public V setValue(V v) {
/*  584: 584 */      V oldValue = Object2ObjectLinkedOpenCustomHashMap.this.value[this.index];
/*  585: 585 */      Object2ObjectLinkedOpenCustomHashMap.this.value[this.index] = v;
/*  586: 586 */      return oldValue;
/*  587:     */    }
/*  588:     */    
/*  589:     */    public boolean equals(Object o) {
/*  590: 590 */      if (!(o instanceof Map.Entry)) return false;
/*  591: 591 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  592: 592 */      return (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2ObjectLinkedOpenCustomHashMap.this.value[this.index] == null ? e.getValue() == null : Object2ObjectLinkedOpenCustomHashMap.this.value[this.index].equals(e.getValue()));
/*  593:     */    }
/*  594:     */    
/*  595: 595 */    public int hashCode() { return Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ObjectLinkedOpenCustomHashMap.this.key[this.index]) ^ (Object2ObjectLinkedOpenCustomHashMap.this.value[this.index] == null ? 0 : Object2ObjectLinkedOpenCustomHashMap.this.value[this.index].hashCode()); }
/*  596:     */    
/*  597:     */    public String toString() {
/*  598: 598 */      return Object2ObjectLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2ObjectLinkedOpenCustomHashMap.this.value[this.index];
/*  599:     */    }
/*  600:     */  }
/*  601:     */  
/*  607:     */  protected void fixPointers(int i)
/*  608:     */  {
/*  609: 609 */    if (this.size == 0) {
/*  610: 610 */      this.first = (this.last = -1);
/*  611: 611 */      return;
/*  612:     */    }
/*  613: 613 */    if (this.first == i) {
/*  614: 614 */      this.first = ((int)this.link[i]);
/*  615: 615 */      if (0 <= this.first)
/*  616:     */      {
/*  617: 617 */        this.link[this.first] |= -4294967296L;
/*  618:     */      }
/*  619: 619 */      return;
/*  620:     */    }
/*  621: 621 */    if (this.last == i) {
/*  622: 622 */      this.last = ((int)(this.link[i] >>> 32));
/*  623: 623 */      if (0 <= this.last)
/*  624:     */      {
/*  625: 625 */        this.link[this.last] |= 4294967295L;
/*  626:     */      }
/*  627: 627 */      return;
/*  628:     */    }
/*  629: 629 */    long linki = this.link[i];
/*  630: 630 */    int prev = (int)(linki >>> 32);
/*  631: 631 */    int next = (int)linki;
/*  632: 632 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  633: 633 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  634:     */  }
/*  635:     */  
/*  642:     */  protected void fixPointers(int s, int d)
/*  643:     */  {
/*  644: 644 */    if (this.size == 1) {
/*  645: 645 */      this.first = (this.last = d);
/*  646:     */      
/*  647: 647 */      this.link[d] = -1L;
/*  648: 648 */      return;
/*  649:     */    }
/*  650: 650 */    if (this.first == s) {
/*  651: 651 */      this.first = d;
/*  652: 652 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  653: 653 */      this.link[d] = this.link[s];
/*  654: 654 */      return;
/*  655:     */    }
/*  656: 656 */    if (this.last == s) {
/*  657: 657 */      this.last = d;
/*  658: 658 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  659: 659 */      this.link[d] = this.link[s];
/*  660: 660 */      return;
/*  661:     */    }
/*  662: 662 */    long links = this.link[s];
/*  663: 663 */    int prev = (int)(links >>> 32);
/*  664: 664 */    int next = (int)links;
/*  665: 665 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  666: 666 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  667: 667 */    this.link[d] = links;
/*  668:     */  }
/*  669:     */  
/*  672:     */  public K firstKey()
/*  673:     */  {
/*  674: 674 */    if (this.size == 0) throw new NoSuchElementException();
/*  675: 675 */    return this.key[this.first];
/*  676:     */  }
/*  677:     */  
/*  680:     */  public K lastKey()
/*  681:     */  {
/*  682: 682 */    if (this.size == 0) throw new NoSuchElementException();
/*  683: 683 */    return this.key[this.last]; }
/*  684:     */  
/*  685: 685 */  public Comparator<? super K> comparator() { return null; }
/*  686: 686 */  public Object2ObjectSortedMap<K, V> tailMap(K from) { throw new UnsupportedOperationException(); }
/*  687: 687 */  public Object2ObjectSortedMap<K, V> headMap(K to) { throw new UnsupportedOperationException(); }
/*  688: 688 */  public Object2ObjectSortedMap<K, V> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*  689:     */  
/*  695:     */  private class MapIterator
/*  696:     */  {
/*  697: 697 */    int prev = -1;
/*  698:     */    
/*  699: 699 */    int next = -1;
/*  700:     */    
/*  701: 701 */    int curr = -1;
/*  702:     */    
/*  703: 703 */    int index = -1;
/*  704:     */    
/*  705: 705 */    private MapIterator() { this.next = Object2ObjectLinkedOpenCustomHashMap.this.first;
/*  706: 706 */      this.index = 0;
/*  707:     */    }
/*  708:     */    
/*  709: 709 */    private MapIterator() { if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[Object2ObjectLinkedOpenCustomHashMap.this.last], from)) {
/*  710: 710 */        this.prev = Object2ObjectLinkedOpenCustomHashMap.this.last;
/*  711: 711 */        this.index = Object2ObjectLinkedOpenCustomHashMap.this.size;
/*  712:     */      }
/*  713:     */      else
/*  714:     */      {
/*  715: 715 */        int pos = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  716:     */        
/*  717: 717 */        while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  718: 718 */          if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[pos], from))
/*  719:     */          {
/*  720: 720 */            this.next = ((int)Object2ObjectLinkedOpenCustomHashMap.this.link[pos]);
/*  721: 721 */            this.prev = pos;
/*  722: 722 */            return;
/*  723:     */          }
/*  724: 724 */          pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  725:     */        }
/*  726: 726 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  727:     */      } }
/*  728:     */    
/*  729: 729 */    public boolean hasNext() { return this.next != -1; }
/*  730: 730 */    public boolean hasPrevious() { return this.prev != -1; }
/*  731:     */    
/*  732: 732 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  733: 733 */      if (this.prev == -1) {
/*  734: 734 */        this.index = 0;
/*  735: 735 */        return;
/*  736:     */      }
/*  737: 737 */      if (this.next == -1) {
/*  738: 738 */        this.index = Object2ObjectLinkedOpenCustomHashMap.this.size;
/*  739: 739 */        return;
/*  740:     */      }
/*  741: 741 */      int pos = Object2ObjectLinkedOpenCustomHashMap.this.first;
/*  742: 742 */      this.index = 1;
/*  743: 743 */      while (pos != this.prev) {
/*  744: 744 */        pos = (int)Object2ObjectLinkedOpenCustomHashMap.this.link[pos];
/*  745: 745 */        this.index += 1;
/*  746:     */      }
/*  747:     */    }
/*  748:     */    
/*  749: 749 */    public int nextIndex() { ensureIndexKnown();
/*  750: 750 */      return this.index;
/*  751:     */    }
/*  752:     */    
/*  753: 753 */    public int previousIndex() { ensureIndexKnown();
/*  754: 754 */      return this.index - 1;
/*  755:     */    }
/*  756:     */    
/*  757: 757 */    public int nextEntry() { if (!hasNext()) return Object2ObjectLinkedOpenCustomHashMap.this.size();
/*  758: 758 */      this.curr = this.next;
/*  759: 759 */      this.next = ((int)Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr]);
/*  760: 760 */      this.prev = this.curr;
/*  761: 761 */      if (this.index >= 0) this.index += 1;
/*  762: 762 */      return this.curr;
/*  763:     */    }
/*  764:     */    
/*  765: 765 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  766: 766 */      this.curr = this.prev;
/*  767: 767 */      this.prev = ((int)(Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  768: 768 */      this.next = this.curr;
/*  769: 769 */      if (this.index >= 0) this.index -= 1;
/*  770: 770 */      return this.curr;
/*  771:     */    }
/*  772:     */    
/*  773:     */    public void remove() {
/*  774: 774 */      ensureIndexKnown();
/*  775: 775 */      if (this.curr == -1) throw new IllegalStateException();
/*  776: 776 */      if (this.curr == this.prev)
/*  777:     */      {
/*  779: 779 */        this.index -= 1;
/*  780: 780 */        this.prev = ((int)(Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  781:     */      }
/*  782:     */      else {
/*  783: 783 */        this.next = ((int)Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr]); }
/*  784: 784 */      Object2ObjectLinkedOpenCustomHashMap.this.size -= 1;
/*  785:     */      
/*  787: 787 */      if (this.prev == -1) { Object2ObjectLinkedOpenCustomHashMap.this.first = this.next;
/*  788:     */      } else
/*  789: 789 */        Object2ObjectLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2ObjectLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  790: 790 */      if (this.next == -1) { Object2ObjectLinkedOpenCustomHashMap.this.last = this.prev;
/*  791:     */      } else
/*  792: 792 */        Object2ObjectLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2ObjectLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  793: 793 */      int pos = this.curr;
/*  794:     */      int last;
/*  795:     */      for (;;) {
/*  796: 796 */        pos = (last = pos) + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  797: 797 */        while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  798: 798 */          int slot = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ObjectLinkedOpenCustomHashMap.this.key[pos])) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  799: 799 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  800: 800 */          pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  801:     */        }
/*  802: 802 */        if (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] == 0) break;
/*  803: 803 */        Object2ObjectLinkedOpenCustomHashMap.this.key[last] = Object2ObjectLinkedOpenCustomHashMap.this.key[pos];
/*  804: 804 */        Object2ObjectLinkedOpenCustomHashMap.this.value[last] = Object2ObjectLinkedOpenCustomHashMap.this.value[pos];
/*  805: 805 */        if (this.next == pos) this.next = last;
/*  806: 806 */        if (this.prev == pos) this.prev = last;
/*  807: 807 */        Object2ObjectLinkedOpenCustomHashMap.this.fixPointers(pos, last);
/*  808:     */      }
/*  809: 809 */      Object2ObjectLinkedOpenCustomHashMap.this.used[last] = false;
/*  810: 810 */      Object2ObjectLinkedOpenCustomHashMap.this.key[last] = null;
/*  811: 811 */      Object2ObjectLinkedOpenCustomHashMap.this.value[last] = null;
/*  812: 812 */      this.curr = -1;
/*  813:     */    }
/*  814:     */    
/*  815: 815 */    public int skip(int n) { int i = n;
/*  816: 816 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  817: 817 */      return n - i - 1;
/*  818:     */    }
/*  819:     */    
/*  820: 820 */    public int back(int n) { int i = n;
/*  821: 821 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  822: 822 */      return n - i - 1;
/*  823:     */    } }
/*  824:     */  
/*  825:     */  private class EntryIterator extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator implements ObjectListIterator<Object2ObjectMap.Entry<K, V>> { private Object2ObjectLinkedOpenCustomHashMap<K, V>.MapEntry entry;
/*  826:     */    
/*  827: 827 */    public EntryIterator() { super(null); }
/*  828:     */    
/*  829: 829 */    public EntryIterator() { super(from, null); }
/*  830:     */    
/*  831:     */    public Object2ObjectLinkedOpenCustomHashMap<K, V>.MapEntry next() {
/*  832: 832 */      return this.entry = new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, nextEntry());
/*  833:     */    }
/*  834:     */    
/*  835: 835 */    public Object2ObjectLinkedOpenCustomHashMap<K, V>.MapEntry previous() { return this.entry = new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, previousEntry()); }
/*  836:     */    
/*  837:     */    public void remove()
/*  838:     */    {
/*  839: 839 */      super.remove();
/*  840: 840 */      Object2ObjectLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1); }
/*  841:     */    
/*  842: 842 */    public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  843: 843 */    public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  844:     */  }
/*  845:     */  
/*  846: 846 */  private class FastEntryIterator extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator implements ObjectListIterator<Object2ObjectMap.Entry<K, V>> { final AbstractObject2ObjectMap.BasicEntry<K, V> entry = new AbstractObject2ObjectMap.BasicEntry(null, null);
/*  847: 847 */    public FastEntryIterator() { super(null); }
/*  848:     */    
/*  849: 849 */    public FastEntryIterator() { super(from, null); }
/*  850:     */    
/*  851:     */    public AbstractObject2ObjectMap.BasicEntry<K, V> next() {
/*  852: 852 */      int e = nextEntry();
/*  853: 853 */      this.entry.key = Object2ObjectLinkedOpenCustomHashMap.this.key[e];
/*  854: 854 */      this.entry.value = Object2ObjectLinkedOpenCustomHashMap.this.value[e];
/*  855: 855 */      return this.entry;
/*  856:     */    }
/*  857:     */    
/*  858: 858 */    public AbstractObject2ObjectMap.BasicEntry<K, V> previous() { int e = previousEntry();
/*  859: 859 */      this.entry.key = Object2ObjectLinkedOpenCustomHashMap.this.key[e];
/*  860: 860 */      this.entry.value = Object2ObjectLinkedOpenCustomHashMap.this.value[e];
/*  861: 861 */      return this.entry; }
/*  862:     */    
/*  863: 863 */    public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  864: 864 */    public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } }
/*  865:     */  
/*  866:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Object2ObjectMap.Entry<K, V>> implements Object2ObjectSortedMap.FastSortedEntrySet<K, V> { private MapEntrySet() {}
/*  867:     */    
/*  868: 868 */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator() { return new Object2ObjectLinkedOpenCustomHashMap.EntryIterator(Object2ObjectLinkedOpenCustomHashMap.this); }
/*  869:     */    
/*  870: 870 */    public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return null; }
/*  871: 871 */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> fromElement, Object2ObjectMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); }
/*  872: 872 */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); }
/*  873: 873 */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> fromElement) { throw new UnsupportedOperationException(); }
/*  874:     */    
/*  875: 875 */    public Object2ObjectMap.Entry<K, V> first() { if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  876: 876 */      return new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, Object2ObjectLinkedOpenCustomHashMap.this.first);
/*  877:     */    }
/*  878:     */    
/*  879: 879 */    public Object2ObjectMap.Entry<K, V> last() { if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  880: 880 */      return new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, Object2ObjectLinkedOpenCustomHashMap.this.last);
/*  881:     */    }
/*  882:     */    
/*  883:     */    public boolean contains(Object o) {
/*  884: 884 */      if (!(o instanceof Map.Entry)) return false;
/*  885: 885 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  886: 886 */      K k = e.getKey();
/*  887:     */      
/*  888: 888 */      int pos = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  889:     */      
/*  890: 890 */      while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  891: 891 */        if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[pos], k)) return Object2ObjectLinkedOpenCustomHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Object2ObjectLinkedOpenCustomHashMap.this.value[pos].equals(e.getValue());
/*  892: 892 */        pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  893:     */      }
/*  894: 894 */      return false;
/*  895:     */    }
/*  896:     */    
/*  897:     */    public boolean remove(Object o) {
/*  898: 898 */      if (!(o instanceof Map.Entry)) return false;
/*  899: 899 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  900: 900 */      K k = e.getKey();
/*  901:     */      
/*  902: 902 */      int pos = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  903:     */      
/*  904: 904 */      while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  905: 905 */        if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[pos], k)) {
/*  906: 906 */          Object2ObjectLinkedOpenCustomHashMap.this.remove(e.getKey());
/*  907: 907 */          return true;
/*  908:     */        }
/*  909: 909 */        pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  910:     */      }
/*  911: 911 */      return false;
/*  912:     */    }
/*  913:     */    
/*  914: 914 */    public int size() { return Object2ObjectLinkedOpenCustomHashMap.this.size; }
/*  915:     */    
/*  916:     */    public void clear() {
/*  917: 917 */      Object2ObjectLinkedOpenCustomHashMap.this.clear();
/*  918:     */    }
/*  919:     */    
/*  920: 920 */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectLinkedOpenCustomHashMap.EntryIterator(Object2ObjectLinkedOpenCustomHashMap.this, from.getKey()); }
/*  921:     */    
/*  922:     */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator() {
/*  923: 923 */      return new Object2ObjectLinkedOpenCustomHashMap.FastEntryIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/*  924:     */    }
/*  925:     */    
/*  926: 926 */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectLinkedOpenCustomHashMap.FastEntryIterator(Object2ObjectLinkedOpenCustomHashMap.this, from.getKey()); }
/*  927:     */  }
/*  928:     */  
/*  929:     */  public Object2ObjectSortedMap.FastSortedEntrySet<K, V> object2ObjectEntrySet() {
/*  930: 930 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/*  931: 931 */    return this.entries;
/*  932:     */  }
/*  933:     */  
/*  936:     */  private final class KeyIterator
/*  937:     */    extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator
/*  938:     */    implements ObjectListIterator<K>
/*  939:     */  {
/*  940: 940 */    public KeyIterator() { super(k, null); }
/*  941: 941 */    public K previous() { return Object2ObjectLinkedOpenCustomHashMap.this.key[previousEntry()]; }
/*  942: 942 */    public void set(K k) { throw new UnsupportedOperationException(); }
/*  943: 943 */    public void add(K k) { throw new UnsupportedOperationException(); }
/*  944: 944 */    public KeyIterator() { super(null); }
/*  945: 945 */    public K next() { return Object2ObjectLinkedOpenCustomHashMap.this.key[nextEntry()]; } }
/*  946:     */  
/*  947:     */  private final class KeySet extends AbstractObjectSortedSet<K> { private KeySet() {}
/*  948:     */    
/*  949: 949 */    public ObjectListIterator<K> iterator(K from) { return new Object2ObjectLinkedOpenCustomHashMap.KeyIterator(Object2ObjectLinkedOpenCustomHashMap.this, from); }
/*  950:     */    
/*  951:     */    public ObjectListIterator<K> iterator() {
/*  952: 952 */      return new Object2ObjectLinkedOpenCustomHashMap.KeyIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/*  953:     */    }
/*  954:     */    
/*  955: 955 */    public int size() { return Object2ObjectLinkedOpenCustomHashMap.this.size; }
/*  956:     */    
/*  958: 958 */    public boolean contains(Object k) { return Object2ObjectLinkedOpenCustomHashMap.this.containsKey(k); }
/*  959:     */    
/*  960:     */    public boolean remove(Object k) {
/*  961: 961 */      int oldSize = Object2ObjectLinkedOpenCustomHashMap.this.size;
/*  962: 962 */      Object2ObjectLinkedOpenCustomHashMap.this.remove(k);
/*  963: 963 */      return Object2ObjectLinkedOpenCustomHashMap.this.size != oldSize;
/*  964:     */    }
/*  965:     */    
/*  966: 966 */    public void clear() { Object2ObjectLinkedOpenCustomHashMap.this.clear(); }
/*  967:     */    
/*  968:     */    public K first() {
/*  969: 969 */      if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  970: 970 */      return Object2ObjectLinkedOpenCustomHashMap.this.key[Object2ObjectLinkedOpenCustomHashMap.this.first];
/*  971:     */    }
/*  972:     */    
/*  973: 973 */    public K last() { if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  974: 974 */      return Object2ObjectLinkedOpenCustomHashMap.this.key[Object2ObjectLinkedOpenCustomHashMap.this.last]; }
/*  975:     */    
/*  976: 976 */    public Comparator<? super K> comparator() { return null; }
/*  977: 977 */    public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/*  978: 978 */    public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/*  979: 979 */    public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*  980:     */  }
/*  981:     */  
/*  982: 982 */  public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/*  983: 983 */    return this.keys;
/*  984:     */  }
/*  985:     */  
/*  988:     */  private final class ValueIterator
/*  989:     */    extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator
/*  990:     */    implements ObjectListIterator<V>
/*  991:     */  {
/*  992: 992 */    public V previous() { return Object2ObjectLinkedOpenCustomHashMap.this.value[previousEntry()]; }
/*  993: 993 */    public void set(V v) { throw new UnsupportedOperationException(); }
/*  994: 994 */    public void add(V v) { throw new UnsupportedOperationException(); }
/*  995: 995 */    public ValueIterator() { super(null); }
/*  996: 996 */    public V next() { return Object2ObjectLinkedOpenCustomHashMap.this.value[nextEntry()]; }
/*  997:     */  }
/*  998:     */  
/*  999: 999 */  public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1000:     */        public ObjectIterator<V> iterator() {
/* 1001:1001 */          return new Object2ObjectLinkedOpenCustomHashMap.ValueIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/* 1002:     */        }
/* 1003:     */        
/* 1004:1004 */        public int size() { return Object2ObjectLinkedOpenCustomHashMap.this.size; }
/* 1005:     */        
/* 1006:     */        public boolean contains(Object v) {
/* 1007:1007 */          return Object2ObjectLinkedOpenCustomHashMap.this.containsValue(v);
/* 1008:     */        }
/* 1009:     */        
/* 1010:1010 */        public void clear() { Object2ObjectLinkedOpenCustomHashMap.this.clear(); }
/* 1011:     */      };
/* 1012:     */    }
/* 1013:1013 */    return this.values;
/* 1014:     */  }
/* 1015:     */  
/* 1024:     */  @Deprecated
/* 1025:     */  public boolean rehash()
/* 1026:     */  {
/* 1027:1027 */    return true;
/* 1028:     */  }
/* 1029:     */  
/* 1040:     */  public boolean trim()
/* 1041:     */  {
/* 1042:1042 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1043:1043 */    if (l >= this.n) return true;
/* 1044:     */    try {
/* 1045:1045 */      rehash(l);
/* 1046:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1047:1047 */      return false; }
/* 1048:1048 */    return true;
/* 1049:     */  }
/* 1050:     */  
/* 1067:     */  public boolean trim(int n)
/* 1068:     */  {
/* 1069:1069 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1070:1070 */    if (this.n <= l) return true;
/* 1071:     */    try {
/* 1072:1072 */      rehash(l);
/* 1073:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1074:1074 */      return false; }
/* 1075:1075 */    return true;
/* 1076:     */  }
/* 1077:     */  
/* 1086:     */  protected void rehash(int newN)
/* 1087:     */  {
/* 1088:1088 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1089:     */    
/* 1090:1090 */    K[] key = this.key;
/* 1091:1091 */    V[] value = this.value;
/* 1092:1092 */    int newMask = newN - 1;
/* 1093:1093 */    K[] newKey = (Object[])new Object[newN];
/* 1094:1094 */    V[] newValue = (Object[])new Object[newN];
/* 1095:1095 */    boolean[] newUsed = new boolean[newN];
/* 1096:1096 */    long[] link = this.link;
/* 1097:1097 */    long[] newLink = new long[newN];
/* 1098:1098 */    this.first = -1;
/* 1099:1099 */    for (int j = this.size; j-- != 0;) {
/* 1100:1100 */      K k = key[i];
/* 1101:1101 */      int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 1102:1102 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1103:1103 */      newUsed[pos] = true;
/* 1104:1104 */      newKey[pos] = k;
/* 1105:1105 */      newValue[pos] = value[i];
/* 1106:1106 */      if (prev != -1) {
/* 1107:1107 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1108:1108 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1109:1109 */        newPrev = pos;
/* 1110:     */      }
/* 1111:     */      else {
/* 1112:1112 */        newPrev = this.first = pos;
/* 1113:     */        
/* 1114:1114 */        newLink[pos] = -1L;
/* 1115:     */      }
/* 1116:1116 */      int t = i;
/* 1117:1117 */      i = (int)link[i];
/* 1118:1118 */      prev = t;
/* 1119:     */    }
/* 1120:1120 */    this.n = newN;
/* 1121:1121 */    this.mask = newMask;
/* 1122:1122 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1123:1123 */    this.key = newKey;
/* 1124:1124 */    this.value = newValue;
/* 1125:1125 */    this.used = newUsed;
/* 1126:1126 */    this.link = newLink;
/* 1127:1127 */    this.last = newPrev;
/* 1128:1128 */    if (newPrev != -1)
/* 1129:     */    {
/* 1130:1130 */      newLink[newPrev] |= 4294967295L;
/* 1131:     */    }
/* 1132:     */  }
/* 1133:     */  
/* 1136:     */  public Object2ObjectLinkedOpenCustomHashMap<K, V> clone()
/* 1137:     */  {
/* 1138:     */    Object2ObjectLinkedOpenCustomHashMap<K, V> c;
/* 1139:     */    
/* 1141:     */    try
/* 1142:     */    {
/* 1143:1143 */      c = (Object2ObjectLinkedOpenCustomHashMap)super.clone();
/* 1144:     */    }
/* 1145:     */    catch (CloneNotSupportedException cantHappen) {
/* 1146:1146 */      throw new InternalError();
/* 1147:     */    }
/* 1148:1148 */    c.keys = null;
/* 1149:1149 */    c.values = null;
/* 1150:1150 */    c.entries = null;
/* 1151:1151 */    c.key = ((Object[])this.key.clone());
/* 1152:1152 */    c.value = ((Object[])this.value.clone());
/* 1153:1153 */    c.used = ((boolean[])this.used.clone());
/* 1154:1154 */    c.link = ((long[])this.link.clone());
/* 1155:1155 */    c.strategy = this.strategy;
/* 1156:1156 */    return c;
/* 1157:     */  }
/* 1158:     */  
/* 1166:     */  public int hashCode()
/* 1167:     */  {
/* 1168:1168 */    int h = 0;
/* 1169:1169 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1170:1170 */      while (this.used[i] == 0) i++;
/* 1171:1171 */      if (this != this.key[i])
/* 1172:1172 */        t = this.strategy.hashCode(this.key[i]);
/* 1173:1173 */      if (this != this.value[i])
/* 1174:1174 */        t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 1175:1175 */      h += t;
/* 1176:1176 */      i++;
/* 1177:     */    }
/* 1178:1178 */    return h;
/* 1179:     */  }
/* 1180:     */  
/* 1181:1181 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 1182:1182 */    V[] value = this.value;
/* 1183:1183 */    Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator i = new MapIterator(null);
/* 1184:1184 */    s.defaultWriteObject();
/* 1185:1185 */    for (int j = this.size; j-- != 0;) {
/* 1186:1186 */      int e = i.nextEntry();
/* 1187:1187 */      s.writeObject(key[e]);
/* 1188:1188 */      s.writeObject(value[e]);
/* 1189:     */    }
/* 1190:     */  }
/* 1191:     */  
/* 1192:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1193:1193 */    s.defaultReadObject();
/* 1194:1194 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1195:1195 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1196:1196 */    this.mask = (this.n - 1);
/* 1197:1197 */    K[] key = this.key = (Object[])new Object[this.n];
/* 1198:1198 */    V[] value = this.value = (Object[])new Object[this.n];
/* 1199:1199 */    boolean[] used = this.used = new boolean[this.n];
/* 1200:1200 */    long[] link = this.link = new long[this.n];
/* 1201:1201 */    int prev = -1;
/* 1202:1202 */    this.first = (this.last = -1);
/* 1203:     */    
/* 1205:1205 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1206:1206 */      K k = s.readObject();
/* 1207:1207 */      V v = s.readObject();
/* 1208:1208 */      pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 1209:1209 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1210:1210 */      used[pos] = true;
/* 1211:1211 */      key[pos] = k;
/* 1212:1212 */      value[pos] = v;
/* 1213:1213 */      if (this.first != -1) {
/* 1214:1214 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1215:1215 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1216:1216 */        prev = pos;
/* 1217:     */      }
/* 1218:     */      else {
/* 1219:1219 */        prev = this.first = pos;
/* 1220:     */        
/* 1221:1221 */        link[pos] |= -4294967296L;
/* 1222:     */      }
/* 1223:     */    }
/* 1224:1224 */    this.last = prev;
/* 1225:1225 */    if (prev != -1)
/* 1226:     */    {
/* 1227:1227 */      link[prev] |= 4294967295L;
/* 1228:     */    }
/* 1229:     */  }
/* 1230:     */  
/* 1231:     */  private void checkTable() {}
/* 1232:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenCustomHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */