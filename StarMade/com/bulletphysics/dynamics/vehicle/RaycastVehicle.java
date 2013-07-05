/*     */ package com.bulletphysics.dynamics.vehicle;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ContactConstraint;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraintType;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.linearmath.MotionState;
/*     */ import com.bulletphysics.linearmath.QuaternionUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ArrayPool;
/*     */ import com.bulletphysics.util.FloatArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class RaycastVehicle extends TypedConstraint
/*     */ {
/*  49 */   private final ArrayPool<float[]> floatArrays = ArrayPool.get(Float.TYPE);
/*     */ 
/*  51 */   private static RigidBody s_fixedObject = new RigidBody(0.0F, null, null);
/*     */   private static final float sideFrictionStiffness2 = 1.0F;
/*  54 */   protected ObjectArrayList<Vector3f> forwardWS = new ObjectArrayList();
/*  55 */   protected ObjectArrayList<Vector3f> axle = new ObjectArrayList();
/*  56 */   protected FloatArrayList forwardImpulse = new FloatArrayList();
/*  57 */   protected FloatArrayList sideImpulse = new FloatArrayList();
/*     */   private float tau;
/*     */   private float damping;
/*     */   private VehicleRaycaster vehicleRaycaster;
/*  62 */   private float pitchControl = 0.0F;
/*     */   private float steeringValue;
/*     */   private float currentVehicleSpeedKmHour;
/*     */   private RigidBody chassisBody;
/*  68 */   private int indexRightAxis = 0;
/*  69 */   private int indexUpAxis = 2;
/*  70 */   private int indexForwardAxis = 1;
/*     */ 
/*  72 */   public ObjectArrayList<WheelInfo> wheelInfo = new ObjectArrayList();
/*     */ 
/*     */   public RaycastVehicle(VehicleTuning tuning, RigidBody chassis, VehicleRaycaster raycaster)
/*     */   {
/*  76 */     super(TypedConstraintType.VEHICLE_CONSTRAINT_TYPE);
/*  77 */     this.vehicleRaycaster = raycaster;
/*  78 */     this.chassisBody = chassis;
/*  79 */     defaultInit(tuning);
/*     */   }
/*     */ 
/*     */   private void defaultInit(VehicleTuning tuning) {
/*  83 */     this.currentVehicleSpeedKmHour = 0.0F;
/*  84 */     this.steeringValue = 0.0F;
/*     */   }
/*     */ 
/*     */   public WheelInfo addWheel(Vector3f connectionPointCS, Vector3f wheelDirectionCS0, Vector3f wheelAxleCS, float suspensionRestLength, float wheelRadius, VehicleTuning tuning, boolean isFrontWheel)
/*     */   {
/*  91 */     WheelInfoConstructionInfo ci = new WheelInfoConstructionInfo();
/*     */ 
/*  93 */     ci.chassisConnectionCS.set(connectionPointCS);
/*  94 */     ci.wheelDirectionCS.set(wheelDirectionCS0);
/*  95 */     ci.wheelAxleCS.set(wheelAxleCS);
/*  96 */     ci.suspensionRestLength = suspensionRestLength;
/*  97 */     ci.wheelRadius = wheelRadius;
/*  98 */     ci.suspensionStiffness = tuning.suspensionStiffness;
/*  99 */     ci.wheelsDampingCompression = tuning.suspensionCompression;
/* 100 */     ci.wheelsDampingRelaxation = tuning.suspensionDamping;
/* 101 */     ci.frictionSlip = tuning.frictionSlip;
/* 102 */     ci.bIsFrontWheel = isFrontWheel;
/* 103 */     ci.maxSuspensionTravelCm = tuning.maxSuspensionTravelCm;
/*     */ 
/* 105 */     this.wheelInfo.add(new WheelInfo(ci));
/*     */ 
/* 107 */     WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(getNumWheels() - 1);
/*     */ 
/* 109 */     updateWheelTransformsWS(wheel, false);
/* 110 */     updateWheelTransform(getNumWheels() - 1, false);
/* 111 */     return wheel;
/*     */   }
/*     */ 
/*     */   public Transform getWheelTransformWS(int wheelIndex, Transform out) {
/* 115 */     assert (wheelIndex < getNumWheels());
/* 116 */     WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(wheelIndex);
/* 117 */     out.set(wheel.worldTransform);
/* 118 */     return out;
/*     */   }
/*     */ 
/*     */   public void updateWheelTransform(int wheelIndex) {
/* 122 */     updateWheelTransform(wheelIndex, true);
/*     */   }
/*     */ 
/*     */   public void updateWheelTransform(int arg1, boolean arg2) {
/* 126 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Quat4f(); tmp11_7.push$javax$vecmath$Matrix3f(); WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(wheelIndex);
/* 127 */       updateWheelTransformsWS(wheel, interpolatedTransform);
/* 128 */       Vector3f up = localStack.get$javax$vecmath$Vector3f();
/* 129 */       up.negate(wheel.raycastInfo.wheelDirectionWS);
/* 130 */       Vector3f right = wheel.raycastInfo.wheelAxleWS;
/* 131 */       Vector3f fwd = localStack.get$javax$vecmath$Vector3f();
/* 132 */       fwd.cross(up, right);
/* 133 */       fwd.normalize();
/*     */ 
/* 138 */       float steering = wheel.steering;
/*     */ 
/* 140 */       Quat4f steeringOrn = localStack.get$javax$vecmath$Quat4f();
/* 141 */       QuaternionUtil.setRotation(steeringOrn, up, steering);
/* 142 */       Matrix3f steeringMat = localStack.get$javax$vecmath$Matrix3f();
/* 143 */       MatrixUtil.setRotation(steeringMat, steeringOrn);
/*     */ 
/* 145 */       Quat4f rotatingOrn = localStack.get$javax$vecmath$Quat4f();
/* 146 */       QuaternionUtil.setRotation(rotatingOrn, right, -wheel.rotation);
/* 147 */       Matrix3f rotatingMat = localStack.get$javax$vecmath$Matrix3f();
/* 148 */       MatrixUtil.setRotation(rotatingMat, rotatingOrn);
/*     */ 
/* 150 */       Matrix3f basis2 = localStack.get$javax$vecmath$Matrix3f();
/* 151 */       basis2.setRow(0, right.x, fwd.x, up.x);
/* 152 */       basis2.setRow(1, right.y, fwd.y, up.y);
/* 153 */       basis2.setRow(2, right.z, fwd.z, up.z);
/*     */ 
/* 155 */       Matrix3f wheelBasis = wheel.worldTransform.basis;
/* 156 */       wheelBasis.mul(steeringMat, rotatingMat);
/* 157 */       wheelBasis.mul(basis2);
/*     */ 
/* 159 */       wheel.worldTransform.origin.scaleAdd(wheel.raycastInfo.suspensionLength, wheel.raycastInfo.wheelDirectionWS, wheel.raycastInfo.hardPointWS);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 160 */       .Stack tmp296_294 = localStack; tmp296_294.pop$javax$vecmath$Vector3f();
/*     */       .Stack tmp300_296 = tmp296_294; tmp300_296.pop$javax$vecmath$Quat4f(); tmp300_296.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void resetSuspension() {
/* 164 */     for (int i = 0; i < this.wheelInfo.size(); i++) {
/* 165 */       WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(i);
/* 166 */       wheel.raycastInfo.suspensionLength = wheel.getSuspensionRestLength();
/* 167 */       wheel.suspensionRelativeVelocity = 0.0F;
/*     */ 
/* 169 */       wheel.raycastInfo.contactNormalWS.negate(wheel.raycastInfo.wheelDirectionWS);
/*     */ 
/* 171 */       wheel.clippedInvContactDotSuspension = 1.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateWheelTransformsWS(WheelInfo wheel) {
/* 176 */     updateWheelTransformsWS(wheel, true);
/*     */   }
/*     */ 
/*     */   public void updateWheelTransformsWS(WheelInfo arg1, boolean arg2) {
/* 180 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); wheel.raycastInfo.isInContact = false;
/*     */ 
/* 182 */       Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 183 */       if ((interpolatedTransform) && (getRigidBody().getMotionState() != null)) {
/* 184 */         getRigidBody().getMotionState().getWorldTransform(chassisTrans); } 
/*     */ wheel.raycastInfo.hardPointWS.set(wheel.chassisConnectionPointCS);
/* 188 */       chassisTrans.transform(wheel.raycastInfo.hardPointWS);
/*     */ 
/* 190 */       wheel.raycastInfo.wheelDirectionWS.set(wheel.wheelDirectionCS);
/* 191 */       chassisTrans.basis.transform(wheel.raycastInfo.wheelDirectionWS);
/*     */ 
/* 193 */       wheel.raycastInfo.wheelAxleWS.set(wheel.wheelAxleCS);
/* 194 */       chassisTrans.basis.transform(wheel.raycastInfo.wheelAxleWS);
/*     */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   public float rayCast(WheelInfo arg1) {
/* 198 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); updateWheelTransformsWS(wheel, false);
/*     */ 
/* 200 */       float depth = -1.0F;
/*     */ 
/* 202 */       float raylen = wheel.getSuspensionRestLength() + wheel.wheelsRadius;
/*     */ 
/* 204 */       Vector3f rayvector = localStack.get$javax$vecmath$Vector3f();
/* 205 */       rayvector.scale(raylen, wheel.raycastInfo.wheelDirectionWS);
/* 206 */       Vector3f source = wheel.raycastInfo.hardPointWS;
/* 207 */       wheel.raycastInfo.contactPointWS.add(source, rayvector);
/* 208 */       Vector3f target = wheel.raycastInfo.contactPointWS;
/*     */ 
/* 210 */       float param = 0.0F;
/*     */ 
/* 212 */       VehicleRaycasterResult rayResults = new VehicleRaycasterResult();
/*     */ 
/* 214 */       assert (this.vehicleRaycaster != null);
/*     */ 
/* 216 */       Object object = this.vehicleRaycaster.castRay(source, target, rayResults);
/*     */ 
/* 218 */       wheel.raycastInfo.groundObject = null;
/*     */ 
/* 220 */       if (object != null) {
/* 221 */         param = rayResults.distFraction;
/* 222 */         depth = raylen * rayResults.distFraction;
/* 223 */         wheel.raycastInfo.contactNormalWS.set(rayResults.hitNormalInWorld);
/* 224 */         wheel.raycastInfo.isInContact = true;
/*     */ 
/* 226 */         wheel.raycastInfo.groundObject = s_fixedObject;
/*     */ 
/* 229 */         float hitDistance = param * raylen;
/* 230 */         wheel.raycastInfo.suspensionLength = (hitDistance - wheel.wheelsRadius);
/*     */ 
/* 233 */         float minSuspensionLength = wheel.getSuspensionRestLength() - wheel.maxSuspensionTravelCm * 0.01F;
/* 234 */         float maxSuspensionLength = wheel.getSuspensionRestLength() + wheel.maxSuspensionTravelCm * 0.01F;
/* 235 */         if (wheel.raycastInfo.suspensionLength < minSuspensionLength) {
/* 236 */           wheel.raycastInfo.suspensionLength = minSuspensionLength;
/*     */         }
/* 238 */         if (wheel.raycastInfo.suspensionLength > maxSuspensionLength) {
/* 239 */           wheel.raycastInfo.suspensionLength = maxSuspensionLength;
/*     */         }
/*     */ 
/* 242 */         wheel.raycastInfo.contactPointWS.set(rayResults.hitPointInWorld);
/*     */ 
/* 244 */         float denominator = wheel.raycastInfo.contactNormalWS.dot(wheel.raycastInfo.wheelDirectionWS);
/*     */ 
/* 246 */         Vector3f chassis_velocity_at_contactPoint = localStack.get$javax$vecmath$Vector3f();
/* 247 */         Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 248 */         relpos.sub(wheel.raycastInfo.contactPointWS, getRigidBody().getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/* 250 */         getRigidBody().getVelocityInLocalPoint(relpos, chassis_velocity_at_contactPoint);
/*     */ 
/* 252 */         float projVel = wheel.raycastInfo.contactNormalWS.dot(chassis_velocity_at_contactPoint);
/*     */ 
/* 254 */         if (denominator >= -0.1F) {
/* 255 */           wheel.suspensionRelativeVelocity = 0.0F;
/* 256 */           wheel.clippedInvContactDotSuspension = 10.0F;
/*     */         }
/*     */         else {
/* 259 */           float inv = -1.0F / denominator;
/* 260 */           wheel.suspensionRelativeVelocity = (projVel * inv);
/* 261 */           wheel.clippedInvContactDotSuspension = inv;
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 267 */         wheel.raycastInfo.suspensionLength = wheel.getSuspensionRestLength();
/* 268 */         wheel.suspensionRelativeVelocity = 0.0F;
/* 269 */         wheel.raycastInfo.contactNormalWS.negate(wheel.raycastInfo.wheelDirectionWS);
/* 270 */         wheel.clippedInvContactDotSuspension = 1.0F;
/*     */       }
/*     */ 
/* 273 */       return depth; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Transform getChassisWorldTransform(Transform out)
/*     */   {
/* 286 */     return getRigidBody().getCenterOfMassTransform(out);
/*     */   }
/*     */ 
/*     */   public void updateVehicle(float arg1) {
/* 290 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); for (int i = 0; i < getNumWheels(); i++) {
/* 291 */         updateWheelTransform(i, false);
/*     */       }
/*     */ 
/* 294 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 296 */       this.currentVehicleSpeedKmHour = (3.6F * getRigidBody().getLinearVelocity(tmp).length());
/*     */ 
/* 298 */       Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 300 */       Vector3f forwardW = localStack.get$javax$vecmath$Vector3f();
/* 301 */       forwardW.set(chassisTrans.basis.getElement(0, this.indexForwardAxis), chassisTrans.basis.getElement(1, this.indexForwardAxis), chassisTrans.basis.getElement(2, this.indexForwardAxis));
/*     */ 
/* 306 */       if (forwardW.dot(getRigidBody().getLinearVelocity(tmp)) < 0.0F) {
/* 307 */         this.currentVehicleSpeedKmHour *= -1.0F;
/*     */       }
/*     */ 
/* 314 */       int i = 0;
/*     */       float depth;
/* 315 */       for (i = 0; i < this.wheelInfo.size(); i++)
/*     */       {
/* 317 */         depth = rayCast((WheelInfo)this.wheelInfo.getQuick(i));
/*     */       }
/*     */ 
/* 320 */       updateSuspension(step);
/*     */ 
/* 322 */       for (i = 0; i < this.wheelInfo.size(); i++)
/*     */       {
/* 324 */         WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(i);
/*     */ 
/* 326 */         float suspensionForce = wheel.wheelsSuspensionForce;
/*     */ 
/* 328 */         float gMaxSuspensionForce = 6000.0F;
/* 329 */         if (suspensionForce > gMaxSuspensionForce) {
/* 330 */           suspensionForce = gMaxSuspensionForce;
/*     */         }
/* 332 */         Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 333 */         impulse.scale(suspensionForce * step, wheel.raycastInfo.contactNormalWS);
/* 334 */         Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 335 */         relpos.sub(wheel.raycastInfo.contactPointWS, getRigidBody().getCenterOfMassPosition(tmp));
/*     */ 
/* 337 */         getRigidBody().applyImpulse(impulse, relpos);
/*     */       }
/*     */ 
/* 340 */       updateFriction(step);
/*     */ 
/* 342 */       for (i = 0; i < this.wheelInfo.size(); i++) {
/* 343 */         WheelInfo wheel = (WheelInfo)this.wheelInfo.getQuick(i);
/* 344 */         Vector3f relpos = localStack.get$javax$vecmath$Vector3f();
/* 345 */         relpos.sub(wheel.raycastInfo.hardPointWS, getRigidBody().getCenterOfMassPosition(tmp));
/* 346 */         Vector3f vel = getRigidBody().getVelocityInLocalPoint(relpos, localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 348 */         if (wheel.raycastInfo.isInContact) {
/* 349 */           Transform chassisWorldTransform = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 351 */           Vector3f fwd = localStack.get$javax$vecmath$Vector3f();
/* 352 */           fwd.set(chassisWorldTransform.basis.getElement(0, this.indexForwardAxis), chassisWorldTransform.basis.getElement(1, this.indexForwardAxis), chassisWorldTransform.basis.getElement(2, this.indexForwardAxis));
/*     */ 
/* 357 */           float proj = fwd.dot(wheel.raycastInfo.contactNormalWS);
/* 358 */           tmp.scale(proj, wheel.raycastInfo.contactNormalWS);
/* 359 */           fwd.sub(tmp);
/*     */ 
/* 361 */           float proj2 = fwd.dot(vel);
/*     */ 
/* 363 */           wheel.deltaRotation = (proj2 * step / wheel.wheelsRadius);
/* 364 */           wheel.rotation += wheel.deltaRotation;
/*     */         }
/*     */         else
/*     */         {
/* 368 */           wheel.rotation += wheel.deltaRotation;
/*     */         }
/*     */ 
/* 371 */         wheel.deltaRotation *= 0.99F;
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 373 */       .Stack tmp592_590 = localStack; tmp592_590.pop$com$bulletphysics$linearmath$Transform(); tmp592_590.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void setSteeringValue(float steering, int wheel) {
/* 376 */     assert ((wheel >= 0) && (wheel < getNumWheels()));
/*     */ 
/* 378 */     WheelInfo wheel_info = getWheelInfo(wheel);
/* 379 */     wheel_info.steering = steering;
/*     */   }
/*     */ 
/*     */   public float getSteeringValue(int wheel) {
/* 383 */     return getWheelInfo(wheel).steering;
/*     */   }
/*     */ 
/*     */   public void applyEngineForce(float force, int wheel) {
/* 387 */     assert ((wheel >= 0) && (wheel < getNumWheels()));
/* 388 */     WheelInfo wheel_info = getWheelInfo(wheel);
/* 389 */     wheel_info.engineForce = force;
/*     */   }
/*     */ 
/*     */   public WheelInfo getWheelInfo(int index) {
/* 393 */     assert ((index >= 0) && (index < getNumWheels()));
/*     */ 
/* 395 */     return (WheelInfo)this.wheelInfo.getQuick(index);
/*     */   }
/*     */ 
/*     */   public void setBrake(float brake, int wheelIndex) {
/* 399 */     assert ((wheelIndex >= 0) && (wheelIndex < getNumWheels()));
/* 400 */     getWheelInfo(wheelIndex).brake = brake;
/*     */   }
/*     */ 
/*     */   public void updateSuspension(float deltaTime) {
/* 404 */     float chassisMass = 1.0F / this.chassisBody.getInvMass();
/*     */ 
/* 406 */     for (int w_it = 0; w_it < getNumWheels(); w_it++) {
/* 407 */       WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(w_it);
/*     */ 
/* 409 */       if (wheel_info.raycastInfo.isInContact)
/*     */       {
/* 413 */         float susp_length = wheel_info.getSuspensionRestLength();
/* 414 */         float current_length = wheel_info.raycastInfo.suspensionLength;
/*     */ 
/* 416 */         float length_diff = susp_length - current_length;
/*     */ 
/* 418 */         float force = wheel_info.suspensionStiffness * length_diff * wheel_info.clippedInvContactDotSuspension;
/*     */ 
/* 423 */         float projected_rel_vel = wheel_info.suspensionRelativeVelocity;
/*     */         float susp_damping;
/*     */         float susp_damping;
/* 426 */         if (projected_rel_vel < 0.0F) {
/* 427 */           susp_damping = wheel_info.wheelsDampingCompression;
/*     */         }
/*     */         else {
/* 430 */           susp_damping = wheel_info.wheelsDampingRelaxation;
/*     */         }
/* 432 */         force -= susp_damping * projected_rel_vel;
/*     */ 
/* 437 */         wheel_info.wheelsSuspensionForce = (force * chassisMass);
/* 438 */         if (wheel_info.wheelsSuspensionForce < 0.0F)
/* 439 */           wheel_info.wheelsSuspensionForce = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 443 */         wheel_info.wheelsSuspensionForce = 0.0F;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private float calcRollingFriction(WheelContactPoint arg1) {
/* 449 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 451 */       float j1 = 0.0F;
/*     */ 
/* 453 */       Vector3f contactPosWorld = contactPoint.frictionPositionWorld;
/*     */ 
/* 455 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 456 */       rel_pos1.sub(contactPosWorld, contactPoint.body0.getCenterOfMassPosition(tmp));
/* 457 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 458 */       rel_pos2.sub(contactPosWorld, contactPoint.body1.getCenterOfMassPosition(tmp));
/*     */ 
/* 460 */       float maxImpulse = contactPoint.maxImpulse;
/*     */ 
/* 462 */       Vector3f vel1 = contactPoint.body0.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 463 */       Vector3f vel2 = contactPoint.body1.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 464 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 465 */       vel.sub(vel1, vel2);
/*     */ 
/* 467 */       float vrel = contactPoint.frictionDirectionWorld.dot(vel);
/*     */ 
/* 470 */       j1 = -vrel * contactPoint.jacDiagABInv;
/* 471 */       j1 = Math.min(j1, maxImpulse);
/* 472 */       return Math.max(j1, -maxImpulse);
/*     */     } finally {
/* 474 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void updateFriction(float arg1)
/*     */   {
/* 479 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Matrix3f(); int numWheel = getNumWheels();
/* 480 */       if (numWheel == 0) {
/* 481 */         return;
/*     */       }
/*     */ 
/* 484 */       MiscUtil.resize(this.forwardWS, numWheel, Vector3f.class);
/* 485 */       MiscUtil.resize(this.axle, numWheel, Vector3f.class);
/* 486 */       MiscUtil.resize(this.forwardImpulse, numWheel, 0.0F);
/* 487 */       MiscUtil.resize(this.sideImpulse, numWheel, 0.0F);
/*     */ 
/* 489 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 491 */       int numWheelsOnGround = 0;
/*     */ 
/* 494 */       for (int i = 0; i < getNumWheels(); i++) {
/* 495 */         WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(i);
/* 496 */         RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
/* 497 */         if (groundObject != null) {
/* 498 */           numWheelsOnGround++;
/*     */         }
/* 500 */         this.sideImpulse.set(i, 0.0F);
/* 501 */         this.forwardImpulse.set(i, 0.0F);
/*     */       }
/*     */ 
/* 505 */       Transform wheelTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 506 */       for (int i = 0; i < getNumWheels(); i++)
/*     */       {
/* 508 */         WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(i);
/*     */ 
/* 510 */         RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
/*     */ 
/* 512 */         if (groundObject != null) {
/* 513 */           getWheelTransformWS(i, wheelTrans);
/*     */ 
/* 515 */           Matrix3f wheelBasis0 = localStack.get$javax$vecmath$Matrix3f(wheelTrans.basis);
/* 516 */           ((Vector3f)this.axle.getQuick(i)).set(wheelBasis0.getElement(0, this.indexRightAxis), wheelBasis0.getElement(1, this.indexRightAxis), wheelBasis0.getElement(2, this.indexRightAxis));
/*     */ 
/* 521 */           Vector3f surfNormalWS = wheel_info.raycastInfo.contactNormalWS;
/* 522 */           float proj = ((Vector3f)this.axle.getQuick(i)).dot(surfNormalWS);
/* 523 */           tmp.scale(proj, surfNormalWS);
/* 524 */           ((Vector3f)this.axle.getQuick(i)).sub(tmp);
/* 525 */           ((Vector3f)this.axle.getQuick(i)).normalize();
/*     */ 
/* 527 */           ((Vector3f)this.forwardWS.getQuick(i)).cross(surfNormalWS, (Vector3f)this.axle.getQuick(i));
/* 528 */           ((Vector3f)this.forwardWS.getQuick(i)).normalize();
/*     */ 
/* 530 */           float[] floatPtr = (float[])this.floatArrays.getFixed(1);
/* 531 */           ContactConstraint.resolveSingleBilateral(this.chassisBody, wheel_info.raycastInfo.contactPointWS, groundObject, wheel_info.raycastInfo.contactPointWS, 0.0F, (Vector3f)this.axle.getQuick(i), floatPtr, timeStep);
/*     */ 
/* 534 */           this.sideImpulse.set(i, floatPtr[0]);
/* 535 */           this.floatArrays.release(floatPtr);
/*     */ 
/* 537 */           this.sideImpulse.set(i, this.sideImpulse.get(i) * 1.0F);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 542 */       float sideFactor = 1.0F;
/* 543 */       float fwdFactor = 0.5F;
/*     */ 
/* 545 */       boolean sliding = false;
/*     */ 
/* 547 */       for (int wheel = 0; wheel < getNumWheels(); wheel++) {
/* 548 */         WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(wheel);
/* 549 */         RigidBody groundObject = (RigidBody)wheel_info.raycastInfo.groundObject;
/*     */ 
/* 551 */         float rollingFriction = 0.0F;
/*     */ 
/* 553 */         if (groundObject != null) {
/* 554 */           if (wheel_info.engineForce != 0.0F) {
/* 555 */             rollingFriction = wheel_info.engineForce * timeStep;
/*     */           }
/*     */           else {
/* 558 */             float defaultRollingFrictionImpulse = 0.0F;
/* 559 */             float maxImpulse = wheel_info.brake != 0.0F ? wheel_info.brake : defaultRollingFrictionImpulse;
/* 560 */             WheelContactPoint contactPt = new WheelContactPoint(this.chassisBody, groundObject, wheel_info.raycastInfo.contactPointWS, (Vector3f)this.forwardWS.getQuick(wheel), maxImpulse);
/* 561 */             rollingFriction = calcRollingFriction(contactPt);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 567 */         this.forwardImpulse.set(wheel, 0.0F);
/* 568 */         ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo = 1.0F;
/*     */ 
/* 570 */         if (groundObject != null) {
/* 571 */           ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo = 1.0F;
/*     */ 
/* 573 */           float maximp = wheel_info.wheelsSuspensionForce * timeStep * wheel_info.frictionSlip;
/* 574 */           float maximpSide = maximp;
/*     */ 
/* 576 */           float maximpSquared = maximp * maximpSide;
/*     */ 
/* 578 */           this.forwardImpulse.set(wheel, rollingFriction);
/*     */ 
/* 580 */           float x = this.forwardImpulse.get(wheel) * fwdFactor;
/* 581 */           float y = this.sideImpulse.get(wheel) * sideFactor;
/*     */ 
/* 583 */           float impulseSquared = x * x + y * y;
/*     */ 
/* 585 */           if (impulseSquared > maximpSquared) {
/* 586 */             sliding = true;
/*     */ 
/* 588 */             float factor = maximp / (float)Math.sqrt(impulseSquared);
/*     */ 
/* 590 */             ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo *= factor;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 597 */       if (sliding) {
/* 598 */         for (int wheel = 0; wheel < getNumWheels(); wheel++) {
/* 599 */           if ((this.sideImpulse.get(wheel) != 0.0F) && 
/* 600 */             (((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo < 1.0F)) {
/* 601 */             this.forwardImpulse.set(wheel, this.forwardImpulse.get(wheel) * ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo);
/* 602 */             this.sideImpulse.set(wheel, this.sideImpulse.get(wheel) * ((WheelInfo)this.wheelInfo.getQuick(wheel)).skidInfo);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 610 */       for (int wheel = 0; wheel < getNumWheels(); wheel++) {
/* 611 */         WheelInfo wheel_info = (WheelInfo)this.wheelInfo.getQuick(wheel);
/*     */ 
/* 613 */         Vector3f rel_pos = localStack.get$javax$vecmath$Vector3f();
/* 614 */         rel_pos.sub(wheel_info.raycastInfo.contactPointWS, this.chassisBody.getCenterOfMassPosition(tmp));
/*     */ 
/* 616 */         if (this.forwardImpulse.get(wheel) != 0.0F) {
/* 617 */           tmp.scale(this.forwardImpulse.get(wheel), (Tuple3f)this.forwardWS.getQuick(wheel));
/* 618 */           this.chassisBody.applyImpulse(tmp, rel_pos);
/*     */         }
/* 620 */         if (this.sideImpulse.get(wheel) != 0.0F) {
/* 621 */           RigidBody groundObject = (RigidBody)((WheelInfo)this.wheelInfo.getQuick(wheel)).raycastInfo.groundObject;
/*     */ 
/* 623 */           Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 624 */           rel_pos2.sub(wheel_info.raycastInfo.contactPointWS, groundObject.getCenterOfMassPosition(tmp));
/*     */ 
/* 626 */           Vector3f sideImp = localStack.get$javax$vecmath$Vector3f();
/* 627 */           sideImp.scale(this.sideImpulse.get(wheel), (Tuple3f)this.axle.getQuick(wheel));
/*     */ 
/* 629 */           rel_pos.z *= wheel_info.rollInfluence;
/* 630 */           this.chassisBody.applyImpulse(sideImp, rel_pos);
/*     */ 
/* 633 */           tmp.negate(sideImp);
/* 634 */           groundObject.applyImpulse(tmp, rel_pos2);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 638 */       .Stack tmp1205_1203 = localStack; tmp1205_1203.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp1209_1205 = tmp1205_1203; tmp1209_1205.pop$javax$vecmath$Vector3f(); tmp1209_1205.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float timeStep)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getNumWheels()
/*     */   {
/* 651 */     return this.wheelInfo.size();
/*     */   }
/*     */ 
/*     */   public void setPitchControl(float pitch) {
/* 655 */     this.pitchControl = pitch;
/*     */   }
/*     */ 
/*     */   public RigidBody getRigidBody() {
/* 659 */     return this.chassisBody;
/*     */   }
/*     */ 
/*     */   public int getRightAxis() {
/* 663 */     return this.indexRightAxis;
/*     */   }
/*     */ 
/*     */   public int getUpAxis() {
/* 667 */     return this.indexUpAxis;
/*     */   }
/*     */ 
/*     */   public int getForwardAxis() {
/* 671 */     return this.indexForwardAxis;
/*     */   }
/*     */ 
/*     */   public Vector3f getForwardVector(Vector3f arg1)
/*     */   {
/* 678 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform chassisTrans = getChassisWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 680 */       out.set(chassisTrans.basis.getElement(0, this.indexForwardAxis), chassisTrans.basis.getElement(1, this.indexForwardAxis), chassisTrans.basis.getElement(2, this.indexForwardAxis));
/*     */ 
/* 685 */       return out; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float getCurrentSpeedKmHour()
/*     */   {
/* 692 */     return this.currentVehicleSpeedKmHour;
/*     */   }
/*     */ 
/*     */   public void setCoordinateSystem(int rightIndex, int upIndex, int forwardIndex) {
/* 696 */     this.indexRightAxis = rightIndex;
/* 697 */     this.indexUpAxis = upIndex;
/* 698 */     this.indexForwardAxis = forwardIndex;
/*     */   }
/*     */   private static class WheelContactPoint { public RigidBody body0;
/*     */     public RigidBody body1;
/* 706 */     public final Vector3f frictionPositionWorld = new Vector3f();
/* 707 */     public final Vector3f frictionDirectionWorld = new Vector3f();
/*     */     public float jacDiagABInv;
/*     */     public float maxImpulse;
/*     */ 
/* 712 */     public WheelContactPoint(RigidBody body0, RigidBody body1, Vector3f frictionPosWorld, Vector3f frictionDirectionWorld, float maxImpulse) { this.body0 = body0;
/* 713 */       this.body1 = body1;
/* 714 */       this.frictionPositionWorld.set(frictionPosWorld);
/* 715 */       this.frictionDirectionWorld.set(frictionDirectionWorld);
/* 716 */       this.maxImpulse = maxImpulse;
/*     */ 
/* 718 */       float denom0 = body0.computeImpulseDenominator(frictionPosWorld, frictionDirectionWorld);
/* 719 */       float denom1 = body1.computeImpulseDenominator(frictionPosWorld, frictionDirectionWorld);
/* 720 */       float relaxation = 1.0F;
/* 721 */       this.jacDiagABInv = (relaxation / (denom0 + denom1));
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.RaycastVehicle
 * JD-Core Version:    0.6.2
 */