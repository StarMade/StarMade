package org.schema.game.common.data.physics;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
import javax.vecmath.Vector3f;

class KinematicCharacterControllerExt$KinematicClosestNotMeRayResultCallback
  extends CollisionWorld.ClosestRayResultCallback
{
  protected CollisionObject field_101;
  
  public KinematicCharacterControllerExt$KinematicClosestNotMeRayResultCallback(CollisionObject paramCollisionObject)
  {
    super(new Vector3f(), new Vector3f());
    this.field_101 = paramCollisionObject;
  }
  
  public float addSingleResult(CollisionWorld.LocalRayResult paramLocalRayResult, boolean paramBoolean)
  {
    if (paramLocalRayResult.collisionObject == this.field_101) {
      return 1.0F;
    }
    return super.addSingleResult(paramLocalRayResult, paramBoolean);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt.KinematicClosestNotMeRayResultCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */