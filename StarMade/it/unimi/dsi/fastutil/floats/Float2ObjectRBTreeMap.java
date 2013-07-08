/*    1:     */package it.unimi.dsi.fastutil.floats;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.HashCommon;
/*    4:     */import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*    5:     */import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*    6:     */import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*    7:     */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*    8:     */import it.unimi.dsi.fastutil.objects.ObjectIterator;
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
/*   71:     */public class Float2ObjectRBTreeMap<V>
/*   72:     */  extends AbstractFloat2ObjectSortedMap<V>
/*   73:     */  implements Serializable, Cloneable
/*   74:     */{
/*   75:     */  protected transient Entry<V> tree;
/*   76:     */  protected int count;
/*   77:     */  protected transient Entry<V> firstEntry;
/*   78:     */  protected transient Entry<V> lastEntry;
/*   79:     */  protected volatile transient ObjectSortedSet<Float2ObjectMap.Entry<V>> entries;
/*   80:     */  protected volatile transient FloatSortedSet keys;
/*   81:     */  protected volatile transient ObjectCollection<V> values;
/*   82:     */  protected transient boolean modified;
/*   83:     */  protected Comparator<? super Float> storedComparator;
/*   84:     */  protected transient FloatComparator actualComparator;
/*   85:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   86:     */  private static final boolean ASSERTS = false;
/*   87:     */  private transient boolean[] dirPath;
/*   88:     */  private transient Entry<V>[] nodePath;
/*   89:     */  
/*   90:     */  public Float2ObjectRBTreeMap()
/*   91:     */  {
/*   92:  92 */    allocatePaths();
/*   93:     */    
/*   97:  97 */    this.tree = null;
/*   98:  98 */    this.count = 0;
/*   99:     */  }
/*  100:     */  
/*  114:     */  private void setActualComparator()
/*  115:     */  {
/*  116: 116 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof FloatComparator))) this.actualComparator = ((FloatComparator)this.storedComparator); else {
/*  117: 117 */      this.actualComparator = new FloatComparator() {
/*  118:     */        public int compare(float k1, float k2) {
/*  119: 119 */          return Float2ObjectRBTreeMap.this.storedComparator.compare(Float.valueOf(k1), Float.valueOf(k2));
/*  120:     */        }
/*  121:     */        
/*  122: 122 */        public int compare(Float ok1, Float ok2) { return Float2ObjectRBTreeMap.this.storedComparator.compare(ok1, ok2); }
/*  123:     */      };
/*  124:     */    }
/*  125:     */  }
/*  126:     */  
/*  133:     */  public Float2ObjectRBTreeMap(Comparator<? super Float> c)
/*  134:     */  {
/*  135: 135 */    this();
/*  136: 136 */    this.storedComparator = c;
/*  137: 137 */    setActualComparator();
/*  138:     */  }
/*  139:     */  
/*  145:     */  public Float2ObjectRBTreeMap(Map<? extends Float, ? extends V> m)
/*  146:     */  {
/*  147: 147 */    this();
/*  148: 148 */    putAll(m);
/*  149:     */  }
/*  150:     */  
/*  155:     */  public Float2ObjectRBTreeMap(SortedMap<Float, V> m)
/*  156:     */  {
/*  157: 157 */    this(m.comparator());
/*  158: 158 */    putAll(m);
/*  159:     */  }
/*  160:     */  
/*  165:     */  public Float2ObjectRBTreeMap(Float2ObjectMap<? extends V> m)
/*  166:     */  {
/*  167: 167 */    this();
/*  168: 168 */    putAll(m);
/*  169:     */  }
/*  170:     */  
/*  175:     */  public Float2ObjectRBTreeMap(Float2ObjectSortedMap<V> m)
/*  176:     */  {
/*  177: 177 */    this(m.comparator());
/*  178: 178 */    putAll(m);
/*  179:     */  }
/*  180:     */  
/*  188:     */  public Float2ObjectRBTreeMap(float[] k, V[] v, Comparator<? super Float> c)
/*  189:     */  {
/*  190: 190 */    this(c);
/*  191: 191 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  192: 192 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  193:     */    }
/*  194:     */  }
/*  195:     */  
/*  201:     */  public Float2ObjectRBTreeMap(float[] k, V[] v)
/*  202:     */  {
/*  203: 203 */    this(k, v, null);
/*  204:     */  }
/*  205:     */  
/*  228:     */  final int compare(float k1, float k2)
/*  229:     */  {
/*  230: 230 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  231:     */  }
/*  232:     */  
/*  240:     */  final Entry<V> findKey(float k)
/*  241:     */  {
/*  242: 242 */    Entry<V> e = this.tree;
/*  243:     */    
/*  244:     */    int cmp;
/*  245: 245 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) { e = cmp < 0 ? e.left() : e.right();
/*  246:     */    }
/*  247: 247 */    return e;
/*  248:     */  }
/*  249:     */  
/*  256:     */  final Entry<V> locateKey(float k)
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
/*  282:     */  public V put(float k, V v)
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
/*  454:     */  public V remove(float k)
/*  455:     */  {
/*  456: 456 */    this.modified = false;
/*  457:     */    
/*  458: 458 */    if (this.tree == null) { return this.defRetValue;
/*  459:     */    }
/*  460: 460 */    Entry<V> p = this.tree;
/*  461:     */    
/*  462: 462 */    int i = 0;
/*  463: 463 */    float kk = k;
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
/*  728:     */  public V put(Float ok, V ov)
/*  729:     */  {
/*  730: 730 */    V oldValue = put(ok.floatValue(), ov);
/*  731: 731 */    return this.modified ? this.defRetValue : oldValue;
/*  732:     */  }
/*  733:     */  
/*  735:     */  public V remove(Object ok)
/*  736:     */  {
/*  737: 737 */    V oldValue = remove(((Float)ok).floatValue());
/*  738: 738 */    return this.modified ? oldValue : this.defRetValue;
/*  739:     */  }
/*  740:     */  
/*  742:     */  public boolean containsValue(Object v)
/*  743:     */  {
/*  744: 744 */    Float2ObjectRBTreeMap<V>.ValueIterator i = new ValueIterator(null);
/*  745:     */    
/*  747: 747 */    int j = this.count;
/*  748: 748 */    for (; j-- != 0; 
/*  749:     */        
/*  750: 750 */        return true)
/*  751:     */    {
/*  752:     */      label16:
/*  753: 749 */      Object ev = i.next();
/*  754: 750 */      if (ev == null ? v != null : !ev.equals(v))
/*  755:     */        break label16;
/*  756:     */    }
/*  757: 753 */    return false;
/*  758:     */  }
/*  759:     */  
/*  760:     */  public void clear()
/*  761:     */  {
/*  762: 758 */    this.count = 0;
/*  763: 759 */    this.tree = null;
/*  764: 760 */    this.entries = null;
/*  765: 761 */    this.values = null;
/*  766: 762 */    this.keys = null;
/*  767: 763 */    this.firstEntry = (this.lastEntry = null);
/*  768:     */  }
/*  769:     */  
/*  772:     */  private static final class Entry<V>
/*  773:     */    implements Cloneable, Float2ObjectMap.Entry<V>
/*  774:     */  {
/*  775:     */    private static final int BLACK_MASK = 1;
/*  776:     */    
/*  778:     */    private static final int SUCC_MASK = -2147483648;
/*  779:     */    
/*  781:     */    private static final int PRED_MASK = 1073741824;
/*  782:     */    
/*  784:     */    float key;
/*  785:     */    
/*  787:     */    V value;
/*  788:     */    
/*  790:     */    Entry<V> left;
/*  791:     */    
/*  793:     */    Entry<V> right;
/*  794:     */    
/*  796:     */    int info;
/*  797:     */    
/*  799:     */    Entry() {}
/*  800:     */    
/*  802:     */    Entry(float k, V v)
/*  803:     */    {
/*  804: 800 */      this.key = k;
/*  805: 801 */      this.value = v;
/*  806: 802 */      this.info = -1073741824;
/*  807:     */    }
/*  808:     */    
/*  813:     */    Entry<V> left()
/*  814:     */    {
/*  815: 811 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  816:     */    }
/*  817:     */    
/*  822:     */    Entry<V> right()
/*  823:     */    {
/*  824: 820 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  825:     */    }
/*  826:     */    
/*  829:     */    boolean pred()
/*  830:     */    {
/*  831: 827 */      return (this.info & 0x40000000) != 0;
/*  832:     */    }
/*  833:     */    
/*  836:     */    boolean succ()
/*  837:     */    {
/*  838: 834 */      return (this.info & 0x80000000) != 0;
/*  839:     */    }
/*  840:     */    
/*  843:     */    void pred(boolean pred)
/*  844:     */    {
/*  845: 841 */      if (pred) this.info |= 1073741824; else {
/*  846: 842 */        this.info &= -1073741825;
/*  847:     */      }
/*  848:     */    }
/*  849:     */    
/*  851:     */    void succ(boolean succ)
/*  852:     */    {
/*  853: 849 */      if (succ) this.info |= -2147483648; else {
/*  854: 850 */        this.info &= 2147483647;
/*  855:     */      }
/*  856:     */    }
/*  857:     */    
/*  859:     */    void pred(Entry<V> pred)
/*  860:     */    {
/*  861: 857 */      this.info |= 1073741824;
/*  862: 858 */      this.left = pred;
/*  863:     */    }
/*  864:     */    
/*  867:     */    void succ(Entry<V> succ)
/*  868:     */    {
/*  869: 865 */      this.info |= -2147483648;
/*  870: 866 */      this.right = succ;
/*  871:     */    }
/*  872:     */    
/*  875:     */    void left(Entry<V> left)
/*  876:     */    {
/*  877: 873 */      this.info &= -1073741825;
/*  878: 874 */      this.left = left;
/*  879:     */    }
/*  880:     */    
/*  883:     */    void right(Entry<V> right)
/*  884:     */    {
/*  885: 881 */      this.info &= 2147483647;
/*  886: 882 */      this.right = right;
/*  887:     */    }
/*  888:     */    
/*  892:     */    boolean black()
/*  893:     */    {
/*  894: 890 */      return (this.info & 0x1) != 0;
/*  895:     */    }
/*  896:     */    
/*  899:     */    void black(boolean black)
/*  900:     */    {
/*  901: 897 */      if (black) this.info |= 1; else {
/*  902: 898 */        this.info &= -2;
/*  903:     */      }
/*  904:     */    }
/*  905:     */    
/*  909:     */    Entry<V> next()
/*  910:     */    {
/*  911: 907 */      Entry<V> next = this.right;
/*  912: 908 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  913: 909 */      return next;
/*  914:     */    }
/*  915:     */    
/*  920:     */    Entry<V> prev()
/*  921:     */    {
/*  922: 918 */      Entry<V> prev = this.left;
/*  923: 919 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  924: 920 */      return prev;
/*  925:     */    }
/*  926:     */    
/*  927:     */    public Float getKey() {
/*  928: 924 */      return Float.valueOf(this.key);
/*  929:     */    }
/*  930:     */    
/*  931:     */    public float getFloatKey()
/*  932:     */    {
/*  933: 929 */      return this.key;
/*  934:     */    }
/*  935:     */    
/*  936:     */    public V getValue()
/*  937:     */    {
/*  938: 934 */      return this.value;
/*  939:     */    }
/*  940:     */    
/*  946:     */    public V setValue(V value)
/*  947:     */    {
/*  948: 944 */      V oldValue = this.value;
/*  949: 945 */      this.value = value;
/*  950: 946 */      return oldValue;
/*  951:     */    }
/*  952:     */    
/*  953:     */    public Entry<V> clone() {
/*  954:     */      Entry<V> c;
/*  955:     */      try {
/*  956: 952 */        c = (Entry)super.clone();
/*  957:     */      }
/*  958:     */      catch (CloneNotSupportedException cantHappen) {
/*  959: 955 */        throw new InternalError();
/*  960:     */      }
/*  961: 957 */      c.key = this.key;
/*  962: 958 */      c.value = this.value;
/*  963: 959 */      c.info = this.info;
/*  964: 960 */      return c;
/*  965:     */    }
/*  966:     */    
/*  967:     */    public boolean equals(Object o) {
/*  968: 964 */      if (!(o instanceof Map.Entry)) return false;
/*  969: 965 */      Map.Entry<Float, V> e = (Map.Entry)o;
/*  970: 966 */      return (this.key == ((Float)e.getKey()).floatValue()) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/*  971:     */    }
/*  972:     */    
/*  973: 969 */    public int hashCode() { return HashCommon.float2int(this.key) ^ (this.value == null ? 0 : this.value.hashCode()); }
/*  974:     */    
/*  975:     */    public String toString() {
/*  976: 972 */      return this.key + "=>" + this.value;
/*  977:     */    }
/*  978:     */  }
/*  979:     */  
/* 1008:     */  public boolean containsKey(float k)
/* 1009:     */  {
/* 1010:1006 */    return findKey(k) != null;
/* 1011:     */  }
/* 1012:     */  
/* 1013:1009 */  public int size() { return this.count; }
/* 1014:     */  
/* 1015:     */  public boolean isEmpty() {
/* 1016:1012 */    return this.count == 0;
/* 1017:     */  }
/* 1018:     */  
/* 1019:     */  public V get(float k) {
/* 1020:1016 */    Entry<V> e = findKey(k);
/* 1021:1017 */    return e == null ? this.defRetValue : e.value;
/* 1022:     */  }
/* 1023:     */  
/* 1024:1020 */  public float firstFloatKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1025:1021 */    return this.firstEntry.key;
/* 1026:     */  }
/* 1027:     */  
/* 1028:1024 */  public float lastFloatKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1029:1025 */    return this.lastEntry.key;
/* 1030:     */  }
/* 1031:     */  
/* 1034:     */  private class TreeIterator
/* 1035:     */  {
/* 1036:     */    Float2ObjectRBTreeMap.Entry<V> prev;
/* 1037:     */    
/* 1039:     */    Float2ObjectRBTreeMap.Entry<V> next;
/* 1040:     */    
/* 1041:     */    Float2ObjectRBTreeMap.Entry<V> curr;
/* 1042:     */    
/* 1043:1039 */    int index = 0;
/* 1044:     */    
/* 1045:1041 */    TreeIterator() { this.next = Float2ObjectRBTreeMap.this.firstEntry; }
/* 1046:     */    
/* 1047:     */    TreeIterator(float k) {
/* 1048:1044 */      if ((this.next = Float2ObjectRBTreeMap.this.locateKey(k)) != null)
/* 1049:1045 */        if (Float2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1050:1046 */          this.prev = this.next;
/* 1051:1047 */          this.next = this.next.next();
/* 1052:     */        } else {
/* 1053:1049 */          this.prev = this.next.prev();
/* 1054:     */        } }
/* 1055:     */    
/* 1056:1052 */    public boolean hasNext() { return this.next != null; }
/* 1057:1053 */    public boolean hasPrevious() { return this.prev != null; }
/* 1058:     */    
/* 1059:1055 */    void updateNext() { this.next = this.next.next(); }
/* 1060:     */    
/* 1061:     */    Float2ObjectRBTreeMap.Entry<V> nextEntry() {
/* 1062:1058 */      if (!hasNext()) throw new NoSuchElementException();
/* 1063:1059 */      this.curr = (this.prev = this.next);
/* 1064:1060 */      this.index += 1;
/* 1065:1061 */      updateNext();
/* 1066:1062 */      return this.curr;
/* 1067:     */    }
/* 1068:     */    
/* 1069:1065 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1070:     */    
/* 1071:     */    Float2ObjectRBTreeMap.Entry<V> previousEntry() {
/* 1072:1068 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1073:1069 */      this.curr = (this.next = this.prev);
/* 1074:1070 */      this.index -= 1;
/* 1075:1071 */      updatePrevious();
/* 1076:1072 */      return this.curr;
/* 1077:     */    }
/* 1078:     */    
/* 1079:1075 */    public int nextIndex() { return this.index; }
/* 1080:     */    
/* 1082:1078 */    public int previousIndex() { return this.index - 1; }
/* 1083:     */    
/* 1084:     */    public void remove() {
/* 1085:1081 */      if (this.curr == null) { throw new IllegalStateException();
/* 1086:     */      }
/* 1087:     */      
/* 1088:1084 */      if (this.curr == this.prev) this.index -= 1;
/* 1089:1085 */      this.next = (this.prev = this.curr);
/* 1090:1086 */      updatePrevious();
/* 1091:1087 */      updateNext();
/* 1092:1088 */      Float2ObjectRBTreeMap.this.remove(this.curr.key);
/* 1093:1089 */      this.curr = null;
/* 1094:     */    }
/* 1095:     */    
/* 1096:1092 */    public int skip(int n) { int i = n;
/* 1097:1093 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 1098:1094 */      return n - i - 1;
/* 1099:     */    }
/* 1100:     */    
/* 1101:1097 */    public int back(int n) { int i = n;
/* 1102:1098 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1103:1099 */      return n - i - 1;
/* 1104:     */    }
/* 1105:     */  }
/* 1106:     */  
/* 1107:     */  private class EntryIterator
/* 1108:     */    extends Float2ObjectRBTreeMap<V>.TreeIterator
/* 1109:     */    implements ObjectListIterator<Float2ObjectMap.Entry<V>>
/* 1110:     */  {
/* 1111:1107 */    EntryIterator() { super(); }
/* 1112:     */    
/* 1113:1109 */    EntryIterator(float k) { super(k); }
/* 1114:     */    
/* 1115:1111 */    public Float2ObjectMap.Entry<V> next() { return nextEntry(); }
/* 1116:1112 */    public Float2ObjectMap.Entry<V> previous() { return previousEntry(); }
/* 1117:1113 */    public void set(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1118:1114 */    public void add(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1119:     */  }
/* 1120:     */  
/* 1121:1117 */  public ObjectSortedSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1122:1118 */        final Comparator<? super Float2ObjectMap.Entry<V>> comparator = new Comparator() {
/* 1123:     */          public int compare(Float2ObjectMap.Entry<V> x, Float2ObjectMap.Entry<V> y) {
/* 1124:1120 */            return Float2ObjectRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1125:     */          }
/* 1126:     */        };
/* 1127:     */        
/* 1128:1124 */        public Comparator<? super Float2ObjectMap.Entry<V>> comparator() { return this.comparator; }
/* 1129:     */        
/* 1130:     */        public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator() {
/* 1131:1127 */          return new Float2ObjectRBTreeMap.EntryIterator(Float2ObjectRBTreeMap.this);
/* 1132:     */        }
/* 1133:     */        
/* 1134:1130 */        public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator(Float2ObjectMap.Entry<V> from) { return new Float2ObjectRBTreeMap.EntryIterator(Float2ObjectRBTreeMap.this, ((Float)from.getKey()).floatValue()); }
/* 1135:     */        
/* 1136:     */        public boolean contains(Object o)
/* 1137:     */        {
/* 1138:1134 */          if (!(o instanceof Map.Entry)) return false;
/* 1139:1135 */          Map.Entry<Float, V> e = (Map.Entry)o;
/* 1140:1136 */          Float2ObjectRBTreeMap.Entry<V> f = Float2ObjectRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1141:1137 */          return e.equals(f);
/* 1142:     */        }
/* 1143:     */        
/* 1144:     */        public boolean remove(Object o) {
/* 1145:1141 */          if (!(o instanceof Map.Entry)) return false;
/* 1146:1142 */          Map.Entry<Float, V> e = (Map.Entry)o;
/* 1147:1143 */          Float2ObjectRBTreeMap.Entry<V> f = Float2ObjectRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1148:1144 */          if (f != null) Float2ObjectRBTreeMap.this.remove(f.key);
/* 1149:1145 */          return f != null; }
/* 1150:     */        
/* 1151:1147 */        public int size() { return Float2ObjectRBTreeMap.this.count; }
/* 1152:1148 */        public void clear() { Float2ObjectRBTreeMap.this.clear(); }
/* 1153:1149 */        public Float2ObjectMap.Entry<V> first() { return Float2ObjectRBTreeMap.this.firstEntry; }
/* 1154:1150 */        public Float2ObjectMap.Entry<V> last() { return Float2ObjectRBTreeMap.this.lastEntry; }
/* 1155:1151 */        public ObjectSortedSet<Float2ObjectMap.Entry<V>> subSet(Float2ObjectMap.Entry<V> from, Float2ObjectMap.Entry<V> to) { return Float2ObjectRBTreeMap.this.subMap((Float)from.getKey(), (Float)to.getKey()).float2ObjectEntrySet(); }
/* 1156:1152 */        public ObjectSortedSet<Float2ObjectMap.Entry<V>> headSet(Float2ObjectMap.Entry<V> to) { return Float2ObjectRBTreeMap.this.headMap((Float)to.getKey()).float2ObjectEntrySet(); }
/* 1157:1153 */        public ObjectSortedSet<Float2ObjectMap.Entry<V>> tailSet(Float2ObjectMap.Entry<V> from) { return Float2ObjectRBTreeMap.this.tailMap((Float)from.getKey()).float2ObjectEntrySet(); }
/* 1158:     */      };
/* 1159:1155 */    return this.entries;
/* 1160:     */  }
/* 1161:     */  
/* 1164:     */  private final class KeyIterator
/* 1165:     */    extends Float2ObjectRBTreeMap.TreeIterator
/* 1166:     */    implements FloatListIterator
/* 1167:     */  {
/* 1168:1164 */    public KeyIterator() { super(); }
/* 1169:1165 */    public KeyIterator(float k) { super(k); }
/* 1170:1166 */    public float nextFloat() { return nextEntry().key; }
/* 1171:1167 */    public float previousFloat() { return previousEntry().key; }
/* 1172:1168 */    public void set(float k) { throw new UnsupportedOperationException(); }
/* 1173:1169 */    public void add(float k) { throw new UnsupportedOperationException(); }
/* 1174:1170 */    public Float next() { return Float.valueOf(nextEntry().key); }
/* 1175:1171 */    public Float previous() { return Float.valueOf(previousEntry().key); }
/* 1176:1172 */    public void set(Float ok) { throw new UnsupportedOperationException(); }
/* 1177:1173 */    public void add(Float ok) { throw new UnsupportedOperationException(); }
/* 1178:     */  }
/* 1179:     */  
/* 1180:1176 */  private class KeySet extends AbstractFloat2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1181:1177 */    public FloatBidirectionalIterator iterator() { return new Float2ObjectRBTreeMap.KeyIterator(Float2ObjectRBTreeMap.this); }
/* 1182:1178 */    public FloatBidirectionalIterator iterator(float from) { return new Float2ObjectRBTreeMap.KeyIterator(Float2ObjectRBTreeMap.this, from); }
/* 1183:     */  }
/* 1184:     */  
/* 1191:     */  public FloatSortedSet keySet()
/* 1192:     */  {
/* 1193:1189 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1194:1190 */    return this.keys;
/* 1195:     */  }
/* 1196:     */  
/* 1198:     */  private final class ValueIterator
/* 1199:     */    extends Float2ObjectRBTreeMap<V>.TreeIterator
/* 1200:     */    implements ObjectListIterator<V>
/* 1201:     */  {
/* 1202:1198 */    private ValueIterator() { super(); }
/* 1203:1199 */    public V next() { return nextEntry().value; }
/* 1204:1200 */    public V previous() { return previousEntry().value; }
/* 1205:1201 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1206:1202 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1207:     */  }
/* 1208:     */  
/* 1215:     */  public ObjectCollection<V> values()
/* 1216:     */  {
/* 1217:1213 */    if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1218:     */        public ObjectIterator<V> iterator() {
/* 1219:1215 */          return new Float2ObjectRBTreeMap.ValueIterator(Float2ObjectRBTreeMap.this, null);
/* 1220:     */        }
/* 1221:     */        
/* 1222:1218 */        public boolean contains(Object k) { return Float2ObjectRBTreeMap.this.containsValue(k); }
/* 1223:     */        
/* 1224:     */        public int size() {
/* 1225:1221 */          return Float2ObjectRBTreeMap.this.count;
/* 1226:     */        }
/* 1227:     */        
/* 1228:1224 */        public void clear() { Float2ObjectRBTreeMap.this.clear(); }
/* 1229:     */      };
/* 1230:     */    }
/* 1231:1227 */    return this.values;
/* 1232:     */  }
/* 1233:     */  
/* 1234:1230 */  public FloatComparator comparator() { return this.actualComparator; }
/* 1235:     */  
/* 1236:     */  public Float2ObjectSortedMap<V> headMap(float to) {
/* 1237:1233 */    return new Submap(0.0F, true, to, false);
/* 1238:     */  }
/* 1239:     */  
/* 1240:1236 */  public Float2ObjectSortedMap<V> tailMap(float from) { return new Submap(from, false, 0.0F, true); }
/* 1241:     */  
/* 1242:     */  public Float2ObjectSortedMap<V> subMap(float from, float to) {
/* 1243:1239 */    return new Submap(from, false, to, false);
/* 1244:     */  }
/* 1245:     */  
/* 1249:     */  private final class Submap
/* 1250:     */    extends AbstractFloat2ObjectSortedMap<V>
/* 1251:     */    implements Serializable
/* 1252:     */  {
/* 1253:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1254:     */    
/* 1257:     */    float from;
/* 1258:     */    
/* 1260:     */    float to;
/* 1261:     */    
/* 1263:     */    boolean bottom;
/* 1264:     */    
/* 1266:     */    boolean top;
/* 1267:     */    
/* 1269:     */    protected volatile transient ObjectSortedSet<Float2ObjectMap.Entry<V>> entries;
/* 1270:     */    
/* 1272:     */    protected volatile transient FloatSortedSet keys;
/* 1273:     */    
/* 1275:     */    protected volatile transient ObjectCollection<V> values;
/* 1276:     */    
/* 1279:     */    public Submap(float from, boolean bottom, float to, boolean top)
/* 1280:     */    {
/* 1281:1277 */      if ((!bottom) && (!top) && (Float2ObjectRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1282:1278 */      this.from = from;
/* 1283:1279 */      this.bottom = bottom;
/* 1284:1280 */      this.to = to;
/* 1285:1281 */      this.top = top;
/* 1286:1282 */      this.defRetValue = Float2ObjectRBTreeMap.this.defRetValue;
/* 1287:     */    }
/* 1288:     */    
/* 1289:1285 */    public void clear() { Float2ObjectRBTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1290:1286 */      while (i.hasNext()) {
/* 1291:1287 */        i.nextEntry();
/* 1292:1288 */        i.remove();
/* 1293:     */      }
/* 1294:     */    }
/* 1295:     */    
/* 1298:     */    final boolean in(float k)
/* 1299:     */    {
/* 1300:1296 */      return ((this.bottom) || (Float2ObjectRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Float2ObjectRBTreeMap.this.compare(k, this.to) < 0));
/* 1301:     */    }
/* 1302:     */    
/* 1303:     */    public ObjectSortedSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet() {
/* 1304:1300 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1305:     */          public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator() {
/* 1306:1302 */            return new Float2ObjectRBTreeMap.Submap.SubmapEntryIterator(Float2ObjectRBTreeMap.Submap.this);
/* 1307:     */          }
/* 1308:     */          
/* 1309:1305 */          public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator(Float2ObjectMap.Entry<V> from) { return new Float2ObjectRBTreeMap.Submap.SubmapEntryIterator(Float2ObjectRBTreeMap.Submap.this, ((Float)from.getKey()).floatValue()); }
/* 1310:     */          
/* 1311:1307 */          public Comparator<? super Float2ObjectMap.Entry<V>> comparator() { return Float2ObjectRBTreeMap.this.float2ObjectEntrySet().comparator(); }
/* 1312:     */          
/* 1313:     */          public boolean contains(Object o) {
/* 1314:1310 */            if (!(o instanceof Map.Entry)) return false;
/* 1315:1311 */            Map.Entry<Float, V> e = (Map.Entry)o;
/* 1316:1312 */            Float2ObjectRBTreeMap.Entry<V> f = Float2ObjectRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1317:1313 */            return (f != null) && (Float2ObjectRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1318:     */          }
/* 1319:     */          
/* 1320:     */          public boolean remove(Object o) {
/* 1321:1317 */            if (!(o instanceof Map.Entry)) return false;
/* 1322:1318 */            Map.Entry<Float, V> e = (Map.Entry)o;
/* 1323:1319 */            Float2ObjectRBTreeMap.Entry<V> f = Float2ObjectRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1324:1320 */            if ((f != null) && (Float2ObjectRBTreeMap.Submap.this.in(f.key))) Float2ObjectRBTreeMap.Submap.this.remove(f.key);
/* 1325:1321 */            return f != null;
/* 1326:     */          }
/* 1327:     */          
/* 1328:1324 */          public int size() { int c = 0;
/* 1329:1325 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1330:1326 */            return c; }
/* 1331:     */          
/* 1332:1328 */          public boolean isEmpty() { return !new Float2ObjectRBTreeMap.Submap.SubmapIterator(Float2ObjectRBTreeMap.Submap.this).hasNext(); }
/* 1333:1329 */          public void clear() { Float2ObjectRBTreeMap.Submap.this.clear(); }
/* 1334:1330 */          public Float2ObjectMap.Entry<V> first() { return Float2ObjectRBTreeMap.Submap.this.firstEntry(); }
/* 1335:1331 */          public Float2ObjectMap.Entry<V> last() { return Float2ObjectRBTreeMap.Submap.this.lastEntry(); }
/* 1336:1332 */          public ObjectSortedSet<Float2ObjectMap.Entry<V>> subSet(Float2ObjectMap.Entry<V> from, Float2ObjectMap.Entry<V> to) { return Float2ObjectRBTreeMap.Submap.this.subMap((Float)from.getKey(), (Float)to.getKey()).float2ObjectEntrySet(); }
/* 1337:1333 */          public ObjectSortedSet<Float2ObjectMap.Entry<V>> headSet(Float2ObjectMap.Entry<V> to) { return Float2ObjectRBTreeMap.Submap.this.headMap((Float)to.getKey()).float2ObjectEntrySet(); }
/* 1338:1334 */          public ObjectSortedSet<Float2ObjectMap.Entry<V>> tailSet(Float2ObjectMap.Entry<V> from) { return Float2ObjectRBTreeMap.Submap.this.tailMap((Float)from.getKey()).float2ObjectEntrySet(); }
/* 1339:     */        };
/* 1340:1336 */      return this.entries; }
/* 1341:     */    
/* 1342:1338 */    private class KeySet extends AbstractFloat2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1343:1339 */      public FloatBidirectionalIterator iterator() { return new Float2ObjectRBTreeMap.Submap.SubmapKeyIterator(Float2ObjectRBTreeMap.Submap.this); }
/* 1344:1340 */      public FloatBidirectionalIterator iterator(float from) { return new Float2ObjectRBTreeMap.Submap.SubmapKeyIterator(Float2ObjectRBTreeMap.Submap.this, from); }
/* 1345:     */    }
/* 1346:     */    
/* 1347:1343 */    public FloatSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1348:1344 */      return this.keys;
/* 1349:     */    }
/* 1350:     */    
/* 1351:1347 */    public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1352:     */          public ObjectIterator<V> iterator() {
/* 1353:1349 */            return new Float2ObjectRBTreeMap.Submap.SubmapValueIterator(Float2ObjectRBTreeMap.Submap.this, null);
/* 1354:     */          }
/* 1355:     */          
/* 1356:1352 */          public boolean contains(Object k) { return Float2ObjectRBTreeMap.Submap.this.containsValue(k); }
/* 1357:     */          
/* 1358:     */          public int size() {
/* 1359:1355 */            return Float2ObjectRBTreeMap.Submap.this.size();
/* 1360:     */          }
/* 1361:     */          
/* 1362:1358 */          public void clear() { Float2ObjectRBTreeMap.Submap.this.clear(); }
/* 1363:     */        };
/* 1364:     */      }
/* 1365:1361 */      return this.values;
/* 1366:     */    }
/* 1367:     */    
/* 1369:1365 */    public boolean containsKey(float k) { return (in(k)) && (Float2ObjectRBTreeMap.this.containsKey(k)); }
/* 1370:     */    
/* 1371:     */    public boolean containsValue(Object v) {
/* 1372:1368 */      Float2ObjectRBTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1373:1370 */      for (; 
/* 1374:1370 */          i.hasNext(); 
/* 1375:     */          
/* 1376:1372 */          return true)
/* 1377:     */      {
/* 1378:     */        label9:
/* 1379:1371 */        Object ev = i.nextEntry().value;
/* 1380:1372 */        if (ev == null ? v != null : !ev.equals(v)) break label9;
/* 1381:     */      }
/* 1382:1374 */      return false;
/* 1383:     */    }
/* 1384:     */    
/* 1385:     */    public V get(float k)
/* 1386:     */    {
/* 1387:1379 */      float kk = k;
/* 1388:1380 */      Float2ObjectRBTreeMap.Entry<V> e; return (in(kk)) && ((e = Float2ObjectRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1389:     */    }
/* 1390:     */    
/* 1391:1383 */    public V put(float k, V v) { Float2ObjectRBTreeMap.this.modified = false;
/* 1392:1384 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1393:1385 */      V oldValue = Float2ObjectRBTreeMap.this.put(k, v);
/* 1394:1386 */      return Float2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1395:     */    }
/* 1396:     */    
/* 1397:1389 */    public V put(Float ok, V ov) { V oldValue = put(ok.floatValue(), ov);
/* 1398:1390 */      return Float2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1399:     */    }
/* 1400:     */    
/* 1401:     */    public V remove(float k) {
/* 1402:1394 */      Float2ObjectRBTreeMap.this.modified = false;
/* 1403:1395 */      if (!in(k)) return this.defRetValue;
/* 1404:1396 */      V oldValue = Float2ObjectRBTreeMap.this.remove(k);
/* 1405:1397 */      return Float2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1406:     */    }
/* 1407:     */    
/* 1408:1400 */    public V remove(Object ok) { V oldValue = remove(((Float)ok).floatValue());
/* 1409:1401 */      return Float2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1410:     */    }
/* 1411:     */    
/* 1412:1404 */    public int size() { Float2ObjectRBTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1413:1405 */      int n = 0;
/* 1414:1406 */      while (i.hasNext()) {
/* 1415:1407 */        n++;
/* 1416:1408 */        i.nextEntry();
/* 1417:     */      }
/* 1418:1410 */      return n;
/* 1419:     */    }
/* 1420:     */    
/* 1421:1413 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1422:     */    
/* 1424:1416 */    public FloatComparator comparator() { return Float2ObjectRBTreeMap.this.actualComparator; }
/* 1425:     */    
/* 1426:     */    public Float2ObjectSortedMap<V> headMap(float to) {
/* 1427:1419 */      if (this.top) return new Submap(Float2ObjectRBTreeMap.this, this.from, this.bottom, to, false);
/* 1428:1420 */      return Float2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Float2ObjectRBTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1429:     */    }
/* 1430:     */    
/* 1431:1423 */    public Float2ObjectSortedMap<V> tailMap(float from) { if (this.bottom) return new Submap(Float2ObjectRBTreeMap.this, from, false, this.to, this.top);
/* 1432:1424 */      return Float2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Float2ObjectRBTreeMap.this, from, false, this.to, this.top) : this;
/* 1433:     */    }
/* 1434:     */    
/* 1435:1427 */    public Float2ObjectSortedMap<V> subMap(float from, float to) { if ((this.top) && (this.bottom)) return new Submap(Float2ObjectRBTreeMap.this, from, false, to, false);
/* 1436:1428 */      if (!this.top) to = Float2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1437:1429 */      if (!this.bottom) from = Float2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1438:1430 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1439:1431 */      return new Submap(Float2ObjectRBTreeMap.this, from, false, to, false);
/* 1440:     */    }
/* 1441:     */    
/* 1444:     */    public Float2ObjectRBTreeMap.Entry<V> firstEntry()
/* 1445:     */    {
/* 1446:1438 */      if (Float2ObjectRBTreeMap.this.tree == null) return null;
/* 1447:     */      Float2ObjectRBTreeMap.Entry<V> e;
/* 1448:     */      Float2ObjectRBTreeMap.Entry<V> e;
/* 1449:1441 */      if (this.bottom) { e = Float2ObjectRBTreeMap.this.firstEntry;
/* 1450:     */      } else {
/* 1451:1443 */        e = Float2ObjectRBTreeMap.this.locateKey(this.from);
/* 1452:     */        
/* 1453:1445 */        if (Float2ObjectRBTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1454:     */        }
/* 1455:     */      }
/* 1456:1448 */      if ((e == null) || ((!this.top) && (Float2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1457:1449 */      return e;
/* 1458:     */    }
/* 1459:     */    
/* 1462:     */    public Float2ObjectRBTreeMap.Entry<V> lastEntry()
/* 1463:     */    {
/* 1464:1456 */      if (Float2ObjectRBTreeMap.this.tree == null) return null;
/* 1465:     */      Float2ObjectRBTreeMap.Entry<V> e;
/* 1466:     */      Float2ObjectRBTreeMap.Entry<V> e;
/* 1467:1459 */      if (this.top) { e = Float2ObjectRBTreeMap.this.lastEntry;
/* 1468:     */      } else {
/* 1469:1461 */        e = Float2ObjectRBTreeMap.this.locateKey(this.to);
/* 1470:     */        
/* 1471:1463 */        if (Float2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1472:     */        }
/* 1473:     */      }
/* 1474:1466 */      if ((e == null) || ((!this.bottom) && (Float2ObjectRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1475:1467 */      return e;
/* 1476:     */    }
/* 1477:     */    
/* 1478:1470 */    public float firstFloatKey() { Float2ObjectRBTreeMap.Entry<V> e = firstEntry();
/* 1479:1471 */      if (e == null) throw new NoSuchElementException();
/* 1480:1472 */      return e.key;
/* 1481:     */    }
/* 1482:     */    
/* 1483:1475 */    public float lastFloatKey() { Float2ObjectRBTreeMap.Entry<V> e = lastEntry();
/* 1484:1476 */      if (e == null) throw new NoSuchElementException();
/* 1485:1477 */      return e.key;
/* 1486:     */    }
/* 1487:     */    
/* 1488:1480 */    public Float firstKey() { Float2ObjectRBTreeMap.Entry<V> e = firstEntry();
/* 1489:1481 */      if (e == null) throw new NoSuchElementException();
/* 1490:1482 */      return e.getKey();
/* 1491:     */    }
/* 1492:     */    
/* 1493:1485 */    public Float lastKey() { Float2ObjectRBTreeMap.Entry<V> e = lastEntry();
/* 1494:1486 */      if (e == null) throw new NoSuchElementException();
/* 1495:1487 */      return e.getKey();
/* 1496:     */    }
/* 1497:     */    
/* 1500:     */    private class SubmapIterator
/* 1501:     */      extends Float2ObjectRBTreeMap.TreeIterator
/* 1502:     */    {
/* 1503:     */      SubmapIterator()
/* 1504:     */      {
/* 1505:1497 */        super();
/* 1506:1498 */        this.next = Float2ObjectRBTreeMap.Submap.this.firstEntry();
/* 1507:     */      }
/* 1508:     */      
/* 1509:1501 */      SubmapIterator(float k) { this();
/* 1510:1502 */        if (this.next != null)
/* 1511:1503 */          if ((!Float2ObjectRBTreeMap.Submap.this.bottom) && (Float2ObjectRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1512:1504 */          } else if ((!Float2ObjectRBTreeMap.Submap.this.top) && (Float2ObjectRBTreeMap.this.compare(k, (this.prev = Float2ObjectRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1513:     */          } else {
/* 1514:1506 */            this.next = Float2ObjectRBTreeMap.this.locateKey(k);
/* 1515:1507 */            if (Float2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1516:1508 */              this.prev = this.next;
/* 1517:1509 */              this.next = this.next.next();
/* 1518:     */            } else {
/* 1519:1511 */              this.prev = this.next.prev();
/* 1520:     */            }
/* 1521:     */          }
/* 1522:     */      }
/* 1523:     */      
/* 1524:1516 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1525:1517 */        if ((!Float2ObjectRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Float2ObjectRBTreeMap.this.compare(this.prev.key, Float2ObjectRBTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1526:     */      }
/* 1527:     */      
/* 1528:1520 */      void updateNext() { this.next = this.next.next();
/* 1529:1521 */        if ((!Float2ObjectRBTreeMap.Submap.this.top) && (this.next != null) && (Float2ObjectRBTreeMap.this.compare(this.next.key, Float2ObjectRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1530:     */      }
/* 1531:     */    }
/* 1532:     */    
/* 1533:1525 */    private class SubmapEntryIterator extends Float2ObjectRBTreeMap<V>.Submap.SubmapIterator implements ObjectListIterator<Float2ObjectMap.Entry<V>> { SubmapEntryIterator() { super(); }
/* 1534:     */      
/* 1535:1527 */      SubmapEntryIterator(float k) { super(k); }
/* 1536:     */      
/* 1537:1529 */      public Float2ObjectMap.Entry<V> next() { return nextEntry(); }
/* 1538:1530 */      public Float2ObjectMap.Entry<V> previous() { return previousEntry(); }
/* 1539:1531 */      public void set(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1540:1532 */      public void add(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1541:     */    }
/* 1542:     */    
/* 1547:     */    private final class SubmapKeyIterator
/* 1548:     */      extends Float2ObjectRBTreeMap.Submap.SubmapIterator
/* 1549:     */      implements FloatListIterator
/* 1550:     */    {
/* 1551:1543 */      public SubmapKeyIterator() { super(); }
/* 1552:1544 */      public SubmapKeyIterator(float from) { super(from); }
/* 1553:1545 */      public float nextFloat() { return nextEntry().key; }
/* 1554:1546 */      public float previousFloat() { return previousEntry().key; }
/* 1555:1547 */      public void set(float k) { throw new UnsupportedOperationException(); }
/* 1556:1548 */      public void add(float k) { throw new UnsupportedOperationException(); }
/* 1557:1549 */      public Float next() { return Float.valueOf(nextEntry().key); }
/* 1558:1550 */      public Float previous() { return Float.valueOf(previousEntry().key); }
/* 1559:1551 */      public void set(Float ok) { throw new UnsupportedOperationException(); }
/* 1560:1552 */      public void add(Float ok) { throw new UnsupportedOperationException(); }
/* 1561:     */    }
/* 1562:     */    
/* 1566:     */    private final class SubmapValueIterator
/* 1567:     */      extends Float2ObjectRBTreeMap<V>.Submap.SubmapIterator
/* 1568:     */      implements ObjectListIterator<V>
/* 1569:     */    {
/* 1570:1562 */      private SubmapValueIterator() { super(); }
/* 1571:1563 */      public V next() { return nextEntry().value; }
/* 1572:1564 */      public V previous() { return previousEntry().value; }
/* 1573:1565 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1574:1566 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1575:     */    }
/* 1576:     */  }
/* 1577:     */  
/* 1581:     */  public Float2ObjectRBTreeMap<V> clone()
/* 1582:     */  {
/* 1583:     */    Float2ObjectRBTreeMap<V> c;
/* 1584:     */    
/* 1586:     */    try
/* 1587:     */    {
/* 1588:1580 */      c = (Float2ObjectRBTreeMap)super.clone();
/* 1589:     */    }
/* 1590:     */    catch (CloneNotSupportedException cantHappen) {
/* 1591:1583 */      throw new InternalError();
/* 1592:     */    }
/* 1593:1585 */    c.keys = null;
/* 1594:1586 */    c.values = null;
/* 1595:1587 */    c.entries = null;
/* 1596:1588 */    c.allocatePaths();
/* 1597:1589 */    if (this.count != 0)
/* 1598:     */    {
/* 1599:1591 */      Entry<V> rp = new Entry();Entry<V> rq = new Entry();
/* 1600:1592 */      Entry<V> p = rp;
/* 1601:1593 */      rp.left(this.tree);
/* 1602:1594 */      Entry<V> q = rq;
/* 1603:1595 */      rq.pred(null);
/* 1604:     */      for (;;) {
/* 1605:1597 */        if (!p.pred()) {
/* 1606:1598 */          Entry<V> e = p.left.clone();
/* 1607:1599 */          e.pred(q.left);
/* 1608:1600 */          e.succ(q);
/* 1609:1601 */          q.left(e);
/* 1610:1602 */          p = p.left;
/* 1611:1603 */          q = q.left;
/* 1612:     */        }
/* 1613:     */        else {
/* 1614:1606 */          while (p.succ()) {
/* 1615:1607 */            p = p.right;
/* 1616:1608 */            if (p == null) {
/* 1617:1609 */              q.right = null;
/* 1618:1610 */              c.tree = rq.left;
/* 1619:1611 */              c.firstEntry = c.tree;
/* 1620:1612 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1621:1613 */              c.lastEntry = c.tree;
/* 1622:1614 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1623:1615 */              return c;
/* 1624:     */            }
/* 1625:1617 */            q = q.right;
/* 1626:     */          }
/* 1627:1619 */          p = p.right;
/* 1628:1620 */          q = q.right;
/* 1629:     */        }
/* 1630:1622 */        if (!p.succ()) {
/* 1631:1623 */          Entry<V> e = p.right.clone();
/* 1632:1624 */          e.succ(q.right);
/* 1633:1625 */          e.pred(q);
/* 1634:1626 */          q.right(e);
/* 1635:     */        }
/* 1636:     */      }
/* 1637:     */    }
/* 1638:1630 */    return c;
/* 1639:     */  }
/* 1640:     */  
/* 1641:1633 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1642:1634 */    Float2ObjectRBTreeMap<V>.EntryIterator i = new EntryIterator();
/* 1643:     */    
/* 1644:1636 */    s.defaultWriteObject();
/* 1645:1637 */    while (n-- != 0) {
/* 1646:1638 */      Entry<V> e = i.nextEntry();
/* 1647:1639 */      s.writeFloat(e.key);
/* 1648:1640 */      s.writeObject(e.value);
/* 1649:     */    }
/* 1650:     */  }
/* 1651:     */  
/* 1657:     */  private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/* 1658:     */    throws IOException, ClassNotFoundException
/* 1659:     */  {
/* 1660:1652 */    if (n == 1) {
/* 1661:1653 */      Entry<V> top = new Entry(s.readFloat(), s.readObject());
/* 1662:1654 */      top.pred(pred);
/* 1663:1655 */      top.succ(succ);
/* 1664:1656 */      top.black(true);
/* 1665:1657 */      return top;
/* 1666:     */    }
/* 1667:1659 */    if (n == 2)
/* 1668:     */    {
/* 1670:1662 */      Entry<V> top = new Entry(s.readFloat(), s.readObject());
/* 1671:1663 */      top.black(true);
/* 1672:1664 */      top.right(new Entry(s.readFloat(), s.readObject()));
/* 1673:1665 */      top.right.pred(top);
/* 1674:1666 */      top.pred(pred);
/* 1675:1667 */      top.right.succ(succ);
/* 1676:1668 */      return top;
/* 1677:     */    }
/* 1678:     */    
/* 1679:1671 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1680:1672 */    Entry<V> top = new Entry();
/* 1681:1673 */    top.left(readTree(s, leftN, pred, top));
/* 1682:1674 */    top.key = s.readFloat();
/* 1683:1675 */    top.value = s.readObject();
/* 1684:1676 */    top.black(true);
/* 1685:1677 */    top.right(readTree(s, rightN, top, succ));
/* 1686:1678 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1687:1679 */    return top;
/* 1688:     */  }
/* 1689:     */  
/* 1690:1682 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1691:     */    
/* 1693:1685 */    setActualComparator();
/* 1694:1686 */    allocatePaths();
/* 1695:1687 */    if (this.count != 0) {
/* 1696:1688 */      this.tree = readTree(s, this.count, null, null);
/* 1697:     */      
/* 1698:1690 */      Entry<V> e = this.tree;
/* 1699:1691 */      while (e.left() != null) e = e.left();
/* 1700:1692 */      this.firstEntry = e;
/* 1701:1693 */      e = this.tree;
/* 1702:1694 */      while (e.right() != null) e = e.right();
/* 1703:1695 */      this.lastEntry = e;
/* 1704:     */    }
/* 1705:     */  }
/* 1706:     */  
/* 1707:     */  private void checkNodePath() {}
/* 1708:     */  
/* 1709:1701 */  private int checkTree(Entry<V> e, int d, int D) { return 0; }
/* 1710:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ObjectRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */