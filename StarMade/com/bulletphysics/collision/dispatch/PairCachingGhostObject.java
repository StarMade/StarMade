/*    */ package com.bulletphysics.collision.dispatch;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*    */ import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ 
/*    */ public class PairCachingGhostObject extends GhostObject
/*    */ {
/* 36 */   HashedOverlappingPairCache hashPairCache = new HashedOverlappingPairCache();
/*    */ 
/*    */   public void addOverlappingObjectInternal(BroadphaseProxy otherProxy, BroadphaseProxy thisProxy)
/*    */   {
/* 43 */     BroadphaseProxy actualThisProxy = thisProxy != null ? thisProxy : getBroadphaseHandle();
/* 44 */     assert (actualThisProxy != null);
/*    */ 
/* 46 */     CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/* 47 */     assert (otherObject != null);
/*    */ 
/* 50 */     int index = this.overlappingObjects.indexOf(otherObject);
/* 51 */     if (index == -1) {
/* 52 */       this.overlappingObjects.add(otherObject);
/* 53 */       this.hashPairCache.addOverlappingPair(actualThisProxy, otherProxy);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void removeOverlappingObjectInternal(BroadphaseProxy otherProxy, Dispatcher dispatcher, BroadphaseProxy thisProxy1)
/*    */   {
/* 59 */     CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/* 60 */     BroadphaseProxy actualThisProxy = thisProxy1 != null ? thisProxy1 : getBroadphaseHandle();
/* 61 */     assert (actualThisProxy != null);
/*    */ 
/* 63 */     assert (otherObject != null);
/* 64 */     int index = this.overlappingObjects.indexOf(otherObject);
/* 65 */     if (index != -1) {
/* 66 */       this.overlappingObjects.setQuick(index, this.overlappingObjects.getQuick(this.overlappingObjects.size() - 1));
/* 67 */       this.overlappingObjects.removeQuick(this.overlappingObjects.size() - 1);
/* 68 */       this.hashPairCache.removeOverlappingPair(actualThisProxy, otherProxy, dispatcher);
/*    */     }
/*    */   }
/*    */ 
/*    */   public HashedOverlappingPairCache getOverlappingPairCache() {
/* 73 */     return this.hashPairCache;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.PairCachingGhostObject
 * JD-Core Version:    0.6.2
 */