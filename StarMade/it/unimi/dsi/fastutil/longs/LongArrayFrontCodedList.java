/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.AbstractObjectList;
/*   4:    */import it.unimi.dsi.fastutil.objects.AbstractObjectListIterator;
/*   5:    */import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.ObjectInputStream;
/*   8:    */import java.io.Serializable;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.Iterator;
/*  11:    */import java.util.NoSuchElementException;
/*  12:    */
/* 112:    */public class LongArrayFrontCodedList
/* 113:    */  extends AbstractObjectList<long[]>
/* 114:    */  implements Serializable, Cloneable
/* 115:    */{
/* 116:    */  public static final long serialVersionUID = 1L;
/* 117:    */  protected final int n;
/* 118:    */  protected final int ratio;
/* 119:    */  protected final long[][] array;
/* 120:    */  protected transient long[] p;
/* 121:    */  
/* 122:    */  public LongArrayFrontCodedList(Iterator<long[]> arrays, int ratio)
/* 123:    */  {
/* 124:124 */    if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125:125 */    long[][] array = LongBigArrays.EMPTY_BIG_ARRAY;
/* 126:126 */    long[] p = LongArrays.EMPTY_ARRAY;
/* 127:127 */    long[][] a = new long[2][];
/* 128:128 */    long curSize = 0L;
/* 129:129 */    int n = 0;int b = 0;
/* 130:130 */    while (arrays.hasNext()) {
/* 131:131 */      a[b] = ((long[])arrays.next());
/* 132:132 */      int length = a[b].length;
/* 133:133 */      if (n % ratio == 0) {
/* 134:134 */        p = LongArrays.grow(p, n / ratio + 1);
/* 135:135 */        p[(n / ratio)] = curSize;
/* 136:136 */        array = LongBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137:137 */        curSize += writeInt(array, length, curSize);
/* 138:138 */        LongBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139:139 */        curSize += length;
/* 140:    */      }
/* 141:    */      else {
/* 142:142 */        int minLength = a[(1 - b)].length;
/* 143:143 */        if (length < minLength) minLength = length;
/* 144:144 */        for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++) {}
/* 145:145 */        length -= common;
/* 146:146 */        array = LongBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147:147 */        curSize += writeInt(array, length, curSize);
/* 148:148 */        curSize += writeInt(array, common, curSize);
/* 149:149 */        LongBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150:150 */        curSize += length;
/* 151:    */      }
/* 152:152 */      b = 1 - b;
/* 153:153 */      n++;
/* 154:    */    }
/* 155:155 */    this.n = n;
/* 156:156 */    this.ratio = ratio;
/* 157:157 */    this.array = LongBigArrays.trim(array, curSize);
/* 158:158 */    this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/* 159:    */  }
/* 160:    */  
/* 164:    */  public LongArrayFrontCodedList(Collection<long[]> c, int ratio)
/* 165:    */  {
/* 166:166 */    this(c.iterator(), ratio);
/* 167:    */  }
/* 168:    */  
/* 177:    */  private static int readInt(long[][] a, long pos)
/* 178:    */  {
/* 179:179 */    return (int)LongBigArrays.get(a, pos);
/* 180:    */  }
/* 181:    */  
/* 185:    */  private static int count(int length)
/* 186:    */  {
/* 187:187 */    return 1;
/* 188:    */  }
/* 189:    */  
/* 194:    */  private static int writeInt(long[][] a, int length, long pos)
/* 195:    */  {
/* 196:196 */    LongBigArrays.set(a, pos, length);
/* 197:197 */    return 1;
/* 198:    */  }
/* 199:    */  
/* 202:    */  public int ratio()
/* 203:    */  {
/* 204:204 */    return this.ratio;
/* 205:    */  }
/* 206:    */  
/* 212:    */  private int length(int index)
/* 213:    */  {
/* 214:214 */    long[][] array = this.array;
/* 215:215 */    int delta = index % this.ratio;
/* 216:216 */    long pos = this.p[(index / this.ratio)];
/* 217:217 */    int length = readInt(array, pos);
/* 218:218 */    if (delta == 0) { return length;
/* 219:    */    }
/* 220:    */    
/* 221:221 */    pos += count(length) + length;
/* 222:222 */    length = readInt(array, pos);
/* 223:223 */    int common = readInt(array, pos + count(length));
/* 224:224 */    for (int i = 0; i < delta - 1; i++) {
/* 225:225 */      pos += count(length) + count(common) + length;
/* 226:226 */      length = readInt(array, pos);
/* 227:227 */      common = readInt(array, pos + count(length));
/* 228:    */    }
/* 229:229 */    return length + common;
/* 230:    */  }
/* 231:    */  
/* 235:    */  public int arrayLength(int index)
/* 236:    */  {
/* 237:237 */    ensureRestrictedIndex(index);
/* 238:238 */    return length(index);
/* 239:    */  }
/* 240:    */  
/* 247:    */  private int extract(int index, long[] a, int offset, int length)
/* 248:    */  {
/* 249:249 */    int delta = index % this.ratio;
/* 250:250 */    long startPos = this.p[(index / this.ratio)];
/* 251:    */    long pos;
/* 252:252 */    int arrayLength = readInt(this.array, pos = startPos);int currLen = 0;
/* 253:253 */    if (delta == 0) {
/* 254:254 */      pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 255:255 */      LongBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 256:256 */      return arrayLength;
/* 257:    */    }
/* 258:258 */    int common = 0;
/* 259:259 */    for (int i = 0; i < delta; i++) {
/* 260:260 */      long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 261:261 */      pos = prevArrayPos + arrayLength;
/* 262:262 */      arrayLength = readInt(this.array, pos);
/* 263:263 */      common = readInt(this.array, pos + count(arrayLength));
/* 264:264 */      int actualCommon = Math.min(common, length);
/* 265:265 */      if (actualCommon <= currLen) { currLen = actualCommon;
/* 266:    */      } else {
/* 267:267 */        LongBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 268:268 */        currLen = actualCommon;
/* 269:    */      }
/* 270:    */    }
/* 271:271 */    if (currLen < length) LongBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 272:272 */    return arrayLength + common;
/* 273:    */  }
/* 274:    */  
/* 275:275 */  public long[] get(int index) { return getArray(index); }
/* 276:    */  
/* 279:    */  public long[] getArray(int index)
/* 280:    */  {
/* 281:281 */    ensureRestrictedIndex(index);
/* 282:282 */    int length = length(index);
/* 283:283 */    long[] a = new long[length];
/* 284:284 */    extract(index, a, 0, length);
/* 285:285 */    return a;
/* 286:    */  }
/* 287:    */  
/* 295:    */  public int get(int index, long[] a, int offset, int length)
/* 296:    */  {
/* 297:297 */    ensureRestrictedIndex(index);
/* 298:298 */    LongArrays.ensureOffsetLength(a, offset, length);
/* 299:299 */    int arrayLength = extract(index, a, offset, length);
/* 300:300 */    if (length >= arrayLength) return arrayLength;
/* 301:301 */    return length - arrayLength;
/* 302:    */  }
/* 303:    */  
/* 309:    */  public int get(int index, long[] a)
/* 310:    */  {
/* 311:311 */    return get(index, a, 0, a.length);
/* 312:    */  }
/* 313:    */  
/* 314:314 */  public int size() { return this.n; }
/* 315:    */  
/* 316:    */  public ObjectListIterator<long[]> listIterator(final int start) {
/* 317:317 */    ensureIndex(start);
/* 318:318 */    new AbstractObjectListIterator() {
/* 319:319 */      long[] s = LongArrays.EMPTY_ARRAY;
/* 320:320 */      int i = 0;
/* 321:321 */      long pos = 0L;
/* 322:    */      
/* 327:    */      boolean inSync;
/* 328:    */      
/* 333:    */      public boolean hasNext()
/* 334:    */      {
/* 335:335 */        return this.i < LongArrayFrontCodedList.this.n;
/* 336:    */      }
/* 337:    */      
/* 338:338 */      public boolean hasPrevious() { return this.i > 0; }
/* 339:    */      
/* 340:    */      public int previousIndex() {
/* 341:341 */        return this.i - 1;
/* 342:    */      }
/* 343:    */      
/* 344:344 */      public int nextIndex() { return this.i; }
/* 345:    */      
/* 346:    */      public long[] next()
/* 347:    */      {
/* 348:348 */        if (!hasNext()) throw new NoSuchElementException();
/* 349:349 */        int length; if (this.i % LongArrayFrontCodedList.this.ratio == 0) {
/* 350:350 */          this.pos = LongArrayFrontCodedList.this.p[(this.i / LongArrayFrontCodedList.this.ratio)];
/* 351:351 */          int length = LongArrayFrontCodedList.readInt(LongArrayFrontCodedList.this.array, this.pos);
/* 352:352 */          this.s = LongArrays.ensureCapacity(this.s, length, 0);
/* 353:353 */          LongBigArrays.copyFromBig(LongArrayFrontCodedList.this.array, this.pos + LongArrayFrontCodedList.count(length), this.s, 0, length);
/* 354:354 */          this.pos += length + LongArrayFrontCodedList.count(length);
/* 355:355 */          this.inSync = true;
/* 357:    */        }
/* 358:358 */        else if (this.inSync) {
/* 359:359 */          int length = LongArrayFrontCodedList.readInt(LongArrayFrontCodedList.this.array, this.pos);
/* 360:360 */          int common = LongArrayFrontCodedList.readInt(LongArrayFrontCodedList.this.array, this.pos + LongArrayFrontCodedList.access$100(length));
/* 361:361 */          this.s = LongArrays.ensureCapacity(this.s, length + common, common);
/* 362:362 */          LongBigArrays.copyFromBig(LongArrayFrontCodedList.this.array, this.pos + LongArrayFrontCodedList.count(length) + LongArrayFrontCodedList.count(common), this.s, common, length);
/* 363:363 */          this.pos += LongArrayFrontCodedList.count(length) + LongArrayFrontCodedList.count(common) + length;
/* 364:364 */          length += common;
/* 365:    */        }
/* 366:    */        else {
/* 367:367 */          this.s = LongArrays.ensureCapacity(this.s, length = LongArrayFrontCodedList.this.length(this.i), 0);
/* 368:368 */          LongArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/* 369:    */        }
/* 370:    */        
/* 371:371 */        this.i += 1;
/* 372:372 */        return LongArrays.copy(this.s, 0, length);
/* 373:    */      }
/* 374:    */      
/* 375:375 */      public long[] previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 376:376 */        this.inSync = false;
/* 377:377 */        return LongArrayFrontCodedList.this.getArray(--this.i);
/* 378:    */      }
/* 379:    */    };
/* 380:    */  }
/* 381:    */  
/* 386:386 */  public LongArrayFrontCodedList clone() { return this; }
/* 387:    */  
/* 388:    */  public String toString() {
/* 389:389 */    StringBuffer s = new StringBuffer();
/* 390:390 */    s.append("[ ");
/* 391:391 */    for (int i = 0; i < this.n; i++) {
/* 392:392 */      if (i != 0) s.append(", ");
/* 393:393 */      s.append(LongArrayList.wrap(getArray(i)).toString());
/* 394:    */    }
/* 395:395 */    s.append(" ]");
/* 396:396 */    return s.toString();
/* 397:    */  }
/* 398:    */  
/* 401:    */  protected long[] rebuildPointerArray()
/* 402:    */  {
/* 403:403 */    long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 404:404 */    long[][] a = this.array;
/* 405:    */    
/* 406:406 */    long pos = 0L;
/* 407:407 */    int i = 0;int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 408:408 */      int length = readInt(a, pos);
/* 409:409 */      int count = count(length);
/* 410:410 */      skip++; if (skip == this.ratio) {
/* 411:411 */        skip = 0;
/* 412:412 */        p[(j++)] = pos;
/* 413:413 */        pos += count + length;
/* 414:    */      } else {
/* 415:415 */        pos += count + count(readInt(a, pos + count)) + length;
/* 416:    */      } }
/* 417:417 */    return p;
/* 418:    */  }
/* 419:    */  
/* 420:420 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 421:    */    
/* 422:422 */    this.p = rebuildPointerArray();
/* 423:    */  }
/* 424:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongArrayFrontCodedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */