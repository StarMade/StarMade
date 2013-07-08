/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  4:   */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  5:   */import com.bulletphysics.util.ObjectArrayList;
/*  6:   */
/* 37:   */public abstract class Dispatcher
/* 38:   */{
/* 39:   */  public final CollisionAlgorithm findAlgorithm(CollisionObject body0, CollisionObject body1)
/* 40:   */  {
/* 41:41 */    return findAlgorithm(body0, body1, null);
/* 42:   */  }
/* 43:   */  
/* 44:   */  public abstract CollisionAlgorithm findAlgorithm(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, PersistentManifold paramPersistentManifold);
/* 45:   */  
/* 46:   */  public abstract PersistentManifold getNewManifold(Object paramObject1, Object paramObject2);
/* 47:   */  
/* 48:   */  public abstract void releaseManifold(PersistentManifold paramPersistentManifold);
/* 49:   */  
/* 50:   */  public abstract void clearManifold(PersistentManifold paramPersistentManifold);
/* 51:   */  
/* 52:   */  public abstract boolean needsCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2);
/* 53:   */  
/* 54:   */  public abstract boolean needsResponse(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2);
/* 55:   */  
/* 56:   */  public abstract void dispatchAllCollisionPairs(OverlappingPairCache paramOverlappingPairCache, DispatcherInfo paramDispatcherInfo, Dispatcher paramDispatcher);
/* 57:   */  
/* 58:   */  public abstract int getNumManifolds();
/* 59:   */  
/* 60:   */  public abstract PersistentManifold getManifoldByIndexInternal(int paramInt);
/* 61:   */  
/* 62:   */  public abstract ObjectArrayList<PersistentManifold> getInternalManifoldPointer();
/* 63:   */  
/* 64:   */  public abstract void freeCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm);
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.Dispatcher
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */