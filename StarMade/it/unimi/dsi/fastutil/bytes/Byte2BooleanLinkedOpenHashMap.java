/*    1:     */package it.unimi.dsi.fastutil.bytes;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.HashCommon;
/*    5:     */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    7:     */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*    8:     */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*    9:     */import it.unimi.dsi.fastutil.booleans.BooleanListIterator;
/*   10:     */import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*   11:     */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
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
/*  113:     */public class Byte2BooleanLinkedOpenHashMap
/*  114:     */  extends AbstractByte2BooleanSortedMap
/*  115:     */  implements Serializable, Cloneable, Hash
/*  116:     */{
/*  117:     */  public static final long serialVersionUID = 0L;
/*  118:     */  private static final boolean ASSERTS = false;
/*  119:     */  protected transient byte[] key;
/*  120:     */  protected transient boolean[] value;
/*  121:     */  protected transient boolean[] used;
/*  122:     */  protected final float f;
/*  123:     */  protected transient int n;
/*  124:     */  protected transient int maxFill;
/*  125:     */  protected transient int mask;
/*  126:     */  protected int size;
/*  127:     */  protected volatile transient Byte2BooleanSortedMap.FastSortedEntrySet entries;
/*  128:     */  protected volatile transient ByteSortedSet keys;
/*  129:     */  protected volatile transient BooleanCollection values;
/*  130: 130 */  protected transient int first = -1;
/*  131:     */  
/*  132: 132 */  protected transient int last = -1;
/*  133:     */  
/*  140:     */  protected transient long[] link;
/*  141:     */  
/*  149:     */  public Byte2BooleanLinkedOpenHashMap(int expected, float f)
/*  150:     */  {
/*  151: 151 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152: 152 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153: 153 */    this.f = f;
/*  154: 154 */    this.n = HashCommon.arraySize(expected, f);
/*  155: 155 */    this.mask = (this.n - 1);
/*  156: 156 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  157: 157 */    this.key = new byte[this.n];
/*  158: 158 */    this.value = new boolean[this.n];
/*  159: 159 */    this.used = new boolean[this.n];
/*  160: 160 */    this.link = new long[this.n];
/*  161:     */  }
/*  162:     */  
/*  165:     */  public Byte2BooleanLinkedOpenHashMap(int expected)
/*  166:     */  {
/*  167: 167 */    this(expected, 0.75F);
/*  168:     */  }
/*  169:     */  
/*  171:     */  public Byte2BooleanLinkedOpenHashMap()
/*  172:     */  {
/*  173: 173 */    this(16, 0.75F);
/*  174:     */  }
/*  175:     */  
/*  179:     */  public Byte2BooleanLinkedOpenHashMap(Map<? extends Byte, ? extends Boolean> m, float f)
/*  180:     */  {
/*  181: 181 */    this(m.size(), f);
/*  182: 182 */    putAll(m);
/*  183:     */  }
/*  184:     */  
/*  187:     */  public Byte2BooleanLinkedOpenHashMap(Map<? extends Byte, ? extends Boolean> m)
/*  188:     */  {
/*  189: 189 */    this(m, 0.75F);
/*  190:     */  }
/*  191:     */  
/*  195:     */  public Byte2BooleanLinkedOpenHashMap(Byte2BooleanMap m, float f)
/*  196:     */  {
/*  197: 197 */    this(m.size(), f);
/*  198: 198 */    putAll(m);
/*  199:     */  }
/*  200:     */  
/*  203:     */  public Byte2BooleanLinkedOpenHashMap(Byte2BooleanMap m)
/*  204:     */  {
/*  205: 205 */    this(m, 0.75F);
/*  206:     */  }
/*  207:     */  
/*  213:     */  public Byte2BooleanLinkedOpenHashMap(byte[] k, boolean[] v, float f)
/*  214:     */  {
/*  215: 215 */    this(k.length, f);
/*  216: 216 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217: 217 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  218:     */    }
/*  219:     */  }
/*  220:     */  
/*  224:     */  public Byte2BooleanLinkedOpenHashMap(byte[] k, boolean[] v)
/*  225:     */  {
/*  226: 226 */    this(k, v, 0.75F);
/*  227:     */  }
/*  228:     */  
/*  232:     */  public boolean put(byte k, boolean v)
/*  233:     */  {
/*  234: 234 */    int pos = HashCommon.murmurHash3(k) & this.mask;
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
/*  262: 262 */  public Boolean put(Byte ok, Boolean ov) { boolean v = ov.booleanValue();
/*  263: 263 */    byte k = ok.byteValue();
/*  264:     */    
/*  265: 265 */    int pos = HashCommon.murmurHash3(k) & this.mask;
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
/*  304: 304 */        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/*  305: 305 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  306: 306 */        pos = pos + 1 & this.mask;
/*  307:     */      }
/*  308: 308 */      if (this.used[pos] == 0) break;
/*  309: 309 */      this.key[last] = this.key[pos];
/*  310: 310 */      this.value[last] = this.value[pos];
/*  311: 311 */      fixPointers(pos, last);
/*  312:     */    }
/*  313: 313 */    this.used[last] = false;
/*  314: 314 */    return last;
/*  315:     */  }
/*  316:     */  
/*  317:     */  public boolean remove(byte k)
/*  318:     */  {
/*  319: 319 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  320:     */    
/*  321: 321 */    while (this.used[pos] != 0) {
/*  322: 322 */      if (this.key[pos] == k) {
/*  323: 323 */        this.size -= 1;
/*  324: 324 */        fixPointers(pos);
/*  325: 325 */        boolean v = this.value[pos];
/*  326: 326 */        shiftKeys(pos);
/*  327: 327 */        return v;
/*  328:     */      }
/*  329: 329 */      pos = pos + 1 & this.mask;
/*  330:     */    }
/*  331: 331 */    return this.defRetValue;
/*  332:     */  }
/*  333:     */  
/*  334:     */  public Boolean remove(Object ok) {
/*  335: 335 */    byte k = ((Byte)ok).byteValue();
/*  336:     */    
/*  337: 337 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  338:     */    
/*  339: 339 */    while (this.used[pos] != 0) {
/*  340: 340 */      if (this.key[pos] == k) {
/*  341: 341 */        this.size -= 1;
/*  342: 342 */        fixPointers(pos);
/*  343: 343 */        boolean v = this.value[pos];
/*  344: 344 */        shiftKeys(pos);
/*  345: 345 */        return Boolean.valueOf(v);
/*  346:     */      }
/*  347: 347 */      pos = pos + 1 & this.mask;
/*  348:     */    }
/*  349: 349 */    return null;
/*  350:     */  }
/*  351:     */  
/*  354:     */  public boolean removeFirstBoolean()
/*  355:     */  {
/*  356: 356 */    if (this.size == 0) throw new NoSuchElementException();
/*  357: 357 */    this.size -= 1;
/*  358: 358 */    int pos = this.first;
/*  359:     */    
/*  360: 360 */    this.first = ((int)this.link[pos]);
/*  361: 361 */    if (0 <= this.first)
/*  362:     */    {
/*  363: 363 */      this.link[this.first] |= -4294967296L;
/*  364:     */    }
/*  365: 365 */    boolean v = this.value[pos];
/*  366: 366 */    shiftKeys(pos);
/*  367: 367 */    return v;
/*  368:     */  }
/*  369:     */  
/*  372:     */  public boolean removeLastBoolean()
/*  373:     */  {
/*  374: 374 */    if (this.size == 0) throw new NoSuchElementException();
/*  375: 375 */    this.size -= 1;
/*  376: 376 */    int pos = this.last;
/*  377:     */    
/*  378: 378 */    this.last = ((int)(this.link[pos] >>> 32));
/*  379: 379 */    if (0 <= this.last)
/*  380:     */    {
/*  381: 381 */      this.link[this.last] |= 4294967295L;
/*  382:     */    }
/*  383: 383 */    boolean v = this.value[pos];
/*  384: 384 */    shiftKeys(pos);
/*  385: 385 */    return v;
/*  386:     */  }
/*  387:     */  
/*  388: 388 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  389: 389 */    if (this.last == i) {
/*  390: 390 */      this.last = ((int)(this.link[i] >>> 32));
/*  391:     */      
/*  392: 392 */      this.link[this.last] |= 4294967295L;
/*  393:     */    }
/*  394:     */    else {
/*  395: 395 */      long linki = this.link[i];
/*  396: 396 */      int prev = (int)(linki >>> 32);
/*  397: 397 */      int next = (int)linki;
/*  398: 398 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  399: 399 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  400:     */    }
/*  401: 401 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  402: 402 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  403: 403 */    this.first = i;
/*  404:     */  }
/*  405:     */  
/*  406: 406 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  407: 407 */    if (this.first == i) {
/*  408: 408 */      this.first = ((int)this.link[i]);
/*  409:     */      
/*  410: 410 */      this.link[this.first] |= -4294967296L;
/*  411:     */    }
/*  412:     */    else {
/*  413: 413 */      long linki = this.link[i];
/*  414: 414 */      int prev = (int)(linki >>> 32);
/*  415: 415 */      int next = (int)linki;
/*  416: 416 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  417: 417 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  418:     */    }
/*  419: 419 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  420: 420 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  421: 421 */    this.last = i;
/*  422:     */  }
/*  423:     */  
/*  427:     */  public boolean getAndMoveToFirst(byte k)
/*  428:     */  {
/*  429: 429 */    byte[] key = this.key;
/*  430: 430 */    boolean[] used = this.used;
/*  431: 431 */    int mask = this.mask;
/*  432:     */    
/*  433: 433 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  434:     */    
/*  435: 435 */    while (used[pos] != 0) {
/*  436: 436 */      if (k == key[pos]) {
/*  437: 437 */        moveIndexToFirst(pos);
/*  438: 438 */        return this.value[pos];
/*  439:     */      }
/*  440: 440 */      pos = pos + 1 & mask;
/*  441:     */    }
/*  442: 442 */    return this.defRetValue;
/*  443:     */  }
/*  444:     */  
/*  448:     */  public boolean getAndMoveToLast(byte k)
/*  449:     */  {
/*  450: 450 */    byte[] key = this.key;
/*  451: 451 */    boolean[] used = this.used;
/*  452: 452 */    int mask = this.mask;
/*  453:     */    
/*  454: 454 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  455:     */    
/*  456: 456 */    while (used[pos] != 0) {
/*  457: 457 */      if (k == key[pos]) {
/*  458: 458 */        moveIndexToLast(pos);
/*  459: 459 */        return this.value[pos];
/*  460:     */      }
/*  461: 461 */      pos = pos + 1 & mask;
/*  462:     */    }
/*  463: 463 */    return this.defRetValue;
/*  464:     */  }
/*  465:     */  
/*  470:     */  public boolean putAndMoveToFirst(byte k, boolean v)
/*  471:     */  {
/*  472: 472 */    byte[] key = this.key;
/*  473: 473 */    boolean[] used = this.used;
/*  474: 474 */    int mask = this.mask;
/*  475:     */    
/*  476: 476 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  477:     */    
/*  478: 478 */    while (used[pos] != 0) {
/*  479: 479 */      if (k == key[pos]) {
/*  480: 480 */        boolean oldValue = this.value[pos];
/*  481: 481 */        this.value[pos] = v;
/*  482: 482 */        moveIndexToFirst(pos);
/*  483: 483 */        return oldValue;
/*  484:     */      }
/*  485: 485 */      pos = pos + 1 & mask;
/*  486:     */    }
/*  487: 487 */    used[pos] = true;
/*  488: 488 */    key[pos] = k;
/*  489: 489 */    this.value[pos] = v;
/*  490: 490 */    if (this.size == 0) {
/*  491: 491 */      this.first = (this.last = pos);
/*  492:     */      
/*  493: 493 */      this.link[pos] = -1L;
/*  494:     */    }
/*  495:     */    else {
/*  496: 496 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  497: 497 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  498: 498 */      this.first = pos;
/*  499:     */    }
/*  500: 500 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  501:     */    }
/*  502: 502 */    return this.defRetValue;
/*  503:     */  }
/*  504:     */  
/*  509:     */  public boolean putAndMoveToLast(byte k, boolean v)
/*  510:     */  {
/*  511: 511 */    byte[] key = this.key;
/*  512: 512 */    boolean[] used = this.used;
/*  513: 513 */    int mask = this.mask;
/*  514:     */    
/*  515: 515 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  516:     */    
/*  517: 517 */    while (used[pos] != 0) {
/*  518: 518 */      if (k == key[pos]) {
/*  519: 519 */        boolean oldValue = this.value[pos];
/*  520: 520 */        this.value[pos] = v;
/*  521: 521 */        moveIndexToLast(pos);
/*  522: 522 */        return oldValue;
/*  523:     */      }
/*  524: 524 */      pos = pos + 1 & mask;
/*  525:     */    }
/*  526: 526 */    used[pos] = true;
/*  527: 527 */    key[pos] = k;
/*  528: 528 */    this.value[pos] = v;
/*  529: 529 */    if (this.size == 0) {
/*  530: 530 */      this.first = (this.last = pos);
/*  531:     */      
/*  532: 532 */      this.link[pos] = -1L;
/*  533:     */    }
/*  534:     */    else {
/*  535: 535 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  536: 536 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  537: 537 */      this.last = pos;
/*  538:     */    }
/*  539: 539 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  540:     */    }
/*  541: 541 */    return this.defRetValue;
/*  542:     */  }
/*  543:     */  
/*  544: 544 */  public Boolean get(Byte ok) { byte k = ok.byteValue();
/*  545:     */    
/*  546: 546 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  547:     */    
/*  548: 548 */    while (this.used[pos] != 0) {
/*  549: 549 */      if (this.key[pos] == k) return Boolean.valueOf(this.value[pos]);
/*  550: 550 */      pos = pos + 1 & this.mask;
/*  551:     */    }
/*  552: 552 */    return null;
/*  553:     */  }
/*  554:     */  
/*  555:     */  public boolean get(byte k)
/*  556:     */  {
/*  557: 557 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  558:     */    
/*  559: 559 */    while (this.used[pos] != 0) {
/*  560: 560 */      if (this.key[pos] == k) return this.value[pos];
/*  561: 561 */      pos = pos + 1 & this.mask;
/*  562:     */    }
/*  563: 563 */    return this.defRetValue;
/*  564:     */  }
/*  565:     */  
/*  566:     */  public boolean containsKey(byte k)
/*  567:     */  {
/*  568: 568 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  569:     */    
/*  570: 570 */    while (this.used[pos] != 0) {
/*  571: 571 */      if (this.key[pos] == k) return true;
/*  572: 572 */      pos = pos + 1 & this.mask;
/*  573:     */    }
/*  574: 574 */    return false;
/*  575:     */  }
/*  576:     */  
/*  577: 577 */  public boolean containsValue(boolean v) { boolean[] value = this.value;
/*  578: 578 */    boolean[] used = this.used;
/*  579: 579 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/*  580: 580 */    return false;
/*  581:     */  }
/*  582:     */  
/*  587:     */  public void clear()
/*  588:     */  {
/*  589: 589 */    if (this.size == 0) return;
/*  590: 590 */    this.size = 0;
/*  591: 591 */    BooleanArrays.fill(this.used, false);
/*  592:     */    
/*  593: 593 */    this.first = (this.last = -1);
/*  594:     */  }
/*  595:     */  
/*  596: 596 */  public int size() { return this.size; }
/*  597:     */  
/*  598:     */  public boolean isEmpty() {
/*  599: 599 */    return this.size == 0;
/*  600:     */  }
/*  601:     */  
/*  606:     */  @Deprecated
/*  607:     */  public void growthFactor(int growthFactor) {}
/*  608:     */  
/*  613:     */  @Deprecated
/*  614:     */  public int growthFactor()
/*  615:     */  {
/*  616: 616 */    return 16;
/*  617:     */  }
/*  618:     */  
/*  619:     */  private final class MapEntry
/*  620:     */    implements Byte2BooleanMap.Entry, Map.Entry<Byte, Boolean>
/*  621:     */  {
/*  622:     */    private int index;
/*  623:     */    
/*  624:     */    MapEntry(int index)
/*  625:     */    {
/*  626: 626 */      this.index = index;
/*  627:     */    }
/*  628:     */    
/*  629: 629 */    public Byte getKey() { return Byte.valueOf(Byte2BooleanLinkedOpenHashMap.this.key[this.index]); }
/*  630:     */    
/*  631:     */    public byte getByteKey() {
/*  632: 632 */      return Byte2BooleanLinkedOpenHashMap.this.key[this.index];
/*  633:     */    }
/*  634:     */    
/*  635: 635 */    public Boolean getValue() { return Boolean.valueOf(Byte2BooleanLinkedOpenHashMap.this.value[this.index]); }
/*  636:     */    
/*  638: 638 */    public boolean getBooleanValue() { return Byte2BooleanLinkedOpenHashMap.this.value[this.index]; }
/*  639:     */    
/*  640:     */    public boolean setValue(boolean v) {
/*  641: 641 */      boolean oldValue = Byte2BooleanLinkedOpenHashMap.this.value[this.index];
/*  642: 642 */      Byte2BooleanLinkedOpenHashMap.this.value[this.index] = v;
/*  643: 643 */      return oldValue;
/*  644:     */    }
/*  645:     */    
/*  646: 646 */    public Boolean setValue(Boolean v) { return Boolean.valueOf(setValue(v.booleanValue())); }
/*  647:     */    
/*  648:     */    public boolean equals(Object o)
/*  649:     */    {
/*  650: 650 */      if (!(o instanceof Map.Entry)) return false;
/*  651: 651 */      Map.Entry<Byte, Boolean> e = (Map.Entry)o;
/*  652: 652 */      return (Byte2BooleanLinkedOpenHashMap.this.key[this.index] == ((Byte)e.getKey()).byteValue()) && (Byte2BooleanLinkedOpenHashMap.this.value[this.index] == ((Boolean)e.getValue()).booleanValue());
/*  653:     */    }
/*  654:     */    
/*  655: 655 */    public int hashCode() { return Byte2BooleanLinkedOpenHashMap.this.key[this.index] ^ (Byte2BooleanLinkedOpenHashMap.this.value[this.index] != 0 ? 1231 : 1237); }
/*  656:     */    
/*  657:     */    public String toString() {
/*  658: 658 */      return Byte2BooleanLinkedOpenHashMap.this.key[this.index] + "=>" + Byte2BooleanLinkedOpenHashMap.this.value[this.index];
/*  659:     */    }
/*  660:     */  }
/*  661:     */  
/*  667:     */  protected void fixPointers(int i)
/*  668:     */  {
/*  669: 669 */    if (this.size == 0) {
/*  670: 670 */      this.first = (this.last = -1);
/*  671: 671 */      return;
/*  672:     */    }
/*  673: 673 */    if (this.first == i) {
/*  674: 674 */      this.first = ((int)this.link[i]);
/*  675: 675 */      if (0 <= this.first)
/*  676:     */      {
/*  677: 677 */        this.link[this.first] |= -4294967296L;
/*  678:     */      }
/*  679: 679 */      return;
/*  680:     */    }
/*  681: 681 */    if (this.last == i) {
/*  682: 682 */      this.last = ((int)(this.link[i] >>> 32));
/*  683: 683 */      if (0 <= this.last)
/*  684:     */      {
/*  685: 685 */        this.link[this.last] |= 4294967295L;
/*  686:     */      }
/*  687: 687 */      return;
/*  688:     */    }
/*  689: 689 */    long linki = this.link[i];
/*  690: 690 */    int prev = (int)(linki >>> 32);
/*  691: 691 */    int next = (int)linki;
/*  692: 692 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  693: 693 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  694:     */  }
/*  695:     */  
/*  702:     */  protected void fixPointers(int s, int d)
/*  703:     */  {
/*  704: 704 */    if (this.size == 1) {
/*  705: 705 */      this.first = (this.last = d);
/*  706:     */      
/*  707: 707 */      this.link[d] = -1L;
/*  708: 708 */      return;
/*  709:     */    }
/*  710: 710 */    if (this.first == s) {
/*  711: 711 */      this.first = d;
/*  712: 712 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  713: 713 */      this.link[d] = this.link[s];
/*  714: 714 */      return;
/*  715:     */    }
/*  716: 716 */    if (this.last == s) {
/*  717: 717 */      this.last = d;
/*  718: 718 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  719: 719 */      this.link[d] = this.link[s];
/*  720: 720 */      return;
/*  721:     */    }
/*  722: 722 */    long links = this.link[s];
/*  723: 723 */    int prev = (int)(links >>> 32);
/*  724: 724 */    int next = (int)links;
/*  725: 725 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  726: 726 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  727: 727 */    this.link[d] = links;
/*  728:     */  }
/*  729:     */  
/*  732:     */  public byte firstByteKey()
/*  733:     */  {
/*  734: 734 */    if (this.size == 0) throw new NoSuchElementException();
/*  735: 735 */    return this.key[this.first];
/*  736:     */  }
/*  737:     */  
/*  740:     */  public byte lastByteKey()
/*  741:     */  {
/*  742: 742 */    if (this.size == 0) throw new NoSuchElementException();
/*  743: 743 */    return this.key[this.last]; }
/*  744:     */  
/*  745: 745 */  public ByteComparator comparator() { return null; }
/*  746: 746 */  public Byte2BooleanSortedMap tailMap(byte from) { throw new UnsupportedOperationException(); }
/*  747: 747 */  public Byte2BooleanSortedMap headMap(byte to) { throw new UnsupportedOperationException(); }
/*  748: 748 */  public Byte2BooleanSortedMap subMap(byte from, byte to) { throw new UnsupportedOperationException(); }
/*  749:     */  
/*  755:     */  private class MapIterator
/*  756:     */  {
/*  757: 757 */    int prev = -1;
/*  758:     */    
/*  759: 759 */    int next = -1;
/*  760:     */    
/*  761: 761 */    int curr = -1;
/*  762:     */    
/*  763: 763 */    int index = -1;
/*  764:     */    
/*  765: 765 */    private MapIterator() { this.next = Byte2BooleanLinkedOpenHashMap.this.first;
/*  766: 766 */      this.index = 0;
/*  767:     */    }
/*  768:     */    
/*  769: 769 */    private MapIterator(byte from) { if (Byte2BooleanLinkedOpenHashMap.this.key[Byte2BooleanLinkedOpenHashMap.this.last] == from) {
/*  770: 770 */        this.prev = Byte2BooleanLinkedOpenHashMap.this.last;
/*  771: 771 */        this.index = Byte2BooleanLinkedOpenHashMap.this.size;
/*  772:     */      }
/*  773:     */      else
/*  774:     */      {
/*  775: 775 */        int pos = HashCommon.murmurHash3(from) & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  776:     */        
/*  777: 777 */        while (Byte2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  778: 778 */          if (Byte2BooleanLinkedOpenHashMap.this.key[pos] == from)
/*  779:     */          {
/*  780: 780 */            this.next = ((int)Byte2BooleanLinkedOpenHashMap.this.link[pos]);
/*  781: 781 */            this.prev = pos;
/*  782: 782 */            return;
/*  783:     */          }
/*  784: 784 */          pos = pos + 1 & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  785:     */        }
/*  786: 786 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  787:     */      } }
/*  788:     */    
/*  789: 789 */    public boolean hasNext() { return this.next != -1; }
/*  790: 790 */    public boolean hasPrevious() { return this.prev != -1; }
/*  791:     */    
/*  792: 792 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  793: 793 */      if (this.prev == -1) {
/*  794: 794 */        this.index = 0;
/*  795: 795 */        return;
/*  796:     */      }
/*  797: 797 */      if (this.next == -1) {
/*  798: 798 */        this.index = Byte2BooleanLinkedOpenHashMap.this.size;
/*  799: 799 */        return;
/*  800:     */      }
/*  801: 801 */      int pos = Byte2BooleanLinkedOpenHashMap.this.first;
/*  802: 802 */      this.index = 1;
/*  803: 803 */      while (pos != this.prev) {
/*  804: 804 */        pos = (int)Byte2BooleanLinkedOpenHashMap.this.link[pos];
/*  805: 805 */        this.index += 1;
/*  806:     */      }
/*  807:     */    }
/*  808:     */    
/*  809: 809 */    public int nextIndex() { ensureIndexKnown();
/*  810: 810 */      return this.index;
/*  811:     */    }
/*  812:     */    
/*  813: 813 */    public int previousIndex() { ensureIndexKnown();
/*  814: 814 */      return this.index - 1;
/*  815:     */    }
/*  816:     */    
/*  817: 817 */    public int nextEntry() { if (!hasNext()) return Byte2BooleanLinkedOpenHashMap.this.size();
/*  818: 818 */      this.curr = this.next;
/*  819: 819 */      this.next = ((int)Byte2BooleanLinkedOpenHashMap.this.link[this.curr]);
/*  820: 820 */      this.prev = this.curr;
/*  821: 821 */      if (this.index >= 0) this.index += 1;
/*  822: 822 */      return this.curr;
/*  823:     */    }
/*  824:     */    
/*  825: 825 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  826: 826 */      this.curr = this.prev;
/*  827: 827 */      this.prev = ((int)(Byte2BooleanLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  828: 828 */      this.next = this.curr;
/*  829: 829 */      if (this.index >= 0) this.index -= 1;
/*  830: 830 */      return this.curr;
/*  831:     */    }
/*  832:     */    
/*  833:     */    public void remove() {
/*  834: 834 */      ensureIndexKnown();
/*  835: 835 */      if (this.curr == -1) throw new IllegalStateException();
/*  836: 836 */      if (this.curr == this.prev)
/*  837:     */      {
/*  839: 839 */        this.index -= 1;
/*  840: 840 */        this.prev = ((int)(Byte2BooleanLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  841:     */      }
/*  842:     */      else {
/*  843: 843 */        this.next = ((int)Byte2BooleanLinkedOpenHashMap.this.link[this.curr]); }
/*  844: 844 */      Byte2BooleanLinkedOpenHashMap.this.size -= 1;
/*  845:     */      
/*  847: 847 */      if (this.prev == -1) { Byte2BooleanLinkedOpenHashMap.this.first = this.next;
/*  848:     */      } else
/*  849: 849 */        Byte2BooleanLinkedOpenHashMap.this.link[this.prev] ^= (Byte2BooleanLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  850: 850 */      if (this.next == -1) { Byte2BooleanLinkedOpenHashMap.this.last = this.prev;
/*  851:     */      } else
/*  852: 852 */        Byte2BooleanLinkedOpenHashMap.this.link[this.next] ^= (Byte2BooleanLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  853: 853 */      int pos = this.curr;
/*  854:     */      int last;
/*  855:     */      for (;;) {
/*  856: 856 */        pos = (last = pos) + 1 & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  857: 857 */        while (Byte2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  858: 858 */          int slot = HashCommon.murmurHash3(Byte2BooleanLinkedOpenHashMap.this.key[pos]) & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  859: 859 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  860: 860 */          pos = pos + 1 & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  861:     */        }
/*  862: 862 */        if (Byte2BooleanLinkedOpenHashMap.this.used[pos] == 0) break;
/*  863: 863 */        Byte2BooleanLinkedOpenHashMap.this.key[last] = Byte2BooleanLinkedOpenHashMap.this.key[pos];
/*  864: 864 */        Byte2BooleanLinkedOpenHashMap.this.value[last] = Byte2BooleanLinkedOpenHashMap.this.value[pos];
/*  865: 865 */        if (this.next == pos) this.next = last;
/*  866: 866 */        if (this.prev == pos) this.prev = last;
/*  867: 867 */        Byte2BooleanLinkedOpenHashMap.this.fixPointers(pos, last);
/*  868:     */      }
/*  869: 869 */      Byte2BooleanLinkedOpenHashMap.this.used[last] = false;
/*  870: 870 */      this.curr = -1;
/*  871:     */    }
/*  872:     */    
/*  873: 873 */    public int skip(int n) { int i = n;
/*  874: 874 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  875: 875 */      return n - i - 1;
/*  876:     */    }
/*  877:     */    
/*  878: 878 */    public int back(int n) { int i = n;
/*  879: 879 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  880: 880 */      return n - i - 1;
/*  881:     */    } }
/*  882:     */  
/*  883:     */  private class EntryIterator extends Byte2BooleanLinkedOpenHashMap.MapIterator implements ObjectListIterator<Byte2BooleanMap.Entry> { private Byte2BooleanLinkedOpenHashMap.MapEntry entry;
/*  884:     */    
/*  885: 885 */    public EntryIterator() { super(null); }
/*  886:     */    
/*  887: 887 */    public EntryIterator(byte from) { super(from, null); }
/*  888:     */    
/*  889:     */    public Byte2BooleanLinkedOpenHashMap.MapEntry next() {
/*  890: 890 */      return this.entry = new Byte2BooleanLinkedOpenHashMap.MapEntry(Byte2BooleanLinkedOpenHashMap.this, nextEntry());
/*  891:     */    }
/*  892:     */    
/*  893: 893 */    public Byte2BooleanLinkedOpenHashMap.MapEntry previous() { return this.entry = new Byte2BooleanLinkedOpenHashMap.MapEntry(Byte2BooleanLinkedOpenHashMap.this, previousEntry()); }
/*  894:     */    
/*  895:     */    public void remove()
/*  896:     */    {
/*  897: 897 */      super.remove();
/*  898: 898 */      Byte2BooleanLinkedOpenHashMap.MapEntry.access$202(this.entry, -1); }
/*  899:     */    
/*  900: 900 */    public void set(Byte2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/*  901: 901 */    public void add(Byte2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/*  902:     */  }
/*  903:     */  
/*  904: 904 */  private class FastEntryIterator extends Byte2BooleanLinkedOpenHashMap.MapIterator implements ObjectListIterator<Byte2BooleanMap.Entry> { final AbstractByte2BooleanMap.BasicEntry entry = new AbstractByte2BooleanMap.BasicEntry((byte)0, false);
/*  905: 905 */    public FastEntryIterator() { super(null); }
/*  906:     */    
/*  907: 907 */    public FastEntryIterator(byte from) { super(from, null); }
/*  908:     */    
/*  909:     */    public AbstractByte2BooleanMap.BasicEntry next() {
/*  910: 910 */      int e = nextEntry();
/*  911: 911 */      this.entry.key = Byte2BooleanLinkedOpenHashMap.this.key[e];
/*  912: 912 */      this.entry.value = Byte2BooleanLinkedOpenHashMap.this.value[e];
/*  913: 913 */      return this.entry;
/*  914:     */    }
/*  915:     */    
/*  916: 916 */    public AbstractByte2BooleanMap.BasicEntry previous() { int e = previousEntry();
/*  917: 917 */      this.entry.key = Byte2BooleanLinkedOpenHashMap.this.key[e];
/*  918: 918 */      this.entry.value = Byte2BooleanLinkedOpenHashMap.this.value[e];
/*  919: 919 */      return this.entry; }
/*  920:     */    
/*  921: 921 */    public void set(Byte2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/*  922: 922 */    public void add(Byte2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); } }
/*  923:     */  
/*  924:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Byte2BooleanMap.Entry> implements Byte2BooleanSortedMap.FastSortedEntrySet { private MapEntrySet() {}
/*  925:     */    
/*  926: 926 */    public ObjectBidirectionalIterator<Byte2BooleanMap.Entry> iterator() { return new Byte2BooleanLinkedOpenHashMap.EntryIterator(Byte2BooleanLinkedOpenHashMap.this); }
/*  927:     */    
/*  928: 928 */    public Comparator<? super Byte2BooleanMap.Entry> comparator() { return null; }
/*  929: 929 */    public ObjectSortedSet<Byte2BooleanMap.Entry> subSet(Byte2BooleanMap.Entry fromElement, Byte2BooleanMap.Entry toElement) { throw new UnsupportedOperationException(); }
/*  930: 930 */    public ObjectSortedSet<Byte2BooleanMap.Entry> headSet(Byte2BooleanMap.Entry toElement) { throw new UnsupportedOperationException(); }
/*  931: 931 */    public ObjectSortedSet<Byte2BooleanMap.Entry> tailSet(Byte2BooleanMap.Entry fromElement) { throw new UnsupportedOperationException(); }
/*  932:     */    
/*  933: 933 */    public Byte2BooleanMap.Entry first() { if (Byte2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  934: 934 */      return new Byte2BooleanLinkedOpenHashMap.MapEntry(Byte2BooleanLinkedOpenHashMap.this, Byte2BooleanLinkedOpenHashMap.this.first);
/*  935:     */    }
/*  936:     */    
/*  937: 937 */    public Byte2BooleanMap.Entry last() { if (Byte2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  938: 938 */      return new Byte2BooleanLinkedOpenHashMap.MapEntry(Byte2BooleanLinkedOpenHashMap.this, Byte2BooleanLinkedOpenHashMap.this.last);
/*  939:     */    }
/*  940:     */    
/*  941:     */    public boolean contains(Object o) {
/*  942: 942 */      if (!(o instanceof Map.Entry)) return false;
/*  943: 943 */      Map.Entry<Byte, Boolean> e = (Map.Entry)o;
/*  944: 944 */      byte k = ((Byte)e.getKey()).byteValue();
/*  945:     */      
/*  946: 946 */      int pos = HashCommon.murmurHash3(k) & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  947:     */      
/*  948: 948 */      while (Byte2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  949: 949 */        if (Byte2BooleanLinkedOpenHashMap.this.key[pos] == k) return Byte2BooleanLinkedOpenHashMap.this.value[pos] == ((Boolean)e.getValue()).booleanValue();
/*  950: 950 */        pos = pos + 1 & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  951:     */      }
/*  952: 952 */      return false;
/*  953:     */    }
/*  954:     */    
/*  955:     */    public boolean remove(Object o) {
/*  956: 956 */      if (!(o instanceof Map.Entry)) return false;
/*  957: 957 */      Map.Entry<Byte, Boolean> e = (Map.Entry)o;
/*  958: 958 */      byte k = ((Byte)e.getKey()).byteValue();
/*  959:     */      
/*  960: 960 */      int pos = HashCommon.murmurHash3(k) & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  961:     */      
/*  962: 962 */      while (Byte2BooleanLinkedOpenHashMap.this.used[pos] != 0) {
/*  963: 963 */        if (Byte2BooleanLinkedOpenHashMap.this.key[pos] == k) {
/*  964: 964 */          Byte2BooleanLinkedOpenHashMap.this.remove(e.getKey());
/*  965: 965 */          return true;
/*  966:     */        }
/*  967: 967 */        pos = pos + 1 & Byte2BooleanLinkedOpenHashMap.this.mask;
/*  968:     */      }
/*  969: 969 */      return false;
/*  970:     */    }
/*  971:     */    
/*  972: 972 */    public int size() { return Byte2BooleanLinkedOpenHashMap.this.size; }
/*  973:     */    
/*  974:     */    public void clear() {
/*  975: 975 */      Byte2BooleanLinkedOpenHashMap.this.clear();
/*  976:     */    }
/*  977:     */    
/*  978: 978 */    public ObjectBidirectionalIterator<Byte2BooleanMap.Entry> iterator(Byte2BooleanMap.Entry from) { return new Byte2BooleanLinkedOpenHashMap.EntryIterator(Byte2BooleanLinkedOpenHashMap.this, ((Byte)from.getKey()).byteValue()); }
/*  979:     */    
/*  980:     */    public ObjectBidirectionalIterator<Byte2BooleanMap.Entry> fastIterator() {
/*  981: 981 */      return new Byte2BooleanLinkedOpenHashMap.FastEntryIterator(Byte2BooleanLinkedOpenHashMap.this);
/*  982:     */    }
/*  983:     */    
/*  984: 984 */    public ObjectBidirectionalIterator<Byte2BooleanMap.Entry> fastIterator(Byte2BooleanMap.Entry from) { return new Byte2BooleanLinkedOpenHashMap.FastEntryIterator(Byte2BooleanLinkedOpenHashMap.this, ((Byte)from.getKey()).byteValue()); }
/*  985:     */  }
/*  986:     */  
/*  987:     */  public Byte2BooleanSortedMap.FastSortedEntrySet byte2BooleanEntrySet() {
/*  988: 988 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/*  989: 989 */    return this.entries;
/*  990:     */  }
/*  991:     */  
/*  994:     */  private final class KeyIterator
/*  995:     */    extends Byte2BooleanLinkedOpenHashMap.MapIterator
/*  996:     */    implements ByteListIterator
/*  997:     */  {
/*  998: 998 */    public KeyIterator(byte k) { super(k, null); }
/*  999: 999 */    public byte previousByte() { return Byte2BooleanLinkedOpenHashMap.this.key[previousEntry()]; }
/* 1000:1000 */    public void set(byte k) { throw new UnsupportedOperationException(); }
/* 1001:1001 */    public void add(byte k) { throw new UnsupportedOperationException(); }
/* 1002:1002 */    public Byte previous() { return Byte.valueOf(Byte2BooleanLinkedOpenHashMap.this.key[previousEntry()]); }
/* 1003:1003 */    public void set(Byte ok) { throw new UnsupportedOperationException(); }
/* 1004:1004 */    public void add(Byte ok) { throw new UnsupportedOperationException(); }
/* 1005:1005 */    public KeyIterator() { super(null); }
/* 1006:1006 */    public byte nextByte() { return Byte2BooleanLinkedOpenHashMap.this.key[nextEntry()]; }
/* 1007:1007 */    public Byte next() { return Byte.valueOf(Byte2BooleanLinkedOpenHashMap.this.key[nextEntry()]); } }
/* 1008:     */  
/* 1009:     */  private final class KeySet extends AbstractByteSortedSet { private KeySet() {}
/* 1010:     */    
/* 1011:1011 */    public ByteListIterator iterator(byte from) { return new Byte2BooleanLinkedOpenHashMap.KeyIterator(Byte2BooleanLinkedOpenHashMap.this, from); }
/* 1012:     */    
/* 1013:     */    public ByteListIterator iterator() {
/* 1014:1014 */      return new Byte2BooleanLinkedOpenHashMap.KeyIterator(Byte2BooleanLinkedOpenHashMap.this);
/* 1015:     */    }
/* 1016:     */    
/* 1017:1017 */    public int size() { return Byte2BooleanLinkedOpenHashMap.this.size; }
/* 1018:     */    
/* 1020:1020 */    public boolean contains(byte k) { return Byte2BooleanLinkedOpenHashMap.this.containsKey(k); }
/* 1021:     */    
/* 1022:     */    public boolean remove(byte k) {
/* 1023:1023 */      int oldSize = Byte2BooleanLinkedOpenHashMap.this.size;
/* 1024:1024 */      Byte2BooleanLinkedOpenHashMap.this.remove(k);
/* 1025:1025 */      return Byte2BooleanLinkedOpenHashMap.this.size != oldSize;
/* 1026:     */    }
/* 1027:     */    
/* 1028:1028 */    public void clear() { Byte2BooleanLinkedOpenHashMap.this.clear(); }
/* 1029:     */    
/* 1030:     */    public byte firstByte() {
/* 1031:1031 */      if (Byte2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1032:1032 */      return Byte2BooleanLinkedOpenHashMap.this.key[Byte2BooleanLinkedOpenHashMap.this.first];
/* 1033:     */    }
/* 1034:     */    
/* 1035:1035 */    public byte lastByte() { if (Byte2BooleanLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1036:1036 */      return Byte2BooleanLinkedOpenHashMap.this.key[Byte2BooleanLinkedOpenHashMap.this.last]; }
/* 1037:     */    
/* 1038:1038 */    public ByteComparator comparator() { return null; }
/* 1039:1039 */    public final ByteSortedSet tailSet(byte from) { throw new UnsupportedOperationException(); }
/* 1040:1040 */    public final ByteSortedSet headSet(byte to) { throw new UnsupportedOperationException(); }
/* 1041:1041 */    public final ByteSortedSet subSet(byte from, byte to) { throw new UnsupportedOperationException(); }
/* 1042:     */  }
/* 1043:     */  
/* 1044:1044 */  public ByteSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1045:1045 */    return this.keys;
/* 1046:     */  }
/* 1047:     */  
/* 1050:     */  private final class ValueIterator
/* 1051:     */    extends Byte2BooleanLinkedOpenHashMap.MapIterator
/* 1052:     */    implements BooleanListIterator
/* 1053:     */  {
/* 1054:1054 */    public boolean previousBoolean() { return Byte2BooleanLinkedOpenHashMap.this.value[previousEntry()]; }
/* 1055:1055 */    public Boolean previous() { return Boolean.valueOf(Byte2BooleanLinkedOpenHashMap.this.value[previousEntry()]); }
/* 1056:1056 */    public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1057:1057 */    public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1058:1058 */    public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1059:1059 */    public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1060:1060 */    public ValueIterator() { super(null); }
/* 1061:1061 */    public boolean nextBoolean() { return Byte2BooleanLinkedOpenHashMap.this.value[nextEntry()]; }
/* 1062:1062 */    public Boolean next() { return Boolean.valueOf(Byte2BooleanLinkedOpenHashMap.this.value[nextEntry()]); }
/* 1063:     */  }
/* 1064:     */  
/* 1065:1065 */  public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1066:     */        public BooleanIterator iterator() {
/* 1067:1067 */          return new Byte2BooleanLinkedOpenHashMap.ValueIterator(Byte2BooleanLinkedOpenHashMap.this);
/* 1068:     */        }
/* 1069:     */        
/* 1070:1070 */        public int size() { return Byte2BooleanLinkedOpenHashMap.this.size; }
/* 1071:     */        
/* 1072:     */        public boolean contains(boolean v) {
/* 1073:1073 */          return Byte2BooleanLinkedOpenHashMap.this.containsValue(v);
/* 1074:     */        }
/* 1075:     */        
/* 1076:1076 */        public void clear() { Byte2BooleanLinkedOpenHashMap.this.clear(); }
/* 1077:     */      };
/* 1078:     */    }
/* 1079:1079 */    return this.values;
/* 1080:     */  }
/* 1081:     */  
/* 1090:     */  @Deprecated
/* 1091:     */  public boolean rehash()
/* 1092:     */  {
/* 1093:1093 */    return true;
/* 1094:     */  }
/* 1095:     */  
/* 1106:     */  public boolean trim()
/* 1107:     */  {
/* 1108:1108 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1109:1109 */    if (l >= this.n) return true;
/* 1110:     */    try {
/* 1111:1111 */      rehash(l);
/* 1112:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1113:1113 */      return false; }
/* 1114:1114 */    return true;
/* 1115:     */  }
/* 1116:     */  
/* 1133:     */  public boolean trim(int n)
/* 1134:     */  {
/* 1135:1135 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1136:1136 */    if (this.n <= l) return true;
/* 1137:     */    try {
/* 1138:1138 */      rehash(l);
/* 1139:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1140:1140 */      return false; }
/* 1141:1141 */    return true;
/* 1142:     */  }
/* 1143:     */  
/* 1152:     */  protected void rehash(int newN)
/* 1153:     */  {
/* 1154:1154 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1155:     */    
/* 1156:1156 */    byte[] key = this.key;
/* 1157:1157 */    boolean[] value = this.value;
/* 1158:1158 */    int newMask = newN - 1;
/* 1159:1159 */    byte[] newKey = new byte[newN];
/* 1160:1160 */    boolean[] newValue = new boolean[newN];
/* 1161:1161 */    boolean[] newUsed = new boolean[newN];
/* 1162:1162 */    long[] link = this.link;
/* 1163:1163 */    long[] newLink = new long[newN];
/* 1164:1164 */    this.first = -1;
/* 1165:1165 */    for (int j = this.size; j-- != 0;) {
/* 1166:1166 */      byte k = key[i];
/* 1167:1167 */      int pos = HashCommon.murmurHash3(k) & newMask;
/* 1168:1168 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1169:1169 */      newUsed[pos] = true;
/* 1170:1170 */      newKey[pos] = k;
/* 1171:1171 */      newValue[pos] = value[i];
/* 1172:1172 */      if (prev != -1) {
/* 1173:1173 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1174:1174 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1175:1175 */        newPrev = pos;
/* 1176:     */      }
/* 1177:     */      else {
/* 1178:1178 */        newPrev = this.first = pos;
/* 1179:     */        
/* 1180:1180 */        newLink[pos] = -1L;
/* 1181:     */      }
/* 1182:1182 */      int t = i;
/* 1183:1183 */      i = (int)link[i];
/* 1184:1184 */      prev = t;
/* 1185:     */    }
/* 1186:1186 */    this.n = newN;
/* 1187:1187 */    this.mask = newMask;
/* 1188:1188 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1189:1189 */    this.key = newKey;
/* 1190:1190 */    this.value = newValue;
/* 1191:1191 */    this.used = newUsed;
/* 1192:1192 */    this.link = newLink;
/* 1193:1193 */    this.last = newPrev;
/* 1194:1194 */    if (newPrev != -1)
/* 1195:     */    {
/* 1196:1196 */      newLink[newPrev] |= 4294967295L;
/* 1197:     */    }
/* 1198:     */  }
/* 1199:     */  
/* 1202:     */  public Byte2BooleanLinkedOpenHashMap clone()
/* 1203:     */  {
/* 1204:     */    Byte2BooleanLinkedOpenHashMap c;
/* 1205:     */    
/* 1207:     */    try
/* 1208:     */    {
/* 1209:1209 */      c = (Byte2BooleanLinkedOpenHashMap)super.clone();
/* 1210:     */    }
/* 1211:     */    catch (CloneNotSupportedException cantHappen) {
/* 1212:1212 */      throw new InternalError();
/* 1213:     */    }
/* 1214:1214 */    c.keys = null;
/* 1215:1215 */    c.values = null;
/* 1216:1216 */    c.entries = null;
/* 1217:1217 */    c.key = ((byte[])this.key.clone());
/* 1218:1218 */    c.value = ((boolean[])this.value.clone());
/* 1219:1219 */    c.used = ((boolean[])this.used.clone());
/* 1220:1220 */    c.link = ((long[])this.link.clone());
/* 1221:1221 */    return c;
/* 1222:     */  }
/* 1223:     */  
/* 1231:     */  public int hashCode()
/* 1232:     */  {
/* 1233:1233 */    int h = 0;
/* 1234:1234 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1235:1235 */      while (this.used[i] == 0) i++;
/* 1236:1236 */      t = this.key[i];
/* 1237:1237 */      t ^= (this.value[i] != 0 ? 1231 : 1237);
/* 1238:1238 */      h += t;
/* 1239:1239 */      i++;
/* 1240:     */    }
/* 1241:1241 */    return h;
/* 1242:     */  }
/* 1243:     */  
/* 1244:1244 */  private void writeObject(ObjectOutputStream s) throws IOException { byte[] key = this.key;
/* 1245:1245 */    boolean[] value = this.value;
/* 1246:1246 */    MapIterator i = new MapIterator(null);
/* 1247:1247 */    s.defaultWriteObject();
/* 1248:1248 */    for (int j = this.size; j-- != 0;) {
/* 1249:1249 */      int e = i.nextEntry();
/* 1250:1250 */      s.writeByte(key[e]);
/* 1251:1251 */      s.writeBoolean(value[e]);
/* 1252:     */    }
/* 1253:     */  }
/* 1254:     */  
/* 1255:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1256:1256 */    s.defaultReadObject();
/* 1257:1257 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1258:1258 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1259:1259 */    this.mask = (this.n - 1);
/* 1260:1260 */    byte[] key = this.key = new byte[this.n];
/* 1261:1261 */    boolean[] value = this.value = new boolean[this.n];
/* 1262:1262 */    boolean[] used = this.used = new boolean[this.n];
/* 1263:1263 */    long[] link = this.link = new long[this.n];
/* 1264:1264 */    int prev = -1;
/* 1265:1265 */    this.first = (this.last = -1);
/* 1266:     */    
/* 1268:1268 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1269:1269 */      byte k = s.readByte();
/* 1270:1270 */      boolean v = s.readBoolean();
/* 1271:1271 */      pos = HashCommon.murmurHash3(k) & this.mask;
/* 1272:1272 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1273:1273 */      used[pos] = true;
/* 1274:1274 */      key[pos] = k;
/* 1275:1275 */      value[pos] = v;
/* 1276:1276 */      if (this.first != -1) {
/* 1277:1277 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1278:1278 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1279:1279 */        prev = pos;
/* 1280:     */      }
/* 1281:     */      else {
/* 1282:1282 */        prev = this.first = pos;
/* 1283:     */        
/* 1284:1284 */        link[pos] |= -4294967296L;
/* 1285:     */      }
/* 1286:     */    }
/* 1287:1287 */    this.last = prev;
/* 1288:1288 */    if (prev != -1)
/* 1289:     */    {
/* 1290:1290 */      link[prev] |= 4294967295L;
/* 1291:     */    }
/* 1292:     */  }
/* 1293:     */  
/* 1294:     */  private void checkTable() {}
/* 1295:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2BooleanLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */