package com.bulletphysics.collision.broadphase;

public abstract class OverlappingPairCallback
{
  public abstract BroadphasePair addOverlappingPair(BroadphaseProxy paramBroadphaseProxy1, BroadphaseProxy paramBroadphaseProxy2);
  
  public abstract Object removeOverlappingPair(BroadphaseProxy paramBroadphaseProxy1, BroadphaseProxy paramBroadphaseProxy2, Dispatcher paramDispatcher);
  
  public abstract void removeOverlappingPairsContainingProxy(BroadphaseProxy paramBroadphaseProxy, Dispatcher paramDispatcher);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.OverlappingPairCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */