/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  4:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*  5:   */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*  6:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  7:   */import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*  8:   */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*  9:   */import com.bulletphysics.util.ObjectPool;
/* 10:   */
/* 14:   */public class ConvexConvexExtAlgorithm$CreateFunc
/* 15:   */  extends CollisionAlgorithmCreateFunc
/* 16:   */{
/* 17:17 */  private final ObjectPool pool = ObjectPool.get(ConvexConvexExtAlgorithm.class);
/* 18:   */  public ConvexPenetrationDepthSolver pdSolver;
/* 19:   */  public SimplexSolverInterface simplexSolver;
/* 20:   */  
/* 21:   */  public ConvexConvexExtAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
/* 22:   */  {
/* 23:23 */    this.simplexSolver = paramSimplexSolverInterface;
/* 24:24 */    this.pdSolver = paramConvexPenetrationDepthSolver;
/* 25:   */  }
/* 26:   */  
/* 27:   */  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/* 28:   */  {
/* 29:   */    ConvexConvexExtAlgorithm localConvexConvexExtAlgorithm;
/* 30:30 */    (localConvexConvexExtAlgorithm = (ConvexConvexExtAlgorithm)this.pool.get()).init(paramCollisionAlgorithmConstructionInfo.manifold, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver);
/* 31:31 */    return localConvexConvexExtAlgorithm;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/* 35:   */  {
/* 36:36 */    this.pool.release((ConvexConvexExtAlgorithm)paramCollisionAlgorithm);
/* 37:   */  }
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ConvexConvexExtAlgorithm.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */