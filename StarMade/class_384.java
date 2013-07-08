import com.bulletphysics.collision.shapes.ConvexShape;
import java.nio.FloatBuffer;
import org.schema.common.util.ByteUtil;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.client.view.cubes.CubeOptOptMesh;

public abstract class class_384
{
  public static final int[][] field_102;
  public static final int[][] field_775;
  public static final int[][] field_776;
  public static class_384[][] field_102;
  public static final int[][] field_777 = { { 1, 0, 2, 3 }, { 3, 2, 0, 1 }, { 1, 0, 2, 3 }, { 2, 3, 1, 0 }, { 3, 2, 0, 1 }, { 1, 0, 2, 3 } };
  
  protected static void a3(int paramInt1, short paramShort1, byte paramByte1, short paramShort2, byte paramByte2, byte paramByte3, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, short paramShort3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    ByteUtil.a2((byte)paramInt1, paramByte1, paramShort2, paramByte2, paramByte3, (byte)(paramBoolean1 ? 1 : 0), (byte)(paramBoolean2 ? 1 : 0));
    byte b1 = paramCubeMeshBufferContainer.a2(paramInt2, paramInt3 + paramShort3, 0);
    byte b2 = paramCubeMeshBufferContainer.a2(paramInt2, paramInt3 + paramShort3, 1);
    paramInt2 = paramCubeMeshBufferContainer.a2(paramInt2, paramInt3 + paramShort3, 2);
    a4(paramInt1, paramShort1, paramByte1, paramShort2, paramByte2, paramByte3, paramBoolean1, paramBoolean2, paramShort3, paramInt4, b1, b2, paramInt2, paramFloat, paramCubeMeshBufferContainer.jdField_field_1665_of_type_JavaNioFloatBuffer);
  }
  
  protected static void a4(int paramInt1, short paramShort1, byte paramByte1, short paramShort2, byte paramByte2, byte paramByte3, boolean paramBoolean1, boolean paramBoolean2, short paramShort3, int paramInt2, byte paramByte4, byte paramByte5, byte paramByte6, float paramFloat, FloatBuffer paramFloatBuffer)
  {
    paramByte1 = ByteUtil.a2((byte)paramInt1, paramByte1, paramShort2, paramByte2, paramByte3, (byte)(paramBoolean1 ? 1 : 0), (byte)(paramBoolean2 ? 1 : 0));
    paramShort2 = ByteUtil.a3(paramInt2, paramByte4, paramByte5, paramByte6);
    paramInt1 = (byte)field_777[paramInt1][paramShort1] + paramByte1;
    paramFloatBuffer.put(paramShort2);
    paramFloatBuffer.put(paramInt1);
    if (CubeMeshBufferContainer.jdField_field_1665_of_type_Int > 2) {
      paramFloatBuffer.put(paramFloat);
    }
    if (paramInt2 + paramShort3 > CubeOptOptMesh.field_94) {
      CubeOptOptMesh.field_94 = paramInt2 + paramShort3;
    }
    if ((!jdField_field_102_of_type_Boolean) && (paramInt2 + paramShort3 >= 49152)) {
      throw new AssertionError("vert index is bigger: " + (paramInt2 + paramShort3) + "/49152");
    }
  }
  
  public abstract void a1(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer);
  
  public static void b(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, float paramFloat, CubeMeshBufferContainer paramCubeMeshBufferContainer)
  {
    for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1))
    {
      short s2 = s1;
      a3(paramInt1, s2, paramByte1, paramShort, paramByte2, paramByte3, false, false, paramInt2, paramInt3, s1, paramInt4, paramFloat, paramCubeMeshBufferContainer);
    }
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
  
  public static boolean a5()
  {
    return true;
  }
  
  public static ConvexShape a6(int paramInt, byte paramByte, boolean paramBoolean)
  {
    return jdField_field_102_of_type_Array2dOfClass_384[(paramInt - 1)][(paramByte + 8)].a2();
  }
  
  protected abstract ConvexShape a2();
  
  static
  {
    jdField_field_102_of_type_Boolean = !dO.class.desiredAssertionStatus();
    jdField_field_102_of_type_Array2dOfInt = new int[3][16];
    field_775 = new int[3][16];
    field_776 = new int[3][16];
    for (int i = 0; i < jdField_field_102_of_type_Array2dOfInt.length; i++) {
      for (int j = 0; j < 16; j++)
      {
        jdField_field_102_of_type_Array2dOfInt[i][j] = j;
        field_775[i][j] = j;
        field_776[i][j] = j;
      }
    }
    jdField_field_102_of_type_Array2dOfInt[1][4] = 5;
    jdField_field_102_of_type_Array2dOfInt[1][5] = 4;
    jdField_field_102_of_type_Array2dOfInt[1][7] = 6;
    jdField_field_102_of_type_Array2dOfInt[1][6] = 7;
    jdField_field_102_of_type_Array2dOfInt[1][0] = 1;
    jdField_field_102_of_type_Array2dOfInt[1][1] = 0;
    jdField_field_102_of_type_Array2dOfInt[1][3] = 2;
    jdField_field_102_of_type_Array2dOfInt[1][2] = 3;
    jdField_field_102_of_type_Array2dOfInt[1][12] = 13;
    jdField_field_102_of_type_Array2dOfInt[1][13] = 12;
    jdField_field_102_of_type_Array2dOfInt[1][15] = 14;
    jdField_field_102_of_type_Array2dOfInt[1][14] = 15;
    jdField_field_102_of_type_Array2dOfInt[1][8] = 9;
    jdField_field_102_of_type_Array2dOfInt[1][9] = 8;
    jdField_field_102_of_type_Array2dOfInt[1][11] = 10;
    jdField_field_102_of_type_Array2dOfInt[1][10] = 11;
    field_775[1][1] = 5;
    field_775[1][5] = 1;
    field_775[1][0] = 4;
    field_775[1][4] = 0;
    field_775[1][3] = 7;
    field_775[1][7] = 3;
    field_775[1][2] = 6;
    field_775[1][6] = 2;
    field_775[1][9] = 13;
    field_775[1][13] = 9;
    field_775[1][8] = 12;
    field_775[1][12] = 8;
    field_775[1][11] = 15;
    field_775[1][15] = 11;
    field_775[1][10] = 14;
    field_775[1][14] = 10;
    field_776[1][0] = 3;
    field_776[1][3] = 0;
    field_776[1][1] = 2;
    field_776[1][2] = 1;
    field_776[1][4] = 7;
    field_776[1][7] = 4;
    field_776[1][5] = 6;
    field_776[1][6] = 5;
    field_776[1][8] = 11;
    field_776[1][11] = 8;
    field_776[1][9] = 10;
    field_776[1][10] = 9;
    field_776[1][12] = 15;
    field_776[1][15] = 12;
    field_776[1][13] = 14;
    field_776[1][14] = 13;
    jdField_field_102_of_type_Array2dOfInt[0][4] = 6;
    jdField_field_102_of_type_Array2dOfInt[0][6] = 4;
    jdField_field_102_of_type_Array2dOfInt[0][0] = 2;
    jdField_field_102_of_type_Array2dOfInt[0][2] = 0;
    jdField_field_102_of_type_Array2dOfInt[0][8] = 9;
    jdField_field_102_of_type_Array2dOfInt[0][9] = 8;
    jdField_field_102_of_type_Array2dOfInt[0][12] = 13;
    jdField_field_102_of_type_Array2dOfInt[0][13] = 12;
    jdField_field_102_of_type_Array2dOfInt[0][10] = 11;
    jdField_field_102_of_type_Array2dOfInt[0][11] = 10;
    jdField_field_102_of_type_Array2dOfInt[0][14] = 15;
    jdField_field_102_of_type_Array2dOfInt[0][15] = 14;
    field_775[0][1] = 7;
    field_775[0][7] = 1;
    field_775[0][5] = 3;
    field_775[0][3] = 5;
    field_775[0][0] = 4;
    field_775[0][4] = 0;
    field_775[0][2] = 6;
    field_775[0][6] = 2;
    field_776[0][1] = 3;
    field_776[0][3] = 1;
    field_776[0][5] = 7;
    field_776[0][7] = 5;
    field_776[0][8] = 10;
    field_776[0][10] = 8;
    field_776[0][12] = 14;
    field_776[0][14] = 12;
    field_776[0][9] = 11;
    field_776[0][11] = 9;
    field_776[0][13] = 15;
    field_776[0][15] = 13;
    jdField_field_102_of_type_Array2dOfClass_384 = new class_384[][] { { new class_277(), new class_289(), new class_279(), new class_275(), new class_207(), new class_211(), new class_205(), new class_209(), new class_269(), new class_281(), new class_271(), new class_267(), new class_269(), new class_281(), new class_271(), new class_267(), new class_273() }, { new class_362(), new class_366(), new class_368(), new class_364(), new class_372(), new class_374(), new class_380(), new class_376(), new class_362(), new class_366(), new class_368(), new class_364(), new class_372(), new class_374(), new class_380(), new class_376(), new class_370() }, { new class_360(), new class_360(), new class_360(), new class_360(), new class_360(), new class_360(), new class_360(), new class_360() } };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_384
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */