package com.bulletphysics.collision.broadphase;

import com.bulletphysics..Stack;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public class DbvtBroadphase
  extends BroadphaseInterface
{
  public static final float DBVT_BP_MARGIN = 0.05F;
  public static final int DYNAMIC_SET = 0;
  public static final int FIXED_SET = 1;
  public static final int STAGECOUNT = 2;
  public final Dbvt[] sets = new Dbvt[2];
  public DbvtProxy[] stageRoots = new DbvtProxy[3];
  public OverlappingPairCache paircache;
  public float predictedframes;
  public int stageCurrent;
  public int fupdates;
  public int dupdates;
  public int pid;
  public int gid;
  public boolean releasepaircache;
  
  public DbvtBroadphase()
  {
    this(null);
  }
  
  public DbvtBroadphase(OverlappingPairCache paircache)
  {
    this.sets[0] = new Dbvt();
    this.sets[1] = new Dbvt();
    this.releasepaircache = (paircache == null);
    this.predictedframes = 2.0F;
    this.stageCurrent = 0;
    this.fupdates = 1;
    this.dupdates = 1;
    this.paircache = (paircache != null ? paircache : new HashedOverlappingPairCache());
    this.gid = 0;
    this.pid = 0;
    for (int local_i = 0; local_i <= 2; local_i++) {
      this.stageRoots[local_i] = null;
    }
  }
  
  public void collide(Dispatcher dispatcher)
  {
    this.sets[0].optimizeIncremental(1 + this.sets[0].leaves * this.dupdates / 100);
    this.sets[1].optimizeIncremental(1 + this.sets[1].leaves * this.fupdates / 100);
    this.stageCurrent = ((this.stageCurrent + 1) % 2);
    DbvtProxy current = this.stageRoots[this.stageCurrent];
    if (current != null)
    {
      DbvtTreeCollider collider = new DbvtTreeCollider(this);
      do
      {
        DbvtProxy next = current.links[1];
        this.stageRoots[current.stage] = listremove(current, this.stageRoots[current.stage]);
        this.stageRoots[2] = listappend(current, this.stageRoots[2]);
        Dbvt.collideTT(this.sets[1].root, current.leaf, collider);
        this.sets[0].remove(current.leaf);
        current.leaf = this.sets[1].insert(current.aabb, current);
        current.stage = 2;
        current = next;
      } while (current != null);
    }
    DbvtTreeCollider collider = new DbvtTreeCollider(this);
    Dbvt.collideTT(this.sets[0].root, this.sets[1].root, collider);
    Dbvt.collideTT(this.sets[0].root, this.sets[0].root, collider);
    ObjectArrayList<BroadphasePair> collider = this.paircache.getOverlappingPairArray();
    if (collider.size() > 0)
    {
      int next = 0;
      int local_ni = collider.size();
      while (next < local_ni)
      {
        BroadphasePair local_p = (BroadphasePair)collider.getQuick(next);
        DbvtProxy local_pa = (DbvtProxy)local_p.pProxy0;
        DbvtProxy local_pb = (DbvtProxy)local_p.pProxy1;
        if (!DbvtAabbMm.Intersect(local_pa.aabb, local_pb.aabb))
        {
          if (local_pa.hashCode() > local_pb.hashCode())
          {
            DbvtProxy tmp = local_pa;
            local_pa = local_pb;
            local_pb = tmp;
          }
          this.paircache.removeOverlappingPair(local_pa, local_pb, dispatcher);
          local_ni--;
          next--;
        }
        next++;
      }
    }
    this.pid += 1;
  }
  
  private static DbvtProxy listappend(DbvtProxy item, DbvtProxy list)
  {
    item.links[0] = null;
    item.links[1] = list;
    if (list != null) {
      list.links[0] = item;
    }
    list = item;
    return list;
  }
  
  private static DbvtProxy listremove(DbvtProxy item, DbvtProxy list)
  {
    if (item.links[0] != null) {
      item.links[0].links[1] = item.links[1];
    } else {
      list = item.links[1];
    }
    if (item.links[1] != null) {
      item.links[1].links[0] = item.links[0];
    }
    return list;
  }
  
  public BroadphaseProxy createProxy(Vector3f aabbMin, Vector3f aabbMax, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Dispatcher dispatcher, Object multiSapProxy)
  {
    DbvtProxy proxy = new DbvtProxy(userPtr, collisionFilterGroup, collisionFilterMask);
    DbvtAabbMm.FromMM(aabbMin, aabbMax, proxy.aabb);
    proxy.leaf = this.sets[0].insert(proxy.aabb, proxy);
    proxy.stage = this.stageCurrent;
    proxy.uniqueId = (++this.gid);
    this.stageRoots[this.stageCurrent] = listappend(proxy, this.stageRoots[this.stageCurrent]);
    return proxy;
  }
  
  public void destroyProxy(BroadphaseProxy absproxy, Dispatcher dispatcher)
  {
    DbvtProxy proxy = (DbvtProxy)absproxy;
    if (proxy.stage == 2) {
      this.sets[1].remove(proxy.leaf);
    } else {
      this.sets[0].remove(proxy.leaf);
    }
    this.stageRoots[proxy.stage] = listremove(proxy, this.stageRoots[proxy.stage]);
    this.paircache.removeOverlappingPairsContainingProxy(proxy, dispatcher);
  }
  
  public void setAabb(BroadphaseProxy arg1, Vector3f arg2, Vector3f arg3, Dispatcher arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      DbvtProxy proxy = (DbvtProxy)absproxy;
      DbvtAabbMm aabb = DbvtAabbMm.FromMM(aabbMin, aabbMax, new DbvtAabbMm());
      if (proxy.stage == 2)
      {
        this.sets[1].remove(proxy.leaf);
        proxy.leaf = this.sets[0].insert(aabb, proxy);
      }
      else if (DbvtAabbMm.Intersect(proxy.leaf.volume, aabb))
      {
        Vector3f delta = localStack.get$javax$vecmath$Vector3f();
        delta.add(aabbMin, aabbMax);
        delta.scale(0.5F);
        delta.sub(proxy.aabb.Center(localStack.get$javax$vecmath$Vector3f()));
        delta.scale(this.predictedframes);
        this.sets[0].update(proxy.leaf, aabb, delta, 0.05F);
      }
      else
      {
        this.sets[0].update(proxy.leaf, aabb);
      }
      this.stageRoots[proxy.stage] = listremove(proxy, this.stageRoots[proxy.stage]);
      proxy.aabb.set(aabb);
      proxy.stage = this.stageCurrent;
      this.stageRoots[this.stageCurrent] = listappend(proxy, this.stageRoots[this.stageCurrent]);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void calculateOverlappingPairs(Dispatcher dispatcher)
  {
    collide(dispatcher);
  }
  
  public OverlappingPairCache getOverlappingPairCache()
  {
    return this.paircache;
  }
  
  public void getBroadphaseAabb(Vector3f aabbMin, Vector3f aabbMax)
  {
    DbvtAabbMm bounds = new DbvtAabbMm();
    if (!this.sets[0].empty())
    {
      if (!this.sets[1].empty()) {
        DbvtAabbMm.Merge(this.sets[0].root.volume, this.sets[1].root.volume, bounds);
      } else {
        bounds.set(this.sets[0].root.volume);
      }
    }
    else if (!this.sets[1].empty()) {
      bounds.set(this.sets[1].root.volume);
    } else {
      DbvtAabbMm.FromCR(new Vector3f(0.0F, 0.0F, 0.0F), 0.0F, bounds);
    }
    aabbMin.set(bounds.Mins());
    aabbMax.set(bounds.Maxs());
  }
  
  public void printStats() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtBroadphase
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */