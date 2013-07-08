/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  4:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*  5:   */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*  6:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  7:   */import com.bulletphysics.util.ObjectPool;
/*  8:   */
/* 37:   */public class CompoundCollisionAlgorithmExt$CreateFunc
/* 38:   */  extends CollisionAlgorithmCreateFunc
/* 39:   */{
/* 40:   */  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/* 41:   */  {
/* 42:   */    CompoundCollisionAlgorithmExt localCompoundCollisionAlgorithmExt;
/* 43:43 */    CompoundCollisionAlgorithmExt.access$002(localCompoundCollisionAlgorithmExt = new CompoundCollisionAlgorithmExt(), paramCollisionObject1);
/* 44:44 */    CompoundCollisionAlgorithmExt.access$102(localCompoundCollisionAlgorithmExt, paramCollisionObject2);
/* 45:45 */    localCompoundCollisionAlgorithmExt.init(paramCollisionAlgorithmConstructionInfo);
/* 46:46 */    return localCompoundCollisionAlgorithmExt;
/* 47:   */  }
/* 48:   */  
/* 51:   */  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
/* 52:   */  {
/* 53:53 */    CompoundCollisionAlgorithmExt.access$200(paramCollisionAlgorithm = (CompoundCollisionAlgorithmExt)paramCollisionAlgorithm).pool.release(paramCollisionAlgorithm);
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */