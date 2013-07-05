/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharCollection;
/*     */ import it.unimi.dsi.fastutil.chars.CharIterator;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class Object2CharOpenCustomHashMap<K> extends AbstractObject2CharMap<K>
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient K[] key;
/*     */   protected transient char[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Object2CharMap.FastEntrySet<K> entries;
/*     */   protected volatile transient ObjectSet<K> keys;
/*     */   protected volatile transient CharCollection values;
/*     */   protected Hash.Strategy<K> strategy;
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(int expected, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 110 */     this.strategy = strategy;
/* 111 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 112 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 113 */     this.f = f;
/* 114 */     this.n = HashCommon.arraySize(expected, f);
/* 115 */     this.mask = (this.n - 1);
/* 116 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 117 */     this.key = ((Object[])new Object[this.n]);
/* 118 */     this.value = new char[this.n];
/* 119 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(int expected, Hash.Strategy<K> strategy)
/*     */   {
/* 127 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(Hash.Strategy<K> strategy)
/*     */   {
/* 134 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(Map<? extends K, ? extends Character> m, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 143 */     this(m.size(), f, strategy);
/* 144 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(Map<? extends K, ? extends Character> m, Hash.Strategy<K> strategy)
/*     */   {
/* 152 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(Object2CharMap<K> m, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 161 */     this(m.size(), f, strategy);
/* 162 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(Object2CharMap<K> m, Hash.Strategy<K> strategy)
/*     */   {
/* 170 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(K[] k, char[] v, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 181 */     this(k.length, f, strategy);
/* 182 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 183 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap(K[] k, char[] v, Hash.Strategy<K> strategy)
/*     */   {
/* 193 */     this(k, v, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Hash.Strategy<K> strategy()
/*     */   {
/* 200 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public char put(K k, char v)
/*     */   {
/* 208 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 210 */     while (this.used[pos] != 0) {
/* 211 */       if (this.strategy.equals(this.key[pos], k)) {
/* 212 */         char oldValue = this.value[pos];
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
/*     */   public Character put(K ok, Character ov) {
/* 226 */     char v = ov.charValue();
/* 227 */     Object k = ok;
/*     */ 
/* 229 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 231 */     while (this.used[pos] != 0) {
/* 232 */       if (this.strategy.equals(this.key[pos], k)) {
/* 233 */         Character oldValue = Character.valueOf(this.value[pos]);
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
/* 267 */     this.key[last] = null;
/* 268 */     return last;
/*     */   }
/*     */ 
/*     */   public char removeChar(Object k)
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
/* 288 */     Object k = ok;
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
/*     */ 
/*     */   public char getChar(Object k)
/*     */   {
/* 306 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 308 */     while (this.used[pos] != 0) {
/* 309 */       if (this.strategy.equals(this.key[pos], k)) return this.value[pos];
/* 310 */       pos = pos + 1 & this.mask;
/*     */     }
/* 312 */     return this.defRetValue;
/*     */   }
/*     */ 
/*     */   public boolean containsKey(Object k)
/*     */   {
/* 317 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 319 */     while (this.used[pos] != 0) {
/* 320 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 321 */       pos = pos + 1 & this.mask;
/*     */     }
/* 323 */     return false;
/*     */   }
/*     */   public boolean containsValue(char v) {
/* 326 */     char[] value = this.value;
/* 327 */     boolean[] used = this.used;
/* 328 */     for (int i = this.n; i-- != 0; return true) label16: if ((used[i] == 0) || (value[i] != v))
/*     */         break label16; return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 338 */     if (this.size == 0) return;
/* 339 */     this.size = 0;
/* 340 */     BooleanArrays.fill(this.used, false);
/*     */ 
/* 342 */     ObjectArrays.fill(this.key, null);
/*     */   }
/*     */   public int size() {
/* 345 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 348 */     return this.size == 0;
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
/* 365 */     return 16;
/*     */   }
/*     */ 
/*     */   public Object2CharMap.FastEntrySet<K> object2CharEntrySet()
/*     */   {
/* 569 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 570 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public ObjectSet<K> keySet()
/*     */   {
/* 602 */     if (this.keys == null) this.keys = new KeySet(null);
/* 603 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 617 */     if (this.values == null) this.values = new AbstractCharCollection() {
/*     */         public CharIterator iterator() {
/* 619 */           return new Object2CharOpenCustomHashMap.ValueIterator(Object2CharOpenCustomHashMap.this);
/*     */         }
/*     */         public int size() {
/* 622 */           return Object2CharOpenCustomHashMap.this.size;
/*     */         }
/*     */         public boolean contains(char v) {
/* 625 */           return Object2CharOpenCustomHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 628 */           Object2CharOpenCustomHashMap.this.clear();
/*     */         }
/*     */       };
/* 631 */     return this.values;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 645 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 660 */     int l = HashCommon.arraySize(this.size, this.f);
/* 661 */     if (l >= this.n) return true; try
/*     */     {
/* 663 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 665 */       return false;
/* 666 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 687 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 688 */     if (this.n <= l) return true; try
/*     */     {
/* 690 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 692 */       return false;
/* 693 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 706 */     int i = 0;
/* 707 */     boolean[] used = this.used;
/*     */ 
/* 709 */     Object[] key = this.key;
/* 710 */     char[] value = this.value;
/* 711 */     int newMask = newN - 1;
/* 712 */     Object[] newKey = (Object[])new Object[newN];
/* 713 */     char[] newValue = new char[newN];
/* 714 */     boolean[] newUsed = new boolean[newN];
/* 715 */     for (int j = this.size; j-- != 0; ) {
/* 716 */       while (used[i] == 0) i++;
/* 717 */       Object k = key[i];
/* 718 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 719 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 720 */       newUsed[pos] = true;
/* 721 */       newKey[pos] = k;
/* 722 */       newValue[pos] = value[i];
/* 723 */       i++;
/*     */     }
/* 725 */     this.n = newN;
/* 726 */     this.mask = newMask;
/* 727 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 728 */     this.key = newKey;
/* 729 */     this.value = newValue;
/* 730 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public Object2CharOpenCustomHashMap<K> clone()
/*     */   {
/*     */     Object2CharOpenCustomHashMap c;
/*     */     try
/*     */     {
/* 743 */       c = (Object2CharOpenCustomHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 746 */       throw new InternalError();
/*     */     }
/* 748 */     c.keys = null;
/* 749 */     c.values = null;
/* 750 */     c.entries = null;
/* 751 */     c.key = ((Object[])this.key.clone());
/* 752 */     c.value = ((char[])this.value.clone());
/* 753 */     c.used = ((boolean[])this.used.clone());
/* 754 */     c.strategy = this.strategy;
/* 755 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 767 */     int h = 0;
/* 768 */     int j = this.size; int i = 0; for (int t = 0; j-- != 0; ) {
/* 769 */       while (this.used[i] == 0) i++;
/* 770 */       if (this != this.key[i])
/* 771 */         t = this.strategy.hashCode(this.key[i]);
/* 772 */       t ^= this.value[i];
/* 773 */       h += t;
/* 774 */       i++;
/*     */     }
/* 776 */     return h;
/*     */   }
/*     */   private void writeObject(ObjectOutputStream s) throws IOException {
/* 779 */     Object[] key = this.key;
/* 780 */     char[] value = this.value;
/* 781 */     MapIterator i = new MapIterator(null);
/* 782 */     s.defaultWriteObject();
/* 783 */     for (int j = this.size; j-- != 0; ) {
/* 784 */       int e = i.nextEntry();
/* 785 */       s.writeObject(key[e]);
/* 786 */       s.writeChar(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 791 */     s.defaultReadObject();
/* 792 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 793 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 794 */     this.mask = (this.n - 1);
/* 795 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 796 */     char[] value = this.value = new char[this.n];
/* 797 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 800 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 801 */       Object k = s.readObject();
/* 802 */       char v = s.readChar();
/* 803 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 804 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 805 */       used[pos] = true;
/* 806 */       key[pos] = k;
/* 807 */       value[pos] = v;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private final class ValueIterator extends Object2CharOpenCustomHashMap.MapIterator
/*     */     implements CharIterator
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 612 */       super(null); } 
/* 613 */     public char nextChar() { return Object2CharOpenCustomHashMap.this.value[nextEntry()]; } 
/* 614 */     public Character next() { return Character.valueOf(Object2CharOpenCustomHashMap.this.value[nextEntry()]); }
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
/* 584 */       return new Object2CharOpenCustomHashMap.KeyIterator(Object2CharOpenCustomHashMap.this);
/*     */     }
/*     */     public int size() {
/* 587 */       return Object2CharOpenCustomHashMap.this.size;
/*     */     }
/*     */     public boolean contains(Object k) {
/* 590 */       return Object2CharOpenCustomHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(Object k) {
/* 593 */       int oldSize = Object2CharOpenCustomHashMap.this.size;
/* 594 */       Object2CharOpenCustomHashMap.this.remove(k);
/* 595 */       return Object2CharOpenCustomHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 598 */       Object2CharOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Object2CharOpenCustomHashMap<K>.MapIterator
/*     */     implements ObjectIterator<K>
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 579 */       super(null); } 
/* 580 */     public K next() { return Object2CharOpenCustomHashMap.this.key[nextEntry()]; }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Object2CharMap.Entry<K>>
/*     */     implements Object2CharMap.FastEntrySet<K>
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Object2CharMap.Entry<K>> iterator()
/*     */     {
/* 525 */       return new Object2CharOpenCustomHashMap.EntryIterator(Object2CharOpenCustomHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Object2CharMap.Entry<K>> fastIterator() {
/* 528 */       return new Object2CharOpenCustomHashMap.FastEntryIterator(Object2CharOpenCustomHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 532 */       if (!(o instanceof Map.Entry)) return false;
/* 533 */       Map.Entry e = (Map.Entry)o;
/* 534 */       Object k = e.getKey();
/*     */ 
/* 536 */       int pos = HashCommon.murmurHash3(Object2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Object2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 538 */       while (Object2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 539 */         if (Object2CharOpenCustomHashMap.this.strategy.equals(Object2CharOpenCustomHashMap.this.key[pos], k)) return Object2CharOpenCustomHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/* 540 */         pos = pos + 1 & Object2CharOpenCustomHashMap.this.mask;
/*     */       }
/* 542 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 546 */       if (!(o instanceof Map.Entry)) return false;
/* 547 */       Map.Entry e = (Map.Entry)o;
/* 548 */       Object k = e.getKey();
/*     */ 
/* 550 */       int pos = HashCommon.murmurHash3(Object2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Object2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 552 */       while (Object2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 553 */         if (Object2CharOpenCustomHashMap.this.strategy.equals(Object2CharOpenCustomHashMap.this.key[pos], k)) {
/* 554 */           Object2CharOpenCustomHashMap.this.remove(e.getKey());
/* 555 */           return true;
/*     */         }
/* 557 */         pos = pos + 1 & Object2CharOpenCustomHashMap.this.mask;
/*     */       }
/* 559 */       return false;
/*     */     }
/*     */     public int size() {
/* 562 */       return Object2CharOpenCustomHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 565 */       Object2CharOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Object2CharOpenCustomHashMap<K>.MapIterator
/*     */     implements ObjectIterator<Object2CharMap.Entry<K>>
/*     */   {
/* 515 */     final AbstractObject2CharMap.BasicEntry<K> entry = new AbstractObject2CharMap.BasicEntry(null, '\000');
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 514 */       super(null);
/*     */     }
/*     */     public AbstractObject2CharMap.BasicEntry<K> next() {
/* 517 */       int e = nextEntry();
/* 518 */       this.entry.key = Object2CharOpenCustomHashMap.this.key[e];
/* 519 */       this.entry.value = Object2CharOpenCustomHashMap.this.value[e];
/* 520 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Object2CharOpenCustomHashMap<K>.MapIterator
/*     */     implements ObjectIterator<Object2CharMap.Entry<K>>
/*     */   {
/*     */     private Object2CharOpenCustomHashMap<K>.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 503 */       super(null);
/*     */     }
/*     */     public Object2CharMap.Entry<K> next() {
/* 506 */       return this.entry = new Object2CharOpenCustomHashMap.MapEntry(Object2CharOpenCustomHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 510 */       super.remove();
/* 511 */       Object2CharOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
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
/* 411 */       this.pos = Object2CharOpenCustomHashMap.this.n;
/*     */ 
/* 414 */       this.last = -1;
/*     */ 
/* 416 */       this.c = Object2CharOpenCustomHashMap.this.size;
/*     */ 
/* 421 */       boolean[] used = Object2CharOpenCustomHashMap.this.used;
/* 422 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 425 */       return this.c != 0;
/*     */     }
/*     */     public int nextEntry() {
/* 428 */       if (!hasNext()) throw new NoSuchElementException();
/* 429 */       this.c -= 1;
/*     */ 
/* 431 */       if (this.pos < 0) {
/* 432 */         Object k = this.wrapped.get(-(this.last = --this.pos) - 2);
/*     */ 
/* 434 */         int pos = HashCommon.murmurHash3(Object2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Object2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 436 */         while (Object2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 437 */           if (Object2CharOpenCustomHashMap.this.strategy.equals(Object2CharOpenCustomHashMap.this.key[pos], k)) return pos;
/* 438 */           pos = pos + 1 & Object2CharOpenCustomHashMap.this.mask;
/*     */         }
/*     */       }
/* 441 */       this.last = this.pos;
/*     */ 
/* 443 */       if (this.c != 0) {
/* 444 */         boolean[] used = Object2CharOpenCustomHashMap.this.used;
/* 445 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 448 */       return this.last;
/*     */     }
/*     */ 
/*     */     protected final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 461 */         pos = (last = pos) + 1 & Object2CharOpenCustomHashMap.this.mask;
/* 462 */         while (Object2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 463 */           int slot = HashCommon.murmurHash3(Object2CharOpenCustomHashMap.this.strategy.hashCode(Object2CharOpenCustomHashMap.this.key[pos])) & Object2CharOpenCustomHashMap.this.mask;
/* 464 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 465 */           pos = pos + 1 & Object2CharOpenCustomHashMap.this.mask;
/*     */         }
/* 467 */         if (Object2CharOpenCustomHashMap.this.used[pos] == 0) break;
/* 468 */         if (pos < last)
/*     */         {
/* 470 */           if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 471 */           this.wrapped.add(Object2CharOpenCustomHashMap.this.key[pos]);
/*     */         }
/* 473 */         Object2CharOpenCustomHashMap.this.key[last] = Object2CharOpenCustomHashMap.this.key[pos];
/* 474 */         Object2CharOpenCustomHashMap.this.value[last] = Object2CharOpenCustomHashMap.this.value[pos];
/*     */       }
/* 476 */       Object2CharOpenCustomHashMap.this.used[last] = false;
/* 477 */       Object2CharOpenCustomHashMap.this.key[last] = null;
/* 478 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 482 */       if (this.last == -1) throw new IllegalStateException();
/* 483 */       if (this.pos < -1)
/*     */       {
/* 485 */         Object2CharOpenCustomHashMap.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 486 */         this.last = -1;
/* 487 */         return;
/*     */       }
/* 489 */       Object2CharOpenCustomHashMap.this.size -= 1;
/* 490 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 491 */         this.c += 1;
/* 492 */         nextEntry();
/*     */       }
/* 494 */       this.last = -1;
/*     */     }
/*     */ 
/*     */     public int skip(int n) {
/* 498 */       int i = n;
/* 499 */       while ((i-- != 0) && (hasNext())) nextEntry();
/* 500 */       return n - i - 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class MapEntry
/*     */     implements Object2CharMap.Entry<K>, Map.Entry<K, Character>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 375 */       this.index = index;
/*     */     }
/*     */     public K getKey() {
/* 378 */       return Object2CharOpenCustomHashMap.this.key[this.index];
/*     */     }
/*     */     public Character getValue() {
/* 381 */       return Character.valueOf(Object2CharOpenCustomHashMap.this.value[this.index]);
/*     */     }
/*     */     public char getCharValue() {
/* 384 */       return Object2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public char setValue(char v) {
/* 387 */       char oldValue = Object2CharOpenCustomHashMap.this.value[this.index];
/* 388 */       Object2CharOpenCustomHashMap.this.value[this.index] = v;
/* 389 */       return oldValue;
/*     */     }
/*     */     public Character setValue(Character v) {
/* 392 */       return Character.valueOf(setValue(v.charValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 396 */       if (!(o instanceof Map.Entry)) return false;
/* 397 */       Map.Entry e = (Map.Entry)o;
/* 398 */       return (Object2CharOpenCustomHashMap.this.strategy.equals(Object2CharOpenCustomHashMap.this.key[this.index], e.getKey())) && (Object2CharOpenCustomHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/*     */     }
/*     */     public int hashCode() {
/* 401 */       return Object2CharOpenCustomHashMap.this.strategy.hashCode(Object2CharOpenCustomHashMap.this.key[this.index]) ^ Object2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public String toString() {
/* 404 */       return Object2CharOpenCustomHashMap.this.key[this.index] + "=>" + Object2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2CharOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */