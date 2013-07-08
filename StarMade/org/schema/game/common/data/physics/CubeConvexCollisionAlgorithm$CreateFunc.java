package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.util.ObjectPool;

public class CubeConvexCollisionAlgorithm$CreateFunc
  extends CollisionAlgorithmCreateFunc
{
  private final ObjectPool pool = ObjectPool.get(CubeConvexCollisionAlgorithm.class);
  public SimplexSolverInterface simplexSolver;
  public ConvexPenetrationDepthSolver pdSolver;
  
  public CubeConvexCollisionAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver)
  {
    this.simplexSolver = paramSimplexSolverInterface;
    this.pdSolver = paramConvexPenetrationDepthSolver;
  }
  
  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
  {
    CubeConvexCollisionAlgorithm localCubeConvexCollisionAlgorithm;
    (localCubeConvexCollisionAlgorithm = (CubeConvexCollisionAlgorithm)this.pool.get()).init(null, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver, this.swapped);
    return localCubeConvexCollisionAlgorithm;
  }
  
  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
  {
    this.pool.release((CubeConvexCollisionAlgorithm)paramCollisionAlgorithm);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */