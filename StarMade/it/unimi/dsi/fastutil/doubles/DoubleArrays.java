/*    1:     */package it.unimi.dsi.fastutil.doubles;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    4:     */import it.unimi.dsi.fastutil.ints.IntArrays;
/*    5:     */import java.io.Serializable;
/*    6:     */import java.util.Random;
/*    7:     */
/*   84:     */public class DoubleArrays
/*   85:     */{
/*   86:     */  public static final long ONEOVERPHI = 106039L;
/*   87:  87 */  public static final double[] EMPTY_ARRAY = new double[0];
/*   88:     */  
/*   89:     */  private static final int SMALL = 7;
/*   90:     */  
/*   91:     */  private static final int MEDIUM = 50;
/*   92:     */  
/*   93:     */  private static final int DIGIT_BITS = 8;
/*   94:     */  
/*   95:     */  private static final int DIGIT_MASK = 255;
/*   96:     */  private static final int DIGITS_PER_ELEMENT = 8;
/*   97:     */  
/*   98:     */  public static double[] ensureCapacity(double[] array, int length)
/*   99:     */  {
/*  100: 100 */    if (length > array.length) {
/*  101: 101 */      double[] t = new double[length];
/*  102:     */      
/*  103: 103 */      System.arraycopy(array, 0, t, 0, array.length);
/*  104: 104 */      return t;
/*  105:     */    }
/*  106: 106 */    return array;
/*  107:     */  }
/*  108:     */  
/*  116:     */  public static double[] ensureCapacity(double[] array, int length, int preserve)
/*  117:     */  {
/*  118: 118 */    if (length > array.length) {
/*  119: 119 */      double[] t = new double[length];
/*  120:     */      
/*  121: 121 */      System.arraycopy(array, 0, t, 0, preserve);
/*  122: 122 */      return t;
/*  123:     */    }
/*  124: 124 */    return array;
/*  125:     */  }
/*  126:     */  
/*  140:     */  public static double[] grow(double[] array, int length)
/*  141:     */  {
/*  142: 142 */    if (length > array.length) {
/*  143: 143 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144: 144 */      double[] t = new double[newLength];
/*  145:     */      
/*  146: 146 */      System.arraycopy(array, 0, t, 0, array.length);
/*  147: 147 */      return t;
/*  148:     */    }
/*  149: 149 */    return array;
/*  150:     */  }
/*  151:     */  
/*  166:     */  public static double[] grow(double[] array, int length, int preserve)
/*  167:     */  {
/*  168: 168 */    if (length > array.length) {
/*  169: 169 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170: 170 */      double[] t = new double[newLength];
/*  171:     */      
/*  172: 172 */      System.arraycopy(array, 0, t, 0, preserve);
/*  173: 173 */      return t;
/*  174:     */    }
/*  175: 175 */    return array;
/*  176:     */  }
/*  177:     */  
/*  186:     */  public static double[] trim(double[] array, int length)
/*  187:     */  {
/*  188: 188 */    if (length >= array.length) return array;
/*  189: 189 */    double[] t = length == 0 ? EMPTY_ARRAY : new double[length];
/*  190:     */    
/*  191: 191 */    System.arraycopy(array, 0, t, 0, length);
/*  192: 192 */    return t;
/*  193:     */  }
/*  194:     */  
/*  206:     */  public static double[] setLength(double[] array, int length)
/*  207:     */  {
/*  208: 208 */    if (length == array.length) return array;
/*  209: 209 */    if (length < array.length) return trim(array, length);
/*  210: 210 */    return ensureCapacity(array, length);
/*  211:     */  }
/*  212:     */  
/*  218:     */  public static double[] copy(double[] array, int offset, int length)
/*  219:     */  {
/*  220: 220 */    ensureOffsetLength(array, offset, length);
/*  221: 221 */    double[] a = length == 0 ? EMPTY_ARRAY : new double[length];
/*  222:     */    
/*  223: 223 */    System.arraycopy(array, offset, a, 0, length);
/*  224: 224 */    return a;
/*  225:     */  }
/*  226:     */  
/*  230:     */  public static double[] copy(double[] array)
/*  231:     */  {
/*  232: 232 */    return (double[])array.clone();
/*  233:     */  }
/*  234:     */  
/*  241:     */  public static void fill(double[] array, double value)
/*  242:     */  {
/*  243: 243 */    int i = array.length;
/*  244: 244 */    while (i-- != 0) { array[i] = value;
/*  245:     */    }
/*  246:     */  }
/*  247:     */  
/*  256:     */  public static void fill(double[] array, int from, int to, double value)
/*  257:     */  {
/*  258: 258 */    ensureFromTo(array, from, to);
/*  259: 259 */    for (from != 0; to-- != 0; array[to] = value) {}
/*  260: 260 */    for (int i = from; i < to; i++) { array[i] = value;
/*  261:     */    }
/*  262:     */  }
/*  263:     */  
/*  270:     */  public static boolean equals(double[] a1, double[] a2)
/*  271:     */  {
/*  272: 272 */    int i = a1.length;
/*  273: 273 */    if (i != a2.length) return false;
/*  274: 274 */    while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275: 275 */    return true;
/*  276:     */  }
/*  277:     */  
/*  286:     */  public static void ensureFromTo(double[] a, int from, int to)
/*  287:     */  {
/*  288: 288 */    it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*  289:     */  }
/*  290:     */  
/*  299:     */  public static void ensureOffsetLength(double[] a, int offset, int length)
/*  300:     */  {
/*  301: 301 */    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*  302:     */  }
/*  303:     */  
/*  304:     */  private static void swap(double[] x, int a, int b)
/*  305:     */  {
/*  306: 306 */    double t = x[a];
/*  307: 307 */    x[a] = x[b];
/*  308: 308 */    x[b] = t;
/*  309:     */  }
/*  310:     */  
/*  311: 311 */  private static void vecSwap(double[] x, int a, int b, int n) { for (int i = 0; i < n; b++) { swap(x, a, b);i++;a++;
/*  312:     */    } }
/*  313:     */  
/*  314: 314 */  private static int med3(double[] x, int a, int b, int c, DoubleComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315: 315 */    int ac = comp.compare(x[a], x[c]);
/*  316: 316 */    int bc = comp.compare(x[b], x[c]);
/*  317: 317 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  318:     */  }
/*  319:     */  
/*  320:     */  private static void selectionSort(double[] a, int from, int to, DoubleComparator comp)
/*  321:     */  {
/*  322: 322 */    for (int i = from; i < to - 1; i++) {
/*  323: 323 */      int m = i;
/*  324: 324 */      for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325: 325 */      if (m != i) {
/*  326: 326 */        double u = a[i];
/*  327: 327 */        a[i] = a[m];
/*  328: 328 */        a[m] = u;
/*  329:     */      }
/*  330:     */    }
/*  331:     */  }
/*  332:     */  
/*  333: 333 */  private static void insertionSort(double[] a, int from, int to, DoubleComparator comp) { int i = from; for (;;) { i++; if (i >= to) break;
/*  334: 334 */      double t = a[i];
/*  335: 335 */      int j = i;
/*  336: 336 */      for (double u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/*  337: 337 */        a[j] = u;
/*  338: 338 */        if (from == j - 1) {
/*  339: 339 */          j--;
/*  340: 340 */          break;
/*  341:     */        }
/*  342:     */      }
/*  343: 343 */      a[j] = t;
/*  344:     */    }
/*  345:     */  }
/*  346:     */  
/*  347:     */  private static void selectionSort(double[] a, int from, int to) {
/*  348: 348 */    for (int i = from; i < to - 1; i++) {
/*  349: 349 */      int m = i;
/*  350: 350 */      for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351: 351 */      if (m != i) {
/*  352: 352 */        double u = a[i];
/*  353: 353 */        a[i] = a[m];
/*  354: 354 */        a[m] = u;
/*  355:     */      }
/*  356:     */    }
/*  357:     */  }
/*  358:     */  
/*  359:     */  private static void insertionSort(double[] a, int from, int to) {
/*  360: 360 */    int i = from; for (;;) { i++; if (i >= to) break;
/*  361: 361 */      double t = a[i];
/*  362: 362 */      int j = i;
/*  363: 363 */      for (double u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
/*  364: 364 */        a[j] = u;
/*  365: 365 */        if (from == j - 1) {
/*  366: 366 */          j--;
/*  367: 367 */          break;
/*  368:     */        }
/*  369:     */      }
/*  370: 370 */      a[j] = t;
/*  371:     */    }
/*  372:     */  }
/*  373:     */  
/*  385:     */  public static void quickSort(double[] x, int from, int to, DoubleComparator comp)
/*  386:     */  {
/*  387: 387 */    int len = to - from;
/*  388:     */    
/*  389: 389 */    if (len < 7) {
/*  390: 390 */      selectionSort(x, from, to, comp);
/*  391: 391 */      return;
/*  392:     */    }
/*  393:     */    
/*  394: 394 */    int m = from + len / 2;
/*  395: 395 */    if (len > 7) {
/*  396: 396 */      int l = from;
/*  397: 397 */      int n = to - 1;
/*  398: 398 */      if (len > 50) {
/*  399: 399 */        int s = len / 8;
/*  400: 400 */        l = med3(x, l, l + s, l + 2 * s, comp);
/*  401: 401 */        m = med3(x, m - s, m, m + s, comp);
/*  402: 402 */        n = med3(x, n - 2 * s, n - s, n, comp);
/*  403:     */      }
/*  404: 404 */      m = med3(x, l, m, n, comp);
/*  405:     */    }
/*  406: 406 */    double v = x[m];
/*  407:     */    
/*  408: 408 */    int a = from;int b = a;int c = to - 1;int d = c;
/*  409:     */    for (;;) {
/*  410:     */      int comparison;
/*  411: 411 */      if ((b <= c) && ((comparison = comp.compare(x[b], v)) <= 0)) {
/*  412: 412 */        if (comparison == 0) swap(x, a++, b);
/*  413: 413 */        b++;
/*  414:     */      } else { int comparison;
/*  415: 415 */        while ((c >= b) && ((comparison = comp.compare(x[c], v)) >= 0)) {
/*  416: 416 */          if (comparison == 0) swap(x, c, d--);
/*  417: 417 */          c--;
/*  418:     */        }
/*  419: 419 */        if (b > c) break;
/*  420: 420 */        swap(x, b++, c--);
/*  421:     */      }
/*  422:     */    }
/*  423: 423 */    int n = to;
/*  424: 424 */    int s = Math.min(a - from, b - a);
/*  425: 425 */    vecSwap(x, from, b - s, s);
/*  426: 426 */    s = Math.min(d - c, n - d - 1);
/*  427: 427 */    vecSwap(x, b, n - s, s);
/*  428:     */    
/*  429: 429 */    if ((s = b - a) > 1) quickSort(x, from, from + s, comp);
/*  430: 430 */    if ((s = d - c) > 1) { quickSort(x, n - s, n, comp);
/*  431:     */    }
/*  432:     */  }
/*  433:     */  
/*  442:     */  public static void quickSort(double[] x, DoubleComparator comp)
/*  443:     */  {
/*  444: 444 */    quickSort(x, 0, x.length, comp);
/*  445:     */  }
/*  446:     */  
/*  447:     */  private static int med3(double[] x, int a, int b, int c) {
/*  448: 448 */    int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449: 449 */    int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450: 450 */    int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451: 451 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  452:     */  }
/*  453:     */  
/*  466:     */  public static void quickSort(double[] x, int from, int to)
/*  467:     */  {
/*  468: 468 */    int len = to - from;
/*  469:     */    
/*  470: 470 */    if (len < 7) {
/*  471: 471 */      selectionSort(x, from, to);
/*  472: 472 */      return;
/*  473:     */    }
/*  474:     */    
/*  475: 475 */    int m = from + len / 2;
/*  476: 476 */    if (len > 7) {
/*  477: 477 */      int l = from;
/*  478: 478 */      int n = to - 1;
/*  479: 479 */      if (len > 50) {
/*  480: 480 */        int s = len / 8;
/*  481: 481 */        l = med3(x, l, l + s, l + 2 * s);
/*  482: 482 */        m = med3(x, m - s, m, m + s);
/*  483: 483 */        n = med3(x, n - 2 * s, n - s, n);
/*  484:     */      }
/*  485: 485 */      m = med3(x, l, m, n);
/*  486:     */    }
/*  487: 487 */    double v = x[m];
/*  488:     */    
/*  489: 489 */    int a = from;int b = a;int c = to - 1;int d = c;
/*  490:     */    for (;;)
/*  491:     */    {
/*  492: 492 */      if (b <= c) { int comparison; if ((comparison = x[b] == v ? 0 : x[b] < v ? -1 : 1) <= 0) {
/*  493: 493 */          if (comparison == 0) swap(x, a++, b);
/*  494: 494 */          b++;
/*  495:     */        }
/*  496: 496 */      } else { while (c >= b) { int comparison; if ((comparison = x[c] == v ? 0 : x[c] < v ? -1 : 1) < 0) break;
/*  497: 497 */          if (comparison == 0) swap(x, c, d--);
/*  498: 498 */          c--;
/*  499:     */        }
/*  500: 500 */        if (b > c) break;
/*  501: 501 */        swap(x, b++, c--);
/*  502:     */      }
/*  503:     */    }
/*  504: 504 */    int n = to;
/*  505: 505 */    int s = Math.min(a - from, b - a);
/*  506: 506 */    vecSwap(x, from, b - s, s);
/*  507: 507 */    s = Math.min(d - c, n - d - 1);
/*  508: 508 */    vecSwap(x, b, n - s, s);
/*  509:     */    
/*  510: 510 */    if ((s = b - a) > 1) quickSort(x, from, from + s);
/*  511: 511 */    if ((s = d - c) > 1) { quickSort(x, n - s, n);
/*  512:     */    }
/*  513:     */  }
/*  514:     */  
/*  521:     */  public static void quickSort(double[] x)
/*  522:     */  {
/*  523: 523 */    quickSort(x, 0, x.length);
/*  524:     */  }
/*  525:     */  
/*  535:     */  public static void mergeSort(double[] a, int from, int to, double[] supp)
/*  536:     */  {
/*  537: 537 */    int len = to - from;
/*  538:     */    
/*  539: 539 */    if (len < 7) {
/*  540: 540 */      insertionSort(a, from, to);
/*  541: 541 */      return;
/*  542:     */    }
/*  543:     */    
/*  544: 544 */    int mid = from + to >>> 1;
/*  545: 545 */    mergeSort(supp, from, mid, a);
/*  546: 546 */    mergeSort(supp, mid, to, a);
/*  547:     */    
/*  549: 549 */    if (supp[(mid - 1)] <= supp[mid]) {
/*  550: 550 */      System.arraycopy(supp, from, a, from, len);
/*  551: 551 */      return;
/*  552:     */    }
/*  553:     */    
/*  554: 554 */    int i = from;int p = from; for (int q = mid; i < to; i++) {
/*  555: 555 */      if ((q >= to) || ((p < mid) && (supp[p] <= supp[q]))) a[i] = supp[(p++)]; else {
/*  556: 556 */        a[i] = supp[(q++)];
/*  557:     */      }
/*  558:     */    }
/*  559:     */  }
/*  560:     */  
/*  567:     */  public static void mergeSort(double[] a, int from, int to)
/*  568:     */  {
/*  569: 569 */    mergeSort(a, from, to, (double[])a.clone());
/*  570:     */  }
/*  571:     */  
/*  577:     */  public static void mergeSort(double[] a)
/*  578:     */  {
/*  579: 579 */    mergeSort(a, 0, a.length);
/*  580:     */  }
/*  581:     */  
/*  593:     */  public static void mergeSort(double[] a, int from, int to, DoubleComparator comp, double[] supp)
/*  594:     */  {
/*  595: 595 */    int len = to - from;
/*  596:     */    
/*  597: 597 */    if (len < 7) {
/*  598: 598 */      insertionSort(a, from, to, comp);
/*  599: 599 */      return;
/*  600:     */    }
/*  601:     */    
/*  602: 602 */    int mid = from + to >>> 1;
/*  603: 603 */    mergeSort(supp, from, mid, comp, a);
/*  604: 604 */    mergeSort(supp, mid, to, comp, a);
/*  605:     */    
/*  607: 607 */    if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0) {
/*  608: 608 */      System.arraycopy(supp, from, a, from, len);
/*  609: 609 */      return;
/*  610:     */    }
/*  611:     */    
/*  612: 612 */    int i = from;int p = from; for (int q = mid; i < to; i++) {
/*  613: 613 */      if ((q >= to) || ((p < mid) && (comp.compare(supp[p], supp[q]) <= 0))) a[i] = supp[(p++)]; else {
/*  614: 614 */        a[i] = supp[(q++)];
/*  615:     */      }
/*  616:     */    }
/*  617:     */  }
/*  618:     */  
/*  627:     */  public static void mergeSort(double[] a, int from, int to, DoubleComparator comp)
/*  628:     */  {
/*  629: 629 */    mergeSort(a, from, to, comp, (double[])a.clone());
/*  630:     */  }
/*  631:     */  
/*  639:     */  public static void mergeSort(double[] a, DoubleComparator comp)
/*  640:     */  {
/*  641: 641 */    mergeSort(a, 0, a.length, comp);
/*  642:     */  }
/*  643:     */  
/*  654:     */  public static int binarySearch(double[] a, int from, int to, double key)
/*  655:     */  {
/*  656:     */    
/*  657:     */    
/*  667: 667 */    while (from <= to) {
/*  668: 668 */      int mid = from + to >>> 1;
/*  669: 669 */      double midVal = a[mid];
/*  670: 670 */      if (midVal < key) { from = mid + 1;
/*  671: 671 */      } else if (midVal > key) to = mid - 1; else
/*  672: 672 */        return mid;
/*  673:     */    }
/*  674: 674 */    return -(from + 1);
/*  675:     */  }
/*  676:     */  
/*  693:     */  public static int binarySearch(double[] a, double key)
/*  694:     */  {
/*  695: 695 */    return binarySearch(a, 0, a.length, key);
/*  696:     */  }
/*  697:     */  
/*  708:     */  public static int binarySearch(double[] a, int from, int to, double key, DoubleComparator c)
/*  709:     */  {
/*  710:     */    
/*  711:     */    
/*  721: 721 */    while (from <= to) {
/*  722: 722 */      int mid = from + to >>> 1;
/*  723: 723 */      double midVal = a[mid];
/*  724: 724 */      int cmp = c.compare(midVal, key);
/*  725: 725 */      if (cmp < 0) { from = mid + 1;
/*  726: 726 */      } else if (cmp > 0) to = mid - 1; else
/*  727: 727 */        return mid;
/*  728:     */    }
/*  729: 729 */    return -(from + 1);
/*  730:     */  }
/*  731:     */  
/*  749:     */  public static int binarySearch(double[] a, double key, DoubleComparator c)
/*  750:     */  {
/*  751: 751 */    return binarySearch(a, 0, a.length, key, c);
/*  752:     */  }
/*  753:     */  
/*  759:     */  private static final long fixDouble(double d)
/*  760:     */  {
/*  761: 761 */    long l = Double.doubleToRawLongBits(d);
/*  762: 762 */    return l >= 0L ? l : l ^ 0xFFFFFFFF;
/*  763:     */  }
/*  764:     */  
/*  779:     */  public static void radixSort(double[] a)
/*  780:     */  {
/*  781: 781 */    radixSort(a, 0, a.length);
/*  782:     */  }
/*  783:     */  
/*  800:     */  public static void radixSort(double[] a, int from, int to)
/*  801:     */  {
/*  802: 802 */    int maxLevel = 7;
/*  803: 803 */    int stackSize = 1786;
/*  804: 804 */    int[] offsetStack = new int[1786];
/*  805: 805 */    int offsetPos = 0;
/*  806: 806 */    int[] lengthStack = new int[1786];
/*  807: 807 */    int lengthPos = 0;
/*  808: 808 */    int[] levelStack = new int[1786];
/*  809: 809 */    int levelPos = 0;
/*  810: 810 */    offsetStack[(offsetPos++)] = from;
/*  811: 811 */    lengthStack[(lengthPos++)] = (to - from);
/*  812: 812 */    levelStack[(levelPos++)] = 0;
/*  813: 813 */    int[] count = new int[256];
/*  814: 814 */    int[] pos = new int[256];
/*  815: 815 */    byte[] digit = new byte[to - from];
/*  816: 816 */    while (offsetPos > 0) {
/*  817: 817 */      int first = offsetStack[(--offsetPos)];
/*  818: 818 */      int length = lengthStack[(--lengthPos)];
/*  819: 819 */      int level = levelStack[(--levelPos)];
/*  820: 820 */      int signMask = level % 8 == 0 ? 128 : 0;
/*  821: 821 */      if (length < 50) {
/*  822: 822 */        selectionSort(a, first, first + length);
/*  823:     */      }
/*  824:     */      else {
/*  825: 825 */        int shift = (7 - level % 8) * 8;
/*  826:     */        
/*  827: 827 */        for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixDouble(a[(first + i)]) >>> shift & 0xFF ^ signMask))) {}
/*  828: 828 */        for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1) {}
/*  829:     */        
/*  830: 830 */        int lastUsed = -1;
/*  831: 831 */        int i = 0; for (int p = 0; i < 256; i++) {
/*  832: 832 */          if (count[i] != 0) {
/*  833: 833 */            lastUsed = i;
/*  834: 834 */            if ((level < 7) && (count[i] > 1))
/*  835:     */            {
/*  836: 836 */              offsetStack[(offsetPos++)] = (p + first);
/*  837: 837 */              lengthStack[(lengthPos++)] = count[i];
/*  838: 838 */              levelStack[(levelPos++)] = (level + 1);
/*  839:     */            }
/*  840:     */          }
/*  841: 841 */          int tmp343_342 = (p + count[i]);p = tmp343_342;pos[i] = tmp343_342;
/*  842:     */        }
/*  843:     */        
/*  844: 844 */        int end = length - count[lastUsed];
/*  845: 845 */        count[lastUsed] = 0;
/*  846:     */        
/*  847: 847 */        int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  848: 848 */          double t = a[(i + first)];
/*  849: 849 */          c = digit[i] & 0xFF;
/*  850: 850 */          for (;;) { int d; if ((d = pos[c] -= 1) <= i) break;
/*  851: 851 */            double z = t;
/*  852: 852 */            int zz = c;
/*  853: 853 */            t = a[(d + first)];
/*  854: 854 */            c = digit[d] & 0xFF;
/*  855: 855 */            a[(d + first)] = z;
/*  856: 856 */            digit[d] = ((byte)zz);
/*  857:     */          }
/*  858: 858 */          a[(i + first)] = t;i += count[c];
/*  859:     */        }
/*  860:     */      }
/*  861:     */    } }
/*  862:     */  
/*  863: 863 */  private static void insertionSortIndirect(int[] perm, double[] a, int from, int to) { int i = from; for (;;) { i++; if (i >= to) break;
/*  864: 864 */      int t = perm[i];
/*  865: 865 */      int j = i;
/*  866: 866 */      for (int u = perm[(j - 1)]; a[t] < a[u]; u = perm[(--j - 1)]) {
/*  867: 867 */        perm[j] = u;
/*  868: 868 */        if (from == j - 1) {
/*  869: 869 */          j--;
/*  870: 870 */          break;
/*  871:     */        }
/*  872:     */      }
/*  873: 873 */      perm[j] = t;
/*  874:     */    }
/*  875:     */  }
/*  876:     */  
/*  899:     */  public static void radixSortIndirect(int[] perm, double[] a, boolean stable)
/*  900:     */  {
/*  901: 901 */    radixSortIndirect(perm, a, 0, perm.length, stable);
/*  902:     */  }
/*  903:     */  
/*  928:     */  public static void radixSortIndirect(int[] perm, double[] a, int from, int to, boolean stable)
/*  929:     */  {
/*  930: 930 */    int maxLevel = 7;
/*  931: 931 */    int stackSize = 1786;
/*  932: 932 */    int[] offsetStack = new int[1786];
/*  933: 933 */    int offsetPos = 0;
/*  934: 934 */    int[] lengthStack = new int[1786];
/*  935: 935 */    int lengthPos = 0;
/*  936: 936 */    int[] levelStack = new int[1786];
/*  937: 937 */    int levelPos = 0;
/*  938: 938 */    offsetStack[(offsetPos++)] = from;
/*  939: 939 */    lengthStack[(lengthPos++)] = (to - from);
/*  940: 940 */    levelStack[(levelPos++)] = 0;
/*  941: 941 */    int[] count = new int[256];
/*  942: 942 */    int[] pos = stable ? null : new int[256];
/*  943: 943 */    int[] support = stable ? new int[perm.length] : null;
/*  944: 944 */    byte[] digit = new byte[to - from];
/*  945: 945 */    while (offsetPos > 0) {
/*  946: 946 */      int first = offsetStack[(--offsetPos)];
/*  947: 947 */      int length = lengthStack[(--lengthPos)];
/*  948: 948 */      int level = levelStack[(--levelPos)];
/*  949: 949 */      int signMask = level % 8 == 0 ? 128 : 0;
/*  950: 950 */      if (length < 50) {
/*  951: 951 */        insertionSortIndirect(perm, a, first, first + length);
/*  952:     */      }
/*  953:     */      else {
/*  954: 954 */        int shift = (7 - level % 8) * 8;
/*  955:     */        
/*  956: 956 */        for (int i = length; i-- != 0; digit[i] = ((byte)(int)(fixDouble(a[perm[(first + i)]]) >>> shift & 0xFF ^ signMask))) {}
/*  957: 957 */        for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1) {}
/*  958:     */        
/*  959: 959 */        int lastUsed = -1;
/*  960: 960 */        int i = 0; for (int p = 0; i < 256; i++) {
/*  961: 961 */          if (count[i] != 0) {
/*  962: 962 */            lastUsed = i;
/*  963: 963 */            if ((level < 7) && (count[i] > 1)) {
/*  964: 964 */              offsetStack[(offsetPos++)] = (p + first);
/*  965: 965 */              lengthStack[(lengthPos++)] = count[i];
/*  966: 966 */              levelStack[(levelPos++)] = (level + 1);
/*  967:     */            }
/*  968:     */          }
/*  969: 969 */          if (stable) { int tmp376_375 = (p + count[i]);p = tmp376_375;count[i] = tmp376_375;
/*  970: 970 */          } else { int tmp395_394 = (p + count[i]);p = tmp395_394;pos[i] = tmp395_394;
/*  971:     */          } }
/*  972: 972 */        if (stable) {
/*  973: 973 */          for (int i = length; i-- != 0; support[// INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */