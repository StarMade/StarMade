import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;

public final class class_1427
  extends MotionState
{
  private Transform jdField_field_282_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform field_283 = new Transform();
  private Transform field_284 = new Transform();
  private Transform field_285 = new Transform();
  private boolean jdField_field_282_of_type_Boolean = true;
  
  public class_1427()
  {
    this.jdField_field_282_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.field_283.setIdentity();
    this.field_285.setIdentity();
  }
  
  public class_1427(Transform paramTransform)
  {
    this.jdField_field_282_of_type_ComBulletphysicsLinearmathTransform.set(paramTransform);
    this.field_283.setIdentity();
    this.field_285.set(paramTransform);
  }
  
  public final Transform getWorldTransform(Transform paramTransform)
  {
    if (!this.jdField_field_282_of_type_Boolean)
    {
      paramTransform.set(this.field_284);
      paramTransform.mul(this.jdField_field_282_of_type_ComBulletphysicsLinearmathTransform);
      return paramTransform;
    }
    paramTransform.set(this.jdField_field_282_of_type_ComBulletphysicsLinearmathTransform);
    return paramTransform;
  }
  
  public final void setWorldTransform(Transform paramTransform)
  {
    this.jdField_field_282_of_type_ComBulletphysicsLinearmathTransform.set(paramTransform);
    if (!this.jdField_field_282_of_type_Boolean) {
      this.jdField_field_282_of_type_ComBulletphysicsLinearmathTransform.mul(this.field_283);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1427
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */