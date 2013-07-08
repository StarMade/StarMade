/*   1:    */package org.schema.game.client.view.cubes.noise;
/*   2:    */
/*   3:    */import dJ;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.nio.IntBuffer;
/*   6:    */import java.util.Arrays;
/*   7:    */import java.util.Random;
/*   8:    */import org.lwjgl.BufferUtils;
/*   9:    */
/*  15:    */public class Simplex
/*  16:    */{
/*  17: 17 */  private static final float[][] jdField_a_of_type_Array2dOfFloat = { { 1.0F, 1.0F, 0.0F }, { -1.0F, 1.0F, 0.0F }, { 1.0F, -1.0F, 0.0F }, { -1.0F, -1.0F, 0.0F }, { 1.0F, 0.0F, 1.0F }, { -1.0F, 0.0F, 1.0F }, { 1.0F, 0.0F, -1.0F }, { -1.0F, 0.0F, -1.0F }, { 0.0F, 1.0F, 1.0F }, { 0.0F, -1.0F, 1.0F }, { 0.0F, 1.0F, -1.0F }, { 0.0F, -1.0F, -1.0F } };
/*  18:    */  
/*  45:    */  private static final int[] jdField_a_of_type_ArrayOfInt;
/*  46:    */  
/*  72: 72 */  private static final int[] b = new int[(Simplex.jdField_a_of_type_ArrayOfInt = new int[] { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180, 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 }).length];
/*  73:    */  
/*  84:    */  private static final IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/*  85:    */  
/*  97:    */  public static void main(String[] paramArrayOfString)
/*  98:    */  {
/*  99: 99 */    dJ.a(0.0F, 0.0F, 0.0F);
/* 100:100 */    System.err.println("PERM: " + jdField_a_of_type_ArrayOfInt.length);
/* 101:    */    
/* 103:103 */    long l = System.currentTimeMillis();
/* 104:104 */    int i; int j; for (paramArrayOfString = 0; paramArrayOfString < 500; 
/* 105:105 */        paramArrayOfString++) {
/* 106:106 */      for (i = 0; i < 500; i++) {
/* 107:107 */        for (j = 0; j < 500; j++) {
/* 108:108 */          a(j, i, paramArrayOfString, jdField_a_of_type_ArrayOfInt);
/* 109:    */        }
/* 110:    */      }
/* 111:    */    }
/* 112:    */    
/* 113:113 */    System.err.println("1TIME: " + (float)(System.currentTimeMillis() - l) / 1000.0F);
/* 114:    */    
/* 115:115 */    l = System.currentTimeMillis();
/* 116:116 */    for (paramArrayOfString = 0; paramArrayOfString < 500; paramArrayOfString++) {
/* 117:117 */      for (i = 0; i < 500; i++) {
/* 118:118 */        for (j = 0; j < 500; j++) {
/* 119:119 */          dJ.a(j, i, paramArrayOfString);
/* 120:    */        }
/* 121:    */      }
/* 122:    */    }
/* 123:123 */    System.err.println("2TIME: " + (float)(System.currentTimeMillis() - l) / 1000.0F);
/* 124:    */  }
/* 125:    */  
/* 126:    */  static
/* 127:    */  {
/* 128: 74 */    for (int i = 0; i < b.length; i++) {
/* 129: 75 */      b[i] = (i % 12);
/* 130:    */    }
/* 131:    */    
/* 132: 78 */    jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(jdField_a_of_type_ArrayOfInt.length);
/* 133:    */    
/* 134: 80 */    for (i = 0; i < jdField_a_of_type_ArrayOfInt.length; i++) {
/* 135: 81 */      jdField_a_of_type_JavaNioIntBuffer.put(i % 12);
/* 136:    */    }
/* 137:    */    
/* 199:145 */    { { 1, 0, 0, 1, 1, 0 }, { 1, 0, 0, 1, 0, 1 }, { 0, 0, 1, 1, 0, 1 }, { 0, 0, 1, 0, 1, 1 }, { 0, 1, 0, 0, 1, 1 } }[5] = { 0, 1, 0, 1, 1, 0 };
/* 200:    */  }
/* 201:    */  
/* 207:153 */  private static int[] c = { 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0 };
/* 208:    */  
/* 326:    */  public static float a(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfInt)
/* 327:    */  {
/* 328:274 */    float f1 = (paramFloat1 + paramFloat2 + paramFloat3) * 0.3333333F;
/* 329:275 */    int j = (int)(paramFloat1 + f1);
/* 330:276 */    int k = (int)(paramFloat2 + f1);
/* 331:277 */    int m = (int)(paramFloat3 + f1);
/* 332:278 */    f1 = (j + k + m) * 0.1666667F;
/* 333:279 */    float f3 = j - f1;
/* 334:280 */    float f4 = k - f1;
/* 335:281 */    f1 = m - f1;
/* 336:282 */    paramFloat1 -= f3;
/* 337:283 */    paramFloat2 -= f4;
/* 338:284 */    paramFloat3 -= f1;
/* 339:    */    int i2;
/* 340:286 */    if (paramFloat1 >= paramFloat2) {
/* 341:287 */      if (paramFloat2 >= paramFloat3) {
/* 342:288 */        i2 = 0;
/* 343:289 */      } else if (paramFloat1 >= paramFloat3) {
/* 344:290 */        i2 = 6;
/* 345:    */      } else {
/* 346:292 */        i2 = 12;
/* 347:    */      }
/* 348:    */    }
/* 349:295 */    else if (paramFloat2 < paramFloat3) {
/* 350:296 */      i2 = 18;
/* 351:297 */    } else if (paramFloat1 < paramFloat3) {
/* 352:298 */      i2 = 24;
/* 353:    */    } else {
/* 354:300 */      i2 = 30;
/* 355:    */    }
/* 356:    */    
/* 368:314 */    f1 = paramFloat1 - c[i2] + 0.1666667F;
/* 369:315 */    f3 = paramFloat2 - c[(i2 + 1)] + 0.1666667F;
/* 370:316 */    f4 = paramFloat3 - c[(i2 + 2)] + 0.1666667F;
/* 371:317 */    float f5 = paramFloat1 - c[(i2 + 3)] + 0.3333333F;
/* 372:318 */    float f6 = paramFloat2 - c[(i2 + 4)] + 0.3333333F;
/* 373:319 */    float f7 = paramFloat3 - c[(i2 + 5)] + 0.3333333F;
/* 374:    */    
/* 375:321 */    float f8 = paramFloat1 + -0.5F;
/* 376:322 */    float f9 = paramFloat2 + -0.5F;
/* 377:323 */    float f10 = paramFloat3 + -0.5F;
/* 378:    */    
/* 382:328 */    int n = j & 0xFF;
/* 383:329 */    k &= 255;
/* 384:330 */    m &= 255;
/* 385:    */    
/* 389:    */    float f11;
/* 390:    */    
/* 394:340 */    if ((f11 = 0.6F - paramFloat1 * paramFloat1 - paramFloat2 * paramFloat2 - paramFloat3 * paramFloat3) < 0.0F) {
/* 395:341 */      paramFloat2 = 0.0F;
/* 396:    */    } else {
/* 397:343 */      int i1 = b[paramArrayOfInt[(n + paramArrayOfInt[(k + paramArrayOfInt[m])])]]; float 
/* 398:344 */        tmp340_339 = (f11 * f11);
/* 399:    */      
/* 400:346 */      paramFloat2 = tmp340_339 * tmp340_339 * (paramFloat1 * jdField_a_of_type_Array2dOfFloat[i1][0] + paramFloat2 * jdField_a_of_type_Array2dOfFloat[i1][1] + paramFloat3 * jdField_a_of_type_Array2dOfFloat[i1][2]);
/* 401:    */    }
/* 402:    */    
/* 404:350 */    if ((paramFloat1 = 0.6F - f1 * f1 - f3 * f3 - f4 * f4) < 0.0F) {
/* 405:351 */      paramFloat3 = 0.0F;
/* 406:    */    } else {
/* 407:353 */      paramFloat3 = b[paramArrayOfInt[(n + c[i2] + paramArrayOfInt[(k + c[(i2 + 1)] + paramArrayOfInt[(m + c[(i2 + 2)])])])]]; float 
/* 408:354 */        tmp455_454 = (paramFloat1 * paramFloat1);
/* 409:    */      
/* 410:356 */      paramFloat3 = tmp455_454 * tmp455_454 * (f1 * jdField_a_of_type_Array2dOfFloat[paramFloat3][0] + f3 * jdField_a_of_type_Array2dOfFloat[paramFloat3][1] + f4 * jdField_a_of_type_Array2dOfFloat[paramFloat3][2]);
/* 411:    */    }
/* 412:    */    
/* 413:    */    float f2;
/* 414:360 */    if ((paramFloat1 = 0.6F - f5 * f5 - f6 * f6 - f7 * f7) < 0.0F) {
/* 415:361 */      f1 = 0.0F;
/* 416:    */    } else {
/* 417:363 */      int i = b[paramArrayOfInt[(n + c[(i2 + 3)] + paramArrayOfInt[(k + c[(i2 + 4)] + paramArrayOfInt[(m + c[(i2 + 5)])])])]]; float 
/* 418:364 */        tmp574_573 = (paramFloat1 * paramFloat1);
/* 419:    */      
/* 420:366 */      f2 = tmp574_573 * tmp574_573 * (f5 * jdField_a_of_type_Array2dOfFloat[i][0] + f6 * jdField_a_of_type_Array2dOfFloat[i][1] + f7 * jdField_a_of_type_Array2dOfFloat[i][2]);
/* 421:    */    }
/* 422:    */    
/* 424:370 */    if ((paramFloat1 = 0.6F - f8 * f8 - f9 * f9 - f10 * f10) < 0.0F) {
/* 425:371 */      paramFloat1 = 0.0F;
/* 426:    */    } else {
/* 427:373 */      paramArrayOfInt = b[paramArrayOfInt[(n + 1 + paramArrayOfInt[(k + 1 + paramArrayOfInt[(m + 1)])])]]; float 
/* 428:374 */        tmp674_673 = (paramFloat1 * paramFloat1);
/* 429:    */      
/* 430:376 */      paramFloat1 = tmp674_673 * tmp674_673 * (f8 * jdField_a_of_type_Array2dOfFloat[paramArrayOfInt][0] + f9 * jdField_a_of_type_Array2dOfFloat[paramArrayOfInt][1] + f10 * jdField_a_of_type_Array2dOfFloat[paramArrayOfInt][2]);
/* 431:    */    }
/* 432:    */    
/* 433:379 */    return 16.0F * (paramFloat2 + paramFloat3 + f2 + paramFloat1) + 1.0F;
/* 434:    */  }
/* 435:    */  
/* 436:    */  public static int[] a(Random paramRandom) {
/* 437:    */    int[] arrayOfInt;
/* 438:384 */    Random localRandom1 = paramRandom;int i = (paramRandom = arrayOfInt = Arrays.copyOf(jdField_a_of_type_ArrayOfInt, jdField_a_of_type_ArrayOfInt.length)).length; for (int j = 0; j < i; j++) { int k = j + localRandom1.nextInt(i - j);int n = k;int m = j;Random localRandom2 = paramRandom;int i1 = paramRandom[m];localRandom2[m] = localRandom2[n];localRandom2[n] = i1;
/* 439:    */    }
/* 440:386 */    return arrayOfInt;
/* 441:    */  }
/* 442:    */  
/* 450:    */  public static float b(float paramFloat1, float paramFloat2, float paramFloat3, int[] paramArrayOfInt)
/* 451:    */  {
/* 452:398 */    float f = 0.0F;
/* 453:    */    
/* 454:400 */    for (int i = 1; i <= 4; i++) {
/* 455:401 */      int j = i * i;
/* 456:402 */      f += a(paramFloat1 * j, paramFloat2 * j, paramFloat3 * j, paramArrayOfInt);
/* 457:    */    }
/* 458:404 */    return f;
/* 459:    */  }
/* 460:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.client.view.cubes.noise.Simplex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */