/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ import com.bulletphysics.linearmath.IDebugDraw;
/*    */ 
/*    */ public class DispatcherInfo
/*    */ {
/*    */   public float timeStep;
/*    */   public int stepCount;
/*    */   public DispatchFunc dispatchFunc;
/*    */   public float timeOfImpact;
/*    */   public boolean useContinuous;
/*    */   public IDebugDraw debugDraw;
/*    */   public boolean enableSatConvex;
/* 42 */   public boolean enableSPU = true;
/* 43 */   public boolean useEpa = true;
/* 44 */   public float allowedCcdPenetration = 0.04F;
/*    */ 
/*    */   public DispatcherInfo()
/*    */   {
/* 48 */     this.dispatchFunc = DispatchFunc.DISPATCH_DISCRETE;
/* 49 */     this.timeOfImpact = 1.0F;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DispatcherInfo
 * JD-Core Version:    0.6.2
 */