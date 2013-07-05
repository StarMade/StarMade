/*      */ package org.apache.commons.lang3.builder;
/*      */ 
/*      */ import org.apache.commons.lang3.ObjectUtils;
/*      */ 
/*      */ public class ToStringBuilder
/*      */   implements Builder<String>
/*      */ {
/*   94 */   private static volatile ToStringStyle defaultStyle = ToStringStyle.DEFAULT_STYLE;
/*      */   private final StringBuffer buffer;
/*      */   private final Object object;
/*      */   private final ToStringStyle style;
/*      */ 
/*      */   public static ToStringStyle getDefaultStyle()
/*      */   {
/*  117 */     return defaultStyle;
/*      */   }
/*      */ 
/*      */   public static void setDefaultStyle(ToStringStyle style)
/*      */   {
/*  136 */     if (style == null) {
/*  137 */       throw new IllegalArgumentException("The style must not be null");
/*      */     }
/*  139 */     defaultStyle = style;
/*      */   }
/*      */ 
/*      */   public static String reflectionToString(Object object)
/*      */   {
/*  152 */     return ReflectionToStringBuilder.toString(object);
/*      */   }
/*      */ 
/*      */   public static String reflectionToString(Object object, ToStringStyle style)
/*      */   {
/*  165 */     return ReflectionToStringBuilder.toString(object, style);
/*      */   }
/*      */ 
/*      */   public static String reflectionToString(Object object, ToStringStyle style, boolean outputTransients)
/*      */   {
/*  179 */     return ReflectionToStringBuilder.toString(object, style, outputTransients, false, null);
/*      */   }
/*      */ 
/*      */   public static <T> String reflectionToString(T object, ToStringStyle style, boolean outputTransients, Class<? super T> reflectUpToClass)
/*      */   {
/*  200 */     return ReflectionToStringBuilder.toString(object, style, outputTransients, false, reflectUpToClass);
/*      */   }
/*      */ 
/*      */   public ToStringBuilder(Object object)
/*      */   {
/*  226 */     this(object, null, null);
/*      */   }
/*      */ 
/*      */   public ToStringBuilder(Object object, ToStringStyle style)
/*      */   {
/*  238 */     this(object, style, null);
/*      */   }
/*      */ 
/*      */   public ToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer)
/*      */   {
/*  253 */     if (style == null) {
/*  254 */       style = getDefaultStyle();
/*      */     }
/*  256 */     if (buffer == null) {
/*  257 */       buffer = new StringBuffer(512);
/*      */     }
/*  259 */     this.buffer = buffer;
/*  260 */     this.style = style;
/*  261 */     this.object = object;
/*      */ 
/*  263 */     style.appendStart(buffer, object);
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(boolean value)
/*      */   {
/*  276 */     this.style.append(this.buffer, null, value);
/*  277 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(boolean[] array)
/*      */   {
/*  290 */     this.style.append(this.buffer, null, array, null);
/*  291 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(byte value)
/*      */   {
/*  304 */     this.style.append(this.buffer, null, value);
/*  305 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(byte[] array)
/*      */   {
/*  318 */     this.style.append(this.buffer, null, array, null);
/*  319 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(char value)
/*      */   {
/*  332 */     this.style.append(this.buffer, null, value);
/*  333 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(char[] array)
/*      */   {
/*  346 */     this.style.append(this.buffer, null, array, null);
/*  347 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(double value)
/*      */   {
/*  360 */     this.style.append(this.buffer, null, value);
/*  361 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(double[] array)
/*      */   {
/*  374 */     this.style.append(this.buffer, null, array, null);
/*  375 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(float value)
/*      */   {
/*  388 */     this.style.append(this.buffer, null, value);
/*  389 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(float[] array)
/*      */   {
/*  402 */     this.style.append(this.buffer, null, array, null);
/*  403 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(int value)
/*      */   {
/*  416 */     this.style.append(this.buffer, null, value);
/*  417 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(int[] array)
/*      */   {
/*  430 */     this.style.append(this.buffer, null, array, null);
/*  431 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(long value)
/*      */   {
/*  444 */     this.style.append(this.buffer, null, value);
/*  445 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(long[] array)
/*      */   {
/*  458 */     this.style.append(this.buffer, null, array, null);
/*  459 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(Object obj)
/*      */   {
/*  472 */     this.style.append(this.buffer, null, obj, null);
/*  473 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(Object[] array)
/*      */   {
/*  486 */     this.style.append(this.buffer, null, array, null);
/*  487 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(short value)
/*      */   {
/*  500 */     this.style.append(this.buffer, null, value);
/*  501 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(short[] array)
/*      */   {
/*  514 */     this.style.append(this.buffer, null, array, null);
/*  515 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, boolean value)
/*      */   {
/*  527 */     this.style.append(this.buffer, fieldName, value);
/*  528 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, boolean[] array)
/*      */   {
/*  540 */     this.style.append(this.buffer, fieldName, array, null);
/*  541 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, boolean[] array, boolean fullDetail)
/*      */   {
/*  560 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  561 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, byte value)
/*      */   {
/*  573 */     this.style.append(this.buffer, fieldName, value);
/*  574 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, byte[] array)
/*      */   {
/*  585 */     this.style.append(this.buffer, fieldName, array, null);
/*  586 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, byte[] array, boolean fullDetail)
/*      */   {
/*  605 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  606 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, char value)
/*      */   {
/*  618 */     this.style.append(this.buffer, fieldName, value);
/*  619 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, char[] array)
/*      */   {
/*  631 */     this.style.append(this.buffer, fieldName, array, null);
/*  632 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, char[] array, boolean fullDetail)
/*      */   {
/*  651 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  652 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, double value)
/*      */   {
/*  664 */     this.style.append(this.buffer, fieldName, value);
/*  665 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, double[] array)
/*      */   {
/*  677 */     this.style.append(this.buffer, fieldName, array, null);
/*  678 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, double[] array, boolean fullDetail)
/*      */   {
/*  697 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  698 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, float value)
/*      */   {
/*  710 */     this.style.append(this.buffer, fieldName, value);
/*  711 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, float[] array)
/*      */   {
/*  723 */     this.style.append(this.buffer, fieldName, array, null);
/*  724 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, float[] array, boolean fullDetail)
/*      */   {
/*  743 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  744 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, int value)
/*      */   {
/*  756 */     this.style.append(this.buffer, fieldName, value);
/*  757 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, int[] array)
/*      */   {
/*  769 */     this.style.append(this.buffer, fieldName, array, null);
/*  770 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, int[] array, boolean fullDetail)
/*      */   {
/*  789 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  790 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, long value)
/*      */   {
/*  802 */     this.style.append(this.buffer, fieldName, value);
/*  803 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, long[] array)
/*      */   {
/*  815 */     this.style.append(this.buffer, fieldName, array, null);
/*  816 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, long[] array, boolean fullDetail)
/*      */   {
/*  835 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  836 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, Object obj)
/*      */   {
/*  848 */     this.style.append(this.buffer, fieldName, obj, null);
/*  849 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, Object obj, boolean fullDetail)
/*      */   {
/*  863 */     this.style.append(this.buffer, fieldName, obj, Boolean.valueOf(fullDetail));
/*  864 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, Object[] array)
/*      */   {
/*  876 */     this.style.append(this.buffer, fieldName, array, null);
/*  877 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, Object[] array, boolean fullDetail)
/*      */   {
/*  896 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  897 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, short value)
/*      */   {
/*  909 */     this.style.append(this.buffer, fieldName, value);
/*  910 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, short[] array)
/*      */   {
/*  922 */     this.style.append(this.buffer, fieldName, array, null);
/*  923 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder append(String fieldName, short[] array, boolean fullDetail)
/*      */   {
/*  942 */     this.style.append(this.buffer, fieldName, array, Boolean.valueOf(fullDetail));
/*  943 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder appendAsObjectToString(Object object)
/*      */   {
/*  956 */     ObjectUtils.identityToString(getStringBuffer(), object);
/*  957 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder appendSuper(String superToString)
/*      */   {
/*  975 */     if (superToString != null) {
/*  976 */       this.style.appendSuper(this.buffer, superToString);
/*      */     }
/*  978 */     return this;
/*      */   }
/*      */ 
/*      */   public ToStringBuilder appendToString(String toString)
/*      */   {
/* 1009 */     if (toString != null) {
/* 1010 */       this.style.appendToString(this.buffer, toString);
/*      */     }
/* 1012 */     return this;
/*      */   }
/*      */ 
/*      */   public Object getObject()
/*      */   {
/* 1022 */     return this.object;
/*      */   }
/*      */ 
/*      */   public StringBuffer getStringBuffer()
/*      */   {
/* 1031 */     return this.buffer;
/*      */   }
/*      */ 
/*      */   public ToStringStyle getStyle()
/*      */   {
/* 1043 */     return this.style;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1058 */     if (getObject() == null)
/* 1059 */       getStringBuffer().append(getStyle().getNullText());
/*      */     else {
/* 1061 */       this.style.appendEnd(getStringBuffer(), getObject());
/*      */     }
/* 1063 */     return getStringBuffer().toString();
/*      */   }
/*      */ 
/*      */   public String build()
/*      */   {
/* 1077 */     return toString();
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.ToStringBuilder
 * JD-Core Version:    0.6.2
 */