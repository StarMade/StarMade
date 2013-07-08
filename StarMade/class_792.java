import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import org.schema.schine.network.StateInterface;

public final class class_792
  extends class_597
{
  private Transform field_172 = new Transform();
  
  public class_792(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
    this.jdField_field_173_of_type_Float = 15.0F;
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    c4(paramclass_941);
  }
  
  private void c4(class_941 paramclass_941)
  {
    Vector3f localVector3f;
    (localVector3f = new Vector3f(this.jdField_field_173_of_type_JavaxVecmathVector3f)).scale((float)(paramclass_941.a() * 10.0D));
    this.field_172.set(this.jdField_field_173_of_type_ComBulletphysicsLinearmathTransform);
    this.field_172.origin.add(localVector3f);
    a14(this.field_172);
  }
  
  public final void b3(class_941 paramclass_941)
  {
    c4(paramclass_941);
  }
  
  public final byte a21()
  {
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_792
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */