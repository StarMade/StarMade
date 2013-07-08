package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.util.ObjectArrayList;

public class CollisionDispatcherExt
  extends CollisionDispatcher
{
  public CollisionDispatcherExt(CollisionConfiguration paramCollisionConfiguration)
  {
    super(paramCollisionConfiguration);
    setNearCallback(new DefaultNearCallbackExt());
  }
  
  public void freeCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm)
  {
    CollisionAlgorithmCreateFunc localCollisionAlgorithmCreateFunc = paramCollisionAlgorithm.internalGetCreateFunc();
    paramCollisionAlgorithm.internalSetCreateFunc(null);
    localCollisionAlgorithmCreateFunc.releaseCollisionAlgorithm(paramCollisionAlgorithm);
    paramCollisionAlgorithm.destroy();
  }
  
  public boolean needsResponse(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
  {
    if ((((paramCollisionObject1 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject2.getCollisionShape() instanceof CubesCompoundShape))) || (((paramCollisionObject2 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject1.getCollisionShape() instanceof CubesCompoundShape)))) {
      return false;
    }
    if ((((paramCollisionObject1 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject2.getCollisionShape() instanceof CubeShape))) || (((paramCollisionObject2 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject1.getCollisionShape() instanceof CubeShape)))) {
      return false;
    }
    return super.needsResponse(paramCollisionObject1, paramCollisionObject2);
  }
  
  public void releaseManifold(PersistentManifold paramPersistentManifold)
  {
    super.releaseManifold(paramPersistentManifold);
    assert (checkInternalManifoldDestroyed(paramPersistentManifold));
  }
  
  private boolean checkInternalManifoldDestroyed(PersistentManifold paramPersistentManifold)
  {
    ObjectArrayList localObjectArrayList = getInternalManifoldPointer();
    for (int i = 0; i < localObjectArrayList.size(); i++) {
      if ((PersistentManifold)localObjectArrayList.getQuick(i) == paramPersistentManifold) {
        return false;
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.CollisionDispatcherExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */