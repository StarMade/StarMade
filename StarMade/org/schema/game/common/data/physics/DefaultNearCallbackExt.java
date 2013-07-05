/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.DispatchFunc;
/*    */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    */ import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*    */ import com.bulletphysics.collision.dispatch.NearCallback;
/*    */ 
/*    */ public class DefaultNearCallbackExt extends NearCallback
/*    */ {
/* 13 */   private final ManifoldResult contactPointResult = new ManifoldResult();
/*    */ 
/*    */   public void handleCollision(BroadphasePair paramBroadphasePair, CollisionDispatcher paramCollisionDispatcher, DispatcherInfo paramDispatcherInfo)
/*    */   {
/* 20 */     CollisionObject localCollisionObject1 = (CollisionObject)paramBroadphasePair.pProxy0.clientObject;
/* 21 */     CollisionObject localCollisionObject2 = (CollisionObject)paramBroadphasePair.pProxy1.clientObject;
/*    */ 
/* 24 */     if (paramCollisionDispatcher.needsCollision(localCollisionObject1, localCollisionObject2))
/*    */     {
/* 26 */       if (paramBroadphasePair.algorithm == null)
/*    */       {
/* 32 */         paramBroadphasePair.algorithm = paramCollisionDispatcher.findAlgorithm(localCollisionObject1, localCollisionObject2);
/*    */       }
/* 34 */       if (paramBroadphasePair.algorithm != null)
/*    */       {
/* 36 */         this.contactPointResult.init(localCollisionObject1, localCollisionObject2);
/*    */ 
/* 38 */         if (paramDispatcherInfo.dispatchFunc == DispatchFunc.DISPATCH_DISCRETE)
/*    */         {
/* 41 */           paramBroadphasePair.algorithm.processCollision(localCollisionObject1, localCollisionObject2, paramDispatcherInfo, this.contactPointResult); return;
/*    */         }
/*    */ 
/* 45 */         paramBroadphasePair = paramBroadphasePair.algorithm.calculateTimeOfImpact(localCollisionObject1, localCollisionObject2, paramDispatcherInfo, this.contactPointResult);
/* 46 */         if (paramDispatcherInfo.timeOfImpact > paramBroadphasePair)
/* 47 */           paramDispatcherInfo.timeOfImpact = paramBroadphasePair;
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.DefaultNearCallbackExt
 * JD-Core Version:    0.6.2
 */