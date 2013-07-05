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
/*     */ public class ObjectOpenHashBigSet<K> extends AbstractObjectSet<K>
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
/*     */   public ObjectOpenHashBigSet(long expected, float f)
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
/*     */   public ObjectOpenHashBigSet(long expected)
/*     */   {
/* 124 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet()
/*     */   {
/* 132 */     this(16L, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(Collection<? extends K> c, float f)
/*     */   {
/* 142 */     this(c.size(), f);
/* 143 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(Collection<? extends K> c)
/*     */   {
/* 153 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(ObjectCollection<? extends K> c, float f)
/*     */   {
/* 163 */     this(c.size(), f);
/* 164 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(ObjectCollection<? extends K> c)
/*     */   {
/* 174 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(ObjectIterator<K> i, float f)
/*     */   {
/* 184 */     this(16L, f);
/* 185 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(ObjectIterator<K> i)
/*     */   {
/* 194 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(K[] a, int offset, int length, float f)
/*     */   {
/* 204 */     this(length < 0 ? 0L : length, f);
/* 205 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 206 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(K[] a, int offset, int length)
/*     */   {
/* 215 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(K[] a, float f)
/*     */   {
/* 223 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet(K[] a)
/*     */   {
/* 231 */     this(a, 0.75F);
/*     */   }
/*     */   public boolean add(K k) {
/* 234 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/*     */ 
/* 236 */     int displ = (int)(h & this.segmentMask);
/* 237 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 239 */     while (this.used[base][displ] != 0) {
/* 240 */       if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) return false;
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
/* 265 */         long slot = (ObjectBigArrays.get(this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(ObjectBigArrays.get(this.key, pos).hashCode())) & this.mask;
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
/* 278 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/*     */ 
/* 280 */     int displ = (int)(h & this.segmentMask);
/* 281 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 283 */     while (this.used[base][displ] != 0) {
/* 284 */       if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) {
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
/* 296 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/*     */ 
/* 298 */     int displ = (int)(h & this.segmentMask);
/* 299 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 301 */     while (this.used[base][displ] != 0) {
/* 302 */       if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) return true;
/* 303 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/* 305 */     return false;
/*     */   }
/*     */ 
/*     */   public K get(Object k)
/*     */   {
/* 311 */     long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/*     */ 
/* 313 */     int displ = (int)(h & this.segmentMask);
/* 314 */     int base = (int)((h & this.mask) >>> 27);
/*     */ 
/* 316 */     while (this.used[base][displ] != 0) {
/* 317 */       if (this.key[base][displ] == null ? k == null : this.key[base][displ].equals(k)) return this.key[base][displ];
/* 318 */       base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/*     */     }
/* 320 */     return null;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 329 */     if (this.size == 0L) return;
/* 330 */     this.size = 0L;
/* 331 */     BooleanBigArrays.fill(this.used, false);
/* 332 */     ObjectBigArrays.fill(this.key, null);
/*     */   }
/*     */ 
/*     */   public ObjectIterator<K> iterator()
/*     */   {
/* 436 */     return new SetIterator(null);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 450 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 465 */     long l = HashCommon.bigArraySize(this.size, this.f);
/* 466 */     if (l >= this.n) return true; try
/*     */     {
/* 468 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 470 */       return false;
/* 471 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(long n)
/*     */   {
/* 492 */     long l = HashCommon.bigArraySize(n, this.f);
/* 493 */     if (this.n <= l) return true; try
/*     */     {
/* 495 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 497 */       return false;
/* 498 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(long newN)
/*     */   {
/* 511 */     boolean[][] used = this.used;
/* 512 */     Object[][] key = this.key;
/* 513 */     boolean[][] newUsed = BooleanBigArrays.newBigArray(newN);
/* 514 */     Object[][] newKey = (Object[][])ObjectBigArrays.newBigArray(newN);
/* 515 */     long newMask = newN - 1L;
/* 516 */     int newSegmentMask = newKey[0].length - 1;
/* 517 */     int newBaseMask = newKey.length - 1;
/* 518 */     int base = 0; int displ = 0;
/*     */ 
/* 521 */     for (long i = this.size; i-- != 0L; ) {
/* 522 */       while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 523 */       Object k = key[base][displ];
/* 524 */       long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/*     */ 
/* 526 */       int d = (int)(h & newSegmentMask);
/* 527 */       int b = (int)((h & newMask) >>> 27);
/* 528 */       while (newUsed[b][d] != 0) b = b + ((d = d + 1 & newSegmentMask) == 0 ? 1 : 0) & newBaseMask;
/* 529 */       newUsed[b][d] = 1;
/* 530 */       newKey[b][d] = k;
/* 531 */       base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/*     */     }
/* 533 */     this.n = newN;
/* 534 */     this.key = newKey;
/* 535 */     this.used = newUsed;
/* 536 */     initMasks();
/* 537 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/*     */   }
/*     */   @Deprecated
/*     */   public int size() {
/* 541 */     return (int)Math.min(2147483647L, this.size);
/*     */   }
/*     */   public long size64() {
/* 544 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 547 */     return this.size == 0L;
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashBigSet<K> clone()
/*     */   {
/*     */     ObjectOpenHashBigSet c;
/*     */     try
/*     */     {
/* 560 */       c = (ObjectOpenHashBigSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 563 */       throw new InternalError();
/*     */     }
/* 565 */     c.key = ObjectBigArrays.copy(this.key);
/* 566 */     c.used = BooleanBigArrays.copy(this.used);
/* 567 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 579 */     boolean[][] used = this.used;
/* 580 */     Object[][] key = this.key;
/* 581 */     int h = 0;
/* 582 */     int base = 0; int displ = 0;
/* 583 */     for (long j = this.size; j-- != 0L; ) {
/* 584 */       while (used[base][displ] == 0) base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/* 585 */       if (this != key[base][displ])
/* 586 */         h += (key[base][displ] == null ? 0 : key[base][displ].hashCode());
/* 587 */       base += ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0);
/*     */     }
/* 589 */     return h; } 
/* 592 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 593 */     s.defaultWriteObject();
/* 594 */     for (long j = this.size; j-- != 0L; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 598 */     s.defaultReadObject();
/* 599 */     this.n = HashCommon.bigArraySize(this.size, this.f);
/* 600 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 601 */     Object[][] key = this.key = (Object[][])ObjectBigArrays.newBigArray(this.n);
/* 602 */     boolean[][] used = this.used = BooleanBigArrays.newBigArray(this.n);
/* 603 */     initMasks();
/*     */ 
/* 607 */     for (long i = this.size; i-- != 0L; ) {
/* 608 */       Object k = s.readObject();
/* 609 */       long h = k == null ? -9148929187392628276L : HashCommon.murmurHash3(k.hashCode());
/* 610 */       int base = (int)((h & this.mask) >>> 27);
/* 611 */       int displ = (int)(h & this.segmentMask);
/* 612 */       while (used[base][displ] != 0) base = base + ((displ = displ + 1 & this.segmentMask) == 0 ? 1 : 0) & this.baseMask;
/* 613 */       used[base][displ] = 1;
/* 614 */       key[base][displ] = k;
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
/*     */     ObjectArrayList<K> wrapped;
/*     */ 
/*     */     private SetIterator()
/*     */     {
/* 348 */       this.c = ObjectOpenHashBigSet.this.size;
/*     */ 
/* 353 */       this.base = ObjectOpenHashBigSet.this.key.length;
/* 354 */       this.lastBase = -1;
/* 355 */       boolean[][] used = ObjectOpenHashBigSet.this.used;
/* 356 */       if (this.c != 0L)
/*     */         do if (this.displ-- == 0) {
/* 358 */             this.base -= 1;
/* 359 */             this.displ = ((int)ObjectOpenHashBigSet.this.mask);
/*     */           }
/* 361 */         while (used[this.base][this.displ] == 0); 
/*     */     }
/*     */ 
/* 364 */     public boolean hasNext() { return this.c != 0L; }
/*     */ 
/*     */     public K next() {
/* 367 */       if (!hasNext()) throw new NoSuchElementException();
/* 368 */       this.c -= 1L;
/*     */ 
/* 370 */       if (this.base < 0) return this.wrapped.get(-(this.lastBase = --this.base) - 2);
/* 371 */       Object retVal = ObjectOpenHashBigSet.this.key[(this.lastBase = this.base)][(this.lastDispl = this.displ)];
/* 372 */       if (this.c != 0L) {
/* 373 */         boolean[][] used = ObjectOpenHashBigSet.this.used;
/*     */         do
/* 375 */           if (this.displ-- == 0) {
/* 376 */             if (this.base-- == 0) break;
/* 377 */             this.displ = ((int)ObjectOpenHashBigSet.this.mask);
/*     */           }
/* 379 */         while (used[this.base][this.displ] == 0);
/*     */       }
/*     */ 
/* 382 */       return retVal;
/*     */     }
/*     */ 
/*     */     protected final long shiftKeys(long pos)
/*     */     {
/*     */       long last;
/*     */       while (true)
/*     */       {
/* 399 */         pos = (last = pos) + 1L & ObjectOpenHashBigSet.this.mask;
/* 400 */         while (BooleanBigArrays.get(ObjectOpenHashBigSet.this.used, pos)) {
/* 401 */           long slot = (ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos) == null ? -9148929187392628276L : HashCommon.murmurHash3(ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos).hashCode())) & ObjectOpenHashBigSet.this.mask;
/* 402 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 403 */           pos = pos + 1L & ObjectOpenHashBigSet.this.mask;
/*     */         }
/* 405 */         if (!BooleanBigArrays.get(ObjectOpenHashBigSet.this.used, pos)) break;
/* 406 */         if (pos < last)
/*     */         {
/* 408 */           if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 409 */           this.wrapped.add(ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos));
/*     */         }
/* 411 */         ObjectBigArrays.set(ObjectOpenHashBigSet.this.key, last, ObjectBigArrays.get(ObjectOpenHashBigSet.this.key, pos));
/*     */       }
/* 413 */       BooleanBigArrays.set(ObjectOpenHashBigSet.this.used, last, false);
/* 414 */       ObjectBigArrays.set(ObjectOpenHashBigSet.this.key, last, null);
/* 415 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 419 */       if (this.lastBase == -1) throw new IllegalStateException();
/* 420 */       if (this.base < -1)
/*     */       {
/* 422 */         ObjectOpenHashBigSet.this.remove(this.wrapped.set(-this.base - 2, null));
/* 423 */         this.lastBase = -1;
/* 424 */         return;
/*     */       }
/* 426 */       ObjectOpenHashBigSet.this.size -= 1L;
/* 427 */       if ((shiftKeys(this.lastBase * 134217728L + this.lastDispl) == this.base * 134217728L + this.displ) && (this.c > 0L)) {
/* 428 */         this.c += 1L;
/* 429 */         next();
/*     */       }
/* 431 */       this.lastBase = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenHashBigSet
 * JD-Core Version:    0.6.2
 */