/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.ObjectInputStream;
/*    5:     */import java.io.ObjectOutputStream;
/*    6:     */import java.io.Serializable;
/*    7:     */import java.util.Comparator;
/*    8:     */import java.util.Iterator;
/*    9:     */import java.util.Map;
/*   10:     */import java.util.Map.Entry;
/*   11:     */import java.util.NoSuchElementException;
/*   12:     */import java.util.SortedMap;
/*   13:     */
/*   70:     */public class Object2ReferenceRBTreeMap<K, V>
/*   71:     */  extends AbstractObject2ReferenceSortedMap<K, V>
/*   72:     */  implements Serializable, Cloneable
/*   73:     */{
/*   74:     */  protected transient Entry<K, V> tree;
/*   75:     */  protected int count;
/*   76:     */  protected transient Entry<K, V> firstEntry;
/*   77:     */  protected transient Entry<K, V> lastEntry;
/*   78:     */  protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
/*   79:     */  protected volatile transient ObjectSortedSet<K> keys;
/*   80:     */  protected volatile transient ReferenceCollection<V> values;
/*   81:     */  protected transient boolean modified;
/*   82:     */  protected Comparator<? super K> storedComparator;
/*   83:     */  protected transient Comparator<? super K> actualComparator;
/*   84:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   85:     */  private static final boolean ASSERTS = false;
/*   86:     */  private transient boolean[] dirPath;
/*   87:     */  private transient Entry<K, V>[] nodePath;
/*   88:     */  
/*   89:     */  public Object2ReferenceRBTreeMap()
/*   90:     */  {
/*   91:  91 */    allocatePaths();
/*   92:     */    
/*   97:  97 */    this.tree = null;
/*   98:  98 */    this.count = 0;
/*   99:     */  }
/*  100:     */  
/*  110:     */  private void setActualComparator()
/*  111:     */  {
/*  112: 112 */    this.actualComparator = this.storedComparator;
/*  113:     */  }
/*  114:     */  
/*  117:     */  public Object2ReferenceRBTreeMap(Comparator<? super K> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    this.storedComparator = c;
/*  121: 121 */    setActualComparator();
/*  122:     */  }
/*  123:     */  
/*  126:     */  public Object2ReferenceRBTreeMap(Map<? extends K, ? extends V> m)
/*  127:     */  {
/*  128: 128 */    this();
/*  129: 129 */    putAll(m);
/*  130:     */  }
/*  131:     */  
/*  134:     */  public Object2ReferenceRBTreeMap(SortedMap<K, V> m)
/*  135:     */  {
/*  136: 136 */    this(m.comparator());
/*  137: 137 */    putAll(m);
/*  138:     */  }
/*  139:     */  
/*  142:     */  public Object2ReferenceRBTreeMap(Object2ReferenceMap<? extends K, ? extends V> m)
/*  143:     */  {
/*  144: 144 */    this();
/*  145: 145 */    putAll(m);
/*  146:     */  }
/*  147:     */  
/*  150:     */  public Object2ReferenceRBTreeMap(Object2ReferenceSortedMap<K, V> m)
/*  151:     */  {
/*  152: 152 */    this(m.comparator());
/*  153: 153 */    putAll(m);
/*  154:     */  }
/*  155:     */  
/*  161:     */  public Object2ReferenceRBTreeMap(K[] k, V[] v, Comparator<? super K> c)
/*  162:     */  {
/*  163: 163 */    this(c);
/*  164: 164 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  165: 165 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  166:     */    }
/*  167:     */  }
/*  168:     */  
/*  172:     */  public Object2ReferenceRBTreeMap(K[] k, V[] v)
/*  173:     */  {
/*  174: 174 */    this(k, v, null);
/*  175:     */  }
/*  176:     */  
/*  195:     */  final int compare(K k1, K k2)
/*  196:     */  {
/*  197: 197 */    return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*  198:     */  }
/*  199:     */  
/*  203:     */  final Entry<K, V> findKey(K k)
/*  204:     */  {
/*  205: 205 */    Entry<K, V> e = this.tree;
/*  206:     */    int cmp;
/*  207: 207 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  208: 208 */    return e;
/*  209:     */  }
/*  210:     */  
/*  215:     */  final Entry<K, V> locateKey(K k)
/*  216:     */  {
/*  217: 217 */    Entry<K, V> e = this.tree;Entry<K, V> last = this.tree;
/*  218: 218 */    int cmp = 0;
/*  219: 219 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  220: 220 */      last = e;
/*  221: 221 */      e = cmp < 0 ? e.left() : e.right();
/*  222:     */    }
/*  223: 223 */    return cmp == 0 ? e : last;
/*  224:     */  }
/*  225:     */  
/*  229:     */  private void allocatePaths()
/*  230:     */  {
/*  231: 231 */    this.dirPath = new boolean[64];
/*  232: 232 */    this.nodePath = new Entry[64];
/*  233:     */  }
/*  234:     */  
/*  235:     */  public V put(K k, V v)
/*  236:     */  {
/*  237: 237 */    this.modified = false;
/*  238: 238 */    int maxDepth = 0;
/*  239: 239 */    if (this.tree == null) {
/*  240: 240 */      this.count += 1;
/*  241: 241 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  242:     */    }
/*  243:     */    else {
/*  244: 244 */      Entry<K, V> p = this.tree;
/*  245: 245 */      int i = 0;
/*  246:     */      for (;;) { int cmp;
/*  247: 247 */        if ((cmp = compare(k, p.key)) == 0) {
/*  248: 248 */          V oldValue = p.value;
/*  249: 249 */          p.value = v;
/*  250:     */          
/*  251: 251 */          while (i-- != 0) this.nodePath[i] = null;
/*  252: 252 */          return oldValue;
/*  253:     */        }
/*  254: 254 */        this.nodePath[i] = p;
/*  255: 255 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  256: 256 */          if (p.succ()) {
/*  257: 257 */            this.count += 1;
/*  258: 258 */            Entry<K, V> e = new Entry(k, v);
/*  259: 259 */            if (p.right == null) this.lastEntry = e;
/*  260: 260 */            e.left = p;
/*  261: 261 */            e.right = p.right;
/*  262: 262 */            p.right(e);
/*  263: 263 */            break;
/*  264:     */          }
/*  265: 265 */          p = p.right;
/*  266:     */        }
/*  267:     */        else {
/*  268: 268 */          if (p.pred()) {
/*  269: 269 */            this.count += 1;
/*  270: 270 */            Entry<K, V> e = new Entry(k, v);
/*  271: 271 */            if (p.left == null) this.firstEntry = e;
/*  272: 272 */            e.right = p;
/*  273: 273 */            e.left = p.left;
/*  274: 274 */            p.left(e);
/*  275: 275 */            break;
/*  276:     */          }
/*  277: 277 */          p = p.left;
/*  278:     */        } }
/*  279:     */      Entry<K, V> e;
/*  280: 280 */      this.modified = true;
/*  281: 281 */      maxDepth = i--;
/*  282: 282 */      while ((i > 0) && (!this.nodePath[i].black())) {
/*  283: 283 */        if (this.dirPath[(i - 1)] == 0) {
/*  284: 284 */          Entry<K, V> y = this.nodePath[(i - 1)].right;
/*  285: 285 */          if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  286: 286 */            this.nodePath[i].black(true);
/*  287: 287 */            y.black(true);
/*  288: 288 */            this.nodePath[(i - 1)].black(false);
/*  289: 289 */            i -= 2;
/*  290:     */          }
/*  291:     */          else
/*  292:     */          {
/*  293: 293 */            if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*  294:     */            } else {
/*  295: 295 */              Entry<K, V> x = this.nodePath[i];
/*  296: 296 */              y = x.right;
/*  297: 297 */              x.right = y.left;
/*  298: 298 */              y.left = x;
/*  299: 299 */              this.nodePath[(i - 1)].left = y;
/*  300: 300 */              if (y.pred()) {
/*  301: 301 */                y.pred(false);
/*  302: 302 */                x.succ(y);
/*  303:     */              }
/*  304:     */            }
/*  305: 305 */            Entry<K, V> x = this.nodePath[(i - 1)];
/*  306: 306 */            x.black(false);
/*  307: 307 */            y.black(true);
/*  308: 308 */            x.left = y.right;
/*  309: 309 */            y.right = x;
/*  310: 310 */            if (i < 2) { this.tree = y;
/*  311:     */            }
/*  312: 312 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  313: 313 */              this.nodePath[(i - 2)].left = y;
/*  314:     */            }
/*  315: 315 */            if (!y.succ()) break;
/*  316: 316 */            y.succ(false);
/*  317: 317 */            x.pred(y);break;
/*  318:     */          }
/*  319:     */          
/*  320:     */        }
/*  321:     */        else
/*  322:     */        {
/*  323: 323 */          Entry<K, V> y = this.nodePath[(i - 1)].left;
/*  324: 324 */          if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  325: 325 */            this.nodePath[i].black(true);
/*  326: 326 */            y.black(true);
/*  327: 327 */            this.nodePath[(i - 1)].black(false);
/*  328: 328 */            i -= 2;
/*  329:     */          }
/*  330:     */          else
/*  331:     */          {
/*  332: 332 */            if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*  333:     */            } else {
/*  334: 334 */              Entry<K, V> x = this.nodePath[i];
/*  335: 335 */              y = x.left;
/*  336: 336 */              x.left = y.right;
/*  337: 337 */              y.right = x;
/*  338: 338 */              this.nodePath[(i - 1)].right = y;
/*  339: 339 */              if (y.succ()) {
/*  340: 340 */                y.succ(false);
/*  341: 341 */                x.pred(y);
/*  342:     */              }
/*  343:     */            }
/*  344: 344 */            Entry<K, V> x = this.nodePath[(i - 1)];
/*  345: 345 */            x.black(false);
/*  346: 346 */            y.black(true);
/*  347: 347 */            x.right = y.left;
/*  348: 348 */            y.left = x;
/*  349: 349 */            if (i < 2) { this.tree = y;
/*  350:     */            }
/*  351: 351 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  352: 352 */              this.nodePath[(i - 2)].left = y;
/*  353:     */            }
/*  354: 354 */            if (!y.pred()) break;
/*  355: 355 */            y.pred(false);
/*  356: 356 */            x.succ(y);break;
/*  357:     */          }
/*  358:     */        }
/*  359:     */      }
/*  360:     */    }
/*  361:     */    
/*  363: 363 */    this.tree.black(true);
/*  364:     */    
/*  365: 365 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  366:     */    }
/*  367:     */    
/*  370: 370 */    return this.defRetValue;
/*  371:     */  }
/*  372:     */  
/*  374:     */  public V remove(Object k)
/*  375:     */  {
/*  376: 376 */    this.modified = false;
/*  377: 377 */    if (this.tree == null) return this.defRetValue;
/*  378: 378 */    Entry<K, V> p = this.tree;
/*  379:     */    
/*  380: 380 */    int i = 0;
/*  381: 381 */    K kk = k;
/*  382:     */    int cmp;
/*  383: 383 */    while ((cmp = compare(kk, p.key)) != 0) {
/*  384: 384 */      this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  385: 385 */      this.nodePath[i] = p;
/*  386: 386 */      if (this.dirPath[(i++)] != 0) {
/*  387: 387 */        if ((p = p.right()) == null)
/*  388:     */        {
/*  389: 389 */          while (i-- != 0) this.nodePath[i] = null;
/*  390: 390 */          return this.defRetValue;
/*  391:     */        }
/*  392:     */        
/*  393:     */      }
/*  394: 394 */      else if ((p = p.left()) == null)
/*  395:     */      {
/*  396: 396 */        while (i-- != 0) this.nodePath[i] = null;
/*  397: 397 */        return this.defRetValue;
/*  398:     */      }
/*  399:     */    }
/*  400:     */    
/*  401: 401 */    if (p.left == null) this.firstEntry = p.next();
/*  402: 402 */    if (p.right == null) this.lastEntry = p.prev();
/*  403: 403 */    if (p.succ()) {
/*  404: 404 */      if (p.pred()) {
/*  405: 405 */        if (i == 0) { this.tree = p.left;
/*  406:     */        }
/*  407: 407 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else {
/*  408: 408 */          this.nodePath[(i - 1)].pred(p.left);
/*  409:     */        }
/*  410:     */      }
/*  411:     */      else {
/*  412: 412 */        p.prev().right = p.right;
/*  413: 413 */        if (i == 0) { this.tree = p.left;
/*  414:     */        }
/*  415: 415 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  416: 416 */          this.nodePath[(i - 1)].left = p.left;
/*  417:     */        }
/*  418:     */      }
/*  419:     */    }
/*  420:     */    else
/*  421:     */    {
/*  422: 422 */      Entry<K, V> r = p.right;
/*  423: 423 */      if (r.pred()) {
/*  424: 424 */        r.left = p.left;
/*  425: 425 */        r.pred(p.pred());
/*  426: 426 */        if (!r.pred()) r.prev().right = r;
/*  427: 427 */        if (i == 0) { this.tree = r;
/*  428:     */        }
/*  429: 429 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  430: 430 */          this.nodePath[(i - 1)].left = r;
/*  431:     */        }
/*  432: 432 */        boolean color = r.black();
/*  433: 433 */        r.black(p.black());
/*  434: 434 */        p.black(color);
/*  435: 435 */        this.dirPath[i] = true;
/*  436: 436 */        this.nodePath[(i++)] = r;
/*  437:     */      }
/*  438:     */      else
/*  439:     */      {
/*  440: 440 */        int j = i++;
/*  441:     */        Entry<K, V> s;
/*  442: 442 */        for (;;) { this.dirPath[i] = false;
/*  443: 443 */          this.nodePath[(i++)] = r;
/*  444: 444 */          s = r.left;
/*  445: 445 */          if (s.pred()) break;
/*  446: 446 */          r = s;
/*  447:     */        }
/*  448: 448 */        this.dirPath[j] = true;
/*  449: 449 */        this.nodePath[j] = s;
/*  450: 450 */        if (s.succ()) r.pred(s); else
/*  451: 451 */          r.left = s.right;
/*  452: 452 */        s.left = p.left;
/*  453: 453 */        if (!p.pred()) {
/*  454: 454 */          p.prev().right = s;
/*  455: 455 */          s.pred(false);
/*  456:     */        }
/*  457: 457 */        s.right(p.right);
/*  458: 458 */        boolean color = s.black();
/*  459: 459 */        s.black(p.black());
/*  460: 460 */        p.black(color);
/*  461: 461 */        if (j == 0) { this.tree = s;
/*  462:     */        }
/*  463: 463 */        else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  464: 464 */          this.nodePath[(j - 1)].left = s;
/*  465:     */        }
/*  466:     */      }
/*  467:     */    }
/*  468: 468 */    int maxDepth = i;
/*  469: 469 */    if (p.black()) {
/*  470: 470 */      for (; i > 0; i--) {
/*  471: 471 */        if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*  472:     */        {
/*  473: 473 */          Entry<K, V> x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  474: 474 */          if (!x.black()) {
/*  475: 475 */            x.black(true);
/*  476: 476 */            break;
/*  477:     */          }
/*  478:     */        }
/*  479: 479 */        if (this.dirPath[(i - 1)] == 0) {
/*  480: 480 */          Entry<K, V> w = this.nodePath[(i - 1)].right;
/*  481: 481 */          if (!w.black()) {
/*  482: 482 */            w.black(true);
/*  483: 483 */            this.nodePath[(i - 1)].black(false);
/*  484: 484 */            this.nodePath[(i - 1)].right = w.left;
/*  485: 485 */            w.left = this.nodePath[(i - 1)];
/*  486: 486 */            if (i < 2) { this.tree = w;
/*  487:     */            }
/*  488: 488 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  489: 489 */              this.nodePath[(i - 2)].left = w;
/*  490:     */            }
/*  491: 491 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  492: 492 */            this.dirPath[i] = false;
/*  493: 493 */            this.nodePath[(i - 1)] = w;
/*  494: 494 */            if (maxDepth == i++) maxDepth++;
/*  495: 495 */            w = this.nodePath[(i - 1)].right;
/*  496:     */          }
/*  497: 497 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  498:     */          {
/*  499: 499 */            w.black(false);
/*  500:     */          }
/*  501:     */          else {
/*  502: 502 */            if ((w.succ()) || (w.right.black())) {
/*  503: 503 */              Entry<K, V> y = w.left;
/*  504: 504 */              y.black(true);
/*  505: 505 */              w.black(false);
/*  506: 506 */              w.left = y.right;
/*  507: 507 */              y.right = w;
/*  508: 508 */              w = this.nodePath[(i - 1)].right = y;
/*  509: 509 */              if (w.succ()) {
/*  510: 510 */                w.succ(false);
/*  511: 511 */                w.right.pred(w);
/*  512:     */              }
/*  513:     */            }
/*  514: 514 */            w.black(this.nodePath[(i - 1)].black());
/*  515: 515 */            this.nodePath[(i - 1)].black(true);
/*  516: 516 */            w.right.black(true);
/*  517: 517 */            this.nodePath[(i - 1)].right = w.left;
/*  518: 518 */            w.left = this.nodePath[(i - 1)];
/*  519: 519 */            if (i < 2) { this.tree = w;
/*  520:     */            }
/*  521: 521 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  522: 522 */              this.nodePath[(i - 2)].left = w;
/*  523:     */            }
/*  524: 524 */            if (!w.pred()) break;
/*  525: 525 */            w.pred(false);
/*  526: 526 */            this.nodePath[(i - 1)].succ(w);break;
/*  527:     */          }
/*  528:     */          
/*  529:     */        }
/*  530:     */        else
/*  531:     */        {
/*  532: 532 */          Entry<K, V> w = this.nodePath[(i - 1)].left;
/*  533: 533 */          if (!w.black()) {
/*  534: 534 */            w.black(true);
/*  535: 535 */            this.nodePath[(i - 1)].black(false);
/*  536: 536 */            this.nodePath[(i - 1)].left = w.right;
/*  537: 537 */            w.right = this.nodePath[(i - 1)];
/*  538: 538 */            if (i < 2) { this.tree = w;
/*  539:     */            }
/*  540: 540 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  541: 541 */              this.nodePath[(i - 2)].left = w;
/*  542:     */            }
/*  543: 543 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  544: 544 */            this.dirPath[i] = true;
/*  545: 545 */            this.nodePath[(i - 1)] = w;
/*  546: 546 */            if (maxDepth == i++) maxDepth++;
/*  547: 547 */            w = this.nodePath[(i - 1)].left;
/*  548:     */          }
/*  549: 549 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  550:     */          {
/*  551: 551 */            w.black(false);
/*  552:     */          }
/*  553:     */          else {
/*  554: 554 */            if ((w.pred()) || (w.left.black())) {
/*  555: 555 */              Entry<K, V> y = w.right;
/*  556: 556 */              y.black(true);
/*  557: 557 */              w.black(false);
/*  558: 558 */              w.right = y.left;
/*  559: 559 */              y.left = w;
/*  560: 560 */              w = this.nodePath[(i - 1)].left = y;
/*  561: 561 */              if (w.pred()) {
/*  562: 562 */                w.pred(false);
/*  563: 563 */                w.left.succ(w);
/*  564:     */              }
/*  565:     */            }
/*  566: 566 */            w.black(this.nodePath[(i - 1)].black());
/*  567: 567 */            this.nodePath[(i - 1)].black(true);
/*  568: 568 */            w.left.black(true);
/*  569: 569 */            this.nodePath[(i - 1)].left = w.right;
/*  570: 570 */            w.right = this.nodePath[(i - 1)];
/*  571: 571 */            if (i < 2) { this.tree = w;
/*  572:     */            }
/*  573: 573 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  574: 574 */              this.nodePath[(i - 2)].left = w;
/*  575:     */            }
/*  576: 576 */            if (!w.succ()) break;
/*  577: 577 */            w.succ(false);
/*  578: 578 */            this.nodePath[(i - 1)].pred(w);break;
/*  579:     */          }
/*  580:     */        }
/*  581:     */      }
/*  582:     */      
/*  584: 584 */      if (this.tree != null) this.tree.black(true);
/*  585:     */    }
/*  586: 586 */    this.modified = true;
/*  587: 587 */    this.count -= 1;
/*  588:     */    
/*  589: 589 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  590:     */    }
/*  591:     */    
/*  594: 594 */    return p.value;
/*  595:     */  }
/*  596:     */  
/*  597: 597 */  public boolean containsValue(Object v) { Object2ReferenceRBTreeMap<K, V>.ValueIterator i = new ValueIterator(null);
/*  598:     */    
/*  599: 599 */    int j = this.count;
/*  600: 600 */    while (j-- != 0) {
/*  601: 601 */      Object ev = i.next();
/*  602: 602 */      if (ev == v) return true;
/*  603:     */    }
/*  604: 604 */    return false;
/*  605:     */  }
/*  606:     */  
/*  607: 607 */  public void clear() { this.count = 0;
/*  608: 608 */    this.tree = null;
/*  609: 609 */    this.entries = null;
/*  610: 610 */    this.values = null;
/*  611: 611 */    this.keys = null;
/*  612: 612 */    this.firstEntry = (this.lastEntry = null);
/*  613:     */  }
/*  614:     */  
/*  617:     */  private static final class Entry<K, V>
/*  618:     */    implements Cloneable, Object2ReferenceMap.Entry<K, V>
/*  619:     */  {
/*  620:     */    private static final int BLACK_MASK = 1;
/*  621:     */    
/*  623:     */    private static final int SUCC_MASK = -2147483648;
/*  624:     */    
/*  626:     */    private static final int PRED_MASK = 1073741824;
/*  627:     */    
/*  628:     */    K key;
/*  629:     */    
/*  630:     */    V value;
/*  631:     */    
/*  632:     */    Entry<K, V> left;
/*  633:     */    
/*  634:     */    Entry<K, V> right;
/*  635:     */    
/*  636:     */    int info;
/*  637:     */    
/*  639:     */    Entry() {}
/*  640:     */    
/*  642:     */    Entry(K k, V v)
/*  643:     */    {
/*  644: 644 */      this.key = k;
/*  645: 645 */      this.value = v;
/*  646: 646 */      this.info = -1073741824;
/*  647:     */    }
/*  648:     */    
/*  652:     */    Entry<K, V> left()
/*  653:     */    {
/*  654: 654 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  655:     */    }
/*  656:     */    
/*  660:     */    Entry<K, V> right()
/*  661:     */    {
/*  662: 662 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  663:     */    }
/*  664:     */    
/*  666:     */    boolean pred()
/*  667:     */    {
/*  668: 668 */      return (this.info & 0x40000000) != 0;
/*  669:     */    }
/*  670:     */    
/*  672:     */    boolean succ()
/*  673:     */    {
/*  674: 674 */      return (this.info & 0x80000000) != 0;
/*  675:     */    }
/*  676:     */    
/*  678:     */    void pred(boolean pred)
/*  679:     */    {
/*  680: 680 */      if (pred) this.info |= 1073741824; else {
/*  681: 681 */        this.info &= -1073741825;
/*  682:     */      }
/*  683:     */    }
/*  684:     */    
/*  685:     */    void succ(boolean succ)
/*  686:     */    {
/*  687: 687 */      if (succ) this.info |= -2147483648; else {
/*  688: 688 */        this.info &= 2147483647;
/*  689:     */      }
/*  690:     */    }
/*  691:     */    
/*  692:     */    void pred(Entry<K, V> pred)
/*  693:     */    {
/*  694: 694 */      this.info |= 1073741824;
/*  695: 695 */      this.left = pred;
/*  696:     */    }
/*  697:     */    
/*  699:     */    void succ(Entry<K, V> succ)
/*  700:     */    {
/*  701: 701 */      this.info |= -2147483648;
/*  702: 702 */      this.right = succ;
/*  703:     */    }
/*  704:     */    
/*  706:     */    void left(Entry<K, V> left)
/*  707:     */    {
/*  708: 708 */      this.info &= -1073741825;
/*  709: 709 */      this.left = left;
/*  710:     */    }
/*  711:     */    
/*  713:     */    void right(Entry<K, V> right)
/*  714:     */    {
/*  715: 715 */      this.info &= 2147483647;
/*  716: 716 */      this.right = right;
/*  717:     */    }
/*  718:     */    
/*  720:     */    boolean black()
/*  721:     */    {
/*  722: 722 */      return (this.info & 0x1) != 0;
/*  723:     */    }
/*  724:     */    
/*  726:     */    void black(boolean black)
/*  727:     */    {
/*  728: 728 */      if (black) this.info |= 1; else {
/*  729: 729 */        this.info &= -2;
/*  730:     */      }
/*  731:     */    }
/*  732:     */    
/*  734:     */    Entry<K, V> next()
/*  735:     */    {
/*  736: 736 */      Entry<K, V> next = this.right;
/*  737: 737 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  738: 738 */      return next;
/*  739:     */    }
/*  740:     */    
/*  743:     */    Entry<K, V> prev()
/*  744:     */    {
/*  745: 745 */      Entry<K, V> prev = this.left;
/*  746: 746 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  747: 747 */      return prev;
/*  748:     */    }
/*  749:     */    
/*  750: 750 */    public K getKey() { return this.key; }
/*  751:     */    
/*  753: 753 */    public V getValue() { return this.value; }
/*  754:     */    
/*  755:     */    public V setValue(V value) {
/*  756: 756 */      V oldValue = this.value;
/*  757: 757 */      this.value = value;
/*  758: 758 */      return oldValue;
/*  759:     */    }
/*  760:     */    
/*  761:     */    public Entry<K, V> clone() {
/*  762:     */      Entry<K, V> c;
/*  763:     */      try {
/*  764: 764 */        c = (Entry)super.clone();
/*  765:     */      }
/*  766:     */      catch (CloneNotSupportedException cantHappen) {
/*  767: 767 */        throw new InternalError();
/*  768:     */      }
/*  769: 769 */      c.key = this.key;
/*  770: 770 */      c.value = this.value;
/*  771: 771 */      c.info = this.info;
/*  772: 772 */      return c;
/*  773:     */    }
/*  774:     */    
/*  775:     */    public boolean equals(Object o) {
/*  776: 776 */      if (!(o instanceof Map.Entry)) return false;
/*  777: 777 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  778: 778 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == e.getValue());
/*  779:     */    }
/*  780:     */    
/*  781: 781 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
/*  782:     */    
/*  783:     */    public String toString() {
/*  784: 784 */      return this.key + "=>" + this.value;
/*  785:     */    }
/*  786:     */  }
/*  787:     */  
/*  816:     */  public boolean containsKey(Object k)
/*  817:     */  {
/*  818: 818 */    return findKey(k) != null;
/*  819:     */  }
/*  820:     */  
/*  821: 821 */  public int size() { return this.count; }
/*  822:     */  
/*  823:     */  public boolean isEmpty() {
/*  824: 824 */    return this.count == 0;
/*  825:     */  }
/*  826:     */  
/*  827:     */  public V get(Object k) {
/*  828: 828 */    Entry<K, V> e = findKey(k);
/*  829: 829 */    return e == null ? this.defRetValue : e.value;
/*  830:     */  }
/*  831:     */  
/*  832: 832 */  public K firstKey() { if (this.tree == null) throw new NoSuchElementException();
/*  833: 833 */    return this.firstEntry.key;
/*  834:     */  }
/*  835:     */  
/*  836: 836 */  public K lastKey() { if (this.tree == null) throw new NoSuchElementException();
/*  837: 837 */    return this.lastEntry.key;
/*  838:     */  }
/*  839:     */  
/*  842:     */  private class TreeIterator
/*  843:     */  {
/*  844:     */    Object2ReferenceRBTreeMap.Entry<K, V> prev;
/*  845:     */    
/*  847:     */    Object2ReferenceRBTreeMap.Entry<K, V> next;
/*  848:     */    
/*  849:     */    Object2ReferenceRBTreeMap.Entry<K, V> curr;
/*  850:     */    
/*  851: 851 */    int index = 0;
/*  852:     */    
/*  853: 853 */    TreeIterator() { this.next = Object2ReferenceRBTreeMap.this.firstEntry; }
/*  854:     */    
/*  855:     */    TreeIterator() {
/*  856: 856 */      if ((this.next = Object2ReferenceRBTreeMap.this.locateKey(k)) != null)
/*  857: 857 */        if (Object2ReferenceRBTreeMap.this.compare(this.next.key, k) <= 0) {
/*  858: 858 */          this.prev = this.next;
/*  859: 859 */          this.next = this.next.next();
/*  860:     */        } else {
/*  861: 861 */          this.prev = this.next.prev();
/*  862:     */        } }
/*  863:     */    
/*  864: 864 */    public boolean hasNext() { return this.next != null; }
/*  865: 865 */    public boolean hasPrevious() { return this.prev != null; }
/*  866:     */    
/*  867: 867 */    void updateNext() { this.next = this.next.next(); }
/*  868:     */    
/*  869:     */    Object2ReferenceRBTreeMap.Entry<K, V> nextEntry() {
/*  870: 870 */      if (!hasNext()) throw new NoSuchElementException();
/*  871: 871 */      this.curr = (this.prev = this.next);
/*  872: 872 */      this.index += 1;
/*  873: 873 */      updateNext();
/*  874: 874 */      return this.curr;
/*  875:     */    }
/*  876:     */    
/*  877: 877 */    void updatePrevious() { this.prev = this.prev.prev(); }
/*  878:     */    
/*  879:     */    Object2ReferenceRBTreeMap.Entry<K, V> previousEntry() {
/*  880: 880 */      if (!hasPrevious()) throw new NoSuchElementException();
/*  881: 881 */      this.curr = (this.next = this.prev);
/*  882: 882 */      this.index -= 1;
/*  883: 883 */      updatePrevious();
/*  884: 884 */      return this.curr;
/*  885:     */    }
/*  886:     */    
/*  887: 887 */    public int nextIndex() { return this.index; }
/*  888:     */    
/*  890: 890 */    public int previousIndex() { return this.index - 1; }
/*  891:     */    
/*  892:     */    public void remove() {
/*  893: 893 */      if (this.curr == null) { throw new IllegalStateException();
/*  894:     */      }
/*  895:     */      
/*  896: 896 */      if (this.curr == this.prev) this.index -= 1;
/*  897: 897 */      this.next = (this.prev = this.curr);
/*  898: 898 */      updatePrevious();
/*  899: 899 */      updateNext();
/*  900: 900 */      Object2ReferenceRBTreeMap.this.remove(this.curr.key);
/*  901: 901 */      this.curr = null;
/*  902:     */    }
/*  903:     */    
/*  904: 904 */    public int skip(int n) { int i = n;
/*  905: 905 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  906: 906 */      return n - i - 1;
/*  907:     */    }
/*  908:     */    
/*  909: 909 */    public int back(int n) { int i = n;
/*  910: 910 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  911: 911 */      return n - i - 1;
/*  912:     */    }
/*  913:     */  }
/*  914:     */  
/*  915:     */  private class EntryIterator
/*  916:     */    extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
/*  917:     */    implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*  918:     */  {
/*  919: 919 */    EntryIterator() { super(); }
/*  920:     */    
/*  921: 921 */    EntryIterator() { super(k); }
/*  922:     */    
/*  923: 923 */    public Object2ReferenceMap.Entry<K, V> next() { return nextEntry(); }
/*  924: 924 */    public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); }
/*  925: 925 */    public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  926: 926 */    public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  927:     */  }
/*  928:     */  
/*  929: 929 */  public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  930: 930 */        final Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator = new Comparator() {
/*  931:     */          public int compare(Object2ReferenceMap.Entry<K, V> x, Object2ReferenceMap.Entry<K, V> y) {
/*  932: 932 */            return Object2ReferenceRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*  933:     */          }
/*  934:     */        };
/*  935:     */        
/*  936: 936 */        public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return this.comparator; }
/*  937:     */        
/*  938:     */        public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/*  939: 939 */          return new Object2ReferenceRBTreeMap.EntryIterator(Object2ReferenceRBTreeMap.this);
/*  940:     */        }
/*  941:     */        
/*  942: 942 */        public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) { return new Object2ReferenceRBTreeMap.EntryIterator(Object2ReferenceRBTreeMap.this, from.getKey()); }
/*  943:     */        
/*  944:     */        public boolean contains(Object o)
/*  945:     */        {
/*  946: 946 */          if (!(o instanceof Map.Entry)) return false;
/*  947: 947 */          Map.Entry<K, V> e = (Map.Entry)o;
/*  948: 948 */          Object2ReferenceRBTreeMap.Entry<K, V> f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/*  949: 949 */          return e.equals(f);
/*  950:     */        }
/*  951:     */        
/*  952:     */        public boolean remove(Object o) {
/*  953: 953 */          if (!(o instanceof Map.Entry)) return false;
/*  954: 954 */          Map.Entry<K, V> e = (Map.Entry)o;
/*  955: 955 */          Object2ReferenceRBTreeMap.Entry<K, V> f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/*  956: 956 */          if (f != null) Object2ReferenceRBTreeMap.this.remove(f.key);
/*  957: 957 */          return f != null; }
/*  958:     */        
/*  959: 959 */        public int size() { return Object2ReferenceRBTreeMap.this.count; }
/*  960: 960 */        public void clear() { Object2ReferenceRBTreeMap.this.clear(); }
/*  961: 961 */        public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceRBTreeMap.this.firstEntry; }
/*  962: 962 */        public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceRBTreeMap.this.lastEntry; }
/*  963: 963 */        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); }
/*  964: 964 */        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.this.headMap(to.getKey()).object2ReferenceEntrySet(); }
/*  965: 965 */        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceRBTreeMap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/*  966:     */      };
/*  967: 967 */    return this.entries;
/*  968:     */  }
/*  969:     */  
/*  972:     */  private final class KeyIterator
/*  973:     */    extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
/*  974:     */    implements ObjectListIterator<K>
/*  975:     */  {
/*  976: 976 */    public KeyIterator() { super(); }
/*  977: 977 */    public KeyIterator() { super(k); }
/*  978: 978 */    public K next() { return nextEntry().key; }
/*  979: 979 */    public K previous() { return previousEntry().key; }
/*  980: 980 */    public void set(K k) { throw new UnsupportedOperationException(); }
/*  981: 981 */    public void add(K k) { throw new UnsupportedOperationException(); }
/*  982:     */  }
/*  983:     */  
/*  984: 984 */  private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/*  985: 985 */    public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceRBTreeMap.KeyIterator(Object2ReferenceRBTreeMap.this); }
/*  986: 986 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceRBTreeMap.KeyIterator(Object2ReferenceRBTreeMap.this, from); }
/*  987:     */  }
/*  988:     */  
/*  995:     */  public ObjectSortedSet<K> keySet()
/*  996:     */  {
/*  997: 997 */    if (this.keys == null) this.keys = new KeySet(null);
/*  998: 998 */    return this.keys;
/*  999:     */  }
/* 1000:     */  
/* 1002:     */  private final class ValueIterator
/* 1003:     */    extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
/* 1004:     */    implements ObjectListIterator<V>
/* 1005:     */  {
/* 1006:1006 */    private ValueIterator() { super(); }
/* 1007:1007 */    public V next() { return nextEntry().value; }
/* 1008:1008 */    public V previous() { return previousEntry().value; }
/* 1009:1009 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1010:1010 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1011:     */  }
/* 1012:     */  
/* 1019:     */  public ReferenceCollection<V> values()
/* 1020:     */  {
/* 1021:1021 */    if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1022:     */        public ObjectIterator<V> iterator() {
/* 1023:1023 */          return new Object2ReferenceRBTreeMap.ValueIterator(Object2ReferenceRBTreeMap.this, null);
/* 1024:     */        }
/* 1025:     */        
/* 1026:1026 */        public boolean contains(Object k) { return Object2ReferenceRBTreeMap.this.containsValue(k); }
/* 1027:     */        
/* 1028:     */        public int size() {
/* 1029:1029 */          return Object2ReferenceRBTreeMap.this.count;
/* 1030:     */        }
/* 1031:     */        
/* 1032:1032 */        public void clear() { Object2ReferenceRBTreeMap.this.clear(); }
/* 1033:     */      };
/* 1034:     */    }
/* 1035:1035 */    return this.values;
/* 1036:     */  }
/* 1037:     */  
/* 1038:1038 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1039:     */  
/* 1040:     */  public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1041:1041 */    return new Submap(null, true, to, false);
/* 1042:     */  }
/* 1043:     */  
/* 1044:1044 */  public Object2ReferenceSortedMap<K, V> tailMap(K from) { return new Submap(from, false, null, true); }
/* 1045:     */  
/* 1046:     */  public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 1047:1047 */    return new Submap(from, false, to, false);
/* 1048:     */  }
/* 1049:     */  
/* 1053:     */  private final class Submap
/* 1054:     */    extends AbstractObject2ReferenceSortedMap<K, V>
/* 1055:     */    implements Serializable
/* 1056:     */  {
/* 1057:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1058:     */    
/* 1061:     */    K from;
/* 1062:     */    
/* 1064:     */    K to;
/* 1065:     */    
/* 1067:     */    boolean bottom;
/* 1068:     */    
/* 1070:     */    boolean top;
/* 1071:     */    
/* 1073:     */    protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
/* 1074:     */    
/* 1076:     */    protected volatile transient ObjectSortedSet<K> keys;
/* 1077:     */    
/* 1079:     */    protected volatile transient ReferenceCollection<V> values;
/* 1080:     */    
/* 1083:     */    public Submap(boolean from, K bottom, boolean to)
/* 1084:     */    {
/* 1085:1085 */      if ((!bottom) && (!top) && (Object2ReferenceRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1086:1086 */      this.from = from;
/* 1087:1087 */      this.bottom = bottom;
/* 1088:1088 */      this.to = to;
/* 1089:1089 */      this.top = top;
/* 1090:1090 */      this.defRetValue = Object2ReferenceRBTreeMap.this.defRetValue;
/* 1091:     */    }
/* 1092:     */    
/* 1093:1093 */    public void clear() { Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1094:1094 */      while (i.hasNext()) {
/* 1095:1095 */        i.nextEntry();
/* 1096:1096 */        i.remove();
/* 1097:     */      }
/* 1098:     */    }
/* 1099:     */    
/* 1102:     */    final boolean in(K k)
/* 1103:     */    {
/* 1104:1104 */      return ((this.bottom) || (Object2ReferenceRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ReferenceRBTreeMap.this.compare(k, this.to) < 0));
/* 1105:     */    }
/* 1106:     */    
/* 1107:     */    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() {
/* 1108:1108 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1109:     */          public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/* 1110:1110 */            return new Object2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Object2ReferenceRBTreeMap.Submap.this);
/* 1111:     */          }
/* 1112:     */          
/* 1113:1113 */          public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) { return new Object2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Object2ReferenceRBTreeMap.Submap.this, from.getKey()); }
/* 1114:     */          
/* 1115:1115 */          public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return Object2ReferenceRBTreeMap.this.object2ReferenceEntrySet().comparator(); }
/* 1116:     */          
/* 1117:     */          public boolean contains(Object o) {
/* 1118:1118 */            if (!(o instanceof Map.Entry)) return false;
/* 1119:1119 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1120:1120 */            Object2ReferenceRBTreeMap.Entry<K, V> f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/* 1121:1121 */            return (f != null) && (Object2ReferenceRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1122:     */          }
/* 1123:     */          
/* 1124:     */          public boolean remove(Object o) {
/* 1125:1125 */            if (!(o instanceof Map.Entry)) return false;
/* 1126:1126 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1127:1127 */            Object2ReferenceRBTreeMap.Entry<K, V> f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/* 1128:1128 */            if ((f != null) && (Object2ReferenceRBTreeMap.Submap.this.in(f.key))) Object2ReferenceRBTreeMap.Submap.this.remove(f.key);
/* 1129:1129 */            return f != null;
/* 1130:     */          }
/* 1131:     */          
/* 1132:1132 */          public int size() { int c = 0;
/* 1133:1133 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1134:1134 */            return c; }
/* 1135:     */          
/* 1136:1136 */          public boolean isEmpty() { return !new Object2ReferenceRBTreeMap.Submap.SubmapIterator(Object2ReferenceRBTreeMap.Submap.this).hasNext(); }
/* 1137:1137 */          public void clear() { Object2ReferenceRBTreeMap.Submap.this.clear(); }
/* 1138:1138 */          public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceRBTreeMap.Submap.this.firstEntry(); }
/* 1139:1139 */          public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceRBTreeMap.Submap.this.lastEntry(); }
/* 1140:1140 */          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); }
/* 1141:1141 */          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.Submap.this.headMap(to.getKey()).object2ReferenceEntrySet(); }
/* 1142:1142 */          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceRBTreeMap.Submap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/* 1143:     */        };
/* 1144:1144 */      return this.entries; }
/* 1145:     */    
/* 1146:1146 */    private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1147:1147 */      public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Object2ReferenceRBTreeMap.Submap.this); }
/* 1148:1148 */      public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Object2ReferenceRBTreeMap.Submap.this, from); }
/* 1149:     */    }
/* 1150:     */    
/* 1151:1151 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1152:1152 */      return this.keys;
/* 1153:     */    }
/* 1154:     */    
/* 1155:1155 */    public ReferenceCollection<V> values() { if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1156:     */          public ObjectIterator<V> iterator() {
/* 1157:1157 */            return new Object2ReferenceRBTreeMap.Submap.SubmapValueIterator(Object2ReferenceRBTreeMap.Submap.this, null);
/* 1158:     */          }
/* 1159:     */          
/* 1160:1160 */          public boolean contains(Object k) { return Object2ReferenceRBTreeMap.Submap.this.containsValue(k); }
/* 1161:     */          
/* 1162:     */          public int size() {
/* 1163:1163 */            return Object2ReferenceRBTreeMap.Submap.this.size();
/* 1164:     */          }
/* 1165:     */          
/* 1166:1166 */          public void clear() { Object2ReferenceRBTreeMap.Submap.this.clear(); }
/* 1167:     */        };
/* 1168:     */      }
/* 1169:1169 */      return this.values;
/* 1170:     */    }
/* 1171:     */    
/* 1173:1173 */    public boolean containsKey(Object k) { return (in(k)) && (Object2ReferenceRBTreeMap.this.containsKey(k)); }
/* 1174:     */    
/* 1175:     */    public boolean containsValue(Object v) {
/* 1176:1176 */      Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1177:     */      
/* 1178:1178 */      while (i.hasNext()) {
/* 1179:1179 */        Object ev = i.nextEntry().value;
/* 1180:1180 */        if (ev == v) return true;
/* 1181:     */      }
/* 1182:1182 */      return false;
/* 1183:     */    }
/* 1184:     */    
/* 1185:     */    public V get(Object k)
/* 1186:     */    {
/* 1187:1187 */      K kk = k;
/* 1188:1188 */      Object2ReferenceRBTreeMap.Entry<K, V> e; return (in(kk)) && ((e = Object2ReferenceRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1189:     */    }
/* 1190:     */    
/* 1191:1191 */    public V put(K k, V v) { Object2ReferenceRBTreeMap.this.modified = false;
/* 1192:1192 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1193:1193 */      V oldValue = Object2ReferenceRBTreeMap.this.put(k, v);
/* 1194:1194 */      return Object2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1195:     */    }
/* 1196:     */    
/* 1197:     */    public V remove(Object k) {
/* 1198:1198 */      Object2ReferenceRBTreeMap.this.modified = false;
/* 1199:1199 */      if (!in(k)) return this.defRetValue;
/* 1200:1200 */      V oldValue = Object2ReferenceRBTreeMap.this.remove(k);
/* 1201:1201 */      return Object2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1202:     */    }
/* 1203:     */    
/* 1204:1204 */    public int size() { Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1205:1205 */      int n = 0;
/* 1206:1206 */      while (i.hasNext()) {
/* 1207:1207 */        n++;
/* 1208:1208 */        i.nextEntry();
/* 1209:     */      }
/* 1210:1210 */      return n;
/* 1211:     */    }
/* 1212:     */    
/* 1213:1213 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1214:     */    
/* 1216:1216 */    public Comparator<? super K> comparator() { return Object2ReferenceRBTreeMap.this.actualComparator; }
/* 1217:     */    
/* 1218:     */    public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1219:1219 */      if (this.top) return new Submap(Object2ReferenceRBTreeMap.this, this.from, this.bottom, to, false);
/* 1220:1220 */      return Object2ReferenceRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ReferenceRBTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1221:     */    }
/* 1222:     */    
/* 1223:1223 */    public Object2ReferenceSortedMap<K, V> tailMap(K from) { if (this.bottom) return new Submap(Object2ReferenceRBTreeMap.this, from, false, this.to, this.top);
/* 1224:1224 */      return Object2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ReferenceRBTreeMap.this, from, false, this.to, this.top) : this;
/* 1225:     */    }
/* 1226:     */    
/* 1227:1227 */    public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { if ((this.top) && (this.bottom)) return new Submap(Object2ReferenceRBTreeMap.this, from, false, to, false);
/* 1228:1228 */      if (!this.top) to = Object2ReferenceRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1229:1229 */      if (!this.bottom) from = Object2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1230:1230 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1231:1231 */      return new Submap(Object2ReferenceRBTreeMap.this, from, false, to, false);
/* 1232:     */    }
/* 1233:     */    
/* 1236:     */    public Object2ReferenceRBTreeMap.Entry<K, V> firstEntry()
/* 1237:     */    {
/* 1238:1238 */      if (Object2ReferenceRBTreeMap.this.tree == null) return null;
/* 1239:     */      Object2ReferenceRBTreeMap.Entry<K, V> e;
/* 1240:     */      Object2ReferenceRBTreeMap.Entry<K, V> e;
/* 1241:1241 */      if (this.bottom) { e = Object2ReferenceRBTreeMap.this.firstEntry;
/* 1242:     */      } else {
/* 1243:1243 */        e = Object2ReferenceRBTreeMap.this.locateKey(this.from);
/* 1244:     */        
/* 1245:1245 */        if (Object2ReferenceRBTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1246:     */        }
/* 1247:     */      }
/* 1248:1248 */      if ((e == null) || ((!this.top) && (Object2ReferenceRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1249:1249 */      return e;
/* 1250:     */    }
/* 1251:     */    
/* 1254:     */    public Object2ReferenceRBTreeMap.Entry<K, V> lastEntry()
/* 1255:     */    {
/* 1256:1256 */      if (Object2ReferenceRBTreeMap.this.tree == null) return null;
/* 1257:     */      Object2ReferenceRBTreeMap.Entry<K, V> e;
/* 1258:     */      Object2ReferenceRBTreeMap.Entry<K, V> e;
/* 1259:1259 */      if (this.top) { e = Object2ReferenceRBTreeMap.this.lastEntry;
/* 1260:     */      } else {
/* 1261:1261 */        e = Object2ReferenceRBTreeMap.this.locateKey(this.to);
/* 1262:     */        
/* 1263:1263 */        if (Object2ReferenceRBTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1264:     */        }
/* 1265:     */      }
/* 1266:1266 */      if ((e == null) || ((!this.bottom) && (Object2ReferenceRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1267:1267 */      return e;
/* 1268:     */    }
/* 1269:     */    
/* 1270:1270 */    public K firstKey() { Object2ReferenceRBTreeMap.Entry<K, V> e = firstEntry();
/* 1271:1271 */      if (e == null) throw new NoSuchElementException();
/* 1272:1272 */      return e.key;
/* 1273:     */    }
/* 1274:     */    
/* 1275:1275 */    public K lastKey() { Object2ReferenceRBTreeMap.Entry<K, V> e = lastEntry();
/* 1276:1276 */      if (e == null) throw new NoSuchElementException();
/* 1277:1277 */      return e.key;
/* 1278:     */    }
/* 1279:     */    
/* 1282:     */    private class SubmapIterator
/* 1283:     */      extends Object2ReferenceRBTreeMap.TreeIterator
/* 1284:     */    {
/* 1285:     */      SubmapIterator()
/* 1286:     */      {
/* 1287:1287 */        super();
/* 1288:1288 */        this.next = Object2ReferenceRBTreeMap.Submap.this.firstEntry();
/* 1289:     */      }
/* 1290:     */      
/* 1291:1291 */      SubmapIterator() { this();
/* 1292:1292 */        if (this.next != null)
/* 1293:1293 */          if ((!Object2ReferenceRBTreeMap.Submap.this.bottom) && (Object2ReferenceRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1294:1294 */          } else if ((!Object2ReferenceRBTreeMap.Submap.this.top) && (Object2ReferenceRBTreeMap.this.compare(k, (this.prev = Object2ReferenceRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1295:     */          } else {
/* 1296:1296 */            this.next = Object2ReferenceRBTreeMap.this.locateKey(k);
/* 1297:1297 */            if (Object2ReferenceRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1298:1298 */              this.prev = this.next;
/* 1299:1299 */              this.next = this.next.next();
/* 1300:     */            } else {
/* 1301:1301 */              this.prev = this.next.prev();
/* 1302:     */            }
/* 1303:     */          }
/* 1304:     */      }
/* 1305:     */      
/* 1306:1306 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1307:1307 */        if ((!Object2ReferenceRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ReferenceRBTreeMap.this.compare(this.prev.key, Object2ReferenceRBTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1308:     */      }
/* 1309:     */      
/* 1310:1310 */      void updateNext() { this.next = this.next.next();
/* 1311:1311 */        if ((!Object2ReferenceRBTreeMap.Submap.this.top) && (this.next != null) && (Object2ReferenceRBTreeMap.this.compare(this.next.key, Object2ReferenceRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1312:     */      }
/* 1313:     */    }
/* 1314:     */    
/* 1315:1315 */    private class SubmapEntryIterator extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>> { SubmapEntryIterator() { super(); }
/* 1316:     */      
/* 1317:1317 */      SubmapEntryIterator() { super(k); }
/* 1318:     */      
/* 1319:1319 */      public Object2ReferenceMap.Entry<K, V> next() { return nextEntry(); }
/* 1320:1320 */      public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); }
/* 1321:1321 */      public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1322:1322 */      public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1323:     */    }
/* 1324:     */    
/* 1329:     */    private final class SubmapKeyIterator
/* 1330:     */      extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
/* 1331:     */      implements ObjectListIterator<K>
/* 1332:     */    {
/* 1333:1333 */      public SubmapKeyIterator() { super(); }
/* 1334:1334 */      public SubmapKeyIterator() { super(from); }
/* 1335:1335 */      public K next() { return nextEntry().key; }
/* 1336:1336 */      public K previous() { return previousEntry().key; }
/* 1337:1337 */      public void set(K k) { throw new UnsupportedOperationException(); }
/* 1338:1338 */      public void add(K k) { throw new UnsupportedOperationException(); }
/* 1339:     */    }
/* 1340:     */    
/* 1344:     */    private final class SubmapValueIterator
/* 1345:     */      extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
/* 1346:     */      implements ObjectListIterator<V>
/* 1347:     */    {
/* 1348:1348 */      private SubmapValueIterator() { super(); }
/* 1349:1349 */      public V next() { return nextEntry().value; }
/* 1350:1350 */      public V previous() { return previousEntry().value; }
/* 1351:1351 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1352:1352 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1353:     */    }
/* 1354:     */  }
/* 1355:     */  
/* 1359:     */  public Object2ReferenceRBTreeMap<K, V> clone()
/* 1360:     */  {
/* 1361:     */    Object2ReferenceRBTreeMap<K, V> c;
/* 1362:     */    
/* 1364:     */    try
/* 1365:     */    {
/* 1366:1366 */      c = (Object2ReferenceRBTreeMap)super.clone();
/* 1367:     */    }
/* 1368:     */    catch (CloneNotSupportedException cantHappen) {
/* 1369:1369 */      throw new InternalError();
/* 1370:     */    }
/* 1371:1371 */    c.keys = null;
/* 1372:1372 */    c.values = null;
/* 1373:1373 */    c.entries = null;
/* 1374:1374 */    c.allocatePaths();
/* 1375:1375 */    if (this.count != 0)
/* 1376:     */    {
/* 1377:1377 */      Entry<K, V> rp = new Entry();Entry<K, V> rq = new Entry();
/* 1378:1378 */      Entry<K, V> p = rp;
/* 1379:1379 */      rp.left(this.tree);
/* 1380:1380 */      Entry<K, V> q = rq;
/* 1381:1381 */      rq.pred(null);
/* 1382:     */      for (;;) {
/* 1383:1383 */        if (!p.pred()) {
/* 1384:1384 */          Entry<K, V> e = p.left.clone();
/* 1385:1385 */          e.pred(q.left);
/* 1386:1386 */          e.succ(q);
/* 1387:1387 */          q.left(e);
/* 1388:1388 */          p = p.left;
/* 1389:1389 */          q = q.left;
/* 1390:     */        }
/* 1391:     */        else {
/* 1392:1392 */          while (p.succ()) {
/* 1393:1393 */            p = p.right;
/* 1394:1394 */            if (p == null) {
/* 1395:1395 */              q.right = null;
/* 1396:1396 */              c.tree = rq.left;
/* 1397:1397 */              c.firstEntry = c.tree;
/* 1398:1398 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1399:1399 */              c.lastEntry = c.tree;
/* 1400:1400 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1401:1401 */              return c;
/* 1402:     */            }
/* 1403:1403 */            q = q.right;
/* 1404:     */          }
/* 1405:1405 */          p = p.right;
/* 1406:1406 */          q = q.right;
/* 1407:     */        }
/* 1408:1408 */        if (!p.succ()) {
/* 1409:1409 */          Entry<K, V> e = p.right.clone();
/* 1410:1410 */          e.succ(q.right);
/* 1411:1411 */          e.pred(q);
/* 1412:1412 */          q.right(e);
/* 1413:     */        }
/* 1414:     */      }
/* 1415:     */    }
/* 1416:1416 */    return c;
/* 1417:     */  }
/* 1418:     */  
/* 1419:1419 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1420:1420 */    Object2ReferenceRBTreeMap<K, V>.EntryIterator i = new EntryIterator();
/* 1421:     */    
/* 1422:1422 */    s.defaultWriteObject();
/* 1423:1423 */    while (n-- != 0) {
/* 1424:1424 */      Entry<K, V> e = i.nextEntry();
/* 1425:1425 */      s.writeObject(e.key);
/* 1426:1426 */      s.writeObject(e.value);
/* 1427:     */    }
/* 1428:     */  }
/* 1429:     */  
/* 1435:     */  private Entry<K, V> readTree(ObjectInputStream s, int n, Entry<K, V> pred, Entry<K, V> succ)
/* 1436:     */    throws IOException, ClassNotFoundException
/* 1437:     */  {
/* 1438:1438 */    if (n == 1) {
/* 1439:1439 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1440:1440 */      top.pred(pred);
/* 1441:1441 */      top.succ(succ);
/* 1442:1442 */      top.black(true);
/* 1443:1443 */      return top;
/* 1444:     */    }
/* 1445:1445 */    if (n == 2)
/* 1446:     */    {
/* 1448:1448 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1449:1449 */      top.black(true);
/* 1450:1450 */      top.right(new Entry(s.readObject(), s.readObject()));
/* 1451:1451 */      top.right.pred(top);
/* 1452:1452 */      top.pred(pred);
/* 1453:1453 */      top.right.succ(succ);
/* 1454:1454 */      return top;
/* 1455:     */    }
/* 1456:     */    
/* 1457:1457 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1458:1458 */    Entry<K, V> top = new Entry();
/* 1459:1459 */    top.left(readTree(s, leftN, pred, top));
/* 1460:1460 */    top.key = s.readObject();
/* 1461:1461 */    top.value = s.readObject();
/* 1462:1462 */    top.black(true);
/* 1463:1463 */    top.right(readTree(s, rightN, top, succ));
/* 1464:1464 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1465:1465 */    return top;
/* 1466:     */  }
/* 1467:     */  
/* 1468:1468 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1469:     */    
/* 1471:1471 */    setActualComparator();
/* 1472:1472 */    allocatePaths();
/* 1473:1473 */    if (this.count != 0) {
/* 1474:1474 */      this.tree = readTree(s, this.count, null, null);
/* 1475:     */      
/* 1476:1476 */      Entry<K, V> e = this.tree;
/* 1477:1477 */      while (e.left() != null) e = e.left();
/* 1478:1478 */      this.firstEntry = e;
/* 1479:1479 */      e = this.tree;
/* 1480:1480 */      while (e.right() != null) e = e.right();
/* 1481:1481 */      this.lastEntry = e;
/* 1482:     */    }
/* 1483:     */  }
/* 1484:     */  
/* 1485:     */  private void checkNodePath() {}
/* 1486:     */  
/* 1487:1487 */  private int checkTree(Entry<K, V> e, int d, int D) { return 0; }
/* 1488:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */