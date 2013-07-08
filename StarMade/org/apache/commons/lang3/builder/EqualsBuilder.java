/*   1:    */package org.apache.commons.lang3.builder;
/*   2:    */
/*   3:    */import java.lang.reflect.AccessibleObject;
/*   4:    */import java.lang.reflect.Field;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import java.util.Collection;
/*   7:    */import java.util.HashSet;
/*   8:    */import java.util.Set;
/*   9:    */import org.apache.commons.lang3.ArrayUtils;
/*  10:    */import org.apache.commons.lang3.tuple.Pair;
/*  11:    */
/*  89:    */public class EqualsBuilder
/*  90:    */  implements Builder<Boolean>
/*  91:    */{
/*  92: 92 */  private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal();
/*  93:    */  
/* 119:    */  static Set<Pair<IDKey, IDKey>> getRegistry()
/* 120:    */  {
/* 121:121 */    return (Set)REGISTRY.get();
/* 122:    */  }
/* 123:    */  
/* 133:    */  static Pair<IDKey, IDKey> getRegisterPair(Object lhs, Object rhs)
/* 134:    */  {
/* 135:135 */    IDKey left = new IDKey(lhs);
/* 136:136 */    IDKey right = new IDKey(rhs);
/* 137:137 */    return Pair.of(left, right);
/* 138:    */  }
/* 139:    */  
/* 152:    */  static boolean isRegistered(Object lhs, Object rhs)
/* 153:    */  {
/* 154:154 */    Set<Pair<IDKey, IDKey>> registry = getRegistry();
/* 155:155 */    Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
/* 156:156 */    Pair<IDKey, IDKey> swappedPair = Pair.of(pair.getLeft(), pair.getRight());
/* 157:    */    
/* 158:158 */    return (registry != null) && ((registry.contains(pair)) || (registry.contains(swappedPair)));
/* 159:    */  }
/* 160:    */  
/* 170:    */  static void register(Object lhs, Object rhs)
/* 171:    */  {
/* 172:172 */    synchronized (EqualsBuilder.class) {
/* 173:173 */      if (getRegistry() == null) {
/* 174:174 */        REGISTRY.set(new HashSet());
/* 175:    */      }
/* 176:    */    }
/* 177:    */    
/* 178:178 */    Set<Pair<IDKey, IDKey>> registry = getRegistry();
/* 179:179 */    Object pair = getRegisterPair(lhs, rhs);
/* 180:180 */    registry.add(pair);
/* 181:    */  }
/* 182:    */  
/* 194:    */  static void unregister(Object lhs, Object rhs)
/* 195:    */  {
/* 196:196 */    Set<Pair<IDKey, IDKey>> registry = getRegistry();
/* 197:197 */    if (registry != null) {
/* 198:198 */      Pair<IDKey, IDKey> pair = getRegisterPair(lhs, rhs);
/* 199:199 */      registry.remove(pair);
/* 200:200 */      synchronized (EqualsBuilder.class)
/* 201:    */      {
/* 202:202 */        registry = getRegistry();
/* 203:203 */        if ((registry != null) && (registry.isEmpty())) {
/* 204:204 */          REGISTRY.remove();
/* 205:    */        }
/* 206:    */      }
/* 207:    */    }
/* 208:    */  }
/* 209:    */  
/* 214:214 */  private boolean isEquals = true;
/* 215:    */  
/* 246:    */  public static boolean reflectionEquals(Object lhs, Object rhs, Collection<String> excludeFields)
/* 247:    */  {
/* 248:248 */    return reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/* 249:    */  }
/* 250:    */  
/* 269:    */  public static boolean reflectionEquals(Object lhs, Object rhs, String... excludeFields)
/* 270:    */  {
/* 271:271 */    return reflectionEquals(lhs, rhs, false, null, excludeFields);
/* 272:    */  }
/* 273:    */  
/* 293:    */  public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients)
/* 294:    */  {
/* 295:295 */    return reflectionEquals(lhs, rhs, testTransients, null, new String[0]);
/* 296:    */  }
/* 297:    */  
/* 324:    */  public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, String... excludeFields)
/* 325:    */  {
/* 326:326 */    if (lhs == rhs) {
/* 327:327 */      return true;
/* 328:    */    }
/* 329:329 */    if ((lhs == null) || (rhs == null)) {
/* 330:330 */      return false;
/* 331:    */    }
/* 332:    */    
/* 336:336 */    Class<?> lhsClass = lhs.getClass();
/* 337:337 */    Class<?> rhsClass = rhs.getClass();
/* 338:    */    
/* 339:339 */    if (lhsClass.isInstance(rhs)) {
/* 340:340 */      Class<?> testClass = lhsClass;
/* 341:341 */      if (!rhsClass.isInstance(lhs))
/* 342:    */      {
/* 343:343 */        testClass = rhsClass;
/* 344:    */      }
/* 345:345 */    } else if (rhsClass.isInstance(lhs)) {
/* 346:346 */      Class<?> testClass = rhsClass;
/* 347:347 */      if (!lhsClass.isInstance(rhs))
/* 348:    */      {
/* 349:349 */        testClass = lhsClass;
/* 350:    */      }
/* 351:    */    }
/* 352:    */    else {
/* 353:353 */      return false; }
/* 354:    */    Class<?> testClass;
/* 355:355 */    EqualsBuilder equalsBuilder = new EqualsBuilder();
/* 356:    */    try {
/* 357:357 */      reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
/* 358:358 */      while ((testClass.getSuperclass() != null) && (testClass != reflectUpToClass)) {
/* 359:359 */        testClass = testClass.getSuperclass();
/* 360:360 */        reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
/* 361:    */      }
/* 362:    */      
/* 364:    */    }
/* 365:    */    catch (IllegalArgumentException e)
/* 366:    */    {
/* 368:368 */      return false;
/* 369:    */    }
/* 370:370 */    return equalsBuilder.isEquals();
/* 371:    */  }
/* 372:    */  
/* 390:    */  private static void reflectionAppend(Object lhs, Object rhs, Class<?> clazz, EqualsBuilder builder, boolean useTransients, String[] excludeFields)
/* 391:    */  {
/* 392:392 */    if (isRegistered(lhs, rhs)) {
/* 393:393 */      return;
/* 394:    */    }
/* 395:    */    try
/* 396:    */    {
/* 397:397 */      register(lhs, rhs);
/* 398:398 */      Field[] fields = clazz.getDeclaredFields();
/* 399:399 */      AccessibleObject.setAccessible(fields, true);
/* 400:400 */      for (int i = 0; (i < fields.length) && (builder.isEquals); i++) {
/* 401:401 */        Field f = fields[i];
/* 402:402 */        if ((!ArrayUtils.contains(excludeFields, f.getName())) && (f.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(f.getModifiers()))) && (!Modifier.isStatic(f.getModifiers())))
/* 403:    */        {
/* 404:    */          try
/* 405:    */          {
/* 407:407 */            builder.append(f.get(lhs), f.get(rhs));
/* 408:    */          }
/* 409:    */          catch (IllegalAccessException e)
/* 410:    */          {
/* 411:411 */            throw new InternalError("Unexpected IllegalAccessException");
/* 412:    */          }
/* 413:    */        }
/* 414:    */      }
/* 415:    */    } finally {
/* 416:416 */      unregister(lhs, rhs);
/* 417:    */    }
/* 418:    */  }
/* 419:    */  
/* 428:    */  public EqualsBuilder appendSuper(boolean superEquals)
/* 429:    */  {
/* 430:430 */    if (!this.isEquals) {
/* 431:431 */      return this;
/* 432:    */    }
/* 433:433 */    this.isEquals = superEquals;
/* 434:434 */    return this;
/* 435:    */  }
/* 436:    */  
/* 446:    */  public EqualsBuilder append(Object lhs, Object rhs)
/* 447:    */  {
/* 448:448 */    if (!this.isEquals) {
/* 449:449 */      return this;
/* 450:    */    }
/* 451:451 */    if (lhs == rhs) {
/* 452:452 */      return this;
/* 453:    */    }
/* 454:454 */    if ((lhs == null) || (rhs == null)) {
/* 455:455 */      setEquals(false);
/* 456:456 */      return this;
/* 457:    */    }
/* 458:458 */    Class<?> lhsClass = lhs.getClass();
/* 459:459 */    if (!lhsClass.isArray())
/* 460:    */    {
/* 461:461 */      this.isEquals = lhs.equals(rhs);
/* 462:462 */    } else if (lhs.getClass() != rhs.getClass())
/* 463:    */    {
/* 464:464 */      setEquals(false);
/* 467:    */    }
/* 468:468 */    else if ((lhs instanceof long[])) {
/* 469:469 */      append((long[])lhs, (long[])rhs);
/* 470:470 */    } else if ((lhs instanceof int[])) {
/* 471:471 */      append((int[])lhs, (int[])rhs);
/* 472:472 */    } else if ((lhs instanceof short[])) {
/* 473:473 */      append((short[])lhs, (short[])rhs);
/* 474:474 */    } else if ((lhs instanceof char[])) {
/* 475:475 */      append((char[])lhs, (char[])rhs);
/* 476:476 */    } else if ((lhs instanceof byte[])) {
/* 477:477 */      append((byte[])lhs, (byte[])rhs);
/* 478:478 */    } else if ((lhs instanceof double[])) {
/* 479:479 */      append((double[])lhs, (double[])rhs);
/* 480:480 */    } else if ((lhs instanceof float[])) {
/* 481:481 */      append((float[])lhs, (float[])rhs);
/* 482:482 */    } else if ((lhs instanceof boolean[])) {
/* 483:483 */      append((boolean[])lhs, (boolean[])rhs);
/* 484:    */    }
/* 485:    */    else {
/* 486:486 */      append((Object[])lhs, (Object[])rhs);
/* 487:    */    }
/* 488:488 */    return this;
/* 489:    */  }
/* 490:    */  
/* 501:    */  public EqualsBuilder append(long lhs, long rhs)
/* 502:    */  {
/* 503:503 */    if (!this.isEquals) {
/* 504:504 */      return this;
/* 505:    */    }
/* 506:506 */    this.isEquals = (lhs == rhs);
/* 507:507 */    return this;
/* 508:    */  }
/* 509:    */  
/* 516:    */  public EqualsBuilder append(int lhs, int rhs)
/* 517:    */  {
/* 518:518 */    if (!this.isEquals) {
/* 519:519 */      return this;
/* 520:    */    }
/* 521:521 */    this.isEquals = (lhs == rhs);
/* 522:522 */    return this;
/* 523:    */  }
/* 524:    */  
/* 531:    */  public EqualsBuilder append(short lhs, short rhs)
/* 532:    */  {
/* 533:533 */    if (!this.isEquals) {
/* 534:534 */      return this;
/* 535:    */    }
/* 536:536 */    this.isEquals = (lhs == rhs);
/* 537:537 */    return this;
/* 538:    */  }
/* 539:    */  
/* 546:    */  public EqualsBuilder append(char lhs, char rhs)
/* 547:    */  {
/* 548:548 */    if (!this.isEquals) {
/* 549:549 */      return this;
/* 550:    */    }
/* 551:551 */    this.isEquals = (lhs == rhs);
/* 552:552 */    return this;
/* 553:    */  }
/* 554:    */  
/* 561:    */  public EqualsBuilder append(byte lhs, byte rhs)
/* 562:    */  {
/* 563:563 */    if (!this.isEquals) {
/* 564:564 */      return this;
/* 565:    */    }
/* 566:566 */    this.isEquals = (lhs == rhs);
/* 567:567 */    return this;
/* 568:    */  }
/* 569:    */  
/* 582:    */  public EqualsBuilder append(double lhs, double rhs)
/* 583:    */  {
/* 584:584 */    if (!this.isEquals) {
/* 585:585 */      return this;
/* 586:    */    }
/* 587:587 */    return append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
/* 588:    */  }
/* 589:    */  
/* 602:    */  public EqualsBuilder append(float lhs, float rhs)
/* 603:    */  {
/* 604:604 */    if (!this.isEquals) {
/* 605:605 */      return this;
/* 606:    */    }
/* 607:607 */    return append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
/* 608:    */  }
/* 609:    */  
/* 616:    */  public EqualsBuilder append(boolean lhs, boolean rhs)
/* 617:    */  {
/* 618:618 */    if (!this.isEquals) {
/* 619:619 */      return this;
/* 620:    */    }
/* 621:621 */    this.isEquals = (lhs == rhs);
/* 622:622 */    return this;
/* 623:    */  }
/* 624:    */  
/* 634:    */  public EqualsBuilder append(Object[] lhs, Object[] rhs)
/* 635:    */  {
/* 636:636 */    if (!this.isEquals) {
/* 637:637 */      return this;
/* 638:    */    }
/* 639:639 */    if (lhs == rhs) {
/* 640:640 */      return this;
/* 641:    */    }
/* 642:642 */    if ((lhs == null) || (rhs == null)) {
/* 643:643 */      setEquals(false);
/* 644:644 */      return this;
/* 645:    */    }
/* 646:646 */    if (lhs.length != rhs.length) {
/* 647:647 */      setEquals(false);
/* 648:648 */      return this;
/* 649:    */    }
/* 650:650 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 651:651 */      append(lhs[i], rhs[i]);
/* 652:    */    }
/* 653:653 */    return this;
/* 654:    */  }
/* 655:    */  
/* 665:    */  public EqualsBuilder append(long[] lhs, long[] rhs)
/* 666:    */  {
/* 667:667 */    if (!this.isEquals) {
/* 668:668 */      return this;
/* 669:    */    }
/* 670:670 */    if (lhs == rhs) {
/* 671:671 */      return this;
/* 672:    */    }
/* 673:673 */    if ((lhs == null) || (rhs == null)) {
/* 674:674 */      setEquals(false);
/* 675:675 */      return this;
/* 676:    */    }
/* 677:677 */    if (lhs.length != rhs.length) {
/* 678:678 */      setEquals(false);
/* 679:679 */      return this;
/* 680:    */    }
/* 681:681 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 682:682 */      append(lhs[i], rhs[i]);
/* 683:    */    }
/* 684:684 */    return this;
/* 685:    */  }
/* 686:    */  
/* 696:    */  public EqualsBuilder append(int[] lhs, int[] rhs)
/* 697:    */  {
/* 698:698 */    if (!this.isEquals) {
/* 699:699 */      return this;
/* 700:    */    }
/* 701:701 */    if (lhs == rhs) {
/* 702:702 */      return this;
/* 703:    */    }
/* 704:704 */    if ((lhs == null) || (rhs == null)) {
/* 705:705 */      setEquals(false);
/* 706:706 */      return this;
/* 707:    */    }
/* 708:708 */    if (lhs.length != rhs.length) {
/* 709:709 */      setEquals(false);
/* 710:710 */      return this;
/* 711:    */    }
/* 712:712 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 713:713 */      append(lhs[i], rhs[i]);
/* 714:    */    }
/* 715:715 */    return this;
/* 716:    */  }
/* 717:    */  
/* 727:    */  public EqualsBuilder append(short[] lhs, short[] rhs)
/* 728:    */  {
/* 729:729 */    if (!this.isEquals) {
/* 730:730 */      return this;
/* 731:    */    }
/* 732:732 */    if (lhs == rhs) {
/* 733:733 */      return this;
/* 734:    */    }
/* 735:735 */    if ((lhs == null) || (rhs == null)) {
/* 736:736 */      setEquals(false);
/* 737:737 */      return this;
/* 738:    */    }
/* 739:739 */    if (lhs.length != rhs.length) {
/* 740:740 */      setEquals(false);
/* 741:741 */      return this;
/* 742:    */    }
/* 743:743 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 744:744 */      append(lhs[i], rhs[i]);
/* 745:    */    }
/* 746:746 */    return this;
/* 747:    */  }
/* 748:    */  
/* 758:    */  public EqualsBuilder append(char[] lhs, char[] rhs)
/* 759:    */  {
/* 760:760 */    if (!this.isEquals) {
/* 761:761 */      return this;
/* 762:    */    }
/* 763:763 */    if (lhs == rhs) {
/* 764:764 */      return this;
/* 765:    */    }
/* 766:766 */    if ((lhs == null) || (rhs == null)) {
/* 767:767 */      setEquals(false);
/* 768:768 */      return this;
/* 769:    */    }
/* 770:770 */    if (lhs.length != rhs.length) {
/* 771:771 */      setEquals(false);
/* 772:772 */      return this;
/* 773:    */    }
/* 774:774 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 775:775 */      append(lhs[i], rhs[i]);
/* 776:    */    }
/* 777:777 */    return this;
/* 778:    */  }
/* 779:    */  
/* 789:    */  public EqualsBuilder append(byte[] lhs, byte[] rhs)
/* 790:    */  {
/* 791:791 */    if (!this.isEquals) {
/* 792:792 */      return this;
/* 793:    */    }
/* 794:794 */    if (lhs == rhs) {
/* 795:795 */      return this;
/* 796:    */    }
/* 797:797 */    if ((lhs == null) || (rhs == null)) {
/* 798:798 */      setEquals(false);
/* 799:799 */      return this;
/* 800:    */    }
/* 801:801 */    if (lhs.length != rhs.length) {
/* 802:802 */      setEquals(false);
/* 803:803 */      return this;
/* 804:    */    }
/* 805:805 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 806:806 */      append(lhs[i], rhs[i]);
/* 807:    */    }
/* 808:808 */    return this;
/* 809:    */  }
/* 810:    */  
/* 820:    */  public EqualsBuilder append(double[] lhs, double[] rhs)
/* 821:    */  {
/* 822:822 */    if (!this.isEquals) {
/* 823:823 */      return this;
/* 824:    */    }
/* 825:825 */    if (lhs == rhs) {
/* 826:826 */      return this;
/* 827:    */    }
/* 828:828 */    if ((lhs == null) || (rhs == null)) {
/* 829:829 */      setEquals(false);
/* 830:830 */      return this;
/* 831:    */    }
/* 832:832 */    if (lhs.length != rhs.length) {
/* 833:833 */      setEquals(false);
/* 834:834 */      return this;
/* 835:    */    }
/* 836:836 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 837:837 */      append(lhs[i], rhs[i]);
/* 838:    */    }
/* 839:839 */    return this;
/* 840:    */  }
/* 841:    */  
/* 851:    */  public EqualsBuilder append(float[] lhs, float[] rhs)
/* 852:    */  {
/* 853:853 */    if (!this.isEquals) {
/* 854:854 */      return this;
/* 855:    */    }
/* 856:856 */    if (lhs == rhs) {
/* 857:857 */      return this;
/* 858:    */    }
/* 859:859 */    if ((lhs == null) || (rhs == null)) {
/* 860:860 */      setEquals(false);
/* 861:861 */      return this;
/* 862:    */    }
/* 863:863 */    if (lhs.length != rhs.length) {
/* 864:864 */      setEquals(false);
/* 865:865 */      return this;
/* 866:    */    }
/* 867:867 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 868:868 */      append(lhs[i], rhs[i]);
/* 869:    */    }
/* 870:870 */    return this;
/* 871:    */  }
/* 872:    */  
/* 882:    */  public EqualsBuilder append(boolean[] lhs, boolean[] rhs)
/* 883:    */  {
/* 884:884 */    if (!this.isEquals) {
/* 885:885 */      return this;
/* 886:    */    }
/* 887:887 */    if (lhs == rhs) {
/* 888:888 */      return this;
/* 889:    */    }
/* 890:890 */    if ((lhs == null) || (rhs == null)) {
/* 891:891 */      setEquals(false);
/* 892:892 */      return this;
/* 893:    */    }
/* 894:894 */    if (lhs.length != rhs.length) {
/* 895:895 */      setEquals(false);
/* 896:896 */      return this;
/* 897:    */    }
/* 898:898 */    for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 899:899 */      append(lhs[i], rhs[i]);
/* 900:    */    }
/* 901:901 */    return this;
/* 902:    */  }
/* 903:    */  
/* 909:    */  public boolean isEquals()
/* 910:    */  {
/* 911:911 */    return this.isEquals;
/* 912:    */  }
/* 913:    */  
/* 922:    */  public Boolean build()
/* 923:    */  {
/* 924:924 */    return Boolean.valueOf(isEquals());
/* 925:    */  }
/* 926:    */  
/* 932:    */  protected void setEquals(boolean isEquals)
/* 933:    */  {
/* 934:934 */    this.isEquals = isEquals;
/* 935:    */  }
/* 936:    */  
/* 940:    */  public void reset()
/* 941:    */  {
/* 942:942 */    this.isEquals = true;
/* 943:    */  }
/* 944:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.EqualsBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */