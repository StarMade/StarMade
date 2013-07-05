/*      */ package it.unimi.dsi.fastutil.chars;
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
/*      */ public class Char2ObjectRBTreeMap<V> extends AbstractChar2ObjectSortedMap<V>
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry<V> tree;
/*      */   protected int count;
/*      */   protected transient Entry<V> firstEntry;
/*      */   protected transient Entry<V> lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Char2ObjectMap.Entry<V>> entries;
/*      */   protected volatile transient CharSortedSet keys;
/*      */   protected volatile transient ObjectCollection<V> values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super Character> storedComparator;
/*      */   protected transient CharComparator actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */   private transient Entry<V>[] nodePath;
/*      */ 
/*      */   public Char2ObjectRBTreeMap()
/*      */   {
/*   92 */     allocatePaths();
/*      */ 
/*   97 */     this.tree = null;
/*   98 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  116 */     if ((this.storedComparator == null) || ((this.storedComparator instanceof CharComparator))) this.actualComparator = ((CharComparator)this.storedComparator); else
/*  117 */       this.actualComparator = new CharComparator() {
/*      */         public int compare(char k1, char k2) {
/*  119 */           return Char2ObjectRBTreeMap.this.storedComparator.compare(Character.valueOf(k1), Character.valueOf(k2));
/*      */         }
/*      */         public int compare(Character ok1, Character ok2) {
/*  122 */           return Char2ObjectRBTreeMap.this.storedComparator.compare(ok1, ok2);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(Comparator<? super Character> c)
/*      */   {
/*  135 */     this();
/*  136 */     this.storedComparator = c;
/*  137 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(Map<? extends Character, ? extends V> m)
/*      */   {
/*  147 */     this();
/*  148 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(SortedMap<Character, V> m)
/*      */   {
/*  157 */     this(m.comparator());
/*  158 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(Char2ObjectMap<? extends V> m)
/*      */   {
/*  167 */     this();
/*  168 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(Char2ObjectSortedMap<V> m)
/*      */   {
/*  177 */     this(m.comparator());
/*  178 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(char[] k, V[] v, Comparator<? super Character> c)
/*      */   {
/*  190 */     this(c);
/*  191 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  192 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap(char[] k, V[] v)
/*      */   {
/*  203 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(char k1, char k2)
/*      */   {
/*  230 */     return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry<V> findKey(char k)
/*      */   {
/*  242 */     Entry e = this.tree;
/*      */     int cmp;
/*  245 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*      */ 
/*  247 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry<V> locateKey(char k)
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
/*      */   public V put(char k, V v)
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
/*      */   public V remove(char k)
/*      */   {
/*  456 */     this.modified = false;
/*      */ 
/*  458 */     if (this.tree == null) return this.defRetValue;
/*      */ 
/*  460 */     Entry p = this.tree;
/*      */ 
/*  462 */     int i = 0;
/*  463 */     char kk = k;
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
/*      */   public V put(Character ok, V ov)
/*      */   {
/*  730 */     Object oldValue = put(ok.charValue(), ov);
/*  731 */     return this.modified ? this.defRetValue : oldValue;
/*      */   }
/*      */ 
/*      */   public V remove(Object ok)
/*      */   {
/*  737 */     Object oldValue = remove(((Character)ok).charValue());
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
/*      */   public boolean containsKey(char k)
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
/*      */   public V get(char k) {
/* 1016 */     Entry e = findKey(k);
/* 1017 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */   public char firstCharKey() {
/* 1020 */     if (this.tree == null) throw new NoSuchElementException();
/* 1021 */     return this.firstEntry.key;
/*      */   }
/*      */   public char lastCharKey() {
/* 1024 */     if (this.tree == null) throw new NoSuchElementException();
/* 1025 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Char2ObjectMap.Entry<V>> char2ObjectEntrySet()
/*      */   {
/* 1117 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1118 */         final Comparator<? super Char2ObjectMap.Entry<V>> comparator = new Comparator() {
/*      */           public int compare(Char2ObjectMap.Entry<V> x, Char2ObjectMap.Entry<V> y) {
/* 1120 */             return Char2ObjectRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/* 1118 */         };
/*      */ 
/*      */         public Comparator<? super Char2ObjectMap.Entry<V>> comparator()
/*      */         {
/* 1124 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Char2ObjectMap.Entry<V>> iterator() {
/* 1127 */           return new Char2ObjectRBTreeMap.EntryIterator(Char2ObjectRBTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Char2ObjectMap.Entry<V>> iterator(Char2ObjectMap.Entry<V> from) {
/* 1130 */           return new Char2ObjectRBTreeMap.EntryIterator(Char2ObjectRBTreeMap.this, ((Character)from.getKey()).charValue());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/* 1134 */           if (!(o instanceof Map.Entry)) return false;
/* 1135 */           Map.Entry e = (Map.Entry)o;
/* 1136 */           Char2ObjectRBTreeMap.Entry f = Char2ObjectRBTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1137 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/* 1141 */           if (!(o instanceof Map.Entry)) return false;
/* 1142 */           Map.Entry e = (Map.Entry)o;
/* 1143 */           Char2ObjectRBTreeMap.Entry f = Char2ObjectRBTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1144 */           if (f != null) Char2ObjectRBTreeMap.this.remove(f.key);
/* 1145 */           return f != null;
/*      */         }
/* 1147 */         public int size() { return Char2ObjectRBTreeMap.this.count; } 
/* 1148 */         public void clear() { Char2ObjectRBTreeMap.this.clear(); } 
/* 1149 */         public Char2ObjectMap.Entry<V> first() { return Char2ObjectRBTreeMap.this.firstEntry; } 
/* 1150 */         public Char2ObjectMap.Entry<V> last() { return Char2ObjectRBTreeMap.this.lastEntry; } 
/* 1151 */         public ObjectSortedSet<Char2ObjectMap.Entry<V>> subSet(Char2ObjectMap.Entry<V> from, Char2ObjectMap.Entry<V> to) { return Char2ObjectRBTreeMap.this.subMap((Character)from.getKey(), (Character)to.getKey()).char2ObjectEntrySet(); } 
/* 1152 */         public ObjectSortedSet<Char2ObjectMap.Entry<V>> headSet(Char2ObjectMap.Entry<V> to) { return Char2ObjectRBTreeMap.this.headMap((Character)to.getKey()).char2ObjectEntrySet(); } 
/* 1153 */         public ObjectSortedSet<Char2ObjectMap.Entry<V>> tailSet(Char2ObjectMap.Entry<V> from) { return Char2ObjectRBTreeMap.this.tailMap((Character)from.getKey()).char2ObjectEntrySet(); }
/*      */       };
/* 1155 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public CharSortedSet keySet()
/*      */   {
/* 1189 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1190 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ObjectCollection<V> values()
/*      */   {
/* 1213 */     if (this.values == null) this.values = new AbstractObjectCollection() {
/*      */         public ObjectIterator<V> iterator() {
/* 1215 */           return new Char2ObjectRBTreeMap.ValueIterator(Char2ObjectRBTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(Object k) {
/* 1218 */           return Char2ObjectRBTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1221 */           return Char2ObjectRBTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1224 */           Char2ObjectRBTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1227 */     return this.values;
/*      */   }
/*      */   public CharComparator comparator() {
/* 1230 */     return this.actualComparator;
/*      */   }
/*      */   public Char2ObjectSortedMap<V> headMap(char to) {
/* 1233 */     return new Submap('\000', true, to, false);
/*      */   }
/*      */   public Char2ObjectSortedMap<V> tailMap(char from) {
/* 1236 */     return new Submap(from, false, '\000', true);
/*      */   }
/*      */   public Char2ObjectSortedMap<V> subMap(char from, char to) {
/* 1239 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Char2ObjectRBTreeMap<V> clone()
/*      */   {
/*      */     Char2ObjectRBTreeMap c;
/*      */     try
/*      */     {
/* 1580 */       c = (Char2ObjectRBTreeMap)super.clone();
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
/* 1639 */       s.writeChar(e.key);
/* 1640 */       s.writeObject(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry<V> readTree(ObjectInputStream s, int n, Entry<V> pred, Entry<V> succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1652 */     if (n == 1) {
/* 1653 */       Entry top = new Entry(s.readChar(), s.readObject());
/* 1654 */       top.pred(pred);
/* 1655 */       top.succ(succ);
/* 1656 */       top.black(true);
/* 1657 */       return top;
/*      */     }
/* 1659 */     if (n == 2)
/*      */     {
/* 1662 */       Entry top = new Entry(s.readChar(), s.readObject());
/* 1663 */       top.black(true);
/* 1664 */       top.right(new Entry(s.readChar(), s.readObject()));
/* 1665 */       top.right.pred(top);
/* 1666 */       top.pred(pred);
/* 1667 */       top.right.succ(succ);
/* 1668 */       return top;
/*      */     }
/*      */ 
/* 1671 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1672 */     Entry top = new Entry();
/* 1673 */     top.left(readTree(s, leftN, pred, top));
/* 1674 */     top.key = s.readChar();
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
/*      */   private final class Submap extends AbstractChar2ObjectSortedMap<V>
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     char from;
/*      */     char to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Char2ObjectMap.Entry<V>> entries;
/*      */     protected volatile transient CharSortedSet keys;
/*      */     protected volatile transient ObjectCollection<V> values;
/*      */ 
/*      */     public Submap(char from, boolean bottom, char to, boolean top)
/*      */     {
/* 1277 */       if ((!bottom) && (!top) && (Char2ObjectRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1278 */       this.from = from;
/* 1279 */       this.bottom = bottom;
/* 1280 */       this.to = to;
/* 1281 */       this.top = top;
/* 1282 */       this.defRetValue = Char2ObjectRBTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1285 */       SubmapIterator i = new SubmapIterator();
/* 1286 */       while (i.hasNext()) {
/* 1287 */         i.nextEntry();
/* 1288 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(char k)
/*      */     {
/* 1296 */       return ((this.bottom) || (Char2ObjectRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Char2ObjectRBTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Char2ObjectMap.Entry<V>> char2ObjectEntrySet() {
/* 1300 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Char2ObjectMap.Entry<V>> iterator() {
/* 1302 */             return new Char2ObjectRBTreeMap.Submap.SubmapEntryIterator(Char2ObjectRBTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Char2ObjectMap.Entry<V>> iterator(Char2ObjectMap.Entry<V> from) {
/* 1305 */             return new Char2ObjectRBTreeMap.Submap.SubmapEntryIterator(Char2ObjectRBTreeMap.Submap.this, ((Character)from.getKey()).charValue());
/*      */           }
/* 1307 */           public Comparator<? super Char2ObjectMap.Entry<V>> comparator() { return Char2ObjectRBTreeMap.this.char2ObjectEntrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1310 */             if (!(o instanceof Map.Entry)) return false;
/* 1311 */             Map.Entry e = (Map.Entry)o;
/* 1312 */             Char2ObjectRBTreeMap.Entry f = Char2ObjectRBTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1313 */             return (f != null) && (Char2ObjectRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1317 */             if (!(o instanceof Map.Entry)) return false;
/* 1318 */             Map.Entry e = (Map.Entry)o;
/* 1319 */             Char2ObjectRBTreeMap.Entry f = Char2ObjectRBTreeMap.this.findKey(((Character)e.getKey()).charValue());
/* 1320 */             if ((f != null) && (Char2ObjectRBTreeMap.Submap.this.in(f.key))) Char2ObjectRBTreeMap.Submap.this.remove(f.key);
/* 1321 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1324 */             int c = 0;
/* 1325 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1326 */             return c;
/*      */           }
/* 1328 */           public boolean isEmpty() { return !new Char2ObjectRBTreeMap.Submap.SubmapIterator(Char2ObjectRBTreeMap.Submap.this).hasNext(); } 
/* 1329 */           public void clear() { Char2ObjectRBTreeMap.Submap.this.clear(); } 
/* 1330 */           public Char2ObjectMap.Entry<V> first() { return Char2ObjectRBTreeMap.Submap.this.firstEntry(); } 
/* 1331 */           public Char2ObjectMap.Entry<V> last() { return Char2ObjectRBTreeMap.Submap.this.lastEntry(); } 
/* 1332 */           public ObjectSortedSet<Char2ObjectMap.Entry<V>> subSet(Char2ObjectMap.Entry<V> from, Char2ObjectMap.Entry<V> to) { return Char2ObjectRBTreeMap.Submap.this.subMap((Character)from.getKey(), (Character)to.getKey()).char2ObjectEntrySet(); } 
/* 1333 */           public ObjectSortedSet<Char2ObjectMap.Entry<V>> headSet(Char2ObjectMap.Entry<V> to) { return Char2ObjectRBTreeMap.Submap.this.headMap((Character)to.getKey()).char2ObjectEntrySet(); } 
/* 1334 */           public ObjectSortedSet<Char2ObjectMap.Entry<V>> tailSet(Char2ObjectMap.Entry<V> from) { return Char2ObjectRBTreeMap.Submap.this.tailMap((Character)from.getKey()).char2ObjectEntrySet(); }
/*      */         };
/* 1336 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public CharSortedSet keySet()
/*      */     {
/* 1343 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1344 */       return this.keys;
/*      */     }
/*      */     public ObjectCollection<V> values() {
/* 1347 */       if (this.values == null) this.values = new AbstractObjectCollection() {
/*      */           public ObjectIterator<V> iterator() {
/* 1349 */             return new Char2ObjectRBTreeMap.Submap.SubmapValueIterator(Char2ObjectRBTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(Object k) {
/* 1352 */             return Char2ObjectRBTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1355 */             return Char2ObjectRBTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1358 */             Char2ObjectRBTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1361 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(char k) {
/* 1365 */       return (in(k)) && (Char2ObjectRBTreeMap.this.containsKey(k));
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
/*      */     public V get(char k)
/*      */     {
/* 1379 */       char kk = k;
/*      */       Char2ObjectRBTreeMap.Entry e;
/* 1380 */       return (in(kk)) && ((e = Char2ObjectRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */     public V put(char k, V v) {
/* 1383 */       Char2ObjectRBTreeMap.this.modified = false;
/* 1384 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1385 */       Object oldValue = Char2ObjectRBTreeMap.this.put(k, v);
/* 1386 */       return Char2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */     public V put(Character ok, V ov) {
/* 1389 */       Object oldValue = put(ok.charValue(), ov);
/* 1390 */       return Char2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */ 
/*      */     public V remove(char k) {
/* 1394 */       Char2ObjectRBTreeMap.this.modified = false;
/* 1395 */       if (!in(k)) return this.defRetValue;
/* 1396 */       Object oldValue = Char2ObjectRBTreeMap.this.remove(k);
/* 1397 */       return Char2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public V remove(Object ok) {
/* 1400 */       Object oldValue = remove(((Character)ok).charValue());
/* 1401 */       return Char2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
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
/*      */     public CharComparator comparator() {
/* 1416 */       return Char2ObjectRBTreeMap.this.actualComparator;
/*      */     }
/*      */     public Char2ObjectSortedMap<V> headMap(char to) {
/* 1419 */       if (this.top) return new Submap(Char2ObjectRBTreeMap.this, this.from, this.bottom, to, false);
/* 1420 */       return Char2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Char2ObjectRBTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Char2ObjectSortedMap<V> tailMap(char from) {
/* 1423 */       if (this.bottom) return new Submap(Char2ObjectRBTreeMap.this, from, false, this.to, this.top);
/* 1424 */       return Char2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Char2ObjectRBTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Char2ObjectSortedMap<V> subMap(char from, char to) {
/* 1427 */       if ((this.top) && (this.bottom)) return new Submap(Char2ObjectRBTreeMap.this, from, false, to, false);
/* 1428 */       if (!this.top) to = Char2ObjectRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1429 */       if (!this.bottom) from = Char2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1430 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1431 */       return new Submap(Char2ObjectRBTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Char2ObjectRBTreeMap.Entry<V> firstEntry()
/*      */     {
/* 1438 */       if (Char2ObjectRBTreeMap.this.tree == null) return null;
/* 1441 */       Char2ObjectRBTreeMap.Entry e;
/*      */       Char2ObjectRBTreeMap.Entry e;
/* 1441 */       if (this.bottom) { e = Char2ObjectRBTreeMap.this.firstEntry;
/*      */       } else {
/* 1443 */         e = Char2ObjectRBTreeMap.this.locateKey(this.from);
/*      */ 
/* 1445 */         if (Char2ObjectRBTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1448 */       if ((e == null) || ((!this.top) && (Char2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1449 */       return e;
/*      */     }
/*      */ 
/*      */     public Char2ObjectRBTreeMap.Entry<V> lastEntry()
/*      */     {
/* 1456 */       if (Char2ObjectRBTreeMap.this.tree == null) return null;
/* 1459 */       Char2ObjectRBTreeMap.Entry e;
/*      */       Char2ObjectRBTreeMap.Entry e;
/* 1459 */       if (this.top) { e = Char2ObjectRBTreeMap.this.lastEntry;
/*      */       } else {
/* 1461 */         e = Char2ObjectRBTreeMap.this.locateKey(this.to);
/*      */ 
/* 1463 */         if (Char2ObjectRBTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1466 */       if ((e == null) || ((!this.bottom) && (Char2ObjectRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1467 */       return e;
/*      */     }
/*      */     public char firstCharKey() {
/* 1470 */       Char2ObjectRBTreeMap.Entry e = firstEntry();
/* 1471 */       if (e == null) throw new NoSuchElementException();
/* 1472 */       return e.key;
/*      */     }
/*      */     public char lastCharKey() {
/* 1475 */       Char2ObjectRBTreeMap.Entry e = lastEntry();
/* 1476 */       if (e == null) throw new NoSuchElementException();
/* 1477 */       return e.key;
/*      */     }
/*      */     public Character firstKey() {
/* 1480 */       Char2ObjectRBTreeMap.Entry e = firstEntry();
/* 1481 */       if (e == null) throw new NoSuchElementException();
/* 1482 */       return e.getKey();
/*      */     }
/*      */     public Character lastKey() {
/* 1485 */       Char2ObjectRBTreeMap.Entry e = lastEntry();
/* 1486 */       if (e == null) throw new NoSuchElementException();
/* 1487 */       return e.getKey();
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Char2ObjectRBTreeMap<V>.Submap.SubmapIterator
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
/*      */     private final class SubmapKeyIterator extends Char2ObjectRBTreeMap.Submap.SubmapIterator
/*      */       implements CharListIterator
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1543 */         super(); } 
/* 1544 */       public SubmapKeyIterator(char from) { super(from); } 
/* 1545 */       public char nextChar() { return nextEntry().key; } 
/* 1546 */       public char previousChar() { return previousEntry().key; } 
/* 1547 */       public void set(char k) { throw new UnsupportedOperationException(); } 
/* 1548 */       public void add(char k) { throw new UnsupportedOperationException(); } 
/* 1549 */       public Character next() { return Character.valueOf(nextEntry().key); } 
/* 1550 */       public Character previous() { return Character.valueOf(previousEntry().key); } 
/* 1551 */       public void set(Character ok) { throw new UnsupportedOperationException(); } 
/* 1552 */       public void add(Character ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Char2ObjectRBTreeMap<V>.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Char2ObjectMap.Entry<V>>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1525 */         super();
/*      */       }
/* 1527 */       SubmapEntryIterator(char k) { super(k); } 
/*      */       public Char2ObjectMap.Entry<V> next() {
/* 1529 */         return nextEntry(); } 
/* 1530 */       public Char2ObjectMap.Entry<V> previous() { return previousEntry(); } 
/* 1531 */       public void set(Char2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); } 
/* 1532 */       public void add(Char2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Char2ObjectRBTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1497 */         super();
/* 1498 */         this.next = Char2ObjectRBTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator(char k) {
/* 1501 */         this();
/* 1502 */         if (this.next != null)
/* 1503 */           if ((!Char2ObjectRBTreeMap.Submap.this.bottom) && (Char2ObjectRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1504 */           } else if ((!Char2ObjectRBTreeMap.Submap.this.top) && (Char2ObjectRBTreeMap.this.compare(k, (this.prev = Char2ObjectRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1506 */             this.next = Char2ObjectRBTreeMap.this.locateKey(k);
/* 1507 */             if (Char2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1508 */               this.prev = this.next;
/* 1509 */               this.next = this.next.next();
/*      */             } else {
/* 1511 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1516 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1517 */         if ((!Char2ObjectRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Char2ObjectRBTreeMap.this.compare(this.prev.key, Char2ObjectRBTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1520 */         this.next = this.next.next();
/* 1521 */         if ((!Char2ObjectRBTreeMap.Submap.this.top) && (this.next != null) && (Char2ObjectRBTreeMap.this.compare(this.next.key, Char2ObjectRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractChar2ObjectSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1338 */         super(); } 
/* 1339 */       public CharBidirectionalIterator iterator() { return new Char2ObjectRBTreeMap.Submap.SubmapKeyIterator(Char2ObjectRBTreeMap.Submap.this); } 
/* 1340 */       public CharBidirectionalIterator iterator(char from) { return new Char2ObjectRBTreeMap.Submap.SubmapKeyIterator(Char2ObjectRBTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Char2ObjectRBTreeMap<V>.TreeIterator
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
/*      */   private class KeySet extends AbstractChar2ObjectSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1176 */       super(); } 
/* 1177 */     public CharBidirectionalIterator iterator() { return new Char2ObjectRBTreeMap.KeyIterator(Char2ObjectRBTreeMap.this); } 
/* 1178 */     public CharBidirectionalIterator iterator(char from) { return new Char2ObjectRBTreeMap.KeyIterator(Char2ObjectRBTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Char2ObjectRBTreeMap.TreeIterator
/*      */     implements CharListIterator
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1164 */       super(); } 
/* 1165 */     public KeyIterator(char k) { super(k); } 
/* 1166 */     public char nextChar() { return nextEntry().key; } 
/* 1167 */     public char previousChar() { return previousEntry().key; } 
/* 1168 */     public void set(char k) { throw new UnsupportedOperationException(); } 
/* 1169 */     public void add(char k) { throw new UnsupportedOperationException(); } 
/* 1170 */     public Character next() { return Character.valueOf(nextEntry().key); } 
/* 1171 */     public Character previous() { return Character.valueOf(previousEntry().key); } 
/* 1172 */     public void set(Character ok) { throw new UnsupportedOperationException(); } 
/* 1173 */     public void add(Character ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Char2ObjectRBTreeMap<V>.TreeIterator
/*      */     implements ObjectListIterator<Char2ObjectMap.Entry<V>>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/* 1107 */       super();
/*      */     }
/* 1109 */     EntryIterator(char k) { super(k); } 
/*      */     public Char2ObjectMap.Entry<V> next() {
/* 1111 */       return nextEntry(); } 
/* 1112 */     public Char2ObjectMap.Entry<V> previous() { return previousEntry(); } 
/* 1113 */     public void set(Char2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); } 
/* 1114 */     public void add(Char2ObjectMap.Entry<V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Char2ObjectRBTreeMap.Entry<V> prev;
/*      */     Char2ObjectRBTreeMap.Entry<V> next;
/*      */     Char2ObjectRBTreeMap.Entry<V> curr;
/* 1039 */     int index = 0;
/*      */ 
/* 1041 */     TreeIterator() { this.next = Char2ObjectRBTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator(char k) {
/* 1044 */       if ((this.next = Char2ObjectRBTreeMap.this.locateKey(k)) != null)
/* 1045 */         if (Char2ObjectRBTreeMap.this.compare(this.next.key, k) <= 0) {
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
/*      */     Char2ObjectRBTreeMap.Entry<V> nextEntry() {
/* 1058 */       if (!hasNext()) throw new NoSuchElementException();
/* 1059 */       this.curr = (this.prev = this.next);
/* 1060 */       this.index += 1;
/* 1061 */       updateNext();
/* 1062 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/* 1065 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Char2ObjectRBTreeMap.Entry<V> previousEntry() {
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
/* 1088 */       Char2ObjectRBTreeMap.this.remove(this.curr.key);
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
/*      */     implements Cloneable, Char2ObjectMap.Entry<V>
/*      */   {
/*      */     private static final int BLACK_MASK = 1;
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     char key;
/*      */     V value;
/*      */     Entry<V> left;
/*      */     Entry<V> right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(char k, V v)
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
/*      */     public Character getKey() {
/*  924 */       return Character.valueOf(this.key);
/*      */     }
/*      */ 
/*      */     public char getCharKey()
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
/*  966 */       return (this.key == ((Character)e.getKey()).charValue()) && (this.value == null ? e.getValue() == null : this.value.equals(e.getValue()));
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
 * Qualified Name:     it.unimi.dsi.fastutil.chars.Char2ObjectRBTreeMap
 * JD-Core Version:    0.6.2
 */