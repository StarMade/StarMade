/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
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
/*      */ public class Object2ObjectLinkedOpenCustomHashMap<K, V> extends AbstractObject2ObjectSortedMap<K, V>
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
/*      */   protected volatile transient Object2ObjectSortedMap.FastSortedEntrySet<K, V> entries;
/*      */   protected volatile transient ObjectSortedSet<K> keys;
/*      */   protected volatile transient ObjectCollection<V> values;
/*  116 */   protected transient int first = -1;
/*      */ 
/*  118 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */   protected Hash.Strategy<K> strategy;
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  140 */     this.strategy = strategy;
/*  141 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  142 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  143 */     this.f = f;
/*  144 */     this.n = HashCommon.arraySize(expected, f);
/*  145 */     this.mask = (this.n - 1);
/*  146 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  147 */     this.key = ((Object[])new Object[this.n]);
/*  148 */     this.value = ((Object[])new Object[this.n]);
/*  149 */     this.used = new boolean[this.n];
/*  150 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*      */   {
/*  158 */     this(expected, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
/*      */   {
/*  165 */     this(16, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(Map<? extends K, ? extends V> m, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  174 */     this(m.size(), f, strategy);
/*  175 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(Map<? extends K, ? extends V> m, Hash.Strategy<K> strategy)
/*      */   {
/*  183 */     this(m, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(Object2ObjectMap<K, V> m, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  192 */     this(m.size(), f, strategy);
/*  193 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(Object2ObjectMap<K, V> m, Hash.Strategy<K> strategy)
/*      */   {
/*  201 */     this(m, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(K[] k, V[] v, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  212 */     this(k.length, f, strategy);
/*  213 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  214 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap(K[] k, V[] v, Hash.Strategy<K> strategy)
/*      */   {
/*  224 */     this(k, v, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Hash.Strategy<K> strategy()
/*      */   {
/*  231 */     return this.strategy;
/*      */   }
/*      */ 
/*      */   public V put(K k, V v)
/*      */   {
/*  239 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  241 */     while (this.used[pos] != 0) {
/*  242 */       if (this.strategy.equals(this.key[pos], k)) {
/*  243 */         Object oldValue = this.value[pos];
/*  244 */         this.value[pos] = v;
/*  245 */         return oldValue;
/*      */       }
/*  247 */       pos = pos + 1 & this.mask;
/*      */     }
/*  249 */     this.used[pos] = true;
/*  250 */     this.key[pos] = k;
/*  251 */     this.value[pos] = v;
/*  252 */     if (this.size == 0) {
/*  253 */       this.first = (this.last = pos);
/*      */ 
/*  255 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  258 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  259 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  260 */       this.last = pos;
/*      */     }
/*  262 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  264 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   protected final int shiftKeys(int pos)
/*      */   {
/*      */     int last;
/*      */     while (true)
/*      */     {
/*  276 */       pos = (last = pos) + 1 & this.mask;
/*  277 */       while (this.used[pos] != 0) {
/*  278 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/*  279 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  280 */         pos = pos + 1 & this.mask;
/*      */       }
/*  282 */       if (this.used[pos] == 0) break;
/*  283 */       this.key[last] = this.key[pos];
/*  284 */       this.value[last] = this.value[pos];
/*  285 */       fixPointers(pos, last);
/*      */     }
/*  287 */     this.used[last] = false;
/*  288 */     this.key[last] = null;
/*  289 */     this.value[last] = null;
/*  290 */     return last;
/*      */   }
/*      */ 
/*      */   public V remove(Object k)
/*      */   {
/*  295 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  297 */     while (this.used[pos] != 0) {
/*  298 */       if (this.strategy.equals(this.key[pos], k)) {
/*  299 */         this.size -= 1;
/*  300 */         fixPointers(pos);
/*  301 */         Object v = this.value[pos];
/*  302 */         shiftKeys(pos);
/*  303 */         return v;
/*      */       }
/*  305 */       pos = pos + 1 & this.mask;
/*      */     }
/*  307 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V removeFirst()
/*      */   {
/*  314 */     if (this.size == 0) throw new NoSuchElementException();
/*  315 */     this.size -= 1;
/*  316 */     int pos = this.first;
/*      */ 
/*  318 */     this.first = ((int)this.link[pos]);
/*  319 */     if (0 <= this.first)
/*      */     {
/*  321 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  323 */     Object v = this.value[pos];
/*  324 */     shiftKeys(pos);
/*  325 */     return v;
/*      */   }
/*      */ 
/*      */   public V removeLast()
/*      */   {
/*  332 */     if (this.size == 0) throw new NoSuchElementException();
/*  333 */     this.size -= 1;
/*  334 */     int pos = this.last;
/*      */ 
/*  336 */     this.last = ((int)(this.link[pos] >>> 32));
/*  337 */     if (0 <= this.last)
/*      */     {
/*  339 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  341 */     Object v = this.value[pos];
/*  342 */     shiftKeys(pos);
/*  343 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  346 */     if ((this.size == 1) || (this.first == i)) return;
/*  347 */     if (this.last == i) {
/*  348 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  350 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  353 */       long linki = this.link[i];
/*  354 */       int prev = (int)(linki >>> 32);
/*  355 */       int next = (int)linki;
/*  356 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  357 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  359 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  360 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  361 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  364 */     if ((this.size == 1) || (this.last == i)) return;
/*  365 */     if (this.first == i) {
/*  366 */       this.first = ((int)this.link[i]);
/*      */ 
/*  368 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  371 */       long linki = this.link[i];
/*  372 */       int prev = (int)(linki >>> 32);
/*  373 */       int next = (int)linki;
/*  374 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  375 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  377 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  378 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  379 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public V getAndMoveToFirst(K k)
/*      */   {
/*  387 */     Object[] key = this.key;
/*  388 */     boolean[] used = this.used;
/*  389 */     int mask = this.mask;
/*      */ 
/*  391 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  393 */     while (used[pos] != 0) {
/*  394 */       if (this.strategy.equals(k, key[pos])) {
/*  395 */         moveIndexToFirst(pos);
/*  396 */         return this.value[pos];
/*      */       }
/*  398 */       pos = pos + 1 & mask;
/*      */     }
/*  400 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V getAndMoveToLast(K k)
/*      */   {
/*  408 */     Object[] key = this.key;
/*  409 */     boolean[] used = this.used;
/*  410 */     int mask = this.mask;
/*      */ 
/*  412 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  414 */     while (used[pos] != 0) {
/*  415 */       if (this.strategy.equals(k, key[pos])) {
/*  416 */         moveIndexToLast(pos);
/*  417 */         return this.value[pos];
/*      */       }
/*  419 */       pos = pos + 1 & mask;
/*      */     }
/*  421 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V putAndMoveToFirst(K k, V v)
/*      */   {
/*  430 */     Object[] key = this.key;
/*  431 */     boolean[] used = this.used;
/*  432 */     int mask = this.mask;
/*      */ 
/*  434 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  436 */     while (used[pos] != 0) {
/*  437 */       if (this.strategy.equals(k, key[pos])) {
/*  438 */         Object oldValue = this.value[pos];
/*  439 */         this.value[pos] = v;
/*  440 */         moveIndexToFirst(pos);
/*  441 */         return oldValue;
/*      */       }
/*  443 */       pos = pos + 1 & mask;
/*      */     }
/*  445 */     used[pos] = true;
/*  446 */     key[pos] = k;
/*  447 */     this.value[pos] = v;
/*  448 */     if (this.size == 0) {
/*  449 */       this.first = (this.last = pos);
/*      */ 
/*  451 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  454 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  455 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  456 */       this.first = pos;
/*      */     }
/*  458 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  460 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V putAndMoveToLast(K k, V v)
/*      */   {
/*  469 */     Object[] key = this.key;
/*  470 */     boolean[] used = this.used;
/*  471 */     int mask = this.mask;
/*      */ 
/*  473 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  475 */     while (used[pos] != 0) {
/*  476 */       if (this.strategy.equals(k, key[pos])) {
/*  477 */         Object oldValue = this.value[pos];
/*  478 */         this.value[pos] = v;
/*  479 */         moveIndexToLast(pos);
/*  480 */         return oldValue;
/*      */       }
/*  482 */       pos = pos + 1 & mask;
/*      */     }
/*  484 */     used[pos] = true;
/*  485 */     key[pos] = k;
/*  486 */     this.value[pos] = v;
/*  487 */     if (this.size == 0) {
/*  488 */       this.first = (this.last = pos);
/*      */ 
/*  490 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  493 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  494 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  495 */       this.last = pos;
/*      */     }
/*  497 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  499 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public V get(Object k)
/*      */   {
/*  504 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  506 */     while (this.used[pos] != 0) {
/*  507 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/*  508 */       pos = pos + 1 & this.mask;
/*      */     }
/*  510 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  515 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  517 */     while (this.used[pos] != 0) {
/*  518 */       if (this.strategy.equals(this.key[pos], k)) return true;
/*  519 */       pos = pos + 1 & this.mask;
/*      */     }
/*  521 */     return false;
/*      */   }
/*      */   public boolean containsValue(Object v) {
/*  524 */     Object[] value = this.value;
/*  525 */     boolean[] used = this.used;
/*  526 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v)))
/*      */         break label16; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  536 */     if (this.size == 0) return;
/*  537 */     this.size = 0;
/*  538 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  540 */     ObjectArrays.fill(this.key, null);
/*  541 */     ObjectArrays.fill(this.value, null);
/*  542 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  545 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  548 */     return this.size == 0;
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
/*  565 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  609 */     if (this.size == 0) {
/*  610 */       this.first = (this.last = -1);
/*  611 */       return;
/*      */     }
/*  613 */     if (this.first == i) {
/*  614 */       this.first = ((int)this.link[i]);
/*  615 */       if (0 <= this.first)
/*      */       {
/*  617 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  619 */       return;
/*      */     }
/*  621 */     if (this.last == i) {
/*  622 */       this.last = ((int)(this.link[i] >>> 32));
/*  623 */       if (0 <= this.last)
/*      */       {
/*  625 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  627 */       return;
/*      */     }
/*  629 */     long linki = this.link[i];
/*  630 */     int prev = (int)(linki >>> 32);
/*  631 */     int next = (int)linki;
/*  632 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  633 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  644 */     if (this.size == 1) {
/*  645 */       this.first = (this.last = d);
/*      */ 
/*  647 */       this.link[d] = -1L;
/*  648 */       return;
/*      */     }
/*  650 */     if (this.first == s) {
/*  651 */       this.first = d;
/*  652 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  653 */       this.link[d] = this.link[s];
/*  654 */       return;
/*      */     }
/*  656 */     if (this.last == s) {
/*  657 */       this.last = d;
/*  658 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  659 */       this.link[d] = this.link[s];
/*  660 */       return;
/*      */     }
/*  662 */     long links = this.link[s];
/*  663 */     int prev = (int)(links >>> 32);
/*  664 */     int next = (int)links;
/*  665 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  666 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  667 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public K firstKey()
/*      */   {
/*  674 */     if (this.size == 0) throw new NoSuchElementException();
/*  675 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public K lastKey()
/*      */   {
/*  682 */     if (this.size == 0) throw new NoSuchElementException();
/*  683 */     return this.key[this.last];
/*      */   }
/*  685 */   public Comparator<? super K> comparator() { return null; } 
/*  686 */   public Object2ObjectSortedMap<K, V> tailMap(K from) { throw new UnsupportedOperationException(); } 
/*  687 */   public Object2ObjectSortedMap<K, V> headMap(K to) { throw new UnsupportedOperationException(); } 
/*  688 */   public Object2ObjectSortedMap<K, V> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Object2ObjectSortedMap.FastSortedEntrySet<K, V> object2ObjectEntrySet()
/*      */   {
/*  930 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/*  931 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/*  982 */     if (this.keys == null) this.keys = new KeySet(null);
/*  983 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ObjectCollection<V> values()
/*      */   {
/*  999 */     if (this.values == null) this.values = new AbstractObjectCollection() {
/*      */         public ObjectIterator<V> iterator() {
/* 1001 */           return new Object2ObjectLinkedOpenCustomHashMap.ValueIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1004 */           return Object2ObjectLinkedOpenCustomHashMap.this.size;
/*      */         }
/*      */         public boolean contains(Object v) {
/* 1007 */           return Object2ObjectLinkedOpenCustomHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1010 */           Object2ObjectLinkedOpenCustomHashMap.this.clear();
/*      */         }
/*      */       };
/* 1013 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1027 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1042 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1043 */     if (l >= this.n) return true; try
/*      */     {
/* 1045 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1047 */       return false;
/* 1048 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1069 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1070 */     if (this.n <= l) return true; try
/*      */     {
/* 1072 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1074 */       return false;
/* 1075 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1088 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1090 */     Object[] key = this.key;
/* 1091 */     Object[] value = this.value;
/* 1092 */     int newMask = newN - 1;
/* 1093 */     Object[] newKey = (Object[])new Object[newN];
/* 1094 */     Object[] newValue = (Object[])new Object[newN];
/* 1095 */     boolean[] newUsed = new boolean[newN];
/* 1096 */     long[] link = this.link;
/* 1097 */     long[] newLink = new long[newN];
/* 1098 */     this.first = -1;
/* 1099 */     for (int j = this.size; j-- != 0; ) {
/* 1100 */       Object k = key[i];
/* 1101 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 1102 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1103 */       newUsed[pos] = true;
/* 1104 */       newKey[pos] = k;
/* 1105 */       newValue[pos] = value[i];
/* 1106 */       if (prev != -1) {
/* 1107 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1108 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1109 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1112 */         newPrev = this.first = pos;
/*      */ 
/* 1114 */         newLink[pos] = -1L;
/*      */       }
/* 1116 */       int t = i;
/* 1117 */       i = (int)link[i];
/* 1118 */       prev = t;
/*      */     }
/* 1120 */     this.n = newN;
/* 1121 */     this.mask = newMask;
/* 1122 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1123 */     this.key = newKey;
/* 1124 */     this.value = newValue;
/* 1125 */     this.used = newUsed;
/* 1126 */     this.link = newLink;
/* 1127 */     this.last = newPrev;
/* 1128 */     if (newPrev != -1)
/*      */     {
/* 1130 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Object2ObjectLinkedOpenCustomHashMap<K, V> clone()
/*      */   {
/*      */     Object2ObjectLinkedOpenCustomHashMap c;
/*      */     try
/*      */     {
/* 1143 */       c = (Object2ObjectLinkedOpenCustomHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1146 */       throw new InternalError();
/*      */     }
/* 1148 */     c.keys = null;
/* 1149 */     c.values = null;
/* 1150 */     c.entries = null;
/* 1151 */     c.key = ((Object[])this.key.clone());
/* 1152 */     c.value = ((Object[])this.value.clone());
/* 1153 */     c.used = ((boolean[])this.used.clone());
/* 1154 */     c.link = ((long[])this.link.clone());
/* 1155 */     c.strategy = this.strategy;
/* 1156 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1168 */     int h = 0;
/* 1169 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1170 */       while (this.used[i] == 0) i++;
/* 1171 */       if (this != this.key[i])
/* 1172 */         t = this.strategy.hashCode(this.key[i]);
/* 1173 */       if (this != this.value[i])
/* 1174 */         t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 1175 */       h += t;
/* 1176 */       i++;
/*      */     }
/* 1178 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1181 */     Object[] key = this.key;
/* 1182 */     Object[] value = this.value;
/* 1183 */     MapIterator i = new MapIterator(null);
/* 1184 */     s.defaultWriteObject();
/* 1185 */     for (int j = this.size; j-- != 0; ) {
/* 1186 */       int e = i.nextEntry();
/* 1187 */       s.writeObject(key[e]);
/* 1188 */       s.writeObject(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1193 */     s.defaultReadObject();
/* 1194 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1195 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1196 */     this.mask = (this.n - 1);
/* 1197 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 1198 */     Object[] value = this.value = (Object[])new Object[this.n];
/* 1199 */     boolean[] used = this.used = new boolean[this.n];
/* 1200 */     long[] link = this.link = new long[this.n];
/* 1201 */     int prev = -1;
/* 1202 */     this.first = (this.last = -1);
/*      */ 
/* 1205 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1206 */       Object k = s.readObject();
/* 1207 */       Object v = s.readObject();
/* 1208 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 1209 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1210 */       used[pos] = true;
/* 1211 */       key[pos] = k;
/* 1212 */       value[pos] = v;
/* 1213 */       if (this.first != -1) {
/* 1214 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1215 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1216 */         prev = pos;
/*      */       }
/*      */       else {
/* 1219 */         prev = this.first = pos;
/*      */ 
/* 1221 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1224 */     this.last = prev;
/* 1225 */     if (prev != -1)
/*      */     {
/* 1227 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<V>
/*      */   {
/*      */     public V previous()
/*      */     {
/*  992 */       return Object2ObjectLinkedOpenCustomHashMap.this.value[previousEntry()]; } 
/*  993 */     public void set(V v) { throw new UnsupportedOperationException(); } 
/*  994 */     public void add(V v) { throw new UnsupportedOperationException(); } 
/*  995 */     public ValueIterator() { super(null); } 
/*  996 */     public V next() { return Object2ObjectLinkedOpenCustomHashMap.this.value[nextEntry()]; }
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
/*  949 */       return new Object2ObjectLinkedOpenCustomHashMap.KeyIterator(Object2ObjectLinkedOpenCustomHashMap.this, from);
/*      */     }
/*      */     public ObjectListIterator<K> iterator() {
/*  952 */       return new Object2ObjectLinkedOpenCustomHashMap.KeyIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/*      */     }
/*      */     public int size() {
/*  955 */       return Object2ObjectLinkedOpenCustomHashMap.this.size;
/*      */     }
/*      */     public boolean contains(Object k) {
/*  958 */       return Object2ObjectLinkedOpenCustomHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(Object k) {
/*  961 */       int oldSize = Object2ObjectLinkedOpenCustomHashMap.this.size;
/*  962 */       Object2ObjectLinkedOpenCustomHashMap.this.remove(k);
/*  963 */       return Object2ObjectLinkedOpenCustomHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/*  966 */       Object2ObjectLinkedOpenCustomHashMap.this.clear();
/*      */     }
/*      */     public K first() {
/*  969 */       if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  970 */       return Object2ObjectLinkedOpenCustomHashMap.this.key[Object2ObjectLinkedOpenCustomHashMap.this.first];
/*      */     }
/*      */     public K last() {
/*  973 */       if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  974 */       return Object2ObjectLinkedOpenCustomHashMap.this.key[Object2ObjectLinkedOpenCustomHashMap.this.last];
/*      */     }
/*  976 */     public Comparator<? super K> comparator() { return null; } 
/*  977 */     public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/*  978 */     public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/*  979 */     public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/*  940 */       super(k, null); } 
/*  941 */     public K previous() { return Object2ObjectLinkedOpenCustomHashMap.this.key[previousEntry()]; } 
/*  942 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/*  943 */     public void add(K k) { throw new UnsupportedOperationException(); } 
/*  944 */     public KeyIterator() { super(null); } 
/*  945 */     public K next() { return Object2ObjectLinkedOpenCustomHashMap.this.key[nextEntry()]; }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Object2ObjectMap.Entry<K, V>>
/*      */     implements Object2ObjectSortedMap.FastSortedEntrySet<K, V>
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator()
/*      */     {
/*  868 */       return new Object2ObjectLinkedOpenCustomHashMap.EntryIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/*      */     }
/*  870 */     public Comparator<? super Object2ObjectMap.Entry<K, V>> comparator() { return null; } 
/*  871 */     public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> subSet(Object2ObjectMap.Entry<K, V> fromElement, Object2ObjectMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); } 
/*  872 */     public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> headSet(Object2ObjectMap.Entry<K, V> toElement) { throw new UnsupportedOperationException(); } 
/*  873 */     public ObjectSortedSet<Object2ObjectMap.Entry<K, V>> tailSet(Object2ObjectMap.Entry<K, V> fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Object2ObjectMap.Entry<K, V> first() {
/*  875 */       if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  876 */       return new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, Object2ObjectLinkedOpenCustomHashMap.this.first);
/*      */     }
/*      */     public Object2ObjectMap.Entry<K, V> last() {
/*  879 */       if (Object2ObjectLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  880 */       return new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, Object2ObjectLinkedOpenCustomHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  884 */       if (!(o instanceof Map.Entry)) return false;
/*  885 */       Map.Entry e = (Map.Entry)o;
/*  886 */       Object k = e.getKey();
/*      */ 
/*  888 */       int pos = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */ 
/*  890 */       while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  891 */         if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[pos], k)) return Object2ObjectLinkedOpenCustomHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Object2ObjectLinkedOpenCustomHashMap.this.value[pos].equals(e.getValue());
/*  892 */         pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */       }
/*  894 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  898 */       if (!(o instanceof Map.Entry)) return false;
/*  899 */       Map.Entry e = (Map.Entry)o;
/*  900 */       Object k = e.getKey();
/*      */ 
/*  902 */       int pos = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */ 
/*  904 */       while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  905 */         if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[pos], k)) {
/*  906 */           Object2ObjectLinkedOpenCustomHashMap.this.remove(e.getKey());
/*  907 */           return true;
/*      */         }
/*  909 */         pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */       }
/*  911 */       return false;
/*      */     }
/*      */     public int size() {
/*  914 */       return Object2ObjectLinkedOpenCustomHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/*  917 */       Object2ObjectLinkedOpenCustomHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> iterator(Object2ObjectMap.Entry<K, V> from) {
/*  920 */       return new Object2ObjectLinkedOpenCustomHashMap.EntryIterator(Object2ObjectLinkedOpenCustomHashMap.this, from.getKey());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator() {
/*  923 */       return new Object2ObjectLinkedOpenCustomHashMap.FastEntryIterator(Object2ObjectLinkedOpenCustomHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2ObjectMap.Entry<K, V>> fastIterator(Object2ObjectMap.Entry<K, V> from) {
/*  926 */       return new Object2ObjectLinkedOpenCustomHashMap.FastEntryIterator(Object2ObjectLinkedOpenCustomHashMap.this, from.getKey());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<Object2ObjectMap.Entry<K, V>>
/*      */   {
/*  846 */     final AbstractObject2ObjectMap.BasicEntry<K, V> entry = new AbstractObject2ObjectMap.BasicEntry(null, null);
/*      */ 
/*  847 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator() {
/*  849 */       super(from, null);
/*      */     }
/*      */     public AbstractObject2ObjectMap.BasicEntry<K, V> next() {
/*  852 */       int e = nextEntry();
/*  853 */       this.entry.key = Object2ObjectLinkedOpenCustomHashMap.this.key[e];
/*  854 */       this.entry.value = Object2ObjectLinkedOpenCustomHashMap.this.value[e];
/*  855 */       return this.entry;
/*      */     }
/*      */     public AbstractObject2ObjectMap.BasicEntry<K, V> previous() {
/*  858 */       int e = previousEntry();
/*  859 */       this.entry.key = Object2ObjectLinkedOpenCustomHashMap.this.key[e];
/*  860 */       this.entry.value = Object2ObjectLinkedOpenCustomHashMap.this.value[e];
/*  861 */       return this.entry;
/*      */     }
/*  863 */     public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/*  864 */     public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2ObjectLinkedOpenCustomHashMap<K, V>.MapIterator
/*      */     implements ObjectListIterator<Object2ObjectMap.Entry<K, V>>
/*      */   {
/*      */     private Object2ObjectLinkedOpenCustomHashMap<K, V>.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  827 */       super(null);
/*      */     }
/*  829 */     public EntryIterator() { super(from, null); }
/*      */ 
/*      */     public Object2ObjectLinkedOpenCustomHashMap<K, V>.MapEntry next() {
/*  832 */       return this.entry = new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, nextEntry());
/*      */     }
/*      */     public Object2ObjectLinkedOpenCustomHashMap<K, V>.MapEntry previous() {
/*  835 */       return this.entry = new Object2ObjectLinkedOpenCustomHashMap.MapEntry(Object2ObjectLinkedOpenCustomHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  839 */       super.remove();
/*  840 */       Object2ObjectLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  842 */     public void set(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); } 
/*  843 */     public void add(Object2ObjectMap.Entry<K, V> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  697 */     int prev = -1;
/*      */ 
/*  699 */     int next = -1;
/*      */ 
/*  701 */     int curr = -1;
/*      */ 
/*  703 */     int index = -1;
/*      */ 
/*  705 */     private MapIterator() { this.next = Object2ObjectLinkedOpenCustomHashMap.this.first;
/*  706 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator() {
/*  709 */       if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[Object2ObjectLinkedOpenCustomHashMap.this.last], from)) {
/*  710 */         this.prev = Object2ObjectLinkedOpenCustomHashMap.this.last;
/*  711 */         this.index = Object2ObjectLinkedOpenCustomHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  715 */         int pos = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */ 
/*  717 */         while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  718 */           if (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[pos], from))
/*      */           {
/*  720 */             this.next = ((int)Object2ObjectLinkedOpenCustomHashMap.this.link[pos]);
/*  721 */             this.prev = pos;
/*  722 */             return;
/*      */           }
/*  724 */           pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */         }
/*  726 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  729 */     public boolean hasNext() { return this.next != -1; } 
/*  730 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  732 */       if (this.index >= 0) return;
/*  733 */       if (this.prev == -1) {
/*  734 */         this.index = 0;
/*  735 */         return;
/*      */       }
/*  737 */       if (this.next == -1) {
/*  738 */         this.index = Object2ObjectLinkedOpenCustomHashMap.this.size;
/*  739 */         return;
/*      */       }
/*  741 */       int pos = Object2ObjectLinkedOpenCustomHashMap.this.first;
/*  742 */       this.index = 1;
/*  743 */       while (pos != this.prev) {
/*  744 */         pos = (int)Object2ObjectLinkedOpenCustomHashMap.this.link[pos];
/*  745 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  749 */     public int nextIndex() { ensureIndexKnown();
/*  750 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  753 */       ensureIndexKnown();
/*  754 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  757 */       if (!hasNext()) return Object2ObjectLinkedOpenCustomHashMap.this.size();
/*  758 */       this.curr = this.next;
/*  759 */       this.next = ((int)Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr]);
/*  760 */       this.prev = this.curr;
/*  761 */       if (this.index >= 0) this.index += 1;
/*  762 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  765 */       if (!hasPrevious()) return -1;
/*  766 */       this.curr = this.prev;
/*  767 */       this.prev = ((int)(Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  768 */       this.next = this.curr;
/*  769 */       if (this.index >= 0) this.index -= 1;
/*  770 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  774 */       ensureIndexKnown();
/*  775 */       if (this.curr == -1) throw new IllegalStateException();
/*  776 */       if (this.curr == this.prev)
/*      */       {
/*  779 */         this.index -= 1;
/*  780 */         this.prev = ((int)(Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  783 */         this.next = ((int)Object2ObjectLinkedOpenCustomHashMap.this.link[this.curr]);
/*  784 */       }Object2ObjectLinkedOpenCustomHashMap.this.size -= 1;
/*      */ 
/*  787 */       if (this.prev == -1) Object2ObjectLinkedOpenCustomHashMap.this.first = this.next;
/*      */       else
/*  789 */         Object2ObjectLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2ObjectLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  790 */       if (this.next == -1) Object2ObjectLinkedOpenCustomHashMap.this.last = this.prev;
/*      */       else
/*  792 */         Object2ObjectLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2ObjectLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  796 */         pos = (last = pos) + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  797 */         while (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  798 */           int slot = HashCommon.murmurHash3(Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ObjectLinkedOpenCustomHashMap.this.key[pos])) & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*  799 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  800 */           pos = pos + 1 & Object2ObjectLinkedOpenCustomHashMap.this.mask;
/*      */         }
/*  802 */         if (Object2ObjectLinkedOpenCustomHashMap.this.used[pos] == 0) break;
/*  803 */         Object2ObjectLinkedOpenCustomHashMap.this.key[last] = Object2ObjectLinkedOpenCustomHashMap.this.key[pos];
/*  804 */         Object2ObjectLinkedOpenCustomHashMap.this.value[last] = Object2ObjectLinkedOpenCustomHashMap.this.value[pos];
/*  805 */         if (this.next == pos) this.next = last;
/*  806 */         if (this.prev == pos) this.prev = last;
/*  807 */         Object2ObjectLinkedOpenCustomHashMap.this.fixPointers(pos, last);
/*      */       }
/*  809 */       Object2ObjectLinkedOpenCustomHashMap.this.used[last] = false;
/*  810 */       Object2ObjectLinkedOpenCustomHashMap.this.key[last] = null;
/*  811 */       Object2ObjectLinkedOpenCustomHashMap.this.value[last] = null;
/*  812 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  815 */       int i = n;
/*  816 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  817 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  820 */       int i = n;
/*  821 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  822 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Object2ObjectMap.Entry<K, V>, Map.Entry<K, V>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  575 */       this.index = index;
/*      */     }
/*      */     public K getKey() {
/*  578 */       return Object2ObjectLinkedOpenCustomHashMap.this.key[this.index];
/*      */     }
/*      */     public V getValue() {
/*  581 */       return Object2ObjectLinkedOpenCustomHashMap.this.value[this.index];
/*      */     }
/*      */     public V setValue(V v) {
/*  584 */       Object oldValue = Object2ObjectLinkedOpenCustomHashMap.this.value[this.index];
/*  585 */       Object2ObjectLinkedOpenCustomHashMap.this.value[this.index] = v;
/*  586 */       return oldValue;
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  590 */       if (!(o instanceof Map.Entry)) return false;
/*  591 */       Map.Entry e = (Map.Entry)o;
/*  592 */       return (Object2ObjectLinkedOpenCustomHashMap.this.strategy.equals(Object2ObjectLinkedOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2ObjectLinkedOpenCustomHashMap.this.value[this.index] == null ? e.getValue() == null : Object2ObjectLinkedOpenCustomHashMap.this.value[this.index].equals(e.getValue()));
/*      */     }
/*      */     public int hashCode() {
/*  595 */       return Object2ObjectLinkedOpenCustomHashMap.this.strategy.hashCode(Object2ObjectLinkedOpenCustomHashMap.this.key[this.index]) ^ (Object2ObjectLinkedOpenCustomHashMap.this.value[this.index] == null ? 0 : Object2ObjectLinkedOpenCustomHashMap.this.value[this.index].hashCode());
/*      */     }
/*      */     public String toString() {
/*  598 */       return Object2ObjectLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2ObjectLinkedOpenCustomHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */