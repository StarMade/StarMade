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
/*     */ public class ObjectOpenHashSet<K> extends AbstractObjectSet<K>
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
/*     */   public ObjectOpenHashSet(int expected, float f)
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
/*     */   public ObjectOpenHashSet(int expected)
/*     */   {
/* 106 */     this(expected, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet()
/*     */   {
/* 112 */     this(16, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(Collection<? extends K> c, float f)
/*     */   {
/* 120 */     this(c.size(), f);
/* 121 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(Collection<? extends K> c)
/*     */   {
/* 129 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(ObjectCollection<? extends K> c, float f)
/*     */   {
/* 137 */     this(c.size(), f);
/* 138 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(ObjectCollection<? extends K> c)
/*     */   {
/* 146 */     this(c, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(ObjectIterator<K> i, float f)
/*     */   {
/* 154 */     this(16, f);
/* 155 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(ObjectIterator<K> i)
/*     */   {
/* 162 */     this(i, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(K[] a, int offset, int length, float f)
/*     */   {
/* 172 */     this(length < 0 ? 0 : length, f);
/* 173 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 174 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(K[] a, int offset, int length)
/*     */   {
/* 183 */     this(a, offset, length, 0.75F);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(K[] a, float f)
/*     */   {
/* 191 */     this(a, 0, a.length, f);
/*     */   }
/*     */ 
/*     */   public ObjectOpenHashSet(K[] a)
/*     */   {
/* 199 */     this(a, 0.75F);
/*     */   }
/*     */ 
/*     */   public boolean add(K k)
/*     */   {
/* 207 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 209 */     while (this.used[pos] != 0) {
/* 210 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return false;
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
/* 231 */         int slot = (this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(this.key[pos].hashCode())) & this.mask;
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
/* 245 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 247 */     while (this.used[pos] != 0) {
/* 248 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) {
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
/* 261 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 263 */     while (this.used[pos] != 0) {
/* 264 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return true;
/* 265 */       pos = pos + 1 & this.mask;
/*     */     }
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */   public K get(Object k)
/*     */   {
/* 275 */     int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/*     */ 
/* 277 */     while (this.used[pos] != 0) {
/* 278 */       if (this.key[pos] == null ? k == null : this.key[pos].equals(k)) return this.key[pos];
/* 279 */       pos = pos + 1 & this.mask;
/*     */     }
/* 281 */     return null;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 290 */     if (this.size == 0) return;
/* 291 */     this.size = 0;
/* 292 */     BooleanArrays.fill(this.used, false);
/* 293 */     ObjectArrays.fill(this.key, null);
/*     */   }
/*     */   public int size() {
/* 296 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 299 */     return this.size == 0;
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
/* 316 */     return 16;
/*     */   }
/*     */ 
/*     */   public ObjectIterator<K> iterator()
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
/* 478 */     Object[] key = this.key;
/* 479 */     int newMask = newN - 1;
/* 480 */     Object[] newKey = (Object[])new Object[newN];
/* 481 */     boolean[] newUsed = new boolean[newN];
/* 482 */     for (int j = this.size; j-- != 0; ) {
/* 483 */       while (used[i] == 0) i++;
/* 484 */       Object k = key[i];
/* 485 */       int pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & newMask;
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
/*     */   public ObjectOpenHashSet<K> clone()
/*     */   {
/*     */     ObjectOpenHashSet c;
/*     */     try
/*     */     {
/* 508 */       c = (ObjectOpenHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 511 */       throw new InternalError();
/*     */     }
/* 513 */     c.key = ((Object[])this.key.clone());
/* 514 */     c.used = ((boolean[])this.used.clone());
/* 515 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 527 */     int h = 0; int i = 0; int j = this.size;
/* 528 */     while (j-- != 0) {
/* 529 */       while (this.used[i] == 0) i++;
/* 530 */       if (this != this.key[i])
/* 531 */         h += (this.key[i] == null ? 0 : this.key[i].hashCode());
/* 532 */       i++;
/*     */     }
/* 534 */     return h; } 
/* 537 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 538 */     s.defaultWriteObject();
/* 539 */     for (int j = this.size; j-- != 0; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 543 */     s.defaultReadObject();
/* 544 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 545 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 546 */     this.mask = (this.n - 1);
/* 547 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 548 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 550 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 551 */       Object k = s.readObject();
/* 552 */       pos = (k == null ? 142593372 : HashCommon.murmurHash3(k.hashCode())) & this.mask;
/* 553 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 554 */       used[pos] = true;
/* 555 */       key[pos] = k;
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
/*     */     ObjectArrayList<K> wrapped;
/*     */ 
/*     */     private SetIterator()
/*     */     {
/* 322 */       this.pos = ObjectOpenHashSet.this.n;
/*     */ 
/* 325 */       this.last = -1;
/*     */ 
/* 327 */       this.c = ObjectOpenHashSet.this.size;
/*     */ 
/* 332 */       boolean[] used = ObjectOpenHashSet.this.used;
/* 333 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 336 */       return this.c != 0;
/*     */     }
/*     */     public K next() {
/* 339 */       if (!hasNext()) throw new NoSuchElementException();
/* 340 */       this.c -= 1;
/*     */ 
/* 342 */       if (this.pos < 0) return this.wrapped.get(-(this.last = --this.pos) - 2);
/* 343 */       Object retVal = ObjectOpenHashSet.this.key[(this.last = this.pos)];
/*     */ 
/* 345 */       if (this.c != 0) {
/* 346 */         boolean[] used = ObjectOpenHashSet.this.used;
/* 347 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 350 */       return retVal;
/*     */     }
/*     */ 
/*     */     final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 363 */         pos = (last = pos) + 1 & ObjectOpenHashSet.this.mask;
/* 364 */         while (ObjectOpenHashSet.this.used[pos] != 0) {
/* 365 */           int slot = (ObjectOpenHashSet.this.key[pos] == null ? 142593372 : HashCommon.murmurHash3(ObjectOpenHashSet.this.key[pos].hashCode())) & ObjectOpenHashSet.this.mask;
/* 366 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 367 */           pos = pos + 1 & ObjectOpenHashSet.this.mask;
/*     */         }
/* 369 */         if (ObjectOpenHashSet.this.used[pos] == 0) break;
/* 370 */         if (pos < last)
/*     */         {
/* 372 */           if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 373 */           this.wrapped.add(ObjectOpenHashSet.this.key[pos]);
/*     */         }
/* 375 */         ObjectOpenHashSet.this.key[last] = ObjectOpenHashSet.this.key[pos];
/*     */       }
/* 377 */       ObjectOpenHashSet.this.used[last] = false;
/* 378 */       ObjectOpenHashSet.this.key[last] = null;
/* 379 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 383 */       if (this.last == -1) throw new IllegalStateException();
/* 384 */       if (this.pos < -1)
/*     */       {
/* 386 */         ObjectOpenHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 387 */         this.last = -1;
/* 388 */         return;
/*     */       }
/* 390 */       ObjectOpenHashSet.this.size -= 1;
/* 391 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 392 */         this.c += 1;
/* 393 */         next();
/*     */       }
/* 395 */       this.last = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 * JD-Core Version:    0.6.2
 */