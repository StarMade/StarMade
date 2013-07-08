package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.DispatchFunc;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.dispatch.NearCallback;

public class DefaultNearCallbackExt
  extends NearCallback
{
  private final ManifoldResult contactPointResult = new ManifoldResult();
  
  public void handleCollision(BroadphasePair paramBroadphasePair, CollisionDispatcher paramCollisionDispatcher, DispatcherInfo paramDispatcherInfo)
  {
    CollisionObject localCollisionObject1 = (CollisionObject)paramBroadphasePair.pProxy0.clientObject;
    CollisionObject localCollisionObject2 = (CollisionObject)paramBroadphasePair.pProxy1.clientObject;
    if (paramCollisionDispatcher.needsCollision(localCollisionObject1, localCollisionObject2))
    {
      if (paramBroadphasePair.algorithm == null) {
        paramBroadphasePair.algorithm = paramCollisionDispatcher.findAlgorithm(localCollisionObject1, localCollisionObject2);
      }
      if (paramBroadphasePair.algorithm != null)
      {
        this.contactPointResult.init(localCollisionObject1, localCollisionObject2);
        if (paramDispatcherInfo.dispatchFunc == DispatchFunc.DISPATCH_DISCRETE)
        {
          paramBroadphasePair.algorithm.processCollision(localCollisionObject1, localCollisionObject2, paramDispatcherInfo, this.contactPointResult);
          return;
        }
        paramBroadphasePair = paramBroadphasePair.algorithm.calculateTimeOfImpact(localCollisionObject1, localCollisionObject2, paramDispatcherInfo, this.contactPointResult);
        if (paramDispatcherInfo.timeOfImpact > paramBroadphasePair) {
          paramDispatcherInfo.timeOfImpact = paramBroadphasePair;
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.DefaultNearCallbackExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */