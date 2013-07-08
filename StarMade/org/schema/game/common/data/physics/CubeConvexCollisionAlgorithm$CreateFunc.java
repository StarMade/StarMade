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
/* 64:   */public class CubeConvexCollisionAlgorithm$CreateFunc
/* 65:   */  extends CollisionAlgorithmCreateFunc
/* 66:   */{
/* 67:67 */  private final ObjectPool pool = ObjectPool.get(CubeConvexCollisionAlgorithm.class);
/* 68:   */  
/* 69:   */  public SimplexSolverInterface simplexSolver;
/* 70:   */  public ConvexPenetrationDepthSolver pdSolver;
/* 71:   */  
/* 72:   */  public CubeConvexCollisionAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
/* 73:   */  {
/* 74:74 */    this.simplexSolver = paramSimplexSolverInterface;
/* 75:75 */    this.pdSolver = paramConvexPenetrationDepthSolver;
/* 76:   */  }
/* 77:   */  
/* 79:   */  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/* 80:   */  {
/* 81:   */    CubeConvexCollisionAlgorithm localCubeConvexCollisionAlgorithm;
/* 82:   */    
/* 83:83 */    (localCubeConvexCollisionAlgorithm = (CubeConvexCollisionAlgorithm)this.pool.get()).init(null, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver, this.swapped);
/* 84:84 */    return localCubeConvexCollisionAlgorithm;
/* 85:   */  }
/* 86:   */  
/* 87:   */  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/* 88:   */  {
/* 89:89 */    this.pool.release((CubeConvexCollisionAlgorithm)paramCollisionAlgorithm);
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */