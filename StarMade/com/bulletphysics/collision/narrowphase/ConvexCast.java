package com.bulletphysics.collision.narrowphase;

import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public abstract class ConvexCast
{
  public abstract boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, CastResult paramCastResult);
  
  public static class CastResult
  {
    public final Transform hitTransformA = new Transform();
    public final Transform hitTransformB = new Transform();
    public final Vector3f normal = new Vector3f();
    public final Vector3f hitPoint = new Vector3f();
    public float fraction = 1.0E+030F;
    public float allowedPenetration = 0.0F;
    public IDebugDraw debugDrawer;
    
    public void debugDraw(float fraction) {}
    
    public void drawCoordSystem(Transform trans) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ConvexCast
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */