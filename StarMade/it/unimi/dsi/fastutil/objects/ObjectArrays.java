/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.lang.reflect.Array;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.Random;
/*   8:    */
/*  84:    */public class ObjectArrays
/*  85:    */{
/*  86:    */  public static final long ONEOVERPHI = 106039L;
/*  87: 87 */  public static final Object[] EMPTY_ARRAY = new Object[0];
/*  88:    */  
/*  92:    */  private static final int SMALL = 7;
/*  93:    */  
/*  97:    */  private static final int MEDIUM = 50;
/*  98:    */  
/* 102:    */  private static <K> K[] newArray(K[] prototype, int length)
/* 103:    */  {
/* 104:104 */    Class<?> componentType = prototype.getClass().getComponentType();
/* 105:105 */    if ((length == 0) && (componentType == Object.class)) return (Object[])EMPTY_ARRAY;
/* 106:106 */    return (Object[])Array.newInstance(prototype.getClass().getComponentType(), length);
/* 107:    */  }
/* 108:    */  
/* 120:    */  public static <K> K[] ensureCapacity(K[] array, int length)
/* 121:    */  {
/* 122:122 */    if (length > array.length) {
/* 123:123 */      K[] t = newArray(array, length);
/* 124:    */      
/* 129:129 */      System.arraycopy(array, 0, t, 0, array.length);
/* 130:130 */      return t;
/* 131:    */    }
/* 132:132 */    return array;
/* 133:    */  }
/* 134:    */  
/* 143:    */  public static <K> K[] ensureCapacity(K[] array, int length, int preserve)
/* 144:    */  {
/* 145:145 */    if (length > array.length) {
/* 146:146 */      K[] t = newArray(array, length);
/* 147:    */      
/* 152:152 */      System.arraycopy(array, 0, t, 0, preserve);
/* 153:153 */      return t;
/* 154:    */    }
/* 155:155 */    return array;
/* 156:    */  }
/* 157:    */  
/* 173:    */  public static <K> K[] grow(K[] array, int length)
/* 174:    */  {
/* 175:175 */    if (length > array.length) {
/* 176:176 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 177:177 */      K[] t = newArray(array, newLength);
/* 178:    */      
/* 183:183 */      System.arraycopy(array, 0, t, 0, array.length);
/* 184:184 */      return t;
/* 185:    */    }
/* 186:186 */    return array;
/* 187:    */  }
/* 188:    */  
/* 206:    */  public static <K> K[] grow(K[] array, int length, int preserve)
/* 207:    */  {
/* 208:208 */    if (length > array.length) {
/* 209:209 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 210:    */      
/* 211:211 */      K[] t = newArray(array, newLength);
/* 212:    */      
/* 217:217 */      System.arraycopy(array, 0, t, 0, preserve);
/* 218:    */      
/* 219:219 */      return t;
/* 220:    */    }
/* 221:221 */    return array;
/* 222:    */  }
/* 223:    */  
/* 235:    */  public static <K> K[] trim(K[] array, int length)
/* 236:    */  {
/* 237:237 */    if (length >= array.length) return array;
/* 238:238 */    K[] t = newArray(array, length);
/* 239:    */    
/* 244:244 */    System.arraycopy(array, 0, t, 0, length);
/* 245:245 */    return t;
/* 246:    */  }
/* 247:    */  
/* 261:    */  public static <K> K[] setLength(K[] array, int length)
/* 262:    */  {
/* 263:263 */    if (length == array.length) return array;
/* 264:264 */    if (length < array.length) return trim(array, length);
/* 265:265 */    return ensureCapacity(array, length);
/* 266:    */  }
/* 267:    */  
/* 275:    */  public static <K> K[] copy(K[] array, int offset, int length)
/* 276:    */  {
/* 277:277 */    ensureOffsetLength(array, offset, length);
/* 278:278 */    K[] a = newArray(array, length);
/* 279:    */    
/* 284:284 */    System.arraycopy(array, offset, a, 0, length);
/* 285:285 */    return a;
/* 286:    */  }
/* 287:    */  
/* 293:    */  public static <K> K[] copy(K[] array)
/* 294:    */  {
/* 295:295 */    return (Object[])array.clone();
/* 296:    */  }
/* 297:    */  
/* 306:    */  public static <K> void fill(K[] array, K value)
/* 307:    */  {
/* 308:308 */    int i = array.length;
/* 309:309 */    while (i-- != 0) { array[i] = value;
/* 310:    */    }
/* 311:    */  }
/* 312:    */  
/* 323:    */  public static <K> void fill(K[] array, int from, int to, K value)
/* 324:    */  {
/* 325:325 */    ensureFromTo(array, from, to);
/* 326:326 */    for (from != 0; to-- != 0; array[to] = value) {}
/* 327:327 */    for (int i = from; i < to; i++) { array[i] = value;
/* 328:    */    }
/* 329:    */  }
/* 330:    */  
/* 341:    */  public static <K> boolean equals(K[] a1, K[] a2)
/* 342:    */  {
/* 343:343 */    int i = a1.length;
/* 344:344 */    if (i != a2.length) return false;
/* 345:345 */    for (; i-- != 0; return false) label11: if (a1[i] == null ? a2[i] == null : a1[i].equals(a2[i])) break label11;
/* 346:346 */    return true;
/* 347:    */  }
/* 348:    */  
/* 361:    */  public static <K> void ensureFromTo(K[] a, int from, int to)
/* 362:    */  {
/* 363:363 */    it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/* 364:    */  }
/* 365:    */  
/* 375:    */  public static <K> void ensureOffsetLength(K[] a, int offset, int length)
/* 376:    */  {
/* 377:377 */    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/* 378:    */  }
/* 379:    */  
/* 382:    */  private static <K> void swap(K[] x, int a, int b)
/* 383:    */  {
/* 384:384 */    K t = x[a];
/* 385:385 */    x[a] = x[b];
/* 386:386 */    x[b] = t;
/* 387:    */  }
/* 388:    */  
/* 389:    */  private static <K> void vecSwap(K[] x, int a, int b, int n) {
/* 390:390 */    for (int i = 0; i < n; b++) { swap(x, a, b);i++;a++;
/* 391:    */    }
/* 392:    */  }
/* 393:    */  
/* 394:394 */  private static <K> int med3(K[] x, int a, int b, int c, Comparator<K> comp) { int ab = comp.compare(x[a], x[b]);
/* 395:395 */    int ac = comp.compare(x[a], x[c]);
/* 396:396 */    int bc = comp.compare(x[b], x[c]);
/* 397:397 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 398:    */  }
/* 399:    */  
/* 402:    */  private static <K> void selectionSort(K[] a, int from, int to, Comparator<K> comp)
/* 403:    */  {
/* 404:404 */    for (int i = from; i < to - 1; i++) {
/* 405:405 */      int m = i;
/* 406:406 */      for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/* 407:407 */      if (m != i) {
/* 408:408 */        K u = a[i];
/* 409:409 */        a[i] = a[m];
/* 410:410 */        a[m] = u;
/* 411:    */      }
/* 412:    */    }
/* 413:    */  }
/* 414:    */  
/* 415:    */  private static <K> void insertionSort(K[] a, int from, int to, Comparator<K> comp)
/* 416:    */  {
/* 417:417 */    int i = from; for (;;) { i++; if (i >= to) break;
/* 418:418 */      K t = a[i];
/* 419:419 */      int j = i;
/* 420:420 */      for (K u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/* 421:421 */        a[j] = u;
/* 422:422 */        if (from == j - 1) {
/* 423:423 */          j--;
/* 424:424 */          break;
/* 425:    */        }
/* 426:    */      }
/* 427:427 */      a[j] = t;
/* 428:    */    }
/* 429:    */  }
/* 430:    */  
/* 431:    */  private static <K> void selectionSort(K[] a, int from, int to)
/* 432:    */  {
/* 433:433 */    for (int i = from; i < to - 1; i++) {
/* 434:434 */      int m = i;
/* 435:435 */      for (int j = i + 1; j < to; j++) if (((Comparable)a[j]).compareTo(a[m]) < 0) m = j;
/* 436:436 */      if (m != i) {
/* 437:437 */        K u = a[i];
/* 438:438 */        a[i] = a[m];
/* 439:439 */        a[m] = u;
/* 440:    */      }
/* 441:    */    }
/* 442:    */  }
/* 443:    */  
/* 444:    */  private static <K> void insertionSort(K[] a, int from, int to)
/* 445:    */  {
/* 446:446 */    int i = from; for (;;) { i++; if (i >= to) break;
/* 447:447 */      K t = a[i];
/* 448:448 */      int j = i;
/* 449:449 */      for (K u = a[(j - 1)]; ((Comparable)t).compareTo(u) < 0; u = a[(--j - 1)]) {
/* 450:450 */        a[j] = u;
/* 451:451 */        if (from == j - 1) {
/* 452:452 */          j--;
/* 453:453 */          break;
/* 454:    */        }
/* 455:    */      }
/* 456:456 */      a[j] = t;
/* 457:    */    }
/* 458:    */  }
/* 459:    */  
/* 473:    */  public static <K> void quickSort(K[] x, int from, int to, Comparator<K> comp)
/* 474:    */  {
/* 475:475 */    int len = to - from;
/* 476:    */    
/* 478:478 */    if (len < 7) {
/* 479:479 */      selectionSort(x, from, to, comp);
/* 480:480 */      return;
/* 481:    */    }
/* 482:    */    
/* 484:484 */    int m = from + len / 2;
/* 485:485 */    if (len > 7) {
/* 486:486 */      int l = from;
/* 487:487 */      int n = to - 1;
/* 488:488 */      if (len > 50) {
/* 489:489 */        int s = len / 8;
/* 490:490 */        l = med3(x, l, l + s, l + 2 * s, comp);
/* 491:491 */        m = med3(x, m - s, m, m + s, comp);
/* 492:492 */        n = med3(x, n - 2 * s, n - s, n, comp);
/* 493:    */      }
/* 494:494 */      m = med3(x, l, m, n, comp);
/* 495:    */    }
/* 496:    */    
/* 497:497 */    K v = x[m];
/* 498:    */    
/* 500:500 */    int a = from;int b = a;int c = to - 1;int d = c;
/* 501:    */    for (;;) {
/* 502:    */      int comparison;
/* 503:503 */      if ((b <= c) && ((comparison = comp.compare(x[b], v)) <= 0)) {
/* 504:504 */        if (comparison == 0) swap(x, a++, b);
/* 505:505 */        b++;
/* 506:    */      } else { int comparison;
/* 507:507 */        while ((c >= b) && ((comparison = comp.compare(x[c], v)) >= 0)) {
/* 508:508 */          if (comparison == 0) swap(x, c, d--);
/* 509:509 */          c--;
/* 510:    */        }
/* 511:511 */        if (b > c) break;
/* 512:512 */        swap(x, b++, c--);
/* 513:    */      }
/* 514:    */    }
/* 515:    */    
/* 516:516 */    int n = to;
/* 517:517 */    int s = Math.min(a - from, b - a);
/* 518:518 */    vecSwap(x, from, b - s, s);
/* 519:519 */    s = Math.min(d - c, n - d - 1);
/* 520:520 */    vecSwap(x, b, n - s, s);
/* 521:    */    
/* 523:523 */    if ((s = b - a) > 1) quickSort(x, from, from + s, comp);
/* 524:524 */    if ((s = d - c) > 1) { quickSort(x, n - s, n, comp);
/* 525:    */    }
/* 526:    */  }
/* 527:    */  
/* 538:    */  public static <K> void quickSort(K[] x, Comparator<K> comp)
/* 539:    */  {
/* 540:540 */    quickSort(x, 0, x.length, comp);
/* 541:    */  }
/* 542:    */  
/* 544:    */  private static <K> int med3(K[] x, int a, int b, int c)
/* 545:    */  {
/* 546:546 */    int ab = ((Comparable)x[a]).compareTo(x[b]);
/* 547:547 */    int ac = ((Comparable)x[a]).compareTo(x[c]);
/* 548:548 */    int bc = ((Comparable)x[b]).compareTo(x[c]);
/* 549:549 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 550:    */  }
/* 551:    */  
/* 567:    */  public static <K> void quickSort(K[] x, int from, int to)
/* 568:    */  {
/* 569:569 */    int len = to - from;
/* 570:    */    
/* 572:572 */    if (len < 7) {
/* 573:573 */      selectionSort(x, from, to);
/* 574:574 */      return;
/* 575:    */    }
/* 576:    */    
/* 578:578 */    int m = from + len / 2;
/* 579:579 */    if (len > 7) {
/* 580:580 */      int l = from;
/* 581:581 */      int n = to - 1;
/* 582:582 */      if (len > 50) {
/* 583:583 */        int s = len / 8;
/* 584:584 */        l = med3(x, l, l + s, l + 2 * s);
/* 585:585 */        m = med3(x, m - s, m, m + s);
/* 586:586 */        n = med3(x, n - 2 * s, n - s, n);
/* 587:    */      }
/* 588:588 */      m = med3(x, l, m, n);
/* 589:    */    }
/* 590:    */    
/* 591:591 */    K v = x[m];
/* 592:    */    
/* 594:594 */    int a = from;int b = a;int c = to - 1;int d = c;
/* 595:    */    for (;;) {
/* 596:    */      int comparison;
/* 597:597 */      if ((b <= c) && ((comparison = ((Comparable)x[b]).compareTo(v)) <= 0)) {
/* 598:598 */        if (comparison == 0) swap(x, a++, b);
/* 599:599 */        b++;
/* 600:    */      } else { int comparison;
/* 601:601 */        while ((c >= b) && ((comparison = ((Comparable)x[c]).compareTo(v)) >= 0)) {
/* 602:602 */          if (comparison == 0) swap(x, c, d--);
/* 603:603 */          c--;
/* 604:    */        }
/* 605:605 */        if (b > c) break;
/* 606:606 */        swap(x, b++, c--);
/* 607:    */      }
/* 608:    */    }
/* 609:    */    
/* 610:610 */    int n = to;
/* 611:611 */    int s = Math.min(a - from, b - a);
/* 612:612 */    vecSwap(x, from, b - s, s);
/* 613:613 */    s = Math.min(d - c, n - d - 1);
/* 614:614 */    vecSwap(x, b, n - s, s);
/* 615:    */    
/* 617:617 */    if ((s = b - a) > 1) quickSort(x, from, from + s);
/* 618:618 */    if ((s = d - c) > 1) { quickSort(x, n - s, n);
/* 619:    */    }
/* 620:    */  }
/* 621:    */  
/* 630:    */  public static <K> void quickSort(K[] x)
/* 631:    */  {
/* 632:632 */    quickSort(x, 0, x.length);
/* 633:    */  }
/* 634:    */  
/* 646:    */  public static <K> void mergeSort(K[] a, int from, int to, K[] supp)
/* 647:    */  {
/* 648:648 */    int len = to - from;
/* 649:    */    
/* 651:651 */    if (len < 7) {
/* 652:652 */      insertionSort(a, from, to);
/* 653:653 */      return;
/* 654:    */    }
/* 655:    */    
/* 657:657 */    int mid = from + to >>> 1;
/* 658:658 */    mergeSort(supp, from, mid, a);
/* 659:659 */    mergeSort(supp, mid, to, a);
/* 660:    */    
/* 663:663 */    if (((Comparable)supp[(mid - 1)]).compareTo(supp[mid]) <= 0) {
/* 664:664 */      System.arraycopy(supp, from, a, from, len);
/* 665:665 */      return;
/* 666:    */    }
/* 667:    */    
/* 669:669 */    int i = from;int p = from; for (int q = mid; i < to; i++) {
/* 670:670 */      if ((q >= to) || ((p < mid) && (((Comparable)supp[p]).compareTo(supp[q]) <= 0))) a[i] = supp[(p++)]; else {
/* 671:671 */        a[i] = supp[(q++)];
/* 672:    */      }
/* 673:    */    }
/* 674:    */  }
/* 675:    */  
/* 683:    */  public static <K> void mergeSort(K[] a, int from, int to)
/* 684:    */  {
/* 685:685 */    mergeSort(a, from, to, (Object[])a.clone());
/* 686:    */  }
/* 687:    */  
/* 694:    */  public static <K> void mergeSort(K[] a)
/* 695:    */  {
/* 696:696 */    mergeSort(a, 0, a.length);
/* 697:    */  }
/* 698:    */  
/* 711:    */  public static <K> void mergeSort(K[] a, int from, int to, Comparator<K> comp, K[] supp)
/* 712:    */  {
/* 713:713 */    int len = to - from;
/* 714:    */    
/* 716:716 */    if (len < 7) {
/* 717:717 */      insertionSort(a, from, to, comp);
/* 718:718 */      return;
/* 719:    */    }
/* 720:    */    
/* 722:722 */    int mid = from + to >>> 1;
/* 723:723 */    mergeSort(supp, from, mid, comp, a);
/* 724:724 */    mergeSort(supp, mid, to, comp, a);
/* 725:    */    
/* 728:728 */    if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0) {
/* 729:729 */      System.arraycopy(supp, from, a, from, len);
/* 730:730 */      return;
/* 731:    */    }
/* 732:    */    
/* 734:734 */    int i = from;int p = from; for (int q = mid; i < to; i++) {
/* 735:735 */      if ((q >= to) || ((p < mid) && (comp.compare(supp[p], supp[q]) <= 0))) a[i] = supp[(p++)]; else {
/* 736:736 */        a[i] = supp[(q++)];
/* 737:    */      }
/* 738:    */    }
/* 739:    */  }
/* 740:    */  
/* 750:    */  public static <K> void mergeSort(K[] a, int from, int to, Comparator<K> comp)
/* 751:    */  {
/* 752:752 */    mergeSort(a, from, to, comp, (Object[])a.clone());
/* 753:    */  }
/* 754:    */  
/* 763:    */  public static <K> void mergeSort(K[] a, Comparator<K> comp)
/* 764:    */  {
/* 765:765 */    mergeSort(a, 0, a.length, comp);
/* 766:    */  }
/* 767:    */  
/* 780:    */  public static <K> int binarySearch(K[] a, int from, int to, K key)
/* 781:    */  {
/* 782:    */    
/* 783:    */    
/* 794:794 */    while (from <= to) {
/* 795:795 */      int mid = from + to >>> 1;
/* 796:796 */      K midVal = a[mid];
/* 797:    */      
/* 802:802 */      int cmp = ((Comparable)midVal).compareTo(key);
/* 803:803 */      if (cmp < 0) { from = mid + 1;
/* 804:804 */      } else if (cmp > 0) to = mid - 1; else {
/* 805:805 */        return mid;
/* 806:    */      }
/* 807:    */    }
/* 808:808 */    return -(from + 1);
/* 809:    */  }
/* 810:    */  
/* 828:    */  public static <K> int binarySearch(K[] a, K key)
/* 829:    */  {
/* 830:830 */    return binarySearch(a, 0, a.length, key);
/* 831:    */  }
/* 832:    */  
/* 844:    */  public static <K> int binarySearch(K[] a, int from, int to, K key, Comparator<K> c)
/* 845:    */  {
/* 846:    */    
/* 847:    */    
/* 857:857 */    while (from <= to) {
/* 858:858 */      int mid = from + to >>> 1;
/* 859:859 */      K midVal = a[mid];
/* 860:860 */      int cmp = c.compare(midVal, key);
/* 861:861 */      if (cmp < 0) { from = mid + 1;
/* 862:862 */      } else if (cmp > 0) to = mid - 1; else
/* 863:863 */        return mid;
/* 864:    */    }
/* 865:865 */    return -(from + 1);
/* 866:    */  }
/* 867:    */  
/* 886:    */  public static <K> int binarySearch(K[] a, K key, Comparator<K> c)
/* 887:    */  {
/* 888:888 */    return binarySearch(a, 0, a.length, key, c);
/* 889:    */  }
/* 890:    */  
/* 897:    */  public static <K> K[] shuffle(K[] a, int from, int to, Random random)
/* 898:    */  {
/* 899:899 */    for (int i = to - from; i-- != 0;) {
/* 900:900 */      int p = random.nextInt(i + 1);
/* 901:901 */      K t = a[(from + i)];
/* 902:902 */      a[(from + i)] = a[(from + p)];
/* 903:903 */      a[(from + p)] = t;
/* 904:    */    }
/* 905:905 */    return a;
/* 906:    */  }
/* 907:    */  
/* 912:    */  public static <K> K[] shuffle(K[] a, Random random)
/* 913:    */  {
/* 914:914 */    for (int i = a.length; i-- != 0;) {
/* 915:915 */      int p = random.nextInt(i + 1);
/* 916:916 */      K t = a[i];
/* 917:917 */      a[i] = a[p];
/* 918:918 */      a[p] = t;
/* 919:    */    }
/* 920:920 */    return a;
/* 921:    */  }
/* 922:    */  
/* 926:    */  public static <K> K[] reverse(K[] a)
/* 927:    */  {
/* 928:928 */    int length = a.length;
/* 929:929 */    for (int i = length / 2; i-- != 0;) {
/* 930:930 */      K t = a[(length - i - 1)];
/* 931:931 */      a[(length - i - 1)] = a[i];
/* 932:932 */      a[i] = t;
/* 933:    */    }
/* 934:934 */    return a;
/* 935:    */  }
/* 936:    */  
/* 937:    */  private static final class ArrayHashStrategy<K> implements Hash.Strategy<K[]>, Serializable {
/* 938:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 939:    */    
/* 940:940 */    public int hashCode(K[] o) { return java.util.Arrays.hashCode(o); }
/* 941:    */    
/* 942:    */    public boolean equals(K[] a, K[] b) {
/* 943:943 */      return ObjectArrays.equals(a, b);
/* 944:    */    }
/* 945:    */  }
/* 946:    */  
/* 953:953 */  public static final Hash.Strategy HASH_STRATEGY = new ArrayHashStrategy(null);
/* 954:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */