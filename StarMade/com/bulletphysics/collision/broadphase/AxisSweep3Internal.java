package com.bulletphysics.collision.broadphase;

import com.bulletphysics..Stack;
import com.bulletphysics.BulletStats;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public abstract class AxisSweep3Internal
  extends BroadphaseInterface
{
  protected int bpHandleMask;
  protected int handleSentinel;
  protected final Vector3f worldAabbMin;
  protected final Vector3f worldAabbMax;
  protected final Vector3f quantize;
  protected int numHandles;
  protected int maxHandles;
  protected Handle[] pHandles;
  protected int firstFreeHandle;
  protected EdgeArray[] pEdges;
  protected OverlappingPairCache pairCache;
  protected OverlappingPairCallback userPairCallback;
  protected boolean ownsPairCache;
  protected int invalidPair;
  protected int mask;
  
  AxisSweep3Internal(Vector3f arg1, Vector3f arg2, int arg3, int arg4, int arg5, OverlappingPairCache arg6) {}
  
  protected int allocHandle()
  {
    assert (this.firstFreeHandle != 0);
    int handle = this.firstFreeHandle;
    this.firstFreeHandle = getHandle(handle).getNextFree();
    this.numHandles += 1;
    return handle;
  }
  
  protected void freeHandle(int handle)
  {
    assert ((handle > 0) && (handle < this.maxHandles));
    getHandle(handle).setNextFree(this.firstFreeHandle);
    this.firstFreeHandle = handle;
    this.numHandles -= 1;
  }
  
  protected boolean testOverlap(int ignoreAxis, Handle pHandleA, Handle pHandleB)
  {
    for (int axis = 0; axis < 3; axis++) {
      if ((axis != ignoreAxis) && ((pHandleA.getMaxEdges(axis) < pHandleB.getMinEdges(axis)) || (pHandleB.getMaxEdges(axis) < pHandleA.getMinEdges(axis)))) {
        return false;
      }
    }
    return true;
  }
  
  protected void quantize(int[] arg1, Vector3f arg2, int arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f clampedPoint = localStack.get$javax$vecmath$Vector3f(point);
      VectorUtil.setMax(clampedPoint, this.worldAabbMin);
      VectorUtil.setMin(clampedPoint, this.worldAabbMax);
      Vector3f local_v = localStack.get$javax$vecmath$Vector3f();
      local_v.sub(clampedPoint, this.worldAabbMin);
      VectorUtil.mul(local_v, local_v, this.quantize);
      out[0] = (((int)local_v.field_615 & this.bpHandleMask | isMax) & this.mask);
      out[1] = (((int)local_v.field_616 & this.bpHandleMask | isMax) & this.mask);
      out[2] = (((int)local_v.field_617 & this.bpHandleMask | isMax) & this.mask);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void sortMinDown(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
  {
    EdgeArray edgeArray = this.pEdges[axis];
    int pEdge_idx = edge;
    int pPrev_idx = pEdge_idx - 1;
    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
    while (edgeArray.getPos(pEdge_idx) < edgeArray.getPos(pPrev_idx))
    {
      Handle pHandlePrev = getHandle(edgeArray.getHandle(pPrev_idx));
      if (edgeArray.isMax(pPrev_idx) != 0)
      {
        if ((updateOverlaps) && (testOverlap(axis, pHandleEdge, pHandlePrev)))
        {
          this.pairCache.addOverlappingPair(pHandleEdge, pHandlePrev);
          if (this.userPairCallback != null) {
            this.userPairCallback.addOverlappingPair(pHandleEdge, pHandlePrev);
          }
        }
        pHandlePrev.incMaxEdges(axis);
      }
      else
      {
        pHandlePrev.incMinEdges(axis);
      }
      pHandleEdge.decMinEdges(axis);
      edgeArray.swap(pEdge_idx, pPrev_idx);
      pEdge_idx--;
      pPrev_idx--;
    }
  }
  
  protected void sortMinUp(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
  {
    EdgeArray edgeArray = this.pEdges[axis];
    int pEdge_idx = edge;
    int pNext_idx = pEdge_idx + 1;
    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
    while ((edgeArray.getHandle(pNext_idx) != 0) && (edgeArray.getPos(pEdge_idx) >= edgeArray.getPos(pNext_idx)))
    {
      Handle pHandleNext = getHandle(edgeArray.getHandle(pNext_idx));
      if (edgeArray.isMax(pNext_idx) != 0)
      {
        if (updateOverlaps)
        {
          Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
          Handle handle1 = getHandle(edgeArray.getHandle(pNext_idx));
          this.pairCache.removeOverlappingPair(handle0, handle1, dispatcher);
          if (this.userPairCallback != null) {
            this.userPairCallback.removeOverlappingPair(handle0, handle1, dispatcher);
          }
        }
        pHandleNext.decMaxEdges(axis);
      }
      else
      {
        pHandleNext.decMinEdges(axis);
      }
      pHandleEdge.incMinEdges(axis);
      edgeArray.swap(pEdge_idx, pNext_idx);
      pEdge_idx++;
      pNext_idx++;
    }
  }
  
  protected void sortMaxDown(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
  {
    EdgeArray edgeArray = this.pEdges[axis];
    int pEdge_idx = edge;
    int pPrev_idx = pEdge_idx - 1;
    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
    while (edgeArray.getPos(pEdge_idx) < edgeArray.getPos(pPrev_idx))
    {
      Handle pHandlePrev = getHandle(edgeArray.getHandle(pPrev_idx));
      if (edgeArray.isMax(pPrev_idx) == 0)
      {
        if (updateOverlaps)
        {
          Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
          Handle handle1 = getHandle(edgeArray.getHandle(pPrev_idx));
          this.pairCache.removeOverlappingPair(handle0, handle1, dispatcher);
          if (this.userPairCallback != null) {
            this.userPairCallback.removeOverlappingPair(handle0, handle1, dispatcher);
          }
        }
        pHandlePrev.incMinEdges(axis);
      }
      else
      {
        pHandlePrev.incMaxEdges(axis);
      }
      pHandleEdge.decMaxEdges(axis);
      edgeArray.swap(pEdge_idx, pPrev_idx);
      pEdge_idx--;
      pPrev_idx--;
    }
  }
  
  protected void sortMaxUp(int axis, int edge, Dispatcher dispatcher, boolean updateOverlaps)
  {
    EdgeArray edgeArray = this.pEdges[axis];
    int pEdge_idx = edge;
    int pNext_idx = pEdge_idx + 1;
    Handle pHandleEdge = getHandle(edgeArray.getHandle(pEdge_idx));
    while ((edgeArray.getHandle(pNext_idx) != 0) && (edgeArray.getPos(pEdge_idx) >= edgeArray.getPos(pNext_idx)))
    {
      Handle pHandleNext = getHandle(edgeArray.getHandle(pNext_idx));
      if (edgeArray.isMax(pNext_idx) == 0)
      {
        if ((updateOverlaps) && (testOverlap(axis, pHandleEdge, pHandleNext)))
        {
          Handle handle0 = getHandle(edgeArray.getHandle(pEdge_idx));
          Handle handle1 = getHandle(edgeArray.getHandle(pNext_idx));
          this.pairCache.addOverlappingPair(handle0, handle1);
          if (this.userPairCallback != null) {
            this.userPairCallback.addOverlappingPair(handle0, handle1);
          }
        }
        pHandleNext.decMinEdges(axis);
      }
      else
      {
        pHandleNext.decMaxEdges(axis);
      }
      pHandleEdge.incMaxEdges(axis);
      edgeArray.swap(pEdge_idx, pNext_idx);
      pEdge_idx++;
      pNext_idx++;
    }
  }
  
  public int getNumHandles()
  {
    return this.numHandles;
  }
  
  public void calculateOverlappingPairs(Dispatcher dispatcher)
  {
    if (this.pairCache.hasDeferredRemoval())
    {
      ObjectArrayList<BroadphasePair> overlappingPairArray = this.pairCache.getOverlappingPairArray();
      MiscUtil.quickSort(overlappingPairArray, BroadphasePair.broadphasePairSortPredicate);
      MiscUtil.resize(overlappingPairArray, overlappingPairArray.size() - this.invalidPair, BroadphasePair.class);
      this.invalidPair = 0;
      BroadphasePair previousPair = new BroadphasePair();
      previousPair.pProxy0 = null;
      previousPair.pProxy1 = null;
      previousPair.algorithm = null;
      for (int local_i = 0; local_i < overlappingPairArray.size(); local_i++)
      {
        BroadphasePair pair = (BroadphasePair)overlappingPairArray.getQuick(local_i);
        boolean isDuplicate = pair.equals(previousPair);
        previousPair.set(pair);
        boolean needsRemoval = false;
        if (!isDuplicate)
        {
          boolean hasOverlap = testAabbOverlap(pair.pProxy0, pair.pProxy1);
          if (hasOverlap) {
            needsRemoval = false;
          } else {
            needsRemoval = true;
          }
        }
        else
        {
          needsRemoval = true;
          assert (pair.algorithm == null);
        }
        if (needsRemoval)
        {
          this.pairCache.cleanOverlappingPair(pair, dispatcher);
          pair.pProxy0 = null;
          pair.pProxy1 = null;
          this.invalidPair += 1;
          BulletStats.gOverlappingPairs -= 1;
        }
      }
      MiscUtil.quickSort(overlappingPairArray, BroadphasePair.broadphasePairSortPredicate);
      MiscUtil.resize(overlappingPairArray, overlappingPairArray.size() - this.invalidPair, BroadphasePair.class);
      this.invalidPair = 0;
    }
  }
  
  public int addHandle(Vector3f aabbMin, Vector3f aabbMax, Object pOwner, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
  {
    int[] min = new int[3];
    int[] max = new int[3];
    quantize(min, aabbMin, 0);
    quantize(max, aabbMax, 1);
    int handle = allocHandle();
    Handle pHandle = getHandle(handle);
    pHandle.uniqueId = handle;
    pHandle.clientObject = pOwner;
    pHandle.collisionFilterGroup = collisionFilterGroup;
    pHandle.collisionFilterMask = collisionFilterMask;
    pHandle.multiSapParentProxy = multiSapProxy;
    int limit = this.numHandles * 2;
    for (int axis = 0; axis < 3; axis++)
    {
      this.pHandles[0].setMaxEdges(axis, this.pHandles[0].getMaxEdges(axis) + 2);
      this.pEdges[axis].set(limit + 1, limit - 1);
      this.pEdges[axis].setPos(limit - 1, min[axis]);
      this.pEdges[axis].setHandle(limit - 1, handle);
      this.pEdges[axis].setPos(limit, max[axis]);
      this.pEdges[axis].setHandle(limit, handle);
      pHandle.setMinEdges(axis, limit - 1);
      pHandle.setMaxEdges(axis, limit);
    }
    sortMinDown(0, pHandle.getMinEdges(0), dispatcher, false);
    sortMaxDown(0, pHandle.getMaxEdges(0), dispatcher, false);
    sortMinDown(1, pHandle.getMinEdges(1), dispatcher, false);
    sortMaxDown(1, pHandle.getMaxEdges(1), dispatcher, false);
    sortMinDown(2, pHandle.getMinEdges(2), dispatcher, true);
    sortMaxDown(2, pHandle.getMaxEdges(2), dispatcher, true);
    return handle;
  }
  
  public void removeHandle(int handle, Dispatcher dispatcher)
  {
    Handle pHandle = getHandle(handle);
    if (!this.pairCache.hasDeferredRemoval()) {
      this.pairCache.removeOverlappingPairsContainingProxy(pHandle, dispatcher);
    }
    int limit = this.numHandles * 2;
    for (int axis = 0; axis < 3; axis++) {
      this.pHandles[0].setMaxEdges(axis, this.pHandles[0].getMaxEdges(axis) - 2);
    }
    for (axis = 0; axis < 3; axis++)
    {
      EdgeArray pEdges = this.pEdges[axis];
      int max = pHandle.getMaxEdges(axis);
      pEdges.setPos(max, this.handleSentinel);
      sortMaxUp(axis, max, dispatcher, false);
      int local_i = pHandle.getMinEdges(axis);
      pEdges.setPos(local_i, this.handleSentinel);
      sortMinUp(axis, local_i, dispatcher, false);
      pEdges.setHandle(limit - 1, 0);
      pEdges.setPos(limit - 1, this.handleSentinel);
    }
    freeHandle(handle);
  }
  
  public void updateHandle(int handle, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher)
  {
    Handle pHandle = getHandle(handle);
    int[] min = new int[3];
    int[] max = new int[3];
    quantize(min, aabbMin, 0);
    quantize(max, aabbMax, 1);
    for (int axis = 0; axis < 3; axis++)
    {
      int emin = pHandle.getMinEdges(axis);
      int emax = pHandle.getMaxEdges(axis);
      int dmin = min[axis] - this.pEdges[axis].getPos(emin);
      int dmax = max[axis] - this.pEdges[axis].getPos(emax);
      this.pEdges[axis].setPos(emin, min[axis]);
      this.pEdges[axis].setPos(emax, max[axis]);
      if (dmin < 0) {
        sortMinDown(axis, emin, dispatcher, true);
      }
      if (dmax > 0) {
        sortMaxUp(axis, emax, dispatcher, true);
      }
      if (dmin > 0) {
        sortMinUp(axis, emin, dispatcher, true);
      }
      if (dmax < 0) {
        sortMaxDown(axis, emax, dispatcher, true);
      }
    }
  }
  
  public Handle getHandle(int index)
  {
    return this.pHandles[index];
  }
  
  public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
  {
    int handleId = addHandle(aabbMin, aabbMax, userPtr, collisionFilterGroup, collisionFilterMask, dispatcher, multiSapProxy);
    Handle handle = getHandle(handleId);
    return handle;
  }
  
  public void destroyProxy(BroadphaseProxy proxy, Dispatcher dispatcher)
  {
    Handle handle = (Handle)proxy;
    removeHandle(handle.uniqueId, dispatcher);
  }
  
  public void setAabb(BroadphaseProxy proxy, Vector3f aabbMin, Vector3f aabbMax, Dispatcher dispatcher)
  {
    Handle handle = (Handle)proxy;
    updateHandle(handle.uniqueId, aabbMin, aabbMax, dispatcher);
  }
  
  public boolean testAabbOverlap(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
  {
    Handle pHandleA = (Handle)proxy0;
    Handle pHandleB = (Handle)proxy1;
    for (int axis = 0; axis < 3; axis++) {
      if ((pHandleA.getMaxEdges(axis) < pHandleB.getMinEdges(axis)) || (pHandleB.getMaxEdges(axis) < pHandleA.getMinEdges(axis))) {
        return false;
      }
    }
    return true;
  }
  
  public OverlappingPairCache getOverlappingPairCache()
  {
    return this.pairCache;
  }
  
  public void setOverlappingPairUserCallback(OverlappingPairCallback pairCallback)
  {
    this.userPairCallback = pairCallback;
  }
  
  public OverlappingPairCallback getOverlappingPairUserCallback()
  {
    return this.userPairCallback;
  }
  
  public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax)
  {
    aabbMin.set(this.worldAabbMin);
    aabbMax.set(this.worldAabbMax);
  }
  
  public void printStats() {}
  
  protected abstract EdgeArray createEdgeArray(int paramInt);
  
  protected abstract Handle createHandle();
  
  protected abstract int getMask();
  
  protected static abstract class Handle
    extends BroadphaseProxy
  {
    public abstract int getMinEdges(int paramInt);
    
    public abstract void setMinEdges(int paramInt1, int paramInt2);
    
    public abstract int getMaxEdges(int paramInt);
    
    public abstract void setMaxEdges(int paramInt1, int paramInt2);
    
    public void incMinEdges(int edgeIndex)
    {
      setMinEdges(edgeIndex, getMinEdges(edgeIndex) + 1);
    }
    
    public void incMaxEdges(int edgeIndex)
    {
      setMaxEdges(edgeIndex, getMaxEdges(edgeIndex) + 1);
    }
    
    public void decMinEdges(int edgeIndex)
    {
      setMinEdges(edgeIndex, getMinEdges(edgeIndex) - 1);
    }
    
    public void decMaxEdges(int edgeIndex)
    {
      setMaxEdges(edgeIndex, getMaxEdges(edgeIndex) - 1);
    }
    
    public void setNextFree(int next)
    {
      setMinEdges(0, next);
    }
    
    public int getNextFree()
    {
      return getMinEdges(0);
    }
  }
  
  protected static abstract class EdgeArray
  {
    public abstract void swap(int paramInt1, int paramInt2);
    
    public abstract void set(int paramInt1, int paramInt2);
    
    public abstract int getPos(int paramInt);
    
    public abstract void setPos(int paramInt1, int paramInt2);
    
    public abstract int getHandle(int paramInt);
    
    public abstract void setHandle(int paramInt1, int paramInt2);
    
    public int isMax(int offset)
    {
      return getPos(offset) & 0x1;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3Internal
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */