import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.SegmentController;

public final class class_199
  extends class_960
  implements class_954
{
  private Vector3f jdField_field_89_of_type_JavaxVecmathVector3f = new Vector3f();
  private SegmentController jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController;
  private class_457 jdField_field_89_of_type_Class_457;
  private Transform jdField_field_90_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private float jdField_field_89_of_type_Float;
  private float jdField_field_90_of_type_Float;
  
  public class_199(class_457 paramclass_457)
  {
    super(paramclass_457.a1());
    new Vector3f();
    new Vector3f();
    new Vector3f();
    this.jdField_field_89_of_type_Float = 25.0F;
    this.jdField_field_90_of_type_Float = 5.0F;
    new class_48();
    new class_35();
    this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController = paramclass_457.a1();
    this.jdField_field_89_of_type_Class_457 = paramclass_457;
  }
  
  public final synchronized Vector3f a83()
  {
    Vector3f localVector3f1 = super.a83();
    Vector3f localVector3f2 = b9();
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.set(this.field_89.getWorldTransform());
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.basis.transform(localVector3f2);
    localVector3f1.add(localVector3f2);
    return localVector3f1;
  }
  
  public final Vector3f b9()
  {
    return new Vector3f(this.jdField_field_89_of_type_JavaxVecmathVector3f.field_615 + this.jdField_field_89_of_type_Class_457.a().field_475 - 8.0F, this.jdField_field_89_of_type_JavaxVecmathVector3f.field_616 + this.jdField_field_89_of_type_Class_457.a().field_476 - 8.0F, this.jdField_field_89_of_type_JavaxVecmathVector3f.field_617 + this.jdField_field_89_of_type_Class_457.a().field_477 - 10.0F);
  }
  
  public final void handleKeyEvent() {}
  
  public final void a84(class_48 paramclass_48)
  {
    paramclass_48.c1(this.jdField_field_89_of_type_Class_457.a());
    this.jdField_field_89_of_type_JavaxVecmathVector3f.set(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if ((!((class_371)this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a14().a18().a79().a60().a51().a45().a36().g()) && (!((class_371)this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a14().a18().a79().a60().a53().g())) {
      return;
    }
    if (((class_371)this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a14().a18().a77().c()) {
      return;
    }
    Vector3f localVector3f1 = new Vector3f(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
    Vector3f localVector3f2 = new Vector3f(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
    Vector3f localVector3f3 = new Vector3f(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
    float f = Keyboard.isKeyDown(42) ? this.jdField_field_89_of_type_Float : this.jdField_field_90_of_type_Float;
    localVector3f1.scale(f * paramclass_941.a());
    localVector3f2.scale(f * paramclass_941.a());
    localVector3f3.scale(f * paramclass_941.a());
    if ((!class_367.field_713.a6()) || (!class_367.field_714.a6()))
    {
      if (class_367.field_713.a6()) {
        this.jdField_field_89_of_type_JavaxVecmathVector3f.add(localVector3f1);
      }
      if (class_367.field_714.a6())
      {
        localVector3f1.scale(-1.0F);
        this.jdField_field_89_of_type_JavaxVecmathVector3f.add(localVector3f1);
      }
    }
    if ((!class_367.field_711.a6()) || (!class_367.field_712.a6()))
    {
      if (class_367.field_711.a6())
      {
        localVector3f3.scale(-1.0F);
        this.jdField_field_89_of_type_JavaxVecmathVector3f.add(localVector3f3);
      }
      if (class_367.field_712.a6()) {
        this.jdField_field_89_of_type_JavaxVecmathVector3f.add(localVector3f3);
      }
    }
    if ((!class_367.field_716.a6()) || (!class_367.field_715.a6()))
    {
      if (class_367.field_716.a6())
      {
        localVector3f2.scale(-1.0F);
        this.jdField_field_89_of_type_JavaxVecmathVector3f.add(localVector3f2);
      }
      if (class_367.field_715.a6()) {
        this.jdField_field_89_of_type_JavaxVecmathVector3f.add(localVector3f2);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_199
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */