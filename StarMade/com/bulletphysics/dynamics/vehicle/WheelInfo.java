/*     */ package com.bulletphysics.dynamics.vehicle;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class WheelInfo
/*     */ {
/*  40 */   public final RaycastInfo raycastInfo = new RaycastInfo();
/*     */ 
/*  42 */   public final Transform worldTransform = new Transform();
/*     */ 
/*  44 */   public final Vector3f chassisConnectionPointCS = new Vector3f();
/*  45 */   public final Vector3f wheelDirectionCS = new Vector3f();
/*  46 */   public final Vector3f wheelAxleCS = new Vector3f();
/*     */   public float suspensionRestLength1;
/*     */   public float maxSuspensionTravelCm;
/*     */   public float wheelsRadius;
/*     */   public float suspensionStiffness;
/*     */   public float wheelsDampingCompression;
/*     */   public float wheelsDampingRelaxation;
/*     */   public float frictionSlip;
/*     */   public float steering;
/*     */   public float rotation;
/*     */   public float deltaRotation;
/*     */   public float rollInfluence;
/*     */   public float engineForce;
/*     */   public float brake;
/*     */   public boolean bIsFrontWheel;
/*     */   public Object clientInfo;
/*     */   public float clippedInvContactDotSuspension;
/*     */   public float suspensionRelativeVelocity;
/*     */   public float wheelsSuspensionForce;
/*     */   public float skidInfo;
/*     */ 
/*     */   public WheelInfo(WheelInfoConstructionInfo ci)
/*     */   {
/*  74 */     this.suspensionRestLength1 = ci.suspensionRestLength;
/*  75 */     this.maxSuspensionTravelCm = ci.maxSuspensionTravelCm;
/*     */ 
/*  77 */     this.wheelsRadius = ci.wheelRadius;
/*  78 */     this.suspensionStiffness = ci.suspensionStiffness;
/*  79 */     this.wheelsDampingCompression = ci.wheelsDampingCompression;
/*  80 */     this.wheelsDampingRelaxation = ci.wheelsDampingRelaxation;
/*  81 */     this.chassisConnectionPointCS.set(ci.chassisConnectionCS);
/*  82 */     this.wheelDirectionCS.set(ci.wheelDirectionCS);
/*  83 */     this.wheelAxleCS.set(ci.wheelAxleCS);
/*  84 */     this.frictionSlip = ci.frictionSlip;
/*  85 */     this.steering = 0.0F;
/*  86 */     this.engineForce = 0.0F;
/*  87 */     this.rotation = 0.0F;
/*  88 */     this.deltaRotation = 0.0F;
/*  89 */     this.brake = 0.0F;
/*  90 */     this.rollInfluence = 0.1F;
/*  91 */     this.bIsFrontWheel = ci.bIsFrontWheel;
/*     */   }
/*     */ 
/*     */   public float getSuspensionRestLength() {
/*  95 */     return this.suspensionRestLength1;
/*     */   }
/*     */ 
/*     */   public void updateWheel(RigidBody arg1, RaycastInfo arg2) {
/*  99 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (raycastInfo.isInContact) {
/* 100 */         float project = raycastInfo.contactNormalWS.dot(raycastInfo.wheelDirectionWS);
/* 101 */         Vector3f chassis_velocity_at_contactPoint = localStack.get$javax$vecmath$Vector3f();
/* 102 */         Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 103 */         relpos.sub(raycastInfo.contactPointWS, chassis.getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
/* 104 */         chassis.getVelocityInLocalPoint(relpos, chassis_velocity_at_contactPoint);
/* 105 */         float projVel = raycastInfo.contactNormalWS.dot(chassis_velocity_at_contactPoint);
/* 106 */         if (project >= -0.1F) {
/* 107 */           this.suspensionRelativeVelocity = 0.0F;
/* 108 */           this.clippedInvContactDotSuspension = 10.0F;
/*     */         }
/*     */         else {
/* 111 */           float inv = -1.0F / project;
/* 112 */           this.suspensionRelativeVelocity = (projVel * inv);
/* 113 */           this.clippedInvContactDotSuspension = inv;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 118 */         raycastInfo.suspensionLength = getSuspensionRestLength();
/* 119 */         this.suspensionRelativeVelocity = 0.0F;
/* 120 */         raycastInfo.contactNormalWS.negate(raycastInfo.wheelDirectionWS);
/* 121 */         this.clippedInvContactDotSuspension = 1.0F;
/*     */       }return; } finally {
/* 123 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static class RaycastInfo
/*     */   {
/* 129 */     public final Vector3f contactNormalWS = new Vector3f();
/* 130 */     public final Vector3f contactPointWS = new Vector3f();
/*     */     public float suspensionLength;
/* 132 */     public final Vector3f hardPointWS = new Vector3f();
/* 133 */     public final Vector3f wheelDirectionWS = new Vector3f();
/* 134 */     public final Vector3f wheelAxleWS = new Vector3f();
/*     */     public boolean isInContact;
/*     */     public Object groundObject;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.WheelInfo
 * JD-Core Version:    0.6.2
 */