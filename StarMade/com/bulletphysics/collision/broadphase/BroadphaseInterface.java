package com.bulletphysics.collision.broadphase;

import javax.vecmath.Vector3f;

public abstract class BroadphaseInterface
{
  public abstract BroadphaseProxy createProxy(Vector3f paramVector3f1, Vector3f paramVector3f2, BroadphaseNativeType paramBroadphaseNativeType, Object paramObject1, short paramShort1, short paramShort2, Dispatcher paramDispatcher, Object paramObject2);
  
  public abstract void destroyProxy(BroadphaseProxy paramBroadphaseProxy, Dispatcher paramDispatcher);
  
  public abstract void setAabb(BroadphaseProxy paramBroadphaseProxy, Vector3f paramVector3f1, Vector3f paramVector3f2, Dispatcher paramDispatcher);
  
  public abstract void calculateOverlappingPairs(Dispatcher paramDispatcher);
  
  public abstract OverlappingPairCache getOverlappingPairCache();
  
  public abstract void getBroadphaseAabb(Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public abstract void printStats();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */