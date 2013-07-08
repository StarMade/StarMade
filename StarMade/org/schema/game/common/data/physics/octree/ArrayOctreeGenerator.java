package org.schema.game.common.data.physics.octree;

import class_35;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ArrayOctreeGenerator
{
  public static void splitStart(class_35 paramclass_351, class_35 paramclass_352, byte paramByte)
  {
    paramclass_351 = new class_35(paramclass_351);
    (paramclass_352 = new class_35(paramByte, paramByte, paramByte)).a1(paramclass_351);
    class_35 localclass_351 = new class_35(paramclass_351);
    class_35 localclass_352 = new class_35(paramclass_352);
    localclass_351.a(paramByte, (byte)0, (byte)0);
    localclass_352.a(paramByte, (byte)0, (byte)0);
    class_35 localclass_353 = new class_35(paramclass_351);
    class_35 localclass_354 = new class_35(paramclass_352);
    localclass_353.a(paramByte, (byte)0, paramByte);
    localclass_354.a(paramByte, (byte)0, paramByte);
    class_35 localclass_355 = new class_35(paramclass_351);
    class_35 localclass_356 = new class_35(paramclass_352);
    localclass_355.a((byte)0, (byte)0, paramByte);
    localclass_356.a((byte)0, (byte)0, paramByte);
    class_35 localclass_357 = new class_35(paramclass_351);
    class_35 localclass_358 = new class_35(paramclass_352);
    localclass_357.a((byte)0, paramByte, (byte)0);
    localclass_358.a((byte)0, paramByte, (byte)0);
    class_35 localclass_359 = new class_35(paramclass_351);
    class_35 localclass_3510 = new class_35(paramclass_352);
    localclass_359.a(paramByte, paramByte, (byte)0);
    localclass_3510.a(paramByte, paramByte, (byte)0);
    class_35 localclass_3511 = new class_35(paramclass_351);
    class_35 localclass_3512 = new class_35(paramclass_352);
    localclass_3511.a(paramByte, paramByte, paramByte);
    localclass_3512.a(paramByte, paramByte, paramByte);
    class_35 localclass_3513 = new class_35(paramclass_351);
    class_35 localclass_3514 = new class_35(paramclass_352);
    localclass_3513.a((byte)0, paramByte, paramByte);
    localclass_3514.a((byte)0, paramByte, paramByte);
    paramByte = (byte)(paramByte / 2);
    split(0, 0, paramclass_351, paramclass_352, paramByte);
    split(1, 0, localclass_351, localclass_352, paramByte);
    split(2, 0, localclass_353, localclass_354, paramByte);
    split(3, 0, localclass_355, localclass_356, paramByte);
    split(4, 0, localclass_357, localclass_358, paramByte);
    split(5, 0, localclass_359, localclass_3510, paramByte);
    split(6, 0, localclass_3511, localclass_3512, paramByte);
    split(7, 0, localclass_3513, localclass_3514, paramByte);
  }
  
  public static void split(int paramInt1, int paramInt2, class_35 paramclass_351, class_35 paramclass_352, byte paramByte)
  {
    for (;;)
    {
      int i;
      put(i = ArrayOctree.getIndex(paramInt1, paramInt2), paramclass_351, paramclass_352);
      for (int j = paramclass_351.field_455 + 8; j < paramclass_352.field_455 + 8; j++) {
        for (int k = paramclass_351.field_454 + 8; k < paramclass_352.field_454 + 8; k++) {
          for (int m = paramclass_351.field_453 + 8; m < paramclass_352.field_453 + 8; m++) {
            putNodeIndex(m, k, j, paramInt2, i);
          }
        }
      }
      if (paramInt2 >= 2) {
        break;
      }
      class_35 localclass_352 = new class_35(paramclass_351);
      class_35 localclass_353;
      (localclass_353 = new class_35(paramByte, paramByte, paramByte)).a1(localclass_352);
      class_35 localclass_354 = new class_35(localclass_352);
      paramclass_351 = new class_35(localclass_353);
      localclass_354.a(paramByte, (byte)0, (byte)0);
      paramclass_351.a(paramByte, (byte)0, (byte)0);
      paramclass_352 = new class_35(localclass_352);
      class_35 localclass_351 = new class_35(localclass_353);
      paramclass_352.a(paramByte, (byte)0, paramByte);
      localclass_351.a(paramByte, (byte)0, paramByte);
      class_35 localclass_355 = new class_35(localclass_352);
      class_35 localclass_356 = new class_35(localclass_353);
      localclass_355.a((byte)0, (byte)0, paramByte);
      localclass_356.a((byte)0, (byte)0, paramByte);
      class_35 localclass_357 = new class_35(localclass_352);
      class_35 localclass_358 = new class_35(localclass_353);
      localclass_357.a((byte)0, paramByte, (byte)0);
      localclass_358.a((byte)0, paramByte, (byte)0);
      class_35 localclass_359 = new class_35(localclass_352);
      class_35 localclass_3510 = new class_35(localclass_353);
      localclass_359.a(paramByte, paramByte, (byte)0);
      localclass_3510.a(paramByte, paramByte, (byte)0);
      class_35 localclass_3511 = new class_35(localclass_352);
      class_35 localclass_3512 = new class_35(localclass_353);
      localclass_3511.a(paramByte, paramByte, paramByte);
      localclass_3512.a(paramByte, paramByte, paramByte);
      class_35 localclass_3513 = new class_35(localclass_352);
      class_35 localclass_3514 = new class_35(localclass_353);
      localclass_3513.a((byte)0, paramByte, paramByte);
      localclass_3514.a((byte)0, paramByte, paramByte);
      paramInt1 <<= 3;
      paramByte = (byte)(paramByte / 2);
      split(paramInt1, paramInt2 + 1, localclass_352, localclass_353, paramByte);
      split(paramInt1 + 1, paramInt2 + 1, localclass_354, paramclass_351, paramByte);
      split(paramInt1 + 2, paramInt2 + 1, paramclass_352, localclass_351, paramByte);
      split(paramInt1 + 3, paramInt2 + 1, localclass_355, localclass_356, paramByte);
      split(paramInt1 + 4, paramInt2 + 1, localclass_357, localclass_358, paramByte);
      split(paramInt1 + 5, paramInt2 + 1, localclass_359, localclass_3510, paramByte);
      split(paramInt1 + 6, paramInt2 + 1, localclass_3511, localclass_3512, paramByte);
      paramclass_352 = localclass_3514;
      paramclass_351 = localclass_3513;
      paramInt2 += 1;
      paramInt1 += 7;
    }
  }
  
  public static void putNodeIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramInt1 = ((paramInt3 << 4 << 4) + (paramInt2 << 4) + paramInt1) * 3;
    ArrayOctree.indexBuffer.put(paramInt1 + paramInt4, paramInt5);
  }
  
  public static int getNodeIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = ((paramInt3 << 4 << 4) + (paramInt2 << 4) + paramInt1) * 3;
    return ArrayOctree.indexBuffer.get(paramInt1 + paramInt4);
  }
  
  public static void put(int paramInt, class_35 paramclass_351, class_35 paramclass_352)
  {
    paramInt *= 6;
    ArrayOctree.dimBuffer.put(paramInt, paramclass_351.field_453);
    ArrayOctree.dimBuffer.put(paramInt + 1, paramclass_351.field_454);
    ArrayOctree.dimBuffer.put(paramInt + 2, paramclass_351.field_455);
    ArrayOctree.dimBuffer.put(paramInt + 3, paramclass_352.field_453);
    ArrayOctree.dimBuffer.put(paramInt + 4, paramclass_352.field_454);
    ArrayOctree.dimBuffer.put(paramInt + 5, paramclass_352.field_455);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctreeGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */