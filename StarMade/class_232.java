import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;

public final class class_232
  implements class_1382
{
  private final class_228 jdField_field_116_of_type_Class_228;
  private final Transform jdField_field_116_of_type_ComBulletphysicsLinearmathTransform;
  class_48 jdField_field_116_of_type_Class_48 = new class_48();
  final Vector3f jdField_field_116_of_type_JavaxVecmathVector3f = new Vector3f();
  final Vector3f field_117;
  final Vector3f jdField_field_118_of_type_JavaxVecmathVector3f = new Vector3f();
  public class_48 field_117;
  private final class_48 jdField_field_118_of_type_Class_48 = new class_48();
  
  public class_232(class_228 paramclass_228)
  {
    this.jdField_field_117_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_116_of_type_Class_228 = paramclass_228;
    this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public final Transform getWorldTransform()
  {
    return this.jdField_field_116_of_type_ComBulletphysicsLinearmathTransform;
  }
  
  public final class_48 a(class_48 paramclass_48)
  {
    paramclass_48.b1(this.jdField_field_116_of_type_Class_48);
    return paramclass_48;
  }
  
  public final void a1(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.jdField_field_116_of_type_Class_48.b(paramInt1, paramInt2, paramInt3);
    System.err.println("[CLIENT][MAP][POS] SETTING TO " + this.jdField_field_116_of_type_Class_48);
    a2(paramBoolean);
  }
  
  final void a2(boolean paramBoolean)
  {
    float f1 = this.jdField_field_116_of_type_Class_48.field_475;
    float f2 = this.jdField_field_116_of_type_Class_48.field_476;
    float f3 = this.jdField_field_116_of_type_Class_48.field_477;
    this.jdField_field_117_of_type_JavaxVecmathVector3f.set((f1 - 7.5F) * 6.25F, (f2 - 7.5F) * 6.25F, (f3 - 7.5F) * 6.25F);
    if (paramBoolean)
    {
      getWorldTransform().origin.set(this.jdField_field_117_of_type_JavaxVecmathVector3f);
      this.jdField_field_116_of_type_JavaxVecmathVector3f.set(this.jdField_field_117_of_type_JavaxVecmathVector3f);
    }
    this.jdField_field_117_of_type_Class_48 = class_789.a192(this.jdField_field_116_of_type_Class_48, this.jdField_field_118_of_type_Class_48);
    this.jdField_field_116_of_type_Class_228.a30(this.jdField_field_116_of_type_Class_48);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_232
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */