package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.DispatchFunc;
import com.bulletphysics.collision.broadphase.DispatcherInfo;

public class DefaultNearCallback
  extends NearCallback
{
  private final ManifoldResult contactPointResult = new ManifoldResult();
  
  public void handleCollision(BroadphasePair collisionPair, CollisionDispatcher dispatcher, DispatcherInfo dispatchInfo)
  {
    CollisionObject colObj0 = (CollisionObject)collisionPair.pProxy0.clientObject;
    CollisionObject colObj1 = (CollisionObject)collisionPair.pProxy1.clientObject;
    if (dispatcher.needsCollision(colObj0, colObj1))
    {
      if (collisionPair.algorithm == null) {
        collisionPair.algorithm = dispatcher.findAlgorithm(colObj0, colObj1);
      }
      if (collisionPair.algorithm != null)
      {
        this.contactPointResult.init(colObj0, colObj1);
        if (dispatchInfo.dispatchFunc == DispatchFunc.DISPATCH_DISCRETE)
        {
          collisionPair.algorithm.processCollision(colObj0, colObj1, dispatchInfo, this.contactPointResult);
        }
        else
        {
          float toi = collisionPair.algorithm.calculateTimeOfImpact(colObj0, colObj1, dispatchInfo, this.contactPointResult);
          if (dispatchInfo.timeOfImpact > toi) {
            dispatchInfo.timeOfImpact = toi;
          }
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.DefaultNearCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */