/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ public class BroadphaseProxy
/*    */ {
/*    */   public Object clientObject;
/*    */   public short collisionFilterGroup;
/*    */   public short collisionFilterMask;
/*    */   public Object multiSapParentProxy;
/*    */   public int uniqueId;
/*    */ 
/*    */   public BroadphaseProxy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BroadphaseProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask)
/*    */   {
/* 53 */     this(userPtr, collisionFilterGroup, collisionFilterMask, null);
/*    */   }
/*    */ 
/*    */   public BroadphaseProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask, Object multiSapParentProxy) {
/* 57 */     this.clientObject = userPtr;
/* 58 */     this.collisionFilterGroup = collisionFilterGroup;
/* 59 */     this.collisionFilterMask = collisionFilterMask;
/* 60 */     this.multiSapParentProxy = multiSapParentProxy;
/*    */   }
/*    */ 
/*    */   public int getUid() {
/* 64 */     return this.uniqueId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseProxy
 * JD-Core Version:    0.6.2
 */