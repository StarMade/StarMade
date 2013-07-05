/*     */ package com.bulletphysics.dynamics;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.BulletGlobals;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObjectType;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.linearmath.MotionState;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class RigidBody extends CollisionObject
/*     */ {
/*     */   private static final float MAX_ANGVEL = 1.570796F;
/*  74 */   private final Matrix3f invInertiaTensorWorld = new Matrix3f();
/*  75 */   private final Vector3f linearVelocity = new Vector3f();
/*  76 */   private final Vector3f angularVelocity = new Vector3f();
/*     */   private float inverseMass;
/*     */   private float angularFactor;
/*  80 */   private final Vector3f gravity = new Vector3f();
/*  81 */   private final Vector3f invInertiaLocal = new Vector3f();
/*  82 */   private final Vector3f totalForce = new Vector3f();
/*  83 */   private final Vector3f totalTorque = new Vector3f();
/*     */   private float linearDamping;
/*     */   private float angularDamping;
/*     */   private boolean additionalDamping;
/*     */   private float additionalDampingFactor;
/*     */   private float additionalLinearDampingThresholdSqr;
/*     */   private float additionalAngularDampingThresholdSqr;
/*     */   private float additionalAngularDampingFactor;
/*     */   private float linearSleepingThreshold;
/*     */   private float angularSleepingThreshold;
/*     */   private MotionState optionalMotionState;
/* 101 */   private final ObjectArrayList<TypedConstraint> constraintRefs = new ObjectArrayList();
/*     */   public int contactSolverType;
/*     */   public int frictionSolverType;
/* 107 */   private static int uniqueId = 0;
/*     */   public int debugBodyId;
/*     */ 
/*     */   public RigidBody(RigidBodyConstructionInfo constructionInfo)
/*     */   {
/* 111 */     setupRigidBody(constructionInfo);
/*     */   }
/*     */ 
/*     */   public RigidBody(float mass, MotionState motionState, CollisionShape collisionShape) {
/* 115 */     this(mass, motionState, collisionShape, new Vector3f(0.0F, 0.0F, 0.0F));
/*     */   }
/*     */ 
/*     */   public RigidBody(float mass, MotionState motionState, CollisionShape collisionShape, Vector3f localInertia) {
/* 119 */     RigidBodyConstructionInfo cinfo = new RigidBodyConstructionInfo(mass, motionState, collisionShape, localInertia);
/* 120 */     setupRigidBody(cinfo);
/*     */   }
/*     */ 
/*     */   private void setupRigidBody(RigidBodyConstructionInfo constructionInfo) {
/* 124 */     this.internalType = CollisionObjectType.RIGID_BODY;
/*     */ 
/* 126 */     this.linearVelocity.set(0.0F, 0.0F, 0.0F);
/* 127 */     this.angularVelocity.set(0.0F, 0.0F, 0.0F);
/* 128 */     this.angularFactor = 1.0F;
/* 129 */     this.gravity.set(0.0F, 0.0F, 0.0F);
/* 130 */     this.totalForce.set(0.0F, 0.0F, 0.0F);
/* 131 */     this.totalTorque.set(0.0F, 0.0F, 0.0F);
/* 132 */     this.linearDamping = 0.0F;
/* 133 */     this.angularDamping = 0.5F;
/* 134 */     this.linearSleepingThreshold = constructionInfo.linearSleepingThreshold;
/* 135 */     this.angularSleepingThreshold = constructionInfo.angularSleepingThreshold;
/* 136 */     this.optionalMotionState = constructionInfo.motionState;
/* 137 */     this.contactSolverType = 0;
/* 138 */     this.frictionSolverType = 0;
/* 139 */     this.additionalDamping = constructionInfo.additionalDamping;
/* 140 */     this.additionalDampingFactor = constructionInfo.additionalDampingFactor;
/* 141 */     this.additionalLinearDampingThresholdSqr = constructionInfo.additionalLinearDampingThresholdSqr;
/* 142 */     this.additionalAngularDampingThresholdSqr = constructionInfo.additionalAngularDampingThresholdSqr;
/* 143 */     this.additionalAngularDampingFactor = constructionInfo.additionalAngularDampingFactor;
/*     */ 
/* 145 */     if (this.optionalMotionState != null)
/*     */     {
/* 147 */       this.optionalMotionState.getWorldTransform(this.worldTransform);
/*     */     }
/*     */     else {
/* 150 */       this.worldTransform.set(constructionInfo.startWorldTransform);
/*     */     }
/*     */ 
/* 153 */     this.interpolationWorldTransform.set(this.worldTransform);
/* 154 */     this.interpolationLinearVelocity.set(0.0F, 0.0F, 0.0F);
/* 155 */     this.interpolationAngularVelocity.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 158 */     this.friction = constructionInfo.friction;
/* 159 */     this.restitution = constructionInfo.restitution;
/*     */ 
/* 161 */     setCollisionShape(constructionInfo.collisionShape);
/* 162 */     this.debugBodyId = (uniqueId++);
/*     */ 
/* 164 */     setMassProps(constructionInfo.mass, constructionInfo.localInertia);
/* 165 */     setDamping(constructionInfo.linearDamping, constructionInfo.angularDamping);
/* 166 */     updateInertiaTensor();
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 172 */     assert (this.constraintRefs.size() == 0);
/*     */   }
/*     */ 
/*     */   public void proceedToTransform(Transform newTrans) {
/* 176 */     setCenterOfMassTransform(newTrans);
/*     */   }
/*     */ 
/*     */   public static RigidBody upcast(CollisionObject colObj)
/*     */   {
/* 184 */     if (colObj.getInternalType() == CollisionObjectType.RIGID_BODY) {
/* 185 */       return (RigidBody)colObj;
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */ 
/*     */   public void predictIntegratedTransform(float timeStep, Transform predictedTransform)
/*     */   {
/* 194 */     TransformUtil.integrateTransform(this.worldTransform, this.linearVelocity, this.angularVelocity, timeStep, predictedTransform);
/*     */   }
/*     */ 
/*     */   public void saveKinematicState(float timeStep)
/*     */   {
/* 199 */     if (timeStep != 0.0F)
/*     */     {
/* 201 */       if (getMotionState() != null) {
/* 202 */         getMotionState().getWorldTransform(this.worldTransform);
/*     */       }
/*     */ 
/* 206 */       TransformUtil.calculateVelocity(this.interpolationWorldTransform, this.worldTransform, timeStep, this.linearVelocity, this.angularVelocity);
/* 207 */       this.interpolationLinearVelocity.set(this.linearVelocity);
/* 208 */       this.interpolationAngularVelocity.set(this.angularVelocity);
/* 209 */       this.interpolationWorldTransform.set(this.worldTransform);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void applyGravity()
/*     */   {
/* 215 */     if (isStaticOrKinematicObject()) {
/* 216 */       return;
/*     */     }
/* 218 */     applyCentralForce(this.gravity);
/*     */   }
/*     */ 
/*     */   public void setGravity(Vector3f acceleration) {
/* 222 */     if (this.inverseMass != 0.0F)
/* 223 */       this.gravity.scale(1.0F / this.inverseMass, acceleration);
/*     */   }
/*     */ 
/*     */   public Vector3f getGravity(Vector3f out)
/*     */   {
/* 228 */     out.set(this.gravity);
/* 229 */     return out;
/*     */   }
/*     */ 
/*     */   public void setDamping(float lin_damping, float ang_damping) {
/* 233 */     this.linearDamping = MiscUtil.GEN_clamped(lin_damping, 0.0F, 1.0F);
/* 234 */     this.angularDamping = MiscUtil.GEN_clamped(ang_damping, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   public float getLinearDamping() {
/* 238 */     return this.linearDamping;
/*     */   }
/*     */ 
/*     */   public float getAngularDamping() {
/* 242 */     return this.angularDamping;
/*     */   }
/*     */ 
/*     */   public float getLinearSleepingThreshold() {
/* 246 */     return this.linearSleepingThreshold;
/*     */   }
/*     */ 
/*     */   public float getAngularSleepingThreshold() {
/* 250 */     return this.angularSleepingThreshold;
/*     */   }
/*     */ 
/*     */   public void applyDamping(float arg1)
/*     */   {
/* 265 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.linearVelocity.scale((float)Math.pow(1.0F - this.linearDamping, timeStep));
/* 266 */       this.angularVelocity.scale((float)Math.pow(1.0F - this.angularDamping, timeStep));
/*     */ 
/* 269 */       if (this.additionalDamping)
/*     */       {
/* 272 */         if ((this.angularVelocity.lengthSquared() < this.additionalAngularDampingThresholdSqr) && (this.linearVelocity.lengthSquared() < this.additionalLinearDampingThresholdSqr))
/*     */         {
/* 274 */           this.angularVelocity.scale(this.additionalDampingFactor);
/* 275 */           this.linearVelocity.scale(this.additionalDampingFactor);
/*     */         }
/*     */ 
/* 278 */         float speed = this.linearVelocity.length();
/* 279 */         if (speed < this.linearDamping) {
/* 280 */           float dampVel = 0.005F;
/* 281 */           if (speed > dampVel) {
/* 282 */             Vector3f dir = localStack.get$javax$vecmath$Vector3f(this.linearVelocity);
/* 283 */             dir.normalize();
/* 284 */             dir.scale(dampVel);
/* 285 */             this.linearVelocity.sub(dir);
/*     */           }
/*     */           else {
/* 288 */             this.linearVelocity.set(0.0F, 0.0F, 0.0F);
/*     */           }
/*     */         }
/*     */ 
/* 292 */         float angSpeed = this.angularVelocity.length();
/* 293 */         if (angSpeed < this.angularDamping) {
/* 294 */           float angDampVel = 0.005F;
/* 295 */           if (angSpeed > angDampVel) {
/* 296 */             Vector3f dir = localStack.get$javax$vecmath$Vector3f(this.angularVelocity);
/* 297 */             dir.normalize();
/* 298 */             dir.scale(angDampVel);
/* 299 */             this.angularVelocity.sub(dir);
/*     */           }
/*     */           else {
/* 302 */             this.angularVelocity.set(0.0F, 0.0F, 0.0F);
/*     */           }
/*     */         }
/*     */       }
/* 306 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void setMassProps(float mass, Vector3f inertia) {
/* 309 */     if (mass == 0.0F) {
/* 310 */       this.collisionFlags |= 1;
/* 311 */       this.inverseMass = 0.0F;
/*     */     }
/*     */     else {
/* 314 */       this.collisionFlags &= -2;
/* 315 */       this.inverseMass = (1.0F / mass);
/*     */     }
/*     */ 
/* 318 */     this.invInertiaLocal.set(inertia.x != 0.0F ? 1.0F / inertia.x : 0.0F, inertia.y != 0.0F ? 1.0F / inertia.y : 0.0F, inertia.z != 0.0F ? 1.0F / inertia.z : 0.0F);
/*     */   }
/*     */ 
/*     */   public float getInvMass()
/*     */   {
/* 324 */     return this.inverseMass;
/*     */   }
/*     */ 
/*     */   public Matrix3f getInvInertiaTensorWorld(Matrix3f out) {
/* 328 */     out.set(this.invInertiaTensorWorld);
/* 329 */     return out;
/*     */   }
/*     */ 
/*     */   public void integrateVelocities(float arg1) {
/* 333 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (isStaticOrKinematicObject()) {
/* 334 */         return;
/*     */       }
/*     */ 
/* 337 */       this.linearVelocity.scaleAdd(this.inverseMass * step, this.totalForce, this.linearVelocity);
/* 338 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f(this.totalTorque);
/* 339 */       this.invInertiaTensorWorld.transform(tmp);
/* 340 */       this.angularVelocity.scaleAdd(step, tmp, this.angularVelocity);
/*     */ 
/* 343 */       float angvel = this.angularVelocity.length();
/* 344 */       if (angvel * step > 1.570796F)
/* 345 */         this.angularVelocity.scale(1.570796F / step / angvel);
/*     */       return; } finally {
/* 347 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void setCenterOfMassTransform(Transform xform) {
/* 350 */     if (isStaticOrKinematicObject()) {
/* 351 */       this.interpolationWorldTransform.set(this.worldTransform);
/*     */     }
/*     */     else {
/* 354 */       this.interpolationWorldTransform.set(xform);
/*     */     }
/* 356 */     getLinearVelocity(this.interpolationLinearVelocity);
/* 357 */     getAngularVelocity(this.interpolationAngularVelocity);
/* 358 */     this.worldTransform.set(xform);
/* 359 */     updateInertiaTensor();
/*     */   }
/*     */ 
/*     */   public void applyCentralForce(Vector3f force) {
/* 363 */     this.totalForce.add(force);
/*     */   }
/*     */ 
/*     */   public Vector3f getInvInertiaDiagLocal(Vector3f out) {
/* 367 */     out.set(this.invInertiaLocal);
/* 368 */     return out;
/*     */   }
/*     */ 
/*     */   public void setInvInertiaDiagLocal(Vector3f diagInvInertia) {
/* 372 */     this.invInertiaLocal.set(diagInvInertia);
/*     */   }
/*     */ 
/*     */   public void setSleepingThresholds(float linear, float angular) {
/* 376 */     this.linearSleepingThreshold = linear;
/* 377 */     this.angularSleepingThreshold = angular;
/*     */   }
/*     */ 
/*     */   public void applyTorque(Vector3f torque) {
/* 381 */     this.totalTorque.add(torque);
/*     */   }
/*     */ 
/*     */   public void applyForce(Vector3f arg1, Vector3f arg2) {
/* 385 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); applyCentralForce(force);
/*     */ 
/* 387 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 388 */       tmp.cross(rel_pos, force);
/* 389 */       tmp.scale(this.angularFactor);
/* 390 */       applyTorque(tmp);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void applyCentralImpulse(Vector3f impulse) {
/* 394 */     this.linearVelocity.scaleAdd(this.inverseMass, impulse, this.linearVelocity);
/*     */   }
/*     */ 
/*     */   public void applyTorqueImpulse(Vector3f arg1)
/*     */   {
/* 399 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f(torque);
/* 400 */       this.invInertiaTensorWorld.transform(tmp);
/* 401 */       this.angularVelocity.add(tmp);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void applyImpulse(Vector3f arg1, Vector3f arg2) {
/* 406 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (this.inverseMass != 0.0F) {
/* 407 */         applyCentralImpulse(impulse);
/* 408 */         if (this.angularFactor != 0.0F) {
/* 409 */           Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 410 */           tmp.cross(rel_pos, impulse);
/* 411 */           tmp.scale(this.angularFactor);
/* 412 */           applyTorqueImpulse(tmp);
/*     */         }
/*     */       }
/* 415 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void internalApplyImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
/*     */   {
/* 421 */     if (this.inverseMass != 0.0F) {
/* 422 */       this.linearVelocity.scaleAdd(impulseMagnitude, linearComponent, this.linearVelocity);
/* 423 */       if (this.angularFactor != 0.0F)
/* 424 */         this.angularVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.angularVelocity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearForces()
/*     */   {
/* 430 */     this.totalForce.set(0.0F, 0.0F, 0.0F);
/* 431 */     this.totalTorque.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public void updateInertiaTensor() {
/* 435 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Matrix3f(); Matrix3f mat1 = localStack.get$javax$vecmath$Matrix3f();
/* 436 */       MatrixUtil.scale(mat1, this.worldTransform.basis, this.invInertiaLocal);
/*     */ 
/* 438 */       Matrix3f mat2 = localStack.get$javax$vecmath$Matrix3f(this.worldTransform.basis);
/* 439 */       mat2.transpose();
/*     */ 
/* 441 */       this.invInertiaTensorWorld.mul(mat1, mat2);
/*     */       return; } finally { localStack.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */   public Vector3f getCenterOfMassPosition(Vector3f out) {
/* 445 */     out.set(this.worldTransform.origin);
/* 446 */     return out;
/*     */   }
/*     */ 
/*     */   public Quat4f getOrientation(Quat4f out) {
/* 450 */     MatrixUtil.getRotation(this.worldTransform.basis, out);
/* 451 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getCenterOfMassTransform(Transform out) {
/* 455 */     out.set(this.worldTransform);
/* 456 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getLinearVelocity(Vector3f out) {
/* 460 */     out.set(this.linearVelocity);
/* 461 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getAngularVelocity(Vector3f out) {
/* 465 */     out.set(this.angularVelocity);
/* 466 */     return out;
/*     */   }
/*     */ 
/*     */   public void setLinearVelocity(Vector3f lin_vel) {
/* 470 */     assert (this.collisionFlags != 1);
/* 471 */     this.linearVelocity.set(lin_vel);
/*     */   }
/*     */ 
/*     */   public void setAngularVelocity(Vector3f ang_vel) {
/* 475 */     assert (this.collisionFlags != 1);
/* 476 */     this.angularVelocity.set(ang_vel);
/*     */   }
/*     */ 
/*     */   public Vector3f getVelocityInLocalPoint(Vector3f rel_pos, Vector3f out)
/*     */   {
/* 481 */     Vector3f vec = out;
/* 482 */     vec.cross(this.angularVelocity, rel_pos);
/* 483 */     vec.add(this.linearVelocity);
/* 484 */     return out;
/*     */   }
/*     */ 
/*     */   public void translate(Vector3f v)
/*     */   {
/* 491 */     this.worldTransform.origin.add(v);
/*     */   }
/*     */ 
/*     */   public void getAabb(Vector3f aabbMin, Vector3f aabbMax) {
/* 495 */     getCollisionShape().getAabb(this.worldTransform, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   public float computeImpulseDenominator(Vector3f arg1, Vector3f arg2) {
/* 499 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f r0 = localStack.get$javax$vecmath$Vector3f();
/* 500 */       r0.sub(pos, getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/* 502 */       Vector3f c0 = localStack.get$javax$vecmath$Vector3f();
/* 503 */       c0.cross(r0, normal);
/*     */ 
/* 505 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 506 */       MatrixUtil.transposeTransform(tmp, c0, getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()));
/*     */ 
/* 508 */       Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/* 509 */       vec.cross(tmp, r0);
/*     */ 
/* 511 */       return this.inverseMass + normal.dot(vec);
/*     */     }
/*     */     finally
/*     */     {
/* 511 */       .Stack tmp109_107 = localStack; tmp109_107.pop$javax$vecmath$Vector3f(); tmp109_107.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float computeAngularImpulseDenominator(Vector3f arg1) {
/* 515 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp5_4 = localStack; tmp5_4.push$javax$vecmath$Vector3f(); tmp5_4.push$javax$vecmath$Matrix3f(); Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/* 516 */       MatrixUtil.transposeTransform(vec, axis, getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()));
/* 517 */       return axis.dot(vec);
/*     */     }
/*     */     finally
/*     */     {
/* 517 */       .Stack tmp45_44 = localStack; tmp45_44.pop$javax$vecmath$Vector3f(); tmp45_44.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void updateDeactivation(float arg1) {
/* 521 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if ((getActivationState() == 2) || (getActivationState() == 4)) {
/* 522 */         return;
/*     */       }
/*     */ 
/* 525 */       if ((getLinearVelocity(localStack.get$javax$vecmath$Vector3f()).lengthSquared() < this.linearSleepingThreshold * this.linearSleepingThreshold) && (getAngularVelocity(localStack.get$javax$vecmath$Vector3f()).lengthSquared() < this.angularSleepingThreshold * this.angularSleepingThreshold))
/*     */       {
/* 527 */         this.deactivationTime += timeStep;
/*     */       }
/*     */       else {
/* 530 */         this.deactivationTime = 0.0F;
/* 531 */         setActivationState(0);
/*     */       }return; } finally {
/* 533 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public boolean wantsSleeping() {
/* 536 */     if (getActivationState() == 4) {
/* 537 */       return false;
/*     */     }
/*     */ 
/* 541 */     if ((BulletGlobals.isDeactivationDisabled()) || (BulletGlobals.getDeactivationTime() == 0.0F)) {
/* 542 */       return false;
/*     */     }
/*     */ 
/* 545 */     if ((getActivationState() == 2) || (getActivationState() == 3)) {
/* 546 */       return true;
/*     */     }
/*     */ 
/* 549 */     if (this.deactivationTime > BulletGlobals.getDeactivationTime()) {
/* 550 */       return true;
/*     */     }
/* 552 */     return false;
/*     */   }
/*     */ 
/*     */   public BroadphaseProxy getBroadphaseProxy() {
/* 556 */     return this.broadphaseHandle;
/*     */   }
/*     */ 
/*     */   public void setNewBroadphaseProxy(BroadphaseProxy broadphaseProxy) {
/* 560 */     this.broadphaseHandle = broadphaseProxy;
/*     */   }
/*     */ 
/*     */   public MotionState getMotionState() {
/* 564 */     return this.optionalMotionState;
/*     */   }
/*     */ 
/*     */   public void setMotionState(MotionState motionState) {
/* 568 */     this.optionalMotionState = motionState;
/* 569 */     if (this.optionalMotionState != null)
/* 570 */       motionState.getWorldTransform(this.worldTransform);
/*     */   }
/*     */ 
/*     */   public void setAngularFactor(float angFac)
/*     */   {
/* 575 */     this.angularFactor = angFac;
/*     */   }
/*     */ 
/*     */   public float getAngularFactor() {
/* 579 */     return this.angularFactor;
/*     */   }
/*     */ 
/*     */   public boolean isInWorld()
/*     */   {
/* 586 */     return getBroadphaseProxy() != null;
/*     */   }
/*     */ 
/*     */   public boolean checkCollideWithOverride(CollisionObject co)
/*     */   {
/* 592 */     RigidBody otherRb = upcast(co);
/* 593 */     if (otherRb == null) {
/* 594 */       return true;
/*     */     }
/*     */ 
/* 597 */     for (int i = 0; i < this.constraintRefs.size(); i++) {
/* 598 */       TypedConstraint c = (TypedConstraint)this.constraintRefs.getQuick(i);
/* 599 */       if ((c.getRigidBodyA() == otherRb) || (c.getRigidBodyB() == otherRb)) {
/* 600 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 604 */     return true;
/*     */   }
/*     */ 
/*     */   public void addConstraintRef(TypedConstraint c) {
/* 608 */     int index = this.constraintRefs.indexOf(c);
/* 609 */     if (index == -1) {
/* 610 */       this.constraintRefs.add(c);
/*     */     }
/*     */ 
/* 613 */     this.checkCollideWith = true;
/*     */   }
/*     */ 
/*     */   public void removeConstraintRef(TypedConstraint c) {
/* 617 */     this.constraintRefs.remove(c);
/* 618 */     this.checkCollideWith = (this.constraintRefs.size() > 0);
/*     */   }
/*     */ 
/*     */   public TypedConstraint getConstraintRef(int index) {
/* 622 */     return (TypedConstraint)this.constraintRefs.getQuick(index);
/*     */   }
/*     */ 
/*     */   public int getNumConstraintRefs() {
/* 626 */     return this.constraintRefs.size();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.RigidBody
 * JD-Core Version:    0.6.2
 */