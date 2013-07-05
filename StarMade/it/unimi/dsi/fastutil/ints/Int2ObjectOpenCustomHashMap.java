/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Int2ObjectOpenCustomHashMap<V> extends AbstractInt2ObjectMap<V>
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient int[] key;
/*     */   protected transient V[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Int2ObjectMap.FastEntrySet<V> entries;
/*     */   protected volatile transient IntSet keys;
/*     */   protected volatile transient ObjectCollection<V> values;
/*     */   protected IntHash.Strategy strategy;
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(int expected, float f, IntHash.Strategy strategy)
/*     */   {
/* 110 */     this.strategy = strategy;
/* 111 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 112 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 113 */     this.f = f;
/* 114 */     this.n = HashCommon.arraySize(expected, f);
/* 115 */     this.mask = (this.n - 1);
/* 116 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 117 */     this.key = new int[this.n];
/* 118 */     this.value = ((Object[])new Object[this.n]);
/* 119 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(int expected, IntHash.Strategy strategy)
/*     */   {
/* 127 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(IntHash.Strategy strategy)
/*     */   {
/* 134 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(Map<? extends Integer, ? extends V> m, float f, IntHash.Strategy strategy)
/*     */   {
/* 143 */     this(m.size(), f, strategy);
/* 144 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(Map<? extends Integer, ? extends V> m, IntHash.Strategy strategy)
/*     */   {
/* 152 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(Int2ObjectMap<V> m, float f, IntHash.Strategy strategy)
/*     */   {
/* 161 */     this(m.size(), f, strategy);
/* 162 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(Int2ObjectMap<V> m, IntHash.Strategy strategy)
/*     */   {
/* 170 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(int[] k, V[] v, float f, IntHash.Strategy strategy)
/*     */   {
/* 181 */     this(k.length, f, strategy);
/* 182 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 183 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap(int[] k, V[] v, IntHash.Strategy strategy)
/*     */   {
/* 193 */     this(k, v, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public IntHash.Strategy strategy()
/*     */   {
/* 200 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public V put(int k, V v)
/*     */   {
/* 208 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 210 */     while (this.used[pos] != 0) {
/* 211 */       if (this.strategy.equals(this.key[pos], k)) {
/* 212 */         Object oldValue = this.value[pos];
/* 213 */         this.value[pos] = v;
/* 214 */         return oldValue;
/*     */       }
/* 216 */       pos = pos + 1 & this.mask;
/*     */     }
/* 218 */     this.used[pos] = true;
/* 219 */     this.key[pos] = k;
/* 220 */     this.value[pos] = v;
/* 221 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 223 */     return this.defRetValue;
/*     */   }
/*     */   public V put(Integer ok, V ov) {
/* 226 */     Object v = ov;
/* 227 */     int k = ok.intValue();
/*     */ 
/* 229 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 231 */     while (this.used[pos] != 0) {
/* 232 */       if (this.strategy.equals(this.key[pos], k)) {
/* 233 */         Object oldValue = this.value[pos];
/* 234 */         this.value[pos] = v;
/* 235 */         return oldValue;
/*     */       }
/* 237 */       pos = pos + 1 & this.mask;
/*     */     }
/* 239 */     this.used[pos] = true;
/* 240 */     this.key[pos] = k;
/* 241 */     this.value[pos] = v;
/* 242 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 244 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 256 */       pos = (last = pos) + 1 & this.mask;
/* 257 */       while (this.used[pos] != 0) {
/* 258 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 259 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 260 */         pos = pos + 1 & this.mask;
/*     */       }
/* 262 */       if (this.used[pos] == 0) break;
/* 263 */       this.key[last] = this.key[pos];
/* 264 */       this.value[last] = this.value[pos];
/*     */     }
/* 266 */     this.used[last] = false;
/* 267 */     this.value[last] = null;
/* 268 */     return last;
/*     */   }
/*     */ 
/*     */   public V remove(int k)
/*     */   {
/* 273 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 275 */     while (this.used[pos] != 0) {
/* 276 */       if (this.strategy.equals(this.key[pos], k)) {
/* 277 */         this.size -= 1;
/* 278 */         Object v = this.value[pos];
/* 279 */         shiftKeys(pos);
/* 280 */         return v;
/*     */       }
/* 282 */       pos = pos + 1 & this.mask;
/*     */     }
/* 284 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public V remove(Object ok) {
/* 288 */     int k = ((Integer)ok).intValue();
/*     */ 
/* 290 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 292 */     while (this.used[pos] != 0) {
/* 293 */       if (this.strategy.equals(this.key[pos], k)) {
/* 294 */         this.size -= 1;
/* 295 */         Object v = this.value[pos];
/* 296 */         shiftKeys(pos);
/* 297 */         return v;
/*     */       }
/* 299 */       pos = pos + 1 & this.mask;
/*     */     }
/* 301 */     return this.defRetValue;
/*     */   }
/*     */   public V get(Integer ok) {
/* 304 */     int k = ok.intValue();
/*     */ 
/* 306 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 308 */     while (this.used[pos] != 0) {
/* 309 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 310 */       pos = pos + 1 & this.mask;
/*     */     }
/* 312 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public V get(int k)
/*     */   {
/* 317 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 319 */     while (this.used[pos] != 0) {
/* 320 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 321 */       pos = pos + 1 & this.mask;
/*     */     }
/* 323 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(int k)
/*     */   {
/* 328 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 330 */     while (this.used[pos] != 0) {
/* 331 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 332 */       pos = pos + 1 & this.mask;
/*     */     }
/* 334 */     return false;
/*     */   }
/*     */   public boolean containsValue(Object v) {
/* 337 */     Object[] value = this.value;
/* 338 */     boolean[] used = this.used;
/* 339 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] == null ? v != null : !value[i].equals(v)))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 349 */     if (this.size == 0) return;
/* 350 */     this.size = 0;
/* 351 */     BooleanArrays.fill(this.used, false);
/*     */ 
/* 353 */     ObjectArrays.fill(this.value, null);
/*     */   }
/*     */   public int size() {
/* 356 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 359 */     return this.size == 0;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public void growthFactor(int growthFactor)
/*     */   {
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public int growthFactor()
/*     */   {
/* 376 */     return 16;
/*     */   }
/*     */ 
/*     */   public Int2ObjectMap.FastEntrySet<V> int2ObjectEntrySet()
/*     */   {
/* 577 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 578 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public IntSet keySet()
/*     */   {
/* 611 */     if (this.keys == null) this.keys = new KeySet(null);
/* 612 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 625 */     if (this.values == null) this.values = new AbstractObjectCollection() {
/*     */         public ObjectIterator<V> iterator() {
/* 627 */           return new Int2ObjectOpenCustomHashMap.ValueIterator(Int2ObjectOpenCustomHashMap.this);
/*     */         }
/*     */         public int size() {
/* 630 */           return Int2ObjectOpenCustomHashMap.this.size;
/*     */         }
/*     */         public boolean contains(Object v) {
/* 633 */           return Int2ObjectOpenCustomHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 636 */           Int2ObjectOpenCustomHashMap.this.clear();
/*     */         }
/*     */       };
/* 639 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 653 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 668 */     int l = HashCommon.arraySize(this.size, this.f);
/* 669 */     if (l >= this.n) return true; try
/*     */     {
/* 671 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 673 */       return false;
/* 674 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 695 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 696 */     if (this.n <= l) return true; try
/*     */     {
/* 698 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 700 */       return false;
/* 701 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 714 */     int i = 0;
/* 715 */     boolean[] used = this.used;
/*     */ 
/* 717 */     int[] key = this.key;
/* 718 */     Object[] value = this.value;
/* 719 */     int newMask = newN - 1;
/* 720 */     int[] newKey = new int[newN];
/* 721 */     Object[] newValue = (Object[])new Object[newN];
/* 722 */     boolean[] newUsed = new boolean[newN];
/* 723 */     for (int j = this.size; j-- != 0; ) {
/* 724 */       while (used[i] == 0) i++;
/* 725 */       int k = key[i];
/* 726 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 727 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 728 */       newUsed[pos] = true;
/* 729 */       newKey[pos] = k;
/* 730 */       newValue[pos] = value[i];
/* 731 */       i++;
/*     */     }
/* 733 */     this.n = newN;
/* 734 */     this.mask = newMask;
/* 735 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 736 */     this.key = newKey;
/* 737 */     this.value = newValue;
/* 738 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Int2ObjectOpenCustomHashMap<V> clone()
/*     */   {
/*     */     Int2ObjectOpenCustomHashMap c;
/*     */     try
/*     */     {
/* 751 */       c = (Int2ObjectOpenCustomHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 754 */       throw new InternalError();
/*     */     }
/* 756 */     c.keys = null;
/* 757 */     c.values = null;
/* 758 */     c.entries = null;
/* 759 */     c.key = ((int[])this.key.clone());
/* 760 */     c.value = ((Object[])this.value.clone());
/* 761 */     c.used = ((boolean[])this.used.clone());
/* 762 */     c.strategy = this.strategy;
/* 763 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 775 */     int h = 0;
/* 776 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 777 */       while (this.used[i] == 0) i++;
/* 778 */       t = this.strategy.hashCode(this.key[i]);
/* 779 */       if (this != this.value[i])
/* 780 */         t ^= (this.value[i] == null ? 0 : this.value[i].hashCode());
/* 781 */       h += t;
/* 782 */       i++;
/*     */     }
/* 784 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 787 */     int[] key = this.key;
/* 788 */     Object[] value = this.value;
/* 789 */     MapIterator i = new MapIterator(null);
/* 790 */     s.defaultWriteObject();
/* 791 */     for (int j = this.size; j-- != 0; ) {
/* 792 */       int e = i.nextEntry();
/* 793 */       s.writeInt(key[e]);
/* 794 */       s.writeObject(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 799 */     s.defaultReadObject();
/* 800 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 801 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 802 */     this.mask = (this.n - 1);
/* 803 */     int[] key = this.key = new int[this.n];
/* 804 */     Object[] value = this.value = (Object[])new Object[this.n];
/* 805 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 808 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 809 */       int k = s.readInt();
/* 810 */       Object v = s.readObject();
/* 811 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 812 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 813 */       used[pos] = true;
/* 814 */       key[pos] = k;
/* 815 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Int2ObjectOpenCustomHashMap<V>.MapIterator
/*     */     implements ObjectIterator<V>
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 621 */       super(null); } 
/* 622 */     public V next() { return Int2ObjectOpenCustomHashMap.this.value[nextEntry()]; }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class KeySet extends AbstractIntSet
/*     */   {
/*     */     private KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public IntIterator iterator()
/*     */     {
/* 593 */       return new Int2ObjectOpenCustomHashMap.KeyIterator(Int2ObjectOpenCustomHashMap.this);
/*     */     }
/*     */     public int size() {
/* 596 */       return Int2ObjectOpenCustomHashMap.this.size;
/*     */     }
/*     */     public boolean contains(int k) {
/* 599 */       return Int2ObjectOpenCustomHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(int k) {
/* 602 */       int oldSize = Int2ObjectOpenCustomHashMap.this.size;
/* 603 */       Int2ObjectOpenCustomHashMap.this.remove(k);
/* 604 */       return Int2ObjectOpenCustomHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 607 */       Int2ObjectOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Int2ObjectOpenCustomHashMap.MapIterator
/*     */     implements IntIterator
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 587 */       super(null); } 
/* 588 */     public int nextInt() { return Int2ObjectOpenCustomHashMap.this.key[nextEntry()]; } 
/* 589 */     public Integer next() { return Integer.valueOf(Int2ObjectOpenCustomHashMap.this.key[nextEntry()]); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Int2ObjectMap.Entry<V>>
/*     */     implements Int2ObjectMap.FastEntrySet<V>
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Int2ObjectMap.Entry<V>> iterator()
/*     */     {
/* 533 */       return new Int2ObjectOpenCustomHashMap.EntryIterator(Int2ObjectOpenCustomHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Int2ObjectMap.Entry<V>> fastIterator() {
/* 536 */       return new Int2ObjectOpenCustomHashMap.FastEntryIterator(Int2ObjectOpenCustomHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 540 */       if (!(o instanceof Map.Entry)) return false;
/* 541 */       Map.Entry e = (Map.Entry)o;
/* 542 */       int k = ((Integer)e.getKey()).intValue();
/*     */ 
/* 544 */       int pos = HashCommon.murmurHash3(Int2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Int2ObjectOpenCustomHashMap.this.mask;
/*     */ 
/* 546 */       while (Int2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 547 */         if (Int2ObjectOpenCustomHashMap.this.strategy.equals(Int2ObjectOpenCustomHashMap.this.key[pos], k)) return Int2ObjectOpenCustomHashMap.this.value[pos] == null ? false : e.getValue() == null ? true : Int2ObjectOpenCustomHashMap.this.value[pos].equals(e.getValue());
/* 548 */         pos = pos + 1 & Int2ObjectOpenCustomHashMap.this.mask;
/*     */       }
/* 550 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 554 */       if (!(o instanceof Map.Entry)) return false;
/* 555 */       Map.Entry e = (Map.Entry)o;
/* 556 */       int k = ((Integer)e.getKey()).intValue();
/*     */ 
/* 558 */       int pos = HashCommon.murmurHash3(Int2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Int2ObjectOpenCustomHashMap.this.mask;
/*     */ 
/* 560 */       while (Int2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 561 */         if (Int2ObjectOpenCustomHashMap.this.strategy.equals(Int2ObjectOpenCustomHashMap.this.key[pos], k)) {
/* 562 */           Int2ObjectOpenCustomHashMap.this.remove(e.getKey());
/* 563 */           return true;
/*     */         }
/* 565 */         pos = pos + 1 & Int2ObjectOpenCustomHashMap.this.mask;
/*     */       }
/* 567 */       return false;
/*     */     }
/*     */     public int size() {
/* 570 */       return Int2ObjectOpenCustomHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 573 */       Int2ObjectOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Int2ObjectOpenCustomHashMap<V>.MapIterator
/*     */     implements ObjectIterator<Int2ObjectMap.Entry<V>>
/*     */   {
/* 523 */     final AbstractInt2ObjectMap.BasicEntry<V> entry = new AbstractInt2ObjectMap.BasicEntry(0, null);
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 522 */       super(null);
/*     */     }
/*     */     public AbstractInt2ObjectMap.BasicEntry<V> next() {
/* 525 */       int e = nextEntry();
/* 526 */       this.entry.key = Int2ObjectOpenCustomHashMap.this.key[e];
/* 527 */       this.entry.value = Int2ObjectOpenCustomHashMap.this.value[e];
/* 528 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Int2ObjectOpenCustomHashMap<V>.MapIterator
/*     */     implements ObjectIterator<Int2ObjectMap.Entry<V>>
/*     */   {
/*     */     private Int2ObjectOpenCustomHashMap<V>.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 511 */       super(null);
/*     */     }
/*     */     public Int2ObjectMap.Entry<V> next() {
/* 514 */       return this.entry = new Int2ObjectOpenCustomHashMap.MapEntry(Int2ObjectOpenCustomHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 518 */       super.remove();
/* 519 */       Int2ObjectOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MapIterator
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     IntArrayList wrapped;
/*     */ 
/*     */     private MapIterator()
/*     */     {
/* 419 */       this.pos = Int2ObjectOpenCustomHashMap.this.n;
/*     */ 
/* 422 */       this.last = -1;
/*     */ 
/* 424 */       this.c = Int2ObjectOpenCustomHashMap.this.size;
/*     */ 
/* 429 */       boolean[] used = Int2ObjectOpenCustomHashMap.this.used;
/* 430 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 433 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 436 */       if (!hasNext()) throw new NoSuchElementException();
/* 437 */       this.c -= 1;
/*     */ 
/* 439 */       if (this.pos < 0) {
/* 440 */         int k = this.wrapped.getInt(-(this.last = --this.pos) - 2);
/*     */ 
/* 442 */         int pos = HashCommon.murmurHash3(Int2ObjectOpenCustomHashMap.this.strategy.hashCode(k)) & Int2ObjectOpenCustomHashMap.this.mask;
/*     */ 
/* 444 */         while (Int2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 445 */           if (Int2ObjectOpenCustomHashMap.this.strategy.equals(Int2ObjectOpenCustomHashMap.this.key[pos], k)) return pos;
/* 446 */           pos = pos + 1 & Int2ObjectOpenCustomHashMap.this.mask;
/*     */         }
/*     */       }
/* 449 */       this.last = this.pos;
/*     */ 
/* 451 */       if (this.c != 0) {
/* 452 */         boolean[] used = Int2ObjectOpenCustomHashMap.this.used;
/* 453 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 456 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 469 */         pos = (last = pos) + 1 & Int2ObjectOpenCustomHashMap.this.mask;
/* 470 */         while (Int2ObjectOpenCustomHashMap.this.used[pos] != 0) {
/* 471 */           int slot = HashCommon.murmurHash3(Int2ObjectOpenCustomHashMap.this.strategy.hashCode(Int2ObjectOpenCustomHashMap.this.key[pos])) & Int2ObjectOpenCustomHashMap.this.mask;
/* 472 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 473 */           pos = pos + 1 & Int2ObjectOpenCustomHashMap.this.mask;
/*     */         }
/* 475 */         if (Int2ObjectOpenCustomHashMap.this.used[pos] == 0) break;
/* 476 */         if (pos < last)
/*     */         {
/* 478 */           if (this.wrapped == null) this.wrapped = new IntArrayList();
/* 479 */           this.wrapped.add(Int2ObjectOpenCustomHashMap.this.key[pos]);
/*     */         }
/* 481 */         Int2ObjectOpenCustomHashMap.this.key[last] = Int2ObjectOpenCustomHashMap.this.key[pos];
/* 482 */         Int2ObjectOpenCustomHashMap.this.value[last] = Int2ObjectOpenCustomHashMap.this.value[pos];
/*     */       }
/* 484 */       Int2ObjectOpenCustomHashMap.this.used[last] = false;
/* 485 */       Int2ObjectOpenCustomHashMap.this.value[last] = null;
/* 486 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 490 */       if (this.last == -1) throw new IllegalStateException();
/* 491 */       if (this.pos < -1)
/*     */       {
/* 493 */         Int2ObjectOpenCustomHashMap.this.remove(this.wrapped.getInt(-this.pos - 2));
/* 494 */         this.last = -1;
/* 495 */         return;
/*     */       }
/* 497 */       Int2ObjectOpenCustomHashMap.this.size -= 1;
/* 498 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 499 */         this.c += 1;
/* 500 */         nextEntry();
/*     */       }
/* 502 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 506 */       int i = n;
/* 507 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 508 */       return n - i - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class MapEntry
/*     */     implements Int2ObjectMap.Entry<V>, Map.Entry<Integer, V>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 386 */       this.index = index;
/*     */     }
/*     */     public Integer getKey() {
/* 389 */       return Integer.valueOf(Int2ObjectOpenCustomHashMap.this.key[this.index]);
/*     */     }
/*     */     public int getIntKey() {
/* 392 */       return Int2ObjectOpenCustomHashMap.this.key[this.index];
/*     */     }
/*     */     public V getValue() {
/* 395 */       return Int2ObjectOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public V setValue(V v) {
/* 398 */       Object oldValue = Int2ObjectOpenCustomHashMap.this.value[this.index];
/* 399 */       Int2ObjectOpenCustomHashMap.this.value[this.index] = v;
/* 400 */       return oldValue;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 404 */       if (!(o instanceof Map.Entry)) return false;
/* 405 */       Map.Entry e = (Map.Entry)o;
/* 406 */       return (Int2ObjectOpenCustomHashMap.this.strategy.equals(Int2ObjectOpenCustomHashMap.this.key[this.index], ((Integer)e.getKey()).intValue())) && (Int2ObjectOpenCustomHashMap.this.value[this.index] == null ? e.getValue() == null : Int2ObjectOpenCustomHashMap.this.value[this.index].equals(e.getValue()));
/*     */     }
/*     */     public int hashCode() {
/* 409 */       return Int2ObjectOpenCustomHashMap.this.strategy.hashCode(Int2ObjectOpenCustomHashMap.this.key[this.index]) ^ (Int2ObjectOpenCustomHashMap.this.value[this.index] == null ? 0 : Int2ObjectOpenCustomHashMap.this.value[this.index].hashCode());
/*     */     }
/*     */     public String toString() {
/* 412 */       return Int2ObjectOpenCustomHashMap.this.key[this.index] + "=>" + Int2ObjectOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.Int2ObjectOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */