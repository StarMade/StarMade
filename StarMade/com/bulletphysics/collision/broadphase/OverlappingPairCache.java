package com.bulletphysics.collision.broadphase;

import com.bulletphysics.util.ObjectArrayList;

public abstract class OverlappingPairCache
  extends OverlappingPairCallback
{
  public abstract ObjectArrayList<BroadphasePair> getOverlappingPairArray();
  
  public abstract void cleanOverlappingPair(BroadphasePair paramBroadphasePair, Dispatcher paramDispatcher);
  
  public abstract int getNumOverlappingPairs();
  
  public abstract void cleanProxyFromPairs(BroadphaseProxy paramBroadphaseProxy, Dispatcher paramDispatcher);
  
  public abstract void setOverlapFilterCallback(OverlapFilterCallback paramOverlapFilterCallback);
  
  public abstract void processAllOverlappingPairs(OverlapCallback paramOverlapCallback, Dispatcher paramDispatcher);
  
  public abstract BroadphasePair findPair(BroadphaseProxy paramBroadphaseProxy1, BroadphaseProxy paramBroadphaseProxy2);
  
  public abstract boolean hasDeferredRemoval();
  
  public abstract void setInternalGhostPairCallback(OverlappingPairCallback paramOverlappingPairCallback);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.OverlappingPairCache
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */