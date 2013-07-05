/*     */ package it.unimi.dsi.fastutil.bytes;
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
/*     */ public class ByteArrayFrontCodedList extends AbstractObjectList<byte[]>
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   public static final long serialVersionUID = 1L;
/*     */   protected final int n;
/*     */   protected final int ratio;
/*     */   protected final byte[][] array;
/*     */   protected transient long[] p;
/*     */ 
/*     */   public ByteArrayFrontCodedList(Iterator<byte[]> arrays, int ratio)
/*     */   {
/* 124 */     if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125 */     byte[][] array = ByteBigArrays.EMPTY_BIG_ARRAY;
/* 126 */     long[] p = LongArrays.EMPTY_ARRAY;
/* 127 */     byte[][] a = new byte[2][];
/* 128 */     long curSize = 0L;
/* 129 */     int n = 0; int b = 0;
/* 130 */     while (arrays.hasNext()) {
/* 131 */       a[b] = ((byte[])arrays.next());
/* 132 */       int length = a[b].length;
/* 133 */       if (n % ratio == 0) {
/* 134 */         p = LongArrays.grow(p, n / ratio + 1);
/* 135 */         p[(n / ratio)] = curSize;
/* 136 */         array = ByteBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137 */         curSize += writeInt(array, length, curSize);
/* 138 */         ByteBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139 */         curSize += length;
/*     */       }
/*     */       else {
/* 142 */         int minLength = a[(1 - b)].length;
/* 143 */         if (length < minLength) minLength = length;
/* 144 */         for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++);
/* 145 */         length -= common;
/* 146 */         array = ByteBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147 */         curSize += writeInt(array, length, curSize);
/* 148 */         curSize += writeInt(array, common, curSize);
/* 149 */         ByteBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150 */         curSize += length;
/*     */       }
/* 152 */       b = 1 - b;
/* 153 */       n++;
/*     */     }
/* 155 */     this.n = n;
/* 156 */     this.ratio = ratio;
/* 157 */     this.array = ByteBigArrays.trim(array, curSize);
/* 158 */     this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/*     */   }
/*     */ 
/*     */   public ByteArrayFrontCodedList(Collection<byte[]> c, int ratio)
/*     */   {
/* 166 */     this(c.iterator(), ratio);
/*     */   }
/*     */ 
/*     */   private static int readInt(byte[][] a, long pos)
/*     */   {
/* 176 */     byte b0 = ByteBigArrays.get(a, pos);
/* 177 */     if (b0 >= 0) return b0;
/* 178 */     byte b1 = ByteBigArrays.get(a, pos + 1L);
/* 179 */     if (b1 >= 0) return -b0 - 1 << 7 | b1;
/* 180 */     byte b2 = ByteBigArrays.get(a, pos + 2L);
/* 181 */     if (b2 >= 0) return -b0 - 1 << 14 | -b1 - 1 << 7 | b2;
/* 182 */     byte b3 = ByteBigArrays.get(a, pos + 3L);
/* 183 */     if (b3 >= 0) return -b0 - 1 << 21 | -b1 - 1 << 14 | -b2 - 1 << 7 | b3;
/* 184 */     return -b0 - 1 << 28 | -b1 - 1 << 21 | -b2 - 1 << 14 | -b3 - 1 << 7 | ByteBigArrays.get(a, pos + 4L);
/*     */   }
/*     */ 
/*     */   private static int count(int length)
/*     */   {
/* 192 */     if (length < 128) return 1;
/* 193 */     if (length < 16384) return 2;
/* 194 */     if (length < 2097152) return 3;
/* 195 */     if (length < 268435456) return 4;
/* 196 */     return 5;
/*     */   }
/*     */ 
/*     */   private static int writeInt(byte[][] a, int length, long pos)
/*     */   {
/* 205 */     int count = count(length);
/* 206 */     ByteBigArrays.set(a, pos + count - 1L, (byte)(length & 0x7F));
/* 207 */     if (count != 1) {
/* 208 */       int i = count - 1;
/* 209 */       while (i-- != 0) {
/* 210 */         length >>>= 7;
/* 211 */         ByteBigArrays.set(a, pos + i, (byte)(-(length & 0x7F) - 1));
/*     */       }
/*     */     }
/* 214 */     return count;
/*     */   }
/*     */ 
/*     */   public int ratio()
/*     */   {
/* 221 */     return this.ratio;
/*     */   }
/*     */ 
/*     */   private int length(int index)
/*     */   {
/* 231 */     byte[][] array = this.array;
/* 232 */     int delta = index % this.ratio;
/* 233 */     long pos = this.p[(index / this.ratio)];
/* 234 */     int length = readInt(array, pos);
/* 235 */     if (delta == 0) return length;
/*     */ 
/* 238 */     pos += count(length) + length;
/* 239 */     length = readInt(array, pos);
/* 240 */     int common = readInt(array, pos + count(length));
/* 241 */     for (int i = 0; i < delta - 1; i++) {
/* 242 */       pos += count(length) + count(common) + length;
/* 243 */       length = readInt(array, pos);
/* 244 */       common = readInt(array, pos + count(length));
/*     */     }
/* 246 */     return length + common;
/*     */   }
/*     */ 
/*     */   public int arrayLength(int index)
/*     */   {
/* 254 */     ensureRestrictedIndex(index);
/* 255 */     return length(index);
/*     */   }
/*     */ 
/*     */   private int extract(int index, byte[] a, int offset, int length)
/*     */   {
/* 266 */     int delta = index % this.ratio;
/* 267 */     long startPos = this.p[(index / this.ratio)];
/*     */     long pos;
/* 269 */     int arrayLength = readInt(this.array, pos = startPos); int currLen = 0;
/* 270 */     if (delta == 0) {
/* 271 */       pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 272 */       ByteBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 273 */       return arrayLength;
/*     */     }
/* 275 */     int common = 0;
/* 276 */     for (int i = 0; i < delta; i++) {
/* 277 */       long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 278 */       pos = prevArrayPos + arrayLength;
/* 279 */       arrayLength = readInt(this.array, pos);
/* 280 */       common = readInt(this.array, pos + count(arrayLength));
/* 281 */       int actualCommon = Math.min(common, length);
/* 282 */       if (actualCommon <= currLen) { currLen = actualCommon;
/*     */       } else {
/* 284 */         ByteBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 285 */         currLen = actualCommon;
/*     */       }
/*     */     }
/* 288 */     if (currLen < length) ByteBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 289 */     return arrayLength + common;
/*     */   }
/*     */   public byte[] get(int index) {
/* 292 */     return getArray(index);
/*     */   }
/*     */ 
/*     */   public byte[] getArray(int index)
/*     */   {
/* 298 */     ensureRestrictedIndex(index);
/* 299 */     int length = length(index);
/* 300 */     byte[] a = new byte[length];
/* 301 */     extract(index, a, 0, length);
/* 302 */     return a;
/*     */   }
/*     */ 
/*     */   public int get(int index, byte[] a, int offset, int length)
/*     */   {
/* 314 */     ensureRestrictedIndex(index);
/* 315 */     ByteArrays.ensureOffsetLength(a, offset, length);
/* 316 */     int arrayLength = extract(index, a, offset, length);
/* 317 */     if (length >= arrayLength) return arrayLength;
/* 318 */     return length - arrayLength;
/*     */   }
/*     */ 
/*     */   public int get(int index, byte[] a)
/*     */   {
/* 328 */     return get(index, a, 0, a.length);
/*     */   }
/*     */   public int size() {
/* 331 */     return this.n;
/*     */   }
/*     */   public ObjectListIterator<byte[]> listIterator(final int start) {
/* 334 */     ensureIndex(start);
/* 335 */     return new AbstractObjectListIterator() {
/* 336 */       byte[] s = ByteArrays.EMPTY_ARRAY;
/* 337 */       int i = 0;
/* 338 */       long pos = 0L;
/*     */       boolean inSync;
/*     */ 
/*     */       public boolean hasNext()
/*     */       {
/* 352 */         return this.i < ByteArrayFrontCodedList.this.n;
/*     */       }
/*     */       public boolean hasPrevious() {
/* 355 */         return this.i > 0;
/*     */       }
/*     */       public int previousIndex() {
/* 358 */         return this.i - 1;
/*     */       }
/*     */       public int nextIndex() {
/* 361 */         return this.i;
/*     */       }
/*     */ 
/*     */       public byte[] next() {
/* 365 */         if (!hasNext()) throw new NoSuchElementException();
/*     */         int length;
/* 366 */         if (this.i % ByteArrayFrontCodedList.this.ratio == 0) {
/* 367 */           this.pos = ByteArrayFrontCodedList.this.p[(this.i / ByteArrayFrontCodedList.this.ratio)];
/* 368 */           int length = ByteArrayFrontCodedList.readInt(ByteArrayFrontCodedList.this.array, this.pos);
/* 369 */           this.s = ByteArrays.ensureCapacity(this.s, length, 0);
/* 370 */           ByteBigArrays.copyFromBig(ByteArrayFrontCodedList.this.array, this.pos + ByteArrayFrontCodedList.count(length), this.s, 0, length);
/* 371 */           this.pos += length + ByteArrayFrontCodedList.count(length);
/* 372 */           this.inSync = true;
/*     */         }
/* 375 */         else if (this.inSync) {
/* 376 */           int length = ByteArrayFrontCodedList.readInt(ByteArrayFrontCodedList.this.array, this.pos);
/* 377 */           int common = ByteArrayFrontCodedList.readInt(ByteArrayFrontCodedList.this.array, this.pos + ByteArrayFrontCodedList.access$100(length));
/* 378 */           this.s = ByteArrays.ensureCapacity(this.s, length + common, common);
/* 379 */           ByteBigArrays.copyFromBig(ByteArrayFrontCodedList.this.array, this.pos + ByteArrayFrontCodedList.count(length) + ByteArrayFrontCodedList.count(common), this.s, common, length);
/* 380 */           this.pos += ByteArrayFrontCodedList.count(length) + ByteArrayFrontCodedList.count(common) + length;
/* 381 */           length += common;
/*     */         }
/*     */         else {
/* 384 */           this.s = ByteArrays.ensureCapacity(this.s, length = ByteArrayFrontCodedList.this.length(this.i), 0);
/* 385 */           ByteArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/*     */         }
/*     */ 
/* 388 */         this.i += 1;
/* 389 */         return ByteArrays.copy(this.s, 0, length);
/*     */       }
/*     */       public byte[] previous() {
/* 392 */         if (!hasPrevious()) throw new NoSuchElementException();
/* 393 */         this.inSync = false;
/* 394 */         return ByteArrayFrontCodedList.this.getArray(--this.i);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public ByteArrayFrontCodedList clone()
/*     */   {
/* 403 */     return this;
/*     */   }
/*     */   public String toString() {
/* 406 */     StringBuffer s = new StringBuffer();
/* 407 */     s.append("[ ");
/* 408 */     for (int i = 0; i < this.n; i++) {
/* 409 */       if (i != 0) s.append(", ");
/* 410 */       s.append(ByteArrayList.wrap(getArray(i)).toString());
/*     */     }
/* 412 */     s.append(" ]");
/* 413 */     return s.toString();
/*     */   }
/*     */ 
/*     */   protected long[] rebuildPointerArray()
/*     */   {
/* 420 */     long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 421 */     byte[][] a = this.array;
/*     */ 
/* 423 */     long pos = 0L;
/* 424 */     int i = 0; int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 425 */       int length = readInt(a, pos);
/* 426 */       int count = count(length);
/* 427 */       skip++; if (skip == this.ratio) {
/* 428 */         skip = 0;
/* 429 */         p[(j++)] = pos;
/* 430 */         pos += count + length;
/*     */       } else {
/* 432 */         pos += count + count(readInt(a, pos + count)) + length;
/*     */       }
/*     */     }
/* 434 */     return p;
/*     */   }
/*     */   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
/* 437 */     s.defaultReadObject();
/*     */ 
/* 439 */     this.p = rebuildPointerArray();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrayFrontCodedList
 * JD-Core Version:    0.6.2
 */