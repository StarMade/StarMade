import com.bulletphysics.linearmath.Transform;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_1386
  implements class_1369
{
  private final class_1384 jdField_field_107_of_type_Class_1384;
  private final class_1430 jdField_field_107_of_type_Class_1430;
  private boolean jdField_field_107_of_type_Boolean;
  private Transform[] jdField_field_107_of_type_ArrayOfComBulletphysicsLinearmathTransform;
  private int jdField_field_107_of_type_Int;
  
  public class_1386(class_1384 paramclass_1384, class_1430 paramclass_1430)
  {
    this.jdField_field_107_of_type_Class_1384 = paramclass_1384;
    this.jdField_field_107_of_type_Class_1430 = paramclass_1430;
  }
  
  public final class_1384 a8()
  {
    return this.jdField_field_107_of_type_Class_1384;
  }
  
  public final void a2()
  {
    if (!this.jdField_field_107_of_type_Boolean)
    {
      class_1386 localclass_1386 = this;
      this.jdField_field_107_of_type_Class_1430.a1();
      localclass_1386.jdField_field_107_of_type_Boolean = true;
    }
    class_1376.field_1570.field_1578 = this;
    class_1376.field_1570.b();
    this.jdField_field_107_of_type_Class_1430.b();
  }
  
  public final void d()
  {
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, 0);
  }
  
  public final void b()
  {
    class_1430.c();
    class_1376.field_1570.d();
  }
  
  public final void a5(class_941 paramclass_941)
  {
    this.jdField_field_107_of_type_Class_1384.b(paramclass_941);
    this.jdField_field_107_of_type_ArrayOfComBulletphysicsLinearmathTransform = this.jdField_field_107_of_type_Class_1384.a();
  }
  
  public final void a(class_1377 paramclass_1377)
  {
    if ((!field_201) && (this.jdField_field_107_of_type_ArrayOfComBulletphysicsLinearmathTransform == null)) {
      throw new AssertionError();
    }
    GlUtil.a36(paramclass_1377, "m_BoneMatrices", this.jdField_field_107_of_type_ArrayOfComBulletphysicsLinearmathTransform);
    GL13.glActiveTexture(33984);
    GL11.glBindTexture(3553, this.jdField_field_107_of_type_Int);
    GlUtil.a35(paramclass_1377, "diffuseMap", 0);
    GL13.glActiveTexture(33984);
  }
  
  public final void a9(int paramInt)
  {
    this.jdField_field_107_of_type_Int = paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1386
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */