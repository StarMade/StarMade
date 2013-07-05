/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*    */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*    */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*    */ import com.bulletphysics.util.ObjectPool;
/*    */ 
/*    */ public class ConvexConvexExtAlgorithm$CreateFunc extends CollisionAlgorithmCreateFunc
/*    */ {
/* 17 */   private final ObjectPool pool = ObjectPool.get(ConvexConvexExtAlgorithm.class);
/*    */   public ConvexPenetrationDepthSolver pdSolver;
/*    */   public SimplexSolverInterface simplexSolver;
/*    */ 
/*    */   public ConvexConvexExtAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
/*    */   {
/* 23 */     this.simplexSolver = paramSimplexSolverInterface;
/* 24 */     this.pdSolver = paramConvexPenetrationDepthSolver;
/*    */   }
/*    */ 
/*    */   public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*    */   {
/*    */     ConvexConvexExtAlgorithm localConvexConvexExtAlgorithm;
/* 29 */     (
/* 30 */       localConvexConvexExtAlgorithm = (ConvexConvexExtAlgorithm)this.pool.get())
/* 30 */       .init(paramCollisionAlgorithmConstructionInfo.manifold, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver);
/* 31 */     return localConvexConvexExtAlgorithm;
/*    */   }
/*    */ 
/*    */   public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/*    */   {
/* 36 */     this.pool.release((ConvexConvexExtAlgorithm)paramCollisionAlgorithm);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ConvexConvexExtAlgorithm.CreateFunc
 * JD-Core Version:    0.6.2
 */