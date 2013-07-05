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
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectLinkedOpenCustomHashSet<K> extends AbstractObjectSortedSet<K>
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient K[] key;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/* 101 */   protected transient int first = -1;
/*     */ 
/* 103 */   protected transient int last = -1;
/*     */   protected transient long[] link;
/*     */   protected Hash.Strategy<K> strategy;
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(int expected, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 121 */     this.strategy = strategy;
/* 122 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 123 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 124 */     this.f = f;
/* 125 */     this.n = HashCommon.arraySize(expected, f);
/* 126 */     this.mask = (this.n - 1);
/* 127 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 128 */     this.key = ((Object[])new Object[this.n]);
/* 129 */     this.used = new boolean[this.n];
/* 130 */     this.link = new long[this.n];
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(int expected, Hash.Strategy<K> strategy)
/*     */   {
/* 138 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(Hash.Strategy<K> strategy)
/*     */   {
/* 145 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(Collection<? extends K> c, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 154 */     this(c.size(), f, strategy);
/* 155 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(Collection<? extends K> c, Hash.Strategy<K> strategy)
/*     */   {
/* 164 */     this(c, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(ObjectCollection<? extends K> c, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 173 */     this(c.size(), f, strategy);
/* 174 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(ObjectCollection<? extends K> c, Hash.Strategy<K> strategy)
/*     */   {
/* 183 */     this(c, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(ObjectIterator<K> i, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 192 */     this(16, f, strategy);
/* 193 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(ObjectIterator<K> i, Hash.Strategy<K> strategy)
/*     */   {
/* 201 */     this(i, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(K[] a, int offset, int length, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 212 */     this(length < 0 ? 0 : length, f, strategy);
/* 213 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 214 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(K[] a, int offset, int length, Hash.Strategy<K> strategy)
/*     */   {
/* 224 */     this(a, offset, length, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(K[] a, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 233 */     this(a, 0, a.length, f, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet(K[] a, Hash.Strategy<K> strategy)
/*     */   {
/* 242 */     this(a, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Hash.Strategy<K> strategy()
/*     */   {
/* 249 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public boolean add(K k)
/*     */   {
/* 257 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 259 */     while (this.used[pos] != 0) {
/* 260 */       if (this.strategy.equals(this.key[pos], k)) return false;
/* 261 */       pos = pos + 1 & this.mask;
/*     */     }
/* 263 */     this.used[pos] = true;
/* 264 */     this.key[pos] = k;
/* 265 */     if (this.size == 0) {
/* 266 */       this.first = (this.last = pos);
/*     */ 
/* 268 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 271 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 272 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 273 */       this.last = pos;
/*     */     }
/* 275 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 277 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 289 */       pos = (last = pos) + 1 & this.mask;
/* 290 */       while (this.used[pos] != 0) {
/* 291 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 292 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 293 */         pos = pos + 1 & this.mask;
/*     */       }
/* 295 */       if (this.used[pos] == 0) break;
/* 296 */       this.key[last] = this.key[pos];
/* 297 */       fixPointers(pos, last);
/*     */     }
/* 299 */     this.used[last] = false;
/* 300 */     this.key[last] = null;
/* 301 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object k)
/*     */   {
/* 306 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 308 */     while (this.used[pos] != 0) {
/* 309 */       if (this.strategy.equals(this.key[pos], k)) {
/* 310 */         this.size -= 1;
/* 311 */         fixPointers(pos);
/* 312 */         shiftKeys(pos);
/*     */ 
/* 314 */         return true;
/*     */       }
/* 316 */       pos = pos + 1 & this.mask;
/*     */     }
/* 318 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k)
/*     */   {
/* 323 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 325 */     while (this.used[pos] != 0) {
/* 326 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 327 */       pos = pos + 1 & this.mask;
/*     */     }
/* 329 */     return false;
/*     */   }
/*     */ 
/*     */   public K get(Object k)
/*     */   {
/* 337 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 339 */     while (this.used[pos] != 0) {
/* 340 */       if (this.strategy.equals(this.key[pos], k)) return this.key[pos];
/* 341 */       pos = pos + 1 & this.mask;
/*     */     }
/* 343 */     return null;
/*     */   }
/*     */ 
/*     */   public K removeFirst()
/*     */   {
/* 350 */     if (this.size == 0) throw new NoSuchElementException();
/* 351 */     this.size -= 1;
/* 352 */     int pos = this.first;
/*     */ 
/* 354 */     this.first = ((int)this.link[pos]);
/* 355 */     if (0 <= this.first)
/*     */     {
/* 357 */       this.link[this.first] |= -4294967296L;
/*     */     }
/* 359 */     Object k = this.key[pos];
/* 360 */     shiftKeys(pos);
/* 361 */     return k;
/*     */   }
/*     */ 
/*     */   public K removeLast()
/*     */   {
/* 368 */     if (this.size == 0) throw new NoSuchElementException();
/* 369 */     this.size -= 1;
/* 370 */     int pos = this.last;
/*     */ 
/* 372 */     this.last = ((int)(this.link[pos] >>> 32));
/* 373 */     if (0 <= this.last)
/*     */     {
/* 375 */       this.link[this.last] |= 4294967295L;
/*     */     }
/* 377 */     Object k = this.key[pos];
/* 378 */     shiftKeys(pos);
/* 379 */     return k;
/*     */   }
/*     */   private void moveIndexToFirst(int i) {
/* 382 */     if ((this.size == 1) || (this.first == i)) return;
/* 383 */     if (this.last == i) {
/* 384 */       this.last = ((int)(this.link[i] >>> 32));
/*     */ 
/* 386 */       this.link[this.last] |= 4294967295L;
/*     */     }
/*     */     else {
/* 389 */       long linki = this.link[i];
/* 390 */       int prev = (int)(linki >>> 32);
/* 391 */       int next = (int)linki;
/* 392 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 393 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 395 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 396 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 397 */     this.first = i;
/*     */   }
/*     */   private void moveIndexToLast(int i) {
/* 400 */     if ((this.size == 1) || (this.last == i)) return;
/* 401 */     if (this.first == i) {
/* 402 */       this.first = ((int)this.link[i]);
/*     */ 
/* 404 */       this.link[this.first] |= -4294967296L;
/*     */     }
/*     */     else {
/* 407 */       long linki = this.link[i];
/* 408 */       int prev = (int)(linki >>> 32);
/* 409 */       int next = (int)linki;
/* 410 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 411 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 413 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 414 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 415 */     this.last = i;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToFirst(K k)
/*     */   {
/* 423 */     Object[] key = this.key;
/* 424 */     boolean[] used = this.used;
/* 425 */     int mask = this.mask;
/*     */ 
/* 427 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*     */ 
/* 429 */     while (used[pos] != 0) {
/* 430 */       if (this.strategy.equals(k, key[pos])) {
/* 431 */         moveIndexToFirst(pos);
/* 432 */         return false;
/*     */       }
/* 434 */       pos = pos + 1 & mask;
/*     */     }
/* 436 */     used[pos] = true;
/* 437 */     key[pos] = k;
/* 438 */     if (this.size == 0) {
/* 439 */       this.first = (this.last = pos);
/*     */ 
/* 441 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 444 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 445 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 446 */       this.first = pos;
/*     */     }
/* 448 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 450 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToLast(K k)
/*     */   {
/* 458 */     Object[] key = this.key;
/* 459 */     boolean[] used = this.used;
/* 460 */     int mask = this.mask;
/*     */ 
/* 462 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*     */ 
/* 464 */     while (used[pos] != 0) {
/* 465 */       if (this.strategy.equals(k, key[pos])) {
/* 466 */         moveIndexToLast(pos);
/* 467 */         return false;
/*     */       }
/* 469 */       pos = pos + 1 & mask;
/*     */     }
/* 471 */     used[pos] = true;
/* 472 */     key[pos] = k;
/* 473 */     if (this.size == 0) {
/* 474 */       this.first = (this.last = pos);
/*     */ 
/* 476 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 479 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 480 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 481 */       this.last = pos;
/*     */     }
/* 483 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 485 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 494 */     if (this.size == 0) return;
/* 495 */     this.size = 0;
/* 496 */     BooleanArrays.fill(this.used, false);
/* 497 */     ObjectArrays.fill(this.key, null);
/* 498 */     this.first = (this.last = -1);
/*     */   }
/*     */   public int size() {
/* 501 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 504 */     return this.size == 0;
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
/* 521 */     return 16;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int i)
/*     */   {
/* 531 */     if (this.size == 0) {
/* 532 */       this.first = (this.last = -1);
/* 533 */       return;
/*     */     }
/* 535 */     if (this.first == i) {
/* 536 */       this.first = ((int)this.link[i]);
/* 537 */       if (0 <= this.first)
/*     */       {
/* 539 */         this.link[this.first] |= -4294967296L;
/*     */       }
/* 541 */       return;
/*     */     }
/* 543 */     if (this.last == i) {
/* 544 */       this.last = ((int)(this.link[i] >>> 32));
/* 545 */       if (0 <= this.last)
/*     */       {
/* 547 */         this.link[this.last] |= 4294967295L;
/*     */       }
/* 549 */       return;
/*     */     }
/* 551 */     long linki = this.link[i];
/* 552 */     int prev = (int)(linki >>> 32);
/* 553 */     int next = (int)linki;
/* 554 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 555 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int s, int d)
/*     */   {
/* 566 */     if (this.size == 1) {
/* 567 */       this.first = (this.last = d);
/*     */ 
/* 569 */       this.link[d] = -1L;
/* 570 */       return;
/*     */     }
/* 572 */     if (this.first == s) {
/* 573 */       this.first = d;
/* 574 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 575 */       this.link[d] = this.link[s];
/* 576 */       return;
/*     */     }
/* 578 */     if (this.last == s) {
/* 579 */       this.last = d;
/* 580 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 581 */       this.link[d] = this.link[s];
/* 582 */       return;
/*     */     }
/* 584 */     long links = this.link[s];
/* 585 */     int prev = (int)(links >>> 32);
/* 586 */     int next = (int)links;
/* 587 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 588 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 589 */     this.link[d] = links;
/*     */   }
/*     */ 
/*     */   public K first()
/*     */   {
/* 596 */     if (this.size == 0) throw new NoSuchElementException();
/* 597 */     return this.key[this.first];
/*     */   }
/*     */ 
/*     */   public K last()
/*     */   {
/* 604 */     if (this.size == 0) throw new NoSuchElementException();
/* 605 */     return this.key[this.last];
/*     */   }
/* 607 */   public ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/* 608 */   public ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/* 609 */   public ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); } 
/* 610 */   public Comparator<? super K> comparator() { return null; }
/*     */ 
/*     */ 
/*     */   public ObjectListIterator<K> iterator(K from)
/*     */   {
/* 744 */     return new SetIterator(from);
/*     */   }
/*     */   public ObjectListIterator<K> iterator() {
/* 747 */     return new SetIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 761 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 776 */     int l = HashCommon.arraySize(this.size, this.f);
/* 777 */     if (l >= this.n) return true; try
/*     */     {
/* 779 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 781 */       return false;
/* 782 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 803 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 804 */     if (this.n <= l) return true; try
/*     */     {
/* 806 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 808 */       return false;
/* 809 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 822 */     int i = this.first; int prev = -1; int newPrev = -1;
/*     */ 
/* 824 */     Object[] key = this.key;
/* 825 */     int newMask = newN - 1;
/* 826 */     Object[] newKey = (Object[])new Object[newN];
/* 827 */     boolean[] newUsed = new boolean[newN];
/* 828 */     long[] link = this.link;
/* 829 */     long[] newLink = new long[newN];
/* 830 */     this.first = -1;
/* 831 */     for (int j = this.size; j-- != 0; ) {
/* 832 */       Object k = key[i];
/* 833 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 834 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 835 */       newUsed[pos] = true;
/* 836 */       newKey[pos] = k;
/* 837 */       if (prev != -1) {
/* 838 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 839 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 840 */         newPrev = pos;
/*     */       }
/*     */       else {
/* 843 */         newPrev = this.first = pos;
/*     */ 
/* 845 */         newLink[pos] = -1L;
/*     */       }
/* 847 */       int t = i;
/* 848 */       i = (int)link[i];
/* 849 */       prev = t;
/*     */     }
/* 851 */     this.n = newN;
/* 852 */     this.mask = newMask;
/* 853 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 854 */     this.key = newKey;
/* 855 */     this.used = newUsed;
/* 856 */     this.link = newLink;
/* 857 */     this.last = newPrev;
/* 858 */     if (newPrev != -1)
/*     */     {
/* 860 */       newLink[newPrev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenCustomHashSet<K> clone()
/*     */   {
/*     */     ObjectLinkedOpenCustomHashSet c;
/*     */     try
/*     */     {
/* 873 */       c = (ObjectLinkedOpenCustomHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 876 */       throw new InternalError();
/*     */     }
/* 878 */     c.key = ((Object[])this.key.clone());
/* 879 */     c.used = ((boolean[])this.used.clone());
/* 880 */     c.link = ((long[])this.link.clone());
/* 881 */     c.strategy = this.strategy;
/* 882 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 894 */     int h = 0; int i = 0; int j = this.size;
/* 895 */     while (j-- != 0) {
/* 896 */       while (this.used[i] == 0) i++;
/* 897 */       if (this != this.key[i])
/* 898 */         h += this.strategy.hashCode(this.key[i]);
/* 899 */       i++;
/*     */     }
/* 901 */     return h; } 
/* 904 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 905 */     s.defaultWriteObject();
/* 906 */     for (int j = this.size; j-- != 0; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 910 */     s.defaultReadObject();
/* 911 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 912 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 913 */     this.mask = (this.n - 1);
/* 914 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 915 */     boolean[] used = this.used = new boolean[this.n];
/* 916 */     long[] link = this.link = new long[this.n];
/* 917 */     int prev = -1;
/* 918 */     this.first = (this.last = -1);
/*     */ 
/* 920 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 921 */       Object k = s.readObject();
/* 922 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 923 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 924 */       used[pos] = true;
/* 925 */       key[pos] = k;
/* 926 */       if (this.first != -1) {
/* 927 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 928 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 929 */         prev = pos;
/*     */       }
/*     */       else {
/* 932 */         prev = this.first = pos;
/*     */ 
/* 934 */         link[pos] |= -4294967296L;
/*     */       }
/*     */     }
/* 937 */     this.last = prev;
/* 938 */     if (prev != -1)
/*     */     {
/* 940 */       link[prev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractObjectListIterator<K>
/*     */   {
/* 619 */     int prev = -1;
/*     */ 
/* 621 */     int next = -1;
/*     */ 
/* 623 */     int curr = -1;
/*     */ 
/* 625 */     int index = -1;
/*     */ 
/* 627 */     SetIterator() { this.next = ObjectLinkedOpenCustomHashSet.this.first;
/* 628 */       this.index = 0; }
/*     */ 
/*     */     SetIterator() {
/* 631 */       if (ObjectLinkedOpenCustomHashSet.this.strategy.equals(ObjectLinkedOpenCustomHashSet.this.key[ObjectLinkedOpenCustomHashSet.this.last], from)) {
/* 632 */         this.prev = ObjectLinkedOpenCustomHashSet.this.last;
/* 633 */         this.index = ObjectLinkedOpenCustomHashSet.this.size;
/*     */       }
/*     */       else
/*     */       {
/* 637 */         int pos = HashCommon.murmurHash3(ObjectLinkedOpenCustomHashSet.this.strategy.hashCode(from)) & ObjectLinkedOpenCustomHashSet.this.mask;
/*     */ 
/* 639 */         while (ObjectLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 640 */           if (ObjectLinkedOpenCustomHashSet.this.strategy.equals(ObjectLinkedOpenCustomHashSet.this.key[pos], from))
/*     */           {
/* 642 */             this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[pos]);
/* 643 */             this.prev = pos;
/* 644 */             return;
/*     */           }
/* 646 */           pos = pos + 1 & ObjectLinkedOpenCustomHashSet.this.mask;
/*     */         }
/* 648 */         throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/*     */       }
/*     */     }
/* 651 */     public boolean hasNext() { return this.next != -1; } 
/* 652 */     public boolean hasPrevious() { return this.prev != -1; } 
/*     */     public K next() {
/* 654 */       if (!hasNext()) throw new NoSuchElementException();
/* 655 */       this.curr = this.next;
/* 656 */       this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[this.curr]);
/* 657 */       this.prev = this.curr;
/* 658 */       if (this.index >= 0) this.index += 1;
/*     */ 
/* 660 */       return ObjectLinkedOpenCustomHashSet.this.key[this.curr];
/*     */     }
/*     */     public K previous() {
/* 663 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 664 */       this.curr = this.prev;
/* 665 */       this.prev = ((int)(ObjectLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/* 666 */       this.next = this.curr;
/* 667 */       if (this.index >= 0) this.index -= 1;
/* 668 */       return ObjectLinkedOpenCustomHashSet.this.key[this.curr];
/*     */     }
/*     */     private final void ensureIndexKnown() {
/* 671 */       if (this.index >= 0) return;
/* 672 */       if (this.prev == -1) {
/* 673 */         this.index = 0;
/* 674 */         return;
/*     */       }
/* 676 */       if (this.next == -1) {
/* 677 */         this.index = ObjectLinkedOpenCustomHashSet.this.size;
/* 678 */         return;
/*     */       }
/* 680 */       int pos = ObjectLinkedOpenCustomHashSet.this.first;
/* 681 */       this.index = 1;
/* 682 */       while (pos != this.prev) {
/* 683 */         pos = (int)ObjectLinkedOpenCustomHashSet.this.link[pos];
/* 684 */         this.index += 1;
/*     */       }
/*     */     }
/*     */ 
/* 688 */     public int nextIndex() { ensureIndexKnown();
/* 689 */       return this.index; }
/*     */ 
/*     */     public int previousIndex() {
/* 692 */       ensureIndexKnown();
/* 693 */       return this.index - 1;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 697 */       ensureIndexKnown();
/* 698 */       if (this.curr == -1) throw new IllegalStateException();
/* 699 */       if (this.curr == this.prev)
/*     */       {
/* 702 */         this.index -= 1;
/* 703 */         this.prev = ((int)(ObjectLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/*     */       }
/*     */       else {
/* 706 */         this.next = ((int)ObjectLinkedOpenCustomHashSet.this.link[this.curr]);
/* 707 */       }ObjectLinkedOpenCustomHashSet.this.size -= 1;
/*     */ 
/* 710 */       if (this.prev == -1) ObjectLinkedOpenCustomHashSet.this.first = this.next;
/*     */       else
/* 712 */         ObjectLinkedOpenCustomHashSet.this.link[this.prev] ^= (ObjectLinkedOpenCustomHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 713 */       if (this.next == -1) ObjectLinkedOpenCustomHashSet.this.last = this.prev;
/*     */       else
/* 715 */         ObjectLinkedOpenCustomHashSet.this.link[this.next] ^= (ObjectLinkedOpenCustomHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*     */       int last;
/*     */       while (true) {
/* 719 */         pos = (last = pos) + 1 & ObjectLinkedOpenCustomHashSet.this.mask;
/* 720 */         while (ObjectLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 721 */           int slot = HashCommon.murmurHash3(ObjectLinkedOpenCustomHashSet.this.strategy.hashCode(ObjectLinkedOpenCustomHashSet.this.key[pos])) & ObjectLinkedOpenCustomHashSet.this.mask;
/* 722 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 723 */           pos = pos + 1 & ObjectLinkedOpenCustomHashSet.this.mask;
/*     */         }
/* 725 */         if (ObjectLinkedOpenCustomHashSet.this.used[pos] == 0) break;
/* 726 */         ObjectLinkedOpenCustomHashSet.this.key[last] = ObjectLinkedOpenCustomHashSet.this.key[pos];
/* 727 */         if (this.next == pos) this.next = last;
/* 728 */         if (this.prev == pos) this.prev = last;
/* 729 */         ObjectLinkedOpenCustomHashSet.this.fixPointers(pos, last);
/*     */       }
/* 731 */       ObjectLinkedOpenCustomHashSet.this.used[last] = false;
/* 732 */       ObjectLinkedOpenCustomHashSet.this.key[last] = null;
/* 733 */       this.curr = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLinkedOpenCustomHashSet
 * JD-Core Version:    0.6.2
 */