import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.common.FastMath;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1356
  extends class_946
  implements class_1369
{
  private class_1377 jdField_field_98_of_type_Class_1377 = class_1376.field_1552;
  private static class_1395 jdField_field_98_of_type_Class_1395;
  private int jdField_field_98_of_type_Int;
  private static Matrix4f[] jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f;
  private static FloatBuffer field_108 = BufferUtils.createFloatBuffer(16);
  
  public class_1356(class_944 paramclass_944)
  {
    super(paramclass_944, 0.1F);
    if ((!jdField_field_106_of_type_Boolean) && (class_1376.field_1552 == null)) {
      throw new AssertionError();
    }
    jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f = new Matrix4f[300];
    for (paramclass_944 = 0; paramclass_944 < jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length; paramclass_944++)
    {
      jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[paramclass_944] = new Matrix4f();
      jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[paramclass_944].setIdentity();
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Class_944.b1() > 0)
    {
      this.jdField_field_98_of_type_Class_1377.field_1578 = this;
      this.jdField_field_98_of_type_Class_1377.b();
      super.b();
      this.jdField_field_98_of_type_Class_1377.d();
    }
  }
  
  protected final int a52(int paramInt, class_950 paramclass_950)
  {
    paramclass_950.a4(paramInt, this.jdField_field_98_of_type_JavaxVecmathVector3f);
    if (!class_969.a1().a187(this.jdField_field_98_of_type_JavaxVecmathVector3f)) {
      return 0;
    }
    this.jdField_field_98_of_type_JavaxVecmathVector4f.set(this.jdField_field_98_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_98_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_98_of_type_JavaxVecmathVector3f.field_617, paramclass_950.a3(paramInt));
    paramclass_950.b(paramInt, this.jdField_field_106_of_type_JavaxVecmathVector3f);
    this.jdField_field_106_of_type_JavaxVecmathVector4f.set(this.jdField_field_106_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_106_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_106_of_type_JavaxVecmathVector3f.field_617, 0.0F);
    for (paramInt = 0; paramInt < 4; paramInt++)
    {
      GlUtil.a25(jdField_field_98_of_type_JavaNioFloatBuffer, this.jdField_field_98_of_type_JavaxVecmathVector4f);
      this.jdField_field_106_of_type_JavaxVecmathVector4f.field_599 = jdField_field_98_of_type_ArrayOfFloat[paramInt];
      GlUtil.a25(jdField_field_106_of_type_JavaNioFloatBuffer, this.jdField_field_106_of_type_JavaxVecmathVector4f);
    }
    return 4;
  }
  
  public final void d()
  {
    GL11.glBindTexture(3553, 0);
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Boolean = true;
    if (jdField_field_98_of_type_Class_1395 == null) {
      jdField_field_98_of_type_Class_1395 = class_969.a2().a5("star").a153().a1();
    }
    super.c();
  }
  
  public final void a13(class_1377 paramclass_1377)
  {
    field_108.rewind();
    jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[FastMath.b1(this.jdField_field_98_of_type_Int - jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 2, jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 1)].store(field_108);
    field_108.rewind();
    GlUtil.c5(paramclass_1377, "oldModelViewMatrix", field_108);
    GlUtil.a42(paramclass_1377, "Param", new Vector4f(1.0F, 0.1F, 0.15F, 0.0005F));
    GL11.glBindTexture(3553, jdField_field_98_of_type_Class_1395.field_1592);
    GlUtil.a35(paramclass_1377, "Texture", 0);
    if ((paramclass_1377 = (class_1360)this.jdField_field_98_of_type_Class_944).field_9 > 0.015F)
    {
      jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f[this.jdField_field_98_of_type_Int].load(class_969.field_1259);
      this.jdField_field_98_of_type_Int = FastMath.b1(this.jdField_field_98_of_type_Int + 1, jdField_field_98_of_type_ArrayOfOrgLwjglUtilVectorMatrix4f.length - 1);
      paramclass_1377.field_9 = 0.0F;
    }
  }
  
  static
  {
    jdField_field_106_of_type_Boolean = !zb.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1356
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */