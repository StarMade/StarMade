/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class IntLinkedOpenHashSet extends AbstractIntSortedSet
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient int[] key;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/* 102 */   protected transient int first = -1;
/*     */ 
/* 104 */   protected transient int last = -1;
/*     */   protected transient long[] link;
/*     */ 
/*     */   public IntLinkedOpenHashSet(int expected, float f)
/*     */   {
/* 119 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 120 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 121 */     this.f = f;
/* 122 */     this.n = HashCommon.arraySize(expected, f);
/* 123 */     this.mask = (this.n - 1);
/* 124 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 125 */     this.key = new int[this.n];
/* 126 */     this.used = new boolean[this.n];
/* 127 */     this.link = new long[this.n];
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(int expected)
/*     */   {
/* 134 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet()
/*     */   {
/* 140 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(Collection<? extends Integer> c, float f)
/*     */   {
/* 148 */     this(c.size(), f);
/* 149 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(Collection<? extends Integer> c)
/*     */   {
/* 157 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(IntCollection c, float f)
/*     */   {
/* 165 */     this(c.size(), f);
/* 166 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(IntCollection c)
/*     */   {
/* 174 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(IntIterator i, float f)
/*     */   {
/* 182 */     this(16, f);
/* 183 */     while (i.hasNext()) add(i.nextInt());
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(IntIterator i)
/*     */   {
/* 190 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(Iterator<?> i, float f)
/*     */   {
/* 198 */     this(IntIterators.asIntIterator(i), f);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(Iterator<?> i)
/*     */   {
/* 205 */     this(IntIterators.asIntIterator(i));
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(int[] a, int offset, int length, float f)
/*     */   {
/* 215 */     this(length < 0 ? 0 : length, f);
/* 216 */     IntArrays.ensureOffsetLength(a, offset, length);
/* 217 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(int[] a, int offset, int length)
/*     */   {
/* 226 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(int[] a, float f)
/*     */   {
/* 234 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet(int[] a)
/*     */   {
/* 242 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(int k)
/*     */   {
/* 250 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 252 */     while (this.used[pos] != 0) {
/* 253 */       if (this.key[pos] == k) return false;
/* 254 */       pos = pos + 1 & this.mask;
/*     */     }
/* 256 */     this.used[pos] = true;
/* 257 */     this.key[pos] = k;
/* 258 */     if (this.size == 0) {
/* 259 */       this.first = (this.last = pos);
/*     */ 
/* 261 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 264 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 265 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 266 */       this.last = pos;
/*     */     }
/* 268 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 270 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 282 */       pos = (last = pos) + 1 & this.mask;
/* 283 */       while (this.used[pos] != 0) {
/* 284 */         int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 285 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 286 */         pos = pos + 1 & this.mask;
/*     */       }
/* 288 */       if (this.used[pos] == 0) break;
/* 289 */       this.key[last] = this.key[pos];
/* 290 */       fixPointers(pos, last);
/*     */     }
/* 292 */     this.used[last] = false;
/* 293 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(int k)
/*     */   {
/* 298 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 300 */     while (this.used[pos] != 0) {
/* 301 */       if (this.key[pos] == k) {
/* 302 */         this.size -= 1;
/* 303 */         fixPointers(pos);
/* 304 */         shiftKeys(pos);
/*     */ 
/* 306 */         return true;
/*     */       }
/* 308 */       pos = pos + 1 & this.mask;
/*     */     }
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(int k)
/*     */   {
/* 315 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 317 */     while (this.used[pos] != 0) {
/* 318 */       if (this.key[pos] == k) return true;
/* 319 */       pos = pos + 1 & this.mask;
/*     */     }
/* 321 */     return false;
/*     */   }
/*     */ 
/*     */   public int removeFirstInt()
/*     */   {
/* 328 */     if (this.size == 0) throw new NoSuchElementException();
/* 329 */     this.size -= 1;
/* 330 */     int pos = this.first;
/*     */ 
/* 332 */     this.first = ((int)this.link[pos]);
/* 333 */     if (0 <= this.first)
/*     */     {
/* 335 */       this.link[this.first] |= -4294967296L;
/*     */     }
/* 337 */     int k = this.key[pos];
/* 338 */     shiftKeys(pos);
/* 339 */     return k;
/*     */   }
/*     */ 
/*     */   public int removeLastInt()
/*     */   {
/* 346 */     if (this.size == 0) throw new NoSuchElementException();
/* 347 */     this.size -= 1;
/* 348 */     int pos = this.last;
/*     */ 
/* 350 */     this.last = ((int)(this.link[pos] >>> 32));
/* 351 */     if (0 <= this.last)
/*     */     {
/* 353 */       this.link[this.last] |= 4294967295L;
/*     */     }
/* 355 */     int k = this.key[pos];
/* 356 */     shiftKeys(pos);
/* 357 */     return k;
/*     */   }
/*     */   private void moveIndexToFirst(int i) {
/* 360 */     if ((this.size == 1) || (this.first == i)) return;
/* 361 */     if (this.last == i) {
/* 362 */       this.last = ((int)(this.link[i] >>> 32));
/*     */ 
/* 364 */       this.link[this.last] |= 4294967295L;
/*     */     }
/*     */     else {
/* 367 */       long linki = this.link[i];
/* 368 */       int prev = (int)(linki >>> 32);
/* 369 */       int next = (int)linki;
/* 370 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 371 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 373 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 374 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 375 */     this.first = i;
/*     */   }
/*     */   private void moveIndexToLast(int i) {
/* 378 */     if ((this.size == 1) || (this.last == i)) return;
/* 379 */     if (this.first == i) {
/* 380 */       this.first = ((int)this.link[i]);
/*     */ 
/* 382 */       this.link[this.first] |= -4294967296L;
/*     */     }
/*     */     else {
/* 385 */       long linki = this.link[i];
/* 386 */       int prev = (int)(linki >>> 32);
/* 387 */       int next = (int)linki;
/* 388 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 389 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 391 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 392 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 393 */     this.last = i;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToFirst(int k)
/*     */   {
/* 401 */     int[] key = this.key;
/* 402 */     boolean[] used = this.used;
/* 403 */     int mask = this.mask;
/*     */ 
/* 405 */     int pos = HashCommon.murmurHash3(k) & mask;
/*     */ 
/* 407 */     while (used[pos] != 0) {
/* 408 */       if (k == key[pos]) {
/* 409 */         moveIndexToFirst(pos);
/* 410 */         return false;
/*     */       }
/* 412 */       pos = pos + 1 & mask;
/*     */     }
/* 414 */     used[pos] = true;
/* 415 */     key[pos] = k;
/* 416 */     if (this.size == 0) {
/* 417 */       this.first = (this.last = pos);
/*     */ 
/* 419 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 422 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 423 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 424 */       this.first = pos;
/*     */     }
/* 426 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 428 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToLast(int k)
/*     */   {
/* 436 */     int[] key = this.key;
/* 437 */     boolean[] used = this.used;
/* 438 */     int mask = this.mask;
/*     */ 
/* 440 */     int pos = HashCommon.murmurHash3(k) & mask;
/*     */ 
/* 442 */     while (used[pos] != 0) {
/* 443 */       if (k == key[pos]) {
/* 444 */         moveIndexToLast(pos);
/* 445 */         return false;
/*     */       }
/* 447 */       pos = pos + 1 & mask;
/*     */     }
/* 449 */     used[pos] = true;
/* 450 */     key[pos] = k;
/* 451 */     if (this.size == 0) {
/* 452 */       this.first = (this.last = pos);
/*     */ 
/* 454 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 457 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 458 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 459 */       this.last = pos;
/*     */     }
/* 461 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 463 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 472 */     if (this.size == 0) return;
/* 473 */     this.size = 0;
/* 474 */     BooleanArrays.fill(this.used, false);
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
/*     */   public int firstInt()
/*     */   {
/* 573 */     if (this.size == 0) throw new NoSuchElementException();
/* 574 */     return this.key[this.first];
/*     */   }
/*     */ 
/*     */   public int lastInt()
/*     */   {
/* 581 */     if (this.size == 0) throw new NoSuchElementException();
/* 582 */     return this.key[this.last];
/*     */   }
/* 584 */   public IntSortedSet tailSet(int from) { throw new UnsupportedOperationException(); } 
/* 585 */   public IntSortedSet headSet(int to) { throw new UnsupportedOperationException(); } 
/* 586 */   public IntSortedSet subSet(int from, int to) { throw new UnsupportedOperationException(); } 
/* 587 */   public IntComparator comparator() { return null; }
/*     */ 
/*     */ 
/*     */   public IntListIterator iterator(int from)
/*     */   {
/* 720 */     return new SetIterator(from);
/*     */   }
/*     */   public IntListIterator iterator() {
/* 723 */     return new SetIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 737 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 752 */     int l = HashCommon.arraySize(this.size, this.f);
/* 753 */     if (l >= this.n) return true; try
/*     */     {
/* 755 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 757 */       return false;
/* 758 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 779 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 780 */     if (this.n <= l) return true; try
/*     */     {
/* 782 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 784 */       return false;
/* 785 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 798 */     int i = this.first; int prev = -1; int newPrev = -1;
/*     */ 
/* 800 */     int[] key = this.key;
/* 801 */     int newMask = newN - 1;
/* 802 */     int[] newKey = new int[newN];
/* 803 */     boolean[] newUsed = new boolean[newN];
/* 804 */     long[] link = this.link;
/* 805 */     long[] newLink = new long[newN];
/* 806 */     this.first = -1;
/* 807 */     for (int j = this.size; j-- != 0; ) {
/* 808 */       int k = key[i];
/* 809 */       int pos = HashCommon.murmurHash3(k) & newMask;
/* 810 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 811 */       newUsed[pos] = true;
/* 812 */       newKey[pos] = k;
/* 813 */       if (prev != -1) {
/* 814 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 815 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 816 */         newPrev = pos;
/*     */       }
/*     */       else {
/* 819 */         newPrev = this.first = pos;
/*     */ 
/* 821 */         newLink[pos] = -1L;
/*     */       }
/* 823 */       int t = i;
/* 824 */       i = (int)link[i];
/* 825 */       prev = t;
/*     */     }
/* 827 */     this.n = newN;
/* 828 */     this.mask = newMask;
/* 829 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 830 */     this.key = newKey;
/* 831 */     this.used = newUsed;
/* 832 */     this.link = newLink;
/* 833 */     this.last = newPrev;
/* 834 */     if (newPrev != -1)
/*     */     {
/* 836 */       newLink[newPrev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   public IntLinkedOpenHashSet clone()
/*     */   {
/*     */     IntLinkedOpenHashSet c;
/*     */     try
/*     */     {
/* 849 */       c = (IntLinkedOpenHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 852 */       throw new InternalError();
/*     */     }
/* 854 */     c.key = ((int[])this.key.clone());
/* 855 */     c.used = ((boolean[])this.used.clone());
/* 856 */     c.link = ((long[])this.link.clone());
/* 857 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 869 */     int h = 0; int i = 0; int j = this.size;
/* 870 */     while (j-- != 0) {
/* 871 */       while (this.used[i] == 0) i++;
/* 872 */       h += this.key[i];
/* 873 */       i++;
/*     */     }
/* 875 */     return h; } 
/* 878 */   private void writeObject(ObjectOutputStream s) throws IOException { IntIterator i = iterator();
/* 879 */     s.defaultWriteObject();
/* 880 */     for (int j = this.size; j-- != 0; s.writeInt(i.nextInt()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 884 */     s.defaultReadObject();
/* 885 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 886 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 887 */     this.mask = (this.n - 1);
/* 888 */     int[] key = this.key = new int[this.n];
/* 889 */     boolean[] used = this.used = new boolean[this.n];
/* 890 */     long[] link = this.link = new long[this.n];
/* 891 */     int prev = -1;
/* 892 */     this.first = (this.last = -1);
/*     */ 
/* 894 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 895 */       int k = s.readInt();
/* 896 */       pos = HashCommon.murmurHash3(k) & this.mask;
/* 897 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 898 */       used[pos] = true;
/* 899 */       key[pos] = k;
/* 900 */       if (this.first != -1) {
/* 901 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 902 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 903 */         prev = pos;
/*     */       }
/*     */       else {
/* 906 */         prev = this.first = pos;
/*     */ 
/* 908 */         link[pos] |= -4294967296L;
/*     */       }
/*     */     }
/* 911 */     this.last = prev;
/* 912 */     if (prev != -1)
/*     */     {
/* 914 */       link[prev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractIntListIterator
/*     */   {
/* 596 */     int prev = -1;
/*     */ 
/* 598 */     int next = -1;
/*     */ 
/* 600 */     int curr = -1;
/*     */ 
/* 602 */     int index = -1;
/*     */ 
/* 604 */     SetIterator() { this.next = IntLinkedOpenHashSet.this.first;
/* 605 */       this.index = 0; }
/*     */ 
/*     */     SetIterator(int from) {
/* 608 */       if (IntLinkedOpenHashSet.this.key[IntLinkedOpenHashSet.this.last] == from) {
/* 609 */         this.prev = IntLinkedOpenHashSet.this.last;
/* 610 */         this.index = IntLinkedOpenHashSet.this.size;
/*     */       }
/*     */       else
/*     */       {
/* 614 */         int pos = HashCommon.murmurHash3(from) & IntLinkedOpenHashSet.this.mask;
/*     */ 
/* 616 */         while (IntLinkedOpenHashSet.this.used[pos] != 0) {
/* 617 */           if (IntLinkedOpenHashSet.this.key[pos] == from)
/*     */           {
/* 619 */             this.next = ((int)IntLinkedOpenHashSet.this.link[pos]);
/* 620 */             this.prev = pos;
/* 621 */             return;
/*     */           }
/* 623 */           pos = pos + 1 & IntLinkedOpenHashSet.this.mask;
/*     */         }
/* 625 */         throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/*     */       }
/*     */     }
/* 628 */     public boolean hasNext() { return this.next != -1; } 
/* 629 */     public boolean hasPrevious() { return this.prev != -1; } 
/*     */     public int nextInt() {
/* 631 */       if (!hasNext()) throw new NoSuchElementException();
/* 632 */       this.curr = this.next;
/* 633 */       this.next = ((int)IntLinkedOpenHashSet.this.link[this.curr]);
/* 634 */       this.prev = this.curr;
/* 635 */       if (this.index >= 0) this.index += 1;
/*     */ 
/* 637 */       return IntLinkedOpenHashSet.this.key[this.curr];
/*     */     }
/*     */     public int previousInt() {
/* 640 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 641 */       this.curr = this.prev;
/* 642 */       this.prev = ((int)(IntLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 643 */       this.next = this.curr;
/* 644 */       if (this.index >= 0) this.index -= 1;
/* 645 */       return IntLinkedOpenHashSet.this.key[this.curr];
/*     */     }
/*     */     private final void ensureIndexKnown() {
/* 648 */       if (this.index >= 0) return;
/* 649 */       if (this.prev == -1) {
/* 650 */         this.index = 0;
/* 651 */         return;
/*     */       }
/* 653 */       if (this.next == -1) {
/* 654 */         this.index = IntLinkedOpenHashSet.this.size;
/* 655 */         return;
/*     */       }
/* 657 */       int pos = IntLinkedOpenHashSet.this.first;
/* 658 */       this.index = 1;
/* 659 */       while (pos != this.prev) {
/* 660 */         pos = (int)IntLinkedOpenHashSet.this.link[pos];
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
/* 680 */         this.prev = ((int)(IntLinkedOpenHashSet.this.link[this.curr] >>> 32));
/*     */       }
/*     */       else {
/* 683 */         this.next = ((int)IntLinkedOpenHashSet.this.link[this.curr]);
/* 684 */       }IntLinkedOpenHashSet.this.size -= 1;
/*     */ 
/* 687 */       if (this.prev == -1) IntLinkedOpenHashSet.this.first = this.next;
/*     */       else
/* 689 */         IntLinkedOpenHashSet.this.link[this.prev] ^= (IntLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 690 */       if (this.next == -1) IntLinkedOpenHashSet.this.last = this.prev;
/*     */       else
/* 692 */         IntLinkedOpenHashSet.this.link[this.next] ^= (IntLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*     */       int last;
/*     */       while (true) {
/* 696 */         pos = (last = pos) + 1 & IntLinkedOpenHashSet.this.mask;
/* 697 */         while (IntLinkedOpenHashSet.this.used[pos] != 0) {
/* 698 */           int slot = HashCommon.murmurHash3(IntLinkedOpenHashSet.this.key[pos]) & IntLinkedOpenHashSet.this.mask;
/* 699 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 700 */           pos = pos + 1 & IntLinkedOpenHashSet.this.mask;
/*     */         }
/* 702 */         if (IntLinkedOpenHashSet.this.used[pos] == 0) break;
/* 703 */         IntLinkedOpenHashSet.this.key[last] = IntLinkedOpenHashSet.this.key[pos];
/* 704 */         if (this.next == pos) this.next = last;
/* 705 */         if (this.prev == pos) this.prev = last;
/* 706 */         IntLinkedOpenHashSet.this.fixPointers(pos, last);
/*     */       }
/* 708 */       IntLinkedOpenHashSet.this.used[last] = false;
/* 709 */       this.curr = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet
 * JD-Core Version:    0.6.2
 */