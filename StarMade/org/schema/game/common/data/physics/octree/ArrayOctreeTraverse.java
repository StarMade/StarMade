package org.schema.game.common.data.physics.octree;

import java.io.PrintStream;
import java.util.Arrays;

public class ArrayOctreeTraverse
{
  public static byte[][][] tMap = new byte[256][][];
  
  public static void create()
  {
    for (int i = 0; i < 256; i++)
    {
      int j = 0;
      int n;
      int i1;
      int i2;
      for (int k = 0; k < 2; k = (byte)(k + 1)) {
        for (m = 0; m < 2; m = (byte)(m + 1)) {
          for (n = 0; n < 2; n = (byte)(n + 1))
          {
            i1 = (k % 2 << 2) + (m % 2 << 1) + n % 2;
            i2 = 1 << i1;
            if ((i & i2) == i2) {
              j++;
            }
          }
        }
      }
      tMap[i] = new byte[j][];
      k = 0;
      for (int m = 0; m < 2; m = (byte)(m + 1)) {
        for (n = 0; n < 2; n = (byte)(n + 1)) {
          for (i1 = 0; i1 < 2; i1 = (byte)(i1 + 1))
          {
            i2 = (m % 2 << 2) + (n % 2 << 1) + i1 % 2;
            j = 1 << i2;
            if ((i & j) == j)
            {
              tMap[i][k] = new byte[3];
              tMap[i][k][0] = i1;
              tMap[i][k][1] = n;
              tMap[i][k][2] = m;
              k++;
            }
          }
        }
      }
    }
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.currentTimeMillis();
    for (paramArrayOfString = 0; paramArrayOfString < 256; paramArrayOfString++)
    {
      System.err.println("############## " + paramArrayOfString + " ##############");
      for (int i = 0; i < tMap[paramArrayOfString].length; i++) {
        System.err.println(Arrays.toString(tMap[paramArrayOfString][i]));
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctreeTraverse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */