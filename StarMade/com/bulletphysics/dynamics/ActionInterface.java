package com.bulletphysics.dynamics;

import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.linearmath.IDebugDraw;

public abstract class ActionInterface
{
  public abstract void updateAction(CollisionWorld paramCollisionWorld, float paramFloat);
  
  public abstract void debugDraw(IDebugDraw paramIDebugDraw);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.ActionInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */