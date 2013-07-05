/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectList;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectListIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class LongArrayFrontCodedList extends AbstractObjectList<long[]>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   public static final long serialVersionUID = 1L;
/*     */   protected final int n;
/*     */   protected final int ratio;
/*     */   protected final long[][] array;
/*     */   protected transient long[] p;
/*     */ 
/*     */   public LongArrayFrontCodedList(Iterator<long[]> arrays, int ratio)
/*     */   {
/* 124 */     if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125 */     long[][] array = LongBigArrays.EMPTY_BIG_ARRAY;
/* 126 */     long[] p = LongArrays.EMPTY_ARRAY;
/* 127 */     long[][] a = new long[2][];
/* 128 */     long curSize = 0L;
/* 129 */     int n = 0; int b = 0;
/* 130 */     while (arrays.hasNext()) {
/* 131 */       a[b] = ((long[])arrays.next());
/* 132 */       int length = a[b].length;
/* 133 */       if (n % ratio == 0) {
/* 134 */         p = LongArrays.grow(p, n / ratio + 1);
/* 135 */         p[(n / ratio)] = curSize;
/* 136 */         array = LongBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137 */         curSize += writeInt(array, length, curSize);
/* 138 */         LongBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139 */         curSize += length;
/*     */       }
/*     */       else {
/* 142 */         int minLength = a[(1 - b)].length;
/* 143 */         if (length < minLength) minLength = length;
/* 144 */         for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++);
/* 145 */         length -= common;
/* 146 */         array = LongBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147 */         curSize += writeInt(array, length, curSize);
/* 148 */         curSize += writeInt(array, common, curSize);
/* 149 */         LongBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150 */         curSize += length;
/*     */       }
/* 152 */       b = 1 - b;
/* 153 */       n++;
/*     */     }
/* 155 */     this.n = n;
/* 156 */     this.ratio = ratio;
/* 157 */     this.array = LongBigArrays.trim(array, curSize);
/* 158 */     this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/*     */   }
/*     */ 
/*     */   public LongArrayFrontCodedList(Collection<long[]> c, int ratio)
/*     */   {
/* 166 */     this(c.iterator(), ratio);
/*     */   }
/*     */ 
/*     */   private static int readInt(long[][] a, long pos)
/*     */   {
/* 179 */     return (int)LongBigArrays.get(a, pos);
/*     */   }
/*     */ 
/*     */   private static int count(int length)
/*     */   {
/* 187 */     return 1;
/*     */   }
/*     */ 
/*     */   private static int writeInt(long[][] a, int length, long pos)
/*     */   {
/* 196 */     LongBigArrays.set(a, pos, length);
/* 197 */     return 1;
/*     */   }
/*     */ 
/*     */   public int ratio()
/*     */   {
/* 204 */     return this.ratio;
/*     */   }
/*     */ 
/*     */   private int length(int index)
/*     */   {
/* 214 */     long[][] array = this.array;
/* 215 */     int delta = index % this.ratio;
/* 216 */     long pos = this.p[(index / this.ratio)];
/* 217 */     int length = readInt(array, pos);
/* 218 */     if (delta == 0) return length;
/*     */ 
/* 221 */     pos += count(length) + length;
/* 222 */     length = readInt(array, pos);
/* 223 */     int common = readInt(array, pos + count(length));
/* 224 */     for (int i = 0; i < delta - 1; i++) {
/* 225 */       pos += count(length) + count(common) + length;
/* 226 */       length = readInt(array, pos);
/* 227 */       common = readInt(array, pos + count(length));
/*     */     }
/* 229 */     return length + common;
/*     */   }
/*     */ 
/*     */   public int arrayLength(int index)
/*     */   {
/* 237 */     ensureRestrictedIndex(index);
/* 238 */     return length(index);
/*     */   }
/*     */ 
/*     */   private int extract(int index, long[] a, int offset, int length)
/*     */   {
/* 249 */     int delta = index % this.ratio;
/* 250 */     long startPos = this.p[(index / this.ratio)];
/*     */     long pos;
/* 252 */     int arrayLength = readInt(this.array, pos = startPos); int currLen = 0;
/* 253 */     if (delta == 0) {
/* 254 */       pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 255 */       LongBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 256 */       return arrayLength;
/*     */     }
/* 258 */     int common = 0;
/* 259 */     for (int i = 0; i < delta; i++) {
/* 260 */       long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 261 */       pos = prevArrayPos + arrayLength;
/* 262 */       arrayLength = readInt(this.array, pos);
/* 263 */       common = readInt(this.array, pos + count(arrayLength));
/* 264 */       int actualCommon = Math.min(common, length);
/* 265 */       if (actualCommon <= currLen) { currLen = actualCommon;
/*     */       } else {
/* 267 */         LongBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 268 */         currLen = actualCommon;
/*     */       }
/*     */     }
/* 271 */     if (currLen < length) LongBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 272 */     return arrayLength + common;
/*     */   }
/*     */   public long[] get(int index) {
/* 275 */     return getArray(index);
/*     */   }
/*     */ 
/*     */   public long[] getArray(int index)
/*     */   {
/* 281 */     ensureRestrictedIndex(index);
/* 282 */     int length = length(index);
/* 283 */     long[] a = new long[length];
/* 284 */     extract(index, a, 0, length);
/* 285 */     return a;
/*     */   }
/*     */ 
/*     */   public int get(int index, long[] a, int offset, int length)
/*     */   {
/* 297 */     ensureRestrictedIndex(index);
/* 298 */     LongArrays.ensureOffsetLength(a, offset, length);
/* 299 */     int arrayLength = extract(index, a, offset, length);
/* 300 */     if (length >= arrayLength) return arrayLength;
/* 301 */     return length - arrayLength;
/*     */   }
/*     */ 
/*     */   public int get(int index, long[] a)
/*     */   {
/* 311 */     return get(index, a, 0, a.length);
/*     */   }
/*     */   public int size() {
/* 314 */     return this.n;
/*     */   }
/*     */   public ObjectListIterator<long[]> listIterator(final int start) {
/* 317 */     ensureIndex(start);
/* 318 */     return new AbstractObjectListIterator() {
/* 319 */       long[] s = LongArrays.EMPTY_ARRAY;
/* 320 */       int i = 0;
/* 321 */       long pos = 0L;
/*     */       boolean inSync;
/*     */ 
/*     */       public boolean hasNext()
/*     */       {
/* 335 */         return this.i < LongArrayFrontCodedList.this.n;
/*     */       }
/*     */       public boolean hasPrevious() {
/* 338 */         return this.i > 0;
/*     */       }
/*     */       public int previousIndex() {
/* 341 */         return this.i - 1;
/*     */       }
/*     */       public int nextIndex() {
/* 344 */         return this.i;
/*     */       }
/*     */ 
/*     */       public long[] next() {
/* 348 */         if (!hasNext()) throw new NoSuchElementException();
/*     */         int length;
/* 349 */         if (this.i % LongArrayFrontCodedList.this.ratio == 0) {
/* 350 */           this.pos = LongArrayFrontCodedList.this.p[(this.i / LongArrayFrontCodedList.this.ratio)];
/* 351 */           int length = LongArrayFrontCodedList.readInt(LongArrayFrontCodedList.this.array, this.pos);
/* 352 */           this.s = LongArrays.ensureCapacity(this.s, length, 0);
/* 353 */           LongBigArrays.copyFromBig(LongArrayFrontCodedList.this.array, this.pos + LongArrayFrontCodedList.count(length), this.s, 0, length);
/* 354 */           this.pos += length + LongArrayFrontCodedList.count(length);
/* 355 */           this.inSync = true;
/*     */         }
/* 358 */         else if (this.inSync) {
/* 359 */           int length = LongArrayFrontCodedList.readInt(LongArrayFrontCodedList.this.array, this.pos);
/* 360 */           int common = LongArrayFrontCodedList.readInt(LongArrayFrontCodedList.this.array, this.pos + LongArrayFrontCodedList.access$100(length));
/* 361 */           this.s = LongArrays.ensureCapacity(this.s, length + common, common);
/* 362 */           LongBigArrays.copyFromBig(LongArrayFrontCodedList.this.array, this.pos + LongArrayFrontCodedList.count(length) + LongArrayFrontCodedList.count(common), this.s, common, length);
/* 363 */           this.pos += LongArrayFrontCodedList.count(length) + LongArrayFrontCodedList.count(common) + length;
/* 364 */           length += common;
/*     */         }
/*     */         else {
/* 367 */           this.s = LongArrays.ensureCapacity(this.s, length = LongArrayFrontCodedList.this.length(this.i), 0);
/* 368 */           LongArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/*     */         }
/*     */ 
/* 371 */         this.i += 1;
/* 372 */         return LongArrays.copy(this.s, 0, length);
/*     */       }
/*     */       public long[] previous() {
/* 375 */         if (!hasPrevious()) throw new NoSuchElementException();
/* 376 */         this.inSync = false;
/* 377 */         return LongArrayFrontCodedList.this.getArray(--this.i);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public LongArrayFrontCodedList clone()
/*     */   {
/* 386 */     return this;
/*     */   }
/*     */   public String toString() {
/* 389 */     StringBuffer s = new StringBuffer();
/* 390 */     s.append("[ ");
/* 391 */     for (int i = 0; i < this.n; i++) {
/* 392 */       if (i != 0) s.append(", ");
/* 393 */       s.append(LongArrayList.wrap(getArray(i)).toString());
/*     */     }
/* 395 */     s.append(" ]");
/* 396 */     return s.toString();
/*     */   }
/*     */ 
/*     */   protected long[] rebuildPointerArray()
/*     */   {
/* 403 */     long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 404 */     long[][] a = this.array;
/*     */ 
/* 406 */     long pos = 0L;
/* 407 */     int i = 0; int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 408 */       int length = readInt(a, pos);
/* 409 */       int count = count(length);
/* 410 */       skip++; if (skip == this.ratio) {
/* 411 */         skip = 0;
/* 412 */         p[(j++)] = pos;
/* 413 */         pos += count + length;
/*     */       } else {
/* 415 */         pos += count + count(readInt(a, pos + count)) + length;
/*     */       }
/*     */     }
/* 417 */     return p;
/*     */   }
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 420 */     s.defaultReadObject();
/*     */ 
/* 422 */     this.p = rebuildPointerArray();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArrayFrontCodedList
 * JD-Core Version:    0.6.2
 */