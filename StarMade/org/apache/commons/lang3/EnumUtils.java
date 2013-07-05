/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EnumUtils
/*     */ {
/*     */   public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass)
/*     */   {
/*  53 */     Map map = new LinkedHashMap();
/*  54 */     for (Enum e : (Enum[])enumClass.getEnumConstants()) {
/*  55 */       map.put(e.name(), e);
/*     */     }
/*  57 */     return map;
/*     */   }
/*     */ 
/*     */   public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass)
/*     */   {
/*  70 */     return new ArrayList(Arrays.asList(enumClass.getEnumConstants()));
/*     */   }
/*     */ 
/*     */   public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName)
/*     */   {
/*  85 */     if (enumName == null)
/*  86 */       return false;
/*     */     try
/*     */     {
/*  89 */       Enum.valueOf(enumClass, enumName);
/*  90 */       return true; } catch (IllegalArgumentException ex) {
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static <E extends Enum<E>> E getEnum(Class<E> enumClass, String enumName)
/*     */   {
/* 108 */     if (enumName == null)
/* 109 */       return null;
/*     */     try
/*     */     {
/* 112 */       return Enum.valueOf(enumClass, enumName); } catch (IllegalArgumentException ex) {
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, Iterable<E> values)
/*     */   {
/* 135 */     checkBitVectorable(enumClass);
/* 136 */     Validate.notNull(values);
/* 137 */     long total = 0L;
/* 138 */     for (Enum constant : values) {
/* 139 */       total |= 1 << constant.ordinal();
/*     */     }
/* 141 */     return total;
/*     */   }
/*     */ 
/*     */   public static <E extends Enum<E>> long generateBitVector(Class<E> enumClass, E[] values)
/*     */   {
/* 161 */     Validate.noNullElements(values);
/* 162 */     return generateBitVector(enumClass, Arrays.asList(values));
/*     */   }
/*     */ 
/*     */   public static <E extends Enum<E>> EnumSet<E> processBitVector(Class<E> enumClass, long value)
/*     */   {
/* 179 */     Enum[] constants = (Enum[])checkBitVectorable(enumClass).getEnumConstants();
/* 180 */     EnumSet results = EnumSet.noneOf(enumClass);
/* 181 */     for (Enum constant : constants) {
/* 182 */       if ((value & 1 << constant.ordinal()) != 0L) {
/* 183 */         results.add(constant);
/*     */       }
/*     */     }
/* 186 */     return results;
/*     */   }
/*     */ 
/*     */   private static <E extends Enum<E>> Class<E> checkBitVectorable(Class<E> enumClass)
/*     */   {
/* 199 */     Validate.notNull(enumClass, "EnumClass must be defined.", new Object[0]);
/*     */ 
/* 201 */     Enum[] constants = (Enum[])enumClass.getEnumConstants();
/* 202 */     Validate.isTrue(constants != null, "%s does not seem to be an Enum type", new Object[] { enumClass });
/* 203 */     Validate.isTrue(constants.length <= 64, "Cannot store %s %s values in %s bits", new Object[] { Integer.valueOf(constants.length), enumClass.getSimpleName(), Integer.valueOf(64) });
/*     */ 
/* 206 */     return enumClass;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.EnumUtils
 * JD-Core Version:    0.6.2
 */