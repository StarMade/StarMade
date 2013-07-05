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
/*     */ public class ReferenceLinkedOpenHashSet<K> extends AbstractReferenceSortedSet<K>
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
/*     */   public ReferenceLinkedOpenHashSet(int expected, float f)
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
/*     */   public ReferenceLinkedOpenHashSet(int expected)
/*     */   {
/* 133 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet()
/*     */   {
/* 139 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(Collection<? extends K> c, float f)
/*     */   {
/* 147 */     this(c.size(), f);
/* 148 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(Collection<? extends K> c)
/*     */   {
/* 156 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(ReferenceCollection<? extends K> c, float f)
/*     */   {
/* 164 */     this(c.size(), f);
/* 165 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(ReferenceCollection<? extends K> c)
/*     */   {
/* 173 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(ObjectIterator<K> i, float f)
/*     */   {
/* 181 */     this(16, f);
/* 182 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(ObjectIterator<K> i)
/*     */   {
/* 189 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(K[] a, int offset, int length, float f)
/*     */   {
/* 199 */     this(length < 0 ? 0 : length, f);
/* 200 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 201 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(K[] a, int offset, int length)
/*     */   {
/* 210 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(K[] a, float f)
/*     */   {
/* 218 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet(K[] a)
/*     */   {
/* 226 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(K k)
/*     */   {
/* 234 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 236 */     while (this.used[pos] != 0) {
/* 237 */       if (this.key[pos] == k) return false;
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
/* 268 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
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
/* 283 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 285 */     while (this.used[pos] != 0) {
/* 286 */       if (this.key[pos] == k) {
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
/* 300 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 302 */     while (this.used[pos] != 0) {
/* 303 */       if (this.key[pos] == k) return true;
/* 304 */       pos = pos + 1 & this.mask;
/*     */     }
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */   public K removeFirst()
/*     */   {
/* 313 */     if (this.size == 0) throw new NoSuchElementException();
/* 314 */     this.size -= 1;
/* 315 */     int pos = this.first;
/*     */ 
/* 317 */     this.first = ((int)this.link[pos]);
/* 318 */     if (0 <= this.first)
/*     */     {
/* 320 */       this.link[this.first] |= -4294967296L;
/*     */     }
/* 322 */     Object k = this.key[pos];
/* 323 */     shiftKeys(pos);
/* 324 */     return k;
/*     */   }
/*     */ 
/*     */   public K removeLast()
/*     */   {
/* 331 */     if (this.size == 0) throw new NoSuchElementException();
/* 332 */     this.size -= 1;
/* 333 */     int pos = this.last;
/*     */ 
/* 335 */     this.last = ((int)(this.link[pos] >>> 32));
/* 336 */     if (0 <= this.last)
/*     */     {
/* 338 */       this.link[this.last] |= 4294967295L;
/*     */     }
/* 340 */     Object k = this.key[pos];
/* 341 */     shiftKeys(pos);
/* 342 */     return k;
/*     */   }
/*     */   private void moveIndexToFirst(int i) {
/* 345 */     if ((this.size == 1) || (this.first == i)) return;
/* 346 */     if (this.last == i) {
/* 347 */       this.last = ((int)(this.link[i] >>> 32));
/*     */ 
/* 349 */       this.link[this.last] |= 4294967295L;
/*     */     }
/*     */     else {
/* 352 */       long linki = this.link[i];
/* 353 */       int prev = (int)(linki >>> 32);
/* 354 */       int next = (int)linki;
/* 355 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 356 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 358 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 359 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 360 */     this.first = i;
/*     */   }
/*     */   private void moveIndexToLast(int i) {
/* 363 */     if ((this.size == 1) || (this.last == i)) return;
/* 364 */     if (this.first == i) {
/* 365 */       this.first = ((int)this.link[i]);
/*     */ 
/* 367 */       this.link[this.first] |= -4294967296L;
/*     */     }
/*     */     else {
/* 370 */       long linki = this.link[i];
/* 371 */       int prev = (int)(linki >>> 32);
/* 372 */       int next = (int)linki;
/* 373 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 374 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 376 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 377 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 378 */     this.last = i;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToFirst(K k)
/*     */   {
/* 386 */     Object[] key = this.key;
/* 387 */     boolean[] used = this.used;
/* 388 */     int mask = this.mask;
/*     */ 
/* 390 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*     */ 
/* 392 */     while (used[pos] != 0) {
/* 393 */       if (k == key[pos]) {
/* 394 */         moveIndexToFirst(pos);
/* 395 */         return false;
/*     */       }
/* 397 */       pos = pos + 1 & mask;
/*     */     }
/* 399 */     used[pos] = true;
/* 400 */     key[pos] = k;
/* 401 */     if (this.size == 0) {
/* 402 */       this.first = (this.last = pos);
/*     */ 
/* 404 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 407 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 408 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 409 */       this.first = pos;
/*     */     }
/* 411 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 413 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToLast(K k)
/*     */   {
/* 421 */     Object[] key = this.key;
/* 422 */     boolean[] used = this.used;
/* 423 */     int mask = this.mask;
/*     */ 
/* 425 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & mask;
/*     */ 
/* 427 */     while (used[pos] != 0) {
/* 428 */       if (k == key[pos]) {
/* 429 */         moveIndexToLast(pos);
/* 430 */         return false;
/*     */       }
/* 432 */       pos = pos + 1 & mask;
/*     */     }
/* 434 */     used[pos] = true;
/* 435 */     key[pos] = k;
/* 436 */     if (this.size == 0) {
/* 437 */       this.first = (this.last = pos);
/*     */ 
/* 439 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 442 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 443 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 444 */       this.last = pos;
/*     */     }
/* 446 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 448 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 457 */     if (this.size == 0) return;
/* 458 */     this.size = 0;
/* 459 */     BooleanArrays.fill(this.used, false);
/* 460 */     ObjectArrays.fill(this.key, null);
/* 461 */     this.first = (this.last = -1);
/*     */   }
/*     */   public int size() {
/* 464 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 467 */     return this.size == 0;
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
/* 484 */     return 16;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int i)
/*     */   {
/* 494 */     if (this.size == 0) {
/* 495 */       this.first = (this.last = -1);
/* 496 */       return;
/*     */     }
/* 498 */     if (this.first == i) {
/* 499 */       this.first = ((int)this.link[i]);
/* 500 */       if (0 <= this.first)
/*     */       {
/* 502 */         this.link[this.first] |= -4294967296L;
/*     */       }
/* 504 */       return;
/*     */     }
/* 506 */     if (this.last == i) {
/* 507 */       this.last = ((int)(this.link[i] >>> 32));
/* 508 */       if (0 <= this.last)
/*     */       {
/* 510 */         this.link[this.last] |= 4294967295L;
/*     */       }
/* 512 */       return;
/*     */     }
/* 514 */     long linki = this.link[i];
/* 515 */     int prev = (int)(linki >>> 32);
/* 516 */     int next = (int)linki;
/* 517 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 518 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int s, int d)
/*     */   {
/* 529 */     if (this.size == 1) {
/* 530 */       this.first = (this.last = d);
/*     */ 
/* 532 */       this.link[d] = -1L;
/* 533 */       return;
/*     */     }
/* 535 */     if (this.first == s) {
/* 536 */       this.first = d;
/* 537 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 538 */       this.link[d] = this.link[s];
/* 539 */       return;
/*     */     }
/* 541 */     if (this.last == s) {
/* 542 */       this.last = d;
/* 543 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 544 */       this.link[d] = this.link[s];
/* 545 */       return;
/*     */     }
/* 547 */     long links = this.link[s];
/* 548 */     int prev = (int)(links >>> 32);
/* 549 */     int next = (int)links;
/* 550 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 551 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 552 */     this.link[d] = links;
/*     */   }
/*     */ 
/*     */   public K first()
/*     */   {
/* 559 */     if (this.size == 0) throw new NoSuchElementException();
/* 560 */     return this.key[this.first];
/*     */   }
/*     */ 
/*     */   public K last()
/*     */   {
/* 567 */     if (this.size == 0) throw new NoSuchElementException();
/* 568 */     return this.key[this.last];
/*     */   }
/* 570 */   public ReferenceSortedSet<K> tailSet(K from) { throw new UnsupportedOperationException(); } 
/* 571 */   public ReferenceSortedSet<K> headSet(K to) { throw new UnsupportedOperationException(); } 
/* 572 */   public ReferenceSortedSet<K> subSet(K from, K to) { throw new UnsupportedOperationException(); } 
/* 573 */   public Comparator<? super K> comparator() { return null; }
/*     */ 
/*     */ 
/*     */   public ObjectListIterator<K> iterator(K from)
/*     */   {
/* 707 */     return new SetIterator(from);
/*     */   }
/*     */   public ObjectListIterator<K> iterator() {
/* 710 */     return new SetIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 724 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 739 */     int l = HashCommon.arraySize(this.size, this.f);
/* 740 */     if (l >= this.n) return true; try
/*     */     {
/* 742 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 744 */       return false;
/* 745 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 766 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 767 */     if (this.n <= l) return true; try
/*     */     {
/* 769 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 771 */       return false;
/* 772 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 785 */     int i = this.first; int prev = -1; int newPrev = -1;
/*     */ 
/* 787 */     Object[] key = this.key;
/* 788 */     int newMask = newN - 1;
/* 789 */     Object[] newKey = (Object[])new Object[newN];
/* 790 */     boolean[] newUsed = new boolean[newN];
/* 791 */     long[] link = this.link;
/* 792 */     long[] newLink = new long[newN];
/* 793 */     this.first = -1;
/* 794 */     for (int j = this.size; j-- != 0; ) {
/* 795 */       Object k = key[i];
/* 796 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 797 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 798 */       newUsed[pos] = true;
/* 799 */       newKey[pos] = k;
/* 800 */       if (prev != -1) {
/* 801 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 802 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 803 */         newPrev = pos;
/*     */       }
/*     */       else {
/* 806 */         newPrev = this.first = pos;
/*     */ 
/* 808 */         newLink[pos] = -1L;
/*     */       }
/* 810 */       int t = i;
/* 811 */       i = (int)link[i];
/* 812 */       prev = t;
/*     */     }
/* 814 */     this.n = newN;
/* 815 */     this.mask = newMask;
/* 816 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 817 */     this.key = newKey;
/* 818 */     this.used = newUsed;
/* 819 */     this.link = newLink;
/* 820 */     this.last = newPrev;
/* 821 */     if (newPrev != -1)
/*     */     {
/* 823 */       newLink[newPrev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   public ReferenceLinkedOpenHashSet<K> clone()
/*     */   {
/*     */     ReferenceLinkedOpenHashSet c;
/*     */     try
/*     */     {
/* 836 */       c = (ReferenceLinkedOpenHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 839 */       throw new InternalError();
/*     */     }
/* 841 */     c.key = ((Object[])this.key.clone());
/* 842 */     c.used = ((boolean[])this.used.clone());
/* 843 */     c.link = ((long[])this.link.clone());
/* 844 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 856 */     int h = 0; int i = 0; int j = this.size;
/* 857 */     while (j-- != 0) {
/* 858 */       while (this.used[i] == 0) i++;
/* 859 */       if (this != this.key[i])
/* 860 */         h += (this.key[i] == null ? 0 : System.identityHashCode(this.key[i]));
/* 861 */       i++;
/*     */     }
/* 863 */     return h; } 
/* 866 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 867 */     s.defaultWriteObject();
/* 868 */     for (int j = this.size; j-- != 0; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 872 */     s.defaultReadObject();
/* 873 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 874 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 875 */     this.mask = (this.n - 1);
/* 876 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 877 */     boolean[] used = this.used = new boolean[this.n];
/* 878 */     long[] link = this.link = new long[this.n];
/* 879 */     int prev = -1;
/* 880 */     this.first = (this.last = -1);
/*     */ 
/* 882 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 883 */       Object k = s.readObject();
/* 884 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 885 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 886 */       used[pos] = true;
/* 887 */       key[pos] = k;
/* 888 */       if (this.first != -1) {
/* 889 */         link[prev] ^= (link[prev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 890 */         link[pos] ^= (link[pos] ^ (prev & 0xFFFFFFFF) << 32) & 0x0;
/* 891 */         prev = pos;
/*     */       }
/*     */       else {
/* 894 */         prev = this.first = pos;
/*     */ 
/* 896 */         link[pos] |= -4294967296L;
/*     */       }
/*     */     }
/* 899 */     this.last = prev;
/* 900 */     if (prev != -1)
/*     */     {
/* 902 */       link[prev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractObjectListIterator<K>
/*     */   {
/* 582 */     int prev = -1;
/*     */ 
/* 584 */     int next = -1;
/*     */ 
/* 586 */     int curr = -1;
/*     */ 
/* 588 */     int index = -1;
/*     */ 
/* 590 */     SetIterator() { this.next = ReferenceLinkedOpenHashSet.this.first;
/* 591 */       this.index = 0; }
/*     */ 
/*     */     SetIterator() {
/* 594 */       if (ReferenceLinkedOpenHashSet.this.key[ReferenceLinkedOpenHashSet.this.last] == from) {
/* 595 */         this.prev = ReferenceLinkedOpenHashSet.this.last;
/* 596 */         this.index = ReferenceLinkedOpenHashSet.this.size;
/*     */       }
/*     */       else
/*     */       {
/* 600 */         int pos = (from == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(from))) & ReferenceLinkedOpenHashSet.this.mask;
/*     */ 
/* 602 */         while (ReferenceLinkedOpenHashSet.this.used[pos] != 0) {
/* 603 */           if (ReferenceLinkedOpenHashSet.this.key[pos] == from)
/*     */           {
/* 605 */             this.next = ((int)ReferenceLinkedOpenHashSet.this.link[pos]);
/* 606 */             this.prev = pos;
/* 607 */             return;
/*     */           }
/* 609 */           pos = pos + 1 & ReferenceLinkedOpenHashSet.this.mask;
/*     */         }
/* 611 */         throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/*     */       }
/*     */     }
/* 614 */     public boolean hasNext() { return this.next != -1; } 
/* 615 */     public boolean hasPrevious() { return this.prev != -1; } 
/*     */     public K next() {
/* 617 */       if (!hasNext()) throw new NoSuchElementException();
/* 618 */       this.curr = this.next;
/* 619 */       this.next = ((int)ReferenceLinkedOpenHashSet.this.link[this.curr]);
/* 620 */       this.prev = this.curr;
/* 621 */       if (this.index >= 0) this.index += 1;
/*     */ 
/* 623 */       return ReferenceLinkedOpenHashSet.this.key[this.curr];
/*     */     }
/*     */     public K previous() {
/* 626 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 627 */       this.curr = this.prev;
/* 628 */       this.prev = ((int)(ReferenceLinkedOpenHashSet.this.link[this.curr] >>> 32));
/* 629 */       this.next = this.curr;
/* 630 */       if (this.index >= 0) this.index -= 1;
/* 631 */       return ReferenceLinkedOpenHashSet.this.key[this.curr];
/*     */     }
/*     */     private final void ensureIndexKnown() {
/* 634 */       if (this.index >= 0) return;
/* 635 */       if (this.prev == -1) {
/* 636 */         this.index = 0;
/* 637 */         return;
/*     */       }
/* 639 */       if (this.next == -1) {
/* 640 */         this.index = ReferenceLinkedOpenHashSet.this.size;
/* 641 */         return;
/*     */       }
/* 643 */       int pos = ReferenceLinkedOpenHashSet.this.first;
/* 644 */       this.index = 1;
/* 645 */       while (pos != this.prev) {
/* 646 */         pos = (int)ReferenceLinkedOpenHashSet.this.link[pos];
/* 647 */         this.index += 1;
/*     */       }
/*     */     }
/*     */ 
/* 651 */     public int nextIndex() { ensureIndexKnown();
/* 652 */       return this.index; }
/*     */ 
/*     */     public int previousIndex() {
/* 655 */       ensureIndexKnown();
/* 656 */       return this.index - 1;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 660 */       ensureIndexKnown();
/* 661 */       if (this.curr == -1) throw new IllegalStateException();
/* 662 */       if (this.curr == this.prev)
/*     */       {
/* 665 */         this.index -= 1;
/* 666 */         this.prev = ((int)(ReferenceLinkedOpenHashSet.this.link[this.curr] >>> 32));
/*     */       }
/*     */       else {
/* 669 */         this.next = ((int)ReferenceLinkedOpenHashSet.this.link[this.curr]);
/* 670 */       }ReferenceLinkedOpenHashSet.this.size -= 1;
/*     */ 
/* 673 */       if (this.prev == -1) ReferenceLinkedOpenHashSet.this.first = this.next;
/*     */       else
/* 675 */         ReferenceLinkedOpenHashSet.this.link[this.prev] ^= (ReferenceLinkedOpenHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 676 */       if (this.next == -1) ReferenceLinkedOpenHashSet.this.last = this.prev;
/*     */       else
/* 678 */         ReferenceLinkedOpenHashSet.this.link[this.next] ^= (ReferenceLinkedOpenHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*     */       int last;
/*     */       while (true) {
/* 682 */         pos = (last = pos) + 1 & ReferenceLinkedOpenHashSet.this.mask;
/* 683 */         while (ReferenceLinkedOpenHashSet.this.used[pos] != 0) {
/* 684 */           int slot = (ReferenceLinkedOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(ReferenceLinkedOpenHashSet.this.key[pos]))) & ReferenceLinkedOpenHashSet.this.mask;
/* 685 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 686 */           pos = pos + 1 & ReferenceLinkedOpenHashSet.this.mask;
/*     */         }
/* 688 */         if (ReferenceLinkedOpenHashSet.this.used[pos] == 0) break;
/* 689 */         ReferenceLinkedOpenHashSet.this.key[last] = ReferenceLinkedOpenHashSet.this.key[pos];
/* 690 */         if (this.next == pos) this.next = last;
/* 691 */         if (this.prev == pos) this.prev = last;
/* 692 */         ReferenceLinkedOpenHashSet.this.fixPointers(pos, last);
/*     */       }
/* 694 */       ReferenceLinkedOpenHashSet.this.used[last] = false;
/* 695 */       ReferenceLinkedOpenHashSet.this.key[last] = null;
/* 696 */       this.curr = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceLinkedOpenHashSet
 * JD-Core Version:    0.6.2
 */