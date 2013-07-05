/*     */ package org.apache.commons.lang3.builder;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ public class EqualsBuilder
/*     */   implements Builder<Boolean>
/*     */ {
/*  92 */   private static final ThreadLocal<Set<Pair<IDKey, IDKey>>> REGISTRY = new ThreadLocal();
/*     */ 
/* 214 */   private boolean isEquals = true;
/*     */ 
/*     */   static Set<Pair<IDKey, IDKey>> getRegistry()
/*     */   {
/* 121 */     return (Set)REGISTRY.get();
/*     */   }
/*     */ 
/*     */   static Pair<IDKey, IDKey> getRegisterPair(Object lhs, Object rhs)
/*     */   {
/* 135 */     IDKey left = new IDKey(lhs);
/* 136 */     IDKey right = new IDKey(rhs);
/* 137 */     return Pair.of(left, right);
/*     */   }
/*     */ 
/*     */   static boolean isRegistered(Object lhs, Object rhs)
/*     */   {
/* 154 */     Set registry = getRegistry();
/* 155 */     Pair pair = getRegisterPair(lhs, rhs);
/* 156 */     Pair swappedPair = Pair.of(pair.getLeft(), pair.getRight());
/*     */ 
/* 158 */     return (registry != null) && ((registry.contains(pair)) || (registry.contains(swappedPair)));
/*     */   }
/*     */ 
/*     */   static void register(Object lhs, Object rhs)
/*     */   {
/* 172 */     synchronized (EqualsBuilder.class) {
/* 173 */       if (getRegistry() == null) {
/* 174 */         REGISTRY.set(new HashSet());
/*     */       }
/*     */     }
/*     */ 
/* 178 */     Set registry = getRegistry();
/* 179 */     Pair pair = getRegisterPair(lhs, rhs);
/* 180 */     registry.add(pair);
/*     */   }
/*     */ 
/*     */   static void unregister(Object lhs, Object rhs)
/*     */   {
/* 196 */     Set registry = getRegistry();
/* 197 */     if (registry != null) {
/* 198 */       Pair pair = getRegisterPair(lhs, rhs);
/* 199 */       registry.remove(pair);
/* 200 */       synchronized (EqualsBuilder.class)
/*     */       {
/* 202 */         registry = getRegistry();
/* 203 */         if ((registry != null) && (registry.isEmpty()))
/* 204 */           REGISTRY.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, Collection<String> excludeFields)
/*     */   {
/* 248 */     return reflectionEquals(lhs, rhs, ReflectionToStringBuilder.toNoNullStringArray(excludeFields));
/*     */   }
/*     */ 
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, String[] excludeFields)
/*     */   {
/* 271 */     return reflectionEquals(lhs, rhs, false, null, excludeFields);
/*     */   }
/*     */ 
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients)
/*     */   {
/* 295 */     return reflectionEquals(lhs, rhs, testTransients, null, new String[0]);
/*     */   }
/*     */ 
/*     */   public static boolean reflectionEquals(Object lhs, Object rhs, boolean testTransients, Class<?> reflectUpToClass, String[] excludeFields)
/*     */   {
/* 326 */     if (lhs == rhs) {
/* 327 */       return true;
/*     */     }
/* 329 */     if ((lhs == null) || (rhs == null)) {
/* 330 */       return false;
/*     */     }
/*     */ 
/* 336 */     Class lhsClass = lhs.getClass();
/* 337 */     Class rhsClass = rhs.getClass();
/*     */ 
/* 339 */     if (lhsClass.isInstance(rhs)) {
/* 340 */       Class testClass = lhsClass;
/* 341 */       if (!rhsClass.isInstance(lhs))
/*     */       {
/* 343 */         testClass = rhsClass;
/*     */       }
/* 345 */     } else if (rhsClass.isInstance(lhs)) {
/* 346 */       Class testClass = rhsClass;
/* 347 */       if (!lhsClass.isInstance(rhs))
/*     */       {
/* 349 */         testClass = lhsClass;
/*     */       }
/*     */     }
/*     */     else {
/* 353 */       return false;
/*     */     }
/*     */     Class testClass;
/* 355 */     EqualsBuilder equalsBuilder = new EqualsBuilder();
/*     */     try {
/* 357 */       reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
/* 358 */       while ((testClass.getSuperclass() != null) && (testClass != reflectUpToClass)) {
/* 359 */         testClass = testClass.getSuperclass();
/* 360 */         reflectionAppend(lhs, rhs, testClass, equalsBuilder, testTransients, excludeFields);
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (IllegalArgumentException e)
/*     */     {
/* 368 */       return false;
/*     */     }
/* 370 */     return equalsBuilder.isEquals();
/*     */   }
/*     */ 
/*     */   private static void reflectionAppend(Object lhs, Object rhs, Class<?> clazz, EqualsBuilder builder, boolean useTransients, String[] excludeFields)
/*     */   {
/* 392 */     if (isRegistered(lhs, rhs)) {
/* 393 */       return;
/*     */     }
/*     */     try
/*     */     {
/* 397 */       register(lhs, rhs);
/* 398 */       Field[] fields = clazz.getDeclaredFields();
/* 399 */       AccessibleObject.setAccessible(fields, true);
/* 400 */       for (int i = 0; (i < fields.length) && (builder.isEquals); i++) {
/* 401 */         Field f = fields[i];
/* 402 */         if ((!ArrayUtils.contains(excludeFields, f.getName())) && (f.getName().indexOf('$') == -1) && ((useTransients) || (!Modifier.isTransient(f.getModifiers()))) && (!Modifier.isStatic(f.getModifiers())))
/*     */         {
/*     */           try
/*     */           {
/* 407 */             builder.append(f.get(lhs), f.get(rhs));
/*     */           }
/*     */           catch (IllegalAccessException e)
/*     */           {
/* 411 */             throw new InternalError("Unexpected IllegalAccessException");
/*     */           }
/*     */         }
/*     */       }
/*     */     } finally {
/* 416 */       unregister(lhs, rhs);
/*     */     }
/*     */   }
/*     */ 
/*     */   public EqualsBuilder appendSuper(boolean superEquals)
/*     */   {
/* 430 */     if (!this.isEquals) {
/* 431 */       return this;
/*     */     }
/* 433 */     this.isEquals = superEquals;
/* 434 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(Object lhs, Object rhs)
/*     */   {
/* 448 */     if (!this.isEquals) {
/* 449 */       return this;
/*     */     }
/* 451 */     if (lhs == rhs) {
/* 452 */       return this;
/*     */     }
/* 454 */     if ((lhs == null) || (rhs == null)) {
/* 455 */       setEquals(false);
/* 456 */       return this;
/*     */     }
/* 458 */     Class lhsClass = lhs.getClass();
/* 459 */     if (!lhsClass.isArray())
/*     */     {
/* 461 */       this.isEquals = lhs.equals(rhs);
/* 462 */     } else if (lhs.getClass() != rhs.getClass())
/*     */     {
/* 464 */       setEquals(false);
/*     */     }
/* 468 */     else if ((lhs instanceof long[]))
/* 469 */       append((long[])lhs, (long[])rhs);
/* 470 */     else if ((lhs instanceof int[]))
/* 471 */       append((int[])lhs, (int[])rhs);
/* 472 */     else if ((lhs instanceof short[]))
/* 473 */       append((short[])lhs, (short[])rhs);
/* 474 */     else if ((lhs instanceof char[]))
/* 475 */       append((char[])lhs, (char[])rhs);
/* 476 */     else if ((lhs instanceof byte[]))
/* 477 */       append((byte[])lhs, (byte[])rhs);
/* 478 */     else if ((lhs instanceof double[]))
/* 479 */       append((double[])lhs, (double[])rhs);
/* 480 */     else if ((lhs instanceof float[]))
/* 481 */       append((float[])lhs, (float[])rhs);
/* 482 */     else if ((lhs instanceof boolean[])) {
/* 483 */       append((boolean[])lhs, (boolean[])rhs);
/*     */     }
/*     */     else {
/* 486 */       append((Object[])lhs, (Object[])rhs);
/*     */     }
/* 488 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(long lhs, long rhs)
/*     */   {
/* 503 */     if (!this.isEquals) {
/* 504 */       return this;
/*     */     }
/* 506 */     this.isEquals = (lhs == rhs);
/* 507 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(int lhs, int rhs)
/*     */   {
/* 518 */     if (!this.isEquals) {
/* 519 */       return this;
/*     */     }
/* 521 */     this.isEquals = (lhs == rhs);
/* 522 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(short lhs, short rhs)
/*     */   {
/* 533 */     if (!this.isEquals) {
/* 534 */       return this;
/*     */     }
/* 536 */     this.isEquals = (lhs == rhs);
/* 537 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(char lhs, char rhs)
/*     */   {
/* 548 */     if (!this.isEquals) {
/* 549 */       return this;
/*     */     }
/* 551 */     this.isEquals = (lhs == rhs);
/* 552 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(byte lhs, byte rhs)
/*     */   {
/* 563 */     if (!this.isEquals) {
/* 564 */       return this;
/*     */     }
/* 566 */     this.isEquals = (lhs == rhs);
/* 567 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(double lhs, double rhs)
/*     */   {
/* 584 */     if (!this.isEquals) {
/* 585 */       return this;
/*     */     }
/* 587 */     return append(Double.doubleToLongBits(lhs), Double.doubleToLongBits(rhs));
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(float lhs, float rhs)
/*     */   {
/* 604 */     if (!this.isEquals) {
/* 605 */       return this;
/*     */     }
/* 607 */     return append(Float.floatToIntBits(lhs), Float.floatToIntBits(rhs));
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(boolean lhs, boolean rhs)
/*     */   {
/* 618 */     if (!this.isEquals) {
/* 619 */       return this;
/*     */     }
/* 621 */     this.isEquals = (lhs == rhs);
/* 622 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(Object[] lhs, Object[] rhs)
/*     */   {
/* 636 */     if (!this.isEquals) {
/* 637 */       return this;
/*     */     }
/* 639 */     if (lhs == rhs) {
/* 640 */       return this;
/*     */     }
/* 642 */     if ((lhs == null) || (rhs == null)) {
/* 643 */       setEquals(false);
/* 644 */       return this;
/*     */     }
/* 646 */     if (lhs.length != rhs.length) {
/* 647 */       setEquals(false);
/* 648 */       return this;
/*     */     }
/* 650 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 651 */       append(lhs[i], rhs[i]);
/*     */     }
/* 653 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(long[] lhs, long[] rhs)
/*     */   {
/* 667 */     if (!this.isEquals) {
/* 668 */       return this;
/*     */     }
/* 670 */     if (lhs == rhs) {
/* 671 */       return this;
/*     */     }
/* 673 */     if ((lhs == null) || (rhs == null)) {
/* 674 */       setEquals(false);
/* 675 */       return this;
/*     */     }
/* 677 */     if (lhs.length != rhs.length) {
/* 678 */       setEquals(false);
/* 679 */       return this;
/*     */     }
/* 681 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 682 */       append(lhs[i], rhs[i]);
/*     */     }
/* 684 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(int[] lhs, int[] rhs)
/*     */   {
/* 698 */     if (!this.isEquals) {
/* 699 */       return this;
/*     */     }
/* 701 */     if (lhs == rhs) {
/* 702 */       return this;
/*     */     }
/* 704 */     if ((lhs == null) || (rhs == null)) {
/* 705 */       setEquals(false);
/* 706 */       return this;
/*     */     }
/* 708 */     if (lhs.length != rhs.length) {
/* 709 */       setEquals(false);
/* 710 */       return this;
/*     */     }
/* 712 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 713 */       append(lhs[i], rhs[i]);
/*     */     }
/* 715 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(short[] lhs, short[] rhs)
/*     */   {
/* 729 */     if (!this.isEquals) {
/* 730 */       return this;
/*     */     }
/* 732 */     if (lhs == rhs) {
/* 733 */       return this;
/*     */     }
/* 735 */     if ((lhs == null) || (rhs == null)) {
/* 736 */       setEquals(false);
/* 737 */       return this;
/*     */     }
/* 739 */     if (lhs.length != rhs.length) {
/* 740 */       setEquals(false);
/* 741 */       return this;
/*     */     }
/* 743 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 744 */       append(lhs[i], rhs[i]);
/*     */     }
/* 746 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(char[] lhs, char[] rhs)
/*     */   {
/* 760 */     if (!this.isEquals) {
/* 761 */       return this;
/*     */     }
/* 763 */     if (lhs == rhs) {
/* 764 */       return this;
/*     */     }
/* 766 */     if ((lhs == null) || (rhs == null)) {
/* 767 */       setEquals(false);
/* 768 */       return this;
/*     */     }
/* 770 */     if (lhs.length != rhs.length) {
/* 771 */       setEquals(false);
/* 772 */       return this;
/*     */     }
/* 774 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 775 */       append(lhs[i], rhs[i]);
/*     */     }
/* 777 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(byte[] lhs, byte[] rhs)
/*     */   {
/* 791 */     if (!this.isEquals) {
/* 792 */       return this;
/*     */     }
/* 794 */     if (lhs == rhs) {
/* 795 */       return this;
/*     */     }
/* 797 */     if ((lhs == null) || (rhs == null)) {
/* 798 */       setEquals(false);
/* 799 */       return this;
/*     */     }
/* 801 */     if (lhs.length != rhs.length) {
/* 802 */       setEquals(false);
/* 803 */       return this;
/*     */     }
/* 805 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 806 */       append(lhs[i], rhs[i]);
/*     */     }
/* 808 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(double[] lhs, double[] rhs)
/*     */   {
/* 822 */     if (!this.isEquals) {
/* 823 */       return this;
/*     */     }
/* 825 */     if (lhs == rhs) {
/* 826 */       return this;
/*     */     }
/* 828 */     if ((lhs == null) || (rhs == null)) {
/* 829 */       setEquals(false);
/* 830 */       return this;
/*     */     }
/* 832 */     if (lhs.length != rhs.length) {
/* 833 */       setEquals(false);
/* 834 */       return this;
/*     */     }
/* 836 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 837 */       append(lhs[i], rhs[i]);
/*     */     }
/* 839 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(float[] lhs, float[] rhs)
/*     */   {
/* 853 */     if (!this.isEquals) {
/* 854 */       return this;
/*     */     }
/* 856 */     if (lhs == rhs) {
/* 857 */       return this;
/*     */     }
/* 859 */     if ((lhs == null) || (rhs == null)) {
/* 860 */       setEquals(false);
/* 861 */       return this;
/*     */     }
/* 863 */     if (lhs.length != rhs.length) {
/* 864 */       setEquals(false);
/* 865 */       return this;
/*     */     }
/* 867 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 868 */       append(lhs[i], rhs[i]);
/*     */     }
/* 870 */     return this;
/*     */   }
/*     */ 
/*     */   public EqualsBuilder append(boolean[] lhs, boolean[] rhs)
/*     */   {
/* 884 */     if (!this.isEquals) {
/* 885 */       return this;
/*     */     }
/* 887 */     if (lhs == rhs) {
/* 888 */       return this;
/*     */     }
/* 890 */     if ((lhs == null) || (rhs == null)) {
/* 891 */       setEquals(false);
/* 892 */       return this;
/*     */     }
/* 894 */     if (lhs.length != rhs.length) {
/* 895 */       setEquals(false);
/* 896 */       return this;
/*     */     }
/* 898 */     for (int i = 0; (i < lhs.length) && (this.isEquals); i++) {
/* 899 */       append(lhs[i], rhs[i]);
/*     */     }
/* 901 */     return this;
/*     */   }
/*     */ 
/*     */   public boolean isEquals()
/*     */   {
/* 911 */     return this.isEquals;
/*     */   }
/*     */ 
/*     */   public Boolean build()
/*     */   {
/* 924 */     return Boolean.valueOf(isEquals());
/*     */   }
/*     */ 
/*     */   protected void setEquals(boolean isEquals)
/*     */   {
/* 934 */     this.isEquals = isEquals;
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 942 */     this.isEquals = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.builder.EqualsBuilder
 * JD-Core Version:    0.6.2
 */