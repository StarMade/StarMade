/*      */ package it.unimi.dsi.fastutil.floats;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
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
/*      */ public class Float2DoubleRBTreeMap extends AbstractFloat2DoubleSortedMap
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   protected transient Entry tree;
/*      */   protected int count;
/*      */   protected transient Entry firstEntry;
/*      */   protected transient Entry lastEntry;
/*      */   protected volatile transient ObjectSortedSet<Float2DoubleMap.Entry> entries;
/*      */   protected volatile transient FloatSortedSet keys;
/*      */   protected volatile transient DoubleCollection values;
/*      */   protected transient boolean modified;
/*      */   protected Comparator<? super Float> storedComparator;
/*      */   protected transient FloatComparator actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353129L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */   private transient Entry[] nodePath;
/*      */ 
/*      */   public Float2DoubleRBTreeMap()
/*      */   {
/*   94 */     allocatePaths();
/*      */ 
/*   99 */     this.tree = null;
/*  100 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*  116 */     if ((this.storedComparator == null) || ((this.storedComparator instanceof FloatComparator))) this.actualComparator = ((FloatComparator)this.storedComparator); else
/*  117 */       this.actualComparator = new FloatComparator() {
/*      */         public int compare(float k1, float k2) {
/*  119 */           return Float2DoubleRBTreeMap.this.storedComparator.compare(Float.valueOf(k1), Float.valueOf(k2));
/*      */         }
/*      */         public int compare(Float ok1, Float ok2) {
/*  122 */           return Float2DoubleRBTreeMap.this.storedComparator.compare(ok1, ok2);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(Comparator<? super Float> c)
/*      */   {
/*  135 */     this();
/*  136 */     this.storedComparator = c;
/*  137 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(Map<? extends Float, ? extends Double> m)
/*      */   {
/*  147 */     this();
/*  148 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(SortedMap<Float, Double> m)
/*      */   {
/*  157 */     this(m.comparator());
/*  158 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(Float2DoubleMap m)
/*      */   {
/*  167 */     this();
/*  168 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(Float2DoubleSortedMap m)
/*      */   {
/*  177 */     this(m.comparator());
/*  178 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(float[] k, double[] v, Comparator<? super Float> c)
/*      */   {
/*  190 */     this(c);
/*  191 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  192 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap(float[] k, double[] v)
/*      */   {
/*  203 */     this(k, v, null);
/*      */   }
/*      */ 
/*      */   final int compare(float k1, float k2)
/*      */   {
/*  230 */     return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   final Entry findKey(float k)
/*      */   {
/*  242 */     Entry e = this.tree;
/*      */     int cmp;
/*  245 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) e = cmp < 0 ? e.left() : e.right();
/*      */ 
/*  247 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry locateKey(float k)
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
/*      */   public double put(float k, double v)
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
/*  297 */           double oldValue = p.value;
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
/*      */   public double remove(float k)
/*      */   {
/*  456 */     this.modified = false;
/*      */ 
/*  458 */     if (this.tree == null) return this.defRetValue;
/*      */ 
/*  460 */     Entry p = this.tree;
/*      */ 
/*  462 */     int i = 0;
/*  463 */     float kk = k;
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
/*      */   public Double put(Float ok, Double ov)
/*      */   {
/*  730 */     double oldValue = put(ok.floatValue(), ov.doubleValue());
/*  731 */     return this.modified ? null : Double.valueOf(oldValue);
/*      */   }
/*      */ 
/*      */   public Double remove(Object ok)
/*      */   {
/*  737 */     double oldValue = remove(((Float)ok).floatValue());
/*  738 */     return this.modified ? Double.valueOf(oldValue) : null;
/*      */   }
/*      */ 
/*      */   public boolean containsValue(double v)
/*      */   {
/*  744 */     ValueIterator i = new ValueIterator(null);
/*      */ 
/*  747 */     int j = this.count;
/*  748 */     while (j-- != 0) {
/*  749 */       double ev = i.nextDouble();
/*  750 */       if (ev == v) return true;
/*      */     }
/*      */ 
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
/*      */   public boolean containsKey(float k)
/*      */   {
/* 1025 */     return findKey(k) != null;
/*      */   }
/*      */ 
/*      */   public int size() {
/* 1029 */     return this.count;
/*      */   }
/*      */ 
/*      */   public boolean isEmpty() {
/* 1033 */     return this.count == 0;
/*      */   }
/*      */ 
/*      */   public double get(float k)
/*      */   {
/* 1039 */     Entry e = findKey(k);
/* 1040 */     return e == null ? this.defRetValue : e.value;
/*      */   }
/*      */   public float firstFloatKey() {
/* 1043 */     if (this.tree == null) throw new NoSuchElementException();
/* 1044 */     return this.firstEntry.key;
/*      */   }
/*      */   public float lastFloatKey() {
/* 1047 */     if (this.tree == null) throw new NoSuchElementException();
/* 1048 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet()
/*      */   {
/* 1140 */     if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/* 1141 */         final Comparator<? super Float2DoubleMap.Entry> comparator = new Comparator() {
/*      */           public int compare(Float2DoubleMap.Entry x, Float2DoubleMap.Entry y) {
/* 1143 */             return Float2DoubleRBTreeMap.this.storedComparator.compare(x.getKey(), y.getKey());
/*      */           }
/* 1141 */         };
/*      */ 
/*      */         public Comparator<? super Float2DoubleMap.Entry> comparator()
/*      */         {
/* 1147 */           return this.comparator;
/*      */         }
/*      */         public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator() {
/* 1150 */           return new Float2DoubleRBTreeMap.EntryIterator(Float2DoubleRBTreeMap.this);
/*      */         }
/*      */         public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator(Float2DoubleMap.Entry from) {
/* 1153 */           return new Float2DoubleRBTreeMap.EntryIterator(Float2DoubleRBTreeMap.this, ((Float)from.getKey()).floatValue());
/*      */         }
/*      */ 
/*      */         public boolean contains(Object o) {
/* 1157 */           if (!(o instanceof Map.Entry)) return false;
/* 1158 */           Map.Entry e = (Map.Entry)o;
/* 1159 */           Float2DoubleRBTreeMap.Entry f = Float2DoubleRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1160 */           return e.equals(f);
/*      */         }
/*      */ 
/*      */         public boolean remove(Object o) {
/* 1164 */           if (!(o instanceof Map.Entry)) return false;
/* 1165 */           Map.Entry e = (Map.Entry)o;
/* 1166 */           Float2DoubleRBTreeMap.Entry f = Float2DoubleRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1167 */           if (f != null) Float2DoubleRBTreeMap.this.remove(f.key);
/* 1168 */           return f != null;
/*      */         }
/* 1170 */         public int size() { return Float2DoubleRBTreeMap.this.count; } 
/* 1171 */         public void clear() { Float2DoubleRBTreeMap.this.clear(); } 
/* 1172 */         public Float2DoubleMap.Entry first() { return Float2DoubleRBTreeMap.this.firstEntry; } 
/* 1173 */         public Float2DoubleMap.Entry last() { return Float2DoubleRBTreeMap.this.lastEntry; } 
/* 1174 */         public ObjectSortedSet<Float2DoubleMap.Entry> subSet(Float2DoubleMap.Entry from, Float2DoubleMap.Entry to) { return Float2DoubleRBTreeMap.this.subMap((Float)from.getKey(), (Float)to.getKey()).float2DoubleEntrySet(); } 
/* 1175 */         public ObjectSortedSet<Float2DoubleMap.Entry> headSet(Float2DoubleMap.Entry to) { return Float2DoubleRBTreeMap.this.headMap((Float)to.getKey()).float2DoubleEntrySet(); } 
/* 1176 */         public ObjectSortedSet<Float2DoubleMap.Entry> tailSet(Float2DoubleMap.Entry from) { return Float2DoubleRBTreeMap.this.tailMap((Float)from.getKey()).float2DoubleEntrySet(); }
/*      */       };
/* 1178 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public FloatSortedSet keySet()
/*      */   {
/* 1212 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1213 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public DoubleCollection values()
/*      */   {
/* 1240 */     if (this.values == null) this.values = new AbstractDoubleCollection() {
/*      */         public DoubleIterator iterator() {
/* 1242 */           return new Float2DoubleRBTreeMap.ValueIterator(Float2DoubleRBTreeMap.this, null);
/*      */         }
/*      */         public boolean contains(double k) {
/* 1245 */           return Float2DoubleRBTreeMap.this.containsValue(k);
/*      */         }
/*      */         public int size() {
/* 1248 */           return Float2DoubleRBTreeMap.this.count;
/*      */         }
/*      */         public void clear() {
/* 1251 */           Float2DoubleRBTreeMap.this.clear();
/*      */         }
/*      */       };
/* 1254 */     return this.values;
/*      */   }
/*      */   public FloatComparator comparator() {
/* 1257 */     return this.actualComparator;
/*      */   }
/*      */   public Float2DoubleSortedMap headMap(float to) {
/* 1260 */     return new Submap(0.0F, true, to, false);
/*      */   }
/*      */   public Float2DoubleSortedMap tailMap(float from) {
/* 1263 */     return new Submap(from, false, 0.0F, true);
/*      */   }
/*      */   public Float2DoubleSortedMap subMap(float from, float to) {
/* 1266 */     return new Submap(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Float2DoubleRBTreeMap clone()
/*      */   {
/*      */     Float2DoubleRBTreeMap c;
/*      */     try
/*      */     {
/* 1611 */       c = (Float2DoubleRBTreeMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1614 */       throw new InternalError();
/*      */     }
/* 1616 */     c.keys = null;
/* 1617 */     c.values = null;
/* 1618 */     c.entries = null;
/* 1619 */     c.allocatePaths();
/* 1620 */     if (this.count != 0)
/*      */     {
/* 1622 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1623 */       Entry p = rp;
/* 1624 */       rp.left(this.tree);
/* 1625 */       Entry q = rq;
/* 1626 */       rq.pred(null);
/*      */       while (true) {
/* 1628 */         if (!p.pred()) {
/* 1629 */           Entry e = p.left.clone();
/* 1630 */           e.pred(q.left);
/* 1631 */           e.succ(q);
/* 1632 */           q.left(e);
/* 1633 */           p = p.left;
/* 1634 */           q = q.left;
/*      */         }
/*      */         else {
/* 1637 */           while (p.succ()) {
/* 1638 */             p = p.right;
/* 1639 */             if (p == null) {
/* 1640 */               q.right = null;
/* 1641 */               c.tree = rq.left;
/* 1642 */               c.firstEntry = c.tree;
/* 1643 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1644 */               c.lastEntry = c.tree;
/* 1645 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1646 */               return c;
/*      */             }
/* 1648 */             q = q.right;
/*      */           }
/* 1650 */           p = p.right;
/* 1651 */           q = q.right;
/*      */         }
/* 1653 */         if (!p.succ()) {
/* 1654 */           Entry e = p.right.clone();
/* 1655 */           e.succ(q.right);
/* 1656 */           e.pred(q);
/* 1657 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1661 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1664 */     int n = this.count;
/* 1665 */     EntryIterator i = new EntryIterator();
/*      */ 
/* 1667 */     s.defaultWriteObject();
/* 1668 */     while (n-- != 0) {
/* 1669 */       Entry e = i.nextEntry();
/* 1670 */       s.writeFloat(e.key);
/* 1671 */       s.writeDouble(e.value);
/*      */     }
/*      */   }
/*      */ 
/*      */   private Entry readTree(ObjectInputStream s, int n, Entry pred, Entry succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1683 */     if (n == 1) {
/* 1684 */       Entry top = new Entry(s.readFloat(), s.readDouble());
/* 1685 */       top.pred(pred);
/* 1686 */       top.succ(succ);
/* 1687 */       top.black(true);
/* 1688 */       return top;
/*      */     }
/* 1690 */     if (n == 2)
/*      */     {
/* 1693 */       Entry top = new Entry(s.readFloat(), s.readDouble());
/* 1694 */       top.black(true);
/* 1695 */       top.right(new Entry(s.readFloat(), s.readDouble()));
/* 1696 */       top.right.pred(top);
/* 1697 */       top.pred(pred);
/* 1698 */       top.right.succ(succ);
/* 1699 */       return top;
/*      */     }
/*      */ 
/* 1702 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1703 */     Entry top = new Entry();
/* 1704 */     top.left(readTree(s, leftN, pred, top));
/* 1705 */     top.key = s.readFloat();
/* 1706 */     top.value = s.readDouble();
/* 1707 */     top.black(true);
/* 1708 */     top.right(readTree(s, rightN, top, succ));
/* 1709 */     if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1710 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1713 */     s.defaultReadObject();
/*      */ 
/* 1716 */     setActualComparator();
/* 1717 */     allocatePaths();
/* 1718 */     if (this.count != 0) {
/* 1719 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1721 */       Entry e = this.tree;
/* 1722 */       while (e.left() != null) e = e.left();
/* 1723 */       this.firstEntry = e;
/* 1724 */       e = this.tree;
/* 1725 */       while (e.right() != null) e = e.right();
/* 1726 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */   private void checkNodePath() {
/*      */   }
/*      */ 
/* 1732 */   private int checkTree(Entry e, int d, int D) { return 0; }
/*      */ 
/*      */ 
/*      */   private final class Submap extends AbstractFloat2DoubleSortedMap
/*      */     implements Serializable
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     float from;
/*      */     float to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */     protected volatile transient ObjectSortedSet<Float2DoubleMap.Entry> entries;
/*      */     protected volatile transient FloatSortedSet keys;
/*      */     protected volatile transient DoubleCollection values;
/*      */ 
/*      */     public Submap(float from, boolean bottom, float to, boolean top)
/*      */     {
/* 1304 */       if ((!bottom) && (!top) && (Float2DoubleRBTreeMap.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start key (").append(from).append(") is larger than end key (").append(to).append(")").toString());
/* 1305 */       this.from = from;
/* 1306 */       this.bottom = bottom;
/* 1307 */       this.to = to;
/* 1308 */       this.top = top;
/* 1309 */       this.defRetValue = Float2DoubleRBTreeMap.this.defRetValue;
/*      */     }
/*      */     public void clear() {
/* 1312 */       SubmapIterator i = new SubmapIterator();
/* 1313 */       while (i.hasNext()) {
/* 1314 */         i.nextEntry();
/* 1315 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(float k)
/*      */     {
/* 1323 */       return ((this.bottom) || (Float2DoubleRBTreeMap.this.compare(k, this.from) >= 0)) && ((this.top) || (Float2DoubleRBTreeMap.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public ObjectSortedSet<Float2DoubleMap.Entry> float2DoubleEntrySet() {
/* 1327 */       if (this.entries == null) this.entries = new AbstractObjectSortedSet() {
/*      */           public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator() {
/* 1329 */             return new Float2DoubleRBTreeMap.Submap.SubmapEntryIterator(Float2DoubleRBTreeMap.Submap.this);
/*      */           }
/*      */           public ObjectBidirectionalIterator<Float2DoubleMap.Entry> iterator(Float2DoubleMap.Entry from) {
/* 1332 */             return new Float2DoubleRBTreeMap.Submap.SubmapEntryIterator(Float2DoubleRBTreeMap.Submap.this, ((Float)from.getKey()).floatValue());
/*      */           }
/* 1334 */           public Comparator<? super Float2DoubleMap.Entry> comparator() { return Float2DoubleRBTreeMap.this.float2DoubleEntrySet().comparator(); }
/*      */ 
/*      */           public boolean contains(Object o) {
/* 1337 */             if (!(o instanceof Map.Entry)) return false;
/* 1338 */             Map.Entry e = (Map.Entry)o;
/* 1339 */             Float2DoubleRBTreeMap.Entry f = Float2DoubleRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1340 */             return (f != null) && (Float2DoubleRBTreeMap.Submap.this.in(f.key)) && (e.equals(f));
/*      */           }
/*      */ 
/*      */           public boolean remove(Object o) {
/* 1344 */             if (!(o instanceof Map.Entry)) return false;
/* 1345 */             Map.Entry e = (Map.Entry)o;
/* 1346 */             Float2DoubleRBTreeMap.Entry f = Float2DoubleRBTreeMap.this.findKey(((Float)e.getKey()).floatValue());
/* 1347 */             if ((f != null) && (Float2DoubleRBTreeMap.Submap.this.in(f.key))) Float2DoubleRBTreeMap.Submap.this.remove(f.key);
/* 1348 */             return f != null;
/*      */           }
/*      */           public int size() {
/* 1351 */             int c = 0;
/* 1352 */             for (Iterator i = iterator(); i.hasNext(); i.next()) c++;
/* 1353 */             return c;
/*      */           }
/* 1355 */           public boolean isEmpty() { return !new Float2DoubleRBTreeMap.Submap.SubmapIterator(Float2DoubleRBTreeMap.Submap.this).hasNext(); } 
/* 1356 */           public void clear() { Float2DoubleRBTreeMap.Submap.this.clear(); } 
/* 1357 */           public Float2DoubleMap.Entry first() { return Float2DoubleRBTreeMap.Submap.this.firstEntry(); } 
/* 1358 */           public Float2DoubleMap.Entry last() { return Float2DoubleRBTreeMap.Submap.this.lastEntry(); } 
/* 1359 */           public ObjectSortedSet<Float2DoubleMap.Entry> subSet(Float2DoubleMap.Entry from, Float2DoubleMap.Entry to) { return Float2DoubleRBTreeMap.Submap.this.subMap((Float)from.getKey(), (Float)to.getKey()).float2DoubleEntrySet(); } 
/* 1360 */           public ObjectSortedSet<Float2DoubleMap.Entry> headSet(Float2DoubleMap.Entry to) { return Float2DoubleRBTreeMap.Submap.this.headMap((Float)to.getKey()).float2DoubleEntrySet(); } 
/* 1361 */           public ObjectSortedSet<Float2DoubleMap.Entry> tailSet(Float2DoubleMap.Entry from) { return Float2DoubleRBTreeMap.Submap.this.tailMap((Float)from.getKey()).float2DoubleEntrySet(); }
/*      */         };
/* 1363 */       return this.entries;
/*      */     }
/*      */ 
/*      */     public FloatSortedSet keySet()
/*      */     {
/* 1370 */       if (this.keys == null) this.keys = new KeySet(null);
/* 1371 */       return this.keys;
/*      */     }
/*      */     public DoubleCollection values() {
/* 1374 */       if (this.values == null) this.values = new AbstractDoubleCollection() {
/*      */           public DoubleIterator iterator() {
/* 1376 */             return new Float2DoubleRBTreeMap.Submap.SubmapValueIterator(Float2DoubleRBTreeMap.Submap.this, null);
/*      */           }
/*      */           public boolean contains(double k) {
/* 1379 */             return Float2DoubleRBTreeMap.Submap.this.containsValue(k);
/*      */           }
/*      */           public int size() {
/* 1382 */             return Float2DoubleRBTreeMap.Submap.this.size();
/*      */           }
/*      */           public void clear() {
/* 1385 */             Float2DoubleRBTreeMap.Submap.this.clear();
/*      */           }
/*      */         };
/* 1388 */       return this.values;
/*      */     }
/*      */ 
/*      */     public boolean containsKey(float k) {
/* 1392 */       return (in(k)) && (Float2DoubleRBTreeMap.this.containsKey(k));
/*      */     }
/*      */     public boolean containsValue(double v) {
/* 1395 */       SubmapIterator i = new SubmapIterator();
/*      */ 
/* 1397 */       while (i.hasNext()) {
/* 1398 */         double ev = i.nextEntry().value;
/* 1399 */         if (ev == v) return true;
/*      */       }
/* 1401 */       return false;
/*      */     }
/*      */ 
/*      */     public double get(float k)
/*      */     {
/* 1406 */       float kk = k;
/*      */       Float2DoubleRBTreeMap.Entry e;
/* 1407 */       return (in(kk)) && ((e = Float2DoubleRBTreeMap.this.findKey(kk)) != null) ? e.value : this.defRetValue;
/*      */     }
/*      */     public double put(float k, double v) {
/* 1410 */       Float2DoubleRBTreeMap.this.modified = false;
/* 1411 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Key (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1412 */       double oldValue = Float2DoubleRBTreeMap.this.put(k, v);
/* 1413 */       return Float2DoubleRBTreeMap.this.modified ? this.defRetValue : oldValue;
/*      */     }
/*      */     public Double put(Float ok, Double ov) {
/* 1416 */       double oldValue = put(ok.floatValue(), ov.doubleValue());
/* 1417 */       return Float2DoubleRBTreeMap.this.modified ? null : Double.valueOf(oldValue);
/*      */     }
/*      */ 
/*      */     public double remove(float k) {
/* 1421 */       Float2DoubleRBTreeMap.this.modified = false;
/* 1422 */       if (!in(k)) return this.defRetValue;
/* 1423 */       double oldValue = Float2DoubleRBTreeMap.this.remove(k);
/* 1424 */       return Float2DoubleRBTreeMap.this.modified ? oldValue : this.defRetValue;
/*      */     }
/*      */     public Double remove(Object ok) {
/* 1427 */       double oldValue = remove(((Float)ok).floatValue());
/* 1428 */       return Float2DoubleRBTreeMap.this.modified ? Double.valueOf(oldValue) : null;
/*      */     }
/*      */     public int size() {
/* 1431 */       SubmapIterator i = new SubmapIterator();
/* 1432 */       int n = 0;
/* 1433 */       while (i.hasNext()) {
/* 1434 */         n++;
/* 1435 */         i.nextEntry();
/*      */       }
/* 1437 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1440 */       return !new SubmapIterator().hasNext();
/*      */     }
/*      */     public FloatComparator comparator() {
/* 1443 */       return Float2DoubleRBTreeMap.this.actualComparator;
/*      */     }
/*      */     public Float2DoubleSortedMap headMap(float to) {
/* 1446 */       if (this.top) return new Submap(Float2DoubleRBTreeMap.this, this.from, this.bottom, to, false);
/* 1447 */       return Float2DoubleRBTreeMap.this.compare(to, this.to) < 0 ? new Submap(Float2DoubleRBTreeMap.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public Float2DoubleSortedMap tailMap(float from) {
/* 1450 */       if (this.bottom) return new Submap(Float2DoubleRBTreeMap.this, from, false, this.to, this.top);
/* 1451 */       return Float2DoubleRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Float2DoubleRBTreeMap.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public Float2DoubleSortedMap subMap(float from, float to) {
/* 1454 */       if ((this.top) && (this.bottom)) return new Submap(Float2DoubleRBTreeMap.this, from, false, to, false);
/* 1455 */       if (!this.top) to = Float2DoubleRBTreeMap.this.compare(to, this.to) < 0 ? to : this.to;
/* 1456 */       if (!this.bottom) from = Float2DoubleRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
/* 1457 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1458 */       return new Submap(Float2DoubleRBTreeMap.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public Float2DoubleRBTreeMap.Entry firstEntry()
/*      */     {
/* 1465 */       if (Float2DoubleRBTreeMap.this.tree == null) return null;
/* 1468 */       Float2DoubleRBTreeMap.Entry e;
/*      */       Float2DoubleRBTreeMap.Entry e;
/* 1468 */       if (this.bottom) { e = Float2DoubleRBTreeMap.this.firstEntry;
/*      */       } else {
/* 1470 */         e = Float2DoubleRBTreeMap.this.locateKey(this.from);
/*      */ 
/* 1472 */         if (Float2DoubleRBTreeMap.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1475 */       if ((e == null) || ((!this.top) && (Float2DoubleRBTreeMap.this.compare(e.key, this.to) >= 0))) return null;
/* 1476 */       return e;
/*      */     }
/*      */ 
/*      */     public Float2DoubleRBTreeMap.Entry lastEntry()
/*      */     {
/* 1483 */       if (Float2DoubleRBTreeMap.this.tree == null) return null;
/* 1486 */       Float2DoubleRBTreeMap.Entry e;
/*      */       Float2DoubleRBTreeMap.Entry e;
/* 1486 */       if (this.top) { e = Float2DoubleRBTreeMap.this.lastEntry;
/*      */       } else {
/* 1488 */         e = Float2DoubleRBTreeMap.this.locateKey(this.to);
/*      */ 
/* 1490 */         if (Float2DoubleRBTreeMap.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1493 */       if ((e == null) || ((!this.bottom) && (Float2DoubleRBTreeMap.this.compare(e.key, this.from) < 0))) return null;
/* 1494 */       return e;
/*      */     }
/*      */     public float firstFloatKey() {
/* 1497 */       Float2DoubleRBTreeMap.Entry e = firstEntry();
/* 1498 */       if (e == null) throw new NoSuchElementException();
/* 1499 */       return e.key;
/*      */     }
/*      */     public float lastFloatKey() {
/* 1502 */       Float2DoubleRBTreeMap.Entry e = lastEntry();
/* 1503 */       if (e == null) throw new NoSuchElementException();
/* 1504 */       return e.key;
/*      */     }
/*      */     public Float firstKey() {
/* 1507 */       Float2DoubleRBTreeMap.Entry e = firstEntry();
/* 1508 */       if (e == null) throw new NoSuchElementException();
/* 1509 */       return e.getKey();
/*      */     }
/*      */     public Float lastKey() {
/* 1512 */       Float2DoubleRBTreeMap.Entry e = lastEntry();
/* 1513 */       if (e == null) throw new NoSuchElementException();
/* 1514 */       return e.getKey();
/*      */     }
/*      */ 
/*      */     private final class SubmapValueIterator extends Float2DoubleRBTreeMap.Submap.SubmapIterator
/*      */       implements DoubleListIterator
/*      */     {
/*      */       private SubmapValueIterator()
/*      */       {
/* 1589 */         super(); } 
/* 1590 */       public double nextDouble() { return nextEntry().value; } 
/* 1591 */       public double previousDouble() { return previousEntry().value; } 
/* 1592 */       public void set(double v) { throw new UnsupportedOperationException(); } 
/* 1593 */       public void add(double v) { throw new UnsupportedOperationException(); } 
/* 1594 */       public Double next() { return Double.valueOf(nextEntry().value); } 
/* 1595 */       public Double previous() { return Double.valueOf(previousEntry().value); } 
/* 1596 */       public void set(Double ok) { throw new UnsupportedOperationException(); } 
/* 1597 */       public void add(Double ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private final class SubmapKeyIterator extends Float2DoubleRBTreeMap.Submap.SubmapIterator
/*      */       implements FloatListIterator
/*      */     {
/*      */       public SubmapKeyIterator()
/*      */       {
/* 1570 */         super(); } 
/* 1571 */       public SubmapKeyIterator(float from) { super(from); } 
/* 1572 */       public float nextFloat() { return nextEntry().key; } 
/* 1573 */       public float previousFloat() { return previousEntry().key; } 
/* 1574 */       public void set(float k) { throw new UnsupportedOperationException(); } 
/* 1575 */       public void add(float k) { throw new UnsupportedOperationException(); } 
/* 1576 */       public Float next() { return Float.valueOf(nextEntry().key); } 
/* 1577 */       public Float previous() { return Float.valueOf(previousEntry().key); } 
/* 1578 */       public void set(Float ok) { throw new UnsupportedOperationException(); } 
/* 1579 */       public void add(Float ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapEntryIterator extends Float2DoubleRBTreeMap.Submap.SubmapIterator
/*      */       implements ObjectListIterator<Float2DoubleMap.Entry>
/*      */     {
/*      */       SubmapEntryIterator()
/*      */       {
/* 1552 */         super();
/*      */       }
/* 1554 */       SubmapEntryIterator(float k) { super(k); } 
/*      */       public Float2DoubleMap.Entry next() {
/* 1556 */         return nextEntry(); } 
/* 1557 */       public Float2DoubleMap.Entry previous() { return previousEntry(); } 
/* 1558 */       public void set(Float2DoubleMap.Entry ok) { throw new UnsupportedOperationException(); } 
/* 1559 */       public void add(Float2DoubleMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */     }
/*      */ 
/*      */     private class SubmapIterator extends Float2DoubleRBTreeMap.TreeIterator
/*      */     {
/*      */       SubmapIterator()
/*      */       {
/* 1524 */         super();
/* 1525 */         this.next = Float2DoubleRBTreeMap.Submap.this.firstEntry();
/*      */       }
/*      */       SubmapIterator(float k) {
/* 1528 */         this();
/* 1529 */         if (this.next != null)
/* 1530 */           if ((!Float2DoubleRBTreeMap.Submap.this.bottom) && (Float2DoubleRBTreeMap.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1531 */           } else if ((!Float2DoubleRBTreeMap.Submap.this.top) && (Float2DoubleRBTreeMap.this.compare(k, (this.prev = Float2DoubleRBTreeMap.Submap.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1533 */             this.next = Float2DoubleRBTreeMap.this.locateKey(k);
/* 1534 */             if (Float2DoubleRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1535 */               this.prev = this.next;
/* 1536 */               this.next = this.next.next();
/*      */             } else {
/* 1538 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1543 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1544 */         if ((!Float2DoubleRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Float2DoubleRBTreeMap.this.compare(this.prev.key, Float2DoubleRBTreeMap.Submap.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1547 */         this.next = this.next.next();
/* 1548 */         if ((!Float2DoubleRBTreeMap.Submap.this.top) && (this.next != null) && (Float2DoubleRBTreeMap.this.compare(this.next.key, Float2DoubleRBTreeMap.Submap.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */ 
/*      */     private class KeySet extends AbstractFloat2DoubleSortedMap.KeySet
/*      */     {
/*      */       private KeySet()
/*      */       {
/* 1365 */         super(); } 
/* 1366 */       public FloatBidirectionalIterator iterator() { return new Float2DoubleRBTreeMap.Submap.SubmapKeyIterator(Float2DoubleRBTreeMap.Submap.this); } 
/* 1367 */       public FloatBidirectionalIterator iterator(float from) { return new Float2DoubleRBTreeMap.Submap.SubmapKeyIterator(Float2DoubleRBTreeMap.Submap.this, from); }
/*      */ 
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Float2DoubleRBTreeMap.TreeIterator
/*      */     implements DoubleListIterator
/*      */   {
/*      */     private ValueIterator()
/*      */     {
/* 1221 */       super(); } 
/* 1222 */     public double nextDouble() { return nextEntry().value; } 
/* 1223 */     public double previousDouble() { return previousEntry().value; } 
/* 1224 */     public void set(double v) { throw new UnsupportedOperationException(); } 
/* 1225 */     public void add(double v) { throw new UnsupportedOperationException(); } 
/* 1226 */     public Double next() { return Double.valueOf(nextEntry().value); } 
/* 1227 */     public Double previous() { return Double.valueOf(previousEntry().value); } 
/* 1228 */     public void set(Double ok) { throw new UnsupportedOperationException(); } 
/* 1229 */     public void add(Double ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class KeySet extends AbstractFloat2DoubleSortedMap.KeySet
/*      */   {
/*      */     private KeySet()
/*      */     {
/* 1199 */       super(); } 
/* 1200 */     public FloatBidirectionalIterator iterator() { return new Float2DoubleRBTreeMap.KeyIterator(Float2DoubleRBTreeMap.this); } 
/* 1201 */     public FloatBidirectionalIterator iterator(float from) { return new Float2DoubleRBTreeMap.KeyIterator(Float2DoubleRBTreeMap.this, from); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Float2DoubleRBTreeMap.TreeIterator
/*      */     implements FloatListIterator
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1187 */       super(); } 
/* 1188 */     public KeyIterator(float k) { super(k); } 
/* 1189 */     public float nextFloat() { return nextEntry().key; } 
/* 1190 */     public float previousFloat() { return previousEntry().key; } 
/* 1191 */     public void set(float k) { throw new UnsupportedOperationException(); } 
/* 1192 */     public void add(float k) { throw new UnsupportedOperationException(); } 
/* 1193 */     public Float next() { return Float.valueOf(nextEntry().key); } 
/* 1194 */     public Float previous() { return Float.valueOf(previousEntry().key); } 
/* 1195 */     public void set(Float ok) { throw new UnsupportedOperationException(); } 
/* 1196 */     public void add(Float ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Float2DoubleRBTreeMap.TreeIterator
/*      */     implements ObjectListIterator<Float2DoubleMap.Entry>
/*      */   {
/*      */     EntryIterator()
/*      */     {
/* 1130 */       super();
/*      */     }
/* 1132 */     EntryIterator(float k) { super(k); } 
/*      */     public Float2DoubleMap.Entry next() {
/* 1134 */       return nextEntry(); } 
/* 1135 */     public Float2DoubleMap.Entry previous() { return previousEntry(); } 
/* 1136 */     public void set(Float2DoubleMap.Entry ok) { throw new UnsupportedOperationException(); } 
/* 1137 */     public void add(Float2DoubleMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class TreeIterator
/*      */   {
/*      */     Float2DoubleRBTreeMap.Entry prev;
/*      */     Float2DoubleRBTreeMap.Entry next;
/*      */     Float2DoubleRBTreeMap.Entry curr;
/* 1062 */     int index = 0;
/*      */ 
/* 1064 */     TreeIterator() { this.next = Float2DoubleRBTreeMap.this.firstEntry; }
/*      */ 
/*      */     TreeIterator(float k) {
/* 1067 */       if ((this.next = Float2DoubleRBTreeMap.this.locateKey(k)) != null)
/* 1068 */         if (Float2DoubleRBTreeMap.this.compare(this.next.key, k) <= 0) {
/* 1069 */           this.prev = this.next;
/* 1070 */           this.next = this.next.next();
/*      */         } else {
/* 1072 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/* 1075 */     public boolean hasNext() { return this.next != null; } 
/* 1076 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/* 1078 */       this.next = this.next.next();
/*      */     }
/*      */     Float2DoubleRBTreeMap.Entry nextEntry() {
/* 1081 */       if (!hasNext()) throw new NoSuchElementException();
/* 1082 */       this.curr = (this.prev = this.next);
/* 1083 */       this.index += 1;
/* 1084 */       updateNext();
/* 1085 */       return this.curr;
/*      */     }
/*      */     void updatePrevious() {
/* 1088 */       this.prev = this.prev.prev();
/*      */     }
/*      */     Float2DoubleRBTreeMap.Entry previousEntry() {
/* 1091 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 1092 */       this.curr = (this.next = this.prev);
/* 1093 */       this.index -= 1;
/* 1094 */       updatePrevious();
/* 1095 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/* 1098 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/* 1101 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/* 1104 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/* 1107 */       if (this.curr == this.prev) this.index -= 1;
/* 1108 */       this.next = (this.prev = this.curr);
/* 1109 */       updatePrevious();
/* 1110 */       updateNext();
/* 1111 */       Float2DoubleRBTreeMap.this.remove(this.curr.key);
/* 1112 */       this.curr = null;
/*      */     }
/*      */     public int skip(int n) {
/* 1115 */       int i = n;
/* 1116 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 1117 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/* 1120 */       int i = n;
/* 1121 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/* 1122 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry
/*      */     implements Cloneable, Float2DoubleMap.Entry
/*      */   {
/*      */     private static final int BLACK_MASK = 1;
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     float key;
/*      */     double value;
/*      */     Entry left;
/*      */     Entry right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(float k, double v)
/*      */     {
/*  800 */       this.key = k;
/*  801 */       this.value = v;
/*  802 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry left()
/*      */     {
/*  811 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry right()
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
/*      */     void pred(Entry pred)
/*      */     {
/*  857 */       this.info |= 1073741824;
/*  858 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry succ)
/*      */     {
/*  865 */       this.info |= -2147483648;
/*  866 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry left)
/*      */     {
/*  873 */       this.info &= -1073741825;
/*  874 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry right)
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
/*      */     Entry next()
/*      */     {
/*  907 */       Entry next = this.right;
/*  908 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  909 */       return next;
/*      */     }
/*      */ 
/*      */     Entry prev()
/*      */     {
/*  918 */       Entry prev = this.left;
/*  919 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  920 */       return prev;
/*      */     }
/*      */ 
/*      */     public Float getKey() {
/*  924 */       return Float.valueOf(this.key);
/*      */     }
/*      */ 
/*      */     public float getFloatKey()
/*      */     {
/*  929 */       return this.key;
/*      */     }
/*      */ 
/*      */     public Double getValue()
/*      */     {
/*  934 */       return Double.valueOf(this.value);
/*      */     }
/*      */ 
/*      */     public double getDoubleValue()
/*      */     {
/*  939 */       return this.value;
/*      */     }
/*      */ 
/*      */     public double setValue(double value)
/*      */     {
/*  944 */       double oldValue = this.value;
/*  945 */       this.value = value;
/*  946 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public Double setValue(Double value)
/*      */     {
/*  952 */       return Double.valueOf(setValue(value.doubleValue()));
/*      */     }
/*      */ 
/*      */     public Entry clone()
/*      */     {
/*      */       Entry c;
/*      */       try
/*      */       {
/*  961 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  964 */         throw new InternalError();
/*      */       }
/*      */ 
/*  967 */       c.key = this.key;
/*  968 */       c.value = this.value;
/*  969 */       c.info = this.info;
/*      */ 
/*  971 */       return c;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o)
/*      */     {
/*  976 */       if (!(o instanceof Map.Entry)) return false;
/*  977 */       Map.Entry e = (Map.Entry)o;
/*      */ 
/*  979 */       return (this.key == ((Float)e.getKey()).floatValue()) && (this.value == ((Double)e.getValue()).doubleValue());
/*      */     }
/*      */ 
/*      */     public int hashCode() {
/*  983 */       return HashCommon.float2int(this.key) ^ HashCommon.double2int(this.value);
/*      */     }
/*      */ 
/*      */     public String toString()
/*      */     {
/*  988 */       return this.key + "=>" + this.value;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.Float2DoubleRBTreeMap
 * JD-Core Version:    0.6.2
 */