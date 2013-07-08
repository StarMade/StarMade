package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.broadphase.OverlapCallback;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import java.io.PrintStream;
import java.util.Collections;

public class CollisionDispatcher
  extends Dispatcher
{
  protected final ObjectPool<PersistentManifold> manifoldsPool = ObjectPool.get(PersistentManifold.class);
  private static final int MAX_BROADPHASE_COLLISION_TYPES = BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal();
  private int count = 0;
  private final ObjectArrayList<PersistentManifold> manifoldsPtr = new ObjectArrayList();
  private boolean useIslands = true;
  private boolean staticWarningReported = false;
  private ManifoldResult defaultManifoldResult;
  private NearCallback nearCallback;
  private final CollisionAlgorithmCreateFunc[][] doubleDispatch = new CollisionAlgorithmCreateFunc[MAX_BROADPHASE_COLLISION_TYPES][MAX_BROADPHASE_COLLISION_TYPES];
  private CollisionConfiguration collisionConfiguration;
  private CollisionAlgorithmConstructionInfo tmpCI = new CollisionAlgorithmConstructionInfo();
  private CollisionPairCallback collisionPairCallback = new CollisionPairCallback(null);
  
  public CollisionDispatcher(CollisionConfiguration collisionConfiguration)
  {
    this.collisionConfiguration = collisionConfiguration;
    setNearCallback(new DefaultNearCallback());
    for (int local_i = 0; local_i < MAX_BROADPHASE_COLLISION_TYPES; local_i++) {
      for (int local_j = 0; local_j < MAX_BROADPHASE_COLLISION_TYPES; local_j++)
      {
        this.doubleDispatch[local_i][local_j] = collisionConfiguration.getCollisionAlgorithmCreateFunc(BroadphaseNativeType.forValue(local_i), BroadphaseNativeType.forValue(local_j));
        assert (this.doubleDispatch[local_i][local_j] != null);
      }
    }
  }
  
  public void registerCollisionCreateFunc(int proxyType0, int proxyType1, CollisionAlgorithmCreateFunc createFunc)
  {
    this.doubleDispatch[proxyType0][proxyType1] = createFunc;
  }
  
  public NearCallback getNearCallback()
  {
    return this.nearCallback;
  }
  
  public void setNearCallback(NearCallback nearCallback)
  {
    this.nearCallback = nearCallback;
  }
  
  public CollisionConfiguration getCollisionConfiguration()
  {
    return this.collisionConfiguration;
  }
  
  public void setCollisionConfiguration(CollisionConfiguration collisionConfiguration)
  {
    this.collisionConfiguration = collisionConfiguration;
  }
  
  public CollisionAlgorithm findAlgorithm(CollisionObject body0, CollisionObject body1, PersistentManifold sharedManifold)
  {
    CollisionAlgorithmConstructionInfo local_ci = this.tmpCI;
    local_ci.dispatcher1 = this;
    local_ci.manifold = sharedManifold;
    CollisionAlgorithmCreateFunc createFunc = this.doubleDispatch[body0.getCollisionShape().getShapeType().ordinal()][body1.getCollisionShape().getShapeType().ordinal()];
    CollisionAlgorithm algo = createFunc.createCollisionAlgorithm(local_ci, body0, body1);
    algo.internalSetCreateFunc(createFunc);
    return algo;
  }
  
  public void freeCollisionAlgorithm(CollisionAlgorithm algo)
  {
    CollisionAlgorithmCreateFunc createFunc = algo.internalGetCreateFunc();
    algo.internalSetCreateFunc(null);
    createFunc.releaseCollisionAlgorithm(algo);
    algo.destroy();
  }
  
  public PersistentManifold getNewManifold(Object local_b0, Object local_b1)
  {
    CollisionObject body0 = (CollisionObject)local_b0;
    CollisionObject body1 = (CollisionObject)local_b1;
    PersistentManifold manifold = (PersistentManifold)this.manifoldsPool.get();
    manifold.init(body0, body1, 0);
    manifold.index1a = this.manifoldsPtr.size();
    this.manifoldsPtr.add(manifold);
    return manifold;
  }
  
  public void releaseManifold(PersistentManifold manifold)
  {
    clearManifold(manifold);
    int findIndex = manifold.index1a;
    assert (findIndex < this.manifoldsPtr.size());
    Collections.swap(this.manifoldsPtr, findIndex, this.manifoldsPtr.size() - 1);
    ((PersistentManifold)this.manifoldsPtr.getQuick(findIndex)).index1a = findIndex;
    this.manifoldsPtr.removeQuick(this.manifoldsPtr.size() - 1);
    this.manifoldsPool.release(manifold);
  }
  
  public void clearManifold(PersistentManifold manifold)
  {
    manifold.clearManifold();
  }
  
  public boolean needsCollision(CollisionObject body0, CollisionObject body1)
  {
    assert (body0 != null);
    assert (body1 != null);
    boolean needsCollision = true;
    if ((!this.staticWarningReported) && ((body0.isStaticObject()) || (body0.isKinematicObject())) && ((body1.isStaticObject()) || (body1.isKinematicObject())))
    {
      this.staticWarningReported = true;
      System.err.println("warning CollisionDispatcher.needsCollision: static-static collision!");
    }
    if ((!body0.isActive()) && (!body1.isActive())) {
      needsCollision = false;
    } else if (!body0.checkCollideWith(body1)) {
      needsCollision = false;
    }
    return needsCollision;
  }
  
  public boolean needsResponse(CollisionObject body0, CollisionObject body1)
  {
    boolean hasResponse = (body0.hasContactResponse()) && (body1.hasContactResponse());
    hasResponse = (hasResponse) && ((!body0.isStaticOrKinematicObject()) || (!body1.isStaticOrKinematicObject()));
    return hasResponse;
  }
  
  public void dispatchAllCollisionPairs(OverlappingPairCache pairCache, DispatcherInfo dispatchInfo, Dispatcher dispatcher)
  {
    this.collisionPairCallback.init(dispatchInfo, this);
    pairCache.processAllOverlappingPairs(this.collisionPairCallback, dispatcher);
  }
  
  public int getNumManifolds()
  {
    return this.manifoldsPtr.size();
  }
  
  public PersistentManifold getManifoldByIndexInternal(int index)
  {
    return (PersistentManifold)this.manifoldsPtr.getQuick(index);
  }
  
  public ObjectArrayList<PersistentManifold> getInternalManifoldPointer()
  {
    return this.manifoldsPtr;
  }
  
  private static class CollisionPairCallback
    extends OverlapCallback
  {
    private DispatcherInfo dispatchInfo;
    private CollisionDispatcher dispatcher;
    
    public void init(DispatcherInfo dispatchInfo, CollisionDispatcher dispatcher)
    {
      this.dispatchInfo = dispatchInfo;
      this.dispatcher = dispatcher;
    }
    
    public boolean processOverlap(BroadphasePair pair)
    {
      this.dispatcher.getNearCallback().handleCollision(pair, this.dispatcher, this.dispatchInfo);
      return false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionDispatcher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */