import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public final class class_253
  extends class_944
{
  Transform jdField_field_9_of_type_ComBulletphysicsLinearmathTransform;
  Transform jdField_field_10_of_type_ComBulletphysicsLinearmathTransform;
  boolean jdField_field_9_of_type_Boolean = false;
  private Vector3f jdField_field_9_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_10_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_11 = new Vector3f();
  private Vector3f jdField_field_12_of_type_JavaxVecmathVector3f = new Vector3f();
  boolean jdField_field_10_of_type_Boolean;
  public int field_9;
  private int jdField_field_12_of_type_Int = 10;
  
  public class_253(Transform paramTransform)
  {
    super(512);
    this.jdField_field_9_of_type_Int = -1;
    this.field_11 = true;
    this.jdField_field_9_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
  }
  
  private void a11(Vector3f paramVector3f)
  {
    if (!this.jdField_field_10_of_type_Boolean) {
      a14(paramVector3f, this.jdField_field_9_of_type_JavaxVecmathVector3f);
    }
  }
  
  public final boolean a7(int paramInt, class_941 paramclass_941)
  {
    float f = this.field_9.a3(paramInt);
    this.field_9.a4(paramInt, this.field_11);
    this.field_9.d(paramInt, this.jdField_field_12_of_type_JavaxVecmathVector3f);
    this.field_9.a6(paramInt, f + paramclass_941.a() * 1000.0F);
    this.field_9.a7(paramInt, this.field_11.field_615 + this.jdField_field_12_of_type_JavaxVecmathVector3f.field_615, this.field_11.field_616 + this.jdField_field_12_of_type_JavaxVecmathVector3f.field_616, this.field_11.field_617 + this.jdField_field_12_of_type_JavaxVecmathVector3f.field_617);
    return f < 3000.0F;
  }
  
  public final void a6(class_941 paramclass_941)
  {
    if (this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform == null)
    {
      a11(this.jdField_field_9_of_type_ComBulletphysicsLinearmathTransform.origin);
      this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform = new Transform(this.jdField_field_9_of_type_ComBulletphysicsLinearmathTransform);
    }
    else
    {
      this.jdField_field_10_of_type_JavaxVecmathVector3f.set(this.jdField_field_9_of_type_ComBulletphysicsLinearmathTransform.origin);
      this.jdField_field_10_of_type_JavaxVecmathVector3f.sub(this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform.origin);
      float f1;
      if ((f1 = this.jdField_field_10_of_type_JavaxVecmathVector3f.length()) > class_1352.field_98)
      {
        this.jdField_field_10_of_type_JavaxVecmathVector3f.normalize();
        this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_field_10_of_type_JavaxVecmathVector3f);
        float f2 = class_1352.field_98;
        int i = (int)(f1 / f2);
        float f3 = class_1352.field_98;
        if (i > this.jdField_field_12_of_type_Int) {
          f3 = f1 / this.jdField_field_12_of_type_Int;
        }
        this.jdField_field_10_of_type_JavaxVecmathVector3f.scale(f3);
        while (f2 < f1)
        {
          this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_field_10_of_type_JavaxVecmathVector3f);
          a11(this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform.origin);
          f2 += f3;
        }
        this.jdField_field_10_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_9_of_type_ComBulletphysicsLinearmathTransform);
      }
    }
    super.a6(paramclass_941);
    if ((this.jdField_field_10_of_type_Boolean) && (b1() <= 0)) {
      this.jdField_field_9_of_type_Boolean = false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_253
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */