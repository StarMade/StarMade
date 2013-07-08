package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
import com.bulletphysics.util.ObjectPool;

public class CubeCubeCollisionAlgorithm$CreateFunc
  extends CollisionAlgorithmCreateFunc
{
  private final ObjectPool pool = ObjectPool.get(CubeCubeCollisionAlgorithm.class);
  public SimplexSolverInterface simplexSolver;
  public GjkEpaPenetrationDepthSolver pdSolver;
  
  public CubeCubeCollisionAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, GjkEpaPenetrationDepthSolver paramGjkEpaPenetrationDepthSolver)
  {
    this.simplexSolver = paramSimplexSolverInterface;
    this.pdSolver = paramGjkEpaPenetrationDepthSolver;
  }
  
  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
  {
    CubeCubeCollisionAlgorithm localCubeCubeCollisionAlgorithm;
    (localCubeCubeCollisionAlgorithm = (CubeCubeCollisionAlgorithm)this.pool.get()).init(null, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver);
    return localCubeCubeCollisionAlgorithm;
  }
  
  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
  {
    this.pool.release((CubeCubeCollisionAlgorithm)paramCollisionAlgorithm);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */