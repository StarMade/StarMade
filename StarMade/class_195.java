import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.schine.graphicsengine.camera.Camera;

public final class class_195
  extends Camera
  implements class_954
{
  private class_371 jdField_field_89_of_type_Class_371;
  private class_750 jdField_field_89_of_type_Class_750;
  private Transform jdField_field_89_of_type_ComBulletphysicsLinearmathTransform;
  private CollisionWorld.ClosestRayResultCallback jdField_field_89_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
  private Transform field_90;
  
  public class_195(class_371 paramclass_371, class_750 paramclass_750)
  {
    super(new class_299(paramclass_750));
    a82(paramclass_750);
    this.jdField_field_89_of_type_Class_371 = paramclass_371;
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_89_of_type_Class_1010 = new class_1006(this);
  }
  
  public final class_750 a80()
  {
    return this.jdField_field_89_of_type_Class_750;
  }
  
  public final void handleKeyEvent()
  {
    ((class_202)a184()).handleKeyEvent();
  }
  
  protected final int a81(int paramInt)
  {
    return Math.max(0, Math.min(paramInt, 2500));
  }
  
  public final void a82(class_750 paramclass_750)
  {
    this.jdField_field_89_of_type_Class_750 = paramclass_750;
    ((class_960)a184()).a143(paramclass_750);
  }
  
  public final void a12(class_941 paramclass_941)
  {
    Vector3f localVector3f = null;
    if ((this.jdField_field_89_of_type_Class_750.getGravity().b()) || (this.jdField_field_89_of_type_Class_750.getGravity().a()))
    {
      if (this.jdField_field_89_of_type_Class_750.getGravity().a())
      {
        ((class_1006)this.jdField_field_89_of_type_Class_1010).field_123.set(this.jdField_field_89_of_type_Class_750.getGravity().field_928.getWorldTransform());
        this.field_90 = null;
      }
      else
      {
        if (this.field_90 == null) {
          this.field_90 = new Transform(this.jdField_field_89_of_type_Class_750.getGravity().field_928.getWorldTransform());
        }
        ((class_1006)this.jdField_field_89_of_type_Class_1010).field_123.set(this.field_90);
      }
    }
    else
    {
      this.field_90 = null;
      ((class_1006)this.jdField_field_89_of_type_Class_1010).field_123.set(this.jdField_field_89_of_type_ComBulletphysicsLinearmathTransform);
    }
    super.a12(paramclass_941);
    Object localObject = this.jdField_field_89_of_type_Class_750;
    paramclass_941 = this;
    localObject = new Vector3f(((class_750)localObject).a145().origin);
    localVector3f = new Vector3f(paramclass_941.c16(new Vector3f()));
    CubeRayCastResult localCubeRayCastResult;
    (localCubeRayCastResult = new CubeRayCastResult((Vector3f)localObject, localVector3f, Boolean.valueOf(false), null)).setRespectShields(false);
    localCubeRayCastResult.onlyCubeMeshes = true;
    this.jdField_field_89_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = (this.jdField_field_89_of_type_Float > 0.0F ? ((PhysicsExt)paramclass_941.jdField_field_89_of_type_Class_371.a19()).testRayCollisionPoint((Vector3f)localObject, localVector3f, localCubeRayCastResult, false) : null);
    if ((this.jdField_field_89_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (this.jdField_field_89_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()))
    {
      (paramclass_941 = new Vector3f()).sub(c16(new Vector3f()), this.jdField_field_89_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hitPointWorld);
      paramclass_941.scale(1.01F);
      getWorldTransform().origin.sub(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_195
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */