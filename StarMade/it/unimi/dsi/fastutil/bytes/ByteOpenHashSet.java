/*     */ package it.unimi.dsi.fastutil.bytes;
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
/*     */ public class ByteOpenHashSet extends AbstractByteSet
/*     */   implements Serializable, Cloneable, Hash
/*     */ {
/*     */   public static final long serialVersionUID = 0L;
/*     */   private static final boolean ASSERTS = false;
/*     */   protected transient byte[] key;
/*     */   protected transient boolean[] used;
/*     */   protected final float f;
/*     */   protected transient int n;
/*     */   protected transient int maxFill;
/*     */   protected transient int mask;
/*     */   protected int size;
/*     */ 
/*     */   public ByteOpenHashSet(int expected, float f)
/*     */   {
/*  94 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  95 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  96 */     this.f = f;
/*  97 */     this.n = HashCommon.arraySize(expected, f);
/*  98 */     this.mask = (this.n - 1);
/*  99 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 100 */     this.key = new byte[this.n];
/* 101 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(int expected)
/*     */   {
/* 108 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet()
/*     */   {
/* 114 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(Collection<? extends Byte> c, float f)
/*     */   {
/* 122 */     this(c.size(), f);
/* 123 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(Collection<? extends Byte> c)
/*     */   {
/* 131 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(ByteCollection c, float f)
/*     */   {
/* 139 */     this(c.size(), f);
/* 140 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(ByteCollection c)
/*     */   {
/* 148 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(ByteIterator i, float f)
/*     */   {
/* 156 */     this(16, f);
/* 157 */     while (i.hasNext()) add(i.nextByte());
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(ByteIterator i)
/*     */   {
/* 164 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(Iterator<?> i, float f)
/*     */   {
/* 172 */     this(ByteIterators.asByteIterator(i), f);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(Iterator<?> i)
/*     */   {
/* 179 */     this(ByteIterators.asByteIterator(i));
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(byte[] a, int offset, int length, float f)
/*     */   {
/* 189 */     this(length < 0 ? 0 : length, f);
/* 190 */     ByteArrays.ensureOffsetLength(a, offset, length);
/* 191 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(byte[] a, int offset, int length)
/*     */   {
/* 200 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(byte[] a, float f)
/*     */   {
/* 208 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet(byte[] a)
/*     */   {
/* 216 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(byte k)
/*     */   {
/* 224 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 226 */     while (this.used[pos] != 0) {
/* 227 */       if (this.key[pos] == k) return false;
/* 228 */       pos = pos + 1 & this.mask;
/*     */     }
/* 230 */     this.used[pos] = true;
/* 231 */     this.key[pos] = k;
/* 232 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 234 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 246 */       pos = (last = pos) + 1 & this.mask;
/* 247 */       while (this.used[pos] != 0) {
/* 248 */         int slot = HashCommon.murmurHash3(this.key[pos]) & this.mask;
/* 249 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 250 */         pos = pos + 1 & this.mask;
/*     */       }
/* 252 */       if (this.used[pos] == 0) break;
/* 253 */       this.key[last] = this.key[pos];
/*     */     }
/* 255 */     this.used[last] = false;
/* 256 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(byte k)
/*     */   {
/* 261 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 263 */     while (this.used[pos] != 0) {
/* 264 */       if (this.key[pos] == k) {
/* 265 */         this.size -= 1;
/* 266 */         shiftKeys(pos);
/*     */ 
/* 268 */         return true;
/*     */       }
/* 270 */       pos = pos + 1 & this.mask;
/*     */     }
/* 272 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(byte k)
/*     */   {
/* 277 */     int pos = HashCommon.murmurHash3(k) & this.mask;
/*     */ 
/* 279 */     while (this.used[pos] != 0) {
/* 280 */       if (this.key[pos] == k) return true;
/* 281 */       pos = pos + 1 & this.mask;
/*     */     }
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 292 */     if (this.size == 0) return;
/* 293 */     this.size = 0;
/* 294 */     BooleanArrays.fill(this.used, false);
/*     */   }
/*     */   public int size() {
/* 297 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 300 */     return this.size == 0;
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
/* 317 */     return 16;
/*     */   }
/*     */ 
/*     */   public ByteIterator iterator()
/*     */   {
/* 400 */     return new SetIterator(null);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 414 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 429 */     int l = HashCommon.arraySize(this.size, this.f);
/* 430 */     if (l >= this.n) return true; try
/*     */     {
/* 432 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 434 */       return false;
/* 435 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 456 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 457 */     if (this.n <= l) return true; try
/*     */     {
/* 459 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 461 */       return false;
/* 462 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 475 */     int i = 0;
/* 476 */     boolean[] used = this.used;
/*     */ 
/* 478 */     byte[] key = this.key;
/* 479 */     int newMask = newN - 1;
/* 480 */     byte[] newKey = new byte[newN];
/* 481 */     boolean[] newUsed = new boolean[newN];
/* 482 */     for (int j = this.size; j-- != 0; ) {
/* 483 */       while (used[i] == 0) i++;
/* 484 */       byte k = key[i];
/* 485 */       int pos = HashCommon.murmurHash3(k) & newMask;
/* 486 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 487 */       newUsed[pos] = true;
/* 488 */       newKey[pos] = k;
/* 489 */       i++;
/*     */     }
/* 491 */     this.n = newN;
/* 492 */     this.mask = newMask;
/* 493 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 494 */     this.key = newKey;
/* 495 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public ByteOpenHashSet clone()
/*     */   {
/*     */     ByteOpenHashSet c;
/*     */     try
/*     */     {
/* 508 */       c = (ByteOpenHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 511 */       throw new InternalError();
/*     */     }
/* 513 */     c.key = ((byte[])this.key.clone());
/* 514 */     c.used = ((boolean[])this.used.clone());
/* 515 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 527 */     int h = 0; int i = 0; int j = this.size;
/* 528 */     while (j-- != 0) {
/* 529 */       while (this.used[i] == 0) i++;
/* 530 */       h += this.key[i];
/* 531 */       i++;
/*     */     }
/* 533 */     return h; } 
/* 536 */   private void writeObject(ObjectOutputStream s) throws IOException { ByteIterator i = iterator();
/* 537 */     s.defaultWriteObject();
/* 538 */     for (int j = this.size; j-- != 0; s.writeByte(i.nextByte()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 542 */     s.defaultReadObject();
/* 543 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 544 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 545 */     this.mask = (this.n - 1);
/* 546 */     byte[] key = this.key = new byte[this.n];
/* 547 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 549 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 550 */       byte k = s.readByte();
/* 551 */       pos = HashCommon.murmurHash3(k) & this.mask;
/* 552 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 553 */       used[pos] = true;
/* 554 */       key[pos] = k;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void checkTable()
/*     */   {
/*     */   }
/*     */ 
/*     */   private class SetIterator extends AbstractByteIterator
/*     */   {
/*     */     int pos;
/*     */     int last;
/*     */     int c;
/*     */     ByteArrayList wrapped;
/*     */ 
/*     */     private SetIterator()
/*     */     {
/* 323 */       this.pos = ByteOpenHashSet.this.n;
/*     */ 
/* 326 */       this.last = -1;
/*     */ 
/* 328 */       this.c = ByteOpenHashSet.this.size;
/*     */ 
/* 333 */       boolean[] used = ByteOpenHashSet.this.used;
/* 334 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 337 */       return this.c != 0;
/*     */     }
/*     */     public byte nextByte() {
/* 340 */       if (!hasNext()) throw new NoSuchElementException();
/* 341 */       this.c -= 1;
/*     */ 
/* 343 */       if (this.pos < 0) return this.wrapped.getByte(-(this.last = --this.pos) - 2);
/* 344 */       byte retVal = ByteOpenHashSet.this.key[(this.last = this.pos)];
/*     */ 
/* 346 */       if (this.c != 0) {
/* 347 */         boolean[] used = ByteOpenHashSet.this.used;
/* 348 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 351 */       return retVal;
/*     */     }
/*     */ 
/*     */     final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 364 */         pos = (last = pos) + 1 & ByteOpenHashSet.this.mask;
/* 365 */         while (ByteOpenHashSet.this.used[pos] != 0) {
/* 366 */           int slot = HashCommon.murmurHash3(ByteOpenHashSet.this.key[pos]) & ByteOpenHashSet.this.mask;
/* 367 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 368 */           pos = pos + 1 & ByteOpenHashSet.this.mask;
/*     */         }
/* 370 */         if (ByteOpenHashSet.this.used[pos] == 0) break;
/* 371 */         if (pos < last)
/*     */         {
/* 373 */           if (this.wrapped == null) this.wrapped = new ByteArrayList();
/* 374 */           this.wrapped.add(ByteOpenHashSet.this.key[pos]);
/*     */         }
/* 376 */         ByteOpenHashSet.this.key[last] = ByteOpenHashSet.this.key[pos];
/*     */       }
/* 378 */       ByteOpenHashSet.this.used[last] = false;
/* 379 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 383 */       if (this.last == -1) throw new IllegalStateException();
/* 384 */       if (this.pos < -1)
/*     */       {
/* 386 */         ByteOpenHashSet.this.remove(this.wrapped.getByte(-this.pos - 2));
/* 387 */         this.last = -1;
/* 388 */         return;
/*     */       }
/* 390 */       ByteOpenHashSet.this.size -= 1;
/* 391 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 392 */         this.c += 1;
/* 393 */         nextByte();
/*     */       }
/* 395 */       this.last = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteOpenHashSet
 * JD-Core Version:    0.6.2
 */