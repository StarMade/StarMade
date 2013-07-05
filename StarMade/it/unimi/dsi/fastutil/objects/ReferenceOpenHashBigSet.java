/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.Size64;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanBigArrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ReferenceOpenHashBigSet<K> extends AbstractReferenceSet<K>
/*     */   implements Serializable, Cloneable, Hash, Size64
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient K[][] key;
/*     */   protected transient boolean[][] used;
/*     */   protected final float f;
/*     */   protected transient long n;
/*     */   protected transient long maxFill;
/*     */   protected transient long mask;
/*     */   protected transient int segmentMask;
/*     */   protected transient int baseMask;
/*     */   protected long size;
/*     */ 
/*     */   private void initMasks()
/*     */   {
/*  91 */     this.mask = (this.n - 1L);
/*     */ 
/*  95 */     this.segmentMask = (this.key[0].length - 1);
/*  96 */     this.baseMask = (this.key.length - 1);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(long expected, float f)
/*     */   {
/* 107 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 108 */     if (this.n < 0L) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 109 */     this.f = f;
/* 110 */     this.n = HashCommon.bigArraySize(expected, f);
/* 111 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 112 */     this.key = ((Object[][])ObjectBigArrays.newBigArray(this.n));
/* 113 */     this.used = BooleanBigArrays.newBigArray(this.n);
/* 114 */     initMasks();
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(long expected)
/*     */   {
/* 124 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet()
/*     */   {
/* 132 */     this(16L, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(Collection<? extends K> c, float f)
/*     */   {
/* 142 */     this(c.size(), f);
/* 143 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(Collection<? extends K> c)
/*     */   {
/* 153 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(ReferenceCollection<? extends K> c, float f)
/*     */   {
/* 163 */     this(c.size(), f);
/* 164 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(ReferenceCollection<? extends K> c)
/*     */   {
/* 174 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(ObjectIterator<K> i, float f)
/*     */   {
/* 184 */     this(16L, f);
/* 185 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(ObjectIterator<K> i)
/*     */   {
/* 194 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(K[] a, int offset, int length, float f)
/*     */   {
/* 204 */     this(length < 0 ? 0L : length, f);
/* 205 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 206 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(K[] a, int offset, int length)
/*     */   {
/* 215 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(K[] a, float f)
/*     */   {
/* 223 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet(K[] a)
/*     */   {
/* 231 */     this(a, 0.75F);
/*     */   }
/*     */   public boolean add(K k) {
/* 234 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/*     */ 
/* 236 */     int displ = (int)(h & this.segmentMask);
/* 237 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 239 */     while (this.used[base][displ] != 0) {
/* 240 */       if (this.key[base][displ] == k) return false;
/* 241 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/* 243 */     this.used[base][displ] = 1;
/* 244 */     this.key[base][displ] = k;
/* 245 */     if (++this.size >= this.maxFill) rehash(2L * this.n);
/*     */ 
/* 247 */     return true;
/*     */   }
/*     */ 
/*     */   protected final long shiftKeys(long pos)
/*     */   {
/*     */     long last;
/*     */     while (true)
/*     */     {
/* 263 */       pos = (last = pos) + 1L & this.mask;
/* 264 */       while (BooleanBigArrays.get(this.used, pos)) {
/* 265 */         long slot = (ObjectBigArrays.get(this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(ObjectBigArrays.get(this.key, pos)))) & this.mask;
/* 266 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 267 */         pos = pos + 1L & this.mask;
/*     */       }
/* 269 */       if (!BooleanBigArrays.get(this.used, pos)) break;
/* 270 */       ObjectBigArrays.set(this.key, last, ObjectBigArrays.get(this.key, pos));
/*     */     }
/* 272 */     BooleanBigArrays.set(this.used, last, false);
/* 273 */     ObjectBigArrays.set(this.key, last, null);
/* 274 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object k) {
/* 278 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/*     */ 
/* 280 */     int displ = (int)(h & this.segmentMask);
/* 281 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 283 */     while (this.used[base][displ] != 0) {
/* 284 */       if (this.key[base][displ] == k) {
/* 285 */         this.size -= 1L;
/* 286 */         shiftKeys(base * 134217728L + displ);
/*     */ 
/* 288 */         return true;
/*     */       }
/* 290 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/* 292 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k) {
/* 296 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/*     */ 
/* 298 */     int displ = (int)(h & this.segmentMask);
/* 299 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 301 */     while (this.used[base][displ] != 0) {
/* 302 */       if (this.key[base][displ] == k) return true;
/* 303 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/* 305 */     return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 314 */     if (this.size == 0L) return;
/* 315 */     this.size = 0L;
/* 316 */     BooleanBigArrays.fill(this.used, false);
/* 317 */     ObjectBigArrays.fill(this.key, null);
/*     */   }
/*     */ 
/*     */   public ObjectIterator<K> iterator()
/*     */   {
/* 421 */     return new SetIterator(null);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 435 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 450 */     long l = HashCommon.bigArraySize(this.size, this.f);
/* 451 */     if (l >= this.n) return true; try
/*     */     {
/* 453 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 455 */       return false;
/* 456 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(long n)
/*     */   {
/* 477 */     long l = HashCommon.bigArraySize(n, this.f);
/* 478 */     if (this.n <= l) return true; try
/*     */     {
/* 480 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 482 */       return false;
/* 483 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(long newN)
/*     */   {
/* 496 */     boolean[][] used = this.used;
/* 497 */     Object[][] key = this.key;
/* 498 */     boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
/* 499 */     Object[][] newKey = (Object[][])ObjectBigArrays.newBigArray(newN);
/* 500 */     long newMask = newN - 1L;
/* 501 */     int newSegmentMask = newKey[0].length - 1;
/* 502 */     int newBaseMask = newKey.length - 1;
/* 503 */     int base = 0; int displ = 0;
/*     */ 
/* 506 */     for (long i = this.size; i-- != 0L; ) {
/* 507 */       while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 508 */       Object k = key[base][displ];
/* 509 */       long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/*     */ 
/* 511 */       int d = (int)(h & newSegmentMask);
/* 512 */       int b = (int)((h & newMask) >>> 27);
/* 513 */       while (newUsed[b][d] != 0) b = b + ((d = d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask;
/* 514 */       newUsed[b][d] = 1;
/* 515 */       newKey[b][d] = k;
/* 516 */       base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/*     */     }
/* 518 */     this.n = newN;
/* 519 */     this.key = newKey;
/* 520 */     this.used = newUsed;
/* 521 */     initMasks();
/* 522 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/*     */   }
/*     */   @Deprecated
/*     */   public int size() {
/* 526 */     return (int)Math.min(2147483647L, this.size);
/*     */   }
/*     */   public long size64() {
/* 529 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 532 */     return this.size == 0L;
/*     */   }
/*     */ 
/*     */   public ReferenceOpenHashBigSet<K> clone()
/*     */   {
/*     */     ReferenceOpenHashBigSet c;
/*     */     try
/*     */     {
/* 545 */       c = (ReferenceOpenHashBigSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 548 */       throw new InternalError();
/*     */     }
/* 550 */     c.key = ObjectBigArrays.copy(this.key);
/* 551 */     c.used = BooleanBigArrays.copy(this.used);
/* 552 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 564 */     boolean[][] used = this.used;
/* 565 */     Object[][] key = this.key;
/* 566 */     int h = 0;
/* 567 */     int base = 0; int displ = 0;
/* 568 */     for (long j = this.size; j-- != 0L; ) {
/* 569 */       while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 570 */       if (this != key[base][displ])
/* 571 */         h += (key[base][displ] == null ? 0 : System.identityHashCode(key[base][displ]));
/* 572 */       base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/*     */     }
/* 574 */     return h; } 
/* 577 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 578 */     s.defaultWriteObject();
/* 579 */     for (long j = this.size; j-- != 0L; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 583 */     s.defaultReadObject();
/* 584 */     this.n = HashCommon.bigArraySize(this.size, this.f);
/* 585 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 586 */     Object[][] key = this.key = (Object[][])ObjectBigArrays.newBigArray(this.n);
/* 587 */     boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.n);
/* 588 */     initMasks();
/*     */ 
/* 592 */     for (long i = this.size; i-- != 0L; ) {
/* 593 */       Object k = s.readObject();
/* 594 */       long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(k));
/* 595 */       int base = (int)((h & this.mask) >>> 27);
/* 596 */       int displ = (int)(h & this.segmentMask);
/* 597 */       while (used[base][displ] != 0) base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 598 */       used[base][displ] = 1;
/* 599 */       key[base][displ] = k;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractObjectIterator<K>
/*     */   {
/*     */     int base;
/*     */     int displ;
/*     */     int lastBase;
/*     */     int lastDispl;
/*     */     long c;
/*     */     ReferenceArrayList<K> wrapped;
/*     */ 
/*     */     private SetIterator()
/*     */     {
/* 333 */       this.c = ReferenceOpenHashBigSet.this.size;
/*     */ 
/* 338 */       this.base = ReferenceOpenHashBigSet.this.key.length;
/* 339 */       this.lastBase = -1;
/* 340 */       boolean[][] used = ReferenceOpenHashBigSet.this.used;
/* 341 */       if (this.c != 0L)
/*     */         do if (this.displ-- == 0) {
/* 343 */             this.base -= 1;
/* 344 */             this.displ = ((int)ReferenceOpenHashBigSet.this.mask);
/*     */           }
/* 346 */         while (used[this.base][this.displ] == 0); 
/*     */     }
/*     */ 
/* 349 */     public boolean hasNext() { return this.c != 0L; }
/*     */ 
/*     */     public K next() {
/* 352 */       if (!hasNext()) throw new NoSuchElementException();
/* 353 */       this.c -= 1L;
/*     */ 
/* 355 */       if (this.base < 0) return this.wrapped.get(-(this.lastBase = --this.base) - 2);
/* 356 */       Object retVal = ReferenceOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
/* 357 */       if (this.c != 0L) {
/* 358 */         boolean[][] used = ReferenceOpenHashBigSet.this.used;
/*     */         do
/* 360 */           if (this.displ-- == 0) {
/* 361 */             if (this.base-- == 0) break;
/* 362 */             this.displ = ((int)ReferenceOpenHashBigSet.this.mask);
/*     */           }
/* 364 */         while (used[this.base][this.displ] == 0);
/*     */       }
/*     */ 
/* 367 */       return retVal;
/*     */     }
/*     */ 
/*     */     protected final long shiftKeys(long pos)
/*     */     {
/*     */       long last;
/*     */       while (true)
/*     */       {
/* 384 */         pos = (last = pos) + 1L & ReferenceOpenHashBigSet.this.mask;
/* 385 */         while (BooleanBigArrays.get(ReferenceOpenHashBigSet.this.used, pos)) {
/* 386 */           long slot = (ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(System.identityHashCode(ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos)))) & ReferenceOpenHashBigSet.this.mask;
/* 387 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 388 */           pos = pos + 1L & ReferenceOpenHashBigSet.this.mask;
/*     */         }
/* 390 */         if (!BooleanBigArrays.get(ReferenceOpenHashBigSet.this.used, pos)) break;
/* 391 */         if (pos < last)
/*     */         {
/* 393 */           if (this.wrapped == null) this.wrapped = new ReferenceArrayList();
/* 394 */           this.wrapped.add(ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos));
/*     */         }
/* 396 */         ObjectBigArrays.set(ReferenceOpenHashBigSet.this.key, last, ObjectBigArrays.get(ReferenceOpenHashBigSet.this.key, pos));
/*     */       }
/* 398 */       BooleanBigArrays.set(ReferenceOpenHashBigSet.this.used, last, false);
/* 399 */       ObjectBigArrays.set(ReferenceOpenHashBigSet.this.key, last, null);
/* 400 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 404 */       if (this.lastBase == -1) throw new IllegalStateException();
/* 405 */       if (this.base < -1)
/*     */       {
/* 407 */         ReferenceOpenHashBigSet.this.remove(this.wrapped.set(-this.base - 2, null));
/* 408 */         this.lastBase = -1;
/* 409 */         return;
/*     */       }
/* 411 */       ReferenceOpenHashBigSet.this.size -= 1L;
/* 412 */       if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.c > 0L)) {
/* 413 */         this.c += 1L;
/* 414 */         next();
/*     */       }
/* 416 */       this.lastBase = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ReferenceOpenHashBigSet
 * JD-Core Version:    0.6.2
 */