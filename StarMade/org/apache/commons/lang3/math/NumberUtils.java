/*      */ package org.apache.commons.lang3.math;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import org.apache.commons.lang3.StringUtils;
/*      */ 
/*      */ public class NumberUtils
/*      */ {
/*   33 */   public static final Long LONG_ZERO = Long.valueOf(0L);
/*      */ 
/*   35 */   public static final Long LONG_ONE = Long.valueOf(1L);
/*      */ 
/*   37 */   public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
/*      */ 
/*   39 */   public static final Integer INTEGER_ZERO = Integer.valueOf(0);
/*      */ 
/*   41 */   public static final Integer INTEGER_ONE = Integer.valueOf(1);
/*      */ 
/*   43 */   public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
/*      */ 
/*   45 */   public static final Short SHORT_ZERO = Short.valueOf((short)0);
/*      */ 
/*   47 */   public static final Short SHORT_ONE = Short.valueOf((short)1);
/*      */ 
/*   49 */   public static final Short SHORT_MINUS_ONE = Short.valueOf((short)-1);
/*      */ 
/*   51 */   public static final Byte BYTE_ZERO = Byte.valueOf((byte)0);
/*      */ 
/*   53 */   public static final Byte BYTE_ONE = Byte.valueOf((byte)1);
/*      */ 
/*   55 */   public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte)-1);
/*      */ 
/*   57 */   public static final Double DOUBLE_ZERO = Double.valueOf(0.0D);
/*      */ 
/*   59 */   public static final Double DOUBLE_ONE = Double.valueOf(1.0D);
/*      */ 
/*   61 */   public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0D);
/*      */ 
/*   63 */   public static final Float FLOAT_ZERO = Float.valueOf(0.0F);
/*      */ 
/*   65 */   public static final Float FLOAT_ONE = Float.valueOf(1.0F);
/*      */ 
/*   67 */   public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0F);
/*      */ 
/*      */   public static int toInt(String str)
/*      */   {
/*   99 */     return toInt(str, 0);
/*      */   }
/*      */ 
/*      */   public static int toInt(String str, int defaultValue)
/*      */   {
/*  120 */     if (str == null)
/*  121 */       return defaultValue;
/*      */     try
/*      */     {
/*  124 */       return Integer.parseInt(str); } catch (NumberFormatException nfe) {
/*      */     }
/*  126 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   public static long toLong(String str)
/*      */   {
/*  148 */     return toLong(str, 0L);
/*      */   }
/*      */ 
/*      */   public static long toLong(String str, long defaultValue)
/*      */   {
/*  169 */     if (str == null)
/*  170 */       return defaultValue;
/*      */     try
/*      */     {
/*  173 */       return Long.parseLong(str); } catch (NumberFormatException nfe) {
/*      */     }
/*  175 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   public static float toFloat(String str)
/*      */   {
/*  198 */     return toFloat(str, 0.0F);
/*      */   }
/*      */ 
/*      */   public static float toFloat(String str, float defaultValue)
/*      */   {
/*  221 */     if (str == null)
/*  222 */       return defaultValue;
/*      */     try
/*      */     {
/*  225 */       return Float.parseFloat(str); } catch (NumberFormatException nfe) {
/*      */     }
/*  227 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   public static double toDouble(String str)
/*      */   {
/*  250 */     return toDouble(str, 0.0D);
/*      */   }
/*      */ 
/*      */   public static double toDouble(String str, double defaultValue)
/*      */   {
/*  273 */     if (str == null)
/*  274 */       return defaultValue;
/*      */     try
/*      */     {
/*  277 */       return Double.parseDouble(str); } catch (NumberFormatException nfe) {
/*      */     }
/*  279 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   public static byte toByte(String str)
/*      */   {
/*  302 */     return toByte(str, (byte)0);
/*      */   }
/*      */ 
/*      */   public static byte toByte(String str, byte defaultValue)
/*      */   {
/*  323 */     if (str == null)
/*  324 */       return defaultValue;
/*      */     try
/*      */     {
/*  327 */       return Byte.parseByte(str); } catch (NumberFormatException nfe) {
/*      */     }
/*  329 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   public static short toShort(String str)
/*      */   {
/*  351 */     return toShort(str, (short)0);
/*      */   }
/*      */ 
/*      */   public static short toShort(String str, short defaultValue)
/*      */   {
/*  372 */     if (str == null)
/*  373 */       return defaultValue;
/*      */     try
/*      */     {
/*  376 */       return Short.parseShort(str); } catch (NumberFormatException nfe) {
/*      */     }
/*  378 */     return defaultValue;
/*      */   }
/*      */ 
/*      */   public static Number createNumber(String str)
/*      */     throws NumberFormatException
/*      */   {
/*  445 */     if (str == null) {
/*  446 */       return null;
/*      */     }
/*  448 */     if (StringUtils.isBlank(str)) {
/*  449 */       throw new NumberFormatException("A blank string is not a valid number");
/*      */     }
/*  451 */     if (str.startsWith("--"))
/*      */     {
/*  456 */       return null;
/*      */     }
/*  458 */     if ((str.startsWith("0x")) || (str.startsWith("-0x")) || (str.startsWith("0X")) || (str.startsWith("-0X"))) {
/*  459 */       return createInteger(str);
/*      */     }
/*  461 */     char lastChar = str.charAt(str.length() - 1);
/*      */ 
/*  465 */     int decPos = str.indexOf('.');
/*  466 */     int expPos = str.indexOf('e') + str.indexOf('E') + 1;
/*      */     String mant;
/*      */     String mant;
/*      */     String dec;
/*  468 */     if (decPos > -1)
/*      */     {
/*      */       String dec;
/*      */       String dec;
/*  470 */       if (expPos > -1) {
/*  471 */         if ((expPos < decPos) || (expPos > str.length())) {
/*  472 */           throw new NumberFormatException(str + " is not a valid number.");
/*      */         }
/*  474 */         dec = str.substring(decPos + 1, expPos);
/*      */       } else {
/*  476 */         dec = str.substring(decPos + 1);
/*      */       }
/*  478 */       mant = str.substring(0, decPos);
/*      */     }
/*      */     else
/*      */     {
/*      */       String mant;
/*  480 */       if (expPos > -1) {
/*  481 */         if (expPos > str.length()) {
/*  482 */           throw new NumberFormatException(str + " is not a valid number.");
/*      */         }
/*  484 */         mant = str.substring(0, expPos);
/*      */       } else {
/*  486 */         mant = str;
/*      */       }
/*  488 */       dec = null;
/*      */     }
/*  490 */     if ((!Character.isDigit(lastChar)) && (lastChar != '.'))
/*      */     {
/*      */       String exp;
/*      */       String exp;
/*  491 */       if ((expPos > -1) && (expPos < str.length() - 1))
/*  492 */         exp = str.substring(expPos + 1, str.length() - 1);
/*      */       else {
/*  494 */         exp = null;
/*      */       }
/*      */ 
/*  497 */       String numeric = str.substring(0, str.length() - 1);
/*  498 */       boolean allZeros = (isAllZeros(mant)) && (isAllZeros(exp));
/*  499 */       switch (lastChar) {
/*      */       case 'L':
/*      */       case 'l':
/*  502 */         if ((dec == null) && (exp == null) && (((numeric.charAt(0) == '-') && (isDigits(numeric.substring(1)))) || (isDigits(numeric))))
/*      */         {
/*      */           try
/*      */           {
/*  506 */             return createLong(numeric);
/*      */           }
/*      */           catch (NumberFormatException nfe)
/*      */           {
/*  510 */             return createBigInteger(numeric);
/*      */           }
/*      */         }
/*  513 */         throw new NumberFormatException(str + " is not a valid number.");
/*      */       case 'F':
/*      */       case 'f':
/*      */         try {
/*  517 */           Float f = createFloat(numeric);
/*  518 */           if ((!f.isInfinite()) && ((f.floatValue() != 0.0F) || (allZeros)))
/*      */           {
/*  521 */             return f;
/*      */           }
/*      */         }
/*      */         catch (NumberFormatException nfe)
/*      */         {
/*      */         }
/*      */       case 'D':
/*      */       case 'd':
/*      */         try
/*      */         {
/*  531 */           Double d = createDouble(numeric);
/*  532 */           if ((!d.isInfinite()) && ((d.floatValue() != 0.0D) || (allZeros)))
/*  533 */             return d;
/*      */         }
/*      */         catch (NumberFormatException nfe)
/*      */         {
/*      */         }
/*      */         try {
/*  539 */           return createBigDecimal(numeric);
/*      */         }
/*      */         catch (NumberFormatException e)
/*      */         {
/*      */         }
/*      */       }
/*  545 */       throw new NumberFormatException(str + " is not a valid number.");
/*      */     }
/*      */     String exp;
/*      */     String exp;
/*  551 */     if ((expPos > -1) && (expPos < str.length() - 1))
/*  552 */       exp = str.substring(expPos + 1, str.length());
/*      */     else {
/*  554 */       exp = null;
/*      */     }
/*  556 */     if ((dec == null) && (exp == null)) {
/*      */       try
/*      */       {
/*  559 */         return createInteger(str);
/*      */       }
/*      */       catch (NumberFormatException nfe)
/*      */       {
/*      */         try {
/*  564 */           return createLong(str);
/*      */         }
/*      */         catch (NumberFormatException nfe)
/*      */         {
/*  568 */           return createBigInteger(str);
/*      */         }
/*      */       }
/*      */     }
/*  572 */     boolean allZeros = (isAllZeros(mant)) && (isAllZeros(exp));
/*      */     try {
/*  574 */       Float f = createFloat(str);
/*  575 */       if ((!f.isInfinite()) && ((f.floatValue() != 0.0F) || (allZeros)))
/*  576 */         return f;
/*      */     }
/*      */     catch (NumberFormatException nfe)
/*      */     {
/*      */     }
/*      */     try {
/*  582 */       Double d = createDouble(str);
/*  583 */       if ((!d.isInfinite()) && ((d.doubleValue() != 0.0D) || (allZeros))) {
/*  584 */         return d;
/*      */       }
/*      */     }
/*      */     catch (NumberFormatException nfe)
/*      */     {
/*      */     }
/*  590 */     return createBigDecimal(str);
/*      */   }
/*      */ 
/*      */   private static boolean isAllZeros(String str)
/*      */   {
/*  605 */     if (str == null) {
/*  606 */       return true;
/*      */     }
/*  608 */     for (int i = str.length() - 1; i >= 0; i--) {
/*  609 */       if (str.charAt(i) != '0') {
/*  610 */         return false;
/*      */       }
/*      */     }
/*  613 */     return str.length() > 0;
/*      */   }
/*      */ 
/*      */   public static Float createFloat(String str)
/*      */   {
/*  627 */     if (str == null) {
/*  628 */       return null;
/*      */     }
/*  630 */     return Float.valueOf(str);
/*      */   }
/*      */ 
/*      */   public static Double createDouble(String str)
/*      */   {
/*  643 */     if (str == null) {
/*  644 */       return null;
/*      */     }
/*  646 */     return Double.valueOf(str);
/*      */   }
/*      */ 
/*      */   public static Integer createInteger(String str)
/*      */   {
/*  660 */     if (str == null) {
/*  661 */       return null;
/*      */     }
/*      */ 
/*  664 */     return Integer.decode(str);
/*      */   }
/*      */ 
/*      */   public static Long createLong(String str)
/*      */   {
/*  678 */     if (str == null) {
/*  679 */       return null;
/*      */     }
/*  681 */     return Long.decode(str);
/*      */   }
/*      */ 
/*      */   public static BigInteger createBigInteger(String str)
/*      */   {
/*  694 */     if (str == null) {
/*  695 */       return null;
/*      */     }
/*  697 */     return new BigInteger(str);
/*      */   }
/*      */ 
/*      */   public static BigDecimal createBigDecimal(String str)
/*      */   {
/*  710 */     if (str == null) {
/*  711 */       return null;
/*      */     }
/*      */ 
/*  714 */     if (StringUtils.isBlank(str)) {
/*  715 */       throw new NumberFormatException("A blank string is not a valid number");
/*      */     }
/*  717 */     return new BigDecimal(str);
/*      */   }
/*      */ 
/*      */   public static long min(long[] array)
/*      */   {
/*  732 */     if (array == null)
/*  733 */       throw new IllegalArgumentException("The Array must not be null");
/*  734 */     if (array.length == 0) {
/*  735 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  739 */     long min = array[0];
/*  740 */     for (int i = 1; i < array.length; i++) {
/*  741 */       if (array[i] < min) {
/*  742 */         min = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  746 */     return min;
/*      */   }
/*      */ 
/*      */   public static int min(int[] array)
/*      */   {
/*  759 */     if (array == null)
/*  760 */       throw new IllegalArgumentException("The Array must not be null");
/*  761 */     if (array.length == 0) {
/*  762 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  766 */     int min = array[0];
/*  767 */     for (int j = 1; j < array.length; j++) {
/*  768 */       if (array[j] < min) {
/*  769 */         min = array[j];
/*      */       }
/*      */     }
/*      */ 
/*  773 */     return min;
/*      */   }
/*      */ 
/*      */   public static short min(short[] array)
/*      */   {
/*  786 */     if (array == null)
/*  787 */       throw new IllegalArgumentException("The Array must not be null");
/*  788 */     if (array.length == 0) {
/*  789 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  793 */     short min = array[0];
/*  794 */     for (int i = 1; i < array.length; i++) {
/*  795 */       if (array[i] < min) {
/*  796 */         min = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  800 */     return min;
/*      */   }
/*      */ 
/*      */   public static byte min(byte[] array)
/*      */   {
/*  813 */     if (array == null)
/*  814 */       throw new IllegalArgumentException("The Array must not be null");
/*  815 */     if (array.length == 0) {
/*  816 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  820 */     byte min = array[0];
/*  821 */     for (int i = 1; i < array.length; i++) {
/*  822 */       if (array[i] < min) {
/*  823 */         min = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  827 */     return min;
/*      */   }
/*      */ 
/*      */   public static double min(double[] array)
/*      */   {
/*  841 */     if (array == null)
/*  842 */       throw new IllegalArgumentException("The Array must not be null");
/*  843 */     if (array.length == 0) {
/*  844 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  848 */     double min = array[0];
/*  849 */     for (int i = 1; i < array.length; i++) {
/*  850 */       if (Double.isNaN(array[i])) {
/*  851 */         return (0.0D / 0.0D);
/*      */       }
/*  853 */       if (array[i] < min) {
/*  854 */         min = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  858 */     return min;
/*      */   }
/*      */ 
/*      */   public static float min(float[] array)
/*      */   {
/*  872 */     if (array == null)
/*  873 */       throw new IllegalArgumentException("The Array must not be null");
/*  874 */     if (array.length == 0) {
/*  875 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  879 */     float min = array[0];
/*  880 */     for (int i = 1; i < array.length; i++) {
/*  881 */       if (Float.isNaN(array[i])) {
/*  882 */         return (0.0F / 0.0F);
/*      */       }
/*  884 */       if (array[i] < min) {
/*  885 */         min = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  889 */     return min;
/*      */   }
/*      */ 
/*      */   public static long max(long[] array)
/*      */   {
/*  904 */     if (array == null)
/*  905 */       throw new IllegalArgumentException("The Array must not be null");
/*  906 */     if (array.length == 0) {
/*  907 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  911 */     long max = array[0];
/*  912 */     for (int j = 1; j < array.length; j++) {
/*  913 */       if (array[j] > max) {
/*  914 */         max = array[j];
/*      */       }
/*      */     }
/*      */ 
/*  918 */     return max;
/*      */   }
/*      */ 
/*      */   public static int max(int[] array)
/*      */   {
/*  931 */     if (array == null)
/*  932 */       throw new IllegalArgumentException("The Array must not be null");
/*  933 */     if (array.length == 0) {
/*  934 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  938 */     int max = array[0];
/*  939 */     for (int j = 1; j < array.length; j++) {
/*  940 */       if (array[j] > max) {
/*  941 */         max = array[j];
/*      */       }
/*      */     }
/*      */ 
/*  945 */     return max;
/*      */   }
/*      */ 
/*      */   public static short max(short[] array)
/*      */   {
/*  958 */     if (array == null)
/*  959 */       throw new IllegalArgumentException("The Array must not be null");
/*  960 */     if (array.length == 0) {
/*  961 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  965 */     short max = array[0];
/*  966 */     for (int i = 1; i < array.length; i++) {
/*  967 */       if (array[i] > max) {
/*  968 */         max = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  972 */     return max;
/*      */   }
/*      */ 
/*      */   public static byte max(byte[] array)
/*      */   {
/*  985 */     if (array == null)
/*  986 */       throw new IllegalArgumentException("The Array must not be null");
/*  987 */     if (array.length == 0) {
/*  988 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/*  992 */     byte max = array[0];
/*  993 */     for (int i = 1; i < array.length; i++) {
/*  994 */       if (array[i] > max) {
/*  995 */         max = array[i];
/*      */       }
/*      */     }
/*      */ 
/*  999 */     return max;
/*      */   }
/*      */ 
/*      */   public static double max(double[] array)
/*      */   {
/* 1013 */     if (array == null)
/* 1014 */       throw new IllegalArgumentException("The Array must not be null");
/* 1015 */     if (array.length == 0) {
/* 1016 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/* 1020 */     double max = array[0];
/* 1021 */     for (int j = 1; j < array.length; j++) {
/* 1022 */       if (Double.isNaN(array[j])) {
/* 1023 */         return (0.0D / 0.0D);
/*      */       }
/* 1025 */       if (array[j] > max) {
/* 1026 */         max = array[j];
/*      */       }
/*      */     }
/*      */ 
/* 1030 */     return max;
/*      */   }
/*      */ 
/*      */   public static float max(float[] array)
/*      */   {
/* 1044 */     if (array == null)
/* 1045 */       throw new IllegalArgumentException("The Array must not be null");
/* 1046 */     if (array.length == 0) {
/* 1047 */       throw new IllegalArgumentException("Array cannot be empty.");
/*      */     }
/*      */ 
/* 1051 */     float max = array[0];
/* 1052 */     for (int j = 1; j < array.length; j++) {
/* 1053 */       if (Float.isNaN(array[j])) {
/* 1054 */         return (0.0F / 0.0F);
/*      */       }
/* 1056 */       if (array[j] > max) {
/* 1057 */         max = array[j];
/*      */       }
/*      */     }
/*      */ 
/* 1061 */     return max;
/*      */   }
/*      */ 
/*      */   public static long min(long a, long b, long c)
/*      */   {
/* 1075 */     if (b < a) {
/* 1076 */       a = b;
/*      */     }
/* 1078 */     if (c < a) {
/* 1079 */       a = c;
/*      */     }
/* 1081 */     return a;
/*      */   }
/*      */ 
/*      */   public static int min(int a, int b, int c)
/*      */   {
/* 1093 */     if (b < a) {
/* 1094 */       a = b;
/*      */     }
/* 1096 */     if (c < a) {
/* 1097 */       a = c;
/*      */     }
/* 1099 */     return a;
/*      */   }
/*      */ 
/*      */   public static short min(short a, short b, short c)
/*      */   {
/* 1111 */     if (b < a) {
/* 1112 */       a = b;
/*      */     }
/* 1114 */     if (c < a) {
/* 1115 */       a = c;
/*      */     }
/* 1117 */     return a;
/*      */   }
/*      */ 
/*      */   public static byte min(byte a, byte b, byte c)
/*      */   {
/* 1129 */     if (b < a) {
/* 1130 */       a = b;
/*      */     }
/* 1132 */     if (c < a) {
/* 1133 */       a = c;
/*      */     }
/* 1135 */     return a;
/*      */   }
/*      */ 
/*      */   public static double min(double a, double b, double c)
/*      */   {
/* 1151 */     return Math.min(Math.min(a, b), c);
/*      */   }
/*      */ 
/*      */   public static float min(float a, float b, float c)
/*      */   {
/* 1167 */     return Math.min(Math.min(a, b), c);
/*      */   }
/*      */ 
/*      */   public static long max(long a, long b, long c)
/*      */   {
/* 1181 */     if (b > a) {
/* 1182 */       a = b;
/*      */     }
/* 1184 */     if (c > a) {
/* 1185 */       a = c;
/*      */     }
/* 1187 */     return a;
/*      */   }
/*      */ 
/*      */   public static int max(int a, int b, int c)
/*      */   {
/* 1199 */     if (b > a) {
/* 1200 */       a = b;
/*      */     }
/* 1202 */     if (c > a) {
/* 1203 */       a = c;
/*      */     }
/* 1205 */     return a;
/*      */   }
/*      */ 
/*      */   public static short max(short a, short b, short c)
/*      */   {
/* 1217 */     if (b > a) {
/* 1218 */       a = b;
/*      */     }
/* 1220 */     if (c > a) {
/* 1221 */       a = c;
/*      */     }
/* 1223 */     return a;
/*      */   }
/*      */ 
/*      */   public static byte max(byte a, byte b, byte c)
/*      */   {
/* 1235 */     if (b > a) {
/* 1236 */       a = b;
/*      */     }
/* 1238 */     if (c > a) {
/* 1239 */       a = c;
/*      */     }
/* 1241 */     return a;
/*      */   }
/*      */ 
/*      */   public static double max(double a, double b, double c)
/*      */   {
/* 1257 */     return Math.max(Math.max(a, b), c);
/*      */   }
/*      */ 
/*      */   public static float max(float a, float b, float c)
/*      */   {
/* 1273 */     return Math.max(Math.max(a, b), c);
/*      */   }
/*      */ 
/*      */   public static boolean isDigits(String str)
/*      */   {
/* 1288 */     if (StringUtils.isEmpty(str)) {
/* 1289 */       return false;
/*      */     }
/* 1291 */     for (int i = 0; i < str.length(); i++) {
/* 1292 */       if (!Character.isDigit(str.charAt(i))) {
/* 1293 */         return false;
/*      */       }
/*      */     }
/* 1296 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean isNumber(String str)
/*      */   {
/* 1313 */     if (StringUtils.isEmpty(str)) {
/* 1314 */       return false;
/*      */     }
/* 1316 */     char[] chars = str.toCharArray();
/* 1317 */     int sz = chars.length;
/* 1318 */     boolean hasExp = false;
/* 1319 */     boolean hasDecPoint = false;
/* 1320 */     boolean allowSigns = false;
/* 1321 */     boolean foundDigit = false;
/*      */ 
/* 1323 */     int start = chars[0] == '-' ? 1 : 0;
/* 1324 */     if ((sz > start + 1) && (chars[start] == '0') && (chars[(start + 1)] == 'x')) {
/* 1325 */       int i = start + 2;
/* 1326 */       if (i == sz) {
/* 1327 */         return false;
/*      */       }
/*      */ 
/* 1330 */       for (; i < chars.length; i++) {
/* 1331 */         if (((chars[i] < '0') || (chars[i] > '9')) && ((chars[i] < 'a') || (chars[i] > 'f')) && ((chars[i] < 'A') || (chars[i] > 'F')))
/*      */         {
/* 1334 */           return false;
/*      */         }
/*      */       }
/* 1337 */       return true;
/*      */     }
/* 1339 */     sz--;
/*      */ 
/* 1341 */     int i = start;
/*      */ 
/* 1344 */     while ((i < sz) || ((i < sz + 1) && (allowSigns) && (!foundDigit))) {
/* 1345 */       if ((chars[i] >= '0') && (chars[i] <= '9')) {
/* 1346 */         foundDigit = true;
/* 1347 */         allowSigns = false;
/*      */       }
/* 1349 */       else if (chars[i] == '.') {
/* 1350 */         if ((hasDecPoint) || (hasExp))
/*      */         {
/* 1352 */           return false;
/*      */         }
/* 1354 */         hasDecPoint = true;
/* 1355 */       } else if ((chars[i] == 'e') || (chars[i] == 'E'))
/*      */       {
/* 1357 */         if (hasExp)
/*      */         {
/* 1359 */           return false;
/*      */         }
/* 1361 */         if (!foundDigit) {
/* 1362 */           return false;
/*      */         }
/* 1364 */         hasExp = true;
/* 1365 */         allowSigns = true;
/* 1366 */       } else if ((chars[i] == '+') || (chars[i] == '-')) {
/* 1367 */         if (!allowSigns) {
/* 1368 */           return false;
/*      */         }
/* 1370 */         allowSigns = false;
/* 1371 */         foundDigit = false;
/*      */       } else {
/* 1373 */         return false;
/*      */       }
/* 1375 */       i++;
/*      */     }
/* 1377 */     if (i < chars.length) {
/* 1378 */       if ((chars[i] >= '0') && (chars[i] <= '9'))
/*      */       {
/* 1380 */         return true;
/*      */       }
/* 1382 */       if ((chars[i] == 'e') || (chars[i] == 'E'))
/*      */       {
/* 1384 */         return false;
/*      */       }
/* 1386 */       if (chars[i] == '.') {
/* 1387 */         if ((hasDecPoint) || (hasExp))
/*      */         {
/* 1389 */           return false;
/*      */         }
/*      */ 
/* 1392 */         return foundDigit;
/*      */       }
/* 1394 */       if ((!allowSigns) && ((chars[i] == 'd') || (chars[i] == 'D') || (chars[i] == 'f') || (chars[i] == 'F')))
/*      */       {
/* 1399 */         return foundDigit;
/*      */       }
/* 1401 */       if ((chars[i] == 'l') || (chars[i] == 'L'))
/*      */       {
/* 1404 */         return (foundDigit) && (!hasExp) && (!hasDecPoint);
/*      */       }
/*      */ 
/* 1407 */       return false;
/*      */     }
/*      */ 
/* 1411 */     return (!allowSigns) && (foundDigit);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.math.NumberUtils
 * JD-Core Version:    0.6.2
 */