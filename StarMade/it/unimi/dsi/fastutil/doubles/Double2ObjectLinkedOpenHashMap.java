/*    1:     */package it.unimi.dsi.fastutil.doubles;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.HashCommon;
/*    5:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    6:     */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*    7:     */import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*    8:     */import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*    9:     */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*   10:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   11:     */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*   12:     */import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*   13:     */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   14:     */import java.io.IOException;
/*   15:     */import java.io.ObjectInputStream;
/*   16:     */import java.io.ObjectOutputStream;
/*   17:     */import java.io.Serializable;
/*   18:     */import java.util.Comparator;
/*   19:     */import java.util.Map;
/*   20:     */import java.util.Map.Entry;
/*   21:     */import java.util.NoSuchElementException;
/*   22:     */
/*  101:     */public class Double2ObjectLinkedOpenHashMap<V>
/*  102:     */  extends AbstractDouble2ObjectSortedMap<V>
/*  103:     */  implements Serializable, Cloneable, Hash
/*  104:     */{
/*  105:     */  public static final long serialVersionUID = 0L;
/*  106:     */  private static final boolean ASSERTS = false;
/*  107:     */  protected transient double[] key;
/*  108:     */  protected transient V[] value;
/*  109:     */  protected transient boolean[] used;
/*  110:     */  protected final float f;
/*  111:     */  protected transient int n;
/*  112:     */  protected transient int maxFill;
/*  113:     */  protected transient int mask;
/*  114:     */  protected int size;
/*  115:     */  protected volatile transient Double2ObjectSortedMap.FastSortedEntrySet<V> entries;
/*  116:     */  protected volatile transient DoubleSortedSet keys;
/*  117:     */  protected volatile transient ObjectCollection<V> values;
/*  118: 118 */  protected transient int first = -1;
/*  119:     */  
/*  120: 120 */  protected transient int last = -1;
/*  121:     */  
/*  128:     */  protected transient long[] link;
/*  129:     */  
/*  137:     */  public Double2ObjectLinkedOpenHashMap(int expected, float f)
/*  138:     */  {
/*  139: 139 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  140: 140 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  141: 141 */    this.f = f;
/*  142: 142 */    this.n = HashCommon.arraySize(expected, f);
/*  143: 143 */    this.mask = (this.n - 1);
/*  144: 144 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  145: 145 */    this.key = new double[this.n];
/*  146: 146 */    this.value = ((Object[])new Object[this.n]);
/*  147: 147 */    this.used = new boolean[this.n];
/*  148: 148 */    this.link = new long[this.n];
/*  149:     */  }
/*  150:     */  
/*  153:     */  public Double2ObjectLinkedOpenHashMap(int expected)
/*  154:     */  {
/*  155: 155 */    this(expected, 0.75F);
/*  156:     */  }
/*  157:     */  
/*  159:     */  public Double2ObjectLinkedOpenHashMap()
/*  160:     */  {
/*  161: 161 */    this(16, 0.75F);
/*  162:     */  }
/*  163:     */  
/*  167:     */  public Double2ObjectLinkedOpenHashMap(Map<? extends Double, ? extends V> m, float f)
/*  168:     */  {
/*  169: 169 */    this(m.size(), f);
/*  170: 170 */    putAll(m);
/*  171:     */  }
/*  172:     */  
/*  175:     */  public Double2ObjectLinkedOpenHashMap(Map<? extends Double, ? extends V> m)
/*  176:     */  {
/*  177: 177 */    this(m, 0.75F);
/*  178:     */  }
/*  179:     */  
/*  183:     */  public Double2ObjectLinkedOpenHashMap(Double2ObjectMap<V> m, float f)
/*  184:     */  {
/*  185: 185 */    this(m.size(), f);
/*  186: 186 */    putAll(m);
/*  187:     */  }
/*  188:     */  
/*  191:     */  public Double2ObjectLinkedOpenHashMap(Double2ObjectMap<V> m)
/*  192:     */  {
/*  193: 193 */    this(m, 0.75F);
/*  194:     */  }
/*  195:     */  
/*  201:     */  public Double2ObjectLinkedOpenHashMap(double[] k, V[] v, float f)
/*  202:     */  {
/*  203: 203 */    this(k.length, f);
/*  204: 204 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  205: 205 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  206:     */    }
/*  207:     */  }
/*  208:     */  
/*  212:     */  public Double2ObjectLinkedOpenHashMap(double[] k, V[] v)
/*  213:     */  {
/*  214: 214 */    this(k, v, 0.75F);
/*  215:     */  }
/*  216:     */  
/*  220:     */  public V put(double k, V v)
/*  221:     */  {
/*  222: 222 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  223:     */    
/*  224: 224 */    while (this.used[pos] != 0) {
/*  225: 225 */      if (this.key[pos] == k) {
/*  226: 226 */        V oldValue = this.value[pos];
/*  227: 227 */        this.value[pos] = v;
/*  228: 228 */        return oldValue;
/*  229:     */      }
/*  230: 230 */      pos = pos + 1 & this.mask;
/*  231:     */    }
/*  232: 232 */    this.used[pos] = true;
/*  233: 233 */    this.key[pos] = k;
/*  234: 234 */    this.value[pos] = v;
/*  235: 235 */    if (this.size == 0) {
/*  236: 236 */      this.first = (this.last = pos);
/*  237:     */      
/*  238: 238 */      this.link[pos] = -1L;
/*  239:     */    }
/*  240:     */    else {
/*  241: 241 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  242: 242 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  243: 243 */      this.last = pos;
/*  244:     */    }
/*  245: 245 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  246:     */    }
/*  247: 247 */    return this.defRetValue;
/*  248:     */  }
/*  249:     */  
/*  250: 250 */  public V put(Double ok, V ov) { V v = ov;
/*  251: 251 */    double k = ok.doubleValue();
/*  252:     */    
/*  253: 253 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  254:     */    
/*  255: 255 */    while (this.used[pos] != 0) {
/*  256: 256 */      if (this.key[pos] == k) {
/*  257: 257 */        V oldValue = this.value[pos];
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
/*  283:     */  protected final int shiftKeys(int pos)
/*  284:     */  {
/*  285:     */    int last;
/*  286:     */    
/*  288:     */    for (;;)
/*  289:     */    {
/*  290: 290 */      pos = (last = pos) + 1 & this.mask;
/*  291: 291 */      while (this.used[pos] != 0) {
/*  292: 292 */        int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(this.key[pos])) & this.mask;
/*  293: 293 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  294: 294 */        pos = pos + 1 & this.mask;
/*  295:     */      }
/*  296: 296 */      if (this.used[pos] == 0) break;
/*  297: 297 */      this.key[last] = this.key[pos];
/*  298: 298 */      this.value[last] = this.value[pos];
/*  299: 299 */      fixPointers(pos, last);
/*  300:     */    }
/*  301: 301 */    this.used[last] = false;
/*  302: 302 */    this.value[last] = null;
/*  303: 303 */    return last;
/*  304:     */  }
/*  305:     */  
/*  306:     */  public V remove(double k)
/*  307:     */  {
/*  308: 308 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  309:     */    
/*  310: 310 */    while (this.used[pos] != 0) {
/*  311: 311 */      if (this.key[pos] == k) {
/*  312: 312 */        this.size -= 1;
/*  313: 313 */        fixPointers(pos);
/*  314: 314 */        V v = this.value[pos];
/*  315: 315 */        shiftKeys(pos);
/*  316: 316 */        return v;
/*  317:     */      }
/*  318: 318 */      pos = pos + 1 & this.mask;
/*  319:     */    }
/*  320: 320 */    return this.defRetValue;
/*  321:     */  }
/*  322:     */  
/*  323:     */  public V remove(Object ok) {
/*  324: 324 */    double k = ((Double)ok).doubleValue();
/*  325:     */    
/*  326: 326 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  327:     */    
/*  328: 328 */    while (this.used[pos] != 0) {
/*  329: 329 */      if (this.key[pos] == k) {
/*  330: 330 */        this.size -= 1;
/*  331: 331 */        fixPointers(pos);
/*  332: 332 */        V v = this.value[pos];
/*  333: 333 */        shiftKeys(pos);
/*  334: 334 */        return v;
/*  335:     */      }
/*  336: 336 */      pos = pos + 1 & this.mask;
/*  337:     */    }
/*  338: 338 */    return this.defRetValue;
/*  339:     */  }
/*  340:     */  
/*  343:     */  public V removeFirst()
/*  344:     */  {
/*  345: 345 */    if (this.size == 0) throw new NoSuchElementException();
/*  346: 346 */    this.size -= 1;
/*  347: 347 */    int pos = this.first;
/*  348:     */    
/*  349: 349 */    this.first = ((int)this.link[pos]);
/*  350: 350 */    if (0 <= this.first)
/*  351:     */    {
/*  352: 352 */      this.link[this.first] |= -4294967296L;
/*  353:     */    }
/*  354: 354 */    V v = this.value[pos];
/*  355: 355 */    shiftKeys(pos);
/*  356: 356 */    return v;
/*  357:     */  }
/*  358:     */  
/*  361:     */  public V removeLast()
/*  362:     */  {
/*  363: 363 */    if (this.size == 0) throw new NoSuchElementException();
/*  364: 364 */    this.size -= 1;
/*  365: 365 */    int pos = this.last;
/*  366:     */    
/*  367: 367 */    this.last = ((int)(this.link[pos] >>> 32));
/*  368: 368 */    if (0 <= this.last)
/*  369:     */    {
/*  370: 370 */      this.link[this.last] |= 4294967295L;
/*  371:     */    }
/*  372: 372 */    V v = this.value[pos];
/*  373: 373 */    shiftKeys(pos);
/*  374: 374 */    return v;
/*  375:     */  }
/*  376:     */  
/*  377: 377 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  378: 378 */    if (this.last == i) {
/*  379: 379 */      this.last = ((int)(this.link[i] >>> 32));
/*  380:     */      
/*  381: 381 */      this.link[this.last] |= 4294967295L;
/*  382:     */    }
/*  383:     */    else {
/*  384: 384 */      long linki = this.link[i];
/*  385: 385 */      int prev = (int)(linki >>> 32);
/*  386: 386 */      int next = (int)linki;
/*  387: 387 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  388: 388 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  389:     */    }
/*  390: 390 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  391: 391 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  392: 392 */    this.first = i;
/*  393:     */  }
/*  394:     */  
/*  395: 395 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  396: 396 */    if (this.first == i) {
/*  397: 397 */      this.first = ((int)this.link[i]);
/*  398:     */      
/*  399: 399 */      this.link[this.first] |= -4294967296L;
/*  400:     */    }
/*  401:     */    else {
/*  402: 402 */      long linki = this.link[i];
/*  403: 403 */      int prev = (int)(linki >>> 32);
/*  404: 404 */      int next = (int)linki;
/*  405: 405 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  406: 406 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  407:     */    }
/*  408: 408 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  409: 409 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  410: 410 */    this.last = i;
/*  411:     */  }
/*  412:     */  
/*  416:     */  public V getAndMoveToFirst(double k)
/*  417:     */  {
/*  418: 418 */    double[] key = this.key;
/*  419: 419 */    boolean[] used = this.used;
/*  420: 420 */    int mask = this.mask;
/*  421:     */    
/*  422: 422 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*  423:     */    
/*  424: 424 */    while (used[pos] != 0) {
/*  425: 425 */      if (k == key[pos]) {
/*  426: 426 */        moveIndexToFirst(pos);
/*  427: 427 */        return this.value[pos];
/*  428:     */      }
/*  429: 429 */      pos = pos + 1 & mask;
/*  430:     */    }
/*  431: 431 */    return this.defRetValue;
/*  432:     */  }
/*  433:     */  
/*  437:     */  public V getAndMoveToLast(double k)
/*  438:     */  {
/*  439: 439 */    double[] key = this.key;
/*  440: 440 */    boolean[] used = this.used;
/*  441: 441 */    int mask = this.mask;
/*  442:     */    
/*  443: 443 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*  444:     */    
/*  445: 445 */    while (used[pos] != 0) {
/*  446: 446 */      if (k == key[pos]) {
/*  447: 447 */        moveIndexToLast(pos);
/*  448: 448 */        return this.value[pos];
/*  449:     */      }
/*  450: 450 */      pos = pos + 1 & mask;
/*  451:     */    }
/*  452: 452 */    return this.defRetValue;
/*  453:     */  }
/*  454:     */  
/*  459:     */  public V putAndMoveToFirst(double k, V v)
/*  460:     */  {
/*  461: 461 */    double[] key = this.key;
/*  462: 462 */    boolean[] used = this.used;
/*  463: 463 */    int mask = this.mask;
/*  464:     */    
/*  465: 465 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*  466:     */    
/*  467: 467 */    while (used[pos] != 0) {
/*  468: 468 */      if (k == key[pos]) {
/*  469: 469 */        V oldValue = this.value[pos];
/*  470: 470 */        this.value[pos] = v;
/*  471: 471 */        moveIndexToFirst(pos);
/*  472: 472 */        return oldValue;
/*  473:     */      }
/*  474: 474 */      pos = pos + 1 & mask;
/*  475:     */    }
/*  476: 476 */    used[pos] = true;
/*  477: 477 */    key[pos] = k;
/*  478: 478 */    this.value[pos] = v;
/*  479: 479 */    if (this.size == 0) {
/*  480: 480 */      this.first = (this.last = pos);
/*  481:     */      
/*  482: 482 */      this.link[pos] = -1L;
/*  483:     */    }
/*  484:     */    else {
/*  485: 485 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  486: 486 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  487: 487 */      this.first = pos;
/*  488:     */    }
/*  489: 489 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  490:     */    }
/*  491: 491 */    return this.defRetValue;
/*  492:     */  }
/*  493:     */  
/*  498:     */  public V putAndMoveToLast(double k, V v)
/*  499:     */  {
/*  500: 500 */    double[] key = this.key;
/*  501: 501 */    boolean[] used = this.used;
/*  502: 502 */    int mask = this.mask;
/*  503:     */    
/*  504: 504 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*  505:     */    
/*  506: 506 */    while (used[pos] != 0) {
/*  507: 507 */      if (k == key[pos]) {
/*  508: 508 */        V oldValue = this.value[pos];
/*  509: 509 */        this.value[pos] = v;
/*  510: 510 */        moveIndexToLast(pos);
/*  511: 511 */        return oldValue;
/*  512:     */      }
/*  513: 513 */      pos = pos + 1 & mask;
/*  514:     */    }
/*  515: 515 */    used[pos] = true;
/*  516: 516 */    key[pos] = k;
/*  517: 517 */    this.value[pos] = v;
/*  518: 518 */    if (this.size == 0) {
/*  519: 519 */      this.first = (this.last = pos);
/*  520:     */      
/*  521: 521 */      this.link[pos] = -1L;
/*  522:     */    }
/*  523:     */    else {
/*  524: 524 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  525: 525 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  526: 526 */      this.last = pos;
/*  527:     */    }
/*  528: 528 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  529:     */    }
/*  530: 530 */    return this.defRetValue;
/*  531:     */  }
/*  532:     */  
/*  533: 533 */  public V get(Double ok) { double k = ok.doubleValue();
/*  534:     */    
/*  535: 535 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  536:     */    
/*  537: 537 */    while (this.used[pos] != 0) {
/*  538: 538 */      if (this.key[pos] == k) return this.value[pos];
/*  539: 539 */      pos = pos + 1 & this.mask;
/*  540:     */    }
/*  541: 541 */    return this.defRetValue;
/*  542:     */  }
/*  543:     */  
/*  544:     */  public V get(double k)
/*  545:     */  {
/*  546: 546 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  547:     */    
/*  548: 548 */    while (this.used[pos] != 0) {
/*  549: 549 */      if (this.key[pos] == k) return this.value[pos];
/*  550: 550 */      pos = pos + 1 & this.mask;
/*  551:     */    }
/*  552: 552 */    return this.defRetValue;
/*  553:     */  }
/*  554:     */  
/*  555:     */  public boolean containsKey(double k)
/*  556:     */  {
/*  557: 557 */    int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*  558:     */    
/*  559: 559 */    while (this.used[pos] != 0) {
/*  560: 560 */      if (this.key[pos] == k) return true;
/*  561: 561 */      pos = pos + 1 & this.mask;
/*  562:     */    }
/*  563: 563 */    return false;
/*  564:     */  }
/*  565:     */  
/*  566: 566 */  public boolean containsValue(Object v) { V[] value = this.value;
/*  567: 567 */    boolean[] used = this.used;
/*  568: 568 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v))) break label16;
/*  569: 569 */    return false;
/*  570:     */  }
/*  571:     */  
/*  576:     */  public void clear()
/*  577:     */  {
/*  578: 578 */    if (this.size == 0) return;
/*  579: 579 */    this.size = 0;
/*  580: 580 */    BooleanArrays.fill(this.used, false);
/*  581:     */    
/*  582: 582 */    ObjectArrays.fill(this.value, null);
/*  583: 583 */    this.first = (this.last = -1);
/*  584:     */  }
/*  585:     */  
/*  586: 586 */  public int size() { return this.size; }
/*  587:     */  
/*  588:     */  public boolean isEmpty() {
/*  589: 589 */    return this.size == 0;
/*  590:     */  }
/*  591:     */  
/*  596:     */  @Deprecated
/*  597:     */  public void growthFactor(int growthFactor) {}
/*  598:     */  
/*  603:     */  @Deprecated
/*  604:     */  public int growthFactor()
/*  605:     */  {
/*  606: 606 */    return 16;
/*  607:     */  }
/*  608:     */  
/*  609:     */  private final class MapEntry
/*  610:     */    implements Double2ObjectMap.Entry<V>, Map.Entry<Double, V>
/*  611:     */  {
/*  612:     */    private int index;
/*  613:     */    
/*  614:     */    MapEntry(int index)
/*  615:     */    {
/*  616: 616 */      this.index = index;
/*  617:     */    }
/*  618:     */    
/*  619: 619 */    public Double getKey() { return Double.valueOf(Double2ObjectLinkedOpenHashMap.this.key[this.index]); }
/*  620:     */    
/*  621:     */    public double getDoubleKey() {
/*  622: 622 */      return Double2ObjectLinkedOpenHashMap.this.key[this.index];
/*  623:     */    }
/*  624:     */    
/*  625: 625 */    public V getValue() { return Double2ObjectLinkedOpenHashMap.this.value[this.index]; }
/*  626:     */    
/*  627:     */    public V setValue(V v) {
/*  628: 628 */      V oldValue = Double2ObjectLinkedOpenHashMap.this.value[this.index];
/*  629: 629 */      Double2ObjectLinkedOpenHashMap.this.value[this.index] = v;
/*  630: 630 */      return oldValue;
/*  631:     */    }
/*  632:     */    
/*  633:     */    public boolean equals(Object o) {
/*  634: 634 */      if (!(o instanceof Map.Entry)) return false;
/*  635: 635 */      Map.Entry<Double, V> e = (Map.Entry)o;
/*  636: 636 */      return (Double2ObjectLinkedOpenHashMap.this.key[this.index] == ((Double)e.getKey()).doubleValue()) && (Double2ObjectLinkedOpenHashMap.this.value[this.index] == null ? e.getValue() == null : Double2ObjectLinkedOpenHashMap.this.value[this.index].equals(e.getValue()));
/*  637:     */    }
/*  638:     */    
/*  639: 639 */    public int hashCode() { return HashCommon.double2int(Double2ObjectLinkedOpenHashMap.this.key[this.index]) ^ (Double2ObjectLinkedOpenHashMap.this.value[this.index] == null ? 0 : Double2ObjectLinkedOpenHashMap.this.value[this.index].hashCode()); }
/*  640:     */    
/*  641:     */    public String toString() {
/*  642: 642 */      return Double2ObjectLinkedOpenHashMap.this.key[this.index] + "=>" + Double2ObjectLinkedOpenHashMap.this.value[this.index];
/*  643:     */    }
/*  644:     */  }
/*  645:     */  
/*  651:     */  protected void fixPointers(int i)
/*  652:     */  {
/*  653: 653 */    if (this.size == 0) {
/*  654: 654 */      this.first = (this.last = -1);
/*  655: 655 */      return;
/*  656:     */    }
/*  657: 657 */    if (this.first == i) {
/*  658: 658 */      this.first = ((int)this.link[i]);
/*  659: 659 */      if (0 <= this.first)
/*  660:     */      {
/*  661: 661 */        this.link[this.first] |= -4294967296L;
/*  662:     */      }
/*  663: 663 */      return;
/*  664:     */    }
/*  665: 665 */    if (this.last == i) {
/*  666: 666 */      this.last = ((int)(this.link[i] >>> 32));
/*  667: 667 */      if (0 <= this.last)
/*  668:     */      {
/*  669: 669 */        this.link[this.last] |= 4294967295L;
/*  670:     */      }
/*  671: 671 */      return;
/*  672:     */    }
/*  673: 673 */    long linki = this.link[i];
/*  674: 674 */    int prev = (int)(linki >>> 32);
/*  675: 675 */    int next = (int)linki;
/*  676: 676 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  677: 677 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  678:     */  }
/*  679:     */  
/*  686:     */  protected void fixPointers(int s, int d)
/*  687:     */  {
/*  688: 688 */    if (this.size == 1) {
/*  689: 689 */      this.first = (this.last = d);
/*  690:     */      
/*  691: 691 */      this.link[d] = -1L;
/*  692: 692 */      return;
/*  693:     */    }
/*  694: 694 */    if (this.first == s) {
/*  695: 695 */      this.first = d;
/*  696: 696 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  697: 697 */      this.link[d] = this.link[s];
/*  698: 698 */      return;
/*  699:     */    }
/*  700: 700 */    if (this.last == s) {
/*  701: 701 */      this.last = d;
/*  702: 702 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  703: 703 */      this.link[d] = this.link[s];
/*  704: 704 */      return;
/*  705:     */    }
/*  706: 706 */    long links = this.link[s];
/*  707: 707 */    int prev = (int)(links >>> 32);
/*  708: 708 */    int next = (int)links;
/*  709: 709 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  710: 710 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  711: 711 */    this.link[d] = links;
/*  712:     */  }
/*  713:     */  
/*  716:     */  public double firstDoubleKey()
/*  717:     */  {
/*  718: 718 */    if (this.size == 0) throw new NoSuchElementException();
/*  719: 719 */    return this.key[this.first];
/*  720:     */  }
/*  721:     */  
/*  724:     */  public double lastDoubleKey()
/*  725:     */  {
/*  726: 726 */    if (this.size == 0) throw new NoSuchElementException();
/*  727: 727 */    return this.key[this.last]; }
/*  728:     */  
/*  729: 729 */  public DoubleComparator comparator() { return null; }
/*  730: 730 */  public Double2ObjectSortedMap<V> tailMap(double from) { throw new UnsupportedOperationException(); }
/*  731: 731 */  public Double2ObjectSortedMap<V> headMap(double to) { throw new UnsupportedOperationException(); }
/*  732: 732 */  public Double2ObjectSortedMap<V> subMap(double from, double to) { throw new UnsupportedOperationException(); }
/*  733:     */  
/*  739:     */  private class MapIterator
/*  740:     */  {
/*  741: 741 */    int prev = -1;
/*  742:     */    
/*  743: 743 */    int next = -1;
/*  744:     */    
/*  745: 745 */    int curr = -1;
/*  746:     */    
/*  747: 747 */    int index = -1;
/*  748:     */    
/*  749: 749 */    private MapIterator() { this.next = Double2ObjectLinkedOpenHashMap.this.first;
/*  750: 750 */      this.index = 0;
/*  751:     */    }
/*  752:     */    
/*  753: 753 */    private MapIterator(double from) { if (Double2ObjectLinkedOpenHashMap.this.key[Double2ObjectLinkedOpenHashMap.this.last] == from) {
/*  754: 754 */        this.prev = Double2ObjectLinkedOpenHashMap.this.last;
/*  755: 755 */        this.index = Double2ObjectLinkedOpenHashMap.this.size;
/*  756:     */      }
/*  757:     */      else
/*  758:     */      {
/*  759: 759 */        int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(from)) & Double2ObjectLinkedOpenHashMap.this.mask;
/*  760:     */        
/*  761: 761 */        while (Double2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  762: 762 */          if (Double2ObjectLinkedOpenHashMap.this.key[pos] == from)
/*  763:     */          {
/*  764: 764 */            this.next = ((int)Double2ObjectLinkedOpenHashMap.this.link[pos]);
/*  765: 765 */            this.prev = pos;
/*  766: 766 */            return;
/*  767:     */          }
/*  768: 768 */          pos = pos + 1 & Double2ObjectLinkedOpenHashMap.this.mask;
/*  769:     */        }
/*  770: 770 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  771:     */      } }
/*  772:     */    
/*  773: 773 */    public boolean hasNext() { return this.next != -1; }
/*  774: 774 */    public boolean hasPrevious() { return this.prev != -1; }
/*  775:     */    
/*  776: 776 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  777: 777 */      if (this.prev == -1) {
/*  778: 778 */        this.index = 0;
/*  779: 779 */        return;
/*  780:     */      }
/*  781: 781 */      if (this.next == -1) {
/*  782: 782 */        this.index = Double2ObjectLinkedOpenHashMap.this.size;
/*  783: 783 */        return;
/*  784:     */      }
/*  785: 785 */      int pos = Double2ObjectLinkedOpenHashMap.this.first;
/*  786: 786 */      this.index = 1;
/*  787: 787 */      while (pos != this.prev) {
/*  788: 788 */        pos = (int)Double2ObjectLinkedOpenHashMap.this.link[pos];
/*  789: 789 */        this.index += 1;
/*  790:     */      }
/*  791:     */    }
/*  792:     */    
/*  793: 793 */    public int nextIndex() { ensureIndexKnown();
/*  794: 794 */      return this.index;
/*  795:     */    }
/*  796:     */    
/*  797: 797 */    public int previousIndex() { ensureIndexKnown();
/*  798: 798 */      return this.index - 1;
/*  799:     */    }
/*  800:     */    
/*  801: 801 */    public int nextEntry() { if (!hasNext()) return Double2ObjectLinkedOpenHashMap.this.size();
/*  802: 802 */      this.curr = this.next;
/*  803: 803 */      this.next = ((int)Double2ObjectLinkedOpenHashMap.this.link[this.curr]);
/*  804: 804 */      this.prev = this.curr;
/*  805: 805 */      if (this.index >= 0) this.index += 1;
/*  806: 806 */      return this.curr;
/*  807:     */    }
/*  808:     */    
/*  809: 809 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  810: 810 */      this.curr = this.prev;
/*  811: 811 */      this.prev = ((int)(Double2ObjectLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  812: 812 */      this.next = this.curr;
/*  813: 813 */      if (this.index >= 0) this.index -= 1;
/*  814: 814 */      return this.curr;
/*  815:     */    }
/*  816:     */    
/*  817:     */    public void remove() {
/*  818: 818 */      ensureIndexKnown();
/*  819: 819 */      if (this.curr == -1) throw new IllegalStateException();
/*  820: 820 */      if (this.curr == this.prev)
/*  821:     */      {
/*  823: 823 */        this.index -= 1;
/*  824: 824 */        this.prev = ((int)(Double2ObjectLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  825:     */      }
/*  826:     */      else {
/*  827: 827 */        this.next = ((int)Double2ObjectLinkedOpenHashMap.this.link[this.curr]); }
/*  828: 828 */      Double2ObjectLinkedOpenHashMap.this.size -= 1;
/*  829:     */      
/*  831: 831 */      if (this.prev == -1) { Double2ObjectLinkedOpenHashMap.this.first = this.next;
/*  832:     */      } else
/*  833: 833 */        Double2ObjectLinkedOpenHashMap.this.link[this.prev] ^= (Double2ObjectLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  834: 834 */      if (this.next == -1) { Double2ObjectLinkedOpenHashMap.this.last = this.prev;
/*  835:     */      } else
/*  836: 836 */        Double2ObjectLinkedOpenHashMap.this.link[this.next] ^= (Double2ObjectLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  837: 837 */      int pos = this.curr;
/*  838:     */      int last;
/*  839:     */      for (;;) {
/*  840: 840 */        pos = (last = pos) + 1 & Double2ObjectLinkedOpenHashMap.this.mask;
/*  841: 841 */        while (Double2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  842: 842 */          int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(Double2ObjectLinkedOpenHashMap.this.key[pos])) & Double2ObjectLinkedOpenHashMap.this.mask;
/*  843: 843 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  844: 844 */          pos = pos + 1 & Double2ObjectLinkedOpenHashMap.this.mask;
/*  845:     */        }
/*  846: 846 */        if (Double2ObjectLinkedOpenHashMap.this.used[pos] == 0) break;
/*  847: 847 */        Double2ObjectLinkedOpenHashMap.this.key[last] = Double2ObjectLinkedOpenHashMap.this.key[pos];
/*  848: 848 */        Double2ObjectLinkedOpenHashMap.this.value[last] = Double2ObjectLinkedOpenHashMap.this.value[pos];
/*  849: 849 */        if (this.next == pos) this.next = last;
/*  850: 850 */        if (this.prev == pos) this.prev = last;
/*  851: 851 */        Double2ObjectLinkedOpenHashMap.this.fixPointers(pos, last);
/*  852:     */      }
/*  853: 853 */      Double2ObjectLinkedOpenHashMap.this.used[last] = false;
/*  854: 854 */      Double2ObjectLinkedOpenHashMap.this.value[last] = null;
/*  855: 855 */      this.curr = -1;
/*  856:     */    }
/*  857:     */    
/*  858: 858 */    public int skip(int n) { int i = n;
/*  859: 859 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  860: 860 */      return n - i - 1;
/*  861:     */    }
/*  862:     */    
/*  863: 863 */    public int back(int n) { int i = n;
/*  864: 864 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  865: 865 */      return n - i - 1;
/*  866:     */    } }
/*  867:     */  
/*  868:     */  private class EntryIterator extends Double2ObjectLinkedOpenHashMap<V>.MapIterator implements ObjectListIterator<Double2ObjectMap.Entry<V>> { private Double2ObjectLinkedOpenHashMap<V>.MapEntry entry;
/*  869:     */    
/*  870: 870 */    public EntryIterator() { super(null); }
/*  871:     */    
/*  872: 872 */    public EntryIterator(double from) { super(from, null); }
/*  873:     */    
/*  874:     */    public Double2ObjectLinkedOpenHashMap<V>.MapEntry next() {
/*  875: 875 */      return this.entry = new Double2ObjectLinkedOpenHashMap.MapEntry(Double2ObjectLinkedOpenHashMap.this, nextEntry());
/*  876:     */    }
/*  877:     */    
/*  878: 878 */    public Double2ObjectLinkedOpenHashMap<V>.MapEntry previous() { return this.entry = new Double2ObjectLinkedOpenHashMap.MapEntry(Double2ObjectLinkedOpenHashMap.this, previousEntry()); }
/*  879:     */    
/*  880:     */    public void remove()
/*  881:     */    {
/*  882: 882 */      super.remove();
/*  883: 883 */      Double2ObjectLinkedOpenHashMap.MapEntry.access$202(this.entry, -1); }
/*  884:     */    
/*  885: 885 */    public void set(Double2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*  886: 886 */    public void add(Double2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*  887:     */  }
/*  888:     */  
/*  889: 889 */  private class FastEntryIterator extends Double2ObjectLinkedOpenHashMap<V>.MapIterator implements ObjectListIterator<Double2ObjectMap.Entry<V>> { final AbstractDouble2ObjectMap.BasicEntry<V> entry = new AbstractDouble2ObjectMap.BasicEntry(0.0D, null);
/*  890: 890 */    public FastEntryIterator() { super(null); }
/*  891:     */    
/*  892: 892 */    public FastEntryIterator(double from) { super(from, null); }
/*  893:     */    
/*  894:     */    public AbstractDouble2ObjectMap.BasicEntry<V> next() {
/*  895: 895 */      int e = nextEntry();
/*  896: 896 */      this.entry.key = Double2ObjectLinkedOpenHashMap.this.key[e];
/*  897: 897 */      this.entry.value = Double2ObjectLinkedOpenHashMap.this.value[e];
/*  898: 898 */      return this.entry;
/*  899:     */    }
/*  900:     */    
/*  901: 901 */    public AbstractDouble2ObjectMap.BasicEntry<V> previous() { int e = previousEntry();
/*  902: 902 */      this.entry.key = Double2ObjectLinkedOpenHashMap.this.key[e];
/*  903: 903 */      this.entry.value = Double2ObjectLinkedOpenHashMap.this.value[e];
/*  904: 904 */      return this.entry; }
/*  905:     */    
/*  906: 906 */    public void set(Double2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*  907: 907 */    public void add(Double2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); } }
/*  908:     */  
/*  909:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Double2ObjectMap.Entry<V>> implements Double2ObjectSortedMap.FastSortedEntrySet<V> { private MapEntrySet() {}
/*  910:     */    
/*  911: 911 */    public ObjectBidirectionalIterator<Double2ObjectMap.Entry<V>> iterator() { return new Double2ObjectLinkedOpenHashMap.EntryIterator(Double2ObjectLinkedOpenHashMap.this); }
/*  912:     */    
/*  913: 913 */    public Comparator<? super Double2ObjectMap.Entry<V>> comparator() { return null; }
/*  914: 914 */    public ObjectSortedSet<Double2ObjectMap.Entry<V>> subSet(Double2ObjectMap.Entry<V> fromElement, Double2ObjectMap.Entry<V> toElement) { throw new UnsupportedOperationException(); }
/*  915: 915 */    public ObjectSortedSet<Double2ObjectMap.Entry<V>> headSet(Double2ObjectMap.Entry<V> toElement) { throw new UnsupportedOperationException(); }
/*  916: 916 */    public ObjectSortedSet<Double2ObjectMap.Entry<V>> tailSet(Double2ObjectMap.Entry<V> fromElement) { throw new UnsupportedOperationException(); }
/*  917:     */    
/*  918: 918 */    public Double2ObjectMap.Entry<V> first() { if (Double2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  919: 919 */      return new Double2ObjectLinkedOpenHashMap.MapEntry(Double2ObjectLinkedOpenHashMap.this, Double2ObjectLinkedOpenHashMap.this.first);
/*  920:     */    }
/*  921:     */    
/*  922: 922 */    public Double2ObjectMap.Entry<V> last() { if (Double2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  923: 923 */      return new Double2ObjectLinkedOpenHashMap.MapEntry(Double2ObjectLinkedOpenHashMap.this, Double2ObjectLinkedOpenHashMap.this.last);
/*  924:     */    }
/*  925:     */    
/*  926:     */    public boolean contains(Object o) {
/*  927: 927 */      if (!(o instanceof Map.Entry)) return false;
/*  928: 928 */      Map.Entry<Double, V> e = (Map.Entry)o;
/*  929: 929 */      double k = ((Double)e.getKey()).doubleValue();
/*  930:     */      
/*  931: 931 */      int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & Double2ObjectLinkedOpenHashMap.this.mask;
/*  932:     */      
/*  933: 933 */      while (Double2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  934: 934 */        if (Double2ObjectLinkedOpenHashMap.this.key[pos] == k) return Double2ObjectLinkedOpenHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Double2ObjectLinkedOpenHashMap.this.value[pos].equals(e.getValue());
/*  935: 935 */        pos = pos + 1 & Double2ObjectLinkedOpenHashMap.this.mask;
/*  936:     */      }
/*  937: 937 */      return false;
/*  938:     */    }
/*  939:     */    
/*  940:     */    public boolean remove(Object o) {
/*  941: 941 */      if (!(o instanceof Map.Entry)) return false;
/*  942: 942 */      Map.Entry<Double, V> e = (Map.Entry)o;
/*  943: 943 */      double k = ((Double)e.getKey()).doubleValue();
/*  944:     */      
/*  945: 945 */      int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & Double2ObjectLinkedOpenHashMap.this.mask;
/*  946:     */      
/*  947: 947 */      while (Double2ObjectLinkedOpenHashMap.this.used[pos] != 0) {
/*  948: 948 */        if (Double2ObjectLinkedOpenHashMap.this.key[pos] == k) {
/*  949: 949 */          Double2ObjectLinkedOpenHashMap.this.remove(e.getKey());
/*  950: 950 */          return true;
/*  951:     */        }
/*  952: 952 */        pos = pos + 1 & Double2ObjectLinkedOpenHashMap.this.mask;
/*  953:     */      }
/*  954: 954 */      return false;
/*  955:     */    }
/*  956:     */    
/*  957: 957 */    public int size() { return Double2ObjectLinkedOpenHashMap.this.size; }
/*  958:     */    
/*  959:     */    public void clear() {
/*  960: 960 */      Double2ObjectLinkedOpenHashMap.this.clear();
/*  961:     */    }
/*  962:     */    
/*  963: 963 */    public ObjectBidirectionalIterator<Double2ObjectMap.Entry<V>> iterator(Double2ObjectMap.Entry<V> from) { return new Double2ObjectLinkedOpenHashMap.EntryIterator(Double2ObjectLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue()); }
/*  964:     */    
/*  965:     */    public ObjectBidirectionalIterator<Double2ObjectMap.Entry<V>> fastIterator() {
/*  966: 966 */      return new Double2ObjectLinkedOpenHashMap.FastEntryIterator(Double2ObjectLinkedOpenHashMap.this);
/*  967:     */    }
/*  968:     */    
/*  969: 969 */    public ObjectBidirectionalIterator<Double2ObjectMap.Entry<V>> fastIterator(Double2ObjectMap.Entry<V> from) { return new Double2ObjectLinkedOpenHashMap.FastEntryIterator(Double2ObjectLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue()); }
/*  970:     */  }
/*  971:     */  
/*  972:     */  public Double2ObjectSortedMap.FastSortedEntrySet<V> double2ObjectEntrySet() {
/*  973: 973 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/*  974: 974 */    return this.entries;
/*  975:     */  }
/*  976:     */  
/*  979:     */  private final class KeyIterator
/*  980:     */    extends Double2ObjectLinkedOpenHashMap.MapIterator
/*  981:     */    implements DoubleListIterator
/*  982:     */  {
/*  983: 983 */    public KeyIterator(double k) { super(k, null); }
/*  984: 984 */    public double previousDouble() { return Double2ObjectLinkedOpenHashMap.this.key[previousEntry()]; }
/*  985: 985 */    public void set(double k) { throw new UnsupportedOperationException(); }
/*  986: 986 */    public void add(double k) { throw new UnsupportedOperationException(); }
/*  987: 987 */    public Double previous() { return Double.valueOf(Double2ObjectLinkedOpenHashMap.this.key[previousEntry()]); }
/*  988: 988 */    public void set(Double ok) { throw new UnsupportedOperationException(); }
/*  989: 989 */    public void add(Double ok) { throw new UnsupportedOperationException(); }
/*  990: 990 */    public KeyIterator() { super(null); }
/*  991: 991 */    public double nextDouble() { return Double2ObjectLinkedOpenHashMap.this.key[nextEntry()]; }
/*  992: 992 */    public Double next() { return Double.valueOf(Double2ObjectLinkedOpenHashMap.this.key[nextEntry()]); } }
/*  993:     */  
/*  994:     */  private final class KeySet extends AbstractDoubleSortedSet { private KeySet() {}
/*  995:     */    
/*  996: 996 */    public DoubleListIterator iterator(double from) { return new Double2ObjectLinkedOpenHashMap.KeyIterator(Double2ObjectLinkedOpenHashMap.this, from); }
/*  997:     */    
/*  998:     */    public DoubleListIterator iterator() {
/*  999: 999 */      return new Double2ObjectLinkedOpenHashMap.KeyIterator(Double2ObjectLinkedOpenHashMap.this);
/* 1000:     */    }
/* 1001:     */    
/* 1002:1002 */    public int size() { return Double2ObjectLinkedOpenHashMap.this.size; }
/* 1003:     */    
/* 1005:1005 */    public boolean contains(double k) { return Double2ObjectLinkedOpenHashMap.this.containsKey(k); }
/* 1006:     */    
/* 1007:     */    public boolean remove(double k) {
/* 1008:1008 */      int oldSize = Double2ObjectLinkedOpenHashMap.this.size;
/* 1009:1009 */      Double2ObjectLinkedOpenHashMap.this.remove(k);
/* 1010:1010 */      return Double2ObjectLinkedOpenHashMap.this.size != oldSize;
/* 1011:     */    }
/* 1012:     */    
/* 1013:1013 */    public void clear() { Double2ObjectLinkedOpenHashMap.this.clear(); }
/* 1014:     */    
/* 1015:     */    public double firstDouble() {
/* 1016:1016 */      if (Double2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1017:1017 */      return Double2ObjectLinkedOpenHashMap.this.key[Double2ObjectLinkedOpenHashMap.this.first];
/* 1018:     */    }
/* 1019:     */    
/* 1020:1020 */    public double lastDouble() { if (Double2ObjectLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1021:1021 */      return Double2ObjectLinkedOpenHashMap.this.key[Double2ObjectLinkedOpenHashMap.this.last]; }
/* 1022:     */    
/* 1023:1023 */    public DoubleComparator comparator() { return null; }
/* 1024:1024 */    public final DoubleSortedSet tailSet(double from) { throw new UnsupportedOperationException(); }
/* 1025:1025 */    public final DoubleSortedSet headSet(double to) { throw new UnsupportedOperationException(); }
/* 1026:1026 */    public final DoubleSortedSet subSet(double from, double to) { throw new UnsupportedOperationException(); }
/* 1027:     */  }
/* 1028:     */  
/* 1029:1029 */  public DoubleSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1030:1030 */    return this.keys;
/* 1031:     */  }
/* 1032:     */  
/* 1035:     */  private final class ValueIterator
/* 1036:     */    extends Double2ObjectLinkedOpenHashMap<V>.MapIterator
/* 1037:     */    implements ObjectListIterator<V>
/* 1038:     */  {
/* 1039:1039 */    public V previous() { return Double2ObjectLinkedOpenHashMap.this.value[previousEntry()]; }
/* 1040:1040 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1041:1041 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1042:1042 */    public ValueIterator() { super(null); }
/* 1043:1043 */    public V next() { return Double2ObjectLinkedOpenHashMap.this.value[nextEntry()]; }
/* 1044:     */  }
/* 1045:     */  
/* 1046:1046 */  public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1047:     */        public ObjectIterator<V> iterator() {
/* 1048:1048 */          return new Double2ObjectLinkedOpenHashMap.ValueIterator(Double2ObjectLinkedOpenHashMap.this);
/* 1049:     */        }
/* 1050:     */        
/* 1051:1051 */        public int size() { return Double2ObjectLinkedOpenHashMap.this.size; }
/* 1052:     */        
/* 1053:     */        public boolean contains(Object v) {
/* 1054:1054 */          return Double2ObjectLinkedOpenHashMap.this.containsValue(v);
/* 1055:     */        }
/* 1056:     */        
/* 1057:1057 */        public void clear() { Double2ObjectLinkedOpenHashMap.this.clear(); }
/* 1058:     */      };
/* 1059:     */    }
/* 1060:1060 */    return this.values;
/* 1061:     */  }
/* 1062:     */  
/* 1071:     */  @Deprecated
/* 1072:     */  public boolean rehash()
/* 1073:     */  {
/* 1074:1074 */    return true;
/* 1075:     */  }
/* 1076:     */  
/* 1087:     */  public boolean trim()
/* 1088:     */  {
/* 1089:1089 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1090:1090 */    if (l >= this.n) return true;
/* 1091:     */    try {
/* 1092:1092 */      rehash(l);
/* 1093:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1094:1094 */      return false; }
/* 1095:1095 */    return true;
/* 1096:     */  }
/* 1097:     */  
/* 1114:     */  public boolean trim(int n)
/* 1115:     */  {
/* 1116:1116 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1117:1117 */    if (this.n <= l) return true;
/* 1118:     */    try {
/* 1119:1119 */      rehash(l);
/* 1120:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1121:1121 */      return false; }
/* 1122:1122 */    return true;
/* 1123:     */  }
/* 1124:     */  
/* 1133:     */  protected void rehash(int newN)
/* 1134:     */  {
/* 1135:1135 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1136:     */    
/* 1137:1137 */    double[] key = this.key;
/* 1138:1138 */    V[] value = this.value;
/* 1139:1139 */    int newMask = newN - 1;
/* 1140:1140 */    double[] newKey = new double[newN];
/* 1141:1141 */    V[] newValue = (Object[])new Object[newN];
/* 1142:1142 */    boolean[] newUsed = new boolean[newN];
/* 1143:1143 */    long[] link = this.link;
/* 1144:1144 */    long[] newLink = new long[newN];
/* 1145:1145 */    this.first = -1;
/* 1146:1146 */    for (int j = this.size; j-- != 0;) {
/* 1147:1147 */      double k = key[i];
/* 1148:1148 */      int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & newMask;
/* 1149:1149 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1150:1150 */      newUsed[pos] = true;
/* 1151:1151 */      newKey[pos] = k;
/* 1152:1152 */      newValue[pos] = value[i];
/* 1153:1153 */      if (prev != -1) {
/* 1154:1154 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1155:1155 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1156:1156 */        newPrev = pos;
/* 1157:     */      }
/* 1158:     */      else {
/* 1159:1159 */        newPrev = this.first = pos;
/* 1160:     */        
/* 1161:1161 */        newLink[pos] = -1L;
/* 1162:     */      }
/* 1163:1163 */      int t = i;
/* 1164:1164 */      i = (int)link[i];
/* 1165:1165 */      prev = t;
/* 1166:     */    }
/* 1167:1167 */    this.n = newN;
/* 1168:1168 */    this.mask = newMask;
/* 1169:1169 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1170:1170 */    this.key = newKey;
/* 1171:1171 */    this.value = newValue;
/* 1172:1172 */    this.used = newUsed;
/* 1173:1173 */    this.link = newLink;
/* 1174:1174 */    this.last = newPrev;
/* 1175:1175 */    if (newPrev != -1)
/* 1176:     */    {
/* 1177:1177 */      newLink[newPrev] |= 4294967295L;
/* 1178:     */    }
/* 1179:     */  }
/* 1180:     */  
/* 1183:     */  public Double2ObjectLinkedOpenHashMap<V> clone()
/* 1184:     */  {
/* 1185:     */    Double2ObjectLinkedOpenHashMap<V> c;
/* 1186:     */    
/* 1188:     */    try
/* 1189:     */    {
/* 1190:1190 */      c = (Double2ObjectLinkedOpenHashMap)super.clone();
/* 1191:     */    }
/* 1192:     */    catch (CloneNotSupportedException cantHappen) {
/* 1193:1193 */      throw new InternalError();
/* 1194:     */    }
/* 1195:1195 */    c.keys = null;
/* 1196:1196 */    c.values = null;
/* 1197:1197 */    c.entries = null;
/* 1198:1198 */    c.key = ((double[])this.key.clone());
/* 1199:1199 */    c.value = ((Object[])this.value.clone());
/* 1200:1200 */    c.used = ((boolean[])this.used.clone());
/* 1201:1201 */    c.link = ((long[])this.link.clone());
/* 1202:1202 */    return c;
/* 1203:     */  }
/* 1204:     */  
/* 1212:     */  public int hashCode()
/* 1213:     */  {
/* 1214:1214 */    int h = 0;
/* 1215:1215 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1216:1216 */      while (this.used[i] == 0) i++;
/* 1217:1217 */      t = HashCommon.double2int(this.key[i]);
/* 1218:1218 */      if (this != this.value[i])
/* 1219:1219 */        t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 1220:1220 */      h += t;
/* 1221:1221 */      i++;
/* 1222:     */    }
/* 1223:1223 */    return h;
/* 1224:     */  }
/* 1225:     */  
/* 1226:1226 */  private void writeObject(ObjectOutputStream s) throws IOException { double[] key = this.key;
/* 1227:1227 */    V[] value = this.value;
/* 1228:1228 */    Double2ObjectLinkedOpenHashMap<V>.MapIterator i = new MapIterator(null);
/* 1229:1229 */    s.defaultWriteObject();
/* 1230:1230 */    for (int j = this.size; j-- != 0;) {
/* 1231:1231 */      int e = i.nextEntry();
/* 1232:1232 */      s.writeDouble(key[e]);
/* 1233:1233 */      s.writeObject(value[e]);
/* 1234:     */    }
/* 1235:     */  }
/* 1236:     */  
/* 1237:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1238:1238 */    s.defaultReadObject();
/* 1239:1239 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1240:1240 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1241:1241 */    this.mask = (this.n - 1);
/* 1242:1242 */    double[] key = this.key = new double[this.n];
/* 1243:1243 */    V[] value = this.value = (Object[])new Object[this.n];
/* 1244:1244 */    boolean[] used = this.used = new boolean[this.n];
/* 1245:1245 */    long[] link = this.link = new long[this.n];
/* 1246:1246 */    int prev = -1;
/* 1247:1247 */    this.first = (this.last = -1);
/* 1248:     */    
/* 1250:1250 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1251:1251 */      double k = s.readDouble();
/* 1252:1252 */      V v = s.readObject();
/* 1253:1253 */      pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/* 1254:1254 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1255:1255 */      used[pos] = true;
/* 1256:1256 */      key[pos] = k;
/* 1257:1257 */      value[pos] = v;
/* 1258:1258 */      if (this.first != -1) {
/* 1259:1259 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1260:1260 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1261:1261 */        prev = pos;
/* 1262:     */      }
/* 1263:     */      else {
/* 1264:1264 */        prev = this.first = pos;
/* 1265:     */        
/* 1266:1266 */        link[pos] |= -4294967296L;
/* 1267:     */      }
/* 1268:     */    }
/* 1269:1269 */    this.last = prev;
/* 1270:1270 */    if (prev != -1)
/* 1271:     */    {
/* 1272:1272 */      link[prev] |= 4294967295L;
/* 1273:     */    }
/* 1274:     */  }
/* 1275:     */  
/* 1276:     */  private void checkTable() {}
/* 1277:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2ObjectLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */