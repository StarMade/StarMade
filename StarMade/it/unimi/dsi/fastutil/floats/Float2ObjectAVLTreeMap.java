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
/*   71:     */public class Float2ObjectAVLTreeMap<V>
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
/*   88:     */  
/*   89:     */  public Float2ObjectAVLTreeMap()
/*   90:     */  {
/*   91:  91 */    allocatePaths();
/*   92:     */    
/*   96:  96 */    this.tree = null;
/*   97:  97 */    this.count = 0;
/*   98:     */  }
/*   99:     */  
/*  112:     */  private void setActualComparator()
/*  113:     */  {
/*  114: 114 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof FloatComparator))) this.actualComparator = ((FloatComparator)this.storedComparator); else {
/*  115: 115 */      this.actualComparator = new FloatComparator() {
/*  116:     */        public int compare(float k1, float k2) {
/*  117: 117 */          return Float2ObjectAVLTreeMap.this.storedComparator.compare(Float.valueOf(k1), Float.valueOf(k2));
/*  118:     */        }
/*  119:     */        
/*  120: 120 */        public int compare(Float ok1, Float ok2) { return Float2ObjectAVLTreeMap.this.storedComparator.compare(ok1, ok2); }
/*  121:     */      };
/*  122:     */    }
/*  123:     */  }
/*  124:     */  
/*  131:     */  public Float2ObjectAVLTreeMap(Comparator<? super Float> c)
/*  132:     */  {
/*  133: 133 */    this();
/*  134: 134 */    this.storedComparator = c;
/*  135: 135 */    setActualComparator();
/*  136:     */  }
/*  137:     */  
/*  143:     */  public Float2ObjectAVLTreeMap(Map<? extends Float, ? extends V> m)
/*  144:     */  {
/*  145: 145 */    this();
/*  146: 146 */    putAll(m);
/*  147:     */  }
/*  148:     */  
/*  153:     */  public Float2ObjectAVLTreeMap(SortedMap<Float, V> m)
/*  154:     */  {
/*  155: 155 */    this(m.comparator());
/*  156: 156 */    putAll(m);
/*  157:     */  }
/*  158:     */  
/*  163:     */  public Float2ObjectAVLTreeMap(Float2ObjectMap<? extends V> m)
/*  164:     */  {
/*  165: 165 */    this();
/*  166: 166 */    putAll(m);
/*  167:     */  }
/*  168:     */  
/*  173:     */  public Float2ObjectAVLTreeMap(Float2ObjectSortedMap<V> m)
/*  174:     */  {
/*  175: 175 */    this(m.comparator());
/*  176: 176 */    putAll(m);
/*  177:     */  }
/*  178:     */  
/*  186:     */  public Float2ObjectAVLTreeMap(float[] k, V[] v, Comparator<? super Float> c)
/*  187:     */  {
/*  188: 188 */    this(c);
/*  189: 189 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  190: 190 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  191:     */    }
/*  192:     */  }
/*  193:     */  
/*  199:     */  public Float2ObjectAVLTreeMap(float[] k, V[] v)
/*  200:     */  {
/*  201: 201 */    this(k, v, null);
/*  202:     */  }
/*  203:     */  
/*  226:     */  final int compare(float k1, float k2)
/*  227:     */  {
/*  228: 228 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  229:     */  }
/*  230:     */  
/*  238:     */  final Entry<V> findKey(float k)
/*  239:     */  {
/*  240: 240 */    Entry<V> e = this.tree;
/*  241:     */    
/*  242:     */    int cmp;
/*  243: 243 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) { e = cmp < 0 ? e.left() : e.right();
/*  244:     */    }
/*  245: 245 */    return e;
/*  246:     */  }
/*  247:     */  
/*  254:     */  final Entry<V> locateKey(float k)
/*  255:     */  {
/*  256: 256 */    Entry<V> e = this.tree;Entry<V> last = this.tree;
/*  257: 257 */    int cmp = 0;
/*  258:     */    
/*  259: 259 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  260: 260 */      last = e;
/*  261: 261 */      e = cmp < 0 ? e.left() : e.right();
/*  262:     */    }
/*  263:     */    
/*  264: 264 */    return cmp == 0 ? e : last;
/*  265:     */  }
/*  266:     */  
/*  270:     */  private void allocatePaths()
/*  271:     */  {
/*  272: 272 */    this.dirPath = new boolean[48];
/*  273:     */  }
/*  274:     */  
/*  278:     */  public V put(float k, V v)
/*  279:     */  {
/*  280: 280 */    this.modified = false;
/*  281:     */    
/*  282: 282 */    if (this.tree == null) {
/*  283: 283 */      this.count += 1;
/*  284: 284 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  285: 285 */      this.modified = true;
/*  286:     */    }
/*  287:     */    else {
/*  288: 288 */      Entry<V> p = this.tree;Entry<V> q = null;Entry<V> y = this.tree;Entry<V> z = null;Entry<V> e = null;Entry<V> w = null;
/*  289: 289 */      int i = 0;
/*  290:     */      for (;;) {
/*  291:     */        int cmp;
/*  292: 292 */        if ((cmp = compare(k, p.key)) == 0) {
/*  293: 293 */          V oldValue = p.value;
/*  294: 294 */          p.value = v;
/*  295: 295 */          return oldValue;
/*  296:     */        }
/*  297:     */        
/*  298: 298 */        if (p.balance() != 0) {
/*  299: 299 */          i = 0;
/*  300: 300 */          z = q;
/*  301: 301 */          y = p;
/*  302:     */        }
/*  303:     */        
/*  304: 304 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  305: 305 */          if (p.succ()) {
/*  306: 306 */            this.count += 1;
/*  307: 307 */            e = new Entry(k, v);
/*  308:     */            
/*  309: 309 */            this.modified = true;
/*  310: 310 */            if (p.right == null) { this.lastEntry = e;
/*  311:     */            }
/*  312: 312 */            e.left = p;
/*  313: 313 */            e.right = p.right;
/*  314:     */            
/*  315: 315 */            p.right(e);
/*  316:     */            
/*  317: 317 */            break;
/*  318:     */          }
/*  319:     */          
/*  320: 320 */          q = p;
/*  321: 321 */          p = p.right;
/*  322:     */        }
/*  323:     */        else {
/*  324: 324 */          if (p.pred()) {
/*  325: 325 */            this.count += 1;
/*  326: 326 */            e = new Entry(k, v);
/*  327:     */            
/*  328: 328 */            this.modified = true;
/*  329: 329 */            if (p.left == null) { this.firstEntry = e;
/*  330:     */            }
/*  331: 331 */            e.right = p;
/*  332: 332 */            e.left = p.left;
/*  333:     */            
/*  334: 334 */            p.left(e);
/*  335:     */            
/*  336: 336 */            break;
/*  337:     */          }
/*  338:     */          
/*  339: 339 */          q = p;
/*  340: 340 */          p = p.left;
/*  341:     */        }
/*  342:     */      }
/*  343:     */      
/*  344: 344 */      p = y;
/*  345: 345 */      i = 0;
/*  346:     */      
/*  347: 347 */      while (p != e) {
/*  348: 348 */        if (this.dirPath[i] != 0) p.incBalance(); else {
/*  349: 349 */          p.decBalance();
/*  350:     */        }
/*  351: 351 */        p = this.dirPath[(i++)] != 0 ? p.right : p.left;
/*  352:     */      }
/*  353:     */      
/*  354: 354 */      if (y.balance() == -2) {
/*  355: 355 */        Entry<V> x = y.left;
/*  356:     */        
/*  357: 357 */        if (x.balance() == -1) {
/*  358: 358 */          w = x;
/*  359: 359 */          if (x.succ()) {
/*  360: 360 */            x.succ(false);
/*  361: 361 */            y.pred(x);
/*  362:     */          } else {
/*  363: 363 */            y.left = x.right;
/*  364:     */          }
/*  365: 365 */          x.right = y;
/*  366: 366 */          x.balance(0);
/*  367: 367 */          y.balance(0);
/*  369:     */        }
/*  370:     */        else
/*  371:     */        {
/*  372: 372 */          w = x.right;
/*  373: 373 */          x.right = w.left;
/*  374: 374 */          w.left = x;
/*  375: 375 */          y.left = w.right;
/*  376: 376 */          w.right = y;
/*  377: 377 */          if (w.balance() == -1) {
/*  378: 378 */            x.balance(0);
/*  379: 379 */            y.balance(1);
/*  380:     */          }
/*  381: 381 */          else if (w.balance() == 0) {
/*  382: 382 */            x.balance(0);
/*  383: 383 */            y.balance(0);
/*  384:     */          }
/*  385:     */          else {
/*  386: 386 */            x.balance(-1);
/*  387: 387 */            y.balance(0);
/*  388:     */          }
/*  389: 389 */          w.balance(0);
/*  390:     */          
/*  392: 392 */          if (w.pred()) {
/*  393: 393 */            x.succ(w);
/*  394: 394 */            w.pred(false);
/*  395:     */          }
/*  396: 396 */          if (w.succ()) {
/*  397: 397 */            y.pred(w);
/*  398: 398 */            w.succ(false);
/*  399:     */          }
/*  400:     */          
/*  401:     */        }
/*  402:     */      }
/*  403: 403 */      else if (y.balance() == 2) {
/*  404: 404 */        Entry<V> x = y.right;
/*  405:     */        
/*  406: 406 */        if (x.balance() == 1) {
/*  407: 407 */          w = x;
/*  408: 408 */          if (x.pred()) {
/*  409: 409 */            x.pred(false);
/*  410: 410 */            y.succ(x);
/*  411:     */          } else {
/*  412: 412 */            y.right = x.left;
/*  413:     */          }
/*  414: 414 */          x.left = y;
/*  415: 415 */          x.balance(0);
/*  416: 416 */          y.balance(0);
/*  418:     */        }
/*  419:     */        else
/*  420:     */        {
/*  421: 421 */          w = x.left;
/*  422: 422 */          x.left = w.right;
/*  423: 423 */          w.right = x;
/*  424: 424 */          y.right = w.left;
/*  425: 425 */          w.left = y;
/*  426: 426 */          if (w.balance() == 1) {
/*  427: 427 */            x.balance(0);
/*  428: 428 */            y.balance(-1);
/*  429:     */          }
/*  430: 430 */          else if (w.balance() == 0) {
/*  431: 431 */            x.balance(0);
/*  432: 432 */            y.balance(0);
/*  433:     */          }
/*  434:     */          else {
/*  435: 435 */            x.balance(1);
/*  436: 436 */            y.balance(0);
/*  437:     */          }
/*  438: 438 */          w.balance(0);
/*  439:     */          
/*  441: 441 */          if (w.pred()) {
/*  442: 442 */            y.succ(w);
/*  443: 443 */            w.pred(false);
/*  444:     */          }
/*  445: 445 */          if (w.succ()) {
/*  446: 446 */            x.pred(w);
/*  447: 447 */            w.succ(false);
/*  448:     */          }
/*  449:     */        }
/*  450:     */      }
/*  451:     */      else {
/*  452: 452 */        return this.defRetValue;
/*  453:     */      }
/*  454: 454 */      if (z == null) { this.tree = w;
/*  455:     */      }
/*  456: 456 */      else if (z.left == y) z.left = w; else {
/*  457: 457 */        z.right = w;
/*  458:     */      }
/*  459:     */    }
/*  460:     */    
/*  462: 462 */    return this.defRetValue;
/*  463:     */  }
/*  464:     */  
/*  470:     */  private Entry<V> parent(Entry<V> e)
/*  471:     */  {
/*  472: 472 */    if (e == this.tree) { return null;
/*  473:     */    }
/*  474:     */    Entry<V> y;
/*  475: 475 */    Entry<V> x = y = e;
/*  476:     */    for (;;)
/*  477:     */    {
/*  478: 478 */      if (y.succ()) {
/*  479: 479 */        Entry<V> p = y.right;
/*  480: 480 */        if ((p == null) || (p.left != e)) {
/*  481: 481 */          while (!x.pred()) x = x.left;
/*  482: 482 */          p = x.left;
/*  483:     */        }
/*  484: 484 */        return p;
/*  485:     */      }
/*  486: 486 */      if (x.pred()) {
/*  487: 487 */        Entry<V> p = x.left;
/*  488: 488 */        if ((p == null) || (p.right != e)) {
/*  489: 489 */          while (!y.succ()) y = y.right;
/*  490: 490 */          p = y.right;
/*  491:     */        }
/*  492: 492 */        return p;
/*  493:     */      }
/*  494:     */      
/*  495: 495 */      x = x.left;
/*  496: 496 */      y = y.right;
/*  497:     */    }
/*  498:     */  }
/*  499:     */  
/*  504:     */  public V remove(float k)
/*  505:     */  {
/*  506: 506 */    this.modified = false;
/*  507:     */    
/*  508: 508 */    if (this.tree == null) { return this.defRetValue;
/*  509:     */    }
/*  510:     */    
/*  511: 511 */    Entry<V> p = this.tree;Entry<V> q = null;
/*  512: 512 */    boolean dir = false;
/*  513: 513 */    float kk = k;
/*  514:     */    
/*  515:     */    int cmp;
/*  516: 516 */    while ((cmp = compare(kk, p.key)) != 0) {
/*  517: 517 */      if ((dir = cmp > 0 ? 1 : 0) != 0) {
/*  518: 518 */        q = p;
/*  519: 519 */        if ((p = p.right()) == null) return this.defRetValue;
/*  520:     */      }
/*  521:     */      else {
/*  522: 522 */        q = p;
/*  523: 523 */        if ((p = p.left()) == null) { return this.defRetValue;
/*  524:     */        }
/*  525:     */      }
/*  526:     */    }
/*  527: 527 */    if (p.left == null) this.firstEntry = p.next();
/*  528: 528 */    if (p.right == null) { this.lastEntry = p.prev();
/*  529:     */    }
/*  530: 530 */    if (p.succ()) {
/*  531: 531 */      if (p.pred()) {
/*  532: 532 */        if (q != null) {
/*  533: 533 */          if (dir) q.succ(p.right); else
/*  534: 534 */            q.pred(p.left);
/*  535:     */        } else {
/*  536: 536 */          this.tree = (dir ? p.right : p.left);
/*  537:     */        }
/*  538:     */      } else {
/*  539: 539 */        p.prev().right = p.right;
/*  540:     */        
/*  541: 541 */        if (q != null) {
/*  542: 542 */          if (dir) q.right = p.left; else
/*  543: 543 */            q.left = p.left;
/*  544:     */        } else {
/*  545: 545 */          this.tree = p.left;
/*  546:     */        }
/*  547:     */      }
/*  548:     */    } else {
/*  549: 549 */      Entry<V> r = p.right;
/*  550:     */      
/*  551: 551 */      if (r.pred()) {
/*  552: 552 */        r.left = p.left;
/*  553: 553 */        r.pred(p.pred());
/*  554: 554 */        if (!r.pred()) r.prev().right = r;
/*  555: 555 */        if (q != null) {
/*  556: 556 */          if (dir) q.right = r; else
/*  557: 557 */            q.left = r;
/*  558:     */        } else {
/*  559: 559 */          this.tree = r;
/*  560:     */        }
/*  561: 561 */        r.balance(p.balance());
/*  562: 562 */        q = r;
/*  563: 563 */        dir = true;
/*  564:     */      }
/*  565:     */      else
/*  566:     */      {
/*  567:     */        Entry<V> s;
/*  568:     */        for (;;)
/*  569:     */        {
/*  570: 570 */          s = r.left;
/*  571: 571 */          if (s.pred()) break;
/*  572: 572 */          r = s;
/*  573:     */        }
/*  574:     */        
/*  575: 575 */        if (s.succ()) r.pred(s); else {
/*  576: 576 */          r.left = s.right;
/*  577:     */        }
/*  578: 578 */        s.left = p.left;
/*  579:     */        
/*  580: 580 */        if (!p.pred()) {
/*  581: 581 */          p.prev().right = s;
/*  582: 582 */          s.pred(false);
/*  583:     */        }
/*  584:     */        
/*  585: 585 */        s.right = p.right;
/*  586: 586 */        s.succ(false);
/*  587:     */        
/*  588: 588 */        if (q != null) {
/*  589: 589 */          if (dir) q.right = s; else
/*  590: 590 */            q.left = s;
/*  591:     */        } else {
/*  592: 592 */          this.tree = s;
/*  593:     */        }
/*  594: 594 */        s.balance(p.balance());
/*  595: 595 */        q = r;
/*  596: 596 */        dir = false;
/*  597:     */      }
/*  598:     */    }
/*  599:     */    
/*  602: 602 */    while (q != null) {
/*  603: 603 */      Entry<V> y = q;
/*  604: 604 */      q = parent(y);
/*  605:     */      
/*  606: 606 */      if (!dir) {
/*  607: 607 */        dir = (q != null) && (q.left != y);
/*  608: 608 */        y.incBalance();
/*  609:     */        
/*  610: 610 */        if (y.balance() == 1) break;
/*  611: 611 */        if (y.balance() == 2)
/*  612:     */        {
/*  613: 613 */          Entry<V> x = y.right;
/*  614:     */          
/*  616: 616 */          if (x.balance() == -1)
/*  617:     */          {
/*  621: 621 */            Entry<V> w = x.left;
/*  622: 622 */            x.left = w.right;
/*  623: 623 */            w.right = x;
/*  624: 624 */            y.right = w.left;
/*  625: 625 */            w.left = y;
/*  626:     */            
/*  627: 627 */            if (w.balance() == 1) {
/*  628: 628 */              x.balance(0);
/*  629: 629 */              y.balance(-1);
/*  630:     */            }
/*  631: 631 */            else if (w.balance() == 0) {
/*  632: 632 */              x.balance(0);
/*  633: 633 */              y.balance(0);
/*  635:     */            }
/*  636:     */            else
/*  637:     */            {
/*  638: 638 */              x.balance(1);
/*  639: 639 */              y.balance(0);
/*  640:     */            }
/*  641:     */            
/*  642: 642 */            w.balance(0);
/*  643:     */            
/*  644: 644 */            if (w.pred()) {
/*  645: 645 */              y.succ(w);
/*  646: 646 */              w.pred(false);
/*  647:     */            }
/*  648: 648 */            if (w.succ()) {
/*  649: 649 */              x.pred(w);
/*  650: 650 */              w.succ(false);
/*  651:     */            }
/*  652:     */            
/*  653: 653 */            if (q != null) {
/*  654: 654 */              if (dir) q.right = w; else
/*  655: 655 */                q.left = w;
/*  656:     */            } else {
/*  657: 657 */              this.tree = w;
/*  658:     */            }
/*  659:     */          } else {
/*  660: 660 */            if (q != null) {
/*  661: 661 */              if (dir) q.right = x; else
/*  662: 662 */                q.left = x;
/*  663:     */            } else {
/*  664: 664 */              this.tree = x;
/*  665:     */            }
/*  666: 666 */            if (x.balance() == 0) {
/*  667: 667 */              y.right = x.left;
/*  668: 668 */              x.left = y;
/*  669: 669 */              x.balance(-1);
/*  670: 670 */              y.balance(1);
/*  671: 671 */              break;
/*  672:     */            }
/*  673:     */            
/*  675: 675 */            if (x.pred()) {
/*  676: 676 */              y.succ(true);
/*  677: 677 */              x.pred(false);
/*  678:     */            } else {
/*  679: 679 */              y.right = x.left;
/*  680:     */            }
/*  681: 681 */            x.left = y;
/*  682: 682 */            y.balance(0);
/*  683: 683 */            x.balance(0);
/*  684:     */          }
/*  685:     */        }
/*  686:     */      }
/*  687:     */      else {
/*  688: 688 */        dir = (q != null) && (q.left != y);
/*  689: 689 */        y.decBalance();
/*  690:     */        
/*  691: 691 */        if (y.balance() == -1) break;
/*  692: 692 */        if (y.balance() == -2)
/*  693:     */        {
/*  694: 694 */          Entry<V> x = y.left;
/*  695:     */          
/*  697: 697 */          if (x.balance() == 1)
/*  698:     */          {
/*  702: 702 */            Entry<V> w = x.right;
/*  703: 703 */            x.right = w.left;
/*  704: 704 */            w.left = x;
/*  705: 705 */            y.left = w.right;
/*  706: 706 */            w.right = y;
/*  707:     */            
/*  708: 708 */            if (w.balance() == -1) {
/*  709: 709 */              x.balance(0);
/*  710: 710 */              y.balance(1);
/*  711:     */            }
/*  712: 712 */            else if (w.balance() == 0) {
/*  713: 713 */              x.balance(0);
/*  714: 714 */              y.balance(0);
/*  716:     */            }
/*  717:     */            else
/*  718:     */            {
/*  719: 719 */              x.balance(-1);
/*  720: 720 */              y.balance(0);
/*  721:     */            }
/*  722:     */            
/*  723: 723 */            w.balance(0);
/*  724:     */            
/*  725: 725 */            if (w.pred()) {
/*  726: 726 */              x.succ(w);
/*  727: 727 */              w.pred(false);
/*  728:     */            }
/*  729: 729 */            if (w.succ()) {
/*  730: 730 */              y.pred(w);
/*  731: 731 */              w.succ(false);
/*  732:     */            }
/*  733:     */            
/*  734: 734 */            if (q != null) {
/*  735: 735 */              if (dir) q.right = w; else
/*  736: 736 */                q.left = w;
/*  737:     */            } else {
/*  738: 738 */              this.tree = w;
/*  739:     */            }
/*  740:     */          } else {
/*  741: 741 */            if (q != null) {
/*  742: 742 */              if (dir) q.right = x; else
/*  743: 743 */                q.left = x;
/*  744:     */            } else {
/*  745: 745 */              this.tree = x;
/*  746:     */            }
/*  747: 747 */            if (x.balance() == 0) {
/*  748: 748 */              y.left = x.right;
/*  749: 749 */              x.right = y;
/*  750: 750 */              x.balance(1);
/*  751: 751 */              y.balance(-1);
/*  752: 752 */              break;
/*  753:     */            }
/*  754:     */            
/*  756: 756 */            if (x.succ()) {
/*  757: 757 */              y.pred(true);
/*  758: 758 */              x.succ(false);
/*  759:     */            } else {
/*  760: 760 */              y.left = x.right;
/*  761:     */            }
/*  762: 762 */            x.right = y;
/*  763: 763 */            y.balance(0);
/*  764: 764 */            x.balance(0);
/*  765:     */          }
/*  766:     */        }
/*  767:     */      }
/*  768:     */    }
/*  769:     */    
/*  770: 770 */    this.modified = true;
/*  771: 771 */    this.count -= 1;
/*  772:     */    
/*  773: 773 */    return p.value;
/*  774:     */  }
/*  775:     */  
/*  777:     */  public V put(Float ok, V ov)
/*  778:     */  {
/*  779: 779 */    V oldValue = put(ok.floatValue(), ov);
/*  780: 780 */    return this.modified ? this.defRetValue : oldValue;
/*  781:     */  }
/*  782:     */  
/*  786:     */  public V remove(Object ok)
/*  787:     */  {
/*  788: 788 */    V oldValue = remove(((Float)ok).floatValue());
/*  789: 789 */    return this.modified ? oldValue : this.defRetValue;
/*  790:     */  }
/*  791:     */  
/*  793:     */  public boolean containsValue(Object v)
/*  794:     */  {
/*  795: 795 */    Float2ObjectAVLTreeMap<V>.ValueIterator i = new ValueIterator(null);
/*  796:     */    
/*  798: 798 */    int j = this.count;
/*  799: 799 */    for (; j-- != 0; 
/*  800:     */        
/*  801: 801 */        return true)
/*  802:     */    {
/*  803:     */      label16:
/*  804: 800 */      V ev = i.next();
/*  805: 801 */      if (ev == null ? v != null : !ev.equals(v))
/*  806:     */        break label16;
/*  807:     */    }
/*  808: 804 */    return false;
/*  809:     */  }
/*  810:     */  
/*  811:     */  public void clear() {
/*  812: 808 */    this.count = 0;
/*  813: 809 */    this.tree = null;
/*  814: 810 */    this.entries = null;
/*  815: 811 */    this.values = null;
/*  816: 812 */    this.keys = null;
/*  817: 813 */    this.firstEntry = (this.lastEntry = null);
/*  818:     */  }
/*  819:     */  
/*  822:     */  private static final class Entry<V>
/*  823:     */    implements Cloneable, Float2ObjectMap.Entry<V>
/*  824:     */  {
/*  825:     */    private static final int SUCC_MASK = -2147483648;
/*  826:     */    
/*  828:     */    private static final int PRED_MASK = 1073741824;
/*  829:     */    
/*  831:     */    private static final int BALANCE_MASK = 255;
/*  832:     */    
/*  834:     */    float key;
/*  835:     */    
/*  837:     */    V value;
/*  838:     */    
/*  840:     */    Entry<V> left;
/*  841:     */    
/*  843:     */    Entry<V> right;
/*  844:     */    
/*  846:     */    int info;
/*  847:     */    
/*  849:     */    Entry() {}
/*  850:     */    
/*  852:     */    Entry(float k, V v)
/*  853:     */    {
/*  854: 850 */      this.key = k;
/*  855: 851 */      this.value = v;
/*  856: 852 */      this.info = -1073741824;
/*  857:     */    }
/*  858:     */    
/*  863:     */    Entry<V> left()
/*  864:     */    {
/*  865: 861 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  866:     */    }
/*  867:     */    
/*  872:     */    Entry<V> right()
/*  873:     */    {
/*  874: 870 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  875:     */    }
/*  876:     */    
/*  879:     */    boolean pred()
/*  880:     */    {
/*  881: 877 */      return (this.info & 0x40000000) != 0;
/*  882:     */    }
/*  883:     */    
/*  886:     */    boolean succ()
/*  887:     */    {
/*  888: 884 */      return (this.info & 0x80000000) != 0;
/*  889:     */    }
/*  890:     */    
/*  893:     */    void pred(boolean pred)
/*  894:     */    {
/*  895: 891 */      if (pred) this.info |= 1073741824; else {
/*  896: 892 */        this.info &= -1073741825;
/*  897:     */      }
/*  898:     */    }
/*  899:     */    
/*  901:     */    void succ(boolean succ)
/*  902:     */    {
/*  903: 899 */      if (succ) this.info |= -2147483648; else {
/*  904: 900 */        this.info &= 2147483647;
/*  905:     */      }
/*  906:     */    }
/*  907:     */    
/*  909:     */    void pred(Entry<V> pred)
/*  910:     */    {
/*  911: 907 */      this.info |= 1073741824;
/*  912: 908 */      this.left = pred;
/*  913:     */    }
/*  914:     */    
/*  917:     */    void succ(Entry<V> succ)
/*  918:     */    {
/*  919: 915 */      this.info |= -2147483648;
/*  920: 916 */      this.right = succ;
/*  921:     */    }
/*  922:     */    
/*  925:     */    void left(Entry<V> left)
/*  926:     */    {
/*  927: 923 */      this.info &= -1073741825;
/*  928: 924 */      this.left = left;
/*  929:     */    }
/*  930:     */    
/*  933:     */    void right(Entry<V> right)
/*  934:     */    {
/*  935: 931 */      this.info &= 2147483647;
/*  936: 932 */      this.right = right;
/*  937:     */    }
/*  938:     */    
/*  941:     */    int balance()
/*  942:     */    {
/*  943: 939 */      return (byte)this.info;
/*  944:     */    }
/*  945:     */    
/*  948:     */    void balance(int level)
/*  949:     */    {
/*  950: 946 */      this.info &= -256;
/*  951: 947 */      this.info |= level & 0xFF;
/*  952:     */    }
/*  953:     */    
/*  954:     */    void incBalance()
/*  955:     */    {
/*  956: 952 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  957:     */    }
/*  958:     */    
/*  959:     */    protected void decBalance()
/*  960:     */    {
/*  961: 957 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*  962:     */    }
/*  963:     */    
/*  968:     */    Entry<V> next()
/*  969:     */    {
/*  970: 966 */      Entry<V> next = this.right;
/*  971: 967 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  972: 968 */      return next;
/*  973:     */    }
/*  974:     */    
/*  979:     */    Entry<V> prev()
/*  980:     */    {
/*  981: 977 */      Entry<V> prev = this.left;
/*  982: 978 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  983: 979 */      return prev;
/*  984:     */    }
/*  985:     */    
/*  986:     */    public Float getKey() {
/*  987: 983 */      return Float.valueOf(this.key);
/*  988:     */    }
/*  989:     */    
/*  990:     */    public float getFloatKey()
/*  991:     */    {
/*  992: 988 */      return this.key;
/*  993:     */    }
/*  994:     */    
/*  995:     */    public V getValue()
/*  996:     */    {
/*  997: 993 */      return this.value;
/*  998:     */    }
/*  999:     */    
/* 1005:     */    public V setValue(V value)
/* 1006:     */    {
/* 1007:1003 */      V oldValue = this.value;
/* 1008:1004 */      this.value = value;
/* 1009:1005 */      return oldValue;
/* 1010:     */    }
/* 1011:     */    
/* 1012:     */    public Entry<V> clone() {
/* 1013:     */      Entry<V> c;
/* 1014:     */      try {
/* 1015:1011 */        c = (Entry)super.clone();
/* 1016:     */      }
/* 1017:     */      catch (CloneNotSupportedException cantHappen) {
/* 1018:1014 */        throw new InternalError();
/* 1019:     */      }
/* 1020:1016 */      c.key = this.key;
/* 1021:1017 */      c.value = this.value;
/* 1022:1018 */      c.info = this.info;
/* 1023:1019 */      return c;
/* 1024:     */    }
/* 1025:     */    
/* 1026:     */    public boolean equals(Object o) {
/* 1027:1023 */      if (!(o instanceof Map.Entry)) return false;
/* 1028:1024 */      Map.Entry<Float, V> e = (Map.Entry)o;
/* 1029:1025 */      return (this.key == ((Float)e.getKey()).floatValue()) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/* 1030:     */    }
/* 1031:     */    
/* 1032:1028 */    public int hashCode() { return HashCommon.float2int(this.key) ^ (this.value == null ? 0 : this.value.hashCode()); }
/* 1033:     */    
/* 1034:     */    public String toString() {
/* 1035:1031 */      return this.key + "=>" + this.value;
/* 1036:     */    }
/* 1037:     */  }
/* 1038:     */  
/* 1069:     */  public boolean containsKey(float k)
/* 1070:     */  {
/* 1071:1067 */    return findKey(k) != null;
/* 1072:     */  }
/* 1073:     */  
/* 1074:1070 */  public int size() { return this.count; }
/* 1075:     */  
/* 1076:     */  public boolean isEmpty() {
/* 1077:1073 */    return this.count == 0;
/* 1078:     */  }
/* 1079:     */  
/* 1080:     */  public V get(float k) {
/* 1081:1077 */    Entry<V> e = findKey(k);
/* 1082:1078 */    return e == null ? this.defRetValue : e.value;
/* 1083:     */  }
/* 1084:     */  
/* 1085:1081 */  public float firstFloatKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1086:1082 */    return this.firstEntry.key;
/* 1087:     */  }
/* 1088:     */  
/* 1089:1085 */  public float lastFloatKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1090:1086 */    return this.lastEntry.key;
/* 1091:     */  }
/* 1092:     */  
/* 1095:     */  private class TreeIterator
/* 1096:     */  {
/* 1097:     */    Float2ObjectAVLTreeMap.Entry<V> prev;
/* 1098:     */    
/* 1100:     */    Float2ObjectAVLTreeMap.Entry<V> next;
/* 1101:     */    
/* 1102:     */    Float2ObjectAVLTreeMap.Entry<V> curr;
/* 1103:     */    
/* 1104:1100 */    int index = 0;
/* 1105:     */    
/* 1106:1102 */    TreeIterator() { this.next = Float2ObjectAVLTreeMap.this.firstEntry; }
/* 1107:     */    
/* 1108:     */    TreeIterator(float k) {
/* 1109:1105 */      if ((this.next = Float2ObjectAVLTreeMap.this.locateKey(k)) != null)
/* 1110:1106 */        if (Float2ObjectAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1111:1107 */          this.prev = this.next;
/* 1112:1108 */          this.next = this.next.next();
/* 1113:     */        } else {
/* 1114:1110 */          this.prev = this.next.prev();
/* 1115:     */        } }
/* 1116:     */    
/* 1117:1113 */    public boolean hasNext() { return this.next != null; }
/* 1118:1114 */    public boolean hasPrevious() { return this.prev != null; }
/* 1119:     */    
/* 1120:1116 */    void updateNext() { this.next = this.next.next(); }
/* 1121:     */    
/* 1122:     */    Float2ObjectAVLTreeMap.Entry<V> nextEntry() {
/* 1123:1119 */      if (!hasNext()) throw new NoSuchElementException();
/* 1124:1120 */      this.curr = (this.prev = this.next);
/* 1125:1121 */      this.index += 1;
/* 1126:1122 */      updateNext();
/* 1127:1123 */      return this.curr;
/* 1128:     */    }
/* 1129:     */    
/* 1130:1126 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1131:     */    
/* 1132:     */    Float2ObjectAVLTreeMap.Entry<V> previousEntry() {
/* 1133:1129 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1134:1130 */      this.curr = (this.next = this.prev);
/* 1135:1131 */      this.index -= 1;
/* 1136:1132 */      updatePrevious();
/* 1137:1133 */      return this.curr;
/* 1138:     */    }
/* 1139:     */    
/* 1140:1136 */    public int nextIndex() { return this.index; }
/* 1141:     */    
/* 1143:1139 */    public int previousIndex() { return this.index - 1; }
/* 1144:     */    
/* 1145:     */    public void remove() {
/* 1146:1142 */      if (this.curr == null) { throw new IllegalStateException();
/* 1147:     */      }
/* 1148:     */      
/* 1149:1145 */      if (this.curr == this.prev) this.index -= 1;
/* 1150:1146 */      this.next = (this.prev = this.curr);
/* 1151:1147 */      updatePrevious();
/* 1152:1148 */      updateNext();
/* 1153:1149 */      Float2ObjectAVLTreeMap.this.remove(this.curr.key);
/* 1154:1150 */      this.curr = null;
/* 1155:     */    }
/* 1156:     */    
/* 1157:1153 */    public int skip(int n) { int i = n;
/* 1158:1154 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 1159:1155 */      return n - i - 1;
/* 1160:     */    }
/* 1161:     */    
/* 1162:1158 */    public int back(int n) { int i = n;
/* 1163:1159 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1164:1160 */      return n - i - 1;
/* 1165:     */    }
/* 1166:     */  }
/* 1167:     */  
/* 1168:     */  private class EntryIterator
/* 1169:     */    extends Float2ObjectAVLTreeMap<V>.TreeIterator
/* 1170:     */    implements ObjectListIterator<Float2ObjectMap.Entry<V>>
/* 1171:     */  {
/* 1172:1168 */    EntryIterator() { super(); }
/* 1173:     */    
/* 1174:1170 */    EntryIterator(float k) { super(k); }
/* 1175:     */    
/* 1176:1172 */    public Float2ObjectMap.Entry<V> next() { return nextEntry(); }
/* 1177:1173 */    public Float2ObjectMap.Entry<V> previous() { return previousEntry(); }
/* 1178:1174 */    public void set(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1179:1175 */    public void add(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1180:     */  }
/* 1181:     */  
/* 1182:1178 */  public ObjectSortedSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1183:1179 */        final Comparator<? super Float2ObjectMap.Entry<V>> comparator = new Comparator() {
/* 1184:     */          public int compare(Float2ObjectMap.Entry<V> x, Float2ObjectMap.Entry<V> y) {
/* 1185:1181 */            return Float2ObjectAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1186:     */          }
/* 1187:     */        };
/* 1188:     */        
/* 1189:1185 */        public Comparator<? super Float2ObjectMap.Entry<V>> comparator() { return this.comparator; }
/* 1190:     */        
/* 1191:     */        public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator() {
/* 1192:1188 */          return new Float2ObjectAVLTreeMap.EntryIterator(Float2ObjectAVLTreeMap.this);
/* 1193:     */        }
/* 1194:     */        
/* 1195:1191 */        public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator(Float2ObjectMap.Entry<V> from) { return new Float2ObjectAVLTreeMap.EntryIterator(Float2ObjectAVLTreeMap.this, ((Float)from.getKey()).floatValue()); }
/* 1196:     */        
/* 1197:     */        public boolean contains(Object o)
/* 1198:     */        {
/* 1199:1195 */          if (!(o instanceof Map.Entry)) return false;
/* 1200:1196 */          Map.Entry<Float, V> e = (Map.Entry)o;
/* 1201:1197 */          Float2ObjectAVLTreeMap.Entry<V> f = Float2ObjectAVLTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1202:1198 */          return e.equals(f);
/* 1203:     */        }
/* 1204:     */        
/* 1205:     */        public boolean remove(Object o) {
/* 1206:1202 */          if (!(o instanceof Map.Entry)) return false;
/* 1207:1203 */          Map.Entry<Float, V> e = (Map.Entry)o;
/* 1208:1204 */          Float2ObjectAVLTreeMap.Entry<V> f = Float2ObjectAVLTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1209:1205 */          if (f != null) Float2ObjectAVLTreeMap.this.remove(f.key);
/* 1210:1206 */          return f != null; }
/* 1211:     */        
/* 1212:1208 */        public int size() { return Float2ObjectAVLTreeMap.this.count; }
/* 1213:1209 */        public void clear() { Float2ObjectAVLTreeMap.this.clear(); }
/* 1214:1210 */        public Float2ObjectMap.Entry<V> first() { return Float2ObjectAVLTreeMap.this.firstEntry; }
/* 1215:1211 */        public Float2ObjectMap.Entry<V> last() { return Float2ObjectAVLTreeMap.this.lastEntry; }
/* 1216:1212 */        public ObjectSortedSet<Float2ObjectMap.Entry<V>> subSet(Float2ObjectMap.Entry<V> from, Float2ObjectMap.Entry<V> to) { return Float2ObjectAVLTreeMap.this.subMap((Float)from.getKey(), (Float)to.getKey()).float2ObjectEntrySet(); }
/* 1217:1213 */        public ObjectSortedSet<Float2ObjectMap.Entry<V>> headSet(Float2ObjectMap.Entry<V> to) { return Float2ObjectAVLTreeMap.this.headMap((Float)to.getKey()).float2ObjectEntrySet(); }
/* 1218:1214 */        public ObjectSortedSet<Float2ObjectMap.Entry<V>> tailSet(Float2ObjectMap.Entry<V> from) { return Float2ObjectAVLTreeMap.this.tailMap((Float)from.getKey()).float2ObjectEntrySet(); }
/* 1219:     */      };
/* 1220:1216 */    return this.entries;
/* 1221:     */  }
/* 1222:     */  
/* 1225:     */  private final class KeyIterator
/* 1226:     */    extends Float2ObjectAVLTreeMap.TreeIterator
/* 1227:     */    implements FloatListIterator
/* 1228:     */  {
/* 1229:1225 */    public KeyIterator() { super(); }
/* 1230:1226 */    public KeyIterator(float k) { super(k); }
/* 1231:1227 */    public float nextFloat() { return nextEntry().key; }
/* 1232:1228 */    public float previousFloat() { return previousEntry().key; }
/* 1233:1229 */    public void set(float k) { throw new UnsupportedOperationException(); }
/* 1234:1230 */    public void add(float k) { throw new UnsupportedOperationException(); }
/* 1235:1231 */    public Float next() { return Float.valueOf(nextEntry().key); }
/* 1236:1232 */    public Float previous() { return Float.valueOf(previousEntry().key); }
/* 1237:1233 */    public void set(Float ok) { throw new UnsupportedOperationException(); }
/* 1238:1234 */    public void add(Float ok) { throw new UnsupportedOperationException(); }
/* 1239:     */  }
/* 1240:     */  
/* 1241:1237 */  private class KeySet extends AbstractFloat2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1242:1238 */    public FloatBidirectionalIterator iterator() { return new Float2ObjectAVLTreeMap.KeyIterator(Float2ObjectAVLTreeMap.this); }
/* 1243:1239 */    public FloatBidirectionalIterator iterator(float from) { return new Float2ObjectAVLTreeMap.KeyIterator(Float2ObjectAVLTreeMap.this, from); }
/* 1244:     */  }
/* 1245:     */  
/* 1252:     */  public FloatSortedSet keySet()
/* 1253:     */  {
/* 1254:1250 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1255:1251 */    return this.keys;
/* 1256:     */  }
/* 1257:     */  
/* 1259:     */  private final class ValueIterator
/* 1260:     */    extends Float2ObjectAVLTreeMap<V>.TreeIterator
/* 1261:     */    implements ObjectListIterator<V>
/* 1262:     */  {
/* 1263:1259 */    private ValueIterator() { super(); }
/* 1264:1260 */    public V next() { return nextEntry().value; }
/* 1265:1261 */    public V previous() { return previousEntry().value; }
/* 1266:1262 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1267:1263 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1268:     */  }
/* 1269:     */  
/* 1276:     */  public ObjectCollection<V> values()
/* 1277:     */  {
/* 1278:1274 */    if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1279:     */        public ObjectIterator<V> iterator() {
/* 1280:1276 */          return new Float2ObjectAVLTreeMap.ValueIterator(Float2ObjectAVLTreeMap.this, null);
/* 1281:     */        }
/* 1282:     */        
/* 1283:1279 */        public boolean contains(Object k) { return Float2ObjectAVLTreeMap.this.containsValue(k); }
/* 1284:     */        
/* 1285:     */        public int size() {
/* 1286:1282 */          return Float2ObjectAVLTreeMap.this.count;
/* 1287:     */        }
/* 1288:     */        
/* 1289:1285 */        public void clear() { Float2ObjectAVLTreeMap.this.clear(); }
/* 1290:     */      };
/* 1291:     */    }
/* 1292:1288 */    return this.values;
/* 1293:     */  }
/* 1294:     */  
/* 1295:1291 */  public FloatComparator comparator() { return this.actualComparator; }
/* 1296:     */  
/* 1297:     */  public Float2ObjectSortedMap<V> headMap(float to) {
/* 1298:1294 */    return new Submap(0.0F, true, to, false);
/* 1299:     */  }
/* 1300:     */  
/* 1301:1297 */  public Float2ObjectSortedMap<V> tailMap(float from) { return new Submap(from, false, 0.0F, true); }
/* 1302:     */  
/* 1303:     */  public Float2ObjectSortedMap<V> subMap(float from, float to) {
/* 1304:1300 */    return new Submap(from, false, to, false);
/* 1305:     */  }
/* 1306:     */  
/* 1310:     */  private final class Submap
/* 1311:     */    extends AbstractFloat2ObjectSortedMap<V>
/* 1312:     */    implements Serializable
/* 1313:     */  {
/* 1314:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1315:     */    
/* 1318:     */    float from;
/* 1319:     */    
/* 1321:     */    float to;
/* 1322:     */    
/* 1324:     */    boolean bottom;
/* 1325:     */    
/* 1327:     */    boolean top;
/* 1328:     */    
/* 1330:     */    protected volatile transient ObjectSortedSet<Float2ObjectMap.Entry<V>> entries;
/* 1331:     */    
/* 1333:     */    protected volatile transient FloatSortedSet keys;
/* 1334:     */    
/* 1336:     */    protected volatile transient ObjectCollection<V> values;
/* 1337:     */    
/* 1340:     */    public Submap(float from, boolean bottom, float to, boolean top)
/* 1341:     */    {
/* 1342:1338 */      if ((!bottom) && (!top) && (Float2ObjectAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1343:1339 */      this.from = from;
/* 1344:1340 */      this.bottom = bottom;
/* 1345:1341 */      this.to = to;
/* 1346:1342 */      this.top = top;
/* 1347:1343 */      this.defRetValue = Float2ObjectAVLTreeMap.this.defRetValue;
/* 1348:     */    }
/* 1349:     */    
/* 1350:1346 */    public void clear() { Float2ObjectAVLTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1351:1347 */      while (i.hasNext()) {
/* 1352:1348 */        i.nextEntry();
/* 1353:1349 */        i.remove();
/* 1354:     */      }
/* 1355:     */    }
/* 1356:     */    
/* 1359:     */    final boolean in(float k)
/* 1360:     */    {
/* 1361:1357 */      return ((this.bottom) || (Float2ObjectAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Float2ObjectAVLTreeMap.this.compare(k, this.to) < 0));
/* 1362:     */    }
/* 1363:     */    
/* 1364:     */    public ObjectSortedSet<Float2ObjectMap.Entry<V>> float2ObjectEntrySet() {
/* 1365:1361 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1366:     */          public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator() {
/* 1367:1363 */            return new Float2ObjectAVLTreeMap.Submap.SubmapEntryIterator(Float2ObjectAVLTreeMap.Submap.this);
/* 1368:     */          }
/* 1369:     */          
/* 1370:1366 */          public ObjectBidirectionalIterator<Float2ObjectMap.Entry<V>> iterator(Float2ObjectMap.Entry<V> from) { return new Float2ObjectAVLTreeMap.Submap.SubmapEntryIterator(Float2ObjectAVLTreeMap.Submap.this, ((Float)from.getKey()).floatValue()); }
/* 1371:     */          
/* 1372:1368 */          public Comparator<? super Float2ObjectMap.Entry<V>> comparator() { return Float2ObjectAVLTreeMap.this.entrySet().comparator(); }
/* 1373:     */          
/* 1374:     */          public boolean contains(Object o) {
/* 1375:1371 */            if (!(o instanceof Map.Entry)) return false;
/* 1376:1372 */            Map.Entry<Float, V> e = (Map.Entry)o;
/* 1377:1373 */            Float2ObjectAVLTreeMap.Entry<V> f = Float2ObjectAVLTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1378:1374 */            return (f != null) && (Float2ObjectAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1379:     */          }
/* 1380:     */          
/* 1381:     */          public boolean remove(Object o) {
/* 1382:1378 */            if (!(o instanceof Map.Entry)) return false;
/* 1383:1379 */            Map.Entry<Float, V> e = (Map.Entry)o;
/* 1384:1380 */            Float2ObjectAVLTreeMap.Entry<V> f = Float2ObjectAVLTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1385:1381 */            if ((f != null) && (Float2ObjectAVLTreeMap.Submap.this.in(f.key))) Float2ObjectAVLTreeMap.Submap.this.remove(f.key);
/* 1386:1382 */            return f != null;
/* 1387:     */          }
/* 1388:     */          
/* 1389:1385 */          public int size() { int c = 0;
/* 1390:1386 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1391:1387 */            return c;
/* 1392:     */          }
/* 1393:     */          
/* 1394:1390 */          public boolean isEmpty() { return !new Float2ObjectAVLTreeMap.Submap.SubmapIterator(Float2ObjectAVLTreeMap.Submap.this).hasNext(); }
/* 1395:     */          
/* 1397:1393 */          public void clear() { Float2ObjectAVLTreeMap.Submap.this.clear(); }
/* 1398:     */          
/* 1399:1395 */          public Float2ObjectMap.Entry<V> first() { return Float2ObjectAVLTreeMap.Submap.this.firstEntry(); }
/* 1400:1396 */          public Float2ObjectMap.Entry<V> last() { return Float2ObjectAVLTreeMap.Submap.this.lastEntry(); }
/* 1401:1397 */          public ObjectSortedSet<Float2ObjectMap.Entry<V>> subSet(Float2ObjectMap.Entry<V> from, Float2ObjectMap.Entry<V> to) { return Float2ObjectAVLTreeMap.Submap.this.subMap((Float)from.getKey(), (Float)to.getKey()).float2ObjectEntrySet(); }
/* 1402:1398 */          public ObjectSortedSet<Float2ObjectMap.Entry<V>> headSet(Float2ObjectMap.Entry<V> to) { return Float2ObjectAVLTreeMap.Submap.this.headMap((Float)to.getKey()).float2ObjectEntrySet(); }
/* 1403:1399 */          public ObjectSortedSet<Float2ObjectMap.Entry<V>> tailSet(Float2ObjectMap.Entry<V> from) { return Float2ObjectAVLTreeMap.Submap.this.tailMap((Float)from.getKey()).float2ObjectEntrySet(); }
/* 1404:     */        };
/* 1405:1401 */      return this.entries; }
/* 1406:     */    
/* 1407:1403 */    private class KeySet extends AbstractFloat2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1408:1404 */      public FloatBidirectionalIterator iterator() { return new Float2ObjectAVLTreeMap.Submap.SubmapKeyIterator(Float2ObjectAVLTreeMap.Submap.this); }
/* 1409:1405 */      public FloatBidirectionalIterator iterator(float from) { return new Float2ObjectAVLTreeMap.Submap.SubmapKeyIterator(Float2ObjectAVLTreeMap.Submap.this, from); }
/* 1410:     */    }
/* 1411:     */    
/* 1412:1408 */    public FloatSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1413:1409 */      return this.keys;
/* 1414:     */    }
/* 1415:     */    
/* 1416:1412 */    public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1417:     */          public ObjectIterator<V> iterator() {
/* 1418:1414 */            return new Float2ObjectAVLTreeMap.Submap.SubmapValueIterator(Float2ObjectAVLTreeMap.Submap.this, null);
/* 1419:     */          }
/* 1420:     */          
/* 1421:1417 */          public boolean contains(Object k) { return Float2ObjectAVLTreeMap.Submap.this.containsValue(k); }
/* 1422:     */          
/* 1423:     */          public int size() {
/* 1424:1420 */            return Float2ObjectAVLTreeMap.Submap.this.size();
/* 1425:     */          }
/* 1426:     */          
/* 1427:1423 */          public void clear() { Float2ObjectAVLTreeMap.Submap.this.clear(); }
/* 1428:     */        };
/* 1429:     */      }
/* 1430:1426 */      return this.values;
/* 1431:     */    }
/* 1432:     */    
/* 1434:1430 */    public boolean containsKey(float k) { return (in(k)) && (Float2ObjectAVLTreeMap.this.containsKey(k)); }
/* 1435:     */    
/* 1436:     */    public boolean containsValue(Object v) {
/* 1437:1433 */      Float2ObjectAVLTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1438:1435 */      for (; 
/* 1439:1435 */          i.hasNext(); 
/* 1440:     */          
/* 1441:1437 */          return true)
/* 1442:     */      {
/* 1443:     */        label9:
/* 1444:1436 */        Object ev = i.nextEntry().value;
/* 1445:1437 */        if (ev == null ? v != null : !ev.equals(v)) break label9;
/* 1446:     */      }
/* 1447:1439 */      return false;
/* 1448:     */    }
/* 1449:     */    
/* 1450:     */    public V get(float k)
/* 1451:     */    {
/* 1452:1444 */      float kk = k;
/* 1453:1445 */      Float2ObjectAVLTreeMap.Entry<V> e; return (in(kk)) && ((e = Float2ObjectAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1454:     */    }
/* 1455:     */    
/* 1456:1448 */    public V put(float k, V v) { Float2ObjectAVLTreeMap.this.modified = false;
/* 1457:1449 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1458:1450 */      V oldValue = Float2ObjectAVLTreeMap.this.put(k, v);
/* 1459:1451 */      return Float2ObjectAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1460:     */    }
/* 1461:     */    
/* 1462:1454 */    public V put(Float ok, V ov) { V oldValue = put(ok.floatValue(), ov);
/* 1463:1455 */      return Float2ObjectAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1464:     */    }
/* 1465:     */    
/* 1466:     */    public V remove(float k) {
/* 1467:1459 */      Float2ObjectAVLTreeMap.this.modified = false;
/* 1468:1460 */      if (!in(k)) return this.defRetValue;
/* 1469:1461 */      V oldValue = Float2ObjectAVLTreeMap.this.remove(k);
/* 1470:1462 */      return Float2ObjectAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1471:     */    }
/* 1472:     */    
/* 1473:1465 */    public V remove(Object ok) { V oldValue = remove(((Float)ok).floatValue());
/* 1474:1466 */      return Float2ObjectAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1475:     */    }
/* 1476:     */    
/* 1477:1469 */    public int size() { Float2ObjectAVLTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1478:1470 */      int n = 0;
/* 1479:1471 */      while (i.hasNext()) {
/* 1480:1472 */        n++;
/* 1481:1473 */        i.nextEntry();
/* 1482:     */      }
/* 1483:1475 */      return n;
/* 1484:     */    }
/* 1485:     */    
/* 1486:1478 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1487:     */    
/* 1489:1481 */    public FloatComparator comparator() { return Float2ObjectAVLTreeMap.this.actualComparator; }
/* 1490:     */    
/* 1491:     */    public Float2ObjectSortedMap<V> headMap(float to) {
/* 1492:1484 */      if (this.top) return new Submap(Float2ObjectAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1493:1485 */      return Float2ObjectAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Float2ObjectAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1494:     */    }
/* 1495:     */    
/* 1496:1488 */    public Float2ObjectSortedMap<V> tailMap(float from) { if (this.bottom) return new Submap(Float2ObjectAVLTreeMap.this, from, false, this.to, this.top);
/* 1497:1489 */      return Float2ObjectAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Float2ObjectAVLTreeMap.this, from, false, this.to, this.top) : this;
/* 1498:     */    }
/* 1499:     */    
/* 1500:1492 */    public Float2ObjectSortedMap<V> subMap(float from, float to) { if ((this.top) && (this.bottom)) return new Submap(Float2ObjectAVLTreeMap.this, from, false, to, false);
/* 1501:1493 */      if (!this.top) to = Float2ObjectAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1502:1494 */      if (!this.bottom) from = Float2ObjectAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1503:1495 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1504:1496 */      return new Submap(Float2ObjectAVLTreeMap.this, from, false, to, false);
/* 1505:     */    }
/* 1506:     */    
/* 1509:     */    public Float2ObjectAVLTreeMap.Entry<V> firstEntry()
/* 1510:     */    {
/* 1511:1503 */      if (Float2ObjectAVLTreeMap.this.tree == null) return null;
/* 1512:     */      Float2ObjectAVLTreeMap.Entry<V> e;
/* 1513:     */      Float2ObjectAVLTreeMap.Entry<V> e;
/* 1514:1506 */      if (this.bottom) { e = Float2ObjectAVLTreeMap.this.firstEntry;
/* 1515:     */      } else {
/* 1516:1508 */        e = Float2ObjectAVLTreeMap.this.locateKey(this.from);
/* 1517:     */        
/* 1518:1510 */        if (Float2ObjectAVLTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1519:     */        }
/* 1520:     */      }
/* 1521:1513 */      if ((e == null) || ((!this.top) && (Float2ObjectAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1522:1514 */      return e;
/* 1523:     */    }
/* 1524:     */    
/* 1527:     */    public Float2ObjectAVLTreeMap.Entry<V> lastEntry()
/* 1528:     */    {
/* 1529:1521 */      if (Float2ObjectAVLTreeMap.this.tree == null) return null;
/* 1530:     */      Float2ObjectAVLTreeMap.Entry<V> e;
/* 1531:     */      Float2ObjectAVLTreeMap.Entry<V> e;
/* 1532:1524 */      if (this.top) { e = Float2ObjectAVLTreeMap.this.lastEntry;
/* 1533:     */      } else {
/* 1534:1526 */        e = Float2ObjectAVLTreeMap.this.locateKey(this.to);
/* 1535:     */        
/* 1536:1528 */        if (Float2ObjectAVLTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1537:     */        }
/* 1538:     */      }
/* 1539:1531 */      if ((e == null) || ((!this.bottom) && (Float2ObjectAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1540:1532 */      return e;
/* 1541:     */    }
/* 1542:     */    
/* 1543:1535 */    public float firstFloatKey() { Float2ObjectAVLTreeMap.Entry<V> e = firstEntry();
/* 1544:1536 */      if (e == null) throw new NoSuchElementException();
/* 1545:1537 */      return e.key;
/* 1546:     */    }
/* 1547:     */    
/* 1548:1540 */    public float lastFloatKey() { Float2ObjectAVLTreeMap.Entry<V> e = lastEntry();
/* 1549:1541 */      if (e == null) throw new NoSuchElementException();
/* 1550:1542 */      return e.key;
/* 1551:     */    }
/* 1552:     */    
/* 1553:1545 */    public Float firstKey() { Float2ObjectAVLTreeMap.Entry<V> e = firstEntry();
/* 1554:1546 */      if (e == null) throw new NoSuchElementException();
/* 1555:1547 */      return e.getKey();
/* 1556:     */    }
/* 1557:     */    
/* 1558:1550 */    public Float lastKey() { Float2ObjectAVLTreeMap.Entry<V> e = lastEntry();
/* 1559:1551 */      if (e == null) throw new NoSuchElementException();
/* 1560:1552 */      return e.getKey();
/* 1561:     */    }
/* 1562:     */    
/* 1565:     */    private class SubmapIterator
/* 1566:     */      extends Float2ObjectAVLTreeMap.TreeIterator
/* 1567:     */    {
/* 1568:     */      SubmapIterator()
/* 1569:     */      {
/* 1570:1562 */        super();
/* 1571:1563 */        this.next = Float2ObjectAVLTreeMap.Submap.this.firstEntry();
/* 1572:     */      }
/* 1573:     */      
/* 1574:1566 */      SubmapIterator(float k) { this();
/* 1575:1567 */        if (this.next != null)
/* 1576:1568 */          if ((!Float2ObjectAVLTreeMap.Submap.this.bottom) && (Float2ObjectAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1577:1569 */          } else if ((!Float2ObjectAVLTreeMap.Submap.this.top) && (Float2ObjectAVLTreeMap.this.compare(k, (this.prev = Float2ObjectAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1578:     */          } else {
/* 1579:1571 */            this.next = Float2ObjectAVLTreeMap.this.locateKey(k);
/* 1580:1572 */            if (Float2ObjectAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1581:1573 */              this.prev = this.next;
/* 1582:1574 */              this.next = this.next.next();
/* 1583:     */            } else {
/* 1584:1576 */              this.prev = this.next.prev();
/* 1585:     */            }
/* 1586:     */          }
/* 1587:     */      }
/* 1588:     */      
/* 1589:1581 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1590:1582 */        if ((!Float2ObjectAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Float2ObjectAVLTreeMap.this.compare(this.prev.key, Float2ObjectAVLTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1591:     */      }
/* 1592:     */      
/* 1593:1585 */      void updateNext() { this.next = this.next.next();
/* 1594:1586 */        if ((!Float2ObjectAVLTreeMap.Submap.this.top) && (this.next != null) && (Float2ObjectAVLTreeMap.this.compare(this.next.key, Float2ObjectAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1595:     */      }
/* 1596:     */    }
/* 1597:     */    
/* 1598:1590 */    private class SubmapEntryIterator extends Float2ObjectAVLTreeMap<V>.Submap.SubmapIterator implements ObjectListIterator<Float2ObjectMap.Entry<V>> { SubmapEntryIterator() { super(); }
/* 1599:     */      
/* 1600:1592 */      SubmapEntryIterator(float k) { super(k); }
/* 1601:     */      
/* 1602:1594 */      public Float2ObjectMap.Entry<V> next() { return nextEntry(); }
/* 1603:1595 */      public Float2ObjectMap.Entry<V> previous() { return previousEntry(); }
/* 1604:1596 */      public void set(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1605:1597 */      public void add(Float2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1606:     */    }
/* 1607:     */    
/* 1612:     */    private final class SubmapKeyIterator
/* 1613:     */      extends Float2ObjectAVLTreeMap.Submap.SubmapIterator
/* 1614:     */      implements FloatListIterator
/* 1615:     */    {
/* 1616:1608 */      public SubmapKeyIterator() { super(); }
/* 1617:1609 */      public SubmapKeyIterator(float from) { super(from); }
/* 1618:1610 */      public float nextFloat() { return nextEntry().key; }
/* 1619:1611 */      public float previousFloat() { return previousEntry().key; }
/* 1620:1612 */      public void set(float k) { throw new UnsupportedOperationException(); }
/* 1621:1613 */      public void add(float k) { throw new UnsupportedOperationException(); }
/* 1622:1614 */      public Float next() { return Float.valueOf(nextEntry().key); }
/* 1623:1615 */      public Float previous() { return Float.valueOf(previousEntry().key); }
/* 1624:1616 */      public void set(Float ok) { throw new UnsupportedOperationException(); }
/* 1625:1617 */      public void add(Float ok) { throw new UnsupportedOperationException(); }
/* 1626:     */    }
/* 1627:     */    
/* 1631:     */    private final class SubmapValueIterator
/* 1632:     */      extends Float2ObjectAVLTreeMap<V>.Submap.SubmapIterator
/* 1633:     */      implements ObjectListIterator<V>
/* 1634:     */    {
/* 1635:1627 */      private SubmapValueIterator() { super(); }
/* 1636:1628 */      public V next() { return nextEntry().value; }
/* 1637:1629 */      public V previous() { return previousEntry().value; }
/* 1638:1630 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1639:1631 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1640:     */    }
/* 1641:     */  }
/* 1642:     */  
/* 1646:     */  public Float2ObjectAVLTreeMap<V> clone()
/* 1647:     */  {
/* 1648:     */    Float2ObjectAVLTreeMap<V> c;
/* 1649:     */    
/* 1651:     */    try
/* 1652:     */    {
/* 1653:1645 */      c = (Float2ObjectAVLTreeMap)super.clone();
/* 1654:     */    }
/* 1655:     */    catch (CloneNotSupportedException cantHappen) {
/* 1656:1648 */      throw new InternalError();
/* 1657:     */    }
/* 1658:1650 */    c.keys = null;
/* 1659:1651 */    c.values = null;
/* 1660:1652 */    c.entries = null;
/* 1661:1653 */    c.allocatePaths();
/* 1662:1654 */    if (this.count != 0)
/* 1663:     */    {
/* 1664:1656 */      Entry<V> rp = new Entry();Entry<V> rq = new Entry();
/* 1665:1657 */      Entry<V> p = rp;
/* 1666:1658 */      rp.left(this.tree);
/* 1667:1659 */      Entry<V> q = rq;
/* 1668:1660 */      rq.pred(null);
/* 1669:     */      for (;;) {
/* 1670:1662 */        if (!p.pred()) {
/* 1671:1663 */          Entry<V> e = p.left.clone();
/* 1672:1664 */          e.pred(q.left);
/* 1673:1665 */          e.succ(q);
/* 1674:1666 */          q.left(e);
/* 1675:1667 */          p = p.left;
/* 1676:1668 */          q = q.left;
/* 1677:     */        }
/* 1678:     */        else {
/* 1679:1671 */          while (p.succ()) {
/* 1680:1672 */            p = p.right;
/* 1681:1673 */            if (p == null) {
/* 1682:1674 */              q.right = null;
/* 1683:1675 */              c.tree = rq.left;
/* 1684:1676 */              c.firstEntry = c.tree;
/* 1685:1677 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1686:1678 */              c.lastEntry = c.tree;
/* 1687:1679 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1688:1680 */              return c;
/* 1689:     */            }
/* 1690:1682 */            q = q.right;
/* 1691:     */          }
/* 1692:1684 */          p = p.right;
/* 1693:1685 */          q = q.right;
/* 1694:     */        }
/* 1695:1687 */        if (!p.succ()) {
/* 1696:1688 */          Entry<V> e = p.right.clone();
/* 1697:1689 */          e.succ(q.right);
/* 1698:1690 */          e.pred(q);
/* 1699:1691 */          q.right(e);
/* 1700:     */        }
/* 1701:     */      }
/* 1702:     */    }
/* 1703:1695 */    return c;
/* 1704:     */  }
/* 1705:     */  
/* 1706:1698 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1707:1699 */    Float2ObjectAVLTreeMap<V>.EntryIterator i = new EntryIterator();
/* 1708:     */    
/* 1709:1701 */    s.defaultWriteObject();
/* 1710:1702 */    while (n-- != 0) {
/* 1711:1703 */      Entry<V> e = i.nextEntry();
/* 1712:1704 */      s.writeFloat(e.key);
/* 1713:1705 */      s.writeObject(e.value);
/* 1714:     */    }
/* 1715:     */  }
/* 1716:     */  
/* 1722:     */  private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/* 1723:     */    throws IOException, ClassNotFoundException
/* 1724:     */  {
/* 1725:1717 */    if (n == 1) {
/* 1726:1718 */      Entry<V> top = new Entry(s.readFloat(), s.readObject());
/* 1727:1719 */      top.pred(pred);
/* 1728:1720 */      top.succ(succ);
/* 1729:1721 */      return top;
/* 1730:     */    }
/* 1731:1723 */    if (n == 2)
/* 1732:     */    {
/* 1734:1726 */      Entry<V> top = new Entry(s.readFloat(), s.readObject());
/* 1735:1727 */      top.right(new Entry(s.readFloat(), s.readObject()));
/* 1736:1728 */      top.right.pred(top);
/* 1737:1729 */      top.balance(1);
/* 1738:1730 */      top.pred(pred);
/* 1739:1731 */      top.right.succ(succ);
/* 1740:1732 */      return top;
/* 1741:     */    }
/* 1742:     */    
/* 1743:1735 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1744:1736 */    Entry<V> top = new Entry();
/* 1745:1737 */    top.left(readTree(s, leftN, pred, top));
/* 1746:1738 */    top.key = s.readFloat();
/* 1747:1739 */    top.value = s.readObject();
/* 1748:1740 */    top.right(readTree(s, rightN, top, succ));
/* 1749:1741 */    if (n == (n & -n)) top.balance(1);
/* 1750:1742 */    return top;
/* 1751:     */  }
/* 1752:     */  
/* 1753:1745 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1754:     */    
/* 1756:1748 */    setActualComparator();
/* 1757:1749 */    allocatePaths();
/* 1758:1750 */    if (this.count != 0) {
/* 1759:1751 */      this.tree = readTree(s, this.count, null, null);
/* 1760:     */      
/* 1761:1753 */      Entry<V> e = this.tree;
/* 1762:1754 */      while (e.left() != null) e = e.left();
/* 1763:1755 */      this.firstEntry = e;
/* 1764:1756 */      e = this.tree;
/* 1765:1757 */      while (e.right() != null) e = e.right();
/* 1766:1758 */      this.lastEntry = e;
/* 1767:     */    }
/* 1768:     */  }
/* 1769:     */  
/* 1770:     */  private static int checkTree(Entry e) {
/* 1771:1763 */    return 0;
/* 1772:     */  }
/* 1773:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */