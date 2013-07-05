/*      */ package it.unimi.dsi.fastutil.doubles;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
/*      */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*      */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*      */ import it.unimi.dsi.fastutil.ints.IntListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Comparator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class Double2IntLinkedOpenHashMap extends AbstractDouble2IntSortedMap
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   public static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient double[] key;
/*      */   protected transient int[] value;
/*      */   protected transient boolean[] used;
/*      */   protected final float f;
/*      */   protected transient int n;
/*      */   protected transient int maxFill;
/*      */   protected transient int mask;
/*      */   protected int size;
/*      */   protected volatile transient Double2IntSortedMap.FastSortedEntrySet entries;
/*      */   protected volatile transient DoubleSortedSet keys;
/*      */   protected volatile transient IntCollection values;
/*  130 */   protected transient int first = -1;
/*      */ 
/*  132 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(int expected, float f)
/*      */   {
/*  151 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153 */     this.f = f;
/*  154 */     this.n = HashCommon.arraySize(expected, f);
/*  155 */     this.mask = (this.n - 1);
/*  156 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  157 */     this.key = new double[this.n];
/*  158 */     this.value = new int[this.n];
/*  159 */     this.used = new boolean[this.n];
/*  160 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(int expected)
/*      */   {
/*  167 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap()
/*      */   {
/*  173 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(Map<? extends Double, ? extends Integer> m, float f)
/*      */   {
/*  181 */     this(m.size(), f);
/*  182 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(Map<? extends Double, ? extends Integer> m)
/*      */   {
/*  189 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(Double2IntMap m, float f)
/*      */   {
/*  197 */     this(m.size(), f);
/*  198 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(Double2IntMap m)
/*      */   {
/*  205 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(double[] k, int[] v, float f)
/*      */   {
/*  215 */     this(k.length, f);
/*  216 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap(double[] k, int[] v)
/*      */   {
/*  226 */     this(k, v, 0.75F);
/*      */   }
/*      */ 
/*      */   public int put(double k, int v)
/*      */   {
/*  234 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  236 */     while (this.used[pos] != 0) {
/*  237 */       if (this.key[pos] == k) {
/*  238 */         int oldValue = this.value[pos];
/*  239 */         this.value[pos] = v;
/*  240 */         return oldValue;
/*      */       }
/*  242 */       pos = pos + 1 & this.mask;
/*      */     }
/*  244 */     this.used[pos] = true;
/*  245 */     this.key[pos] = k;
/*  246 */     this.value[pos] = v;
/*  247 */     if (this.size == 0) {
/*  248 */       this.first = (this.last = pos);
/*      */ 
/*  250 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  253 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  254 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  255 */       this.last = pos;
/*      */     }
/*  257 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  259 */     return this.defRetValue;
/*      */   }
/*      */   public Integer put(Double ok, Integer ov) {
/*  262 */     int v = ov.intValue();
/*  263 */     double k = ok.doubleValue();
/*      */ 
/*  265 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  267 */     while (this.used[pos] != 0) {
/*  268 */       if (this.key[pos] == k) {
/*  269 */         Integer oldValue = Integer.valueOf(this.value[pos]);
/*  270 */         this.value[pos] = v;
/*  271 */         return oldValue;
/*      */       }
/*  273 */       pos = pos + 1 & this.mask;
/*      */     }
/*  275 */     this.used[pos] = true;
/*  276 */     this.key[pos] = k;
/*  277 */     this.value[pos] = v;
/*  278 */     if (this.size == 0) {
/*  279 */       this.first = (this.last = pos);
/*      */ 
/*  281 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  284 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  285 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  286 */       this.last = pos;
/*      */     }
/*  288 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  290 */     return null;
/*      */   }
/*      */ 
/*      */   public int add(double k, int incr)
/*      */   {
/*  305 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  307 */     while (this.used[pos] != 0) {
/*  308 */       if (this.key[pos] == k) {
/*  309 */         int oldValue = this.value[pos];
/*  310 */         this.value[pos] += incr;
/*  311 */         return oldValue;
/*      */       }
/*  313 */       pos = pos + 1 & this.mask;
/*      */     }
/*  315 */     this.used[pos] = true;
/*  316 */     this.key[pos] = k;
/*  317 */     this.value[pos] = (this.defRetValue + incr);
/*  318 */     if (this.size == 0) {
/*  319 */       this.first = (this.last = pos);
/*      */ 
/*  321 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  324 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  325 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  326 */       this.last = pos;
/*      */     }
/*  328 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  330 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   protected final int shiftKeys(int pos)
/*      */   {
/*      */     int last;
/*      */     while (true)
/*      */     {
/*  342 */       pos = (last = pos) + 1 & this.mask;
/*  343 */       while (this.used[pos] != 0) {
/*  344 */         int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(this.key[pos])) & this.mask;
/*  345 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  346 */         pos = pos + 1 & this.mask;
/*      */       }
/*  348 */       if (this.used[pos] == 0) break;
/*  349 */       this.key[last] = this.key[pos];
/*  350 */       this.value[last] = this.value[pos];
/*  351 */       fixPointers(pos, last);
/*      */     }
/*  353 */     this.used[last] = false;
/*  354 */     return last;
/*      */   }
/*      */ 
/*      */   public int remove(double k)
/*      */   {
/*  359 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  361 */     while (this.used[pos] != 0) {
/*  362 */       if (this.key[pos] == k) {
/*  363 */         this.size -= 1;
/*  364 */         fixPointers(pos);
/*  365 */         int v = this.value[pos];
/*  366 */         shiftKeys(pos);
/*  367 */         return v;
/*      */       }
/*  369 */       pos = pos + 1 & this.mask;
/*      */     }
/*  371 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public Integer remove(Object ok) {
/*  375 */     double k = ((Double)ok).doubleValue();
/*      */ 
/*  377 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  379 */     while (this.used[pos] != 0) {
/*  380 */       if (this.key[pos] == k) {
/*  381 */         this.size -= 1;
/*  382 */         fixPointers(pos);
/*  383 */         int v = this.value[pos];
/*  384 */         shiftKeys(pos);
/*  385 */         return Integer.valueOf(v);
/*      */       }
/*  387 */       pos = pos + 1 & this.mask;
/*      */     }
/*  389 */     return null;
/*      */   }
/*      */ 
/*      */   public int removeFirstInt()
/*      */   {
/*  396 */     if (this.size == 0) throw new NoSuchElementException();
/*  397 */     this.size -= 1;
/*  398 */     int pos = this.first;
/*      */ 
/*  400 */     this.first = ((int)this.link[pos]);
/*  401 */     if (0 <= this.first)
/*      */     {
/*  403 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  405 */     int v = this.value[pos];
/*  406 */     shiftKeys(pos);
/*  407 */     return v;
/*      */   }
/*      */ 
/*      */   public int removeLastInt()
/*      */   {
/*  414 */     if (this.size == 0) throw new NoSuchElementException();
/*  415 */     this.size -= 1;
/*  416 */     int pos = this.last;
/*      */ 
/*  418 */     this.last = ((int)(this.link[pos] >>> 32));
/*  419 */     if (0 <= this.last)
/*      */     {
/*  421 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  423 */     int v = this.value[pos];
/*  424 */     shiftKeys(pos);
/*  425 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  428 */     if ((this.size == 1) || (this.first == i)) return;
/*  429 */     if (this.last == i) {
/*  430 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  432 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  435 */       long linki = this.link[i];
/*  436 */       int prev = (int)(linki >>> 32);
/*  437 */       int next = (int)linki;
/*  438 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  439 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  441 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  442 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  443 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  446 */     if ((this.size == 1) || (this.last == i)) return;
/*  447 */     if (this.first == i) {
/*  448 */       this.first = ((int)this.link[i]);
/*      */ 
/*  450 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  453 */       long linki = this.link[i];
/*  454 */       int prev = (int)(linki >>> 32);
/*  455 */       int next = (int)linki;
/*  456 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  457 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  459 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  460 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  461 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public int getAndMoveToFirst(double k)
/*      */   {
/*  469 */     double[] key = this.key;
/*  470 */     boolean[] used = this.used;
/*  471 */     int mask = this.mask;
/*      */ 
/*  473 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*      */ 
/*  475 */     while (used[pos] != 0) {
/*  476 */       if (k == key[pos]) {
/*  477 */         moveIndexToFirst(pos);
/*  478 */         return this.value[pos];
/*      */       }
/*  480 */       pos = pos + 1 & mask;
/*      */     }
/*  482 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public int getAndMoveToLast(double k)
/*      */   {
/*  490 */     double[] key = this.key;
/*  491 */     boolean[] used = this.used;
/*  492 */     int mask = this.mask;
/*      */ 
/*  494 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*      */ 
/*  496 */     while (used[pos] != 0) {
/*  497 */       if (k == key[pos]) {
/*  498 */         moveIndexToLast(pos);
/*  499 */         return this.value[pos];
/*      */       }
/*  501 */       pos = pos + 1 & mask;
/*      */     }
/*  503 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public int putAndMoveToFirst(double k, int v)
/*      */   {
/*  512 */     double[] key = this.key;
/*  513 */     boolean[] used = this.used;
/*  514 */     int mask = this.mask;
/*      */ 
/*  516 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*      */ 
/*  518 */     while (used[pos] != 0) {
/*  519 */       if (k == key[pos]) {
/*  520 */         int oldValue = this.value[pos];
/*  521 */         this.value[pos] = v;
/*  522 */         moveIndexToFirst(pos);
/*  523 */         return oldValue;
/*      */       }
/*  525 */       pos = pos + 1 & mask;
/*      */     }
/*  527 */     used[pos] = true;
/*  528 */     key[pos] = k;
/*  529 */     this.value[pos] = v;
/*  530 */     if (this.size == 0) {
/*  531 */       this.first = (this.last = pos);
/*      */ 
/*  533 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  536 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  537 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  538 */       this.first = pos;
/*      */     }
/*  540 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  542 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public int putAndMoveToLast(double k, int v)
/*      */   {
/*  551 */     double[] key = this.key;
/*  552 */     boolean[] used = this.used;
/*  553 */     int mask = this.mask;
/*      */ 
/*  555 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & mask;
/*      */ 
/*  557 */     while (used[pos] != 0) {
/*  558 */       if (k == key[pos]) {
/*  559 */         int oldValue = this.value[pos];
/*  560 */         this.value[pos] = v;
/*  561 */         moveIndexToLast(pos);
/*  562 */         return oldValue;
/*      */       }
/*  564 */       pos = pos + 1 & mask;
/*      */     }
/*  566 */     used[pos] = true;
/*  567 */     key[pos] = k;
/*  568 */     this.value[pos] = v;
/*  569 */     if (this.size == 0) {
/*  570 */       this.first = (this.last = pos);
/*      */ 
/*  572 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  575 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  576 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  577 */       this.last = pos;
/*      */     }
/*  579 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  581 */     return this.defRetValue;
/*      */   }
/*      */   public Integer get(Double ok) {
/*  584 */     double k = ok.doubleValue();
/*      */ 
/*  586 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  588 */     while (this.used[pos] != 0) {
/*  589 */       if (this.key[pos] == k) return Integer.valueOf(this.value[pos]);
/*  590 */       pos = pos + 1 & this.mask;
/*      */     }
/*  592 */     return null;
/*      */   }
/*      */ 
/*      */   public int get(double k)
/*      */   {
/*  597 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  599 */     while (this.used[pos] != 0) {
/*  600 */       if (this.key[pos] == k) return this.value[pos];
/*  601 */       pos = pos + 1 & this.mask;
/*      */     }
/*  603 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(double k)
/*      */   {
/*  608 */     int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/*      */ 
/*  610 */     while (this.used[pos] != 0) {
/*  611 */       if (this.key[pos] == k) return true;
/*  612 */       pos = pos + 1 & this.mask;
/*      */     }
/*  614 */     return false;
/*      */   }
/*      */   public boolean containsValue(int v) {
/*  617 */     int[] value = this.value;
/*  618 */     boolean[] used = this.used;
/*  619 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*      */         break label16; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  629 */     if (this.size == 0) return;
/*  630 */     this.size = 0;
/*  631 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  633 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  636 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  639 */     return this.size == 0;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public void growthFactor(int growthFactor)
/*      */   {
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public int growthFactor()
/*      */   {
/*  656 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  709 */     if (this.size == 0) {
/*  710 */       this.first = (this.last = -1);
/*  711 */       return;
/*      */     }
/*  713 */     if (this.first == i) {
/*  714 */       this.first = ((int)this.link[i]);
/*  715 */       if (0 <= this.first)
/*      */       {
/*  717 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  719 */       return;
/*      */     }
/*  721 */     if (this.last == i) {
/*  722 */       this.last = ((int)(this.link[i] >>> 32));
/*  723 */       if (0 <= this.last)
/*      */       {
/*  725 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  727 */       return;
/*      */     }
/*  729 */     long linki = this.link[i];
/*  730 */     int prev = (int)(linki >>> 32);
/*  731 */     int next = (int)linki;
/*  732 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  733 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  744 */     if (this.size == 1) {
/*  745 */       this.first = (this.last = d);
/*      */ 
/*  747 */       this.link[d] = -1L;
/*  748 */       return;
/*      */     }
/*  750 */     if (this.first == s) {
/*  751 */       this.first = d;
/*  752 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  753 */       this.link[d] = this.link[s];
/*  754 */       return;
/*      */     }
/*  756 */     if (this.last == s) {
/*  757 */       this.last = d;
/*  758 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  759 */       this.link[d] = this.link[s];
/*  760 */       return;
/*      */     }
/*  762 */     long links = this.link[s];
/*  763 */     int prev = (int)(links >>> 32);
/*  764 */     int next = (int)links;
/*  765 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  766 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  767 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public double firstDoubleKey()
/*      */   {
/*  774 */     if (this.size == 0) throw new NoSuchElementException();
/*  775 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public double lastDoubleKey()
/*      */   {
/*  782 */     if (this.size == 0) throw new NoSuchElementException();
/*  783 */     return this.key[this.last];
/*      */   }
/*  785 */   public DoubleComparator comparator() { return null; } 
/*  786 */   public Double2IntSortedMap tailMap(double from) { throw new UnsupportedOperationException(); } 
/*  787 */   public Double2IntSortedMap headMap(double to) { throw new UnsupportedOperationException(); } 
/*  788 */   public Double2IntSortedMap subMap(double from, double to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Double2IntSortedMap.FastSortedEntrySet double2IntEntrySet()
/*      */   {
/* 1028 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 1029 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public DoubleSortedSet keySet()
/*      */   {
/* 1084 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1085 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public IntCollection values()
/*      */   {
/* 1105 */     if (this.values == null) this.values = new AbstractIntCollection() {
/*      */         public IntIterator iterator() {
/* 1107 */           return new Double2IntLinkedOpenHashMap.ValueIterator(Double2IntLinkedOpenHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1110 */           return Double2IntLinkedOpenHashMap.this.size;
/*      */         }
/*      */         public boolean contains(int v) {
/* 1113 */           return Double2IntLinkedOpenHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1116 */           Double2IntLinkedOpenHashMap.this.clear();
/*      */         }
/*      */       };
/* 1119 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1133 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1148 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1149 */     if (l >= this.n) return true; try
/*      */     {
/* 1151 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1153 */       return false;
/* 1154 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1175 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1176 */     if (this.n <= l) return true; try
/*      */     {
/* 1178 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1180 */       return false;
/* 1181 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1194 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1196 */     double[] key = this.key;
/* 1197 */     int[] value = this.value;
/* 1198 */     int newMask = newN - 1;
/* 1199 */     double[] newKey = new double[newN];
/* 1200 */     int[] newValue = new int[newN];
/* 1201 */     boolean[] newUsed = new boolean[newN];
/* 1202 */     long[] link = this.link;
/* 1203 */     long[] newLink = new long[newN];
/* 1204 */     this.first = -1;
/* 1205 */     for (int j = this.size; j-- != 0; ) {
/* 1206 */       double k = key[i];
/* 1207 */       int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & newMask;
/* 1208 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1209 */       newUsed[pos] = true;
/* 1210 */       newKey[pos] = k;
/* 1211 */       newValue[pos] = value[i];
/* 1212 */       if (prev != -1) {
/* 1213 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1214 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1215 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1218 */         newPrev = this.first = pos;
/*      */ 
/* 1220 */         newLink[pos] = -1L;
/*      */       }
/* 1222 */       int t = i;
/* 1223 */       i = (int)link[i];
/* 1224 */       prev = t;
/*      */     }
/* 1226 */     this.n = newN;
/* 1227 */     this.mask = newMask;
/* 1228 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1229 */     this.key = newKey;
/* 1230 */     this.value = newValue;
/* 1231 */     this.used = newUsed;
/* 1232 */     this.link = newLink;
/* 1233 */     this.last = newPrev;
/* 1234 */     if (newPrev != -1)
/*      */     {
/* 1236 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Double2IntLinkedOpenHashMap clone()
/*      */   {
/*      */     Double2IntLinkedOpenHashMap c;
/*      */     try
/*      */     {
/* 1249 */       c = (Double2IntLinkedOpenHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1252 */       throw new InternalError();
/*      */     }
/* 1254 */     c.keys = null;
/* 1255 */     c.values = null;
/* 1256 */     c.entries = null;
/* 1257 */     c.key = ((double[])this.key.clone());
/* 1258 */     c.value = ((int[])this.value.clone());
/* 1259 */     c.used = ((boolean[])this.used.clone());
/* 1260 */     c.link = ((long[])this.link.clone());
/* 1261 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1273 */     int h = 0;
/* 1274 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1275 */       while (this.used[i] == 0) i++;
/* 1276 */       t = HashCommon.double2int(this.key[i]);
/* 1277 */       t ^= this.value[i];
/* 1278 */       h += t;
/* 1279 */       i++;
/*      */     }
/* 1281 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1284 */     double[] key = this.key;
/* 1285 */     int[] value = this.value;
/* 1286 */     MapIterator i = new MapIterator(null);
/* 1287 */     s.defaultWriteObject();
/* 1288 */     for (int j = this.size; j-- != 0; ) {
/* 1289 */       int e = i.nextEntry();
/* 1290 */       s.writeDouble(key[e]);
/* 1291 */       s.writeInt(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1296 */     s.defaultReadObject();
/* 1297 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1298 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1299 */     this.mask = (this.n - 1);
/* 1300 */     double[] key = this.key = new double[this.n];
/* 1301 */     int[] value = this.value = new int[this.n];
/* 1302 */     boolean[] used = this.used = new boolean[this.n];
/* 1303 */     long[] link = this.link = new long[this.n];
/* 1304 */     int prev = -1;
/* 1305 */     this.first = (this.last = -1);
/*      */ 
/* 1308 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1309 */       double k = s.readDouble();
/* 1310 */       int v = s.readInt();
/* 1311 */       pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & this.mask;
/* 1312 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1313 */       used[pos] = true;
/* 1314 */       key[pos] = k;
/* 1315 */       value[pos] = v;
/* 1316 */       if (this.first != -1) {
/* 1317 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1318 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1319 */         prev = pos;
/*      */       }
/*      */       else {
/* 1322 */         prev = this.first = pos;
/*      */ 
/* 1324 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1327 */     this.last = prev;
/* 1328 */     if (prev != -1)
/*      */     {
/* 1330 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Double2IntLinkedOpenHashMap.MapIterator
/*      */     implements IntListIterator
/*      */   {
/*      */     public int previousInt()
/*      */     {
/* 1094 */       return Double2IntLinkedOpenHashMap.this.value[previousEntry()]; } 
/* 1095 */     public Integer previous() { return Integer.valueOf(Double2IntLinkedOpenHashMap.this.value[previousEntry()]); } 
/* 1096 */     public void set(Integer ok) { throw new UnsupportedOperationException(); } 
/* 1097 */     public void add(Integer ok) { throw new UnsupportedOperationException(); } 
/* 1098 */     public void set(int v) { throw new UnsupportedOperationException(); } 
/* 1099 */     public void add(int v) { throw new UnsupportedOperationException(); } 
/* 1100 */     public ValueIterator() { super(null); } 
/* 1101 */     public int nextInt() { return Double2IntLinkedOpenHashMap.this.value[nextEntry()]; } 
/* 1102 */     public Integer next() { return Integer.valueOf(Double2IntLinkedOpenHashMap.this.value[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeySet extends AbstractDoubleSortedSet
/*      */   {
/*      */     private KeySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public DoubleListIterator iterator(double from)
/*      */     {
/* 1051 */       return new Double2IntLinkedOpenHashMap.KeyIterator(Double2IntLinkedOpenHashMap.this, from);
/*      */     }
/*      */     public DoubleListIterator iterator() {
/* 1054 */       return new Double2IntLinkedOpenHashMap.KeyIterator(Double2IntLinkedOpenHashMap.this);
/*      */     }
/*      */     public int size() {
/* 1057 */       return Double2IntLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public boolean contains(double k) {
/* 1060 */       return Double2IntLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(double k) {
/* 1063 */       int oldSize = Double2IntLinkedOpenHashMap.this.size;
/* 1064 */       Double2IntLinkedOpenHashMap.this.remove(k);
/* 1065 */       return Double2IntLinkedOpenHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/* 1068 */       Double2IntLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public double firstDouble() {
/* 1071 */       if (Double2IntLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1072 */       return Double2IntLinkedOpenHashMap.this.key[Double2IntLinkedOpenHashMap.this.first];
/*      */     }
/*      */     public double lastDouble() {
/* 1075 */       if (Double2IntLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1076 */       return Double2IntLinkedOpenHashMap.this.key[Double2IntLinkedOpenHashMap.this.last];
/*      */     }
/* 1078 */     public DoubleComparator comparator() { return null; } 
/* 1079 */     public final DoubleSortedSet tailSet(double from) { throw new UnsupportedOperationException(); } 
/* 1080 */     public final DoubleSortedSet headSet(double to) { throw new UnsupportedOperationException(); } 
/* 1081 */     public final DoubleSortedSet subSet(double from, double to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Double2IntLinkedOpenHashMap.MapIterator
/*      */     implements DoubleListIterator
/*      */   {
/*      */     public KeyIterator(double k)
/*      */     {
/* 1038 */       super(k, null); } 
/* 1039 */     public double previousDouble() { return Double2IntLinkedOpenHashMap.this.key[previousEntry()]; } 
/* 1040 */     public void set(double k) { throw new UnsupportedOperationException(); } 
/* 1041 */     public void add(double k) { throw new UnsupportedOperationException(); } 
/* 1042 */     public Double previous() { return Double.valueOf(Double2IntLinkedOpenHashMap.this.key[previousEntry()]); } 
/* 1043 */     public void set(Double ok) { throw new UnsupportedOperationException(); } 
/* 1044 */     public void add(Double ok) { throw new UnsupportedOperationException(); } 
/* 1045 */     public KeyIterator() { super(null); } 
/* 1046 */     public double nextDouble() { return Double2IntLinkedOpenHashMap.this.key[nextEntry()]; } 
/* 1047 */     public Double next() { return Double.valueOf(Double2IntLinkedOpenHashMap.this.key[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Double2IntMap.Entry>
/*      */     implements Double2IntSortedMap.FastSortedEntrySet
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Double2IntMap.Entry> iterator()
/*      */     {
/*  966 */       return new Double2IntLinkedOpenHashMap.EntryIterator(Double2IntLinkedOpenHashMap.this);
/*      */     }
/*  968 */     public Comparator<? super Double2IntMap.Entry> comparator() { return null; } 
/*  969 */     public ObjectSortedSet<Double2IntMap.Entry> subSet(Double2IntMap.Entry fromElement, Double2IntMap.Entry toElement) { throw new UnsupportedOperationException(); } 
/*  970 */     public ObjectSortedSet<Double2IntMap.Entry> headSet(Double2IntMap.Entry toElement) { throw new UnsupportedOperationException(); } 
/*  971 */     public ObjectSortedSet<Double2IntMap.Entry> tailSet(Double2IntMap.Entry fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Double2IntMap.Entry first() {
/*  973 */       if (Double2IntLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  974 */       return new Double2IntLinkedOpenHashMap.MapEntry(Double2IntLinkedOpenHashMap.this, Double2IntLinkedOpenHashMap.this.first);
/*      */     }
/*      */     public Double2IntMap.Entry last() {
/*  977 */       if (Double2IntLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  978 */       return new Double2IntLinkedOpenHashMap.MapEntry(Double2IntLinkedOpenHashMap.this, Double2IntLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  982 */       if (!(o instanceof Map.Entry)) return false;
/*  983 */       Map.Entry e = (Map.Entry)o;
/*  984 */       double k = ((Double)e.getKey()).doubleValue();
/*      */ 
/*  986 */       int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & Double2IntLinkedOpenHashMap.this.mask;
/*      */ 
/*  988 */       while (Double2IntLinkedOpenHashMap.this.used[pos] != 0) {
/*  989 */         if (Double2IntLinkedOpenHashMap.this.key[pos] == k) return Double2IntLinkedOpenHashMap.this.value[pos] == ((Integer)e.getValue()).intValue();
/*  990 */         pos = pos + 1 & Double2IntLinkedOpenHashMap.this.mask;
/*      */       }
/*  992 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  996 */       if (!(o instanceof Map.Entry)) return false;
/*  997 */       Map.Entry e = (Map.Entry)o;
/*  998 */       double k = ((Double)e.getKey()).doubleValue();
/*      */ 
/* 1000 */       int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(k)) & Double2IntLinkedOpenHashMap.this.mask;
/*      */ 
/* 1002 */       while (Double2IntLinkedOpenHashMap.this.used[pos] != 0) {
/* 1003 */         if (Double2IntLinkedOpenHashMap.this.key[pos] == k) {
/* 1004 */           Double2IntLinkedOpenHashMap.this.remove(e.getKey());
/* 1005 */           return true;
/*      */         }
/* 1007 */         pos = pos + 1 & Double2IntLinkedOpenHashMap.this.mask;
/*      */       }
/* 1009 */       return false;
/*      */     }
/*      */     public int size() {
/* 1012 */       return Double2IntLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/* 1015 */       Double2IntLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Double2IntMap.Entry> iterator(Double2IntMap.Entry from) {
/* 1018 */       return new Double2IntLinkedOpenHashMap.EntryIterator(Double2IntLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Double2IntMap.Entry> fastIterator() {
/* 1021 */       return new Double2IntLinkedOpenHashMap.FastEntryIterator(Double2IntLinkedOpenHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Double2IntMap.Entry> fastIterator(Double2IntMap.Entry from) {
/* 1024 */       return new Double2IntLinkedOpenHashMap.FastEntryIterator(Double2IntLinkedOpenHashMap.this, ((Double)from.getKey()).doubleValue());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Double2IntLinkedOpenHashMap.MapIterator
/*      */     implements ObjectListIterator<Double2IntMap.Entry>
/*      */   {
/*  944 */     final AbstractDouble2IntMap.BasicEntry entry = new AbstractDouble2IntMap.BasicEntry(0.0D, 0);
/*      */ 
/*  945 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator(double from) {
/*  947 */       super(from, null);
/*      */     }
/*      */     public AbstractDouble2IntMap.BasicEntry next() {
/*  950 */       int e = nextEntry();
/*  951 */       this.entry.key = Double2IntLinkedOpenHashMap.this.key[e];
/*  952 */       this.entry.value = Double2IntLinkedOpenHashMap.this.value[e];
/*  953 */       return this.entry;
/*      */     }
/*      */     public AbstractDouble2IntMap.BasicEntry previous() {
/*  956 */       int e = previousEntry();
/*  957 */       this.entry.key = Double2IntLinkedOpenHashMap.this.key[e];
/*  958 */       this.entry.value = Double2IntLinkedOpenHashMap.this.value[e];
/*  959 */       return this.entry;
/*      */     }
/*  961 */     public void set(Double2IntMap.Entry ok) { throw new UnsupportedOperationException(); } 
/*  962 */     public void add(Double2IntMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Double2IntLinkedOpenHashMap.MapIterator
/*      */     implements ObjectListIterator<Double2IntMap.Entry>
/*      */   {
/*      */     private Double2IntLinkedOpenHashMap.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  925 */       super(null);
/*      */     }
/*  927 */     public EntryIterator(double from) { super(from, null); }
/*      */ 
/*      */     public Double2IntLinkedOpenHashMap.MapEntry next() {
/*  930 */       return this.entry = new Double2IntLinkedOpenHashMap.MapEntry(Double2IntLinkedOpenHashMap.this, nextEntry());
/*      */     }
/*      */     public Double2IntLinkedOpenHashMap.MapEntry previous() {
/*  933 */       return this.entry = new Double2IntLinkedOpenHashMap.MapEntry(Double2IntLinkedOpenHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  937 */       super.remove();
/*  938 */       Double2IntLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  940 */     public void set(Double2IntMap.Entry ok) { throw new UnsupportedOperationException(); } 
/*  941 */     public void add(Double2IntMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  797 */     int prev = -1;
/*      */ 
/*  799 */     int next = -1;
/*      */ 
/*  801 */     int curr = -1;
/*      */ 
/*  803 */     int index = -1;
/*      */ 
/*  805 */     private MapIterator() { this.next = Double2IntLinkedOpenHashMap.this.first;
/*  806 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator(double from) {
/*  809 */       if (Double2IntLinkedOpenHashMap.this.key[Double2IntLinkedOpenHashMap.this.last] == from) {
/*  810 */         this.prev = Double2IntLinkedOpenHashMap.this.last;
/*  811 */         this.index = Double2IntLinkedOpenHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  815 */         int pos = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(from)) & Double2IntLinkedOpenHashMap.this.mask;
/*      */ 
/*  817 */         while (Double2IntLinkedOpenHashMap.this.used[pos] != 0) {
/*  818 */           if (Double2IntLinkedOpenHashMap.this.key[pos] == from)
/*      */           {
/*  820 */             this.next = ((int)Double2IntLinkedOpenHashMap.this.link[pos]);
/*  821 */             this.prev = pos;
/*  822 */             return;
/*      */           }
/*  824 */           pos = pos + 1 & Double2IntLinkedOpenHashMap.this.mask;
/*      */         }
/*  826 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  829 */     public boolean hasNext() { return this.next != -1; } 
/*  830 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  832 */       if (this.index >= 0) return;
/*  833 */       if (this.prev == -1) {
/*  834 */         this.index = 0;
/*  835 */         return;
/*      */       }
/*  837 */       if (this.next == -1) {
/*  838 */         this.index = Double2IntLinkedOpenHashMap.this.size;
/*  839 */         return;
/*      */       }
/*  841 */       int pos = Double2IntLinkedOpenHashMap.this.first;
/*  842 */       this.index = 1;
/*  843 */       while (pos != this.prev) {
/*  844 */         pos = (int)Double2IntLinkedOpenHashMap.this.link[pos];
/*  845 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  849 */     public int nextIndex() { ensureIndexKnown();
/*  850 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  853 */       ensureIndexKnown();
/*  854 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  857 */       if (!hasNext()) return Double2IntLinkedOpenHashMap.this.size();
/*  858 */       this.curr = this.next;
/*  859 */       this.next = ((int)Double2IntLinkedOpenHashMap.this.link[this.curr]);
/*  860 */       this.prev = this.curr;
/*  861 */       if (this.index >= 0) this.index += 1;
/*  862 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  865 */       if (!hasPrevious()) return -1;
/*  866 */       this.curr = this.prev;
/*  867 */       this.prev = ((int)(Double2IntLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  868 */       this.next = this.curr;
/*  869 */       if (this.index >= 0) this.index -= 1;
/*  870 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  874 */       ensureIndexKnown();
/*  875 */       if (this.curr == -1) throw new IllegalStateException();
/*  876 */       if (this.curr == this.prev)
/*      */       {
/*  879 */         this.index -= 1;
/*  880 */         this.prev = ((int)(Double2IntLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  883 */         this.next = ((int)Double2IntLinkedOpenHashMap.this.link[this.curr]);
/*  884 */       }Double2IntLinkedOpenHashMap.this.size -= 1;
/*      */ 
/*  887 */       if (this.prev == -1) Double2IntLinkedOpenHashMap.this.first = this.next;
/*      */       else
/*  889 */         Double2IntLinkedOpenHashMap.this.link[this.prev] ^= (Double2IntLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  890 */       if (this.next == -1) Double2IntLinkedOpenHashMap.this.last = this.prev;
/*      */       else
/*  892 */         Double2IntLinkedOpenHashMap.this.link[this.next] ^= (Double2IntLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  896 */         pos = (last = pos) + 1 & Double2IntLinkedOpenHashMap.this.mask;
/*  897 */         while (Double2IntLinkedOpenHashMap.this.used[pos] != 0) {
/*  898 */           int slot = (int)HashCommon.murmurHash3(Double.doubleToRawLongBits(Double2IntLinkedOpenHashMap.this.key[pos])) & Double2IntLinkedOpenHashMap.this.mask;
/*  899 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  900 */           pos = pos + 1 & Double2IntLinkedOpenHashMap.this.mask;
/*      */         }
/*  902 */         if (Double2IntLinkedOpenHashMap.this.used[pos] == 0) break;
/*  903 */         Double2IntLinkedOpenHashMap.this.key[last] = Double2IntLinkedOpenHashMap.this.key[pos];
/*  904 */         Double2IntLinkedOpenHashMap.this.value[last] = Double2IntLinkedOpenHashMap.this.value[pos];
/*  905 */         if (this.next == pos) this.next = last;
/*  906 */         if (this.prev == pos) this.prev = last;
/*  907 */         Double2IntLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */       }
/*  909 */       Double2IntLinkedOpenHashMap.this.used[last] = false;
/*  910 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  913 */       int i = n;
/*  914 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  915 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  918 */       int i = n;
/*  919 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  920 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Double2IntMap.Entry, Map.Entry<Double, Integer>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  666 */       this.index = index;
/*      */     }
/*      */     public Double getKey() {
/*  669 */       return Double.valueOf(Double2IntLinkedOpenHashMap.this.key[this.index]);
/*      */     }
/*      */     public double getDoubleKey() {
/*  672 */       return Double2IntLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     public Integer getValue() {
/*  675 */       return Integer.valueOf(Double2IntLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */     public int getIntValue() {
/*  678 */       return Double2IntLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public int setValue(int v) {
/*  681 */       int oldValue = Double2IntLinkedOpenHashMap.this.value[this.index];
/*  682 */       Double2IntLinkedOpenHashMap.this.value[this.index] = v;
/*  683 */       return oldValue;
/*      */     }
/*      */     public Integer setValue(Integer v) {
/*  686 */       return Integer.valueOf(setValue(v.intValue()));
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  690 */       if (!(o instanceof Map.Entry)) return false;
/*  691 */       Map.Entry e = (Map.Entry)o;
/*  692 */       return (Double2IntLinkedOpenHashMap.this.key[this.index] == ((Double)e.getKey()).doubleValue()) && (Double2IntLinkedOpenHashMap.this.value[this.index] == ((Integer)e.getValue()).intValue());
/*      */     }
/*      */     public int hashCode() {
/*  695 */       return HashCommon.double2int(Double2IntLinkedOpenHashMap.this.key[this.index]) ^ Double2IntLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public String toString() {
/*  698 */       return Double2IntLinkedOpenHashMap.this.key[this.index] + "=>" + Double2IntLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2IntLinkedOpenHashMap
 * JD-Core Version:    0.6.2
 */