/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*    */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*    */ import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*    */ import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ 
/*    */ public class CollisionDispatcherExt extends CollisionDispatcher
/*    */ {
/*    */   public CollisionDispatcherExt(CollisionConfiguration paramCollisionConfiguration)
/*    */   {
/* 20 */     super(paramCollisionConfiguration);
/*    */ 
/* 22 */     setNearCallback(new DefaultNearCallbackExt());
/*    */   }
/*    */ 
/*    */   public void freeCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm) {
/* 26 */     CollisionAlgorithmCreateFunc localCollisionAlgorithmCreateFunc = paramCollisionAlgorithm.internalGetCreateFunc();
/* 27 */     paramCollisionAlgorithm.internalSetCreateFunc(null);
/* 28 */     localCollisionAlgorithmCreateFunc.releaseCollisionAlgorithm(paramCollisionAlgorithm);
/*    */ 
/* 30 */     paramCollisionAlgorithm.destroy();
/*    */   }
/*    */ 
/*    */   public boolean needsResponse(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/*    */   {
/* 41 */     if ((((paramCollisionObject1 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject2.getCollisionShape() instanceof CubesCompoundShape))) || (((paramCollisionObject2 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject1.getCollisionShape() instanceof CubesCompoundShape))))
/*    */     {
/* 43 */       return false;
/*    */     }
/* 45 */     if ((((paramCollisionObject1 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject2.getCollisionShape() instanceof CubeShape))) || (((paramCollisionObject2 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject1.getCollisionShape() instanceof CubeShape))))
/*    */     {
/* 47 */       return false;
/*    */     }
/* 49 */     return super.needsResponse(paramCollisionObject1, paramCollisionObject2);
/*    */   }
/*    */ 
/*    */   public void releaseManifold(PersistentManifold paramPersistentManifold)
/*    */   {
/* 70 */     super.releaseManifold(paramPersistentManifold);
/*    */ 
/* 72 */     assert (checkInternalManifoldDestroyed(paramPersistentManifold));
/*    */   }
/*    */ 
/*    */   private boolean checkInternalManifoldDestroyed(PersistentManifold paramPersistentManifold) {
/* 76 */     ObjectArrayList localObjectArrayList = getInternalManifoldPointer();
/* 77 */     for (int i = 0; i < localObjectArrayList.size(); i++)
/*    */     {
/* 82 */       if ((PersistentManifold)localObjectArrayList.getQuick(i) == 
/* 82 */         paramPersistentManifold) {
/* 83 */         return false;
/*    */       }
/*    */     }
/*    */ 
/* 87 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CollisionDispatcherExt
 * JD-Core Version:    0.6.2
 */