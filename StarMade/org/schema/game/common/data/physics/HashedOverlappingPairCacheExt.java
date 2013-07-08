/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.BulletStats;
/*  4:   */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*  5:   */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*  6:   */import com.bulletphysics.collision.broadphase.Dispatcher;
/*  7:   */import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*  8:   */import com.bulletphysics.collision.broadphase.OverlapCallback;
/*  9:   */import com.bulletphysics.collision.broadphase.OverlapFilterCallback;
/* 10:   */import com.bulletphysics.util.ObjectArrayList;
/* 11:   */import q;
/* 12:   */
/* 16:   */public class HashedOverlappingPairCacheExt
/* 17:   */  extends HashedOverlappingPairCache
/* 18:   */{
/* 19:   */  public void processAllOverlappingPairs(OverlapCallback paramOverlapCallback, Dispatcher paramDispatcher)
/* 20:   */  {
/* 21:21 */    for (int i = 0; i < getOverlappingPairArray().size();)
/* 22:   */    {
/* 23:23 */      BroadphasePair localBroadphasePair = (BroadphasePair)getOverlappingPairArray().getQuick(i);
/* 24:   */      
/* 25:25 */      if (paramOverlapCallback.processOverlap(localBroadphasePair))
/* 26:   */      {
/* 27:27 */        removeOverlappingPair(localBroadphasePair.pProxy0, localBroadphasePair.pProxy1, paramDispatcher);
/* 28:   */        
/* 29:29 */        BulletStats.gOverlappingPairs -= 1;
/* 30:   */      }
/* 31:   */      else {
/* 32:32 */        i++;
/* 33:   */      }
/* 34:   */    }
/* 35:   */  }
/* 36:   */  
/* 41:   */  public boolean needsBroadphaseCollision(BroadphaseProxy paramBroadphaseProxy1, BroadphaseProxy paramBroadphaseProxy2)
/* 42:   */  {
/* 43:43 */    if (((paramBroadphaseProxy1.clientObject instanceof RigidBodyExt)) && ((paramBroadphaseProxy2.clientObject instanceof RigidBodyExt))) {
/* 44:44 */      RigidBodyExt localRigidBodyExt1 = (RigidBodyExt)paramBroadphaseProxy1.clientObject;
/* 45:45 */      RigidBodyExt localRigidBodyExt2 = (RigidBodyExt)paramBroadphaseProxy2.clientObject;
/* 46:46 */      if ((localRigidBodyExt1.virtualSec != null) && (localRigidBodyExt2.virtualSec != null)) {
/* 47:47 */        return !localRigidBodyExt1.virtualSec.equals(localRigidBodyExt2.virtualSec);
/* 48:   */      }
/* 49:49 */      if ((localRigidBodyExt1.virtualSec != null) && (localRigidBodyExt2.virtualSec == null)) {
/* 50:50 */        return localRigidBodyExt1.getCollisionShape() != localRigidBodyExt2.getCollisionShape();
/* 51:   */      }
/* 52:52 */      if ((localRigidBodyExt1.virtualSec == null) && (localRigidBodyExt2.virtualSec != null)) {
/* 53:53 */        return localRigidBodyExt1.getCollisionShape() != localRigidBodyExt2.getCollisionShape();
/* 54:   */      }
/* 55:   */    }
/* 56:56 */    if (getOverlapFilterCallback() != null) {
/* 57:57 */      return getOverlapFilterCallback().needBroadphaseCollision(paramBroadphaseProxy1, paramBroadphaseProxy2);
/* 58:   */    }
/* 59:   */    
/* 61:61 */    if (((paramBroadphaseProxy1.collisionFilterGroup & paramBroadphaseProxy2.collisionFilterMask) != 0 ? 1 : 0) != 0) if ((paramBroadphaseProxy2.collisionFilterGroup & paramBroadphaseProxy1.collisionFilterMask) != 0) { return true;
/* 62:   */      }
/* 63:63 */    return false;
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.HashedOverlappingPairCacheExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */