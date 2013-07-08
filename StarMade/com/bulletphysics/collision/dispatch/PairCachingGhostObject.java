package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.util.ObjectArrayList;

public class PairCachingGhostObject
  extends GhostObject
{
  HashedOverlappingPairCache hashPairCache = new HashedOverlappingPairCache();
  
  public void addOverlappingObjectInternal(BroadphaseProxy otherProxy, BroadphaseProxy thisProxy)
  {
    BroadphaseProxy actualThisProxy = thisProxy != null ? thisProxy : getBroadphaseHandle();
    assert (actualThisProxy != null);
    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
    assert (otherObject != null);
    int index = this.overlappingObjects.indexOf(otherObject);
    if (index == -1)
    {
      this.overlappingObjects.add(otherObject);
      this.hashPairCache.addOverlappingPair(actualThisProxy, otherProxy);
    }
  }
  
  public void removeOverlappingObjectInternal(BroadphaseProxy otherProxy, Dispatcher dispatcher, BroadphaseProxy thisProxy1)
  {
    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
    BroadphaseProxy actualThisProxy = thisProxy1 != null ? thisProxy1 : getBroadphaseHandle();
    assert (actualThisProxy != null);
    assert (otherObject != null);
    int index = this.overlappingObjects.indexOf(otherObject);
    if (index != -1)
    {
      this.overlappingObjects.setQuick(index, this.overlappingObjects.getQuick(this.overlappingObjects.size() - 1));
      this.overlappingObjects.removeQuick(this.overlappingObjects.size() - 1);
      this.hashPairCache.removeOverlappingPair(actualThisProxy, otherProxy, dispatcher);
    }
  }
  
  public HashedOverlappingPairCache getOverlappingPairCache()
  {
    return this.hashPairCache;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.PairCachingGhostObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */