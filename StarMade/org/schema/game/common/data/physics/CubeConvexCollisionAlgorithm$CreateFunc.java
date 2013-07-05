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
/*    */ public class CubeConvexCollisionAlgorithm$CreateFunc extends CollisionAlgorithmCreateFunc
/*    */ {
/* 67 */   private final ObjectPool pool = ObjectPool.get(CubeConvexCollisionAlgorithm.class);
/*    */   public SimplexSolverInterface simplexSolver;
/*    */   public ConvexPenetrationDepthSolver pdSolver;
/*    */ 
/*    */   public CubeConvexCollisionAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
/*    */   {
/* 74 */     this.simplexSolver = paramSimplexSolverInterface;
/* 75 */     this.pdSolver = paramConvexPenetrationDepthSolver;
/*    */   }
/*    */ 
/*    */   public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*    */   {
/*    */     CubeConvexCollisionAlgorithm localCubeConvexCollisionAlgorithm;
/* 82 */     (
/* 83 */       localCubeConvexCollisionAlgorithm = (CubeConvexCollisionAlgorithm)this.pool.get())
/* 83 */       .init(null, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver, this.swapped);
/* 84 */     return localCubeConvexCollisionAlgorithm;
/*    */   }
/*    */ 
/*    */   public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/*    */   {
/* 89 */     this.pool.release((CubeConvexCollisionAlgorithm)paramCollisionAlgorithm);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm.CreateFunc
 * JD-Core Version:    0.6.2
 */