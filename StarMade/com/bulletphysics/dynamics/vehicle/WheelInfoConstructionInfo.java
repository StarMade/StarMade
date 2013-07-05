/*    */ package com.bulletphysics.dynamics.vehicle;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class WheelInfoConstructionInfo
/*    */ {
/* 34 */   public final Vector3f chassisConnectionCS = new Vector3f();
/* 35 */   public final Vector3f wheelDirectionCS = new Vector3f();
/* 36 */   public final Vector3f wheelAxleCS = new Vector3f();
/*    */   public float suspensionRestLength;
/*    */   public float maxSuspensionTravelCm;
/*    */   public float wheelRadius;
/*    */   public float suspensionStiffness;
/*    */   public float wheelsDampingCompression;
/*    */   public float wheelsDampingRelaxation;
/*    */   public float frictionSlip;
/*    */   public boolean bIsFrontWheel;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.WheelInfoConstructionInfo
 * JD-Core Version:    0.6.2
 */