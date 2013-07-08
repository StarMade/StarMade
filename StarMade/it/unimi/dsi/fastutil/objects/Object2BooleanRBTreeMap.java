/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*    4:     */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*    5:     */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanListIterator;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.ObjectInputStream;
/*    9:     */import java.io.ObjectOutputStream;
/*   10:     */import java.io.Serializable;
/*   11:     */import java.util.Comparator;
/*   12:     */import java.util.Iterator;
/*   13:     */import java.util.Map;
/*   14:     */import java.util.Map.Entry;
/*   15:     */import java.util.NoSuchElementException;
/*   16:     */import java.util.SortedMap;
/*   17:     */
/*   72:     */public class Object2BooleanRBTreeMap<K>
/*   73:     */  extends AbstractObject2BooleanSortedMap<K>
/*   74:     */  implements Serializable, Cloneable
/*   75:     */{
/*   76:     */  protected transient Entry<K> tree;
/*   77:     */  protected int count;
/*   78:     */  protected transient Entry<K> firstEntry;
/*   79:     */  protected transient Entry<K> lastEntry;
/*   80:     */  protected volatile transient ObjectSortedSet<Object2BooleanMap.Entry<K>> entries;
/*   81:     */  protected volatile transient ObjectSortedSet<K> keys;
/*   82:     */  protected volatile transient BooleanCollection values;
/*   83:     */  protected transient boolean modified;
/*   84:     */  protected Comparator<? super K> storedComparator;
/*   85:     */  protected transient Comparator<? super K> actualComparator;
/*   86:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   87:     */  private static final boolean ASSERTS = false;
/*   88:     */  private transient boolean[] dirPath;
/*   89:     */  private transient Entry<K>[] nodePath;
/*   90:     */  
/*   91:     */  public Object2BooleanRBTreeMap()
/*   92:     */  {
/*   93:  93 */    allocatePaths();
/*   94:     */    
/*   98:  98 */    this.tree = null;
/*   99:  99 */    this.count = 0;
/*  100:     */  }
/*  101:     */  
/*  110:     */  private void setActualComparator()
/*  111:     */  {
/*  112: 112 */    this.actualComparator = this.storedComparator;
/*  113:     */  }
/*  114:     */  
/*  117:     */  public Object2BooleanRBTreeMap(Comparator<? super K> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    this.storedComparator = c;
/*  121: 121 */    setActualComparator();
/*  122:     */  }
/*  123:     */  
/*  126:     */  public Object2BooleanRBTreeMap(Map<? extends K, ? extends Boolean> m)
/*  127:     */  {
/*  128: 128 */    this();
/*  129: 129 */    putAll(m);
/*  130:     */  }
/*  131:     */  
/*  134:     */  public Object2BooleanRBTreeMap(SortedMap<K, Boolean> m)
/*  135:     */  {
/*  136: 136 */    this(m.comparator());
/*  137: 137 */    putAll(m);
/*  138:     */  }
/*  139:     */  
/*  142:     */  public Object2BooleanRBTreeMap(Object2BooleanMap<? extends K> m)
/*  143:     */  {
/*  144: 144 */    this();
/*  145: 145 */    putAll(m);
/*  146:     */  }
/*  147:     */  
/*  150:     */  public Object2BooleanRBTreeMap(Object2BooleanSortedMap<K> m)
/*  151:     */  {
/*  152: 152 */    this(m.comparator());
/*  153: 153 */    putAll(m);
/*  154:     */  }
/*  155:     */  
/*  161:     */  public Object2BooleanRBTreeMap(K[] k, boolean[] v, Comparator<? super K> c)
/*  162:     */  {
/*  163: 163 */    this(c);
/*  164: 164 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  165: 165 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  166:     */    }
/*  167:     */  }
/*  168:     */  
/*  172:     */  public Object2BooleanRBTreeMap(K[] k, boolean[] v)
/*  173:     */  {
/*  174: 174 */    this(k, v, null);
/*  175:     */  }
/*  176:     */  
/*  195:     */  final int compare(K k1, K k2)
/*  196:     */  {
/*  197: 197 */    return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*  198:     */  }
/*  199:     */  
/*  203:     */  final Entry<K> findKey(K k)
/*  204:     */  {
/*  205: 205 */    Entry<K> e = this.tree;
/*  206:     */    int cmp;
/*  207: 207 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  208: 208 */    return e;
/*  209:     */  }
/*  210:     */  
/*  215:     */  final Entry<K> locateKey(K k)
/*  216:     */  {
/*  217: 217 */    Entry<K> e = this.tree;Entry<K> last = this.tree;
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
/*  235:     */  public boolean put(K k, boolean v)
/*  236:     */  {
/*  237: 237 */    this.modified = false;
/*  238: 238 */    int maxDepth = 0;
/*  239: 239 */    if (this.tree == null) {
/*  240: 240 */      this.count += 1;
/*  241: 241 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  242:     */    }
/*  243:     */    else {
/*  244: 244 */      Entry<K> p = this.tree;
/*  245: 245 */      int i = 0;
/*  246:     */      for (;;) { int cmp;
/*  247: 247 */        if ((cmp = compare(k, p.key)) == 0) {
/*  248: 248 */          boolean oldValue = p.value;
/*  249: 249 */          p.value = v;
/*  250:     */          
/*  251: 251 */          while (i-- != 0) this.nodePath[i] = null;
/*  252: 252 */          return oldValue;
/*  253:     */        }
/*  254: 254 */        this.nodePath[i] = p;
/*  255: 255 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  256: 256 */          if (p.succ()) {
/*  257: 257 */            this.count += 1;
/*  258: 258 */            Entry<K> e = new Entry(k, v);
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
/*  270: 270 */            Entry<K> e = new Entry(k, v);
/*  271: 271 */            if (p.left == null) this.firstEntry = e;
/*  272: 272 */            e.right = p;
/*  273: 273 */            e.left = p.left;
/*  274: 274 */            p.left(e);
/*  275: 275 */            break;
/*  276:     */          }
/*  277: 277 */          p = p.left;
/*  278:     */        } }
/*  279:     */      Entry<K> e;
/*  280: 280 */      this.modified = true;
/*  281: 281 */      maxDepth = i--;
/*  282: 282 */      while ((i > 0) && (!this.nodePath[i].black())) {
/*  283: 283 */        if (this.dirPath[(i - 1)] == 0) {
/*  284: 284 */          Entry<K> y = this.nodePath[(i - 1)].right;
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
/*  295: 295 */              Entry<K> x = this.nodePath[i];
/*  296: 296 */              y = x.right;
/*  297: 297 */              x.right = y.left;
/*  298: 298 */              y.left = x;
/*  299: 299 */              this.nodePath[(i - 1)].left = y;
/*  300: 300 */              if (y.pred()) {
/*  301: 301 */                y.pred(false);
/*  302: 302 */                x.succ(y);
/*  303:     */              }
/*  304:     */            }
/*  305: 305 */            Entry<K> x = this.nodePath[(i - 1)];
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
/*  323: 323 */          Entry<K> y = this.nodePath[(i - 1)].left;
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
/*  334: 334 */              Entry<K> x = this.nodePath[i];
/*  335: 335 */              y = x.left;
/*  336: 336 */              x.left = y.right;
/*  337: 337 */              y.right = x;
/*  338: 338 */              this.nodePath[(i - 1)].right = y;
/*  339: 339 */              if (y.succ()) {
/*  340: 340 */                y.succ(false);
/*  341: 341 */                x.pred(y);
/*  342:     */              }
/*  343:     */            }
/*  344: 344 */            Entry<K> x = this.nodePath[(i - 1)];
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
/*  374:     */  public boolean removeBoolean(Object k)
/*  375:     */  {
/*  376: 376 */    this.modified = false;
/*  377: 377 */    if (this.tree == null) return this.defRetValue;
/*  378: 378 */    Entry<K> p = this.tree;
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
/*  422: 422 */      Entry<K> r = p.right;
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
/*  441:     */        Entry<K> s;
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
/*  473: 473 */          Entry<K> x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  474: 474 */          if (!x.black()) {
/*  475: 475 */            x.black(true);
/*  476: 476 */            break;
/*  477:     */          }
/*  478:     */        }
/*  479: 479 */        if (this.dirPath[(i - 1)] == 0) {
/*  480: 480 */          Entry<K> w = this.nodePath[(i - 1)].right;
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
/*  503: 503 */              Entry<K> y = w.left;
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
/*  532: 532 */          Entry<K> w = this.nodePath[(i - 1)].left;
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
/*  555: 555 */              Entry<K> y = w.right;
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
/*  597: 597 */  public Boolean put(K ok, Boolean ov) { boolean oldValue = put(ok, ov.booleanValue());
/*  598: 598 */    return this.modified ? null : Boolean.valueOf(oldValue);
/*  599:     */  }
/*  600:     */  
/*  601: 601 */  public Boolean remove(Object ok) { boolean oldValue = removeBoolean(ok);
/*  602: 602 */    return this.modified ? Boolean.valueOf(oldValue) : null;
/*  603:     */  }
/*  604:     */  
/*  605: 605 */  public boolean containsValue(boolean v) { Object2BooleanRBTreeMap<K>.ValueIterator i = new ValueIterator(null);
/*  606:     */    
/*  607: 607 */    int j = this.count;
/*  608: 608 */    while (j-- != 0) {
/*  609: 609 */      boolean ev = i.nextBoolean();
/*  610: 610 */      if (ev == v) return true;
/*  611:     */    }
/*  612: 612 */    return false;
/*  613:     */  }
/*  614:     */  
/*  615: 615 */  public void clear() { this.count = 0;
/*  616: 616 */    this.tree = null;
/*  617: 617 */    this.entries = null;
/*  618: 618 */    this.values = null;
/*  619: 619 */    this.keys = null;
/*  620: 620 */    this.firstEntry = (this.lastEntry = null);
/*  621:     */  }
/*  622:     */  
/*  625:     */  private static final class Entry<K>
/*  626:     */    implements Cloneable, Object2BooleanMap.Entry<K>
/*  627:     */  {
/*  628:     */    private static final int BLACK_MASK = 1;
/*  629:     */    
/*  631:     */    private static final int SUCC_MASK = -2147483648;
/*  632:     */    
/*  634:     */    private static final int PRED_MASK = 1073741824;
/*  635:     */    
/*  636:     */    K key;
/*  637:     */    
/*  638:     */    boolean value;
/*  639:     */    
/*  640:     */    Entry<K> left;
/*  641:     */    
/*  642:     */    Entry<K> right;
/*  643:     */    
/*  644:     */    int info;
/*  645:     */    
/*  647:     */    Entry() {}
/*  648:     */    
/*  650:     */    Entry(K k, boolean v)
/*  651:     */    {
/*  652: 652 */      this.key = k;
/*  653: 653 */      this.value = v;
/*  654: 654 */      this.info = -1073741824;
/*  655:     */    }
/*  656:     */    
/*  660:     */    Entry<K> left()
/*  661:     */    {
/*  662: 662 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  663:     */    }
/*  664:     */    
/*  668:     */    Entry<K> right()
/*  669:     */    {
/*  670: 670 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  671:     */    }
/*  672:     */    
/*  674:     */    boolean pred()
/*  675:     */    {
/*  676: 676 */      return (this.info & 0x40000000) != 0;
/*  677:     */    }
/*  678:     */    
/*  680:     */    boolean succ()
/*  681:     */    {
/*  682: 682 */      return (this.info & 0x80000000) != 0;
/*  683:     */    }
/*  684:     */    
/*  686:     */    void pred(boolean pred)
/*  687:     */    {
/*  688: 688 */      if (pred) this.info |= 1073741824; else {
/*  689: 689 */        this.info &= -1073741825;
/*  690:     */      }
/*  691:     */    }
/*  692:     */    
/*  693:     */    void succ(boolean succ)
/*  694:     */    {
/*  695: 695 */      if (succ) this.info |= -2147483648; else {
/*  696: 696 */        this.info &= 2147483647;
/*  697:     */      }
/*  698:     */    }
/*  699:     */    
/*  700:     */    void pred(Entry<K> pred)
/*  701:     */    {
/*  702: 702 */      this.info |= 1073741824;
/*  703: 703 */      this.left = pred;
/*  704:     */    }
/*  705:     */    
/*  707:     */    void succ(Entry<K> succ)
/*  708:     */    {
/*  709: 709 */      this.info |= -2147483648;
/*  710: 710 */      this.right = succ;
/*  711:     */    }
/*  712:     */    
/*  714:     */    void left(Entry<K> left)
/*  715:     */    {
/*  716: 716 */      this.info &= -1073741825;
/*  717: 717 */      this.left = left;
/*  718:     */    }
/*  719:     */    
/*  721:     */    void right(Entry<K> right)
/*  722:     */    {
/*  723: 723 */      this.info &= 2147483647;
/*  724: 724 */      this.right = right;
/*  725:     */    }
/*  726:     */    
/*  728:     */    boolean black()
/*  729:     */    {
/*  730: 730 */      return (this.info & 0x1) != 0;
/*  731:     */    }
/*  732:     */    
/*  734:     */    void black(boolean black)
/*  735:     */    {
/*  736: 736 */      if (black) this.info |= 1; else {
/*  737: 737 */        this.info &= -2;
/*  738:     */      }
/*  739:     */    }
/*  740:     */    
/*  742:     */    Entry<K> next()
/*  743:     */    {
/*  744: 744 */      Entry<K> next = this.right;
/*  745: 745 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  746: 746 */      return next;
/*  747:     */    }
/*  748:     */    
/*  751:     */    Entry<K> prev()
/*  752:     */    {
/*  753: 753 */      Entry<K> prev = this.left;
/*  754: 754 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  755: 755 */      return prev;
/*  756:     */    }
/*  757:     */    
/*  758: 758 */    public K getKey() { return this.key; }
/*  759:     */    
/*  760:     */    public Boolean getValue() {
/*  761: 761 */      return Boolean.valueOf(this.value);
/*  762:     */    }
/*  763:     */    
/*  764: 764 */    public boolean getBooleanValue() { return this.value; }
/*  765:     */    
/*  766:     */    public boolean setValue(boolean value) {
/*  767: 767 */      boolean oldValue = this.value;
/*  768: 768 */      this.value = value;
/*  769: 769 */      return oldValue;
/*  770:     */    }
/*  771:     */    
/*  772: 772 */    public Boolean setValue(Boolean value) { return Boolean.valueOf(setValue(value.booleanValue())); }
/*  773:     */    
/*  774:     */    public Entry<K> clone()
/*  775:     */    {
/*  776:     */      Entry<K> c;
/*  777:     */      try {
/*  778: 778 */        c = (Entry)super.clone();
/*  779:     */      }
/*  780:     */      catch (CloneNotSupportedException cantHappen) {
/*  781: 781 */        throw new InternalError();
/*  782:     */      }
/*  783: 783 */      c.key = this.key;
/*  784: 784 */      c.value = this.value;
/*  785: 785 */      c.info = this.info;
/*  786: 786 */      return c;
/*  787:     */    }
/*  788:     */    
/*  789:     */    public boolean equals(Object o) {
/*  790: 790 */      if (!(o instanceof Map.Entry)) return false;
/*  791: 791 */      Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  792: 792 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == ((Boolean)e.getValue()).booleanValue());
/*  793:     */    }
/*  794:     */    
/*  795: 795 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value ? 1231 : 1237); }
/*  796:     */    
/*  797:     */    public String toString() {
/*  798: 798 */      return this.key + "=>" + this.value;
/*  799:     */    }
/*  800:     */  }
/*  801:     */  
/*  830:     */  public boolean containsKey(Object k)
/*  831:     */  {
/*  832: 832 */    return findKey(k) != null;
/*  833:     */  }
/*  834:     */  
/*  835: 835 */  public int size() { return this.count; }
/*  836:     */  
/*  837:     */  public boolean isEmpty() {
/*  838: 838 */    return this.count == 0;
/*  839:     */  }
/*  840:     */  
/*  841:     */  public boolean getBoolean(Object k) {
/*  842: 842 */    Entry<K> e = findKey(k);
/*  843: 843 */    return e == null ? this.defRetValue : e.value;
/*  844:     */  }
/*  845:     */  
/*  846:     */  public Boolean get(Object ok) {
/*  847: 847 */    Entry<K> e = findKey(ok);
/*  848: 848 */    return e == null ? null : e.getValue();
/*  849:     */  }
/*  850:     */  
/*  851: 851 */  public K firstKey() { if (this.tree == null) throw new NoSuchElementException();
/*  852: 852 */    return this.firstEntry.key;
/*  853:     */  }
/*  854:     */  
/*  855: 855 */  public K lastKey() { if (this.tree == null) throw new NoSuchElementException();
/*  856: 856 */    return this.lastEntry.key;
/*  857:     */  }
/*  858:     */  
/*  861:     */  private class TreeIterator
/*  862:     */  {
/*  863:     */    Object2BooleanRBTreeMap.Entry<K> prev;
/*  864:     */    
/*  866:     */    Object2BooleanRBTreeMap.Entry<K> next;
/*  867:     */    
/*  868:     */    Object2BooleanRBTreeMap.Entry<K> curr;
/*  869:     */    
/*  870: 870 */    int index = 0;
/*  871:     */    
/*  872: 872 */    TreeIterator() { this.next = Object2BooleanRBTreeMap.this.firstEntry; }
/*  873:     */    
/*  874:     */    TreeIterator() {
/*  875: 875 */      if ((this.next = Object2BooleanRBTreeMap.this.locateKey(k)) != null)
/*  876: 876 */        if (Object2BooleanRBTreeMap.this.compare(this.next.key, k) <= 0) {
/*  877: 877 */          this.prev = this.next;
/*  878: 878 */          this.next = this.next.next();
/*  879:     */        } else {
/*  880: 880 */          this.prev = this.next.prev();
/*  881:     */        } }
/*  882:     */    
/*  883: 883 */    public boolean hasNext() { return this.next != null; }
/*  884: 884 */    public boolean hasPrevious() { return this.prev != null; }
/*  885:     */    
/*  886: 886 */    void updateNext() { this.next = this.next.next(); }
/*  887:     */    
/*  888:     */    Object2BooleanRBTreeMap.Entry<K> nextEntry() {
/*  889: 889 */      if (!hasNext()) throw new NoSuchElementException();
/*  890: 890 */      this.curr = (this.prev = this.next);
/*  891: 891 */      this.index += 1;
/*  892: 892 */      updateNext();
/*  893: 893 */      return this.curr;
/*  894:     */    }
/*  895:     */    
/*  896: 896 */    void updatePrevious() { this.prev = this.prev.prev(); }
/*  897:     */    
/*  898:     */    Object2BooleanRBTreeMap.Entry<K> previousEntry() {
/*  899: 899 */      if (!hasPrevious()) throw new NoSuchElementException();
/*  900: 900 */      this.curr = (this.next = this.prev);
/*  901: 901 */      this.index -= 1;
/*  902: 902 */      updatePrevious();
/*  903: 903 */      return this.curr;
/*  904:     */    }
/*  905:     */    
/*  906: 906 */    public int nextIndex() { return this.index; }
/*  907:     */    
/*  909: 909 */    public int previousIndex() { return this.index - 1; }
/*  910:     */    
/*  911:     */    public void remove() {
/*  912: 912 */      if (this.curr == null) { throw new IllegalStateException();
/*  913:     */      }
/*  914:     */      
/*  915: 915 */      if (this.curr == this.prev) this.index -= 1;
/*  916: 916 */      this.next = (this.prev = this.curr);
/*  917: 917 */      updatePrevious();
/*  918: 918 */      updateNext();
/*  919: 919 */      Object2BooleanRBTreeMap.this.removeBoolean(this.curr.key);
/*  920: 920 */      this.curr = null;
/*  921:     */    }
/*  922:     */    
/*  923: 923 */    public int skip(int n) { int i = n;
/*  924: 924 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  925: 925 */      return n - i - 1;
/*  926:     */    }
/*  927:     */    
/*  928: 928 */    public int back(int n) { int i = n;
/*  929: 929 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  930: 930 */      return n - i - 1;
/*  931:     */    }
/*  932:     */  }
/*  933:     */  
/*  934:     */  private class EntryIterator
/*  935:     */    extends Object2BooleanRBTreeMap<K>.TreeIterator
/*  936:     */    implements ObjectListIterator<Object2BooleanMap.Entry<K>>
/*  937:     */  {
/*  938: 938 */    EntryIterator() { super(); }
/*  939:     */    
/*  940: 940 */    EntryIterator() { super(k); }
/*  941:     */    
/*  942: 942 */    public Object2BooleanMap.Entry<K> next() { return nextEntry(); }
/*  943: 943 */    public Object2BooleanMap.Entry<K> previous() { return previousEntry(); }
/*  944: 944 */    public void set(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  945: 945 */    public void add(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*  946:     */  }
/*  947:     */  
/*  948: 948 */  public ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  949: 949 */        final Comparator<? super Object2BooleanMap.Entry<K>> comparator = new Comparator() {
/*  950:     */          public int compare(Object2BooleanMap.Entry<K> x, Object2BooleanMap.Entry<K> y) {
/*  951: 951 */            return Object2BooleanRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*  952:     */          }
/*  953:     */        };
/*  954:     */        
/*  955: 955 */        public Comparator<? super Object2BooleanMap.Entry<K>> comparator() { return this.comparator; }
/*  956:     */        
/*  957:     */        public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> iterator() {
/*  958: 958 */          return new Object2BooleanRBTreeMap.EntryIterator(Object2BooleanRBTreeMap.this);
/*  959:     */        }
/*  960:     */        
/*  961: 961 */        public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> iterator(Object2BooleanMap.Entry<K> from) { return new Object2BooleanRBTreeMap.EntryIterator(Object2BooleanRBTreeMap.this, from.getKey()); }
/*  962:     */        
/*  963:     */        public boolean contains(Object o)
/*  964:     */        {
/*  965: 965 */          if (!(o instanceof Map.Entry)) return false;
/*  966: 966 */          Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  967: 967 */          Object2BooleanRBTreeMap.Entry<K> f = Object2BooleanRBTreeMap.this.findKey(e.getKey());
/*  968: 968 */          return e.equals(f);
/*  969:     */        }
/*  970:     */        
/*  971:     */        public boolean remove(Object o) {
/*  972: 972 */          if (!(o instanceof Map.Entry)) return false;
/*  973: 973 */          Map.Entry<K, Boolean> e = (Map.Entry)o;
/*  974: 974 */          Object2BooleanRBTreeMap.Entry<K> f = Object2BooleanRBTreeMap.this.findKey(e.getKey());
/*  975: 975 */          if (f != null) Object2BooleanRBTreeMap.this.removeBoolean(f.key);
/*  976: 976 */          return f != null; }
/*  977:     */        
/*  978: 978 */        public int size() { return Object2BooleanRBTreeMap.this.count; }
/*  979: 979 */        public void clear() { Object2BooleanRBTreeMap.this.clear(); }
/*  980: 980 */        public Object2BooleanMap.Entry<K> first() { return Object2BooleanRBTreeMap.this.firstEntry; }
/*  981: 981 */        public Object2BooleanMap.Entry<K> last() { return Object2BooleanRBTreeMap.this.lastEntry; }
/*  982: 982 */        public ObjectSortedSet<Object2BooleanMap.Entry<K>> subSet(Object2BooleanMap.Entry<K> from, Object2BooleanMap.Entry<K> to) { return Object2BooleanRBTreeMap.this.subMap(from.getKey(), to.getKey()).object2BooleanEntrySet(); }
/*  983: 983 */        public ObjectSortedSet<Object2BooleanMap.Entry<K>> headSet(Object2BooleanMap.Entry<K> to) { return Object2BooleanRBTreeMap.this.headMap(to.getKey()).object2BooleanEntrySet(); }
/*  984: 984 */        public ObjectSortedSet<Object2BooleanMap.Entry<K>> tailSet(Object2BooleanMap.Entry<K> from) { return Object2BooleanRBTreeMap.this.tailMap(from.getKey()).object2BooleanEntrySet(); }
/*  985:     */      };
/*  986: 986 */    return this.entries;
/*  987:     */  }
/*  988:     */  
/*  991:     */  private final class KeyIterator
/*  992:     */    extends Object2BooleanRBTreeMap<K>.TreeIterator
/*  993:     */    implements ObjectListIterator<K>
/*  994:     */  {
/*  995: 995 */    public KeyIterator() { super(); }
/*  996: 996 */    public KeyIterator() { super(k); }
/*  997: 997 */    public K next() { return nextEntry().key; }
/*  998: 998 */    public K previous() { return previousEntry().key; }
/*  999: 999 */    public void set(K k) { throw new UnsupportedOperationException(); }
/* 1000:1000 */    public void add(K k) { throw new UnsupportedOperationException(); }
/* 1001:     */  }
/* 1002:     */  
/* 1003:1003 */  private class KeySet extends AbstractObject2BooleanSortedMap.KeySet { private KeySet() { super(); }
/* 1004:1004 */    public ObjectBidirectionalIterator<K> iterator() { return new Object2BooleanRBTreeMap.KeyIterator(Object2BooleanRBTreeMap.this); }
/* 1005:1005 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2BooleanRBTreeMap.KeyIterator(Object2BooleanRBTreeMap.this, from); }
/* 1006:     */  }
/* 1007:     */  
/* 1014:     */  public ObjectSortedSet<K> keySet()
/* 1015:     */  {
/* 1016:1016 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1017:1017 */    return this.keys;
/* 1018:     */  }
/* 1019:     */  
/* 1021:     */  private final class ValueIterator
/* 1022:     */    extends Object2BooleanRBTreeMap.TreeIterator
/* 1023:     */    implements BooleanListIterator
/* 1024:     */  {
/* 1025:1025 */    private ValueIterator() { super(); }
/* 1026:1026 */    public boolean nextBoolean() { return nextEntry().value; }
/* 1027:1027 */    public boolean previousBoolean() { return previousEntry().value; }
/* 1028:1028 */    public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1029:1029 */    public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1030:1030 */    public Boolean next() { return Boolean.valueOf(nextEntry().value); }
/* 1031:1031 */    public Boolean previous() { return Boolean.valueOf(previousEntry().value); }
/* 1032:1032 */    public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1033:1033 */    public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1034:     */  }
/* 1035:     */  
/* 1042:     */  public BooleanCollection values()
/* 1043:     */  {
/* 1044:1044 */    if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1045:     */        public BooleanIterator iterator() {
/* 1046:1046 */          return new Object2BooleanRBTreeMap.ValueIterator(Object2BooleanRBTreeMap.this, null);
/* 1047:     */        }
/* 1048:     */        
/* 1049:1049 */        public boolean contains(boolean k) { return Object2BooleanRBTreeMap.this.containsValue(k); }
/* 1050:     */        
/* 1051:     */        public int size() {
/* 1052:1052 */          return Object2BooleanRBTreeMap.this.count;
/* 1053:     */        }
/* 1054:     */        
/* 1055:1055 */        public void clear() { Object2BooleanRBTreeMap.this.clear(); }
/* 1056:     */      };
/* 1057:     */    }
/* 1058:1058 */    return this.values;
/* 1059:     */  }
/* 1060:     */  
/* 1061:1061 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1062:     */  
/* 1063:     */  public Object2BooleanSortedMap<K> headMap(K to) {
/* 1064:1064 */    return new Submap(null, true, to, false);
/* 1065:     */  }
/* 1066:     */  
/* 1067:1067 */  public Object2BooleanSortedMap<K> tailMap(K from) { return new Submap(from, false, null, true); }
/* 1068:     */  
/* 1069:     */  public Object2BooleanSortedMap<K> subMap(K from, K to) {
/* 1070:1070 */    return new Submap(from, false, to, false);
/* 1071:     */  }
/* 1072:     */  
/* 1076:     */  private final class Submap
/* 1077:     */    extends AbstractObject2BooleanSortedMap<K>
/* 1078:     */    implements Serializable
/* 1079:     */  {
/* 1080:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1081:     */    
/* 1084:     */    K from;
/* 1085:     */    
/* 1087:     */    K to;
/* 1088:     */    
/* 1090:     */    boolean bottom;
/* 1091:     */    
/* 1093:     */    boolean top;
/* 1094:     */    
/* 1096:     */    protected volatile transient ObjectSortedSet<Object2BooleanMap.Entry<K>> entries;
/* 1097:     */    
/* 1099:     */    protected volatile transient ObjectSortedSet<K> keys;
/* 1100:     */    
/* 1102:     */    protected volatile transient BooleanCollection values;
/* 1103:     */    
/* 1106:     */    public Submap(boolean from, K bottom, boolean to)
/* 1107:     */    {
/* 1108:1108 */      if ((!bottom) && (!top) && (Object2BooleanRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1109:1109 */      this.from = from;
/* 1110:1110 */      this.bottom = bottom;
/* 1111:1111 */      this.to = to;
/* 1112:1112 */      this.top = top;
/* 1113:1113 */      this.defRetValue = Object2BooleanRBTreeMap.this.defRetValue;
/* 1114:     */    }
/* 1115:     */    
/* 1116:1116 */    public void clear() { Object2BooleanRBTreeMap<K>.Submap.SubmapIterator i = new SubmapIterator();
/* 1117:1117 */      while (i.hasNext()) {
/* 1118:1118 */        i.nextEntry();
/* 1119:1119 */        i.remove();
/* 1120:     */      }
/* 1121:     */    }
/* 1122:     */    
/* 1125:     */    final boolean in(K k)
/* 1126:     */    {
/* 1127:1127 */      return ((this.bottom) || (Object2BooleanRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2BooleanRBTreeMap.this.compare(k, this.to) < 0));
/* 1128:     */    }
/* 1129:     */    
/* 1130:     */    public ObjectSortedSet<Object2BooleanMap.Entry<K>> object2BooleanEntrySet() {
/* 1131:1131 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1132:     */          public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> iterator() {
/* 1133:1133 */            return new Object2BooleanRBTreeMap.Submap.SubmapEntryIterator(Object2BooleanRBTreeMap.Submap.this);
/* 1134:     */          }
/* 1135:     */          
/* 1136:1136 */          public ObjectBidirectionalIterator<Object2BooleanMap.Entry<K>> iterator(Object2BooleanMap.Entry<K> from) { return new Object2BooleanRBTreeMap.Submap.SubmapEntryIterator(Object2BooleanRBTreeMap.Submap.this, from.getKey()); }
/* 1137:     */          
/* 1138:1138 */          public Comparator<? super Object2BooleanMap.Entry<K>> comparator() { return Object2BooleanRBTreeMap.this.object2BooleanEntrySet().comparator(); }
/* 1139:     */          
/* 1140:     */          public boolean contains(Object o) {
/* 1141:1141 */            if (!(o instanceof Map.Entry)) return false;
/* 1142:1142 */            Map.Entry<K, Boolean> e = (Map.Entry)o;
/* 1143:1143 */            Object2BooleanRBTreeMap.Entry<K> f = Object2BooleanRBTreeMap.this.findKey(e.getKey());
/* 1144:1144 */            return (f != null) && (Object2BooleanRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1145:     */          }
/* 1146:     */          
/* 1147:     */          public boolean remove(Object o) {
/* 1148:1148 */            if (!(o instanceof Map.Entry)) return false;
/* 1149:1149 */            Map.Entry<K, Boolean> e = (Map.Entry)o;
/* 1150:1150 */            Object2BooleanRBTreeMap.Entry<K> f = Object2BooleanRBTreeMap.this.findKey(e.getKey());
/* 1151:1151 */            if ((f != null) && (Object2BooleanRBTreeMap.Submap.this.in(f.key))) Object2BooleanRBTreeMap.Submap.this.removeBoolean(f.key);
/* 1152:1152 */            return f != null;
/* 1153:     */          }
/* 1154:     */          
/* 1155:1155 */          public int size() { int c = 0;
/* 1156:1156 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1157:1157 */            return c; }
/* 1158:     */          
/* 1159:1159 */          public boolean isEmpty() { return !new Object2BooleanRBTreeMap.Submap.SubmapIterator(Object2BooleanRBTreeMap.Submap.this).hasNext(); }
/* 1160:1160 */          public void clear() { Object2BooleanRBTreeMap.Submap.this.clear(); }
/* 1161:1161 */          public Object2BooleanMap.Entry<K> first() { return Object2BooleanRBTreeMap.Submap.this.firstEntry(); }
/* 1162:1162 */          public Object2BooleanMap.Entry<K> last() { return Object2BooleanRBTreeMap.Submap.this.lastEntry(); }
/* 1163:1163 */          public ObjectSortedSet<Object2BooleanMap.Entry<K>> subSet(Object2BooleanMap.Entry<K> from, Object2BooleanMap.Entry<K> to) { return Object2BooleanRBTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2BooleanEntrySet(); }
/* 1164:1164 */          public ObjectSortedSet<Object2BooleanMap.Entry<K>> headSet(Object2BooleanMap.Entry<K> to) { return Object2BooleanRBTreeMap.Submap.this.headMap(to.getKey()).object2BooleanEntrySet(); }
/* 1165:1165 */          public ObjectSortedSet<Object2BooleanMap.Entry<K>> tailSet(Object2BooleanMap.Entry<K> from) { return Object2BooleanRBTreeMap.Submap.this.tailMap(from.getKey()).object2BooleanEntrySet(); }
/* 1166:     */        };
/* 1167:1167 */      return this.entries; }
/* 1168:     */    
/* 1169:1169 */    private class KeySet extends AbstractObject2BooleanSortedMap.KeySet { private KeySet() { super(); }
/* 1170:1170 */      public ObjectBidirectionalIterator<K> iterator() { return new Object2BooleanRBTreeMap.Submap.SubmapKeyIterator(Object2BooleanRBTreeMap.Submap.this); }
/* 1171:1171 */      public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2BooleanRBTreeMap.Submap.SubmapKeyIterator(Object2BooleanRBTreeMap.Submap.this, from); }
/* 1172:     */    }
/* 1173:     */    
/* 1174:1174 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1175:1175 */      return this.keys;
/* 1176:     */    }
/* 1177:     */    
/* 1178:1178 */    public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1179:     */          public BooleanIterator iterator() {
/* 1180:1180 */            return new Object2BooleanRBTreeMap.Submap.SubmapValueIterator(Object2BooleanRBTreeMap.Submap.this, null);
/* 1181:     */          }
/* 1182:     */          
/* 1183:1183 */          public boolean contains(boolean k) { return Object2BooleanRBTreeMap.Submap.this.containsValue(k); }
/* 1184:     */          
/* 1185:     */          public int size() {
/* 1186:1186 */            return Object2BooleanRBTreeMap.Submap.this.size();
/* 1187:     */          }
/* 1188:     */          
/* 1189:1189 */          public void clear() { Object2BooleanRBTreeMap.Submap.this.clear(); }
/* 1190:     */        };
/* 1191:     */      }
/* 1192:1192 */      return this.values;
/* 1193:     */    }
/* 1194:     */    
/* 1196:1196 */    public boolean containsKey(Object k) { return (in(k)) && (Object2BooleanRBTreeMap.this.containsKey(k)); }
/* 1197:     */    
/* 1198:     */    public boolean containsValue(boolean v) {
/* 1199:1199 */      Object2BooleanRBTreeMap<K>.Submap.SubmapIterator i = new SubmapIterator();
/* 1200:     */      
/* 1201:1201 */      while (i.hasNext()) {
/* 1202:1202 */        boolean ev = i.nextEntry().value;
/* 1203:1203 */        if (ev == v) return true;
/* 1204:     */      }
/* 1205:1205 */      return false;
/* 1206:     */    }
/* 1207:     */    
/* 1208:     */    public boolean getBoolean(Object k)
/* 1209:     */    {
/* 1210:1210 */      K kk = k;
/* 1211:1211 */      Object2BooleanRBTreeMap.Entry<K> e; return (in(kk)) && ((e = Object2BooleanRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1212:     */    }
/* 1213:     */    
/* 1214:     */    public Boolean get(Object ok)
/* 1215:     */    {
/* 1216:1216 */      K kk = ok;
/* 1217:1217 */      Object2BooleanRBTreeMap.Entry<K> e; return (in(kk)) && ((e = Object2BooleanRBTreeMap.this.findKey(kk)) != null) ? e.getValue() : null;
/* 1218:     */    }
/* 1219:     */    
/* 1220:1220 */    public boolean put(K k, boolean v) { Object2BooleanRBTreeMap.this.modified = false;
/* 1221:1221 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1222:1222 */      boolean oldValue = Object2BooleanRBTreeMap.this.put(k, v);
/* 1223:1223 */      return Object2BooleanRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1224:     */    }
/* 1225:     */    
/* 1226:1226 */    public Boolean put(K ok, Boolean ov) { boolean oldValue = put(ok, ov.booleanValue());
/* 1227:1227 */      return Object2BooleanRBTreeMap.this.modified ? null : Boolean.valueOf(oldValue);
/* 1228:     */    }
/* 1229:     */    
/* 1230:     */    public boolean removeBoolean(Object k) {
/* 1231:1231 */      Object2BooleanRBTreeMap.this.modified = false;
/* 1232:1232 */      if (!in(k)) return this.defRetValue;
/* 1233:1233 */      boolean oldValue = Object2BooleanRBTreeMap.this.removeBoolean(k);
/* 1234:1234 */      return Object2BooleanRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1235:     */    }
/* 1236:     */    
/* 1237:1237 */    public Boolean remove(Object ok) { boolean oldValue = removeBoolean(ok);
/* 1238:1238 */      return Object2BooleanRBTreeMap.this.modified ? Boolean.valueOf(oldValue) : null;
/* 1239:     */    }
/* 1240:     */    
/* 1241:1241 */    public int size() { Object2BooleanRBTreeMap<K>.Submap.SubmapIterator i = new SubmapIterator();
/* 1242:1242 */      int n = 0;
/* 1243:1243 */      while (i.hasNext()) {
/* 1244:1244 */        n++;
/* 1245:1245 */        i.nextEntry();
/* 1246:     */      }
/* 1247:1247 */      return n;
/* 1248:     */    }
/* 1249:     */    
/* 1250:1250 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1251:     */    
/* 1253:1253 */    public Comparator<? super K> comparator() { return Object2BooleanRBTreeMap.this.actualComparator; }
/* 1254:     */    
/* 1255:     */    public Object2BooleanSortedMap<K> headMap(K to) {
/* 1256:1256 */      if (this.top) return new Submap(Object2BooleanRBTreeMap.this, this.from, this.bottom, to, false);
/* 1257:1257 */      return Object2BooleanRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2BooleanRBTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1258:     */    }
/* 1259:     */    
/* 1260:1260 */    public Object2BooleanSortedMap<K> tailMap(K from) { if (this.bottom) return new Submap(Object2BooleanRBTreeMap.this, from, false, this.to, this.top);
/* 1261:1261 */      return Object2BooleanRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2BooleanRBTreeMap.this, from, false, this.to, this.top) : this;
/* 1262:     */    }
/* 1263:     */    
/* 1264:1264 */    public Object2BooleanSortedMap<K> subMap(K from, K to) { if ((this.top) && (this.bottom)) return new Submap(Object2BooleanRBTreeMap.this, from, false, to, false);
/* 1265:1265 */      if (!this.top) to = Object2BooleanRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1266:1266 */      if (!this.bottom) from = Object2BooleanRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1267:1267 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1268:1268 */      return new Submap(Object2BooleanRBTreeMap.this, from, false, to, false);
/* 1269:     */    }
/* 1270:     */    
/* 1273:     */    public Object2BooleanRBTreeMap.Entry<K> firstEntry()
/* 1274:     */    {
/* 1275:1275 */      if (Object2BooleanRBTreeMap.this.tree == null) return null;
/* 1276:     */      Object2BooleanRBTreeMap.Entry<K> e;
/* 1277:     */      Object2BooleanRBTreeMap.Entry<K> e;
/* 1278:1278 */      if (this.bottom) { e = Object2BooleanRBTreeMap.this.firstEntry;
/* 1279:     */      } else {
/* 1280:1280 */        e = Object2BooleanRBTreeMap.this.locateKey(this.from);
/* 1281:     */        
/* 1282:1282 */        if (Object2BooleanRBTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1283:     */        }
/* 1284:     */      }
/* 1285:1285 */      if ((e == null) || ((!this.top) && (Object2BooleanRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1286:1286 */      return e;
/* 1287:     */    }
/* 1288:     */    
/* 1291:     */    public Object2BooleanRBTreeMap.Entry<K> lastEntry()
/* 1292:     */    {
/* 1293:1293 */      if (Object2BooleanRBTreeMap.this.tree == null) return null;
/* 1294:     */      Object2BooleanRBTreeMap.Entry<K> e;
/* 1295:     */      Object2BooleanRBTreeMap.Entry<K> e;
/* 1296:1296 */      if (this.top) { e = Object2BooleanRBTreeMap.this.lastEntry;
/* 1297:     */      } else {
/* 1298:1298 */        e = Object2BooleanRBTreeMap.this.locateKey(this.to);
/* 1299:     */        
/* 1300:1300 */        if (Object2BooleanRBTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1301:     */        }
/* 1302:     */      }
/* 1303:1303 */      if ((e == null) || ((!this.bottom) && (Object2BooleanRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1304:1304 */      return e;
/* 1305:     */    }
/* 1306:     */    
/* 1307:1307 */    public K firstKey() { Object2BooleanRBTreeMap.Entry<K> e = firstEntry();
/* 1308:1308 */      if (e == null) throw new NoSuchElementException();
/* 1309:1309 */      return e.key;
/* 1310:     */    }
/* 1311:     */    
/* 1312:1312 */    public K lastKey() { Object2BooleanRBTreeMap.Entry<K> e = lastEntry();
/* 1313:1313 */      if (e == null) throw new NoSuchElementException();
/* 1314:1314 */      return e.key;
/* 1315:     */    }
/* 1316:     */    
/* 1319:     */    private class SubmapIterator
/* 1320:     */      extends Object2BooleanRBTreeMap.TreeIterator
/* 1321:     */    {
/* 1322:     */      SubmapIterator()
/* 1323:     */      {
/* 1324:1324 */        super();
/* 1325:1325 */        this.next = Object2BooleanRBTreeMap.Submap.this.firstEntry();
/* 1326:     */      }
/* 1327:     */      
/* 1328:1328 */      SubmapIterator() { this();
/* 1329:1329 */        if (this.next != null)
/* 1330:1330 */          if ((!Object2BooleanRBTreeMap.Submap.this.bottom) && (Object2BooleanRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1331:1331 */          } else if ((!Object2BooleanRBTreeMap.Submap.this.top) && (Object2BooleanRBTreeMap.this.compare(k, (this.prev = Object2BooleanRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1332:     */          } else {
/* 1333:1333 */            this.next = Object2BooleanRBTreeMap.this.locateKey(k);
/* 1334:1334 */            if (Object2BooleanRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1335:1335 */              this.prev = this.next;
/* 1336:1336 */              this.next = this.next.next();
/* 1337:     */            } else {
/* 1338:1338 */              this.prev = this.next.prev();
/* 1339:     */            }
/* 1340:     */          }
/* 1341:     */      }
/* 1342:     */      
/* 1343:1343 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1344:1344 */        if ((!Object2BooleanRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2BooleanRBTreeMap.this.compare(this.prev.key, Object2BooleanRBTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1345:     */      }
/* 1346:     */      
/* 1347:1347 */      void updateNext() { this.next = this.next.next();
/* 1348:1348 */        if ((!Object2BooleanRBTreeMap.Submap.this.top) && (this.next != null) && (Object2BooleanRBTreeMap.this.compare(this.next.key, Object2BooleanRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1349:     */      }
/* 1350:     */    }
/* 1351:     */    
/* 1352:1352 */    private class SubmapEntryIterator extends Object2BooleanRBTreeMap<K>.Submap.SubmapIterator implements ObjectListIterator<Object2BooleanMap.Entry<K>> { SubmapEntryIterator() { super(); }
/* 1353:     */      
/* 1354:1354 */      SubmapEntryIterator() { super(k); }
/* 1355:     */      
/* 1356:1356 */      public Object2BooleanMap.Entry<K> next() { return nextEntry(); }
/* 1357:1357 */      public Object2BooleanMap.Entry<K> previous() { return previousEntry(); }
/* 1358:1358 */      public void set(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/* 1359:1359 */      public void add(Object2BooleanMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/* 1360:     */    }
/* 1361:     */    
/* 1366:     */    private final class SubmapKeyIterator
/* 1367:     */      extends Object2BooleanRBTreeMap<K>.Submap.SubmapIterator
/* 1368:     */      implements ObjectListIterator<K>
/* 1369:     */    {
/* 1370:1370 */      public SubmapKeyIterator() { super(); }
/* 1371:1371 */      public SubmapKeyIterator() { super(from); }
/* 1372:1372 */      public K next() { return nextEntry().key; }
/* 1373:1373 */      public K previous() { return previousEntry().key; }
/* 1374:1374 */      public void set(K k) { throw new UnsupportedOperationException(); }
/* 1375:1375 */      public void add(K k) { throw new UnsupportedOperationException(); }
/* 1376:     */    }
/* 1377:     */    
/* 1381:     */    private final class SubmapValueIterator
/* 1382:     */      extends Object2BooleanRBTreeMap.Submap.SubmapIterator
/* 1383:     */      implements BooleanListIterator
/* 1384:     */    {
/* 1385:1385 */      private SubmapValueIterator() { super(); }
/* 1386:1386 */      public boolean nextBoolean() { return nextEntry().value; }
/* 1387:1387 */      public boolean previousBoolean() { return previousEntry().value; }
/* 1388:1388 */      public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1389:1389 */      public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1390:1390 */      public Boolean next() { return Boolean.valueOf(nextEntry().value); }
/* 1391:1391 */      public Boolean previous() { return Boolean.valueOf(previousEntry().value); }
/* 1392:1392 */      public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1393:1393 */      public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1394:     */    }
/* 1395:     */  }
/* 1396:     */  
/* 1400:     */  public Object2BooleanRBTreeMap<K> clone()
/* 1401:     */  {
/* 1402:     */    Object2BooleanRBTreeMap<K> c;
/* 1403:     */    
/* 1405:     */    try
/* 1406:     */    {
/* 1407:1407 */      c = (Object2BooleanRBTreeMap)super.clone();
/* 1408:     */    }
/* 1409:     */    catch (CloneNotSupportedException cantHappen) {
/* 1410:1410 */      throw new InternalError();
/* 1411:     */    }
/* 1412:1412 */    c.keys = null;
/* 1413:1413 */    c.values = null;
/* 1414:1414 */    c.entries = null;
/* 1415:1415 */    c.allocatePaths();
/* 1416:1416 */    if (this.count != 0)
/* 1417:     */    {
/* 1418:1418 */      Entry<K> rp = new Entry();Entry<K> rq = new Entry();
/* 1419:1419 */      Entry<K> p = rp;
/* 1420:1420 */      rp.left(this.tree);
/* 1421:1421 */      Entry<K> q = rq;
/* 1422:1422 */      rq.pred(null);
/* 1423:     */      for (;;) {
/* 1424:1424 */        if (!p.pred()) {
/* 1425:1425 */          Entry<K> e = p.left.clone();
/* 1426:1426 */          e.pred(q.left);
/* 1427:1427 */          e.succ(q);
/* 1428:1428 */          q.left(e);
/* 1429:1429 */          p = p.left;
/* 1430:1430 */          q = q.left;
/* 1431:     */        }
/* 1432:     */        else {
/* 1433:1433 */          while (p.succ()) {
/* 1434:1434 */            p = p.right;
/* 1435:1435 */            if (p == null) {
/* 1436:1436 */              q.right = null;
/* 1437:1437 */              c.tree = rq.left;
/* 1438:1438 */              c.firstEntry = c.tree;
/* 1439:1439 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1440:1440 */              c.lastEntry = c.tree;
/* 1441:1441 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1442:1442 */              return c;
/* 1443:     */            }
/* 1444:1444 */            q = q.right;
/* 1445:     */          }
/* 1446:1446 */          p = p.right;
/* 1447:1447 */          q = q.right;
/* 1448:     */        }
/* 1449:1449 */        if (!p.succ()) {
/* 1450:1450 */          Entry<K> e = p.right.clone();
/* 1451:1451 */          e.succ(q.right);
/* 1452:1452 */          e.pred(q);
/* 1453:1453 */          q.right(e);
/* 1454:     */        }
/* 1455:     */      }
/* 1456:     */    }
/* 1457:1457 */    return c;
/* 1458:     */  }
/* 1459:     */  
/* 1460:1460 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1461:1461 */    Object2BooleanRBTreeMap<K>.EntryIterator i = new EntryIterator();
/* 1462:     */    
/* 1463:1463 */    s.defaultWriteObject();
/* 1464:1464 */    while (n-- != 0) {
/* 1465:1465 */      Entry<K> e = i.nextEntry();
/* 1466:1466 */      s.writeObject(e.key);
/* 1467:1467 */      s.writeBoolean(e.value);
/* 1468:     */    }
/* 1469:     */  }
/* 1470:     */  
/* 1476:     */  private Entry<K> readTree(ObjectInputStream s, int n, Entry<K> pred, Entry<K> succ)
/* 1477:     */    throws IOException, ClassNotFoundException
/* 1478:     */  {
/* 1479:1479 */    if (n == 1) {
/* 1480:1480 */      Entry<K> top = new Entry(s.readObject(), s.readBoolean());
/* 1481:1481 */      top.pred(pred);
/* 1482:1482 */      top.succ(succ);
/* 1483:1483 */      top.black(true);
/* 1484:1484 */      return top;
/* 1485:     */    }
/* 1486:1486 */    if (n == 2)
/* 1487:     */    {
/* 1489:1489 */      Entry<K> top = new Entry(s.readObject(), s.readBoolean());
/* 1490:1490 */      top.black(true);
/* 1491:1491 */      top.right(new Entry(s.readObject(), s.readBoolean()));
/* 1492:1492 */      top.right.pred(top);
/* 1493:1493 */      top.pred(pred);
/* 1494:1494 */      top.right.succ(succ);
/* 1495:1495 */      return top;
/* 1496:     */    }
/* 1497:     */    
/* 1498:1498 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1499:1499 */    Entry<K> top = new Entry();
/* 1500:1500 */    top.left(readTree(s, leftN, pred, top));
/* 1501:1501 */    top.key = s.readObject();
/* 1502:1502 */    top.value = s.readBoolean();
/* 1503:1503 */    top.black(true);
/* 1504:1504 */    top.right(readTree(s, rightN, top, succ));
/* 1505:1505 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1506:1506 */    return top;
/* 1507:     */  }
/* 1508:     */  
/* 1509:1509 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1510:     */    
/* 1512:1512 */    setActualComparator();
/* 1513:1513 */    allocatePaths();
/* 1514:1514 */    if (this.count != 0) {
/* 1515:1515 */      this.tree = readTree(s, this.count, null, null);
/* 1516:     */      
/* 1517:1517 */      Entry<K> e = this.tree;
/* 1518:1518 */      while (e.left() != null) e = e.left();
/* 1519:1519 */      this.firstEntry = e;
/* 1520:1520 */      e = this.tree;
/* 1521:1521 */      while (e.right() != null) e = e.right();
/* 1522:1522 */      this.lastEntry = e;
/* 1523:     */    }
/* 1524:     */  }
/* 1525:     */  
/* 1526:     */  private void checkNodePath() {}
/* 1527:     */  
/* 1528:1528 */  private int checkTree(Entry<K> e, int d, int D) { return 0; }
/* 1529:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2BooleanRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */