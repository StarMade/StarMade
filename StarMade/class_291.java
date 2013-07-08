import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_291
  implements class_965
{
  private class_1358 jdField_field_98_of_type_Class_1358;
  class_226 jdField_field_98_of_type_Class_226;
  private boolean jdField_field_98_of_type_Boolean = true;
  class_798 jdField_field_98_of_type_Class_798;
  Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  class_48 jdField_field_98_of_type_Class_48 = new class_48();
  
  public class_291(class_798 paramclass_798)
  {
    this.jdField_field_98_of_type_Class_798 = paramclass_798;
    this.jdField_field_98_of_type_Class_226 = new class_226();
    this.jdField_field_98_of_type_Class_1358 = new class_1358(this.jdField_field_98_of_type_Class_226, 8.0F);
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    GlUtil.b3(this.jdField_field_98_of_type_Class_798.a1().getWorldTransformClient());
    this.jdField_field_98_of_type_Class_1358.field_98.set(this.jdField_field_98_of_type_Class_798.a1().getWorldTransformClient());
    this.jdField_field_98_of_type_Class_1358.b();
    GlUtil.c2();
  }
  
  public final void c()
  {
    if (this.jdField_field_98_of_type_Class_1358.field_98 == null) {
      this.jdField_field_98_of_type_Class_1358.field_98 = new Transform();
    }
    this.jdField_field_98_of_type_Class_1358.c();
    this.jdField_field_98_of_type_Boolean = false;
  }
  
  public final boolean a5()
  {
    return this.jdField_field_98_of_type_Class_226.b1() > 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_291
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */