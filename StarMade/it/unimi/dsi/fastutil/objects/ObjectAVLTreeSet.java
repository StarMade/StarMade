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
/*   57:     */public class ObjectAVLTreeSet<K>
/*   58:     */  extends AbstractObjectSortedSet<K>
/*   59:     */  implements Serializable, Cloneable, ObjectSortedSet<K>
/*   60:     */{
/*   61:     */  protected transient Entry<K> tree;
/*   62:     */  protected int count;
/*   63:     */  protected transient Entry<K> firstEntry;
/*   64:     */  protected transient Entry<K> lastEntry;
/*   65:     */  protected Comparator<? super K> storedComparator;
/*   66:     */  protected transient Comparator<? super K> actualComparator;
/*   67:     */  public static final long serialVersionUID = -7046029254386353130L;
/*   68:     */  private static final boolean ASSERTS = false;
/*   69:     */  private transient boolean[] dirPath;
/*   70:     */  
/*   71:     */  public ObjectAVLTreeSet()
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
/*  105:     */  public ObjectAVLTreeSet(Comparator<? super K> c)
/*  106:     */  {
/*  107: 107 */    this();
/*  108: 108 */    this.storedComparator = c;
/*  109: 109 */    setActualComparator();
/*  110:     */  }
/*  111:     */  
/*  117:     */  public ObjectAVLTreeSet(Collection<? extends K> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    addAll(c);
/*  121:     */  }
/*  122:     */  
/*  127:     */  public ObjectAVLTreeSet(SortedSet<K> s)
/*  128:     */  {
/*  129: 129 */    this(s.comparator());
/*  130: 130 */    addAll(s);
/*  131:     */  }
/*  132:     */  
/*  137:     */  public ObjectAVLTreeSet(ObjectCollection<? extends K> c)
/*  138:     */  {
/*  139: 139 */    this();
/*  140: 140 */    addAll(c);
/*  141:     */  }
/*  142:     */  
/*  147:     */  public ObjectAVLTreeSet(ObjectSortedSet<K> s)
/*  148:     */  {
/*  149: 149 */    this(s.comparator());
/*  150: 150 */    addAll(s);
/*  151:     */  }
/*  152:     */  
/*  153:     */  public ObjectAVLTreeSet(ObjectIterator<? extends K> i)
/*  154:     */  {
/*  155:  73 */    allocatePaths();
/*  156:     */    
/*  242: 160 */    while (i.hasNext()) { add(i.next());
/*  243:     */    }
/*  244:     */  }
/*  245:     */  
/*  251:     */  public ObjectAVLTreeSet(Iterator<? extends K> i)
/*  252:     */  {
/*  253: 171 */    this(ObjectIterators.asObjectIterator(i));
/*  254:     */  }
/*  255:     */  
/*  264:     */  public ObjectAVLTreeSet(K[] a, int offset, int length, Comparator<? super K> c)
/*  265:     */  {
/*  266: 184 */    this(c);
/*  267: 185 */    ObjectArrays.ensureOffsetLength(a, offset, length);
/*  268: 186 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/*  269:     */    }
/*  270:     */  }
/*  271:     */  
/*  278:     */  public ObjectAVLTreeSet(K[] a, int offset, int length)
/*  279:     */  {
/*  280: 198 */    this(a, offset, length, null);
/*  281:     */  }
/*  282:     */  
/*  288:     */  public ObjectAVLTreeSet(K[] a)
/*  289:     */  {
/*  290: 208 */    this();
/*  291: 209 */    int i = a.length;
/*  292: 210 */    while (i-- != 0) { add(a[i]);
/*  293:     */    }
/*  294:     */  }
/*  295:     */  
/*  301:     */  public ObjectAVLTreeSet(K[] a, Comparator<? super K> c)
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
/*  379:     */  private void allocatePaths()
/*  380:     */  {
/*  381: 299 */    this.dirPath = new boolean[48];
/*  382:     */  }
/*  383:     */  
/*  385:     */  public boolean add(K k)
/*  386:     */  {
/*  387: 305 */    if (this.tree == null) {
/*  388: 306 */      this.count += 1;
/*  389: 307 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k));
/*  390:     */    }
/*  391:     */    else {
/*  392: 310 */      Entry<K> p = this.tree;Entry<K> q = null;Entry<K> y = this.tree;Entry<K> z = null;Entry<K> e = null;Entry<K> w = null;
/*  393: 311 */      int i = 0;
/*  394:     */      for (;;) {
/*  395:     */        int cmp;
/*  396: 314 */        if ((cmp = compare(k, p.key)) == 0) { return false;
/*  397:     */        }
/*  398: 316 */        if (p.balance() != 0) {
/*  399: 317 */          i = 0;
/*  400: 318 */          z = q;
/*  401: 319 */          y = p;
/*  402:     */        }
/*  403:     */        
/*  404: 322 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  405: 323 */          if (p.succ()) {
/*  406: 324 */            this.count += 1;
/*  407: 325 */            e = new Entry(k);
/*  408:     */            
/*  409: 327 */            if (p.right == null) { this.lastEntry = e;
/*  410:     */            }
/*  411: 329 */            e.left = p;
/*  412: 330 */            e.right = p.right;
/*  413:     */            
/*  414: 332 */            p.right(e);
/*  415:     */            
/*  416: 334 */            break;
/*  417:     */          }
/*  418:     */          
/*  419: 337 */          q = p;
/*  420: 338 */          p = p.right;
/*  421:     */        }
/*  422:     */        else {
/*  423: 341 */          if (p.pred()) {
/*  424: 342 */            this.count += 1;
/*  425: 343 */            e = new Entry(k);
/*  426:     */            
/*  427: 345 */            if (p.left == null) { this.firstEntry = e;
/*  428:     */            }
/*  429: 347 */            e.right = p;
/*  430: 348 */            e.left = p.left;
/*  431:     */            
/*  432: 350 */            p.left(e);
/*  433:     */            
/*  434: 352 */            break;
/*  435:     */          }
/*  436:     */          
/*  437: 355 */          q = p;
/*  438: 356 */          p = p.left;
/*  439:     */        }
/*  440:     */      }
/*  441:     */      
/*  442: 360 */      p = y;
/*  443: 361 */      i = 0;
/*  444:     */      
/*  445: 363 */      while (p != e) {
/*  446: 364 */        if (this.dirPath[i] != 0) p.incBalance(); else {
/*  447: 365 */          p.decBalance();
/*  448:     */        }
/*  449: 367 */        p = this.dirPath[(i++)] != 0 ? p.right : p.left;
/*  450:     */      }
/*  451:     */      
/*  452: 370 */      if (y.balance() == -2) {
/*  453: 371 */        Entry<K> x = y.left;
/*  454:     */        
/*  455: 373 */        if (x.balance() == -1) {
/*  456: 374 */          w = x;
/*  457: 375 */          if (x.succ()) {
/*  458: 376 */            x.succ(false);
/*  459: 377 */            y.pred(x);
/*  460:     */          } else {
/*  461: 379 */            y.left = x.right;
/*  462:     */          }
/*  463: 381 */          x.right = y;
/*  464: 382 */          x.balance(0);
/*  465: 383 */          y.balance(0);
/*  467:     */        }
/*  468:     */        else
/*  469:     */        {
/*  470: 388 */          w = x.right;
/*  471: 389 */          x.right = w.left;
/*  472: 390 */          w.left = x;
/*  473: 391 */          y.left = w.right;
/*  474: 392 */          w.right = y;
/*  475: 393 */          if (w.balance() == -1) {
/*  476: 394 */            x.balance(0);
/*  477: 395 */            y.balance(1);
/*  478:     */          }
/*  479: 397 */          else if (w.balance() == 0) {
/*  480: 398 */            x.balance(0);
/*  481: 399 */            y.balance(0);
/*  482:     */          }
/*  483:     */          else {
/*  484: 402 */            x.balance(-1);
/*  485: 403 */            y.balance(0);
/*  486:     */          }
/*  487: 405 */          w.balance(0);
/*  488:     */          
/*  490: 408 */          if (w.pred()) {
/*  491: 409 */            x.succ(w);
/*  492: 410 */            w.pred(false);
/*  493:     */          }
/*  494: 412 */          if (w.succ()) {
/*  495: 413 */            y.pred(w);
/*  496: 414 */            w.succ(false);
/*  497:     */          }
/*  498:     */          
/*  499:     */        }
/*  500:     */      }
/*  501: 419 */      else if (y.balance() == 2) {
/*  502: 420 */        Entry<K> x = y.right;
/*  503:     */        
/*  504: 422 */        if (x.balance() == 1) {
/*  505: 423 */          w = x;
/*  506: 424 */          if (x.pred()) {
/*  507: 425 */            x.pred(false);
/*  508: 426 */            y.succ(x);
/*  509:     */          } else {
/*  510: 428 */            y.right = x.left;
/*  511:     */          }
/*  512: 430 */          x.left = y;
/*  513: 431 */          x.balance(0);
/*  514: 432 */          y.balance(0);
/*  516:     */        }
/*  517:     */        else
/*  518:     */        {
/*  519: 437 */          w = x.left;
/*  520: 438 */          x.left = w.right;
/*  521: 439 */          w.right = x;
/*  522: 440 */          y.right = w.left;
/*  523: 441 */          w.left = y;
/*  524: 442 */          if (w.balance() == 1) {
/*  525: 443 */            x.balance(0);
/*  526: 444 */            y.balance(-1);
/*  527:     */          }
/*  528: 446 */          else if (w.balance() == 0) {
/*  529: 447 */            x.balance(0);
/*  530: 448 */            y.balance(0);
/*  531:     */          }
/*  532:     */          else {
/*  533: 451 */            x.balance(1);
/*  534: 452 */            y.balance(0);
/*  535:     */          }
/*  536: 454 */          w.balance(0);
/*  537:     */          
/*  539: 457 */          if (w.pred()) {
/*  540: 458 */            y.succ(w);
/*  541: 459 */            w.pred(false);
/*  542:     */          }
/*  543: 461 */          if (w.succ()) {
/*  544: 462 */            x.pred(w);
/*  545: 463 */            w.succ(false);
/*  546:     */          }
/*  547:     */        }
/*  548:     */      }
/*  549:     */      else {
/*  550: 468 */        return true;
/*  551:     */      }
/*  552: 470 */      if (z == null) { this.tree = w;
/*  553:     */      }
/*  554: 472 */      else if (z.left == y) z.left = w; else {
/*  555: 473 */        z.right = w;
/*  556:     */      }
/*  557:     */    }
/*  558:     */    
/*  560: 478 */    return true;
/*  561:     */  }
/*  562:     */  
/*  571:     */  private Entry<K> parent(Entry<K> e)
/*  572:     */  {
/*  573: 491 */    if (e == this.tree) { return null;
/*  574:     */    }
/*  575:     */    Entry<K> y;
/*  576: 494 */    Entry<K> x = y = e;
/*  577:     */    for (;;)
/*  578:     */    {
/*  579: 497 */      if (y.succ()) {
/*  580: 498 */        Entry<K> p = y.right;
/*  581: 499 */        if ((p == null) || (p.left != e)) {
/*  582: 500 */          while (!x.pred()) x = x.left;
/*  583: 501 */          p = x.left;
/*  584:     */        }
/*  585: 503 */        return p;
/*  586:     */      }
/*  587: 505 */      if (x.pred()) {
/*  588: 506 */        Entry<K> p = x.left;
/*  589: 507 */        if ((p == null) || (p.right != e)) {
/*  590: 508 */          while (!y.succ()) y = y.right;
/*  591: 509 */          p = y.right;
/*  592:     */        }
/*  593: 511 */        return p;
/*  594:     */      }
/*  595:     */      
/*  596: 514 */      x = x.left;
/*  597: 515 */      y = y.right;
/*  598:     */    }
/*  599:     */  }
/*  600:     */  
/*  602:     */  public boolean remove(Object k)
/*  603:     */  {
/*  604: 522 */    if (this.tree == null) { return false;
/*  605:     */    }
/*  606:     */    
/*  607: 525 */    Entry<K> p = this.tree;Entry<K> q = null;
/*  608: 526 */    boolean dir = false;
/*  609: 527 */    K kk = k;
/*  610:     */    
/*  611:     */    int cmp;
/*  612: 530 */    while ((cmp = compare(kk, p.key)) != 0) {
/*  613: 531 */      if ((dir = cmp > 0 ? 1 : 0) != 0) {
/*  614: 532 */        q = p;
/*  615: 533 */        if ((p = p.right()) == null) return false;
/*  616:     */      }
/*  617:     */      else {
/*  618: 536 */        q = p;
/*  619: 537 */        if ((p = p.left()) == null) { return false;
/*  620:     */        }
/*  621:     */      }
/*  622:     */    }
/*  623: 541 */    if (p.left == null) this.firstEntry = p.next();
/*  624: 542 */    if (p.right == null) { this.lastEntry = p.prev();
/*  625:     */    }
/*  626: 544 */    if (p.succ()) {
/*  627: 545 */      if (p.pred()) {
/*  628: 546 */        if (q != null) {
/*  629: 547 */          if (dir) q.succ(p.right); else
/*  630: 548 */            q.pred(p.left);
/*  631:     */        } else {
/*  632: 550 */          this.tree = (dir ? p.right : p.left);
/*  633:     */        }
/*  634:     */      } else {
/*  635: 553 */        p.prev().right = p.right;
/*  636:     */        
/*  637: 555 */        if (q != null) {
/*  638: 556 */          if (dir) q.right = p.left; else
/*  639: 557 */            q.left = p.left;
/*  640:     */        } else {
/*  641: 559 */          this.tree = p.left;
/*  642:     */        }
/*  643:     */      }
/*  644:     */    } else {
/*  645: 563 */      Entry<K> r = p.right;
/*  646:     */      
/*  647: 565 */      if (r.pred()) {
/*  648: 566 */        r.left = p.left;
/*  649: 567 */        r.pred(p.pred());
/*  650: 568 */        if (!r.pred()) r.prev().right = r;
/*  651: 569 */        if (q != null) {
/*  652: 570 */          if (dir) q.right = r; else
/*  653: 571 */            q.left = r;
/*  654:     */        } else {
/*  655: 573 */          this.tree = r;
/*  656:     */        }
/*  657: 575 */        r.balance(p.balance());
/*  658: 576 */        q = r;
/*  659: 577 */        dir = true;
/*  660:     */      }
/*  661:     */      else
/*  662:     */      {
/*  663:     */        Entry<K> s;
/*  664:     */        for (;;)
/*  665:     */        {
/*  666: 584 */          s = r.left;
/*  667: 585 */          if (s.pred()) break;
/*  668: 586 */          r = s;
/*  669:     */        }
/*  670:     */        
/*  671: 589 */        if (s.succ()) r.pred(s); else {
/*  672: 590 */          r.left = s.right;
/*  673:     */        }
/*  674: 592 */        s.left = p.left;
/*  675:     */        
/*  676: 594 */        if (!p.pred()) {
/*  677: 595 */          p.prev().right = s;
/*  678: 596 */          s.pred(false);
/*  679:     */        }
/*  680:     */        
/*  681: 599 */        s.right = p.right;
/*  682: 600 */        s.succ(false);
/*  683:     */        
/*  684: 602 */        if (q != null) {
/*  685: 603 */          if (dir) q.right = s; else
/*  686: 604 */            q.left = s;
/*  687:     */        } else {
/*  688: 606 */          this.tree = s;
/*  689:     */        }
/*  690: 608 */        s.balance(p.balance());
/*  691: 609 */        q = r;
/*  692: 610 */        dir = false;
/*  693:     */      }
/*  694:     */    }
/*  695:     */    
/*  698: 616 */    while (q != null) {
/*  699: 617 */      Entry<K> y = q;
/*  700: 618 */      q = parent(y);
/*  701:     */      
/*  702: 620 */      if (!dir) {
/*  703: 621 */        dir = (q != null) && (q.left != y);
/*  704: 622 */        y.incBalance();
/*  705:     */        
/*  706: 624 */        if (y.balance() == 1) break;
/*  707: 625 */        if (y.balance() == 2)
/*  708:     */        {
/*  709: 627 */          Entry<K> x = y.right;
/*  710:     */          
/*  712: 630 */          if (x.balance() == -1)
/*  713:     */          {
/*  717: 635 */            Entry<K> w = x.left;
/*  718: 636 */            x.left = w.right;
/*  719: 637 */            w.right = x;
/*  720: 638 */            y.right = w.left;
/*  721: 639 */            w.left = y;
/*  722:     */            
/*  723: 641 */            if (w.balance() == 1) {
/*  724: 642 */              x.balance(0);
/*  725: 643 */              y.balance(-1);
/*  726:     */            }
/*  727: 645 */            else if (w.balance() == 0) {
/*  728: 646 */              x.balance(0);
/*  729: 647 */              y.balance(0);
/*  731:     */            }
/*  732:     */            else
/*  733:     */            {
/*  734: 652 */              x.balance(1);
/*  735: 653 */              y.balance(0);
/*  736:     */            }
/*  737:     */            
/*  738: 656 */            w.balance(0);
/*  739:     */            
/*  740: 658 */            if (w.pred()) {
/*  741: 659 */              y.succ(w);
/*  742: 660 */              w.pred(false);
/*  743:     */            }
/*  744: 662 */            if (w.succ()) {
/*  745: 663 */              x.pred(w);
/*  746: 664 */              w.succ(false);
/*  747:     */            }
/*  748:     */            
/*  749: 667 */            if (q != null) {
/*  750: 668 */              if (dir) q.right = w; else
/*  751: 669 */                q.left = w;
/*  752:     */            } else {
/*  753: 671 */              this.tree = w;
/*  754:     */            }
/*  755:     */          } else {
/*  756: 674 */            if (q != null) {
/*  757: 675 */              if (dir) q.right = x; else
/*  758: 676 */                q.left = x;
/*  759:     */            } else {
/*  760: 678 */              this.tree = x;
/*  761:     */            }
/*  762: 680 */            if (x.balance() == 0) {
/*  763: 681 */              y.right = x.left;
/*  764: 682 */              x.left = y;
/*  765: 683 */              x.balance(-1);
/*  766: 684 */              y.balance(1);
/*  767: 685 */              break;
/*  768:     */            }
/*  769:     */            
/*  772: 690 */            if (x.pred()) {
/*  773: 691 */              y.succ(true);
/*  774: 692 */              x.pred(false);
/*  775:     */            } else {
/*  776: 694 */              y.right = x.left;
/*  777:     */            }
/*  778: 696 */            x.left = y;
/*  779: 697 */            y.balance(0);
/*  780: 698 */            x.balance(0);
/*  781:     */          }
/*  782:     */        }
/*  783:     */      }
/*  784:     */      else {
/*  785: 703 */        dir = (q != null) && (q.left != y);
/*  786: 704 */        y.decBalance();
/*  787:     */        
/*  788: 706 */        if (y.balance() == -1) break;
/*  789: 707 */        if (y.balance() == -2)
/*  790:     */        {
/*  791: 709 */          Entry<K> x = y.left;
/*  792:     */          
/*  794: 712 */          if (x.balance() == 1)
/*  795:     */          {
/*  799: 717 */            Entry<K> w = x.right;
/*  800: 718 */            x.right = w.left;
/*  801: 719 */            w.left = x;
/*  802: 720 */            y.left = w.right;
/*  803: 721 */            w.right = y;
/*  804:     */            
/*  805: 723 */            if (w.balance() == -1) {
/*  806: 724 */              x.balance(0);
/*  807: 725 */              y.balance(1);
/*  808:     */            }
/*  809: 727 */            else if (w.balance() == 0) {
/*  810: 728 */              x.balance(0);
/*  811: 729 */              y.balance(0);
/*  813:     */            }
/*  814:     */            else
/*  815:     */            {
/*  816: 734 */              x.balance(-1);
/*  817: 735 */              y.balance(0);
/*  818:     */            }
/*  819:     */            
/*  820: 738 */            w.balance(0);
/*  821:     */            
/*  822: 740 */            if (w.pred()) {
/*  823: 741 */              x.succ(w);
/*  824: 742 */              w.pred(false);
/*  825:     */            }
/*  826: 744 */            if (w.succ()) {
/*  827: 745 */              y.pred(w);
/*  828: 746 */              w.succ(false);
/*  829:     */            }
/*  830:     */            
/*  831: 749 */            if (q != null) {
/*  832: 750 */              if (dir) q.right = w; else
/*  833: 751 */                q.left = w;
/*  834:     */            } else {
/*  835: 753 */              this.tree = w;
/*  836:     */            }
/*  837:     */          } else {
/*  838: 756 */            if (q != null) {
/*  839: 757 */              if (dir) q.right = x; else
/*  840: 758 */                q.left = x;
/*  841:     */            } else {
/*  842: 760 */              this.tree = x;
/*  843:     */            }
/*  844: 762 */            if (x.balance() == 0) {
/*  845: 763 */              y.left = x.right;
/*  846: 764 */              x.right = y;
/*  847: 765 */              x.balance(1);
/*  848: 766 */              y.balance(-1);
/*  849: 767 */              break;
/*  850:     */            }
/*  851:     */            
/*  854: 772 */            if (x.succ()) {
/*  855: 773 */              y.pred(true);
/*  856: 774 */              x.succ(false);
/*  857:     */            } else {
/*  858: 776 */              y.left = x.right;
/*  859:     */            }
/*  860: 778 */            x.right = y;
/*  861: 779 */            y.balance(0);
/*  862: 780 */            x.balance(0);
/*  863:     */          }
/*  864:     */        }
/*  865:     */      }
/*  866:     */    }
/*  867:     */    
/*  868: 786 */    this.count -= 1;
/*  869:     */    
/*  870: 788 */    return true;
/*  871:     */  }
/*  872:     */  
/*  875: 793 */  public boolean contains(Object k) { return findKey(k) != null; }
/*  876:     */  
/*  877:     */  public void clear() {
/*  878: 796 */    this.count = 0;
/*  879: 797 */    this.tree = null;
/*  880: 798 */    this.firstEntry = (this.lastEntry = null);
/*  881:     */  }
/*  882:     */  
/*  885:     */  private static final class Entry<K>
/*  886:     */    implements Cloneable
/*  887:     */  {
/*  888:     */    private static final int SUCC_MASK = -2147483648;
/*  889:     */    
/*  891:     */    private static final int PRED_MASK = 1073741824;
/*  892:     */    
/*  893:     */    private static final int BALANCE_MASK = 255;
/*  894:     */    
/*  895:     */    K key;
/*  896:     */    
/*  897:     */    Entry<K> left;
/*  898:     */    
/*  899:     */    Entry<K> right;
/*  900:     */    
/*  901:     */    int info;
/*  902:     */    
/*  904:     */    Entry() {}
/*  905:     */    
/*  907:     */    Entry(K k)
/*  908:     */    {
/*  909: 827 */      this.key = k;
/*  910: 828 */      this.info = -1073741824;
/*  911:     */    }
/*  912:     */    
/*  916:     */    Entry<K> left()
/*  917:     */    {
/*  918: 836 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  919:     */    }
/*  920:     */    
/*  924:     */    Entry<K> right()
/*  925:     */    {
/*  926: 844 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  927:     */    }
/*  928:     */    
/*  930:     */    boolean pred()
/*  931:     */    {
/*  932: 850 */      return (this.info & 0x40000000) != 0;
/*  933:     */    }
/*  934:     */    
/*  936:     */    boolean succ()
/*  937:     */    {
/*  938: 856 */      return (this.info & 0x80000000) != 0;
/*  939:     */    }
/*  940:     */    
/*  942:     */    void pred(boolean pred)
/*  943:     */    {
/*  944: 862 */      if (pred) this.info |= 1073741824; else {
/*  945: 863 */        this.info &= -1073741825;
/*  946:     */      }
/*  947:     */    }
/*  948:     */    
/*  949:     */    void succ(boolean succ)
/*  950:     */    {
/*  951: 869 */      if (succ) this.info |= -2147483648; else {
/*  952: 870 */        this.info &= 2147483647;
/*  953:     */      }
/*  954:     */    }
/*  955:     */    
/*  956:     */    void pred(Entry<K> pred)
/*  957:     */    {
/*  958: 876 */      this.info |= 1073741824;
/*  959: 877 */      this.left = pred;
/*  960:     */    }
/*  961:     */    
/*  963:     */    void succ(Entry<K> succ)
/*  964:     */    {
/*  965: 883 */      this.info |= -2147483648;
/*  966: 884 */      this.right = succ;
/*  967:     */    }
/*  968:     */    
/*  970:     */    void left(Entry<K> left)
/*  971:     */    {
/*  972: 890 */      this.info &= -1073741825;
/*  973: 891 */      this.left = left;
/*  974:     */    }
/*  975:     */    
/*  977:     */    void right(Entry<K> right)
/*  978:     */    {
/*  979: 897 */      this.info &= 2147483647;
/*  980: 898 */      this.right = right;
/*  981:     */    }
/*  982:     */    
/*  984:     */    int balance()
/*  985:     */    {
/*  986: 904 */      return (byte)this.info;
/*  987:     */    }
/*  988:     */    
/*  990:     */    void balance(int level)
/*  991:     */    {
/*  992: 910 */      this.info &= -256;
/*  993: 911 */      this.info |= level & 0xFF;
/*  994:     */    }
/*  995:     */    
/*  996:     */    void incBalance() {
/*  997: 915 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  998:     */    }
/*  999:     */    
/* 1000:     */    protected void decBalance() {
/* 1001: 919 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/* 1002:     */    }
/* 1003:     */    
/* 1006:     */    Entry<K> next()
/* 1007:     */    {
/* 1008: 926 */      Entry<K> next = this.right;
/* 1009: 927 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/* 1010: 928 */      return next;
/* 1011:     */    }
/* 1012:     */    
/* 1015:     */    Entry<K> prev()
/* 1016:     */    {
/* 1017: 935 */      Entry<K> prev = this.left;
/* 1018: 936 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/* 1019: 937 */      return prev;
/* 1020:     */    }
/* 1021:     */    
/* 1022:     */    public Entry<K> clone() {
/* 1023:     */      Entry<K> c;
/* 1024:     */      try {
/* 1025: 943 */        c = (Entry)super.clone();
/* 1026:     */      }
/* 1027:     */      catch (CloneNotSupportedException cantHappen) {
/* 1028: 946 */        throw new InternalError();
/* 1029:     */      }
/* 1030: 948 */      c.key = this.key;
/* 1031: 949 */      c.info = this.info;
/* 1032: 950 */      return c;
/* 1033:     */    }
/* 1034:     */    
/* 1035: 953 */    public boolean equals(Object o) { if (!(o instanceof Entry)) return false;
/* 1036: 954 */      Entry<?> e = (Entry)o;
/* 1037: 955 */      return this.key == null ? false : e.key == null ? true : this.key.equals(e.key);
/* 1038:     */    }
/* 1039:     */    
/* 1040: 958 */    public int hashCode() { return this.key == null ? 0 : this.key.hashCode(); }
/* 1041:     */    
/* 1042:     */    public String toString() {
/* 1043: 961 */      return String.valueOf(this.key);
/* 1044:     */    }
/* 1045:     */  }
/* 1046:     */  
/* 1076:     */  public int size()
/* 1077:     */  {
/* 1078: 996 */    return this.count;
/* 1079:     */  }
/* 1080:     */  
/* 1081: 999 */  public boolean isEmpty() { return this.count == 0; }
/* 1082:     */  
/* 1083:     */  public K first() {
/* 1084:1002 */    if (this.tree == null) throw new NoSuchElementException();
/* 1085:1003 */    return this.firstEntry.key;
/* 1086:     */  }
/* 1087:     */  
/* 1088:1006 */  public K last() { if (this.tree == null) throw new NoSuchElementException();
/* 1089:1007 */    return this.lastEntry.key;
/* 1090:     */  }
/* 1091:     */  
/* 1094:     */  private class SetIterator
/* 1095:     */    extends AbstractObjectListIterator<K>
/* 1096:     */  {
/* 1097:     */    ObjectAVLTreeSet.Entry<K> prev;
/* 1098:     */    
/* 1099:     */    ObjectAVLTreeSet.Entry<K> next;
/* 1100:     */    
/* 1101:     */    ObjectAVLTreeSet.Entry<K> curr;
/* 1102:     */    
/* 1103:1021 */    int index = 0;
/* 1104:     */    
/* 1105:1023 */    SetIterator() { this.next = ObjectAVLTreeSet.this.firstEntry; }
/* 1106:     */    
/* 1107:     */    SetIterator() {
/* 1108:1026 */      if ((this.next = ObjectAVLTreeSet.this.locateKey(k)) != null)
/* 1109:1027 */        if (ObjectAVLTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1110:1028 */          this.prev = this.next;
/* 1111:1029 */          this.next = this.next.next();
/* 1112:     */        } else {
/* 1113:1031 */          this.prev = this.next.prev();
/* 1114:     */        } }
/* 1115:     */    
/* 1116:1034 */    public boolean hasNext() { return this.next != null; }
/* 1117:1035 */    public boolean hasPrevious() { return this.prev != null; }
/* 1118:     */    
/* 1119:1037 */    void updateNext() { this.next = this.next.next(); }
/* 1120:     */    
/* 1121:     */    ObjectAVLTreeSet.Entry<K> nextEntry() {
/* 1122:1040 */      if (!hasNext()) throw new NoSuchElementException();
/* 1123:1041 */      this.curr = (this.prev = this.next);
/* 1124:1042 */      this.index += 1;
/* 1125:1043 */      updateNext();
/* 1126:1044 */      return this.curr; }
/* 1127:     */    
/* 1128:1046 */    public K next() { return nextEntry().key; }
/* 1129:1047 */    public K previous() { return previousEntry().key; }
/* 1130:     */    
/* 1131:1049 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1132:     */    
/* 1133:     */    ObjectAVLTreeSet.Entry<K> previousEntry() {
/* 1134:1052 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1135:1053 */      this.curr = (this.next = this.prev);
/* 1136:1054 */      this.index -= 1;
/* 1137:1055 */      updatePrevious();
/* 1138:1056 */      return this.curr;
/* 1139:     */    }
/* 1140:     */    
/* 1141:1059 */    public int nextIndex() { return this.index; }
/* 1142:     */    
/* 1144:1062 */    public int previousIndex() { return this.index - 1; }
/* 1145:     */    
/* 1146:     */    public void remove() {
/* 1147:1065 */      if (this.curr == null) { throw new IllegalStateException();
/* 1148:     */      }
/* 1149:     */      
/* 1150:1068 */      if (this.curr == this.prev) this.index -= 1;
/* 1151:1069 */      this.next = (this.prev = this.curr);
/* 1152:1070 */      updatePrevious();
/* 1153:1071 */      updateNext();
/* 1154:1072 */      ObjectAVLTreeSet.this.remove(this.curr.key);
/* 1155:1073 */      this.curr = null;
/* 1156:     */    }
/* 1157:     */  }
/* 1158:     */  
/* 1159:1077 */  public ObjectBidirectionalIterator<K> iterator() { return new SetIterator(); }
/* 1160:     */  
/* 1161:     */  public ObjectBidirectionalIterator<K> iterator(K from) {
/* 1162:1080 */    return new SetIterator(from);
/* 1163:     */  }
/* 1164:     */  
/* 1165:1083 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1166:     */  
/* 1167:     */  public ObjectSortedSet<K> headSet(K to) {
/* 1168:1086 */    return new Subset(null, true, to, false);
/* 1169:     */  }
/* 1170:     */  
/* 1171:1089 */  public ObjectSortedSet<K> tailSet(K from) { return new Subset(from, false, null, true); }
/* 1172:     */  
/* 1173:     */  public ObjectSortedSet<K> subSet(K from, K to) {
/* 1174:1092 */    return new Subset(from, false, to, false);
/* 1175:     */  }
/* 1176:     */  
/* 1180:     */  private final class Subset
/* 1181:     */    extends AbstractObjectSortedSet<K>
/* 1182:     */    implements Serializable, ObjectSortedSet<K>
/* 1183:     */  {
/* 1184:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1185:     */    
/* 1188:     */    K from;
/* 1189:     */    
/* 1191:     */    K to;
/* 1192:     */    
/* 1194:     */    boolean bottom;
/* 1195:     */    
/* 1197:     */    boolean top;
/* 1198:     */    
/* 1201:     */    public Subset(boolean from, K bottom, boolean to)
/* 1202:     */    {
/* 1203:1121 */      if ((!bottom) && (!top) && (ObjectAVLTreeSet.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start element (").append(from).append(") is larger than end element (").append(to).append(")").toString());
/* 1204:1122 */      this.from = from;
/* 1205:1123 */      this.bottom = bottom;
/* 1206:1124 */      this.to = to;
/* 1207:1125 */      this.top = top;
/* 1208:     */    }
/* 1209:     */    
/* 1210:1128 */    public void clear() { ObjectAVLTreeSet<K>.Subset.SubsetIterator i = new SubsetIterator();
/* 1211:1129 */      while (i.hasNext()) {
/* 1212:1130 */        i.next();
/* 1213:1131 */        i.remove();
/* 1214:     */      }
/* 1215:     */    }
/* 1216:     */    
/* 1219:     */    final boolean in(K k)
/* 1220:     */    {
/* 1221:1139 */      return ((this.bottom) || (ObjectAVLTreeSet.this.compare(k, this.from) >= 0)) && ((this.top) || (ObjectAVLTreeSet.this.compare(k, this.to) < 0));
/* 1222:     */    }
/* 1223:     */    
/* 1226:1144 */    public boolean contains(Object k) { return (in(k)) && (ObjectAVLTreeSet.this.contains(k)); }
/* 1227:     */    
/* 1228:     */    public boolean add(K k) {
/* 1229:1147 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Element (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1230:1148 */      return ObjectAVLTreeSet.this.add(k);
/* 1231:     */    }
/* 1232:     */    
/* 1233:     */    public boolean remove(Object k) {
/* 1234:1152 */      if (!in(k)) return false;
/* 1235:1153 */      return ObjectAVLTreeSet.this.remove(k);
/* 1236:     */    }
/* 1237:     */    
/* 1238:1156 */    public int size() { ObjectAVLTreeSet<K>.Subset.SubsetIterator i = new SubsetIterator();
/* 1239:1157 */      int n = 0;
/* 1240:1158 */      while (i.hasNext()) {
/* 1241:1159 */        n++;
/* 1242:1160 */        i.next();
/* 1243:     */      }
/* 1244:1162 */      return n;
/* 1245:     */    }
/* 1246:     */    
/* 1247:1165 */    public boolean isEmpty() { return !new SubsetIterator().hasNext(); }
/* 1248:     */    
/* 1249:     */    public Comparator<? super K> comparator() {
/* 1250:1168 */      return ObjectAVLTreeSet.this.actualComparator;
/* 1251:     */    }
/* 1252:     */    
/* 1253:1171 */    public ObjectBidirectionalIterator<K> iterator() { return new SubsetIterator(); }
/* 1254:     */    
/* 1256:1174 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new SubsetIterator(from); }
/* 1257:     */    
/* 1258:     */    public ObjectSortedSet<K> headSet(K to) {
/* 1259:1177 */      if (this.top) return new Subset(ObjectAVLTreeSet.this, this.from, this.bottom, to, false);
/* 1260:1178 */      return ObjectAVLTreeSet.this.compare(to, this.to) < 0 ? new Subset(ObjectAVLTreeSet.this, this.from, this.bottom, to, false) : this;
/* 1261:     */    }
/* 1262:     */    
/* 1263:1181 */    public ObjectSortedSet<K> tailSet(K from) { if (this.bottom) return new Subset(ObjectAVLTreeSet.this, from, false, this.to, this.top);
/* 1264:1182 */      return ObjectAVLTreeSet.this.compare(from, this.from) > 0 ? new Subset(ObjectAVLTreeSet.this, from, false, this.to, this.top) : this;
/* 1265:     */    }
/* 1266:     */    
/* 1267:1185 */    public ObjectSortedSet<K> subSet(K from, K to) { if ((this.top) && (this.bottom)) return new Subset(ObjectAVLTreeSet.this, from, false, to, false);
/* 1268:1186 */      if (!this.top) to = ObjectAVLTreeSet.this.compare(to, this.to) < 0 ? to : this.to;
/* 1269:1187 */      if (!this.bottom) from = ObjectAVLTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
/* 1270:1188 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1271:1189 */      return new Subset(ObjectAVLTreeSet.this, from, false, to, false);
/* 1272:     */    }
/* 1273:     */    
/* 1276:     */    public ObjectAVLTreeSet.Entry<K> firstEntry()
/* 1277:     */    {
/* 1278:1196 */      if (ObjectAVLTreeSet.this.tree == null) return null;
/* 1279:     */      ObjectAVLTreeSet.Entry<K> e;
/* 1280:     */      ObjectAVLTreeSet.Entry<K> e;
/* 1281:1199 */      if (this.bottom) { e = ObjectAVLTreeSet.this.firstEntry;
/* 1282:     */      } else {
/* 1283:1201 */        e = ObjectAVLTreeSet.this.locateKey(this.from);
/* 1284:     */        
/* 1285:1203 */        if (ObjectAVLTreeSet.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1286:     */        }
/* 1287:     */      }
/* 1288:1206 */      if ((e == null) || ((!this.top) && (ObjectAVLTreeSet.this.compare(e.key, this.to) >= 0))) return null;
/* 1289:1207 */      return e;
/* 1290:     */    }
/* 1291:     */    
/* 1294:     */    public ObjectAVLTreeSet.Entry<K> lastEntry()
/* 1295:     */    {
/* 1296:1214 */      if (ObjectAVLTreeSet.this.tree == null) return null;
/* 1297:     */      ObjectAVLTreeSet.Entry<K> e;
/* 1298:     */      ObjectAVLTreeSet.Entry<K> e;
/* 1299:1217 */      if (this.top) { e = ObjectAVLTreeSet.this.lastEntry;
/* 1300:     */      } else {
/* 1301:1219 */        e = ObjectAVLTreeSet.this.locateKey(this.to);
/* 1302:     */        
/* 1303:1221 */        if (ObjectAVLTreeSet.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1304:     */        }
/* 1305:     */      }
/* 1306:1224 */      if ((e == null) || ((!this.bottom) && (ObjectAVLTreeSet.this.compare(e.key, this.from) < 0))) return null;
/* 1307:1225 */      return e;
/* 1308:     */    }
/* 1309:     */    
/* 1310:1228 */    public K first() { ObjectAVLTreeSet.Entry<K> e = firstEntry();
/* 1311:1229 */      if (e == null) throw new NoSuchElementException();
/* 1312:1230 */      return e.key;
/* 1313:     */    }
/* 1314:     */    
/* 1315:1233 */    public K last() { ObjectAVLTreeSet.Entry<K> e = lastEntry();
/* 1316:1234 */      if (e == null) throw new NoSuchElementException();
/* 1317:1235 */      return e.key;
/* 1318:     */    }
/* 1319:     */    
/* 1322:     */    private final class SubsetIterator
/* 1323:     */      extends ObjectAVLTreeSet.SetIterator
/* 1324:     */    {
/* 1325:     */      SubsetIterator()
/* 1326:     */      {
/* 1327:1245 */        super();
/* 1328:1246 */        this.next = ObjectAVLTreeSet.Subset.this.firstEntry();
/* 1329:     */      }
/* 1330:     */      
/* 1331:1249 */      SubsetIterator() { this();
/* 1332:1250 */        if (this.next != null)
/* 1333:1251 */          if ((!ObjectAVLTreeSet.Subset.this.bottom) && (ObjectAVLTreeSet.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1334:1252 */          } else if ((!ObjectAVLTreeSet.Subset.this.top) && (ObjectAVLTreeSet.this.compare(k, (this.prev = ObjectAVLTreeSet.Subset.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1335:     */          } else {
/* 1336:1254 */            this.next = ObjectAVLTreeSet.this.locateKey(k);
/* 1337:1255 */            if (ObjectAVLTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1338:1256 */              this.prev = this.next;
/* 1339:1257 */              this.next = this.next.next();
/* 1340:     */            } else {
/* 1341:1259 */              this.prev = this.next.prev();
/* 1342:     */            }
/* 1343:     */          }
/* 1344:     */      }
/* 1345:     */      
/* 1346:1264 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1347:1265 */        if ((!ObjectAVLTreeSet.Subset.this.bottom) && (this.prev != null) && (ObjectAVLTreeSet.this.compare(this.prev.key, ObjectAVLTreeSet.Subset.this.from) < 0)) this.prev = null;
/* 1348:     */      }
/* 1349:     */      
/* 1350:1268 */      void updateNext() { this.next = this.next.next();
/* 1351:1269 */        if ((!ObjectAVLTreeSet.Subset.this.top) && (this.next != null) && (ObjectAVLTreeSet.this.compare(this.next.key, ObjectAVLTreeSet.Subset.this.to) >= 0)) { this.next = null;
/* 1352:     */        }
/* 1353:     */      }
/* 1354:     */    }
/* 1355:     */  }
/* 1356:     */  
/* 1359:     */  public Object clone()
/* 1360:     */  {
/* 1361:     */    ObjectAVLTreeSet<K> c;
/* 1362:     */    
/* 1364:     */    try
/* 1365:     */    {
/* 1366:1284 */      c = (ObjectAVLTreeSet)super.clone();
/* 1367:     */    }
/* 1368:     */    catch (CloneNotSupportedException cantHappen) {
/* 1369:1287 */      throw new InternalError();
/* 1370:     */    }
/* 1371:1289 */    c.allocatePaths();
/* 1372:1290 */    if (this.count != 0)
/* 1373:     */    {
/* 1374:1292 */      Entry<K> rp = new Entry();Entry<K> rq = new Entry();
/* 1375:1293 */      Entry<K> p = rp;
/* 1376:1294 */      rp.left(this.tree);
/* 1377:1295 */      Entry<K> q = rq;
/* 1378:1296 */      rq.pred(null);
/* 1379:     */      for (;;) {
/* 1380:1298 */        if (!p.pred()) {
/* 1381:1299 */          Entry<K> e = p.left.clone();
/* 1382:1300 */          e.pred(q.left);
/* 1383:1301 */          e.succ(q);
/* 1384:1302 */          q.left(e);
/* 1385:1303 */          p = p.left;
/* 1386:1304 */          q = q.left;
/* 1387:     */        }
/* 1388:     */        else {
/* 1389:1307 */          while (p.succ()) {
/* 1390:1308 */            p = p.right;
/* 1391:1309 */            if (p == null) {
/* 1392:1310 */              q.right = null;
/* 1393:1311 */              c.tree = rq.left;
/* 1394:1312 */              c.firstEntry = c.tree;
/* 1395:1313 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1396:1314 */              c.lastEntry = c.tree;
/* 1397:1315 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1398:1316 */              return c;
/* 1399:     */            }
/* 1400:1318 */            q = q.right;
/* 1401:     */          }
/* 1402:1320 */          p = p.right;
/* 1403:1321 */          q = q.right;
/* 1404:     */        }
/* 1405:1323 */        if (!p.succ()) {
/* 1406:1324 */          Entry<K> e = p.right.clone();
/* 1407:1325 */          e.succ(q.right);
/* 1408:1326 */          e.pred(q);
/* 1409:1327 */          q.right(e);
/* 1410:     */        }
/* 1411:     */      }
/* 1412:     */    }
/* 1413:1331 */    return c;
/* 1414:     */  }
/* 1415:     */  
/* 1416:1334 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1417:1335 */    ObjectAVLTreeSet<K>.SetIterator i = new SetIterator();
/* 1418:1336 */    s.defaultWriteObject();
/* 1419:1337 */    while (n-- != 0) { s.writeObject(i.next());
/* 1420:     */    }
/* 1421:     */  }
/* 1422:     */  
/* 1427:     */  private Entry<K> readTree(ObjectInputStream s, int n, Entry<K> pred, Entry<K> succ)
/* 1428:     */    throws IOException, ClassNotFoundException
/* 1429:     */  {
/* 1430:1348 */    if (n == 1) {
/* 1431:1349 */      Entry<K> top = new Entry(s.readObject());
/* 1432:1350 */      top.pred(pred);
/* 1433:1351 */      top.succ(succ);
/* 1434:1352 */      return top;
/* 1435:     */    }
/* 1436:1354 */    if (n == 2)
/* 1437:     */    {
/* 1439:1357 */      Entry<K> top = new Entry(s.readObject());
/* 1440:1358 */      top.right(new Entry(s.readObject()));
/* 1441:1359 */      top.right.pred(top);
/* 1442:1360 */      top.balance(1);
/* 1443:1361 */      top.pred(pred);
/* 1444:1362 */      top.right.succ(succ);
/* 1445:1363 */      return top;
/* 1446:     */    }
/* 1447:     */    
/* 1448:1366 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1449:1367 */    Entry<K> top = new Entry();
/* 1450:1368 */    top.left(readTree(s, leftN, pred, top));
/* 1451:1369 */    top.key = s.readObject();
/* 1452:1370 */    top.right(readTree(s, rightN, top, succ));
/* 1453:1371 */    if (n == (n & -n)) top.balance(1);
/* 1454:1372 */    return top;
/* 1455:     */  }
/* 1456:     */  
/* 1457:1375 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1458:     */    
/* 1460:1378 */    setActualComparator();
/* 1461:1379 */    allocatePaths();
/* 1462:1380 */    if (this.count != 0) {
/* 1463:1381 */      this.tree = readTree(s, this.count, null, null);
/* 1464:     */      
/* 1465:1383 */      Entry<K> e = this.tree;
/* 1466:1384 */      while (e.left() != null) e = e.left();
/* 1467:1385 */      this.firstEntry = e;
/* 1468:1386 */      e = this.tree;
/* 1469:1387 */      while (e.right() != null) e = e.right();
/* 1470:1388 */      this.lastEntry = e;
/* 1471:     */    }
/* 1472:     */  }
/* 1473:     */  
/* 1474:     */  private static <K> int checkTree(Entry<K> e) {
/* 1475:1393 */    return 0;
/* 1476:     */  }
/* 1477:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectAVLTreeSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */