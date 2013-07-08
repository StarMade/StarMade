package com.bulletphysics.collision.dispatch;

import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.util.Comparator;

public class SimulationIslandManager
{
  private final UnionFind unionFind = new UnionFind();
  private final ObjectArrayList<PersistentManifold> islandmanifold = new ObjectArrayList();
  private final ObjectArrayList<CollisionObject> islandBodies = new ObjectArrayList();
  private static final Comparator<PersistentManifold> persistentManifoldComparator = new Comparator()
  {
    public int compare(PersistentManifold lhs, PersistentManifold rhs)
    {
      return SimulationIslandManager.getIslandId(lhs) < SimulationIslandManager.getIslandId(rhs) ? -1 : 1;
    }
  };
  
  public void initUnionFind(int local_n)
  {
    this.unionFind.reset(local_n);
  }
  
  public UnionFind getUnionFind()
  {
    return this.unionFind;
  }
  
  public void findUnions(Dispatcher dispatcher, CollisionWorld colWorld)
  {
    ObjectArrayList<BroadphasePair> pairPtr = colWorld.getPairCache().getOverlappingPairArray();
    for (int local_i = 0; local_i < pairPtr.size(); local_i++)
    {
      BroadphasePair collisionPair = (BroadphasePair)pairPtr.getQuick(local_i);
      CollisionObject colObj0 = (CollisionObject)collisionPair.pProxy0.clientObject;
      CollisionObject colObj1 = (CollisionObject)collisionPair.pProxy1.clientObject;
      if ((colObj0 != null) && (colObj0.mergesSimulationIslands()) && (colObj1 != null) && (colObj1.mergesSimulationIslands())) {
        this.unionFind.unite(colObj0.getIslandTag(), colObj1.getIslandTag());
      }
    }
  }
  
  public void updateActivationState(CollisionWorld colWorld, Dispatcher dispatcher)
  {
    initUnionFind(colWorld.getCollisionObjectArray().size());
    int index = 0;
    for (int local_i = 0; local_i < colWorld.getCollisionObjectArray().size(); local_i++)
    {
      CollisionObject collisionObject = (CollisionObject)colWorld.getCollisionObjectArray().getQuick(local_i);
      collisionObject.setIslandTag(index);
      collisionObject.setCompanionId(-1);
      collisionObject.setHitFraction(1.0F);
      index++;
    }
    findUnions(dispatcher, colWorld);
  }
  
  public void storeIslandActivationState(CollisionWorld colWorld)
  {
    int index = 0;
    for (int local_i = 0; local_i < colWorld.getCollisionObjectArray().size(); local_i++)
    {
      CollisionObject collisionObject = (CollisionObject)colWorld.getCollisionObjectArray().getQuick(local_i);
      if (!collisionObject.isStaticOrKinematicObject())
      {
        collisionObject.setIslandTag(this.unionFind.find(index));
        collisionObject.setCompanionId(-1);
      }
      else
      {
        collisionObject.setIslandTag(-1);
        collisionObject.setCompanionId(-2);
      }
      index++;
    }
  }
  
  private static int getIslandId(PersistentManifold lhs)
  {
    CollisionObject rcolObj0 = (CollisionObject)lhs.getBody0();
    CollisionObject rcolObj1 = (CollisionObject)lhs.getBody1();
    int islandId = rcolObj0.getIslandTag() >= 0 ? rcolObj0.getIslandTag() : rcolObj1.getIslandTag();
    return islandId;
  }
  
  public void buildIslands(Dispatcher dispatcher, ObjectArrayList<CollisionObject> collisionObjects)
  {
    BulletStats.pushProfile("islandUnionFindAndQuickSort");
    try
    {
      this.islandmanifold.clear();
      getUnionFind().sortIslands();
      int numElem = getUnionFind().getNumElements();
      int endIslandIndex = 1;
      for (int startIslandIndex = 0; startIslandIndex < numElem; startIslandIndex = endIslandIndex)
      {
        int islandId = getUnionFind().getElement(startIslandIndex).field_1648;
        for (endIslandIndex = startIslandIndex + 1; (endIslandIndex < numElem) && (getUnionFind().getElement(endIslandIndex).field_1648 == islandId); endIslandIndex++) {}
        boolean allSleeping = true;
        for (int idx = startIslandIndex; idx < endIslandIndex; idx++)
        {
          int local_i = getUnionFind().getElement(idx).field_1649;
          CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(local_i);
          if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || ((!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) {
            throw new AssertionError();
          }
          if (colObj0.getIslandTag() == islandId)
          {
            if (colObj0.getActivationState() == 1) {
              allSleeping = false;
            }
            if (colObj0.getActivationState() == 4) {
              allSleeping = false;
            }
          }
        }
        if (allSleeping) {
          for (idx = startIslandIndex; idx < endIslandIndex; idx++)
          {
            int local_i = getUnionFind().getElement(idx).field_1649;
            CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(local_i);
            if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || ((!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) {
              throw new AssertionError();
            }
            if (colObj0.getIslandTag() == islandId) {
              colObj0.setActivationState(2);
            }
          }
        }
        for (idx = startIslandIndex; idx < endIslandIndex; idx++)
        {
          int local_i = getUnionFind().getElement(idx).field_1649;
          CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(local_i);
          if (((colObj0.getIslandTag() == islandId) || (colObj0.getIslandTag() == -1)) || ((!$assertionsDisabled) && (colObj0.getIslandTag() != islandId) && (colObj0.getIslandTag() != -1))) {
            throw new AssertionError();
          }
          if ((colObj0.getIslandTag() == islandId) && (colObj0.getActivationState() == 2)) {
            colObj0.setActivationState(3);
          }
        }
      }
      int allSleeping = dispatcher.getNumManifolds();
      for (int islandId = 0; islandId < allSleeping; islandId++)
      {
        PersistentManifold idx = dispatcher.getManifoldByIndexInternal(islandId);
        CollisionObject local_i = (CollisionObject)idx.getBody0();
        CollisionObject colObj0 = (CollisionObject)idx.getBody1();
        if (((local_i != null) && (local_i.getActivationState() != 2)) || ((colObj0 != null) && (colObj0.getActivationState() != 2)))
        {
          if ((local_i.isKinematicObject()) && (local_i.getActivationState() != 2)) {
            colObj0.activate();
          }
          if ((colObj0.isKinematicObject()) && (colObj0.getActivationState() != 2)) {
            local_i.activate();
          }
          if (dispatcher.needsResponse(local_i, colObj0)) {
            this.islandmanifold.add(idx);
          }
        }
      }
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public void buildAndProcessIslands(Dispatcher dispatcher, ObjectArrayList<CollisionObject> collisionObjects, IslandCallback callback)
  {
    buildIslands(dispatcher, collisionObjects);
    int endIslandIndex = 1;
    int numElem = getUnionFind().getNumElements();
    BulletStats.pushProfile("processIslands");
    try
    {
      int numManifolds = this.islandmanifold.size();
      MiscUtil.quickSort(this.islandmanifold, persistentManifoldComparator);
      int startManifoldIndex = 0;
      int endManifoldIndex = 1;
      for (int startIslandIndex = 0; startIslandIndex < numElem; startIslandIndex = endIslandIndex)
      {
        int islandId = getUnionFind().getElement(startIslandIndex).field_1648;
        boolean islandSleeping = false;
        for (endIslandIndex = startIslandIndex; (endIslandIndex < numElem) && (getUnionFind().getElement(endIslandIndex).field_1648 == islandId); endIslandIndex++)
        {
          int local_i = getUnionFind().getElement(endIslandIndex).field_1649;
          CollisionObject colObj0 = (CollisionObject)collisionObjects.getQuick(local_i);
          this.islandBodies.add(colObj0);
          if (!colObj0.isActive()) {
            islandSleeping = true;
          }
        }
        int local_i = 0;
        int colObj0 = -1;
        if (startManifoldIndex < numManifolds)
        {
          int curIslandId = getIslandId((PersistentManifold)this.islandmanifold.getQuick(startManifoldIndex));
          if (curIslandId == islandId)
          {
            colObj0 = startManifoldIndex;
            for (endManifoldIndex = startManifoldIndex + 1; (endManifoldIndex < numManifolds) && (islandId == getIslandId((PersistentManifold)this.islandmanifold.getQuick(endManifoldIndex))); endManifoldIndex++) {}
            local_i = endManifoldIndex - startManifoldIndex;
          }
        }
        if (!islandSleeping) {
          callback.processIsland(this.islandBodies, this.islandBodies.size(), this.islandmanifold, colObj0, local_i, islandId);
        }
        if (local_i != 0) {
          startManifoldIndex = endManifoldIndex;
        }
        this.islandBodies.clear();
      }
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public static abstract class IslandCallback
  {
    public abstract void processIsland(ObjectArrayList<CollisionObject> paramObjectArrayList, int paramInt1, ObjectArrayList<PersistentManifold> paramObjectArrayList1, int paramInt2, int paramInt3, int paramInt4);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.SimulationIslandManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */