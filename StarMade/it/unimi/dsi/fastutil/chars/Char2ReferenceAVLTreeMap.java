/*    1:     */package it.unimi.dsi.fastutil.chars;
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
/*   71:     */public class Char2ReferenceAVLTreeMap<V>
/*   72:     */  extends AbstractChar2ReferenceSortedMap<V>
/*   73:     */  implements Serializable, Cloneable
/*   74:     */{
/*   75:     */  protected transient Entry<V> tree;
/*   76:     */  protected int count;
/*   77:     */  protected transient Entry<V> firstEntry;
/*   78:     */  protected transient Entry<V> lastEntry;
/*   79:     */  protected volatile transient ObjectSortedSet<Char2ReferenceMap.Entry<V>> entries;
/*   80:     */  protected volatile transient CharSortedSet keys;
/*   81:     */  protected volatile transient ReferenceCollection<V> values;
/*   82:     */  protected transient boolean modified;
/*   83:     */  protected Comparator<? super Character> storedComparator;
/*   84:     */  protected transient CharComparator actualComparator;
/*   85:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   86:     */  private static final boolean ASSERTS = false;
/*   87:     */  private transient boolean[] dirPath;
/*   88:     */  
/*   89:     */  public Char2ReferenceAVLTreeMap()
/*   90:     */  {
/*   91:  91 */    allocatePaths();
/*   92:     */    
/*   96:  96 */    this.tree = null;
/*   97:  97 */    this.count = 0;
/*   98:     */  }
/*   99:     */  
/*  112:     */  private void setActualComparator()
/*  113:     */  {
/*  114: 114 */    if ((this.storedComparator == null) || ((this.storedComparator instanceof CharComparator))) this.actualComparator = ((CharComparator)this.storedComparator); else {
/*  115: 115 */      this.actualComparator = new CharComparator() {
/*  116:     */        public int compare(char k1, char k2) {
/*  117: 117 */          return Char2ReferenceAVLTreeMap.this.storedComparator.compare(Character.valueOf(k1), Character.valueOf(k2));
/*  118:     */        }
/*  119:     */        
/*  120: 120 */        public int compare(Character ok1, Character ok2) { return Char2ReferenceAVLTreeMap.this.storedComparator.compare(ok1, ok2); }
/*  121:     */      };
/*  122:     */    }
/*  123:     */  }
/*  124:     */  
/*  131:     */  public Char2ReferenceAVLTreeMap(Comparator<? super Character> c)
/*  132:     */  {
/*  133: 133 */    this();
/*  134: 134 */    this.storedComparator = c;
/*  135: 135 */    setActualComparator();
/*  136:     */  }
/*  137:     */  
/*  143:     */  public Char2ReferenceAVLTreeMap(Map<? extends Character, ? extends V> m)
/*  144:     */  {
/*  145: 145 */    this();
/*  146: 146 */    putAll(m);
/*  147:     */  }
/*  148:     */  
/*  153:     */  public Char2ReferenceAVLTreeMap(SortedMap<Character, V> m)
/*  154:     */  {
/*  155: 155 */    this(m.comparator());
/*  156: 156 */    putAll(m);
/*  157:     */  }
/*  158:     */  
/*  163:     */  public Char2ReferenceAVLTreeMap(Char2ReferenceMap<? extends V> m)
/*  164:     */  {
/*  165: 165 */    this();
/*  166: 166 */    putAll(m);
/*  167:     */  }
/*  168:     */  
/*  173:     */  public Char2ReferenceAVLTreeMap(Char2ReferenceSortedMap<V> m)
/*  174:     */  {
/*  175: 175 */    this(m.comparator());
/*  176: 176 */    putAll(m);
/*  177:     */  }
/*  178:     */  
/*  186:     */  public Char2ReferenceAVLTreeMap(char[] k, V[] v, Comparator<? super Character> c)
/*  187:     */  {
/*  188: 188 */    this(c);
/*  189: 189 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  190: 190 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  191:     */    }
/*  192:     */  }
/*  193:     */  
/*  199:     */  public Char2ReferenceAVLTreeMap(char[] k, V[] v)
/*  200:     */  {
/*  201: 201 */    this(k, v, null);
/*  202:     */  }
/*  203:     */  
/*  226:     */  final int compare(char k1, char k2)
/*  227:     */  {
/*  228: 228 */    return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*  229:     */  }
/*  230:     */  
/*  238:     */  final Entry<V> findKey(char k)
/*  239:     */  {
/*  240: 240 */    Entry<V> e = this.tree;
/*  241:     */    
/*  242:     */    int cmp;
/*  243: 243 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) { e = cmp < 0 ? e.left() : e.right();
/*  244:     */    }
/*  245: 245 */    return e;
/*  246:     */  }
/*  247:     */  
/*  254:     */  final Entry<V> locateKey(char k)
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
/*  278:     */  public V put(char k, V v)
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
/*  504:     */  public V remove(char k)
/*  505:     */  {
/*  506: 506 */    this.modified = false;
/*  507:     */    
/*  508: 508 */    if (this.tree == null) { return this.defRetValue;
/*  509:     */    }
/*  510:     */    
/*  511: 511 */    Entry<V> p = this.tree;Entry<V> q = null;
/*  512: 512 */    boolean dir = false;
/*  513: 513 */    char kk = k;
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
/*  777:     */  public V put(Character ok, V ov)
/*  778:     */  {
/*  779: 779 */    V oldValue = put(ok.charValue(), ov);
/*  780: 780 */    return this.modified ? this.defRetValue : oldValue;
/*  781:     */  }
/*  782:     */  
/*  786:     */  public V remove(Object ok)
/*  787:     */  {
/*  788: 788 */    V oldValue = remove(((Character)ok).charValue());
/*  789: 789 */    return this.modified ? oldValue : this.defRetValue;
/*  790:     */  }
/*  791:     */  
/*  793:     */  public boolean containsValue(Object v)
/*  794:     */  {
/*  795: 795 */    Char2ReferenceAVLTreeMap<V>.ValueIterator i = new ValueIterator(null);
/*  796:     */    
/*  798: 798 */    int j = this.count;
/*  799: 799 */    while (j-- != 0) {
/*  800: 800 */      V ev = i.next();
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
/*  818:     */  private static final class Entry<V>
/*  819:     */    implements Cloneable, Char2ReferenceMap.Entry<V>
/*  820:     */  {
/*  821:     */    private static final int SUCC_MASK = -2147483648;
/*  822:     */    
/*  824:     */    private static final int PRED_MASK = 1073741824;
/*  825:     */    
/*  827:     */    private static final int BALANCE_MASK = 255;
/*  828:     */    
/*  830:     */    char key;
/*  831:     */    
/*  833:     */    V value;
/*  834:     */    
/*  836:     */    Entry<V> left;
/*  837:     */    
/*  839:     */    Entry<V> right;
/*  840:     */    
/*  842:     */    int info;
/*  843:     */    
/*  845:     */    Entry() {}
/*  846:     */    
/*  848:     */    Entry(char k, V v)
/*  849:     */    {
/*  850: 850 */      this.key = k;
/*  851: 851 */      this.value = v;
/*  852: 852 */      this.info = -1073741824;
/*  853:     */    }
/*  854:     */    
/*  859:     */    Entry<V> left()
/*  860:     */    {
/*  861: 861 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  862:     */    }
/*  863:     */    
/*  868:     */    Entry<V> right()
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
/*  905:     */    void pred(Entry<V> pred)
/*  906:     */    {
/*  907: 907 */      this.info |= 1073741824;
/*  908: 908 */      this.left = pred;
/*  909:     */    }
/*  910:     */    
/*  913:     */    void succ(Entry<V> succ)
/*  914:     */    {
/*  915: 915 */      this.info |= -2147483648;
/*  916: 916 */      this.right = succ;
/*  917:     */    }
/*  918:     */    
/*  921:     */    void left(Entry<V> left)
/*  922:     */    {
/*  923: 923 */      this.info &= -1073741825;
/*  924: 924 */      this.left = left;
/*  925:     */    }
/*  926:     */    
/*  929:     */    void right(Entry<V> right)
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
/*  964:     */    Entry<V> next()
/*  965:     */    {
/*  966: 966 */      Entry<V> next = this.right;
/*  967: 967 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  968: 968 */      return next;
/*  969:     */    }
/*  970:     */    
/*  975:     */    Entry<V> prev()
/*  976:     */    {
/*  977: 977 */      Entry<V> prev = this.left;
/*  978: 978 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  979: 979 */      return prev;
/*  980:     */    }
/*  981:     */    
/*  982:     */    public Character getKey() {
/*  983: 983 */      return Character.valueOf(this.key);
/*  984:     */    }
/*  985:     */    
/*  986:     */    public char getCharKey()
/*  987:     */    {
/*  988: 988 */      return this.key;
/*  989:     */    }
/*  990:     */    
/*  991:     */    public V getValue()
/*  992:     */    {
/*  993: 993 */      return this.value;
/*  994:     */    }
/*  995:     */    
/* 1001:     */    public V setValue(V value)
/* 1002:     */    {
/* 1003:1003 */      V oldValue = this.value;
/* 1004:1004 */      this.value = value;
/* 1005:1005 */      return oldValue;
/* 1006:     */    }
/* 1007:     */    
/* 1008:     */    public Entry<V> clone() {
/* 1009:     */      Entry<V> c;
/* 1010:     */      try {
/* 1011:1011 */        c = (Entry)super.clone();
/* 1012:     */      }
/* 1013:     */      catch (CloneNotSupportedException cantHappen) {
/* 1014:1014 */        throw new InternalError();
/* 1015:     */      }
/* 1016:1016 */      c.key = this.key;
/* 1017:1017 */      c.value = this.value;
/* 1018:1018 */      c.info = this.info;
/* 1019:1019 */      return c;
/* 1020:     */    }
/* 1021:     */    
/* 1022:     */    public boolean equals(Object o) {
/* 1023:1023 */      if (!(o instanceof Map.Entry)) return false;
/* 1024:1024 */      Map.Entry<Character, V> e = (Map.Entry)o;
/* 1025:1025 */      return (this.key == ((Character)e.getKey()).charValue()) && (this.value == e.getValue());
/* 1026:     */    }
/* 1027:     */    
/* 1028:1028 */    public int hashCode() { return this.key ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
/* 1029:     */    
/* 1030:     */    public String toString() {
/* 1031:1031 */      return this.key + "=>" + this.value;
/* 1032:     */    }
/* 1033:     */  }
/* 1034:     */  
/* 1065:     */  public boolean containsKey(char k)
/* 1066:     */  {
/* 1067:1067 */    return findKey(k) != null;
/* 1068:     */  }
/* 1069:     */  
/* 1070:1070 */  public int size() { return this.count; }
/* 1071:     */  
/* 1072:     */  public boolean isEmpty() {
/* 1073:1073 */    return this.count == 0;
/* 1074:     */  }
/* 1075:     */  
/* 1076:     */  public V get(char k) {
/* 1077:1077 */    Entry<V> e = findKey(k);
/* 1078:1078 */    return e == null ? this.defRetValue : e.value;
/* 1079:     */  }
/* 1080:     */  
/* 1081:1081 */  public char firstCharKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1082:1082 */    return this.firstEntry.key;
/* 1083:     */  }
/* 1084:     */  
/* 1085:1085 */  public char lastCharKey() { if (this.tree == null) throw new NoSuchElementException();
/* 1086:1086 */    return this.lastEntry.key;
/* 1087:     */  }
/* 1088:     */  
/* 1091:     */  private class TreeIterator
/* 1092:     */  {
/* 1093:     */    Char2ReferenceAVLTreeMap.Entry<V> prev;
/* 1094:     */    
/* 1096:     */    Char2ReferenceAVLTreeMap.Entry<V> next;
/* 1097:     */    
/* 1098:     */    Char2ReferenceAVLTreeMap.Entry<V> curr;
/* 1099:     */    
/* 1100:1100 */    int index = 0;
/* 1101:     */    
/* 1102:1102 */    TreeIterator() { this.next = Char2ReferenceAVLTreeMap.this.firstEntry; }
/* 1103:     */    
/* 1104:     */    TreeIterator(char k) {
/* 1105:1105 */      if ((this.next = Char2ReferenceAVLTreeMap.this.locateKey(k)) != null)
/* 1106:1106 */        if (Char2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1107:1107 */          this.prev = this.next;
/* 1108:1108 */          this.next = this.next.next();
/* 1109:     */        } else {
/* 1110:1110 */          this.prev = this.next.prev();
/* 1111:     */        } }
/* 1112:     */    
/* 1113:1113 */    public boolean hasNext() { return this.next != null; }
/* 1114:1114 */    public boolean hasPrevious() { return this.prev != null; }
/* 1115:     */    
/* 1116:1116 */    void updateNext() { this.next = this.next.next(); }
/* 1117:     */    
/* 1118:     */    Char2ReferenceAVLTreeMap.Entry<V> nextEntry() {
/* 1119:1119 */      if (!hasNext()) throw new NoSuchElementException();
/* 1120:1120 */      this.curr = (this.prev = this.next);
/* 1121:1121 */      this.index += 1;
/* 1122:1122 */      updateNext();
/* 1123:1123 */      return this.curr;
/* 1124:     */    }
/* 1125:     */    
/* 1126:1126 */    void updatePrevious() { this.prev = this.prev.prev(); }
/* 1127:     */    
/* 1128:     */    Char2ReferenceAVLTreeMap.Entry<V> previousEntry() {
/* 1129:1129 */      if (!hasPrevious()) throw new NoSuchElementException();
/* 1130:1130 */      this.curr = (this.next = this.prev);
/* 1131:1131 */      this.index -= 1;
/* 1132:1132 */      updatePrevious();
/* 1133:1133 */      return this.curr;
/* 1134:     */    }
/* 1135:     */    
/* 1136:1136 */    public int nextIndex() { return this.index; }
/* 1137:     */    
/* 1139:1139 */    public int previousIndex() { return this.index - 1; }
/* 1140:     */    
/* 1141:     */    public void remove() {
/* 1142:1142 */      if (this.curr == null) { throw new IllegalStateException();
/* 1143:     */      }
/* 1144:     */      
/* 1145:1145 */      if (this.curr == this.prev) this.index -= 1;
/* 1146:1146 */      this.next = (this.prev = this.curr);
/* 1147:1147 */      updatePrevious();
/* 1148:1148 */      updateNext();
/* 1149:1149 */      Char2ReferenceAVLTreeMap.this.remove(this.curr.key);
/* 1150:1150 */      this.curr = null;
/* 1151:     */    }
/* 1152:     */    
/* 1153:1153 */    public int skip(int n) { int i = n;
/* 1154:1154 */      while ((i-- != 0) && (hasNext())) nextEntry();
/* 1155:1155 */      return n - i - 1;
/* 1156:     */    }
/* 1157:     */    
/* 1158:1158 */    public int back(int n) { int i = n;
/* 1159:1159 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1160:1160 */      return n - i - 1;
/* 1161:     */    }
/* 1162:     */  }
/* 1163:     */  
/* 1164:     */  private class EntryIterator
/* 1165:     */    extends Char2ReferenceAVLTreeMap<V>.TreeIterator
/* 1166:     */    implements ObjectListIterator<Char2ReferenceMap.Entry<V>>
/* 1167:     */  {
/* 1168:1168 */    EntryIterator() { super(); }
/* 1169:     */    
/* 1170:1170 */    EntryIterator(char k) { super(k); }
/* 1171:     */    
/* 1172:1172 */    public Char2ReferenceMap.Entry<V> next() { return nextEntry(); }
/* 1173:1173 */    public Char2ReferenceMap.Entry<V> previous() { return previousEntry(); }
/* 1174:1174 */    public void set(Char2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1175:1175 */    public void add(Char2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1176:     */  }
/* 1177:     */  
/* 1178:1178 */  public ObjectSortedSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1179:1179 */        final Comparator<? super Char2ReferenceMap.Entry<V>> comparator = new Comparator() {
/* 1180:     */          public int compare(Char2ReferenceMap.Entry<V> x, Char2ReferenceMap.Entry<V> y) {
/* 1181:1181 */            return Char2ReferenceAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1182:     */          }
/* 1183:     */        };
/* 1184:     */        
/* 1185:1185 */        public Comparator<? super Char2ReferenceMap.Entry<V>> comparator() { return this.comparator; }
/* 1186:     */        
/* 1187:     */        public ObjectBidirectionalIterator<Char2ReferenceMap.Entry<V>> iterator() {
/* 1188:1188 */          return new Char2ReferenceAVLTreeMap.EntryIterator(Char2ReferenceAVLTreeMap.this);
/* 1189:     */        }
/* 1190:     */        
/* 1191:1191 */        public ObjectBidirectionalIterator<Char2ReferenceMap.Entry<V>> iterator(Char2ReferenceMap.Entry<V> from) { return new Char2ReferenceAVLTreeMap.EntryIterator(Char2ReferenceAVLTreeMap.this, ((Character)from.getKey()).charValue()); }
/* 1192:     */        
/* 1193:     */        public boolean contains(Object o)
/* 1194:     */        {
/* 1195:1195 */          if (!(o instanceof Map.Entry)) return false;
/* 1196:1196 */          Map.Entry<Character, V> e = (Map.Entry)o;
/* 1197:1197 */          Char2ReferenceAVLTreeMap.Entry<V> f = Char2ReferenceAVLTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1198:1198 */          return e.equals(f);
/* 1199:     */        }
/* 1200:     */        
/* 1201:     */        public boolean remove(Object o) {
/* 1202:1202 */          if (!(o instanceof Map.Entry)) return false;
/* 1203:1203 */          Map.Entry<Character, V> e = (Map.Entry)o;
/* 1204:1204 */          Char2ReferenceAVLTreeMap.Entry<V> f = Char2ReferenceAVLTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1205:1205 */          if (f != null) Char2ReferenceAVLTreeMap.this.remove(f.key);
/* 1206:1206 */          return f != null; }
/* 1207:     */        
/* 1208:1208 */        public int size() { return Char2ReferenceAVLTreeMap.this.count; }
/* 1209:1209 */        public void clear() { Char2ReferenceAVLTreeMap.this.clear(); }
/* 1210:1210 */        public Char2ReferenceMap.Entry<V> first() { return Char2ReferenceAVLTreeMap.this.firstEntry; }
/* 1211:1211 */        public Char2ReferenceMap.Entry<V> last() { return Char2ReferenceAVLTreeMap.this.lastEntry; }
/* 1212:1212 */        public ObjectSortedSet<Char2ReferenceMap.Entry<V>> subSet(Char2ReferenceMap.Entry<V> from, Char2ReferenceMap.Entry<V> to) { return Char2ReferenceAVLTreeMap.this.subMap((Character)from.getKey(), (Character)to.getKey()).char2ReferenceEntrySet(); }
/* 1213:1213 */        public ObjectSortedSet<Char2ReferenceMap.Entry<V>> headSet(Char2ReferenceMap.Entry<V> to) { return Char2ReferenceAVLTreeMap.this.headMap((Character)to.getKey()).char2ReferenceEntrySet(); }
/* 1214:1214 */        public ObjectSortedSet<Char2ReferenceMap.Entry<V>> tailSet(Char2ReferenceMap.Entry<V> from) { return Char2ReferenceAVLTreeMap.this.tailMap((Character)from.getKey()).char2ReferenceEntrySet(); }
/* 1215:     */      };
/* 1216:1216 */    return this.entries;
/* 1217:     */  }
/* 1218:     */  
/* 1221:     */  private final class KeyIterator
/* 1222:     */    extends Char2ReferenceAVLTreeMap.TreeIterator
/* 1223:     */    implements CharListIterator
/* 1224:     */  {
/* 1225:1225 */    public KeyIterator() { super(); }
/* 1226:1226 */    public KeyIterator(char k) { super(k); }
/* 1227:1227 */    public char nextChar() { return nextEntry().key; }
/* 1228:1228 */    public char previousChar() { return previousEntry().key; }
/* 1229:1229 */    public void set(char k) { throw new UnsupportedOperationException(); }
/* 1230:1230 */    public void add(char k) { throw new UnsupportedOperationException(); }
/* 1231:1231 */    public Character next() { return Character.valueOf(nextEntry().key); }
/* 1232:1232 */    public Character previous() { return Character.valueOf(previousEntry().key); }
/* 1233:1233 */    public void set(Character ok) { throw new UnsupportedOperationException(); }
/* 1234:1234 */    public void add(Character ok) { throw new UnsupportedOperationException(); }
/* 1235:     */  }
/* 1236:     */  
/* 1237:1237 */  private class KeySet extends AbstractChar2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1238:1238 */    public CharBidirectionalIterator iterator() { return new Char2ReferenceAVLTreeMap.KeyIterator(Char2ReferenceAVLTreeMap.this); }
/* 1239:1239 */    public CharBidirectionalIterator iterator(char from) { return new Char2ReferenceAVLTreeMap.KeyIterator(Char2ReferenceAVLTreeMap.this, from); }
/* 1240:     */  }
/* 1241:     */  
/* 1248:     */  public CharSortedSet keySet()
/* 1249:     */  {
/* 1250:1250 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1251:1251 */    return this.keys;
/* 1252:     */  }
/* 1253:     */  
/* 1255:     */  private final class ValueIterator
/* 1256:     */    extends Char2ReferenceAVLTreeMap<V>.TreeIterator
/* 1257:     */    implements ObjectListIterator<V>
/* 1258:     */  {
/* 1259:1259 */    private ValueIterator() { super(); }
/* 1260:1260 */    public V next() { return nextEntry().value; }
/* 1261:1261 */    public V previous() { return previousEntry().value; }
/* 1262:1262 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1263:1263 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1264:     */  }
/* 1265:     */  
/* 1272:     */  public ReferenceCollection<V> values()
/* 1273:     */  {
/* 1274:1274 */    if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1275:     */        public ObjectIterator<V> iterator() {
/* 1276:1276 */          return new Char2ReferenceAVLTreeMap.ValueIterator(Char2ReferenceAVLTreeMap.this, null);
/* 1277:     */        }
/* 1278:     */        
/* 1279:1279 */        public boolean contains(Object k) { return Char2ReferenceAVLTreeMap.this.containsValue(k); }
/* 1280:     */        
/* 1281:     */        public int size() {
/* 1282:1282 */          return Char2ReferenceAVLTreeMap.this.count;
/* 1283:     */        }
/* 1284:     */        
/* 1285:1285 */        public void clear() { Char2ReferenceAVLTreeMap.this.clear(); }
/* 1286:     */      };
/* 1287:     */    }
/* 1288:1288 */    return this.values;
/* 1289:     */  }
/* 1290:     */  
/* 1291:1291 */  public CharComparator comparator() { return this.actualComparator; }
/* 1292:     */  
/* 1293:     */  public Char2ReferenceSortedMap<V> headMap(char to) {
/* 1294:1294 */    return new Submap('\000', true, to, false);
/* 1295:     */  }
/* 1296:     */  
/* 1297:1297 */  public Char2ReferenceSortedMap<V> tailMap(char from) { return new Submap(from, false, '\000', true); }
/* 1298:     */  
/* 1299:     */  public Char2ReferenceSortedMap<V> subMap(char from, char to) {
/* 1300:1300 */    return new Submap(from, false, to, false);
/* 1301:     */  }
/* 1302:     */  
/* 1306:     */  private final class Submap
/* 1307:     */    extends AbstractChar2ReferenceSortedMap<V>
/* 1308:     */    implements Serializable
/* 1309:     */  {
/* 1310:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1311:     */    
/* 1314:     */    char from;
/* 1315:     */    
/* 1317:     */    char to;
/* 1318:     */    
/* 1320:     */    boolean bottom;
/* 1321:     */    
/* 1323:     */    boolean top;
/* 1324:     */    
/* 1326:     */    protected volatile transient ObjectSortedSet<Char2ReferenceMap.Entry<V>> entries;
/* 1327:     */    
/* 1329:     */    protected volatile transient CharSortedSet keys;
/* 1330:     */    
/* 1332:     */    protected volatile transient ReferenceCollection<V> values;
/* 1333:     */    
/* 1336:     */    public Submap(char from, boolean bottom, char to, boolean top)
/* 1337:     */    {
/* 1338:1338 */      if ((!bottom) && (!top) && (Char2ReferenceAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1339:1339 */      this.from = from;
/* 1340:1340 */      this.bottom = bottom;
/* 1341:1341 */      this.to = to;
/* 1342:1342 */      this.top = top;
/* 1343:1343 */      this.defRetValue = Char2ReferenceAVLTreeMap.this.defRetValue;
/* 1344:     */    }
/* 1345:     */    
/* 1346:1346 */    public void clear() { Char2ReferenceAVLTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1347:1347 */      while (i.hasNext()) {
/* 1348:1348 */        i.nextEntry();
/* 1349:1349 */        i.remove();
/* 1350:     */      }
/* 1351:     */    }
/* 1352:     */    
/* 1355:     */    final boolean in(char k)
/* 1356:     */    {
/* 1357:1357 */      return ((this.bottom) || (Char2ReferenceAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Char2ReferenceAVLTreeMap.this.compare(k, this.to) < 0));
/* 1358:     */    }
/* 1359:     */    
/* 1360:     */    public ObjectSortedSet<Char2ReferenceMap.Entry<V>> char2ReferenceEntrySet() {
/* 1361:1361 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1362:     */          public ObjectBidirectionalIterator<Char2ReferenceMap.Entry<V>> iterator() {
/* 1363:1363 */            return new Char2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Char2ReferenceAVLTreeMap.Submap.this);
/* 1364:     */          }
/* 1365:     */          
/* 1366:1366 */          public ObjectBidirectionalIterator<Char2ReferenceMap.Entry<V>> iterator(Char2ReferenceMap.Entry<V> from) { return new Char2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Char2ReferenceAVLTreeMap.Submap.this, ((Character)from.getKey()).charValue()); }
/* 1367:     */          
/* 1368:1368 */          public Comparator<? super Char2ReferenceMap.Entry<V>> comparator() { return Char2ReferenceAVLTreeMap.this.entrySet().comparator(); }
/* 1369:     */          
/* 1370:     */          public boolean contains(Object o) {
/* 1371:1371 */            if (!(o instanceof Map.Entry)) return false;
/* 1372:1372 */            Map.Entry<Character, V> e = (Map.Entry)o;
/* 1373:1373 */            Char2ReferenceAVLTreeMap.Entry<V> f = Char2ReferenceAVLTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1374:1374 */            return (f != null) && (Char2ReferenceAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1375:     */          }
/* 1376:     */          
/* 1377:     */          public boolean remove(Object o) {
/* 1378:1378 */            if (!(o instanceof Map.Entry)) return false;
/* 1379:1379 */            Map.Entry<Character, V> e = (Map.Entry)o;
/* 1380:1380 */            Char2ReferenceAVLTreeMap.Entry<V> f = Char2ReferenceAVLTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1381:1381 */            if ((f != null) && (Char2ReferenceAVLTreeMap.Submap.this.in(f.key))) Char2ReferenceAVLTreeMap.Submap.this.remove(f.key);
/* 1382:1382 */            return f != null;
/* 1383:     */          }
/* 1384:     */          
/* 1385:1385 */          public int size() { int c = 0;
/* 1386:1386 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1387:1387 */            return c;
/* 1388:     */          }
/* 1389:     */          
/* 1390:1390 */          public boolean isEmpty() { return !new Char2ReferenceAVLTreeMap.Submap.SubmapIterator(Char2ReferenceAVLTreeMap.Submap.this).hasNext(); }
/* 1391:     */          
/* 1393:1393 */          public void clear() { Char2ReferenceAVLTreeMap.Submap.this.clear(); }
/* 1394:     */          
/* 1395:1395 */          public Char2ReferenceMap.Entry<V> first() { return Char2ReferenceAVLTreeMap.Submap.this.firstEntry(); }
/* 1396:1396 */          public Char2ReferenceMap.Entry<V> last() { return Char2ReferenceAVLTreeMap.Submap.this.lastEntry(); }
/* 1397:1397 */          public ObjectSortedSet<Char2ReferenceMap.Entry<V>> subSet(Char2ReferenceMap.Entry<V> from, Char2ReferenceMap.Entry<V> to) { return Char2ReferenceAVLTreeMap.Submap.this.subMap((Character)from.getKey(), (Character)to.getKey()).char2ReferenceEntrySet(); }
/* 1398:1398 */          public ObjectSortedSet<Char2ReferenceMap.Entry<V>> headSet(Char2ReferenceMap.Entry<V> to) { return Char2ReferenceAVLTreeMap.Submap.this.headMap((Character)to.getKey()).char2ReferenceEntrySet(); }
/* 1399:1399 */          public ObjectSortedSet<Char2ReferenceMap.Entry<V>> tailSet(Char2ReferenceMap.Entry<V> from) { return Char2ReferenceAVLTreeMap.Submap.this.tailMap((Character)from.getKey()).char2ReferenceEntrySet(); }
/* 1400:     */        };
/* 1401:1401 */      return this.entries; }
/* 1402:     */    
/* 1403:1403 */    private class KeySet extends AbstractChar2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1404:1404 */      public CharBidirectionalIterator iterator() { return new Char2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Char2ReferenceAVLTreeMap.Submap.this); }
/* 1405:1405 */      public CharBidirectionalIterator iterator(char from) { return new Char2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Char2ReferenceAVLTreeMap.Submap.this, from); }
/* 1406:     */    }
/* 1407:     */    
/* 1408:1408 */    public CharSortedSet keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1409:1409 */      return this.keys;
/* 1410:     */    }
/* 1411:     */    
/* 1412:1412 */    public ReferenceCollection<V> values() { if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1413:     */          public ObjectIterator<V> iterator() {
/* 1414:1414 */            return new Char2ReferenceAVLTreeMap.Submap.SubmapValueIterator(Char2ReferenceAVLTreeMap.Submap.this, null);
/* 1415:     */          }
/* 1416:     */          
/* 1417:1417 */          public boolean contains(Object k) { return Char2ReferenceAVLTreeMap.Submap.this.containsValue(k); }
/* 1418:     */          
/* 1419:     */          public int size() {
/* 1420:1420 */            return Char2ReferenceAVLTreeMap.Submap.this.size();
/* 1421:     */          }
/* 1422:     */          
/* 1423:1423 */          public void clear() { Char2ReferenceAVLTreeMap.Submap.this.clear(); }
/* 1424:     */        };
/* 1425:     */      }
/* 1426:1426 */      return this.values;
/* 1427:     */    }
/* 1428:     */    
/* 1430:1430 */    public boolean containsKey(char k) { return (in(k)) && (Char2ReferenceAVLTreeMap.this.containsKey(k)); }
/* 1431:     */    
/* 1432:     */    public boolean containsValue(Object v) {
/* 1433:1433 */      Char2ReferenceAVLTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1434:     */      
/* 1435:1435 */      while (i.hasNext()) {
/* 1436:1436 */        Object ev = i.nextEntry().value;
/* 1437:1437 */        if (ev == v) return true;
/* 1438:     */      }
/* 1439:1439 */      return false;
/* 1440:     */    }
/* 1441:     */    
/* 1442:     */    public V get(char k)
/* 1443:     */    {
/* 1444:1444 */      char kk = k;
/* 1445:1445 */      Char2ReferenceAVLTreeMap.Entry<V> e; return (in(kk)) && ((e = Char2ReferenceAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1446:     */    }
/* 1447:     */    
/* 1448:1448 */    public V put(char k, V v) { Char2ReferenceAVLTreeMap.this.modified = false;
/* 1449:1449 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1450:1450 */      V oldValue = Char2ReferenceAVLTreeMap.this.put(k, v);
/* 1451:1451 */      return Char2ReferenceAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1452:     */    }
/* 1453:     */    
/* 1454:1454 */    public V put(Character ok, V ov) { V oldValue = put(ok.charValue(), ov);
/* 1455:1455 */      return Char2ReferenceAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1456:     */    }
/* 1457:     */    
/* 1458:     */    public V remove(char k) {
/* 1459:1459 */      Char2ReferenceAVLTreeMap.this.modified = false;
/* 1460:1460 */      if (!in(k)) return this.defRetValue;
/* 1461:1461 */      V oldValue = Char2ReferenceAVLTreeMap.this.remove(k);
/* 1462:1462 */      return Char2ReferenceAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1463:     */    }
/* 1464:     */    
/* 1465:1465 */    public V remove(Object ok) { V oldValue = remove(((Character)ok).charValue());
/* 1466:1466 */      return Char2ReferenceAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1467:     */    }
/* 1468:     */    
/* 1469:1469 */    public int size() { Char2ReferenceAVLTreeMap<V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1470:1470 */      int n = 0;
/* 1471:1471 */      while (i.hasNext()) {
/* 1472:1472 */        n++;
/* 1473:1473 */        i.nextEntry();
/* 1474:     */      }
/* 1475:1475 */      return n;
/* 1476:     */    }
/* 1477:     */    
/* 1478:1478 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1479:     */    
/* 1481:1481 */    public CharComparator comparator() { return Char2ReferenceAVLTreeMap.this.actualComparator; }
/* 1482:     */    
/* 1483:     */    public Char2ReferenceSortedMap<V> headMap(char to) {
/* 1484:1484 */      if (this.top) return new Submap(Char2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1485:1485 */      return Char2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Char2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1486:     */    }
/* 1487:     */    
/* 1488:1488 */    public Char2ReferenceSortedMap<V> tailMap(char from) { if (this.bottom) return new Submap(Char2ReferenceAVLTreeMap.this, from, false, this.to, this.top);
/* 1489:1489 */      return Char2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Char2ReferenceAVLTreeMap.this, from, false, this.to, this.top) : this;
/* 1490:     */    }
/* 1491:     */    
/* 1492:1492 */    public Char2ReferenceSortedMap<V> subMap(char from, char to) { if ((this.top) && (this.bottom)) return new Submap(Char2ReferenceAVLTreeMap.this, from, false, to, false);
/* 1493:1493 */      if (!this.top) to = Char2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1494:1494 */      if (!this.bottom) from = Char2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1495:1495 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1496:1496 */      return new Submap(Char2ReferenceAVLTreeMap.this, from, false, to, false);
/* 1497:     */    }
/* 1498:     */    
/* 1501:     */    public Char2ReferenceAVLTreeMap.Entry<V> firstEntry()
/* 1502:     */    {
/* 1503:1503 */      if (Char2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1504:     */      Char2ReferenceAVLTreeMap.Entry<V> e;
/* 1505:     */      Char2ReferenceAVLTreeMap.Entry<V> e;
/* 1506:1506 */      if (this.bottom) { e = Char2ReferenceAVLTreeMap.this.firstEntry;
/* 1507:     */      } else {
/* 1508:1508 */        e = Char2ReferenceAVLTreeMap.this.locateKey(this.from);
/* 1509:     */        
/* 1510:1510 */        if (Char2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1511:     */        }
/* 1512:     */      }
/* 1513:1513 */      if ((e == null) || ((!this.top) && (Char2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1514:1514 */      return e;
/* 1515:     */    }
/* 1516:     */    
/* 1519:     */    public Char2ReferenceAVLTreeMap.Entry<V> lastEntry()
/* 1520:     */    {
/* 1521:1521 */      if (Char2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1522:     */      Char2ReferenceAVLTreeMap.Entry<V> e;
/* 1523:     */      Char2ReferenceAVLTreeMap.Entry<V> e;
/* 1524:1524 */      if (this.top) { e = Char2ReferenceAVLTreeMap.this.lastEntry;
/* 1525:     */      } else {
/* 1526:1526 */        e = Char2ReferenceAVLTreeMap.this.locateKey(this.to);
/* 1527:     */        
/* 1528:1528 */        if (Char2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1529:     */        }
/* 1530:     */      }
/* 1531:1531 */      if ((e == null) || ((!this.bottom) && (Char2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1532:1532 */      return e;
/* 1533:     */    }
/* 1534:     */    
/* 1535:1535 */    public char firstCharKey() { Char2ReferenceAVLTreeMap.Entry<V> e = firstEntry();
/* 1536:1536 */      if (e == null) throw new NoSuchElementException();
/* 1537:1537 */      return e.key;
/* 1538:     */    }
/* 1539:     */    
/* 1540:1540 */    public char lastCharKey() { Char2ReferenceAVLTreeMap.Entry<V> e = lastEntry();
/* 1541:1541 */      if (e == null) throw new NoSuchElementException();
/* 1542:1542 */      return e.key;
/* 1543:     */    }
/* 1544:     */    
/* 1545:1545 */    public Character firstKey() { Char2ReferenceAVLTreeMap.Entry<V> e = firstEntry();
/* 1546:1546 */      if (e == null) throw new NoSuchElementException();
/* 1547:1547 */      return e.getKey();
/* 1548:     */    }
/* 1549:     */    
/* 1550:1550 */    public Character lastKey() { Char2ReferenceAVLTreeMap.Entry<V> e = lastEntry();
/* 1551:1551 */      if (e == null) throw new NoSuchElementException();
/* 1552:1552 */      return e.getKey();
/* 1553:     */    }
/* 1554:     */    
/* 1557:     */    private class SubmapIterator
/* 1558:     */      extends Char2ReferenceAVLTreeMap.TreeIterator
/* 1559:     */    {
/* 1560:     */      SubmapIterator()
/* 1561:     */      {
/* 1562:1562 */        super();
/* 1563:1563 */        this.next = Char2ReferenceAVLTreeMap.Submap.this.firstEntry();
/* 1564:     */      }
/* 1565:     */      
/* 1566:1566 */      SubmapIterator(char k) { this();
/* 1567:1567 */        if (this.next != null)
/* 1568:1568 */          if ((!Char2ReferenceAVLTreeMap.Submap.this.bottom) && (Char2ReferenceAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1569:1569 */          } else if ((!Char2ReferenceAVLTreeMap.Submap.this.top) && (Char2ReferenceAVLTreeMap.this.compare(k, (this.prev = Char2ReferenceAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1570:     */          } else {
/* 1571:1571 */            this.next = Char2ReferenceAVLTreeMap.this.locateKey(k);
/* 1572:1572 */            if (Char2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1573:1573 */              this.prev = this.next;
/* 1574:1574 */              this.next = this.next.next();
/* 1575:     */            } else {
/* 1576:1576 */              this.prev = this.next.prev();
/* 1577:     */            }
/* 1578:     */          }
/* 1579:     */      }
/* 1580:     */      
/* 1581:1581 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1582:1582 */        if ((!Char2ReferenceAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Char2ReferenceAVLTreeMap.this.compare(this.prev.key, Char2ReferenceAVLTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1583:     */      }
/* 1584:     */      
/* 1585:1585 */      void updateNext() { this.next = this.next.next();
/* 1586:1586 */        if ((!Char2ReferenceAVLTreeMap.Submap.this.top) && (this.next != null) && (Char2ReferenceAVLTreeMap.this.compare(this.next.key, Char2ReferenceAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1587:     */      }
/* 1588:     */    }
/* 1589:     */    
/* 1590:1590 */    private class SubmapEntryIterator extends Char2ReferenceAVLTreeMap<V>.Submap.SubmapIterator implements ObjectListIterator<Char2ReferenceMap.Entry<V>> { SubmapEntryIterator() { super(); }
/* 1591:     */      
/* 1592:1592 */      SubmapEntryIterator(char k) { super(k); }
/* 1593:     */      
/* 1594:1594 */      public Char2ReferenceMap.Entry<V> next() { return nextEntry(); }
/* 1595:1595 */      public Char2ReferenceMap.Entry<V> previous() { return previousEntry(); }
/* 1596:1596 */      public void set(Char2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1597:1597 */      public void add(Char2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/* 1598:     */    }
/* 1599:     */    
/* 1604:     */    private final class SubmapKeyIterator
/* 1605:     */      extends Char2ReferenceAVLTreeMap.Submap.SubmapIterator
/* 1606:     */      implements CharListIterator
/* 1607:     */    {
/* 1608:1608 */      public SubmapKeyIterator() { super(); }
/* 1609:1609 */      public SubmapKeyIterator(char from) { super(from); }
/* 1610:1610 */      public char nextChar() { return nextEntry().key; }
/* 1611:1611 */      public char previousChar() { return previousEntry().key; }
/* 1612:1612 */      public void set(char k) { throw new UnsupportedOperationException(); }
/* 1613:1613 */      public void add(char k) { throw new UnsupportedOperationException(); }
/* 1614:1614 */      public Character next() { return Character.valueOf(nextEntry().key); }
/* 1615:1615 */      public Character previous() { return Character.valueOf(previousEntry().key); }
/* 1616:1616 */      public void set(Character ok) { throw new UnsupportedOperationException(); }
/* 1617:1617 */      public void add(Character ok) { throw new UnsupportedOperationException(); }
/* 1618:     */    }
/* 1619:     */    
/* 1623:     */    private final class SubmapValueIterator
/* 1624:     */      extends Char2ReferenceAVLTreeMap<V>.Submap.SubmapIterator
/* 1625:     */      implements ObjectListIterator<V>
/* 1626:     */    {
/* 1627:1627 */      private SubmapValueIterator() { super(); }
/* 1628:1628 */      public V next() { return nextEntry().value; }
/* 1629:1629 */      public V previous() { return previousEntry().value; }
/* 1630:1630 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1631:1631 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1632:     */    }
/* 1633:     */  }
/* 1634:     */  
/* 1638:     */  public Char2ReferenceAVLTreeMap<V> clone()
/* 1639:     */  {
/* 1640:     */    Char2ReferenceAVLTreeMap<V> c;
/* 1641:     */    
/* 1643:     */    try
/* 1644:     */    {
/* 1645:1645 */      c = (Char2ReferenceAVLTreeMap)super.clone();
/* 1646:     */    }
/* 1647:     */    catch (CloneNotSupportedException cantHappen) {
/* 1648:1648 */      throw new InternalError();
/* 1649:     */    }
/* 1650:1650 */    c.keys = null;
/* 1651:1651 */    c.values = null;
/* 1652:1652 */    c.entries = null;
/* 1653:1653 */    c.allocatePaths();
/* 1654:1654 */    if (this.count != 0)
/* 1655:     */    {
/* 1656:1656 */      Entry<V> rp = new Entry();Entry<V> rq = new Entry();
/* 1657:1657 */      Entry<V> p = rp;
/* 1658:1658 */      rp.left(this.tree);
/* 1659:1659 */      Entry<V> q = rq;
/* 1660:1660 */      rq.pred(null);
/* 1661:     */      for (;;) {
/* 1662:1662 */        if (!p.pred()) {
/* 1663:1663 */          Entry<V> e = p.left.clone();
/* 1664:1664 */          e.pred(q.left);
/* 1665:1665 */          e.succ(q);
/* 1666:1666 */          q.left(e);
/* 1667:1667 */          p = p.left;
/* 1668:1668 */          q = q.left;
/* 1669:     */        }
/* 1670:     */        else {
/* 1671:1671 */          while (p.succ()) {
/* 1672:1672 */            p = p.right;
/* 1673:1673 */            if (p == null) {
/* 1674:1674 */              q.right = null;
/* 1675:1675 */              c.tree = rq.left;
/* 1676:1676 */              c.firstEntry = c.tree;
/* 1677:1677 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1678:1678 */              c.lastEntry = c.tree;
/* 1679:1679 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1680:1680 */              return c;
/* 1681:     */            }
/* 1682:1682 */            q = q.right;
/* 1683:     */          }
/* 1684:1684 */          p = p.right;
/* 1685:1685 */          q = q.right;
/* 1686:     */        }
/* 1687:1687 */        if (!p.succ()) {
/* 1688:1688 */          Entry<V> e = p.right.clone();
/* 1689:1689 */          e.succ(q.right);
/* 1690:1690 */          e.pred(q);
/* 1691:1691 */          q.right(e);
/* 1692:     */        }
/* 1693:     */      }
/* 1694:     */    }
/* 1695:1695 */    return c;
/* 1696:     */  }
/* 1697:     */  
/* 1698:1698 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1699:1699 */    Char2ReferenceAVLTreeMap<V>.EntryIterator i = new EntryIterator();
/* 1700:     */    
/* 1701:1701 */    s.defaultWriteObject();
/* 1702:1702 */    while (n-- != 0) {
/* 1703:1703 */      Entry<V> e = i.nextEntry();
/* 1704:1704 */      s.writeChar(e.key);
/* 1705:1705 */      s.writeObject(e.value);
/* 1706:     */    }
/* 1707:     */  }
/* 1708:     */  
/* 1714:     */  private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/* 1715:     */    throws IOException, ClassNotFoundException
/* 1716:     */  {
/* 1717:1717 */    if (n == 1) {
/* 1718:1718 */      Entry<V> top = new Entry(s.readChar(), s.readObject());
/* 1719:1719 */      top.pred(pred);
/* 1720:1720 */      top.succ(succ);
/* 1721:1721 */      return top;
/* 1722:     */    }
/* 1723:1723 */    if (n == 2)
/* 1724:     */    {
/* 1726:1726 */      Entry<V> top = new Entry(s.readChar(), s.readObject());
/* 1727:1727 */      top.right(new Entry(s.readChar(), s.readObject()));
/* 1728:1728 */      top.right.pred(top);
/* 1729:1729 */      top.balance(1);
/* 1730:1730 */      top.pred(pred);
/* 1731:1731 */      top.right.succ(succ);
/* 1732:1732 */      return top;
/* 1733:     */    }
/* 1734:     */    
/* 1735:1735 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1736:1736 */    Entry<V> top = new Entry();
/* 1737:1737 */    top.left(readTree(s, leftN, pred, top));
/* 1738:1738 */    top.key = s.readChar();
/* 1739:1739 */    top.value = s.readObject();
/* 1740:1740 */    top.right(readTree(s, rightN, top, succ));
/* 1741:1741 */    if (n == (n & -n)) top.balance(1);
/* 1742:1742 */    return top;
/* 1743:     */  }
/* 1744:     */  
/* 1745:1745 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1746:     */    
/* 1748:1748 */    setActualComparator();
/* 1749:1749 */    allocatePaths();
/* 1750:1750 */    if (this.count != 0) {
/* 1751:1751 */      this.tree = readTree(s, this.count, null, null);
/* 1752:     */      
/* 1753:1753 */      Entry<V> e = this.tree;
/* 1754:1754 */      while (e.left() != null) e = e.left();
/* 1755:1755 */      this.firstEntry = e;
/* 1756:1756 */      e = this.tree;
/* 1757:1757 */      while (e.right() != null) e = e.right();
/* 1758:1758 */      this.lastEntry = e;
/* 1759:     */    }
/* 1760:     */  }
/* 1761:     */  
/* 1762:     */  private static int checkTree(Entry e) {
/* 1763:1763 */    return 0;
/* 1764:     */  }
/* 1765:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ReferenceAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */