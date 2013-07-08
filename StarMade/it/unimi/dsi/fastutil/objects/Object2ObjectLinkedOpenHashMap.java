/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.HashCommon;
/*    5:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    6:     */import java.io.IOException;
/*    7:     */import java.io.ObjectInputStream;
/*    8:     */import java.io.ObjectOutputStream;
/*    9:     */import java.io.Serializable;
/*   10:     */import java.util.Comparator;
/*   11:     */import java.util.Map;
/*   12:     */import java.util.Map.Entry;
/*   13:     */import java.util.NoSuchElementException;
/*   14:     */
/*   99:     */public class Object2ObjectLinkedOpenHashMap<K, V>
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
/*  126:     */  protected transient long[] link;
/*  127:     */  
/*  135:     */  public Object2ObjectLinkedOpenHashMap(int expected, float f)
/*  136:     */  {
/*  137: 137 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  138: 138 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  139: 139 */    this.f = f;
/*  140: 140 */    this.n = HashCommon.arraySize(expected, f);
/*  141: 141 */    this.mask = (this.n - 1);
/*  142: 142 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  143: 143 */    this.key = ((Object[])new Object[this.n]);
/*  144: 144 */    this.value = ((Object[])new Object[this.n]);
/*  145: 145 */    this.used = new boolean[this.n];
/*  146: 146 */    this.link = new long[this.n];
/*  147:     */  }
/*  148:     */  
/*  151:     */  public Object2ObjectLinkedOpenHashMap(int expected)
/*  152:     */  {
/*  153: 153 */    this(expected, 0.75F);
/*  154:     */  }
/*  155:     */  
/*  157:     */  public Object2ObjectLinkedOpenHashMap()
/*  158:     */  {
/*  159: 159 */    this(16, 0.75F);
/*  160:     */  }
/*  161:     */  
/*  165:     */  public Object2ObjectLinkedOpenHashMap(Map<? extends K, ? extends V> m, float f)
/*  166:     */  {
/*  167: 167 */    this(m.size(), f);
/*  168: 168 */    putAll(m);
/*  169:     */  }
/*  170:     */  
/*  173:     */  public Object2ObjectLinkedOpenHashMap(Map<? extends K, ? extends V> m)
/*  174:     */  {
/*  175: 175 */    this(m, 0.75F);
/*  176:     */  }
/*  177:     */  
/*  181:     */  public Object2ObjectLinkedOpenHashMap(Object2ObjectMap<K, V> m, float f)
/*  182:     */  {
/*  183: 183 */    this(m.size(), f);
/*  184: 184 */    putAll(m);
/*  185:     */  }
/*  186:     */  
/*  189:     */  public Object2ObjectLinkedOpenHashMap(Object2ObjectMap<K, V> m)
/*  190:     */  {
/*  191: 191 */    this(m, 0.75F);
/*  192:     */  }
/*  193:     */  
/*  199:     */  public Object2ObjectLinkedOpenHashMap(K[] k, V[] v, float f)
/*  200:     */  {
/*  201: 201 */    this(k.length, f);
/*  202: 202 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  203: 203 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  204:     */    }
/*  205:     */  }
/*  206:     */  
/*  210:     */  public Object2ObjectLinkedOpenHashMap(K[] k, V[] v)
/*  211:     */  {
/*  212: 212 */    this(k, v, 0.75F);
/*  213:     */  }
/*  214:     */  
/*  218:     */  public V put(K k, V v)
/*  219:     */  {
/*  220: 220 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*  221:     */    
/*  222: 222 */    while (this.used[pos] != 0) {
/*  223: 223 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/*  224: 224 */        V oldValue = this.value[pos];
/*  225: 225 */        this.value[pos] = v;
/*  226: 226 */        return oldValue;
/*  227:     */      }
/*  228: 228 */      pos = pos + 1 & this.mask;
/*  229:     */    }
/*  230: 230 */    this.used[pos] = true;
/*  231: 231 */    this.key[pos] = k;
/*  232: 232 */    this.value[pos] = v;
/*  233: 233 */    if (this.size == 0) {
/*  234: 234 */      this.first = (this.last = pos);
/*  235:     */      
/*  236: 236 */      this.link[pos] = -1L;
/*  237:     */    }
/*  238:     */    else {
/*  239: 239 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  240: 240 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  241: 241 */      this.last = pos;
/*  242:     */    }
/*  243: 243 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  244:     */    }
/*  245: 245 */    return this.defRetValue;
/*  246:     */  }
/*  247:     */  
/*  250:     */  protected final int shiftKeys(int pos)
/*  251:     */  {
/*  252:     */    int last;
/*  253:     */    
/*  255:     */    for (;;)
/*  256:     */    {
/*  257: 257 */      pos = (last = pos) + 1 & this.mask;
/*  258: 258 */      while (this.used[pos] != 0) {
/*  259: 259 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
/*  260: 260 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  261: 261 */        pos = pos + 1 & this.mask;
/*  262:     */      }
/*  263: 263 */      if (this.used[pos] == 0) break;
/*  264: 264 */      this.key[last] = this.key[pos];
/*  265: 265 */      this.value[last] = this.value[pos];
/*  266: 266 */      fixPointers(pos, last);
/*  267:     */    }
/*  268: 268 */    this.used[last] = false;
/*  269: 269 */    this.key[last] = null;
/*  270: 270 */    this.value[last] = null;
/*  271: 271 */    return last;
/*  272:     */  }
/*  273:     */  
/*  274:     */  public V remove(Object k)
/*  275:     */  {
/*  276: 276 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*  277:     */    
/*  278: 278 */    while (this.used[pos] != 0) {
/*  279: 279 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/*  280: 280 */        this.size -= 1;
/*  281: 281 */        fixPointers(pos);
/*  282: 282 */        V v = this.value[pos];
/*  283: 283 */        shiftKeys(pos);
/*  284: 284 */        return v;
/*  285:     */      }
/*  286: 286 */      pos = pos + 1 & this.mask;
/*  287:     */    }
/*  288: 288 */    return this.defRetValue;
/*  289:     */  }
/*  290:     */  
/*  293:     */  public V removeFirst()
/*  294:     */  {
/*  295: 295 */    if (this.size == 0) throw new NoSuchElementException();
/*  296: 296 */    this.size -= 1;
/*  297: 297 */    int pos = this.first;
/*  298:     */    
/*  299: 299 */    this.first = ((int)this.link[pos]);
/*  300: 300 */    if (0 <= this.first)
/*  301:     */    {
/*  302: 302 */      this.link[this.first] |= -4294967296L;
/*  303:     */    }
/*  304: 304 */    V v = this.value[pos];
/*  305: 305 */    shiftKeys(pos);
/*  306: 306 */    return v;
/*  307:     */  }
/*  308:     */  
/*  311:     */  public V removeLast()
/*  312:     */  {
/*  313: 313 */    if (this.size == 0) throw new NoSuchElementException();
/*  314: 314 */    this.size -= 1;
/*  315: 315 */    int pos = this.last;
/*  316:     */    
/*  317: 317 */    this.last = ((int)(this.link[pos] >>> 32));
/*  318: 318 */    if (0 <= this.last)
/*  319:     */    {
/*  320: 320 */      this.link[this.last] |= 4294967295L;
/*  321:     */    }
/*  322: 322 */    V v = this.value[pos];
/*  323: 323 */    shiftKeys(pos);
/*  324: 324 */    return v;
/*  325:     */  }
/*  326:     */  
/*  327: 327 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  328: 328 */    if (this.last == i) {
/*  329: 329 */      this.last = ((int)(this.link[i] >>> 32));
/*  330:     */      
/*  331: 331 */      this.link[this.last] |= 4294967295L;
/*  332:     */    }
/*  333:     */    else {
/*  334: 334 */      long linki = this.link[i];
/*  335: 335 */      int prev = (int)(linki >>> 32);
/*  336: 336 */      int next = (int)linki;
/*  337: 337 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  338: 338 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  339:     */    }
/*  340: 340 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  341: 341 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  342: 342 */    this.first = i;
/*  343:     */  }
/*  344:     */  
/*  345: 345 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  346: 346 */    if (this.first == i) {
/*  347: 347 */      this.first = ((int)this.link[i]);
/*  348:     */      
/*  349: 349 */      this.link[this.first] |= -4294967296L;
/*  350:     */    }
/*  351:     */    else {
/*  352: 352 */      long linki = this.link[i];
/*  353: 353 */      int prev = (int)(linki >>> 32);
/*  354: 354 */      int next = (int)linki;
/*  355: 355 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  356: 356 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  357:     */    }
/*  358: 358 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  359: 359 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  360: 360 */    this.last = i;
/*  361:     */  }
/*  362:     */  
/*  366:     */  public V getAndMoveToFirst(K k)
/*  367:     */  {
/*  368: 368 */    K[] key = this.key;
/*  369: 369 */    boolean[] used = this.used;
/*  370: 370 */    int mask = this.mask;
/*  371:     */    
/*  372: 372 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*  373:     */    
/*  374: 374 */    while (used[pos] != 0) {
/*  375: 375 */      if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  376: 376 */        moveIndexToFirst(pos);
/*  377: 377 */        return this.value[pos];
/*  378:     */      }
/*  379: 379 */      pos = pos + 1 & mask;
/*  380:     */    }
/*  381: 381 */    return this.defRetValue;
/*  382:     */  }
/*  383:     */  
/*  387:     */  public V getAndMoveToLast(K k)
/*  388:     */  {
/*  389: 389 */    K[] key = this.key;
/*  390: 390 */    boolean[] used = this.used;
/*  391: 391 */    int mask = this.mask;
/*  392:     */    
/*  393: 393 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*  394:     */    
/*  395: 395 */    while (used[pos] != 0) {
/*  396: 396 */      if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  397: 397 */        moveIndexToLast(pos);
/*  398: 398 */        return this.value[pos];
/*  399:     */      }
/*  400: 400 */      pos = pos + 1 & mask;
/*  401:     */    }
/*  402: 402 */    return this.defRetValue;
/*  403:     */  }
/*  404:     */  
/*  409:     */  public V putAndMoveToFirst(K k, V v)
/*  410:     */  {
/*  411: 411 */    K[] key = this.key;
/*  412: 412 */    boolean[] used = this.used;
/*  413: 413 */    int mask = this.mask;
/*  414:     */    
/*  415: 415 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*  416:     */    
/*  417: 417 */    while (used[pos] != 0) {
/*  418: 418 */      if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  419: 419 */        V oldValue = this.value[pos];
/*  420: 420 */        this.value[pos] = v;
/*  421: 421 */        moveIndexToFirst(pos);
/*  422: 422 */        return oldValue;
/*  423:     */      }
/*  424: 424 */      pos = pos + 1 & mask;
/*  425:     */    }
/*  426: 426 */    used[pos] = true;
/*  427: 427 */    key[pos] = k;
/*  428: 428 */    this.value[pos] = v;
/*  429: 429 */    if (this.size == 0) {
/*  430: 430 */      this.first = (this.last = pos);
/*  431:     */      
/*  432: 432 */      this.link[pos] = -1L;
/*  433:     */    }
/*  434:     */    else {
/*  435: 435 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  436: 436 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  437: 437 */      this.first = pos;
/*  438:     */    }
/*  439: 439 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  440:     */    }
/*  441: 441 */    return this.defRetValue;
/*  442:     */  }
/*  443:     */  
/*  448:     */  public V putAndMoveToLast(K k, V v)
/*  449:     */  {
/*  450: 450 */    K[] key = this.key;
/*  451: 451 */    boolean[] used = this.used;
/*  452: 452 */    int mask = this.mask;
/*  453:     */    
/*  454: 454 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*  455:     */    
/*  456: 456 */    while (used[pos] != 0) {
/*  457: 457 */      if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  458: 458 */        V oldValue = this.value[pos];
/*  459: 459 */        this.value[pos] = v;
/*  460: 460 */        moveIndexToLast(pos);
/*  461: 461 */        return oldValue;
/*  462:     */      }
/*  463: 463 */      pos = pos + 1 & mask;
/*  464:     */    }
/*  465: 465 */    used[pos] = true;
/*  466: 466 */    key[pos] = k;
/*  467: 467 */    this.value[pos] = v;
/*  468: 468 */    if (this.size == 0) {
/*  469: 469 */      this.first = (this.last = pos);
/*  470:     */      
/*  471: 471 */      this.link[pos] = -1L;
/*  472:     */    }
/*  473:     */    else {
/*  474: 474 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  475: 475 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  476: 476 */      this.last = pos;
/*  477:     */    }
/*  478: 478 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  479:     */    }
/*  480: 480 */    return this.defRetValue;
/*  481:     */  }
/*  482:     */  
/*  483:     */  public V get(Object k)
/*  484:     */  {
/*  485: 485 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*  486:     */    
/*  487: 487 */    while (this.used[pos] != 0) {
/*  488: 488 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.value[pos];
/*  489: 489 */      pos = pos + 1 & this.mask;
/*  490:     */    }
/*  491: 491 */    return this.defRetValue;
/*  492:     */  }
/*  493:     */  
/*  494:     */  public boolean containsKey(Object k)
/*  495:     */  {
/*  496: 496 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*  497:     */    
/*  498: 498 */    while (this.used[pos] != 0) {
/*  499: 499 */      if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/*  500: 500 */      pos = pos + 1 & this.mask;
/*  501:     */    }
/*  502: 502 */    return false;
/*  503:     */  }
/*  504:     */  
/*  505: 505 */  public boolean containsValue(Object v) { V[] value = this.value;
/*  506: 506 */    boolean[] used = this.used;
/*  507: 507 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v))) break label16;
/*  508: 508 */    return false;
/*  509:     */  }
/*  510:     */  
/*  515:     */  public void clear()
/*  516:     */  {
/*  517: 517 */    if (this.size == 0) return;
/*  518: 518 */    this.size = 0;
/*  519: 519 */    BooleanArrays.fill(this.used, false);
/*  520:     */    
/*  521: 521 */    ObjectArrays.fill(this.key, null);
/*  522: 522 */    ObjectArrays.fill(this.value, null);
/*  523: 523 */    this.first = (this.last = -1);
/*  524:     */  }
/*  525:     */  
/*  526: 526 */  public int size() { return this.size; }
/*  527:     */  
/*  528:     */  public boolean isEmpty() {
/*  529: 529 */    return this.size == 0;
/*  530:     */  }
/*  531:     */  
/*  536:     */  @Deprecated
/*  537:     */  public void growthFactor(int growthFactor) {}
/*  538:     */  
/*  543:     */  @Deprecated
/*  544:     */  public int growthFactor()
/*  545:     */  {
/*  546: 546 */    return 16;
/*  547:     */  }
/*  548:     */  
/*  549:     */  private final class MapEntry
/*  550:     */    implements Object2ObjectMap.Entry<K, V>, Map.Entry<K, V>
/*  551:     */  {
/*  552:     */    private int index;
/*  553:     */    
/*  554:     */    MapEntry(int index)
/*  555:     */    {
/*  556: 556 */      this.index = index;
/*  557:     */    }
/*  558:     */    
/*  559: 559 */    public K getKey() { return Object2ObjectLinkedOpenHashMap.this.key[this.index]; }
/*  560:     */    
/*  562: 562 */    public V getValue() { return Object2ObjectLinkedOpenHashMap.this.value[this.index]; }
/*  563:     */    
/*  564:     */    public V setValue(V v) {
/*  565: 565 */      V oldValue = Object2ObjectLinkedOpenHashMap.this.value[this.index];
/*  566: 566 */      Object2ObjectLinkedOpenHashMap.this.value[this.index] = v;
/*  567: 567 */      return oldValue;
/*  568:     */    }
/*  569:     */    
/*  570:     */    public boolean equals(Object o) {
/*  571: 571 */      if (!(o instanceof Map.Entry)) return false;
/*  572: 572 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  573: 573 */      return (Object2ObjectLinkedOpenHashMap.this.key[this.index] == null ? e.getKey() == null : Object2ObjectLinkedOpenHashMap.this.key[this.index].equals(e.getKey())) && (Object2ObjectLinkedOpenHashMap.this.value[this.index] == null ? e.getValue() == null : Object2ObjectLinkedOpenHashMap.this.value[this.index].equals(e.getValue()));
/*  574:     */    }
/*  575:     */    
/*  576: 576 */    public int hashCode() { return (Object2ObjectLinkedOpenHashMap.this.key[this.index] == null ? 0 : Object2ObjectLinkedOpenHashMap.this.key[this.index].hashCode()) ^ (Object2ObjectLinkedOpenHashMap.this.value[this.index] == null ? 0 : Object2ObjectLinkedOpenHashMap.this.value[this.index].hashCode()); }
/*  577:     */    
/*  578:     */    public String toString() {
/*  579: 579 */      return Object2ObjectLinkedOpenHashMap.this.key[this.index] + "=>" + Object2ObjectLinkedOpenHashMap.this.value[this.index];
/*  580:     */    }
/*  581:     */  }
/*  582:     */  
/*  588:     */  protected void fixPointers(int i)
/*  589:     */  {
/*  590: 590 */    if (this.size == 0) {
/*  591: 591 */      this.first = (this.last = -1);
/*  592: 592 */      return;
/*  593:     */    }
/*  594: 594 */    if (this.first == i) {
/*  595: 595 */      this.first = ((int)this.link[i]);
/*  596: 596 */      if (0 <= this.first)
/*  597:     */      {
/*  598: 598 */        this.link[this.first] |= -4294967296L;
/*  599:     */      }
/*  600: 600 */      return;
/*  601:     */    }
/*  602: 602 */    if (this.last == i) {
/*  603: 603 */      this.last = ((int)(this.link[i] >>> 32));
/*  604: 604 */      if (0 <= this.last)
/*  605:     */      {
/*  606: 606 */        this.link[this.last] |= 4294967295L;
/*  607:     */      }
/*  608: 608 */      return;
/*  609:     */    }
/*  610: 610 */    long linki = this.link[i];
/*  611: 611 */    int prev = (int)(linki >>> 32);
/*  612: 612 */    int next = (int)linki;
/*  613: 613 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  614: 614 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  615:     */  }
/*  616:     */  
/*  623:     */  protected void fixPointers(int s, int d)
/*  624:     */  {
/*  625: 625 */    if (this.size == 1) {
/*  626: 626 */      this.first = (this.last = d);
/*  627:     */      
/*  628: 628 */      this.link[d] = -1L;
/*  629: 629 */      return;
/*  630:     */    }
/*  631: 631 */    if (this.first == s) {
/*  632: 632 */      this.first = d;
/*  633: 633 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  634: 634 */      this.link[d] = this.link[s];
/*  635: 635 */      return;
/*  636:     */    }
/*  637: 637 */    if (this.last == s) {
/*  638: 638 */      this.last = d;
/*  639: 639 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  640: 640 */      this.link[d] = this.link[s];
/*  641: 641 */      return;
/*  642:     */    }
/*  643: 643 */    long links = this.link[s];
/*  644: 644 */    int prev = (int)(links >>> 32);
/*  645: 645 */    int next = (int)links;
/*  646: 646 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  647: 647 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  648: 648 */    this.link[d] = links;
/*  649:     */  }
/*  650:     */  
/*  653:     */  public K firstKey()
/*  654:     */  {
/*  655: 655 */    if (this.size == 0) throw new NoSuchElementException();
/*  656: 656 */    return this.key[this.first];
/*  657:     */  }
/*  658:     */  
/*  661:     */  public K lastKey()
/*  662:     */  {
/*  663: 663 */    if (this.size == 0) throw new NoSuchElementException();
/*  664: 664 */    return this.key[this.last]; }
/*  665:     */  
/*  666: 666 */  public Comparator<? super K> comparator() { return null; }
/*  667: 667 */  public Object2ObjectSortedMap<K, V> tailMap(K from) { throw new UnsupportedOperationException(); }
/*  668: 668 */  public Object2ObjectSortedMap<K, V> headMap(K to) { throw new UnsupportedOperationException(); }
/*  669: 669 */  public Object2ObjectSortedMap<K, V> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*  670:     */  
/*  676:     */  private class MapIterator
/*  677:     */  {
/*  678: 678 */    int prev = -1;
/*  679:     */    
/*  680: 680 */    int next = -1;
/*  681:     */    
/*  682: 682 */    int curr = -1;
/*  683:     */    
/*  684: 684 */    int index = -1;
/*  685:     */    
/*  686: 686 */    private MapIterator() { this.next = Object2ObjectLinkedOpenHashMap.this.first;
/*  687: 687 */      this.index = 0;
/*  688:     */    }
/*  689:     */    
/*  690: 690 */    private MapIterator() { if (Object2ObjectLinkedOpenHashMap.this.key[Object2ObjectLinkedOpenHashMap.this.last] == null ? from == null : Object2ObjectLinkedOpenHashMap.this.key[Object2ObjectLinkedOpenHashMap.this.last].equals(from)) {
/*  691: 691 */        this.prev = Object2ObjectLinkedOpenHashMap.this.last;
/*  692: 692 */        this.index = Object2ObjectLinkedOpenHashMap.this.size;
/*  693:     */      }
/*  694:     */      else
/*  695:     */      {
/*  696: 696 */        int pos = (from == null ? 142593372 : HashCommon.murmurHash3(from.hashCode())) & Object2ObjectLinkedOpenHashMap.this.mask;
/*  697:     */        
/*  698: 698 */        while (Object2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  699: 699 */          if (Object2ObjectLinkedOpenHashMap.this.key[pos] == null ? from == null : Object2ObjectLinkedOpenHashMap.this.key[pos].equals(from))
/*  700:     */          {
/*  701: 701 */            this.next = ((int)Object2ObjectLinkedOpenHashMap.this.link[pos]);
/*  702: 702 */            this.prev = pos;
/*  703: 703 */            return;
/*  704:     */          }
/*  705: 705 */          pos = pos + 1 & Object2ObjectLinkedOpenHashMap.this.mask;
/*  706:     */        }
/*  707: 707 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  708:     */      } }
/*  709:     */    
/*  710: 710 */    public boolean hasNext() { return this.next != -1; }
/*  711: 711 */    public boolean hasPrevious() { return this.prev != -1; }
/*  712:     */    
/*  713: 713 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  714: 714 */      if (this.prev == -1) {
/*  715: 715 */        this.index = 0;
/*  716: 716 */        return;
/*  717:     */      }
/*  718: 718 */      if (this.next == -1) {
/*  719: 719 */        this.index = Object2ObjectLinkedOpenHashMap.this.size;
/*  720: 720 */        return;
/*  721:     */      }
/*  722: 722 */      int pos = Object2ObjectLinkedOpenHashMap.this.first;
/*  723: 723 */      this.index = 1;
/*  724: 724 */      while (pos != this.prev) {
/*  725: 725 */        pos = (int)Object2ObjectLinkedOpenHashMap.this.link[pos];
/*  726: 726 */        this.index += 1;
/*  727:     */      }
/*  728:     */    }
/*  729:     */    
/*  730: 730 */    public int nextIndex() { ensureIndexKnown();
/*  731: 731 */      return this.index;
/*  732:     */    }
/*  733:     */    
/*  734: 734 */    public int previousIndex() { ensureIndexKnown();
/*  735: 735 */      return this.index - 1;
/*  736:     */    }
/*  737:     */    
/*  738: 738 */    public int nextEntry() { if (!hasNext()) return Object2ObjectLinkedOpenHashMap.this.size();
/*  739: 739 */      this.curr = this.next;
/*  740: 740 */      this.next = ((int)Object2ObjectLinkedOpenHashMap.this.link[this.curr]);
/*  741: 741 */      this.prev = this.curr;
/*  742: 742 */      if (this.index >= 0) this.index += 1;
/*  743: 743 */      return this.curr;
/*  744:     */    }
/*  745:     */    
/*  746: 746 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  747: 747 */      this.curr = this.prev;
/*  748: 748 */      this.prev = ((int)(Object2ObjectLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  749: 749 */      this.next = this.curr;
/*  750: 750 */      if (this.index >= 0) this.index -= 1;
/*  751: 751 */      return this.curr;
/*  752:     */    }
/*  753:     */    
/*  754:     */    public void remove() {
/*  755: 755 */      ensureIndexKnown();
/*  756: 756 */      if (this.curr == -1) throw new IllegalStateException();
/*  757: 757 */      if (this.curr == this.prev)
/*  758:     */      {
/*  760: 760 */        this.index -= 1;
/*  761: 761 */        this.prev = ((int)(Object2ObjectLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  762:     */      }
/*  763:     */      else {
/*  764: 764 */        this.next = ((int)Object2ObjectLinkedOpenHashMap.this.link[this.curr]); }
/*  765: 765 */      Object2ObjectLinkedOpenHashMap.this.size -= 1;
/*  766:     */      
/*  768: 768 */      if (this.prev == -1) { Object2ObjectLinkedOpenHashMap.this.first = this.next;
/*  769:     */      } else
/*  770: 770 */        Object2ObjectLinkedOpenHashMap.this.link[this.prev] ^= (Object2ObjectLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  771: 771 */      if (this.next == -1) { Object2ObjectLinkedOpenHashMap.this.last = this.prev;
/*  772:     */      } else
/*  773: 773 */        Object2ObjectLinkedOpenHashMap.this.link[this.next] ^= (Object2ObjectLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  774: 774 */      int pos = this.curr;
/*  775:     */      int last;
/*  776:     */      for (;;) {
/*  777: 777 */        pos = (last = pos) + 1 & Object2ObjectLinkedOpenHashMap.this.mask;
/*  778: 778 */        while (Object2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  779: 779 */          int slot = (Object2ObjectLinkedOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2ObjectLinkedOpenHashMap.this.key[pos].hashCode())) & Object2ObjectLinkedOpenHashMap.this.mask;
/*  780: 780 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  781: 781 */          pos = pos + 1 & Object2ObjectLinkedOpenHashMap.this.mask;
/*  782:     */        }
/*  783: 783 */        if (Object2ObjectLinkedOpenHashMap.this.used[pos] == 0) break;
/*  784: 784 */        Object2ObjectLinkedOpenHashMap.this.key[last] = Object2ObjectLinkedOpenHashMap.this.key[pos];
/*  785: 785 */        Object2ObjectLinkedOpenHashMap.this.value[last] = Object2ObjectLinkedOpenHashMap.this.value[pos];
/*  786: 786 */        if (this.next == pos) this.next = last;
/*  787: 787 */        if (this.prev == pos) this.prev = last;
/*  788: 788 */        Object2ObjectLinkedOpenHashMap.this.fixPointers(pos, last);
/*  789:     */      }
/*  790: 790 */      Object2ObjectLinkedOpenHashMap.this.used[last] = false;
/*  791: 791 */      Object2ObjectLinkedOpenHashMap.this.key[last] = null;
/*  792: 792 */      Object2ObjectLinkedOpenHashMap.this.value[last] = null;
/*  793: 793 */      this.curr = -1;
/*  794:     */    }
/*  795:     */    
/*  796: 796 */    public int skip(int n) { int i = n;
/*  797: 797 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  798: 798 */      return n - i - 1;
/*  799:     */    }
/*  800:     */    
/*  801: 801 */    public int back(int n) { int i = n;
/*  802: 802 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  803: 803 */      return n - i - 1;
/*  804:     */    } }
/*  805:     */  
/*  806:     */  private class EntryIterator extends Object2ObjectLinkedOpenHashMap<K, V>.MapIterator implements ObjectListIterator<Object2ObjectMap.Entry<K, V>> { private Object2ObjectLinkedOpenHashMap<K, V>.MapEntry entry;
/*  807:     */    
/*  808: 808 */    public EntryIterator() { super(null); }
/*  809:     */    
/*  810: 810 */    public EntryIterator() { super(from, null); }
/*  811:     */    
/*  812:     */    public Object2ObjectLinkedOpenHashMap<K, V>.MapEntry next() {
/*  813: 813 */      return this.entry = new Object2ObjectLinkedOpenHashMap.MapEntry(Object2ObjectLinkedOpenHashMap.this, nextEntry());
/*  814:     */    }
/*  815:     */    
/*  816: 816 */    public Object2ObjectLinkedOpenHashMap<K, V>.MapEntry previous() { return this.entry = new Object2ObjectLinkedOpenHashMap.MapEntry(Object2ObjectLinkedOpenHashMap.this, previousEntry()); }
/*  817:     */    
/*  818:     */    public void remove()
/*  819:     */    {
/*  820: 820 */      super.remove();
/*  821: 821 */      Object2ObjectLinkedOpenHashMap.MapEntry.access$202(this.entry, -1); }
/*  822:     */    
/*  823: 823 */    public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  824: 824 */    public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  825:     */  }
/*  826:     */  
/*  827: 827 */  private class FastEntryIterator extends Object2ObjectLinkedOpenHashMap<K, V>.MapIterator implements ObjectListIterator<Object2ObjectMap.Entry<K, V>> { final AbstractObject2ObjectMap.BasicEntry<K, V> entry = new AbstractObject2ObjectMap.BasicEntry(null, null);
/*  828: 828 */    public FastEntryIterator() { super(null); }
/*  829:     */    
/*  830: 830 */    public FastEntryIterator() { super(from, null); }
/*  831:     */    
/*  832:     */    public AbstractObject2ObjectMap.BasicEntry<K, V> next() {
/*  833: 833 */      int e = nextEntry();
/*  834: 834 */      this.entry.key = Object2ObjectLinkedOpenHashMap.this.key[e];
/*  835: 835 */      this.entry.value = Object2ObjectLinkedOpenHashMap.this.value[e];
/*  836: 836 */      return this.entry;
/*  837:     */    }
/*  838:     */    
/*  839: 839 */    public AbstractObject2ObjectMap.BasicEntry<K, V> previous() { int e = previousEntry();
/*  840: 840 */      this.entry.key = Object2ObjectLinkedOpenHashMap.this.key[e];
/*  841: 841 */      this.entry.value = Object2ObjectLinkedOpenHashMap.this.value[e];
/*  842: 842 */      return this.entry; }
/*  843:     */    
/*  844: 844 */    public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  845: 845 */    public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } }
/*  846:     */  
/*  847:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Object2ObjectMap.Entry<K, V>> implements Object2ObjectSortedMap.FastSortedEntrySet<K, V> { private MapEntrySet() {}
/*  848:     */    
/*  849: 849 */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator() { return new Object2ObjectLinkedOpenHashMap.EntryIterator(Object2ObjectLinkedOpenHashMap.this); }
/*  850:     */    
/*  851: 851 */    public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return null; }
/*  852: 852 */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> fromElement, Object2ObjectMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); }
/*  853: 853 */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); }
/*  854: 854 */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> fromElement) { throw new UnsupportedOperationException(); }
/*  855:     */    
/*  856: 856 */    public Object2ObjectMap.Entry<K, V> first() { if (Object2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  857: 857 */      return new Object2ObjectLinkedOpenHashMap.MapEntry(Object2ObjectLinkedOpenHashMap.this, Object2ObjectLinkedOpenHashMap.this.first);
/*  858:     */    }
/*  859:     */    
/*  860: 860 */    public Object2ObjectMap.Entry<K, V> last() { if (Object2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  861: 861 */      return new Object2ObjectLinkedOpenHashMap.MapEntry(Object2ObjectLinkedOpenHashMap.this, Object2ObjectLinkedOpenHashMap.this.last);
/*  862:     */    }
/*  863:     */    
/*  864:     */    public boolean contains(Object o) {
/*  865: 865 */      if (!(o instanceof Map.Entry)) return false;
/*  866: 866 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  867: 867 */      K k = e.getKey();
/*  868:     */      
/*  869: 869 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ObjectLinkedOpenHashMap.this.mask;
/*  870:     */      
/*  871: 871 */      while (Object2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  872: 872 */        if (Object2ObjectLinkedOpenHashMap.this.key[pos] == null ? k == null : Object2ObjectLinkedOpenHashMap.this.key[pos].equals(k)) return Object2ObjectLinkedOpenHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Object2ObjectLinkedOpenHashMap.this.value[pos].equals(e.getValue());
/*  873: 873 */        pos = pos + 1 & Object2ObjectLinkedOpenHashMap.this.mask;
/*  874:     */      }
/*  875: 875 */      return false;
/*  876:     */    }
/*  877:     */    
/*  878:     */    public boolean remove(Object o) {
/*  879: 879 */      if (!(o instanceof Map.Entry)) return false;
/*  880: 880 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  881: 881 */      K k = e.getKey();
/*  882:     */      
/*  883: 883 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ObjectLinkedOpenHashMap.this.mask;
/*  884:     */      
/*  885: 885 */      while (Object2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  886: 886 */        if (Object2ObjectLinkedOpenHashMap.this.key[pos] == null ? k == null : Object2ObjectLinkedOpenHashMap.this.key[pos].equals(k)) {
/*  887: 887 */          Object2ObjectLinkedOpenHashMap.this.remove(e.getKey());
/*  888: 888 */          return true;
/*  889:     */        }
/*  890: 890 */        pos = pos + 1 & Object2ObjectLinkedOpenHashMap.this.mask;
/*  891:     */      }
/*  892: 892 */      return false;
/*  893:     */    }
/*  894:     */    
/*  895: 895 */    public int size() { return Object2ObjectLinkedOpenHashMap.this.size; }
/*  896:     */    
/*  897:     */    public void clear() {
/*  898: 898 */      Object2ObjectLinkedOpenHashMap.this.clear();
/*  899:     */    }
/*  900:     */    
/*  901: 901 */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectLinkedOpenHashMap.EntryIterator(Object2ObjectLinkedOpenHashMap.this, from.getKey()); }
/*  902:     */    
/*  903:     */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator() {
/*  904: 904 */      return new Object2ObjectLinkedOpenHashMap.FastEntryIterator(Object2ObjectLinkedOpenHashMap.this);
/*  905:     */    }
/*  906:     */    
/*  907: 907 */    public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectLinkedOpenHashMap.FastEntryIterator(Object2ObjectLinkedOpenHashMap.this, from.getKey()); }
/*  908:     */  }
/*  909:     */  
/*  910:     */  public Object2ObjectSortedMap.FastSortedEntrySet<K, V> object2ObjectEntrySet() {
/*  911: 911 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/*  912: 912 */    return this.entries;
/*  913:     */  }
/*  914:     */  
/*  917:     */  private final class KeyIterator
/*  918:     */    extends Object2ObjectLinkedOpenHashMap<K, V>.MapIterator
/*  919:     */    implements ObjectListIterator<K>
/*  920:     */  {
/*  921: 921 */    public KeyIterator() { super(k, null); }
/*  922: 922 */    public K previous() { return Object2ObjectLinkedOpenHashMap.this.key[previousEntry()]; }
/*  923: 923 */    public void set(K k) { throw new UnsupportedOperationException(); }
/*  924: 924 */    public void add(K k) { throw new UnsupportedOperationException(); }
/*  925: 925 */    public KeyIterator() { super(null); }
/*  926: 926 */    public K next() { return Object2ObjectLinkedOpenHashMap.this.key[nextEntry()]; } }
/*  927:     */  
/*  928:     */  private final class KeySet extends AbstractObjectSortedSet<K> { private KeySet() {}
/*  929:     */    
/*  930: 930 */    public ObjectListIterator<K> iterator(K from) { return new Object2ObjectLinkedOpenHashMap.KeyIterator(Object2ObjectLinkedOpenHashMap.this, from); }
/*  931:     */    
/*  932:     */    public ObjectListIterator<K> iterator() {
/*  933: 933 */      return new Object2ObjectLinkedOpenHashMap.KeyIterator(Object2ObjectLinkedOpenHashMap.this);
/*  934:     */    }
/*  935:     */    
/*  936: 936 */    public int size() { return Object2ObjectLinkedOpenHashMap.this.size; }
/*  937:     */    
/*  939: 939 */    public boolean contains(Object k) { return Object2ObjectLinkedOpenHashMap.this.containsKey(k); }
/*  940:     */    
/*  941:     */    public boolean remove(Object k) {
/*  942: 942 */      int oldSize = Object2ObjectLinkedOpenHashMap.this.size;
/*  943: 943 */      Object2ObjectLinkedOpenHashMap.this.remove(k);
/*  944: 944 */      return Object2ObjectLinkedOpenHashMap.this.size != oldSize;
/*  945:     */    }
/*  946:     */    
/*  947: 947 */    public void clear() { Object2ObjectLinkedOpenHashMap.this.clear(); }
/*  948:     */    
/*  949:     */    public K first() {
/*  950: 950 */      if (Object2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  951: 951 */      return Object2ObjectLinkedOpenHashMap.this.key[Object2ObjectLinkedOpenHashMap.this.first];
/*  952:     */    }
/*  953:     */    
/*  954: 954 */    public K last() { if (Object2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  955: 955 */      return Object2ObjectLinkedOpenHashMap.this.key[Object2ObjectLinkedOpenHashMap.this.last]; }
/*  956:     */    
/*  957: 957 */    public Comparator<? super K> comparator() { return null; }
/*  958: 958 */    public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/*  959: 959 */    public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/*  960: 960 */    public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*  961:     */  }
/*  962:     */  
/*  963: 963 */  public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/*  964: 964 */    return this.keys;
/*  965:     */  }
/*  966:     */  
/*  969:     */  private final class ValueIterator
/*  970:     */    extends Object2ObjectLinkedOpenHashMap<K, V>.MapIterator
/*  971:     */    implements ObjectListIterator<V>
/*  972:     */  {
/*  973: 973 */    public V previous() { return Object2ObjectLinkedOpenHashMap.this.value[previousEntry()]; }
/*  974: 974 */    public void set(V v) { throw new UnsupportedOperationException(); }
/*  975: 975 */    public void add(V v) { throw new UnsupportedOperationException(); }
/*  976: 976 */    public ValueIterator() { super(null); }
/*  977: 977 */    public V next() { return Object2ObjectLinkedOpenHashMap.this.value[nextEntry()]; }
/*  978:     */  }
/*  979:     */  
/*  980: 980 */  public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/*  981:     */        public ObjectIterator<V> iterator() {
/*  982: 982 */          return new Object2ObjectLinkedOpenHashMap.ValueIterator(Object2ObjectLinkedOpenHashMap.this);
/*  983:     */        }
/*  984:     */        
/*  985: 985 */        public int size() { return Object2ObjectLinkedOpenHashMap.this.size; }
/*  986:     */        
/*  987:     */        public boolean contains(Object v) {
/*  988: 988 */          return Object2ObjectLinkedOpenHashMap.this.containsValue(v);
/*  989:     */        }
/*  990:     */        
/*  991: 991 */        public void clear() { Object2ObjectLinkedOpenHashMap.this.clear(); }
/*  992:     */      };
/*  993:     */    }
/*  994: 994 */    return this.values;
/*  995:     */  }
/*  996:     */  
/* 1005:     */  @Deprecated
/* 1006:     */  public boolean rehash()
/* 1007:     */  {
/* 1008:1008 */    return true;
/* 1009:     */  }
/* 1010:     */  
/* 1021:     */  public boolean trim()
/* 1022:     */  {
/* 1023:1023 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1024:1024 */    if (l >= this.n) return true;
/* 1025:     */    try {
/* 1026:1026 */      rehash(l);
/* 1027:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1028:1028 */      return false; }
/* 1029:1029 */    return true;
/* 1030:     */  }
/* 1031:     */  
/* 1048:     */  public boolean trim(int n)
/* 1049:     */  {
/* 1050:1050 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1051:1051 */    if (this.n <= l) return true;
/* 1052:     */    try {
/* 1053:1053 */      rehash(l);
/* 1054:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1055:1055 */      return false; }
/* 1056:1056 */    return true;
/* 1057:     */  }
/* 1058:     */  
/* 1067:     */  protected void rehash(int newN)
/* 1068:     */  {
/* 1069:1069 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1070:     */    
/* 1071:1071 */    K[] key = this.key;
/* 1072:1072 */    V[] value = this.value;
/* 1073:1073 */    int newMask = newN - 1;
/* 1074:1074 */    K[] newKey = (Object[])new Object[newN];
/* 1075:1075 */    V[] newValue = (Object[])new Object[newN];
/* 1076:1076 */    boolean[] newUsed = new boolean[newN];
/* 1077:1077 */    long[] link = this.link;
/* 1078:1078 */    long[] newLink = new long[newN];
/* 1079:1079 */    this.first = -1;
/* 1080:1080 */    for (int j = this.size; j-- != 0;) {
/* 1081:1081 */      K k = key[i];
/* 1082:1082 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
/* 1083:1083 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1084:1084 */      newUsed[pos] = true;
/* 1085:1085 */      newKey[pos] = k;
/* 1086:1086 */      newValue[pos] = value[i];
/* 1087:1087 */      if (prev != -1) {
/* 1088:1088 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1089:1089 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1090:1090 */        newPrev = pos;
/* 1091:     */      }
/* 1092:     */      else {
/* 1093:1093 */        newPrev = this.first = pos;
/* 1094:     */        
/* 1095:1095 */        newLink[pos] = -1L;
/* 1096:     */      }
/* 1097:1097 */      int t = i;
/* 1098:1098 */      i = (int)link[i];
/* 1099:1099 */      prev = t;
/* 1100:     */    }
/* 1101:1101 */    this.n = newN;
/* 1102:1102 */    this.mask = newMask;
/* 1103:1103 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1104:1104 */    this.key = newKey;
/* 1105:1105 */    this.value = newValue;
/* 1106:1106 */    this.used = newUsed;
/* 1107:1107 */    this.link = newLink;
/* 1108:1108 */    this.last = newPrev;
/* 1109:1109 */    if (newPrev != -1)
/* 1110:     */    {
/* 1111:1111 */      newLink[newPrev] |= 4294967295L;
/* 1112:     */    }
/* 1113:     */  }
/* 1114:     */  
/* 1117:     */  public Object2ObjectLinkedOpenHashMap<K, V> clone()
/* 1118:     */  {
/* 1119:     */    Object2ObjectLinkedOpenHashMap<K, V> c;
/* 1120:     */    
/* 1122:     */    try
/* 1123:     */    {
/* 1124:1124 */      c = (Object2ObjectLinkedOpenHashMap)super.clone();
/* 1125:     */    }
/* 1126:     */    catch (CloneNotSupportedException cantHappen) {
/* 1127:1127 */      throw new InternalError();
/* 1128:     */    }
/* 1129:1129 */    c.keys = null;
/* 1130:1130 */    c.values = null;
/* 1131:1131 */    c.entries = null;
/* 1132:1132 */    c.key = ((Object[])this.key.clone());
/* 1133:1133 */    c.value = ((Object[])this.value.clone());
/* 1134:1134 */    c.used = ((boolean[])this.used.clone());
/* 1135:1135 */    c.link = ((long[])this.link.clone());
/* 1136:1136 */    return c;
/* 1137:     */  }
/* 1138:     */  
/* 1146:     */  public int hashCode()
/* 1147:     */  {
/* 1148:1148 */    int h = 0;
/* 1149:1149 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1150:1150 */      while (this.used[i] == 0) i++;
/* 1151:1151 */      if (this != this.key[i])
/* 1152:1152 */        t = this.key[i] == null ? 0 : this.key[i].hashCode();
/* 1153:1153 */      if (this != this.value[i])
/* 1154:1154 */        t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 1155:1155 */      h += t;
/* 1156:1156 */      i++;
/* 1157:     */    }
/* 1158:1158 */    return h;
/* 1159:     */  }
/* 1160:     */  
/* 1161:1161 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 1162:1162 */    V[] value = this.value;
/* 1163:1163 */    Object2ObjectLinkedOpenHashMap<K, V>.MapIterator i = new MapIterator(null);
/* 1164:1164 */    s.defaultWriteObject();
/* 1165:1165 */    for (int j = this.size; j-- != 0;) {
/* 1166:1166 */      int e = i.nextEntry();
/* 1167:1167 */      s.writeObject(key[e]);
/* 1168:1168 */      s.writeObject(value[e]);
/* 1169:     */    }
/* 1170:     */  }
/* 1171:     */  
/* 1172:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1173:1173 */    s.defaultReadObject();
/* 1174:1174 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1175:1175 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1176:1176 */    this.mask = (this.n - 1);
/* 1177:1177 */    K[] key = this.key = (Object[])new Object[this.n];
/* 1178:1178 */    V[] value = this.value = (Object[])new Object[this.n];
/* 1179:1179 */    boolean[] used = this.used = new boolean[this.n];
/* 1180:1180 */    long[] link = this.link = new long[this.n];
/* 1181:1181 */    int prev = -1;
/* 1182:1182 */    this.first = (this.last = -1);
/* 1183:     */    
/* 1185:1185 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1186:1186 */      K k = s.readObject();
/* 1187:1187 */      V v = s.readObject();
/* 1188:1188 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 1189:1189 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1190:1190 */      used[pos] = true;
/* 1191:1191 */      key[pos] = k;
/* 1192:1192 */      value[pos] = v;
/* 1193:1193 */      if (this.first != -1) {
/* 1194:1194 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1195:1195 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1196:1196 */        prev = pos;
/* 1197:     */      }
/* 1198:     */      else {
/* 1199:1199 */        prev = this.first = pos;
/* 1200:     */        
/* 1201:1201 */        link[pos] |= -4294967296L;
/* 1202:     */      }
/* 1203:     */    }
/* 1204:1204 */    this.last = prev;
/* 1205:1205 */    if (prev != -1)
/* 1206:     */    {
/* 1207:1207 */      link[prev] |= 4294967295L;
/* 1208:     */    }
/* 1209:     */  }
/* 1210:     */  
/* 1211:     */  private void checkTable() {}
/* 1212:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */