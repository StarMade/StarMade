/*  1:   */package org.schema.game.common.data.physics.octree;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.Arrays;
/*  5:   */
/*  7:   */public class ArrayOctreeTraverse
/*  8:   */{
/*  9: 9 */  public static byte[][][] tMap = new byte[256][][];
/* 10:   */  
/* 12:   */  public static void create()
/* 13:   */  {
/* 14:14 */    for (int i = 0; i < 256; i++)
/* 15:   */    {
/* 17:17 */      int j = 0;
/* 18:18 */      int n; int i1; int i2; for (int k = 0; k < 2; k = (byte)(k + 1)) {
/* 19:19 */        for (m = 0; m < 2; m = (byte)(m + 1)) {
/* 20:20 */          for (n = 0; n < 2; n = (byte)(n + 1)) {
/* 21:21 */            i1 = (k % 2 << 2) + (m % 2 << 1) + n % 2;
/* 22:   */            
/* 25:25 */            i2 = 1 << i1;
/* 26:26 */            if ((i & i2) == i2) {
/* 27:27 */              j++;
/* 28:   */            }
/* 29:   */          }
/* 30:   */        }
/* 31:   */      }
/* 32:32 */      tMap[i] = new byte[j][];
/* 33:33 */      k = 0;
/* 34:34 */      for (int m = 0; m < 2; m = (byte)(m + 1)) {
/* 35:35 */        for (n = 0; n < 2; n = (byte)(n + 1)) {
/* 36:36 */          for (i1 = 0; i1 < 2; i1 = (byte)(i1 + 1))
/* 37:   */          {
/* 38:38 */            i2 = (m % 2 << 2) + (n % 2 << 1) + i1 % 2;
/* 39:39 */            j = 1 << i2;
/* 40:40 */            if ((i & j) == j) {
/* 41:41 */              tMap[i][k] = new byte[3];
/* 42:42 */              tMap[i][k][0] = i1;
/* 43:43 */              tMap[i][k][1] = n;
/* 44:44 */              tMap[i][k][2] = m;
/* 45:45 */              k++;
/* 46:   */            }
/* 47:   */          }
/* 48:   */        }
/* 49:   */      }
/* 50:   */    }
/* 51:   */  }
/* 52:   */  
/* 53:   */  public static void main(String[] paramArrayOfString)
/* 54:   */  {
/* 55:55 */    System.currentTimeMillis();
/* 56:   */    
/* 59:59 */    for (paramArrayOfString = 0; paramArrayOfString < 256; paramArrayOfString++)
/* 60:   */    {
/* 61:61 */      System.err.println("############## " + paramArrayOfString + " ##############");
/* 62:62 */      for (int i = 0; i < tMap[paramArrayOfString].length; i++) {
/* 63:63 */        System.err.println(Arrays.toString(tMap[paramArrayOfString][i]));
/* 64:   */      }
/* 65:   */    }
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctreeTraverse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */