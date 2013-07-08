/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*  4:   */import com.bulletphysics.collision.broadphase.Dbvt;
/*  5:   */import com.bulletphysics.collision.broadphase.Dbvt.Node;
/*  6:   */import com.bulletphysics.collision.broadphase.DbvtAabbMm;
/*  7:   */import com.bulletphysics.collision.broadphase.DbvtBroadphase;
/*  8:   */import com.bulletphysics.collision.broadphase.DbvtProxy;
/*  9:   */import com.bulletphysics.collision.broadphase.Dispatcher;
/* 10:   */import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/* 11:   */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/* 12:   */import javax.vecmath.Vector3f;
/* 13:   */
/* 14:   */public class DbvtBroadphaseExt extends DbvtBroadphase
/* 15:   */{
/* 16:   */  public DbvtBroadphaseExt()
/* 17:   */  {
/* 18:18 */    this(null);
/* 19:   */  }
/* 20:   */  
/* 21:   */  public DbvtBroadphaseExt(OverlappingPairCache paramOverlappingPairCache)
/* 22:   */  {
/* 23:23 */    this.sets[0] = new DbvtExt();
/* 24:24 */    this.sets[1] = new DbvtExt();
/* 25:   */    
/* 27:27 */    this.releasepaircache = (paramOverlappingPairCache == null);
/* 28:28 */    this.predictedframes = 2.0F;
/* 29:29 */    this.stageCurrent = 0;
/* 30:30 */    this.fupdates = 1;
/* 31:31 */    this.dupdates = 1;
/* 32:32 */    this.paircache = (paramOverlappingPairCache != null ? paramOverlappingPairCache : new HashedOverlappingPairCache());
/* 33:33 */    this.gid = 0;
/* 34:34 */    this.pid = 0;
/* 35:   */    
/* 36:36 */    for (paramOverlappingPairCache = 0; paramOverlappingPairCache <= 2; paramOverlappingPairCache++) {
/* 37:37 */      this.stageRoots[paramOverlappingPairCache] = null;
/* 38:   */    }
/* 39:   */  }
/* 40:   */  
/* 44:44 */  private final Vector3f delta = new Vector3f();
/* 45:45 */  private final Vector3f centerOut = new Vector3f();
/* 46:   */  
/* 47:   */  public void setAabb(BroadphaseProxy paramBroadphaseProxy, Vector3f paramVector3f1, Vector3f paramVector3f2, Dispatcher paramDispatcher) {
/* 48:48 */    paramBroadphaseProxy = (DbvtProxy)paramBroadphaseProxy;
/* 49:49 */    paramDispatcher = DbvtAabbMm.FromMM(paramVector3f1, paramVector3f2, new DbvtAabbMm());
/* 50:50 */    if (paramBroadphaseProxy.stage == 2)
/* 51:   */    {
/* 52:52 */      this.sets[1].remove(paramBroadphaseProxy.leaf);
/* 53:53 */      paramBroadphaseProxy.leaf = this.sets[0].insert(paramDispatcher, paramBroadphaseProxy);
/* 56:   */    }
/* 57:57 */    else if (DbvtAabbMm.Intersect(paramBroadphaseProxy.leaf.volume, paramDispatcher)) {
/* 58:58 */      this.delta.add(paramVector3f1, paramVector3f2);
/* 59:59 */      this.delta.scale(0.5F);
/* 60:60 */      this.delta.sub(paramBroadphaseProxy.aabb.Center(this.centerOut));
/* 61:   */      
/* 62:62 */      this.delta.scale(this.predictedframes);
/* 63:63 */      this.sets[0].update(paramBroadphaseProxy.leaf, paramDispatcher, this.delta, 0.05F);
/* 66:   */    }
/* 67:   */    else
/* 68:   */    {
/* 70:70 */      this.sets[0].update(paramBroadphaseProxy.leaf, paramDispatcher);
/* 71:   */    }
/* 72:   */    
/* 74:74 */    this.stageRoots[paramBroadphaseProxy.stage] = listremove(paramBroadphaseProxy, this.stageRoots[paramBroadphaseProxy.stage]);
/* 75:75 */    paramBroadphaseProxy.aabb.set(paramDispatcher);
/* 76:76 */    paramBroadphaseProxy.stage = this.stageCurrent;
/* 77:77 */    this.stageRoots[this.stageCurrent] = listappend(paramBroadphaseProxy, this.stageRoots[this.stageCurrent]);
/* 78:   */  }
/* 79:   */  
/* 80:80 */  private static DbvtProxy listremove(DbvtProxy paramDbvtProxy1, DbvtProxy paramDbvtProxy2) { if (paramDbvtProxy1.links[0] != null) {
/* 81:81 */      paramDbvtProxy1.links[0].links[1] = paramDbvtProxy1.links[1];
/* 82:   */    }
/* 83:   */    else {
/* 84:84 */      paramDbvtProxy2 = paramDbvtProxy1.links[1];
/* 85:   */    }
/* 86:   */    
/* 87:87 */    if (paramDbvtProxy1.links[1] != null) {
/* 88:88 */      paramDbvtProxy1.links[1].links[0] = paramDbvtProxy1.links[0];
/* 89:   */    }
/* 90:90 */    return paramDbvtProxy2;
/* 91:   */  }
/* 92:   */  
/* 93:93 */  private static DbvtProxy listappend(DbvtProxy paramDbvtProxy1, DbvtProxy paramDbvtProxy2) { paramDbvtProxy1.links[0] = null;
/* 94:94 */    paramDbvtProxy1.links[1] = paramDbvtProxy2;
/* 95:95 */    if (paramDbvtProxy2 != null) { paramDbvtProxy2.links[0] = paramDbvtProxy1;
/* 96:   */    }
/* 97:97 */    return paramDbvtProxy1;
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.DbvtBroadphaseExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */