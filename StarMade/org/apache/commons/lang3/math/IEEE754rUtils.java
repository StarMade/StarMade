/*     */ package org.apache.commons.lang3.math;
/*     */ 
/*     */ public class IEEE754rUtils
/*     */ {
/*     */   public static double min(double[] array)
/*     */   {
/*  39 */     if (array == null)
/*  40 */       throw new IllegalArgumentException("The Array must not be null");
/*  41 */     if (array.length == 0) {
/*  42 */       throw new IllegalArgumentException("Array cannot be empty.");
/*     */     }
/*     */ 
/*  46 */     double min = array[0];
/*  47 */     for (int i = 1; i < array.length; i++) {
/*  48 */       min = min(array[i], min);
/*     */     }
/*     */ 
/*  51 */     return min;
/*     */   }
/*     */ 
/*     */   public static float min(float[] array)
/*     */   {
/*  64 */     if (array == null)
/*  65 */       throw new IllegalArgumentException("The Array must not be null");
/*  66 */     if (array.length == 0) {
/*  67 */       throw new IllegalArgumentException("Array cannot be empty.");
/*     */     }
/*     */ 
/*  71 */     float min = array[0];
/*  72 */     for (int i = 1; i < array.length; i++) {
/*  73 */       min = min(array[i], min);
/*     */     }
/*     */ 
/*  76 */     return min;
/*     */   }
/*     */ 
/*     */   public static double min(double a, double b, double c)
/*     */   {
/*  90 */     return min(min(a, b), c);
/*     */   }
/*     */ 
/*     */   public static double min(double a, double b)
/*     */   {
/* 103 */     if (Double.isNaN(a)) {
/* 104 */       return b;
/*     */     }
/* 106 */     if (Double.isNaN(b)) {
/* 107 */       return a;
/*     */     }
/* 109 */     return Math.min(a, b);
/*     */   }
/*     */ 
/*     */   public static float min(float a, float b, float c)
/*     */   {
/* 124 */     return min(min(a, b), c);
/*     */   }
/*     */ 
/*     */   public static float min(float a, float b)
/*     */   {
/* 137 */     if (Float.isNaN(a)) {
/* 138 */       return b;
/*     */     }
/* 140 */     if (Float.isNaN(b)) {
/* 141 */       return a;
/*     */     }
/* 143 */     return Math.min(a, b);
/*     */   }
/*     */ 
/*     */   public static double max(double[] array)
/*     */   {
/* 157 */     if (array == null)
/* 158 */       throw new IllegalArgumentException("The Array must not be null");
/* 159 */     if (array.length == 0) {
/* 160 */       throw new IllegalArgumentException("Array cannot be empty.");
/*     */     }
/*     */ 
/* 164 */     double max = array[0];
/* 165 */     for (int j = 1; j < array.length; j++) {
/* 166 */       max = max(array[j], max);
/*     */     }
/*     */ 
/* 169 */     return max;
/*     */   }
/*     */ 
/*     */   public static float max(float[] array)
/*     */   {
/* 182 */     if (array == null)
/* 183 */       throw new IllegalArgumentException("The Array must not be null");
/* 184 */     if (array.length == 0) {
/* 185 */       throw new IllegalArgumentException("Array cannot be empty.");
/*     */     }
/*     */ 
/* 189 */     float max = array[0];
/* 190 */     for (int j = 1; j < array.length; j++) {
/* 191 */       max = max(array[j], max);
/*     */     }
/*     */ 
/* 194 */     return max;
/*     */   }
/*     */ 
/*     */   public static double max(double a, double b, double c)
/*     */   {
/* 208 */     return max(max(a, b), c);
/*     */   }
/*     */ 
/*     */   public static double max(double a, double b)
/*     */   {
/* 221 */     if (Double.isNaN(a)) {
/* 222 */       return b;
/*     */     }
/* 224 */     if (Double.isNaN(b)) {
/* 225 */       return a;
/*     */     }
/* 227 */     return Math.max(a, b);
/*     */   }
/*     */ 
/*     */   public static float max(float a, float b, float c)
/*     */   {
/* 242 */     return max(max(a, b), c);
/*     */   }
/*     */ 
/*     */   public static float max(float a, float b)
/*     */   {
/* 255 */     if (Float.isNaN(a)) {
/* 256 */       return b;
/*     */     }
/* 258 */     if (Float.isNaN(b)) {
/* 259 */       return a;
/*     */     }
/* 261 */     return Math.max(a, b);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.math.IEEE754rUtils
 * JD-Core Version:    0.6.2
 */