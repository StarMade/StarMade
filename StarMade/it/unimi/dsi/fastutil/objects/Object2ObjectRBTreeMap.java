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
/*   70:     */public class Object2ObjectRBTreeMap<K, V>
/*   71:     */  extends AbstractObject2ObjectSortedMap<K, V>
/*   72:     */  implements Serializable, Cloneable
/*   73:     */{
/*   74:     */  protected transient Entry<K, V> tree;
/*   75:     */  protected int count;
/*   76:     */  protected transient Entry<K, V> firstEntry;
/*   77:     */  protected transient Entry<K, V> lastEntry;
/*   78:     */  protected volatile transient ObjectSortedSet<Object2ObjectMap.Entry<K, V>> entries;
/*   79:     */  protected volatile transient ObjectSortedSet<K> keys;
/*   80:     */  protected volatile transient ObjectCollection<V> values;
/*   81:     */  protected transient boolean modified;
/*   82:     */  protected Comparator<? super K> storedComparator;
/*   83:     */  protected transient Comparator<? super K> actualComparator;
/*   84:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   85:     */  private static final boolean ASSERTS = false;
/*   86:     */  private transient boolean[] dirPath;
/*   87:     */  private transient Entry<K, V>[] nodePath;
/*   88:     */  
/*   89:     */  public Object2ObjectRBTreeMap()
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
/*  117:     */  public Object2ObjectRBTreeMap(Comparator<? super K> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    this.storedComparator = c;
/*  121: 121 */    setActualComparator();
/*  122:     */  }
/*  123:     */  
/*  126:     */  public Object2ObjectRBTreeMap(Map<? extends K, ? extends V> m)
/*  127:     */  {
/*  128: 128 */    this();
/*  129: 129 */    putAll(m);
/*  130:     */  }
/*  131:     */  
/*  134:     */  public Object2ObjectRBTreeMap(SortedMap<K, V> m)
/*  135:     */  {
/*  136: 136 */    this(m.comparator());
/*  137: 137 */    putAll(m);
/*  138:     */  }
/*  139:     */  
/*  142:     */  public Object2ObjectRBTreeMap(Object2ObjectMap<? extends K, ? extends V> m)
/*  143:     */  {
/*  144: 144 */    this();
/*  145: 145 */    putAll(m);
/*  146:     */  }
/*  147:     */  
/*  150:     */  public Object2ObjectRBTreeMap(Object2ObjectSortedMap<K, V> m)
/*  151:     */  {
/*  152: 152 */    this(m.comparator());
/*  153: 153 */    putAll(m);
/*  154:     */  }
/*  155:     */  
/*  161:     */  public Object2ObjectRBTreeMap(K[] k, V[] v, Comparator<? super K> c)
/*  162:     */  {
/*  163: 163 */    this(c);
/*  164: 164 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  165: 165 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  166:     */    }
/*  167:     */  }
/*  168:     */  
/*  172:     */  public Object2ObjectRBTreeMap(K[] k, V[] v)
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
/*  597: 597 */  public boolean containsValue(Object v) { Object2ObjectRBTreeMap<K, V>.ValueIterator i = new ValueIterator(null);
/*  598:     */    
/*  599: 599 */    int j = this.count;
/*  600: 600 */    for (; j-- != 0; 
/*  601:     */        
/*  602: 602 */        return true)
/*  603:     */    {
/*  604:     */      label16:
/*  605: 601 */      Object ev = i.next();
/*  606: 602 */      if (ev == null ? v != null : !ev.equals(v)) break label16;
/*  607:     */    }
/*  608: 604 */    return false;
/*  609:     */  }
/*  610:     */  
/*  611: 607 */  public void clear() { this.count = 0;
/*  612: 608 */    this.tree = null;
/*  613: 609 */    this.entries = null;
/*  614: 610 */    this.values = null;
/*  615: 611 */    this.keys = null;
/*  616: 612 */    this.firstEntry = (this.lastEntry = null);
/*  617:     */  }
/*  618:     */  
/*  621:     */  private static final class Entry<K, V>
/*  622:     */    implements Cloneable, Object2ObjectMap.Entry<K, V>
/*  623:     */  {
/*  624:     */    private static final int BLACK_MASK = 1;
/*  625:     */    
/*  627:     */    private static final int SUCC_MASK = -2147483648;
/*  628:     */    
/*  630:     */    private static final int PRED_MASK = 1073741824;
/*  631:     */    
/*  632:     */    K key;
/*  633:     */    
/*  634:     */    V value;
/*  635:     */    
/*  636:     */    Entry<K, V> left;
/*  637:     */    
/*  638:     */    Entry<K, V> right;
/*  639:     */    
/*  640:     */    int info;
/*  641:     */    
/*  643:     */    Entry() {}
/*  644:     */    
/*  646:     */    Entry(K k, V v)
/*  647:     */    {
/*  648: 644 */      this.key = k;
/*  649: 645 */      this.value = v;
/*  650: 646 */      this.info = -1073741824;
/*  651:     */    }
/*  652:     */    
/*  656:     */    Entry<K, V> left()
/*  657:     */    {
/*  658: 654 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  659:     */    }
/*  660:     */    
/*  664:     */    Entry<K, V> right()
/*  665:     */    {
/*  666: 662 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  667:     */    }
/*  668:     */    
/*  670:     */    boolean pred()
/*  671:     */    {
/*  672: 668 */      return (this.info & 0x40000000) != 0;
/*  673:     */    }
/*  674:     */    
/*  676:     */    boolean succ()
/*  677:     */    {
/*  678: 674 */      return (this.info & 0x80000000) != 0;
/*  679:     */    }
/*  680:     */    
/*  682:     */    void pred(boolean pred)
/*  683:     */    {
/*  684: 680 */      if (pred) this.info |= 1073741824; else {
/*  685: 681 */        this.info &= -1073741825;
/*  686:     */      }
/*  687:     */    }
/*  688:     */    
/*  689:     */    void succ(boolean succ)
/*  690:     */    {
/*  691: 687 */      if (succ) this.info |= -2147483648; else {
/*  692: 688 */        this.info &= 2147483647;
/*  693:     */      }
/*  694:     */    }
/*  695:     */    
/*  696:     */    void pred(Entry<K, V> pred)
/*  697:     */    {
/*  698: 694 */      this.info |= 1073741824;
/*  699: 695 */      this.left = pred;
/*  700:     */    }
/*  701:     */    
/*  703:     */    void succ(Entry<K, V> succ)
/*  704:     */    {
/*  705: 701 */      this.info |= -2147483648;
/*  706: 702 */      this.right = succ;
/*  707:     */    }
/*  708:     */    
/*  710:     */    void left(Entry<K, V> left)
/*  711:     */    {
/*  712: 708 */      this.info &= -1073741825;
/*  713: 709 */      this.left = left;
/*  714:     */    }
/*  715:     */    
/*  717:     */    void right(Entry<K, V> right)
/*  718:     */    {
/*  719: 715 */      this.info &= 2147483647;
/*  720: 716 */      this.right = right;
/*  721:     */    }
/*  722:     */    
/*  724:     */    boolean black()
/*  725:     */    {
/*  726: 722 */      return (this.info & 0x1) != 0;
/*  727:     */    }
/*  728:     */    
/*  730:     */    void black(boolean black)
/*  731:     */    {
/*  732: 728 */      if (black) this.info |= 1; else {
/*  733: 729 */        this.info &= -2;
/*  734:     */      }
/*  735:     */    }
/*  736:     */    
/*  738:     */    Entry<K, V> next()
/*  739:     */    {
/*  740: 736 */      Entry<K, V> next = this.right;
/*  741: 737 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  742: 738 */      return next;
/*  743:     */    }
/*  744:     */    
/*  747:     */    Entry<K, V> prev()
/*  748:     */    {
/*  749: 745 */      Entry<K, V> prev = this.left;
/*  750: 746 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  751: 747 */      return prev;
/*  752:     */    }
/*  753:     */    
/*  754: 750 */    public K getKey() { return this.key; }
/*  755:     */    
/*  757: 753 */    public V getValue() { return this.value; }
/*  758:     */    
/*  759:     */    public V setValue(V value) {
/*  760: 756 */      V oldValue = this.value;
/*  761: 757 */      this.value = value;
/*  762: 758 */      return oldValue;
/*  763:     */    }
/*  764:     */    
/*  765:     */    public Entry<K, V> clone() {
/*  766:     */      Entry<K, V> c;
/*  767:     */      try {
/*  768: 764 */        c = (Entry)super.clone();
/*  769:     */      }
/*  770:     */      catch (CloneNotSupportedException cantHappen) {
/*  771: 767 */        throw new InternalError();
/*  772:     */      }
/*  773: 769 */      c.key = this.key;
/*  774: 770 */      c.value = this.value;
/*  775: 771 */      c.info = this.info;
/*  776: 772 */      return c;
/*  777:     */    }
/*  778:     */    
/*  779:     */    public boolean equals(Object o) {
/*  780: 776 */      if (!(o instanceof Map.Entry)) return false;
/*  781: 777 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  782: 778 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/*  783:     */    }
/*  784:     */    
/*  785: 781 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode()); }
/*  786:     */    
/*  787:     */    public String toString() {
/*  788: 784 */      return this.key + "=>" + this.value;
/*  789:     */    }
/*  790:     */  }
/*  791:     */  
/*  820:     */  public boolean containsKey(Object k)
/*  821:     */  {
/*  822: 818 */    return findKey(k) != null;
/*  823:     */  }
/*  824:     */  
/*  825: 821 */  public int size() { return this.count; }
/*  826:     */  
/*  827:     */  public boolean isEmpty() {
/*  828: 824 */    return this.count == 0;
/*  829:     */  }
/*  830:     */  
/*  831:     */  public V get(Object k) {
/*  832: 828 */    Entry<K, V> e = findKey(k);
/*  833: 829 */    return e == null ? this.defRetValue : e.value;
/*  834:     */  }
/*  835:     */  
/*  836: 832 */  public K firstKey() { if (this.tree == null) throw new NoSuchElementException();
/*  837: 833 */    return this.firstEntry.key;
/*  838:     */  }
/*  839:     */  
/*  840: 836 */  public K lastKey() { if (this.tree == null) throw new NoSuchElementException();
/*  841: 837 */    return this.lastEntry.key;
/*  842:     */  }
/*  843:     */  
/*  846:     */  private class TreeIterator
/*  847:     */  {
/*  848:     */    Object2ObjectRBTreeMap.Entry<K, V> prev;
/*  849:     */    
/*  851:     */    Object2ObjectRBTreeMap.Entry<K, V> next;
/*  852:     */    
/*  853:     */    Object2ObjectRBTreeMap.Entry<K, V> curr;
/*  854:     */    
/*  855: 851 */    int index = 0;
/*  856:     */    
/*  857: 853 */    TreeIterator() { this.next = Object2ObjectRBTreeMap.this.firstEntry; }
/*  858:     */    
/*  859:     */    TreeIterator() {
/*  860: 856 */      if ((this.next = Object2ObjectRBTreeMap.this.locateKey(k)) != null)
/*  861: 857 */        if (Object2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/*  862: 858 */          this.prev = this.next;
/*  863: 859 */          this.next = this.next.next();
/*  864:     */        } else {
/*  865: 861 */          this.prev = this.next.prev();
/*  866:     */        } }
/*  867:     */    
/*  868: 864 */    public boolean hasNext() { return this.next != null; }
/*  869: 865 */    public boolean hasPrevious() { return this.prev != null; }
/*  870:     */    
/*  871: 867 */    void updateNext() { this.next = this.next.next(); }
/*  872:     */    
/*  873:     */    Object2ObjectRBTreeMap.Entry<K, V> nextEntry() {
/*  874: 870 */      if (!hasNext()) throw new NoSuchElementException();
/*  875: 871 */      this.curr = (this.prev = this.next);
/*  876: 872 */      this.index += 1;
/*  877: 873 */      updateNext();
/*  878: 874 */      return this.curr;
/*  879:     */    }
/*  880:     */    
/*  881: 877 */    void updatePrevious() { this.prev = this.prev.prev(); }
/*  882:     */    
/*  883:     */    Object2ObjectRBTreeMap.Entry<K, V> previousEntry() {
/*  884: 880 */      if (!hasPrevious()) throw new NoSuchElementException();
/*  885: 881 */      this.curr = (this.next = this.prev);
/*  886: 882 */      this.index -= 1;
/*  887: 883 */      updatePrevious();
/*  888: 884 */      return this.curr;
/*  889:     */    }
/*  890:     */    
/*  891: 887 */    public int nextIndex() { return this.index; }
/*  892:     */    
/*  894: 890 */    public int previousIndex() { return this.index - 1; }
/*  895:     */    
/*  896:     */    public void remove() {
/*  897: 893 */      if (this.curr == null) { throw new IllegalStateException();
/*  898:     */      }
/*  899:     */      
/*  900: 896 */      if (this.curr == this.prev) this.index -= 1;
/*  901: 897 */      this.next = (this.prev = this.curr);
/*  902: 898 */      updatePrevious();
/*  903: 899 */      updateNext();
/*  904: 900 */      Object2ObjectRBTreeMap.this.remove(this.curr.key);
/*  905: 901 */      this.curr = null;
/*  906:     */    }
/*  907:     */    
/*  908: 904 */    public int skip(int n) { int i = n;
/*  909: 905 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  910: 906 */      return n - i - 1;
/*  911:     */    }
/*  912:     */    
/*  913: 909 */    public int back(int n) { int i = n;
/*  914: 910 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  915: 911 */      return n - i - 1;
/*  916:     */    }
/*  917:     */  }
/*  918:     */  
/*  919:     */  private class EntryIterator
/*  920:     */    extends Object2ObjectRBTreeMap<K, V>.TreeIterator
/*  921:     */    implements ObjectListIterator<Object2ObjectMap.Entry<K, V>>
/*  922:     */  {
/*  923: 919 */    EntryIterator() { super(); }
/*  924:     */    
/*  925: 921 */    EntryIterator() { super(k); }
/*  926:     */    
/*  927: 923 */    public Object2ObjectMap.Entry<K, V> next() { return nextEntry(); }
/*  928: 924 */    public Object2ObjectMap.Entry<K, V> previous() { return previousEntry(); }
/*  929: 925 */    public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  930: 926 */    public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  931:     */  }
/*  932:     */  
/*  933: 929 */  public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  934: 930 */        final Comparator<? super Object2ObjectMap.Entry<K, V>> comparator = new Comparator() {
/*  935:     */          public int compare(Object2ObjectMap.Entry<K, V> x, Object2ObjectMap.Entry<K, V> y) {
/*  936: 932 */            return Object2ObjectRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*  937:     */          }
/*  938:     */        };
/*  939:     */        
/*  940: 936 */        public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return this.comparator; }
/*  941:     */        
/*  942:     */        public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator() {
/*  943: 939 */          return new Object2ObjectRBTreeMap.EntryIterator(Object2ObjectRBTreeMap.this);
/*  944:     */        }
/*  945:     */        
/*  946: 942 */        public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectRBTreeMap.EntryIterator(Object2ObjectRBTreeMap.this, from.getKey()); }
/*  947:     */        
/*  948:     */        public boolean contains(Object o)
/*  949:     */        {
/*  950: 946 */          if (!(o instanceof Map.Entry)) return false;
/*  951: 947 */          Map.Entry<K, V> e = (Map.Entry)o;
/*  952: 948 */          Object2ObjectRBTreeMap.Entry<K, V> f = Object2ObjectRBTreeMap.this.findKey(e.getKey());
/*  953: 949 */          return e.equals(f);
/*  954:     */        }
/*  955:     */        
/*  956:     */        public boolean remove(Object o) {
/*  957: 953 */          if (!(o instanceof Map.Entry)) return false;
/*  958: 954 */          Map.Entry<K, V> e = (Map.Entry)o;
/*  959: 955 */          Object2ObjectRBTreeMap.Entry<K, V> f = Object2ObjectRBTreeMap.this.findKey(e.getKey());
/*  960: 956 */          if (f != null) Object2ObjectRBTreeMap.this.remove(f.key);
/*  961: 957 */          return f != null; }
/*  962:     */        
/*  963: 959 */        public int size() { return Object2ObjectRBTreeMap.this.count; }
/*  964: 960 */        public void clear() { Object2ObjectRBTreeMap.this.clear(); }
/*  965: 961 */        public Object2ObjectMap.Entry<K, V> first() { return Object2ObjectRBTreeMap.this.firstEntry; }
/*  966: 962 */        public Object2ObjectMap.Entry<K, V> last() { return Object2ObjectRBTreeMap.this.lastEntry; }
/*  967: 963 */        public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> from, Object2ObjectMap.Entry<K, V> to) { return Object2ObjectRBTreeMap.this.subMap(from.getKey(), to.getKey()).object2ObjectEntrySet(); }
/*  968: 964 */        public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> to) { return Object2ObjectRBTreeMap.this.headMap(to.getKey()).object2ObjectEntrySet(); }
/*  969: 965 */        public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> from) { return Object2ObjectRBTreeMap.this.tailMap(from.getKey()).object2ObjectEntrySet(); }
/*  970:     */      };
/*  971: 967 */    return this.entries;
/*  972:     */  }
/*  973:     */  
/*  976:     */  private final class KeyIterator
/*  977:     */    extends Object2ObjectRBTreeMap<K, V>.TreeIterator
/*  978:     */    implements ObjectListIterator<K>
/*  979:     */  {
/*  980: 976 */    public KeyIterator() { super(); }
/*  981: 977 */    public KeyIterator() { super(k); }
/*  982: 978 */    public K next() { return nextEntry().key; }
/*  983: 979 */    public K previous() { return previousEntry().key; }
/*  984: 980 */    public void set(K k) { throw new UnsupportedOperationException(); }
/*  985: 981 */    public void add(K k) { throw new UnsupportedOperationException(); }
/*  986:     */  }
/*  987:     */  
/*  988: 984 */  private class KeySet extends AbstractObject2ObjectSortedMap.KeySet { private KeySet() { super(); }
/*  989: 985 */    public ObjectBidirectionalIterator<K> iterator() { return new Object2ObjectRBTreeMap.KeyIterator(Object2ObjectRBTreeMap.this); }
/*  990: 986 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ObjectRBTreeMap.KeyIterator(Object2ObjectRBTreeMap.this, from); }
/*  991:     */  }
/*  992:     */  
/*  999:     */  public ObjectSortedSet<K> keySet()
/* 1000:     */  {
/* 1001: 997 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1002: 998 */    return this.keys;
/* 1003:     */  }
/* 1004:     */  
/* 1006:     */  private final class ValueIterator
/* 1007:     */    extends Object2ObjectRBTreeMap<K, V>.TreeIterator
/* 1008:     */    implements ObjectListIterator<V>
/* 1009:     */  {
/* 1010:1006 */    private ValueIterator() { super(); }
/* 1011:1007 */    public V next() { return nextEntry().value; }
/* 1012:1008 */    public V previous() { return previousEntry().value; }
/* 1013:1009 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1014:1010 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1015:     */  }
/* 1016:     */  
/* 1023:     */  public ObjectCollection<V> values()
/* 1024:     */  {
/* 1025:1021 */    if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1026:     */        public ObjectIterator<V> iterator() {
/* 1027:1023 */          return new Object2ObjectRBTreeMap.ValueIterator(Object2ObjectRBTreeMap.this, null);
/* 1028:     */        }
/* 1029:     */        
/* 1030:1026 */        public boolean contains(Object k) { return Object2ObjectRBTreeMap.this.containsValue(k); }
/* 1031:     */        
/* 1032:     */        public int size() {
/* 1033:1029 */          return Object2ObjectRBTreeMap.this.count;
/* 1034:     */        }
/* 1035:     */        
/* 1036:1032 */        public void clear() { Object2ObjectRBTreeMap.this.clear(); }
/* 1037:     */      };
/* 1038:     */    }
/* 1039:1035 */    return this.values;
/* 1040:     */  }
/* 1041:     */  
/* 1042:1038 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1043:     */  
/* 1044:     */  public Object2ObjectSortedMap<K, V> headMap(K to) {
/* 1045:1041 */    return new Submap(null, true, to, false);
/* 1046:     */  }
/* 1047:     */  
/* 1048:1044 */  public Object2ObjectSortedMap<K, V> tailMap(K from) { return new Submap(from, false, null, true); }
/* 1049:     */  
/* 1050:     */  public Object2ObjectSortedMap<K, V> subMap(K from, K to) {
/* 1051:1047 */    return new Submap(from, false, to, false);
/* 1052:     */  }
/* 1053:     */  
/* 1057:     */  private final class Submap
/* 1058:     */    extends AbstractObject2ObjectSortedMap<K, V>
/* 1059:     */    implements Serializable
/* 1060:     */  {
/* 1061:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1062:     */    
/* 1065:     */    K from;
/* 1066:     */    
/* 1068:     */    K to;
/* 1069:     */    
/* 1071:     */    boolean bottom;
/* 1072:     */    
/* 1074:     */    boolean top;
/* 1075:     */    
/* 1077:     */    protected volatile transient ObjectSortedSet<Object2ObjectMap.Entry<K, V>> entries;
/* 1078:     */    
/* 1080:     */    protected volatile transient ObjectSortedSet<K> keys;
/* 1081:     */    
/* 1083:     */    protected volatile transient ObjectCollection<V> values;
/* 1084:     */    
/* 1087:     */    public Submap(boolean from, K bottom, boolean to)
/* 1088:     */    {
/* 1089:1085 */      if ((!bottom) && (!top) && (Object2ObjectRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1090:1086 */      this.from = from;
/* 1091:1087 */      this.bottom = bottom;
/* 1092:1088 */      this.to = to;
/* 1093:1089 */      this.top = top;
/* 1094:1090 */      this.defRetValue = Object2ObjectRBTreeMap.this.defRetValue;
/* 1095:     */    }
/* 1096:     */    
/* 1097:1093 */    public void clear() { Object2ObjectRBTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1098:1094 */      while (i.hasNext()) {
/* 1099:1095 */        i.nextEntry();
/* 1100:1096 */        i.remove();
/* 1101:     */      }
/* 1102:     */    }
/* 1103:     */    
/* 1106:     */    final boolean in(K k)
/* 1107:     */    {
/* 1108:1104 */      return ((this.bottom) || (Object2ObjectRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ObjectRBTreeMap.this.compare(k, this.to) < 0));
/* 1109:     */    }
/* 1110:     */    
/* 1111:     */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet() {
/* 1112:1108 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1113:     */          public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator() {
/* 1114:1110 */            return new Object2ObjectRBTreeMap.Submap.SubmapEntryIterator(Object2ObjectRBTreeMap.Submap.this);
/* 1115:     */          }
/* 1116:     */          
/* 1117:1113 */          public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectRBTreeMap.Submap.SubmapEntryIterator(Object2ObjectRBTreeMap.Submap.this, from.getKey()); }
/* 1118:     */          
/* 1119:1115 */          public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return Object2ObjectRBTreeMap.this.object2ObjectEntrySet().comparator(); }
/* 1120:     */          
/* 1121:     */          public boolean contains(Object o) {
/* 1122:1118 */            if (!(o instanceof Map.Entry)) return false;
/* 1123:1119 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1124:1120 */            Object2ObjectRBTreeMap.Entry<K, V> f = Object2ObjectRBTreeMap.this.findKey(e.getKey());
/* 1125:1121 */            return (f != null) && (Object2ObjectRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1126:     */          }
/* 1127:     */          
/* 1128:     */          public boolean remove(Object o) {
/* 1129:1125 */            if (!(o instanceof Map.Entry)) return false;
/* 1130:1126 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1131:1127 */            Object2ObjectRBTreeMap.Entry<K, V> f = Object2ObjectRBTreeMap.this.findKey(e.getKey());
/* 1132:1128 */            if ((f != null) && (Object2ObjectRBTreeMap.Submap.this.in(f.key))) Object2ObjectRBTreeMap.Submap.this.remove(f.key);
/* 1133:1129 */            return f != null;
/* 1134:     */          }
/* 1135:     */          
/* 1136:1132 */          public int size() { int c = 0;
/* 1137:1133 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1138:1134 */            return c; }
/* 1139:     */          
/* 1140:1136 */          public boolean isEmpty() { return !new Object2ObjectRBTreeMap.Submap.SubmapIterator(Object2ObjectRBTreeMap.Submap.this).hasNext(); }
/* 1141:1137 */          public void clear() { Object2ObjectRBTreeMap.Submap.this.clear(); }
/* 1142:1138 */          public Object2ObjectMap.Entry<K, V> first() { return Object2ObjectRBTreeMap.Submap.this.firstEntry(); }
/* 1143:1139 */          public Object2ObjectMap.Entry<K, V> last() { return Object2ObjectRBTreeMap.Submap.this.lastEntry(); }
/* 1144:1140 */          public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> from, Object2ObjectMap.Entry<K, V> to) { return Object2ObjectRBTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ObjectEntrySet(); }
/* 1145:1141 */          public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> to) { return Object2ObjectRBTreeMap.Submap.this.headMap(to.getKey()).object2ObjectEntrySet(); }
/* 1146:1142 */          public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> from) { return Object2ObjectRBTreeMap.Submap.this.tailMap(from.getKey()).object2ObjectEntrySet(); }
/* 1147:     */        };
/* 1148:1144 */      return this.entries; }
/* 1149:     */    
/* 1150:1146 */    private class KeySet extends AbstractObject2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1151:1147 */      public ObjectBidirectionalIterator<K> iterator() { return new Object2ObjectRBTreeMap.Submap.SubmapKeyIterator(Object2ObjectRBTreeMap.Submap.this); }
/* 1152:1148 */      public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ObjectRBTreeMap.Submap.SubmapKeyIterator(Object2ObjectRBTreeMap.Submap.this, from); }
/* 1153:     */    }
/* 1154:     */    
/* 1155:1151 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1156:1152 */      return this.keys;
/* 1157:     */    }
/* 1158:     */    
/* 1159:1155 */    public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1160:     */          public ObjectIterator<V> iterator() {
/* 1161:1157 */            return new Object2ObjectRBTreeMap.Submap.SubmapValueIterator(Object2ObjectRBTreeMap.Submap.this, null);
/* 1162:     */          }
/* 1163:     */          
/* 1164:1160 */          public boolean contains(Object k) { return Object2ObjectRBTreeMap.Submap.this.containsValue(k); }
/* 1165:     */          
/* 1166:     */          public int size() {
/* 1167:1163 */            return Object2ObjectRBTreeMap.Submap.this.size();
/* 1168:     */          }
/* 1169:     */          
/* 1170:1166 */          public void clear() { Object2ObjectRBTreeMap.Submap.this.clear(); }
/* 1171:     */        };
/* 1172:     */      }
/* 1173:1169 */      return this.values;
/* 1174:     */    }
/* 1175:     */    
/* 1177:1173 */    public boolean containsKey(Object k) { return (in(k)) && (Object2ObjectRBTreeMap.this.containsKey(k)); }
/* 1178:     */    
/* 1179:     */    public boolean containsValue(Object v) {
/* 1180:1176 */      Object2ObjectRBTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1181:1178 */      for (; 
/* 1182:1178 */          i.hasNext(); 
/* 1183:     */          
/* 1184:1180 */          return true)
/* 1185:     */      {
/* 1186:     */        label9:
/* 1187:1179 */        Object ev = i.nextEntry().value;
/* 1188:1180 */        if (ev == null ? v != null : !ev.equals(v)) break label9;
/* 1189:     */      }
/* 1190:1182 */      return false;
/* 1191:     */    }
/* 1192:     */    
/* 1193:     */    public V get(Object k)
/* 1194:     */    {
/* 1195:1187 */      K kk = k;
/* 1196:1188 */      Object2ObjectRBTreeMap.Entry<K, V> e; return (in(kk)) && ((e = Object2ObjectRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1197:     */    }
/* 1198:     */    
/* 1199:1191 */    public V put(K k, V v) { Object2ObjectRBTreeMap.this.modified = false;
/* 1200:1192 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1201:1193 */      V oldValue = Object2ObjectRBTreeMap.this.put(k, v);
/* 1202:1194 */      return Object2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1203:     */    }
/* 1204:     */    
/* 1205:     */    public V remove(Object k) {
/* 1206:1198 */      Object2ObjectRBTreeMap.this.modified = false;
/* 1207:1199 */      if (!in(k)) return this.defRetValue;
/* 1208:1200 */      V oldValue = Object2ObjectRBTreeMap.this.remove(k);
/* 1209:1201 */      return Object2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1210:     */    }
/* 1211:     */    
/* 1212:1204 */    public int size() { Object2ObjectRBTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1213:1205 */      int n = 0;
/* 1214:1206 */      while (i.hasNext()) {
/* 1215:1207 */        n++;
/* 1216:1208 */        i.nextEntry();
/* 1217:     */      }
/* 1218:1210 */      return n;
/* 1219:     */    }
/* 1220:     */    
/* 1221:1213 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1222:     */    
/* 1224:1216 */    public Comparator<? super K> comparator() { return Object2ObjectRBTreeMap.this.actualComparator; }
/* 1225:     */    
/* 1226:     */    public Object2ObjectSortedMap<K, V> headMap(K to) {
/* 1227:1219 */      if (this.top) return new Submap(Object2ObjectRBTreeMap.this, this.from, this.bottom, to, false);
/* 1228:1220 */      return Object2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ObjectRBTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1229:     */    }
/* 1230:     */    
/* 1231:1223 */    public Object2ObjectSortedMap<K, V> tailMap(K from) { if (this.bottom) return new Submap(Object2ObjectRBTreeMap.this, from, false, this.to, this.top);
/* 1232:1224 */      return Object2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ObjectRBTreeMap.this, from, false, this.to, this.top) : this;
/* 1233:     */    }
/* 1234:     */    
/* 1235:1227 */    public Object2ObjectSortedMap<K, V> subMap(K from, K to) { if ((this.top) && (this.bottom)) return new Submap(Object2ObjectRBTreeMap.this, from, false, to, false);
/* 1236:1228 */      if (!this.top) to = Object2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1237:1229 */      if (!this.bottom) from = Object2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1238:1230 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1239:1231 */      return new Submap(Object2ObjectRBTreeMap.this, from, false, to, false);
/* 1240:     */    }
/* 1241:     */    
/* 1244:     */    public Object2ObjectRBTreeMap.Entry<K, V> firstEntry()
/* 1245:     */    {
/* 1246:1238 */      if (Object2ObjectRBTreeMap.this.tree == null) return null;
/* 1247:     */      Object2ObjectRBTreeMap.Entry<K, V> e;
/* 1248:     */      Object2ObjectRBTreeMap.Entry<K, V> e;
/* 1249:1241 */      if (this.bottom) { e = Object2ObjectRBTreeMap.this.firstEntry;
/* 1250:     */      } else {
/* 1251:1243 */        e = Object2ObjectRBTreeMap.this.locateKey(this.from);
/* 1252:     */        
/* 1253:1245 */        if (Object2ObjectRBTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1254:     */        }
/* 1255:     */      }
/* 1256:1248 */      if ((e == null) || ((!this.top) && (Object2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1257:1249 */      return e;
/* 1258:     */    }
/* 1259:     */    
/* 1262:     */    public Object2ObjectRBTreeMap.Entry<K, V> lastEntry()
/* 1263:     */    {
/* 1264:1256 */      if (Object2ObjectRBTreeMap.this.tree == null) return null;
/* 1265:     */      Object2ObjectRBTreeMap.Entry<K, V> e;
/* 1266:     */      Object2ObjectRBTreeMap.Entry<K, V> e;
/* 1267:1259 */      if (this.top) { e = Object2ObjectRBTreeMap.this.lastEntry;
/* 1268:     */      } else {
/* 1269:1261 */        e = Object2ObjectRBTreeMap.this.locateKey(this.to);
/* 1270:     */        
/* 1271:1263 */        if (Object2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1272:     */        }
/* 1273:     */      }
/* 1274:1266 */      if ((e == null) || ((!this.bottom) && (Object2ObjectRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1275:1267 */      return e;
/* 1276:     */    }
/* 1277:     */    
/* 1278:1270 */    public K firstKey() { Object2ObjectRBTreeMap.Entry<K, V> e = firstEntry();
/* 1279:1271 */      if (e == null) throw new NoSuchElementException();
/* 1280:1272 */      return e.key;
/* 1281:     */    }
/* 1282:     */    
/* 1283:1275 */    public K lastKey() { Object2ObjectRBTreeMap.Entry<K, V> e = lastEntry();
/* 1284:1276 */      if (e == null) throw new NoSuchElementException();
/* 1285:1277 */      return e.key;
/* 1286:     */    }
/* 1287:     */    
/* 1290:     */    private class SubmapIterator
/* 1291:     */      extends Object2ObjectRBTreeMap.TreeIterator
/* 1292:     */    {
/* 1293:     */      SubmapIterator()
/* 1294:     */      {
/* 1295:1287 */        super();
/* 1296:1288 */        this.next = Object2ObjectRBTreeMap.Submap.this.firstEntry();
/* 1297:     */      }
/* 1298:     */      
/* 1299:1291 */      SubmapIterator() { this();
/* 1300:1292 */        if (this.next != null)
/* 1301:1293 */          if ((!Object2ObjectRBTreeMap.Submap.this.bottom) && (Object2ObjectRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1302:1294 */          } else if ((!Object2ObjectRBTreeMap.Submap.this.top) && (Object2ObjectRBTreeMap.this.compare(k, (this.prev = Object2ObjectRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1303:     */          } else {
/* 1304:1296 */            this.next = Object2ObjectRBTreeMap.this.locateKey(k);
/* 1305:1297 */            if (Object2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1306:1298 */              this.prev = this.next;
/* 1307:1299 */              this.next = this.next.next();
/* 1308:     */            } else {
/* 1309:1301 */              this.prev = this.next.prev();
/* 1310:     */            }
/* 1311:     */          }
/* 1312:     */      }
/* 1313:     */      
/* 1314:1306 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1315:1307 */        if ((!Object2ObjectRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ObjectRBTreeMap.this.compare(this.prev.key, Object2ObjectRBTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1316:     */      }
/* 1317:     */      
/* 1318:1310 */      void updateNext() { this.next = this.next.next();
/* 1319:1311 */        if ((!Object2ObjectRBTreeMap.Submap.this.top) && (this.next != null) && (Object2ObjectRBTreeMap.this.compare(this.next.key, Object2ObjectRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1320:     */      }
/* 1321:     */    }
/* 1322:     */    
/* 1323:1315 */    private class SubmapEntryIterator extends Object2ObjectRBTreeMap<K, V>.Submap.SubmapIterator implements ObjectListIterator<Object2ObjectMap.Entry<K, V>> { SubmapEntryIterator() { super(); }
/* 1324:     */      
/* 1325:1317 */      SubmapEntryIterator() { super(k); }
/* 1326:     */      
/* 1327:1319 */      public Object2ObjectMap.Entry<K, V> next() { return nextEntry(); }
/* 1328:1320 */      public Object2ObjectMap.Entry<K, V> previous() { return previousEntry(); }
/* 1329:1321 */      public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1330:1322 */      public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1331:     */    }
/* 1332:     */    
/* 1337:     */    private final class SubmapKeyIterator
/* 1338:     */      extends Object2ObjectRBTreeMap<K, V>.Submap.SubmapIterator
/* 1339:     */      implements ObjectListIterator<K>
/* 1340:     */    {
/* 1341:1333 */      public SubmapKeyIterator() { super(); }
/* 1342:1334 */      public SubmapKeyIterator() { super(from); }
/* 1343:1335 */      public K next() { return nextEntry().key; }
/* 1344:1336 */      public K previous() { return previousEntry().key; }
/* 1345:1337 */      public void set(K k) { throw new UnsupportedOperationException(); }
/* 1346:1338 */      public void add(K k) { throw new UnsupportedOperationException(); }
/* 1347:     */    }
/* 1348:     */    
/* 1352:     */    private final class SubmapValueIterator
/* 1353:     */      extends Object2ObjectRBTreeMap<K, V>.Submap.SubmapIterator
/* 1354:     */      implements ObjectListIterator<V>
/* 1355:     */    {
/* 1356:1348 */      private SubmapValueIterator() { super(); }
/* 1357:1349 */      public V next() { return nextEntry().value; }
/* 1358:1350 */      public V previous() { return previousEntry().value; }
/* 1359:1351 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1360:1352 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1361:     */    }
/* 1362:     */  }
/* 1363:     */  
/* 1367:     */  public Object2ObjectRBTreeMap<K, V> clone()
/* 1368:     */  {
/* 1369:     */    Object2ObjectRBTreeMap<K, V> c;
/* 1370:     */    
/* 1372:     */    try
/* 1373:     */    {
/* 1374:1366 */      c = (Object2ObjectRBTreeMap)super.clone();
/* 1375:     */    }
/* 1376:     */    catch (CloneNotSupportedException cantHappen) {
/* 1377:1369 */      throw new InternalError();
/* 1378:     */    }
/* 1379:1371 */    c.keys = null;
/* 1380:1372 */    c.values = null;
/* 1381:1373 */    c.entries = null;
/* 1382:1374 */    c.allocatePaths();
/* 1383:1375 */    if (this.count != 0)
/* 1384:     */    {
/* 1385:1377 */      Entry<K, V> rp = new Entry();Entry<K, V> rq = new Entry();
/* 1386:1378 */      Entry<K, V> p = rp;
/* 1387:1379 */      rp.left(this.tree);
/* 1388:1380 */      Entry<K, V> q = rq;
/* 1389:1381 */      rq.pred(null);
/* 1390:     */      for (;;) {
/* 1391:1383 */        if (!p.pred()) {
/* 1392:1384 */          Entry<K, V> e = p.left.clone();
/* 1393:1385 */          e.pred(q.left);
/* 1394:1386 */          e.succ(q);
/* 1395:1387 */          q.left(e);
/* 1396:1388 */          p = p.left;
/* 1397:1389 */          q = q.left;
/* 1398:     */        }
/* 1399:     */        else {
/* 1400:1392 */          while (p.succ()) {
/* 1401:1393 */            p = p.right;
/* 1402:1394 */            if (p == null) {
/* 1403:1395 */              q.right = null;
/* 1404:1396 */              c.tree = rq.left;
/* 1405:1397 */              c.firstEntry = c.tree;
/* 1406:1398 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1407:1399 */              c.lastEntry = c.tree;
/* 1408:1400 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1409:1401 */              return c;
/* 1410:     */            }
/* 1411:1403 */            q = q.right;
/* 1412:     */          }
/* 1413:1405 */          p = p.right;
/* 1414:1406 */          q = q.right;
/* 1415:     */        }
/* 1416:1408 */        if (!p.succ()) {
/* 1417:1409 */          Entry<K, V> e = p.right.clone();
/* 1418:1410 */          e.succ(q.right);
/* 1419:1411 */          e.pred(q);
/* 1420:1412 */          q.right(e);
/* 1421:     */        }
/* 1422:     */      }
/* 1423:     */    }
/* 1424:1416 */    return c;
/* 1425:     */  }
/* 1426:     */  
/* 1427:1419 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1428:1420 */    Object2ObjectRBTreeMap<K, V>.EntryIterator i = new EntryIterator();
/* 1429:     */    
/* 1430:1422 */    s.defaultWriteObject();
/* 1431:1423 */    while (n-- != 0) {
/* 1432:1424 */      Entry<K, V> e = i.nextEntry();
/* 1433:1425 */      s.writeObject(e.key);
/* 1434:1426 */      s.writeObject(e.value);
/* 1435:     */    }
/* 1436:     */  }
/* 1437:     */  
/* 1443:     */  private Entry<K, V> readTree(ObjectInputStream s, int n, Entry<K, V> pred, Entry<K, V> succ)
/* 1444:     */    throws IOException, ClassNotFoundException
/* 1445:     */  {
/* 1446:1438 */    if (n == 1) {
/* 1447:1439 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1448:1440 */      top.pred(pred);
/* 1449:1441 */      top.succ(succ);
/* 1450:1442 */      top.black(true);
/* 1451:1443 */      return top;
/* 1452:     */    }
/* 1453:1445 */    if (n == 2)
/* 1454:     */    {
/* 1456:1448 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1457:1449 */      top.black(true);
/* 1458:1450 */      top.right(new Entry(s.readObject(), s.readObject()));
/* 1459:1451 */      top.right.pred(top);
/* 1460:1452 */      top.pred(pred);
/* 1461:1453 */      top.right.succ(succ);
/* 1462:1454 */      return top;
/* 1463:     */    }
/* 1464:     */    
/* 1465:1457 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1466:1458 */    Entry<K, V> top = new Entry();
/* 1467:1459 */    top.left(readTree(s, leftN, pred, top));
/* 1468:1460 */    top.key = s.readObject();
/* 1469:1461 */    top.value = s.readObject();
/* 1470:1462 */    top.black(true);
/* 1471:1463 */    top.right(readTree(s, rightN, top, succ));
/* 1472:1464 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1473:1465 */    return top;
/* 1474:     */  }
/* 1475:     */  
/* 1476:1468 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1477:     */    
/* 1479:1471 */    setActualComparator();
/* 1480:1472 */    allocatePaths();
/* 1481:1473 */    if (this.count != 0) {
/* 1482:1474 */      this.tree = readTree(s, this.count, null, null);
/* 1483:     */      
/* 1484:1476 */      Entry<K, V> e = this.tree;
/* 1485:1477 */      while (e.left() != null) e = e.left();
/* 1486:1478 */      this.firstEntry = e;
/* 1487:1479 */      e = this.tree;
/* 1488:1480 */      while (e.right() != null) e = e.right();
/* 1489:1481 */      this.lastEntry = e;
/* 1490:     */    }
/* 1491:     */  }
/* 1492:     */  
/* 1493:     */  private void checkNodePath() {}
/* 1494:     */  
/* 1495:1487 */  private int checkTree(Entry<K, V> e, int d, int D) { return 0; }
/* 1496:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */