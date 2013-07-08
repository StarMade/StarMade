package com.bulletphysics.collision.broadphase;

import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.ManifoldResult;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.util.ObjectArrayList;

public abstract class CollisionAlgorithm
{
  private CollisionAlgorithmCreateFunc createFunc;
  protected Dispatcher dispatcher;
  
  public void init() {}
  
  public void init(CollisionAlgorithmConstructionInfo local_ci)
  {
    this.dispatcher = local_ci.dispatcher1;
  }
  
  public abstract void destroy();
  
  public abstract void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult);
  
  public abstract float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult);
  
  public abstract void getAllContactManifolds(ObjectArrayList<PersistentManifold> paramObjectArrayList);
  
  public final void internalSetCreateFunc(CollisionAlgorithmCreateFunc func)
  {
    this.createFunc = func;
  }
  
  public final CollisionAlgorithmCreateFunc internalGetCreateFunc()
  {
    return this.createFunc;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.CollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */