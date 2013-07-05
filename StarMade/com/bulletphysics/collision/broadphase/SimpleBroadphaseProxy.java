/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ class SimpleBroadphaseProxy extends BroadphaseProxy
/*    */ {
/* 34 */   protected final Vector3f min = new Vector3f();
/* 35 */   protected final Vector3f max = new Vector3f();
/*    */ 
/*    */   public SimpleBroadphaseProxy() {
/*    */   }
/*    */ 
/*    */   public SimpleBroadphaseProxy(Vector3f minpt, Vector3f maxpt, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Object multiSapProxy) {
/* 41 */     super(userPtr, collisionFilterGroup, collisionFilterMask, multiSapProxy);
/* 42 */     this.min.set(minpt);
/* 43 */     this.max.set(maxpt);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.SimpleBroadphaseProxy
 * JD-Core Version:    0.6.2
 */