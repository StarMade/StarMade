/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Comparator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class Object2ReferenceLinkedOpenHashMap<K, V> extends AbstractObject2ReferenceSortedMap<K, V>
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   public static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient K[] key;
/*      */   protected transient V[] value;
/*      */   protected transient boolean[] used;
/*      */   protected final float f;
/*      */   protected transient int n;
/*      */   protected transient int maxFill;
/*      */   protected transient int mask;
/*      */   protected int size;
/*      */   protected volatile transient Object2ReferenceSortedMap.FastSortedEntrySet<K, V> entries;
/*      */   protected volatile transient ObjectSortedSet<K> keys;
/*      */   protected volatile transient ReferenceCollection<V> values;
/*  116 */   protected transient int first = -1;
/*      */ 
/*  118 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(int expected, float f)
/*      */   {
/*  137 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  138 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  139 */     this.f = f;
/*  140 */     this.n = HashCommon.arraySize(expected, f);
/*  141 */     this.mask = (this.n - 1);
/*  142 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  143 */     this.key = ((Object[])new Object[this.n]);
/*  144 */     this.value = ((Object[])new Object[this.n]);
/*  145 */     this.used = new boolean[this.n];
/*  146 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(int expected)
/*      */   {
/*  153 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap()
/*      */   {
/*  159 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(Map<? extends K, ? extends V> m, float f)
/*      */   {
/*  167 */     this(m.size(), f);
/*  168 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(Map<? extends K, ? extends V> m)
/*      */   {
/*  175 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(Object2ReferenceMap<K, V> m, float f)
/*      */   {
/*  183 */     this(m.size(), f);
/*  184 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(Object2ReferenceMap<K, V> m)
/*      */   {
/*  191 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(K[] k, V[] v, float f)
/*      */   {
/*  201 */     this(k.length, f);
/*  202 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  203 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap(K[] k, V[] v)
/*      */   {
/*  212 */     this(k, v, 0.75F);
/*      */   }
/*      */ 
/*      */   public V put(K k, V v)
/*      */   {
/*  220 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*      */ 
/*  222 */     while (this.used[pos] != 0) {
/*  223 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/*  224 */         Object oldValue = this.value[pos];
/*  225 */         this.value[pos] = v;
/*  226 */         return oldValue;
/*      */       }
/*  228 */       pos = pos + 1 & this.mask;
/*      */     }
/*  230 */     this.used[pos] = true;
/*  231 */     this.key[pos] = k;
/*  232 */     this.value[pos] = v;
/*  233 */     if (this.size == 0) {
/*  234 */       this.first = (this.last = pos);
/*      */ 
/*  236 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  239 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  240 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  241 */       this.last = pos;
/*      */     }
/*  243 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  245 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   protected final int shiftKeys(int pos)
/*      */   {
/*      */     int last;
/*      */     while (true)
/*      */     {
/*  257 */       pos = (last = pos) + 1 & this.mask;
/*  258 */       while (this.used[pos] != 0) {
/*  259 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
/*  260 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  261 */         pos = pos + 1 & this.mask;
/*      */       }
/*  263 */       if (this.used[pos] == 0) break;
/*  264 */       this.key[last] = this.key[pos];
/*  265 */       this.value[last] = this.value[pos];
/*  266 */       fixPointers(pos, last);
/*      */     }
/*  268 */     this.used[last] = false;
/*  269 */     this.key[last] = null;
/*  270 */     this.value[last] = null;
/*  271 */     return last;
/*      */   }
/*      */ 
/*      */   public V remove(Object k)
/*      */   {
/*  276 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*      */ 
/*  278 */     while (this.used[pos] != 0) {
/*  279 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/*  280 */         this.size -= 1;
/*  281 */         fixPointers(pos);
/*  282 */         Object v = this.value[pos];
/*  283 */         shiftKeys(pos);
/*  284 */         return v;
/*      */       }
/*  286 */       pos = pos + 1 & this.mask;
/*      */     }
/*  288 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V removeFirst()
/*      */   {
/*  295 */     if (this.size == 0) throw new NoSuchElementException();
/*  296 */     this.size -= 1;
/*  297 */     int pos = this.first;
/*      */ 
/*  299 */     this.first = ((int)this.link[pos]);
/*  300 */     if (0 <= this.first)
/*      */     {
/*  302 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  304 */     Object v = this.value[pos];
/*  305 */     shiftKeys(pos);
/*  306 */     return v;
/*      */   }
/*      */ 
/*      */   public V removeLast()
/*      */   {
/*  313 */     if (this.size == 0) throw new NoSuchElementException();
/*  314 */     this.size -= 1;
/*  315 */     int pos = this.last;
/*      */ 
/*  317 */     this.last = ((int)(this.link[pos] >>> 32));
/*  318 */     if (0 <= this.last)
/*      */     {
/*  320 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  322 */     Object v = this.value[pos];
/*  323 */     shiftKeys(pos);
/*  324 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  327 */     if ((this.size == 1) || (this.first == i)) return;
/*  328 */     if (this.last == i) {
/*  329 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  331 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  334 */       long linki = this.link[i];
/*  335 */       int prev = (int)(linki >>> 32);
/*  336 */       int next = (int)linki;
/*  337 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  338 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  340 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  341 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  342 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  345 */     if ((this.size == 1) || (this.last == i)) return;
/*  346 */     if (this.first == i) {
/*  347 */       this.first = ((int)this.link[i]);
/*      */ 
/*  349 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  352 */       long linki = this.link[i];
/*  353 */       int prev = (int)(linki >>> 32);
/*  354 */       int next = (int)linki;
/*  355 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  356 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  358 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  359 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  360 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public V getAndMoveToFirst(K k)
/*      */   {
/*  368 */     Object[] key = this.key;
/*  369 */     boolean[] used = this.used;
/*  370 */     int mask = this.mask;
/*      */ 
/*  372 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*      */ 
/*  374 */     while (used[pos] != 0) {
/*  375 */       if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  376 */         moveIndexToFirst(pos);
/*  377 */         return this.value[pos];
/*      */       }
/*  379 */       pos = pos + 1 & mask;
/*      */     }
/*  381 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V getAndMoveToLast(K k)
/*      */   {
/*  389 */     Object[] key = this.key;
/*  390 */     boolean[] used = this.used;
/*  391 */     int mask = this.mask;
/*      */ 
/*  393 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*      */ 
/*  395 */     while (used[pos] != 0) {
/*  396 */       if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  397 */         moveIndexToLast(pos);
/*  398 */         return this.value[pos];
/*      */       }
/*  400 */       pos = pos + 1 & mask;
/*      */     }
/*  402 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V putAndMoveToFirst(K k, V v)
/*      */   {
/*  411 */     Object[] key = this.key;
/*  412 */     boolean[] used = this.used;
/*  413 */     int mask = this.mask;
/*      */ 
/*  415 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*      */ 
/*  417 */     while (used[pos] != 0) {
/*  418 */       if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  419 */         Object oldValue = this.value[pos];
/*  420 */         this.value[pos] = v;
/*  421 */         moveIndexToFirst(pos);
/*  422 */         return oldValue;
/*      */       }
/*  424 */       pos = pos + 1 & mask;
/*      */     }
/*  426 */     used[pos] = true;
/*  427 */     key[pos] = k;
/*  428 */     this.value[pos] = v;
/*  429 */     if (this.size == 0) {
/*  430 */       this.first = (this.last = pos);
/*      */ 
/*  432 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  435 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  436 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  437 */       this.first = pos;
/*      */     }
/*  439 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  441 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V putAndMoveToLast(K k, V v)
/*      */   {
/*  450 */     Object[] key = this.key;
/*  451 */     boolean[] used = this.used;
/*  452 */     int mask = this.mask;
/*      */ 
/*  454 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*      */ 
/*  456 */     while (used[pos] != 0) {
/*  457 */       if (k == null ? key[pos] == null : k.equals(key[pos])) {
/*  458 */         Object oldValue = this.value[pos];
/*  459 */         this.value[pos] = v;
/*  460 */         moveIndexToLast(pos);
/*  461 */         return oldValue;
/*      */       }
/*  463 */       pos = pos + 1 & mask;
/*      */     }
/*  465 */     used[pos] = true;
/*  466 */     key[pos] = k;
/*  467 */     this.value[pos] = v;
/*  468 */     if (this.size == 0) {
/*  469 */       this.first = (this.last = pos);
/*      */ 
/*  471 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  474 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  475 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  476 */       this.last = pos;
/*      */     }
/*  478 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  480 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V get(Object k)
/*      */   {
/*  485 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*      */ 
/*  487 */     while (this.used[pos] != 0) {
/*  488 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.value[pos];
/*  489 */       pos = pos + 1 & this.mask;
/*      */     }
/*  491 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  496 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*      */ 
/*  498 */     while (this.used[pos] != 0) {
/*  499 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/*  500 */       pos = pos + 1 & this.mask;
/*      */     }
/*  502 */     return false;
/*      */   }
/*      */   public boolean containsValue(Object v) {
/*  505 */     Object[] value = this.value;
/*  506 */     boolean[] used = this.used;
/*  507 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*      */         break label16; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  517 */     if (this.size == 0) return;
/*  518 */     this.size = 0;
/*  519 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  521 */     ObjectArrays.fill(this.key, null);
/*  522 */     ObjectArrays.fill(this.value, null);
/*  523 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  526 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  529 */     return this.size == 0;
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
/*  546 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  590 */     if (this.size == 0) {
/*  591 */       this.first = (this.last = -1);
/*  592 */       return;
/*      */     }
/*  594 */     if (this.first == i) {
/*  595 */       this.first = ((int)this.link[i]);
/*  596 */       if (0 <= this.first)
/*      */       {
/*  598 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  600 */       return;
/*      */     }
/*  602 */     if (this.last == i) {
/*  603 */       this.last = ((int)(this.link[i] >>> 32));
/*  604 */       if (0 <= this.last)
/*      */       {
/*  606 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  608 */       return;
/*      */     }
/*  610 */     long linki = this.link[i];
/*  611 */     int prev = (int)(linki >>> 32);
/*  612 */     int next = (int)linki;
/*  613 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  614 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  625 */     if (this.size == 1) {
/*  626 */       this.first = (this.last = d);
/*      */ 
/*  628 */       this.link[d] = -1L;
/*  629 */       return;
/*      */     }
/*  631 */     if (this.first == s) {
/*  632 */       this.first = d;
/*  633 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  634 */       this.link[d] = this.link[s];
/*  635 */       return;
/*      */     }
/*  637 */     if (this.last == s) {
/*  638 */       this.last = d;
/*  639 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  640 */       this.link[d] = this.link[s];
/*  641 */       return;
/*      */     }
/*  643 */     long links = this.link[s];
/*  644 */     int prev = (int)(links >>> 32);
/*  645 */     int next = (int)links;
/*  646 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  647 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  648 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public K firstKey()
/*      */   {
/*  655 */     if (this.size == 0) throw new NoSuchElementException();
/*  656 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public K lastKey()
/*      */   {
/*  663 */     if (this.size == 0) throw new NoSuchElementException();
/*  664 */     return this.key[this.last];
/*      */   }
/*  666 */   public Comparator<? super K> comparator() { return null; } 
/*  667 */   public Object2ReferenceSortedMap<K, V> tailMap(K from) { throw new UnsupportedOperationException(); } 
/*  668 */   public Object2ReferenceSortedMap<K, V> headMap(K to) { throw new UnsupportedOperationException(); } 
/*  669 */   public Object2ReferenceSortedMap<K, V> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Object2ReferenceSortedMap.FastSortedEntrySet<K, V> object2ReferenceEntrySet()
/*      */   {
/*  911 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/*  912 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/*  963 */     if (this.keys == null) this.keys = new KeySet(null);
/*  964 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ReferenceCollection<V> values()
/*      */   {
/*  980 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*      */         public ObjectIterator<V> iterator() {
/*  982 */           return new Object2ReferenceLinkedOpenHashMap.ValueIterator(Object2ReferenceLinkedOpenHashMap.this);
/*      */         }
/*      */         public int size() {
/*  985 */           return Object2ReferenceLinkedOpenHashMap.this.size;
/*      */         }
/*      */         public boolean contains(Object v) {
/*  988 */           return Object2ReferenceLinkedOpenHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/*  991 */           Object2ReferenceLinkedOpenHashMap.this.clear();
/*      */         }
/*      */       };
/*  994 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1008 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1023 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1024 */     if (l >= this.n) return true; try
/*      */     {
/* 1026 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1028 */       return false;
/* 1029 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1050 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1051 */     if (this.n <= l) return true; try
/*      */     {
/* 1053 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1055 */       return false;
/* 1056 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1069 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1071 */     Object[] key = this.key;
/* 1072 */     Object[] value = this.value;
/* 1073 */     int newMask = newN - 1;
/* 1074 */     Object[] newKey = (Object[])new Object[newN];
/* 1075 */     Object[] newValue = (Object[])new Object[newN];
/* 1076 */     boolean[] newUsed = new boolean[newN];
/* 1077 */     long[] link = this.link;
/* 1078 */     long[] newLink = new long[newN];
/* 1079 */     this.first = -1;
/* 1080 */     for (int j = this.size; j-- != 0; ) {
/* 1081 */       Object k = key[i];
/* 1082 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
/* 1083 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1084 */       newUsed[pos] = true;
/* 1085 */       newKey[pos] = k;
/* 1086 */       newValue[pos] = value[i];
/* 1087 */       if (prev != -1) {
/* 1088 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1089 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1090 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1093 */         newPrev = this.first = pos;
/*      */ 
/* 1095 */         newLink[pos] = -1L;
/*      */       }
/* 1097 */       int t = i;
/* 1098 */       i = (int)link[i];
/* 1099 */       prev = t;
/*      */     }
/* 1101 */     this.n = newN;
/* 1102 */     this.mask = newMask;
/* 1103 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1104 */     this.key = newKey;
/* 1105 */     this.value = newValue;
/* 1106 */     this.used = newUsed;
/* 1107 */     this.link = newLink;
/* 1108 */     this.last = newPrev;
/* 1109 */     if (newPrev != -1)
/*      */     {
/* 1111 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Object2ReferenceLinkedOpenHashMap<K, V> clone()
/*      */   {
/*      */     Object2ReferenceLinkedOpenHashMap c;
/*      */     try
/*      */     {
/* 1124 */       c = (Object2ReferenceLinkedOpenHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1127 */       throw new InternalError();
/*      */     }
/* 1129 */     c.keys = null;
/* 1130 */     c.values = null;
/* 1131 */     c.entries = null;
/* 1132 */     c.key = ((Object[])this.key.clone());
/* 1133 */     c.value = ((Object[])this.value.clone());
/* 1134 */     c.used = ((boolean[])this.used.clone());
/* 1135 */     c.link = ((long[])this.link.clone());
/* 1136 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1148 */     int h = 0;
/* 1149 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1150 */       while (this.used[i] == 0) i++;
/* 1151 */       if (this != this.key[i])
/* 1152 */         t = this.key[i] == null ? 0 : this.key[i].hashCode();
/* 1153 */       if (this != this.value[i])
/* 1154 */         t ^= (this.value[i] == null ? 0 : System.identityHashCode(this.value[i]));
/* 1155 */       h += t;
/* 1156 */       i++;
/*      */     }
/* 1158 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1161 */     Object[] key = this.key;
/* 1162 */     Object[] value = this.value;
/* 1163 */     MapIterator i = new MapIterator(null);
/* 1164 */     s.defaultWriteObject();
/* 1165 */     for (int j = this.size; j-- != 0; ) {
/* 1166 */       int e = i.nextEntry();
/* 1167 */       s.writeObject(key[e]);
/* 1168 */       s.writeObject(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1173 */     s.defaultReadObject();
/* 1174 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1175 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1176 */     this.mask = (this.n - 1);
/* 1177 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 1178 */     Object[] value = this.value = (Object[])new Object[this.n];
/* 1179 */     boolean[] used = this.used = new boolean[this.n];
/* 1180 */     long[] link = this.link = new long[this.n];
/* 1181 */     int prev = -1;
/* 1182 */     this.first = (this.last = -1);
/*      */ 
/* 1185 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1186 */       Object k = s.readObject();
/* 1187 */       Object v = s.readObject();
/* 1188 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 1189 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1190 */       used[pos] = true;
/* 1191 */       key[pos] = k;
/* 1192 */       value[pos] = v;
/* 1193 */       if (this.first != -1) {
/* 1194 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1195 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1196 */         prev = pos;
/*      */       }
/*      */       else {
/* 1199 */         prev = this.first = pos;
/*      */ 
/* 1201 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1204 */     this.last = prev;
/* 1205 */     if (prev != -1)
/*      */     {
/* 1207 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2ReferenceLinkedOpenHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<V>
/*      */   {
/*      */     public V previous()
/*      */     {
/*  973 */       return Object2ReferenceLinkedOpenHashMap.this.value[previousEntry()]; } 
/*  974 */     public void set(V v) { throw new UnsupportedOperationException(); } 
/*  975 */     public void add(V v) { throw new UnsupportedOperationException(); } 
/*  976 */     public ValueIterator() { super(null); } 
/*  977 */     public V next() { return Object2ReferenceLinkedOpenHashMap.this.value[nextEntry()]; }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeySet extends AbstractObjectSortedSet<K>
/*      */   {
/*      */     private KeySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectListIterator<K> iterator(K from)
/*      */     {
/*  930 */       return new Object2ReferenceLinkedOpenHashMap.KeyIterator(Object2ReferenceLinkedOpenHashMap.this, from);
/*      */     }
/*      */     public ObjectListIterator<K> iterator() {
/*  933 */       return new Object2ReferenceLinkedOpenHashMap.KeyIterator(Object2ReferenceLinkedOpenHashMap.this);
/*      */     }
/*      */     public int size() {
/*  936 */       return Object2ReferenceLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public boolean contains(Object k) {
/*  939 */       return Object2ReferenceLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(Object k) {
/*  942 */       int oldSize = Object2ReferenceLinkedOpenHashMap.this.size;
/*  943 */       Object2ReferenceLinkedOpenHashMap.this.remove(k);
/*  944 */       return Object2ReferenceLinkedOpenHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/*  947 */       Object2ReferenceLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public K first() {
/*  950 */       if (Object2ReferenceLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  951 */       return Object2ReferenceLinkedOpenHashMap.this.key[Object2ReferenceLinkedOpenHashMap.this.first];
/*      */     }
/*      */     public K last() {
/*  954 */       if (Object2ReferenceLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  955 */       return Object2ReferenceLinkedOpenHashMap.this.key[Object2ReferenceLinkedOpenHashMap.this.last];
/*      */     }
/*  957 */     public Comparator<? super K> comparator() { return null; } 
/*  958 */     public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/*  959 */     public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/*  960 */     public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2ReferenceLinkedOpenHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/*  921 */       super(k, null); } 
/*  922 */     public K previous() { return Object2ReferenceLinkedOpenHashMap.this.key[previousEntry()]; } 
/*  923 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/*  924 */     public void add(K k) { throw new UnsupportedOperationException(); } 
/*  925 */     public KeyIterator() { super(null); } 
/*  926 */     public K next() { return Object2ReferenceLinkedOpenHashMap.this.key[nextEntry()]; }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Object2ReferenceMap.Entry<K, V>>
/*      */     implements Object2ReferenceSortedMap.FastSortedEntrySet<K, V>
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator()
/*      */     {
/*  849 */       return new Object2ReferenceLinkedOpenHashMap.EntryIterator(Object2ReferenceLinkedOpenHashMap.this);
/*      */     }
/*  851 */     public Comparator<? super Object2ReferenceMap.Entry<K, V>> comparator() { return null; } 
/*  852 */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> subSet(Object2ReferenceMap.Entry<K, V> fromElement, Object2ReferenceMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); } 
/*  853 */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> headSet(Object2ReferenceMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); } 
/*  854 */     public ObjectSortedSet<Object2ReferenceMap.Entry<K, V>> tailSet(Object2ReferenceMap.Entry<K, V> fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Object2ReferenceMap.Entry<K, V> first() {
/*  856 */       if (Object2ReferenceLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  857 */       return new Object2ReferenceLinkedOpenHashMap.MapEntry(Object2ReferenceLinkedOpenHashMap.this, Object2ReferenceLinkedOpenHashMap.this.first);
/*      */     }
/*      */     public Object2ReferenceMap.Entry<K, V> last() {
/*  860 */       if (Object2ReferenceLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  861 */       return new Object2ReferenceLinkedOpenHashMap.MapEntry(Object2ReferenceLinkedOpenHashMap.this, Object2ReferenceLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  865 */       if (!(o instanceof Map.Entry)) return false;
/*  866 */       Map.Entry e = (Map.Entry)o;
/*  867 */       Object k = e.getKey();
/*      */ 
/*  869 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */ 
/*  871 */       while (Object2ReferenceLinkedOpenHashMap.this.used[pos] != 0) {
/*  872 */         if (Object2ReferenceLinkedOpenHashMap.this.key[pos] == null ? k == null : Object2ReferenceLinkedOpenHashMap.this.key[pos].equals(k)) return Object2ReferenceLinkedOpenHashMap.this.value[pos] == e.getValue();
/*  873 */         pos = pos + 1 & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */       }
/*  875 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  879 */       if (!(o instanceof Map.Entry)) return false;
/*  880 */       Map.Entry e = (Map.Entry)o;
/*  881 */       Object k = e.getKey();
/*      */ 
/*  883 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */ 
/*  885 */       while (Object2ReferenceLinkedOpenHashMap.this.used[pos] != 0) {
/*  886 */         if (Object2ReferenceLinkedOpenHashMap.this.key[pos] == null ? k == null : Object2ReferenceLinkedOpenHashMap.this.key[pos].equals(k)) {
/*  887 */           Object2ReferenceLinkedOpenHashMap.this.remove(e.getKey());
/*  888 */           return true;
/*      */         }
/*  890 */         pos = pos + 1 & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */       }
/*  892 */       return false;
/*      */     }
/*      */     public int size() {
/*  895 */       return Object2ReferenceLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/*  898 */       Object2ReferenceLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> iterator(Object2ReferenceMap.Entry<K, V> from) {
/*  901 */       return new Object2ReferenceLinkedOpenHashMap.EntryIterator(Object2ReferenceLinkedOpenHashMap.this, from.getKey());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> fastIterator() {
/*  904 */       return new Object2ReferenceLinkedOpenHashMap.FastEntryIterator(Object2ReferenceLinkedOpenHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2ReferenceMap.Entry<K, V>> fastIterator(Object2ReferenceMap.Entry<K, V> from) {
/*  907 */       return new Object2ReferenceLinkedOpenHashMap.FastEntryIterator(Object2ReferenceLinkedOpenHashMap.this, from.getKey());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Object2ReferenceLinkedOpenHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*      */   {
/*  827 */     final AbstractObject2ReferenceMap.BasicEntry<K, V> entry = new AbstractObject2ReferenceMap.BasicEntry(null, null);
/*      */ 
/*  828 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator() {
/*  830 */       super(from, null);
/*      */     }
/*      */     public AbstractObject2ReferenceMap.BasicEntry<K, V> next() {
/*  833 */       int e = nextEntry();
/*  834 */       this.entry.key = Object2ReferenceLinkedOpenHashMap.this.key[e];
/*  835 */       this.entry.value = Object2ReferenceLinkedOpenHashMap.this.value[e];
/*  836 */       return this.entry;
/*      */     }
/*      */     public AbstractObject2ReferenceMap.BasicEntry<K, V> previous() {
/*  839 */       int e = previousEntry();
/*  840 */       this.entry.key = Object2ReferenceLinkedOpenHashMap.this.key[e];
/*  841 */       this.entry.value = Object2ReferenceLinkedOpenHashMap.this.value[e];
/*  842 */       return this.entry;
/*      */     }
/*  844 */     public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/*  845 */     public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2ReferenceLinkedOpenHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<Object2ReferenceMap.Entry<K, V>>
/*      */   {
/*      */     private Object2ReferenceLinkedOpenHashMap<K, V>.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  808 */       super(null);
/*      */     }
/*  810 */     public EntryIterator() { super(from, null); }
/*      */ 
/*      */     public Object2ReferenceLinkedOpenHashMap<K, V>.MapEntry next() {
/*  813 */       return this.entry = new Object2ReferenceLinkedOpenHashMap.MapEntry(Object2ReferenceLinkedOpenHashMap.this, nextEntry());
/*      */     }
/*      */     public Object2ReferenceLinkedOpenHashMap<K, V>.MapEntry previous() {
/*  816 */       return this.entry = new Object2ReferenceLinkedOpenHashMap.MapEntry(Object2ReferenceLinkedOpenHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  820 */       super.remove();
/*  821 */       Object2ReferenceLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  823 */     public void set(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/*  824 */     public void add(Object2ReferenceMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  678 */     int prev = -1;
/*      */ 
/*  680 */     int next = -1;
/*      */ 
/*  682 */     int curr = -1;
/*      */ 
/*  684 */     int index = -1;
/*      */ 
/*  686 */     private MapIterator() { this.next = Object2ReferenceLinkedOpenHashMap.this.first;
/*  687 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator() {
/*  690 */       if (Object2ReferenceLinkedOpenHashMap.this.key[Object2ReferenceLinkedOpenHashMap.this.last] == null ? from == null : Object2ReferenceLinkedOpenHashMap.this.key[Object2ReferenceLinkedOpenHashMap.this.last].equals(from)) {
/*  691 */         this.prev = Object2ReferenceLinkedOpenHashMap.this.last;
/*  692 */         this.index = Object2ReferenceLinkedOpenHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  696 */         int pos = (from == null ? 142593372 : HashCommon.murmurHash3(from.hashCode())) & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */ 
/*  698 */         while (Object2ReferenceLinkedOpenHashMap.this.used[pos] != 0) {
/*  699 */           if (Object2ReferenceLinkedOpenHashMap.this.key[pos] == null ? from == null : Object2ReferenceLinkedOpenHashMap.this.key[pos].equals(from))
/*      */           {
/*  701 */             this.next = ((int)Object2ReferenceLinkedOpenHashMap.this.link[pos]);
/*  702 */             this.prev = pos;
/*  703 */             return;
/*      */           }
/*  705 */           pos = pos + 1 & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */         }
/*  707 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  710 */     public boolean hasNext() { return this.next != -1; } 
/*  711 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  713 */       if (this.index >= 0) return;
/*  714 */       if (this.prev == -1) {
/*  715 */         this.index = 0;
/*  716 */         return;
/*      */       }
/*  718 */       if (this.next == -1) {
/*  719 */         this.index = Object2ReferenceLinkedOpenHashMap.this.size;
/*  720 */         return;
/*      */       }
/*  722 */       int pos = Object2ReferenceLinkedOpenHashMap.this.first;
/*  723 */       this.index = 1;
/*  724 */       while (pos != this.prev) {
/*  725 */         pos = (int)Object2ReferenceLinkedOpenHashMap.this.link[pos];
/*  726 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  730 */     public int nextIndex() { ensureIndexKnown();
/*  731 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  734 */       ensureIndexKnown();
/*  735 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  738 */       if (!hasNext()) return Object2ReferenceLinkedOpenHashMap.this.size();
/*  739 */       this.curr = this.next;
/*  740 */       this.next = ((int)Object2ReferenceLinkedOpenHashMap.this.link[this.curr]);
/*  741 */       this.prev = this.curr;
/*  742 */       if (this.index >= 0) this.index += 1;
/*  743 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  746 */       if (!hasPrevious()) return -1;
/*  747 */       this.curr = this.prev;
/*  748 */       this.prev = ((int)(Object2ReferenceLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  749 */       this.next = this.curr;
/*  750 */       if (this.index >= 0) this.index -= 1;
/*  751 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  755 */       ensureIndexKnown();
/*  756 */       if (this.curr == -1) throw new IllegalStateException();
/*  757 */       if (this.curr == this.prev)
/*      */       {
/*  760 */         this.index -= 1;
/*  761 */         this.prev = ((int)(Object2ReferenceLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  764 */         this.next = ((int)Object2ReferenceLinkedOpenHashMap.this.link[this.curr]);
/*  765 */       }Object2ReferenceLinkedOpenHashMap.this.size -= 1;
/*      */ 
/*  768 */       if (this.prev == -1) Object2ReferenceLinkedOpenHashMap.this.first = this.next;
/*      */       else
/*  770 */         Object2ReferenceLinkedOpenHashMap.this.link[this.prev] ^= (Object2ReferenceLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  771 */       if (this.next == -1) Object2ReferenceLinkedOpenHashMap.this.last = this.prev;
/*      */       else
/*  773 */         Object2ReferenceLinkedOpenHashMap.this.link[this.next] ^= (Object2ReferenceLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  777 */         pos = (last = pos) + 1 & Object2ReferenceLinkedOpenHashMap.this.mask;
/*  778 */         while (Object2ReferenceLinkedOpenHashMap.this.used[pos] != 0) {
/*  779 */           int slot = (Object2ReferenceLinkedOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(Object2ReferenceLinkedOpenHashMap.this.key[pos].hashCode())) & Object2ReferenceLinkedOpenHashMap.this.mask;
/*  780 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  781 */           pos = pos + 1 & Object2ReferenceLinkedOpenHashMap.this.mask;
/*      */         }
/*  783 */         if (Object2ReferenceLinkedOpenHashMap.this.used[pos] == 0) break;
/*  784 */         Object2ReferenceLinkedOpenHashMap.this.key[last] = Object2ReferenceLinkedOpenHashMap.this.key[pos];
/*  785 */         Object2ReferenceLinkedOpenHashMap.this.value[last] = Object2ReferenceLinkedOpenHashMap.this.value[pos];
/*  786 */         if (this.next == pos) this.next = last;
/*  787 */         if (this.prev == pos) this.prev = last;
/*  788 */         Object2ReferenceLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */       }
/*  790 */       Object2ReferenceLinkedOpenHashMap.this.used[last] = false;
/*  791 */       Object2ReferenceLinkedOpenHashMap.this.key[last] = null;
/*  792 */       Object2ReferenceLinkedOpenHashMap.this.value[last] = null;
/*  793 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  796 */       int i = n;
/*  797 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  798 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  801 */       int i = n;
/*  802 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  803 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Object2ReferenceMap.Entry<K, V>, Map.Entry<K, V>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  556 */       this.index = index;
/*      */     }
/*      */     public K getKey() {
/*  559 */       return Object2ReferenceLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     public V getValue() {
/*  562 */       return Object2ReferenceLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public V setValue(V v) {
/*  565 */       Object oldValue = Object2ReferenceLinkedOpenHashMap.this.value[this.index];
/*  566 */       Object2ReferenceLinkedOpenHashMap.this.value[this.index] = v;
/*  567 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  571 */       if (!(o instanceof Map.Entry)) return false;
/*  572 */       Map.Entry e = (Map.Entry)o;
/*  573 */       return (Object2ReferenceLinkedOpenHashMap.this.key[this.index] == null ? e.getKey() == null : Object2ReferenceLinkedOpenHashMap.this.key[this.index].equals(e.getKey())) && (Object2ReferenceLinkedOpenHashMap.this.value[this.index] == e.getValue());
/*      */     }
/*      */     public int hashCode() {
/*  576 */       return (Object2ReferenceLinkedOpenHashMap.this.key[this.index] == null ? 0 : Object2ReferenceLinkedOpenHashMap.this.key[this.index].hashCode()) ^ (Object2ReferenceLinkedOpenHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Object2ReferenceLinkedOpenHashMap.this.value[this.index]));
/*      */     }
/*      */     public String toString() {
/*  579 */       return Object2ReferenceLinkedOpenHashMap.this.key[this.index] + "=>" + Object2ReferenceLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ReferenceLinkedOpenHashMap
 * JD-Core Version:    0.6.2
 */