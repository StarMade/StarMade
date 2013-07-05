/*     */ package org.schema.game.client.view.cubes.noise;
/*     */ 
/*     */ import dJ;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import org.lwjgl.BufferUtils;
/*     */ 
/*     */ public class Simplex
/*     */ {
/*  17 */   private static final float[][] jdField_a_of_type_Array2dOfFloat = { { 1.0F, 1.0F, 0.0F }, { -1.0F, 1.0F, 0.0F }, { 1.0F, -1.0F, 0.0F }, { -1.0F, -1.0F, 0.0F }, { 1.0F, 0.0F, 1.0F }, { -1.0F, 0.0F, 1.0F }, { 1.0F, 0.0F, -1.0F }, { -1.0F, 0.0F, -1.0F }, { 0.0F, 1.0F, 1.0F }, { 0.0F, -1.0F, 1.0F }, { 0.0F, 1.0F, -1.0F }, { 0.0F, -1.0F, -1.0F } };
/*     */   private static final int[] jdField_a_of_type_ArrayOfInt;
/*  72 */   private static final int[] b = new int[(
/*  72 */     Simplex.jdField_a_of_type_ArrayOfInt = new int[] { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 }).length]
/*  72 */     ;
/*     */   private static final IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/* 153 */   private static int[] c = { 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0 };
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  99 */     dJ.a(0.0F, 0.0F, 0.0F);
/* 100 */     System.err.println("PERM: " + jdField_a_of_type_ArrayOfInt.length);
/*     */ 
/* 103 */     long l = System.currentTimeMillis();
/*     */     int i;
/*     */     int j;
/* 104 */     for (paramArrayOfString = 0; paramArrayOfString < 500; 
/* 105 */       paramArrayOfString++) {
/* 106 */       for (i = 0; i < 500; i++) {
/* 107 */         for (j = 0; j < 500; j++) {
/* 108 */           a(j, i, paramArrayOfString, jdField_a_of_type_ArrayOfInt);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 113 */     System.err.println("1TIME: " + (float)(System.currentTimeMillis() - l) / 1000.0F);
/*     */ 
/* 115 */     l = System.currentTimeMillis();
/* 116 */     for (paramArrayOfString = 0; paramArrayOfString < 500; paramArrayOfString++) {
/* 117 */       for (i = 0; i < 500; i++) {
/* 118 */         for (j = 0; j < 500; j++) {
/* 119 */           dJ.a(j, i, paramArrayOfString);
/*     */         }
/*     */       }
/*     */     }
/* 123 */     System.err.println("2TIME: " + (float)(System.currentTimeMillis() - l) / 1000.0F);
/*     */   }
/*     */ 
/*     */   public static float a(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfInt)
/*     */   {
/* 274 */     float f1 = (paramFloat1 + paramFloat2 + paramFloat3) * 0.3333333F;
/* 275 */     int j = (int)(paramFloat1 + f1);
/* 276 */     int k = (int)(paramFloat2 + f1);
/* 277 */     int m = (int)(paramFloat3 + f1);
/* 278 */     f1 = (j + k + m) * 0.1666667F;
/* 279 */     float f3 = j - f1;
/* 280 */     float f4 = k - f1;
/* 281 */     f1 = m - f1;
/* 282 */     paramFloat1 -= f3;
/* 283 */     paramFloat2 -= f4;
/* 284 */     paramFloat3 -= f1;
/*     */     int i2;
/* 286 */     if (paramFloat1 >= paramFloat2) {
/* 287 */       if (paramFloat2 >= paramFloat3)
/* 288 */         i2 = 0;
/* 289 */       else if (paramFloat1 >= paramFloat3)
/* 290 */         i2 = 6;
/*     */       else {
/* 292 */         i2 = 12;
/*     */       }
/*     */     }
/* 295 */     else if (paramFloat2 < paramFloat3)
/* 296 */       i2 = 18;
/* 297 */     else if (paramFloat1 < paramFloat3)
/* 298 */       i2 = 24;
/*     */     else {
/* 300 */       i2 = 30;
/*     */     }
/*     */ 
/* 314 */     f1 = paramFloat1 - c[i2] + 0.1666667F;
/* 315 */     f3 = paramFloat2 - c[(i2 + 1)] + 0.1666667F;
/* 316 */     f4 = paramFloat3 - c[(i2 + 2)] + 0.1666667F;
/* 317 */     float f5 = paramFloat1 - c[(i2 + 3)] + 0.3333333F;
/* 318 */     float f6 = paramFloat2 - c[(i2 + 4)] + 0.3333333F;
/* 319 */     float f7 = paramFloat3 - c[(i2 + 5)] + 0.3333333F;
/*     */ 
/* 321 */     float f8 = paramFloat1 + -0.5F;
/* 322 */     float f9 = paramFloat2 + -0.5F;
/* 323 */     float f10 = paramFloat3 + -0.5F;
/*     */ 
/* 328 */     int n = j & 0xFF;
/* 329 */     k &= 255;
/* 330 */     m &= 255;
/*     */     float f11;
/* 340 */     if ((
/* 340 */       f11 = 0.6F - paramFloat1 * paramFloat1 - paramFloat2 * paramFloat2 - paramFloat3 * paramFloat3) < 
/* 340 */       0.0F) {
/* 341 */       paramFloat2 = 0.0F;
/*     */     } else {
/* 343 */       int i1 = b[paramArrayOfInt[(n + paramArrayOfInt[(k + paramArrayOfInt[m])])]];
/*     */       float tmp340_339 = (f11 * f11);
/*     */ 
/* 346 */       paramFloat2 = tmp340_339 * tmp340_339 * (
/* 346 */         paramFloat1 * jdField_a_of_type_Array2dOfFloat[i1][0] + paramFloat2 * jdField_a_of_type_Array2dOfFloat[i1][1] + paramFloat3 * jdField_a_of_type_Array2dOfFloat[i1][2]);
/*     */     }
/*     */ 
/* 350 */     if ((
/* 350 */       paramFloat1 = 0.6F - f1 * f1 - f3 * f3 - f4 * f4) < 
/* 350 */       0.0F) {
/* 351 */       paramFloat3 = 0.0F;
/*     */     } else {
/* 353 */       paramFloat3 = b[paramArrayOfInt[(n + c[i2] + paramArrayOfInt[(k + c[(i2 + 1)] + paramArrayOfInt[(m + c[(i2 + 2)])])])]];
/*     */       float tmp455_454 = (paramFloat1 * paramFloat1);
/*     */ 
/* 356 */       paramFloat3 = tmp455_454 * tmp455_454 * (
/* 356 */         f1 * jdField_a_of_type_Array2dOfFloat[paramFloat3][0] + f3 * jdField_a_of_type_Array2dOfFloat[paramFloat3][1] + f4 * jdField_a_of_type_Array2dOfFloat[paramFloat3][2]);
/*     */     }
/*     */     float f2;
/* 360 */     if ((
/* 360 */       paramFloat1 = 0.6F - f5 * f5 - f6 * f6 - f7 * f7) < 
/* 360 */       0.0F) {
/* 361 */       f1 = 0.0F;
/*     */     } else {
/* 363 */       int i = b[paramArrayOfInt[(n + c[(i2 + 3)] + paramArrayOfInt[(k + c[(i2 + 4)] + paramArrayOfInt[(m + c[(i2 + 5)])])])]];
/*     */       float tmp574_573 = (paramFloat1 * paramFloat1);
/*     */ 
/* 366 */       f2 = tmp574_573 * tmp574_573 * (
/* 366 */         f5 * jdField_a_of_type_Array2dOfFloat[i][0] + f6 * jdField_a_of_type_Array2dOfFloat[i][1] + f7 * jdField_a_of_type_Array2dOfFloat[i][2]);
/*     */     }
/*     */ 
/* 370 */     if ((
/* 370 */       paramFloat1 = 0.6F - f8 * f8 - f9 * f9 - f10 * f10) < 
/* 370 */       0.0F) {
/* 371 */       paramFloat1 = 0.0F;
/*     */     } else {
/* 373 */       paramArrayOfInt = b[paramArrayOfInt[(n + 1 + paramArrayOfInt[(k + 1 + paramArrayOfInt[(m + 1)])])]];
/*     */       float tmp674_673 = (paramFloat1 * paramFloat1);
/*     */ 
/* 376 */       paramFloat1 = tmp674_673 * tmp674_673 * (
/* 376 */         f8 * jdField_a_of_type_Array2dOfFloat[paramArrayOfInt][0] + f9 * jdField_a_of_type_Array2dOfFloat[paramArrayOfInt][1] + f10 * jdField_a_of_type_Array2dOfFloat[paramArrayOfInt][2]);
/*     */     }
/*     */ 
/* 379 */     return 16.0F * (paramFloat2 + paramFloat3 + f2 + paramFloat1) + 1.0F;
/*     */   }
/*     */ 
/*     */   public static int[] a(Random paramRandom)
/*     */   {
/* 384 */     int[] arrayOfInt;
/* 384 */     Random localRandom1 = paramRandom; int i = (paramRandom = arrayOfInt = Arrays.copyOf(jdField_a_of_type_ArrayOfInt, jdField_a_of_type_ArrayOfInt.length)).length;
/* 384 */     for (int j = 0; j < i; j++) { int k = j + localRandom1.nextInt(i - j); int n = k; int m = j; Random localRandom2 = paramRandom; int i1 = paramRandom[m]; localRandom2[m] = localRandom2[n]; localRandom2[n] = i1;
/*     */     }
/* 386 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   public static float b(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfInt)
/*     */   {
/* 398 */     float f = 0.0F;
/*     */ 
/* 400 */     for (int i = 1; i <= 4; i++) {
/* 401 */       int j = i * i;
/* 402 */       f += a(paramFloat1 * j, paramFloat2 * j, paramFloat3 * j, paramArrayOfInt);
/*     */     }
/* 404 */     return f;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  74 */     for (int i = 0; i < b.length; i++) {
/*  75 */       b[i] = (i % 12);
/*     */     }
/*     */ 
/*  78 */     jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(jdField_a_of_type_ArrayOfInt.length);
/*     */ 
/*  80 */     for (i = 0; i < jdField_a_of_type_ArrayOfInt.length; i++) {
/*  81 */       jdField_a_of_type_JavaNioIntBuffer.put(i % 12);
/*     */     }
/*     */ 
/* 145 */     { { 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1 }, { 0, 0, 1, 1, 0, 1 }, { 0, 0, 1, 0, 1, 1 }, { 0, 1, 0, 0, 1, 1 } }[5] = { 0, 1, 0, 1, 1, 0 };
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.cubes.noise.Simplex
 * JD-Core Version:    0.6.2
 */