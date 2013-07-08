/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*  4:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  5:   */import com.bulletphysics.collision.broadphase.DispatchFunc;
/*  6:   */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*  7:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  8:   */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*  9:   */import com.bulletphysics.collision.dispatch.NearCallback;
/* 10:   */
/* 11:   */public class DefaultNearCallbackExt extends NearCallback
/* 12:   */{
/* 13:13 */  private final ManifoldResult contactPointResult = new ManifoldResult();
/* 14:   */  
/* 18:   */  public void handleCollision(com.bulletphysics.collision.broadphase.BroadphasePair paramBroadphasePair, com.bulletphysics.collision.dispatch.CollisionDispatcher paramCollisionDispatcher, DispatcherInfo paramDispatcherInfo)
/* 19:   */  {
/* 20:20 */    CollisionObject localCollisionObject1 = (CollisionObject)paramBroadphasePair.pProxy0.clientObject;
/* 21:21 */    CollisionObject localCollisionObject2 = (CollisionObject)paramBroadphasePair.pProxy1.clientObject;
/* 22:   */    
/* 24:24 */    if (paramCollisionDispatcher.needsCollision(localCollisionObject1, localCollisionObject2))
/* 25:   */    {
/* 26:26 */      if (paramBroadphasePair.algorithm == null)
/* 27:   */      {
/* 32:32 */        paramBroadphasePair.algorithm = paramCollisionDispatcher.findAlgorithm(localCollisionObject1, localCollisionObject2);
/* 33:   */      }
/* 34:34 */      if (paramBroadphasePair.algorithm != null)
/* 35:   */      {
/* 36:36 */        this.contactPointResult.init(localCollisionObject1, localCollisionObject2);
/* 37:   */        
/* 38:38 */        if (paramDispatcherInfo.dispatchFunc == DispatchFunc.DISPATCH_DISCRETE)
/* 39:   */        {
/* 41:41 */          paramBroadphasePair.algorithm.processCollision(localCollisionObject1, localCollisionObject2, paramDispatcherInfo, this.contactPointResult);return;
/* 42:   */        }
/* 43:   */        
/* 45:45 */        paramBroadphasePair = paramBroadphasePair.algorithm.calculateTimeOfImpact(localCollisionObject1, localCollisionObject2, paramDispatcherInfo, this.contactPointResult);
/* 46:46 */        if (paramDispatcherInfo.timeOfImpact > paramBroadphasePair) {
/* 47:47 */          paramDispatcherInfo.timeOfImpact = paramBroadphasePair;
/* 48:   */        }
/* 49:   */      }
/* 50:   */    }
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.DefaultNearCallbackExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */