/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*  4:   */import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*  5:   */import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*  6:   */import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*  7:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  8:   */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  9:   */import com.bulletphysics.util.ObjectArrayList;
/* 10:   */
/* 15:   */public class CollisionDispatcherExt
/* 16:   */  extends CollisionDispatcher
/* 17:   */{
/* 18:   */  public CollisionDispatcherExt(CollisionConfiguration paramCollisionConfiguration)
/* 19:   */  {
/* 20:20 */    super(paramCollisionConfiguration);
/* 21:   */    
/* 22:22 */    setNearCallback(new DefaultNearCallbackExt());
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void freeCollisionAlgorithm(CollisionAlgorithm paramCollisionAlgorithm) {
/* 26:26 */    CollisionAlgorithmCreateFunc localCollisionAlgorithmCreateFunc = paramCollisionAlgorithm.internalGetCreateFunc();
/* 27:27 */    paramCollisionAlgorithm.internalSetCreateFunc(null);
/* 28:28 */    localCollisionAlgorithmCreateFunc.releaseCollisionAlgorithm(paramCollisionAlgorithm);
/* 29:   */    
/* 30:30 */    paramCollisionAlgorithm.destroy();
/* 31:   */  }
/* 32:   */  
/* 39:   */  public boolean needsResponse(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2)
/* 40:   */  {
/* 41:41 */    if ((((paramCollisionObject1 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject2.getCollisionShape() instanceof CubesCompoundShape))) || (((paramCollisionObject2 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject1.getCollisionShape() instanceof CubesCompoundShape))))
/* 42:   */    {
/* 43:43 */      return false;
/* 44:   */    }
/* 45:45 */    if ((((paramCollisionObject1 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject2.getCollisionShape() instanceof CubeShape))) || (((paramCollisionObject2 instanceof PairCachingGhostObjectExt)) && ((paramCollisionObject1.getCollisionShape() instanceof CubeShape))))
/* 46:   */    {
/* 47:47 */      return false;
/* 48:   */    }
/* 49:49 */    return super.needsResponse(paramCollisionObject1, paramCollisionObject2);
/* 50:   */  }
/* 51:   */  
/* 71:   */  public void releaseManifold(PersistentManifold paramPersistentManifold)
/* 72:   */  {
/* 73:73 */    super.releaseManifold(paramPersistentManifold);
/* 74:   */    
/* 75:75 */    assert (checkInternalManifoldDestroyed(paramPersistentManifold));
/* 76:   */  }
/* 77:   */  
/* 78:   */  private boolean checkInternalManifoldDestroyed(PersistentManifold paramPersistentManifold) {
/* 79:79 */    ObjectArrayList localObjectArrayList = getInternalManifoldPointer();
/* 80:80 */    for (int i = 0; i < localObjectArrayList.size(); i++)
/* 81:   */    {
/* 85:85 */      if ((PersistentManifold)localObjectArrayList.getQuick(i) == paramPersistentManifold) {
/* 86:86 */        return false;
/* 87:   */      }
/* 88:   */    }
/* 89:   */    
/* 90:90 */    return true;
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CollisionDispatcherExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */