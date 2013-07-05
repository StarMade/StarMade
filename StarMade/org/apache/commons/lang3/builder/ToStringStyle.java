/*      */ package org.apache.commons.lang3.builder;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Array;
/*      */ import java.util.Collection;
/*      */ import java.util.Map;
/*      */ import java.util.WeakHashMap;
/*      */ import org.apache.commons.lang3.ClassUtils;
/*      */ import org.apache.commons.lang3.ObjectUtils;
/*      */ import org.apache.commons.lang3.SystemUtils;
/*      */ 
/*      */ public abstract class ToStringStyle
/*      */   implements Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -2587890625525655916L;
/*   81 */   public static final ToStringStyle DEFAULT_STYLE = new DefaultToStringStyle();
/*      */ 
/*   95 */   public static final ToStringStyle MULTI_LINE_STYLE = new MultiLineToStringStyle();
/*      */ 
/*  106 */   public static final ToStringStyle NO_FIELD_NAMES_STYLE = new NoFieldNameToStringStyle();
/*      */ 
/*  118 */   public static final ToStringStyle SHORT_PREFIX_STYLE = new ShortPrefixToStringStyle();
/*      */ 
/*  128 */   public static final ToStringStyle SIMPLE_STYLE = new SimpleToStringStyle();
/*      */ 
/*  136 */   private static final ThreadLocal<WeakHashMap<Object, Object>> REGISTRY = new ThreadLocal();
/*      */ 
/*  213 */   private boolean useFieldNames = true;
/*      */ 
/*  218 */   private boolean useClassName = true;
/*      */ 
/*  223 */   private boolean useShortClassName = false;
/*      */ 
/*  228 */   private boolean useIdentityHashCode = true;
/*      */ 
/*  233 */   private String contentStart = "[";
/*      */ 
/*  238 */   private String contentEnd = "]";
/*      */ 
/*  243 */   private String fieldNameValueSeparator = "=";
/*      */ 
/*  248 */   private boolean fieldSeparatorAtStart = false;
/*      */ 
/*  253 */   private boolean fieldSeparatorAtEnd = false;
/*      */ 
/*  258 */   private String fieldSeparator = ",";
/*      */ 
/*  263 */   private String arrayStart = "{";
/*      */ 
/*  268 */   private String arraySeparator = ",";
/*      */ 
/*  273 */   private boolean arrayContentDetail = true;
/*      */ 
/*  278 */   private String arrayEnd = "}";
/*      */ 
/*  284 */   private boolean defaultFullDetail = true;
/*      */ 
/*  289 */   private String nullText = "<null>";
/*      */ 
/*  294 */   private String sizeStartText = "<size=";
/*      */ 
/*  299 */   private String sizeEndText = ">";
/*      */ 
/*  304 */   private String summaryObjectStartText = "<";
/*      */ 
/*  309 */   private String summaryObjectEndText = ">";
/*      */ 
/*      */   static Map<Object, Object> getRegistry()
/*      */   {
/*  148 */     return (Map)REGISTRY.get();
/*      */   }
/*      */ 
/*      */   static boolean isRegistered(Object value)
/*      */   {
/*  163 */     Map m = getRegistry();
/*  164 */     return (m != null) && (m.containsKey(value));
/*      */   }
/*      */ 
/*      */   static void register(Object value)
/*      */   {
/*  177 */     if (value != null) {
/*  178 */       Map m = getRegistry();
/*  179 */       if (m == null) {
/*  180 */         REGISTRY.set(new WeakHashMap());
/*      */       }
/*  182 */       getRegistry().put(value, null);
/*      */     }
/*      */   }
/*      */ 
/*      */   static void unregister(Object value)
/*      */   {
/*  199 */     if (value != null) {
/*  200 */       Map m = getRegistry();
/*  201 */       if (m != null) {
/*  202 */         m.remove(value);
/*  203 */         if (m.isEmpty())
/*  204 */           REGISTRY.remove();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void appendSuper(StringBuffer buffer, String superToString)
/*      */   {
/*  333 */     appendToString(buffer, superToString);
/*      */   }
/*      */ 
/*      */   public void appendToString(StringBuffer buffer, String toString)
/*      */   {
/*  347 */     if (toString != null) {
/*  348 */       int pos1 = toString.indexOf(this.contentStart) + this.contentStart.length();
/*  349 */       int pos2 = toString.lastIndexOf(this.contentEnd);
/*  350 */       if ((pos1 != pos2) && (pos1 >= 0) && (pos2 >= 0)) {
/*  351 */         String data = toString.substring(pos1, pos2);
/*  352 */         if (this.fieldSeparatorAtStart) {
/*  353 */           removeLastFieldSeparator(buffer);
/*      */         }
/*  355 */         buffer.append(data);
/*  356 */         appendFieldSeparator(buffer);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void appendStart(StringBuffer buffer, Object object)
/*      */   {
/*  368 */     if (object != null) {
/*  369 */       appendClassName(buffer, object);
/*  370 */       appendIdentityHashCode(buffer, object);
/*  371 */       appendContentStart(buffer);
/*  372 */       if (this.fieldSeparatorAtStart)
/*  373 */         appendFieldSeparator(buffer);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void appendEnd(StringBuffer buffer, Object object)
/*      */   {
/*  386 */     if (!this.fieldSeparatorAtEnd) {
/*  387 */       removeLastFieldSeparator(buffer);
/*      */     }
/*  389 */     appendContentEnd(buffer);
/*  390 */     unregister(object);
/*      */   }
/*      */ 
/*      */   protected void removeLastFieldSeparator(StringBuffer buffer)
/*      */   {
/*  400 */     int len = buffer.length();
/*  401 */     int sepLen = this.fieldSeparator.length();
/*  402 */     if ((len > 0) && (sepLen > 0) && (len >= sepLen)) {
/*  403 */       boolean match = true;
/*  404 */       for (int i = 0; i < sepLen; i++) {
/*  405 */         if (buffer.charAt(len - 1 - i) != this.fieldSeparator.charAt(sepLen - 1 - i)) {
/*  406 */           match = false;
/*  407 */           break;
/*      */         }
/*      */       }
/*  410 */       if (match)
/*  411 */         buffer.setLength(len - sepLen);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail)
/*      */   {
/*  430 */     appendFieldStart(buffer, fieldName);
/*      */ 
/*  432 */     if (value == null) {
/*  433 */       appendNullText(buffer, fieldName);
/*      */     }
/*      */     else {
/*  436 */       appendInternal(buffer, fieldName, value, isFullDetail(fullDetail));
/*      */     }
/*      */ 
/*  439 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendInternal(StringBuffer buffer, String fieldName, Object value, boolean detail)
/*      */   {
/*  462 */     if ((isRegistered(value)) && (!(value instanceof Number)) && (!(value instanceof Boolean)) && (!(value instanceof Character)))
/*      */     {
/*  464 */       appendCyclicObject(buffer, fieldName, value);
/*  465 */       return;
/*      */     }
/*      */ 
/*  468 */     register(value);
/*      */     try
/*      */     {
/*  471 */       if ((value instanceof Collection)) {
/*  472 */         if (detail)
/*  473 */           appendDetail(buffer, fieldName, (Collection)value);
/*      */         else {
/*  475 */           appendSummarySize(buffer, fieldName, ((Collection)value).size());
/*      */         }
/*      */       }
/*  478 */       else if ((value instanceof Map)) {
/*  479 */         if (detail)
/*  480 */           appendDetail(buffer, fieldName, (Map)value);
/*      */         else {
/*  482 */           appendSummarySize(buffer, fieldName, ((Map)value).size());
/*      */         }
/*      */       }
/*  485 */       else if ((value instanceof long[])) {
/*  486 */         if (detail)
/*  487 */           appendDetail(buffer, fieldName, (long[])value);
/*      */         else {
/*  489 */           appendSummary(buffer, fieldName, (long[])value);
/*      */         }
/*      */       }
/*  492 */       else if ((value instanceof int[])) {
/*  493 */         if (detail)
/*  494 */           appendDetail(buffer, fieldName, (int[])value);
/*      */         else {
/*  496 */           appendSummary(buffer, fieldName, (int[])value);
/*      */         }
/*      */       }
/*  499 */       else if ((value instanceof short[])) {
/*  500 */         if (detail)
/*  501 */           appendDetail(buffer, fieldName, (short[])value);
/*      */         else {
/*  503 */           appendSummary(buffer, fieldName, (short[])value);
/*      */         }
/*      */       }
/*  506 */       else if ((value instanceof byte[])) {
/*  507 */         if (detail)
/*  508 */           appendDetail(buffer, fieldName, (byte[])value);
/*      */         else {
/*  510 */           appendSummary(buffer, fieldName, (byte[])value);
/*      */         }
/*      */       }
/*  513 */       else if ((value instanceof char[])) {
/*  514 */         if (detail)
/*  515 */           appendDetail(buffer, fieldName, (char[])value);
/*      */         else {
/*  517 */           appendSummary(buffer, fieldName, (char[])value);
/*      */         }
/*      */       }
/*  520 */       else if ((value instanceof double[])) {
/*  521 */         if (detail)
/*  522 */           appendDetail(buffer, fieldName, (double[])value);
/*      */         else {
/*  524 */           appendSummary(buffer, fieldName, (double[])value);
/*      */         }
/*      */       }
/*  527 */       else if ((value instanceof float[])) {
/*  528 */         if (detail)
/*  529 */           appendDetail(buffer, fieldName, (float[])value);
/*      */         else {
/*  531 */           appendSummary(buffer, fieldName, (float[])value);
/*      */         }
/*      */       }
/*  534 */       else if ((value instanceof boolean[])) {
/*  535 */         if (detail)
/*  536 */           appendDetail(buffer, fieldName, (boolean[])value);
/*      */         else {
/*  538 */           appendSummary(buffer, fieldName, (boolean[])value);
/*      */         }
/*      */       }
/*  541 */       else if (value.getClass().isArray()) {
/*  542 */         if (detail)
/*  543 */           appendDetail(buffer, fieldName, (Object[])value);
/*      */         else {
/*  545 */           appendSummary(buffer, fieldName, (Object[])value);
/*      */         }
/*      */ 
/*      */       }
/*  549 */       else if (detail)
/*  550 */         appendDetail(buffer, fieldName, value);
/*      */       else
/*  552 */         appendSummary(buffer, fieldName, value);
/*      */     }
/*      */     finally
/*      */     {
/*  556 */       unregister(value);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void appendCyclicObject(StringBuffer buffer, String fieldName, Object value)
/*      */   {
/*  573 */     ObjectUtils.identityToString(buffer, value);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Object value)
/*      */   {
/*  586 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Collection<?> coll)
/*      */   {
/*  598 */     buffer.append(coll);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Map<?, ?> map)
/*      */   {
/*  610 */     buffer.append(map);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, Object value)
/*      */   {
/*  623 */     buffer.append(this.summaryObjectStartText);
/*  624 */     buffer.append(getShortClassName(value.getClass()));
/*  625 */     buffer.append(this.summaryObjectEndText);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, long value)
/*      */   {
/*  639 */     appendFieldStart(buffer, fieldName);
/*  640 */     appendDetail(buffer, fieldName, value);
/*  641 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, long value)
/*      */   {
/*  653 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, int value)
/*      */   {
/*  667 */     appendFieldStart(buffer, fieldName);
/*  668 */     appendDetail(buffer, fieldName, value);
/*  669 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, int value)
/*      */   {
/*  681 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, short value)
/*      */   {
/*  695 */     appendFieldStart(buffer, fieldName);
/*  696 */     appendDetail(buffer, fieldName, value);
/*  697 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, short value)
/*      */   {
/*  709 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, byte value)
/*      */   {
/*  723 */     appendFieldStart(buffer, fieldName);
/*  724 */     appendDetail(buffer, fieldName, value);
/*  725 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, byte value)
/*      */   {
/*  737 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, char value)
/*      */   {
/*  751 */     appendFieldStart(buffer, fieldName);
/*  752 */     appendDetail(buffer, fieldName, value);
/*  753 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, char value)
/*      */   {
/*  765 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, double value)
/*      */   {
/*  779 */     appendFieldStart(buffer, fieldName);
/*  780 */     appendDetail(buffer, fieldName, value);
/*  781 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, double value)
/*      */   {
/*  793 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, float value)
/*      */   {
/*  807 */     appendFieldStart(buffer, fieldName);
/*  808 */     appendDetail(buffer, fieldName, value);
/*  809 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, float value)
/*      */   {
/*  821 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, boolean value)
/*      */   {
/*  835 */     appendFieldStart(buffer, fieldName);
/*  836 */     appendDetail(buffer, fieldName, value);
/*  837 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, boolean value)
/*      */   {
/*  849 */     buffer.append(value);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail)
/*      */   {
/*  863 */     appendFieldStart(buffer, fieldName);
/*      */ 
/*  865 */     if (array == null) {
/*  866 */       appendNullText(buffer, fieldName);
/*      */     }
/*  868 */     else if (isFullDetail(fullDetail)) {
/*  869 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/*  872 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/*  875 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, Object[] array)
/*      */   {
/*  890 */     buffer.append(this.arrayStart);
/*  891 */     for (int i = 0; i < array.length; i++) {
/*  892 */       Object item = array[i];
/*  893 */       if (i > 0) {
/*  894 */         buffer.append(this.arraySeparator);
/*      */       }
/*  896 */       if (item == null) {
/*  897 */         appendNullText(buffer, fieldName);
/*      */       }
/*      */       else {
/*  900 */         appendInternal(buffer, fieldName, item, this.arrayContentDetail);
/*      */       }
/*      */     }
/*  903 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void reflectionAppendArrayDetail(StringBuffer buffer, String fieldName, Object array)
/*      */   {
/*  916 */     buffer.append(this.arrayStart);
/*  917 */     int length = Array.getLength(array);
/*  918 */     for (int i = 0; i < length; i++) {
/*  919 */       Object item = Array.get(array, i);
/*  920 */       if (i > 0) {
/*  921 */         buffer.append(this.arraySeparator);
/*      */       }
/*  923 */       if (item == null) {
/*  924 */         appendNullText(buffer, fieldName);
/*      */       }
/*      */       else {
/*  927 */         appendInternal(buffer, fieldName, item, this.arrayContentDetail);
/*      */       }
/*      */     }
/*  930 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, Object[] array)
/*      */   {
/*  943 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail)
/*      */   {
/*  959 */     appendFieldStart(buffer, fieldName);
/*      */ 
/*  961 */     if (array == null) {
/*  962 */       appendNullText(buffer, fieldName);
/*      */     }
/*  964 */     else if (isFullDetail(fullDetail)) {
/*  965 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/*  968 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/*  971 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, long[] array)
/*      */   {
/*  984 */     buffer.append(this.arrayStart);
/*  985 */     for (int i = 0; i < array.length; i++) {
/*  986 */       if (i > 0) {
/*  987 */         buffer.append(this.arraySeparator);
/*      */       }
/*  989 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/*  991 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, long[] array)
/*      */   {
/* 1004 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail)
/*      */   {
/* 1020 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1022 */     if (array == null) {
/* 1023 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1025 */     else if (isFullDetail(fullDetail)) {
/* 1026 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1029 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1032 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, int[] array)
/*      */   {
/* 1045 */     buffer.append(this.arrayStart);
/* 1046 */     for (int i = 0; i < array.length; i++) {
/* 1047 */       if (i > 0) {
/* 1048 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1050 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1052 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, int[] array)
/*      */   {
/* 1065 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail)
/*      */   {
/* 1081 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1083 */     if (array == null) {
/* 1084 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1086 */     else if (isFullDetail(fullDetail)) {
/* 1087 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1090 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1093 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, short[] array)
/*      */   {
/* 1106 */     buffer.append(this.arrayStart);
/* 1107 */     for (int i = 0; i < array.length; i++) {
/* 1108 */       if (i > 0) {
/* 1109 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1111 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1113 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, short[] array)
/*      */   {
/* 1126 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail)
/*      */   {
/* 1142 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1144 */     if (array == null) {
/* 1145 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1147 */     else if (isFullDetail(fullDetail)) {
/* 1148 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1151 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1154 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, byte[] array)
/*      */   {
/* 1167 */     buffer.append(this.arrayStart);
/* 1168 */     for (int i = 0; i < array.length; i++) {
/* 1169 */       if (i > 0) {
/* 1170 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1172 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1174 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, byte[] array)
/*      */   {
/* 1187 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail)
/*      */   {
/* 1203 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1205 */     if (array == null) {
/* 1206 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1208 */     else if (isFullDetail(fullDetail)) {
/* 1209 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1212 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1215 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, char[] array)
/*      */   {
/* 1228 */     buffer.append(this.arrayStart);
/* 1229 */     for (int i = 0; i < array.length; i++) {
/* 1230 */       if (i > 0) {
/* 1231 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1233 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1235 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, char[] array)
/*      */   {
/* 1248 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail)
/*      */   {
/* 1264 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1266 */     if (array == null) {
/* 1267 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1269 */     else if (isFullDetail(fullDetail)) {
/* 1270 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1273 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1276 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, double[] array)
/*      */   {
/* 1289 */     buffer.append(this.arrayStart);
/* 1290 */     for (int i = 0; i < array.length; i++) {
/* 1291 */       if (i > 0) {
/* 1292 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1294 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1296 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, double[] array)
/*      */   {
/* 1309 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail)
/*      */   {
/* 1325 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1327 */     if (array == null) {
/* 1328 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1330 */     else if (isFullDetail(fullDetail)) {
/* 1331 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1334 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1337 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, float[] array)
/*      */   {
/* 1350 */     buffer.append(this.arrayStart);
/* 1351 */     for (int i = 0; i < array.length; i++) {
/* 1352 */       if (i > 0) {
/* 1353 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1355 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1357 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, float[] array)
/*      */   {
/* 1370 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail)
/*      */   {
/* 1386 */     appendFieldStart(buffer, fieldName);
/*      */ 
/* 1388 */     if (array == null) {
/* 1389 */       appendNullText(buffer, fieldName);
/*      */     }
/* 1391 */     else if (isFullDetail(fullDetail)) {
/* 1392 */       appendDetail(buffer, fieldName, array);
/*      */     }
/*      */     else {
/* 1395 */       appendSummary(buffer, fieldName, array);
/*      */     }
/*      */ 
/* 1398 */     appendFieldEnd(buffer, fieldName);
/*      */   }
/*      */ 
/*      */   protected void appendDetail(StringBuffer buffer, String fieldName, boolean[] array)
/*      */   {
/* 1411 */     buffer.append(this.arrayStart);
/* 1412 */     for (int i = 0; i < array.length; i++) {
/* 1413 */       if (i > 0) {
/* 1414 */         buffer.append(this.arraySeparator);
/*      */       }
/* 1416 */       appendDetail(buffer, fieldName, array[i]);
/*      */     }
/* 1418 */     buffer.append(this.arrayEnd);
/*      */   }
/*      */ 
/*      */   protected void appendSummary(StringBuffer buffer, String fieldName, boolean[] array)
/*      */   {
/* 1431 */     appendSummarySize(buffer, fieldName, array.length);
/*      */   }
/*      */ 
/*      */   protected void appendClassName(StringBuffer buffer, Object object)
/*      */   {
/* 1443 */     if ((this.useClassName) && (object != null)) {
/* 1444 */       register(object);
/* 1445 */       if (this.useShortClassName)
/* 1446 */         buffer.append(getShortClassName(object.getClass()));
/*      */       else
/* 1448 */         buffer.append(object.getClass().getName());
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void appendIdentityHashCode(StringBuffer buffer, Object object)
/*      */   {
/* 1460 */     if ((isUseIdentityHashCode()) && (object != null)) {
/* 1461 */       register(object);
/* 1462 */       buffer.append('@');
/* 1463 */       buffer.append(Integer.toHexString(System.identityHashCode(object)));
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void appendContentStart(StringBuffer buffer)
/*      */   {
/* 1473 */     buffer.append(this.contentStart);
/*      */   }
/*      */ 
/*      */   protected void appendContentEnd(StringBuffer buffer)
/*      */   {
/* 1482 */     buffer.append(this.contentEnd);
/*      */   }
/*      */ 
/*      */   protected void appendNullText(StringBuffer buffer, String fieldName)
/*      */   {
/* 1494 */     buffer.append(this.nullText);
/*      */   }
/*      */ 
/*      */   protected void appendFieldSeparator(StringBuffer buffer)
/*      */   {
/* 1503 */     buffer.append(this.fieldSeparator);
/*      */   }
/*      */ 
/*      */   protected void appendFieldStart(StringBuffer buffer, String fieldName)
/*      */   {
/* 1513 */     if ((this.useFieldNames) && (fieldName != null)) {
/* 1514 */       buffer.append(fieldName);
/* 1515 */       buffer.append(this.fieldNameValueSeparator);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void appendFieldEnd(StringBuffer buffer, String fieldName)
/*      */   {
/* 1526 */     appendFieldSeparator(buffer);
/*      */   }
/*      */ 
/*      */   protected void appendSummarySize(StringBuffer buffer, String fieldName, int size)
/*      */   {
/* 1545 */     buffer.append(this.sizeStartText);
/* 1546 */     buffer.append(size);
/* 1547 */     buffer.append(this.sizeEndText);
/*      */   }
/*      */ 
/*      */   protected boolean isFullDetail(Boolean fullDetailRequest)
/*      */   {
/* 1565 */     if (fullDetailRequest == null) {
/* 1566 */       return this.defaultFullDetail;
/*      */     }
/* 1568 */     return fullDetailRequest.booleanValue();
/*      */   }
/*      */ 
/*      */   protected String getShortClassName(Class<?> cls)
/*      */   {
/* 1581 */     return ClassUtils.getShortClassName(cls);
/*      */   }
/*      */ 
/*      */   protected boolean isUseClassName()
/*      */   {
/* 1595 */     return this.useClassName;
/*      */   }
/*      */ 
/*      */   protected void setUseClassName(boolean useClassName)
/*      */   {
/* 1604 */     this.useClassName = useClassName;
/*      */   }
/*      */ 
/*      */   protected boolean isUseShortClassName()
/*      */   {
/* 1616 */     return this.useShortClassName;
/*      */   }
/*      */ 
/*      */   protected void setUseShortClassName(boolean useShortClassName)
/*      */   {
/* 1626 */     this.useShortClassName = useShortClassName;
/*      */   }
/*      */ 
/*      */   protected boolean isUseIdentityHashCode()
/*      */   {
/* 1637 */     return this.useIdentityHashCode;
/*      */   }
/*      */ 
/*      */   protected void setUseIdentityHashCode(boolean useIdentityHashCode)
/*      */   {
/* 1646 */     this.useIdentityHashCode = useIdentityHashCode;
/*      */   }
/*      */ 
/*      */   protected boolean isUseFieldNames()
/*      */   {
/* 1657 */     return this.useFieldNames;
/*      */   }
/*      */ 
/*      */   protected void setUseFieldNames(boolean useFieldNames)
/*      */   {
/* 1666 */     this.useFieldNames = useFieldNames;
/*      */   }
/*      */ 
/*      */   protected boolean isDefaultFullDetail()
/*      */   {
/* 1678 */     return this.defaultFullDetail;
/*      */   }
/*      */ 
/*      */   protected void setDefaultFullDetail(boolean defaultFullDetail)
/*      */   {
/* 1688 */     this.defaultFullDetail = defaultFullDetail;
/*      */   }
/*      */ 
/*      */   protected boolean isArrayContentDetail()
/*      */   {
/* 1699 */     return this.arrayContentDetail;
/*      */   }
/*      */ 
/*      */   protected void setArrayContentDetail(boolean arrayContentDetail)
/*      */   {
/* 1708 */     this.arrayContentDetail = arrayContentDetail;
/*      */   }
/*      */ 
/*      */   protected String getArrayStart()
/*      */   {
/* 1719 */     return this.arrayStart;
/*      */   }
/*      */ 
/*      */   protected void setArrayStart(String arrayStart)
/*      */   {
/* 1731 */     if (arrayStart == null) {
/* 1732 */       arrayStart = "";
/*      */     }
/* 1734 */     this.arrayStart = arrayStart;
/*      */   }
/*      */ 
/*      */   protected String getArrayEnd()
/*      */   {
/* 1745 */     return this.arrayEnd;
/*      */   }
/*      */ 
/*      */   protected void setArrayEnd(String arrayEnd)
/*      */   {
/* 1757 */     if (arrayEnd == null) {
/* 1758 */       arrayEnd = "";
/*      */     }
/* 1760 */     this.arrayEnd = arrayEnd;
/*      */   }
/*      */ 
/*      */   protected String getArraySeparator()
/*      */   {
/* 1771 */     return this.arraySeparator;
/*      */   }
/*      */ 
/*      */   protected void setArraySeparator(String arraySeparator)
/*      */   {
/* 1783 */     if (arraySeparator == null) {
/* 1784 */       arraySeparator = "";
/*      */     }
/* 1786 */     this.arraySeparator = arraySeparator;
/*      */   }
/*      */ 
/*      */   protected String getContentStart()
/*      */   {
/* 1797 */     return this.contentStart;
/*      */   }
/*      */ 
/*      */   protected void setContentStart(String contentStart)
/*      */   {
/* 1809 */     if (contentStart == null) {
/* 1810 */       contentStart = "";
/*      */     }
/* 1812 */     this.contentStart = contentStart;
/*      */   }
/*      */ 
/*      */   protected String getContentEnd()
/*      */   {
/* 1823 */     return this.contentEnd;
/*      */   }
/*      */ 
/*      */   protected void setContentEnd(String contentEnd)
/*      */   {
/* 1835 */     if (contentEnd == null) {
/* 1836 */       contentEnd = "";
/*      */     }
/* 1838 */     this.contentEnd = contentEnd;
/*      */   }
/*      */ 
/*      */   protected String getFieldNameValueSeparator()
/*      */   {
/* 1849 */     return this.fieldNameValueSeparator;
/*      */   }
/*      */ 
/*      */   protected void setFieldNameValueSeparator(String fieldNameValueSeparator)
/*      */   {
/* 1861 */     if (fieldNameValueSeparator == null) {
/* 1862 */       fieldNameValueSeparator = "";
/*      */     }
/* 1864 */     this.fieldNameValueSeparator = fieldNameValueSeparator;
/*      */   }
/*      */ 
/*      */   protected String getFieldSeparator()
/*      */   {
/* 1875 */     return this.fieldSeparator;
/*      */   }
/*      */ 
/*      */   protected void setFieldSeparator(String fieldSeparator)
/*      */   {
/* 1887 */     if (fieldSeparator == null) {
/* 1888 */       fieldSeparator = "";
/*      */     }
/* 1890 */     this.fieldSeparator = fieldSeparator;
/*      */   }
/*      */ 
/*      */   protected boolean isFieldSeparatorAtStart()
/*      */   {
/* 1903 */     return this.fieldSeparatorAtStart;
/*      */   }
/*      */ 
/*      */   protected void setFieldSeparatorAtStart(boolean fieldSeparatorAtStart)
/*      */   {
/* 1914 */     this.fieldSeparatorAtStart = fieldSeparatorAtStart;
/*      */   }
/*      */ 
/*      */   protected boolean isFieldSeparatorAtEnd()
/*      */   {
/* 1927 */     return this.fieldSeparatorAtEnd;
/*      */   }
/*      */ 
/*      */   protected void setFieldSeparatorAtEnd(boolean fieldSeparatorAtEnd)
/*      */   {
/* 1938 */     this.fieldSeparatorAtEnd = fieldSeparatorAtEnd;
/*      */   }
/*      */ 
/*      */   protected String getNullText()
/*      */   {
/* 1949 */     return this.nullText;
/*      */   }
/*      */ 
/*      */   protected void setNullText(String nullText)
/*      */   {
/* 1961 */     if (nullText == null) {
/* 1962 */       nullText = "";
/*      */     }
/* 1964 */     this.nullText = nullText;
/*      */   }
/*      */ 
/*      */   protected String getSizeStartText()
/*      */   {
/* 1978 */     return this.sizeStartText;
/*      */   }
/*      */ 
/*      */   protected void setSizeStartText(String sizeStartText)
/*      */   {
/* 1993 */     if (sizeStartText == null) {
/* 1994 */       sizeStartText = "";
/*      */     }
/* 1996 */     this.sizeStartText = sizeStartText;
/*      */   }
/*      */ 
/*      */   protected String getSizeEndText()
/*      */   {
/* 2010 */     return this.sizeEndText;
/*      */   }
/*      */ 
/*      */   protected void setSizeEndText(String sizeEndText)
/*      */   {
/* 2025 */     if (sizeEndText == null) {
/* 2026 */       sizeEndText = "";
/*      */     }
/* 2028 */     this.sizeEndText = sizeEndText;
/*      */   }
/*      */ 
/*      */   protected String getSummaryObjectStartText()
/*      */   {
/* 2042 */     return this.summaryObjectStartText;
/*      */   }
/*      */ 
/*      */   protected void setSummaryObjectStartText(String summaryObjectStartText)
/*      */   {
/* 2057 */     if (summaryObjectStartText == null) {
/* 2058 */       summaryObjectStartText = "";
/*      */     }
/* 2060 */     this.summaryObjectStartText = summaryObjectStartText;
/*      */   }
/*      */ 
/*      */   protected String getSummaryObjectEndText()
/*      */   {
/* 2074 */     return this.summaryObjectEndText;
/*      */   }
/*      */ 
/*      */   protected void setSummaryObjectEndText(String summaryObjectEndText)
/*      */   {
/* 2089 */     if (summaryObjectEndText == null) {
/* 2090 */       summaryObjectEndText = "";
/*      */     }
/* 2092 */     this.summaryObjectEndText = summaryObjectEndText;
/*      */   }
/*      */ 
/*      */   private static final class MultiLineToStringStyle extends ToStringStyle
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     MultiLineToStringStyle()
/*      */     {
/* 2254 */       setContentStart("[");
/* 2255 */       setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
/* 2256 */       setFieldSeparatorAtStart(true);
/* 2257 */       setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
/*      */     }
/*      */ 
/*      */     private Object readResolve()
/*      */     {
/* 2266 */       return ToStringStyle.MULTI_LINE_STYLE;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class SimpleToStringStyle extends ToStringStyle
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     SimpleToStringStyle()
/*      */     {
/* 2218 */       setUseClassName(false);
/* 2219 */       setUseIdentityHashCode(false);
/* 2220 */       setUseFieldNames(false);
/* 2221 */       setContentStart("");
/* 2222 */       setContentEnd("");
/*      */     }
/*      */ 
/*      */     private Object readResolve()
/*      */     {
/* 2230 */       return ToStringStyle.SIMPLE_STYLE;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class ShortPrefixToStringStyle extends ToStringStyle
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     ShortPrefixToStringStyle()
/*      */     {
/* 2186 */       setUseShortClassName(true);
/* 2187 */       setUseIdentityHashCode(false);
/*      */     }
/*      */ 
/*      */     private Object readResolve()
/*      */     {
/* 2195 */       return ToStringStyle.SHORT_PREFIX_STYLE;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class NoFieldNameToStringStyle extends ToStringStyle
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     NoFieldNameToStringStyle()
/*      */     {
/* 2152 */       setUseFieldNames(false);
/*      */     }
/*      */ 
/*      */     private Object readResolve()
/*      */     {
/* 2161 */       return ToStringStyle.NO_FIELD_NAMES_STYLE;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static final class DefaultToStringStyle extends ToStringStyle
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     private Object readResolve()
/*      */     {
/* 2127 */       return ToStringStyle.DEFAULT_STYLE;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.ToStringStyle
 * JD-Core Version:    0.6.2
 */