/*     */ package it.unimi.dsi.fastutil.bytes;
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
/*     */ public class Byte2CharOpenCustomHashMap extends AbstractByte2CharMap
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient byte[] key;
/*     */   protected transient char[] value;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */   protected volatile transient Byte2CharMap.FastEntrySet entries;
/*     */   protected volatile transient ByteSet keys;
/*     */   protected volatile transient CharCollection values;
/*     */   protected ByteHash.Strategy strategy;
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(int expected, float f, ByteHash.Strategy strategy)
/*     */   {
/* 111 */     this.strategy = strategy;
/* 112 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 113 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 114 */     this.f = f;
/* 115 */     this.n = HashCommon.arraySize(expected, f);
/* 116 */     this.mask = (this.n - 1);
/* 117 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 118 */     this.key = new byte[this.n];
/* 119 */     this.value = new char[this.n];
/* 120 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(int expected, ByteHash.Strategy strategy)
/*     */   {
/* 128 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(ByteHash.Strategy strategy)
/*     */   {
/* 135 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(Map<? extends Byte, ? extends Character> m, float f, ByteHash.Strategy strategy)
/*     */   {
/* 144 */     this(m.size(), f, strategy);
/* 145 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(Map<? extends Byte, ? extends Character> m, ByteHash.Strategy strategy)
/*     */   {
/* 153 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(Byte2CharMap m, float f, ByteHash.Strategy strategy)
/*     */   {
/* 162 */     this(m.size(), f, strategy);
/* 163 */     putAll(m);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(Byte2CharMap m, ByteHash.Strategy strategy)
/*     */   {
/* 171 */     this(m, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(byte[] k, char[] v, float f, ByteHash.Strategy strategy)
/*     */   {
/* 182 */     this(k.length, f, strategy);
/* 183 */     if (k.length != v.length) throw new IllegalArgumentException("The key array and the value array have different lengths (" + k.length + " and " + v.length + ")");
/* 184 */     for (int i = 0; i < k.length; i++) put(k[i], v[i]);
/*     */   }
/*     */ 
/*     */   public Byte2CharOpenCustomHashMap(byte[] k, char[] v, ByteHash.Strategy strategy)
/*     */   {
/* 194 */     this(k, v, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ByteHash.Strategy strategy()
/*     */   {
/* 201 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public char put(byte k, char v)
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
/*     */   public Character put(Byte ok, Character ov) {
/* 227 */     char v = ov.charValue();
/* 228 */     byte k = ok.byteValue();
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
/*     */   public char remove(byte k)
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
/* 288 */     byte k = ((Byte)ok).byteValue();
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
/*     */   public Character get(Byte ok) {
/* 304 */     byte k = ok.byteValue();
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
/*     */   public char get(byte k)
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
/*     */   public boolean containsKey(byte k)
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
/*     */   public Byte2CharMap.FastEntrySet byte2CharEntrySet()
/*     */   {
/* 581 */     if (this.entries == null) this.entries = new MapEntrySet(null);
/* 582 */     return this.entries;
/*     */   }
/*     */ 
/*     */   public ByteSet keySet()
/*     */   {
/* 615 */     if (this.keys == null) this.keys = new KeySet(null);
/* 616 */     return this.keys;
/*     */   }
/*     */ 
/*     */   public CharCollection values()
/*     */   {
/* 630 */     if (this.values == null) this.values = new AbstractCharCollection() {
/*     */         public CharIterator iterator() {
/* 632 */           return new Byte2CharOpenCustomHashMap.ValueIterator(Byte2CharOpenCustomHashMap.this);
/*     */         }
/*     */         public int size() {
/* 635 */           return Byte2CharOpenCustomHashMap.this.size;
/*     */         }
/*     */         public boolean contains(char v) {
/* 638 */           return Byte2CharOpenCustomHashMap.this.containsValue(v);
/*     */         }
/*     */         public void clear() {
/* 641 */           Byte2CharOpenCustomHashMap.this.clear();
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
/* 722 */     byte[] key = this.key;
/* 723 */     char[] value = this.value;
/* 724 */     int newMask = newN - 1;
/* 725 */     byte[] newKey = new byte[newN];
/* 726 */     char[] newValue = new char[newN];
/* 727 */     boolean[] newUsed = new boolean[newN];
/* 728 */     for (int j = this.size; j-- != 0; ) {
/* 729 */       while (used[i] == 0) i++;
/* 730 */       byte k = key[i];
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
/*     */   public Byte2CharOpenCustomHashMap clone()
/*     */   {
/*     */     Byte2CharOpenCustomHashMap c;
/*     */     try
/*     */     {
/* 756 */       c = (Byte2CharOpenCustomHashMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 759 */       throw new InternalError();
/*     */     }
/* 761 */     c.keys = null;
/* 762 */     c.values = null;
/* 763 */     c.entries = null;
/* 764 */     c.key = ((byte[])this.key.clone());
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
/* 791 */     byte[] key = this.key;
/* 792 */     char[] value = this.value;
/* 793 */     MapIterator i = new MapIterator(null);
/* 794 */     s.defaultWriteObject();
/* 795 */     for (int j = this.size; j-- != 0; ) {
/* 796 */       int e = i.nextEntry();
/* 797 */       s.writeByte(key[e]);
/* 798 */       s.writeChar(value[e]);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 803 */     s.defaultReadObject();
/* 804 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 805 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 806 */     this.mask = (this.n - 1);
/* 807 */     byte[] key = this.key = new byte[this.n];
/* 808 */     char[] value = this.value = new char[this.n];
/* 809 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 812 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 813 */       byte k = s.readByte();
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
/*     */   private final class ValueIterator extends Byte2CharOpenCustomHashMap.MapIterator
/*     */     implements CharIterator
/*     */   {
/*     */     public ValueIterator()
/*     */     {
/* 625 */       super(null); } 
/* 626 */     public char nextChar() { return Byte2CharOpenCustomHashMap.this.value[nextEntry()]; } 
/* 627 */     public Character next() { return Character.valueOf(Byte2CharOpenCustomHashMap.this.value[nextEntry()]); }
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
/* 597 */       return new Byte2CharOpenCustomHashMap.KeyIterator(Byte2CharOpenCustomHashMap.this);
/*     */     }
/*     */     public int size() {
/* 600 */       return Byte2CharOpenCustomHashMap.this.size;
/*     */     }
/*     */     public boolean contains(byte k) {
/* 603 */       return Byte2CharOpenCustomHashMap.this.containsKey(k);
/*     */     }
/*     */     public boolean remove(byte k) {
/* 606 */       int oldSize = Byte2CharOpenCustomHashMap.this.size;
/* 607 */       Byte2CharOpenCustomHashMap.this.remove(k);
/* 608 */       return Byte2CharOpenCustomHashMap.this.size != oldSize;
/*     */     }
/*     */     public void clear() {
/* 611 */       Byte2CharOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private final class KeyIterator extends Byte2CharOpenCustomHashMap.MapIterator
/*     */     implements ByteIterator
/*     */   {
/*     */     public KeyIterator()
/*     */     {
/* 591 */       super(null); } 
/* 592 */     public byte nextByte() { return Byte2CharOpenCustomHashMap.this.key[nextEntry()]; } 
/* 593 */     public Byte next() { return Byte.valueOf(Byte2CharOpenCustomHashMap.this.key[nextEntry()]); }
/*     */ 
/*     */   }
/*     */ 
/*     */   private final class MapEntrySet extends AbstractObjectSet<Byte2CharMap.Entry>
/*     */     implements Byte2CharMap.FastEntrySet
/*     */   {
/*     */     private MapEntrySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<Byte2CharMap.Entry> iterator()
/*     */     {
/* 537 */       return new Byte2CharOpenCustomHashMap.EntryIterator(Byte2CharOpenCustomHashMap.this, null);
/*     */     }
/*     */     public ObjectIterator<Byte2CharMap.Entry> fastIterator() {
/* 540 */       return new Byte2CharOpenCustomHashMap.FastEntryIterator(Byte2CharOpenCustomHashMap.this, null);
/*     */     }
/*     */ 
/*     */     public boolean contains(Object o) {
/* 544 */       if (!(o instanceof Map.Entry)) return false;
/* 545 */       Map.Entry e = (Map.Entry)o;
/* 546 */       byte k = ((Byte)e.getKey()).byteValue();
/*     */ 
/* 548 */       int pos = HashCommon.murmurHash3(Byte2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Byte2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 550 */       while (Byte2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 551 */         if (Byte2CharOpenCustomHashMap.this.strategy.equals(Byte2CharOpenCustomHashMap.this.key[pos], k)) return Byte2CharOpenCustomHashMap.this.value[pos] == ((Character)e.getValue()).charValue();
/* 552 */         pos = pos + 1 & Byte2CharOpenCustomHashMap.this.mask;
/*     */       }
/* 554 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean remove(Object o) {
/* 558 */       if (!(o instanceof Map.Entry)) return false;
/* 559 */       Map.Entry e = (Map.Entry)o;
/* 560 */       byte k = ((Byte)e.getKey()).byteValue();
/*     */ 
/* 562 */       int pos = HashCommon.murmurHash3(Byte2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Byte2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 564 */       while (Byte2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 565 */         if (Byte2CharOpenCustomHashMap.this.strategy.equals(Byte2CharOpenCustomHashMap.this.key[pos], k)) {
/* 566 */           Byte2CharOpenCustomHashMap.this.remove(e.getKey());
/* 567 */           return true;
/*     */         }
/* 569 */         pos = pos + 1 & Byte2CharOpenCustomHashMap.this.mask;
/*     */       }
/* 571 */       return false;
/*     */     }
/*     */     public int size() {
/* 574 */       return Byte2CharOpenCustomHashMap.this.size;
/*     */     }
/*     */     public void clear() {
/* 577 */       Byte2CharOpenCustomHashMap.this.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   private class FastEntryIterator extends Byte2CharOpenCustomHashMap.MapIterator
/*     */     implements ObjectIterator<Byte2CharMap.Entry>
/*     */   {
/* 527 */     final AbstractByte2CharMap.BasicEntry entry = new AbstractByte2CharMap.BasicEntry((byte)0, '\000');
/*     */ 
/*     */     private FastEntryIterator()
/*     */     {
/* 526 */       super(null);
/*     */     }
/*     */     public AbstractByte2CharMap.BasicEntry next() {
/* 529 */       int e = nextEntry();
/* 530 */       this.entry.key = Byte2CharOpenCustomHashMap.this.key[e];
/* 531 */       this.entry.value = Byte2CharOpenCustomHashMap.this.value[e];
/* 532 */       return this.entry;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class EntryIterator extends Byte2CharOpenCustomHashMap.MapIterator
/*     */     implements ObjectIterator<Byte2CharMap.Entry>
/*     */   {
/*     */     private Byte2CharOpenCustomHashMap.MapEntry entry;
/*     */ 
/*     */     private EntryIterator()
/*     */     {
/* 515 */       super(null);
/*     */     }
/*     */     public Byte2CharMap.Entry next() {
/* 518 */       return this.entry = new Byte2CharOpenCustomHashMap.MapEntry(Byte2CharOpenCustomHashMap.this, nextEntry());
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 522 */       super.remove();
/* 523 */       Byte2CharOpenCustomHashMap.MapEntry.access$102(this.entry, -1);
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
/* 424 */       this.pos = Byte2CharOpenCustomHashMap.this.n;
/*     */ 
/* 427 */       this.last = -1;
/*     */ 
/* 429 */       this.c = Byte2CharOpenCustomHashMap.this.size;
/*     */ 
/* 434 */       boolean[] used = Byte2CharOpenCustomHashMap.this.used;
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
/* 445 */         byte k = this.wrapped.getByte(-(this.last = --this.pos) - 2);
/*     */ 
/* 447 */         int pos = HashCommon.murmurHash3(Byte2CharOpenCustomHashMap.this.strategy.hashCode(k)) & Byte2CharOpenCustomHashMap.this.mask;
/*     */ 
/* 449 */         while (Byte2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 450 */           if (Byte2CharOpenCustomHashMap.this.strategy.equals(Byte2CharOpenCustomHashMap.this.key[pos], k)) return pos;
/* 451 */           pos = pos + 1 & Byte2CharOpenCustomHashMap.this.mask;
/*     */         }
/*     */       }
/* 454 */       this.last = this.pos;
/*     */ 
/* 456 */       if (this.c != 0) {
/* 457 */         boolean[] used = Byte2CharOpenCustomHashMap.this.used;
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
/* 474 */         pos = (last = pos) + 1 & Byte2CharOpenCustomHashMap.this.mask;
/* 475 */         while (Byte2CharOpenCustomHashMap.this.used[pos] != 0) {
/* 476 */           int slot = HashCommon.murmurHash3(Byte2CharOpenCustomHashMap.this.strategy.hashCode(Byte2CharOpenCustomHashMap.this.key[pos])) & Byte2CharOpenCustomHashMap.this.mask;
/* 477 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 478 */           pos = pos + 1 & Byte2CharOpenCustomHashMap.this.mask;
/*     */         }
/* 480 */         if (Byte2CharOpenCustomHashMap.this.used[pos] == 0) break;
/* 481 */         if (pos < last)
/*     */         {
/* 483 */           if (this.wrapped == null) this.wrapped = new ByteArrayList();
/* 484 */           this.wrapped.add(Byte2CharOpenCustomHashMap.this.key[pos]);
/*     */         }
/* 486 */         Byte2CharOpenCustomHashMap.this.key[last] = Byte2CharOpenCustomHashMap.this.key[pos];
/* 487 */         Byte2CharOpenCustomHashMap.this.value[last] = Byte2CharOpenCustomHashMap.this.value[pos];
/*     */       }
/* 489 */       Byte2CharOpenCustomHashMap.this.used[last] = false;
/* 490 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 494 */       if (this.last == -1) throw new IllegalStateException();
/* 495 */       if (this.pos < -1)
/*     */       {
/* 497 */         Byte2CharOpenCustomHashMap.this.remove(this.wrapped.getByte(-this.pos - 2));
/* 498 */         this.last = -1;
/* 499 */         return;
/*     */       }
/* 501 */       Byte2CharOpenCustomHashMap.this.size -= 1;
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
/*     */     implements Byte2CharMap.Entry, Map.Entry<Byte, Character>
/*     */   {
/*     */     private int index;
/*     */ 
/*     */     MapEntry(int index)
/*     */     {
/* 385 */       this.index = index;
/*     */     }
/*     */     public Byte getKey() {
/* 388 */       return Byte.valueOf(Byte2CharOpenCustomHashMap.this.key[this.index]);
/*     */     }
/*     */     public byte getByteKey() {
/* 391 */       return Byte2CharOpenCustomHashMap.this.key[this.index];
/*     */     }
/*     */     public Character getValue() {
/* 394 */       return Character.valueOf(Byte2CharOpenCustomHashMap.this.value[this.index]);
/*     */     }
/*     */     public char getCharValue() {
/* 397 */       return Byte2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public char setValue(char v) {
/* 400 */       char oldValue = Byte2CharOpenCustomHashMap.this.value[this.index];
/* 401 */       Byte2CharOpenCustomHashMap.this.value[this.index] = v;
/* 402 */       return oldValue;
/*     */     }
/*     */     public Character setValue(Character v) {
/* 405 */       return Character.valueOf(setValue(v.charValue()));
/*     */     }
/*     */ 
/*     */     public boolean equals(Object o) {
/* 409 */       if (!(o instanceof Map.Entry)) return false;
/* 410 */       Map.Entry e = (Map.Entry)o;
/* 411 */       return (Byte2CharOpenCustomHashMap.this.strategy.equals(Byte2CharOpenCustomHashMap.this.key[this.index], ((Byte)e.getKey()).byteValue())) && (Byte2CharOpenCustomHashMap.this.value[this.index] == ((Character)e.getValue()).charValue());
/*     */     }
/*     */     public int hashCode() {
/* 414 */       return Byte2CharOpenCustomHashMap.this.strategy.hashCode(Byte2CharOpenCustomHashMap.this.key[this.index]) ^ Byte2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */     public String toString() {
/* 417 */       return Byte2CharOpenCustomHashMap.this.key[this.index] + "=>" + Byte2CharOpenCustomHashMap.this.value[this.index];
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.Byte2CharOpenCustomHashMap
 * JD-Core Version:    0.6.2
 */