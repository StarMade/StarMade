/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*  4:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  5:   */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*  6:   */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  7:   */import com.bulletphysics.util.ObjectArrayList;
/*  8:   */
/* 42:   */public abstract class CollisionAlgorithm
/* 43:   */{
/* 44:   */  private CollisionAlgorithmCreateFunc createFunc;
/* 45:   */  protected Dispatcher dispatcher;
/* 46:   */  
/* 47:   */  public void init() {}
/* 48:   */  
/* 49:   */  public void init(CollisionAlgorithmConstructionInfo ci)
/* 50:   */  {
/* 51:51 */    this.dispatcher = ci.dispatcher1;
/* 52:   */  }
/* 53:   */  
/* 54:   */  public abstract void destroy();
/* 55:   */  
/* 56:   */  public abstract void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult);
/* 57:   */  
/* 58:   */  public abstract float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult);
/* 59:   */  
/* 60:   */  public abstract void getAllContactManifolds(ObjectArrayList<PersistentManifold> paramObjectArrayList);
/* 61:   */  
/* 62:   */  public final void internalSetCreateFunc(CollisionAlgorithmCreateFunc func) {
/* 63:63 */    this.createFunc = func;
/* 64:   */  }
/* 65:   */  
/* 66:   */  public final CollisionAlgorithmCreateFunc internalGetCreateFunc() {
/* 67:67 */    return this.createFunc;
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.CollisionAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */