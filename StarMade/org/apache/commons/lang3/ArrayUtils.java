/*    1:     */package org.apache.commons.lang3;
/*    2:     */
/*    3:     */import java.lang.reflect.Array;
/*    4:     */import java.util.Arrays;
/*    5:     */import java.util.HashMap;
/*    6:     */import java.util.HashSet;
/*    7:     */import java.util.Map;
/*    8:     */import java.util.Map.Entry;
/*    9:     */import org.apache.commons.lang3.builder.EqualsBuilder;
/*   10:     */import org.apache.commons.lang3.builder.HashCodeBuilder;
/*   11:     */import org.apache.commons.lang3.builder.ToStringBuilder;
/*   12:     */import org.apache.commons.lang3.builder.ToStringStyle;
/*   13:     */import org.apache.commons.lang3.mutable.MutableInt;
/*   14:     */
/*   47:     */public class ArrayUtils
/*   48:     */{
/*   49:  49 */  public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*   50:     */  
/*   53:  53 */  public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
/*   54:     */  
/*   57:  57 */  public static final String[] EMPTY_STRING_ARRAY = new String[0];
/*   58:     */  
/*   61:  61 */  public static final long[] EMPTY_LONG_ARRAY = new long[0];
/*   62:     */  
/*   65:  65 */  public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
/*   66:     */  
/*   69:  69 */  public static final int[] EMPTY_INT_ARRAY = new int[0];
/*   70:     */  
/*   73:  73 */  public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
/*   74:     */  
/*   77:  77 */  public static final short[] EMPTY_SHORT_ARRAY = new short[0];
/*   78:     */  
/*   81:  81 */  public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
/*   82:     */  
/*   85:  85 */  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*   86:     */  
/*   89:  89 */  public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
/*   90:     */  
/*   93:  93 */  public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
/*   94:     */  
/*   97:  97 */  public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
/*   98:     */  
/*  101: 101 */  public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
/*  102:     */  
/*  105: 105 */  public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
/*  106:     */  
/*  109: 109 */  public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
/*  110:     */  
/*  113: 113 */  public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
/*  114:     */  
/*  117: 117 */  public static final char[] EMPTY_CHAR_ARRAY = new char[0];
/*  118:     */  
/*  121: 121 */  public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
/*  122:     */  
/*  139:     */  public static final int INDEX_NOT_FOUND = -1;
/*  140:     */  
/*  157:     */  public static String toString(Object array)
/*  158:     */  {
/*  159: 159 */    return toString(array, "{}");
/*  160:     */  }
/*  161:     */  
/*  173:     */  public static String toString(Object array, String stringIfNull)
/*  174:     */  {
/*  175: 175 */    if (array == null) {
/*  176: 176 */      return stringIfNull;
/*  177:     */    }
/*  178: 178 */    return new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE).append(array).toString();
/*  179:     */  }
/*  180:     */  
/*  188:     */  public static int hashCode(Object array)
/*  189:     */  {
/*  190: 190 */    return new HashCodeBuilder().append(array).toHashCode();
/*  191:     */  }
/*  192:     */  
/*  202:     */  public static boolean isEquals(Object array1, Object array2)
/*  203:     */  {
/*  204: 204 */    return new EqualsBuilder().append(array1, array2).isEquals();
/*  205:     */  }
/*  206:     */  
/*  233:     */  public static Map<Object, Object> toMap(Object[] array)
/*  234:     */  {
/*  235: 235 */    if (array == null) {
/*  236: 236 */      return null;
/*  237:     */    }
/*  238: 238 */    Map<Object, Object> map = new HashMap((int)(array.length * 1.5D));
/*  239: 239 */    for (int i = 0; i < array.length; i++) {
/*  240: 240 */      Object object = array[i];
/*  241: 241 */      if ((object instanceof Map.Entry)) {
/*  242: 242 */        Map.Entry<?, ?> entry = (Map.Entry)object;
/*  243: 243 */        map.put(entry.getKey(), entry.getValue());
/*  244: 244 */      } else if ((object instanceof Object[])) {
/*  245: 245 */        Object[] entry = (Object[])object;
/*  246: 246 */        if (entry.length < 2) {
/*  247: 247 */          throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2");
/*  248:     */        }
/*  249:     */        
/*  251: 251 */        map.put(entry[0], entry[1]);
/*  252:     */      } else {
/*  253: 253 */        throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
/*  254:     */      }
/*  255:     */    }
/*  256:     */    
/*  258: 258 */    return map;
/*  259:     */  }
/*  260:     */  
/*  299:     */  public static <T> T[] toArray(T... items)
/*  300:     */  {
/*  301: 301 */    return items;
/*  302:     */  }
/*  303:     */  
/*  318:     */  public static <T> T[] clone(T[] array)
/*  319:     */  {
/*  320: 320 */    if (array == null) {
/*  321: 321 */      return null;
/*  322:     */    }
/*  323: 323 */    return (Object[])array.clone();
/*  324:     */  }
/*  325:     */  
/*  334:     */  public static long[] clone(long[] array)
/*  335:     */  {
/*  336: 336 */    if (array == null) {
/*  337: 337 */      return null;
/*  338:     */    }
/*  339: 339 */    return (long[])array.clone();
/*  340:     */  }
/*  341:     */  
/*  350:     */  public static int[] clone(int[] array)
/*  351:     */  {
/*  352: 352 */    if (array == null) {
/*  353: 353 */      return null;
/*  354:     */    }
/*  355: 355 */    return (int[])array.clone();
/*  356:     */  }
/*  357:     */  
/*  366:     */  public static short[] clone(short[] array)
/*  367:     */  {
/*  368: 368 */    if (array == null) {
/*  369: 369 */      return null;
/*  370:     */    }
/*  371: 371 */    return (short[])array.clone();
/*  372:     */  }
/*  373:     */  
/*  382:     */  public static char[] clone(char[] array)
/*  383:     */  {
/*  384: 384 */    if (array == null) {
/*  385: 385 */      return null;
/*  386:     */    }
/*  387: 387 */    return (char[])array.clone();
/*  388:     */  }
/*  389:     */  
/*  398:     */  public static byte[] clone(byte[] array)
/*  399:     */  {
/*  400: 400 */    if (array == null) {
/*  401: 401 */      return null;
/*  402:     */    }
/*  403: 403 */    return (byte[])array.clone();
/*  404:     */  }
/*  405:     */  
/*  414:     */  public static double[] clone(double[] array)
/*  415:     */  {
/*  416: 416 */    if (array == null) {
/*  417: 417 */      return null;
/*  418:     */    }
/*  419: 419 */    return (double[])array.clone();
/*  420:     */  }
/*  421:     */  
/*  430:     */  public static float[] clone(float[] array)
/*  431:     */  {
/*  432: 432 */    if (array == null) {
/*  433: 433 */      return null;
/*  434:     */    }
/*  435: 435 */    return (float[])array.clone();
/*  436:     */  }
/*  437:     */  
/*  446:     */  public static boolean[] clone(boolean[] array)
/*  447:     */  {
/*  448: 448 */    if (array == null) {
/*  449: 449 */      return null;
/*  450:     */    }
/*  451: 451 */    return (boolean[])array.clone();
/*  452:     */  }
/*  453:     */  
/*  468:     */  public static Object[] nullToEmpty(Object[] array)
/*  469:     */  {
/*  470: 470 */    if ((array == null) || (array.length == 0)) {
/*  471: 471 */      return EMPTY_OBJECT_ARRAY;
/*  472:     */    }
/*  473: 473 */    return array;
/*  474:     */  }
/*  475:     */  
/*  488:     */  public static String[] nullToEmpty(String[] array)
/*  489:     */  {
/*  490: 490 */    if ((array == null) || (array.length == 0)) {
/*  491: 491 */      return EMPTY_STRING_ARRAY;
/*  492:     */    }
/*  493: 493 */    return array;
/*  494:     */  }
/*  495:     */  
/*  508:     */  public static long[] nullToEmpty(long[] array)
/*  509:     */  {
/*  510: 510 */    if ((array == null) || (array.length == 0)) {
/*  511: 511 */      return EMPTY_LONG_ARRAY;
/*  512:     */    }
/*  513: 513 */    return array;
/*  514:     */  }
/*  515:     */  
/*  528:     */  public static int[] nullToEmpty(int[] array)
/*  529:     */  {
/*  530: 530 */    if ((array == null) || (array.length == 0)) {
/*  531: 531 */      return EMPTY_INT_ARRAY;
/*  532:     */    }
/*  533: 533 */    return array;
/*  534:     */  }
/*  535:     */  
/*  548:     */  public static short[] nullToEmpty(short[] array)
/*  549:     */  {
/*  550: 550 */    if ((array == null) || (array.length == 0)) {
/*  551: 551 */      return EMPTY_SHORT_ARRAY;
/*  552:     */    }
/*  553: 553 */    return array;
/*  554:     */  }
/*  555:     */  
/*  568:     */  public static char[] nullToEmpty(char[] array)
/*  569:     */  {
/*  570: 570 */    if ((array == null) || (array.length == 0)) {
/*  571: 571 */      return EMPTY_CHAR_ARRAY;
/*  572:     */    }
/*  573: 573 */    return array;
/*  574:     */  }
/*  575:     */  
/*  588:     */  public static byte[] nullToEmpty(byte[] array)
/*  589:     */  {
/*  590: 590 */    if ((array == null) || (array.length == 0)) {
/*  591: 591 */      return EMPTY_BYTE_ARRAY;
/*  592:     */    }
/*  593: 593 */    return array;
/*  594:     */  }
/*  595:     */  
/*  608:     */  public static double[] nullToEmpty(double[] array)
/*  609:     */  {
/*  610: 610 */    if ((array == null) || (array.length == 0)) {
/*  611: 611 */      return EMPTY_DOUBLE_ARRAY;
/*  612:     */    }
/*  613: 613 */    return array;
/*  614:     */  }
/*  615:     */  
/*  628:     */  public static float[] nullToEmpty(float[] array)
/*  629:     */  {
/*  630: 630 */    if ((array == null) || (array.length == 0)) {
/*  631: 631 */      return EMPTY_FLOAT_ARRAY;
/*  632:     */    }
/*  633: 633 */    return array;
/*  634:     */  }
/*  635:     */  
/*  648:     */  public static boolean[] nullToEmpty(boolean[] array)
/*  649:     */  {
/*  650: 650 */    if ((array == null) || (array.length == 0)) {
/*  651: 651 */      return EMPTY_BOOLEAN_ARRAY;
/*  652:     */    }
/*  653: 653 */    return array;
/*  654:     */  }
/*  655:     */  
/*  668:     */  public static Long[] nullToEmpty(Long[] array)
/*  669:     */  {
/*  670: 670 */    if ((array == null) || (array.length == 0)) {
/*  671: 671 */      return EMPTY_LONG_OBJECT_ARRAY;
/*  672:     */    }
/*  673: 673 */    return array;
/*  674:     */  }
/*  675:     */  
/*  688:     */  public static Integer[] nullToEmpty(Integer[] array)
/*  689:     */  {
/*  690: 690 */    if ((array == null) || (array.length == 0)) {
/*  691: 691 */      return EMPTY_INTEGER_OBJECT_ARRAY;
/*  692:     */    }
/*  693: 693 */    return array;
/*  694:     */  }
/*  695:     */  
/*  708:     */  public static Short[] nullToEmpty(Short[] array)
/*  709:     */  {
/*  710: 710 */    if ((array == null) || (array.length == 0)) {
/*  711: 711 */      return EMPTY_SHORT_OBJECT_ARRAY;
/*  712:     */    }
/*  713: 713 */    return array;
/*  714:     */  }
/*  715:     */  
/*  728:     */  public static Character[] nullToEmpty(Character[] array)
/*  729:     */  {
/*  730: 730 */    if ((array == null) || (array.length == 0)) {
/*  731: 731 */      return EMPTY_CHARACTER_OBJECT_ARRAY;
/*  732:     */    }
/*  733: 733 */    return array;
/*  734:     */  }
/*  735:     */  
/*  748:     */  public static Byte[] nullToEmpty(Byte[] array)
/*  749:     */  {
/*  750: 750 */    if ((array == null) || (array.length == 0)) {
/*  751: 751 */      return EMPTY_BYTE_OBJECT_ARRAY;
/*  752:     */    }
/*  753: 753 */    return array;
/*  754:     */  }
/*  755:     */  
/*  768:     */  public static Double[] nullToEmpty(Double[] array)
/*  769:     */  {
/*  770: 770 */    if ((array == null) || (array.length == 0)) {
/*  771: 771 */      return EMPTY_DOUBLE_OBJECT_ARRAY;
/*  772:     */    }
/*  773: 773 */    return array;
/*  774:     */  }
/*  775:     */  
/*  788:     */  public static Float[] nullToEmpty(Float[] array)
/*  789:     */  {
/*  790: 790 */    if ((array == null) || (array.length == 0)) {
/*  791: 791 */      return EMPTY_FLOAT_OBJECT_ARRAY;
/*  792:     */    }
/*  793: 793 */    return array;
/*  794:     */  }
/*  795:     */  
/*  808:     */  public static Boolean[] nullToEmpty(Boolean[] array)
/*  809:     */  {
/*  810: 810 */    if ((array == null) || (array.length == 0)) {
/*  811: 811 */      return EMPTY_BOOLEAN_OBJECT_ARRAY;
/*  812:     */    }
/*  813: 813 */    return array;
/*  814:     */  }
/*  815:     */  
/*  845:     */  public static <T> T[] subarray(T[] array, int startIndexInclusive, int endIndexExclusive)
/*  846:     */  {
/*  847: 847 */    if (array == null) {
/*  848: 848 */      return null;
/*  849:     */    }
/*  850: 850 */    if (startIndexInclusive < 0) {
/*  851: 851 */      startIndexInclusive = 0;
/*  852:     */    }
/*  853: 853 */    if (endIndexExclusive > array.length) {
/*  854: 854 */      endIndexExclusive = array.length;
/*  855:     */    }
/*  856: 856 */    int newSize = endIndexExclusive - startIndexInclusive;
/*  857: 857 */    Class<?> type = array.getClass().getComponentType();
/*  858: 858 */    if (newSize <= 0)
/*  859:     */    {
/*  860: 860 */      T[] emptyArray = (Object[])Array.newInstance(type, 0);
/*  861: 861 */      return emptyArray;
/*  862:     */    }
/*  863:     */    
/*  864: 864 */    T[] subarray = (Object[])Array.newInstance(type, newSize);
/*  865: 865 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  866: 866 */    return subarray;
/*  867:     */  }
/*  868:     */  
/*  887:     */  public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive)
/*  888:     */  {
/*  889: 889 */    if (array == null) {
/*  890: 890 */      return null;
/*  891:     */    }
/*  892: 892 */    if (startIndexInclusive < 0) {
/*  893: 893 */      startIndexInclusive = 0;
/*  894:     */    }
/*  895: 895 */    if (endIndexExclusive > array.length) {
/*  896: 896 */      endIndexExclusive = array.length;
/*  897:     */    }
/*  898: 898 */    int newSize = endIndexExclusive - startIndexInclusive;
/*  899: 899 */    if (newSize <= 0) {
/*  900: 900 */      return EMPTY_LONG_ARRAY;
/*  901:     */    }
/*  902:     */    
/*  903: 903 */    long[] subarray = new long[newSize];
/*  904: 904 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  905: 905 */    return subarray;
/*  906:     */  }
/*  907:     */  
/*  926:     */  public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive)
/*  927:     */  {
/*  928: 928 */    if (array == null) {
/*  929: 929 */      return null;
/*  930:     */    }
/*  931: 931 */    if (startIndexInclusive < 0) {
/*  932: 932 */      startIndexInclusive = 0;
/*  933:     */    }
/*  934: 934 */    if (endIndexExclusive > array.length) {
/*  935: 935 */      endIndexExclusive = array.length;
/*  936:     */    }
/*  937: 937 */    int newSize = endIndexExclusive - startIndexInclusive;
/*  938: 938 */    if (newSize <= 0) {
/*  939: 939 */      return EMPTY_INT_ARRAY;
/*  940:     */    }
/*  941:     */    
/*  942: 942 */    int[] subarray = new int[newSize];
/*  943: 943 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  944: 944 */    return subarray;
/*  945:     */  }
/*  946:     */  
/*  965:     */  public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive)
/*  966:     */  {
/*  967: 967 */    if (array == null) {
/*  968: 968 */      return null;
/*  969:     */    }
/*  970: 970 */    if (startIndexInclusive < 0) {
/*  971: 971 */      startIndexInclusive = 0;
/*  972:     */    }
/*  973: 973 */    if (endIndexExclusive > array.length) {
/*  974: 974 */      endIndexExclusive = array.length;
/*  975:     */    }
/*  976: 976 */    int newSize = endIndexExclusive - startIndexInclusive;
/*  977: 977 */    if (newSize <= 0) {
/*  978: 978 */      return EMPTY_SHORT_ARRAY;
/*  979:     */    }
/*  980:     */    
/*  981: 981 */    short[] subarray = new short[newSize];
/*  982: 982 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  983: 983 */    return subarray;
/*  984:     */  }
/*  985:     */  
/* 1004:     */  public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive)
/* 1005:     */  {
/* 1006:1006 */    if (array == null) {
/* 1007:1007 */      return null;
/* 1008:     */    }
/* 1009:1009 */    if (startIndexInclusive < 0) {
/* 1010:1010 */      startIndexInclusive = 0;
/* 1011:     */    }
/* 1012:1012 */    if (endIndexExclusive > array.length) {
/* 1013:1013 */      endIndexExclusive = array.length;
/* 1014:     */    }
/* 1015:1015 */    int newSize = endIndexExclusive - startIndexInclusive;
/* 1016:1016 */    if (newSize <= 0) {
/* 1017:1017 */      return EMPTY_CHAR_ARRAY;
/* 1018:     */    }
/* 1019:     */    
/* 1020:1020 */    char[] subarray = new char[newSize];
/* 1021:1021 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1022:1022 */    return subarray;
/* 1023:     */  }
/* 1024:     */  
/* 1043:     */  public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive)
/* 1044:     */  {
/* 1045:1045 */    if (array == null) {
/* 1046:1046 */      return null;
/* 1047:     */    }
/* 1048:1048 */    if (startIndexInclusive < 0) {
/* 1049:1049 */      startIndexInclusive = 0;
/* 1050:     */    }
/* 1051:1051 */    if (endIndexExclusive > array.length) {
/* 1052:1052 */      endIndexExclusive = array.length;
/* 1053:     */    }
/* 1054:1054 */    int newSize = endIndexExclusive - startIndexInclusive;
/* 1055:1055 */    if (newSize <= 0) {
/* 1056:1056 */      return EMPTY_BYTE_ARRAY;
/* 1057:     */    }
/* 1058:     */    
/* 1059:1059 */    byte[] subarray = new byte[newSize];
/* 1060:1060 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1061:1061 */    return subarray;
/* 1062:     */  }
/* 1063:     */  
/* 1082:     */  public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive)
/* 1083:     */  {
/* 1084:1084 */    if (array == null) {
/* 1085:1085 */      return null;
/* 1086:     */    }
/* 1087:1087 */    if (startIndexInclusive < 0) {
/* 1088:1088 */      startIndexInclusive = 0;
/* 1089:     */    }
/* 1090:1090 */    if (endIndexExclusive > array.length) {
/* 1091:1091 */      endIndexExclusive = array.length;
/* 1092:     */    }
/* 1093:1093 */    int newSize = endIndexExclusive - startIndexInclusive;
/* 1094:1094 */    if (newSize <= 0) {
/* 1095:1095 */      return EMPTY_DOUBLE_ARRAY;
/* 1096:     */    }
/* 1097:     */    
/* 1098:1098 */    double[] subarray = new double[newSize];
/* 1099:1099 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1100:1100 */    return subarray;
/* 1101:     */  }
/* 1102:     */  
/* 1121:     */  public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive)
/* 1122:     */  {
/* 1123:1123 */    if (array == null) {
/* 1124:1124 */      return null;
/* 1125:     */    }
/* 1126:1126 */    if (startIndexInclusive < 0) {
/* 1127:1127 */      startIndexInclusive = 0;
/* 1128:     */    }
/* 1129:1129 */    if (endIndexExclusive > array.length) {
/* 1130:1130 */      endIndexExclusive = array.length;
/* 1131:     */    }
/* 1132:1132 */    int newSize = endIndexExclusive - startIndexInclusive;
/* 1133:1133 */    if (newSize <= 0) {
/* 1134:1134 */      return EMPTY_FLOAT_ARRAY;
/* 1135:     */    }
/* 1136:     */    
/* 1137:1137 */    float[] subarray = new float[newSize];
/* 1138:1138 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1139:1139 */    return subarray;
/* 1140:     */  }
/* 1141:     */  
/* 1160:     */  public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive)
/* 1161:     */  {
/* 1162:1162 */    if (array == null) {
/* 1163:1163 */      return null;
/* 1164:     */    }
/* 1165:1165 */    if (startIndexInclusive < 0) {
/* 1166:1166 */      startIndexInclusive = 0;
/* 1167:     */    }
/* 1168:1168 */    if (endIndexExclusive > array.length) {
/* 1169:1169 */      endIndexExclusive = array.length;
/* 1170:     */    }
/* 1171:1171 */    int newSize = endIndexExclusive - startIndexInclusive;
/* 1172:1172 */    if (newSize <= 0) {
/* 1173:1173 */      return EMPTY_BOOLEAN_ARRAY;
/* 1174:     */    }
/* 1175:     */    
/* 1176:1176 */    boolean[] subarray = new boolean[newSize];
/* 1177:1177 */    System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1178:1178 */    return subarray;
/* 1179:     */  }
/* 1180:     */  
/* 1193:     */  public static boolean isSameLength(Object[] array1, Object[] array2)
/* 1194:     */  {
/* 1195:1195 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1196:     */    {
/* 1198:1198 */      return false;
/* 1199:     */    }
/* 1200:1200 */    return true;
/* 1201:     */  }
/* 1202:     */  
/* 1211:     */  public static boolean isSameLength(long[] array1, long[] array2)
/* 1212:     */  {
/* 1213:1213 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1214:     */    {
/* 1216:1216 */      return false;
/* 1217:     */    }
/* 1218:1218 */    return true;
/* 1219:     */  }
/* 1220:     */  
/* 1229:     */  public static boolean isSameLength(int[] array1, int[] array2)
/* 1230:     */  {
/* 1231:1231 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1232:     */    {
/* 1234:1234 */      return false;
/* 1235:     */    }
/* 1236:1236 */    return true;
/* 1237:     */  }
/* 1238:     */  
/* 1247:     */  public static boolean isSameLength(short[] array1, short[] array2)
/* 1248:     */  {
/* 1249:1249 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1250:     */    {
/* 1252:1252 */      return false;
/* 1253:     */    }
/* 1254:1254 */    return true;
/* 1255:     */  }
/* 1256:     */  
/* 1265:     */  public static boolean isSameLength(char[] array1, char[] array2)
/* 1266:     */  {
/* 1267:1267 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1268:     */    {
/* 1270:1270 */      return false;
/* 1271:     */    }
/* 1272:1272 */    return true;
/* 1273:     */  }
/* 1274:     */  
/* 1283:     */  public static boolean isSameLength(byte[] array1, byte[] array2)
/* 1284:     */  {
/* 1285:1285 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1286:     */    {
/* 1288:1288 */      return false;
/* 1289:     */    }
/* 1290:1290 */    return true;
/* 1291:     */  }
/* 1292:     */  
/* 1301:     */  public static boolean isSameLength(double[] array1, double[] array2)
/* 1302:     */  {
/* 1303:1303 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1304:     */    {
/* 1306:1306 */      return false;
/* 1307:     */    }
/* 1308:1308 */    return true;
/* 1309:     */  }
/* 1310:     */  
/* 1319:     */  public static boolean isSameLength(float[] array1, float[] array2)
/* 1320:     */  {
/* 1321:1321 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1322:     */    {
/* 1324:1324 */      return false;
/* 1325:     */    }
/* 1326:1326 */    return true;
/* 1327:     */  }
/* 1328:     */  
/* 1337:     */  public static boolean isSameLength(boolean[] array1, boolean[] array2)
/* 1338:     */  {
/* 1339:1339 */    if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/* 1340:     */    {
/* 1342:1342 */      return false;
/* 1343:     */    }
/* 1344:1344 */    return true;
/* 1345:     */  }
/* 1346:     */  
/* 1367:     */  public static int getLength(Object array)
/* 1368:     */  {
/* 1369:1369 */    if (array == null) {
/* 1370:1370 */      return 0;
/* 1371:     */    }
/* 1372:1372 */    return Array.getLength(array);
/* 1373:     */  }
/* 1374:     */  
/* 1383:     */  public static boolean isSameType(Object array1, Object array2)
/* 1384:     */  {
/* 1385:1385 */    if ((array1 == null) || (array2 == null)) {
/* 1386:1386 */      throw new IllegalArgumentException("The Array must not be null");
/* 1387:     */    }
/* 1388:1388 */    return array1.getClass().getName().equals(array2.getClass().getName());
/* 1389:     */  }
/* 1390:     */  
/* 1401:     */  public static void reverse(Object[] array)
/* 1402:     */  {
/* 1403:1403 */    if (array == null) {
/* 1404:1404 */      return;
/* 1405:     */    }
/* 1406:1406 */    int i = 0;
/* 1407:1407 */    int j = array.length - 1;
/* 1408:     */    
/* 1409:1409 */    while (j > i) {
/* 1410:1410 */      Object tmp = array[j];
/* 1411:1411 */      array[j] = array[i];
/* 1412:1412 */      array[i] = tmp;
/* 1413:1413 */      j--;
/* 1414:1414 */      i++;
/* 1415:     */    }
/* 1416:     */  }
/* 1417:     */  
/* 1424:     */  public static void reverse(long[] array)
/* 1425:     */  {
/* 1426:1426 */    if (array == null) {
/* 1427:1427 */      return;
/* 1428:     */    }
/* 1429:1429 */    int i = 0;
/* 1430:1430 */    int j = array.length - 1;
/* 1431:     */    
/* 1432:1432 */    while (j > i) {
/* 1433:1433 */      long tmp = array[j];
/* 1434:1434 */      array[j] = array[i];
/* 1435:1435 */      array[i] = tmp;
/* 1436:1436 */      j--;
/* 1437:1437 */      i++;
/* 1438:     */    }
/* 1439:     */  }
/* 1440:     */  
/* 1447:     */  public static void reverse(int[] array)
/* 1448:     */  {
/* 1449:1449 */    if (array == null) {
/* 1450:1450 */      return;
/* 1451:     */    }
/* 1452:1452 */    int i = 0;
/* 1453:1453 */    int j = array.length - 1;
/* 1454:     */    
/* 1455:1455 */    while (j > i) {
/* 1456:1456 */      int tmp = array[j];
/* 1457:1457 */      array[j] = array[i];
/* 1458:1458 */      array[i] = tmp;
/* 1459:1459 */      j--;
/* 1460:1460 */      i++;
/* 1461:     */    }
/* 1462:     */  }
/* 1463:     */  
/* 1470:     */  public static void reverse(short[] array)
/* 1471:     */  {
/* 1472:1472 */    if (array == null) {
/* 1473:1473 */      return;
/* 1474:     */    }
/* 1475:1475 */    int i = 0;
/* 1476:1476 */    int j = array.length - 1;
/* 1477:     */    
/* 1478:1478 */    while (j > i) {
/* 1479:1479 */      short tmp = array[j];
/* 1480:1480 */      array[j] = array[i];
/* 1481:1481 */      array[i] = tmp;
/* 1482:1482 */      j--;
/* 1483:1483 */      i++;
/* 1484:     */    }
/* 1485:     */  }
/* 1486:     */  
/* 1493:     */  public static void reverse(char[] array)
/* 1494:     */  {
/* 1495:1495 */    if (array == null) {
/* 1496:1496 */      return;
/* 1497:     */    }
/* 1498:1498 */    int i = 0;
/* 1499:1499 */    int j = array.length - 1;
/* 1500:     */    
/* 1501:1501 */    while (j > i) {
/* 1502:1502 */      char tmp = array[j];
/* 1503:1503 */      array[j] = array[i];
/* 1504:1504 */      array[i] = tmp;
/* 1505:1505 */      j--;
/* 1506:1506 */      i++;
/* 1507:     */    }
/* 1508:     */  }
/* 1509:     */  
/* 1516:     */  public static void reverse(byte[] array)
/* 1517:     */  {
/* 1518:1518 */    if (array == null) {
/* 1519:1519 */      return;
/* 1520:     */    }
/* 1521:1521 */    int i = 0;
/* 1522:1522 */    int j = array.length - 1;
/* 1523:     */    
/* 1524:1524 */    while (j > i) {
/* 1525:1525 */      byte tmp = array[j];
/* 1526:1526 */      array[j] = array[i];
/* 1527:1527 */      array[i] = tmp;
/* 1528:1528 */      j--;
/* 1529:1529 */      i++;
/* 1530:     */    }
/* 1531:     */  }
/* 1532:     */  
/* 1539:     */  public static void reverse(double[] array)
/* 1540:     */  {
/* 1541:1541 */    if (array == null) {
/* 1542:1542 */      return;
/* 1543:     */    }
/* 1544:1544 */    int i = 0;
/* 1545:1545 */    int j = array.length - 1;
/* 1546:     */    
/* 1547:1547 */    while (j > i) {
/* 1548:1548 */      double tmp = array[j];
/* 1549:1549 */      array[j] = array[i];
/* 1550:1550 */      array[i] = tmp;
/* 1551:1551 */      j--;
/* 1552:1552 */      i++;
/* 1553:     */    }
/* 1554:     */  }
/* 1555:     */  
/* 1562:     */  public static void reverse(float[] array)
/* 1563:     */  {
/* 1564:1564 */    if (array == null) {
/* 1565:1565 */      return;
/* 1566:     */    }
/* 1567:1567 */    int i = 0;
/* 1568:1568 */    int j = array.length - 1;
/* 1569:     */    
/* 1570:1570 */    while (j > i) {
/* 1571:1571 */      float tmp = array[j];
/* 1572:1572 */      array[j] = array[i];
/* 1573:1573 */      array[i] = tmp;
/* 1574:1574 */      j--;
/* 1575:1575 */      i++;
/* 1576:     */    }
/* 1577:     */  }
/* 1578:     */  
/* 1585:     */  public static void reverse(boolean[] array)
/* 1586:     */  {
/* 1587:1587 */    if (array == null) {
/* 1588:1588 */      return;
/* 1589:     */    }
/* 1590:1590 */    int i = 0;
/* 1591:1591 */    int j = array.length - 1;
/* 1592:     */    
/* 1593:1593 */    while (j > i) {
/* 1594:1594 */      boolean tmp = array[j];
/* 1595:1595 */      array[j] = array[i];
/* 1596:1596 */      array[i] = tmp;
/* 1597:1597 */      j--;
/* 1598:1598 */      i++;
/* 1599:     */    }
/* 1600:     */  }
/* 1601:     */  
/* 1616:     */  public static int indexOf(Object[] array, Object objectToFind)
/* 1617:     */  {
/* 1618:1618 */    return indexOf(array, objectToFind, 0);
/* 1619:     */  }
/* 1620:     */  
/* 1634:     */  public static int indexOf(Object[] array, Object objectToFind, int startIndex)
/* 1635:     */  {
/* 1636:1636 */    if (array == null) {
/* 1637:1637 */      return -1;
/* 1638:     */    }
/* 1639:1639 */    if (startIndex < 0) {
/* 1640:1640 */      startIndex = 0;
/* 1641:     */    }
/* 1642:1642 */    if (objectToFind == null) {
/* 1643:1643 */      for (int i = startIndex; i < array.length; i++) {
/* 1644:1644 */        if (array[i] == null) {
/* 1645:1645 */          return i;
/* 1646:     */        }
/* 1647:     */      }
/* 1648:1648 */    } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 1649:1649 */      for (int i = startIndex; i < array.length; i++) {
/* 1650:1650 */        if (objectToFind.equals(array[i])) {
/* 1651:1651 */          return i;
/* 1652:     */        }
/* 1653:     */      }
/* 1654:     */    }
/* 1655:1655 */    return -1;
/* 1656:     */  }
/* 1657:     */  
/* 1667:     */  public static int lastIndexOf(Object[] array, Object objectToFind)
/* 1668:     */  {
/* 1669:1669 */    return lastIndexOf(array, objectToFind, 2147483647);
/* 1670:     */  }
/* 1671:     */  
/* 1685:     */  public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex)
/* 1686:     */  {
/* 1687:1687 */    if (array == null) {
/* 1688:1688 */      return -1;
/* 1689:     */    }
/* 1690:1690 */    if (startIndex < 0)
/* 1691:1691 */      return -1;
/* 1692:1692 */    if (startIndex >= array.length) {
/* 1693:1693 */      startIndex = array.length - 1;
/* 1694:     */    }
/* 1695:1695 */    if (objectToFind == null) {
/* 1696:1696 */      for (int i = startIndex; i >= 0; i--) {
/* 1697:1697 */        if (array[i] == null) {
/* 1698:1698 */          return i;
/* 1699:     */        }
/* 1700:     */      }
/* 1701:1701 */    } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 1702:1702 */      for (int i = startIndex; i >= 0; i--) {
/* 1703:1703 */        if (objectToFind.equals(array[i])) {
/* 1704:1704 */          return i;
/* 1705:     */        }
/* 1706:     */      }
/* 1707:     */    }
/* 1708:1708 */    return -1;
/* 1709:     */  }
/* 1710:     */  
/* 1719:     */  public static boolean contains(Object[] array, Object objectToFind)
/* 1720:     */  {
/* 1721:1721 */    return indexOf(array, objectToFind) != -1;
/* 1722:     */  }
/* 1723:     */  
/* 1735:     */  public static int indexOf(long[] array, long valueToFind)
/* 1736:     */  {
/* 1737:1737 */    return indexOf(array, valueToFind, 0);
/* 1738:     */  }
/* 1739:     */  
/* 1753:     */  public static int indexOf(long[] array, long valueToFind, int startIndex)
/* 1754:     */  {
/* 1755:1755 */    if (array == null) {
/* 1756:1756 */      return -1;
/* 1757:     */    }
/* 1758:1758 */    if (startIndex < 0) {
/* 1759:1759 */      startIndex = 0;
/* 1760:     */    }
/* 1761:1761 */    for (int i = startIndex; i < array.length; i++) {
/* 1762:1762 */      if (valueToFind == array[i]) {
/* 1763:1763 */        return i;
/* 1764:     */      }
/* 1765:     */    }
/* 1766:1766 */    return -1;
/* 1767:     */  }
/* 1768:     */  
/* 1778:     */  public static int lastIndexOf(long[] array, long valueToFind)
/* 1779:     */  {
/* 1780:1780 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 1781:     */  }
/* 1782:     */  
/* 1796:     */  public static int lastIndexOf(long[] array, long valueToFind, int startIndex)
/* 1797:     */  {
/* 1798:1798 */    if (array == null) {
/* 1799:1799 */      return -1;
/* 1800:     */    }
/* 1801:1801 */    if (startIndex < 0)
/* 1802:1802 */      return -1;
/* 1803:1803 */    if (startIndex >= array.length) {
/* 1804:1804 */      startIndex = array.length - 1;
/* 1805:     */    }
/* 1806:1806 */    for (int i = startIndex; i >= 0; i--) {
/* 1807:1807 */      if (valueToFind == array[i]) {
/* 1808:1808 */        return i;
/* 1809:     */      }
/* 1810:     */    }
/* 1811:1811 */    return -1;
/* 1812:     */  }
/* 1813:     */  
/* 1822:     */  public static boolean contains(long[] array, long valueToFind)
/* 1823:     */  {
/* 1824:1824 */    return indexOf(array, valueToFind) != -1;
/* 1825:     */  }
/* 1826:     */  
/* 1838:     */  public static int indexOf(int[] array, int valueToFind)
/* 1839:     */  {
/* 1840:1840 */    return indexOf(array, valueToFind, 0);
/* 1841:     */  }
/* 1842:     */  
/* 1856:     */  public static int indexOf(int[] array, int valueToFind, int startIndex)
/* 1857:     */  {
/* 1858:1858 */    if (array == null) {
/* 1859:1859 */      return -1;
/* 1860:     */    }
/* 1861:1861 */    if (startIndex < 0) {
/* 1862:1862 */      startIndex = 0;
/* 1863:     */    }
/* 1864:1864 */    for (int i = startIndex; i < array.length; i++) {
/* 1865:1865 */      if (valueToFind == array[i]) {
/* 1866:1866 */        return i;
/* 1867:     */      }
/* 1868:     */    }
/* 1869:1869 */    return -1;
/* 1870:     */  }
/* 1871:     */  
/* 1881:     */  public static int lastIndexOf(int[] array, int valueToFind)
/* 1882:     */  {
/* 1883:1883 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 1884:     */  }
/* 1885:     */  
/* 1899:     */  public static int lastIndexOf(int[] array, int valueToFind, int startIndex)
/* 1900:     */  {
/* 1901:1901 */    if (array == null) {
/* 1902:1902 */      return -1;
/* 1903:     */    }
/* 1904:1904 */    if (startIndex < 0)
/* 1905:1905 */      return -1;
/* 1906:1906 */    if (startIndex >= array.length) {
/* 1907:1907 */      startIndex = array.length - 1;
/* 1908:     */    }
/* 1909:1909 */    for (int i = startIndex; i >= 0; i--) {
/* 1910:1910 */      if (valueToFind == array[i]) {
/* 1911:1911 */        return i;
/* 1912:     */      }
/* 1913:     */    }
/* 1914:1914 */    return -1;
/* 1915:     */  }
/* 1916:     */  
/* 1925:     */  public static boolean contains(int[] array, int valueToFind)
/* 1926:     */  {
/* 1927:1927 */    return indexOf(array, valueToFind) != -1;
/* 1928:     */  }
/* 1929:     */  
/* 1941:     */  public static int indexOf(short[] array, short valueToFind)
/* 1942:     */  {
/* 1943:1943 */    return indexOf(array, valueToFind, 0);
/* 1944:     */  }
/* 1945:     */  
/* 1959:     */  public static int indexOf(short[] array, short valueToFind, int startIndex)
/* 1960:     */  {
/* 1961:1961 */    if (array == null) {
/* 1962:1962 */      return -1;
/* 1963:     */    }
/* 1964:1964 */    if (startIndex < 0) {
/* 1965:1965 */      startIndex = 0;
/* 1966:     */    }
/* 1967:1967 */    for (int i = startIndex; i < array.length; i++) {
/* 1968:1968 */      if (valueToFind == array[i]) {
/* 1969:1969 */        return i;
/* 1970:     */      }
/* 1971:     */    }
/* 1972:1972 */    return -1;
/* 1973:     */  }
/* 1974:     */  
/* 1984:     */  public static int lastIndexOf(short[] array, short valueToFind)
/* 1985:     */  {
/* 1986:1986 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 1987:     */  }
/* 1988:     */  
/* 2002:     */  public static int lastIndexOf(short[] array, short valueToFind, int startIndex)
/* 2003:     */  {
/* 2004:2004 */    if (array == null) {
/* 2005:2005 */      return -1;
/* 2006:     */    }
/* 2007:2007 */    if (startIndex < 0)
/* 2008:2008 */      return -1;
/* 2009:2009 */    if (startIndex >= array.length) {
/* 2010:2010 */      startIndex = array.length - 1;
/* 2011:     */    }
/* 2012:2012 */    for (int i = startIndex; i >= 0; i--) {
/* 2013:2013 */      if (valueToFind == array[i]) {
/* 2014:2014 */        return i;
/* 2015:     */      }
/* 2016:     */    }
/* 2017:2017 */    return -1;
/* 2018:     */  }
/* 2019:     */  
/* 2028:     */  public static boolean contains(short[] array, short valueToFind)
/* 2029:     */  {
/* 2030:2030 */    return indexOf(array, valueToFind) != -1;
/* 2031:     */  }
/* 2032:     */  
/* 2045:     */  public static int indexOf(char[] array, char valueToFind)
/* 2046:     */  {
/* 2047:2047 */    return indexOf(array, valueToFind, 0);
/* 2048:     */  }
/* 2049:     */  
/* 2064:     */  public static int indexOf(char[] array, char valueToFind, int startIndex)
/* 2065:     */  {
/* 2066:2066 */    if (array == null) {
/* 2067:2067 */      return -1;
/* 2068:     */    }
/* 2069:2069 */    if (startIndex < 0) {
/* 2070:2070 */      startIndex = 0;
/* 2071:     */    }
/* 2072:2072 */    for (int i = startIndex; i < array.length; i++) {
/* 2073:2073 */      if (valueToFind == array[i]) {
/* 2074:2074 */        return i;
/* 2075:     */      }
/* 2076:     */    }
/* 2077:2077 */    return -1;
/* 2078:     */  }
/* 2079:     */  
/* 2090:     */  public static int lastIndexOf(char[] array, char valueToFind)
/* 2091:     */  {
/* 2092:2092 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 2093:     */  }
/* 2094:     */  
/* 2109:     */  public static int lastIndexOf(char[] array, char valueToFind, int startIndex)
/* 2110:     */  {
/* 2111:2111 */    if (array == null) {
/* 2112:2112 */      return -1;
/* 2113:     */    }
/* 2114:2114 */    if (startIndex < 0)
/* 2115:2115 */      return -1;
/* 2116:2116 */    if (startIndex >= array.length) {
/* 2117:2117 */      startIndex = array.length - 1;
/* 2118:     */    }
/* 2119:2119 */    for (int i = startIndex; i >= 0; i--) {
/* 2120:2120 */      if (valueToFind == array[i]) {
/* 2121:2121 */        return i;
/* 2122:     */      }
/* 2123:     */    }
/* 2124:2124 */    return -1;
/* 2125:     */  }
/* 2126:     */  
/* 2136:     */  public static boolean contains(char[] array, char valueToFind)
/* 2137:     */  {
/* 2138:2138 */    return indexOf(array, valueToFind) != -1;
/* 2139:     */  }
/* 2140:     */  
/* 2152:     */  public static int indexOf(byte[] array, byte valueToFind)
/* 2153:     */  {
/* 2154:2154 */    return indexOf(array, valueToFind, 0);
/* 2155:     */  }
/* 2156:     */  
/* 2170:     */  public static int indexOf(byte[] array, byte valueToFind, int startIndex)
/* 2171:     */  {
/* 2172:2172 */    if (array == null) {
/* 2173:2173 */      return -1;
/* 2174:     */    }
/* 2175:2175 */    if (startIndex < 0) {
/* 2176:2176 */      startIndex = 0;
/* 2177:     */    }
/* 2178:2178 */    for (int i = startIndex; i < array.length; i++) {
/* 2179:2179 */      if (valueToFind == array[i]) {
/* 2180:2180 */        return i;
/* 2181:     */      }
/* 2182:     */    }
/* 2183:2183 */    return -1;
/* 2184:     */  }
/* 2185:     */  
/* 2195:     */  public static int lastIndexOf(byte[] array, byte valueToFind)
/* 2196:     */  {
/* 2197:2197 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 2198:     */  }
/* 2199:     */  
/* 2213:     */  public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex)
/* 2214:     */  {
/* 2215:2215 */    if (array == null) {
/* 2216:2216 */      return -1;
/* 2217:     */    }
/* 2218:2218 */    if (startIndex < 0)
/* 2219:2219 */      return -1;
/* 2220:2220 */    if (startIndex >= array.length) {
/* 2221:2221 */      startIndex = array.length - 1;
/* 2222:     */    }
/* 2223:2223 */    for (int i = startIndex; i >= 0; i--) {
/* 2224:2224 */      if (valueToFind == array[i]) {
/* 2225:2225 */        return i;
/* 2226:     */      }
/* 2227:     */    }
/* 2228:2228 */    return -1;
/* 2229:     */  }
/* 2230:     */  
/* 2239:     */  public static boolean contains(byte[] array, byte valueToFind)
/* 2240:     */  {
/* 2241:2241 */    return indexOf(array, valueToFind) != -1;
/* 2242:     */  }
/* 2243:     */  
/* 2255:     */  public static int indexOf(double[] array, double valueToFind)
/* 2256:     */  {
/* 2257:2257 */    return indexOf(array, valueToFind, 0);
/* 2258:     */  }
/* 2259:     */  
/* 2272:     */  public static int indexOf(double[] array, double valueToFind, double tolerance)
/* 2273:     */  {
/* 2274:2274 */    return indexOf(array, valueToFind, 0, tolerance);
/* 2275:     */  }
/* 2276:     */  
/* 2290:     */  public static int indexOf(double[] array, double valueToFind, int startIndex)
/* 2291:     */  {
/* 2292:2292 */    if (isEmpty(array)) {
/* 2293:2293 */      return -1;
/* 2294:     */    }
/* 2295:2295 */    if (startIndex < 0) {
/* 2296:2296 */      startIndex = 0;
/* 2297:     */    }
/* 2298:2298 */    for (int i = startIndex; i < array.length; i++) {
/* 2299:2299 */      if (valueToFind == array[i]) {
/* 2300:2300 */        return i;
/* 2301:     */      }
/* 2302:     */    }
/* 2303:2303 */    return -1;
/* 2304:     */  }
/* 2305:     */  
/* 2322:     */  public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance)
/* 2323:     */  {
/* 2324:2324 */    if (isEmpty(array)) {
/* 2325:2325 */      return -1;
/* 2326:     */    }
/* 2327:2327 */    if (startIndex < 0) {
/* 2328:2328 */      startIndex = 0;
/* 2329:     */    }
/* 2330:2330 */    double min = valueToFind - tolerance;
/* 2331:2331 */    double max = valueToFind + tolerance;
/* 2332:2332 */    for (int i = startIndex; i < array.length; i++) {
/* 2333:2333 */      if ((array[i] >= min) && (array[i] <= max)) {
/* 2334:2334 */        return i;
/* 2335:     */      }
/* 2336:     */    }
/* 2337:2337 */    return -1;
/* 2338:     */  }
/* 2339:     */  
/* 2349:     */  public static int lastIndexOf(double[] array, double valueToFind)
/* 2350:     */  {
/* 2351:2351 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 2352:     */  }
/* 2353:     */  
/* 2366:     */  public static int lastIndexOf(double[] array, double valueToFind, double tolerance)
/* 2367:     */  {
/* 2368:2368 */    return lastIndexOf(array, valueToFind, 2147483647, tolerance);
/* 2369:     */  }
/* 2370:     */  
/* 2384:     */  public static int lastIndexOf(double[] array, double valueToFind, int startIndex)
/* 2385:     */  {
/* 2386:2386 */    if (isEmpty(array)) {
/* 2387:2387 */      return -1;
/* 2388:     */    }
/* 2389:2389 */    if (startIndex < 0)
/* 2390:2390 */      return -1;
/* 2391:2391 */    if (startIndex >= array.length) {
/* 2392:2392 */      startIndex = array.length - 1;
/* 2393:     */    }
/* 2394:2394 */    for (int i = startIndex; i >= 0; i--) {
/* 2395:2395 */      if (valueToFind == array[i]) {
/* 2396:2396 */        return i;
/* 2397:     */      }
/* 2398:     */    }
/* 2399:2399 */    return -1;
/* 2400:     */  }
/* 2401:     */  
/* 2418:     */  public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance)
/* 2419:     */  {
/* 2420:2420 */    if (isEmpty(array)) {
/* 2421:2421 */      return -1;
/* 2422:     */    }
/* 2423:2423 */    if (startIndex < 0)
/* 2424:2424 */      return -1;
/* 2425:2425 */    if (startIndex >= array.length) {
/* 2426:2426 */      startIndex = array.length - 1;
/* 2427:     */    }
/* 2428:2428 */    double min = valueToFind - tolerance;
/* 2429:2429 */    double max = valueToFind + tolerance;
/* 2430:2430 */    for (int i = startIndex; i >= 0; i--) {
/* 2431:2431 */      if ((array[i] >= min) && (array[i] <= max)) {
/* 2432:2432 */        return i;
/* 2433:     */      }
/* 2434:     */    }
/* 2435:2435 */    return -1;
/* 2436:     */  }
/* 2437:     */  
/* 2446:     */  public static boolean contains(double[] array, double valueToFind)
/* 2447:     */  {
/* 2448:2448 */    return indexOf(array, valueToFind) != -1;
/* 2449:     */  }
/* 2450:     */  
/* 2463:     */  public static boolean contains(double[] array, double valueToFind, double tolerance)
/* 2464:     */  {
/* 2465:2465 */    return indexOf(array, valueToFind, 0, tolerance) != -1;
/* 2466:     */  }
/* 2467:     */  
/* 2479:     */  public static int indexOf(float[] array, float valueToFind)
/* 2480:     */  {
/* 2481:2481 */    return indexOf(array, valueToFind, 0);
/* 2482:     */  }
/* 2483:     */  
/* 2497:     */  public static int indexOf(float[] array, float valueToFind, int startIndex)
/* 2498:     */  {
/* 2499:2499 */    if (isEmpty(array)) {
/* 2500:2500 */      return -1;
/* 2501:     */    }
/* 2502:2502 */    if (startIndex < 0) {
/* 2503:2503 */      startIndex = 0;
/* 2504:     */    }
/* 2505:2505 */    for (int i = startIndex; i < array.length; i++) {
/* 2506:2506 */      if (valueToFind == array[i]) {
/* 2507:2507 */        return i;
/* 2508:     */      }
/* 2509:     */    }
/* 2510:2510 */    return -1;
/* 2511:     */  }
/* 2512:     */  
/* 2522:     */  public static int lastIndexOf(float[] array, float valueToFind)
/* 2523:     */  {
/* 2524:2524 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 2525:     */  }
/* 2526:     */  
/* 2540:     */  public static int lastIndexOf(float[] array, float valueToFind, int startIndex)
/* 2541:     */  {
/* 2542:2542 */    if (isEmpty(array)) {
/* 2543:2543 */      return -1;
/* 2544:     */    }
/* 2545:2545 */    if (startIndex < 0)
/* 2546:2546 */      return -1;
/* 2547:2547 */    if (startIndex >= array.length) {
/* 2548:2548 */      startIndex = array.length - 1;
/* 2549:     */    }
/* 2550:2550 */    for (int i = startIndex; i >= 0; i--) {
/* 2551:2551 */      if (valueToFind == array[i]) {
/* 2552:2552 */        return i;
/* 2553:     */      }
/* 2554:     */    }
/* 2555:2555 */    return -1;
/* 2556:     */  }
/* 2557:     */  
/* 2566:     */  public static boolean contains(float[] array, float valueToFind)
/* 2567:     */  {
/* 2568:2568 */    return indexOf(array, valueToFind) != -1;
/* 2569:     */  }
/* 2570:     */  
/* 2582:     */  public static int indexOf(boolean[] array, boolean valueToFind)
/* 2583:     */  {
/* 2584:2584 */    return indexOf(array, valueToFind, 0);
/* 2585:     */  }
/* 2586:     */  
/* 2601:     */  public static int indexOf(boolean[] array, boolean valueToFind, int startIndex)
/* 2602:     */  {
/* 2603:2603 */    if (isEmpty(array)) {
/* 2604:2604 */      return -1;
/* 2605:     */    }
/* 2606:2606 */    if (startIndex < 0) {
/* 2607:2607 */      startIndex = 0;
/* 2608:     */    }
/* 2609:2609 */    for (int i = startIndex; i < array.length; i++) {
/* 2610:2610 */      if (valueToFind == array[i]) {
/* 2611:2611 */        return i;
/* 2612:     */      }
/* 2613:     */    }
/* 2614:2614 */    return -1;
/* 2615:     */  }
/* 2616:     */  
/* 2627:     */  public static int lastIndexOf(boolean[] array, boolean valueToFind)
/* 2628:     */  {
/* 2629:2629 */    return lastIndexOf(array, valueToFind, 2147483647);
/* 2630:     */  }
/* 2631:     */  
/* 2645:     */  public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex)
/* 2646:     */  {
/* 2647:2647 */    if (isEmpty(array)) {
/* 2648:2648 */      return -1;
/* 2649:     */    }
/* 2650:2650 */    if (startIndex < 0)
/* 2651:2651 */      return -1;
/* 2652:2652 */    if (startIndex >= array.length) {
/* 2653:2653 */      startIndex = array.length - 1;
/* 2654:     */    }
/* 2655:2655 */    for (int i = startIndex; i >= 0; i--) {
/* 2656:2656 */      if (valueToFind == array[i]) {
/* 2657:2657 */        return i;
/* 2658:     */      }
/* 2659:     */    }
/* 2660:2660 */    return -1;
/* 2661:     */  }
/* 2662:     */  
/* 2671:     */  public static boolean contains(boolean[] array, boolean valueToFind)
/* 2672:     */  {
/* 2673:2673 */    return indexOf(array, valueToFind) != -1;
/* 2674:     */  }
/* 2675:     */  
/* 2689:     */  public static char[] toPrimitive(Character[] array)
/* 2690:     */  {
/* 2691:2691 */    if (array == null)
/* 2692:2692 */      return null;
/* 2693:2693 */    if (array.length == 0) {
/* 2694:2694 */      return EMPTY_CHAR_ARRAY;
/* 2695:     */    }
/* 2696:2696 */    char[] result = new char[array.length];
/* 2697:2697 */    for (int i = 0; i < array.length; i++) {
/* 2698:2698 */      result[i] = array[i].charValue();
/* 2699:     */    }
/* 2700:2700 */    return result;
/* 2701:     */  }
/* 2702:     */  
/* 2711:     */  public static char[] toPrimitive(Character[] array, char valueForNull)
/* 2712:     */  {
/* 2713:2713 */    if (array == null)
/* 2714:2714 */      return null;
/* 2715:2715 */    if (array.length == 0) {
/* 2716:2716 */      return EMPTY_CHAR_ARRAY;
/* 2717:     */    }
/* 2718:2718 */    char[] result = new char[array.length];
/* 2719:2719 */    for (int i = 0; i < array.length; i++) {
/* 2720:2720 */      Character b = array[i];
/* 2721:2721 */      result[i] = (b == null ? valueForNull : b.charValue());
/* 2722:     */    }
/* 2723:2723 */    return result;
/* 2724:     */  }
/* 2725:     */  
/* 2733:     */  public static Character[] toObject(char[] array)
/* 2734:     */  {
/* 2735:2735 */    if (array == null)
/* 2736:2736 */      return null;
/* 2737:2737 */    if (array.length == 0) {
/* 2738:2738 */      return EMPTY_CHARACTER_OBJECT_ARRAY;
/* 2739:     */    }
/* 2740:2740 */    Character[] result = new Character[array.length];
/* 2741:2741 */    for (int i = 0; i < array.length; i++) {
/* 2742:2742 */      result[i] = Character.valueOf(array[i]);
/* 2743:     */    }
/* 2744:2744 */    return result;
/* 2745:     */  }
/* 2746:     */  
/* 2757:     */  public static long[] toPrimitive(Long[] array)
/* 2758:     */  {
/* 2759:2759 */    if (array == null)
/* 2760:2760 */      return null;
/* 2761:2761 */    if (array.length == 0) {
/* 2762:2762 */      return EMPTY_LONG_ARRAY;
/* 2763:     */    }
/* 2764:2764 */    long[] result = new long[array.length];
/* 2765:2765 */    for (int i = 0; i < array.length; i++) {
/* 2766:2766 */      result[i] = array[i].longValue();
/* 2767:     */    }
/* 2768:2768 */    return result;
/* 2769:     */  }
/* 2770:     */  
/* 2779:     */  public static long[] toPrimitive(Long[] array, long valueForNull)
/* 2780:     */  {
/* 2781:2781 */    if (array == null)
/* 2782:2782 */      return null;
/* 2783:2783 */    if (array.length == 0) {
/* 2784:2784 */      return EMPTY_LONG_ARRAY;
/* 2785:     */    }
/* 2786:2786 */    long[] result = new long[array.length];
/* 2787:2787 */    for (int i = 0; i < array.length; i++) {
/* 2788:2788 */      Long b = array[i];
/* 2789:2789 */      result[i] = (b == null ? valueForNull : b.longValue());
/* 2790:     */    }
/* 2791:2791 */    return result;
/* 2792:     */  }
/* 2793:     */  
/* 2801:     */  public static Long[] toObject(long[] array)
/* 2802:     */  {
/* 2803:2803 */    if (array == null)
/* 2804:2804 */      return null;
/* 2805:2805 */    if (array.length == 0) {
/* 2806:2806 */      return EMPTY_LONG_OBJECT_ARRAY;
/* 2807:     */    }
/* 2808:2808 */    Long[] result = new Long[array.length];
/* 2809:2809 */    for (int i = 0; i < array.length; i++) {
/* 2810:2810 */      result[i] = Long.valueOf(array[i]);
/* 2811:     */    }
/* 2812:2812 */    return result;
/* 2813:     */  }
/* 2814:     */  
/* 2825:     */  public static int[] toPrimitive(Integer[] array)
/* 2826:     */  {
/* 2827:2827 */    if (array == null)
/* 2828:2828 */      return null;
/* 2829:2829 */    if (array.length == 0) {
/* 2830:2830 */      return EMPTY_INT_ARRAY;
/* 2831:     */    }
/* 2832:2832 */    int[] result = new int[array.length];
/* 2833:2833 */    for (int i = 0; i < array.length; i++) {
/* 2834:2834 */      result[i] = array[i].intValue();
/* 2835:     */    }
/* 2836:2836 */    return result;
/* 2837:     */  }
/* 2838:     */  
/* 2847:     */  public static int[] toPrimitive(Integer[] array, int valueForNull)
/* 2848:     */  {
/* 2849:2849 */    if (array == null)
/* 2850:2850 */      return null;
/* 2851:2851 */    if (array.length == 0) {
/* 2852:2852 */      return EMPTY_INT_ARRAY;
/* 2853:     */    }
/* 2854:2854 */    int[] result = new int[array.length];
/* 2855:2855 */    for (int i = 0; i < array.length; i++) {
/* 2856:2856 */      Integer b = array[i];
/* 2857:2857 */      result[i] = (b == null ? valueForNull : b.intValue());
/* 2858:     */    }
/* 2859:2859 */    return result;
/* 2860:     */  }
/* 2861:     */  
/* 2869:     */  public static Integer[] toObject(int[] array)
/* 2870:     */  {
/* 2871:2871 */    if (array == null)
/* 2872:2872 */      return null;
/* 2873:2873 */    if (array.length == 0) {
/* 2874:2874 */      return EMPTY_INTEGER_OBJECT_ARRAY;
/* 2875:     */    }
/* 2876:2876 */    Integer[] result = new Integer[array.length];
/* 2877:2877 */    for (int i = 0; i < array.length; i++) {
/* 2878:2878 */      result[i] = Integer.valueOf(array[i]);
/* 2879:     */    }
/* 2880:2880 */    return result;
/* 2881:     */  }
/* 2882:     */  
/* 2893:     */  public static short[] toPrimitive(Short[] array)
/* 2894:     */  {
/* 2895:2895 */    if (array == null)
/* 2896:2896 */      return null;
/* 2897:2897 */    if (array.length == 0) {
/* 2898:2898 */      return EMPTY_SHORT_ARRAY;
/* 2899:     */    }
/* 2900:2900 */    short[] result = new short[array.length];
/* 2901:2901 */    for (int i = 0; i < array.length; i++) {
/* 2902:2902 */      result[i] = array[i].shortValue();
/* 2903:     */    }
/* 2904:2904 */    return result;
/* 2905:     */  }
/* 2906:     */  
/* 2915:     */  public static short[] toPrimitive(Short[] array, short valueForNull)
/* 2916:     */  {
/* 2917:2917 */    if (array == null)
/* 2918:2918 */      return null;
/* 2919:2919 */    if (array.length == 0) {
/* 2920:2920 */      return EMPTY_SHORT_ARRAY;
/* 2921:     */    }
/* 2922:2922 */    short[] result = new short[array.length];
/* 2923:2923 */    for (int i = 0; i < array.length; i++) {
/* 2924:2924 */      Short b = array[i];
/* 2925:2925 */      result[i] = (b == null ? valueForNull : b.shortValue());
/* 2926:     */    }
/* 2927:2927 */    return result;
/* 2928:     */  }
/* 2929:     */  
/* 2937:     */  public static Short[] toObject(short[] array)
/* 2938:     */  {
/* 2939:2939 */    if (array == null)
/* 2940:2940 */      return null;
/* 2941:2941 */    if (array.length == 0) {
/* 2942:2942 */      return EMPTY_SHORT_OBJECT_ARRAY;
/* 2943:     */    }
/* 2944:2944 */    Short[] result = new Short[array.length];
/* 2945:2945 */    for (int i = 0; i < array.length; i++) {
/* 2946:2946 */      result[i] = Short.valueOf(array[i]);
/* 2947:     */    }
/* 2948:2948 */    return result;
/* 2949:     */  }
/* 2950:     */  
/* 2961:     */  public static byte[] toPrimitive(Byte[] array)
/* 2962:     */  {
/* 2963:2963 */    if (array == null)
/* 2964:2964 */      return null;
/* 2965:2965 */    if (array.length == 0) {
/* 2966:2966 */      return EMPTY_BYTE_ARRAY;
/* 2967:     */    }
/* 2968:2968 */    byte[] result = new byte[array.length];
/* 2969:2969 */    for (int i = 0; i < array.length; i++) {
/* 2970:2970 */      result[i] = array[i].byteValue();
/* 2971:     */    }
/* 2972:2972 */    return result;
/* 2973:     */  }
/* 2974:     */  
/* 2983:     */  public static byte[] toPrimitive(Byte[] array, byte valueForNull)
/* 2984:     */  {
/* 2985:2985 */    if (array == null)
/* 2986:2986 */      return null;
/* 2987:2987 */    if (array.length == 0) {
/* 2988:2988 */      return EMPTY_BYTE_ARRAY;
/* 2989:     */    }
/* 2990:2990 */    byte[] result = new byte[array.length];
/* 2991:2991 */    for (int i = 0; i < array.length; i++) {
/* 2992:2992 */      Byte b = array[i];
/* 2993:2993 */      result[i] = (b == null ? valueForNull : b.byteValue());
/* 2994:     */    }
/* 2995:2995 */    return result;
/* 2996:     */  }
/* 2997:     */  
/* 3005:     */  public static Byte[] toObject(byte[] array)
/* 3006:     */  {
/* 3007:3007 */    if (array == null)
/* 3008:3008 */      return null;
/* 3009:3009 */    if (array.length == 0) {
/* 3010:3010 */      return EMPTY_BYTE_OBJECT_ARRAY;
/* 3011:     */    }
/* 3012:3012 */    Byte[] result = new Byte[array.length];
/* 3013:3013 */    for (int i = 0; i < array.length; i++) {
/* 3014:3014 */      result[i] = Byte.valueOf(array[i]);
/* 3015:     */    }
/* 3016:3016 */    return result;
/* 3017:     */  }
/* 3018:     */  
/* 3029:     */  public static double[] toPrimitive(Double[] array)
/* 3030:     */  {
/* 3031:3031 */    if (array == null)
/* 3032:3032 */      return null;
/* 3033:3033 */    if (array.length == 0) {
/* 3034:3034 */      return EMPTY_DOUBLE_ARRAY;
/* 3035:     */    }
/* 3036:3036 */    double[] result = new double[array.length];
/* 3037:3037 */    for (int i = 0; i < array.length; i++) {
/* 3038:3038 */      result[i] = array[i].doubleValue();
/* 3039:     */    }
/* 3040:3040 */    return result;
/* 3041:     */  }
/* 3042:     */  
/* 3051:     */  public static double[] toPrimitive(Double[] array, double valueForNull)
/* 3052:     */  {
/* 3053:3053 */    if (array == null)
/* 3054:3054 */      return null;
/* 3055:3055 */    if (array.length == 0) {
/* 3056:3056 */      return EMPTY_DOUBLE_ARRAY;
/* 3057:     */    }
/* 3058:3058 */    double[] result = new double[array.length];
/* 3059:3059 */    for (int i = 0; i < array.length; i++) {
/* 3060:3060 */      Double b = array[i];
/* 3061:3061 */      result[i] = (b == null ? valueForNull : b.doubleValue());
/* 3062:     */    }
/* 3063:3063 */    return result;
/* 3064:     */  }
/* 3065:     */  
/* 3073:     */  public static Double[] toObject(double[] array)
/* 3074:     */  {
/* 3075:3075 */    if (array == null)
/* 3076:3076 */      return null;
/* 3077:3077 */    if (array.length == 0) {
/* 3078:3078 */      return EMPTY_DOUBLE_OBJECT_ARRAY;
/* 3079:     */    }
/* 3080:3080 */    Double[] result = new Double[array.length];
/* 3081:3081 */    for (int i = 0; i < array.length; i++) {
/* 3082:3082 */      result[i] = Double.valueOf(array[i]);
/* 3083:     */    }
/* 3084:3084 */    return result;
/* 3085:     */  }
/* 3086:     */  
/* 3097:     */  public static float[] toPrimitive(Float[] array)
/* 3098:     */  {
/* 3099:3099 */    if (array == null)
/* 3100:3100 */      return null;
/* 3101:3101 */    if (array.length == 0) {
/* 3102:3102 */      return EMPTY_FLOAT_ARRAY;
/* 3103:     */    }
/* 3104:3104 */    float[] result = new float[array.length];
/* 3105:3105 */    for (int i = 0; i < array.length; i++) {
/* 3106:3106 */      result[i] = array[i].floatValue();
/* 3107:     */    }
/* 3108:3108 */    return result;
/* 3109:     */  }
/* 3110:     */  
/* 3119:     */  public static float[] toPrimitive(Float[] array, float valueForNull)
/* 3120:     */  {
/* 3121:3121 */    if (array == null)
/* 3122:3122 */      return null;
/* 3123:3123 */    if (array.length == 0) {
/* 3124:3124 */      return EMPTY_FLOAT_ARRAY;
/* 3125:     */    }
/* 3126:3126 */    float[] result = new float[array.length];
/* 3127:3127 */    for (int i = 0; i < array.length; i++) {
/* 3128:3128 */      Float b = array[i];
/* 3129:3129 */      result[i] = (b == null ? valueForNull : b.floatValue());
/* 3130:     */    }
/* 3131:3131 */    return result;
/* 3132:     */  }
/* 3133:     */  
/* 3141:     */  public static Float[] toObject(float[] array)
/* 3142:     */  {
/* 3143:3143 */    if (array == null)
/* 3144:3144 */      return null;
/* 3145:3145 */    if (array.length == 0) {
/* 3146:3146 */      return EMPTY_FLOAT_OBJECT_ARRAY;
/* 3147:     */    }
/* 3148:3148 */    Float[] result = new Float[array.length];
/* 3149:3149 */    for (int i = 0; i < array.length; i++) {
/* 3150:3150 */      result[i] = Float.valueOf(array[i]);
/* 3151:     */    }
/* 3152:3152 */    return result;
/* 3153:     */  }
/* 3154:     */  
/* 3165:     */  public static boolean[] toPrimitive(Boolean[] array)
/* 3166:     */  {
/* 3167:3167 */    if (array == null)
/* 3168:3168 */      return null;
/* 3169:3169 */    if (array.length == 0) {
/* 3170:3170 */      return EMPTY_BOOLEAN_ARRAY;
/* 3171:     */    }
/* 3172:3172 */    boolean[] result = new boolean[array.length];
/* 3173:3173 */    for (int i = 0; i < array.length; i++) {
/* 3174:3174 */      result[i] = array[i].booleanValue();
/* 3175:     */    }
/* 3176:3176 */    return result;
/* 3177:     */  }
/* 3178:     */  
/* 3187:     */  public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull)
/* 3188:     */  {
/* 3189:3189 */    if (array == null)
/* 3190:3190 */      return null;
/* 3191:3191 */    if (array.length == 0) {
/* 3192:3192 */      return EMPTY_BOOLEAN_ARRAY;
/* 3193:     */    }
/* 3194:3194 */    boolean[] result = new boolean[array.length];
/* 3195:3195 */    for (int i = 0; i < array.length; i++) {
/* 3196:3196 */      Boolean b = array[i];
/* 3197:3197 */      result[i] = (b == null ? valueForNull : b.booleanValue());
/* 3198:     */    }
/* 3199:3199 */    return result;
/* 3200:     */  }
/* 3201:     */  
/* 3209:     */  public static Boolean[] toObject(boolean[] array)
/* 3210:     */  {
/* 3211:3211 */    if (array == null)
/* 3212:3212 */      return null;
/* 3213:3213 */    if (array.length == 0) {
/* 3214:3214 */      return EMPTY_BOOLEAN_OBJECT_ARRAY;
/* 3215:     */    }
/* 3216:3216 */    Boolean[] result = new Boolean[array.length];
/* 3217:3217 */    for (int i = 0; i < array.length; i++) {
/* 3218:3218 */      result[i] = (array[i] != 0 ? Boolean.TRUE : Boolean.FALSE);
/* 3219:     */    }
/* 3220:3220 */    return result;
/* 3221:     */  }
/* 3222:     */  
/* 3230:     */  public static boolean isEmpty(Object[] array)
/* 3231:     */  {
/* 3232:3232 */    return (array == null) || (array.length == 0);
/* 3233:     */  }
/* 3234:     */  
/* 3241:     */  public static boolean isEmpty(long[] array)
/* 3242:     */  {
/* 3243:3243 */    return (array == null) || (array.length == 0);
/* 3244:     */  }
/* 3245:     */  
/* 3252:     */  public static boolean isEmpty(int[] array)
/* 3253:     */  {
/* 3254:3254 */    return (array == null) || (array.length == 0);
/* 3255:     */  }
/* 3256:     */  
/* 3263:     */  public static boolean isEmpty(short[] array)
/* 3264:     */  {
/* 3265:3265 */    return (array == null) || (array.length == 0);
/* 3266:     */  }
/* 3267:     */  
/* 3274:     */  public static boolean isEmpty(char[] array)
/* 3275:     */  {
/* 3276:3276 */    return (array == null) || (array.length == 0);
/* 3277:     */  }
/* 3278:     */  
/* 3285:     */  public static boolean isEmpty(byte[] array)
/* 3286:     */  {
/* 3287:3287 */    return (array == null) || (array.length == 0);
/* 3288:     */  }
/* 3289:     */  
/* 3296:     */  public static boolean isEmpty(double[] array)
/* 3297:     */  {
/* 3298:3298 */    return (array == null) || (array.length == 0);
/* 3299:     */  }
/* 3300:     */  
/* 3307:     */  public static boolean isEmpty(float[] array)
/* 3308:     */  {
/* 3309:3309 */    return (array == null) || (array.length == 0);
/* 3310:     */  }
/* 3311:     */  
/* 3318:     */  public static boolean isEmpty(boolean[] array)
/* 3319:     */  {
/* 3320:3320 */    return (array == null) || (array.length == 0);
/* 3321:     */  }
/* 3322:     */  
/* 3331:     */  public static <T> boolean isNotEmpty(T[] array)
/* 3332:     */  {
/* 3333:3333 */    return (array != null) && (array.length != 0);
/* 3334:     */  }
/* 3335:     */  
/* 3342:     */  public static boolean isNotEmpty(long[] array)
/* 3343:     */  {
/* 3344:3344 */    return (array != null) && (array.length != 0);
/* 3345:     */  }
/* 3346:     */  
/* 3353:     */  public static boolean isNotEmpty(int[] array)
/* 3354:     */  {
/* 3355:3355 */    return (array != null) && (array.length != 0);
/* 3356:     */  }
/* 3357:     */  
/* 3364:     */  public static boolean isNotEmpty(short[] array)
/* 3365:     */  {
/* 3366:3366 */    return (array != null) && (array.length != 0);
/* 3367:     */  }
/* 3368:     */  
/* 3375:     */  public static boolean isNotEmpty(char[] array)
/* 3376:     */  {
/* 3377:3377 */    return (array != null) && (array.length != 0);
/* 3378:     */  }
/* 3379:     */  
/* 3386:     */  public static boolean isNotEmpty(byte[] array)
/* 3387:     */  {
/* 3388:3388 */    return (array != null) && (array.length != 0);
/* 3389:     */  }
/* 3390:     */  
/* 3397:     */  public static boolean isNotEmpty(double[] array)
/* 3398:     */  {
/* 3399:3399 */    return (array != null) && (array.length != 0);
/* 3400:     */  }
/* 3401:     */  
/* 3408:     */  public static boolean isNotEmpty(float[] array)
/* 3409:     */  {
/* 3410:3410 */    return (array != null) && (array.length != 0);
/* 3411:     */  }
/* 3412:     */  
/* 3419:     */  public static boolean isNotEmpty(boolean[] array)
/* 3420:     */  {
/* 3421:3421 */    return (array != null) && (array.length != 0);
/* 3422:     */  }
/* 3423:     */  
/* 3447:     */  public static <T> T[] addAll(T[] array1, T... array2)
/* 3448:     */  {
/* 3449:3449 */    if (array1 == null)
/* 3450:3450 */      return clone(array2);
/* 3451:3451 */    if (array2 == null) {
/* 3452:3452 */      return clone(array1);
/* 3453:     */    }
/* 3454:3454 */    Class<?> type1 = array1.getClass().getComponentType();
/* 3455:     */    
/* 3456:3456 */    T[] joinedArray = (Object[])Array.newInstance(type1, array1.length + array2.length);
/* 3457:3457 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3458:     */    try {
/* 3459:3459 */      System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3462:     */    }
/* 3463:     */    catch (ArrayStoreException ase)
/* 3464:     */    {
/* 3467:3467 */      Class<?> type2 = array2.getClass().getComponentType();
/* 3468:3468 */      if (!type1.isAssignableFrom(type2)) {
/* 3469:3469 */        throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), ase);
/* 3470:     */      }
/* 3471:     */      
/* 3472:3472 */      throw ase;
/* 3473:     */    }
/* 3474:3474 */    return joinedArray;
/* 3475:     */  }
/* 3476:     */  
/* 3493:     */  public static boolean[] addAll(boolean[] array1, boolean... array2)
/* 3494:     */  {
/* 3495:3495 */    if (array1 == null)
/* 3496:3496 */      return clone(array2);
/* 3497:3497 */    if (array2 == null) {
/* 3498:3498 */      return clone(array1);
/* 3499:     */    }
/* 3500:3500 */    boolean[] joinedArray = new boolean[array1.length + array2.length];
/* 3501:3501 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3502:3502 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3503:3503 */    return joinedArray;
/* 3504:     */  }
/* 3505:     */  
/* 3522:     */  public static char[] addAll(char[] array1, char... array2)
/* 3523:     */  {
/* 3524:3524 */    if (array1 == null)
/* 3525:3525 */      return clone(array2);
/* 3526:3526 */    if (array2 == null) {
/* 3527:3527 */      return clone(array1);
/* 3528:     */    }
/* 3529:3529 */    char[] joinedArray = new char[array1.length + array2.length];
/* 3530:3530 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3531:3531 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3532:3532 */    return joinedArray;
/* 3533:     */  }
/* 3534:     */  
/* 3551:     */  public static byte[] addAll(byte[] array1, byte... array2)
/* 3552:     */  {
/* 3553:3553 */    if (array1 == null)
/* 3554:3554 */      return clone(array2);
/* 3555:3555 */    if (array2 == null) {
/* 3556:3556 */      return clone(array1);
/* 3557:     */    }
/* 3558:3558 */    byte[] joinedArray = new byte[array1.length + array2.length];
/* 3559:3559 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3560:3560 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3561:3561 */    return joinedArray;
/* 3562:     */  }
/* 3563:     */  
/* 3580:     */  public static short[] addAll(short[] array1, short... array2)
/* 3581:     */  {
/* 3582:3582 */    if (array1 == null)
/* 3583:3583 */      return clone(array2);
/* 3584:3584 */    if (array2 == null) {
/* 3585:3585 */      return clone(array1);
/* 3586:     */    }
/* 3587:3587 */    short[] joinedArray = new short[array1.length + array2.length];
/* 3588:3588 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3589:3589 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3590:3590 */    return joinedArray;
/* 3591:     */  }
/* 3592:     */  
/* 3609:     */  public static int[] addAll(int[] array1, int... array2)
/* 3610:     */  {
/* 3611:3611 */    if (array1 == null)
/* 3612:3612 */      return clone(array2);
/* 3613:3613 */    if (array2 == null) {
/* 3614:3614 */      return clone(array1);
/* 3615:     */    }
/* 3616:3616 */    int[] joinedArray = new int[array1.length + array2.length];
/* 3617:3617 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3618:3618 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3619:3619 */    return joinedArray;
/* 3620:     */  }
/* 3621:     */  
/* 3638:     */  public static long[] addAll(long[] array1, long... array2)
/* 3639:     */  {
/* 3640:3640 */    if (array1 == null)
/* 3641:3641 */      return clone(array2);
/* 3642:3642 */    if (array2 == null) {
/* 3643:3643 */      return clone(array1);
/* 3644:     */    }
/* 3645:3645 */    long[] joinedArray = new long[array1.length + array2.length];
/* 3646:3646 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3647:3647 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3648:3648 */    return joinedArray;
/* 3649:     */  }
/* 3650:     */  
/* 3667:     */  public static float[] addAll(float[] array1, float... array2)
/* 3668:     */  {
/* 3669:3669 */    if (array1 == null)
/* 3670:3670 */      return clone(array2);
/* 3671:3671 */    if (array2 == null) {
/* 3672:3672 */      return clone(array1);
/* 3673:     */    }
/* 3674:3674 */    float[] joinedArray = new float[array1.length + array2.length];
/* 3675:3675 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3676:3676 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3677:3677 */    return joinedArray;
/* 3678:     */  }
/* 3679:     */  
/* 3696:     */  public static double[] addAll(double[] array1, double... array2)
/* 3697:     */  {
/* 3698:3698 */    if (array1 == null)
/* 3699:3699 */      return clone(array2);
/* 3700:3700 */    if (array2 == null) {
/* 3701:3701 */      return clone(array1);
/* 3702:     */    }
/* 3703:3703 */    double[] joinedArray = new double[array1.length + array2.length];
/* 3704:3704 */    System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3705:3705 */    System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3706:3706 */    return joinedArray;
/* 3707:     */  }
/* 3708:     */  
/* 3723:     */  public static <T> T[] add(T[] array, T element)
/* 3724:     */  {
/* 3725:     */    Class<?> type;
/* 3726:     */    
/* 3740:3740 */    if (array != null) {
/* 3741:3741 */      type = array.getClass(); } else { Class<?> type;
/* 3742:3742 */      if (element != null) {
/* 3743:3743 */        type = element.getClass();
/* 3744:     */      } else
/* 3745:3745 */        throw new IllegalArgumentException("Arguments cannot both be null");
/* 3746:     */    }
/* 3747:     */    Class<?> type;
/* 3748:3748 */    T[] newArray = (Object[])copyArrayGrow1(array, type);
/* 3749:3749 */    newArray[(newArray.length - 1)] = element;
/* 3750:3750 */    return newArray;
/* 3751:     */  }
/* 3752:     */  
/* 3773:     */  public static boolean[] add(boolean[] array, boolean element)
/* 3774:     */  {
/* 3775:3775 */    boolean[] newArray = (boolean[])copyArrayGrow1(array, Boolean.TYPE);
/* 3776:3776 */    newArray[(newArray.length - 1)] = element;
/* 3777:3777 */    return newArray;
/* 3778:     */  }
/* 3779:     */  
/* 3800:     */  public static byte[] add(byte[] array, byte element)
/* 3801:     */  {
/* 3802:3802 */    byte[] newArray = (byte[])copyArrayGrow1(array, Byte.TYPE);
/* 3803:3803 */    newArray[(newArray.length - 1)] = element;
/* 3804:3804 */    return newArray;
/* 3805:     */  }
/* 3806:     */  
/* 3827:     */  public static char[] add(char[] array, char element)
/* 3828:     */  {
/* 3829:3829 */    char[] newArray = (char[])copyArrayGrow1(array, Character.TYPE);
/* 3830:3830 */    newArray[(newArray.length - 1)] = element;
/* 3831:3831 */    return newArray;
/* 3832:     */  }
/* 3833:     */  
/* 3854:     */  public static double[] add(double[] array, double element)
/* 3855:     */  {
/* 3856:3856 */    double[] newArray = (double[])copyArrayGrow1(array, Double.TYPE);
/* 3857:3857 */    newArray[(newArray.length - 1)] = element;
/* 3858:3858 */    return newArray;
/* 3859:     */  }
/* 3860:     */  
/* 3881:     */  public static float[] add(float[] array, float element)
/* 3882:     */  {
/* 3883:3883 */    float[] newArray = (float[])copyArrayGrow1(array, Float.TYPE);
/* 3884:3884 */    newArray[(newArray.length - 1)] = element;
/* 3885:3885 */    return newArray;
/* 3886:     */  }
/* 3887:     */  
/* 3908:     */  public static int[] add(int[] array, int element)
/* 3909:     */  {
/* 3910:3910 */    int[] newArray = (int[])copyArrayGrow1(array, Integer.TYPE);
/* 3911:3911 */    newArray[(newArray.length - 1)] = element;
/* 3912:3912 */    return newArray;
/* 3913:     */  }
/* 3914:     */  
/* 3935:     */  public static long[] add(long[] array, long element)
/* 3936:     */  {
/* 3937:3937 */    long[] newArray = (long[])copyArrayGrow1(array, Long.TYPE);
/* 3938:3938 */    newArray[(newArray.length - 1)] = element;
/* 3939:3939 */    return newArray;
/* 3940:     */  }
/* 3941:     */  
/* 3962:     */  public static short[] add(short[] array, short element)
/* 3963:     */  {
/* 3964:3964 */    short[] newArray = (short[])copyArrayGrow1(array, Short.TYPE);
/* 3965:3965 */    newArray[(newArray.length - 1)] = element;
/* 3966:3966 */    return newArray;
/* 3967:     */  }
/* 3968:     */  
/* 3977:     */  private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType)
/* 3978:     */  {
/* 3979:3979 */    if (array != null) {
/* 3980:3980 */      int arrayLength = Array.getLength(array);
/* 3981:3981 */      Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
/* 3982:3982 */      System.arraycopy(array, 0, newArray, 0, arrayLength);
/* 3983:3983 */      return newArray;
/* 3984:     */    }
/* 3985:3985 */    return Array.newInstance(newArrayComponentType, 1);
/* 3986:     */  }
/* 3987:     */  
/* 4017:     */  public static <T> T[] add(T[] array, int index, T element)
/* 4018:     */  {
/* 4019:4019 */    Class<?> clss = null;
/* 4020:4020 */    if (array != null) {
/* 4021:4021 */      clss = array.getClass().getComponentType();
/* 4022:4022 */    } else if (element != null) {
/* 4023:4023 */      clss = element.getClass();
/* 4024:     */    } else {
/* 4025:4025 */      throw new IllegalArgumentException("Array and element cannot both be null");
/* 4026:     */    }
/* 4027:     */    
/* 4028:4028 */    T[] newArray = (Object[])add(array, index, element, clss);
/* 4029:4029 */    return newArray;
/* 4030:     */  }
/* 4031:     */  
/* 4058:     */  public static boolean[] add(boolean[] array, int index, boolean element)
/* 4059:     */  {
/* 4060:4060 */    return (boolean[])add(array, index, Boolean.valueOf(element), Boolean.TYPE);
/* 4061:     */  }
/* 4062:     */  
/* 4090:     */  public static char[] add(char[] array, int index, char element)
/* 4091:     */  {
/* 4092:4092 */    return (char[])add(array, index, Character.valueOf(element), Character.TYPE);
/* 4093:     */  }
/* 4094:     */  
/* 4121:     */  public static byte[] add(byte[] array, int index, byte element)
/* 4122:     */  {
/* 4123:4123 */    return (byte[])add(array, index, Byte.valueOf(element), Byte.TYPE);
/* 4124:     */  }
/* 4125:     */  
/* 4152:     */  public static short[] add(short[] array, int index, short element)
/* 4153:     */  {
/* 4154:4154 */    return (short[])add(array, index, Short.valueOf(element), Short.TYPE);
/* 4155:     */  }
/* 4156:     */  
/* 4183:     */  public static int[] add(int[] array, int index, int element)
/* 4184:     */  {
/* 4185:4185 */    return (int[])add(array, index, Integer.valueOf(element), Integer.TYPE);
/* 4186:     */  }
/* 4187:     */  
/* 4214:     */  public static long[] add(long[] array, int index, long element)
/* 4215:     */  {
/* 4216:4216 */    return (long[])add(array, index, Long.valueOf(element), Long.TYPE);
/* 4217:     */  }
/* 4218:     */  
/* 4245:     */  public static float[] add(float[] array, int index, float element)
/* 4246:     */  {
/* 4247:4247 */    return (float[])add(array, index, Float.valueOf(element), Float.TYPE);
/* 4248:     */  }
/* 4249:     */  
/* 4276:     */  public static double[] add(double[] array, int index, double element)
/* 4277:     */  {
/* 4278:4278 */    return (double[])add(array, index, Double.valueOf(element), Double.TYPE);
/* 4279:     */  }
/* 4280:     */  
/* 4291:     */  private static Object add(Object array, int index, Object element, Class<?> clss)
/* 4292:     */  {
/* 4293:4293 */    if (array == null) {
/* 4294:4294 */      if (index != 0) {
/* 4295:4295 */        throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
/* 4296:     */      }
/* 4297:4297 */      Object joinedArray = Array.newInstance(clss, 1);
/* 4298:4298 */      Array.set(joinedArray, 0, element);
/* 4299:4299 */      return joinedArray;
/* 4300:     */    }
/* 4301:4301 */    int length = Array.getLength(array);
/* 4302:4302 */    if ((index > length) || (index < 0)) {
/* 4303:4303 */      throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/* 4304:     */    }
/* 4305:4305 */    Object result = Array.newInstance(clss, length + 1);
/* 4306:4306 */    System.arraycopy(array, 0, result, 0, index);
/* 4307:4307 */    Array.set(result, index, element);
/* 4308:4308 */    if (index < length) {
/* 4309:4309 */      System.arraycopy(array, index, result, index + 1, length - index);
/* 4310:     */    }
/* 4311:4311 */    return result;
/* 4312:     */  }
/* 4313:     */  
/* 4343:     */  public static <T> T[] remove(T[] array, int index)
/* 4344:     */  {
/* 4345:4345 */    return (Object[])remove(array, index);
/* 4346:     */  }
/* 4347:     */  
/* 4373:     */  public static <T> T[] removeElement(T[] array, Object element)
/* 4374:     */  {
/* 4375:4375 */    int index = indexOf(array, element);
/* 4376:4376 */    if (index == -1) {
/* 4377:4377 */      return clone(array);
/* 4378:     */    }
/* 4379:4379 */    return remove(array, index);
/* 4380:     */  }
/* 4381:     */  
/* 4409:     */  public static boolean[] remove(boolean[] array, int index)
/* 4410:     */  {
/* 4411:4411 */    return (boolean[])remove(array, index);
/* 4412:     */  }
/* 4413:     */  
/* 4438:     */  public static boolean[] removeElement(boolean[] array, boolean element)
/* 4439:     */  {
/* 4440:4440 */    int index = indexOf(array, element);
/* 4441:4441 */    if (index == -1) {
/* 4442:4442 */      return clone(array);
/* 4443:     */    }
/* 4444:4444 */    return remove(array, index);
/* 4445:     */  }
/* 4446:     */  
/* 4474:     */  public static byte[] remove(byte[] array, int index)
/* 4475:     */  {
/* 4476:4476 */    return (byte[])remove(array, index);
/* 4477:     */  }
/* 4478:     */  
/* 4503:     */  public static byte[] removeElement(byte[] array, byte element)
/* 4504:     */  {
/* 4505:4505 */    int index = indexOf(array, element);
/* 4506:4506 */    if (index == -1) {
/* 4507:4507 */      return clone(array);
/* 4508:     */    }
/* 4509:4509 */    return remove(array, index);
/* 4510:     */  }
/* 4511:     */  
/* 4539:     */  public static char[] remove(char[] array, int index)
/* 4540:     */  {
/* 4541:4541 */    return (char[])remove(array, index);
/* 4542:     */  }
/* 4543:     */  
/* 4568:     */  public static char[] removeElement(char[] array, char element)
/* 4569:     */  {
/* 4570:4570 */    int index = indexOf(array, element);
/* 4571:4571 */    if (index == -1) {
/* 4572:4572 */      return clone(array);
/* 4573:     */    }
/* 4574:4574 */    return remove(array, index);
/* 4575:     */  }
/* 4576:     */  
/* 4604:     */  public static double[] remove(double[] array, int index)
/* 4605:     */  {
/* 4606:4606 */    return (double[])remove(array, index);
/* 4607:     */  }
/* 4608:     */  
/* 4633:     */  public static double[] removeElement(double[] array, double element)
/* 4634:     */  {
/* 4635:4635 */    int index = indexOf(array, element);
/* 4636:4636 */    if (index == -1) {
/* 4637:4637 */      return clone(array);
/* 4638:     */    }
/* 4639:4639 */    return remove(array, index);
/* 4640:     */  }
/* 4641:     */  
/* 4669:     */  public static float[] remove(float[] array, int index)
/* 4670:     */  {
/* 4671:4671 */    return (float[])remove(array, index);
/* 4672:     */  }
/* 4673:     */  
/* 4698:     */  public static float[] removeElement(float[] array, float element)
/* 4699:     */  {
/* 4700:4700 */    int index = indexOf(array, element);
/* 4701:4701 */    if (index == -1) {
/* 4702:4702 */      return clone(array);
/* 4703:     */    }
/* 4704:4704 */    return remove(array, index);
/* 4705:     */  }
/* 4706:     */  
/* 4734:     */  public static int[] remove(int[] array, int index)
/* 4735:     */  {
/* 4736:4736 */    return (int[])remove(array, index);
/* 4737:     */  }
/* 4738:     */  
/* 4763:     */  public static int[] removeElement(int[] array, int element)
/* 4764:     */  {
/* 4765:4765 */    int index = indexOf(array, element);
/* 4766:4766 */    if (index == -1) {
/* 4767:4767 */      return clone(array);
/* 4768:     */    }
/* 4769:4769 */    return remove(array, index);
/* 4770:     */  }
/* 4771:     */  
/* 4799:     */  public static long[] remove(long[] array, int index)
/* 4800:     */  {
/* 4801:4801 */    return (long[])remove(array, index);
/* 4802:     */  }
/* 4803:     */  
/* 4828:     */  public static long[] removeElement(long[] array, long element)
/* 4829:     */  {
/* 4830:4830 */    int index = indexOf(array, element);
/* 4831:4831 */    if (index == -1) {
/* 4832:4832 */      return clone(array);
/* 4833:     */    }
/* 4834:4834 */    return remove(array, index);
/* 4835:     */  }
/* 4836:     */  
/* 4864:     */  public static short[] remove(short[] array, int index)
/* 4865:     */  {
/* 4866:4866 */    return (short[])remove(array, index);
/* 4867:     */  }
/* 4868:     */  
/* 4893:     */  public static short[] removeElement(short[] array, short element)
/* 4894:     */  {
/* 4895:4895 */    int index = indexOf(array, element);
/* 4896:4896 */    if (index == -1) {
/* 4897:4897 */      return clone(array);
/* 4898:     */    }
/* 4899:4899 */    return remove(array, index);
/* 4900:     */  }
/* 4901:     */  
/* 4922:     */  private static Object remove(Object array, int index)
/* 4923:     */  {
/* 4924:4924 */    int length = getLength(array);
/* 4925:4925 */    if ((index < 0) || (index >= length)) {
/* 4926:4926 */      throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/* 4927:     */    }
/* 4928:     */    
/* 4929:4929 */    Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
/* 4930:4930 */    System.arraycopy(array, 0, result, 0, index);
/* 4931:4931 */    if (index < length - 1) {
/* 4932:4932 */      System.arraycopy(array, index + 1, result, index, length - index - 1);
/* 4933:     */    }
/* 4934:     */    
/* 4935:4935 */    return result;
/* 4936:     */  }
/* 4937:     */  
/* 4965:     */  public static <T> T[] removeAll(T[] array, int... indices)
/* 4966:     */  {
/* 4967:4967 */    return (Object[])removeAll(array, clone(indices));
/* 4968:     */  }
/* 4969:     */  
/* 4997:     */  public static <T> T[] removeElements(T[] array, T... values)
/* 4998:     */  {
/* 4999:4999 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5000:5000 */      return clone(array);
/* 5001:     */    }
/* 5002:5002 */    HashMap<T, MutableInt> occurrences = new HashMap(values.length);
/* 5003:5003 */    for (T v : values) {
/* 5004:5004 */      MutableInt count = (MutableInt)occurrences.get(v);
/* 5005:5005 */      if (count == null) {
/* 5006:5006 */        occurrences.put(v, new MutableInt(1));
/* 5007:     */      } else {
/* 5008:5008 */        count.increment();
/* 5009:     */      }
/* 5010:     */    }
/* 5011:5011 */    HashSet<Integer> toRemove = new HashSet();
/* 5012:5012 */    for (Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
/* 5013:5013 */      T v = e.getKey();
/* 5014:5014 */      int found = 0;
/* 5015:5015 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5016:5016 */        found = indexOf(array, v, found);
/* 5017:5017 */        if (found < 0) {
/* 5018:     */          break;
/* 5019:     */        }
/* 5020:5020 */        toRemove.add(Integer.valueOf(found++));
/* 5021:     */      }
/* 5022:     */    }
/* 5023:5023 */    return removeAll(array, extractIndices(toRemove));
/* 5024:     */  }
/* 5025:     */  
/* 5054:     */  public static byte[] removeAll(byte[] array, int... indices)
/* 5055:     */  {
/* 5056:5056 */    return (byte[])removeAll(array, clone(indices));
/* 5057:     */  }
/* 5058:     */  
/* 5085:     */  public static byte[] removeElements(byte[] array, byte... values)
/* 5086:     */  {
/* 5087:5087 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5088:5088 */      return clone(array);
/* 5089:     */    }
/* 5090:5090 */    HashMap<Byte, MutableInt> occurrences = new HashMap(values.length);
/* 5091:5091 */    for (byte v : values) {
/* 5092:5092 */      Byte boxed = Byte.valueOf(v);
/* 5093:5093 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5094:5094 */      if (count == null) {
/* 5095:5095 */        occurrences.put(boxed, new MutableInt(1));
/* 5096:     */      } else {
/* 5097:5097 */        count.increment();
/* 5098:     */      }
/* 5099:     */    }
/* 5100:5100 */    HashSet<Integer> toRemove = new HashSet();
/* 5101:5101 */    for (Map.Entry<Byte, MutableInt> e : occurrences.entrySet()) {
/* 5102:5102 */      Byte v = (Byte)e.getKey();
/* 5103:5103 */      int found = 0;
/* 5104:5104 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5105:5105 */        found = indexOf(array, v.byteValue(), found);
/* 5106:5106 */        if (found < 0) {
/* 5107:     */          break;
/* 5108:     */        }
/* 5109:5109 */        toRemove.add(Integer.valueOf(found++));
/* 5110:     */      }
/* 5111:     */    }
/* 5112:5112 */    return removeAll(array, extractIndices(toRemove));
/* 5113:     */  }
/* 5114:     */  
/* 5143:     */  public static short[] removeAll(short[] array, int... indices)
/* 5144:     */  {
/* 5145:5145 */    return (short[])removeAll(array, clone(indices));
/* 5146:     */  }
/* 5147:     */  
/* 5174:     */  public static short[] removeElements(short[] array, short... values)
/* 5175:     */  {
/* 5176:5176 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5177:5177 */      return clone(array);
/* 5178:     */    }
/* 5179:5179 */    HashMap<Short, MutableInt> occurrences = new HashMap(values.length);
/* 5180:5180 */    for (short v : values) {
/* 5181:5181 */      Short boxed = Short.valueOf(v);
/* 5182:5182 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5183:5183 */      if (count == null) {
/* 5184:5184 */        occurrences.put(boxed, new MutableInt(1));
/* 5185:     */      } else {
/* 5186:5186 */        count.increment();
/* 5187:     */      }
/* 5188:     */    }
/* 5189:5189 */    HashSet<Integer> toRemove = new HashSet();
/* 5190:5190 */    for (Map.Entry<Short, MutableInt> e : occurrences.entrySet()) {
/* 5191:5191 */      Short v = (Short)e.getKey();
/* 5192:5192 */      int found = 0;
/* 5193:5193 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5194:5194 */        found = indexOf(array, v.shortValue(), found);
/* 5195:5195 */        if (found < 0) {
/* 5196:     */          break;
/* 5197:     */        }
/* 5198:5198 */        toRemove.add(Integer.valueOf(found++));
/* 5199:     */      }
/* 5200:     */    }
/* 5201:5201 */    return removeAll(array, extractIndices(toRemove));
/* 5202:     */  }
/* 5203:     */  
/* 5232:     */  public static int[] removeAll(int[] array, int... indices)
/* 5233:     */  {
/* 5234:5234 */    return (int[])removeAll(array, clone(indices));
/* 5235:     */  }
/* 5236:     */  
/* 5263:     */  public static int[] removeElements(int[] array, int... values)
/* 5264:     */  {
/* 5265:5265 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5266:5266 */      return clone(array);
/* 5267:     */    }
/* 5268:5268 */    HashMap<Integer, MutableInt> occurrences = new HashMap(values.length);
/* 5269:5269 */    for (int v : values) {
/* 5270:5270 */      Integer boxed = Integer.valueOf(v);
/* 5271:5271 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5272:5272 */      if (count == null) {
/* 5273:5273 */        occurrences.put(boxed, new MutableInt(1));
/* 5274:     */      } else {
/* 5275:5275 */        count.increment();
/* 5276:     */      }
/* 5277:     */    }
/* 5278:5278 */    HashSet<Integer> toRemove = new HashSet();
/* 5279:5279 */    for (Map.Entry<Integer, MutableInt> e : occurrences.entrySet()) {
/* 5280:5280 */      Integer v = (Integer)e.getKey();
/* 5281:5281 */      int found = 0;
/* 5282:5282 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5283:5283 */        found = indexOf(array, v.intValue(), found);
/* 5284:5284 */        if (found < 0) {
/* 5285:     */          break;
/* 5286:     */        }
/* 5287:5287 */        toRemove.add(Integer.valueOf(found++));
/* 5288:     */      }
/* 5289:     */    }
/* 5290:5290 */    return removeAll(array, extractIndices(toRemove));
/* 5291:     */  }
/* 5292:     */  
/* 5321:     */  public static char[] removeAll(char[] array, int... indices)
/* 5322:     */  {
/* 5323:5323 */    return (char[])removeAll(array, clone(indices));
/* 5324:     */  }
/* 5325:     */  
/* 5352:     */  public static char[] removeElements(char[] array, char... values)
/* 5353:     */  {
/* 5354:5354 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5355:5355 */      return clone(array);
/* 5356:     */    }
/* 5357:5357 */    HashMap<Character, MutableInt> occurrences = new HashMap(values.length);
/* 5358:5358 */    for (char v : values) {
/* 5359:5359 */      Character boxed = Character.valueOf(v);
/* 5360:5360 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5361:5361 */      if (count == null) {
/* 5362:5362 */        occurrences.put(boxed, new MutableInt(1));
/* 5363:     */      } else {
/* 5364:5364 */        count.increment();
/* 5365:     */      }
/* 5366:     */    }
/* 5367:5367 */    HashSet<Integer> toRemove = new HashSet();
/* 5368:5368 */    for (Map.Entry<Character, MutableInt> e : occurrences.entrySet()) {
/* 5369:5369 */      Character v = (Character)e.getKey();
/* 5370:5370 */      int found = 0;
/* 5371:5371 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5372:5372 */        found = indexOf(array, v.charValue(), found);
/* 5373:5373 */        if (found < 0) {
/* 5374:     */          break;
/* 5375:     */        }
/* 5376:5376 */        toRemove.add(Integer.valueOf(found++));
/* 5377:     */      }
/* 5378:     */    }
/* 5379:5379 */    return removeAll(array, extractIndices(toRemove));
/* 5380:     */  }
/* 5381:     */  
/* 5410:     */  public static long[] removeAll(long[] array, int... indices)
/* 5411:     */  {
/* 5412:5412 */    return (long[])removeAll(array, clone(indices));
/* 5413:     */  }
/* 5414:     */  
/* 5441:     */  public static long[] removeElements(long[] array, long... values)
/* 5442:     */  {
/* 5443:5443 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5444:5444 */      return clone(array);
/* 5445:     */    }
/* 5446:5446 */    HashMap<Long, MutableInt> occurrences = new HashMap(values.length);
/* 5447:5447 */    for (long v : values) {
/* 5448:5448 */      Long boxed = Long.valueOf(v);
/* 5449:5449 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5450:5450 */      if (count == null) {
/* 5451:5451 */        occurrences.put(boxed, new MutableInt(1));
/* 5452:     */      } else {
/* 5453:5453 */        count.increment();
/* 5454:     */      }
/* 5455:     */    }
/* 5456:5456 */    HashSet<Integer> toRemove = new HashSet();
/* 5457:5457 */    for (Map.Entry<Long, MutableInt> e : occurrences.entrySet()) {
/* 5458:5458 */      Long v = (Long)e.getKey();
/* 5459:5459 */      int found = 0;
/* 5460:5460 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5461:5461 */        found = indexOf(array, v.longValue(), found);
/* 5462:5462 */        if (found < 0) {
/* 5463:     */          break;
/* 5464:     */        }
/* 5465:5465 */        toRemove.add(Integer.valueOf(found++));
/* 5466:     */      }
/* 5467:     */    }
/* 5468:5468 */    return removeAll(array, extractIndices(toRemove));
/* 5469:     */  }
/* 5470:     */  
/* 5499:     */  public static float[] removeAll(float[] array, int... indices)
/* 5500:     */  {
/* 5501:5501 */    return (float[])removeAll(array, clone(indices));
/* 5502:     */  }
/* 5503:     */  
/* 5530:     */  public static float[] removeElements(float[] array, float... values)
/* 5531:     */  {
/* 5532:5532 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5533:5533 */      return clone(array);
/* 5534:     */    }
/* 5535:5535 */    HashMap<Float, MutableInt> occurrences = new HashMap(values.length);
/* 5536:5536 */    for (float v : values) {
/* 5537:5537 */      Float boxed = Float.valueOf(v);
/* 5538:5538 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5539:5539 */      if (count == null) {
/* 5540:5540 */        occurrences.put(boxed, new MutableInt(1));
/* 5541:     */      } else {
/* 5542:5542 */        count.increment();
/* 5543:     */      }
/* 5544:     */    }
/* 5545:5545 */    HashSet<Integer> toRemove = new HashSet();
/* 5546:5546 */    for (Map.Entry<Float, MutableInt> e : occurrences.entrySet()) {
/* 5547:5547 */      Float v = (Float)e.getKey();
/* 5548:5548 */      int found = 0;
/* 5549:5549 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5550:5550 */        found = indexOf(array, v.floatValue(), found);
/* 5551:5551 */        if (found < 0) {
/* 5552:     */          break;
/* 5553:     */        }
/* 5554:5554 */        toRemove.add(Integer.valueOf(found++));
/* 5555:     */      }
/* 5556:     */    }
/* 5557:5557 */    return removeAll(array, extractIndices(toRemove));
/* 5558:     */  }
/* 5559:     */  
/* 5588:     */  public static double[] removeAll(double[] array, int... indices)
/* 5589:     */  {
/* 5590:5590 */    return (double[])removeAll(array, clone(indices));
/* 5591:     */  }
/* 5592:     */  
/* 5619:     */  public static double[] removeElements(double[] array, double... values)
/* 5620:     */  {
/* 5621:5621 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5622:5622 */      return clone(array);
/* 5623:     */    }
/* 5624:5624 */    HashMap<Double, MutableInt> occurrences = new HashMap(values.length);
/* 5625:5625 */    for (double v : values) {
/* 5626:5626 */      Double boxed = Double.valueOf(v);
/* 5627:5627 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5628:5628 */      if (count == null) {
/* 5629:5629 */        occurrences.put(boxed, new MutableInt(1));
/* 5630:     */      } else {
/* 5631:5631 */        count.increment();
/* 5632:     */      }
/* 5633:     */    }
/* 5634:5634 */    HashSet<Integer> toRemove = new HashSet();
/* 5635:5635 */    for (Map.Entry<Double, MutableInt> e : occurrences.entrySet()) {
/* 5636:5636 */      Double v = (Double)e.getKey();
/* 5637:5637 */      int found = 0;
/* 5638:5638 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5639:5639 */        found = indexOf(array, v.doubleValue(), found);
/* 5640:5640 */        if (found < 0) {
/* 5641:     */          break;
/* 5642:     */        }
/* 5643:5643 */        toRemove.add(Integer.valueOf(found++));
/* 5644:     */      }
/* 5645:     */    }
/* 5646:5646 */    return removeAll(array, extractIndices(toRemove));
/* 5647:     */  }
/* 5648:     */  
/* 5673:     */  public static boolean[] removeAll(boolean[] array, int... indices)
/* 5674:     */  {
/* 5675:5675 */    return (boolean[])removeAll(array, clone(indices));
/* 5676:     */  }
/* 5677:     */  
/* 5704:     */  public static boolean[] removeElements(boolean[] array, boolean... values)
/* 5705:     */  {
/* 5706:5706 */    if ((isEmpty(array)) || (isEmpty(values))) {
/* 5707:5707 */      return clone(array);
/* 5708:     */    }
/* 5709:5709 */    HashMap<Boolean, MutableInt> occurrences = new HashMap(values.length);
/* 5710:5710 */    for (boolean v : values) {
/* 5711:5711 */      Boolean boxed = Boolean.valueOf(v);
/* 5712:5712 */      MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5713:5713 */      if (count == null) {
/* 5714:5714 */        occurrences.put(boxed, new MutableInt(1));
/* 5715:     */      } else {
/* 5716:5716 */        count.increment();
/* 5717:     */      }
/* 5718:     */    }
/* 5719:5719 */    HashSet<Integer> toRemove = new HashSet();
/* 5720:5720 */    for (Map.Entry<Boolean, MutableInt> e : occurrences.entrySet()) {
/* 5721:5721 */      Boolean v = (Boolean)e.getKey();
/* 5722:5722 */      int found = 0;
/* 5723:5723 */      int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5724:5724 */        found = indexOf(array, v.booleanValue(), found);
/* 5725:5725 */        if (found < 0) {
/* 5726:     */          break;
/* 5727:     */        }
/* 5728:5728 */        toRemove.add(Integer.valueOf(found++));
/* 5729:     */      }
/* 5730:     */    }
/* 5731:5731 */    return removeAll(array, extractIndices(toRemove));
/* 5732:     */  }
/* 5733:     */  
/* 5740:     */  private static Object removeAll(Object array, int... indices)
/* 5741:     */  {
/* 5742:5742 */    int length = getLength(array);
/* 5743:5743 */    int diff = 0;
/* 5744:     */    
/* 5745:5745 */    if (isNotEmpty(indices)) {
/* 5746:5746 */      Arrays.sort(indices);
/* 5747:     */      
/* 5748:5748 */      int i = indices.length;
/* 5749:5749 */      int prevIndex = length;
/* 5750:5750 */      for (;;) { i--; if (i < 0) break;
/* 5751:5751 */        int index = indices[i];
/* 5752:5752 */        if ((index < 0) || (index >= length)) {
/* 5753:5753 */          throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/* 5754:     */        }
/* 5755:5755 */        if (index < prevIndex)
/* 5756:     */        {
/* 5758:5758 */          diff++;
/* 5759:5759 */          prevIndex = index;
/* 5760:     */        }
/* 5761:     */      } }
/* 5762:5762 */    Object result = Array.newInstance(array.getClass().getComponentType(), length - diff);
/* 5763:5763 */    if (diff < length) {
/* 5764:5764 */      int end = length;
/* 5765:5765 */      int dest = length - diff;
/* 5766:5766 */      for (int i = indices.length - 1; i >= 0; i--) {
/* 5767:5767 */        int index = indices[i];
/* 5768:5768 */        if (end - index > 1) {
/* 5769:5769 */          int cp = end - index - 1;
/* 5770:5770 */          dest -= cp;
/* 5771:5771 */          System.arraycopy(array, index + 1, result, dest, cp);
/* 5772:     */        }
/* 5773:5773 */        end = index;
/* 5774:     */      }
/* 5775:5775 */      if (end > 0) {
/* 5776:5776 */        System.arraycopy(array, 0, result, 0, end);
/* 5777:     */      }
/* 5778:     */    }
/* 5779:5779 */    return result;
/* 5780:     */  }
/* 5781:     */  
/* 5787:     */  private static int[] extractIndices(HashSet<Integer> coll)
/* 5788:     */  {
/* 5789:5789 */    int[] result = new int[coll.size()];
/* 5790:5790 */    int i = 0;
/* 5791:5791 */    for (Integer index : coll) {
/* 5792:5792 */      result[(i++)] = index.intValue();
/* 5793:     */    }
/* 5794:5794 */    return result;
/* 5795:     */  }
/* 5796:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.ArrayUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */