/*      */ package org.apache.commons.lang3;
/*      */ 
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.regex.Pattern;
/*      */ 
/*      */ public class Validate
/*      */ {
/*      */   private static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified exclusive range of %s to %s";
/*      */   private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified inclusive range of %s to %s";
/*      */   private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
/*      */   private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
/*      */   private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
/*      */   private static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE = "The validated array contains null element at index: %d";
/*      */   private static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE = "The validated collection contains null element at index: %d";
/*      */   private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
/*      */   private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
/*      */   private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence is empty";
/*      */   private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
/*      */   private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
/*      */   private static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
/*      */   private static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence index is invalid: %d";
/*      */   private static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE = "The validated collection index is invalid: %d";
/*      */   private static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
/*      */   private static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
/*      */   private static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";
/*      */ 
/*      */   public static void isTrue(boolean expression, String message, long value)
/*      */   {
/*  105 */     if (!expression)
/*  106 */       throw new IllegalArgumentException(String.format(message, new Object[] { Long.valueOf(value) }));
/*      */   }
/*      */ 
/*      */   public static void isTrue(boolean expression, String message, double value)
/*      */   {
/*  130 */     if (!expression)
/*  131 */       throw new IllegalArgumentException(String.format(message, new Object[] { Double.valueOf(value) }));
/*      */   }
/*      */ 
/*      */   public static void isTrue(boolean expression, String message, Object[] values)
/*      */   {
/*  154 */     if (!expression)
/*  155 */       throw new IllegalArgumentException(String.format(message, values));
/*      */   }
/*      */ 
/*      */   public static void isTrue(boolean expression)
/*      */   {
/*  179 */     if (!expression)
/*  180 */       throw new IllegalArgumentException("The validated expression is false");
/*      */   }
/*      */ 
/*      */   public static <T> T notNull(T object)
/*      */   {
/*  203 */     return notNull(object, "The validated object is null", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T> T notNull(T object, String message, Object[] values)
/*      */   {
/*  221 */     if (object == null) {
/*  222 */       throw new NullPointerException(String.format(message, values));
/*      */     }
/*  224 */     return object;
/*      */   }
/*      */ 
/*      */   public static <T> T[] notEmpty(T[] array, String message, Object[] values)
/*      */   {
/*  247 */     if (array == null) {
/*  248 */       throw new NullPointerException(String.format(message, values));
/*      */     }
/*  250 */     if (array.length == 0) {
/*  251 */       throw new IllegalArgumentException(String.format(message, values));
/*      */     }
/*  253 */     return array;
/*      */   }
/*      */ 
/*      */   public static <T> T[] notEmpty(T[] array)
/*      */   {
/*  273 */     return notEmpty(array, "The validated array is empty", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T extends Collection<?>> T notEmpty(T collection, String message, Object[] values)
/*      */   {
/*  296 */     if (collection == null) {
/*  297 */       throw new NullPointerException(String.format(message, values));
/*      */     }
/*  299 */     if (collection.isEmpty()) {
/*  300 */       throw new IllegalArgumentException(String.format(message, values));
/*      */     }
/*  302 */     return collection;
/*      */   }
/*      */ 
/*      */   public static <T extends Collection<?>> T notEmpty(T collection)
/*      */   {
/*  322 */     return notEmpty(collection, "The validated collection is empty", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T extends Map<?, ?>> T notEmpty(T map, String message, Object[] values)
/*      */   {
/*  345 */     if (map == null) {
/*  346 */       throw new NullPointerException(String.format(message, values));
/*      */     }
/*  348 */     if (map.isEmpty()) {
/*  349 */       throw new IllegalArgumentException(String.format(message, values));
/*      */     }
/*  351 */     return map;
/*      */   }
/*      */ 
/*      */   public static <T extends Map<?, ?>> T notEmpty(T map)
/*      */   {
/*  371 */     return notEmpty(map, "The validated map is empty", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T extends CharSequence> T notEmpty(T chars, String message, Object[] values)
/*      */   {
/*  394 */     if (chars == null) {
/*  395 */       throw new NullPointerException(String.format(message, values));
/*      */     }
/*  397 */     if (chars.length() == 0) {
/*  398 */       throw new IllegalArgumentException(String.format(message, values));
/*      */     }
/*  400 */     return chars;
/*      */   }
/*      */ 
/*      */   public static <T extends CharSequence> T notEmpty(T chars)
/*      */   {
/*  421 */     return notEmpty(chars, "The validated character sequence is empty", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T extends CharSequence> T notBlank(T chars, String message, Object[] values)
/*      */   {
/*  447 */     if (chars == null) {
/*  448 */       throw new NullPointerException(String.format(message, values));
/*      */     }
/*  450 */     if (StringUtils.isBlank(chars)) {
/*  451 */       throw new IllegalArgumentException(String.format(message, values));
/*      */     }
/*  453 */     return chars;
/*      */   }
/*      */ 
/*      */   public static <T extends CharSequence> T notBlank(T chars)
/*      */   {
/*  476 */     return notBlank(chars, "The validated character sequence is blank", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T> T[] noNullElements(T[] array, String message, Object[] values)
/*      */   {
/*  506 */     notNull(array);
/*  507 */     for (int i = 0; i < array.length; i++) {
/*  508 */       if (array[i] == null) {
/*  509 */         Object[] values2 = ArrayUtils.add(values, Integer.valueOf(i));
/*  510 */         throw new IllegalArgumentException(String.format(message, values2));
/*      */       }
/*      */     }
/*  513 */     return array;
/*      */   }
/*      */ 
/*      */   public static <T> T[] noNullElements(T[] array)
/*      */   {
/*  538 */     return noNullElements(array, "The validated array contains null element at index: %d", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T extends Iterable<?>> T noNullElements(T iterable, String message, Object[] values)
/*      */   {
/*  568 */     notNull(iterable);
/*  569 */     int i = 0;
/*  570 */     for (Iterator it = iterable.iterator(); it.hasNext(); i++) {
/*  571 */       if (it.next() == null) {
/*  572 */         Object[] values2 = ArrayUtils.addAll(values, new Object[] { Integer.valueOf(i) });
/*  573 */         throw new IllegalArgumentException(String.format(message, values2));
/*      */       }
/*      */     }
/*  576 */     return iterable;
/*      */   }
/*      */ 
/*      */   public static <T extends Iterable<?>> T noNullElements(T iterable)
/*      */   {
/*  601 */     return noNullElements(iterable, "The validated collection contains null element at index: %d", new Object[0]);
/*      */   }
/*      */ 
/*      */   public static <T> T[] validIndex(T[] array, int index, String message, Object[] values)
/*      */   {
/*  629 */     notNull(array);
/*  630 */     if ((index < 0) || (index >= array.length)) {
/*  631 */       throw new IndexOutOfBoundsException(String.format(message, values));
/*      */     }
/*  633 */     return array;
/*      */   }
/*      */ 
/*      */   public static <T> T[] validIndex(T[] array, int index)
/*      */   {
/*  660 */     return validIndex(array, index, "The validated array index is invalid: %d", new Object[] { Integer.valueOf(index) });
/*      */   }
/*      */ 
/*      */   public static <T extends Collection<?>> T validIndex(T collection, int index, String message, Object[] values)
/*      */   {
/*  688 */     notNull(collection);
/*  689 */     if ((index < 0) || (index >= collection.size())) {
/*  690 */       throw new IndexOutOfBoundsException(String.format(message, values));
/*      */     }
/*  692 */     return collection;
/*      */   }
/*      */ 
/*      */   public static <T extends Collection<?>> T validIndex(T collection, int index)
/*      */   {
/*  716 */     return validIndex(collection, index, "The validated collection index is invalid: %d", new Object[] { Integer.valueOf(index) });
/*      */   }
/*      */ 
/*      */   public static <T extends CharSequence> T validIndex(T chars, int index, String message, Object[] values)
/*      */   {
/*  745 */     notNull(chars);
/*  746 */     if ((index < 0) || (index >= chars.length())) {
/*  747 */       throw new IndexOutOfBoundsException(String.format(message, values));
/*      */     }
/*  749 */     return chars;
/*      */   }
/*      */ 
/*      */   public static <T extends CharSequence> T validIndex(T chars, int index)
/*      */   {
/*  777 */     return validIndex(chars, index, "The validated character sequence index is invalid: %d", new Object[] { Integer.valueOf(index) });
/*      */   }
/*      */ 
/*      */   public static void validState(boolean expression)
/*      */   {
/*  803 */     if (!expression)
/*  804 */       throw new IllegalStateException("The validated state is false");
/*      */   }
/*      */ 
/*      */   public static void validState(boolean expression, String message, Object[] values)
/*      */   {
/*  825 */     if (!expression)
/*  826 */       throw new IllegalStateException(String.format(message, values));
/*      */   }
/*      */ 
/*      */   public static void matchesPattern(CharSequence input, String pattern)
/*      */   {
/*  849 */     if (!Pattern.matches(pattern, input))
/*  850 */       throw new IllegalArgumentException(String.format("The string %s does not match the pattern %s", new Object[] { input, pattern }));
/*      */   }
/*      */ 
/*      */   public static void matchesPattern(CharSequence input, String pattern, String message, Object[] values)
/*      */   {
/*  872 */     if (!Pattern.matches(pattern, input))
/*  873 */       throw new IllegalArgumentException(String.format(message, values));
/*      */   }
/*      */ 
/*      */   public static <T> void inclusiveBetween(T start, T end, Comparable<T> value)
/*      */   {
/*  896 */     if ((value.compareTo(start) < 0) || (value.compareTo(end) > 0))
/*  897 */       throw new IllegalArgumentException(String.format("The value %s is not in the specified inclusive range of %s to %s", new Object[] { value, start, end }));
/*      */   }
/*      */ 
/*      */   public static <T> void inclusiveBetween(T start, T end, Comparable<T> value, String message, Object[] values)
/*      */   {
/*  920 */     if ((value.compareTo(start) < 0) || (value.compareTo(end) > 0))
/*  921 */       throw new IllegalArgumentException(String.format(message, values));
/*      */   }
/*      */ 
/*      */   public static <T> void exclusiveBetween(T start, T end, Comparable<T> value)
/*      */   {
/*  944 */     if ((value.compareTo(start) <= 0) || (value.compareTo(end) >= 0))
/*  945 */       throw new IllegalArgumentException(String.format("The value %s is not in the specified exclusive range of %s to %s", new Object[] { value, start, end }));
/*      */   }
/*      */ 
/*      */   public static <T> void exclusiveBetween(T start, T end, Comparable<T> value, String message, Object[] values)
/*      */   {
/*  968 */     if ((value.compareTo(start) <= 0) || (value.compareTo(end) >= 0))
/*  969 */       throw new IllegalArgumentException(String.format(message, values));
/*      */   }
/*      */ 
/*      */   public static void isInstanceOf(Class<?> type, Object obj)
/*      */   {
/*  993 */     if (!type.isInstance(obj))
/*  994 */       throw new IllegalArgumentException(String.format("Expected type: %s, actual: %s", new Object[] { type.getName(), obj == null ? "null" : obj.getClass().getName() }));
/*      */   }
/*      */ 
/*      */   public static void isInstanceOf(Class<?> type, Object obj, String message, Object[] values)
/*      */   {
/* 1017 */     if (!type.isInstance(obj))
/* 1018 */       throw new IllegalArgumentException(String.format(message, values));
/*      */   }
/*      */ 
/*      */   public static void isAssignableFrom(Class<?> superType, Class<?> type)
/*      */   {
/* 1042 */     if (!superType.isAssignableFrom(type))
/* 1043 */       throw new IllegalArgumentException(String.format("Cannot assign a %s to a %s", new Object[] { type == null ? "null" : type.getName(), superType.getName() }));
/*      */   }
/*      */ 
/*      */   public static void isAssignableFrom(Class<?> superType, Class<?> type, String message, Object[] values)
/*      */   {
/* 1066 */     if (!superType.isAssignableFrom(type))
/* 1067 */       throw new IllegalArgumentException(String.format(message, values));
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.Validate
 * JD-Core Version:    0.6.2
 */