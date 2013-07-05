/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*    */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.util.ObjectPool;
/*    */ 
/*    */ public class CompoundCollisionAlgorithmExt$SwappedCreateFunc extends CollisionAlgorithmCreateFunc
/*    */ {
/* 58 */   private final ObjectPool pool = ObjectPool.get(CompoundCollisionAlgorithmExt.class);
/*    */ 
/*    */   public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*    */   {
/*    */     CompoundCollisionAlgorithmExt localCompoundCollisionAlgorithmExt;
/* 67 */     CompoundCollisionAlgorithmExt.access$002(localCompoundCollisionAlgorithmExt = new CompoundCollisionAlgorithmExt(), 
/* 67 */       paramCollisionObject2);
/* 68 */     CompoundCollisionAlgorithmExt.access$102(localCompoundCollisionAlgorithmExt, paramCollisionObject1);
/*    */ 
/* 70 */     localCompoundCollisionAlgorithmExt.init(paramCollisionAlgorithmConstructionInfo);
/* 71 */     localCompoundCollisionAlgorithmExt.swapped = true;
/* 72 */     return localCompoundCollisionAlgorithmExt;
/*    */   }
/*    */ 
/*    */   public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/*    */   {
/* 79 */     CompoundCollisionAlgorithmExt.access$200(paramCollisionAlgorithm = (CompoundCollisionAlgorithmExt)paramCollisionAlgorithm).pool
/* 79 */       .release(paramCollisionAlgorithm);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt.SwappedCreateFunc
 * JD-Core Version:    0.6.2
 */