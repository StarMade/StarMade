/*     */ package it.unimi.dsi.fastutil.longs;
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
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LongOpenHashBigSet extends AbstractLongSet
/*     */   implements Serializable, Cloneable, Hash, Size64
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient long[][] key;
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
/*  93 */     this.mask = (this.n - 1L);
/*     */ 
/*  97 */     this.segmentMask = (this.key[0].length - 1);
/*  98 */     this.baseMask = (this.key.length - 1);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(long expected, float f)
/*     */   {
/* 109 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/* 110 */     if (this.n < 0L) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/* 111 */     this.f = f;
/* 112 */     this.n = HashCommon.bigArraySize(expected, f);
/* 113 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 114 */     this.key = LongBigArrays.newBigArray(this.n);
/* 115 */     this.used = BooleanBigArrays.newBigArray(this.n);
/* 116 */     initMasks();
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(long expected)
/*     */   {
/* 124 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet()
/*     */   {
/* 132 */     this(16L, 0.75F);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(Collection<? extends Long> c, float f)
/*     */   {
/* 142 */     this(c.size(), f);
/* 143 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(Collection<? extends Long> c)
/*     */   {
/* 153 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(LongCollection c, float f)
/*     */   {
/* 163 */     this(c.size(), f);
/* 164 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(LongCollection c)
/*     */   {
/* 174 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(LongIterator i, float f)
/*     */   {
/* 184 */     this(16L, f);
/* 185 */     while (i.hasNext()) add(i.nextLong());
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(LongIterator i)
/*     */   {
/* 194 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(Iterator<?> i, float f)
/*     */   {
/* 207 */     this(LongIterators.asLongIterator(i), f);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(Iterator<?> i)
/*     */   {
/* 215 */     this(LongIterators.asLongIterator(i));
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(long[] a, int offset, int length, float f)
/*     */   {
/* 230 */     this(length < 0 ? 0L : length, f);
/* 231 */     LongArrays.ensureOffsetLength(a, offset, length);
/* 232 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(long[] a, int offset, int length)
/*     */   {
/* 243 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(long[] a, float f)
/*     */   {
/* 253 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet(long[] a)
/*     */   {
/* 263 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(long k) {
/* 267 */     long h = HashCommon.murmurHash3(k);
/*     */ 
/* 270 */     int displ = (int)(h & this.segmentMask);
/* 271 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 274 */     while (this.used[base][displ] != 0) {
/* 275 */       if (this.key[base][displ] == k) return false;
/* 276 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/*     */ 
/* 279 */     this.used[base][displ] = 1;
/* 280 */     this.key[base][displ] = k;
/*     */ 
/* 282 */     if (++this.size >= this.maxFill) rehash(2L * this.n);
/*     */ 
/* 284 */     return true;
/*     */   }
/*     */ 
/*     */   protected final long shiftKeys(long pos)
/*     */   {
/*     */     long last;
/*     */     while (true)
/*     */     {
/* 302 */       pos = (last = pos) + 1L & this.mask;
/*     */ 
/* 304 */       while (BooleanBigArrays.get(this.used, pos)) {
/* 305 */         long slot = HashCommon.murmurHash3(LongBigArrays.get(this.key, pos)) & this.mask;
/* 306 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 307 */         pos = pos + 1L & this.mask;
/*     */       }
/*     */ 
/* 310 */       if (!BooleanBigArrays.get(this.used, pos))
/*     */         break;
/* 312 */       LongBigArrays.set(this.key, last, LongBigArrays.get(this.key, pos));
/*     */     }
/*     */ 
/* 315 */     BooleanBigArrays.set(this.used, last, false);
/*     */ 
/* 319 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(long k)
/*     */   {
/* 324 */     long h = HashCommon.murmurHash3(k);
/*     */ 
/* 327 */     int displ = (int)(h & this.segmentMask);
/* 328 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 331 */     while (this.used[base][displ] != 0) {
/* 332 */       if (this.key[base][displ] == k) {
/* 333 */         this.size -= 1L;
/* 334 */         shiftKeys(base * 134217728L + displ);
/*     */ 
/* 336 */         return true;
/*     */       }
/* 338 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/*     */ 
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(long k)
/*     */   {
/* 346 */     long h = HashCommon.murmurHash3(k);
/*     */ 
/* 349 */     int displ = (int)(h & this.segmentMask);
/* 350 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 353 */     while (this.used[base][displ] != 0) {
/* 354 */       if (this.key[base][displ] == k) return true;
/* 355 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/*     */ 
/* 358 */     return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 367 */     if (this.size == 0L) return;
/* 368 */     this.size = 0L;
/* 369 */     BooleanBigArrays.fill(this.used, false);
/*     */   }
/*     */ 
/*     */   public LongIterator iterator()
/*     */   {
/* 472 */     return new SetIterator(null);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 486 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 501 */     long l = HashCommon.bigArraySize(this.size, this.f);
/* 502 */     if (l >= this.n) return true; try
/*     */     {
/* 504 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 506 */       return false;
/* 507 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(long n)
/*     */   {
/* 528 */     long l = HashCommon.bigArraySize(n, this.f);
/* 529 */     if (this.n <= l) return true; try
/*     */     {
/* 531 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 533 */       return false;
/* 534 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(long newN)
/*     */   {
/* 547 */     boolean[][] used = this.used;
/* 548 */     long[][] key = this.key;
/* 549 */     boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
/* 550 */     long[][] newKey = LongBigArrays.newBigArray(newN);
/* 551 */     long newMask = newN - 1L;
/* 552 */     int newSegmentMask = newKey[0].length - 1;
/* 553 */     int newBaseMask = newKey.length - 1;
/* 554 */     int base = 0; int displ = 0;
/*     */ 
/* 557 */     for (long i = this.size; i-- != 0L; ) {
/* 558 */       while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 559 */       long k = key[base][displ];
/* 560 */       long h = HashCommon.murmurHash3(k);
/*     */ 
/* 562 */       int d = (int)(h & newSegmentMask);
/* 563 */       int b = (int)((h & newMask) >>> 27);
/* 564 */       while (newUsed[b][d] != 0) b = b + ((d = d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask;
/* 565 */       newUsed[b][d] = 1;
/* 566 */       newKey[b][d] = k;
/* 567 */       base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/*     */     }
/* 569 */     this.n = newN;
/* 570 */     this.key = newKey;
/* 571 */     this.used = newUsed;
/* 572 */     initMasks();
/* 573 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/*     */   }
/*     */   @Deprecated
/*     */   public int size() {
/* 577 */     return (int)Math.min(2147483647L, this.size);
/*     */   }
/*     */   public long size64() {
/* 580 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 583 */     return this.size == 0L;
/*     */   }
/*     */ 
/*     */   public LongOpenHashBigSet clone()
/*     */   {
/*     */     LongOpenHashBigSet c;
/*     */     try
/*     */     {
/* 596 */       c = (LongOpenHashBigSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 599 */       throw new InternalError();
/*     */     }
/* 601 */     c.key = LongBigArrays.copy(this.key);
/* 602 */     c.used = BooleanBigArrays.copy(this.used);
/* 603 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 615 */     boolean[][] used = this.used;
/* 616 */     long[][] key = this.key;
/* 617 */     int h = 0;
/* 618 */     int base = 0; int displ = 0;
/* 619 */     for (long j = this.size; j-- != 0L; ) {
/* 620 */       while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 621 */       h += HashCommon.long2int(key[base][displ]);
/* 622 */       base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/*     */     }
/* 624 */     return h; } 
/* 627 */   private void writeObject(ObjectOutputStream s) throws IOException { LongIterator i = iterator();
/* 628 */     s.defaultWriteObject();
/* 629 */     for (long j = this.size; j-- != 0L; s.writeLong(i.nextLong()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 633 */     s.defaultReadObject();
/* 634 */     this.n = HashCommon.bigArraySize(this.size, this.f);
/* 635 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 636 */     long[][] key = this.key = LongBigArrays.newBigArray(this.n);
/* 637 */     boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.n);
/* 638 */     initMasks();
/*     */ 
/* 642 */     for (long i = this.size; i-- != 0L; ) {
/* 643 */       long k = s.readLong();
/* 644 */       long h = HashCommon.murmurHash3(k);
/* 645 */       int base = (int)((h & this.mask) >>> 27);
/* 646 */       int displ = (int)(h & this.segmentMask);
/* 647 */       while (used[base][displ] != 0) base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 648 */       used[base][displ] = 1;
/* 649 */       key[base][displ] = k;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractLongIterator
/*     */   {
/*     */     int base;
/*     */     int displ;
/*     */     int lastBase;
/*     */     int lastDispl;
/*     */     long c;
/*     */     LongArrayList wrapped;
/*     */ 
/*     */     private SetIterator()
/*     */     {
/* 385 */       this.c = LongOpenHashBigSet.this.size;
/*     */ 
/* 390 */       this.base = LongOpenHashBigSet.this.key.length;
/* 391 */       this.lastBase = -1;
/* 392 */       boolean[][] used = LongOpenHashBigSet.this.used;
/* 393 */       if (this.c != 0L)
/*     */         do if (this.displ-- == 0) {
/* 395 */             this.base -= 1;
/* 396 */             this.displ = ((int)LongOpenHashBigSet.this.mask);
/*     */           }
/* 398 */         while (used[this.base][this.displ] == 0); 
/*     */     }
/*     */ 
/* 401 */     public boolean hasNext() { return this.c != 0L; }
/*     */ 
/*     */     public long nextLong() {
/* 404 */       if (!hasNext()) throw new NoSuchElementException();
/* 405 */       this.c -= 1L;
/*     */ 
/* 407 */       if (this.base < 0) return this.wrapped.getLong(-(this.lastBase = --this.base) - 2);
/* 408 */       long retVal = LongOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
/* 409 */       if (this.c != 0L) {
/* 410 */         boolean[][] used = LongOpenHashBigSet.this.used;
/*     */         do
/* 412 */           if (this.displ-- == 0) {
/* 413 */             if (this.base-- == 0) break;
/* 414 */             this.displ = ((int)LongOpenHashBigSet.this.mask);
/*     */           }
/* 416 */         while (used[this.base][this.displ] == 0);
/*     */       }
/*     */ 
/* 419 */       return retVal;
/*     */     }
/*     */ 
/*     */     protected final long shiftKeys(long pos)
/*     */     {
/*     */       long last;
/*     */       while (true)
/*     */       {
/* 436 */         pos = (last = pos) + 1L & LongOpenHashBigSet.this.mask;
/* 437 */         while (BooleanBigArrays.get(LongOpenHashBigSet.this.used, pos)) {
/* 438 */           long slot = HashCommon.murmurHash3(LongBigArrays.get(LongOpenHashBigSet.this.key, pos)) & LongOpenHashBigSet.this.mask;
/* 439 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 440 */           pos = pos + 1L & LongOpenHashBigSet.this.mask;
/*     */         }
/* 442 */         if (!BooleanBigArrays.get(LongOpenHashBigSet.this.used, pos)) break;
/* 443 */         if (pos < last)
/*     */         {
/* 445 */           if (this.wrapped == null) this.wrapped = new LongArrayList();
/* 446 */           this.wrapped.add(LongBigArrays.get(LongOpenHashBigSet.this.key, pos));
/*     */         }
/* 448 */         LongBigArrays.set(LongOpenHashBigSet.this.key, last, LongBigArrays.get(LongOpenHashBigSet.this.key, pos));
/*     */       }
/* 450 */       BooleanBigArrays.set(LongOpenHashBigSet.this.used, last, false);
/* 451 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 455 */       if (this.lastBase == -1) throw new IllegalStateException();
/* 456 */       if (this.base < -1)
/*     */       {
/* 458 */         LongOpenHashBigSet.this.remove(this.wrapped.getLong(-this.base - 2));
/* 459 */         this.lastBase = -1;
/* 460 */         return;
/*     */       }
/* 462 */       LongOpenHashBigSet.this.size -= 1L;
/* 463 */       if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.c > 0L)) {
/* 464 */         this.c += 1L;
/* 465 */         nextLong();
/*     */       }
/* 467 */       this.lastBase = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongOpenHashBigSet
 * JD-Core Version:    0.6.2
 */