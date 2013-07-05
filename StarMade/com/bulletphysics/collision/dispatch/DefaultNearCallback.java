/*    */ package com.bulletphysics.collision.dispatch;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.DispatchFunc;
/*    */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    */ 
/*    */ public class DefaultNearCallback extends NearCallback
/*    */ {
/* 37 */   private final ManifoldResult contactPointResult = new ManifoldResult();
/*    */ 
/*    */   public void handleCollision(BroadphasePair collisionPair, CollisionDispatcher dispatcher, DispatcherInfo dispatchInfo) {
/* 40 */     CollisionObject colObj0 = (CollisionObject)collisionPair.pProxy0.clientObject;
/* 41 */     CollisionObject colObj1 = (CollisionObject)collisionPair.pProxy1.clientObject;
/*    */ 
/* 43 */     if (dispatcher.needsCollision(colObj0, colObj1))
/*    */     {
/* 45 */       if (collisionPair.algorithm == null) {
/* 46 */         collisionPair.algorithm = dispatcher.findAlgorithm(colObj0, colObj1);
/*    */       }
/*    */ 
/* 49 */       if (collisionPair.algorithm != null)
/*    */       {
/* 51 */         this.contactPointResult.init(colObj0, colObj1);
/*    */ 
/* 53 */         if (dispatchInfo.dispatchFunc == DispatchFunc.DISPATCH_DISCRETE)
/*    */         {
/* 55 */           collisionPair.algorithm.processCollision(colObj0, colObj1, dispatchInfo, this.contactPointResult);
/*    */         }
/*    */         else
/*    */         {
/* 59 */           float toi = collisionPair.algorithm.calculateTimeOfImpact(colObj0, colObj1, dispatchInfo, this.contactPointResult);
/* 60 */           if (dispatchInfo.timeOfImpact > toi)
/* 61 */             dispatchInfo.timeOfImpact = toi;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.DefaultNearCallback
 * JD-Core Version:    0.6.2
 */