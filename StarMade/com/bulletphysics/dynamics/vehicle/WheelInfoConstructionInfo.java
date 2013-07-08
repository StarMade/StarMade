/*  1:   */package com.bulletphysics.dynamics.vehicle;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 32:   */public class WheelInfoConstructionInfo
/* 33:   */{
/* 34:34 */  public final Vector3f chassisConnectionCS = new Vector3f();
/* 35:35 */  public final Vector3f wheelDirectionCS = new Vector3f();
/* 36:36 */  public final Vector3f wheelAxleCS = new Vector3f();
/* 37:   */  public float suspensionRestLength;
/* 38:   */  public float maxSuspensionTravelCm;
/* 39:   */  public float wheelRadius;
/* 40:   */  public float suspensionStiffness;
/* 41:   */  public float wheelsDampingCompression;
/* 42:   */  public float wheelsDampingRelaxation;
/* 43:   */  public float frictionSlip;
/* 44:   */  public boolean bIsFrontWheel;
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.WheelInfoConstructionInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */