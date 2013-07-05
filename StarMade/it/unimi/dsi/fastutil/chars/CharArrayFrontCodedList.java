/*     */ package it.unimi.dsi.fastutil.chars;
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
/*     */ public class CharArrayFrontCodedList extends AbstractObjectList<char[]>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   public static final long serialVersionUID = 1L;
/*     */   protected final int n;
/*     */   protected final int ratio;
/*     */   protected final char[][] array;
/*     */   protected transient long[] p;
/*     */ 
/*     */   public CharArrayFrontCodedList(Iterator<char[]> arrays, int ratio)
/*     */   {
/* 124 */     if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125 */     char[][] array = CharBigArrays.EMPTY_BIG_ARRAY;
/* 126 */     long[] p = LongArrays.EMPTY_ARRAY;
/* 127 */     char[][] a = new char[2][];
/* 128 */     long curSize = 0L;
/* 129 */     int n = 0; int b = 0;
/* 130 */     while (arrays.hasNext()) {
/* 131 */       a[b] = ((char[])arrays.next());
/* 132 */       int length = a[b].length;
/* 133 */       if (n % ratio == 0) {
/* 134 */         p = LongArrays.grow(p, n / ratio + 1);
/* 135 */         p[(n / ratio)] = curSize;
/* 136 */         array = CharBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137 */         curSize += writeInt(array, length, curSize);
/* 138 */         CharBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139 */         curSize += length;
/*     */       }
/*     */       else {
/* 142 */         int minLength = a[(1 - b)].length;
/* 143 */         if (length < minLength) minLength = length;
/* 144 */         for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++);
/* 145 */         length -= common;
/* 146 */         array = CharBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147 */         curSize += writeInt(array, length, curSize);
/* 148 */         curSize += writeInt(array, common, curSize);
/* 149 */         CharBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150 */         curSize += length;
/*     */       }
/* 152 */       b = 1 - b;
/* 153 */       n++;
/*     */     }
/* 155 */     this.n = n;
/* 156 */     this.ratio = ratio;
/* 157 */     this.array = CharBigArrays.trim(array, curSize);
/* 158 */     this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/*     */   }
/*     */ 
/*     */   public CharArrayFrontCodedList(Collection<char[]> c, int ratio)
/*     */   {
/* 166 */     this(c.iterator(), ratio);
/*     */   }
/*     */ 
/*     */   private static int readInt(char[][] a, long pos)
/*     */   {
/* 181 */     char c0 = CharBigArrays.get(a, pos);
/* 182 */     return c0 < 32768 ? c0 : (c0 & 0x7FFF) << '\020' | CharBigArrays.get(a, pos + 1L);
/*     */   }
/*     */ 
/*     */   private static int count(int length)
/*     */   {
/* 190 */     return length < 32768 ? 1 : 2;
/*     */   }
/*     */ 
/*     */   private static int writeInt(char[][] a, int length, long pos)
/*     */   {
/* 199 */     if (length < 32768) {
/* 200 */       CharBigArrays.set(a, pos, (char)length);
/* 201 */       return 1;
/*     */     }
/* 203 */     CharBigArrays.set(a, pos++, (char)(length >>> 16 | 0x8000));
/* 204 */     CharBigArrays.set(a, pos, (char)(length & 0xFFFF));
/* 205 */     return 2;
/*     */   }
/*     */ 
/*     */   public int ratio()
/*     */   {
/* 212 */     return this.ratio;
/*     */   }
/*     */ 
/*     */   private int length(int index)
/*     */   {
/* 222 */     char[][] array = this.array;
/* 223 */     int delta = index % this.ratio;
/* 224 */     long pos = this.p[(index / this.ratio)];
/* 225 */     int length = readInt(array, pos);
/* 226 */     if (delta == 0) return length;
/*     */ 
/* 229 */     pos += count(length) + length;
/* 230 */     length = readInt(array, pos);
/* 231 */     int common = readInt(array, pos + count(length));
/* 232 */     for (int i = 0; i < delta - 1; i++) {
/* 233 */       pos += count(length) + count(common) + length;
/* 234 */       length = readInt(array, pos);
/* 235 */       common = readInt(array, pos + count(length));
/*     */     }
/* 237 */     return length + common;
/*     */   }
/*     */ 
/*     */   public int arrayLength(int index)
/*     */   {
/* 245 */     ensureRestrictedIndex(index);
/* 246 */     return length(index);
/*     */   }
/*     */ 
/*     */   private int extract(int index, char[] a, int offset, int length)
/*     */   {
/* 257 */     int delta = index % this.ratio;
/* 258 */     long startPos = this.p[(index / this.ratio)];
/*     */     long pos;
/* 260 */     int arrayLength = readInt(this.array, pos = startPos); int currLen = 0;
/* 261 */     if (delta == 0) {
/* 262 */       pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 263 */       CharBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 264 */       return arrayLength;
/*     */     }
/* 266 */     int common = 0;
/* 267 */     for (int i = 0; i < delta; i++) {
/* 268 */       long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 269 */       pos = prevArrayPos + arrayLength;
/* 270 */       arrayLength = readInt(this.array, pos);
/* 271 */       common = readInt(this.array, pos + count(arrayLength));
/* 272 */       int actualCommon = Math.min(common, length);
/* 273 */       if (actualCommon <= currLen) { currLen = actualCommon;
/*     */       } else {
/* 275 */         CharBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 276 */         currLen = actualCommon;
/*     */       }
/*     */     }
/* 279 */     if (currLen < length) CharBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 280 */     return arrayLength + common;
/*     */   }
/*     */   public char[] get(int index) {
/* 283 */     return getArray(index);
/*     */   }
/*     */ 
/*     */   public char[] getArray(int index)
/*     */   {
/* 289 */     ensureRestrictedIndex(index);
/* 290 */     int length = length(index);
/* 291 */     char[] a = new char[length];
/* 292 */     extract(index, a, 0, length);
/* 293 */     return a;
/*     */   }
/*     */ 
/*     */   public int get(int index, char[] a, int offset, int length)
/*     */   {
/* 305 */     ensureRestrictedIndex(index);
/* 306 */     CharArrays.ensureOffsetLength(a, offset, length);
/* 307 */     int arrayLength = extract(index, a, offset, length);
/* 308 */     if (length >= arrayLength) return arrayLength;
/* 309 */     return length - arrayLength;
/*     */   }
/*     */ 
/*     */   public int get(int index, char[] a)
/*     */   {
/* 319 */     return get(index, a, 0, a.length);
/*     */   }
/*     */   public int size() {
/* 322 */     return this.n;
/*     */   }
/*     */   public ObjectListIterator<char[]> listIterator(final int start) {
/* 325 */     ensureIndex(start);
/* 326 */     return new AbstractObjectListIterator() {
/* 327 */       char[] s = CharArrays.EMPTY_ARRAY;
/* 328 */       int i = 0;
/* 329 */       long pos = 0L;
/*     */       boolean inSync;
/*     */ 
/*     */       public boolean hasNext()
/*     */       {
/* 343 */         return this.i < CharArrayFrontCodedList.this.n;
/*     */       }
/*     */       public boolean hasPrevious() {
/* 346 */         return this.i > 0;
/*     */       }
/*     */       public int previousIndex() {
/* 349 */         return this.i - 1;
/*     */       }
/*     */       public int nextIndex() {
/* 352 */         return this.i;
/*     */       }
/*     */ 
/*     */       public char[] next() {
/* 356 */         if (!hasNext()) throw new NoSuchElementException();
/*     */         int length;
/* 357 */         if (this.i % CharArrayFrontCodedList.this.ratio == 0) {
/* 358 */           this.pos = CharArrayFrontCodedList.this.p[(this.i / CharArrayFrontCodedList.this.ratio)];
/* 359 */           int length = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos);
/* 360 */           this.s = CharArrays.ensureCapacity(this.s, length, 0);
/* 361 */           CharBigArrays.copyFromBig(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.count(length), this.s, 0, length);
/* 362 */           this.pos += length + CharArrayFrontCodedList.count(length);
/* 363 */           this.inSync = true;
/*     */         }
/* 366 */         else if (this.inSync) {
/* 367 */           int length = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos);
/* 368 */           int common = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.access$100(length));
/* 369 */           this.s = CharArrays.ensureCapacity(this.s, length + common, common);
/* 370 */           CharBigArrays.copyFromBig(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.count(length) + CharArrayFrontCodedList.count(common), this.s, common, length);
/* 371 */           this.pos += CharArrayFrontCodedList.count(length) + CharArrayFrontCodedList.count(common) + length;
/* 372 */           length += common;
/*     */         }
/*     */         else {
/* 375 */           this.s = CharArrays.ensureCapacity(this.s, length = CharArrayFrontCodedList.this.length(this.i), 0);
/* 376 */           CharArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/*     */         }
/*     */ 
/* 379 */         this.i += 1;
/* 380 */         return CharArrays.copy(this.s, 0, length);
/*     */       }
/*     */       public char[] previous() {
/* 383 */         if (!hasPrevious()) throw new NoSuchElementException();
/* 384 */         this.inSync = false;
/* 385 */         return CharArrayFrontCodedList.this.getArray(--this.i);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public CharArrayFrontCodedList clone()
/*     */   {
/* 394 */     return this;
/*     */   }
/*     */   public String toString() {
/* 397 */     StringBuffer s = new StringBuffer();
/* 398 */     s.append("[ ");
/* 399 */     for (int i = 0; i < this.n; i++) {
/* 400 */       if (i != 0) s.append(", ");
/* 401 */       s.append(CharArrayList.wrap(getArray(i)).toString());
/*     */     }
/* 403 */     s.append(" ]");
/* 404 */     return s.toString();
/*     */   }
/*     */ 
/*     */   protected long[] rebuildPointerArray()
/*     */   {
/* 411 */     long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 412 */     char[][] a = this.array;
/*     */ 
/* 414 */     long pos = 0L;
/* 415 */     int i = 0; int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 416 */       int length = readInt(a, pos);
/* 417 */       int count = count(length);
/* 418 */       skip++; if (skip == this.ratio) {
/* 419 */         skip = 0;
/* 420 */         p[(j++)] = pos;
/* 421 */         pos += count + length;
/*     */       } else {
/* 423 */         pos += count + count(readInt(a, pos + count)) + length;
/*     */       }
/*     */     }
/* 425 */     return p;
/*     */   }
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 428 */     s.defaultReadObject();
/*     */ 
/* 430 */     this.p = rebuildPointerArray();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayFrontCodedList
 * JD-Core Version:    0.6.2
 */