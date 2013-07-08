/*    1:     */package it.unimi.dsi.fastutil.shorts;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.booleans.AbstractBooleanCollection;
/*    4:     */import it.unimi.dsi.fastutil.booleans.BooleanCollection;
/*    5:     */import it.unimi.dsi.fastutil.booleans.BooleanIterator;
/*    6:     */import it.unimi.dsi.fastutil.booleans.BooleanListIterator;
/*    7:     */import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*    8:     */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*    9:     */import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*   10:     */import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*   11:     */import java.io.IOException;
/*   12:     */import java.io.ObjectInputStream;
/*   13:     */import java.io.ObjectOutputStream;
/*   14:     */import java.io.Serializable;
/*   15:     */import java.util.Comparator;
/*   16:     */import java.util.Iterator;
/*   17:     */import java.util.Map;
/*   18:     */import java.util.Map.Entry;
/*   19:     */import java.util.NoSuchElementException;
/*   20:     */import java.util.SortedMap;
/*   21:     */
/*   73:     */public class Short2BooleanRBTreeMap
/*   74:     */  extends AbstractShort2BooleanSortedMap
/*   75:     */  implements Serializable, Cloneable
/*   76:     */{
/*   77:     */  protected transient Entry tree;
/*   78:     */  protected int count;
/*   79:     */  protected transient Entry firstEntry;
/*   80:     */  protected transient Entry lastEntry;
/*   81:     */  protected volatile transient ObjectSortedSet<Short2BooleanMap.Entry> entries;
/*   82:     */  protected volatile transient ShortSortedSet keys;
/*   83:     */  protected volatile transient BooleanCollection values;
/*   84:     */  protected transient boolean modified;
/*   85:     */  protected Comparator<? super Short> storedComparator;
/*   86:     */  protected transient ShortComparator actualComparator;
/*   87:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   88:     */  private static final boolean ASSERTS = false;
/*   89:     */  private transient boolean[] dirPath;
/*   90:     */  private transient Entry[] nodePath;
/*   91:     */  
/*   92:     */  public Short2BooleanRBTreeMap()
/*   93:     */  {
/*   94:  94 */    allocatePaths();
/*   95:     */    
/*   99:  99 */    this.tree = null;
/*  100: 100 */    this.count = 0;
/*  101:     */  }
/*  102:     */  
/*  114:     */  private void setActualComparator()
/*  115:     */  {
/*  116: 116 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof ShortComparator))) this.actualComparator = ((ShortComparator)this.storedComparator); else {
/*  117: 117 */      this.actualComparator = new ShortComparator() {
/*  118:     */        public int compare(short k1, short k2) {
/*  119: 119 */          return Short2BooleanRBTreeMap.this.storedComparator.compare(Short.valueOf(k1), Short.valueOf(k2));
/*  120:     */        }
/*  121:     */        
/*  122: 122 */        public int compare(Short ok1, Short ok2) { return Short2BooleanRBTreeMap.this.storedComparator.compare(ok1, ok2); }
/*  123:     */      };
/*  124:     */    }
/*  125:     */  }
/*  126:     */  
/*  133:     */  public Short2BooleanRBTreeMap(Comparator<? super Short> c)
/*  134:     */  {
/*  135: 135 */    this();
/*  136: 136 */    this.storedComparator = c;
/*  137: 137 */    setActualComparator();
/*  138:     */  }
/*  139:     */  
/*  145:     */  public Short2BooleanRBTreeMap(Map<? extends Short, ? extends Boolean> m)
/*  146:     */  {
/*  147: 147 */    this();
/*  148: 148 */    putAll(m);
/*  149:     */  }
/*  150:     */  
/*  155:     */  public Short2BooleanRBTreeMap(SortedMap<Short, Boolean> m)
/*  156:     */  {
/*  157: 157 */    this(m.comparator());
/*  158: 158 */    putAll(m);
/*  159:     */  }
/*  160:     */  
/*  165:     */  public Short2BooleanRBTreeMap(Short2BooleanMap m)
/*  166:     */  {
/*  167: 167 */    this();
/*  168: 168 */    putAll(m);
/*  169:     */  }
/*  170:     */  
/*  175:     */  public Short2BooleanRBTreeMap(Short2BooleanSortedMap m)
/*  176:     */  {
/*  177: 177 */    this(m.comparator());
/*  178: 178 */    putAll(m);
/*  179:     */  }
/*  180:     */  
/*  188:     */  public Short2BooleanRBTreeMap(short[] k, boolean[] v, Comparator<? super Short> c)
/*  189:     */  {
/*  190: 190 */    this(c);
/*  191: 191 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  192: 192 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  193:     */    }
/*  194:     */  }
/*  195:     */  
/*  201:     */  public Short2BooleanRBTreeMap(short[] k, boolean[] v)
/*  202:     */  {
/*  203: 203 */    this(k, v, null);
/*  204:     */  }
/*  205:     */  
/*  228:     */  final int compare(short k1, short k2)
/*  229:     */  {
/*  230: 230 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  231:     */  }
/*  232:     */  
/*  240:     */  final Entry findKey(short k)
/*  241:     */  {
/*  242: 242 */    Entry e = this.tree;
/*  243:     */    
/*  244:     */    int cmp;
/*  245: 245 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) { e = cmp < 0 ? e.left() : e.right();
/*  246:     */    }
/*  247: 247 */    return e;
/*  248:     */  }
/*  249:     */  
/*  256:     */  final Entry locateKey(short k)
/*  257:     */  {
/*  258: 258 */    Entry e = this.tree;Entry last = this.tree;
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
/*  282:     */  public boolean put(short k, boolean v)
/*  283:     */  {
/*  284: 284 */    this.modified = false;
/*  285: 285 */    int maxDepth = 0;
/*  286:     */    
/*  287: 287 */    if (this.tree == null) {
/*  288: 288 */      this.count += 1;
/*  289: 289 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  290:     */    }
/*  291:     */    else {
/*  292: 292 */      Entry p = this.tree;
/*  293: 293 */      int i = 0;
/*  294:     */      for (;;) {
/*  295:     */        int cmp;
/*  296: 296 */        if ((cmp = compare(k, p.key)) == 0) {
/*  297: 297 */          boolean oldValue = p.value;
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
/*  309: 309 */            Entry e = new Entry(k, v);
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
/*  326: 326 */            Entry e = new Entry(k, v);
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
/*  341:     */      Entry e;
/*  342: 342 */      this.modified = true;
/*  343: 343 */      maxDepth = i--;
/*  344:     */      
/*  345: 345 */      while ((i > 0) && (!this.nodePath[i].black())) {
/*  346: 346 */        if (this.dirPath[(i - 1)] == 0) {
/*  347: 347 */          Entry y = this.nodePath[(i - 1)].right;
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
/*  360: 360 */              Entry x = this.nodePath[i];
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
/*  372: 372 */            Entry x = this.nodePath[(i - 1)];
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
/*  392: 392 */          Entry y = this.nodePath[(i - 1)].left;
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
/*  405: 405 */              Entry x = this.nodePath[i];
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
/*  418: 418 */            Entry x = this.nodePath[(i - 1)];
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
/*  454:     */  public boolean remove(short k)
/*  455:     */  {
/*  456: 456 */    this.modified = false;
/*  457:     */    
/*  458: 458 */    if (this.tree == null) { return this.defRetValue;
/*  459:     */    }
/*  460: 460 */    Entry p = this.tree;
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
/*  511: 511 */      Entry r = p.right;
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
/*  532:     */        Entry s;
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
/*  574: 574 */          Entry x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  575:     */          
/*  576: 576 */          if (!x.black()) {
/*  577: 577 */            x.black(true);
/*  578: 578 */            break;
/*  579:     */          }
/*  580:     */        }
/*  581:     */        
/*  582: 582 */        if (this.dirPath[(i - 1)] == 0) {
/*  583: 583 */          Entry w = this.nodePath[(i - 1)].right;
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
/*  612: 612 */              Entry y = w.left;
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
/*  647: 647 */          Entry w = this.nodePath[(i - 1)].left;
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
/*  676: 676 */              Entry y = w.right;
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
/*  728:     */  public Boolean put(Short ok, Boolean ov)
/*  729:     */  {
/*  730: 730 */    boolean oldValue = put(ok.shortValue(), ov.booleanValue());
/*  731: 731 */    return this.modified ? null : Boolean.valueOf(oldValue);
/*  732:     */  }
/*  733:     */  
/*  735:     */  public Boolean remove(Object ok)
/*  736:     */  {
/*  737: 737 */    boolean oldValue = remove(((Short)ok).shortValue());
/*  738: 738 */    return this.modified ? Boolean.valueOf(oldValue) : null;
/*  739:     */  }
/*  740:     */  
/*  742:     */  public boolean containsValue(boolean v)
/*  743:     */  {
/*  744: 744 */    ValueIterator i = new ValueIterator(null);
/*  745:     */    
/*  747: 747 */    int j = this.count;
/*  748: 748 */    while (j-- != 0) {
/*  749: 749 */      boolean ev = i.nextBoolean();
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
/*  768:     */  private static final class Entry
/*  769:     */    implements Cloneable, Short2BooleanMap.Entry
/*  770:     */  {
/*  771:     */    private static final int BLACK_MASK = 1;
/*  772:     */    
/*  774:     */    private static final int SUCC_MASK = -2147483648;
/*  775:     */    
/*  777:     */    private static final int PRED_MASK = 1073741824;
/*  778:     */    
/*  780:     */    short key;
/*  781:     */    
/*  783:     */    boolean value;
/*  784:     */    
/*  786:     */    Entry left;
/*  787:     */    
/*  789:     */    Entry right;
/*  790:     */    
/*  792:     */    int info;
/*  793:     */    
/*  795:     */    Entry() {}
/*  796:     */    
/*  798:     */    Entry(short k, boolean v)
/*  799:     */    {
/*  800: 800 */      this.key = k;
/*  801: 801 */      this.value = v;
/*  802: 802 */      this.info = -1073741824;
/*  803:     */    }
/*  804:     */    
/*  809:     */    Entry left()
/*  810:     */    {
/*  811: 811 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  812:     */    }
/*  813:     */    
/*  818:     */    Entry right()
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
/*  855:     */    void pred(Entry pred)
/*  856:     */    {
/*  857: 857 */      this.info |= 1073741824;
/*  858: 858 */      this.left = pred;
/*  859:     */    }
/*  860:     */    
/*  863:     */    void succ(Entry succ)
/*  864:     */    {
/*  865: 865 */      this.info |= -2147483648;
/*  866: 866 */      this.right = succ;
/*  867:     */    }
/*  868:     */    
/*  871:     */    void left(Entry left)
/*  872:     */    {
/*  873: 873 */      this.info &= -1073741825;
/*  874: 874 */      this.left = left;
/*  875:     */    }
/*  876:     */    
/*  879:     */    void right(Entry right)
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
/*  905:     */    Entry next()
/*  906:     */    {
/*  907: 907 */      Entry next = this.right;
/*  908: 908 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  909: 909 */      return next;
/*  910:     */    }
/*  911:     */    
/*  916:     */    Entry prev()
/*  917:     */    {
/*  918: 918 */      Entry prev = this.left;
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
/*  932:     */    public Boolean getValue()
/*  933:     */    {
/*  934: 934 */      return Boolean.valueOf(this.value);
/*  935:     */    }
/*  936:     */    
/*  937:     */    public boolean getBooleanValue()
/*  938:     */    {
/*  939: 939 */      return this.value;
/*  940:     */    }
/*  941:     */    
/*  942:     */    public boolean setValue(boolean value)
/*  943:     */    {
/*  944: 944 */      boolean oldValue = this.value;
/*  945: 945 */      this.value = value;
/*  946: 946 */      return oldValue;
/*  947:     */    }
/*  948:     */    
/*  950:     */    public Boolean setValue(Boolean value)
/*  951:     */    {
/*  952: 952 */      return Boolean.valueOf(setValue(value.booleanValue()));
/*  953:     */    }
/*  954:     */    
/*  956:     */    public Entry clone()
/*  957:     */    {
/*  958:     */      Entry c;
/*  959:     */      try
/*  960:     */      {
/*  961: 961 */        c = (Entry)super.clone();
/*  962:     */      }
/*  963:     */      catch (CloneNotSupportedException cantHappen) {
/*  964: 964 */        throw new InternalError();
/*  965:     */      }
/*  966:     */      
/*  967: 967 */      c.key = this.key;
/*  968: 968 */      c.value = this.value;
/*  969: 969 */      c.info = this.info;
/*  970:     */      
/*  971: 971 */      return c;
/*  972:     */    }
/*  973:     */    
/*  974:     */    public boolean equals(Object o)
/*  975:     */    {
/*  976: 976 */      if (!(o instanceof Map.Entry)) return false;
/*  977: 977 */      Map.Entry<Short, Boolean> e = (Map.Entry)o;
/*  978:     */      
/*  979: 979 */      return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == ((Boolean)e.getValue()).booleanValue());
/*  980:     */    }
/*  981:     */    
/*  982:     */    public int hashCode() {
/*  983: 983 */      return this.key ^ (this.value ? 1231 : 1237);
/*  984:     */    }
/*  985:     */    
/*  986:     */    public String toString()
/*  987:     */    {
/*  988: 988 */      return this.key + "=>" + this.value;
/*  989:     */    }
/*  990:     */  }
/*  991:     */  
/* 1023:     */  public boolean containsKey(short k)
/* 1024:     */  {
/* 1025:1025 */    return findKey(k) != null;
/* 1026:     */  }
/* 1027:     */  
/* 1028:     */  public int size() {
/* 1029:1029 */    return this.count;
/* 1030:     */  }
/* 1031:     */  
/* 1032:     */  public boolean isEmpty() {
/* 1033:1033 */    return this.count == 0;
/* 1034:     */  }
/* 1035:     */  
/* 1037:     */  public boolean get(short k)
/* 1038:     */  {
/* 1039:1039 */    Entry e = findKey(k);
/* 1040:1040 */    return e == null ? this.defRetValue : e.value;
/* 1041:     */  }
/* 1042:     */  
/* 1043:1043 */  public short firstShortKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1044:1044 */    return this.firstEntry.key;
/* 1045:     */  }
/* 1046:     */  
/* 1047:1047 */  public short lastShortKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1048:1048 */    return this.lastEntry.key;
/* 1049:     */  }
/* 1050:     */  
/* 1053:     */  private class TreeIterator
/* 1054:     */  {
/* 1055:     */    Short2BooleanRBTreeMap.Entry prev;
/* 1056:     */    
/* 1058:     */    Short2BooleanRBTreeMap.Entry next;
/* 1059:     */    
/* 1060:     */    Short2BooleanRBTreeMap.Entry curr;
/* 1061:     */    
/* 1062:1062 */    int index = 0;
/* 1063:     */    
/* 1064:1064 */    TreeIterator() { this.next = Short2BooleanRBTreeMap.this.firstEntry; }
/* 1065:     */    
/* 1066:     */    TreeIterator(short k) {
/* 1067:1067 */      if ((this.next = Short2BooleanRBTreeMap.this.locateKey(k)) != null)
/* 1068:1068 */        if (Short2BooleanRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1069:1069 */          this.prev = this.next;
/* 1070:1070 */          this.next = this.next.next();
/* 1071:     */        } else {
/* 1072:1072 */          this.prev = this.next.prev();
/* 1073:     */        } }
/* 1074:     */    
/* 1075:1075 */    public boolean hasNext() { return this.next != null; }
/* 1076:1076 */    public boolean hasPrevious() { return this.prev != null; }
/* 1077:     */    
/* 1078:1078 */    void updateNext() { this.next = this.next.next(); }
/* 1079:     */    
/* 1080:     */    Short2BooleanRBTreeMap.Entry nextEntry() {
/* 1081:1081 */      if (!hasNext()) throw new NoSuchElementException();
/* 1082:1082 */      this.curr = (this.prev = this.next);
/* 1083:1083 */      this.index += 1;
/* 1084:1084 */      updateNext();
/* 1085:1085 */      return this.curr;
/* 1086:     */    }
/* 1087:     */    
/* 1088:1088 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1089:     */    
/* 1090:     */    Short2BooleanRBTreeMap.Entry previousEntry() {
/* 1091:1091 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1092:1092 */      this.curr = (this.next = this.prev);
/* 1093:1093 */      this.index -= 1;
/* 1094:1094 */      updatePrevious();
/* 1095:1095 */      return this.curr;
/* 1096:     */    }
/* 1097:     */    
/* 1098:1098 */    public int nextIndex() { return this.index; }
/* 1099:     */    
/* 1101:1101 */    public int previousIndex() { return this.index - 1; }
/* 1102:     */    
/* 1103:     */    public void remove() {
/* 1104:1104 */      if (this.curr == null) { throw new IllegalStateException();
/* 1105:     */      }
/* 1106:     */      
/* 1107:1107 */      if (this.curr == this.prev) this.index -= 1;
/* 1108:1108 */      this.next = (this.prev = this.curr);
/* 1109:1109 */      updatePrevious();
/* 1110:1110 */      updateNext();
/* 1111:1111 */      Short2BooleanRBTreeMap.this.remove(this.curr.key);
/* 1112:1112 */      this.curr = null;
/* 1113:     */    }
/* 1114:     */    
/* 1115:1115 */    public int skip(int n) { int i = n;
/* 1116:1116 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 1117:1117 */      return n - i - 1;
/* 1118:     */    }
/* 1119:     */    
/* 1120:1120 */    public int back(int n) { int i = n;
/* 1121:1121 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1122:1122 */      return n - i - 1;
/* 1123:     */    }
/* 1124:     */  }
/* 1125:     */  
/* 1126:     */  private class EntryIterator
/* 1127:     */    extends Short2BooleanRBTreeMap.TreeIterator
/* 1128:     */    implements ObjectListIterator<Short2BooleanMap.Entry>
/* 1129:     */  {
/* 1130:1130 */    EntryIterator() { super(); }
/* 1131:     */    
/* 1132:1132 */    EntryIterator(short k) { super(k); }
/* 1133:     */    
/* 1134:1134 */    public Short2BooleanMap.Entry next() { return nextEntry(); }
/* 1135:1135 */    public Short2BooleanMap.Entry previous() { return previousEntry(); }
/* 1136:1136 */    public void set(Short2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1137:1137 */    public void add(Short2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1138:     */  }
/* 1139:     */  
/* 1140:1140 */  public ObjectSortedSet<Short2BooleanMap.Entry> short2BooleanEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1141:1141 */        final Comparator<? super Short2BooleanMap.Entry> comparator = new Comparator() {
/* 1142:     */          public int compare(Short2BooleanMap.Entry x, Short2BooleanMap.Entry y) {
/* 1143:1143 */            return Short2BooleanRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1144:     */          }
/* 1145:     */        };
/* 1146:     */        
/* 1147:1147 */        public Comparator<? super Short2BooleanMap.Entry> comparator() { return this.comparator; }
/* 1148:     */        
/* 1149:     */        public ObjectBidirectionalIterator<Short2BooleanMap.Entry> iterator() {
/* 1150:1150 */          return new Short2BooleanRBTreeMap.EntryIterator(Short2BooleanRBTreeMap.this);
/* 1151:     */        }
/* 1152:     */        
/* 1153:1153 */        public ObjectBidirectionalIterator<Short2BooleanMap.Entry> iterator(Short2BooleanMap.Entry from) { return new Short2BooleanRBTreeMap.EntryIterator(Short2BooleanRBTreeMap.this, ((Short)from.getKey()).shortValue()); }
/* 1154:     */        
/* 1155:     */        public boolean contains(Object o)
/* 1156:     */        {
/* 1157:1157 */          if (!(o instanceof Map.Entry)) return false;
/* 1158:1158 */          Map.Entry<Short, Boolean> e = (Map.Entry)o;
/* 1159:1159 */          Short2BooleanRBTreeMap.Entry f = Short2BooleanRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1160:1160 */          return e.equals(f);
/* 1161:     */        }
/* 1162:     */        
/* 1163:     */        public boolean remove(Object o) {
/* 1164:1164 */          if (!(o instanceof Map.Entry)) return false;
/* 1165:1165 */          Map.Entry<Short, Boolean> e = (Map.Entry)o;
/* 1166:1166 */          Short2BooleanRBTreeMap.Entry f = Short2BooleanRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1167:1167 */          if (f != null) Short2BooleanRBTreeMap.this.remove(f.key);
/* 1168:1168 */          return f != null; }
/* 1169:     */        
/* 1170:1170 */        public int size() { return Short2BooleanRBTreeMap.this.count; }
/* 1171:1171 */        public void clear() { Short2BooleanRBTreeMap.this.clear(); }
/* 1172:1172 */        public Short2BooleanMap.Entry first() { return Short2BooleanRBTreeMap.this.firstEntry; }
/* 1173:1173 */        public Short2BooleanMap.Entry last() { return Short2BooleanRBTreeMap.this.lastEntry; }
/* 1174:1174 */        public ObjectSortedSet<Short2BooleanMap.Entry> subSet(Short2BooleanMap.Entry from, Short2BooleanMap.Entry to) { return Short2BooleanRBTreeMap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2BooleanEntrySet(); }
/* 1175:1175 */        public ObjectSortedSet<Short2BooleanMap.Entry> headSet(Short2BooleanMap.Entry to) { return Short2BooleanRBTreeMap.this.headMap((Short)to.getKey()).short2BooleanEntrySet(); }
/* 1176:1176 */        public ObjectSortedSet<Short2BooleanMap.Entry> tailSet(Short2BooleanMap.Entry from) { return Short2BooleanRBTreeMap.this.tailMap((Short)from.getKey()).short2BooleanEntrySet(); }
/* 1177:     */      };
/* 1178:1178 */    return this.entries;
/* 1179:     */  }
/* 1180:     */  
/* 1183:     */  private final class KeyIterator
/* 1184:     */    extends Short2BooleanRBTreeMap.TreeIterator
/* 1185:     */    implements ShortListIterator
/* 1186:     */  {
/* 1187:1187 */    public KeyIterator() { super(); }
/* 1188:1188 */    public KeyIterator(short k) { super(k); }
/* 1189:1189 */    public short nextShort() { return nextEntry().key; }
/* 1190:1190 */    public short previousShort() { return previousEntry().key; }
/* 1191:1191 */    public void set(short k) { throw new UnsupportedOperationException(); }
/* 1192:1192 */    public void add(short k) { throw new UnsupportedOperationException(); }
/* 1193:1193 */    public Short next() { return Short.valueOf(nextEntry().key); }
/* 1194:1194 */    public Short previous() { return Short.valueOf(previousEntry().key); }
/* 1195:1195 */    public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1196:1196 */    public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1197:     */  }
/* 1198:     */  
/* 1199:1199 */  private class KeySet extends AbstractShort2BooleanSortedMap.KeySet { private KeySet() { super(); }
/* 1200:1200 */    public ShortBidirectionalIterator iterator() { return new Short2BooleanRBTreeMap.KeyIterator(Short2BooleanRBTreeMap.this); }
/* 1201:1201 */    public ShortBidirectionalIterator iterator(short from) { return new Short2BooleanRBTreeMap.KeyIterator(Short2BooleanRBTreeMap.this, from); }
/* 1202:     */  }
/* 1203:     */  
/* 1210:     */  public ShortSortedSet keySet()
/* 1211:     */  {
/* 1212:1212 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1213:1213 */    return this.keys;
/* 1214:     */  }
/* 1215:     */  
/* 1217:     */  private final class ValueIterator
/* 1218:     */    extends Short2BooleanRBTreeMap.TreeIterator
/* 1219:     */    implements BooleanListIterator
/* 1220:     */  {
/* 1221:1221 */    private ValueIterator() { super(); }
/* 1222:1222 */    public boolean nextBoolean() { return nextEntry().value; }
/* 1223:1223 */    public boolean previousBoolean() { return previousEntry().value; }
/* 1224:1224 */    public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1225:1225 */    public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1226:1226 */    public Boolean next() { return Boolean.valueOf(nextEntry().value); }
/* 1227:1227 */    public Boolean previous() { return Boolean.valueOf(previousEntry().value); }
/* 1228:1228 */    public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1229:1229 */    public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1230:     */  }
/* 1231:     */  
/* 1238:     */  public BooleanCollection values()
/* 1239:     */  {
/* 1240:1240 */    if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1241:     */        public BooleanIterator iterator() {
/* 1242:1242 */          return new Short2BooleanRBTreeMap.ValueIterator(Short2BooleanRBTreeMap.this, null);
/* 1243:     */        }
/* 1244:     */        
/* 1245:1245 */        public boolean contains(boolean k) { return Short2BooleanRBTreeMap.this.containsValue(k); }
/* 1246:     */        
/* 1247:     */        public int size() {
/* 1248:1248 */          return Short2BooleanRBTreeMap.this.count;
/* 1249:     */        }
/* 1250:     */        
/* 1251:1251 */        public void clear() { Short2BooleanRBTreeMap.this.clear(); }
/* 1252:     */      };
/* 1253:     */    }
/* 1254:1254 */    return this.values;
/* 1255:     */  }
/* 1256:     */  
/* 1257:1257 */  public ShortComparator comparator() { return this.actualComparator; }
/* 1258:     */  
/* 1259:     */  public Short2BooleanSortedMap headMap(short to) {
/* 1260:1260 */    return new Submap((short)0, true, to, false);
/* 1261:     */  }
/* 1262:     */  
/* 1263:1263 */  public Short2BooleanSortedMap tailMap(short from) { return new Submap(from, false, (short)0, true); }
/* 1264:     */  
/* 1265:     */  public Short2BooleanSortedMap subMap(short from, short to) {
/* 1266:1266 */    return new Submap(from, false, to, false);
/* 1267:     */  }
/* 1268:     */  
/* 1272:     */  private final class Submap
/* 1273:     */    extends AbstractShort2BooleanSortedMap
/* 1274:     */    implements Serializable
/* 1275:     */  {
/* 1276:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1277:     */    
/* 1280:     */    short from;
/* 1281:     */    
/* 1283:     */    short to;
/* 1284:     */    
/* 1286:     */    boolean bottom;
/* 1287:     */    
/* 1289:     */    boolean top;
/* 1290:     */    
/* 1292:     */    protected volatile transient ObjectSortedSet<Short2BooleanMap.Entry> entries;
/* 1293:     */    
/* 1295:     */    protected volatile transient ShortSortedSet keys;
/* 1296:     */    
/* 1298:     */    protected volatile transient BooleanCollection values;
/* 1299:     */    
/* 1302:     */    public Submap(short from, boolean bottom, short to, boolean top)
/* 1303:     */    {
/* 1304:1304 */      if ((!bottom) && (!top) && (Short2BooleanRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1305:1305 */      this.from = from;
/* 1306:1306 */      this.bottom = bottom;
/* 1307:1307 */      this.to = to;
/* 1308:1308 */      this.top = top;
/* 1309:1309 */      this.defRetValue = Short2BooleanRBTreeMap.this.defRetValue;
/* 1310:     */    }
/* 1311:     */    
/* 1312:1312 */    public void clear() { SubmapIterator i = new SubmapIterator();
/* 1313:1313 */      while (i.hasNext()) {
/* 1314:1314 */        i.nextEntry();
/* 1315:1315 */        i.remove();
/* 1316:     */      }
/* 1317:     */    }
/* 1318:     */    
/* 1321:     */    final boolean in(short k)
/* 1322:     */    {
/* 1323:1323 */      return ((this.bottom) || (Short2BooleanRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Short2BooleanRBTreeMap.this.compare(k, this.to) < 0));
/* 1324:     */    }
/* 1325:     */    
/* 1326:     */    public ObjectSortedSet<Short2BooleanMap.Entry> short2BooleanEntrySet() {
/* 1327:1327 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1328:     */          public ObjectBidirectionalIterator<Short2BooleanMap.Entry> iterator() {
/* 1329:1329 */            return new Short2BooleanRBTreeMap.Submap.SubmapEntryIterator(Short2BooleanRBTreeMap.Submap.this);
/* 1330:     */          }
/* 1331:     */          
/* 1332:1332 */          public ObjectBidirectionalIterator<Short2BooleanMap.Entry> iterator(Short2BooleanMap.Entry from) { return new Short2BooleanRBTreeMap.Submap.SubmapEntryIterator(Short2BooleanRBTreeMap.Submap.this, ((Short)from.getKey()).shortValue()); }
/* 1333:     */          
/* 1334:1334 */          public Comparator<? super Short2BooleanMap.Entry> comparator() { return Short2BooleanRBTreeMap.this.short2BooleanEntrySet().comparator(); }
/* 1335:     */          
/* 1336:     */          public boolean contains(Object o) {
/* 1337:1337 */            if (!(o instanceof Map.Entry)) return false;
/* 1338:1338 */            Map.Entry<Short, Boolean> e = (Map.Entry)o;
/* 1339:1339 */            Short2BooleanRBTreeMap.Entry f = Short2BooleanRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1340:1340 */            return (f != null) && (Short2BooleanRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1341:     */          }
/* 1342:     */          
/* 1343:     */          public boolean remove(Object o) {
/* 1344:1344 */            if (!(o instanceof Map.Entry)) return false;
/* 1345:1345 */            Map.Entry<Short, Boolean> e = (Map.Entry)o;
/* 1346:1346 */            Short2BooleanRBTreeMap.Entry f = Short2BooleanRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1347:1347 */            if ((f != null) && (Short2BooleanRBTreeMap.Submap.this.in(f.key))) Short2BooleanRBTreeMap.Submap.this.remove(f.key);
/* 1348:1348 */            return f != null;
/* 1349:     */          }
/* 1350:     */          
/* 1351:1351 */          public int size() { int c = 0;
/* 1352:1352 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1353:1353 */            return c; }
/* 1354:     */          
/* 1355:1355 */          public boolean isEmpty() { return !new Short2BooleanRBTreeMap.Submap.SubmapIterator(Short2BooleanRBTreeMap.Submap.this).hasNext(); }
/* 1356:1356 */          public void clear() { Short2BooleanRBTreeMap.Submap.this.clear(); }
/* 1357:1357 */          public Short2BooleanMap.Entry first() { return Short2BooleanRBTreeMap.Submap.this.firstEntry(); }
/* 1358:1358 */          public Short2BooleanMap.Entry last() { return Short2BooleanRBTreeMap.Submap.this.lastEntry(); }
/* 1359:1359 */          public ObjectSortedSet<Short2BooleanMap.Entry> subSet(Short2BooleanMap.Entry from, Short2BooleanMap.Entry to) { return Short2BooleanRBTreeMap.Submap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2BooleanEntrySet(); }
/* 1360:1360 */          public ObjectSortedSet<Short2BooleanMap.Entry> headSet(Short2BooleanMap.Entry to) { return Short2BooleanRBTreeMap.Submap.this.headMap((Short)to.getKey()).short2BooleanEntrySet(); }
/* 1361:1361 */          public ObjectSortedSet<Short2BooleanMap.Entry> tailSet(Short2BooleanMap.Entry from) { return Short2BooleanRBTreeMap.Submap.this.tailMap((Short)from.getKey()).short2BooleanEntrySet(); }
/* 1362:     */        };
/* 1363:1363 */      return this.entries; }
/* 1364:     */    
/* 1365:1365 */    private class KeySet extends AbstractShort2BooleanSortedMap.KeySet { private KeySet() { super(); }
/* 1366:1366 */      public ShortBidirectionalIterator iterator() { return new Short2BooleanRBTreeMap.Submap.SubmapKeyIterator(Short2BooleanRBTreeMap.Submap.this); }
/* 1367:1367 */      public ShortBidirectionalIterator iterator(short from) { return new Short2BooleanRBTreeMap.Submap.SubmapKeyIterator(Short2BooleanRBTreeMap.Submap.this, from); }
/* 1368:     */    }
/* 1369:     */    
/* 1370:1370 */    public ShortSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1371:1371 */      return this.keys;
/* 1372:     */    }
/* 1373:     */    
/* 1374:1374 */    public BooleanCollection values() { if (this.values == null) { this.values = new AbstractBooleanCollection() {
/* 1375:     */          public BooleanIterator iterator() {
/* 1376:1376 */            return new Short2BooleanRBTreeMap.Submap.SubmapValueIterator(Short2BooleanRBTreeMap.Submap.this, null);
/* 1377:     */          }
/* 1378:     */          
/* 1379:1379 */          public boolean contains(boolean k) { return Short2BooleanRBTreeMap.Submap.this.containsValue(k); }
/* 1380:     */          
/* 1381:     */          public int size() {
/* 1382:1382 */            return Short2BooleanRBTreeMap.Submap.this.size();
/* 1383:     */          }
/* 1384:     */          
/* 1385:1385 */          public void clear() { Short2BooleanRBTreeMap.Submap.this.clear(); }
/* 1386:     */        };
/* 1387:     */      }
/* 1388:1388 */      return this.values;
/* 1389:     */    }
/* 1390:     */    
/* 1392:1392 */    public boolean containsKey(short k) { return (in(k)) && (Short2BooleanRBTreeMap.this.containsKey(k)); }
/* 1393:     */    
/* 1394:     */    public boolean containsValue(boolean v) {
/* 1395:1395 */      SubmapIterator i = new SubmapIterator();
/* 1396:     */      
/* 1397:1397 */      while (i.hasNext()) {
/* 1398:1398 */        boolean ev = i.nextEntry().value;
/* 1399:1399 */        if (ev == v) return true;
/* 1400:     */      }
/* 1401:1401 */      return false;
/* 1402:     */    }
/* 1403:     */    
/* 1404:     */    public boolean get(short k)
/* 1405:     */    {
/* 1406:1406 */      short kk = k;
/* 1407:1407 */      Short2BooleanRBTreeMap.Entry e; return (in(kk)) && ((e = Short2BooleanRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1408:     */    }
/* 1409:     */    
/* 1410:1410 */    public boolean put(short k, boolean v) { Short2BooleanRBTreeMap.this.modified = false;
/* 1411:1411 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1412:1412 */      boolean oldValue = Short2BooleanRBTreeMap.this.put(k, v);
/* 1413:1413 */      return Short2BooleanRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1414:     */    }
/* 1415:     */    
/* 1416:1416 */    public Boolean put(Short ok, Boolean ov) { boolean oldValue = put(ok.shortValue(), ov.booleanValue());
/* 1417:1417 */      return Short2BooleanRBTreeMap.this.modified ? null : Boolean.valueOf(oldValue);
/* 1418:     */    }
/* 1419:     */    
/* 1420:     */    public boolean remove(short k) {
/* 1421:1421 */      Short2BooleanRBTreeMap.this.modified = false;
/* 1422:1422 */      if (!in(k)) return this.defRetValue;
/* 1423:1423 */      boolean oldValue = Short2BooleanRBTreeMap.this.remove(k);
/* 1424:1424 */      return Short2BooleanRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1425:     */    }
/* 1426:     */    
/* 1427:1427 */    public Boolean remove(Object ok) { boolean oldValue = remove(((Short)ok).shortValue());
/* 1428:1428 */      return Short2BooleanRBTreeMap.this.modified ? Boolean.valueOf(oldValue) : null;
/* 1429:     */    }
/* 1430:     */    
/* 1431:1431 */    public int size() { SubmapIterator i = new SubmapIterator();
/* 1432:1432 */      int n = 0;
/* 1433:1433 */      while (i.hasNext()) {
/* 1434:1434 */        n++;
/* 1435:1435 */        i.nextEntry();
/* 1436:     */      }
/* 1437:1437 */      return n;
/* 1438:     */    }
/* 1439:     */    
/* 1440:1440 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1441:     */    
/* 1443:1443 */    public ShortComparator comparator() { return Short2BooleanRBTreeMap.this.actualComparator; }
/* 1444:     */    
/* 1445:     */    public Short2BooleanSortedMap headMap(short to) {
/* 1446:1446 */      if (this.top) return new Submap(Short2BooleanRBTreeMap.this, this.from, this.bottom, to, false);
/* 1447:1447 */      return Short2BooleanRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Short2BooleanRBTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1448:     */    }
/* 1449:     */    
/* 1450:1450 */    public Short2BooleanSortedMap tailMap(short from) { if (this.bottom) return new Submap(Short2BooleanRBTreeMap.this, from, false, this.to, this.top);
/* 1451:1451 */      return Short2BooleanRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Short2BooleanRBTreeMap.this, from, false, this.to, this.top) : this;
/* 1452:     */    }
/* 1453:     */    
/* 1454:1454 */    public Short2BooleanSortedMap subMap(short from, short to) { if ((this.top) && (this.bottom)) return new Submap(Short2BooleanRBTreeMap.this, from, false, to, false);
/* 1455:1455 */      if (!this.top) to = Short2BooleanRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1456:1456 */      if (!this.bottom) from = Short2BooleanRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1457:1457 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1458:1458 */      return new Submap(Short2BooleanRBTreeMap.this, from, false, to, false);
/* 1459:     */    }
/* 1460:     */    
/* 1463:     */    public Short2BooleanRBTreeMap.Entry firstEntry()
/* 1464:     */    {
/* 1465:1465 */      if (Short2BooleanRBTreeMap.this.tree == null) return null;
/* 1466:     */      Short2BooleanRBTreeMap.Entry e;
/* 1467:     */      Short2BooleanRBTreeMap.Entry e;
/* 1468:1468 */      if (this.bottom) { e = Short2BooleanRBTreeMap.this.firstEntry;
/* 1469:     */      } else {
/* 1470:1470 */        e = Short2BooleanRBTreeMap.this.locateKey(this.from);
/* 1471:     */        
/* 1472:1472 */        if (Short2BooleanRBTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1473:     */        }
/* 1474:     */      }
/* 1475:1475 */      if ((e == null) || ((!this.top) && (Short2BooleanRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1476:1476 */      return e;
/* 1477:     */    }
/* 1478:     */    
/* 1481:     */    public Short2BooleanRBTreeMap.Entry lastEntry()
/* 1482:     */    {
/* 1483:1483 */      if (Short2BooleanRBTreeMap.this.tree == null) return null;
/* 1484:     */      Short2BooleanRBTreeMap.Entry e;
/* 1485:     */      Short2BooleanRBTreeMap.Entry e;
/* 1486:1486 */      if (this.top) { e = Short2BooleanRBTreeMap.this.lastEntry;
/* 1487:     */      } else {
/* 1488:1488 */        e = Short2BooleanRBTreeMap.this.locateKey(this.to);
/* 1489:     */        
/* 1490:1490 */        if (Short2BooleanRBTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1491:     */        }
/* 1492:     */      }
/* 1493:1493 */      if ((e == null) || ((!this.bottom) && (Short2BooleanRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1494:1494 */      return e;
/* 1495:     */    }
/* 1496:     */    
/* 1497:1497 */    public short firstShortKey() { Short2BooleanRBTreeMap.Entry e = firstEntry();
/* 1498:1498 */      if (e == null) throw new NoSuchElementException();
/* 1499:1499 */      return e.key;
/* 1500:     */    }
/* 1501:     */    
/* 1502:1502 */    public short lastShortKey() { Short2BooleanRBTreeMap.Entry e = lastEntry();
/* 1503:1503 */      if (e == null) throw new NoSuchElementException();
/* 1504:1504 */      return e.key;
/* 1505:     */    }
/* 1506:     */    
/* 1507:1507 */    public Short firstKey() { Short2BooleanRBTreeMap.Entry e = firstEntry();
/* 1508:1508 */      if (e == null) throw new NoSuchElementException();
/* 1509:1509 */      return e.getKey();
/* 1510:     */    }
/* 1511:     */    
/* 1512:1512 */    public Short lastKey() { Short2BooleanRBTreeMap.Entry e = lastEntry();
/* 1513:1513 */      if (e == null) throw new NoSuchElementException();
/* 1514:1514 */      return e.getKey();
/* 1515:     */    }
/* 1516:     */    
/* 1519:     */    private class SubmapIterator
/* 1520:     */      extends Short2BooleanRBTreeMap.TreeIterator
/* 1521:     */    {
/* 1522:     */      SubmapIterator()
/* 1523:     */      {
/* 1524:1524 */        super();
/* 1525:1525 */        this.next = Short2BooleanRBTreeMap.Submap.this.firstEntry();
/* 1526:     */      }
/* 1527:     */      
/* 1528:1528 */      SubmapIterator(short k) { this();
/* 1529:1529 */        if (this.next != null)
/* 1530:1530 */          if ((!Short2BooleanRBTreeMap.Submap.this.bottom) && (Short2BooleanRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1531:1531 */          } else if ((!Short2BooleanRBTreeMap.Submap.this.top) && (Short2BooleanRBTreeMap.this.compare(k, (this.prev = Short2BooleanRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1532:     */          } else {
/* 1533:1533 */            this.next = Short2BooleanRBTreeMap.this.locateKey(k);
/* 1534:1534 */            if (Short2BooleanRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1535:1535 */              this.prev = this.next;
/* 1536:1536 */              this.next = this.next.next();
/* 1537:     */            } else {
/* 1538:1538 */              this.prev = this.next.prev();
/* 1539:     */            }
/* 1540:     */          }
/* 1541:     */      }
/* 1542:     */      
/* 1543:1543 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1544:1544 */        if ((!Short2BooleanRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Short2BooleanRBTreeMap.this.compare(this.prev.key, Short2BooleanRBTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1545:     */      }
/* 1546:     */      
/* 1547:1547 */      void updateNext() { this.next = this.next.next();
/* 1548:1548 */        if ((!Short2BooleanRBTreeMap.Submap.this.top) && (this.next != null) && (Short2BooleanRBTreeMap.this.compare(this.next.key, Short2BooleanRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1549:     */      }
/* 1550:     */    }
/* 1551:     */    
/* 1552:1552 */    private class SubmapEntryIterator extends Short2BooleanRBTreeMap.Submap.SubmapIterator implements ObjectListIterator<Short2BooleanMap.Entry> { SubmapEntryIterator() { super(); }
/* 1553:     */      
/* 1554:1554 */      SubmapEntryIterator(short k) { super(k); }
/* 1555:     */      
/* 1556:1556 */      public Short2BooleanMap.Entry next() { return nextEntry(); }
/* 1557:1557 */      public Short2BooleanMap.Entry previous() { return previousEntry(); }
/* 1558:1558 */      public void set(Short2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1559:1559 */      public void add(Short2BooleanMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1560:     */    }
/* 1561:     */    
/* 1566:     */    private final class SubmapKeyIterator
/* 1567:     */      extends Short2BooleanRBTreeMap.Submap.SubmapIterator
/* 1568:     */      implements ShortListIterator
/* 1569:     */    {
/* 1570:1570 */      public SubmapKeyIterator() { super(); }
/* 1571:1571 */      public SubmapKeyIterator(short from) { super(from); }
/* 1572:1572 */      public short nextShort() { return nextEntry().key; }
/* 1573:1573 */      public short previousShort() { return previousEntry().key; }
/* 1574:1574 */      public void set(short k) { throw new UnsupportedOperationException(); }
/* 1575:1575 */      public void add(short k) { throw new UnsupportedOperationException(); }
/* 1576:1576 */      public Short next() { return Short.valueOf(nextEntry().key); }
/* 1577:1577 */      public Short previous() { return Short.valueOf(previousEntry().key); }
/* 1578:1578 */      public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1579:1579 */      public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1580:     */    }
/* 1581:     */    
/* 1585:     */    private final class SubmapValueIterator
/* 1586:     */      extends Short2BooleanRBTreeMap.Submap.SubmapIterator
/* 1587:     */      implements BooleanListIterator
/* 1588:     */    {
/* 1589:1589 */      private SubmapValueIterator() { super(); }
/* 1590:1590 */      public boolean nextBoolean() { return nextEntry().value; }
/* 1591:1591 */      public boolean previousBoolean() { return previousEntry().value; }
/* 1592:1592 */      public void set(boolean v) { throw new UnsupportedOperationException(); }
/* 1593:1593 */      public void add(boolean v) { throw new UnsupportedOperationException(); }
/* 1594:1594 */      public Boolean next() { return Boolean.valueOf(nextEntry().value); }
/* 1595:1595 */      public Boolean previous() { return Boolean.valueOf(previousEntry().value); }
/* 1596:1596 */      public void set(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1597:1597 */      public void add(Boolean ok) { throw new UnsupportedOperationException(); }
/* 1598:     */    }
/* 1599:     */  }
/* 1600:     */  
/* 1604:     */  public Short2BooleanRBTreeMap clone()
/* 1605:     */  {
/* 1606:     */    Short2BooleanRBTreeMap c;
/* 1607:     */    
/* 1609:     */    try
/* 1610:     */    {
/* 1611:1611 */      c = (Short2BooleanRBTreeMap)super.clone();
/* 1612:     */    }
/* 1613:     */    catch (CloneNotSupportedException cantHappen) {
/* 1614:1614 */      throw new InternalError();
/* 1615:     */    }
/* 1616:1616 */    c.keys = null;
/* 1617:1617 */    c.values = null;
/* 1618:1618 */    c.entries = null;
/* 1619:1619 */    c.allocatePaths();
/* 1620:1620 */    if (this.count != 0)
/* 1621:     */    {
/* 1622:1622 */      Entry rp = new Entry();Entry rq = new Entry();
/* 1623:1623 */      Entry p = rp;
/* 1624:1624 */      rp.left(this.tree);
/* 1625:1625 */      Entry q = rq;
/* 1626:1626 */      rq.pred(null);
/* 1627:     */      for (;;) {
/* 1628:1628 */        if (!p.pred()) {
/* 1629:1629 */          Entry e = p.left.clone();
/* 1630:1630 */          e.pred(q.left);
/* 1631:1631 */          e.succ(q);
/* 1632:1632 */          q.left(e);
/* 1633:1633 */          p = p.left;
/* 1634:1634 */          q = q.left;
/* 1635:     */        }
/* 1636:     */        else {
/* 1637:1637 */          while (p.succ()) {
/* 1638:1638 */            p = p.right;
/* 1639:1639 */            if (p == null) {
/* 1640:1640 */              q.right = null;
/* 1641:1641 */              c.tree = rq.left;
/* 1642:1642 */              c.firstEntry = c.tree;
/* 1643:1643 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1644:1644 */              c.lastEntry = c.tree;
/* 1645:1645 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1646:1646 */              return c;
/* 1647:     */            }
/* 1648:1648 */            q = q.right;
/* 1649:     */          }
/* 1650:1650 */          p = p.right;
/* 1651:1651 */          q = q.right;
/* 1652:     */        }
/* 1653:1653 */        if (!p.succ()) {
/* 1654:1654 */          Entry e = p.right.clone();
/* 1655:1655 */          e.succ(q.right);
/* 1656:1656 */          e.pred(q);
/* 1657:1657 */          q.right(e);
/* 1658:     */        }
/* 1659:     */      }
/* 1660:     */    }
/* 1661:1661 */    return c;
/* 1662:     */  }
/* 1663:     */  
/* 1664:1664 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1665:1665 */    EntryIterator i = new EntryIterator();
/* 1666:     */    
/* 1667:1667 */    s.defaultWriteObject();
/* 1668:1668 */    while (n-- != 0) {
/* 1669:1669 */      Entry e = i.nextEntry();
/* 1670:1670 */      s.writeShort(e.key);
/* 1671:1671 */      s.writeBoolean(e.value);
/* 1672:     */    }
/* 1673:     */  }
/* 1674:     */  
/* 1680:     */  private Entry readTree(ObjectInputStream s, int n, Entry pred, Entry succ)
/* 1681:     */    throws IOException, ClassNotFoundException
/* 1682:     */  {
/* 1683:1683 */    if (n == 1) {
/* 1684:1684 */      Entry top = new Entry(s.readShort(), s.readBoolean());
/* 1685:1685 */      top.pred(pred);
/* 1686:1686 */      top.succ(succ);
/* 1687:1687 */      top.black(true);
/* 1688:1688 */      return top;
/* 1689:     */    }
/* 1690:1690 */    if (n == 2)
/* 1691:     */    {
/* 1693:1693 */      Entry top = new Entry(s.readShort(), s.readBoolean());
/* 1694:1694 */      top.black(true);
/* 1695:1695 */      top.right(new Entry(s.readShort(), s.readBoolean()));
/* 1696:1696 */      top.right.pred(top);
/* 1697:1697 */      top.pred(pred);
/* 1698:1698 */      top.right.succ(succ);
/* 1699:1699 */      return top;
/* 1700:     */    }
/* 1701:     */    
/* 1702:1702 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1703:1703 */    Entry top = new Entry();
/* 1704:1704 */    top.left(readTree(s, leftN, pred, top));
/* 1705:1705 */    top.key = s.readShort();
/* 1706:1706 */    top.value = s.readBoolean();
/* 1707:1707 */    top.black(true);
/* 1708:1708 */    top.right(readTree(s, rightN, top, succ));
/* 1709:1709 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1710:1710 */    return top;
/* 1711:     */  }
/* 1712:     */  
/* 1713:1713 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1714:     */    
/* 1716:1716 */    setActualComparator();
/* 1717:1717 */    allocatePaths();
/* 1718:1718 */    if (this.count != 0) {
/* 1719:1719 */      this.tree = readTree(s, this.count, null, null);
/* 1720:     */      
/* 1721:1721 */      Entry e = this.tree;
/* 1722:1722 */      while (e.left() != null) e = e.left();
/* 1723:1723 */      this.firstEntry = e;
/* 1724:1724 */      e = this.tree;
/* 1725:1725 */      while (e.right() != null) e = e.right();
/* 1726:1726 */      this.lastEntry = e;
/* 1727:     */    }
/* 1728:     */  }
/* 1729:     */  
/* 1730:     */  private void checkNodePath() {}
/* 1731:     */  
/* 1732:1732 */  private int checkTree(Entry e, int d, int D) { return 0; }
/* 1733:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2BooleanRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */