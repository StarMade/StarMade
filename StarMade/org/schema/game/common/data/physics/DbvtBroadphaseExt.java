/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    */ import com.bulletphysics.collision.broadphase.Dbvt;
/*    */ import com.bulletphysics.collision.broadphase.Dbvt.Node;
/*    */ import com.bulletphysics.collision.broadphase.DbvtAabbMm;
/*    */ import com.bulletphysics.collision.broadphase.DbvtBroadphase;
/*    */ import com.bulletphysics.collision.broadphase.DbvtProxy;
/*    */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*    */ import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*    */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class DbvtBroadphaseExt extends DbvtBroadphase
/*    */ {
/* 44 */   private final Vector3f delta = new Vector3f();
/* 45 */   private final Vector3f centerOut = new Vector3f();
/*    */ 
/*    */   public DbvtBroadphaseExt()
/*    */   {
/* 18 */     this(null);
/*    */   }
/*    */ 
/*    */   public DbvtBroadphaseExt(OverlappingPairCache paramOverlappingPairCache)
/*    */   {
/* 23 */     this.sets[0] = new DbvtExt();
/* 24 */     this.sets[1] = new DbvtExt();
/*    */ 
/* 27 */     this.releasepaircache = (paramOverlappingPairCache == null);
/* 28 */     this.predictedframes = 2.0F;
/* 29 */     this.stageCurrent = 0;
/* 30 */     this.fupdates = 1;
/* 31 */     this.dupdates = 1;
/* 32 */     this.paircache = (paramOverlappingPairCache != null ? paramOverlappingPairCache : new HashedOverlappingPairCache());
/* 33 */     this.gid = 0;
/* 34 */     this.pid = 0;
/*    */ 
/* 36 */     for (paramOverlappingPairCache = 0; paramOverlappingPairCache <= 2; paramOverlappingPairCache++)
/* 37 */       this.stageRoots[paramOverlappingPairCache] = null;
/*    */   }
/*    */ 
/*    */   public void setAabb(BroadphaseProxy paramBroadphaseProxy, Vector3f paramVector3f1, Vector3f paramVector3f2, Dispatcher paramDispatcher)
/*    */   {
/* 48 */     paramBroadphaseProxy = (DbvtProxy)paramBroadphaseProxy;
/* 49 */     paramDispatcher = DbvtAabbMm.FromMM(paramVector3f1, paramVector3f2, new DbvtAabbMm());
/* 50 */     if (paramBroadphaseProxy.stage == 2)
/*    */     {
/* 52 */       this.sets[1].remove(paramBroadphaseProxy.leaf);
/* 53 */       paramBroadphaseProxy.leaf = this.sets[0].insert(paramDispatcher, paramBroadphaseProxy);
/*    */     }
/* 57 */     else if (DbvtAabbMm.Intersect(paramBroadphaseProxy.leaf.volume, paramDispatcher)) {
/* 58 */       this.delta.add(paramVector3f1, paramVector3f2);
/* 59 */       this.delta.scale(0.5F);
/* 60 */       this.delta.sub(paramBroadphaseProxy.aabb.Center(this.centerOut));
/*    */ 
/* 62 */       this.delta.scale(this.predictedframes);
/* 63 */       this.sets[0].update(paramBroadphaseProxy.leaf, paramDispatcher, this.delta, 0.05F);
/*    */     }
/*    */     else
/*    */     {
/* 70 */       this.sets[0].update(paramBroadphaseProxy.leaf, paramDispatcher);
/*    */     }
/*    */ 
/* 74 */     this.stageRoots[paramBroadphaseProxy.stage] = listremove(paramBroadphaseProxy, this.stageRoots[paramBroadphaseProxy.stage]);
/* 75 */     paramBroadphaseProxy.aabb.set(paramDispatcher);
/* 76 */     paramBroadphaseProxy.stage = this.stageCurrent;
/* 77 */     this.stageRoots[this.stageCurrent] = listappend(paramBroadphaseProxy, this.stageRoots[this.stageCurrent]);
/*    */   }
/*    */   private static DbvtProxy listremove(DbvtProxy paramDbvtProxy1, DbvtProxy paramDbvtProxy2) {
/* 80 */     if (paramDbvtProxy1.links[0] != null) {
/* 81 */       paramDbvtProxy1.links[0].links[1] = paramDbvtProxy1.links[1];
/*    */     }
/*    */     else {
/* 84 */       paramDbvtProxy2 = paramDbvtProxy1.links[1];
/*    */     }
/*    */ 
/* 87 */     if (paramDbvtProxy1.links[1] != null) {
/* 88 */       paramDbvtProxy1.links[1].links[0] = paramDbvtProxy1.links[0];
/*    */     }
/* 90 */     return paramDbvtProxy2;
/*    */   }
/*    */   private static DbvtProxy listappend(DbvtProxy paramDbvtProxy1, DbvtProxy paramDbvtProxy2) {
/* 93 */     paramDbvtProxy1.links[0] = null;
/* 94 */     paramDbvtProxy1.links[1] = paramDbvtProxy2;
/* 95 */     if (paramDbvtProxy2 != null) paramDbvtProxy2.links[0] = paramDbvtProxy1;
/*    */ 
/* 97 */     return paramDbvtProxy1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.DbvtBroadphaseExt
 * JD-Core Version:    0.6.2
 */