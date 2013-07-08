/*    1:     */package it.unimi.dsi.fastutil.shorts;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash;
/*    4:     */import it.unimi.dsi.fastutil.HashCommon;
/*    5:     */import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*    6:     */import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*    7:     */import it.unimi.dsi.fastutil.floats.FloatCollection;
/*    8:     */import it.unimi.dsi.fastutil.floats.FloatIterator;
/*    9:     */import it.unimi.dsi.fastutil.floats.FloatListIterator;
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
/*  113:     */public class Short2FloatLinkedOpenHashMap
/*  114:     */  extends AbstractShort2FloatSortedMap
/*  115:     */  implements Serializable, Cloneable, Hash
/*  116:     */{
/*  117:     */  public static final long serialVersionUID = 0L;
/*  118:     */  private static final boolean ASSERTS = false;
/*  119:     */  protected transient short[] key;
/*  120:     */  protected transient float[] value;
/*  121:     */  protected transient boolean[] used;
/*  122:     */  protected final float f;
/*  123:     */  protected transient int n;
/*  124:     */  protected transient int maxFill;
/*  125:     */  protected transient int mask;
/*  126:     */  protected int size;
/*  127:     */  protected volatile transient Short2FloatSortedMap.FastSortedEntrySet entries;
/*  128:     */  protected volatile transient ShortSortedSet keys;
/*  129:     */  protected volatile transient FloatCollection values;
/*  130: 130 */  protected transient int first = -1;
/*  131:     */  
/*  132: 132 */  protected transient int last = -1;
/*  133:     */  
/*  140:     */  protected transient long[] link;
/*  141:     */  
/*  149:     */  public Short2FloatLinkedOpenHashMap(int expected, float f)
/*  150:     */  {
/*  151: 151 */    if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152: 152 */    if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153: 153 */    this.f = f;
/*  154: 154 */    this.n = HashCommon.arraySize(expected, f);
/*  155: 155 */    this.mask = (this.n - 1);
/*  156: 156 */    this.maxFill = HashCommon.maxFill(this.n, f);
/*  157: 157 */    this.key = new short[this.n];
/*  158: 158 */    this.value = new float[this.n];
/*  159: 159 */    this.used = new boolean[this.n];
/*  160: 160 */    this.link = new long[this.n];
/*  161:     */  }
/*  162:     */  
/*  165:     */  public Short2FloatLinkedOpenHashMap(int expected)
/*  166:     */  {
/*  167: 167 */    this(expected, 0.75F);
/*  168:     */  }
/*  169:     */  
/*  171:     */  public Short2FloatLinkedOpenHashMap()
/*  172:     */  {
/*  173: 173 */    this(16, 0.75F);
/*  174:     */  }
/*  175:     */  
/*  179:     */  public Short2FloatLinkedOpenHashMap(Map<? extends Short, ? extends Float> m, float f)
/*  180:     */  {
/*  181: 181 */    this(m.size(), f);
/*  182: 182 */    putAll(m);
/*  183:     */  }
/*  184:     */  
/*  187:     */  public Short2FloatLinkedOpenHashMap(Map<? extends Short, ? extends Float> m)
/*  188:     */  {
/*  189: 189 */    this(m, 0.75F);
/*  190:     */  }
/*  191:     */  
/*  195:     */  public Short2FloatLinkedOpenHashMap(Short2FloatMap m, float f)
/*  196:     */  {
/*  197: 197 */    this(m.size(), f);
/*  198: 198 */    putAll(m);
/*  199:     */  }
/*  200:     */  
/*  203:     */  public Short2FloatLinkedOpenHashMap(Short2FloatMap m)
/*  204:     */  {
/*  205: 205 */    this(m, 0.75F);
/*  206:     */  }
/*  207:     */  
/*  213:     */  public Short2FloatLinkedOpenHashMap(short[] k, float[] v, float f)
/*  214:     */  {
/*  215: 215 */    this(k.length, f);
/*  216: 216 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217: 217 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  218:     */    }
/*  219:     */  }
/*  220:     */  
/*  224:     */  public Short2FloatLinkedOpenHashMap(short[] k, float[] v)
/*  225:     */  {
/*  226: 226 */    this(k, v, 0.75F);
/*  227:     */  }
/*  228:     */  
/*  232:     */  public float put(short k, float v)
/*  233:     */  {
/*  234: 234 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  235:     */    
/*  236: 236 */    while (this.used[pos] != 0) {
/*  237: 237 */      if (this.key[pos] == k) {
/*  238: 238 */        float oldValue = this.value[pos];
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
/*  262: 262 */  public Float put(Short ok, Float ov) { float v = ov.floatValue();
/*  263: 263 */    short k = ok.shortValue();
/*  264:     */    
/*  265: 265 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  266:     */    
/*  267: 267 */    while (this.used[pos] != 0) {
/*  268: 268 */      if (this.key[pos] == k) {
/*  269: 269 */        Float oldValue = Float.valueOf(this.value[pos]);
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
/*  303:     */  public float add(short k, float incr)
/*  304:     */  {
/*  305: 305 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  306:     */    
/*  307: 307 */    while (this.used[pos] != 0) {
/*  308: 308 */      if (this.key[pos] == k) {
/*  309: 309 */        float oldValue = this.value[pos];
/*  310: 310 */        this.value[pos] += incr;
/*  311: 311 */        return oldValue;
/*  312:     */      }
/*  313: 313 */      pos = pos + 1 & this.mask;
/*  314:     */    }
/*  315: 315 */    this.used[pos] = true;
/*  316: 316 */    this.key[pos] = k;
/*  317: 317 */    this.value[pos] = (this.defRetValue + incr);
/*  318: 318 */    if (this.size == 0) {
/*  319: 319 */      this.first = (this.last = pos);
/*  320:     */      
/*  321: 321 */      this.link[pos] = -1L;
/*  322:     */    }
/*  323:     */    else {
/*  324: 324 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  325: 325 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  326: 326 */      this.last = pos;
/*  327:     */    }
/*  328: 328 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size + 1, this.f));
/*  329:     */    }
/*  330: 330 */    return this.defRetValue;
/*  331:     */  }
/*  332:     */  
/*  335:     */  protected final int shiftKeys(int pos)
/*  336:     */  {
/*  337:     */    int last;
/*  338:     */    
/*  340:     */    for (;;)
/*  341:     */    {
/*  342: 342 */      pos = (last = pos) + 1 & this.mask;
/*  343: 343 */      while (this.used[pos] != 0) {
/*  344: 344 */        int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/*  345: 345 */        if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  346: 346 */        pos = pos + 1 & this.mask;
/*  347:     */      }
/*  348: 348 */      if (this.used[pos] == 0) break;
/*  349: 349 */      this.key[last] = this.key[pos];
/*  350: 350 */      this.value[last] = this.value[pos];
/*  351: 351 */      fixPointers(pos, last);
/*  352:     */    }
/*  353: 353 */    this.used[last] = false;
/*  354: 354 */    return last;
/*  355:     */  }
/*  356:     */  
/*  357:     */  public float remove(short k)
/*  358:     */  {
/*  359: 359 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  360:     */    
/*  361: 361 */    while (this.used[pos] != 0) {
/*  362: 362 */      if (this.key[pos] == k) {
/*  363: 363 */        this.size -= 1;
/*  364: 364 */        fixPointers(pos);
/*  365: 365 */        float v = this.value[pos];
/*  366: 366 */        shiftKeys(pos);
/*  367: 367 */        return v;
/*  368:     */      }
/*  369: 369 */      pos = pos + 1 & this.mask;
/*  370:     */    }
/*  371: 371 */    return this.defRetValue;
/*  372:     */  }
/*  373:     */  
/*  374:     */  public Float remove(Object ok) {
/*  375: 375 */    short k = ((Short)ok).shortValue();
/*  376:     */    
/*  377: 377 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  378:     */    
/*  379: 379 */    while (this.used[pos] != 0) {
/*  380: 380 */      if (this.key[pos] == k) {
/*  381: 381 */        this.size -= 1;
/*  382: 382 */        fixPointers(pos);
/*  383: 383 */        float v = this.value[pos];
/*  384: 384 */        shiftKeys(pos);
/*  385: 385 */        return Float.valueOf(v);
/*  386:     */      }
/*  387: 387 */      pos = pos + 1 & this.mask;
/*  388:     */    }
/*  389: 389 */    return null;
/*  390:     */  }
/*  391:     */  
/*  394:     */  public float removeFirstFloat()
/*  395:     */  {
/*  396: 396 */    if (this.size == 0) throw new NoSuchElementException();
/*  397: 397 */    this.size -= 1;
/*  398: 398 */    int pos = this.first;
/*  399:     */    
/*  400: 400 */    this.first = ((int)this.link[pos]);
/*  401: 401 */    if (0 <= this.first)
/*  402:     */    {
/*  403: 403 */      this.link[this.first] |= -4294967296L;
/*  404:     */    }
/*  405: 405 */    float v = this.value[pos];
/*  406: 406 */    shiftKeys(pos);
/*  407: 407 */    return v;
/*  408:     */  }
/*  409:     */  
/*  412:     */  public float removeLastFloat()
/*  413:     */  {
/*  414: 414 */    if (this.size == 0) throw new NoSuchElementException();
/*  415: 415 */    this.size -= 1;
/*  416: 416 */    int pos = this.last;
/*  417:     */    
/*  418: 418 */    this.last = ((int)(this.link[pos] >>> 32));
/*  419: 419 */    if (0 <= this.last)
/*  420:     */    {
/*  421: 421 */      this.link[this.last] |= 4294967295L;
/*  422:     */    }
/*  423: 423 */    float v = this.value[pos];
/*  424: 424 */    shiftKeys(pos);
/*  425: 425 */    return v;
/*  426:     */  }
/*  427:     */  
/*  428: 428 */  private void moveIndexToFirst(int i) { if ((this.size == 1) || (this.first == i)) return;
/*  429: 429 */    if (this.last == i) {
/*  430: 430 */      this.last = ((int)(this.link[i] >>> 32));
/*  431:     */      
/*  432: 432 */      this.link[this.last] |= 4294967295L;
/*  433:     */    }
/*  434:     */    else {
/*  435: 435 */      long linki = this.link[i];
/*  436: 436 */      int prev = (int)(linki >>> 32);
/*  437: 437 */      int next = (int)linki;
/*  438: 438 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  439: 439 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  440:     */    }
/*  441: 441 */    this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  442: 442 */    this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  443: 443 */    this.first = i;
/*  444:     */  }
/*  445:     */  
/*  446: 446 */  private void moveIndexToLast(int i) { if ((this.size == 1) || (this.last == i)) return;
/*  447: 447 */    if (this.first == i) {
/*  448: 448 */      this.first = ((int)this.link[i]);
/*  449:     */      
/*  450: 450 */      this.link[this.first] |= -4294967296L;
/*  451:     */    }
/*  452:     */    else {
/*  453: 453 */      long linki = this.link[i];
/*  454: 454 */      int prev = (int)(linki >>> 32);
/*  455: 455 */      int next = (int)linki;
/*  456: 456 */      this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  457: 457 */      this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  458:     */    }
/*  459: 459 */    this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  460: 460 */    this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  461: 461 */    this.last = i;
/*  462:     */  }
/*  463:     */  
/*  467:     */  public float getAndMoveToFirst(short k)
/*  468:     */  {
/*  469: 469 */    short[] key = this.key;
/*  470: 470 */    boolean[] used = this.used;
/*  471: 471 */    int mask = this.mask;
/*  472:     */    
/*  473: 473 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  474:     */    
/*  475: 475 */    while (used[pos] != 0) {
/*  476: 476 */      if (k == key[pos]) {
/*  477: 477 */        moveIndexToFirst(pos);
/*  478: 478 */        return this.value[pos];
/*  479:     */      }
/*  480: 480 */      pos = pos + 1 & mask;
/*  481:     */    }
/*  482: 482 */    return this.defRetValue;
/*  483:     */  }
/*  484:     */  
/*  488:     */  public float getAndMoveToLast(short k)
/*  489:     */  {
/*  490: 490 */    short[] key = this.key;
/*  491: 491 */    boolean[] used = this.used;
/*  492: 492 */    int mask = this.mask;
/*  493:     */    
/*  494: 494 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  495:     */    
/*  496: 496 */    while (used[pos] != 0) {
/*  497: 497 */      if (k == key[pos]) {
/*  498: 498 */        moveIndexToLast(pos);
/*  499: 499 */        return this.value[pos];
/*  500:     */      }
/*  501: 501 */      pos = pos + 1 & mask;
/*  502:     */    }
/*  503: 503 */    return this.defRetValue;
/*  504:     */  }
/*  505:     */  
/*  510:     */  public float putAndMoveToFirst(short k, float v)
/*  511:     */  {
/*  512: 512 */    short[] key = this.key;
/*  513: 513 */    boolean[] used = this.used;
/*  514: 514 */    int mask = this.mask;
/*  515:     */    
/*  516: 516 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  517:     */    
/*  518: 518 */    while (used[pos] != 0) {
/*  519: 519 */      if (k == key[pos]) {
/*  520: 520 */        float oldValue = this.value[pos];
/*  521: 521 */        this.value[pos] = v;
/*  522: 522 */        moveIndexToFirst(pos);
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
/*  536: 536 */      this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  537: 537 */      this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  538: 538 */      this.first = pos;
/*  539:     */    }
/*  540: 540 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  541:     */    }
/*  542: 542 */    return this.defRetValue;
/*  543:     */  }
/*  544:     */  
/*  549:     */  public float putAndMoveToLast(short k, float v)
/*  550:     */  {
/*  551: 551 */    short[] key = this.key;
/*  552: 552 */    boolean[] used = this.used;
/*  553: 553 */    int mask = this.mask;
/*  554:     */    
/*  555: 555 */    int pos = HashCommon.murmurHash3(k) & mask;
/*  556:     */    
/*  557: 557 */    while (used[pos] != 0) {
/*  558: 558 */      if (k == key[pos]) {
/*  559: 559 */        float oldValue = this.value[pos];
/*  560: 560 */        this.value[pos] = v;
/*  561: 561 */        moveIndexToLast(pos);
/*  562: 562 */        return oldValue;
/*  563:     */      }
/*  564: 564 */      pos = pos + 1 & mask;
/*  565:     */    }
/*  566: 566 */    used[pos] = true;
/*  567: 567 */    key[pos] = k;
/*  568: 568 */    this.value[pos] = v;
/*  569: 569 */    if (this.size == 0) {
/*  570: 570 */      this.first = (this.last = pos);
/*  571:     */      
/*  572: 572 */      this.link[pos] = -1L;
/*  573:     */    }
/*  574:     */    else {
/*  575: 575 */      this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  576: 576 */      this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  577: 577 */      this.last = pos;
/*  578:     */    }
/*  579: 579 */    if (++this.size >= this.maxFill) { rehash(HashCommon.arraySize(this.size, this.f));
/*  580:     */    }
/*  581: 581 */    return this.defRetValue;
/*  582:     */  }
/*  583:     */  
/*  584: 584 */  public Float get(Short ok) { short k = ok.shortValue();
/*  585:     */    
/*  586: 586 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  587:     */    
/*  588: 588 */    while (this.used[pos] != 0) {
/*  589: 589 */      if (this.key[pos] == k) return Float.valueOf(this.value[pos]);
/*  590: 590 */      pos = pos + 1 & this.mask;
/*  591:     */    }
/*  592: 592 */    return null;
/*  593:     */  }
/*  594:     */  
/*  595:     */  public float get(short k)
/*  596:     */  {
/*  597: 597 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  598:     */    
/*  599: 599 */    while (this.used[pos] != 0) {
/*  600: 600 */      if (this.key[pos] == k) return this.value[pos];
/*  601: 601 */      pos = pos + 1 & this.mask;
/*  602:     */    }
/*  603: 603 */    return this.defRetValue;
/*  604:     */  }
/*  605:     */  
/*  606:     */  public boolean containsKey(short k)
/*  607:     */  {
/*  608: 608 */    int pos = HashCommon.murmurHash3(k) & this.mask;
/*  609:     */    
/*  610: 610 */    while (this.used[pos] != 0) {
/*  611: 611 */      if (this.key[pos] == k) return true;
/*  612: 612 */      pos = pos + 1 & this.mask;
/*  613:     */    }
/*  614: 614 */    return false;
/*  615:     */  }
/*  616:     */  
/*  617: 617 */  public boolean containsValue(float v) { float[] value = this.value;
/*  618: 618 */    boolean[] used = this.used;
/*  619: 619 */    for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v)) break label16;
/*  620: 620 */    return false;
/*  621:     */  }
/*  622:     */  
/*  627:     */  public void clear()
/*  628:     */  {
/*  629: 629 */    if (this.size == 0) return;
/*  630: 630 */    this.size = 0;
/*  631: 631 */    BooleanArrays.fill(this.used, false);
/*  632:     */    
/*  633: 633 */    this.first = (this.last = -1);
/*  634:     */  }
/*  635:     */  
/*  636: 636 */  public int size() { return this.size; }
/*  637:     */  
/*  638:     */  public boolean isEmpty() {
/*  639: 639 */    return this.size == 0;
/*  640:     */  }
/*  641:     */  
/*  646:     */  @Deprecated
/*  647:     */  public void growthFactor(int growthFactor) {}
/*  648:     */  
/*  653:     */  @Deprecated
/*  654:     */  public int growthFactor()
/*  655:     */  {
/*  656: 656 */    return 16;
/*  657:     */  }
/*  658:     */  
/*  659:     */  private final class MapEntry
/*  660:     */    implements Short2FloatMap.Entry, Map.Entry<Short, Float>
/*  661:     */  {
/*  662:     */    private int index;
/*  663:     */    
/*  664:     */    MapEntry(int index)
/*  665:     */    {
/*  666: 666 */      this.index = index;
/*  667:     */    }
/*  668:     */    
/*  669: 669 */    public Short getKey() { return Short.valueOf(Short2FloatLinkedOpenHashMap.this.key[this.index]); }
/*  670:     */    
/*  671:     */    public short getShortKey() {
/*  672: 672 */      return Short2FloatLinkedOpenHashMap.this.key[this.index];
/*  673:     */    }
/*  674:     */    
/*  675: 675 */    public Float getValue() { return Float.valueOf(Short2FloatLinkedOpenHashMap.this.value[this.index]); }
/*  676:     */    
/*  678: 678 */    public float getFloatValue() { return Short2FloatLinkedOpenHashMap.this.value[this.index]; }
/*  679:     */    
/*  680:     */    public float setValue(float v) {
/*  681: 681 */      float oldValue = Short2FloatLinkedOpenHashMap.this.value[this.index];
/*  682: 682 */      Short2FloatLinkedOpenHashMap.this.value[this.index] = v;
/*  683: 683 */      return oldValue;
/*  684:     */    }
/*  685:     */    
/*  686: 686 */    public Float setValue(Float v) { return Float.valueOf(setValue(v.floatValue())); }
/*  687:     */    
/*  688:     */    public boolean equals(Object o)
/*  689:     */    {
/*  690: 690 */      if (!(o instanceof Map.Entry)) return false;
/*  691: 691 */      Map.Entry<Short, Float> e = (Map.Entry)o;
/*  692: 692 */      return (Short2FloatLinkedOpenHashMap.this.key[this.index] == ((Short)e.getKey()).shortValue()) && (Short2FloatLinkedOpenHashMap.this.value[this.index] == ((Float)e.getValue()).floatValue());
/*  693:     */    }
/*  694:     */    
/*  695: 695 */    public int hashCode() { return Short2FloatLinkedOpenHashMap.this.key[this.index] ^ HashCommon.float2int(Short2FloatLinkedOpenHashMap.this.value[this.index]); }
/*  696:     */    
/*  697:     */    public String toString() {
/*  698: 698 */      return Short2FloatLinkedOpenHashMap.this.key[this.index] + "=>" + Short2FloatLinkedOpenHashMap.this.value[this.index];
/*  699:     */    }
/*  700:     */  }
/*  701:     */  
/*  707:     */  protected void fixPointers(int i)
/*  708:     */  {
/*  709: 709 */    if (this.size == 0) {
/*  710: 710 */      this.first = (this.last = -1);
/*  711: 711 */      return;
/*  712:     */    }
/*  713: 713 */    if (this.first == i) {
/*  714: 714 */      this.first = ((int)this.link[i]);
/*  715: 715 */      if (0 <= this.first)
/*  716:     */      {
/*  717: 717 */        this.link[this.first] |= -4294967296L;
/*  718:     */      }
/*  719: 719 */      return;
/*  720:     */    }
/*  721: 721 */    if (this.last == i) {
/*  722: 722 */      this.last = ((int)(this.link[i] >>> 32));
/*  723: 723 */      if (0 <= this.last)
/*  724:     */      {
/*  725: 725 */        this.link[this.last] |= 4294967295L;
/*  726:     */      }
/*  727: 727 */      return;
/*  728:     */    }
/*  729: 729 */    long linki = this.link[i];
/*  730: 730 */    int prev = (int)(linki >>> 32);
/*  731: 731 */    int next = (int)linki;
/*  732: 732 */    this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  733: 733 */    this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*  734:     */  }
/*  735:     */  
/*  742:     */  protected void fixPointers(int s, int d)
/*  743:     */  {
/*  744: 744 */    if (this.size == 1) {
/*  745: 745 */      this.first = (this.last = d);
/*  746:     */      
/*  747: 747 */      this.link[d] = -1L;
/*  748: 748 */      return;
/*  749:     */    }
/*  750: 750 */    if (this.first == s) {
/*  751: 751 */      this.first = d;
/*  752: 752 */      this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  753: 753 */      this.link[d] = this.link[s];
/*  754: 754 */      return;
/*  755:     */    }
/*  756: 756 */    if (this.last == s) {
/*  757: 757 */      this.last = d;
/*  758: 758 */      this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  759: 759 */      this.link[d] = this.link[s];
/*  760: 760 */      return;
/*  761:     */    }
/*  762: 762 */    long links = this.link[s];
/*  763: 763 */    int prev = (int)(links >>> 32);
/*  764: 764 */    int next = (int)links;
/*  765: 765 */    this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  766: 766 */    this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  767: 767 */    this.link[d] = links;
/*  768:     */  }
/*  769:     */  
/*  772:     */  public short firstShortKey()
/*  773:     */  {
/*  774: 774 */    if (this.size == 0) throw new NoSuchElementException();
/*  775: 775 */    return this.key[this.first];
/*  776:     */  }
/*  777:     */  
/*  780:     */  public short lastShortKey()
/*  781:     */  {
/*  782: 782 */    if (this.size == 0) throw new NoSuchElementException();
/*  783: 783 */    return this.key[this.last]; }
/*  784:     */  
/*  785: 785 */  public ShortComparator comparator() { return null; }
/*  786: 786 */  public Short2FloatSortedMap tailMap(short from) { throw new UnsupportedOperationException(); }
/*  787: 787 */  public Short2FloatSortedMap headMap(short to) { throw new UnsupportedOperationException(); }
/*  788: 788 */  public Short2FloatSortedMap subMap(short from, short to) { throw new UnsupportedOperationException(); }
/*  789:     */  
/*  795:     */  private class MapIterator
/*  796:     */  {
/*  797: 797 */    int prev = -1;
/*  798:     */    
/*  799: 799 */    int next = -1;
/*  800:     */    
/*  801: 801 */    int curr = -1;
/*  802:     */    
/*  803: 803 */    int index = -1;
/*  804:     */    
/*  805: 805 */    private MapIterator() { this.next = Short2FloatLinkedOpenHashMap.this.first;
/*  806: 806 */      this.index = 0;
/*  807:     */    }
/*  808:     */    
/*  809: 809 */    private MapIterator(short from) { if (Short2FloatLinkedOpenHashMap.this.key[Short2FloatLinkedOpenHashMap.this.last] == from) {
/*  810: 810 */        this.prev = Short2FloatLinkedOpenHashMap.this.last;
/*  811: 811 */        this.index = Short2FloatLinkedOpenHashMap.this.size;
/*  812:     */      }
/*  813:     */      else
/*  814:     */      {
/*  815: 815 */        int pos = HashCommon.murmurHash3(from) & Short2FloatLinkedOpenHashMap.this.mask;
/*  816:     */        
/*  817: 817 */        while (Short2FloatLinkedOpenHashMap.this.used[pos] != 0) {
/*  818: 818 */          if (Short2FloatLinkedOpenHashMap.this.key[pos] == from)
/*  819:     */          {
/*  820: 820 */            this.next = ((int)Short2FloatLinkedOpenHashMap.this.link[pos]);
/*  821: 821 */            this.prev = pos;
/*  822: 822 */            return;
/*  823:     */          }
/*  824: 824 */          pos = pos + 1 & Short2FloatLinkedOpenHashMap.this.mask;
/*  825:     */        }
/*  826: 826 */        throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*  827:     */      } }
/*  828:     */    
/*  829: 829 */    public boolean hasNext() { return this.next != -1; }
/*  830: 830 */    public boolean hasPrevious() { return this.prev != -1; }
/*  831:     */    
/*  832: 832 */    private final void ensureIndexKnown() { if (this.index >= 0) return;
/*  833: 833 */      if (this.prev == -1) {
/*  834: 834 */        this.index = 0;
/*  835: 835 */        return;
/*  836:     */      }
/*  837: 837 */      if (this.next == -1) {
/*  838: 838 */        this.index = Short2FloatLinkedOpenHashMap.this.size;
/*  839: 839 */        return;
/*  840:     */      }
/*  841: 841 */      int pos = Short2FloatLinkedOpenHashMap.this.first;
/*  842: 842 */      this.index = 1;
/*  843: 843 */      while (pos != this.prev) {
/*  844: 844 */        pos = (int)Short2FloatLinkedOpenHashMap.this.link[pos];
/*  845: 845 */        this.index += 1;
/*  846:     */      }
/*  847:     */    }
/*  848:     */    
/*  849: 849 */    public int nextIndex() { ensureIndexKnown();
/*  850: 850 */      return this.index;
/*  851:     */    }
/*  852:     */    
/*  853: 853 */    public int previousIndex() { ensureIndexKnown();
/*  854: 854 */      return this.index - 1;
/*  855:     */    }
/*  856:     */    
/*  857: 857 */    public int nextEntry() { if (!hasNext()) return Short2FloatLinkedOpenHashMap.this.size();
/*  858: 858 */      this.curr = this.next;
/*  859: 859 */      this.next = ((int)Short2FloatLinkedOpenHashMap.this.link[this.curr]);
/*  860: 860 */      this.prev = this.curr;
/*  861: 861 */      if (this.index >= 0) this.index += 1;
/*  862: 862 */      return this.curr;
/*  863:     */    }
/*  864:     */    
/*  865: 865 */    public int previousEntry() { if (!hasPrevious()) return -1;
/*  866: 866 */      this.curr = this.prev;
/*  867: 867 */      this.prev = ((int)(Short2FloatLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  868: 868 */      this.next = this.curr;
/*  869: 869 */      if (this.index >= 0) this.index -= 1;
/*  870: 870 */      return this.curr;
/*  871:     */    }
/*  872:     */    
/*  873:     */    public void remove() {
/*  874: 874 */      ensureIndexKnown();
/*  875: 875 */      if (this.curr == -1) throw new IllegalStateException();
/*  876: 876 */      if (this.curr == this.prev)
/*  877:     */      {
/*  879: 879 */        this.index -= 1;
/*  880: 880 */        this.prev = ((int)(Short2FloatLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  881:     */      }
/*  882:     */      else {
/*  883: 883 */        this.next = ((int)Short2FloatLinkedOpenHashMap.this.link[this.curr]); }
/*  884: 884 */      Short2FloatLinkedOpenHashMap.this.size -= 1;
/*  885:     */      
/*  887: 887 */      if (this.prev == -1) { Short2FloatLinkedOpenHashMap.this.first = this.next;
/*  888:     */      } else
/*  889: 889 */        Short2FloatLinkedOpenHashMap.this.link[this.prev] ^= (Short2FloatLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  890: 890 */      if (this.next == -1) { Short2FloatLinkedOpenHashMap.this.last = this.prev;
/*  891:     */      } else
/*  892: 892 */        Short2FloatLinkedOpenHashMap.this.link[this.next] ^= (Short2FloatLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0;
/*  893: 893 */      int pos = this.curr;
/*  894:     */      int last;
/*  895:     */      for (;;) {
/*  896: 896 */        pos = (last = pos) + 1 & Short2FloatLinkedOpenHashMap.this.mask;
/*  897: 897 */        while (Short2FloatLinkedOpenHashMap.this.used[pos] != 0) {
/*  898: 898 */          int slot = HashCommon.murmurHash3(Short2FloatLinkedOpenHashMap.this.key[pos]) & Short2FloatLinkedOpenHashMap.this.mask;
/*  899: 899 */          if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  900: 900 */          pos = pos + 1 & Short2FloatLinkedOpenHashMap.this.mask;
/*  901:     */        }
/*  902: 902 */        if (Short2FloatLinkedOpenHashMap.this.used[pos] == 0) break;
/*  903: 903 */        Short2FloatLinkedOpenHashMap.this.key[last] = Short2FloatLinkedOpenHashMap.this.key[pos];
/*  904: 904 */        Short2FloatLinkedOpenHashMap.this.value[last] = Short2FloatLinkedOpenHashMap.this.value[pos];
/*  905: 905 */        if (this.next == pos) this.next = last;
/*  906: 906 */        if (this.prev == pos) this.prev = last;
/*  907: 907 */        Short2FloatLinkedOpenHashMap.this.fixPointers(pos, last);
/*  908:     */      }
/*  909: 909 */      Short2FloatLinkedOpenHashMap.this.used[last] = false;
/*  910: 910 */      this.curr = -1;
/*  911:     */    }
/*  912:     */    
/*  913: 913 */    public int skip(int n) { int i = n;
/*  914: 914 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  915: 915 */      return n - i - 1;
/*  916:     */    }
/*  917:     */    
/*  918: 918 */    public int back(int n) { int i = n;
/*  919: 919 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  920: 920 */      return n - i - 1;
/*  921:     */    } }
/*  922:     */  
/*  923:     */  private class EntryIterator extends Short2FloatLinkedOpenHashMap.MapIterator implements ObjectListIterator<Short2FloatMap.Entry> { private Short2FloatLinkedOpenHashMap.MapEntry entry;
/*  924:     */    
/*  925: 925 */    public EntryIterator() { super(null); }
/*  926:     */    
/*  927: 927 */    public EntryIterator(short from) { super(from, null); }
/*  928:     */    
/*  929:     */    public Short2FloatLinkedOpenHashMap.MapEntry next() {
/*  930: 930 */      return this.entry = new Short2FloatLinkedOpenHashMap.MapEntry(Short2FloatLinkedOpenHashMap.this, nextEntry());
/*  931:     */    }
/*  932:     */    
/*  933: 933 */    public Short2FloatLinkedOpenHashMap.MapEntry previous() { return this.entry = new Short2FloatLinkedOpenHashMap.MapEntry(Short2FloatLinkedOpenHashMap.this, previousEntry()); }
/*  934:     */    
/*  935:     */    public void remove()
/*  936:     */    {
/*  937: 937 */      super.remove();
/*  938: 938 */      Short2FloatLinkedOpenHashMap.MapEntry.access$202(this.entry, -1); }
/*  939:     */    
/*  940: 940 */    public void set(Short2FloatMap.Entry ok) { throw new UnsupportedOperationException(); }
/*  941: 941 */    public void add(Short2FloatMap.Entry ok) { throw new UnsupportedOperationException(); }
/*  942:     */  }
/*  943:     */  
/*  944: 944 */  private class FastEntryIterator extends Short2FloatLinkedOpenHashMap.MapIterator implements ObjectListIterator<Short2FloatMap.Entry> { final AbstractShort2FloatMap.BasicEntry entry = new AbstractShort2FloatMap.BasicEntry((short)0, 0.0F);
/*  945: 945 */    public FastEntryIterator() { super(null); }
/*  946:     */    
/*  947: 947 */    public FastEntryIterator(short from) { super(from, null); }
/*  948:     */    
/*  949:     */    public AbstractShort2FloatMap.BasicEntry next() {
/*  950: 950 */      int e = nextEntry();
/*  951: 951 */      this.entry.key = Short2FloatLinkedOpenHashMap.this.key[e];
/*  952: 952 */      this.entry.value = Short2FloatLinkedOpenHashMap.this.value[e];
/*  953: 953 */      return this.entry;
/*  954:     */    }
/*  955:     */    
/*  956: 956 */    public AbstractShort2FloatMap.BasicEntry previous() { int e = previousEntry();
/*  957: 957 */      this.entry.key = Short2FloatLinkedOpenHashMap.this.key[e];
/*  958: 958 */      this.entry.value = Short2FloatLinkedOpenHashMap.this.value[e];
/*  959: 959 */      return this.entry; }
/*  960:     */    
/*  961: 961 */    public void set(Short2FloatMap.Entry ok) { throw new UnsupportedOperationException(); }
/*  962: 962 */    public void add(Short2FloatMap.Entry ok) { throw new UnsupportedOperationException(); } }
/*  963:     */  
/*  964:     */  private final class MapEntrySet extends AbstractObjectSortedSet<Short2FloatMap.Entry> implements Short2FloatSortedMap.FastSortedEntrySet { private MapEntrySet() {}
/*  965:     */    
/*  966: 966 */    public ObjectBidirectionalIterator<Short2FloatMap.Entry> iterator() { return new Short2FloatLinkedOpenHashMap.EntryIterator(Short2FloatLinkedOpenHashMap.this); }
/*  967:     */    
/*  968: 968 */    public Comparator<? super Short2FloatMap.Entry> comparator() { return null; }
/*  969: 969 */    public ObjectSortedSet<Short2FloatMap.Entry> subSet(Short2FloatMap.Entry fromElement, Short2FloatMap.Entry toElement) { throw new UnsupportedOperationException(); }
/*  970: 970 */    public ObjectSortedSet<Short2FloatMap.Entry> headSet(Short2FloatMap.Entry toElement) { throw new UnsupportedOperationException(); }
/*  971: 971 */    public ObjectSortedSet<Short2FloatMap.Entry> tailSet(Short2FloatMap.Entry fromElement) { throw new UnsupportedOperationException(); }
/*  972:     */    
/*  973: 973 */    public Short2FloatMap.Entry first() { if (Short2FloatLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  974: 974 */      return new Short2FloatLinkedOpenHashMap.MapEntry(Short2FloatLinkedOpenHashMap.this, Short2FloatLinkedOpenHashMap.this.first);
/*  975:     */    }
/*  976:     */    
/*  977: 977 */    public Short2FloatMap.Entry last() { if (Short2FloatLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  978: 978 */      return new Short2FloatLinkedOpenHashMap.MapEntry(Short2FloatLinkedOpenHashMap.this, Short2FloatLinkedOpenHashMap.this.last);
/*  979:     */    }
/*  980:     */    
/*  981:     */    public boolean contains(Object o) {
/*  982: 982 */      if (!(o instanceof Map.Entry)) return false;
/*  983: 983 */      Map.Entry<Short, Float> e = (Map.Entry)o;
/*  984: 984 */      short k = ((Short)e.getKey()).shortValue();
/*  985:     */      
/*  986: 986 */      int pos = HashCommon.murmurHash3(k) & Short2FloatLinkedOpenHashMap.this.mask;
/*  987:     */      
/*  988: 988 */      while (Short2FloatLinkedOpenHashMap.this.used[pos] != 0) {
/*  989: 989 */        if (Short2FloatLinkedOpenHashMap.this.key[pos] == k) return Short2FloatLinkedOpenHashMap.this.value[pos] == ((Float)e.getValue()).floatValue();
/*  990: 990 */        pos = pos + 1 & Short2FloatLinkedOpenHashMap.this.mask;
/*  991:     */      }
/*  992: 992 */      return false;
/*  993:     */    }
/*  994:     */    
/*  995:     */    public boolean remove(Object o) {
/*  996: 996 */      if (!(o instanceof Map.Entry)) return false;
/*  997: 997 */      Map.Entry<Short, Float> e = (Map.Entry)o;
/*  998: 998 */      short k = ((Short)e.getKey()).shortValue();
/*  999:     */      
/* 1000:1000 */      int pos = HashCommon.murmurHash3(k) & Short2FloatLinkedOpenHashMap.this.mask;
/* 1001:     */      
/* 1002:1002 */      while (Short2FloatLinkedOpenHashMap.this.used[pos] != 0) {
/* 1003:1003 */        if (Short2FloatLinkedOpenHashMap.this.key[pos] == k) {
/* 1004:1004 */          Short2FloatLinkedOpenHashMap.this.remove(e.getKey());
/* 1005:1005 */          return true;
/* 1006:     */        }
/* 1007:1007 */        pos = pos + 1 & Short2FloatLinkedOpenHashMap.this.mask;
/* 1008:     */      }
/* 1009:1009 */      return false;
/* 1010:     */    }
/* 1011:     */    
/* 1012:1012 */    public int size() { return Short2FloatLinkedOpenHashMap.this.size; }
/* 1013:     */    
/* 1014:     */    public void clear() {
/* 1015:1015 */      Short2FloatLinkedOpenHashMap.this.clear();
/* 1016:     */    }
/* 1017:     */    
/* 1018:1018 */    public ObjectBidirectionalIterator<Short2FloatMap.Entry> iterator(Short2FloatMap.Entry from) { return new Short2FloatLinkedOpenHashMap.EntryIterator(Short2FloatLinkedOpenHashMap.this, ((Short)from.getKey()).shortValue()); }
/* 1019:     */    
/* 1020:     */    public ObjectBidirectionalIterator<Short2FloatMap.Entry> fastIterator() {
/* 1021:1021 */      return new Short2FloatLinkedOpenHashMap.FastEntryIterator(Short2FloatLinkedOpenHashMap.this);
/* 1022:     */    }
/* 1023:     */    
/* 1024:1024 */    public ObjectBidirectionalIterator<Short2FloatMap.Entry> fastIterator(Short2FloatMap.Entry from) { return new Short2FloatLinkedOpenHashMap.FastEntryIterator(Short2FloatLinkedOpenHashMap.this, ((Short)from.getKey()).shortValue()); }
/* 1025:     */  }
/* 1026:     */  
/* 1027:     */  public Short2FloatSortedMap.FastSortedEntrySet short2FloatEntrySet() {
/* 1028:1028 */    if (this.entries == null) this.entries = new MapEntrySet(null);
/* 1029:1029 */    return this.entries;
/* 1030:     */  }
/* 1031:     */  
/* 1034:     */  private final class KeyIterator
/* 1035:     */    extends Short2FloatLinkedOpenHashMap.MapIterator
/* 1036:     */    implements ShortListIterator
/* 1037:     */  {
/* 1038:1038 */    public KeyIterator(short k) { super(k, null); }
/* 1039:1039 */    public short previousShort() { return Short2FloatLinkedOpenHashMap.this.key[previousEntry()]; }
/* 1040:1040 */    public void set(short k) { throw new UnsupportedOperationException(); }
/* 1041:1041 */    public void add(short k) { throw new UnsupportedOperationException(); }
/* 1042:1042 */    public Short previous() { return Short.valueOf(Short2FloatLinkedOpenHashMap.this.key[previousEntry()]); }
/* 1043:1043 */    public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1044:1044 */    public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1045:1045 */    public KeyIterator() { super(null); }
/* 1046:1046 */    public short nextShort() { return Short2FloatLinkedOpenHashMap.this.key[nextEntry()]; }
/* 1047:1047 */    public Short next() { return Short.valueOf(Short2FloatLinkedOpenHashMap.this.key[nextEntry()]); } }
/* 1048:     */  
/* 1049:     */  private final class KeySet extends AbstractShortSortedSet { private KeySet() {}
/* 1050:     */    
/* 1051:1051 */    public ShortListIterator iterator(short from) { return new Short2FloatLinkedOpenHashMap.KeyIterator(Short2FloatLinkedOpenHashMap.this, from); }
/* 1052:     */    
/* 1053:     */    public ShortListIterator iterator() {
/* 1054:1054 */      return new Short2FloatLinkedOpenHashMap.KeyIterator(Short2FloatLinkedOpenHashMap.this);
/* 1055:     */    }
/* 1056:     */    
/* 1057:1057 */    public int size() { return Short2FloatLinkedOpenHashMap.this.size; }
/* 1058:     */    
/* 1060:1060 */    public boolean contains(short k) { return Short2FloatLinkedOpenHashMap.this.containsKey(k); }
/* 1061:     */    
/* 1062:     */    public boolean remove(short k) {
/* 1063:1063 */      int oldSize = Short2FloatLinkedOpenHashMap.this.size;
/* 1064:1064 */      Short2FloatLinkedOpenHashMap.this.remove(k);
/* 1065:1065 */      return Short2FloatLinkedOpenHashMap.this.size != oldSize;
/* 1066:     */    }
/* 1067:     */    
/* 1068:1068 */    public void clear() { Short2FloatLinkedOpenHashMap.this.clear(); }
/* 1069:     */    
/* 1070:     */    public short firstShort() {
/* 1071:1071 */      if (Short2FloatLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1072:1072 */      return Short2FloatLinkedOpenHashMap.this.key[Short2FloatLinkedOpenHashMap.this.first];
/* 1073:     */    }
/* 1074:     */    
/* 1075:1075 */    public short lastShort() { if (Short2FloatLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1076:1076 */      return Short2FloatLinkedOpenHashMap.this.key[Short2FloatLinkedOpenHashMap.this.last]; }
/* 1077:     */    
/* 1078:1078 */    public ShortComparator comparator() { return null; }
/* 1079:1079 */    public final ShortSortedSet tailSet(short from) { throw new UnsupportedOperationException(); }
/* 1080:1080 */    public final ShortSortedSet headSet(short to) { throw new UnsupportedOperationException(); }
/* 1081:1081 */    public final ShortSortedSet subSet(short from, short to) { throw new UnsupportedOperationException(); }
/* 1082:     */  }
/* 1083:     */  
/* 1084:1084 */  public ShortSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1085:1085 */    return this.keys;
/* 1086:     */  }
/* 1087:     */  
/* 1090:     */  private final class ValueIterator
/* 1091:     */    extends Short2FloatLinkedOpenHashMap.MapIterator
/* 1092:     */    implements FloatListIterator
/* 1093:     */  {
/* 1094:1094 */    public float previousFloat() { return Short2FloatLinkedOpenHashMap.this.value[previousEntry()]; }
/* 1095:1095 */    public Float previous() { return Float.valueOf(Short2FloatLinkedOpenHashMap.this.value[previousEntry()]); }
/* 1096:1096 */    public void set(Float ok) { throw new UnsupportedOperationException(); }
/* 1097:1097 */    public void add(Float ok) { throw new UnsupportedOperationException(); }
/* 1098:1098 */    public void set(float v) { throw new UnsupportedOperationException(); }
/* 1099:1099 */    public void add(float v) { throw new UnsupportedOperationException(); }
/* 1100:1100 */    public ValueIterator() { super(null); }
/* 1101:1101 */    public float nextFloat() { return Short2FloatLinkedOpenHashMap.this.value[nextEntry()]; }
/* 1102:1102 */    public Float next() { return Float.valueOf(Short2FloatLinkedOpenHashMap.this.value[nextEntry()]); }
/* 1103:     */  }
/* 1104:     */  
/* 1105:1105 */  public FloatCollection values() { if (this.values == null) { this.values = new AbstractFloatCollection() {
/* 1106:     */        public FloatIterator iterator() {
/* 1107:1107 */          return new Short2FloatLinkedOpenHashMap.ValueIterator(Short2FloatLinkedOpenHashMap.this);
/* 1108:     */        }
/* 1109:     */        
/* 1110:1110 */        public int size() { return Short2FloatLinkedOpenHashMap.this.size; }
/* 1111:     */        
/* 1112:     */        public boolean contains(float v) {
/* 1113:1113 */          return Short2FloatLinkedOpenHashMap.this.containsValue(v);
/* 1114:     */        }
/* 1115:     */        
/* 1116:1116 */        public void clear() { Short2FloatLinkedOpenHashMap.this.clear(); }
/* 1117:     */      };
/* 1118:     */    }
/* 1119:1119 */    return this.values;
/* 1120:     */  }
/* 1121:     */  
/* 1130:     */  @Deprecated
/* 1131:     */  public boolean rehash()
/* 1132:     */  {
/* 1133:1133 */    return true;
/* 1134:     */  }
/* 1135:     */  
/* 1146:     */  public boolean trim()
/* 1147:     */  {
/* 1148:1148 */    int l = HashCommon.arraySize(this.size, this.f);
/* 1149:1149 */    if (l >= this.n) return true;
/* 1150:     */    try {
/* 1151:1151 */      rehash(l);
/* 1152:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1153:1153 */      return false; }
/* 1154:1154 */    return true;
/* 1155:     */  }
/* 1156:     */  
/* 1173:     */  public boolean trim(int n)
/* 1174:     */  {
/* 1175:1175 */    int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1176:1176 */    if (this.n <= l) return true;
/* 1177:     */    try {
/* 1178:1178 */      rehash(l);
/* 1179:     */    } catch (OutOfMemoryError cantDoIt) {
/* 1180:1180 */      return false; }
/* 1181:1181 */    return true;
/* 1182:     */  }
/* 1183:     */  
/* 1192:     */  protected void rehash(int newN)
/* 1193:     */  {
/* 1194:1194 */    int i = this.first;int prev = -1;int newPrev = -1;
/* 1195:     */    
/* 1196:1196 */    short[] key = this.key;
/* 1197:1197 */    float[] value = this.value;
/* 1198:1198 */    int newMask = newN - 1;
/* 1199:1199 */    short[] newKey = new short[newN];
/* 1200:1200 */    float[] newValue = new float[newN];
/* 1201:1201 */    boolean[] newUsed = new boolean[newN];
/* 1202:1202 */    long[] link = this.link;
/* 1203:1203 */    long[] newLink = new long[newN];
/* 1204:1204 */    this.first = -1;
/* 1205:1205 */    for (int j = this.size; j-- != 0;) {
/* 1206:1206 */      short k = key[i];
/* 1207:1207 */      int pos = HashCommon.murmurHash3(k) & newMask;
/* 1208:1208 */      while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1209:1209 */      newUsed[pos] = true;
/* 1210:1210 */      newKey[pos] = k;
/* 1211:1211 */      newValue[pos] = value[i];
/* 1212:1212 */      if (prev != -1) {
/* 1213:1213 */        newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1214:1214 */        newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1215:1215 */        newPrev = pos;
/* 1216:     */      }
/* 1217:     */      else {
/* 1218:1218 */        newPrev = this.first = pos;
/* 1219:     */        
/* 1220:1220 */        newLink[pos] = -1L;
/* 1221:     */      }
/* 1222:1222 */      int t = i;
/* 1223:1223 */      i = (int)link[i];
/* 1224:1224 */      prev = t;
/* 1225:     */    }
/* 1226:1226 */    this.n = newN;
/* 1227:1227 */    this.mask = newMask;
/* 1228:1228 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1229:1229 */    this.key = newKey;
/* 1230:1230 */    this.value = newValue;
/* 1231:1231 */    this.used = newUsed;
/* 1232:1232 */    this.link = newLink;
/* 1233:1233 */    this.last = newPrev;
/* 1234:1234 */    if (newPrev != -1)
/* 1235:     */    {
/* 1236:1236 */      newLink[newPrev] |= 4294967295L;
/* 1237:     */    }
/* 1238:     */  }
/* 1239:     */  
/* 1242:     */  public Short2FloatLinkedOpenHashMap clone()
/* 1243:     */  {
/* 1244:     */    Short2FloatLinkedOpenHashMap c;
/* 1245:     */    
/* 1247:     */    try
/* 1248:     */    {
/* 1249:1249 */      c = (Short2FloatLinkedOpenHashMap)super.clone();
/* 1250:     */    }
/* 1251:     */    catch (CloneNotSupportedException cantHappen) {
/* 1252:1252 */      throw new InternalError();
/* 1253:     */    }
/* 1254:1254 */    c.keys = null;
/* 1255:1255 */    c.values = null;
/* 1256:1256 */    c.entries = null;
/* 1257:1257 */    c.key = ((short[])this.key.clone());
/* 1258:1258 */    c.value = ((float[])this.value.clone());
/* 1259:1259 */    c.used = ((boolean[])this.used.clone());
/* 1260:1260 */    c.link = ((long[])this.link.clone());
/* 1261:1261 */    return c;
/* 1262:     */  }
/* 1263:     */  
/* 1271:     */  public int hashCode()
/* 1272:     */  {
/* 1273:1273 */    int h = 0;
/* 1274:1274 */    int j = this.size;int i = 0; for (int t = 0; j-- != 0;) {
/* 1275:1275 */      while (this.used[i] == 0) i++;
/* 1276:1276 */      t = this.key[i];
/* 1277:1277 */      t ^= HashCommon.float2int(this.value[i]);
/* 1278:1278 */      h += t;
/* 1279:1279 */      i++;
/* 1280:     */    }
/* 1281:1281 */    return h;
/* 1282:     */  }
/* 1283:     */  
/* 1284:1284 */  private void writeObject(ObjectOutputStream s) throws IOException { short[] key = this.key;
/* 1285:1285 */    float[] value = this.value;
/* 1286:1286 */    MapIterator i = new MapIterator(null);
/* 1287:1287 */    s.defaultWriteObject();
/* 1288:1288 */    for (int j = this.size; j-- != 0;) {
/* 1289:1289 */      int e = i.nextEntry();
/* 1290:1290 */      s.writeShort(key[e]);
/* 1291:1291 */      s.writeFloat(value[e]);
/* 1292:     */    }
/* 1293:     */  }
/* 1294:     */  
/* 1295:     */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1296:1296 */    s.defaultReadObject();
/* 1297:1297 */    this.n = HashCommon.arraySize(this.size, this.f);
/* 1298:1298 */    this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1299:1299 */    this.mask = (this.n - 1);
/* 1300:1300 */    short[] key = this.key = new short[this.n];
/* 1301:1301 */    float[] value = this.value = new float[this.n];
/* 1302:1302 */    boolean[] used = this.used = new boolean[this.n];
/* 1303:1303 */    long[] link = this.link = new long[this.n];
/* 1304:1304 */    int prev = -1;
/* 1305:1305 */    this.first = (this.last = -1);
/* 1306:     */    
/* 1308:1308 */    int i = this.size; for (int pos = 0; i-- != 0;) {
/* 1309:1309 */      short k = s.readShort();
/* 1310:1310 */      float v = s.readFloat();
/* 1311:1311 */      pos = HashCommon.murmurHash3(k) & this.mask;
/* 1312:1312 */      while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1313:1313 */      used[pos] = true;
/* 1314:1314 */      key[pos] = k;
/* 1315:1315 */      value[pos] = v;
/* 1316:1316 */      if (this.first != -1) {
/* 1317:1317 */        link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1318:1318 */        link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1319:1319 */        prev = pos;
/* 1320:     */      }
/* 1321:     */      else {
/* 1322:1322 */        prev = this.first = pos;
/* 1323:     */        
/* 1324:1324 */        link[pos] |= -4294967296L;
/* 1325:     */      }
/* 1326:     */    }
/* 1327:1327 */    this.last = prev;
/* 1328:1328 */    if (prev != -1)
/* 1329:     */    {
/* 1330:1330 */      link[prev] |= 4294967295L;
/* 1331:     */    }
/* 1332:     */  }
/* 1333:     */  
/* 1334:     */  private void checkTable() {}
/* 1335:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2FloatLinkedOpenHashMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */