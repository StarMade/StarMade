/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.dispatch.ConvexConvexAlgorithm;
/*    */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*    */ 
/*    */ public class ConvexConvexExtAlgorithm extends ConvexConvexAlgorithm
/*    */ {
/*    */   public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*    */   {
/* 42 */     if (((paramCollisionObject1.getCollisionShape() instanceof CubeShape)) || ((paramCollisionObject2.getCollisionShape() instanceof CubeShape))) {
/* 43 */       return;
/*    */     }
/*    */ 
/* 47 */     super.processCollision(paramCollisionObject1, paramCollisionObject2, paramDispatcherInfo, paramManifoldResult);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ConvexConvexExtAlgorithm
 * JD-Core Version:    0.6.2
 */