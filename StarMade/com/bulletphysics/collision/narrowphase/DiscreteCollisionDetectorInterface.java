package com.bulletphysics.collision.narrowphase;

import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class DiscreteCollisionDetectorInterface
{
  public final void getClosestPoints(ClosestPointInput input, Result output, IDebugDraw debugDraw)
  {
    getClosestPoints(input, output, debugDraw, false);
  }
  
  public abstract void getClosestPoints(ClosestPointInput paramClosestPointInput, Result paramResult, IDebugDraw paramIDebugDraw, boolean paramBoolean);
  
  public static class ClosestPointInput
  {
    public final Transform transformA = new Transform();
    public final Transform transformB = new Transform();
    public float maximumDistanceSquared;
    
    public ClosestPointInput()
    {
      init();
    }
    
    public void init()
    {
      this.maximumDistanceSquared = 3.4028235E+38F;
    }
  }
  
  public static abstract class Result
  {
    public abstract void setShapeIdentifiers(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract void addContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */