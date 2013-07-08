/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.BigArrays;
/*   4:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   5:    */import java.io.Serializable;
/*   6:    */import java.lang.reflect.Array;
/*   7:    */import java.util.Arrays;
/*   8:    */import java.util.Comparator;
/*   9:    */import java.util.Random;
/*  10:    */
/*  92:    */public class ObjectBigArrays
/*  93:    */{
/*  94:    */  public static final long ONEOVERPHI = 106039L;
/*  95: 95 */  public static final Object[][] EMPTY_BIG_ARRAY = new Object[0][];
/*  96:    */  
/* 102:    */  public static <K> K get(K[][] array, long index)
/* 103:    */  {
/* 104:104 */    return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/* 105:    */  }
/* 106:    */  
/* 111:    */  public static <K> void set(K[][] array, long index, K value)
/* 112:    */  {
/* 113:113 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/* 114:    */  }
/* 115:    */  
/* 121:    */  public static <K> void swap(K[][] array, long first, long second)
/* 122:    */  {
/* 123:123 */    K t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/* 124:124 */    array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/* 125:125 */    array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/* 126:    */  }
/* 127:    */  
/* 131:    */  public static <K> long length(K[][] array)
/* 132:    */  {
/* 133:133 */    int length = array.length;
/* 134:134 */    return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/* 135:    */  }
/* 136:    */  
/* 144:    */  public static <K> void copy(K[][] srcArray, long srcPos, K[][] destArray, long destPos, long length)
/* 145:    */  {
/* 146:146 */    if (destPos <= srcPos) {
/* 147:147 */      int srcSegment = BigArrays.segment(srcPos);
/* 148:148 */      int destSegment = BigArrays.segment(destPos);
/* 149:149 */      int srcDispl = BigArrays.displacement(srcPos);
/* 150:150 */      int destDispl = BigArrays.displacement(destPos);
/* 151:    */      
/* 152:152 */      while (length > 0L) {
/* 153:153 */        int l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
/* 154:154 */        System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, l);
/* 155:155 */        if (srcDispl += l == 134217728) {
/* 156:156 */          srcDispl = 0;
/* 157:157 */          srcSegment++;
/* 158:    */        }
/* 159:159 */        if (destDispl += l == 134217728) {
/* 160:160 */          destDispl = 0;
/* 161:161 */          destSegment++;
/* 162:    */        }
/* 163:163 */        length -= l;
/* 164:    */      }
/* 165:    */    }
/* 166:    */    else {
/* 167:167 */      int srcSegment = BigArrays.segment(srcPos + length);
/* 168:168 */      int destSegment = BigArrays.segment(destPos + length);
/* 169:169 */      int srcDispl = BigArrays.displacement(srcPos + length);
/* 170:170 */      int destDispl = BigArrays.displacement(destPos + length);
/* 171:    */      
/* 172:172 */      while (length > 0L) {
/* 173:173 */        if (srcDispl == 0) {
/* 174:174 */          srcDispl = 134217728;
/* 175:175 */          srcSegment--;
/* 176:    */        }
/* 177:177 */        if (destDispl == 0) {
/* 178:178 */          destDispl = 134217728;
/* 179:179 */          destSegment--;
/* 180:    */        }
/* 181:181 */        int l = (int)Math.min(length, Math.min(srcDispl, destDispl));
/* 182:182 */        System.arraycopy(srcArray[srcSegment], srcDispl - l, destArray[destSegment], destDispl - l, l);
/* 183:183 */        srcDispl -= l;
/* 184:184 */        destDispl -= l;
/* 185:185 */        length -= l;
/* 186:    */      }
/* 187:    */    }
/* 188:    */  }
/* 189:    */  
/* 196:    */  public static <K> void copyFromBig(K[][] srcArray, long srcPos, K[] destArray, int destPos, int length)
/* 197:    */  {
/* 198:198 */    int srcSegment = BigArrays.segment(srcPos);
/* 199:199 */    int srcDispl = BigArrays.displacement(srcPos);
/* 200:    */    
/* 201:201 */    while (length > 0) {
/* 202:202 */      int l = Math.min(srcArray[srcSegment].length - srcDispl, length);
/* 203:203 */      System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, l);
/* 204:204 */      if (srcDispl += l == 134217728) {
/* 205:205 */        srcDispl = 0;
/* 206:206 */        srcSegment++;
/* 207:    */      }
/* 208:208 */      destPos += l;
/* 209:209 */      length -= l;
/* 210:    */    }
/* 211:    */  }
/* 212:    */  
/* 219:    */  public static <K> void copyToBig(K[] srcArray, int srcPos, K[][] destArray, long destPos, long length)
/* 220:    */  {
/* 221:221 */    int destSegment = BigArrays.segment(destPos);
/* 222:222 */    int destDispl = BigArrays.displacement(destPos);
/* 223:    */    
/* 224:224 */    while (length > 0L) {
/* 225:225 */      int l = (int)Math.min(destArray[destSegment].length - destDispl, length);
/* 226:226 */      System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, l);
/* 227:227 */      if (destDispl += l == 134217728) {
/* 228:228 */        destDispl = 0;
/* 229:229 */        destSegment++;
/* 230:    */      }
/* 231:231 */      srcPos += l;
/* 232:232 */      length -= l;
/* 233:    */    }
/* 234:    */  }
/* 235:    */  
/* 245:    */  public static <K> K[][] newBigArray(K[][] prototype, long length)
/* 246:    */  {
/* 247:247 */    return (Object[][])newBigArray(prototype.getClass().getComponentType(), length);
/* 248:    */  }
/* 249:    */  
/* 259:    */  private static Object[][] newBigArray(Class<?> componentType, long length)
/* 260:    */  {
/* 261:261 */    if ((length == 0L) && (componentType == [Ljava.lang.Object.class)) return EMPTY_BIG_ARRAY;
/* 262:262 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/* 263:263 */    Object[][] base = (Object[][])Array.newInstance(componentType, baseLength);
/* 264:264 */    int residual = (int)(length & 0x7FFFFFF);
/* 265:265 */    if (residual != 0) {
/* 266:266 */      for (int i = 0; i < baseLength - 1; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
/* 267:267 */      base[(baseLength - 1)] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), residual));
/* 268:    */    } else {
/* 269:269 */      for (int i = 0; i < baseLength; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728)); }
/* 270:270 */    return base;
/* 271:    */  }
/* 272:    */  
/* 276:    */  public static Object[][] newBigArray(long length)
/* 277:    */  {
/* 278:278 */    if (length == 0L) return EMPTY_BIG_ARRAY;
/* 279:279 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/* 280:280 */    Object[][] base = new Object[baseLength][];
/* 281:281 */    int residual = (int)(length & 0x7FFFFFF);
/* 282:282 */    if (residual != 0) {
/* 283:283 */      for (int i = 0; i < baseLength - 1; i++) base[i] = new Object[134217728];
/* 284:284 */      base[(baseLength - 1)] = new Object[residual];
/* 285:    */    } else {
/* 286:286 */      for (int i = 0; i < baseLength; i++) base[i] = new Object[134217728]; }
/* 287:287 */    return base;
/* 288:    */  }
/* 289:    */  
/* 296:    */  public static <K> K[][] wrap(K[] array)
/* 297:    */  {
/* 298:298 */    if ((array.length == 0) && (array.getClass() == [Ljava.lang.Object.class)) return (Object[][])EMPTY_BIG_ARRAY;
/* 299:299 */    if (array.length <= 134217728) {
/* 300:300 */      K[][] bigArray = (Object[][])Array.newInstance(array.getClass(), 1);
/* 301:301 */      bigArray[0] = array;
/* 302:302 */      return bigArray;
/* 303:    */    }
/* 304:304 */    K[][] bigArray = (Object[][])newBigArray(array.getClass(), array.length);
/* 305:305 */    for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/* 306:306 */    return bigArray;
/* 307:    */  }
/* 308:    */  
/* 321:    */  public static <K> K[][] ensureCapacity(K[][] array, long length)
/* 322:    */  {
/* 323:323 */    return ensureCapacity(array, length, length(array));
/* 324:    */  }
/* 325:    */  
/* 340:    */  public static <K> K[][] ensureCapacity(K[][] array, long length, long preserve)
/* 341:    */  {
/* 342:342 */    long oldLength = length(array);
/* 343:343 */    if (length > oldLength) {
/* 344:344 */      int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/* 345:345 */      int baseLength = (int)((length + 134217727L) / 134217728L);
/* 346:346 */      K[][] base = (Object[][])Arrays.copyOf(array, baseLength);
/* 347:347 */      Class<?> componentType = array.getClass().getComponentType();
/* 348:348 */      int residual = (int)(length & 0x7FFFFFF);
/* 349:349 */      if (residual != 0) {
/* 350:350 */        for (int i = valid; i < baseLength - 1; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728));
/* 351:351 */        base[(baseLength - 1)] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), residual));
/* 352:    */      } else {
/* 353:353 */        for (int i = valid; i < baseLength; i++) base[i] = ((Object[])(Object[])Array.newInstance(componentType.getComponentType(), 134217728)); }
/* 354:354 */      if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/* 355:355 */      return base;
/* 356:    */    }
/* 357:357 */    return array;
/* 358:    */  }
/* 359:    */  
/* 376:    */  public static <K> K[][] grow(K[][] array, long length)
/* 377:    */  {
/* 378:378 */    long oldLength = length(array);
/* 379:379 */    return length > oldLength ? grow(array, length, oldLength) : array;
/* 380:    */  }
/* 381:    */  
/* 399:    */  public static <K> K[][] grow(K[][] array, long length, long preserve)
/* 400:    */  {
/* 401:401 */    long oldLength = length(array);
/* 402:402 */    return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/* 403:    */  }
/* 404:    */  
/* 416:    */  public static <K> K[][] trim(K[][] array, long length)
/* 417:    */  {
/* 418:418 */    long oldLength = length(array);
/* 419:419 */    if (length >= oldLength) return array;
/* 420:420 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/* 421:421 */    K[][] base = (Object[][])Arrays.copyOf(array, baseLength);
/* 422:422 */    int residual = (int)(length & 0x7FFFFFF);
/* 423:423 */    if (residual != 0) base[(baseLength - 1)] = ObjectArrays.trim(base[(baseLength - 1)], residual);
/* 424:424 */    return base;
/* 425:    */  }
/* 426:    */  
/* 441:    */  public static <K> K[][] setLength(K[][] array, long length)
/* 442:    */  {
/* 443:443 */    long oldLength = length(array);
/* 444:444 */    if (length == oldLength) return array;
/* 445:445 */    if (length < oldLength) return trim(array, length);
/* 446:446 */    return ensureCapacity(array, length);
/* 447:    */  }
/* 448:    */  
/* 454:    */  public static <K> K[][] copy(K[][] array, long offset, long length)
/* 455:    */  {
/* 456:456 */    ensureOffsetLength(array, offset, length);
/* 457:457 */    K[][] a = newBigArray(array, length);
/* 458:    */    
/* 459:459 */    copy(array, offset, a, 0L, length);
/* 460:460 */    return a;
/* 461:    */  }
/* 462:    */  
/* 466:    */  public static <K> K[][] copy(K[][] array)
/* 467:    */  {
/* 468:468 */    K[][] base = (Object[][])array.clone();
/* 469:469 */    for (int i = base.length; i-- != 0; base[i] = ((Object[])array[i].clone())) {}
/* 470:470 */    return base;
/* 471:    */  }
/* 472:    */  
/* 479:    */  public static <K> void fill(K[][] array, K value)
/* 480:    */  {
/* 481:481 */    for (int i = array.length; i-- != 0; ObjectArrays.fill(array[i], value)) {}
/* 482:    */  }
/* 483:    */  
/* 493:    */  public static <K> void fill(K[][] array, long from, long to, K value)
/* 494:    */  {
/* 495:495 */    long length = length(array);
/* 496:496 */    BigArrays.ensureFromTo(length, from, to);
/* 497:497 */    int fromSegment = BigArrays.segment(from);
/* 498:498 */    int toSegment = BigArrays.segment(to);
/* 499:499 */    int fromDispl = BigArrays.displacement(from);
/* 500:500 */    int toDispl = BigArrays.displacement(to);
/* 501:501 */    if (fromSegment == toSegment) {
/* 502:502 */      ObjectArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/* 503:503 */      return;
/* 504:    */    }
/* 505:505 */    if (toDispl != 0) ObjectArrays.fill(array[toSegment], 0, toDispl, value);
/* 506:506 */    for (;;) { toSegment--; if (toSegment <= fromSegment) break; ObjectArrays.fill(array[toSegment], value); }
/* 507:507 */    ObjectArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/* 508:    */  }
/* 509:    */  
/* 517:    */  public static <K> boolean equals(K[][] a1, K[][] a2)
/* 518:    */  {
/* 519:519 */    if (length(a1) != length(a2)) return false;
/* 520:520 */    K[] t; K[] u; int j; do { int i = a1.length;
/* 521:    */      
/* 526:526 */      while (j-- == 0)
/* 527:    */      {
/* 528:522 */        if (i-- == 0) break;
/* 529:523 */        t = a1[i];
/* 530:524 */        u = a2[i];
/* 531:525 */        j = t.length;
/* 532:526 */      } } while (t[j] == null ? u[j] == null : t[j].equals(u[j])); return false;
/* 533:    */    
/* 534:528 */    return true;
/* 535:    */  }
/* 536:    */  
/* 541:    */  public static <K> String toString(K[][] a)
/* 542:    */  {
/* 543:537 */    if (a == null) return "null";
/* 544:538 */    long last = length(a) - 1L;
/* 545:539 */    if (last == -1L) return "[]";
/* 546:540 */    StringBuilder b = new StringBuilder();
/* 547:541 */    b.append('[');
/* 548:542 */    for (long i = 0L;; i += 1L) {
/* 549:543 */      b.append(String.valueOf(get(a, i)));
/* 550:544 */      if (i == last) return b.append(']').toString();
/* 551:545 */      b.append(", ");
/* 552:    */    }
/* 553:    */  }
/* 554:    */  
/* 563:    */  public static <K> void ensureFromTo(K[][] a, long from, long to)
/* 564:    */  {
/* 565:559 */    BigArrays.ensureFromTo(length(a), from, to);
/* 566:    */  }
/* 567:    */  
/* 576:    */  public static <K> void ensureOffsetLength(K[][] a, long offset, long length)
/* 577:    */  {
/* 578:572 */    BigArrays.ensureOffsetLength(length(a), offset, length);
/* 579:    */  }
/* 580:    */  
/* 581:    */  private static final class BigArrayHashStrategy<K> implements Hash.Strategy<K[][]>, Serializable {
/* 582:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 583:    */    
/* 584:578 */    public int hashCode(K[][] o) { return Arrays.deepHashCode(o); }
/* 585:    */    
/* 586:    */    public boolean equals(K[][] a, K[][] b) {
/* 587:581 */      return ObjectBigArrays.equals(a, b);
/* 588:    */    }
/* 589:    */  }
/* 590:    */  
/* 597:591 */  public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/* 598:    */  private static final int SMALL = 7;
/* 599:    */  private static final int MEDIUM = 40;
/* 600:    */  
/* 601:595 */  private static <K> void vecSwap(K[][] x, long a, long b, long n) { for (int i = 0; i < n; b += 1L) { swap(x, a, b);i++;a += 1L;
/* 602:    */    } }
/* 603:    */  
/* 604:598 */  private static <K> long med3(K[][] x, long a, long b, long c, Comparator<K> comp) { int ab = comp.compare(get(x, a), get(x, b));
/* 605:599 */    int ac = comp.compare(get(x, a), get(x, c));
/* 606:600 */    int bc = comp.compare(get(x, b), get(x, c));
/* 607:601 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 608:    */  }
/* 609:    */  
/* 610:    */  private static <K> void selectionSort(K[][] a, long from, long to, Comparator<K> comp)
/* 611:    */  {
/* 612:606 */    for (long i = from; i < to - 1L; i += 1L) {
/* 613:607 */      long m = i;
/* 614:608 */      for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/* 615:609 */      if (m != i) { swap(a, i, m);
/* 616:    */      }
/* 617:    */    }
/* 618:    */  }
/* 619:    */  
/* 629:    */  public static <K> void quickSort(K[][] x, long from, long to, Comparator<K> comp)
/* 630:    */  {
/* 631:625 */    long len = to - from;
/* 632:    */    
/* 633:627 */    if (len < 7L) {
/* 634:628 */      for (long i = from; i < to; i += 1L)
/* 635:629 */        for (long j = i; (j > from) && (comp.compare(get(x, j - 1L), get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/* 636:630 */      return;
/* 637:    */    }
/* 638:    */    
/* 639:633 */    long m = from + len / 2L;
/* 640:634 */    if (len > 7L) {
/* 641:635 */      long l = from;
/* 642:636 */      long n = to - 1L;
/* 643:637 */      if (len > 40L) {
/* 644:638 */        long s = len / 8L;
/* 645:639 */        l = med3(x, l, l + s, l + 2L * s, comp);
/* 646:640 */        m = med3(x, m - s, m, m + s, comp);
/* 647:641 */        n = med3(x, n - 2L * s, n - s, n, comp);
/* 648:    */      }
/* 649:643 */      m = med3(x, l, m, n, comp);
/* 650:    */    }
/* 651:645 */    K v = get(x, m);
/* 652:    */    
/* 653:647 */    long a = from;long b = a;long c = to - 1L;long d = c;
/* 654:    */    for (;;) {
/* 655:    */      int comparison;
/* 656:650 */      if ((b <= c) && ((comparison = comp.compare(get(x, b), v)) <= 0)) {
/* 657:651 */        if (comparison == 0) swap(x, a++, b);
/* 658:652 */        b += 1L;
/* 659:    */      } else { int comparison;
/* 660:654 */        while ((c >= b) && ((comparison = comp.compare(get(x, c), v)) >= 0)) {
/* 661:655 */          if (comparison == 0) swap(x, c, d--);
/* 662:656 */          c -= 1L;
/* 663:    */        }
/* 664:658 */        if (b > c) break;
/* 665:659 */        swap(x, b++, c--);
/* 666:    */      }
/* 667:    */    }
/* 668:662 */    long n = to;
/* 669:663 */    long s = Math.min(a - from, b - a);
/* 670:664 */    vecSwap(x, from, b - s, s);
/* 671:665 */    s = Math.min(d - c, n - d - 1L);
/* 672:666 */    vecSwap(x, b, n - s, s);
/* 673:    */    
/* 674:668 */    if ((s = b - a) > 1L) quickSort(x, from, from + s, comp);
/* 675:669 */    if ((s = d - c) > 1L) quickSort(x, n - s, n, comp);
/* 676:    */  }
/* 677:    */  
/* 678:    */  private static <K> long med3(K[][] x, long a, long b, long c) {
/* 679:673 */    int ab = ((Comparable)get(x, a)).compareTo(get(x, b));
/* 680:674 */    int ac = ((Comparable)get(x, a)).compareTo(get(x, c));
/* 681:675 */    int bc = ((Comparable)get(x, b)).compareTo(get(x, c));
/* 682:676 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 683:    */  }
/* 684:    */  
/* 685:    */  private static <K> void selectionSort(K[][] a, long from, long to)
/* 686:    */  {
/* 687:681 */    for (long i = from; i < to - 1L; i += 1L) {
/* 688:682 */      long m = i;
/* 689:683 */      for (long j = i + 1L; j < to; j += 1L) if (((Comparable)get(a, j)).compareTo(get(a, m)) < 0) m = j;
/* 690:684 */      if (m != i) { swap(a, i, m);
/* 691:    */      }
/* 692:    */    }
/* 693:    */  }
/* 694:    */  
/* 703:    */  public static <K> void quickSort(K[][] x, Comparator<K> comp)
/* 704:    */  {
/* 705:699 */    quickSort(x, 0L, length(x), comp);
/* 706:    */  }
/* 707:    */  
/* 717:    */  public static <K> void quickSort(K[][] x, long from, long to)
/* 718:    */  {
/* 719:713 */    long len = to - from;
/* 720:    */    
/* 721:715 */    if (len < 7L) {
/* 722:716 */      for (long i = from; i < to; i += 1L)
/* 723:717 */        for (long j = i; (j > from) && (((Comparable)get(x, j - 1L)).compareTo(get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/* 724:718 */      return;
/* 725:    */    }
/* 726:    */    
/* 727:721 */    long m = from + len / 2L;
/* 728:722 */    if (len > 7L) {
/* 729:723 */      long l = from;
/* 730:724 */      long n = to - 1L;
/* 731:725 */      if (len > 40L) {
/* 732:726 */        long s = len / 8L;
/* 733:727 */        l = med3(x, l, l + s, l + 2L * s);
/* 734:728 */        m = med3(x, m - s, m, m + s);
/* 735:729 */        n = med3(x, n - 2L * s, n - s, n);
/* 736:    */      }
/* 737:731 */      m = med3(x, l, m, n);
/* 738:    */    }
/* 739:733 */    K v = get(x, m);
/* 740:    */    
/* 741:735 */    long a = from;long b = a;long c = to - 1L;long d = c;
/* 742:    */    for (;;) {
/* 743:    */      int comparison;
/* 744:738 */      if ((b <= c) && ((comparison = ((Comparable)get(x, b)).compareTo(v)) <= 0)) {
/* 745:739 */        if (comparison == 0) swap(x, a++, b);
/* 746:740 */        b += 1L;
/* 747:    */      } else { int comparison;
/* 748:742 */        while ((c >= b) && ((comparison = ((Comparable)get(x, c)).compareTo(v)) >= 0)) {
/* 749:743 */          if (comparison == 0) swap(x, c, d--);
/* 750:744 */          c -= 1L;
/* 751:    */        }
/* 752:746 */        if (b > c) break;
/* 753:747 */        swap(x, b++, c--);
/* 754:    */      }
/* 755:    */    }
/* 756:750 */    long n = to;
/* 757:751 */    long s = Math.min(a - from, b - a);
/* 758:752 */    vecSwap(x, from, b - s, s);
/* 759:753 */    s = Math.min(d - c, n - d - 1L);
/* 760:754 */    vecSwap(x, b, n - s, s);
/* 761:    */    
/* 762:756 */    if ((s = b - a) > 1L) quickSort(x, from, from + s);
/* 763:757 */    if ((s = d - c) > 1L) { quickSort(x, n - s, n);
/* 764:    */    }
/* 765:    */  }
/* 766:    */  
/* 773:    */  public static <K> void quickSort(K[][] x)
/* 774:    */  {
/* 775:769 */    quickSort(x, 0L, length(x));
/* 776:    */  }
/* 777:    */  
/* 798:    */  public static <K> long binarySearch(K[][] a, long from, long to, K key)
/* 799:    */  {
/* 800:794 */    to -= 1L;
/* 801:795 */    while (from <= to) {
/* 802:796 */      long mid = from + to >>> 1;
/* 803:797 */      K midVal = get(a, mid);
/* 804:798 */      int cmp = ((Comparable)midVal).compareTo(key);
/* 805:799 */      if (cmp < 0) { from = mid + 1L;
/* 806:800 */      } else if (cmp > 0) to = mid - 1L; else
/* 807:801 */        return mid;
/* 808:    */    }
/* 809:803 */    return -(from + 1L);
/* 810:    */  }
/* 811:    */  
/* 828:    */  public static <K> long binarySearch(K[][] a, Object key)
/* 829:    */  {
/* 830:824 */    return binarySearch(a, 0L, length(a), key);
/* 831:    */  }
/* 832:    */  
/* 853:    */  public static <K> long binarySearch(K[][] a, long from, long to, K key, Comparator<K> c)
/* 854:    */  {
/* 855:849 */    to -= 1L;
/* 856:850 */    while (from <= to) {
/* 857:851 */      long mid = from + to >>> 1;
/* 858:852 */      K midVal = get(a, mid);
/* 859:853 */      int cmp = c.compare(midVal, key);
/* 860:854 */      if (cmp < 0) { from = mid + 1L;
/* 861:855 */      } else if (cmp > 0) to = mid - 1L; else
/* 862:856 */        return mid;
/* 863:    */    }
/* 864:858 */    return -(from + 1L);
/* 865:    */  }
/* 866:    */  
/* 884:    */  public static <K> long binarySearch(K[][] a, K key, Comparator<K> c)
/* 885:    */  {
/* 886:880 */    return binarySearch(a, 0L, length(a), key, c);
/* 887:    */  }
/* 888:    */  
/* 895:    */  public static <K> K[][] shuffle(K[][] a, long from, long to, Random random)
/* 896:    */  {
/* 897:891 */    for (long i = to - from; i-- != 0L;) {
/* 898:892 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 899:893 */      K t = get(a, from + i);
/* 900:894 */      set(a, from + i, get(a, from + p));
/* 901:895 */      set(a, from + p, t);
/* 902:    */    }
/* 903:897 */    return a;
/* 904:    */  }
/* 905:    */  
/* 910:    */  public static <K> K[][] shuffle(K[][] a, Random random)
/* 911:    */  {
/* 912:906 */    for (long i = length(a); i-- != 0L;) {
/* 913:907 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 914:908 */      K t = get(a, i);
/* 915:909 */      set(a, i, get(a, p));
/* 916:910 */      set(a, p, t);
/* 917:    */    }
/* 918:912 */    return a;
/* 919:    */  }
/* 920:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectBigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */