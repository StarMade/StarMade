package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ConvexConvexAlgorithm;
import com.bulletphysics.collision.dispatch.ManifoldResult;

public class ConvexConvexExtAlgorithm
  extends ConvexConvexAlgorithm
{
  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
  {
    if (((paramCollisionObject1.getCollisionShape() instanceof CubeShape)) || ((paramCollisionObject2.getCollisionShape() instanceof CubeShape))) {
      return;
    }
    super.processCollision(paramCollisionObject1, paramCollisionObject2, paramDispatcherInfo, paramManifoldResult);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ConvexConvexExtAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */