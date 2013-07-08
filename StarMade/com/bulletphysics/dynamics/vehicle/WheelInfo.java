package com.bulletphysics.dynamics.vehicle;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class WheelInfo
{
  public final RaycastInfo raycastInfo = new RaycastInfo();
  public final Transform worldTransform = new Transform();
  public final Vector3f chassisConnectionPointCS = new Vector3f();
  public final Vector3f wheelDirectionCS = new Vector3f();
  public final Vector3f wheelAxleCS = new Vector3f();
  public float suspensionRestLength1;
  public float maxSuspensionTravelCm;
  public float wheelsRadius;
  public float suspensionStiffness;
  public float wheelsDampingCompression;
  public float wheelsDampingRelaxation;
  public float frictionSlip;
  public float steering;
  public float rotation;
  public float deltaRotation;
  public float rollInfluence;
  public float engineForce;
  public float brake;
  public boolean bIsFrontWheel;
  public Object clientInfo;
  public float clippedInvContactDotSuspension;
  public float suspensionRelativeVelocity;
  public float wheelsSuspensionForce;
  public float skidInfo;
  
  public WheelInfo(WheelInfoConstructionInfo local_ci)
  {
    this.suspensionRestLength1 = local_ci.suspensionRestLength;
    this.maxSuspensionTravelCm = local_ci.maxSuspensionTravelCm;
    this.wheelsRadius = local_ci.wheelRadius;
    this.suspensionStiffness = local_ci.suspensionStiffness;
    this.wheelsDampingCompression = local_ci.wheelsDampingCompression;
    this.wheelsDampingRelaxation = local_ci.wheelsDampingRelaxation;
    this.chassisConnectionPointCS.set(local_ci.chassisConnectionCS);
    this.wheelDirectionCS.set(local_ci.wheelDirectionCS);
    this.wheelAxleCS.set(local_ci.wheelAxleCS);
    this.frictionSlip = local_ci.frictionSlip;
    this.steering = 0.0F;
    this.engineForce = 0.0F;
    this.rotation = 0.0F;
    this.deltaRotation = 0.0F;
    this.brake = 0.0F;
    this.rollInfluence = 0.1F;
    this.bIsFrontWheel = local_ci.bIsFrontWheel;
  }
  
  public float getSuspensionRestLength()
  {
    return this.suspensionRestLength1;
  }
  
  public void updateWheel(RigidBody arg1, RaycastInfo arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (raycastInfo.isInContact)
      {
        float project = raycastInfo.contactNormalWS.dot(raycastInfo.wheelDirectionWS);
        Vector3f chassis_velocity_at_contactPoint = localStack.get$javax$vecmath$Vector3f();
        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
        relpos.sub(raycastInfo.contactPointWS, chassis.getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
        chassis.getVelocityInLocalPoint(relpos, chassis_velocity_at_contactPoint);
        float projVel = raycastInfo.contactNormalWS.dot(chassis_velocity_at_contactPoint);
        if (project >= -0.1F)
        {
          this.suspensionRelativeVelocity = 0.0F;
          this.clippedInvContactDotSuspension = 10.0F;
        }
        else
        {
          float inv = -1.0F / project;
          this.suspensionRelativeVelocity = (projVel * inv);
          this.clippedInvContactDotSuspension = inv;
        }
      }
      else
      {
        raycastInfo.suspensionLength = getSuspensionRestLength();
        this.suspensionRelativeVelocity = 0.0F;
        raycastInfo.contactNormalWS.negate(raycastInfo.wheelDirectionWS);
        this.clippedInvContactDotSuspension = 1.0F;
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static class RaycastInfo
  {
    public final Vector3f contactNormalWS = new Vector3f();
    public final Vector3f contactPointWS = new Vector3f();
    public float suspensionLength;
    public final Vector3f hardPointWS = new Vector3f();
    public final Vector3f wheelDirectionWS = new Vector3f();
    public final Vector3f wheelAxleWS = new Vector3f();
    public boolean isInContact;
    public Object groundObject;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.WheelInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */