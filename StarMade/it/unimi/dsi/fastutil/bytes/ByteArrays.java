/*    1:     */package it.unimi.dsi.fastutil.bytes;
/*    2:     */
/*    3:     */import it.unimi.dsi.fastutil.Hash.Strategy;
/*    4:     */import it.unimi.dsi.fastutil.ints.IntArrays;
/*    5:     */import java.io.Serializable;
/*    6:     */import java.util.Random;
/*    7:     */
/*   84:     */public class ByteArrays
/*   85:     */{
/*   86:     */  public static final long ONEOVERPHI = 106039L;
/*   87:  87 */  public static final byte[] EMPTY_ARRAY = new byte[0];
/*   88:     */  
/*   89:     */  private static final int SMALL = 7;
/*   90:     */  
/*   91:     */  private static final int MEDIUM = 50;
/*   92:     */  
/*   93:     */  private static final int DIGIT_BITS = 8;
/*   94:     */  
/*   95:     */  private static final int DIGIT_MASK = 255;
/*   96:     */  private static final int DIGITS_PER_ELEMENT = 1;
/*   97:     */  
/*   98:     */  public static byte[] ensureCapacity(byte[] array, int length)
/*   99:     */  {
/*  100: 100 */    if (length > array.length) {
/*  101: 101 */      byte[] t = new byte[length];
/*  102:     */      
/*  103: 103 */      System.arraycopy(array, 0, t, 0, array.length);
/*  104: 104 */      return t;
/*  105:     */    }
/*  106: 106 */    return array;
/*  107:     */  }
/*  108:     */  
/*  116:     */  public static byte[] ensureCapacity(byte[] array, int length, int preserve)
/*  117:     */  {
/*  118: 118 */    if (length > array.length) {
/*  119: 119 */      byte[] t = new byte[length];
/*  120:     */      
/*  121: 121 */      System.arraycopy(array, 0, t, 0, preserve);
/*  122: 122 */      return t;
/*  123:     */    }
/*  124: 124 */    return array;
/*  125:     */  }
/*  126:     */  
/*  140:     */  public static byte[] grow(byte[] array, int length)
/*  141:     */  {
/*  142: 142 */    if (length > array.length) {
/*  143: 143 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  144: 144 */      byte[] t = new byte[newLength];
/*  145:     */      
/*  146: 146 */      System.arraycopy(array, 0, t, 0, array.length);
/*  147: 147 */      return t;
/*  148:     */    }
/*  149: 149 */    return array;
/*  150:     */  }
/*  151:     */  
/*  166:     */  public static byte[] grow(byte[] array, int length, int preserve)
/*  167:     */  {
/*  168: 168 */    if (length > array.length) {
/*  169: 169 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/*  170: 170 */      byte[] t = new byte[newLength];
/*  171:     */      
/*  172: 172 */      System.arraycopy(array, 0, t, 0, preserve);
/*  173: 173 */      return t;
/*  174:     */    }
/*  175: 175 */    return array;
/*  176:     */  }
/*  177:     */  
/*  186:     */  public static byte[] trim(byte[] array, int length)
/*  187:     */  {
/*  188: 188 */    if (length >= array.length) return array;
/*  189: 189 */    byte[] t = length == 0 ? EMPTY_ARRAY : new byte[length];
/*  190:     */    
/*  191: 191 */    System.arraycopy(array, 0, t, 0, length);
/*  192: 192 */    return t;
/*  193:     */  }
/*  194:     */  
/*  206:     */  public static byte[] setLength(byte[] array, int length)
/*  207:     */  {
/*  208: 208 */    if (length == array.length) return array;
/*  209: 209 */    if (length < array.length) return trim(array, length);
/*  210: 210 */    return ensureCapacity(array, length);
/*  211:     */  }
/*  212:     */  
/*  218:     */  public static byte[] copy(byte[] array, int offset, int length)
/*  219:     */  {
/*  220: 220 */    ensureOffsetLength(array, offset, length);
/*  221: 221 */    byte[] a = length == 0 ? EMPTY_ARRAY : new byte[length];
/*  222:     */    
/*  223: 223 */    System.arraycopy(array, offset, a, 0, length);
/*  224: 224 */    return a;
/*  225:     */  }
/*  226:     */  
/*  230:     */  public static byte[] copy(byte[] array)
/*  231:     */  {
/*  232: 232 */    return (byte[])array.clone();
/*  233:     */  }
/*  234:     */  
/*  241:     */  public static void fill(byte[] array, byte value)
/*  242:     */  {
/*  243: 243 */    int i = array.length;
/*  244: 244 */    while (i-- != 0) { array[i] = value;
/*  245:     */    }
/*  246:     */  }
/*  247:     */  
/*  256:     */  public static void fill(byte[] array, int from, int to, byte value)
/*  257:     */  {
/*  258: 258 */    ensureFromTo(array, from, to);
/*  259: 259 */    for (from != 0; to-- != 0; array[to] = value) {}
/*  260: 260 */    for (int i = from; i < to; i++) { array[i] = value;
/*  261:     */    }
/*  262:     */  }
/*  263:     */  
/*  270:     */  public static boolean equals(byte[] a1, byte[] a2)
/*  271:     */  {
/*  272: 272 */    int i = a1.length;
/*  273: 273 */    if (i != a2.length) return false;
/*  274: 274 */    while (i-- != 0) if (a1[i] != a2[i]) return false;
/*  275: 275 */    return true;
/*  276:     */  }
/*  277:     */  
/*  286:     */  public static void ensureFromTo(byte[] a, int from, int to)
/*  287:     */  {
/*  288: 288 */    it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/*  289:     */  }
/*  290:     */  
/*  299:     */  public static void ensureOffsetLength(byte[] a, int offset, int length)
/*  300:     */  {
/*  301: 301 */    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/*  302:     */  }
/*  303:     */  
/*  304:     */  private static void swap(byte[] x, int a, int b)
/*  305:     */  {
/*  306: 306 */    byte t = x[a];
/*  307: 307 */    x[a] = x[b];
/*  308: 308 */    x[b] = t;
/*  309:     */  }
/*  310:     */  
/*  311: 311 */  private static void vecSwap(byte[] x, int a, int b, int n) { for (int i = 0; i < n; b++) { swap(x, a, b);i++;a++;
/*  312:     */    } }
/*  313:     */  
/*  314: 314 */  private static int med3(byte[] x, int a, int b, int c, ByteComparator comp) { int ab = comp.compare(x[a], x[b]);
/*  315: 315 */    int ac = comp.compare(x[a], x[c]);
/*  316: 316 */    int bc = comp.compare(x[b], x[c]);
/*  317: 317 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  318:     */  }
/*  319:     */  
/*  320:     */  private static void selectionSort(byte[] a, int from, int to, ByteComparator comp)
/*  321:     */  {
/*  322: 322 */    for (int i = from; i < to - 1; i++) {
/*  323: 323 */      int m = i;
/*  324: 324 */      for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/*  325: 325 */      if (m != i) {
/*  326: 326 */        byte u = a[i];
/*  327: 327 */        a[i] = a[m];
/*  328: 328 */        a[m] = u;
/*  329:     */      }
/*  330:     */    }
/*  331:     */  }
/*  332:     */  
/*  333: 333 */  private static void insertionSort(byte[] a, int from, int to, ByteComparator comp) { int i = from; for (;;) { i++; if (i >= to) break;
/*  334: 334 */      byte t = a[i];
/*  335: 335 */      int j = i;
/*  336: 336 */      for (byte u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
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
/*  347:     */  private static void selectionSort(byte[] a, int from, int to) {
/*  348: 348 */    for (int i = from; i < to - 1; i++) {
/*  349: 349 */      int m = i;
/*  350: 350 */      for (int j = i + 1; j < to; j++) if (a[j] < a[m]) m = j;
/*  351: 351 */      if (m != i) {
/*  352: 352 */        byte u = a[i];
/*  353: 353 */        a[i] = a[m];
/*  354: 354 */        a[m] = u;
/*  355:     */      }
/*  356:     */    }
/*  357:     */  }
/*  358:     */  
/*  359:     */  private static void insertionSort(byte[] a, int from, int to) {
/*  360: 360 */    int i = from; for (;;) { i++; if (i >= to) break;
/*  361: 361 */      byte t = a[i];
/*  362: 362 */      int j = i;
/*  363: 363 */      for (byte u = a[(j - 1)]; t < u; u = a[(--j - 1)]) {
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
/*  385:     */  public static void quickSort(byte[] x, int from, int to, ByteComparator comp)
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
/*  406: 406 */    byte v = x[m];
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
/*  442:     */  public static void quickSort(byte[] x, ByteComparator comp)
/*  443:     */  {
/*  444: 444 */    quickSort(x, 0, x.length, comp);
/*  445:     */  }
/*  446:     */  
/*  447:     */  private static int med3(byte[] x, int a, int b, int c) {
/*  448: 448 */    int ab = x[a] == x[b] ? 0 : x[a] < x[b] ? -1 : 1;
/*  449: 449 */    int ac = x[a] == x[c] ? 0 : x[a] < x[c] ? -1 : 1;
/*  450: 450 */    int bc = x[b] == x[c] ? 0 : x[b] < x[c] ? -1 : 1;
/*  451: 451 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/*  452:     */  }
/*  453:     */  
/*  466:     */  public static void quickSort(byte[] x, int from, int to)
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
/*  487: 487 */    byte v = x[m];
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
/*  521:     */  public static void quickSort(byte[] x)
/*  522:     */  {
/*  523: 523 */    quickSort(x, 0, x.length);
/*  524:     */  }
/*  525:     */  
/*  535:     */  public static void mergeSort(byte[] a, int from, int to, byte[] supp)
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
/*  567:     */  public static void mergeSort(byte[] a, int from, int to)
/*  568:     */  {
/*  569: 569 */    mergeSort(a, from, to, (byte[])a.clone());
/*  570:     */  }
/*  571:     */  
/*  577:     */  public static void mergeSort(byte[] a)
/*  578:     */  {
/*  579: 579 */    mergeSort(a, 0, a.length);
/*  580:     */  }
/*  581:     */  
/*  593:     */  public static void mergeSort(byte[] a, int from, int to, ByteComparator comp, byte[] supp)
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
/*  627:     */  public static void mergeSort(byte[] a, int from, int to, ByteComparator comp)
/*  628:     */  {
/*  629: 629 */    mergeSort(a, from, to, comp, (byte[])a.clone());
/*  630:     */  }
/*  631:     */  
/*  639:     */  public static void mergeSort(byte[] a, ByteComparator comp)
/*  640:     */  {
/*  641: 641 */    mergeSort(a, 0, a.length, comp);
/*  642:     */  }
/*  643:     */  
/*  654:     */  public static int binarySearch(byte[] a, int from, int to, byte key)
/*  655:     */  {
/*  656:     */    
/*  657:     */    
/*  667: 667 */    while (from <= to) {
/*  668: 668 */      int mid = from + to >>> 1;
/*  669: 669 */      byte midVal = a[mid];
/*  670: 670 */      if (midVal < key) { from = mid + 1;
/*  671: 671 */      } else if (midVal > key) to = mid - 1; else
/*  672: 672 */        return mid;
/*  673:     */    }
/*  674: 674 */    return -(from + 1);
/*  675:     */  }
/*  676:     */  
/*  693:     */  public static int binarySearch(byte[] a, byte key)
/*  694:     */  {
/*  695: 695 */    return binarySearch(a, 0, a.length, key);
/*  696:     */  }
/*  697:     */  
/*  708:     */  public static int binarySearch(byte[] a, int from, int to, byte key, ByteComparator c)
/*  709:     */  {
/*  710:     */    
/*  711:     */    
/*  721: 721 */    while (from <= to) {
/*  722: 722 */      int mid = from + to >>> 1;
/*  723: 723 */      byte midVal = a[mid];
/*  724: 724 */      int cmp = c.compare(midVal, key);
/*  725: 725 */      if (cmp < 0) { from = mid + 1;
/*  726: 726 */      } else if (cmp > 0) to = mid - 1; else
/*  727: 727 */        return mid;
/*  728:     */    }
/*  729: 729 */    return -(from + 1);
/*  730:     */  }
/*  731:     */  
/*  749:     */  public static int binarySearch(byte[] a, byte key, ByteComparator c)
/*  750:     */  {
/*  751: 751 */    return binarySearch(a, 0, a.length, key, c);
/*  752:     */  }
/*  753:     */  
/*  775:     */  public static void radixSort(byte[] a)
/*  776:     */  {
/*  777: 777 */    radixSort(a, 0, a.length);
/*  778:     */  }
/*  779:     */  
/*  796:     */  public static void radixSort(byte[] a, int from, int to)
/*  797:     */  {
/*  798: 798 */    int maxLevel = 0;
/*  799: 799 */    int stackSize = 1;
/*  800: 800 */    int[] offsetStack = new int[1];
/*  801: 801 */    int offsetPos = 0;
/*  802: 802 */    int[] lengthStack = new int[1];
/*  803: 803 */    int lengthPos = 0;
/*  804: 804 */    int[] levelStack = new int[1];
/*  805: 805 */    int levelPos = 0;
/*  806: 806 */    offsetStack[(offsetPos++)] = from;
/*  807: 807 */    lengthStack[(lengthPos++)] = (to - from);
/*  808: 808 */    levelStack[(levelPos++)] = 0;
/*  809: 809 */    int[] count = new int[256];
/*  810: 810 */    int[] pos = new int[256];
/*  811: 811 */    byte[] digit = new byte[to - from];
/*  812: 812 */    while (offsetPos > 0) {
/*  813: 813 */      int first = offsetStack[(--offsetPos)];
/*  814: 814 */      int length = lengthStack[(--lengthPos)];
/*  815: 815 */      int level = levelStack[(--levelPos)];
/*  816: 816 */      int signMask = level % 1 == 0 ? 128 : 0;
/*  817: 817 */      if (length < 50) {
/*  818: 818 */        selectionSort(a, first, first + length);
/*  819:     */      }
/*  820:     */      else {
/*  821: 821 */        int shift = (0 - level % 1) * 8;
/*  822:     */        
/*  823: 823 */        for (int i = length; i-- != 0; digit[i] = ((byte)(a[(first + i)] >>> shift & 0xFF ^ signMask))) {}
/*  824: 824 */        for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1) {}
/*  825:     */        
/*  826: 826 */        int lastUsed = -1;
/*  827: 827 */        int i = 0; for (int p = 0; i < 256; i++) {
/*  828: 828 */          if (count[i] != 0) {
/*  829: 829 */            lastUsed = i;
/*  830: 830 */            if ((level < 0) && (count[i] > 1))
/*  831:     */            {
/*  832: 832 */              offsetStack[(offsetPos++)] = (p + first);
/*  833: 833 */              lengthStack[(lengthPos++)] = count[i];
/*  834: 834 */              levelStack[(levelPos++)] = (level + 1);
/*  835:     */            }
/*  836:     */          }
/*  837: 837 */          int tmp324_323 = (p + count[i]);p = tmp324_323;pos[i] = tmp324_323;
/*  838:     */        }
/*  839:     */        
/*  840: 840 */        int end = length - count[lastUsed];
/*  841: 841 */        count[lastUsed] = 0;
/*  842:     */        
/*  843: 843 */        int i = 0; for (int c = -1; i < end; count[c] = 0) {
/*  844: 844 */          byte t = a[(i + first)];
/*  845: 845 */          c = digit[i] & 0xFF;
/*  846: 846 */          for (;;) { int d; if ((d = pos[c] -= 1) <= i) break;
/*  847: 847 */            byte z = t;
/*  848: 848 */            int zz = c;
/*  849: 849 */            t = a[(d + first)];
/*  850: 850 */            c = digit[d] & 0xFF;
/*  851: 851 */            a[(d + first)] = z;
/*  852: 852 */            digit[d] = ((byte)zz);
/*  853:     */          }
/*  854: 854 */          a[(i + first)] = t;i += count[c];
/*  855:     */        }
/*  856:     */      }
/*  857:     */    } }
/*  858:     */  
/*  859: 859 */  private static void insertionSortIndirect(int[] perm, byte[] a, int from, int to) { int i = from; for (;;) { i++; if (i >= to) break;
/*  860: 860 */      int t = perm[i];
/*  861: 861 */      int j = i;
/*  862: 862 */      for (int u = perm[(j - 1)]; a[t] < a[u]; u = perm[(--j - 1)]) {
/*  863: 863 */        perm[j] = u;
/*  864: 864 */        if (from == j - 1) {
/*  865: 865 */          j--;
/*  866: 866 */          break;
/*  867:     */        }
/*  868:     */      }
/*  869: 869 */      perm[j] = t;
/*  870:     */    }
/*  871:     */  }
/*  872:     */  
/*  895:     */  public static void radixSortIndirect(int[] perm, byte[] a, boolean stable)
/*  896:     */  {
/*  897: 897 */    radixSortIndirect(perm, a, 0, perm.length, stable);
/*  898:     */  }
/*  899:     */  
/*  924:     */  public static void radixSortIndirect(int[] perm, byte[] a, int from, int to, boolean stable)
/*  925:     */  {
/*  926: 926 */    int maxLevel = 0;
/*  927: 927 */    int stackSize = 1;
/*  928: 928 */    int[] offsetStack = new int[1];
/*  929: 929 */    int offsetPos = 0;
/*  930: 930 */    int[] lengthStack = new int[1];
/*  931: 931 */    int lengthPos = 0;
/*  932: 932 */    int[] levelStack = new int[1];
/*  933: 933 */    int levelPos = 0;
/*  934: 934 */    offsetStack[(offsetPos++)] = from;
/*  935: 935 */    lengthStack[(lengthPos++)] = (to - from);
/*  936: 936 */    levelStack[(levelPos++)] = 0;
/*  937: 937 */    int[] count = new int[256];
/*  938: 938 */    int[] pos = stable ? null : new int[256];
/*  939: 939 */    int[] support = stable ? new int[perm.length] : null;
/*  940: 940 */    byte[] digit = new byte[to - from];
/*  941: 941 */    while (offsetPos > 0) {
/*  942: 942 */      int first = offsetStack[(--offsetPos)];
/*  943: 943 */      int length = lengthStack[(--lengthPos)];
/*  944: 944 */      int level = levelStack[(--levelPos)];
/*  945: 945 */      int signMask = level % 1 == 0 ? 128 : 0;
/*  946: 946 */      if (length < 50) {
/*  947: 947 */        insertionSortIndirect(perm, a, first, first + length);
/*  948:     */      }
/*  949:     */      else {
/*  950: 950 */        int shift = (0 - level % 1) * 8;
/*  951:     */        
/*  952: 952 */        for (int i = length; i-- != 0; digit[i] = ((byte)(a[perm[(first + i)]] >>> shift & 0xFF ^ signMask))) {}
/*  953: 953 */        for (int i = length; i-- != 0; count[(digit[i] & 0xFF)] += 1) {}
/*  954:     */        
/*  955: 955 */        int lastUsed = -1;
/*  956: 956 */        int i = 0; for (int p = 0; i < 256; i++) {
/*  957: 957 */          if (count[i] != 0) {
/*  958: 958 */            lastUsed = i;
/*  959: 959 */            if ((level < 0) && (count[i] > 1)) {
/*  960: 960 */              offsetStack[(offsetPos++)] = (p + first);
/*  961: 961 */              lengthStack[(lengthPos++)] = count[i];
/*  962: 962 */              levelStack[(levelPos++)] = (level + 1);
/*  963:     */            }
/*  964:     */          }
/*  965: 965 */          if (stable) { int tmp357_356 = (p + count[i]);p = tmp357_356;count[i] = tmp357_356;
/*  966: 966 */          } else { int tmp376_375 = (p + count[i]);p = tmp376_375;pos[i] = tmp376_375;
/*  967:     */          } }
/*  968: 968 */        if (stable) {
/*  969: 969 */          for (int i = length; i-- != 0; support[// INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.ByteArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */