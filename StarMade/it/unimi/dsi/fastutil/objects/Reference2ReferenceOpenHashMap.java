/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
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
/*     */ public class Reference2ReferenceOpenHashMap<K, V> extends AbstractReference2ReferenceMap<K, V>
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
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(int expected, float f)
/*     */   {
/* 104 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 105 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 106 */     this.f = f;
/* 107 */     this.n = HashCommon.arraySize(expected, f);
/* 108 */     this.mask = (this.n - 1);
/* 109 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 110 */     this.key = ((Object[])new Object[this.n]);
/* 111 */     this.value = ((Object[])new Object[this.n]);
/* 112 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(int expected)
/*     */   {
/* 119 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap()
/*     */   {
/* 125 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(Map<? extends K, ? extends V> m, float f)
/*     */   {
/* 133 */     this(m.size(), f);
/* 134 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(Map<? extends K, ? extends V> m)
/*     */   {
/* 141 */     this(m, 0.75F);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(Reference2ReferenceMap<K, V> m, float f)
/*     */   {
/* 149 */     this(m.size(), f);
/* 150 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(Reference2ReferenceMap<K, V> m)
/*     */   {
/* 157 */     this(m, 0.75F);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(K[] k, V[] v, float f)
/*     */   {
/* 167 */     this(k.length, f);
/* 168 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 169 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap(K[] k, V[] v)
/*     */   {
/* 178 */     this(k, v, 0.75F);
/*     */   }
/*     */ 
/*     */   public V put(K k, V v)
/*     */   {
/* 186 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 188 */     while (this.used[pos] != 0) {
/* 189 */       if (this.key[pos] == k) {
/* 190 */         Object oldValue = this.value[pos];
/* 191 */         this.value[pos] = v;
/* 192 */         return oldValue;
/*     */       }
/* 194 */       pos = pos + 1 & this.mask;
/*     */     }
/* 196 */     this.used[pos] = true;
/* 197 */     this.key[pos] = k;
/* 198 */     this.value[pos] = v;
/* 199 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 201 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 213 */       pos = (last = pos) + 1 & this.mask;
/* 214 */       while (this.used[pos] != 0) {
/* 215 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 216 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 217 */         pos = pos + 1 & this.mask;
/*     */       }
/* 219 */       if (this.used[pos] == 0) break;
/* 220 */       this.key[last] = this.key[pos];
/* 221 */       this.value[last] = this.value[pos];
/*     */     }
/* 223 */     this.used[last] = false;
/* 224 */     this.key[last] = null;
/* 225 */     this.value[last] = null;
/* 226 */     return last;
/*     */   }
/*     */ 
/*     */   public V remove(Object k)
/*     */   {
/* 231 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 233 */     while (this.used[pos] != 0) {
/* 234 */       if (this.key[pos] == k) {
/* 235 */         this.size -= 1;
/* 236 */         Object v = this.value[pos];
/* 237 */         shiftKeys(pos);
/* 238 */         return v;
/*     */       }
/* 240 */       pos = pos + 1 & this.mask;
/*     */     }
/* 242 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public V get(Object k)
/*     */   {
/* 247 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 249 */     while (this.used[pos] != 0) {
/* 250 */       if (this.key[pos] == k) return this.value[pos];
/* 251 */       pos = pos + 1 & this.mask;
/*     */     }
/* 253 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k)
/*     */   {
/* 258 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 260 */     while (this.used[pos] != 0) {
/* 261 */       if (this.key[pos] == k) return true;
/* 262 */       pos = pos + 1 & this.mask;
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */   public boolean containsValue(Object v) {
/* 267 */     Object[] value = this.value;
/* 268 */     boolean[] used = this.used;
/* 269 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 279 */     if (this.size == 0) return;
/* 280 */     this.size = 0;
/* 281 */     BooleanArrays.fill(this.used, false);
/*     */ 
/* 283 */     ObjectArrays.fill(this.key, null);
/* 284 */     ObjectArrays.fill(this.value, null);
/*     */   }
/*     */   public int size() {
/* 287 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 290 */     return this.size == 0;
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
/* 307 */     return 16;
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceMap.FastEntrySet<K, V> reference2ReferenceEntrySet()
/*     */   {
/* 506 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 507 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public ReferenceSet<K> keySet()
/*     */   {
/* 539 */     if (this.keys == null) this.keys = new KeySet(null);
/* 540 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 553 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*     */         public ObjectIterator<V> iterator() {
/* 555 */           return new Reference2ReferenceOpenHashMap.ValueIterator(Reference2ReferenceOpenHashMap.this);
/*     */         }
/*     */         public int size() {
/* 558 */           return Reference2ReferenceOpenHashMap.this.size;
/*     */         }
/*     */         public boolean contains(Object v) {
/* 561 */           return Reference2ReferenceOpenHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 564 */           Reference2ReferenceOpenHashMap.this.clear();
/*     */         }
/*     */       };
/* 567 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 581 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 596 */     int l = HashCommon.arraySize(this.size, this.f);
/* 597 */     if (l >= this.n) return true; try
/*     */     {
/* 599 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 601 */       return false;
/* 602 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 623 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 624 */     if (this.n <= l) return true; try
/*     */     {
/* 626 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 628 */       return false;
/* 629 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 642 */     int i = 0;
/* 643 */     boolean[] used = this.used;
/*     */ 
/* 645 */     Object[] key = this.key;
/* 646 */     Object[] value = this.value;
/* 647 */     int newMask = newN - 1;
/* 648 */     Object[] newKey = (Object[])new Object[newN];
/* 649 */     Object[] newValue = (Object[])new Object[newN];
/* 650 */     boolean[] newUsed = new boolean[newN];
/* 651 */     for (int j = this.size; j-- != 0; ) {
/* 652 */       while (used[i] == 0) i++;
/* 653 */       Object k = key[i];
/* 654 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 655 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 656 */       newUsed[pos] = true;
/* 657 */       newKey[pos] = k;
/* 658 */       newValue[pos] = value[i];
/* 659 */       i++;
/*     */     }
/* 661 */     this.n = newN;
/* 662 */     this.mask = newMask;
/* 663 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 664 */     this.key = newKey;
/* 665 */     this.value = newValue;
/* 666 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Reference2ReferenceOpenHashMap<K, V> clone()
/*     */   {
/*     */     Reference2ReferenceOpenHashMap c;
/*     */     try
/*     */     {
/* 679 */       c = (Reference2ReferenceOpenHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 682 */       throw new InternalError();
/*     */     }
/* 684 */     c.keys = null;
/* 685 */     c.values = null;
/* 686 */     c.entries = null;
/* 687 */     c.key = ((Object[])this.key.clone());
/* 688 */     c.value = ((Object[])this.value.clone());
/* 689 */     c.used = ((boolean[])this.used.clone());
/* 690 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 702 */     int h = 0;
/* 703 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 704 */       while (this.used[i] == 0) i++;
/* 705 */       if (this != this.key[i])
/* 706 */         t = this.key[i] == null ? 0 : System.identityHashCode(this.key[i]);
/* 707 */       if (this != this.value[i])
/* 708 */         t ^= (this.value[i] == null ? 0 : System.identityHashCode(this.value[i]));
/* 709 */       h += t;
/* 710 */       i++;
/*     */     }
/* 712 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 715 */     Object[] key = this.key;
/* 716 */     Object[] value = this.value;
/* 717 */     MapIterator i = new MapIterator(null);
/* 718 */     s.defaultWriteObject();
/* 719 */     for (int j = this.size; j-- != 0; ) {
/* 720 */       int e = i.nextEntry();
/* 721 */       s.writeObject(key[e]);
/* 722 */       s.writeObject(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 727 */     s.defaultReadObject();
/* 728 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 729 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 730 */     this.mask = (this.n - 1);
/* 731 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 732 */     Object[] value = this.value = (Object[])new Object[this.n];
/* 733 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 736 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 737 */       Object k = s.readObject();
/* 738 */       Object v = s.readObject();
/* 739 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 740 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 741 */       used[pos] = true;
/* 742 */       key[pos] = k;
/* 743 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Reference2ReferenceOpenHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<V>
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 549 */       super(null); } 
/* 550 */     public V next() { return Reference2ReferenceOpenHashMap.this.value[nextEntry()]; }
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
/* 521 */       return new Reference2ReferenceOpenHashMap.KeyIterator(Reference2ReferenceOpenHashMap.this);
/*     */     }
/*     */     public int size() {
/* 524 */       return Reference2ReferenceOpenHashMap.this.size;
/*     */     }
/*     */     public boolean contains(Object k) {
/* 527 */       return Reference2ReferenceOpenHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(Object k) {
/* 530 */       int oldSize = Reference2ReferenceOpenHashMap.this.size;
/* 531 */       Reference2ReferenceOpenHashMap.this.remove(k);
/* 532 */       return Reference2ReferenceOpenHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 535 */       Reference2ReferenceOpenHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Reference2ReferenceOpenHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<K>
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 516 */       super(null); } 
/* 517 */     public K next() { return Reference2ReferenceOpenHashMap.this.key[nextEntry()]; }
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
/* 462 */       return new Reference2ReferenceOpenHashMap.EntryIterator(Reference2ReferenceOpenHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Reference2ReferenceMap.Entry<K, V>> fastIterator() {
/* 465 */       return new Reference2ReferenceOpenHashMap.FastEntryIterator(Reference2ReferenceOpenHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 469 */       if (!(o instanceof Map.Entry)) return false;
/* 470 */       Map.Entry e = (Map.Entry)o;
/* 471 */       Object k = e.getKey();
/*     */ 
/* 473 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ReferenceOpenHashMap.this.mask;
/*     */ 
/* 475 */       while (Reference2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 476 */         if (Reference2ReferenceOpenHashMap.this.key[pos] == k) return Reference2ReferenceOpenHashMap.this.value[pos] == e.getValue();
/* 477 */         pos = pos + 1 & Reference2ReferenceOpenHashMap.this.mask;
/*     */       }
/* 479 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 483 */       if (!(o instanceof Map.Entry)) return false;
/* 484 */       Map.Entry e = (Map.Entry)o;
/* 485 */       Object k = e.getKey();
/*     */ 
/* 487 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ReferenceOpenHashMap.this.mask;
/*     */ 
/* 489 */       while (Reference2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 490 */         if (Reference2ReferenceOpenHashMap.this.key[pos] == k) {
/* 491 */           Reference2ReferenceOpenHashMap.this.remove(e.getKey());
/* 492 */           return true;
/*     */         }
/* 494 */         pos = pos + 1 & Reference2ReferenceOpenHashMap.this.mask;
/*     */       }
/* 496 */       return false;
/*     */     }
/*     */     public int size() {
/* 499 */       return Reference2ReferenceOpenHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 502 */       Reference2ReferenceOpenHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Reference2ReferenceOpenHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<Reference2ReferenceMap.Entry<K, V>>
/*     */   {
/* 452 */     final AbstractReference2ReferenceMap.BasicEntry<K, V> entry = new AbstractReference2ReferenceMap.BasicEntry(null, null);
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 451 */       super(null);
/*     */     }
/*     */     public AbstractReference2ReferenceMap.BasicEntry<K, V> next() {
/* 454 */       int e = nextEntry();
/* 455 */       this.entry.key = Reference2ReferenceOpenHashMap.this.key[e];
/* 456 */       this.entry.value = Reference2ReferenceOpenHashMap.this.value[e];
/* 457 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Reference2ReferenceOpenHashMap<K, V>.MapIterator
/*     */     implements ObjectIterator<Reference2ReferenceMap.Entry<K, V>>
/*     */   {
/*     */     private Reference2ReferenceOpenHashMap<K, V>.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 440 */       super(null);
/*     */     }
/*     */     public Reference2ReferenceMap.Entry<K, V> next() {
/* 443 */       return this.entry = new Reference2ReferenceOpenHashMap.MapEntry(Reference2ReferenceOpenHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 447 */       super.remove();
/* 448 */       Reference2ReferenceOpenHashMap.MapEntry.access$102(this.entry, -1);
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
/* 347 */       this.pos = Reference2ReferenceOpenHashMap.this.n;
/*     */ 
/* 350 */       this.last = -1;
/*     */ 
/* 352 */       this.c = Reference2ReferenceOpenHashMap.this.size;
/*     */ 
/* 357 */       boolean[] used = Reference2ReferenceOpenHashMap.this.used;
/* 358 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 361 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 364 */       if (!hasNext()) throw new NoSuchElementException();
/* 365 */       this.c -= 1;
/*     */ 
/* 367 */       if (this.pos < 0) {
/* 368 */         Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/*     */ 
/* 370 */         int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & Reference2ReferenceOpenHashMap.this.mask;
/*     */ 
/* 372 */         while (Reference2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 373 */           if (Reference2ReferenceOpenHashMap.this.key[pos] == k) return pos;
/* 374 */           pos = pos + 1 & Reference2ReferenceOpenHashMap.this.mask;
/*     */         }
/*     */       }
/* 377 */       this.last = this.pos;
/*     */ 
/* 379 */       if (this.c != 0) {
/* 380 */         boolean[] used = Reference2ReferenceOpenHashMap.this.used;
/* 381 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 384 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 397 */         pos = (last = pos) + 1 & Reference2ReferenceOpenHashMap.this.mask;
/* 398 */         while (Reference2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 399 */           int slot = (Reference2ReferenceOpenHashMap.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(Reference2ReferenceOpenHashMap.this.key[pos]))) & Reference2ReferenceOpenHashMap.this.mask;
/* 400 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 401 */           pos = pos + 1 & Reference2ReferenceOpenHashMap.this.mask;
/*     */         }
/* 403 */         if (Reference2ReferenceOpenHashMap.this.used[pos] == 0) break;
/* 404 */         if (pos < last)
/*     */         {
/* 406 */           if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 407 */           this.wrapped.add(Reference2ReferenceOpenHashMap.this.key[pos]);
/*     */         }
/* 409 */         Reference2ReferenceOpenHashMap.this.key[last] = Reference2ReferenceOpenHashMap.this.key[pos];
/* 410 */         Reference2ReferenceOpenHashMap.this.value[last] = Reference2ReferenceOpenHashMap.this.value[pos];
/*     */       }
/* 412 */       Reference2ReferenceOpenHashMap.this.used[last] = false;
/* 413 */       Reference2ReferenceOpenHashMap.this.key[last] = null;
/* 414 */       Reference2ReferenceOpenHashMap.this.value[last] = null;
/* 415 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 419 */       if (this.last == -1) throw new IllegalStateException();
/* 420 */       if (this.pos < -1)
/*     */       {
/* 422 */         Reference2ReferenceOpenHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 423 */         this.last = -1;
/* 424 */         return;
/*     */       }
/* 426 */       Reference2ReferenceOpenHashMap.this.size -= 1;
/* 427 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 428 */         this.c += 1;
/* 429 */         nextEntry();
/*     */       }
/* 431 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 435 */       int i = n;
/* 436 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 437 */       return n - i - 1;
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
/* 317 */       this.index = index;
/*     */     }
/*     */     public K getKey() {
/* 320 */       return Reference2ReferenceOpenHashMap.this.key[this.index];
/*     */     }
/*     */     public V getValue() {
/* 323 */       return Reference2ReferenceOpenHashMap.this.value[this.index];
/*     */     }
/*     */     public V setValue(V v) {
/* 326 */       Object oldValue = Reference2ReferenceOpenHashMap.this.value[this.index];
/* 327 */       Reference2ReferenceOpenHashMap.this.value[this.index] = v;
/* 328 */       return oldValue;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 332 */       if (!(o instanceof Map.Entry)) return false;
/* 333 */       Map.Entry e = (Map.Entry)o;
/* 334 */       return (Reference2ReferenceOpenHashMap.this.key[this.index] == e.getKey()) && (Reference2ReferenceOpenHashMap.this.value[this.index] == e.getValue());
/*     */     }
/*     */     public int hashCode() {
/* 337 */       return (Reference2ReferenceOpenHashMap.this.key[this.index] == null ? 0 : System.identityHashCode(Reference2ReferenceOpenHashMap.this.key[this.index])) ^ (Reference2ReferenceOpenHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Reference2ReferenceOpenHashMap.this.value[this.index]));
/*     */     }
/*     */     public String toString() {
/* 340 */       return Reference2ReferenceOpenHashMap.this.key[this.index] + "=>" + Reference2ReferenceOpenHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap
 * JD-Core Version:    0.6.2
 */