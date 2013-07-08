package com.bulletphysics;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;

public abstract class ContactAddedCallback
{
  public abstract boolean contactAdded(ManifoldPoint paramManifoldPoint, CollisionObject paramCollisionObject1, int paramInt1, int paramInt2, CollisionObject paramCollisionObject2, int paramInt3, int paramInt4);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.ContactAddedCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */