/*    1:     */package org.apache.commons.lang3;
/*    2:     */
/*    3:     */import org.apache.commons.lang3.math.NumberUtils;
/*    4:     */
/*   60:     */public class BooleanUtils
/*   61:     */{
/*   62:     */  public static Boolean negate(Boolean bool)
/*   63:     */  {
/*   64:  64 */    if (bool == null) {
/*   65:  65 */      return null;
/*   66:     */    }
/*   67:  67 */    return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/*   68:     */  }
/*   69:     */  
/*   85:     */  public static boolean isTrue(Boolean bool)
/*   86:     */  {
/*   87:  87 */    return Boolean.TRUE.equals(bool);
/*   88:     */  }
/*   89:     */  
/*  103:     */  public static boolean isNotTrue(Boolean bool)
/*  104:     */  {
/*  105: 105 */    return !isTrue(bool);
/*  106:     */  }
/*  107:     */  
/*  121:     */  public static boolean isFalse(Boolean bool)
/*  122:     */  {
/*  123: 123 */    return Boolean.FALSE.equals(bool);
/*  124:     */  }
/*  125:     */  
/*  139:     */  public static boolean isNotFalse(Boolean bool)
/*  140:     */  {
/*  141: 141 */    return !isFalse(bool);
/*  142:     */  }
/*  143:     */  
/*  157:     */  public static boolean toBoolean(Boolean bool)
/*  158:     */  {
/*  159: 159 */    return (bool != null) && (bool.booleanValue());
/*  160:     */  }
/*  161:     */  
/*  174:     */  public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull)
/*  175:     */  {
/*  176: 176 */    if (bool == null) {
/*  177: 177 */      return valueIfNull;
/*  178:     */    }
/*  179: 179 */    return bool.booleanValue();
/*  180:     */  }
/*  181:     */  
/*  197:     */  public static boolean toBoolean(int value)
/*  198:     */  {
/*  199: 199 */    return value != 0;
/*  200:     */  }
/*  201:     */  
/*  215:     */  public static Boolean toBooleanObject(int value)
/*  216:     */  {
/*  217: 217 */    return value == 0 ? Boolean.FALSE : Boolean.TRUE;
/*  218:     */  }
/*  219:     */  
/*  237:     */  public static Boolean toBooleanObject(Integer value)
/*  238:     */  {
/*  239: 239 */    if (value == null) {
/*  240: 240 */      return null;
/*  241:     */    }
/*  242: 242 */    return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
/*  243:     */  }
/*  244:     */  
/*  260:     */  public static boolean toBoolean(int value, int trueValue, int falseValue)
/*  261:     */  {
/*  262: 262 */    if (value == trueValue) {
/*  263: 263 */      return true;
/*  264:     */    }
/*  265: 265 */    if (value == falseValue) {
/*  266: 266 */      return false;
/*  267:     */    }
/*  268:     */    
/*  269: 269 */    throw new IllegalArgumentException("The Integer did not match either specified value");
/*  270:     */  }
/*  271:     */  
/*  288:     */  public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue)
/*  289:     */  {
/*  290: 290 */    if (value == null) {
/*  291: 291 */      if (trueValue == null) {
/*  292: 292 */        return true;
/*  293:     */      }
/*  294: 294 */      if (falseValue == null)
/*  295: 295 */        return false;
/*  296:     */    } else {
/*  297: 297 */      if (value.equals(trueValue))
/*  298: 298 */        return true;
/*  299: 299 */      if (value.equals(falseValue)) {
/*  300: 300 */        return false;
/*  301:     */      }
/*  302:     */    }
/*  303: 303 */    throw new IllegalArgumentException("The Integer did not match either specified value");
/*  304:     */  }
/*  305:     */  
/*  323:     */  public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue)
/*  324:     */  {
/*  325: 325 */    if (value == trueValue) {
/*  326: 326 */      return Boolean.TRUE;
/*  327:     */    }
/*  328: 328 */    if (value == falseValue) {
/*  329: 329 */      return Boolean.FALSE;
/*  330:     */    }
/*  331: 331 */    if (value == nullValue) {
/*  332: 332 */      return null;
/*  333:     */    }
/*  334:     */    
/*  335: 335 */    throw new IllegalArgumentException("The Integer did not match any specified value");
/*  336:     */  }
/*  337:     */  
/*  355:     */  public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue)
/*  356:     */  {
/*  357: 357 */    if (value == null) {
/*  358: 358 */      if (trueValue == null) {
/*  359: 359 */        return Boolean.TRUE;
/*  360:     */      }
/*  361: 361 */      if (falseValue == null) {
/*  362: 362 */        return Boolean.FALSE;
/*  363:     */      }
/*  364: 364 */      if (nullValue == null)
/*  365: 365 */        return null;
/*  366:     */    } else {
/*  367: 367 */      if (value.equals(trueValue))
/*  368: 368 */        return Boolean.TRUE;
/*  369: 369 */      if (value.equals(falseValue))
/*  370: 370 */        return Boolean.FALSE;
/*  371: 371 */      if (value.equals(nullValue)) {
/*  372: 372 */        return null;
/*  373:     */      }
/*  374:     */    }
/*  375: 375 */    throw new IllegalArgumentException("The Integer did not match any specified value");
/*  376:     */  }
/*  377:     */  
/*  391:     */  public static int toInteger(boolean bool)
/*  392:     */  {
/*  393: 393 */    return bool ? 1 : 0;
/*  394:     */  }
/*  395:     */  
/*  407:     */  public static Integer toIntegerObject(boolean bool)
/*  408:     */  {
/*  409: 409 */    return bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*  410:     */  }
/*  411:     */  
/*  425:     */  public static Integer toIntegerObject(Boolean bool)
/*  426:     */  {
/*  427: 427 */    if (bool == null) {
/*  428: 428 */      return null;
/*  429:     */    }
/*  430: 430 */    return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*  431:     */  }
/*  432:     */  
/*  445:     */  public static int toInteger(boolean bool, int trueValue, int falseValue)
/*  446:     */  {
/*  447: 447 */    return bool ? trueValue : falseValue;
/*  448:     */  }
/*  449:     */  
/*  464:     */  public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue)
/*  465:     */  {
/*  466: 466 */    if (bool == null) {
/*  467: 467 */      return nullValue;
/*  468:     */    }
/*  469: 469 */    return bool.booleanValue() ? trueValue : falseValue;
/*  470:     */  }
/*  471:     */  
/*  484:     */  public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue)
/*  485:     */  {
/*  486: 486 */    return bool ? trueValue : falseValue;
/*  487:     */  }
/*  488:     */  
/*  503:     */  public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue)
/*  504:     */  {
/*  505: 505 */    if (bool == null) {
/*  506: 506 */      return nullValue;
/*  507:     */    }
/*  508: 508 */    return bool.booleanValue() ? trueValue : falseValue;
/*  509:     */  }
/*  510:     */  
/*  543:     */  public static Boolean toBooleanObject(String str)
/*  544:     */  {
/*  545: 545 */    if (str == "true") {
/*  546: 546 */      return Boolean.TRUE;
/*  547:     */    }
/*  548: 548 */    if (str == null) {
/*  549: 549 */      return null;
/*  550:     */    }
/*  551: 551 */    switch (str.length()) {
/*  552:     */    case 1: 
/*  553: 553 */      char ch0 = str.charAt(0);
/*  554: 554 */      if ((ch0 == 'y') || (ch0 == 'Y') || (ch0 == 't') || (ch0 == 'T'))
/*  555:     */      {
/*  556: 556 */        return Boolean.TRUE;
/*  557:     */      }
/*  558: 558 */      if ((ch0 == 'n') || (ch0 == 'N') || (ch0 == 'f') || (ch0 == 'F'))
/*  559:     */      {
/*  560: 560 */        return Boolean.FALSE;
/*  561:     */      }
/*  562:     */      
/*  563:     */      break;
/*  564:     */    case 2: 
/*  565: 565 */      char ch0 = str.charAt(0);
/*  566: 566 */      char ch1 = str.charAt(1);
/*  567: 567 */      if (((ch0 == 'o') || (ch0 == 'O')) && ((ch1 == 'n') || (ch1 == 'N')))
/*  568:     */      {
/*  569: 569 */        return Boolean.TRUE;
/*  570:     */      }
/*  571: 571 */      if (((ch0 == 'n') || (ch0 == 'N')) && ((ch1 == 'o') || (ch1 == 'O')))
/*  572:     */      {
/*  573: 573 */        return Boolean.FALSE;
/*  574:     */      }
/*  575:     */      
/*  576:     */      break;
/*  577:     */    case 3: 
/*  578: 578 */      char ch0 = str.charAt(0);
/*  579: 579 */      char ch1 = str.charAt(1);
/*  580: 580 */      char ch2 = str.charAt(2);
/*  581: 581 */      if (((ch0 == 'y') || (ch0 == 'Y')) && ((ch1 == 'e') || (ch1 == 'E')) && ((ch2 == 's') || (ch2 == 'S')))
/*  582:     */      {
/*  584: 584 */        return Boolean.TRUE;
/*  585:     */      }
/*  586: 586 */      if (((ch0 == 'o') || (ch0 == 'O')) && ((ch1 == 'f') || (ch1 == 'F')) && ((ch2 == 'f') || (ch2 == 'F')))
/*  587:     */      {
/*  589: 589 */        return Boolean.FALSE;
/*  590:     */      }
/*  591:     */      
/*  592:     */      break;
/*  593:     */    case 4: 
/*  594: 594 */      char ch0 = str.charAt(0);
/*  595: 595 */      char ch1 = str.charAt(1);
/*  596: 596 */      char ch2 = str.charAt(2);
/*  597: 597 */      char ch3 = str.charAt(3);
/*  598: 598 */      if (((ch0 == 't') || (ch0 == 'T')) && ((ch1 == 'r') || (ch1 == 'R')) && ((ch2 == 'u') || (ch2 == 'U')) && ((ch3 == 'e') || (ch3 == 'E')))
/*  599:     */      {
/*  602: 602 */        return Boolean.TRUE;
/*  603:     */      }
/*  604:     */      
/*  605:     */      break;
/*  606:     */    case 5: 
/*  607: 607 */      char ch0 = str.charAt(0);
/*  608: 608 */      char ch1 = str.charAt(1);
/*  609: 609 */      char ch2 = str.charAt(2);
/*  610: 610 */      char ch3 = str.charAt(3);
/*  611: 611 */      char ch4 = str.charAt(4);
/*  612: 612 */      if (((ch0 == 'f') || (ch0 == 'F')) && ((ch1 == 'a') || (ch1 == 'A')) && ((ch2 == 'l') || (ch2 == 'L')) && ((ch3 == 's') || (ch3 == 'S')) && ((ch4 == 'e') || (ch4 == 'E')))
/*  613:     */      {
/*  617: 617 */        return Boolean.FALSE;
/*  618:     */      }
/*  619:     */      
/*  620:     */      break;
/*  621:     */    }
/*  622:     */    
/*  623: 623 */    return null;
/*  624:     */  }
/*  625:     */  
/*  644:     */  public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString)
/*  645:     */  {
/*  646: 646 */    if (str == null) {
/*  647: 647 */      if (trueString == null) {
/*  648: 648 */        return Boolean.TRUE;
/*  649:     */      }
/*  650: 650 */      if (falseString == null) {
/*  651: 651 */        return Boolean.FALSE;
/*  652:     */      }
/*  653: 653 */      if (nullString == null)
/*  654: 654 */        return null;
/*  655:     */    } else {
/*  656: 656 */      if (str.equals(trueString))
/*  657: 657 */        return Boolean.TRUE;
/*  658: 658 */      if (str.equals(falseString))
/*  659: 659 */        return Boolean.FALSE;
/*  660: 660 */      if (str.equals(nullString)) {
/*  661: 661 */        return null;
/*  662:     */      }
/*  663:     */    }
/*  664: 664 */    throw new IllegalArgumentException("The String did not match any specified value");
/*  665:     */  }
/*  666:     */  
/*  693:     */  public static boolean toBoolean(String str)
/*  694:     */  {
/*  695: 695 */    return toBooleanObject(str) == Boolean.TRUE;
/*  696:     */  }
/*  697:     */  
/*  711:     */  public static boolean toBoolean(String str, String trueString, String falseString)
/*  712:     */  {
/*  713: 713 */    if (str == trueString)
/*  714: 714 */      return true;
/*  715: 715 */    if (str == falseString)
/*  716: 716 */      return false;
/*  717: 717 */    if (str != null) {
/*  718: 718 */      if (str.equals(trueString))
/*  719: 719 */        return true;
/*  720: 720 */      if (str.equals(falseString)) {
/*  721: 721 */        return false;
/*  722:     */      }
/*  723:     */    }
/*  724:     */    
/*  725: 725 */    throw new IllegalArgumentException("The String did not match either specified value");
/*  726:     */  }
/*  727:     */  
/*  742:     */  public static String toStringTrueFalse(Boolean bool)
/*  743:     */  {
/*  744: 744 */    return toString(bool, "true", "false", null);
/*  745:     */  }
/*  746:     */  
/*  759:     */  public static String toStringOnOff(Boolean bool)
/*  760:     */  {
/*  761: 761 */    return toString(bool, "on", "off", null);
/*  762:     */  }
/*  763:     */  
/*  776:     */  public static String toStringYesNo(Boolean bool)
/*  777:     */  {
/*  778: 778 */    return toString(bool, "yes", "no", null);
/*  779:     */  }
/*  780:     */  
/*  795:     */  public static String toString(Boolean bool, String trueString, String falseString, String nullString)
/*  796:     */  {
/*  797: 797 */    if (bool == null) {
/*  798: 798 */      return nullString;
/*  799:     */    }
/*  800: 800 */    return bool.booleanValue() ? trueString : falseString;
/*  801:     */  }
/*  802:     */  
/*  816:     */  public static String toStringTrueFalse(boolean bool)
/*  817:     */  {
/*  818: 818 */    return toString(bool, "true", "false");
/*  819:     */  }
/*  820:     */  
/*  832:     */  public static String toStringOnOff(boolean bool)
/*  833:     */  {
/*  834: 834 */    return toString(bool, "on", "off");
/*  835:     */  }
/*  836:     */  
/*  848:     */  public static String toStringYesNo(boolean bool)
/*  849:     */  {
/*  850: 850 */    return toString(bool, "yes", "no");
/*  851:     */  }
/*  852:     */  
/*  865:     */  public static String toString(boolean bool, String trueString, String falseString)
/*  866:     */  {
/*  867: 867 */    return bool ? trueString : falseString;
/*  868:     */  }
/*  869:     */  
/*  889:     */  public static boolean and(boolean... array)
/*  890:     */  {
/*  891: 891 */    if (array == null) {
/*  892: 892 */      throw new IllegalArgumentException("The Array must not be null");
/*  893:     */    }
/*  894: 894 */    if (array.length == 0) {
/*  895: 895 */      throw new IllegalArgumentException("Array is empty");
/*  896:     */    }
/*  897: 897 */    for (boolean element : array) {
/*  898: 898 */      if (!element) {
/*  899: 899 */        return false;
/*  900:     */      }
/*  901:     */    }
/*  902: 902 */    return true;
/*  903:     */  }
/*  904:     */  
/*  923:     */  public static Boolean and(Boolean... array)
/*  924:     */  {
/*  925: 925 */    if (array == null) {
/*  926: 926 */      throw new IllegalArgumentException("The Array must not be null");
/*  927:     */    }
/*  928: 928 */    if (array.length == 0) {
/*  929: 929 */      throw new IllegalArgumentException("Array is empty");
/*  930:     */    }
/*  931:     */    try {
/*  932: 932 */      boolean[] primitive = ArrayUtils.toPrimitive(array);
/*  933: 933 */      return and(primitive) ? Boolean.TRUE : Boolean.FALSE;
/*  934:     */    } catch (NullPointerException ex) {
/*  935: 935 */      throw new IllegalArgumentException("The array must not contain any null elements");
/*  936:     */    }
/*  937:     */  }
/*  938:     */  
/*  957:     */  public static boolean or(boolean... array)
/*  958:     */  {
/*  959: 959 */    if (array == null) {
/*  960: 960 */      throw new IllegalArgumentException("The Array must not be null");
/*  961:     */    }
/*  962: 962 */    if (array.length == 0) {
/*  963: 963 */      throw new IllegalArgumentException("Array is empty");
/*  964:     */    }
/*  965: 965 */    for (boolean element : array) {
/*  966: 966 */      if (element) {
/*  967: 967 */        return true;
/*  968:     */      }
/*  969:     */    }
/*  970: 970 */    return false;
/*  971:     */  }
/*  972:     */  
/*  992:     */  public static Boolean or(Boolean... array)
/*  993:     */  {
/*  994: 994 */    if (array == null) {
/*  995: 995 */      throw new IllegalArgumentException("The Array must not be null");
/*  996:     */    }
/*  997: 997 */    if (array.length == 0) {
/*  998: 998 */      throw new IllegalArgumentException("Array is empty");
/*  999:     */    }
/* 1000:     */    try {
/* 1001:1001 */      boolean[] primitive = ArrayUtils.toPrimitive(array);
/* 1002:1002 */      return or(primitive) ? Boolean.TRUE : Boolean.FALSE;
/* 1003:     */    } catch (NullPointerException ex) {
/* 1004:1004 */      throw new IllegalArgumentException("The array must not contain any null elements");
/* 1005:     */    }
/* 1006:     */  }
/* 1007:     */  
/* 1025:     */  public static boolean xor(boolean... array)
/* 1026:     */  {
/* 1027:1027 */    if (array == null) {
/* 1028:1028 */      throw new IllegalArgumentException("The Array must not be null");
/* 1029:     */    }
/* 1030:1030 */    if (array.length == 0) {
/* 1031:1031 */      throw new IllegalArgumentException("Array is empty");
/* 1032:     */    }
/* 1033:     */    
/* 1035:1035 */    int trueCount = 0;
/* 1036:1036 */    for (boolean element : array)
/* 1037:     */    {
/* 1039:1039 */      if (element) {
/* 1040:1040 */        if (trueCount < 1) {
/* 1041:1041 */          trueCount++;
/* 1042:     */        } else {
/* 1043:1043 */          return false;
/* 1044:     */        }
/* 1045:     */      }
/* 1046:     */    }
/* 1047:     */    
/* 1049:1049 */    return trueCount == 1;
/* 1050:     */  }
/* 1051:     */  
/* 1066:     */  public static Boolean xor(Boolean... array)
/* 1067:     */  {
/* 1068:1068 */    if (array == null) {
/* 1069:1069 */      throw new IllegalArgumentException("The Array must not be null");
/* 1070:     */    }
/* 1071:1071 */    if (array.length == 0) {
/* 1072:1072 */      throw new IllegalArgumentException("Array is empty");
/* 1073:     */    }
/* 1074:     */    try {
/* 1075:1075 */      boolean[] primitive = ArrayUtils.toPrimitive(array);
/* 1076:1076 */      return xor(primitive) ? Boolean.TRUE : Boolean.FALSE;
/* 1077:     */    } catch (NullPointerException ex) {
/* 1078:1078 */      throw new IllegalArgumentException("The array must not contain any null elements");
/* 1079:     */    }
/* 1080:     */  }
/* 1081:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.BooleanUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */