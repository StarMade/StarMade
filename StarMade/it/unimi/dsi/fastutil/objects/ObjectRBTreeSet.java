/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.ObjectInputStream;
/*    5:     */import java.io.ObjectOutputStream;
/*    6:     */import java.io.Serializable;
/*    7:     */import java.util.Collection;
/*    8:     */import java.util.Comparator;
/*    9:     */import java.util.Iterator;
/*   10:     */import java.util.NoSuchElementException;
/*   11:     */import java.util.SortedSet;
/*   12:     */
/*   56:     */public class ObjectRBTreeSet<K>
/*   57:     */  extends AbstractObjectSortedSet<K>
/*   58:     */  implements Serializable, Cloneable, ObjectSortedSet<K>
/*   59:     */{
/*   60:     */  protected transient Entry<K> tree;
/*   61:     */  protected int count;
/*   62:     */  protected transient Entry<K> firstEntry;
/*   63:     */  protected transient Entry<K> lastEntry;
/*   64:     */  protected Comparator<? super K> storedComparator;
/*   65:     */  protected transient Comparator<? super K> actualComparator;
/*   66:     */  public static final long serialVersionUID = -7046029254386353130L;
/*   67:     */  private static final boolean ASSERTS = false;
/*   68:     */  private transient boolean[] dirPath;
/*   69:     */  private transient Entry<K>[] nodePath;
/*   70:     */  
/*   71:     */  public ObjectRBTreeSet()
/*   72:     */  {
/*   73:  73 */    allocatePaths();
/*   74:     */    
/*   78:  78 */    this.tree = null;
/*   79:  79 */    this.count = 0;
/*   80:     */  }
/*   81:     */  
/*   89:     */  private void setActualComparator()
/*   90:     */  {
/*   91:  91 */    this.actualComparator = this.storedComparator;
/*   92:     */  }
/*   93:     */  
/*  105:     */  public ObjectRBTreeSet(Comparator<? super K> c)
/*  106:     */  {
/*  107: 107 */    this();
/*  108: 108 */    this.storedComparator = c;
/*  109: 109 */    setActualComparator();
/*  110:     */  }
/*  111:     */  
/*  117:     */  public ObjectRBTreeSet(Collection<? extends K> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    addAll(c);
/*  121:     */  }
/*  122:     */  
/*  127:     */  public ObjectRBTreeSet(SortedSet<K> s)
/*  128:     */  {
/*  129: 129 */    this(s.comparator());
/*  130: 130 */    addAll(s);
/*  131:     */  }
/*  132:     */  
/*  137:     */  public ObjectRBTreeSet(ObjectCollection<? extends K> c)
/*  138:     */  {
/*  139: 139 */    this();
/*  140: 140 */    addAll(c);
/*  141:     */  }
/*  142:     */  
/*  147:     */  public ObjectRBTreeSet(ObjectSortedSet<K> s)
/*  148:     */  {
/*  149: 149 */    this(s.comparator());
/*  150: 150 */    addAll(s);
/*  151:     */  }
/*  152:     */  
/*  153:     */  public ObjectRBTreeSet(ObjectIterator<? extends K> i)
/*  154:     */  {
/*  155:  73 */    allocatePaths();
/*  156:     */    
/*  242: 160 */    while (i.hasNext()) { add(i.next());
/*  243:     */    }
/*  244:     */  }
/*  245:     */  
/*  251:     */  public ObjectRBTreeSet(Iterator<? extends K> i)
/*  252:     */  {
/*  253: 171 */    this(ObjectIterators.asObjectIterator(i));
/*  254:     */  }
/*  255:     */  
/*  264:     */  public ObjectRBTreeSet(K[] a, int offset, int length, Comparator<? super K> c)
/*  265:     */  {
/*  266: 184 */    this(c);
/*  267: 185 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/*  268: 186 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/*  269:     */    }
/*  270:     */  }
/*  271:     */  
/*  278:     */  public ObjectRBTreeSet(K[] a, int offset, int length)
/*  279:     */  {
/*  280: 198 */    this(a, offset, length, null);
/*  281:     */  }
/*  282:     */  
/*  288:     */  public ObjectRBTreeSet(K[] a)
/*  289:     */  {
/*  290: 208 */    this();
/*  291: 209 */    int i = a.length;
/*  292: 210 */    while (i-- != 0) { add(a[i]);
/*  293:     */    }
/*  294:     */  }
/*  295:     */  
/*  301:     */  public ObjectRBTreeSet(K[] a, Comparator<? super K> c)
/*  302:     */  {
/*  303: 221 */    this(c);
/*  304: 222 */    int i = a.length;
/*  305: 223 */    while (i-- != 0) { add(a[i]);
/*  306:     */    }
/*  307:     */  }
/*  308:     */  
/*  333:     */  final int compare(K k1, K k2)
/*  334:     */  {
/*  335: 253 */    return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*  336:     */  }
/*  337:     */  
/*  345:     */  private Entry<K> findKey(K k)
/*  346:     */  {
/*  347: 265 */    Entry<K> e = this.tree;
/*  348:     */    
/*  349:     */    int cmp;
/*  350: 268 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  351: 269 */      e = cmp < 0 ? e.left() : e.right();
/*  352:     */    }
/*  353: 271 */    return e;
/*  354:     */  }
/*  355:     */  
/*  362:     */  final Entry<K> locateKey(K k)
/*  363:     */  {
/*  364: 282 */    Entry<K> e = this.tree;Entry<K> last = this.tree;
/*  365: 283 */    int cmp = 0;
/*  366:     */    
/*  367: 285 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  368: 286 */      last = e;
/*  369: 287 */      e = cmp < 0 ? e.left() : e.right();
/*  370:     */    }
/*  371:     */    
/*  372: 290 */    return cmp == 0 ? e : last;
/*  373:     */  }
/*  374:     */  
/*  380:     */  private void allocatePaths()
/*  381:     */  {
/*  382: 300 */    this.dirPath = new boolean[64];
/*  383:     */    
/*  384: 302 */    this.nodePath = new Entry[64];
/*  385:     */  }
/*  386:     */  
/*  390:     */  public boolean add(K k)
/*  391:     */  {
/*  392: 310 */    int maxDepth = 0;
/*  393:     */    
/*  394: 312 */    if (this.tree == null) {
/*  395: 313 */      this.count += 1;
/*  396: 314 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k));
/*  397:     */    }
/*  398:     */    else {
/*  399: 317 */      Entry<K> p = this.tree;
/*  400: 318 */      int i = 0;
/*  401:     */      for (;;) {
/*  402:     */        int cmp;
/*  403: 321 */        if ((cmp = compare(k, p.key)) == 0)
/*  404:     */        {
/*  405: 323 */          while (i-- != 0) this.nodePath[i] = null;
/*  406: 324 */          return false;
/*  407:     */        }
/*  408:     */        
/*  409: 327 */        this.nodePath[i] = p;
/*  410:     */        
/*  411: 329 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  412: 330 */          if (p.succ()) {
/*  413: 331 */            this.count += 1;
/*  414: 332 */            Entry<K> e = new Entry(k);
/*  415:     */            
/*  416: 334 */            if (p.right == null) { this.lastEntry = e;
/*  417:     */            }
/*  418: 336 */            e.left = p;
/*  419: 337 */            e.right = p.right;
/*  420:     */            
/*  421: 339 */            p.right(e);
/*  422:     */            
/*  423: 341 */            break;
/*  424:     */          }
/*  425:     */          
/*  426: 344 */          p = p.right;
/*  427:     */        }
/*  428:     */        else {
/*  429: 347 */          if (p.pred()) {
/*  430: 348 */            this.count += 1;
/*  431: 349 */            Entry<K> e = new Entry(k);
/*  432:     */            
/*  433: 351 */            if (p.left == null) { this.firstEntry = e;
/*  434:     */            }
/*  435: 353 */            e.right = p;
/*  436: 354 */            e.left = p.left;
/*  437:     */            
/*  438: 356 */            p.left(e);
/*  439:     */            
/*  440: 358 */            break;
/*  441:     */          }
/*  442:     */          
/*  443: 361 */          p = p.left;
/*  444:     */        }
/*  445:     */      }
/*  446:     */      Entry<K> e;
/*  447: 365 */      maxDepth = i--;
/*  448:     */      
/*  449: 367 */      while ((i > 0) && (!this.nodePath[i].black())) {
/*  450: 368 */        if (this.dirPath[(i - 1)] == 0) {
/*  451: 369 */          Entry<K> y = this.nodePath[(i - 1)].right;
/*  452:     */          
/*  453: 371 */          if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  454: 372 */            this.nodePath[i].black(true);
/*  455: 373 */            y.black(true);
/*  456: 374 */            this.nodePath[(i - 1)].black(false);
/*  457: 375 */            i -= 2;
/*  459:     */          }
/*  460:     */          else
/*  461:     */          {
/*  462: 380 */            if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*  463:     */            } else {
/*  464: 382 */              Entry<K> x = this.nodePath[i];
/*  465: 383 */              y = x.right;
/*  466: 384 */              x.right = y.left;
/*  467: 385 */              y.left = x;
/*  468: 386 */              this.nodePath[(i - 1)].left = y;
/*  469:     */              
/*  470: 388 */              if (y.pred()) {
/*  471: 389 */                y.pred(false);
/*  472: 390 */                x.succ(y);
/*  473:     */              }
/*  474:     */            }
/*  475:     */            
/*  476: 394 */            Entry<K> x = this.nodePath[(i - 1)];
/*  477: 395 */            x.black(false);
/*  478: 396 */            y.black(true);
/*  479:     */            
/*  480: 398 */            x.left = y.right;
/*  481: 399 */            y.right = x;
/*  482: 400 */            if (i < 2) { this.tree = y;
/*  483:     */            }
/*  484: 402 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  485: 403 */              this.nodePath[(i - 2)].left = y;
/*  486:     */            }
/*  487:     */            
/*  488: 406 */            if (!y.succ()) break;
/*  489: 407 */            y.succ(false);
/*  490: 408 */            x.pred(y);break;
/*  491:     */          }
/*  492:     */          
/*  493:     */        }
/*  494:     */        else
/*  495:     */        {
/*  496: 414 */          Entry<K> y = this.nodePath[(i - 1)].left;
/*  497:     */          
/*  498: 416 */          if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  499: 417 */            this.nodePath[i].black(true);
/*  500: 418 */            y.black(true);
/*  501: 419 */            this.nodePath[(i - 1)].black(false);
/*  502: 420 */            i -= 2;
/*  504:     */          }
/*  505:     */          else
/*  506:     */          {
/*  507: 425 */            if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*  508:     */            } else {
/*  509: 427 */              Entry<K> x = this.nodePath[i];
/*  510: 428 */              y = x.left;
/*  511: 429 */              x.left = y.right;
/*  512: 430 */              y.right = x;
/*  513: 431 */              this.nodePath[(i - 1)].right = y;
/*  514:     */              
/*  515: 433 */              if (y.succ()) {
/*  516: 434 */                y.succ(false);
/*  517: 435 */                x.pred(y);
/*  518:     */              }
/*  519:     */            }
/*  520:     */            
/*  522: 440 */            Entry<K> x = this.nodePath[(i - 1)];
/*  523: 441 */            x.black(false);
/*  524: 442 */            y.black(true);
/*  525:     */            
/*  526: 444 */            x.right = y.left;
/*  527: 445 */            y.left = x;
/*  528: 446 */            if (i < 2) { this.tree = y;
/*  529:     */            }
/*  530: 448 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  531: 449 */              this.nodePath[(i - 2)].left = y;
/*  532:     */            }
/*  533:     */            
/*  534: 452 */            if (!y.pred()) break;
/*  535: 453 */            y.pred(false);
/*  536: 454 */            x.succ(y);break;
/*  537:     */          }
/*  538:     */        }
/*  539:     */      }
/*  540:     */    }
/*  541:     */    
/*  544: 462 */    this.tree.black(true);
/*  545:     */    
/*  546: 464 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  547:     */    }
/*  548:     */    
/*  551: 469 */    return true;
/*  552:     */  }
/*  553:     */  
/*  555:     */  public boolean remove(Object k)
/*  556:     */  {
/*  557: 475 */    if (this.tree == null) { return false;
/*  558:     */    }
/*  559: 477 */    Entry<K> p = this.tree;
/*  560:     */    
/*  561: 479 */    int i = 0;
/*  562: 480 */    K kk = k;
/*  563:     */    
/*  564:     */    int cmp;
/*  565: 483 */    while ((cmp = compare(kk, p.key)) != 0)
/*  566:     */    {
/*  567: 485 */      this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  568: 486 */      this.nodePath[i] = p;
/*  569:     */      
/*  570: 488 */      if (this.dirPath[(i++)] != 0) {
/*  571: 489 */        if ((p = p.right()) == null)
/*  572:     */        {
/*  573: 491 */          while (i-- != 0) this.nodePath[i] = null;
/*  574: 492 */          return false;
/*  575:     */        }
/*  576:     */        
/*  577:     */      }
/*  578: 496 */      else if ((p = p.left()) == null)
/*  579:     */      {
/*  580: 498 */        while (i-- != 0) this.nodePath[i] = null;
/*  581: 499 */        return false;
/*  582:     */      }
/*  583:     */    }
/*  584:     */    
/*  586: 504 */    if (p.left == null) this.firstEntry = p.next();
/*  587: 505 */    if (p.right == null) { this.lastEntry = p.prev();
/*  588:     */    }
/*  589: 507 */    if (p.succ()) {
/*  590: 508 */      if (p.pred()) {
/*  591: 509 */        if (i == 0) { this.tree = p.left;
/*  592:     */        }
/*  593: 511 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else {
/*  594: 512 */          this.nodePath[(i - 1)].pred(p.left);
/*  595:     */        }
/*  596:     */      }
/*  597:     */      else {
/*  598: 516 */        p.prev().right = p.right;
/*  599:     */        
/*  600: 518 */        if (i == 0) { this.tree = p.left;
/*  601:     */        }
/*  602: 520 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  603: 521 */          this.nodePath[(i - 1)].left = p.left;
/*  604:     */        }
/*  605:     */      }
/*  606:     */    }
/*  607:     */    else
/*  608:     */    {
/*  609: 527 */      Entry<K> r = p.right;
/*  610:     */      
/*  611: 529 */      if (r.pred()) {
/*  612: 530 */        r.left = p.left;
/*  613: 531 */        r.pred(p.pred());
/*  614: 532 */        if (!r.pred()) r.prev().right = r;
/*  615: 533 */        if (i == 0) { this.tree = r;
/*  616:     */        }
/*  617: 535 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  618: 536 */          this.nodePath[(i - 1)].left = r;
/*  619:     */        }
/*  620:     */        
/*  621: 539 */        boolean color = r.black();
/*  622: 540 */        r.black(p.black());
/*  623: 541 */        p.black(color);
/*  624: 542 */        this.dirPath[i] = true;
/*  625: 543 */        this.nodePath[(i++)] = r;
/*  626:     */      }
/*  627:     */      else
/*  628:     */      {
/*  629: 547 */        int j = i++;
/*  630:     */        Entry<K> s;
/*  631:     */        for (;;) {
/*  632: 550 */          this.dirPath[i] = false;
/*  633: 551 */          this.nodePath[(i++)] = r;
/*  634: 552 */          s = r.left;
/*  635: 553 */          if (s.pred()) break;
/*  636: 554 */          r = s;
/*  637:     */        }
/*  638:     */        
/*  639: 557 */        this.dirPath[j] = true;
/*  640: 558 */        this.nodePath[j] = s;
/*  641:     */        
/*  642: 560 */        if (s.succ()) r.pred(s); else {
/*  643: 561 */          r.left = s.right;
/*  644:     */        }
/*  645: 563 */        s.left = p.left;
/*  646:     */        
/*  647: 565 */        if (!p.pred()) {
/*  648: 566 */          p.prev().right = s;
/*  649: 567 */          s.pred(false);
/*  650:     */        }
/*  651:     */        
/*  652: 570 */        s.right(p.right);
/*  653:     */        
/*  654: 572 */        boolean color = s.black();
/*  655: 573 */        s.black(p.black());
/*  656: 574 */        p.black(color);
/*  657:     */        
/*  658: 576 */        if (j == 0) { this.tree = s;
/*  659:     */        }
/*  660: 578 */        else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  661: 579 */          this.nodePath[(j - 1)].left = s;
/*  662:     */        }
/*  663:     */      }
/*  664:     */    }
/*  665:     */    
/*  666: 584 */    int maxDepth = i;
/*  667:     */    
/*  668: 586 */    if (p.black()) {
/*  669: 587 */      for (; i > 0; i--) {
/*  670: 588 */        if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*  671:     */        {
/*  672: 590 */          Entry<K> x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  673:     */          
/*  674: 592 */          if (!x.black()) {
/*  675: 593 */            x.black(true);
/*  676: 594 */            break;
/*  677:     */          }
/*  678:     */        }
/*  679:     */        
/*  680: 598 */        if (this.dirPath[(i - 1)] == 0) {
/*  681: 599 */          Entry<K> w = this.nodePath[(i - 1)].right;
/*  682:     */          
/*  683: 601 */          if (!w.black()) {
/*  684: 602 */            w.black(true);
/*  685: 603 */            this.nodePath[(i - 1)].black(false);
/*  686:     */            
/*  687: 605 */            this.nodePath[(i - 1)].right = w.left;
/*  688: 606 */            w.left = this.nodePath[(i - 1)];
/*  689:     */            
/*  690: 608 */            if (i < 2) { this.tree = w;
/*  691:     */            }
/*  692: 610 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  693: 611 */              this.nodePath[(i - 2)].left = w;
/*  694:     */            }
/*  695:     */            
/*  696: 614 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  697: 615 */            this.dirPath[i] = false;
/*  698: 616 */            this.nodePath[(i - 1)] = w;
/*  699: 617 */            if (maxDepth == i++) { maxDepth++;
/*  700:     */            }
/*  701: 619 */            w = this.nodePath[(i - 1)].right;
/*  702:     */          }
/*  703:     */          
/*  704: 622 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  705:     */          {
/*  706: 624 */            w.black(false);
/*  707:     */          }
/*  708:     */          else {
/*  709: 627 */            if ((w.succ()) || (w.right.black())) {
/*  710: 628 */              Entry<K> y = w.left;
/*  711:     */              
/*  712: 630 */              y.black(true);
/*  713: 631 */              w.black(false);
/*  714: 632 */              w.left = y.right;
/*  715: 633 */              y.right = w;
/*  716: 634 */              w = this.nodePath[(i - 1)].right = y;
/*  717:     */              
/*  718: 636 */              if (w.succ()) {
/*  719: 637 */                w.succ(false);
/*  720: 638 */                w.right.pred(w);
/*  721:     */              }
/*  722:     */            }
/*  723:     */            
/*  724: 642 */            w.black(this.nodePath[(i - 1)].black());
/*  725: 643 */            this.nodePath[(i - 1)].black(true);
/*  726: 644 */            w.right.black(true);
/*  727:     */            
/*  728: 646 */            this.nodePath[(i - 1)].right = w.left;
/*  729: 647 */            w.left = this.nodePath[(i - 1)];
/*  730:     */            
/*  731: 649 */            if (i < 2) { this.tree = w;
/*  732:     */            }
/*  733: 651 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  734: 652 */              this.nodePath[(i - 2)].left = w;
/*  735:     */            }
/*  736:     */            
/*  737: 655 */            if (!w.pred()) break;
/*  738: 656 */            w.pred(false);
/*  739: 657 */            this.nodePath[(i - 1)].succ(w);break;
/*  740:     */          }
/*  741:     */          
/*  742:     */        }
/*  743:     */        else
/*  744:     */        {
/*  745: 663 */          Entry<K> w = this.nodePath[(i - 1)].left;
/*  746:     */          
/*  747: 665 */          if (!w.black()) {
/*  748: 666 */            w.black(true);
/*  749: 667 */            this.nodePath[(i - 1)].black(false);
/*  750:     */            
/*  751: 669 */            this.nodePath[(i - 1)].left = w.right;
/*  752: 670 */            w.right = this.nodePath[(i - 1)];
/*  753:     */            
/*  754: 672 */            if (i < 2) { this.tree = w;
/*  755:     */            }
/*  756: 674 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  757: 675 */              this.nodePath[(i - 2)].left = w;
/*  758:     */            }
/*  759:     */            
/*  760: 678 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  761: 679 */            this.dirPath[i] = true;
/*  762: 680 */            this.nodePath[(i - 1)] = w;
/*  763: 681 */            if (maxDepth == i++) { maxDepth++;
/*  764:     */            }
/*  765: 683 */            w = this.nodePath[(i - 1)].left;
/*  766:     */          }
/*  767:     */          
/*  768: 686 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  769:     */          {
/*  770: 688 */            w.black(false);
/*  771:     */          }
/*  772:     */          else {
/*  773: 691 */            if ((w.pred()) || (w.left.black())) {
/*  774: 692 */              Entry<K> y = w.right;
/*  775:     */              
/*  776: 694 */              y.black(true);
/*  777: 695 */              w.black(false);
/*  778: 696 */              w.right = y.left;
/*  779: 697 */              y.left = w;
/*  780: 698 */              w = this.nodePath[(i - 1)].left = y;
/*  781:     */              
/*  782: 700 */              if (w.pred()) {
/*  783: 701 */                w.pred(false);
/*  784: 702 */                w.left.succ(w);
/*  785:     */              }
/*  786:     */            }
/*  787:     */            
/*  788: 706 */            w.black(this.nodePath[(i - 1)].black());
/*  789: 707 */            this.nodePath[(i - 1)].black(true);
/*  790: 708 */            w.left.black(true);
/*  791:     */            
/*  792: 710 */            this.nodePath[(i - 1)].left = w.right;
/*  793: 711 */            w.right = this.nodePath[(i - 1)];
/*  794:     */            
/*  795: 713 */            if (i < 2) { this.tree = w;
/*  796:     */            }
/*  797: 715 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  798: 716 */              this.nodePath[(i - 2)].left = w;
/*  799:     */            }
/*  800:     */            
/*  801: 719 */            if (!w.succ()) break;
/*  802: 720 */            w.succ(false);
/*  803: 721 */            this.nodePath[(i - 1)].pred(w);break;
/*  804:     */          }
/*  805:     */        }
/*  806:     */      }
/*  807:     */      
/*  810: 728 */      if (this.tree != null) { this.tree.black(true);
/*  811:     */      }
/*  812:     */    }
/*  813: 731 */    this.count -= 1;
/*  814:     */    
/*  815: 733 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  816:     */    }
/*  817:     */    
/*  820: 738 */    return true;
/*  821:     */  }
/*  822:     */  
/*  825: 743 */  public boolean contains(Object k) { return findKey(k) != null; }
/*  826:     */  
/*  827:     */  public void clear() {
/*  828: 746 */    this.count = 0;
/*  829: 747 */    this.tree = null;
/*  830: 748 */    this.firstEntry = (this.lastEntry = null);
/*  831:     */  }
/*  832:     */  
/*  835:     */  private static final class Entry<K>
/*  836:     */    implements Cloneable
/*  837:     */  {
/*  838:     */    private static final int BLACK_MASK = 1;
/*  839:     */    
/*  841:     */    private static final int SUCC_MASK = -2147483648;
/*  842:     */    
/*  843:     */    private static final int PRED_MASK = 1073741824;
/*  844:     */    
/*  845:     */    K key;
/*  846:     */    
/*  847:     */    Entry<K> left;
/*  848:     */    
/*  849:     */    Entry<K> right;
/*  850:     */    
/*  851:     */    int info;
/*  852:     */    
/*  854:     */    Entry() {}
/*  855:     */    
/*  857:     */    Entry(K k)
/*  858:     */    {
/*  859: 777 */      this.key = k;
/*  860: 778 */      this.info = -1073741824;
/*  861:     */    }
/*  862:     */    
/*  866:     */    Entry<K> left()
/*  867:     */    {
/*  868: 786 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  869:     */    }
/*  870:     */    
/*  874:     */    Entry<K> right()
/*  875:     */    {
/*  876: 794 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  877:     */    }
/*  878:     */    
/*  880:     */    boolean pred()
/*  881:     */    {
/*  882: 800 */      return (this.info & 0x40000000) != 0;
/*  883:     */    }
/*  884:     */    
/*  886:     */    boolean succ()
/*  887:     */    {
/*  888: 806 */      return (this.info & 0x80000000) != 0;
/*  889:     */    }
/*  890:     */    
/*  892:     */    void pred(boolean pred)
/*  893:     */    {
/*  894: 812 */      if (pred) this.info |= 1073741824; else {
/*  895: 813 */        this.info &= -1073741825;
/*  896:     */      }
/*  897:     */    }
/*  898:     */    
/*  899:     */    void succ(boolean succ)
/*  900:     */    {
/*  901: 819 */      if (succ) this.info |= -2147483648; else {
/*  902: 820 */        this.info &= 2147483647;
/*  903:     */      }
/*  904:     */    }
/*  905:     */    
/*  906:     */    void pred(Entry<K> pred)
/*  907:     */    {
/*  908: 826 */      this.info |= 1073741824;
/*  909: 827 */      this.left = pred;
/*  910:     */    }
/*  911:     */    
/*  913:     */    void succ(Entry<K> succ)
/*  914:     */    {
/*  915: 833 */      this.info |= -2147483648;
/*  916: 834 */      this.right = succ;
/*  917:     */    }
/*  918:     */    
/*  920:     */    void left(Entry<K> left)
/*  921:     */    {
/*  922: 840 */      this.info &= -1073741825;
/*  923: 841 */      this.left = left;
/*  924:     */    }
/*  925:     */    
/*  927:     */    void right(Entry<K> right)
/*  928:     */    {
/*  929: 847 */      this.info &= 2147483647;
/*  930: 848 */      this.right = right;
/*  931:     */    }
/*  932:     */    
/*  934:     */    boolean black()
/*  935:     */    {
/*  936: 854 */      return (this.info & 0x1) != 0;
/*  937:     */    }
/*  938:     */    
/*  940:     */    void black(boolean black)
/*  941:     */    {
/*  942: 860 */      if (black) this.info |= 1; else {
/*  943: 861 */        this.info &= -2;
/*  944:     */      }
/*  945:     */    }
/*  946:     */    
/*  948:     */    Entry<K> next()
/*  949:     */    {
/*  950: 868 */      Entry<K> next = this.right;
/*  951: 869 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  952: 870 */      return next;
/*  953:     */    }
/*  954:     */    
/*  957:     */    Entry<K> prev()
/*  958:     */    {
/*  959: 877 */      Entry<K> prev = this.left;
/*  960: 878 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  961: 879 */      return prev;
/*  962:     */    }
/*  963:     */    
/*  964:     */    public Entry<K> clone() {
/*  965:     */      Entry<K> c;
/*  966:     */      try {
/*  967: 885 */        c = (Entry)super.clone();
/*  968:     */      }
/*  969:     */      catch (CloneNotSupportedException cantHappen) {
/*  970: 888 */        throw new InternalError();
/*  971:     */      }
/*  972: 890 */      c.key = this.key;
/*  973: 891 */      c.info = this.info;
/*  974: 892 */      return c;
/*  975:     */    }
/*  976:     */    
/*  977: 895 */    public boolean equals(Object o) { if (!(o instanceof Entry)) return false;
/*  978: 896 */      Entry<?> e = (Entry)o;
/*  979: 897 */      return this.key == null ? false : e.key == null ? true : this.key.equals(e.key);
/*  980:     */    }
/*  981:     */    
/*  982: 900 */    public int hashCode() { return this.key == null ? 0 : this.key.hashCode(); }
/*  983:     */    
/*  984:     */    public String toString() {
/*  985: 903 */      return String.valueOf(this.key);
/*  986:     */    }
/*  987:     */  }
/*  988:     */  
/* 1018:     */  public int size()
/* 1019:     */  {
/* 1020: 938 */    return this.count;
/* 1021:     */  }
/* 1022:     */  
/* 1023: 941 */  public boolean isEmpty() { return this.count == 0; }
/* 1024:     */  
/* 1025:     */  public K first() {
/* 1026: 944 */    if (this.tree == null) throw new NoSuchElementException();
/* 1027: 945 */    return this.firstEntry.key;
/* 1028:     */  }
/* 1029:     */  
/* 1030: 948 */  public K last() { if (this.tree == null) throw new NoSuchElementException();
/* 1031: 949 */    return this.lastEntry.key;
/* 1032:     */  }
/* 1033:     */  
/* 1036:     */  private class SetIterator
/* 1037:     */    extends AbstractObjectListIterator<K>
/* 1038:     */  {
/* 1039:     */    ObjectRBTreeSet.Entry<K> prev;
/* 1040:     */    
/* 1041:     */    ObjectRBTreeSet.Entry<K> next;
/* 1042:     */    
/* 1043:     */    ObjectRBTreeSet.Entry<K> curr;
/* 1044:     */    
/* 1045: 963 */    int index = 0;
/* 1046:     */    
/* 1047: 965 */    SetIterator() { this.next = ObjectRBTreeSet.this.firstEntry; }
/* 1048:     */    
/* 1049:     */    SetIterator() {
/* 1050: 968 */      if ((this.next = ObjectRBTreeSet.this.locateKey(k)) != null)
/* 1051: 969 */        if (ObjectRBTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1052: 970 */          this.prev = this.next;
/* 1053: 971 */          this.next = this.next.next();
/* 1054:     */        } else {
/* 1055: 973 */          this.prev = this.next.prev();
/* 1056:     */        } }
/* 1057:     */    
/* 1058: 976 */    public boolean hasNext() { return this.next != null; }
/* 1059: 977 */    public boolean hasPrevious() { return this.prev != null; }
/* 1060:     */    
/* 1061: 979 */    void updateNext() { this.next = this.next.next(); }
/* 1062:     */    
/* 1063:     */    ObjectRBTreeSet.Entry<K> nextEntry() {
/* 1064: 982 */      if (!hasNext()) throw new NoSuchElementException();
/* 1065: 983 */      this.curr = (this.prev = this.next);
/* 1066: 984 */      this.index += 1;
/* 1067: 985 */      updateNext();
/* 1068: 986 */      return this.curr; }
/* 1069:     */    
/* 1070: 988 */    public K next() { return nextEntry().key; }
/* 1071: 989 */    public K previous() { return previousEntry().key; }
/* 1072:     */    
/* 1073: 991 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1074:     */    
/* 1075:     */    ObjectRBTreeSet.Entry<K> previousEntry() {
/* 1076: 994 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1077: 995 */      this.curr = (this.next = this.prev);
/* 1078: 996 */      this.index -= 1;
/* 1079: 997 */      updatePrevious();
/* 1080: 998 */      return this.curr;
/* 1081:     */    }
/* 1082:     */    
/* 1083:1001 */    public int nextIndex() { return this.index; }
/* 1084:     */    
/* 1086:1004 */    public int previousIndex() { return this.index - 1; }
/* 1087:     */    
/* 1088:     */    public void remove() {
/* 1089:1007 */      if (this.curr == null) { throw new IllegalStateException();
/* 1090:     */      }
/* 1091:     */      
/* 1092:1010 */      if (this.curr == this.prev) this.index -= 1;
/* 1093:1011 */      this.next = (this.prev = this.curr);
/* 1094:1012 */      updatePrevious();
/* 1095:1013 */      updateNext();
/* 1096:1014 */      ObjectRBTreeSet.this.remove(this.curr.key);
/* 1097:1015 */      this.curr = null;
/* 1098:     */    }
/* 1099:     */  }
/* 1100:     */  
/* 1101:1019 */  public ObjectBidirectionalIterator<K> iterator() { return new SetIterator(); }
/* 1102:     */  
/* 1103:     */  public ObjectBidirectionalIterator<K> iterator(K from) {
/* 1104:1022 */    return new SetIterator(from);
/* 1105:     */  }
/* 1106:     */  
/* 1107:1025 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1108:     */  
/* 1109:     */  public ObjectSortedSet<K> headSet(K to) {
/* 1110:1028 */    return new Subset(null, true, to, false);
/* 1111:     */  }
/* 1112:     */  
/* 1113:1031 */  public ObjectSortedSet<K> tailSet(K from) { return new Subset(from, false, null, true); }
/* 1114:     */  
/* 1115:     */  public ObjectSortedSet<K> subSet(K from, K to) {
/* 1116:1034 */    return new Subset(from, false, to, false);
/* 1117:     */  }
/* 1118:     */  
/* 1122:     */  private final class Subset
/* 1123:     */    extends AbstractObjectSortedSet<K>
/* 1124:     */    implements Serializable, ObjectSortedSet<K>
/* 1125:     */  {
/* 1126:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1127:     */    
/* 1130:     */    K from;
/* 1131:     */    
/* 1133:     */    K to;
/* 1134:     */    
/* 1136:     */    boolean bottom;
/* 1137:     */    
/* 1139:     */    boolean top;
/* 1140:     */    
/* 1143:     */    public Subset(boolean from, K bottom, boolean to)
/* 1144:     */    {
/* 1145:1063 */      if ((!bottom) && (!top) && (ObjectRBTreeSet.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start element (").append(from).append(") is larger than end element (").append(to).append(")").toString());
/* 1146:1064 */      this.from = from;
/* 1147:1065 */      this.bottom = bottom;
/* 1148:1066 */      this.to = to;
/* 1149:1067 */      this.top = top;
/* 1150:     */    }
/* 1151:     */    
/* 1152:1070 */    public void clear() { ObjectRBTreeSet<K>.Subset.SubsetIterator i = new SubsetIterator();
/* 1153:1071 */      while (i.hasNext()) {
/* 1154:1072 */        i.next();
/* 1155:1073 */        i.remove();
/* 1156:     */      }
/* 1157:     */    }
/* 1158:     */    
/* 1161:     */    final boolean in(K k)
/* 1162:     */    {
/* 1163:1081 */      return ((this.bottom) || (ObjectRBTreeSet.this.compare(k, this.from) >= 0)) && ((this.top) || (ObjectRBTreeSet.this.compare(k, this.to) < 0));
/* 1164:     */    }
/* 1165:     */    
/* 1168:1086 */    public boolean contains(Object k) { return (in(k)) && (ObjectRBTreeSet.this.contains(k)); }
/* 1169:     */    
/* 1170:     */    public boolean add(K k) {
/* 1171:1089 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Element (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1172:1090 */      return ObjectRBTreeSet.this.add(k);
/* 1173:     */    }
/* 1174:     */    
/* 1175:     */    public boolean remove(Object k) {
/* 1176:1094 */      if (!in(k)) return false;
/* 1177:1095 */      return ObjectRBTreeSet.this.remove(k);
/* 1178:     */    }
/* 1179:     */    
/* 1180:1098 */    public int size() { ObjectRBTreeSet<K>.Subset.SubsetIterator i = new SubsetIterator();
/* 1181:1099 */      int n = 0;
/* 1182:1100 */      while (i.hasNext()) {
/* 1183:1101 */        n++;
/* 1184:1102 */        i.next();
/* 1185:     */      }
/* 1186:1104 */      return n;
/* 1187:     */    }
/* 1188:     */    
/* 1189:1107 */    public boolean isEmpty() { return !new SubsetIterator().hasNext(); }
/* 1190:     */    
/* 1191:     */    public Comparator<? super K> comparator() {
/* 1192:1110 */      return ObjectRBTreeSet.this.actualComparator;
/* 1193:     */    }
/* 1194:     */    
/* 1195:1113 */    public ObjectBidirectionalIterator<K> iterator() { return new SubsetIterator(); }
/* 1196:     */    
/* 1198:1116 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new SubsetIterator(from); }
/* 1199:     */    
/* 1200:     */    public ObjectSortedSet<K> headSet(K to) {
/* 1201:1119 */      if (this.top) return new Subset(ObjectRBTreeSet.this, this.from, this.bottom, to, false);
/* 1202:1120 */      return ObjectRBTreeSet.this.compare(to, this.to) < 0 ? new Subset(ObjectRBTreeSet.this, this.from, this.bottom, to, false) : this;
/* 1203:     */    }
/* 1204:     */    
/* 1205:1123 */    public ObjectSortedSet<K> tailSet(K from) { if (this.bottom) return new Subset(ObjectRBTreeSet.this, from, false, this.to, this.top);
/* 1206:1124 */      return ObjectRBTreeSet.this.compare(from, this.from) > 0 ? new Subset(ObjectRBTreeSet.this, from, false, this.to, this.top) : this;
/* 1207:     */    }
/* 1208:     */    
/* 1209:1127 */    public ObjectSortedSet<K> subSet(K from, K to) { if ((this.top) && (this.bottom)) return new Subset(ObjectRBTreeSet.this, from, false, to, false);
/* 1210:1128 */      if (!this.top) to = ObjectRBTreeSet.this.compare(to, this.to) < 0 ? to : this.to;
/* 1211:1129 */      if (!this.bottom) from = ObjectRBTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
/* 1212:1130 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1213:1131 */      return new Subset(ObjectRBTreeSet.this, from, false, to, false);
/* 1214:     */    }
/* 1215:     */    
/* 1218:     */    public ObjectRBTreeSet.Entry<K> firstEntry()
/* 1219:     */    {
/* 1220:1138 */      if (ObjectRBTreeSet.this.tree == null) return null;
/* 1221:     */      ObjectRBTreeSet.Entry<K> e;
/* 1222:     */      ObjectRBTreeSet.Entry<K> e;
/* 1223:1141 */      if (this.bottom) { e = ObjectRBTreeSet.this.firstEntry;
/* 1224:     */      } else {
/* 1225:1143 */        e = ObjectRBTreeSet.this.locateKey(this.from);
/* 1226:     */        
/* 1227:1145 */        if (ObjectRBTreeSet.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1228:     */        }
/* 1229:     */      }
/* 1230:1148 */      if ((e == null) || ((!this.top) && (ObjectRBTreeSet.this.compare(e.key, this.to) >= 0))) return null;
/* 1231:1149 */      return e;
/* 1232:     */    }
/* 1233:     */    
/* 1236:     */    public ObjectRBTreeSet.Entry<K> lastEntry()
/* 1237:     */    {
/* 1238:1156 */      if (ObjectRBTreeSet.this.tree == null) return null;
/* 1239:     */      ObjectRBTreeSet.Entry<K> e;
/* 1240:     */      ObjectRBTreeSet.Entry<K> e;
/* 1241:1159 */      if (this.top) { e = ObjectRBTreeSet.this.lastEntry;
/* 1242:     */      } else {
/* 1243:1161 */        e = ObjectRBTreeSet.this.locateKey(this.to);
/* 1244:     */        
/* 1245:1163 */        if (ObjectRBTreeSet.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1246:     */        }
/* 1247:     */      }
/* 1248:1166 */      if ((e == null) || ((!this.bottom) && (ObjectRBTreeSet.this.compare(e.key, this.from) < 0))) return null;
/* 1249:1167 */      return e;
/* 1250:     */    }
/* 1251:     */    
/* 1252:1170 */    public K first() { ObjectRBTreeSet.Entry<K> e = firstEntry();
/* 1253:1171 */      if (e == null) throw new NoSuchElementException();
/* 1254:1172 */      return e.key;
/* 1255:     */    }
/* 1256:     */    
/* 1257:1175 */    public K last() { ObjectRBTreeSet.Entry<K> e = lastEntry();
/* 1258:1176 */      if (e == null) throw new NoSuchElementException();
/* 1259:1177 */      return e.key;
/* 1260:     */    }
/* 1261:     */    
/* 1264:     */    private final class SubsetIterator
/* 1265:     */      extends ObjectRBTreeSet.SetIterator
/* 1266:     */    {
/* 1267:     */      SubsetIterator()
/* 1268:     */      {
/* 1269:1187 */        super();
/* 1270:1188 */        this.next = ObjectRBTreeSet.Subset.this.firstEntry();
/* 1271:     */      }
/* 1272:     */      
/* 1273:1191 */      SubsetIterator() { this();
/* 1274:1192 */        if (this.next != null)
/* 1275:1193 */          if ((!ObjectRBTreeSet.Subset.this.bottom) && (ObjectRBTreeSet.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1276:1194 */          } else if ((!ObjectRBTreeSet.Subset.this.top) && (ObjectRBTreeSet.this.compare(k, (this.prev = ObjectRBTreeSet.Subset.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1277:     */          } else {
/* 1278:1196 */            this.next = ObjectRBTreeSet.this.locateKey(k);
/* 1279:1197 */            if (ObjectRBTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1280:1198 */              this.prev = this.next;
/* 1281:1199 */              this.next = this.next.next();
/* 1282:     */            } else {
/* 1283:1201 */              this.prev = this.next.prev();
/* 1284:     */            }
/* 1285:     */          }
/* 1286:     */      }
/* 1287:     */      
/* 1288:1206 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1289:1207 */        if ((!ObjectRBTreeSet.Subset.this.bottom) && (this.prev != null) && (ObjectRBTreeSet.this.compare(this.prev.key, ObjectRBTreeSet.Subset.this.from) < 0)) this.prev = null;
/* 1290:     */      }
/* 1291:     */      
/* 1292:1210 */      void updateNext() { this.next = this.next.next();
/* 1293:1211 */        if ((!ObjectRBTreeSet.Subset.this.top) && (this.next != null) && (ObjectRBTreeSet.this.compare(this.next.key, ObjectRBTreeSet.Subset.this.to) >= 0)) { this.next = null;
/* 1294:     */        }
/* 1295:     */      }
/* 1296:     */    }
/* 1297:     */  }
/* 1298:     */  
/* 1301:     */  public Object clone()
/* 1302:     */  {
/* 1303:     */    ObjectRBTreeSet<K> c;
/* 1304:     */    
/* 1306:     */    try
/* 1307:     */    {
/* 1308:1226 */      c = (ObjectRBTreeSet)super.clone();
/* 1309:     */    }
/* 1310:     */    catch (CloneNotSupportedException cantHappen) {
/* 1311:1229 */      throw new InternalError();
/* 1312:     */    }
/* 1313:1231 */    c.allocatePaths();
/* 1314:1232 */    if (this.count != 0)
/* 1315:     */    {
/* 1316:1234 */      Entry<K> rp = new Entry();Entry<K> rq = new Entry();
/* 1317:1235 */      Entry<K> p = rp;
/* 1318:1236 */      rp.left(this.tree);
/* 1319:1237 */      Entry<K> q = rq;
/* 1320:1238 */      rq.pred(null);
/* 1321:     */      for (;;) {
/* 1322:1240 */        if (!p.pred()) {
/* 1323:1241 */          Entry<K> e = p.left.clone();
/* 1324:1242 */          e.pred(q.left);
/* 1325:1243 */          e.succ(q);
/* 1326:1244 */          q.left(e);
/* 1327:1245 */          p = p.left;
/* 1328:1246 */          q = q.left;
/* 1329:     */        }
/* 1330:     */        else {
/* 1331:1249 */          while (p.succ()) {
/* 1332:1250 */            p = p.right;
/* 1333:1251 */            if (p == null) {
/* 1334:1252 */              q.right = null;
/* 1335:1253 */              c.tree = rq.left;
/* 1336:1254 */              c.firstEntry = c.tree;
/* 1337:1255 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1338:1256 */              c.lastEntry = c.tree;
/* 1339:1257 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1340:1258 */              return c;
/* 1341:     */            }
/* 1342:1260 */            q = q.right;
/* 1343:     */          }
/* 1344:1262 */          p = p.right;
/* 1345:1263 */          q = q.right;
/* 1346:     */        }
/* 1347:1265 */        if (!p.succ()) {
/* 1348:1266 */          Entry<K> e = p.right.clone();
/* 1349:1267 */          e.succ(q.right);
/* 1350:1268 */          e.pred(q);
/* 1351:1269 */          q.right(e);
/* 1352:     */        }
/* 1353:     */      }
/* 1354:     */    }
/* 1355:1273 */    return c;
/* 1356:     */  }
/* 1357:     */  
/* 1358:1276 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1359:1277 */    ObjectRBTreeSet<K>.SetIterator i = new SetIterator();
/* 1360:1278 */    s.defaultWriteObject();
/* 1361:1279 */    while (n-- != 0) { s.writeObject(i.next());
/* 1362:     */    }
/* 1363:     */  }
/* 1364:     */  
/* 1369:     */  private Entry<K> readTree(ObjectInputStream s, int n, Entry<K> pred, Entry<K> succ)
/* 1370:     */    throws IOException, ClassNotFoundException
/* 1371:     */  {
/* 1372:1290 */    if (n == 1) {
/* 1373:1291 */      Entry<K> top = new Entry(s.readObject());
/* 1374:1292 */      top.pred(pred);
/* 1375:1293 */      top.succ(succ);
/* 1376:1294 */      top.black(true);
/* 1377:1295 */      return top;
/* 1378:     */    }
/* 1379:1297 */    if (n == 2)
/* 1380:     */    {
/* 1382:1300 */      Entry<K> top = new Entry(s.readObject());
/* 1383:1301 */      top.black(true);
/* 1384:1302 */      top.right(new Entry(s.readObject()));
/* 1385:1303 */      top.right.pred(top);
/* 1386:1304 */      top.pred(pred);
/* 1387:1305 */      top.right.succ(succ);
/* 1388:1306 */      return top;
/* 1389:     */    }
/* 1390:     */    
/* 1391:1309 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1392:1310 */    Entry<K> top = new Entry();
/* 1393:1311 */    top.left(readTree(s, leftN, pred, top));
/* 1394:1312 */    top.key = s.readObject();
/* 1395:1313 */    top.black(true);
/* 1396:1314 */    top.right(readTree(s, rightN, top, succ));
/* 1397:1315 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1398:1316 */    return top;
/* 1399:     */  }
/* 1400:     */  
/* 1401:1319 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1402:     */    
/* 1404:1322 */    setActualComparator();
/* 1405:1323 */    allocatePaths();
/* 1406:1324 */    if (this.count != 0) {
/* 1407:1325 */      this.tree = readTree(s, this.count, null, null);
/* 1408:     */      
/* 1409:1327 */      Entry<K> e = this.tree;
/* 1410:1328 */      while (e.left() != null) e = e.left();
/* 1411:1329 */      this.firstEntry = e;
/* 1412:1330 */      e = this.tree;
/* 1413:1331 */      while (e.right() != null) e = e.right();
/* 1414:1332 */      this.lastEntry = e;
/* 1415:     */    }
/* 1416:     */  }
/* 1417:     */  
/* 1418:     */  private void checkNodePath() {}
/* 1419:     */  
/* 1420:1338 */  private int checkTree(Entry<K> e, int d, int D) { return 0; }
/* 1421:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectRBTreeSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */