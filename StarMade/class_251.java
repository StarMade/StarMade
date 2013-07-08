import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector4f;

public abstract class class_251
{
  public Transform field_104;
  private float field_104;
  public String field_104;
  public Vector4f field_104;
  
  public class_251(Transform paramTransform, String paramString)
  {
    this.jdField_field_104_of_type_JavaxVecmathVector4f = null;
    this.jdField_field_104_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
    this.jdField_field_104_of_type_JavaLangString = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((class_251)paramObject).jdField_field_104_of_type_JavaLangString.equals(this.jdField_field_104_of_type_JavaLangString);
  }
  
  public abstract Transform a();
  
  public int hashCode()
  {
    return this.jdField_field_104_of_type_JavaLangString.hashCode();
  }
  
  public boolean a2()
  {
    return this.jdField_field_104_of_type_Float < 0.3F;
  }
  
  public void a1(class_941 paramclass_941)
  {
    this.jdField_field_104_of_type_Float += paramclass_941.a();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_251
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */