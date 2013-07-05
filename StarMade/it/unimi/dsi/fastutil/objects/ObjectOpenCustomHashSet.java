/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import it.unimi.dsi.fastutil.Hash.Strategy;
/*     */ import it.unimi.dsi.fastutil.HashCommon;
/*     */ import it.unimi.dsi.fastutil.booleans.BooleanArrays;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class ObjectOpenCustomHashSet<K> extends AbstractObjectSet<K>
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
/*     */   protected Hash.Strategy<K> strategy;
/*     */ 
/*     */   public ObjectOpenCustomHashSet(int expected, float f, Hash.Strategy<K> strategy)
/*     */   {
/*  96 */     this.strategy = strategy;
/*  97 */     if ((f <= 0.0F) || (f > 1.0F)) throw new IllegalArgumentException("Load factor must be greater than 0 and smaller than or equal to 1");
/*  98 */     if (expected < 0) throw new IllegalArgumentException("The expected number of elements must be nonnegative");
/*  99 */     this.f = f;
/* 100 */     this.n = HashCommon.arraySize(expected, f);
/* 101 */     this.mask = (this.n - 1);
/* 102 */     this.maxFill = HashCommon.maxFill(this.n, f);
/* 103 */     this.key = ((Object[])new Object[this.n]);
/* 104 */     this.used = new boolean[this.n];
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(int expected, Hash.Strategy<K> strategy)
/*     */   {
/* 112 */     this(expected, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(Hash.Strategy<K> strategy)
/*     */   {
/* 119 */     this(16, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(Collection<? extends K> c, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 128 */     this(c.size(), f, strategy);
/* 129 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(Collection<? extends K> c, Hash.Strategy<K> strategy)
/*     */   {
/* 138 */     this(c, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(ObjectCollection<? extends K> c, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 147 */     this(c.size(), f, strategy);
/* 148 */     addAll(c);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(ObjectCollection<? extends K> c, Hash.Strategy<K> strategy)
/*     */   {
/* 157 */     this(c, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(ObjectIterator<K> i, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 166 */     this(16, f, strategy);
/* 167 */     while (i.hasNext()) add(i.next());
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(ObjectIterator<K> i, Hash.Strategy<K> strategy)
/*     */   {
/* 175 */     this(i, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(K[] a, int offset, int length, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 186 */     this(length < 0 ? 0 : length, f, strategy);
/* 187 */     ObjectArrays.ensureOffsetLength(a, offset, length);
/* 188 */     for (int i = 0; i < length; i++) add(a[(offset + i)]);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(K[] a, int offset, int length, Hash.Strategy<K> strategy)
/*     */   {
/* 198 */     this(a, offset, length, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(K[] a, float f, Hash.Strategy<K> strategy)
/*     */   {
/* 207 */     this(a, 0, a.length, f, strategy);
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet(K[] a, Hash.Strategy<K> strategy)
/*     */   {
/* 216 */     this(a, 0.75F, strategy);
/*     */   }
/*     */ 
/*     */   public Hash.Strategy<K> strategy()
/*     */   {
/* 223 */     return this.strategy;
/*     */   }
/*     */ 
/*     */   public boolean add(K k)
/*     */   {
/* 231 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 233 */     while (this.used[pos] != 0) {
/* 234 */       if (this.strategy.equals(this.key[pos], k)) return false;
/* 235 */       pos = pos + 1 & this.mask;
/*     */     }
/* 237 */     this.used[pos] = true;
/* 238 */     this.key[pos] = k;
/* 239 */     if (++this.size >= this.maxFill) rehash(HashCommon.arraySize(this.size + 1, this.f));
/*     */ 
/* 241 */     return true;
/*     */   }
/*     */ 
/*     */   protected final int shiftKeys(int pos)
/*     */   {
/*     */     int last;
/*     */     while (true)
/*     */     {
/* 253 */       pos = (last = pos) + 1 & this.mask;
/* 254 */       while (this.used[pos] != 0) {
/* 255 */         int slot = HashCommon.murmurHash3(this.strategy.hashCode(this.key[pos])) & this.mask;
/* 256 */         if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 257 */         pos = pos + 1 & this.mask;
/*     */       }
/* 259 */       if (this.used[pos] == 0) break;
/* 260 */       this.key[last] = this.key[pos];
/*     */     }
/* 262 */     this.used[last] = false;
/* 263 */     this.key[last] = null;
/* 264 */     return last;
/*     */   }
/*     */ 
/*     */   public boolean remove(Object k)
/*     */   {
/* 269 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 271 */     while (this.used[pos] != 0) {
/* 272 */       if (this.strategy.equals(this.key[pos], k)) {
/* 273 */         this.size -= 1;
/* 274 */         shiftKeys(pos);
/*     */ 
/* 276 */         return true;
/*     */       }
/* 278 */       pos = pos + 1 & this.mask;
/*     */     }
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(Object k)
/*     */   {
/* 285 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 287 */     while (this.used[pos] != 0) {
/* 288 */       if (this.strategy.equals(this.key[pos], k)) return true;
/* 289 */       pos = pos + 1 & this.mask;
/*     */     }
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */   public K get(Object k)
/*     */   {
/* 299 */     int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/*     */ 
/* 301 */     while (this.used[pos] != 0) {
/* 302 */       if (this.strategy.equals(this.key[pos], k)) return this.key[pos];
/* 303 */       pos = pos + 1 & this.mask;
/*     */     }
/* 305 */     return null;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 314 */     if (this.size == 0) return;
/* 315 */     this.size = 0;
/* 316 */     BooleanArrays.fill(this.used, false);
/* 317 */     ObjectArrays.fill(this.key, null);
/*     */   }
/*     */   public int size() {
/* 320 */     return this.size;
/*     */   }
/*     */   public boolean isEmpty() {
/* 323 */     return this.size == 0;
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
/* 340 */     return 16;
/*     */   }
/*     */ 
/*     */   public ObjectIterator<K> iterator()
/*     */   {
/* 424 */     return new SetIterator(null);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean rehash()
/*     */   {
/* 438 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean trim()
/*     */   {
/* 453 */     int l = HashCommon.arraySize(this.size, this.f);
/* 454 */     if (l >= this.n) return true; try
/*     */     {
/* 456 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 458 */       return false;
/* 459 */     }return true;
/*     */   }
/*     */ 
/*     */   public boolean trim(int n)
/*     */   {
/* 480 */     int l = HashCommon.nextPowerOfTwo((int)Math.ceil(n / this.f));
/* 481 */     if (this.n <= l) return true; try
/*     */     {
/* 483 */       rehash(l);
/*     */     } catch (OutOfMemoryError cantDoIt) {
/* 485 */       return false;
/* 486 */     }return true;
/*     */   }
/*     */ 
/*     */   protected void rehash(int newN)
/*     */   {
/* 499 */     int i = 0;
/* 500 */     boolean[] used = this.used;
/*     */ 
/* 502 */     Object[] key = this.key;
/* 503 */     int newMask = newN - 1;
/* 504 */     Object[] newKey = (Object[])new Object[newN];
/* 505 */     boolean[] newUsed = new boolean[newN];
/* 506 */     for (int j = this.size; j-- != 0; ) {
/* 507 */       while (used[i] == 0) i++;
/* 508 */       Object k = key[i];
/* 509 */       int pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & newMask;
/* 510 */       while (newUsed[pos] != 0) pos = pos + 1 & newMask;
/* 511 */       newUsed[pos] = true;
/* 512 */       newKey[pos] = k;
/* 513 */       i++;
/*     */     }
/* 515 */     this.n = newN;
/* 516 */     this.mask = newMask;
/* 517 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 518 */     this.key = newKey;
/* 519 */     this.used = newUsed;
/*     */   }
/*     */ 
/*     */   public ObjectOpenCustomHashSet<K> clone()
/*     */   {
/*     */     ObjectOpenCustomHashSet c;
/*     */     try
/*     */     {
/* 532 */       c = (ObjectOpenCustomHashSet)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException cantHappen) {
/* 535 */       throw new InternalError();
/*     */     }
/* 537 */     c.key = ((Object[])this.key.clone());
/* 538 */     c.used = ((boolean[])this.used.clone());
/* 539 */     c.strategy = this.strategy;
/* 540 */     return c;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 552 */     int h = 0; int i = 0; int j = this.size;
/* 553 */     while (j-- != 0) {
/* 554 */       while (this.used[i] == 0) i++;
/* 555 */       if (this != this.key[i])
/* 556 */         h += this.strategy.hashCode(this.key[i]);
/* 557 */       i++;
/*     */     }
/* 559 */     return h; } 
/* 562 */   private void writeObject(ObjectOutputStream s) throws IOException { ObjectIterator i = iterator();
/* 563 */     s.defaultWriteObject();
/* 564 */     for (int j = this.size; j-- != 0; s.writeObject(i.next()));
/*     */   }
/*     */ 
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 568 */     s.defaultReadObject();
/* 569 */     this.n = HashCommon.arraySize(this.size, this.f);
/* 570 */     this.maxFill = HashCommon.maxFill(this.n, this.f);
/* 571 */     this.mask = (this.n - 1);
/* 572 */     Object[] key = this.key = (Object[])new Object[this.n];
/* 573 */     boolean[] used = this.used = new boolean[this.n];
/*     */ 
/* 575 */     int i = this.size; for (int pos = 0; i-- != 0; ) {
/* 576 */       Object k = s.readObject();
/* 577 */       pos = HashCommon.murmurHash3(this.strategy.hashCode(k)) & this.mask;
/* 578 */       while (used[pos] != 0) pos = pos + 1 & this.mask;
/* 579 */       used[pos] = true;
/* 580 */       key[pos] = k;
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
/* 346 */       this.pos = ObjectOpenCustomHashSet.this.n;
/*     */ 
/* 349 */       this.last = -1;
/*     */ 
/* 351 */       this.c = ObjectOpenCustomHashSet.this.size;
/*     */ 
/* 356 */       boolean[] used = ObjectOpenCustomHashSet.this.used;
/* 357 */       while ((this.c != 0) && (used[(--this.pos)] == 0));
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 360 */       return this.c != 0;
/*     */     }
/*     */     public K next() {
/* 363 */       if (!hasNext()) throw new NoSuchElementException();
/* 364 */       this.c -= 1;
/*     */ 
/* 366 */       if (this.pos < 0) return this.wrapped.get(-(this.last = --this.pos) - 2);
/* 367 */       Object retVal = ObjectOpenCustomHashSet.this.key[(this.last = this.pos)];
/*     */ 
/* 369 */       if (this.c != 0) {
/* 370 */         boolean[] used = ObjectOpenCustomHashSet.this.used;
/* 371 */         while ((this.pos-- != 0) && (used[this.pos] == 0));
/*     */       }
/* 374 */       return retVal;
/*     */     }
/*     */ 
/*     */     final int shiftKeys(int pos)
/*     */     {
/*     */       int last;
/*     */       while (true)
/*     */       {
/* 387 */         pos = (last = pos) + 1 & ObjectOpenCustomHashSet.this.mask;
/* 388 */         while (ObjectOpenCustomHashSet.this.used[pos] != 0) {
/* 389 */           int slot = HashCommon.murmurHash3(ObjectOpenCustomHashSet.this.strategy.hashCode(ObjectOpenCustomHashSet.this.key[pos])) & ObjectOpenCustomHashSet.this.mask;
/* 390 */           if (last <= pos ? (last < slot) && (slot <= pos) : (last >= slot) && (slot > pos)) break;
/* 391 */           pos = pos + 1 & ObjectOpenCustomHashSet.this.mask;
/*     */         }
/* 393 */         if (ObjectOpenCustomHashSet.this.used[pos] == 0) break;
/* 394 */         if (pos < last)
/*     */         {
/* 396 */           if (this.wrapped == null) this.wrapped = new ObjectArrayList();
/* 397 */           this.wrapped.add(ObjectOpenCustomHashSet.this.key[pos]);
/*     */         }
/* 399 */         ObjectOpenCustomHashSet.this.key[last] = ObjectOpenCustomHashSet.this.key[pos];
/*     */       }
/* 401 */       ObjectOpenCustomHashSet.this.used[last] = false;
/* 402 */       ObjectOpenCustomHashSet.this.key[last] = null;
/* 403 */       return last;
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 407 */       if (this.last == -1) throw new IllegalStateException();
/* 408 */       if (this.pos < -1)
/*     */       {
/* 410 */         ObjectOpenCustomHashSet.this.remove(this.wrapped.set(-this.pos - 2, null));
/* 411 */         this.last = -1;
/* 412 */         return;
/*     */       }
/* 414 */       ObjectOpenCustomHashSet.this.size -= 1;
/* 415 */       if ((shiftKeys(this.last) == this.pos) && (this.c > 0)) {
/* 416 */         this.c += 1;
/* 417 */         next();
/*     */       }
/* 419 */       this.last = -1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet
 * JD-Core Version:    0.6.2
 */