/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
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
/*      */ public class Object2ReferenceAVLTreeMap<K, V> extends AbstractObject2ReferenceSortedMap<K, V>
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry<K, V> tree;
/*      */   protected int count;
/*      */   protected transient Entry<K, V> firstEntry;
/*      */   protected transient Entry<K, V> lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
/*      */   protected volatile transient ObjectSortedSet<K> keys;
/*      */   protected volatile transient ReferenceCollection<V> values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super K> storedComparator;
/*      */   protected transient Comparator<? super K> actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap()
/*      */   {
/*   90 */     allocatePaths();
/*      */ 
/*   95 */     this.tree = null;
/*   96 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  110 */     this.actualComparator = this.storedComparator;
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(Comparator<? super K> c)
/*      */   {
/*  117 */     this();
/*  118 */     this.storedComparator = c;
/*  119 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(Map<? extends K, ? extends V> m)
/*      */   {
/*  126 */     this();
/*  127 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(SortedMap<K, V> m)
/*      */   {
/*  134 */     this(m.comparator());
/*  135 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(Object2ReferenceMap<? extends K, ? extends V> m)
/*      */   {
/*  142 */     this();
/*  143 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(Object2ReferenceSortedMap<K, V> m)
/*      */   {
/*  150 */     this(m.comparator());
/*  151 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(K[] k, V[] v, Comparator<? super K> c)
/*      */   {
/*  161 */     this(c);
/*  162 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  163 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap(K[] k, V[] v)
/*      */   {
/*  172 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(K k1, K k2)
/*      */   {
/*  195 */     return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<K, V> findKey(K k)
/*      */   {
/*  203 */     Entry e = this.tree;
/*      */     int cmp;
/*  205 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  206 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<K, V> locateKey(K k)
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
/*      */   public V put(K k, V v)
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
/*  243 */           Object oldValue = p.value;
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
/*      */   private Entry<K, V> parent(Entry<K, V> e)
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
/*      */   public V remove(Object k)
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
/*      */   public boolean containsValue(Object v) {
/*  642 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  644 */     int j = this.count;
/*  645 */     while (j-- != 0) {
/*  646 */       Object ev = i.next();
/*  647 */       if (ev == v) return true;
/*      */     }
/*  649 */     return false;
/*      */   }
/*      */   public void clear() {
/*  652 */     this.count = 0;
/*  653 */     this.tree = null;
/*  654 */     this.entries = null;
/*  655 */     this.values = null;
/*  656 */     this.keys = null;
/*  657 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  873 */     return findKey(k) != null;
/*      */   }
/*      */   public int size() {
/*  876 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/*  879 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public V get(Object k) {
/*  883 */     Entry e = findKey(k);
/*  884 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */   public K firstKey() {
/*  887 */     if (this.tree == null) throw new NoSuchElementException();
/*  888 */     return this.firstEntry.key;
/*      */   }
/*      */   public K lastKey() {
/*  891 */     if (this.tree == null) throw new NoSuchElementException();
/*  892 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
/*      */   {
/*  984 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  985 */         final Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator = new Comparator() {
/*      */           public int compare(Object2ReferenceMap.Entry<K, V> x, Object2ReferenceMap.Entry<K, V> y) {
/*  987 */             return Object2ReferenceAVLTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/*  985 */         };
/*      */ 
/*      */         public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator()
/*      */         {
/*  991 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/*  994 */           return new Object2ReferenceAVLTreeMap.EntryIterator(Object2ReferenceAVLTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) {
/*  997 */           return new Object2ReferenceAVLTreeMap.EntryIterator(Object2ReferenceAVLTreeMap.this, from.getKey());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/* 1001 */           if (!(o instanceof Map.Entry)) return false;
/* 1002 */           Map.Entry e = (Map.Entry)o;
/* 1003 */           Object2ReferenceAVLTreeMap.Entry f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1004 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/* 1008 */           if (!(o instanceof Map.Entry)) return false;
/* 1009 */           Map.Entry e = (Map.Entry)o;
/* 1010 */           Object2ReferenceAVLTreeMap.Entry f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1011 */           if (f != null) Object2ReferenceAVLTreeMap.this.remove(f.key);
/* 1012 */           return f != null;
/*      */         }
/* 1014 */         public int size() { return Object2ReferenceAVLTreeMap.this.count; } 
/* 1015 */         public void clear() { Object2ReferenceAVLTreeMap.this.clear(); } 
/* 1016 */         public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceAVLTreeMap.this.firstEntry; } 
/* 1017 */         public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceAVLTreeMap.this.lastEntry; } 
/* 1018 */         public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); } 
/* 1019 */         public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.this.headMap(to.getKey()).object2ReferenceEntrySet(); } 
/* 1020 */         public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceAVLTreeMap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/*      */       };
/* 1022 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/* 1052 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1053 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ReferenceCollection<V> values()
/*      */   {
/* 1076 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */         public ObjectIterator<V> iterator() {
/* 1078 */           return new Object2ReferenceAVLTreeMap.ValueIterator(Object2ReferenceAVLTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(Object k) {
/* 1081 */           return Object2ReferenceAVLTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1084 */           return Object2ReferenceAVLTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1087 */           Object2ReferenceAVLTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1090 */     return this.values;
/*      */   }
/*      */   public Comparator<? super K> comparator() {
/* 1093 */     return this.actualComparator;
/*      */   }
/*      */   public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1096 */     return new Submap(null, true, to, false);
/*      */   }
/*      */   public Object2ReferenceSortedMap<K, V> tailMap(K from) {
/* 1099 */     return new Submap(from, false, null, true);
/*      */   }
/*      */   public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 1102 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceAVLTreeMap<K, V> clone()
/*      */   {
/*      */     Object2ReferenceAVLTreeMap c;
/*      */     try
/*      */     {
/* 1425 */       c = (Object2ReferenceAVLTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1428 */       throw new InternalError();
/*      */     }
/* 1430 */     c.keys = null;
/* 1431 */     c.values = null;
/* 1432 */     c.entries = null;
/* 1433 */     c.allocatePaths();
/* 1434 */     if (this.count != 0)
/*      */     {
/* 1436 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1437 */       Entry p = rp;
/* 1438 */       rp.left(this.tree);
/* 1439 */       Entry q = rq;
/* 1440 */       rq.pred(null);
/*      */       while (true) {
/* 1442 */         if (!p.pred()) {
/* 1443 */           Entry e = p.left.clone();
/* 1444 */           e.pred(q.left);
/* 1445 */           e.succ(q);
/* 1446 */           q.left(e);
/* 1447 */           p = p.left;
/* 1448 */           q = q.left;
/*      */         }
/*      */         else {
/* 1451 */           while (p.succ()) {
/* 1452 */             p = p.right;
/* 1453 */             if (p == null) {
/* 1454 */               q.right = null;
/* 1455 */               c.tree = rq.left;
/* 1456 */               c.firstEntry = c.tree;
/* 1457 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1458 */               c.lastEntry = c.tree;
/* 1459 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1460 */               return c;
/*      */             }
/* 1462 */             q = q.right;
/*      */           }
/* 1464 */           p = p.right;
/* 1465 */           q = q.right;
/*      */         }
/* 1467 */         if (!p.succ()) {
/* 1468 */           Entry e = p.right.clone();
/* 1469 */           e.succ(q.right);
/* 1470 */           e.pred(q);
/* 1471 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1475 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1478 */     int n = this.count;
/* 1479 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1481 */     s.defaultWriteObject();
/* 1482 */     while (n-- != 0) {
/* 1483 */       Entry e = i.nextEntry();
/* 1484 */       s.writeObject(e.key);
/* 1485 */       s.writeObject(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<K, V> readTree(ObjectInputStream s, int n, Entry<K, V> pred, Entry<K, V> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1497 */     if (n == 1) {
/* 1498 */       Entry top = new Entry(s.readObject(), s.readObject());
/* 1499 */       top.pred(pred);
/* 1500 */       top.succ(succ);
/* 1501 */       return top;
/*      */     }
/* 1503 */     if (n == 2)
/*      */     {
/* 1506 */       Entry top = new Entry(s.readObject(), s.readObject());
/* 1507 */       top.right(new Entry(s.readObject(), s.readObject()));
/* 1508 */       top.right.pred(top);
/* 1509 */       top.balance(1);
/* 1510 */       top.pred(pred);
/* 1511 */       top.right.succ(succ);
/* 1512 */       return top;
/*      */     }
/*      */ 
/* 1515 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1516 */     Entry top = new Entry();
/* 1517 */     top.left(readTree(s, leftN, pred, top));
/* 1518 */     top.key = s.readObject();
/* 1519 */     top.value = s.readObject();
/* 1520 */     top.right(readTree(s, rightN, top, succ));
/* 1521 */     if (n == (n & -n)) top.balance(1);
/* 1522 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1525 */     s.defaultReadObject();
/*      */ 
/* 1528 */     setActualComparator();
/* 1529 */     allocatePaths();
/* 1530 */     if (this.count != 0) {
/* 1531 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1533 */       Entry e = this.tree;
/* 1534 */       while (e.left() != null) e = e.left();
/* 1535 */       this.firstEntry = e;
/* 1536 */       e = this.tree;
/* 1537 */       while (e.right() != null) e = e.right();
/* 1538 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static int checkTree(Entry e) {
/* 1543 */     return 0;
/*      */   }
/*      */ 
/*      */   private final class Submap extends AbstractObject2ReferenceSortedMap<K, V>
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     K from;
/*      */     K to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> entries;
/*      */     protected volatile transient ObjectSortedSet<K> keys;
/*      */     protected volatile transient ReferenceCollection<V> values;
/*      */ 
/*      */     public Submap(boolean from, K bottom, boolean to)
/*      */     {
/* 1140 */       if ((!bottom) && (!top) && (Object2ReferenceAVLTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1141 */       this.from = from;
/* 1142 */       this.bottom = bottom;
/* 1143 */       this.to = to;
/* 1144 */       this.top = top;
/* 1145 */       this.defRetValue = Object2ReferenceAVLTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1148 */       SubmapIterator i = new SubmapIterator();
/* 1149 */       while (i.hasNext()) {
/* 1150 */         i.nextEntry();
/* 1151 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(K k)
/*      */     {
/* 1159 */       return ((this.bottom) || (Object2ReferenceAVLTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ReferenceAVLTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() {
/* 1163 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/* 1165 */             return new Object2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Object2ReferenceAVLTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) {
/* 1168 */             return new Object2ReferenceAVLTreeMap.Submap.SubmapEntryIterator(Object2ReferenceAVLTreeMap.Submap.this, from.getKey());
/*      */           }
/* 1170 */           public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return Object2ReferenceAVLTreeMap.this.entrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1173 */             if (!(o instanceof Map.Entry)) return false;
/* 1174 */             Map.Entry e = (Map.Entry)o;
/* 1175 */             Object2ReferenceAVLTreeMap.Entry f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1176 */             return (f != null) && (Object2ReferenceAVLTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1180 */             if (!(o instanceof Map.Entry)) return false;
/* 1181 */             Map.Entry e = (Map.Entry)o;
/* 1182 */             Object2ReferenceAVLTreeMap.Entry f = Object2ReferenceAVLTreeMap.this.findKey(e.getKey());
/* 1183 */             if ((f != null) && (Object2ReferenceAVLTreeMap.Submap.this.in(f.key))) Object2ReferenceAVLTreeMap.Submap.this.remove(f.key);
/* 1184 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1187 */             int c = 0;
/* 1188 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1189 */             return c;
/*      */           }
/*      */           public boolean isEmpty() {
/* 1192 */             return !new Object2ReferenceAVLTreeMap.Submap.SubmapIterator(Object2ReferenceAVLTreeMap.Submap.this).hasNext();
/*      */           }
/*      */           public void clear() {
/* 1195 */             Object2ReferenceAVLTreeMap.Submap.this.clear();
/*      */           }
/* 1197 */           public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceAVLTreeMap.Submap.this.firstEntry(); } 
/* 1198 */           public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceAVLTreeMap.Submap.this.lastEntry(); } 
/* 1199 */           public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); } 
/* 1200 */           public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceAVLTreeMap.Submap.this.headMap(to.getKey()).object2ReferenceEntrySet(); } 
/* 1201 */           public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceAVLTreeMap.Submap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/*      */         };
/* 1203 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<K> keySet()
/*      */     {
/* 1210 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1211 */       return this.keys;
/*      */     }
/*      */     public ReferenceCollection<V> values() {
/* 1214 */       if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */           public ObjectIterator<V> iterator() {
/* 1216 */             return new Object2ReferenceAVLTreeMap.Submap.SubmapValueIterator(Object2ReferenceAVLTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(Object k) {
/* 1219 */             return Object2ReferenceAVLTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1222 */             return Object2ReferenceAVLTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1225 */             Object2ReferenceAVLTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1228 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(Object k) {
/* 1232 */       return (in(k)) && (Object2ReferenceAVLTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(Object v) {
/* 1235 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1237 */       while (i.hasNext()) {
/* 1238 */         Object ev = i.nextEntry().value;
/* 1239 */         if (ev == v) return true;
/*      */       }
/* 1241 */       return false;
/*      */     }
/*      */ 
/*      */     public V get(Object k)
/*      */     {
/* 1246 */       Object kk = k;
/*      */       Object2ReferenceAVLTreeMap.Entry e;
/* 1247 */       return (in(kk)) && ((e = Object2ReferenceAVLTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */     public V put(K k, V v) {
/* 1250 */       Object2ReferenceAVLTreeMap.this.modified = false;
/* 1251 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1252 */       Object oldValue = Object2ReferenceAVLTreeMap.this.put(k, v);
/* 1253 */       return Object2ReferenceAVLTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */ 
/*      */     public V remove(Object k) {
/* 1257 */       Object2ReferenceAVLTreeMap.this.modified = false;
/* 1258 */       if (!in(k)) return this.defRetValue;
/* 1259 */       Object oldValue = Object2ReferenceAVLTreeMap.this.remove(k);
/* 1260 */       return Object2ReferenceAVLTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public int size() {
/* 1263 */       SubmapIterator i = new SubmapIterator();
/* 1264 */       int n = 0;
/* 1265 */       while (i.hasNext()) {
/* 1266 */         n++;
/* 1267 */         i.nextEntry();
/*      */       }
/* 1269 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1272 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public Comparator<? super K> comparator() {
/* 1275 */       return Object2ReferenceAVLTreeMap.this.actualComparator;
/*      */     }
/*      */     public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1278 */       if (this.top) return new Submap(Object2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false);
/* 1279 */       return Object2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ReferenceAVLTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Object2ReferenceSortedMap<K, V> tailMap(K from) {
/* 1282 */       if (this.bottom) return new Submap(Object2ReferenceAVLTreeMap.this, from, false, this.to, this.top);
/* 1283 */       return Object2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ReferenceAVLTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 1286 */       if ((this.top) && (this.bottom)) return new Submap(Object2ReferenceAVLTreeMap.this, from, false, to, false);
/* 1287 */       if (!this.top) to = Object2ReferenceAVLTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1288 */       if (!this.bottom) from = Object2ReferenceAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1289 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1290 */       return new Submap(Object2ReferenceAVLTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Object2ReferenceAVLTreeMap.Entry<K, V> firstEntry()
/*      */     {
/* 1297 */       if (Object2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1300 */       Object2ReferenceAVLTreeMap.Entry e;
/*      */       Object2ReferenceAVLTreeMap.Entry e;
/* 1300 */       if (this.bottom) { e = Object2ReferenceAVLTreeMap.this.firstEntry;
/*      */       } else {
/* 1302 */         e = Object2ReferenceAVLTreeMap.this.locateKey(this.from);
/*      */ 
/* 1304 */         if (Object2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1307 */       if ((e == null) || ((!this.top) && (Object2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1308 */       return e;
/*      */     }
/*      */ 
/*      */     public Object2ReferenceAVLTreeMap.Entry<K, V> lastEntry()
/*      */     {
/* 1315 */       if (Object2ReferenceAVLTreeMap.this.tree == null) return null;
/* 1318 */       Object2ReferenceAVLTreeMap.Entry e;
/*      */       Object2ReferenceAVLTreeMap.Entry e;
/* 1318 */       if (this.top) { e = Object2ReferenceAVLTreeMap.this.lastEntry;
/*      */       } else {
/* 1320 */         e = Object2ReferenceAVLTreeMap.this.locateKey(this.to);
/*      */ 
/* 1322 */         if (Object2ReferenceAVLTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1325 */       if ((e == null) || ((!this.bottom) && (Object2ReferenceAVLTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1326 */       return e;
/*      */     }
/*      */     public K firstKey() {
/* 1329 */       Object2ReferenceAVLTreeMap.Entry e = firstEntry();
/* 1330 */       if (e == null) throw new NoSuchElementException();
/* 1331 */       return e.key;
/*      */     }
/*      */     public K lastKey() {
/* 1334 */       Object2ReferenceAVLTreeMap.Entry e = lastEntry();
/* 1335 */       if (e == null) throw new NoSuchElementException();
/* 1336 */       return e.key;
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<V>
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1407 */         super(); } 
/* 1408 */       public V next() { return nextEntry().value; } 
/* 1409 */       public V previous() { return previousEntry().value; } 
/* 1410 */       public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1411 */       public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<K>
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1392 */         super(); } 
/* 1393 */       public SubmapKeyIterator() { super(from); } 
/* 1394 */       public K next() { return nextEntry().key; } 
/* 1395 */       public K previous() { return previousEntry().key; } 
/* 1396 */       public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1397 */       public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Object2ReferenceAVLTreeMap<K, V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1374 */         super();
/*      */       }
/* 1376 */       SubmapEntryIterator() { super(k); } 
/*      */       public Object2ReferenceMap.Entry<K, V> next() {
/* 1378 */         return nextEntry(); } 
/* 1379 */       public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); } 
/* 1380 */       public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/* 1381 */       public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Object2ReferenceAVLTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1346 */         super();
/* 1347 */         this.next = Object2ReferenceAVLTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator() {
/* 1350 */         this();
/* 1351 */         if (this.next != null)
/* 1352 */           if ((!Object2ReferenceAVLTreeMap.Submap.this.bottom) && (Object2ReferenceAVLTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1353 */           } else if ((!Object2ReferenceAVLTreeMap.Submap.this.top) && (Object2ReferenceAVLTreeMap.this.compare(k, (this.prev = Object2ReferenceAVLTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1355 */             this.next = Object2ReferenceAVLTreeMap.this.locateKey(k);
/* 1356 */             if (Object2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1357 */               this.prev = this.next;
/* 1358 */               this.next = this.next.next();
/*      */             } else {
/* 1360 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1365 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1366 */         if ((!Object2ReferenceAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ReferenceAVLTreeMap.this.compare(this.prev.key, Object2ReferenceAVLTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1369 */         this.next = this.next.next();
/* 1370 */         if ((!Object2ReferenceAVLTreeMap.Submap.this.top) && (this.next != null) && (Object2ReferenceAVLTreeMap.this.compare(this.next.key, Object2ReferenceAVLTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1205 */         super(); } 
/* 1206 */       public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Object2ReferenceAVLTreeMap.Submap.this); } 
/* 1207 */       public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceAVLTreeMap.Submap.SubmapKeyIterator(Object2ReferenceAVLTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2ReferenceAVLTreeMap<K, V>.TreeIterator
/*      */     implements ObjectListIterator<V>
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1061 */       super(); } 
/* 1062 */     public V next() { return nextEntry().value; } 
/* 1063 */     public V previous() { return previousEntry().value; } 
/* 1064 */     public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1065 */     public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1039 */       super(); } 
/* 1040 */     public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceAVLTreeMap.KeyIterator(Object2ReferenceAVLTreeMap.this); } 
/* 1041 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceAVLTreeMap.KeyIterator(Object2ReferenceAVLTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2ReferenceAVLTreeMap<K, V>.TreeIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1031 */       super(); } 
/* 1032 */     public KeyIterator() { super(k); } 
/* 1033 */     public K next() { return nextEntry().key; } 
/* 1034 */     public K previous() { return previousEntry().key; } 
/* 1035 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1036 */     public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2ReferenceAVLTreeMap<K, V>.TreeIterator
/*      */     implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/*  974 */       super();
/*      */     }
/*  976 */     EntryIterator() { super(k); } 
/*      */     public Object2ReferenceMap.Entry<K, V> next() {
/*  978 */       return nextEntry(); } 
/*  979 */     public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); } 
/*  980 */     public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/*  981 */     public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Object2ReferenceAVLTreeMap.Entry<K, V> prev;
/*      */     Object2ReferenceAVLTreeMap.Entry<K, V> next;
/*      */     Object2ReferenceAVLTreeMap.Entry<K, V> curr;
/*  906 */     int index = 0;
/*      */ 
/*  908 */     TreeIterator() { this.next = Object2ReferenceAVLTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator() {
/*  911 */       if ((this.next = Object2ReferenceAVLTreeMap.this.locateKey(k)) != null)
/*  912 */         if (Object2ReferenceAVLTreeMap.this.compare(this.next.key, k) <= 0) {
/*  913 */           this.prev = this.next;
/*  914 */           this.next = this.next.next();
/*      */         } else {
/*  916 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/*  919 */     public boolean hasNext() { return this.next != null; } 
/*  920 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/*  922 */       this.next = this.next.next();
/*      */     }
/*      */     Object2ReferenceAVLTreeMap.Entry<K, V> nextEntry() {
/*  925 */       if (!hasNext()) throw new NoSuchElementException();
/*  926 */       this.curr = (this.prev = this.next);
/*  927 */       this.index += 1;
/*  928 */       updateNext();
/*  929 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/*  932 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Object2ReferenceAVLTreeMap.Entry<K, V> previousEntry() {
/*  935 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  936 */       this.curr = (this.next = this.prev);
/*  937 */       this.index -= 1;
/*  938 */       updatePrevious();
/*  939 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/*  942 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/*  945 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/*  948 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/*  951 */       if (this.curr == this.prev) this.index -= 1;
/*  952 */       this.next = (this.prev = this.curr);
/*  953 */       updatePrevious();
/*  954 */       updateNext();
/*  955 */       Object2ReferenceAVLTreeMap.this.remove(this.curr.key);
/*  956 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/*  959 */       int i = n;
/*  960 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  961 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  964 */       int i = n;
/*  965 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  966 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry<K, V>
/*      */     implements Cloneable, Object2ReferenceMap.Entry<K, V>
/*      */   {
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     private static final int BALANCE_MASK = 255;
/*      */     K key;
/*      */     V value;
/*      */     Entry<K, V> left;
/*      */     Entry<K, V> right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(K k, V v)
/*      */     {
/*  689 */       this.key = k;
/*  690 */       this.value = v;
/*  691 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry<K, V> left()
/*      */     {
/*  699 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry<K, V> right()
/*      */     {
/*  707 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  713 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  719 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  725 */       if (pred) this.info |= 1073741824; else
/*  726 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  732 */       if (succ) this.info |= -2147483648; else
/*  733 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry<K, V> pred)
/*      */     {
/*  739 */       this.info |= 1073741824;
/*  740 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry<K, V> succ)
/*      */     {
/*  746 */       this.info |= -2147483648;
/*  747 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry<K, V> left)
/*      */     {
/*  753 */       this.info &= -1073741825;
/*  754 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry<K, V> right)
/*      */     {
/*  760 */       this.info &= 2147483647;
/*  761 */       this.right = right;
/*      */     }
/*      */ 
/*      */     int balance()
/*      */     {
/*  767 */       return (byte)this.info;
/*      */     }
/*      */ 
/*      */     void balance(int level)
/*      */     {
/*  773 */       this.info &= -256;
/*  774 */       this.info |= level & 0xFF;
/*      */     }
/*      */ 
/*      */     void incBalance() {
/*  778 */       this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
/*      */     }
/*      */ 
/*      */     protected void decBalance() {
/*  782 */       this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
/*      */     }
/*      */ 
/*      */     Entry<K, V> next()
/*      */     {
/*  789 */       Entry next = this.right;
/*  790 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  791 */       return next;
/*      */     }
/*      */ 
/*      */     Entry<K, V> prev()
/*      */     {
/*  798 */       Entry prev = this.left;
/*  799 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  800 */       return prev;
/*      */     }
/*      */     public K getKey() {
/*  803 */       return this.key;
/*      */     }
/*      */     public V getValue() {
/*  806 */       return this.value;
/*      */     }
/*      */     public V setValue(V value) {
/*  809 */       Object oldValue = this.value;
/*  810 */       this.value = value;
/*  811 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public Entry<K, V> clone() {
/*      */       Entry c;
/*      */       try {
/*  817 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  820 */         throw new InternalError();
/*      */       }
/*  822 */       c.key = this.key;
/*  823 */       c.value = this.value;
/*  824 */       c.info = this.info;
/*  825 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  829 */       if (!(o instanceof Map.Entry)) return false;
/*  830 */       Map.Entry e = (Map.Entry)o;
/*  831 */       return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == e.getValue());
/*      */     }
/*      */     public int hashCode() {
/*  834 */       return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
/*      */     }
/*      */     public String toString() {
/*  837 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceAVLTreeMap
 * JD-Core Version:    0.6.2
 */