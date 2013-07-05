/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import org.apache.commons.lang3.math.NumberUtils;
/*      */ 
/*      */ public class BooleanUtils
/*      */ {
/*      */   public static Boolean negate(Boolean bool)
/*      */   {
/*   64 */     if (bool == null) {
/*   65 */       return null;
/*      */     }
/*   67 */     return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/*      */   }
/*      */ 
/*      */   public static boolean isTrue(Boolean bool)
/*      */   {
/*   87 */     return Boolean.TRUE.equals(bool);
/*      */   }
/*      */ 
/*      */   public static boolean isNotTrue(Boolean bool)
/*      */   {
/*  105 */     return !isTrue(bool);
/*      */   }
/*      */ 
/*      */   public static boolean isFalse(Boolean bool)
/*      */   {
/*  123 */     return Boolean.FALSE.equals(bool);
/*      */   }
/*      */ 
/*      */   public static boolean isNotFalse(Boolean bool)
/*      */   {
/*  141 */     return !isFalse(bool);
/*      */   }
/*      */ 
/*      */   public static boolean toBoolean(Boolean bool)
/*      */   {
/*  159 */     return (bool != null) && (bool.booleanValue());
/*      */   }
/*      */ 
/*      */   public static boolean toBooleanDefaultIfNull(Boolean bool, boolean valueIfNull)
/*      */   {
/*  176 */     if (bool == null) {
/*  177 */       return valueIfNull;
/*      */     }
/*  179 */     return bool.booleanValue();
/*      */   }
/*      */ 
/*      */   public static boolean toBoolean(int value)
/*      */   {
/*  199 */     return value != 0;
/*      */   }
/*      */ 
/*      */   public static Boolean toBooleanObject(int value)
/*      */   {
/*  217 */     return value == 0 ? Boolean.FALSE : Boolean.TRUE;
/*      */   }
/*      */ 
/*      */   public static Boolean toBooleanObject(Integer value)
/*      */   {
/*  239 */     if (value == null) {
/*  240 */       return null;
/*      */     }
/*  242 */     return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
/*      */   }
/*      */ 
/*      */   public static boolean toBoolean(int value, int trueValue, int falseValue)
/*      */   {
/*  262 */     if (value == trueValue) {
/*  263 */       return true;
/*      */     }
/*  265 */     if (value == falseValue) {
/*  266 */       return false;
/*      */     }
/*      */ 
/*  269 */     throw new IllegalArgumentException("The Integer did not match either specified value");
/*      */   }
/*      */ 
/*      */   public static boolean toBoolean(Integer value, Integer trueValue, Integer falseValue)
/*      */   {
/*  290 */     if (value == null) {
/*  291 */       if (trueValue == null) {
/*  292 */         return true;
/*      */       }
/*  294 */       if (falseValue == null)
/*  295 */         return false;
/*      */     } else {
/*  297 */       if (value.equals(trueValue))
/*  298 */         return true;
/*  299 */       if (value.equals(falseValue)) {
/*  300 */         return false;
/*      */       }
/*      */     }
/*  303 */     throw new IllegalArgumentException("The Integer did not match either specified value");
/*      */   }
/*      */ 
/*      */   public static Boolean toBooleanObject(int value, int trueValue, int falseValue, int nullValue)
/*      */   {
/*  325 */     if (value == trueValue) {
/*  326 */       return Boolean.TRUE;
/*      */     }
/*  328 */     if (value == falseValue) {
/*  329 */       return Boolean.FALSE;
/*      */     }
/*  331 */     if (value == nullValue) {
/*  332 */       return null;
/*      */     }
/*      */ 
/*  335 */     throw new IllegalArgumentException("The Integer did not match any specified value");
/*      */   }
/*      */ 
/*      */   public static Boolean toBooleanObject(Integer value, Integer trueValue, Integer falseValue, Integer nullValue)
/*      */   {
/*  357 */     if (value == null) {
/*  358 */       if (trueValue == null) {
/*  359 */         return Boolean.TRUE;
/*      */       }
/*  361 */       if (falseValue == null) {
/*  362 */         return Boolean.FALSE;
/*      */       }
/*  364 */       if (nullValue == null)
/*  365 */         return null;
/*      */     } else {
/*  367 */       if (value.equals(trueValue))
/*  368 */         return Boolean.TRUE;
/*  369 */       if (value.equals(falseValue))
/*  370 */         return Boolean.FALSE;
/*  371 */       if (value.equals(nullValue)) {
/*  372 */         return null;
/*      */       }
/*      */     }
/*  375 */     throw new IllegalArgumentException("The Integer did not match any specified value");
/*      */   }
/*      */ 
/*      */   public static int toInteger(boolean bool)
/*      */   {
/*  393 */     return bool ? 1 : 0;
/*      */   }
/*      */ 
/*      */   public static Integer toIntegerObject(boolean bool)
/*      */   {
/*  409 */     return bool ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*      */   }
/*      */ 
/*      */   public static Integer toIntegerObject(Boolean bool)
/*      */   {
/*  427 */     if (bool == null) {
/*  428 */       return null;
/*      */     }
/*  430 */     return bool.booleanValue() ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO;
/*      */   }
/*      */ 
/*      */   public static int toInteger(boolean bool, int trueValue, int falseValue)
/*      */   {
/*  447 */     return bool ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */   public static int toInteger(Boolean bool, int trueValue, int falseValue, int nullValue)
/*      */   {
/*  466 */     if (bool == null) {
/*  467 */       return nullValue;
/*      */     }
/*  469 */     return bool.booleanValue() ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */   public static Integer toIntegerObject(boolean bool, Integer trueValue, Integer falseValue)
/*      */   {
/*  486 */     return bool ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */   public static Integer toIntegerObject(Boolean bool, Integer trueValue, Integer falseValue, Integer nullValue)
/*      */   {
/*  505 */     if (bool == null) {
/*  506 */       return nullValue;
/*      */     }
/*  508 */     return bool.booleanValue() ? trueValue : falseValue;
/*      */   }
/*      */ 
/*      */   public static Boolean toBooleanObject(String str)
/*      */   {
/*  545 */     if (str == "true") {
/*  546 */       return Boolean.TRUE;
/*      */     }
/*  548 */     if (str == null) {
/*  549 */       return null;
/*      */     }
/*  551 */     switch (str.length()) {
/*      */     case 1:
/*  553 */       char ch0 = str.charAt(0);
/*  554 */       if ((ch0 == 'y') || (ch0 == 'Y') || (ch0 == 't') || (ch0 == 'T'))
/*      */       {
/*  556 */         return Boolean.TRUE;
/*      */       }
/*  558 */       if ((ch0 == 'n') || (ch0 == 'N') || (ch0 == 'f') || (ch0 == 'F'))
/*      */       {
/*  560 */         return Boolean.FALSE;
/*      */       }
/*      */ 
/*      */       break;
/*      */     case 2:
/*  565 */       char ch0 = str.charAt(0);
/*  566 */       char ch1 = str.charAt(1);
/*  567 */       if (((ch0 == 'o') || (ch0 == 'O')) && ((ch1 == 'n') || (ch1 == 'N')))
/*      */       {
/*  569 */         return Boolean.TRUE;
/*      */       }
/*  571 */       if (((ch0 == 'n') || (ch0 == 'N')) && ((ch1 == 'o') || (ch1 == 'O')))
/*      */       {
/*  573 */         return Boolean.FALSE;
/*      */       }
/*      */ 
/*      */       break;
/*      */     case 3:
/*  578 */       char ch0 = str.charAt(0);
/*  579 */       char ch1 = str.charAt(1);
/*  580 */       char ch2 = str.charAt(2);
/*  581 */       if (((ch0 == 'y') || (ch0 == 'Y')) && ((ch1 == 'e') || (ch1 == 'E')) && ((ch2 == 's') || (ch2 == 'S')))
/*      */       {
/*  584 */         return Boolean.TRUE;
/*      */       }
/*  586 */       if (((ch0 == 'o') || (ch0 == 'O')) && ((ch1 == 'f') || (ch1 == 'F')) && ((ch2 == 'f') || (ch2 == 'F')))
/*      */       {
/*  589 */         return Boolean.FALSE;
/*      */       }
/*      */ 
/*      */       break;
/*      */     case 4:
/*  594 */       char ch0 = str.charAt(0);
/*  595 */       char ch1 = str.charAt(1);
/*  596 */       char ch2 = str.charAt(2);
/*  597 */       char ch3 = str.charAt(3);
/*  598 */       if (((ch0 == 't') || (ch0 == 'T')) && ((ch1 == 'r') || (ch1 == 'R')) && ((ch2 == 'u') || (ch2 == 'U')) && ((ch3 == 'e') || (ch3 == 'E')))
/*      */       {
/*  602 */         return Boolean.TRUE;
/*      */       }
/*      */ 
/*      */       break;
/*      */     case 5:
/*  607 */       char ch0 = str.charAt(0);
/*  608 */       char ch1 = str.charAt(1);
/*  609 */       char ch2 = str.charAt(2);
/*  610 */       char ch3 = str.charAt(3);
/*  611 */       char ch4 = str.charAt(4);
/*  612 */       if (((ch0 == 'f') || (ch0 == 'F')) && ((ch1 == 'a') || (ch1 == 'A')) && ((ch2 == 'l') || (ch2 == 'L')) && ((ch3 == 's') || (ch3 == 'S')) && ((ch4 == 'e') || (ch4 == 'E')))
/*      */       {
/*  617 */         return Boolean.FALSE;
/*      */       }
/*      */ 
/*      */       break;
/*      */     }
/*      */ 
/*  623 */     return null;
/*      */   }
/*      */ 
/*      */   public static Boolean toBooleanObject(String str, String trueString, String falseString, String nullString)
/*      */   {
/*  646 */     if (str == null) {
/*  647 */       if (trueString == null) {
/*  648 */         return Boolean.TRUE;
/*      */       }
/*  650 */       if (falseString == null) {
/*  651 */         return Boolean.FALSE;
/*      */       }
/*  653 */       if (nullString == null)
/*  654 */         return null;
/*      */     } else {
/*  656 */       if (str.equals(trueString))
/*  657 */         return Boolean.TRUE;
/*  658 */       if (str.equals(falseString))
/*  659 */         return Boolean.FALSE;
/*  660 */       if (str.equals(nullString)) {
/*  661 */         return null;
/*      */       }
/*      */     }
/*  664 */     throw new IllegalArgumentException("The String did not match any specified value");
/*      */   }
/*      */ 
/*      */   public static boolean toBoolean(String str)
/*      */   {
/*  695 */     return toBooleanObject(str) == Boolean.TRUE;
/*      */   }
/*      */ 
/*      */   public static boolean toBoolean(String str, String trueString, String falseString)
/*      */   {
/*  713 */     if (str == trueString)
/*  714 */       return true;
/*  715 */     if (str == falseString)
/*  716 */       return false;
/*  717 */     if (str != null) {
/*  718 */       if (str.equals(trueString))
/*  719 */         return true;
/*  720 */       if (str.equals(falseString)) {
/*  721 */         return false;
/*      */       }
/*      */     }
/*      */ 
/*  725 */     throw new IllegalArgumentException("The String did not match either specified value");
/*      */   }
/*      */ 
/*      */   public static String toStringTrueFalse(Boolean bool)
/*      */   {
/*  744 */     return toString(bool, "true", "false", null);
/*      */   }
/*      */ 
/*      */   public static String toStringOnOff(Boolean bool)
/*      */   {
/*  761 */     return toString(bool, "on", "off", null);
/*      */   }
/*      */ 
/*      */   public static String toStringYesNo(Boolean bool)
/*      */   {
/*  778 */     return toString(bool, "yes", "no", null);
/*      */   }
/*      */ 
/*      */   public static String toString(Boolean bool, String trueString, String falseString, String nullString)
/*      */   {
/*  797 */     if (bool == null) {
/*  798 */       return nullString;
/*      */     }
/*  800 */     return bool.booleanValue() ? trueString : falseString;
/*      */   }
/*      */ 
/*      */   public static String toStringTrueFalse(boolean bool)
/*      */   {
/*  818 */     return toString(bool, "true", "false");
/*      */   }
/*      */ 
/*      */   public static String toStringOnOff(boolean bool)
/*      */   {
/*  834 */     return toString(bool, "on", "off");
/*      */   }
/*      */ 
/*      */   public static String toStringYesNo(boolean bool)
/*      */   {
/*  850 */     return toString(bool, "yes", "no");
/*      */   }
/*      */ 
/*      */   public static String toString(boolean bool, String trueString, String falseString)
/*      */   {
/*  867 */     return bool ? trueString : falseString;
/*      */   }
/*      */ 
/*      */   public static boolean and(boolean[] array)
/*      */   {
/*  891 */     if (array == null) {
/*  892 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  894 */     if (array.length == 0) {
/*  895 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*  897 */     for (boolean element : array) {
/*  898 */       if (!element) {
/*  899 */         return false;
/*      */       }
/*      */     }
/*  902 */     return true;
/*      */   }
/*      */ 
/*      */   public static Boolean and(Boolean[] array)
/*      */   {
/*  925 */     if (array == null) {
/*  926 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  928 */     if (array.length == 0)
/*  929 */       throw new IllegalArgumentException("Array is empty");
/*      */     try
/*      */     {
/*  932 */       boolean[] primitive = ArrayUtils.toPrimitive(array);
/*  933 */       return and(primitive) ? Boolean.TRUE : Boolean.FALSE; } catch (NullPointerException ex) {
/*      */     }
/*  935 */     throw new IllegalArgumentException("The array must not contain any null elements");
/*      */   }
/*      */ 
/*      */   public static boolean or(boolean[] array)
/*      */   {
/*  959 */     if (array == null) {
/*  960 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  962 */     if (array.length == 0) {
/*  963 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*  965 */     for (boolean element : array) {
/*  966 */       if (element) {
/*  967 */         return true;
/*      */       }
/*      */     }
/*  970 */     return false;
/*      */   }
/*      */ 
/*      */   public static Boolean or(Boolean[] array)
/*      */   {
/*  994 */     if (array == null) {
/*  995 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/*  997 */     if (array.length == 0)
/*  998 */       throw new IllegalArgumentException("Array is empty");
/*      */     try
/*      */     {
/* 1001 */       boolean[] primitive = ArrayUtils.toPrimitive(array);
/* 1002 */       return or(primitive) ? Boolean.TRUE : Boolean.FALSE; } catch (NullPointerException ex) {
/*      */     }
/* 1004 */     throw new IllegalArgumentException("The array must not contain any null elements");
/*      */   }
/*      */ 
/*      */   public static boolean xor(boolean[] array)
/*      */   {
/* 1027 */     if (array == null) {
/* 1028 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1030 */     if (array.length == 0) {
/* 1031 */       throw new IllegalArgumentException("Array is empty");
/*      */     }
/*      */ 
/* 1035 */     int trueCount = 0;
/* 1036 */     for (boolean element : array)
/*      */     {
/* 1039 */       if (element) {
/* 1040 */         if (trueCount < 1)
/* 1041 */           trueCount++;
/*      */         else {
/* 1043 */           return false;
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1049 */     return trueCount == 1;
/*      */   }
/*      */ 
/*      */   public static Boolean xor(Boolean[] array)
/*      */   {
/* 1068 */     if (array == null) {
/* 1069 */       throw new IllegalArgumentException("The Array must not be null");
/*      */     }
/* 1071 */     if (array.length == 0)
/* 1072 */       throw new IllegalArgumentException("Array is empty");
/*      */     try
/*      */     {
/* 1075 */       boolean[] primitive = ArrayUtils.toPrimitive(array);
/* 1076 */       return xor(primitive) ? Boolean.TRUE : Boolean.FALSE; } catch (NullPointerException ex) {
/*      */     }
/* 1078 */     throw new IllegalArgumentException("The array must not contain any null elements");
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.BooleanUtils
 * JD-Core Version:    0.6.2
 */