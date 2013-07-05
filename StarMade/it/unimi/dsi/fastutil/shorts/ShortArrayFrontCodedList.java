/*     */ package it.unimi.dsi.fastutil.shorts;
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
/*     */ public class ShortArrayFrontCodedList extends AbstractObjectList<short[]>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   public static final long serialVersionUID = 1L;
/*     */   protected final int n;
/*     */   protected final int ratio;
/*     */   protected final short[][] array;
/*     */   protected transient long[] p;
/*     */ 
/*     */   public ShortArrayFrontCodedList(Iterator<short[]> arrays, int ratio)
/*     */   {
/* 124 */     if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125 */     short[][] array = ShortBigArrays.EMPTY_BIG_ARRAY;
/* 126 */     long[] p = LongArrays.EMPTY_ARRAY;
/* 127 */     short[][] a = new short[2][];
/* 128 */     long curSize = 0L;
/* 129 */     int n = 0; int b = 0;
/* 130 */     while (arrays.hasNext()) {
/* 131 */       a[b] = ((short[])arrays.next());
/* 132 */       int length = a[b].length;
/* 133 */       if (n % ratio == 0) {
/* 134 */         p = LongArrays.grow(p, n / ratio + 1);
/* 135 */         p[(n / ratio)] = curSize;
/* 136 */         array = ShortBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137 */         curSize += writeInt(array, length, curSize);
/* 138 */         ShortBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139 */         curSize += length;
/*     */       }
/*     */       else {
/* 142 */         int minLength = a[(1 - b)].length;
/* 143 */         if (length < minLength) minLength = length;
/* 144 */         for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++);
/* 145 */         length -= common;
/* 146 */         array = ShortBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147 */         curSize += writeInt(array, length, curSize);
/* 148 */         curSize += writeInt(array, common, curSize);
/* 149 */         ShortBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150 */         curSize += length;
/*     */       }
/* 152 */       b = 1 - b;
/* 153 */       n++;
/*     */     }
/* 155 */     this.n = n;
/* 156 */     this.ratio = ratio;
/* 157 */     this.array = ShortBigArrays.trim(array, curSize);
/* 158 */     this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/*     */   }
/*     */ 
/*     */   public ShortArrayFrontCodedList(Collection<short[]> c, int ratio)
/*     */   {
/* 166 */     this(c.iterator(), ratio);
/*     */   }
/*     */ 
/*     */   private static int readInt(short[][] a, long pos)
/*     */   {
/* 176 */     short s0 = ShortBigArrays.get(a, pos);
/* 177 */     return s0 >= 0 ? s0 : s0 << 16 | ShortBigArrays.get(a, pos + 1L) & 0xFFFF;
/*     */   }
/*     */ 
/*     */   private static int count(int length)
/*     */   {
/* 185 */     return length < 32768 ? 1 : 2;
/*     */   }
/*     */ 
/*     */   private static int writeInt(short[][] a, int length, long pos)
/*     */   {
/* 194 */     if (length < 32768) {
/* 195 */       ShortBigArrays.set(a, pos, (short)length);
/* 196 */       return 1;
/*     */     }
/* 198 */     ShortBigArrays.set(a, pos++, (short)(-(length >>> 16) - 1));
/* 199 */     ShortBigArrays.set(a, pos, (short)(length & 0xFFFF));
/* 200 */     return 2;
/*     */   }
/*     */ 
/*     */   public int ratio()
/*     */   {
/* 207 */     return this.ratio;
/*     */   }
/*     */ 
/*     */   private int length(int index)
/*     */   {
/* 217 */     short[][] array = this.array;
/* 218 */     int delta = index % this.ratio;
/* 219 */     long pos = this.p[(index / this.ratio)];
/* 220 */     int length = readInt(array, pos);
/* 221 */     if (delta == 0) return length;
/*     */ 
/* 224 */     pos += count(length) + length;
/* 225 */     length = readInt(array, pos);
/* 226 */     int common = readInt(array, pos + count(length));
/* 227 */     for (int i = 0; i < delta - 1; i++) {
/* 228 */       pos += count(length) + count(common) + length;
/* 229 */       length = readInt(array, pos);
/* 230 */       common = readInt(array, pos + count(length));
/*     */     }
/* 232 */     return length + common;
/*     */   }
/*     */ 
/*     */   public int arrayLength(int index)
/*     */   {
/* 240 */     ensureRestrictedIndex(index);
/* 241 */     return length(index);
/*     */   }
/*     */ 
/*     */   private int extract(int index, short[] a, int offset, int length)
/*     */   {
/* 252 */     int delta = index % this.ratio;
/* 253 */     long startPos = this.p[(index / this.ratio)];
/*     */     long pos;
/* 255 */     int arrayLength = readInt(this.array, pos = startPos); int currLen = 0;
/* 256 */     if (delta == 0) {
/* 257 */       pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 258 */       ShortBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 259 */       return arrayLength;
/*     */     }
/* 261 */     int common = 0;
/* 262 */     for (int i = 0; i < delta; i++) {
/* 263 */       long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 264 */       pos = prevArrayPos + arrayLength;
/* 265 */       arrayLength = readInt(this.array, pos);
/* 266 */       common = readInt(this.array, pos + count(arrayLength));
/* 267 */       int actualCommon = Math.min(common, length);
/* 268 */       if (actualCommon <= currLen) { currLen = actualCommon;
/*     */       } else {
/* 270 */         ShortBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 271 */         currLen = actualCommon;
/*     */       }
/*     */     }
/* 274 */     if (currLen < length) ShortBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 275 */     return arrayLength + common;
/*     */   }
/*     */   public short[] get(int index) {
/* 278 */     return getArray(index);
/*     */   }
/*     */ 
/*     */   public short[] getArray(int index)
/*     */   {
/* 284 */     ensureRestrictedIndex(index);
/* 285 */     int length = length(index);
/* 286 */     short[] a = new short[length];
/* 287 */     extract(index, a, 0, length);
/* 288 */     return a;
/*     */   }
/*     */ 
/*     */   public int get(int index, short[] a, int offset, int length)
/*     */   {
/* 300 */     ensureRestrictedIndex(index);
/* 301 */     ShortArrays.ensureOffsetLength(a, offset, length);
/* 302 */     int arrayLength = extract(index, a, offset, length);
/* 303 */     if (length >= arrayLength) return arrayLength;
/* 304 */     return length - arrayLength;
/*     */   }
/*     */ 
/*     */   public int get(int index, short[] a)
/*     */   {
/* 314 */     return get(index, a, 0, a.length);
/*     */   }
/*     */   public int size() {
/* 317 */     return this.n;
/*     */   }
/*     */   public ObjectListIterator<short[]> listIterator(final int start) {
/* 320 */     ensureIndex(start);
/* 321 */     return new AbstractObjectListIterator() {
/* 322 */       short[] s = ShortArrays.EMPTY_ARRAY;
/* 323 */       int i = 0;
/* 324 */       long pos = 0L;
/*     */       boolean inSync;
/*     */ 
/*     */       public boolean hasNext()
/*     */       {
/* 338 */         return this.i < ShortArrayFrontCodedList.this.n;
/*     */       }
/*     */       public boolean hasPrevious() {
/* 341 */         return this.i > 0;
/*     */       }
/*     */       public int previousIndex() {
/* 344 */         return this.i - 1;
/*     */       }
/*     */       public int nextIndex() {
/* 347 */         return this.i;
/*     */       }
/*     */ 
/*     */       public short[] next() {
/* 351 */         if (!hasNext()) throw new NoSuchElementException();
/*     */         int length;
/* 352 */         if (this.i % ShortArrayFrontCodedList.this.ratio == 0) {
/* 353 */           this.pos = ShortArrayFrontCodedList.this.p[(this.i / ShortArrayFrontCodedList.this.ratio)];
/* 354 */           int length = ShortArrayFrontCodedList.readInt(ShortArrayFrontCodedList.this.array, this.pos);
/* 355 */           this.s = ShortArrays.ensureCapacity(this.s, length, 0);
/* 356 */           ShortBigArrays.copyFromBig(ShortArrayFrontCodedList.this.array, this.pos + ShortArrayFrontCodedList.count(length), this.s, 0, length);
/* 357 */           this.pos += length + ShortArrayFrontCodedList.count(length);
/* 358 */           this.inSync = true;
/*     */         }
/* 361 */         else if (this.inSync) {
/* 362 */           int length = ShortArrayFrontCodedList.readInt(ShortArrayFrontCodedList.this.array, this.pos);
/* 363 */           int common = ShortArrayFrontCodedList.readInt(ShortArrayFrontCodedList.this.array, this.pos + ShortArrayFrontCodedList.access$100(length));
/* 364 */           this.s = ShortArrays.ensureCapacity(this.s, length + common, common);
/* 365 */           ShortBigArrays.copyFromBig(ShortArrayFrontCodedList.this.array, this.pos + ShortArrayFrontCodedList.count(length) + ShortArrayFrontCodedList.count(common), this.s, common, length);
/* 366 */           this.pos += ShortArrayFrontCodedList.count(length) + ShortArrayFrontCodedList.count(common) + length;
/* 367 */           length += common;
/*     */         }
/*     */         else {
/* 370 */           this.s = ShortArrays.ensureCapacity(this.s, length = ShortArrayFrontCodedList.this.length(this.i), 0);
/* 371 */           ShortArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/*     */         }
/*     */ 
/* 374 */         this.i += 1;
/* 375 */         return ShortArrays.copy(this.s, 0, length);
/*     */       }
/*     */       public short[] previous() {
/* 378 */         if (!hasPrevious()) throw new NoSuchElementException();
/* 379 */         this.inSync = false;
/* 380 */         return ShortArrayFrontCodedList.this.getArray(--this.i);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ShortArrayFrontCodedList clone()
/*     */   {
/* 389 */     return this;
/*     */   }
/*     */   public String toString() {
/* 392 */     StringBuffer s = new StringBuffer();
/* 393 */     s.append("[ ");
/* 394 */     for (int i = 0; i < this.n; i++) {
/* 395 */       if (i != 0) s.append(", ");
/* 396 */       s.append(ShortArrayList.wrap(getArray(i)).toString());
/*     */     }
/* 398 */     s.append(" ]");
/* 399 */     return s.toString();
/*     */   }
/*     */ 
/*     */   protected long[] rebuildPointerArray()
/*     */   {
/* 406 */     long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 407 */     short[][] a = this.array;
/*     */ 
/* 409 */     long pos = 0L;
/* 410 */     int i = 0; int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 411 */       int length = readInt(a, pos);
/* 412 */       int count = count(length);
/* 413 */       skip++; if (skip == this.ratio) {
/* 414 */         skip = 0;
/* 415 */         p[(j++)] = pos;
/* 416 */         pos += count + length;
/*     */       } else {
/* 418 */         pos += count + count(readInt(a, pos + count)) + length;
/*     */       }
/*     */     }
/* 420 */     return p;
/*     */   }
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 423 */     s.defaultReadObject();
/*     */ 
/* 425 */     this.p = rebuildPointerArray();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortArrayFrontCodedList
 * JD-Core Version:    0.6.2
 */