/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
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
/*     */ public class ObjectLinkedOpenHashSet<K> extends AbstractObjectSortedSet<K>
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
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(int expected, float f)
/*     */   {
/* 118 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 119 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 120 */     this.f = f;
/* 121 */     this.n = HashCommon.arraySize(expected, f);
/* 122 */     this.mask = (this.n - 1);
/* 123 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 124 */     this.key = ((Object[])new Object[this.n]);
/* 125 */     this.used = new boolean[this.n];
/* 126 */     this.link = new long[this.n];
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(int expected)
/*     */   {
/* 133 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet()
/*     */   {
/* 139 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(Collection<? extends K> c, float f)
/*     */   {
/* 147 */     this(c.size(), f);
/* 148 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(Collection<? extends K> c)
/*     */   {
/* 156 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(ObjectCollection<? extends K> c, float f)
/*     */   {
/* 164 */     this(c.size(), f);
/* 165 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(ObjectCollection<? extends K> c)
/*     */   {
/* 173 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(ObjectIterator<K> i, float f)
/*     */   {
/* 181 */     this(16, f);
/* 182 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(ObjectIterator<K> i)
/*     */   {
/* 189 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(K[] a, int offset, int length, float f)
/*     */   {
/* 199 */     this(length < 0 ? 0 : length, f);
/* 200 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 201 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(K[] a, int offset, int length)
/*     */   {
/* 210 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(K[] a, float f)
/*     */   {
/* 218 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet(K[] a)
/*     */   {
/* 226 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(K k)
/*     */   {
/* 234 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 236 */     while (this.used[pos] != 0) {
/* 237 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return false;
/* 238 */       pos = pos + 1 & this.mask;
/*     */     }
/* 240 */     this.used[pos] = true;
/* 241 */     this.key[pos] = k;
/* 242 */     if (this.size == 0) {
/* 243 */       this.first = (this.last = pos);
/*     */ 
/* 245 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 248 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 249 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 250 */       this.last = pos;
/*     */     }
/* 252 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 254 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 266 */       pos = (last = pos) + 1 & this.mask;
/* 267 */       while (this.used[pos] != 0) {
/* 268 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
/* 269 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 270 */         pos = pos + 1 & this.mask;
/*     */       }
/* 272 */       if (this.used[pos] == 0) break;
/* 273 */       this.key[last] = this.key[pos];
/* 274 */       fixPointers(pos, last);
/*     */     }
/* 276 */     this.used[last] = false;
/* 277 */     this.key[last] = null;
/* 278 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object k)
/*     */   {
/* 283 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 285 */     while (this.used[pos] != 0) {
/* 286 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
/* 287 */         this.size -= 1;
/* 288 */         fixPointers(pos);
/* 289 */         shiftKeys(pos);
/*     */ 
/* 291 */         return true;
/*     */       }
/* 293 */       pos = pos + 1 & this.mask;
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k)
/*     */   {
/* 300 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 302 */     while (this.used[pos] != 0) {
/* 303 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 304 */       pos = pos + 1 & this.mask;
/*     */     }
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */   public K get(Object k)
/*     */   {
/* 314 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 316 */     while (this.used[pos] != 0) {
/* 317 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.key[pos];
/* 318 */       pos = pos + 1 & this.mask;
/*     */     }
/* 320 */     return null;
/*     */   }
/*     */ 
/*     */   public K removeFirst()
/*     */   {
/* 327 */     if (this.size == 0) throw new NoSuchElementException();
/* 328 */     this.size -= 1;
/* 329 */     int pos = this.first;
/*     */ 
/* 331 */     this.first = ((int)this.link[pos]);
/* 332 */     if (0 <= this.first)
/*     */     {
/* 334 */       this.link[this.first] |= -4294967296L;
/*     */     }
/* 336 */     Object k = this.key[pos];
/* 337 */     shiftKeys(pos);
/* 338 */     return k;
/*     */   }
/*     */ 
/*     */   public K removeLast()
/*     */   {
/* 345 */     if (this.size == 0) throw new NoSuchElementException();
/* 346 */     this.size -= 1;
/* 347 */     int pos = this.last;
/*     */ 
/* 349 */     this.last = ((int)(this.link[pos] >>> 32));
/* 350 */     if (0 <= this.last)
/*     */     {
/* 352 */       this.link[this.last] |= 4294967295L;
/*     */     }
/* 354 */     Object k = this.key[pos];
/* 355 */     shiftKeys(pos);
/* 356 */     return k;
/*     */   }
/*     */   private void moveIndexToFirst(int i) {
/* 359 */     if ((this.size == 1) || (this.first == i)) return;
/* 360 */     if (this.last == i) {
/* 361 */       this.last = ((int)(this.link[i] >>> 32));
/*     */ 
/* 363 */       this.link[this.last] |= 4294967295L;
/*     */     }
/*     */     else {
/* 366 */       long linki = this.link[i];
/* 367 */       int prev = (int)(linki >>> 32);
/* 368 */       int next = (int)linki;
/* 369 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 370 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 372 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 373 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 374 */     this.first = i;
/*     */   }
/*     */   private void moveIndexToLast(int i) {
/* 377 */     if ((this.size == 1) || (this.last == i)) return;
/* 378 */     if (this.first == i) {
/* 379 */       this.first = ((int)this.link[i]);
/*     */ 
/* 381 */       this.link[this.first] |= -4294967296L;
/*     */     }
/*     */     else {
/* 384 */       long linki = this.link[i];
/* 385 */       int prev = (int)(linki >>> 32);
/* 386 */       int next = (int)linki;
/* 387 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 388 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 390 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 391 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 392 */     this.last = i;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToFirst(K k)
/*     */   {
/* 400 */     Object[] key = this.key;
/* 401 */     boolean[] used = this.used;
/* 402 */     int mask = this.mask;
/*     */ 
/* 404 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*     */ 
/* 406 */     while (used[pos] != 0) {
/* 407 */       if (k == null ? key[pos] == null : k.equals(key[pos])) {
/* 408 */         moveIndexToFirst(pos);
/* 409 */         return false;
/*     */       }
/* 411 */       pos = pos + 1 & mask;
/*     */     }
/* 413 */     used[pos] = true;
/* 414 */     key[pos] = k;
/* 415 */     if (this.size == 0) {
/* 416 */       this.first = (this.last = pos);
/*     */ 
/* 418 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 421 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 422 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 423 */       this.first = pos;
/*     */     }
/* 425 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 427 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToLast(K k)
/*     */   {
/* 435 */     Object[] key = this.key;
/* 436 */     boolean[] used = this.used;
/* 437 */     int mask = this.mask;
/*     */ 
/* 439 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & mask;
/*     */ 
/* 441 */     while (used[pos] != 0) {
/* 442 */       if (k == null ? key[pos] == null : k.equals(key[pos])) {
/* 443 */         moveIndexToLast(pos);
/* 444 */         return false;
/*     */       }
/* 446 */       pos = pos + 1 & mask;
/*     */     }
/* 448 */     used[pos] = true;
/* 449 */     key[pos] = k;
/* 450 */     if (this.size == 0) {
/* 451 */       this.first = (this.last = pos);
/*     */ 
/* 453 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 456 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 457 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 458 */       this.last = pos;
/*     */     }
/* 460 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 462 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 471 */     if (this.size == 0) return;
/* 472 */     this.size = 0;
/* 473 */     BooleanArrays.fill(this.used, false);
/* 474 */     ObjectArrays.fill(this.key, null);
/* 475 */     this.first = (this.last = -1);
/*     */   }
/*     */   public int size() {
/* 478 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 481 */     return this.size == 0;
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
/* 498 */     return 16;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int i)
/*     */   {
/* 508 */     if (this.size == 0) {
/* 509 */       this.first = (this.last = -1);
/* 510 */       return;
/*     */     }
/* 512 */     if (this.first == i) {
/* 513 */       this.first = ((int)this.link[i]);
/* 514 */       if (0 <= this.first)
/*     */       {
/* 516 */         this.link[this.first] |= -4294967296L;
/*     */       }
/* 518 */       return;
/*     */     }
/* 520 */     if (this.last == i) {
/* 521 */       this.last = ((int)(this.link[i] >>> 32));
/* 522 */       if (0 <= this.last)
/*     */       {
/* 524 */         this.link[this.last] |= 4294967295L;
/*     */       }
/* 526 */       return;
/*     */     }
/* 528 */     long linki = this.link[i];
/* 529 */     int prev = (int)(linki >>> 32);
/* 530 */     int next = (int)linki;
/* 531 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 532 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int s, int d)
/*     */   {
/* 543 */     if (this.size == 1) {
/* 544 */       this.first = (this.last = d);
/*     */ 
/* 546 */       this.link[d] = -1L;
/* 547 */       return;
/*     */     }
/* 549 */     if (this.first == s) {
/* 550 */       this.first = d;
/* 551 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 552 */       this.link[d] = this.link[s];
/* 553 */       return;
/*     */     }
/* 555 */     if (this.last == s) {
/* 556 */       this.last = d;
/* 557 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 558 */       this.link[d] = this.link[s];
/* 559 */       return;
/*     */     }
/* 561 */     long links = this.link[s];
/* 562 */     int prev = (int)(links >>> 32);
/* 563 */     int next = (int)links;
/* 564 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 565 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 566 */     this.link[d] = links;
/*     */   }
/*     */ 
/*     */   public K first()
/*     */   {
/* 573 */     if (this.size == 0) throw new NoSuchElementException();
/* 574 */     return this.key[this.first];
/*     */   }
/*     */ 
/*     */   public K last()
/*     */   {
/* 581 */     if (this.size == 0) throw new NoSuchElementException();
/* 582 */     return this.key[this.last];
/*     */   }
/* 584 */   public ObjectSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/* 585 */   public ObjectSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/* 586 */   public ObjectSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); } 
/* 587 */   public Comparator<? super K> comparator() { return null; }
/*     */ 
/*     */ 
/*     */   public ObjectListIterator<K> iterator(K from)
/*     */   {
/* 721 */     return new SetIterator(from);
/*     */   }
/*     */   public ObjectListIterator<K> iterator() {
/* 724 */     return new SetIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 738 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 753 */     int l = HashCommon.arraySize(this.size, this.f);
/* 754 */     if (l >= this.n) return true; try
/*     */     {
/* 756 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 758 */       return false;
/* 759 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 780 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 781 */     if (this.n <= l) return true; try
/*     */     {
/* 783 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 785 */       return false;
/* 786 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 799 */     int i = this.first; int prev = -1; int newPrev = -1;
/*     */ 
/* 801 */     Object[] key = this.key;
/* 802 */     int newMask = newN - 1;
/* 803 */     Object[] newKey = (Object[])new Object[newN];
/* 804 */     boolean[] newUsed = new boolean[newN];
/* 805 */     long[] link = this.link;
/* 806 */     long[] newLink = new long[newN];
/* 807 */     this.first = -1;
/* 808 */     for (int j = this.size; j-- != 0; ) {
/* 809 */       Object k = key[i];
/* 810 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
/* 811 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 812 */       newUsed[pos] = true;
/* 813 */       newKey[pos] = k;
/* 814 */       if (prev != -1) {
/* 815 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 816 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 817 */         newPrev = pos;
/*     */       }
/*     */       else {
/* 820 */         newPrev = this.first = pos;
/*     */ 
/* 822 */         newLink[pos] = -1L;
/*     */       }
/* 824 */       int t = i;
/* 825 */       i = (int)link[i];
/* 826 */       prev = t;
/*     */     }
/* 828 */     this.n = newN;
/* 829 */     this.mask = newMask;
/* 830 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 831 */     this.key = newKey;
/* 832 */     this.used = newUsed;
/* 833 */     this.link = newLink;
/* 834 */     this.last = newPrev;
/* 835 */     if (newPrev != -1)
/*     */     {
/* 837 */       newLink[newPrev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   public ObjectLinkedOpenHashSet<K> clone()
/*     */   {
/*     */     ObjectLinkedOpenHashSet c;
/*     */     try
/*     */     {
/* 850 */       c = (ObjectLinkedOpenHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 853 */       throw new InternalError();
/*     */     }
/* 855 */     c.key = ((Object[])this.key.clone());
/* 856 */     c.used = ((boolean[])this.used.clone());
/* 857 */     c.link = ((long[])this.link.clone());
/* 858 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 870 */     int h = 0; int i = 0; int j = this.size;
/* 871 */     while (j-- != 0) {
/* 872 */       while (this.used[i] == 0) i++;
/* 873 */       if (this != this.key[i])
/* 874 */         h += (this.key[i] == null ? 0 : this.key[i].hashCode());
/* 875 */       i++;
/*     */     }
/* 877 */     return h; } 
/* 880 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 881 */     s.defaultWriteObject();
/* 882 */     for (int j = this.size; j-- != 0; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 886 */     s.defaultReadObject();
/* 887 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 888 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 889 */     this.mask = (this.n - 1);
/* 890 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 891 */     boolean[] used = this.used = new boolean[this.n];
/* 892 */     long[] link = this.link = new long[this.n];
/* 893 */     int prev = -1;
/* 894 */     this.first = (this.last = -1);
/*     */ 
/* 896 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 897 */       Object k = s.readObject();
/* 898 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 899 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 900 */       used[pos] = true;
/* 901 */       key[pos] = k;
/* 902 */       if (this.first != -1) {
/* 903 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 904 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 905 */         prev = pos;
/*     */       }
/*     */       else {
/* 908 */         prev = this.first = pos;
/*     */ 
/* 910 */         link[pos] |= -4294967296L;
/*     */       }
/*     */     }
/* 913 */     this.last = prev;
/* 914 */     if (prev != -1)
/*     */     {
/* 916 */       link[prev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractObjectListIterator<K>
/*     */   {
/* 596 */     int prev = -1;
/*     */ 
/* 598 */     int next = -1;
/*     */ 
/* 600 */     int curr = -1;
/*     */ 
/* 602 */     int index = -1;
/*     */ 
/* 604 */     SetIterator() { this.next = ObjectLinkedOpenHashSet.this.first;
/* 605 */       this.index = 0; }
/*     */ 
/*     */     SetIterator() {
/* 608 */       if (ObjectLinkedOpenHashSet.this.key[ObjectLinkedOpenHashSet.this.last] == null ? from == null : ObjectLinkedOpenHashSet.this.key[ObjectLinkedOpenHashSet.this.last].equals(from)) {
/* 609 */         this.prev = ObjectLinkedOpenHashSet.this.last;
/* 610 */         this.index = ObjectLinkedOpenHashSet.this.size;
/*     */       }
/*     */       else
/*     */       {
/* 614 */         int pos = (from == null ? 142593372 : HashCommon.murmurHash3(from.hashCode())) & ObjectLinkedOpenHashSet.this.mask;
/*     */ 
/* 616 */         while (ObjectLinkedOpenHashSet.this.used[pos] != 0) {
/* 617 */           if (ObjectLinkedOpenHashSet.this.key[pos] == null ? from == null : ObjectLinkedOpenHashSet.this.key[pos].equals(from))
/*     */           {
/* 619 */             this.next = ((int)ObjectLinkedOpenHashSet.this.link[pos]);
/* 620 */             this.prev = pos;
/* 621 */             return;
/*     */           }
/* 623 */           pos = pos + 1 & ObjectLinkedOpenHashSet.this.mask;
/*     */         }
/* 625 */         throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/*     */       }
/*     */     }
/* 628 */     public boolean hasNext() { return this.next != -1; } 
/* 629 */     public boolean hasPrevious() { return this.prev != -1; } 
/*     */     public K next() {
/* 631 */       if (!hasNext()) throw new NoSuchElementException();
/* 632 */       this.curr = this.next;
/* 633 */       this.next = ((int)ObjectLinkedOpenHashSet.this.link[this.curr]);
/* 634 */       this.prev = this.curr;
/* 635 */       if (this.index >= 0) this.index += 1;
/*     */ 
/* 637 */       return ObjectLinkedOpenHashSet.this.key[this.curr];
/*     */     }
/*     */     public K previous() {
/* 640 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 641 */       this.curr = this.prev;
/* 642 */       this.prev = ((int)(ObjectLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 643 */       this.next = this.curr;
/* 644 */       if (this.index >= 0) this.index -= 1;
/* 645 */       return ObjectLinkedOpenHashSet.this.key[this.curr];
/*     */     }
/*     */     private final void ensureIndexKnown() {
/* 648 */       if (this.index >= 0) return;
/* 649 */       if (this.prev == -1) {
/* 650 */         this.index = 0;
/* 651 */         return;
/*     */       }
/* 653 */       if (this.next == -1) {
/* 654 */         this.index = ObjectLinkedOpenHashSet.this.size;
/* 655 */         return;
/*     */       }
/* 657 */       int pos = ObjectLinkedOpenHashSet.this.first;
/* 658 */       this.index = 1;
/* 659 */       while (pos != this.prev) {
/* 660 */         pos = (int)ObjectLinkedOpenHashSet.this.link[pos];
/* 661 */         this.index += 1;
/*     */       }
/*     */     }
/*     */ 
/* 665 */     public int nextIndex() { ensureIndexKnown();
/* 666 */       return this.index; }
/*     */ 
/*     */     public int previousIndex() {
/* 669 */       ensureIndexKnown();
/* 670 */       return this.index - 1;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 674 */       ensureIndexKnown();
/* 675 */       if (this.curr == -1) throw new IllegalStateException();
/* 676 */       if (this.curr == this.prev)
/*     */       {
/* 679 */         this.index -= 1;
/* 680 */         this.prev = ((int)(ObjectLinkedOpenHashSet.this.link[this.curr] >>> 32));
/*     */       }
/*     */       else {
/* 683 */         this.next = ((int)ObjectLinkedOpenHashSet.this.link[this.curr]);
/* 684 */       }ObjectLinkedOpenHashSet.this.size -= 1;
/*     */ 
/* 687 */       if (this.prev == -1) ObjectLinkedOpenHashSet.this.first = this.next;
/*     */       else
/* 689 */         ObjectLinkedOpenHashSet.this.link[this.prev] ^= (ObjectLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 690 */       if (this.next == -1) ObjectLinkedOpenHashSet.this.last = this.prev;
/*     */       else
/* 692 */         ObjectLinkedOpenHashSet.this.link[this.next] ^= (ObjectLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*     */       int last;
/*     */       while (true) {
/* 696 */         pos = (last = pos) + 1 & ObjectLinkedOpenHashSet.this.mask;
/* 697 */         while (ObjectLinkedOpenHashSet.this.used[pos] != 0) {
/* 698 */           int slot = (ObjectLinkedOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(ObjectLinkedOpenHashSet.this.key[pos].hashCode())) & ObjectLinkedOpenHashSet.this.mask;
/* 699 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 700 */           pos = pos + 1 & ObjectLinkedOpenHashSet.this.mask;
/*     */         }
/* 702 */         if (ObjectLinkedOpenHashSet.this.used[pos] == 0) break;
/* 703 */         ObjectLinkedOpenHashSet.this.key[last] = ObjectLinkedOpenHashSet.this.key[pos];
/* 704 */         if (this.next == pos) this.next = last;
/* 705 */         if (this.prev == pos) this.prev = last;
/* 706 */         ObjectLinkedOpenHashSet.this.fixPointers(pos, last);
/*     */       }
/* 708 */       ObjectLinkedOpenHashSet.this.used[last] = false;
/* 709 */       ObjectLinkedOpenHashSet.this.key[last] = null;
/* 710 */       this.curr = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet
 * JD-Core Version:    0.6.2
 */