/*      */ package it.unimi.dsi.fastutil.bytes;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.SortedSet;
/*      */ 
/*      */ public class ByteRBTreeSet extends AbstractByteSortedSet
/*      */   implements Serializable, Cloneable, ByteSortedSet
/*      */ {
/*      */   protected transient Entry tree;
/*      */   protected int count;
/*      */   protected transient Entry firstEntry;
/*      */   protected transient Entry lastEntry;
/*      */   protected Comparator<? super Byte> storedComparator;
/*      */   protected transient ByteComparator actualComparator;
/*      */   public static final long serialVersionUID = -7046029254386353130L;
/*      */   private static final boolean ASSERTS = false;
/*      */   private transient boolean[] dirPath;
/*      */   private transient Entry[] nodePath;
/*      */ 
/*      */   public ByteRBTreeSet()
/*      */   {
/*   74 */     allocatePaths();
/*      */ 
/*   79 */     this.tree = null;
/*   80 */     this.count = 0;
/*      */   }
/*      */ 
/*      */   private void setActualComparator()
/*      */   {
/*   94 */     if ((this.storedComparator == null) || ((this.storedComparator instanceof ByteComparator))) this.actualComparator = ((ByteComparator)this.storedComparator); else
/*   95 */       this.actualComparator = new ByteComparator() {
/*      */         public int compare(byte k1, byte k2) {
/*   97 */           return ByteRBTreeSet.this.storedComparator.compare(Byte.valueOf(k1), Byte.valueOf(k2));
/*      */         }
/*      */         public int compare(Byte ok1, Byte ok2) {
/*  100 */           return ByteRBTreeSet.this.storedComparator.compare(ok1, ok2);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(Comparator<? super Byte> c)
/*      */   {
/*  109 */     this();
/*  110 */     this.storedComparator = c;
/*  111 */     setActualComparator();
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(Collection<? extends Byte> c)
/*      */   {
/*  119 */     this();
/*  120 */     addAll(c);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(SortedSet<Byte> s)
/*      */   {
/*  129 */     this(s.comparator());
/*  130 */     addAll(s);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(ByteCollection c)
/*      */   {
/*  139 */     this();
/*  140 */     addAll(c);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(ByteSortedSet s)
/*      */   {
/*  149 */     this(s.comparator());
/*  150 */     addAll(s);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(ByteIterator i)
/*      */   {
/*   74 */     allocatePaths();
/*      */ 
/*  160 */     while (i.hasNext()) add(i.nextByte());
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(Iterator<? extends Byte> i)
/*      */   {
/*  171 */     this(ByteIterators.asByteIterator(i));
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(byte[] a, int offset, int length, Comparator<? super Byte> c)
/*      */   {
/*  184 */     this(c);
/*  185 */     ByteArrays.ensureOffsetLength(a, offset, length);
/*  186 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(byte[] a, int offset, int length)
/*      */   {
/*  198 */     this(a, offset, length, null);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(byte[] a)
/*      */   {
/*  208 */     this();
/*  209 */     int i = a.length;
/*  210 */     while (i-- != 0) add(a[i]);
/*      */   }
/*      */ 
/*      */   public ByteRBTreeSet(byte[] a, Comparator<? super Byte> c)
/*      */   {
/*  221 */     this(c);
/*  222 */     int i = a.length;
/*  223 */     while (i-- != 0) add(a[i]);
/*      */   }
/*      */ 
/*      */   final int compare(byte k1, byte k2)
/*      */   {
/*  253 */     return this.actualComparator == null ? 1 : k1 == k2 ? 0 : k1 < k2 ? -1 : this.actualComparator.compare(k1, k2);
/*      */   }
/*      */ 
/*      */   private Entry findKey(byte k)
/*      */   {
/*  265 */     Entry e = this.tree;
/*      */     int cmp;
/*  268 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  269 */       e = cmp < 0 ? e.left() : e.right();
/*      */     }
/*  271 */     return e;
/*      */   }
/*      */ 
/*      */   final Entry locateKey(byte k)
/*      */   {
/*  282 */     Entry e = this.tree; Entry last = this.tree;
/*  283 */     int cmp = 0;
/*      */ 
/*  285 */     while ((e != null) && ((cmp = compare(k, e.key)) != 0)) {
/*  286 */       last = e;
/*  287 */       e = cmp < 0 ? e.left() : e.right();
/*      */     }
/*      */ 
/*  290 */     return cmp == 0 ? e : last;
/*      */   }
/*      */ 
/*      */   private void allocatePaths()
/*      */   {
/*  300 */     this.dirPath = new boolean[64];
/*      */ 
/*  304 */     this.nodePath = new Entry[64];
/*      */   }
/*      */ 
/*      */   public boolean add(byte k)
/*      */   {
/*  310 */     int maxDepth = 0;
/*      */ 
/*  312 */     if (this.tree == null) {
/*  313 */       this.count += 1;
/*  314 */       this.tree = (this.lastEntry = this.firstEntry = new Entry(k));
/*      */     }
/*      */     else {
/*  317 */       Entry p = this.tree;
/*  318 */       int i = 0;
/*      */       while (true)
/*      */       {
/*      */         int cmp;
/*  321 */         if ((cmp = compare(k, p.key)) == 0)
/*      */         {
/*  323 */           while (i-- != 0) this.nodePath[i] = null;
/*  324 */           return false;
/*      */         }
/*      */ 
/*  327 */         this.nodePath[i] = p;
/*      */ 
/*  329 */         if ((this.dirPath[(i++)] = cmp > 0 ? 1 : 0) != 0) {
/*  330 */           if (p.succ()) {
/*  331 */             this.count += 1;
/*  332 */             Entry e = new Entry(k);
/*      */ 
/*  334 */             if (p.right == null) this.lastEntry = e;
/*      */ 
/*  336 */             e.left = p;
/*  337 */             e.right = p.right;
/*      */ 
/*  339 */             p.right(e);
/*      */ 
/*  341 */             break;
/*      */           }
/*      */ 
/*  344 */           p = p.right;
/*      */         }
/*      */         else {
/*  347 */           if (p.pred()) {
/*  348 */             this.count += 1;
/*  349 */             Entry e = new Entry(k);
/*      */ 
/*  351 */             if (p.left == null) this.firstEntry = e;
/*      */ 
/*  353 */             e.right = p;
/*  354 */             e.left = p.left;
/*      */ 
/*  356 */             p.left(e);
/*      */ 
/*  358 */             break;
/*      */           }
/*      */ 
/*  361 */           p = p.left;
/*      */         }
/*      */       }
/*      */       Entry e;
/*  365 */       maxDepth = i--;
/*      */ 
/*  367 */       while ((i > 0) && (!this.nodePath[i].black())) {
/*  368 */         if (this.dirPath[(i - 1)] == 0) {
/*  369 */           Entry y = this.nodePath[(i - 1)].right;
/*      */ 
/*  371 */           if ((!this.nodePath[(i - 1)].succ()) && (!y.black())) {
/*  372 */             this.nodePath[i].black(true);
/*  373 */             y.black(true);
/*  374 */             this.nodePath[(i - 1)].black(false);
/*  375 */             i -= 2;
/*      */           }
/*      */           else
/*      */           {
/*  380 */             if (this.dirPath[i] == 0) { y = this.nodePath[i];
/*      */             } else {
/*  382 */               Entry x = this.nodePath[i];
/*  383 */               y = x.right;
/*  384 */               x.right = y.left;
/*  385 */               y.left = x;
/*  386 */               this.nodePath[(i - 1)].left = y;
/*      */ 
/*  388 */               if (y.pred()) {
/*  389 */                 y.pred(false);
/*  390 */                 x.succ(y);
/*      */               }
/*      */             }
/*      */ 
/*  394 */             Entry x = this.nodePath[(i - 1)];
/*  395 */             x.black(false);
/*  396 */             y.black(true);
/*      */ 
/*  398 */             x.left = y.right;
/*  399 */             y.right = x;
/*  400 */             if (i < 2) this.tree = y;
/*  402 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  403 */               this.nodePath[(i - 2)].left = y;
/*      */             }
/*      */ 
/*  406 */             if (!y.succ()) break;
/*  407 */             y.succ(false);
/*  408 */             x.pred(y); break;
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  414 */           Entry y = this.nodePath[(i - 1)].left;
/*      */ 
/*  416 */           if ((!this.nodePath[(i - 1)].pred()) && (!y.black())) {
/*  417 */             this.nodePath[i].black(true);
/*  418 */             y.black(true);
/*  419 */             this.nodePath[(i - 1)].black(false);
/*  420 */             i -= 2;
/*      */           }
/*      */           else
/*      */           {
/*  425 */             if (this.dirPath[i] != 0) { y = this.nodePath[i];
/*      */             } else {
/*  427 */               Entry x = this.nodePath[i];
/*  428 */               y = x.left;
/*  429 */               x.left = y.right;
/*  430 */               y.right = x;
/*  431 */               this.nodePath[(i - 1)].right = y;
/*      */ 
/*  433 */               if (y.succ()) {
/*  434 */                 y.succ(false);
/*  435 */                 x.pred(y);
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*  440 */             Entry x = this.nodePath[(i - 1)];
/*  441 */             x.black(false);
/*  442 */             y.black(true);
/*      */ 
/*  444 */             x.right = y.left;
/*  445 */             y.left = x;
/*  446 */             if (i < 2) this.tree = y;
/*  448 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = y; else {
/*  449 */               this.nodePath[(i - 2)].left = y;
/*      */             }
/*      */ 
/*  452 */             if (!y.pred()) break;
/*  453 */             y.pred(false);
/*  454 */             x.succ(y); break;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  462 */     this.tree.black(true);
/*      */ 
/*  464 */     while (maxDepth-- != 0) this.nodePath[maxDepth] = null;
/*      */ 
/*  469 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean remove(byte k)
/*      */   {
/*  475 */     if (this.tree == null) return false;
/*      */ 
/*  477 */     Entry p = this.tree;
/*      */ 
/*  479 */     int i = 0;
/*  480 */     byte kk = k;
/*      */     int cmp;
/*  483 */     while ((cmp = compare(kk, p.key)) != 0)
/*      */     {
/*  485 */       this.dirPath[i] = (cmp > 0 ? 1 : false);
/*  486 */       this.nodePath[i] = p;
/*      */ 
/*  488 */       if (this.dirPath[(i++)] != 0) {
/*  489 */         if ((p = p.right()) == null)
/*      */         {
/*  491 */           while (i-- != 0) this.nodePath[i] = null;
/*  492 */           return false;
/*      */         }
/*      */ 
/*      */       }
/*  496 */       else if ((p = p.left()) == null)
/*      */       {
/*  498 */         while (i-- != 0) this.nodePath[i] = null;
/*  499 */         return false;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  504 */     if (p.left == null) this.firstEntry = p.next();
/*  505 */     if (p.right == null) this.lastEntry = p.prev();
/*      */ 
/*  507 */     if (p.succ()) {
/*  508 */       if (p.pred()) {
/*  509 */         if (i == 0) this.tree = p.left;
/*  511 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].succ(p.right); else
/*  512 */           this.nodePath[(i - 1)].pred(p.left);
/*      */       }
/*      */       else
/*      */       {
/*  516 */         p.prev().right = p.right;
/*      */ 
/*  518 */         if (i == 0) this.tree = p.left;
/*  520 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = p.left; else {
/*  521 */           this.nodePath[(i - 1)].left = p.left;
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  527 */       Entry r = p.right;
/*      */ 
/*  529 */       if (r.pred()) {
/*  530 */         r.left = p.left;
/*  531 */         r.pred(p.pred());
/*  532 */         if (!r.pred()) r.prev().right = r;
/*  533 */         if (i == 0) this.tree = r;
/*  535 */         else if (this.dirPath[(i - 1)] != 0) this.nodePath[(i - 1)].right = r; else {
/*  536 */           this.nodePath[(i - 1)].left = r;
/*      */         }
/*      */ 
/*  539 */         boolean color = r.black();
/*  540 */         r.black(p.black());
/*  541 */         p.black(color);
/*  542 */         this.dirPath[i] = true;
/*  543 */         this.nodePath[(i++)] = r;
/*      */       }
/*      */       else {
/*  547 */         int j = i++;
/*      */         Entry s;
/*      */         while (true) {
/*  550 */           this.dirPath[i] = false;
/*  551 */           this.nodePath[(i++)] = r;
/*  552 */           s = r.left;
/*  553 */           if (s.pred()) break;
/*  554 */           r = s;
/*      */         }
/*      */ 
/*  557 */         this.dirPath[j] = true;
/*  558 */         this.nodePath[j] = s;
/*      */ 
/*  560 */         if (s.succ()) r.pred(s); else {
/*  561 */           r.left = s.right;
/*      */         }
/*  563 */         s.left = p.left;
/*      */ 
/*  565 */         if (!p.pred()) {
/*  566 */           p.prev().right = s;
/*  567 */           s.pred(false);
/*      */         }
/*      */ 
/*  570 */         s.right(p.right);
/*      */ 
/*  572 */         boolean color = s.black();
/*  573 */         s.black(p.black());
/*  574 */         p.black(color);
/*      */ 
/*  576 */         if (j == 0) this.tree = s;
/*  578 */         else if (this.dirPath[(j - 1)] != 0) this.nodePath[(j - 1)].right = s; else {
/*  579 */           this.nodePath[(j - 1)].left = s;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  584 */     int maxDepth = i;
/*      */ 
/*  586 */     if (p.black()) {
/*  587 */       for (; i > 0; i--) {
/*  588 */         if (((this.dirPath[(i - 1)] != 0) && (!this.nodePath[(i - 1)].succ())) || ((this.dirPath[(i - 1)] == 0) && (!this.nodePath[(i - 1)].pred())))
/*      */         {
/*  590 */           Entry x = this.dirPath[(i - 1)] != 0 ? this.nodePath[(i - 1)].right : this.nodePath[(i - 1)].left;
/*      */ 
/*  592 */           if (!x.black()) {
/*  593 */             x.black(true);
/*  594 */             break;
/*      */           }
/*      */         }
/*      */ 
/*  598 */         if (this.dirPath[(i - 1)] == 0) {
/*  599 */           Entry w = this.nodePath[(i - 1)].right;
/*      */ 
/*  601 */           if (!w.black()) {
/*  602 */             w.black(true);
/*  603 */             this.nodePath[(i - 1)].black(false);
/*      */ 
/*  605 */             this.nodePath[(i - 1)].right = w.left;
/*  606 */             w.left = this.nodePath[(i - 1)];
/*      */ 
/*  608 */             if (i < 2) this.tree = w;
/*  610 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  611 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  614 */             this.nodePath[i] = this.nodePath[(i - 1)];
/*  615 */             this.dirPath[i] = false;
/*  616 */             this.nodePath[(i - 1)] = w;
/*  617 */             if (maxDepth == i++) maxDepth++;
/*      */ 
/*  619 */             w = this.nodePath[(i - 1)].right;
/*      */           }
/*      */ 
/*  622 */           if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*      */           {
/*  624 */             w.black(false);
/*      */           }
/*      */           else {
/*  627 */             if ((w.succ()) || (w.right.black())) {
/*  628 */               Entry y = w.left;
/*      */ 
/*  630 */               y.black(true);
/*  631 */               w.black(false);
/*  632 */               w.left = y.right;
/*  633 */               y.right = w;
/*  634 */               w = this.nodePath[(i - 1)].right = y;
/*      */ 
/*  636 */               if (w.succ()) {
/*  637 */                 w.succ(false);
/*  638 */                 w.right.pred(w);
/*      */               }
/*      */             }
/*      */ 
/*  642 */             w.black(this.nodePath[(i - 1)].black());
/*  643 */             this.nodePath[(i - 1)].black(true);
/*  644 */             w.right.black(true);
/*      */ 
/*  646 */             this.nodePath[(i - 1)].right = w.left;
/*  647 */             w.left = this.nodePath[(i - 1)];
/*      */ 
/*  649 */             if (i < 2) this.tree = w;
/*  651 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  652 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  655 */             if (!w.pred()) break;
/*  656 */             w.pred(false);
/*  657 */             this.nodePath[(i - 1)].succ(w); break;
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  663 */           Entry w = this.nodePath[(i - 1)].left;
/*      */ 
/*  665 */           if (!w.black()) {
/*  666 */             w.black(true);
/*  667 */             this.nodePath[(i - 1)].black(false);
/*      */ 
/*  669 */             this.nodePath[(i - 1)].left = w.right;
/*  670 */             w.right = this.nodePath[(i - 1)];
/*      */ 
/*  672 */             if (i < 2) this.tree = w;
/*  674 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  675 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  678 */             this.nodePath[i] = this.nodePath[(i - 1)];
/*  679 */             this.dirPath[i] = true;
/*  680 */             this.nodePath[(i - 1)] = w;
/*  681 */             if (maxDepth == i++) maxDepth++;
/*      */ 
/*  683 */             w = this.nodePath[(i - 1)].left;
/*      */           }
/*      */ 
/*  686 */           if (((w.pred()) || (w.left.black())) && ((w.succ()) || (w.right.black())))
/*      */           {
/*  688 */             w.black(false);
/*      */           }
/*      */           else {
/*  691 */             if ((w.pred()) || (w.left.black())) {
/*  692 */               Entry y = w.right;
/*      */ 
/*  694 */               y.black(true);
/*  695 */               w.black(false);
/*  696 */               w.right = y.left;
/*  697 */               y.left = w;
/*  698 */               w = this.nodePath[(i - 1)].left = y;
/*      */ 
/*  700 */               if (w.pred()) {
/*  701 */                 w.pred(false);
/*  702 */                 w.left.succ(w);
/*      */               }
/*      */             }
/*      */ 
/*  706 */             w.black(this.nodePath[(i - 1)].black());
/*  707 */             this.nodePath[(i - 1)].black(true);
/*  708 */             w.left.black(true);
/*      */ 
/*  710 */             this.nodePath[(i - 1)].left = w.right;
/*  711 */             w.right = this.nodePath[(i - 1)];
/*      */ 
/*  713 */             if (i < 2) this.tree = w;
/*  715 */             else if (this.dirPath[(i - 2)] != 0) this.nodePath[(i - 2)].right = w; else {
/*  716 */               this.nodePath[(i - 2)].left = w;
/*      */             }
/*      */ 
/*  719 */             if (!w.succ()) break;
/*  720 */             w.succ(false);
/*  721 */             this.nodePath[(i - 1)].pred(w); break;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  728 */       if (this.tree != null) this.tree.black(true);
/*      */     }
/*      */ 
/*  731 */     this.count -= 1;
/*      */ 
/*  733 */     while (maxDepth-- != 0) this.nodePath[maxDepth] = null;
/*      */ 
/*  738 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean contains(byte k)
/*      */   {
/*  743 */     return findKey(k) != null;
/*      */   }
/*      */   public void clear() {
/*  746 */     this.count = 0;
/*  747 */     this.tree = null;
/*  748 */     this.firstEntry = (this.lastEntry = null);
/*      */   }
/*      */ 
/*      */   public int size()
/*      */   {
/*  938 */     return this.count;
/*      */   }
/*      */   public boolean isEmpty() {
/*  941 */     return this.count == 0;
/*      */   }
/*      */   public byte firstByte() {
/*  944 */     if (this.tree == null) throw new NoSuchElementException();
/*  945 */     return this.firstEntry.key;
/*      */   }
/*      */   public byte lastByte() {
/*  948 */     if (this.tree == null) throw new NoSuchElementException();
/*  949 */     return this.lastEntry.key;
/*      */   }
/*      */ 
/*      */   public ByteBidirectionalIterator iterator()
/*      */   {
/* 1019 */     return new SetIterator();
/*      */   }
/*      */   public ByteBidirectionalIterator iterator(byte from) {
/* 1022 */     return new SetIterator(from);
/*      */   }
/*      */   public ByteComparator comparator() {
/* 1025 */     return this.actualComparator;
/*      */   }
/*      */   public ByteSortedSet headSet(byte to) {
/* 1028 */     return new Subset((byte)0, true, to, false);
/*      */   }
/*      */   public ByteSortedSet tailSet(byte from) {
/* 1031 */     return new Subset(from, false, (byte)0, true);
/*      */   }
/*      */   public ByteSortedSet subSet(byte from, byte to) {
/* 1034 */     return new Subset(from, false, to, false);
/*      */   }
/*      */ 
/*      */   public Object clone()
/*      */   {
/*      */     ByteRBTreeSet c;
/*      */     try
/*      */     {
/* 1226 */       c = (ByteRBTreeSet)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1229 */       throw new InternalError();
/*      */     }
/* 1231 */     c.allocatePaths();
/* 1232 */     if (this.count != 0)
/*      */     {
/* 1234 */       Entry rp = new Entry(); Entry rq = new Entry();
/* 1235 */       Entry p = rp;
/* 1236 */       rp.left(this.tree);
/* 1237 */       Entry q = rq;
/* 1238 */       rq.pred(null);
/*      */       while (true) {
/* 1240 */         if (!p.pred()) {
/* 1241 */           Entry e = p.left.clone();
/* 1242 */           e.pred(q.left);
/* 1243 */           e.succ(q);
/* 1244 */           q.left(e);
/* 1245 */           p = p.left;
/* 1246 */           q = q.left;
/*      */         }
/*      */         else {
/* 1249 */           while (p.succ()) {
/* 1250 */             p = p.right;
/* 1251 */             if (p == null) {
/* 1252 */               q.right = null;
/* 1253 */               c.tree = rq.left;
/* 1254 */               c.firstEntry = c.tree;
/* 1255 */               while (c.firstEntry.left != null) c.firstEntry = c.firstEntry.left;
/* 1256 */               c.lastEntry = c.tree;
/* 1257 */               while (c.lastEntry.right != null) c.lastEntry = c.lastEntry.right;
/* 1258 */               return c;
/*      */             }
/* 1260 */             q = q.right;
/*      */           }
/* 1262 */           p = p.right;
/* 1263 */           q = q.right;
/*      */         }
/* 1265 */         if (!p.succ()) {
/* 1266 */           Entry e = p.right.clone();
/* 1267 */           e.succ(q.right);
/* 1268 */           e.pred(q);
/* 1269 */           q.right(e);
/*      */         }
/*      */       }
/*      */     }
/* 1273 */     return c;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1276 */     int n = this.count;
/* 1277 */     SetIterator i = new SetIterator();
/* 1278 */     s.defaultWriteObject();
/* 1279 */     while (n-- != 0) s.writeByte(i.nextByte());
/*      */   }
/*      */ 
/*      */   private Entry readTree(ObjectInputStream s, int n, Entry pred, Entry succ)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1290 */     if (n == 1) {
/* 1291 */       Entry top = new Entry(s.readByte());
/* 1292 */       top.pred(pred);
/* 1293 */       top.succ(succ);
/* 1294 */       top.black(true);
/* 1295 */       return top;
/*      */     }
/* 1297 */     if (n == 2)
/*      */     {
/* 1300 */       Entry top = new Entry(s.readByte());
/* 1301 */       top.black(true);
/* 1302 */       top.right(new Entry(s.readByte()));
/* 1303 */       top.right.pred(top);
/* 1304 */       top.pred(pred);
/* 1305 */       top.right.succ(succ);
/* 1306 */       return top;
/*      */     }
/*      */ 
/* 1309 */     int rightN = n / 2; int leftN = n - rightN - 1;
/* 1310 */     Entry top = new Entry();
/* 1311 */     top.left(readTree(s, leftN, pred, top));
/* 1312 */     top.key = s.readByte();
/* 1313 */     top.black(true);
/* 1314 */     top.right(readTree(s, rightN, top, succ));
/* 1315 */     if (n + 2 == (n + 2 & -(n + 2))) top.right.black(false);
/* 1316 */     return top;
/*      */   }
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1319 */     s.defaultReadObject();
/*      */ 
/* 1322 */     setActualComparator();
/* 1323 */     allocatePaths();
/* 1324 */     if (this.count != 0) {
/* 1325 */       this.tree = readTree(s, this.count, null, null);
/*      */ 
/* 1327 */       Entry e = this.tree;
/* 1328 */       while (e.left() != null) e = e.left();
/* 1329 */       this.firstEntry = e;
/* 1330 */       e = this.tree;
/* 1331 */       while (e.right() != null) e = e.right();
/* 1332 */       this.lastEntry = e;
/*      */     }
/*      */   }
/*      */   private void checkNodePath() {
/*      */   }
/*      */ 
/* 1338 */   private int checkTree(Entry e, int d, int D) { return 0; }
/*      */ 
/*      */ 
/*      */   private final class Subset extends AbstractByteSortedSet
/*      */     implements Serializable, ByteSortedSet
/*      */   {
/*      */     public static final long serialVersionUID = -7046029254386353129L;
/*      */     byte from;
/*      */     byte to;
/*      */     boolean bottom;
/*      */     boolean top;
/*      */ 
/*      */     public Subset(byte from, boolean bottom, byte to, boolean top)
/*      */     {
/* 1063 */       if ((!bottom) && (!top) && (ByteRBTreeSet.this.compare(from, to) > 0)) throw new IllegalArgumentException(new StringBuilder().append("Start element (").append(from).append(") is larger than end element (").append(to).append(")").toString());
/* 1064 */       this.from = from;
/* 1065 */       this.bottom = bottom;
/* 1066 */       this.to = to;
/* 1067 */       this.top = top;
/*      */     }
/*      */     public void clear() {
/* 1070 */       SubsetIterator i = new SubsetIterator();
/* 1071 */       while (i.hasNext()) {
/* 1072 */         i.next();
/* 1073 */         i.remove();
/*      */       }
/*      */     }
/*      */ 
/*      */     final boolean in(byte k)
/*      */     {
/* 1081 */       return ((this.bottom) || (ByteRBTreeSet.this.compare(k, this.from) >= 0)) && ((this.top) || (ByteRBTreeSet.this.compare(k, this.to) < 0));
/*      */     }
/*      */ 
/*      */     public boolean contains(byte k)
/*      */     {
/* 1086 */       return (in(k)) && (ByteRBTreeSet.this.contains(k));
/*      */     }
/*      */     public boolean add(byte k) {
/* 1089 */       if (!in(k)) throw new IllegalArgumentException(new StringBuilder().append("Element (").append(k).append(") out of range [").append(this.bottom ? "-" : String.valueOf(this.from)).append(", ").append(this.top ? "-" : String.valueOf(this.to)).append(")").toString());
/* 1090 */       return ByteRBTreeSet.this.add(k);
/*      */     }
/*      */ 
/*      */     public boolean remove(byte k) {
/* 1094 */       if (!in(k)) return false;
/* 1095 */       return ByteRBTreeSet.this.remove(k);
/*      */     }
/*      */     public int size() {
/* 1098 */       SubsetIterator i = new SubsetIterator();
/* 1099 */       int n = 0;
/* 1100 */       while (i.hasNext()) {
/* 1101 */         n++;
/* 1102 */         i.next();
/*      */       }
/* 1104 */       return n;
/*      */     }
/*      */     public boolean isEmpty() {
/* 1107 */       return !new SubsetIterator().hasNext();
/*      */     }
/*      */     public ByteComparator comparator() {
/* 1110 */       return ByteRBTreeSet.this.actualComparator;
/*      */     }
/*      */     public ByteBidirectionalIterator iterator() {
/* 1113 */       return new SubsetIterator();
/*      */     }
/*      */     public ByteBidirectionalIterator iterator(byte from) {
/* 1116 */       return new SubsetIterator(from);
/*      */     }
/*      */     public ByteSortedSet headSet(byte to) {
/* 1119 */       if (this.top) return new Subset(ByteRBTreeSet.this, this.from, this.bottom, to, false);
/* 1120 */       return ByteRBTreeSet.this.compare(to, this.to) < 0 ? new Subset(ByteRBTreeSet.this, this.from, this.bottom, to, false) : this;
/*      */     }
/*      */     public ByteSortedSet tailSet(byte from) {
/* 1123 */       if (this.bottom) return new Subset(ByteRBTreeSet.this, from, false, this.to, this.top);
/* 1124 */       return ByteRBTreeSet.this.compare(from, this.from) > 0 ? new Subset(ByteRBTreeSet.this, from, false, this.to, this.top) : this;
/*      */     }
/*      */     public ByteSortedSet subSet(byte from, byte to) {
/* 1127 */       if ((this.top) && (this.bottom)) return new Subset(ByteRBTreeSet.this, from, false, to, false);
/* 1128 */       if (!this.top) to = ByteRBTreeSet.this.compare(to, this.to) < 0 ? to : this.to;
/* 1129 */       if (!this.bottom) from = ByteRBTreeSet.this.compare(from, this.from) > 0 ? from : this.from;
/* 1130 */       if ((!this.top) && (!this.bottom) && (from == this.from) && (to == this.to)) return this;
/* 1131 */       return new Subset(ByteRBTreeSet.this, from, false, to, false);
/*      */     }
/*      */ 
/*      */     public ByteRBTreeSet.Entry firstEntry()
/*      */     {
/* 1138 */       if (ByteRBTreeSet.this.tree == null) return null;
/* 1141 */       ByteRBTreeSet.Entry e;
/*      */       ByteRBTreeSet.Entry e;
/* 1141 */       if (this.bottom) { e = ByteRBTreeSet.this.firstEntry;
/*      */       } else {
/* 1143 */         e = ByteRBTreeSet.this.locateKey(this.from);
/*      */ 
/* 1145 */         if (ByteRBTreeSet.this.compare(e.key, this.from) < 0) e = e.next();
/*      */       }
/*      */ 
/* 1148 */       if ((e == null) || ((!this.top) && (ByteRBTreeSet.this.compare(e.key, this.to) >= 0))) return null;
/* 1149 */       return e;
/*      */     }
/*      */ 
/*      */     public ByteRBTreeSet.Entry lastEntry()
/*      */     {
/* 1156 */       if (ByteRBTreeSet.this.tree == null) return null;
/* 1159 */       ByteRBTreeSet.Entry e;
/*      */       ByteRBTreeSet.Entry e;
/* 1159 */       if (this.top) { e = ByteRBTreeSet.this.lastEntry;
/*      */       } else {
/* 1161 */         e = ByteRBTreeSet.this.locateKey(this.to);
/*      */ 
/* 1163 */         if (ByteRBTreeSet.this.compare(e.key, this.to) >= 0) e = e.prev();
/*      */       }
/*      */ 
/* 1166 */       if ((e == null) || ((!this.bottom) && (ByteRBTreeSet.this.compare(e.key, this.from) < 0))) return null;
/* 1167 */       return e;
/*      */     }
/*      */     public byte firstByte() {
/* 1170 */       ByteRBTreeSet.Entry e = firstEntry();
/* 1171 */       if (e == null) throw new NoSuchElementException();
/* 1172 */       return e.key;
/*      */     }
/*      */     public byte lastByte() {
/* 1175 */       ByteRBTreeSet.Entry e = lastEntry();
/* 1176 */       if (e == null) throw new NoSuchElementException();
/* 1177 */       return e.key;
/*      */     }
/*      */ 
/*      */     private final class SubsetIterator extends ByteRBTreeSet.SetIterator
/*      */     {
/*      */       SubsetIterator()
/*      */       {
/* 1187 */         super();
/* 1188 */         this.next = ByteRBTreeSet.Subset.this.firstEntry();
/*      */       }
/*      */       SubsetIterator(byte k) {
/* 1191 */         this();
/* 1192 */         if (this.next != null)
/* 1193 */           if ((!ByteRBTreeSet.Subset.this.bottom) && (ByteRBTreeSet.this.compare(k, this.next.key) < 0)) { this.prev = null;
/* 1194 */           } else if ((!ByteRBTreeSet.Subset.this.top) && (ByteRBTreeSet.this.compare(k, (this.prev = ByteRBTreeSet.Subset.this.lastEntry()).key) >= 0)) { this.next = null;
/*      */           } else {
/* 1196 */             this.next = ByteRBTreeSet.this.locateKey(k);
/* 1197 */             if (ByteRBTreeSet.this.compare(this.next.key, k) <= 0) {
/* 1198 */               this.prev = this.next;
/* 1199 */               this.next = this.next.next();
/*      */             } else {
/* 1201 */               this.prev = this.next.prev();
/*      */             }
/*      */           }
/*      */       }
/*      */ 
/* 1206 */       void updatePrevious() { this.prev = this.prev.prev();
/* 1207 */         if ((!ByteRBTreeSet.Subset.this.bottom) && (this.prev != null) && (ByteRBTreeSet.this.compare(this.prev.key, ByteRBTreeSet.Subset.this.from) < 0)) this.prev = null;  } 
/*      */       void updateNext()
/*      */       {
/* 1210 */         this.next = this.next.next();
/* 1211 */         if ((!ByteRBTreeSet.Subset.this.top) && (this.next != null) && (ByteRBTreeSet.this.compare(this.next.key, ByteRBTreeSet.Subset.this.to) >= 0)) this.next = null;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private class SetIterator extends AbstractByteListIterator
/*      */   {
/*      */     ByteRBTreeSet.Entry prev;
/*      */     ByteRBTreeSet.Entry next;
/*      */     ByteRBTreeSet.Entry curr;
/*  963 */     int index = 0;
/*      */ 
/*  965 */     SetIterator() { this.next = ByteRBTreeSet.this.firstEntry; }
/*      */ 
/*      */     SetIterator(byte k) {
/*  968 */       if ((this.next = ByteRBTreeSet.this.locateKey(k)) != null)
/*  969 */         if (ByteRBTreeSet.this.compare(this.next.key, k) <= 0) {
/*  970 */           this.prev = this.next;
/*  971 */           this.next = this.next.next();
/*      */         } else {
/*  973 */           this.prev = this.next.prev(); }  
/*      */     }
/*      */ 
/*  976 */     public boolean hasNext() { return this.next != null; } 
/*  977 */     public boolean hasPrevious() { return this.prev != null; } 
/*      */     void updateNext() {
/*  979 */       this.next = this.next.next();
/*      */     }
/*      */     ByteRBTreeSet.Entry nextEntry() {
/*  982 */       if (!hasNext()) throw new NoSuchElementException();
/*  983 */       this.curr = (this.prev = this.next);
/*  984 */       this.index += 1;
/*  985 */       updateNext();
/*  986 */       return this.curr;
/*      */     }
/*  988 */     public byte nextByte() { return nextEntry().key; } 
/*  989 */     public byte previousByte() { return previousEntry().key; } 
/*      */     void updatePrevious() {
/*  991 */       this.prev = this.prev.prev();
/*      */     }
/*      */     ByteRBTreeSet.Entry previousEntry() {
/*  994 */       if (!hasPrevious()) throw new NoSuchElementException();
/*  995 */       this.curr = (this.next = this.prev);
/*  996 */       this.index -= 1;
/*  997 */       updatePrevious();
/*  998 */       return this.curr;
/*      */     }
/*      */     public int nextIndex() {
/* 1001 */       return this.index;
/*      */     }
/*      */     public int previousIndex() {
/* 1004 */       return this.index - 1;
/*      */     }
/*      */     public void remove() {
/* 1007 */       if (this.curr == null) throw new IllegalStateException();
/*      */ 
/* 1010 */       if (this.curr == this.prev) this.index -= 1;
/* 1011 */       this.next = (this.prev = this.curr);
/* 1012 */       updatePrevious();
/* 1013 */       updateNext();
/* 1014 */       ByteRBTreeSet.this.remove(this.curr.key);
/* 1015 */       this.curr = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class Entry
/*      */     implements Cloneable
/*      */   {
/*      */     private static final int BLACK_MASK = 1;
/*      */     private static final int SUCC_MASK = -2147483648;
/*      */     private static final int PRED_MASK = 1073741824;
/*      */     byte key;
/*      */     Entry left;
/*      */     Entry right;
/*      */     int info;
/*      */ 
/*      */     Entry()
/*      */     {
/*      */     }
/*      */ 
/*      */     Entry(byte k)
/*      */     {
/*  777 */       this.key = k;
/*  778 */       this.info = -1073741824;
/*      */     }
/*      */ 
/*      */     Entry left()
/*      */     {
/*  786 */       return (this.info & 0x40000000) != 0 ? null : this.left;
/*      */     }
/*      */ 
/*      */     Entry right()
/*      */     {
/*  794 */       return (this.info & 0x80000000) != 0 ? null : this.right;
/*      */     }
/*      */ 
/*      */     boolean pred()
/*      */     {
/*  800 */       return (this.info & 0x40000000) != 0;
/*      */     }
/*      */ 
/*      */     boolean succ()
/*      */     {
/*  806 */       return (this.info & 0x80000000) != 0;
/*      */     }
/*      */ 
/*      */     void pred(boolean pred)
/*      */     {
/*  812 */       if (pred) this.info |= 1073741824; else
/*  813 */         this.info &= -1073741825;
/*      */     }
/*      */ 
/*      */     void succ(boolean succ)
/*      */     {
/*  819 */       if (succ) this.info |= -2147483648; else
/*  820 */         this.info &= 2147483647;
/*      */     }
/*      */ 
/*      */     void pred(Entry pred)
/*      */     {
/*  826 */       this.info |= 1073741824;
/*  827 */       this.left = pred;
/*      */     }
/*      */ 
/*      */     void succ(Entry succ)
/*      */     {
/*  833 */       this.info |= -2147483648;
/*  834 */       this.right = succ;
/*      */     }
/*      */ 
/*      */     void left(Entry left)
/*      */     {
/*  840 */       this.info &= -1073741825;
/*  841 */       this.left = left;
/*      */     }
/*      */ 
/*      */     void right(Entry right)
/*      */     {
/*  847 */       this.info &= 2147483647;
/*  848 */       this.right = right;
/*      */     }
/*      */ 
/*      */     boolean black()
/*      */     {
/*  854 */       return (this.info & 0x1) != 0;
/*      */     }
/*      */ 
/*      */     void black(boolean black)
/*      */     {
/*  860 */       if (black) this.info |= 1; else
/*  861 */         this.info &= -2;
/*      */     }
/*      */ 
/*      */     Entry next()
/*      */     {
/*  868 */       Entry next = this.right;
/*  869 */       for ((this.info & 0x80000000) != 0; (next.info & 0x40000000) == 0; next = next.left);
/*  870 */       return next;
/*      */     }
/*      */ 
/*      */     Entry prev()
/*      */     {
/*  877 */       Entry prev = this.left;
/*  878 */       for ((this.info & 0x40000000) != 0; (prev.info & 0x80000000) == 0; prev = prev.right);
/*  879 */       return prev;
/*      */     }
/*      */ 
/*      */     public Entry clone() {
/*      */       Entry c;
/*      */       try {
/*  885 */         c = (Entry)super.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException cantHappen) {
/*  888 */         throw new InternalError();
/*      */       }
/*  890 */       c.key = this.key;
/*  891 */       c.info = this.info;
/*  892 */       return c;
/*      */     }
/*      */     public boolean equals(Object o) {
/*  895 */       if (!(o instanceof Entry)) return false;
/*  896 */       Entry e = (Entry)o;
/*  897 */       return this.key == e.key;
/*      */     }
/*      */     public int hashCode() {
/*  900 */       return this.key;
/*      */     }
/*      */     public String toString() {
/*  903 */       return String.valueOf(this.key);
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteRBTreeSet
 * JD-Core Version:    0.6.2
 */