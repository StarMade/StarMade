/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import it.unimi.dsi.fastutil.floats.AbstractFloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatCollection;
/*     */ import it.unimi.dsi.fastutil.floats.FloatIterator;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Object2FloatOpenCustomHashMap<K> extends AbstractObject2FloatMap<K>
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient K[] key;
/*     */   protected transient float[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Object2FloatMap.FastEntrySet<K> entries;
/*     */   protected volatile transient ObjectSet<K> keys;
/*     */   protected volatile transient FloatCollection values;
/*     */   protected Hash.Strategy<K> strategy;
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 110 */     this.strategy = strategy;
/* 111 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 112 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 113 */     this.f = f;
/* 114 */     this.n = HashCommon.arraySize(expected, f);
/* 115 */     this.mask = (this.n - 1);
/* 116 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 117 */     this.key = ((Object[])new Object[this.n]);
/* 118 */     this.value = new float[this.n];
/* 119 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*     */   {
/* 127 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(Hash.Strategy<K> strategy)
/*     */   {
/* 134 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(Map<? extends K, ? extends Float> m, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 143 */     this(m.size(), f, strategy);
/* 144 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(Map<? extends K, ? extends Float> m, Hash.Strategy<K> strategy)
/*     */   {
/* 152 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(Object2FloatMap<K> m, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 161 */     this(m.size(), f, strategy);
/* 162 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(Object2FloatMap<K> m, Hash.Strategy<K> strategy)
/*     */   {
/* 170 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(K[] k, float[] v, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 181 */     this(k.length, f, strategy);
/* 182 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 183 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap(K[] k, float[] v, Hash.Strategy<K> strategy)
/*     */   {
/* 193 */     this(k, v, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Hash.Strategy<K> strategy()
/*     */   {
/* 200 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public float put(K k, float v)
/*     */   {
/* 208 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 210 */     while (this.used[pos] != 0) {
/* 211 */       if (this.strategy.equals(this.key[pos], k)) {
/* 212 */         float oldValue = this.value[pos];
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
/*     */   public Float put(K ok, Float ov) {
/* 226 */     float v = ov.floatValue();
/* 227 */     Object k = ok;
/*     */ 
/* 229 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 231 */     while (this.used[pos] != 0) {
/* 232 */       if (this.strategy.equals(this.key[pos], k)) {
/* 233 */         Float oldValue = Float.valueOf(this.value[pos]);
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
/* 244 */     return null;
/*     */   }
/*     */ 
/*     */   public float add(K k, float incr)
/*     */   {
/* 259 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 261 */     while (this.used[pos] != 0) {
/* 262 */       if (this.strategy.equals(this.key[pos], k)) {
/* 263 */         float oldValue = this.value[pos];
/* 264 */         this.value[pos] += incr;
/* 265 */         return oldValue;
/*     */       }
/* 267 */       pos = pos + 1 & this.mask;
/*     */     }
/* 269 */     this.used[pos] = true;
/* 270 */     this.key[pos] = k;
/* 271 */     this.value[pos] = (this.defRetValue + incr);
/* 272 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 274 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 286 */       pos = (last = pos) + 1 & this.mask;
/* 287 */       while (this.used[pos] != 0) {
/* 288 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 289 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 290 */         pos = pos + 1 & this.mask;
/*     */       }
/* 292 */       if (this.used[pos] == 0) break;
/* 293 */       this.key[last] = this.key[pos];
/* 294 */       this.value[last] = this.value[pos];
/*     */     }
/* 296 */     this.used[last] = false;
/* 297 */     this.key[last] = null;
/* 298 */     return last;
/*     */   }
/*     */ 
/*     */   public float removeFloat(Object k)
/*     */   {
/* 303 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 305 */     while (this.used[pos] != 0) {
/* 306 */       if (this.strategy.equals(this.key[pos], k)) {
/* 307 */         this.size -= 1;
/* 308 */         float v = this.value[pos];
/* 309 */         shiftKeys(pos);
/* 310 */         return v;
/*     */       }
/* 312 */       pos = pos + 1 & this.mask;
/*     */     }
/* 314 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public Float remove(Object ok) {
/* 318 */     Object k = ok;
/*     */ 
/* 320 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 322 */     while (this.used[pos] != 0) {
/* 323 */       if (this.strategy.equals(this.key[pos], k)) {
/* 324 */         this.size -= 1;
/* 325 */         float v = this.value[pos];
/* 326 */         shiftKeys(pos);
/* 327 */         return Float.valueOf(v);
/*     */       }
/* 329 */       pos = pos + 1 & this.mask;
/*     */     }
/* 331 */     return null;
/*     */   }
/*     */ 
/*     */   public float getFloat(Object k)
/*     */   {
/* 336 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 338 */     while (this.used[pos] != 0) {
/* 339 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 340 */       pos = pos + 1 & this.mask;
/*     */     }
/* 342 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k)
/*     */   {
/* 347 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 349 */     while (this.used[pos] != 0) {
/* 350 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 351 */       pos = pos + 1 & this.mask;
/*     */     }
/* 353 */     return false;
/*     */   }
/*     */   public boolean containsValue(float v) {
/* 356 */     float[] value = this.value;
/* 357 */     boolean[] used = this.used;
/* 358 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 368 */     if (this.size == 0) return;
/* 369 */     this.size = 0;
/* 370 */     BooleanArrays.fill(this.used, false);
/*     */ 
/* 372 */     ObjectArrays.fill(this.key, null);
/*     */   }
/*     */   public int size() {
/* 375 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 378 */     return this.size == 0;
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
/* 395 */     return 16;
/*     */   }
/*     */ 
/*     */   public Object2FloatMap.FastEntrySet<K> object2FloatEntrySet()
/*     */   {
/* 599 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 600 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public ObjectSet<K> keySet()
/*     */   {
/* 632 */     if (this.keys == null) this.keys = new KeySet(null);
/* 633 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public FloatCollection values()
/*     */   {
/* 647 */     if (this.values == null) this.values = new AbstractFloatCollection() {
/*     */         public FloatIterator iterator() {
/* 649 */           return new Object2FloatOpenCustomHashMap.ValueIterator(Object2FloatOpenCustomHashMap.this);
/*     */         }
/*     */         public int size() {
/* 652 */           return Object2FloatOpenCustomHashMap.this.size;
/*     */         }
/*     */         public boolean contains(float v) {
/* 655 */           return Object2FloatOpenCustomHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 658 */           Object2FloatOpenCustomHashMap.this.clear();
/*     */         }
/*     */       };
/* 661 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 675 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 690 */     int l = HashCommon.arraySize(this.size, this.f);
/* 691 */     if (l >= this.n) return true; try
/*     */     {
/* 693 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 695 */       return false;
/* 696 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 717 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 718 */     if (this.n <= l) return true; try
/*     */     {
/* 720 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 722 */       return false;
/* 723 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 736 */     int i = 0;
/* 737 */     boolean[] used = this.used;
/*     */ 
/* 739 */     Object[] key = this.key;
/* 740 */     float[] value = this.value;
/* 741 */     int newMask = newN - 1;
/* 742 */     Object[] newKey = (Object[])new Object[newN];
/* 743 */     float[] newValue = new float[newN];
/* 744 */     boolean[] newUsed = new boolean[newN];
/* 745 */     for (int j = this.size; j-- != 0; ) {
/* 746 */       while (used[i] == 0) i++;
/* 747 */       Object k = key[i];
/* 748 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 749 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 750 */       newUsed[pos] = true;
/* 751 */       newKey[pos] = k;
/* 752 */       newValue[pos] = value[i];
/* 753 */       i++;
/*     */     }
/* 755 */     this.n = newN;
/* 756 */     this.mask = newMask;
/* 757 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 758 */     this.key = newKey;
/* 759 */     this.value = newValue;
/* 760 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Object2FloatOpenCustomHashMap<K> clone()
/*     */   {
/*     */     Object2FloatOpenCustomHashMap c;
/*     */     try
/*     */     {
/* 773 */       c = (Object2FloatOpenCustomHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 776 */       throw new InternalError();
/*     */     }
/* 778 */     c.keys = null;
/* 779 */     c.values = null;
/* 780 */     c.entries = null;
/* 781 */     c.key = ((Object[])this.key.clone());
/* 782 */     c.value = ((float[])this.value.clone());
/* 783 */     c.used = ((boolean[])this.used.clone());
/* 784 */     c.strategy = this.strategy;
/* 785 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 797 */     int h = 0;
/* 798 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 799 */       while (this.used[i] == 0) i++;
/* 800 */       if (this != this.key[i])
/* 801 */         t = this.strategy.hashCode(this.key[i]);
/* 802 */       t ^= HashCommon.float2int(this.value[i]);
/* 803 */       h += t;
/* 804 */       i++;
/*     */     }
/* 806 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 809 */     Object[] key = this.key;
/* 810 */     float[] value = this.value;
/* 811 */     MapIterator i = new MapIterator(null);
/* 812 */     s.defaultWriteObject();
/* 813 */     for (int j = this.size; j-- != 0; ) {
/* 814 */       int e = i.nextEntry();
/* 815 */       s.writeObject(key[e]);
/* 816 */       s.writeFloat(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 821 */     s.defaultReadObject();
/* 822 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 823 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 824 */     this.mask = (this.n - 1);
/* 825 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 826 */     float[] value = this.value = new float[this.n];
/* 827 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 830 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 831 */       Object k = s.readObject();
/* 832 */       float v = s.readFloat();
/* 833 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 834 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 835 */       used[pos] = true;
/* 836 */       key[pos] = k;
/* 837 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Object2FloatOpenCustomHashMap.MapIterator
/*     */     implements FloatIterator
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 642 */       super(null); } 
/* 643 */     public float nextFloat() { return Object2FloatOpenCustomHashMap.this.value[nextEntry()]; } 
/* 644 */     public Float next() { return Float.valueOf(Object2FloatOpenCustomHashMap.this.value[nextEntry()]); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class KeySet extends AbstractObjectSet<K>
/*     */   {
/*     */     private KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<K> iterator()
/*     */     {
/* 614 */       return new Object2FloatOpenCustomHashMap.KeyIterator(Object2FloatOpenCustomHashMap.this);
/*     */     }
/*     */     public int size() {
/* 617 */       return Object2FloatOpenCustomHashMap.this.size;
/*     */     }
/*     */     public boolean contains(Object k) {
/* 620 */       return Object2FloatOpenCustomHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(Object k) {
/* 623 */       int oldSize = Object2FloatOpenCustomHashMap.this.size;
/* 624 */       Object2FloatOpenCustomHashMap.this.remove(k);
/* 625 */       return Object2FloatOpenCustomHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 628 */       Object2FloatOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Object2FloatOpenCustomHashMap<K>.MapIterator
/*     */     implements ObjectIterator<K>
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 609 */       super(null); } 
/* 610 */     public K next() { return Object2FloatOpenCustomHashMap.this.key[nextEntry()]; }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Object2FloatMap.Entry<K>>
/*     */     implements Object2FloatMap.FastEntrySet<K>
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Object2FloatMap.Entry<K>> iterator()
/*     */     {
/* 555 */       return new Object2FloatOpenCustomHashMap.EntryIterator(Object2FloatOpenCustomHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Object2FloatMap.Entry<K>> fastIterator() {
/* 558 */       return new Object2FloatOpenCustomHashMap.FastEntryIterator(Object2FloatOpenCustomHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 562 */       if (!(o instanceof Map.Entry)) return false;
/* 563 */       Map.Entry e = (Map.Entry)o;
/* 564 */       Object k = e.getKey();
/*     */ 
/* 566 */       int pos = HashCommon.murmurHash3(Object2FloatOpenCustomHashMap.this.strategy.hashCode(k)) & Object2FloatOpenCustomHashMap.this.mask;
/*     */ 
/* 568 */       while (Object2FloatOpenCustomHashMap.this.used[pos] != 0) {
/* 569 */         if (Object2FloatOpenCustomHashMap.this.strategy.equals(Object2FloatOpenCustomHashMap.this.key[pos], k)) return Object2FloatOpenCustomHashMap.this.value[pos] == ((Float)e.getValue()).floatValue();
/* 570 */         pos = pos + 1 & Object2FloatOpenCustomHashMap.this.mask;
/*     */       }
/* 572 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 576 */       if (!(o instanceof Map.Entry)) return false;
/* 577 */       Map.Entry e = (Map.Entry)o;
/* 578 */       Object k = e.getKey();
/*     */ 
/* 580 */       int pos = HashCommon.murmurHash3(Object2FloatOpenCustomHashMap.this.strategy.hashCode(k)) & Object2FloatOpenCustomHashMap.this.mask;
/*     */ 
/* 582 */       while (Object2FloatOpenCustomHashMap.this.used[pos] != 0) {
/* 583 */         if (Object2FloatOpenCustomHashMap.this.strategy.equals(Object2FloatOpenCustomHashMap.this.key[pos], k)) {
/* 584 */           Object2FloatOpenCustomHashMap.this.remove(e.getKey());
/* 585 */           return true;
/*     */         }
/* 587 */         pos = pos + 1 & Object2FloatOpenCustomHashMap.this.mask;
/*     */       }
/* 589 */       return false;
/*     */     }
/*     */     public int size() {
/* 592 */       return Object2FloatOpenCustomHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 595 */       Object2FloatOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Object2FloatOpenCustomHashMap<K>.MapIterator
/*     */     implements ObjectIterator<Object2FloatMap.Entry<K>>
/*     */   {
/* 545 */     final AbstractObject2FloatMap.BasicEntry<K> entry = new AbstractObject2FloatMap.BasicEntry(null, 0.0F);
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 544 */       super(null);
/*     */     }
/*     */     public AbstractObject2FloatMap.BasicEntry<K> next() {
/* 547 */       int e = nextEntry();
/* 548 */       this.entry.key = Object2FloatOpenCustomHashMap.this.key[e];
/* 549 */       this.entry.value = Object2FloatOpenCustomHashMap.this.value[e];
/* 550 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Object2FloatOpenCustomHashMap<K>.MapIterator
/*     */     implements ObjectIterator<Object2FloatMap.Entry<K>>
/*     */   {
/*     */     private Object2FloatOpenCustomHashMap<K>.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 533 */       super(null);
/*     */     }
/*     */     public Object2FloatMap.Entry<K> next() {
/* 536 */       return this.entry = new Object2FloatOpenCustomHashMap.MapEntry(Object2FloatOpenCustomHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 540 */       super.remove();
/* 541 */       Object2FloatOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MapIterator
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     ObjectArrayList<K> wrapped;
/*     */ 
/*     */     private MapIterator()
/*     */     {
/* 441 */       this.pos = Object2FloatOpenCustomHashMap.this.n;
/*     */ 
/* 444 */       this.last = -1;
/*     */ 
/* 446 */       this.c = Object2FloatOpenCustomHashMap.this.size;
/*     */ 
/* 451 */       boolean[] used = Object2FloatOpenCustomHashMap.this.used;
/* 452 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 455 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 458 */       if (!hasNext()) throw new NoSuchElementException();
/* 459 */       this.c -= 1;
/*     */ 
/* 461 */       if (this.pos < 0) {
/* 462 */         Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/*     */ 
/* 464 */         int pos = HashCommon.murmurHash3(Object2FloatOpenCustomHashMap.this.strategy.hashCode(k)) & Object2FloatOpenCustomHashMap.this.mask;
/*     */ 
/* 466 */         while (Object2FloatOpenCustomHashMap.this.used[pos] != 0) {
/* 467 */           if (Object2FloatOpenCustomHashMap.this.strategy.equals(Object2FloatOpenCustomHashMap.this.key[pos], k)) return pos;
/* 468 */           pos = pos + 1 & Object2FloatOpenCustomHashMap.this.mask;
/*     */         }
/*     */       }
/* 471 */       this.last = this.pos;
/*     */ 
/* 473 */       if (this.c != 0) {
/* 474 */         boolean[] used = Object2FloatOpenCustomHashMap.this.used;
/* 475 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 478 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 491 */         pos = (last = pos) + 1 & Object2FloatOpenCustomHashMap.this.mask;
/* 492 */         while (Object2FloatOpenCustomHashMap.this.used[pos] != 0) {
/* 493 */           int slot = HashCommon.murmurHash3(Object2FloatOpenCustomHashMap.this.strategy.hashCode(Object2FloatOpenCustomHashMap.this.key[pos])) & Object2FloatOpenCustomHashMap.this.mask;
/* 494 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 495 */           pos = pos + 1 & Object2FloatOpenCustomHashMap.this.mask;
/*     */         }
/* 497 */         if (Object2FloatOpenCustomHashMap.this.used[pos] == 0) break;
/* 498 */         if (pos < last)
/*     */         {
/* 500 */           if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 501 */           this.wrapped.add(Object2FloatOpenCustomHashMap.this.key[pos]);
/*     */         }
/* 503 */         Object2FloatOpenCustomHashMap.this.key[last] = Object2FloatOpenCustomHashMap.this.key[pos];
/* 504 */         Object2FloatOpenCustomHashMap.this.value[last] = Object2FloatOpenCustomHashMap.this.value[pos];
/*     */       }
/* 506 */       Object2FloatOpenCustomHashMap.this.used[last] = false;
/* 507 */       Object2FloatOpenCustomHashMap.this.key[last] = null;
/* 508 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 512 */       if (this.last == -1) throw new IllegalStateException();
/* 513 */       if (this.pos < -1)
/*     */       {
/* 515 */         Object2FloatOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 516 */         this.last = -1;
/* 517 */         return;
/*     */       }
/* 519 */       Object2FloatOpenCustomHashMap.this.size -= 1;
/* 520 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 521 */         this.c += 1;
/* 522 */         nextEntry();
/*     */       }
/* 524 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 528 */       int i = n;
/* 529 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 530 */       return n - i - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class MapEntry
/*     */     implements Object2FloatMap.Entry<K>, Map.Entry<K, Float>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 405 */       this.index = index;
/*     */     }
/*     */     public K getKey() {
/* 408 */       return Object2FloatOpenCustomHashMap.this.key[this.index];
/*     */     }
/*     */     public Float getValue() {
/* 411 */       return Float.valueOf(Object2FloatOpenCustomHashMap.this.value[this.index]);
/*     */     }
/*     */     public float getFloatValue() {
/* 414 */       return Object2FloatOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public float setValue(float v) {
/* 417 */       float oldValue = Object2FloatOpenCustomHashMap.this.value[this.index];
/* 418 */       Object2FloatOpenCustomHashMap.this.value[this.index] = v;
/* 419 */       return oldValue;
/*     */     }
/*     */     public Float setValue(Float v) {
/* 422 */       return Float.valueOf(setValue(v.floatValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 426 */       if (!(o instanceof Map.Entry)) return false;
/* 427 */       Map.Entry e = (Map.Entry)o;
/* 428 */       return (Object2FloatOpenCustomHashMap.this.strategy.equals(Object2FloatOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2FloatOpenCustomHashMap.this.value[this.index] == ((Float)e.getValue()).floatValue());
/*     */     }
/*     */     public int hashCode() {
/* 431 */       return Object2FloatOpenCustomHashMap.this.strategy.hashCode(Object2FloatOpenCustomHashMap.this.key[this.index]) ^ HashCommon.float2int(Object2FloatOpenCustomHashMap.this.value[this.index]);
/*     */     }
/*     */     public String toString() {
/* 434 */       return Object2FloatOpenCustomHashMap.this.key[this.index] + "=>" + Object2FloatOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2FloatOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */