/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*    */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*    */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*    */ import com.bulletphysics.util.ObjectPool;
/*    */ 
/*    */ public class CubeCubeCollisionAlgorithm$CreateFunc extends CollisionAlgorithmCreateFunc
/*    */ {
/* 75 */   private final ObjectPool pool = ObjectPool.get(CubeCubeCollisionAlgorithm.class);
/*    */   public SimplexSolverInterface simplexSolver;
/*    */   public GjkEpaPenetrationDepthSolver pdSolver;
/*    */ 
/*    */   public CubeCubeCollisionAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, GjkEpaPenetrationDepthSolver paramGjkEpaPenetrationDepthSolver)
/*    */   {
/* 82 */     this.simplexSolver = paramSimplexSolverInterface;
/* 83 */     this.pdSolver = paramGjkEpaPenetrationDepthSolver;
/*    */   }
/*    */ 
/*    */   public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*    */   {
/*    */     CubeCubeCollisionAlgorithm localCubeCubeCollisionAlgorithm;
/* 90 */     (
/* 91 */       localCubeCubeCollisionAlgorithm = (CubeCubeCollisionAlgorithm)this.pool.get())
/* 91 */       .init(null, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver);
/* 92 */     return localCubeCubeCollisionAlgorithm;
/*    */   }
/*    */ 
/*    */   public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/*    */   {
/* 97 */     this.pool.release((CubeCubeCollisionAlgorithm)paramCollisionAlgorithm);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.CreateFunc
 * JD-Core Version:    0.6.2
 */