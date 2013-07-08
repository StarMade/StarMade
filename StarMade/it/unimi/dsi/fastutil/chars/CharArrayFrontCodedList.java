/*   1:    */package it.unimi.dsi.fastutil.chars;
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
/* 112:    */public class CharArrayFrontCodedList
/* 113:    */  extends AbstractObjectList<char[]>
/* 114:    */  implements Serializable, Cloneable
/* 115:    */{
/* 116:    */  public static final long serialVersionUID = 1L;
/* 117:    */  protected final int n;
/* 118:    */  protected final int ratio;
/* 119:    */  protected final char[][] array;
/* 120:    */  protected transient long[] p;
/* 121:    */  
/* 122:    */  public CharArrayFrontCodedList(Iterator<char[]> arrays, int ratio)
/* 123:    */  {
/* 124:124 */    if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125:125 */    char[][] array = CharBigArrays.EMPTY_BIG_ARRAY;
/* 126:126 */    long[] p = LongArrays.EMPTY_ARRAY;
/* 127:127 */    char[][] a = new char[2][];
/* 128:128 */    long curSize = 0L;
/* 129:129 */    int n = 0;int b = 0;
/* 130:130 */    while (arrays.hasNext()) {
/* 131:131 */      a[b] = ((char[])arrays.next());
/* 132:132 */      int length = a[b].length;
/* 133:133 */      if (n % ratio == 0) {
/* 134:134 */        p = LongArrays.grow(p, n / ratio + 1);
/* 135:135 */        p[(n / ratio)] = curSize;
/* 136:136 */        array = CharBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137:137 */        curSize += writeInt(array, length, curSize);
/* 138:138 */        CharBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139:139 */        curSize += length;
/* 140:    */      }
/* 141:    */      else {
/* 142:142 */        int minLength = a[(1 - b)].length;
/* 143:143 */        if (length < minLength) minLength = length;
/* 144:144 */        for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++) {}
/* 145:145 */        length -= common;
/* 146:146 */        array = CharBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147:147 */        curSize += writeInt(array, length, curSize);
/* 148:148 */        curSize += writeInt(array, common, curSize);
/* 149:149 */        CharBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150:150 */        curSize += length;
/* 151:    */      }
/* 152:152 */      b = 1 - b;
/* 153:153 */      n++;
/* 154:    */    }
/* 155:155 */    this.n = n;
/* 156:156 */    this.ratio = ratio;
/* 157:157 */    this.array = CharBigArrays.trim(array, curSize);
/* 158:158 */    this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/* 159:    */  }
/* 160:    */  
/* 164:    */  public CharArrayFrontCodedList(Collection<char[]> c, int ratio)
/* 165:    */  {
/* 166:166 */    this(c.iterator(), ratio);
/* 167:    */  }
/* 168:    */  
/* 179:    */  private static int readInt(char[][] a, long pos)
/* 180:    */  {
/* 181:181 */    char c0 = CharBigArrays.get(a, pos);
/* 182:182 */    return c0 < 32768 ? c0 : (c0 & 0x7FFF) << '\020' | CharBigArrays.get(a, pos + 1L);
/* 183:    */  }
/* 184:    */  
/* 188:    */  private static int count(int length)
/* 189:    */  {
/* 190:190 */    return length < 32768 ? 1 : 2;
/* 191:    */  }
/* 192:    */  
/* 197:    */  private static int writeInt(char[][] a, int length, long pos)
/* 198:    */  {
/* 199:199 */    if (length < 32768) {
/* 200:200 */      CharBigArrays.set(a, pos, (char)length);
/* 201:201 */      return 1;
/* 202:    */    }
/* 203:203 */    CharBigArrays.set(a, pos++, (char)(length >>> 16 | 0x8000));
/* 204:204 */    CharBigArrays.set(a, pos, (char)(length & 0xFFFF));
/* 205:205 */    return 2;
/* 206:    */  }
/* 207:    */  
/* 210:    */  public int ratio()
/* 211:    */  {
/* 212:212 */    return this.ratio;
/* 213:    */  }
/* 214:    */  
/* 220:    */  private int length(int index)
/* 221:    */  {
/* 222:222 */    char[][] array = this.array;
/* 223:223 */    int delta = index % this.ratio;
/* 224:224 */    long pos = this.p[(index / this.ratio)];
/* 225:225 */    int length = readInt(array, pos);
/* 226:226 */    if (delta == 0) { return length;
/* 227:    */    }
/* 228:    */    
/* 229:229 */    pos += count(length) + length;
/* 230:230 */    length = readInt(array, pos);
/* 231:231 */    int common = readInt(array, pos + count(length));
/* 232:232 */    for (int i = 0; i < delta - 1; i++) {
/* 233:233 */      pos += count(length) + count(common) + length;
/* 234:234 */      length = readInt(array, pos);
/* 235:235 */      common = readInt(array, pos + count(length));
/* 236:    */    }
/* 237:237 */    return length + common;
/* 238:    */  }
/* 239:    */  
/* 243:    */  public int arrayLength(int index)
/* 244:    */  {
/* 245:245 */    ensureRestrictedIndex(index);
/* 246:246 */    return length(index);
/* 247:    */  }
/* 248:    */  
/* 255:    */  private int extract(int index, char[] a, int offset, int length)
/* 256:    */  {
/* 257:257 */    int delta = index % this.ratio;
/* 258:258 */    long startPos = this.p[(index / this.ratio)];
/* 259:    */    long pos;
/* 260:260 */    int arrayLength = readInt(this.array, pos = startPos);int currLen = 0;
/* 261:261 */    if (delta == 0) {
/* 262:262 */      pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 263:263 */      CharBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 264:264 */      return arrayLength;
/* 265:    */    }
/* 266:266 */    int common = 0;
/* 267:267 */    for (int i = 0; i < delta; i++) {
/* 268:268 */      long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 269:269 */      pos = prevArrayPos + arrayLength;
/* 270:270 */      arrayLength = readInt(this.array, pos);
/* 271:271 */      common = readInt(this.array, pos + count(arrayLength));
/* 272:272 */      int actualCommon = Math.min(common, length);
/* 273:273 */      if (actualCommon <= currLen) { currLen = actualCommon;
/* 274:    */      } else {
/* 275:275 */        CharBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 276:276 */        currLen = actualCommon;
/* 277:    */      }
/* 278:    */    }
/* 279:279 */    if (currLen < length) CharBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 280:280 */    return arrayLength + common;
/* 281:    */  }
/* 282:    */  
/* 283:283 */  public char[] get(int index) { return getArray(index); }
/* 284:    */  
/* 287:    */  public char[] getArray(int index)
/* 288:    */  {
/* 289:289 */    ensureRestrictedIndex(index);
/* 290:290 */    int length = length(index);
/* 291:291 */    char[] a = new char[length];
/* 292:292 */    extract(index, a, 0, length);
/* 293:293 */    return a;
/* 294:    */  }
/* 295:    */  
/* 303:    */  public int get(int index, char[] a, int offset, int length)
/* 304:    */  {
/* 305:305 */    ensureRestrictedIndex(index);
/* 306:306 */    CharArrays.ensureOffsetLength(a, offset, length);
/* 307:307 */    int arrayLength = extract(index, a, offset, length);
/* 308:308 */    if (length >= arrayLength) return arrayLength;
/* 309:309 */    return length - arrayLength;
/* 310:    */  }
/* 311:    */  
/* 317:    */  public int get(int index, char[] a)
/* 318:    */  {
/* 319:319 */    return get(index, a, 0, a.length);
/* 320:    */  }
/* 321:    */  
/* 322:322 */  public int size() { return this.n; }
/* 323:    */  
/* 324:    */  public ObjectListIterator<char[]> listIterator(final int start) {
/* 325:325 */    ensureIndex(start);
/* 326:326 */    new AbstractObjectListIterator() {
/* 327:327 */      char[] s = CharArrays.EMPTY_ARRAY;
/* 328:328 */      int i = 0;
/* 329:329 */      long pos = 0L;
/* 330:    */      
/* 335:    */      boolean inSync;
/* 336:    */      
/* 341:    */      public boolean hasNext()
/* 342:    */      {
/* 343:343 */        return this.i < CharArrayFrontCodedList.this.n;
/* 344:    */      }
/* 345:    */      
/* 346:346 */      public boolean hasPrevious() { return this.i > 0; }
/* 347:    */      
/* 348:    */      public int previousIndex() {
/* 349:349 */        return this.i - 1;
/* 350:    */      }
/* 351:    */      
/* 352:352 */      public int nextIndex() { return this.i; }
/* 353:    */      
/* 354:    */      public char[] next()
/* 355:    */      {
/* 356:356 */        if (!hasNext()) throw new NoSuchElementException();
/* 357:357 */        int length; if (this.i % CharArrayFrontCodedList.this.ratio == 0) {
/* 358:358 */          this.pos = CharArrayFrontCodedList.this.p[(this.i / CharArrayFrontCodedList.this.ratio)];
/* 359:359 */          int length = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos);
/* 360:360 */          this.s = CharArrays.ensureCapacity(this.s, length, 0);
/* 361:361 */          CharBigArrays.copyFromBig(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.count(length), this.s, 0, length);
/* 362:362 */          this.pos += length + CharArrayFrontCodedList.count(length);
/* 363:363 */          this.inSync = true;
/* 365:    */        }
/* 366:366 */        else if (this.inSync) {
/* 367:367 */          int length = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos);
/* 368:368 */          int common = CharArrayFrontCodedList.readInt(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.access$100(length));
/* 369:369 */          this.s = CharArrays.ensureCapacity(this.s, length + common, common);
/* 370:370 */          CharBigArrays.copyFromBig(CharArrayFrontCodedList.this.array, this.pos + CharArrayFrontCodedList.count(length) + CharArrayFrontCodedList.count(common), this.s, common, length);
/* 371:371 */          this.pos += CharArrayFrontCodedList.count(length) + CharArrayFrontCodedList.count(common) + length;
/* 372:372 */          length += common;
/* 373:    */        }
/* 374:    */        else {
/* 375:375 */          this.s = CharArrays.ensureCapacity(this.s, length = CharArrayFrontCodedList.this.length(this.i), 0);
/* 376:376 */          CharArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/* 377:    */        }
/* 378:    */        
/* 379:379 */        this.i += 1;
/* 380:380 */        return CharArrays.copy(this.s, 0, length);
/* 381:    */      }
/* 382:    */      
/* 383:383 */      public char[] previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 384:384 */        this.inSync = false;
/* 385:385 */        return CharArrayFrontCodedList.this.getArray(--this.i);
/* 386:    */      }
/* 387:    */    };
/* 388:    */  }
/* 389:    */  
/* 394:394 */  public CharArrayFrontCodedList clone() { return this; }
/* 395:    */  
/* 396:    */  public String toString() {
/* 397:397 */    StringBuffer s = new StringBuffer();
/* 398:398 */    s.append("[ ");
/* 399:399 */    for (int i = 0; i < this.n; i++) {
/* 400:400 */      if (i != 0) s.append(", ");
/* 401:401 */      s.append(CharArrayList.wrap(getArray(i)).toString());
/* 402:    */    }
/* 403:403 */    s.append(" ]");
/* 404:404 */    return s.toString();
/* 405:    */  }
/* 406:    */  
/* 409:    */  protected long[] rebuildPointerArray()
/* 410:    */  {
/* 411:411 */    long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 412:412 */    char[][] a = this.array;
/* 413:    */    
/* 414:414 */    long pos = 0L;
/* 415:415 */    int i = 0;int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 416:416 */      int length = readInt(a, pos);
/* 417:417 */      int count = count(length);
/* 418:418 */      skip++; if (skip == this.ratio) {
/* 419:419 */        skip = 0;
/* 420:420 */        p[(j++)] = pos;
/* 421:421 */        pos += count + length;
/* 422:    */      } else {
/* 423:423 */        pos += count + count(readInt(a, pos + count)) + length;
/* 424:    */      } }
/* 425:425 */    return p;
/* 426:    */  }
/* 427:    */  
/* 428:428 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 429:    */    
/* 430:430 */    this.p = rebuildPointerArray();
/* 431:    */  }
/* 432:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayFrontCodedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */