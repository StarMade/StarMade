/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*    */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.util.ObjectPool;
/*    */ 
/*    */ public class CompoundCollisionAlgorithmExt$CreateFunc extends CollisionAlgorithmCreateFunc
/*    */ {
/*    */   public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*    */   {
/*    */     CompoundCollisionAlgorithmExt localCompoundCollisionAlgorithmExt;
/* 43 */     CompoundCollisionAlgorithmExt.access$002(localCompoundCollisionAlgorithmExt = new CompoundCollisionAlgorithmExt(), 
/* 43 */       paramCollisionObject1);
/* 44 */     CompoundCollisionAlgorithmExt.access$102(localCompoundCollisionAlgorithmExt, paramCollisionObject2);
/* 45 */     localCompoundCollisionAlgorithmExt.init(paramCollisionAlgorithmConstructionInfo);
/* 46 */     return localCompoundCollisionAlgorithmExt;
/*    */   }
/*    */ 
/*    */   public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/*    */   {
/* 53 */     CompoundCollisionAlgorithmExt.access$200(paramCollisionAlgorithm = (CompoundCollisionAlgorithmExt)paramCollisionAlgorithm).pool
/* 53 */       .release(paramCollisionAlgorithm);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt.CreateFunc
 * JD-Core Version:    0.6.2
 */