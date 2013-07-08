import java.util.Random;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_1360
  extends class_944
{
  private float jdField_field_10_of_type_Float = 30.0F;
  private float jdField_field_11_of_type_Float = 30.0F;
  private float field_12 = this.jdField_field_11_of_type_Float * this.jdField_field_11_of_type_Float;
  private float field_13 = 3.3F;
  private Random jdField_field_9_of_type_JavaUtilRandom = new Random();
  float jdField_field_9_of_type_Float = 0.0F;
  private float field_272;
  private float field_273;
  private Vector3f jdField_field_9_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_10_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_11_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public final boolean a7(int paramInt, class_941 paramclass_941)
  {
    float f1 = this.field_272 / this.field_273 / 3.0F;
    float f2 = this.field_9.a3(paramInt);
    this.field_9.a4(paramInt, this.jdField_field_10_of_type_JavaxVecmathVector3f);
    this.jdField_field_11_of_type_JavaxVecmathVector3f.sub(this.jdField_field_10_of_type_JavaxVecmathVector3f, class_969.a1().a83());
    if (this.jdField_field_11_of_type_JavaxVecmathVector3f.lengthSquared() > this.field_12 * 2.0F) {
      return false;
    }
    if ((this.jdField_field_10_of_type_JavaxVecmathVector3f.field_615 - class_969.a1().a83().field_615) * this.jdField_field_9_of_type_JavaxVecmathVector3f.field_615 + (this.jdField_field_10_of_type_JavaxVecmathVector3f.field_616 - class_969.a1().a83().field_616) * this.jdField_field_9_of_type_JavaxVecmathVector3f.field_616 + (this.jdField_field_10_of_type_JavaxVecmathVector3f.field_617 - class_969.a1().a83().field_617) * this.jdField_field_9_of_type_JavaxVecmathVector3f.field_617 <= 0.0D) {
      return false;
    }
    this.field_9.a6(paramInt, (float)(f2 + f1 * paramclass_941.a() * 1000.0D));
    return f2 < 1000.0F;
  }
  
  public final void a6(class_941 paramclass_941)
  {
    class_969.a1().a182(this.jdField_field_9_of_type_JavaxVecmathVector3f);
    super.a6(paramclass_941);
    this.jdField_field_9_of_type_Float += paramclass_941.a();
  }
  
  public final void a12(float paramFloat1, float paramFloat2)
  {
    Camera localCamera;
    if ((localCamera = class_969.a1()) == null) {
      return;
    }
    this.field_272 = paramFloat1;
    this.field_273 = paramFloat2;
    Vector3f localVector3f1 = new Vector3f(0.0F, 0.0F, 0.0F);
    paramFloat1 = (int)(this.field_13 * (paramFloat1 / paramFloat2));
    for (paramFloat2 = 0; paramFloat2 < paramFloat1; paramFloat2++)
    {
      Vector3f localVector3f2 = new Vector3f(localCamera.a83());
      Vector3f localVector3f3 = new Vector3f(localCamera.c10());
      Vector3f localVector3f4 = new Vector3f(localCamera.f5());
      Vector3f localVector3f5 = new Vector3f(localCamera.d7());
      localVector3f3.scale(this.jdField_field_11_of_type_Float);
      localVector3f4.scale(this.jdField_field_9_of_type_JavaUtilRandom.nextFloat() * this.jdField_field_10_of_type_Float - this.jdField_field_10_of_type_Float / 2.0F);
      localVector3f5.scale(this.jdField_field_9_of_type_JavaUtilRandom.nextFloat() * this.jdField_field_10_of_type_Float - this.jdField_field_10_of_type_Float / 2.0F);
      localVector3f2.add(localVector3f3);
      localVector3f2.add(localVector3f4);
      localVector3f2.add(localVector3f5);
      a14(localVector3f2, localVector3f1);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1360
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */