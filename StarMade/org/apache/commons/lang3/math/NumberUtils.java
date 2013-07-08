/*    1:     */package org.apache.commons.lang3.math;
/*    2:     */
/*    3:     */import java.math.BigDecimal;
/*    4:     */import java.math.BigInteger;
/*    5:     */import org.apache.commons.lang3.StringUtils;
/*    6:     */
/*   31:     */public class NumberUtils
/*   32:     */{
/*   33:  33 */  public static final Long LONG_ZERO = Long.valueOf(0L);
/*   34:     */  
/*   35:  35 */  public static final Long LONG_ONE = Long.valueOf(1L);
/*   36:     */  
/*   37:  37 */  public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
/*   38:     */  
/*   39:  39 */  public static final Integer INTEGER_ZERO = Integer.valueOf(0);
/*   40:     */  
/*   41:  41 */  public static final Integer INTEGER_ONE = Integer.valueOf(1);
/*   42:     */  
/*   43:  43 */  public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
/*   44:     */  
/*   45:  45 */  public static final Short SHORT_ZERO = Short.valueOf((short)0);
/*   46:     */  
/*   47:  47 */  public static final Short SHORT_ONE = Short.valueOf((short)1);
/*   48:     */  
/*   49:  49 */  public static final Short SHORT_MINUS_ONE = Short.valueOf((short)-1);
/*   50:     */  
/*   51:  51 */  public static final Byte BYTE_ZERO = Byte.valueOf((byte)0);
/*   52:     */  
/*   53:  53 */  public static final Byte BYTE_ONE = Byte.valueOf((byte)1);
/*   54:     */  
/*   55:  55 */  public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte)-1);
/*   56:     */  
/*   57:  57 */  public static final Double DOUBLE_ZERO = Double.valueOf(0.0D);
/*   58:     */  
/*   59:  59 */  public static final Double DOUBLE_ONE = Double.valueOf(1.0D);
/*   60:     */  
/*   61:  61 */  public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0D);
/*   62:     */  
/*   63:  63 */  public static final Float FLOAT_ZERO = Float.valueOf(0.0F);
/*   64:     */  
/*   65:  65 */  public static final Float FLOAT_ONE = Float.valueOf(1.0F);
/*   66:     */  
/*   67:  67 */  public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0F);
/*   68:     */  
/*   97:     */  public static int toInt(String str)
/*   98:     */  {
/*   99:  99 */    return toInt(str, 0);
/*  100:     */  }
/*  101:     */  
/*  118:     */  public static int toInt(String str, int defaultValue)
/*  119:     */  {
/*  120: 120 */    if (str == null) {
/*  121: 121 */      return defaultValue;
/*  122:     */    }
/*  123:     */    try {
/*  124: 124 */      return Integer.parseInt(str);
/*  125:     */    } catch (NumberFormatException nfe) {}
/*  126: 126 */    return defaultValue;
/*  127:     */  }
/*  128:     */  
/*  146:     */  public static long toLong(String str)
/*  147:     */  {
/*  148: 148 */    return toLong(str, 0L);
/*  149:     */  }
/*  150:     */  
/*  167:     */  public static long toLong(String str, long defaultValue)
/*  168:     */  {
/*  169: 169 */    if (str == null) {
/*  170: 170 */      return defaultValue;
/*  171:     */    }
/*  172:     */    try {
/*  173: 173 */      return Long.parseLong(str);
/*  174:     */    } catch (NumberFormatException nfe) {}
/*  175: 175 */    return defaultValue;
/*  176:     */  }
/*  177:     */  
/*  196:     */  public static float toFloat(String str)
/*  197:     */  {
/*  198: 198 */    return toFloat(str, 0.0F);
/*  199:     */  }
/*  200:     */  
/*  219:     */  public static float toFloat(String str, float defaultValue)
/*  220:     */  {
/*  221: 221 */    if (str == null) {
/*  222: 222 */      return defaultValue;
/*  223:     */    }
/*  224:     */    try {
/*  225: 225 */      return Float.parseFloat(str);
/*  226:     */    } catch (NumberFormatException nfe) {}
/*  227: 227 */    return defaultValue;
/*  228:     */  }
/*  229:     */  
/*  248:     */  public static double toDouble(String str)
/*  249:     */  {
/*  250: 250 */    return toDouble(str, 0.0D);
/*  251:     */  }
/*  252:     */  
/*  271:     */  public static double toDouble(String str, double defaultValue)
/*  272:     */  {
/*  273: 273 */    if (str == null) {
/*  274: 274 */      return defaultValue;
/*  275:     */    }
/*  276:     */    try {
/*  277: 277 */      return Double.parseDouble(str);
/*  278:     */    } catch (NumberFormatException nfe) {}
/*  279: 279 */    return defaultValue;
/*  280:     */  }
/*  281:     */  
/*  300:     */  public static byte toByte(String str)
/*  301:     */  {
/*  302: 302 */    return toByte(str, (byte)0);
/*  303:     */  }
/*  304:     */  
/*  321:     */  public static byte toByte(String str, byte defaultValue)
/*  322:     */  {
/*  323: 323 */    if (str == null) {
/*  324: 324 */      return defaultValue;
/*  325:     */    }
/*  326:     */    try {
/*  327: 327 */      return Byte.parseByte(str);
/*  328:     */    } catch (NumberFormatException nfe) {}
/*  329: 329 */    return defaultValue;
/*  330:     */  }
/*  331:     */  
/*  349:     */  public static short toShort(String str)
/*  350:     */  {
/*  351: 351 */    return toShort(str, (short)0);
/*  352:     */  }
/*  353:     */  
/*  370:     */  public static short toShort(String str, short defaultValue)
/*  371:     */  {
/*  372: 372 */    if (str == null) {
/*  373: 373 */      return defaultValue;
/*  374:     */    }
/*  375:     */    try {
/*  376: 376 */      return Short.parseShort(str);
/*  377:     */    } catch (NumberFormatException nfe) {}
/*  378: 378 */    return defaultValue;
/*  379:     */  }
/*  380:     */  
/*  442:     */  public static Number createNumber(String str)
/*  443:     */    throws NumberFormatException
/*  444:     */  {
/*  445: 445 */    if (str == null) {
/*  446: 446 */      return null;
/*  447:     */    }
/*  448: 448 */    if (StringUtils.isBlank(str)) {
/*  449: 449 */      throw new NumberFormatException("A blank string is not a valid number");
/*  450:     */    }
/*  451: 451 */    if (str.startsWith("--"))
/*  452:     */    {
/*  456: 456 */      return null;
/*  457:     */    }
/*  458: 458 */    if ((str.startsWith("0x")) || (str.startsWith("-0x")) || (str.startsWith("0X")) || (str.startsWith("-0X"))) {
/*  459: 459 */      return createInteger(str);
/*  460:     */    }
/*  461: 461 */    char lastChar = str.charAt(str.length() - 1);
/*  462:     */    
/*  465: 465 */    int decPos = str.indexOf('.');
/*  466: 466 */    int expPos = str.indexOf('e') + str.indexOf('E') + 1;
/*  467:     */    String mant;
/*  468: 468 */    String mant; String dec; if (decPos > -1) { String dec;
/*  469:     */      String dec;
/*  470: 470 */      if (expPos > -1) {
/*  471: 471 */        if ((expPos < decPos) || (expPos > str.length())) {
/*  472: 472 */          throw new NumberFormatException(str + " is not a valid number.");
/*  473:     */        }
/*  474: 474 */        dec = str.substring(decPos + 1, expPos);
/*  475:     */      } else {
/*  476: 476 */        dec = str.substring(decPos + 1);
/*  477:     */      }
/*  478: 478 */      mant = str.substring(0, decPos);
/*  479:     */    } else { String mant;
/*  480: 480 */      if (expPos > -1) {
/*  481: 481 */        if (expPos > str.length()) {
/*  482: 482 */          throw new NumberFormatException(str + " is not a valid number.");
/*  483:     */        }
/*  484: 484 */        mant = str.substring(0, expPos);
/*  485:     */      } else {
/*  486: 486 */        mant = str;
/*  487:     */      }
/*  488: 488 */      dec = null;
/*  489:     */    }
/*  490: 490 */    if ((!Character.isDigit(lastChar)) && (lastChar != '.')) { String exp;
/*  491: 491 */      String exp; if ((expPos > -1) && (expPos < str.length() - 1)) {
/*  492: 492 */        exp = str.substring(expPos + 1, str.length() - 1);
/*  493:     */      } else {
/*  494: 494 */        exp = null;
/*  495:     */      }
/*  496:     */      
/*  497: 497 */      String numeric = str.substring(0, str.length() - 1);
/*  498: 498 */      boolean allZeros = (isAllZeros(mant)) && (isAllZeros(exp));
/*  499: 499 */      switch (lastChar) {
/*  500:     */      case 'L': 
/*  501:     */      case 'l': 
/*  502: 502 */        if ((dec == null) && (exp == null) && (((numeric.charAt(0) == '-') && (isDigits(numeric.substring(1)))) || (isDigits(numeric))))
/*  503:     */        {
/*  504:     */          try
/*  505:     */          {
/*  506: 506 */            return createLong(numeric);
/*  507:     */          }
/*  508:     */          catch (NumberFormatException nfe)
/*  509:     */          {
/*  510: 510 */            return createBigInteger(numeric);
/*  511:     */          }
/*  512:     */        }
/*  513: 513 */        throw new NumberFormatException(str + " is not a valid number.");
/*  514:     */      case 'F': 
/*  515:     */      case 'f': 
/*  516:     */        try {
/*  517: 517 */          Float f = createFloat(numeric);
/*  518: 518 */          if ((!f.isInfinite()) && ((f.floatValue() != 0.0F) || (allZeros)))
/*  519:     */          {
/*  521: 521 */            return f;
/*  522:     */          }
/*  523:     */        }
/*  524:     */        catch (NumberFormatException nfe) {}
/*  525:     */      
/*  527:     */      case 'D': 
/*  528:     */      case 'd': 
/*  529:     */        try
/*  530:     */        {
/*  531: 531 */          Double d = createDouble(numeric);
/*  532: 532 */          if ((!d.isInfinite()) && ((d.floatValue() != 0.0D) || (allZeros))) {
/*  533: 533 */            return d;
/*  534:     */          }
/*  535:     */        }
/*  536:     */        catch (NumberFormatException nfe) {}
/*  537:     */        try
/*  538:     */        {
/*  539: 539 */          return createBigDecimal(numeric);
/*  540:     */        }
/*  541:     */        catch (NumberFormatException e) {}
/*  542:     */      }
/*  543:     */      
/*  544:     */      
/*  545: 545 */      throw new NumberFormatException(str + " is not a valid number.");
/*  546:     */    }
/*  547:     */    
/*  548:     */    String exp;
/*  549:     */    
/*  550:     */    String exp;
/*  551: 551 */    if ((expPos > -1) && (expPos < str.length() - 1)) {
/*  552: 552 */      exp = str.substring(expPos + 1, str.length());
/*  553:     */    } else {
/*  554: 554 */      exp = null;
/*  555:     */    }
/*  556: 556 */    if ((dec == null) && (exp == null)) {
/*  557:     */      try
/*  558:     */      {
/*  559: 559 */        return createInteger(str);
/*  560:     */      }
/*  561:     */      catch (NumberFormatException nfe)
/*  562:     */      {
/*  563:     */        try {
/*  564: 564 */          return createLong(str);
/*  565:     */        }
/*  566:     */        catch (NumberFormatException nfe)
/*  567:     */        {
/*  568: 568 */          return createBigInteger(str);
/*  569:     */        }
/*  570:     */      }
/*  571:     */    }
/*  572: 572 */    boolean allZeros = (isAllZeros(mant)) && (isAllZeros(exp));
/*  573:     */    try {
/*  574: 574 */      Float f = createFloat(str);
/*  575: 575 */      if ((!f.isInfinite()) && ((f.floatValue() != 0.0F) || (allZeros))) {
/*  576: 576 */        return f;
/*  577:     */      }
/*  578:     */    }
/*  579:     */    catch (NumberFormatException nfe) {}
/*  580:     */    try
/*  581:     */    {
/*  582: 582 */      Double d = createDouble(str);
/*  583: 583 */      if ((!d.isInfinite()) && ((d.doubleValue() != 0.0D) || (allZeros))) {
/*  584: 584 */        return d;
/*  585:     */      }
/*  586:     */    }
/*  587:     */    catch (NumberFormatException nfe) {}
/*  588:     */    
/*  590: 590 */    return createBigDecimal(str);
/*  591:     */  }
/*  592:     */  
/*  603:     */  private static boolean isAllZeros(String str)
/*  604:     */  {
/*  605: 605 */    if (str == null) {
/*  606: 606 */      return true;
/*  607:     */    }
/*  608: 608 */    for (int i = str.length() - 1; i >= 0; i--) {
/*  609: 609 */      if (str.charAt(i) != '0') {
/*  610: 610 */        return false;
/*  611:     */      }
/*  612:     */    }
/*  613: 613 */    return str.length() > 0;
/*  614:     */  }
/*  615:     */  
/*  625:     */  public static Float createFloat(String str)
/*  626:     */  {
/*  627: 627 */    if (str == null) {
/*  628: 628 */      return null;
/*  629:     */    }
/*  630: 630 */    return Float.valueOf(str);
/*  631:     */  }
/*  632:     */  
/*  641:     */  public static Double createDouble(String str)
/*  642:     */  {
/*  643: 643 */    if (str == null) {
/*  644: 644 */      return null;
/*  645:     */    }
/*  646: 646 */    return Double.valueOf(str);
/*  647:     */  }
/*  648:     */  
/*  658:     */  public static Integer createInteger(String str)
/*  659:     */  {
/*  660: 660 */    if (str == null) {
/*  661: 661 */      return null;
/*  662:     */    }
/*  663:     */    
/*  664: 664 */    return Integer.decode(str);
/*  665:     */  }
/*  666:     */  
/*  676:     */  public static Long createLong(String str)
/*  677:     */  {
/*  678: 678 */    if (str == null) {
/*  679: 679 */      return null;
/*  680:     */    }
/*  681: 681 */    return Long.decode(str);
/*  682:     */  }
/*  683:     */  
/*  692:     */  public static BigInteger createBigInteger(String str)
/*  693:     */  {
/*  694: 694 */    if (str == null) {
/*  695: 695 */      return null;
/*  696:     */    }
/*  697: 697 */    return new BigInteger(str);
/*  698:     */  }
/*  699:     */  
/*  708:     */  public static BigDecimal createBigDecimal(String str)
/*  709:     */  {
/*  710: 710 */    if (str == null) {
/*  711: 711 */      return null;
/*  712:     */    }
/*  713:     */    
/*  714: 714 */    if (StringUtils.isBlank(str)) {
/*  715: 715 */      throw new NumberFormatException("A blank string is not a valid number");
/*  716:     */    }
/*  717: 717 */    return new BigDecimal(str);
/*  718:     */  }
/*  719:     */  
/*  730:     */  public static long min(long[] array)
/*  731:     */  {
/*  732: 732 */    if (array == null)
/*  733: 733 */      throw new IllegalArgumentException("The Array must not be null");
/*  734: 734 */    if (array.length == 0) {
/*  735: 735 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  736:     */    }
/*  737:     */    
/*  739: 739 */    long min = array[0];
/*  740: 740 */    for (int i = 1; i < array.length; i++) {
/*  741: 741 */      if (array[i] < min) {
/*  742: 742 */        min = array[i];
/*  743:     */      }
/*  744:     */    }
/*  745:     */    
/*  746: 746 */    return min;
/*  747:     */  }
/*  748:     */  
/*  757:     */  public static int min(int[] array)
/*  758:     */  {
/*  759: 759 */    if (array == null)
/*  760: 760 */      throw new IllegalArgumentException("The Array must not be null");
/*  761: 761 */    if (array.length == 0) {
/*  762: 762 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  763:     */    }
/*  764:     */    
/*  766: 766 */    int min = array[0];
/*  767: 767 */    for (int j = 1; j < array.length; j++) {
/*  768: 768 */      if (array[j] < min) {
/*  769: 769 */        min = array[j];
/*  770:     */      }
/*  771:     */    }
/*  772:     */    
/*  773: 773 */    return min;
/*  774:     */  }
/*  775:     */  
/*  784:     */  public static short min(short[] array)
/*  785:     */  {
/*  786: 786 */    if (array == null)
/*  787: 787 */      throw new IllegalArgumentException("The Array must not be null");
/*  788: 788 */    if (array.length == 0) {
/*  789: 789 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  790:     */    }
/*  791:     */    
/*  793: 793 */    short min = array[0];
/*  794: 794 */    for (int i = 1; i < array.length; i++) {
/*  795: 795 */      if (array[i] < min) {
/*  796: 796 */        min = array[i];
/*  797:     */      }
/*  798:     */    }
/*  799:     */    
/*  800: 800 */    return min;
/*  801:     */  }
/*  802:     */  
/*  811:     */  public static byte min(byte[] array)
/*  812:     */  {
/*  813: 813 */    if (array == null)
/*  814: 814 */      throw new IllegalArgumentException("The Array must not be null");
/*  815: 815 */    if (array.length == 0) {
/*  816: 816 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  817:     */    }
/*  818:     */    
/*  820: 820 */    byte min = array[0];
/*  821: 821 */    for (int i = 1; i < array.length; i++) {
/*  822: 822 */      if (array[i] < min) {
/*  823: 823 */        min = array[i];
/*  824:     */      }
/*  825:     */    }
/*  826:     */    
/*  827: 827 */    return min;
/*  828:     */  }
/*  829:     */  
/*  839:     */  public static double min(double[] array)
/*  840:     */  {
/*  841: 841 */    if (array == null)
/*  842: 842 */      throw new IllegalArgumentException("The Array must not be null");
/*  843: 843 */    if (array.length == 0) {
/*  844: 844 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  845:     */    }
/*  846:     */    
/*  848: 848 */    double min = array[0];
/*  849: 849 */    for (int i = 1; i < array.length; i++) {
/*  850: 850 */      if (Double.isNaN(array[i])) {
/*  851: 851 */        return (0.0D / 0.0D);
/*  852:     */      }
/*  853: 853 */      if (array[i] < min) {
/*  854: 854 */        min = array[i];
/*  855:     */      }
/*  856:     */    }
/*  857:     */    
/*  858: 858 */    return min;
/*  859:     */  }
/*  860:     */  
/*  870:     */  public static float min(float[] array)
/*  871:     */  {
/*  872: 872 */    if (array == null)
/*  873: 873 */      throw new IllegalArgumentException("The Array must not be null");
/*  874: 874 */    if (array.length == 0) {
/*  875: 875 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  876:     */    }
/*  877:     */    
/*  879: 879 */    float min = array[0];
/*  880: 880 */    for (int i = 1; i < array.length; i++) {
/*  881: 881 */      if (Float.isNaN(array[i])) {
/*  882: 882 */        return (0.0F / 0.0F);
/*  883:     */      }
/*  884: 884 */      if (array[i] < min) {
/*  885: 885 */        min = array[i];
/*  886:     */      }
/*  887:     */    }
/*  888:     */    
/*  889: 889 */    return min;
/*  890:     */  }
/*  891:     */  
/*  902:     */  public static long max(long[] array)
/*  903:     */  {
/*  904: 904 */    if (array == null)
/*  905: 905 */      throw new IllegalArgumentException("The Array must not be null");
/*  906: 906 */    if (array.length == 0) {
/*  907: 907 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  908:     */    }
/*  909:     */    
/*  911: 911 */    long max = array[0];
/*  912: 912 */    for (int j = 1; j < array.length; j++) {
/*  913: 913 */      if (array[j] > max) {
/*  914: 914 */        max = array[j];
/*  915:     */      }
/*  916:     */    }
/*  917:     */    
/*  918: 918 */    return max;
/*  919:     */  }
/*  920:     */  
/*  929:     */  public static int max(int[] array)
/*  930:     */  {
/*  931: 931 */    if (array == null)
/*  932: 932 */      throw new IllegalArgumentException("The Array must not be null");
/*  933: 933 */    if (array.length == 0) {
/*  934: 934 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  935:     */    }
/*  936:     */    
/*  938: 938 */    int max = array[0];
/*  939: 939 */    for (int j = 1; j < array.length; j++) {
/*  940: 940 */      if (array[j] > max) {
/*  941: 941 */        max = array[j];
/*  942:     */      }
/*  943:     */    }
/*  944:     */    
/*  945: 945 */    return max;
/*  946:     */  }
/*  947:     */  
/*  956:     */  public static short max(short[] array)
/*  957:     */  {
/*  958: 958 */    if (array == null)
/*  959: 959 */      throw new IllegalArgumentException("The Array must not be null");
/*  960: 960 */    if (array.length == 0) {
/*  961: 961 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  962:     */    }
/*  963:     */    
/*  965: 965 */    short max = array[0];
/*  966: 966 */    for (int i = 1; i < array.length; i++) {
/*  967: 967 */      if (array[i] > max) {
/*  968: 968 */        max = array[i];
/*  969:     */      }
/*  970:     */    }
/*  971:     */    
/*  972: 972 */    return max;
/*  973:     */  }
/*  974:     */  
/*  983:     */  public static byte max(byte[] array)
/*  984:     */  {
/*  985: 985 */    if (array == null)
/*  986: 986 */      throw new IllegalArgumentException("The Array must not be null");
/*  987: 987 */    if (array.length == 0) {
/*  988: 988 */      throw new IllegalArgumentException("Array cannot be empty.");
/*  989:     */    }
/*  990:     */    
/*  992: 992 */    byte max = array[0];
/*  993: 993 */    for (int i = 1; i < array.length; i++) {
/*  994: 994 */      if (array[i] > max) {
/*  995: 995 */        max = array[i];
/*  996:     */      }
/*  997:     */    }
/*  998:     */    
/*  999: 999 */    return max;
/* 1000:     */  }
/* 1001:     */  
/* 1011:     */  public static double max(double[] array)
/* 1012:     */  {
/* 1013:1013 */    if (array == null)
/* 1014:1014 */      throw new IllegalArgumentException("The Array must not be null");
/* 1015:1015 */    if (array.length == 0) {
/* 1016:1016 */      throw new IllegalArgumentException("Array cannot be empty.");
/* 1017:     */    }
/* 1018:     */    
/* 1020:1020 */    double max = array[0];
/* 1021:1021 */    for (int j = 1; j < array.length; j++) {
/* 1022:1022 */      if (Double.isNaN(array[j])) {
/* 1023:1023 */        return (0.0D / 0.0D);
/* 1024:     */      }
/* 1025:1025 */      if (array[j] > max) {
/* 1026:1026 */        max = array[j];
/* 1027:     */      }
/* 1028:     */    }
/* 1029:     */    
/* 1030:1030 */    return max;
/* 1031:     */  }
/* 1032:     */  
/* 1042:     */  public static float max(float[] array)
/* 1043:     */  {
/* 1044:1044 */    if (array == null)
/* 1045:1045 */      throw new IllegalArgumentException("The Array must not be null");
/* 1046:1046 */    if (array.length == 0) {
/* 1047:1047 */      throw new IllegalArgumentException("Array cannot be empty.");
/* 1048:     */    }
/* 1049:     */    
/* 1051:1051 */    float max = array[0];
/* 1052:1052 */    for (int j = 1; j < array.length; j++) {
/* 1053:1053 */      if (Float.isNaN(array[j])) {
/* 1054:1054 */        return (0.0F / 0.0F);
/* 1055:     */      }
/* 1056:1056 */      if (array[j] > max) {
/* 1057:1057 */        max = array[j];
/* 1058:     */      }
/* 1059:     */    }
/* 1060:     */    
/* 1061:1061 */    return max;
/* 1062:     */  }
/* 1063:     */  
/* 1073:     */  public static long min(long a, long b, long c)
/* 1074:     */  {
/* 1075:1075 */    if (b < a) {
/* 1076:1076 */      a = b;
/* 1077:     */    }
/* 1078:1078 */    if (c < a) {
/* 1079:1079 */      a = c;
/* 1080:     */    }
/* 1081:1081 */    return a;
/* 1082:     */  }
/* 1083:     */  
/* 1091:     */  public static int min(int a, int b, int c)
/* 1092:     */  {
/* 1093:1093 */    if (b < a) {
/* 1094:1094 */      a = b;
/* 1095:     */    }
/* 1096:1096 */    if (c < a) {
/* 1097:1097 */      a = c;
/* 1098:     */    }
/* 1099:1099 */    return a;
/* 1100:     */  }
/* 1101:     */  
/* 1109:     */  public static short min(short a, short b, short c)
/* 1110:     */  {
/* 1111:1111 */    if (b < a) {
/* 1112:1112 */      a = b;
/* 1113:     */    }
/* 1114:1114 */    if (c < a) {
/* 1115:1115 */      a = c;
/* 1116:     */    }
/* 1117:1117 */    return a;
/* 1118:     */  }
/* 1119:     */  
/* 1127:     */  public static byte min(byte a, byte b, byte c)
/* 1128:     */  {
/* 1129:1129 */    if (b < a) {
/* 1130:1130 */      a = b;
/* 1131:     */    }
/* 1132:1132 */    if (c < a) {
/* 1133:1133 */      a = c;
/* 1134:     */    }
/* 1135:1135 */    return a;
/* 1136:     */  }
/* 1137:     */  
/* 1149:     */  public static double min(double a, double b, double c)
/* 1150:     */  {
/* 1151:1151 */    return Math.min(Math.min(a, b), c);
/* 1152:     */  }
/* 1153:     */  
/* 1165:     */  public static float min(float a, float b, float c)
/* 1166:     */  {
/* 1167:1167 */    return Math.min(Math.min(a, b), c);
/* 1168:     */  }
/* 1169:     */  
/* 1179:     */  public static long max(long a, long b, long c)
/* 1180:     */  {
/* 1181:1181 */    if (b > a) {
/* 1182:1182 */      a = b;
/* 1183:     */    }
/* 1184:1184 */    if (c > a) {
/* 1185:1185 */      a = c;
/* 1186:     */    }
/* 1187:1187 */    return a;
/* 1188:     */  }
/* 1189:     */  
/* 1197:     */  public static int max(int a, int b, int c)
/* 1198:     */  {
/* 1199:1199 */    if (b > a) {
/* 1200:1200 */      a = b;
/* 1201:     */    }
/* 1202:1202 */    if (c > a) {
/* 1203:1203 */      a = c;
/* 1204:     */    }
/* 1205:1205 */    return a;
/* 1206:     */  }
/* 1207:     */  
/* 1215:     */  public static short max(short a, short b, short c)
/* 1216:     */  {
/* 1217:1217 */    if (b > a) {
/* 1218:1218 */      a = b;
/* 1219:     */    }
/* 1220:1220 */    if (c > a) {
/* 1221:1221 */      a = c;
/* 1222:     */    }
/* 1223:1223 */    return a;
/* 1224:     */  }
/* 1225:     */  
/* 1233:     */  public static byte max(byte a, byte b, byte c)
/* 1234:     */  {
/* 1235:1235 */    if (b > a) {
/* 1236:1236 */      a = b;
/* 1237:     */    }
/* 1238:1238 */    if (c > a) {
/* 1239:1239 */      a = c;
/* 1240:     */    }
/* 1241:1241 */    return a;
/* 1242:     */  }
/* 1243:     */  
/* 1255:     */  public static double max(double a, double b, double c)
/* 1256:     */  {
/* 1257:1257 */    return Math.max(Math.max(a, b), c);
/* 1258:     */  }
/* 1259:     */  
/* 1271:     */  public static float max(float a, float b, float c)
/* 1272:     */  {
/* 1273:1273 */    return Math.max(Math.max(a, b), c);
/* 1274:     */  }
/* 1275:     */  
/* 1286:     */  public static boolean isDigits(String str)
/* 1287:     */  {
/* 1288:1288 */    if (StringUtils.isEmpty(str)) {
/* 1289:1289 */      return false;
/* 1290:     */    }
/* 1291:1291 */    for (int i = 0; i < str.length(); i++) {
/* 1292:1292 */      if (!Character.isDigit(str.charAt(i))) {
/* 1293:1293 */        return false;
/* 1294:     */      }
/* 1295:     */    }
/* 1296:1296 */    return true;
/* 1297:     */  }
/* 1298:     */  
/* 1311:     */  public static boolean isNumber(String str)
/* 1312:     */  {
/* 1313:1313 */    if (StringUtils.isEmpty(str)) {
/* 1314:1314 */      return false;
/* 1315:     */    }
/* 1316:1316 */    char[] chars = str.toCharArray();
/* 1317:1317 */    int sz = chars.length;
/* 1318:1318 */    boolean hasExp = false;
/* 1319:1319 */    boolean hasDecPoint = false;
/* 1320:1320 */    boolean allowSigns = false;
/* 1321:1321 */    boolean foundDigit = false;
/* 1322:     */    
/* 1323:1323 */    int start = chars[0] == '-' ? 1 : 0;
/* 1324:1324 */    if ((sz > start + 1) && (chars[start] == '0') && (chars[(start + 1)] == 'x')) {
/* 1325:1325 */      int i = start + 2;
/* 1326:1326 */      if (i == sz) {
/* 1327:1327 */        return false;
/* 1328:     */      }
/* 1329:1330 */      for (; 
/* 1330:1330 */          i < chars.length; i++) {
/* 1331:1331 */        if (((chars[i] < '0') || (chars[i] > '9')) && ((chars[i] < 'a') || (chars[i] > 'f')) && ((chars[i] < 'A') || (chars[i] > 'F')))
/* 1332:     */        {
/* 1334:1334 */          return false;
/* 1335:     */        }
/* 1336:     */      }
/* 1337:1337 */      return true;
/* 1338:     */    }
/* 1339:1339 */    sz--;
/* 1340:     */    
/* 1341:1341 */    int i = start;
/* 1342:     */    
/* 1344:1344 */    while ((i < sz) || ((i < sz + 1) && (allowSigns) && (!foundDigit))) {
/* 1345:1345 */      if ((chars[i] >= '0') && (chars[i] <= '9')) {
/* 1346:1346 */        foundDigit = true;
/* 1347:1347 */        allowSigns = false;
/* 1348:     */      }
/* 1349:1349 */      else if (chars[i] == '.') {
/* 1350:1350 */        if ((hasDecPoint) || (hasExp))
/* 1351:     */        {
/* 1352:1352 */          return false;
/* 1353:     */        }
/* 1354:1354 */        hasDecPoint = true;
/* 1355:1355 */      } else if ((chars[i] == 'e') || (chars[i] == 'E'))
/* 1356:     */      {
/* 1357:1357 */        if (hasExp)
/* 1358:     */        {
/* 1359:1359 */          return false;
/* 1360:     */        }
/* 1361:1361 */        if (!foundDigit) {
/* 1362:1362 */          return false;
/* 1363:     */        }
/* 1364:1364 */        hasExp = true;
/* 1365:1365 */        allowSigns = true;
/* 1366:1366 */      } else if ((chars[i] == '+') || (chars[i] == '-')) {
/* 1367:1367 */        if (!allowSigns) {
/* 1368:1368 */          return false;
/* 1369:     */        }
/* 1370:1370 */        allowSigns = false;
/* 1371:1371 */        foundDigit = false;
/* 1372:     */      } else {
/* 1373:1373 */        return false;
/* 1374:     */      }
/* 1375:1375 */      i++;
/* 1376:     */    }
/* 1377:1377 */    if (i < chars.length) {
/* 1378:1378 */      if ((chars[i] >= '0') && (chars[i] <= '9'))
/* 1379:     */      {
/* 1380:1380 */        return true;
/* 1381:     */      }
/* 1382:1382 */      if ((chars[i] == 'e') || (chars[i] == 'E'))
/* 1383:     */      {
/* 1384:1384 */        return false;
/* 1385:     */      }
/* 1386:1386 */      if (chars[i] == '.') {
/* 1387:1387 */        if ((hasDecPoint) || (hasExp))
/* 1388:     */        {
/* 1389:1389 */          return false;
/* 1390:     */        }
/* 1391:     */        
/* 1392:1392 */        return foundDigit;
/* 1393:     */      }
/* 1394:1394 */      if ((!allowSigns) && ((chars[i] == 'd') || (chars[i] == 'D') || (chars[i] == 'f') || (chars[i] == 'F')))
/* 1395:     */      {
/* 1399:1399 */        return foundDigit;
/* 1400:     */      }
/* 1401:1401 */      if ((chars[i] == 'l') || (chars[i] == 'L'))
/* 1402:     */      {
/* 1404:1404 */        return (foundDigit) && (!hasExp) && (!hasDecPoint);
/* 1405:     */      }
/* 1406:     */      
/* 1407:1407 */      return false;
/* 1408:     */    }
/* 1409:     */    
/* 1411:1411 */    return (!allowSigns) && (foundDigit);
/* 1412:     */  }
/* 1413:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.math.NumberUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */