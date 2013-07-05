/*     */ package org.apache.commons.lang3.builder;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ 
/*     */ public class HashCodeBuilder
/*     */   implements Builder<Integer>
/*     */ {
/* 108 */   private static final ThreadLocal<Set<IDKey>> REGISTRY = new ThreadLocal();
/*     */   private final int iConstant;
/* 509 */   private int iTotal = 0;
/*     */ 
/*     */   static Set<IDKey> getRegistry()
/*     */   {
/* 136 */     return (Set)REGISTRY.get();
/*     */   }
/*     */ 
/*     */   static boolean isRegistered(Object value)
/*     */   {
/* 151 */     Set registry = getRegistry();
/* 152 */     return (registry != null) && (registry.contains(new IDKey(value)));
/*     */   }
/*     */ 
/*     */   private static void reflectionAppend(Object object, Class<?> clazz, HashCodeBuilder builder, boolean useTransients, String[] excludeFields)
/*     */   {
/* 173 */     if (isRegistered(object))
/* 174 */       return;
/*     */     try
/*     */     {
/* 177 */       register(object);
/* 178 */       Field[] fields = clazz.getDeclaredFields();
/* 179 */       AccessibleObject.setAccessible(fields, true);
/* 180 */       for (Field field : fields)
/* 181 */         if ((!ArrayUtils.contains(excludeFields, field.getName())) && (field.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(field.getModifiers()))) && (!Modifier.isStatic(field.getModifiers())))
/*     */         {
/*     */           try
/*     */           {
/* 186 */             Object fieldValue = field.get(object);
/* 187 */             builder.append(fieldValue);
/*     */           }
/*     */           catch (IllegalAccessException e)
/*     */           {
/* 191 */             throw new InternalError("Unexpected IllegalAccessException");
/*     */           }
/*     */         }
/*     */     }
/*     */     finally {
/* 196 */       unregister(object);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object)
/*     */   {
/* 238 */     return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, false, null, new String[0]);
/*     */   }
/*     */ 
/*     */   public static int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, Object object, boolean testTransients)
/*     */   {
/* 282 */     return reflectionHashCode(initialNonZeroOddNumber, multiplierNonZeroOddNumber, object, testTransients, null, new String[0]);
/*     */   }
/*     */ 
/*     */   public static <T> int reflectionHashCode(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber, T object, boolean testTransients, Class<? super T> reflectUpToClass, String[] excludeFields)
/*     */   {
/* 335 */     if (object == null) {
/* 336 */       throw new IllegalArgumentException("The object to build a hash code for must not be null");
/*     */     }
/* 338 */     HashCodeBuilder builder = new HashCodeBuilder(initialNonZeroOddNumber, multiplierNonZeroOddNumber);
/* 339 */     Class clazz = object.getClass();
/* 340 */     reflectionAppend(object, clazz, builder, testTransients, excludeFields);
/* 341 */     while ((clazz.getSuperclass() != null) && (clazz != reflectUpToClass)) {
/* 342 */       clazz = clazz.getSuperclass();
/* 343 */       reflectionAppend(object, clazz, builder, testTransients, excludeFields);
/*     */     }
/* 345 */     return builder.toHashCode();
/*     */   }
/*     */ 
/*     */   public static int reflectionHashCode(Object object, boolean testTransients)
/*     */   {
/* 381 */     return reflectionHashCode(17, 37, object, testTransients, null, new String[0]);
/*     */   }
/*     */ 
/*     */   public static int reflectionHashCode(Object object, Collection<String> excludeFields)
/*     */   {
/* 417 */     return reflectionHashCode(object, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*     */   }
/*     */ 
/*     */   public static int reflectionHashCode(Object object, String[] excludeFields)
/*     */   {
/* 455 */     return reflectionHashCode(17, 37, object, false, null, excludeFields);
/*     */   }
/*     */ 
/*     */   static void register(Object value)
/*     */   {
/* 467 */     synchronized (HashCodeBuilder.class) {
/* 468 */       if (getRegistry() == null) {
/* 469 */         REGISTRY.set(new HashSet());
/*     */       }
/*     */     }
/* 472 */     getRegistry().add(new IDKey(value));
/*     */   }
/*     */ 
/*     */   static void unregister(Object value)
/*     */   {
/* 488 */     Set registry = getRegistry();
/* 489 */     if (registry != null) {
/* 490 */       registry.remove(new IDKey(value));
/* 491 */       synchronized (HashCodeBuilder.class)
/*     */       {
/* 493 */         registry = getRegistry();
/* 494 */         if ((registry != null) && (registry.isEmpty()))
/* 495 */           REGISTRY.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder()
/*     */   {
/* 517 */     this.iConstant = 37;
/* 518 */     this.iTotal = 17;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder(int initialNonZeroOddNumber, int multiplierNonZeroOddNumber)
/*     */   {
/* 539 */     if (initialNonZeroOddNumber == 0) {
/* 540 */       throw new IllegalArgumentException("HashCodeBuilder requires a non zero initial value");
/*     */     }
/* 542 */     if (initialNonZeroOddNumber % 2 == 0) {
/* 543 */       throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value");
/*     */     }
/* 545 */     if (multiplierNonZeroOddNumber == 0) {
/* 546 */       throw new IllegalArgumentException("HashCodeBuilder requires a non zero multiplier");
/*     */     }
/* 548 */     if (multiplierNonZeroOddNumber % 2 == 0) {
/* 549 */       throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier");
/*     */     }
/* 551 */     this.iConstant = multiplierNonZeroOddNumber;
/* 552 */     this.iTotal = initialNonZeroOddNumber;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(boolean value)
/*     */   {
/* 577 */     this.iTotal = (this.iTotal * this.iConstant + (value ? 0 : 1));
/* 578 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(boolean[] array)
/*     */   {
/* 591 */     if (array == null)
/* 592 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 594 */       for (boolean element : array) {
/* 595 */         append(element);
/*     */       }
/*     */     }
/* 598 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(byte value)
/*     */   {
/* 613 */     this.iTotal = (this.iTotal * this.iConstant + value);
/* 614 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(byte[] array)
/*     */   {
/* 629 */     if (array == null)
/* 630 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 632 */       for (byte element : array) {
/* 633 */         append(element);
/*     */       }
/*     */     }
/* 636 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(char value)
/*     */   {
/* 649 */     this.iTotal = (this.iTotal * this.iConstant + value);
/* 650 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(char[] array)
/*     */   {
/* 663 */     if (array == null)
/* 664 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 666 */       for (char element : array) {
/* 667 */         append(element);
/*     */       }
/*     */     }
/* 670 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(double value)
/*     */   {
/* 683 */     return append(Double.doubleToLongBits(value));
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(double[] array)
/*     */   {
/* 696 */     if (array == null)
/* 697 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 699 */       for (double element : array) {
/* 700 */         append(element);
/*     */       }
/*     */     }
/* 703 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(float value)
/*     */   {
/* 716 */     this.iTotal = (this.iTotal * this.iConstant + Float.floatToIntBits(value));
/* 717 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(float[] array)
/*     */   {
/* 730 */     if (array == null)
/* 731 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 733 */       for (float element : array) {
/* 734 */         append(element);
/*     */       }
/*     */     }
/* 737 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(int value)
/*     */   {
/* 750 */     this.iTotal = (this.iTotal * this.iConstant + value);
/* 751 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(int[] array)
/*     */   {
/* 764 */     if (array == null)
/* 765 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 767 */       for (int element : array) {
/* 768 */         append(element);
/*     */       }
/*     */     }
/* 771 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(long value)
/*     */   {
/* 788 */     this.iTotal = (this.iTotal * this.iConstant + (int)(value ^ value >> 32));
/* 789 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(long[] array)
/*     */   {
/* 802 */     if (array == null)
/* 803 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 805 */       for (long element : array) {
/* 806 */         append(element);
/*     */       }
/*     */     }
/* 809 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(Object object)
/*     */   {
/* 822 */     if (object == null) {
/* 823 */       this.iTotal *= this.iConstant;
/*     */     }
/* 826 */     else if (object.getClass().isArray())
/*     */     {
/* 829 */       if ((object instanceof long[]))
/* 830 */         append((long[])object);
/* 831 */       else if ((object instanceof int[]))
/* 832 */         append((int[])object);
/* 833 */       else if ((object instanceof short[]))
/* 834 */         append((short[])object);
/* 835 */       else if ((object instanceof char[]))
/* 836 */         append((char[])object);
/* 837 */       else if ((object instanceof byte[]))
/* 838 */         append((byte[])object);
/* 839 */       else if ((object instanceof double[]))
/* 840 */         append((double[])object);
/* 841 */       else if ((object instanceof float[]))
/* 842 */         append((float[])object);
/* 843 */       else if ((object instanceof boolean[])) {
/* 844 */         append((boolean[])object);
/*     */       }
/*     */       else
/* 847 */         append((Object[])object);
/*     */     }
/*     */     else {
/* 850 */       this.iTotal = (this.iTotal * this.iConstant + object.hashCode());
/*     */     }
/*     */ 
/* 853 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(Object[] array)
/*     */   {
/* 866 */     if (array == null)
/* 867 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 869 */       for (Object element : array) {
/* 870 */         append(element);
/*     */       }
/*     */     }
/* 873 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(short value)
/*     */   {
/* 886 */     this.iTotal = (this.iTotal * this.iConstant + value);
/* 887 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder append(short[] array)
/*     */   {
/* 900 */     if (array == null)
/* 901 */       this.iTotal *= this.iConstant;
/*     */     else {
/* 903 */       for (short element : array) {
/* 904 */         append(element);
/*     */       }
/*     */     }
/* 907 */     return this;
/*     */   }
/*     */ 
/*     */   public HashCodeBuilder appendSuper(int superHashCode)
/*     */   {
/* 921 */     this.iTotal = (this.iTotal * this.iConstant + superHashCode);
/* 922 */     return this;
/*     */   }
/*     */ 
/*     */   public int toHashCode()
/*     */   {
/* 933 */     return this.iTotal;
/*     */   }
/*     */ 
/*     */   public Integer build()
/*     */   {
/* 944 */     return Integer.valueOf(toHashCode());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 958 */     return toHashCode();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.HashCodeBuilder
 * JD-Core Version:    0.6.2
 */