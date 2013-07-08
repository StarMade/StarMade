/*   1:    */package it.unimi.dsi.fastutil.booleans;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigArrays;
/*   4:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.util.Arrays;
/*   7:    */import java.util.Random;
/*   8:    */
/*  81:    */public class BooleanBigArrays
/*  82:    */{
/*  83:    */  public static final long ONEOVERPHI = 106039L;
/*  84: 84 */  public static final boolean[][] EMPTY_BIG_ARRAY = new boolean[0][];
/*  85:    */  
/*  90:    */  public static boolean get(boolean[][] array, long index)
/*  91:    */  {
/*  92: 92 */    return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/*  93:    */  }
/*  94:    */  
/*  98:    */  public static void set(boolean[][] array, long index, boolean value)
/*  99:    */  {
/* 100:100 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/* 101:    */  }
/* 102:    */  
/* 107:    */  public static void swap(boolean[][] array, long first, long second)
/* 108:    */  {
/* 109:109 */    boolean t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/* 110:110 */    array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/* 111:111 */    array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/* 112:    */  }
/* 113:    */  
/* 117:    */  public static long length(boolean[][] array)
/* 118:    */  {
/* 119:119 */    int length = array.length;
/* 120:120 */    return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/* 121:    */  }
/* 122:    */  
/* 130:    */  public static void copy(boolean[][] srcArray, long srcPos, boolean[][] destArray, long destPos, long length)
/* 131:    */  {
/* 132:132 */    if (destPos <= srcPos) {
/* 133:133 */      int srcSegment = BigArrays.segment(srcPos);
/* 134:134 */      int destSegment = BigArrays.segment(destPos);
/* 135:135 */      int srcDispl = BigArrays.displacement(srcPos);
/* 136:136 */      int destDispl = BigArrays.displacement(destPos);
/* 137:    */      
/* 138:138 */      while (length > 0L) {
/* 139:139 */        int l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
/* 140:140 */        System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, l);
/* 141:141 */        if (srcDispl += l == 134217728) {
/* 142:142 */          srcDispl = 0;
/* 143:143 */          srcSegment++;
/* 144:    */        }
/* 145:145 */        if (destDispl += l == 134217728) {
/* 146:146 */          destDispl = 0;
/* 147:147 */          destSegment++;
/* 148:    */        }
/* 149:149 */        length -= l;
/* 150:    */      }
/* 151:    */    }
/* 152:    */    else {
/* 153:153 */      int srcSegment = BigArrays.segment(srcPos + length);
/* 154:154 */      int destSegment = BigArrays.segment(destPos + length);
/* 155:155 */      int srcDispl = BigArrays.displacement(srcPos + length);
/* 156:156 */      int destDispl = BigArrays.displacement(destPos + length);
/* 157:    */      
/* 158:158 */      while (length > 0L) {
/* 159:159 */        if (srcDispl == 0) {
/* 160:160 */          srcDispl = 134217728;
/* 161:161 */          srcSegment--;
/* 162:    */        }
/* 163:163 */        if (destDispl == 0) {
/* 164:164 */          destDispl = 134217728;
/* 165:165 */          destSegment--;
/* 166:    */        }
/* 167:167 */        int l = (int)Math.min(length, Math.min(srcDispl, destDispl));
/* 168:168 */        System.arraycopy(srcArray[srcSegment], srcDispl - l, destArray[destSegment], destDispl - l, l);
/* 169:169 */        srcDispl -= l;
/* 170:170 */        destDispl -= l;
/* 171:171 */        length -= l;
/* 172:    */      }
/* 173:    */    }
/* 174:    */  }
/* 175:    */  
/* 182:    */  public static void copyFromBig(boolean[][] srcArray, long srcPos, boolean[] destArray, int destPos, int length)
/* 183:    */  {
/* 184:184 */    int srcSegment = BigArrays.segment(srcPos);
/* 185:185 */    int srcDispl = BigArrays.displacement(srcPos);
/* 186:    */    
/* 187:187 */    while (length > 0) {
/* 188:188 */      int l = Math.min(srcArray[srcSegment].length - srcDispl, length);
/* 189:189 */      System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, l);
/* 190:190 */      if (srcDispl += l == 134217728) {
/* 191:191 */        srcDispl = 0;
/* 192:192 */        srcSegment++;
/* 193:    */      }
/* 194:194 */      destPos += l;
/* 195:195 */      length -= l;
/* 196:    */    }
/* 197:    */  }
/* 198:    */  
/* 205:    */  public static void copyToBig(boolean[] srcArray, int srcPos, boolean[][] destArray, long destPos, long length)
/* 206:    */  {
/* 207:207 */    int destSegment = BigArrays.segment(destPos);
/* 208:208 */    int destDispl = BigArrays.displacement(destPos);
/* 209:    */    
/* 210:210 */    while (length > 0L) {
/* 211:211 */      int l = (int)Math.min(destArray[destSegment].length - destDispl, length);
/* 212:212 */      System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, l);
/* 213:213 */      if (destDispl += l == 134217728) {
/* 214:214 */        destDispl = 0;
/* 215:215 */        destSegment++;
/* 216:    */      }
/* 217:217 */      srcPos += l;
/* 218:218 */      length -= l;
/* 219:    */    }
/* 220:    */  }
/* 221:    */  
/* 225:    */  public static boolean[][] newBigArray(long length)
/* 226:    */  {
/* 227:227 */    if (length == 0L) return EMPTY_BIG_ARRAY;
/* 228:228 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/* 229:229 */    boolean[][] base = new boolean[baseLength][];
/* 230:230 */    int residual = (int)(length & 0x7FFFFFF);
/* 231:231 */    if (residual != 0) {
/* 232:232 */      for (int i = 0; i < baseLength - 1; i++) base[i] = new boolean[134217728];
/* 233:233 */      base[(baseLength - 1)] = new boolean[residual];
/* 234:    */    } else {
/* 235:235 */      for (int i = 0; i < baseLength; i++) base[i] = new boolean[134217728]; }
/* 236:236 */    return base;
/* 237:    */  }
/* 238:    */  
/* 244:    */  public static boolean[][] wrap(boolean[] array)
/* 245:    */  {
/* 246:246 */    if (array.length == 0) return EMPTY_BIG_ARRAY;
/* 247:247 */    if (array.length <= 134217728) return new boolean[][] { array };
/* 248:248 */    boolean[][] bigArray = newBigArray(array.length);
/* 249:249 */    for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/* 250:250 */    return bigArray;
/* 251:    */  }
/* 252:    */  
/* 265:    */  public static boolean[][] ensureCapacity(boolean[][] array, long length)
/* 266:    */  {
/* 267:267 */    return ensureCapacity(array, length, length(array));
/* 268:    */  }
/* 269:    */  
/* 280:    */  public static boolean[][] ensureCapacity(boolean[][] array, long length, long preserve)
/* 281:    */  {
/* 282:282 */    long oldLength = length(array);
/* 283:283 */    if (length > oldLength) {
/* 284:284 */      int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/* 285:285 */      int baseLength = (int)((length + 134217727L) / 134217728L);
/* 286:286 */      boolean[][] base = (boolean[][])Arrays.copyOf(array, baseLength);
/* 287:287 */      int residual = (int)(length & 0x7FFFFFF);
/* 288:288 */      if (residual != 0) {
/* 289:289 */        for (int i = valid; i < baseLength - 1; i++) base[i] = new boolean[134217728];
/* 290:290 */        base[(baseLength - 1)] = new boolean[residual];
/* 291:    */      } else {
/* 292:292 */        for (int i = valid; i < baseLength; i++) base[i] = new boolean[134217728]; }
/* 293:293 */      if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/* 294:294 */      return base;
/* 295:    */    }
/* 296:296 */    return array;
/* 297:    */  }
/* 298:    */  
/* 315:    */  public static boolean[][] grow(boolean[][] array, long length)
/* 316:    */  {
/* 317:317 */    long oldLength = length(array);
/* 318:318 */    return length > oldLength ? grow(array, length, oldLength) : array;
/* 319:    */  }
/* 320:    */  
/* 338:    */  public static boolean[][] grow(boolean[][] array, long length, long preserve)
/* 339:    */  {
/* 340:340 */    long oldLength = length(array);
/* 341:341 */    return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/* 342:    */  }
/* 343:    */  
/* 355:    */  public static boolean[][] trim(boolean[][] array, long length)
/* 356:    */  {
/* 357:357 */    long oldLength = length(array);
/* 358:358 */    if (length >= oldLength) return array;
/* 359:359 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/* 360:360 */    boolean[][] base = (boolean[][])Arrays.copyOf(array, baseLength);
/* 361:361 */    int residual = (int)(length & 0x7FFFFFF);
/* 362:362 */    if (residual != 0) base[(baseLength - 1)] = BooleanArrays.trim(base[(baseLength - 1)], residual);
/* 363:363 */    return base;
/* 364:    */  }
/* 365:    */  
/* 380:    */  public static boolean[][] setLength(boolean[][] array, long length)
/* 381:    */  {
/* 382:382 */    long oldLength = length(array);
/* 383:383 */    if (length == oldLength) return array;
/* 384:384 */    if (length < oldLength) return trim(array, length);
/* 385:385 */    return ensureCapacity(array, length);
/* 386:    */  }
/* 387:    */  
/* 393:    */  public static boolean[][] copy(boolean[][] array, long offset, long length)
/* 394:    */  {
/* 395:395 */    ensureOffsetLength(array, offset, length);
/* 396:396 */    boolean[][] a = newBigArray(length);
/* 397:    */    
/* 398:398 */    copy(array, offset, a, 0L, length);
/* 399:399 */    return a;
/* 400:    */  }
/* 401:    */  
/* 405:    */  public static boolean[][] copy(boolean[][] array)
/* 406:    */  {
/* 407:407 */    boolean[][] base = (boolean[][])array.clone();
/* 408:408 */    for (int i = base.length; i-- != 0; base[i] = ((boolean[])array[i].clone())) {}
/* 409:409 */    return base;
/* 410:    */  }
/* 411:    */  
/* 418:    */  public static void fill(boolean[][] array, boolean value)
/* 419:    */  {
/* 420:420 */    for (int i = array.length; i-- != 0; BooleanArrays.fill(array[i], value)) {}
/* 421:    */  }
/* 422:    */  
/* 432:    */  public static void fill(boolean[][] array, long from, long to, boolean value)
/* 433:    */  {
/* 434:434 */    long length = length(array);
/* 435:435 */    BigArrays.ensureFromTo(length, from, to);
/* 436:436 */    int fromSegment = BigArrays.segment(from);
/* 437:437 */    int toSegment = BigArrays.segment(to);
/* 438:438 */    int fromDispl = BigArrays.displacement(from);
/* 439:439 */    int toDispl = BigArrays.displacement(to);
/* 440:440 */    if (fromSegment == toSegment) {
/* 441:441 */      BooleanArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/* 442:442 */      return;
/* 443:    */    }
/* 444:444 */    if (toDispl != 0) BooleanArrays.fill(array[toSegment], 0, toDispl, value);
/* 445:445 */    for (;;) { toSegment--; if (toSegment <= fromSegment) break; BooleanArrays.fill(array[toSegment], value); }
/* 446:446 */    BooleanArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/* 447:    */  }
/* 448:    */  
/* 456:    */  public static boolean equals(boolean[][] a1, boolean[][] a2)
/* 457:    */  {
/* 458:458 */    if (length(a1) != length(a2)) return false;
/* 459:459 */    boolean[] t; boolean[] u; int j; do { int i = a1.length;
/* 460:    */      
/* 465:465 */      while (j-- == 0)
/* 466:    */      {
/* 467:461 */        if (i-- == 0) break;
/* 468:462 */        t = a1[i];
/* 469:463 */        u = a2[i];
/* 470:464 */        j = t.length;
/* 471:465 */      } } while (t[j] == u[j]); return false;
/* 472:    */    
/* 473:467 */    return true;
/* 474:    */  }
/* 475:    */  
/* 480:    */  public static String toString(boolean[][] a)
/* 481:    */  {
/* 482:476 */    if (a == null) return "null";
/* 483:477 */    long last = length(a) - 1L;
/* 484:478 */    if (last == -1L) return "[]";
/* 485:479 */    StringBuilder b = new StringBuilder();
/* 486:480 */    b.append('[');
/* 487:481 */    for (long i = 0L;; i += 1L) {
/* 488:482 */      b.append(String.valueOf(get(a, i)));
/* 489:483 */      if (i == last) return b.append(']').toString();
/* 490:484 */      b.append(", ");
/* 491:    */    }
/* 492:    */  }
/* 493:    */  
/* 502:    */  public static void ensureFromTo(boolean[][] a, long from, long to)
/* 503:    */  {
/* 504:498 */    BigArrays.ensureFromTo(length(a), from, to);
/* 505:    */  }
/* 506:    */  
/* 515:    */  public static void ensureOffsetLength(boolean[][] a, long offset, long length)
/* 516:    */  {
/* 517:511 */    BigArrays.ensureOffsetLength(length(a), offset, length);
/* 518:    */  }
/* 519:    */  
/* 520:    */  private static final class BigArrayHashStrategy implements Hash.Strategy<boolean[][]>, Serializable {
/* 521:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 522:    */    
/* 523:517 */    public int hashCode(boolean[][] o) { return Arrays.deepHashCode(o); }
/* 524:    */    
/* 525:    */    public boolean equals(boolean[][] a, boolean[][] b) {
/* 526:520 */      return BooleanBigArrays.equals(a, b);
/* 527:    */    }
/* 528:    */  }
/* 529:    */  
/* 536:530 */  public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/* 537:    */  private static final int SMALL = 7;
/* 538:    */  private static final int MEDIUM = 40;
/* 539:    */  
/* 540:534 */  private static void vecSwap(boolean[][] x, long a, long b, long n) { for (int i = 0; i < n; b += 1L) { swap(x, a, b);i++;a += 1L;
/* 541:    */    } }
/* 542:    */  
/* 543:537 */  private static long med3(boolean[][] x, long a, long b, long c, BooleanComparator comp) { int ab = comp.compare(get(x, a), get(x, b));
/* 544:538 */    int ac = comp.compare(get(x, a), get(x, c));
/* 545:539 */    int bc = comp.compare(get(x, b), get(x, c));
/* 546:540 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 547:    */  }
/* 548:    */  
/* 549:    */  private static void selectionSort(boolean[][] a, long from, long to, BooleanComparator comp)
/* 550:    */  {
/* 551:545 */    for (long i = from; i < to - 1L; i += 1L) {
/* 552:546 */      long m = i;
/* 553:547 */      for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/* 554:548 */      if (m != i) { swap(a, i, m);
/* 555:    */      }
/* 556:    */    }
/* 557:    */  }
/* 558:    */  
/* 568:    */  public static void quickSort(boolean[][] x, long from, long to, BooleanComparator comp)
/* 569:    */  {
/* 570:564 */    long len = to - from;
/* 571:    */    
/* 572:566 */    if (len < 7L) {
/* 573:567 */      for (long i = from; i < to; i += 1L)
/* 574:568 */        for (long j = i; (j > from) && (comp.compare(get(x, j - 1L), get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/* 575:569 */      return;
/* 576:    */    }
/* 577:    */    
/* 578:572 */    long m = from + len / 2L;
/* 579:573 */    if (len > 7L) {
/* 580:574 */      long l = from;
/* 581:575 */      long n = to - 1L;
/* 582:576 */      if (len > 40L) {
/* 583:577 */        long s = len / 8L;
/* 584:578 */        l = med3(x, l, l + s, l + 2L * s, comp);
/* 585:579 */        m = med3(x, m - s, m, m + s, comp);
/* 586:580 */        n = med3(x, n - 2L * s, n - s, n, comp);
/* 587:    */      }
/* 588:582 */      m = med3(x, l, m, n, comp);
/* 589:    */    }
/* 590:584 */    boolean v = get(x, m);
/* 591:    */    
/* 592:586 */    long a = from;long b = a;long c = to - 1L;long d = c;
/* 593:    */    for (;;) {
/* 594:    */      int comparison;
/* 595:589 */      if ((b <= c) && ((comparison = comp.compare(get(x, b), v)) <= 0)) {
/* 596:590 */        if (comparison == 0) swap(x, a++, b);
/* 597:591 */        b += 1L;
/* 598:    */      } else { int comparison;
/* 599:593 */        while ((c >= b) && ((comparison = comp.compare(get(x, c), v)) >= 0)) {
/* 600:594 */          if (comparison == 0) swap(x, c, d--);
/* 601:595 */          c -= 1L;
/* 602:    */        }
/* 603:597 */        if (b > c) break;
/* 604:598 */        swap(x, b++, c--);
/* 605:    */      }
/* 606:    */    }
/* 607:601 */    long n = to;
/* 608:602 */    long s = Math.min(a - from, b - a);
/* 609:603 */    vecSwap(x, from, b - s, s);
/* 610:604 */    s = Math.min(d - c, n - d - 1L);
/* 611:605 */    vecSwap(x, b, n - s, s);
/* 612:    */    
/* 613:607 */    if ((s = b - a) > 1L) quickSort(x, from, from + s, comp);
/* 614:608 */    if ((s = d - c) > 1L) quickSort(x, n - s, n, comp);
/* 615:    */  }
/* 616:    */  
/* 617:    */  private static long med3(boolean[][] x, long a, long b, long c) {
/* 618:612 */    int ab = get(x, a) == get(x, b) ? 0 : (!get(x, a)) && (get(x, b)) ? -1 : 1;
/* 619:613 */    int ac = get(x, a) == get(x, c) ? 0 : (!get(x, a)) && (get(x, c)) ? -1 : 1;
/* 620:614 */    int bc = get(x, b) == get(x, c) ? 0 : (!get(x, b)) && (get(x, c)) ? -1 : 1;
/* 621:615 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 622:    */  }
/* 623:    */  
/* 624:    */  private static void selectionSort(boolean[][] a, long from, long to)
/* 625:    */  {
/* 626:620 */    for (long i = from; i < to - 1L; i += 1L) {
/* 627:621 */      long m = i;
/* 628:622 */      for (long j = i + 1L; j < to; j += 1L) if ((!get(a, j)) && (get(a, m))) m = j;
/* 629:623 */      if (m != i) { swap(a, i, m);
/* 630:    */      }
/* 631:    */    }
/* 632:    */  }
/* 633:    */  
/* 642:    */  public static void quickSort(boolean[][] x, BooleanComparator comp)
/* 643:    */  {
/* 644:638 */    quickSort(x, 0L, length(x), comp);
/* 645:    */  }
/* 646:    */  
/* 656:    */  public static void quickSort(boolean[][] x, long from, long to)
/* 657:    */  {
/* 658:652 */    long len = to - from;
/* 659:    */    
/* 660:654 */    if (len < 7L) {
/* 661:655 */      for (long i = from; i < to; i += 1L)
/* 662:656 */        for (long j = i; j > from; j -= 1L) { if ((get(x, j - 1L) == get(x, j) ? 0 : (!get(x, j - 1L)) && (get(x, j)) ? -1 : 1) <= 0) break; swap(x, j, j - 1L); }
/* 663:657 */      return;
/* 664:    */    }
/* 665:    */    
/* 666:660 */    long m = from + len / 2L;
/* 667:661 */    if (len > 7L) {
/* 668:662 */      long l = from;
/* 669:663 */      long n = to - 1L;
/* 670:664 */      if (len > 40L) {
/* 671:665 */        long s = len / 8L;
/* 672:666 */        l = med3(x, l, l + s, l + 2L * s);
/* 673:667 */        m = med3(x, m - s, m, m + s);
/* 674:668 */        n = med3(x, n - 2L * s, n - s, n);
/* 675:    */      }
/* 676:670 */      m = med3(x, l, m, n);
/* 677:    */    }
/* 678:672 */    boolean v = get(x, m);
/* 679:    */    
/* 680:674 */    long a = from;long b = a;long c = to - 1L;long d = c;
/* 681:    */    for (;;)
/* 682:    */    {
/* 683:677 */      if (b <= c) { int comparison; if ((comparison = get(x, b) == v ? 0 : (!get(x, b)) && (v) ? -1 : 1) <= 0) {
/* 684:678 */          if (comparison == 0) swap(x, a++, b);
/* 685:679 */          b += 1L;
/* 686:    */        }
/* 687:681 */      } else { while (c >= b) { int comparison; if ((comparison = get(x, c) == v ? 0 : (!get(x, c)) && (v) ? -1 : 1) < 0) break;
/* 688:682 */          if (comparison == 0) swap(x, c, d--);
/* 689:683 */          c -= 1L;
/* 690:    */        }
/* 691:685 */        if (b > c) break;
/* 692:686 */        swap(x, b++, c--);
/* 693:    */      }
/* 694:    */    }
/* 695:689 */    long n = to;
/* 696:690 */    long s = Math.min(a - from, b - a);
/* 697:691 */    vecSwap(x, from, b - s, s);
/* 698:692 */    s = Math.min(d - c, n - d - 1L);
/* 699:693 */    vecSwap(x, b, n - s, s);
/* 700:    */    
/* 701:695 */    if ((s = b - a) > 1L) quickSort(x, from, from + s);
/* 702:696 */    if ((s = d - c) > 1L) { quickSort(x, n - s, n);
/* 703:    */    }
/* 704:    */  }
/* 705:    */  
/* 712:    */  public static void quickSort(boolean[][] x)
/* 713:    */  {
/* 714:708 */    quickSort(x, 0L, length(x));
/* 715:    */  }
/* 716:    */  
/* 723:    */  public static boolean[][] shuffle(boolean[][] a, long from, long to, Random random)
/* 724:    */  {
/* 725:719 */    for (long i = to - from; i-- != 0L;) {
/* 726:720 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 727:721 */      boolean t = get(a, from + i);
/* 728:722 */      set(a, from + i, get(a, from + p));
/* 729:723 */      set(a, from + p, t);
/* 730:    */    }
/* 731:725 */    return a;
/* 732:    */  }
/* 733:    */  
/* 738:    */  public static boolean[][] shuffle(boolean[][] a, Random random)
/* 739:    */  {
/* 740:734 */    for (long i = length(a); i-- != 0L;) {
/* 741:735 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 742:736 */      boolean t = get(a, i);
/* 743:737 */      set(a, i, get(a, p));
/* 744:738 */      set(a, p, t);
/* 745:    */    }
/* 746:740 */    return a;
/* 747:    */  }
/* 748:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanBigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */