/*   1:    */package it.unimi.dsi.fastutil;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.IntBigArrays;
/*   4:    */import it.unimi.dsi.fastutil.longs.LongComparator;
/*   5:    */import java.io.PrintStream;
/*   6:    */
/* 136:    */public class BigArrays
/* 137:    */{
/* 138:    */  public static final int SEGMENT_SHIFT = 27;
/* 139:    */  public static final int SEGMENT_SIZE = 134217728;
/* 140:    */  public static final int SEGMENT_MASK = 134217727;
/* 141:    */  private static final int SMALL = 7;
/* 142:    */  private static final int MEDIUM = 40;
/* 143:    */  
/* 144:    */  public static int segment(long index)
/* 145:    */  {
/* 146:146 */    return (int)(index >>> 27);
/* 147:    */  }
/* 148:    */  
/* 153:    */  public static int displacement(long index)
/* 154:    */  {
/* 155:155 */    return (int)(index & 0x7FFFFFF);
/* 156:    */  }
/* 157:    */  
/* 162:    */  public static long start(int segment)
/* 163:    */  {
/* 164:164 */    return segment << 27;
/* 165:    */  }
/* 166:    */  
/* 173:    */  public static long index(int segment, int displacement)
/* 174:    */  {
/* 175:175 */    return start(segment) + displacement;
/* 176:    */  }
/* 177:    */  
/* 187:    */  public static void ensureFromTo(long bigArrayLength, long from, long to)
/* 188:    */  {
/* 189:189 */    if (from < 0L) throw new ArrayIndexOutOfBoundsException("Start index (" + from + ") is negative");
/* 190:190 */    if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/* 191:191 */    if (to > bigArrayLength) { throw new ArrayIndexOutOfBoundsException("End index (" + to + ") is greater than big-array length (" + bigArrayLength + ")");
/* 192:    */    }
/* 193:    */  }
/* 194:    */  
/* 203:    */  public static void ensureOffsetLength(long bigArrayLength, long offset, long length)
/* 204:    */  {
/* 205:205 */    if (offset < 0L) throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
/* 206:206 */    if (length < 0L) throw new IllegalArgumentException("Length (" + length + ") is negative");
/* 207:207 */    if (offset + length > bigArrayLength) { throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than big-array length (" + bigArrayLength + ")");
/* 208:    */    }
/* 209:    */  }
/* 210:    */  
/* 219:    */  private static void inPlaceMerge(long from, long mid, long to, LongComparator comp, BigSwapper swapper)
/* 220:    */  {
/* 221:221 */    if ((from >= mid) || (mid >= to)) return;
/* 222:222 */    if (to - from == 2L) {
/* 223:223 */      if (comp.compare(mid, from) < 0)
/* 224:224 */        swapper.swap(from, mid);
/* 225:    */      return;
/* 226:    */    }
/* 227:    */    long secondCut;
/* 228:    */    long secondCut;
/* 229:    */    long firstCut;
/* 230:230 */    if (mid - from > to - mid) {
/* 231:231 */      long firstCut = from + (mid - from) / 2L;
/* 232:232 */      secondCut = lowerBound(mid, to, firstCut, comp);
/* 233:    */    }
/* 234:    */    else {
/* 235:235 */      secondCut = mid + (to - mid) / 2L;
/* 236:236 */      firstCut = upperBound(from, mid, secondCut, comp);
/* 237:    */    }
/* 238:    */    
/* 239:239 */    long first2 = firstCut;
/* 240:240 */    long middle2 = mid;
/* 241:241 */    long last2 = secondCut;
/* 242:242 */    if ((middle2 != first2) && (middle2 != last2)) {
/* 243:243 */      long first1 = first2;
/* 244:244 */      long last1 = middle2;
/* 245:245 */      while (first1 < --last1)
/* 246:246 */        swapper.swap(first1++, last1);
/* 247:247 */      first1 = middle2;
/* 248:248 */      last1 = last2;
/* 249:249 */      while (first1 < --last1)
/* 250:250 */        swapper.swap(first1++, last1);
/* 251:251 */      first1 = first2;
/* 252:252 */      last1 = last2;
/* 253:253 */      while (first1 < --last1) {
/* 254:254 */        swapper.swap(first1++, last1);
/* 255:    */      }
/* 256:    */    }
/* 257:257 */    mid = firstCut + (secondCut - mid);
/* 258:258 */    inPlaceMerge(from, firstCut, mid, comp, swapper);
/* 259:259 */    inPlaceMerge(mid, secondCut, to, comp, swapper);
/* 260:    */  }
/* 261:    */  
/* 273:    */  private static long lowerBound(long mid, long to, long firstCut, LongComparator comp)
/* 274:    */  {
/* 275:275 */    long len = to - mid;
/* 276:276 */    while (len > 0L) {
/* 277:277 */      long half = len / 2L;
/* 278:278 */      long middle = mid + half;
/* 279:279 */      if (comp.compare(middle, firstCut) < 0) {
/* 280:280 */        mid = middle + 1L;
/* 281:281 */        len -= half + 1L;
/* 282:    */      }
/* 283:    */      else {
/* 284:284 */        len = half;
/* 285:    */      }
/* 286:    */    }
/* 287:287 */    return mid;
/* 288:    */  }
/* 289:    */  
/* 290:    */  private static long med3(long a, long b, long c, LongComparator comp)
/* 291:    */  {
/* 292:292 */    int ab = comp.compare(a, b);
/* 293:293 */    int ac = comp.compare(a, c);
/* 294:294 */    int bc = comp.compare(b, c);
/* 295:295 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 296:    */  }
/* 297:    */  
/* 312:    */  public static void mergeSort(long from, long to, LongComparator comp, BigSwapper swapper)
/* 313:    */  {
/* 314:314 */    long length = to - from;
/* 315:    */    
/* 317:317 */    if (length < 7L) {
/* 318:318 */      for (long i = from; i < to; i += 1L) {
/* 319:319 */        for (long j = i; (j > from) && (comp.compare(j - 1L, j) > 0); j -= 1L) {
/* 320:320 */          swapper.swap(j, j - 1L);
/* 321:    */        }
/* 322:    */      }
/* 323:323 */      return;
/* 324:    */    }
/* 325:    */    
/* 327:327 */    long mid = from + to >>> 1;
/* 328:328 */    mergeSort(from, mid, comp, swapper);
/* 329:329 */    mergeSort(mid, to, comp, swapper);
/* 330:    */    
/* 333:333 */    if (comp.compare(mid - 1L, mid) <= 0) { return;
/* 334:    */    }
/* 335:    */    
/* 336:336 */    inPlaceMerge(from, mid, to, comp, swapper);
/* 337:    */  }
/* 338:    */  
/* 351:    */  public static void quickSort(long from, long to, LongComparator comp, BigSwapper swapper)
/* 352:    */  {
/* 353:353 */    long len = to - from;
/* 354:    */    
/* 355:355 */    if (len < 7L) {
/* 356:356 */      for (long i = from; i < to; i += 1L) {
/* 357:357 */        for (long j = i; (j > from) && (comp.compare(j - 1L, j) > 0); j -= 1L)
/* 358:358 */          swapper.swap(j, j - 1L);
/* 359:    */      }
/* 360:360 */      return;
/* 361:    */    }
/* 362:    */    
/* 364:364 */    long m = from + len / 2L;
/* 365:365 */    if (len > 7L) {
/* 366:366 */      long l = from;long n = to - 1L;
/* 367:367 */      if (len > 40L) {
/* 368:368 */        long s = len / 8L;
/* 369:369 */        l = med3(l, l + s, l + 2L * s, comp);
/* 370:370 */        m = med3(m - s, m, m + s, comp);
/* 371:371 */        n = med3(n - 2L * s, n - s, n, comp);
/* 372:    */      }
/* 373:373 */      m = med3(l, m, n, comp);
/* 374:    */    }
/* 375:    */    
/* 377:377 */    long a = from;long b = a;long c = to - 1L;long d = c;
/* 378:    */    for (;;)
/* 379:    */    {
/* 380:    */      int comparison;
/* 381:381 */      if ((b <= c) && ((comparison = comp.compare(b, m)) <= 0)) {
/* 382:382 */        if (comparison == 0) {
/* 383:383 */          if (a == m) { m = b;
/* 384:384 */          } else if (b == m) m = a;
/* 385:385 */          swapper.swap(a++, b);
/* 386:    */        }
/* 387:387 */        b += 1L;
/* 388:    */      } else { int comparison;
/* 389:389 */        while ((c >= b) && ((comparison = comp.compare(c, m)) >= 0)) {
/* 390:390 */          if (comparison == 0) {
/* 391:391 */            if (c == m) { m = d;
/* 392:392 */            } else if (d == m) m = c;
/* 393:393 */            swapper.swap(c, d--);
/* 394:    */          }
/* 395:395 */          c -= 1L;
/* 396:    */        }
/* 397:397 */        if (b > c) break;
/* 398:398 */        if (b == m) { m = d;
/* 399:399 */        } else if (c == m) m = c;
/* 400:400 */        swapper.swap(b++, c--);
/* 401:    */      }
/* 402:    */    }
/* 403:    */    
/* 405:405 */    long n = from + len;
/* 406:406 */    long s = Math.min(a - from, b - a);
/* 407:407 */    vecSwap(swapper, from, b - s, s);
/* 408:408 */    s = Math.min(d - c, n - d - 1L);
/* 409:409 */    vecSwap(swapper, b, n - s, s);
/* 410:    */    
/* 412:412 */    if ((s = b - a) > 1L) quickSort(from, from + s, comp, swapper);
/* 413:413 */    if ((s = d - c) > 1L) { quickSort(n - s, n, comp, swapper);
/* 414:    */    }
/* 415:    */  }
/* 416:    */  
/* 427:    */  private static long upperBound(long from, long mid, long secondCut, LongComparator comp)
/* 428:    */  {
/* 429:429 */    long len = mid - from;
/* 430:430 */    while (len > 0L) {
/* 431:431 */      long half = len / 2L;
/* 432:432 */      long middle = from + half;
/* 433:433 */      if (comp.compare(secondCut, middle) < 0) {
/* 434:434 */        len = half;
/* 435:    */      }
/* 436:    */      else {
/* 437:437 */        from = middle + 1L;
/* 438:438 */        len -= half + 1L;
/* 439:    */      }
/* 440:    */    }
/* 441:441 */    return from;
/* 442:    */  }
/* 443:    */  
/* 446:    */  private static void vecSwap(BigSwapper swapper, long from, long l, long s)
/* 447:    */  {
/* 448:448 */    for (int i = 0; i < s; l += 1L) { swapper.swap(from, l);i++;from += 1L;
/* 449:    */    }
/* 450:    */  }
/* 451:    */  
/* 452:452 */  public static void main(String[] arg) { int[][] a = IntBigArrays.newBigArray(1L << Integer.parseInt(arg[0]));
/* 453:    */    
/* 455:455 */    for (int k = 10; k-- != 0;)
/* 456:    */    {
/* 457:457 */      long start = -System.currentTimeMillis();
/* 458:    */      
/* 459:459 */      long x = 0L;
/* 460:460 */      for (long i = IntBigArrays.length(a); i-- != 0L; x ^= i ^ IntBigArrays.get(a, i)) {}
/* 461:461 */      if (x == 0L) { System.err.println();
/* 462:    */      }
/* 463:463 */      System.out.println("Single loop: " + (start + System.currentTimeMillis()) + "ms");
/* 464:    */      
/* 465:465 */      start = -System.currentTimeMillis();
/* 466:    */      
/* 467:467 */      long y = 0L;
/* 468:468 */      for (int i = a.length; i-- != 0;) {
/* 469:469 */        int[] t = a[i];
/* 470:470 */        for (int d = t.length; d-- != 0; y ^= t[d] ^ index(i, d)) {}
/* 471:    */      }
/* 472:472 */      if (y == 0L) System.err.println();
/* 473:473 */      if (x != y) { throw new AssertionError();
/* 474:    */      }
/* 475:475 */      System.out.println("Double loop: " + (start + System.currentTimeMillis()) + "ms");
/* 476:    */      
/* 477:477 */      long z = 0L;
/* 478:478 */      long j = IntBigArrays.length(a);
/* 479:479 */      for (int i = a.length; i-- != 0;) {
/* 480:480 */        int[] t = a[i];
/* 481:481 */        for (int d = t.length; d-- != 0; y ^= t[d] ^ --j) {}
/* 482:    */      }
/* 483:483 */      if (z == 0L) System.err.println();
/* 484:484 */      if (x != z) { throw new AssertionError();
/* 485:    */      }
/* 486:486 */      System.out.println("Double loop (with additional index): " + (start + System.currentTimeMillis()) + "ms");
/* 487:    */    }
/* 488:    */  }
/* 489:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.BigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */