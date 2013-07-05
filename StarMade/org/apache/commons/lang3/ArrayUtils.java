/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import org.apache.commons.lang3.builder.EqualsBuilder;
/*      */ import org.apache.commons.lang3.builder.HashCodeBuilder;
/*      */ import org.apache.commons.lang3.builder.ToStringBuilder;
/*      */ import org.apache.commons.lang3.builder.ToStringStyle;
/*      */ import org.apache.commons.lang3.mutable.MutableInt;
/*      */ 
/*      */ public class ArrayUtils
/*      */ {
/*   49 */   public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
/*      */ 
/*   53 */   public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
/*      */ 
/*   57 */   public static final String[] EMPTY_STRING_ARRAY = new String[0];
/*      */ 
/*   61 */   public static final long[] EMPTY_LONG_ARRAY = new long[0];
/*      */ 
/*   65 */   public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
/*      */ 
/*   69 */   public static final int[] EMPTY_INT_ARRAY = new int[0];
/*      */ 
/*   73 */   public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
/*      */ 
/*   77 */   public static final short[] EMPTY_SHORT_ARRAY = new short[0];
/*      */ 
/*   81 */   public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
/*      */ 
/*   85 */   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
/*      */ 
/*   89 */   public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
/*      */ 
/*   93 */   public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
/*      */ 
/*   97 */   public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
/*      */ 
/*  101 */   public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
/*      */ 
/*  105 */   public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
/*      */ 
/*  109 */   public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
/*      */ 
/*  113 */   public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
/*      */ 
/*  117 */   public static final char[] EMPTY_CHAR_ARRAY = new char[0];
/*      */ 
/*  121 */   public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
/*      */   public static final int INDEX_NOT_FOUND = -1;
/*      */ 
/*      */   public static String toString(Object array)
/*      */   {
/*  159 */     return toString(array, "{}");
/*      */   }
/*      */ 
/*      */   public static String toString(Object array, String stringIfNull)
/*      */   {
/*  175 */     if (array == null) {
/*  176 */       return stringIfNull;
/*      */     }
/*  178 */     return new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE).append(array).toString();
/*      */   }
/*      */ 
/*      */   public static int hashCode(Object array)
/*      */   {
/*  190 */     return new HashCodeBuilder().append(array).toHashCode();
/*      */   }
/*      */ 
/*      */   public static boolean isEquals(Object array1, Object array2)
/*      */   {
/*  204 */     return new EqualsBuilder().append(array1, array2).isEquals();
/*      */   }
/*      */ 
/*      */   public static Map<Object, Object> toMap(Object[] array)
/*      */   {
/*  235 */     if (array == null) {
/*  236 */       return null;
/*      */     }
/*  238 */     Map map = new HashMap((int)(array.length * 1.5D));
/*  239 */     for (int i = 0; i < array.length; i++) {
/*  240 */       Object object = array[i];
/*  241 */       if ((object instanceof Map.Entry)) {
/*  242 */         Map.Entry entry = (Map.Entry)object;
/*  243 */         map.put(entry.getKey(), entry.getValue());
/*  244 */       } else if ((object instanceof Object[])) {
/*  245 */         Object[] entry = (Object[])object;
/*  246 */         if (entry.length < 2) {
/*  247 */           throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2");
/*      */         }
/*      */ 
/*  251 */         map.put(entry[0], entry[1]);
/*      */       } else {
/*  253 */         throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  258 */     return map;
/*      */   }
/*      */ 
/*      */   public static <T> T[] toArray(T[] items)
/*      */   {
/*  301 */     return items;
/*      */   }
/*      */ 
/*      */   public static <T> T[] clone(T[] array)
/*      */   {
/*  320 */     if (array == null) {
/*  321 */       return null;
/*      */     }
/*  323 */     return (Object[])array.clone();
/*      */   }
/*      */ 
/*      */   public static long[] clone(long[] array)
/*      */   {
/*  336 */     if (array == null) {
/*  337 */       return null;
/*      */     }
/*  339 */     return (long[])array.clone();
/*      */   }
/*      */ 
/*      */   public static int[] clone(int[] array)
/*      */   {
/*  352 */     if (array == null) {
/*  353 */       return null;
/*      */     }
/*  355 */     return (int[])array.clone();
/*      */   }
/*      */ 
/*      */   public static short[] clone(short[] array)
/*      */   {
/*  368 */     if (array == null) {
/*  369 */       return null;
/*      */     }
/*  371 */     return (short[])array.clone();
/*      */   }
/*      */ 
/*      */   public static char[] clone(char[] array)
/*      */   {
/*  384 */     if (array == null) {
/*  385 */       return null;
/*      */     }
/*  387 */     return (char[])array.clone();
/*      */   }
/*      */ 
/*      */   public static byte[] clone(byte[] array)
/*      */   {
/*  400 */     if (array == null) {
/*  401 */       return null;
/*      */     }
/*  403 */     return (byte[])array.clone();
/*      */   }
/*      */ 
/*      */   public static double[] clone(double[] array)
/*      */   {
/*  416 */     if (array == null) {
/*  417 */       return null;
/*      */     }
/*  419 */     return (double[])array.clone();
/*      */   }
/*      */ 
/*      */   public static float[] clone(float[] array)
/*      */   {
/*  432 */     if (array == null) {
/*  433 */       return null;
/*      */     }
/*  435 */     return (float[])array.clone();
/*      */   }
/*      */ 
/*      */   public static boolean[] clone(boolean[] array)
/*      */   {
/*  448 */     if (array == null) {
/*  449 */       return null;
/*      */     }
/*  451 */     return (boolean[])array.clone();
/*      */   }
/*      */ 
/*      */   public static Object[] nullToEmpty(Object[] array)
/*      */   {
/*  470 */     if ((array == null) || (array.length == 0)) {
/*  471 */       return EMPTY_OBJECT_ARRAY;
/*      */     }
/*  473 */     return array;
/*      */   }
/*      */ 
/*      */   public static String[] nullToEmpty(String[] array)
/*      */   {
/*  490 */     if ((array == null) || (array.length == 0)) {
/*  491 */       return EMPTY_STRING_ARRAY;
/*      */     }
/*  493 */     return array;
/*      */   }
/*      */ 
/*      */   public static long[] nullToEmpty(long[] array)
/*      */   {
/*  510 */     if ((array == null) || (array.length == 0)) {
/*  511 */       return EMPTY_LONG_ARRAY;
/*      */     }
/*  513 */     return array;
/*      */   }
/*      */ 
/*      */   public static int[] nullToEmpty(int[] array)
/*      */   {
/*  530 */     if ((array == null) || (array.length == 0)) {
/*  531 */       return EMPTY_INT_ARRAY;
/*      */     }
/*  533 */     return array;
/*      */   }
/*      */ 
/*      */   public static short[] nullToEmpty(short[] array)
/*      */   {
/*  550 */     if ((array == null) || (array.length == 0)) {
/*  551 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/*  553 */     return array;
/*      */   }
/*      */ 
/*      */   public static char[] nullToEmpty(char[] array)
/*      */   {
/*  570 */     if ((array == null) || (array.length == 0)) {
/*  571 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/*  573 */     return array;
/*      */   }
/*      */ 
/*      */   public static byte[] nullToEmpty(byte[] array)
/*      */   {
/*  590 */     if ((array == null) || (array.length == 0)) {
/*  591 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/*  593 */     return array;
/*      */   }
/*      */ 
/*      */   public static double[] nullToEmpty(double[] array)
/*      */   {
/*  610 */     if ((array == null) || (array.length == 0)) {
/*  611 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/*  613 */     return array;
/*      */   }
/*      */ 
/*      */   public static float[] nullToEmpty(float[] array)
/*      */   {
/*  630 */     if ((array == null) || (array.length == 0)) {
/*  631 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/*  633 */     return array;
/*      */   }
/*      */ 
/*      */   public static boolean[] nullToEmpty(boolean[] array)
/*      */   {
/*  650 */     if ((array == null) || (array.length == 0)) {
/*  651 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/*  653 */     return array;
/*      */   }
/*      */ 
/*      */   public static Long[] nullToEmpty(Long[] array)
/*      */   {
/*  670 */     if ((array == null) || (array.length == 0)) {
/*  671 */       return EMPTY_LONG_OBJECT_ARRAY;
/*      */     }
/*  673 */     return array;
/*      */   }
/*      */ 
/*      */   public static Integer[] nullToEmpty(Integer[] array)
/*      */   {
/*  690 */     if ((array == null) || (array.length == 0)) {
/*  691 */       return EMPTY_INTEGER_OBJECT_ARRAY;
/*      */     }
/*  693 */     return array;
/*      */   }
/*      */ 
/*      */   public static Short[] nullToEmpty(Short[] array)
/*      */   {
/*  710 */     if ((array == null) || (array.length == 0)) {
/*  711 */       return EMPTY_SHORT_OBJECT_ARRAY;
/*      */     }
/*  713 */     return array;
/*      */   }
/*      */ 
/*      */   public static Character[] nullToEmpty(Character[] array)
/*      */   {
/*  730 */     if ((array == null) || (array.length == 0)) {
/*  731 */       return EMPTY_CHARACTER_OBJECT_ARRAY;
/*      */     }
/*  733 */     return array;
/*      */   }
/*      */ 
/*      */   public static Byte[] nullToEmpty(Byte[] array)
/*      */   {
/*  750 */     if ((array == null) || (array.length == 0)) {
/*  751 */       return EMPTY_BYTE_OBJECT_ARRAY;
/*      */     }
/*  753 */     return array;
/*      */   }
/*      */ 
/*      */   public static Double[] nullToEmpty(Double[] array)
/*      */   {
/*  770 */     if ((array == null) || (array.length == 0)) {
/*  771 */       return EMPTY_DOUBLE_OBJECT_ARRAY;
/*      */     }
/*  773 */     return array;
/*      */   }
/*      */ 
/*      */   public static Float[] nullToEmpty(Float[] array)
/*      */   {
/*  790 */     if ((array == null) || (array.length == 0)) {
/*  791 */       return EMPTY_FLOAT_OBJECT_ARRAY;
/*      */     }
/*  793 */     return array;
/*      */   }
/*      */ 
/*      */   public static Boolean[] nullToEmpty(Boolean[] array)
/*      */   {
/*  810 */     if ((array == null) || (array.length == 0)) {
/*  811 */       return EMPTY_BOOLEAN_OBJECT_ARRAY;
/*      */     }
/*  813 */     return array;
/*      */   }
/*      */ 
/*      */   public static <T> T[] subarray(T[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/*  847 */     if (array == null) {
/*  848 */       return null;
/*      */     }
/*  850 */     if (startIndexInclusive < 0) {
/*  851 */       startIndexInclusive = 0;
/*      */     }
/*  853 */     if (endIndexExclusive > array.length) {
/*  854 */       endIndexExclusive = array.length;
/*      */     }
/*  856 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  857 */     Class type = array.getClass().getComponentType();
/*  858 */     if (newSize <= 0)
/*      */     {
/*  860 */       Object[] emptyArray = (Object[])Array.newInstance(type, 0);
/*  861 */       return emptyArray;
/*      */     }
/*      */ 
/*  864 */     Object[] subarray = (Object[])Array.newInstance(type, newSize);
/*  865 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  866 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/*  889 */     if (array == null) {
/*  890 */       return null;
/*      */     }
/*  892 */     if (startIndexInclusive < 0) {
/*  893 */       startIndexInclusive = 0;
/*      */     }
/*  895 */     if (endIndexExclusive > array.length) {
/*  896 */       endIndexExclusive = array.length;
/*      */     }
/*  898 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  899 */     if (newSize <= 0) {
/*  900 */       return EMPTY_LONG_ARRAY;
/*      */     }
/*      */ 
/*  903 */     long[] subarray = new long[newSize];
/*  904 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  905 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/*  928 */     if (array == null) {
/*  929 */       return null;
/*      */     }
/*  931 */     if (startIndexInclusive < 0) {
/*  932 */       startIndexInclusive = 0;
/*      */     }
/*  934 */     if (endIndexExclusive > array.length) {
/*  935 */       endIndexExclusive = array.length;
/*      */     }
/*  937 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  938 */     if (newSize <= 0) {
/*  939 */       return EMPTY_INT_ARRAY;
/*      */     }
/*      */ 
/*  942 */     int[] subarray = new int[newSize];
/*  943 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  944 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/*  967 */     if (array == null) {
/*  968 */       return null;
/*      */     }
/*  970 */     if (startIndexInclusive < 0) {
/*  971 */       startIndexInclusive = 0;
/*      */     }
/*  973 */     if (endIndexExclusive > array.length) {
/*  974 */       endIndexExclusive = array.length;
/*      */     }
/*  976 */     int newSize = endIndexExclusive - startIndexInclusive;
/*  977 */     if (newSize <= 0) {
/*  978 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/*      */ 
/*  981 */     short[] subarray = new short[newSize];
/*  982 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/*  983 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/* 1006 */     if (array == null) {
/* 1007 */       return null;
/*      */     }
/* 1009 */     if (startIndexInclusive < 0) {
/* 1010 */       startIndexInclusive = 0;
/*      */     }
/* 1012 */     if (endIndexExclusive > array.length) {
/* 1013 */       endIndexExclusive = array.length;
/*      */     }
/* 1015 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1016 */     if (newSize <= 0) {
/* 1017 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/*      */ 
/* 1020 */     char[] subarray = new char[newSize];
/* 1021 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1022 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/* 1045 */     if (array == null) {
/* 1046 */       return null;
/*      */     }
/* 1048 */     if (startIndexInclusive < 0) {
/* 1049 */       startIndexInclusive = 0;
/*      */     }
/* 1051 */     if (endIndexExclusive > array.length) {
/* 1052 */       endIndexExclusive = array.length;
/*      */     }
/* 1054 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1055 */     if (newSize <= 0) {
/* 1056 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/*      */ 
/* 1059 */     byte[] subarray = new byte[newSize];
/* 1060 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1061 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/* 1084 */     if (array == null) {
/* 1085 */       return null;
/*      */     }
/* 1087 */     if (startIndexInclusive < 0) {
/* 1088 */       startIndexInclusive = 0;
/*      */     }
/* 1090 */     if (endIndexExclusive > array.length) {
/* 1091 */       endIndexExclusive = array.length;
/*      */     }
/* 1093 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1094 */     if (newSize <= 0) {
/* 1095 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/*      */ 
/* 1098 */     double[] subarray = new double[newSize];
/* 1099 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1100 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/* 1123 */     if (array == null) {
/* 1124 */       return null;
/*      */     }
/* 1126 */     if (startIndexInclusive < 0) {
/* 1127 */       startIndexInclusive = 0;
/*      */     }
/* 1129 */     if (endIndexExclusive > array.length) {
/* 1130 */       endIndexExclusive = array.length;
/*      */     }
/* 1132 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1133 */     if (newSize <= 0) {
/* 1134 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/*      */ 
/* 1137 */     float[] subarray = new float[newSize];
/* 1138 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1139 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive)
/*      */   {
/* 1162 */     if (array == null) {
/* 1163 */       return null;
/*      */     }
/* 1165 */     if (startIndexInclusive < 0) {
/* 1166 */       startIndexInclusive = 0;
/*      */     }
/* 1168 */     if (endIndexExclusive > array.length) {
/* 1169 */       endIndexExclusive = array.length;
/*      */     }
/* 1171 */     int newSize = endIndexExclusive - startIndexInclusive;
/* 1172 */     if (newSize <= 0) {
/* 1173 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/*      */ 
/* 1176 */     boolean[] subarray = new boolean[newSize];
/* 1177 */     System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
/* 1178 */     return subarray;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(Object[] array1, Object[] array2)
/*      */   {
/* 1195 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1198 */       return false;
/*      */     }
/* 1200 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(long[] array1, long[] array2)
/*      */   {
/* 1213 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1216 */       return false;
/*      */     }
/* 1218 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(int[] array1, int[] array2)
/*      */   {
/* 1231 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1234 */       return false;
/*      */     }
/* 1236 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(short[] array1, short[] array2)
/*      */   {
/* 1249 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1252 */       return false;
/*      */     }
/* 1254 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(char[] array1, char[] array2)
/*      */   {
/* 1267 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1270 */       return false;
/*      */     }
/* 1272 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(byte[] array1, byte[] array2)
/*      */   {
/* 1285 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1288 */       return false;
/*      */     }
/* 1290 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(double[] array1, double[] array2)
/*      */   {
/* 1303 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1306 */       return false;
/*      */     }
/* 1308 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(float[] array1, float[] array2)
/*      */   {
/* 1321 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1324 */       return false;
/*      */     }
/* 1326 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isSameLength(boolean[] array1, boolean[] array2)
/*      */   {
/* 1339 */     if (((array1 == null) && (array2 != null) && (array2.length > 0)) || ((array2 == null) && (array1 != null) && (array1.length > 0)) || ((array1 != null) && (array2 != null) && (array1.length != array2.length)))
/*      */     {
/* 1342 */       return false;
/*      */     }
/* 1344 */     return true;
/*      */   }
/*      */ 
/*      */   public static int getLength(Object array)
/*      */   {
/* 1369 */     if (array == null) {
/* 1370 */       return 0;
/*      */     }
/* 1372 */     return Array.getLength(array);
/*      */   }
/*      */ 
/*      */   public static boolean isSameType(Object array1, Object array2)
/*      */   {
/* 1385 */     if ((array1 == null) || (array2 == null)) {
/* 1386 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1388 */     return array1.getClass().getName().equals(array2.getClass().getName());
/*      */   }
/*      */ 
/*      */   public static void reverse(Object[] array)
/*      */   {
/* 1403 */     if (array == null) {
/* 1404 */       return;
/*      */     }
/* 1406 */     int i = 0;
/* 1407 */     int j = array.length - 1;
/*      */ 
/* 1409 */     while (j > i) {
/* 1410 */       Object tmp = array[j];
/* 1411 */       array[j] = array[i];
/* 1412 */       array[i] = tmp;
/* 1413 */       j--;
/* 1414 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(long[] array)
/*      */   {
/* 1426 */     if (array == null) {
/* 1427 */       return;
/*      */     }
/* 1429 */     int i = 0;
/* 1430 */     int j = array.length - 1;
/*      */ 
/* 1432 */     while (j > i) {
/* 1433 */       long tmp = array[j];
/* 1434 */       array[j] = array[i];
/* 1435 */       array[i] = tmp;
/* 1436 */       j--;
/* 1437 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(int[] array)
/*      */   {
/* 1449 */     if (array == null) {
/* 1450 */       return;
/*      */     }
/* 1452 */     int i = 0;
/* 1453 */     int j = array.length - 1;
/*      */ 
/* 1455 */     while (j > i) {
/* 1456 */       int tmp = array[j];
/* 1457 */       array[j] = array[i];
/* 1458 */       array[i] = tmp;
/* 1459 */       j--;
/* 1460 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(short[] array)
/*      */   {
/* 1472 */     if (array == null) {
/* 1473 */       return;
/*      */     }
/* 1475 */     int i = 0;
/* 1476 */     int j = array.length - 1;
/*      */ 
/* 1478 */     while (j > i) {
/* 1479 */       short tmp = array[j];
/* 1480 */       array[j] = array[i];
/* 1481 */       array[i] = tmp;
/* 1482 */       j--;
/* 1483 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(char[] array)
/*      */   {
/* 1495 */     if (array == null) {
/* 1496 */       return;
/*      */     }
/* 1498 */     int i = 0;
/* 1499 */     int j = array.length - 1;
/*      */ 
/* 1501 */     while (j > i) {
/* 1502 */       char tmp = array[j];
/* 1503 */       array[j] = array[i];
/* 1504 */       array[i] = tmp;
/* 1505 */       j--;
/* 1506 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(byte[] array)
/*      */   {
/* 1518 */     if (array == null) {
/* 1519 */       return;
/*      */     }
/* 1521 */     int i = 0;
/* 1522 */     int j = array.length - 1;
/*      */ 
/* 1524 */     while (j > i) {
/* 1525 */       byte tmp = array[j];
/* 1526 */       array[j] = array[i];
/* 1527 */       array[i] = tmp;
/* 1528 */       j--;
/* 1529 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(double[] array)
/*      */   {
/* 1541 */     if (array == null) {
/* 1542 */       return;
/*      */     }
/* 1544 */     int i = 0;
/* 1545 */     int j = array.length - 1;
/*      */ 
/* 1547 */     while (j > i) {
/* 1548 */       double tmp = array[j];
/* 1549 */       array[j] = array[i];
/* 1550 */       array[i] = tmp;
/* 1551 */       j--;
/* 1552 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(float[] array)
/*      */   {
/* 1564 */     if (array == null) {
/* 1565 */       return;
/*      */     }
/* 1567 */     int i = 0;
/* 1568 */     int j = array.length - 1;
/*      */ 
/* 1570 */     while (j > i) {
/* 1571 */       float tmp = array[j];
/* 1572 */       array[j] = array[i];
/* 1573 */       array[i] = tmp;
/* 1574 */       j--;
/* 1575 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reverse(boolean[] array)
/*      */   {
/* 1587 */     if (array == null) {
/* 1588 */       return;
/*      */     }
/* 1590 */     int i = 0;
/* 1591 */     int j = array.length - 1;
/*      */ 
/* 1593 */     while (j > i) {
/* 1594 */       boolean tmp = array[j];
/* 1595 */       array[j] = array[i];
/* 1596 */       array[i] = tmp;
/* 1597 */       j--;
/* 1598 */       i++;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static int indexOf(Object[] array, Object objectToFind)
/*      */   {
/* 1618 */     return indexOf(array, objectToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(Object[] array, Object objectToFind, int startIndex)
/*      */   {
/* 1636 */     if (array == null) {
/* 1637 */       return -1;
/*      */     }
/* 1639 */     if (startIndex < 0) {
/* 1640 */       startIndex = 0;
/*      */     }
/* 1642 */     if (objectToFind == null) {
/* 1643 */       for (int i = startIndex; i < array.length; i++) {
/* 1644 */         if (array[i] == null)
/* 1645 */           return i;
/*      */       }
/*      */     }
/* 1648 */     else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 1649 */       for (int i = startIndex; i < array.length; i++) {
/* 1650 */         if (objectToFind.equals(array[i])) {
/* 1651 */           return i;
/*      */         }
/*      */       }
/*      */     }
/* 1655 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(Object[] array, Object objectToFind)
/*      */   {
/* 1669 */     return lastIndexOf(array, objectToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex)
/*      */   {
/* 1687 */     if (array == null) {
/* 1688 */       return -1;
/*      */     }
/* 1690 */     if (startIndex < 0)
/* 1691 */       return -1;
/* 1692 */     if (startIndex >= array.length) {
/* 1693 */       startIndex = array.length - 1;
/*      */     }
/* 1695 */     if (objectToFind == null) {
/* 1696 */       for (int i = startIndex; i >= 0; i--) {
/* 1697 */         if (array[i] == null)
/* 1698 */           return i;
/*      */       }
/*      */     }
/* 1701 */     else if (array.getClass().getComponentType().isInstance(objectToFind)) {
/* 1702 */       for (int i = startIndex; i >= 0; i--) {
/* 1703 */         if (objectToFind.equals(array[i])) {
/* 1704 */           return i;
/*      */         }
/*      */       }
/*      */     }
/* 1708 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(Object[] array, Object objectToFind)
/*      */   {
/* 1721 */     return indexOf(array, objectToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(long[] array, long valueToFind)
/*      */   {
/* 1737 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(long[] array, long valueToFind, int startIndex)
/*      */   {
/* 1755 */     if (array == null) {
/* 1756 */       return -1;
/*      */     }
/* 1758 */     if (startIndex < 0) {
/* 1759 */       startIndex = 0;
/*      */     }
/* 1761 */     for (int i = startIndex; i < array.length; i++) {
/* 1762 */       if (valueToFind == array[i]) {
/* 1763 */         return i;
/*      */       }
/*      */     }
/* 1766 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(long[] array, long valueToFind)
/*      */   {
/* 1780 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(long[] array, long valueToFind, int startIndex)
/*      */   {
/* 1798 */     if (array == null) {
/* 1799 */       return -1;
/*      */     }
/* 1801 */     if (startIndex < 0)
/* 1802 */       return -1;
/* 1803 */     if (startIndex >= array.length) {
/* 1804 */       startIndex = array.length - 1;
/*      */     }
/* 1806 */     for (int i = startIndex; i >= 0; i--) {
/* 1807 */       if (valueToFind == array[i]) {
/* 1808 */         return i;
/*      */       }
/*      */     }
/* 1811 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(long[] array, long valueToFind)
/*      */   {
/* 1824 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(int[] array, int valueToFind)
/*      */   {
/* 1840 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(int[] array, int valueToFind, int startIndex)
/*      */   {
/* 1858 */     if (array == null) {
/* 1859 */       return -1;
/*      */     }
/* 1861 */     if (startIndex < 0) {
/* 1862 */       startIndex = 0;
/*      */     }
/* 1864 */     for (int i = startIndex; i < array.length; i++) {
/* 1865 */       if (valueToFind == array[i]) {
/* 1866 */         return i;
/*      */       }
/*      */     }
/* 1869 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(int[] array, int valueToFind)
/*      */   {
/* 1883 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(int[] array, int valueToFind, int startIndex)
/*      */   {
/* 1901 */     if (array == null) {
/* 1902 */       return -1;
/*      */     }
/* 1904 */     if (startIndex < 0)
/* 1905 */       return -1;
/* 1906 */     if (startIndex >= array.length) {
/* 1907 */       startIndex = array.length - 1;
/*      */     }
/* 1909 */     for (int i = startIndex; i >= 0; i--) {
/* 1910 */       if (valueToFind == array[i]) {
/* 1911 */         return i;
/*      */       }
/*      */     }
/* 1914 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(int[] array, int valueToFind)
/*      */   {
/* 1927 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(short[] array, short valueToFind)
/*      */   {
/* 1943 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(short[] array, short valueToFind, int startIndex)
/*      */   {
/* 1961 */     if (array == null) {
/* 1962 */       return -1;
/*      */     }
/* 1964 */     if (startIndex < 0) {
/* 1965 */       startIndex = 0;
/*      */     }
/* 1967 */     for (int i = startIndex; i < array.length; i++) {
/* 1968 */       if (valueToFind == array[i]) {
/* 1969 */         return i;
/*      */       }
/*      */     }
/* 1972 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(short[] array, short valueToFind)
/*      */   {
/* 1986 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(short[] array, short valueToFind, int startIndex)
/*      */   {
/* 2004 */     if (array == null) {
/* 2005 */       return -1;
/*      */     }
/* 2007 */     if (startIndex < 0)
/* 2008 */       return -1;
/* 2009 */     if (startIndex >= array.length) {
/* 2010 */       startIndex = array.length - 1;
/*      */     }
/* 2012 */     for (int i = startIndex; i >= 0; i--) {
/* 2013 */       if (valueToFind == array[i]) {
/* 2014 */         return i;
/*      */       }
/*      */     }
/* 2017 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(short[] array, short valueToFind)
/*      */   {
/* 2030 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(char[] array, char valueToFind)
/*      */   {
/* 2047 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(char[] array, char valueToFind, int startIndex)
/*      */   {
/* 2066 */     if (array == null) {
/* 2067 */       return -1;
/*      */     }
/* 2069 */     if (startIndex < 0) {
/* 2070 */       startIndex = 0;
/*      */     }
/* 2072 */     for (int i = startIndex; i < array.length; i++) {
/* 2073 */       if (valueToFind == array[i]) {
/* 2074 */         return i;
/*      */       }
/*      */     }
/* 2077 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(char[] array, char valueToFind)
/*      */   {
/* 2092 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(char[] array, char valueToFind, int startIndex)
/*      */   {
/* 2111 */     if (array == null) {
/* 2112 */       return -1;
/*      */     }
/* 2114 */     if (startIndex < 0)
/* 2115 */       return -1;
/* 2116 */     if (startIndex >= array.length) {
/* 2117 */       startIndex = array.length - 1;
/*      */     }
/* 2119 */     for (int i = startIndex; i >= 0; i--) {
/* 2120 */       if (valueToFind == array[i]) {
/* 2121 */         return i;
/*      */       }
/*      */     }
/* 2124 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(char[] array, char valueToFind)
/*      */   {
/* 2138 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(byte[] array, byte valueToFind)
/*      */   {
/* 2154 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(byte[] array, byte valueToFind, int startIndex)
/*      */   {
/* 2172 */     if (array == null) {
/* 2173 */       return -1;
/*      */     }
/* 2175 */     if (startIndex < 0) {
/* 2176 */       startIndex = 0;
/*      */     }
/* 2178 */     for (int i = startIndex; i < array.length; i++) {
/* 2179 */       if (valueToFind == array[i]) {
/* 2180 */         return i;
/*      */       }
/*      */     }
/* 2183 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(byte[] array, byte valueToFind)
/*      */   {
/* 2197 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex)
/*      */   {
/* 2215 */     if (array == null) {
/* 2216 */       return -1;
/*      */     }
/* 2218 */     if (startIndex < 0)
/* 2219 */       return -1;
/* 2220 */     if (startIndex >= array.length) {
/* 2221 */       startIndex = array.length - 1;
/*      */     }
/* 2223 */     for (int i = startIndex; i >= 0; i--) {
/* 2224 */       if (valueToFind == array[i]) {
/* 2225 */         return i;
/*      */       }
/*      */     }
/* 2228 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(byte[] array, byte valueToFind)
/*      */   {
/* 2241 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(double[] array, double valueToFind)
/*      */   {
/* 2257 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(double[] array, double valueToFind, double tolerance)
/*      */   {
/* 2274 */     return indexOf(array, valueToFind, 0, tolerance);
/*      */   }
/*      */ 
/*      */   public static int indexOf(double[] array, double valueToFind, int startIndex)
/*      */   {
/* 2292 */     if (isEmpty(array)) {
/* 2293 */       return -1;
/*      */     }
/* 2295 */     if (startIndex < 0) {
/* 2296 */       startIndex = 0;
/*      */     }
/* 2298 */     for (int i = startIndex; i < array.length; i++) {
/* 2299 */       if (valueToFind == array[i]) {
/* 2300 */         return i;
/*      */       }
/*      */     }
/* 2303 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance)
/*      */   {
/* 2324 */     if (isEmpty(array)) {
/* 2325 */       return -1;
/*      */     }
/* 2327 */     if (startIndex < 0) {
/* 2328 */       startIndex = 0;
/*      */     }
/* 2330 */     double min = valueToFind - tolerance;
/* 2331 */     double max = valueToFind + tolerance;
/* 2332 */     for (int i = startIndex; i < array.length; i++) {
/* 2333 */       if ((array[i] >= min) && (array[i] <= max)) {
/* 2334 */         return i;
/*      */       }
/*      */     }
/* 2337 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(double[] array, double valueToFind)
/*      */   {
/* 2351 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(double[] array, double valueToFind, double tolerance)
/*      */   {
/* 2368 */     return lastIndexOf(array, valueToFind, 2147483647, tolerance);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(double[] array, double valueToFind, int startIndex)
/*      */   {
/* 2386 */     if (isEmpty(array)) {
/* 2387 */       return -1;
/*      */     }
/* 2389 */     if (startIndex < 0)
/* 2390 */       return -1;
/* 2391 */     if (startIndex >= array.length) {
/* 2392 */       startIndex = array.length - 1;
/*      */     }
/* 2394 */     for (int i = startIndex; i >= 0; i--) {
/* 2395 */       if (valueToFind == array[i]) {
/* 2396 */         return i;
/*      */       }
/*      */     }
/* 2399 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance)
/*      */   {
/* 2420 */     if (isEmpty(array)) {
/* 2421 */       return -1;
/*      */     }
/* 2423 */     if (startIndex < 0)
/* 2424 */       return -1;
/* 2425 */     if (startIndex >= array.length) {
/* 2426 */       startIndex = array.length - 1;
/*      */     }
/* 2428 */     double min = valueToFind - tolerance;
/* 2429 */     double max = valueToFind + tolerance;
/* 2430 */     for (int i = startIndex; i >= 0; i--) {
/* 2431 */       if ((array[i] >= min) && (array[i] <= max)) {
/* 2432 */         return i;
/*      */       }
/*      */     }
/* 2435 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(double[] array, double valueToFind)
/*      */   {
/* 2448 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(double[] array, double valueToFind, double tolerance)
/*      */   {
/* 2465 */     return indexOf(array, valueToFind, 0, tolerance) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(float[] array, float valueToFind)
/*      */   {
/* 2481 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(float[] array, float valueToFind, int startIndex)
/*      */   {
/* 2499 */     if (isEmpty(array)) {
/* 2500 */       return -1;
/*      */     }
/* 2502 */     if (startIndex < 0) {
/* 2503 */       startIndex = 0;
/*      */     }
/* 2505 */     for (int i = startIndex; i < array.length; i++) {
/* 2506 */       if (valueToFind == array[i]) {
/* 2507 */         return i;
/*      */       }
/*      */     }
/* 2510 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(float[] array, float valueToFind)
/*      */   {
/* 2524 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(float[] array, float valueToFind, int startIndex)
/*      */   {
/* 2542 */     if (isEmpty(array)) {
/* 2543 */       return -1;
/*      */     }
/* 2545 */     if (startIndex < 0)
/* 2546 */       return -1;
/* 2547 */     if (startIndex >= array.length) {
/* 2548 */       startIndex = array.length - 1;
/*      */     }
/* 2550 */     for (int i = startIndex; i >= 0; i--) {
/* 2551 */       if (valueToFind == array[i]) {
/* 2552 */         return i;
/*      */       }
/*      */     }
/* 2555 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(float[] array, float valueToFind)
/*      */   {
/* 2568 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static int indexOf(boolean[] array, boolean valueToFind)
/*      */   {
/* 2584 */     return indexOf(array, valueToFind, 0);
/*      */   }
/*      */ 
/*      */   public static int indexOf(boolean[] array, boolean valueToFind, int startIndex)
/*      */   {
/* 2603 */     if (isEmpty(array)) {
/* 2604 */       return -1;
/*      */     }
/* 2606 */     if (startIndex < 0) {
/* 2607 */       startIndex = 0;
/*      */     }
/* 2609 */     for (int i = startIndex; i < array.length; i++) {
/* 2610 */       if (valueToFind == array[i]) {
/* 2611 */         return i;
/*      */       }
/*      */     }
/* 2614 */     return -1;
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(boolean[] array, boolean valueToFind)
/*      */   {
/* 2629 */     return lastIndexOf(array, valueToFind, 2147483647);
/*      */   }
/*      */ 
/*      */   public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex)
/*      */   {
/* 2647 */     if (isEmpty(array)) {
/* 2648 */       return -1;
/*      */     }
/* 2650 */     if (startIndex < 0)
/* 2651 */       return -1;
/* 2652 */     if (startIndex >= array.length) {
/* 2653 */       startIndex = array.length - 1;
/*      */     }
/* 2655 */     for (int i = startIndex; i >= 0; i--) {
/* 2656 */       if (valueToFind == array[i]) {
/* 2657 */         return i;
/*      */       }
/*      */     }
/* 2660 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean contains(boolean[] array, boolean valueToFind)
/*      */   {
/* 2673 */     return indexOf(array, valueToFind) != -1;
/*      */   }
/*      */ 
/*      */   public static char[] toPrimitive(Character[] array)
/*      */   {
/* 2691 */     if (array == null)
/* 2692 */       return null;
/* 2693 */     if (array.length == 0) {
/* 2694 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/* 2696 */     char[] result = new char[array.length];
/* 2697 */     for (int i = 0; i < array.length; i++) {
/* 2698 */       result[i] = array[i].charValue();
/*      */     }
/* 2700 */     return result;
/*      */   }
/*      */ 
/*      */   public static char[] toPrimitive(Character[] array, char valueForNull)
/*      */   {
/* 2713 */     if (array == null)
/* 2714 */       return null;
/* 2715 */     if (array.length == 0) {
/* 2716 */       return EMPTY_CHAR_ARRAY;
/*      */     }
/* 2718 */     char[] result = new char[array.length];
/* 2719 */     for (int i = 0; i < array.length; i++) {
/* 2720 */       Character b = array[i];
/* 2721 */       result[i] = (b == null ? valueForNull : b.charValue());
/*      */     }
/* 2723 */     return result;
/*      */   }
/*      */ 
/*      */   public static Character[] toObject(char[] array)
/*      */   {
/* 2735 */     if (array == null)
/* 2736 */       return null;
/* 2737 */     if (array.length == 0) {
/* 2738 */       return EMPTY_CHARACTER_OBJECT_ARRAY;
/*      */     }
/* 2740 */     Character[] result = new Character[array.length];
/* 2741 */     for (int i = 0; i < array.length; i++) {
/* 2742 */       result[i] = Character.valueOf(array[i]);
/*      */     }
/* 2744 */     return result;
/*      */   }
/*      */ 
/*      */   public static long[] toPrimitive(Long[] array)
/*      */   {
/* 2759 */     if (array == null)
/* 2760 */       return null;
/* 2761 */     if (array.length == 0) {
/* 2762 */       return EMPTY_LONG_ARRAY;
/*      */     }
/* 2764 */     long[] result = new long[array.length];
/* 2765 */     for (int i = 0; i < array.length; i++) {
/* 2766 */       result[i] = array[i].longValue();
/*      */     }
/* 2768 */     return result;
/*      */   }
/*      */ 
/*      */   public static long[] toPrimitive(Long[] array, long valueForNull)
/*      */   {
/* 2781 */     if (array == null)
/* 2782 */       return null;
/* 2783 */     if (array.length == 0) {
/* 2784 */       return EMPTY_LONG_ARRAY;
/*      */     }
/* 2786 */     long[] result = new long[array.length];
/* 2787 */     for (int i = 0; i < array.length; i++) {
/* 2788 */       Long b = array[i];
/* 2789 */       result[i] = (b == null ? valueForNull : b.longValue());
/*      */     }
/* 2791 */     return result;
/*      */   }
/*      */ 
/*      */   public static Long[] toObject(long[] array)
/*      */   {
/* 2803 */     if (array == null)
/* 2804 */       return null;
/* 2805 */     if (array.length == 0) {
/* 2806 */       return EMPTY_LONG_OBJECT_ARRAY;
/*      */     }
/* 2808 */     Long[] result = new Long[array.length];
/* 2809 */     for (int i = 0; i < array.length; i++) {
/* 2810 */       result[i] = Long.valueOf(array[i]);
/*      */     }
/* 2812 */     return result;
/*      */   }
/*      */ 
/*      */   public static int[] toPrimitive(Integer[] array)
/*      */   {
/* 2827 */     if (array == null)
/* 2828 */       return null;
/* 2829 */     if (array.length == 0) {
/* 2830 */       return EMPTY_INT_ARRAY;
/*      */     }
/* 2832 */     int[] result = new int[array.length];
/* 2833 */     for (int i = 0; i < array.length; i++) {
/* 2834 */       result[i] = array[i].intValue();
/*      */     }
/* 2836 */     return result;
/*      */   }
/*      */ 
/*      */   public static int[] toPrimitive(Integer[] array, int valueForNull)
/*      */   {
/* 2849 */     if (array == null)
/* 2850 */       return null;
/* 2851 */     if (array.length == 0) {
/* 2852 */       return EMPTY_INT_ARRAY;
/*      */     }
/* 2854 */     int[] result = new int[array.length];
/* 2855 */     for (int i = 0; i < array.length; i++) {
/* 2856 */       Integer b = array[i];
/* 2857 */       result[i] = (b == null ? valueForNull : b.intValue());
/*      */     }
/* 2859 */     return result;
/*      */   }
/*      */ 
/*      */   public static Integer[] toObject(int[] array)
/*      */   {
/* 2871 */     if (array == null)
/* 2872 */       return null;
/* 2873 */     if (array.length == 0) {
/* 2874 */       return EMPTY_INTEGER_OBJECT_ARRAY;
/*      */     }
/* 2876 */     Integer[] result = new Integer[array.length];
/* 2877 */     for (int i = 0; i < array.length; i++) {
/* 2878 */       result[i] = Integer.valueOf(array[i]);
/*      */     }
/* 2880 */     return result;
/*      */   }
/*      */ 
/*      */   public static short[] toPrimitive(Short[] array)
/*      */   {
/* 2895 */     if (array == null)
/* 2896 */       return null;
/* 2897 */     if (array.length == 0) {
/* 2898 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/* 2900 */     short[] result = new short[array.length];
/* 2901 */     for (int i = 0; i < array.length; i++) {
/* 2902 */       result[i] = array[i].shortValue();
/*      */     }
/* 2904 */     return result;
/*      */   }
/*      */ 
/*      */   public static short[] toPrimitive(Short[] array, short valueForNull)
/*      */   {
/* 2917 */     if (array == null)
/* 2918 */       return null;
/* 2919 */     if (array.length == 0) {
/* 2920 */       return EMPTY_SHORT_ARRAY;
/*      */     }
/* 2922 */     short[] result = new short[array.length];
/* 2923 */     for (int i = 0; i < array.length; i++) {
/* 2924 */       Short b = array[i];
/* 2925 */       result[i] = (b == null ? valueForNull : b.shortValue());
/*      */     }
/* 2927 */     return result;
/*      */   }
/*      */ 
/*      */   public static Short[] toObject(short[] array)
/*      */   {
/* 2939 */     if (array == null)
/* 2940 */       return null;
/* 2941 */     if (array.length == 0) {
/* 2942 */       return EMPTY_SHORT_OBJECT_ARRAY;
/*      */     }
/* 2944 */     Short[] result = new Short[array.length];
/* 2945 */     for (int i = 0; i < array.length; i++) {
/* 2946 */       result[i] = Short.valueOf(array[i]);
/*      */     }
/* 2948 */     return result;
/*      */   }
/*      */ 
/*      */   public static byte[] toPrimitive(Byte[] array)
/*      */   {
/* 2963 */     if (array == null)
/* 2964 */       return null;
/* 2965 */     if (array.length == 0) {
/* 2966 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/* 2968 */     byte[] result = new byte[array.length];
/* 2969 */     for (int i = 0; i < array.length; i++) {
/* 2970 */       result[i] = array[i].byteValue();
/*      */     }
/* 2972 */     return result;
/*      */   }
/*      */ 
/*      */   public static byte[] toPrimitive(Byte[] array, byte valueForNull)
/*      */   {
/* 2985 */     if (array == null)
/* 2986 */       return null;
/* 2987 */     if (array.length == 0) {
/* 2988 */       return EMPTY_BYTE_ARRAY;
/*      */     }
/* 2990 */     byte[] result = new byte[array.length];
/* 2991 */     for (int i = 0; i < array.length; i++) {
/* 2992 */       Byte b = array[i];
/* 2993 */       result[i] = (b == null ? valueForNull : b.byteValue());
/*      */     }
/* 2995 */     return result;
/*      */   }
/*      */ 
/*      */   public static Byte[] toObject(byte[] array)
/*      */   {
/* 3007 */     if (array == null)
/* 3008 */       return null;
/* 3009 */     if (array.length == 0) {
/* 3010 */       return EMPTY_BYTE_OBJECT_ARRAY;
/*      */     }
/* 3012 */     Byte[] result = new Byte[array.length];
/* 3013 */     for (int i = 0; i < array.length; i++) {
/* 3014 */       result[i] = Byte.valueOf(array[i]);
/*      */     }
/* 3016 */     return result;
/*      */   }
/*      */ 
/*      */   public static double[] toPrimitive(Double[] array)
/*      */   {
/* 3031 */     if (array == null)
/* 3032 */       return null;
/* 3033 */     if (array.length == 0) {
/* 3034 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/* 3036 */     double[] result = new double[array.length];
/* 3037 */     for (int i = 0; i < array.length; i++) {
/* 3038 */       result[i] = array[i].doubleValue();
/*      */     }
/* 3040 */     return result;
/*      */   }
/*      */ 
/*      */   public static double[] toPrimitive(Double[] array, double valueForNull)
/*      */   {
/* 3053 */     if (array == null)
/* 3054 */       return null;
/* 3055 */     if (array.length == 0) {
/* 3056 */       return EMPTY_DOUBLE_ARRAY;
/*      */     }
/* 3058 */     double[] result = new double[array.length];
/* 3059 */     for (int i = 0; i < array.length; i++) {
/* 3060 */       Double b = array[i];
/* 3061 */       result[i] = (b == null ? valueForNull : b.doubleValue());
/*      */     }
/* 3063 */     return result;
/*      */   }
/*      */ 
/*      */   public static Double[] toObject(double[] array)
/*      */   {
/* 3075 */     if (array == null)
/* 3076 */       return null;
/* 3077 */     if (array.length == 0) {
/* 3078 */       return EMPTY_DOUBLE_OBJECT_ARRAY;
/*      */     }
/* 3080 */     Double[] result = new Double[array.length];
/* 3081 */     for (int i = 0; i < array.length; i++) {
/* 3082 */       result[i] = Double.valueOf(array[i]);
/*      */     }
/* 3084 */     return result;
/*      */   }
/*      */ 
/*      */   public static float[] toPrimitive(Float[] array)
/*      */   {
/* 3099 */     if (array == null)
/* 3100 */       return null;
/* 3101 */     if (array.length == 0) {
/* 3102 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/* 3104 */     float[] result = new float[array.length];
/* 3105 */     for (int i = 0; i < array.length; i++) {
/* 3106 */       result[i] = array[i].floatValue();
/*      */     }
/* 3108 */     return result;
/*      */   }
/*      */ 
/*      */   public static float[] toPrimitive(Float[] array, float valueForNull)
/*      */   {
/* 3121 */     if (array == null)
/* 3122 */       return null;
/* 3123 */     if (array.length == 0) {
/* 3124 */       return EMPTY_FLOAT_ARRAY;
/*      */     }
/* 3126 */     float[] result = new float[array.length];
/* 3127 */     for (int i = 0; i < array.length; i++) {
/* 3128 */       Float b = array[i];
/* 3129 */       result[i] = (b == null ? valueForNull : b.floatValue());
/*      */     }
/* 3131 */     return result;
/*      */   }
/*      */ 
/*      */   public static Float[] toObject(float[] array)
/*      */   {
/* 3143 */     if (array == null)
/* 3144 */       return null;
/* 3145 */     if (array.length == 0) {
/* 3146 */       return EMPTY_FLOAT_OBJECT_ARRAY;
/*      */     }
/* 3148 */     Float[] result = new Float[array.length];
/* 3149 */     for (int i = 0; i < array.length; i++) {
/* 3150 */       result[i] = Float.valueOf(array[i]);
/*      */     }
/* 3152 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean[] toPrimitive(Boolean[] array)
/*      */   {
/* 3167 */     if (array == null)
/* 3168 */       return null;
/* 3169 */     if (array.length == 0) {
/* 3170 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/* 3172 */     boolean[] result = new boolean[array.length];
/* 3173 */     for (int i = 0; i < array.length; i++) {
/* 3174 */       result[i] = array[i].booleanValue();
/*      */     }
/* 3176 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull)
/*      */   {
/* 3189 */     if (array == null)
/* 3190 */       return null;
/* 3191 */     if (array.length == 0) {
/* 3192 */       return EMPTY_BOOLEAN_ARRAY;
/*      */     }
/* 3194 */     boolean[] result = new boolean[array.length];
/* 3195 */     for (int i = 0; i < array.length; i++) {
/* 3196 */       Boolean b = array[i];
/* 3197 */       result[i] = (b == null ? valueForNull : b.booleanValue());
/*      */     }
/* 3199 */     return result;
/*      */   }
/*      */ 
/*      */   public static Boolean[] toObject(boolean[] array)
/*      */   {
/* 3211 */     if (array == null)
/* 3212 */       return null;
/* 3213 */     if (array.length == 0) {
/* 3214 */       return EMPTY_BOOLEAN_OBJECT_ARRAY;
/*      */     }
/* 3216 */     Boolean[] result = new Boolean[array.length];
/* 3217 */     for (int i = 0; i < array.length; i++) {
/* 3218 */       result[i] = (array[i] != 0 ? Boolean.TRUE : Boolean.FALSE);
/*      */     }
/* 3220 */     return result;
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(Object[] array)
/*      */   {
/* 3232 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(long[] array)
/*      */   {
/* 3243 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(int[] array)
/*      */   {
/* 3254 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(short[] array)
/*      */   {
/* 3265 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(char[] array)
/*      */   {
/* 3276 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(byte[] array)
/*      */   {
/* 3287 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(double[] array)
/*      */   {
/* 3298 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(float[] array)
/*      */   {
/* 3309 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static boolean isEmpty(boolean[] array)
/*      */   {
/* 3320 */     return (array == null) || (array.length == 0);
/*      */   }
/*      */ 
/*      */   public static <T> boolean isNotEmpty(T[] array)
/*      */   {
/* 3333 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(long[] array)
/*      */   {
/* 3344 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(int[] array)
/*      */   {
/* 3355 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(short[] array)
/*      */   {
/* 3366 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(char[] array)
/*      */   {
/* 3377 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(byte[] array)
/*      */   {
/* 3388 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(double[] array)
/*      */   {
/* 3399 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(float[] array)
/*      */   {
/* 3410 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static boolean isNotEmpty(boolean[] array)
/*      */   {
/* 3421 */     return (array != null) && (array.length != 0);
/*      */   }
/*      */ 
/*      */   public static <T> T[] addAll(T[] array1, T[] array2)
/*      */   {
/* 3449 */     if (array1 == null)
/* 3450 */       return clone(array2);
/* 3451 */     if (array2 == null) {
/* 3452 */       return clone(array1);
/*      */     }
/* 3454 */     Class type1 = array1.getClass().getComponentType();
/*      */ 
/* 3456 */     Object[] joinedArray = (Object[])Array.newInstance(type1, array1.length + array2.length);
/* 3457 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/*      */     try {
/* 3459 */       System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/*      */     }
/*      */     catch (ArrayStoreException ase)
/*      */     {
/* 3467 */       Class type2 = array2.getClass().getComponentType();
/* 3468 */       if (!type1.isAssignableFrom(type2)) {
/* 3469 */         throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), ase);
/*      */       }
/*      */ 
/* 3472 */       throw ase;
/*      */     }
/* 3474 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static boolean[] addAll(boolean[] array1, boolean[] array2)
/*      */   {
/* 3495 */     if (array1 == null)
/* 3496 */       return clone(array2);
/* 3497 */     if (array2 == null) {
/* 3498 */       return clone(array1);
/*      */     }
/* 3500 */     boolean[] joinedArray = new boolean[array1.length + array2.length];
/* 3501 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3502 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3503 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static char[] addAll(char[] array1, char[] array2)
/*      */   {
/* 3524 */     if (array1 == null)
/* 3525 */       return clone(array2);
/* 3526 */     if (array2 == null) {
/* 3527 */       return clone(array1);
/*      */     }
/* 3529 */     char[] joinedArray = new char[array1.length + array2.length];
/* 3530 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3531 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3532 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static byte[] addAll(byte[] array1, byte[] array2)
/*      */   {
/* 3553 */     if (array1 == null)
/* 3554 */       return clone(array2);
/* 3555 */     if (array2 == null) {
/* 3556 */       return clone(array1);
/*      */     }
/* 3558 */     byte[] joinedArray = new byte[array1.length + array2.length];
/* 3559 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3560 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3561 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static short[] addAll(short[] array1, short[] array2)
/*      */   {
/* 3582 */     if (array1 == null)
/* 3583 */       return clone(array2);
/* 3584 */     if (array2 == null) {
/* 3585 */       return clone(array1);
/*      */     }
/* 3587 */     short[] joinedArray = new short[array1.length + array2.length];
/* 3588 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3589 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3590 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static int[] addAll(int[] array1, int[] array2)
/*      */   {
/* 3611 */     if (array1 == null)
/* 3612 */       return clone(array2);
/* 3613 */     if (array2 == null) {
/* 3614 */       return clone(array1);
/*      */     }
/* 3616 */     int[] joinedArray = new int[array1.length + array2.length];
/* 3617 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3618 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3619 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static long[] addAll(long[] array1, long[] array2)
/*      */   {
/* 3640 */     if (array1 == null)
/* 3641 */       return clone(array2);
/* 3642 */     if (array2 == null) {
/* 3643 */       return clone(array1);
/*      */     }
/* 3645 */     long[] joinedArray = new long[array1.length + array2.length];
/* 3646 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3647 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3648 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static float[] addAll(float[] array1, float[] array2)
/*      */   {
/* 3669 */     if (array1 == null)
/* 3670 */       return clone(array2);
/* 3671 */     if (array2 == null) {
/* 3672 */       return clone(array1);
/*      */     }
/* 3674 */     float[] joinedArray = new float[array1.length + array2.length];
/* 3675 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3676 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3677 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static double[] addAll(double[] array1, double[] array2)
/*      */   {
/* 3698 */     if (array1 == null)
/* 3699 */       return clone(array2);
/* 3700 */     if (array2 == null) {
/* 3701 */       return clone(array1);
/*      */     }
/* 3703 */     double[] joinedArray = new double[array1.length + array2.length];
/* 3704 */     System.arraycopy(array1, 0, joinedArray, 0, array1.length);
/* 3705 */     System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
/* 3706 */     return joinedArray;
/*      */   }
/*      */ 
/*      */   public static <T> T[] add(T[] array, T element)
/*      */   {
/*      */     Class type;
/* 3740 */     if (array != null) {
/* 3741 */       type = array.getClass();
/*      */     }
/*      */     else
/*      */     {
/*      */       Class type;
/* 3742 */       if (element != null)
/* 3743 */         type = element.getClass();
/*      */       else
/* 3745 */         throw new IllegalArgumentException("Arguments cannot both be null");
/*      */     }
/*      */     Class type;
/* 3748 */     Object[] newArray = (Object[])copyArrayGrow1(array, type);
/* 3749 */     newArray[(newArray.length - 1)] = element;
/* 3750 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static boolean[] add(boolean[] array, boolean element)
/*      */   {
/* 3775 */     boolean[] newArray = (boolean[])copyArrayGrow1(array, Boolean.TYPE);
/* 3776 */     newArray[(newArray.length - 1)] = element;
/* 3777 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static byte[] add(byte[] array, byte element)
/*      */   {
/* 3802 */     byte[] newArray = (byte[])copyArrayGrow1(array, Byte.TYPE);
/* 3803 */     newArray[(newArray.length - 1)] = element;
/* 3804 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static char[] add(char[] array, char element)
/*      */   {
/* 3829 */     char[] newArray = (char[])copyArrayGrow1(array, Character.TYPE);
/* 3830 */     newArray[(newArray.length - 1)] = element;
/* 3831 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static double[] add(double[] array, double element)
/*      */   {
/* 3856 */     double[] newArray = (double[])copyArrayGrow1(array, Double.TYPE);
/* 3857 */     newArray[(newArray.length - 1)] = element;
/* 3858 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static float[] add(float[] array, float element)
/*      */   {
/* 3883 */     float[] newArray = (float[])copyArrayGrow1(array, Float.TYPE);
/* 3884 */     newArray[(newArray.length - 1)] = element;
/* 3885 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static int[] add(int[] array, int element)
/*      */   {
/* 3910 */     int[] newArray = (int[])copyArrayGrow1(array, Integer.TYPE);
/* 3911 */     newArray[(newArray.length - 1)] = element;
/* 3912 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static long[] add(long[] array, long element)
/*      */   {
/* 3937 */     long[] newArray = (long[])copyArrayGrow1(array, Long.TYPE);
/* 3938 */     newArray[(newArray.length - 1)] = element;
/* 3939 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static short[] add(short[] array, short element)
/*      */   {
/* 3964 */     short[] newArray = (short[])copyArrayGrow1(array, Short.TYPE);
/* 3965 */     newArray[(newArray.length - 1)] = element;
/* 3966 */     return newArray;
/*      */   }
/*      */ 
/*      */   private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType)
/*      */   {
/* 3979 */     if (array != null) {
/* 3980 */       int arrayLength = Array.getLength(array);
/* 3981 */       Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
/* 3982 */       System.arraycopy(array, 0, newArray, 0, arrayLength);
/* 3983 */       return newArray;
/*      */     }
/* 3985 */     return Array.newInstance(newArrayComponentType, 1);
/*      */   }
/*      */ 
/*      */   public static <T> T[] add(T[] array, int index, T element)
/*      */   {
/* 4019 */     Class clss = null;
/* 4020 */     if (array != null)
/* 4021 */       clss = array.getClass().getComponentType();
/* 4022 */     else if (element != null)
/* 4023 */       clss = element.getClass();
/*      */     else {
/* 4025 */       throw new IllegalArgumentException("Array and element cannot both be null");
/*      */     }
/*      */ 
/* 4028 */     Object[] newArray = (Object[])add(array, index, element, clss);
/* 4029 */     return newArray;
/*      */   }
/*      */ 
/*      */   public static boolean[] add(boolean[] array, int index, boolean element)
/*      */   {
/* 4060 */     return (boolean[])add(array, index, Boolean.valueOf(element), Boolean.TYPE);
/*      */   }
/*      */ 
/*      */   public static char[] add(char[] array, int index, char element)
/*      */   {
/* 4092 */     return (char[])add(array, index, Character.valueOf(element), Character.TYPE);
/*      */   }
/*      */ 
/*      */   public static byte[] add(byte[] array, int index, byte element)
/*      */   {
/* 4123 */     return (byte[])add(array, index, Byte.valueOf(element), Byte.TYPE);
/*      */   }
/*      */ 
/*      */   public static short[] add(short[] array, int index, short element)
/*      */   {
/* 4154 */     return (short[])add(array, index, Short.valueOf(element), Short.TYPE);
/*      */   }
/*      */ 
/*      */   public static int[] add(int[] array, int index, int element)
/*      */   {
/* 4185 */     return (int[])add(array, index, Integer.valueOf(element), Integer.TYPE);
/*      */   }
/*      */ 
/*      */   public static long[] add(long[] array, int index, long element)
/*      */   {
/* 4216 */     return (long[])add(array, index, Long.valueOf(element), Long.TYPE);
/*      */   }
/*      */ 
/*      */   public static float[] add(float[] array, int index, float element)
/*      */   {
/* 4247 */     return (float[])add(array, index, Float.valueOf(element), Float.TYPE);
/*      */   }
/*      */ 
/*      */   public static double[] add(double[] array, int index, double element)
/*      */   {
/* 4278 */     return (double[])add(array, index, Double.valueOf(element), Double.TYPE);
/*      */   }
/*      */ 
/*      */   private static Object add(Object array, int index, Object element, Class<?> clss)
/*      */   {
/* 4293 */     if (array == null) {
/* 4294 */       if (index != 0) {
/* 4295 */         throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
/*      */       }
/* 4297 */       Object joinedArray = Array.newInstance(clss, 1);
/* 4298 */       Array.set(joinedArray, 0, element);
/* 4299 */       return joinedArray;
/*      */     }
/* 4301 */     int length = Array.getLength(array);
/* 4302 */     if ((index > length) || (index < 0)) {
/* 4303 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/*      */     }
/* 4305 */     Object result = Array.newInstance(clss, length + 1);
/* 4306 */     System.arraycopy(array, 0, result, 0, index);
/* 4307 */     Array.set(result, index, element);
/* 4308 */     if (index < length) {
/* 4309 */       System.arraycopy(array, index, result, index + 1, length - index);
/*      */     }
/* 4311 */     return result;
/*      */   }
/*      */ 
/*      */   public static <T> T[] remove(T[] array, int index)
/*      */   {
/* 4345 */     return (Object[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static <T> T[] removeElement(T[] array, Object element)
/*      */   {
/* 4375 */     int index = indexOf(array, element);
/* 4376 */     if (index == -1) {
/* 4377 */       return clone(array);
/*      */     }
/* 4379 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static boolean[] remove(boolean[] array, int index)
/*      */   {
/* 4411 */     return (boolean[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static boolean[] removeElement(boolean[] array, boolean element)
/*      */   {
/* 4440 */     int index = indexOf(array, element);
/* 4441 */     if (index == -1) {
/* 4442 */       return clone(array);
/*      */     }
/* 4444 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static byte[] remove(byte[] array, int index)
/*      */   {
/* 4476 */     return (byte[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static byte[] removeElement(byte[] array, byte element)
/*      */   {
/* 4505 */     int index = indexOf(array, element);
/* 4506 */     if (index == -1) {
/* 4507 */       return clone(array);
/*      */     }
/* 4509 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static char[] remove(char[] array, int index)
/*      */   {
/* 4541 */     return (char[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static char[] removeElement(char[] array, char element)
/*      */   {
/* 4570 */     int index = indexOf(array, element);
/* 4571 */     if (index == -1) {
/* 4572 */       return clone(array);
/*      */     }
/* 4574 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static double[] remove(double[] array, int index)
/*      */   {
/* 4606 */     return (double[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static double[] removeElement(double[] array, double element)
/*      */   {
/* 4635 */     int index = indexOf(array, element);
/* 4636 */     if (index == -1) {
/* 4637 */       return clone(array);
/*      */     }
/* 4639 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static float[] remove(float[] array, int index)
/*      */   {
/* 4671 */     return (float[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static float[] removeElement(float[] array, float element)
/*      */   {
/* 4700 */     int index = indexOf(array, element);
/* 4701 */     if (index == -1) {
/* 4702 */       return clone(array);
/*      */     }
/* 4704 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static int[] remove(int[] array, int index)
/*      */   {
/* 4736 */     return (int[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static int[] removeElement(int[] array, int element)
/*      */   {
/* 4765 */     int index = indexOf(array, element);
/* 4766 */     if (index == -1) {
/* 4767 */       return clone(array);
/*      */     }
/* 4769 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static long[] remove(long[] array, int index)
/*      */   {
/* 4801 */     return (long[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static long[] removeElement(long[] array, long element)
/*      */   {
/* 4830 */     int index = indexOf(array, element);
/* 4831 */     if (index == -1) {
/* 4832 */       return clone(array);
/*      */     }
/* 4834 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   public static short[] remove(short[] array, int index)
/*      */   {
/* 4866 */     return (short[])remove(array, index);
/*      */   }
/*      */ 
/*      */   public static short[] removeElement(short[] array, short element)
/*      */   {
/* 4895 */     int index = indexOf(array, element);
/* 4896 */     if (index == -1) {
/* 4897 */       return clone(array);
/*      */     }
/* 4899 */     return remove(array, index);
/*      */   }
/*      */ 
/*      */   private static Object remove(Object array, int index)
/*      */   {
/* 4924 */     int length = getLength(array);
/* 4925 */     if ((index < 0) || (index >= length)) {
/* 4926 */       throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/*      */     }
/*      */ 
/* 4929 */     Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
/* 4930 */     System.arraycopy(array, 0, result, 0, index);
/* 4931 */     if (index < length - 1) {
/* 4932 */       System.arraycopy(array, index + 1, result, index, length - index - 1);
/*      */     }
/*      */ 
/* 4935 */     return result;
/*      */   }
/*      */ 
/*      */   public static <T> T[] removeAll(T[] array, int[] indices)
/*      */   {
/* 4967 */     return (Object[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static <T> T[] removeElements(T[] array, T[] values)
/*      */   {
/* 4999 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5000 */       return clone(array);
/*      */     }
/* 5002 */     HashMap occurrences = new HashMap(values.length);
/* 5003 */     for (Object v : values) {
/* 5004 */       MutableInt count = (MutableInt)occurrences.get(v);
/* 5005 */       if (count == null)
/* 5006 */         occurrences.put(v, new MutableInt(1));
/*      */       else {
/* 5008 */         count.increment();
/*      */       }
/*      */     }
/* 5011 */     HashSet toRemove = new HashSet();
/* 5012 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5013 */       Object v = e.getKey();
/* 5014 */       int found = 0;
/* 5015 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5016 */         found = indexOf(array, v, found);
/* 5017 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5020 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5023 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static byte[] removeAll(byte[] array, int[] indices)
/*      */   {
/* 5056 */     return (byte[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static byte[] removeElements(byte[] array, byte[] values)
/*      */   {
/* 5087 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5088 */       return clone(array);
/*      */     }
/* 5090 */     HashMap occurrences = new HashMap(values.length);
/* 5091 */     for (byte v : values) {
/* 5092 */       Byte boxed = Byte.valueOf(v);
/* 5093 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5094 */       if (count == null)
/* 5095 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5097 */         count.increment();
/*      */       }
/*      */     }
/* 5100 */     HashSet toRemove = new HashSet();
/* 5101 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5102 */       Byte v = (Byte)e.getKey();
/* 5103 */       int found = 0;
/* 5104 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5105 */         found = indexOf(array, v.byteValue(), found);
/* 5106 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5109 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5112 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static short[] removeAll(short[] array, int[] indices)
/*      */   {
/* 5145 */     return (short[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static short[] removeElements(short[] array, short[] values)
/*      */   {
/* 5176 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5177 */       return clone(array);
/*      */     }
/* 5179 */     HashMap occurrences = new HashMap(values.length);
/* 5180 */     for (short v : values) {
/* 5181 */       Short boxed = Short.valueOf(v);
/* 5182 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5183 */       if (count == null)
/* 5184 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5186 */         count.increment();
/*      */       }
/*      */     }
/* 5189 */     HashSet toRemove = new HashSet();
/* 5190 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5191 */       Short v = (Short)e.getKey();
/* 5192 */       int found = 0;
/* 5193 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5194 */         found = indexOf(array, v.shortValue(), found);
/* 5195 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5198 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5201 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static int[] removeAll(int[] array, int[] indices)
/*      */   {
/* 5234 */     return (int[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static int[] removeElements(int[] array, int[] values)
/*      */   {
/* 5265 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5266 */       return clone(array);
/*      */     }
/* 5268 */     HashMap occurrences = new HashMap(values.length);
/* 5269 */     for (int v : values) {
/* 5270 */       Integer boxed = Integer.valueOf(v);
/* 5271 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5272 */       if (count == null)
/* 5273 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5275 */         count.increment();
/*      */       }
/*      */     }
/* 5278 */     HashSet toRemove = new HashSet();
/* 5279 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5280 */       Integer v = (Integer)e.getKey();
/* 5281 */       int found = 0;
/* 5282 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5283 */         found = indexOf(array, v.intValue(), found);
/* 5284 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5287 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5290 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static char[] removeAll(char[] array, int[] indices)
/*      */   {
/* 5323 */     return (char[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static char[] removeElements(char[] array, char[] values)
/*      */   {
/* 5354 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5355 */       return clone(array);
/*      */     }
/* 5357 */     HashMap occurrences = new HashMap(values.length);
/* 5358 */     for (char v : values) {
/* 5359 */       Character boxed = Character.valueOf(v);
/* 5360 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5361 */       if (count == null)
/* 5362 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5364 */         count.increment();
/*      */       }
/*      */     }
/* 5367 */     HashSet toRemove = new HashSet();
/* 5368 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5369 */       Character v = (Character)e.getKey();
/* 5370 */       int found = 0;
/* 5371 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5372 */         found = indexOf(array, v.charValue(), found);
/* 5373 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5376 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5379 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static long[] removeAll(long[] array, int[] indices)
/*      */   {
/* 5412 */     return (long[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static long[] removeElements(long[] array, long[] values)
/*      */   {
/* 5443 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5444 */       return clone(array);
/*      */     }
/* 5446 */     HashMap occurrences = new HashMap(values.length);
/* 5447 */     for (long v : values) {
/* 5448 */       Long boxed = Long.valueOf(v);
/* 5449 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5450 */       if (count == null)
/* 5451 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5453 */         count.increment();
/*      */       }
/*      */     }
/* 5456 */     HashSet toRemove = new HashSet();
/* 5457 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5458 */       Long v = (Long)e.getKey();
/* 5459 */       int found = 0;
/* 5460 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5461 */         found = indexOf(array, v.longValue(), found);
/* 5462 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5465 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5468 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static float[] removeAll(float[] array, int[] indices)
/*      */   {
/* 5501 */     return (float[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static float[] removeElements(float[] array, float[] values)
/*      */   {
/* 5532 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5533 */       return clone(array);
/*      */     }
/* 5535 */     HashMap occurrences = new HashMap(values.length);
/* 5536 */     for (float v : values) {
/* 5537 */       Float boxed = Float.valueOf(v);
/* 5538 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5539 */       if (count == null)
/* 5540 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5542 */         count.increment();
/*      */       }
/*      */     }
/* 5545 */     HashSet toRemove = new HashSet();
/* 5546 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5547 */       Float v = (Float)e.getKey();
/* 5548 */       int found = 0;
/* 5549 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5550 */         found = indexOf(array, v.floatValue(), found);
/* 5551 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5554 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5557 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static double[] removeAll(double[] array, int[] indices)
/*      */   {
/* 5590 */     return (double[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static double[] removeElements(double[] array, double[] values)
/*      */   {
/* 5621 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5622 */       return clone(array);
/*      */     }
/* 5624 */     HashMap occurrences = new HashMap(values.length);
/* 5625 */     for (double v : values) {
/* 5626 */       Double boxed = Double.valueOf(v);
/* 5627 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5628 */       if (count == null)
/* 5629 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5631 */         count.increment();
/*      */       }
/*      */     }
/* 5634 */     HashSet toRemove = new HashSet();
/* 5635 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5636 */       Double v = (Double)e.getKey();
/* 5637 */       int found = 0;
/* 5638 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5639 */         found = indexOf(array, v.doubleValue(), found);
/* 5640 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5643 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5646 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   public static boolean[] removeAll(boolean[] array, int[] indices)
/*      */   {
/* 5675 */     return (boolean[])removeAll(array, clone(indices));
/*      */   }
/*      */ 
/*      */   public static boolean[] removeElements(boolean[] array, boolean[] values)
/*      */   {
/* 5706 */     if ((isEmpty(array)) || (isEmpty(values))) {
/* 5707 */       return clone(array);
/*      */     }
/* 5709 */     HashMap occurrences = new HashMap(values.length);
/* 5710 */     for (boolean v : values) {
/* 5711 */       Boolean boxed = Boolean.valueOf(v);
/* 5712 */       MutableInt count = (MutableInt)occurrences.get(boxed);
/* 5713 */       if (count == null)
/* 5714 */         occurrences.put(boxed, new MutableInt(1));
/*      */       else {
/* 5716 */         count.increment();
/*      */       }
/*      */     }
/* 5719 */     HashSet toRemove = new HashSet();
/* 5720 */     for (Map.Entry e : occurrences.entrySet()) {
/* 5721 */       Boolean v = (Boolean)e.getKey();
/* 5722 */       int found = 0;
/* 5723 */       int i = 0; for (int ct = ((MutableInt)e.getValue()).intValue(); i < ct; i++) {
/* 5724 */         found = indexOf(array, v.booleanValue(), found);
/* 5725 */         if (found < 0) {
/*      */           break;
/*      */         }
/* 5728 */         toRemove.add(Integer.valueOf(found++));
/*      */       }
/*      */     }
/* 5731 */     return removeAll(array, extractIndices(toRemove));
/*      */   }
/*      */ 
/*      */   private static Object removeAll(Object array, int[] indices)
/*      */   {
/* 5742 */     int length = getLength(array);
/* 5743 */     int diff = 0;
/*      */ 
/* 5745 */     if (isNotEmpty(indices)) {
/* 5746 */       Arrays.sort(indices);
/*      */ 
/* 5748 */       int i = indices.length;
/* 5749 */       int prevIndex = length;
/*      */       while (true) { i--; if (i < 0) break;
/* 5751 */         int index = indices[i];
/* 5752 */         if ((index < 0) || (index >= length)) {
/* 5753 */           throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
/*      */         }
/* 5755 */         if (index < prevIndex)
/*      */         {
/* 5758 */           diff++;
/* 5759 */           prevIndex = index;
/*      */         } }
/*      */     }
/* 5762 */     Object result = Array.newInstance(array.getClass().getComponentType(), length - diff);
/* 5763 */     if (diff < length) {
/* 5764 */       int end = length;
/* 5765 */       int dest = length - diff;
/* 5766 */       for (int i = indices.length - 1; i >= 0; i--) {
/* 5767 */         int index = indices[i];
/* 5768 */         if (end - index > 1) {
/* 5769 */           int cp = end - index - 1;
/* 5770 */           dest -= cp;
/* 5771 */           System.arraycopy(array, index + 1, result, dest, cp);
/*      */         }
/* 5773 */         end = index;
/*      */       }
/* 5775 */       if (end > 0) {
/* 5776 */         System.arraycopy(array, 0, result, 0, end);
/*      */       }
/*      */     }
/* 5779 */     return result;
/*      */   }
/*      */ 
/*      */   private static int[] extractIndices(HashSet<Integer> coll)
/*      */   {
/* 5789 */     int[] result = new int[coll.size()];
/* 5790 */     int i = 0;
/* 5791 */     for (Integer index : coll) {
/* 5792 */       result[(i++)] = index.intValue();
/*      */     }
/* 5794 */     return result;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.ArrayUtils
 * JD-Core Version:    0.6.2
 */