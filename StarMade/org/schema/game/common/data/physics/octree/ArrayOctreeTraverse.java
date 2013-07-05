/*    */ package org.schema.game.common.data.physics.octree;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class ArrayOctreeTraverse
/*    */ {
/*  9 */   public static byte[][][] tMap = new byte[256][][];
/*    */ 
/*    */   public static void create()
/*    */   {
/* 14 */     for (int i = 0; i < 256; i++)
/*    */     {
/* 17 */       int j = 0;
/*    */       int n;
/*    */       int i1;
/*    */       int i2;
/* 18 */       for (int k = 0; k < 2; k = (byte)(k + 1)) {
/* 19 */         for (m = 0; m < 2; m = (byte)(m + 1)) {
/* 20 */           for (n = 0; n < 2; n = (byte)(n + 1)) {
/* 21 */             i1 = (k % 2 << 2) + (m % 2 << 1) + n % 2;
/*    */ 
/* 25 */             i2 = 1 << i1;
/* 26 */             if ((i & i2) == i2) {
/* 27 */               j++;
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/* 32 */       tMap[i] = new byte[j][];
/* 33 */       k = 0;
/* 34 */       for (int m = 0; m < 2; m = (byte)(m + 1))
/* 35 */         for (n = 0; n < 2; n = (byte)(n + 1))
/* 36 */           for (i1 = 0; i1 < 2; i1 = (byte)(i1 + 1))
/*    */           {
/* 38 */             i2 = (m % 2 << 2) + (n % 2 << 1) + i1 % 2;
/* 39 */             j = 1 << i2;
/* 40 */             if ((i & j) == j) {
/* 41 */               tMap[i][k] = new byte[3];
/* 42 */               tMap[i][k][0] = i1;
/* 43 */               tMap[i][k][1] = n;
/* 44 */               tMap[i][k][2] = m;
/* 45 */               k++;
/*    */             }
/*    */           }
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 55 */     System.currentTimeMillis();
/*    */ 
/* 59 */     for (paramArrayOfString = 0; paramArrayOfString < 256; paramArrayOfString++)
/*    */     {
/* 61 */       System.err.println("############## " + paramArrayOfString + " ##############");
/* 62 */       for (int i = 0; i < tMap[paramArrayOfString].length; i++)
/* 63 */         System.err.println(Arrays.toString(tMap[paramArrayOfString][i]));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctreeTraverse
 * JD-Core Version:    0.6.2
 */