/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*      */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*      */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*      */ import it.unimi.dsi.fastutil.ints.IntListIterator;
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
/*      */ public class Object2IntAVLTreeMap<K> extends AbstractObject2IntSortedMap<K>
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry<K> tree;
/*      */   protected int count;
/*      */   protected transient Entry<K> firstEntry;
/*      */   protected transient Entry<K> lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Object2IntMap.Entry<K>> entries;
/*      */   protected volatile transient ObjectSortedSet<K> keys;
/*      */   protected volatile transient IntCollection values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super K> storedComparator;
/*      */   protected transient Comparator<? super K> actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */ 
/*      */   public Object2IntAVLTreeMap()
/*      */   {
/*   92 */     allocatePaths();
/*      */ 
/*   97 */     this.tree = null;
/*   98 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  110 */     this.actualComparator = this.storedComparator;
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(Comparator<? super K> c)
/*      */   {
/*  117 */     this();
/*  118 */     this.storedComparator = c;
/*  119 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(Map<? extends K, ? extends Integer> m)
/*      */   {
/*  126 */     this();
/*  127 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(SortedMap<K, Integer> m)
/*      */   {
/*  134 */     this(m.comparator());
/*  135 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(Object2IntMap<? extends K> m)
/*      */   {
/*  142 */     this();
/*  143 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(Object2IntSortedMap<K> m)
/*      */   {
/*  150 */     this(m.comparator());
/*  151 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(K[] k, int[] v, Comparator<? super K> c)
/*      */   {
/*  161 */     this(c);
/*  162 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  163 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap(K[] k, int[] v)
/*      */   {
/*  172 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(K k1, K k2)
/*      */   {
/*  195 */     return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<K> findKey(K k)
/*      */   {
/*  203 */     Entry e = this.tree;
/*      */     int cmp;
/*  205 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  206 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<K> locateKey(K k)
/*      */   {
/*  215 */     Entry e = this.tree; Entry last = this.tree;
/*  216 */     int cmp = 0;
/*  217 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  218 */       last = e;
/*  219 */       e = cmp < 0 ? e.left() : e.right();
/*      */     }
/*  221 */     return cmp == 0 ? e : last;
/*      */   }
/*      */ 
/*      */   private void allocatePaths()
/*      */   {
/*  227 */     this.dirPath = new boolean[48];
/*      */   }
/*      */ 
/*      */   public int put(K k, int v)
/*      */   {
/*  232 */     this.modified = false;
/*  233 */     if (this.tree == null) {
/*  234 */       this.count += 1;
/*  235 */       this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*  236 */       this.modified = true;
/*      */     }
/*      */     else {
/*  239 */       Entry p = this.tree; Entry q = null; Entry y = this.tree; Entry z = null; Entry e = null; Entry w = null;
/*  240 */       int i = 0;
/*      */       while (true)
/*      */       {
/*      */         int cmp;
/*  242 */         if ((cmp = compare(k, p.key)) == 0) {
/*  243 */           int oldValue = p.value;
/*  244 */           p.value = v;
/*  245 */           return oldValue;
/*      */         }
/*  247 */         if (p.balance() != 0) {
/*  248 */           i = 0;
/*  249 */           z = q;
/*  250 */           y = p;
/*      */         }
/*  252 */         if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  253 */           if (p.succ()) {
/*  254 */             this.count += 1;
/*  255 */             e = new Entry(k, v);
/*  256 */             this.modified = true;
/*  257 */             if (p.right == null) this.lastEntry = e;
/*  258 */             e.left = p;
/*  259 */             e.right = p.right;
/*  260 */             p.right(e);
/*  261 */             break;
/*      */           }
/*  263 */           q = p;
/*  264 */           p = p.right;
/*      */         }
/*      */         else {
/*  267 */           if (p.pred()) {
/*  268 */             this.count += 1;
/*  269 */             e = new Entry(k, v);
/*  270 */             this.modified = true;
/*  271 */             if (p.left == null) this.firstEntry = e;
/*  272 */             e.right = p;
/*  273 */             e.left = p.left;
/*  274 */             p.left(e);
/*  275 */             break;
/*      */           }
/*  277 */           q = p;
/*  278 */           p = p.left;
/*      */         }
/*      */       }
/*  281 */       p = y;
/*  282 */       i = 0;
/*  283 */       while (p != e) {
/*  284 */         if (this.dirPath[i] != 0) p.incBalance(); else
/*  285 */           p.decBalance();
/*  286 */         p = this.dirPath[(i++)] != 0 ? p.right : p.left;
/*      */       }
/*  288 */       if (y.balance() == -2) {
/*  289 */         Entry x = y.left;
/*  290 */         if (x.balance() == -1) {
/*  291 */           w = x;
/*  292 */           if (x.succ()) {
/*  293 */             x.succ(false);
/*  294 */             y.pred(x);
/*      */           } else {
/*  296 */             y.left = x.right;
/*  297 */           }x.right = y;
/*  298 */           x.balance(0);
/*  299 */           y.balance(0);
/*      */         }
/*      */         else
/*      */         {
/*  303 */           w = x.right;
/*  304 */           x.right = w.left;
/*  305 */           w.left = x;
/*  306 */           y.left = w.right;
/*  307 */           w.right = y;
/*  308 */           if (w.balance() == -1) {
/*  309 */             x.balance(0);
/*  310 */             y.balance(1);
/*      */           }
/*  312 */           else if (w.balance() == 0) {
/*  313 */             x.balance(0);
/*  314 */             y.balance(0);
/*      */           }
/*      */           else {
/*  317 */             x.balance(-1);
/*  318 */             y.balance(0);
/*      */           }
/*  320 */           w.balance(0);
/*  321 */           if (w.pred()) {
/*  322 */             x.succ(w);
/*  323 */             w.pred(false);
/*      */           }
/*  325 */           if (w.succ()) {
/*  326 */             y.pred(w);
/*  327 */             w.succ(false);
/*      */           }
/*      */         }
/*      */       }
/*  331 */       else if (y.balance() == 2) {
/*  332 */         Entry x = y.right;
/*  333 */         if (x.balance() == 1) {
/*  334 */           w = x;
/*  335 */           if (x.pred()) {
/*  336 */             x.pred(false);
/*  337 */             y.succ(x);
/*      */           } else {
/*  339 */             y.right = x.left;
/*  340 */           }x.left = y;
/*  341 */           x.balance(0);
/*  342 */           y.balance(0);
/*      */         }
/*      */         else
/*      */         {
/*  346 */           w = x.left;
/*  347 */           x.left = w.right;
/*  348 */           w.right = x;
/*  349 */           y.right = w.left;
/*  350 */           w.left = y;
/*  351 */           if (w.balance() == 1) {
/*  352 */             x.balance(0);
/*  353 */             y.balance(-1);
/*      */           }
/*  355 */           else if (w.balance() == 0) {
/*  356 */             x.balance(0);
/*  357 */             y.balance(0);
/*      */           }
/*      */           else {
/*  360 */             x.balance(1);
/*  361 */             y.balance(0);
/*      */           }
/*  363 */           w.balance(0);
/*  364 */           if (w.pred()) {
/*  365 */             y.succ(w);
/*  366 */             w.pred(false);
/*      */           }
/*  368 */           if (w.succ()) {
/*  369 */             x.pred(w);
/*  370 */             w.succ(false);
/*      */           }
/*      */         }
/*      */       } else {
/*  374 */         return this.defRetValue;
/*  375 */       }if (z == null) this.tree = w;
/*  377 */       else if (z.left == y) z.left = w; else {
/*  378 */         z.right = w;
/*      */       }
/*      */     }
/*      */ 
/*  382 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   private Entry<K> parent(Entry<K> e)
/*      */   {
/*  390 */     if (e == this.tree) return null;
/*      */     Entry y;
/*  392 */     Entry x = y = e;
/*      */     while (true) {
/*  394 */       if (y.succ()) {
/*  395 */         Entry p = y.right;
/*  396 */         if ((p == null) || (p.left != e)) {
/*  397 */           while (!x.pred()) x = x.left;
/*  398 */           p = x.left;
/*      */         }
/*  400 */         return p;
/*      */       }
/*  402 */       if (x.pred()) {
/*  403 */         Entry p = x.left;
/*  404 */         if ((p == null) || (p.right != e)) {
/*  405 */           while (!y.succ()) y = y.right;
/*  406 */           p = y.right;
/*      */         }
/*  408 */         return p;
/*      */       }
/*  410 */       x = x.left;
/*  411 */       y = y.right;
/*      */     }
/*      */   }
/*      */ 
/*      */   public int removeInt(Object k)
/*      */   {
/*  418 */     this.modified = false;
/*  419 */     if (this.tree == null) return this.defRetValue;
/*      */ 
/*  421 */     Entry p = this.tree; Entry q = null;
/*  422 */     boolean dir = false;
/*  423 */     Object kk = k;
/*      */     int cmp;
/*  425 */     while ((cmp = compare(kk, p.key)) != 0) {
/*  426 */       if ((dir = cmp > 0 ? 1 : 0) != 0) {
/*  427 */         q = p;
/*  428 */         if ((p = p.right()) == null) return this.defRetValue; 
/*      */       }
/*      */       else
/*      */       {
/*  431 */         q = p;
/*  432 */         if ((p = p.left()) == null) return this.defRetValue;
/*      */       }
/*      */     }
/*  435 */     if (p.left == null) this.firstEntry = p.next();
/*  436 */     if (p.right == null) this.lastEntry = p.prev();
/*  437 */     if (p.succ()) {
/*  438 */       if (p.pred()) {
/*  439 */         if (q != null) {
/*  440 */           if (dir) q.succ(p.right); else
/*  441 */             q.pred(p.left);
/*      */         }
/*  443 */         else this.tree = (dir ? p.right : p.left); 
/*      */       }
/*      */       else
/*      */       {
/*  446 */         p.prev().right = p.right;
/*  447 */         if (q != null) {
/*  448 */           if (dir) q.right = p.left; else
/*  449 */             q.left = p.left;
/*      */         }
/*  451 */         else this.tree = p.left; 
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  455 */       Entry r = p.right;
/*  456 */       if (r.pred()) {
/*  457 */         r.left = p.left;
/*  458 */         r.pred(p.pred());
/*  459 */         if (!r.pred()) r.prev().right = r;
/*  460 */         if (q != null) {
/*  461 */           if (dir) q.right = r; else
/*  462 */             q.left = r;
/*      */         }
/*  464 */         else this.tree = r;
/*  465 */         r.balance(p.balance());
/*  466 */         q = r;
/*  467 */         dir = true;
/*      */       }
/*      */       else {
/*      */         Entry s;
/*      */         while (true) {
/*  472 */           s = r.left;
/*  473 */           if (s.pred()) break;
/*  474 */           r = s;
/*      */         }
/*  476 */         if (s.succ()) r.pred(s); else
/*  477 */           r.left = s.right;
/*  478 */         s.left = p.left;
/*  479 */         if (!p.pred()) {
/*  480 */           p.prev().right = s;
/*  481 */           s.pred(false);
/*      */         }
/*  483 */         s.right = p.right;
/*  484 */         s.succ(false);
/*  485 */         if (q != null) {
/*  486 */           if (dir) q.right = s; else
/*  487 */             q.left = s;
/*      */         }
/*  489 */         else this.tree = s;
/*  490 */         s.balance(p.balance());
/*  491 */         q = r;
/*  492 */         dir = false;
/*      */       }
/*      */     }
/*      */ 
/*  496 */     while (q != null) {
/*  497 */       Entry y = q;
/*  498 */       q = parent(y);
/*  499 */       if (!dir) {
/*  500 */         dir = (q != null) && (q.left != y);
/*  501 */         y.incBalance();
/*  502 */         if (y.balance() == 1) break;
/*  503 */         if (y.balance() == 2) {
/*  504 */           Entry x = y.right;
/*      */ 
/*  506 */           if (x.balance() == -1)
/*      */           {
/*  509 */             Entry w = x.left;
/*  510 */             x.left = w.right;
/*  511 */             w.right = x;
/*  512 */             y.right = w.left;
/*  513 */             w.left = y;
/*  514 */             if (w.balance() == 1) {
/*  515 */               x.balance(0);
/*  516 */               y.balance(-1);
/*      */             }
/*  518 */             else if (w.balance() == 0) {
/*  519 */               x.balance(0);
/*  520 */               y.balance(0);
/*      */             }
/*      */             else
/*      */             {
/*  524 */               x.balance(1);
/*  525 */               y.balance(0);
/*      */             }
/*  527 */             w.balance(0);
/*  528 */             if (w.pred()) {
/*  529 */               y.succ(w);
/*  530 */               w.pred(false);
/*      */             }
/*  532 */             if (w.succ()) {
/*  533 */               x.pred(w);
/*  534 */               w.succ(false);
/*      */             }
/*  536 */             if (q != null) {
/*  537 */               if (dir) q.right = w; else
/*  538 */                 q.left = w;
/*      */             }
/*  540 */             else this.tree = w; 
/*      */           }
/*      */           else
/*      */           {
/*  543 */             if (q != null) {
/*  544 */               if (dir) q.right = x; else
/*  545 */                 q.left = x;
/*      */             }
/*  547 */             else this.tree = x;
/*  548 */             if (x.balance() == 0) {
/*  549 */               y.right = x.left;
/*  550 */               x.left = y;
/*  551 */               x.balance(-1);
/*  552 */               y.balance(1);
/*  553 */               break;
/*      */             }
/*      */ 
/*  556 */             if (x.pred()) {
/*  557 */               y.succ(true);
/*  558 */               x.pred(false);
/*      */             } else {
/*  560 */               y.right = x.left;
/*  561 */             }x.left = y;
/*  562 */             y.balance(0);
/*  563 */             x.balance(0);
/*      */           }
/*      */         }
/*      */       }
/*      */       else {
/*  568 */         dir = (q != null) && (q.left != y);
/*  569 */         y.decBalance();
/*  570 */         if (y.balance() == -1) break;
/*  571 */         if (y.balance() == -2) {
/*  572 */           Entry x = y.left;
/*      */ 
/*  574 */           if (x.balance() == 1)
/*      */           {
/*  577 */             Entry w = x.right;
/*  578 */             x.right = w.left;
/*  579 */             w.left = x;
/*  580 */             y.left = w.right;
/*  581 */             w.right = y;
/*  582 */             if (w.balance() == -1) {
/*  583 */               x.balance(0);
/*  584 */               y.balance(1);
/*      */             }
/*  586 */             else if (w.balance() == 0) {
/*  587 */               x.balance(0);
/*  588 */               y.balance(0);
/*      */             }
/*      */             else
/*      */             {
/*  592 */               x.balance(-1);
/*  593 */               y.balance(0);
/*      */             }
/*  595 */             w.balance(0);
/*  596 */             if (w.pred()) {
/*  597 */               x.succ(w);
/*  598 */               w.pred(false);
/*      */             }
/*  600 */             if (w.succ()) {
/*  601 */               y.pred(w);
/*  602 */               w.succ(false);
/*      */             }
/*  604 */             if (q != null) {
/*  605 */               if (dir) q.right = w; else
/*  606 */                 q.left = w;
/*      */             }
/*  608 */             else this.tree = w; 
/*      */           }
/*      */           else
/*      */           {
/*  611 */             if (q != null) {
/*  612 */               if (dir) q.right = x; else
/*  613 */                 q.left = x;
/*      */             }
/*  615 */             else this.tree = x;
/*  616 */             if (x.balance() == 0) {
/*  617 */               y.left = x.right;
/*  618 */               x.right = y;
/*  619 */               x.balance(1);
/*  620 */               y.balance(-1);
/*  621 */               break;
/*      */             }
/*      */ 
/*  624 */             if (x.succ()) {
/*  625 */               y.pred(true);
/*  626 */               x.succ(false);
/*      */             } else {
/*  628 */               y.left = x.right;
/*  629 */             }x.right = y;
/*  630 */             y.balance(0);
/*  631 */             x.balance(0);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  636 */     this.modified = true;
/*  637 */     this.count -= 1;
/*      */ 
/*  639 */     return p.value;
/*      */   }
/*      */   public Integer put(K ok, Integer ov) {
/*  642 */     int oldValue = put(ok, ov.intValue());
/*  643 */     return this.modified ? null : Integer.valueOf(oldValue);
/*      */   }
/*      */   public Integer remove(Object ok) {
/*  646 */     int oldValue = removeInt(ok);
/*  647 */     return this.modified ? Integer.valueOf(oldValue) : null;
/*      */   }
/*      */   public boolean containsValue(int v) {
/*  650 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  652 */     int j = this.count;
/*  653 */     while (j-- != 0) {
/*  654 */       int ev = i.nextInt();
/*  655 */       if (ev == v) return true;
/*      */     }
/*  657 */     return false;
/*      */   }
/*      */   public void clear() {
/*  660 */     this.count = 0;
/*  661 */     this.tree = null;
/*  662 */     this.entries = null;
/*  663 */     this.values = null;
/*  664 */     this.keys = null;
/*  665 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  887 */     return findKey(k) != null;
/*      */   }
/*      */   public int size() {
/*  890 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/*  893 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public int getInt(Object k) {
/*  897 */     Entry e = findKey(k);
/*  898 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */ 
/*      */   public Integer get(Object ok) {
/*  902 */     Entry e = findKey(ok);
/*  903 */     return e == null ? null : e.getValue();
/*      */   }
/*      */   public K firstKey() {
/*  906 */     if (this.tree == null) throw new NoSuchElementException();
/*  907 */     return this.firstEntry.key;
/*      */   }
/*      */   public K lastKey() {
/*  910 */     if (this.tree == null) throw new NoSuchElementException();
/*  911 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Object2IntMap.Entry<K>> object2IntEntrySet()
/*      */   {
/* 1003 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1004 */         final Comparator<? super Object2IntMap.Entry<K>> comparator = new Comparator() {
/*      */           public int compare(Object2IntMap.Entry<K> x, Object2IntMap.Entry<K> y) {
/* 1006 */             return Object2IntAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/* 1004 */         };
/*      */ 
/*      */         public Comparator<? super Object2IntMap.Entry<K>> comparator()
/*      */         {
/* 1010 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2IntMap.Entry<K>> iterator() {
/* 1013 */           return new Object2IntAVLTreeMap.EntryIterator(Object2IntAVLTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2IntMap.Entry<K>> iterator(Object2IntMap.Entry<K> from) {
/* 1016 */           return new Object2IntAVLTreeMap.EntryIterator(Object2IntAVLTreeMap.this, from.getKey());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/* 1020 */           if (!(o instanceof Map.Entry)) return false;
/* 1021 */           Map.Entry e = (Map.Entry)o;
/* 1022 */           Object2IntAVLTreeMap.Entry f = Object2IntAVLTreeMap.this.findKey(e.getKey());
/* 1023 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/* 1027 */           if (!(o instanceof Map.Entry)) return false;
/* 1028 */           Map.Entry e = (Map.Entry)o;
/* 1029 */           Object2IntAVLTreeMap.Entry f = Object2IntAVLTreeMap.this.findKey(e.getKey());
/* 1030 */           if (f != null) Object2IntAVLTreeMap.this.removeInt(f.key);
/* 1031 */           return f != null;
/*      */         }
/* 1033 */         public int size() { return Object2IntAVLTreeMap.this.count; } 
/* 1034 */         public void clear() { Object2IntAVLTreeMap.this.clear(); } 
/* 1035 */         public Object2IntMap.Entry<K> first() { return Object2IntAVLTreeMap.this.firstEntry; } 
/* 1036 */         public Object2IntMap.Entry<K> last() { return Object2IntAVLTreeMap.this.lastEntry; } 
/* 1037 */         public ObjectSortedSet<Object2IntMap.Entry<K>> subSet(Object2IntMap.Entry<K> from, Object2IntMap.Entry<K> to) { return Object2IntAVLTreeMap.this.subMap(from.getKey(), to.getKey()).object2IntEntrySet(); } 
/* 1038 */         public ObjectSortedSet<Object2IntMap.Entry<K>> headSet(Object2IntMap.Entry<K> to) { return Object2IntAVLTreeMap.this.headMap(to.getKey()).object2IntEntrySet(); } 
/* 1039 */         public ObjectSortedSet<Object2IntMap.Entry<K>> tailSet(Object2IntMap.Entry<K> from) { return Object2IntAVLTreeMap.this.tailMap(from.getKey()).object2IntEntrySet(); }
/*      */       };
/* 1041 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/* 1071 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1072 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public IntCollection values()
/*      */   {
/* 1099 */     if (this.values == null) this.values = new AbstractIntCollection() {
/*      */         public IntIterator iterator() {
/* 1101 */           return new Object2IntAVLTreeMap.ValueIterator(Object2IntAVLTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(int k) {
/* 1104 */           return Object2IntAVLTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1107 */           return Object2IntAVLTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1110 */           Object2IntAVLTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1113 */     return this.values;
/*      */   }
/*      */   public Comparator<? super K> comparator() {
/* 1116 */     return this.actualComparator;
/*      */   }
/*      */   public Object2IntSortedMap<K> headMap(K to) {
/* 1119 */     return new Submap(null, true, to, false);
/*      */   }
/*      */   public Object2IntSortedMap<K> tailMap(K from) {
/* 1122 */     return new Submap(from, false, null, true);
/*      */   }
/*      */   public Object2IntSortedMap<K> subMap(K from, K to) {
/* 1125 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Object2IntAVLTreeMap<K> clone()
/*      */   {
/*      */     Object2IntAVLTreeMap c;
/*      */     try
/*      */     {
/* 1466 */       c = (Object2IntAVLTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1469 */       throw new InternalError();
/*      */     }
/* 1471 */     c.keys = null;
/* 1472 */     c.values = null;
/* 1473 */     c.entries = null;
/* 1474 */     c.allocatePaths();
/* 1475 */     if (this.count != 0)
/*      */     {
/* 1477 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1478 */       Entry p = rp;
/* 1479 */       rp.left(this.tree);
/* 1480 */       Entry q = rq;
/* 1481 */       rq.pred(null);
/*      */       while (true) {
/* 1483 */         if (!p.pred()) {
/* 1484 */           Entry e = p.left.clone();
/* 1485 */           e.pred(q.left);
/* 1486 */           e.succ(q);
/* 1487 */           q.left(e);
/* 1488 */           p = p.left;
/* 1489 */           q = q.left;
/*      */         }
/*      */         else {
/* 1492 */           while (p.succ()) {
/* 1493 */             p = p.right;
/* 1494 */             if (p == null) {
/* 1495 */               q.right = null;
/* 1496 */               c.tree = rq.left;
/* 1497 */               c.firstEntry = c.tree;
/* 1498 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1499 */               c.lastEntry = c.tree;
/* 1500 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1501 */               return c;
/*      */             }
/* 1503 */             q = q.right;
/*      */           }
/* 1505 */           p = p.right;
/* 1506 */           q = q.right;
/*      */         }
/* 1508 */         if (!p.succ()) {
/* 1509 */           Entry e = p.right.clone();
/* 1510 */           e.succ(q.right);
/* 1511 */           e.pred(q);
/* 1512 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1516 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1519 */     int n = this.count;
/* 1520 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1522 */     s.defaultWriteObject();
/* 1523 */     while (n-- != 0) {
/* 1524 */       Entry e = i.nextEntry();
/* 1525 */       s.writeObject(e.key);
/* 1526 */       s.writeInt(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<K> readTree(ObjectInputStream s, int n, Entry<K> pred, Entry<K> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1538 */     if (n == 1) {
/* 1539 */       Entry top = new Entry(s.readObject(), s.readInt());
/* 1540 */       top.pred(pred);
/* 1541 */       top.succ(succ);
/* 1542 */       return top;
/*      */     }
/* 1544 */     if (n == 2)
/*      */     {
/* 1547 */       Entry top = new Entry(s.readObject(), s.readInt());
/* 1548 */       top.right(new Entry(s.readObject(), s.readInt()));
/* 1549 */       top.right.pred(top);
/* 1550 */       top.balance(1);
/* 1551 */       top.pred(pred);
/* 1552 */       top.right.succ(succ);
/* 1553 */       return top;
/*      */     }
/*      */ 
/* 1556 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1557 */     Entry top = new Entry();
/* 1558 */     top.left(readTree(s, leftN, pred, top));
/* 1559 */     top.key = s.readObject();
/* 1560 */     top.value = s.readInt();
/* 1561 */     top.right(readTree(s, rightN, top, succ));
/* 1562 */     if (n == (n & -n)) top.balance(1);
/* 1563 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1566 */     s.defaultReadObject();
/*      */ 
/* 1569 */     setActualComparator();
/* 1570 */     allocatePaths();
/* 1571 */     if (this.count != 0) {
/* 1572 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1574 */       Entry e = this.tree;
/* 1575 */       while (e.left() != null) e = e.left();
/* 1576 */       this.firstEntry = e;
/* 1577 */       e = this.tree;
/* 1578 */       while (e.right() != null) e = e.right();
/* 1579 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static int checkTree(Entry e) {
/* 1584 */     return 0;
/*      */   }
/*      */ 
/*      */   private final class Submap extends AbstractObject2IntSortedMap<K>
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     K from;
/*      */     K to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Object2IntMap.Entry<K>> entries;
/*      */     protected volatile transient ObjectSortedSet<K> keys;
/*      */     protected volatile transient IntCollection values;
/*      */ 
/*      */     public Submap(boolean from, K bottom, boolean to)
/*      */     {
/* 1163 */       if ((!bottom) && (!top) && (Object2IntAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1164 */       this.from = from;
/* 1165 */       this.bottom = bottom;
/* 1166 */       this.to = to;
/* 1167 */       this.top = top;
/* 1168 */       this.defRetValue = Object2IntAVLTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1171 */       SubmapIterator i = new SubmapIterator();
/* 1172 */       while (i.hasNext()) {
/* 1173 */         i.nextEntry();
/* 1174 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(K k)
/*      */     {
/* 1182 */       return ((this.bottom) || (Object2IntAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2IntAVLTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Object2IntMap.Entry<K>> object2IntEntrySet() {
/* 1186 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Object2IntMap.Entry<K>> iterator() {
/* 1188 */             return new Object2IntAVLTreeMap.Submap.SubmapEntryIterator(Object2IntAVLTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Object2IntMap.Entry<K>> iterator(Object2IntMap.Entry<K> from) {
/* 1191 */             return new Object2IntAVLTreeMap.Submap.SubmapEntryIterator(Object2IntAVLTreeMap.Submap.this, from.getKey());
/*      */           }
/* 1193 */           public Comparator<? super Object2IntMap.Entry<K>> comparator() { return Object2IntAVLTreeMap.this.entrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1196 */             if (!(o instanceof Map.Entry)) return false;
/* 1197 */             Map.Entry e = (Map.Entry)o;
/* 1198 */             Object2IntAVLTreeMap.Entry f = Object2IntAVLTreeMap.this.findKey(e.getKey());
/* 1199 */             return (f != null) && (Object2IntAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1203 */             if (!(o instanceof Map.Entry)) return false;
/* 1204 */             Map.Entry e = (Map.Entry)o;
/* 1205 */             Object2IntAVLTreeMap.Entry f = Object2IntAVLTreeMap.this.findKey(e.getKey());
/* 1206 */             if ((f != null) && (Object2IntAVLTreeMap.Submap.this.in(f.key))) Object2IntAVLTreeMap.Submap.this.removeInt(f.key);
/* 1207 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1210 */             int c = 0;
/* 1211 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1212 */             return c;
/*      */           }
/*      */           public boolean isEmpty() {
/* 1215 */             return !new Object2IntAVLTreeMap.Submap.SubmapIterator(Object2IntAVLTreeMap.Submap.this).hasNext();
/*      */           }
/*      */           public void clear() {
/* 1218 */             Object2IntAVLTreeMap.Submap.this.clear();
/*      */           }
/* 1220 */           public Object2IntMap.Entry<K> first() { return Object2IntAVLTreeMap.Submap.this.firstEntry(); } 
/* 1221 */           public Object2IntMap.Entry<K> last() { return Object2IntAVLTreeMap.Submap.this.lastEntry(); } 
/* 1222 */           public ObjectSortedSet<Object2IntMap.Entry<K>> subSet(Object2IntMap.Entry<K> from, Object2IntMap.Entry<K> to) { return Object2IntAVLTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2IntEntrySet(); } 
/* 1223 */           public ObjectSortedSet<Object2IntMap.Entry<K>> headSet(Object2IntMap.Entry<K> to) { return Object2IntAVLTreeMap.Submap.this.headMap(to.getKey()).object2IntEntrySet(); } 
/* 1224 */           public ObjectSortedSet<Object2IntMap.Entry<K>> tailSet(Object2IntMap.Entry<K> from) { return Object2IntAVLTreeMap.Submap.this.tailMap(from.getKey()).object2IntEntrySet(); }
/*      */         };
/* 1226 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<K> keySet()
/*      */     {
/* 1233 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1234 */       return this.keys;
/*      */     }
/*      */     public IntCollection values() {
/* 1237 */       if (this.values == null) this.values = new AbstractIntCollection() {
/*      */           public IntIterator iterator() {
/* 1239 */             return new Object2IntAVLTreeMap.Submap.SubmapValueIterator(Object2IntAVLTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(int k) {
/* 1242 */             return Object2IntAVLTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1245 */             return Object2IntAVLTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1248 */             Object2IntAVLTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1251 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(Object k) {
/* 1255 */       return (in(k)) && (Object2IntAVLTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(int v) {
/* 1258 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1260 */       while (i.hasNext()) {
/* 1261 */         int ev = i.nextEntry().value;
/* 1262 */         if (ev == v) return true;
/*      */       }
/* 1264 */       return false;
/*      */     }
/*      */ 
/*      */     public int getInt(Object k)
/*      */     {
/* 1269 */       Object kk = k;
/*      */       Object2IntAVLTreeMap.Entry e;
/* 1270 */       return (in(kk)) && ((e = Object2IntAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */ 
/*      */     public Integer get(Object ok)
/*      */     {
/* 1275 */       Object kk = ok;
/*      */       Object2IntAVLTreeMap.Entry e;
/* 1276 */       return (in(kk)) && ((e = Object2IntAVLTreeMap.this.findKey(kk)) != null) ? e.getValue() : null;
/*      */     }
/*      */     public int put(K k, int v) {
/* 1279 */       Object2IntAVLTreeMap.this.modified = false;
/* 1280 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1281 */       int oldValue = Object2IntAVLTreeMap.this.put(k, v);
/* 1282 */       return Object2IntAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */     public Integer put(K ok, Integer ov) {
/* 1285 */       int oldValue = put(ok, ov.intValue());
/* 1286 */       return Object2IntAVLTreeMap.this.modified ? null : Integer.valueOf(oldValue);
/*      */     }
/*      */ 
/*      */     public int removeInt(Object k) {
/* 1290 */       Object2IntAVLTreeMap.this.modified = false;
/* 1291 */       if (!in(k)) return this.defRetValue;
/* 1292 */       int oldValue = Object2IntAVLTreeMap.this.removeInt(k);
/* 1293 */       return Object2IntAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public Integer remove(Object ok) {
/* 1296 */       int oldValue = removeInt(ok);
/* 1297 */       return Object2IntAVLTreeMap.this.modified ? Integer.valueOf(oldValue) : null;
/*      */     }
/*      */     public int size() {
/* 1300 */       SubmapIterator i = new SubmapIterator();
/* 1301 */       int n = 0;
/* 1302 */       while (i.hasNext()) {
/* 1303 */         n++;
/* 1304 */         i.nextEntry();
/*      */       }
/* 1306 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1309 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public Comparator<? super K> comparator() {
/* 1312 */       return Object2IntAVLTreeMap.this.actualComparator;
/*      */     }
/*      */     public Object2IntSortedMap<K> headMap(K to) {
/* 1315 */       if (this.top) return new Submap(Object2IntAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1316 */       return Object2IntAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2IntAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Object2IntSortedMap<K> tailMap(K from) {
/* 1319 */       if (this.bottom) return new Submap(Object2IntAVLTreeMap.this, from, false, this.to, this.top);
/* 1320 */       return Object2IntAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2IntAVLTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Object2IntSortedMap<K> subMap(K from, K to) {
/* 1323 */       if ((this.top) && (this.bottom)) return new Submap(Object2IntAVLTreeMap.this, from, false, to, false);
/* 1324 */       if (!this.top) to = Object2IntAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1325 */       if (!this.bottom) from = Object2IntAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1326 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1327 */       return new Submap(Object2IntAVLTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Object2IntAVLTreeMap.Entry<K> firstEntry()
/*      */     {
/* 1334 */       if (Object2IntAVLTreeMap.this.tree == null) return null;
/* 1337 */       Object2IntAVLTreeMap.Entry e;
/*      */       Object2IntAVLTreeMap.Entry e;
/* 1337 */       if (this.bottom) { e = Object2IntAVLTreeMap.this.firstEntry;
/*      */       } else {
/* 1339 */         e = Object2IntAVLTreeMap.this.locateKey(this.from);
/*      */ 
/* 1341 */         if (Object2IntAVLTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1344 */       if ((e == null) || ((!this.top) && (Object2IntAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1345 */       return e;
/*      */     }
/*      */ 
/*      */     public Object2IntAVLTreeMap.Entry<K> lastEntry()
/*      */     {
/* 1352 */       if (Object2IntAVLTreeMap.this.tree == null) return null;
/* 1355 */       Object2IntAVLTreeMap.Entry e;
/*      */       Object2IntAVLTreeMap.Entry e;
/* 1355 */       if (this.top) { e = Object2IntAVLTreeMap.this.lastEntry;
/*      */       } else {
/* 1357 */         e = Object2IntAVLTreeMap.this.locateKey(this.to);
/*      */ 
/* 1359 */         if (Object2IntAVLTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1362 */       if ((e == null) || ((!this.bottom) && (Object2IntAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1363 */       return e;
/*      */     }
/*      */     public K firstKey() {
/* 1366 */       Object2IntAVLTreeMap.Entry e = firstEntry();
/* 1367 */       if (e == null) throw new NoSuchElementException();
/* 1368 */       return e.key;
/*      */     }
/*      */     public K lastKey() {
/* 1371 */       Object2IntAVLTreeMap.Entry e = lastEntry();
/* 1372 */       if (e == null) throw new NoSuchElementException();
/* 1373 */       return e.key;
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Object2IntAVLTreeMap.Submap.SubmapIterator
/*      */       implements IntListIterator
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1444 */         super(); } 
/* 1445 */       public int nextInt() { return nextEntry().value; } 
/* 1446 */       public int previousInt() { return previousEntry().value; } 
/* 1447 */       public void set(int v) { throw new UnsupportedOperationException(); } 
/* 1448 */       public void add(int v) { throw new UnsupportedOperationException(); } 
/* 1449 */       public Integer next() { return Integer.valueOf(nextEntry().value); } 
/* 1450 */       public Integer previous() { return Integer.valueOf(previousEntry().value); } 
/* 1451 */       public void set(Integer ok) { throw new UnsupportedOperationException(); } 
/* 1452 */       public void add(Integer ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Object2IntAVLTreeMap<K>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<K>
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1429 */         super(); } 
/* 1430 */       public SubmapKeyIterator() { super(from); } 
/* 1431 */       public K next() { return nextEntry().key; } 
/* 1432 */       public K previous() { return previousEntry().key; } 
/* 1433 */       public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1434 */       public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Object2IntAVLTreeMap<K>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Object2IntMap.Entry<K>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1411 */         super();
/*      */       }
/* 1413 */       SubmapEntryIterator() { super(k); } 
/*      */       public Object2IntMap.Entry<K> next() {
/* 1415 */         return nextEntry(); } 
/* 1416 */       public Object2IntMap.Entry<K> previous() { return previousEntry(); } 
/* 1417 */       public void set(Object2IntMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/* 1418 */       public void add(Object2IntMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Object2IntAVLTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1383 */         super();
/* 1384 */         this.next = Object2IntAVLTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator() {
/* 1387 */         this();
/* 1388 */         if (this.next != null)
/* 1389 */           if ((!Object2IntAVLTreeMap.Submap.this.bottom) && (Object2IntAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1390 */           } else if ((!Object2IntAVLTreeMap.Submap.this.top) && (Object2IntAVLTreeMap.this.compare(k, (this.prev = Object2IntAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1392 */             this.next = Object2IntAVLTreeMap.this.locateKey(k);
/* 1393 */             if (Object2IntAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1394 */               this.prev = this.next;
/* 1395 */               this.next = this.next.next();
/*      */             } else {
/* 1397 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1402 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1403 */         if ((!Object2IntAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2IntAVLTreeMap.this.compare(this.prev.key, Object2IntAVLTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1406 */         this.next = this.next.next();
/* 1407 */         if ((!Object2IntAVLTreeMap.Submap.this.top) && (this.next != null) && (Object2IntAVLTreeMap.this.compare(this.next.key, Object2IntAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractObject2IntSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1228 */         super(); } 
/* 1229 */       public ObjectBidirectionalIterator<K> iterator() { return new Object2IntAVLTreeMap.Submap.SubmapKeyIterator(Object2IntAVLTreeMap.Submap.this); } 
/* 1230 */       public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2IntAVLTreeMap.Submap.SubmapKeyIterator(Object2IntAVLTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2IntAVLTreeMap.TreeIterator
/*      */     implements IntListIterator
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1080 */       super(); } 
/* 1081 */     public int nextInt() { return nextEntry().value; } 
/* 1082 */     public int previousInt() { return previousEntry().value; } 
/* 1083 */     public void set(int v) { throw new UnsupportedOperationException(); } 
/* 1084 */     public void add(int v) { throw new UnsupportedOperationException(); } 
/* 1085 */     public Integer next() { return Integer.valueOf(nextEntry().value); } 
/* 1086 */     public Integer previous() { return Integer.valueOf(previousEntry().value); } 
/* 1087 */     public void set(Integer ok) { throw new UnsupportedOperationException(); } 
/* 1088 */     public void add(Integer ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractObject2IntSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1058 */       super(); } 
/* 1059 */     public ObjectBidirectionalIterator<K> iterator() { return new Object2IntAVLTreeMap.KeyIterator(Object2IntAVLTreeMap.this); } 
/* 1060 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2IntAVLTreeMap.KeyIterator(Object2IntAVLTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2IntAVLTreeMap<K>.TreeIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1050 */       super(); } 
/* 1051 */     public KeyIterator() { super(k); } 
/* 1052 */     public K next() { return nextEntry().key; } 
/* 1053 */     public K previous() { return previousEntry().key; } 
/* 1054 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1055 */     public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2IntAVLTreeMap<K>.TreeIterator
/*      */     implements ObjectListIterator<Object2IntMap.Entry<K>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/*  993 */       super();
/*      */     }
/*  995 */     EntryIterator() { super(k); } 
/*      */     public Object2IntMap.Entry<K> next() {
/*  997 */       return nextEntry(); } 
/*  998 */     public Object2IntMap.Entry<K> previous() { return previousEntry(); } 
/*  999 */     public void set(Object2IntMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/* 1000 */     public void add(Object2IntMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Object2IntAVLTreeMap.Entry<K> prev;
/*      */     Object2IntAVLTreeMap.Entry<K> next;
/*      */     Object2IntAVLTreeMap.Entry<K> curr;
/*  925 */     int index = 0;
/*      */ 
/*  927 */     TreeIterator() { this.next = Object2IntAVLTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator() {
/*  930 */       if ((this.next = Object2IntAVLTreeMap.this.locateKey(k)) != null)
/*  931 */         if (Object2IntAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/*  932 */           this.prev = this.next;
/*  933 */           this.next = this.next.next();
/*      */         } else {
/*  935 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/*  938 */     public boolean hasNext() { return this.next != null; } 
/*  939 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/*  941 */       this.next = this.next.next();
/*      */     }
/*      */     Object2IntAVLTreeMap.Entry<K> nextEntry() {
/*  944 */       if (!hasNext()) throw new NoSuchElementException();
/*  945 */       this.curr = (this.prev = this.next);
/*  946 */       this.index += 1;
/*  947 */       updateNext();
/*  948 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/*  951 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Object2IntAVLTreeMap.Entry<K> previousEntry() {
/*  954 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  955 */       this.curr = (this.next = this.prev);
/*  956 */       this.index -= 1;
/*  957 */       updatePrevious();
/*  958 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/*  961 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/*  964 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/*  967 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/*  970 */       if (this.curr == this.prev) this.index -= 1;
/*  971 */       this.next = (this.prev = this.curr);
/*  972 */       updatePrevious();
/*  973 */       updateNext();
/*  974 */       Object2IntAVLTreeMap.this.removeInt(this.curr.key);
/*  975 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/*  978 */       int i = n;
/*  979 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  980 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  983 */       int i = n;
/*  984 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  985 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry<K>
/*      */     implements Cloneable, Object2IntMap.Entry<K>
/*      */   {
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     private static final int BALANCE_MASK = 255;
/*      */     K key;
/*      */     int value;
/*      */     Entry<K> left;
/*      */     Entry<K> right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(K k, int v)
/*      */     {
/*  697 */       this.key = k;
/*  698 */       this.value = v;
/*  699 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry<K> left()
/*      */     {
/*  707 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry<K> right()
/*      */     {
/*  715 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  721 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  727 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  733 */       if (pred) this.info |= 1073741824; else
/*  734 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  740 */       if (succ) this.info |= -2147483648; else
/*  741 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry<K> pred)
/*      */     {
/*  747 */       this.info |= 1073741824;
/*  748 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry<K> succ)
/*      */     {
/*  754 */       this.info |= -2147483648;
/*  755 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry<K> left)
/*      */     {
/*  761 */       this.info &= -1073741825;
/*  762 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry<K> right)
/*      */     {
/*  768 */       this.info &= 2147483647;
/*  769 */       this.right = right;
/*      */     }
/*      */ 
/*      */     int balance()
/*      */     {
/*  775 */       return (byte)this.info;
/*      */     }
/*      */ 
/*      */     void balance(int level)
/*      */     {
/*  781 */       this.info &= -256;
/*  782 */       this.info |= level & 0xFF;
/*      */     }
/*      */ 
/*      */     void incBalance() {
/*  786 */       this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*      */     }
/*      */ 
/*      */     protected void decBalance() {
/*  790 */       this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*      */     }
/*      */ 
/*      */     Entry<K> next()
/*      */     {
/*  797 */       Entry next = this.right;
/*  798 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  799 */       return next;
/*      */     }
/*      */ 
/*      */     Entry<K> prev()
/*      */     {
/*  806 */       Entry prev = this.left;
/*  807 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  808 */       return prev;
/*      */     }
/*      */     public K getKey() {
/*  811 */       return this.key;
/*      */     }
/*      */     public Integer getValue() {
/*  814 */       return Integer.valueOf(this.value);
/*      */     }
/*      */     public int getIntValue() {
/*  817 */       return this.value;
/*      */     }
/*      */     public int setValue(int value) {
/*  820 */       int oldValue = this.value;
/*  821 */       this.value = value;
/*  822 */       return oldValue;
/*      */     }
/*      */     public Integer setValue(Integer value) {
/*  825 */       return Integer.valueOf(setValue(value.intValue()));
/*      */     }
/*      */ 
/*      */     public Entry<K> clone() {
/*      */       Entry c;
/*      */       try {
/*  831 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  834 */         throw new InternalError();
/*      */       }
/*  836 */       c.key = this.key;
/*  837 */       c.value = this.value;
/*  838 */       c.info = this.info;
/*  839 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  843 */       if (!(o instanceof Map.Entry)) return false;
/*  844 */       Map.Entry e = (Map.Entry)o;
/*  845 */       return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == ((Integer)e.getValue()).intValue());
/*      */     }
/*      */     public int hashCode() {
/*  848 */       return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
/*      */     }
/*      */     public String toString() {
/*  851 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2IntAVLTreeMap
 * JD-Core Version:    0.6.2
 */