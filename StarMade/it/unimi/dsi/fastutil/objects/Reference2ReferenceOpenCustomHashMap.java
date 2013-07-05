/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Reference2ReferenceOpenCustomHashMap<K, V> extends AbstractReference2ReferenceMap<K, V>
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient K[] key;
/*     */   protected transient V[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Reference2ReferenceMap.FastEntrySet<K, V> entries;
/*     */   protected volatile transient ReferenceSet<K> keys;
/*     */   protected volatile transient ReferenceCollection<V> values;
/*     */   protected Hash.Strategy<K> strategy;
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 108 */     this.strategy = strategy;
/* 109 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 110 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 111 */     this.f = f;
/* 112 */     this.n = HashCommon.arraySize(expected, f);
/* 113 */     this.mask = (this.n - 1);
/* 114 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 115 */     this.key = ((Object[])new Object[this.n]);
/* 116 */     this.value = ((Object[])new Object[this.n]);
/* 117 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*     */   {
/* 125 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(Hash.Strategy<K> strategy)
/*     */   {
/* 132 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(Map<? extends K, ? extends V> m, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 141 */     this(m.size(), f, strategy);
/* 142 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(Map<? extends K, ? extends V> m, Hash.Strategy<K> strategy)
/*     */   {
/* 150 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(Reference2ReferenceMap<K, V> m, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 159 */     this(m.size(), f, strategy);
/* 160 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(Reference2ReferenceMap<K, V> m, Hash.Strategy<K> strategy)
/*     */   {
/* 168 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(K[] k, V[] v, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 179 */     this(k.length, f, strategy);
/* 180 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 181 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap(K[] k, V[] v, Hash.Strategy<K> strategy)
/*     */   {
/* 191 */     this(k, v, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Hash.Strategy<K> strategy()
/*     */   {
/* 198 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public V put(K k, V v)
/*     */   {
/* 206 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 208 */     while (this.used[pos] != 0) {
/* 209 */       if (this.strategy.equals(this.key[pos], k)) {
/* 210 */         Object oldValue = this.value[pos];
/* 211 */         this.value[pos] = v;
/* 212 */         return oldValue;
/*     */       }
/* 214 */       pos = pos + 1 & this.mask;
/*     */     }
/* 216 */     this.used[pos] = true;
/* 217 */     this.key[pos] = k;
/* 218 */     this.value[pos] = v;
/* 219 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 221 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 233 */       pos = (last = pos) + 1 & this.mask;
/* 234 */       while (this.used[pos] != 0) {
/* 235 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 236 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 237 */         pos = pos + 1 & this.mask;
/*     */       }
/* 239 */       if (this.used[pos] == 0) break;
/* 240 */       this.key[last] = this.key[pos];
/* 241 */       this.value[last] = this.value[pos];
/*     */     }
/* 243 */     this.used[last] = false;
/* 244 */     this.key[last] = null;
/* 245 */     this.value[last] = null;
/* 246 */     return last;
/*     */   }
/*     */ 
/*     */   public V remove(Object k)
/*     */   {
/* 251 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 253 */     while (this.used[pos] != 0) {
/* 254 */       if (this.strategy.equals(this.key[pos], k)) {
/* 255 */         this.size -= 1;
/* 256 */         Object v = this.value[pos];
/* 257 */         shiftKeys(pos);
/* 258 */         return v;
/*     */       }
/* 260 */       pos = pos + 1 & this.mask;
/*     */     }
/* 262 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public V get(Object k)
/*     */   {
/* 267 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 269 */     while (this.used[pos] != 0) {
/* 270 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 271 */       pos = pos + 1 & this.mask;
/*     */     }
/* 273 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k)
/*     */   {
/* 278 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 280 */     while (this.used[pos] != 0) {
/* 281 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 282 */       pos = pos + 1 & this.mask;
/*     */     }
/* 284 */     return false;
/*     */   }
/*     */   public boolean containsValue(Object v) {
/* 287 */     Object[] value = this.value;
/* 288 */     boolean[] used = this.used;
/* 289 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 299 */     if (this.size == 0) return;
/* 300 */     this.size = 0;
/* 301 */     BooleanArrays.fill(this.used, false);
/*     */ 
/* 303 */     ObjectArrays.fill(this.key, null);
/* 304 */     ObjectArrays.fill(this.value, null);
/*     */   }
/*     */   public int size() {
/* 307 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 310 */     return this.size == 0;
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
/* 327 */     return 16;
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceMap.FastEntrySet<K, V> reference2ReferenceEntrySet()
/*     */   {
/* 526 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 527 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public ReferenceSet<K> keySet()
/*     */   {
/* 559 */     if (this.keys == null) this.keys = new KeySet(null);
/* 560 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 573 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*     */         public ObjectIterator<V> iterator() {
/* 575 */           return new Reference2ReferenceOpenCustomHashMap.ValueIterator(Reference2ReferenceOpenCustomHashMap.this);
/*     */         }
/*     */         public int size() {
/* 578 */           return Reference2ReferenceOpenCustomHashMap.this.size;
/*     */         }
/*     */         public boolean contains(Object v) {
/* 581 */           return Reference2ReferenceOpenCustomHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 584 */           Reference2ReferenceOpenCustomHashMap.this.clear();
/*     */         }
/*     */       };
/* 587 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 601 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 616 */     int l = HashCommon.arraySize(this.size, this.f);
/* 617 */     if (l >= this.n) return true; try
/*     */     {
/* 619 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 621 */       return false;
/* 622 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 643 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 644 */     if (this.n <= l) return true; try
/*     */     {
/* 646 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 648 */       return false;
/* 649 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 662 */     int i = 0;
/* 663 */     boolean[] used = this.used;
/*     */ 
/* 665 */     Object[] key = this.key;
/* 666 */     Object[] value = this.value;
/* 667 */     int newMask = newN - 1;
/* 668 */     Object[] newKey = (Object[])new Object[newN];
/* 669 */     Object[] newValue = (Object[])new Object[newN];
/* 670 */     boolean[] newUsed = new boolean[newN];
/* 671 */     for (int j = this.size; j-- != 0; ) {
/* 672 */       while (used[i] == 0) i++;
/* 673 */       Object k = key[i];
/* 674 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 675 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 676 */       newUsed[pos] = true;
/* 677 */       newKey[pos] = k;
/* 678 */       newValue[pos] = value[i];
/* 679 */       i++;
/*     */     }
/* 681 */     this.n = newN;
/* 682 */     this.mask = newMask;
/* 683 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 684 */     this.key = newKey;
/* 685 */     this.value = newValue;
/* 686 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenCustomHashMap<K, V> clone()
/*     */   {
/*     */     Reference2ReferenceOpenCustomHashMap c;
/*     */     try
/*     */     {
/* 699 */       c = (Reference2ReferenceOpenCustomHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 702 */       throw new InternalError();
/*     */     }
/* 704 */     c.keys = null;
/* 705 */     c.values = null;
/* 706 */     c.entries = null;
/* 707 */     c.key = ((Object[])this.key.clone());
/* 708 */     c.value = ((Object[])this.value.clone());
/* 709 */     c.used = ((boolean[])this.used.clone());
/* 710 */     c.strategy = this.strategy;
/* 711 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 723 */     int h = 0;
/* 724 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 725 */       while (this.used[i] == 0) i++;
/* 726 */       if (this != this.key[i])
/* 727 */         t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 728 */       if (this != this.value[i])
/* 729 */         t ^= (this.value[i] == null ? 0 : System.identityHashCode(this.value[i]));
/* 730 */       h += t;
/* 731 */       i++;
/*     */     }
/* 733 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 736 */     Object[] key = this.key;
/* 737 */     Object[] value = this.value;
/* 738 */     MapIterator i = new MapIterator(null);
/* 739 */     s.defaultWriteObject();
/* 740 */     for (int j = this.size; j-- != 0; ) {
/* 741 */       int e = i.nextEntry();
/* 742 */       s.writeObject(key[e]);
/* 743 */       s.writeObject(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 748 */     s.defaultReadObject();
/* 749 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 750 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 751 */     this.mask = (this.n - 1);
/* 752 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 753 */     Object[] value = this.value = (Object[])new Object[this.n];
/* 754 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 757 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 758 */       Object k = s.readObject();
/* 759 */       Object v = s.readObject();
/* 760 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 761 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 762 */       used[pos] = true;
/* 763 */       key[pos] = k;
/* 764 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Reference2ReferenceOpenCustomHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<V>
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 569 */       super(null); } 
/* 570 */     public V next() { return Reference2ReferenceOpenCustomHashMap.this.value[nextEntry()]; }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class KeySet extends AbstractReferenceSet<K>
/*     */   {
/*     */     private KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<K> iterator()
/*     */     {
/* 541 */       return new Reference2ReferenceOpenCustomHashMap.KeyIterator(Reference2ReferenceOpenCustomHashMap.this);
/*     */     }
/*     */     public int size() {
/* 544 */       return Reference2ReferenceOpenCustomHashMap.this.size;
/*     */     }
/*     */     public boolean contains(Object k) {
/* 547 */       return Reference2ReferenceOpenCustomHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(Object k) {
/* 550 */       int oldSize = Reference2ReferenceOpenCustomHashMap.this.size;
/* 551 */       Reference2ReferenceOpenCustomHashMap.this.remove(k);
/* 552 */       return Reference2ReferenceOpenCustomHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 555 */       Reference2ReferenceOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Reference2ReferenceOpenCustomHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<K>
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 536 */       super(null); } 
/* 537 */     public K next() { return Reference2ReferenceOpenCustomHashMap.this.key[nextEntry()]; }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Reference2ReferenceMap.Entry<K, V>>
/*     */     implements Reference2ReferenceMap.FastEntrySet<K, V>
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Reference2ReferenceMap.Entry<K, V>> iterator()
/*     */     {
/* 482 */       return new Reference2ReferenceOpenCustomHashMap.EntryIterator(Reference2ReferenceOpenCustomHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Reference2ReferenceMap.Entry<K, V>> fastIterator() {
/* 485 */       return new Reference2ReferenceOpenCustomHashMap.FastEntryIterator(Reference2ReferenceOpenCustomHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 489 */       if (!(o instanceof Map.Entry)) return false;
/* 490 */       Map.Entry e = (Map.Entry)o;
/* 491 */       Object k = e.getKey();
/*     */ 
/* 493 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */ 
/* 495 */       while (Reference2ReferenceOpenCustomHashMap.this.used[pos] != 0) {
/* 496 */         if (Reference2ReferenceOpenCustomHashMap.this.strategy.equals(Reference2ReferenceOpenCustomHashMap.this.key[pos], k)) return Reference2ReferenceOpenCustomHashMap.this.value[pos] == e.getValue();
/* 497 */         pos = pos + 1 & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */       }
/* 499 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 503 */       if (!(o instanceof Map.Entry)) return false;
/* 504 */       Map.Entry e = (Map.Entry)o;
/* 505 */       Object k = e.getKey();
/*     */ 
/* 507 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */ 
/* 509 */       while (Reference2ReferenceOpenCustomHashMap.this.used[pos] != 0) {
/* 510 */         if (Reference2ReferenceOpenCustomHashMap.this.strategy.equals(Reference2ReferenceOpenCustomHashMap.this.key[pos], k)) {
/* 511 */           Reference2ReferenceOpenCustomHashMap.this.remove(e.getKey());
/* 512 */           return true;
/*     */         }
/* 514 */         pos = pos + 1 & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */       }
/* 516 */       return false;
/*     */     }
/*     */     public int size() {
/* 519 */       return Reference2ReferenceOpenCustomHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 522 */       Reference2ReferenceOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Reference2ReferenceOpenCustomHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<Reference2ReferenceMap.Entry<K, V>>
/*     */   {
/* 472 */     final AbstractReference2ReferenceMap.BasicEntry<K, V> entry = new AbstractReference2ReferenceMap.BasicEntry(null, null);
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 471 */       super(null);
/*     */     }
/*     */     public AbstractReference2ReferenceMap.BasicEntry<K, V> next() {
/* 474 */       int e = nextEntry();
/* 475 */       this.entry.key = Reference2ReferenceOpenCustomHashMap.this.key[e];
/* 476 */       this.entry.value = Reference2ReferenceOpenCustomHashMap.this.value[e];
/* 477 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Reference2ReferenceOpenCustomHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<Reference2ReferenceMap.Entry<K, V>>
/*     */   {
/*     */     private Reference2ReferenceOpenCustomHashMap<K, V>.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 460 */       super(null);
/*     */     }
/*     */     public Reference2ReferenceMap.Entry<K, V> next() {
/* 463 */       return this.entry = new Reference2ReferenceOpenCustomHashMap.MapEntry(Reference2ReferenceOpenCustomHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 467 */       super.remove();
/* 468 */       Reference2ReferenceOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MapIterator
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     ReferenceArrayList<K> wrapped;
/*     */ 
/*     */     private MapIterator()
/*     */     {
/* 367 */       this.pos = Reference2ReferenceOpenCustomHashMap.this.n;
/*     */ 
/* 370 */       this.last = -1;
/*     */ 
/* 372 */       this.c = Reference2ReferenceOpenCustomHashMap.this.size;
/*     */ 
/* 377 */       boolean[] used = Reference2ReferenceOpenCustomHashMap.this.used;
/* 378 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 381 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 384 */       if (!hasNext()) throw new NoSuchElementException();
/* 385 */       this.c -= 1;
/*     */ 
/* 387 */       if (this.pos < 0) {
/* 388 */         Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/*     */ 
/* 390 */         int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */ 
/* 392 */         while (Reference2ReferenceOpenCustomHashMap.this.used[pos] != 0) {
/* 393 */           if (Reference2ReferenceOpenCustomHashMap.this.strategy.equals(Reference2ReferenceOpenCustomHashMap.this.key[pos], k)) return pos;
/* 394 */           pos = pos + 1 & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */         }
/*     */       }
/* 397 */       this.last = this.pos;
/*     */ 
/* 399 */       if (this.c != 0) {
/* 400 */         boolean[] used = Reference2ReferenceOpenCustomHashMap.this.used;
/* 401 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 404 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 417 */         pos = (last = pos) + 1 & Reference2ReferenceOpenCustomHashMap.this.mask;
/* 418 */         while (Reference2ReferenceOpenCustomHashMap.this.used[pos] != 0) {
/* 419 */           int slot = (Reference2ReferenceOpenCustomHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2ReferenceOpenCustomHashMap.this.key[pos]))) & Reference2ReferenceOpenCustomHashMap.this.mask;
/* 420 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 421 */           pos = pos + 1 & Reference2ReferenceOpenCustomHashMap.this.mask;
/*     */         }
/* 423 */         if (Reference2ReferenceOpenCustomHashMap.this.used[pos] == 0) break;
/* 424 */         if (pos < last)
/*     */         {
/* 426 */           if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 427 */           this.wrapped.add(Reference2ReferenceOpenCustomHashMap.this.key[pos]);
/*     */         }
/* 429 */         Reference2ReferenceOpenCustomHashMap.this.key[last] = Reference2ReferenceOpenCustomHashMap.this.key[pos];
/* 430 */         Reference2ReferenceOpenCustomHashMap.this.value[last] = Reference2ReferenceOpenCustomHashMap.this.value[pos];
/*     */       }
/* 432 */       Reference2ReferenceOpenCustomHashMap.this.used[last] = false;
/* 433 */       Reference2ReferenceOpenCustomHashMap.this.key[last] = null;
/* 434 */       Reference2ReferenceOpenCustomHashMap.this.value[last] = null;
/* 435 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 439 */       if (this.last == -1) throw new IllegalStateException();
/* 440 */       if (this.pos < -1)
/*     */       {
/* 442 */         Reference2ReferenceOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 443 */         this.last = -1;
/* 444 */         return;
/*     */       }
/* 446 */       Reference2ReferenceOpenCustomHashMap.this.size -= 1;
/* 447 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 448 */         this.c += 1;
/* 449 */         nextEntry();
/*     */       }
/* 451 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 455 */       int i = n;
/* 456 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 457 */       return n - i - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class MapEntry
/*     */     implements Reference2ReferenceMap.Entry<K, V>, Map.Entry<K, V>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 337 */       this.index = index;
/*     */     }
/*     */     public K getKey() {
/* 340 */       return Reference2ReferenceOpenCustomHashMap.this.key[this.index];
/*     */     }
/*     */     public V getValue() {
/* 343 */       return Reference2ReferenceOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public V setValue(V v) {
/* 346 */       Object oldValue = Reference2ReferenceOpenCustomHashMap.this.value[this.index];
/* 347 */       Reference2ReferenceOpenCustomHashMap.this.value[this.index] = v;
/* 348 */       return oldValue;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 352 */       if (!(o instanceof Map.Entry)) return false;
/* 353 */       Map.Entry e = (Map.Entry)o;
/* 354 */       return (Reference2ReferenceOpenCustomHashMap.this.strategy.equals(Reference2ReferenceOpenCustomHashMap.this.key[this.index], e.getKey())) && (Reference2ReferenceOpenCustomHashMap.this.value[this.index] == e.getValue());
/*     */     }
/*     */     public int hashCode() {
/* 357 */       return (Reference2ReferenceOpenCustomHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2ReferenceOpenCustomHashMap.this.key[this.index])) ^ (Reference2ReferenceOpenCustomHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Reference2ReferenceOpenCustomHashMap.this.value[this.index]));
/*     */     }
/*     */     public String toString() {
/* 360 */       return Reference2ReferenceOpenCustomHashMap.this.key[this.index] + "=>" + Reference2ReferenceOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */