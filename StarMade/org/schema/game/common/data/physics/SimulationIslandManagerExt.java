package org.schema.game.common.data.physics;

import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.collision.dispatch.SimulationIslandManager;
import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
import com.bulletphysics.collision.dispatch.UnionFind;
import com.bulletphysics.collision.dispatch.UnionFind.Element;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import java.util.Comparator;

public class SimulationIslandManagerExt
  extends SimulationIslandManager
{
  private final UnionFind unionFind = new UnionFind();
  private final ObjectArrayList islandmanifold = new ObjectArrayList();
  private final ObjectArrayList islandBodies = new ObjectArrayList();
  private static final Comparator persistentManifoldComparator = new SimulationIslandManagerExt.1();
  
  public void initUnionFind(int paramInt)
  {
    this.unionFind.reset(paramInt);
  }
  
  public UnionFind getUnionFind()
  {
    return this.unionFind;
  }
  
  public void findUnions(Dispatcher paramDispatcher, CollisionWorld paramCollisionWorld)
  {
    paramDispatcher = paramCollisionWorld.getPairCache().getOverlappingPairArray();
    for (paramCollisionWorld = 0; paramCollisionWorld < paramDispatcher.size(); paramCollisionWorld++)
    {
      CollisionObject localCollisionObject = (CollisionObject)(localObject = (BroadphasePair)paramDispatcher.getQuick(paramCollisionWorld)).pProxy0.clientObject;
      Object localObject = (CollisionObject)((BroadphasePair)localObject).pProxy1.clientObject;
      if ((localCollisionObject != null) && (localCollisionObject.mergesSimulationIslands()) && (localObject != null) && (((CollisionObject)localObject).mergesSimulationIslands())) {
        this.unionFind.unite(localCollisionObject.getIslandTag(), ((CollisionObject)localObject).getIslandTag());
      }
    }
  }
  
  public void updateActivationState(CollisionWorld paramCollisionWorld, Dispatcher paramDispatcher)
  {
    initUnionFind(paramCollisionWorld.getCollisionObjectArray().size());
    int i = 0;
    for (int j = 0; j < paramCollisionWorld.getCollisionObjectArray().size(); j++)
    {
      CollisionObject localCollisionObject;
      (localCollisionObject = (CollisionObject)paramCollisionWorld.getCollisionObjectArray().getQuick(j)).setIslandTag(i);
      localCollisionObject.setCompanionId(-1);
      localCollisionObject.setHitFraction(1.0F);
      i++;
    }
    findUnions(paramDispatcher, paramCollisionWorld);
  }
  
  public void storeIslandActivationState(CollisionWorld paramCollisionWorld)
  {
    int i = 0;
    for (int j = 0; j < paramCollisionWorld.getCollisionObjectArray().size(); j++)
    {
      CollisionObject localCollisionObject;
      if (!(localCollisionObject = (CollisionObject)paramCollisionWorld.getCollisionObjectArray().getQuick(j)).isStaticOrKinematicObject())
      {
        localCollisionObject.setIslandTag(this.unionFind.find(i));
        localCollisionObject.setCompanionId(-1);
      }
      else
      {
        localCollisionObject.setIslandTag(-1);
        localCollisionObject.setCompanionId(-2);
      }
      i++;
    }
  }
  
  private static int getIslandId(PersistentManifold paramPersistentManifold)
  {
    CollisionObject localCollisionObject = (CollisionObject)paramPersistentManifold.getBody0();
    paramPersistentManifold = (CollisionObject)paramPersistentManifold.getBody1();
    if (localCollisionObject.getIslandTag() >= 0) {
      return localCollisionObject.getIslandTag();
    }
    return paramPersistentManifold.getIslandTag();
  }
  
  public void buildIslands(Dispatcher paramDispatcher, ObjectArrayList paramObjectArrayList)
  {
    BulletStats.pushProfile("islandUnionFindAndQuickSort");
    try
    {
      this.islandmanifold.clear();
      getUnionFind().sortIslands();
      int i = getUnionFind().getNumElements();
      int j = 0;
      CollisionObject localCollisionObject3;
      for (int k = 0; k < i; k = j)
      {
        m = getUnionFind().getElement(k).field_1648;
        for (j = k + 1; (j < i) && (getUnionFind().getElement(j).field_1648 == m); j++) {}
        n = 1;
        int i2;
        for (int i1 = k; i1 < j; i1++)
        {
          i2 = getUnionFind().getElement(i1).field_1649;
          if ((localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2)).getIslandTag() != m) {
            localCollisionObject3.getIslandTag();
          }
          assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
          if (localCollisionObject3.getIslandTag() == m)
          {
            if (localCollisionObject3.getActivationState() == 1) {
              n = 0;
            }
            if (localCollisionObject3.getActivationState() == 4) {
              n = 0;
            }
          }
        }
        if (n != 0) {
          for (i1 = k; i1 < j; i1++)
          {
            i2 = getUnionFind().getElement(i1).field_1649;
            if ((localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2)).getIslandTag() != m) {
              localCollisionObject3.getIslandTag();
            }
            assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
            if (localCollisionObject3.getIslandTag() == m) {
              localCollisionObject3.setActivationState(2);
            }
          }
        }
        for (i1 = k; i1 < j; i1++)
        {
          i2 = getUnionFind().getElement(i1).field_1649;
          if ((localCollisionObject3 = (CollisionObject)paramObjectArrayList.getQuick(i2)).getIslandTag() != m) {
            localCollisionObject3.getIslandTag();
          }
          assert ((localCollisionObject3.getIslandTag() == m) || (localCollisionObject3.getIslandTag() == -1));
          if ((localCollisionObject3.getIslandTag() == m) && (localCollisionObject3.getActivationState() == 2)) {
            localCollisionObject3.setActivationState(3);
          }
        }
      }
      int n = paramDispatcher.getNumManifolds();
      for (int m = 0; m < n; m++)
      {
        PersistentManifold localPersistentManifold;
        CollisionObject localCollisionObject2 = (CollisionObject)(localPersistentManifold = paramDispatcher.getManifoldByIndexInternal(m)).getBody0();
        localCollisionObject3 = (CollisionObject)localPersistentManifold.getBody1();
        i = 0;
        for (j = 0; j < paramObjectArrayList.size(); j++)
        {
          CollisionObject localCollisionObject1;
          if (((localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j)) == localCollisionObject2) || (localCollisionObject1 == localCollisionObject3)) {
            i = 1;
          }
        }
        if (i == 0) {
          for (j = 0; j < paramObjectArrayList.size(); j++) {
            paramObjectArrayList.getQuick(j);
          }
        }
        assert (i != 0) : ("MANIFOLD OBJECTS NOT FOUND " + localCollisionObject2 + "; " + localCollisionObject3);
        if (((localCollisionObject2 != null) && (localCollisionObject2.getActivationState() != 2)) || ((localCollisionObject3 != null) && (localCollisionObject3.getActivationState() != 2)))
        {
          if ((localCollisionObject2.isKinematicObject()) && (localCollisionObject2.getActivationState() != 2)) {
            localCollisionObject3.activate();
          }
          if ((localCollisionObject3.isKinematicObject()) && (localCollisionObject3.getActivationState() != 2)) {
            localCollisionObject2.activate();
          }
          if (paramDispatcher.needsResponse(localCollisionObject2, localCollisionObject3)) {
            this.islandmanifold.add(localPersistentManifold);
          }
        }
      }
      return;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public void buildAndProcessIslands(Dispatcher paramDispatcher, ObjectArrayList paramObjectArrayList, SimulationIslandManager.IslandCallback paramIslandCallback)
  {
    buildIslands(paramDispatcher, paramObjectArrayList);
    int j;
    for (paramDispatcher = 0; paramDispatcher < this.islandmanifold.size(); paramDispatcher++)
    {
      PersistentManifold localPersistentManifold;
      if (((localPersistentManifold = (PersistentManifold)this.islandmanifold.getQuick(paramDispatcher)).getBody0().toString().contains("|CLI")) || (localPersistentManifold.getBody1().toString().contains("|CLI")))
      {
        localDispatcher2 = 0;
        CollisionObject localCollisionObject1;
        for (j = 0; j < paramObjectArrayList.size(); j++) {
          if (((localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j)) == localPersistentManifold.getBody0()) || (localCollisionObject1 == localPersistentManifold.getBody1())) {
            localDispatcher2 = 1;
          }
        }
        if (localDispatcher2 == 0) {
          for (j = 0; j < paramObjectArrayList.size(); j++)
          {
            localCollisionObject1 = (CollisionObject)paramObjectArrayList.getQuick(j);
            System.err.println("#" + j + " COLLISION OBJECTS: " + localCollisionObject1);
          }
        }
        assert (localDispatcher2 != 0) : ("MANIFOLD OBJECTS NOT FOUND " + localPersistentManifold.getBody0() + "; " + localPersistentManifold.getBody1());
      }
    }
    Dispatcher localDispatcher2 = getUnionFind().getNumElements();
    BulletStats.pushProfile("processIslands");
    try
    {
      j = this.islandmanifold.size();
      MiscUtil.quickSort(this.islandmanifold, persistentManifoldComparator);
      int k = 0;
      int m = 1;
      int i;
      for (Dispatcher localDispatcher1 = 0; localDispatcher1 < localDispatcher2; i = paramDispatcher)
      {
        int n = getUnionFind().getElement(localDispatcher1).field_1648;
        int i1 = 0;
        for (paramDispatcher = localDispatcher1; (paramDispatcher < localDispatcher2) && (getUnionFind().getElement(paramDispatcher).field_1648 == n); paramDispatcher++)
        {
          localDispatcher1 = getUnionFind().getElement(paramDispatcher).field_1649;
          CollisionObject localCollisionObject2 = (CollisionObject)paramObjectArrayList.getQuick(localDispatcher1);
          this.islandBodies.add(localCollisionObject2);
          if (!localCollisionObject2.isActive()) {
            i1 = 1;
          }
        }
        localDispatcher1 = 0;
        int i2 = -1;
        if ((k < j) && (getIslandId((PersistentManifold)this.islandmanifold.getQuick(k)) == n))
        {
          i2 = k;
          for (m = k + 1; (m < j) && (n == getIslandId((PersistentManifold)this.islandmanifold.getQuick(m))); m++) {}
          localDispatcher1 = m - k;
        }
        if (i1 == 0) {
          paramIslandCallback.processIsland(this.islandBodies, this.islandBodies.size(), this.islandmanifold, i2, localDispatcher1, n);
        }
        if (localDispatcher1 != 0) {
          k = m;
        }
        this.islandBodies.clear();
      }
      return;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SimulationIslandManagerExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */