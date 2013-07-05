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
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ReferenceOpenHashSet<K> extends AbstractReferenceSet<K>
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
/*     */ 
/*     */   public ReferenceOpenHashSet(int expected, float f)
/*     */   {
/*  92 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  93 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  94 */     this.f = f;
/*  95 */     this.n = HashCommon.arraySize(expected, f);
/*  96 */     this.mask = (this.n - 1);
/*  97 */     this.maxFill = HashCommon.maxFill(this.n, f);
/*  98 */     this.key = ((Object[])new Object[this.n]);
/*  99 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(int expected)
/*     */   {
/* 106 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet()
/*     */   {
/* 112 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(Collection<? extends K> c, float f)
/*     */   {
/* 120 */     this(c.size(), f);
/* 121 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(Collection<? extends K> c)
/*     */   {
/* 129 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(ReferenceCollection<? extends K> c, float f)
/*     */   {
/* 137 */     this(c.size(), f);
/* 138 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(ReferenceCollection<? extends K> c)
/*     */   {
/* 146 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(ObjectIterator<K> i, float f)
/*     */   {
/* 154 */     this(16, f);
/* 155 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(ObjectIterator<K> i)
/*     */   {
/* 162 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(K[] a, int offset, int length, float f)
/*     */   {
/* 172 */     this(length < 0 ? 0 : length, f);
/* 173 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 174 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(K[] a, int offset, int length)
/*     */   {
/* 183 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(K[] a, float f)
/*     */   {
/* 191 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet(K[] a)
/*     */   {
/* 199 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(K k)
/*     */   {
/* 207 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 209 */     while (this.used[pos] != 0) {
/* 210 */       if (this.key[pos] == k) return false;
/* 211 */       pos = pos + 1 & this.mask;
/*     */     }
/* 213 */     this.used[pos] = true;
/* 214 */     this.key[pos] = k;
/* 215 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 217 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 229 */       pos = (last = pos) + 1 & this.mask;
/* 230 */       while (this.used[pos] != 0) {
/* 231 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(this.key[pos]))) & this.mask;
/* 232 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 233 */         pos = pos + 1 & this.mask;
/*     */       }
/* 235 */       if (this.used[pos] == 0) break;
/* 236 */       this.key[last] = this.key[pos];
/*     */     }
/* 238 */     this.used[last] = false;
/* 239 */     this.key[last] = null;
/* 240 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object k)
/*     */   {
/* 245 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 247 */     while (this.used[pos] != 0) {
/* 248 */       if (this.key[pos] == k) {
/* 249 */         this.size -= 1;
/* 250 */         shiftKeys(pos);
/*     */ 
/* 252 */         return true;
/*     */       }
/* 254 */       pos = pos + 1 & this.mask;
/*     */     }
/* 256 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k)
/*     */   {
/* 261 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/*     */ 
/* 263 */     while (this.used[pos] != 0) {
/* 264 */       if (this.key[pos] == k) return true;
/* 265 */       pos = pos + 1 & this.mask;
/*     */     }
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 276 */     if (this.size == 0) return;
/* 277 */     this.size = 0;
/* 278 */     BooleanArrays.fill(this.used, false);
/* 279 */     ObjectArrays.fill(this.key, null);
/*     */   }
/*     */   public int size() {
/* 282 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 285 */     return this.size == 0;
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
/* 302 */     return 16;
/*     */   }
/*     */ 
/*     */   public ObjectIterator<K> iterator()
/*     */   {
/* 386 */     return new SetIterator(null);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 400 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 415 */     int l = HashCommon.arraySize(this.size, this.f);
/* 416 */     if (l >= this.n) return true; try
/*     */     {
/* 418 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 420 */       return false;
/* 421 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 442 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 443 */     if (this.n <= l) return true; try
/*     */     {
/* 445 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 447 */       return false;
/* 448 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 461 */     int i = 0;
/* 462 */     boolean[] used = this.used;
/*     */ 
/* 464 */     Object[] key = this.key;
/* 465 */     int newMask = newN - 1;
/* 466 */     Object[] newKey = (Object[])new Object[newN];
/* 467 */     boolean[] newUsed = new boolean[newN];
/* 468 */     for (int j = this.size; j-- != 0; ) {
/* 469 */       while (used[i] == 0) i++;
/* 470 */       Object k = key[i];
/* 471 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & newMask;
/* 472 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 473 */       newUsed[pos] = true;
/* 474 */       newKey[pos] = k;
/* 475 */       i++;
/*     */     }
/* 477 */     this.n = newN;
/* 478 */     this.mask = newMask;
/* 479 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 480 */     this.key = newKey;
/* 481 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashSet<K> clone()
/*     */   {
/*     */     ReferenceOpenHashSet c;
/*     */     try
/*     */     {
/* 494 */       c = (ReferenceOpenHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 497 */       throw new InternalError();
/*     */     }
/* 499 */     c.key = ((Object[])this.key.clone());
/* 500 */     c.used = ((boolean[])this.used.clone());
/* 501 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 513 */     int h = 0; int i = 0; int j = this.size;
/* 514 */     while (j-- != 0) {
/* 515 */       while (this.used[i] == 0) i++;
/* 516 */       if (this != this.key[i])
/* 517 */         h += (this.key[i] == null ? 0 : System.identityHashCode(this.key[i]));
/* 518 */       i++;
/*     */     }
/* 520 */     return h; } 
/* 523 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 524 */     s.defaultWriteObject();
/* 525 */     for (int j = this.size; j-- != 0; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 529 */     s.defaultReadObject();
/* 530 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 531 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 532 */     this.mask = (this.n - 1);
/* 533 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 534 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 536 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 537 */       Object k = s.readObject();
/* 538 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(k))) & this.mask;
/* 539 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 540 */       used[pos] = true;
/* 541 */       key[pos] = k;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractObjectIterator<K>
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     ReferenceArrayList<K> wrapped;
/*     */ 
/*     */     private SetIterator()
/*     */     {
/* 308 */       this.pos = ReferenceOpenHashSet.this.n;
/*     */ 
/* 311 */       this.last = -1;
/*     */ 
/* 313 */       this.c = ReferenceOpenHashSet.this.size;
/*     */ 
/* 318 */       boolean[] used = ReferenceOpenHashSet.this.used;
/* 319 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 322 */       return this.c != 0;
/*     */     }
/*     */     public K next() {
/* 325 */       if (!hasNext()) throw new NoSuchElementException();
/* 326 */       this.c -= 1;
/*     */ 
/* 328 */       if (this.pos < 0) return this.wrapped.get(-(this.last = --this.pos) - 2);
/* 329 */       Object retVal = ReferenceOpenHashSet.this.key[(this.last = this.pos)];
/*     */ 
/* 331 */       if (this.c != 0) {
/* 332 */         boolean[] used = ReferenceOpenHashSet.this.used;
/* 333 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 336 */       return retVal;
/*     */     }
/*     */ 
/*     */     final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 349 */         pos = (last = pos) + 1 & ReferenceOpenHashSet.this.mask;
/* 350 */         while (ReferenceOpenHashSet.this.used[pos] != 0) {
/* 351 */           int slot = (ReferenceOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(System.identityHashCode(ReferenceOpenHashSet.this.key[pos]))) & ReferenceOpenHashSet.this.mask;
/* 352 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 353 */           pos = pos + 1 & ReferenceOpenHashSet.this.mask;
/*     */         }
/* 355 */         if (ReferenceOpenHashSet.this.used[pos] == 0) break;
/* 356 */         if (pos < last)
/*     */         {
/* 358 */           if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 359 */           this.wrapped.add(ReferenceOpenHashSet.this.key[pos]);
/*     */         }
/* 361 */         ReferenceOpenHashSet.this.key[last] = ReferenceOpenHashSet.this.key[pos];
/*     */       }
/* 363 */       ReferenceOpenHashSet.this.used[last] = false;
/* 364 */       ReferenceOpenHashSet.this.key[last] = null;
/* 365 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 369 */       if (this.last == -1) throw new IllegalStateException();
/* 370 */       if (this.pos < -1)
/*     */       {
/* 372 */         ReferenceOpenHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 373 */         this.last = -1;
/* 374 */         return;
/*     */       }
/* 376 */       ReferenceOpenHashSet.this.size -= 1;
/* 377 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 378 */         this.c += 1;
/* 379 */         next();
/*     */       }
/* 381 */       this.last = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet
 * JD-Core Version:    0.6.2
 */