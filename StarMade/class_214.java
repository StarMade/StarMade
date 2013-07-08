import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;

public final class class_214
  extends class_944
{
  final class_253[] jdField_field_9_of_type_ArrayOfClass_253;
  private Vector3f field_10 = new Vector3f();
  private Vector3f field_11;
  Vector3f jdField_field_9_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_214(class_253[] paramArrayOfclass_253)
  {
    super(paramArrayOfclass_253.length);
    this.jdField_field_11_of_type_JavaxVecmathVector3f = new Vector3f();
    this.field_11 = true;
    this.jdField_field_9_of_type_ArrayOfClass_253 = paramArrayOfclass_253;
  }
  
  public final boolean a7(int paramInt, class_941 paramclass_941)
  {
    this.field_9.c(paramInt, this.jdField_field_11_of_type_JavaxVecmathVector3f);
    paramclass_941 = this.jdField_field_9_of_type_ArrayOfClass_253[((int)this.jdField_field_11_of_type_JavaxVecmathVector3f.field_615)];
    if (((int)this.jdField_field_11_of_type_JavaxVecmathVector3f.field_616 == paramclass_941.jdField_field_9_of_type_Int) && (paramclass_941.jdField_field_9_of_type_Boolean))
    {
      this.field_10.set(paramclass_941.jdField_field_9_of_type_ComBulletphysicsLinearmathTransform.origin);
      this.field_9.a7(Math.abs(paramInt - this.jdField_field_11_of_type_Int) % this.field_9.a1(), this.field_10.field_615, this.field_10.field_616, this.field_10.field_617);
      return true;
    }
    System.err.println("[MISSILETRAIL] HEAD DIED");
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_214
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */