package com.bulletphysics.collision.broadphase;

import com.bulletphysics.linearmath.IDebugDraw;

public class DispatcherInfo
{
  public float timeStep;
  public int stepCount;
  public DispatchFunc dispatchFunc = DispatchFunc.DISPATCH_DISCRETE;
  public float timeOfImpact = 1.0F;
  public boolean useContinuous;
  public IDebugDraw debugDraw;
  public boolean enableSatConvex;
  public boolean enableSPU = true;
  public boolean useEpa = true;
  public float allowedCcdPenetration = 0.04F;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.DispatcherInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */