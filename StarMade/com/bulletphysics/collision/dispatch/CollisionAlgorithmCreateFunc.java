package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;

public abstract class CollisionAlgorithmCreateFunc
{
  public boolean swapped;
  
  public abstract CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2);
  
  public abstract void releaseCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */