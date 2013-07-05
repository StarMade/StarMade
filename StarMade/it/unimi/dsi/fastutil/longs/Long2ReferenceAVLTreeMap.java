/*      */ package it.unimi.dsi.fastutil.longs;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
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
/*      */ public class Long2ReferenceAVLTreeMap<V> extends AbstractLong2ReferenceSortedMap<V>
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry<V> tree;
/*      */   protected int count;
/*      */   protected transient Entry<V> firstEntry;
/*      */   protected transient Entry<V> lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Long2ReferenceMap.Entry<V>> entries;
/*      */   protected volatile transient LongSortedSet keys;
/*      */   protected volatile transient ReferenceCollection<V> values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super Long> storedComparator;
/*      */   protected transient LongComparator actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap()
/*      */   {
/*   91 */     allocatePaths();
/*      */ 
/*   96 */     this.tree = null;
/*   97 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  114 */     if ((this.storedComparator == null) || ((this.storedComparator instanceof LongComparator))) this.actualComparator = ((LongComparator)this.storedComparator); else
/*  115 */       this.actualComparator = new LongComparator() {
/*      */         public int compare(long k1, long k2) {
/*  117 */           return Long2ReferenceAVLTreeMap.this.storedComparator.compare(Long.valueOf(k1), Long.valueOf(k2));
/*      */         }
/*      */         public int compare(Long ok1, Long ok2) {
/*  120 */           return Long2ReferenceAVLTreeMap.this.storedComparator.compare(ok1, ok2);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(Comparator<? super Long> c)
/*      */   {
/*  133 */     this();
/*  134 */     this.storedComparator = c;
/*  135 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(Map<? extends Long, ? extends V> m)
/*      */   {
/*  145 */     this();
/*  146 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(SortedMap<Long, V> m)
/*      */   {
/*  155 */     this(m.comparator());
/*  156 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(Long2ReferenceMap<? extends V> m)
/*      */   {
/*  165 */     this();
/*  166 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(Long2ReferenceSortedMap<V> m)
/*      */   {
/*  175 */     this(m.comparator());
/*  176 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(long[] k, V[] v, Comparator<? super Long> c)
/*      */   {
/*  188 */     this(c);
/*  189 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  190 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap(long[] k, V[] v)
/*      */   {
/*  201 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(long k1, long k2)
/*      */   {
/*  228 */     return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<V> findKey(long k)
/*      */   {
/*  240 */     Entry e = this.tree;
/*      */     int cmp;
/*  243 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*      */ 
/*  245 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<V> locateKey(long k)
/*      */   {
/*  256 */     Entry e = this.tree; Entry last = this.tree;
/*  257 */     int cmp = 0;
/*      */ 
/*  259 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  260 */       last = e;
/*  261 */       e = cmp < 0 ? e.left() : e.right();
/*      */     }
/*      */ 
/*  264 */     return cmp == 0 ? e : last;
/*      */   }
/*      */ 
/*      */   private void allocatePaths()
/*      */   {
/*  272 */     this.dirPath = new boolean[48];
/*      */   }
/*      */ 
/*      */   public V put(long k, V v)
/*      */   {
/*  280 */     this.modified = false;
/*      */ 
/*  282 */     if (this.tree == null) {
/*  283 */       this.count += 1;
/*  284 */       this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  285 */       this.modified = true;
/*      */     }
/*      */     else {
/*  288 */       Entry p = this.tree; Entry q = null; Entry y = this.tree; Entry z = null; Entry e = null; Entry w = null;
/*  289 */       int i = 0;
/*      */       while (true)
/*      */       {
/*      */         int cmp;
/*  292 */         if ((cmp = compare(k, p.key)) == 0) {
/*  293 */           Object oldValue = p.value;
/*  294 */           p.value = v;
/*  295 */           return oldValue;
/*      */         }
/*      */ 
/*  298 */         if (p.balance() != 0) {
/*  299 */           i = 0;
/*  300 */           z = q;
/*  301 */           y = p;
/*      */         }
/*      */ 
/*  304 */         if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  305 */           if (p.succ()) {
/*  306 */             this.count += 1;
/*  307 */             e = new Entry(k, v);
/*      */ 
/*  309 */             this.modified = true;
/*  310 */             if (p.right == null) this.lastEntry = e;
/*      */ 
/*  312 */             e.left = p;
/*  313 */             e.right = p.right;
/*      */ 
/*  315 */             p.right(e);
/*      */ 
/*  317 */             break;
/*      */           }
/*      */ 
/*  320 */           q = p;
/*  321 */           p = p.right;
/*      */         }
/*      */         else {
/*  324 */           if (p.pred()) {
/*  325 */             this.count += 1;
/*  326 */             e = new Entry(k, v);
/*      */ 
/*  328 */             this.modified = true;
/*  329 */             if (p.left == null) this.firstEntry = e;
/*      */ 
/*  331 */             e.right = p;
/*  332 */             e.left = p.left;
/*      */ 
/*  334 */             p.left(e);
/*      */ 
/*  336 */             break;
/*      */           }
/*      */ 
/*  339 */           q = p;
/*  340 */           p = p.left;
/*      */         }
/*      */       }
/*      */ 
/*  344 */       p = y;
/*  345 */       i = 0;
/*      */ 
/*  347 */       while (p != e) {
/*  348 */         if (this.dirPath[i] != 0) p.incBalance(); else {
/*  349 */           p.decBalance();
/*      */         }
/*  351 */         p = this.dirPath[(i++)] != 0 ? p.right : p.left;
/*      */       }
/*      */ 
/*  354 */       if (y.balance() == -2) {
/*  355 */         Entry x = y.left;
/*      */ 
/*  357 */         if (x.balance() == -1) {
/*  358 */           w = x;
/*  359 */           if (x.succ()) {
/*  360 */             x.succ(false);
/*  361 */             y.pred(x);
/*      */           } else {
/*  363 */             y.left = x.right;
/*      */           }
/*  365 */           x.right = y;
/*  366 */           x.balance(0);
/*  367 */           y.balance(0);
/*      */         }
/*      */         else
/*      */         {
/*  372 */           w = x.right;
/*  373 */           x.right = w.left;
/*  374 */           w.left = x;
/*  375 */           y.left = w.right;
/*  376 */           w.right = y;
/*  377 */           if (w.balance() == -1) {
/*  378 */             x.balance(0);
/*  379 */             y.balance(1);
/*      */           }
/*  381 */           else if (w.balance() == 0) {
/*  382 */             x.balance(0);
/*  383 */             y.balance(0);
/*      */           }
/*      */           else {
/*  386 */             x.balance(-1);
/*  387 */             y.balance(0);
/*      */           }
/*  389 */           w.balance(0);
/*      */ 
/*  392 */           if (w.pred()) {
/*  393 */             x.succ(w);
/*  394 */             w.pred(false);
/*      */           }
/*  396 */           if (w.succ()) {
/*  397 */             y.pred(w);
/*  398 */             w.succ(false);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*  403 */       else if (y.balance() == 2) {
/*  404 */         Entry x = y.right;
/*      */ 
/*  406 */         if (x.balance() == 1) {
/*  407 */           w = x;
/*  408 */           if (x.pred()) {
/*  409 */             x.pred(false);
/*  410 */             y.succ(x);
/*      */           } else {
/*  412 */             y.right = x.left;
/*      */           }
/*  414 */           x.left = y;
/*  415 */           x.balance(0);
/*  416 */           y.balance(0);
/*      */         }
/*      */         else
/*      */         {
/*  421 */           w = x.left;
/*  422 */           x.left = w.right;
/*  423 */           w.right = x;
/*  424 */           y.right = w.left;
/*  425 */           w.left = y;
/*  426 */           if (w.balance() == 1) {
/*  427 */             x.balance(0);
/*  428 */             y.balance(-1);
/*      */           }
/*  430 */           else if (w.balance() == 0) {
/*  431 */             x.balance(0);
/*  432 */             y.balance(0);
/*      */           }
/*      */           else {
/*  435 */             x.balance(1);
/*  436 */             y.balance(0);
/*      */           }
/*  438 */           w.balance(0);
/*      */ 
/*  441 */           if (w.pred()) {
/*  442 */             y.succ(w);
/*  443 */             w.pred(false);
/*      */           }
/*  445 */           if (w.succ()) {
/*  446 */             x.pred(w);
/*  447 */             w.succ(false);
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/*  452 */         return this.defRetValue;
/*      */       }
/*  454 */       if (z == null) this.tree = w;
/*  456 */       else if (z.left == y) z.left = w; else {
/*  457 */         z.right = w;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  462 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   private Entry<V> parent(Entry<V> e)
/*      */   {
/*  472 */     if (e == this.tree) return null;
/*      */     Entry y;
/*  475 */     Entry x = y = e;
/*      */     while (true)
/*      */     {
/*  478 */       if (y.succ()) {
/*  479 */         Entry p = y.right;
/*  480 */         if ((p == null) || (p.left != e)) {
/*  481 */           while (!x.pred()) x = x.left;
/*  482 */           p = x.left;
/*      */         }
/*  484 */         return p;
/*      */       }
/*  486 */       if (x.pred()) {
/*  487 */         Entry p = x.left;
/*  488 */         if ((p == null) || (p.right != e)) {
/*  489 */           while (!y.succ()) y = y.right;
/*  490 */           p = y.right;
/*      */         }
/*  492 */         return p;
/*      */       }
/*      */ 
/*  495 */       x = x.left;
/*  496 */       y = y.right;
/*      */     }
/*      */   }
/*      */ 
/*      */   public V remove(long k)
/*      */   {
/*  506 */     this.modified = false;
/*      */ 
/*  508 */     if (this.tree == null) return this.defRetValue;
/*      */ 
/*  511 */     Entry p = this.tree; Entry q = null;
/*  512 */     boolean dir = false;
/*  513 */     long kk = k;
/*      */     int cmp;
/*  516 */     while ((cmp = compare(kk, p.key)) != 0) {
/*  517 */       if ((dir = cmp > 0 ? 1 : 0) != 0) {
/*  518 */         q = p;
/*  519 */         if ((p = p.right()) == null) return this.defRetValue; 
/*      */       }
/*      */       else
/*      */       {
/*  522 */         q = p;
/*  523 */         if ((p = p.left()) == null) return this.defRetValue;
/*      */       }
/*      */     }
/*      */ 
/*  527 */     if (p.left == null) this.firstEntry = p.next();
/*  528 */     if (p.right == null) this.lastEntry = p.prev();
/*      */ 
/*  530 */     if (p.succ()) {
/*  531 */       if (p.pred()) {
/*  532 */         if (q != null) {
/*  533 */           if (dir) q.succ(p.right); else
/*  534 */             q.pred(p.left);
/*      */         }
/*  536 */         else this.tree = (dir ? p.right : p.left); 
/*      */       }
/*      */       else
/*      */       {
/*  539 */         p.prev().right = p.right;
/*      */ 
/*  541 */         if (q != null) {
/*  542 */           if (dir) q.right = p.left; else
/*  543 */             q.left = p.left;
/*      */         }
/*  545 */         else this.tree = p.left; 
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  549 */       Entry r = p.right;
/*      */ 
/*  551 */       if (r.pred()) {
/*  552 */         r.left = p.left;
/*  553 */         r.pred(p.pred());
/*  554 */         if (!r.pred()) r.prev().right = r;
/*  555 */         if (q != null) {
/*  556 */           if (dir) q.right = r; else
/*  557 */             q.left = r;
/*      */         }
/*  559 */         else this.tree = r;
/*      */ 
/*  561 */         r.balance(p.balance());
/*  562 */         q = r;
/*  563 */         dir = true;
/*      */       }
/*      */       else
/*      */       {
/*      */         Entry s;
/*      */         while (true)
/*      */         {
/*  570 */           s = r.left;
/*  571 */           if (s.pred()) break;
/*  572 */           r = s;
/*      */         }
/*      */ 
/*  575 */         if (s.succ()) r.pred(s); else {
/*  576 */           r.left = s.right;
/*      */         }
/*  578 */         s.left = p.left;
/*      */ 
/*  580 */         if (!p.pred()) {
/*  581 */           p.prev().right = s;
/*  582 */           s.pred(false);
/*      */         }
/*      */ 
/*  585 */         s.right = p.right;
/*  586 */         s.succ(false);
/*      */ 
/*  588 */         if (q != null) {
/*  589 */           if (dir) q.right = s; else
/*  590 */             q.left = s;
/*      */         }
/*  592 */         else this.tree = s;
/*      */ 
/*  594 */         s.balance(p.balance());
/*  595 */         q = r;
/*  596 */         dir = false;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  602 */     while (q != null) {
/*  603 */       Entry y = q;
/*  604 */       q = parent(y);
/*      */ 
/*  606 */       if (!dir) {
/*  607 */         dir = (q != null) && (q.left != y);
/*  608 */         y.incBalance();
/*      */ 
/*  610 */         if (y.balance() == 1) break;
/*  611 */         if (y.balance() == 2)
/*      */         {
/*  613 */           Entry x = y.right;
/*      */ 
/*  616 */           if (x.balance() == -1)
/*      */           {
/*  621 */             Entry w = x.left;
/*  622 */             x.left = w.right;
/*  623 */             w.right = x;
/*  624 */             y.right = w.left;
/*  625 */             w.left = y;
/*      */ 
/*  627 */             if (w.balance() == 1) {
/*  628 */               x.balance(0);
/*  629 */               y.balance(-1);
/*      */             }
/*  631 */             else if (w.balance() == 0) {
/*  632 */               x.balance(0);
/*  633 */               y.balance(0);
/*      */             }
/*      */             else
/*      */             {
/*  638 */               x.balance(1);
/*  639 */               y.balance(0);
/*      */             }
/*      */ 
/*  642 */             w.balance(0);
/*      */ 
/*  644 */             if (w.pred()) {
/*  645 */               y.succ(w);
/*  646 */               w.pred(false);
/*      */             }
/*  648 */             if (w.succ()) {
/*  649 */               x.pred(w);
/*  650 */               w.succ(false);
/*      */             }
/*      */ 
/*  653 */             if (q != null) {
/*  654 */               if (dir) q.right = w; else
/*  655 */                 q.left = w;
/*      */             }
/*  657 */             else this.tree = w; 
/*      */           }
/*      */           else
/*      */           {
/*  660 */             if (q != null) {
/*  661 */               if (dir) q.right = x; else
/*  662 */                 q.left = x;
/*      */             }
/*  664 */             else this.tree = x;
/*      */ 
/*  666 */             if (x.balance() == 0) {
/*  667 */               y.right = x.left;
/*  668 */               x.left = y;
/*  669 */               x.balance(-1);
/*  670 */               y.balance(1);
/*  671 */               break;
/*      */             }
/*      */ 
/*  675 */             if (x.pred()) {
/*  676 */               y.succ(true);
/*  677 */               x.pred(false);
/*      */             } else {
/*  679 */               y.right = x.left;
/*      */             }
/*  681 */             x.left = y;
/*  682 */             y.balance(0);
/*  683 */             x.balance(0);
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/*  688 */         dir = (q != null) && (q.left != y);
/*  689 */         y.decBalance();
/*      */ 
/*  691 */         if (y.balance() == -1) break;
/*  692 */         if (y.balance() == -2)
/*      */         {
/*  694 */           Entry x = y.left;
/*      */ 
/*  697 */           if (x.balance() == 1)
/*      */           {
/*  702 */             Entry w = x.right;
/*  703 */             x.right = w.left;
/*  704 */             w.left = x;
/*  705 */             y.left = w.right;
/*  706 */             w.right = y;
/*      */ 
/*  708 */             if (w.balance() == -1) {
/*  709 */               x.balance(0);
/*  710 */               y.balance(1);
/*      */             }
/*  712 */             else if (w.balance() == 0) {
/*  713 */               x.balance(0);
/*  714 */               y.balance(0);
/*      */             }
/*      */             else
/*      */             {
/*  719 */               x.balance(-1);
/*  720 */               y.balance(0);
/*      */             }
/*      */ 
/*  723 */             w.balance(0);
/*      */ 
/*  725 */             if (w.pred()) {
/*  726 */               x.succ(w);
/*  727 */               w.pred(false);
/*      */             }
/*  729 */             if (w.succ()) {
/*  730 */               y.pred(w);
/*  731 */               w.succ(false);
/*      */             }
/*      */ 
/*  734 */             if (q != null) {
/*  735 */               if (dir) q.right = w; else
/*  736 */                 q.left = w;
/*      */             }
/*  738 */             else this.tree = w; 
/*      */           }
/*      */           else
/*      */           {
/*  741 */             if (q != null) {
/*  742 */               if (dir) q.right = x; else
/*  743 */                 q.left = x;
/*      */             }
/*  745 */             else this.tree = x;
/*      */ 
/*  747 */             if (x.balance() == 0) {
/*  748 */               y.left = x.right;
/*  749 */               x.right = y;
/*  750 */               x.balance(1);
/*  751 */               y.balance(-1);
/*  752 */               break;
/*      */             }
/*      */ 
/*  756 */             if (x.succ()) {
/*  757 */               y.pred(true);
/*  758 */               x.succ(false);
/*      */             } else {
/*  760 */               y.left = x.right;
/*      */             }
/*  762 */             x.right = y;
/*  763 */             y.balance(0);
/*  764 */             x.balance(0);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  770 */     this.modified = true;
/*  771 */     this.count -= 1;
/*      */ 
/*  773 */     return p.value;
/*      */   }
/*      */ 
/*      */   public V put(Long ok, V ov)
/*      */   {
/*  779 */     Object oldValue = put(ok.longValue(), ov);
/*  780 */     return this.modified ? this.defRetValue : oldValue;
/*      */   }
/*      */ 
/*      */   public V remove(Object ok)
/*      */   {
/*  788 */     Object oldValue = remove(((Long)ok).longValue());
/*  789 */     return this.modified ? oldValue : this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsValue(Object v)
/*      */   {
/*  795 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  798 */     int j = this.count;
/*  799 */     while (j-- != 0) {
/*  800 */       Object ev = i.next();
/*  801 */       if (ev == v) return true;
/*      */     }
/*      */ 
/*  804 */     return false;
/*      */   }
/*      */ 
/*      */   public void clear() {
/*  808 */     this.count = 0;
/*  809 */     this.tree = null;
/*  810 */     this.entries = null;
/*  811 */     this.values = null;
/*  812 */     this.keys = null;
/*  813 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public boolean containsKey(long k)
/*      */   {
/* 1067 */     return findKey(k) != null;
/*      */   }
/*      */   public int size() {
/* 1070 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/* 1073 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public V get(long k) {
/* 1077 */     Entry e = findKey(k);
/* 1078 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */   public long firstLongKey() {
/* 1081 */     if (this.tree == null) throw new NoSuchElementException();
/* 1082 */     return this.firstEntry.key;
/*      */   }
/*      */   public long lastLongKey() {
/* 1085 */     if (this.tree == null) throw new NoSuchElementException();
/* 1086 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet()
/*      */   {
/* 1178 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1179 */         final Comparator<? super Long2ReferenceMap.Entry<V>> comparator = new Comparator() {
/*      */           public int compare(Long2ReferenceMap.Entry<V> x, Long2ReferenceMap.Entry<V> y) {
/* 1181 */             return Long2ReferenceAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/* 1179 */         };
/*      */ 
/*      */         public Comparator<? super Long2ReferenceMap.Entry<V>> comparator()
/*      */         {
/* 1185 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator() {
/* 1188 */           return new Long2ReferenceAVLTreeMap.EntryIterator(Long2ReferenceAVLTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator(Long2ReferenceMap.Entry<V> from) {
/* 1191 */           return new Long2ReferenceAVLTreeMap.EntryIterator(Long2ReferenceAVLTreeMap.this, ((Long)from.getKey()).longValue());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/* 1195 */           if (!(o instanceof Map.Entry)) return false;
/* 1196 */           Map.Entry e = (Map.Entry)o;
/* 1197 */           Long2ReferenceAVLTreeMap.Entry f = Long2ReferenceAVLTreeMap.this.findKey(((Long)e.getKey()).longValue());
/* 1198 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/* 1202 */           if (!(o instanceof Map.Entry)) return false;
/* 1203 */           Map.Entry e = (Map.Entry)o;
/* 1204 */           Long2ReferenceAVLTreeMap.Entry f = Long2ReferenceAVLTreeMap.this.findKey(((Long)e.getKey()).longValue());
/* 1205 */           if (f != null) Long2ReferenceAVLTreeMap.this.remove(f.key);
/* 1206 */           return f != null;
/*      */         }
/* 1208 */         public int size() { return Long2ReferenceAVLTreeMap.this.count; } 
/* 1209 */         public void clear() { Long2ReferenceAVLTreeMap.this.clear(); } 
/* 1210 */         public Long2ReferenceMap.Entry<V> first() { return Long2ReferenceAVLTreeMap.this.firstEntry; } 
/* 1211 */         public Long2ReferenceMap.Entry<V> last() { return Long2ReferenceAVLTreeMap.this.lastEntry; } 
/* 1212 */         public ObjectSortedSet<Long2ReferenceMap.Entry<V>> subSet(Long2ReferenceMap.Entry<V> from, Long2ReferenceMap.Entry<V> to) { return Long2ReferenceAVLTreeMap.this.subMap((Long)from.getKey(), (Long)to.getKey()).long2ReferenceEntrySet(); } 
/* 1213 */         public ObjectSortedSet<Long2ReferenceMap.Entry<V>> headSet(Long2ReferenceMap.Entry<V> to) { return Long2ReferenceAVLTreeMap.this.headMap((Long)to.getKey()).long2ReferenceEntrySet(); } 
/* 1214 */         public ObjectSortedSet<Long2ReferenceMap.Entry<V>> tailSet(Long2ReferenceMap.Entry<V> from) { return Long2ReferenceAVLTreeMap.this.tailMap((Long)from.getKey()).long2ReferenceEntrySet(); }
/*      */       };
/* 1216 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public LongSortedSet keySet()
/*      */   {
/* 1250 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1251 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ReferenceCollection<V> values()
/*      */   {
/* 1274 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */         public ObjectIterator<V> iterator() {
/* 1276 */           return new Long2ReferenceAVLTreeMap.ValueIterator(Long2ReferenceAVLTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(Object k) {
/* 1279 */           return Long2ReferenceAVLTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1282 */           return Long2ReferenceAVLTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1285 */           Long2ReferenceAVLTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1288 */     return this.values;
/*      */   }
/*      */   public LongComparator comparator() {
/* 1291 */     return this.actualComparator;
/*      */   }
/*      */   public Long2ReferenceSortedMap<V> headMap(long to) {
/* 1294 */     return new Submap(0L, true, to, false);
/*      */   }
/*      */   public Long2ReferenceSortedMap<V> tailMap(long from) {
/* 1297 */     return new Submap(from, false, 0L, true);
/*      */   }
/*      */   public Long2ReferenceSortedMap<V> subMap(long from, long to) {
/* 1300 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Long2ReferenceAVLTreeMap<V> clone()
/*      */   {
/*      */     Long2ReferenceAVLTreeMap c;
/*      */     try
/*      */     {
/* 1645 */       c = (Long2ReferenceAVLTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1648 */       throw new InternalError();
/*      */     }
/* 1650 */     c.keys = null;
/* 1651 */     c.values = null;
/* 1652 */     c.entries = null;
/* 1653 */     c.allocatePaths();
/* 1654 */     if (this.count != 0)
/*      */     {
/* 1656 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1657 */       Entry p = rp;
/* 1658 */       rp.left(this.tree);
/* 1659 */       Entry q = rq;
/* 1660 */       rq.pred(null);
/*      */       while (true) {
/* 1662 */         if (!p.pred()) {
/* 1663 */           Entry e = p.left.clone();
/* 1664 */           e.pred(q.left);
/* 1665 */           e.succ(q);
/* 1666 */           q.left(e);
/* 1667 */           p = p.left;
/* 1668 */           q = q.left;
/*      */         }
/*      */         else {
/* 1671 */           while (p.succ()) {
/* 1672 */             p = p.right;
/* 1673 */             if (p == null) {
/* 1674 */               q.right = null;
/* 1675 */               c.tree = rq.left;
/* 1676 */               c.firstEntry = c.tree;
/* 1677 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1678 */               c.lastEntry = c.tree;
/* 1679 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1680 */               return c;
/*      */             }
/* 1682 */             q = q.right;
/*      */           }
/* 1684 */           p = p.right;
/* 1685 */           q = q.right;
/*      */         }
/* 1687 */         if (!p.succ()) {
/* 1688 */           Entry e = p.right.clone();
/* 1689 */           e.succ(q.right);
/* 1690 */           e.pred(q);
/* 1691 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1695 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1698 */     int n = this.count;
/* 1699 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1701 */     s.defaultWriteObject();
/* 1702 */     while (n-- != 0) {
/* 1703 */       Entry e = i.nextEntry();
/* 1704 */       s.writeLong(e.key);
/* 1705 */       s.writeObject(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1717 */     if (n == 1) {
/* 1718 */       Entry top = new Entry(s.readLong(), s.readObject());
/* 1719 */       top.pred(pred);
/* 1720 */       top.succ(succ);
/* 1721 */       return top;
/*      */     }
/* 1723 */     if (n == 2)
/*      */     {
/* 1726 */       Entry top = new Entry(s.readLong(), s.readObject());
/* 1727 */       top.right(new Entry(s.readLong(), s.readObject()));
/* 1728 */       top.right.pred(top);
/* 1729 */       top.balance(1);
/* 1730 */       top.pred(pred);
/* 1731 */       top.right.succ(succ);
/* 1732 */       return top;
/*      */     }
/*      */ 
/* 1735 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1736 */     Entry top = new Entry();
/* 1737 */     top.left(readTree(s, leftN, pred, top));
/* 1738 */     top.key = s.readLong();
/* 1739 */     top.value = s.readObject();
/* 1740 */     top.right(readTree(s, rightN, top, succ));
/* 1741 */     if (n == (n & -n)) top.balance(1);
/* 1742 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1745 */     s.defaultReadObject();
/*      */ 
/* 1748 */     setActualComparator();
/* 1749 */     allocatePaths();
/* 1750 */     if (this.count != 0) {
/* 1751 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1753 */       Entry e = this.tree;
/* 1754 */       while (e.left() != null) e = e.left();
/* 1755 */       this.firstEntry = e;
/* 1756 */       e = this.tree;
/* 1757 */       while (e.right() != null) e = e.right();
/* 1758 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static int checkTree(Entry e) {
/* 1763 */     return 0;
/*      */   }
/*      */ 
/*      */   private final class Submap extends AbstractLong2ReferenceSortedMap<V>
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     long from;
/*      */     long to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Long2ReferenceMap.Entry<V>> entries;
/*      */     protected volatile transient LongSortedSet keys;
/*      */     protected volatile transient ReferenceCollection<V> values;
/*      */ 
/*      */     public Submap(long from, boolean bottom, long to, boolean top)
/*      */     {
/* 1338 */       if ((!bottom) && (!top) && (Long2ReferenceAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1339 */       this.from = from;
/* 1340 */       this.bottom = bottom;
/* 1341 */       this.to = to;
/* 1342 */       this.top = top;
/* 1343 */       this.defRetValue = Long2ReferenceAVLTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1346 */       SubmapIterator i = new SubmapIterator();
/* 1347 */       while (i.hasNext()) {
/* 1348 */         i.nextEntry();
/* 1349 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(long k)
/*      */     {
/* 1357 */       return ((this.bottom) || (Long2ReferenceAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Long2ReferenceAVLTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Long2ReferenceMap.Entry<V>> long2ReferenceEntrySet() {
/* 1361 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator() {
/* 1363 */             return new Long2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Long2ReferenceAVLTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Long2ReferenceMap.Entry<V>> iterator(Long2ReferenceMap.Entry<V> from) {
/* 1366 */             return new Long2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Long2ReferenceAVLTreeMap.Submap.this, ((Long)from.getKey()).longValue());
/*      */           }
/* 1368 */           public Comparator<? super Long2ReferenceMap.Entry<V>> comparator() { return Long2ReferenceAVLTreeMap.this.entrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1371 */             if (!(o instanceof Map.Entry)) return false;
/* 1372 */             Map.Entry e = (Map.Entry)o;
/* 1373 */             Long2ReferenceAVLTreeMap.Entry f = Long2ReferenceAVLTreeMap.this.findKey(((Long)e.getKey()).longValue());
/* 1374 */             return (f != null) && (Long2ReferenceAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1378 */             if (!(o instanceof Map.Entry)) return false;
/* 1379 */             Map.Entry e = (Map.Entry)o;
/* 1380 */             Long2ReferenceAVLTreeMap.Entry f = Long2ReferenceAVLTreeMap.this.findKey(((Long)e.getKey()).longValue());
/* 1381 */             if ((f != null) && (Long2ReferenceAVLTreeMap.Submap.this.in(f.key))) Long2ReferenceAVLTreeMap.Submap.this.remove(f.key);
/* 1382 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1385 */             int c = 0;
/* 1386 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1387 */             return c;
/*      */           }
/*      */           public boolean isEmpty() {
/* 1390 */             return !new Long2ReferenceAVLTreeMap.Submap.SubmapIterator(Long2ReferenceAVLTreeMap.Submap.this).hasNext();
/*      */           }
/*      */           public void clear() {
/* 1393 */             Long2ReferenceAVLTreeMap.Submap.this.clear();
/*      */           }
/* 1395 */           public Long2ReferenceMap.Entry<V> first() { return Long2ReferenceAVLTreeMap.Submap.this.firstEntry(); } 
/* 1396 */           public Long2ReferenceMap.Entry<V> last() { return Long2ReferenceAVLTreeMap.Submap.this.lastEntry(); } 
/* 1397 */           public ObjectSortedSet<Long2ReferenceMap.Entry<V>> subSet(Long2ReferenceMap.Entry<V> from, Long2ReferenceMap.Entry<V> to) { return Long2ReferenceAVLTreeMap.Submap.this.subMap((Long)from.getKey(), (Long)to.getKey()).long2ReferenceEntrySet(); } 
/* 1398 */           public ObjectSortedSet<Long2ReferenceMap.Entry<V>> headSet(Long2ReferenceMap.Entry<V> to) { return Long2ReferenceAVLTreeMap.Submap.this.headMap((Long)to.getKey()).long2ReferenceEntrySet(); } 
/* 1399 */           public ObjectSortedSet<Long2ReferenceMap.Entry<V>> tailSet(Long2ReferenceMap.Entry<V> from) { return Long2ReferenceAVLTreeMap.Submap.this.tailMap((Long)from.getKey()).long2ReferenceEntrySet(); }
/*      */         };
/* 1401 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public LongSortedSet keySet()
/*      */     {
/* 1408 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1409 */       return this.keys;
/*      */     }
/*      */     public ReferenceCollection<V> values() {
/* 1412 */       if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */           public ObjectIterator<V> iterator() {
/* 1414 */             return new Long2ReferenceAVLTreeMap.Submap.SubmapValueIterator(Long2ReferenceAVLTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(Object k) {
/* 1417 */             return Long2ReferenceAVLTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1420 */             return Long2ReferenceAVLTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1423 */             Long2ReferenceAVLTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1426 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(long k) {
/* 1430 */       return (in(k)) && (Long2ReferenceAVLTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(Object v) {
/* 1433 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1435 */       while (i.hasNext()) {
/* 1436 */         Object ev = i.nextEntry().value;
/* 1437 */         if (ev == v) return true;
/*      */       }
/* 1439 */       return false;
/*      */     }
/*      */ 
/*      */     public V get(long k)
/*      */     {
/* 1444 */       long kk = k;
/*      */       Long2ReferenceAVLTreeMap.Entry e;
/* 1445 */       return (in(kk)) && ((e = Long2ReferenceAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */     public V put(long k, V v) {
/* 1448 */       Long2ReferenceAVLTreeMap.this.modified = false;
/* 1449 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1450 */       Object oldValue = Long2ReferenceAVLTreeMap.this.put(k, v);
/* 1451 */       return Long2ReferenceAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */     public V put(Long ok, V ov) {
/* 1454 */       Object oldValue = put(ok.longValue(), ov);
/* 1455 */       return Long2ReferenceAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */ 
/*      */     public V remove(long k) {
/* 1459 */       Long2ReferenceAVLTreeMap.this.modified = false;
/* 1460 */       if (!in(k)) return this.defRetValue;
/* 1461 */       Object oldValue = Long2ReferenceAVLTreeMap.this.remove(k);
/* 1462 */       return Long2ReferenceAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public V remove(Object ok) {
/* 1465 */       Object oldValue = remove(((Long)ok).longValue());
/* 1466 */       return Long2ReferenceAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public int size() {
/* 1469 */       SubmapIterator i = new SubmapIterator();
/* 1470 */       int n = 0;
/* 1471 */       while (i.hasNext()) {
/* 1472 */         n++;
/* 1473 */         i.nextEntry();
/*      */       }
/* 1475 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1478 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public LongComparator comparator() {
/* 1481 */       return Long2ReferenceAVLTreeMap.this.actualComparator;
/*      */     }
/*      */     public Long2ReferenceSortedMap<V> headMap(long to) {
/* 1484 */       if (this.top) return new Submap(Long2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1485 */       return Long2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Long2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Long2ReferenceSortedMap<V> tailMap(long from) {
/* 1488 */       if (this.bottom) return new Submap(Long2ReferenceAVLTreeMap.this, from, false, this.to, this.top);
/* 1489 */       return Long2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Long2ReferenceAVLTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Long2ReferenceSortedMap<V> subMap(long from, long to) {
/* 1492 */       if ((this.top) && (this.bottom)) return new Submap(Long2ReferenceAVLTreeMap.this, from, false, to, false);
/* 1493 */       if (!this.top) to = Long2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1494 */       if (!this.bottom) from = Long2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1495 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1496 */       return new Submap(Long2ReferenceAVLTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Long2ReferenceAVLTreeMap.Entry<V> firstEntry()
/*      */     {
/* 1503 */       if (Long2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1506 */       Long2ReferenceAVLTreeMap.Entry e;
/*      */       Long2ReferenceAVLTreeMap.Entry e;
/* 1506 */       if (this.bottom) { e = Long2ReferenceAVLTreeMap.this.firstEntry;
/*      */       } else {
/* 1508 */         e = Long2ReferenceAVLTreeMap.this.locateKey(this.from);
/*      */ 
/* 1510 */         if (Long2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1513 */       if ((e == null) || ((!this.top) && (Long2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1514 */       return e;
/*      */     }
/*      */ 
/*      */     public Long2ReferenceAVLTreeMap.Entry<V> lastEntry()
/*      */     {
/* 1521 */       if (Long2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1524 */       Long2ReferenceAVLTreeMap.Entry e;
/*      */       Long2ReferenceAVLTreeMap.Entry e;
/* 1524 */       if (this.top) { e = Long2ReferenceAVLTreeMap.this.lastEntry;
/*      */       } else {
/* 1526 */         e = Long2ReferenceAVLTreeMap.this.locateKey(this.to);
/*      */ 
/* 1528 */         if (Long2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1531 */       if ((e == null) || ((!this.bottom) && (Long2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1532 */       return e;
/*      */     }
/*      */     public long firstLongKey() {
/* 1535 */       Long2ReferenceAVLTreeMap.Entry e = firstEntry();
/* 1536 */       if (e == null) throw new NoSuchElementException();
/* 1537 */       return e.key;
/*      */     }
/*      */     public long lastLongKey() {
/* 1540 */       Long2ReferenceAVLTreeMap.Entry e = lastEntry();
/* 1541 */       if (e == null) throw new NoSuchElementException();
/* 1542 */       return e.key;
/*      */     }
/*      */     public Long firstKey() {
/* 1545 */       Long2ReferenceAVLTreeMap.Entry e = firstEntry();
/* 1546 */       if (e == null) throw new NoSuchElementException();
/* 1547 */       return e.getKey();
/*      */     }
/*      */     public Long lastKey() {
/* 1550 */       Long2ReferenceAVLTreeMap.Entry e = lastEntry();
/* 1551 */       if (e == null) throw new NoSuchElementException();
/* 1552 */       return e.getKey();
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Long2ReferenceAVLTreeMap<V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<V>
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1627 */         super(); } 
/* 1628 */       public V next() { return nextEntry().value; } 
/* 1629 */       public V previous() { return previousEntry().value; } 
/* 1630 */       public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1631 */       public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Long2ReferenceAVLTreeMap.Submap.SubmapIterator
/*      */       implements LongListIterator
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1608 */         super(); } 
/* 1609 */       public SubmapKeyIterator(long from) { super(from); } 
/* 1610 */       public long nextLong() { return nextEntry().key; } 
/* 1611 */       public long previousLong() { return previousEntry().key; } 
/* 1612 */       public void set(long k) { throw new UnsupportedOperationException(); } 
/* 1613 */       public void add(long k) { throw new UnsupportedOperationException(); } 
/* 1614 */       public Long next() { return Long.valueOf(nextEntry().key); } 
/* 1615 */       public Long previous() { return Long.valueOf(previousEntry().key); } 
/* 1616 */       public void set(Long ok) { throw new UnsupportedOperationException(); } 
/* 1617 */       public void add(Long ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Long2ReferenceAVLTreeMap<V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Long2ReferenceMap.Entry<V>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1590 */         super();
/*      */       }
/* 1592 */       SubmapEntryIterator(long k) { super(k); } 
/*      */       public Long2ReferenceMap.Entry<V> next() {
/* 1594 */         return nextEntry(); } 
/* 1595 */       public Long2ReferenceMap.Entry<V> previous() { return previousEntry(); } 
/* 1596 */       public void set(Long2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); } 
/* 1597 */       public void add(Long2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Long2ReferenceAVLTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1562 */         super();
/* 1563 */         this.next = Long2ReferenceAVLTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator(long k) {
/* 1566 */         this();
/* 1567 */         if (this.next != null)
/* 1568 */           if ((!Long2ReferenceAVLTreeMap.Submap.this.bottom) && (Long2ReferenceAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1569 */           } else if ((!Long2ReferenceAVLTreeMap.Submap.this.top) && (Long2ReferenceAVLTreeMap.this.compare(k, (this.prev = Long2ReferenceAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1571 */             this.next = Long2ReferenceAVLTreeMap.this.locateKey(k);
/* 1572 */             if (Long2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1573 */               this.prev = this.next;
/* 1574 */               this.next = this.next.next();
/*      */             } else {
/* 1576 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1581 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1582 */         if ((!Long2ReferenceAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Long2ReferenceAVLTreeMap.this.compare(this.prev.key, Long2ReferenceAVLTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1585 */         this.next = this.next.next();
/* 1586 */         if ((!Long2ReferenceAVLTreeMap.Submap.this.top) && (this.next != null) && (Long2ReferenceAVLTreeMap.this.compare(this.next.key, Long2ReferenceAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractLong2ReferenceSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1403 */         super(); } 
/* 1404 */       public LongBidirectionalIterator iterator() { return new Long2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Long2ReferenceAVLTreeMap.Submap.this); } 
/* 1405 */       public LongBidirectionalIterator iterator(long from) { return new Long2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Long2ReferenceAVLTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Long2ReferenceAVLTreeMap<V>.TreeIterator
/*      */     implements ObjectListIterator<V>
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1259 */       super(); } 
/* 1260 */     public V next() { return nextEntry().value; } 
/* 1261 */     public V previous() { return previousEntry().value; } 
/* 1262 */     public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1263 */     public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractLong2ReferenceSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1237 */       super(); } 
/* 1238 */     public LongBidirectionalIterator iterator() { return new Long2ReferenceAVLTreeMap.KeyIterator(Long2ReferenceAVLTreeMap.this); } 
/* 1239 */     public LongBidirectionalIterator iterator(long from) { return new Long2ReferenceAVLTreeMap.KeyIterator(Long2ReferenceAVLTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Long2ReferenceAVLTreeMap.TreeIterator
/*      */     implements LongListIterator
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1225 */       super(); } 
/* 1226 */     public KeyIterator(long k) { super(k); } 
/* 1227 */     public long nextLong() { return nextEntry().key; } 
/* 1228 */     public long previousLong() { return previousEntry().key; } 
/* 1229 */     public void set(long k) { throw new UnsupportedOperationException(); } 
/* 1230 */     public void add(long k) { throw new UnsupportedOperationException(); } 
/* 1231 */     public Long next() { return Long.valueOf(nextEntry().key); } 
/* 1232 */     public Long previous() { return Long.valueOf(previousEntry().key); } 
/* 1233 */     public void set(Long ok) { throw new UnsupportedOperationException(); } 
/* 1234 */     public void add(Long ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Long2ReferenceAVLTreeMap<V>.TreeIterator
/*      */     implements ObjectListIterator<Long2ReferenceMap.Entry<V>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/* 1168 */       super();
/*      */     }
/* 1170 */     EntryIterator(long k) { super(k); } 
/*      */     public Long2ReferenceMap.Entry<V> next() {
/* 1172 */       return nextEntry(); } 
/* 1173 */     public Long2ReferenceMap.Entry<V> previous() { return previousEntry(); } 
/* 1174 */     public void set(Long2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); } 
/* 1175 */     public void add(Long2ReferenceMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Long2ReferenceAVLTreeMap.Entry<V> prev;
/*      */     Long2ReferenceAVLTreeMap.Entry<V> next;
/*      */     Long2ReferenceAVLTreeMap.Entry<V> curr;
/* 1100 */     int index = 0;
/*      */ 
/* 1102 */     TreeIterator() { this.next = Long2ReferenceAVLTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator(long k) {
/* 1105 */       if ((this.next = Long2ReferenceAVLTreeMap.this.locateKey(k)) != null)
/* 1106 */         if (Long2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1107 */           this.prev = this.next;
/* 1108 */           this.next = this.next.next();
/*      */         } else {
/* 1110 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/* 1113 */     public boolean hasNext() { return this.next != null; } 
/* 1114 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/* 1116 */       this.next = this.next.next();
/*      */     }
/*      */     Long2ReferenceAVLTreeMap.Entry<V> nextEntry() {
/* 1119 */       if (!hasNext()) throw new NoSuchElementException();
/* 1120 */       this.curr = (this.prev = this.next);
/* 1121 */       this.index += 1;
/* 1122 */       updateNext();
/* 1123 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/* 1126 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Long2ReferenceAVLTreeMap.Entry<V> previousEntry() {
/* 1129 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 1130 */       this.curr = (this.next = this.prev);
/* 1131 */       this.index -= 1;
/* 1132 */       updatePrevious();
/* 1133 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/* 1136 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/* 1139 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/* 1142 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/* 1145 */       if (this.curr == this.prev) this.index -= 1;
/* 1146 */       this.next = (this.prev = this.curr);
/* 1147 */       updatePrevious();
/* 1148 */       updateNext();
/* 1149 */       Long2ReferenceAVLTreeMap.this.remove(this.curr.key);
/* 1150 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/* 1153 */       int i = n;
/* 1154 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 1155 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/* 1158 */       int i = n;
/* 1159 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1160 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry<V>
/*      */     implements Cloneable, Long2ReferenceMap.Entry<V>
/*      */   {
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     private static final int BALANCE_MASK = 255;
/*      */     long key;
/*      */     V value;
/*      */     Entry<V> left;
/*      */     Entry<V> right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(long k, V v)
/*      */     {
/*  850 */       this.key = k;
/*  851 */       this.value = v;
/*  852 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry<V> left()
/*      */     {
/*  861 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry<V> right()
/*      */     {
/*  870 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  877 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  884 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  891 */       if (pred) this.info |= 1073741824; else
/*  892 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  899 */       if (succ) this.info |= -2147483648; else
/*  900 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry<V> pred)
/*      */     {
/*  907 */       this.info |= 1073741824;
/*  908 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry<V> succ)
/*      */     {
/*  915 */       this.info |= -2147483648;
/*  916 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry<V> left)
/*      */     {
/*  923 */       this.info &= -1073741825;
/*  924 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry<V> right)
/*      */     {
/*  931 */       this.info &= 2147483647;
/*  932 */       this.right = right;
/*      */     }
/*      */ 
/*      */     int balance()
/*      */     {
/*  939 */       return (byte)this.info;
/*      */     }
/*      */ 
/*      */     void balance(int level)
/*      */     {
/*  946 */       this.info &= -256;
/*  947 */       this.info |= level & 0xFF;
/*      */     }
/*      */ 
/*      */     void incBalance()
/*      */     {
/*  952 */       this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*      */     }
/*      */ 
/*      */     protected void decBalance()
/*      */     {
/*  957 */       this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*      */     }
/*      */ 
/*      */     Entry<V> next()
/*      */     {
/*  966 */       Entry next = this.right;
/*  967 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  968 */       return next;
/*      */     }
/*      */ 
/*      */     Entry<V> prev()
/*      */     {
/*  977 */       Entry prev = this.left;
/*  978 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  979 */       return prev;
/*      */     }
/*      */ 
/*      */     public Long getKey() {
/*  983 */       return Long.valueOf(this.key);
/*      */     }
/*      */ 
/*      */     public long getLongKey()
/*      */     {
/*  988 */       return this.key;
/*      */     }
/*      */ 
/*      */     public V getValue()
/*      */     {
/*  993 */       return this.value;
/*      */     }
/*      */ 
/*      */     public V setValue(V value)
/*      */     {
/* 1003 */       Object oldValue = this.value;
/* 1004 */       this.value = value;
/* 1005 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public Entry<V> clone() {
/*      */       Entry c;
/*      */       try {
/* 1011 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/* 1014 */         throw new InternalError();
/*      */       }
/* 1016 */       c.key = this.key;
/* 1017 */       c.value = this.value;
/* 1018 */       c.info = this.info;
/* 1019 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/* 1023 */       if (!(o instanceof Map.Entry)) return false;
/* 1024 */       Map.Entry e = (Map.Entry)o;
/* 1025 */       return (this.key == ((Long)e.getKey()).longValue()) && (this.value == e.getValue());
/*      */     }
/*      */     public int hashCode() {
/* 1028 */       return HashCommon.long2int(this.key) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
/*      */     }
/*      */     public String toString() {
/* 1031 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.Long2ReferenceAVLTreeMap
 * JD-Core Version:    0.6.2
 */