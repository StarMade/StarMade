/*    1:     */package it.unimi.dsi.fastutil.objects;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.bytes.AbstractByteCollection;
/*    4:     */import it.unimi.dsi.fastutil.bytes.ByteCollection;
/*    5:     */import it.unimi.dsi.fastutil.bytes.ByteIterator;
/*    6:     */import it.unimi.dsi.fastutil.bytes.ByteListIterator;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.ObjectInputStream;
/*    9:     */import java.io.ObjectOutputStream;
/*   10:     */import java.io.Serializable;
/*   11:     */import java.util.Comparator;
/*   12:     */import java.util.Iterator;
/*   13:     */import java.util.Map;
/*   14:     */import java.util.Map.Entry;
/*   15:     */import java.util.NoSuchElementException;
/*   16:     */import java.util.SortedMap;
/*   17:     */
/*   72:     */public class Object2ByteAVLTreeMap<K>
/*   73:     */  extends AbstractObject2ByteSortedMap<K>
/*   74:     */  implements Serializable, Cloneable
/*   75:     */{
/*   76:     */  protected transient Entry<K> tree;
/*   77:     */  protected int count;
/*   78:     */  protected transient Entry<K> firstEntry;
/*   79:     */  protected transient Entry<K> lastEntry;
/*   80:     */  protected volatile transient ObjectSortedSet<Object2ByteMap.Entry<K>> entries;
/*   81:     */  protected volatile transient ObjectSortedSet<K> keys;
/*   82:     */  protected volatile transient ByteCollection values;
/*   83:     */  protected transient boolean modified;
/*   84:     */  protected Comparator<? super K> storedComparator;
/*   85:     */  protected transient Comparator<? super K> actualComparator;
/*   86:     */  public static final long serialVersionUID = -7046029254386353129L;
/*   87:     */  private static final boolean ASSERTS = false;
/*   88:     */  private transient boolean[] dirPath;
/*   89:     */  
/*   90:     */  public Object2ByteAVLTreeMap()
/*   91:     */  {
/*   92:  92 */    allocatePaths();
/*   93:     */    
/*   97:  97 */    this.tree = null;
/*   98:  98 */    this.count = 0;
/*   99:     */  }
/*  100:     */  
/*  108:     */  private void setActualComparator()
/*  109:     */  {
/*  110: 110 */    this.actualComparator = this.storedComparator;
/*  111:     */  }
/*  112:     */  
/*  115:     */  public Object2ByteAVLTreeMap(Comparator<? super K> c)
/*  116:     */  {
/*  117: 117 */    this();
/*  118: 118 */    this.storedComparator = c;
/*  119: 119 */    setActualComparator();
/*  120:     */  }
/*  121:     */  
/*  124:     */  public Object2ByteAVLTreeMap(Map<? extends K, ? extends Byte> m)
/*  125:     */  {
/*  126: 126 */    this();
/*  127: 127 */    putAll(m);
/*  128:     */  }
/*  129:     */  
/*  132:     */  public Object2ByteAVLTreeMap(SortedMap<K, Byte> m)
/*  133:     */  {
/*  134: 134 */    this(m.comparator());
/*  135: 135 */    putAll(m);
/*  136:     */  }
/*  137:     */  
/*  140:     */  public Object2ByteAVLTreeMap(Object2ByteMap<? extends K> m)
/*  141:     */  {
/*  142: 142 */    this();
/*  143: 143 */    putAll(m);
/*  144:     */  }
/*  145:     */  
/*  148:     */  public Object2ByteAVLTreeMap(Object2ByteSortedMap<K> m)
/*  149:     */  {
/*  150: 150 */    this(m.comparator());
/*  151: 151 */    putAll(m);
/*  152:     */  }
/*  153:     */  
/*  159:     */  public Object2ByteAVLTreeMap(K[] k, byte[] v, Comparator<? super K> c)
/*  160:     */  {
/*  161: 161 */    this(c);
/*  162: 162 */    if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  163: 163 */    for (int i = 0; i < k.length; i++) { put(k[i], v[i]);
/*  164:     */    }
/*  165:     */  }
/*  166:     */  
/*  170:     */  public Object2ByteAVLTreeMap(K[] k, byte[] v)
/*  171:     */  {
/*  172: 172 */    this(k, v, null);
/*  173:     */  }
/*  174:     */  
/*  193:     */  final int compare(K k1, K k2)
/*  194:     */  {
/*  195: 195 */    return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*  196:     */  }
/*  197:     */  
/*  201:     */  final Entry<K> findKey(K k)
/*  202:     */  {
/*  203: 203 */    Entry<K> e = this.tree;
/*  204:     */    int cmp;
/*  205: 205 */    while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  206: 206 */    return e;
/*  207:     */  }
/*  208:     */  
/*  213:     */  final Entry<K> locateKey(K k)
/*  214:     */  {
/*  215: 215 */    Entry<K> e = this.tree;Entry<K> last = this.tree;
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
/*  230:     */  public byte put(K k, byte v)
/*  231:     */  {
/*  232: 232 */    this.modified = false;
/*  233: 233 */    if (this.tree == null) {
/*  234: 234 */      this.count += 1;
/*  235: 235 */      this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  236: 236 */      this.modified = true;
/*  237:     */    }
/*  238:     */    else {
/*  239: 239 */      Entry<K> p = this.tree;Entry<K> q = null;Entry<K> y = this.tree;Entry<K> z = null;Entry<K> e = null;Entry<K> w = null;
/*  240: 240 */      int i = 0;
/*  241:     */      for (;;) { int cmp;
/*  242: 242 */        if ((cmp = compare(k, p.key)) == 0) {
/*  243: 243 */          byte oldValue = p.value;
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
/*  289: 289 */        Entry<K> x = y.left;
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
/*  332: 332 */        Entry<K> x = y.right;
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
/*  388:     */  private Entry<K> parent(Entry<K> e)
/*  389:     */  {
/*  390: 390 */    if (e == this.tree) return null;
/*  391:     */    Entry<K> y;
/*  392: 392 */    Entry<K> x = y = e;
/*  393:     */    for (;;) {
/*  394: 394 */      if (y.succ()) {
/*  395: 395 */        Entry<K> p = y.right;
/*  396: 396 */        if ((p == null) || (p.left != e)) {
/*  397: 397 */          while (!x.pred()) x = x.left;
/*  398: 398 */          p = x.left;
/*  399:     */        }
/*  400: 400 */        return p;
/*  401:     */      }
/*  402: 402 */      if (x.pred()) {
/*  403: 403 */        Entry<K> p = x.left;
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
/*  416:     */  public byte removeByte(Object k)
/*  417:     */  {
/*  418: 418 */    this.modified = false;
/*  419: 419 */    if (this.tree == null) { return this.defRetValue;
/*  420:     */    }
/*  421: 421 */    Entry<K> p = this.tree;Entry<K> q = null;
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
/*  455: 455 */      Entry<K> r = p.right;
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
/*  470:     */        Entry<K> s;
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
/*  497: 497 */      Entry<K> y = q;
/*  498: 498 */      q = parent(y);
/*  499: 499 */      if (!dir) {
/*  500: 500 */        dir = (q != null) && (q.left != y);
/*  501: 501 */        y.incBalance();
/*  502: 502 */        if (y.balance() == 1) break;
/*  503: 503 */        if (y.balance() == 2) {
/*  504: 504 */          Entry<K> x = y.right;
/*  505:     */          
/*  506: 506 */          if (x.balance() == -1)
/*  507:     */          {
/*  509: 509 */            Entry<K> w = x.left;
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
/*  572: 572 */          Entry<K> x = y.left;
/*  573:     */          
/*  574: 574 */          if (x.balance() == 1)
/*  575:     */          {
/*  577: 577 */            Entry<K> w = x.right;
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
/*  642: 642 */  public Byte put(K ok, Byte ov) { byte oldValue = put(ok, ov.byteValue());
/*  643: 643 */    return this.modified ? null : Byte.valueOf(oldValue);
/*  644:     */  }
/*  645:     */  
/*  646: 646 */  public Byte remove(Object ok) { byte oldValue = removeByte(ok);
/*  647: 647 */    return this.modified ? Byte.valueOf(oldValue) : null;
/*  648:     */  }
/*  649:     */  
/*  650: 650 */  public boolean containsValue(byte v) { Object2ByteAVLTreeMap<K>.ValueIterator i = new ValueIterator(null);
/*  651:     */    
/*  652: 652 */    int j = this.count;
/*  653: 653 */    while (j-- != 0) {
/*  654: 654 */      byte ev = i.nextByte();
/*  655: 655 */      if (ev == v) return true;
/*  656:     */    }
/*  657: 657 */    return false;
/*  658:     */  }
/*  659:     */  
/*  660: 660 */  public void clear() { this.count = 0;
/*  661: 661 */    this.tree = null;
/*  662: 662 */    this.entries = null;
/*  663: 663 */    this.values = null;
/*  664: 664 */    this.keys = null;
/*  665: 665 */    this.firstEntry = (this.lastEntry = null);
/*  666:     */  }
/*  667:     */  
/*  670:     */  private static final class Entry<K>
/*  671:     */    implements Cloneable, Object2ByteMap.Entry<K>
/*  672:     */  {
/*  673:     */    private static final int SUCC_MASK = -2147483648;
/*  674:     */    
/*  676:     */    private static final int PRED_MASK = 1073741824;
/*  677:     */    
/*  679:     */    private static final int BALANCE_MASK = 255;
/*  680:     */    
/*  681:     */    K key;
/*  682:     */    
/*  683:     */    byte value;
/*  684:     */    
/*  685:     */    Entry<K> left;
/*  686:     */    
/*  687:     */    Entry<K> right;
/*  688:     */    
/*  689:     */    int info;
/*  690:     */    
/*  692:     */    Entry() {}
/*  693:     */    
/*  695:     */    Entry(K k, byte v)
/*  696:     */    {
/*  697: 697 */      this.key = k;
/*  698: 698 */      this.value = v;
/*  699: 699 */      this.info = -1073741824;
/*  700:     */    }
/*  701:     */    
/*  705:     */    Entry<K> left()
/*  706:     */    {
/*  707: 707 */      return (this.info & 0x40000000) != 0 ? null : this.left;
/*  708:     */    }
/*  709:     */    
/*  713:     */    Entry<K> right()
/*  714:     */    {
/*  715: 715 */      return (this.info & 0x80000000) != 0 ? null : this.right;
/*  716:     */    }
/*  717:     */    
/*  719:     */    boolean pred()
/*  720:     */    {
/*  721: 721 */      return (this.info & 0x40000000) != 0;
/*  722:     */    }
/*  723:     */    
/*  725:     */    boolean succ()
/*  726:     */    {
/*  727: 727 */      return (this.info & 0x80000000) != 0;
/*  728:     */    }
/*  729:     */    
/*  731:     */    void pred(boolean pred)
/*  732:     */    {
/*  733: 733 */      if (pred) this.info |= 1073741824; else {
/*  734: 734 */        this.info &= -1073741825;
/*  735:     */      }
/*  736:     */    }
/*  737:     */    
/*  738:     */    void succ(boolean succ)
/*  739:     */    {
/*  740: 740 */      if (succ) this.info |= -2147483648; else {
/*  741: 741 */        this.info &= 2147483647;
/*  742:     */      }
/*  743:     */    }
/*  744:     */    
/*  745:     */    void pred(Entry<K> pred)
/*  746:     */    {
/*  747: 747 */      this.info |= 1073741824;
/*  748: 748 */      this.left = pred;
/*  749:     */    }
/*  750:     */    
/*  752:     */    void succ(Entry<K> succ)
/*  753:     */    {
/*  754: 754 */      this.info |= -2147483648;
/*  755: 755 */      this.right = succ;
/*  756:     */    }
/*  757:     */    
/*  759:     */    void left(Entry<K> left)
/*  760:     */    {
/*  761: 761 */      this.info &= -1073741825;
/*  762: 762 */      this.left = left;
/*  763:     */    }
/*  764:     */    
/*  766:     */    void right(Entry<K> right)
/*  767:     */    {
/*  768: 768 */      this.info &= 2147483647;
/*  769: 769 */      this.right = right;
/*  770:     */    }
/*  771:     */    
/*  773:     */    int balance()
/*  774:     */    {
/*  775: 775 */      return (byte)this.info;
/*  776:     */    }
/*  777:     */    
/*  779:     */    void balance(int level)
/*  780:     */    {
/*  781: 781 */      this.info &= -256;
/*  782: 782 */      this.info |= level & 0xFF;
/*  783:     */    }
/*  784:     */    
/*  785:     */    void incBalance() {
/*  786: 786 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*  787:     */    }
/*  788:     */    
/*  789:     */    protected void decBalance() {
/*  790: 790 */      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*  791:     */    }
/*  792:     */    
/*  795:     */    Entry<K> next()
/*  796:     */    {
/*  797: 797 */      Entry<K> next = this.right;
/*  798: 798 */      for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left) {}
/*  799: 799 */      return next;
/*  800:     */    }
/*  801:     */    
/*  804:     */    Entry<K> prev()
/*  805:     */    {
/*  806: 806 */      Entry<K> prev = this.left;
/*  807: 807 */      for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right) {}
/*  808: 808 */      return prev;
/*  809:     */    }
/*  810:     */    
/*  811: 811 */    public K getKey() { return this.key; }
/*  812:     */    
/*  813:     */    public Byte getValue() {
/*  814: 814 */      return Byte.valueOf(this.value);
/*  815:     */    }
/*  816:     */    
/*  817: 817 */    public byte getByteValue() { return this.value; }
/*  818:     */    
/*  819:     */    public byte setValue(byte value) {
/*  820: 820 */      byte oldValue = this.value;
/*  821: 821 */      this.value = value;
/*  822: 822 */      return oldValue;
/*  823:     */    }
/*  824:     */    
/*  825: 825 */    public Byte setValue(Byte value) { return Byte.valueOf(setValue(value.byteValue())); }
/*  826:     */    
/*  827:     */    public Entry<K> clone()
/*  828:     */    {
/*  829:     */      Entry<K> c;
/*  830:     */      try {
/*  831: 831 */        c = (Entry)super.clone();
/*  832:     */      }
/*  833:     */      catch (CloneNotSupportedException cantHappen) {
/*  834: 834 */        throw new InternalError();
/*  835:     */      }
/*  836: 836 */      c.key = this.key;
/*  837: 837 */      c.value = this.value;
/*  838: 838 */      c.info = this.info;
/*  839: 839 */      return c;
/*  840:     */    }
/*  841:     */    
/*  842:     */    public boolean equals(Object o) {
/*  843: 843 */      if (!(o instanceof Map.Entry)) return false;
/*  844: 844 */      Map.Entry<K, Byte> e = (Map.Entry)o;
/*  845: 845 */      return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == ((Byte)e.getValue()).byteValue());
/*  846:     */    }
/*  847:     */    
/*  848: 848 */    public int hashCode() { return (this.key == null ? 0 : this.key.hashCode()) ^ this.value; }
/*  849:     */    
/*  850:     */    public String toString() {
/*  851: 851 */      return this.key + "=>" + this.value;
/*  852:     */    }
/*  853:     */  }
/*  854:     */  
/*  885:     */  public boolean containsKey(Object k)
/*  886:     */  {
/*  887: 887 */    return findKey(k) != null;
/*  888:     */  }
/*  889:     */  
/*  890: 890 */  public int size() { return this.count; }
/*  891:     */  
/*  892:     */  public boolean isEmpty() {
/*  893: 893 */    return this.count == 0;
/*  894:     */  }
/*  895:     */  
/*  896:     */  public byte getByte(Object k) {
/*  897: 897 */    Entry<K> e = findKey(k);
/*  898: 898 */    return e == null ? this.defRetValue : e.value;
/*  899:     */  }
/*  900:     */  
/*  901:     */  public Byte get(Object ok) {
/*  902: 902 */    Entry<K> e = findKey(ok);
/*  903: 903 */    return e == null ? null : e.getValue();
/*  904:     */  }
/*  905:     */  
/*  906: 906 */  public K firstKey() { if (this.tree == null) throw new NoSuchElementException();
/*  907: 907 */    return this.firstEntry.key;
/*  908:     */  }
/*  909:     */  
/*  910: 910 */  public K lastKey() { if (this.tree == null) throw new NoSuchElementException();
/*  911: 911 */    return this.lastEntry.key;
/*  912:     */  }
/*  913:     */  
/*  916:     */  private class TreeIterator
/*  917:     */  {
/*  918:     */    Object2ByteAVLTreeMap.Entry<K> prev;
/*  919:     */    
/*  921:     */    Object2ByteAVLTreeMap.Entry<K> next;
/*  922:     */    
/*  923:     */    Object2ByteAVLTreeMap.Entry<K> curr;
/*  924:     */    
/*  925: 925 */    int index = 0;
/*  926:     */    
/*  927: 927 */    TreeIterator() { this.next = Object2ByteAVLTreeMap.this.firstEntry; }
/*  928:     */    
/*  929:     */    TreeIterator() {
/*  930: 930 */      if ((this.next = Object2ByteAVLTreeMap.this.locateKey(k)) != null)
/*  931: 931 */        if (Object2ByteAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/*  932: 932 */          this.prev = this.next;
/*  933: 933 */          this.next = this.next.next();
/*  934:     */        } else {
/*  935: 935 */          this.prev = this.next.prev();
/*  936:     */        } }
/*  937:     */    
/*  938: 938 */    public boolean hasNext() { return this.next != null; }
/*  939: 939 */    public boolean hasPrevious() { return this.prev != null; }
/*  940:     */    
/*  941: 941 */    void updateNext() { this.next = this.next.next(); }
/*  942:     */    
/*  943:     */    Object2ByteAVLTreeMap.Entry<K> nextEntry() {
/*  944: 944 */      if (!hasNext()) throw new NoSuchElementException();
/*  945: 945 */      this.curr = (this.prev = this.next);
/*  946: 946 */      this.index += 1;
/*  947: 947 */      updateNext();
/*  948: 948 */      return this.curr;
/*  949:     */    }
/*  950:     */    
/*  951: 951 */    void updatePrevious() { this.prev = this.prev.prev(); }
/*  952:     */    
/*  953:     */    Object2ByteAVLTreeMap.Entry<K> previousEntry() {
/*  954: 954 */      if (!hasPrevious()) throw new NoSuchElementException();
/*  955: 955 */      this.curr = (this.next = this.prev);
/*  956: 956 */      this.index -= 1;
/*  957: 957 */      updatePrevious();
/*  958: 958 */      return this.curr;
/*  959:     */    }
/*  960:     */    
/*  961: 961 */    public int nextIndex() { return this.index; }
/*  962:     */    
/*  964: 964 */    public int previousIndex() { return this.index - 1; }
/*  965:     */    
/*  966:     */    public void remove() {
/*  967: 967 */      if (this.curr == null) { throw new IllegalStateException();
/*  968:     */      }
/*  969:     */      
/*  970: 970 */      if (this.curr == this.prev) this.index -= 1;
/*  971: 971 */      this.next = (this.prev = this.curr);
/*  972: 972 */      updatePrevious();
/*  973: 973 */      updateNext();
/*  974: 974 */      Object2ByteAVLTreeMap.this.removeByte(this.curr.key);
/*  975: 975 */      this.curr = null;
/*  976:     */    }
/*  977:     */    
/*  978: 978 */    public int skip(int n) { int i = n;
/*  979: 979 */      while ((i-- != 0) && (hasNext())) nextEntry();
/*  980: 980 */      return n - i - 1;
/*  981:     */    }
/*  982:     */    
/*  983: 983 */    public int back(int n) { int i = n;
/*  984: 984 */      while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  985: 985 */      return n - i - 1;
/*  986:     */    }
/*  987:     */  }
/*  988:     */  
/*  989:     */  private class EntryIterator
/*  990:     */    extends Object2ByteAVLTreeMap<K>.TreeIterator
/*  991:     */    implements ObjectListIterator<Object2ByteMap.Entry<K>>
/*  992:     */  {
/*  993: 993 */    EntryIterator() { super(); }
/*  994:     */    
/*  995: 995 */    EntryIterator() { super(k); }
/*  996:     */    
/*  997: 997 */    public Object2ByteMap.Entry<K> next() { return nextEntry(); }
/*  998: 998 */    public Object2ByteMap.Entry<K> previous() { return previousEntry(); }
/*  999: 999 */    public void set(Object2ByteMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/* 1000:1000 */    public void add(Object2ByteMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/* 1001:     */  }
/* 1002:     */  
/* 1003:1003 */  public ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet() { if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1004:1004 */        final Comparator<? super Object2ByteMap.Entry<K>> comparator = new Comparator() {
/* 1005:     */          public int compare(Object2ByteMap.Entry<K> x, Object2ByteMap.Entry<K> y) {
/* 1006:1006 */            return Object2ByteAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/* 1007:     */          }
/* 1008:     */        };
/* 1009:     */        
/* 1010:1010 */        public Comparator<? super Object2ByteMap.Entry<K>> comparator() { return this.comparator; }
/* 1011:     */        
/* 1012:     */        public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> iterator() {
/* 1013:1013 */          return new Object2ByteAVLTreeMap.EntryIterator(Object2ByteAVLTreeMap.this);
/* 1014:     */        }
/* 1015:     */        
/* 1016:1016 */        public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> iterator(Object2ByteMap.Entry<K> from) { return new Object2ByteAVLTreeMap.EntryIterator(Object2ByteAVLTreeMap.this, from.getKey()); }
/* 1017:     */        
/* 1018:     */        public boolean contains(Object o)
/* 1019:     */        {
/* 1020:1020 */          if (!(o instanceof Map.Entry)) return false;
/* 1021:1021 */          Map.Entry<K, Byte> e = (Map.Entry)o;
/* 1022:1022 */          Object2ByteAVLTreeMap.Entry<K> f = Object2ByteAVLTreeMap.this.findKey(e.getKey());
/* 1023:1023 */          return e.equals(f);
/* 1024:     */        }
/* 1025:     */        
/* 1026:     */        public boolean remove(Object o) {
/* 1027:1027 */          if (!(o instanceof Map.Entry)) return false;
/* 1028:1028 */          Map.Entry<K, Byte> e = (Map.Entry)o;
/* 1029:1029 */          Object2ByteAVLTreeMap.Entry<K> f = Object2ByteAVLTreeMap.this.findKey(e.getKey());
/* 1030:1030 */          if (f != null) Object2ByteAVLTreeMap.this.removeByte(f.key);
/* 1031:1031 */          return f != null; }
/* 1032:     */        
/* 1033:1033 */        public int size() { return Object2ByteAVLTreeMap.this.count; }
/* 1034:1034 */        public void clear() { Object2ByteAVLTreeMap.this.clear(); }
/* 1035:1035 */        public Object2ByteMap.Entry<K> first() { return Object2ByteAVLTreeMap.this.firstEntry; }
/* 1036:1036 */        public Object2ByteMap.Entry<K> last() { return Object2ByteAVLTreeMap.this.lastEntry; }
/* 1037:1037 */        public ObjectSortedSet<Object2ByteMap.Entry<K>> subSet(Object2ByteMap.Entry<K> from, Object2ByteMap.Entry<K> to) { return Object2ByteAVLTreeMap.this.subMap(from.getKey(), to.getKey()).object2ByteEntrySet(); }
/* 1038:1038 */        public ObjectSortedSet<Object2ByteMap.Entry<K>> headSet(Object2ByteMap.Entry<K> to) { return Object2ByteAVLTreeMap.this.headMap(to.getKey()).object2ByteEntrySet(); }
/* 1039:1039 */        public ObjectSortedSet<Object2ByteMap.Entry<K>> tailSet(Object2ByteMap.Entry<K> from) { return Object2ByteAVLTreeMap.this.tailMap(from.getKey()).object2ByteEntrySet(); }
/* 1040:     */      };
/* 1041:1041 */    return this.entries;
/* 1042:     */  }
/* 1043:     */  
/* 1046:     */  private final class KeyIterator
/* 1047:     */    extends Object2ByteAVLTreeMap<K>.TreeIterator
/* 1048:     */    implements ObjectListIterator<K>
/* 1049:     */  {
/* 1050:1050 */    public KeyIterator() { super(); }
/* 1051:1051 */    public KeyIterator() { super(k); }
/* 1052:1052 */    public K next() { return nextEntry().key; }
/* 1053:1053 */    public K previous() { return previousEntry().key; }
/* 1054:1054 */    public void set(K k) { throw new UnsupportedOperationException(); }
/* 1055:1055 */    public void add(K k) { throw new UnsupportedOperationException(); }
/* 1056:     */  }
/* 1057:     */  
/* 1058:1058 */  private class KeySet extends AbstractObject2ByteSortedMap.KeySet { private KeySet() { super(); }
/* 1059:1059 */    public ObjectBidirectionalIterator<K> iterator() { return new Object2ByteAVLTreeMap.KeyIterator(Object2ByteAVLTreeMap.this); }
/* 1060:1060 */    public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ByteAVLTreeMap.KeyIterator(Object2ByteAVLTreeMap.this, from); }
/* 1061:     */  }
/* 1062:     */  
/* 1069:     */  public ObjectSortedSet<K> keySet()
/* 1070:     */  {
/* 1071:1071 */    if (this.keys == null) this.keys = new KeySet(null);
/* 1072:1072 */    return this.keys;
/* 1073:     */  }
/* 1074:     */  
/* 1076:     */  private final class ValueIterator
/* 1077:     */    extends Object2ByteAVLTreeMap.TreeIterator
/* 1078:     */    implements ByteListIterator
/* 1079:     */  {
/* 1080:1080 */    private ValueIterator() { super(); }
/* 1081:1081 */    public byte nextByte() { return nextEntry().value; }
/* 1082:1082 */    public byte previousByte() { return previousEntry().value; }
/* 1083:1083 */    public void set(byte v) { throw new UnsupportedOperationException(); }
/* 1084:1084 */    public void add(byte v) { throw new UnsupportedOperationException(); }
/* 1085:1085 */    public Byte next() { return Byte.valueOf(nextEntry().value); }
/* 1086:1086 */    public Byte previous() { return Byte.valueOf(previousEntry().value); }
/* 1087:1087 */    public void set(Byte ok) { throw new UnsupportedOperationException(); }
/* 1088:1088 */    public void add(Byte ok) { throw new UnsupportedOperationException(); }
/* 1089:     */  }
/* 1090:     */  
/* 1097:     */  public ByteCollection values()
/* 1098:     */  {
/* 1099:1099 */    if (this.values == null) { this.values = new AbstractByteCollection() {
/* 1100:     */        public ByteIterator iterator() {
/* 1101:1101 */          return new Object2ByteAVLTreeMap.ValueIterator(Object2ByteAVLTreeMap.this, null);
/* 1102:     */        }
/* 1103:     */        
/* 1104:1104 */        public boolean contains(byte k) { return Object2ByteAVLTreeMap.this.containsValue(k); }
/* 1105:     */        
/* 1106:     */        public int size() {
/* 1107:1107 */          return Object2ByteAVLTreeMap.this.count;
/* 1108:     */        }
/* 1109:     */        
/* 1110:1110 */        public void clear() { Object2ByteAVLTreeMap.this.clear(); }
/* 1111:     */      };
/* 1112:     */    }
/* 1113:1113 */    return this.values;
/* 1114:     */  }
/* 1115:     */  
/* 1116:1116 */  public Comparator<? super K> comparator() { return this.actualComparator; }
/* 1117:     */  
/* 1118:     */  public Object2ByteSortedMap<K> headMap(K to) {
/* 1119:1119 */    return new Submap(null, true, to, false);
/* 1120:     */  }
/* 1121:     */  
/* 1122:1122 */  public Object2ByteSortedMap<K> tailMap(K from) { return new Submap(from, false, null, true); }
/* 1123:     */  
/* 1124:     */  public Object2ByteSortedMap<K> subMap(K from, K to) {
/* 1125:1125 */    return new Submap(from, false, to, false);
/* 1126:     */  }
/* 1127:     */  
/* 1131:     */  private final class Submap
/* 1132:     */    extends AbstractObject2ByteSortedMap<K>
/* 1133:     */    implements Serializable
/* 1134:     */  {
/* 1135:     */    public static final long serialVersionUID = -7046029254386353129L;
/* 1136:     */    
/* 1139:     */    K from;
/* 1140:     */    
/* 1142:     */    K to;
/* 1143:     */    
/* 1145:     */    boolean bottom;
/* 1146:     */    
/* 1148:     */    boolean top;
/* 1149:     */    
/* 1151:     */    protected volatile transient ObjectSortedSet<Object2ByteMap.Entry<K>> entries;
/* 1152:     */    
/* 1154:     */    protected volatile transient ObjectSortedSet<K> keys;
/* 1155:     */    
/* 1157:     */    protected volatile transient ByteCollection values;
/* 1158:     */    
/* 1161:     */    public Submap(boolean from, K bottom, boolean to)
/* 1162:     */    {
/* 1163:1163 */      if ((!bottom) && (!top) && (Object2ByteAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1164:1164 */      this.from = from;
/* 1165:1165 */      this.bottom = bottom;
/* 1166:1166 */      this.to = to;
/* 1167:1167 */      this.top = top;
/* 1168:1168 */      this.defRetValue = Object2ByteAVLTreeMap.this.defRetValue;
/* 1169:     */    }
/* 1170:     */    
/* 1171:1171 */    public void clear() { Object2ByteAVLTreeMap<K>.Submap.SubmapIterator i = new SubmapIterator();
/* 1172:1172 */      while (i.hasNext()) {
/* 1173:1173 */        i.nextEntry();
/* 1174:1174 */        i.remove();
/* 1175:     */      }
/* 1176:     */    }
/* 1177:     */    
/* 1180:     */    final boolean in(K k)
/* 1181:     */    {
/* 1182:1182 */      return ((this.bottom) || (Object2ByteAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ByteAVLTreeMap.this.compare(k, this.to) < 0));
/* 1183:     */    }
/* 1184:     */    
/* 1185:     */    public ObjectSortedSet<Object2ByteMap.Entry<K>> object2ByteEntrySet() {
/* 1186:1186 */      if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1187:     */          public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> iterator() {
/* 1188:1188 */            return new Object2ByteAVLTreeMap.Submap.SubmapEntryIterator(Object2ByteAVLTreeMap.Submap.this);
/* 1189:     */          }
/* 1190:     */          
/* 1191:1191 */          public ObjectBidirectionalIterator<Object2ByteMap.Entry<K>> iterator(Object2ByteMap.Entry<K> from) { return new Object2ByteAVLTreeMap.Submap.SubmapEntryIterator(Object2ByteAVLTreeMap.Submap.this, from.getKey()); }
/* 1192:     */          
/* 1193:1193 */          public Comparator<? super Object2ByteMap.Entry<K>> comparator() { return Object2ByteAVLTreeMap.this.entrySet().comparator(); }
/* 1194:     */          
/* 1195:     */          public boolean contains(Object o) {
/* 1196:1196 */            if (!(o instanceof Map.Entry)) return false;
/* 1197:1197 */            Map.Entry<K, Byte> e = (Map.Entry)o;
/* 1198:1198 */            Object2ByteAVLTreeMap.Entry<K> f = Object2ByteAVLTreeMap.this.findKey(e.getKey());
/* 1199:1199 */            return (f != null) && (Object2ByteAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/* 1200:     */          }
/* 1201:     */          
/* 1202:     */          public boolean remove(Object o) {
/* 1203:1203 */            if (!(o instanceof Map.Entry)) return false;
/* 1204:1204 */            Map.Entry<K, Byte> e = (Map.Entry)o;
/* 1205:1205 */            Object2ByteAVLTreeMap.Entry<K> f = Object2ByteAVLTreeMap.this.findKey(e.getKey());
/* 1206:1206 */            if ((f != null) && (Object2ByteAVLTreeMap.Submap.this.in(f.key))) Object2ByteAVLTreeMap.Submap.this.removeByte(f.key);
/* 1207:1207 */            return f != null;
/* 1208:     */          }
/* 1209:     */          
/* 1210:1210 */          public int size() { int c = 0;
/* 1211:1211 */            for (Iterator<?> i = iterator(); i.hasNext(); i.next()) c++;
/* 1212:1212 */            return c;
/* 1213:     */          }
/* 1214:     */          
/* 1215:1215 */          public boolean isEmpty() { return !new Object2ByteAVLTreeMap.Submap.SubmapIterator(Object2ByteAVLTreeMap.Submap.this).hasNext(); }
/* 1216:     */          
/* 1218:1218 */          public void clear() { Object2ByteAVLTreeMap.Submap.this.clear(); }
/* 1219:     */          
/* 1220:1220 */          public Object2ByteMap.Entry<K> first() { return Object2ByteAVLTreeMap.Submap.this.firstEntry(); }
/* 1221:1221 */          public Object2ByteMap.Entry<K> last() { return Object2ByteAVLTreeMap.Submap.this.lastEntry(); }
/* 1222:1222 */          public ObjectSortedSet<Object2ByteMap.Entry<K>> subSet(Object2ByteMap.Entry<K> from, Object2ByteMap.Entry<K> to) { return Object2ByteAVLTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ByteEntrySet(); }
/* 1223:1223 */          public ObjectSortedSet<Object2ByteMap.Entry<K>> headSet(Object2ByteMap.Entry<K> to) { return Object2ByteAVLTreeMap.Submap.this.headMap(to.getKey()).object2ByteEntrySet(); }
/* 1224:1224 */          public ObjectSortedSet<Object2ByteMap.Entry<K>> tailSet(Object2ByteMap.Entry<K> from) { return Object2ByteAVLTreeMap.Submap.this.tailMap(from.getKey()).object2ByteEntrySet(); }
/* 1225:     */        };
/* 1226:1226 */      return this.entries; }
/* 1227:     */    
/* 1228:1228 */    private class KeySet extends AbstractObject2ByteSortedMap.KeySet { private KeySet() { super(); }
/* 1229:1229 */      public ObjectBidirectionalIterator<K> iterator() { return new Object2ByteAVLTreeMap.Submap.SubmapKeyIterator(Object2ByteAVLTreeMap.Submap.this); }
/* 1230:1230 */      public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ByteAVLTreeMap.Submap.SubmapKeyIterator(Object2ByteAVLTreeMap.Submap.this, from); }
/* 1231:     */    }
/* 1232:     */    
/* 1233:1233 */    public ObjectSortedSet<K> keySet() { if (this.keys == null) this.keys = new KeySet(null);
/* 1234:1234 */      return this.keys;
/* 1235:     */    }
/* 1236:     */    
/* 1237:1237 */    public ByteCollection values() { if (this.values == null) { this.values = new AbstractByteCollection() {
/* 1238:     */          public ByteIterator iterator() {
/* 1239:1239 */            return new Object2ByteAVLTreeMap.Submap.SubmapValueIterator(Object2ByteAVLTreeMap.Submap.this, null);
/* 1240:     */          }
/* 1241:     */          
/* 1242:1242 */          public boolean contains(byte k) { return Object2ByteAVLTreeMap.Submap.this.containsValue(k); }
/* 1243:     */          
/* 1244:     */          public int size() {
/* 1245:1245 */            return Object2ByteAVLTreeMap.Submap.this.size();
/* 1246:     */          }
/* 1247:     */          
/* 1248:1248 */          public void clear() { Object2ByteAVLTreeMap.Submap.this.clear(); }
/* 1249:     */        };
/* 1250:     */      }
/* 1251:1251 */      return this.values;
/* 1252:     */    }
/* 1253:     */    
/* 1255:1255 */    public boolean containsKey(Object k) { return (in(k)) && (Object2ByteAVLTreeMap.this.containsKey(k)); }
/* 1256:     */    
/* 1257:     */    public boolean containsValue(byte v) {
/* 1258:1258 */      Object2ByteAVLTreeMap<K>.Submap.SubmapIterator i = new SubmapIterator();
/* 1259:     */      
/* 1260:1260 */      while (i.hasNext()) {
/* 1261:1261 */        byte ev = i.nextEntry().value;
/* 1262:1262 */        if (ev == v) return true;
/* 1263:     */      }
/* 1264:1264 */      return false;
/* 1265:     */    }
/* 1266:     */    
/* 1267:     */    public byte getByte(Object k)
/* 1268:     */    {
/* 1269:1269 */      K kk = k;
/* 1270:1270 */      Object2ByteAVLTreeMap.Entry<K> e; return (in(kk)) && ((e = Object2ByteAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/* 1271:     */    }
/* 1272:     */    
/* 1273:     */    public Byte get(Object ok)
/* 1274:     */    {
/* 1275:1275 */      K kk = ok;
/* 1276:1276 */      Object2ByteAVLTreeMap.Entry<K> e; return (in(kk)) && ((e = Object2ByteAVLTreeMap.this.findKey(kk)) != null) ? e.getValue() : null;
/* 1277:     */    }
/* 1278:     */    
/* 1279:1279 */    public byte put(K k, byte v) { Object2ByteAVLTreeMap.this.modified = false;
/* 1280:1280 */      if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1281:1281 */      byte oldValue = Object2ByteAVLTreeMap.this.put(k, v);
/* 1282:1282 */      return Object2ByteAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/* 1283:     */    }
/* 1284:     */    
/* 1285:1285 */    public Byte put(K ok, Byte ov) { byte oldValue = put(ok, ov.byteValue());
/* 1286:1286 */      return Object2ByteAVLTreeMap.this.modified ? null : Byte.valueOf(oldValue);
/* 1287:     */    }
/* 1288:     */    
/* 1289:     */    public byte removeByte(Object k) {
/* 1290:1290 */      Object2ByteAVLTreeMap.this.modified = false;
/* 1291:1291 */      if (!in(k)) return this.defRetValue;
/* 1292:1292 */      byte oldValue = Object2ByteAVLTreeMap.this.removeByte(k);
/* 1293:1293 */      return Object2ByteAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/* 1294:     */    }
/* 1295:     */    
/* 1296:1296 */    public Byte remove(Object ok) { byte oldValue = removeByte(ok);
/* 1297:1297 */      return Object2ByteAVLTreeMap.this.modified ? Byte.valueOf(oldValue) : null;
/* 1298:     */    }
/* 1299:     */    
/* 1300:1300 */    public int size() { Object2ByteAVLTreeMap<K>.Submap.SubmapIterator i = new SubmapIterator();
/* 1301:1301 */      int n = 0;
/* 1302:1302 */      while (i.hasNext()) {
/* 1303:1303 */        n++;
/* 1304:1304 */        i.nextEntry();
/* 1305:     */      }
/* 1306:1306 */      return n;
/* 1307:     */    }
/* 1308:     */    
/* 1309:1309 */    public boolean isEmpty() { return !new SubmapIterator().hasNext(); }
/* 1310:     */    
/* 1312:1312 */    public Comparator<? super K> comparator() { return Object2ByteAVLTreeMap.this.actualComparator; }
/* 1313:     */    
/* 1314:     */    public Object2ByteSortedMap<K> headMap(K to) {
/* 1315:1315 */      if (this.top) return new Submap(Object2ByteAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1316:1316 */      return Object2ByteAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ByteAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/* 1317:     */    }
/* 1318:     */    
/* 1319:1319 */    public Object2ByteSortedMap<K> tailMap(K from) { if (this.bottom) return new Submap(Object2ByteAVLTreeMap.this, from, false, this.to, this.top);
/* 1320:1320 */      return Object2ByteAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ByteAVLTreeMap.this, from, false, this.to, this.top) : this;
/* 1321:     */    }
/* 1322:     */    
/* 1323:1323 */    public Object2ByteSortedMap<K> subMap(K from, K to) { if ((this.top) && (this.bottom)) return new Submap(Object2ByteAVLTreeMap.this, from, false, to, false);
/* 1324:1324 */      if (!this.top) to = Object2ByteAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1325:1325 */      if (!this.bottom) from = Object2ByteAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1326:1326 */      if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1327:1327 */      return new Submap(Object2ByteAVLTreeMap.this, from, false, to, false);
/* 1328:     */    }
/* 1329:     */    
/* 1332:     */    public Object2ByteAVLTreeMap.Entry<K> firstEntry()
/* 1333:     */    {
/* 1334:1334 */      if (Object2ByteAVLTreeMap.this.tree == null) return null;
/* 1335:     */      Object2ByteAVLTreeMap.Entry<K> e;
/* 1336:     */      Object2ByteAVLTreeMap.Entry<K> e;
/* 1337:1337 */      if (this.bottom) { e = Object2ByteAVLTreeMap.this.firstEntry;
/* 1338:     */      } else {
/* 1339:1339 */        e = Object2ByteAVLTreeMap.this.locateKey(this.from);
/* 1340:     */        
/* 1341:1341 */        if (Object2ByteAVLTreeMap.this.compare(e.key, this.from) < 0) { e = e.next();
/* 1342:     */        }
/* 1343:     */      }
/* 1344:1344 */      if ((e == null) || ((!this.top) && (Object2ByteAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1345:1345 */      return e;
/* 1346:     */    }
/* 1347:     */    
/* 1350:     */    public Object2ByteAVLTreeMap.Entry<K> lastEntry()
/* 1351:     */    {
/* 1352:1352 */      if (Object2ByteAVLTreeMap.this.tree == null) return null;
/* 1353:     */      Object2ByteAVLTreeMap.Entry<K> e;
/* 1354:     */      Object2ByteAVLTreeMap.Entry<K> e;
/* 1355:1355 */      if (this.top) { e = Object2ByteAVLTreeMap.this.lastEntry;
/* 1356:     */      } else {
/* 1357:1357 */        e = Object2ByteAVLTreeMap.this.locateKey(this.to);
/* 1358:     */        
/* 1359:1359 */        if (Object2ByteAVLTreeMap.this.compare(e.key, this.to) >= 0) { e = e.prev();
/* 1360:     */        }
/* 1361:     */      }
/* 1362:1362 */      if ((e == null) || ((!this.bottom) && (Object2ByteAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1363:1363 */      return e;
/* 1364:     */    }
/* 1365:     */    
/* 1366:1366 */    public K firstKey() { Object2ByteAVLTreeMap.Entry<K> e = firstEntry();
/* 1367:1367 */      if (e == null) throw new NoSuchElementException();
/* 1368:1368 */      return e.key;
/* 1369:     */    }
/* 1370:     */    
/* 1371:1371 */    public K lastKey() { Object2ByteAVLTreeMap.Entry<K> e = lastEntry();
/* 1372:1372 */      if (e == null) throw new NoSuchElementException();
/* 1373:1373 */      return e.key;
/* 1374:     */    }
/* 1375:     */    
/* 1378:     */    private class SubmapIterator
/* 1379:     */      extends Object2ByteAVLTreeMap.TreeIterator
/* 1380:     */    {
/* 1381:     */      SubmapIterator()
/* 1382:     */      {
/* 1383:1383 */        super();
/* 1384:1384 */        this.next = Object2ByteAVLTreeMap.Submap.this.firstEntry();
/* 1385:     */      }
/* 1386:     */      
/* 1387:1387 */      SubmapIterator() { this();
/* 1388:1388 */        if (this.next != null)
/* 1389:1389 */          if ((!Object2ByteAVLTreeMap.Submap.this.bottom) && (Object2ByteAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1390:1390 */          } else if ((!Object2ByteAVLTreeMap.Submap.this.top) && (Object2ByteAVLTreeMap.this.compare(k, (this.prev = Object2ByteAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/* 1391:     */          } else {
/* 1392:1392 */            this.next = Object2ByteAVLTreeMap.this.locateKey(k);
/* 1393:1393 */            if (Object2ByteAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1394:1394 */              this.prev = this.next;
/* 1395:1395 */              this.next = this.next.next();
/* 1396:     */            } else {
/* 1397:1397 */              this.prev = this.next.prev();
/* 1398:     */            }
/* 1399:     */          }
/* 1400:     */      }
/* 1401:     */      
/* 1402:1402 */      void updatePrevious() { this.prev = this.prev.prev();
/* 1403:1403 */        if ((!Object2ByteAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ByteAVLTreeMap.this.compare(this.prev.key, Object2ByteAVLTreeMap.Submap.this.from) < 0)) this.prev = null;
/* 1404:     */      }
/* 1405:     */      
/* 1406:1406 */      void updateNext() { this.next = this.next.next();
/* 1407:1407 */        if ((!Object2ByteAVLTreeMap.Submap.this.top) && (this.next != null) && (Object2ByteAVLTreeMap.this.compare(this.next.key, Object2ByteAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/* 1408:     */      }
/* 1409:     */    }
/* 1410:     */    
/* 1411:1411 */    private class SubmapEntryIterator extends Object2ByteAVLTreeMap<K>.Submap.SubmapIterator implements ObjectListIterator<Object2ByteMap.Entry<K>> { SubmapEntryIterator() { super(); }
/* 1412:     */      
/* 1413:1413 */      SubmapEntryIterator() { super(k); }
/* 1414:     */      
/* 1415:1415 */      public Object2ByteMap.Entry<K> next() { return nextEntry(); }
/* 1416:1416 */      public Object2ByteMap.Entry<K> previous() { return previousEntry(); }
/* 1417:1417 */      public void set(Object2ByteMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/* 1418:1418 */      public void add(Object2ByteMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/* 1419:     */    }
/* 1420:     */    
/* 1425:     */    private final class SubmapKeyIterator
/* 1426:     */      extends Object2ByteAVLTreeMap<K>.Submap.SubmapIterator
/* 1427:     */      implements ObjectListIterator<K>
/* 1428:     */    {
/* 1429:1429 */      public SubmapKeyIterator() { super(); }
/* 1430:1430 */      public SubmapKeyIterator() { super(from); }
/* 1431:1431 */      public K next() { return nextEntry().key; }
/* 1432:1432 */      public K previous() { return previousEntry().key; }
/* 1433:1433 */      public void set(K k) { throw new UnsupportedOperationException(); }
/* 1434:1434 */      public void add(K k) { throw new UnsupportedOperationException(); }
/* 1435:     */    }
/* 1436:     */    
/* 1440:     */    private final class SubmapValueIterator
/* 1441:     */      extends Object2ByteAVLTreeMap.Submap.SubmapIterator
/* 1442:     */      implements ByteListIterator
/* 1443:     */    {
/* 1444:1444 */      private SubmapValueIterator() { super(); }
/* 1445:1445 */      public byte nextByte() { return nextEntry().value; }
/* 1446:1446 */      public byte previousByte() { return previousEntry().value; }
/* 1447:1447 */      public void set(byte v) { throw new UnsupportedOperationException(); }
/* 1448:1448 */      public void add(byte v) { throw new UnsupportedOperationException(); }
/* 1449:1449 */      public Byte next() { return Byte.valueOf(nextEntry().value); }
/* 1450:1450 */      public Byte previous() { return Byte.valueOf(previousEntry().value); }
/* 1451:1451 */      public void set(Byte ok) { throw new UnsupportedOperationException(); }
/* 1452:1452 */      public void add(Byte ok) { throw new UnsupportedOperationException(); }
/* 1453:     */    }
/* 1454:     */  }
/* 1455:     */  
/* 1459:     */  public Object2ByteAVLTreeMap<K> clone()
/* 1460:     */  {
/* 1461:     */    Object2ByteAVLTreeMap<K> c;
/* 1462:     */    
/* 1464:     */    try
/* 1465:     */    {
/* 1466:1466 */      c = (Object2ByteAVLTreeMap)super.clone();
/* 1467:     */    }
/* 1468:     */    catch (CloneNotSupportedException cantHappen) {
/* 1469:1469 */      throw new InternalError();
/* 1470:     */    }
/* 1471:1471 */    c.keys = null;
/* 1472:1472 */    c.values = null;
/* 1473:1473 */    c.entries = null;
/* 1474:1474 */    c.allocatePaths();
/* 1475:1475 */    if (this.count != 0)
/* 1476:     */    {
/* 1477:1477 */      Entry<K> rp = new Entry();Entry<K> rq = new Entry();
/* 1478:1478 */      Entry<K> p = rp;
/* 1479:1479 */      rp.left(this.tree);
/* 1480:1480 */      Entry<K> q = rq;
/* 1481:1481 */      rq.pred(null);
/* 1482:     */      for (;;) {
/* 1483:1483 */        if (!p.pred()) {
/* 1484:1484 */          Entry<K> e = p.left.clone();
/* 1485:1485 */          e.pred(q.left);
/* 1486:1486 */          e.succ(q);
/* 1487:1487 */          q.left(e);
/* 1488:1488 */          p = p.left;
/* 1489:1489 */          q = q.left;
/* 1490:     */        }
/* 1491:     */        else {
/* 1492:1492 */          while (p.succ()) {
/* 1493:1493 */            p = p.right;
/* 1494:1494 */            if (p == null) {
/* 1495:1495 */              q.right = null;
/* 1496:1496 */              c.tree = rq.left;
/* 1497:1497 */              c.firstEntry = c.tree;
/* 1498:1498 */              while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1499:1499 */              c.lastEntry = c.tree;
/* 1500:1500 */              while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1501:1501 */              return c;
/* 1502:     */            }
/* 1503:1503 */            q = q.right;
/* 1504:     */          }
/* 1505:1505 */          p = p.right;
/* 1506:1506 */          q = q.right;
/* 1507:     */        }
/* 1508:1508 */        if (!p.succ()) {
/* 1509:1509 */          Entry<K> e = p.right.clone();
/* 1510:1510 */          e.succ(q.right);
/* 1511:1511 */          e.pred(q);
/* 1512:1512 */          q.right(e);
/* 1513:     */        }
/* 1514:     */      }
/* 1515:     */    }
/* 1516:1516 */    return c;
/* 1517:     */  }
/* 1518:     */  
/* 1519:1519 */  private void writeObject(ObjectOutputStream s) throws IOException { int n = this.count;
/* 1520:1520 */    Object2ByteAVLTreeMap<K>.EntryIterator i = new EntryIterator();
/* 1521:     */    
/* 1522:1522 */    s.defaultWriteObject();
/* 1523:1523 */    while (n-- != 0) {
/* 1524:1524 */      Entry<K> e = i.nextEntry();
/* 1525:1525 */      s.writeObject(e.key);
/* 1526:1526 */      s.writeByte(e.value);
/* 1527:     */    }
/* 1528:     */  }
/* 1529:     */  
/* 1535:     */  private Entry<K> readTree(ObjectInputStream s, int n, Entry<K> pred, Entry<K> succ)
/* 1536:     */    throws IOException, ClassNotFoundException
/* 1537:     */  {
/* 1538:1538 */    if (n == 1) {
/* 1539:1539 */      Entry<K> top = new Entry(s.readObject(), s.readByte());
/* 1540:1540 */      top.pred(pred);
/* 1541:1541 */      top.succ(succ);
/* 1542:1542 */      return top;
/* 1543:     */    }
/* 1544:1544 */    if (n == 2)
/* 1545:     */    {
/* 1547:1547 */      Entry<K> top = new Entry(s.readObject(), s.readByte());
/* 1548:1548 */      top.right(new Entry(s.readObject(), s.readByte()));
/* 1549:1549 */      top.right.pred(top);
/* 1550:1550 */      top.balance(1);
/* 1551:1551 */      top.pred(pred);
/* 1552:1552 */      top.right.succ(succ);
/* 1553:1553 */      return top;
/* 1554:     */    }
/* 1555:     */    
/* 1556:1556 */    int rightN = n / 2;int leftN = n - rightN - 1;
/* 1557:1557 */    Entry<K> top = new Entry();
/* 1558:1558 */    top.left(readTree(s, leftN, pred, top));
/* 1559:1559 */    top.key = s.readObject();
/* 1560:1560 */    top.value = s.readByte();
/* 1561:1561 */    top.right(readTree(s, rightN, top, succ));
/* 1562:1562 */    if (n == (n & -n)) top.balance(1);
/* 1563:1563 */    return top;
/* 1564:     */  }
/* 1565:     */  
/* 1566:1566 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 1567:     */    
/* 1569:1569 */    setActualComparator();
/* 1570:1570 */    allocatePaths();
/* 1571:1571 */    if (this.count != 0) {
/* 1572:1572 */      this.tree = readTree(s, this.count, null, null);
/* 1573:     */      
/* 1574:1574 */      Entry<K> e = this.tree;
/* 1575:1575 */      while (e.left() != null) e = e.left();
/* 1576:1576 */      this.firstEntry = e;
/* 1577:1577 */      e = this.tree;
/* 1578:1578 */      while (e.right() != null) e = e.right();
/* 1579:1579 */      this.lastEntry = e;
/* 1580:     */    }
/* 1581:     */  }
/* 1582:     */  
/* 1583:     */  private static int checkTree(Entry e) {
/* 1584:1584 */    return 0;
/* 1585:     */  }
/* 1586:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ByteAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */