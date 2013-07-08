package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.util.ObjectArrayList;

public class EmptyAlgorithm
  extends CollisionAlgorithm
{
  private static final EmptyAlgorithm INSTANCE = new EmptyAlgorithm();
  
  public void destroy() {}
  
  public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) {}
  
  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
  {
    return 1.0F;
  }
  
  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray) {}
  
  public static class CreateFunc
    extends CollisionAlgorithmCreateFunc
  {
    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo local_ci, CollisionObject body0, CollisionObject body1)
    {
      return EmptyAlgorithm.INSTANCE;
    }
    
    public void releaseCollisionAlgorithm(CollisionAlgorithm algo) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.EmptyAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */