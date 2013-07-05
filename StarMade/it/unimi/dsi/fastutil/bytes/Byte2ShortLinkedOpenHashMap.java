/*      */ package it.unimi.dsi.fastutil.bytes;
/*      */ 
/*      */ import it.unimi.dsi.fastutil.Hash;
/*      */ import it.unimi.dsi.fastutil.HashCommon;
/*      */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*      */ import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*      */ import it.unimi.dsi.fastutil.shorts.AbstractShortCollection;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortCollection;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*      */ import it.unimi.dsi.fastutil.shorts.ShortListIterator;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Comparator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ 
/*      */ public class Byte2ShortLinkedOpenHashMap extends AbstractByte2ShortSortedMap
/*      */   implements Serializable, Cloneable, Hash
/*      */ {
/*      */   public static final long serialVersionUID = 0L;
/*      */   private static final boolean ASSERTS = false;
/*      */   protected transient byte[] key;
/*      */   protected transient short[] value;
/*      */   protected transient boolean[] used;
/*      */   protected final float f;
/*      */   protected transient int n;
/*      */   protected transient int maxFill;
/*      */   protected transient int mask;
/*      */   protected int size;
/*      */   protected volatile transient Byte2ShortSortedMap.FastSortedEntrySet entries;
/*      */   protected volatile transient ByteSortedSet keys;
/*      */   protected volatile transient ShortCollection values;
/*  130 */   protected transient int first = -1;
/*      */ 
/*  132 */   protected transient int last = -1;
/*      */   protected transient long[] link;
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(int expected, float f)
/*      */   {
/*  151 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  152 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  153 */     this.f = f;
/*  154 */     this.n = HashCommon.arraySize(expected, f);
/*  155 */     this.mask = (this.n - 1);
/*  156 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  157 */     this.key = new byte[this.n];
/*  158 */     this.value = new short[this.n];
/*  159 */     this.used = new boolean[this.n];
/*  160 */     this.link = new long[this.n];
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(int expected)
/*      */   {
/*  167 */     this(expected, 0.75F);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap()
/*      */   {
/*  173 */     this(16, 0.75F);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(Map<? extends Byte, ? extends Short> m, float f)
/*      */   {
/*  181 */     this(m.size(), f);
/*  182 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(Map<? extends Byte, ? extends Short> m)
/*      */   {
/*  189 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(Byte2ShortMap m, float f)
/*      */   {
/*  197 */     this(m.size(), f);
/*  198 */     putAll(m);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(Byte2ShortMap m)
/*      */   {
/*  205 */     this(m, 0.75F);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(byte[] k, short[] v, float f)
/*      */   {
/*  215 */     this(k.length, f);
/*  216 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/*  217 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*      */   }
/*      */ 
/*      */   public Byte2ShortLinkedOpenHashMap(byte[] k, short[] v)
/*      */   {
/*  226 */     this(k, v, 0.75F);
/*      */   }
/*      */ 
/*      */   public short put(byte k, short v)
/*      */   {
/*  234 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  236 */     while (this.used[pos] != 0) {
/*  237 */       if (this.key[pos] == k) {
/*  238 */         short oldValue = this.value[pos];
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
/*      */   public Short put(Byte ok, Short ov) {
/*  262 */     short v = ov.shortValue();
/*  263 */     byte k = ok.byteValue();
/*      */ 
/*  265 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  267 */     while (this.used[pos] != 0) {
/*  268 */       if (this.key[pos] == k) {
/*  269 */         Short oldValue = Short.valueOf(this.value[pos]);
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
/*      */   public short add(byte k, short incr)
/*      */   {
/*  305 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  307 */     while (this.used[pos] != 0) {
/*  308 */       if (this.key[pos] == k) {
/*  309 */         short oldValue = this.value[pos];
/*      */         int tmp42_41 = pos;
/*      */         short[] tmp42_38 = this.value; tmp42_38[tmp42_41] = ((short)(tmp42_38[tmp42_41] + incr));
/*  311 */         return oldValue;
/*      */       }
/*  313 */       pos = pos + 1 & this.mask;
/*      */     }
/*  315 */     this.used[pos] = true;
/*  316 */     this.key[pos] = k;
/*  317 */     this.value[pos] = ((short)(this.defRetValue + incr));
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
/*  344 */         int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
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
/*      */   public short remove(byte k)
/*      */   {
/*  359 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  361 */     while (this.used[pos] != 0) {
/*  362 */       if (this.key[pos] == k) {
/*  363 */         this.size -= 1;
/*  364 */         fixPointers(pos);
/*  365 */         short v = this.value[pos];
/*  366 */         shiftKeys(pos);
/*  367 */         return v;
/*      */       }
/*  369 */       pos = pos + 1 & this.mask;
/*      */     }
/*  371 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public Short remove(Object ok) {
/*  375 */     byte k = ((Byte)ok).byteValue();
/*      */ 
/*  377 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  379 */     while (this.used[pos] != 0) {
/*  380 */       if (this.key[pos] == k) {
/*  381 */         this.size -= 1;
/*  382 */         fixPointers(pos);
/*  383 */         short v = this.value[pos];
/*  384 */         shiftKeys(pos);
/*  385 */         return Short.valueOf(v);
/*      */       }
/*  387 */       pos = pos + 1 & this.mask;
/*      */     }
/*  389 */     return null;
/*      */   }
/*      */ 
/*      */   public short removeFirstShort()
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
/*  405 */     short v = this.value[pos];
/*  406 */     shiftKeys(pos);
/*  407 */     return v;
/*      */   }
/*      */ 
/*      */   public short removeLastShort()
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
/*  423 */     short v = this.value[pos];
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
/*      */   public short getAndMoveToFirst(byte k)
/*      */   {
/*  469 */     byte[] key = this.key;
/*  470 */     boolean[] used = this.used;
/*  471 */     int mask = this.mask;
/*      */ 
/*  473 */     int pos = HashCommon.murmurHash3(k) & mask;
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
/*      */   public short getAndMoveToLast(byte k)
/*      */   {
/*  490 */     byte[] key = this.key;
/*  491 */     boolean[] used = this.used;
/*  492 */     int mask = this.mask;
/*      */ 
/*  494 */     int pos = HashCommon.murmurHash3(k) & mask;
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
/*      */   public short putAndMoveToFirst(byte k, short v)
/*      */   {
/*  512 */     byte[] key = this.key;
/*  513 */     boolean[] used = this.used;
/*  514 */     int mask = this.mask;
/*      */ 
/*  516 */     int pos = HashCommon.murmurHash3(k) & mask;
/*      */ 
/*  518 */     while (used[pos] != 0) {
/*  519 */       if (k == key[pos]) {
/*  520 */         short oldValue = this.value[pos];
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
/*      */   public short putAndMoveToLast(byte k, short v)
/*      */   {
/*  551 */     byte[] key = this.key;
/*  552 */     boolean[] used = this.used;
/*  553 */     int mask = this.mask;
/*      */ 
/*  555 */     int pos = HashCommon.murmurHash3(k) & mask;
/*      */ 
/*  557 */     while (used[pos] != 0) {
/*  558 */       if (k == key[pos]) {
/*  559 */         short oldValue = this.value[pos];
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
/*      */   public Short get(Byte ok) {
/*  584 */     byte k = ok.byteValue();
/*      */ 
/*  586 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  588 */     while (this.used[pos] != 0) {
/*  589 */       if (this.key[pos] == k) return Short.valueOf(this.value[pos]);
/*  590 */       pos = pos + 1 & this.mask;
/*      */     }
/*  592 */     return null;
/*      */   }
/*      */ 
/*      */   public short get(byte k)
/*      */   {
/*  597 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  599 */     while (this.used[pos] != 0) {
/*  600 */       if (this.key[pos] == k) return this.value[pos];
/*  601 */       pos = pos + 1 & this.mask;
/*      */     }
/*  603 */     return this.defRetValue;
/*      */   }
/*      */ 
/*      */   public boolean containsKey(byte k)
/*      */   {
/*  608 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*      */ 
/*  610 */     while (this.used[pos] != 0) {
/*  611 */       if (this.key[pos] == k) return true;
/*  612 */       pos = pos + 1 & this.mask;
/*      */     }
/*  614 */     return false;
/*      */   }
/*      */   public boolean containsValue(short v) {
/*  617 */     short[] value = this.value;
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
/*      */   public byte firstByteKey()
/*      */   {
/*  774 */     if (this.size == 0) throw new NoSuchElementException();
/*  775 */     return this.key[this.first];
/*      */   }
/*      */ 
/*      */   public byte lastByteKey()
/*      */   {
/*  782 */     if (this.size == 0) throw new NoSuchElementException();
/*  783 */     return this.key[this.last];
/*      */   }
/*  785 */   public ByteComparator comparator() { return null; } 
/*  786 */   public Byte2ShortSortedMap tailMap(byte from) { throw new UnsupportedOperationException(); } 
/*  787 */   public Byte2ShortSortedMap headMap(byte to) { throw new UnsupportedOperationException(); } 
/*  788 */   public Byte2ShortSortedMap subMap(byte from, byte to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */ 
/*      */   public Byte2ShortSortedMap.FastSortedEntrySet byte2ShortEntrySet()
/*      */   {
/* 1028 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 1029 */     return this.entries;
/*      */   }
/*      */ 
/*      */   public ByteSortedSet keySet()
/*      */   {
/* 1084 */     if (this.keys == null) this.keys = new KeySet(null);
/* 1085 */     return this.keys;
/*      */   }
/*      */ 
/*      */   public ShortCollection values()
/*      */   {
/* 1105 */     if (this.values == null) this.values = new AbstractShortCollection() {
/*      */         public ShortIterator iterator() {
/* 1107 */           return new Byte2ShortLinkedOpenHashMap.ValueIterator(Byte2ShortLinkedOpenHashMap.this);
/*      */         }
/*      */         public int size() {
/* 1110 */           return Byte2ShortLinkedOpenHashMap.this.size;
/*      */         }
/*      */         public boolean contains(short v) {
/* 1113 */           return Byte2ShortLinkedOpenHashMap.this.containsValue(v);
/*      */         }
/*      */         public void clear() {
/* 1116 */           Byte2ShortLinkedOpenHashMap.this.clear();
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
/* 1196 */     byte[] key = this.key;
/* 1197 */     short[] value = this.value;
/* 1198 */     int newMask = newN - 1;
/* 1199 */     byte[] newKey = new byte[newN];
/* 1200 */     short[] newValue = new short[newN];
/* 1201 */     boolean[] newUsed = new boolean[newN];
/* 1202 */     long[] link = this.link;
/* 1203 */     long[] newLink = new long[newN];
/* 1204 */     this.first = -1;
/* 1205 */     for (int j = this.size; j-- != 0; ) {
/* 1206 */       byte k = key[i];
/* 1207 */       int pos = HashCommon.murmurHash3(k) & newMask;
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
/*      */   public Byte2ShortLinkedOpenHashMap clone()
/*      */   {
/*      */     Byte2ShortLinkedOpenHashMap c;
/*      */     try
/*      */     {
/* 1249 */       c = (Byte2ShortLinkedOpenHashMap)super.clone();
/*      */     }
/*      */     catch (CloneNotSupportedException cantHappen) {
/* 1252 */       throw new InternalError();
/*      */     }
/* 1254 */     c.keys = null;
/* 1255 */     c.values = null;
/* 1256 */     c.entries = null;
/* 1257 */     c.key = ((byte[])this.key.clone());
/* 1258 */     c.value = ((short[])this.value.clone());
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
/* 1276 */       t = this.key[i];
/* 1277 */       t ^= this.value[i];
/* 1278 */       h += t;
/* 1279 */       i++;
/*      */     }
/* 1281 */     return h;
/*      */   }
/*      */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 1284 */     byte[] key = this.key;
/* 1285 */     short[] value = this.value;
/* 1286 */     MapIterator i = new MapIterator(null);
/* 1287 */     s.defaultWriteObject();
/* 1288 */     for (int j = this.size; j-- != 0; ) {
/* 1289 */       int e = i.nextEntry();
/* 1290 */       s.writeByte(key[e]);
/* 1291 */       s.writeShort(value[e]);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 1296 */     s.defaultReadObject();
/* 1297 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 1298 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 1299 */     this.mask = (this.n - 1);
/* 1300 */     byte[] key = this.key = new byte[this.n];
/* 1301 */     short[] value = this.value = new short[this.n];
/* 1302 */     boolean[] used = this.used = new boolean[this.n];
/* 1303 */     long[] link = this.link = new long[this.n];
/* 1304 */     int prev = -1;
/* 1305 */     this.first = (this.last = -1);
/*      */ 
/* 1308 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 1309 */       byte k = s.readByte();
/* 1310 */       short v = s.readShort();
/* 1311 */       pos = HashCommon.murmurHash3(k) & this.mask;
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
/*      */   private final class ValueIterator extends Byte2ShortLinkedOpenHashMap.MapIterator
/*      */     implements ShortListIterator
/*      */   {
/*      */     public short previousShort()
/*      */     {
/* 1094 */       return Byte2ShortLinkedOpenHashMap.this.value[previousEntry()]; } 
/* 1095 */     public Short previous() { return Short.valueOf(Byte2ShortLinkedOpenHashMap.this.value[previousEntry()]); } 
/* 1096 */     public void set(Short ok) { throw new UnsupportedOperationException(); } 
/* 1097 */     public void add(Short ok) { throw new UnsupportedOperationException(); } 
/* 1098 */     public void set(short v) { throw new UnsupportedOperationException(); } 
/* 1099 */     public void add(short v) { throw new UnsupportedOperationException(); } 
/* 1100 */     public ValueIterator() { super(null); } 
/* 1101 */     public short nextShort() { return Byte2ShortLinkedOpenHashMap.this.value[nextEntry()]; } 
/* 1102 */     public Short next() { return Short.valueOf(Byte2ShortLinkedOpenHashMap.this.value[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeySet extends AbstractByteSortedSet
/*      */   {
/*      */     private KeySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ByteListIterator iterator(byte from)
/*      */     {
/* 1051 */       return new Byte2ShortLinkedOpenHashMap.KeyIterator(Byte2ShortLinkedOpenHashMap.this, from);
/*      */     }
/*      */     public ByteListIterator iterator() {
/* 1054 */       return new Byte2ShortLinkedOpenHashMap.KeyIterator(Byte2ShortLinkedOpenHashMap.this);
/*      */     }
/*      */     public int size() {
/* 1057 */       return Byte2ShortLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public boolean contains(byte k) {
/* 1060 */       return Byte2ShortLinkedOpenHashMap.this.containsKey(k);
/*      */     }
/*      */     public boolean remove(byte k) {
/* 1063 */       int oldSize = Byte2ShortLinkedOpenHashMap.this.size;
/* 1064 */       Byte2ShortLinkedOpenHashMap.this.remove(k);
/* 1065 */       return Byte2ShortLinkedOpenHashMap.this.size != oldSize;
/*      */     }
/*      */     public void clear() {
/* 1068 */       Byte2ShortLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public byte firstByte() {
/* 1071 */       if (Byte2ShortLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1072 */       return Byte2ShortLinkedOpenHashMap.this.key[Byte2ShortLinkedOpenHashMap.this.first];
/*      */     }
/*      */     public byte lastByte() {
/* 1075 */       if (Byte2ShortLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/* 1076 */       return Byte2ShortLinkedOpenHashMap.this.key[Byte2ShortLinkedOpenHashMap.this.last];
/*      */     }
/* 1078 */     public ByteComparator comparator() { return null; } 
/* 1079 */     public final ByteSortedSet tailSet(byte from) { throw new UnsupportedOperationException(); } 
/* 1080 */     public final ByteSortedSet headSet(byte to) { throw new UnsupportedOperationException(); } 
/* 1081 */     public final ByteSortedSet subSet(byte from, byte to) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class KeyIterator extends Byte2ShortLinkedOpenHashMap.MapIterator
/*      */     implements ByteListIterator
/*      */   {
/*      */     public KeyIterator(byte k)
/*      */     {
/* 1038 */       super(k, null); } 
/* 1039 */     public byte previousByte() { return Byte2ShortLinkedOpenHashMap.this.key[previousEntry()]; } 
/* 1040 */     public void set(byte k) { throw new UnsupportedOperationException(); } 
/* 1041 */     public void add(byte k) { throw new UnsupportedOperationException(); } 
/* 1042 */     public Byte previous() { return Byte.valueOf(Byte2ShortLinkedOpenHashMap.this.key[previousEntry()]); } 
/* 1043 */     public void set(Byte ok) { throw new UnsupportedOperationException(); } 
/* 1044 */     public void add(Byte ok) { throw new UnsupportedOperationException(); } 
/* 1045 */     public KeyIterator() { super(null); } 
/* 1046 */     public byte nextByte() { return Byte2ShortLinkedOpenHashMap.this.key[nextEntry()]; } 
/* 1047 */     public Byte next() { return Byte.valueOf(Byte2ShortLinkedOpenHashMap.this.key[nextEntry()]); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private final class MapEntrySet extends AbstractObjectSortedSet<Byte2ShortMap.Entry>
/*      */     implements Byte2ShortSortedMap.FastSortedEntrySet
/*      */   {
/*      */     private MapEntrySet()
/*      */     {
/*      */     }
/*      */ 
/*      */     public ObjectBidirectionalIterator<Byte2ShortMap.Entry> iterator()
/*      */     {
/*  966 */       return new Byte2ShortLinkedOpenHashMap.EntryIterator(Byte2ShortLinkedOpenHashMap.this);
/*      */     }
/*  968 */     public Comparator<? super Byte2ShortMap.Entry> comparator() { return null; } 
/*  969 */     public ObjectSortedSet<Byte2ShortMap.Entry> subSet(Byte2ShortMap.Entry fromElement, Byte2ShortMap.Entry toElement) { throw new UnsupportedOperationException(); } 
/*  970 */     public ObjectSortedSet<Byte2ShortMap.Entry> headSet(Byte2ShortMap.Entry toElement) { throw new UnsupportedOperationException(); } 
/*  971 */     public ObjectSortedSet<Byte2ShortMap.Entry> tailSet(Byte2ShortMap.Entry fromElement) { throw new UnsupportedOperationException(); } 
/*      */     public Byte2ShortMap.Entry first() {
/*  973 */       if (Byte2ShortLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  974 */       return new Byte2ShortLinkedOpenHashMap.MapEntry(Byte2ShortLinkedOpenHashMap.this, Byte2ShortLinkedOpenHashMap.this.first);
/*      */     }
/*      */     public Byte2ShortMap.Entry last() {
/*  977 */       if (Byte2ShortLinkedOpenHashMap.this.size == 0) throw new NoSuchElementException();
/*  978 */       return new Byte2ShortLinkedOpenHashMap.MapEntry(Byte2ShortLinkedOpenHashMap.this, Byte2ShortLinkedOpenHashMap.this.last);
/*      */     }
/*      */ 
/*      */     public boolean contains(Object o) {
/*  982 */       if (!(o instanceof Map.Entry)) return false;
/*  983 */       Map.Entry e = (Map.Entry)o;
/*  984 */       byte k = ((Byte)e.getKey()).byteValue();
/*      */ 
/*  986 */       int pos = HashCommon.murmurHash3(k) & Byte2ShortLinkedOpenHashMap.this.mask;
/*      */ 
/*  988 */       while (Byte2ShortLinkedOpenHashMap.this.used[pos] != 0) {
/*  989 */         if (Byte2ShortLinkedOpenHashMap.this.key[pos] == k) return Byte2ShortLinkedOpenHashMap.this.value[pos] == ((Short)e.getValue()).shortValue();
/*  990 */         pos = pos + 1 & Byte2ShortLinkedOpenHashMap.this.mask;
/*      */       }
/*  992 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean remove(Object o) {
/*  996 */       if (!(o instanceof Map.Entry)) return false;
/*  997 */       Map.Entry e = (Map.Entry)o;
/*  998 */       byte k = ((Byte)e.getKey()).byteValue();
/*      */ 
/* 1000 */       int pos = HashCommon.murmurHash3(k) & Byte2ShortLinkedOpenHashMap.this.mask;
/*      */ 
/* 1002 */       while (Byte2ShortLinkedOpenHashMap.this.used[pos] != 0) {
/* 1003 */         if (Byte2ShortLinkedOpenHashMap.this.key[pos] == k) {
/* 1004 */           Byte2ShortLinkedOpenHashMap.this.remove(e.getKey());
/* 1005 */           return true;
/*      */         }
/* 1007 */         pos = pos + 1 & Byte2ShortLinkedOpenHashMap.this.mask;
/*      */       }
/* 1009 */       return false;
/*      */     }
/*      */     public int size() {
/* 1012 */       return Byte2ShortLinkedOpenHashMap.this.size;
/*      */     }
/*      */     public void clear() {
/* 1015 */       Byte2ShortLinkedOpenHashMap.this.clear();
/*      */     }
/*      */     public ObjectBidirectionalIterator<Byte2ShortMap.Entry> iterator(Byte2ShortMap.Entry from) {
/* 1018 */       return new Byte2ShortLinkedOpenHashMap.EntryIterator(Byte2ShortLinkedOpenHashMap.this, ((Byte)from.getKey()).byteValue());
/*      */     }
/*      */     public ObjectBidirectionalIterator<Byte2ShortMap.Entry> fastIterator() {
/* 1021 */       return new Byte2ShortLinkedOpenHashMap.FastEntryIterator(Byte2ShortLinkedOpenHashMap.this);
/*      */     }
/*      */     public ObjectBidirectionalIterator<Byte2ShortMap.Entry> fastIterator(Byte2ShortMap.Entry from) {
/* 1024 */       return new Byte2ShortLinkedOpenHashMap.FastEntryIterator(Byte2ShortLinkedOpenHashMap.this, ((Byte)from.getKey()).byteValue());
/*      */     }
/*      */   }
/*      */ 
/*      */   private class FastEntryIterator extends Byte2ShortLinkedOpenHashMap.MapIterator
/*      */     implements ObjectListIterator<Byte2ShortMap.Entry>
/*      */   {
/*  944 */     final AbstractByte2ShortMap.BasicEntry entry = new AbstractByte2ShortMap.BasicEntry((byte)0, (short)0);
/*      */ 
/*  945 */     public FastEntryIterator() { super(null); } 
/*      */     public FastEntryIterator(byte from) {
/*  947 */       super(from, null);
/*      */     }
/*      */     public AbstractByte2ShortMap.BasicEntry next() {
/*  950 */       int e = nextEntry();
/*  951 */       this.entry.key = Byte2ShortLinkedOpenHashMap.this.key[e];
/*  952 */       this.entry.value = Byte2ShortLinkedOpenHashMap.this.value[e];
/*  953 */       return this.entry;
/*      */     }
/*      */     public AbstractByte2ShortMap.BasicEntry previous() {
/*  956 */       int e = previousEntry();
/*  957 */       this.entry.key = Byte2ShortLinkedOpenHashMap.this.key[e];
/*  958 */       this.entry.value = Byte2ShortLinkedOpenHashMap.this.value[e];
/*  959 */       return this.entry;
/*      */     }
/*  961 */     public void set(Byte2ShortMap.Entry ok) { throw new UnsupportedOperationException(); } 
/*  962 */     public void add(Byte2ShortMap.Entry ok) { throw new UnsupportedOperationException(); }
/*      */ 
/*      */   }
/*      */ 
/*      */   private class EntryIterator extends Byte2ShortLinkedOpenHashMap.MapIterator
/*      */     implements ObjectListIterator<Byte2ShortMap.Entry>
/*      */   {
/*      */     private Byte2ShortLinkedOpenHashMap.MapEntry entry;
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  925 */       super(null);
/*      */     }
/*  927 */     public EntryIterator(byte from) { super(from, null); }
/*      */ 
/*      */     public Byte2ShortLinkedOpenHashMap.MapEntry next() {
/*  930 */       return this.entry = new Byte2ShortLinkedOpenHashMap.MapEntry(Byte2ShortLinkedOpenHashMap.this, nextEntry());
/*      */     }
/*      */     public Byte2ShortLinkedOpenHashMap.MapEntry previous() {
/*  933 */       return this.entry = new Byte2ShortLinkedOpenHashMap.MapEntry(Byte2ShortLinkedOpenHashMap.this, previousEntry());
/*      */     }
/*      */ 
/*      */     public void remove() {
/*  937 */       super.remove();
/*  938 */       Byte2ShortLinkedOpenHashMap.MapEntry.access$202(this.entry, -1);
/*      */     }
/*  940 */     public void set(Byte2ShortMap.Entry ok) { throw new UnsupportedOperationException(); } 
/*  941 */     public void add(Byte2ShortMap.Entry ok) { throw new UnsupportedOperationException(); }
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
/*  805 */     private MapIterator() { this.next = Byte2ShortLinkedOpenHashMap.this.first;
/*  806 */       this.index = 0; }
/*      */ 
/*      */     private MapIterator(byte from) {
/*  809 */       if (Byte2ShortLinkedOpenHashMap.this.key[Byte2ShortLinkedOpenHashMap.this.last] == from) {
/*  810 */         this.prev = Byte2ShortLinkedOpenHashMap.this.last;
/*  811 */         this.index = Byte2ShortLinkedOpenHashMap.this.size;
/*      */       }
/*      */       else
/*      */       {
/*  815 */         int pos = HashCommon.murmurHash3(from) & Byte2ShortLinkedOpenHashMap.this.mask;
/*      */ 
/*  817 */         while (Byte2ShortLinkedOpenHashMap.this.used[pos] != 0) {
/*  818 */           if (Byte2ShortLinkedOpenHashMap.this.key[pos] == from)
/*      */           {
/*  820 */             this.next = ((int)Byte2ShortLinkedOpenHashMap.this.link[pos]);
/*  821 */             this.prev = pos;
/*  822 */             return;
/*      */           }
/*  824 */           pos = pos + 1 & Byte2ShortLinkedOpenHashMap.this.mask;
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
/*  838 */         this.index = Byte2ShortLinkedOpenHashMap.this.size;
/*  839 */         return;
/*      */       }
/*  841 */       int pos = Byte2ShortLinkedOpenHashMap.this.first;
/*  842 */       this.index = 1;
/*  843 */       while (pos != this.prev) {
/*  844 */         pos = (int)Byte2ShortLinkedOpenHashMap.this.link[pos];
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
/*  857 */       if (!hasNext()) return Byte2ShortLinkedOpenHashMap.this.size();
/*  858 */       this.curr = this.next;
/*  859 */       this.next = ((int)Byte2ShortLinkedOpenHashMap.this.link[this.curr]);
/*  860 */       this.prev = this.curr;
/*  861 */       if (this.index >= 0) this.index += 1;
/*  862 */       return this.curr;
/*      */     }
/*      */     public int previousEntry() {
/*  865 */       if (!hasPrevious()) return -1;
/*  866 */       this.curr = this.prev;
/*  867 */       this.prev = ((int)(Byte2ShortLinkedOpenHashMap.this.link[this.curr] >>> 32));
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
/*  880 */         this.prev = ((int)(Byte2ShortLinkedOpenHashMap.this.link[this.curr] >>> 32));
/*      */       }
/*      */       else {
/*  883 */         this.next = ((int)Byte2ShortLinkedOpenHashMap.this.link[this.curr]);
/*  884 */       }Byte2ShortLinkedOpenHashMap.this.size -= 1;
/*      */ 
/*  887 */       if (this.prev == -1) Byte2ShortLinkedOpenHashMap.this.first = this.next;
/*      */       else
/*  889 */         Byte2ShortLinkedOpenHashMap.this.link[this.prev] ^= (Byte2ShortLinkedOpenHashMap.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/*  890 */       if (this.next == -1) Byte2ShortLinkedOpenHashMap.this.last = this.prev;
/*      */       else
/*  892 */         Byte2ShortLinkedOpenHashMap.this.link[this.next] ^= (Byte2ShortLinkedOpenHashMap.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*      */       int last;
/*      */       while (true) {
/*  896 */         pos = (last = pos) + 1 & Byte2ShortLinkedOpenHashMap.this.mask;
/*  897 */         while (Byte2ShortLinkedOpenHashMap.this.used[pos] != 0) {
/*  898 */           int slot = HashCommon.murmurHash3(Byte2ShortLinkedOpenHashMap.this.key[pos]) & Byte2ShortLinkedOpenHashMap.this.mask;
/*  899 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/*  900 */           pos = pos + 1 & Byte2ShortLinkedOpenHashMap.this.mask;
/*      */         }
/*  902 */         if (Byte2ShortLinkedOpenHashMap.this.used[pos] == 0) break;
/*  903 */         Byte2ShortLinkedOpenHashMap.this.key[last] = Byte2ShortLinkedOpenHashMap.this.key[pos];
/*  904 */         Byte2ShortLinkedOpenHashMap.this.value[last] = Byte2ShortLinkedOpenHashMap.this.value[pos];
/*  905 */         if (this.next == pos) this.next = last;
/*  906 */         if (this.prev == pos) this.prev = last;
/*  907 */         Byte2ShortLinkedOpenHashMap.this.fixPointers(pos, last);
/*      */       }
/*  909 */       Byte2ShortLinkedOpenHashMap.this.used[last] = false;
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
/*      */     implements Byte2ShortMap.Entry, Map.Entry<Byte, Short>
/*      */   {
/*      */     private int index;
/*      */ 
/*      */     MapEntry(int index)
/*      */     {
/*  666 */       this.index = index;
/*      */     }
/*      */     public Byte getKey() {
/*  669 */       return Byte.valueOf(Byte2ShortLinkedOpenHashMap.this.key[this.index]);
/*      */     }
/*      */     public byte getByteKey() {
/*  672 */       return Byte2ShortLinkedOpenHashMap.this.key[this.index];
/*      */     }
/*      */     public Short getValue() {
/*  675 */       return Short.valueOf(Byte2ShortLinkedOpenHashMap.this.value[this.index]);
/*      */     }
/*      */     public short getShortValue() {
/*  678 */       return Byte2ShortLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public short setValue(short v) {
/*  681 */       short oldValue = Byte2ShortLinkedOpenHashMap.this.value[this.index];
/*  682 */       Byte2ShortLinkedOpenHashMap.this.value[this.index] = v;
/*  683 */       return oldValue;
/*      */     }
/*      */     public Short setValue(Short v) {
/*  686 */       return Short.valueOf(setValue(v.shortValue()));
/*      */     }
/*      */ 
/*      */     public boolean equals(Object o) {
/*  690 */       if (!(o instanceof Map.Entry)) return false;
/*  691 */       Map.Entry e = (Map.Entry)o;
/*  692 */       return (Byte2ShortLinkedOpenHashMap.this.key[this.index] == ((Byte)e.getKey()).byteValue()) && (Byte2ShortLinkedOpenHashMap.this.value[this.index] == ((Short)e.getValue()).shortValue());
/*      */     }
/*      */     public int hashCode() {
/*  695 */       return Byte2ShortLinkedOpenHashMap.this.key[this.index] ^ Byte2ShortLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */     public String toString() {
/*  698 */       return Byte2ShortLinkedOpenHashMap.this.key[this.index] + "=>" + Byte2ShortLinkedOpenHashMap.this.value[this.index];
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ShortLinkedOpenHashMap
 * JD-Core Version:    0.6.2
 */