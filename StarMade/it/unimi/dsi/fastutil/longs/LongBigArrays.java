/*    1:     */package it.unimi.dsi.fastutil.longs;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.BigArrays;
/*    4:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    5:     */import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
/*    6:     */import java.io.Serializable;
/*    7:     */import java.util.Arrays;
/*    8:     */import java.util.Random;
/*    9:     */
/*   81:     */public class LongBigArrays
/*   82:     */{
/*   83:     */  public static final long ONEOVERPHI = 106039L;
/*   84:  84 */  public static final long[][] EMPTY_BIG_ARRAY = new long[0][];
/*   85:     */  
/*   90:     */  public static long get(long[][] array, long index)
/*   91:     */  {
/*   92:  92 */    return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/*   93:     */  }
/*   94:     */  
/*   98:     */  public static void set(long[][] array, long index, long value)
/*   99:     */  {
/*  100: 100 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/*  101:     */  }
/*  102:     */  
/*  107:     */  public static void swap(long[][] array, long first, long second)
/*  108:     */  {
/*  109: 109 */    long t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/*  110: 110 */    array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/*  111: 111 */    array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/*  112:     */  }
/*  113:     */  
/*  118:     */  public static void add(long[][] array, long index, long incr)
/*  119:     */  {
/*  120: 120 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] += incr;
/*  121:     */  }
/*  122:     */  
/*  127:     */  public static void mul(long[][] array, long index, long factor)
/*  128:     */  {
/*  129: 129 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] *= factor;
/*  130:     */  }
/*  131:     */  
/*  135:     */  public static void incr(long[][] array, long index)
/*  136:     */  {
/*  137: 137 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] += 1L;
/*  138:     */  }
/*  139:     */  
/*  143:     */  public static void decr(long[][] array, long index)
/*  144:     */  {
/*  145: 145 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] -= 1L;
/*  146:     */  }
/*  147:     */  
/*  151:     */  public static long length(long[][] array)
/*  152:     */  {
/*  153: 153 */    int length = array.length;
/*  154: 154 */    return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/*  155:     */  }
/*  156:     */  
/*  164:     */  public static void copy(long[][] srcArray, long srcPos, long[][] destArray, long destPos, long length)
/*  165:     */  {
/*  166: 166 */    if (destPos <= srcPos) {
/*  167: 167 */      int srcSegment = BigArrays.segment(srcPos);
/*  168: 168 */      int destSegment = BigArrays.segment(destPos);
/*  169: 169 */      int srcDispl = BigArrays.displacement(srcPos);
/*  170: 170 */      int destDispl = BigArrays.displacement(destPos);
/*  171:     */      
/*  172: 172 */      while (length > 0L) {
/*  173: 173 */        int l = (int)Math.min(length, Math.min(srcArray[srcSegment].length - srcDispl, destArray[destSegment].length - destDispl));
/*  174: 174 */        System.arraycopy(srcArray[srcSegment], srcDispl, destArray[destSegment], destDispl, l);
/*  175: 175 */        if (srcDispl += l == 134217728) {
/*  176: 176 */          srcDispl = 0;
/*  177: 177 */          srcSegment++;
/*  178:     */        }
/*  179: 179 */        if (destDispl += l == 134217728) {
/*  180: 180 */          destDispl = 0;
/*  181: 181 */          destSegment++;
/*  182:     */        }
/*  183: 183 */        length -= l;
/*  184:     */      }
/*  185:     */    }
/*  186:     */    else {
/*  187: 187 */      int srcSegment = BigArrays.segment(srcPos + length);
/*  188: 188 */      int destSegment = BigArrays.segment(destPos + length);
/*  189: 189 */      int srcDispl = BigArrays.displacement(srcPos + length);
/*  190: 190 */      int destDispl = BigArrays.displacement(destPos + length);
/*  191:     */      
/*  192: 192 */      while (length > 0L) {
/*  193: 193 */        if (srcDispl == 0) {
/*  194: 194 */          srcDispl = 134217728;
/*  195: 195 */          srcSegment--;
/*  196:     */        }
/*  197: 197 */        if (destDispl == 0) {
/*  198: 198 */          destDispl = 134217728;
/*  199: 199 */          destSegment--;
/*  200:     */        }
/*  201: 201 */        int l = (int)Math.min(length, Math.min(srcDispl, destDispl));
/*  202: 202 */        System.arraycopy(srcArray[srcSegment], srcDispl - l, destArray[destSegment], destDispl - l, l);
/*  203: 203 */        srcDispl -= l;
/*  204: 204 */        destDispl -= l;
/*  205: 205 */        length -= l;
/*  206:     */      }
/*  207:     */    }
/*  208:     */  }
/*  209:     */  
/*  216:     */  public static void copyFromBig(long[][] srcArray, long srcPos, long[] destArray, int destPos, int length)
/*  217:     */  {
/*  218: 218 */    int srcSegment = BigArrays.segment(srcPos);
/*  219: 219 */    int srcDispl = BigArrays.displacement(srcPos);
/*  220:     */    
/*  221: 221 */    while (length > 0) {
/*  222: 222 */      int l = Math.min(srcArray[srcSegment].length - srcDispl, length);
/*  223: 223 */      System.arraycopy(srcArray[srcSegment], srcDispl, destArray, destPos, l);
/*  224: 224 */      if (srcDispl += l == 134217728) {
/*  225: 225 */        srcDispl = 0;
/*  226: 226 */        srcSegment++;
/*  227:     */      }
/*  228: 228 */      destPos += l;
/*  229: 229 */      length -= l;
/*  230:     */    }
/*  231:     */  }
/*  232:     */  
/*  239:     */  public static void copyToBig(long[] srcArray, int srcPos, long[][] destArray, long destPos, long length)
/*  240:     */  {
/*  241: 241 */    int destSegment = BigArrays.segment(destPos);
/*  242: 242 */    int destDispl = BigArrays.displacement(destPos);
/*  243:     */    
/*  244: 244 */    while (length > 0L) {
/*  245: 245 */      int l = (int)Math.min(destArray[destSegment].length - destDispl, length);
/*  246: 246 */      System.arraycopy(srcArray, srcPos, destArray[destSegment], destDispl, l);
/*  247: 247 */      if (destDispl += l == 134217728) {
/*  248: 248 */        destDispl = 0;
/*  249: 249 */        destSegment++;
/*  250:     */      }
/*  251: 251 */      srcPos += l;
/*  252: 252 */      length -= l;
/*  253:     */    }
/*  254:     */  }
/*  255:     */  
/*  259:     */  public static long[][] newBigArray(long length)
/*  260:     */  {
/*  261: 261 */    if (length == 0L) return EMPTY_BIG_ARRAY;
/*  262: 262 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/*  263: 263 */    long[][] base = new long[baseLength][];
/*  264: 264 */    int residual = (int)(length & 0x7FFFFFF);
/*  265: 265 */    if (residual != 0) {
/*  266: 266 */      for (int i = 0; i < baseLength - 1; i++) base[i] = new long[134217728];
/*  267: 267 */      base[(baseLength - 1)] = new long[residual];
/*  268:     */    } else {
/*  269: 269 */      for (int i = 0; i < baseLength; i++) base[i] = new long[134217728]; }
/*  270: 270 */    return base;
/*  271:     */  }
/*  272:     */  
/*  278:     */  public static long[][] wrap(long[] array)
/*  279:     */  {
/*  280: 280 */    if (array.length == 0) return EMPTY_BIG_ARRAY;
/*  281: 281 */    if (array.length <= 134217728) return new long[][] { array };
/*  282: 282 */    long[][] bigArray = newBigArray(array.length);
/*  283: 283 */    for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/*  284: 284 */    return bigArray;
/*  285:     */  }
/*  286:     */  
/*  299:     */  public static long[][] ensureCapacity(long[][] array, long length)
/*  300:     */  {
/*  301: 301 */    return ensureCapacity(array, length, length(array));
/*  302:     */  }
/*  303:     */  
/*  314:     */  public static long[][] ensureCapacity(long[][] array, long length, long preserve)
/*  315:     */  {
/*  316: 316 */    long oldLength = length(array);
/*  317: 317 */    if (length > oldLength) {
/*  318: 318 */      int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/*  319: 319 */      int baseLength = (int)((length + 134217727L) / 134217728L);
/*  320: 320 */      long[][] base = (long[][])Arrays.copyOf(array, baseLength);
/*  321: 321 */      int residual = (int)(length & 0x7FFFFFF);
/*  322: 322 */      if (residual != 0) {
/*  323: 323 */        for (int i = valid; i < baseLength - 1; i++) base[i] = new long[134217728];
/*  324: 324 */        base[(baseLength - 1)] = new long[residual];
/*  325:     */      } else {
/*  326: 326 */        for (int i = valid; i < baseLength; i++) base[i] = new long[134217728]; }
/*  327: 327 */      if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/*  328: 328 */      return base;
/*  329:     */    }
/*  330: 330 */    return array;
/*  331:     */  }
/*  332:     */  
/*  349:     */  public static long[][] grow(long[][] array, long length)
/*  350:     */  {
/*  351: 351 */    long oldLength = length(array);
/*  352: 352 */    return length > oldLength ? grow(array, length, oldLength) : array;
/*  353:     */  }
/*  354:     */  
/*  372:     */  public static long[][] grow(long[][] array, long length, long preserve)
/*  373:     */  {
/*  374: 374 */    long oldLength = length(array);
/*  375: 375 */    return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/*  376:     */  }
/*  377:     */  
/*  389:     */  public static long[][] trim(long[][] array, long length)
/*  390:     */  {
/*  391: 391 */    long oldLength = length(array);
/*  392: 392 */    if (length >= oldLength) return array;
/*  393: 393 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/*  394: 394 */    long[][] base = (long[][])Arrays.copyOf(array, baseLength);
/*  395: 395 */    int residual = (int)(length & 0x7FFFFFF);
/*  396: 396 */    if (residual != 0) base[(baseLength - 1)] = LongArrays.trim(base[(baseLength - 1)], residual);
/*  397: 397 */    return base;
/*  398:     */  }
/*  399:     */  
/*  414:     */  public static long[][] setLength(long[][] array, long length)
/*  415:     */  {
/*  416: 416 */    long oldLength = length(array);
/*  417: 417 */    if (length == oldLength) return array;
/*  418: 418 */    if (length < oldLength) return trim(array, length);
/*  419: 419 */    return ensureCapacity(array, length);
/*  420:     */  }
/*  421:     */  
/*  427:     */  public static long[][] copy(long[][] array, long offset, long length)
/*  428:     */  {
/*  429: 429 */    ensureOffsetLength(array, offset, length);
/*  430: 430 */    long[][] a = newBigArray(length);
/*  431:     */    
/*  432: 432 */    copy(array, offset, a, 0L, length);
/*  433: 433 */    return a;
/*  434:     */  }
/*  435:     */  
/*  439:     */  public static long[][] copy(long[][] array)
/*  440:     */  {
/*  441: 441 */    long[][] base = (long[][])array.clone();
/*  442: 442 */    for (int i = base.length; i-- != 0; base[i] = ((long[])array[i].clone())) {}
/*  443: 443 */    return base;
/*  444:     */  }
/*  445:     */  
/*  452:     */  public static void fill(long[][] array, long value)
/*  453:     */  {
/*  454: 454 */    for (int i = array.length; i-- != 0; LongArrays.fill(array[i], value)) {}
/*  455:     */  }
/*  456:     */  
/*  466:     */  public static void fill(long[][] array, long from, long to, long value)
/*  467:     */  {
/*  468: 468 */    long length = length(array);
/*  469: 469 */    BigArrays.ensureFromTo(length, from, to);
/*  470: 470 */    int fromSegment = BigArrays.segment(from);
/*  471: 471 */    int toSegment = BigArrays.segment(to);
/*  472: 472 */    int fromDispl = BigArrays.displacement(from);
/*  473: 473 */    int toDispl = BigArrays.displacement(to);
/*  474: 474 */    if (fromSegment == toSegment) {
/*  475: 475 */      LongArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/*  476: 476 */      return;
/*  477:     */    }
/*  478: 478 */    if (toDispl != 0) LongArrays.fill(array[toSegment], 0, toDispl, value);
/*  479: 479 */    for (;;) { toSegment--; if (toSegment <= fromSegment) break; LongArrays.fill(array[toSegment], value); }
/*  480: 480 */    LongArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/*  481:     */  }
/*  482:     */  
/*  490:     */  public static boolean equals(long[][] a1, long[][] a2)
/*  491:     */  {
/*  492: 492 */    if (length(a1) != length(a2)) return false;
/*  493: 493 */    long[] t; long[] u; int j; do { int i = a1.length;
/*  494:     */      
/*  499: 499 */      while (j-- == 0)
/*  500:     */      {
/*  501: 495 */        if (i-- == 0) break;
/*  502: 496 */        t = a1[i];
/*  503: 497 */        u = a2[i];
/*  504: 498 */        j = t.length;
/*  505: 499 */      } } while (t[j] == u[j]); return false;
/*  506:     */    
/*  507: 501 */    return true;
/*  508:     */  }
/*  509:     */  
/*  514:     */  public static String toString(long[][] a)
/*  515:     */  {
/*  516: 510 */    if (a == null) return "null";
/*  517: 511 */    long last = length(a) - 1L;
/*  518: 512 */    if (last == -1L) return "[]";
/*  519: 513 */    StringBuilder b = new StringBuilder();
/*  520: 514 */    b.append('[');
/*  521: 515 */    for (long i = 0L;; i += 1L) {
/*  522: 516 */      b.append(String.valueOf(get(a, i)));
/*  523: 517 */      if (i == last) return b.append(']').toString();
/*  524: 518 */      b.append(", ");
/*  525:     */    }
/*  526:     */  }
/*  527:     */  
/*  536:     */  public static void ensureFromTo(long[][] a, long from, long to)
/*  537:     */  {
/*  538: 532 */    BigArrays.ensureFromTo(length(a), from, to);
/*  539:     */  }
/*  540:     */  
/*  549:     */  public static void ensureOffsetLength(long[][] a, long offset, long length)
/*  550:     */  {
/*  551: 545 */    BigArrays.ensureOffsetLength(length(a), offset, length);
/*  552:     */  }
/*  553:     */  
/*  554:     */  private static final class BigArrayHashStrategy implements Hash.Strategy<long[][]>, Serializable {
/*  555:     */    public static final long serialVersionUID = -7046029254386353129L;
/*  556:     */    
/*  557: 551 */    public int hashCode(long[][] o) { return Arrays.deepHashCode(o); }
/*  558:     */    
/*  559:     */    public boolean equals(long[][] a, long[][] b) {
/*  560: 554 */      return LongBigArrays.equals(a, b);
/*  561:     */    }
/*  562:     */  }
/*  563:     */  
/*  570: 564 */  public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/*  571:     */  private static final int SMALL = 7;
/*  572:     */  private static final int MEDIUM = 40;
/*  573:     */  
/*  574: 568 */  private static void vecSwap(long[][] x, long a, long b, long n) { for (int i = 0; i < n; b += 1L) { swap(x, a, b);i++;a += 1L;
/*  575:     */    } }
/*  576:     */  
/*  577: 571 */  private static long med3(long[][] x, long a, long b, long c, LongComparator comp) { int ab = comp.compare(get(x, a), get(x, b));
/*  578: 572 */    int ac = comp.compare(get(x, a), get(x, c));
/*  579: 573 */    int bc = comp.compare(get(x, b), get(x, c));
/*  580: 574 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  581:     */  }
/*  582:     */  
/*  583:     */  private static void selectionSort(long[][] a, long from, long to, LongComparator comp)
/*  584:     */  {
/*  585: 579 */    for (long i = from; i < to - 1L; i += 1L) {
/*  586: 580 */      long m = i;
/*  587: 581 */      for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/*  588: 582 */      if (m != i) { swap(a, i, m);
/*  589:     */      }
/*  590:     */    }
/*  591:     */  }
/*  592:     */  
/*  602:     */  public static void quickSort(long[][] x, long from, long to, LongComparator comp)
/*  603:     */  {
/*  604: 598 */    long len = to - from;
/*  605:     */    
/*  606: 600 */    if (len < 7L) {
/*  607: 601 */      for (long i = from; i < to; i += 1L)
/*  608: 602 */        for (long j = i; (j > from) && (comp.compare(get(x, j - 1L), get(x, j)) > 0); j -= 1L) swap(x, j, j - 1L);
/*  609: 603 */      return;
/*  610:     */    }
/*  611:     */    
/*  612: 606 */    long m = from + len / 2L;
/*  613: 607 */    if (len > 7L) {
/*  614: 608 */      long l = from;
/*  615: 609 */      long n = to - 1L;
/*  616: 610 */      if (len > 40L) {
/*  617: 611 */        long s = len / 8L;
/*  618: 612 */        l = med3(x, l, l + s, l + 2L * s, comp);
/*  619: 613 */        m = med3(x, m - s, m, m + s, comp);
/*  620: 614 */        n = med3(x, n - 2L * s, n - s, n, comp);
/*  621:     */      }
/*  622: 616 */      m = med3(x, l, m, n, comp);
/*  623:     */    }
/*  624: 618 */    long v = get(x, m);
/*  625:     */    
/*  626: 620 */    long a = from;long b = a;long c = to - 1L;long d = c;
/*  627:     */    for (;;) {
/*  628:     */      int comparison;
/*  629: 623 */      if ((b <= c) && ((comparison = comp.compare(get(x, b), v)) <= 0)) {
/*  630: 624 */        if (comparison == 0) swap(x, a++, b);
/*  631: 625 */        b += 1L;
/*  632:     */      } else { int comparison;
/*  633: 627 */        while ((c >= b) && ((comparison = comp.compare(get(x, c), v)) >= 0)) {
/*  634: 628 */          if (comparison == 0) swap(x, c, d--);
/*  635: 629 */          c -= 1L;
/*  636:     */        }
/*  637: 631 */        if (b > c) break;
/*  638: 632 */        swap(x, b++, c--);
/*  639:     */      }
/*  640:     */    }
/*  641: 635 */    long n = to;
/*  642: 636 */    long s = Math.min(a - from, b - a);
/*  643: 637 */    vecSwap(x, from, b - s, s);
/*  644: 638 */    s = Math.min(d - c, n - d - 1L);
/*  645: 639 */    vecSwap(x, b, n - s, s);
/*  646:     */    
/*  647: 641 */    if ((s = b - a) > 1L) quickSort(x, from, from + s, comp);
/*  648: 642 */    if ((s = d - c) > 1L) quickSort(x, n - s, n, comp);
/*  649:     */  }
/*  650:     */  
/*  651:     */  private static long med3(long[][] x, long a, long b, long c) {
/*  652: 646 */    int ab = get(x, a) == get(x, b) ? 0 : get(x, a) < get(x, b) ? -1 : 1;
/*  653: 647 */    int ac = get(x, a) == get(x, c) ? 0 : get(x, a) < get(x, c) ? -1 : 1;
/*  654: 648 */    int bc = get(x, b) == get(x, c) ? 0 : get(x, b) < get(x, c) ? -1 : 1;
/*  655: 649 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  656:     */  }
/*  657:     */  
/*  658:     */  private static void selectionSort(long[][] a, long from, long to)
/*  659:     */  {
/*  660: 654 */    for (long i = from; i < to - 1L; i += 1L) {
/*  661: 655 */      long m = i;
/*  662: 656 */      for (long j = i + 1L; j < to; j += 1L) if (get(a, j) < get(a, m)) m = j;
/*  663: 657 */      if (m != i) { swap(a, i, m);
/*  664:     */      }
/*  665:     */    }
/*  666:     */  }
/*  667:     */  
/*  676:     */  public static void quickSort(long[][] x, LongComparator comp)
/*  677:     */  {
/*  678: 672 */    quickSort(x, 0L, length(x), comp);
/*  679:     */  }
/*  680:     */  
/*  690:     */  public static void quickSort(long[][] x, long from, long to)
/*  691:     */  {
/*  692: 686 */    long len = to - from;
/*  693:     */    
/*  694: 688 */    if (len < 7L) {
/*  695: 689 */      for (long i = from; i < to; i += 1L)
/*  696: 690 */        for (long j = i; j > from; j -= 1L) { if ((get(x, j - 1L) == get(x, j) ? 0 : get(x, j - 1L) < get(x, j) ? -1 : 1) <= 0) break; swap(x, j, j - 1L); }
/*  697: 691 */      return;
/*  698:     */    }
/*  699:     */    
/*  700: 694 */    long m = from + len / 2L;
/*  701: 695 */    if (len > 7L) {
/*  702: 696 */      long l = from;
/*  703: 697 */      long n = to - 1L;
/*  704: 698 */      if (len > 40L) {
/*  705: 699 */        long s = len / 8L;
/*  706: 700 */        l = med3(x, l, l + s, l + 2L * s);
/*  707: 701 */        m = med3(x, m - s, m, m + s);
/*  708: 702 */        n = med3(x, n - 2L * s, n - s, n);
/*  709:     */      }
/*  710: 704 */      m = med3(x, l, m, n);
/*  711:     */    }
/*  712: 706 */    long v = get(x, m);
/*  713:     */    
/*  714: 708 */    long a = from;long b = a;long c = to - 1L;long d = c;
/*  715:     */    for (;;)
/*  716:     */    {
/*  717: 711 */      if (b <= c) { int comparison; if ((comparison = get(x, b) == v ? 0 : get(x, b) < v ? -1 : 1) <= 0) {
/*  718: 712 */          if (comparison == 0) swap(x, a++, b);
/*  719: 713 */          b += 1L;
/*  720:     */        }
/*  721: 715 */      } else { while (c >= b) { int comparison; if ((comparison = get(x, c) == v ? 0 : get(x, c) < v ? -1 : 1) < 0) break;
/*  722: 716 */          if (comparison == 0) swap(x, c, d--);
/*  723: 717 */          c -= 1L;
/*  724:     */        }
/*  725: 719 */        if (b > c) break;
/*  726: 720 */        swap(x, b++, c--);
/*  727:     */      }
/*  728:     */    }
/*  729: 723 */    long n = to;
/*  730: 724 */    long s = Math.min(a - from, b - a);
/*  731: 725 */    vecSwap(x, from, b - s, s);
/*  732: 726 */    s = Math.min(d - c, n - d - 1L);
/*  733: 727 */    vecSwap(x, b, n - s, s);
/*  734:     */    
/*  735: 729 */    if ((s = b - a) > 1L) quickSort(x, from, from + s);
/*  736: 730 */    if ((s = d - c) > 1L) { quickSort(x, n - s, n);
/*  737:     */    }
/*  738:     */  }
/*  739:     */  
/*  746:     */  public static void quickSort(long[][] x)
/*  747:     */  {
/*  748: 742 */    quickSort(x, 0L, length(x));
/*  749:     */  }
/*  750:     */  
/*  771:     */  public static long binarySearch(long[][] a, long from, long to, long key)
/*  772:     */  {
/*  773: 767 */    to -= 1L;
/*  774: 768 */    while (from <= to) {
/*  775: 769 */      long mid = from + to >>> 1;
/*  776: 770 */      long midVal = get(a, mid);
/*  777: 771 */      if (midVal < key) { from = mid + 1L;
/*  778: 772 */      } else if (midVal > key) to = mid - 1L; else
/*  779: 773 */        return mid;
/*  780:     */    }
/*  781: 775 */    return -(from + 1L);
/*  782:     */  }
/*  783:     */  
/*  800:     */  public static long binarySearch(long[][] a, long key)
/*  801:     */  {
/*  802: 796 */    return binarySearch(a, 0L, length(a), key);
/*  803:     */  }
/*  804:     */  
/*  825:     */  public static long binarySearch(long[][] a, long from, long to, long key, LongComparator c)
/*  826:     */  {
/*  827: 821 */    to -= 1L;
/*  828: 822 */    while (from <= to) {
/*  829: 823 */      long mid = from + to >>> 1;
/*  830: 824 */      long midVal = get(a, mid);
/*  831: 825 */      int cmp = c.compare(midVal, key);
/*  832: 826 */      if (cmp < 0) { from = mid + 1L;
/*  833: 827 */      } else if (cmp > 0) to = mid - 1L; else
/*  834: 828 */        return mid;
/*  835:     */    }
/*  836: 830 */    return -(from + 1L);
/*  837:     */  }
/*  838:     */  
/*  856:     */  public static long binarySearch(long[][] a, long key, LongComparator c)
/*  857:     */  {
/*  858: 852 */    return binarySearch(a, 0L, length(a), key, c);
/*  859:     */  }
/*  860:     */  
/*  866:     */  private static final int DIGIT_BITS = 8;
/*  867:     */  
/*  872:     */  private static final int DIGIT_MASK = 255;
/*  873:     */  
/*  877:     */  private static final int DIGITS_PER_ELEMENT = 8;
/*  878:     */  
/*  882:     */  public static void radixSort(long[][] a)
/*  883:     */  {
/*  884: 878 */    radixSort(a, 0L, length(a));
/*  885:     */  }
/*  886:     */  
/*  903:     */  public static void radixSort(long[][] a, long from, long to)
/*  904:     */  {
/*  905: 899 */    int maxLevel = 7;
/*  906: 900 */    int stackSize = 1786;
/*  907: 901 */    long[] offsetStack = new long[1786];
/*  908: 902 */    int offsetPos = 0;
/*  909: 903 */    long[] lengthStack = new long[1786];
/*  910: 904 */    int lengthPos = 0;
/*  911: 905 */    int[] levelStack = new int[1786];
/*  912: 906 */    int levelPos = 0;
/*  913: 907 */    offsetStack[(offsetPos++)] = from;
/*  914: 908 */    lengthStack[(lengthPos++)] = (to - from);
/*  915: 909 */    levelStack[(levelPos++)] = 0;
/*  916: 910 */    long[] count = new long[256];
/*  917: 911 */    long[] pos = new long[256];
/*  918: 912 */    byte[][] digit = ByteBigArrays.newBigArray(to - from);
/*  919: 913 */    while (offsetPos > 0) {
/*  920: 914 */      long first = offsetStack[(--offsetPos)];
/*  921: 915 */      long length = lengthStack[(--lengthPos)];
/*  922: 916 */      int level = levelStack[(--levelPos)];
/*  923: 917 */      int signMask = level % 8 == 0 ? 128 : 0;
/*  924: 918 */      if (length < 40L) {
/*  925: 919 */        selectionSort(a, first, first + length);
/*  926:     */      }
/*  927:     */      else {
/*  928: 922 */        int shift = (7 - level % 8) * 8;
/*  929:     */        
/*  930: 924 */        for (long i = length; i-- != 0L; ByteBigArrays.set(digit, i, (byte)(int)(get(a, first + i) >>> shift & 0xFF ^ signMask))) {}
/*  931: 925 */        for (long i = length; i-- != 0L; count[(ByteBigArrays.get(digit, i) & 0xFF)] += 1L) {}
/*  932:     */        
/*  933: 927 */        int lastUsed = -1;
/*  934: 928 */        long p = 0L;
/*  935: 929 */        for (int i = 0; i < 256; i++) {
/*  936: 930 */          if (count[i] != 0L) {
/*  937: 931 */            lastUsed = i;
/*  938: 932 */            if ((level < 7) && (count[i] > 1L))
/*  939:     */            {
/*  940: 934 */              offsetStack[(offsetPos++)] = (p + first);
/*  941: 935 */              lengthStack[(lengthPos++)] = count[i];
/*  942: 936 */              levelStack[(levelPos++)] = (level + 1);
/*  943:     */            }
/*  944:     */          }
/*  945: 939 */          long tmp361_360 = (p + count[i]);p = tmp361_360;pos[i] = tmp361_360;
/*  946:     */        }
/*  947:     */        
/*  948: 942 */        long end = length - count[lastUsed];
/*  949: 943 */        count[lastUsed] = 0L;
/*  950:     */        
/*  951: 945 */        int c = -1;
/*  952: 946 */        for (long i = 0L; i < end; count[c] = 0L) {
/*  953: 947 */          long t = get(a, i + first);
/*  954: 948 */          c = ByteBigArrays.get(digit, i) & 0xFF;
/*  955: 949 */          for (;;) { long d; if ((d = pos[c] -= 1L) <= i) break;
/*  956: 950 */            long z = t;
/*  957: 951 */            int zz = c;
/*  958: 952 */            t = get(a, d + first);
/*  959: 953 */            c = ByteBigArrays.get(digit, d) & 0xFF;
/*  960: 954 */            set(a, d + first, z);
/*  961: 955 */            ByteBigArrays.set(digit, d, (byte)zz);
/*  962:     */          }
/*  963: 957 */          set(a, i + first, t);i += count[c];
/*  964:     */        }
/*  965:     */      }
/*  966:     */    } }
/*  967:     */  
/*  968: 962 */  private static void selectionSort(long[][] a, long[][] b, long from, long to) { for (long i = from; i < to - 1L; i += 1L) {
/*  969: 963 */      long m = i;
/*  970: 964 */      for (long j = i + 1L; j < to; j += 1L)
/*  971: 965 */        if ((get(a, j) < get(a, m)) || ((get(a, j) == get(a, m)) && (get(b, j) < get(b, m)))) m = j;
/*  972: 966 */      if (m != i) {
/*  973: 967 */        long t = get(a, i);
/*  974: 968 */        set(a, i, get(a, m));
/*  975: 969 */        set(a, m, t);
/*  976: 970 */        t = get(b, i);
/*  977: 971 */        set(b, i, get(b, m));
/*  978: 972 */        set(b, m, t);
/*  979:     */      }
/*  980:     */    }
/*  981:     */  }
/*  982:     */  
/* 1000:     */  public static void radixSort(long[][] a, long[][] b)
/* 1001:     */  {
/* 1002: 996 */    radixSort(a, b, 0L, length(a));
/* 1003:     */  }
/* 1004:     */  
/* 1025:     */  public static void radixSort(long[][] a, long[][] b, long from, long to)
/* 1026:     */  {
/* 1027:1021 */    int layers = 2;
/* 1028:1022 */    if (length(a) != length(b)) throw new IllegalArgumentException("Array size mismatch.");
/* 1029:1023 */    int maxLevel = 15;
/* 1030:1024 */    int stackSize = 3826;
/* 1031:1025 */    long[] offsetStack = new long[3826];
/* 1032:1026 */    int offsetPos = 0;
/* 1033:1027 */    long[] lengthStack = new long[3826];
/* 1034:1028 */    int lengthPos = 0;
/* 1035:1029 */    int[] levelStack = new int[3826];
/* 1036:1030 */    int levelPos = 0;
/* 1037:1031 */    offsetStack[(offsetPos++)] = from;
/* 1038:1032 */    lengthStack[(lengthPos++)] = (to - from);
/* 1039:1033 */    levelStack[(levelPos++)] = 0;
/* 1040:1034 */    long[] count = new long[256];
/* 1041:1035 */    long[] pos = new long[256];
/* 1042:1036 */    byte[][] digit = ByteBigArrays.newBigArray(to - from);
/* 1043:1037 */    while (offsetPos > 0) {
/* 1044:1038 */      long first = offsetStack[(--offsetPos)];
/* 1045:1039 */      long length = lengthStack[(--lengthPos)];
/* 1046:1040 */      int level = levelStack[(--levelPos)];
/* 1047:1041 */      int signMask = level % 8 == 0 ? 128 : 0;
/* 1048:1042 */      if (length < 40L) {
/* 1049:1043 */        selectionSort(a, b, first, first + length);
/* 1050:     */      }
/* 1051:     */      else {
/* 1052:1046 */        long[][] k = level < 8 ? a : b;
/* 1053:1047 */        int shift = (7 - level % 8) * 8;
/* 1054:     */        
/* 1055:1049 */        for (long i = length; i-- != 0L; ByteBigArrays.set(digit, i, (byte)(int)(get(k, first + i) >>> shift & 0xFF ^ signMask))) {}
/* 1056:1050 */        for (long i = length; i-- != 0L; count[(ByteBigArrays.get(digit, i) & 0xFF)] += 1L) {}
/* 1057:     */        
/* 1058:1052 */        int lastUsed = -1;
/* 1059:1053 */        long p = 0L;
/* 1060:1054 */        for (int i = 0; i < 256; i++) {
/* 1061:1055 */          if (count[i] != 0L) {
/* 1062:1056 */            lastUsed = i;
/* 1063:1057 */            if ((level < 15) && (count[i] > 1L)) {
/* 1064:1058 */              offsetStack[(offsetPos++)] = (p + first);
/* 1065:1059 */              lengthStack[(lengthPos++)] = count[i];
/* 1066:1060 */              levelStack[(levelPos++)] = (level + 1);
/* 1067:     */            }
/* 1068:     */          }
/* 1069:1063 */          long tmp404_403 = (p + count[i]);p = tmp404_403;pos[i] = tmp404_403;
/* 1070:     */        }
/* 1071:     */        
/* 1072:1066 */        long end = length - count[lastUsed];
/* 1073:1067 */        count[lastUsed] = 0L;
/* 1074:     */        
/* 1075:1069 */        int c = -1;
/* 1076:1070 */        for (long i = 0L; i < end; count[c] = 0L) {
/* 1077:1071 */          long t = get(a, i + first);
/* 1078:1072 */          long u = get(b, i + first);
/* 1079:1073 */          c = ByteBigArrays.get(digit, i) & 0xFF;
/* 1080:1074 */          for (;;) { long d; if ((d = pos[c] -= 1L) <= i) break;
/* 1081:1075 */            long z = t;
/* 1082:1076 */            int zz = c;
/* 1083:1077 */            t = get(a, d + first);
/* 1084:1078 */            set(a, d + first, z);
/* 1085:1079 */            z = u;
/* 1086:1080 */            u = get(b, d + first);
/* 1087:1081 */            set(b, d + first, z);
/* 1088:1082 */            c = ByteBigArrays.get(digit, d) & 0xFF;
/* 1089:1083 */            ByteBigArrays.set(digit, d, (byte)zz);
/* 1090:     */          }
/* 1091:1085 */          set(a, i + first, t);
/* 1092:1086 */          set(b, i + first, u);i += count[c];
/* 1093:     */        }
/* 1094:     */      }
/* 1095:     */    }
/* 1096:     */  }
/* 1097:     */  
/* 1104:     */  public static long[][] shuffle(long[][] a, long from, long to, Random random)
/* 1105:     */  {
/* 1106:1099 */    for (long i = to - from; i-- != 0L;) {
/* 1107:1100 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 1108:1101 */      long t = get(a, from + i);
/* 1109:1102 */      set(a, from + i, get(a, from + p));
/* 1110:1103 */      set(a, from + p, t);
/* 1111:     */    }
/* 1112:1105 */    return a;
/* 1113:     */  }
/* 1114:     */  
/* 1119:     */  public static long[][] shuffle(long[][] a, Random random)
/* 1120:     */  {
/* 1121:1114 */    for (long i = length(a); i-- != 0L;) {
/* 1122:1115 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 1123:1116 */      long t = get(a, i);
/* 1124:1117 */      set(a, i, get(a, p));
/* 1125:1118 */      set(a, p, t);
/* 1126:     */    }
/* 1127:1120 */    return a;
/* 1128:     */  }
/* 1129:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongBigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */