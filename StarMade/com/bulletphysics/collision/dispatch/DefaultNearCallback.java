/*  1:   */package com.bulletphysics.collision.dispatch;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*  4:   */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*  5:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  6:   */import com.bulletphysics.collision.broadphase.DispatchFunc;
/*  7:   */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*  8:   */
/* 34:   */public class DefaultNearCallback
/* 35:   */  extends NearCallback
/* 36:   */{
/* 37:37 */  private final ManifoldResult contactPointResult = new ManifoldResult();
/* 38:   */  
/* 39:   */  public void handleCollision(BroadphasePair collisionPair, CollisionDispatcher dispatcher, DispatcherInfo dispatchInfo) {
/* 40:40 */    CollisionObject colObj0 = (CollisionObject)collisionPair.pProxy0.clientObject;
/* 41:41 */    CollisionObject colObj1 = (CollisionObject)collisionPair.pProxy1.clientObject;
/* 42:   */    
/* 43:43 */    if (dispatcher.needsCollision(colObj0, colObj1))
/* 44:   */    {
/* 45:45 */      if (collisionPair.algorithm == null) {
/* 46:46 */        collisionPair.algorithm = dispatcher.findAlgorithm(colObj0, colObj1);
/* 47:   */      }
/* 48:   */      
/* 49:49 */      if (collisionPair.algorithm != null)
/* 50:   */      {
/* 51:51 */        this.contactPointResult.init(colObj0, colObj1);
/* 52:   */        
/* 53:53 */        if (dispatchInfo.dispatchFunc == DispatchFunc.DISPATCH_DISCRETE)
/* 54:   */        {
/* 55:55 */          collisionPair.algorithm.processCollision(colObj0, colObj1, dispatchInfo, this.contactPointResult);
/* 56:   */        }
/* 57:   */        else
/* 58:   */        {
/* 59:59 */          float toi = collisionPair.algorithm.calculateTimeOfImpact(colObj0, colObj1, dispatchInfo, this.contactPointResult);
/* 60:60 */          if (dispatchInfo.timeOfImpact > toi) {
/* 61:61 */            dispatchInfo.timeOfImpact = toi;
/* 62:   */          }
/* 63:   */        }
/* 64:   */      }
/* 65:   */    }
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.DefaultNearCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */