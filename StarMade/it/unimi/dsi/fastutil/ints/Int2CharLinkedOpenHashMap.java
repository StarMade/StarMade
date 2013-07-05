/*      */ package it.unimi.dsi.fastutil.ints;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*      */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*      */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*      */ import it.unimi.dsi.fastutil.chars.CharListIterator;
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
/*      */ public class Int2CharLinkedOpenHashMap extends AbstractInt2CharSortedMap
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   public static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient int[] key;
/*      */   protected transient char[] value;
/*      */   protected transient boolean[] used;
/*      */   protected final float f;
/*      */   protected transient int n;
/*      */   protected transient int maxFill;
/*      */   protected transient int mask;
/*      */   protected int size;
/*      */   protected volatile transient Int2CharSortedMap.FastSortedEntrySet entries;
/*      */   protected volatile transient IntSortedSet keys;
/*      */   protected volatile transient CharCollection values;
/*  130 */   protected transient int first = -1;
/*      */ 
/*  132 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(int expected, float f)
/*      */   {
/*  151 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153 */     this.f = f;
/*  154 */     this.n = HashCommon.arraySize(expected, f);
/*  155 */     this.mask = (this.n - 1);
/*  156 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  157 */     this.key = new int[this.n];
/*  158 */     this.value = new char[this.n];
/*  159 */     this.used = new boolean[this.n];
/*  160 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(int expected)
/*      */   {
/*  167 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap()
/*      */   {
/*  173 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(Map<? extends Integer, ? extends Character> m, float f)
/*      */   {
/*  181 */     this(m.size(), f);
/*  182 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(Map<? extends Integer, ? extends Character> m)
/*      */   {
/*  189 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(Int2CharMap m, float f)
/*      */   {
/*  197 */     this(m.size(), f);
/*  198 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(Int2CharMap m)
/*      */   {
/*  205 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(int[] k, char[] v, float f)
/*      */   {
/*  215 */     this(k.length, f);
/*  216 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap(int[] k, char[] v)
/*      */   {
/*  226 */     this(k, v, 0.75F);
/*      */   }
/*      */ 
/*      */   public char put(int k, char v)
/*      */   {
/*  234 */     int pos = HashCommon.murmurHash3(k) & this.mask;
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
/*      */   public Character put(Integer ok, Character ov) {
/*  262 */     char v = ov.charValue();
/*  263 */     int k = ok.intValue();
/*      */ 
/*  265 */     int pos = HashCommon.murmurHash3(k) & this.mask;
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
/*  304 */         int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/*  305 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  306 */         pos = pos + 1 & this.mask;
/*      */       }
/*  308 */       if (this.used[pos] == 0) break;
/*  309 */       this.key[last] = this.key[pos];
/*  310 */       this.value[last] = this.value[pos];
/*  311 */       fixPointers(pos, last);
/*      */     }
/*  313 */     this.used[last] = false;
/*  314 */     return last;
/*      */   }
/*      */ 
/*      */   public char remove(int k)
/*      */   {
/*  319 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  321 */     while (this.used[pos] != 0) {
/*  322 */       if (this.key[pos] == k) {
/*  323 */         this.size -= 1;
/*  324 */         fixPointers(pos);
/*  325 */         char v = this.value[pos];
/*  326 */         shiftKeys(pos);
/*  327 */         return v;
/*      */       }
/*  329 */       pos = pos + 1 & this.mask;
/*      */     }
/*  331 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public Character remove(Object ok) {
/*  335 */     int k = ((Integer)ok).intValue();
/*      */ 
/*  337 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  339 */     while (this.used[pos] != 0) {
/*  340 */       if (this.key[pos] == k) {
/*  341 */         this.size -= 1;
/*  342 */         fixPointers(pos);
/*  343 */         char v = this.value[pos];
/*  344 */         shiftKeys(pos);
/*  345 */         return Character.valueOf(v);
/*      */       }
/*  347 */       pos = pos + 1 & this.mask;
/*      */     }
/*  349 */     return null;
/*      */   }
/*      */ 
/*      */   public char removeFirstChar()
/*      */   {
/*  356 */     if (this.size == 0) throw new NoSuchElementException();
/*  357 */     this.size -= 1;
/*  358 */     int pos = this.first;
/*      */ 
/*  360 */     this.first = ((int)this.link[pos]);
/*  361 */     if (0 <= this.first)
/*      */     {
/*  363 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  365 */     char v = this.value[pos];
/*  366 */     shiftKeys(pos);
/*  367 */     return v;
/*      */   }
/*      */ 
/*      */   public char removeLastChar()
/*      */   {
/*  374 */     if (this.size == 0) throw new NoSuchElementException();
/*  375 */     this.size -= 1;
/*  376 */     int pos = this.last;
/*      */ 
/*  378 */     this.last = ((int)(this.link[pos] >>> 32));
/*  379 */     if (0 <= this.last)
/*      */     {
/*  381 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  383 */     char v = this.value[pos];
/*  384 */     shiftKeys(pos);
/*  385 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  388 */     if ((this.size == 1) || (this.first == i)) return;
/*  389 */     if (this.last == i) {
/*  390 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  392 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  395 */       long linki = this.link[i];
/*  396 */       int prev = (int)(linki >>> 32);
/*  397 */       int next = (int)linki;
/*  398 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  399 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  401 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  402 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  403 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  406 */     if ((this.size == 1) || (this.last == i)) return;
/*  407 */     if (this.first == i) {
/*  408 */       this.first = ((int)this.link[i]);
/*      */ 
/*  410 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  413 */       long linki = this.link[i];
/*  414 */       int prev = (int)(linki >>> 32);
/*  415 */       int next = (int)linki;
/*  416 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  417 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  419 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  420 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  421 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public char getAndMoveToFirst(int k)
/*      */   {
/*  429 */     int[] key = this.key;
/*  430 */     boolean[] used = this.used;
/*  431 */     int mask = this.mask;
/*      */ 
/*  433 */     int pos = HashCommon.murmurHash3(k) & mask;
/*      */ 
/*  435 */     while (used[pos] != 0) {
/*  436 */       if (k == key[pos]) {
/*  437 */         moveIndexToFirst(pos);
/*  438 */         return this.value[pos];
/*      */       }
/*  440 */       pos = pos + 1 & mask;
/*      */     }
/*  442 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char getAndMoveToLast(int k)
/*      */   {
/*  450 */     int[] key = this.key;
/*  451 */     boolean[] used = this.used;
/*  452 */     int mask = this.mask;
/*      */ 
/*  454 */     int pos = HashCommon.murmurHash3(k) & mask;
/*      */ 
/*  456 */     while (used[pos] != 0) {
/*  457 */       if (k == key[pos]) {
/*  458 */         moveIndexToLast(pos);
/*  459 */         return this.value[pos];
/*      */       }
/*  461 */       pos = pos + 1 & mask;
/*      */     }
/*  463 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char putAndMoveToFirst(int k, char v)
/*      */   {
/*  472 */     int[] key = this.key;
/*  473 */     boolean[] used = this.used;
/*  474 */     int mask = this.mask;
/*      */ 
/*  476 */     int pos = HashCommon.murmurHash3(k) & mask;
/*      */ 
/*  478 */     while (used[pos] != 0) {
/*  479 */       if (k == key[pos]) {
/*  480 */         char oldValue = this.value[pos];
/*  481 */         this.value[pos] = v;
/*  482 */         moveIndexToFirst(pos);
/*  483 */         return oldValue;
/*      */       }
/*  485 */       pos = pos + 1 & mask;
/*      */     }
/*  487 */     used[pos] = true;
/*  488 */     key[pos] = k;
/*  489 */     this.value[pos] = v;
/*  490 */     if (this.size == 0) {
/*  491 */       this.first = (this.last = pos);
/*      */ 
/*  493 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  496 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  497 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  498 */       this.first = pos;
/*      */     }
/*  500 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  502 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char putAndMoveToLast(int k, char v)
/*      */   {
/*  511 */     int[] key = this.key;
/*  512 */     boolean[] used = this.used;
/*  513 */     int mask = this.mask;
/*      */ 
/*  515 */     int pos = HashCommon.murmurHash3(k) & mask;
/*      */ 
/*  517 */     while (used[pos] != 0) {
/*  518 */       if (k == key[pos]) {
/*  519 */         char oldValue = this.value[pos];
/*  520 */         this.value[pos] = v;
/*  521 */         moveIndexToLast(pos);
/*  522 */         return oldValue;
/*      */       }
/*  524 */       pos = pos + 1 & mask;
/*      */     }
/*  526 */     used[pos] = true;
/*  527 */     key[pos] = k;
/*  528 */     this.value[pos] = v;
/*  529 */     if (this.size == 0) {
/*  530 */       this.first = (this.last = pos);
/*      */ 
/*  532 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  535 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  536 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  537 */       this.last = pos;
/*      */     }
/*  539 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  541 */     return this.defRetValue;
/*      */   }
/*      */   public Character get(Integer ok) {
/*  544 */     int k = ok.intValue();
/*      */ 
/*  546 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  548 */     while (this.used[pos] != 0) {
/*  549 */       if (this.key[pos] == k) return Character.valueOf(this.value[pos]);
/*  550 */       pos = pos + 1 & this.mask;
/*      */     }
/*  552 */     return null;
/*      */   }
/*      */ 
/*      */   public char get(int k)
/*      */   {
/*  557 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  559 */     while (this.used[pos] != 0) {
/*  560 */       if (this.key[pos] == k) return this.value[pos];
/*  561 */       pos = pos + 1 & this.mask;
/*      */     }
/*  563 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(int k)
/*      */   {
/*  568 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  570 */     while (this.used[pos] != 0) {
/*  571 */       if (this.key[pos] == k) return true;
/*  572 */       pos = pos + 1 & this.mask;
/*      */     }
/*  574 */     return false;
/*      */   }
/*      */   public boolean containsValue(char v) {
/*  577 */     char[] value = this.value;
/*  578 */     boolean[] used = this.used;
/*  579 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*      */         break label16; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  589 */     if (this.size == 0) return;
/*  590 */     this.size = 0;
/*  591 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  593 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  596 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  599 */     return this.size == 0;
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
/*  616 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  669 */     if (this.size == 0) {
/*  670 */       this.first = (this.last = -1);
/*  671 */       return;
/*      */     }
/*  673 */     if (this.first == i) {
/*  674 */       this.first = ((int)this.link[i]);
/*  675 */       if (0 <= this.first)
/*      */       {
/*  677 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  679 */       return;
/*      */     }
/*  681 */     if (this.last == i) {
/*  682 */       this.last = ((int)(this.link[i] >>> 32));
/*  683 */       if (0 <= this.last)
/*      */       {
/*  685 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  687 */       return;
/*      */     }
/*  689 */     long linki = this.link[i];
/*  690 */     int prev = (int)(linki >>> 32);
/*  691 */     int next = (int)linki;
/*  692 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  693 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  704 */     if (this.size == 1) {
/*  705 */       this.first = (this.last = d);
/*      */ 
/*  707 */       this.link[d] = -1L;
/*  708 */       return;
/*      */     }
/*  710 */     if (this.first == s) {
/*  711 */       this.first = d;
/*  712 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  713 */       this.link[d] = this.link[s];
/*  714 */       return;
/*      */     }
/*  716 */     if (this.last == s) {
/*  717 */       this.last = d;
/*  718 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  719 */       this.link[d] = this.link[s];
/*  720 */       return;
/*      */     }
/*  722 */     long links = this.link[s];
/*  723 */     int prev = (int)(links >>> 32);
/*  724 */     int next = (int)links;
/*  725 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  726 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  727 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public int firstIntKey()
/*      */   {
/*  734 */     if (this.size == 0) throw new NoSuchElementException();
/*  735 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public int lastIntKey()
/*      */   {
/*  742 */     if (this.size == 0) throw new NoSuchElementException();
/*  743 */     return this.key[this.last];
/*      */   }
/*  745 */   public IntComparator comparator() { return null; } 
/*  746 */   public Int2CharSortedMap tailMap(int from) { throw new UnsupportedOperationException(); } 
/*  747 */   public Int2CharSortedMap headMap(int to) { throw new UnsupportedOperationException(); } 
/*  748 */   public Int2CharSortedMap subMap(int from, int to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Int2CharSortedMap.FastSortedEntrySet int2CharEntrySet()
/*      */   {
/*  988 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/*  989 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public IntSortedSet keySet()
/*      */   {
/* 1044 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1045 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public CharCollection values()
/*      */   {
/* 1065 */     if (this.values == null) this.values = new AbstractCharCollection() {
/*      */         public CharIterator iterator() {
/* 1067 */           return new Int2CharLinkedOpenHashMap.ValueIterator(Int2CharLinkedOpenHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1070 */           return Int2CharLinkedOpenHashMap.this.size;
/*      */         }
/*      */         public boolean contains(char v) {
/* 1073 */           return Int2CharLinkedOpenHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1076 */           Int2CharLinkedOpenHashMap.this.clear();
/*      */         }
/*      */       };
/* 1079 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1093 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1108 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1109 */     if (l >= this.n) return true; try
/*      */     {
/* 1111 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1113 */       return false;
/* 1114 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1135 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1136 */     if (this.n <= l) return true; try
/*      */     {
/* 1138 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1140 */       return false;
/* 1141 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1154 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1156 */     int[] key = this.key;
/* 1157 */     char[] value = this.value;
/* 1158 */     int newMask = newN - 1;
/* 1159 */     int[] newKey = new int[newN];
/* 1160 */     char[] newValue = new char[newN];
/* 1161 */     boolean[] newUsed = new boolean[newN];
/* 1162 */     long[] link = this.link;
/* 1163 */     long[] newLink = new long[newN];
/* 1164 */     this.first = -1;
/* 1165 */     for (int j = this.size; j-- != 0; ) {
/* 1166 */       int k = key[i];
/* 1167 */       int pos = HashCommon.murmurHash3(k) & newMask;
/* 1168 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1169 */       newUsed[pos] = true;
/* 1170 */       newKey[pos] = k;
/* 1171 */       newValue[pos] = value[i];
/* 1172 */       if (prev != -1) {
/* 1173 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1174 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1175 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1178 */         newPrev = this.first = pos;
/*      */ 
/* 1180 */         newLink[pos] = -1L;
/*      */       }
/* 1182 */       int t = i;
/* 1183 */       i = (int)link[i];
/* 1184 */       prev = t;
/*      */     }
/* 1186 */     this.n = newN;
/* 1187 */     this.mask = newMask;
/* 1188 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1189 */     this.key = newKey;
/* 1190 */     this.value = newValue;
/* 1191 */     this.used = newUsed;
/* 1192 */     this.link = newLink;
/* 1193 */     this.last = newPrev;
/* 1194 */     if (newPrev != -1)
/*      */     {
/* 1196 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Int2CharLinkedOpenHashMap clone()
/*      */   {
/*      */     Int2CharLinkedOpenHashMap c;
/*      */     try
/*      */     {
/* 1209 */       c = (Int2CharLinkedOpenHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1212 */       throw new InternalError();
/*      */     }
/* 1214 */     c.keys = null;
/* 1215 */     c.values = null;
/* 1216 */     c.entries = null;
/* 1217 */     c.key = ((int[])this.key.clone());
/* 1218 */     c.value = ((char[])this.value.clone());
/* 1219 */     c.used = ((boolean[])this.used.clone());
/* 1220 */     c.link = ((long[])this.link.clone());
/* 1221 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1233 */     int h = 0;
/* 1234 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1235 */       while (this.used[i] == 0) i++;
/* 1236 */       t = this.key[i];
/* 1237 */       t ^= this.value[i];
/* 1238 */       h += t;
/* 1239 */       i++;
/*      */     }
/* 1241 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1244 */     int[] key = this.key;
/* 1245 */     char[] value = this.value;
/* 1246 */     MapIterator i = new MapIterator(null);
/* 1247 */     s.defaultWriteObject();
/* 1248 */     for (int j = this.size; j-- != 0; ) {
/* 1249 */       int e = i.nextEntry();
/* 1250 */       s.writeInt(key[e]);
/* 1251 */       s.writeChar(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1256 */     s.defaultReadObject();
/* 1257 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1258 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1259 */     this.mask = (this.n - 1);
/* 1260 */     int[] key = this.key = new int[this.n];
/* 1261 */     char[] value = this.value = new char[this.n];
/* 1262 */     boolean[] used = this.used = new boolean[this.n];
/* 1263 */     long[] link = this.link = new long[this.n];
/* 1264 */     int prev = -1;
/* 1265 */     this.first = (this.last = -1);
/*      */ 
/* 1268 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1269 */       int k = s.readInt();
/* 1270 */       char v = s.readChar();
/* 1271 */       pos = HashCommon.murmurHash3(k) & this.mask;
/* 1272 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1273 */       used[pos] = true;
/* 1274 */       key[pos] = k;
/* 1275 */       value[pos] = v;
/* 1276 */       if (this.first != -1) {
/* 1277 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1278 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1279 */         prev = pos;
/*      */       }
/*      */       else {
/* 1282 */         prev = this.first = pos;
/*      */ 
/* 1284 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1287 */     this.last = prev;
/* 1288 */     if (prev != -1)
/*      */     {
/* 1290 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Int2CharLinkedOpenHashMap.MapIterator
/*      */     implements CharListIterator
/*      */   {
/*      */     public char previousChar()
/*      */     {
/* 1054 */       return Int2CharLinkedOpenHashMap.this.value[previousEntry()]; } 
/* 1055 */     public Character previous() { return Character.valueOf(Int2CharLinkedOpenHashMap.this.value[previousEntry()]); } 
/* 1056 */     public void set(Character ok) { throw new UnsupportedOperationException(); } 
/* 1057 */     public void add(Character ok) { throw new UnsupportedOperationException(); } 
/* 1058 */     public void set(char v) { throw new UnsupportedOperationException(); } 
/* 1059 */     public void add(char v) { throw new UnsupportedOperationException(); } 
/* 1060 */     public ValueIterator() { super(null); } 
/* 1061 */     public char nextChar() { return Int2CharLinkedOpenHashMap.this.value[nextEntry()]; } 
/* 1062 */     public Character next() { return Character.valueOf(Int2CharLinkedOpenHashMap.this.value[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeySet extends AbstractIntSortedSet
/*      */   {
/*      */     private KeySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public IntListIterator iterator(int from)
/*      */     {
/* 1011 */       return new Int2CharLinkedOpenHashMap.KeyIterator(Int2CharLinkedOpenHashMap.this, from);
/*      */     }
/*      */     public IntListIterator iterator() {
/* 1014 */       return new Int2CharLinkedOpenHashMap.KeyIterator(Int2CharLinkedOpenHashMap.this);
/*      */     }
/*      */     public int size() {
/* 1017 */       return Int2CharLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public boolean contains(int k) {
/* 1020 */       return Int2CharLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(int k) {
/* 1023 */       int oldSize = Int2CharLinkedOpenHashMap.this.size;
/* 1024 */       Int2CharLinkedOpenHashMap.this.remove(k);
/* 1025 */       return Int2CharLinkedOpenHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/* 1028 */       Int2CharLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public int firstInt() {
/* 1031 */       if (Int2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1032 */       return Int2CharLinkedOpenHashMap.this.key[Int2CharLinkedOpenHashMap.this.first];
/*      */     }
/*      */     public int lastInt() {
/* 1035 */       if (Int2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1036 */       return Int2CharLinkedOpenHashMap.this.key[Int2CharLinkedOpenHashMap.this.last];
/*      */     }
/* 1038 */     public IntComparator comparator() { return null; } 
/* 1039 */     public final IntSortedSet tailSet(int from) { throw new UnsupportedOperationException(); } 
/* 1040 */     public final IntSortedSet headSet(int to) { throw new UnsupportedOperationException(); } 
/* 1041 */     public final IntSortedSet subSet(int from, int to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Int2CharLinkedOpenHashMap.MapIterator
/*      */     implements IntListIterator
/*      */   {
/*      */     public KeyIterator(int k)
/*      */     {
/*  998 */       super(k, null); } 
/*  999 */     public int previousInt() { return Int2CharLinkedOpenHashMap.this.key[previousEntry()]; } 
/* 1000 */     public void set(int k) { throw new UnsupportedOperationException(); } 
/* 1001 */     public void add(int k) { throw new UnsupportedOperationException(); } 
/* 1002 */     public Integer previous() { return Integer.valueOf(Int2CharLinkedOpenHashMap.this.key[previousEntry()]); } 
/* 1003 */     public void set(Integer ok) { throw new UnsupportedOperationException(); } 
/* 1004 */     public void add(Integer ok) { throw new UnsupportedOperationException(); } 
/* 1005 */     public KeyIterator() { super(null); } 
/* 1006 */     public int nextInt() { return Int2CharLinkedOpenHashMap.this.key[nextEntry()]; } 
/* 1007 */     public Integer next() { return Integer.valueOf(Int2CharLinkedOpenHashMap.this.key[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Int2CharMap.Entry>
/*      */     implements Int2CharSortedMap.FastSortedEntrySet
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Int2CharMap.Entry> iterator()
/*      */     {
/*  926 */       return new Int2CharLinkedOpenHashMap.EntryIterator(Int2CharLinkedOpenHashMap.this);
/*      */     }
/*  928 */     public Comparator<? super Int2CharMap.Entry> comparator() { return null; } 
/*  929 */     public ObjectSortedSet<Int2CharMap.Entry> subSet(Int2CharMap.Entry fromElement, Int2CharMap.Entry toElement) { throw new UnsupportedOperationException(); } 
/*  930 */     public ObjectSortedSet<Int2CharMap.Entry> headSet(Int2CharMap.Entry toElement) { throw new UnsupportedOperationException(); } 
/*  931 */     public ObjectSortedSet<Int2CharMap.Entry> tailSet(Int2CharMap.Entry fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Int2CharMap.Entry first() {
/*  933 */       if (Int2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  934 */       return new Int2CharLinkedOpenHashMap.MapEntry(Int2CharLinkedOpenHashMap.this, Int2CharLinkedOpenHashMap.this.first);
/*      */     }
/*      */     public Int2CharMap.Entry last() {
/*  937 */       if (Int2CharLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  938 */       return new Int2CharLinkedOpenHashMap.MapEntry(Int2CharLinkedOpenHashMap.this, Int2CharLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  942 */       if (!(o instanceof Map.Entry)) return false;
/*  943 */       Map.Entry e = (Map.Entry)o;
/*  944 */       int k = ((Integer)e.getKey()).intValue();
/*      */ 
/*  946 */       int pos = HashCommon.murmurHash3(k) & Int2CharLinkedOpenHashMap.this.mask;
/*      */ 
/*  948 */       while (Int2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  949 */         if (Int2CharLinkedOpenHashMap.this.key[pos] == k) return Int2CharLinkedOpenHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/*  950 */         pos = pos + 1 & Int2CharLinkedOpenHashMap.this.mask;
/*      */       }
/*  952 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  956 */       if (!(o instanceof Map.Entry)) return false;
/*  957 */       Map.Entry e = (Map.Entry)o;
/*  958 */       int k = ((Integer)e.getKey()).intValue();
/*      */ 
/*  960 */       int pos = HashCommon.murmurHash3(k) & Int2CharLinkedOpenHashMap.this.mask;
/*      */ 
/*  962 */       while (Int2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  963 */         if (Int2CharLinkedOpenHashMap.this.key[pos] == k) {
/*  964 */           Int2CharLinkedOpenHashMap.this.remove(e.getKey());
/*  965 */           return true;
/*      */         }
/*  967 */         pos = pos + 1 & Int2CharLinkedOpenHashMap.this.mask;
/*      */       }
/*  969 */       return false;
/*      */     }
/*      */     public int size() {
/*  972 */       return Int2CharLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/*  975 */       Int2CharLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Int2CharMap.Entry> iterator(Int2CharMap.Entry from) {
/*  978 */       return new Int2CharLinkedOpenHashMap.EntryIterator(Int2CharLinkedOpenHashMap.this, ((Integer)from.getKey()).intValue());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Int2CharMap.Entry> fastIterator() {
/*  981 */       return new Int2CharLinkedOpenHashMap.FastEntryIterator(Int2CharLinkedOpenHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Int2CharMap.Entry> fastIterator(Int2CharMap.Entry from) {
/*  984 */       return new Int2CharLinkedOpenHashMap.FastEntryIterator(Int2CharLinkedOpenHashMap.this, ((Integer)from.getKey()).intValue());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Int2CharLinkedOpenHashMap.MapIterator
/*      */     implements ObjectListIterator<Int2CharMap.Entry>
/*      */   {
/*  904 */     final AbstractInt2CharMap.BasicEntry entry = new AbstractInt2CharMap.BasicEntry(0, '\000');
/*      */ 
/*  905 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator(int from) {
/*  907 */       super(from, null);
/*      */     }
/*      */     public AbstractInt2CharMap.BasicEntry next() {
/*  910 */       int e = nextEntry();
/*  911 */       this.entry.key = Int2CharLinkedOpenHashMap.this.key[e];
/*  912 */       this.entry.value = Int2CharLinkedOpenHashMap.this.value[e];
/*  913 */       return this.entry;
/*      */     }
/*      */     public AbstractInt2CharMap.BasicEntry previous() {
/*  916 */       int e = previousEntry();
/*  917 */       this.entry.key = Int2CharLinkedOpenHashMap.this.key[e];
/*  918 */       this.entry.value = Int2CharLinkedOpenHashMap.this.value[e];
/*  919 */       return this.entry;
/*      */     }
/*  921 */     public void set(Int2CharMap.Entry ok) { throw new UnsupportedOperationException(); } 
/*  922 */     public void add(Int2CharMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Int2CharLinkedOpenHashMap.MapIterator
/*      */     implements ObjectListIterator<Int2CharMap.Entry>
/*      */   {
/*      */     private Int2CharLinkedOpenHashMap.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  885 */       super(null);
/*      */     }
/*  887 */     public EntryIterator(int from) { super(from, null); }
/*      */ 
/*      */     public Int2CharLinkedOpenHashMap.MapEntry next() {
/*  890 */       return this.entry = new Int2CharLinkedOpenHashMap.MapEntry(Int2CharLinkedOpenHashMap.this, nextEntry());
/*      */     }
/*      */     public Int2CharLinkedOpenHashMap.MapEntry previous() {
/*  893 */       return this.entry = new Int2CharLinkedOpenHashMap.MapEntry(Int2CharLinkedOpenHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  897 */       super.remove();
/*  898 */       Int2CharLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  900 */     public void set(Int2CharMap.Entry ok) { throw new UnsupportedOperationException(); } 
/*  901 */     public void add(Int2CharMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  757 */     int prev = -1;
/*      */ 
/*  759 */     int next = -1;
/*      */ 
/*  761 */     int curr = -1;
/*      */ 
/*  763 */     int index = -1;
/*      */ 
/*  765 */     private MapIterator() { this.next = Int2CharLinkedOpenHashMap.this.first;
/*  766 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator(int from) {
/*  769 */       if (Int2CharLinkedOpenHashMap.this.key[Int2CharLinkedOpenHashMap.this.last] == from) {
/*  770 */         this.prev = Int2CharLinkedOpenHashMap.this.last;
/*  771 */         this.index = Int2CharLinkedOpenHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  775 */         int pos = HashCommon.murmurHash3(from) & Int2CharLinkedOpenHashMap.this.mask;
/*      */ 
/*  777 */         while (Int2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  778 */           if (Int2CharLinkedOpenHashMap.this.key[pos] == from)
/*      */           {
/*  780 */             this.next = ((int)Int2CharLinkedOpenHashMap.this.link[pos]);
/*  781 */             this.prev = pos;
/*  782 */             return;
/*      */           }
/*  784 */           pos = pos + 1 & Int2CharLinkedOpenHashMap.this.mask;
/*      */         }
/*  786 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  789 */     public boolean hasNext() { return this.next != -1; } 
/*  790 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  792 */       if (this.index >= 0) return;
/*  793 */       if (this.prev == -1) {
/*  794 */         this.index = 0;
/*  795 */         return;
/*      */       }
/*  797 */       if (this.next == -1) {
/*  798 */         this.index = Int2CharLinkedOpenHashMap.this.size;
/*  799 */         return;
/*      */       }
/*  801 */       int pos = Int2CharLinkedOpenHashMap.this.first;
/*  802 */       this.index = 1;
/*  803 */       while (pos != this.prev) {
/*  804 */         pos = (int)Int2CharLinkedOpenHashMap.this.link[pos];
/*  805 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  809 */     public int nextIndex() { ensureIndexKnown();
/*  810 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  813 */       ensureIndexKnown();
/*  814 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  817 */       if (!hasNext()) return Int2CharLinkedOpenHashMap.this.size();
/*  818 */       this.curr = this.next;
/*  819 */       this.next = ((int)Int2CharLinkedOpenHashMap.this.link[this.curr]);
/*  820 */       this.prev = this.curr;
/*  821 */       if (this.index >= 0) this.index += 1;
/*  822 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  825 */       if (!hasPrevious()) return -1;
/*  826 */       this.curr = this.prev;
/*  827 */       this.prev = ((int)(Int2CharLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*  828 */       this.next = this.curr;
/*  829 */       if (this.index >= 0) this.index -= 1;
/*  830 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  834 */       ensureIndexKnown();
/*  835 */       if (this.curr == -1) throw new IllegalStateException();
/*  836 */       if (this.curr == this.prev)
/*      */       {
/*  839 */         this.index -= 1;
/*  840 */         this.prev = ((int)(Int2CharLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  843 */         this.next = ((int)Int2CharLinkedOpenHashMap.this.link[this.curr]);
/*  844 */       }Int2CharLinkedOpenHashMap.this.size -= 1;
/*      */ 
/*  847 */       if (this.prev == -1) Int2CharLinkedOpenHashMap.this.first = this.next;
/*      */       else
/*  849 */         Int2CharLinkedOpenHashMap.this.link[this.prev] ^= (Int2CharLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  850 */       if (this.next == -1) Int2CharLinkedOpenHashMap.this.last = this.prev;
/*      */       else
/*  852 */         Int2CharLinkedOpenHashMap.this.link[this.next] ^= (Int2CharLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  856 */         pos = (last = pos) + 1 & Int2CharLinkedOpenHashMap.this.mask;
/*  857 */         while (Int2CharLinkedOpenHashMap.this.used[pos] != 0) {
/*  858 */           int slot = HashCommon.murmurHash3(Int2CharLinkedOpenHashMap.this.key[pos]) & Int2CharLinkedOpenHashMap.this.mask;
/*  859 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  860 */           pos = pos + 1 & Int2CharLinkedOpenHashMap.this.mask;
/*      */         }
/*  862 */         if (Int2CharLinkedOpenHashMap.this.used[pos] == 0) break;
/*  863 */         Int2CharLinkedOpenHashMap.this.key[last] = Int2CharLinkedOpenHashMap.this.key[pos];
/*  864 */         Int2CharLinkedOpenHashMap.this.value[last] = Int2CharLinkedOpenHashMap.this.value[pos];
/*  865 */         if (this.next == pos) this.next = last;
/*  866 */         if (this.prev == pos) this.prev = last;
/*  867 */         Int2CharLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */       }
/*  869 */       Int2CharLinkedOpenHashMap.this.used[last] = false;
/*  870 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  873 */       int i = n;
/*  874 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  875 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  878 */       int i = n;
/*  879 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  880 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Int2CharMap.Entry, Map.Entry<Integer, Character>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  626 */       this.index = index;
/*      */     }
/*      */     public Integer getKey() {
/*  629 */       return Integer.valueOf(Int2CharLinkedOpenHashMap.this.key[this.index]);
/*      */     }
/*      */     public int getIntKey() {
/*  632 */       return Int2CharLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     public Character getValue() {
/*  635 */       return Character.valueOf(Int2CharLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */     public char getCharValue() {
/*  638 */       return Int2CharLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public char setValue(char v) {
/*  641 */       char oldValue = Int2CharLinkedOpenHashMap.this.value[this.index];
/*  642 */       Int2CharLinkedOpenHashMap.this.value[this.index] = v;
/*  643 */       return oldValue;
/*      */     }
/*      */     public Character setValue(Character v) {
/*  646 */       return Character.valueOf(setValue(v.charValue()));
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  650 */       if (!(o instanceof Map.Entry)) return false;
/*  651 */       Map.Entry e = (Map.Entry)o;
/*  652 */       return (Int2CharLinkedOpenHashMap.this.key[this.index] == ((Integer)e.getKey()).intValue()) && (Int2CharLinkedOpenHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/*      */     }
/*      */     public int hashCode() {
/*  655 */       return Int2CharLinkedOpenHashMap.this.key[this.index] ^ Int2CharLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public String toString() {
/*  658 */       return Int2CharLinkedOpenHashMap.this.key[this.index] + "=>" + Int2CharLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2CharLinkedOpenHashMap
 * JD-Core Version:    0.6.2
 */