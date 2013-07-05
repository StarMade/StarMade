/*    */ package com.bulletphysics.collision.dispatch;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*    */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*    */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ 
/*    */ public class EmptyAlgorithm extends CollisionAlgorithm
/*    */ {
/* 40 */   private static final EmptyAlgorithm INSTANCE = new EmptyAlgorithm();
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*    */   {
/*    */   }
/*    */ 
/*    */   public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*    */   {
/* 52 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*    */   {
/*    */   }
/*    */ 
/*    */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*    */   {
/*    */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*    */     {
/* 64 */       return EmptyAlgorithm.INSTANCE;
/*    */     }
/*    */ 
/*    */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.EmptyAlgorithm
 * JD-Core Version:    0.6.2
 */