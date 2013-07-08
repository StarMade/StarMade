import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.common.FastMath;
import org.schema.common.util.ByteUtil;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.client.view.cubes.CubeOptOptMesh;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public class class_822
  implements class_965
{
  private static final FloatBuffer jdField_field_98_of_type_JavaNioFloatBuffer = class_329.a(CubeMeshBufferContainer.field_1665 * 24);
  private byte jdField_field_98_of_type_Byte = 2;
  private byte jdField_field_106_of_type_Byte = 0;
  private boolean jdField_field_98_of_type_Boolean;
  
  public final void a() {}
  
  public final void b()
  {
    jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    class_1376.field_1566.field_1578 = CubeOptOptMesh.field_89;
    class_1376.field_1566.b();
    GL11.glDisable(2896);
    GL13.glActiveTexture(33984);
    GL11.glEnable(2884);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glEnableClientState(32884);
    GL11.glVertexPointer(CubeMeshBufferContainer.field_1665, 0, jdField_field_98_of_type_JavaNioFloatBuffer);
    GL11.glDrawArrays(7, 0, 24);
    GL11.glDisableClientState(32884);
    class_1376.field_1566.d();
    GL11.glDisable(3042);
    GL11.glShadeModel(7425);
  }
  
  public final void a41(short paramShort)
  {
    short s1 = paramShort;
    paramShort = this;
    jdField_field_98_of_type_JavaNioFloatBuffer.clear();
    for (int i = 0; i < 6; i++)
    {
      byte b1 = 12;
      byte b2 = 12;
      byte b3 = 12;
      if ((paramShort.jdField_field_98_of_type_Boolean) && (org.schema.game.common.data.element.Element.orientationBackMapping[paramShort.jdField_field_106_of_type_Byte] == i))
      {
        b1 = 15;
        b2 = 3;
        b3 = 3;
      }
      FloatBuffer localFloatBuffer = jdField_field_98_of_type_JavaNioFloatBuffer;
      byte b6 = b3;
      byte b5 = b2;
      byte b4 = b1;
      int m = ElementKeyMap.getInfo(s1).getMaxHitPoints();
      int k = i;
      b3 = paramShort.jdField_field_106_of_type_Byte;
      short s2 = paramShort;
      ElementInformation localElementInformation;
      int n = (localElementInformation = ElementKeyMap.getInfo(s1)).getIndividualSides();
      boolean bool = localElementInformation.isAnimated();
      int i1 = 0;
      if (n == 6)
      {
        b3 = (byte)Math.max(0, Math.min(5, b3));
        if ((!jdField_field_106_of_type_Boolean) && (b3 >= 6)) {
          throw new AssertionError("Orientation wrong: " + b3);
        }
        i1 = CubeMeshBufferContainer.b(k, b3);
      }
      else if (n == 3)
      {
        b3 = (byte)Math.max(0, Math.min(5, b3));
        if ((!jdField_field_106_of_type_Boolean) && (b3 >= 6)) {
          throw new AssertionError("Orientation wrong: " + b3);
        }
        i1 = CubeMeshBufferContainer.a7(k, b3);
      }
      float f1 = m / localElementInformation.getMaxHitPoints();
      m = 0;
      if (f1 < 1.0F) {
        m = FastMath.a4((byte)(int)((1.0F - f1) * 7.0F));
      }
      int j = localElementInformation.getBlockStyle();
      n = (byte)(bool ? 1 : 0);
      byte b7 = (byte)(Math.abs(localElementInformation.getTextureId() + i1) / 256);
      short s3 = (short)((localElementInformation.getTextureId() + i1) % 256);
      if ((j == 1) || (j == 2)) {
        ((class_378)class_384.field_102[(j - 1)][s2.jdField_field_98_of_type_Byte]).a(k, b7, s3, m, n, b4, b6, b5, localFloatBuffer);
      } else {
        for (s2 = 0; s2 < 4; s2 = (short)(s2 + 1))
        {
          float f2 = ByteUtil.a3(2184.0F, b4, b6, b5);
          float f3 = ByteUtil.a2((byte)k, b7, s3, m, n, (byte)0, (byte)0) + class_384.field_777[k][s2];
          localFloatBuffer.put(f2);
          localFloatBuffer.put(f3);
          if (CubeMeshBufferContainer.field_1665 > 2) {
            localFloatBuffer.put(8421504.0F);
          }
          if ((!jdField_field_106_of_type_Boolean) && (2184.0F + s2 >= 49152.0F)) {
            throw new AssertionError("vert index is bigger: " + (2184.0F + s2) + "/49152");
          }
        }
      }
    }
    if ((!jdField_field_106_of_type_Boolean) && (jdField_field_98_of_type_JavaNioFloatBuffer.position() != CubeMeshBufferContainer.field_1665 * 24)) {
      throw new AssertionError(jdField_field_98_of_type_JavaNioFloatBuffer.position());
    }
    jdField_field_98_of_type_JavaNioFloatBuffer.rewind();
    b();
  }
  
  public final void c() {}
  
  public final void a42(byte paramByte)
  {
    this.jdField_field_98_of_type_Byte = paramByte;
  }
  
  public final void b5(byte paramByte)
  {
    this.jdField_field_106_of_type_Byte = paramByte;
  }
  
  public final void a43(boolean paramBoolean)
  {
    this.jdField_field_98_of_type_Boolean = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_822
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */