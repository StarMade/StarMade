import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;

public class class_201
  extends class_960
{
  protected class_48 field_89;
  private Vector3f jdField_field_89_of_type_JavaxVecmathVector3f = new Vector3f();
  protected SegmentController field_89;
  protected class_457 field_89;
  private Transform jdField_field_90_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private float jdField_field_89_of_type_Float = 13.0F;
  private Vector3f jdField_field_90_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_201(class_457 paramclass_457)
  {
    super(paramclass_457.a1());
    this.jdField_field_89_of_type_Class_48 = new class_48();
    new Vector3f();
    new Vector3f();
    new class_48();
    new class_35();
    this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController = paramclass_457.a1();
    this.jdField_field_89_of_type_Class_457 = paramclass_457;
  }
  
  public final synchronized Vector3f a83()
  {
    Vector3f localVector3f = super.a83();
    Object localObject = this;
    localObject = new Vector3f(((class_201)localObject).jdField_field_89_of_type_JavaxVecmathVector3f.field_615 + ((class_201)localObject).jdField_field_89_of_type_Class_457.a().field_475 - 8.0F, ((class_201)localObject).jdField_field_89_of_type_JavaxVecmathVector3f.field_616 + ((class_201)localObject).jdField_field_89_of_type_Class_457.a().field_476 - 8.0F, ((class_201)localObject).jdField_field_89_of_type_JavaxVecmathVector3f.field_617 + ((class_201)localObject).jdField_field_89_of_type_Class_457.a().field_477 - 8.0F);
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.set(this.field_89.getWorldTransform());
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.basis.transform((Tuple3f)localObject);
    localVector3f.add((Tuple3f)localObject);
    return localVector3f;
  }
  
  public final class_48 a85()
  {
    return this.jdField_field_89_of_type_Class_48;
  }
  
  public final void a2()
  {
    this.jdField_field_89_of_type_Float = 50.0F;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_90_of_type_JavaxVecmathVector3f.set(this.jdField_field_89_of_type_Class_48.field_475, this.jdField_field_89_of_type_Class_48.field_476, this.jdField_field_89_of_type_Class_48.field_477);
    this.jdField_field_90_of_type_JavaxVecmathVector3f.sub(this.jdField_field_89_of_type_JavaxVecmathVector3f);
    float f1;
    if ((f1 = this.jdField_field_90_of_type_JavaxVecmathVector3f.length()) <= 0.0F) {
      return;
    }
    float f2 = this.jdField_field_90_of_type_JavaxVecmathVector3f.length();
    this.jdField_field_90_of_type_JavaxVecmathVector3f.normalize();
    this.jdField_field_90_of_type_JavaxVecmathVector3f.scale(paramclass_941.a() * Math.max(f2 * 3.0F, this.jdField_field_89_of_type_Float));
    if (this.jdField_field_90_of_type_JavaxVecmathVector3f.length() < f1)
    {
      this.jdField_field_89_of_type_JavaxVecmathVector3f.add(this.jdField_field_90_of_type_JavaxVecmathVector3f);
      return;
    }
    this.jdField_field_89_of_type_JavaxVecmathVector3f.set(this.jdField_field_89_of_type_Class_48.field_475, this.jdField_field_89_of_type_Class_48.field_476, this.jdField_field_89_of_type_Class_48.field_477);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_201
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */