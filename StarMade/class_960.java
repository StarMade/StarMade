import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class class_960
  extends class_962
{
  public class_1382 field_89;
  protected Transform field_89;
  private Vector3f jdField_field_89_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_960(class_1382 paramclass_1382)
  {
    a143(paramclass_1382);
  }
  
  public final class_1382 a142()
  {
    return this.jdField_field_89_of_type_Class_1382;
  }
  
  public Vector3f a83()
  {
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform = this.jdField_field_89_of_type_Class_1382.getWorldTransform();
    if (this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform != null) {
      this.jdField_field_89_of_type_JavaxVecmathVector3f.set(this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.origin);
    } else {
      this.jdField_field_89_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    }
    return this.jdField_field_89_of_type_JavaxVecmathVector3f;
  }
  
  public final void a143(class_1382 paramclass_1382)
  {
    if ((!jdField_field_89_of_type_Boolean) && (paramclass_1382 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_89_of_type_Class_1382 = paramclass_1382;
  }
  
  public void a12(class_941 paramclass_941) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_960
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */