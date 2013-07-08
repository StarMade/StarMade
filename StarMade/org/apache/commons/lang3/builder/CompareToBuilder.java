/*    1:     */package org.apache.commons.lang3.builder;
/*    2:     */
/*    3:     */import java.lang.reflect.AccessibleObject;
/*    4:     */import java.lang.reflect.Field;
/*    5:     */import java.lang.reflect.Modifier;
/*    6:     */import java.util.Collection;
/*    7:     */import java.util.Comparator;
/*    8:     */import org.apache.commons.lang3.ArrayUtils;
/*    9:     */
/*   97:     */public class CompareToBuilder
/*   98:     */  implements Builder<Integer>
/*   99:     */{
/*  100:     */  private int comparison;
/*  101:     */  
/*  102:     */  public CompareToBuilder()
/*  103:     */  {
/*  104: 104 */    this.comparison = 0;
/*  105:     */  }
/*  106:     */  
/*  133:     */  public static int reflectionCompare(Object lhs, Object rhs)
/*  134:     */  {
/*  135: 135 */    return reflectionCompare(lhs, rhs, false, null, new String[0]);
/*  136:     */  }
/*  137:     */  
/*  165:     */  public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients)
/*  166:     */  {
/*  167: 167 */    return reflectionCompare(lhs, rhs, compareTransients, null, new String[0]);
/*  168:     */  }
/*  169:     */  
/*  198:     */  public static int reflectionCompare(Object lhs, Object rhs, Collection<String> excludeFields)
/*  199:     */  {
/*  200: 200 */    return reflectionCompare(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*  201:     */  }
/*  202:     */  
/*  231:     */  public static int reflectionCompare(Object lhs, Object rhs, String... excludeFields)
/*  232:     */  {
/*  233: 233 */    return reflectionCompare(lhs, rhs, false, null, excludeFields);
/*  234:     */  }
/*  235:     */  
/*  273:     */  public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class<?> reflectUpToClass, String... excludeFields)
/*  274:     */  {
/*  275: 275 */    if (lhs == rhs) {
/*  276: 276 */      return 0;
/*  277:     */    }
/*  278: 278 */    if ((lhs == null) || (rhs == null)) {
/*  279: 279 */      throw new NullPointerException();
/*  280:     */    }
/*  281: 281 */    Class<?> lhsClazz = lhs.getClass();
/*  282: 282 */    if (!lhsClazz.isInstance(rhs)) {
/*  283: 283 */      throw new ClassCastException();
/*  284:     */    }
/*  285: 285 */    CompareToBuilder compareToBuilder = new CompareToBuilder();
/*  286: 286 */    reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*  287: 287 */    while ((lhsClazz.getSuperclass() != null) && (lhsClazz != reflectUpToClass)) {
/*  288: 288 */      lhsClazz = lhsClazz.getSuperclass();
/*  289: 289 */      reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*  290:     */    }
/*  291: 291 */    return compareToBuilder.toComparison();
/*  292:     */  }
/*  293:     */  
/*  311:     */  private static void reflectionAppend(Object lhs, Object rhs, Class<?> clazz, CompareToBuilder builder, boolean useTransients, String[] excludeFields)
/*  312:     */  {
/*  313: 313 */    Field[] fields = clazz.getDeclaredFields();
/*  314: 314 */    AccessibleObject.setAccessible(fields, true);
/*  315: 315 */    for (int i = 0; (i < fields.length) && (builder.comparison == 0); i++) {
/*  316: 316 */      Field f = fields[i];
/*  317: 317 */      if ((!ArrayUtils.contains(excludeFields, f.getName())) && (f.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(f.getModifiers()))) && (!Modifier.isStatic(f.getModifiers())))
/*  318:     */      {
/*  319:     */        try
/*  320:     */        {
/*  322: 322 */          builder.append(f.get(lhs), f.get(rhs));
/*  323:     */        }
/*  324:     */        catch (IllegalAccessException e)
/*  325:     */        {
/*  326: 326 */          throw new InternalError("Unexpected IllegalAccessException");
/*  327:     */        }
/*  328:     */      }
/*  329:     */    }
/*  330:     */  }
/*  331:     */  
/*  340:     */  public CompareToBuilder appendSuper(int superCompareTo)
/*  341:     */  {
/*  342: 342 */    if (this.comparison != 0) {
/*  343: 343 */      return this;
/*  344:     */    }
/*  345: 345 */    this.comparison = superCompareTo;
/*  346: 346 */    return this;
/*  347:     */  }
/*  348:     */  
/*  368:     */  public CompareToBuilder append(Object lhs, Object rhs)
/*  369:     */  {
/*  370: 370 */    return append(lhs, rhs, null);
/*  371:     */  }
/*  372:     */  
/*  397:     */  public CompareToBuilder append(Object lhs, Object rhs, Comparator<?> comparator)
/*  398:     */  {
/*  399: 399 */    if (this.comparison != 0) {
/*  400: 400 */      return this;
/*  401:     */    }
/*  402: 402 */    if (lhs == rhs) {
/*  403: 403 */      return this;
/*  404:     */    }
/*  405: 405 */    if (lhs == null) {
/*  406: 406 */      this.comparison = -1;
/*  407: 407 */      return this;
/*  408:     */    }
/*  409: 409 */    if (rhs == null) {
/*  410: 410 */      this.comparison = 1;
/*  411: 411 */      return this;
/*  412:     */    }
/*  413: 413 */    if (lhs.getClass().isArray())
/*  414:     */    {
/*  417: 417 */      if ((lhs instanceof long[])) {
/*  418: 418 */        append((long[])lhs, (long[])rhs);
/*  419: 419 */      } else if ((lhs instanceof int[])) {
/*  420: 420 */        append((int[])lhs, (int[])rhs);
/*  421: 421 */      } else if ((lhs instanceof short[])) {
/*  422: 422 */        append((short[])lhs, (short[])rhs);
/*  423: 423 */      } else if ((lhs instanceof char[])) {
/*  424: 424 */        append((char[])lhs, (char[])rhs);
/*  425: 425 */      } else if ((lhs instanceof byte[])) {
/*  426: 426 */        append((byte[])lhs, (byte[])rhs);
/*  427: 427 */      } else if ((lhs instanceof double[])) {
/*  428: 428 */        append((double[])lhs, (double[])rhs);
/*  429: 429 */      } else if ((lhs instanceof float[])) {
/*  430: 430 */        append((float[])lhs, (float[])rhs);
/*  431: 431 */      } else if ((lhs instanceof boolean[])) {
/*  432: 432 */        append((boolean[])lhs, (boolean[])rhs);
/*  433:     */      }
/*  434:     */      else
/*  435:     */      {
/*  436: 436 */        append((Object[])lhs, (Object[])rhs, comparator);
/*  437:     */      }
/*  438:     */      
/*  439:     */    }
/*  440: 440 */    else if (comparator == null)
/*  441:     */    {
/*  442: 442 */      Comparable<Object> comparable = (Comparable)lhs;
/*  443: 443 */      this.comparison = comparable.compareTo(rhs);
/*  444:     */    }
/*  445:     */    else {
/*  446: 446 */      Comparator<Object> comparator2 = comparator;
/*  447: 447 */      this.comparison = comparator2.compare(lhs, rhs);
/*  448:     */    }
/*  449:     */    
/*  450: 450 */    return this;
/*  451:     */  }
/*  452:     */  
/*  461:     */  public CompareToBuilder append(long lhs, long rhs)
/*  462:     */  {
/*  463: 463 */    if (this.comparison != 0) {
/*  464: 464 */      return this;
/*  465:     */    }
/*  466: 466 */    this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  467: 467 */    return this;
/*  468:     */  }
/*  469:     */  
/*  477:     */  public CompareToBuilder append(int lhs, int rhs)
/*  478:     */  {
/*  479: 479 */    if (this.comparison != 0) {
/*  480: 480 */      return this;
/*  481:     */    }
/*  482: 482 */    this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  483: 483 */    return this;
/*  484:     */  }
/*  485:     */  
/*  493:     */  public CompareToBuilder append(short lhs, short rhs)
/*  494:     */  {
/*  495: 495 */    if (this.comparison != 0) {
/*  496: 496 */      return this;
/*  497:     */    }
/*  498: 498 */    this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  499: 499 */    return this;
/*  500:     */  }
/*  501:     */  
/*  509:     */  public CompareToBuilder append(char lhs, char rhs)
/*  510:     */  {
/*  511: 511 */    if (this.comparison != 0) {
/*  512: 512 */      return this;
/*  513:     */    }
/*  514: 514 */    this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  515: 515 */    return this;
/*  516:     */  }
/*  517:     */  
/*  525:     */  public CompareToBuilder append(byte lhs, byte rhs)
/*  526:     */  {
/*  527: 527 */    if (this.comparison != 0) {
/*  528: 528 */      return this;
/*  529:     */    }
/*  530: 530 */    this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  531: 531 */    return this;
/*  532:     */  }
/*  533:     */  
/*  546:     */  public CompareToBuilder append(double lhs, double rhs)
/*  547:     */  {
/*  548: 548 */    if (this.comparison != 0) {
/*  549: 549 */      return this;
/*  550:     */    }
/*  551: 551 */    this.comparison = Double.compare(lhs, rhs);
/*  552: 552 */    return this;
/*  553:     */  }
/*  554:     */  
/*  567:     */  public CompareToBuilder append(float lhs, float rhs)
/*  568:     */  {
/*  569: 569 */    if (this.comparison != 0) {
/*  570: 570 */      return this;
/*  571:     */    }
/*  572: 572 */    this.comparison = Float.compare(lhs, rhs);
/*  573: 573 */    return this;
/*  574:     */  }
/*  575:     */  
/*  583:     */  public CompareToBuilder append(boolean lhs, boolean rhs)
/*  584:     */  {
/*  585: 585 */    if (this.comparison != 0) {
/*  586: 586 */      return this;
/*  587:     */    }
/*  588: 588 */    if (lhs == rhs) {
/*  589: 589 */      return this;
/*  590:     */    }
/*  591: 591 */    if (!lhs) {
/*  592: 592 */      this.comparison = -1;
/*  593:     */    } else {
/*  594: 594 */      this.comparison = 1;
/*  595:     */    }
/*  596: 596 */    return this;
/*  597:     */  }
/*  598:     */  
/*  619:     */  public CompareToBuilder append(Object[] lhs, Object[] rhs)
/*  620:     */  {
/*  621: 621 */    return append(lhs, rhs, null);
/*  622:     */  }
/*  623:     */  
/*  646:     */  public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator<?> comparator)
/*  647:     */  {
/*  648: 648 */    if (this.comparison != 0) {
/*  649: 649 */      return this;
/*  650:     */    }
/*  651: 651 */    if (lhs == rhs) {
/*  652: 652 */      return this;
/*  653:     */    }
/*  654: 654 */    if (lhs == null) {
/*  655: 655 */      this.comparison = -1;
/*  656: 656 */      return this;
/*  657:     */    }
/*  658: 658 */    if (rhs == null) {
/*  659: 659 */      this.comparison = 1;
/*  660: 660 */      return this;
/*  661:     */    }
/*  662: 662 */    if (lhs.length != rhs.length) {
/*  663: 663 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  664: 664 */      return this;
/*  665:     */    }
/*  666: 666 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  667: 667 */      append(lhs[i], rhs[i], comparator);
/*  668:     */    }
/*  669: 669 */    return this;
/*  670:     */  }
/*  671:     */  
/*  686:     */  public CompareToBuilder append(long[] lhs, long[] rhs)
/*  687:     */  {
/*  688: 688 */    if (this.comparison != 0) {
/*  689: 689 */      return this;
/*  690:     */    }
/*  691: 691 */    if (lhs == rhs) {
/*  692: 692 */      return this;
/*  693:     */    }
/*  694: 694 */    if (lhs == null) {
/*  695: 695 */      this.comparison = -1;
/*  696: 696 */      return this;
/*  697:     */    }
/*  698: 698 */    if (rhs == null) {
/*  699: 699 */      this.comparison = 1;
/*  700: 700 */      return this;
/*  701:     */    }
/*  702: 702 */    if (lhs.length != rhs.length) {
/*  703: 703 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  704: 704 */      return this;
/*  705:     */    }
/*  706: 706 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  707: 707 */      append(lhs[i], rhs[i]);
/*  708:     */    }
/*  709: 709 */    return this;
/*  710:     */  }
/*  711:     */  
/*  726:     */  public CompareToBuilder append(int[] lhs, int[] rhs)
/*  727:     */  {
/*  728: 728 */    if (this.comparison != 0) {
/*  729: 729 */      return this;
/*  730:     */    }
/*  731: 731 */    if (lhs == rhs) {
/*  732: 732 */      return this;
/*  733:     */    }
/*  734: 734 */    if (lhs == null) {
/*  735: 735 */      this.comparison = -1;
/*  736: 736 */      return this;
/*  737:     */    }
/*  738: 738 */    if (rhs == null) {
/*  739: 739 */      this.comparison = 1;
/*  740: 740 */      return this;
/*  741:     */    }
/*  742: 742 */    if (lhs.length != rhs.length) {
/*  743: 743 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  744: 744 */      return this;
/*  745:     */    }
/*  746: 746 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  747: 747 */      append(lhs[i], rhs[i]);
/*  748:     */    }
/*  749: 749 */    return this;
/*  750:     */  }
/*  751:     */  
/*  766:     */  public CompareToBuilder append(short[] lhs, short[] rhs)
/*  767:     */  {
/*  768: 768 */    if (this.comparison != 0) {
/*  769: 769 */      return this;
/*  770:     */    }
/*  771: 771 */    if (lhs == rhs) {
/*  772: 772 */      return this;
/*  773:     */    }
/*  774: 774 */    if (lhs == null) {
/*  775: 775 */      this.comparison = -1;
/*  776: 776 */      return this;
/*  777:     */    }
/*  778: 778 */    if (rhs == null) {
/*  779: 779 */      this.comparison = 1;
/*  780: 780 */      return this;
/*  781:     */    }
/*  782: 782 */    if (lhs.length != rhs.length) {
/*  783: 783 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  784: 784 */      return this;
/*  785:     */    }
/*  786: 786 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  787: 787 */      append(lhs[i], rhs[i]);
/*  788:     */    }
/*  789: 789 */    return this;
/*  790:     */  }
/*  791:     */  
/*  806:     */  public CompareToBuilder append(char[] lhs, char[] rhs)
/*  807:     */  {
/*  808: 808 */    if (this.comparison != 0) {
/*  809: 809 */      return this;
/*  810:     */    }
/*  811: 811 */    if (lhs == rhs) {
/*  812: 812 */      return this;
/*  813:     */    }
/*  814: 814 */    if (lhs == null) {
/*  815: 815 */      this.comparison = -1;
/*  816: 816 */      return this;
/*  817:     */    }
/*  818: 818 */    if (rhs == null) {
/*  819: 819 */      this.comparison = 1;
/*  820: 820 */      return this;
/*  821:     */    }
/*  822: 822 */    if (lhs.length != rhs.length) {
/*  823: 823 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  824: 824 */      return this;
/*  825:     */    }
/*  826: 826 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  827: 827 */      append(lhs[i], rhs[i]);
/*  828:     */    }
/*  829: 829 */    return this;
/*  830:     */  }
/*  831:     */  
/*  846:     */  public CompareToBuilder append(byte[] lhs, byte[] rhs)
/*  847:     */  {
/*  848: 848 */    if (this.comparison != 0) {
/*  849: 849 */      return this;
/*  850:     */    }
/*  851: 851 */    if (lhs == rhs) {
/*  852: 852 */      return this;
/*  853:     */    }
/*  854: 854 */    if (lhs == null) {
/*  855: 855 */      this.comparison = -1;
/*  856: 856 */      return this;
/*  857:     */    }
/*  858: 858 */    if (rhs == null) {
/*  859: 859 */      this.comparison = 1;
/*  860: 860 */      return this;
/*  861:     */    }
/*  862: 862 */    if (lhs.length != rhs.length) {
/*  863: 863 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  864: 864 */      return this;
/*  865:     */    }
/*  866: 866 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  867: 867 */      append(lhs[i], rhs[i]);
/*  868:     */    }
/*  869: 869 */    return this;
/*  870:     */  }
/*  871:     */  
/*  886:     */  public CompareToBuilder append(double[] lhs, double[] rhs)
/*  887:     */  {
/*  888: 888 */    if (this.comparison != 0) {
/*  889: 889 */      return this;
/*  890:     */    }
/*  891: 891 */    if (lhs == rhs) {
/*  892: 892 */      return this;
/*  893:     */    }
/*  894: 894 */    if (lhs == null) {
/*  895: 895 */      this.comparison = -1;
/*  896: 896 */      return this;
/*  897:     */    }
/*  898: 898 */    if (rhs == null) {
/*  899: 899 */      this.comparison = 1;
/*  900: 900 */      return this;
/*  901:     */    }
/*  902: 902 */    if (lhs.length != rhs.length) {
/*  903: 903 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  904: 904 */      return this;
/*  905:     */    }
/*  906: 906 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  907: 907 */      append(lhs[i], rhs[i]);
/*  908:     */    }
/*  909: 909 */    return this;
/*  910:     */  }
/*  911:     */  
/*  926:     */  public CompareToBuilder append(float[] lhs, float[] rhs)
/*  927:     */  {
/*  928: 928 */    if (this.comparison != 0) {
/*  929: 929 */      return this;
/*  930:     */    }
/*  931: 931 */    if (lhs == rhs) {
/*  932: 932 */      return this;
/*  933:     */    }
/*  934: 934 */    if (lhs == null) {
/*  935: 935 */      this.comparison = -1;
/*  936: 936 */      return this;
/*  937:     */    }
/*  938: 938 */    if (rhs == null) {
/*  939: 939 */      this.comparison = 1;
/*  940: 940 */      return this;
/*  941:     */    }
/*  942: 942 */    if (lhs.length != rhs.length) {
/*  943: 943 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  944: 944 */      return this;
/*  945:     */    }
/*  946: 946 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  947: 947 */      append(lhs[i], rhs[i]);
/*  948:     */    }
/*  949: 949 */    return this;
/*  950:     */  }
/*  951:     */  
/*  966:     */  public CompareToBuilder append(boolean[] lhs, boolean[] rhs)
/*  967:     */  {
/*  968: 968 */    if (this.comparison != 0) {
/*  969: 969 */      return this;
/*  970:     */    }
/*  971: 971 */    if (lhs == rhs) {
/*  972: 972 */      return this;
/*  973:     */    }
/*  974: 974 */    if (lhs == null) {
/*  975: 975 */      this.comparison = -1;
/*  976: 976 */      return this;
/*  977:     */    }
/*  978: 978 */    if (rhs == null) {
/*  979: 979 */      this.comparison = 1;
/*  980: 980 */      return this;
/*  981:     */    }
/*  982: 982 */    if (lhs.length != rhs.length) {
/*  983: 983 */      this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  984: 984 */      return this;
/*  985:     */    }
/*  986: 986 */    for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  987: 987 */      append(lhs[i], rhs[i]);
/*  988:     */    }
/*  989: 989 */    return this;
/*  990:     */  }
/*  991:     */  
/* 1001:     */  public int toComparison()
/* 1002:     */  {
/* 1003:1003 */    return this.comparison;
/* 1004:     */  }
/* 1005:     */  
/* 1015:     */  public Integer build()
/* 1016:     */  {
/* 1017:1017 */    return Integer.valueOf(toComparison());
/* 1018:     */  }
/* 1019:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.CompareToBuilder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */