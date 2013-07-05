/*      */ package org.apache.commons.lang3.builder;
/*      */ 
/*      */ import java.lang.reflect.AccessibleObject;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import org.apache.commons.lang3.ArrayUtils;
/*      */ 
/*      */ public class CompareToBuilder
/*      */   implements Builder<Integer>
/*      */ {
/*      */   private int comparison;
/*      */ 
/*      */   public CompareToBuilder()
/*      */   {
/*  104 */     this.comparison = 0;
/*      */   }
/*      */ 
/*      */   public static int reflectionCompare(Object lhs, Object rhs)
/*      */   {
/*  135 */     return reflectionCompare(lhs, rhs, false, null, new String[0]);
/*      */   }
/*      */ 
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients)
/*      */   {
/*  167 */     return reflectionCompare(lhs, rhs, compareTransients, null, new String[0]);
/*      */   }
/*      */ 
/*      */   public static int reflectionCompare(Object lhs, Object rhs, Collection<String> excludeFields)
/*      */   {
/*  200 */     return reflectionCompare(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*      */   }
/*      */ 
/*      */   public static int reflectionCompare(Object lhs, Object rhs, String[] excludeFields)
/*      */   {
/*  233 */     return reflectionCompare(lhs, rhs, false, null, excludeFields);
/*      */   }
/*      */ 
/*      */   public static int reflectionCompare(Object lhs, Object rhs, boolean compareTransients, Class<?> reflectUpToClass, String[] excludeFields)
/*      */   {
/*  275 */     if (lhs == rhs) {
/*  276 */       return 0;
/*      */     }
/*  278 */     if ((lhs == null) || (rhs == null)) {
/*  279 */       throw new NullPointerException();
/*      */     }
/*  281 */     Class lhsClazz = lhs.getClass();
/*  282 */     if (!lhsClazz.isInstance(rhs)) {
/*  283 */       throw new ClassCastException();
/*      */     }
/*  285 */     CompareToBuilder compareToBuilder = new CompareToBuilder();
/*  286 */     reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*  287 */     while ((lhsClazz.getSuperclass() != null) && (lhsClazz != reflectUpToClass)) {
/*  288 */       lhsClazz = lhsClazz.getSuperclass();
/*  289 */       reflectionAppend(lhs, rhs, lhsClazz, compareToBuilder, compareTransients, excludeFields);
/*      */     }
/*  291 */     return compareToBuilder.toComparison();
/*      */   }
/*      */ 
/*      */   private static void reflectionAppend(Object lhs, Object rhs, Class<?> clazz, CompareToBuilder builder, boolean useTransients, String[] excludeFields)
/*      */   {
/*  313 */     Field[] fields = clazz.getDeclaredFields();
/*  314 */     AccessibleObject.setAccessible(fields, true);
/*  315 */     for (int i = 0; (i < fields.length) && (builder.comparison == 0); i++) {
/*  316 */       Field f = fields[i];
/*  317 */       if ((!ArrayUtils.contains(excludeFields, f.getName())) && (f.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(f.getModifiers()))) && (!Modifier.isStatic(f.getModifiers())))
/*      */       {
/*      */         try
/*      */         {
/*  322 */           builder.append(f.get(lhs), f.get(rhs));
/*      */         }
/*      */         catch (IllegalAccessException e)
/*      */         {
/*  326 */           throw new InternalError("Unexpected IllegalAccessException");
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public CompareToBuilder appendSuper(int superCompareTo)
/*      */   {
/*  342 */     if (this.comparison != 0) {
/*  343 */       return this;
/*      */     }
/*  345 */     this.comparison = superCompareTo;
/*  346 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(Object lhs, Object rhs)
/*      */   {
/*  370 */     return append(lhs, rhs, null);
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(Object lhs, Object rhs, Comparator<?> comparator)
/*      */   {
/*  399 */     if (this.comparison != 0) {
/*  400 */       return this;
/*      */     }
/*  402 */     if (lhs == rhs) {
/*  403 */       return this;
/*      */     }
/*  405 */     if (lhs == null) {
/*  406 */       this.comparison = -1;
/*  407 */       return this;
/*      */     }
/*  409 */     if (rhs == null) {
/*  410 */       this.comparison = 1;
/*  411 */       return this;
/*      */     }
/*  413 */     if (lhs.getClass().isArray())
/*      */     {
/*  417 */       if ((lhs instanceof long[]))
/*  418 */         append((long[])lhs, (long[])rhs);
/*  419 */       else if ((lhs instanceof int[]))
/*  420 */         append((int[])lhs, (int[])rhs);
/*  421 */       else if ((lhs instanceof short[]))
/*  422 */         append((short[])lhs, (short[])rhs);
/*  423 */       else if ((lhs instanceof char[]))
/*  424 */         append((char[])lhs, (char[])rhs);
/*  425 */       else if ((lhs instanceof byte[]))
/*  426 */         append((byte[])lhs, (byte[])rhs);
/*  427 */       else if ((lhs instanceof double[]))
/*  428 */         append((double[])lhs, (double[])rhs);
/*  429 */       else if ((lhs instanceof float[]))
/*  430 */         append((float[])lhs, (float[])rhs);
/*  431 */       else if ((lhs instanceof boolean[])) {
/*  432 */         append((boolean[])lhs, (boolean[])rhs);
/*      */       }
/*      */       else
/*      */       {
/*  436 */         append((Object[])lhs, (Object[])rhs, comparator);
/*      */       }
/*      */ 
/*      */     }
/*  440 */     else if (comparator == null)
/*      */     {
/*  442 */       Comparable comparable = (Comparable)lhs;
/*  443 */       this.comparison = comparable.compareTo(rhs);
/*      */     }
/*      */     else {
/*  446 */       Comparator comparator2 = comparator;
/*  447 */       this.comparison = comparator2.compare(lhs, rhs);
/*      */     }
/*      */ 
/*  450 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(long lhs, long rhs)
/*      */   {
/*  463 */     if (this.comparison != 0) {
/*  464 */       return this;
/*      */     }
/*  466 */     this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  467 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(int lhs, int rhs)
/*      */   {
/*  479 */     if (this.comparison != 0) {
/*  480 */       return this;
/*      */     }
/*  482 */     this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  483 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(short lhs, short rhs)
/*      */   {
/*  495 */     if (this.comparison != 0) {
/*  496 */       return this;
/*      */     }
/*  498 */     this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  499 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(char lhs, char rhs)
/*      */   {
/*  511 */     if (this.comparison != 0) {
/*  512 */       return this;
/*      */     }
/*  514 */     this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  515 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(byte lhs, byte rhs)
/*      */   {
/*  527 */     if (this.comparison != 0) {
/*  528 */       return this;
/*      */     }
/*  530 */     this.comparison = (lhs > rhs ? 1 : lhs < rhs ? -1 : 0);
/*  531 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(double lhs, double rhs)
/*      */   {
/*  548 */     if (this.comparison != 0) {
/*  549 */       return this;
/*      */     }
/*  551 */     this.comparison = Double.compare(lhs, rhs);
/*  552 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(float lhs, float rhs)
/*      */   {
/*  569 */     if (this.comparison != 0) {
/*  570 */       return this;
/*      */     }
/*  572 */     this.comparison = Float.compare(lhs, rhs);
/*  573 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(boolean lhs, boolean rhs)
/*      */   {
/*  585 */     if (this.comparison != 0) {
/*  586 */       return this;
/*      */     }
/*  588 */     if (lhs == rhs) {
/*  589 */       return this;
/*      */     }
/*  591 */     if (!lhs)
/*  592 */       this.comparison = -1;
/*      */     else {
/*  594 */       this.comparison = 1;
/*      */     }
/*  596 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(Object[] lhs, Object[] rhs)
/*      */   {
/*  621 */     return append(lhs, rhs, null);
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(Object[] lhs, Object[] rhs, Comparator<?> comparator)
/*      */   {
/*  648 */     if (this.comparison != 0) {
/*  649 */       return this;
/*      */     }
/*  651 */     if (lhs == rhs) {
/*  652 */       return this;
/*      */     }
/*  654 */     if (lhs == null) {
/*  655 */       this.comparison = -1;
/*  656 */       return this;
/*      */     }
/*  658 */     if (rhs == null) {
/*  659 */       this.comparison = 1;
/*  660 */       return this;
/*      */     }
/*  662 */     if (lhs.length != rhs.length) {
/*  663 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  664 */       return this;
/*      */     }
/*  666 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  667 */       append(lhs[i], rhs[i], comparator);
/*      */     }
/*  669 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(long[] lhs, long[] rhs)
/*      */   {
/*  688 */     if (this.comparison != 0) {
/*  689 */       return this;
/*      */     }
/*  691 */     if (lhs == rhs) {
/*  692 */       return this;
/*      */     }
/*  694 */     if (lhs == null) {
/*  695 */       this.comparison = -1;
/*  696 */       return this;
/*      */     }
/*  698 */     if (rhs == null) {
/*  699 */       this.comparison = 1;
/*  700 */       return this;
/*      */     }
/*  702 */     if (lhs.length != rhs.length) {
/*  703 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  704 */       return this;
/*      */     }
/*  706 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  707 */       append(lhs[i], rhs[i]);
/*      */     }
/*  709 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(int[] lhs, int[] rhs)
/*      */   {
/*  728 */     if (this.comparison != 0) {
/*  729 */       return this;
/*      */     }
/*  731 */     if (lhs == rhs) {
/*  732 */       return this;
/*      */     }
/*  734 */     if (lhs == null) {
/*  735 */       this.comparison = -1;
/*  736 */       return this;
/*      */     }
/*  738 */     if (rhs == null) {
/*  739 */       this.comparison = 1;
/*  740 */       return this;
/*      */     }
/*  742 */     if (lhs.length != rhs.length) {
/*  743 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  744 */       return this;
/*      */     }
/*  746 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  747 */       append(lhs[i], rhs[i]);
/*      */     }
/*  749 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(short[] lhs, short[] rhs)
/*      */   {
/*  768 */     if (this.comparison != 0) {
/*  769 */       return this;
/*      */     }
/*  771 */     if (lhs == rhs) {
/*  772 */       return this;
/*      */     }
/*  774 */     if (lhs == null) {
/*  775 */       this.comparison = -1;
/*  776 */       return this;
/*      */     }
/*  778 */     if (rhs == null) {
/*  779 */       this.comparison = 1;
/*  780 */       return this;
/*      */     }
/*  782 */     if (lhs.length != rhs.length) {
/*  783 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  784 */       return this;
/*      */     }
/*  786 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  787 */       append(lhs[i], rhs[i]);
/*      */     }
/*  789 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(char[] lhs, char[] rhs)
/*      */   {
/*  808 */     if (this.comparison != 0) {
/*  809 */       return this;
/*      */     }
/*  811 */     if (lhs == rhs) {
/*  812 */       return this;
/*      */     }
/*  814 */     if (lhs == null) {
/*  815 */       this.comparison = -1;
/*  816 */       return this;
/*      */     }
/*  818 */     if (rhs == null) {
/*  819 */       this.comparison = 1;
/*  820 */       return this;
/*      */     }
/*  822 */     if (lhs.length != rhs.length) {
/*  823 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  824 */       return this;
/*      */     }
/*  826 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  827 */       append(lhs[i], rhs[i]);
/*      */     }
/*  829 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(byte[] lhs, byte[] rhs)
/*      */   {
/*  848 */     if (this.comparison != 0) {
/*  849 */       return this;
/*      */     }
/*  851 */     if (lhs == rhs) {
/*  852 */       return this;
/*      */     }
/*  854 */     if (lhs == null) {
/*  855 */       this.comparison = -1;
/*  856 */       return this;
/*      */     }
/*  858 */     if (rhs == null) {
/*  859 */       this.comparison = 1;
/*  860 */       return this;
/*      */     }
/*  862 */     if (lhs.length != rhs.length) {
/*  863 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  864 */       return this;
/*      */     }
/*  866 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  867 */       append(lhs[i], rhs[i]);
/*      */     }
/*  869 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(double[] lhs, double[] rhs)
/*      */   {
/*  888 */     if (this.comparison != 0) {
/*  889 */       return this;
/*      */     }
/*  891 */     if (lhs == rhs) {
/*  892 */       return this;
/*      */     }
/*  894 */     if (lhs == null) {
/*  895 */       this.comparison = -1;
/*  896 */       return this;
/*      */     }
/*  898 */     if (rhs == null) {
/*  899 */       this.comparison = 1;
/*  900 */       return this;
/*      */     }
/*  902 */     if (lhs.length != rhs.length) {
/*  903 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  904 */       return this;
/*      */     }
/*  906 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  907 */       append(lhs[i], rhs[i]);
/*      */     }
/*  909 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(float[] lhs, float[] rhs)
/*      */   {
/*  928 */     if (this.comparison != 0) {
/*  929 */       return this;
/*      */     }
/*  931 */     if (lhs == rhs) {
/*  932 */       return this;
/*      */     }
/*  934 */     if (lhs == null) {
/*  935 */       this.comparison = -1;
/*  936 */       return this;
/*      */     }
/*  938 */     if (rhs == null) {
/*  939 */       this.comparison = 1;
/*  940 */       return this;
/*      */     }
/*  942 */     if (lhs.length != rhs.length) {
/*  943 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  944 */       return this;
/*      */     }
/*  946 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  947 */       append(lhs[i], rhs[i]);
/*      */     }
/*  949 */     return this;
/*      */   }
/*      */ 
/*      */   public CompareToBuilder append(boolean[] lhs, boolean[] rhs)
/*      */   {
/*  968 */     if (this.comparison != 0) {
/*  969 */       return this;
/*      */     }
/*  971 */     if (lhs == rhs) {
/*  972 */       return this;
/*      */     }
/*  974 */     if (lhs == null) {
/*  975 */       this.comparison = -1;
/*  976 */       return this;
/*      */     }
/*  978 */     if (rhs == null) {
/*  979 */       this.comparison = 1;
/*  980 */       return this;
/*      */     }
/*  982 */     if (lhs.length != rhs.length) {
/*  983 */       this.comparison = (lhs.length < rhs.length ? -1 : 1);
/*  984 */       return this;
/*      */     }
/*  986 */     for (int i = 0; (i < lhs.length) && (this.comparison == 0); i++) {
/*  987 */       append(lhs[i], rhs[i]);
/*      */     }
/*  989 */     return this;
/*      */   }
/*      */ 
/*      */   public int toComparison()
/*      */   {
/* 1003 */     return this.comparison;
/*      */   }
/*      */ 
/*      */   public Integer build()
/*      */   {
/* 1017 */     return Integer.valueOf(toComparison());
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.CompareToBuilder
 * JD-Core Version:    0.6.2
 */