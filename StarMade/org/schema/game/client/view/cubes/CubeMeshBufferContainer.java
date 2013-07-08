package org.schema.game.client.view.cubes;

import class_1376;
import class_384;
import class_48;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.BufferUtils;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public class CubeMeshBufferContainer
{
  public ShortBuffer field_1665;
  public FloatBuffer field_1665;
  public IntBuffer field_1665;
  public ShortBuffer field_1666;
  private ByteBuffer jdField_field_1665_of_type_JavaNioByteBuffer;
  private ByteBuffer jdField_field_1666_of_type_JavaNioByteBuffer;
  public static final int field_1665;
  private static final int[][] jdField_field_1665_of_type_Array2dOfInt = { { 0, 1, 2, 3, 5, 4 }, { 1, 0, 3, 2, 4, 5 }, { 1, 0, 5, 4, 2, 3 }, { 0, 1, 4, 5, 3, 2 }, { 4, 5, 2, 3, 0, 1 }, { 5, 4, 2, 3, 1, 0 } };
  private static final int[][] jdField_field_1666_of_type_Array2dOfInt = { { 3, 3, 4, 5, 3, 3 }, { 3, 3, 5, 4, 3, 3 }, { 3, 3, 4, 5, 3, 3 }, { 3, 3, 5, 4, 3, 3 }, { 3, 3, 4, 5, 3, 3 }, { 3, 3, 4, 5, 3, 3 } };
  
  public static int a(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1) * 3 * 24;
  }
  
  public static CubeMeshBufferContainer a1()
  {
    CubeMeshBufferContainer localCubeMeshBufferContainer1;
    CubeMeshBufferContainer localCubeMeshBufferContainer2;
    (localCubeMeshBufferContainer2 = localCubeMeshBufferContainer1 = new CubeMeshBufferContainer()).jdField_field_1665_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(4096);
    localCubeMeshBufferContainer2.jdField_field_1666_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(4096);
    localCubeMeshBufferContainer2.jdField_field_1665_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(294912);
    localCubeMeshBufferContainer2.jdField_field_1666_of_type_JavaNioByteBuffer = BufferUtils.createByteBuffer(4096);
    localCubeMeshBufferContainer2.jdField_field_1665_of_type_JavaNioShortBuffer = BufferUtils.createShortBuffer(4096);
    localCubeMeshBufferContainer2.jdField_field_1665_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(49152 * jdField_field_1665_of_type_Int);
    localCubeMeshBufferContainer2.jdField_field_1665_of_type_JavaNioFloatBuffer.rewind();
    return localCubeMeshBufferContainer1;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.err.println("index 96 kb");
    System.err.println("attrib 384 kb");
    System.err.println("other " + 196608 * jdField_field_1665_of_type_Int / 1024 + " kb");
    paramArrayOfString = (float)Math.floor(8.53125D);
    float f1 = (float)Math.floor((2184.0D - paramArrayOfString * 256.0D) / 16.0D);
    float f2 = 2184.0F - (f1 * 16.0F + paramArrayOfString * 256.0F);
    System.err.println("2184.0: " + f2 + ", " + f1 + ", " + paramArrayOfString);
  }
  
  public final byte a2(int paramInt1, int paramInt2, int paramInt3)
  {
    return this.jdField_field_1665_of_type_JavaNioByteBuffer.get(paramInt1 + paramInt2 * 3 + paramInt3);
  }
  
  public final byte a3(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    paramByte1 = paramByte1 + (paramByte2 << 4) + (paramByte3 << 8);
    return this.jdField_field_1666_of_type_JavaNioByteBuffer.get(paramByte1);
  }
  
  public final void a4()
  {
    for (int i = 0; i < 4096; i++) {
      this.jdField_field_1666_of_type_JavaNioByteBuffer.put(i, (byte)0);
    }
  }
  
  public final void a5(int paramInt1, byte paramByte, int paramInt2, int paramInt3)
  {
    this.jdField_field_1665_of_type_JavaNioByteBuffer.put(paramInt1 + paramInt2 * 3 + paramInt3, paramByte);
  }
  
  public final void a6(int paramInt, byte paramByte)
  {
    this.jdField_field_1666_of_type_JavaNioByteBuffer.put(paramInt, paramByte);
  }
  
  public static byte a7(int paramInt1, int paramInt2)
  {
    return (byte)(5 - jdField_field_1666_of_type_Array2dOfInt[paramInt2][paramInt1]);
  }
  
  public static byte b(int paramInt1, int paramInt2)
  {
    return (byte)(5 - jdField_field_1665_of_type_Array2dOfInt[paramInt2][paramInt1]);
  }
  
  public static final int a8(CubeMeshBufferContainer paramCubeMeshBufferContainer, int paramInt1, int paramInt2, SegmentData paramSegmentData, int paramInt3, int paramInt4, short paramShort)
  {
    int i = SegmentData.getLightInfoIndexFromIndex(paramInt2);
    int j = (paramShort = ElementKeyMap.getInfo(paramShort)).getIndividualSides();
    boolean bool = paramShort.isAnimated();
    int m = paramShort.getBlockStyle();
    int n = paramSegmentData.getOrientation(paramInt2);
    if ((!paramSegmentData.isActive(paramInt2)) && ((m == 1) || (m == 2)))
    {
      if ((!jdField_field_1665_of_type_Boolean) && (paramShort.canActivate())) {
        throw new AssertionError();
      }
      n = (byte)(n + 8);
    }
    int i1 = 0;
    if (j == 6)
    {
      n = (byte)Math.max(0, Math.min(5, n));
      if ((!jdField_field_1665_of_type_Boolean) && (n >= 6)) {
        throw new AssertionError("Orientation wrong: " + n);
      }
      i1 = b(paramInt4, n);
    }
    else if (j == 3)
    {
      n = (byte)Math.max(0, Math.min(5, n));
      if ((!jdField_field_1665_of_type_Boolean) && (n >= 6)) {
        throw new AssertionError("Orientation wrong: " + n);
      }
      i1 = a7(paramInt4, n);
    }
    float f = paramSegmentData.getHitpoints(paramInt2) / paramShort.getMaxHitPoints();
    byte b = 0;
    if (f < 1.0F) {
      b = FastMath.a4((byte)(int)((1.0F - f) * 7.0F));
    }
    if ((paramCubeMeshBufferContainer.jdField_field_1666_of_type_JavaNioByteBuffer.get(paramInt2 / 3) & paramInt3) == paramInt3)
    {
      class_384.a5();
      paramInt2 = paramInt4 << 2;
      paramInt3 = 0;
      if ((bool) && ((j != 3) || ((paramInt4 != 2) && (paramInt4 != 3)))) {
        paramInt3 = 1;
      }
      j = (byte)(Math.abs(paramShort.getTextureId() + i1) / 256);
      paramShort = (short)((paramShort.getTextureId() + i1) % 256);
      int k = ByteUtil.b(paramSegmentData.getSegment().field_34.field_475) + 128;
      i1 = ByteUtil.b(paramSegmentData.getSegment().field_34.field_476) + 128;
      paramSegmentData = (ByteUtil.b(paramSegmentData.getSegment().field_34.field_477) + 128 << 16) + (i1 << 8) + k;
      if (m > 0) {
        class_384.field_102[(m - 1)][n].a1(paramInt4, j, paramShort, b, paramInt3, i, paramInt2, paramInt1, paramSegmentData, paramCubeMeshBufferContainer);
      } else {
        class_384.b(paramInt4, j, paramShort, b, paramInt3, i, paramInt2, paramInt1, paramSegmentData, paramCubeMeshBufferContainer);
      }
      return 4;
    }
    return 0;
  }
  
  static
  {
    jdField_field_1665_of_type_Int = class_1376.field_1542;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.client.view.cubes.CubeMeshBufferContainer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */