import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_254
  implements class_965
{
  private class_253[] jdField_field_98_of_type_ArrayOfClass_253;
  private class_1359 jdField_field_98_of_type_Class_1359;
  private class_214 jdField_field_98_of_type_Class_214;
  private class_1358 jdField_field_98_of_type_Class_1358;
  private boolean jdField_field_98_of_type_Boolean = true;
  private class_371 jdField_field_98_of_type_Class_371;
  public final ArrayList field_98;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_254(class_371 paramclass_371)
  {
    this.jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
    this.jdField_field_98_of_type_ArrayOfClass_253 = new class_253[32];
    for (paramclass_371 = 0; paramclass_371 < 32; paramclass_371++) {
      this.jdField_field_98_of_type_ArrayOfClass_253[paramclass_371] = new class_253(class_969.a1().getWorldTransform());
    }
    this.jdField_field_98_of_type_Class_1359 = new class_1359();
    this.jdField_field_98_of_type_Class_214 = new class_214(this.jdField_field_98_of_type_ArrayOfClass_253);
    this.jdField_field_98_of_type_Class_1358 = new class_1358(this.jdField_field_98_of_type_Class_214, 16.0F);
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean) {
      c();
    }
    this.jdField_field_98_of_type_Class_1358.b();
    for (int i = 0; i < 32; i++) {
      if (this.jdField_field_98_of_type_ArrayOfClass_253[i].jdField_field_9_of_type_Boolean)
      {
        this.jdField_field_98_of_type_Class_1359.a62(this.jdField_field_98_of_type_ArrayOfClass_253[i]);
        this.jdField_field_98_of_type_Class_1359.b();
      }
    }
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Class_1359.c();
    this.jdField_field_98_of_type_Boolean = false;
  }
  
  private void a14(Transform paramTransform)
  {
    for (int i = 0; i < 32; i++) {
      if (this.jdField_field_98_of_type_ArrayOfClass_253[i].jdField_field_9_of_type_ComBulletphysicsLinearmathTransform == paramTransform)
      {
        this.jdField_field_98_of_type_ArrayOfClass_253[i].jdField_field_10_of_type_Boolean = true;
        this.jdField_field_98_of_type_Class_371.a27().a91().a23(this.jdField_field_98_of_type_ArrayOfClass_253[i].jdField_field_9_of_type_ComBulletphysicsLinearmathTransform.origin, 50.0F);
        (paramTransform = this.jdField_field_98_of_type_Class_214).jdField_field_9_of_type_ArrayOfClass_253[i].jdField_field_9_of_type_Int = ((paramTransform.jdField_field_9_of_type_ArrayOfClass_253[i].jdField_field_9_of_type_Int + 1) % 100000);
        return;
      }
    }
  }
  
  public final void a1(class_941 paramclass_941)
  {
    synchronized (this.jdField_field_98_of_type_JavaUtilArrayList)
    {
      while (!this.jdField_field_98_of_type_JavaUtilArrayList.isEmpty())
      {
        Object localObject1;
        if ((localObject1 = (class_358)this.jdField_field_98_of_type_JavaUtilArrayList.remove(0)).jdField_field_708_of_type_Boolean)
        {
          Object localObject2 = ((class_358)localObject1).jdField_field_708_of_type_ComBulletphysicsLinearmathTransform;
          localObject1 = this;
          for (Object localObject3 = 0;; localObject3++)
          {
            if (localObject3 >= 32) {
              break label204;
            }
            if (!localObject1.jdField_field_98_of_type_ArrayOfClass_253[localObject3].jdField_field_9_of_type_Boolean)
            {
              System.err.println("[MISSILE] STARTING NEW TRAIL " + ((Transform)localObject2).origin);
              Object localObject4 = localObject2;
              (localObject2 = localObject1.jdField_field_98_of_type_ArrayOfClass_253[localObject3]).b2();
              ((class_253)localObject2).jdField_field_9_of_type_ComBulletphysicsLinearmathTransform = localObject4;
              ((class_253)localObject2).jdField_field_10_of_type_ComBulletphysicsLinearmathTransform = null;
              ((class_253)localObject2).jdField_field_10_of_type_Boolean = false;
              ((class_253)localObject2).jdField_field_9_of_type_Boolean = true;
              localObject4 = localObject3;
              class_214 tmp143_136 = ((class_254)localObject1).jdField_field_98_of_type_Class_214;
              i = tmp143_136.a14((localObject2 = tmp143_136).jdField_field_9_of_type_ArrayOfClass_253[localObject4].jdField_field_9_of_type_ComBulletphysicsLinearmathTransform.origin, ((class_214)localObject2).jdField_field_9_of_type_JavaxVecmathVector3f);
              localObject2.jdField_field_9_of_type_ArrayOfClass_253[localObject4].jdField_field_9_of_type_Int = i;
              ((class_944)localObject2).field_9.c1(i, localObject4, i, 0.0F);
              break;
            }
          }
        }
        else
        {
          label204:
          a14(i.jdField_field_708_of_type_ComBulletphysicsLinearmathTransform);
        }
      }
    }
    for (int i = 0; i < 32; i++) {
      if (this.jdField_field_98_of_type_ArrayOfClass_253[i].jdField_field_9_of_type_Boolean) {
        this.jdField_field_98_of_type_ArrayOfClass_253[i].a6(paramclass_941);
      }
    }
    this.jdField_field_98_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
    if (this.jdField_field_98_of_type_Class_1359 != null) {
      this.jdField_field_98_of_type_Class_1359.a1(paramclass_941);
    }
    this.jdField_field_98_of_type_Class_214.a6(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_254
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */