package org.schema.game.common.data.physics;

import class_48;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.collision.broadphase.OverlapCallback;
import com.bulletphysics.collision.broadphase.OverlapFilterCallback;
import com.bulletphysics.util.ObjectArrayList;

public class HashedOverlappingPairCacheExt
  extends HashedOverlappingPairCache
{
  public void processAllOverlappingPairs(OverlapCallback paramOverlapCallback, Dispatcher paramDispatcher)
  {
    int i = 0;
    while (i < getOverlappingPairArray().size())
    {
      BroadphasePair localBroadphasePair = (BroadphasePair)getOverlappingPairArray().getQuick(i);
      if (paramOverlapCallback.processOverlap(localBroadphasePair))
      {
        removeOverlappingPair(localBroadphasePair.pProxy0, localBroadphasePair.pProxy1, paramDispatcher);
        BulletStats.gOverlappingPairs -= 1;
      }
      else
      {
        i++;
      }
    }
  }
  
  public boolean needsBroadphaseCollision(BroadphaseProxy paramBroadphaseProxy1, BroadphaseProxy paramBroadphaseProxy2)
  {
    if (((paramBroadphaseProxy1.clientObject instanceof RigidBodyExt)) && ((paramBroadphaseProxy2.clientObject instanceof RigidBodyExt)))
    {
      RigidBodyExt localRigidBodyExt1 = (RigidBodyExt)paramBroadphaseProxy1.clientObject;
      RigidBodyExt localRigidBodyExt2 = (RigidBodyExt)paramBroadphaseProxy2.clientObject;
      if ((localRigidBodyExt1.virtualSec != null) && (localRigidBodyExt2.virtualSec != null)) {
        return !localRigidBodyExt1.virtualSec.equals(localRigidBodyExt2.virtualSec);
      }
      if ((localRigidBodyExt1.virtualSec != null) && (localRigidBodyExt2.virtualSec == null)) {
        return localRigidBodyExt1.getCollisionShape() != localRigidBodyExt2.getCollisionShape();
      }
      if ((localRigidBodyExt1.virtualSec == null) && (localRigidBodyExt2.virtualSec != null)) {
        return localRigidBodyExt1.getCollisionShape() != localRigidBodyExt2.getCollisionShape();
      }
    }
    if (getOverlapFilterCallback() != null) {
      return getOverlapFilterCallback().needBroadphaseCollision(paramBroadphaseProxy1, paramBroadphaseProxy2);
    }
    if (((paramBroadphaseProxy1.collisionFilterGroup & paramBroadphaseProxy2.collisionFilterMask) != 0 ? 1 : 0) != 0) {
      if ((paramBroadphaseProxy2.collisionFilterGroup & paramBroadphaseProxy1.collisionFilterMask) != 0) {
        return true;
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.HashedOverlappingPairCacheExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */