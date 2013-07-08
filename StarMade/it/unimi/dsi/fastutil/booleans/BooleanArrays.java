/*   1:    */package it.unimi.dsi.fastutil.booleans;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.Hash.Strategy;
/*   4:    */import java.io.Serializable;
/*   5:    */import java.util.Random;
/*   6:    */
/*  84:    */public class BooleanArrays
/*  85:    */{
/*  86:    */  public static final long ONEOVERPHI = 106039L;
/*  87: 87 */  public static final boolean[] EMPTY_ARRAY = new boolean[0];
/*  88:    */  
/*  91:    */  private static final int SMALL = 7;
/*  92:    */  
/*  94:    */  private static final int MEDIUM = 50;
/*  95:    */  
/*  98:    */  public static boolean[] ensureCapacity(boolean[] array, int length)
/*  99:    */  {
/* 100:100 */    if (length > array.length) {
/* 101:101 */      boolean[] t = new boolean[length];
/* 102:    */      
/* 103:103 */      System.arraycopy(array, 0, t, 0, array.length);
/* 104:104 */      return t;
/* 105:    */    }
/* 106:106 */    return array;
/* 107:    */  }
/* 108:    */  
/* 116:    */  public static boolean[] ensureCapacity(boolean[] array, int length, int preserve)
/* 117:    */  {
/* 118:118 */    if (length > array.length) {
/* 119:119 */      boolean[] t = new boolean[length];
/* 120:    */      
/* 121:121 */      System.arraycopy(array, 0, t, 0, preserve);
/* 122:122 */      return t;
/* 123:    */    }
/* 124:124 */    return array;
/* 125:    */  }
/* 126:    */  
/* 140:    */  public static boolean[] grow(boolean[] array, int length)
/* 141:    */  {
/* 142:142 */    if (length > array.length) {
/* 143:143 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 144:144 */      boolean[] t = new boolean[newLength];
/* 145:    */      
/* 146:146 */      System.arraycopy(array, 0, t, 0, array.length);
/* 147:147 */      return t;
/* 148:    */    }
/* 149:149 */    return array;
/* 150:    */  }
/* 151:    */  
/* 166:    */  public static boolean[] grow(boolean[] array, int length, int preserve)
/* 167:    */  {
/* 168:168 */    if (length > array.length) {
/* 169:169 */      int newLength = (int)Math.min(Math.max(106039L * array.length >>> 16, length), 2147483647L);
/* 170:170 */      boolean[] t = new boolean[newLength];
/* 171:    */      
/* 172:172 */      System.arraycopy(array, 0, t, 0, preserve);
/* 173:173 */      return t;
/* 174:    */    }
/* 175:175 */    return array;
/* 176:    */  }
/* 177:    */  
/* 186:    */  public static boolean[] trim(boolean[] array, int length)
/* 187:    */  {
/* 188:188 */    if (length >= array.length) return array;
/* 189:189 */    boolean[] t = length == 0 ? EMPTY_ARRAY : new boolean[length];
/* 190:    */    
/* 191:191 */    System.arraycopy(array, 0, t, 0, length);
/* 192:192 */    return t;
/* 193:    */  }
/* 194:    */  
/* 206:    */  public static boolean[] setLength(boolean[] array, int length)
/* 207:    */  {
/* 208:208 */    if (length == array.length) return array;
/* 209:209 */    if (length < array.length) return trim(array, length);
/* 210:210 */    return ensureCapacity(array, length);
/* 211:    */  }
/* 212:    */  
/* 218:    */  public static boolean[] copy(boolean[] array, int offset, int length)
/* 219:    */  {
/* 220:220 */    ensureOffsetLength(array, offset, length);
/* 221:221 */    boolean[] a = length == 0 ? EMPTY_ARRAY : new boolean[length];
/* 222:    */    
/* 223:223 */    System.arraycopy(array, offset, a, 0, length);
/* 224:224 */    return a;
/* 225:    */  }
/* 226:    */  
/* 230:    */  public static boolean[] copy(boolean[] array)
/* 231:    */  {
/* 232:232 */    return (boolean[])array.clone();
/* 233:    */  }
/* 234:    */  
/* 241:    */  public static void fill(boolean[] array, boolean value)
/* 242:    */  {
/* 243:243 */    int i = array.length;
/* 244:244 */    while (i-- != 0) { array[i] = value;
/* 245:    */    }
/* 246:    */  }
/* 247:    */  
/* 256:    */  public static void fill(boolean[] array, int from, int to, boolean value)
/* 257:    */  {
/* 258:258 */    ensureFromTo(array, from, to);
/* 259:259 */    for (from != 0; to-- != 0; array[to] = value) {}
/* 260:260 */    for (int i = from; i < to; i++) { array[i] = value;
/* 261:    */    }
/* 262:    */  }
/* 263:    */  
/* 270:    */  public static boolean equals(boolean[] a1, boolean[] a2)
/* 271:    */  {
/* 272:272 */    int i = a1.length;
/* 273:273 */    if (i != a2.length) return false;
/* 274:274 */    while (i-- != 0) if (a1[i] != a2[i]) return false;
/* 275:275 */    return true;
/* 276:    */  }
/* 277:    */  
/* 286:    */  public static void ensureFromTo(boolean[] a, int from, int to)
/* 287:    */  {
/* 288:288 */    it.unimi.dsi.fastutil.Arrays.ensureFromTo(a.length, from, to);
/* 289:    */  }
/* 290:    */  
/* 299:    */  public static void ensureOffsetLength(boolean[] a, int offset, int length)
/* 300:    */  {
/* 301:301 */    it.unimi.dsi.fastutil.Arrays.ensureOffsetLength(a.length, offset, length);
/* 302:    */  }
/* 303:    */  
/* 304:    */  private static void swap(boolean[] x, int a, int b)
/* 305:    */  {
/* 306:306 */    boolean t = x[a];
/* 307:307 */    x[a] = x[b];
/* 308:308 */    x[b] = t;
/* 309:    */  }
/* 310:    */  
/* 311:311 */  private static void vecSwap(boolean[] x, int a, int b, int n) { for (int i = 0; i < n; b++) { swap(x, a, b);i++;a++;
/* 312:    */    } }
/* 313:    */  
/* 314:314 */  private static int med3(boolean[] x, int a, int b, int c, BooleanComparator comp) { int ab = comp.compare(x[a], x[b]);
/* 315:315 */    int ac = comp.compare(x[a], x[c]);
/* 316:316 */    int bc = comp.compare(x[b], x[c]);
/* 317:317 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 318:    */  }
/* 319:    */  
/* 320:    */  private static void selectionSort(boolean[] a, int from, int to, BooleanComparator comp)
/* 321:    */  {
/* 322:322 */    for (int i = from; i < to - 1; i++) {
/* 323:323 */      int m = i;
/* 324:324 */      for (int j = i + 1; j < to; j++) if (comp.compare(a[j], a[m]) < 0) m = j;
/* 325:325 */      if (m != i) {
/* 326:326 */        boolean u = a[i];
/* 327:327 */        a[i] = a[m];
/* 328:328 */        a[m] = u;
/* 329:    */      }
/* 330:    */    }
/* 331:    */  }
/* 332:    */  
/* 333:333 */  private static void insertionSort(boolean[] a, int from, int to, BooleanComparator comp) { int i = from; for (;;) { i++; if (i >= to) break;
/* 334:334 */      boolean t = a[i];
/* 335:335 */      int j = i;
/* 336:336 */      for (boolean u = a[(j - 1)]; comp.compare(t, u) < 0; u = a[(--j - 1)]) {
/* 337:337 */        a[j] = u;
/* 338:338 */        if (from == j - 1) {
/* 339:339 */          j--;
/* 340:340 */          break;
/* 341:    */        }
/* 342:    */      }
/* 343:343 */      a[j] = t;
/* 344:    */    }
/* 345:    */  }
/* 346:    */  
/* 347:    */  private static void selectionSort(boolean[] a, int from, int to) {
/* 348:348 */    for (int i = from; i < to - 1; i++) {
/* 349:349 */      int m = i;
/* 350:350 */      for (int j = i + 1; j < to; j++) if ((a[j] == 0) && (a[m] != 0)) m = j;
/* 351:351 */      if (m != i) {
/* 352:352 */        boolean u = a[i];
/* 353:353 */        a[i] = a[m];
/* 354:354 */        a[m] = u;
/* 355:    */      }
/* 356:    */    }
/* 357:    */  }
/* 358:    */  
/* 359:    */  private static void insertionSort(boolean[] a, int from, int to) {
/* 360:360 */    int i = from; for (;;) { i++; if (i >= to) break;
/* 361:361 */      boolean t = a[i];
/* 362:362 */      int j = i;
/* 363:363 */      for (boolean u = a[(j - 1)]; (!t) && (u); u = a[(--j - 1)]) {
/* 364:364 */        a[j] = u;
/* 365:365 */        if (from == j - 1) {
/* 366:366 */          j--;
/* 367:367 */          break;
/* 368:    */        }
/* 369:    */      }
/* 370:370 */      a[j] = t;
/* 371:    */    }
/* 372:    */  }
/* 373:    */  
/* 385:    */  public static void quickSort(boolean[] x, int from, int to, BooleanComparator comp)
/* 386:    */  {
/* 387:387 */    int len = to - from;
/* 388:    */    
/* 389:389 */    if (len < 7) {
/* 390:390 */      selectionSort(x, from, to, comp);
/* 391:391 */      return;
/* 392:    */    }
/* 393:    */    
/* 394:394 */    int m = from + len / 2;
/* 395:395 */    if (len > 7) {
/* 396:396 */      int l = from;
/* 397:397 */      int n = to - 1;
/* 398:398 */      if (len > 50) {
/* 399:399 */        int s = len / 8;
/* 400:400 */        l = med3(x, l, l + s, l + 2 * s, comp);
/* 401:401 */        m = med3(x, m - s, m, m + s, comp);
/* 402:402 */        n = med3(x, n - 2 * s, n - s, n, comp);
/* 403:    */      }
/* 404:404 */      m = med3(x, l, m, n, comp);
/* 405:    */    }
/* 406:406 */    boolean v = x[m];
/* 407:    */    
/* 408:408 */    int a = from;int b = a;int c = to - 1;int d = c;
/* 409:    */    for (;;) {
/* 410:    */      int comparison;
/* 411:411 */      if ((b <= c) && ((comparison = comp.compare(x[b], v)) <= 0)) {
/* 412:412 */        if (comparison == 0) swap(x, a++, b);
/* 413:413 */        b++;
/* 414:    */      } else { int comparison;
/* 415:415 */        while ((c >= b) && ((comparison = comp.compare(x[c], v)) >= 0)) {
/* 416:416 */          if (comparison == 0) swap(x, c, d--);
/* 417:417 */          c--;
/* 418:    */        }
/* 419:419 */        if (b > c) break;
/* 420:420 */        swap(x, b++, c--);
/* 421:    */      }
/* 422:    */    }
/* 423:423 */    int n = to;
/* 424:424 */    int s = Math.min(a - from, b - a);
/* 425:425 */    vecSwap(x, from, b - s, s);
/* 426:426 */    s = Math.min(d - c, n - d - 1);
/* 427:427 */    vecSwap(x, b, n - s, s);
/* 428:    */    
/* 429:429 */    if ((s = b - a) > 1) quickSort(x, from, from + s, comp);
/* 430:430 */    if ((s = d - c) > 1) { quickSort(x, n - s, n, comp);
/* 431:    */    }
/* 432:    */  }
/* 433:    */  
/* 442:    */  public static void quickSort(boolean[] x, BooleanComparator comp)
/* 443:    */  {
/* 444:444 */    quickSort(x, 0, x.length, comp);
/* 445:    */  }
/* 446:    */  
/* 447:    */  private static int med3(boolean[] x, int a, int b, int c) {
/* 448:448 */    int ab = x[a] == x[b] ? 0 : (x[a] == 0) && (x[b] != 0) ? -1 : 1;
/* 449:449 */    int ac = x[a] == x[c] ? 0 : (x[a] == 0) && (x[c] != 0) ? -1 : 1;
/* 450:450 */    int bc = x[b] == x[c] ? 0 : (x[b] == 0) && (x[c] != 0) ? -1 : 1;
/* 451:451 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 452:    */  }
/* 453:    */  
/* 466:    */  public static void quickSort(boolean[] x, int from, int to)
/* 467:    */  {
/* 468:468 */    int len = to - from;
/* 469:    */    
/* 470:470 */    if (len < 7) {
/* 471:471 */      selectionSort(x, from, to);
/* 472:472 */      return;
/* 473:    */    }
/* 474:    */    
/* 475:475 */    int m = from + len / 2;
/* 476:476 */    if (len > 7) {
/* 477:477 */      int l = from;
/* 478:478 */      int n = to - 1;
/* 479:479 */      if (len > 50) {
/* 480:480 */        int s = len / 8;
/* 481:481 */        l = med3(x, l, l + s, l + 2 * s);
/* 482:482 */        m = med3(x, m - s, m, m + s);
/* 483:483 */        n = med3(x, n - 2 * s, n - s, n);
/* 484:    */      }
/* 485:485 */      m = med3(x, l, m, n);
/* 486:    */    }
/* 487:487 */    boolean v = x[m];
/* 488:    */    
/* 489:489 */    int a = from;int b = a;int c = to - 1;int d = c;
/* 490:    */    for (;;)
/* 491:    */    {
/* 492:492 */      if (b <= c) { int comparison; if ((comparison = x[b] == v ? 0 : (x[b] == 0) && (v) ? -1 : 1) <= 0) {
/* 493:493 */          if (comparison == 0) swap(x, a++, b);
/* 494:494 */          b++;
/* 495:    */        }
/* 496:496 */      } else { while (c >= b) { int comparison; if ((comparison = x[c] == v ? 0 : (x[c] == 0) && (v) ? -1 : 1) < 0) break;
/* 497:497 */          if (comparison == 0) swap(x, c, d--);
/* 498:498 */          c--;
/* 499:    */        }
/* 500:500 */        if (b > c) break;
/* 501:501 */        swap(x, b++, c--);
/* 502:    */      }
/* 503:    */    }
/* 504:504 */    int n = to;
/* 505:505 */    int s = Math.min(a - from, b - a);
/* 506:506 */    vecSwap(x, from, b - s, s);
/* 507:507 */    s = Math.min(d - c, n - d - 1);
/* 508:508 */    vecSwap(x, b, n - s, s);
/* 509:    */    
/* 510:510 */    if ((s = b - a) > 1) quickSort(x, from, from + s);
/* 511:511 */    if ((s = d - c) > 1) { quickSort(x, n - s, n);
/* 512:    */    }
/* 513:    */  }
/* 514:    */  
/* 521:    */  public static void quickSort(boolean[] x)
/* 522:    */  {
/* 523:523 */    quickSort(x, 0, x.length);
/* 524:    */  }
/* 525:    */  
/* 535:    */  public static void mergeSort(boolean[] a, int from, int to, boolean[] supp)
/* 536:    */  {
/* 537:537 */    int len = to - from;
/* 538:    */    
/* 539:539 */    if (len < 7) {
/* 540:540 */      insertionSort(a, from, to);
/* 541:541 */      return;
/* 542:    */    }
/* 543:    */    
/* 544:544 */    int mid = from + to >>> 1;
/* 545:545 */    mergeSort(supp, from, mid, a);
/* 546:546 */    mergeSort(supp, mid, to, a);
/* 547:    */    
/* 549:549 */    if ((supp[(mid - 1)] == 0) || (supp[mid] != 0)) {
/* 550:550 */      System.arraycopy(supp, from, a, from, len);
/* 551:551 */      return;
/* 552:    */    }
/* 553:    */    
/* 554:554 */    int i = from;int p = from; for (int q = mid; i < to; i++) {
/* 555:555 */      if ((q >= to) || ((p < mid) && ((supp[p] == 0) || (supp[q] != 0)))) a[i] = supp[(p++)]; else {
/* 556:556 */        a[i] = supp[(q++)];
/* 557:    */      }
/* 558:    */    }
/* 559:    */  }
/* 560:    */  
/* 567:    */  public static void mergeSort(boolean[] a, int from, int to)
/* 568:    */  {
/* 569:569 */    mergeSort(a, from, to, (boolean[])a.clone());
/* 570:    */  }
/* 571:    */  
/* 577:    */  public static void mergeSort(boolean[] a)
/* 578:    */  {
/* 579:579 */    mergeSort(a, 0, a.length);
/* 580:    */  }
/* 581:    */  
/* 593:    */  public static void mergeSort(boolean[] a, int from, int to, BooleanComparator comp, boolean[] supp)
/* 594:    */  {
/* 595:595 */    int len = to - from;
/* 596:    */    
/* 597:597 */    if (len < 7) {
/* 598:598 */      insertionSort(a, from, to, comp);
/* 599:599 */      return;
/* 600:    */    }
/* 601:    */    
/* 602:602 */    int mid = from + to >>> 1;
/* 603:603 */    mergeSort(supp, from, mid, comp, a);
/* 604:604 */    mergeSort(supp, mid, to, comp, a);
/* 605:    */    
/* 607:607 */    if (comp.compare(supp[(mid - 1)], supp[mid]) <= 0) {
/* 608:608 */      System.arraycopy(supp, from, a, from, len);
/* 609:609 */      return;
/* 610:    */    }
/* 611:    */    
/* 612:612 */    int i = from;int p = from; for (int q = mid; i < to; i++) {
/* 613:613 */      if ((q >= to) || ((p < mid) && (comp.compare(supp[p], supp[q]) <= 0))) a[i] = supp[(p++)]; else {
/* 614:614 */        a[i] = supp[(q++)];
/* 615:    */      }
/* 616:    */    }
/* 617:    */  }
/* 618:    */  
/* 627:    */  public static void mergeSort(boolean[] a, int from, int to, BooleanComparator comp)
/* 628:    */  {
/* 629:629 */    mergeSort(a, from, to, comp, (boolean[])a.clone());
/* 630:    */  }
/* 631:    */  
/* 639:    */  public static void mergeSort(boolean[] a, BooleanComparator comp)
/* 640:    */  {
/* 641:641 */    mergeSort(a, 0, a.length, comp);
/* 642:    */  }
/* 643:    */  
/* 650:    */  public static boolean[] shuffle(boolean[] a, int from, int to, Random random)
/* 651:    */  {
/* 652:652 */    for (int i = to - from; i-- != 0;) {
/* 653:653 */      int p = random.nextInt(i + 1);
/* 654:654 */      boolean t = a[(from + i)];
/* 655:655 */      a[(from + i)] = a[(from + p)];
/* 656:656 */      a[(from + p)] = t;
/* 657:    */    }
/* 658:658 */    return a;
/* 659:    */  }
/* 660:    */  
/* 665:    */  public static boolean[] shuffle(boolean[] a, Random random)
/* 666:    */  {
/* 667:667 */    for (int i = a.length; i-- != 0;) {
/* 668:668 */      int p = random.nextInt(i + 1);
/* 669:669 */      boolean t = a[i];
/* 670:670 */      a[i] = a[p];
/* 671:671 */      a[p] = t;
/* 672:    */    }
/* 673:673 */    return a;
/* 674:    */  }
/* 675:    */  
/* 679:    */  public static boolean[] reverse(boolean[] a)
/* 680:    */  {
/* 681:681 */    int length = a.length;
/* 682:682 */    for (int i = length / 2; i-- != 0;) {
/* 683:683 */      boolean t = a[(length - i - 1)];
/* 684:684 */      a[(length - i - 1)] = a[i];
/* 685:685 */      a[i] = t;
/* 686:    */    }
/* 687:687 */    return a;
/* 688:    */  }
/* 689:    */  
/* 690:    */  private static final class ArrayHashStrategy implements Hash.Strategy<boolean[]>, Serializable {
/* 691:    */    public static final long serialVersionUID = -7046029254386353129L;
/* 692:    */    
/* 693:693 */    public int hashCode(boolean[] o) { return java.util.Arrays.hashCode(o); }
/* 694:    */    
/* 695:    */    public boolean equals(boolean[] a, boolean[] b) {
/* 696:696 */      return BooleanArrays.equals(a, b);
/* 697:    */    }
/* 698:    */  }
/* 699:    */  
/* 705:705 */  public static final Hash.Strategy<boolean[]> HASH_STRATEGY = new ArrayHashStrategy(null);
/* 706:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.booleans.BooleanArrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */