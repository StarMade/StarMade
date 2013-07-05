/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractReferenceCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ReferenceCollection;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Byte2ReferenceOpenHashMap<V> extends AbstractByte2ReferenceMap<V>
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient byte[] key;
/*     */   protected transient V[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Byte2ReferenceMap.FastEntrySet<V> entries;
/*     */   protected volatile transient ByteSet keys;
/*     */   protected volatile transient ReferenceCollection<V> values;
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(int expected, float f)
/*     */   {
/* 106 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 107 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 108 */     this.f = f;
/* 109 */     this.n = HashCommon.arraySize(expected, f);
/* 110 */     this.mask = (this.n - 1);
/* 111 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 112 */     this.key = new byte[this.n];
/* 113 */     this.value = ((Object[])new Object[this.n]);
/* 114 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(int expected)
/*     */   {
/* 121 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap()
/*     */   {
/* 127 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(Map<? extends Byte, ? extends V> m, float f)
/*     */   {
/* 135 */     this(m.size(), f);
/* 136 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(Map<? extends Byte, ? extends V> m)
/*     */   {
/* 143 */     this(m, 0.75F);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(Byte2ReferenceMap<V> m, float f)
/*     */   {
/* 151 */     this(m.size(), f);
/* 152 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(Byte2ReferenceMap<V> m)
/*     */   {
/* 159 */     this(m, 0.75F);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(byte[] k, V[] v, float f)
/*     */   {
/* 169 */     this(k.length, f);
/* 170 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 171 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap(byte[] k, V[] v)
/*     */   {
/* 180 */     this(k, v, 0.75F);
/*     */   }
/*     */ 
/*     */   public V put(byte k, V v)
/*     */   {
/* 188 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 190 */     while (this.used[pos] != 0) {
/* 191 */       if (this.key[pos] == k) {
/* 192 */         Object oldValue = this.value[pos];
/* 193 */         this.value[pos] = v;
/* 194 */         return oldValue;
/*     */       }
/* 196 */       pos = pos + 1 & this.mask;
/*     */     }
/* 198 */     this.used[pos] = true;
/* 199 */     this.key[pos] = k;
/* 200 */     this.value[pos] = v;
/* 201 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 203 */     return this.defRetValue;
/*     */   }
/*     */   public V put(Byte ok, V ov) {
/* 206 */     Object v = ov;
/* 207 */     byte k = ok.byteValue();
/*     */ 
/* 209 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 211 */     while (this.used[pos] != 0) {
/* 212 */       if (this.key[pos] == k) {
/* 213 */         Object oldValue = this.value[pos];
/* 214 */         this.value[pos] = v;
/* 215 */         return oldValue;
/*     */       }
/* 217 */       pos = pos + 1 & this.mask;
/*     */     }
/* 219 */     this.used[pos] = true;
/* 220 */     this.key[pos] = k;
/* 221 */     this.value[pos] = v;
/* 222 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 224 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 236 */       pos = (last = pos) + 1 & this.mask;
/* 237 */       while (this.used[pos] != 0) {
/* 238 */         int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 239 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 240 */         pos = pos + 1 & this.mask;
/*     */       }
/* 242 */       if (this.used[pos] == 0) break;
/* 243 */       this.key[last] = this.key[pos];
/* 244 */       this.value[last] = this.value[pos];
/*     */     }
/* 246 */     this.used[last] = false;
/* 247 */     this.value[last] = null;
/* 248 */     return last;
/*     */   }
/*     */ 
/*     */   public V remove(byte k)
/*     */   {
/* 253 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 255 */     while (this.used[pos] != 0) {
/* 256 */       if (this.key[pos] == k) {
/* 257 */         this.size -= 1;
/* 258 */         Object v = this.value[pos];
/* 259 */         shiftKeys(pos);
/* 260 */         return v;
/*     */       }
/* 262 */       pos = pos + 1 & this.mask;
/*     */     }
/* 264 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public V remove(Object ok) {
/* 268 */     byte k = ((Byte)ok).byteValue();
/*     */ 
/* 270 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 272 */     while (this.used[pos] != 0) {
/* 273 */       if (this.key[pos] == k) {
/* 274 */         this.size -= 1;
/* 275 */         Object v = this.value[pos];
/* 276 */         shiftKeys(pos);
/* 277 */         return v;
/*     */       }
/* 279 */       pos = pos + 1 & this.mask;
/*     */     }
/* 281 */     return this.defRetValue;
/*     */   }
/*     */   public V get(Byte ok) {
/* 284 */     byte k = ok.byteValue();
/*     */ 
/* 286 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 288 */     while (this.used[pos] != 0) {
/* 289 */       if (this.key[pos] == k) return this.value[pos];
/* 290 */       pos = pos + 1 & this.mask;
/*     */     }
/* 292 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public V get(byte k)
/*     */   {
/* 297 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 299 */     while (this.used[pos] != 0) {
/* 300 */       if (this.key[pos] == k) return this.value[pos];
/* 301 */       pos = pos + 1 & this.mask;
/*     */     }
/* 303 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(byte k)
/*     */   {
/* 308 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 310 */     while (this.used[pos] != 0) {
/* 311 */       if (this.key[pos] == k) return true;
/* 312 */       pos = pos + 1 & this.mask;
/*     */     }
/* 314 */     return false;
/*     */   }
/*     */   public boolean containsValue(Object v) {
/* 317 */     Object[] value = this.value;
/* 318 */     boolean[] used = this.used;
/* 319 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 329 */     if (this.size == 0) return;
/* 330 */     this.size = 0;
/* 331 */     BooleanArrays.fill(this.used, false);
/*     */ 
/* 333 */     ObjectArrays.fill(this.value, null);
/*     */   }
/*     */   public int size() {
/* 336 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 339 */     return this.size == 0;
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
/* 356 */     return 16;
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceMap.FastEntrySet<V> byte2ReferenceEntrySet()
/*     */   {
/* 557 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 558 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public ByteSet keySet()
/*     */   {
/* 591 */     if (this.keys == null) this.keys = new KeySet(null);
/* 592 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public ReferenceCollection<V> values()
/*     */   {
/* 605 */     if (this.values == null) this.values = new AbstractReferenceCollection() {
/*     */         public ObjectIterator<V> iterator() {
/* 607 */           return new Byte2ReferenceOpenHashMap.ValueIterator(Byte2ReferenceOpenHashMap.this);
/*     */         }
/*     */         public int size() {
/* 610 */           return Byte2ReferenceOpenHashMap.this.size;
/*     */         }
/*     */         public boolean contains(Object v) {
/* 613 */           return Byte2ReferenceOpenHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 616 */           Byte2ReferenceOpenHashMap.this.clear();
/*     */         }
/*     */       };
/* 619 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 633 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 648 */     int l = HashCommon.arraySize(this.size, this.f);
/* 649 */     if (l >= this.n) return true; try
/*     */     {
/* 651 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 653 */       return false;
/* 654 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 675 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 676 */     if (this.n <= l) return true; try
/*     */     {
/* 678 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 680 */       return false;
/* 681 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 694 */     int i = 0;
/* 695 */     boolean[] used = this.used;
/*     */ 
/* 697 */     byte[] key = this.key;
/* 698 */     Object[] value = this.value;
/* 699 */     int newMask = newN - 1;
/* 700 */     byte[] newKey = new byte[newN];
/* 701 */     Object[] newValue = (Object[])new Object[newN];
/* 702 */     boolean[] newUsed = new boolean[newN];
/* 703 */     for (int j = this.size; j-- != 0; ) {
/* 704 */       while (used[i] == 0) i++;
/* 705 */       byte k = key[i];
/* 706 */       int pos = HashCommon.murmurHash3(k) & newMask;
/* 707 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 708 */       newUsed[pos] = true;
/* 709 */       newKey[pos] = k;
/* 710 */       newValue[pos] = value[i];
/* 711 */       i++;
/*     */     }
/* 713 */     this.n = newN;
/* 714 */     this.mask = newMask;
/* 715 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 716 */     this.key = newKey;
/* 717 */     this.value = newValue;
/* 718 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Byte2ReferenceOpenHashMap<V> clone()
/*     */   {
/*     */     Byte2ReferenceOpenHashMap c;
/*     */     try
/*     */     {
/* 731 */       c = (Byte2ReferenceOpenHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 734 */       throw new InternalError();
/*     */     }
/* 736 */     c.keys = null;
/* 737 */     c.values = null;
/* 738 */     c.entries = null;
/* 739 */     c.key = ((byte[])this.key.clone());
/* 740 */     c.value = ((Object[])this.value.clone());
/* 741 */     c.used = ((boolean[])this.used.clone());
/* 742 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 754 */     int h = 0;
/* 755 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 756 */       while (this.used[i] == 0) i++;
/* 757 */       t = this.key[i];
/* 758 */       if (this != this.value[i])
/* 759 */         t ^= (this.value[i] == null ? 0 : System.identityHashCode(this.value[i]));
/* 760 */       h += t;
/* 761 */       i++;
/*     */     }
/* 763 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 766 */     byte[] key = this.key;
/* 767 */     Object[] value = this.value;
/* 768 */     MapIterator i = new MapIterator(null);
/* 769 */     s.defaultWriteObject();
/* 770 */     for (int j = this.size; j-- != 0; ) {
/* 771 */       int e = i.nextEntry();
/* 772 */       s.writeByte(key[e]);
/* 773 */       s.writeObject(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 778 */     s.defaultReadObject();
/* 779 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 780 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 781 */     this.mask = (this.n - 1);
/* 782 */     byte[] key = this.key = new byte[this.n];
/* 783 */     Object[] value = this.value = (Object[])new Object[this.n];
/* 784 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 787 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 788 */       byte k = s.readByte();
/* 789 */       Object v = s.readObject();
/* 790 */       pos = HashCommon.murmurHash3(k) & this.mask;
/* 791 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 792 */       used[pos] = true;
/* 793 */       key[pos] = k;
/* 794 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Byte2ReferenceOpenHashMap<V>.MapIterator
/*     */     implements ObjectIterator<V>
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 601 */       super(null); } 
/* 602 */     public V next() { return Byte2ReferenceOpenHashMap.this.value[nextEntry()]; }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class KeySet extends AbstractByteSet
/*     */   {
/*     */     private KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ByteIterator iterator()
/*     */     {
/* 573 */       return new Byte2ReferenceOpenHashMap.KeyIterator(Byte2ReferenceOpenHashMap.this);
/*     */     }
/*     */     public int size() {
/* 576 */       return Byte2ReferenceOpenHashMap.this.size;
/*     */     }
/*     */     public boolean contains(byte k) {
/* 579 */       return Byte2ReferenceOpenHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(byte k) {
/* 582 */       int oldSize = Byte2ReferenceOpenHashMap.this.size;
/* 583 */       Byte2ReferenceOpenHashMap.this.remove(k);
/* 584 */       return Byte2ReferenceOpenHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 587 */       Byte2ReferenceOpenHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Byte2ReferenceOpenHashMap.MapIterator
/*     */     implements ByteIterator
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 567 */       super(null); } 
/* 568 */     public byte nextByte() { return Byte2ReferenceOpenHashMap.this.key[nextEntry()]; } 
/* 569 */     public Byte next() { return Byte.valueOf(Byte2ReferenceOpenHashMap.this.key[nextEntry()]); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Byte2ReferenceMap.Entry<V>>
/*     */     implements Byte2ReferenceMap.FastEntrySet<V>
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Byte2ReferenceMap.Entry<V>> iterator()
/*     */     {
/* 513 */       return new Byte2ReferenceOpenHashMap.EntryIterator(Byte2ReferenceOpenHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Byte2ReferenceMap.Entry<V>> fastIterator() {
/* 516 */       return new Byte2ReferenceOpenHashMap.FastEntryIterator(Byte2ReferenceOpenHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 520 */       if (!(o instanceof Map.Entry)) return false;
/* 521 */       Map.Entry e = (Map.Entry)o;
/* 522 */       byte k = ((Byte)e.getKey()).byteValue();
/*     */ 
/* 524 */       int pos = HashCommon.murmurHash3(k) & Byte2ReferenceOpenHashMap.this.mask;
/*     */ 
/* 526 */       while (Byte2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 527 */         if (Byte2ReferenceOpenHashMap.this.key[pos] == k) return Byte2ReferenceOpenHashMap.this.value[pos] == e.getValue();
/* 528 */         pos = pos + 1 & Byte2ReferenceOpenHashMap.this.mask;
/*     */       }
/* 530 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 534 */       if (!(o instanceof Map.Entry)) return false;
/* 535 */       Map.Entry e = (Map.Entry)o;
/* 536 */       byte k = ((Byte)e.getKey()).byteValue();
/*     */ 
/* 538 */       int pos = HashCommon.murmurHash3(k) & Byte2ReferenceOpenHashMap.this.mask;
/*     */ 
/* 540 */       while (Byte2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 541 */         if (Byte2ReferenceOpenHashMap.this.key[pos] == k) {
/* 542 */           Byte2ReferenceOpenHashMap.this.remove(e.getKey());
/* 543 */           return true;
/*     */         }
/* 545 */         pos = pos + 1 & Byte2ReferenceOpenHashMap.this.mask;
/*     */       }
/* 547 */       return false;
/*     */     }
/*     */     public int size() {
/* 550 */       return Byte2ReferenceOpenHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 553 */       Byte2ReferenceOpenHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Byte2ReferenceOpenHashMap<V>.MapIterator
/*     */     implements ObjectIterator<Byte2ReferenceMap.Entry<V>>
/*     */   {
/* 503 */     final AbstractByte2ReferenceMap.BasicEntry<V> entry = new AbstractByte2ReferenceMap.BasicEntry((byte)0, null);
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 502 */       super(null);
/*     */     }
/*     */     public AbstractByte2ReferenceMap.BasicEntry<V> next() {
/* 505 */       int e = nextEntry();
/* 506 */       this.entry.key = Byte2ReferenceOpenHashMap.this.key[e];
/* 507 */       this.entry.value = Byte2ReferenceOpenHashMap.this.value[e];
/* 508 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Byte2ReferenceOpenHashMap<V>.MapIterator
/*     */     implements ObjectIterator<Byte2ReferenceMap.Entry<V>>
/*     */   {
/*     */     private Byte2ReferenceOpenHashMap<V>.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 491 */       super(null);
/*     */     }
/*     */     public Byte2ReferenceMap.Entry<V> next() {
/* 494 */       return this.entry = new Byte2ReferenceOpenHashMap.MapEntry(Byte2ReferenceOpenHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 498 */       super.remove();
/* 499 */       Byte2ReferenceOpenHashMap.MapEntry.access$102(this.entry, -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MapIterator
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     ByteArrayList wrapped;
/*     */ 
/*     */     private MapIterator()
/*     */     {
/* 399 */       this.pos = Byte2ReferenceOpenHashMap.this.n;
/*     */ 
/* 402 */       this.last = -1;
/*     */ 
/* 404 */       this.c = Byte2ReferenceOpenHashMap.this.size;
/*     */ 
/* 409 */       boolean[] used = Byte2ReferenceOpenHashMap.this.used;
/* 410 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 413 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 416 */       if (!hasNext()) throw new NoSuchElementException();
/* 417 */       this.c -= 1;
/*     */ 
/* 419 */       if (this.pos < 0) {
/* 420 */         byte k = this.wrapped.getByte(-(this.last = --this.pos) - 2);
/*     */ 
/* 422 */         int pos = HashCommon.murmurHash3(k) & Byte2ReferenceOpenHashMap.this.mask;
/*     */ 
/* 424 */         while (Byte2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 425 */           if (Byte2ReferenceOpenHashMap.this.key[pos] == k) return pos;
/* 426 */           pos = pos + 1 & Byte2ReferenceOpenHashMap.this.mask;
/*     */         }
/*     */       }
/* 429 */       this.last = this.pos;
/*     */ 
/* 431 */       if (this.c != 0) {
/* 432 */         boolean[] used = Byte2ReferenceOpenHashMap.this.used;
/* 433 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 436 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 449 */         pos = (last = pos) + 1 & Byte2ReferenceOpenHashMap.this.mask;
/* 450 */         while (Byte2ReferenceOpenHashMap.this.used[pos] != 0) {
/* 451 */           int slot = HashCommon.murmurHash3(Byte2ReferenceOpenHashMap.this.key[pos]) & Byte2ReferenceOpenHashMap.this.mask;
/* 452 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 453 */           pos = pos + 1 & Byte2ReferenceOpenHashMap.this.mask;
/*     */         }
/* 455 */         if (Byte2ReferenceOpenHashMap.this.used[pos] == 0) break;
/* 456 */         if (pos < last)
/*     */         {
/* 458 */           if (this.wrapped == null) this.wrapped = new ByteArrayList();
/* 459 */           this.wrapped.add(Byte2ReferenceOpenHashMap.this.key[pos]);
/*     */         }
/* 461 */         Byte2ReferenceOpenHashMap.this.key[last] = Byte2ReferenceOpenHashMap.this.key[pos];
/* 462 */         Byte2ReferenceOpenHashMap.this.value[last] = Byte2ReferenceOpenHashMap.this.value[pos];
/*     */       }
/* 464 */       Byte2ReferenceOpenHashMap.this.used[last] = false;
/* 465 */       Byte2ReferenceOpenHashMap.this.value[last] = null;
/* 466 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 470 */       if (this.last == -1) throw new IllegalStateException();
/* 471 */       if (this.pos < -1)
/*     */       {
/* 473 */         Byte2ReferenceOpenHashMap.this.remove(this.wrapped.getByte(-this.pos - 2));
/* 474 */         this.last = -1;
/* 475 */         return;
/*     */       }
/* 477 */       Byte2ReferenceOpenHashMap.this.size -= 1;
/* 478 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 479 */         this.c += 1;
/* 480 */         nextEntry();
/*     */       }
/* 482 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 486 */       int i = n;
/* 487 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 488 */       return n - i - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class MapEntry
/*     */     implements Byte2ReferenceMap.Entry<V>, Map.Entry<Byte, V>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 366 */       this.index = index;
/*     */     }
/*     */     public Byte getKey() {
/* 369 */       return Byte.valueOf(Byte2ReferenceOpenHashMap.this.key[this.index]);
/*     */     }
/*     */     public byte getByteKey() {
/* 372 */       return Byte2ReferenceOpenHashMap.this.key[this.index];
/*     */     }
/*     */     public V getValue() {
/* 375 */       return Byte2ReferenceOpenHashMap.this.value[this.index];
/*     */     }
/*     */     public V setValue(V v) {
/* 378 */       Object oldValue = Byte2ReferenceOpenHashMap.this.value[this.index];
/* 379 */       Byte2ReferenceOpenHashMap.this.value[this.index] = v;
/* 380 */       return oldValue;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 384 */       if (!(o instanceof Map.Entry)) return false;
/* 385 */       Map.Entry e = (Map.Entry)o;
/* 386 */       return (Byte2ReferenceOpenHashMap.this.key[this.index] == ((Byte)e.getKey()).byteValue()) && (Byte2ReferenceOpenHashMap.this.value[this.index] == e.getValue());
/*     */     }
/*     */     public int hashCode() {
/* 389 */       return Byte2ReferenceOpenHashMap.this.key[this.index] ^ (Byte2ReferenceOpenHashMap.this.value[this.index] == null ? 0 : System.identityHashCode(Byte2ReferenceOpenHashMap.this.value[this.index]));
/*     */     }
/*     */     public String toString() {
/* 392 */       return Byte2ReferenceOpenHashMap.this.key[this.index] + "=>" + Byte2ReferenceOpenHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2ReferenceOpenHashMap
 * JD-Core Version:    0.6.2
 */