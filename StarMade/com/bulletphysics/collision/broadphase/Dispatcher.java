/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*    */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ 
/*    */ public abstract class Dispatcher
/*    */ {
/*    */   public final CollisionAlgorithm findAlgorithm(CollisionObject body0, CollisionObject body1)
/*    */   {
/* 41 */     return findAlgorithm(body0, body1, null);
/*    */   }
/*    */ 
/*    */   public abstract CollisionAlgorithm findAlgorithm(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, PersistentManifold paramPersistentManifold);
/*    */ 
/*    */   public abstract PersistentManifold getNewManifold(Object paramObject1, Object paramObject2);
/*    */ 
/*    */   public abstract void releaseManifold(PersistentManifold paramPersistentManifold);
/*    */ 
/*    */   public abstract void clearManifold(PersistentManifold paramPersistentManifold);
/*    */ 
/*    */   public abstract boolean needsCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2);
/*    */ 
/*    */   public abstract boolean needsResponse(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2);
/*    */ 
/*    */   public abstract void dispatchAllCollisionPairs(OverlappingPairCache paramOverlappingPairCache, DispatcherInfo paramDispatcherInfo, Dispatcher paramDispatcher);
/*    */ 
/*    */   public abstract int getNumManifolds();
/*    */ 
/*    */   public abstract PersistentManifold getManifoldByIndexInternal(int paramInt);
/*    */ 
/*    */   public abstract ObjectArrayList<PersistentManifold> getInternalManifoldPointer();
/*    */ 
/*    */   public abstract void freeCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.Dispatcher
 * JD-Core Version:    0.6.2
 */