/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   4:    */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*   6:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   7:    */import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*   8:    */import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*   9:    */import com.bulletphysics.util.ObjectPool;
/*  10:    */
/*  77:    */public class CubeCubeCollisionAlgorithm$CreateFunc
/*  78:    */  extends CollisionAlgorithmCreateFunc
/*  79:    */{
/*  80: 80 */  private final ObjectPool pool = ObjectPool.get(CubeCubeCollisionAlgorithm.class);
/*  81:    */  
/*  82:    */  public SimplexSolverInterface simplexSolver;
/*  83:    */  public GjkEpaPenetrationDepthSolver pdSolver;
/*  84:    */  
/*  85:    */  public CubeCubeCollisionAlgorithm$CreateFunc(SimplexSolverInterface paramSimplexSolverInterface, GjkEpaPenetrationDepthSolver paramGjkEpaPenetrationDepthSolver)
/*  86:    */  {
/*  87: 87 */    this.simplexSolver = paramSimplexSolverInterface;
/*  88: 88 */    this.pdSolver = paramGjkEpaPenetrationDepthSolver;
/*  89:    */  }
/*  90:    */  
/*  92:    */  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*  93:    */  {
/*  94:    */    CubeCubeCollisionAlgorithm localCubeCubeCollisionAlgorithm;
/*  95:    */    
/*  96: 96 */    (localCubeCubeCollisionAlgorithm = (CubeCubeCollisionAlgorithm)this.pool.get()).init(null, paramCollisionAlgorithmConstructionInfo, paramCollisionObject1, paramCollisionObject2, this.simplexSolver, this.pdSolver);
/*  97: 97 */    return localCubeCubeCollisionAlgorithm;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/* 101:    */  {
/* 102:102 */    this.pool.release((CubeCubeCollisionAlgorithm)paramCollisionAlgorithm);
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */