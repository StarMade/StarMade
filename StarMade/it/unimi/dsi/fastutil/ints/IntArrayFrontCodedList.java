/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.longs.LongArrays;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectList;
/*   5:    */import it.unimi.dsi.fastutil.objects.AbstractObjectListIterator;
/*   6:    */import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*   7:    */import java.io.IOException;
/*   8:    */import java.io.ObjectInputStream;
/*   9:    */import java.io.Serializable;
/*  10:    */import java.util.Collection;
/*  11:    */import java.util.Iterator;
/*  12:    */import java.util.NoSuchElementException;
/*  13:    */
/* 112:    */public class IntArrayFrontCodedList
/* 113:    */  extends AbstractObjectList<int[]>
/* 114:    */  implements Serializable, Cloneable
/* 115:    */{
/* 116:    */  public static final long serialVersionUID = 1L;
/* 117:    */  protected final int n;
/* 118:    */  protected final int ratio;
/* 119:    */  protected final int[][] array;
/* 120:    */  protected transient long[] p;
/* 121:    */  
/* 122:    */  public IntArrayFrontCodedList(Iterator<int[]> arrays, int ratio)
/* 123:    */  {
/* 124:124 */    if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125:125 */    int[][] array = IntBigArrays.EMPTY_BIG_ARRAY;
/* 126:126 */    long[] p = LongArrays.EMPTY_ARRAY;
/* 127:127 */    int[][] a = new int[2][];
/* 128:128 */    long curSize = 0L;
/* 129:129 */    int n = 0;int b = 0;
/* 130:130 */    while (arrays.hasNext()) {
/* 131:131 */      a[b] = ((int[])arrays.next());
/* 132:132 */      int length = a[b].length;
/* 133:133 */      if (n % ratio == 0) {
/* 134:134 */        p = LongArrays.grow(p, n / ratio + 1);
/* 135:135 */        p[(n / ratio)] = curSize;
/* 136:136 */        array = IntBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137:137 */        curSize += writeInt(array, length, curSize);
/* 138:138 */        IntBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139:139 */        curSize += length;
/* 140:    */      }
/* 141:    */      else {
/* 142:142 */        int minLength = a[(1 - b)].length;
/* 143:143 */        if (length < minLength) minLength = length;
/* 144:144 */        for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++) {}
/* 145:145 */        length -= common;
/* 146:146 */        array = IntBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147:147 */        curSize += writeInt(array, length, curSize);
/* 148:148 */        curSize += writeInt(array, common, curSize);
/* 149:149 */        IntBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150:150 */        curSize += length;
/* 151:    */      }
/* 152:152 */      b = 1 - b;
/* 153:153 */      n++;
/* 154:    */    }
/* 155:155 */    this.n = n;
/* 156:156 */    this.ratio = ratio;
/* 157:157 */    this.array = IntBigArrays.trim(array, curSize);
/* 158:158 */    this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/* 159:    */  }
/* 160:    */  
/* 164:    */  public IntArrayFrontCodedList(Collection<int[]> c, int ratio)
/* 165:    */  {
/* 166:166 */    this(c.iterator(), ratio);
/* 167:    */  }
/* 168:    */  
/* 175:    */  private static int readInt(int[][] a, long pos)
/* 176:    */  {
/* 177:177 */    return IntBigArrays.get(a, pos);
/* 178:    */  }
/* 179:    */  
/* 183:    */  private static int count(int length)
/* 184:    */  {
/* 185:185 */    return 1;
/* 186:    */  }
/* 187:    */  
/* 192:    */  private static int writeInt(int[][] a, int length, long pos)
/* 193:    */  {
/* 194:194 */    IntBigArrays.set(a, pos, length);
/* 195:195 */    return 1;
/* 196:    */  }
/* 197:    */  
/* 200:    */  public int ratio()
/* 201:    */  {
/* 202:202 */    return this.ratio;
/* 203:    */  }
/* 204:    */  
/* 210:    */  private int length(int index)
/* 211:    */  {
/* 212:212 */    int[][] array = this.array;
/* 213:213 */    int delta = index % this.ratio;
/* 214:214 */    long pos = this.p[(index / this.ratio)];
/* 215:215 */    int length = readInt(array, pos);
/* 216:216 */    if (delta == 0) { return length;
/* 217:    */    }
/* 218:    */    
/* 219:219 */    pos += count(length) + length;
/* 220:220 */    length = readInt(array, pos);
/* 221:221 */    int common = readInt(array, pos + count(length));
/* 222:222 */    for (int i = 0; i < delta - 1; i++) {
/* 223:223 */      pos += count(length) + count(common) + length;
/* 224:224 */      length = readInt(array, pos);
/* 225:225 */      common = readInt(array, pos + count(length));
/* 226:    */    }
/* 227:227 */    return length + common;
/* 228:    */  }
/* 229:    */  
/* 233:    */  public int arrayLength(int index)
/* 234:    */  {
/* 235:235 */    ensureRestrictedIndex(index);
/* 236:236 */    return length(index);
/* 237:    */  }
/* 238:    */  
/* 245:    */  private int extract(int index, int[] a, int offset, int length)
/* 246:    */  {
/* 247:247 */    int delta = index % this.ratio;
/* 248:248 */    long startPos = this.p[(index / this.ratio)];
/* 249:    */    long pos;
/* 250:250 */    int arrayLength = readInt(this.array, pos = startPos);int currLen = 0;
/* 251:251 */    if (delta == 0) {
/* 252:252 */      pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 253:253 */      IntBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 254:254 */      return arrayLength;
/* 255:    */    }
/* 256:256 */    int common = 0;
/* 257:257 */    for (int i = 0; i < delta; i++) {
/* 258:258 */      long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 259:259 */      pos = prevArrayPos + arrayLength;
/* 260:260 */      arrayLength = readInt(this.array, pos);
/* 261:261 */      common = readInt(this.array, pos + count(arrayLength));
/* 262:262 */      int actualCommon = Math.min(common, length);
/* 263:263 */      if (actualCommon <= currLen) { currLen = actualCommon;
/* 264:    */      } else {
/* 265:265 */        IntBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 266:266 */        currLen = actualCommon;
/* 267:    */      }
/* 268:    */    }
/* 269:269 */    if (currLen < length) IntBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 270:270 */    return arrayLength + common;
/* 271:    */  }
/* 272:    */  
/* 273:273 */  public int[] get(int index) { return getArray(index); }
/* 274:    */  
/* 277:    */  public int[] getArray(int index)
/* 278:    */  {
/* 279:279 */    ensureRestrictedIndex(index);
/* 280:280 */    int length = length(index);
/* 281:281 */    int[] a = new int[length];
/* 282:282 */    extract(index, a, 0, length);
/* 283:283 */    return a;
/* 284:    */  }
/* 285:    */  
/* 293:    */  public int get(int index, int[] a, int offset, int length)
/* 294:    */  {
/* 295:295 */    ensureRestrictedIndex(index);
/* 296:296 */    IntArrays.ensureOffsetLength(a, offset, length);
/* 297:297 */    int arrayLength = extract(index, a, offset, length);
/* 298:298 */    if (length >= arrayLength) return arrayLength;
/* 299:299 */    return length - arrayLength;
/* 300:    */  }
/* 301:    */  
/* 307:    */  public int get(int index, int[] a)
/* 308:    */  {
/* 309:309 */    return get(index, a, 0, a.length);
/* 310:    */  }
/* 311:    */  
/* 312:312 */  public int size() { return this.n; }
/* 313:    */  
/* 314:    */  public ObjectListIterator<int[]> listIterator(final int start) {
/* 315:315 */    ensureIndex(start);
/* 316:316 */    new AbstractObjectListIterator() {
/* 317:317 */      int[] s = IntArrays.EMPTY_ARRAY;
/* 318:318 */      int i = 0;
/* 319:319 */      long pos = 0L;
/* 320:    */      
/* 325:    */      boolean inSync;
/* 326:    */      
/* 331:    */      public boolean hasNext()
/* 332:    */      {
/* 333:333 */        return this.i < IntArrayFrontCodedList.this.n;
/* 334:    */      }
/* 335:    */      
/* 336:336 */      public boolean hasPrevious() { return this.i > 0; }
/* 337:    */      
/* 338:    */      public int previousIndex() {
/* 339:339 */        return this.i - 1;
/* 340:    */      }
/* 341:    */      
/* 342:342 */      public int nextIndex() { return this.i; }
/* 343:    */      
/* 344:    */      public int[] next()
/* 345:    */      {
/* 346:346 */        if (!hasNext()) throw new NoSuchElementException();
/* 347:347 */        int length; if (this.i % IntArrayFrontCodedList.this.ratio == 0) {
/* 348:348 */          this.pos = IntArrayFrontCodedList.this.p[(this.i / IntArrayFrontCodedList.this.ratio)];
/* 349:349 */          int length = IntArrayFrontCodedList.readInt(IntArrayFrontCodedList.this.array, this.pos);
/* 350:350 */          this.s = IntArrays.ensureCapacity(this.s, length, 0);
/* 351:351 */          IntBigArrays.copyFromBig(IntArrayFrontCodedList.this.array, this.pos + IntArrayFrontCodedList.count(length), this.s, 0, length);
/* 352:352 */          this.pos += length + IntArrayFrontCodedList.count(length);
/* 353:353 */          this.inSync = true;
/* 355:    */        }
/* 356:356 */        else if (this.inSync) {
/* 357:357 */          int length = IntArrayFrontCodedList.readInt(IntArrayFrontCodedList.this.array, this.pos);
/* 358:358 */          int common = IntArrayFrontCodedList.readInt(IntArrayFrontCodedList.this.array, this.pos + IntArrayFrontCodedList.access$100(length));
/* 359:359 */          this.s = IntArrays.ensureCapacity(this.s, length + common, common);
/* 360:360 */          IntBigArrays.copyFromBig(IntArrayFrontCodedList.this.array, this.pos + IntArrayFrontCodedList.count(length) + IntArrayFrontCodedList.count(common), this.s, common, length);
/* 361:361 */          this.pos += IntArrayFrontCodedList.count(length) + IntArrayFrontCodedList.count(common) + length;
/* 362:362 */          length += common;
/* 363:    */        }
/* 364:    */        else {
/* 365:365 */          this.s = IntArrays.ensureCapacity(this.s, length = IntArrayFrontCodedList.this.length(this.i), 0);
/* 366:366 */          IntArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/* 367:    */        }
/* 368:    */        
/* 369:369 */        this.i += 1;
/* 370:370 */        return IntArrays.copy(this.s, 0, length);
/* 371:    */      }
/* 372:    */      
/* 373:373 */      public int[] previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 374:374 */        this.inSync = false;
/* 375:375 */        return IntArrayFrontCodedList.this.getArray(--this.i);
/* 376:    */      }
/* 377:    */    };
/* 378:    */  }
/* 379:    */  
/* 384:384 */  public IntArrayFrontCodedList clone() { return this; }
/* 385:    */  
/* 386:    */  public String toString() {
/* 387:387 */    StringBuffer s = new StringBuffer();
/* 388:388 */    s.append("[ ");
/* 389:389 */    for (int i = 0; i < this.n; i++) {
/* 390:390 */      if (i != 0) s.append(", ");
/* 391:391 */      s.append(IntArrayList.wrap(getArray(i)).toString());
/* 392:    */    }
/* 393:393 */    s.append(" ]");
/* 394:394 */    return s.toString();
/* 395:    */  }
/* 396:    */  
/* 399:    */  protected long[] rebuildPointerArray()
/* 400:    */  {
/* 401:401 */    long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 402:402 */    int[][] a = this.array;
/* 403:    */    
/* 404:404 */    long pos = 0L;
/* 405:405 */    int i = 0;int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 406:406 */      int length = readInt(a, pos);
/* 407:407 */      int count = count(length);
/* 408:408 */      skip++; if (skip == this.ratio) {
/* 409:409 */        skip = 0;
/* 410:410 */        p[(j++)] = pos;
/* 411:411 */        pos += count + length;
/* 412:    */      } else {
/* 413:413 */        pos += count + count(readInt(a, pos + count)) + length;
/* 414:    */      } }
/* 415:415 */    return p;
/* 416:    */  }
/* 417:    */  
/* 418:418 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 419:    */    
/* 420:420 */    this.p = rebuildPointerArray();
/* 421:    */  }
/* 422:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntArrayFrontCodedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */