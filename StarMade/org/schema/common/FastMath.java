/*     */ package org.schema.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Random;
/*     */ 
/*     */ public final class FastMath
/*     */ {
/*  53 */   private static float[] jdField_a_of_type_ArrayOfFloat = new float[65536];
/*     */   private static float jdField_a_of_type_Float;
/*     */   public static final Random a;
/*     */   private static final float[] jdField_b_of_type_ArrayOfFloat;
/*     */   private static final float[] c;
/*     */   private static final int jdField_a_of_type_Int;
/*     */   private static final float jdField_b_of_type_Float;
/*     */   private static final float[] d;
/*     */ 
/*     */   public static float a(float paramFloat)
/*     */   {
/* 132 */     if (paramFloat < 0.0F) {
/* 133 */       return -paramFloat;
/*     */     }
/* 135 */     return paramFloat;
/*     */   }
/*     */   public static short a(short paramShort) {
/* 138 */     if (paramShort < 0) return (short)-paramShort; return paramShort;
/*     */   }
/*     */ 
/*     */   public static float b(float paramFloat)
/*     */   {
/* 151 */     if (-1.0F < paramFloat) {
/* 152 */       if (paramFloat < 1.0F) {
/* 153 */         return (float)Math.acos(paramFloat);
/*     */       }
/*     */ 
/* 156 */       return 0.0F;
/*     */     }
/*     */ 
/* 159 */     return 3.141593F;
/*     */   }
/*     */ 
/*     */   public static double a(double paramDouble)
/*     */   {
/* 172 */     return (paramDouble * -0.6981317007977321D * paramDouble - 0.8726646259971648D) * paramDouble + 1.570796326794897D;
/*     */   }
/*     */ 
/*     */   public static final float a(float paramFloat1, float paramFloat2)
/*     */   {
/*     */     float f2;
/*     */     float f1;
/* 179 */     if (paramFloat2 < 0.0F)
/*     */     {
/* 181 */       if (paramFloat1 < 0.0F)
/*     */       {
/* 183 */         paramFloat2 = -paramFloat2;
/* 184 */         paramFloat1 = -paramFloat1;
/*     */ 
/* 186 */         f2 = 1.0F;
/*     */       }
/*     */       else
/*     */       {
/* 190 */         paramFloat2 = -paramFloat2;
/* 191 */         f2 = -1.0F;
/*     */       }
/*     */ 
/* 194 */       f1 = -3.141593F;
/*     */     }
/*     */     else
/*     */     {
/* 198 */       if (paramFloat1 < 0.0F)
/*     */       {
/* 200 */         paramFloat1 = -paramFloat1;
/* 201 */         f2 = -1.0F;
/*     */       }
/*     */       else
/*     */       {
/* 205 */         f2 = 1.0F;
/*     */       }
/*     */ 
/* 208 */       f1 = 0.0F;
/*     */     }
/*     */ 
/* 211 */     float f3 = jdField_b_of_type_Float / (paramFloat2 < paramFloat1 ? paramFloat1 : paramFloat2);
/*     */ 
/* 213 */     paramFloat2 = (int)(paramFloat2 * f3);
/* 214 */     paramFloat1 = (int)(paramFloat1 * f3);
/*     */ 
/* 216 */     return (d[(paramFloat1 * jdField_a_of_type_Int + paramFloat2)] + f1) * f2;
/*     */   }
/*     */ 
/*     */   public static float c(float paramFloat)
/*     */   {
/* 340 */     return (float)Math.ceil(paramFloat);
/*     */   }
/*     */ 
/*     */   public static byte a(byte paramByte)
/*     */   {
/* 352 */     if (paramByte < 0) return 0; if (paramByte > 7) return 7; return paramByte;
/*     */   }
/*     */ 
/*     */   public static float a(float paramFloat1, float paramFloat2, float paramFloat3)
/*     */   {
/* 364 */     if (paramFloat1 < paramFloat2) return paramFloat2; if (paramFloat1 > paramFloat3) return paramFloat3; return paramFloat1;
/*     */   }
/*     */ 
/*     */   public static float d(float paramFloat)
/*     */   {
/* 394 */     return h(paramFloat + 1.570796F);
/*     */   }
/*     */ 
/*     */   public static final float e(float paramFloat)
/*     */   {
/* 402 */     return jdField_a_of_type_ArrayOfFloat[((int)(paramFloat * 10430.38F + 16384.0F) & 0xFFFF)];
/*     */   }
/*     */ 
/*     */   public static int a(int paramInt1, int paramInt2)
/*     */   {
/* 446 */     if (paramInt2 == 0) {
/* 447 */       return 0;
/*     */     }
/* 449 */     if (paramInt1 < 0) {
/* 450 */       return Math.abs(paramInt1 + 1) % paramInt2;
/*     */     }
/* 452 */     return paramInt1 % paramInt2;
/*     */   }
/*     */ 
/*     */   public static int b(int paramInt1, int paramInt2)
/*     */   {
/* 479 */     if (paramInt2 == 0) {
/* 480 */       return 0;
/*     */     }
/* 482 */     if (paramInt1 < 0) {
/* 483 */       return paramInt2 - 1 - Math.abs(paramInt1 + 1) % paramInt2;
/*     */     }
/* 485 */     return paramInt1 % paramInt2;
/*     */   }
/*     */ 
/*     */   public static float f(float paramFloat)
/*     */   {
/* 516 */     return (float)Math.exp(paramFloat);
/*     */   }
/*     */ 
/*     */   public static boolean a(int paramInt)
/*     */   {
/* 553 */     return (paramInt > 0) && ((paramInt & paramInt - 1) == 0);
/*     */   }
/*     */ 
/*     */   public static float g(float paramFloat)
/*     */   {
/* 575 */     return (float)Math.log(paramFloat) / jdField_a_of_type_Float;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 599 */     for (paramArrayOfString = 32; paramArrayOfString >= -32; paramArrayOfString--) {
/* 600 */       System.err.println(paramArrayOfString + ": " + a(paramArrayOfString, 2));
/*     */     }
/* 602 */     System.err.println("-------------------------");
/* 603 */     for (paramArrayOfString = 32; paramArrayOfString >= -32; paramArrayOfString--)
/* 604 */       System.err.println(paramArrayOfString + ": " + a(paramArrayOfString, 16));
/*     */   }
/*     */ 
/*     */   public static float a()
/*     */   {
/* 619 */     return jdField_a_of_type_JavaUtilRandom.nextFloat();
/*     */   }
/*     */ 
/*     */   public static float b(float paramFloat1, float paramFloat2)
/*     */   {
/* 697 */     return (float)Math.pow(paramFloat1, paramFloat2);
/*     */   }
/*     */ 
/*     */   public static double b(double paramDouble)
/*     */   {
/* 754 */     return (1.0D / (1.0D + Math.pow(1.000696D, -paramDouble)) - 0.5D) * 2.0D * 1000000.0D;
/*     */   }
/*     */ 
/*     */   public static float h(float paramFloat)
/*     */   {
/* 767 */     if (Math.abs(paramFloat %= 6.283186F) > 3.141593F) paramFloat -= 6.283186F; if (Math.abs(paramFloat) > 1.570796F) paramFloat = 3.141593F - paramFloat;
/* 768 */     if (Math.abs(paramFloat = paramFloat) <= 
/* 768 */       0.7853981633974483D) {
/* 769 */       return (float)Math.sin(paramFloat);
/*     */     }
/*     */ 
/* 772 */     return (float)Math.cos(1.570796326794897D - paramFloat);
/*     */   }
/*     */ 
/*     */   public static final float i(float paramFloat)
/*     */   {
/* 777 */     return jdField_b_of_type_ArrayOfFloat[((int)(paramFloat * 651.89862F) & 0xFFF)];
/*     */   }
/*     */ 
/*     */   public static final float j(float paramFloat)
/*     */   {
/* 782 */     return c[((int)(paramFloat * 651.89862F) & 0xFFF)];
/*     */   }
/*     */ 
/*     */   public static final float k(float paramFloat)
/*     */   {
/* 790 */     return jdField_a_of_type_ArrayOfFloat[((int)(paramFloat * 10430.38F) & 0xFFFF)];
/*     */   }
/*     */ 
/*     */   public static float l(float paramFloat)
/*     */   {
/* 836 */     return (float)Math.sqrt(paramFloat);
/*     */   }
/*     */ 
/*     */   public static float m(float paramFloat)
/*     */   {
/* 850 */     float f2 = 0.5F * paramFloat;
/*     */ 
/* 852 */     int i = Float.floatToIntBits(paramFloat);
/*     */     float f1;
/* 856 */     return (
/* 855 */       f1 = Float.intBitsToFloat(1597463007 - (i >> 1))) * (
/* 855 */       1.5F - f2 * f1 * f1) * 
/* 856 */       paramFloat;
/*     */   }
/*     */ 
/*     */   public static int a(float paramFloat)
/*     */   {
/* 910 */     return (int)(paramFloat + 16384.0D) - 16384;
/*     */   }
/*     */ 
/*     */   public static int b(float paramFloat)
/*     */   {
/* 920 */     return 16384 - (int)(16384.0D - paramFloat);
/*     */   }
/*     */ 
/*     */   public static float n(float paramFloat)
/*     */   {
/* 931 */     return (float)Math.tan(paramFloat);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  55 */     for (int i = 0; i < 65536; i++) {
/*  56 */       jdField_a_of_type_ArrayOfFloat[i] = ((float)Math.sin(i * 3.141592653589793D * 2.0D / 65536.0D));
/*     */     }
/*     */ 
/*  70 */     jdField_a_of_type_Float = (float)Math.log(2.0D);
/*     */ 
/*  92 */     jdField_a_of_type_JavaUtilRandom = new Random(System.currentTimeMillis());
/*     */ 
/* 104 */     jdField_b_of_type_ArrayOfFloat = new float[4096];
/*     */ 
/* 117 */     c = new float[4096];
/*     */ 
/* 119 */     for (i = 0; i < 4096; i++)
/*     */     {
/* 121 */       jdField_b_of_type_ArrayOfFloat[i] = ((float)Math.sin((i + 0.5F) / 4096.0F * 6.283186F));
/* 122 */       c[i] = ((float)Math.cos((i + 0.5F) / 4096.0F * 6.283186F));
/*     */     }
/*     */ 
/* 227 */     jdField_b_of_type_Float = (
/* 227 */       FastMath.jdField_a_of_type_Int = (int)Math.sqrt(16384.0D)) - 
/* 227 */       1;
/*     */ 
/* 229 */     d = new float[16384];
/*     */ 
/* 233 */     for (i = 0; i < jdField_a_of_type_Int; i++)
/*     */     {
/* 235 */       for (int j = 0; j < jdField_a_of_type_Int; j++)
/*     */       {
/* 237 */         float f1 = i / jdField_a_of_type_Int;
/* 238 */         float f2 = j / jdField_a_of_type_Int;
/*     */ 
/* 240 */         d[(j * jdField_a_of_type_Int + i)] = ((float)Math.atan2(f2, f1));
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.common.FastMath
 * JD-Core Version:    0.6.2
 */