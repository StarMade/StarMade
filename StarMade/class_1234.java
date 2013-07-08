import com.bulletphysics.linearmath.Transform;
import java.util.Random;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;

public final class class_1234
  extends class_1254
{
  private static final long serialVersionUID = 1L;
  private long jdField_field_128_of_type_Long;
  private Vector3f jdField_field_128_of_type_JavaxVecmathVector3f = new Vector3f();
  private float jdField_field_128_of_type_Float;
  private class_48 jdField_field_128_of_type_Class_48 = new class_48();
  private Transform jdField_field_128_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Transform jdField_field_162_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private class_48 jdField_field_162_of_type_Class_48 = new class_48();
  private Vector3f jdField_field_162_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_158 = new Vector3f();
  private Vector3f field_262 = new Vector3f();
  
  public class_1234(class_981 paramclass_981)
  {
    super(paramclass_981);
  }
  
  public final boolean c()
  {
    switch (Universe.getRandom().nextInt(7))
    {
    case 0: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 1.0F, 0.0F);
      break;
    case 1: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, -1.0F, 0.0F);
      break;
    case 2: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(1.0F, 0.0F, 0.0F);
      break;
    case 3: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(-1.0F, 0.0F, 0.0F);
      break;
    case 4: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 1.0F);
      break;
    case 5: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, -1.0F);
      break;
    case 6: 
      this.jdField_field_128_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
    }
    this.jdField_field_128_of_type_JavaxVecmathVector3f.scale((float)(Math.random() * 300.0D));
    this.jdField_field_128_of_type_Long = System.currentTimeMillis();
    return false;
  }
  
  public final boolean b()
  {
    return false;
  }
  
  public final boolean d()
  {
    Object localObject1;
    class_670 localclass_670 = (localObject1 = (class_1041)((SegmentController)a7()).getState()).a62().getSector(((SegmentController)a7()).getSectorId());
    class_48 localclass_48 = ((class_1256)a6().field_223).a9();
    Object localObject2;
    if (localclass_670 != null)
    {
      (localObject2 = new class_48(localclass_48)).c1(localclass_670.field_136);
      long l = ((class_1041)localObject1).a59().calculateStartTime();
      localObject1 = class_789.a192(localclass_48, new class_48());
      if (class_791.a19(localclass_48))
      {
        float f = (float)((System.currentTimeMillis() - l) % 1200000L) / 1200000.0F;
        if (class_791.a19(localclass_670.field_136)) {
          this.jdField_field_128_of_type_Float = f;
        } else {
          this.jdField_field_128_of_type_Float = 0.0F;
        }
      }
      else
      {
        this.jdField_field_128_of_type_Float = 0.0F;
      }
      ((class_48)localObject1).a5(16);
      ((class_48)localObject1).a(8, 8, 8);
      ((class_48)localObject1).c1(localclass_670.field_136);
      this.jdField_field_128_of_type_Class_48.b1((class_48)localObject1);
      this.jdField_field_162_of_type_Class_48.b1((class_48)localObject2);
      this.jdField_field_162_of_type_JavaxVecmathVector3f.set(this.jdField_field_162_of_type_Class_48.field_475 * Universe.getSectorSizeWithMargin(), this.jdField_field_162_of_type_Class_48.field_476 * Universe.getSectorSizeWithMargin(), this.jdField_field_162_of_type_Class_48.field_477 * Universe.getSectorSizeWithMargin());
      this.field_262.set(this.jdField_field_128_of_type_Class_48.field_475 * Universe.getSectorSizeWithMargin(), this.jdField_field_128_of_type_Class_48.field_476 * Universe.getSectorSizeWithMargin(), this.jdField_field_128_of_type_Class_48.field_477 * Universe.getSectorSizeWithMargin());
      this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      this.jdField_field_162_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      if (this.jdField_field_128_of_type_Class_48.a4() > 0.0F)
      {
        if (this.jdField_field_162_of_type_Class_48.a4() > 0.0F)
        {
          this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.field_262);
          this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.basis.rotX(6.283186F * this.jdField_field_128_of_type_Float);
          (localObject1 = new Vector3f()).sub(this.jdField_field_162_of_type_JavaxVecmathVector3f, this.field_262);
          this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.origin.add((Tuple3f)localObject1);
          this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.basis.transform(this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.origin);
        }
      }
      else
      {
        this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.basis.rotX(12.566371F * this.jdField_field_128_of_type_Float);
        this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.origin.set(this.jdField_field_162_of_type_JavaxVecmathVector3f);
        this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.basis.transform(this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.origin);
      }
    }
    (localObject2 = ((SegmentController)a7()).getWorldTransform().origin).add(this.jdField_field_128_of_type_JavaxVecmathVector3f);
    this.field_158.sub(this.jdField_field_128_of_type_ComBulletphysicsLinearmathTransform.origin, (Tuple3f)localObject2);
    if (System.currentTimeMillis() - this.jdField_field_128_of_type_Long > 1000L) {
      a12(new class_1113());
    }
    return false;
  }
  
  public final Vector3f a4()
  {
    return this.field_158;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1234
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */