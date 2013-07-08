package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dbvt;
import com.bulletphysics.collision.broadphase.Dbvt.Node;
import com.bulletphysics.collision.broadphase.DbvtAabbMm;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.broadphase.DbvtProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import javax.vecmath.Vector3f;

public class DbvtBroadphaseExt
  extends DbvtBroadphase
{
  private final Vector3f delta = new Vector3f();
  private final Vector3f centerOut = new Vector3f();
  
  public DbvtBroadphaseExt()
  {
    this(null);
  }
  
  public DbvtBroadphaseExt(OverlappingPairCache paramOverlappingPairCache)
  {
    this.sets[0] = new DbvtExt();
    this.sets[1] = new DbvtExt();
    this.releasepaircache = (paramOverlappingPairCache == null);
    this.predictedframes = 2.0F;
    this.stageCurrent = 0;
    this.fupdates = 1;
    this.dupdates = 1;
    this.paircache = (paramOverlappingPairCache != null ? paramOverlappingPairCache : new HashedOverlappingPairCache());
    this.gid = 0;
    this.pid = 0;
    for (paramOverlappingPairCache = 0; paramOverlappingPairCache <= 2; paramOverlappingPairCache++) {
      this.stageRoots[paramOverlappingPairCache] = null;
    }
  }
  
  public void setAabb(BroadphaseProxy paramBroadphaseProxy, Vector3f paramVector3f1, Vector3f paramVector3f2, Dispatcher paramDispatcher)
  {
    paramBroadphaseProxy = (DbvtProxy)paramBroadphaseProxy;
    paramDispatcher = DbvtAabbMm.FromMM(paramVector3f1, paramVector3f2, new DbvtAabbMm());
    if (paramBroadphaseProxy.stage == 2)
    {
      this.sets[1].remove(paramBroadphaseProxy.leaf);
      paramBroadphaseProxy.leaf = this.sets[0].insert(paramDispatcher, paramBroadphaseProxy);
    }
    else if (DbvtAabbMm.Intersect(paramBroadphaseProxy.leaf.volume, paramDispatcher))
    {
      this.delta.add(paramVector3f1, paramVector3f2);
      this.delta.scale(0.5F);
      this.delta.sub(paramBroadphaseProxy.aabb.Center(this.centerOut));
      this.delta.scale(this.predictedframes);
      this.sets[0].update(paramBroadphaseProxy.leaf, paramDispatcher, this.delta, 0.05F);
    }
    else
    {
      this.sets[0].update(paramBroadphaseProxy.leaf, paramDispatcher);
    }
    this.stageRoots[paramBroadphaseProxy.stage] = listremove(paramBroadphaseProxy, this.stageRoots[paramBroadphaseProxy.stage]);
    paramBroadphaseProxy.aabb.set(paramDispatcher);
    paramBroadphaseProxy.stage = this.stageCurrent;
    this.stageRoots[this.stageCurrent] = listappend(paramBroadphaseProxy, this.stageRoots[this.stageCurrent]);
  }
  
  private static DbvtProxy listremove(DbvtProxy paramDbvtProxy1, DbvtProxy paramDbvtProxy2)
  {
    if (paramDbvtProxy1.links[0] != null) {
      paramDbvtProxy1.links[0].links[1] = paramDbvtProxy1.links[1];
    } else {
      paramDbvtProxy2 = paramDbvtProxy1.links[1];
    }
    if (paramDbvtProxy1.links[1] != null) {
      paramDbvtProxy1.links[1].links[0] = paramDbvtProxy1.links[0];
    }
    return paramDbvtProxy2;
  }
  
  private static DbvtProxy listappend(DbvtProxy paramDbvtProxy1, DbvtProxy paramDbvtProxy2)
  {
    paramDbvtProxy1.links[0] = null;
    paramDbvtProxy1.links[1] = paramDbvtProxy2;
    if (paramDbvtProxy2 != null) {
      paramDbvtProxy2.links[0] = paramDbvtProxy1;
    }
    return paramDbvtProxy1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.DbvtBroadphaseExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */