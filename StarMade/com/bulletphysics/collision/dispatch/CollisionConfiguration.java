package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;

public abstract class CollisionConfiguration
{
  public abstract CollisionAlgorithmCreateFunc getCollisionAlgorithmCreateFunc(BroadphaseNativeType paramBroadphaseNativeType1, BroadphaseNativeType paramBroadphaseNativeType2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionConfiguration
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */