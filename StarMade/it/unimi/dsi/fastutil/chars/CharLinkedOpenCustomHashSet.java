/*     */ package it.unimi.dsi.fastutil.chars;
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
/*     */ public class CharLinkedOpenCustomHashSet extends AbstractCharSortedSet
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient char[] key;
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
/*     */   protected CharHash.Strategy strategy;
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(int expected, float f, CharHash.Strategy strategy)
/*     */   {
/* 122 */     this.strategy = strategy;
/* 123 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 124 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 125 */     this.f = f;
/* 126 */     this.n = HashCommon.arraySize(expected, f);
/* 127 */     this.mask = (this.n - 1);
/* 128 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 129 */     this.key = new char[this.n];
/* 130 */     this.used = new boolean[this.n];
/* 131 */     this.link = new long[this.n];
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(int expected, CharHash.Strategy strategy)
/*     */   {
/* 139 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(CharHash.Strategy strategy)
/*     */   {
/* 146 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(Collection<? extends Character> c, float f, CharHash.Strategy strategy)
/*     */   {
/* 155 */     this(c.size(), f, strategy);
/* 156 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(Collection<? extends Character> c, CharHash.Strategy strategy)
/*     */   {
/* 165 */     this(c, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(CharCollection c, float f, CharHash.Strategy strategy)
/*     */   {
/* 174 */     this(c.size(), f, strategy);
/* 175 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(CharCollection c, CharHash.Strategy strategy)
/*     */   {
/* 184 */     this(c, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(CharIterator i, float f, CharHash.Strategy strategy)
/*     */   {
/* 193 */     this(16, f, strategy);
/* 194 */     while (i.hasNext()) add(i.nextChar());
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(CharIterator i, CharHash.Strategy strategy)
/*     */   {
/* 202 */     this(i, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(Iterator<?> i, float f, CharHash.Strategy strategy)
/*     */   {
/* 211 */     this(CharIterators.asCharIterator(i), f, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(Iterator<?> i, CharHash.Strategy strategy)
/*     */   {
/* 219 */     this(CharIterators.asCharIterator(i), strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(char[] a, int offset, int length, float f, CharHash.Strategy strategy)
/*     */   {
/* 230 */     this(length < 0 ? 0 : length, f, strategy);
/* 231 */     CharArrays.ensureOffsetLength(a, offset, length);
/* 232 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(char[] a, int offset, int length, CharHash.Strategy strategy)
/*     */   {
/* 242 */     this(a, offset, length, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(char[] a, float f, CharHash.Strategy strategy)
/*     */   {
/* 251 */     this(a, 0, a.length, f, strategy);
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet(char[] a, CharHash.Strategy strategy)
/*     */   {
/* 260 */     this(a, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public CharHash.Strategy strategy()
/*     */   {
/* 267 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public boolean add(char k)
/*     */   {
/* 275 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 277 */     while (this.used[pos] != 0) {
/* 278 */       if (this.strategy.equals(this.key[pos], k)) return false;
/* 279 */       pos = pos + 1 & this.mask;
/*     */     }
/* 281 */     this.used[pos] = true;
/* 282 */     this.key[pos] = k;
/* 283 */     if (this.size == 0) {
/* 284 */       this.first = (this.last = pos);
/*     */ 
/* 286 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 289 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 290 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 291 */       this.last = pos;
/*     */     }
/* 293 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 295 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 307 */       pos = (last = pos) + 1 & this.mask;
/* 308 */       while (this.used[pos] != 0) {
/* 309 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 310 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 311 */         pos = pos + 1 & this.mask;
/*     */       }
/* 313 */       if (this.used[pos] == 0) break;
/* 314 */       this.key[last] = this.key[pos];
/* 315 */       fixPointers(pos, last);
/*     */     }
/* 317 */     this.used[last] = false;
/* 318 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(char k)
/*     */   {
/* 323 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 325 */     while (this.used[pos] != 0) {
/* 326 */       if (this.strategy.equals(this.key[pos], k)) {
/* 327 */         this.size -= 1;
/* 328 */         fixPointers(pos);
/* 329 */         shiftKeys(pos);
/*     */ 
/* 331 */         return true;
/*     */       }
/* 333 */       pos = pos + 1 & this.mask;
/*     */     }
/* 335 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(char k)
/*     */   {
/* 340 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 342 */     while (this.used[pos] != 0) {
/* 343 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 344 */       pos = pos + 1 & this.mask;
/*     */     }
/* 346 */     return false;
/*     */   }
/*     */ 
/*     */   public char removeFirstChar()
/*     */   {
/* 353 */     if (this.size == 0) throw new NoSuchElementException();
/* 354 */     this.size -= 1;
/* 355 */     int pos = this.first;
/*     */ 
/* 357 */     this.first = ((int)this.link[pos]);
/* 358 */     if (0 <= this.first)
/*     */     {
/* 360 */       this.link[this.first] |= -4294967296L;
/*     */     }
/* 362 */     char k = this.key[pos];
/* 363 */     shiftKeys(pos);
/* 364 */     return k;
/*     */   }
/*     */ 
/*     */   public char removeLastChar()
/*     */   {
/* 371 */     if (this.size == 0) throw new NoSuchElementException();
/* 372 */     this.size -= 1;
/* 373 */     int pos = this.last;
/*     */ 
/* 375 */     this.last = ((int)(this.link[pos] >>> 32));
/* 376 */     if (0 <= this.last)
/*     */     {
/* 378 */       this.link[this.last] |= 4294967295L;
/*     */     }
/* 380 */     char k = this.key[pos];
/* 381 */     shiftKeys(pos);
/* 382 */     return k;
/*     */   }
/*     */   private void moveIndexToFirst(int i) {
/* 385 */     if ((this.size == 1) || (this.first == i)) return;
/* 386 */     if (this.last == i) {
/* 387 */       this.last = ((int)(this.link[i] >>> 32));
/*     */ 
/* 389 */       this.link[this.last] |= 4294967295L;
/*     */     }
/*     */     else {
/* 392 */       long linki = this.link[i];
/* 393 */       int prev = (int)(linki >>> 32);
/* 394 */       int next = (int)linki;
/* 395 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 396 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 398 */     this.link[this.first] ^= (this.link[this.first] ^ (i & 0xFFFFFFFF) << 32) & 0x0;
/* 399 */     this.link[i] = (0x0 | this.first & 0xFFFFFFFF);
/* 400 */     this.first = i;
/*     */   }
/*     */   private void moveIndexToLast(int i) {
/* 403 */     if ((this.size == 1) || (this.last == i)) return;
/* 404 */     if (this.first == i) {
/* 405 */       this.first = ((int)this.link[i]);
/*     */ 
/* 407 */       this.link[this.first] |= -4294967296L;
/*     */     }
/*     */     else {
/* 410 */       long linki = this.link[i];
/* 411 */       int prev = (int)(linki >>> 32);
/* 412 */       int next = (int)linki;
/* 413 */       this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 414 */       this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */     }
/* 416 */     this.link[this.last] ^= (this.link[this.last] ^ i & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 417 */     this.link[i] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 418 */     this.last = i;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToFirst(char k)
/*     */   {
/* 426 */     char[] key = this.key;
/* 427 */     boolean[] used = this.used;
/* 428 */     int mask = this.mask;
/*     */ 
/* 430 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*     */ 
/* 432 */     while (used[pos] != 0) {
/* 433 */       if (this.strategy.equals(k, key[pos])) {
/* 434 */         moveIndexToFirst(pos);
/* 435 */         return false;
/*     */       }
/* 437 */       pos = pos + 1 & mask;
/*     */     }
/* 439 */     used[pos] = true;
/* 440 */     key[pos] = k;
/* 441 */     if (this.size == 0) {
/* 442 */       this.first = (this.last = pos);
/*     */ 
/* 444 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 447 */       this.link[this.first] ^= (this.link[this.first] ^ (pos & 0xFFFFFFFF) << 32) & 0x0;
/* 448 */       this.link[pos] = (0x0 | this.first & 0xFFFFFFFF);
/* 449 */       this.first = pos;
/*     */     }
/* 451 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 453 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean addAndMoveToLast(char k)
/*     */   {
/* 461 */     char[] key = this.key;
/* 462 */     boolean[] used = this.used;
/* 463 */     int mask = this.mask;
/*     */ 
/* 465 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & mask;
/*     */ 
/* 467 */     while (used[pos] != 0) {
/* 468 */       if (this.strategy.equals(k, key[pos])) {
/* 469 */         moveIndexToLast(pos);
/* 470 */         return false;
/*     */       }
/* 472 */       pos = pos + 1 & mask;
/*     */     }
/* 474 */     used[pos] = true;
/* 475 */     key[pos] = k;
/* 476 */     if (this.size == 0) {
/* 477 */       this.first = (this.last = pos);
/*     */ 
/* 479 */       this.link[pos] = -1L;
/*     */     }
/*     */     else {
/* 482 */       this.link[this.last] ^= (this.link[this.last] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 483 */       this.link[pos] = ((this.last & 0xFFFFFFFF) << 32 | 0xFFFFFFFF);
/* 484 */       this.last = pos;
/*     */     }
/* 486 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size, this.f));
/*     */ 
/* 488 */     return true;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 497 */     if (this.size == 0) return;
/* 498 */     this.size = 0;
/* 499 */     BooleanArrays.fill(this.used, false);
/* 500 */     this.first = (this.last = -1);
/*     */   }
/*     */   public int size() {
/* 503 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 506 */     return this.size == 0;
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
/* 523 */     return 16;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int i)
/*     */   {
/* 533 */     if (this.size == 0) {
/* 534 */       this.first = (this.last = -1);
/* 535 */       return;
/*     */     }
/* 537 */     if (this.first == i) {
/* 538 */       this.first = ((int)this.link[i]);
/* 539 */       if (0 <= this.first)
/*     */       {
/* 541 */         this.link[this.first] |= -4294967296L;
/*     */       }
/* 543 */       return;
/*     */     }
/* 545 */     if (this.last == i) {
/* 546 */       this.last = ((int)(this.link[i] >>> 32));
/* 547 */       if (0 <= this.last)
/*     */       {
/* 549 */         this.link[this.last] |= 4294967295L;
/*     */       }
/* 551 */       return;
/*     */     }
/* 553 */     long linki = this.link[i];
/* 554 */     int prev = (int)(linki >>> 32);
/* 555 */     int next = (int)linki;
/* 556 */     this.link[prev] ^= (this.link[prev] ^ linki & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 557 */     this.link[next] ^= (this.link[next] ^ linki & 0x0) & 0x0;
/*     */   }
/*     */ 
/*     */   protected void fixPointers(int s, int d)
/*     */   {
/* 568 */     if (this.size == 1) {
/* 569 */       this.first = (this.last = d);
/*     */ 
/* 571 */       this.link[d] = -1L;
/* 572 */       return;
/*     */     }
/* 574 */     if (this.first == s) {
/* 575 */       this.first = d;
/* 576 */       this.link[((int)this.link[s])] ^= (this.link[((int)this.link[s])] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 577 */       this.link[d] = this.link[s];
/* 578 */       return;
/*     */     }
/* 580 */     if (this.last == s) {
/* 581 */       this.last = d;
/* 582 */       this.link[((int)(this.link[s] >>> 32))] ^= (this.link[((int)(this.link[s] >>> 32))] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 583 */       this.link[d] = this.link[s];
/* 584 */       return;
/*     */     }
/* 586 */     long links = this.link[s];
/* 587 */     int prev = (int)(links >>> 32);
/* 588 */     int next = (int)links;
/* 589 */     this.link[prev] ^= (this.link[prev] ^ d & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 590 */     this.link[next] ^= (this.link[next] ^ (d & 0xFFFFFFFF) << 32) & 0x0;
/* 591 */     this.link[d] = links;
/*     */   }
/*     */ 
/*     */   public char firstChar()
/*     */   {
/* 598 */     if (this.size == 0) throw new NoSuchElementException();
/* 599 */     return this.key[this.first];
/*     */   }
/*     */ 
/*     */   public char lastChar()
/*     */   {
/* 606 */     if (this.size == 0) throw new NoSuchElementException();
/* 607 */     return this.key[this.last];
/*     */   }
/* 609 */   public CharSortedSet tailSet(char from) { throw new UnsupportedOperationException(); } 
/* 610 */   public CharSortedSet headSet(char to) { throw new UnsupportedOperationException(); } 
/* 611 */   public CharSortedSet subSet(char from, char to) { throw new UnsupportedOperationException(); } 
/* 612 */   public CharComparator comparator() { return null; }
/*     */ 
/*     */ 
/*     */   public CharListIterator iterator(char from)
/*     */   {
/* 745 */     return new SetIterator(from);
/*     */   }
/*     */   public CharListIterator iterator() {
/* 748 */     return new SetIterator();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 762 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 777 */     int l = HashCommon.arraySize(this.size, this.f);
/* 778 */     if (l >= this.n) return true; try
/*     */     {
/* 780 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 782 */       return false;
/* 783 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 804 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 805 */     if (this.n <= l) return true; try
/*     */     {
/* 807 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 809 */       return false;
/* 810 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 823 */     int i = this.first; int prev = -1; int newPrev = -1;
/*     */ 
/* 825 */     char[] key = this.key;
/* 826 */     int newMask = newN - 1;
/* 827 */     char[] newKey = new char[newN];
/* 828 */     boolean[] newUsed = new boolean[newN];
/* 829 */     long[] link = this.link;
/* 830 */     long[] newLink = new long[newN];
/* 831 */     this.first = -1;
/* 832 */     for (int j = this.size; j-- != 0; ) {
/* 833 */       char k = key[i];
/* 834 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 835 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 836 */       newUsed[pos] = true;
/* 837 */       newKey[pos] = k;
/* 838 */       if (prev != -1) {
/* 839 */         newLink[newPrev] ^= (newLink[newPrev] ^ pos & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 840 */         newLink[pos] ^= (newLink[pos] ^ (newPrev & 0xFFFFFFFF) << 32) & 0x0;
/* 841 */         newPrev = pos;
/*     */       }
/*     */       else {
/* 844 */         newPrev = this.first = pos;
/*     */ 
/* 846 */         newLink[pos] = -1L;
/*     */       }
/* 848 */       int t = i;
/* 849 */       i = (int)link[i];
/* 850 */       prev = t;
/*     */     }
/* 852 */     this.n = newN;
/* 853 */     this.mask = newMask;
/* 854 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 855 */     this.key = newKey;
/* 856 */     this.used = newUsed;
/* 857 */     this.link = newLink;
/* 858 */     this.last = newPrev;
/* 859 */     if (newPrev != -1)
/*     */     {
/* 861 */       newLink[newPrev] |= 4294967295L;
/*     */     }
/*     */   }
/*     */ 
/*     */   public CharLinkedOpenCustomHashSet clone()
/*     */   {
/*     */     CharLinkedOpenCustomHashSet c;
/*     */     try
/*     */     {
/* 874 */       c = (CharLinkedOpenCustomHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 877 */       throw new InternalError();
/*     */     }
/* 879 */     c.key = ((char[])this.key.clone());
/* 880 */     c.used = ((boolean[])this.used.clone());
/* 881 */     c.link = ((long[])this.link.clone());
/* 882 */     c.strategy = this.strategy;
/* 883 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 895 */     int h = 0; int i = 0; int j = this.size;
/* 896 */     while (j-- != 0) {
/* 897 */       while (this.used[i] == 0) i++;
/* 898 */       h += this.strategy.hashCode(this.key[i]);
/* 899 */       i++;
/*     */     }
/* 901 */     return h; } 
/* 904 */   private void writeObject(ObjectOutputStream s) throws IOException { CharIterator i = iterator();
/* 905 */     s.defaultWriteObject();
/* 906 */     for (int j = this.size; j-- != 0; s.writeChar(i.nextChar()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 910 */     s.defaultReadObject();
/* 911 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 912 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 913 */     this.mask = (this.n - 1);
/* 914 */     char[] key = this.key = new char[this.n];
/* 915 */     boolean[] used = this.used = new boolean[this.n];
/* 916 */     long[] link = this.link = new long[this.n];
/* 917 */     int prev = -1;
/* 918 */     this.first = (this.last = -1);
/*     */ 
/* 920 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 921 */       char k = s.readChar();
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
/*     */   private class SetIterator extends AbstractCharListIterator
/*     */   {
/* 621 */     int prev = -1;
/*     */ 
/* 623 */     int next = -1;
/*     */ 
/* 625 */     int curr = -1;
/*     */ 
/* 627 */     int index = -1;
/*     */ 
/* 629 */     SetIterator() { this.next = CharLinkedOpenCustomHashSet.this.first;
/* 630 */       this.index = 0; }
/*     */ 
/*     */     SetIterator(char from) {
/* 633 */       if (CharLinkedOpenCustomHashSet.this.strategy.equals(CharLinkedOpenCustomHashSet.this.key[CharLinkedOpenCustomHashSet.this.last], from)) {
/* 634 */         this.prev = CharLinkedOpenCustomHashSet.this.last;
/* 635 */         this.index = CharLinkedOpenCustomHashSet.this.size;
/*     */       }
/*     */       else
/*     */       {
/* 639 */         int pos = HashCommon.murmurHash3(CharLinkedOpenCustomHashSet.this.strategy.hashCode(from)) & CharLinkedOpenCustomHashSet.this.mask;
/*     */ 
/* 641 */         while (CharLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 642 */           if (CharLinkedOpenCustomHashSet.this.strategy.equals(CharLinkedOpenCustomHashSet.this.key[pos], from))
/*     */           {
/* 644 */             this.next = ((int)CharLinkedOpenCustomHashSet.this.link[pos]);
/* 645 */             this.prev = pos;
/* 646 */             return;
/*     */           }
/* 648 */           pos = pos + 1 & CharLinkedOpenCustomHashSet.this.mask;
/*     */         }
/* 650 */         throw new NoSuchElementException("The key " + from + " does not belong to this set.");
/*     */       }
/*     */     }
/* 653 */     public boolean hasNext() { return this.next != -1; } 
/* 654 */     public boolean hasPrevious() { return this.prev != -1; } 
/*     */     public char nextChar() {
/* 656 */       if (!hasNext()) throw new NoSuchElementException();
/* 657 */       this.curr = this.next;
/* 658 */       this.next = ((int)CharLinkedOpenCustomHashSet.this.link[this.curr]);
/* 659 */       this.prev = this.curr;
/* 660 */       if (this.index >= 0) this.index += 1;
/*     */ 
/* 662 */       return CharLinkedOpenCustomHashSet.this.key[this.curr];
/*     */     }
/*     */     public char previousChar() {
/* 665 */       if (!hasPrevious()) throw new NoSuchElementException();
/* 666 */       this.curr = this.prev;
/* 667 */       this.prev = ((int)(CharLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/* 668 */       this.next = this.curr;
/* 669 */       if (this.index >= 0) this.index -= 1;
/* 670 */       return CharLinkedOpenCustomHashSet.this.key[this.curr];
/*     */     }
/*     */     private final void ensureIndexKnown() {
/* 673 */       if (this.index >= 0) return;
/* 674 */       if (this.prev == -1) {
/* 675 */         this.index = 0;
/* 676 */         return;
/*     */       }
/* 678 */       if (this.next == -1) {
/* 679 */         this.index = CharLinkedOpenCustomHashSet.this.size;
/* 680 */         return;
/*     */       }
/* 682 */       int pos = CharLinkedOpenCustomHashSet.this.first;
/* 683 */       this.index = 1;
/* 684 */       while (pos != this.prev) {
/* 685 */         pos = (int)CharLinkedOpenCustomHashSet.this.link[pos];
/* 686 */         this.index += 1;
/*     */       }
/*     */     }
/*     */ 
/* 690 */     public int nextIndex() { ensureIndexKnown();
/* 691 */       return this.index; }
/*     */ 
/*     */     public int previousIndex() {
/* 694 */       ensureIndexKnown();
/* 695 */       return this.index - 1;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 699 */       ensureIndexKnown();
/* 700 */       if (this.curr == -1) throw new IllegalStateException();
/* 701 */       if (this.curr == this.prev)
/*     */       {
/* 704 */         this.index -= 1;
/* 705 */         this.prev = ((int)(CharLinkedOpenCustomHashSet.this.link[this.curr] >>> 32));
/*     */       }
/*     */       else {
/* 708 */         this.next = ((int)CharLinkedOpenCustomHashSet.this.link[this.curr]);
/* 709 */       }CharLinkedOpenCustomHashSet.this.size -= 1;
/*     */ 
/* 712 */       if (this.prev == -1) CharLinkedOpenCustomHashSet.this.first = this.next;
/*     */       else
/* 714 */         CharLinkedOpenCustomHashSet.this.link[this.prev] ^= (CharLinkedOpenCustomHashSet.this.link[this.prev] ^ this.next & 0xFFFFFFFF) & 0xFFFFFFFF;
/* 715 */       if (this.next == -1) CharLinkedOpenCustomHashSet.this.last = this.prev;
/*     */       else
/* 717 */         CharLinkedOpenCustomHashSet.this.link[this.next] ^= (CharLinkedOpenCustomHashSet.this.link[this.next] ^ (this.prev & 0xFFFFFFFF) << 32) & 0x0; int pos = this.curr;
/*     */       int last;
/*     */       while (true) {
/* 721 */         pos = (last = pos) + 1 & CharLinkedOpenCustomHashSet.this.mask;
/* 722 */         while (CharLinkedOpenCustomHashSet.this.used[pos] != 0) {
/* 723 */           int slot = HashCommon.murmurHash3(CharLinkedOpenCustomHashSet.this.strategy.hashCode(CharLinkedOpenCustomHashSet.this.key[pos])) & CharLinkedOpenCustomHashSet.this.mask;
/* 724 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 725 */           pos = pos + 1 & CharLinkedOpenCustomHashSet.this.mask;
/*     */         }
/* 727 */         if (CharLinkedOpenCustomHashSet.this.used[pos] == 0) break;
/* 728 */         CharLinkedOpenCustomHashSet.this.key[last] = CharLinkedOpenCustomHashSet.this.key[pos];
/* 729 */         if (this.next == pos) this.next = last;
/* 730 */         if (this.prev == pos) this.prev = last;
/* 731 */         CharLinkedOpenCustomHashSet.this.fixPointers(pos, last);
/*     */       }
/* 733 */       CharLinkedOpenCustomHashSet.this.used[last] = false;
/* 734 */       this.curr = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharLinkedOpenCustomHashSet
 * JD-Core Version:    0.6.2
 */