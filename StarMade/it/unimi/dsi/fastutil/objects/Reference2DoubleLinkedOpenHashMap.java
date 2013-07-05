/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleCollection;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleIterator;
/*      */ import it.unimi.dsi.fastutil.doubles.DoubleListIterator;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Comparator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class Reference2DoubleLinkedOpenHashMap<K> extends AbstractReference2DoubleSortedMap<K>
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   public static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient K[] key;
/*      */   protected transient double[] value;
/*      */   protected transient boolean[] used;
/*      */   protected final float f;
/*      */   protected transient int n;
/*      */   protected transient int maxFill;
/*      */   protected transient int mask;
/*      */   protected int size;
/*      */   protected volatile transient Reference2DoubleSortedMap.FastSortedEntrySet<K> entries;
/*      */   protected volatile transient ReferenceSortedSet<K> keys;
/*      */   protected volatile transient DoubleCollection values;
/*  130 */   protected transient int first = -1;
/*      */ 
/*  132 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(int expected, float f)
/*      */   {
/*  151 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153 */     this.f = f;
/*  154 */     this.n = HashCommon.arraySize(expected, f);
/*  155 */     this.mask = (this.n - 1);
/*  156 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  157 */     this.key = ((Object[])new Object[this.n]);
/*  158 */     this.value = new double[this.n];
/*  159 */     this.used = new boolean[this.n];
/*  160 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(int expected)
/*      */   {
/*  167 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap()
/*      */   {
/*  173 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(Map<? extends K, ? extends Double> m, float f)
/*      */   {
/*  181 */     this(m.size(), f);
/*  182 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(Map<? extends K, ? extends Double> m)
/*      */   {
/*  189 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(Reference2DoubleMap<K> m, float f)
/*      */   {
/*  197 */     this(m.size(), f);
/*  198 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(Reference2DoubleMap<K> m)
/*      */   {
/*  205 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(K[] k, double[] v, float f)
/*      */   {
/*  215 */     this(k.length, f);
/*  216 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap(K[] k, double[] v)
/*      */   {
/*  226 */     this(k, v, 0.75F);
/*      */   }
/*      */ 
/*      */   public double put(K k, double v)
/*      */   {
/*  234 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  236 */     while (this.used[pos] != 0) {
/*  237 */       if (this.key[pos] == k) {
/*  238 */         double oldValue = this.value[pos];
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
/*      */   public Double put(K ok, Double ov) {
/*  262 */     double v = ov.doubleValue();
/*  263 */     Object k = ok;
/*      */ 
/*  265 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  267 */     while (this.used[pos] != 0) {
/*  268 */       if (this.key[pos] == k) {
/*  269 */         Double oldValue = Double.valueOf(this.value[pos]);
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
/*      */   public double add(K k, double incr)
/*      */   {
/*  305 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  307 */     while (this.used[pos] != 0) {
/*  308 */       if (this.key[pos] == k) {
/*  309 */         double oldValue = this.value[pos];
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
/*  344 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/*  345 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  346 */         pos = pos + 1 & this.mask;
/*      */       }
/*  348 */       if (this.used[pos] == 0) break;
/*  349 */       this.key[last] = this.key[pos];
/*  350 */       this.value[last] = this.value[pos];
/*  351 */       fixPointers(pos, last);
/*      */     }
/*  353 */     this.used[last] = false;
/*  354 */     this.key[last] = null;
/*  355 */     return last;
/*      */   }
/*      */ 
/*      */   public double removeDouble(Object k)
/*      */   {
/*  360 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  362 */     while (this.used[pos] != 0) {
/*  363 */       if (this.key[pos] == k) {
/*  364 */         this.size -= 1;
/*  365 */         fixPointers(pos);
/*  366 */         double v = this.value[pos];
/*  367 */         shiftKeys(pos);
/*  368 */         return v;
/*      */       }
/*  370 */       pos = pos + 1 & this.mask;
/*      */     }
/*  372 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public Double remove(Object ok) {
/*  376 */     Object k = ok;
/*      */ 
/*  378 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  380 */     while (this.used[pos] != 0) {
/*  381 */       if (this.key[pos] == k) {
/*  382 */         this.size -= 1;
/*  383 */         fixPointers(pos);
/*  384 */         double v = this.value[pos];
/*  385 */         shiftKeys(pos);
/*  386 */         return Double.valueOf(v);
/*      */       }
/*  388 */       pos = pos + 1 & this.mask;
/*      */     }
/*  390 */     return null;
/*      */   }
/*      */ 
/*      */   public double removeFirstDouble()
/*      */   {
/*  397 */     if (this.size == 0) throw new NoSuchElementException();
/*  398 */     this.size -= 1;
/*  399 */     int pos = this.first;
/*      */ 
/*  401 */     this.first = ((int)this.link[pos]);
/*  402 */     if (0 <= this.first)
/*      */     {
/*  404 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  406 */     double v = this.value[pos];
/*  407 */     shiftKeys(pos);
/*  408 */     return v;
/*      */   }
/*      */ 
/*      */   public double removeLastDouble()
/*      */   {
/*  415 */     if (this.size == 0) throw new NoSuchElementException();
/*  416 */     this.size -= 1;
/*  417 */     int pos = this.last;
/*      */ 
/*  419 */     this.last = ((int)(this.link[pos] >>> 32));
/*  420 */     if (0 <= this.last)
/*      */     {
/*  422 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  424 */     double v = this.value[pos];
/*  425 */     shiftKeys(pos);
/*  426 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  429 */     if ((this.size == 1) || (this.first == i)) return;
/*  430 */     if (this.last == i) {
/*  431 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  433 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  436 */       long linki = this.link[i];
/*  437 */       int prev = (int)(linki >>> 32);
/*  438 */       int next = (int)linki;
/*  439 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  440 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  442 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  443 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  444 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  447 */     if ((this.size == 1) || (this.last == i)) return;
/*  448 */     if (this.first == i) {
/*  449 */       this.first = ((int)this.link[i]);
/*      */ 
/*  451 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  454 */       long linki = this.link[i];
/*  455 */       int prev = (int)(linki >>> 32);
/*  456 */       int next = (int)linki;
/*  457 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  458 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  460 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  461 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  462 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public double getAndMoveToFirst(K k)
/*      */   {
/*  470 */     Object[] key = this.key;
/*  471 */     boolean[] used = this.used;
/*  472 */     int mask = this.mask;
/*      */ 
/*  474 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  476 */     while (used[pos] != 0) {
/*  477 */       if (k == key[pos]) {
/*  478 */         moveIndexToFirst(pos);
/*  479 */         return this.value[pos];
/*      */       }
/*  481 */       pos = pos + 1 & mask;
/*      */     }
/*  483 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public double getAndMoveToLast(K k)
/*      */   {
/*  491 */     Object[] key = this.key;
/*  492 */     boolean[] used = this.used;
/*  493 */     int mask = this.mask;
/*      */ 
/*  495 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  497 */     while (used[pos] != 0) {
/*  498 */       if (k == key[pos]) {
/*  499 */         moveIndexToLast(pos);
/*  500 */         return this.value[pos];
/*      */       }
/*  502 */       pos = pos + 1 & mask;
/*      */     }
/*  504 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public double putAndMoveToFirst(K k, double v)
/*      */   {
/*  513 */     Object[] key = this.key;
/*  514 */     boolean[] used = this.used;
/*  515 */     int mask = this.mask;
/*      */ 
/*  517 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  519 */     while (used[pos] != 0) {
/*  520 */       if (k == key[pos]) {
/*  521 */         double oldValue = this.value[pos];
/*  522 */         this.value[pos] = v;
/*  523 */         moveIndexToFirst(pos);
/*  524 */         return oldValue;
/*      */       }
/*  526 */       pos = pos + 1 & mask;
/*      */     }
/*  528 */     used[pos] = true;
/*  529 */     key[pos] = k;
/*  530 */     this.value[pos] = v;
/*  531 */     if (this.size == 0) {
/*  532 */       this.first = (this.last = pos);
/*      */ 
/*  534 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  537 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  538 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  539 */       this.first = pos;
/*      */     }
/*  541 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  543 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public double putAndMoveToLast(K k, double v)
/*      */   {
/*  552 */     Object[] key = this.key;
/*  553 */     boolean[] used = this.used;
/*  554 */     int mask = this.mask;
/*      */ 
/*  556 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  558 */     while (used[pos] != 0) {
/*  559 */       if (k == key[pos]) {
/*  560 */         double oldValue = this.value[pos];
/*  561 */         this.value[pos] = v;
/*  562 */         moveIndexToLast(pos);
/*  563 */         return oldValue;
/*      */       }
/*  565 */       pos = pos + 1 & mask;
/*      */     }
/*  567 */     used[pos] = true;
/*  568 */     key[pos] = k;
/*  569 */     this.value[pos] = v;
/*  570 */     if (this.size == 0) {
/*  571 */       this.first = (this.last = pos);
/*      */ 
/*  573 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  576 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  577 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  578 */       this.last = pos;
/*      */     }
/*  580 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  582 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public double getDouble(Object k)
/*      */   {
/*  587 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  589 */     while (this.used[pos] != 0) {
/*  590 */       if (this.key[pos] == k) return this.value[pos];
/*  591 */       pos = pos + 1 & this.mask;
/*      */     }
/*  593 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  598 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  600 */     while (this.used[pos] != 0) {
/*  601 */       if (this.key[pos] == k) return true;
/*  602 */       pos = pos + 1 & this.mask;
/*      */     }
/*  604 */     return false;
/*      */   }
/*      */   public boolean containsValue(double v) {
/*  607 */     double[] value = this.value;
/*  608 */     boolean[] used = this.used;
/*  609 */     for (int i = this.n; i-- != 0; return true) label17: if ((used[i] == 0) || (value[i] != v))
/*      */         break label17; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  619 */     if (this.size == 0) return;
/*  620 */     this.size = 0;
/*  621 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  623 */     ObjectArrays.fill(this.key, null);
/*  624 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  627 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  630 */     return this.size == 0;
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
/*  647 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  697 */     if (this.size == 0) {
/*  698 */       this.first = (this.last = -1);
/*  699 */       return;
/*      */     }
/*  701 */     if (this.first == i) {
/*  702 */       this.first = ((int)this.link[i]);
/*  703 */       if (0 <= this.first)
/*      */       {
/*  705 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  707 */       return;
/*      */     }
/*  709 */     if (this.last == i) {
/*  710 */       this.last = ((int)(this.link[i] >>> 32));
/*  711 */       if (0 <= this.last)
/*      */       {
/*  713 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  715 */       return;
/*      */     }
/*  717 */     long linki = this.link[i];
/*  718 */     int prev = (int)(linki >>> 32);
/*  719 */     int next = (int)linki;
/*  720 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  721 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  732 */     if (this.size == 1) {
/*  733 */       this.first = (this.last = d);
/*      */ 
/*  735 */       this.link[d] = -1L;
/*  736 */       return;
/*      */     }
/*  738 */     if (this.first == s) {
/*  739 */       this.first = d;
/*  740 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  741 */       this.link[d] = this.link[s];
/*  742 */       return;
/*      */     }
/*  744 */     if (this.last == s) {
/*  745 */       this.last = d;
/*  746 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  747 */       this.link[d] = this.link[s];
/*  748 */       return;
/*      */     }
/*  750 */     long links = this.link[s];
/*  751 */     int prev = (int)(links >>> 32);
/*  752 */     int next = (int)links;
/*  753 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  754 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  755 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public K firstKey()
/*      */   {
/*  762 */     if (this.size == 0) throw new NoSuchElementException();
/*  763 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public K lastKey()
/*      */   {
/*  770 */     if (this.size == 0) throw new NoSuchElementException();
/*  771 */     return this.key[this.last];
/*      */   }
/*  773 */   public Comparator<? super K> comparator() { return null; } 
/*  774 */   public Reference2DoubleSortedMap<K> tailMap(K from) { throw new UnsupportedOperationException(); } 
/*  775 */   public Reference2DoubleSortedMap<K> headMap(K to) { throw new UnsupportedOperationException(); } 
/*  776 */   public Reference2DoubleSortedMap<K> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Reference2DoubleSortedMap.FastSortedEntrySet<K> reference2DoubleEntrySet()
/*      */   {
/* 1017 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 1018 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ReferenceSortedSet<K> keySet()
/*      */   {
/* 1069 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1070 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public DoubleCollection values()
/*      */   {
/* 1090 */     if (this.values == null) this.values = new AbstractDoubleCollection() {
/*      */         public DoubleIterator iterator() {
/* 1092 */           return new Reference2DoubleLinkedOpenHashMap.ValueIterator(Reference2DoubleLinkedOpenHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1095 */           return Reference2DoubleLinkedOpenHashMap.this.size;
/*      */         }
/*      */         public boolean contains(double v) {
/* 1098 */           return Reference2DoubleLinkedOpenHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1101 */           Reference2DoubleLinkedOpenHashMap.this.clear();
/*      */         }
/*      */       };
/* 1104 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1118 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1133 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1134 */     if (l >= this.n) return true; try
/*      */     {
/* 1136 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1138 */       return false;
/* 1139 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1160 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1161 */     if (this.n <= l) return true; try
/*      */     {
/* 1163 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1165 */       return false;
/* 1166 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1179 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1181 */     Object[] key = this.key;
/* 1182 */     double[] value = this.value;
/* 1183 */     int newMask = newN - 1;
/* 1184 */     Object[] newKey = (Object[])new Object[newN];
/* 1185 */     double[] newValue = new double[newN];
/* 1186 */     boolean[] newUsed = new boolean[newN];
/* 1187 */     long[] link = this.link;
/* 1188 */     long[] newLink = new long[newN];
/* 1189 */     this.first = -1;
/* 1190 */     for (int j = this.size; j-- != 0; ) {
/* 1191 */       Object k = key[i];
/* 1192 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 1193 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1194 */       newUsed[pos] = true;
/* 1195 */       newKey[pos] = k;
/* 1196 */       newValue[pos] = value[i];
/* 1197 */       if (prev != -1) {
/* 1198 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1199 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1200 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1203 */         newPrev = this.first = pos;
/*      */ 
/* 1205 */         newLink[pos] = -1L;
/*      */       }
/* 1207 */       int t = i;
/* 1208 */       i = (int)link[i];
/* 1209 */       prev = t;
/*      */     }
/* 1211 */     this.n = newN;
/* 1212 */     this.mask = newMask;
/* 1213 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1214 */     this.key = newKey;
/* 1215 */     this.value = newValue;
/* 1216 */     this.used = newUsed;
/* 1217 */     this.link = newLink;
/* 1218 */     this.last = newPrev;
/* 1219 */     if (newPrev != -1)
/*      */     {
/* 1221 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Reference2DoubleLinkedOpenHashMap<K> clone()
/*      */   {
/*      */     Reference2DoubleLinkedOpenHashMap c;
/*      */     try
/*      */     {
/* 1234 */       c = (Reference2DoubleLinkedOpenHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1237 */       throw new InternalError();
/*      */     }
/* 1239 */     c.keys = null;
/* 1240 */     c.values = null;
/* 1241 */     c.entries = null;
/* 1242 */     c.key = ((Object[])this.key.clone());
/* 1243 */     c.value = ((double[])this.value.clone());
/* 1244 */     c.used = ((boolean[])this.used.clone());
/* 1245 */     c.link = ((long[])this.link.clone());
/* 1246 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1258 */     int h = 0;
/* 1259 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1260 */       while (this.used[i] == 0) i++;
/* 1261 */       if (this != this.key[i])
/* 1262 */         t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 1263 */       t ^= HashCommon.double2int(this.value[i]);
/* 1264 */       h += t;
/* 1265 */       i++;
/*      */     }
/* 1267 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1270 */     Object[] key = this.key;
/* 1271 */     double[] value = this.value;
/* 1272 */     MapIterator i = new MapIterator(null);
/* 1273 */     s.defaultWriteObject();
/* 1274 */     for (int j = this.size; j-- != 0; ) {
/* 1275 */       int e = i.nextEntry();
/* 1276 */       s.writeObject(key[e]);
/* 1277 */       s.writeDouble(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1282 */     s.defaultReadObject();
/* 1283 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1284 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1285 */     this.mask = (this.n - 1);
/* 1286 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 1287 */     double[] value = this.value = new double[this.n];
/* 1288 */     boolean[] used = this.used = new boolean[this.n];
/* 1289 */     long[] link = this.link = new long[this.n];
/* 1290 */     int prev = -1;
/* 1291 */     this.first = (this.last = -1);
/*      */ 
/* 1294 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1295 */       Object k = s.readObject();
/* 1296 */       double v = s.readDouble();
/* 1297 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 1298 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1299 */       used[pos] = true;
/* 1300 */       key[pos] = k;
/* 1301 */       value[pos] = v;
/* 1302 */       if (this.first != -1) {
/* 1303 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1304 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1305 */         prev = pos;
/*      */       }
/*      */       else {
/* 1308 */         prev = this.first = pos;
/*      */ 
/* 1310 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1313 */     this.last = prev;
/* 1314 */     if (prev != -1)
/*      */     {
/* 1316 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Reference2DoubleLinkedOpenHashMap.MapIterator
/*      */     implements DoubleListIterator
/*      */   {
/*      */     public double previousDouble()
/*      */     {
/* 1079 */       return Reference2DoubleLinkedOpenHashMap.this.value[previousEntry()]; } 
/* 1080 */     public Double previous() { return Double.valueOf(Reference2DoubleLinkedOpenHashMap.this.value[previousEntry()]); } 
/* 1081 */     public void set(Double ok) { throw new UnsupportedOperationException(); } 
/* 1082 */     public void add(Double ok) { throw new UnsupportedOperationException(); } 
/* 1083 */     public void set(double v) { throw new UnsupportedOperationException(); } 
/* 1084 */     public void add(double v) { throw new UnsupportedOperationException(); } 
/* 1085 */     public ValueIterator() { super(null); } 
/* 1086 */     public double nextDouble() { return Reference2DoubleLinkedOpenHashMap.this.value[nextEntry()]; } 
/* 1087 */     public Double next() { return Double.valueOf(Reference2DoubleLinkedOpenHashMap.this.value[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeySet extends AbstractReferenceSortedSet<K>
/*      */   {
/*      */     private KeySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectListIterator<K> iterator(K from)
/*      */     {
/* 1036 */       return new Reference2DoubleLinkedOpenHashMap.KeyIterator(Reference2DoubleLinkedOpenHashMap.this, from);
/*      */     }
/*      */     public ObjectListIterator<K> iterator() {
/* 1039 */       return new Reference2DoubleLinkedOpenHashMap.KeyIterator(Reference2DoubleLinkedOpenHashMap.this);
/*      */     }
/*      */     public int size() {
/* 1042 */       return Reference2DoubleLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public boolean contains(Object k) {
/* 1045 */       return Reference2DoubleLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(Object k) {
/* 1048 */       int oldSize = Reference2DoubleLinkedOpenHashMap.this.size;
/* 1049 */       Reference2DoubleLinkedOpenHashMap.this.remove(k);
/* 1050 */       return Reference2DoubleLinkedOpenHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/* 1053 */       Reference2DoubleLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public K first() {
/* 1056 */       if (Reference2DoubleLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1057 */       return Reference2DoubleLinkedOpenHashMap.this.key[Reference2DoubleLinkedOpenHashMap.this.first];
/*      */     }
/*      */     public K last() {
/* 1060 */       if (Reference2DoubleLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1061 */       return Reference2DoubleLinkedOpenHashMap.this.key[Reference2DoubleLinkedOpenHashMap.this.last];
/*      */     }
/* 1063 */     public Comparator<? super K> comparator() { return null; } 
/* 1064 */     public final ReferenceSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/* 1065 */     public final ReferenceSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/* 1066 */     public final ReferenceSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Reference2DoubleLinkedOpenHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1027 */       super(k, null); } 
/* 1028 */     public K previous() { return Reference2DoubleLinkedOpenHashMap.this.key[previousEntry()]; } 
/* 1029 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1030 */     public void add(K k) { throw new UnsupportedOperationException(); } 
/* 1031 */     public KeyIterator() { super(null); } 
/* 1032 */     public K next() { return Reference2DoubleLinkedOpenHashMap.this.key[nextEntry()]; }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Reference2DoubleMap.Entry<K>>
/*      */     implements Reference2DoubleSortedMap.FastSortedEntrySet<K>
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Reference2DoubleMap.Entry<K>> iterator()
/*      */     {
/*  955 */       return new Reference2DoubleLinkedOpenHashMap.EntryIterator(Reference2DoubleLinkedOpenHashMap.this);
/*      */     }
/*  957 */     public Comparator<? super Reference2DoubleMap.Entry<K>> comparator() { return null; } 
/*  958 */     public ObjectSortedSet<Reference2DoubleMap.Entry<K>> subSet(Reference2DoubleMap.Entry<K> fromElement, Reference2DoubleMap.Entry<K> toElement) { throw new UnsupportedOperationException(); } 
/*  959 */     public ObjectSortedSet<Reference2DoubleMap.Entry<K>> headSet(Reference2DoubleMap.Entry<K> toElement) { throw new UnsupportedOperationException(); } 
/*  960 */     public ObjectSortedSet<Reference2DoubleMap.Entry<K>> tailSet(Reference2DoubleMap.Entry<K> fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Reference2DoubleMap.Entry<K> first() {
/*  962 */       if (Reference2DoubleLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  963 */       return new Reference2DoubleLinkedOpenHashMap.MapEntry(Reference2DoubleLinkedOpenHashMap.this, Reference2DoubleLinkedOpenHashMap.this.first);
/*      */     }
/*      */     public Reference2DoubleMap.Entry<K> last() {
/*  966 */       if (Reference2DoubleLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  967 */       return new Reference2DoubleLinkedOpenHashMap.MapEntry(Reference2DoubleLinkedOpenHashMap.this, Reference2DoubleLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  971 */       if (!(o instanceof Map.Entry)) return false;
/*  972 */       Map.Entry e = (Map.Entry)o;
/*  973 */       Object k = e.getKey();
/*      */ 
/*  975 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */ 
/*  977 */       while (Reference2DoubleLinkedOpenHashMap.this.used[pos] != 0) {
/*  978 */         if (Reference2DoubleLinkedOpenHashMap.this.key[pos] == k) return Reference2DoubleLinkedOpenHashMap.this.value[pos] == ((Double)e.getValue()).doubleValue();
/*  979 */         pos = pos + 1 & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */       }
/*  981 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  985 */       if (!(o instanceof Map.Entry)) return false;
/*  986 */       Map.Entry e = (Map.Entry)o;
/*  987 */       Object k = e.getKey();
/*      */ 
/*  989 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */ 
/*  991 */       while (Reference2DoubleLinkedOpenHashMap.this.used[pos] != 0) {
/*  992 */         if (Reference2DoubleLinkedOpenHashMap.this.key[pos] == k) {
/*  993 */           Reference2DoubleLinkedOpenHashMap.this.remove(e.getKey());
/*  994 */           return true;
/*      */         }
/*  996 */         pos = pos + 1 & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */       }
/*  998 */       return false;
/*      */     }
/*      */     public int size() {
/* 1001 */       return Reference2DoubleLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/* 1004 */       Reference2DoubleLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Reference2DoubleMap.Entry<K>> iterator(Reference2DoubleMap.Entry<K> from) {
/* 1007 */       return new Reference2DoubleLinkedOpenHashMap.EntryIterator(Reference2DoubleLinkedOpenHashMap.this, from.getKey());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Reference2DoubleMap.Entry<K>> fastIterator() {
/* 1010 */       return new Reference2DoubleLinkedOpenHashMap.FastEntryIterator(Reference2DoubleLinkedOpenHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Reference2DoubleMap.Entry<K>> fastIterator(Reference2DoubleMap.Entry<K> from) {
/* 1013 */       return new Reference2DoubleLinkedOpenHashMap.FastEntryIterator(Reference2DoubleLinkedOpenHashMap.this, from.getKey());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Reference2DoubleLinkedOpenHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<Reference2DoubleMap.Entry<K>>
/*      */   {
/*  933 */     final AbstractReference2DoubleMap.BasicEntry<K> entry = new AbstractReference2DoubleMap.BasicEntry(null, 0.0D);
/*      */ 
/*  934 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator() {
/*  936 */       super(from, null);
/*      */     }
/*      */     public AbstractReference2DoubleMap.BasicEntry<K> next() {
/*  939 */       int e = nextEntry();
/*  940 */       this.entry.key = Reference2DoubleLinkedOpenHashMap.this.key[e];
/*  941 */       this.entry.value = Reference2DoubleLinkedOpenHashMap.this.value[e];
/*  942 */       return this.entry;
/*      */     }
/*      */     public AbstractReference2DoubleMap.BasicEntry<K> previous() {
/*  945 */       int e = previousEntry();
/*  946 */       this.entry.key = Reference2DoubleLinkedOpenHashMap.this.key[e];
/*  947 */       this.entry.value = Reference2DoubleLinkedOpenHashMap.this.value[e];
/*  948 */       return this.entry;
/*      */     }
/*  950 */     public void set(Reference2DoubleMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  951 */     public void add(Reference2DoubleMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Reference2DoubleLinkedOpenHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<Reference2DoubleMap.Entry<K>>
/*      */   {
/*      */     private Reference2DoubleLinkedOpenHashMap<K>.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  914 */       super(null);
/*      */     }
/*  916 */     public EntryIterator() { super(from, null); }
/*      */ 
/*      */     public Reference2DoubleLinkedOpenHashMap<K>.MapEntry next() {
/*  919 */       return this.entry = new Reference2DoubleLinkedOpenHashMap.MapEntry(Reference2DoubleLinkedOpenHashMap.this, nextEntry());
/*      */     }
/*      */     public Reference2DoubleLinkedOpenHashMap<K>.MapEntry previous() {
/*  922 */       return this.entry = new Reference2DoubleLinkedOpenHashMap.MapEntry(Reference2DoubleLinkedOpenHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  926 */       super.remove();
/*  927 */       Reference2DoubleLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  929 */     public void set(Reference2DoubleMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  930 */     public void add(Reference2DoubleMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  785 */     int prev = -1;
/*      */ 
/*  787 */     int next = -1;
/*      */ 
/*  789 */     int curr = -1;
/*      */ 
/*  791 */     int index = -1;
/*      */ 
/*  793 */     private MapIterator() { this.next = Reference2DoubleLinkedOpenHashMap.this.first;
/*  794 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator() {
/*  797 */       if (Reference2DoubleLinkedOpenHashMap.this.key[Reference2DoubleLinkedOpenHashMap.this.last] == from) {
/*  798 */         this.prev = Reference2DoubleLinkedOpenHashMap.this.last;
/*  799 */         this.index = Reference2DoubleLinkedOpenHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  803 */         int pos = (from == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(from))) & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */ 
/*  805 */         while (Reference2DoubleLinkedOpenHashMap.this.used[pos] != 0) {
/*  806 */           if (Reference2DoubleLinkedOpenHashMap.this.key[pos] == from)
/*      */           {
/*  808 */             this.next = ((int)Reference2DoubleLinkedOpenHashMap.this.link[pos]);
/*  809 */             this.prev = pos;
/*  810 */             return;
/*      */           }
/*  812 */           pos = pos + 1 & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */         }
/*  814 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  817 */     public boolean hasNext() { return this.next != -1; } 
/*  818 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  820 */       if (this.index >= 0) return;
/*  821 */       if (this.prev == -1) {
/*  822 */         this.index = 0;
/*  823 */         return;
/*      */       }
/*  825 */       if (this.next == -1) {
/*  826 */         this.index = Reference2DoubleLinkedOpenHashMap.this.size;
/*  827 */         return;
/*      */       }
/*  829 */       int pos = Reference2DoubleLinkedOpenHashMap.this.first;
/*  830 */       this.index = 1;
/*  831 */       while (pos != this.prev) {
/*  832 */         pos = (int)Reference2DoubleLinkedOpenHashMap.this.link[pos];
/*  833 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  837 */     public int nextIndex() { ensureIndexKnown();
/*  838 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  841 */       ensureIndexKnown();
/*  842 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  845 */       if (!hasNext()) return Reference2DoubleLinkedOpenHashMap.this.size();
/*  846 */       this.curr = this.next;
/*  847 */       this.next = ((int)Reference2DoubleLinkedOpenHashMap.this.link[this.curr]);
/*  848 */       this.prev = this.curr;
/*  849 */       if (this.index >= 0) this.index += 1;
/*  850 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  853 */       if (!hasPrevious()) return -1;
/*  854 */       this.curr = this.prev;
/*  855 */       this.prev = ((int)(Reference2DoubleLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  856 */       this.next = this.curr;
/*  857 */       if (this.index >= 0) this.index -= 1;
/*  858 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  862 */       ensureIndexKnown();
/*  863 */       if (this.curr == -1) throw new IllegalStateException();
/*  864 */       if (this.curr == this.prev)
/*      */       {
/*  867 */         this.index -= 1;
/*  868 */         this.prev = ((int)(Reference2DoubleLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  871 */         this.next = ((int)Reference2DoubleLinkedOpenHashMap.this.link[this.curr]);
/*  872 */       }Reference2DoubleLinkedOpenHashMap.this.size -= 1;
/*      */ 
/*  875 */       if (this.prev == -1) Reference2DoubleLinkedOpenHashMap.this.first = this.next;
/*      */       else
/*  877 */         Reference2DoubleLinkedOpenHashMap.this.link[this.prev] ^= (Reference2DoubleLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  878 */       if (this.next == -1) Reference2DoubleLinkedOpenHashMap.this.last = this.prev;
/*      */       else
/*  880 */         Reference2DoubleLinkedOpenHashMap.this.link[this.next] ^= (Reference2DoubleLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  884 */         pos = (last = pos) + 1 & Reference2DoubleLinkedOpenHashMap.this.mask;
/*  885 */         while (Reference2DoubleLinkedOpenHashMap.this.used[pos] != 0) {
/*  886 */           int slot = (Reference2DoubleLinkedOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2DoubleLinkedOpenHashMap.this.key[pos]))) & Reference2DoubleLinkedOpenHashMap.this.mask;
/*  887 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  888 */           pos = pos + 1 & Reference2DoubleLinkedOpenHashMap.this.mask;
/*      */         }
/*  890 */         if (Reference2DoubleLinkedOpenHashMap.this.used[pos] == 0) break;
/*  891 */         Reference2DoubleLinkedOpenHashMap.this.key[last] = Reference2DoubleLinkedOpenHashMap.this.key[pos];
/*  892 */         Reference2DoubleLinkedOpenHashMap.this.value[last] = Reference2DoubleLinkedOpenHashMap.this.value[pos];
/*  893 */         if (this.next == pos) this.next = last;
/*  894 */         if (this.prev == pos) this.prev = last;
/*  895 */         Reference2DoubleLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */       }
/*  897 */       Reference2DoubleLinkedOpenHashMap.this.used[last] = false;
/*  898 */       Reference2DoubleLinkedOpenHashMap.this.key[last] = null;
/*  899 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  902 */       int i = n;
/*  903 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  904 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  907 */       int i = n;
/*  908 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  909 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Reference2DoubleMap.Entry<K>, Map.Entry<K, Double>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  657 */       this.index = index;
/*      */     }
/*      */     public K getKey() {
/*  660 */       return Reference2DoubleLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     public Double getValue() {
/*  663 */       return Double.valueOf(Reference2DoubleLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */     public double getDoubleValue() {
/*  666 */       return Reference2DoubleLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public double setValue(double v) {
/*  669 */       double oldValue = Reference2DoubleLinkedOpenHashMap.this.value[this.index];
/*  670 */       Reference2DoubleLinkedOpenHashMap.this.value[this.index] = v;
/*  671 */       return oldValue;
/*      */     }
/*      */     public Double setValue(Double v) {
/*  674 */       return Double.valueOf(setValue(v.doubleValue()));
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  678 */       if (!(o instanceof Map.Entry)) return false;
/*  679 */       Map.Entry e = (Map.Entry)o;
/*  680 */       return (Reference2DoubleLinkedOpenHashMap.this.key[this.index] == e.getKey()) && (Reference2DoubleLinkedOpenHashMap.this.value[this.index] == ((Double)e.getValue()).doubleValue());
/*      */     }
/*      */     public int hashCode() {
/*  683 */       return (Reference2DoubleLinkedOpenHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2DoubleLinkedOpenHashMap.this.key[this.index])) ^ HashCommon.double2int(Reference2DoubleLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */     public String toString() {
/*  686 */       return Reference2DoubleLinkedOpenHashMap.this.key[this.index] + "=>" + Reference2DoubleLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2DoubleLinkedOpenHashMap
 * JD-Core Version:    0.6.2
 */