/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Double2CharOpenCustomHashMap extends AbstractDouble2CharMap
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient double[] key;
/*     */   protected transient char[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Double2CharMap.FastEntrySet entries;
/*     */   protected volatile transient DoubleSet keys;
/*     */   protected volatile transient CharCollection values;
/*     */   protected DoubleHash.Strategy strategy;
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(int expected, float f, DoubleHash.Strategy strategy)
/*     */   {
/* 111 */     this.strategy = strategy;
/* 112 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 113 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 114 */     this.f = f;
/* 115 */     this.n = HashCommon.arraySize(expected, f);
/* 116 */     this.mask = (this.n - 1);
/* 117 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 118 */     this.key = new double[this.n];
/* 119 */     this.value = new char[this.n];
/* 120 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(int expected, DoubleHash.Strategy strategy)
/*     */   {
/* 128 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(DoubleHash.Strategy strategy)
/*     */   {
/* 135 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(Map<? extends Double, ? extends Character> m, float f, DoubleHash.Strategy strategy)
/*     */   {
/* 144 */     this(m.size(), f, strategy);
/* 145 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(Map<? extends Double, ? extends Character> m, DoubleHash.Strategy strategy)
/*     */   {
/* 153 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(Double2CharMap m, float f, DoubleHash.Strategy strategy)
/*     */   {
/* 162 */     this(m.size(), f, strategy);
/* 163 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(Double2CharMap m, DoubleHash.Strategy strategy)
/*     */   {
/* 171 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(double[] k, char[] v, float f, DoubleHash.Strategy strategy)
/*     */   {
/* 182 */     this(k.length, f, strategy);
/* 183 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 184 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap(double[] k, char[] v, DoubleHash.Strategy strategy)
/*     */   {
/* 194 */     this(k, v, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public DoubleHash.Strategy strategy()
/*     */   {
/* 201 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public char put(double k, char v)
/*     */   {
/* 209 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 211 */     while (this.used[pos] != 0) {
/* 212 */       if (this.strategy.equals(this.key[pos], k)) {
/* 213 */         char oldValue = this.value[pos];
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
/*     */   public Character put(Double ok, Character ov) {
/* 227 */     char v = ov.charValue();
/* 228 */     double k = ok.doubleValue();
/*     */ 
/* 230 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 232 */     while (this.used[pos] != 0) {
/* 233 */       if (this.strategy.equals(this.key[pos], k)) {
/* 234 */         Character oldValue = Character.valueOf(this.value[pos]);
/* 235 */         this.value[pos] = v;
/* 236 */         return oldValue;
/*     */       }
/* 238 */       pos = pos + 1 & this.mask;
/*     */     }
/* 240 */     this.used[pos] = true;
/* 241 */     this.key[pos] = k;
/* 242 */     this.value[pos] = v;
/* 243 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 257 */       pos = (last = pos) + 1 & this.mask;
/* 258 */       while (this.used[pos] != 0) {
/* 259 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 260 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 261 */         pos = pos + 1 & this.mask;
/*     */       }
/* 263 */       if (this.used[pos] == 0) break;
/* 264 */       this.key[last] = this.key[pos];
/* 265 */       this.value[last] = this.value[pos];
/*     */     }
/* 267 */     this.used[last] = false;
/* 268 */     return last;
/*     */   }
/*     */ 
/*     */   public char remove(double k)
/*     */   {
/* 273 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 275 */     while (this.used[pos] != 0) {
/* 276 */       if (this.strategy.equals(this.key[pos], k)) {
/* 277 */         this.size -= 1;
/* 278 */         char v = this.value[pos];
/* 279 */         shiftKeys(pos);
/* 280 */         return v;
/*     */       }
/* 282 */       pos = pos + 1 & this.mask;
/*     */     }
/* 284 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public Character remove(Object ok) {
/* 288 */     double k = ((Double)ok).doubleValue();
/*     */ 
/* 290 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 292 */     while (this.used[pos] != 0) {
/* 293 */       if (this.strategy.equals(this.key[pos], k)) {
/* 294 */         this.size -= 1;
/* 295 */         char v = this.value[pos];
/* 296 */         shiftKeys(pos);
/* 297 */         return Character.valueOf(v);
/*     */       }
/* 299 */       pos = pos + 1 & this.mask;
/*     */     }
/* 301 */     return null;
/*     */   }
/*     */   public Character get(Double ok) {
/* 304 */     double k = ok.doubleValue();
/*     */ 
/* 306 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 308 */     while (this.used[pos] != 0) {
/* 309 */       if (this.strategy.equals(this.key[pos], k)) return Character.valueOf(this.value[pos]);
/* 310 */       pos = pos + 1 & this.mask;
/*     */     }
/* 312 */     return null;
/*     */   }
/*     */ 
/*     */   public char get(double k)
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
/*     */   public boolean containsKey(double k)
/*     */   {
/* 328 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 330 */     while (this.used[pos] != 0) {
/* 331 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 332 */       pos = pos + 1 & this.mask;
/*     */     }
/* 334 */     return false;
/*     */   }
/*     */   public boolean containsValue(char v) {
/* 337 */     char[] value = this.value;
/* 338 */     boolean[] used = this.used;
/* 339 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 349 */     if (this.size == 0) return;
/* 350 */     this.size = 0;
/* 351 */     BooleanArrays.fill(this.used, false);
/*     */   }
/*     */ 
/*     */   public int size() {
/* 355 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 358 */     return this.size == 0;
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
/* 375 */     return 16;
/*     */   }
/*     */ 
/*     */   public Double2CharMap.FastEntrySet double2CharEntrySet()
/*     */   {
/* 581 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 582 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public DoubleSet keySet()
/*     */   {
/* 615 */     if (this.keys == null) this.keys = new KeySet(null);
/* 616 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 630 */     if (this.values == null) this.values = new AbstractCharCollection() {
/*     */         public CharIterator iterator() {
/* 632 */           return new Double2CharOpenCustomHashMap.ValueIterator(Double2CharOpenCustomHashMap.this);
/*     */         }
/*     */         public int size() {
/* 635 */           return Double2CharOpenCustomHashMap.this.size;
/*     */         }
/*     */         public boolean contains(char v) {
/* 638 */           return Double2CharOpenCustomHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 641 */           Double2CharOpenCustomHashMap.this.clear();
/*     */         }
/*     */       };
/* 644 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 658 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 673 */     int l = HashCommon.arraySize(this.size, this.f);
/* 674 */     if (l >= this.n) return true; try
/*     */     {
/* 676 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 678 */       return false;
/* 679 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 700 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 701 */     if (this.n <= l) return true; try
/*     */     {
/* 703 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 705 */       return false;
/* 706 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 719 */     int i = 0;
/* 720 */     boolean[] used = this.used;
/*     */ 
/* 722 */     double[] key = this.key;
/* 723 */     char[] value = this.value;
/* 724 */     int newMask = newN - 1;
/* 725 */     double[] newKey = new double[newN];
/* 726 */     char[] newValue = new char[newN];
/* 727 */     boolean[] newUsed = new boolean[newN];
/* 728 */     for (int j = this.size; j-- != 0; ) {
/* 729 */       while (used[i] == 0) i++;
/* 730 */       double k = key[i];
/* 731 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 732 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 733 */       newUsed[pos] = true;
/* 734 */       newKey[pos] = k;
/* 735 */       newValue[pos] = value[i];
/* 736 */       i++;
/*     */     }
/* 738 */     this.n = newN;
/* 739 */     this.mask = newMask;
/* 740 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 741 */     this.key = newKey;
/* 742 */     this.value = newValue;
/* 743 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Double2CharOpenCustomHashMap clone()
/*     */   {
/*     */     Double2CharOpenCustomHashMap c;
/*     */     try
/*     */     {
/* 756 */       c = (Double2CharOpenCustomHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 759 */       throw new InternalError();
/*     */     }
/* 761 */     c.keys = null;
/* 762 */     c.values = null;
/* 763 */     c.entries = null;
/* 764 */     c.key = ((double[])this.key.clone());
/* 765 */     c.value = ((char[])this.value.clone());
/* 766 */     c.used = ((boolean[])this.used.clone());
/* 767 */     c.strategy = this.strategy;
/* 768 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 780 */     int h = 0;
/* 781 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 782 */       while (this.used[i] == 0) i++;
/* 783 */       t = this.strategy.hashCode(this.key[i]);
/* 784 */       t ^= this.value[i];
/* 785 */       h += t;
/* 786 */       i++;
/*     */     }
/* 788 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 791 */     double[] key = this.key;
/* 792 */     char[] value = this.value;
/* 793 */     MapIterator i = new MapIterator(null);
/* 794 */     s.defaultWriteObject();
/* 795 */     for (int j = this.size; j-- != 0; ) {
/* 796 */       int e = i.nextEntry();
/* 797 */       s.writeDouble(key[e]);
/* 798 */       s.writeChar(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 803 */     s.defaultReadObject();
/* 804 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 805 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 806 */     this.mask = (this.n - 1);
/* 807 */     double[] key = this.key = new double[this.n];
/* 808 */     char[] value = this.value = new char[this.n];
/* 809 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 812 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 813 */       double k = s.readDouble();
/* 814 */       char v = s.readChar();
/* 815 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 816 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 817 */       used[pos] = true;
/* 818 */       key[pos] = k;
/* 819 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Double2CharOpenCustomHashMap.MapIterator
/*     */     implements CharIterator
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 625 */       super(null); } 
/* 626 */     public char nextChar() { return Double2CharOpenCustomHashMap.this.value[nextEntry()]; } 
/* 627 */     public Character next() { return Character.valueOf(Double2CharOpenCustomHashMap.this.value[nextEntry()]); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class KeySet extends AbstractDoubleSet
/*     */   {
/*     */     private KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public DoubleIterator iterator()
/*     */     {
/* 597 */       return new Double2CharOpenCustomHashMap.KeyIterator(Double2CharOpenCustomHashMap.this);
/*     */     }
/*     */     public int size() {
/* 600 */       return Double2CharOpenCustomHashMap.this.size;
/*     */     }
/*     */     public boolean contains(double k) {
/* 603 */       return Double2CharOpenCustomHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(double k) {
/* 606 */       int oldSize = Double2CharOpenCustomHashMap.this.size;
/* 607 */       Double2CharOpenCustomHashMap.this.remove(k);
/* 608 */       return Double2CharOpenCustomHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 611 */       Double2CharOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Double2CharOpenCustomHashMap.MapIterator
/*     */     implements DoubleIterator
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 591 */       super(null); } 
/* 592 */     public double nextDouble() { return Double2CharOpenCustomHashMap.this.key[nextEntry()]; } 
/* 593 */     public Double next() { return Double.valueOf(Double2CharOpenCustomHashMap.this.key[nextEntry()]); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Double2CharMap.Entry>
/*     */     implements Double2CharMap.FastEntrySet
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Double2CharMap.Entry> iterator()
/*     */     {
/* 537 */       return new Double2CharOpenCustomHashMap.EntryIterator(Double2CharOpenCustomHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Double2CharMap.Entry> fastIterator() {
/* 540 */       return new Double2CharOpenCustomHashMap.FastEntryIterator(Double2CharOpenCustomHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 544 */       if (!(o instanceof Map.Entry)) return false;
/* 545 */       Map.Entry e = (Map.Entry)o;
/* 546 */       double k = ((Double)e.getKey()).doubleValue();
/*     */ 
/* 548 */       int pos = HashCommon.murmurHash3(Double2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Double2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 550 */       while (Double2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 551 */         if (Double2CharOpenCustomHashMap.this.strategy.equals(Double2CharOpenCustomHashMap.this.key[pos], k)) return Double2CharOpenCustomHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/* 552 */         pos = pos + 1 & Double2CharOpenCustomHashMap.this.mask;
/*     */       }
/* 554 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 558 */       if (!(o instanceof Map.Entry)) return false;
/* 559 */       Map.Entry e = (Map.Entry)o;
/* 560 */       double k = ((Double)e.getKey()).doubleValue();
/*     */ 
/* 562 */       int pos = HashCommon.murmurHash3(Double2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Double2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 564 */       while (Double2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 565 */         if (Double2CharOpenCustomHashMap.this.strategy.equals(Double2CharOpenCustomHashMap.this.key[pos], k)) {
/* 566 */           Double2CharOpenCustomHashMap.this.remove(e.getKey());
/* 567 */           return true;
/*     */         }
/* 569 */         pos = pos + 1 & Double2CharOpenCustomHashMap.this.mask;
/*     */       }
/* 571 */       return false;
/*     */     }
/*     */     public int size() {
/* 574 */       return Double2CharOpenCustomHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 577 */       Double2CharOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Double2CharOpenCustomHashMap.MapIterator
/*     */     implements ObjectIterator<Double2CharMap.Entry>
/*     */   {
/* 527 */     final AbstractDouble2CharMap.BasicEntry entry = new AbstractDouble2CharMap.BasicEntry(0.0D, '\000');
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 526 */       super(null);
/*     */     }
/*     */     public AbstractDouble2CharMap.BasicEntry next() {
/* 529 */       int e = nextEntry();
/* 530 */       this.entry.key = Double2CharOpenCustomHashMap.this.key[e];
/* 531 */       this.entry.value = Double2CharOpenCustomHashMap.this.value[e];
/* 532 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Double2CharOpenCustomHashMap.MapIterator
/*     */     implements ObjectIterator<Double2CharMap.Entry>
/*     */   {
/*     */     private Double2CharOpenCustomHashMap.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 515 */       super(null);
/*     */     }
/*     */     public Double2CharMap.Entry next() {
/* 518 */       return this.entry = new Double2CharOpenCustomHashMap.MapEntry(Double2CharOpenCustomHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 522 */       super.remove();
/* 523 */       Double2CharOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private class MapIterator
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     DoubleArrayList wrapped;
/*     */ 
/*     */     private MapIterator()
/*     */     {
/* 424 */       this.pos = Double2CharOpenCustomHashMap.this.n;
/*     */ 
/* 427 */       this.last = -1;
/*     */ 
/* 429 */       this.c = Double2CharOpenCustomHashMap.this.size;
/*     */ 
/* 434 */       boolean[] used = Double2CharOpenCustomHashMap.this.used;
/* 435 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 438 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 441 */       if (!hasNext()) throw new NoSuchElementException();
/* 442 */       this.c -= 1;
/*     */ 
/* 444 */       if (this.pos < 0) {
/* 445 */         double k = this.wrapped.getDouble(-(this.last = --this.pos) - 2);
/*     */ 
/* 447 */         int pos = HashCommon.murmurHash3(Double2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Double2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 449 */         while (Double2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 450 */           if (Double2CharOpenCustomHashMap.this.strategy.equals(Double2CharOpenCustomHashMap.this.key[pos], k)) return pos;
/* 451 */           pos = pos + 1 & Double2CharOpenCustomHashMap.this.mask;
/*     */         }
/*     */       }
/* 454 */       this.last = this.pos;
/*     */ 
/* 456 */       if (this.c != 0) {
/* 457 */         boolean[] used = Double2CharOpenCustomHashMap.this.used;
/* 458 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 461 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 474 */         pos = (last = pos) + 1 & Double2CharOpenCustomHashMap.this.mask;
/* 475 */         while (Double2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 476 */           int slot = HashCommon.murmurHash3(Double2CharOpenCustomHashMap.this.strategy.hashCode(Double2CharOpenCustomHashMap.this.key[pos])) & Double2CharOpenCustomHashMap.this.mask;
/* 477 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 478 */           pos = pos + 1 & Double2CharOpenCustomHashMap.this.mask;
/*     */         }
/* 480 */         if (Double2CharOpenCustomHashMap.this.used[pos] == 0) break;
/* 481 */         if (pos < last)
/*     */         {
/* 483 */           if (this.wrapped == null) this.wrapped = new DoubleArrayList();
/* 484 */           this.wrapped.add(Double2CharOpenCustomHashMap.this.key[pos]);
/*     */         }
/* 486 */         Double2CharOpenCustomHashMap.this.key[last] = Double2CharOpenCustomHashMap.this.key[pos];
/* 487 */         Double2CharOpenCustomHashMap.this.value[last] = Double2CharOpenCustomHashMap.this.value[pos];
/*     */       }
/* 489 */       Double2CharOpenCustomHashMap.this.used[last] = false;
/* 490 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 494 */       if (this.last == -1) throw new IllegalStateException();
/* 495 */       if (this.pos < -1)
/*     */       {
/* 497 */         Double2CharOpenCustomHashMap.this.remove(this.wrapped.getDouble(-this.pos - 2));
/* 498 */         this.last = -1;
/* 499 */         return;
/*     */       }
/* 501 */       Double2CharOpenCustomHashMap.this.size -= 1;
/* 502 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 503 */         this.c += 1;
/* 504 */         nextEntry();
/*     */       }
/* 506 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 510 */       int i = n;
/* 511 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 512 */       return n - i - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class MapEntry
/*     */     implements Double2CharMap.Entry, Map.Entry<Double, Character>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 385 */       this.index = index;
/*     */     }
/*     */     public Double getKey() {
/* 388 */       return Double.valueOf(Double2CharOpenCustomHashMap.this.key[this.index]);
/*     */     }
/*     */     public double getDoubleKey() {
/* 391 */       return Double2CharOpenCustomHashMap.this.key[this.index];
/*     */     }
/*     */     public Character getValue() {
/* 394 */       return Character.valueOf(Double2CharOpenCustomHashMap.this.value[this.index]);
/*     */     }
/*     */     public char getCharValue() {
/* 397 */       return Double2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public char setValue(char v) {
/* 400 */       char oldValue = Double2CharOpenCustomHashMap.this.value[this.index];
/* 401 */       Double2CharOpenCustomHashMap.this.value[this.index] = v;
/* 402 */       return oldValue;
/*     */     }
/*     */     public Character setValue(Character v) {
/* 405 */       return Character.valueOf(setValue(v.charValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 409 */       if (!(o instanceof Map.Entry)) return false;
/* 410 */       Map.Entry e = (Map.Entry)o;
/* 411 */       return (Double2CharOpenCustomHashMap.this.strategy.equals(Double2CharOpenCustomHashMap.this.key[this.index], ((Double)e.getKey()).doubleValue())) && (Double2CharOpenCustomHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/*     */     }
/*     */     public int hashCode() {
/* 414 */       return Double2CharOpenCustomHashMap.this.strategy.hashCode(Double2CharOpenCustomHashMap.this.key[this.index]) ^ Double2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public String toString() {
/* 417 */       return Double2CharOpenCustomHashMap.this.key[this.index] + "=>" + Double2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.Double2CharOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */