import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_818
  implements class_1369
{
  private class_1433 jdField_field_107_of_type_Class_1433 = new class_1433(20.0F);
  private static FloatBuffer jdField_field_107_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static FloatBuffer jdField_field_201_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static FloatBuffer field_202 = BufferUtils.createFloatBuffer(16);
  private static FloatBuffer field_203 = BufferUtils.createFloatBuffer(16);
  private static int[] jdField_field_107_of_type_ArrayOfInt = { 255, 253, 250, 246, 241, 220, 210, 200, 180, 170, 160, 135, 120, 110, 90, 85, 70, 56, 43, 32, 24, 18, 14, 11, 8, 6, 4, 3, 2, 1, 0, 0 };
  private int jdField_field_107_of_type_Int = 0;
  private int jdField_field_201_of_type_Int = 30;
  
  public class_818()
  {
    a2();
  }
  
  private static void a2()
  {
    ByteBuffer localByteBuffer = BufferUtils.createByteBuffer(jdField_field_107_of_type_ArrayOfInt.length << 2);
    for (int i = 0; i < jdField_field_107_of_type_ArrayOfInt.length; i++) {
      localByteBuffer.putInt(jdField_field_107_of_type_ArrayOfInt[i]);
    }
    localByteBuffer.flip();
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    float f = 6.8F + this.jdField_field_107_of_type_Class_1433.a1() * 0.6F;
    if (class_949.field_1240.b1()) {
      f *= 2.0F;
    }
    GlUtil.a33(paramclass_1377, "Size2", f);
    if (this.jdField_field_107_of_type_Int <= 0)
    {
      field_203.rewind();
      field_202.rewind();
      field_203.put(field_202);
      field_203.rewind();
    }
    this.jdField_field_107_of_type_Int += 1;
    if (this.jdField_field_107_of_type_Int >= this.jdField_field_201_of_type_Int) {
      this.jdField_field_107_of_type_Int = 0;
    }
    jdField_field_201_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_201_of_type_JavaNioFloatBuffer.put(jdField_field_107_of_type_JavaNioFloatBuffer);
    jdField_field_201_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1260.store(jdField_field_107_of_type_JavaNioFloatBuffer);
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    field_202.rewind();
    class_969.field_1259.store(field_202);
    field_202.rewind();
    GlUtil.c5(paramclass_1377, "ProjectionMatrix", jdField_field_107_of_type_JavaNioFloatBuffer);
    GlUtil.c5(paramclass_1377, "ModelViewMatrix", field_202);
    Vector3f localVector3f = class_969.a1().a83();
    GlUtil.a40(paramclass_1377, "CamPosition", localVector3f);
    GL11.glBindTexture(3553, class_969.a2().a5("starSprite").a153().a1().field_1592);
    GlUtil.a35(paramclass_1377, "starTex", 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_818
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */