/*    1:     */package it.unimi.dsi.fastutil.shorts;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*    4:     */import it.unimi.dsi.fastutil.chars.CharCollection;
/*    5:     */import it.unimi.dsi.fastutil.chars.CharIterator;
/*    6:     */import it.unimi.dsi.fastutil.chars.CharListIterator;
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
/*   73:     */public class Short2CharAVLTreeMap
/*   74:     */  extends AbstractShort2CharSortedMap
/*   75:     */  implements Serializable, Cloneable
/*   76:     */{
/*   77:     */  protected transient Entry tree;
/*   78:     */  protected int count;
/*   79:     */  protected transient Entry firstEntry;
/*   80:     */  protected transient Entry lastEntry;
/*   81:     */  protected volatile transient ObjectSortedSet<Short2CharMap.Entry> entries;
/*   82:     */  protected volatile transient ShortSortedSet keys;
/*   83:     */  protected volatile transient CharCollection values;
/*   84:     */  protected transient boolean modified;
/*   85:     */  protected Comparator<? super Short> storedComparator;
/*   86:     */  protected transient ShortComparator actualComparator;
/*   87:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   88:     */  private static final boolean ASSERTS = false;
/*   89:     */  private transient boolean[] dirPath;
/*   90:     */  
/*   91:     */  public Short2CharAVLTreeMap()
/*   92:     */  {
/*   93:  93 */    allocatePaths();
/*   94:     */    
/*   98:  98 */    this.tree = null;
/*   99:  99 */    this.count = 0;
/*  100:     */  }
/*  101:     */  
/*  112:     */  private void setActualComparator()
/*  113:     */  {
/*  114: 114 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof ShortComparator))) this.actualComparator = ((ShortComparator)this.storedComparator); else {
/*  115: 115 */      this.actualComparator = new ShortComparator() {
/*  116:     */        public int compare(short k1, short k2) {
/*  117: 117 */          return Short2CharAVLTreeMap.this.storedComparator.compare(Short.valueOf(k1), Short.valueOf(k2));
/*  118:     */        }
/*  119:     */        
/*  120: 120 */        public int compare(Short ok1, Short ok2) { return Short2CharAVLTreeMap.this.storedComparator.compare(ok1, ok2); }
/*  121:     */      };
/*  122:     */    }
/*  123:     */  }
/*  124:     */  
/*  131:     */  public Short2CharAVLTreeMap(Comparator<? super Short> c)
/*  132:     */  {
/*  133: 133 */    this();
/*  134: 134 */    this.storedComparator = c;
/*  135: 135 */    setActualComparator();
/*  136:     */  }
/*  137:     */  
/*  143:     */  public Short2CharAVLTreeMap(Map<? extends Short, ? extends Character> m)
/*  144:     */  {
/*  145: 145 */    this();
/*  146: 146 */    putAll(m);
/*  147:     */  }
/*  148:     */  
/*  153:     */  public Short2CharAVLTreeMap(SortedMap<Short, Character> m)
/*  154:     */  {
/*  155: 155 */    this(m.comparator());
/*  156: 156 */    putAll(m);
/*  157:     */  }
/*  158:     */  
/*  163:     */  public Short2CharAVLTreeMap(Short2CharMap m)
/*  164:     */  {
/*  165: 165 */    this();
/*  166: 166 */    putAll(m);
/*  167:     */  }
/*  168:     */  
/*  173:     */  public Short2CharAVLTreeMap(Short2CharSortedMap m)
/*  174:     */  {
/*  175: 175 */    this(m.comparator());
/*  176: 176 */    putAll(m);
/*  177:     */  }
/*  178:     */  
/*  186:     */  public Short2CharAVLTreeMap(short[] k, char[] v, Comparator<? super Short> c)
/*  187:     */  {
/*  188: 188 */    this(c);
/*  189: 189 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  190: 190 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  191:     */    }
/*  192:     */  }
/*  193:     */  
/*  199:     */  public Short2CharAVLTreeMap(short[] k, char[] v)
/*  200:     */  {
/*  201: 201 */    this(k, v, null);
/*  202:     */  }
/*  203:     */  
/*  226:     */  final int compare(short k1, short k2)
/*  227:     */  {
/*  228: 228 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  229:     */  }
/*  230:     */  
/*  238:     */  final Entry findKey(short k)
/*  239:     */  {
/*  240: 240 */    Entry e = this.tree;
/*  241:     */    
/*  242:     */    int cmp;
/*  243: 243 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) { e = cmp < 0 ? e.left() : e.right();
/*  244:     */    }
/*  245: 245 */    return e;
/*  246:     */  }
/*  247:     */  
/*  254:     */  final Entry locateKey(short k)
/*  255:     */  {
/*  256: 256 */    Entry e = this.tree;Entry last = this.tree;
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
/*  278:     */  public char put(short k, char v)
/*  279:     */  {
/*  280: 280 */    this.modified = false;
/*  281:     */    
/*  282: 282 */    if (this.tree == null) {
/*  283: 283 */      this.count += 1;
/*  284: 284 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  285: 285 */      this.modified = true;
/*  286:     */    }
/*  287:     */    else {
/*  288: 288 */      Entry p = this.tree;Entry q = null;Entry y = this.tree;Entry z = null;Entry e = null;Entry w = null;
/*  289: 289 */      int i = 0;
/*  290:     */      for (;;) {
/*  291:     */        int cmp;
/*  292: 292 */        if ((cmp = compare(k, p.key)) == 0) {
/*  293: 293 */          char oldValue = p.value;
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
/*  355: 355 */        Entry x = y.left;
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
/*  404: 404 */        Entry x = y.right;
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
/*  470:     */  private Entry parent(Entry e)
/*  471:     */  {
/*  472: 472 */    if (e == this.tree) { return null;
/*  473:     */    }
/*  474:     */    Entry y;
/*  475: 475 */    Entry x = y = e;
/*  476:     */    for (;;)
/*  477:     */    {
/*  478: 478 */      if (y.succ()) {
/*  479: 479 */        Entry p = y.right;
/*  480: 480 */        if ((p == null) || (p.left != e)) {
/*  481: 481 */          while (!x.pred()) x = x.left;
/*  482: 482 */          p = x.left;
/*  483:     */        }
/*  484: 484 */        return p;
/*  485:     */      }
/*  486: 486 */      if (x.pred()) {
/*  487: 487 */        Entry p = x.left;
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
/*  504:     */  public char remove(short k)
/*  505:     */  {
/*  506: 506 */    this.modified = false;
/*  507:     */    
/*  508: 508 */    if (this.tree == null) { return this.defRetValue;
/*  509:     */    }
/*  510:     */    
/*  511: 511 */    Entry p = this.tree;Entry q = null;
/*  512: 512 */    boolean dir = false;
/*  513: 513 */    short kk = k;
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
/*  549: 549 */      Entry r = p.right;
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
/*  567:     */        Entry s;
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
/*  603: 603 */      Entry y = q;
/*  604: 604 */      q = parent(y);
/*  605:     */      
/*  606: 606 */      if (!dir) {
/*  607: 607 */        dir = (q != null) && (q.left != y);
/*  608: 608 */        y.incBalance();
/*  609:     */        
/*  610: 610 */        if (y.balance() == 1) break;
/*  611: 611 */        if (y.balance() == 2)
/*  612:     */        {
/*  613: 613 */          Entry x = y.right;
/*  614:     */          
/*  616: 616 */          if (x.balance() == -1)
/*  617:     */          {
/*  621: 621 */            Entry w = x.left;
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
/*  694: 694 */          Entry x = y.left;
/*  695:     */          
/*  697: 697 */          if (x.balance() == 1)
/*  698:     */          {
/*  702: 702 */            Entry w = x.right;
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
/*  777:     */  public Character put(Short ok, Character ov)
/*  778:     */  {
/*  779: 779 */    char oldValue = put(ok.shortValue(), ov.charValue());
/*  780: 780 */    return this.modified ? null : Character.valueOf(oldValue);
/*  781:     */  }
/*  782:     */  
/*  786:     */  public Character remove(Object ok)
/*  787:     */  {
/*  788: 788 */    char oldValue = remove(((Short)ok).shortValue());
/*  789: 789 */    return this.modified ? Character.valueOf(oldValue) : null;
/*  790:     */  }
/*  791:     */  
/*  793:     */  public boolean containsValue(char v)
/*  794:     */  {
/*  795: 795 */    ValueIterator i = new ValueIterator(null);
/*  796:     */    
/*  798: 798 */    int j = this.count;
/*  799: 799 */    while (j-- != 0) {
/*  800: 800 */      char ev = i.nextChar();
/*  801: 801 */      if (ev == v) { return true;
/*  802:     */      }
/*  803:     */    }
/*  804: 804 */    return false;
/*  805:     */  }
/*  806:     */  
/*  807:     */  public void clear() {
/*  808: 808 */    this.count = 0;
/*  809: 809 */    this.tree = null;
/*  810: 810 */    this.entries = null;
/*  811: 811 */    this.values = null;
/*  812: 812 */    this.keys = null;
/*  813: 813 */    this.firstEntry = (this.lastEntry = null);
/*  814:     */  }
/*  815:     */  
/*  818:     */  private static final class Entry
/*  819:     */    implements Cloneable, Short2CharMap.Entry
/*  820:     */  {
/*  821:     */    private static final int SUCC_MASK = -2147483648;
/*  822:     */    
/*  824:     */    private static final int PRED_MASK = 1073741824;
/*  825:     */    
/*  827:     */    private static final int BALANCE_MASK = 255;
/*  828:     */    
/*  830:     */    short key;
/*  831:     */    
/*  833:     */    char value;
/*  834:     */    
/*  836:     */    Entry left;
/*  837:     */    
/*  839:     */    Entry right;
/*  840:     */    
/*  842:     */    int info;
/*  843:     */    
/*  845:     */    Entry() {}
/*  846:     */    
/*  848:     */    Entry(short k, char v)
/*  849:     */    {
/*  850: 850 */      this.key = k;
/*  851: 851 */      this.value = v;
/*  852: 852 */      this.info = -1073741824;
/*  853:     */    }
/*  854:     */    
/*  859:     */    Entry left()
/*  860:     */    {
/*  861: 861 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  862:     */    }
/*  863:     */    
/*  868:     */    Entry right()
/*  869:     */    {
/*  870: 870 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  871:     */    }
/*  872:     */    
/*  875:     */    boolean pred()
/*  876:     */    {
/*  877: 877 */      return (this.info & 0x40000000) != 0;
/*  878:     */    }
/*  879:     */    
/*  882:     */    boolean succ()
/*  883:     */    {
/*  884: 884 */      return (this.info & 0x80000000) != 0;
/*  885:     */    }
/*  886:     */    
/*  889:     */    void pred(boolean pred)
/*  890:     */    {
/*  891: 891 */      if (pred) this.info |= 1073741824; else {
/*  892: 892 */        this.info &= -1073741825;
/*  893:     */      }
/*  894:     */    }
/*  895:     */    
/*  897:     */    void succ(boolean succ)
/*  898:     */    {
/*  899: 899 */      if (succ) this.info |= -2147483648; else {
/*  900: 900 */        this.info &= 2147483647;
/*  901:     */      }
/*  902:     */    }
/*  903:     */    
/*  905:     */    void pred(Entry pred)
/*  906:     */    {
/*  907: 907 */      this.info |= 1073741824;
/*  908: 908 */      this.left = pred;
/*  909:     */    }
/*  910:     */    
/*  913:     */    void succ(Entry succ)
/*  914:     */    {
/*  915: 915 */      this.info |= -2147483648;
/*  916: 916 */      this.right = succ;
/*  917:     */    }
/*  918:     */    
/*  921:     */    void left(Entry left)
/*  922:     */    {
/*  923: 923 */      this.info &= -1073741825;
/*  924: 924 */      this.left = left;
/*  925:     */    }
/*  926:     */    
/*  929:     */    void right(Entry right)
/*  930:     */    {
/*  931: 931 */      this.info &= 2147483647;
/*  932: 932 */      this.right = right;
/*  933:     */    }
/*  934:     */    
/*  937:     */    int balance()
/*  938:     */    {
/*  939: 939 */      return (byte)this.info;
/*  940:     */    }
/*  941:     */    
/*  944:     */    void balance(int level)
/*  945:     */    {
/*  946: 946 */      this.info &= -256;
/*  947: 947 */      this.info |= level & 0xFF;
/*  948:     */    }
/*  949:     */    
/*  950:     */    void incBalance()
/*  951:     */    {
/*  952: 952 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  953:     */    }
/*  954:     */    
/*  955:     */    protected void decBalance()
/*  956:     */    {
/*  957: 957 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*  958:     */    }
/*  959:     */    
/*  964:     */    Entry next()
/*  965:     */    {
/*  966: 966 */      Entry next = this.right;
/*  967: 967 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  968: 968 */      return next;
/*  969:     */    }
/*  970:     */    
/*  975:     */    Entry prev()
/*  976:     */    {
/*  977: 977 */      Entry prev = this.left;
/*  978: 978 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  979: 979 */      return prev;
/*  980:     */    }
/*  981:     */    
/*  982:     */    public Short getKey() {
/*  983: 983 */      return Short.valueOf(this.key);
/*  984:     */    }
/*  985:     */    
/*  986:     */    public short getShortKey()
/*  987:     */    {
/*  988: 988 */      return this.key;
/*  989:     */    }
/*  990:     */    
/*  991:     */    public Character getValue()
/*  992:     */    {
/*  993: 993 */      return Character.valueOf(this.value);
/*  994:     */    }
/*  995:     */    
/*  996:     */    public char getCharValue()
/*  997:     */    {
/*  998: 998 */      return this.value;
/*  999:     */    }
/* 1000:     */    
/* 1001:     */    public char setValue(char value)
/* 1002:     */    {
/* 1003:1003 */      char oldValue = this.value;
/* 1004:1004 */      this.value = value;
/* 1005:1005 */      return oldValue;
/* 1006:     */    }
/* 1007:     */    
/* 1009:     */    public Character setValue(Character value)
/* 1010:     */    {
/* 1011:1011 */      return Character.valueOf(setValue(value.charValue()));
/* 1012:     */    }
/* 1013:     */    
/* 1015:     */    public Entry clone()
/* 1016:     */    {
/* 1017:     */      Entry c;
/* 1018:     */      try
/* 1019:     */      {
/* 1020:1020 */        c = (Entry)super.clone();
/* 1021:     */      }
/* 1022:     */      catch (CloneNotSupportedException cantHappen) {
/* 1023:1023 */        throw new InternalError();
/* 1024:     */      }
/* 1025:     */      
/* 1026:1026 */      c.key = this.key;
/* 1027:1027 */      c.value = this.value;
/* 1028:1028 */      c.info = this.info;
/* 1029:     */      
/* 1030:1030 */      return c;
/* 1031:     */    }
/* 1032:     */    
/* 1033:     */    public boolean equals(Object o)
/* 1034:     */    {
/* 1035:1035 */      if (!(o instanceof Map.Entry)) return false;
/* 1036:1036 */      Map.Entry<Short, Character> e = (Map.Entry)o;
/* 1037:     */      
/* 1038:1038 */      return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == ((Character)e.getValue()).charValue());
/* 1039:     */    }
/* 1040:     */    
/* 1041:     */    public int hashCode() {
/* 1042:1042 */      return this.key ^ this.value;
/* 1043:     */    }
/* 1044:     */    
/* 1045:     */    public String toString()
/* 1046:     */    {
/* 1047:1047 */      return this.key + "=>" + this.value;
/* 1048:     */    }
/* 1049:     */  }
/* 1050:     */  
/* 1085:     */  public boolean containsKey(short k)
/* 1086:     */  {
/* 1087:1087 */    return findKey(k) != null;
/* 1088:     */  }
/* 1089:     */  
/* 1090:     */  public int size() {
/* 1091:1091 */    return this.count;
/* 1092:     */  }
/* 1093:     */  
/* 1094:     */  public boolean isEmpty() {
/* 1095:1095 */    return this.count == 0;
/* 1096:     */  }
/* 1097:     */  
/* 1099:     */  public char get(short k)
/* 1100:     */  {
/* 1101:1101 */    Entry e = findKey(k);
/* 1102:1102 */    return e == null ? this.defRetValue : e.value;
/* 1103:     */  }
/* 1104:     */  
/* 1105:1105 */  public short firstShortKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1106:1106 */    return this.firstEntry.key;
/* 1107:     */  }
/* 1108:     */  
/* 1109:1109 */  public short lastShortKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1110:1110 */    return this.lastEntry.key;
/* 1111:     */  }
/* 1112:     */  
/* 1115:     */  private class TreeIterator
/* 1116:     */  {
/* 1117:     */    Short2CharAVLTreeMap.Entry prev;
/* 1118:     */    
/* 1120:     */    Short2CharAVLTreeMap.Entry next;
/* 1121:     */    
/* 1122:     */    Short2CharAVLTreeMap.Entry curr;
/* 1123:     */    
/* 1124:1124 */    int index = 0;
/* 1125:     */    
/* 1126:1126 */    TreeIterator() { this.next = Short2CharAVLTreeMap.this.firstEntry; }
/* 1127:     */    
/* 1128:     */    TreeIterator(short k) {
/* 1129:1129 */      if ((this.next = Short2CharAVLTreeMap.this.locateKey(k)) != null)
/* 1130:1130 */        if (Short2CharAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1131:1131 */          this.prev = this.next;
/* 1132:1132 */          this.next = this.next.next();
/* 1133:     */        } else {
/* 1134:1134 */          this.prev = this.next.prev();
/* 1135:     */        } }
/* 1136:     */    
/* 1137:1137 */    public boolean hasNext() { return this.next != null; }
/* 1138:1138 */    public boolean hasPrevious() { return this.prev != null; }
/* 1139:     */    
/* 1140:1140 */    void updateNext() { this.next = this.next.next(); }
/* 1141:     */    
/* 1142:     */    Short2CharAVLTreeMap.Entry nextEntry() {
/* 1143:1143 */      if (!hasNext()) throw new NoSuchElementException();
/* 1144:1144 */      this.curr = (this.prev = this.next);
/* 1145:1145 */      this.index += 1;
/* 1146:1146 */      updateNext();
/* 1147:1147 */      return this.curr;
/* 1148:     */    }
/* 1149:     */    
/* 1150:1150 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1151:     */    
/* 1152:     */    Short2CharAVLTreeMap.Entry previousEntry() {
/* 1153:1153 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1154:1154 */      this.curr = (this.next = this.prev);
/* 1155:1155 */      this.index -= 1;
/* 1156:1156 */      updatePrevious();
/* 1157:1157 */      return this.curr;
/* 1158:     */    }
/* 1159:     */    
/* 1160:1160 */    public int nextIndex() { return this.index; }
/* 1161:     */    
/* 1163:1163 */    public int previousIndex() { return this.index - 1; }
/* 1164:     */    
/* 1165:     */    public void remove() {
/* 1166:1166 */      if (this.curr == null) { throw new IllegalStateException();
/* 1167:     */      }
/* 1168:     */      
/* 1169:1169 */      if (this.curr == this.prev) this.index -= 1;
/* 1170:1170 */      this.next = (this.prev = this.curr);
/* 1171:1171 */      updatePrevious();
/* 1172:1172 */      updateNext();
/* 1173:1173 */      Short2CharAVLTreeMap.this.remove(this.curr.key);
/* 1174:1174 */      this.curr = null;
/* 1175:     */    }
/* 1176:     */    
/* 1177:1177 */    public int skip(int n) { int i = n;
/* 1178:1178 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 1179:1179 */      return n - i - 1;
/* 1180:     */    }
/* 1181:     */    
/* 1182:1182 */    public int back(int n) { int i = n;
/* 1183:1183 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1184:1184 */      return n - i - 1;
/* 1185:     */    }
/* 1186:     */  }
/* 1187:     */  
/* 1188:     */  private class EntryIterator
/* 1189:     */    extends Short2CharAVLTreeMap.TreeIterator
/* 1190:     */    implements ObjectListIterator<Short2CharMap.Entry>
/* 1191:     */  {
/* 1192:1192 */    EntryIterator() { super(); }
/* 1193:     */    
/* 1194:1194 */    EntryIterator(short k) { super(k); }
/* 1195:     */    
/* 1196:1196 */    public Short2CharMap.Entry next() { return nextEntry(); }
/* 1197:1197 */    public Short2CharMap.Entry previous() { return previousEntry(); }
/* 1198:1198 */    public void set(Short2CharMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1199:1199 */    public void add(Short2CharMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1200:     */  }
/* 1201:     */  
/* 1202:1202 */  public ObjectSortedSet<Short2CharMap.Entry> short2CharEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1203:1203 */        final Comparator<? super Short2CharMap.Entry> comparator = new Comparator() {
/* 1204:     */          public int compare(Short2CharMap.Entry x, Short2CharMap.Entry y) {
/* 1205:1205 */            return Short2CharAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1206:     */          }
/* 1207:     */        };
/* 1208:     */        
/* 1209:1209 */        public Comparator<? super Short2CharMap.Entry> comparator() { return this.comparator; }
/* 1210:     */        
/* 1211:     */        public ObjectBidirectionalIterator<Short2CharMap.Entry> iterator() {
/* 1212:1212 */          return new Short2CharAVLTreeMap.EntryIterator(Short2CharAVLTreeMap.this);
/* 1213:     */        }
/* 1214:     */        
/* 1215:1215 */        public ObjectBidirectionalIterator<Short2CharMap.Entry> iterator(Short2CharMap.Entry from) { return new Short2CharAVLTreeMap.EntryIterator(Short2CharAVLTreeMap.this, ((Short)from.getKey()).shortValue()); }
/* 1216:     */        
/* 1217:     */        public boolean contains(Object o)
/* 1218:     */        {
/* 1219:1219 */          if (!(o instanceof Map.Entry)) return false;
/* 1220:1220 */          Map.Entry<Short, Character> e = (Map.Entry)o;
/* 1221:1221 */          Short2CharAVLTreeMap.Entry f = Short2CharAVLTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1222:1222 */          return e.equals(f);
/* 1223:     */        }
/* 1224:     */        
/* 1225:     */        public boolean remove(Object o) {
/* 1226:1226 */          if (!(o instanceof Map.Entry)) return false;
/* 1227:1227 */          Map.Entry<Short, Character> e = (Map.Entry)o;
/* 1228:1228 */          Short2CharAVLTreeMap.Entry f = Short2CharAVLTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1229:1229 */          if (f != null) Short2CharAVLTreeMap.this.remove(f.key);
/* 1230:1230 */          return f != null; }
/* 1231:     */        
/* 1232:1232 */        public int size() { return Short2CharAVLTreeMap.this.count; }
/* 1233:1233 */        public void clear() { Short2CharAVLTreeMap.this.clear(); }
/* 1234:1234 */        public Short2CharMap.Entry first() { return Short2CharAVLTreeMap.this.firstEntry; }
/* 1235:1235 */        public Short2CharMap.Entry last() { return Short2CharAVLTreeMap.this.lastEntry; }
/* 1236:1236 */        public ObjectSortedSet<Short2CharMap.Entry> subSet(Short2CharMap.Entry from, Short2CharMap.Entry to) { return Short2CharAVLTreeMap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2CharEntrySet(); }
/* 1237:1237 */        public ObjectSortedSet<Short2CharMap.Entry> headSet(Short2CharMap.Entry to) { return Short2CharAVLTreeMap.this.headMap((Short)to.getKey()).short2CharEntrySet(); }
/* 1238:1238 */        public ObjectSortedSet<Short2CharMap.Entry> tailSet(Short2CharMap.Entry from) { return Short2CharAVLTreeMap.this.tailMap((Short)from.getKey()).short2CharEntrySet(); }
/* 1239:     */      };
/* 1240:1240 */    return this.entries;
/* 1241:     */  }
/* 1242:     */  
/* 1245:     */  private final class KeyIterator
/* 1246:     */    extends Short2CharAVLTreeMap.TreeIterator
/* 1247:     */    implements ShortListIterator
/* 1248:     */  {
/* 1249:1249 */    public KeyIterator() { super(); }
/* 1250:1250 */    public KeyIterator(short k) { super(k); }
/* 1251:1251 */    public short nextShort() { return nextEntry().key; }
/* 1252:1252 */    public short previousShort() { return previousEntry().key; }
/* 1253:1253 */    public void set(short k) { throw new UnsupportedOperationException(); }
/* 1254:1254 */    public void add(short k) { throw new UnsupportedOperationException(); }
/* 1255:1255 */    public Short next() { return Short.valueOf(nextEntry().key); }
/* 1256:1256 */    public Short previous() { return Short.valueOf(previousEntry().key); }
/* 1257:1257 */    public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1258:1258 */    public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1259:     */  }
/* 1260:     */  
/* 1261:1261 */  private class KeySet extends AbstractShort2CharSortedMap.KeySet { private KeySet() { super(); }
/* 1262:1262 */    public ShortBidirectionalIterator iterator() { return new Short2CharAVLTreeMap.KeyIterator(Short2CharAVLTreeMap.this); }
/* 1263:1263 */    public ShortBidirectionalIterator iterator(short from) { return new Short2CharAVLTreeMap.KeyIterator(Short2CharAVLTreeMap.this, from); }
/* 1264:     */  }
/* 1265:     */  
/* 1272:     */  public ShortSortedSet keySet()
/* 1273:     */  {
/* 1274:1274 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1275:1275 */    return this.keys;
/* 1276:     */  }
/* 1277:     */  
/* 1279:     */  private final class ValueIterator
/* 1280:     */    extends Short2CharAVLTreeMap.TreeIterator
/* 1281:     */    implements CharListIterator
/* 1282:     */  {
/* 1283:1283 */    private ValueIterator() { super(); }
/* 1284:1284 */    public char nextChar() { return nextEntry().value; }
/* 1285:1285 */    public char previousChar() { return previousEntry().value; }
/* 1286:1286 */    public void set(char v) { throw new UnsupportedOperationException(); }
/* 1287:1287 */    public void add(char v) { throw new UnsupportedOperationException(); }
/* 1288:1288 */    public Character next() { return Character.valueOf(nextEntry().value); }
/* 1289:1289 */    public Character previous() { return Character.valueOf(previousEntry().value); }
/* 1290:1290 */    public void set(Character ok) { throw new UnsupportedOperationException(); }
/* 1291:1291 */    public void add(Character ok) { throw new UnsupportedOperationException(); }
/* 1292:     */  }
/* 1293:     */  
/* 1300:     */  public CharCollection values()
/* 1301:     */  {
/* 1302:1302 */    if (this.values == null) { this.values = new AbstractCharCollection() {
/* 1303:     */        public CharIterator iterator() {
/* 1304:1304 */          return new Short2CharAVLTreeMap.ValueIterator(Short2CharAVLTreeMap.this, null);
/* 1305:     */        }
/* 1306:     */        
/* 1307:1307 */        public boolean contains(char k) { return Short2CharAVLTreeMap.this.containsValue(k); }
/* 1308:     */        
/* 1309:     */        public int size() {
/* 1310:1310 */          return Short2CharAVLTreeMap.this.count;
/* 1311:     */        }
/* 1312:     */        
/* 1313:1313 */        public void clear() { Short2CharAVLTreeMap.this.clear(); }
/* 1314:     */      };
/* 1315:     */    }
/* 1316:1316 */    return this.values;
/* 1317:     */  }
/* 1318:     */  
/* 1319:1319 */  public ShortComparator comparator() { return this.actualComparator; }
/* 1320:     */  
/* 1321:     */  public Short2CharSortedMap headMap(short to) {
/* 1322:1322 */    return new Submap((short)0, true, to, false);
/* 1323:     */  }
/* 1324:     */  
/* 1325:1325 */  public Short2CharSortedMap tailMap(short from) { return new Submap(from, false, (short)0, true); }
/* 1326:     */  
/* 1327:     */  public Short2CharSortedMap subMap(short from, short to) {
/* 1328:1328 */    return new Submap(from, false, to, false);
/* 1329:     */  }
/* 1330:     */  
/* 1334:     */  private final class Submap
/* 1335:     */    extends AbstractShort2CharSortedMap
/* 1336:     */    implements Serializable
/* 1337:     */  {
/* 1338:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1339:     */    
/* 1342:     */    short from;
/* 1343:     */    
/* 1345:     */    short to;
/* 1346:     */    
/* 1348:     */    boolean bottom;
/* 1349:     */    
/* 1351:     */    boolean top;
/* 1352:     */    
/* 1354:     */    protected volatile transient ObjectSortedSet<Short2CharMap.Entry> entries;
/* 1355:     */    
/* 1357:     */    protected volatile transient ShortSortedSet keys;
/* 1358:     */    
/* 1360:     */    protected volatile transient CharCollection values;
/* 1361:     */    
/* 1364:     */    public Submap(short from, boolean bottom, short to, boolean top)
/* 1365:     */    {
/* 1366:1366 */      if ((!bottom) && (!top) && (Short2CharAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1367:1367 */      this.from = from;
/* 1368:1368 */      this.bottom = bottom;
/* 1369:1369 */      this.to = to;
/* 1370:1370 */      this.top = top;
/* 1371:1371 */      this.defRetValue = Short2CharAVLTreeMap.this.defRetValue;
/* 1372:     */    }
/* 1373:     */    
/* 1374:1374 */    public void clear() { SubmapIterator i = new SubmapIterator();
/* 1375:1375 */      while (i.hasNext()) {
/* 1376:1376 */        i.nextEntry();
/* 1377:1377 */        i.remove();
/* 1378:     */      }
/* 1379:     */    }
/* 1380:     */    
/* 1383:     */    final boolean in(short k)
/* 1384:     */    {
/* 1385:1385 */      return ((this.bottom) || (Short2CharAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Short2CharAVLTreeMap.this.compare(k, this.to) < 0));
/* 1386:     */    }
/* 1387:     */    
/* 1388:     */    public ObjectSortedSet<Short2CharMap.Entry> short2CharEntrySet() {
/* 1389:1389 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1390:     */          public ObjectBidirectionalIterator<Short2CharMap.Entry> iterator() {
/* 1391:1391 */            return new Short2CharAVLTreeMap.Submap.SubmapEntryIterator(Short2CharAVLTreeMap.Submap.this);
/* 1392:     */          }
/* 1393:     */          
/* 1394:1394 */          public ObjectBidirectionalIterator<Short2CharMap.Entry> iterator(Short2CharMap.Entry from) { return new Short2CharAVLTreeMap.Submap.SubmapEntryIterator(Short2CharAVLTreeMap.Submap.this, ((Short)from.getKey()).shortValue()); }
/* 1395:     */          
/* 1396:1396 */          public Comparator<? super Short2CharMap.Entry> comparator() { return Short2CharAVLTreeMap.this.entrySet().comparator(); }
/* 1397:     */          
/* 1398:     */          public boolean contains(Object o) {
/* 1399:1399 */            if (!(o instanceof Map.Entry)) return false;
/* 1400:1400 */            Map.Entry<Short, Character> e = (Map.Entry)o;
/* 1401:1401 */            Short2CharAVLTreeMap.Entry f = Short2CharAVLTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1402:1402 */            return (f != null) && (Short2CharAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1403:     */          }
/* 1404:     */          
/* 1405:     */          public boolean remove(Object o) {
/* 1406:1406 */            if (!(o instanceof Map.Entry)) return false;
/* 1407:1407 */            Map.Entry<Short, Character> e = (Map.Entry)o;
/* 1408:1408 */            Short2CharAVLTreeMap.Entry f = Short2CharAVLTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1409:1409 */            if ((f != null) && (Short2CharAVLTreeMap.Submap.this.in(f.key))) Short2CharAVLTreeMap.Submap.this.remove(f.key);
/* 1410:1410 */            return f != null;
/* 1411:     */          }
/* 1412:     */          
/* 1413:1413 */          public int size() { int c = 0;
/* 1414:1414 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1415:1415 */            return c;
/* 1416:     */          }
/* 1417:     */          
/* 1418:1418 */          public boolean isEmpty() { return !new Short2CharAVLTreeMap.Submap.SubmapIterator(Short2CharAVLTreeMap.Submap.this).hasNext(); }
/* 1419:     */          
/* 1421:1421 */          public void clear() { Short2CharAVLTreeMap.Submap.this.clear(); }
/* 1422:     */          
/* 1423:1423 */          public Short2CharMap.Entry first() { return Short2CharAVLTreeMap.Submap.this.firstEntry(); }
/* 1424:1424 */          public Short2CharMap.Entry last() { return Short2CharAVLTreeMap.Submap.this.lastEntry(); }
/* 1425:1425 */          public ObjectSortedSet<Short2CharMap.Entry> subSet(Short2CharMap.Entry from, Short2CharMap.Entry to) { return Short2CharAVLTreeMap.Submap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2CharEntrySet(); }
/* 1426:1426 */          public ObjectSortedSet<Short2CharMap.Entry> headSet(Short2CharMap.Entry to) { return Short2CharAVLTreeMap.Submap.this.headMap((Short)to.getKey()).short2CharEntrySet(); }
/* 1427:1427 */          public ObjectSortedSet<Short2CharMap.Entry> tailSet(Short2CharMap.Entry from) { return Short2CharAVLTreeMap.Submap.this.tailMap((Short)from.getKey()).short2CharEntrySet(); }
/* 1428:     */        };
/* 1429:1429 */      return this.entries; }
/* 1430:     */    
/* 1431:1431 */    private class KeySet extends AbstractShort2CharSortedMap.KeySet { private KeySet() { super(); }
/* 1432:1432 */      public ShortBidirectionalIterator iterator() { return new Short2CharAVLTreeMap.Submap.SubmapKeyIterator(Short2CharAVLTreeMap.Submap.this); }
/* 1433:1433 */      public ShortBidirectionalIterator iterator(short from) { return new Short2CharAVLTreeMap.Submap.SubmapKeyIterator(Short2CharAVLTreeMap.Submap.this, from); }
/* 1434:     */    }
/* 1435:     */    
/* 1436:1436 */    public ShortSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1437:1437 */      return this.keys;
/* 1438:     */    }
/* 1439:     */    
/* 1440:1440 */    public CharCollection values() { if (this.values == null) { this.values = new AbstractCharCollection() {
/* 1441:     */          public CharIterator iterator() {
/* 1442:1442 */            return new Short2CharAVLTreeMap.Submap.SubmapValueIterator(Short2CharAVLTreeMap.Submap.this, null);
/* 1443:     */          }
/* 1444:     */          
/* 1445:1445 */          public boolean contains(char k) { return Short2CharAVLTreeMap.Submap.this.containsValue(k); }
/* 1446:     */          
/* 1447:     */          public int size() {
/* 1448:1448 */            return Short2CharAVLTreeMap.Submap.this.size();
/* 1449:     */          }
/* 1450:     */          
/* 1451:1451 */          public void clear() { Short2CharAVLTreeMap.Submap.this.clear(); }
/* 1452:     */        };
/* 1453:     */      }
/* 1454:1454 */      return this.values;
/* 1455:     */    }
/* 1456:     */    
/* 1458:1458 */    public boolean containsKey(short k) { return (in(k)) && (Short2CharAVLTreeMap.this.containsKey(k)); }
/* 1459:     */    
/* 1460:     */    public boolean containsValue(char v) {
/* 1461:1461 */      SubmapIterator i = new SubmapIterator();
/* 1462:     */      
/* 1463:1463 */      while (i.hasNext()) {
/* 1464:1464 */        char ev = i.nextEntry().value;
/* 1465:1465 */        if (ev == v) return true;
/* 1466:     */      }
/* 1467:1467 */      return false;
/* 1468:     */    }
/* 1469:     */    
/* 1470:     */    public char get(short k)
/* 1471:     */    {
/* 1472:1472 */      short kk = k;
/* 1473:1473 */      Short2CharAVLTreeMap.Entry e; return (in(kk)) && ((e = Short2CharAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1474:     */    }
/* 1475:     */    
/* 1476:1476 */    public char put(short k, char v) { Short2CharAVLTreeMap.this.modified = false;
/* 1477:1477 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1478:1478 */      char oldValue = Short2CharAVLTreeMap.this.put(k, v);
/* 1479:1479 */      return Short2CharAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1480:     */    }
/* 1481:     */    
/* 1482:1482 */    public Character put(Short ok, Character ov) { char oldValue = put(ok.shortValue(), ov.charValue());
/* 1483:1483 */      return Short2CharAVLTreeMap.this.modified ? null : Character.valueOf(oldValue);
/* 1484:     */    }
/* 1485:     */    
/* 1486:     */    public char remove(short k) {
/* 1487:1487 */      Short2CharAVLTreeMap.this.modified = false;
/* 1488:1488 */      if (!in(k)) return this.defRetValue;
/* 1489:1489 */      char oldValue = Short2CharAVLTreeMap.this.remove(k);
/* 1490:1490 */      return Short2CharAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1491:     */    }
/* 1492:     */    
/* 1493:1493 */    public Character remove(Object ok) { char oldValue = remove(((Short)ok).shortValue());
/* 1494:1494 */      return Short2CharAVLTreeMap.this.modified ? Character.valueOf(oldValue) : null;
/* 1495:     */    }
/* 1496:     */    
/* 1497:1497 */    public int size() { SubmapIterator i = new SubmapIterator();
/* 1498:1498 */      int n = 0;
/* 1499:1499 */      while (i.hasNext()) {
/* 1500:1500 */        n++;
/* 1501:1501 */        i.nextEntry();
/* 1502:     */      }
/* 1503:1503 */      return n;
/* 1504:     */    }
/* 1505:     */    
/* 1506:1506 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1507:     */    
/* 1509:1509 */    public ShortComparator comparator() { return Short2CharAVLTreeMap.this.actualComparator; }
/* 1510:     */    
/* 1511:     */    public Short2CharSortedMap headMap(short to) {
/* 1512:1512 */      if (this.top) return new Submap(Short2CharAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1513:1513 */      return Short2CharAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Short2CharAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1514:     */    }
/* 1515:     */    
/* 1516:1516 */    public Short2CharSortedMap tailMap(short from) { if (this.bottom) return new Submap(Short2CharAVLTreeMap.this, from, false, this.to, this.top);
/* 1517:1517 */      return Short2CharAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Short2CharAVLTreeMap.this, from, false, this.to, this.top) : this;
/* 1518:     */    }
/* 1519:     */    
/* 1520:1520 */    public Short2CharSortedMap subMap(short from, short to) { if ((this.top) && (this.bottom)) return new Submap(Short2CharAVLTreeMap.this, from, false, to, false);
/* 1521:1521 */      if (!this.top) to = Short2CharAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1522:1522 */      if (!this.bottom) from = Short2CharAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1523:1523 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1524:1524 */      return new Submap(Short2CharAVLTreeMap.this, from, false, to, false);
/* 1525:     */    }
/* 1526:     */    
/* 1529:     */    public Short2CharAVLTreeMap.Entry firstEntry()
/* 1530:     */    {
/* 1531:1531 */      if (Short2CharAVLTreeMap.this.tree == null) return null;
/* 1532:     */      Short2CharAVLTreeMap.Entry e;
/* 1533:     */      Short2CharAVLTreeMap.Entry e;
/* 1534:1534 */      if (this.bottom) { e = Short2CharAVLTreeMap.this.firstEntry;
/* 1535:     */      } else {
/* 1536:1536 */        e = Short2CharAVLTreeMap.this.locateKey(this.from);
/* 1537:     */        
/* 1538:1538 */        if (Short2CharAVLTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1539:     */        }
/* 1540:     */      }
/* 1541:1541 */      if ((e == null) || ((!this.top) && (Short2CharAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1542:1542 */      return e;
/* 1543:     */    }
/* 1544:     */    
/* 1547:     */    public Short2CharAVLTreeMap.Entry lastEntry()
/* 1548:     */    {
/* 1549:1549 */      if (Short2CharAVLTreeMap.this.tree == null) return null;
/* 1550:     */      Short2CharAVLTreeMap.Entry e;
/* 1551:     */      Short2CharAVLTreeMap.Entry e;
/* 1552:1552 */      if (this.top) { e = Short2CharAVLTreeMap.this.lastEntry;
/* 1553:     */      } else {
/* 1554:1554 */        e = Short2CharAVLTreeMap.this.locateKey(this.to);
/* 1555:     */        
/* 1556:1556 */        if (Short2CharAVLTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1557:     */        }
/* 1558:     */      }
/* 1559:1559 */      if ((e == null) || ((!this.bottom) && (Short2CharAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1560:1560 */      return e;
/* 1561:     */    }
/* 1562:     */    
/* 1563:1563 */    public short firstShortKey() { Short2CharAVLTreeMap.Entry e = firstEntry();
/* 1564:1564 */      if (e == null) throw new NoSuchElementException();
/* 1565:1565 */      return e.key;
/* 1566:     */    }
/* 1567:     */    
/* 1568:1568 */    public short lastShortKey() { Short2CharAVLTreeMap.Entry e = lastEntry();
/* 1569:1569 */      if (e == null) throw new NoSuchElementException();
/* 1570:1570 */      return e.key;
/* 1571:     */    }
/* 1572:     */    
/* 1573:1573 */    public Short firstKey() { Short2CharAVLTreeMap.Entry e = firstEntry();
/* 1574:1574 */      if (e == null) throw new NoSuchElementException();
/* 1575:1575 */      return e.getKey();
/* 1576:     */    }
/* 1577:     */    
/* 1578:1578 */    public Short lastKey() { Short2CharAVLTreeMap.Entry e = lastEntry();
/* 1579:1579 */      if (e == null) throw new NoSuchElementException();
/* 1580:1580 */      return e.getKey();
/* 1581:     */    }
/* 1582:     */    
/* 1585:     */    private class SubmapIterator
/* 1586:     */      extends Short2CharAVLTreeMap.TreeIterator
/* 1587:     */    {
/* 1588:     */      SubmapIterator()
/* 1589:     */      {
/* 1590:1590 */        super();
/* 1591:1591 */        this.next = Short2CharAVLTreeMap.Submap.this.firstEntry();
/* 1592:     */      }
/* 1593:     */      
/* 1594:1594 */      SubmapIterator(short k) { this();
/* 1595:1595 */        if (this.next != null)
/* 1596:1596 */          if ((!Short2CharAVLTreeMap.Submap.this.bottom) && (Short2CharAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1597:1597 */          } else if ((!Short2CharAVLTreeMap.Submap.this.top) && (Short2CharAVLTreeMap.this.compare(k, (this.prev = Short2CharAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1598:     */          } else {
/* 1599:1599 */            this.next = Short2CharAVLTreeMap.this.locateKey(k);
/* 1600:1600 */            if (Short2CharAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1601:1601 */              this.prev = this.next;
/* 1602:1602 */              this.next = this.next.next();
/* 1603:     */            } else {
/* 1604:1604 */              this.prev = this.next.prev();
/* 1605:     */            }
/* 1606:     */          }
/* 1607:     */      }
/* 1608:     */      
/* 1609:1609 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1610:1610 */        if ((!Short2CharAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Short2CharAVLTreeMap.this.compare(this.prev.key, Short2CharAVLTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1611:     */      }
/* 1612:     */      
/* 1613:1613 */      void updateNext() { this.next = this.next.next();
/* 1614:1614 */        if ((!Short2CharAVLTreeMap.Submap.this.top) && (this.next != null) && (Short2CharAVLTreeMap.this.compare(this.next.key, Short2CharAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1615:     */      }
/* 1616:     */    }
/* 1617:     */    
/* 1618:1618 */    private class SubmapEntryIterator extends Short2CharAVLTreeMap.Submap.SubmapIterator implements ObjectListIterator<Short2CharMap.Entry> { SubmapEntryIterator() { super(); }
/* 1619:     */      
/* 1620:1620 */      SubmapEntryIterator(short k) { super(k); }
/* 1621:     */      
/* 1622:1622 */      public Short2CharMap.Entry next() { return nextEntry(); }
/* 1623:1623 */      public Short2CharMap.Entry previous() { return previousEntry(); }
/* 1624:1624 */      public void set(Short2CharMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1625:1625 */      public void add(Short2CharMap.Entry ok) { throw new UnsupportedOperationException(); }
/* 1626:     */    }
/* 1627:     */    
/* 1632:     */    private final class SubmapKeyIterator
/* 1633:     */      extends Short2CharAVLTreeMap.Submap.SubmapIterator
/* 1634:     */      implements ShortListIterator
/* 1635:     */    {
/* 1636:1636 */      public SubmapKeyIterator() { super(); }
/* 1637:1637 */      public SubmapKeyIterator(short from) { super(from); }
/* 1638:1638 */      public short nextShort() { return nextEntry().key; }
/* 1639:1639 */      public short previousShort() { return previousEntry().key; }
/* 1640:1640 */      public void set(short k) { throw new UnsupportedOperationException(); }
/* 1641:1641 */      public void add(short k) { throw new UnsupportedOperationException(); }
/* 1642:1642 */      public Short next() { return Short.valueOf(nextEntry().key); }
/* 1643:1643 */      public Short previous() { return Short.valueOf(previousEntry().key); }
/* 1644:1644 */      public void set(Short ok) { throw new UnsupportedOperationException(); }
/* 1645:1645 */      public void add(Short ok) { throw new UnsupportedOperationException(); }
/* 1646:     */    }
/* 1647:     */    
/* 1651:     */    private final class SubmapValueIterator
/* 1652:     */      extends Short2CharAVLTreeMap.Submap.SubmapIterator
/* 1653:     */      implements CharListIterator
/* 1654:     */    {
/* 1655:1655 */      private SubmapValueIterator() { super(); }
/* 1656:1656 */      public char nextChar() { return nextEntry().value; }
/* 1657:1657 */      public char previousChar() { return previousEntry().value; }
/* 1658:1658 */      public void set(char v) { throw new UnsupportedOperationException(); }
/* 1659:1659 */      public void add(char v) { throw new UnsupportedOperationException(); }
/* 1660:1660 */      public Character next() { return Character.valueOf(nextEntry().value); }
/* 1661:1661 */      public Character previous() { return Character.valueOf(previousEntry().value); }
/* 1662:1662 */      public void set(Character ok) { throw new UnsupportedOperationException(); }
/* 1663:1663 */      public void add(Character ok) { throw new UnsupportedOperationException(); }
/* 1664:     */    }
/* 1665:     */  }
/* 1666:     */  
/* 1670:     */  public Short2CharAVLTreeMap clone()
/* 1671:     */  {
/* 1672:     */    Short2CharAVLTreeMap c;
/* 1673:     */    
/* 1675:     */    try
/* 1676:     */    {
/* 1677:1677 */      c = (Short2CharAVLTreeMap)super.clone();
/* 1678:     */    }
/* 1679:     */    catch (CloneNotSupportedException cantHappen) {
/* 1680:1680 */      throw new InternalError();
/* 1681:     */    }
/* 1682:1682 */    c.keys = null;
/* 1683:1683 */    c.values = null;
/* 1684:1684 */    c.entries = null;
/* 1685:1685 */    c.allocatePaths();
/* 1686:1686 */    if (this.count != 0)
/* 1687:     */    {
/* 1688:1688 */      Entry rp = new Entry();Entry rq = new Entry();
/* 1689:1689 */      Entry p = rp;
/* 1690:1690 */      rp.left(this.tree);
/* 1691:1691 */      Entry q = rq;
/* 1692:1692 */      rq.pred(null);
/* 1693:     */      for (;;) {
/* 1694:1694 */        if (!p.pred()) {
/* 1695:1695 */          Entry e = p.left.clone();
/* 1696:1696 */          e.pred(q.left);
/* 1697:1697 */          e.succ(q);
/* 1698:1698 */          q.left(e);
/* 1699:1699 */          p = p.left;
/* 1700:1700 */          q = q.left;
/* 1701:     */        }
/* 1702:     */        else {
/* 1703:1703 */          while (p.succ()) {
/* 1704:1704 */            p = p.right;
/* 1705:1705 */            if (p == null) {
/* 1706:1706 */              q.right = null;
/* 1707:1707 */              c.tree = rq.left;
/* 1708:1708 */              c.firstEntry = c.tree;
/* 1709:1709 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1710:1710 */              c.lastEntry = c.tree;
/* 1711:1711 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1712:1712 */              return c;
/* 1713:     */            }
/* 1714:1714 */            q = q.right;
/* 1715:     */          }
/* 1716:1716 */          p = p.right;
/* 1717:1717 */          q = q.right;
/* 1718:     */        }
/* 1719:1719 */        if (!p.succ()) {
/* 1720:1720 */          Entry e = p.right.clone();
/* 1721:1721 */          e.succ(q.right);
/* 1722:1722 */          e.pred(q);
/* 1723:1723 */          q.right(e);
/* 1724:     */        }
/* 1725:     */      }
/* 1726:     */    }
/* 1727:1727 */    return c;
/* 1728:     */  }
/* 1729:     */  
/* 1730:1730 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1731:1731 */    EntryIterator i = new EntryIterator();
/* 1732:     */    
/* 1733:1733 */    s.defaultWriteObject();
/* 1734:1734 */    while (n-- != 0) {
/* 1735:1735 */      Entry e = i.nextEntry();
/* 1736:1736 */      s.writeShort(e.key);
/* 1737:1737 */      s.writeChar(e.value);
/* 1738:     */    }
/* 1739:     */  }
/* 1740:     */  
/* 1746:     */  private Entry readTree(ObjectInputStream s, int n, Entry pred, Entry succ)
/* 1747:     */    throws IOException, ClassNotFoundException
/* 1748:     */  {
/* 1749:1749 */    if (n == 1) {
/* 1750:1750 */      Entry top = new Entry(s.readShort(), s.readChar());
/* 1751:1751 */      top.pred(pred);
/* 1752:1752 */      top.succ(succ);
/* 1753:1753 */      return top;
/* 1754:     */    }
/* 1755:1755 */    if (n == 2)
/* 1756:     */    {
/* 1758:1758 */      Entry top = new Entry(s.readShort(), s.readChar());
/* 1759:1759 */      top.right(new Entry(s.readShort(), s.readChar()));
/* 1760:1760 */      top.right.pred(top);
/* 1761:1761 */      top.balance(1);
/* 1762:1762 */      top.pred(pred);
/* 1763:1763 */      top.right.succ(succ);
/* 1764:1764 */      return top;
/* 1765:     */    }
/* 1766:     */    
/* 1767:1767 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1768:1768 */    Entry top = new Entry();
/* 1769:1769 */    top.left(readTree(s, leftN, pred, top));
/* 1770:1770 */    top.key = s.readShort();
/* 1771:1771 */    top.value = s.readChar();
/* 1772:1772 */    top.right(readTree(s, rightN, top, succ));
/* 1773:1773 */    if (n == (n & -n)) top.balance(1);
/* 1774:1774 */    return top;
/* 1775:     */  }
/* 1776:     */  
/* 1777:1777 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1778:     */    
/* 1780:1780 */    setActualComparator();
/* 1781:1781 */    allocatePaths();
/* 1782:1782 */    if (this.count != 0) {
/* 1783:1783 */      this.tree = readTree(s, this.count, null, null);
/* 1784:     */      
/* 1785:1785 */      Entry e = this.tree;
/* 1786:1786 */      while (e.left() != null) e = e.left();
/* 1787:1787 */      this.firstEntry = e;
/* 1788:1788 */      e = this.tree;
/* 1789:1789 */      while (e.right() != null) e = e.right();
/* 1790:1790 */      this.lastEntry = e;
/* 1791:     */    }
/* 1792:     */  }
/* 1793:     */  
/* 1794:     */  private static int checkTree(Entry e) {
/* 1795:1795 */    return 0;
/* 1796:     */  }
/* 1797:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2CharAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */