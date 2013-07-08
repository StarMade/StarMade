/*   1:    */package it.unimi.dsi.fastutil.shorts;
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
/* 112:    */public class ShortArrayFrontCodedList
/* 113:    */  extends AbstractObjectList<short[]>
/* 114:    */  implements Serializable, Cloneable
/* 115:    */{
/* 116:    */  public static final long serialVersionUID = 1L;
/* 117:    */  protected final int n;
/* 118:    */  protected final int ratio;
/* 119:    */  protected final short[][] array;
/* 120:    */  protected transient long[] p;
/* 121:    */  
/* 122:    */  public ShortArrayFrontCodedList(Iterator<short[]> arrays, int ratio)
/* 123:    */  {
/* 124:124 */    if (ratio < 1) throw new IllegalArgumentException("Illegal ratio (" + ratio + ")");
/* 125:125 */    short[][] array = ShortBigArrays.EMPTY_BIG_ARRAY;
/* 126:126 */    long[] p = LongArrays.EMPTY_ARRAY;
/* 127:127 */    short[][] a = new short[2][];
/* 128:128 */    long curSize = 0L;
/* 129:129 */    int n = 0;int b = 0;
/* 130:130 */    while (arrays.hasNext()) {
/* 131:131 */      a[b] = ((short[])arrays.next());
/* 132:132 */      int length = a[b].length;
/* 133:133 */      if (n % ratio == 0) {
/* 134:134 */        p = LongArrays.grow(p, n / ratio + 1);
/* 135:135 */        p[(n / ratio)] = curSize;
/* 136:136 */        array = ShortBigArrays.grow(array, curSize + count(length) + length, curSize);
/* 137:137 */        curSize += writeInt(array, length, curSize);
/* 138:138 */        ShortBigArrays.copyToBig(a[b], 0, array, curSize, length);
/* 139:139 */        curSize += length;
/* 140:    */      }
/* 141:    */      else {
/* 142:142 */        int minLength = a[(1 - b)].length;
/* 143:143 */        if (length < minLength) minLength = length;
/* 144:144 */        for (int common = 0; (common < minLength) && (a[0][common] == a[1][common]); common++) {}
/* 145:145 */        length -= common;
/* 146:146 */        array = ShortBigArrays.grow(array, curSize + count(length) + count(common) + length, curSize);
/* 147:147 */        curSize += writeInt(array, length, curSize);
/* 148:148 */        curSize += writeInt(array, common, curSize);
/* 149:149 */        ShortBigArrays.copyToBig(a[b], common, array, curSize, length);
/* 150:150 */        curSize += length;
/* 151:    */      }
/* 152:152 */      b = 1 - b;
/* 153:153 */      n++;
/* 154:    */    }
/* 155:155 */    this.n = n;
/* 156:156 */    this.ratio = ratio;
/* 157:157 */    this.array = ShortBigArrays.trim(array, curSize);
/* 158:158 */    this.p = LongArrays.trim(p, (n + ratio - 1) / ratio);
/* 159:    */  }
/* 160:    */  
/* 164:    */  public ShortArrayFrontCodedList(Collection<short[]> c, int ratio)
/* 165:    */  {
/* 166:166 */    this(c.iterator(), ratio);
/* 167:    */  }
/* 168:    */  
/* 174:    */  private static int readInt(short[][] a, long pos)
/* 175:    */  {
/* 176:176 */    short s0 = ShortBigArrays.get(a, pos);
/* 177:177 */    return s0 >= 0 ? s0 : s0 << 16 | ShortBigArrays.get(a, pos + 1L) & 0xFFFF;
/* 178:    */  }
/* 179:    */  
/* 183:    */  private static int count(int length)
/* 184:    */  {
/* 185:185 */    return length < 32768 ? 1 : 2;
/* 186:    */  }
/* 187:    */  
/* 192:    */  private static int writeInt(short[][] a, int length, long pos)
/* 193:    */  {
/* 194:194 */    if (length < 32768) {
/* 195:195 */      ShortBigArrays.set(a, pos, (short)length);
/* 196:196 */      return 1;
/* 197:    */    }
/* 198:198 */    ShortBigArrays.set(a, pos++, (short)(-(length >>> 16) - 1));
/* 199:199 */    ShortBigArrays.set(a, pos, (short)(length & 0xFFFF));
/* 200:200 */    return 2;
/* 201:    */  }
/* 202:    */  
/* 205:    */  public int ratio()
/* 206:    */  {
/* 207:207 */    return this.ratio;
/* 208:    */  }
/* 209:    */  
/* 215:    */  private int length(int index)
/* 216:    */  {
/* 217:217 */    short[][] array = this.array;
/* 218:218 */    int delta = index % this.ratio;
/* 219:219 */    long pos = this.p[(index / this.ratio)];
/* 220:220 */    int length = readInt(array, pos);
/* 221:221 */    if (delta == 0) { return length;
/* 222:    */    }
/* 223:    */    
/* 224:224 */    pos += count(length) + length;
/* 225:225 */    length = readInt(array, pos);
/* 226:226 */    int common = readInt(array, pos + count(length));
/* 227:227 */    for (int i = 0; i < delta - 1; i++) {
/* 228:228 */      pos += count(length) + count(common) + length;
/* 229:229 */      length = readInt(array, pos);
/* 230:230 */      common = readInt(array, pos + count(length));
/* 231:    */    }
/* 232:232 */    return length + common;
/* 233:    */  }
/* 234:    */  
/* 238:    */  public int arrayLength(int index)
/* 239:    */  {
/* 240:240 */    ensureRestrictedIndex(index);
/* 241:241 */    return length(index);
/* 242:    */  }
/* 243:    */  
/* 250:    */  private int extract(int index, short[] a, int offset, int length)
/* 251:    */  {
/* 252:252 */    int delta = index % this.ratio;
/* 253:253 */    long startPos = this.p[(index / this.ratio)];
/* 254:    */    long pos;
/* 255:255 */    int arrayLength = readInt(this.array, pos = startPos);int currLen = 0;
/* 256:256 */    if (delta == 0) {
/* 257:257 */      pos = this.p[(index / this.ratio)] + count(arrayLength);
/* 258:258 */      ShortBigArrays.copyFromBig(this.array, pos, a, offset, Math.min(length, arrayLength));
/* 259:259 */      return arrayLength;
/* 260:    */    }
/* 261:261 */    int common = 0;
/* 262:262 */    for (int i = 0; i < delta; i++) {
/* 263:263 */      long prevArrayPos = pos + count(arrayLength) + (i != 0 ? count(common) : 0);
/* 264:264 */      pos = prevArrayPos + arrayLength;
/* 265:265 */      arrayLength = readInt(this.array, pos);
/* 266:266 */      common = readInt(this.array, pos + count(arrayLength));
/* 267:267 */      int actualCommon = Math.min(common, length);
/* 268:268 */      if (actualCommon <= currLen) { currLen = actualCommon;
/* 269:    */      } else {
/* 270:270 */        ShortBigArrays.copyFromBig(this.array, prevArrayPos, a, currLen + offset, actualCommon - currLen);
/* 271:271 */        currLen = actualCommon;
/* 272:    */      }
/* 273:    */    }
/* 274:274 */    if (currLen < length) ShortBigArrays.copyFromBig(this.array, pos + count(arrayLength) + count(common), a, currLen + offset, Math.min(arrayLength, length - currLen));
/* 275:275 */    return arrayLength + common;
/* 276:    */  }
/* 277:    */  
/* 278:278 */  public short[] get(int index) { return getArray(index); }
/* 279:    */  
/* 282:    */  public short[] getArray(int index)
/* 283:    */  {
/* 284:284 */    ensureRestrictedIndex(index);
/* 285:285 */    int length = length(index);
/* 286:286 */    short[] a = new short[length];
/* 287:287 */    extract(index, a, 0, length);
/* 288:288 */    return a;
/* 289:    */  }
/* 290:    */  
/* 298:    */  public int get(int index, short[] a, int offset, int length)
/* 299:    */  {
/* 300:300 */    ensureRestrictedIndex(index);
/* 301:301 */    ShortArrays.ensureOffsetLength(a, offset, length);
/* 302:302 */    int arrayLength = extract(index, a, offset, length);
/* 303:303 */    if (length >= arrayLength) return arrayLength;
/* 304:304 */    return length - arrayLength;
/* 305:    */  }
/* 306:    */  
/* 312:    */  public int get(int index, short[] a)
/* 313:    */  {
/* 314:314 */    return get(index, a, 0, a.length);
/* 315:    */  }
/* 316:    */  
/* 317:317 */  public int size() { return this.n; }
/* 318:    */  
/* 319:    */  public ObjectListIterator<short[]> listIterator(final int start) {
/* 320:320 */    ensureIndex(start);
/* 321:321 */    new AbstractObjectListIterator() {
/* 322:322 */      short[] s = ShortArrays.EMPTY_ARRAY;
/* 323:323 */      int i = 0;
/* 324:324 */      long pos = 0L;
/* 325:    */      
/* 330:    */      boolean inSync;
/* 331:    */      
/* 336:    */      public boolean hasNext()
/* 337:    */      {
/* 338:338 */        return this.i < ShortArrayFrontCodedList.this.n;
/* 339:    */      }
/* 340:    */      
/* 341:341 */      public boolean hasPrevious() { return this.i > 0; }
/* 342:    */      
/* 343:    */      public int previousIndex() {
/* 344:344 */        return this.i - 1;
/* 345:    */      }
/* 346:    */      
/* 347:347 */      public int nextIndex() { return this.i; }
/* 348:    */      
/* 349:    */      public short[] next()
/* 350:    */      {
/* 351:351 */        if (!hasNext()) throw new NoSuchElementException();
/* 352:352 */        int length; if (this.i % ShortArrayFrontCodedList.this.ratio == 0) {
/* 353:353 */          this.pos = ShortArrayFrontCodedList.this.p[(this.i / ShortArrayFrontCodedList.this.ratio)];
/* 354:354 */          int length = ShortArrayFrontCodedList.readInt(ShortArrayFrontCodedList.this.array, this.pos);
/* 355:355 */          this.s = ShortArrays.ensureCapacity(this.s, length, 0);
/* 356:356 */          ShortBigArrays.copyFromBig(ShortArrayFrontCodedList.this.array, this.pos + ShortArrayFrontCodedList.count(length), this.s, 0, length);
/* 357:357 */          this.pos += length + ShortArrayFrontCodedList.count(length);
/* 358:358 */          this.inSync = true;
/* 360:    */        }
/* 361:361 */        else if (this.inSync) {
/* 362:362 */          int length = ShortArrayFrontCodedList.readInt(ShortArrayFrontCodedList.this.array, this.pos);
/* 363:363 */          int common = ShortArrayFrontCodedList.readInt(ShortArrayFrontCodedList.this.array, this.pos + ShortArrayFrontCodedList.access$100(length));
/* 364:364 */          this.s = ShortArrays.ensureCapacity(this.s, length + common, common);
/* 365:365 */          ShortBigArrays.copyFromBig(ShortArrayFrontCodedList.this.array, this.pos + ShortArrayFrontCodedList.count(length) + ShortArrayFrontCodedList.count(common), this.s, common, length);
/* 366:366 */          this.pos += ShortArrayFrontCodedList.count(length) + ShortArrayFrontCodedList.count(common) + length;
/* 367:367 */          length += common;
/* 368:    */        }
/* 369:    */        else {
/* 370:370 */          this.s = ShortArrays.ensureCapacity(this.s, length = ShortArrayFrontCodedList.this.length(this.i), 0);
/* 371:371 */          ShortArrayFrontCodedList.this.extract(this.i, this.s, 0, length);
/* 372:    */        }
/* 373:    */        
/* 374:374 */        this.i += 1;
/* 375:375 */        return ShortArrays.copy(this.s, 0, length);
/* 376:    */      }
/* 377:    */      
/* 378:378 */      public short[] previous() { if (!hasPrevious()) throw new NoSuchElementException();
/* 379:379 */        this.inSync = false;
/* 380:380 */        return ShortArrayFrontCodedList.this.getArray(--this.i);
/* 381:    */      }
/* 382:    */    };
/* 383:    */  }
/* 384:    */  
/* 389:389 */  public ShortArrayFrontCodedList clone() { return this; }
/* 390:    */  
/* 391:    */  public String toString() {
/* 392:392 */    StringBuffer s = new StringBuffer();
/* 393:393 */    s.append("[ ");
/* 394:394 */    for (int i = 0; i < this.n; i++) {
/* 395:395 */      if (i != 0) s.append(", ");
/* 396:396 */      s.append(ShortArrayList.wrap(getArray(i)).toString());
/* 397:    */    }
/* 398:398 */    s.append(" ]");
/* 399:399 */    return s.toString();
/* 400:    */  }
/* 401:    */  
/* 404:    */  protected long[] rebuildPointerArray()
/* 405:    */  {
/* 406:406 */    long[] p = new long[(this.n + this.ratio - 1) / this.ratio];
/* 407:407 */    short[][] a = this.array;
/* 408:    */    
/* 409:409 */    long pos = 0L;
/* 410:410 */    int i = 0;int j = 0; for (int skip = this.ratio - 1; i < this.n; i++) {
/* 411:411 */      int length = readInt(a, pos);
/* 412:412 */      int count = count(length);
/* 413:413 */      skip++; if (skip == this.ratio) {
/* 414:414 */        skip = 0;
/* 415:415 */        p[(j++)] = pos;
/* 416:416 */        pos += count + length;
/* 417:    */      } else {
/* 418:418 */        pos += count + count(readInt(a, pos + count)) + length;
/* 419:    */      } }
/* 420:420 */    return p;
/* 421:    */  }
/* 422:    */  
/* 423:423 */  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { s.defaultReadObject();
/* 424:    */    
/* 425:425 */    this.p = rebuildPointerArray();
/* 426:    */  }
/* 427:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortArrayFrontCodedList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */