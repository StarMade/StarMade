import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_293
  implements class_965
{
  public class_1399 field_98;
  private class_1401 jdField_field_98_of_type_Class_1401 = new class_1401(this.jdField_field_98_of_type_Class_1399);
  private boolean jdField_field_98_of_type_Boolean = true;
  
  public class_293()
  {
    this.jdField_field_98_of_type_Class_1399 = new class_1399();
  }
  
  public final void a22(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback)
  {
    class_969.a1().a83();
    this.jdField_field_98_of_type_Class_1399.a13(new Vector3f(paramClosestRayResultCallback.hitPointWorld), 4.0F);
  }
  
  public final void a23(Vector3f paramVector3f, float paramFloat)
  {
    class_969.a1().a83();
    this.jdField_field_98_of_type_Class_1399.a13(new Vector3f(paramVector3f), paramFloat);
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    this.jdField_field_98_of_type_Class_1401.b();
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Class_1401.c();
    this.jdField_field_98_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_293
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */