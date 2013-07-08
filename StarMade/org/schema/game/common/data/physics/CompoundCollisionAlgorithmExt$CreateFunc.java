package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.util.ObjectPool;

public class CompoundCollisionAlgorithmExt$CreateFunc
  extends CollisionAlgorithmCreateFunc
{
  public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
  {
    CompoundCollisionAlgorithmExt localCompoundCollisionAlgorithmExt;
    CompoundCollisionAlgorithmExt.access$002(localCompoundCollisionAlgorithmExt = new CompoundCollisionAlgorithmExt(), paramCollisionObject1);
    CompoundCollisionAlgorithmExt.access$102(localCompoundCollisionAlgorithmExt, paramCollisionObject2);
    localCompoundCollisionAlgorithmExt.init(paramCollisionAlgorithmConstructionInfo);
    return localCompoundCollisionAlgorithmExt;
  }
  
  public void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
  {
    CompoundCollisionAlgorithmExt.access$200(paramCollisionAlgorithm = (CompoundCollisionAlgorithmExt)paramCollisionAlgorithm).pool.release(paramCollisionAlgorithm);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CompoundCollisionAlgorithmExt.CreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */