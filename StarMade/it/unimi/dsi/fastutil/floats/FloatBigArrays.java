/*    1:     */package it.unimi.dsi.fastutil.floats;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.BigArrays;
/*    4:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    5:     */import it.unimi.dsi.fastutil.bytes.ByteBigArrays;
/*    6:     */import java.io.Serializable;
/*    7:     */import java.util.Arrays;
/*    8:     */import java.util.Random;
/*    9:     */
/*   81:     */public class FloatBigArrays
/*   82:     */{
/*   83:     */  public static final long ONEOVERPHI = 106039L;
/*   84:  84 */  public static final float[][] EMPTY_BIG_ARRAY = new float[0][];
/*   85:     */  
/*   90:     */  public static float get(float[][] array, long index)
/*   91:     */  {
/*   92:  92 */    return array[BigArrays.segment(index)][BigArrays.displacement(index)];
/*   93:     */  }
/*   94:     */  
/*   98:     */  public static void set(float[][] array, long index, float value)
/*   99:     */  {
/*  100: 100 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] = value;
/*  101:     */  }
/*  102:     */  
/*  107:     */  public static void swap(float[][] array, long first, long second)
/*  108:     */  {
/*  109: 109 */    float t = array[BigArrays.segment(first)][BigArrays.displacement(first)];
/*  110: 110 */    array[BigArrays.segment(first)][BigArrays.displacement(first)] = array[BigArrays.segment(second)][BigArrays.displacement(second)];
/*  111: 111 */    array[BigArrays.segment(second)][BigArrays.displacement(second)] = t;
/*  112:     */  }
/*  113:     */  
/*  118:     */  public static void add(float[][] array, long index, float incr)
/*  119:     */  {
/*  120: 120 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] += incr;
/*  121:     */  }
/*  122:     */  
/*  127:     */  public static void mul(float[][] array, long index, float factor)
/*  128:     */  {
/*  129: 129 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] *= factor;
/*  130:     */  }
/*  131:     */  
/*  135:     */  public static void incr(float[][] array, long index)
/*  136:     */  {
/*  137: 137 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] += 1.0F;
/*  138:     */  }
/*  139:     */  
/*  143:     */  public static void decr(float[][] array, long index)
/*  144:     */  {
/*  145: 145 */    array[BigArrays.segment(index)][BigArrays.displacement(index)] -= 1.0F;
/*  146:     */  }
/*  147:     */  
/*  151:     */  public static long length(float[][] array)
/*  152:     */  {
/*  153: 153 */    int length = array.length;
/*  154: 154 */    return length == 0 ? 0L : BigArrays.start(length - 1) + array[(length - 1)].length;
/*  155:     */  }
/*  156:     */  
/*  164:     */  public static void copy(float[][] srcArray, long srcPos, float[][] destArray, long destPos, long length)
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
/*  216:     */  public static void copyFromBig(float[][] srcArray, long srcPos, float[] destArray, int destPos, int length)
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
/*  239:     */  public static void copyToBig(float[] srcArray, int srcPos, float[][] destArray, long destPos, long length)
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
/*  259:     */  public static float[][] newBigArray(long length)
/*  260:     */  {
/*  261: 261 */    if (length == 0L) return EMPTY_BIG_ARRAY;
/*  262: 262 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/*  263: 263 */    float[][] base = new float[baseLength][];
/*  264: 264 */    int residual = (int)(length & 0x7FFFFFF);
/*  265: 265 */    if (residual != 0) {
/*  266: 266 */      for (int i = 0; i < baseLength - 1; i++) base[i] = new float[134217728];
/*  267: 267 */      base[(baseLength - 1)] = new float[residual];
/*  268:     */    } else {
/*  269: 269 */      for (int i = 0; i < baseLength; i++) base[i] = new float[134217728]; }
/*  270: 270 */    return base;
/*  271:     */  }
/*  272:     */  
/*  278:     */  public static float[][] wrap(float[] array)
/*  279:     */  {
/*  280: 280 */    if (array.length == 0) return EMPTY_BIG_ARRAY;
/*  281: 281 */    if (array.length <= 134217728) return new float[][] { array };
/*  282: 282 */    float[][] bigArray = newBigArray(array.length);
/*  283: 283 */    for (int i = 0; i < bigArray.length; i++) System.arraycopy(array, (int)BigArrays.start(i), bigArray[i], 0, bigArray[i].length);
/*  284: 284 */    return bigArray;
/*  285:     */  }
/*  286:     */  
/*  299:     */  public static float[][] ensureCapacity(float[][] array, long length)
/*  300:     */  {
/*  301: 301 */    return ensureCapacity(array, length, length(array));
/*  302:     */  }
/*  303:     */  
/*  314:     */  public static float[][] ensureCapacity(float[][] array, long length, long preserve)
/*  315:     */  {
/*  316: 316 */    long oldLength = length(array);
/*  317: 317 */    if (length > oldLength) {
/*  318: 318 */      int valid = array.length - ((array.length == 0) || ((array.length > 0) && (array[(array.length - 1)].length == 134217728)) ? 0 : 1);
/*  319: 319 */      int baseLength = (int)((length + 134217727L) / 134217728L);
/*  320: 320 */      float[][] base = (float[][])Arrays.copyOf(array, baseLength);
/*  321: 321 */      int residual = (int)(length & 0x7FFFFFF);
/*  322: 322 */      if (residual != 0) {
/*  323: 323 */        for (int i = valid; i < baseLength - 1; i++) base[i] = new float[134217728];
/*  324: 324 */        base[(baseLength - 1)] = new float[residual];
/*  325:     */      } else {
/*  326: 326 */        for (int i = valid; i < baseLength; i++) base[i] = new float[134217728]; }
/*  327: 327 */      if (preserve - valid * 134217728L > 0L) copy(array, valid * 134217728L, base, valid * 134217728L, preserve - valid * 134217728L);
/*  328: 328 */      return base;
/*  329:     */    }
/*  330: 330 */    return array;
/*  331:     */  }
/*  332:     */  
/*  349:     */  public static float[][] grow(float[][] array, long length)
/*  350:     */  {
/*  351: 351 */    long oldLength = length(array);
/*  352: 352 */    return length > oldLength ? grow(array, length, oldLength) : array;
/*  353:     */  }
/*  354:     */  
/*  372:     */  public static float[][] grow(float[][] array, long length, long preserve)
/*  373:     */  {
/*  374: 374 */    long oldLength = length(array);
/*  375: 375 */    return length > oldLength ? ensureCapacity(array, Math.max(106039L * oldLength >>> 16, length), preserve) : array;
/*  376:     */  }
/*  377:     */  
/*  389:     */  public static float[][] trim(float[][] array, long length)
/*  390:     */  {
/*  391: 391 */    long oldLength = length(array);
/*  392: 392 */    if (length >= oldLength) return array;
/*  393: 393 */    int baseLength = (int)((length + 134217727L) / 134217728L);
/*  394: 394 */    float[][] base = (float[][])Arrays.copyOf(array, baseLength);
/*  395: 395 */    int residual = (int)(length & 0x7FFFFFF);
/*  396: 396 */    if (residual != 0) base[(baseLength - 1)] = FloatArrays.trim(base[(baseLength - 1)], residual);
/*  397: 397 */    return base;
/*  398:     */  }
/*  399:     */  
/*  414:     */  public static float[][] setLength(float[][] array, long length)
/*  415:     */  {
/*  416: 416 */    long oldLength = length(array);
/*  417: 417 */    if (length == oldLength) return array;
/*  418: 418 */    if (length < oldLength) return trim(array, length);
/*  419: 419 */    return ensureCapacity(array, length);
/*  420:     */  }
/*  421:     */  
/*  427:     */  public static float[][] copy(float[][] array, long offset, long length)
/*  428:     */  {
/*  429: 429 */    ensureOffsetLength(array, offset, length);
/*  430: 430 */    float[][] a = newBigArray(length);
/*  431:     */    
/*  432: 432 */    copy(array, offset, a, 0L, length);
/*  433: 433 */    return a;
/*  434:     */  }
/*  435:     */  
/*  439:     */  public static float[][] copy(float[][] array)
/*  440:     */  {
/*  441: 441 */    float[][] base = (float[][])array.clone();
/*  442: 442 */    for (int i = base.length; i-- != 0; base[i] = ((float[])array[i].clone())) {}
/*  443: 443 */    return base;
/*  444:     */  }
/*  445:     */  
/*  452:     */  public static void fill(float[][] array, float value)
/*  453:     */  {
/*  454: 454 */    for (int i = array.length; i-- != 0; FloatArrays.fill(array[i], value)) {}
/*  455:     */  }
/*  456:     */  
/*  466:     */  public static void fill(float[][] array, long from, long to, float value)
/*  467:     */  {
/*  468: 468 */    long length = length(array);
/*  469: 469 */    BigArrays.ensureFromTo(length, from, to);
/*  470: 470 */    int fromSegment = BigArrays.segment(from);
/*  471: 471 */    int toSegment = BigArrays.segment(to);
/*  472: 472 */    int fromDispl = BigArrays.displacement(from);
/*  473: 473 */    int toDispl = BigArrays.displacement(to);
/*  474: 474 */    if (fromSegment == toSegment) {
/*  475: 475 */      FloatArrays.fill(array[fromSegment], fromDispl, toDispl, value);
/*  476: 476 */      return;
/*  477:     */    }
/*  478: 478 */    if (toDispl != 0) FloatArrays.fill(array[toSegment], 0, toDispl, value);
/*  479: 479 */    for (;;) { toSegment--; if (toSegment <= fromSegment) break; FloatArrays.fill(array[toSegment], value); }
/*  480: 480 */    FloatArrays.fill(array[fromSegment], fromDispl, 134217728, value);
/*  481:     */  }
/*  482:     */  
/*  490:     */  public static boolean equals(float[][] a1, float[][] a2)
/*  491:     */  {
/*  492: 492 */    if (length(a1) != length(a2)) return false;
/*  493: 493 */    float[] t; float[] u; int j; do { int i = a1.length;
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
/*  514:     */  public static String toString(float[][] a)
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
/*  536:     */  public static void ensureFromTo(float[][] a, long from, long to)
/*  537:     */  {
/*  538: 532 */    BigArrays.ensureFromTo(length(a), from, to);
/*  539:     */  }
/*  540:     */  
/*  549:     */  public static void ensureOffsetLength(float[][] a, long offset, long length)
/*  550:     */  {
/*  551: 545 */    BigArrays.ensureOffsetLength(length(a), offset, length);
/*  552:     */  }
/*  553:     */  
/*  554:     */  private static final class BigArrayHashStrategy implements Hash.Strategy<float[][]>, Serializable {
/*  555:     */    public static final long serialVersionUID = -7046029254386353129L;
/*  556:     */    
/*  557: 551 */    public int hashCode(float[][] o) { return Arrays.deepHashCode(o); }
/*  558:     */    
/*  559:     */    public boolean equals(float[][] a, float[][] b) {
/*  560: 554 */      return FloatBigArrays.equals(a, b);
/*  561:     */    }
/*  562:     */  }
/*  563:     */  
/*  570: 564 */  public static final Hash.Strategy HASH_STRATEGY = new BigArrayHashStrategy(null);
/*  571:     */  private static final int SMALL = 7;
/*  572:     */  private static final int MEDIUM = 40;
/*  573:     */  
/*  574: 568 */  private static void vecSwap(float[][] x, long a, long b, long n) { for (int i = 0; i < n; b += 1L) { swap(x, a, b);i++;a += 1L;
/*  575:     */    } }
/*  576:     */  
/*  577: 571 */  private static long med3(float[][] x, long a, long b, long c, FloatComparator comp) { int ab = comp.compare(get(x, a), get(x, b));
/*  578: 572 */    int ac = comp.compare(get(x, a), get(x, c));
/*  579: 573 */    int bc = comp.compare(get(x, b), get(x, c));
/*  580: 574 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  581:     */  }
/*  582:     */  
/*  583:     */  private static void selectionSort(float[][] a, long from, long to, FloatComparator comp)
/*  584:     */  {
/*  585: 579 */    for (long i = from; i < to - 1L; i += 1L) {
/*  586: 580 */      long m = i;
/*  587: 581 */      for (long j = i + 1L; j < to; j += 1L) if (comp.compare(get(a, j), get(a, m)) < 0) m = j;
/*  588: 582 */      if (m != i) { swap(a, i, m);
/*  589:     */      }
/*  590:     */    }
/*  591:     */  }
/*  592:     */  
/*  602:     */  public static void quickSort(float[][] x, long from, long to, FloatComparator comp)
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
/*  624: 618 */    float v = get(x, m);
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
/*  651:     */  private static long med3(float[][] x, long a, long b, long c) {
/*  652: 646 */    int ab = get(x, a) == get(x, b) ? 0 : get(x, a) < get(x, b) ? -1 : 1;
/*  653: 647 */    int ac = get(x, a) == get(x, c) ? 0 : get(x, a) < get(x, c) ? -1 : 1;
/*  654: 648 */    int bc = get(x, b) == get(x, c) ? 0 : get(x, b) < get(x, c) ? -1 : 1;
/*  655: 649 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  656:     */  }
/*  657:     */  
/*  658:     */  private static void selectionSort(float[][] a, long from, long to)
/*  659:     */  {
/*  660: 654 */    for (long i = from; i < to - 1L; i += 1L) {
/*  661: 655 */      long m = i;
/*  662: 656 */      for (long j = i + 1L; j < to; j += 1L) if (get(a, j) < get(a, m)) m = j;
/*  663: 657 */      if (m != i) { swap(a, i, m);
/*  664:     */      }
/*  665:     */    }
/*  666:     */  }
/*  667:     */  
/*  676:     */  public static void quickSort(float[][] x, FloatComparator comp)
/*  677:     */  {
/*  678: 672 */    quickSort(x, 0L, length(x), comp);
/*  679:     */  }
/*  680:     */  
/*  690:     */  public static void quickSort(float[][] x, long from, long to)
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
/*  712: 706 */    float v = get(x, m);
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
/*  746:     */  public static void quickSort(float[][] x)
/*  747:     */  {
/*  748: 742 */    quickSort(x, 0L, length(x));
/*  749:     */  }
/*  750:     */  
/*  756:     */  private static final int DIGIT_BITS = 8;
/*  757:     */  
/*  761:     */  private static final int DIGIT_MASK = 255;
/*  762:     */  
/*  766:     */  private static final int DIGITS_PER_ELEMENT = 4;
/*  767:     */  
/*  771:     */  public static long binarySearch(float[][] a, long from, long to, float key)
/*  772:     */  {
/*  773: 767 */    to -= 1L;
/*  774: 768 */    while (from <= to) {
/*  775: 769 */      long mid = from + to >>> 1;
/*  776: 770 */      float midVal = get(a, mid);
/*  777: 771 */      if (midVal < key) { from = mid + 1L;
/*  778: 772 */      } else if (midVal > key) to = mid - 1L; else
/*  779: 773 */        return mid;
/*  780:     */    }
/*  781: 775 */    return -(from + 1L);
/*  782:     */  }
/*  783:     */  
/*  800:     */  public static long binarySearch(float[][] a, float key)
/*  801:     */  {
/*  802: 796 */    return binarySearch(a, 0L, length(a), key);
/*  803:     */  }
/*  804:     */  
/*  825:     */  public static long binarySearch(float[][] a, long from, long to, float key, FloatComparator c)
/*  826:     */  {
/*  827: 821 */    to -= 1L;
/*  828: 822 */    while (from <= to) {
/*  829: 823 */      long mid = from + to >>> 1;
/*  830: 824 */      float midVal = get(a, mid);
/*  831: 825 */      int cmp = c.compare(midVal, key);
/*  832: 826 */      if (cmp < 0) { from = mid + 1L;
/*  833: 827 */      } else if (cmp > 0) to = mid - 1L; else
/*  834: 828 */        return mid;
/*  835:     */    }
/*  836: 830 */    return -(from + 1L);
/*  837:     */  }
/*  838:     */  
/*  856:     */  public static long binarySearch(float[][] a, float key, FloatComparator c)
/*  857:     */  {
/*  858: 852 */    return binarySearch(a, 0L, length(a), key, c);
/*  859:     */  }
/*  860:     */  
/*  866:     */  private static final long fixFloat(float f)
/*  867:     */  {
/*  868: 862 */    long i = Float.floatToRawIntBits(f);
/*  869: 863 */    return i >= 0L ? i : i ^ 0x7FFFFFFF;
/*  870:     */  }
/*  871:     */  
/*  886:     */  public static void radixSort(float[][] a)
/*  887:     */  {
/*  888: 882 */    radixSort(a, 0L, length(a));
/*  889:     */  }
/*  890:     */  
/*  907:     */  public static void radixSort(float[][] a, long from, long to)
/*  908:     */  {
/*  909: 903 */    int maxLevel = 3;
/*  910: 904 */    int stackSize = 766;
/*  911: 905 */    long[] offsetStack = new long[766];
/*  912: 906 */    int offsetPos = 0;
/*  913: 907 */    long[] lengthStack = new long[766];
/*  914: 908 */    int lengthPos = 0;
/*  915: 909 */    int[] levelStack = new int[766];
/*  916: 910 */    int levelPos = 0;
/*  917: 911 */    offsetStack[(offsetPos++)] = from;
/*  918: 912 */    lengthStack[(lengthPos++)] = (to - from);
/*  919: 913 */    levelStack[(levelPos++)] = 0;
/*  920: 914 */    long[] count = new long[256];
/*  921: 915 */    long[] pos = new long[256];
/*  922: 916 */    byte[][] digit = ByteBigArrays.newBigArray(to - from);
/*  923: 917 */    while (offsetPos > 0) {
/*  924: 918 */      long first = offsetStack[(--offsetPos)];
/*  925: 919 */      long length = lengthStack[(--lengthPos)];
/*  926: 920 */      int level = levelStack[(--levelPos)];
/*  927: 921 */      int signMask = level % 4 == 0 ? 128 : 0;
/*  928: 922 */      if (length < 40L) {
/*  929: 923 */        selectionSort(a, first, first + length);
/*  930:     */      }
/*  931:     */      else {
/*  932: 926 */        int shift = (3 - level % 4) * 8;
/*  933:     */        
/*  934: 928 */        for (long i = length; i-- != 0L; ByteBigArrays.set(digit, i, (byte)(int)(fixFloat(get(a, first + i)) >>> shift & 0xFF ^ signMask))) {}
/*  935: 929 */        for (long i = length; i-- != 0L; count[(ByteBigArrays.get(digit, i) & 0xFF)] += 1L) {}
/*  936:     */        
/*  937: 931 */        int lastUsed = -1;
/*  938: 932 */        long p = 0L;
/*  939: 933 */        for (int i = 0; i < 256; i++) {
/*  940: 934 */          if (count[i] != 0L) {
/*  941: 935 */            lastUsed = i;
/*  942: 936 */            if ((level < 3) && (count[i] > 1L))
/*  943:     */            {
/*  944: 938 */              offsetStack[(offsetPos++)] = (p + first);
/*  945: 939 */              lengthStack[(lengthPos++)] = count[i];
/*  946: 940 */              levelStack[(levelPos++)] = (level + 1);
/*  947:     */            }
/*  948:     */          }
/*  949: 943 */          long tmp359_358 = (p + count[i]);p = tmp359_358;pos[i] = tmp359_358;
/*  950:     */        }
/*  951:     */        
/*  952: 946 */        long end = length - count[lastUsed];
/*  953: 947 */        count[lastUsed] = 0L;
/*  954:     */        
/*  955: 949 */        int c = -1;
/*  956: 950 */        for (long i = 0L; i < end; count[c] = 0L) {
/*  957: 951 */          float t = get(a, i + first);
/*  958: 952 */          c = ByteBigArrays.get(digit, i) & 0xFF;
/*  959: 953 */          for (;;) { long d; if ((d = pos[c] -= 1L) <= i) break;
/*  960: 954 */            float z = t;
/*  961: 955 */            int zz = c;
/*  962: 956 */            t = get(a, d + first);
/*  963: 957 */            c = ByteBigArrays.get(digit, d) & 0xFF;
/*  964: 958 */            set(a, d + first, z);
/*  965: 959 */            ByteBigArrays.set(digit, d, (byte)zz);
/*  966:     */          }
/*  967: 961 */          set(a, i + first, t);i += count[c];
/*  968:     */        }
/*  969:     */      }
/*  970:     */    } }
/*  971:     */  
/*  972: 966 */  private static void selectionSort(float[][] a, float[][] b, long from, long to) { for (long i = from; i < to - 1L; i += 1L) {
/*  973: 967 */      long m = i;
/*  974: 968 */      for (long j = i + 1L; j < to; j += 1L)
/*  975: 969 */        if ((get(a, j) < get(a, m)) || ((get(a, j) == get(a, m)) && (get(b, j) < get(b, m)))) m = j;
/*  976: 970 */      if (m != i) {
/*  977: 971 */        float t = get(a, i);
/*  978: 972 */        set(a, i, get(a, m));
/*  979: 973 */        set(a, m, t);
/*  980: 974 */        t = get(b, i);
/*  981: 975 */        set(b, i, get(b, m));
/*  982: 976 */        set(b, m, t);
/*  983:     */      }
/*  984:     */    }
/*  985:     */  }
/*  986:     */  
/* 1004:     */  public static void radixSort(float[][] a, float[][] b)
/* 1005:     */  {
/* 1006:1000 */    radixSort(a, b, 0L, length(a));
/* 1007:     */  }
/* 1008:     */  
/* 1029:     */  public static void radixSort(float[][] a, float[][] b, long from, long to)
/* 1030:     */  {
/* 1031:1025 */    int layers = 2;
/* 1032:1026 */    if (length(a) != length(b)) throw new IllegalArgumentException("Array size mismatch.");
/* 1033:1027 */    int maxLevel = 7;
/* 1034:1028 */    int stackSize = 1786;
/* 1035:1029 */    long[] offsetStack = new long[1786];
/* 1036:1030 */    int offsetPos = 0;
/* 1037:1031 */    long[] lengthStack = new long[1786];
/* 1038:1032 */    int lengthPos = 0;
/* 1039:1033 */    int[] levelStack = new int[1786];
/* 1040:1034 */    int levelPos = 0;
/* 1041:1035 */    offsetStack[(offsetPos++)] = from;
/* 1042:1036 */    lengthStack[(lengthPos++)] = (to - from);
/* 1043:1037 */    levelStack[(levelPos++)] = 0;
/* 1044:1038 */    long[] count = new long[256];
/* 1045:1039 */    long[] pos = new long[256];
/* 1046:1040 */    byte[][] digit = ByteBigArrays.newBigArray(to - from);
/* 1047:1041 */    while (offsetPos > 0) {
/* 1048:1042 */      long first = offsetStack[(--offsetPos)];
/* 1049:1043 */      long length = lengthStack[(--lengthPos)];
/* 1050:1044 */      int level = levelStack[(--levelPos)];
/* 1051:1045 */      int signMask = level % 4 == 0 ? 128 : 0;
/* 1052:1046 */      if (length < 40L) {
/* 1053:1047 */        selectionSort(a, b, first, first + length);
/* 1054:     */      }
/* 1055:     */      else {
/* 1056:1050 */        float[][] k = level < 4 ? a : b;
/* 1057:1051 */        int shift = (3 - level % 4) * 8;
/* 1058:     */        
/* 1059:1053 */        for (long i = length; i-- != 0L; ByteBigArrays.set(digit, i, (byte)(int)(fixFloat(get(k, first + i)) >>> shift & 0xFF ^ signMask))) {}
/* 1060:1054 */        for (long i = length; i-- != 0L; count[(ByteBigArrays.get(digit, i) & 0xFF)] += 1L) {}
/* 1061:     */        
/* 1062:1056 */        int lastUsed = -1;
/* 1063:1057 */        long p = 0L;
/* 1064:1058 */        for (int i = 0; i < 256; i++) {
/* 1065:1059 */          if (count[i] != 0L) {
/* 1066:1060 */            lastUsed = i;
/* 1067:1061 */            if ((level < 7) && (count[i] > 1L)) {
/* 1068:1062 */              offsetStack[(offsetPos++)] = (p + first);
/* 1069:1063 */              lengthStack[(lengthPos++)] = count[i];
/* 1070:1064 */              levelStack[(levelPos++)] = (level + 1);
/* 1071:     */            }
/* 1072:     */          }
/* 1073:1067 */          long tmp403_402 = (p + count[i]);p = tmp403_402;pos[i] = tmp403_402;
/* 1074:     */        }
/* 1075:     */        
/* 1076:1070 */        long end = length - count[lastUsed];
/* 1077:1071 */        count[lastUsed] = 0L;
/* 1078:     */        
/* 1079:1073 */        int c = -1;
/* 1080:1074 */        for (long i = 0L; i < end; count[c] = 0L) {
/* 1081:1075 */          float t = get(a, i + first);
/* 1082:1076 */          float u = get(b, i + first);
/* 1083:1077 */          c = ByteBigArrays.get(digit, i) & 0xFF;
/* 1084:1078 */          for (;;) { long d; if ((d = pos[c] -= 1L) <= i) break;
/* 1085:1079 */            float z = t;
/* 1086:1080 */            int zz = c;
/* 1087:1081 */            t = get(a, d + first);
/* 1088:1082 */            set(a, d + first, z);
/* 1089:1083 */            z = u;
/* 1090:1084 */            u = get(b, d + first);
/* 1091:1085 */            set(b, d + first, z);
/* 1092:1086 */            c = ByteBigArrays.get(digit, d) & 0xFF;
/* 1093:1087 */            ByteBigArrays.set(digit, d, (byte)zz);
/* 1094:     */          }
/* 1095:1089 */          set(a, i + first, t);
/* 1096:1090 */          set(b, i + first, u);i += count[c];
/* 1097:     */        }
/* 1098:     */      }
/* 1099:     */    }
/* 1100:     */  }
/* 1101:     */  
/* 1108:     */  public static float[][] shuffle(float[][] a, long from, long to, Random random)
/* 1109:     */  {
/* 1110:1103 */    for (long i = to - from; i-- != 0L;) {
/* 1111:1104 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 1112:1105 */      float t = get(a, from + i);
/* 1113:1106 */      set(a, from + i, get(a, from + p));
/* 1114:1107 */      set(a, from + p, t);
/* 1115:     */    }
/* 1116:1109 */    return a;
/* 1117:     */  }
/* 1118:     */  
/* 1123:     */  public static float[][] shuffle(float[][] a, Random random)
/* 1124:     */  {
/* 1125:1118 */    for (long i = length(a); i-- != 0L;) {
/* 1126:1119 */      long p = (random.nextLong() & 0xFFFFFFFF) % (i + 1L);
/* 1127:1120 */      float t = get(a, i);
/* 1128:1121 */      set(a, i, get(a, p));
/* 1129:1122 */      set(a, p, t);
/* 1130:     */    }
/* 1131:1124 */    return a;
/* 1132:     */  }
/* 1133:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatBigArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */