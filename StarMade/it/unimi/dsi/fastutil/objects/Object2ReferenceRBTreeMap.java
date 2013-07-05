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
/*      */ public class Object2ReferenceRBTreeMap<K, V> extends AbstractObject2ReferenceSortedMap<K, V>
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
/*      */   private transient Entry<K, V>[] nodePath;
/*      */ 
/*      */   public Object2ReferenceRBTreeMap()
/*      */   {
/*   91 */     allocatePaths();
/*      */ 
/*   97 */     this.tree = null;
/*   98 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  112 */     this.actualComparator = this.storedComparator;
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(Comparator<? super K> c)
/*      */   {
/*  119 */     this();
/*  120 */     this.storedComparator = c;
/*  121 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(Map<? extends K, ? extends V> m)
/*      */   {
/*  128 */     this();
/*  129 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(SortedMap<K, V> m)
/*      */   {
/*  136 */     this(m.comparator());
/*  137 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(Object2ReferenceMap<? extends K, ? extends V> m)
/*      */   {
/*  144 */     this();
/*  145 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(Object2ReferenceSortedMap<K, V> m)
/*      */   {
/*  152 */     this(m.comparator());
/*  153 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(K[] k, V[] v, Comparator<? super K> c)
/*      */   {
/*  163 */     this(c);
/*  164 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  165 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap(K[] k, V[] v)
/*      */   {
/*  174 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(K k1, K k2)
/*      */   {
/*  197 */     return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<K, V> findKey(K k)
/*      */   {
/*  205 */     Entry e = this.tree;
/*      */     int cmp;
/*  207 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  208 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<K, V> locateKey(K k)
/*      */   {
/*  217 */     Entry e = this.tree; Entry last = this.tree;
/*  218 */     int cmp = 0;
/*  219 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  220 */       last = e;
/*  221 */       e = cmp < 0 ? e.left() : e.right();
/*      */     }
/*  223 */     return cmp == 0 ? e : last;
/*      */   }
/*      */ 
/*      */   private void allocatePaths()
/*      */   {
/*  231 */     this.dirPath = new boolean[64];
/*  232 */     this.nodePath = new Entry[64];
/*      */   }
/*      */ 
/*      */   public V put(K k, V v)
/*      */   {
/*  237 */     this.modified = false;
/*  238 */     int maxDepth = 0;
/*  239 */     if (this.tree == null) {
/*  240 */       this.count += 1;
/*  241 */       this.tree = (this.lastEntry = this.firstEntry = new Entry(k, v));
/*      */     }
/*      */     else {
/*  244 */       Entry p = this.tree;
/*  245 */       int i = 0;
/*      */       while (true)
/*      */       {
/*      */         int cmp;
/*  247 */         if ((cmp = compare(k, p.key)) == 0) {
/*  248 */           Object oldValue = p.value;
/*  249 */           p.value = v;
/*      */ 
/*  251 */           while (i-- != 0) this.nodePath[i] = null;
/*  252 */           return oldValue;
/*      */         }
/*  254 */         this.nodePath[i] = p;
/*  255 */         if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  256 */           if (p.succ()) {
/*  257 */             this.count += 1;
/*  258 */             Entry e = new Entry(k, v);
/*  259 */             if (p.right == null) this.lastEntry = e;
/*  260 */             e.left = p;
/*  261 */             e.right = p.right;
/*  262 */             p.right(e);
/*  263 */             break;
/*      */           }
/*  265 */           p = p.right;
/*      */         }
/*      */         else {
/*  268 */           if (p.pred()) {
/*  269 */             this.count += 1;
/*  270 */             Entry e = new Entry(k, v);
/*  271 */             if (p.left == null) this.firstEntry = e;
/*  272 */             e.right = p;
/*  273 */             e.left = p.left;
/*  274 */             p.left(e);
/*  275 */             break;
/*      */           }
/*  277 */           p = p.left;
/*      */         }
/*      */       }
/*      */       Entry e;
/*  280 */       this.modified = true;
/*  281 */       maxDepth = i--;
/*  282 */       while ((i > 0) && (!this.nodePath[i].black())) {
/*  283 */         if (this.dirPath[(i - 1)] == 0) {
/*  284 */           Entry y = this.nodePath[(i - 1)].right;
/*  285 */           if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  286 */             this.nodePath[i].black(true);
/*  287 */             y.black(true);
/*  288 */             this.nodePath[(i - 1)].black(false);
/*  289 */             i -= 2;
/*      */           }
/*      */           else
/*      */           {
/*  293 */             if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*      */             } else {
/*  295 */               Entry x = this.nodePath[i];
/*  296 */               y = x.right;
/*  297 */               x.right = y.left;
/*  298 */               y.left = x;
/*  299 */               this.nodePath[(i - 1)].left = y;
/*  300 */               if (y.pred()) {
/*  301 */                 y.pred(false);
/*  302 */                 x.succ(y);
/*      */               }
/*      */             }
/*  305 */             Entry x = this.nodePath[(i - 1)];
/*  306 */             x.black(false);
/*  307 */             y.black(true);
/*  308 */             x.left = y.right;
/*  309 */             y.right = x;
/*  310 */             if (i < 2) this.tree = y;
/*  312 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  313 */               this.nodePath[(i - 2)].left = y;
/*      */             }
/*  315 */             if (!y.succ()) break;
/*  316 */             y.succ(false);
/*  317 */             x.pred(y); break;
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  323 */           Entry y = this.nodePath[(i - 1)].left;
/*  324 */           if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  325 */             this.nodePath[i].black(true);
/*  326 */             y.black(true);
/*  327 */             this.nodePath[(i - 1)].black(false);
/*  328 */             i -= 2;
/*      */           }
/*      */           else
/*      */           {
/*  332 */             if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*      */             } else {
/*  334 */               Entry x = this.nodePath[i];
/*  335 */               y = x.left;
/*  336 */               x.left = y.right;
/*  337 */               y.right = x;
/*  338 */               this.nodePath[(i - 1)].right = y;
/*  339 */               if (y.succ()) {
/*  340 */                 y.succ(false);
/*  341 */                 x.pred(y);
/*      */               }
/*      */             }
/*  344 */             Entry x = this.nodePath[(i - 1)];
/*  345 */             x.black(false);
/*  346 */             y.black(true);
/*  347 */             x.right = y.left;
/*  348 */             y.left = x;
/*  349 */             if (i < 2) this.tree = y;
/*  351 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  352 */               this.nodePath[(i - 2)].left = y;
/*      */             }
/*  354 */             if (!y.pred()) break;
/*  355 */             y.pred(false);
/*  356 */             x.succ(y); break;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  363 */     this.tree.black(true);
/*      */ 
/*  365 */     while (maxDepth-- != 0) this.nodePath[maxDepth] = null;
/*      */ 
/*  370 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V remove(Object k)
/*      */   {
/*  376 */     this.modified = false;
/*  377 */     if (this.tree == null) return this.defRetValue;
/*  378 */     Entry p = this.tree;
/*      */ 
/*  380 */     int i = 0;
/*  381 */     Object kk = k;
/*      */     int cmp;
/*  383 */     while ((cmp = compare(kk, p.key)) != 0) {
/*  384 */       this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  385 */       this.nodePath[i] = p;
/*  386 */       if (this.dirPath[(i++)] != 0) {
/*  387 */         if ((p = p.right()) == null)
/*      */         {
/*  389 */           while (i-- != 0) this.nodePath[i] = null;
/*  390 */           return this.defRetValue;
/*      */         }
/*      */ 
/*      */       }
/*  394 */       else if ((p = p.left()) == null)
/*      */       {
/*  396 */         while (i-- != 0) this.nodePath[i] = null;
/*  397 */         return this.defRetValue;
/*      */       }
/*      */     }
/*      */ 
/*  401 */     if (p.left == null) this.firstEntry = p.next();
/*  402 */     if (p.right == null) this.lastEntry = p.prev();
/*  403 */     if (p.succ()) {
/*  404 */       if (p.pred()) {
/*  405 */         if (i == 0) this.tree = p.left;
/*  407 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else
/*  408 */           this.nodePath[(i - 1)].pred(p.left);
/*      */       }
/*      */       else
/*      */       {
/*  412 */         p.prev().right = p.right;
/*  413 */         if (i == 0) this.tree = p.left;
/*  415 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  416 */           this.nodePath[(i - 1)].left = p.left;
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  422 */       Entry r = p.right;
/*  423 */       if (r.pred()) {
/*  424 */         r.left = p.left;
/*  425 */         r.pred(p.pred());
/*  426 */         if (!r.pred()) r.prev().right = r;
/*  427 */         if (i == 0) this.tree = r;
/*  429 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  430 */           this.nodePath[(i - 1)].left = r;
/*      */         }
/*  432 */         boolean color = r.black();
/*  433 */         r.black(p.black());
/*  434 */         p.black(color);
/*  435 */         this.dirPath[i] = true;
/*  436 */         this.nodePath[(i++)] = r;
/*      */       } else {
/*  440 */         int j = i++;
/*      */         Entry s;
/*      */         while (true) {
/*  442 */           this.dirPath[i] = false;
/*  443 */           this.nodePath[(i++)] = r;
/*  444 */           s = r.left;
/*  445 */           if (s.pred()) break;
/*  446 */           r = s;
/*      */         }
/*  448 */         this.dirPath[j] = true;
/*  449 */         this.nodePath[j] = s;
/*  450 */         if (s.succ()) r.pred(s); else
/*  451 */           r.left = s.right;
/*  452 */         s.left = p.left;
/*  453 */         if (!p.pred()) {
/*  454 */           p.prev().right = s;
/*  455 */           s.pred(false);
/*      */         }
/*  457 */         s.right(p.right);
/*  458 */         boolean color = s.black();
/*  459 */         s.black(p.black());
/*  460 */         p.black(color);
/*  461 */         if (j == 0) this.tree = s;
/*  463 */         else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  464 */           this.nodePath[(j - 1)].left = s;
/*      */         }
/*      */       }
/*      */     }
/*  468 */     int maxDepth = i;
/*  469 */     if (p.black()) {
/*  470 */       for (; i > 0; i--) {
/*  471 */         if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*      */         {
/*  473 */           Entry x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*  474 */           if (!x.black()) {
/*  475 */             x.black(true);
/*  476 */             break;
/*      */           }
/*      */         }
/*  479 */         if (this.dirPath[(i - 1)] == 0) {
/*  480 */           Entry w = this.nodePath[(i - 1)].right;
/*  481 */           if (!w.black()) {
/*  482 */             w.black(true);
/*  483 */             this.nodePath[(i - 1)].black(false);
/*  484 */             this.nodePath[(i - 1)].right = w.left;
/*  485 */             w.left = this.nodePath[(i - 1)];
/*  486 */             if (i < 2) this.tree = w;
/*  488 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  489 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*  491 */             this.nodePath[i] = this.nodePath[(i - 1)];
/*  492 */             this.dirPath[i] = false;
/*  493 */             this.nodePath[(i - 1)] = w;
/*  494 */             if (maxDepth == i++) maxDepth++;
/*  495 */             w = this.nodePath[(i - 1)].right;
/*      */           }
/*  497 */           if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*      */           {
/*  499 */             w.black(false);
/*      */           }
/*      */           else {
/*  502 */             if ((w.succ()) || (w.right.black())) {
/*  503 */               Entry y = w.left;
/*  504 */               y.black(true);
/*  505 */               w.black(false);
/*  506 */               w.left = y.right;
/*  507 */               y.right = w;
/*  508 */               w = this.nodePath[(i - 1)].right = y;
/*  509 */               if (w.succ()) {
/*  510 */                 w.succ(false);
/*  511 */                 w.right.pred(w);
/*      */               }
/*      */             }
/*  514 */             w.black(this.nodePath[(i - 1)].black());
/*  515 */             this.nodePath[(i - 1)].black(true);
/*  516 */             w.right.black(true);
/*  517 */             this.nodePath[(i - 1)].right = w.left;
/*  518 */             w.left = this.nodePath[(i - 1)];
/*  519 */             if (i < 2) this.tree = w;
/*  521 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  522 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*  524 */             if (!w.pred()) break;
/*  525 */             w.pred(false);
/*  526 */             this.nodePath[(i - 1)].succ(w); break;
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  532 */           Entry w = this.nodePath[(i - 1)].left;
/*  533 */           if (!w.black()) {
/*  534 */             w.black(true);
/*  535 */             this.nodePath[(i - 1)].black(false);
/*  536 */             this.nodePath[(i - 1)].left = w.right;
/*  537 */             w.right = this.nodePath[(i - 1)];
/*  538 */             if (i < 2) this.tree = w;
/*  540 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  541 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*  543 */             this.nodePath[i] = this.nodePath[(i - 1)];
/*  544 */             this.dirPath[i] = true;
/*  545 */             this.nodePath[(i - 1)] = w;
/*  546 */             if (maxDepth == i++) maxDepth++;
/*  547 */             w = this.nodePath[(i - 1)].left;
/*      */           }
/*  549 */           if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*      */           {
/*  551 */             w.black(false);
/*      */           }
/*      */           else {
/*  554 */             if ((w.pred()) || (w.left.black())) {
/*  555 */               Entry y = w.right;
/*  556 */               y.black(true);
/*  557 */               w.black(false);
/*  558 */               w.right = y.left;
/*  559 */               y.left = w;
/*  560 */               w = this.nodePath[(i - 1)].left = y;
/*  561 */               if (w.pred()) {
/*  562 */                 w.pred(false);
/*  563 */                 w.left.succ(w);
/*      */               }
/*      */             }
/*  566 */             w.black(this.nodePath[(i - 1)].black());
/*  567 */             this.nodePath[(i - 1)].black(true);
/*  568 */             w.left.black(true);
/*  569 */             this.nodePath[(i - 1)].left = w.right;
/*  570 */             w.right = this.nodePath[(i - 1)];
/*  571 */             if (i < 2) this.tree = w;
/*  573 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  574 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*  576 */             if (!w.succ()) break;
/*  577 */             w.succ(false);
/*  578 */             this.nodePath[(i - 1)].pred(w); break;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  584 */       if (this.tree != null) this.tree.black(true);
/*      */     }
/*  586 */     this.modified = true;
/*  587 */     this.count -= 1;
/*      */ 
/*  589 */     while (maxDepth-- != 0) this.nodePath[maxDepth] = null;
/*      */ 
/*  594 */     return p.value;
/*      */   }
/*      */   public boolean containsValue(Object v) {
/*  597 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  599 */     int j = this.count;
/*  600 */     while (j-- != 0) {
/*  601 */       Object ev = i.next();
/*  602 */       if (ev == v) return true;
/*      */     }
/*  604 */     return false;
/*      */   }
/*      */   public void clear() {
/*  607 */     this.count = 0;
/*  608 */     this.tree = null;
/*  609 */     this.entries = null;
/*  610 */     this.values = null;
/*  611 */     this.keys = null;
/*  612 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  818 */     return findKey(k) != null;
/*      */   }
/*      */   public int size() {
/*  821 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/*  824 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public V get(Object k) {
/*  828 */     Entry e = findKey(k);
/*  829 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */   public K firstKey() {
/*  832 */     if (this.tree == null) throw new NoSuchElementException();
/*  833 */     return this.firstEntry.key;
/*      */   }
/*      */   public K lastKey() {
/*  836 */     if (this.tree == null) throw new NoSuchElementException();
/*  837 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet()
/*      */   {
/*  929 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  930 */         final Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator = new Comparator() {
/*      */           public int compare(Object2ReferenceMap.Entry<K, V> x, Object2ReferenceMap.Entry<K, V> y) {
/*  932 */             return Object2ReferenceRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/*  930 */         };
/*      */ 
/*      */         public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator()
/*      */         {
/*  936 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/*  939 */           return new Object2ReferenceRBTreeMap.EntryIterator(Object2ReferenceRBTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) {
/*  942 */           return new Object2ReferenceRBTreeMap.EntryIterator(Object2ReferenceRBTreeMap.this, from.getKey());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/*  946 */           if (!(o instanceof Map.Entry)) return false;
/*  947 */           Map.Entry e = (Map.Entry)o;
/*  948 */           Object2ReferenceRBTreeMap.Entry f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/*  949 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/*  953 */           if (!(o instanceof Map.Entry)) return false;
/*  954 */           Map.Entry e = (Map.Entry)o;
/*  955 */           Object2ReferenceRBTreeMap.Entry f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/*  956 */           if (f != null) Object2ReferenceRBTreeMap.this.remove(f.key);
/*  957 */           return f != null;
/*      */         }
/*  959 */         public int size() { return Object2ReferenceRBTreeMap.this.count; } 
/*  960 */         public void clear() { Object2ReferenceRBTreeMap.this.clear(); } 
/*  961 */         public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceRBTreeMap.this.firstEntry; } 
/*  962 */         public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceRBTreeMap.this.lastEntry; } 
/*  963 */         public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); } 
/*  964 */         public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.this.headMap(to.getKey()).object2ReferenceEntrySet(); } 
/*  965 */         public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceRBTreeMap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/*      */       };
/*  967 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/*  997 */     if (this.keys == null) this.keys = new KeySet(null);
/*  998 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ReferenceCollection<V> values()
/*      */   {
/* 1021 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */         public ObjectIterator<V> iterator() {
/* 1023 */           return new Object2ReferenceRBTreeMap.ValueIterator(Object2ReferenceRBTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(Object k) {
/* 1026 */           return Object2ReferenceRBTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1029 */           return Object2ReferenceRBTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1032 */           Object2ReferenceRBTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1035 */     return this.values;
/*      */   }
/*      */   public Comparator<? super K> comparator() {
/* 1038 */     return this.actualComparator;
/*      */   }
/*      */   public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1041 */     return new Submap(null, true, to, false);
/*      */   }
/*      */   public Object2ReferenceSortedMap<K, V> tailMap(K from) {
/* 1044 */     return new Submap(from, false, null, true);
/*      */   }
/*      */   public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 1047 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceRBTreeMap<K, V> clone()
/*      */   {
/*      */     Object2ReferenceRBTreeMap c;
/*      */     try
/*      */     {
/* 1366 */       c = (Object2ReferenceRBTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1369 */       throw new InternalError();
/*      */     }
/* 1371 */     c.keys = null;
/* 1372 */     c.values = null;
/* 1373 */     c.entries = null;
/* 1374 */     c.allocatePaths();
/* 1375 */     if (this.count != 0)
/*      */     {
/* 1377 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1378 */       Entry p = rp;
/* 1379 */       rp.left(this.tree);
/* 1380 */       Entry q = rq;
/* 1381 */       rq.pred(null);
/*      */       while (true) {
/* 1383 */         if (!p.pred()) {
/* 1384 */           Entry e = p.left.clone();
/* 1385 */           e.pred(q.left);
/* 1386 */           e.succ(q);
/* 1387 */           q.left(e);
/* 1388 */           p = p.left;
/* 1389 */           q = q.left;
/*      */         }
/*      */         else {
/* 1392 */           while (p.succ()) {
/* 1393 */             p = p.right;
/* 1394 */             if (p == null) {
/* 1395 */               q.right = null;
/* 1396 */               c.tree = rq.left;
/* 1397 */               c.firstEntry = c.tree;
/* 1398 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1399 */               c.lastEntry = c.tree;
/* 1400 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1401 */               return c;
/*      */             }
/* 1403 */             q = q.right;
/*      */           }
/* 1405 */           p = p.right;
/* 1406 */           q = q.right;
/*      */         }
/* 1408 */         if (!p.succ()) {
/* 1409 */           Entry e = p.right.clone();
/* 1410 */           e.succ(q.right);
/* 1411 */           e.pred(q);
/* 1412 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1416 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1419 */     int n = this.count;
/* 1420 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1422 */     s.defaultWriteObject();
/* 1423 */     while (n-- != 0) {
/* 1424 */       Entry e = i.nextEntry();
/* 1425 */       s.writeObject(e.key);
/* 1426 */       s.writeObject(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<K, V> readTree(ObjectInputStream s, int n, Entry<K, V> pred, Entry<K, V> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1438 */     if (n == 1) {
/* 1439 */       Entry top = new Entry(s.readObject(), s.readObject());
/* 1440 */       top.pred(pred);
/* 1441 */       top.succ(succ);
/* 1442 */       top.black(true);
/* 1443 */       return top;
/*      */     }
/* 1445 */     if (n == 2)
/*      */     {
/* 1448 */       Entry top = new Entry(s.readObject(), s.readObject());
/* 1449 */       top.black(true);
/* 1450 */       top.right(new Entry(s.readObject(), s.readObject()));
/* 1451 */       top.right.pred(top);
/* 1452 */       top.pred(pred);
/* 1453 */       top.right.succ(succ);
/* 1454 */       return top;
/*      */     }
/*      */ 
/* 1457 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1458 */     Entry top = new Entry();
/* 1459 */     top.left(readTree(s, leftN, pred, top));
/* 1460 */     top.key = s.readObject();
/* 1461 */     top.value = s.readObject();
/* 1462 */     top.black(true);
/* 1463 */     top.right(readTree(s, rightN, top, succ));
/* 1464 */     if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1465 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1468 */     s.defaultReadObject();
/*      */ 
/* 1471 */     setActualComparator();
/* 1472 */     allocatePaths();
/* 1473 */     if (this.count != 0) {
/* 1474 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1476 */       Entry e = this.tree;
/* 1477 */       while (e.left() != null) e = e.left();
/* 1478 */       this.firstEntry = e;
/* 1479 */       e = this.tree;
/* 1480 */       while (e.right() != null) e = e.right();
/* 1481 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */   private void checkNodePath() {
/*      */   }
/*      */ 
/* 1487 */   private int checkTree(Entry<K, V> e, int d, int D) { return 0; }
/*      */ 
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
/* 1085 */       if ((!bottom) && (!top) && (Object2ReferenceRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1086 */       this.from = from;
/* 1087 */       this.bottom = bottom;
/* 1088 */       this.to = to;
/* 1089 */       this.top = top;
/* 1090 */       this.defRetValue = Object2ReferenceRBTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1093 */       SubmapIterator i = new SubmapIterator();
/* 1094 */       while (i.hasNext()) {
/* 1095 */         i.nextEntry();
/* 1096 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(K k)
/*      */     {
/* 1104 */       return ((this.bottom) || (Object2ReferenceRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ReferenceRBTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> object2ReferenceEntrySet() {
/* 1108 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator() {
/* 1110 */             return new Object2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Object2ReferenceRBTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) {
/* 1113 */             return new Object2ReferenceRBTreeMap.Submap.SubmapEntryIterator(Object2ReferenceRBTreeMap.Submap.this, from.getKey());
/*      */           }
/* 1115 */           public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return Object2ReferenceRBTreeMap.this.object2ReferenceEntrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1118 */             if (!(o instanceof Map.Entry)) return false;
/* 1119 */             Map.Entry e = (Map.Entry)o;
/* 1120 */             Object2ReferenceRBTreeMap.Entry f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/* 1121 */             return (f != null) && (Object2ReferenceRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1125 */             if (!(o instanceof Map.Entry)) return false;
/* 1126 */             Map.Entry e = (Map.Entry)o;
/* 1127 */             Object2ReferenceRBTreeMap.Entry f = Object2ReferenceRBTreeMap.this.findKey(e.getKey());
/* 1128 */             if ((f != null) && (Object2ReferenceRBTreeMap.Submap.this.in(f.key))) Object2ReferenceRBTreeMap.Submap.this.remove(f.key);
/* 1129 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1132 */             int c = 0;
/* 1133 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1134 */             return c;
/*      */           }
/* 1136 */           public boolean isEmpty() { return !new Object2ReferenceRBTreeMap.Submap.SubmapIterator(Object2ReferenceRBTreeMap.Submap.this).hasNext(); } 
/* 1137 */           public void clear() { Object2ReferenceRBTreeMap.Submap.this.clear(); } 
/* 1138 */           public Object2ReferenceMap.Entry<K, V> first() { return Object2ReferenceRBTreeMap.Submap.this.firstEntry(); } 
/* 1139 */           public Object2ReferenceMap.Entry<K, V> last() { return Object2ReferenceRBTreeMap.Submap.this.lastEntry(); } 
/* 1140 */           public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> from, Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ReferenceEntrySet(); } 
/* 1141 */           public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> to) { return Object2ReferenceRBTreeMap.Submap.this.headMap(to.getKey()).object2ReferenceEntrySet(); } 
/* 1142 */           public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> from) { return Object2ReferenceRBTreeMap.Submap.this.tailMap(from.getKey()).object2ReferenceEntrySet(); }
/*      */         };
/* 1144 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<K> keySet()
/*      */     {
/* 1151 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1152 */       return this.keys;
/*      */     }
/*      */     public ReferenceCollection<V> values() {
/* 1155 */       if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */           public ObjectIterator<V> iterator() {
/* 1157 */             return new Object2ReferenceRBTreeMap.Submap.SubmapValueIterator(Object2ReferenceRBTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(Object k) {
/* 1160 */             return Object2ReferenceRBTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1163 */             return Object2ReferenceRBTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1166 */             Object2ReferenceRBTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1169 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(Object k) {
/* 1173 */       return (in(k)) && (Object2ReferenceRBTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(Object v) {
/* 1176 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1178 */       while (i.hasNext()) {
/* 1179 */         Object ev = i.nextEntry().value;
/* 1180 */         if (ev == v) return true;
/*      */       }
/* 1182 */       return false;
/*      */     }
/*      */ 
/*      */     public V get(Object k)
/*      */     {
/* 1187 */       Object kk = k;
/*      */       Object2ReferenceRBTreeMap.Entry e;
/* 1188 */       return (in(kk)) && ((e = Object2ReferenceRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */     public V put(K k, V v) {
/* 1191 */       Object2ReferenceRBTreeMap.this.modified = false;
/* 1192 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1193 */       Object oldValue = Object2ReferenceRBTreeMap.this.put(k, v);
/* 1194 */       return Object2ReferenceRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */ 
/*      */     public V remove(Object k) {
/* 1198 */       Object2ReferenceRBTreeMap.this.modified = false;
/* 1199 */       if (!in(k)) return this.defRetValue;
/* 1200 */       Object oldValue = Object2ReferenceRBTreeMap.this.remove(k);
/* 1201 */       return Object2ReferenceRBTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public int size() {
/* 1204 */       SubmapIterator i = new SubmapIterator();
/* 1205 */       int n = 0;
/* 1206 */       while (i.hasNext()) {
/* 1207 */         n++;
/* 1208 */         i.nextEntry();
/*      */       }
/* 1210 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1213 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public Comparator<? super K> comparator() {
/* 1216 */       return Object2ReferenceRBTreeMap.this.actualComparator;
/*      */     }
/*      */     public Object2ReferenceSortedMap<K, V> headMap(K to) {
/* 1219 */       if (this.top) return new Submap(Object2ReferenceRBTreeMap.this, this.from, this.bottom, to, false);
/* 1220 */       return Object2ReferenceRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ReferenceRBTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Object2ReferenceSortedMap<K, V> tailMap(K from) {
/* 1223 */       if (this.bottom) return new Submap(Object2ReferenceRBTreeMap.this, from, false, this.to, this.top);
/* 1224 */       return Object2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ReferenceRBTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Object2ReferenceSortedMap<K, V> subMap(K from, K to) {
/* 1227 */       if ((this.top) && (this.bottom)) return new Submap(Object2ReferenceRBTreeMap.this, from, false, to, false);
/* 1228 */       if (!this.top) to = Object2ReferenceRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1229 */       if (!this.bottom) from = Object2ReferenceRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1230 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1231 */       return new Submap(Object2ReferenceRBTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Object2ReferenceRBTreeMap.Entry<K, V> firstEntry()
/*      */     {
/* 1238 */       if (Object2ReferenceRBTreeMap.this.tree == null) return null;
/* 1241 */       Object2ReferenceRBTreeMap.Entry e;
/*      */       Object2ReferenceRBTreeMap.Entry e;
/* 1241 */       if (this.bottom) { e = Object2ReferenceRBTreeMap.this.firstEntry;
/*      */       } else {
/* 1243 */         e = Object2ReferenceRBTreeMap.this.locateKey(this.from);
/*      */ 
/* 1245 */         if (Object2ReferenceRBTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1248 */       if ((e == null) || ((!this.top) && (Object2ReferenceRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1249 */       return e;
/*      */     }
/*      */ 
/*      */     public Object2ReferenceRBTreeMap.Entry<K, V> lastEntry()
/*      */     {
/* 1256 */       if (Object2ReferenceRBTreeMap.this.tree == null) return null;
/* 1259 */       Object2ReferenceRBTreeMap.Entry e;
/*      */       Object2ReferenceRBTreeMap.Entry e;
/* 1259 */       if (this.top) { e = Object2ReferenceRBTreeMap.this.lastEntry;
/*      */       } else {
/* 1261 */         e = Object2ReferenceRBTreeMap.this.locateKey(this.to);
/*      */ 
/* 1263 */         if (Object2ReferenceRBTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1266 */       if ((e == null) || ((!this.bottom) && (Object2ReferenceRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1267 */       return e;
/*      */     }
/*      */     public K firstKey() {
/* 1270 */       Object2ReferenceRBTreeMap.Entry e = firstEntry();
/* 1271 */       if (e == null) throw new NoSuchElementException();
/* 1272 */       return e.key;
/*      */     }
/*      */     public K lastKey() {
/* 1275 */       Object2ReferenceRBTreeMap.Entry e = lastEntry();
/* 1276 */       if (e == null) throw new NoSuchElementException();
/* 1277 */       return e.key;
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<V>
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1348 */         super(); } 
/* 1349 */       public V next() { return nextEntry().value; } 
/* 1350 */       public V previous() { return previousEntry().value; } 
/* 1351 */       public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1352 */       public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<K>
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1333 */         super(); } 
/* 1334 */       public SubmapKeyIterator() { super(from); } 
/* 1335 */       public K next() { return nextEntry().key; } 
/* 1336 */       public K previous() { return previousEntry().key; } 
/* 1337 */       public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1338 */       public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Object2ReferenceRBTreeMap<K, V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1315 */         super();
/*      */       }
/* 1317 */       SubmapEntryIterator() { super(k); } 
/*      */       public Object2ReferenceMap.Entry<K, V> next() {
/* 1319 */         return nextEntry(); } 
/* 1320 */       public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); } 
/* 1321 */       public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/* 1322 */       public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Object2ReferenceRBTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1287 */         super();
/* 1288 */         this.next = Object2ReferenceRBTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator() {
/* 1291 */         this();
/* 1292 */         if (this.next != null)
/* 1293 */           if ((!Object2ReferenceRBTreeMap.Submap.this.bottom) && (Object2ReferenceRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1294 */           } else if ((!Object2ReferenceRBTreeMap.Submap.this.top) && (Object2ReferenceRBTreeMap.this.compare(k, (this.prev = Object2ReferenceRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1296 */             this.next = Object2ReferenceRBTreeMap.this.locateKey(k);
/* 1297 */             if (Object2ReferenceRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1298 */               this.prev = this.next;
/* 1299 */               this.next = this.next.next();
/*      */             } else {
/* 1301 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1306 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1307 */         if ((!Object2ReferenceRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ReferenceRBTreeMap.this.compare(this.prev.key, Object2ReferenceRBTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1310 */         this.next = this.next.next();
/* 1311 */         if ((!Object2ReferenceRBTreeMap.Submap.this.top) && (this.next != null) && (Object2ReferenceRBTreeMap.this.compare(this.next.key, Object2ReferenceRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1146 */         super(); } 
/* 1147 */       public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Object2ReferenceRBTreeMap.Submap.this); } 
/* 1148 */       public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceRBTreeMap.Submap.SubmapKeyIterator(Object2ReferenceRBTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
/*      */     implements ObjectListIterator<V>
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1006 */       super(); } 
/* 1007 */     public V next() { return nextEntry().value; } 
/* 1008 */     public V previous() { return previousEntry().value; } 
/* 1009 */     public void set(V v) { throw new UnsupportedOperationException(); } 
/* 1010 */     public void add(V v) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractObject2ReferenceSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/*  984 */       super(); } 
/*  985 */     public ObjectBidirectionalIterator<K> iterator() { return new Object2ReferenceRBTreeMap.KeyIterator(Object2ReferenceRBTreeMap.this); } 
/*  986 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ReferenceRBTreeMap.KeyIterator(Object2ReferenceRBTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/*  976 */       super(); } 
/*  977 */     public KeyIterator() { super(k); } 
/*  978 */     public K next() { return nextEntry().key; } 
/*  979 */     public K previous() { return previousEntry().key; } 
/*  980 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/*  981 */     public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2ReferenceRBTreeMap<K, V>.TreeIterator
/*      */     implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/*  919 */       super();
/*      */     }
/*  921 */     EntryIterator() { super(k); } 
/*      */     public Object2ReferenceMap.Entry<K, V> next() {
/*  923 */       return nextEntry(); } 
/*  924 */     public Object2ReferenceMap.Entry<K, V> previous() { return previousEntry(); } 
/*  925 */     public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/*  926 */     public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Object2ReferenceRBTreeMap.Entry<K, V> prev;
/*      */     Object2ReferenceRBTreeMap.Entry<K, V> next;
/*      */     Object2ReferenceRBTreeMap.Entry<K, V> curr;
/*  851 */     int index = 0;
/*      */ 
/*  853 */     TreeIterator() { this.next = Object2ReferenceRBTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator() {
/*  856 */       if ((this.next = Object2ReferenceRBTreeMap.this.locateKey(k)) != null)
/*  857 */         if (Object2ReferenceRBTreeMap.this.compare(this.next.key, k) <= 0) {
/*  858 */           this.prev = this.next;
/*  859 */           this.next = this.next.next();
/*      */         } else {
/*  861 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/*  864 */     public boolean hasNext() { return this.next != null; } 
/*  865 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/*  867 */       this.next = this.next.next();
/*      */     }
/*      */     Object2ReferenceRBTreeMap.Entry<K, V> nextEntry() {
/*  870 */       if (!hasNext()) throw new NoSuchElementException();
/*  871 */       this.curr = (this.prev = this.next);
/*  872 */       this.index += 1;
/*  873 */       updateNext();
/*  874 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/*  877 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Object2ReferenceRBTreeMap.Entry<K, V> previousEntry() {
/*  880 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  881 */       this.curr = (this.next = this.prev);
/*  882 */       this.index -= 1;
/*  883 */       updatePrevious();
/*  884 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/*  887 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/*  890 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/*  893 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/*  896 */       if (this.curr == this.prev) this.index -= 1;
/*  897 */       this.next = (this.prev = this.curr);
/*  898 */       updatePrevious();
/*  899 */       updateNext();
/*  900 */       Object2ReferenceRBTreeMap.this.remove(this.curr.key);
/*  901 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/*  904 */       int i = n;
/*  905 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  906 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  909 */       int i = n;
/*  910 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  911 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry<K, V>
/*      */     implements Cloneable, Object2ReferenceMap.Entry<K, V>
/*      */   {
/*      */     private static final int BLACK_MASK = 1;
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
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
/*  644 */       this.key = k;
/*  645 */       this.value = v;
/*  646 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry<K, V> left()
/*      */     {
/*  654 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry<K, V> right()
/*      */     {
/*  662 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  668 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  674 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  680 */       if (pred) this.info |= 1073741824; else
/*  681 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  687 */       if (succ) this.info |= -2147483648; else
/*  688 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry<K, V> pred)
/*      */     {
/*  694 */       this.info |= 1073741824;
/*  695 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry<K, V> succ)
/*      */     {
/*  701 */       this.info |= -2147483648;
/*  702 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry<K, V> left)
/*      */     {
/*  708 */       this.info &= -1073741825;
/*  709 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry<K, V> right)
/*      */     {
/*  715 */       this.info &= 2147483647;
/*  716 */       this.right = right;
/*      */     }
/*      */ 
/*      */     boolean black()
/*      */     {
/*  722 */       return (this.info & 0x1) != 0;
/*      */     }
/*      */ 
/*      */     void black(boolean black)
/*      */     {
/*  728 */       if (black) this.info |= 1; else
/*  729 */         this.info &= -2;
/*      */     }
/*      */ 
/*      */     Entry<K, V> next()
/*      */     {
/*  736 */       Entry next = this.right;
/*  737 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  738 */       return next;
/*      */     }
/*      */ 
/*      */     Entry<K, V> prev()
/*      */     {
/*  745 */       Entry prev = this.left;
/*  746 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  747 */       return prev;
/*      */     }
/*      */     public K getKey() {
/*  750 */       return this.key;
/*      */     }
/*      */     public V getValue() {
/*  753 */       return this.value;
/*      */     }
/*      */     public V setValue(V value) {
/*  756 */       Object oldValue = this.value;
/*  757 */       this.value = value;
/*  758 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public Entry<K, V> clone() {
/*      */       Entry c;
/*      */       try {
/*  764 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  767 */         throw new InternalError();
/*      */       }
/*  769 */       c.key = this.key;
/*  770 */       c.value = this.value;
/*  771 */       c.info = this.info;
/*  772 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  776 */       if (!(o instanceof Map.Entry)) return false;
/*  777 */       Map.Entry e = (Map.Entry)o;
/*  778 */       return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == e.getValue());
/*      */     }
/*      */     public int hashCode() {
/*  781 */       return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : System.identityHashCode(this.value));
/*      */     }
/*      */     public String toString() {
/*  784 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceRBTreeMap
 * JD-Core Version:    0.6.2
 */