/*    */ package com.bulletphysics.collision.dispatch;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*    */ import com.bulletphysics.collision.broadphase.OverlappingPairCallback;
/*    */ 
/*    */ public class GhostPairCallback extends OverlappingPairCallback
/*    */ {
/*    */   public BroadphasePair addOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1)
/*    */   {
/* 41 */     CollisionObject colObj0 = (CollisionObject)proxy0.clientObject;
/* 42 */     CollisionObject colObj1 = (CollisionObject)proxy1.clientObject;
/* 43 */     GhostObject ghost0 = GhostObject.upcast(colObj0);
/* 44 */     GhostObject ghost1 = GhostObject.upcast(colObj1);
/*    */ 
/* 46 */     if (ghost0 != null) {
/* 47 */       ghost0.addOverlappingObjectInternal(proxy1, proxy0);
/*    */     }
/* 49 */     if (ghost1 != null) {
/* 50 */       ghost1.addOverlappingObjectInternal(proxy0, proxy1);
/*    */     }
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   public Object removeOverlappingPair(BroadphaseProxy proxy0, BroadphaseProxy proxy1, Dispatcher dispatcher) {
/* 56 */     CollisionObject colObj0 = (CollisionObject)proxy0.clientObject;
/* 57 */     CollisionObject colObj1 = (CollisionObject)proxy1.clientObject;
/* 58 */     GhostObject ghost0 = GhostObject.upcast(colObj0);
/* 59 */     GhostObject ghost1 = GhostObject.upcast(colObj1);
/*    */ 
/* 61 */     if (ghost0 != null) {
/* 62 */       ghost0.removeOverlappingObjectInternal(proxy1, dispatcher, proxy0);
/*    */     }
/* 64 */     if (ghost1 != null) {
/* 65 */       ghost1.removeOverlappingObjectInternal(proxy0, dispatcher, proxy1);
/*    */     }
/* 67 */     return null;
/*    */   }
/*    */ 
/*    */   public void removeOverlappingPairsContainingProxy(BroadphaseProxy proxy0, Dispatcher dispatcher) {
/* 71 */     if (!$assertionsDisabled) throw new AssertionError();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.GhostPairCallback
 * JD-Core Version:    0.6.2
 */