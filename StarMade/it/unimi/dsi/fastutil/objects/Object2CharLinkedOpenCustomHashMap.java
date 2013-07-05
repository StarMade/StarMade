/*      */ package it.unimi.dsi.fastutil.objects;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.Hash.Strategy;
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
/*      */ public class Object2CharLinkedOpenCustomHashMap<K> extends AbstractObject2CharSortedMap<K>
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
/*      */   protected volatile transient Object2CharSortedMap.FastSortedEntrySet<K> entries;
/*      */   protected volatile transient ObjectSortedSet<K> keys;
/*      */   protected volatile transient CharCollection values;
/*  130 */   protected transient int first = -1;
/*      */ 
/*  132 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */   protected Hash.Strategy<K> strategy;
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  154 */     this.strategy = strategy;
/*  155 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  156 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  157 */     this.f = f;
/*  158 */     this.n = HashCommon.arraySize(expected, f);
/*  159 */     this.mask = (this.n - 1);
/*  160 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  161 */     this.key = ((Object[])new Object[this.n]);
/*  162 */     this.value = new char[this.n];
/*  163 */     this.used = new boolean[this.n];
/*  164 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*      */   {
/*  172 */     this(expected, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(Hash.Strategy<K> strategy)
/*      */   {
/*  179 */     this(16, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(Map<? extends K, ? extends Character> m, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  188 */     this(m.size(), f, strategy);
/*  189 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(Map<? extends K, ? extends Character> m, Hash.Strategy<K> strategy)
/*      */   {
/*  197 */     this(m, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(Object2CharMap<K> m, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  206 */     this(m.size(), f, strategy);
/*  207 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(Object2CharMap<K> m, Hash.Strategy<K> strategy)
/*      */   {
/*  215 */     this(m, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(K[] k, char[] v, float f, Hash.Strategy<K> strategy)
/*      */   {
/*  226 */     this(k.length, f, strategy);
/*  227 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  228 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap(K[] k, char[] v, Hash.Strategy<K> strategy)
/*      */   {
/*  238 */     this(k, v, 0.75F, strategy);
/*      */   }
/*      */ 
/*      */   public Hash.Strategy<K> strategy()
/*      */   {
/*  245 */     return this.strategy;
/*      */   }
/*      */ 
/*      */   public char put(K k, char v)
/*      */   {
/*  253 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  255 */     while (this.used[pos] != 0) {
/*  256 */       if (this.strategy.equals(this.key[pos], k)) {
/*  257 */         char oldValue = this.value[pos];
/*  258 */         this.value[pos] = v;
/*  259 */         return oldValue;
/*      */       }
/*  261 */       pos = pos + 1 & this.mask;
/*      */     }
/*  263 */     this.used[pos] = true;
/*  264 */     this.key[pos] = k;
/*  265 */     this.value[pos] = v;
/*  266 */     if (this.size == 0) {
/*  267 */       this.first = (this.last = pos);
/*      */ 
/*  269 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  272 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  273 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  274 */       this.last = pos;
/*      */     }
/*  276 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  278 */     return this.defRetValue;
/*      */   }
/*      */   public Character put(K ok, Character ov) {
/*  281 */     char v = ov.charValue();
/*  282 */     Object k = ok;
/*      */ 
/*  284 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  286 */     while (this.used[pos] != 0) {
/*  287 */       if (this.strategy.equals(this.key[pos], k)) {
/*  288 */         Character oldValue = Character.valueOf(this.value[pos]);
/*  289 */         this.value[pos] = v;
/*  290 */         return oldValue;
/*      */       }
/*  292 */       pos = pos + 1 & this.mask;
/*      */     }
/*  294 */     this.used[pos] = true;
/*  295 */     this.key[pos] = k;
/*  296 */     this.value[pos] = v;
/*  297 */     if (this.size == 0) {
/*  298 */       this.first = (this.last = pos);
/*      */ 
/*  300 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  303 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  304 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  305 */       this.last = pos;
/*      */     }
/*  307 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*      */ 
/*  309 */     return null;
/*      */   }
/*      */ 
/*      */   protected final int shiftKeys(int pos)
/*      */   {
/*      */     int last;
/*      */     while (true)
/*      */     {
/*  321 */       pos = (last = pos) + 1 & this.mask;
/*  322 */       while (this.used[pos] != 0) {
/*  323 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/*  324 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  325 */         pos = pos + 1 & this.mask;
/*      */       }
/*  327 */       if (this.used[pos] == 0) break;
/*  328 */       this.key[last] = this.key[pos];
/*  329 */       this.value[last] = this.value[pos];
/*  330 */       fixPointers(pos, last);
/*      */     }
/*  332 */     this.used[last] = false;
/*  333 */     this.key[last] = null;
/*  334 */     return last;
/*      */   }
/*      */ 
/*      */   public char removeChar(Object k)
/*      */   {
/*  339 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  341 */     while (this.used[pos] != 0) {
/*  342 */       if (this.strategy.equals(this.key[pos], k)) {
/*  343 */         this.size -= 1;
/*  344 */         fixPointers(pos);
/*  345 */         char v = this.value[pos];
/*  346 */         shiftKeys(pos);
/*  347 */         return v;
/*      */       }
/*  349 */       pos = pos + 1 & this.mask;
/*      */     }
/*  351 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public Character remove(Object ok) {
/*  355 */     Object k = ok;
/*      */ 
/*  357 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  359 */     while (this.used[pos] != 0) {
/*  360 */       if (this.strategy.equals(this.key[pos], k)) {
/*  361 */         this.size -= 1;
/*  362 */         fixPointers(pos);
/*  363 */         char v = this.value[pos];
/*  364 */         shiftKeys(pos);
/*  365 */         return Character.valueOf(v);
/*      */       }
/*  367 */       pos = pos + 1 & this.mask;
/*      */     }
/*  369 */     return null;
/*      */   }
/*      */ 
/*      */   public char removeFirstChar()
/*      */   {
/*  376 */     if (this.size == 0) throw new NoSuchElementException();
/*  377 */     this.size -= 1;
/*  378 */     int pos = this.first;
/*      */ 
/*  380 */     this.first = ((int)this.link[pos]);
/*  381 */     if (0 <= this.first)
/*      */     {
/*  383 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*  385 */     char v = this.value[pos];
/*  386 */     shiftKeys(pos);
/*  387 */     return v;
/*      */   }
/*      */ 
/*      */   public char removeLastChar()
/*      */   {
/*  394 */     if (this.size == 0) throw new NoSuchElementException();
/*  395 */     this.size -= 1;
/*  396 */     int pos = this.last;
/*      */ 
/*  398 */     this.last = ((int)(this.link[pos] >>> 32));
/*  399 */     if (0 <= this.last)
/*      */     {
/*  401 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*  403 */     char v = this.value[pos];
/*  404 */     shiftKeys(pos);
/*  405 */     return v;
/*      */   }
/*      */   private void moveIndexToFirst(int i) {
/*  408 */     if ((this.size == 1) || (this.first == i)) return;
/*  409 */     if (this.last == i) {
/*  410 */       this.last = ((int)(this.link[i] >>> 32));
/*      */ 
/*  412 */       this.link[this.last] |= 4294967295L;
/*      */     }
/*      */     else {
/*  415 */       long linki = this.link[i];
/*  416 */       int prev = (int)(linki >>> 32);
/*  417 */       int next = (int)linki;
/*  418 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  419 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  421 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/*  422 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/*  423 */     this.first = i;
/*      */   }
/*      */   private void moveIndexToLast(int i) {
/*  426 */     if ((this.size == 1) || (this.last == i)) return;
/*  427 */     if (this.first == i) {
/*  428 */       this.first = ((int)this.link[i]);
/*      */ 
/*  430 */       this.link[this.first] |= -4294967296L;
/*      */     }
/*      */     else {
/*  433 */       long linki = this.link[i];
/*  434 */       int prev = (int)(linki >>> 32);
/*  435 */       int next = (int)linki;
/*  436 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  437 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */     }
/*  439 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  440 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  441 */     this.last = i;
/*      */   }
/*      */ 
/*      */   public char getAndMoveToFirst(K k)
/*      */   {
/*  449 */     Object[] key = this.key;
/*  450 */     boolean[] used = this.used;
/*  451 */     int mask = this.mask;
/*      */ 
/*  453 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  455 */     while (used[pos] != 0) {
/*  456 */       if (this.strategy.equals(k, key[pos])) {
/*  457 */         moveIndexToFirst(pos);
/*  458 */         return this.value[pos];
/*      */       }
/*  460 */       pos = pos + 1 & mask;
/*      */     }
/*  462 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char getAndMoveToLast(K k)
/*      */   {
/*  470 */     Object[] key = this.key;
/*  471 */     boolean[] used = this.used;
/*  472 */     int mask = this.mask;
/*      */ 
/*  474 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  476 */     while (used[pos] != 0) {
/*  477 */       if (this.strategy.equals(k, key[pos])) {
/*  478 */         moveIndexToLast(pos);
/*  479 */         return this.value[pos];
/*      */       }
/*  481 */       pos = pos + 1 & mask;
/*      */     }
/*  483 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char putAndMoveToFirst(K k, char v)
/*      */   {
/*  492 */     Object[] key = this.key;
/*  493 */     boolean[] used = this.used;
/*  494 */     int mask = this.mask;
/*      */ 
/*  496 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  498 */     while (used[pos] != 0) {
/*  499 */       if (this.strategy.equals(k, key[pos])) {
/*  500 */         char oldValue = this.value[pos];
/*  501 */         this.value[pos] = v;
/*  502 */         moveIndexToFirst(pos);
/*  503 */         return oldValue;
/*      */       }
/*  505 */       pos = pos + 1 & mask;
/*      */     }
/*  507 */     used[pos] = true;
/*  508 */     key[pos] = k;
/*  509 */     this.value[pos] = v;
/*  510 */     if (this.size == 0) {
/*  511 */       this.first = (this.last = pos);
/*      */ 
/*  513 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  516 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/*  517 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/*  518 */       this.first = pos;
/*      */     }
/*  520 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  522 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char putAndMoveToLast(K k, char v)
/*      */   {
/*  531 */     Object[] key = this.key;
/*  532 */     boolean[] used = this.used;
/*  533 */     int mask = this.mask;
/*      */ 
/*  535 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*      */ 
/*  537 */     while (used[pos] != 0) {
/*  538 */       if (this.strategy.equals(k, key[pos])) {
/*  539 */         char oldValue = this.value[pos];
/*  540 */         this.value[pos] = v;
/*  541 */         moveIndexToLast(pos);
/*  542 */         return oldValue;
/*      */       }
/*  544 */       pos = pos + 1 & mask;
/*      */     }
/*  546 */     used[pos] = true;
/*  547 */     key[pos] = k;
/*  548 */     this.value[pos] = v;
/*  549 */     if (this.size == 0) {
/*  550 */       this.first = (this.last = pos);
/*      */ 
/*  552 */       this.link[pos] = -1L;
/*      */     }
/*      */     else {
/*  555 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  556 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/*  557 */       this.last = pos;
/*      */     }
/*  559 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*      */ 
/*  561 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public char getChar(Object k)
/*      */   {
/*  566 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  568 */     while (this.used[pos] != 0) {
/*  569 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/*  570 */       pos = pos + 1 & this.mask;
/*      */     }
/*  572 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(Object k)
/*      */   {
/*  577 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*      */ 
/*  579 */     while (this.used[pos] != 0) {
/*  580 */       if (this.strategy.equals(this.key[pos], k)) return true;
/*  581 */       pos = pos + 1 & this.mask;
/*      */     }
/*  583 */     return false;
/*      */   }
/*      */   public boolean containsValue(char v) {
/*  586 */     char[] value = this.value;
/*  587 */     boolean[] used = this.used;
/*  588 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*      */         break label16; return false;
/*      */   }
/*      */ 
/*      */   public void clear()
/*      */   {
/*  598 */     if (this.size == 0) return;
/*  599 */     this.size = 0;
/*  600 */     BooleanArrays.fill(this.used, false);
/*      */ 
/*  602 */     ObjectArrays.fill(this.key, null);
/*  603 */     this.first = (this.last = -1);
/*      */   }
/*      */   public int size() {
/*  606 */     return this.size;
/*      */   }
/*      */   public boolean isEmpty() {
/*  609 */     return this.size == 0;
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
/*  626 */     return 16;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int i)
/*      */   {
/*  676 */     if (this.size == 0) {
/*  677 */       this.first = (this.last = -1);
/*  678 */       return;
/*      */     }
/*  680 */     if (this.first == i) {
/*  681 */       this.first = ((int)this.link[i]);
/*  682 */       if (0 <= this.first)
/*      */       {
/*  684 */         this.link[this.first] |= -4294967296L;
/*      */       }
/*  686 */       return;
/*      */     }
/*  688 */     if (this.last == i) {
/*  689 */       this.last = ((int)(this.link[i] >>> 32));
/*  690 */       if (0 <= this.last)
/*      */       {
/*  692 */         this.link[this.last] |= 4294967295L;
/*      */       }
/*  694 */       return;
/*      */     }
/*  696 */     long linki = this.link[i];
/*  697 */     int prev = (int)(linki >>> 32);
/*  698 */     int next = (int)linki;
/*  699 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  700 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*      */   }
/*      */ 
/*      */   protected void fixPointers(int s, int d)
/*      */   {
/*  711 */     if (this.size == 1) {
/*  712 */       this.first = (this.last = d);
/*      */ 
/*  714 */       this.link[d] = -1L;
/*  715 */       return;
/*      */     }
/*  717 */     if (this.first == s) {
/*  718 */       this.first = d;
/*  719 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  720 */       this.link[d] = this.link[s];
/*  721 */       return;
/*      */     }
/*  723 */     if (this.last == s) {
/*  724 */       this.last = d;
/*  725 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  726 */       this.link[d] = this.link[s];
/*  727 */       return;
/*      */     }
/*  729 */     long links = this.link[s];
/*  730 */     int prev = (int)(links >>> 32);
/*  731 */     int next = (int)links;
/*  732 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  733 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/*  734 */     this.link[d] = links;
/*      */   }
/*      */ 
/*      */   public K firstKey()
/*      */   {
/*  741 */     if (this.size == 0) throw new NoSuchElementException();
/*  742 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public K lastKey()
/*      */   {
/*  749 */     if (this.size == 0) throw new NoSuchElementException();
/*  750 */     return this.key[this.last];
/*      */   }
/*  752 */   public Comparator<? super K> comparator() { return null; } 
/*  753 */   public Object2CharSortedMap<K> tailMap(K from) { throw new UnsupportedOperationException(); } 
/*  754 */   public Object2CharSortedMap<K> headMap(K to) { throw new UnsupportedOperationException(); } 
/*  755 */   public Object2CharSortedMap<K> subMap(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Object2CharSortedMap.FastSortedEntrySet<K> object2CharEntrySet()
/*      */   {
/*  996 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/*  997 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ObjectSortedSet<K> keySet()
/*      */   {
/* 1048 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1049 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public CharCollection values()
/*      */   {
/* 1069 */     if (this.values == null) this.values = new AbstractCharCollection() {
/*      */         public CharIterator iterator() {
/* 1071 */           return new Object2CharLinkedOpenCustomHashMap.ValueIterator(Object2CharLinkedOpenCustomHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1074 */           return Object2CharLinkedOpenCustomHashMap.this.size;
/*      */         }
/*      */         public boolean contains(char v) {
/* 1077 */           return Object2CharLinkedOpenCustomHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1080 */           Object2CharLinkedOpenCustomHashMap.this.clear();
/*      */         }
/*      */       };
/* 1083 */     return this.values;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public boolean rehash()
/*      */   {
/* 1097 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean trim()
/*      */   {
/* 1112 */     int l = HashCommon.arraySize(this.size, this.f);
/* 1113 */     if (l >= this.n) return true; try
/*      */     {
/* 1115 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1117 */       return false;
/* 1118 */     }return true;
/*      */   }
/*      */ 
/*      */   public boolean trim(int n)
/*      */   {
/* 1139 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 1140 */     if (this.n <= l) return true; try
/*      */     {
/* 1142 */       rehash(l);
/*      */     } catch (OutOfMemoryError cantDoIt) {
/* 1144 */       return false;
/* 1145 */     }return true;
/*      */   }
/*      */ 
/*      */   protected void rehash(int newN)
/*      */   {
/* 1158 */     int i = this.first; int prev = -1; int newPrev = -1;
/*      */ 
/* 1160 */     Object[] key = this.key;
/* 1161 */     char[] value = this.value;
/* 1162 */     int newMask = newN - 1;
/* 1163 */     Object[] newKey = (Object[])new Object[newN];
/* 1164 */     char[] newValue = new char[newN];
/* 1165 */     boolean[] newUsed = new boolean[newN];
/* 1166 */     long[] link = this.link;
/* 1167 */     long[] newLink = new long[newN];
/* 1168 */     this.first = -1;
/* 1169 */     for (int j = this.size; j-- != 0; ) {
/* 1170 */       Object k = key[i];
/* 1171 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 1172 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 1173 */       newUsed[pos] = true;
/* 1174 */       newKey[pos] = k;
/* 1175 */       newValue[pos] = value[i];
/* 1176 */       if (prev != -1) {
/* 1177 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1178 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 1179 */         newPrev = pos;
/*      */       }
/*      */       else {
/* 1182 */         newPrev = this.first = pos;
/*      */ 
/* 1184 */         newLink[pos] = -1L;
/*      */       }
/* 1186 */       int t = i;
/* 1187 */       i = (int)link[i];
/* 1188 */       prev = t;
/*      */     }
/* 1190 */     this.n = newN;
/* 1191 */     this.mask = newMask;
/* 1192 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1193 */     this.key = newKey;
/* 1194 */     this.value = newValue;
/* 1195 */     this.used = newUsed;
/* 1196 */     this.link = newLink;
/* 1197 */     this.last = newPrev;
/* 1198 */     if (newPrev != -1)
/*      */     {
/* 1200 */       newLink[newPrev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Object2CharLinkedOpenCustomHashMap<K> clone()
/*      */   {
/*      */     Object2CharLinkedOpenCustomHashMap c;
/*      */     try
/*      */     {
/* 1213 */       c = (Object2CharLinkedOpenCustomHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1216 */       throw new InternalError();
/*      */     }
/* 1218 */     c.keys = null;
/* 1219 */     c.values = null;
/* 1220 */     c.entries = null;
/* 1221 */     c.key = ((Object[])this.key.clone());
/* 1222 */     c.value = ((char[])this.value.clone());
/* 1223 */     c.used = ((boolean[])this.used.clone());
/* 1224 */     c.link = ((long[])this.link.clone());
/* 1225 */     c.strategy = this.strategy;
/* 1226 */     return c;
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1238 */     int h = 0;
/* 1239 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 1240 */       while (this.used[i] == 0) i++;
/* 1241 */       if (this != this.key[i])
/* 1242 */         t = this.strategy.hashCode(this.key[i]);
/* 1243 */       t ^= this.value[i];
/* 1244 */       h += t;
/* 1245 */       i++;
/*      */     }
/* 1247 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1250 */     Object[] key = this.key;
/* 1251 */     char[] value = this.value;
/* 1252 */     MapIterator i = new MapIterator(null);
/* 1253 */     s.defaultWriteObject();
/* 1254 */     for (int j = this.size; j-- != 0; ) {
/* 1255 */       int e = i.nextEntry();
/* 1256 */       s.writeObject(key[e]);
/* 1257 */       s.writeChar(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1262 */     s.defaultReadObject();
/* 1263 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1264 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1265 */     this.mask = (this.n - 1);
/* 1266 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 1267 */     char[] value = this.value = new char[this.n];
/* 1268 */     boolean[] used = this.used = new boolean[this.n];
/* 1269 */     long[] link = this.link = new long[this.n];
/* 1270 */     int prev = -1;
/* 1271 */     this.first = (this.last = -1);
/*      */ 
/* 1274 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1275 */       Object k = s.readObject();
/* 1276 */       char v = s.readChar();
/* 1277 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 1278 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 1279 */       used[pos] = true;
/* 1280 */       key[pos] = k;
/* 1281 */       value[pos] = v;
/* 1282 */       if (this.first != -1) {
/* 1283 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 1284 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 1285 */         prev = pos;
/*      */       }
/*      */       else {
/* 1288 */         prev = this.first = pos;
/*      */ 
/* 1290 */         link[pos] |= -4294967296L;
/*      */       }
/*      */     }
/* 1293 */     this.last = prev;
/* 1294 */     if (prev != -1)
/*      */     {
/* 1296 */       link[prev] |= 4294967295L;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void checkTable()
/*      */   {
/*      */   }
/*      */ 
/*      */   private final class ValueIterator extends Object2CharLinkedOpenCustomHashMap.MapIterator
/*      */     implements CharListIterator
/*      */   {
/*      */     public char previousChar()
/*      */     {
/* 1058 */       return Object2CharLinkedOpenCustomHashMap.this.value[previousEntry()]; } 
/* 1059 */     public Character previous() { return Character.valueOf(Object2CharLinkedOpenCustomHashMap.this.value[previousEntry()]); } 
/* 1060 */     public void set(Character ok) { throw new UnsupportedOperationException(); } 
/* 1061 */     public void add(Character ok) { throw new UnsupportedOperationException(); } 
/* 1062 */     public void set(char v) { throw new UnsupportedOperationException(); } 
/* 1063 */     public void add(char v) { throw new UnsupportedOperationException(); } 
/* 1064 */     public ValueIterator() { super(null); } 
/* 1065 */     public char nextChar() { return Object2CharLinkedOpenCustomHashMap.this.value[nextEntry()]; } 
/* 1066 */     public Character next() { return Character.valueOf(Object2CharLinkedOpenCustomHashMap.this.value[nextEntry()]); }
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
/* 1015 */       return new Object2CharLinkedOpenCustomHashMap.KeyIterator(Object2CharLinkedOpenCustomHashMap.this, from);
/*      */     }
/*      */     public ObjectListIterator<K> iterator() {
/* 1018 */       return new Object2CharLinkedOpenCustomHashMap.KeyIterator(Object2CharLinkedOpenCustomHashMap.this);
/*      */     }
/*      */     public int size() {
/* 1021 */       return Object2CharLinkedOpenCustomHashMap.this.size;
/*      */     }
/*      */     public boolean contains(Object k) {
/* 1024 */       return Object2CharLinkedOpenCustomHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(Object k) {
/* 1027 */       int oldSize = Object2CharLinkedOpenCustomHashMap.this.size;
/* 1028 */       Object2CharLinkedOpenCustomHashMap.this.remove(k);
/* 1029 */       return Object2CharLinkedOpenCustomHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/* 1032 */       Object2CharLinkedOpenCustomHashMap.this.clear();
/*      */     }
/*      */     public K first() {
/* 1035 */       if (Object2CharLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/* 1036 */       return Object2CharLinkedOpenCustomHashMap.this.key[Object2CharLinkedOpenCustomHashMap.this.first];
/*      */     }
/*      */     public K last() {
/* 1039 */       if (Object2CharLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/* 1040 */       return Object2CharLinkedOpenCustomHashMap.this.key[Object2CharLinkedOpenCustomHashMap.this.last];
/*      */     }
/* 1042 */     public Comparator<? super K> comparator() { return null; } 
/* 1043 */     public final ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/* 1044 */     public final ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/* 1045 */     public final ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Object2CharLinkedOpenCustomHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<K>
/*      */   {
/*      */     public KeyIterator()
/*      */     {
/* 1006 */       super(k, null); } 
/* 1007 */     public K previous() { return Object2CharLinkedOpenCustomHashMap.this.key[previousEntry()]; } 
/* 1008 */     public void set(K k) { throw new UnsupportedOperationException(); } 
/* 1009 */     public void add(K k) { throw new UnsupportedOperationException(); } 
/* 1010 */     public KeyIterator() { super(null); } 
/* 1011 */     public K next() { return Object2CharLinkedOpenCustomHashMap.this.key[nextEntry()]; }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Object2CharMap.Entry<K>>
/*      */     implements Object2CharSortedMap.FastSortedEntrySet<K>
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> iterator()
/*      */     {
/*  934 */       return new Object2CharLinkedOpenCustomHashMap.EntryIterator(Object2CharLinkedOpenCustomHashMap.this);
/*      */     }
/*  936 */     public Comparator<? super Object2CharMap.Entry<K>> comparator() { return null; } 
/*  937 */     public ObjectSortedSet<Object2CharMap.Entry<K>> subSet(Object2CharMap.Entry<K> fromElement, Object2CharMap.Entry<K> toElement) { throw new UnsupportedOperationException(); } 
/*  938 */     public ObjectSortedSet<Object2CharMap.Entry<K>> headSet(Object2CharMap.Entry<K> toElement) { throw new UnsupportedOperationException(); } 
/*  939 */     public ObjectSortedSet<Object2CharMap.Entry<K>> tailSet(Object2CharMap.Entry<K> fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Object2CharMap.Entry<K> first() {
/*  941 */       if (Object2CharLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  942 */       return new Object2CharLinkedOpenCustomHashMap.MapEntry(Object2CharLinkedOpenCustomHashMap.this, Object2CharLinkedOpenCustomHashMap.this.first);
/*      */     }
/*      */     public Object2CharMap.Entry<K> last() {
/*  945 */       if (Object2CharLinkedOpenCustomHashMap.this.size == 0) throw new NoSuchElementException();
/*  946 */       return new Object2CharLinkedOpenCustomHashMap.MapEntry(Object2CharLinkedOpenCustomHashMap.this, Object2CharLinkedOpenCustomHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  950 */       if (!(o instanceof Map.Entry)) return false;
/*  951 */       Map.Entry e = (Map.Entry)o;
/*  952 */       Object k = e.getKey();
/*      */ 
/*  954 */       int pos = HashCommon.murmurHash3(Object2CharLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */ 
/*  956 */       while (Object2CharLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  957 */         if (Object2CharLinkedOpenCustomHashMap.this.strategy.equals(Object2CharLinkedOpenCustomHashMap.this.key[pos], k)) return Object2CharLinkedOpenCustomHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/*  958 */         pos = pos + 1 & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */       }
/*  960 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  964 */       if (!(o instanceof Map.Entry)) return false;
/*  965 */       Map.Entry e = (Map.Entry)o;
/*  966 */       Object k = e.getKey();
/*      */ 
/*  968 */       int pos = HashCommon.murmurHash3(Object2CharLinkedOpenCustomHashMap.this.strategy.hashCode(k)) & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */ 
/*  970 */       while (Object2CharLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  971 */         if (Object2CharLinkedOpenCustomHashMap.this.strategy.equals(Object2CharLinkedOpenCustomHashMap.this.key[pos], k)) {
/*  972 */           Object2CharLinkedOpenCustomHashMap.this.remove(e.getKey());
/*  973 */           return true;
/*      */         }
/*  975 */         pos = pos + 1 & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */       }
/*  977 */       return false;
/*      */     }
/*      */     public int size() {
/*  980 */       return Object2CharLinkedOpenCustomHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/*  983 */       Object2CharLinkedOpenCustomHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> iterator(Object2CharMap.Entry<K> from) {
/*  986 */       return new Object2CharLinkedOpenCustomHashMap.EntryIterator(Object2CharLinkedOpenCustomHashMap.this, from.getKey());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> fastIterator() {
/*  989 */       return new Object2CharLinkedOpenCustomHashMap.FastEntryIterator(Object2CharLinkedOpenCustomHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> fastIterator(Object2CharMap.Entry<K> from) {
/*  992 */       return new Object2CharLinkedOpenCustomHashMap.FastEntryIterator(Object2CharLinkedOpenCustomHashMap.this, from.getKey());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Object2CharLinkedOpenCustomHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<Object2CharMap.Entry<K>>
/*      */   {
/*  912 */     final AbstractObject2CharMap.BasicEntry<K> entry = new AbstractObject2CharMap.BasicEntry(null, '\000');
/*      */ 
/*  913 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator() {
/*  915 */       super(from, null);
/*      */     }
/*      */     public AbstractObject2CharMap.BasicEntry<K> next() {
/*  918 */       int e = nextEntry();
/*  919 */       this.entry.key = Object2CharLinkedOpenCustomHashMap.this.key[e];
/*  920 */       this.entry.value = Object2CharLinkedOpenCustomHashMap.this.value[e];
/*  921 */       return this.entry;
/*      */     }
/*      */     public AbstractObject2CharMap.BasicEntry<K> previous() {
/*  924 */       int e = previousEntry();
/*  925 */       this.entry.key = Object2CharLinkedOpenCustomHashMap.this.key[e];
/*  926 */       this.entry.value = Object2CharLinkedOpenCustomHashMap.this.value[e];
/*  927 */       return this.entry;
/*      */     }
/*  929 */     public void set(Object2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  930 */     public void add(Object2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Object2CharLinkedOpenCustomHashMap<K>.MapIterator
/*      */     implements ObjectListIterator<Object2CharMap.Entry<K>>
/*      */   {
/*      */     private Object2CharLinkedOpenCustomHashMap<K>.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  893 */       super(null);
/*      */     }
/*  895 */     public EntryIterator() { super(from, null); }
/*      */ 
/*      */     public Object2CharLinkedOpenCustomHashMap<K>.MapEntry next() {
/*  898 */       return this.entry = new Object2CharLinkedOpenCustomHashMap.MapEntry(Object2CharLinkedOpenCustomHashMap.this, nextEntry());
/*      */     }
/*      */     public Object2CharLinkedOpenCustomHashMap<K>.MapEntry previous() {
/*  901 */       return this.entry = new Object2CharLinkedOpenCustomHashMap.MapEntry(Object2CharLinkedOpenCustomHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  905 */       super.remove();
/*  906 */       Object2CharLinkedOpenCustomHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  908 */     public void set(Object2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); } 
/*  909 */     public void add(Object2CharMap.Entry<K> ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class MapIterator
/*      */   {
/*  764 */     int prev = -1;
/*      */ 
/*  766 */     int next = -1;
/*      */ 
/*  768 */     int curr = -1;
/*      */ 
/*  770 */     int index = -1;
/*      */ 
/*  772 */     private MapIterator() { this.next = Object2CharLinkedOpenCustomHashMap.this.first;
/*  773 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator() {
/*  776 */       if (Object2CharLinkedOpenCustomHashMap.this.strategy.equals(Object2CharLinkedOpenCustomHashMap.this.key[Object2CharLinkedOpenCustomHashMap.this.last], from)) {
/*  777 */         this.prev = Object2CharLinkedOpenCustomHashMap.this.last;
/*  778 */         this.index = Object2CharLinkedOpenCustomHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  782 */         int pos = HashCommon.murmurHash3(Object2CharLinkedOpenCustomHashMap.this.strategy.hashCode(from)) & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */ 
/*  784 */         while (Object2CharLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  785 */           if (Object2CharLinkedOpenCustomHashMap.this.strategy.equals(Object2CharLinkedOpenCustomHashMap.this.key[pos], from))
/*      */           {
/*  787 */             this.next = ((int)Object2CharLinkedOpenCustomHashMap.this.link[pos]);
/*  788 */             this.prev = pos;
/*  789 */             return;
/*      */           }
/*  791 */           pos = pos + 1 & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */         }
/*  793 */         throw new NoSuchElementException("The key " + from + " does not belong to this map.");
/*      */       }
/*      */     }
/*  796 */     public boolean hasNext() { return this.next != -1; } 
/*  797 */     public boolean hasPrevious() { return this.prev != -1; } 
/*      */     private final void ensureIndexKnown() {
/*  799 */       if (this.index >= 0) return;
/*  800 */       if (this.prev == -1) {
/*  801 */         this.index = 0;
/*  802 */         return;
/*      */       }
/*  804 */       if (this.next == -1) {
/*  805 */         this.index = Object2CharLinkedOpenCustomHashMap.this.size;
/*  806 */         return;
/*      */       }
/*  808 */       int pos = Object2CharLinkedOpenCustomHashMap.this.first;
/*  809 */       this.index = 1;
/*  810 */       while (pos != this.prev) {
/*  811 */         pos = (int)Object2CharLinkedOpenCustomHashMap.this.link[pos];
/*  812 */         this.index += 1;
/*      */       }
/*      */     }
/*      */ 
/*  816 */     public int nextIndex() { ensureIndexKnown();
/*  817 */       return this.index; }
/*      */ 
/*      */     public int previousIndex() {
/*  820 */       ensureIndexKnown();
/*  821 */       return this.index - 1;
/*      */     }
/*      */     public int nextEntry() {
/*  824 */       if (!hasNext()) return Object2CharLinkedOpenCustomHashMap.this.size();
/*  825 */       this.curr = this.next;
/*  826 */       this.next = ((int)Object2CharLinkedOpenCustomHashMap.this.link[this.curr]);
/*  827 */       this.prev = this.curr;
/*  828 */       if (this.index >= 0) this.index += 1;
/*  829 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  832 */       if (!hasPrevious()) return -1;
/*  833 */       this.curr = this.prev;
/*  834 */       this.prev = ((int)(Object2CharLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*  835 */       this.next = this.curr;
/*  836 */       if (this.index >= 0) this.index -= 1;
/*  837 */       return this.curr;
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  841 */       ensureIndexKnown();
/*  842 */       if (this.curr == -1) throw new IllegalStateException();
/*  843 */       if (this.curr == this.prev)
/*      */       {
/*  846 */         this.index -= 1;
/*  847 */         this.prev = ((int)(Object2CharLinkedOpenCustomHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  850 */         this.next = ((int)Object2CharLinkedOpenCustomHashMap.this.link[this.curr]);
/*  851 */       }Object2CharLinkedOpenCustomHashMap.this.size -= 1;
/*      */ 
/*  854 */       if (this.prev == -1) Object2CharLinkedOpenCustomHashMap.this.first = this.next;
/*      */       else
/*  856 */         Object2CharLinkedOpenCustomHashMap.this.link[this.prev] ^= (Object2CharLinkedOpenCustomHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  857 */       if (this.next == -1) Object2CharLinkedOpenCustomHashMap.this.last = this.prev;
/*      */       else
/*  859 */         Object2CharLinkedOpenCustomHashMap.this.link[this.next] ^= (Object2CharLinkedOpenCustomHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  863 */         pos = (last = pos) + 1 & Object2CharLinkedOpenCustomHashMap.this.mask;
/*  864 */         while (Object2CharLinkedOpenCustomHashMap.this.used[pos] != 0) {
/*  865 */           int slot = HashCommon.murmurHash3(Object2CharLinkedOpenCustomHashMap.this.strategy.hashCode(Object2CharLinkedOpenCustomHashMap.this.key[pos])) & Object2CharLinkedOpenCustomHashMap.this.mask;
/*  866 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  867 */           pos = pos + 1 & Object2CharLinkedOpenCustomHashMap.this.mask;
/*      */         }
/*  869 */         if (Object2CharLinkedOpenCustomHashMap.this.used[pos] == 0) break;
/*  870 */         Object2CharLinkedOpenCustomHashMap.this.key[last] = Object2CharLinkedOpenCustomHashMap.this.key[pos];
/*  871 */         Object2CharLinkedOpenCustomHashMap.this.value[last] = Object2CharLinkedOpenCustomHashMap.this.value[pos];
/*  872 */         if (this.next == pos) this.next = last;
/*  873 */         if (this.prev == pos) this.prev = last;
/*  874 */         Object2CharLinkedOpenCustomHashMap.this.fixPointers(pos, last);
/*      */       }
/*  876 */       Object2CharLinkedOpenCustomHashMap.this.used[last] = false;
/*  877 */       Object2CharLinkedOpenCustomHashMap.this.key[last] = null;
/*  878 */       this.curr = -1;
/*      */     }
/*      */     public int skip(int n) {
/*  881 */       int i = n;
/*  882 */       while ((i-- != 0) && (hasNext())) nextEntry();
/*  883 */       return n - i - 1;
/*      */     }
/*      */     public int back(int n) {
/*  886 */       int i = n;
/*  887 */       while ((i-- != 0) && (hasPrevious())) previousEntry();
/*  888 */       return n - i - 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   private final class MapEntry
/*      */     implements Object2CharMap.Entry<K>, Map.Entry<K, Character>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  636 */       this.index = index;
/*      */     }
/*      */     public K getKey() {
/*  639 */       return Object2CharLinkedOpenCustomHashMap.this.key[this.index];
/*      */     }
/*      */     public Character getValue() {
/*  642 */       return Character.valueOf(Object2CharLinkedOpenCustomHashMap.this.value[this.index]);
/*      */     }
/*      */     public char getCharValue() {
/*  645 */       return Object2CharLinkedOpenCustomHashMap.this.value[this.index];
/*      */     }
/*      */     public char setValue(char v) {
/*  648 */       char oldValue = Object2CharLinkedOpenCustomHashMap.this.value[this.index];
/*  649 */       Object2CharLinkedOpenCustomHashMap.this.value[this.index] = v;
/*  650 */       return oldValue;
/*      */     }
/*      */     public Character setValue(Character v) {
/*  653 */       return Character.valueOf(setValue(v.charValue()));
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  657 */       if (!(o instanceof Map.Entry)) return false;
/*  658 */       Map.Entry e = (Map.Entry)o;
/*  659 */       return (Object2CharLinkedOpenCustomHashMap.this.strategy.equals(Object2CharLinkedOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2CharLinkedOpenCustomHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/*      */     }
/*      */     public int hashCode() {
/*  662 */       return Object2CharLinkedOpenCustomHashMap.this.strategy.hashCode(Object2CharLinkedOpenCustomHashMap.this.key[this.index]) ^ Object2CharLinkedOpenCustomHashMap.this.value[this.index];
/*      */     }
/*      */     public String toString() {
/*  665 */       return Object2CharLinkedOpenCustomHashMap.this.key[this.index] + "=>" + Object2CharLinkedOpenCustomHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2CharLinkedOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */