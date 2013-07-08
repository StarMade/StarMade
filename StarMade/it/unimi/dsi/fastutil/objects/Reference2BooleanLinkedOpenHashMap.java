/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.HashCommon;
/*    5:     */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    7:     */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*    8:     */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*    9:     */import it.unimi.dsi.fastutil.booleans.BooleanListIterator;
/*   10:     */import java.io.IOException;
/*   11:     */import java.io.ObjectInputStream;
/*   12:     */import java.io.ObjectOutputStream;
/*   13:     */import java.io.Serializable;
/*   14:     */import java.util.Comparator;
/*   15:     */import java.util.Map;
/*   16:     */import java.util.Map.Entry;
/*   17:     */import java.util.NoSuchElementException;
/*   18:     */
/*  113:     */public class Reference2BooleanLinkedOpenHashMap<K>
/*  114:     */  extends AbstractReference2BooleanSortedMap<K>
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
/*  127:     */  protected volatile transient Reference2BooleanSortedMap.FastSortedEntrySet<K> entries;
/*  128:     */  protected volatile transient ReferenceSortedSet<K> keys;
/*  129:     */  protected volatile transient BooleanCollection values;
/*  130: 130 */  protected transient int first = -1;
/*  131:     */  
/*  132: 132 */  protected transient int last = -1;
/*  133:     */  
/*  140:     */  protected transient long[] link;
/*  141:     */  
/*  149:     */  public Reference2BooleanLinkedOpenHashMap(int expected, float f)
/*  150:     */  {
/*  151: 151 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152: 152 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153: 153 */    this.f = f;
/*  154: 154 */    this.n = HashCommon.arraySize(expected, f);
/*  155: 155 */    this.mask = (this.n - 1);
/*  156: 156 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  157: 157 */    this.key = ((Object[])new Object[this.n]);
/*  158: 158 */    this.value = new boolean[this.n];
/*  159: 159 */    this.used = new boolean[this.n];
/*  160: 160 */    this.link = new long[this.n];
/*  161:     */  }
/*  162:     */  
/*  165:     */  public Reference2BooleanLinkedOpenHashMap(int expected)
/*  166:     */  {
/*  167: 167 */    this(expected, 0.75F);
/*  168:     */  }
/*  169:     */  
/*  171:     */  public Reference2BooleanLinkedOpenHashMap()
/*  172:     */  {
/*  173: 173 */    this(16, 0.75F);
/*  174:     */  }
/*  175:     */  
/*  179:     */  public Reference2BooleanLinkedOpenHashMap(Map<? extends K, ? extends Boolean> m, float f)
/*  180:     */  {
/*  181: 181 */    this(m.size(), f);
/*  182: 182 */    putAll(m);
/*  183:     */  }
/*  184:     */  
/*  187:     */  public Reference2BooleanLinkedOpenHashMap(Map<? extends K, ? extends Boolean> m)
/*  188:     */  {
/*  189: 189 */    this(m, 0.75F);
/*  190:     */  }
/*  191:     */  
/*  195:     */  public Reference2BooleanLinkedOpenHashMap(Reference2BooleanMap<K> m, float f)
/*  196:     */  {
/*  197: 197 */    this(m.size(), f);
/*  198: 198 */    putAll(m);
/*  199:     */  }
/*  200:     */  
/*  203:     */  public Reference2BooleanLinkedOpenHashMap(Reference2BooleanMap<K> m)
/*  204:     */  {
/*  205: 205 */    this(m, 0.75F);
/*  206:     */  }
/*  207:     */  
/*  213:     */  public Reference2BooleanLinkedOpenHashMap(K[] k, boolean[] v, float f)
/*  214:     */  {
/*  215: 215 */    this(k.length, f);
/*  216: 216 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217: 217 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  218:     */    }
/*  219:     */  }
/*  220:     */  
/*  224:     */  public Reference2BooleanLinkedOpenHashMap(K[] k, boolean[] v)
/*  225:     */  {
/*  226: 226 */    this(k, v, 0.75F);
/*  227:     */  }
/*  228:     */  
/*  232:     */  public boolean put(K k, boolean v)
/*  233:     */  {
/*  234: 234 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*  235:     */    
/*  236: 236 */    while (this.used[pos] != 0) {
/*  237: 237 */      if (this.key[pos] == k) {
/*  238: 238 */        boolean oldValue = this.value[pos];
/*  239: 239 */        this.value[pos] = v;
/*  240: 240 */        return oldValue;
/*  241:     */      }
/*  242: 242 */      pos = pos + 1 & this.mask;
/*  243:     */    }
/*  244: 244 */    this.used[pos] = true;
/*  245: 245 */    this.key[pos] = k;
/*  246: 246 */    this.value[pos] = v;
/*  247: 247 */    if (this.size == 0) {
/*  248: 248 */      this.first = (this.last = pos);
/*  249:     */      
/*  250: 250 */      this.link[pos] = -1L;
/*  251:     */    }
/*  252:     */    else {
/*  253: 253 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  254: 254 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  255: 255 */      this.last = pos;
/*  256:     */    }
/*  257: 257 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  258:     */    }
/*  259: 259 */    return this.defRetValue;
/*  260:     */  }
/*  261:     */  
/*  262: 262 */  public Boolean put(K ok, Boolean ov) { boolean v = ov.booleanValue();
/*  263: 263 */    K k = ok;
/*  264:     */    
/*  265: 265 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*  266:     */    
/*  267: 267 */    while (this.used[pos] != 0) {
/*  268: 268 */      if (this.key[pos] == k) {
/*  269: 269 */        Boolean oldValue = Boolean.valueOf(this.value[pos]);
/*  270: 270 */        this.value[pos] = v;
/*  271: 271 */        return oldValue;
/*  272:     */      }
/*  273: 273 */      pos = pos + 1 & this.mask;
/*  274:     */    }
/*  275: 275 */    this.used[pos] = true;
/*  276: 276 */    this.key[pos] = k;
/*  277: 277 */    this.value[pos] = v;
/*  278: 278 */    if (this.size == 0) {
/*  279: 279 */      this.first = (this.last = pos);
/*  280:     */      
/*  281: 281 */      this.link[pos] = -1L;
/*  282:     */    }
/*  283:     */    else {
/*  284: 284 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  285: 285 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  286: 286 */      this.last = pos;
/*  287:     */    }
/*  288: 288 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  289:     */    }
/*  290: 290 */    return null;
/*  291:     */  }
/*  292:     */  
/*  295:     */  protected final int shiftKeys(int pos)
/*  296:     */  {
/*  297:     */    int last;
/*  298:     */    
/*  300:     */    for (;;)
/*  301:     */    {
/*  302: 302 */      pos = (last = pos) + 1 & this.mask;
/*  303: 303 */      while (this.used[pos] != 0) {
/*  304: 304 */        int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/*  305: 305 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  306: 306 */        pos = pos + 1 & this.mask;
/*  307:     */      }
/*  308: 308 */      if (this.used[pos] == 0) break;
/*  309: 309 */      this.key[last] = this.key[pos];
/*  310: 310 */      this.value[last] = this.value[pos];
/*  311: 311 */      fixPointers(pos, last);
/*  312:     */    }
/*  313: 313 */    this.used[last] = false;
/*  314: 314 */    this.key[last] = null;
/*  315: 315 */    return last;
/*  316:     */  }
/*  317:     */  
/*  318:     */  public boolean removeBoolean(Object k)
/*  319:     */  {
/*  320: 320 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*  321:     */    
/*  322: 322 */    while (this.used[pos] != 0) {
/*  323: 323 */      if (this.key[pos] == k) {
/*  324: 324 */        this.size -= 1;
/*  325: 325 */        fixPointers(pos);
/*  326: 326 */        boolean v = this.value[pos];
/*  327: 327 */        shiftKeys(pos);
/*  328: 328 */        return v;
/*  329:     */      }
/*  330: 330 */      pos = pos + 1 & this.mask;
/*  331:     */    }
/*  332: 332 */    return this.defRetValue;
/*  333:     */  }
/*  334:     */  
/*  335:     */  public Boolean remove(Object ok) {
/*  336: 336 */    K k = ok;
/*  337:     */    
/*  338: 338 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*  339:     */    
/*  340: 340 */    while (this.used[pos] != 0) {
/*  341: 341 */      if (this.key[pos] == k) {
/*  342: 342 */        this.size -= 1;
/*  343: 343 */        fixPointers(pos);
/*  344: 344 */        boolean v = this.value[pos];
/*  345: 345 */        shiftKeys(pos);
/*  346: 346 */        return Boolean.valueOf(v);
/*  347:     */      }
/*  348: 348 */      pos = pos + 1 & this.mask;
/*  349:     */    }
/*  350: 350 */    return null;
/*  351:     */  }
/*  352:     */  
/*  355:     */  public boolean removeFirstBoolean()
/*  356:     */  {
/*  357: 357 */    if (this.size == 0) throw new NoSuchElementException();
/*  358: 358 */    this.size -= 1;
/*  359: 359 */    int pos = this.first;
/*  360:     */    
/*  361: 361 */    this.first = ((int)this.link[pos]);
/*  362: 362 */    if (0 <= this.first)
/*  363:     */    {
/*  364: 364 */      this.link[this.first] |= -4294967296L;
/*  365:     */    }
/*  366: 366 */    boolean v = this.value[pos];
/*  367: 367 */    shiftKeys(pos);
/*  368: 368 */    return v;
/*  369:     */  }
/*  370:     */  
/*  373:     */  public boolean removeLastBoolean()
/*  374:     */  {
/*  375: 375 */    if (this.size == 0) throw new NoSuchElementException();
/*  376: 376 */    this.size -= 1;
/*  377: 377 */    int pos = this.last;
/*  378:     */    
/*  379: 379 */    this.last = ((int)(this.link[pos] >>> 32));
/*  380: 380 */    if (0 <= this.last)
/*  381:     */    {
/*  382: 382 */      this.link[this.last] |= 4294967295L;
/*  383:     */    }
/*  384: 384 */    boolean v = this.value[pos];
/*  385: 385 */    shiftKeys(pos);
/*  386: 386 */    return v;
/*  387:     */  }
/*  388:     */  
/*  389: 389 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  390: 390 */    if (this.last == i) {
/*  391: 391 */      this.last = ((int)(this.link[i] >>> 32));
/*  392:     */      
/*  393: 393 */      this.link[this.last] |= 4294967295L;
/*  394:     */    }
/*  395:     */    else {
/*  396: 396 */      long linki = this.link[i];
/*  397: 397 */      int prev = (int)(linki >>> 32);
/*  398: 398 */      int next = (int)linki;
/*  399: 399 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  400: 400 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  401:     */    }
/*  402: 402 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  403: 403 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  404: 404 */    this.first = i;
/*  405:     */  }
/*  406:     */  
/*  407: 407 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  408: 408 */    if (this.first == i) {
/*  409: 409 */      this.first = ((int)this.link[i]);
/*  410:     */      
/*  411: 411 */      this.link[this.first] |= -4294967296L;
/*  412:     */    }
/*  413:     */    else {
/*  414: 414 */      long linki = this.link[i];
/*  415: 415 */      int prev = (int)(linki >>> 32);
/*  416: 416 */      int next = (int)linki;
/*  417: 417 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  418: 418 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  419:     */    }
/*  420: 420 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  421: 421 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  422: 422 */    this.last = i;
/*  423:     */  }
/*  424:     */  
/*  428:     */  public boolean getAndMoveToFirst(K k)
/*  429:     */  {
/*  430: 430 */    K[] key = this.key;
/*  431: 431 */    boolean[] used = this.used;
/*  432: 432 */    int mask = this.mask;
/*  433:     */    
/*  434: 434 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*  435:     */    
/*  436: 436 */    while (used[pos] != 0) {
/*  437: 437 */      if (k == key[pos]) {
/*  438: 438 */        moveIndexToFirst(pos);
/*  439: 439 */        return this.value[pos];
/*  440:     */      }
/*  441: 441 */      pos = pos + 1 & mask;
/*  442:     */    }
/*  443: 443 */    return this.defRetValue;
/*  444:     */  }
/*  445:     */  
/*  449:     */  public boolean getAndMoveToLast(K k)
/*  450:     */  {
/*  451: 451 */    K[] key = this.key;
/*  452: 452 */    boolean[] used = this.used;
/*  453: 453 */    int mask = this.mask;
/*  454:     */    
/*  455: 455 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*  456:     */    
/*  457: 457 */    while (used[pos] != 0) {
/*  458: 458 */      if (k == key[pos]) {
/*  459: 459 */        moveIndexToLast(pos);
/*  460: 460 */        return this.value[pos];
/*  461:     */      }
/*  462: 462 */      pos = pos + 1 & mask;
/*  463:     */    }
/*  464: 464 */    return this.defRetValue;
/*  465:     */  }
/*  466:     */  
/*  471:     */  public boolean putAndMoveToFirst(K k, boolean v)
/*  472:     */  {
/*  473: 473 */    K[] key = this.key;
/*  474: 474 */    boolean[] used = this.used;
/*  475: 475 */    int mask = this.mask;
/*  476:     */    
/*  477: 477 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*  478:     */    
/*  479: 479 */    while (used[pos] != 0) {
/*  480: 480 */      if (k == key[pos]) {
/*  481: 481 */        boolean oldValue = this.value[pos];
/*  482: 482 */        this.value[pos] = v;
/*  483: 483 */        moveIndexToFirst(pos);
/*  484: 484 */        return oldValue;
/*  485:     */      }
/*  486: 486 */      pos = pos + 1 & mask;
/*  487:     */    }
/*  488: 488 */    used[pos] = true;
/*  489: 489 */    key[pos] = k;
/*  490: 490 */    this.value[pos] = v;
/*  491: 491 */    if (this.size == 0) {
/*  492: 492 */      this.first = (this.last = pos);
/*  493:     */      
/*  494: 494 */      this.link[pos] = -1L;
/*  495:     */    }
/*  496:     */    else {
/*  497: 497 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  498: 498 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  499: 499 */      this.first = pos;
/*  500:     */    }
/*  501: 501 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  502:     */    }
/*  503: 503 */    return this.defRetValue;
/*  504:     */  }
/*  505:     */  
/*  510:     */  public boolean putAndMoveToLast(K k, boolean v)
/*  511:     */  {
/*  512: 512 */    K[] key = this.key;
/*  513: 513 */    boolean[] used = this.used;
/*  514: 514 */    int mask = this.mask;
/*  515:     */    
/*  516: 516 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*  517:     */    
/*  518: 518 */    while (used[pos] != 0) {
/*  519: 519 */      if (k == key[pos]) {
/*  520: 520 */        boolean oldValue = this.value[pos];
/*  521: 521 */        this.value[pos] = v;
/*  522: 522 */        moveIndexToLast(pos);
/*  523: 523 */        return oldValue;
/*  524:     */      }
/*  525: 525 */      pos = pos + 1 & mask;
/*  526:     */    }
/*  527: 527 */    used[pos] = true;
/*  528: 528 */    key[pos] = k;
/*  529: 529 */    this.value[pos] = v;
/*  530: 530 */    if (this.size == 0) {
/*  531: 531 */      this.first = (this.last = pos);
/*  532:     */      
/*  533: 533 */      this.link[pos] = -1L;
/*  534:     */    }
/*  535:     */    else {
/*  536: 536 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  537: 537 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  538: 538 */      this.last = pos;
/*  539:     */    }
/*  540: 540 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  541:     */    }
/*  542: 542 */    return this.defRetValue;
/*  543:     */  }
/*  544:     */  
/*  545:     */  public boolean getBoolean(Object k)
/*  546:     */  {
/*  547: 547 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*  548:     */    
/*  549: 549 */    while (this.used[pos] != 0) {
/*  550: 550 */      if (this.key[pos] == k) return this.value[pos];
/*  551: 551 */      pos = pos + 1 & this.mask;
/*  552:     */    }
/*  553: 553 */    return this.defRetValue;
/*  554:     */  }
/*  555:     */  
/*  556:     */  public boolean containsKey(Object k)
/*  557:     */  {
/*  558: 558 */    int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*  559:     */    
/*  560: 560 */    while (this.used[pos] != 0) {
/*  561: 561 */      if (this.key[pos] == k) return true;
/*  562: 562 */      pos = pos + 1 & this.mask;
/*  563:     */    }
/*  564: 564 */    return false;
/*  565:     */  }
/*  566:     */  
/*  567: 567 */  public boolean containsValue(boolean v) { boolean[] value = this.value;
/*  568: 568 */    boolean[] used = this.used;
/*  569: 569 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/*  570: 570 */    return false;
/*  571:     */  }
/*  572:     */  
/*  577:     */  public void clear()
/*  578:     */  {
/*  579: 579 */    if (this.size == 0) return;
/*  580: 580 */    this.size = 0;
/*  581: 581 */    BooleanArrays.fill(this.used, false);
/*  582:     */    
/*  583: 583 */    ObjectArrays.fill(this.key, null);
/*  584: 584 */    this.first = (this.last = -1);
/*  585:     */  }
/*  586:     */  
/*  587: 587 */  public int size() { return this.size; }
/*  588:     */  
/*  589:     */  public boolean isEmpty() {
/*  590: 590 */    return this.size == 0;
/*  591:     */  }
/*  592:     */  
/*  597:     */  @Deprecated
/*  598:     */  public void growthFactor(int growthFactor) {}
/*  599:     */  
/*  604:     */  @Deprecated
/*  605:     */  public int growthFactor()
/*  606:     */  {
/*  607: 607 */    return 16;
/*  608:     */  }
/*  609:     */  
/*  610:     */  private final class MapEntry
/*  611:     */    implements Reference2BooleanMap.Entry<K>, Map.Entry<K, Boolean>
/*  612:     */  {
/*  613:     */    private int index;
/*  614:     */    
/*  615:     */    MapEntry(int index)
/*  616:     */    {
/*  617: 617 */      this.index = index;
/*  618:     */    }
/*  619:     */    
/*  620: 620 */    public K getKey() { return Reference2BooleanLinkedOpenHashMap.this.key[this.index]; }
/*  621:     */    
/*  622:     */    public Boolean getValue() {
/*  623: 623 */      return Boolean.valueOf(Reference2BooleanLinkedOpenHashMap.this.value[this.index]);
/*  624:     */    }
/*  625:     */    
/*  626: 626 */    public boolean getBooleanValue() { return Reference2BooleanLinkedOpenHashMap.this.value[this.index]; }
/*  627:     */    
/*  628:     */    public boolean setValue(boolean v) {
/*  629: 629 */      boolean oldValue = Reference2BooleanLinkedOpenHashMap.this.value[this.index];
/*  630: 630 */      Reference2BooleanLinkedOpenHashMap.this.value[this.index] = v;
/*  631: 631 */      return oldValue;
/*  632:     */    }
/*  633:     */    
/*  634: 634 */    public Boolean setValue(Boolean v) { return Boolean.valueOf(setValue(v.booleanValue())); }
/*  635:     */    
/*  636:     */    public boolean equals(Object o)
/*  637:     */    {
/*  638: 638 */      if (!(o instanceof Map.Entry)) return false;
/*  639: 639 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  640: 640 */      return (Reference2BooleanLinkedOpenHashMap.this.key[this.index] == e.getKey()) && (Reference2BooleanLinkedOpenHashMap.this.value[this.index] == ((Boolean)e.getValue()).booleanValue());
/*  641:     */    }
/*  642:     */    
/*  643: 643 */    public int hashCode() { return (Reference2BooleanLinkedOpenHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2BooleanLinkedOpenHashMap.this.key[this.index])) ^ (Reference2BooleanLinkedOpenHashMap.this.value[this.index] != 0 ? 1231 : 1237); }
/*  644:     */    
/*  645:     */    public String toString() {
/*  646: 646 */      return Reference2BooleanLinkedOpenHashMap.this.key[this.index] + "=>" + Reference2BooleanLinkedOpenHashMap.this.value[this.index];
/*  647:     */    }
/*  648:     */  }
/*  649:     */  
/*  655:     */  protected void fixPointers(int i)
/*  656:     */  {
/*  657: 657 */    if (this.size == 0) {
/*  658: 658 */      this.first = (this.last = -1);
/*  659: 659 */      return;
/*  660:     */    }
/*  661: 661 */    if (this.first == i) {
/*  662: 662 */      this.first = ((int)this.link[i]);
/*  663: 663 */      if (0 <= this.first)
/*  664:     */      {
/*  665: 665 */        this.link[this.first] |= -4294967296L;
/*  666:     */      }
/*  667: 667 */      return;
/*  668:     */    }
/*  669: 669 */    if (this.last == i) {
/*  670: 670 */      this.last = ((int)(this.link[i] >>> 32));
/*  671: 671 */      if (0 <= this.last)
/*  672:     */      {
/*  673: 673 */        this.link[this.last] |= 4294967295L;
/*  674:     */      }
/*  675: 675 */      return;
/*  676:     */    }
/*  677: 677 */    long linki = this.link[i];
/*  678: 678 */    int prev = (int)(linki >>> 32);
/*  679: 679 */    int next = (int)linki;
/*  680: 680 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  681: 681 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  682:     */  }
/*  683:     */  
/*  690:     */  protected void fixPointers(int s, int d)
/*  691:     */  {
/*  692: 692 */    if (this.size == 1) {
/*  693: 693 */      this.first = (this.last = d);
/*  694:     */      
/*  695: 695 */      this.link[d] = -1L;
/*  696: 696 */      return;
/*  697:     */    }
/*  698: 698 */    if (this.first == s) {
/*  699: 699 */      this.first = d;
/*  700: 700 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  701: 701 */      this.link[d] = this.link[s];
/*  702: 702 */      return;
/*  703:     */    }
/*  704: 704 */    if (this.last == s) {
/*  705: 705 */      this.last = d;
/*  706: 706 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  707: 707 */      this.link[d] = this.link[s];
/*  708: 708 */      return;
/*  709:     */    }
/*  710: 710 */    long links = this.link[s];
/*  711: 711 */    int prev = (int)(links >>> 32);
/*  712: 712 */    int next = (int)links;
/*  713: 713 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  714: 714 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  715: 715 */    this.link[d] = links;
/*  716:     */  }
/*  717:     */  
/*  720:     */  public K firstKey()
/*  721:     */  {
/*  722: 722 */    if (this.size == 0) throw new NoSuchElementException();
/*  723: 723 */    return this.key[this.first];
/*  724:     */  }
/*  725:     */  
/*  728:     */  public K lastKey()
/*  729:     */  {
/*  730: 730 */    if (this.size == 0) throw new NoSuchElementException();
/*  731: 731 */    return this.key[this.last]; }
/*  732:     */  
/*  733: 733 */  public Comparator<? super K> comparator() { return null; }
/*  734: 734 */  public Reference2BooleanSortedMap<K> tailMap(K from) { throw new UnsupportedOperationException(); }
/*  735: 735 */  public Reference2BooleanSortedMap<K> headMap(K to) { throw new UnsupportedOperationException(); }
/*  736: 736 */  public Reference2BooleanSortedMap<K> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*  737:     */  
/*  743:     */  private class MapIterator
/*  744:     */  {
/*  745: 745 */    int prev = -1;
/*  746:     */    
/*  747: 747 */    int next = -1;
/*  748:     */    
/*  749: 749 */    int curr = -1;
/*  750:     */    
/*  751: 751 */    int index = -1;
/*  752:     */    
/*  753: 753 */    private MapIterator() { this.next = Reference2BooleanLinkedOpenHashMap.this.first;
/*  754: 754 */      this.index = 0;
/*  755:     */    }
/*  756:     */    
/*  757: 757 */    private MapIterator() { if (Reference2BooleanLinkedOpenHashMap.this.key[Reference2BooleanLinkedOpenHashMap.this.last] == from) {
/*  758: 758 */        this.prev = Reference2BooleanLinkedOpenHashMap.this.last;
/*  759: 759 */        this.index = Reference2BooleanLinkedOpenHashMap.this.size;
/*  760:     */      }
/*  761:     */      else
/*  762:     */      {
/*  763: 763 */        int pos = (from == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(from))) & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  764:     */        
/*  765: 765 */        while (Reference2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  766: 766 */          if (Reference2BooleanLinkedOpenHashMap.this.key[pos] == from)
/*  767:     */          {
/*  768: 768 */            this.next = ((int)Reference2BooleanLinkedOpenHashMap.this.link[pos]);
/*  769: 769 */            this.prev = pos;
/*  770: 770 */            return;
/*  771:     */          }
/*  772: 772 */          pos = pos + 1 & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  773:     */        }
/*  774: 774 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  775:     */      } }
/*  776:     */    
/*  777: 777 */    public boolean hasNext() { return this.next != -1; }
/*  778: 778 */    public boolean hasPrevious() { return this.prev != -1; }
/*  779:     */    
/*  780: 780 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  781: 781 */      if (this.prev == -1) {
/*  782: 782 */        this.index = 0;
/*  783: 783 */        return;
/*  784:     */      }
/*  785: 785 */      if (this.next == -1) {
/*  786: 786 */        this.index = Reference2BooleanLinkedOpenHashMap.this.size;
/*  787: 787 */        return;
/*  788:     */      }
/*  789: 789 */      int pos = Reference2BooleanLinkedOpenHashMap.this.first;
/*  790: 790 */      this.index = 1;
/*  791: 791 */      while (pos != this.prev) {
/*  792: 792 */        pos = (int)Reference2BooleanLinkedOpenHashMap.this.link[pos];
/*  793: 793 */        this.index += 1;
/*  794:     */      }
/*  795:     */    }
/*  796:     */    
/*  797: 797 */    public int nextIndex() { ensureIndexKnown();
/*  798: 798 */      return this.index;
/*  799:     */    }
/*  800:     */    
/*  801: 801 */    public int previousIndex() { ensureIndexKnown();
/*  802: 802 */      return this.index - 1;
/*  803:     */    }
/*  804:     */    
/*  805: 805 */    public int nextEntry() { if (!hasNext()) return Reference2BooleanLinkedOpenHashMap.this.size();
/*  806: 806 */      this.curr = this.next;
/*  807: 807 */      this.next = ((int)Reference2BooleanLinkedOpenHashMap.this.link[this.curr]);
/*  808: 808 */      this.prev = this.curr;
/*  809: 809 */      if (this.index >= 0) this.index += 1;
/*  810: 810 */      return this.curr;
/*  811:     */    }
/*  812:     */    
/*  813: 813 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  814: 814 */      this.curr = this.prev;
/*  815: 815 */      this.prev = ((int)(Reference2BooleanLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  816: 816 */      this.next = this.curr;
/*  817: 817 */      if (this.index >= 0) this.index -= 1;
/*  818: 818 */      return this.curr;
/*  819:     */    }
/*  820:     */    
/*  821:     */    public void remove() {
/*  822: 822 */      ensureIndexKnown();
/*  823: 823 */      if (this.curr == -1) throw new IllegalStateException();
/*  824: 824 */      if (this.curr == this.prev)
/*  825:     */      {
/*  827: 827 */        this.index -= 1;
/*  828: 828 */        this.prev = ((int)(Reference2BooleanLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  829:     */      }
/*  830:     */      else {
/*  831: 831 */        this.next = ((int)Reference2BooleanLinkedOpenHashMap.this.link[this.curr]); }
/*  832: 832 */      Reference2BooleanLinkedOpenHashMap.this.size -= 1;
/*  833:     */      
/*  835: 835 */      if (this.prev == -1) { Reference2BooleanLinkedOpenHashMap.this.first = this.next;
/*  836:     */      } else
/*  837: 837 */        Reference2BooleanLinkedOpenHashMap.this.link[this.prev] ^= (Reference2BooleanLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  838: 838 */      if (this.next == -1) { Reference2BooleanLinkedOpenHashMap.this.last = this.prev;
/*  839:     */      } else
/*  840: 840 */        Reference2BooleanLinkedOpenHashMap.this.link[this.next] ^= (Reference2BooleanLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  841: 841 */      int pos = this.curr;
/*  842:     */      int last;
/*  843:     */      for (;;) {
/*  844: 844 */        pos = (last = pos) + 1 & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  845: 845 */        while (Reference2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  846: 846 */          int slot = (Reference2BooleanLinkedOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2BooleanLinkedOpenHashMap.this.key[pos]))) & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  847: 847 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  848: 848 */          pos = pos + 1 & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  849:     */        }
/*  850: 850 */        if (Reference2BooleanLinkedOpenHashMap.this.used[pos] == 0) break;
/*  851: 851 */        Reference2BooleanLinkedOpenHashMap.this.key[last] = Reference2BooleanLinkedOpenHashMap.this.key[pos];
/*  852: 852 */        Reference2BooleanLinkedOpenHashMap.this.value[last] = Reference2BooleanLinkedOpenHashMap.this.value[pos];
/*  853: 853 */        if (this.next == pos) this.next = last;
/*  854: 854 */        if (this.prev == pos) this.prev = last;
/*  855: 855 */        Reference2BooleanLinkedOpenHashMap.this.fixPointers(pos, last);
/*  856:     */      }
/*  857: 857 */      Reference2BooleanLinkedOpenHashMap.this.used[last] = false;
/*  858: 858 */      Reference2BooleanLinkedOpenHashMap.this.key[last] = null;
/*  859: 859 */      this.curr = -1;
/*  860:     */    }
/*  861:     */    
/*  862: 862 */    public int skip(int n) { int i = n;
/*  863: 863 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  864: 864 */      return n - i - 1;
/*  865:     */    }
/*  866:     */    
/*  867: 867 */    public int back(int n) { int i = n;
/*  868: 868 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  869: 869 */      return n - i - 1;
/*  870:     */    } }
/*  871:     */  
/*  872:     */  private class EntryIterator extends Reference2BooleanLinkedOpenHashMap<K>.MapIterator implements ObjectListIterator<Reference2BooleanMap.Entry<K>> { private Reference2BooleanLinkedOpenHashMap<K>.MapEntry entry;
/*  873:     */    
/*  874: 874 */    public EntryIterator() { super(null); }
/*  875:     */    
/*  876: 876 */    public EntryIterator() { super(from, null); }
/*  877:     */    
/*  878:     */    public Reference2BooleanLinkedOpenHashMap<K>.MapEntry next() {
/*  879: 879 */      return this.entry = new Reference2BooleanLinkedOpenHashMap.MapEntry(Reference2BooleanLinkedOpenHashMap.this, nextEntry());
/*  880:     */    }
/*  881:     */    
/*  882: 882 */    public Reference2BooleanLinkedOpenHashMap<K>.MapEntry previous() { return this.entry = new Reference2BooleanLinkedOpenHashMap.MapEntry(Reference2BooleanLinkedOpenHashMap.this, previousEntry()); }
/*  883:     */    
/*  884:     */    public void remove()
/*  885:     */    {
/*  886: 886 */      super.remove();
/*  887: 887 */      Reference2BooleanLinkedOpenHashMap.MapEntry.access$202(this.entry, -1); }
/*  888:     */    
/*  889: 889 */    public void set(Reference2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  890: 890 */    public void add(Reference2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  891:     */  }
/*  892:     */  
/*  893: 893 */  private class FastEntryIterator extends Reference2BooleanLinkedOpenHashMap<K>.MapIterator implements ObjectListIterator<Reference2BooleanMap.Entry<K>> { final AbstractReference2BooleanMap.BasicEntry<K> entry = new AbstractReference2BooleanMap.BasicEntry(null, false);
/*  894: 894 */    public FastEntryIterator() { super(null); }
/*  895:     */    
/*  896: 896 */    public FastEntryIterator() { super(from, null); }
/*  897:     */    
/*  898:     */    public AbstractReference2BooleanMap.BasicEntry<K> next() {
/*  899: 899 */      int e = nextEntry();
/*  900: 900 */      this.entry.key = Reference2BooleanLinkedOpenHashMap.this.key[e];
/*  901: 901 */      this.entry.value = Reference2BooleanLinkedOpenHashMap.this.value[e];
/*  902: 902 */      return this.entry;
/*  903:     */    }
/*  904:     */    
/*  905: 905 */    public AbstractReference2BooleanMap.BasicEntry<K> previous() { int e = previousEntry();
/*  906: 906 */      this.entry.key = Reference2BooleanLinkedOpenHashMap.this.key[e];
/*  907: 907 */      this.entry.value = Reference2BooleanLinkedOpenHashMap.this.value[e];
/*  908: 908 */      return this.entry; }
/*  909:     */    
/*  910: 910 */    public void set(Reference2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  911: 911 */    public void add(Reference2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); } }
/*  912:     */  
/*  913:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Reference2BooleanMap.Entry<K>> implements Reference2BooleanSortedMap.FastSortedEntrySet<K> { private MapEntrySet() {}
/*  914:     */    
/*  915: 915 */    public ObjectBidirectionalIterator<Reference2BooleanMap.Entry<K>> iterator() { return new Reference2BooleanLinkedOpenHashMap.EntryIterator(Reference2BooleanLinkedOpenHashMap.this); }
/*  916:     */    
/*  917: 917 */    public Comparator<? super Reference2BooleanMap.Entry<K>> comparator() { return null; }
/*  918: 918 */    public ObjectSortedSet<Reference2BooleanMap.Entry<K>> subSet(Reference2BooleanMap.Entry<K> fromElement, Reference2BooleanMap.Entry<K> toElement) { throw new UnsupportedOperationException(); }
/*  919: 919 */    public ObjectSortedSet<Reference2BooleanMap.Entry<K>> headSet(Reference2BooleanMap.Entry<K> toElement) { throw new UnsupportedOperationException(); }
/*  920: 920 */    public ObjectSortedSet<Reference2BooleanMap.Entry<K>> tailSet(Reference2BooleanMap.Entry<K> fromElement) { throw new UnsupportedOperationException(); }
/*  921:     */    
/*  922: 922 */    public Reference2BooleanMap.Entry<K> first() { if (Reference2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  923: 923 */      return new Reference2BooleanLinkedOpenHashMap.MapEntry(Reference2BooleanLinkedOpenHashMap.this, Reference2BooleanLinkedOpenHashMap.this.first);
/*  924:     */    }
/*  925:     */    
/*  926: 926 */    public Reference2BooleanMap.Entry<K> last() { if (Reference2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  927: 927 */      return new Reference2BooleanLinkedOpenHashMap.MapEntry(Reference2BooleanLinkedOpenHashMap.this, Reference2BooleanLinkedOpenHashMap.this.last);
/*  928:     */    }
/*  929:     */    
/*  930:     */    public boolean contains(Object o) {
/*  931: 931 */      if (!(o instanceof Map.Entry)) return false;
/*  932: 932 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  933: 933 */      K k = e.getKey();
/*  934:     */      
/*  935: 935 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  936:     */      
/*  937: 937 */      while (Reference2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  938: 938 */        if (Reference2BooleanLinkedOpenHashMap.this.key[pos] == k) return Reference2BooleanLinkedOpenHashMap.this.value[pos] == ((Boolean)e.getValue()).booleanValue();
/*  939: 939 */        pos = pos + 1 & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  940:     */      }
/*  941: 941 */      return false;
/*  942:     */    }
/*  943:     */    
/*  944:     */    public boolean remove(Object o) {
/*  945: 945 */      if (!(o instanceof Map.Entry)) return false;
/*  946: 946 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  947: 947 */      K k = e.getKey();
/*  948:     */      
/*  949: 949 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  950:     */      
/*  951: 951 */      while (Reference2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  952: 952 */        if (Reference2BooleanLinkedOpenHashMap.this.key[pos] == k) {
/*  953: 953 */          Reference2BooleanLinkedOpenHashMap.this.remove(e.getKey());
/*  954: 954 */          return true;
/*  955:     */        }
/*  956: 956 */        pos = pos + 1 & Reference2BooleanLinkedOpenHashMap.this.mask;
/*  957:     */      }
/*  958: 958 */      return false;
/*  959:     */    }
/*  960:     */    
/*  961: 961 */    public int size() { return Reference2BooleanLinkedOpenHashMap.this.size; }
/*  962:     */    
/*  963:     */    public void clear() {
/*  964: 964 */      Reference2BooleanLinkedOpenHashMap.this.clear();
/*  965:     */    }
/*  966:     */    
/*  967: 967 */    public ObjectBidirectionalIterator<Reference2BooleanMap.Entry<K>> iterator(Reference2BooleanMap.Entry<K> from) { return new Reference2BooleanLinkedOpenHashMap.EntryIterator(Reference2BooleanLinkedOpenHashMap.this, from.getKey()); }
/*  968:     */    
/*  969:     */    public ObjectBidirectionalIterator<Reference2BooleanMap.Entry<K>> fastIterator() {
/*  970: 970 */      return new Reference2BooleanLinkedOpenHashMap.FastEntryIterator(Reference2BooleanLinkedOpenHashMap.this);
/*  971:     */    }
/*  972:     */    
/*  973: 973 */    public ObjectBidirectionalIterator<Reference2BooleanMap.Entry<K>> fastIterator(Reference2BooleanMap.Entry<K> from) { return new Reference2BooleanLinkedOpenHashMap.FastEntryIterator(Reference2BooleanLinkedOpenHashMap.this, from.getKey()); }
/*  974:     */  }
/*  975:     */  
/*  976:     */  public Reference2BooleanSortedMap.FastSortedEntrySet<K> reference2BooleanEntrySet() {
/*  977: 977 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/*  978: 978 */    return this.entries;
/*  979:     */  }
/*  980:     */  
/*  983:     */  private final class KeyIterator
/*  984:     */    extends Reference2BooleanLinkedOpenHashMap<K>.MapIterator
/*  985:     */    implements ObjectListIterator<K>
/*  986:     */  {
/*  987: 987 */    public KeyIterator() { super(k, null); }
/*  988: 988 */    public K previous() { return Reference2BooleanLinkedOpenHashMap.this.key[previousEntry()]; }
/*  989: 989 */    public void set(K k) { throw new UnsupportedOperationException(); }
/*  990: 990 */    public void add(K k) { throw new UnsupportedOperationException(); }
/*  991: 991 */    public KeyIterator() { super(null); }
/*  992: 992 */    public K next() { return Reference2BooleanLinkedOpenHashMap.this.key[nextEntry()]; } }
/*  993:     */  
/*  994:     */  private final class KeySet extends AbstractReferenceSortedSet<K> { private KeySet() {}
/*  995:     */    
/*  996: 996 */    public ObjectListIterator<K> iterator(K from) { return new Reference2BooleanLinkedOpenHashMap.KeyIterator(Reference2BooleanLinkedOpenHashMap.this, from); }
/*  997:     */    
/*  998:     */    public ObjectListIterator<K> iterator() {
/*  999: 999 */      return new Reference2BooleanLinkedOpenHashMap.KeyIterator(Reference2BooleanLinkedOpenHashMap.this);
/* 1000:     */    }
/* 1001:     */    
/* 1002:1002 */    public int size() { return Reference2BooleanLinkedOpenHashMap.this.size; }
/* 1003:     */    
/* 1005:1005 */    public boolean contains(Object k) { return Reference2BooleanLinkedOpenHashMap.this.containsKey(k); }
/* 1006:     */    
/* 1007:     */    public boolean remove(Object k) {
/* 1008:1008 */      int oldSize = Reference2BooleanLinkedOpenHashMap.this.size;
/* 1009:1009 */      Reference2BooleanLinkedOpenHashMap.this.remove(k);
/* 1010:1010 */      return Reference2BooleanLinkedOpenHashMap.this.size != oldSize;
/* 1011:     */    }
/* 1012:     */    
/* 1013:1013 */    public void clear() { Reference2BooleanLinkedOpenHashMap.this.clear(); }
/* 1014:     */    
/* 1015:     */    public K first() {
/* 1016:1016 */      if (Reference2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1017:1017 */      return Reference2BooleanLinkedOpenHashMap.this.key[Reference2BooleanLinkedOpenHashMap.this.first];
/* 1018:     */    }
/* 1019:     */    
/* 1020:1020 */    public K last() { if (Reference2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1021:1021 */      return Reference2BooleanLinkedOpenHashMap.this.key[Reference2BooleanLinkedOpenHashMap.this.last]; }
/* 1022:     */    
/* 1023:1023 */    public Comparator<? super K> comparator() { return null; }
/* 1024:1024 */    public final ReferenceSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); }
/* 1025:1025 */    public final ReferenceSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); }
/* 1026:1026 */    public final ReferenceSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/* 1027:     */  }
/* 1028:     */  
/* 1029:1029 */  public ReferenceSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1030:1030 */    return this.keys;
/* 1031:     */  }
/* 1032:     */  
/* 1035:     */  private final class ValueIterator
/* 1036:     */    extends Reference2BooleanLinkedOpenHashMap.MapIterator
/* 1037:     */    implements BooleanListIterator
/* 1038:     */  {
/* 1039:1039 */    public boolean previousBoolean() { return Reference2BooleanLinkedOpenHashMap.this.value[previousEntry()]; }
/* 1040:1040 */    public Boolean previous() { return Boolean.valueOf(Reference2BooleanLinkedOpenHashMap.this.value[previousEntry()]); }
/* 1041:1041 */    public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1042:1042 */    public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1043:1043 */    public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1044:1044 */    public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1045:1045 */    public ValueIterator() { super(null); }
/* 1046:1046 */    public boolean nextBoolean() { return Reference2BooleanLinkedOpenHashMap.this.value[nextEntry()]; }
/* 1047:1047 */    public Boolean next() { return Boolean.valueOf(Reference2BooleanLinkedOpenHashMap.this.value[nextEntry()]); }
/* 1048:     */  }
/* 1049:     */  
/* 1050:1050 */  public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1051:     */        public BooleanIterator iterator() {
/* 1052:1052 */          return new Reference2BooleanLinkedOpenHashMap.ValueIterator(Reference2BooleanLinkedOpenHashMap.this);
/* 1053:     */        }
/* 1054:     */        
/* 1055:1055 */        public int size() { return Reference2BooleanLinkedOpenHashMap.this.size; }
/* 1056:     */        
/* 1057:     */        public boolean contains(boolean v) {
/* 1058:1058 */          return Reference2BooleanLinkedOpenHashMap.this.containsValue(v);
/* 1059:     */        }
/* 1060:     */        
/* 1061:1061 */        public void clear() { Reference2BooleanLinkedOpenHashMap.this.clear(); }
/* 1062:     */      };
/* 1063:     */    }
/* 1064:1064 */    return this.values;
/* 1065:     */  }
/* 1066:     */  
/* 1075:     */  @Deprecated
/* 1076:     */  public boolean rehash()
/* 1077:     */  {
/* 1078:1078 */    return true;
/* 1079:     */  }
/* 1080:     */  
/* 1091:     */  public boolean trim()
/* 1092:     */  {
/* 1093:1093 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1094:1094 */    if (l >= this.n) return true;
/* 1095:     */    try {
/* 1096:1096 */      rehash(l);
/* 1097:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1098:1098 */      return false; }
/* 1099:1099 */    return true;
/* 1100:     */  }
/* 1101:     */  
/* 1118:     */  public boolean trim(int n)
/* 1119:     */  {
/* 1120:1120 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1121:1121 */    if (this.n <= l) return true;
/* 1122:     */    try {
/* 1123:1123 */      rehash(l);
/* 1124:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1125:1125 */      return false; }
/* 1126:1126 */    return true;
/* 1127:     */  }
/* 1128:     */  
/* 1137:     */  protected void rehash(int newN)
/* 1138:     */  {
/* 1139:1139 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1140:     */    
/* 1141:1141 */    K[] key = this.key;
/* 1142:1142 */    boolean[] value = this.value;
/* 1143:1143 */    int newMask = newN - 1;
/* 1144:1144 */    K[] newKey = (Object[])new Object[newN];
/* 1145:1145 */    boolean[] newValue = new boolean[newN];
/* 1146:1146 */    boolean[] newUsed = new boolean[newN];
/* 1147:1147 */    long[] link = this.link;
/* 1148:1148 */    long[] newLink = new long[newN];
/* 1149:1149 */    this.first = -1;
/* 1150:1150 */    for (int j = this.size; j-- != 0;) {
/* 1151:1151 */      K k = key[i];
/* 1152:1152 */      int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 1153:1153 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1154:1154 */      newUsed[pos] = true;
/* 1155:1155 */      newKey[pos] = k;
/* 1156:1156 */      newValue[pos] = value[i];
/* 1157:1157 */      if (prev != -1) {
/* 1158:1158 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1159:1159 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1160:1160 */        newPrev = pos;
/* 1161:     */      }
/* 1162:     */      else {
/* 1163:1163 */        newPrev = this.first = pos;
/* 1164:     */        
/* 1165:1165 */        newLink[pos] = -1L;
/* 1166:     */      }
/* 1167:1167 */      int t = i;
/* 1168:1168 */      i = (int)link[i];
/* 1169:1169 */      prev = t;
/* 1170:     */    }
/* 1171:1171 */    this.n = newN;
/* 1172:1172 */    this.mask = newMask;
/* 1173:1173 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1174:1174 */    this.key = newKey;
/* 1175:1175 */    this.value = newValue;
/* 1176:1176 */    this.used = newUsed;
/* 1177:1177 */    this.link = newLink;
/* 1178:1178 */    this.last = newPrev;
/* 1179:1179 */    if (newPrev != -1)
/* 1180:     */    {
/* 1181:1181 */      newLink[newPrev] |= 4294967295L;
/* 1182:     */    }
/* 1183:     */  }
/* 1184:     */  
/* 1187:     */  public Reference2BooleanLinkedOpenHashMap<K> clone()
/* 1188:     */  {
/* 1189:     */    Reference2BooleanLinkedOpenHashMap<K> c;
/* 1190:     */    
/* 1192:     */    try
/* 1193:     */    {
/* 1194:1194 */      c = (Reference2BooleanLinkedOpenHashMap)super.clone();
/* 1195:     */    }
/* 1196:     */    catch (CloneNotSupportedException cantHappen) {
/* 1197:1197 */      throw new InternalError();
/* 1198:     */    }
/* 1199:1199 */    c.keys = null;
/* 1200:1200 */    c.values = null;
/* 1201:1201 */    c.entries = null;
/* 1202:1202 */    c.key = ((Object[])this.key.clone());
/* 1203:1203 */    c.value = ((boolean[])this.value.clone());
/* 1204:1204 */    c.used = ((boolean[])this.used.clone());
/* 1205:1205 */    c.link = ((long[])this.link.clone());
/* 1206:1206 */    return c;
/* 1207:     */  }
/* 1208:     */  
/* 1216:     */  public int hashCode()
/* 1217:     */  {
/* 1218:1218 */    int h = 0;
/* 1219:1219 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1220:1220 */      while (this.used[i] == 0) i++;
/* 1221:1221 */      if (this != this.key[i])
/* 1222:1222 */        t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 1223:1223 */      t ^= (this.value[i] != 0 ? 1231 : 1237);
/* 1224:1224 */      h += t;
/* 1225:1225 */      i++;
/* 1226:     */    }
/* 1227:1227 */    return h;
/* 1228:     */  }
/* 1229:     */  
/* 1230:1230 */  private void writeObject(ObjectOutputStream s) throws IOException { K[] key = this.key;
/* 1231:1231 */    boolean[] value = this.value;
/* 1232:1232 */    Reference2BooleanLinkedOpenHashMap<K>.MapIterator i = new MapIterator(null);
/* 1233:1233 */    s.defaultWriteObject();
/* 1234:1234 */    for (int j = this.size; j-- != 0;) {
/* 1235:1235 */      int e = i.nextEntry();
/* 1236:1236 */      s.writeObject(key[e]);
/* 1237:1237 */      s.writeBoolean(value[e]);
/* 1238:     */    }
/* 1239:     */  }
/* 1240:     */  
/* 1241:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1242:1242 */    s.defaultReadObject();
/* 1243:1243 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1244:1244 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1245:1245 */    this.mask = (this.n - 1);
/* 1246:1246 */    K[] key = this.key = (Object[])new Object[this.n];
/* 1247:1247 */    boolean[] value = this.value = new boolean[this.n];
/* 1248:1248 */    boolean[] used = this.used = new boolean[this.n];
/* 1249:1249 */    long[] link = this.link = new long[this.n];
/* 1250:1250 */    int prev = -1;
/* 1251:1251 */    this.first = (this.last = -1);
/* 1252:     */    
/* 1254:1254 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1255:1255 */      K k = s.readObject();
/* 1256:1256 */      boolean v = s.readBoolean();
/* 1257:1257 */      pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 1258:1258 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1259:1259 */      used[pos] = true;
/* 1260:1260 */      key[pos] = k;
/* 1261:1261 */      value[pos] = v;
/* 1262:1262 */      if (this.first != -1) {
/* 1263:1263 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1264:1264 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1265:1265 */        prev = pos;
/* 1266:     */      }
/* 1267:     */      else {
/* 1268:1268 */        prev = this.first = pos;
/* 1269:     */        
/* 1270:1270 */        link[pos] |= -4294967296L;
/* 1271:     */      }
/* 1272:     */    }
/* 1273:1273 */    this.last = prev;
/* 1274:1274 */    if (prev != -1)
/* 1275:     */    {
/* 1276:1276 */      link[prev] |= 4294967295L;
/* 1277:     */    }
/* 1278:     */  }
/* 1279:     */  
/* 1280:     */  private void checkTable() {}
/* 1281:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2BooleanLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */