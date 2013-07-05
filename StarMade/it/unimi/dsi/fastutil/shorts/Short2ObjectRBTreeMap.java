/*      */ package it.unimi.dsi.fastutil.shorts;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.SortedMap;
/*      */ 
/*      */ public class Short2ObjectRBTreeMap<V> extends AbstractShort2ObjectSortedMap<V>
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry<V> tree;
/*      */   protected int count;
/*      */   protected transient Entry<V> firstEntry;
/*      */   protected transient Entry<V> lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Short2ObjectMap.Entry<V>> entries;
/*      */   protected volatile transient ShortSortedSet keys;
/*      */   protected volatile transient ObjectCollection<V> values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super Short> storedComparator;
/*      */   protected transient ShortComparator actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */   private transient Entry<V>[] nodePath;
/*      */ 
/*      */   public Short2ObjectRBTreeMap()
/*      */   {
/*   92 */     allocatePaths();
/*      */ 
/*   97 */     this.tree = null;
/*   98 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  116 */     if ((this.storedComparator == null) || ((this.storedComparator instanceof ShortComparator))) this.actualComparator = ((ShortComparator)this.storedComparator); else
/*  117 */       this.actualComparator = new ShortComparator() {
/*      */         public int compare(short k1, short k2) {
/*  119 */           return Short2ObjectRBTreeMap.this.storedComparator.compare(Short.valueOf(k1), Short.valueOf(k2));
/*      */         }
/*      */         public int compare(Short ok1, Short ok2) {
/*  122 */           return Short2ObjectRBTreeMap.this.storedComparator.compare(ok1, ok2);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(Comparator<? super Short> c)
/*      */   {
/*  135 */     this();
/*  136 */     this.storedComparator = c;
/*  137 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(Map<? extends Short, ? extends V> m)
/*      */   {
/*  147 */     this();
/*  148 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(SortedMap<Short, V> m)
/*      */   {
/*  157 */     this(m.comparator());
/*  158 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(Short2ObjectMap<? extends V> m)
/*      */   {
/*  167 */     this();
/*  168 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(Short2ObjectSortedMap<V> m)
/*      */   {
/*  177 */     this(m.comparator());
/*  178 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(short[] k, V[] v, Comparator<? super Short> c)
/*      */   {
/*  190 */     this(c);
/*  191 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  192 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap(short[] k, V[] v)
/*      */   {
/*  203 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(short k1, short k2)
/*      */   {
/*  230 */     return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<V> findKey(short k)
/*      */   {
/*  242 */     Entry e = this.tree;
/*      */     int cmp;
/*  245 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*      */ 
/*  247 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<V> locateKey(short k)
/*      */   {
/*  258 */     Entry e = this.tree; Entry last = this.tree;
/*  259 */     int cmp = 0;
/*      */ 
/*  261 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  262 */       last = e;
/*  263 */       e = cmp < 0 ? e.left() : e.right();
/*      */     }
/*      */ 
/*  266 */     return cmp == 0 ? e : last;
/*      */   }
/*      */ 
/*      */   private void allocatePaths()
/*      */   {
/*  276 */     this.dirPath = new boolean[64];
/*  277 */     this.nodePath = new Entry[64];
/*      */   }
/*      */ 
/*      */   public V put(short k, V v)
/*      */   {
/*  284 */     this.modified = false;
/*  285 */     int maxDepth = 0;
/*      */ 
/*  287 */     if (this.tree == null) {
/*  288 */       this.count += 1;
/*  289 */       this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*      */     }
/*      */     else {
/*  292 */       Entry p = this.tree;
/*  293 */       int i = 0;
/*      */       while (true)
/*      */       {
/*      */         int cmp;
/*  296 */         if ((cmp = compare(k, p.key)) == 0) {
/*  297 */           Object oldValue = p.value;
/*  298 */           p.value = v;
/*      */ 
/*  300 */           while (i-- != 0) this.nodePath[i] = null;
/*  301 */           return oldValue;
/*      */         }
/*      */ 
/*  304 */         this.nodePath[i] = p;
/*      */ 
/*  306 */         if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  307 */           if (p.succ()) {
/*  308 */             this.count += 1;
/*  309 */             Entry e = new Entry(k, v);
/*      */ 
/*  311 */             if (p.right == null) this.lastEntry = e;
/*      */ 
/*  313 */             e.left = p;
/*  314 */             e.right = p.right;
/*      */ 
/*  316 */             p.right(e);
/*      */ 
/*  318 */             break;
/*      */           }
/*      */ 
/*  321 */           p = p.right;
/*      */         }
/*      */         else {
/*  324 */           if (p.pred()) {
/*  325 */             this.count += 1;
/*  326 */             Entry e = new Entry(k, v);
/*      */ 
/*  328 */             if (p.left == null) this.firstEntry = e;
/*      */ 
/*  330 */             e.right = p;
/*  331 */             e.left = p.left;
/*      */ 
/*  333 */             p.left(e);
/*      */ 
/*  335 */             break;
/*      */           }
/*      */ 
/*  338 */           p = p.left;
/*      */         }
/*      */       }
/*      */       Entry e;
/*  342 */       this.modified = true;
/*  343 */       maxDepth = i--;
/*      */ 
/*  345 */       while ((i > 0) && (!this.nodePath[i].black())) {
/*  346 */         if (this.dirPath[(i - 1)] == 0) {
/*  347 */           Entry y = this.nodePath[(i - 1)].right;
/*      */ 
/*  349 */           if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  350 */             this.nodePath[i].black(true);
/*  351 */             y.black(true);
/*  352 */             this.nodePath[(i - 1)].black(false);
/*  353 */             i -= 2;
/*      */           }
/*      */           else
/*      */           {
/*  358 */             if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*      */             } else {
/*  360 */               Entry x = this.nodePath[i];
/*  361 */               y = x.right;
/*  362 */               x.right = y.left;
/*  363 */               y.left = x;
/*  364 */               this.nodePath[(i - 1)].left = y;
/*      */ 
/*  366 */               if (y.pred()) {
/*  367 */                 y.pred(false);
/*  368 */                 x.succ(y);
/*      */               }
/*      */             }
/*      */ 
/*  372 */             Entry x = this.nodePath[(i - 1)];
/*  373 */             x.black(false);
/*  374 */             y.black(true);
/*      */ 
/*  376 */             x.left = y.right;
/*  377 */             y.right = x;
/*  378 */             if (i < 2) this.tree = y;
/*  380 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  381 */               this.nodePath[(i - 2)].left = y;
/*      */             }
/*      */ 
/*  384 */             if (!y.succ()) break;
/*  385 */             y.succ(false);
/*  386 */             x.pred(y); break;
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  392 */           Entry y = this.nodePath[(i - 1)].left;
/*      */ 
/*  394 */           if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  395 */             this.nodePath[i].black(true);
/*  396 */             y.black(true);
/*  397 */             this.nodePath[(i - 1)].black(false);
/*  398 */             i -= 2;
/*      */           }
/*      */           else
/*      */           {
/*  403 */             if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*      */             } else {
/*  405 */               Entry x = this.nodePath[i];
/*  406 */               y = x.left;
/*  407 */               x.left = y.right;
/*  408 */               y.right = x;
/*  409 */               this.nodePath[(i - 1)].right = y;
/*      */ 
/*  411 */               if (y.succ()) {
/*  412 */                 y.succ(false);
/*  413 */                 x.pred(y);
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*  418 */             Entry x = this.nodePath[(i - 1)];
/*  419 */             x.black(false);
/*  420 */             y.black(true);
/*      */ 
/*  422 */             x.right = y.left;
/*  423 */             y.left = x;
/*  424 */             if (i < 2) this.tree = y;
/*  426 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  427 */               this.nodePath[(i - 2)].left = y;
/*      */             }
/*      */ 
/*  430 */             if (!y.pred()) break;
/*  431 */             y.pred(false);
/*  432 */             x.succ(y); break;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  440 */     this.tree.black(true);
/*      */ 
/*  442 */     while (maxDepth-- != 0) this.nodePath[maxDepth] = null;
/*      */ 
/*  447 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V remove(short k)
/*      */   {
/*  456 */     this.modified = false;
/*      */ 
/*  458 */     if (this.tree == null) return this.defRetValue;
/*      */ 
/*  460 */     Entry p = this.tree;
/*      */ 
/*  462 */     int i = 0;
/*  463 */     short kk = k;
/*      */     int cmp;
/*  466 */     while ((cmp = compare(kk, p.key)) != 0)
/*      */     {
/*  468 */       this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  469 */       this.nodePath[i] = p;
/*      */ 
/*  471 */       if (this.dirPath[(i++)] != 0) {
/*  472 */         if ((p = p.right()) == null)
/*      */         {
/*  474 */           while (i-- != 0) this.nodePath[i] = null;
/*  475 */           return this.defRetValue;
/*      */         }
/*      */ 
/*      */       }
/*  479 */       else if ((p = p.left()) == null)
/*      */       {
/*  481 */         while (i-- != 0) this.nodePath[i] = null;
/*  482 */         return this.defRetValue;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  488 */     if (p.left == null) this.firstEntry = p.next();
/*  489 */     if (p.right == null) this.lastEntry = p.prev();
/*      */ 
/*  491 */     if (p.succ()) {
/*  492 */       if (p.pred()) {
/*  493 */         if (i == 0) this.tree = p.left;
/*  495 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else
/*  496 */           this.nodePath[(i - 1)].pred(p.left);
/*      */       }
/*      */       else
/*      */       {
/*  500 */         p.prev().right = p.right;
/*      */ 
/*  502 */         if (i == 0) this.tree = p.left;
/*  504 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  505 */           this.nodePath[(i - 1)].left = p.left;
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  511 */       Entry r = p.right;
/*      */ 
/*  513 */       if (r.pred()) {
/*  514 */         r.left = p.left;
/*  515 */         r.pred(p.pred());
/*  516 */         if (!r.pred()) r.prev().right = r;
/*  517 */         if (i == 0) this.tree = r;
/*  519 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  520 */           this.nodePath[(i - 1)].left = r;
/*      */         }
/*      */ 
/*  523 */         boolean color = r.black();
/*  524 */         r.black(p.black());
/*  525 */         p.black(color);
/*  526 */         this.dirPath[i] = true;
/*  527 */         this.nodePath[(i++)] = r;
/*      */       }
/*      */       else {
/*  531 */         int j = i++;
/*      */         Entry s;
/*      */         while (true) {
/*  534 */           this.dirPath[i] = false;
/*  535 */           this.nodePath[(i++)] = r;
/*  536 */           s = r.left;
/*  537 */           if (s.pred()) break;
/*  538 */           r = s;
/*      */         }
/*      */ 
/*  541 */         this.dirPath[j] = true;
/*  542 */         this.nodePath[j] = s;
/*      */ 
/*  544 */         if (s.succ()) r.pred(s); else {
/*  545 */           r.left = s.right;
/*      */         }
/*  547 */         s.left = p.left;
/*      */ 
/*  549 */         if (!p.pred()) {
/*  550 */           p.prev().right = s;
/*  551 */           s.pred(false);
/*      */         }
/*      */ 
/*  554 */         s.right(p.right);
/*      */ 
/*  556 */         boolean color = s.black();
/*  557 */         s.black(p.black());
/*  558 */         p.black(color);
/*      */ 
/*  560 */         if (j == 0) this.tree = s;
/*  562 */         else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  563 */           this.nodePath[(j - 1)].left = s;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  568 */     int maxDepth = i;
/*      */ 
/*  570 */     if (p.black()) {
/*  571 */       for (; i > 0; i--) {
/*  572 */         if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*      */         {
/*  574 */           Entry x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*      */ 
/*  576 */           if (!x.black()) {
/*  577 */             x.black(true);
/*  578 */             break;
/*      */           }
/*      */         }
/*      */ 
/*  582 */         if (this.dirPath[(i - 1)] == 0) {
/*  583 */           Entry w = this.nodePath[(i - 1)].right;
/*      */ 
/*  585 */           if (!w.black()) {
/*  586 */             w.black(true);
/*  587 */             this.nodePath[(i - 1)].black(false);
/*      */ 
/*  589 */             this.nodePath[(i - 1)].right = w.left;
/*  590 */             w.left = this.nodePath[(i - 1)];
/*      */ 
/*  592 */             if (i < 2) this.tree = w;
/*  594 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  595 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  598 */             this.nodePath[i] = this.nodePath[(i - 1)];
/*  599 */             this.dirPath[i] = false;
/*  600 */             this.nodePath[(i - 1)] = w;
/*  601 */             if (maxDepth == i++) maxDepth++;
/*      */ 
/*  603 */             w = this.nodePath[(i - 1)].right;
/*      */           }
/*      */ 
/*  606 */           if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*      */           {
/*  608 */             w.black(false);
/*      */           }
/*      */           else {
/*  611 */             if ((w.succ()) || (w.right.black())) {
/*  612 */               Entry y = w.left;
/*      */ 
/*  614 */               y.black(true);
/*  615 */               w.black(false);
/*  616 */               w.left = y.right;
/*  617 */               y.right = w;
/*  618 */               w = this.nodePath[(i - 1)].right = y;
/*      */ 
/*  620 */               if (w.succ()) {
/*  621 */                 w.succ(false);
/*  622 */                 w.right.pred(w);
/*      */               }
/*      */             }
/*      */ 
/*  626 */             w.black(this.nodePath[(i - 1)].black());
/*  627 */             this.nodePath[(i - 1)].black(true);
/*  628 */             w.right.black(true);
/*      */ 
/*  630 */             this.nodePath[(i - 1)].right = w.left;
/*  631 */             w.left = this.nodePath[(i - 1)];
/*      */ 
/*  633 */             if (i < 2) this.tree = w;
/*  635 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  636 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  639 */             if (!w.pred()) break;
/*  640 */             w.pred(false);
/*  641 */             this.nodePath[(i - 1)].succ(w); break;
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  647 */           Entry w = this.nodePath[(i - 1)].left;
/*      */ 
/*  649 */           if (!w.black()) {
/*  650 */             w.black(true);
/*  651 */             this.nodePath[(i - 1)].black(false);
/*      */ 
/*  653 */             this.nodePath[(i - 1)].left = w.right;
/*  654 */             w.right = this.nodePath[(i - 1)];
/*      */ 
/*  656 */             if (i < 2) this.tree = w;
/*  658 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  659 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  662 */             this.nodePath[i] = this.nodePath[(i - 1)];
/*  663 */             this.dirPath[i] = true;
/*  664 */             this.nodePath[(i - 1)] = w;
/*  665 */             if (maxDepth == i++) maxDepth++;
/*      */ 
/*  667 */             w = this.nodePath[(i - 1)].left;
/*      */           }
/*      */ 
/*  670 */           if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*      */           {
/*  672 */             w.black(false);
/*      */           }
/*      */           else {
/*  675 */             if ((w.pred()) || (w.left.black())) {
/*  676 */               Entry y = w.right;
/*      */ 
/*  678 */               y.black(true);
/*  679 */               w.black(false);
/*  680 */               w.right = y.left;
/*  681 */               y.left = w;
/*  682 */               w = this.nodePath[(i - 1)].left = y;
/*      */ 
/*  684 */               if (w.pred()) {
/*  685 */                 w.pred(false);
/*  686 */                 w.left.succ(w);
/*      */               }
/*      */             }
/*      */ 
/*  690 */             w.black(this.nodePath[(i - 1)].black());
/*  691 */             this.nodePath[(i - 1)].black(true);
/*  692 */             w.left.black(true);
/*      */ 
/*  694 */             this.nodePath[(i - 1)].left = w.right;
/*  695 */             w.right = this.nodePath[(i - 1)];
/*      */ 
/*  697 */             if (i < 2) this.tree = w;
/*  699 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  700 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  703 */             if (!w.succ()) break;
/*  704 */             w.succ(false);
/*  705 */             this.nodePath[(i - 1)].pred(w); break;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  712 */       if (this.tree != null) this.tree.black(true);
/*      */     }
/*      */ 
/*  715 */     this.modified = true;
/*  716 */     this.count -= 1;
/*      */ 
/*  718 */     while (maxDepth-- != 0) this.nodePath[maxDepth] = null;
/*      */ 
/*  723 */     return p.value;
/*      */   }
/*      */ 
/*      */   public V put(Short ok, V ov)
/*      */   {
/*  730 */     Object oldValue = put(ok.shortValue(), ov);
/*  731 */     return this.modified ? this.defRetValue : oldValue;
/*      */   }
/*      */ 
/*      */   public V remove(Object ok)
/*      */   {
/*  737 */     Object oldValue = remove(((Short)ok).shortValue());
/*  738 */     return this.modified ? oldValue : this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsValue(Object v)
/*      */   {
/*  744 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  747 */     int j = this.count;
/*      */ 
/*  750 */     for (; j-- != 0; 
/*  750 */       return true)
/*      */     {
/*  749 */       label16: Object ev = i.next();
/*  750 */       if (ev == null ? v != null : !ev.equals(v))
/*      */         break label16;
/*      */     }
/*  753 */     return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  758 */     this.count = 0;
/*  759 */     this.tree = null;
/*  760 */     this.entries = null;
/*  761 */     this.values = null;
/*  762 */     this.keys = null;
/*  763 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public boolean containsKey(short k)
/*      */   {
/* 1006 */     return findKey(k) != null;
/*      */   }
/*      */   public int size() {
/* 1009 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/* 1012 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public V get(short k) {
/* 1016 */     Entry e = findKey(k);
/* 1017 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */   public short firstShortKey() {
/* 1020 */     if (this.tree == null) throw new NoSuchElementException();
/* 1021 */     return this.firstEntry.key;
/*      */   }
/*      */   public short lastShortKey() {
/* 1024 */     if (this.tree == null) throw new NoSuchElementException();
/* 1025 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
/*      */   {
/* 1117 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1118 */         final Comparator<? super Short2ObjectMap.Entry<V>> comparator = new Comparator() {
/*      */           public int compare(Short2ObjectMap.Entry<V> x, Short2ObjectMap.Entry<V> y) {
/* 1120 */             return Short2ObjectRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/* 1118 */         };
/*      */ 
/*      */         public Comparator<? super Short2ObjectMap.Entry<V>> comparator()
/*      */         {
/* 1124 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator() {
/* 1127 */           return new Short2ObjectRBTreeMap.EntryIterator(Short2ObjectRBTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator(Short2ObjectMap.Entry<V> from) {
/* 1130 */           return new Short2ObjectRBTreeMap.EntryIterator(Short2ObjectRBTreeMap.this, ((Short)from.getKey()).shortValue());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/* 1134 */           if (!(o instanceof Map.Entry)) return false;
/* 1135 */           Map.Entry e = (Map.Entry)o;
/* 1136 */           Short2ObjectRBTreeMap.Entry f = Short2ObjectRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1137 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/* 1141 */           if (!(o instanceof Map.Entry)) return false;
/* 1142 */           Map.Entry e = (Map.Entry)o;
/* 1143 */           Short2ObjectRBTreeMap.Entry f = Short2ObjectRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1144 */           if (f != null) Short2ObjectRBTreeMap.this.remove(f.key);
/* 1145 */           return f != null;
/*      */         }
/* 1147 */         public int size() { return Short2ObjectRBTreeMap.this.count; } 
/* 1148 */         public void clear() { Short2ObjectRBTreeMap.this.clear(); } 
/* 1149 */         public Short2ObjectMap.Entry<V> first() { return Short2ObjectRBTreeMap.this.firstEntry; } 
/* 1150 */         public Short2ObjectMap.Entry<V> last() { return Short2ObjectRBTreeMap.this.lastEntry; } 
/* 1151 */         public ObjectSortedSet<Short2ObjectMap.Entry<V>> subSet(Short2ObjectMap.Entry<V> from, Short2ObjectMap.Entry<V> to) { return Short2ObjectRBTreeMap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2ObjectEntrySet(); } 
/* 1152 */         public ObjectSortedSet<Short2ObjectMap.Entry<V>> headSet(Short2ObjectMap.Entry<V> to) { return Short2ObjectRBTreeMap.this.headMap((Short)to.getKey()).short2ObjectEntrySet(); } 
/* 1153 */         public ObjectSortedSet<Short2ObjectMap.Entry<V>> tailSet(Short2ObjectMap.Entry<V> from) { return Short2ObjectRBTreeMap.this.tailMap((Short)from.getKey()).short2ObjectEntrySet(); }
/*      */       };
/* 1155 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ShortSortedSet keySet()
/*      */   {
/* 1189 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1190 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ObjectCollection<V> values()
/*      */   {
/* 1213 */     if (this.values == null) this.values = new AbstractObjectCollection() {
/*      */         public ObjectIterator<V> iterator() {
/* 1215 */           return new Short2ObjectRBTreeMap.ValueIterator(Short2ObjectRBTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(Object k) {
/* 1218 */           return Short2ObjectRBTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1221 */           return Short2ObjectRBTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1224 */           Short2ObjectRBTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1227 */     return this.values;
/*      */   }
/*      */   public ShortComparator comparator() {
/* 1230 */     return this.actualComparator;
/*      */   }
/*      */   public Short2ObjectSortedMap<V> headMap(short to) {
/* 1233 */     return new Submap((short)0, true, to, false);
/*      */   }
/*      */   public Short2ObjectSortedMap<V> tailMap(short from) {
/* 1236 */     return new Submap(from, false, (short)0, true);
/*      */   }
/*      */   public Short2ObjectSortedMap<V> subMap(short from, short to) {
/* 1239 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Short2ObjectRBTreeMap<V> clone()
/*      */   {
/*      */     Short2ObjectRBTreeMap c;
/*      */     try
/*      */     {
/* 1580 */       c = (Short2ObjectRBTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1583 */       throw new InternalError();
/*      */     }
/* 1585 */     c.keys = null;
/* 1586 */     c.values = null;
/* 1587 */     c.entries = null;
/* 1588 */     c.allocatePaths();
/* 1589 */     if (this.count != 0)
/*      */     {
/* 1591 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1592 */       Entry p = rp;
/* 1593 */       rp.left(this.tree);
/* 1594 */       Entry q = rq;
/* 1595 */       rq.pred(null);
/*      */       while (true) {
/* 1597 */         if (!p.pred()) {
/* 1598 */           Entry e = p.left.clone();
/* 1599 */           e.pred(q.left);
/* 1600 */           e.succ(q);
/* 1601 */           q.left(e);
/* 1602 */           p = p.left;
/* 1603 */           q = q.left;
/*      */         }
/*      */         else {
/* 1606 */           while (p.succ()) {
/* 1607 */             p = p.right;
/* 1608 */             if (p == null) {
/* 1609 */               q.right = null;
/* 1610 */               c.tree = rq.left;
/* 1611 */               c.firstEntry = c.tree;
/* 1612 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1613 */               c.lastEntry = c.tree;
/* 1614 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1615 */               return c;
/*      */             }
/* 1617 */             q = q.right;
/*      */           }
/* 1619 */           p = p.right;
/* 1620 */           q = q.right;
/*      */         }
/* 1622 */         if (!p.succ()) {
/* 1623 */           Entry e = p.right.clone();
/* 1624 */           e.succ(q.right);
/* 1625 */           e.pred(q);
/* 1626 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1630 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1633 */     int n = this.count;
/* 1634 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1636 */     s.defaultWriteObject();
/* 1637 */     while (n-- != 0) {
/* 1638 */       Entry e = i.nextEntry();
/* 1639 */       s.writeShort(e.key);
/* 1640 */       s.writeObject(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1652 */     if (n == 1) {
/* 1653 */       Entry top = new Entry(s.readShort(), s.readObject());
/* 1654 */       top.pred(pred);
/* 1655 */       top.succ(succ);
/* 1656 */       top.black(true);
/* 1657 */       return top;
/*      */     }
/* 1659 */     if (n == 2)
/*      */     {
/* 1662 */       Entry top = new Entry(s.readShort(), s.readObject());
/* 1663 */       top.black(true);
/* 1664 */       top.right(new Entry(s.readShort(), s.readObject()));
/* 1665 */       top.right.pred(top);
/* 1666 */       top.pred(pred);
/* 1667 */       top.right.succ(succ);
/* 1668 */       return top;
/*      */     }
/*      */ 
/* 1671 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1672 */     Entry top = new Entry();
/* 1673 */     top.left(readTree(s, leftN, pred, top));
/* 1674 */     top.key = s.readShort();
/* 1675 */     top.value = s.readObject();
/* 1676 */     top.black(true);
/* 1677 */     top.right(readTree(s, rightN, top, succ));
/* 1678 */     if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1679 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1682 */     s.defaultReadObject();
/*      */ 
/* 1685 */     setActualComparator();
/* 1686 */     allocatePaths();
/* 1687 */     if (this.count != 0) {
/* 1688 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1690 */       Entry e = this.tree;
/* 1691 */       while (e.left() != null) e = e.left();
/* 1692 */       this.firstEntry = e;
/* 1693 */       e = this.tree;
/* 1694 */       while (e.right() != null) e = e.right();
/* 1695 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */   private void checkNodePath() {
/*      */   }
/*      */ 
/* 1701 */   private int checkTree(Entry<V> e, int d, int D) { return 0; }
/*      */ 
/*      */ 
/*      */   private final class Submap extends AbstractShort2ObjectSortedMap<V>
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     short from;
/*      */     short to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Short2ObjectMap.Entry<V>> entries;
/*      */     protected volatile transient ShortSortedSet keys;
/*      */     protected volatile transient ObjectCollection<V> values;
/*      */ 
/*      */     public Submap(short from, boolean bottom, short to, boolean top)
/*      */     {
/* 1277 */       if ((!bottom) && (!top) && (Short2ObjectRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1278 */       this.from = from;
/* 1279 */       this.bottom = bottom;
/* 1280 */       this.to = to;
/* 1281 */       this.top = top;
/* 1282 */       this.defRetValue = Short2ObjectRBTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1285 */       SubmapIterator i = new SubmapIterator();
/* 1286 */       while (i.hasNext()) {
/* 1287 */         i.nextEntry();
/* 1288 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(short k)
/*      */     {
/* 1296 */       return ((this.bottom) || (Short2ObjectRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Short2ObjectRBTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet() {
/* 1300 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator() {
/* 1302 */             return new Short2ObjectRBTreeMap.Submap.SubmapEntryIterator(Short2ObjectRBTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator(Short2ObjectMap.Entry<V> from) {
/* 1305 */             return new Short2ObjectRBTreeMap.Submap.SubmapEntryIterator(Short2ObjectRBTreeMap.Submap.this, ((Short)from.getKey()).shortValue());
/*      */           }
/* 1307 */           public Comparator<? super Short2ObjectMap.Entry<V>> comparator() { return Short2ObjectRBTreeMap.this.short2ObjectEntrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1310 */             if (!(o instanceof Map.Entry)) return false;
/* 1311 */             Map.Entry e = (Map.Entry)o;
/* 1312 */             Short2ObjectRBTreeMap.Entry f = Short2ObjectRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1313 */             return (f != null) && (Short2ObjectRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1317 */             if (!(o instanceof Map.Entry)) return false;
/* 1318 */             Map.Entry e = (Map.Entry)o;
/* 1319 */             Short2ObjectRBTreeMap.Entry f = Short2ObjectRBTreeMap.this.findKey(((Short)e.getKey()).shortValue());
/* 1320 */             if ((f != null) && (Short2ObjectRBTreeMap.Submap.this.in(f.key))) Short2ObjectRBTreeMap.Submap.this.remove(f.key);
/* 1321 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1324 */             int c = 0;
/* 1325 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1326 */             return c;
/*      */           }
/* 1328 */           public boolean isEmpty() { return !new Short2ObjectRBTreeMap.Submap.SubmapIterator(Short2ObjectRBTreeMap.Submap.this).hasNext(); } 
/* 1329 */           public void clear() { Short2ObjectRBTreeMap.Submap.this.clear(); } 
/* 1330 */           public Short2ObjectMap.Entry<V> first() { return Short2ObjectRBTreeMap.Submap.this.firstEntry(); } 
/* 1331 */           public Short2ObjectMap.Entry<V> last() { return Short2ObjectRBTreeMap.Submap.this.lastEntry(); } 
/* 1332 */           public ObjectSortedSet<Short2ObjectMap.Entry<V>> subSet(Short2ObjectMap.Entry<V> from, Short2ObjectMap.Entry<V> to) { return Short2ObjectRBTreeMap.Submap.this.subMap((Short)from.getKey(), (Short)to.getKey()).short2ObjectEntrySet(); } 
/* 1333 */           public ObjectSortedSet<Short2ObjectMap.Entry<V>> headSet(Short2ObjectMap.Entry<V> to) { return Short2ObjectRBTreeMap.Submap.this.headMap((Short)to.getKey()).short2ObjectEntrySet(); } 
/* 1334 */           public ObjectSortedSet<Short2ObjectMap.Entry<V>> tailSet(Short2ObjectMap.Entry<V> from) { return Short2ObjectRBTreeMap.Submap.this.tailMap((Short)from.getKey()).short2ObjectEntrySet(); }
/*      */         };
/* 1336 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public ShortSortedSet keySet()
/*      */     {
/* 1343 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1344 */       return this.keys;
/*      */     }
/*      */     public ObjectCollection<V> values() {
/* 1347 */       if (this.values == null) this.values = new AbstractObjectCollection() {
/*      */           public ObjectIterator<V> iterator() {
/* 1349 */             return new Short2ObjectRBTreeMap.Submap.SubmapValueIterator(Short2ObjectRBTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(Object k) {
/* 1352 */             return Short2ObjectRBTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1355 */             return Short2ObjectRBTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1358 */             Short2ObjectRBTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1361 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(short k) {
/* 1365 */       return (in(k)) && (Short2ObjectRBTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(Object v) {
/* 1368 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1372 */       for (; i.hasNext(); 
/* 1372 */         return true)
/*      */       {
/* 1371 */         label9: Object ev = i.nextEntry().value;
/* 1372 */         if (ev == null ? v != null : !ev.equals(v)) break label9;
/*      */       }
/* 1374 */       return false;
/*      */     }
/*      */ 
/*      */     public V get(short k)
/*      */     {
/* 1379 */       short kk = k;
/*      */       Short2ObjectRBTreeMap.Entry e;
/* 1380 */       return (in(kk)) && ((e = Short2ObjectRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */     public V put(short k, V v) {
/* 1383 */       Short2ObjectRBTreeMap.this.modified = false;
/* 1384 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1385 */       Object oldValue = Short2ObjectRBTreeMap.this.put(k, v);
/* 1386 */       return Short2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */     public V put(Short ok, V ov) {
/* 1389 */       Object oldValue = put(ok.shortValue(), ov);
/* 1390 */       return Short2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */ 
/*      */     public V remove(short k) {
/* 1394 */       Short2ObjectRBTreeMap.this.modified = false;
/* 1395 */       if (!in(k)) return this.defRetValue;
/* 1396 */       Object oldValue = Short2ObjectRBTreeMap.this.remove(k);
/* 1397 */       return Short2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public V remove(Object ok) {
/* 1400 */       Object oldValue = remove(((Short)ok).shortValue());
/* 1401 */       return Short2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public int size() {
/* 1404 */       SubmapIterator i = new SubmapIterator();
/* 1405 */       int n = 0;
/* 1406 */       while (i.hasNext()) {
/* 1407 */         n++;
/* 1408 */         i.nextEntry();
/*      */       }
/* 1410 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1413 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public ShortComparator comparator() {
/* 1416 */       return Short2ObjectRBTreeMap.this.actualComparator;
/*      */     }
/*      */     public Short2ObjectSortedMap<V> headMap(short to) {
/* 1419 */       if (this.top) return new Submap(Short2ObjectRBTreeMap.this, this.from, this.bottom, to, false);
/* 1420 */       return Short2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Short2ObjectRBTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Short2ObjectSortedMap<V> tailMap(short from) {
/* 1423 */       if (this.bottom) return new Submap(Short2ObjectRBTreeMap.this, from, false, this.to, this.top);
/* 1424 */       return Short2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Short2ObjectRBTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Short2ObjectSortedMap<V> subMap(short from, short to) {
/* 1427 */       if ((this.top) && (this.bottom)) return new Submap(Short2ObjectRBTreeMap.this, from, false, to, false);
/* 1428 */       if (!this.top) to = Short2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1429 */       if (!this.bottom) from = Short2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1430 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1431 */       return new Submap(Short2ObjectRBTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Short2ObjectRBTreeMap.Entry<V> firstEntry()
/*      */     {
/* 1438 */       if (Short2ObjectRBTreeMap.this.tree == null) return null;
/* 1441 */       Short2ObjectRBTreeMap.Entry e;
/*      */       Short2ObjectRBTreeMap.Entry e;
/* 1441 */       if (this.bottom) { e = Short2ObjectRBTreeMap.this.firstEntry;
/*      */       } else {
/* 1443 */         e = Short2ObjectRBTreeMap.this.locateKey(this.from);
/*      */ 
/* 1445 */         if (Short2ObjectRBTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1448 */       if ((e == null) || ((!this.top) && (Short2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1449 */       return e;
/*      */     }
/*      */ 
/*      */     public Short2ObjectRBTreeMap.Entry<V> lastEntry()
/*      */     {
/* 1456 */       if (Short2ObjectRBTreeMap.this.tree == null) return null;
/* 1459 */       Short2ObjectRBTreeMap.Entry e;
/*      */       Short2ObjectRBTreeMap.Entry e;
/* 1459 */       if (this.top) { e = Short2ObjectRBTreeMap.this.lastEntry;
/*      */       } else {
/* 1461 */         e = Short2ObjectRBTreeMap.this.locateKey(this.to);
/*      */ 
/* 1463 */         if (Short2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1466 */       if ((e == null) || ((!this.bottom) && (Short2ObjectRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1467 */       return e;
/*      */     }
/*      */     public short firstShortKey() {
/* 1470 */       Short2ObjectRBTreeMap.Entry e = firstEntry();
/* 1471 */       if (e == null) throw new NoSuchElementException();
/* 1472 */       return e.key;
/*      */     }
/*      */     public short lastShortKey() {
/* 1475 */       Short2ObjectRBTreeMap.Entry e = lastEntry();
/* 1476 */       if (e == null) throw new NoSuchElementException();
/* 1477 */       return e.key;
/*      */     }
/*      */     public Short firstKey() {
/* 1480 */       Short2ObjectRBTreeMap.Entry e = firstEntry();
/* 1481 */       if (e == null) throw new NoSuchElementException();
/* 1482 */       return e.getKey();
/*      */     }
/*      */     public Short lastKey() {
/* 1485 */       Short2ObjectRBTreeMap.Entry e = lastEntry();
/* 1486 */       if (e == null) throw new NoSuchElementException();
/* 1487 */       return e.getKey();
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Short2ObjectRBTreeMap<V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<V>
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1562 */         super(); } 
/* 1563 */       public V next() { return nextEntry().value; } 
/* 1564 */       public V previous() { return previousEntry().value; } 
/* 1565 */       public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1566 */       public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Short2ObjectRBTreeMap.Submap.SubmapIterator
/*      */       implements ShortListIterator
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1543 */         super(); } 
/* 1544 */       public SubmapKeyIterator(short from) { super(from); } 
/* 1545 */       public short nextShort() { return nextEntry().key; } 
/* 1546 */       public short previousShort() { return previousEntry().key; } 
/* 1547 */       public void set(short k) { throw new UnsupportedOperationException(); } 
/* 1548 */       public void add(short k) { throw new UnsupportedOperationException(); } 
/* 1549 */       public Short next() { return Short.valueOf(nextEntry().key); } 
/* 1550 */       public Short previous() { return Short.valueOf(previousEntry().key); } 
/* 1551 */       public void set(Short ok) { throw new UnsupportedOperationException(); } 
/* 1552 */       public void add(Short ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Short2ObjectRBTreeMap<V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Short2ObjectMap.Entry<V>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1525 */         super();
/*      */       }
/* 1527 */       SubmapEntryIterator(short k) { super(k); } 
/*      */       public Short2ObjectMap.Entry<V> next() {
/* 1529 */         return nextEntry(); } 
/* 1530 */       public Short2ObjectMap.Entry<V> previous() { return previousEntry(); } 
/* 1531 */       public void set(Short2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); } 
/* 1532 */       public void add(Short2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Short2ObjectRBTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1497 */         super();
/* 1498 */         this.next = Short2ObjectRBTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator(short k) {
/* 1501 */         this();
/* 1502 */         if (this.next != null)
/* 1503 */           if ((!Short2ObjectRBTreeMap.Submap.this.bottom) && (Short2ObjectRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1504 */           } else if ((!Short2ObjectRBTreeMap.Submap.this.top) && (Short2ObjectRBTreeMap.this.compare(k, (this.prev = Short2ObjectRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1506 */             this.next = Short2ObjectRBTreeMap.this.locateKey(k);
/* 1507 */             if (Short2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1508 */               this.prev = this.next;
/* 1509 */               this.next = this.next.next();
/*      */             } else {
/* 1511 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1516 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1517 */         if ((!Short2ObjectRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Short2ObjectRBTreeMap.this.compare(this.prev.key, Short2ObjectRBTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1520 */         this.next = this.next.next();
/* 1521 */         if ((!Short2ObjectRBTreeMap.Submap.this.top) && (this.next != null) && (Short2ObjectRBTreeMap.this.compare(this.next.key, Short2ObjectRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractShort2ObjectSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1338 */         super(); } 
/* 1339 */       public ShortBidirectionalIterator iterator() { return new Short2ObjectRBTreeMap.Submap.SubmapKeyIterator(Short2ObjectRBTreeMap.Submap.this); } 
/* 1340 */       public ShortBidirectionalIterator iterator(short from) { return new Short2ObjectRBTreeMap.Submap.SubmapKeyIterator(Short2ObjectRBTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Short2ObjectRBTreeMap<V>.TreeIterator
/*      */     implements ObjectListIterator<V>
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1198 */       super(); } 
/* 1199 */     public V next() { return nextEntry().value; } 
/* 1200 */     public V previous() { return previousEntry().value; } 
/* 1201 */     public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1202 */     public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractShort2ObjectSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1176 */       super(); } 
/* 1177 */     public ShortBidirectionalIterator iterator() { return new Short2ObjectRBTreeMap.KeyIterator(Short2ObjectRBTreeMap.this); } 
/* 1178 */     public ShortBidirectionalIterator iterator(short from) { return new Short2ObjectRBTreeMap.KeyIterator(Short2ObjectRBTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Short2ObjectRBTreeMap.TreeIterator
/*      */     implements ShortListIterator
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1164 */       super(); } 
/* 1165 */     public KeyIterator(short k) { super(k); } 
/* 1166 */     public short nextShort() { return nextEntry().key; } 
/* 1167 */     public short previousShort() { return previousEntry().key; } 
/* 1168 */     public void set(short k) { throw new UnsupportedOperationException(); } 
/* 1169 */     public void add(short k) { throw new UnsupportedOperationException(); } 
/* 1170 */     public Short next() { return Short.valueOf(nextEntry().key); } 
/* 1171 */     public Short previous() { return Short.valueOf(previousEntry().key); } 
/* 1172 */     public void set(Short ok) { throw new UnsupportedOperationException(); } 
/* 1173 */     public void add(Short ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Short2ObjectRBTreeMap<V>.TreeIterator
/*      */     implements ObjectListIterator<Short2ObjectMap.Entry<V>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/* 1107 */       super();
/*      */     }
/* 1109 */     EntryIterator(short k) { super(k); } 
/*      */     public Short2ObjectMap.Entry<V> next() {
/* 1111 */       return nextEntry(); } 
/* 1112 */     public Short2ObjectMap.Entry<V> previous() { return previousEntry(); } 
/* 1113 */     public void set(Short2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); } 
/* 1114 */     public void add(Short2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Short2ObjectRBTreeMap.Entry<V> prev;
/*      */     Short2ObjectRBTreeMap.Entry<V> next;
/*      */     Short2ObjectRBTreeMap.Entry<V> curr;
/* 1039 */     int index = 0;
/*      */ 
/* 1041 */     TreeIterator() { this.next = Short2ObjectRBTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator(short k) {
/* 1044 */       if ((this.next = Short2ObjectRBTreeMap.this.locateKey(k)) != null)
/* 1045 */         if (Short2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1046 */           this.prev = this.next;
/* 1047 */           this.next = this.next.next();
/*      */         } else {
/* 1049 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/* 1052 */     public boolean hasNext() { return this.next != null; } 
/* 1053 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/* 1055 */       this.next = this.next.next();
/*      */     }
/*      */     Short2ObjectRBTreeMap.Entry<V> nextEntry() {
/* 1058 */       if (!hasNext()) throw new NoSuchElementException();
/* 1059 */       this.curr = (this.prev = this.next);
/* 1060 */       this.index += 1;
/* 1061 */       updateNext();
/* 1062 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/* 1065 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Short2ObjectRBTreeMap.Entry<V> previousEntry() {
/* 1068 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 1069 */       this.curr = (this.next = this.prev);
/* 1070 */       this.index -= 1;
/* 1071 */       updatePrevious();
/* 1072 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/* 1075 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/* 1078 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/* 1081 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/* 1084 */       if (this.curr == this.prev) this.index -= 1;
/* 1085 */       this.next = (this.prev = this.curr);
/* 1086 */       updatePrevious();
/* 1087 */       updateNext();
/* 1088 */       Short2ObjectRBTreeMap.this.remove(this.curr.key);
/* 1089 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/* 1092 */       int i = n;
/* 1093 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 1094 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/* 1097 */       int i = n;
/* 1098 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1099 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry<V>
/*      */     implements Cloneable, Short2ObjectMap.Entry<V>
/*      */   {
/*      */     private static final int BLACK_MASK = 1;
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     short key;
/*      */     V value;
/*      */     Entry<V> left;
/*      */     Entry<V> right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(short k, V v)
/*      */     {
/*  800 */       this.key = k;
/*  801 */       this.value = v;
/*  802 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry<V> left()
/*      */     {
/*  811 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry<V> right()
/*      */     {
/*  820 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  827 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  834 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  841 */       if (pred) this.info |= 1073741824; else
/*  842 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  849 */       if (succ) this.info |= -2147483648; else
/*  850 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry<V> pred)
/*      */     {
/*  857 */       this.info |= 1073741824;
/*  858 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry<V> succ)
/*      */     {
/*  865 */       this.info |= -2147483648;
/*  866 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry<V> left)
/*      */     {
/*  873 */       this.info &= -1073741825;
/*  874 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry<V> right)
/*      */     {
/*  881 */       this.info &= 2147483647;
/*  882 */       this.right = right;
/*      */     }
/*      */ 
/*      */     boolean black()
/*      */     {
/*  890 */       return (this.info & 0x1) != 0;
/*      */     }
/*      */ 
/*      */     void black(boolean black)
/*      */     {
/*  897 */       if (black) this.info |= 1; else
/*  898 */         this.info &= -2;
/*      */     }
/*      */ 
/*      */     Entry<V> next()
/*      */     {
/*  907 */       Entry next = this.right;
/*  908 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  909 */       return next;
/*      */     }
/*      */ 
/*      */     Entry<V> prev()
/*      */     {
/*  918 */       Entry prev = this.left;
/*  919 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  920 */       return prev;
/*      */     }
/*      */ 
/*      */     public Short getKey() {
/*  924 */       return Short.valueOf(this.key);
/*      */     }
/*      */ 
/*      */     public short getShortKey()
/*      */     {
/*  929 */       return this.key;
/*      */     }
/*      */ 
/*      */     public V getValue()
/*      */     {
/*  934 */       return this.value;
/*      */     }
/*      */ 
/*      */     public V setValue(V value)
/*      */     {
/*  944 */       Object oldValue = this.value;
/*  945 */       this.value = value;
/*  946 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public Entry<V> clone() {
/*      */       Entry c;
/*      */       try {
/*  952 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  955 */         throw new InternalError();
/*      */       }
/*  957 */       c.key = this.key;
/*  958 */       c.value = this.value;
/*  959 */       c.info = this.info;
/*  960 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  964 */       if (!(o instanceof Map.Entry)) return false;
/*  965 */       Map.Entry e = (Map.Entry)o;
/*  966 */       return (this.key == ((Short)e.getKey()).shortValue()) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
/*      */     }
/*      */     public int hashCode() {
/*  969 */       return this.key ^ (this.value == null ? 0 : this.value.hashCode());
/*      */     }
/*      */     public String toString() {
/*  972 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectRBTreeMap
 * JD-Core Version:    0.6.2
 */