/*    1:     */package org.apache.commons.lang3;
/*    2:     */
/*    3:     */import java.util.Collection;
/*    4:     */import java.util.Iterator;
/*    5:     */import java.util.Map;
/*    6:     */import java.util.regex.Pattern;
/*    7:     */
/*   82:     */public class Validate
/*   83:     */{
/*   84:     */  private static final String DEFAULT_EXCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified exclusive range of %s to %s";
/*   85:     */  private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified inclusive range of %s to %s";
/*   86:     */  private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";
/*   87:     */  private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";
/*   88:     */  private static final String DEFAULT_IS_TRUE_EX_MESSAGE = "The validated expression is false";
/*   89:     */  private static final String DEFAULT_NO_NULL_ELEMENTS_ARRAY_EX_MESSAGE = "The validated array contains null element at index: %d";
/*   90:     */  private static final String DEFAULT_NO_NULL_ELEMENTS_COLLECTION_EX_MESSAGE = "The validated collection contains null element at index: %d";
/*   91:     */  private static final String DEFAULT_NOT_BLANK_EX_MESSAGE = "The validated character sequence is blank";
/*   92:     */  private static final String DEFAULT_NOT_EMPTY_ARRAY_EX_MESSAGE = "The validated array is empty";
/*   93:     */  private static final String DEFAULT_NOT_EMPTY_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence is empty";
/*   94:     */  private static final String DEFAULT_NOT_EMPTY_COLLECTION_EX_MESSAGE = "The validated collection is empty";
/*   95:     */  private static final String DEFAULT_NOT_EMPTY_MAP_EX_MESSAGE = "The validated map is empty";
/*   96:     */  private static final String DEFAULT_VALID_INDEX_ARRAY_EX_MESSAGE = "The validated array index is invalid: %d";
/*   97:     */  private static final String DEFAULT_VALID_INDEX_CHAR_SEQUENCE_EX_MESSAGE = "The validated character sequence index is invalid: %d";
/*   98:     */  private static final String DEFAULT_VALID_INDEX_COLLECTION_EX_MESSAGE = "The validated collection index is invalid: %d";
/*   99:     */  private static final String DEFAULT_VALID_STATE_EX_MESSAGE = "The validated state is false";
/*  100:     */  private static final String DEFAULT_IS_ASSIGNABLE_EX_MESSAGE = "Cannot assign a %s to a %s";
/*  101:     */  private static final String DEFAULT_IS_INSTANCE_OF_EX_MESSAGE = "Expected type: %s, actual: %s";
/*  102:     */  
/*  103:     */  public static void isTrue(boolean expression, String message, long value)
/*  104:     */  {
/*  105: 105 */    if (!expression) {
/*  106: 106 */      throw new IllegalArgumentException(String.format(message, new Object[] { Long.valueOf(value) }));
/*  107:     */    }
/*  108:     */  }
/*  109:     */  
/*  128:     */  public static void isTrue(boolean expression, String message, double value)
/*  129:     */  {
/*  130: 130 */    if (!expression) {
/*  131: 131 */      throw new IllegalArgumentException(String.format(message, new Object[] { Double.valueOf(value) }));
/*  132:     */    }
/*  133:     */  }
/*  134:     */  
/*  152:     */  public static void isTrue(boolean expression, String message, Object... values)
/*  153:     */  {
/*  154: 154 */    if (!expression) {
/*  155: 155 */      throw new IllegalArgumentException(String.format(message, values));
/*  156:     */    }
/*  157:     */  }
/*  158:     */  
/*  177:     */  public static void isTrue(boolean expression)
/*  178:     */  {
/*  179: 179 */    if (!expression) {
/*  180: 180 */      throw new IllegalArgumentException("The validated expression is false");
/*  181:     */    }
/*  182:     */  }
/*  183:     */  
/*  201:     */  public static <T> T notNull(T object)
/*  202:     */  {
/*  203: 203 */    return notNull(object, "The validated object is null", new Object[0]);
/*  204:     */  }
/*  205:     */  
/*  219:     */  public static <T> T notNull(T object, String message, Object... values)
/*  220:     */  {
/*  221: 221 */    if (object == null) {
/*  222: 222 */      throw new NullPointerException(String.format(message, values));
/*  223:     */    }
/*  224: 224 */    return object;
/*  225:     */  }
/*  226:     */  
/*  245:     */  public static <T> T[] notEmpty(T[] array, String message, Object... values)
/*  246:     */  {
/*  247: 247 */    if (array == null) {
/*  248: 248 */      throw new NullPointerException(String.format(message, values));
/*  249:     */    }
/*  250: 250 */    if (array.length == 0) {
/*  251: 251 */      throw new IllegalArgumentException(String.format(message, values));
/*  252:     */    }
/*  253: 253 */    return array;
/*  254:     */  }
/*  255:     */  
/*  271:     */  public static <T> T[] notEmpty(T[] array)
/*  272:     */  {
/*  273: 273 */    return notEmpty(array, "The validated array is empty", new Object[0]);
/*  274:     */  }
/*  275:     */  
/*  294:     */  public static <T extends Collection<?>> T notEmpty(T collection, String message, Object... values)
/*  295:     */  {
/*  296: 296 */    if (collection == null) {
/*  297: 297 */      throw new NullPointerException(String.format(message, values));
/*  298:     */    }
/*  299: 299 */    if (collection.isEmpty()) {
/*  300: 300 */      throw new IllegalArgumentException(String.format(message, values));
/*  301:     */    }
/*  302: 302 */    return collection;
/*  303:     */  }
/*  304:     */  
/*  320:     */  public static <T extends Collection<?>> T notEmpty(T collection)
/*  321:     */  {
/*  322: 322 */    return notEmpty(collection, "The validated collection is empty", new Object[0]);
/*  323:     */  }
/*  324:     */  
/*  343:     */  public static <T extends Map<?, ?>> T notEmpty(T map, String message, Object... values)
/*  344:     */  {
/*  345: 345 */    if (map == null) {
/*  346: 346 */      throw new NullPointerException(String.format(message, values));
/*  347:     */    }
/*  348: 348 */    if (map.isEmpty()) {
/*  349: 349 */      throw new IllegalArgumentException(String.format(message, values));
/*  350:     */    }
/*  351: 351 */    return map;
/*  352:     */  }
/*  353:     */  
/*  369:     */  public static <T extends Map<?, ?>> T notEmpty(T map)
/*  370:     */  {
/*  371: 371 */    return notEmpty(map, "The validated map is empty", new Object[0]);
/*  372:     */  }
/*  373:     */  
/*  392:     */  public static <T extends CharSequence> T notEmpty(T chars, String message, Object... values)
/*  393:     */  {
/*  394: 394 */    if (chars == null) {
/*  395: 395 */      throw new NullPointerException(String.format(message, values));
/*  396:     */    }
/*  397: 397 */    if (chars.length() == 0) {
/*  398: 398 */      throw new IllegalArgumentException(String.format(message, values));
/*  399:     */    }
/*  400: 400 */    return chars;
/*  401:     */  }
/*  402:     */  
/*  419:     */  public static <T extends CharSequence> T notEmpty(T chars)
/*  420:     */  {
/*  421: 421 */    return notEmpty(chars, "The validated character sequence is empty", new Object[0]);
/*  422:     */  }
/*  423:     */  
/*  445:     */  public static <T extends CharSequence> T notBlank(T chars, String message, Object... values)
/*  446:     */  {
/*  447: 447 */    if (chars == null) {
/*  448: 448 */      throw new NullPointerException(String.format(message, values));
/*  449:     */    }
/*  450: 450 */    if (StringUtils.isBlank(chars)) {
/*  451: 451 */      throw new IllegalArgumentException(String.format(message, values));
/*  452:     */    }
/*  453: 453 */    return chars;
/*  454:     */  }
/*  455:     */  
/*  474:     */  public static <T extends CharSequence> T notBlank(T chars)
/*  475:     */  {
/*  476: 476 */    return notBlank(chars, "The validated character sequence is blank", new Object[0]);
/*  477:     */  }
/*  478:     */  
/*  504:     */  public static <T> T[] noNullElements(T[] array, String message, Object... values)
/*  505:     */  {
/*  506: 506 */    notNull(array);
/*  507: 507 */    for (int i = 0; i < array.length; i++) {
/*  508: 508 */      if (array[i] == null) {
/*  509: 509 */        Object[] values2 = ArrayUtils.add(values, Integer.valueOf(i));
/*  510: 510 */        throw new IllegalArgumentException(String.format(message, values2));
/*  511:     */      }
/*  512:     */    }
/*  513: 513 */    return array;
/*  514:     */  }
/*  515:     */  
/*  536:     */  public static <T> T[] noNullElements(T[] array)
/*  537:     */  {
/*  538: 538 */    return noNullElements(array, "The validated array contains null element at index: %d", new Object[0]);
/*  539:     */  }
/*  540:     */  
/*  566:     */  public static <T extends Iterable<?>> T noNullElements(T iterable, String message, Object... values)
/*  567:     */  {
/*  568: 568 */    notNull(iterable);
/*  569: 569 */    int i = 0;
/*  570: 570 */    for (Iterator<?> it = iterable.iterator(); it.hasNext(); i++) {
/*  571: 571 */      if (it.next() == null) {
/*  572: 572 */        Object[] values2 = ArrayUtils.addAll(values, new Object[] { Integer.valueOf(i) });
/*  573: 573 */        throw new IllegalArgumentException(String.format(message, values2));
/*  574:     */      }
/*  575:     */    }
/*  576: 576 */    return iterable;
/*  577:     */  }
/*  578:     */  
/*  599:     */  public static <T extends Iterable<?>> T noNullElements(T iterable)
/*  600:     */  {
/*  601: 601 */    return noNullElements(iterable, "The validated collection contains null element at index: %d", new Object[0]);
/*  602:     */  }
/*  603:     */  
/*  627:     */  public static <T> T[] validIndex(T[] array, int index, String message, Object... values)
/*  628:     */  {
/*  629: 629 */    notNull(array);
/*  630: 630 */    if ((index < 0) || (index >= array.length)) {
/*  631: 631 */      throw new IndexOutOfBoundsException(String.format(message, values));
/*  632:     */    }
/*  633: 633 */    return array;
/*  634:     */  }
/*  635:     */  
/*  658:     */  public static <T> T[] validIndex(T[] array, int index)
/*  659:     */  {
/*  660: 660 */    return validIndex(array, index, "The validated array index is invalid: %d", new Object[] { Integer.valueOf(index) });
/*  661:     */  }
/*  662:     */  
/*  686:     */  public static <T extends Collection<?>> T validIndex(T collection, int index, String message, Object... values)
/*  687:     */  {
/*  688: 688 */    notNull(collection);
/*  689: 689 */    if ((index < 0) || (index >= collection.size())) {
/*  690: 690 */      throw new IndexOutOfBoundsException(String.format(message, values));
/*  691:     */    }
/*  692: 692 */    return collection;
/*  693:     */  }
/*  694:     */  
/*  714:     */  public static <T extends Collection<?>> T validIndex(T collection, int index)
/*  715:     */  {
/*  716: 716 */    return validIndex(collection, index, "The validated collection index is invalid: %d", new Object[] { Integer.valueOf(index) });
/*  717:     */  }
/*  718:     */  
/*  743:     */  public static <T extends CharSequence> T validIndex(T chars, int index, String message, Object... values)
/*  744:     */  {
/*  745: 745 */    notNull(chars);
/*  746: 746 */    if ((index < 0) || (index >= chars.length())) {
/*  747: 747 */      throw new IndexOutOfBoundsException(String.format(message, values));
/*  748:     */    }
/*  749: 749 */    return chars;
/*  750:     */  }
/*  751:     */  
/*  775:     */  public static <T extends CharSequence> T validIndex(T chars, int index)
/*  776:     */  {
/*  777: 777 */    return validIndex(chars, index, "The validated character sequence index is invalid: %d", new Object[] { Integer.valueOf(index) });
/*  778:     */  }
/*  779:     */  
/*  801:     */  public static void validState(boolean expression)
/*  802:     */  {
/*  803: 803 */    if (!expression) {
/*  804: 804 */      throw new IllegalStateException("The validated state is false");
/*  805:     */    }
/*  806:     */  }
/*  807:     */  
/*  823:     */  public static void validState(boolean expression, String message, Object... values)
/*  824:     */  {
/*  825: 825 */    if (!expression) {
/*  826: 826 */      throw new IllegalStateException(String.format(message, values));
/*  827:     */    }
/*  828:     */  }
/*  829:     */  
/*  847:     */  public static void matchesPattern(CharSequence input, String pattern)
/*  848:     */  {
/*  849: 849 */    if (!Pattern.matches(pattern, input)) {
/*  850: 850 */      throw new IllegalArgumentException(String.format("The string %s does not match the pattern %s", new Object[] { input, pattern }));
/*  851:     */    }
/*  852:     */  }
/*  853:     */  
/*  870:     */  public static void matchesPattern(CharSequence input, String pattern, String message, Object... values)
/*  871:     */  {
/*  872: 872 */    if (!Pattern.matches(pattern, input)) {
/*  873: 873 */      throw new IllegalArgumentException(String.format(message, values));
/*  874:     */    }
/*  875:     */  }
/*  876:     */  
/*  894:     */  public static <T> void inclusiveBetween(T start, T end, Comparable<T> value)
/*  895:     */  {
/*  896: 896 */    if ((value.compareTo(start) < 0) || (value.compareTo(end) > 0)) {
/*  897: 897 */      throw new IllegalArgumentException(String.format("The value %s is not in the specified inclusive range of %s to %s", new Object[] { value, start, end }));
/*  898:     */    }
/*  899:     */  }
/*  900:     */  
/*  918:     */  public static <T> void inclusiveBetween(T start, T end, Comparable<T> value, String message, Object... values)
/*  919:     */  {
/*  920: 920 */    if ((value.compareTo(start) < 0) || (value.compareTo(end) > 0)) {
/*  921: 921 */      throw new IllegalArgumentException(String.format(message, values));
/*  922:     */    }
/*  923:     */  }
/*  924:     */  
/*  942:     */  public static <T> void exclusiveBetween(T start, T end, Comparable<T> value)
/*  943:     */  {
/*  944: 944 */    if ((value.compareTo(start) <= 0) || (value.compareTo(end) >= 0)) {
/*  945: 945 */      throw new IllegalArgumentException(String.format("The value %s is not in the specified exclusive range of %s to %s", new Object[] { value, start, end }));
/*  946:     */    }
/*  947:     */  }
/*  948:     */  
/*  966:     */  public static <T> void exclusiveBetween(T start, T end, Comparable<T> value, String message, Object... values)
/*  967:     */  {
/*  968: 968 */    if ((value.compareTo(start) <= 0) || (value.compareTo(end) >= 0)) {
/*  969: 969 */      throw new IllegalArgumentException(String.format(message, values));
/*  970:     */    }
/*  971:     */  }
/*  972:     */  
/*  991:     */  public static void isInstanceOf(Class<?> type, Object obj)
/*  992:     */  {
/*  993: 993 */    if (!type.isInstance(obj)) {
/*  994: 994 */      throw new IllegalArgumentException(String.format("Expected type: %s, actual: %s", new Object[] { type.getName(), obj == null ? "null" : obj.getClass().getName() }));
/*  995:     */    }
/*  996:     */  }
/*  997:     */  
/* 1015:     */  public static void isInstanceOf(Class<?> type, Object obj, String message, Object... values)
/* 1016:     */  {
/* 1017:1017 */    if (!type.isInstance(obj)) {
/* 1018:1018 */      throw new IllegalArgumentException(String.format(message, values));
/* 1019:     */    }
/* 1020:     */  }
/* 1021:     */  
/* 1040:     */  public static void isAssignableFrom(Class<?> superType, Class<?> type)
/* 1041:     */  {
/* 1042:1042 */    if (!superType.isAssignableFrom(type)) {
/* 1043:1043 */      throw new IllegalArgumentException(String.format("Cannot assign a %s to a %s", new Object[] { type == null ? "null" : type.getName(), superType.getName() }));
/* 1044:     */    }
/* 1045:     */  }
/* 1046:     */  
/* 1064:     */  public static void isAssignableFrom(Class<?> superType, Class<?> type, String message, Object... values)
/* 1065:     */  {
/* 1066:1066 */    if (!superType.isAssignableFrom(type)) {
/* 1067:1067 */      throw new IllegalArgumentException(String.format(message, values));
/* 1068:     */    }
/* 1069:     */  }
/* 1070:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.Validate
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */