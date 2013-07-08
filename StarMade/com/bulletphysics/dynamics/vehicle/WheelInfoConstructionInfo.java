package com.bulletphysics.dynamics.vehicle;

import javax.vecmath.Vector3f;

public class WheelInfoConstructionInfo
{
  public final Vector3f chassisConnectionCS = new Vector3f();
  public final Vector3f wheelDirectionCS = new Vector3f();
  public final Vector3f wheelAxleCS = new Vector3f();
  public float suspensionRestLength;
  public float maxSuspensionTravelCm;
  public float wheelRadius;
  public float suspensionStiffness;
  public float wheelsDampingCompression;
  public float wheelsDampingRelaxation;
  public float frictionSlip;
  public boolean bIsFrontWheel;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.WheelInfoConstructionInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */