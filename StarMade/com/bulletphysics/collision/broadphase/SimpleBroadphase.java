package com.bulletphysics.collision.broadphase;

import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public class SimpleBroadphase
  extends BroadphaseInterface
{
  private final ObjectArrayList<SimpleBroadphaseProxy> handles = new ObjectArrayList();
  private int maxHandles;
  private OverlappingPairCache pairCache;
  private boolean ownsPairCache;
  
  public SimpleBroadphase()
  {
    this(16384, null);
  }
  
  public SimpleBroadphase(int maxProxies)
  {
    this(maxProxies, null);
  }
  
  public SimpleBroadphase(int maxProxies, OverlappingPairCache overlappingPairCache)
  {
    this.pairCache = overlappingPairCache;
    if (overlappingPairCache == null)
    {
      this.pairCache = new HashedOverlappingPairCache();
      this.ownsPairCache = true;
    }
  }
  
  public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
  {
    assert ((aabbMin.field_615 <= aabbMax.field_615) && (aabbMin.field_616 <= aabbMax.field_616) && (aabbMin.field_617 <= aabbMax.field_617));
    SimpleBroadphaseProxy proxy = new SimpleBroadphaseProxy(aabbMin, aabbMax, shapeType, userPtr, collisionFilterGroup, collisionFilterMask, multiSapProxy);
    proxy.uniqueId = this.handles.size();
    this.handles.add(proxy);
    return proxy;
  }
  
  public void destroyProxy(BroadphaseProxy proxyOrg, Dispatcher dispatcher)
  {
    this.handles.remove(proxyOrg);
    this.pairCache.removeOverlappingPairsContainingProxy(proxyOrg, dispatcher);
  }
  
  public void setAabb(BroadphaseProxy proxy, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher)
  {
    SimpleBroadphaseProxy sbp = (SimpleBroadphaseProxy)proxy;
    sbp.min.set(aabbMin);
    sbp.max.set(aabbMax);
  }
  
  private static boolean aabbOverlap(SimpleBroadphaseProxy proxy0, SimpleBroadphaseProxy proxy1)
  {
    return (proxy0.min.field_615 <= proxy1.max.field_615) && (proxy1.min.field_615 <= proxy0.max.field_615) && (proxy0.min.field_616 <= proxy1.max.field_616) && (proxy1.min.field_616 <= proxy0.max.field_616) && (proxy0.min.field_617 <= proxy1.max.field_617) && (proxy1.min.field_617 <= proxy0.max.field_617);
  }
  
  public void calculateOverlappingPairs(Dispatcher dispatcher)
  {
    for (int local_i = 0; local_i < this.handles.size(); local_i++)
    {
      SimpleBroadphaseProxy proxy0 = (SimpleBroadphaseProxy)this.handles.getQuick(local_i);
      for (int local_j = 0; local_j < this.handles.size(); local_j++)
      {
        SimpleBroadphaseProxy proxy1 = (SimpleBroadphaseProxy)this.handles.getQuick(local_j);
        if (proxy0 != proxy1) {
          if (aabbOverlap(proxy0, proxy1))
          {
            if (this.pairCache.findPair(proxy0, proxy1) == null) {
              this.pairCache.addOverlappingPair(proxy0, proxy1);
            }
          }
          else if ((!this.pairCache.hasDeferredRemoval()) && (this.pairCache.findPair(proxy0, proxy1) != null)) {
            this.pairCache.removeOverlappingPair(proxy0, proxy1, dispatcher);
          }
        }
      }
    }
  }
  
  public OverlappingPairCache getOverlappingPairCache()
  {
    return this.pairCache;
  }
  
  public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax)
  {
    aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
    aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
  }
  
  public void printStats() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.SimpleBroadphase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */