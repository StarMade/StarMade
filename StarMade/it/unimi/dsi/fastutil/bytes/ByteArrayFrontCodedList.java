/*   1:    */package it.unimi.dsi.fastutil.bytes;
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
/* 112:    */public class ByteArrayFrontCodedList
/* 113:    */  extends AbstractObjectList<byte[]>
/* 114:    */  implements Serializable, Cloneable
/* 115:    */{
/* 116:    */  public static final long serialVersionUID = 1L;
/* 117:    */  protected final int n;
/* 118:    */  protected final int ratio;
/* 119:    */  protected final byte[][] array;
/* 120:    */  protected transient long[] p;
/* 121:    */  
/* 122:    */  public ByteArrayFrontCodedList(Iterator<byte[]> arrays, int ratio)
/* 123:    */  {
/* 124:124 */    if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125:125 */    byte[][] array = ByteBigArrays.EMPTY_BIG_ARRAY;
/* 126:126 */    long[] p = LongArrays.EMPTY_ARRAY;
/* 127:127 */    byte[][] a = new byte[2][];
/* 128:128 */    long curSize = 0L;
/* 129:129 */    int n = 0;int b = 0;
/* 130:130 */    while (arrays.hasNext()) {
/* 131:131 */      a[b] = ((byte[])arrays.next());
/* 132:132 */      int length = a[b].length;
/* 133:133 */      if (n % ratio == 0) {
/* 134:134 */        p = LongArrays.grow(p, n / ratio + 1);
/* 135:135 */        p[(n / ratio)] = curSize;
/* 136:136 */        array = ByteBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137:137 */        curSize += writeInt(array, length, curSize);
/* 138:138 */        ByteBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139:139 */        curSize += length;
/* 140:    */      }
/* 141:    */      else {
/* 142:142 */        int minLength = a[(1 - b)].length;
/* 143:143 */        if (length < minLength) minLength = length;
/* 144:144 */        for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++) {}
/* 145:145 */        length -= common;
/* 146:146 */        array = ByteBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147:147 */        curSize += writeInt(array, length, curSize);
/* 148:148 */        curSize += writeInt(array, common, curSize);
/* 149:149 */        ByteBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150:150 */        curSize += length;
/* 151:    */      }
/* 152:152 */      b = 1 - b;
/* 153:153 */      n++;
/* 154:    */    }
/* 155:155 */    this.n = n;
/* 156:156 */    this.ratio = ratio;
/* 157:157 */    this.array = ByteBigArrays.trim(array, curSize);
/* 158:158 */    this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/* 159:    */  }
/* 160:    */  
/* 164:    */  public ByteArrayFrontCodedList(Collection<byte[]> c, int ratio)
/* 165:    */  {
/* 166:166 */    this(c.iterator(), ratio);
/* 167:    */  }
/* 168:    */  
/* 174:    */  private static int readInt(byte[][] a, long pos)
/* 175:    */  {
/* 176:176 */    byte b0 = ByteBigArrays.get(a, pos);
/* 177:177 */    if (b0 >= 0) return b0;
/* 178:178 */    byte b1 = ByteBigArrays.get(a, pos + 1L);
/* 179:179 */    if (b1 >= 0) return -b0 - 1 << 7 | b1;
/* 180:180 */    byte b2 = ByteBigArrays.get(a, pos + 2L);
/* 181:181 */    if (b2 >= 0) return -b0 - 1 << 14 | -b1 - 1 << 7 | b2;
/* 182:182 */    byte b3 = ByteBigArrays.get(a, pos + 3L);
/* 183:183 */    if (b3 >= 0) return -b0 - 1 << 21 | -b1 - 1 << 14 | -b2 - 1 << 7 | b3;
/* 184:184 */    return -b0 - 1 << 28 | -b1 - 1 << 21 | -b2 - 1 << 14 | -b3 - 1 << 7 | ByteBigArrays.get(a, pos + 4L);
/* 185:    */  }
/* 186:    */  
/* 190:    */  private static int count(int length)
/* 191:    */  {
/* 192:192 */    if (length < 128) return 1;
/* 193:193 */    if (length < 16384) return 2;
/* 194:194 */    if (length < 2097152) return 3;
/* 195:195 */    if (length < 268435456) return 4;
/* 196:196 */    return 5;
/* 197:    */  }
/* 198:    */  
/* 203:    */  private static int writeInt(byte[][] a, int length, long pos)
/* 204:    */  {
/* 205:205 */    int count = count(length);
/* 206:206 */    ByteBigArrays.set(a, pos + count - 1L, (byte)(length & 0x7F));
/* 207:207 */    if (count != 1) {
/* 208:208 */      int i = count - 1;
/* 209:209 */      while (i-- != 0) {
/* 210:210 */        length >>>= 7;
/* 211:211 */        ByteBigArrays.set(a, pos + i, (byte)(-(length & 0x7F) - 1));
/* 212:    */      }
/* 213:    */    }
/* 214:214 */    return count;
/* 215:    */  }
/* 216:    */  
/* 219:    */  public int ratio()
/* 220:    */  {
/* 221:221 */    return this.ratio;
/* 222:    */  }
/* 223:    */  
/* 229:    */  private int length(int index)
/* 230:    */  {
/* 231:231 */    byte[][] array = this.array;
/* 232:232 */    int delta = index % this.ratio;
/* 233:233 */    long pos = this.p[(index / this.ratio)];
/* 234:234 */    int length = readInt(array, pos);
/* 235:235 */    if (delta == 0) { return length;
/* 236:    */    }
/* 237:    */    
/* 238:238 */    pos += count(length) + length;
/* 239:239 */    length = readInt(array, pos);
/* 240:240 */    int common = readInt(array, pos + count(length));
/* 241:241 */    for (int i = 0; i < delta - 1; i++) {
/* 242:242 */      pos += count(length) + count(common) + length;
/* 243:243 */      length = readInt(array, pos);
/* 244:244 */      common = readInt(array, pos + count(length));
/* 245:    */    }
/* 246:246 */    return length + common;
/* 247:    */  }
/* 248:    */  
/* 252:    */  public int arrayLength(int index)
/* 253:    */  {
/* 254:254 */    ensureRestrictedIndex(index);
/* 255:255 */    return length(index);
/* 256:    */  }
/* 257:    */  
/* 264:    */  private int extract(int index, byte[] a, int offset, int length)
/* 265:    */  {
/* 266:266 */    int delta = index % this.ratio;
/* 267:267 */    long startPos = this.p[(index / this.ratio)];
/* 268:    */    long pos;
/* 269:269 */    int arrayLength = readInt(this.array, pos = startPos);int currLen = 0;
/* 270:270 */    if (delta == 0) {
/* 271:271 */      pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 272:272 */      ByteBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 273:273 */      return arrayLength;
/* 274:    */    }
/* 275:275 */    int common = 0;
/* 276:276 */    for (int i = 0; i < delta; i++) {
/* 277:277 */      long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 278:278 */      pos = prevArrayPos + arrayLength;
/* 279:279 */      arrayLength = readInt(this.array, pos);
/* 280:280 */      common = readInt(this.array, pos + count(arrayLength));
/* 281:281 */      int actualCommon = Math.min(common, length);
/* 282:282 */      if (actualCommon <= currLen) { currLen = actualCommon;
/* 283:    */      } else {
/* 284:284 */        ByteBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 285:285 */        currLen = actualCommon;
/* 286:    */      }
/* 287:    */    }
/* 288:288 */    if (currLen < length) ByteBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 289:289 */    return arrayLength + common;
/* 290:    */  }
/* 291:    */  
/* 292:292 */  public byte[] get(int index) { return getArray(index); }
/* 293:    */  
/* 296:    */  public byte[] getArray(int index)
/* 297:    */  {
/* 298:298 */    ensureRestrictedIndex(index);
/* 299:299 */    int length = length(index);
/* 300:300 */    byte[] a = new byte[length];
/* 301:301 */    extract(index, a, 0, length);
/* 302:302 */    return a;
/* 303:    */  }
/* 304:    */  
/* 312:    */  public int get(int index, byte[] a, int offset, int length)
/* 313:    */  {
/* 314:314 */    ensureRestrictedIndex(index);
/* 315:315 */    ByteArrays.ensureOffsetLength(a, offset, length);
/* 316:316 */    int arrayLength = extract(index, a, offset, length);
/* 317:317 */    if (length >= arrayLength) return arrayLength;
/* 318:318 */    return length - arrayLength;
/* 319:    */  }
/* 320:    */  
/* 326:    */  public int get(int index, byte[] a)
/* 327:    */  {
/* 328:328 */    return get(index, a, 0, a.length);
/* 329:    */  }
/* 330:    */  
/* 331:331 */  public int size() { return this.n; }
/* 332:    */  
/* 333:    */  public ObjectListIterator<byte[]> listIterator(final int start) {
/* 334:334 */    ensureIndex(start);
/* 335:335 */    new AbstractObjectListIterator() {
/* 336:336 */      byte[] s = ByteArrays.EMPTY_ARRAY;
/* 337:337 */      int i = 0;
/* 338:338 */      long pos = 0L;
/* 339:    */      
/* 344:    */      boolean inSync;
/* 345:    */      
/* 350:    */      public boolean hasNext()
/* 351:    */      {
/* 352:352 */        return this.i < ByteArrayFrontCodedList.this.n;
/* 353:    */      }
/* 354:    */      
/* 355:355 */      public boolean hasPrevious() { return this.i > 0; }
/* 356:    */      
/* 357:    */      public int previousIndex() {
/* 358:358 */        return this.i - 1;
/* 359:    */      }
/* 360:    */      
/* 361:361 */      public int nextIndex() { return this.i; }
/* 362:    */      
/* 363:    */      public byte[] next()
/* 364:    */      {
/* 365:365 */        if (!hasNext()) throw new NoSuchElementException();
/* 366:366 */        int length; if (this.i % ByteArrayFrontCodedList.this.ratio == 0) {
/* 367:367 */          this.pos = ByteArrayFrontCodedList.this.p[(this.i / ByteArrayFrontCodedList.this.ratio)];
/* 368:368 */          int length = ByteArrayFrontCodedList.readInt(ByteArrayFrontCodedList.this.array, this.pos);
/* 369:369 */          this.s = ByteArrays.ensureCapacity(this.s, length, 0);
/* 370:370 */          ByteBigArrays.copyFromBig(ByteArrayFrontCodedList.this.array, this.pos + ByteArrayFrontCodedList.count(length), this.s, 0, length);
/* 371:371 */          this.pos += length + ByteArrayFrontCodedList.count(length);
/* 372:372 */          this.inSync = true;
/* 374:    */        }
/* 375:375 */        else if (this.inSync) {
/* 376:376 */          int length = ByteArrayFrontCodedList.readInt(ByteArrayFrontCodedList.this.array, this.pos);
/* 377:377 */          int common = ByteArrayFrontCodedList.readInt(ByteArrayFrontCodedList.this.array, this.pos + ByteArrayFrontCodedList.access$100(length));
/* 378:378 */          this.s = ByteArrays.ensureCapacity(this.s, length + common, common);
/* 379:379 */          ByteBigArrays.copyFromBig(ByteArrayFrontCodedList.this.array, this.pos + ByteArrayFrontCodedList.count(length) + ByteArrayFrontCodedList.count(common), this.s, common, length);
/* 380:380 */          this.pos += ByteArrayFrontCodedList.count(length) + ByteArrayFrontCodedList.count(common) + length;
/* 381:381 */          length += common;
/* 382:    */        }
/* 383:    */        else {
/* 384:384 */          this.s = ByteArrays.ensureCapacity(this.s, length = ByteArrayFrontCodedList.this.length(this.i), 0);
/* 385:385 */          ByteArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/* 386:    */        }
/* 387:    */        
/* 388:388 */        this.i += 1;
/* 389:389 */        return ByteArrays.copy(this.s, 0, length);
/* 390:    */      }
/* 391:    */      
/* 392:392 */      public byte[] previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 393:393 */        this.inSync = false;
/* 394:394 */        return ByteArrayFrontCodedList.this.getArray(--this.i);
/* 395:    */      }
/* 396:    */    };
/* 397:    */  }
/* 398:    */  
/* 403:403 */  public ByteArrayFrontCodedList clone() { return this; }
/* 404:    */  
/* 405:    */  public String toString() {
/* 406:406 */    StringBuffer s = new StringBuffer();
/* 407:407 */    s.append("[ ");
/* 408:408 */    for (int i = 0; i < this.n; i++) {
/* 409:409 */      if (i != 0) s.append(", ");
/* 410:410 */      s.append(ByteArrayList.wrap(getArray(i)).toString());
/* 411:    */    }
/* 412:412 */    s.append(" ]");
/* 413:413 */    return s.toString();
/* 414:    */  }
/* 415:    */  
/* 418:    */  protected long[] rebuildPointerArray()
/* 419:    */  {
/* 420:420 */    long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 421:421 */    byte[][] a = this.array;
/* 422:    */    
/* 423:423 */    long pos = 0L;
/* 424:424 */    int i = 0;int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 425:425 */      int length = readInt(a, pos);
/* 426:426 */      int count = count(length);
/* 427:427 */      skip++; if (skip == this.ratio) {
/* 428:428 */        skip = 0;
/* 429:429 */        p[(j++)] = pos;
/* 430:430 */        pos += count + length;
/* 431:    */      } else {
/* 432:432 */        pos += count + count(readInt(a, pos + count)) + length;
/* 433:    */      } }
/* 434:434 */    return p;
/* 435:    */  }
/* 436:    */  
/* 437:437 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 438:    */    
/* 439:439 */    this.p = rebuildPointerArray();
/* 440:    */  }
/* 441:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrayFrontCodedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */