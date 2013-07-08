/*   1:    */package com.bulletphysics.dynamics.vehicle;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.dynamics.RigidBody;
/*   5:    */import com.bulletphysics.linearmath.Transform;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/*  38:    */public class WheelInfo
/*  39:    */{
/*  40: 40 */  public final RaycastInfo raycastInfo = new RaycastInfo();
/*  41:    */  
/*  42: 42 */  public final Transform worldTransform = new Transform();
/*  43:    */  
/*  44: 44 */  public final Vector3f chassisConnectionPointCS = new Vector3f();
/*  45: 45 */  public final Vector3f wheelDirectionCS = new Vector3f();
/*  46: 46 */  public final Vector3f wheelAxleCS = new Vector3f();
/*  47:    */  
/*  48:    */  public float suspensionRestLength1;
/*  49:    */  
/*  50:    */  public float maxSuspensionTravelCm;
/*  51:    */  
/*  52:    */  public float wheelsRadius;
/*  53:    */  
/*  54:    */  public float suspensionStiffness;
/*  55:    */  
/*  56:    */  public float wheelsDampingCompression;
/*  57:    */  public float wheelsDampingRelaxation;
/*  58:    */  public float frictionSlip;
/*  59:    */  public float steering;
/*  60:    */  public float rotation;
/*  61:    */  public float deltaRotation;
/*  62:    */  public float rollInfluence;
/*  63:    */  public float engineForce;
/*  64:    */  public float brake;
/*  65:    */  public boolean bIsFrontWheel;
/*  66:    */  public Object clientInfo;
/*  67:    */  public float clippedInvContactDotSuspension;
/*  68:    */  public float suspensionRelativeVelocity;
/*  69:    */  public float wheelsSuspensionForce;
/*  70:    */  public float skidInfo;
/*  71:    */  
/*  72:    */  public WheelInfo(WheelInfoConstructionInfo ci)
/*  73:    */  {
/*  74: 74 */    this.suspensionRestLength1 = ci.suspensionRestLength;
/*  75: 75 */    this.maxSuspensionTravelCm = ci.maxSuspensionTravelCm;
/*  76:    */    
/*  77: 77 */    this.wheelsRadius = ci.wheelRadius;
/*  78: 78 */    this.suspensionStiffness = ci.suspensionStiffness;
/*  79: 79 */    this.wheelsDampingCompression = ci.wheelsDampingCompression;
/*  80: 80 */    this.wheelsDampingRelaxation = ci.wheelsDampingRelaxation;
/*  81: 81 */    this.chassisConnectionPointCS.set(ci.chassisConnectionCS);
/*  82: 82 */    this.wheelDirectionCS.set(ci.wheelDirectionCS);
/*  83: 83 */    this.wheelAxleCS.set(ci.wheelAxleCS);
/*  84: 84 */    this.frictionSlip = ci.frictionSlip;
/*  85: 85 */    this.steering = 0.0F;
/*  86: 86 */    this.engineForce = 0.0F;
/*  87: 87 */    this.rotation = 0.0F;
/*  88: 88 */    this.deltaRotation = 0.0F;
/*  89: 89 */    this.brake = 0.0F;
/*  90: 90 */    this.rollInfluence = 0.1F;
/*  91: 91 */    this.bIsFrontWheel = ci.bIsFrontWheel;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public float getSuspensionRestLength() {
/*  95: 95 */    return this.suspensionRestLength1;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public void updateWheel(RigidBody arg1, RaycastInfo arg2) {
/*  99: 99 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (raycastInfo.isInContact) {
/* 100:100 */        float project = raycastInfo.contactNormalWS.dot(raycastInfo.wheelDirectionWS);
/* 101:101 */        Vector3f chassis_velocity_at_contactPoint = localStack.get$javax$vecmath$Vector3f();
/* 102:102 */        Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 103:103 */        relpos.sub(raycastInfo.contactPointWS, chassis.getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
/* 104:104 */        chassis.getVelocityInLocalPoint(relpos, chassis_velocity_at_contactPoint);
/* 105:105 */        float projVel = raycastInfo.contactNormalWS.dot(chassis_velocity_at_contactPoint);
/* 106:106 */        if (project >= -0.1F) {
/* 107:107 */          this.suspensionRelativeVelocity = 0.0F;
/* 108:108 */          this.clippedInvContactDotSuspension = 10.0F;
/* 109:    */        }
/* 110:    */        else {
/* 111:111 */          float inv = -1.0F / project;
/* 112:112 */          this.suspensionRelativeVelocity = (projVel * inv);
/* 113:113 */          this.clippedInvContactDotSuspension = inv;
/* 114:    */        }
/* 115:    */      }
/* 116:    */      else
/* 117:    */      {
/* 118:118 */        raycastInfo.suspensionLength = getSuspensionRestLength();
/* 119:119 */        this.suspensionRelativeVelocity = 0.0F;
/* 120:120 */        raycastInfo.contactNormalWS.negate(raycastInfo.wheelDirectionWS);
/* 121:121 */        this.clippedInvContactDotSuspension = 1.0F;
/* 122:    */      }
/* 123:123 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 127:    */  public static class RaycastInfo
/* 128:    */  {
/* 129:129 */    public final Vector3f contactNormalWS = new Vector3f();
/* 130:130 */    public final Vector3f contactPointWS = new Vector3f();
/* 131:    */    public float suspensionLength;
/* 132:132 */    public final Vector3f hardPointWS = new Vector3f();
/* 133:133 */    public final Vector3f wheelDirectionWS = new Vector3f();
/* 134:134 */    public final Vector3f wheelAxleWS = new Vector3f();
/* 135:    */    public boolean isInContact;
/* 136:    */    public Object groundObject;
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.WheelInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */