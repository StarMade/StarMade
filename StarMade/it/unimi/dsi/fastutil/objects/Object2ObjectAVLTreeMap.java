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
/*   70:     */public class Object2ObjectAVLTreeMap<K, V>
/*   71:     */  extends AbstractObject2ObjectSortedMap<K, V>
/*   72:     */  implements Serializable, Cloneable
/*   73:     */{
/*   74:     */  protected transient Entry<K, V> tree;
/*   75:     */  protected int count;
/*   76:     */  protected transient Entry<K, V> firstEntry;
/*   77:     */  protected transient Entry<K, V> lastEntry;
/*   78:     */  protected volatile transient ObjectSortedSet<Object2ObjectMap.Entry<K, V>> entries;
/*   79:     */  protected volatile transient ObjectSortedSet<K> keys;
/*   80:     */  protected volatile transient ObjectCollection<V> values;
/*   81:     */  protected transient boolean modified;
/*   82:     */  protected Comparator<? super K> storedComparator;
/*   83:     */  protected transient Comparator<? super K> actualComparator;
/*   84:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   85:     */  private static final boolean ASSERTS = false;
/*   86:     */  private transient boolean[] dirPath;
/*   87:     */  
/*   88:     */  public Object2ObjectAVLTreeMap()
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
/*  115:     */  public Object2ObjectAVLTreeMap(Comparator<? super K> c)
/*  116:     */  {
/*  117: 117 */    this();
/*  118: 118 */    this.storedComparator = c;
/*  119: 119 */    setActualComparator();
/*  120:     */  }
/*  121:     */  
/*  124:     */  public Object2ObjectAVLTreeMap(Map<? extends K, ? extends V> m)
/*  125:     */  {
/*  126: 126 */    this();
/*  127: 127 */    putAll(m);
/*  128:     */  }
/*  129:     */  
/*  132:     */  public Object2ObjectAVLTreeMap(SortedMap<K, V> m)
/*  133:     */  {
/*  134: 134 */    this(m.comparator());
/*  135: 135 */    putAll(m);
/*  136:     */  }
/*  137:     */  
/*  140:     */  public Object2ObjectAVLTreeMap(Object2ObjectMap<? extends K, ? extends V> m)
/*  141:     */  {
/*  142: 142 */    this();
/*  143: 143 */    putAll(m);
/*  144:     */  }
/*  145:     */  
/*  148:     */  public Object2ObjectAVLTreeMap(Object2ObjectSortedMap<K, V> m)
/*  149:     */  {
/*  150: 150 */    this(m.comparator());
/*  151: 151 */    putAll(m);
/*  152:     */  }
/*  153:     */  
/*  159:     */  public Object2ObjectAVLTreeMap(K[] k, V[] v, Comparator<? super K> c)
/*  160:     */  {
/*  161: 161 */    this(c);
/*  162: 162 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  163: 163 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  164:     */    }
/*  165:     */  }
/*  166:     */  
/*  170:     */  public Object2ObjectAVLTreeMap(K[] k, V[] v)
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
/*  642: 642 */  public boolean containsValue(Object v) { Object2ObjectAVLTreeMap<K, V>.ValueIterator i = new ValueIterator(null);
/*  643:     */    
/*  644: 644 */    int j = this.count;
/*  645: 645 */    for (; j-- != 0; 
/*  646:     */        
/*  647: 647 */        return true)
/*  648:     */    {
/*  649:     */      label16:
/*  650: 646 */      V ev = i.next();
/*  651: 647 */      if (ev == null ? v != null : !ev.equals(v)) break label16;
/*  652:     */    }
/*  653: 649 */    return false;
/*  654:     */  }
/*  655:     */  
/*  656: 652 */  public void clear() { this.count = 0;
/*  657: 653 */    this.tree = null;
/*  658: 654 */    this.entries = null;
/*  659: 655 */    this.values = null;
/*  660: 656 */    this.keys = null;
/*  661: 657 */    this.firstEntry = (this.lastEntry = null);
/*  662:     */  }
/*  663:     */  
/*  666:     */  private static final class Entry<K, V>
/*  667:     */    implements Cloneable, Object2ObjectMap.Entry<K, V>
/*  668:     */  {
/*  669:     */    private static final int SUCC_MASK = -2147483648;
/*  670:     */    
/*  672:     */    private static final int PRED_MASK = 1073741824;
/*  673:     */    
/*  675:     */    private static final int BALANCE_MASK = 255;
/*  676:     */    
/*  677:     */    K key;
/*  678:     */    
/*  679:     */    V value;
/*  680:     */    
/*  681:     */    Entry<K, V> left;
/*  682:     */    
/*  683:     */    Entry<K, V> right;
/*  684:     */    
/*  685:     */    int info;
/*  686:     */    
/*  688:     */    Entry() {}
/*  689:     */    
/*  691:     */    Entry(K k, V v)
/*  692:     */    {
/*  693: 689 */      this.key = k;
/*  694: 690 */      this.value = v;
/*  695: 691 */      this.info = -1073741824;
/*  696:     */    }
/*  697:     */    
/*  701:     */    Entry<K, V> left()
/*  702:     */    {
/*  703: 699 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  704:     */    }
/*  705:     */    
/*  709:     */    Entry<K, V> right()
/*  710:     */    {
/*  711: 707 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  712:     */    }
/*  713:     */    
/*  715:     */    boolean pred()
/*  716:     */    {
/*  717: 713 */      return (this.info & 0x40000000) != 0;
/*  718:     */    }
/*  719:     */    
/*  721:     */    boolean succ()
/*  722:     */    {
/*  723: 719 */      return (this.info & 0x80000000) != 0;
/*  724:     */    }
/*  725:     */    
/*  727:     */    void pred(boolean pred)
/*  728:     */    {
/*  729: 725 */      if (pred) this.info |= 1073741824; else {
/*  730: 726 */        this.info &= -1073741825;
/*  731:     */      }
/*  732:     */    }
/*  733:     */    
/*  734:     */    void succ(boolean succ)
/*  735:     */    {
/*  736: 732 */      if (succ) this.info |= -2147483648; else {
/*  737: 733 */        this.info &= 2147483647;
/*  738:     */      }
/*  739:     */    }
/*  740:     */    
/*  741:     */    void pred(Entry<K, V> pred)
/*  742:     */    {
/*  743: 739 */      this.info |= 1073741824;
/*  744: 740 */      this.left = pred;
/*  745:     */    }
/*  746:     */    
/*  748:     */    void succ(Entry<K, V> succ)
/*  749:     */    {
/*  750: 746 */      this.info |= -2147483648;
/*  751: 747 */      this.right = succ;
/*  752:     */    }
/*  753:     */    
/*  755:     */    void left(Entry<K, V> left)
/*  756:     */    {
/*  757: 753 */      this.info &= -1073741825;
/*  758: 754 */      this.left = left;
/*  759:     */    }
/*  760:     */    
/*  762:     */    void right(Entry<K, V> right)
/*  763:     */    {
/*  764: 760 */      this.info &= 2147483647;
/*  765: 761 */      this.right = right;
/*  766:     */    }
/*  767:     */    
/*  769:     */    int balance()
/*  770:     */    {
/*  771: 767 */      return (byte)this.info;
/*  772:     */    }
/*  773:     */    
/*  775:     */    void balance(int level)
/*  776:     */    {
/*  777: 773 */      this.info &= -256;
/*  778: 774 */      this.info |= level & 0xFF;
/*  779:     */    }
/*  780:     */    
/*  781:     */    void incBalance() {
/*  782: 778 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  783:     */    }
/*  784:     */    
/*  785:     */    protected void decBalance() {
/*  786: 782 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*  787:     */    }
/*  788:     */    
/*  791:     */    Entry<K, V> next()
/*  792:     */    {
/*  793: 789 */      Entry<K, V> next = this.right;
/*  794: 790 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  795: 791 */      return next;
/*  796:     */    }
/*  797:     */    
/*  800:     */    Entry<K, V> prev()
/*  801:     */    {
/*  802: 798 */      Entry<K, V> prev = this.left;
/*  803: 799 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  804: 800 */      return prev;
/*  805:     */    }
/*  806:     */    
/*  807: 803 */    public K getKey() { return this.key; }
/*  808:     */    
/*  810: 806 */    public V getValue() { return this.value; }
/*  811:     */    
/*  812:     */    public V setValue(V value) {
/*  813: 809 */      V oldValue = this.value;
/*  814: 810 */      this.value = value;
/*  815: 811 */      return oldValue;
/*  816:     */    }
/*  817:     */    
/*  818:     */    public Entry<K, V> clone() {
/*  819:     */      Entry<K, V> c;
/*  820:     */      try {
/*  821: 817 */        c = (Entry)super.clone();
/*  822:     */      }
/*  823:     */      catch (CloneNotSupportedException cantHappen) {
/*  824: 820 */        throw new InternalError();
/*  825:     */      }
/*  826: 822 */      c.key = this.key;
/*  827: 823 */      c.value = this.value;
/*  828: 824 */      c.info = this.info;
/*  829: 825 */      return c;
/*  830:     */    }
/*  831:     */    
/*  832:     */    public boolean equals(Object o) {
/*  833: 829 */      if (!(o instanceof Map.Entry)) return false;
/*  834: 830 */      Map.Entry<K, V> e = (Map.Entry)o;
/*  835: 831 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/*  836:     */    }
/*  837:     */    
/*  838: 834 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode()); }
/*  839:     */    
/*  840:     */    public String toString() {
/*  841: 837 */      return this.key + "=>" + this.value;
/*  842:     */    }
/*  843:     */  }
/*  844:     */  
/*  875:     */  public boolean containsKey(Object k)
/*  876:     */  {
/*  877: 873 */    return findKey(k) != null;
/*  878:     */  }
/*  879:     */  
/*  880: 876 */  public int size() { return this.count; }
/*  881:     */  
/*  882:     */  public boolean isEmpty() {
/*  883: 879 */    return this.count == 0;
/*  884:     */  }
/*  885:     */  
/*  886:     */  public V get(Object k) {
/*  887: 883 */    Entry<K, V> e = findKey(k);
/*  888: 884 */    return e == null ? this.defRetValue : e.value;
/*  889:     */  }
/*  890:     */  
/*  891: 887 */  public K firstKey() { if (this.tree == null) throw new NoSuchElementException();
/*  892: 888 */    return this.firstEntry.key;
/*  893:     */  }
/*  894:     */  
/*  895: 891 */  public K lastKey() { if (this.tree == null) throw new NoSuchElementException();
/*  896: 892 */    return this.lastEntry.key;
/*  897:     */  }
/*  898:     */  
/*  901:     */  private class TreeIterator
/*  902:     */  {
/*  903:     */    Object2ObjectAVLTreeMap.Entry<K, V> prev;
/*  904:     */    
/*  906:     */    Object2ObjectAVLTreeMap.Entry<K, V> next;
/*  907:     */    
/*  908:     */    Object2ObjectAVLTreeMap.Entry<K, V> curr;
/*  909:     */    
/*  910: 906 */    int index = 0;
/*  911:     */    
/*  912: 908 */    TreeIterator() { this.next = Object2ObjectAVLTreeMap.this.firstEntry; }
/*  913:     */    
/*  914:     */    TreeIterator() {
/*  915: 911 */      if ((this.next = Object2ObjectAVLTreeMap.this.locateKey(k)) != null)
/*  916: 912 */        if (Object2ObjectAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/*  917: 913 */          this.prev = this.next;
/*  918: 914 */          this.next = this.next.next();
/*  919:     */        } else {
/*  920: 916 */          this.prev = this.next.prev();
/*  921:     */        } }
/*  922:     */    
/*  923: 919 */    public boolean hasNext() { return this.next != null; }
/*  924: 920 */    public boolean hasPrevious() { return this.prev != null; }
/*  925:     */    
/*  926: 922 */    void updateNext() { this.next = this.next.next(); }
/*  927:     */    
/*  928:     */    Object2ObjectAVLTreeMap.Entry<K, V> nextEntry() {
/*  929: 925 */      if (!hasNext()) throw new NoSuchElementException();
/*  930: 926 */      this.curr = (this.prev = this.next);
/*  931: 927 */      this.index += 1;
/*  932: 928 */      updateNext();
/*  933: 929 */      return this.curr;
/*  934:     */    }
/*  935:     */    
/*  936: 932 */    void updatePrevious() { this.prev = this.prev.prev(); }
/*  937:     */    
/*  938:     */    Object2ObjectAVLTreeMap.Entry<K, V> previousEntry() {
/*  939: 935 */      if (!hasPrevious()) throw new NoSuchElementException();
/*  940: 936 */      this.curr = (this.next = this.prev);
/*  941: 937 */      this.index -= 1;
/*  942: 938 */      updatePrevious();
/*  943: 939 */      return this.curr;
/*  944:     */    }
/*  945:     */    
/*  946: 942 */    public int nextIndex() { return this.index; }
/*  947:     */    
/*  949: 945 */    public int previousIndex() { return this.index - 1; }
/*  950:     */    
/*  951:     */    public void remove() {
/*  952: 948 */      if (this.curr == null) { throw new IllegalStateException();
/*  953:     */      }
/*  954:     */      
/*  955: 951 */      if (this.curr == this.prev) this.index -= 1;
/*  956: 952 */      this.next = (this.prev = this.curr);
/*  957: 953 */      updatePrevious();
/*  958: 954 */      updateNext();
/*  959: 955 */      Object2ObjectAVLTreeMap.this.remove(this.curr.key);
/*  960: 956 */      this.curr = null;
/*  961:     */    }
/*  962:     */    
/*  963: 959 */    public int skip(int n) { int i = n;
/*  964: 960 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  965: 961 */      return n - i - 1;
/*  966:     */    }
/*  967:     */    
/*  968: 964 */    public int back(int n) { int i = n;
/*  969: 965 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  970: 966 */      return n - i - 1;
/*  971:     */    }
/*  972:     */  }
/*  973:     */  
/*  974:     */  private class EntryIterator
/*  975:     */    extends Object2ObjectAVLTreeMap<K, V>.TreeIterator
/*  976:     */    implements ObjectListIterator<Object2ObjectMap.Entry<K, V>>
/*  977:     */  {
/*  978: 974 */    EntryIterator() { super(); }
/*  979:     */    
/*  980: 976 */    EntryIterator() { super(k); }
/*  981:     */    
/*  982: 978 */    public Object2ObjectMap.Entry<K, V> next() { return nextEntry(); }
/*  983: 979 */    public Object2ObjectMap.Entry<K, V> previous() { return previousEntry(); }
/*  984: 980 */    public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  985: 981 */    public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*  986:     */  }
/*  987:     */  
/*  988: 984 */  public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  989: 985 */        final Comparator<? super Object2ObjectMap.Entry<K, V>> comparator = new Comparator() {
/*  990:     */          public int compare(Object2ObjectMap.Entry<K, V> x, Object2ObjectMap.Entry<K, V> y) {
/*  991: 987 */            return Object2ObjectAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*  992:     */          }
/*  993:     */        };
/*  994:     */        
/*  995: 991 */        public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return this.comparator; }
/*  996:     */        
/*  997:     */        public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator() {
/*  998: 994 */          return new Object2ObjectAVLTreeMap.EntryIterator(Object2ObjectAVLTreeMap.this);
/*  999:     */        }
/* 1000:     */        
/* 1001: 997 */        public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectAVLTreeMap.EntryIterator(Object2ObjectAVLTreeMap.this, from.getKey()); }
/* 1002:     */        
/* 1003:     */        public boolean contains(Object o)
/* 1004:     */        {
/* 1005:1001 */          if (!(o instanceof Map.Entry)) return false;
/* 1006:1002 */          Map.Entry<K, V> e = (Map.Entry)o;
/* 1007:1003 */          Object2ObjectAVLTreeMap.Entry<K, V> f = Object2ObjectAVLTreeMap.this.findKey(e.getKey());
/* 1008:1004 */          return e.equals(f);
/* 1009:     */        }
/* 1010:     */        
/* 1011:     */        public boolean remove(Object o) {
/* 1012:1008 */          if (!(o instanceof Map.Entry)) return false;
/* 1013:1009 */          Map.Entry<K, V> e = (Map.Entry)o;
/* 1014:1010 */          Object2ObjectAVLTreeMap.Entry<K, V> f = Object2ObjectAVLTreeMap.this.findKey(e.getKey());
/* 1015:1011 */          if (f != null) Object2ObjectAVLTreeMap.this.remove(f.key);
/* 1016:1012 */          return f != null; }
/* 1017:     */        
/* 1018:1014 */        public int size() { return Object2ObjectAVLTreeMap.this.count; }
/* 1019:1015 */        public void clear() { Object2ObjectAVLTreeMap.this.clear(); }
/* 1020:1016 */        public Object2ObjectMap.Entry<K, V> first() { return Object2ObjectAVLTreeMap.this.firstEntry; }
/* 1021:1017 */        public Object2ObjectMap.Entry<K, V> last() { return Object2ObjectAVLTreeMap.this.lastEntry; }
/* 1022:1018 */        public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> from, Object2ObjectMap.Entry<K, V> to) { return Object2ObjectAVLTreeMap.this.subMap(from.getKey(), to.getKey()).object2ObjectEntrySet(); }
/* 1023:1019 */        public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> to) { return Object2ObjectAVLTreeMap.this.headMap(to.getKey()).object2ObjectEntrySet(); }
/* 1024:1020 */        public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> from) { return Object2ObjectAVLTreeMap.this.tailMap(from.getKey()).object2ObjectEntrySet(); }
/* 1025:     */      };
/* 1026:1022 */    return this.entries;
/* 1027:     */  }
/* 1028:     */  
/* 1031:     */  private final class KeyIterator
/* 1032:     */    extends Object2ObjectAVLTreeMap<K, V>.TreeIterator
/* 1033:     */    implements ObjectListIterator<K>
/* 1034:     */  {
/* 1035:1031 */    public KeyIterator() { super(); }
/* 1036:1032 */    public KeyIterator() { super(k); }
/* 1037:1033 */    public K next() { return nextEntry().key; }
/* 1038:1034 */    public K previous() { return previousEntry().key; }
/* 1039:1035 */    public void set(K k) { throw new UnsupportedOperationException(); }
/* 1040:1036 */    public void add(K k) { throw new UnsupportedOperationException(); }
/* 1041:     */  }
/* 1042:     */  
/* 1043:1039 */  private class KeySet extends AbstractObject2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1044:1040 */    public ObjectBidirectionalIterator<K> iterator() { return new Object2ObjectAVLTreeMap.KeyIterator(Object2ObjectAVLTreeMap.this); }
/* 1045:1041 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ObjectAVLTreeMap.KeyIterator(Object2ObjectAVLTreeMap.this, from); }
/* 1046:     */  }
/* 1047:     */  
/* 1054:     */  public ObjectSortedSet<K> keySet()
/* 1055:     */  {
/* 1056:1052 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1057:1053 */    return this.keys;
/* 1058:     */  }
/* 1059:     */  
/* 1061:     */  private final class ValueIterator
/* 1062:     */    extends Object2ObjectAVLTreeMap<K, V>.TreeIterator
/* 1063:     */    implements ObjectListIterator<V>
/* 1064:     */  {
/* 1065:1061 */    private ValueIterator() { super(); }
/* 1066:1062 */    public V next() { return nextEntry().value; }
/* 1067:1063 */    public V previous() { return previousEntry().value; }
/* 1068:1064 */    public void set(V v) { throw new UnsupportedOperationException(); }
/* 1069:1065 */    public void add(V v) { throw new UnsupportedOperationException(); }
/* 1070:     */  }
/* 1071:     */  
/* 1078:     */  public ObjectCollection<V> values()
/* 1079:     */  {
/* 1080:1076 */    if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1081:     */        public ObjectIterator<V> iterator() {
/* 1082:1078 */          return new Object2ObjectAVLTreeMap.ValueIterator(Object2ObjectAVLTreeMap.this, null);
/* 1083:     */        }
/* 1084:     */        
/* 1085:1081 */        public boolean contains(Object k) { return Object2ObjectAVLTreeMap.this.containsValue(k); }
/* 1086:     */        
/* 1087:     */        public int size() {
/* 1088:1084 */          return Object2ObjectAVLTreeMap.this.count;
/* 1089:     */        }
/* 1090:     */        
/* 1091:1087 */        public void clear() { Object2ObjectAVLTreeMap.this.clear(); }
/* 1092:     */      };
/* 1093:     */    }
/* 1094:1090 */    return this.values;
/* 1095:     */  }
/* 1096:     */  
/* 1097:1093 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1098:     */  
/* 1099:     */  public Object2ObjectSortedMap<K, V> headMap(K to) {
/* 1100:1096 */    return new Submap(null, true, to, false);
/* 1101:     */  }
/* 1102:     */  
/* 1103:1099 */  public Object2ObjectSortedMap<K, V> tailMap(K from) { return new Submap(from, false, null, true); }
/* 1104:     */  
/* 1105:     */  public Object2ObjectSortedMap<K, V> subMap(K from, K to) {
/* 1106:1102 */    return new Submap(from, false, to, false);
/* 1107:     */  }
/* 1108:     */  
/* 1112:     */  private final class Submap
/* 1113:     */    extends AbstractObject2ObjectSortedMap<K, V>
/* 1114:     */    implements Serializable
/* 1115:     */  {
/* 1116:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1117:     */    
/* 1120:     */    K from;
/* 1121:     */    
/* 1123:     */    K to;
/* 1124:     */    
/* 1126:     */    boolean bottom;
/* 1127:     */    
/* 1129:     */    boolean top;
/* 1130:     */    
/* 1132:     */    protected volatile transient ObjectSortedSet<Object2ObjectMap.Entry<K, V>> entries;
/* 1133:     */    
/* 1135:     */    protected volatile transient ObjectSortedSet<K> keys;
/* 1136:     */    
/* 1138:     */    protected volatile transient ObjectCollection<V> values;
/* 1139:     */    
/* 1142:     */    public Submap(boolean from, K bottom, boolean to)
/* 1143:     */    {
/* 1144:1140 */      if ((!bottom) && (!top) && (Object2ObjectAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1145:1141 */      this.from = from;
/* 1146:1142 */      this.bottom = bottom;
/* 1147:1143 */      this.to = to;
/* 1148:1144 */      this.top = top;
/* 1149:1145 */      this.defRetValue = Object2ObjectAVLTreeMap.this.defRetValue;
/* 1150:     */    }
/* 1151:     */    
/* 1152:1148 */    public void clear() { Object2ObjectAVLTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1153:1149 */      while (i.hasNext()) {
/* 1154:1150 */        i.nextEntry();
/* 1155:1151 */        i.remove();
/* 1156:     */      }
/* 1157:     */    }
/* 1158:     */    
/* 1161:     */    final boolean in(K k)
/* 1162:     */    {
/* 1163:1159 */      return ((this.bottom) || (Object2ObjectAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ObjectAVLTreeMap.this.compare(k, this.to) < 0));
/* 1164:     */    }
/* 1165:     */    
/* 1166:     */    public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> object2ObjectEntrySet() {
/* 1167:1163 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1168:     */          public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator() {
/* 1169:1165 */            return new Object2ObjectAVLTreeMap.Submap.SubmapEntryIterator(Object2ObjectAVLTreeMap.Submap.this);
/* 1170:     */          }
/* 1171:     */          
/* 1172:1168 */          public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) { return new Object2ObjectAVLTreeMap.Submap.SubmapEntryIterator(Object2ObjectAVLTreeMap.Submap.this, from.getKey()); }
/* 1173:     */          
/* 1174:1170 */          public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return Object2ObjectAVLTreeMap.this.entrySet().comparator(); }
/* 1175:     */          
/* 1176:     */          public boolean contains(Object o) {
/* 1177:1173 */            if (!(o instanceof Map.Entry)) return false;
/* 1178:1174 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1179:1175 */            Object2ObjectAVLTreeMap.Entry<K, V> f = Object2ObjectAVLTreeMap.this.findKey(e.getKey());
/* 1180:1176 */            return (f != null) && (Object2ObjectAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1181:     */          }
/* 1182:     */          
/* 1183:     */          public boolean remove(Object o) {
/* 1184:1180 */            if (!(o instanceof Map.Entry)) return false;
/* 1185:1181 */            Map.Entry<K, V> e = (Map.Entry)o;
/* 1186:1182 */            Object2ObjectAVLTreeMap.Entry<K, V> f = Object2ObjectAVLTreeMap.this.findKey(e.getKey());
/* 1187:1183 */            if ((f != null) && (Object2ObjectAVLTreeMap.Submap.this.in(f.key))) Object2ObjectAVLTreeMap.Submap.this.remove(f.key);
/* 1188:1184 */            return f != null;
/* 1189:     */          }
/* 1190:     */          
/* 1191:1187 */          public int size() { int c = 0;
/* 1192:1188 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1193:1189 */            return c;
/* 1194:     */          }
/* 1195:     */          
/* 1196:1192 */          public boolean isEmpty() { return !new Object2ObjectAVLTreeMap.Submap.SubmapIterator(Object2ObjectAVLTreeMap.Submap.this).hasNext(); }
/* 1197:     */          
/* 1199:1195 */          public void clear() { Object2ObjectAVLTreeMap.Submap.this.clear(); }
/* 1200:     */          
/* 1201:1197 */          public Object2ObjectMap.Entry<K, V> first() { return Object2ObjectAVLTreeMap.Submap.this.firstEntry(); }
/* 1202:1198 */          public Object2ObjectMap.Entry<K, V> last() { return Object2ObjectAVLTreeMap.Submap.this.lastEntry(); }
/* 1203:1199 */          public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> from, Object2ObjectMap.Entry<K, V> to) { return Object2ObjectAVLTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ObjectEntrySet(); }
/* 1204:1200 */          public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> to) { return Object2ObjectAVLTreeMap.Submap.this.headMap(to.getKey()).object2ObjectEntrySet(); }
/* 1205:1201 */          public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> from) { return Object2ObjectAVLTreeMap.Submap.this.tailMap(from.getKey()).object2ObjectEntrySet(); }
/* 1206:     */        };
/* 1207:1203 */      return this.entries; }
/* 1208:     */    
/* 1209:1205 */    private class KeySet extends AbstractObject2ObjectSortedMap.KeySet { private KeySet() { super(); }
/* 1210:1206 */      public ObjectBidirectionalIterator<K> iterator() { return new Object2ObjectAVLTreeMap.Submap.SubmapKeyIterator(Object2ObjectAVLTreeMap.Submap.this); }
/* 1211:1207 */      public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ObjectAVLTreeMap.Submap.SubmapKeyIterator(Object2ObjectAVLTreeMap.Submap.this, from); }
/* 1212:     */    }
/* 1213:     */    
/* 1214:1210 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1215:1211 */      return this.keys;
/* 1216:     */    }
/* 1217:     */    
/* 1218:1214 */    public ObjectCollection<V> values() { if (this.values == null) { this.values = new AbstractObjectCollection() {
/* 1219:     */          public ObjectIterator<V> iterator() {
/* 1220:1216 */            return new Object2ObjectAVLTreeMap.Submap.SubmapValueIterator(Object2ObjectAVLTreeMap.Submap.this, null);
/* 1221:     */          }
/* 1222:     */          
/* 1223:1219 */          public boolean contains(Object k) { return Object2ObjectAVLTreeMap.Submap.this.containsValue(k); }
/* 1224:     */          
/* 1225:     */          public int size() {
/* 1226:1222 */            return Object2ObjectAVLTreeMap.Submap.this.size();
/* 1227:     */          }
/* 1228:     */          
/* 1229:1225 */          public void clear() { Object2ObjectAVLTreeMap.Submap.this.clear(); }
/* 1230:     */        };
/* 1231:     */      }
/* 1232:1228 */      return this.values;
/* 1233:     */    }
/* 1234:     */    
/* 1236:1232 */    public boolean containsKey(Object k) { return (in(k)) && (Object2ObjectAVLTreeMap.this.containsKey(k)); }
/* 1237:     */    
/* 1238:     */    public boolean containsValue(Object v) {
/* 1239:1235 */      Object2ObjectAVLTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1240:1237 */      for (; 
/* 1241:1237 */          i.hasNext(); 
/* 1242:     */          
/* 1243:1239 */          return true)
/* 1244:     */      {
/* 1245:     */        label9:
/* 1246:1238 */        Object ev = i.nextEntry().value;
/* 1247:1239 */        if (ev == null ? v != null : !ev.equals(v)) break label9;
/* 1248:     */      }
/* 1249:1241 */      return false;
/* 1250:     */    }
/* 1251:     */    
/* 1252:     */    public V get(Object k)
/* 1253:     */    {
/* 1254:1246 */      K kk = k;
/* 1255:1247 */      Object2ObjectAVLTreeMap.Entry<K, V> e; return (in(kk)) && ((e = Object2ObjectAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1256:     */    }
/* 1257:     */    
/* 1258:1250 */    public V put(K k, V v) { Object2ObjectAVLTreeMap.this.modified = false;
/* 1259:1251 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1260:1252 */      V oldValue = Object2ObjectAVLTreeMap.this.put(k, v);
/* 1261:1253 */      return Object2ObjectAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1262:     */    }
/* 1263:     */    
/* 1264:     */    public V remove(Object k) {
/* 1265:1257 */      Object2ObjectAVLTreeMap.this.modified = false;
/* 1266:1258 */      if (!in(k)) return this.defRetValue;
/* 1267:1259 */      V oldValue = Object2ObjectAVLTreeMap.this.remove(k);
/* 1268:1260 */      return Object2ObjectAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1269:     */    }
/* 1270:     */    
/* 1271:1263 */    public int size() { Object2ObjectAVLTreeMap<K, V>.Submap.SubmapIterator i = new SubmapIterator();
/* 1272:1264 */      int n = 0;
/* 1273:1265 */      while (i.hasNext()) {
/* 1274:1266 */        n++;
/* 1275:1267 */        i.nextEntry();
/* 1276:     */      }
/* 1277:1269 */      return n;
/* 1278:     */    }
/* 1279:     */    
/* 1280:1272 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1281:     */    
/* 1283:1275 */    public Comparator<? super K> comparator() { return Object2ObjectAVLTreeMap.this.actualComparator; }
/* 1284:     */    
/* 1285:     */    public Object2ObjectSortedMap<K, V> headMap(K to) {
/* 1286:1278 */      if (this.top) return new Submap(Object2ObjectAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1287:1279 */      return Object2ObjectAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ObjectAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1288:     */    }
/* 1289:     */    
/* 1290:1282 */    public Object2ObjectSortedMap<K, V> tailMap(K from) { if (this.bottom) return new Submap(Object2ObjectAVLTreeMap.this, from, false, this.to, this.top);
/* 1291:1283 */      return Object2ObjectAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ObjectAVLTreeMap.this, from, false, this.to, this.top) : this;
/* 1292:     */    }
/* 1293:     */    
/* 1294:1286 */    public Object2ObjectSortedMap<K, V> subMap(K from, K to) { if ((this.top) && (this.bottom)) return new Submap(Object2ObjectAVLTreeMap.this, from, false, to, false);
/* 1295:1287 */      if (!this.top) to = Object2ObjectAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1296:1288 */      if (!this.bottom) from = Object2ObjectAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1297:1289 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1298:1290 */      return new Submap(Object2ObjectAVLTreeMap.this, from, false, to, false);
/* 1299:     */    }
/* 1300:     */    
/* 1303:     */    public Object2ObjectAVLTreeMap.Entry<K, V> firstEntry()
/* 1304:     */    {
/* 1305:1297 */      if (Object2ObjectAVLTreeMap.this.tree == null) return null;
/* 1306:     */      Object2ObjectAVLTreeMap.Entry<K, V> e;
/* 1307:     */      Object2ObjectAVLTreeMap.Entry<K, V> e;
/* 1308:1300 */      if (this.bottom) { e = Object2ObjectAVLTreeMap.this.firstEntry;
/* 1309:     */      } else {
/* 1310:1302 */        e = Object2ObjectAVLTreeMap.this.locateKey(this.from);
/* 1311:     */        
/* 1312:1304 */        if (Object2ObjectAVLTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1313:     */        }
/* 1314:     */      }
/* 1315:1307 */      if ((e == null) || ((!this.top) && (Object2ObjectAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1316:1308 */      return e;
/* 1317:     */    }
/* 1318:     */    
/* 1321:     */    public Object2ObjectAVLTreeMap.Entry<K, V> lastEntry()
/* 1322:     */    {
/* 1323:1315 */      if (Object2ObjectAVLTreeMap.this.tree == null) return null;
/* 1324:     */      Object2ObjectAVLTreeMap.Entry<K, V> e;
/* 1325:     */      Object2ObjectAVLTreeMap.Entry<K, V> e;
/* 1326:1318 */      if (this.top) { e = Object2ObjectAVLTreeMap.this.lastEntry;
/* 1327:     */      } else {
/* 1328:1320 */        e = Object2ObjectAVLTreeMap.this.locateKey(this.to);
/* 1329:     */        
/* 1330:1322 */        if (Object2ObjectAVLTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1331:     */        }
/* 1332:     */      }
/* 1333:1325 */      if ((e == null) || ((!this.bottom) && (Object2ObjectAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1334:1326 */      return e;
/* 1335:     */    }
/* 1336:     */    
/* 1337:1329 */    public K firstKey() { Object2ObjectAVLTreeMap.Entry<K, V> e = firstEntry();
/* 1338:1330 */      if (e == null) throw new NoSuchElementException();
/* 1339:1331 */      return e.key;
/* 1340:     */    }
/* 1341:     */    
/* 1342:1334 */    public K lastKey() { Object2ObjectAVLTreeMap.Entry<K, V> e = lastEntry();
/* 1343:1335 */      if (e == null) throw new NoSuchElementException();
/* 1344:1336 */      return e.key;
/* 1345:     */    }
/* 1346:     */    
/* 1349:     */    private class SubmapIterator
/* 1350:     */      extends Object2ObjectAVLTreeMap.TreeIterator
/* 1351:     */    {
/* 1352:     */      SubmapIterator()
/* 1353:     */      {
/* 1354:1346 */        super();
/* 1355:1347 */        this.next = Object2ObjectAVLTreeMap.Submap.this.firstEntry();
/* 1356:     */      }
/* 1357:     */      
/* 1358:1350 */      SubmapIterator() { this();
/* 1359:1351 */        if (this.next != null)
/* 1360:1352 */          if ((!Object2ObjectAVLTreeMap.Submap.this.bottom) && (Object2ObjectAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1361:1353 */          } else if ((!Object2ObjectAVLTreeMap.Submap.this.top) && (Object2ObjectAVLTreeMap.this.compare(k, (this.prev = Object2ObjectAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1362:     */          } else {
/* 1363:1355 */            this.next = Object2ObjectAVLTreeMap.this.locateKey(k);
/* 1364:1356 */            if (Object2ObjectAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1365:1357 */              this.prev = this.next;
/* 1366:1358 */              this.next = this.next.next();
/* 1367:     */            } else {
/* 1368:1360 */              this.prev = this.next.prev();
/* 1369:     */            }
/* 1370:     */          }
/* 1371:     */      }
/* 1372:     */      
/* 1373:1365 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1374:1366 */        if ((!Object2ObjectAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ObjectAVLTreeMap.this.compare(this.prev.key, Object2ObjectAVLTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1375:     */      }
/* 1376:     */      
/* 1377:1369 */      void updateNext() { this.next = this.next.next();
/* 1378:1370 */        if ((!Object2ObjectAVLTreeMap.Submap.this.top) && (this.next != null) && (Object2ObjectAVLTreeMap.this.compare(this.next.key, Object2ObjectAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1379:     */      }
/* 1380:     */    }
/* 1381:     */    
/* 1382:1374 */    private class SubmapEntryIterator extends Object2ObjectAVLTreeMap<K, V>.Submap.SubmapIterator implements ObjectListIterator<Object2ObjectMap.Entry<K, V>> { SubmapEntryIterator() { super(); }
/* 1383:     */      
/* 1384:1376 */      SubmapEntryIterator() { super(k); }
/* 1385:     */      
/* 1386:1378 */      public Object2ObjectMap.Entry<K, V> next() { return nextEntry(); }
/* 1387:1379 */      public Object2ObjectMap.Entry<K, V> previous() { return previousEntry(); }
/* 1388:1380 */      public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1389:1381 */      public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/* 1390:     */    }
/* 1391:     */    
/* 1396:     */    private final class SubmapKeyIterator
/* 1397:     */      extends Object2ObjectAVLTreeMap<K, V>.Submap.SubmapIterator
/* 1398:     */      implements ObjectListIterator<K>
/* 1399:     */    {
/* 1400:1392 */      public SubmapKeyIterator() { super(); }
/* 1401:1393 */      public SubmapKeyIterator() { super(from); }
/* 1402:1394 */      public K next() { return nextEntry().key; }
/* 1403:1395 */      public K previous() { return previousEntry().key; }
/* 1404:1396 */      public void set(K k) { throw new UnsupportedOperationException(); }
/* 1405:1397 */      public void add(K k) { throw new UnsupportedOperationException(); }
/* 1406:     */    }
/* 1407:     */    
/* 1411:     */    private final class SubmapValueIterator
/* 1412:     */      extends Object2ObjectAVLTreeMap<K, V>.Submap.SubmapIterator
/* 1413:     */      implements ObjectListIterator<V>
/* 1414:     */    {
/* 1415:1407 */      private SubmapValueIterator() { super(); }
/* 1416:1408 */      public V next() { return nextEntry().value; }
/* 1417:1409 */      public V previous() { return previousEntry().value; }
/* 1418:1410 */      public void set(V v) { throw new UnsupportedOperationException(); }
/* 1419:1411 */      public void add(V v) { throw new UnsupportedOperationException(); }
/* 1420:     */    }
/* 1421:     */  }
/* 1422:     */  
/* 1426:     */  public Object2ObjectAVLTreeMap<K, V> clone()
/* 1427:     */  {
/* 1428:     */    Object2ObjectAVLTreeMap<K, V> c;
/* 1429:     */    
/* 1431:     */    try
/* 1432:     */    {
/* 1433:1425 */      c = (Object2ObjectAVLTreeMap)super.clone();
/* 1434:     */    }
/* 1435:     */    catch (CloneNotSupportedException cantHappen) {
/* 1436:1428 */      throw new InternalError();
/* 1437:     */    }
/* 1438:1430 */    c.keys = null;
/* 1439:1431 */    c.values = null;
/* 1440:1432 */    c.entries = null;
/* 1441:1433 */    c.allocatePaths();
/* 1442:1434 */    if (this.count != 0)
/* 1443:     */    {
/* 1444:1436 */      Entry<K, V> rp = new Entry();Entry<K, V> rq = new Entry();
/* 1445:1437 */      Entry<K, V> p = rp;
/* 1446:1438 */      rp.left(this.tree);
/* 1447:1439 */      Entry<K, V> q = rq;
/* 1448:1440 */      rq.pred(null);
/* 1449:     */      for (;;) {
/* 1450:1442 */        if (!p.pred()) {
/* 1451:1443 */          Entry<K, V> e = p.left.clone();
/* 1452:1444 */          e.pred(q.left);
/* 1453:1445 */          e.succ(q);
/* 1454:1446 */          q.left(e);
/* 1455:1447 */          p = p.left;
/* 1456:1448 */          q = q.left;
/* 1457:     */        }
/* 1458:     */        else {
/* 1459:1451 */          while (p.succ()) {
/* 1460:1452 */            p = p.right;
/* 1461:1453 */            if (p == null) {
/* 1462:1454 */              q.right = null;
/* 1463:1455 */              c.tree = rq.left;
/* 1464:1456 */              c.firstEntry = c.tree;
/* 1465:1457 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1466:1458 */              c.lastEntry = c.tree;
/* 1467:1459 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1468:1460 */              return c;
/* 1469:     */            }
/* 1470:1462 */            q = q.right;
/* 1471:     */          }
/* 1472:1464 */          p = p.right;
/* 1473:1465 */          q = q.right;
/* 1474:     */        }
/* 1475:1467 */        if (!p.succ()) {
/* 1476:1468 */          Entry<K, V> e = p.right.clone();
/* 1477:1469 */          e.succ(q.right);
/* 1478:1470 */          e.pred(q);
/* 1479:1471 */          q.right(e);
/* 1480:     */        }
/* 1481:     */      }
/* 1482:     */    }
/* 1483:1475 */    return c;
/* 1484:     */  }
/* 1485:     */  
/* 1486:1478 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1487:1479 */    Object2ObjectAVLTreeMap<K, V>.EntryIterator i = new EntryIterator();
/* 1488:     */    
/* 1489:1481 */    s.defaultWriteObject();
/* 1490:1482 */    while (n-- != 0) {
/* 1491:1483 */      Entry<K, V> e = i.nextEntry();
/* 1492:1484 */      s.writeObject(e.key);
/* 1493:1485 */      s.writeObject(e.value);
/* 1494:     */    }
/* 1495:     */  }
/* 1496:     */  
/* 1502:     */  private Entry<K, V> readTree(ObjectInputStream s, int n, Entry<K, V> pred, Entry<K, V> succ)
/* 1503:     */    throws IOException, ClassNotFoundException
/* 1504:     */  {
/* 1505:1497 */    if (n == 1) {
/* 1506:1498 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1507:1499 */      top.pred(pred);
/* 1508:1500 */      top.succ(succ);
/* 1509:1501 */      return top;
/* 1510:     */    }
/* 1511:1503 */    if (n == 2)
/* 1512:     */    {
/* 1514:1506 */      Entry<K, V> top = new Entry(s.readObject(), s.readObject());
/* 1515:1507 */      top.right(new Entry(s.readObject(), s.readObject()));
/* 1516:1508 */      top.right.pred(top);
/* 1517:1509 */      top.balance(1);
/* 1518:1510 */      top.pred(pred);
/* 1519:1511 */      top.right.succ(succ);
/* 1520:1512 */      return top;
/* 1521:     */    }
/* 1522:     */    
/* 1523:1515 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1524:1516 */    Entry<K, V> top = new Entry();
/* 1525:1517 */    top.left(readTree(s, leftN, pred, top));
/* 1526:1518 */    top.key = s.readObject();
/* 1527:1519 */    top.value = s.readObject();
/* 1528:1520 */    top.right(readTree(s, rightN, top, succ));
/* 1529:1521 */    if (n == (n & -n)) top.balance(1);
/* 1530:1522 */    return top;
/* 1531:     */  }
/* 1532:     */  
/* 1533:1525 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1534:     */    
/* 1536:1528 */    setActualComparator();
/* 1537:1529 */    allocatePaths();
/* 1538:1530 */    if (this.count != 0) {
/* 1539:1531 */      this.tree = readTree(s, this.count, null, null);
/* 1540:     */      
/* 1541:1533 */      Entry<K, V> e = this.tree;
/* 1542:1534 */      while (e.left() != null) e = e.left();
/* 1543:1535 */      this.firstEntry = e;
/* 1544:1536 */      e = this.tree;
/* 1545:1537 */      while (e.right() != null) e = e.right();
/* 1546:1538 */      this.lastEntry = e;
/* 1547:     */    }
/* 1548:     */  }
/* 1549:     */  
/* 1550:     */  private static int checkTree(Entry e) {
/* 1551:1543 */    return 0;
/* 1552:     */  }
/* 1553:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */