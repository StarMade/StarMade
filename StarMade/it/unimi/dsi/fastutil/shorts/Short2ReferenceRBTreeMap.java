/*    1:     */package it.unimi.dsi.fastutil.shorts;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*    4:     */import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*    5:     */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*    6:     */import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*    7:     */import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*    8:     */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*    9:     */import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*   10:     */import java.io.IOException;
/*   11:     */import java.io.ObjectInputStream;
/*   12:     */import java.io.ObjectOutputStream;
/*   13:     */import java.io.Serializable;
/*   14:     */import java.util.Comparator;
/*   15:     */import java.util.Iterator;
/*   16:     */import java.util.Map;
/*   17:     */import java.util.Map.Entry;
/*   18:     */import java.util.NoSuchElementException;
/*   19:     */import java.util.SortedMap;
/*   20:     */
/*   71:     */public class Short2ReferenceRBTreeMap<V>
/*   72:     */  extends AbstractShort2ReferenceSortedMap<V>
/*   73:     */  implements Serializable, Cloneable
/*   74:     */{
/*   75:     */  protected transient Entry<V> tree;
/*   76:     */  protected int count;
/*   77:     */  protected transient Entry<V> firstEntry;
/*   78:     */  protected transient Entry<V> lastEntry;
/*   79:     */  protected volatile transient ObjectSortedSet<Short2ReferenceMap.Entry<V>> entries;
/*   80:     */  protected volatile transient ShortSortedSet keys;
/*   81:     */  protected volatile transient ReferenceCollection<V> values;
/*   82:     */  protected transient boolean modified;
/*   83:     */  protected Comparator<? super Short> storedComparator;
/*   84:     */  protected transient ShortComparator actualComparator;
/*   85:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   86:     */  private static final boolean ASSERTS = false;
/*   87:     */  private transient boolean[] dirPath;
/*   88:     */  private transient Entry<V>[] nodePath;
/*   89:     */  
/*   90:     */  public Short2ReferenceRBTreeMap()
/*   91:     */  {
/*   92:  92 */    allocatePaths();
/*   93:     */    
/*   97:  97 */    this.tree = null;
/*   98:  98 */    this.count = 0;
/*   99:     */  }
/*  100:     */  
/*  114:     */  private void setActualComparator()
/*  115:     */  {
/*  116: 116 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof ShortComparator))) this.actualComparator = ((ShortComparator)this.storedComparator); else {
/*  117: 117 */      this.actualComparator = new ShortComparator() {
/*  118:     */        public int compare(short k1, short k2) {
/*  119: 119 */          return Short2ReferenceRBTreeMap.this.storedComparator.compare(Short.valueOf(k1), Short.valueOf(k2));
/*  120:     */        }
/*  121:     */        
/*  122: 122 */        public int compare(Short ok1, Short ok2) { return Short2ReferenceRBTreeMap.this.storedComparator.compare(ok1, ok2); }
/*  123:     */      };
/*  124:     */    }
/*  125:     */  }
/*  126:     */  
/*  133:     */  public Short2ReferenceRBTreeMap(Comparator<? super Short> c)
/*  134:     */  {
/*  135: 135 */    this();
/*  136: 136 */    this.storedComparator = c;
/*  137: 137 */    setActualComparator();
/*  138:     */  }
/*  139:     */  
/*  145:     */  public Short2ReferenceRBTreeMap(Map<? extends Short, ? extends V> m)
/*  146:     */  {
/*  147: 147 */    this();
/*  148: 148 */    putAll(m);
/*  149:     */  }
/*  150:     */  
/*  155:     */  public Short2ReferenceRBTreeMap(SortedMap<Short, V> m)
/*  156:     */  {
/*  157: 157 */    this(m.comparator());
/*  158: 158 */    putAll(m);
/*  159:     */  }
/*  160:     */  
/*  165:     */  public Short2ReferenceRBTreeMap(Short2ReferenceMap<? extends V> m)
/*  166:     */  {
/*  167: 167 */    this();
/*  168: 168 */    putAll(m);
/*  169:     */  }
/*  170:     */  
/*  175:     */  public Short2ReferenceRBTreeMap(Short2ReferenceSortedMap<V> m)
/*  176:     */  {
/*  177: 177 */    this(m.comparator());
/*  178: 178 */    putAll(m);
/*  179:     */  }
/*  180:     */  
/*  188:     */  public Short2ReferenceRBTreeMap(short[] k, V[] v, Comparator<? super Short> c)
/*  189:     */  {
/*  190: 190 */    this(c);
/*  191: 191 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  192: 192 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  193:     */    }
/*  194:     */  }
/*  195:     */  
/*  201:     */  public Short2ReferenceRBTreeMap(short[] k, V[] v)
/*  202:     */  {
/*  203: 203 */    this(k, v, null);
/*  204:     */  }
/*  205:     */  
/*  228:     */  final int compare(short k1, short k2)
/*  229:     */  {
/*  230: 230 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  231:     */  }
/*  232:     */  
/*  240:     */  final Entry<V> findKey(short k)
/*  241:     */  {
/*  242: 242 */    Entry<V> e = this.tree;
/*  243:     */    
/*  244:     */    int cmp;
/*  245: 245 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) { e = cmp < 0 ? e.left() : e.right();
/*  246:     */    }
/*  247: 247 */    return e;
/*  248:     */  }
/*  249:     */  
/*  256:     */  final Entry<V> locateKey(short k)
/*  257:     */  {
/*  258: 258 */    Entry<V> e = this.tree;Entry<V> last = this.tree;
/*  259: 259 */    int cmp = 0;
/*  260:     */    
/*  261: 261 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  262: 262 */      last = e;
/*  263: 263 */      e = cmp < 0 ? e.left() : e.right();
/*  264:     */    }
/*  265:     */    
/*  266: 266 */    return cmp == 0 ? e : last;
/*  267:     */  }
/*  268:     */  
/*  274:     */  private void allocatePaths()
/*  275:     */  {
/*  276: 276 */    this.dirPath = new boolean[64];
/*  277: 277 */    this.nodePath = new Entry[64];
/*  278:     */  }
/*  279:     */  
/*  282:     */  public V put(short k, V v)
/*  283:     */  {
/*  284: 284 */    this.modified = false;
/*  285: 285 */    int maxDepth = 0;
/*  286:     */    
/*  287: 287 */    if (this.tree == null) {
/*  288: 288 */      this.count += 1;
/*  289: 289 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  290:     */    }
/*  291:     */    else {
/*  292: 292 */      Entry<V> p = this.tree;
/*  293: 293 */      int i = 0;
/*  294:     */      for (;;) {
/*  295:     */        int cmp;
/*  296: 296 */        if ((cmp = compare(k, p.key)) == 0) {
/*  297: 297 */          V oldValue = p.value;
/*  298: 298 */          p.value = v;
/*  299:     */          
/*  300: 300 */          while (i-- != 0) this.nodePath[i] = null;
/*  301: 301 */          return oldValue;
/*  302:     */        }
/*  303:     */        
/*  304: 304 */        this.nodePath[i] = p;
/*  305:     */        
/*  306: 306 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  307: 307 */          if (p.succ()) {
/*  308: 308 */            this.count += 1;
/*  309: 309 */            Entry<V> e = new Entry(k, v);
/*  310:     */            
/*  311: 311 */            if (p.right == null) { this.lastEntry = e;
/*  312:     */            }
/*  313: 313 */            e.left = p;
/*  314: 314 */            e.right = p.right;
/*  315:     */            
/*  316: 316 */            p.right(e);
/*  317:     */            
/*  318: 318 */            break;
/*  319:     */          }
/*  320:     */          
/*  321: 321 */          p = p.right;
/*  322:     */        }
/*  323:     */        else {
/*  324: 324 */          if (p.pred()) {
/*  325: 325 */            this.count += 1;
/*  326: 326 */            Entry<V> e = new Entry(k, v);
/*  327:     */            
/*  328: 328 */            if (p.left == null) { this.firstEntry = e;
/*  329:     */            }
/*  330: 330 */            e.right = p;
/*  331: 331 */            e.left = p.left;
/*  332:     */            
/*  333: 333 */            p.left(e);
/*  334:     */            
/*  335: 335 */            break;
/*  336:     */          }
/*  337:     */          
/*  338: 338 */          p = p.left;
/*  339:     */        }
/*  340:     */      }
/*  341:     */      Entry<V> e;
/*  342: 342 */      this.modified = true;
/*  343: 343 */      maxDepth = i--;
/*  344:     */      
/*  345: 345 */      while ((i > 0) && (!this.nodePath[i].black())) {
/*  346: 346 */        if (this.dirPath[(i - 1)] == 0) {
/*  347: 347 */          Entry<V> y = this.nodePath[(i - 1)].right;
/*  348:     */          
/*  349: 349 */          if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  350: 350 */            this.nodePath[i].black(true);
/*  351: 351 */            y.black(true);
/*  352: 352 */            this.nodePath[(i - 1)].black(false);
/*  353: 353 */            i -= 2;
/*  355:     */          }
/*  356:     */          else
/*  357:     */          {
/*  358: 358 */            if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*  359:     */            } else {
/*  360: 360 */              Entry<V> x = this.nodePath[i];
/*  361: 361 */              y = x.right;
/*  362: 362 */              x.right = y.left;
/*  363: 363 */              y.left = x;
/*  364: 364 */              this.nodePath[(i - 1)].left = y;
/*  365:     */              
/*  366: 366 */              if (y.pred()) {
/*  367: 367 */                y.pred(false);
/*  368: 368 */                x.succ(y);
/*  369:     */              }
/*  370:     */            }
/*  371:     */            
/*  372: 372 */            Entry<V> x = this.nodePath[(i - 1)];
/*  373: 373 */            x.black(false);
/*  374: 374 */            y.black(true);
/*  375:     */            
/*  376: 376 */            x.left = y.right;
/*  377: 377 */            y.right = x;
/*  378: 378 */            if (i < 2) { this.tree = y;
/*  379:     */            }
/*  380: 380 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  381: 381 */              this.nodePath[(i - 2)].left = y;
/*  382:     */            }
/*  383:     */            
/*  384: 384 */            if (!y.succ()) break;
/*  385: 385 */            y.succ(false);
/*  386: 386 */            x.pred(y);break;
/*  387:     */          }
/*  388:     */          
/*  389:     */        }
/*  390:     */        else
/*  391:     */        {
/*  392: 392 */          Entry<V> y = this.nodePath[(i - 1)].left;
/*  393:     */          
/*  394: 394 */          if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  395: 395 */            this.nodePath[i].black(true);
/*  396: 396 */            y.black(true);
/*  397: 397 */            this.nodePath[(i - 1)].black(false);
/*  398: 398 */            i -= 2;
/*  400:     */          }
/*  401:     */          else
/*  402:     */          {
/*  403: 403 */            if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*  404:     */            } else {
/*  405: 405 */              Entry<V> x = this.nodePath[i];
/*  406: 406 */              y = x.left;
/*  407: 407 */              x.left = y.right;
/*  408: 408 */              y.right = x;
/*  409: 409 */              this.nodePath[(i - 1)].right = y;
/*  410:     */              
/*  411: 411 */              if (y.succ()) {
/*  412: 412 */                y.succ(false);
/*  413: 413 */                x.pred(y);
/*  414:     */              }
/*  415:     */            }
/*  416:     */            
/*  418: 418 */            Entry<V> x = this.nodePath[(i - 1)];
/*  419: 419 */            x.black(false);
/*  420: 420 */            y.black(true);
/*  421:     */            
/*  422: 422 */            x.right = y.left;
/*  423: 423 */            y.left = x;
/*  424: 424 */            if (i < 2) { this.tree = y;
/*  425:     */            }
/*  426: 426 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  427: 427 */              this.nodePath[(i - 2)].left = y;
/*  428:     */            }
/*  429:     */            
/*  430: 430 */            if (!y.pred()) break;
/*  431: 431 */            y.pred(false);
/*  432: 432 */            x.succ(y);break;
/*  433:     */          }
/*  434:     */        }
/*  435:     */      }
/*  436:     */    }
/*  437:     */    
/*  440: 440 */    this.tree.black(true);
/*  441:     */    
/*  442: 442 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  443:     */    }
/*  444:     */    
/*  447: 447 */    return this.defRetValue;
/*  448:     */  }
/*  449:     */  
/*  454:     */  public V remove(short k)
/*  455:     */  {
/*  456: 456 */    this.modified = false;
/*  457:     */    
/*  458: 458 */    if (this.tree == null) { return this.defRetValue;
/*  459:     */    }
/*  460: 460 */    Entry<V> p = this.tree;
/*  461:     */    
/*  462: 462 */    int i = 0;
/*  463: 463 */    short kk = k;
/*  464:     */    
/*  465:     */    int cmp;
/*  466: 466 */    while ((cmp = compare(kk, p.key)) != 0)
/*  467:     */    {
/*  468: 468 */      this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  469: 469 */      this.nodePath[i] = p;
/*  470:     */      
/*  471: 471 */      if (this.dirPath[(i++)] != 0) {
/*  472: 472 */        if ((p = p.right()) == null)
/*  473:     */        {
/*  474: 474 */          while (i-- != 0) this.nodePath[i] = null;
/*  475: 475 */          return this.defRetValue;
/*  476:     */        }
/*  477:     */        
/*  478:     */      }
/*  479: 479 */      else if ((p = p.left()) == null)
/*  480:     */      {
/*  481: 481 */        while (i-- != 0) this.nodePath[i] = null;
/*  482: 482 */        return this.defRetValue;
/*  483:     */      }
/*  484:     */    }
/*  485:     */    
/*  488: 488 */    if (p.left == null) this.firstEntry = p.next();
/*  489: 489 */    if (p.right == null) { this.lastEntry = p.prev();
/*  490:     */    }
/*  491: 491 */    if (p.succ()) {
/*  492: 492 */      if (p.pred()) {
/*  493: 493 */        if (i == 0) { this.tree = p.left;
/*  494:     */        }
/*  495: 495 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else {
/*  496: 496 */          this.nodePath[(i - 1)].pred(p.left);
/*  497:     */        }
/*  498:     */      }
/*  499:     */      else {
/*  500: 500 */        p.prev().right = p.right;
/*  501:     */        
/*  502: 502 */        if (i == 0) { this.tree = p.left;
/*  503:     */        }
/*  504: 504 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  505: 505 */          this.nodePath[(i - 1)].left = p.left;
/*  506:     */        }
/*  507:     */      }
/*  508:     */    }
/*  509:     */    else
/*  510:     */    {
/*  511: 511 */      Entry<V> r = p.right;
/*  512:     */      
/*  513: 513 */      if (r.pred()) {
/*  514: 514 */        r.left = p.left;
/*  515: 515 */        r.pred(p.pred());
/*  516: 516 */        if (!r.pred()) r.prev().right = r;
/*  517: 517 */        if (i == 0) { this.tree = r;
/*  518:     */        }
/*  519: 519 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  520: 520 */          this.nodePath[(i - 1)].left = r;
/*  521:     */        }
/*  522:     */        
/*  523: 523 */        boolean color = r.black();
/*  524: 524 */        r.black(p.black());
/*  525: 525 */        p.black(color);
/*  526: 526 */        this.dirPath[i] = true;
/*  527: 527 */        this.nodePath[(i++)] = r;
/*  528:     */      }
/*  529:     */      else
/*  530:     */      {
/*  531: 531 */        int j = i++;
/*  532:     */        Entry<V> s;
/*  533:     */        for (;;) {
/*  534: 534 */          this.dirPath[i] = false;
/*  535: 535 */          this.nodePath[(i++)] = r;
/*  536: 536 */          s = r.left;
/*  537: 537 */          if (s.pred()) break;
/*  538: 538 */          r = s;
/*  539:     */        }
/*  540:     */        
/*  541: 541 */        this.dirPath[j] = true;
/*  542: 542 */        this.nodePath[j] = s;
/*  543:     */        
/*  544: 544 */        if (s.succ()) r.pred(s); else {
/*  545: 545 */          r.left = s.right;
/*  546:     */        }
/*  547: 547 */        s.left = p.left;
/*  548:     */        
/*  549: 549 */        if (!p.pred()) {
/*  550: 550 */          p.prev().right = s;
/*  551: 551 */          s.pred(false);
/*  552:     */        }
/*  553:     */        
/*  554: 554 */        s.right(p.right);
/*  555:     */        
/*  556: 556 */        boolean color = s.black();
/*  557: 557 */        s.black(p.black());
/*  558: 558 */        p.black(color);
/*  559:     */        
/*  560: 560 */        if (j == 0) { this.tree = s;
/*  561:     */        }
/*  562: 562 */        else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  563: 563 */          this.nodePath[(j - 1)].left = s;
/*  564:     */        }
/*  565:     */      }
/*  566:     */    }
/*  567:     */    
/*  568: 568 */    int maxDepth = i;
/*  569:     */    
/*  570: 570 */    if (p.black()) {
/*  571: 571 */      for (; i > 0; i--) {
/*  572: 572 */        if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*  573:     */        {
/*  574: 574 */          Entry<V> x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  575:     */          
/*  576: 576 */          if (!x.black()) {
/*  577: 577 */            x.black(true);
/*  578: 578 */            break;
/*  579:     */          }
/*  580:     */        }
/*  581:     */        
/*  582: 582 */        if (this.dirPath[(i - 1)] == 0) {
/*  583: 583 */          Entry<V> w = this.nodePath[(i - 1)].right;
/*  584:     */          
/*  585: 585 */          if (!w.black()) {
/*  586: 586 */            w.black(true);
/*  587: 587 */            this.nodePath[(i - 1)].black(false);
/*  588:     */            
/*  589: 589 */            this.nodePath[(i - 1)].right = w.left;
/*  590: 590 */            w.left = this.nodePath[(i - 1)];
/*  591:     */            
/*  592: 592 */            if (i < 2) { this.tree = w;
/*  593:     */            }
/*  594: 594 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  595: 595 */              this.nodePath[(i - 2)].left = w;
/*  596:     */            }
/*  597:     */            
/*  598: 598 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  599: 599 */            this.dirPath[i] = false;
/*  600: 600 */            this.nodePath[(i - 1)] = w;
/*  601: 601 */            if (maxDepth == i++) { maxDepth++;
/*  602:     */            }
/*  603: 603 */            w = this.nodePath[(i - 1)].right;
/*  604:     */          }
/*  605:     */          
/*  606: 606 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  607:     */          {
/*  608: 608 */            w.black(false);
/*  609:     */          }
/*  610:     */          else {
/*  611: 611 */            if ((w.succ()) || (w.right.black())) {
/*  612: 612 */              Entry<V> y = w.left;
/*  613:     */              
/*  614: 614 */              y.black(true);
/*  615: 615 */              w.black(false);
/*  616: 616 */              w.left = y.right;
/*  617: 617 */              y.right = w;
/*  618: 618 */              w = this.nodePath[(i - 1)].right = y;
/*  619:     */              
/*  620: 620 */              if (w.succ()) {
/*  621: 621 */                w.succ(false);
/*  622: 622 */                w.right.pred(w);
/*  623:     */              }
/*  624:     */            }
/*  625:     */            
/*  626: 626 */            w.black(this.nodePath[(i - 1)].black());
/*  627: 627 */            this.nodePath[(i - 1)].black(true);
/*  628: 628 */            w.right.black(true);
/*  629:     */            
/*  630: 630 */            this.nodePath[(i - 1)].right = w.left;
/*  631: 631 */            w.left = this.nodePath[(i - 1)];
/*  632:     */            
/*  633: 633 */            if (i < 2) { this.tree = w;
/*  634:     */            }
/*  635: 635 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  636: 636 */              this.nodePath[(i - 2)].left = w;
/*  637:     */            }
/*  638:     */            
/*  639: 639 */            if (!w.pred()) break;
/*  640: 640 */            w.pred(false);
/*  641: 641 */            this.nodePath[(i - 1)].succ(w);break;
/*  642:     */          }
/*  643:     */          
/*  644:     */        }
/*  645:     */        else
/*  646:     */        {
/*  647: 647 */          Entry<V> w = this.nodePath[(i - 1)].left;
/*  648:     */          
/*  649: 649 */          if (!w.black()) {
/*  650: 650 */            w.black(true);
/*  651: 651 */            this.nodePath[(i - 1)].black(false);
/*  652:     */            
/*  653: 653 */            this.nodePath[(i - 1)].left = w.right;
/*  654: 654 */            w.right = this.nodePath[(i - 1)];
/*  655:     */            
/*  656: 656 */            if (i < 2) { this.tree = w;
/*  657:     */            }
/*  658: 658 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  659: 659 */              this.nodePath[(i - 2)].left = w;
/*  660:     */            }
/*  661:     */            
/*  662: 662 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  663: 663 */            this.dirPath[i] = true;
/*  664: 664 */            this.nodePath[(i - 1)] = w;
/*  665: 665 */            if (maxDepth == i++) { maxDepth++;
/*  666:     */            }
/*  667: 667 */            w = this.nodePath[(i - 1)].left;
/*  668:     */          }
/*  669:     */          
/*  670: 670 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  671:     */          {
/*  672: 672 */            w.black(false);
/*  673:     */          }
/*  674:     */          else {
/*  675: 675 */            if ((w.pred()) || (w.left.black())) {
/*  676: 676 */              Entry<V> y = w.right;
/*  677:     */              
/*  678: 678 */              y.black(true);
/*  679: 679 */              w.black(false);
/*  680: 680 */              w.right = y.left;
/*  681: 681 */              y.left = w;
/*  682: 682 */              w = this.nodePath[(i - 1)].left = y;
/*  683:     */              
/*  684: 684 */              if (w.pred()) {
/*  685: 685 */                w.pred(false);
/*  686: 686 */                w.left.succ(w);
/*  687:     */              }
/*  688:     */            }
/*  689:     */            
/*  690: 690 */            w.black(this.nodePath[(i - 1)].black());
/*  691: 691 */            this.nodePath[(i - 1)].black(true);
/*  692: 692 */            w.left.black(true);
/*  693:     */            
/*  694: 694 */            this.nodePath[(i - 1)].left = w.right;
/*  695: 695 */            w.right = this.nodePath[(i - 1)];
/*  696:     */            
/*  697: 697 */            if (i < 2) { this.tree = w;
/*  698:     */            }
/*  699: 699 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  700: 700 */              this.nodePath[(i - 2)].left = w;
/*  701:     */            }
/*  702:     */            
/*  703: 703 */            if (!w.succ()) break;
/*  704: 704 */            w.succ(false);
/*  705: 705 */            this.nodePath[(i - 1)].pred(w);break;
/*  706:     */          }
/*  707:     */        }
/*  708:     */      }
/*  709:     */      
/*  712: 712 */      if (this.tree != null) { this.tree.black(true);
/*  713:     */      }
/*  714:     */    }
/*  715: 715 */    this.modified = true;
/*  716: 716 */    this.count -= 1;
/*  717:     */    
/*  718: 718 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  719:     */    }
/*  720:     */    
/*  723: 723 */    return p.value;
/*  724:     */  }
/*  725:     */  
/*  728:     */  public V put(Short ok, V ov)
/*  729:     */  {
/*  730: 730 */    V oldValue = put(ok.shortValue(), ov);
/*  731: 731 */    return this.modified ? this.defRetValue : oldValue;
/*  732:     */  }
/*  733:     */  
/*  735:     */  public V remove(Object ok)
/*  736:     */  {
/*  737: 737 */    V oldValue = remove(((Short)ok).shortValue());
/*  738: 738 */    return this.modified ? oldValue : this.defRetValue;
/*  739:     */  }
/*  740:     */  
/*  742:     */  public boolean containsValue(Object v)
/*  743:     */  {
/*  744: 744 */    Short2ReferenceRBTreeMap<V>.ValueIterator i = new ValueIterator(null);
/*  745:     */    
/*  747: 747 */    int j = this.count;
/*  748: 748 */    while (j-- != 0) {
/*  749: 749 */      Object ev = i.next();
/*  750: 750 */      if (ev == v) { return true;
/*  751:     */      }
/*  752:     */    }
/*  753: 753 */    return false;
/*  754:     */  }
/*  755:     */  
/*  756:     */  public void clear()
/*  757:     */  {
/*  758: 758 */    this.count = 0;
/*  759: 759 */    this.tree = null;
/*  760: 760 */    this.entries = null;
/*  761: 761 */    this.values = null;
/*  762: 762 */    this.keys = null;
/*  763: 763 */    this.firstEntry = (this.lastEntry = null);
/*  764:     */  }
/*  765:     */  
/*  768:     */  private static final class Entry<V>
/*  769:     */    implements Cloneable, Short2ReferenceMap.Entry<V>
/*  770:     */  {
/*  771:     */    private static final int BLACK_MASK = 1;
/*  772:     */    
/*  774:     */    private static final int SUCC_MASK = -2147483648;
/*  775:     */    
/*  777:     */    private static final int PRED_MASK = 1073741824;
/*  778:     */    
/*  780:     */    short key;
/*  781:     */    
/*  783:     */    V value;
/*  784:     */    
/*  786:     */    Entry<V> left;
/*  787:     */    
/*  789:     */    Entry<V> right;
/*  790:     */    
/*  792:     */    int info;
/*  793:     */    
/*  795:     */    Entry() {}
/*  796:     */    
/*  798:     */    Entry(short k, V v)
/*  799:     */    {
/*  800: 800 */      this.key = k;
/*  801: 801 */      this.value = v;
/*  802: 802 */      this.info = -1073741824;
/*  803:     */    }
/*  804:     */    
/*  809:     */    Entry<V> left()
/*  810:     */    {
/*  811: 811 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  812:     */    }
/*  813:     */    
/*  818:     */    Entry<V> right()
/*  819:     */    {
/*  820: 820 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  821:     */    }
/*  822:     */    
/*  825:     */    boolean pred()
/*  826:     */    {
/*  827: 827 */      return (this.info & 0x40000000) != 0;
/*  828:     */    }
/*  829:     */    
/*  832:     */    boolean succ()
/*  833:     */    {
/*  834: 834 */      return (this.info & 0x80000000) != 0;
/*  835:     */    }
/*  836:     */    
/*  839:     */    void pred(boolean pred)
/*  840:     */    {
/*  841: 841 */      if (pred) this.info |= 1073741824; else {
/*  842: 842 */        this.info &= -1073741825;
/*  843:     */      }
/*  844:     */    }
/*  845:     */    
/*  847:     */    void succ(boolean succ)
/*  848:     */    {
/*  849: 849 */      if (succ) this.info |= -2147483648; else {
/*  850: 850 */        this.info &= 2147483647;
/*  851:     */      }
/*  852:     */    }
/*  853:     */    
/*  855:     */    void pred(Entry<V> pred)
/*  856:     */    {
/*  857: 857 */      this.info |= 1073741824;
/*  858: 858 */      this.left = pred;
/*  859:     */    }
/*  860:     */    
/*  863:     */    void succ(Entry<V> succ)
/*  864:     */    {
/*  865: 865 */      this.info |= -2147483648;
/*  866: 866 */      this.right = succ;
/*  867:     */    }
/*  868:     */    
/*  871:     */    void left(Entry<V> left)
/*  872:     */    {
/*  873: 873 */      this.info &= -1073741825;
/*  874: 874 */      this.left = left;
/*  875:     */    }
/*  876:     */    
/*  879:     */    void right(Entry<V> right)
/*  880:     */    {
/*  881: 881 */      this.info &= 2147483647;
/*  882: 882 */      this.right = right;
/*  883:     */    }
/*  884:     */    
/*  888:     */    boolean black()
/*  889:     */    {
/*  890: 890 */      return (this.info & 0x1) != 0;
/*  891:     */    }
/*  892:     */    
/*  895:     */    void black(boolean black)
/*  896:     */    {
/*  897: 897 */      if (black) this.info |= 1; else {
/*  898: 898 */        this.info &= -2;
/*  899:     */      }
/*  900:     */    }
/*  901:     */    
/*  905:     */    Entry<V> next()
/*  906:     */    {
/*  907: 907 */      Entry<V> next = this.right;
/*  908: 908 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  909: 909 */      return next;
/*  910:     */    }
/*  911:     */    
/*  916:     */    Entry<V> prev()
/*  917:     */    {
/*  918: 918 */      Entry<V> prev = this.left;
/*  919: 919 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  920: 920 */      return prev;
/*  921:     */    }
/*  922:     */    
/*  923:     */    public Short getKey() {
/*  924: 924 */      return Short.valueOf(this.key);
/*  925:     */    }
/*  926:     */    
/*  927:     */    public short getShortKey()
/*  928:     */    {
/*  929: 929 */      return this.key;
/*  930:     */    }
/*  931:     */    
/*  932:     */    public V getValue()
/*  933:     */    {
/*  934: 934 */      return this.value;
/*  935:     */    }
/*  936:     */    
/*  942:     */    public V setValue(V value)
/*  943:     */    {
/*  944: 944 */      V oldValue = this.value;
/*  945: 945 */      this.value = value;
/*  946: 946 */      return oldValue;
/*  947:     */    }
/*  948:     */    
/*  949:     */    public Entry<V> clone() {
/*  950:     */      Entry<V> c;
/*  951:     */      try {
/*  952: 952 */        c = (Entry)super.clone();
/*  953:     */      }
/*  954:     */      catch (CloneNotSupportedException cantHappen) {
/*  955: 955 */        throw new InternalError();
/*  956:     */      }
/*  957: 957 */      c.key = this.key;
/*  958: 958 */      c.value = this.value;
/*  959: 959 */      c.info = this.info;
/*  960: 960 */      return c;
/*  961:     */    }
/*  962:     */    
/*  963:     */    public boolean equals(Object o) {
/*  964: 964 */      if (!(o instanceof Map.Entry)) return false;
/*  965: 965 */      Map.Entry<Short, V> e = (Map.Entry)o;
/*  966: 966 */      return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == e.getValue());
/*  967:     */    }
/*  968:     */    
/*  969: 969 */    public int hashCode() { return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
/*  970:     */    
/*  971:     */    public String toString() {
/*  972: 972 */      return this.key + "=>" + this.value;
/*  973:     */    }
/*  974:     */  }
/*  975:     */  
/* 1004:     */  public boolean containsKey(short k)
/* 1005:     */  {
/* 1006:1006 */    return findKey(k) != null;
/* 1007:     */  }
/* 1008:     */  
/* 1009:1009 */  public int size() { return this.count; }
/* 1010:     */  
/* 1011:     */  public boolean isEmpty() {
/* 1012:1012 */    return this.count == 0;
/* 1013:     */  }
/* 1014:     */  
/* 1015:     */  public V get(short k) {
/* 1016:1016 */    Entry<V> e = findKey(k);
/* 1017:1017 */    return e == null ? this.defRetValue : e.value;
/* 1018:     */  }
/* 1019:     */  
/* 1020:1020 */  public short firstShortKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1021:1021 */    return this.firstEntry.key;
/* 1022:     */  }
/* 1023:     */  
/* 1024:1024 */  public short lastShortKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1025:1025 */    return this.lastEntry.key;
/* 1026:     */  }
/* 1027:     */  
/* 1030:     */  private class TreeIterator
/* 1031:     */  {
/* 1032:     */    Short2ReferenceRBTreeMap.Entry<V> prev;
/* 1033:     */    
/* 1035:     */    Short2ReferenceRBTreeMap.Entry<V> next;
/* 1036:     */    
/* 1037:     */    Short2ReferenceRBTreeMap.Entry<V> curr;
/* 1038:     */    
/* 1039:1039 */    int index = 0;
/* 1040:     */    
/* 1041:1041 */    TreeIterator() { this.next = Short2ReferenceRBTreeMap.this.firstEntry; }
/* 1042:     */    
/* 1043:     */    TreeIterator(short k) {
/* 1044:1044 */      if ((this.next = Short2ReferenceRBTreeMap.this.locateKey(k)) != null)
/* 1045:1045 */        if (Short2ReferenceRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1046:1046 */          this.prev = this.next;
/* 1047:1047 */          this.next = this.next.next();
/* 1048:     */        } else {
/* 1049:1049 */          this.prev = this.next.prev();
/* 1050:     */        } }
/* 1051:     */    
/* 1052:1052 */    public boolean hasNext() { return this.next != null; }
/* 1053:1053 */    public boolean hasPrevious() { return this.prev != null; }
/* 1054:     */    
/* 1055:1055 */    void updateNext() { this.next = this.next.next(); }
/* 1056:     */    
/* 1057:     */    Short2ReferenceRBTreeMap.Entry<V> nextEntry() {
/* 1058:1058 */      if (!hasNext()) throw new NoSuchElementException();
/* 1059:1059 */      this.curr = (this.prev = this.next);
/* 1060:1060 */      this.index += 1;
/* 1061:1061 */      updateNext();
/* 1062:1062 */      return this.curr;
/* 1063:     */    }
/* 1064:     */    
/* 1065:1065 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1066:     */    
/* 1067:     */    Short2ReferenceRBTreeMap.Entry<V> previousEntry() {
/* 1068:1068 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1069:1069 */      this.curr = (this.next = this.prev);
/* 1070:1070 */      this.index -= 1;
/* 1071:1071 */      updatePrevious();
/* 1072:1072 */      return this.curr;
/* 1073:     */    }
/* 1074:     */    
/* 1075:1075 */    public int nextIndex() { return this.index; }
/* 1076:     */    
/* 1078:1078 */    public int previousIndex() { return this.index - 1; }
/* 1079:     */    
/* 1080:     */    public void remove() {
/* 1081:1081 */      if (this.curr == null) { throw new IllegalStateException();
/* 1082:     */      }
/* 1083:     */      
/* 1084:1084 */      if (this.curr == this.prev) this.index -= 1;
/* 1085:1085 */      this.next = (this.prev = this.curr);
/* 1086:1086 */      updatePrevious();
/* 1087:1087 */      updateNext();
/* 1088:1088 */      Short2ReferenceRBTreeMap.this.remove(this.curr.key);
/* 1089:1089 */      this.curr = null;
/* 1090:     */    }
/* 1091:     */    
/* 1092:1092 */    public int skip(int n) { int i = n;
/* 1093:1093 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 1094:1094 */      return n - i - 1;
/* 1095:     */    }
/* 1096:     */    
/* 1097:1097 */    public int back(int n) { int i = n;
/* 1098:1098 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1099:1099 */      return n - i - 1;
/* 1100:     */    }
/* 1101:     */  }
/* 1102:     */  
/* 1103:     */  private class EntryIterator
/* 1104:     */    extends Short2ReferenceRBTreeMap<V>.TreeIterator
/* 1105:     */    implements ObjectListIterator<Short2ReferenceMap.Entry<V>>
/* 1106:     */  {
/* 1107:1107 */    EntryIterator() { super(); }
/* 1108:     */    
/* 1109:1109 */    EntryIterator(short k) { super(k); }
/* 1110:     */    
/* 1111:1111 */    public Short2ReferenceMap.Entry<V> next() { return nextEntry(); }
/* 1112:1112 */    public Short2ReferenceMap.Entry<V> previous() { return previousEntry(); }
/* 1113:1113 */    public void set(Short2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1114:1114 */    public void add(Short2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1115:     */  }
/* 1116:     */  
/* 1117:1117 */  public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1118:1118 */        final Comparator<? super Short2ReferenceMap.Entry<V>> comparator = new Comparator() {
/* 1119:     */          public int compare(Short2ReferenceMap.Entry<V> x, Short2ReferenceMap.Entry<V> y) {
/* 1120:1120 */            return Short2ReferenceRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1121:     */          }
/* 1122:     */        };
/* 1123:     */        
/* 1124:1124 */        public Comparator<? super Short2ReferenceMap.Entry<V>> comparator() { return this.comparator; }
/* 1125:     */        
/* 1126:     */        public ObjectBidirectionalIterator<Short2ReferenceMap.Entry<V>> iterator() {
/* 1127:1127 */          return new Short2ReferenceRBTreeMap.EntryIterator(Short2ReferenceRBTreeMap.this);
/* 1128:     */        }
/* 1129:     */        
/* 1130:1130 */        public ObjectBidirectionalIterator<Short2ReferenceMap.Entry<V>> iterator(Short2ReferenceMap.Entry<V> from) { return new Short2ReferenceRBTreeMap.EntryIterator(Short2ReferenceRBTreeMap.this, ((Short)from.getKey()).shortValue()); }
/* 1131:     */        
/* 1132:     */        public boolean contains(Object o)
/* 1133:     */        {
/* 1134:1134 */          if (!(o instanceof Map.Entry)) return false;
/* 1135:1135 */          Map.Entry<Short, V> e = (Map.Entry)o;
/* 1136:1136 */          Short2ReferenceRBTreeMap.Entry<V> f = Short2ReferenceRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1137:1137 */          return e.equals(f);
/* 1138:     */        }
/* 1139:     */        
/* 1140:     */        public boolean remove(Object o) {
/* 1141:1141 */          if (!(o instanceof Map.Entry)) return false;
/* 1142:1142 */          Map.Entry<Short, V> e = (Map.Entry)o;
/* 1143:1143 */          Short2ReferenceRBTreeMap.Entry<V> f = Short2ReferenceRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1144:1144 */          if (f != null) Short2ReferenceRBTreeMap.this.remove(f.key);
/* 1145:1145 */          return f != null; }
/* 1146:     */        
/* 1147:1147 */        public int size() { return Short2ReferenceRBTreeMap.this.count; }
/* 1148:1148 */        public void clear() { Short2ReferenceRBTreeMap.this.clear(); }
/* 1149:1149 */        public Short2ReferenceMap.Entry<V> first() { return Short2ReferenceRBTreeMap.this.firstEntry; }
/* 1150:1150 */        public Short2ReferenceMap.Entry<V> last() { return Short2ReferenceRBTreeMap.this.lastEntry; }
/* 1151:1151 */        public ObjectSortedSet<Short2ReferenceMap.Entry<V>> subSet(Short2ReferenceMap.Entry<V> from, Short2ReferenceMap.Entry<V> to) { return Short2ReferenceRBTreeMap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2ReferenceEntrySet(); }
/* 1152:1152 */        public ObjectSortedSet<Short2ReferenceMap.Entry<V>> headSet(Short2ReferenceMap.Entry<V> to) { return Short2ReferenceRBTreeMap.this.headMap((Short)to.getKey()).short2ReferenceEntrySet(); }
/* 1153:1153 */        public ObjectSortedSet<Short2ReferenceMap.Entry<V>> tailSet(Short2ReferenceMap.Entry<V> from) { return Short2ReferenceRBTreeMap.this.tailMap((Short)from.getKey()).short2ReferenceEntrySet(); }
/* 1154:     */      };
/* 1155:1155 */    return this.entries;
/* 1156:     */  }
/* 1157:     */  
/* 1160:     */  private final class KeyIterator
/* 1161:     */    extends Short2ReferenceRBTreeMap.TreeIterator
/* 1162:     */    implements ShortListIterator
/* 1163:     */  {
/* 1164:1164 */    public KeyIterator() { super(); }
/* 1165:1165 */    public KeyIterator(short k) { super(k); }
/* 1166:1166 */    public short nextShort() { return nextEntry().key; }
/* 1167:1167 */    public short previousShort() { return previousEntry().key; }
/* 1168:1168 */    public void set(short k) { throw new UnsupportedOperationException(); }
/* 1169:1169 */    public void add(short k) { throw new UnsupportedOperationException(); }
/* 1170:1170 */    public Short next() { return Short.valueOf(nextEntry().key); }
/* 1171:1171 */    public Short previous() { return Short.valueOf(previousEntry().key); }
/* 1172:1172 */    public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1173:1173 */    public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1174:     */  }
/* 1175:     */  
/* 1176:1176 */  private class KeySet extends AbstractShort2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1177:1177 */    public ShortBidirectionalIterator iterator() { return new Short2ReferenceRBTreeMap.KeyIterator(Short2ReferenceRBTreeMap.this); }
/* 1178:1178 */    public ShortBidirectionalIterator iterator(short from) { return new Short2ReferenceRBTreeMap.KeyIterator(Short2ReferenceRBTreeMap.this, from); }
/* 1179:     */  }
/* 1180:     */  
/* 1187:     */  public ShortSortedSet keySet()
/* 1188:     */  {
/* 1189:1189 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1190:1190 */    return this.keys;
/* 1191:     */  }
/* 1192:     */  
/* 1194:     */  private final class ValueIterator
/* 1195:     */    extends Short2ReferenceRBTreeMap<V>.TreeIterator
/* 1196:     */    implements ObjectListIterator<V>
/* 1197:     */  {
/* 1198:1198 */    private ValueIterator() { super(); }
/* 1199:1199 */    public V next() { return nextEntry().value; }
/* 1200:1200 */    public V previous() { return previousEntry().value; }
/* 1201:1201 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1202:1202 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1203:     */  }
/* 1204:     */  
/* 1211:     */  public ReferenceCollection<V> values()
/* 1212:     */  {
/* 1213:1213 */    if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1214:     */        public ObjectIterator<V> iterator() {
/* 1215:1215 */          return new Short2ReferenceRBTreeMap.ValueIterator(Short2ReferenceRBTreeMap.this, null);
/* 1216:     */        }
/* 1217:     */        
/* 1218:1218 */        public boolean contains(Object k) { return Short2ReferenceRBTreeMap.this.containsValue(k); }
/* 1219:     */        
/* 1220:     */        public int size() {
/* 1221:1221 */          return Short2ReferenceRBTreeMap.this.count;
/* 1222:     */        }
/* 1223:     */        
/* 1224:1224 */        public void clear() { Short2ReferenceRBTreeMap.this.clear(); }
/* 1225:     */      };
/* 1226:     */    }
/* 1227:1227 */    return this.values;
/* 1228:     */  }
/* 1229:     */  
/* 1230:1230 */  public ShortComparator comparator() { return this.actualComparator; }
/* 1231:     */  
/* 1232:     */  public Short2ReferenceSortedMap<V> headMap(short to) {
/* 1233:1233 */    return new Submap((short)0, true, to, false);
/* 1234:     */  }
/* 1235:     */  
/* 1236:1236 */  public Short2ReferenceSortedMap<V> tailMap(short from) { return new Submap(from, false, (short)0, true); }
/* 1237:     */  
/* 1238:     */  public Short2ReferenceSortedMap<V> subMap(short from, short to) {
/* 1239:1239 */    return new Submap(from, false, to, false);
/* 1240:     */  }
/* 1241:     */  
/* 1245:     */  private final class Submap
/* 1246:     */    extends AbstractShort2ReferenceSortedMap<V>
/* 1247:     */    implements Serializable
/* 1248:     */  {
/* 1249:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1250:     */    
/* 1253:     */    short from;
/* 1254:     */    
/* 1256:     */    short to;
/* 1257:     */    
/* 1259:     */    boolean bottom;
/* 1260:     */    
/* 1262:     */    boolean top;
/* 1263:     */    
/* 1265:     */    protected volatile transient ObjectSortedSet<Short2ReferenceMap.Entry<V>> entries;
/* 1266:     */    
/* 1268:     */    protected volatile transient ShortSortedSet keys;
/* 1269:     */    
/* 1271:     */    protected volatile transient ReferenceCollection<V> values;
/* 1272:     */    
/* 1275:     */    public Submap(short from, boolean bottom, short to, boolean top)
/* 1276:     */    {
/* 1277:1277 */      if ((!bottom) && (!top) && (Short2ReferenceRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1278:1278 */      this.from = from;
/* 1279:1279 */      this.bottom = bottom;
/* 1280:1280 */      this.to = to;
/* 1281:1281 */      this.top = top;
/* 1282:1282 */      this.defRetValue = Short2ReferenceRBTreeMap.this.defRetValue;
/* 1283:     */    }
/* 1284:     */    
/* 1285:1285 */    public void clear() { Short2ReferenceRBTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1286:1286 */      while (i.hasNext()) {
/* 1287:1287 */        i.nextEntry();
/* 1288:1288 */        i.remove();
/* 1289:     */      }
/* 1290:     */    }
/* 1291:     */    
/* 1294:     */    final boolean in(short k)
/* 1295:     */    {
/* 1296:1296 */      return ((this.bottom) || (Short2ReferenceRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Short2ReferenceRBTreeMap.this.compare(k, this.to) < 0));
/* 1297:     */    }
/* 1298:     */    
/* 1299:     */    public ObjectSortedSet<Short2ReferenceMap.Entry<V>> short2ReferenceEntrySet() {
/* 1300:1300 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1301:     */          public ObjectBidirectionalIterator<Short2ReferenceMap.Entry<V>> iterator() {
/* 1302:1302 */            return new Short2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Short2ReferenceRBTreeMap.Submap.this);
/* 1303:     */          }
/* 1304:     */          
/* 1305:1305 */          public ObjectBidirectionalIterator<Short2ReferenceMap.Entry<V>> iterator(Short2ReferenceMap.Entry<V> from) { return new Short2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Short2ReferenceRBTreeMap.Submap.this, ((Short)from.getKey()).shortValue()); }
/* 1306:     */          
/* 1307:1307 */          public Comparator<? super Short2ReferenceMap.Entry<V>> comparator() { return Short2ReferenceRBTreeMap.this.short2ReferenceEntrySet().comparator(); }
/* 1308:     */          
/* 1309:     */          public boolean contains(Object o) {
/* 1310:1310 */            if (!(o instanceof Map.Entry)) return false;
/* 1311:1311 */            Map.Entry<Short, V> e = (Map.Entry)o;
/* 1312:1312 */            Short2ReferenceRBTreeMap.Entry<V> f = Short2ReferenceRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1313:1313 */            return (f != null) && (Short2ReferenceRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1314:     */          }
/* 1315:     */          
/* 1316:     */          public boolean remove(Object o) {
/* 1317:1317 */            if (!(o instanceof Map.Entry)) return false;
/* 1318:1318 */            Map.Entry<Short, V> e = (Map.Entry)o;
/* 1319:1319 */            Short2ReferenceRBTreeMap.Entry<V> f = Short2ReferenceRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1320:1320 */            if ((f != null) && (Short2ReferenceRBTreeMap.Submap.this.in(f.key))) Short2ReferenceRBTreeMap.Submap.this.remove(f.key);
/* 1321:1321 */            return f != null;
/* 1322:     */          }
/* 1323:     */          
/* 1324:1324 */          public int size() { int c = 0;
/* 1325:1325 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1326:1326 */            return c; }
/* 1327:     */          
/* 1328:1328 */          public boolean isEmpty() { return !new Short2ReferenceRBTreeMap.Submap.SubmapIterator(Short2ReferenceRBTreeMap.Submap.this).hasNext(); }
/* 1329:1329 */          public void clear() { Short2ReferenceRBTreeMap.Submap.this.clear(); }
/* 1330:1330 */          public Short2ReferenceMap.Entry<V> first() { return Short2ReferenceRBTreeMap.Submap.this.firstEntry(); }
/* 1331:1331 */          public Short2ReferenceMap.Entry<V> last() { return Short2ReferenceRBTreeMap.Submap.this.lastEntry(); }
/* 1332:1332 */          public ObjectSortedSet<Short2ReferenceMap.Entry<V>> subSet(Short2ReferenceMap.Entry<V> from, Short2ReferenceMap.Entry<V> to) { return Short2ReferenceRBTreeMap.Submap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2ReferenceEntrySet(); }
/* 1333:1333 */          public ObjectSortedSet<Short2ReferenceMap.Entry<V>> headSet(Short2ReferenceMap.Entry<V> to) { return Short2ReferenceRBTreeMap.Submap.this.headMap((Short)to.getKey()).short2ReferenceEntrySet(); }
/* 1334:1334 */          public ObjectSortedSet<Short2ReferenceMap.Entry<V>> tailSet(Short2ReferenceMap.Entry<V> from) { return Short2ReferenceRBTreeMap.Submap.this.tailMap((Short)from.getKey()).short2ReferenceEntrySet(); }
/* 1335:     */        };
/* 1336:1336 */      return this.entries; }
/* 1337:     */    
/* 1338:1338 */    private class KeySet extends AbstractShort2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1339:1339 */      public ShortBidirectionalIterator iterator() { return new Short2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Short2ReferenceRBTreeMap.Submap.this); }
/* 1340:1340 */      public ShortBidirectionalIterator iterator(short from) { return new Short2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Short2ReferenceRBTreeMap.Submap.this, from); }
/* 1341:     */    }
/* 1342:     */    
/* 1343:1343 */    public ShortSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1344:1344 */      return this.keys;
/* 1345:     */    }
/* 1346:     */    
/* 1347:1347 */    public ReferenceCollection<V> values() { if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1348:     */          public ObjectIterator<V> iterator() {
/* 1349:1349 */            return new Short2ReferenceRBTreeMap.Submap.SubmapValueIterator(Short2ReferenceRBTreeMap.Submap.this, null);
/* 1350:     */          }
/* 1351:     */          
/* 1352:1352 */          public boolean contains(Object k) { return Short2ReferenceRBTreeMap.Submap.this.containsValue(k); }
/* 1353:     */          
/* 1354:     */          public int size() {
/* 1355:1355 */            return Short2ReferenceRBTreeMap.Submap.this.size();
/* 1356:     */          }
/* 1357:     */          
/* 1358:1358 */          public void clear() { Short2ReferenceRBTreeMap.Submap.this.clear(); }
/* 1359:     */        };
/* 1360:     */      }
/* 1361:1361 */      return this.values;
/* 1362:     */    }
/* 1363:     */    
/* 1365:1365 */    public boolean containsKey(short k) { return (in(k)) && (Short2ReferenceRBTreeMap.this.containsKey(k)); }
/* 1366:     */    
/* 1367:     */    public boolean containsValue(Object v) {
/* 1368:1368 */      Short2ReferenceRBTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1369:     */      
/* 1370:1370 */      while (i.hasNext()) {
/* 1371:1371 */        Object ev = i.nextEntry().value;
/* 1372:1372 */        if (ev == v) return true;
/* 1373:     */      }
/* 1374:1374 */      return false;
/* 1375:     */    }
/* 1376:     */    
/* 1377:     */    public V get(short k)
/* 1378:     */    {
/* 1379:1379 */      short kk = k;
/* 1380:1380 */      Short2ReferenceRBTreeMap.Entry<V> e; return (in(kk)) && ((e = Short2ReferenceRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1381:     */    }
/* 1382:     */    
/* 1383:1383 */    public V put(short k, V v) { Short2ReferenceRBTreeMap.this.modified = false;
/* 1384:1384 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1385:1385 */      V oldValue = Short2ReferenceRBTreeMap.this.put(k, v);
/* 1386:1386 */      return Short2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1387:     */    }
/* 1388:     */    
/* 1389:1389 */    public V put(Short ok, V ov) { V oldValue = put(ok.shortValue(), ov);
/* 1390:1390 */      return Short2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1391:     */    }
/* 1392:     */    
/* 1393:     */    public V remove(short k) {
/* 1394:1394 */      Short2ReferenceRBTreeMap.this.modified = false;
/* 1395:1395 */      if (!in(k)) return this.defRetValue;
/* 1396:1396 */      V oldValue = Short2ReferenceRBTreeMap.this.remove(k);
/* 1397:1397 */      return Short2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1398:     */    }
/* 1399:     */    
/* 1400:1400 */    public V remove(Object ok) { V oldValue = remove(((Short)ok).shortValue());
/* 1401:1401 */      return Short2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1402:     */    }
/* 1403:     */    
/* 1404:1404 */    public int size() { Short2ReferenceRBTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1405:1405 */      int n = 0;
/* 1406:1406 */      while (i.hasNext()) {
/* 1407:1407 */        n++;
/* 1408:1408 */        i.nextEntry();
/* 1409:     */      }
/* 1410:1410 */      return n;
/* 1411:     */    }
/* 1412:     */    
/* 1413:1413 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1414:     */    
/* 1416:1416 */    public ShortComparator comparator() { return Short2ReferenceRBTreeMap.this.actualComparator; }
/* 1417:     */    
/* 1418:     */    public Short2ReferenceSortedMap<V> headMap(short to) {
/* 1419:1419 */      if (this.top) return new Submap(Short2ReferenceRBTreeMap.this, this.from, this.bottom, to, false);
/* 1420:1420 */      return Short2ReferenceRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Short2ReferenceRBTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1421:     */    }
/* 1422:     */    
/* 1423:1423 */    public Short2ReferenceSortedMap<V> tailMap(short from) { if (this.bottom) return new Submap(Short2ReferenceRBTreeMap.this, from, false, this.to, this.top);
/* 1424:1424 */      return Short2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Short2ReferenceRBTreeMap.this, from, false, this.to, this.top) : this;
/* 1425:     */    }
/* 1426:     */    
/* 1427:1427 */    public Short2ReferenceSortedMap<V> subMap(short from, short to) { if ((this.top) && (this.bottom)) return new Submap(Short2ReferenceRBTreeMap.this, from, false, to, false);
/* 1428:1428 */      if (!this.top) to = Short2ReferenceRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1429:1429 */      if (!this.bottom) from = Short2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1430:1430 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1431:1431 */      return new Submap(Short2ReferenceRBTreeMap.this, from, false, to, false);
/* 1432:     */    }
/* 1433:     */    
/* 1436:     */    public Short2ReferenceRBTreeMap.Entry<V> firstEntry()
/* 1437:     */    {
/* 1438:1438 */      if (Short2ReferenceRBTreeMap.this.tree == null) return null;
/* 1439:     */      Short2ReferenceRBTreeMap.Entry<V> e;
/* 1440:     */      Short2ReferenceRBTreeMap.Entry<V> e;
/* 1441:1441 */      if (this.bottom) { e = Short2ReferenceRBTreeMap.this.firstEntry;
/* 1442:     */      } else {
/* 1443:1443 */        e = Short2ReferenceRBTreeMap.this.locateKey(this.from);
/* 1444:     */        
/* 1445:1445 */        if (Short2ReferenceRBTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1446:     */        }
/* 1447:     */      }
/* 1448:1448 */      if ((e == null) || ((!this.top) && (Short2ReferenceRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1449:1449 */      return e;
/* 1450:     */    }
/* 1451:     */    
/* 1454:     */    public Short2ReferenceRBTreeMap.Entry<V> lastEntry()
/* 1455:     */    {
/* 1456:1456 */      if (Short2ReferenceRBTreeMap.this.tree == null) return null;
/* 1457:     */      Short2ReferenceRBTreeMap.Entry<V> e;
/* 1458:     */      Short2ReferenceRBTreeMap.Entry<V> e;
/* 1459:1459 */      if (this.top) { e = Short2ReferenceRBTreeMap.this.lastEntry;
/* 1460:     */      } else {
/* 1461:1461 */        e = Short2ReferenceRBTreeMap.this.locateKey(this.to);
/* 1462:     */        
/* 1463:1463 */        if (Short2ReferenceRBTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1464:     */        }
/* 1465:     */      }
/* 1466:1466 */      if ((e == null) || ((!this.bottom) && (Short2ReferenceRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1467:1467 */      return e;
/* 1468:     */    }
/* 1469:     */    
/* 1470:1470 */    public short firstShortKey() { Short2ReferenceRBTreeMap.Entry<V> e = firstEntry();
/* 1471:1471 */      if (e == null) throw new NoSuchElementException();
/* 1472:1472 */      return e.key;
/* 1473:     */    }
/* 1474:     */    
/* 1475:1475 */    public short lastShortKey() { Short2ReferenceRBTreeMap.Entry<V> e = lastEntry();
/* 1476:1476 */      if (e == null) throw new NoSuchElementException();
/* 1477:1477 */      return e.key;
/* 1478:     */    }
/* 1479:     */    
/* 1480:1480 */    public Short firstKey() { Short2ReferenceRBTreeMap.Entry<V> e = firstEntry();
/* 1481:1481 */      if (e == null) throw new NoSuchElementException();
/* 1482:1482 */      return e.getKey();
/* 1483:     */    }
/* 1484:     */    
/* 1485:1485 */    public Short lastKey() { Short2ReferenceRBTreeMap.Entry<V> e = lastEntry();
/* 1486:1486 */      if (e == null) throw new NoSuchElementException();
/* 1487:1487 */      return e.getKey();
/* 1488:     */    }
/* 1489:     */    
/* 1492:     */    private class SubmapIterator
/* 1493:     */      extends Short2ReferenceRBTreeMap.TreeIterator
/* 1494:     */    {
/* 1495:     */      SubmapIterator()
/* 1496:     */      {
/* 1497:1497 */        super();
/* 1498:1498 */        this.next = Short2ReferenceRBTreeMap.Submap.this.firstEntry();
/* 1499:     */      }
/* 1500:     */      
/* 1501:1501 */      SubmapIterator(short k) { this();
/* 1502:1502 */        if (this.next != null)
/* 1503:1503 */          if ((!Short2ReferenceRBTreeMap.Submap.this.bottom) && (Short2ReferenceRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1504:1504 */          } else if ((!Short2ReferenceRBTreeMap.Submap.this.top) && (Short2ReferenceRBTreeMap.this.compare(k, (this.prev = Short2ReferenceRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1505:     */          } else {
/* 1506:1506 */            this.next = Short2ReferenceRBTreeMap.this.locateKey(k);
/* 1507:1507 */            if (Short2ReferenceRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1508:1508 */              this.prev = this.next;
/* 1509:1509 */              this.next = this.next.next();
/* 1510:     */            } else {
/* 1511:1511 */              this.prev = this.next.prev();
/* 1512:     */            }
/* 1513:     */          }
/* 1514:     */      }
/* 1515:     */      
/* 1516:1516 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1517:1517 */        if ((!Short2ReferenceRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Short2ReferenceRBTreeMap.this.compare(this.prev.key, Short2ReferenceRBTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1518:     */      }
/* 1519:     */      
/* 1520:1520 */      void updateNext() { this.next = this.next.next();
/* 1521:1521 */        if ((!Short2ReferenceRBTreeMap.Submap.this.top) && (this.next != null) && (Short2ReferenceRBTreeMap.this.compare(this.next.key, Short2ReferenceRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1522:     */      }
/* 1523:     */    }
/* 1524:     */    
/* 1525:1525 */    private class SubmapEntryIterator extends Short2ReferenceRBTreeMap<V>.Submap.SubmapIterator implements ObjectListIterator<Short2ReferenceMap.Entry<V>> { SubmapEntryIterator() { super(); }
/* 1526:     */      
/* 1527:1527 */      SubmapEntryIterator(short k) { super(k); }
/* 1528:     */      
/* 1529:1529 */      public Short2ReferenceMap.Entry<V> next() { return nextEntry(); }
/* 1530:1530 */      public Short2ReferenceMap.Entry<V> previous() { return previousEntry(); }
/* 1531:1531 */      public void set(Short2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1532:1532 */      public void add(Short2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1533:     */    }
/* 1534:     */    
/* 1539:     */    private final class SubmapKeyIterator
/* 1540:     */      extends Short2ReferenceRBTreeMap.Submap.SubmapIterator
/* 1541:     */      implements ShortListIterator
/* 1542:     */    {
/* 1543:1543 */      public SubmapKeyIterator() { super(); }
/* 1544:1544 */      public SubmapKeyIterator(short from) { super(from); }
/* 1545:1545 */      public short nextShort() { return nextEntry().key; }
/* 1546:1546 */      public short previousShort() { return previousEntry().key; }
/* 1547:1547 */      public void set(short k) { throw new UnsupportedOperationException(); }
/* 1548:1548 */      public void add(short k) { throw new UnsupportedOperationException(); }
/* 1549:1549 */      public Short next() { return Short.valueOf(nextEntry().key); }
/* 1550:1550 */      public Short previous() { return Short.valueOf(previousEntry().key); }
/* 1551:1551 */      public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1552:1552 */      public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1553:     */    }
/* 1554:     */    
/* 1558:     */    private final class SubmapValueIterator
/* 1559:     */      extends Short2ReferenceRBTreeMap<V>.Submap.SubmapIterator
/* 1560:     */      implements ObjectListIterator<V>
/* 1561:     */    {
/* 1562:1562 */      private SubmapValueIterator() { super(); }
/* 1563:1563 */      public V next() { return nextEntry().value; }
/* 1564:1564 */      public V previous() { return previousEntry().value; }
/* 1565:1565 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1566:1566 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1567:     */    }
/* 1568:     */  }
/* 1569:     */  
/* 1573:     */  public Short2ReferenceRBTreeMap<V> clone()
/* 1574:     */  {
/* 1575:     */    Short2ReferenceRBTreeMap<V> c;
/* 1576:     */    
/* 1578:     */    try
/* 1579:     */    {
/* 1580:1580 */      c = (Short2ReferenceRBTreeMap)super.clone();
/* 1581:     */    }
/* 1582:     */    catch (CloneNotSupportedException cantHappen) {
/* 1583:1583 */      throw new InternalError();
/* 1584:     */    }
/* 1585:1585 */    c.keys = null;
/* 1586:1586 */    c.values = null;
/* 1587:1587 */    c.entries = null;
/* 1588:1588 */    c.allocatePaths();
/* 1589:1589 */    if (this.count != 0)
/* 1590:     */    {
/* 1591:1591 */      Entry<V> rp = new Entry();Entry<V> rq = new Entry();
/* 1592:1592 */      Entry<V> p = rp;
/* 1593:1593 */      rp.left(this.tree);
/* 1594:1594 */      Entry<V> q = rq;
/* 1595:1595 */      rq.pred(null);
/* 1596:     */      for (;;) {
/* 1597:1597 */        if (!p.pred()) {
/* 1598:1598 */          Entry<V> e = p.left.clone();
/* 1599:1599 */          e.pred(q.left);
/* 1600:1600 */          e.succ(q);
/* 1601:1601 */          q.left(e);
/* 1602:1602 */          p = p.left;
/* 1603:1603 */          q = q.left;
/* 1604:     */        }
/* 1605:     */        else {
/* 1606:1606 */          while (p.succ()) {
/* 1607:1607 */            p = p.right;
/* 1608:1608 */            if (p == null) {
/* 1609:1609 */              q.right = null;
/* 1610:1610 */              c.tree = rq.left;
/* 1611:1611 */              c.firstEntry = c.tree;
/* 1612:1612 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1613:1613 */              c.lastEntry = c.tree;
/* 1614:1614 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1615:1615 */              return c;
/* 1616:     */            }
/* 1617:1617 */            q = q.right;
/* 1618:     */          }
/* 1619:1619 */          p = p.right;
/* 1620:1620 */          q = q.right;
/* 1621:     */        }
/* 1622:1622 */        if (!p.succ()) {
/* 1623:1623 */          Entry<V> e = p.right.clone();
/* 1624:1624 */          e.succ(q.right);
/* 1625:1625 */          e.pred(q);
/* 1626:1626 */          q.right(e);
/* 1627:     */        }
/* 1628:     */      }
/* 1629:     */    }
/* 1630:1630 */    return c;
/* 1631:     */  }
/* 1632:     */  
/* 1633:1633 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1634:1634 */    Short2ReferenceRBTreeMap<V>.EntryIterator i = new EntryIterator();
/* 1635:     */    
/* 1636:1636 */    s.defaultWriteObject();
/* 1637:1637 */    while (n-- != 0) {
/* 1638:1638 */      Entry<V> e = i.nextEntry();
/* 1639:1639 */      s.writeShort(e.key);
/* 1640:1640 */      s.writeObject(e.value);
/* 1641:     */    }
/* 1642:     */  }
/* 1643:     */  
/* 1649:     */  private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/* 1650:     */    throws IOException, ClassNotFoundException
/* 1651:     */  {
/* 1652:1652 */    if (n == 1) {
/* 1653:1653 */      Entry<V> top = new Entry(s.readShort(), s.readObject());
/* 1654:1654 */      top.pred(pred);
/* 1655:1655 */      top.succ(succ);
/* 1656:1656 */      top.black(true);
/* 1657:1657 */      return top;
/* 1658:     */    }
/* 1659:1659 */    if (n == 2)
/* 1660:     */    {
/* 1662:1662 */      Entry<V> top = new Entry(s.readShort(), s.readObject());
/* 1663:1663 */      top.black(true);
/* 1664:1664 */      top.right(new Entry(s.readShort(), s.readObject()));
/* 1665:1665 */      top.right.pred(top);
/* 1666:1666 */      top.pred(pred);
/* 1667:1667 */      top.right.succ(succ);
/* 1668:1668 */      return top;
/* 1669:     */    }
/* 1670:     */    
/* 1671:1671 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1672:1672 */    Entry<V> top = new Entry();
/* 1673:1673 */    top.left(readTree(s, leftN, pred, top));
/* 1674:1674 */    top.key = s.readShort();
/* 1675:1675 */    top.value = s.readObject();
/* 1676:1676 */    top.black(true);
/* 1677:1677 */    top.right(readTree(s, rightN, top, succ));
/* 1678:1678 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1679:1679 */    return top;
/* 1680:     */  }
/* 1681:     */  
/* 1682:1682 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1683:     */    
/* 1685:1685 */    setActualComparator();
/* 1686:1686 */    allocatePaths();
/* 1687:1687 */    if (this.count != 0) {
/* 1688:1688 */      this.tree = readTree(s, this.count, null, null);
/* 1689:     */      
/* 1690:1690 */      Entry<V> e = this.tree;
/* 1691:1691 */      while (e.left() != null) e = e.left();
/* 1692:1692 */      this.firstEntry = e;
/* 1693:1693 */      e = this.tree;
/* 1694:1694 */      while (e.right() != null) e = e.right();
/* 1695:1695 */      this.lastEntry = e;
/* 1696:     */    }
/* 1697:     */  }
/* 1698:     */  
/* 1699:     */  private void checkNodePath() {}
/* 1700:     */  
/* 1701:1701 */  private int checkTree(Entry<V> e, int d, int D) { return 0; }
/* 1702:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ReferenceRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */