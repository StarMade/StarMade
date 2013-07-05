/*     */ package org.apache.commons.lang3.builder;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ 
/*     */ public class ReflectionToStringBuilder extends ToStringBuilder
/*     */ {
/* 351 */   private boolean appendStatics = false;
/*     */ 
/* 356 */   private boolean appendTransients = false;
/*     */   protected String[] excludeFieldNames;
/* 368 */   private Class<?> upToClass = null;
/*     */ 
/*     */   public static String toString(Object object)
/*     */   {
/* 113 */     return toString(object, null, false, false, null);
/*     */   }
/*     */ 
/*     */   public static String toString(Object object, ToStringStyle style)
/*     */   {
/* 145 */     return toString(object, style, false, false, null);
/*     */   }
/*     */ 
/*     */   public static String toString(Object object, ToStringStyle style, boolean outputTransients)
/*     */   {
/* 183 */     return toString(object, style, outputTransients, false, null);
/*     */   }
/*     */ 
/*     */   public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics)
/*     */   {
/* 229 */     return toString(object, style, outputTransients, outputStatics, null);
/*     */   }
/*     */ 
/*     */   public static <T> String toString(T object, ToStringStyle style, boolean outputTransients, boolean outputStatics, Class<? super T> reflectUpToClass)
/*     */   {
/* 282 */     return new ReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients, outputStatics).toString();
/*     */   }
/*     */ 
/*     */   public static String toStringExclude(Object object, Collection<String> excludeFieldNames)
/*     */   {
/* 296 */     return toStringExclude(object, toNoNullStringArray(excludeFieldNames));
/*     */   }
/*     */ 
/*     */   static String[] toNoNullStringArray(Collection<String> collection)
/*     */   {
/* 309 */     if (collection == null) {
/* 310 */       return ArrayUtils.EMPTY_STRING_ARRAY;
/*     */     }
/* 312 */     return toNoNullStringArray(collection.toArray());
/*     */   }
/*     */ 
/*     */   static String[] toNoNullStringArray(Object[] array)
/*     */   {
/* 325 */     List list = new ArrayList(array.length);
/* 326 */     for (Object e : array) {
/* 327 */       if (e != null) {
/* 328 */         list.add(e.toString());
/*     */       }
/*     */     }
/* 331 */     return (String[])list.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
/*     */   }
/*     */ 
/*     */   public static String toStringExclude(Object object, String[] excludeFieldNames)
/*     */   {
/* 345 */     return new ReflectionToStringBuilder(object).setExcludeFieldNames(excludeFieldNames).toString();
/*     */   }
/*     */ 
/*     */   public ReflectionToStringBuilder(Object object)
/*     */   {
/* 385 */     super(object);
/*     */   }
/*     */ 
/*     */   public ReflectionToStringBuilder(Object object, ToStringStyle style)
/*     */   {
/* 405 */     super(object, style);
/*     */   }
/*     */ 
/*     */   public ReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer)
/*     */   {
/* 431 */     super(object, style, buffer);
/*     */   }
/*     */ 
/*     */   public <T> ReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer, Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics)
/*     */   {
/* 456 */     super(object, style, buffer);
/* 457 */     setUpToClass(reflectUpToClass);
/* 458 */     setAppendTransients(outputTransients);
/* 459 */     setAppendStatics(outputStatics);
/*     */   }
/*     */ 
/*     */   protected boolean accept(Field field)
/*     */   {
/* 475 */     if (field.getName().indexOf('$') != -1)
/*     */     {
/* 477 */       return false;
/*     */     }
/* 479 */     if ((Modifier.isTransient(field.getModifiers())) && (!isAppendTransients()))
/*     */     {
/* 481 */       return false;
/*     */     }
/* 483 */     if ((Modifier.isStatic(field.getModifiers())) && (!isAppendStatics()))
/*     */     {
/* 485 */       return false;
/*     */     }
/* 487 */     if ((this.excludeFieldNames != null) && (Arrays.binarySearch(this.excludeFieldNames, field.getName()) >= 0))
/*     */     {
/* 490 */       return false;
/*     */     }
/* 492 */     return true;
/*     */   }
/*     */ 
/*     */   protected void appendFieldsIn(Class<?> clazz)
/*     */   {
/* 509 */     if (clazz.isArray()) {
/* 510 */       reflectionAppendArray(getObject());
/* 511 */       return;
/*     */     }
/* 513 */     Field[] fields = clazz.getDeclaredFields();
/* 514 */     AccessibleObject.setAccessible(fields, true);
/* 515 */     for (Field field : fields) {
/* 516 */       String fieldName = field.getName();
/* 517 */       if (accept(field))
/*     */       {
/*     */         try
/*     */         {
/* 521 */           Object fieldValue = getValue(field);
/* 522 */           append(fieldName, fieldValue);
/*     */         }
/*     */         catch (IllegalAccessException ex)
/*     */         {
/* 528 */           throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String[] getExcludeFieldNames()
/*     */   {
/* 538 */     return (String[])this.excludeFieldNames.clone();
/*     */   }
/*     */ 
/*     */   public Class<?> getUpToClass()
/*     */   {
/* 549 */     return this.upToClass;
/*     */   }
/*     */ 
/*     */   protected Object getValue(Field field)
/*     */     throws IllegalArgumentException, IllegalAccessException
/*     */   {
/* 569 */     return field.get(getObject());
/*     */   }
/*     */ 
/*     */   public boolean isAppendStatics()
/*     */   {
/* 581 */     return this.appendStatics;
/*     */   }
/*     */ 
/*     */   public boolean isAppendTransients()
/*     */   {
/* 592 */     return this.appendTransients;
/*     */   }
/*     */ 
/*     */   public ReflectionToStringBuilder reflectionAppendArray(Object array)
/*     */   {
/* 605 */     getStyle().reflectionAppendArrayDetail(getStringBuffer(), null, array);
/* 606 */     return this;
/*     */   }
/*     */ 
/*     */   public void setAppendStatics(boolean appendStatics)
/*     */   {
/* 619 */     this.appendStatics = appendStatics;
/*     */   }
/*     */ 
/*     */   public void setAppendTransients(boolean appendTransients)
/*     */   {
/* 631 */     this.appendTransients = appendTransients;
/*     */   }
/*     */ 
/*     */   public ReflectionToStringBuilder setExcludeFieldNames(String[] excludeFieldNamesParam)
/*     */   {
/* 642 */     if (excludeFieldNamesParam == null) {
/* 643 */       this.excludeFieldNames = null;
/*     */     }
/*     */     else {
/* 646 */       this.excludeFieldNames = toNoNullStringArray(excludeFieldNamesParam);
/* 647 */       Arrays.sort(this.excludeFieldNames);
/*     */     }
/* 649 */     return this;
/*     */   }
/*     */ 
/*     */   public void setUpToClass(Class<?> clazz)
/*     */   {
/* 661 */     if (clazz != null) {
/* 662 */       Object object = getObject();
/* 663 */       if ((object != null) && (!clazz.isInstance(object))) {
/* 664 */         throw new IllegalArgumentException("Specified class is not a superclass of the object");
/*     */       }
/*     */     }
/* 667 */     this.upToClass = clazz;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 679 */     if (getObject() == null) {
/* 680 */       return getStyle().getNullText();
/*     */     }
/* 682 */     Class clazz = getObject().getClass();
/* 683 */     appendFieldsIn(clazz);
/* 684 */     while ((clazz.getSuperclass() != null) && (clazz != getUpToClass())) {
/* 685 */       clazz = clazz.getSuperclass();
/* 686 */       appendFieldsIn(clazz);
/*     */     }
/* 688 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.ReflectionToStringBuilder
 * JD-Core Version:    0.6.2
 */