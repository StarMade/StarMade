/*    1:     */package it.unimi.dsi.fastutil.longs;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.HashCommon;
/*    4:     */import java.io.IOException;
/*    5:     */import java.io.ObjectInputStream;
/*    6:     */import java.io.ObjectOutputStream;
/*    7:     */import java.io.Serializable;
/*    8:     */import java.util.Collection;
/*    9:     */import java.util.Comparator;
/*   10:     */import java.util.Iterator;
/*   11:     */import java.util.NoSuchElementException;
/*   12:     */import java.util.SortedSet;
/*   13:     */
/*   57:     */public class LongRBTreeSet
/*   58:     */  extends AbstractLongSortedSet
/*   59:     */  implements Serializable, Cloneable, LongSortedSet
/*   60:     */{
/*   61:     */  protected transient Entry tree;
/*   62:     */  protected int count;
/*   63:     */  protected transient Entry firstEntry;
/*   64:     */  protected transient Entry lastEntry;
/*   65:     */  protected Comparator<? super Long> storedComparator;
/*   66:     */  protected transient LongComparator actualComparator;
/*   67:     */  public static final long serialVersionUID = -7046029254386353130L;
/*   68:     */  private static final boolean ASSERTS = false;
/*   69:     */  private transient boolean[] dirPath;
/*   70:     */  private transient Entry[] nodePath;
/*   71:     */  
/*   72:     */  public LongRBTreeSet()
/*   73:     */  {
/*   74:  74 */    allocatePaths();
/*   75:     */    
/*   79:  79 */    this.tree = null;
/*   80:  80 */    this.count = 0;
/*   81:     */  }
/*   82:     */  
/*   92:     */  private void setActualComparator()
/*   93:     */  {
/*   94:  94 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof LongComparator))) this.actualComparator = ((LongComparator)this.storedComparator); else {
/*   95:  95 */      this.actualComparator = new LongComparator() {
/*   96:     */        public int compare(long k1, long k2) {
/*   97:  97 */          return LongRBTreeSet.this.storedComparator.compare(Long.valueOf(k1), Long.valueOf(k2));
/*   98:     */        }
/*   99:     */        
/*  100: 100 */        public int compare(Long ok1, Long ok2) { return LongRBTreeSet.this.storedComparator.compare(ok1, ok2); }
/*  101:     */      };
/*  102:     */    }
/*  103:     */  }
/*  104:     */  
/*  107:     */  public LongRBTreeSet(Comparator<? super Long> c)
/*  108:     */  {
/*  109: 109 */    this();
/*  110: 110 */    this.storedComparator = c;
/*  111: 111 */    setActualComparator();
/*  112:     */  }
/*  113:     */  
/*  117:     */  public LongRBTreeSet(Collection<? extends Long> c)
/*  118:     */  {
/*  119: 119 */    this();
/*  120: 120 */    addAll(c);
/*  121:     */  }
/*  122:     */  
/*  127:     */  public LongRBTreeSet(SortedSet<Long> s)
/*  128:     */  {
/*  129: 129 */    this(s.comparator());
/*  130: 130 */    addAll(s);
/*  131:     */  }
/*  132:     */  
/*  137:     */  public LongRBTreeSet(LongCollection c)
/*  138:     */  {
/*  139: 139 */    this();
/*  140: 140 */    addAll(c);
/*  141:     */  }
/*  142:     */  
/*  147:     */  public LongRBTreeSet(LongSortedSet s)
/*  148:     */  {
/*  149: 149 */    this(s.comparator());
/*  150: 150 */    addAll(s);
/*  151:     */  }
/*  152:     */  
/*  153:     */  public LongRBTreeSet(LongIterator i)
/*  154:     */  {
/*  155:  74 */    allocatePaths();
/*  156:     */    
/*  241: 160 */    while (i.hasNext()) { add(i.nextLong());
/*  242:     */    }
/*  243:     */  }
/*  244:     */  
/*  250:     */  public LongRBTreeSet(Iterator<? extends Long> i)
/*  251:     */  {
/*  252: 171 */    this(LongIterators.asLongIterator(i));
/*  253:     */  }
/*  254:     */  
/*  263:     */  public LongRBTreeSet(long[] a, int offset, int length, Comparator<? super Long> c)
/*  264:     */  {
/*  265: 184 */    this(c);
/*  266: 185 */    LongArrays.ensureOffsetLength(a, offset, length);
/*  267: 186 */    for (int i = 0; i < length; i++) { add(a[(offset + i)]);
/*  268:     */    }
/*  269:     */  }
/*  270:     */  
/*  277:     */  public LongRBTreeSet(long[] a, int offset, int length)
/*  278:     */  {
/*  279: 198 */    this(a, offset, length, null);
/*  280:     */  }
/*  281:     */  
/*  287:     */  public LongRBTreeSet(long[] a)
/*  288:     */  {
/*  289: 208 */    this();
/*  290: 209 */    int i = a.length;
/*  291: 210 */    while (i-- != 0) { add(a[i]);
/*  292:     */    }
/*  293:     */  }
/*  294:     */  
/*  300:     */  public LongRBTreeSet(long[] a, Comparator<? super Long> c)
/*  301:     */  {
/*  302: 221 */    this(c);
/*  303: 222 */    int i = a.length;
/*  304: 223 */    while (i-- != 0) { add(a[i]);
/*  305:     */    }
/*  306:     */  }
/*  307:     */  
/*  332:     */  final int compare(long k1, long k2)
/*  333:     */  {
/*  334: 253 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  335:     */  }
/*  336:     */  
/*  344:     */  private Entry findKey(long k)
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
/*  361:     */  final Entry locateKey(long k)
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
/*  379:     */  private void allocatePaths()
/*  380:     */  {
/*  381: 300 */    this.dirPath = new boolean[64];
/*  382:     */    
/*  385: 304 */    this.nodePath = new Entry[64];
/*  386:     */  }
/*  387:     */  
/*  389:     */  public boolean add(long k)
/*  390:     */  {
/*  391: 310 */    int maxDepth = 0;
/*  392:     */    
/*  393: 312 */    if (this.tree == null) {
/*  394: 313 */      this.count += 1;
/*  395: 314 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k));
/*  396:     */    }
/*  397:     */    else {
/*  398: 317 */      Entry p = this.tree;
/*  399: 318 */      int i = 0;
/*  400:     */      for (;;) {
/*  401:     */        int cmp;
/*  402: 321 */        if ((cmp = compare(k, p.key)) == 0)
/*  403:     */        {
/*  404: 323 */          while (i-- != 0) this.nodePath[i] = null;
/*  405: 324 */          return false;
/*  406:     */        }
/*  407:     */        
/*  408: 327 */        this.nodePath[i] = p;
/*  409:     */        
/*  410: 329 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  411: 330 */          if (p.succ()) {
/*  412: 331 */            this.count += 1;
/*  413: 332 */            Entry e = new Entry(k);
/*  414:     */            
/*  415: 334 */            if (p.right == null) { this.lastEntry = e;
/*  416:     */            }
/*  417: 336 */            e.left = p;
/*  418: 337 */            e.right = p.right;
/*  419:     */            
/*  420: 339 */            p.right(e);
/*  421:     */            
/*  422: 341 */            break;
/*  423:     */          }
/*  424:     */          
/*  425: 344 */          p = p.right;
/*  426:     */        }
/*  427:     */        else {
/*  428: 347 */          if (p.pred()) {
/*  429: 348 */            this.count += 1;
/*  430: 349 */            Entry e = new Entry(k);
/*  431:     */            
/*  432: 351 */            if (p.left == null) { this.firstEntry = e;
/*  433:     */            }
/*  434: 353 */            e.right = p;
/*  435: 354 */            e.left = p.left;
/*  436:     */            
/*  437: 356 */            p.left(e);
/*  438:     */            
/*  439: 358 */            break;
/*  440:     */          }
/*  441:     */          
/*  442: 361 */          p = p.left;
/*  443:     */        }
/*  444:     */      }
/*  445:     */      Entry e;
/*  446: 365 */      maxDepth = i--;
/*  447:     */      
/*  448: 367 */      while ((i > 0) && (!this.nodePath[i].black())) {
/*  449: 368 */        if (this.dirPath[(i - 1)] == 0) {
/*  450: 369 */          Entry y = this.nodePath[(i - 1)].right;
/*  451:     */          
/*  452: 371 */          if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  453: 372 */            this.nodePath[i].black(true);
/*  454: 373 */            y.black(true);
/*  455: 374 */            this.nodePath[(i - 1)].black(false);
/*  456: 375 */            i -= 2;
/*  458:     */          }
/*  459:     */          else
/*  460:     */          {
/*  461: 380 */            if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*  462:     */            } else {
/*  463: 382 */              Entry x = this.nodePath[i];
/*  464: 383 */              y = x.right;
/*  465: 384 */              x.right = y.left;
/*  466: 385 */              y.left = x;
/*  467: 386 */              this.nodePath[(i - 1)].left = y;
/*  468:     */              
/*  469: 388 */              if (y.pred()) {
/*  470: 389 */                y.pred(false);
/*  471: 390 */                x.succ(y);
/*  472:     */              }
/*  473:     */            }
/*  474:     */            
/*  475: 394 */            Entry x = this.nodePath[(i - 1)];
/*  476: 395 */            x.black(false);
/*  477: 396 */            y.black(true);
/*  478:     */            
/*  479: 398 */            x.left = y.right;
/*  480: 399 */            y.right = x;
/*  481: 400 */            if (i < 2) { this.tree = y;
/*  482:     */            }
/*  483: 402 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  484: 403 */              this.nodePath[(i - 2)].left = y;
/*  485:     */            }
/*  486:     */            
/*  487: 406 */            if (!y.succ()) break;
/*  488: 407 */            y.succ(false);
/*  489: 408 */            x.pred(y);break;
/*  490:     */          }
/*  491:     */          
/*  492:     */        }
/*  493:     */        else
/*  494:     */        {
/*  495: 414 */          Entry y = this.nodePath[(i - 1)].left;
/*  496:     */          
/*  497: 416 */          if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  498: 417 */            this.nodePath[i].black(true);
/*  499: 418 */            y.black(true);
/*  500: 419 */            this.nodePath[(i - 1)].black(false);
/*  501: 420 */            i -= 2;
/*  503:     */          }
/*  504:     */          else
/*  505:     */          {
/*  506: 425 */            if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*  507:     */            } else {
/*  508: 427 */              Entry x = this.nodePath[i];
/*  509: 428 */              y = x.left;
/*  510: 429 */              x.left = y.right;
/*  511: 430 */              y.right = x;
/*  512: 431 */              this.nodePath[(i - 1)].right = y;
/*  513:     */              
/*  514: 433 */              if (y.succ()) {
/*  515: 434 */                y.succ(false);
/*  516: 435 */                x.pred(y);
/*  517:     */              }
/*  518:     */            }
/*  519:     */            
/*  521: 440 */            Entry x = this.nodePath[(i - 1)];
/*  522: 441 */            x.black(false);
/*  523: 442 */            y.black(true);
/*  524:     */            
/*  525: 444 */            x.right = y.left;
/*  526: 445 */            y.left = x;
/*  527: 446 */            if (i < 2) { this.tree = y;
/*  528:     */            }
/*  529: 448 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  530: 449 */              this.nodePath[(i - 2)].left = y;
/*  531:     */            }
/*  532:     */            
/*  533: 452 */            if (!y.pred()) break;
/*  534: 453 */            y.pred(false);
/*  535: 454 */            x.succ(y);break;
/*  536:     */          }
/*  537:     */        }
/*  538:     */      }
/*  539:     */    }
/*  540:     */    
/*  543: 462 */    this.tree.black(true);
/*  544:     */    
/*  545: 464 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  546:     */    }
/*  547:     */    
/*  550: 469 */    return true;
/*  551:     */  }
/*  552:     */  
/*  554:     */  public boolean remove(long k)
/*  555:     */  {
/*  556: 475 */    if (this.tree == null) { return false;
/*  557:     */    }
/*  558: 477 */    Entry p = this.tree;
/*  559:     */    
/*  560: 479 */    int i = 0;
/*  561: 480 */    long kk = k;
/*  562:     */    
/*  563:     */    int cmp;
/*  564: 483 */    while ((cmp = compare(kk, p.key)) != 0)
/*  565:     */    {
/*  566: 485 */      this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  567: 486 */      this.nodePath[i] = p;
/*  568:     */      
/*  569: 488 */      if (this.dirPath[(i++)] != 0) {
/*  570: 489 */        if ((p = p.right()) == null)
/*  571:     */        {
/*  572: 491 */          while (i-- != 0) this.nodePath[i] = null;
/*  573: 492 */          return false;
/*  574:     */        }
/*  575:     */        
/*  576:     */      }
/*  577: 496 */      else if ((p = p.left()) == null)
/*  578:     */      {
/*  579: 498 */        while (i-- != 0) this.nodePath[i] = null;
/*  580: 499 */        return false;
/*  581:     */      }
/*  582:     */    }
/*  583:     */    
/*  585: 504 */    if (p.left == null) this.firstEntry = p.next();
/*  586: 505 */    if (p.right == null) { this.lastEntry = p.prev();
/*  587:     */    }
/*  588: 507 */    if (p.succ()) {
/*  589: 508 */      if (p.pred()) {
/*  590: 509 */        if (i == 0) { this.tree = p.left;
/*  591:     */        }
/*  592: 511 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else {
/*  593: 512 */          this.nodePath[(i - 1)].pred(p.left);
/*  594:     */        }
/*  595:     */      }
/*  596:     */      else {
/*  597: 516 */        p.prev().right = p.right;
/*  598:     */        
/*  599: 518 */        if (i == 0) { this.tree = p.left;
/*  600:     */        }
/*  601: 520 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  602: 521 */          this.nodePath[(i - 1)].left = p.left;
/*  603:     */        }
/*  604:     */      }
/*  605:     */    }
/*  606:     */    else
/*  607:     */    {
/*  608: 527 */      Entry r = p.right;
/*  609:     */      
/*  610: 529 */      if (r.pred()) {
/*  611: 530 */        r.left = p.left;
/*  612: 531 */        r.pred(p.pred());
/*  613: 532 */        if (!r.pred()) r.prev().right = r;
/*  614: 533 */        if (i == 0) { this.tree = r;
/*  615:     */        }
/*  616: 535 */        else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  617: 536 */          this.nodePath[(i - 1)].left = r;
/*  618:     */        }
/*  619:     */        
/*  620: 539 */        boolean color = r.black();
/*  621: 540 */        r.black(p.black());
/*  622: 541 */        p.black(color);
/*  623: 542 */        this.dirPath[i] = true;
/*  624: 543 */        this.nodePath[(i++)] = r;
/*  625:     */      }
/*  626:     */      else
/*  627:     */      {
/*  628: 547 */        int j = i++;
/*  629:     */        Entry s;
/*  630:     */        for (;;) {
/*  631: 550 */          this.dirPath[i] = false;
/*  632: 551 */          this.nodePath[(i++)] = r;
/*  633: 552 */          s = r.left;
/*  634: 553 */          if (s.pred()) break;
/*  635: 554 */          r = s;
/*  636:     */        }
/*  637:     */        
/*  638: 557 */        this.dirPath[j] = true;
/*  639: 558 */        this.nodePath[j] = s;
/*  640:     */        
/*  641: 560 */        if (s.succ()) r.pred(s); else {
/*  642: 561 */          r.left = s.right;
/*  643:     */        }
/*  644: 563 */        s.left = p.left;
/*  645:     */        
/*  646: 565 */        if (!p.pred()) {
/*  647: 566 */          p.prev().right = s;
/*  648: 567 */          s.pred(false);
/*  649:     */        }
/*  650:     */        
/*  651: 570 */        s.right(p.right);
/*  652:     */        
/*  653: 572 */        boolean color = s.black();
/*  654: 573 */        s.black(p.black());
/*  655: 574 */        p.black(color);
/*  656:     */        
/*  657: 576 */        if (j == 0) { this.tree = s;
/*  658:     */        }
/*  659: 578 */        else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  660: 579 */          this.nodePath[(j - 1)].left = s;
/*  661:     */        }
/*  662:     */      }
/*  663:     */    }
/*  664:     */    
/*  665: 584 */    int maxDepth = i;
/*  666:     */    
/*  667: 586 */    if (p.black()) {
/*  668: 587 */      for (; i > 0; i--) {
/*  669: 588 */        if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*  670:     */        {
/*  671: 590 */          Entry x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  672:     */          
/*  673: 592 */          if (!x.black()) {
/*  674: 593 */            x.black(true);
/*  675: 594 */            break;
/*  676:     */          }
/*  677:     */        }
/*  678:     */        
/*  679: 598 */        if (this.dirPath[(i - 1)] == 0) {
/*  680: 599 */          Entry w = this.nodePath[(i - 1)].right;
/*  681:     */          
/*  682: 601 */          if (!w.black()) {
/*  683: 602 */            w.black(true);
/*  684: 603 */            this.nodePath[(i - 1)].black(false);
/*  685:     */            
/*  686: 605 */            this.nodePath[(i - 1)].right = w.left;
/*  687: 606 */            w.left = this.nodePath[(i - 1)];
/*  688:     */            
/*  689: 608 */            if (i < 2) { this.tree = w;
/*  690:     */            }
/*  691: 610 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  692: 611 */              this.nodePath[(i - 2)].left = w;
/*  693:     */            }
/*  694:     */            
/*  695: 614 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  696: 615 */            this.dirPath[i] = false;
/*  697: 616 */            this.nodePath[(i - 1)] = w;
/*  698: 617 */            if (maxDepth == i++) { maxDepth++;
/*  699:     */            }
/*  700: 619 */            w = this.nodePath[(i - 1)].right;
/*  701:     */          }
/*  702:     */          
/*  703: 622 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  704:     */          {
/*  705: 624 */            w.black(false);
/*  706:     */          }
/*  707:     */          else {
/*  708: 627 */            if ((w.succ()) || (w.right.black())) {
/*  709: 628 */              Entry y = w.left;
/*  710:     */              
/*  711: 630 */              y.black(true);
/*  712: 631 */              w.black(false);
/*  713: 632 */              w.left = y.right;
/*  714: 633 */              y.right = w;
/*  715: 634 */              w = this.nodePath[(i - 1)].right = y;
/*  716:     */              
/*  717: 636 */              if (w.succ()) {
/*  718: 637 */                w.succ(false);
/*  719: 638 */                w.right.pred(w);
/*  720:     */              }
/*  721:     */            }
/*  722:     */            
/*  723: 642 */            w.black(this.nodePath[(i - 1)].black());
/*  724: 643 */            this.nodePath[(i - 1)].black(true);
/*  725: 644 */            w.right.black(true);
/*  726:     */            
/*  727: 646 */            this.nodePath[(i - 1)].right = w.left;
/*  728: 647 */            w.left = this.nodePath[(i - 1)];
/*  729:     */            
/*  730: 649 */            if (i < 2) { this.tree = w;
/*  731:     */            }
/*  732: 651 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  733: 652 */              this.nodePath[(i - 2)].left = w;
/*  734:     */            }
/*  735:     */            
/*  736: 655 */            if (!w.pred()) break;
/*  737: 656 */            w.pred(false);
/*  738: 657 */            this.nodePath[(i - 1)].succ(w);break;
/*  739:     */          }
/*  740:     */          
/*  741:     */        }
/*  742:     */        else
/*  743:     */        {
/*  744: 663 */          Entry w = this.nodePath[(i - 1)].left;
/*  745:     */          
/*  746: 665 */          if (!w.black()) {
/*  747: 666 */            w.black(true);
/*  748: 667 */            this.nodePath[(i - 1)].black(false);
/*  749:     */            
/*  750: 669 */            this.nodePath[(i - 1)].left = w.right;
/*  751: 670 */            w.right = this.nodePath[(i - 1)];
/*  752:     */            
/*  753: 672 */            if (i < 2) { this.tree = w;
/*  754:     */            }
/*  755: 674 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  756: 675 */              this.nodePath[(i - 2)].left = w;
/*  757:     */            }
/*  758:     */            
/*  759: 678 */            this.nodePath[i] = this.nodePath[(i - 1)];
/*  760: 679 */            this.dirPath[i] = true;
/*  761: 680 */            this.nodePath[(i - 1)] = w;
/*  762: 681 */            if (maxDepth == i++) { maxDepth++;
/*  763:     */            }
/*  764: 683 */            w = this.nodePath[(i - 1)].left;
/*  765:     */          }
/*  766:     */          
/*  767: 686 */          if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*  768:     */          {
/*  769: 688 */            w.black(false);
/*  770:     */          }
/*  771:     */          else {
/*  772: 691 */            if ((w.pred()) || (w.left.black())) {
/*  773: 692 */              Entry y = w.right;
/*  774:     */              
/*  775: 694 */              y.black(true);
/*  776: 695 */              w.black(false);
/*  777: 696 */              w.right = y.left;
/*  778: 697 */              y.left = w;
/*  779: 698 */              w = this.nodePath[(i - 1)].left = y;
/*  780:     */              
/*  781: 700 */              if (w.pred()) {
/*  782: 701 */                w.pred(false);
/*  783: 702 */                w.left.succ(w);
/*  784:     */              }
/*  785:     */            }
/*  786:     */            
/*  787: 706 */            w.black(this.nodePath[(i - 1)].black());
/*  788: 707 */            this.nodePath[(i - 1)].black(true);
/*  789: 708 */            w.left.black(true);
/*  790:     */            
/*  791: 710 */            this.nodePath[(i - 1)].left = w.right;
/*  792: 711 */            w.right = this.nodePath[(i - 1)];
/*  793:     */            
/*  794: 713 */            if (i < 2) { this.tree = w;
/*  795:     */            }
/*  796: 715 */            else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  797: 716 */              this.nodePath[(i - 2)].left = w;
/*  798:     */            }
/*  799:     */            
/*  800: 719 */            if (!w.succ()) break;
/*  801: 720 */            w.succ(false);
/*  802: 721 */            this.nodePath[(i - 1)].pred(w);break;
/*  803:     */          }
/*  804:     */        }
/*  805:     */      }
/*  806:     */      
/*  809: 728 */      if (this.tree != null) { this.tree.black(true);
/*  810:     */      }
/*  811:     */    }
/*  812: 731 */    this.count -= 1;
/*  813:     */    
/*  814: 733 */    while (maxDepth-- != 0) { this.nodePath[maxDepth] = null;
/*  815:     */    }
/*  816:     */    
/*  819: 738 */    return true;
/*  820:     */  }
/*  821:     */  
/*  824: 743 */  public boolean contains(long k) { return findKey(k) != null; }
/*  825:     */  
/*  826:     */  public void clear() {
/*  827: 746 */    this.count = 0;
/*  828: 747 */    this.tree = null;
/*  829: 748 */    this.firstEntry = (this.lastEntry = null);
/*  830:     */  }
/*  831:     */  
/*  834:     */  private static final class Entry
/*  835:     */    implements Cloneable
/*  836:     */  {
/*  837:     */    private static final int BLACK_MASK = 1;
/*  838:     */    
/*  840:     */    private static final int SUCC_MASK = -2147483648;
/*  841:     */    
/*  842:     */    private static final int PRED_MASK = 1073741824;
/*  843:     */    
/*  844:     */    long key;
/*  845:     */    
/*  846:     */    Entry left;
/*  847:     */    
/*  848:     */    Entry right;
/*  849:     */    
/*  850:     */    int info;
/*  851:     */    
/*  853:     */    Entry() {}
/*  854:     */    
/*  856:     */    Entry(long k)
/*  857:     */    {
/*  858: 777 */      this.key = k;
/*  859: 778 */      this.info = -1073741824;
/*  860:     */    }
/*  861:     */    
/*  865:     */    Entry left()
/*  866:     */    {
/*  867: 786 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  868:     */    }
/*  869:     */    
/*  873:     */    Entry right()
/*  874:     */    {
/*  875: 794 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  876:     */    }
/*  877:     */    
/*  879:     */    boolean pred()
/*  880:     */    {
/*  881: 800 */      return (this.info & 0x40000000) != 0;
/*  882:     */    }
/*  883:     */    
/*  885:     */    boolean succ()
/*  886:     */    {
/*  887: 806 */      return (this.info & 0x80000000) != 0;
/*  888:     */    }
/*  889:     */    
/*  891:     */    void pred(boolean pred)
/*  892:     */    {
/*  893: 812 */      if (pred) this.info |= 1073741824; else {
/*  894: 813 */        this.info &= -1073741825;
/*  895:     */      }
/*  896:     */    }
/*  897:     */    
/*  898:     */    void succ(boolean succ)
/*  899:     */    {
/*  900: 819 */      if (succ) this.info |= -2147483648; else {
/*  901: 820 */        this.info &= 2147483647;
/*  902:     */      }
/*  903:     */    }
/*  904:     */    
/*  905:     */    void pred(Entry pred)
/*  906:     */    {
/*  907: 826 */      this.info |= 1073741824;
/*  908: 827 */      this.left = pred;
/*  909:     */    }
/*  910:     */    
/*  912:     */    void succ(Entry succ)
/*  913:     */    {
/*  914: 833 */      this.info |= -2147483648;
/*  915: 834 */      this.right = succ;
/*  916:     */    }
/*  917:     */    
/*  919:     */    void left(Entry left)
/*  920:     */    {
/*  921: 840 */      this.info &= -1073741825;
/*  922: 841 */      this.left = left;
/*  923:     */    }
/*  924:     */    
/*  926:     */    void right(Entry right)
/*  927:     */    {
/*  928: 847 */      this.info &= 2147483647;
/*  929: 848 */      this.right = right;
/*  930:     */    }
/*  931:     */    
/*  933:     */    boolean black()
/*  934:     */    {
/*  935: 854 */      return (this.info & 0x1) != 0;
/*  936:     */    }
/*  937:     */    
/*  939:     */    void black(boolean black)
/*  940:     */    {
/*  941: 860 */      if (black) this.info |= 1; else {
/*  942: 861 */        this.info &= -2;
/*  943:     */      }
/*  944:     */    }
/*  945:     */    
/*  947:     */    Entry next()
/*  948:     */    {
/*  949: 868 */      Entry next = this.right;
/*  950: 869 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  951: 870 */      return next;
/*  952:     */    }
/*  953:     */    
/*  956:     */    Entry prev()
/*  957:     */    {
/*  958: 877 */      Entry prev = this.left;
/*  959: 878 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  960: 879 */      return prev;
/*  961:     */    }
/*  962:     */    
/*  963:     */    public Entry clone() {
/*  964:     */      Entry c;
/*  965:     */      try {
/*  966: 885 */        c = (Entry)super.clone();
/*  967:     */      }
/*  968:     */      catch (CloneNotSupportedException cantHappen) {
/*  969: 888 */        throw new InternalError();
/*  970:     */      }
/*  971: 890 */      c.key = this.key;
/*  972: 891 */      c.info = this.info;
/*  973: 892 */      return c;
/*  974:     */    }
/*  975:     */    
/*  976: 895 */    public boolean equals(Object o) { if (!(o instanceof Entry)) return false;
/*  977: 896 */      Entry e = (Entry)o;
/*  978: 897 */      return this.key == e.key;
/*  979:     */    }
/*  980:     */    
/*  981: 900 */    public int hashCode() { return HashCommon.long2int(this.key); }
/*  982:     */    
/*  983:     */    public String toString() {
/*  984: 903 */      return String.valueOf(this.key);
/*  985:     */    }
/*  986:     */  }
/*  987:     */  
/* 1017:     */  public int size()
/* 1018:     */  {
/* 1019: 938 */    return this.count;
/* 1020:     */  }
/* 1021:     */  
/* 1022: 941 */  public boolean isEmpty() { return this.count == 0; }
/* 1023:     */  
/* 1024:     */  public long firstLong() {
/* 1025: 944 */    if (this.tree == null) throw new NoSuchElementException();
/* 1026: 945 */    return this.firstEntry.key;
/* 1027:     */  }
/* 1028:     */  
/* 1029: 948 */  public long lastLong() { if (this.tree == null) throw new NoSuchElementException();
/* 1030: 949 */    return this.lastEntry.key;
/* 1031:     */  }
/* 1032:     */  
/* 1035:     */  private class SetIterator
/* 1036:     */    extends AbstractLongListIterator
/* 1037:     */  {
/* 1038:     */    LongRBTreeSet.Entry prev;
/* 1039:     */    
/* 1040:     */    LongRBTreeSet.Entry next;
/* 1041:     */    
/* 1042:     */    LongRBTreeSet.Entry curr;
/* 1043:     */    
/* 1044: 963 */    int index = 0;
/* 1045:     */    
/* 1046: 965 */    SetIterator() { this.next = LongRBTreeSet.this.firstEntry; }
/* 1047:     */    
/* 1048:     */    SetIterator(long k) {
/* 1049: 968 */      if ((this.next = LongRBTreeSet.this.locateKey(k)) != null)
/* 1050: 969 */        if (LongRBTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1051: 970 */          this.prev = this.next;
/* 1052: 971 */          this.next = this.next.next();
/* 1053:     */        } else {
/* 1054: 973 */          this.prev = this.next.prev();
/* 1055:     */        } }
/* 1056:     */    
/* 1057: 976 */    public boolean hasNext() { return this.next != null; }
/* 1058: 977 */    public boolean hasPrevious() { return this.prev != null; }
/* 1059:     */    
/* 1060: 979 */    void updateNext() { this.next = this.next.next(); }
/* 1061:     */    
/* 1062:     */    LongRBTreeSet.Entry nextEntry() {
/* 1063: 982 */      if (!hasNext()) throw new NoSuchElementException();
/* 1064: 983 */      this.curr = (this.prev = this.next);
/* 1065: 984 */      this.index += 1;
/* 1066: 985 */      updateNext();
/* 1067: 986 */      return this.curr; }
/* 1068:     */    
/* 1069: 988 */    public long nextLong() { return nextEntry().key; }
/* 1070: 989 */    public long previousLong() { return previousEntry().key; }
/* 1071:     */    
/* 1072: 991 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1073:     */    
/* 1074:     */    LongRBTreeSet.Entry previousEntry() {
/* 1075: 994 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1076: 995 */      this.curr = (this.next = this.prev);
/* 1077: 996 */      this.index -= 1;
/* 1078: 997 */      updatePrevious();
/* 1079: 998 */      return this.curr;
/* 1080:     */    }
/* 1081:     */    
/* 1082:1001 */    public int nextIndex() { return this.index; }
/* 1083:     */    
/* 1085:1004 */    public int previousIndex() { return this.index - 1; }
/* 1086:     */    
/* 1087:     */    public void remove() {
/* 1088:1007 */      if (this.curr == null) { throw new IllegalStateException();
/* 1089:     */      }
/* 1090:     */      
/* 1091:1010 */      if (this.curr == this.prev) this.index -= 1;
/* 1092:1011 */      this.next = (this.prev = this.curr);
/* 1093:1012 */      updatePrevious();
/* 1094:1013 */      updateNext();
/* 1095:1014 */      LongRBTreeSet.this.remove(this.curr.key);
/* 1096:1015 */      this.curr = null;
/* 1097:     */    }
/* 1098:     */  }
/* 1099:     */  
/* 1100:1019 */  public LongBidirectionalIterator iterator() { return new SetIterator(); }
/* 1101:     */  
/* 1102:     */  public LongBidirectionalIterator iterator(long from) {
/* 1103:1022 */    return new SetIterator(from);
/* 1104:     */  }
/* 1105:     */  
/* 1106:1025 */  public LongComparator comparator() { return this.actualComparator; }
/* 1107:     */  
/* 1108:     */  public LongSortedSet headSet(long to) {
/* 1109:1028 */    return new Subset(0L, true, to, false);
/* 1110:     */  }
/* 1111:     */  
/* 1112:1031 */  public LongSortedSet tailSet(long from) { return new Subset(from, false, 0L, true); }
/* 1113:     */  
/* 1114:     */  public LongSortedSet subSet(long from, long to) {
/* 1115:1034 */    return new Subset(from, false, to, false);
/* 1116:     */  }
/* 1117:     */  
/* 1121:     */  private final class Subset
/* 1122:     */    extends AbstractLongSortedSet
/* 1123:     */    implements Serializable, LongSortedSet
/* 1124:     */  {
/* 1125:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1126:     */    
/* 1129:     */    long from;
/* 1130:     */    
/* 1132:     */    long to;
/* 1133:     */    
/* 1135:     */    boolean bottom;
/* 1136:     */    
/* 1138:     */    boolean top;
/* 1139:     */    
/* 1142:     */    public Subset(long from, boolean bottom, long to, boolean top)
/* 1143:     */    {
/* 1144:1063 */      if ((!bottom) && (!top) && (LongRBTreeSet.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start element (").append(from).append(") is larger than end element (").append(to).append(")").toString());
/* 1145:1064 */      this.from = from;
/* 1146:1065 */      this.bottom = bottom;
/* 1147:1066 */      this.to = to;
/* 1148:1067 */      this.top = top;
/* 1149:     */    }
/* 1150:     */    
/* 1151:1070 */    public void clear() { SubsetIterator i = new SubsetIterator();
/* 1152:1071 */      while (i.hasNext()) {
/* 1153:1072 */        i.next();
/* 1154:1073 */        i.remove();
/* 1155:     */      }
/* 1156:     */    }
/* 1157:     */    
/* 1160:     */    final boolean in(long k)
/* 1161:     */    {
/* 1162:1081 */      return ((this.bottom) || (LongRBTreeSet.this.compare(k, this.from) >= 0)) && ((this.top) || (LongRBTreeSet.this.compare(k, this.to) < 0));
/* 1163:     */    }
/* 1164:     */    
/* 1167:1086 */    public boolean contains(long k) { return (in(k)) && (LongRBTreeSet.this.contains(k)); }
/* 1168:     */    
/* 1169:     */    public boolean add(long k) {
/* 1170:1089 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Element (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1171:1090 */      return LongRBTreeSet.this.add(k);
/* 1172:     */    }
/* 1173:     */    
/* 1174:     */    public boolean remove(long k) {
/* 1175:1094 */      if (!in(k)) return false;
/* 1176:1095 */      return LongRBTreeSet.this.remove(k);
/* 1177:     */    }
/* 1178:     */    
/* 1179:1098 */    public int size() { SubsetIterator i = new SubsetIterator();
/* 1180:1099 */      int n = 0;
/* 1181:1100 */      while (i.hasNext()) {
/* 1182:1101 */        n++;
/* 1183:1102 */        i.next();
/* 1184:     */      }
/* 1185:1104 */      return n;
/* 1186:     */    }
/* 1187:     */    
/* 1188:1107 */    public boolean isEmpty() { return !new SubsetIterator().hasNext(); }
/* 1189:     */    
/* 1190:     */    public LongComparator comparator() {
/* 1191:1110 */      return LongRBTreeSet.this.actualComparator;
/* 1192:     */    }
/* 1193:     */    
/* 1194:1113 */    public LongBidirectionalIterator iterator() { return new SubsetIterator(); }
/* 1195:     */    
/* 1197:1116 */    public LongBidirectionalIterator iterator(long from) { return new SubsetIterator(from); }
/* 1198:     */    
/* 1199:     */    public LongSortedSet headSet(long to) {
/* 1200:1119 */      if (this.top) return new Subset(LongRBTreeSet.this, this.from, this.bottom, to, false);
/* 1201:1120 */      return LongRBTreeSet.this.compare(to, this.to) < 0 ? new Subset(LongRBTreeSet.this, this.from, this.bottom, to, false) : this;
/* 1202:     */    }
/* 1203:     */    
/* 1204:1123 */    public LongSortedSet tailSet(long from) { if (this.bottom) return new Subset(LongRBTreeSet.this, from, false, this.to, this.top);
/* 1205:1124 */      return LongRBTreeSet.this.compare(from, this.from) > 0 ? new Subset(LongRBTreeSet.this, from, false, this.to, this.top) : this;
/* 1206:     */    }
/* 1207:     */    
/* 1208:1127 */    public LongSortedSet subSet(long from, long to) { if ((this.top) && (this.bottom)) return new Subset(LongRBTreeSet.this, from, false, to, false);
/* 1209:1128 */      if (!this.top) to = LongRBTreeSet.this.compare(to, this.to) < 0 ? to : this.to;
/* 1210:1129 */      if (!this.bottom) from = LongRBTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
/* 1211:1130 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1212:1131 */      return new Subset(LongRBTreeSet.this, from, false, to, false);
/* 1213:     */    }
/* 1214:     */    
/* 1217:     */    public LongRBTreeSet.Entry firstEntry()
/* 1218:     */    {
/* 1219:1138 */      if (LongRBTreeSet.this.tree == null) return null;
/* 1220:     */      LongRBTreeSet.Entry e;
/* 1221:     */      LongRBTreeSet.Entry e;
/* 1222:1141 */      if (this.bottom) { e = LongRBTreeSet.this.firstEntry;
/* 1223:     */      } else {
/* 1224:1143 */        e = LongRBTreeSet.this.locateKey(this.from);
/* 1225:     */        
/* 1226:1145 */        if (LongRBTreeSet.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1227:     */        }
/* 1228:     */      }
/* 1229:1148 */      if ((e == null) || ((!this.top) && (LongRBTreeSet.this.compare(e.key, this.to) >= 0))) return null;
/* 1230:1149 */      return e;
/* 1231:     */    }
/* 1232:     */    
/* 1235:     */    public LongRBTreeSet.Entry lastEntry()
/* 1236:     */    {
/* 1237:1156 */      if (LongRBTreeSet.this.tree == null) return null;
/* 1238:     */      LongRBTreeSet.Entry e;
/* 1239:     */      LongRBTreeSet.Entry e;
/* 1240:1159 */      if (this.top) { e = LongRBTreeSet.this.lastEntry;
/* 1241:     */      } else {
/* 1242:1161 */        e = LongRBTreeSet.this.locateKey(this.to);
/* 1243:     */        
/* 1244:1163 */        if (LongRBTreeSet.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1245:     */        }
/* 1246:     */      }
/* 1247:1166 */      if ((e == null) || ((!this.bottom) && (LongRBTreeSet.this.compare(e.key, this.from) < 0))) return null;
/* 1248:1167 */      return e;
/* 1249:     */    }
/* 1250:     */    
/* 1251:1170 */    public long firstLong() { LongRBTreeSet.Entry e = firstEntry();
/* 1252:1171 */      if (e == null) throw new NoSuchElementException();
/* 1253:1172 */      return e.key;
/* 1254:     */    }
/* 1255:     */    
/* 1256:1175 */    public long lastLong() { LongRBTreeSet.Entry e = lastEntry();
/* 1257:1176 */      if (e == null) throw new NoSuchElementException();
/* 1258:1177 */      return e.key;
/* 1259:     */    }
/* 1260:     */    
/* 1263:     */    private final class SubsetIterator
/* 1264:     */      extends LongRBTreeSet.SetIterator
/* 1265:     */    {
/* 1266:     */      SubsetIterator()
/* 1267:     */      {
/* 1268:1187 */        super();
/* 1269:1188 */        this.next = LongRBTreeSet.Subset.this.firstEntry();
/* 1270:     */      }
/* 1271:     */      
/* 1272:1191 */      SubsetIterator(long k) { this();
/* 1273:1192 */        if (this.next != null)
/* 1274:1193 */          if ((!LongRBTreeSet.Subset.this.bottom) && (LongRBTreeSet.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1275:1194 */          } else if ((!LongRBTreeSet.Subset.this.top) && (LongRBTreeSet.this.compare(k, (this.prev = LongRBTreeSet.Subset.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1276:     */          } else {
/* 1277:1196 */            this.next = LongRBTreeSet.this.locateKey(k);
/* 1278:1197 */            if (LongRBTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1279:1198 */              this.prev = this.next;
/* 1280:1199 */              this.next = this.next.next();
/* 1281:     */            } else {
/* 1282:1201 */              this.prev = this.next.prev();
/* 1283:     */            }
/* 1284:     */          }
/* 1285:     */      }
/* 1286:     */      
/* 1287:1206 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1288:1207 */        if ((!LongRBTreeSet.Subset.this.bottom) && (this.prev != null) && (LongRBTreeSet.this.compare(this.prev.key, LongRBTreeSet.Subset.this.from) < 0)) this.prev = null;
/* 1289:     */      }
/* 1290:     */      
/* 1291:1210 */      void updateNext() { this.next = this.next.next();
/* 1292:1211 */        if ((!LongRBTreeSet.Subset.this.top) && (this.next != null) && (LongRBTreeSet.this.compare(this.next.key, LongRBTreeSet.Subset.this.to) >= 0)) { this.next = null;
/* 1293:     */        }
/* 1294:     */      }
/* 1295:     */    }
/* 1296:     */  }
/* 1297:     */  
/* 1300:     */  public Object clone()
/* 1301:     */  {
/* 1302:     */    LongRBTreeSet c;
/* 1303:     */    
/* 1305:     */    try
/* 1306:     */    {
/* 1307:1226 */      c = (LongRBTreeSet)super.clone();
/* 1308:     */    }
/* 1309:     */    catch (CloneNotSupportedException cantHappen) {
/* 1310:1229 */      throw new InternalError();
/* 1311:     */    }
/* 1312:1231 */    c.allocatePaths();
/* 1313:1232 */    if (this.count != 0)
/* 1314:     */    {
/* 1315:1234 */      Entry rp = new Entry();Entry rq = new Entry();
/* 1316:1235 */      Entry p = rp;
/* 1317:1236 */      rp.left(this.tree);
/* 1318:1237 */      Entry q = rq;
/* 1319:1238 */      rq.pred(null);
/* 1320:     */      for (;;) {
/* 1321:1240 */        if (!p.pred()) {
/* 1322:1241 */          Entry e = p.left.clone();
/* 1323:1242 */          e.pred(q.left);
/* 1324:1243 */          e.succ(q);
/* 1325:1244 */          q.left(e);
/* 1326:1245 */          p = p.left;
/* 1327:1246 */          q = q.left;
/* 1328:     */        }
/* 1329:     */        else {
/* 1330:1249 */          while (p.succ()) {
/* 1331:1250 */            p = p.right;
/* 1332:1251 */            if (p == null) {
/* 1333:1252 */              q.right = null;
/* 1334:1253 */              c.tree = rq.left;
/* 1335:1254 */              c.firstEntry = c.tree;
/* 1336:1255 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1337:1256 */              c.lastEntry = c.tree;
/* 1338:1257 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1339:1258 */              return c;
/* 1340:     */            }
/* 1341:1260 */            q = q.right;
/* 1342:     */          }
/* 1343:1262 */          p = p.right;
/* 1344:1263 */          q = q.right;
/* 1345:     */        }
/* 1346:1265 */        if (!p.succ()) {
/* 1347:1266 */          Entry e = p.right.clone();
/* 1348:1267 */          e.succ(q.right);
/* 1349:1268 */          e.pred(q);
/* 1350:1269 */          q.right(e);
/* 1351:     */        }
/* 1352:     */      }
/* 1353:     */    }
/* 1354:1273 */    return c;
/* 1355:     */  }
/* 1356:     */  
/* 1357:1276 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1358:1277 */    SetIterator i = new SetIterator();
/* 1359:1278 */    s.defaultWriteObject();
/* 1360:1279 */    while (n-- != 0) { s.writeLong(i.nextLong());
/* 1361:     */    }
/* 1362:     */  }
/* 1363:     */  
/* 1368:     */  private Entry readTree(ObjectInputStream s, int n, Entry pred, Entry succ)
/* 1369:     */    throws IOException, ClassNotFoundException
/* 1370:     */  {
/* 1371:1290 */    if (n == 1) {
/* 1372:1291 */      Entry top = new Entry(s.readLong());
/* 1373:1292 */      top.pred(pred);
/* 1374:1293 */      top.succ(succ);
/* 1375:1294 */      top.black(true);
/* 1376:1295 */      return top;
/* 1377:     */    }
/* 1378:1297 */    if (n == 2)
/* 1379:     */    {
/* 1381:1300 */      Entry top = new Entry(s.readLong());
/* 1382:1301 */      top.black(true);
/* 1383:1302 */      top.right(new Entry(s.readLong()));
/* 1384:1303 */      top.right.pred(top);
/* 1385:1304 */      top.pred(pred);
/* 1386:1305 */      top.right.succ(succ);
/* 1387:1306 */      return top;
/* 1388:     */    }
/* 1389:     */    
/* 1390:1309 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1391:1310 */    Entry top = new Entry();
/* 1392:1311 */    top.left(readTree(s, leftN, pred, top));
/* 1393:1312 */    top.key = s.readLong();
/* 1394:1313 */    top.black(true);
/* 1395:1314 */    top.right(readTree(s, rightN, top, succ));
/* 1396:1315 */    if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1397:1316 */    return top;
/* 1398:     */  }
/* 1399:     */  
/* 1400:1319 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1401:     */    
/* 1403:1322 */    setActualComparator();
/* 1404:1323 */    allocatePaths();
/* 1405:1324 */    if (this.count != 0) {
/* 1406:1325 */      this.tree = readTree(s, this.count, null, null);
/* 1407:     */      
/* 1408:1327 */      Entry e = this.tree;
/* 1409:1328 */      while (e.left() != null) e = e.left();
/* 1410:1329 */      this.firstEntry = e;
/* 1411:1330 */      e = this.tree;
/* 1412:1331 */      while (e.right() != null) e = e.right();
/* 1413:1332 */      this.lastEntry = e;
/* 1414:     */    }
/* 1415:     */  }
/* 1416:     */  
/* 1417:     */  private void checkNodePath() {}
/* 1418:     */  
/* 1419:1338 */  private int checkTree(Entry e, int d, int D) { return 0; }
/* 1420:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongRBTreeSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */