/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.LongArrays;
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
/*     */ public class IntArrayFrontCodedList extends AbstractObjectList<int[]>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   public static final long serialVersionUID = 1L;
/*     */   protected final int n;
/*     */   protected final int ratio;
/*     */   protected final int[][] array;
/*     */   protected transient long[] p;
/*     */ 
/*     */   public IntArrayFrontCodedList(Iterator<int[]> arrays, int ratio)
/*     */   {
/* 124 */     if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125 */     int[][] array = IntBigArrays.EMPTY_BIG_ARRAY;
/* 126 */     long[] p = LongArrays.EMPTY_ARRAY;
/* 127 */     int[][] a = new int[2][];
/* 128 */     long curSize = 0L;
/* 129 */     int n = 0; int b = 0;
/* 130 */     while (arrays.hasNext()) {
/* 131 */       a[b] = ((int[])arrays.next());
/* 132 */       int length = a[b].length;
/* 133 */       if (n % ratio == 0) {
/* 134 */         p = LongArrays.grow(p, n / ratio + 1);
/* 135 */         p[(n / ratio)] = curSize;
/* 136 */         array = IntBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137 */         curSize += writeInt(array, length, curSize);
/* 138 */         IntBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139 */         curSize += length;
/*     */       }
/*     */       else {
/* 142 */         int minLength = a[(1 - b)].length;
/* 143 */         if (length < minLength) minLength = length;
/* 144 */         for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++);
/* 145 */         length -= common;
/* 146 */         array = IntBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147 */         curSize += writeInt(array, length, curSize);
/* 148 */         curSize += writeInt(array, common, curSize);
/* 149 */         IntBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150 */         curSize += length;
/*     */       }
/* 152 */       b = 1 - b;
/* 153 */       n++;
/*     */     }
/* 155 */     this.n = n;
/* 156 */     this.ratio = ratio;
/* 157 */     this.array = IntBigArrays.trim(array, curSize);
/* 158 */     this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/*     */   }
/*     */ 
/*     */   public IntArrayFrontCodedList(Collection<int[]> c, int ratio)
/*     */   {
/* 166 */     this(c.iterator(), ratio);
/*     */   }
/*     */ 
/*     */   private static int readInt(int[][] a, long pos)
/*     */   {
/* 177 */     return IntBigArrays.get(a, pos);
/*     */   }
/*     */ 
/*     */   private static int count(int length)
/*     */   {
/* 185 */     return 1;
/*     */   }
/*     */ 
/*     */   private static int writeInt(int[][] a, int length, long pos)
/*     */   {
/* 194 */     IntBigArrays.set(a, pos, length);
/* 195 */     return 1;
/*     */   }
/*     */ 
/*     */   public int ratio()
/*     */   {
/* 202 */     return this.ratio;
/*     */   }
/*     */ 
/*     */   private int length(int index)
/*     */   {
/* 212 */     int[][] array = this.array;
/* 213 */     int delta = index % this.ratio;
/* 214 */     long pos = this.p[(index / this.ratio)];
/* 215 */     int length = readInt(array, pos);
/* 216 */     if (delta == 0) return length;
/*     */ 
/* 219 */     pos += count(length) + length;
/* 220 */     length = readInt(array, pos);
/* 221 */     int common = readInt(array, pos + count(length));
/* 222 */     for (int i = 0; i < delta - 1; i++) {
/* 223 */       pos += count(length) + count(common) + length;
/* 224 */       length = readInt(array, pos);
/* 225 */       common = readInt(array, pos + count(length));
/*     */     }
/* 227 */     return length + common;
/*     */   }
/*     */ 
/*     */   public int arrayLength(int index)
/*     */   {
/* 235 */     ensureRestrictedIndex(index);
/* 236 */     return length(index);
/*     */   }
/*     */ 
/*     */   private int extract(int index, int[] a, int offset, int length)
/*     */   {
/* 247 */     int delta = index % this.ratio;
/* 248 */     long startPos = this.p[(index / this.ratio)];
/*     */     long pos;
/* 250 */     int arrayLength = readInt(this.array, pos = startPos); int currLen = 0;
/* 251 */     if (delta == 0) {
/* 252 */       pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 253 */       IntBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 254 */       return arrayLength;
/*     */     }
/* 256 */     int common = 0;
/* 257 */     for (int i = 0; i < delta; i++) {
/* 258 */       long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 259 */       pos = prevArrayPos + arrayLength;
/* 260 */       arrayLength = readInt(this.array, pos);
/* 261 */       common = readInt(this.array, pos + count(arrayLength));
/* 262 */       int actualCommon = Math.min(common, length);
/* 263 */       if (actualCommon <= currLen) { currLen = actualCommon;
/*     */       } else {
/* 265 */         IntBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 266 */         currLen = actualCommon;
/*     */       }
/*     */     }
/* 269 */     if (currLen < length) IntBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 270 */     return arrayLength + common;
/*     */   }
/*     */   public int[] get(int index) {
/* 273 */     return getArray(index);
/*     */   }
/*     */ 
/*     */   public int[] getArray(int index)
/*     */   {
/* 279 */     ensureRestrictedIndex(index);
/* 280 */     int length = length(index);
/* 281 */     int[] a = new int[length];
/* 282 */     extract(index, a, 0, length);
/* 283 */     return a;
/*     */   }
/*     */ 
/*     */   public int get(int index, int[] a, int offset, int length)
/*     */   {
/* 295 */     ensureRestrictedIndex(index);
/* 296 */     IntArrays.ensureOffsetLength(a, offset, length);
/* 297 */     int arrayLength = extract(index, a, offset, length);
/* 298 */     if (length >= arrayLength) return arrayLength;
/* 299 */     return length - arrayLength;
/*     */   }
/*     */ 
/*     */   public int get(int index, int[] a)
/*     */   {
/* 309 */     return get(index, a, 0, a.length);
/*     */   }
/*     */   public int size() {
/* 312 */     return this.n;
/*     */   }
/*     */   public ObjectListIterator<int[]> listIterator(final int start) {
/* 315 */     ensureIndex(start);
/* 316 */     return new AbstractObjectListIterator() {
/* 317 */       int[] s = IntArrays.EMPTY_ARRAY;
/* 318 */       int i = 0;
/* 319 */       long pos = 0L;
/*     */       boolean inSync;
/*     */ 
/*     */       public boolean hasNext()
/*     */       {
/* 333 */         return this.i < IntArrayFrontCodedList.this.n;
/*     */       }
/*     */       public boolean hasPrevious() {
/* 336 */         return this.i > 0;
/*     */       }
/*     */       public int previousIndex() {
/* 339 */         return this.i - 1;
/*     */       }
/*     */       public int nextIndex() {
/* 342 */         return this.i;
/*     */       }
/*     */ 
/*     */       public int[] next() {
/* 346 */         if (!hasNext()) throw new NoSuchElementException();
/*     */         int length;
/* 347 */         if (this.i % IntArrayFrontCodedList.this.ratio == 0) {
/* 348 */           this.pos = IntArrayFrontCodedList.this.p[(this.i / IntArrayFrontCodedList.this.ratio)];
/* 349 */           int length = IntArrayFrontCodedList.readInt(IntArrayFrontCodedList.this.array, this.pos);
/* 350 */           this.s = IntArrays.ensureCapacity(this.s, length, 0);
/* 351 */           IntBigArrays.copyFromBig(IntArrayFrontCodedList.this.array, this.pos + IntArrayFrontCodedList.count(length), this.s, 0, length);
/* 352 */           this.pos += length + IntArrayFrontCodedList.count(length);
/* 353 */           this.inSync = true;
/*     */         }
/* 356 */         else if (this.inSync) {
/* 357 */           int length = IntArrayFrontCodedList.readInt(IntArrayFrontCodedList.this.array, this.pos);
/* 358 */           int common = IntArrayFrontCodedList.readInt(IntArrayFrontCodedList.this.array, this.pos + IntArrayFrontCodedList.access$100(length));
/* 359 */           this.s = IntArrays.ensureCapacity(this.s, length + common, common);
/* 360 */           IntBigArrays.copyFromBig(IntArrayFrontCodedList.this.array, this.pos + IntArrayFrontCodedList.count(length) + IntArrayFrontCodedList.count(common), this.s, common, length);
/* 361 */           this.pos += IntArrayFrontCodedList.count(length) + IntArrayFrontCodedList.count(common) + length;
/* 362 */           length += common;
/*     */         }
/*     */         else {
/* 365 */           this.s = IntArrays.ensureCapacity(this.s, length = IntArrayFrontCodedList.this.length(this.i), 0);
/* 366 */           IntArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/*     */         }
/*     */ 
/* 369 */         this.i += 1;
/* 370 */         return IntArrays.copy(this.s, 0, length);
/*     */       }
/*     */       public int[] previous() {
/* 373 */         if (!hasPrevious()) throw new NoSuchElementException();
/* 374 */         this.inSync = false;
/* 375 */         return IntArrayFrontCodedList.this.getArray(--this.i);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public IntArrayFrontCodedList clone()
/*     */   {
/* 384 */     return this;
/*     */   }
/*     */   public String toString() {
/* 387 */     StringBuffer s = new StringBuffer();
/* 388 */     s.append("[ ");
/* 389 */     for (int i = 0; i < this.n; i++) {
/* 390 */       if (i != 0) s.append(", ");
/* 391 */       s.append(IntArrayList.wrap(getArray(i)).toString());
/*     */     }
/* 393 */     s.append(" ]");
/* 394 */     return s.toString();
/*     */   }
/*     */ 
/*     */   protected long[] rebuildPointerArray()
/*     */   {
/* 401 */     long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 402 */     int[][] a = this.array;
/*     */ 
/* 404 */     long pos = 0L;
/* 405 */     int i = 0; int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 406 */       int length = readInt(a, pos);
/* 407 */       int count = count(length);
/* 408 */       skip++; if (skip == this.ratio) {
/* 409 */         skip = 0;
/* 410 */         p[(j++)] = pos;
/* 411 */         pos += count + length;
/*     */       } else {
/* 413 */         pos += count + count(readInt(a, pos + count)) + length;
/*     */       }
/*     */     }
/* 415 */     return p;
/*     */   }
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 418 */     s.defaultReadObject();
/*     */ 
/* 420 */     this.p = rebuildPointerArray();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntArrayFrontCodedList
 * JD-Core Version:    0.6.2
 */