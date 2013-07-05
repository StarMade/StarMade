/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortListIterator;
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
/*      */ public class Object2ShortRBTreeMap<K> extends AbstractObject2ShortSortedMap<K>
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry<K> tree;
/*      */   protected int count;
/*      */   protected transient Entry<K> firstEntry;
/*      */   protected transient Entry<K> lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Object2ShortMap.Entry<K>> entries;
/*      */   protected volatile transient ObjectSortedSet<K> keys;
/*      */   protected volatile transient ShortCollection values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super K> storedComparator;
/*      */   protected transient Comparator<? super K> actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */   private transient Entry<K>[] nodePath;
/*      */ 
/*      */   public Object2ShortRBTreeMap()
/*      */   {
/*   93 */     allocatePaths();
/*      */ 
/*   98 */     this.tree = null;
/*   99 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  112 */     this.actualComparator = this.storedComparator;
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(Comparator<? super K> c)
/*      */   {
/*  119 */     this();
/*  120 */     this.storedComparator = c;
/*  121 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(Map<? extends K, ? extends Short> m)
/*      */   {
/*  128 */     this();
/*  129 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(SortedMap<K, Short> m)
/*      */   {
/*  136 */     this(m.comparator());
/*  137 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(Object2ShortMap<? extends K> m)
/*      */   {
/*  144 */     this();
/*  145 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(Object2ShortSortedMap<K> m)
/*      */   {
/*  152 */     this(m.comparator());
/*  153 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(K[] k, short[] v, Comparator<? super K> c)
/*      */   {
/*  163 */     this(c);
/*  164 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  165 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap(K[] k, short[] v)
/*      */   {
/*  174 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(K k1, K k2)
/*      */   {
/*  197 */     return this.actualComparator == null ? ((Comparable)k1).compareTo(k2) : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<K> findKey(K k)
/*      */   {
/*  205 */     Entry e = this.tree;
/*      */     int cmp;
/*  207 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*  208 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<K> locateKey(K k)
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
/*      */   public short put(K k, short v)
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
/*  248 */           short oldValue = p.value;
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
/*      */   public short removeShort(Object k)
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
/*      */   public Short put(K ok, Short ov) {
/*  597 */     short oldValue = put(ok, ov.shortValue());
/*  598 */     return this.modified ? null : Short.valueOf(oldValue);
/*      */   }
/*      */   public Short remove(Object ok) {
/*  601 */     short oldValue = removeShort(ok);
/*  602 */     return this.modified ? Short.valueOf(oldValue) : null;
/*      */   }
/*      */   public boolean containsValue(short v) {
/*  605 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  607 */     int j = this.count;
/*  608 */     while (j-- != 0) {
/*  609 */       short ev = i.nextShort();
/*  610 */       if (ev == v) return true;
/*      */     }
/*  612 */     return false;
/*      */   }
/*      */   public void clear() {
/*  615 */     this.count = 0;
/*  616 */     this.tree = null;
/*  617 */     this.entries = null;
/*  618 */     this.values = null;
/*  619 */     this.keys = null;
/*  620 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  832 */     return findKey(k) != null;
/*      */   }
/*      */   public int size() {
/*  835 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/*  838 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public short getShort(Object k) {
/*  842 */     Entry e = findKey(k);
/*  843 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */ 
/*      */   public Short get(Object ok) {
/*  847 */     Entry e = findKey(ok);
/*  848 */     return e == null ? null : e.getValue();
/*      */   }
/*      */   public K firstKey() {
/*  851 */     if (this.tree == null) throw new NoSuchElementException();
/*  852 */     return this.firstEntry.key;
/*      */   }
/*      */   public K lastKey() {
/*  855 */     if (this.tree == null) throw new NoSuchElementException();
/*  856 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Object2ShortMap.Entry<K>> object2ShortEntrySet()
/*      */   {
/*  948 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*  949 */         final Comparator<? super Object2ShortMap.Entry<K>> comparator = new Comparator() {
/*      */           public int compare(Object2ShortMap.Entry<K> x, Object2ShortMap.Entry<K> y) {
/*  951 */             return Object2ShortRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/*  949 */         };
/*      */ 
/*      */         public Comparator<? super Object2ShortMap.Entry<K>> comparator()
/*      */         {
/*  955 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2ShortMap.Entry<K>> iterator() {
/*  958 */           return new Object2ShortRBTreeMap.EntryIterator(Object2ShortRBTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Object2ShortMap.Entry<K>> iterator(Object2ShortMap.Entry<K> from) {
/*  961 */           return new Object2ShortRBTreeMap.EntryIterator(Object2ShortRBTreeMap.this, from.getKey());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/*  965 */           if (!(o instanceof Map.Entry)) return false;
/*  966 */           Map.Entry e = (Map.Entry)o;
/*  967 */           Object2ShortRBTreeMap.Entry f = Object2ShortRBTreeMap.this.findKey(e.getKey());
/*  968 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/*  972 */           if (!(o instanceof Map.Entry)) return false;
/*  973 */           Map.Entry e = (Map.Entry)o;
/*  974 */           Object2ShortRBTreeMap.Entry f = Object2ShortRBTreeMap.this.findKey(e.getKey());
/*  975 */           if (f != null) Object2ShortRBTreeMap.this.removeShort(f.key);
/*  976 */           return f != null;
/*      */         }
/*  978 */         public int size() { return Object2ShortRBTreeMap.this.count; } 
/*  979 */         public void clear() { Object2ShortRBTreeMap.this.clear(); } 
/*  980 */         public Object2ShortMap.Entry<K> first() { return Object2ShortRBTreeMap.this.firstEntry; } 
/*  981 */         public Object2ShortMap.Entry<K> last() { return Object2ShortRBTreeMap.this.lastEntry; } 
/*  982 */         public ObjectSortedSet<Object2ShortMap.Entry<K>> subSet(Object2ShortMap.Entry<K> from, Object2ShortMap.Entry<K> to) { return Object2ShortRBTreeMap.this.subMap(from.getKey(), to.getKey()).object2ShortEntrySet(); } 
/*  983 */         public ObjectSortedSet<Object2ShortMap.Entry<K>> headSet(Object2ShortMap.Entry<K> to) { return Object2ShortRBTreeMap.this.headMap(to.getKey()).object2ShortEntrySet(); } 
/*  984 */         public ObjectSortedSet<Object2ShortMap.Entry<K>> tailSet(Object2ShortMap.Entry<K> from) { return Object2ShortRBTreeMap.this.tailMap(from.getKey()).object2ShortEntrySet(); }
/*      */       };
/*  986 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/* 1016 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1017 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ShortCollection values()
/*      */   {
/* 1044 */     if (this.values == null) this.values = new AbstractShortCollection() {
/*      */         public ShortIterator iterator() {
/* 1046 */           return new Object2ShortRBTreeMap.ValueIterator(Object2ShortRBTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(short k) {
/* 1049 */           return Object2ShortRBTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1052 */           return Object2ShortRBTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1055 */           Object2ShortRBTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1058 */     return this.values;
/*      */   }
/*      */   public Comparator<? super K> comparator() {
/* 1061 */     return this.actualComparator;
/*      */   }
/*      */   public Object2ShortSortedMap<K> headMap(K to) {
/* 1064 */     return new Submap(null, true, to, false);
/*      */   }
/*      */   public Object2ShortSortedMap<K> tailMap(K from) {
/* 1067 */     return new Submap(from, false, null, true);
/*      */   }
/*      */   public Object2ShortSortedMap<K> subMap(K from, K to) {
/* 1070 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Object2ShortRBTreeMap<K> clone()
/*      */   {
/*      */     Object2ShortRBTreeMap c;
/*      */     try
/*      */     {
/* 1407 */       c = (Object2ShortRBTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1410 */       throw new InternalError();
/*      */     }
/* 1412 */     c.keys = null;
/* 1413 */     c.values = null;
/* 1414 */     c.entries = null;
/* 1415 */     c.allocatePaths();
/* 1416 */     if (this.count != 0)
/*      */     {
/* 1418 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1419 */       Entry p = rp;
/* 1420 */       rp.left(this.tree);
/* 1421 */       Entry q = rq;
/* 1422 */       rq.pred(null);
/*      */       while (true) {
/* 1424 */         if (!p.pred()) {
/* 1425 */           Entry e = p.left.clone();
/* 1426 */           e.pred(q.left);
/* 1427 */           e.succ(q);
/* 1428 */           q.left(e);
/* 1429 */           p = p.left;
/* 1430 */           q = q.left;
/*      */         }
/*      */         else {
/* 1433 */           while (p.succ()) {
/* 1434 */             p = p.right;
/* 1435 */             if (p == null) {
/* 1436 */               q.right = null;
/* 1437 */               c.tree = rq.left;
/* 1438 */               c.firstEntry = c.tree;
/* 1439 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1440 */               c.lastEntry = c.tree;
/* 1441 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1442 */               return c;
/*      */             }
/* 1444 */             q = q.right;
/*      */           }
/* 1446 */           p = p.right;
/* 1447 */           q = q.right;
/*      */         }
/* 1449 */         if (!p.succ()) {
/* 1450 */           Entry e = p.right.clone();
/* 1451 */           e.succ(q.right);
/* 1452 */           e.pred(q);
/* 1453 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1457 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1460 */     int n = this.count;
/* 1461 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1463 */     s.defaultWriteObject();
/* 1464 */     while (n-- != 0) {
/* 1465 */       Entry e = i.nextEntry();
/* 1466 */       s.writeObject(e.key);
/* 1467 */       s.writeShort(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<K> readTree(ObjectInputStream s, int n, Entry<K> pred, Entry<K> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1479 */     if (n == 1) {
/* 1480 */       Entry top = new Entry(s.readObject(), s.readShort());
/* 1481 */       top.pred(pred);
/* 1482 */       top.succ(succ);
/* 1483 */       top.black(true);
/* 1484 */       return top;
/*      */     }
/* 1486 */     if (n == 2)
/*      */     {
/* 1489 */       Entry top = new Entry(s.readObject(), s.readShort());
/* 1490 */       top.black(true);
/* 1491 */       top.right(new Entry(s.readObject(), s.readShort()));
/* 1492 */       top.right.pred(top);
/* 1493 */       top.pred(pred);
/* 1494 */       top.right.succ(succ);
/* 1495 */       return top;
/*      */     }
/*      */ 
/* 1498 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1499 */     Entry top = new Entry();
/* 1500 */     top.left(readTree(s, leftN, pred, top));
/* 1501 */     top.key = s.readObject();
/* 1502 */     top.value = s.readShort();
/* 1503 */     top.black(true);
/* 1504 */     top.right(readTree(s, rightN, top, succ));
/* 1505 */     if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1506 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1509 */     s.defaultReadObject();
/*      */ 
/* 1512 */     setActualComparator();
/* 1513 */     allocatePaths();
/* 1514 */     if (this.count != 0) {
/* 1515 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1517 */       Entry e = this.tree;
/* 1518 */       while (e.left() != null) e = e.left();
/* 1519 */       this.firstEntry = e;
/* 1520 */       e = this.tree;
/* 1521 */       while (e.right() != null) e = e.right();
/* 1522 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */   private void checkNodePath() {
/*      */   }
/*      */ 
/* 1528 */   private int checkTree(Entry<K> e, int d, int D) { return 0; }
/*      */ 
/*      */ 
/*      */   private final class Submap extends AbstractObject2ShortSortedMap<K>
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     K from;
/*      */     K to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Object2ShortMap.Entry<K>> entries;
/*      */     protected volatile transient ObjectSortedSet<K> keys;
/*      */     protected volatile transient ShortCollection values;
/*      */ 
/*      */     public Submap(boolean from, K bottom, boolean to)
/*      */     {
/* 1108 */       if ((!bottom) && (!top) && (Object2ShortRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1109 */       this.from = from;
/* 1110 */       this.bottom = bottom;
/* 1111 */       this.to = to;
/* 1112 */       this.top = top;
/* 1113 */       this.defRetValue = Object2ShortRBTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1116 */       SubmapIterator i = new SubmapIterator();
/* 1117 */       while (i.hasNext()) {
/* 1118 */         i.nextEntry();
/* 1119 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(K k)
/*      */     {
/* 1127 */       return ((this.bottom) || (Object2ShortRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Object2ShortRBTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Object2ShortMap.Entry<K>> object2ShortEntrySet() {
/* 1131 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Object2ShortMap.Entry<K>> iterator() {
/* 1133 */             return new Object2ShortRBTreeMap.Submap.SubmapEntryIterator(Object2ShortRBTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Object2ShortMap.Entry<K>> iterator(Object2ShortMap.Entry<K> from) {
/* 1136 */             return new Object2ShortRBTreeMap.Submap.SubmapEntryIterator(Object2ShortRBTreeMap.Submap.this, from.getKey());
/*      */           }
/* 1138 */           public Comparator<? super Object2ShortMap.Entry<K>> comparator() { return Object2ShortRBTreeMap.this.object2ShortEntrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1141 */             if (!(o instanceof Map.Entry)) return false;
/* 1142 */             Map.Entry e = (Map.Entry)o;
/* 1143 */             Object2ShortRBTreeMap.Entry f = Object2ShortRBTreeMap.this.findKey(e.getKey());
/* 1144 */             return (f != null) && (Object2ShortRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1148 */             if (!(o instanceof Map.Entry)) return false;
/* 1149 */             Map.Entry e = (Map.Entry)o;
/* 1150 */             Object2ShortRBTreeMap.Entry f = Object2ShortRBTreeMap.this.findKey(e.getKey());
/* 1151 */             if ((f != null) && (Object2ShortRBTreeMap.Submap.this.in(f.key))) Object2ShortRBTreeMap.Submap.this.removeShort(f.key);
/* 1152 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1155 */             int c = 0;
/* 1156 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1157 */             return c;
/*      */           }
/* 1159 */           public boolean isEmpty() { return !new Object2ShortRBTreeMap.Submap.SubmapIterator(Object2ShortRBTreeMap.Submap.this).hasNext(); } 
/* 1160 */           public void clear() { Object2ShortRBTreeMap.Submap.this.clear(); } 
/* 1161 */           public Object2ShortMap.Entry<K> first() { return Object2ShortRBTreeMap.Submap.this.firstEntry(); } 
/* 1162 */           public Object2ShortMap.Entry<K> last() { return Object2ShortRBTreeMap.Submap.this.lastEntry(); } 
/* 1163 */           public ObjectSortedSet<Object2ShortMap.Entry<K>> subSet(Object2ShortMap.Entry<K> from, Object2ShortMap.Entry<K> to) { return Object2ShortRBTreeMap.Submap.this.subMap(from.getKey(), to.getKey()).object2ShortEntrySet(); } 
/* 1164 */           public ObjectSortedSet<Object2ShortMap.Entry<K>> headSet(Object2ShortMap.Entry<K> to) { return Object2ShortRBTreeMap.Submap.this.headMap(to.getKey()).object2ShortEntrySet(); } 
/* 1165 */           public ObjectSortedSet<Object2ShortMap.Entry<K>> tailSet(Object2ShortMap.Entry<K> from) { return Object2ShortRBTreeMap.Submap.this.tailMap(from.getKey()).object2ShortEntrySet(); }
/*      */         };
/* 1167 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<K> keySet()
/*      */     {
/* 1174 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1175 */       return this.keys;
/*      */     }
/*      */     public ShortCollection values() {
/* 1178 */       if (this.values == null) this.values = new AbstractShortCollection() {
/*      */           public ShortIterator iterator() {
/* 1180 */             return new Object2ShortRBTreeMap.Submap.SubmapValueIterator(Object2ShortRBTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(short k) {
/* 1183 */             return Object2ShortRBTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1186 */             return Object2ShortRBTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1189 */             Object2ShortRBTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1192 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(Object k) {
/* 1196 */       return (in(k)) && (Object2ShortRBTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(short v) {
/* 1199 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1201 */       while (i.hasNext()) {
/* 1202 */         short ev = i.nextEntry().value;
/* 1203 */         if (ev == v) return true;
/*      */       }
/* 1205 */       return false;
/*      */     }
/*      */ 
/*      */     public short getShort(Object k)
/*      */     {
/* 1210 */       Object kk = k;
/*      */       Object2ShortRBTreeMap.Entry e;
/* 1211 */       return (in(kk)) && ((e = Object2ShortRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */ 
/*      */     public Short get(Object ok)
/*      */     {
/* 1216 */       Object kk = ok;
/*      */       Object2ShortRBTreeMap.Entry e;
/* 1217 */       return (in(kk)) && ((e = Object2ShortRBTreeMap.this.findKey(kk)) != null) ? e.getValue() : null;
/*      */     }
/*      */     public short put(K k, short v) {
/* 1220 */       Object2ShortRBTreeMap.this.modified = false;
/* 1221 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1222 */       short oldValue = Object2ShortRBTreeMap.this.put(k, v);
/* 1223 */       return Object2ShortRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */     public Short put(K ok, Short ov) {
/* 1226 */       short oldValue = put(ok, ov.shortValue());
/* 1227 */       return Object2ShortRBTreeMap.this.modified ? null : Short.valueOf(oldValue);
/*      */     }
/*      */ 
/*      */     public short removeShort(Object k) {
/* 1231 */       Object2ShortRBTreeMap.this.modified = false;
/* 1232 */       if (!in(k)) return this.defRetValue;
/* 1233 */       short oldValue = Object2ShortRBTreeMap.this.removeShort(k);
/* 1234 */       return Object2ShortRBTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public Short remove(Object ok) {
/* 1237 */       short oldValue = removeShort(ok);
/* 1238 */       return Object2ShortRBTreeMap.this.modified ? Short.valueOf(oldValue) : null;
/*      */     }
/*      */     public int size() {
/* 1241 */       SubmapIterator i = new SubmapIterator();
/* 1242 */       int n = 0;
/* 1243 */       while (i.hasNext()) {
/* 1244 */         n++;
/* 1245 */         i.nextEntry();
/*      */       }
/* 1247 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1250 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public Comparator<? super K> comparator() {
/* 1253 */       return Object2ShortRBTreeMap.this.actualComparator;
/*      */     }
/*      */     public Object2ShortSortedMap<K> headMap(K to) {
/* 1256 */       if (this.top) return new Submap(Object2ShortRBTreeMap.this, this.from, this.bottom, to, false);
/* 1257 */       return Object2ShortRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Object2ShortRBTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Object2ShortSortedMap<K> tailMap(K from) {
/* 1260 */       if (this.bottom) return new Submap(Object2ShortRBTreeMap.this, from, false, this.to, this.top);
/* 1261 */       return Object2ShortRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2ShortRBTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Object2ShortSortedMap<K> subMap(K from, K to) {
/* 1264 */       if ((this.top) && (this.bottom)) return new Submap(Object2ShortRBTreeMap.this, from, false, to, false);
/* 1265 */       if (!this.top) to = Object2ShortRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1266 */       if (!this.bottom) from = Object2ShortRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1267 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1268 */       return new Submap(Object2ShortRBTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Object2ShortRBTreeMap.Entry<K> firstEntry()
/*      */     {
/* 1275 */       if (Object2ShortRBTreeMap.this.tree == null) return null;
/* 1278 */       Object2ShortRBTreeMap.Entry e;
/*      */       Object2ShortRBTreeMap.Entry e;
/* 1278 */       if (this.bottom) { e = Object2ShortRBTreeMap.this.firstEntry;
/*      */       } else {
/* 1280 */         e = Object2ShortRBTreeMap.this.locateKey(this.from);
/*      */ 
/* 1282 */         if (Object2ShortRBTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1285 */       if ((e == null) || ((!this.top) && (Object2ShortRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1286 */       return e;
/*      */     }
/*      */ 
/*      */     public Object2ShortRBTreeMap.Entry<K> lastEntry()
/*      */     {
/* 1293 */       if (Object2ShortRBTreeMap.this.tree == null) return null;
/* 1296 */       Object2ShortRBTreeMap.Entry e;
/*      */       Object2ShortRBTreeMap.Entry e;
/* 1296 */       if (this.top) { e = Object2ShortRBTreeMap.this.lastEntry;
/*      */       } else {
/* 1298 */         e = Object2ShortRBTreeMap.this.locateKey(this.to);
/*      */ 
/* 1300 */         if (Object2ShortRBTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1303 */       if ((e == null) || ((!this.bottom) && (Object2ShortRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1304 */       return e;
/*      */     }
/*      */     public K firstKey() {
/* 1307 */       Object2ShortRBTreeMap.Entry e = firstEntry();
/* 1308 */       if (e == null) throw new NoSuchElementException();
/* 1309 */       return e.key;
/*      */     }
/*      */     public K lastKey() {
/* 1312 */       Object2ShortRBTreeMap.Entry e = lastEntry();
/* 1313 */       if (e == null) throw new NoSuchElementException();
/* 1314 */       return e.key;
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Object2ShortRBTreeMap.Submap.SubmapIterator
/*      */       implements ShortListIterator
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1385 */         super(); } 
/* 1386 */       public short nextShort() { return nextEntry().value; } 
/* 1387 */       public short previousShort() { return previousEntry().value; } 
/* 1388 */       public void set(short v) { throw new UnsupportedOperationException(); } 
/* 1389 */       public void add(short v) { throw new UnsupportedOperationException(); } 
/* 1390 */       public Short next() { return Short.valueOf(nextEntry().value); } 
/* 1391 */       public Short previous() { return Short.valueOf(previousEntry().value); } 
/* 1392 */       public void set(Short ok) { throw new UnsupportedOperationException(); } 
/* 1393 */       public void add(Short ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Object2ShortRBTreeMap<K>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<K>
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1370 */         super(); } 
/* 1371 */       public SubmapKeyIterator() { super(from); } 
/* 1372 */       public K next() { return nextEntry().key; } 
/* 1373 */       public K previous() { return previousEntry().key; } 
/* 1374 */       public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1375 */       public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Object2ShortRBTreeMap<K>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Object2ShortMap.Entry<K>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1352 */         super();
/*      */       }
/* 1354 */       SubmapEntryIterator() { super(k); } 
/*      */       public Object2ShortMap.Entry<K> next() {
/* 1356 */         return nextEntry(); } 
/* 1357 */       public Object2ShortMap.Entry<K> previous() { return previousEntry(); } 
/* 1358 */       public void set(Object2ShortMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/* 1359 */       public void add(Object2ShortMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Object2ShortRBTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1324 */         super();
/* 1325 */         this.next = Object2ShortRBTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator() {
/* 1328 */         this();
/* 1329 */         if (this.next != null)
/* 1330 */           if ((!Object2ShortRBTreeMap.Submap.this.bottom) && (Object2ShortRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1331 */           } else if ((!Object2ShortRBTreeMap.Submap.this.top) && (Object2ShortRBTreeMap.this.compare(k, (this.prev = Object2ShortRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1333 */             this.next = Object2ShortRBTreeMap.this.locateKey(k);
/* 1334 */             if (Object2ShortRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1335 */               this.prev = this.next;
/* 1336 */               this.next = this.next.next();
/*      */             } else {
/* 1338 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1343 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1344 */         if ((!Object2ShortRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2ShortRBTreeMap.this.compare(this.prev.key, Object2ShortRBTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1347 */         this.next = this.next.next();
/* 1348 */         if ((!Object2ShortRBTreeMap.Submap.this.top) && (this.next != null) && (Object2ShortRBTreeMap.this.compare(this.next.key, Object2ShortRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractObject2ShortSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1169 */         super(); } 
/* 1170 */       public ObjectBidirectionalIterator<K> iterator() { return new Object2ShortRBTreeMap.Submap.SubmapKeyIterator(Object2ShortRBTreeMap.Submap.this); } 
/* 1171 */       public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ShortRBTreeMap.Submap.SubmapKeyIterator(Object2ShortRBTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2ShortRBTreeMap.TreeIterator
/*      */     implements ShortListIterator
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1025 */       super(); } 
/* 1026 */     public short nextShort() { return nextEntry().value; } 
/* 1027 */     public short previousShort() { return previousEntry().value; } 
/* 1028 */     public void set(short v) { throw new UnsupportedOperationException(); } 
/* 1029 */     public void add(short v) { throw new UnsupportedOperationException(); } 
/* 1030 */     public Short next() { return Short.valueOf(nextEntry().value); } 
/* 1031 */     public Short previous() { return Short.valueOf(previousEntry().value); } 
/* 1032 */     public void set(Short ok) { throw new UnsupportedOperationException(); } 
/* 1033 */     public void add(Short ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractObject2ShortSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1003 */       super(); } 
/* 1004 */     public ObjectBidirectionalIterator<K> iterator() { return new Object2ShortRBTreeMap.KeyIterator(Object2ShortRBTreeMap.this); } 
/* 1005 */     public ObjectBidirectionalIterator<K> iterator(K from) { return new Object2ShortRBTreeMap.KeyIterator(Object2ShortRBTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2ShortRBTreeMap<K>.TreeIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/*  995 */       super(); } 
/*  996 */     public KeyIterator() { super(k); } 
/*  997 */     public K next() { return nextEntry().key; } 
/*  998 */     public K previous() { return previousEntry().key; } 
/*  999 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1000 */     public void add(K k) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2ShortRBTreeMap<K>.TreeIterator
/*      */     implements ObjectListIterator<Object2ShortMap.Entry<K>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/*  938 */       super();
/*      */     }
/*  940 */     EntryIterator() { super(k); } 
/*      */     public Object2ShortMap.Entry<K> next() {
/*  942 */       return nextEntry(); } 
/*  943 */     public Object2ShortMap.Entry<K> previous() { return previousEntry(); } 
/*  944 */     public void set(Object2ShortMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  945 */     public void add(Object2ShortMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Object2ShortRBTreeMap.Entry<K> prev;
/*      */     Object2ShortRBTreeMap.Entry<K> next;
/*      */     Object2ShortRBTreeMap.Entry<K> curr;
/*  870 */     int index = 0;
/*      */ 
/*  872 */     TreeIterator() { this.next = Object2ShortRBTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator() {
/*  875 */       if ((this.next = Object2ShortRBTreeMap.this.locateKey(k)) != null)
/*  876 */         if (Object2ShortRBTreeMap.this.compare(this.next.key, k) <= 0) {
/*  877 */           this.prev = this.next;
/*  878 */           this.next = this.next.next();
/*      */         } else {
/*  880 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/*  883 */     public boolean hasNext() { return this.next != null; } 
/*  884 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/*  886 */       this.next = this.next.next();
/*      */     }
/*      */     Object2ShortRBTreeMap.Entry<K> nextEntry() {
/*  889 */       if (!hasNext()) throw new NoSuchElementException();
/*  890 */       this.curr = (this.prev = this.next);
/*  891 */       this.index += 1;
/*  892 */       updateNext();
/*  893 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/*  896 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Object2ShortRBTreeMap.Entry<K> previousEntry() {
/*  899 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  900 */       this.curr = (this.next = this.prev);
/*  901 */       this.index -= 1;
/*  902 */       updatePrevious();
/*  903 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/*  906 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/*  909 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/*  912 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/*  915 */       if (this.curr == this.prev) this.index -= 1;
/*  916 */       this.next = (this.prev = this.curr);
/*  917 */       updatePrevious();
/*  918 */       updateNext();
/*  919 */       Object2ShortRBTreeMap.this.removeShort(this.curr.key);
/*  920 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/*  923 */       int i = n;
/*  924 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  925 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  928 */       int i = n;
/*  929 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  930 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry<K>
/*      */     implements Cloneable, Object2ShortMap.Entry<K>
/*      */   {
/*      */     private static final int BLACK_MASK = 1;
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     K key;
/*      */     short value;
/*      */     Entry<K> left;
/*      */     Entry<K> right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(K k, short v)
/*      */     {
/*  652 */       this.key = k;
/*  653 */       this.value = v;
/*  654 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry<K> left()
/*      */     {
/*  662 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry<K> right()
/*      */     {
/*  670 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  676 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  682 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  688 */       if (pred) this.info |= 1073741824; else
/*  689 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  695 */       if (succ) this.info |= -2147483648; else
/*  696 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry<K> pred)
/*      */     {
/*  702 */       this.info |= 1073741824;
/*  703 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry<K> succ)
/*      */     {
/*  709 */       this.info |= -2147483648;
/*  710 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry<K> left)
/*      */     {
/*  716 */       this.info &= -1073741825;
/*  717 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry<K> right)
/*      */     {
/*  723 */       this.info &= 2147483647;
/*  724 */       this.right = right;
/*      */     }
/*      */ 
/*      */     boolean black()
/*      */     {
/*  730 */       return (this.info & 0x1) != 0;
/*      */     }
/*      */ 
/*      */     void black(boolean black)
/*      */     {
/*  736 */       if (black) this.info |= 1; else
/*  737 */         this.info &= -2;
/*      */     }
/*      */ 
/*      */     Entry<K> next()
/*      */     {
/*  744 */       Entry next = this.right;
/*  745 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  746 */       return next;
/*      */     }
/*      */ 
/*      */     Entry<K> prev()
/*      */     {
/*  753 */       Entry prev = this.left;
/*  754 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  755 */       return prev;
/*      */     }
/*      */     public K getKey() {
/*  758 */       return this.key;
/*      */     }
/*      */     public Short getValue() {
/*  761 */       return Short.valueOf(this.value);
/*      */     }
/*      */     public short getShortValue() {
/*  764 */       return this.value;
/*      */     }
/*      */     public short setValue(short value) {
/*  767 */       short oldValue = this.value;
/*  768 */       this.value = value;
/*  769 */       return oldValue;
/*      */     }
/*      */     public Short setValue(Short value) {
/*  772 */       return Short.valueOf(setValue(value.shortValue()));
/*      */     }
/*      */ 
/*      */     public Entry<K> clone() {
/*      */       Entry c;
/*      */       try {
/*  778 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  781 */         throw new InternalError();
/*      */       }
/*  783 */       c.key = this.key;
/*  784 */       c.value = this.value;
/*  785 */       c.info = this.info;
/*  786 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  790 */       if (!(o instanceof Map.Entry)) return false;
/*  791 */       Map.Entry e = (Map.Entry)o;
/*  792 */       return (this.key == null ? e.getKey() == null : this.key.equals(e.getKey())) && (this.value == ((Short)e.getValue()).shortValue());
/*      */     }
/*      */     public int hashCode() {
/*  795 */       return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
/*      */     }
/*      */     public String toString() {
/*  798 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ShortRBTreeMap
 * JD-Core Version:    0.6.2
 */