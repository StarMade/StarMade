/*    1:     */package it.unimi.dsi.fastutil.bytes;
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
/*   58:     */public class ByteAVLTreeSet
/*   59:     */  extends AbstractByteSortedSet
/*   60:     */  implements Serializable, Cloneable, ByteSortedSet
/*   61:     */{
/*   62:     */  protected transient Entry tree;
/*   63:     */  protected int count;
/*   64:     */  protected transient Entry firstEntry;
/*   65:     */  protected transient Entry lastEntry;
/*   66:     */  protected Comparator<? super Byte> storedComparator;
/*   67:     */  protected transient ByteComparator actualComparator;
/*   68:     */  public static final long serialVersionUID = -7046029254386353130L;
/*   69:     */  private static final boolean ASSERTS = false;
/*   70:     */  private transient boolean[] dirPath;
/*   71:     */  
/*   72:     */  public ByteAVLTreeSet()
/*   73:     */  {
/*   74:  74 */    allocatePaths();
/*   75:     */    
/*   79:  79 */    this.tree = null;
/*   80:  80 */    this.count = 0;
/*   81:     */  }
/*   82:     */  
/*   92:     */  private void setActualComparator()
/*   93:     */  {
/*   94:  94 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof ByteComparator))) this.actualComparator = ((ByteComparator)this.storedComparator); else {
/*   95:  95 */      this.actualComparator = new ByteComparator() {
/*   96:     */        public int compare(byte k1, byte k2) {
/*   97:  97 */          return ByteAVLTreeSet.this.storedComparator.compare(Byte.valueOf(k1), Byte.valueOf(k2));
/*   98:     */        }
/*   99:     */        
/*  100: 100 */        public int compare(Byte ok1, Byte ok2) { return ByteAVLTreeSet.this.storedComparator.compare(ok1, ok2); }
/*  101:     */      };
/*  102:     */    }
/*  103:     */  }
/*  104:     */  
/*  107:     */  public ByteAVLTreeSet(Comparator<? super Byte> c)
/*  108:     */  {
/*  109: 109 */    this();
/*  110: 110 */    this.storedComparator = c;
/*  111: 111 */    setActualComparator();
/*  112:     */  }
/*  113:     */  
/*  117:     */  public ByteAVLTreeSet(Collection<? extends Byte> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    addAll(c);
/*  121:     */  }
/*  122:     */  
/*  127:     */  public ByteAVLTreeSet(SortedSet<Byte> s)
/*  128:     */  {
/*  129: 129 */    this(s.comparator());
/*  130: 130 */    addAll(s);
/*  131:     */  }
/*  132:     */  
/*  137:     */  public ByteAVLTreeSet(ByteCollection c)
/*  138:     */  {
/*  139: 139 */    this();
/*  140: 140 */    addAll(c);
/*  141:     */  }
/*  142:     */  
/*  147:     */  public ByteAVLTreeSet(ByteSortedSet s)
/*  148:     */  {
/*  149: 149 */    this(s.comparator());
/*  150: 150 */    addAll(s);
/*  151:     */  }
/*  152:     */  
/*  153:     */  public ByteAVLTreeSet(ByteIterator i)
/*  154:     */  {
/*  155:  74 */    allocatePaths();
/*  156:     */    
/*  241: 160 */    while (i.hasNext()) { add(i.nextByte());
/*  242:     */    }
/*  243:     */  }
/*  244:     */  
/*  250:     */  public ByteAVLTreeSet(Iterator<? extends Byte> i)
/*  251:     */  {
/*  252: 171 */    this(ByteIterators.asByteIterator(i));
/*  253:     */  }
/*  254:     */  
/*  263:     */  public ByteAVLTreeSet(byte[] a, int offset, int length, Comparator<? super Byte> c)
/*  264:     */  {
/*  265: 184 */    this(c);
/*  266: 185 */    ByteArrays.ensureOffsetLength(a, offset, length);
/*  267: 186 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/*  268:     */    }
/*  269:     */  }
/*  270:     */  
/*  277:     */  public ByteAVLTreeSet(byte[] a, int offset, int length)
/*  278:     */  {
/*  279: 198 */    this(a, offset, length, null);
/*  280:     */  }
/*  281:     */  
/*  287:     */  public ByteAVLTreeSet(byte[] a)
/*  288:     */  {
/*  289: 208 */    this();
/*  290: 209 */    int i = a.length;
/*  291: 210 */    while (i-- != 0) { add(a[i]);
/*  292:     */    }
/*  293:     */  }
/*  294:     */  
/*  300:     */  public ByteAVLTreeSet(byte[] a, Comparator<? super Byte> c)
/*  301:     */  {
/*  302: 221 */    this(c);
/*  303: 222 */    int i = a.length;
/*  304: 223 */    while (i-- != 0) { add(a[i]);
/*  305:     */    }
/*  306:     */  }
/*  307:     */  
/*  332:     */  final int compare(byte k1, byte k2)
/*  333:     */  {
/*  334: 253 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  335:     */  }
/*  336:     */  
/*  344:     */  private Entry findKey(byte k)
/*  345:     */  {
/*  346: 265 */    Entry e = this.tree;
/*  347:     */    
/*  348:     */    int cmp;
/*  349: 268 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  350: 269 */      e = cmp < 0 ? e.left() : e.right();
/*  351:     */    }
/*  352: 271 */    return e;
/*  353:     */  }
/*  354:     */  
/*  361:     */  final Entry locateKey(byte k)
/*  362:     */  {
/*  363: 282 */    Entry e = this.tree;Entry last = this.tree;
/*  364: 283 */    int cmp = 0;
/*  365:     */    
/*  366: 285 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  367: 286 */      last = e;
/*  368: 287 */      e = cmp < 0 ? e.left() : e.right();
/*  369:     */    }
/*  370:     */    
/*  371: 290 */    return cmp == 0 ? e : last;
/*  372:     */  }
/*  373:     */  
/*  378:     */  private void allocatePaths()
/*  379:     */  {
/*  380: 299 */    this.dirPath = new boolean[48];
/*  381:     */  }
/*  382:     */  
/*  384:     */  public boolean add(byte k)
/*  385:     */  {
/*  386: 305 */    if (this.tree == null) {
/*  387: 306 */      this.count += 1;
/*  388: 307 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k));
/*  389:     */    }
/*  390:     */    else {
/*  391: 310 */      Entry p = this.tree;Entry q = null;Entry y = this.tree;Entry z = null;Entry e = null;Entry w = null;
/*  392: 311 */      int i = 0;
/*  393:     */      for (;;) {
/*  394:     */        int cmp;
/*  395: 314 */        if ((cmp = compare(k, p.key)) == 0) { return false;
/*  396:     */        }
/*  397: 316 */        if (p.balance() != 0) {
/*  398: 317 */          i = 0;
/*  399: 318 */          z = q;
/*  400: 319 */          y = p;
/*  401:     */        }
/*  402:     */        
/*  403: 322 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  404: 323 */          if (p.succ()) {
/*  405: 324 */            this.count += 1;
/*  406: 325 */            e = new Entry(k);
/*  407:     */            
/*  408: 327 */            if (p.right == null) { this.lastEntry = e;
/*  409:     */            }
/*  410: 329 */            e.left = p;
/*  411: 330 */            e.right = p.right;
/*  412:     */            
/*  413: 332 */            p.right(e);
/*  414:     */            
/*  415: 334 */            break;
/*  416:     */          }
/*  417:     */          
/*  418: 337 */          q = p;
/*  419: 338 */          p = p.right;
/*  420:     */        }
/*  421:     */        else {
/*  422: 341 */          if (p.pred()) {
/*  423: 342 */            this.count += 1;
/*  424: 343 */            e = new Entry(k);
/*  425:     */            
/*  426: 345 */            if (p.left == null) { this.firstEntry = e;
/*  427:     */            }
/*  428: 347 */            e.right = p;
/*  429: 348 */            e.left = p.left;
/*  430:     */            
/*  431: 350 */            p.left(e);
/*  432:     */            
/*  433: 352 */            break;
/*  434:     */          }
/*  435:     */          
/*  436: 355 */          q = p;
/*  437: 356 */          p = p.left;
/*  438:     */        }
/*  439:     */      }
/*  440:     */      
/*  441: 360 */      p = y;
/*  442: 361 */      i = 0;
/*  443:     */      
/*  444: 363 */      while (p != e) {
/*  445: 364 */        if (this.dirPath[i] != 0) p.incBalance(); else {
/*  446: 365 */          p.decBalance();
/*  447:     */        }
/*  448: 367 */        p = this.dirPath[(i++)] != 0 ? p.right : p.left;
/*  449:     */      }
/*  450:     */      
/*  451: 370 */      if (y.balance() == -2) {
/*  452: 371 */        Entry x = y.left;
/*  453:     */        
/*  454: 373 */        if (x.balance() == -1) {
/*  455: 374 */          w = x;
/*  456: 375 */          if (x.succ()) {
/*  457: 376 */            x.succ(false);
/*  458: 377 */            y.pred(x);
/*  459:     */          } else {
/*  460: 379 */            y.left = x.right;
/*  461:     */          }
/*  462: 381 */          x.right = y;
/*  463: 382 */          x.balance(0);
/*  464: 383 */          y.balance(0);
/*  466:     */        }
/*  467:     */        else
/*  468:     */        {
/*  469: 388 */          w = x.right;
/*  470: 389 */          x.right = w.left;
/*  471: 390 */          w.left = x;
/*  472: 391 */          y.left = w.right;
/*  473: 392 */          w.right = y;
/*  474: 393 */          if (w.balance() == -1) {
/*  475: 394 */            x.balance(0);
/*  476: 395 */            y.balance(1);
/*  477:     */          }
/*  478: 397 */          else if (w.balance() == 0) {
/*  479: 398 */            x.balance(0);
/*  480: 399 */            y.balance(0);
/*  481:     */          }
/*  482:     */          else {
/*  483: 402 */            x.balance(-1);
/*  484: 403 */            y.balance(0);
/*  485:     */          }
/*  486: 405 */          w.balance(0);
/*  487:     */          
/*  489: 408 */          if (w.pred()) {
/*  490: 409 */            x.succ(w);
/*  491: 410 */            w.pred(false);
/*  492:     */          }
/*  493: 412 */          if (w.succ()) {
/*  494: 413 */            y.pred(w);
/*  495: 414 */            w.succ(false);
/*  496:     */          }
/*  497:     */          
/*  498:     */        }
/*  499:     */      }
/*  500: 419 */      else if (y.balance() == 2) {
/*  501: 420 */        Entry x = y.right;
/*  502:     */        
/*  503: 422 */        if (x.balance() == 1) {
/*  504: 423 */          w = x;
/*  505: 424 */          if (x.pred()) {
/*  506: 425 */            x.pred(false);
/*  507: 426 */            y.succ(x);
/*  508:     */          } else {
/*  509: 428 */            y.right = x.left;
/*  510:     */          }
/*  511: 430 */          x.left = y;
/*  512: 431 */          x.balance(0);
/*  513: 432 */          y.balance(0);
/*  515:     */        }
/*  516:     */        else
/*  517:     */        {
/*  518: 437 */          w = x.left;
/*  519: 438 */          x.left = w.right;
/*  520: 439 */          w.right = x;
/*  521: 440 */          y.right = w.left;
/*  522: 441 */          w.left = y;
/*  523: 442 */          if (w.balance() == 1) {
/*  524: 443 */            x.balance(0);
/*  525: 444 */            y.balance(-1);
/*  526:     */          }
/*  527: 446 */          else if (w.balance() == 0) {
/*  528: 447 */            x.balance(0);
/*  529: 448 */            y.balance(0);
/*  530:     */          }
/*  531:     */          else {
/*  532: 451 */            x.balance(1);
/*  533: 452 */            y.balance(0);
/*  534:     */          }
/*  535: 454 */          w.balance(0);
/*  536:     */          
/*  538: 457 */          if (w.pred()) {
/*  539: 458 */            y.succ(w);
/*  540: 459 */            w.pred(false);
/*  541:     */          }
/*  542: 461 */          if (w.succ()) {
/*  543: 462 */            x.pred(w);
/*  544: 463 */            w.succ(false);
/*  545:     */          }
/*  546:     */        }
/*  547:     */      }
/*  548:     */      else {
/*  549: 468 */        return true;
/*  550:     */      }
/*  551: 470 */      if (z == null) { this.tree = w;
/*  552:     */      }
/*  553: 472 */      else if (z.left == y) z.left = w; else {
/*  554: 473 */        z.right = w;
/*  555:     */      }
/*  556:     */    }
/*  557:     */    
/*  559: 478 */    return true;
/*  560:     */  }
/*  561:     */  
/*  570:     */  private Entry parent(Entry e)
/*  571:     */  {
/*  572: 491 */    if (e == this.tree) { return null;
/*  573:     */    }
/*  574:     */    Entry y;
/*  575: 494 */    Entry x = y = e;
/*  576:     */    for (;;)
/*  577:     */    {
/*  578: 497 */      if (y.succ()) {
/*  579: 498 */        Entry p = y.right;
/*  580: 499 */        if ((p == null) || (p.left != e)) {
/*  581: 500 */          while (!x.pred()) x = x.left;
/*  582: 501 */          p = x.left;
/*  583:     */        }
/*  584: 503 */        return p;
/*  585:     */      }
/*  586: 505 */      if (x.pred()) {
/*  587: 506 */        Entry p = x.left;
/*  588: 507 */        if ((p == null) || (p.right != e)) {
/*  589: 508 */          while (!y.succ()) y = y.right;
/*  590: 509 */          p = y.right;
/*  591:     */        }
/*  592: 511 */        return p;
/*  593:     */      }
/*  594:     */      
/*  595: 514 */      x = x.left;
/*  596: 515 */      y = y.right;
/*  597:     */    }
/*  598:     */  }
/*  599:     */  
/*  601:     */  public boolean remove(byte k)
/*  602:     */  {
/*  603: 522 */    if (this.tree == null) { return false;
/*  604:     */    }
/*  605:     */    
/*  606: 525 */    Entry p = this.tree;Entry q = null;
/*  607: 526 */    boolean dir = false;
/*  608: 527 */    byte kk = k;
/*  609:     */    
/*  610:     */    int cmp;
/*  611: 530 */    while ((cmp = compare(kk, p.key)) != 0) {
/*  612: 531 */      if ((dir = cmp > 0 ? 1 : 0) != 0) {
/*  613: 532 */        q = p;
/*  614: 533 */        if ((p = p.right()) == null) return false;
/*  615:     */      }
/*  616:     */      else {
/*  617: 536 */        q = p;
/*  618: 537 */        if ((p = p.left()) == null) { return false;
/*  619:     */        }
/*  620:     */      }
/*  621:     */    }
/*  622: 541 */    if (p.left == null) this.firstEntry = p.next();
/*  623: 542 */    if (p.right == null) { this.lastEntry = p.prev();
/*  624:     */    }
/*  625: 544 */    if (p.succ()) {
/*  626: 545 */      if (p.pred()) {
/*  627: 546 */        if (q != null) {
/*  628: 547 */          if (dir) q.succ(p.right); else
/*  629: 548 */            q.pred(p.left);
/*  630:     */        } else {
/*  631: 550 */          this.tree = (dir ? p.right : p.left);
/*  632:     */        }
/*  633:     */      } else {
/*  634: 553 */        p.prev().right = p.right;
/*  635:     */        
/*  636: 555 */        if (q != null) {
/*  637: 556 */          if (dir) q.right = p.left; else
/*  638: 557 */            q.left = p.left;
/*  639:     */        } else {
/*  640: 559 */          this.tree = p.left;
/*  641:     */        }
/*  642:     */      }
/*  643:     */    } else {
/*  644: 563 */      Entry r = p.right;
/*  645:     */      
/*  646: 565 */      if (r.pred()) {
/*  647: 566 */        r.left = p.left;
/*  648: 567 */        r.pred(p.pred());
/*  649: 568 */        if (!r.pred()) r.prev().right = r;
/*  650: 569 */        if (q != null) {
/*  651: 570 */          if (dir) q.right = r; else
/*  652: 571 */            q.left = r;
/*  653:     */        } else {
/*  654: 573 */          this.tree = r;
/*  655:     */        }
/*  656: 575 */        r.balance(p.balance());
/*  657: 576 */        q = r;
/*  658: 577 */        dir = true;
/*  659:     */      }
/*  660:     */      else
/*  661:     */      {
/*  662:     */        Entry s;
/*  663:     */        for (;;)
/*  664:     */        {
/*  665: 584 */          s = r.left;
/*  666: 585 */          if (s.pred()) break;
/*  667: 586 */          r = s;
/*  668:     */        }
/*  669:     */        
/*  670: 589 */        if (s.succ()) r.pred(s); else {
/*  671: 590 */          r.left = s.right;
/*  672:     */        }
/*  673: 592 */        s.left = p.left;
/*  674:     */        
/*  675: 594 */        if (!p.pred()) {
/*  676: 595 */          p.prev().right = s;
/*  677: 596 */          s.pred(false);
/*  678:     */        }
/*  679:     */        
/*  680: 599 */        s.right = p.right;
/*  681: 600 */        s.succ(false);
/*  682:     */        
/*  683: 602 */        if (q != null) {
/*  684: 603 */          if (dir) q.right = s; else
/*  685: 604 */            q.left = s;
/*  686:     */        } else {
/*  687: 606 */          this.tree = s;
/*  688:     */        }
/*  689: 608 */        s.balance(p.balance());
/*  690: 609 */        q = r;
/*  691: 610 */        dir = false;
/*  692:     */      }
/*  693:     */    }
/*  694:     */    
/*  697: 616 */    while (q != null) {
/*  698: 617 */      Entry y = q;
/*  699: 618 */      q = parent(y);
/*  700:     */      
/*  701: 620 */      if (!dir) {
/*  702: 621 */        dir = (q != null) && (q.left != y);
/*  703: 622 */        y.incBalance();
/*  704:     */        
/*  705: 624 */        if (y.balance() == 1) break;
/*  706: 625 */        if (y.balance() == 2)
/*  707:     */        {
/*  708: 627 */          Entry x = y.right;
/*  709:     */          
/*  711: 630 */          if (x.balance() == -1)
/*  712:     */          {
/*  716: 635 */            Entry w = x.left;
/*  717: 636 */            x.left = w.right;
/*  718: 637 */            w.right = x;
/*  719: 638 */            y.right = w.left;
/*  720: 639 */            w.left = y;
/*  721:     */            
/*  722: 641 */            if (w.balance() == 1) {
/*  723: 642 */              x.balance(0);
/*  724: 643 */              y.balance(-1);
/*  725:     */            }
/*  726: 645 */            else if (w.balance() == 0) {
/*  727: 646 */              x.balance(0);
/*  728: 647 */              y.balance(0);
/*  730:     */            }
/*  731:     */            else
/*  732:     */            {
/*  733: 652 */              x.balance(1);
/*  734: 653 */              y.balance(0);
/*  735:     */            }
/*  736:     */            
/*  737: 656 */            w.balance(0);
/*  738:     */            
/*  739: 658 */            if (w.pred()) {
/*  740: 659 */              y.succ(w);
/*  741: 660 */              w.pred(false);
/*  742:     */            }
/*  743: 662 */            if (w.succ()) {
/*  744: 663 */              x.pred(w);
/*  745: 664 */              w.succ(false);
/*  746:     */            }
/*  747:     */            
/*  748: 667 */            if (q != null) {
/*  749: 668 */              if (dir) q.right = w; else
/*  750: 669 */                q.left = w;
/*  751:     */            } else {
/*  752: 671 */              this.tree = w;
/*  753:     */            }
/*  754:     */          } else {
/*  755: 674 */            if (q != null) {
/*  756: 675 */              if (dir) q.right = x; else
/*  757: 676 */                q.left = x;
/*  758:     */            } else {
/*  759: 678 */              this.tree = x;
/*  760:     */            }
/*  761: 680 */            if (x.balance() == 0) {
/*  762: 681 */              y.right = x.left;
/*  763: 682 */              x.left = y;
/*  764: 683 */              x.balance(-1);
/*  765: 684 */              y.balance(1);
/*  766: 685 */              break;
/*  767:     */            }
/*  768:     */            
/*  771: 690 */            if (x.pred()) {
/*  772: 691 */              y.succ(true);
/*  773: 692 */              x.pred(false);
/*  774:     */            } else {
/*  775: 694 */              y.right = x.left;
/*  776:     */            }
/*  777: 696 */            x.left = y;
/*  778: 697 */            y.balance(0);
/*  779: 698 */            x.balance(0);
/*  780:     */          }
/*  781:     */        }
/*  782:     */      }
/*  783:     */      else {
/*  784: 703 */        dir = (q != null) && (q.left != y);
/*  785: 704 */        y.decBalance();
/*  786:     */        
/*  787: 706 */        if (y.balance() == -1) break;
/*  788: 707 */        if (y.balance() == -2)
/*  789:     */        {
/*  790: 709 */          Entry x = y.left;
/*  791:     */          
/*  793: 712 */          if (x.balance() == 1)
/*  794:     */          {
/*  798: 717 */            Entry w = x.right;
/*  799: 718 */            x.right = w.left;
/*  800: 719 */            w.left = x;
/*  801: 720 */            y.left = w.right;
/*  802: 721 */            w.right = y;
/*  803:     */            
/*  804: 723 */            if (w.balance() == -1) {
/*  805: 724 */              x.balance(0);
/*  806: 725 */              y.balance(1);
/*  807:     */            }
/*  808: 727 */            else if (w.balance() == 0) {
/*  809: 728 */              x.balance(0);
/*  810: 729 */              y.balance(0);
/*  812:     */            }
/*  813:     */            else
/*  814:     */            {
/*  815: 734 */              x.balance(-1);
/*  816: 735 */              y.balance(0);
/*  817:     */            }
/*  818:     */            
/*  819: 738 */            w.balance(0);
/*  820:     */            
/*  821: 740 */            if (w.pred()) {
/*  822: 741 */              x.succ(w);
/*  823: 742 */              w.pred(false);
/*  824:     */            }
/*  825: 744 */            if (w.succ()) {
/*  826: 745 */              y.pred(w);
/*  827: 746 */              w.succ(false);
/*  828:     */            }
/*  829:     */            
/*  830: 749 */            if (q != null) {
/*  831: 750 */              if (dir) q.right = w; else
/*  832: 751 */                q.left = w;
/*  833:     */            } else {
/*  834: 753 */              this.tree = w;
/*  835:     */            }
/*  836:     */          } else {
/*  837: 756 */            if (q != null) {
/*  838: 757 */              if (dir) q.right = x; else
/*  839: 758 */                q.left = x;
/*  840:     */            } else {
/*  841: 760 */              this.tree = x;
/*  842:     */            }
/*  843: 762 */            if (x.balance() == 0) {
/*  844: 763 */              y.left = x.right;
/*  845: 764 */              x.right = y;
/*  846: 765 */              x.balance(1);
/*  847: 766 */              y.balance(-1);
/*  848: 767 */              break;
/*  849:     */            }
/*  850:     */            
/*  853: 772 */            if (x.succ()) {
/*  854: 773 */              y.pred(true);
/*  855: 774 */              x.succ(false);
/*  856:     */            } else {
/*  857: 776 */              y.left = x.right;
/*  858:     */            }
/*  859: 778 */            x.right = y;
/*  860: 779 */            y.balance(0);
/*  861: 780 */            x.balance(0);
/*  862:     */          }
/*  863:     */        }
/*  864:     */      }
/*  865:     */    }
/*  866:     */    
/*  867: 786 */    this.count -= 1;
/*  868:     */    
/*  869: 788 */    return true;
/*  870:     */  }
/*  871:     */  
/*  874: 793 */  public boolean contains(byte k) { return findKey(k) != null; }
/*  875:     */  
/*  876:     */  public void clear() {
/*  877: 796 */    this.count = 0;
/*  878: 797 */    this.tree = null;
/*  879: 798 */    this.firstEntry = (this.lastEntry = null);
/*  880:     */  }
/*  881:     */  
/*  884:     */  private static final class Entry
/*  885:     */    implements Cloneable
/*  886:     */  {
/*  887:     */    private static final int SUCC_MASK = -2147483648;
/*  888:     */    
/*  890:     */    private static final int PRED_MASK = 1073741824;
/*  891:     */    
/*  892:     */    private static final int BALANCE_MASK = 255;
/*  893:     */    
/*  894:     */    byte key;
/*  895:     */    
/*  896:     */    Entry left;
/*  897:     */    
/*  898:     */    Entry right;
/*  899:     */    
/*  900:     */    int info;
/*  901:     */    
/*  903:     */    Entry() {}
/*  904:     */    
/*  906:     */    Entry(byte k)
/*  907:     */    {
/*  908: 827 */      this.key = k;
/*  909: 828 */      this.info = -1073741824;
/*  910:     */    }
/*  911:     */    
/*  915:     */    Entry left()
/*  916:     */    {
/*  917: 836 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  918:     */    }
/*  919:     */    
/*  923:     */    Entry right()
/*  924:     */    {
/*  925: 844 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  926:     */    }
/*  927:     */    
/*  929:     */    boolean pred()
/*  930:     */    {
/*  931: 850 */      return (this.info & 0x40000000) != 0;
/*  932:     */    }
/*  933:     */    
/*  935:     */    boolean succ()
/*  936:     */    {
/*  937: 856 */      return (this.info & 0x80000000) != 0;
/*  938:     */    }
/*  939:     */    
/*  941:     */    void pred(boolean pred)
/*  942:     */    {
/*  943: 862 */      if (pred) this.info |= 1073741824; else {
/*  944: 863 */        this.info &= -1073741825;
/*  945:     */      }
/*  946:     */    }
/*  947:     */    
/*  948:     */    void succ(boolean succ)
/*  949:     */    {
/*  950: 869 */      if (succ) this.info |= -2147483648; else {
/*  951: 870 */        this.info &= 2147483647;
/*  952:     */      }
/*  953:     */    }
/*  954:     */    
/*  955:     */    void pred(Entry pred)
/*  956:     */    {
/*  957: 876 */      this.info |= 1073741824;
/*  958: 877 */      this.left = pred;
/*  959:     */    }
/*  960:     */    
/*  962:     */    void succ(Entry succ)
/*  963:     */    {
/*  964: 883 */      this.info |= -2147483648;
/*  965: 884 */      this.right = succ;
/*  966:     */    }
/*  967:     */    
/*  969:     */    void left(Entry left)
/*  970:     */    {
/*  971: 890 */      this.info &= -1073741825;
/*  972: 891 */      this.left = left;
/*  973:     */    }
/*  974:     */    
/*  976:     */    void right(Entry right)
/*  977:     */    {
/*  978: 897 */      this.info &= 2147483647;
/*  979: 898 */      this.right = right;
/*  980:     */    }
/*  981:     */    
/*  983:     */    int balance()
/*  984:     */    {
/*  985: 904 */      return (byte)this.info;
/*  986:     */    }
/*  987:     */    
/*  989:     */    void balance(int level)
/*  990:     */    {
/*  991: 910 */      this.info &= -256;
/*  992: 911 */      this.info |= level & 0xFF;
/*  993:     */    }
/*  994:     */    
/*  995:     */    void incBalance() {
/*  996: 915 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  997:     */    }
/*  998:     */    
/*  999:     */    protected void decBalance() {
/* 1000: 919 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/* 1001:     */    }
/* 1002:     */    
/* 1005:     */    Entry next()
/* 1006:     */    {
/* 1007: 926 */      Entry next = this.right;
/* 1008: 927 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/* 1009: 928 */      return next;
/* 1010:     */    }
/* 1011:     */    
/* 1014:     */    Entry prev()
/* 1015:     */    {
/* 1016: 935 */      Entry prev = this.left;
/* 1017: 936 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/* 1018: 937 */      return prev;
/* 1019:     */    }
/* 1020:     */    
/* 1021:     */    public Entry clone() {
/* 1022:     */      Entry c;
/* 1023:     */      try {
/* 1024: 943 */        c = (Entry)super.clone();
/* 1025:     */      }
/* 1026:     */      catch (CloneNotSupportedException cantHappen) {
/* 1027: 946 */        throw new InternalError();
/* 1028:     */      }
/* 1029: 948 */      c.key = this.key;
/* 1030: 949 */      c.info = this.info;
/* 1031: 950 */      return c;
/* 1032:     */    }
/* 1033:     */    
/* 1034: 953 */    public boolean equals(Object o) { if (!(o instanceof Entry)) return false;
/* 1035: 954 */      Entry e = (Entry)o;
/* 1036: 955 */      return this.key == e.key;
/* 1037:     */    }
/* 1038:     */    
/* 1039: 958 */    public int hashCode() { return this.key; }
/* 1040:     */    
/* 1041:     */    public String toString() {
/* 1042: 961 */      return String.valueOf(this.key);
/* 1043:     */    }
/* 1044:     */  }
/* 1045:     */  
/* 1075:     */  public int size()
/* 1076:     */  {
/* 1077: 996 */    return this.count;
/* 1078:     */  }
/* 1079:     */  
/* 1080: 999 */  public boolean isEmpty() { return this.count == 0; }
/* 1081:     */  
/* 1082:     */  public byte firstByte() {
/* 1083:1002 */    if (this.tree == null) throw new NoSuchElementException();
/* 1084:1003 */    return this.firstEntry.key;
/* 1085:     */  }
/* 1086:     */  
/* 1087:1006 */  public byte lastByte() { if (this.tree == null) throw new NoSuchElementException();
/* 1088:1007 */    return this.lastEntry.key;
/* 1089:     */  }
/* 1090:     */  
/* 1093:     */  private class SetIterator
/* 1094:     */    extends AbstractByteListIterator
/* 1095:     */  {
/* 1096:     */    ByteAVLTreeSet.Entry prev;
/* 1097:     */    
/* 1098:     */    ByteAVLTreeSet.Entry next;
/* 1099:     */    
/* 1100:     */    ByteAVLTreeSet.Entry curr;
/* 1101:     */    
/* 1102:1021 */    int index = 0;
/* 1103:     */    
/* 1104:1023 */    SetIterator() { this.next = ByteAVLTreeSet.this.firstEntry; }
/* 1105:     */    
/* 1106:     */    SetIterator(byte k) {
/* 1107:1026 */      if ((this.next = ByteAVLTreeSet.this.locateKey(k)) != null)
/* 1108:1027 */        if (ByteAVLTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1109:1028 */          this.prev = this.next;
/* 1110:1029 */          this.next = this.next.next();
/* 1111:     */        } else {
/* 1112:1031 */          this.prev = this.next.prev();
/* 1113:     */        } }
/* 1114:     */    
/* 1115:1034 */    public boolean hasNext() { return this.next != null; }
/* 1116:1035 */    public boolean hasPrevious() { return this.prev != null; }
/* 1117:     */    
/* 1118:1037 */    void updateNext() { this.next = this.next.next(); }
/* 1119:     */    
/* 1120:     */    ByteAVLTreeSet.Entry nextEntry() {
/* 1121:1040 */      if (!hasNext()) throw new NoSuchElementException();
/* 1122:1041 */      this.curr = (this.prev = this.next);
/* 1123:1042 */      this.index += 1;
/* 1124:1043 */      updateNext();
/* 1125:1044 */      return this.curr; }
/* 1126:     */    
/* 1127:1046 */    public byte nextByte() { return nextEntry().key; }
/* 1128:1047 */    public byte previousByte() { return previousEntry().key; }
/* 1129:     */    
/* 1130:1049 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1131:     */    
/* 1132:     */    ByteAVLTreeSet.Entry previousEntry() {
/* 1133:1052 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1134:1053 */      this.curr = (this.next = this.prev);
/* 1135:1054 */      this.index -= 1;
/* 1136:1055 */      updatePrevious();
/* 1137:1056 */      return this.curr;
/* 1138:     */    }
/* 1139:     */    
/* 1140:1059 */    public int nextIndex() { return this.index; }
/* 1141:     */    
/* 1143:1062 */    public int previousIndex() { return this.index - 1; }
/* 1144:     */    
/* 1145:     */    public void remove() {
/* 1146:1065 */      if (this.curr == null) { throw new IllegalStateException();
/* 1147:     */      }
/* 1148:     */      
/* 1149:1068 */      if (this.curr == this.prev) this.index -= 1;
/* 1150:1069 */      this.next = (this.prev = this.curr);
/* 1151:1070 */      updatePrevious();
/* 1152:1071 */      updateNext();
/* 1153:1072 */      ByteAVLTreeSet.this.remove(this.curr.key);
/* 1154:1073 */      this.curr = null;
/* 1155:     */    }
/* 1156:     */  }
/* 1157:     */  
/* 1158:1077 */  public ByteBidirectionalIterator iterator() { return new SetIterator(); }
/* 1159:     */  
/* 1160:     */  public ByteBidirectionalIterator iterator(byte from) {
/* 1161:1080 */    return new SetIterator(from);
/* 1162:     */  }
/* 1163:     */  
/* 1164:1083 */  public ByteComparator comparator() { return this.actualComparator; }
/* 1165:     */  
/* 1166:     */  public ByteSortedSet headSet(byte to) {
/* 1167:1086 */    return new Subset((byte)0, true, to, false);
/* 1168:     */  }
/* 1169:     */  
/* 1170:1089 */  public ByteSortedSet tailSet(byte from) { return new Subset(from, false, (byte)0, true); }
/* 1171:     */  
/* 1172:     */  public ByteSortedSet subSet(byte from, byte to) {
/* 1173:1092 */    return new Subset(from, false, to, false);
/* 1174:     */  }
/* 1175:     */  
/* 1179:     */  private final class Subset
/* 1180:     */    extends AbstractByteSortedSet
/* 1181:     */    implements Serializable, ByteSortedSet
/* 1182:     */  {
/* 1183:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1184:     */    
/* 1187:     */    byte from;
/* 1188:     */    
/* 1190:     */    byte to;
/* 1191:     */    
/* 1193:     */    boolean bottom;
/* 1194:     */    
/* 1196:     */    boolean top;
/* 1197:     */    
/* 1200:     */    public Subset(byte from, boolean bottom, byte to, boolean top)
/* 1201:     */    {
/* 1202:1121 */      if ((!bottom) && (!top) && (ByteAVLTreeSet.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start element (").append(from).append(") is larger than end element (").append(to).append(")").toString());
/* 1203:1122 */      this.from = from;
/* 1204:1123 */      this.bottom = bottom;
/* 1205:1124 */      this.to = to;
/* 1206:1125 */      this.top = top;
/* 1207:     */    }
/* 1208:     */    
/* 1209:1128 */    public void clear() { SubsetIterator i = new SubsetIterator();
/* 1210:1129 */      while (i.hasNext()) {
/* 1211:1130 */        i.next();
/* 1212:1131 */        i.remove();
/* 1213:     */      }
/* 1214:     */    }
/* 1215:     */    
/* 1218:     */    final boolean in(byte k)
/* 1219:     */    {
/* 1220:1139 */      return ((this.bottom) || (ByteAVLTreeSet.this.compare(k, this.from) >= 0)) && ((this.top) || (ByteAVLTreeSet.this.compare(k, this.to) < 0));
/* 1221:     */    }
/* 1222:     */    
/* 1225:1144 */    public boolean contains(byte k) { return (in(k)) && (ByteAVLTreeSet.this.contains(k)); }
/* 1226:     */    
/* 1227:     */    public boolean add(byte k) {
/* 1228:1147 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Element (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1229:1148 */      return ByteAVLTreeSet.this.add(k);
/* 1230:     */    }
/* 1231:     */    
/* 1232:     */    public boolean remove(byte k) {
/* 1233:1152 */      if (!in(k)) return false;
/* 1234:1153 */      return ByteAVLTreeSet.this.remove(k);
/* 1235:     */    }
/* 1236:     */    
/* 1237:1156 */    public int size() { SubsetIterator i = new SubsetIterator();
/* 1238:1157 */      int n = 0;
/* 1239:1158 */      while (i.hasNext()) {
/* 1240:1159 */        n++;
/* 1241:1160 */        i.next();
/* 1242:     */      }
/* 1243:1162 */      return n;
/* 1244:     */    }
/* 1245:     */    
/* 1246:1165 */    public boolean isEmpty() { return !new SubsetIterator().hasNext(); }
/* 1247:     */    
/* 1248:     */    public ByteComparator comparator() {
/* 1249:1168 */      return ByteAVLTreeSet.this.actualComparator;
/* 1250:     */    }
/* 1251:     */    
/* 1252:1171 */    public ByteBidirectionalIterator iterator() { return new SubsetIterator(); }
/* 1253:     */    
/* 1255:1174 */    public ByteBidirectionalIterator iterator(byte from) { return new SubsetIterator(from); }
/* 1256:     */    
/* 1257:     */    public ByteSortedSet headSet(byte to) {
/* 1258:1177 */      if (this.top) return new Subset(ByteAVLTreeSet.this, this.from, this.bottom, to, false);
/* 1259:1178 */      return ByteAVLTreeSet.this.compare(to, this.to) < 0 ? new Subset(ByteAVLTreeSet.this, this.from, this.bottom, to, false) : this;
/* 1260:     */    }
/* 1261:     */    
/* 1262:1181 */    public ByteSortedSet tailSet(byte from) { if (this.bottom) return new Subset(ByteAVLTreeSet.this, from, false, this.to, this.top);
/* 1263:1182 */      return ByteAVLTreeSet.this.compare(from, this.from) > 0 ? new Subset(ByteAVLTreeSet.this, from, false, this.to, this.top) : this;
/* 1264:     */    }
/* 1265:     */    
/* 1266:1185 */    public ByteSortedSet subSet(byte from, byte to) { if ((this.top) && (this.bottom)) return new Subset(ByteAVLTreeSet.this, from, false, to, false);
/* 1267:1186 */      if (!this.top) to = ByteAVLTreeSet.this.compare(to, this.to) < 0 ? to : this.to;
/* 1268:1187 */      if (!this.bottom) from = ByteAVLTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
/* 1269:1188 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1270:1189 */      return new Subset(ByteAVLTreeSet.this, from, false, to, false);
/* 1271:     */    }
/* 1272:     */    
/* 1275:     */    public ByteAVLTreeSet.Entry firstEntry()
/* 1276:     */    {
/* 1277:1196 */      if (ByteAVLTreeSet.this.tree == null) return null;
/* 1278:     */      ByteAVLTreeSet.Entry e;
/* 1279:     */      ByteAVLTreeSet.Entry e;
/* 1280:1199 */      if (this.bottom) { e = ByteAVLTreeSet.this.firstEntry;
/* 1281:     */      } else {
/* 1282:1201 */        e = ByteAVLTreeSet.this.locateKey(this.from);
/* 1283:     */        
/* 1284:1203 */        if (ByteAVLTreeSet.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1285:     */        }
/* 1286:     */      }
/* 1287:1206 */      if ((e == null) || ((!this.top) && (ByteAVLTreeSet.this.compare(e.key, this.to) >= 0))) return null;
/* 1288:1207 */      return e;
/* 1289:     */    }
/* 1290:     */    
/* 1293:     */    public ByteAVLTreeSet.Entry lastEntry()
/* 1294:     */    {
/* 1295:1214 */      if (ByteAVLTreeSet.this.tree == null) return null;
/* 1296:     */      ByteAVLTreeSet.Entry e;
/* 1297:     */      ByteAVLTreeSet.Entry e;
/* 1298:1217 */      if (this.top) { e = ByteAVLTreeSet.this.lastEntry;
/* 1299:     */      } else {
/* 1300:1219 */        e = ByteAVLTreeSet.this.locateKey(this.to);
/* 1301:     */        
/* 1302:1221 */        if (ByteAVLTreeSet.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1303:     */        }
/* 1304:     */      }
/* 1305:1224 */      if ((e == null) || ((!this.bottom) && (ByteAVLTreeSet.this.compare(e.key, this.from) < 0))) return null;
/* 1306:1225 */      return e;
/* 1307:     */    }
/* 1308:     */    
/* 1309:1228 */    public byte firstByte() { ByteAVLTreeSet.Entry e = firstEntry();
/* 1310:1229 */      if (e == null) throw new NoSuchElementException();
/* 1311:1230 */      return e.key;
/* 1312:     */    }
/* 1313:     */    
/* 1314:1233 */    public byte lastByte() { ByteAVLTreeSet.Entry e = lastEntry();
/* 1315:1234 */      if (e == null) throw new NoSuchElementException();
/* 1316:1235 */      return e.key;
/* 1317:     */    }
/* 1318:     */    
/* 1321:     */    private final class SubsetIterator
/* 1322:     */      extends ByteAVLTreeSet.SetIterator
/* 1323:     */    {
/* 1324:     */      SubsetIterator()
/* 1325:     */      {
/* 1326:1245 */        super();
/* 1327:1246 */        this.next = ByteAVLTreeSet.Subset.this.firstEntry();
/* 1328:     */      }
/* 1329:     */      
/* 1330:1249 */      SubsetIterator(byte k) { this();
/* 1331:1250 */        if (this.next != null)
/* 1332:1251 */          if ((!ByteAVLTreeSet.Subset.this.bottom) && (ByteAVLTreeSet.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1333:1252 */          } else if ((!ByteAVLTreeSet.Subset.this.top) && (ByteAVLTreeSet.this.compare(k, (this.prev = ByteAVLTreeSet.Subset.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1334:     */          } else {
/* 1335:1254 */            this.next = ByteAVLTreeSet.this.locateKey(k);
/* 1336:1255 */            if (ByteAVLTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1337:1256 */              this.prev = this.next;
/* 1338:1257 */              this.next = this.next.next();
/* 1339:     */            } else {
/* 1340:1259 */              this.prev = this.next.prev();
/* 1341:     */            }
/* 1342:     */          }
/* 1343:     */      }
/* 1344:     */      
/* 1345:1264 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1346:1265 */        if ((!ByteAVLTreeSet.Subset.this.bottom) && (this.prev != null) && (ByteAVLTreeSet.this.compare(this.prev.key, ByteAVLTreeSet.Subset.this.from) < 0)) this.prev = null;
/* 1347:     */      }
/* 1348:     */      
/* 1349:1268 */      void updateNext() { this.next = this.next.next();
/* 1350:1269 */        if ((!ByteAVLTreeSet.Subset.this.top) && (this.next != null) && (ByteAVLTreeSet.this.compare(this.next.key, ByteAVLTreeSet.Subset.this.to) >= 0)) { this.next = null;
/* 1351:     */        }
/* 1352:     */      }
/* 1353:     */    }
/* 1354:     */  }
/* 1355:     */  
/* 1358:     */  public Object clone()
/* 1359:     */  {
/* 1360:     */    ByteAVLTreeSet c;
/* 1361:     */    
/* 1363:     */    try
/* 1364:     */    {
/* 1365:1284 */      c = (ByteAVLTreeSet)super.clone();
/* 1366:     */    }
/* 1367:     */    catch (CloneNotSupportedException cantHappen) {
/* 1368:1287 */      throw new InternalError();
/* 1369:     */    }
/* 1370:1289 */    c.allocatePaths();
/* 1371:1290 */    if (this.count != 0)
/* 1372:     */    {
/* 1373:1292 */      Entry rp = new Entry();Entry rq = new Entry();
/* 1374:1293 */      Entry p = rp;
/* 1375:1294 */      rp.left(this.tree);
/* 1376:1295 */      Entry q = rq;
/* 1377:1296 */      rq.pred(null);
/* 1378:     */      for (;;) {
/* 1379:1298 */        if (!p.pred()) {
/* 1380:1299 */          Entry e = p.left.clone();
/* 1381:1300 */          e.pred(q.left);
/* 1382:1301 */          e.succ(q);
/* 1383:1302 */          q.left(e);
/* 1384:1303 */          p = p.left;
/* 1385:1304 */          q = q.left;
/* 1386:     */        }
/* 1387:     */        else {
/* 1388:1307 */          while (p.succ()) {
/* 1389:1308 */            p = p.right;
/* 1390:1309 */            if (p == null) {
/* 1391:1310 */              q.right = null;
/* 1392:1311 */              c.tree = rq.left;
/* 1393:1312 */              c.firstEntry = c.tree;
/* 1394:1313 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1395:1314 */              c.lastEntry = c.tree;
/* 1396:1315 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1397:1316 */              return c;
/* 1398:     */            }
/* 1399:1318 */            q = q.right;
/* 1400:     */          }
/* 1401:1320 */          p = p.right;
/* 1402:1321 */          q = q.right;
/* 1403:     */        }
/* 1404:1323 */        if (!p.succ()) {
/* 1405:1324 */          Entry e = p.right.clone();
/* 1406:1325 */          e.succ(q.right);
/* 1407:1326 */          e.pred(q);
/* 1408:1327 */          q.right(e);
/* 1409:     */        }
/* 1410:     */      }
/* 1411:     */    }
/* 1412:1331 */    return c;
/* 1413:     */  }
/* 1414:     */  
/* 1415:1334 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1416:1335 */    SetIterator i = new SetIterator();
/* 1417:1336 */    s.defaultWriteObject();
/* 1418:1337 */    while (n-- != 0) { s.writeByte(i.nextByte());
/* 1419:     */    }
/* 1420:     */  }
/* 1421:     */  
/* 1426:     */  private Entry readTree(ObjectInputStream s, int n, Entry pred, Entry succ)
/* 1427:     */    throws IOException, ClassNotFoundException
/* 1428:     */  {
/* 1429:1348 */    if (n == 1) {
/* 1430:1349 */      Entry top = new Entry(s.readByte());
/* 1431:1350 */      top.pred(pred);
/* 1432:1351 */      top.succ(succ);
/* 1433:1352 */      return top;
/* 1434:     */    }
/* 1435:1354 */    if (n == 2)
/* 1436:     */    {
/* 1438:1357 */      Entry top = new Entry(s.readByte());
/* 1439:1358 */      top.right(new Entry(s.readByte()));
/* 1440:1359 */      top.right.pred(top);
/* 1441:1360 */      top.balance(1);
/* 1442:1361 */      top.pred(pred);
/* 1443:1362 */      top.right.succ(succ);
/* 1444:1363 */      return top;
/* 1445:     */    }
/* 1446:     */    
/* 1447:1366 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1448:1367 */    Entry top = new Entry();
/* 1449:1368 */    top.left(readTree(s, leftN, pred, top));
/* 1450:1369 */    top.key = s.readByte();
/* 1451:1370 */    top.right(readTree(s, rightN, top, succ));
/* 1452:1371 */    if (n == (n & -n)) top.balance(1);
/* 1453:1372 */    return top;
/* 1454:     */  }
/* 1455:     */  
/* 1456:1375 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1457:     */    
/* 1459:1378 */    setActualComparator();
/* 1460:1379 */    allocatePaths();
/* 1461:1380 */    if (this.count != 0) {
/* 1462:1381 */      this.tree = readTree(s, this.count, null, null);
/* 1463:     */      
/* 1464:1383 */      Entry e = this.tree;
/* 1465:1384 */      while (e.left() != null) e = e.left();
/* 1466:1385 */      this.firstEntry = e;
/* 1467:1386 */      e = this.tree;
/* 1468:1387 */      while (e.right() != null) e = e.right();
/* 1469:1388 */      this.lastEntry = e;
/* 1470:     */    }
/* 1471:     */  }
/* 1472:     */  
/* 1473:     */  private static int checkTree(Entry e) {
/* 1474:1393 */    return 0;
/* 1475:     */  }
/* 1476:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteAVLTreeSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */