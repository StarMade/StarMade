/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.BulletStats;
/*    */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*    */ import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*    */ import com.bulletphysics.collision.broadphase.OverlapCallback;
/*    */ import com.bulletphysics.collision.broadphase.OverlapFilterCallback;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ import q;
/*    */ 
/*    */ public class HashedOverlappingPairCacheExt extends HashedOverlappingPairCache
/*    */ {
/*    */   public void processAllOverlappingPairs(OverlapCallback paramOverlapCallback, Dispatcher paramDispatcher)
/*    */   {
/* 21 */     for (int i = 0; i < getOverlappingPairArray().size(); )
/*    */     {
/* 23 */       BroadphasePair localBroadphasePair = (BroadphasePair)getOverlappingPairArray().getQuick(i);
/*    */ 
/* 25 */       if (paramOverlapCallback.processOverlap(localBroadphasePair))
/*    */       {
/* 27 */         removeOverlappingPair(localBroadphasePair.pProxy0, localBroadphasePair.pProxy1, paramDispatcher);
/*    */ 
/* 29 */         BulletStats.gOverlappingPairs -= 1;
/*    */       }
/*    */       else {
/* 32 */         i++;
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean needsBroadphaseCollision(BroadphaseProxy paramBroadphaseProxy1, BroadphaseProxy paramBroadphaseProxy2)
/*    */   {
/* 43 */     if (((paramBroadphaseProxy1.clientObject instanceof RigidBodyExt)) && ((paramBroadphaseProxy2.clientObject instanceof RigidBodyExt))) {
/* 44 */       RigidBodyExt localRigidBodyExt1 = (RigidBodyExt)paramBroadphaseProxy1.clientObject;
/* 45 */       RigidBodyExt localRigidBodyExt2 = (RigidBodyExt)paramBroadphaseProxy2.clientObject;
/* 46 */       if ((localRigidBodyExt1.virtualSec != null) && (localRigidBodyExt2.virtualSec != null)) {
/* 47 */         return !localRigidBodyExt1.virtualSec.equals(localRigidBodyExt2.virtualSec);
/*    */       }
/* 49 */       if ((localRigidBodyExt1.virtualSec != null) && (localRigidBodyExt2.virtualSec == null)) {
/* 50 */         return localRigidBodyExt1.getCollisionShape() != localRigidBodyExt2.getCollisionShape();
/*    */       }
/* 52 */       if ((localRigidBodyExt1.virtualSec == null) && (localRigidBodyExt2.virtualSec != null)) {
/* 53 */         return localRigidBodyExt1.getCollisionShape() != localRigidBodyExt2.getCollisionShape();
/*    */       }
/*    */     }
/* 56 */     if (getOverlapFilterCallback() != null) {
/* 57 */       return getOverlapFilterCallback().needBroadphaseCollision(paramBroadphaseProxy1, paramBroadphaseProxy2);
/*    */     }
/*    */ 
/* 61 */     if (((paramBroadphaseProxy1.collisionFilterGroup & paramBroadphaseProxy2.collisionFilterMask) != 0 ? 1 : 0) != 0) {
/* 61 */       if ((paramBroadphaseProxy2.collisionFilterGroup & paramBroadphaseProxy1.collisionFilterMask) != 0) return true;
/*    */     }
/* 63 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.HashedOverlappingPairCacheExt
 * JD-Core Version:    0.6.2
 */