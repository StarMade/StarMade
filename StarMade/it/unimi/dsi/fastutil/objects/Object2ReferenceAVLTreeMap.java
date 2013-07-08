/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import java.io.IOException;
/*    4:     */import java.io.ObjectInputStream;
/*    5:     */import java.io.ObjectOutputStream;
/*    6:     */import java.io.Serializable;
/*    7:     */import java.util.Comparator;
/*    8:     */import java.util.Iterator;
/*    9:     */import java.util.Map;
/*   10:     */import java.util.Map.Entry;
/*   11:     */import java.util.NoSuchElementException;
/*   12:     */import java.util.SortedMap;
/*   13:     */
/*   70:     */public class Object2ReferenceAVLTreeMap<K, V>
/*   71:     */  extends AbstractObject2ReferenceSortedMap<K, V>
/*   72:     */  implements Serializable, Cloneable
/*   73:     */{
/*   74:     */  protected transient Entry<K, V> tree;
/*   75:     */  protected int count;
/*   76:     */  protected transient Entry<K, V> firstEntry;
/*   77:     */  protected transient Entry<K, V> lastEntry;
/*   78:     */  protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
/*   79:     */  protected volatile transient ObjectSortedSet<K> keys;
/*   80:     */  protected volatile transient ReferenceCollection<V> values;
/*   81:     */  protected transient boolean modified;
/*   82:     */  protected Comparator<? super K> storedComparator;
/*   83:     */  protected transient Comparator<? super K> actualComparator;
/*   84:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   85:     */  private static final boolean ASSERTS = false;
/*   86:     */  private transient boolean[] dirPath;
/*   87:     */  
/*   88:     */  public Object2ReferenceAVLTreeMap()
/*   89:     */  {
/*   90:  90 */    allocatePaths();
/*   91:     */    
/*   95:  95 */    this.tree = null;
/*   96:  96 */    this.count = 0;
/*   97:     */  }
/*   98:     */  
/*  108:     */  private void setActualComparator()
/*  109:     */  {
/*  110: 110 */    this.actualComparator = this.storedComparator;
/*  111:     */  }
/*  112:     */  
/*  115:     */  public Object2ReferenceAVLTreeMap(Comparator<? super K> c)
/*  116:     */  {
/*  117: 117 */    this();
/*  118: 118 */    this.storedComparator = c;
/*  119: 119 */    setActualComparator();
/*  120:     */  }
/*  121:     */  
/*  124:     */  public Object2ReferenceAVLTreeMap(Map<? extends K, ? extends V> m)
/*  125:     */  {
/*  126: 126 */    this();
/*  127: 127 */    putAll(m);
/*  128:     */  }
/*  129:     */  
/*  132:     */  public Object2ReferenceAVLTreeMap(SortedMap<K, V> m)
/*  133:     */  {
/*  134: 134 */    this(m.comparator());
/*  135: 135 */    putAll(m);
/*  136:     */  }
/*  137:     */  
/*  140:     */  public Object2ReferenceAVLTreeMap(Object2ReferenceMap<? extends K, ? extends V> m)
/*  141:     */  {
/*  142: 142 */    this();
/*  143: 143 */    putAll(m);
/*  144:     */  }
/*  145:     */  
/*  148:     */  public Object2ReferenceAVLTreeMap(Object2ReferenceSortedMap<K, V> m)
/*  149:     */  {
/*  150: 150 */    this(m.comparator());
/*  151: 151 */    putAll(m);
/*  152:     */  }
/*  153:     */  
/*  159:     */  public Object2ReferenceAVLTreeMap(K[] k, V[] v, Comparator<? super K> c)
/*  160:     */  {
/*  161: 161 */    this(c);
/*  162: 162 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  163: 163 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  164:     */    }
/*  165:     */  }
/*  166:     */  
/*  170:     */  public Object2ReferenceAVLTreeMap(K[] k, V[] v)
/*  171:     */  {
/*  172: 172 */    this(k, v, null);
/*  173:     */  }
/*  174:     */  
/*  193:     */  final int compare(K k1, K k2)
/*  194:     */  {
/*  195: 195 */    return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*  196:     */  }
/*  197:     */  
/*  201:     */  final Entry<K, V> findKey(K k)
/*  202:     */  {
/*  203: 203 */    Entry<K, V> e = this.tree;
/*  204:     */    int cmp;
/*  205: 205 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  206: 206 */    return e;
/*  207:     */  }
/*  208:     */  
/*  213:     */  final Entry<K, V> locateKey(K k)
/*  214:     */  {
/*  215: 215 */    Entry<K, V> e = this.tree;Entry<K, V> last = this.tree;
/*  216: 216 */    int cmp = 0;
/*  217: 217 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  218: 218 */      last = e;
/*  219: 219 */      e = cmp < 0 ? e.left() : e.right();
/*  220:     */    }
/*  221: 221 */    return cmp == 0 ? e : last;
/*  222:     */  }
/*  223:     */  
/*  225:     */  private void allocatePaths()
/*  226:     */  {
/*  227: 227 */    this.dirPath = new boolean[48];
/*  228:     */  }
/*  229:     */  
/*  230:     */  public V put(K k, V v)
/*  231:     */  {
/*  232: 232 */    this.modified = false;
/*  233: 233 */    if (this.tree == null) {
/*  234: 234 */      this.count += 1;
/*  235: 235 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  236: 236 */      this.modified = true;
/*  237:     */    }
/*  238:     */    else {
/*  239: 239 */      Entry<K, V> p = this.tree;Entry<K, V> q = null;Entry<K, V> y = this.tree;Entry<K, V> z = null;Entry<K, V> e = null;Entry<K, V> w = null;
/*  240: 240 */      int i = 0;
/*  241:     */      for (;;) { int cmp;
/*  242: 242 */        if ((cmp = compare(k, p.key)) == 0) {
/*  243: 243 */          V oldValue = p.value;
/*  244: 244 */          p.value = v;
/*  245: 245 */          return oldValue;
/*  246:     */        }
/*  247: 247 */        if (p.balance() != 0) {
/*  248: 248 */          i = 0;
/*  249: 249 */          z = q;
/*  250: 250 */          y = p;
/*  251:     */        }
/*  252: 252 */        if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  253: 253 */          if (p.succ()) {
/*  254: 254 */            this.count += 1;
/*  255: 255 */            e = new Entry(k, v);
/*  256: 256 */            this.modified = true;
/*  257: 257 */            if (p.right == null) this.lastEntry = e;
/*  258: 258 */            e.left = p;
/*  259: 259 */            e.right = p.right;
/*  260: 260 */            p.right(e);
/*  261: 261 */            break;
/*  262:     */          }
/*  263: 263 */          q = p;
/*  264: 264 */          p = p.right;
/*  265:     */        }
/*  266:     */        else {
/*  267: 267 */          if (p.pred()) {
/*  268: 268 */            this.count += 1;
/*  269: 269 */            e = new Entry(k, v);
/*  270: 270 */            this.modified = true;
/*  271: 271 */            if (p.left == null) this.firstEntry = e;
/*  272: 272 */            e.right = p;
/*  273: 273 */            e.left = p.left;
/*  274: 274 */            p.left(e);
/*  275: 275 */            break;
/*  276:     */          }
/*  277: 277 */          q = p;
/*  278: 278 */          p = p.left;
/*  279:     */        }
/*  280:     */      }
/*  281: 281 */      p = y;
/*  282: 282 */      i = 0;
/*  283: 283 */      while (p != e) {
/*  284: 284 */        if (this.dirPath[i] != 0) p.incBalance(); else
/*  285: 285 */          p.decBalance();
/*  286: 286 */        p = this.dirPath[(i++)] != 0 ? p.right : p.left;
/*  287:     */      }
/*  288: 288 */      if (y.balance() == -2) {
/*  289: 289 */        Entry<K, V> x = y.left;
/*  290: 290 */        if (x.balance() == -1) {
/*  291: 291 */          w = x;
/*  292: 292 */          if (x.succ()) {
/*  293: 293 */            x.succ(false);
/*  294: 294 */            y.pred(x);
/*  295:     */          } else {
/*  296: 296 */            y.left = x.right; }
/*  297: 297 */          x.right = y;
/*  298: 298 */          x.balance(0);
/*  299: 299 */          y.balance(0);
/*  300:     */        }
/*  301:     */        else
/*  302:     */        {
/*  303: 303 */          w = x.right;
/*  304: 304 */          x.right = w.left;
/*  305: 305 */          w.left = x;
/*  306: 306 */          y.left = w.right;
/*  307: 307 */          w.right = y;
/*  308: 308 */          if (w.balance() == -1) {
/*  309: 309 */            x.balance(0);
/*  310: 310 */            y.balance(1);
/*  311:     */          }
/*  312: 312 */          else if (w.balance() == 0) {
/*  313: 313 */            x.balance(0);
/*  314: 314 */            y.balance(0);
/*  315:     */          }
/*  316:     */          else {
/*  317: 317 */            x.balance(-1);
/*  318: 318 */            y.balance(0);
/*  319:     */          }
/*  320: 320 */          w.balance(0);
/*  321: 321 */          if (w.pred()) {
/*  322: 322 */            x.succ(w);
/*  323: 323 */            w.pred(false);
/*  324:     */          }
/*  325: 325 */          if (w.succ()) {
/*  326: 326 */            y.pred(w);
/*  327: 327 */            w.succ(false);
/*  328:     */          }
/*  329:     */        }
/*  330:     */      }
/*  331: 331 */      else if (y.balance() == 2) {
/*  332: 332 */        Entry<K, V> x = y.right;
/*  333: 333 */        if (x.balance() == 1) {
/*  334: 334 */          w = x;
/*  335: 335 */          if (x.pred()) {
/*  336: 336 */            x.pred(false);
/*  337: 337 */            y.succ(x);
/*  338:     */          } else {
/*  339: 339 */            y.right = x.left; }
/*  340: 340 */          x.left = y;
/*  341: 341 */          x.balance(0);
/*  342: 342 */          y.balance(0);
/*  343:     */        }
/*  344:     */        else
/*  345:     */        {
/*  346: 346 */          w = x.left;
/*  347: 347 */          x.left = w.right;
/*  348: 348 */          w.right = x;
/*  349: 349 */          y.right = w.left;
/*  350: 350 */          w.left = y;
/*  351: 351 */          if (w.balance() == 1) {
/*  352: 352 */            x.balance(0);
/*  353: 353 */            y.balance(-1);
/*  354:     */          }
/*  355: 355 */          else if (w.balance() == 0) {
/*  356: 356 */            x.balance(0);
/*  357: 357 */            y.balance(0);
/*  358:     */          }
/*  359:     */          else {
/*  360: 360 */            x.balance(1);
/*  361: 361 */            y.balance(0);
/*  362:     */          }
/*  363: 363 */          w.balance(0);
/*  364: 364 */          if (w.pred()) {
/*  365: 365 */            y.succ(w);
/*  366: 366 */            w.pred(false);
/*  367:     */          }
/*  368: 368 */          if (w.succ()) {
/*  369: 369 */            x.pred(w);
/*  370: 370 */            w.succ(false);
/*  371:     */          }
/*  372:     */        }
/*  373:     */      } else {
/*  374: 374 */        return this.defRetValue; }
/*  375: 375 */      if (z == null) { this.tree = w;
/*  376:     */      }
/*  377: 377 */      else if (z.left == y) z.left = w; else {
/*  378: 378 */        z.right = w;
/*  379:     */      }
/*  380:     */    }
/*  381:     */    
/*  382: 382 */    return this.defRetValue;
/*  383:     */  }
/*  384:     */  
/*  388:     */  private Entry<K, V> parent(Entry<K, V> e)
/*  389:     */  {
/*  390: 390 */    if (e == this.tree) return null;
/*  391:     */    Entry<K, V> y;
/*  392: 392 */    Entry<K, V> x = y = e;
/*  393:     */    for (;;) {
/*  394: 394 */      if (y.succ()) {
/*  395: 395 */        Entry<K, V> p = y.right;
/*  396: 396 */        if ((p == null) || (p.left != e)) {
/*  397: 397 */          while (!x.pred()) x = x.left;
/*  398: 398 */          p = x.left;
/*  399:     */        }
/*  400: 400 */        return p;
/*  401:     */      }
/*  402: 402 */      if (x.pred()) {
/*  403: 403 */        Entry<K, V> p = x.left;
/*  404: 404 */        if ((p == null) || (p.right != e)) {
/*  405: 405 */          while (!y.succ()) y = y.right;
/*  406: 406 */          p = y.right;
/*  407:     */        }
/*  408: 408 */        return p;
/*  409:     */      }
/*  410: 410 */      x = x.left;
/*  411: 411 */      y = y.right;
/*  412:     */    }
/*  413:     */  }
/*  414:     */  
/*  416:     */  public V remove(Object k)
/*  417:     */  {
/*  418: 418 */    this.modified = false;
/*  419: 419 */    if (this.tree == null) { return this.defRetValue;
/*  420:     */    }
/*  421: 421 */    Entry<K, V> p = this.tree;Entry<K, V> q = null;
/*  422: 422 */    boolean dir = false;
/*  423: 423 */    K kk = k;
/*  424:     */    int cmp;
/*  425: 425 */    while ((cmp = compare(kk, p.key)) != 0) {
/*  426: 426 */      if ((dir = cmp > 0 ? 1 : 0) != 0) {
/*  427: 427 */        q = p;
/*  428: 428 */        if ((p = p.right()) == null) return this.defRetValue;
/*  429:     */      }
/*  430:     */      else {
/*  431: 431 */        q = p;
/*  432: 432 */        if ((p = p.left()) == null) return this.defRetValue;
/*  433:     */      }
/*  434:     */    }
/*  435: 435 */    if (p.left == null) this.firstEntry = p.next();
/*  436: 436 */    if (p.right == null) this.lastEntry = p.prev();
/*  437: 437 */    if (p.succ()) {
/*  438: 438 */      if (p.pred()) {
/*  439: 439 */        if (q != null) {
/*  440: 440 */          if (dir) q.succ(p.right); else
/*  441: 441 */            q.pred(p.left);
/*  442:     */        } else {
/*  443: 443 */          this.tree = (dir ? p.right : p.left);
/*  444:     */        }
/*  445:     */      } else {
/*  446: 446 */        p.prev().right = p.right;
/*  447: 447 */        if (q != null) {
/*  448: 448 */          if (dir) q.right = p.left; else
/*  449: 449 */            q.left = p.left;
/*  450:     */        } else {
/*  451: 451 */          this.tree = p.left;
/*  452:     */        }
/*  453:     */      }
/*  454:     */    } else {
/*  455: 455 */      Entry<K, V> r = p.right;
/*  456: 456 */      if (r.pred()) {
/*  457: 457 */        r.left = p.left;
/*  458: 458 */        r.pred(p.pred());
/*  459: 459 */        if (!r.pred()) r.prev().right = r;
/*  460: 460 */        if (q != null) {
/*  461: 461 */          if (dir) q.right = r; else
/*  462: 462 */            q.left = r;
/*  463:     */        } else
/*  464: 464 */          this.tree = r;
/*  465: 465 */        r.balance(p.balance());
/*  466: 466 */        q = r;
/*  467: 467 */        dir = true;
/*  468:     */      }
/*  469:     */      else {
/*  470:     */        Entry<K, V> s;
/*  471:     */        for (;;) {
/*  472: 472 */          s = r.left;
/*  473: 473 */          if (s.pred()) break;
/*  474: 474 */          r = s;
/*  475:     */        }
/*  476: 476 */        if (s.succ()) r.pred(s); else
/*  477: 477 */          r.left = s.right;
/*  478: 478 */        s.left = p.left;
/*  479: 479 */        if (!p.pred()) {
/*  480: 480 */          p.prev().right = s;
/*  481: 481 */          s.pred(false);
/*  482:     */        }
/*  483: 483 */        s.right = p.right;
/*  484: 484 */        s.succ(false);
/*  485: 485 */        if (q != null) {
/*  486: 486 */          if (dir) q.right = s; else
/*  487: 487 */            q.left = s;
/*  488:     */        } else
/*  489: 489 */          this.tree = s;
/*  490: 490 */        s.balance(p.balance());
/*  491: 491 */        q = r;
/*  492: 492 */        dir = false;
/*  493:     */      }
/*  494:     */    }
/*  495:     */    
/*  496: 496 */    while (q != null) {
/*  497: 497 */      Entry<K, V> y = q;
/*  498: 498 */      q = parent(y);
/*  499: 499 */      if (!dir) {
/*  500: 500 */        dir = (q != null) && (q.left != y);
/*  501: 501 */        y.incBalance();
/*  502: 502 */        if (y.balance() == 1) break;
/*  503: 503 */        if (y.balance() == 2) {
/*  504: 504 */          Entry<K, V> x = y.right;
/*  505:     */          
/*  506: 506 */          if (x.balance() == -1)
/*  507:     */          {
/*  509: 509 */            Entry<K, V> w = x.left;
/*  510: 510 */            x.left = w.right;
/*  511: 511 */            w.right = x;
/*  512: 512 */            y.right = w.left;
/*  513: 513 */            w.left = y;
/*  514: 514 */            if (w.balance() == 1) {
/*  515: 515 */              x.balance(0);
/*  516: 516 */              y.balance(-1);
/*  517:     */            }
/*  518: 518 */            else if (w.balance() == 0) {
/*  519: 519 */              x.balance(0);
/*  520: 520 */              y.balance(0);
/*  521:     */            }
/*  522:     */            else
/*  523:     */            {
/*  524: 524 */              x.balance(1);
/*  525: 525 */              y.balance(0);
/*  526:     */            }
/*  527: 527 */            w.balance(0);
/*  528: 528 */            if (w.pred()) {
/*  529: 529 */              y.succ(w);
/*  530: 530 */              w.pred(false);
/*  531:     */            }
/*  532: 532 */            if (w.succ()) {
/*  533: 533 */              x.pred(w);
/*  534: 534 */              w.succ(false);
/*  535:     */            }
/*  536: 536 */            if (q != null) {
/*  537: 537 */              if (dir) q.right = w; else
/*  538: 538 */                q.left = w;
/*  539:     */            } else {
/*  540: 540 */              this.tree = w;
/*  541:     */            }
/*  542:     */          } else {
/*  543: 543 */            if (q != null) {
/*  544: 544 */              if (dir) q.right = x; else
/*  545: 545 */                q.left = x;
/*  546:     */            } else
/*  547: 547 */              this.tree = x;
/*  548: 548 */            if (x.balance() == 0) {
/*  549: 549 */              y.right = x.left;
/*  550: 550 */              x.left = y;
/*  551: 551 */              x.balance(-1);
/*  552: 552 */              y.balance(1);
/*  553: 553 */              break;
/*  554:     */            }
/*  555:     */            
/*  556: 556 */            if (x.pred()) {
/*  557: 557 */              y.succ(true);
/*  558: 558 */              x.pred(false);
/*  559:     */            } else {
/*  560: 560 */              y.right = x.left; }
/*  561: 561 */            x.left = y;
/*  562: 562 */            y.balance(0);
/*  563: 563 */            x.balance(0);
/*  564:     */          }
/*  565:     */        }
/*  566:     */      }
/*  567:     */      else {
/*  568: 568 */        dir = (q != null) && (q.left != y);
/*  569: 569 */        y.decBalance();
/*  570: 570 */        if (y.balance() == -1) break;
/*  571: 571 */        if (y.balance() == -2) {
/*  572: 572 */          Entry<K, V> x = y.left;
/*  573:     */          
/*  574: 574 */          if (x.balance() == 1)
/*  575:     */          {
/*  577: 577 */            Entry<K, V> w = x.right;
/*  578: 578 */            x.right = w.left;
/*  579: 579 */            w.left = x;
/*  580: 580 */            y.left = w.right;
/*  581: 581 */            w.right = y;
/*  582: 582 */            if (w.balance() == -1) {
/*  583: 583 */              x.balance(0);
/*  584: 584 */              y.balance(1);
/*  585:     */            }
/*  586: 586 */            else if (w.balance() == 0) {
/*  587: 587 */              x.balance(0);
/*  588: 588 */              y.balance(0);
/*  589:     */            }
/*  590:     */            else
/*  591:     */            {
/*  592: 592 */              x.balance(-1);
/*  593: 593 */              y.balance(0);
/*  594:     */            }
/*  595: 595 */            w.balance(0);
/*  596: 596 */            if (w.pred()) {
/*  597: 597 */              x.succ(w);
/*  598: 598 */              w.pred(false);
/*  599:     */            }
/*  600: 600 */            if (w.succ()) {
/*  601: 601 */              y.pred(w);
/*  602: 602 */              w.succ(false);
/*  603:     */            }
/*  604: 604 */            if (q != null) {
/*  605: 605 */              if (dir) q.right = w; else
/*  606: 606 */                q.left = w;
/*  607:     */            } else {
/*  608: 608 */              this.tree = w;
/*  609:     */            }
/*  610:     */          } else {
/*  611: 611 */            if (q != null) {
/*  612: 612 */              if (dir) q.right = x; else
/*  613: 613 */                q.left = x;
/*  614:     */            } else
/*  615: 615 */              this.tree = x;
/*  616: 616 */            if (x.balance() == 0) {
/*  617: 617 */              y.left = x.right;
/*  618: 618 */              x.right = y;
/*  619: 619 */              x.balance(1);
/*  620: 620 */              y.balance(-1);
/*  621: 621 */              break;
/*  622:     */            }
/*  623:     */            
/*  624: 624 */            if (x.succ()) {
/*  625: 625 */              y.pred(true);
/*  626: 626 */              x.succ(false);
/*  627:     */            } else {
/*  628: 628 */              y.left = x.right; }
/*  629: 629 */            x.right = y;
/*  630: 630 */            y.balance(0);
/*  631: 631 */            x.balance(0);
/*  632:     */          }
/*  633:     */        }
/*  634:     */      }
/*  635:     */    }
/*  636: 636 */    this.modified = true;
/*  637: 637 */    this.count -= 1;
/*  638:     */    
/*  639: 639 */    return p.value;
/*  640:     */  }
/*  641:     */  
/*  642: 642 */  public boolean containsValue(Object v) { Object2ReferenceAVLTreeMap<K, V>.ValueIterator i = new ValueIterator(null);
/*  643:     */    
/*  644: 644 */    int j = this.count;
/*  645: 645 */    while (j-- != 0) {
/*  646: 646 */      V ev = i.next();
/*  647: 647 */      if (ev == v) return true;
/*  648:     */    }
/*  649: 649 */    return false;
/*  650:     */  }
/*  651:     */  
/*  652: 652 */  public void clear() { this.count = 0;
/*  653: 653 */    this.tree = null;
/*  654: 654 */    this.entries = null;
/*  655: 655 */    this.values = null;
/*  656: 656 */    this.keys = null;
/*  657: 657 */    this.firstEntry = (this.lastEntry = null);
/*  658:     */  }
/*  659:     */  
/*  662:     */  private static final class Entry<K, V>
/*  663:     */    implements Cloneable, Object2ReferenceMap.Entry<K, V>
/*  664:     */  {
/*  665:     */    private static final int SUCC_MASK = -2147483648;
/*  666:     */    
/*  668:     */    private static final int PRED_MASK = 1073741824;
/*  669:     */    
/*  671:     */    private static final int BALANCE_MASK = 255;
/*  672:     */    
/*  673:     */    K key;
/*  674:     */    
/*  675:     */    V value;
/*  676:     */    
/*  677:     */    Entry<K, V> left;
/*  678:     */    
/*  679:     */    Entry<K, V> right;
/*  680:     */    
/*  681:     */    int info;
/*  682:     */    
/*  684:     */    Entry() {}
/*  685:     */    
/*  687:     */    Entry(K k, V v)
/*  688:     */    {
/*  689: 689 */      this.key = k;
/*  690: 690 */      this.value = v;
/*  691: 691 */      this.info = -1073741824;
/*  692:     */    }
/*  693:     */    
/*  697:     */    Entry<K, V> left()
/*  698:     */    {
/*  699: 699 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  700:     */    }
/*  701:     */    
/*  705:     */    Entry<K, V> right()
/*  706:     */    {
/*  707: 707 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  708:     */    }
/*  709:     */    
/*  711:     */    boolean pred()
/*  712:     */    {
/*  713: 713 */      return (this.info & 0x40000000) != 0;
/*  714:     */    }
/*  715:     */    
/*  717:     */    boolean succ()
/*  718:     */    {
/*  719: 719 */      return (this.info & 0x80000000) != 0;
/*  720:     */    }
/*  721:     */    
/*  723:     */    void pred(boolean pred)
/*  724:     */    {
/*  725: 725 */      if (pred) this.info |= 1073741824; else {
/*  726: 726 */        this.info &= -1073741825;
/*  727:     */      }
/*  728:     */    }
/*  729:     */    
/*  730:     */    void succ(boolean succ)
/*  731:     */    {
/*  732: 732 */      if (succ) this.info |= -2147483648; else {
/*  733: 733 */        this.info &= 2147483647;
/*  734:     */      }
/*  735:     */    }
/*  736:     */    
/*  737:     */    void pred(Entry<K, V> pred)
/*  738:     */    {
/*  739: 739 */      this.info |= 1073741824;
/*  740: 740 */      this.left = pred;
/*  741:     */    }
/*  742:     */    
/*  744:     */    void succ(Entry<K, V> succ)
/*  745:     */    {
/*  746: 746 */      this.info |= -2147483648;
/*  747: 747 */      this.right = succ;
/*  748:     */    }
/*  749:     */    
/*  751:     */    void left(Entry<K, V> left)
/*  752:     */    {
/*  753: 753 */      this.info &= -1073741825;
/*  754: 754 */      this.left = left;
/*  755:     */    }
/*  756:     */    
/*  758:     */    void right(Entry<K, V> right)
/*  759:     */    {
/*  760: 760 */      this.info &= 2147483647;
/*  761: 761 */      this.right = right;
/*  762:     */    }
/*  763:     */    
/*  765:     */    int balance()
/*  766:     */    {
/*  767: 767 */      return (byte)this.info;
/*  768:     */    }
/*  769:     */    
/*  771:     */    void balance(int level)
/*  772:     */    {
/*  773: 773 */      this.info &= -256;
/*  774: 774 */      this.info |= level & 0xFF;
/*  775:     */    }
/*  776:     */    
/*  777:     */    void incBalance() {
/*  778: 778 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  779:     */    }
/*  780:     */    
/*  781:     */    protected void decBalance() {
/*  782: 782 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*  783:     */    }
/*  784:     */    
/*  787:     */    Entry<K, V> next()
/*  788:     */    {
/*  789: 789 */      Entry<K, V> next = this.right;
/*  790: 790 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  791: 791 */      return next;
/*  792:     */    }
/*  793:     */    
/*  796:     */    Entry<K, V> prev()
/*  797:     */    {
/*  798: 798 */      Entry<K, V> prev = this.left;
/*  799: 799 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  800: 800 */      return prev;
/*  801:     */    }
/*  802:     */    
/*  803: 803 */    public K getKey() { return this.key; }
/*  804:     */    
/*  806: 806 */    public V getValue() { return this.value; }
/*  807:     */    
/*  808:     */    public V setValue(V value) {
/*  809: 809 */      V oldValue = this.value;
/*  810: 810 */      this.value = value;
/*  811: 811 */      return oldValue;
/*  812:     */    }
/*  813:     */    
/*  814:     */    public Entry<K, V> clone() {
/*  815:     */      Entry<K, V> c;
/*  816:     */      try {
/*  817: 817 */        c = (Entry)super.clone();
/*  818:     */      }
/*  819:     */      catch (CloneNotSupportedException cantHappen) {
/*  820: 820 */        throw new InternalError();
/*  821:     */      }
/*  822: 822 */      c.key = this.key;
/*  823: 823 */      c.value = this.value;
/*  824: 824 */      c.info = this.info;
/*  825: 825 */      return c;
/*  826:     */    }
/*  827:     */    
/*  828:     */    public boolean equals(Object o) {
/*  829: 829 */      if (!(o instanceof Map.Entry)) return false;
/*  830: 830 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  831: 831 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == e.getValue());
/*  832:     */    }
/*  833:     */    
/*  834: 834 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value)); }
/*  835:     */    
/*  836:     */    public String toString() {
/*  837: 837 */      return this.key + "=>" + this.value;
/*  838:     */    }
/*  839:     */  }
/*  840:     */  
/*  871:     */  public boolean containsKey(Object k)
/*  872:     */  {
/*  873: 873 */    return findKey(k) != null;
/*  874:     */  }
/*  875:     */  
/*  876: 876 */  public int size() { return this.count; }
/*  877:     */  
/*  878:     */  public boolean isEmpty() {
/*  879: 879 */    return this.count == 0;
/*  880:     */  }
/*  881:     */  
/*  882:     */  public V get(Object k) {
/*  883: 883 */    Entry<K, V> e = findKey(k);
/*  884: 884 */    return e == null ? this.defRetValue : e.value;
/*  885:     */  }
/*  886:     */  
/*  887: 887 */  public K firstKey() { if (this.tree == null) throw new NoSuchElementException();
/*  888: 888 */    return this.firstEntry.key;
/*  889:     */  }
/*  890:     */  
/*  891: 891 */  public K lastKey() { if (this.tree == null) throw new NoSuchElementException();
/*  892: 892 */    return this.lastEntry.key;
/*  893:     */  }
/*  894:     */  
/*  897:     */  private class TreeIterator
/*  898:     */  {
/*  899:     */    Object2ReferenceAVLTreeMap.Entry<K, V> prev;
/*  900:     */    
/*  902:     */    Object2ReferenceAVLTreeMap.Entry<K, V> next;
/*  903:     */    
/*  904:     */    Object2ReferenceAVLTreeMap.Entry<K, V> curr;
/*  905:     */    
/*  906: 906 */    int index = 0;
/*  907:     */    
/*  908: 908 */    TreeIterator() { this.next = Object2ReferenceAVLTreeMap.this.firstEntry; }
/*  909:     */    
/*  910:     */    TreeIterator() {
/*  911: 911 */      if ((this.next = Object2ReferenceAVLTreeMap.this.locateKey(k)) != null)
/*  912: 912 */        if (Object2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/*  913: 913 */          this.prev = this.next;
/*  914: 914 */          this.next = this.next.next();
/*  915:     */        } else {
/*  916: 916 */          this.prev = this.next.prev();
/*  917:     */        } }
/*  918:     */    
/*  919: 919 */    public boolean hasNext() { return this.next != null; }
/*  920: 920 */    public boolean hasPrevious() { return this.prev != null; }
/*  921:     */    
/*  922: 922 */    void updateNext() { this.next = this.next.next(); }
/*  923:     */    
/*  924:     */    Object2ReferenceAVLTreeMap.Entry<K, V> nextEntry() {
/*  925: 925 */      if (!hasNext()) throw new NoSuchElementException();
/*  926: 926 */      this.curr = (this.prev = this.next);
/*  927: 927 */      this.index += 1;
/*  928: 928 */      updateNext();
/*  929: 929 */      return this.curr;
/*  930:     */    }
/*  931:     */    
/*  932: 932 */    void updatePrevious() { this.prev = this.prev.prev(); }
/*  933:     */    
/*  934:     */    Object2ReferenceAVLTreeMap.Entry<K, V> previousEntry() {
/*  935: 935 */      if (!hasPrevious()) throw new NoSuchElementException();
/*  936: 936 */      this.curr = (this.next = this.prev);
/*  937: 937 */      this.index -= 1;
/*  938: 938 */      updatePrevious();
/*  939: 939 */      return this.curr;
/*  940:     */    }
/*  941:     */    
/*  942: 942 */    public int nextIndex() { return this.index; }
/*  943:     */    
/*  945: 945 */    public int previousIndex() { return this.index - 1; }
/*  946:     */    
/*  947:     */    public void remove() {
/*  948: 948 */      if (this.curr == null) { throw new IllegalStateException();
/*  949:     */      }
/*  950:     */      
/*  951: 951 */      if (this.curr == this.prev) this.index -= 1;
/*  952: 952 */      this.next = (this.prev = this.curr);
/*  953: 953 */      updatePrevious();
/*  954: 954 */      updateNext();
/*  955: 955 */      Object2ReferenceAVLTreeMap.this.remove(this.curr.key);
/*  956: 956 */      this.curr = null;
/*  957:     */    }
/*  958:     */    
/*  959: 959 */    public int skip(int n) { int i = n;
/*  960: 960 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  961: 961 */      return n - i - 1;
/*  962:     */    }
/*  963:     */    
/*  964: 964 */    public int back(int n) { int i = n;
/*  965: 965 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  966: 966 */      return n - i - 1;
/*  967:     */    }
/*  968:     */  }
/*  969:     */  
/*  970:     */  private class EntryIterator
/*  971:     */    extends Object2ReferenceAVLTreeMap<K, V>.TreeIterator
/*  972:     */    implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*  973:     */  {
/*  974: 974 */    EntryIterator() { super(); }
/*  975:     */    
/*  976: 976 */    EntryIterator() { super(k); }
/*  977:     */    
/*  978: 978 */    public Object2ReferenceMap.Entry<K, V> next() { return nextEntry(); }
/*  979: 979 */    public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); }
/*  980: 980 */    public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  981: 981 */    public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  982:     */  }
/*  983:     */  
/*  984: 984 */  public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  985: 985 */        final Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator = new Comparator() {
/*  986:     */          public int compare(Object2ReferenceMap.Entry<K, V> x, Object2ReferenceMap.Entry<K, V> y) {
/*  987: 987 */            return Object2ReferenceAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*  988:     */          }
/*  989:     */        };
/*  990:     */        
/*  991: 991 */        public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return this.comparator; }
/*  992:     */        
/*  993:     */        public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/*  994: 994 */          return new Object2ReferenceAVLTreeMap.EntryIterator(Object2ReferenceAVLTreeMap.this);
/*  995:     */        }
/*  996:     */        
/*  997: 997 */        public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) { return new Object2ReferenceAVLTreeMap.EntryIterator(Object2ReferenceAVLTreeMap.this, from.getKey()); }
/*  998:     */        
/*  999:     */        public boolean contains(Object o)
/* 1000:     */        {
/* 1001:1001 */          if (!(o instanceof Map.Entry)) return false;
/* 1002:1002 */          Map.Entry<K, V> e = (Map.Entry)o;
/* 1003:1003 */          Object2ReferenceAVLTreeMap.Entry<K, V> f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1004:1004 */          return e.equals(f);
/* 1005:     */        }
/* 1006:     */        
/* 1007:     */        public boolean remove(Object o) {
/* 1008:1008 */          if (!(o instanceof Map.Entry)) return false;
/* 1009:1009 */          Map.Entry<K, V> e = (Map.Entry)o;
/* 1010:1010 */          Object2ReferenceAVLTreeMap.Entry<K, V> f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1011:1011 */          if (f != null) Object2ReferenceAVLTreeMap.this.remove(f.key);
/* 1012:1012 */          return f != null; }
/* 1013:     */        
/* 1014:1014 */        public int size() { return Object2ReferenceAVLTreeMap.this.count; }
/* 1015:1015 */        public void clear() { Object2ReferenceAVLTreeMap.this.clear(); }
/* 1016:1016 */        public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceAVLTreeMap.this.firstEntry; }
/* 1017:1017 */        public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceAVLTreeMap.this.lastEntry; }
/* 1018:1018 */        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); }
/* 1019:1019 */        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.this.headMap(to.getKey()).object2ReferenceEntrySet(); }
/* 1020:1020 */        public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceAVLTreeMap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/* 1021:     */      };
/* 1022:1022 */    return this.entries;
/* 1023:     */  }
/* 1024:     */  
/* 1027:     */  private final class KeyIterator
/* 1028:     */    extends Object2ReferenceAVLTreeMap<K, V>.TreeIterator
/* 1029:     */    implements ObjectListIterator<K>
/* 1030:     */  {
/* 1031:1031 */    public KeyIterator() { super(); }
/* 1032:1032 */    public KeyIterator() { super(k); }
/* 1033:1033 */    public K next() { return nextEntry().key; }
/* 1034:1034 */    public K previous() { return previousEntry().key; }
/* 1035:1035 */    public void set(K k) { throw new UnsupportedOperationException(); }
/* 1036:1036 */    public void add(K k) { throw new UnsupportedOperationException(); }
/* 1037:     */  }
/* 1038:     */  
/* 1039:1039 */  private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1040:1040 */    public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceAVLTreeMap.KeyIterator(Object2ReferenceAVLTreeMap.this); }
/* 1041:1041 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceAVLTreeMap.KeyIterator(Object2ReferenceAVLTreeMap.this, from); }
/* 1042:     */  }
/* 1043:     */  
/* 1050:     */  public ObjectSortedSet<K> keySet()
/* 1051:     */  {
/* 1052:1052 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1053:1053 */    return this.keys;
/* 1054:     */  }
/* 1055:     */  
/* 1057:     */  private final class ValueIterator
/* 1058:     */    extends Object2ReferenceAVLTreeMap<K, V>.TreeIterator
/* 1059:     */    implements ObjectListIterator<V>
/* 1060:     */  {
/* 1061:1061 */    private ValueIterator() { super(); }
/* 1062:1062 */    public V next() { return nextEntry().value; }
/* 1063:1063 */    public V previous() { return previousEntry().value; }
/* 1064:1064 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1065:1065 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1066:     */  }
/* 1067:     */  
/* 1074:     */  public ReferenceCollection<V> values()
/* 1075:     */  {
/* 1076:1076 */    if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1077:     */        public ObjectIterator<V> iterator() {
/* 1078:1078 */          return new Object2ReferenceAVLTreeMap.ValueIterator(Object2ReferenceAVLTreeMap.this, null);
/* 1079:     */        }
/* 1080:     */        
/* 1081:1081 */        public boolean contains(Object k) { return Object2ReferenceAVLTreeMap.this.containsValue(k); }
/* 1082:     */        
/* 1083:     */        public int size() {
/* 1084:1084 */          return Object2ReferenceAVLTreeMap.this.count;
/* 1085:     */        }
/* 1086:     */        
/* 1087:1087 */        public void clear() { Object2ReferenceAVLTreeMap.this.clear(); }
/* 1088:     */      };
/* 1089:     */    }
/* 1090:1090 */    return this.values;
/* 1091:     */  }
/* 1092:     */  
/* 1093:1093 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1094:     */  
/* 1095:     */  public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1096:1096 */    return new Submap(null, true, to, false);
/* 1097:     */  }
/* 1098:     */  
/* 1099:1099 */  public Object2ReferenceSortedMap<K, V> tailMap(K from) { return new Submap(from, false, null, true); }
/* 1100:     */  
/* 1101:     */  public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 1102:1102 */    return new Submap(from, false, to, false);
/* 1103:     */  }
/* 1104:     */  
/* 1108:     */  private final class Submap
/* 1109:     */    extends AbstractObject2ReferenceSortedMap<K, V>
/* 1110:     */    implements Serializable
/* 1111:     */  {
/* 1112:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1113:     */    
/* 1116:     */    K from;
/* 1117:     */    
/* 1119:     */    K to;
/* 1120:     */    
/* 1122:     */    boolean bottom;
/* 1123:     */    
/* 1125:     */    boolean top;
/* 1126:     */    
/* 1128:     */    protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
/* 1129:     */    
/* 1131:     */    protected volatile transient ObjectSortedSet<K> keys;
/* 1132:     */    
/* 1134:     */    protected volatile transient ReferenceCollection<V> values;
/* 1135:     */    
/* 1138:     */    public Submap(boolean from, K bottom, boolean to)
/* 1139:     */    {
/* 1140:1140 */      if ((!bottom) && (!top) && (Object2ReferenceAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1141:1141 */      this.from = from;
/* 1142:1142 */      this.bottom = bottom;
/* 1143:1143 */      this.to = to;
/* 1144:1144 */      this.top = top;
/* 1145:1145 */      this.defRetValue = Object2ReferenceAVLTreeMap.this.defRetValue;
/* 1146:     */    }
/* 1147:     */    
/* 1148:1148 */    public void clear() { Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1149:1149 */      while (i.hasNext()) {
/* 1150:1150 */        i.nextEntry();
/* 1151:1151 */        i.remove();
/* 1152:     */      }
/* 1153:     */    }
/* 1154:     */    
/* 1157:     */    final boolean in(K k)
/* 1158:     */    {
/* 1159:1159 */      return ((this.bottom) || (Object2ReferenceAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ReferenceAVLTreeMap.this.compare(k, this.to) < 0));
/* 1160:     */    }
/* 1161:     */    
/* 1162:     */    public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() {
/* 1163:1163 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1164:     */          public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/* 1165:1165 */            return new Object2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Object2ReferenceAVLTreeMap.Submap.this);
/* 1166:     */          }
/* 1167:     */          
/* 1168:1168 */          public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) { return new Object2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Object2ReferenceAVLTreeMap.Submap.this, from.getKey()); }
/* 1169:     */          
/* 1170:1170 */          public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return Object2ReferenceAVLTreeMap.this.entrySet().comparator(); }
/* 1171:     */          
/* 1172:     */          public boolean contains(Object o) {
/* 1173:1173 */            if (!(o instanceof Map.Entry)) return false;
/* 1174:1174 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1175:1175 */            Object2ReferenceAVLTreeMap.Entry<K, V> f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1176:1176 */            return (f != null) && (Object2ReferenceAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1177:     */          }
/* 1178:     */          
/* 1179:     */          public boolean remove(Object o) {
/* 1180:1180 */            if (!(o instanceof Map.Entry)) return false;
/* 1181:1181 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1182:1182 */            Object2ReferenceAVLTreeMap.Entry<K, V> f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1183:1183 */            if ((f != null) && (Object2ReferenceAVLTreeMap.Submap.this.in(f.key))) Object2ReferenceAVLTreeMap.Submap.this.remove(f.key);
/* 1184:1184 */            return f != null;
/* 1185:     */          }
/* 1186:     */          
/* 1187:1187 */          public int size() { int c = 0;
/* 1188:1188 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1189:1189 */            return c;
/* 1190:     */          }
/* 1191:     */          
/* 1192:1192 */          public boolean isEmpty() { return !new Object2ReferenceAVLTreeMap.Submap.SubmapIterator(Object2ReferenceAVLTreeMap.Submap.this).hasNext(); }
/* 1193:     */          
/* 1195:1195 */          public void clear() { Object2ReferenceAVLTreeMap.Submap.this.clear(); }
/* 1196:     */          
/* 1197:1197 */          public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceAVLTreeMap.Submap.this.firstEntry(); }
/* 1198:1198 */          public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceAVLTreeMap.Submap.this.lastEntry(); }
/* 1199:1199 */          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); }
/* 1200:1200 */          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.Submap.this.headMap(to.getKey()).object2ReferenceEntrySet(); }
/* 1201:1201 */          public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceAVLTreeMap.Submap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/* 1202:     */        };
/* 1203:1203 */      return this.entries; }
/* 1204:     */    
/* 1205:1205 */    private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet { private KeySet() { super(); }
/* 1206:1206 */      public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Object2ReferenceAVLTreeMap.Submap.this); }
/* 1207:1207 */      public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Object2ReferenceAVLTreeMap.Submap.this, from); }
/* 1208:     */    }
/* 1209:     */    
/* 1210:1210 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1211:1211 */      return this.keys;
/* 1212:     */    }
/* 1213:     */    
/* 1214:1214 */    public ReferenceCollection<V> values() { if (this.values == null) { this.values = new AbstractReferenceCollection() {
/* 1215:     */          public ObjectIterator<V> iterator() {
/* 1216:1216 */            return new Object2ReferenceAVLTreeMap.Submap.SubmapValueIterator(Object2ReferenceAVLTreeMap.Submap.this, null);
/* 1217:     */          }
/* 1218:     */          
/* 1219:1219 */          public boolean contains(Object k) { return Object2ReferenceAVLTreeMap.Submap.this.containsValue(k); }
/* 1220:     */          
/* 1221:     */          public int size() {
/* 1222:1222 */            return Object2ReferenceAVLTreeMap.Submap.this.size();
/* 1223:     */          }
/* 1224:     */          
/* 1225:1225 */          public void clear() { Object2ReferenceAVLTreeMap.Submap.this.clear(); }
/* 1226:     */        };
/* 1227:     */      }
/* 1228:1228 */      return this.values;
/* 1229:     */    }
/* 1230:     */    
/* 1232:1232 */    public boolean containsKey(Object k) { return (in(k)) && (Object2ReferenceAVLTreeMap.this.containsKey(k)); }
/* 1233:     */    
/* 1234:     */    public boolean containsValue(Object v) {
/* 1235:1235 */      Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1236:     */      
/* 1237:1237 */      while (i.hasNext()) {
/* 1238:1238 */        Object ev = i.nextEntry().value;
/* 1239:1239 */        if (ev == v) return true;
/* 1240:     */      }
/* 1241:1241 */      return false;
/* 1242:     */    }
/* 1243:     */    
/* 1244:     */    public V get(Object k)
/* 1245:     */    {
/* 1246:1246 */      K kk = k;
/* 1247:1247 */      Object2ReferenceAVLTreeMap.Entry<K, V> e; return (in(kk)) && ((e = Object2ReferenceAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1248:     */    }
/* 1249:     */    
/* 1250:1250 */    public V put(K k, V v) { Object2ReferenceAVLTreeMap.this.modified = false;
/* 1251:1251 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1252:1252 */      V oldValue = Object2ReferenceAVLTreeMap.this.put(k, v);
/* 1253:1253 */      return Object2ReferenceAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1254:     */    }
/* 1255:     */    
/* 1256:     */    public V remove(Object k) {
/* 1257:1257 */      Object2ReferenceAVLTreeMap.this.modified = false;
/* 1258:1258 */      if (!in(k)) return this.defRetValue;
/* 1259:1259 */      V oldValue = Object2ReferenceAVLTreeMap.this.remove(k);
/* 1260:1260 */      return Object2ReferenceAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1261:     */    }
/* 1262:     */    
/* 1263:1263 */    public int size() { Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1264:1264 */      int n = 0;
/* 1265:1265 */      while (i.hasNext()) {
/* 1266:1266 */        n++;
/* 1267:1267 */        i.nextEntry();
/* 1268:     */      }
/* 1269:1269 */      return n;
/* 1270:     */    }
/* 1271:     */    
/* 1272:1272 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1273:     */    
/* 1275:1275 */    public Comparator<? super K> comparator() { return Object2ReferenceAVLTreeMap.this.actualComparator; }
/* 1276:     */    
/* 1277:     */    public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1278:1278 */      if (this.top) return new Submap(Object2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1279:1279 */      return Object2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1280:     */    }
/* 1281:     */    
/* 1282:1282 */    public Object2ReferenceSortedMap<K, V> tailMap(K from) { if (this.bottom) return new Submap(Object2ReferenceAVLTreeMap.this, from, false, this.to, this.top);
/* 1283:1283 */      return Object2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ReferenceAVLTreeMap.this, from, false, this.to, this.top) : this;
/* 1284:     */    }
/* 1285:     */    
/* 1286:1286 */    public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { if ((this.top) && (this.bottom)) return new Submap(Object2ReferenceAVLTreeMap.this, from, false, to, false);
/* 1287:1287 */      if (!this.top) to = Object2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1288:1288 */      if (!this.bottom) from = Object2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1289:1289 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1290:1290 */      return new Submap(Object2ReferenceAVLTreeMap.this, from, false, to, false);
/* 1291:     */    }
/* 1292:     */    
/* 1295:     */    public Object2ReferenceAVLTreeMap.Entry<K, V> firstEntry()
/* 1296:     */    {
/* 1297:1297 */      if (Object2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1298:     */      Object2ReferenceAVLTreeMap.Entry<K, V> e;
/* 1299:     */      Object2ReferenceAVLTreeMap.Entry<K, V> e;
/* 1300:1300 */      if (this.bottom) { e = Object2ReferenceAVLTreeMap.this.firstEntry;
/* 1301:     */      } else {
/* 1302:1302 */        e = Object2ReferenceAVLTreeMap.this.locateKey(this.from);
/* 1303:     */        
/* 1304:1304 */        if (Object2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1305:     */        }
/* 1306:     */      }
/* 1307:1307 */      if ((e == null) || ((!this.top) && (Object2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1308:1308 */      return e;
/* 1309:     */    }
/* 1310:     */    
/* 1313:     */    public Object2ReferenceAVLTreeMap.Entry<K, V> lastEntry()
/* 1314:     */    {
/* 1315:1315 */      if (Object2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1316:     */      Object2ReferenceAVLTreeMap.Entry<K, V> e;
/* 1317:     */      Object2ReferenceAVLTreeMap.Entry<K, V> e;
/* 1318:1318 */      if (this.top) { e = Object2ReferenceAVLTreeMap.this.lastEntry;
/* 1319:     */      } else {
/* 1320:1320 */        e = Object2ReferenceAVLTreeMap.this.locateKey(this.to);
/* 1321:     */        
/* 1322:1322 */        if (Object2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1323:     */        }
/* 1324:     */      }
/* 1325:1325 */      if ((e == null) || ((!this.bottom) && (Object2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1326:1326 */      return e;
/* 1327:     */    }
/* 1328:     */    
/* 1329:1329 */    public K firstKey() { Object2ReferenceAVLTreeMap.Entry<K, V> e = firstEntry();
/* 1330:1330 */      if (e == null) throw new NoSuchElementException();
/* 1331:1331 */      return e.key;
/* 1332:     */    }
/* 1333:     */    
/* 1334:1334 */    public K lastKey() { Object2ReferenceAVLTreeMap.Entry<K, V> e = lastEntry();
/* 1335:1335 */      if (e == null) throw new NoSuchElementException();
/* 1336:1336 */      return e.key;
/* 1337:     */    }
/* 1338:     */    
/* 1341:     */    private class SubmapIterator
/* 1342:     */      extends Object2ReferenceAVLTreeMap.TreeIterator
/* 1343:     */    {
/* 1344:     */      SubmapIterator()
/* 1345:     */      {
/* 1346:1346 */        super();
/* 1347:1347 */        this.next = Object2ReferenceAVLTreeMap.Submap.this.firstEntry();
/* 1348:     */      }
/* 1349:     */      
/* 1350:1350 */      SubmapIterator() { this();
/* 1351:1351 */        if (this.next != null)
/* 1352:1352 */          if ((!Object2ReferenceAVLTreeMap.Submap.this.bottom) && (Object2ReferenceAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1353:1353 */          } else if ((!Object2ReferenceAVLTreeMap.Submap.this.top) && (Object2ReferenceAVLTreeMap.this.compare(k, (this.prev = Object2ReferenceAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1354:     */          } else {
/* 1355:1355 */            this.next = Object2ReferenceAVLTreeMap.this.locateKey(k);
/* 1356:1356 */            if (Object2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1357:1357 */              this.prev = this.next;
/* 1358:1358 */              this.next = this.next.next();
/* 1359:     */            } else {
/* 1360:1360 */              this.prev = this.next.prev();
/* 1361:     */            }
/* 1362:     */          }
/* 1363:     */      }
/* 1364:     */      
/* 1365:1365 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1366:1366 */        if ((!Object2ReferenceAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ReferenceAVLTreeMap.this.compare(this.prev.key, Object2ReferenceAVLTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1367:     */      }
/* 1368:     */      
/* 1369:1369 */      void updateNext() { this.next = this.next.next();
/* 1370:1370 */        if ((!Object2ReferenceAVLTreeMap.Submap.this.top) && (this.next != null) && (Object2ReferenceAVLTreeMap.this.compare(this.next.key, Object2ReferenceAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1371:     */      }
/* 1372:     */    }
/* 1373:     */    
/* 1374:1374 */    private class SubmapEntryIterator extends Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>> { SubmapEntryIterator() { super(); }
/* 1375:     */      
/* 1376:1376 */      SubmapEntryIterator() { super(k); }
/* 1377:     */      
/* 1378:1378 */      public Object2ReferenceMap.Entry<K, V> next() { return nextEntry(); }
/* 1379:1379 */      public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); }
/* 1380:1380 */      public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1381:1381 */      public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1382:     */    }
/* 1383:     */    
/* 1388:     */    private final class SubmapKeyIterator
/* 1389:     */      extends Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator
/* 1390:     */      implements ObjectListIterator<K>
/* 1391:     */    {
/* 1392:1392 */      public SubmapKeyIterator() { super(); }
/* 1393:1393 */      public SubmapKeyIterator() { super(from); }
/* 1394:1394 */      public K next() { return nextEntry().key; }
/* 1395:1395 */      public K previous() { return previousEntry().key; }
/* 1396:1396 */      public void set(K k) { throw new UnsupportedOperationException(); }
/* 1397:1397 */      public void add(K k) { throw new UnsupportedOperationException(); }
/* 1398:     */    }
/* 1399:     */    
/* 1403:     */    private final class SubmapValueIterator
/* 1404:     */      extends Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator
/* 1405:     */      implements ObjectListIterator<V>
/* 1406:     */    {
/* 1407:1407 */      private SubmapValueIterator() { super(); }
/* 1408:1408 */      public V next() { return nextEntry().value; }
/* 1409:1409 */      public V previous() { return previousEntry().value; }
/* 1410:1410 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1411:1411 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1412:     */    }
/* 1413:     */  }
/* 1414:     */  
/* 1418:     */  public Object2ReferenceAVLTreeMap<K, V> clone()
/* 1419:     */  {
/* 1420:     */    Object2ReferenceAVLTreeMap<K, V> c;
/* 1421:     */    
/* 1423:     */    try
/* 1424:     */    {
/* 1425:1425 */      c = (Object2ReferenceAVLTreeMap)super.clone();
/* 1426:     */    }
/* 1427:     */    catch (CloneNotSupportedException cantHappen) {
/* 1428:1428 */      throw new InternalError();
/* 1429:     */    }
/* 1430:1430 */    c.keys = null;
/* 1431:1431 */    c.values = null;
/* 1432:1432 */    c.entries = null;
/* 1433:1433 */    c.allocatePaths();
/* 1434:1434 */    if (this.count != 0)
/* 1435:     */    {
/* 1436:1436 */      Entry<K, V> rp = new Entry();Entry<K, V> rq = new Entry();
/* 1437:1437 */      Entry<K, V> p = rp;
/* 1438:1438 */      rp.left(this.tree);
/* 1439:1439 */      Entry<K, V> q = rq;
/* 1440:1440 */      rq.pred(null);
/* 1441:     */      for (;;) {
/* 1442:1442 */        if (!p.pred()) {
/* 1443:1443 */          Entry<K, V> e = p.left.clone();
/* 1444:1444 */          e.pred(q.left);
/* 1445:1445 */          e.succ(q);
/* 1446:1446 */          q.left(e);
/* 1447:1447 */          p = p.left;
/* 1448:1448 */          q = q.left;
/* 1449:     */        }
/* 1450:     */        else {
/* 1451:1451 */          while (p.succ()) {
/* 1452:1452 */            p = p.right;
/* 1453:1453 */            if (p == null) {
/* 1454:1454 */              q.right = null;
/* 1455:1455 */              c.tree = rq.left;
/* 1456:1456 */              c.firstEntry = c.tree;
/* 1457:1457 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1458:1458 */              c.lastEntry = c.tree;
/* 1459:1459 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1460:1460 */              return c;
/* 1461:     */            }
/* 1462:1462 */            q = q.right;
/* 1463:     */          }
/* 1464:1464 */          p = p.right;
/* 1465:1465 */          q = q.right;
/* 1466:     */        }
/* 1467:1467 */        if (!p.succ()) {
/* 1468:1468 */          Entry<K, V> e = p.right.clone();
/* 1469:1469 */          e.succ(q.right);
/* 1470:1470 */          e.pred(q);
/* 1471:1471 */          q.right(e);
/* 1472:     */        }
/* 1473:     */      }
/* 1474:     */    }
/* 1475:1475 */    return c;
/* 1476:     */  }
/* 1477:     */  
/* 1478:1478 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1479:1479 */    Object2ReferenceAVLTreeMap<K, V>.EntryIterator i = new EntryIterator();
/* 1480:     */    
/* 1481:1481 */    s.defaultWriteObject();
/* 1482:1482 */    while (n-- != 0) {
/* 1483:1483 */      Entry<K, V> e = i.nextEntry();
/* 1484:1484 */      s.writeObject(e.key);
/* 1485:1485 */      s.writeObject(e.value);
/* 1486:     */    }
/* 1487:     */  }
/* 1488:     */  
/* 1494:     */  private Entry<K, V> readTree(ObjectInputStream s, int n, Entry<K, V> pred, Entry<K, V> succ)
/* 1495:     */    throws IOException, ClassNotFoundException
/* 1496:     */  {
/* 1497:1497 */    if (n == 1) {
/* 1498:1498 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1499:1499 */      top.pred(pred);
/* 1500:1500 */      top.succ(succ);
/* 1501:1501 */      return top;
/* 1502:     */    }
/* 1503:1503 */    if (n == 2)
/* 1504:     */    {
/* 1506:1506 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1507:1507 */      top.right(new Entry(s.readObject(), s.readObject()));
/* 1508:1508 */      top.right.pred(top);
/* 1509:1509 */      top.balance(1);
/* 1510:1510 */      top.pred(pred);
/* 1511:1511 */      top.right.succ(succ);
/* 1512:1512 */      return top;
/* 1513:     */    }
/* 1514:     */    
/* 1515:1515 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1516:1516 */    Entry<K, V> top = new Entry();
/* 1517:1517 */    top.left(readTree(s, leftN, pred, top));
/* 1518:1518 */    top.key = s.readObject();
/* 1519:1519 */    top.value = s.readObject();
/* 1520:1520 */    top.right(readTree(s, rightN, top, succ));
/* 1521:1521 */    if (n == (n & -n)) top.balance(1);
/* 1522:1522 */    return top;
/* 1523:     */  }
/* 1524:     */  
/* 1525:1525 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1526:     */    
/* 1528:1528 */    setActualComparator();
/* 1529:1529 */    allocatePaths();
/* 1530:1530 */    if (this.count != 0) {
/* 1531:1531 */      this.tree = readTree(s, this.count, null, null);
/* 1532:     */      
/* 1533:1533 */      Entry<K, V> e = this.tree;
/* 1534:1534 */      while (e.left() != null) e = e.left();
/* 1535:1535 */      this.firstEntry = e;
/* 1536:1536 */      e = this.tree;
/* 1537:1537 */      while (e.right() != null) e = e.right();
/* 1538:1538 */      this.lastEntry = e;
/* 1539:     */    }
/* 1540:     */  }
/* 1541:     */  
/* 1542:     */  private static int checkTree(Entry e) {
/* 1543:1543 */    return 0;
/* 1544:     */  }
/* 1545:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */