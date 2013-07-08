package org.schema.game.common.data.physics;

import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
import javax.vecmath.Vector3f;

public class ClosestConvexResultCallbackExt
  extends CollisionWorld.ClosestConvexResultCallback
{
  public Object userData;
  public boolean checkHasHitOnly;
  
  public ClosestConvexResultCallbackExt(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    super(paramVector3f1, paramVector3f2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ClosestConvexResultCallbackExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */