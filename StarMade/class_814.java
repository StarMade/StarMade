import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_814
  implements class_1369
{
  private class_1395 jdField_field_107_of_type_Class_1395;
  private static FloatBuffer jdField_field_107_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  private static FloatBuffer field_201 = BufferUtils.createFloatBuffer(16);
  private static FloatBuffer field_202 = BufferUtils.createFloatBuffer(16);
  
  public class_814()
  {
    class_814 localclass_814 = this;
    if ((!jdField_field_107_of_type_Boolean) && (class_333.field_134 == null)) {
      throw new AssertionError();
    }
    localclass_814.jdField_field_107_of_type_Class_1395 = class_333.field_134;
  }
  
  public final class_1395 a1()
  {
    return this.jdField_field_107_of_type_Class_1395;
  }
  
  public final void d() {}
  
  public final void a(class_1377 paramclass_1377)
  {
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    class_969.field_1260.store(jdField_field_107_of_type_JavaNioFloatBuffer);
    jdField_field_107_of_type_JavaNioFloatBuffer.rewind();
    field_201.rewind();
    class_969.field_1259.store(field_201);
    field_201.rewind();
    Matrix4f localMatrix4f1;
    (localMatrix4f1 = new Matrix4f()).load(jdField_field_107_of_type_JavaNioFloatBuffer);
    Matrix4f localMatrix4f2;
    (localMatrix4f2 = new Matrix4f()).load(field_201);
    Matrix4f localMatrix4f3 = new Matrix4f();
    Matrix4f.mul(localMatrix4f1, localMatrix4f2, localMatrix4f3);
    localMatrix4f3.store(field_202);
    field_202.rewind();
    GlUtil.c5(paramclass_1377, "MVP", field_202);
    GlUtil.a35(paramclass_1377, "CubeMapTex", 0);
    GlUtil.a41(paramclass_1377, "MaterialColor", 1.0F, 1.0F, 1.0F, 1.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_814
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */