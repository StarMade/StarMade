/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.IDebugDraw;
/*  4:   */
/* 33:   */public class DispatcherInfo
/* 34:   */{
/* 35:   */  public float timeStep;
/* 36:   */  public int stepCount;
/* 37:   */  public DispatchFunc dispatchFunc;
/* 38:   */  public float timeOfImpact;
/* 39:   */  public boolean useContinuous;
/* 40:   */  public IDebugDraw debugDraw;
/* 41:   */  public boolean enableSatConvex;
/* 42:42 */  public boolean enableSPU = true;
/* 43:43 */  public boolean useEpa = true;
/* 44:44 */  public float allowedCcdPenetration = 0.04F;
/* 45:   */  
/* 46:   */  public DispatcherInfo()
/* 47:   */  {
/* 48:48 */    this.dispatchFunc = DispatchFunc.DISPATCH_DISCRETE;
/* 49:49 */    this.timeOfImpact = 1.0F;
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DispatcherInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */