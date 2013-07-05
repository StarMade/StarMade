/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*      */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*      */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*      */ import it.unimi.dsi.fastutil.chars.CharListIterator;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Comparator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class Reference2CharLinkedOpenHashMap<K> extends AbstractReference2CharSortedMap<K>
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   public static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient K[] key;
/*      */   protected transient char[] value;
/*      */   protected transient boolean[] used;
/*      */   protected final float f;
/*      */   protected transient int n;
/*      */   protected transient int maxFill;
/*      */   protected transient int mask;
/*      */   protected int size;
/*      */   protected volatile transient Reference2CharSortedMap.FastSortedEntrySet<K> entries;
/*      */   protected volatile transient ReferenceSortedSet<K> keys;
/*      */   protected volatile transient CharCollection values;
/*  130 */   protected transient int first = -1;
/*      */ 
/*  132 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(int expected, float f)
/*      */   {
/*  151 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153 */     this.f = f;
/*  154 */     this.n = HashCommon.arraySize(expected, f);
/*  155 */     this.mask = (this.n - 1);
/*  156 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  157 */     this.key = ((Object[])new Object[this.n]);
/*  158 */     this.value = new char[this.n];
/*  159 */     this.used = new boolean[this.n];
/*  160 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(int expected)
/*      */   {
/*  167 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap()
/*      */   {
/*  173 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(Map<? extends K, ? extends Character> m, float f)
/*      */   {
/*  181 */     this(m.size(), f);
/*  182 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(Map<? extends K, ? extends Character> m)
/*      */   {
/*  189 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(Reference2CharMap<K> m, float f)
/*      */   {
/*  197 */     this(m.size(), f);
/*  198 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(Reference2CharMap<K> m)
/*      */   {
/*  205 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(K[] k, char[] v, float f)
/*      */   {
/*  215 */     this(k.length, f);
/*  216 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap(K[] k, char[] v)
/*      */   {
/*  226 */     this(k, v, 0.75F);
/*      */   }
/*      */ 
/*      */   public char put(K k, char v)
/*      */   {
/*  234 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  236 */     while (this.used[pos] != 0) {
/*  237 */       if (this.key[pos] == k) {
/*  238 */         char oldValue = this.value[pos];
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
/*      */   public Character put(K ok, Character ov) {
/*  262 */     char v = ov.charValue();
/*  263 */     Object k = ok;
/*      */ 
/*  265 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  267 */     while (this.used[pos] != 0) {
/*  268 */       if (this.key[pos] == k) {
/*  269 */         Character oldValue = Character.valueOf(this.value[pos]);
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
/*      */   protected final int shiftKeys(int pos)
/*      */   {
/*      */     int last;
/*      */     while (true)
/*      */     {
/*  302 */       pos = (last = pos) + 1 & this.mask;
/*  303 */       while (this.used[pos] != 0) {
/*  304 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/*  305 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  306 */         pos = pos + 1 & this.mask;
/*      */       }
/*  308 */       if (this.used[pos] == 0) break;
/*  309 */       this.key[last] = this.key[pos];
/*  310 */       this.value[last] = this.value[pos];
/*  311 */       fixPointers(pos, last);
/*      */     }
/*  313 */     this.used[last] = false;
/*  314 */     this.key[last] = null;
/*  315 */     return last;
/*      */   }
/*      */ 
/*      */   public char removeChar(Object k)
/*      */   {
/*  320 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  322 */     while (this.used[pos] != 0) {
/*  323 */       if (this.key[pos] == k) {
/*  324 */         this.size -= 1;
/*  325 */         fixPointers(pos);
/*  326 */         char v = this.value[pos];
/*  327 */         shiftKeys(pos);
/*  328 */         return v;
/*      */       }
/*  330 */       pos = pos + 1 & this.mask;
/*      */     }
/*  332 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public Character remove(Object ok) {
/*  336 */     Object k = ok;
/*      */ 
/*  338 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  340 */     while (this.used[pos] != 0) {
/*  341 */       if (this.key[pos] == k) {
/*  342 */         this.size -= 1;
/*  343 */         fixPointers(pos);
/*  344 */         char v = this.value[pos];
/*  345 */         shiftKeys(pos);
/*  346 */         return Character.valueOf(v);
/*      */       }
/*  348 */       pos = pos + 1 & this.mask;
/*      */     }
/*  350 */     return null;
/*      */   }
/*      */ 
/*      */   public char removeFirstChar()
/*      */   {
/*  357 */     if (this.size == 0) throw new NoSuchElementException();
/*  358 */     this.size -= 1;
/*  359 */     int pos = this.first;
/*      */ 
/*  361 */     this.first = ((int)this.link[pos]);
/*  362 */     if (0 <= this.first)
/*      */     {
/*  364 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  366 */     char v = this.value[pos];
/*  367 */     shiftKeys(pos);
/*  368 */     return v;
/*      */   }
/*      */ 
/*      */   public char removeLastChar()
/*      */   {
/*  375 */     if (this.size == 0) throw new NoSuchElementException();
/*  376 */     this.size -= 1;
/*  377 */     int pos = this.last;
/*      */ 
/*  379 */     this.last = ((int)(this.link[pos] >>> 32));
/*  380 */     if (0 <= this.last)
/*      */     {
/*  382 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  384 */     char v = this.value[pos];
/*  385 */     shiftKeys(pos);
/*  386 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  389 */     if ((this.size == 1) || (this.first == i)) return;
/*  390 */     if (this.last == i) {
/*  391 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  393 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  396 */       long linki = this.link[i];
/*  397 */       int prev = (int)(linki >>> 32);
/*  398 */       int next = (int)linki;
/*  399 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  400 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  402 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  403 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  404 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  407 */     if ((this.size == 1) || (this.last == i)) return;
/*  408 */     if (this.first == i) {
/*  409 */       this.first = ((int)this.link[i]);
/*      */ 
/*  411 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  414 */       long linki = this.link[i];
/*  415 */       int prev = (int)(linki >>> 32);
/*  416 */       int next = (int)linki;
/*  417 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  418 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  420 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  421 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  422 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public char getAndMoveToFirst(K k)
/*      */   {
/*  430 */     Object[] key = this.key;
/*  431 */     boolean[] used = this.used;
/*  432 */     int mask = this.mask;
/*      */ 
/*  434 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  436 */     while (used[pos] != 0) {
/*  437 */       if (k == key[pos]) {
/*  438 */         moveIndexToFirst(pos);
/*  439 */         return this.value[pos];
/*      */       }
/*  441 */       pos = pos + 1 & mask;
/*      */     }
/*  443 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char getAndMoveToLast(K k)
/*      */   {
/*  451 */     Object[] key = this.key;
/*  452 */     boolean[] used = this.used;
/*  453 */     int mask = this.mask;
/*      */ 
/*  455 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  457 */     while (used[pos] != 0) {
/*  458 */       if (k == key[pos]) {
/*  459 */         moveIndexToLast(pos);
/*  460 */         return this.value[pos];
/*      */       }
/*  462 */       pos = pos + 1 & mask;
/*      */     }
/*  464 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char putAndMoveToFirst(K k, char v)
/*      */   {
/*  473 */     Object[] key = this.key;
/*  474 */     boolean[] used = this.used;
/*  475 */     int mask = this.mask;
/*      */ 
/*  477 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  479 */     while (used[pos] != 0) {
/*  480 */       if (k == key[pos]) {
/*  481 */         char oldValue = this.value[pos];
/*  482 */         this.value[pos] = v;
/*  483 */         moveIndexToFirst(pos);
/*  484 */         return oldValue;
/*      */       }
/*  486 */       pos = pos + 1 & mask;
/*      */     }
/*  488 */     used[pos] = true;
/*  489 */     key[pos] = k;
/*  490 */     this.value[pos] = v;
/*  491 */     if (this.size == 0) {
/*  492 */       this.first = (this.last = pos);
/*      */ 
/*  494 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  497 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  498 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  499 */       this.first = pos;
/*      */     }
/*  501 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  503 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char putAndMoveToLast(K k, char v)
/*      */   {
/*  512 */     Object[] key = this.key;
/*  513 */     boolean[] used = this.used;
/*  514 */     int mask = this.mask;
/*      */ 
/*  516 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*      */ 
/*  518 */     while (used[pos] != 0) {
/*  519 */       if (k == key[pos]) {
/*  520 */         char oldValue = this.value[pos];
/*  521 */         this.value[pos] = v;
/*  522 */         moveIndexToLast(pos);
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
/*  536 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  537 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  538 */       this.last = pos;
/*      */     }
/*  540 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  542 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char getChar(Object k)
/*      */   {
/*  547 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  549 */     while (this.used[pos] != 0) {
/*  550 */       if (this.key[pos] == k) return this.value[pos];
/*  551 */       pos = pos + 1 & this.mask;
/*      */     }
/*  553 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  558 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*      */ 
/*  560 */     while (this.used[pos] != 0) {
/*  561 */       if (this.key[pos] == k) return true;
/*  562 */       pos = pos + 1 & this.mask;
/*      */     }
/*  564 */     return false;
/*      */   }
/*      */   public boolean containsValue(char v) {
/*  567 */     char[] value = this.value;
/*  568 */     boolean[] used = this.used;
/*  569 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*      */         break label16; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  579 */     if (this.size == 0) return;
/*  580 */     this.size = 0;
/*  581 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  583 */     ObjectArrays.fill(this.key, null);
/*  584 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  587 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  590 */     return this.size == 0;
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
/*  607 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  657 */     if (this.size == 0) {
/*  658 */       this.first = (this.last = -1);
/*  659 */       return;
/*      */     }
/*  661 */     if (this.first == i) {
/*  662 */       this.first = ((int)this.link[i]);
/*  663 */       if (0 <= this.first)
/*      */       {
/*  665 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  667 */       return;
/*      */     }
/*  669 */     if (this.last == i) {
/*  670 */       this.last = ((int)(this.link[i] >>> 32));
/*  671 */       if (0 <= this.last)
/*      */       {
/*  673 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  675 */       return;
/*      */     }
/*  677 */     long linki = this.link[i];
/*  678 */     int prev = (int)(linki >>> 32);
/*  679 */     int next = (int)linki;
/*  680 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  681 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  692 */     if (this.size == 1) {
/*  693 */       this.first = (this.last = d);
/*      */ 
/*  695 */       this.link[d] = -1L;
/*  696 */       return;
/*      */     }
/*  698 */     if (this.first == s) {
/*  699 */       this.first = d;
/*  700 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  701 */       this.link[d] = this.link[s];
/*  702 */       return;
/*      */     }
/*  704 */     if (this.last == s) {
/*  705 */       this.last = d;
/*  706 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  707 */       this.link[d] = this.link[s];
/*  708 */       return;
/*      */     }
/*  710 */     long links = this.link[s];
/*  711 */     int prev = (int)(links >>> 32);
/*  712 */     int next = (int)links;
/*  713 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  714 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  715 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public K firstKey()
/*      */   {
/*  722 */     if (this.size == 0) throw new NoSuchElementException();
/*  723 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public K lastKey()
/*      */   {
/*  730 */     if (this.size == 0) throw new NoSuchElementException();
/*  731 */     return this.key[this.last];
/*      */   }
/*  733 */   public Comparator<? super K> comparator() { return null; } 
/*  734 */   public Reference2CharSortedMap<K> tailMap(K from) { throw new UnsupportedOperationException(); } 
/*  735 */   public Reference2CharSortedMap<K> headMap(K to) { throw new UnsupportedOperationException(); } 
/*  736 */   public Reference2CharSortedMap<K> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Reference2CharSortedMap.FastSortedEntrySet<K> reference2CharEntrySet()
/*      */   {
/*  977 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/*  978 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ReferenceSortedSet<K> keySet()
/*      */   {
/* 1029 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1030 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public CharCollection values()
/*      */   {
/* 1050 */     if (this.values == null) this.values = new AbstractCharCollection() {
/*      */         public CharIterator iterator() {
/* 1052 */           return new Reference2CharLinkedOpenHashMap.ValueIterator(Reference2CharLinkedOpenHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1055 */           return Reference2CharLinkedOpenHashMap.this.size;
/*      */         }
/*      */         public boolean contains(char v) {
/* 1058 */           return Reference2CharLinkedOpenHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1061 */           Reference2CharLinkedOpenHashMap.this.clear();
/*      */         }
/*      */       };
/* 1064 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1078 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1093 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1094 */     if (l >= this.n) return true; try
/*      */     {
/* 1096 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1098 */       return false;
/* 1099 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1120 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1121 */     if (this.n <= l) return true; try
/*      */     {
/* 1123 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1125 */       return false;
/* 1126 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1139 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1141 */     Object[] key = this.key;
/* 1142 */     char[] value = this.value;
/* 1143 */     int newMask = newN - 1;
/* 1144 */     Object[] newKey = (Object[])new Object[newN];
/* 1145 */     char[] newValue = new char[newN];
/* 1146 */     boolean[] newUsed = new boolean[newN];
/* 1147 */     long[] link = this.link;
/* 1148 */     long[] newLink = new long[newN];
/* 1149 */     this.first = -1;
/* 1150 */     for (int j = this.size; j-- != 0; ) {
/* 1151 */       Object k = key[i];
/* 1152 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 1153 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1154 */       newUsed[pos] = true;
/* 1155 */       newKey[pos] = k;
/* 1156 */       newValue[pos] = value[i];
/* 1157 */       if (prev != -1) {
/* 1158 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1159 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1160 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1163 */         newPrev = this.first = pos;
/*      */ 
/* 1165 */         newLink[pos] = -1L;
/*      */       }
/* 1167 */       int t = i;
/* 1168 */       i = (int)link[i];
/* 1169 */       prev = t;
/*      */     }
/* 1171 */     this.n = newN;
/* 1172 */     this.mask = newMask;
/* 1173 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1174 */     this.key = newKey;
/* 1175 */     this.value = newValue;
/* 1176 */     this.used = newUsed;
/* 1177 */     this.link = newLink;
/* 1178 */     this.last = newPrev;
/* 1179 */     if (newPrev != -1)
/*      */     {
/* 1181 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Reference2CharLinkedOpenHashMap<K> clone()
/*      */   {
/*      */     Reference2CharLinkedOpenHashMap c;
/*      */     try
/*      */     {
/* 1194 */       c = (Reference2CharLinkedOpenHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1197 */       throw new InternalError();
/*      */     }
/* 1199 */     c.keys = null;
/* 1200 */     c.values = null;
/* 1201 */     c.entries = null;
/* 1202 */     c.key = ((Object[])this.key.clone());
/* 1203 */     c.value = ((char[])this.value.clone());
/* 1204 */     c.used = ((boolean[])this.used.clone());
/* 1205 */     c.link = ((long[])this.link.clone());
/* 1206 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1218 */     int h = 0;
/* 1219 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1220 */       while (this.used[i] == 0) i++;
/* 1221 */       if (this != this.key[i])
/* 1222 */         t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 1223 */       t ^= this.value[i];
/* 1224 */       h += t;
/* 1225 */       i++;
/*      */     }
/* 1227 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1230 */     Object[] key = this.key;
/* 1231 */     char[] value = this.value;
/* 1232 */     MapIterator i = new MapIterator(null);
/* 1233 */     s.defaultWriteObject();
/* 1234 */     for (int j = this.size; j-- != 0; ) {
/* 1235 */       int e = i.nextEntry();
/* 1236 */       s.writeObject(key[e]);
/* 1237 */       s.writeChar(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1242 */     s.defaultReadObject();
/* 1243 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1244 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1245 */     this.mask = (this.n - 1);
/* 1246 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 1247 */     char[] value = this.value = new char[this.n];
/* 1248 */     boolean[] used = this.used = new boolean[this.n];
/* 1249 */     long[] link = this.link = new long[this.n];
/* 1250 */     int prev = -1;
/* 1251 */     this.first = (this.last = -1);
/*      */ 
/* 1254 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1255 */       Object k = s.readObject();
/* 1256 */       char v = s.readChar();
/* 1257 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 1258 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1259 */       used[pos] = true;
/* 1260 */       key[pos] = k;
/* 1261 */       value[pos] = v;
/* 1262 */       if (this.first != -1) {
/* 1263 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1264 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1265 */         prev = pos;
/*      */       }
/*      */       else {
/* 1268 */         prev = this.first = pos;
/*      */ 
/* 1270 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1273 */     this.last = prev;
/* 1274 */     if (prev != -1)
/*      */     {
/* 1276 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Reference2CharLinkedOpenHashMap.MapIterator
/*      */     implements CharListIterator
/*      */   {
/*      */     public char previousChar()
/*      */     {
/* 1039 */       return Reference2CharLinkedOpenHashMap.this.value[previousEntry()]; } 
/* 1040 */     public Character previous() { return Character.valueOf(Reference2CharLinkedOpenHashMap.this.value[previousEntry()]); } 
/* 1041 */     public void set(Character ok) { throw new UnsupportedOperationException(); } 
/* 1042 */     public void add(Character ok) { throw new UnsupportedOperationException(); } 
/* 1043 */     public void set(char v) { throw new UnsupportedOperationException(); } 
/* 1044 */     public void add(char v) { throw new UnsupportedOperationException(); } 
/* 1045 */     public ValueIterator() { super(null); } 
/* 1046 */     public char nextChar() { return Reference2CharLinkedOpenHashMap.this.value[nextEntry()]; } 
/* 1047 */     public Character next() { return Character.valueOf(Reference2CharLinkedOpenHashMap.this.value[nextEntry()]); }
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
/*  996 */       return new Reference2CharLinkedOpenHashMap.KeyIterator(Reference2CharLinkedOpenHashMap.this, from);
/*      */     }
/*      */     public ObjectListIterator<K> iterator() {
/*  999 */       return new Reference2CharLinkedOpenHashMap.KeyIterator(Reference2CharLinkedOpenHashMap.this);
/*      */     }
/*      */     public int size() {
/* 1002 */       return Reference2CharLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public boolean contains(Object k) {
/* 1005 */       return Reference2CharLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(Object k) {
/* 1008 */       int oldSize = Reference2CharLinkedOpenHashMap.this.size;
/* 1009 */       Reference2CharLinkedOpenHashMap.this.remove(k);
/* 1010 */       return Reference2CharLinkedOpenHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/* 1013 */       Reference2CharLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public K first() {
/* 1016 */       if (Reference2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1017 */       return Reference2CharLinkedOpenHashMap.this.key[Reference2CharLinkedOpenHashMap.this.first];
/*      */     }
/*      */     public K last() {
/* 1020 */       if (Reference2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1021 */       return Reference2CharLinkedOpenHashMap.this.key[Reference2CharLinkedOpenHashMap.this.last];
/*      */     }
/* 1023 */     public Comparator<? super K> comparator() { return null; } 
/* 1024 */     public final ReferenceSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/* 1025 */     public final ReferenceSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/* 1026 */     public final ReferenceSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Reference2CharLinkedOpenHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/*  987 */       super(k, null); } 
/*  988 */     public K previous() { return Reference2CharLinkedOpenHashMap.this.key[previousEntry()]; } 
/*  989 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/*  990 */     public void add(K k) { throw new UnsupportedOperationException(); } 
/*  991 */     public KeyIterator() { super(null); } 
/*  992 */     public K next() { return Reference2CharLinkedOpenHashMap.this.key[nextEntry()]; }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Reference2CharMap.Entry<K>>
/*      */     implements Reference2CharSortedMap.FastSortedEntrySet<K>
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Reference2CharMap.Entry<K>> iterator()
/*      */     {
/*  915 */       return new Reference2CharLinkedOpenHashMap.EntryIterator(Reference2CharLinkedOpenHashMap.this);
/*      */     }
/*  917 */     public Comparator<? super Reference2CharMap.Entry<K>> comparator() { return null; } 
/*  918 */     public ObjectSortedSet<Reference2CharMap.Entry<K>> subSet(Reference2CharMap.Entry<K> fromElement, Reference2CharMap.Entry<K> toElement) { throw new UnsupportedOperationException(); } 
/*  919 */     public ObjectSortedSet<Reference2CharMap.Entry<K>> headSet(Reference2CharMap.Entry<K> toElement) { throw new UnsupportedOperationException(); } 
/*  920 */     public ObjectSortedSet<Reference2CharMap.Entry<K>> tailSet(Reference2CharMap.Entry<K> fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Reference2CharMap.Entry<K> first() {
/*  922 */       if (Reference2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  923 */       return new Reference2CharLinkedOpenHashMap.MapEntry(Reference2CharLinkedOpenHashMap.this, Reference2CharLinkedOpenHashMap.this.first);
/*      */     }
/*      */     public Reference2CharMap.Entry<K> last() {
/*  926 */       if (Reference2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  927 */       return new Reference2CharLinkedOpenHashMap.MapEntry(Reference2CharLinkedOpenHashMap.this, Reference2CharLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  931 */       if (!(o instanceof Map.Entry)) return false;
/*  932 */       Map.Entry e = (Map.Entry)o;
/*  933 */       Object k = e.getKey();
/*      */ 
/*  935 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2CharLinkedOpenHashMap.this.mask;
/*      */ 
/*  937 */       while (Reference2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  938 */         if (Reference2CharLinkedOpenHashMap.this.key[pos] == k) return Reference2CharLinkedOpenHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/*  939 */         pos = pos + 1 & Reference2CharLinkedOpenHashMap.this.mask;
/*      */       }
/*  941 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  945 */       if (!(o instanceof Map.Entry)) return false;
/*  946 */       Map.Entry e = (Map.Entry)o;
/*  947 */       Object k = e.getKey();
/*      */ 
/*  949 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2CharLinkedOpenHashMap.this.mask;
/*      */ 
/*  951 */       while (Reference2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  952 */         if (Reference2CharLinkedOpenHashMap.this.key[pos] == k) {
/*  953 */           Reference2CharLinkedOpenHashMap.this.remove(e.getKey());
/*  954 */           return true;
/*      */         }
/*  956 */         pos = pos + 1 & Reference2CharLinkedOpenHashMap.this.mask;
/*      */       }
/*  958 */       return false;
/*      */     }
/*      */     public int size() {
/*  961 */       return Reference2CharLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/*  964 */       Reference2CharLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Reference2CharMap.Entry<K>> iterator(Reference2CharMap.Entry<K> from) {
/*  967 */       return new Reference2CharLinkedOpenHashMap.EntryIterator(Reference2CharLinkedOpenHashMap.this, from.getKey());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Reference2CharMap.Entry<K>> fastIterator() {
/*  970 */       return new Reference2CharLinkedOpenHashMap.FastEntryIterator(Reference2CharLinkedOpenHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Reference2CharMap.Entry<K>> fastIterator(Reference2CharMap.Entry<K> from) {
/*  973 */       return new Reference2CharLinkedOpenHashMap.FastEntryIterator(Reference2CharLinkedOpenHashMap.this, from.getKey());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Reference2CharLinkedOpenHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<Reference2CharMap.Entry<K>>
/*      */   {
/*  893 */     final AbstractReference2CharMap.BasicEntry<K> entry = new AbstractReference2CharMap.BasicEntry(null, '\000');
/*      */ 
/*  894 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator() {
/*  896 */       super(from, null);
/*      */     }
/*      */     public AbstractReference2CharMap.BasicEntry<K> next() {
/*  899 */       int e = nextEntry();
/*  900 */       this.entry.key = Reference2CharLinkedOpenHashMap.this.key[e];
/*  901 */       this.entry.value = Reference2CharLinkedOpenHashMap.this.value[e];
/*  902 */       return this.entry;
/*      */     }
/*      */     public AbstractReference2CharMap.BasicEntry<K> previous() {
/*  905 */       int e = previousEntry();
/*  906 */       this.entry.key = Reference2CharLinkedOpenHashMap.this.key[e];
/*  907 */       this.entry.value = Reference2CharLinkedOpenHashMap.this.value[e];
/*  908 */       return this.entry;
/*      */     }
/*  910 */     public void set(Reference2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  911 */     public void add(Reference2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Reference2CharLinkedOpenHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<Reference2CharMap.Entry<K>>
/*      */   {
/*      */     private Reference2CharLinkedOpenHashMap<K>.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  874 */       super(null);
/*      */     }
/*  876 */     public EntryIterator() { super(from, null); }
/*      */ 
/*      */     public Reference2CharLinkedOpenHashMap<K>.MapEntry next() {
/*  879 */       return this.entry = new Reference2CharLinkedOpenHashMap.MapEntry(Reference2CharLinkedOpenHashMap.this, nextEntry());
/*      */     }
/*      */     public Reference2CharLinkedOpenHashMap<K>.MapEntry previous() {
/*  882 */       return this.entry = new Reference2CharLinkedOpenHashMap.MapEntry(Reference2CharLinkedOpenHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  886 */       super.remove();
/*  887 */       Reference2CharLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  889 */     public void set(Reference2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  890 */     public void add(Reference2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  745 */     int prev = -1;
/*      */ 
/*  747 */     int next = -1;
/*      */ 
/*  749 */     int curr = -1;
/*      */ 
/*  751 */     int index = -1;
/*      */ 
/*  753 */     private MapIterator() { this.next = Reference2CharLinkedOpenHashMap.this.first;
/*  754 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator() {
/*  757 */       if (Reference2CharLinkedOpenHashMap.this.key[Reference2CharLinkedOpenHashMap.this.last] == from) {
/*  758 */         this.prev = Reference2CharLinkedOpenHashMap.this.last;
/*  759 */         this.index = Reference2CharLinkedOpenHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  763 */         int pos = (from == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(from))) & Reference2CharLinkedOpenHashMap.this.mask;
/*      */ 
/*  765 */         while (Reference2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  766 */           if (Reference2CharLinkedOpenHashMap.this.key[pos] == from)
/*      */           {
/*  768 */             this.next = ((int)Reference2CharLinkedOpenHashMap.this.link[pos]);
/*  769 */             this.prev = pos;
/*  770 */             return;
/*      */           }
/*  772 */           pos = pos + 1 & Reference2CharLinkedOpenHashMap.this.mask;
/*      */         }
/*  774 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  777 */     public boolean hasNext() { return this.next != -1; } 
/*  778 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  780 */       if (this.index >= 0) return;
/*  781 */       if (this.prev == -1) {
/*  782 */         this.index = 0;
/*  783 */         return;
/*      */       }
/*  785 */       if (this.next == -1) {
/*  786 */         this.index = Reference2CharLinkedOpenHashMap.this.size;
/*  787 */         return;
/*      */       }
/*  789 */       int pos = Reference2CharLinkedOpenHashMap.this.first;
/*  790 */       this.index = 1;
/*  791 */       while (pos != this.prev) {
/*  792 */         pos = (int)Reference2CharLinkedOpenHashMap.this.link[pos];
/*  793 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  797 */     public int nextIndex() { ensureIndexKnown();
/*  798 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  801 */       ensureIndexKnown();
/*  802 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  805 */       if (!hasNext()) return Reference2CharLinkedOpenHashMap.this.size();
/*  806 */       this.curr = this.next;
/*  807 */       this.next = ((int)Reference2CharLinkedOpenHashMap.this.link[this.curr]);
/*  808 */       this.prev = this.curr;
/*  809 */       if (this.index >= 0) this.index += 1;
/*  810 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  813 */       if (!hasPrevious()) return -1;
/*  814 */       this.curr = this.prev;
/*  815 */       this.prev = ((int)(Reference2CharLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  816 */       this.next = this.curr;
/*  817 */       if (this.index >= 0) this.index -= 1;
/*  818 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  822 */       ensureIndexKnown();
/*  823 */       if (this.curr == -1) throw new IllegalStateException();
/*  824 */       if (this.curr == this.prev)
/*      */       {
/*  827 */         this.index -= 1;
/*  828 */         this.prev = ((int)(Reference2CharLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  831 */         this.next = ((int)Reference2CharLinkedOpenHashMap.this.link[this.curr]);
/*  832 */       }Reference2CharLinkedOpenHashMap.this.size -= 1;
/*      */ 
/*  835 */       if (this.prev == -1) Reference2CharLinkedOpenHashMap.this.first = this.next;
/*      */       else
/*  837 */         Reference2CharLinkedOpenHashMap.this.link[this.prev] ^= (Reference2CharLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  838 */       if (this.next == -1) Reference2CharLinkedOpenHashMap.this.last = this.prev;
/*      */       else
/*  840 */         Reference2CharLinkedOpenHashMap.this.link[this.next] ^= (Reference2CharLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  844 */         pos = (last = pos) + 1 & Reference2CharLinkedOpenHashMap.this.mask;
/*  845 */         while (Reference2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  846 */           int slot = (Reference2CharLinkedOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2CharLinkedOpenHashMap.this.key[pos]))) & Reference2CharLinkedOpenHashMap.this.mask;
/*  847 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  848 */           pos = pos + 1 & Reference2CharLinkedOpenHashMap.this.mask;
/*      */         }
/*  850 */         if (Reference2CharLinkedOpenHashMap.this.used[pos] == 0) break;
/*  851 */         Reference2CharLinkedOpenHashMap.this.key[last] = Reference2CharLinkedOpenHashMap.this.key[pos];
/*  852 */         Reference2CharLinkedOpenHashMap.this.value[last] = Reference2CharLinkedOpenHashMap.this.value[pos];
/*  853 */         if (this.next == pos) this.next = last;
/*  854 */         if (this.prev == pos) this.prev = last;
/*  855 */         Reference2CharLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */       }
/*  857 */       Reference2CharLinkedOpenHashMap.this.used[last] = false;
/*  858 */       Reference2CharLinkedOpenHashMap.this.key[last] = null;
/*  859 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  862 */       int i = n;
/*  863 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  864 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  867 */       int i = n;
/*  868 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  869 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Reference2CharMap.Entry<K>, Map.Entry<K, Character>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  617 */       this.index = index;
/*      */     }
/*      */     public K getKey() {
/*  620 */       return Reference2CharLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     public Character getValue() {
/*  623 */       return Character.valueOf(Reference2CharLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */     public char getCharValue() {
/*  626 */       return Reference2CharLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public char setValue(char v) {
/*  629 */       char oldValue = Reference2CharLinkedOpenHashMap.this.value[this.index];
/*  630 */       Reference2CharLinkedOpenHashMap.this.value[this.index] = v;
/*  631 */       return oldValue;
/*      */     }
/*      */     public Character setValue(Character v) {
/*  634 */       return Character.valueOf(setValue(v.charValue()));
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  638 */       if (!(o instanceof Map.Entry)) return false;
/*  639 */       Map.Entry e = (Map.Entry)o;
/*  640 */       return (Reference2CharLinkedOpenHashMap.this.key[this.index] == e.getKey()) && (Reference2CharLinkedOpenHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/*      */     }
/*      */     public int hashCode() {
/*  643 */       return (Reference2CharLinkedOpenHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2CharLinkedOpenHashMap.this.key[this.index])) ^ Reference2CharLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public String toString() {
/*  646 */       return Reference2CharLinkedOpenHashMap.this.key[this.index] + "=>" + Reference2CharLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2CharLinkedOpenHashMap
 * JD-Core Version:    0.6.2
 */