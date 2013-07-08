/*  1:   */package com.bulletphysics.collision.dispatch;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  4:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*  5:   */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*  6:   */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  7:   */import com.bulletphysics.util.ObjectArrayList;
/*  8:   */
/* 37:   */public class EmptyAlgorithm
/* 38:   */  extends CollisionAlgorithm
/* 39:   */{
/* 40:40 */  private static final EmptyAlgorithm INSTANCE = new EmptyAlgorithm();
/* 41:   */  
/* 44:   */  public void destroy() {}
/* 45:   */  
/* 47:   */  public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) {}
/* 48:   */  
/* 50:   */  public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/* 51:   */  {
/* 52:52 */    return 1.0F;
/* 53:   */  }
/* 54:   */  
/* 56:   */  public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray) {}
/* 57:   */  
/* 59:   */  public static class CreateFunc
/* 60:   */    extends CollisionAlgorithmCreateFunc
/* 61:   */  {
/* 62:   */    public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/* 63:   */    {
/* 64:64 */      return EmptyAlgorithm.INSTANCE;
/* 65:   */    }
/* 66:   */    
/* 67:   */    public void releaseCollisionAlgorithm(CollisionAlgorithm algo) {}
/* 68:   */  }
/* 69:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.EmptyAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */