/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.ScalarUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class HingeConstraint extends TypedConstraint
/*     */ {
/*  47 */   private JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  48 */   private JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*     */ 
/*  50 */   private final Transform rbAFrame = new Transform();
/*  51 */   private final Transform rbBFrame = new Transform();
/*     */   private float motorTargetVelocity;
/*     */   private float maxMotorImpulse;
/*     */   private float limitSoftness;
/*     */   private float biasFactor;
/*     */   private float relaxationFactor;
/*     */   private float lowerLimit;
/*     */   private float upperLimit;
/*     */   private float kHinge;
/*     */   private float limitSign;
/*     */   private float correction;
/*     */   private float accLimitImpulse;
/*     */   private boolean angularOnly;
/*     */   private boolean enableAngularMotor;
/*     */   private boolean solveLimit;
/*     */ 
/*     */   public HingeConstraint()
/*     */   {
/*  75 */     super(TypedConstraintType.HINGE_CONSTRAINT_TYPE);
/*  76 */     this.enableAngularMotor = false;
/*     */   }
/*     */ 
/*     */   public HingeConstraint(RigidBody arg1, RigidBody arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, Vector3f arg6)
/*     */   {
/*     */   }
/*     */ 
/*     */   public HingeConstraint(RigidBody arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*     */   }
/*     */ 
/*     */   public HingeConstraint(RigidBody rbA, RigidBody rbB, Transform rbAFrame, Transform rbBFrame)
/*     */   {
/* 182 */     super(TypedConstraintType.HINGE_CONSTRAINT_TYPE, rbA, rbB);
/* 183 */     this.rbAFrame.set(rbAFrame);
/* 184 */     this.rbBFrame.set(rbBFrame);
/* 185 */     this.angularOnly = false;
/* 186 */     this.enableAngularMotor = false;
/*     */ 
/* 189 */     this.rbBFrame.basis.m02 *= -1.0F;
/* 190 */     this.rbBFrame.basis.m12 *= -1.0F;
/* 191 */     this.rbBFrame.basis.m22 *= -1.0F;
/*     */ 
/* 194 */     this.lowerLimit = 1.0E+030F;
/* 195 */     this.upperLimit = -1.0E+030F;
/* 196 */     this.biasFactor = 0.3F;
/* 197 */     this.relaxationFactor = 1.0F;
/* 198 */     this.limitSoftness = 0.9F;
/* 199 */     this.solveLimit = false;
/*     */   }
/*     */ 
/*     */   public HingeConstraint(RigidBody arg1, Transform arg2)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/* 230 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Matrix3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 231 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 232 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 233 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 234 */       Matrix3f mat1 = localStack.get$javax$vecmath$Matrix3f();
/* 235 */       Matrix3f mat2 = localStack.get$javax$vecmath$Matrix3f();
/*     */ 
/* 237 */       Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 238 */       Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 240 */       this.appliedImpulse = 0.0F;
/*     */ 
/* 242 */       if (!this.angularOnly) {
/* 243 */         Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 244 */         centerOfMassA.transform(pivotAInW);
/*     */ 
/* 246 */         Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 247 */         centerOfMassB.transform(pivotBInW);
/*     */ 
/* 249 */         Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
/* 250 */         relPos.sub(pivotBInW, pivotAInW);
/*     */ 
/* 252 */         Vector3f[] normal = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 253 */         if (relPos.lengthSquared() > 1.192093E-007F) {
/* 254 */           normal[0].set(relPos);
/* 255 */           normal[0].normalize();
/*     */         }
/*     */         else {
/* 258 */           normal[0].set(1.0F, 0.0F, 0.0F);
/*     */         }
/*     */ 
/* 261 */         TransformUtil.planeSpace1(normal[0], normal[1], normal[2]);
/*     */ 
/* 263 */         for (int i = 0; i < 3; i++) {
/* 264 */           mat1.transpose(centerOfMassA.basis);
/* 265 */           mat2.transpose(centerOfMassB.basis);
/*     */ 
/* 267 */           tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 268 */           tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 270 */           this.jac[i].init(mat1, mat2, tmp1, tmp2, normal[i], this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 287 */       Vector3f jointAxis0local = localStack.get$javax$vecmath$Vector3f();
/* 288 */       Vector3f jointAxis1local = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 290 */       this.rbAFrame.basis.getColumn(2, tmp);
/* 291 */       TransformUtil.planeSpace1(tmp, jointAxis0local, jointAxis1local);
/*     */ 
/* 296 */       Vector3f jointAxis0 = localStack.get$javax$vecmath$Vector3f(jointAxis0local);
/* 297 */       centerOfMassA.basis.transform(jointAxis0);
/*     */ 
/* 299 */       Vector3f jointAxis1 = localStack.get$javax$vecmath$Vector3f(jointAxis1local);
/* 300 */       centerOfMassA.basis.transform(jointAxis1);
/*     */ 
/* 302 */       Vector3f hingeAxisWorld = localStack.get$javax$vecmath$Vector3f();
/* 303 */       this.rbAFrame.basis.getColumn(2, hingeAxisWorld);
/* 304 */       centerOfMassA.basis.transform(hingeAxisWorld);
/*     */ 
/* 306 */       mat1.transpose(centerOfMassA.basis);
/* 307 */       mat2.transpose(centerOfMassB.basis);
/* 308 */       this.jacAng[0].init(jointAxis0, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/* 315 */       this.jacAng[1].init(jointAxis1, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/* 322 */       this.jacAng[2].init(hingeAxisWorld, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/* 329 */       float hingeAngle = getHingeAngle();
/*     */ 
/* 332 */       this.correction = 0.0F;
/* 333 */       this.limitSign = 0.0F;
/* 334 */       this.solveLimit = false;
/* 335 */       this.accLimitImpulse = 0.0F;
/*     */ 
/* 337 */       if (this.lowerLimit < this.upperLimit) {
/* 338 */         if (hingeAngle <= this.lowerLimit * this.limitSoftness) {
/* 339 */           this.correction = (this.lowerLimit - hingeAngle);
/* 340 */           this.limitSign = 1.0F;
/* 341 */           this.solveLimit = true;
/*     */         }
/* 343 */         else if (hingeAngle >= this.upperLimit * this.limitSoftness) {
/* 344 */           this.correction = (this.upperLimit - hingeAngle);
/* 345 */           this.limitSign = -1.0F;
/* 346 */           this.solveLimit = true;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 351 */       Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 352 */       this.rbAFrame.basis.getColumn(2, axisA);
/* 353 */       centerOfMassA.basis.transform(axisA);
/*     */ 
/* 355 */       this.kHinge = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(axisA) + getRigidBodyB().computeAngularImpulseDenominator(axisA)));
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 357 */       .Stack tmp792_790 = localStack; tmp792_790.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp796_792 = tmp792_790; tmp796_792.pop$javax$vecmath$Vector3f(); tmp796_792.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float arg1) {
/* 361 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 362 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 363 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 365 */       Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 366 */       Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 368 */       Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 369 */       centerOfMassA.transform(pivotAInW);
/*     */ 
/* 371 */       Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 372 */       centerOfMassB.transform(pivotBInW);
/*     */ 
/* 374 */       float tau = 0.3F;
/*     */ 
/* 377 */       if (!this.angularOnly) {
/* 378 */         Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 379 */         rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 381 */         Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 382 */         rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 384 */         Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 385 */         Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 386 */         Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 387 */         vel.sub(vel1, vel2);
/*     */ 
/* 389 */         for (int i = 0; i < 3; i++) {
/* 390 */           Vector3f normal = this.jac[i].linearJointAxis;
/* 391 */           float jacDiagABInv = 1.0F / this.jac[i].getDiagonal();
/*     */ 
/* 394 */           float rel_vel = normal.dot(vel);
/*     */ 
/* 396 */           tmp.sub(pivotAInW, pivotBInW);
/* 397 */           float depth = -tmp.dot(normal);
/* 398 */           float impulse = depth * tau / timeStep * jacDiagABInv - rel_vel * jacDiagABInv;
/* 399 */           this.appliedImpulse += impulse;
/* 400 */           Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 401 */           impulse_vector.scale(impulse, normal);
/*     */ 
/* 403 */           tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 404 */           this.rbA.applyImpulse(impulse_vector, tmp);
/*     */ 
/* 406 */           tmp.negate(impulse_vector);
/* 407 */           tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 408 */           this.rbB.applyImpulse(tmp, tmp2);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 417 */       Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 418 */       this.rbAFrame.basis.getColumn(2, axisA);
/* 419 */       centerOfMassA.basis.transform(axisA);
/*     */ 
/* 421 */       Vector3f axisB = localStack.get$javax$vecmath$Vector3f();
/* 422 */       this.rbBFrame.basis.getColumn(2, axisB);
/* 423 */       centerOfMassB.basis.transform(axisB);
/*     */ 
/* 425 */       Vector3f angVelA = getRigidBodyA().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 426 */       Vector3f angVelB = getRigidBodyB().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 428 */       Vector3f angVelAroundHingeAxisA = localStack.get$javax$vecmath$Vector3f();
/* 429 */       angVelAroundHingeAxisA.scale(axisA.dot(angVelA), axisA);
/*     */ 
/* 431 */       Vector3f angVelAroundHingeAxisB = localStack.get$javax$vecmath$Vector3f();
/* 432 */       angVelAroundHingeAxisB.scale(axisB.dot(angVelB), axisB);
/*     */ 
/* 434 */       Vector3f angAorthog = localStack.get$javax$vecmath$Vector3f();
/* 435 */       angAorthog.sub(angVelA, angVelAroundHingeAxisA);
/*     */ 
/* 437 */       Vector3f angBorthog = localStack.get$javax$vecmath$Vector3f();
/* 438 */       angBorthog.sub(angVelB, angVelAroundHingeAxisB);
/*     */ 
/* 440 */       Vector3f velrelOrthog = localStack.get$javax$vecmath$Vector3f();
/* 441 */       velrelOrthog.sub(angAorthog, angBorthog);
/*     */ 
/* 445 */       float relaxation = 1.0F;
/* 446 */       float len = velrelOrthog.length();
/* 447 */       if (len > 1.0E-005F) {
/* 448 */         Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 449 */         normal.normalize(velrelOrthog);
/*     */ 
/* 451 */         float denom = getRigidBodyA().computeAngularImpulseDenominator(normal) + getRigidBodyB().computeAngularImpulseDenominator(normal);
/*     */ 
/* 455 */         velrelOrthog.scale(1.0F / denom * this.relaxationFactor);
/*     */       }
/*     */ 
/* 461 */       Vector3f angularError = localStack.get$javax$vecmath$Vector3f();
/* 462 */       angularError.cross(axisA, axisB);
/* 463 */       angularError.negate();
/* 464 */       angularError.scale(1.0F / timeStep);
/* 465 */       float len2 = angularError.length();
/* 466 */       if (len2 > 1.0E-005F) {
/* 467 */         Vector3f normal2 = localStack.get$javax$vecmath$Vector3f();
/* 468 */         normal2.normalize(angularError);
/*     */ 
/* 470 */         float denom2 = getRigidBodyA().computeAngularImpulseDenominator(normal2) + getRigidBodyB().computeAngularImpulseDenominator(normal2);
/*     */ 
/* 472 */         angularError.scale(1.0F / denom2 * relaxation);
/*     */       }
/*     */ 
/* 475 */       tmp.negate(velrelOrthog);
/* 476 */       tmp.add(angularError);
/* 477 */       this.rbA.applyTorqueImpulse(tmp);
/*     */ 
/* 479 */       tmp.sub(velrelOrthog, angularError);
/* 480 */       this.rbB.applyTorqueImpulse(tmp);
/*     */ 
/* 483 */       if (this.solveLimit) {
/* 484 */         tmp.sub(angVelB, angVelA);
/* 485 */         float amplitude = (tmp.dot(axisA) * this.relaxationFactor + this.correction * (1.0F / timeStep) * this.biasFactor) * this.limitSign;
/*     */ 
/* 487 */         float impulseMag = amplitude * this.kHinge;
/*     */ 
/* 490 */         float temp = this.accLimitImpulse;
/* 491 */         this.accLimitImpulse = Math.max(this.accLimitImpulse + impulseMag, 0.0F);
/* 492 */         impulseMag = this.accLimitImpulse - temp;
/*     */ 
/* 494 */         Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 495 */         impulse.scale(impulseMag * this.limitSign, axisA);
/*     */ 
/* 497 */         this.rbA.applyTorqueImpulse(impulse);
/*     */ 
/* 499 */         tmp.negate(impulse);
/* 500 */         this.rbB.applyTorqueImpulse(tmp);
/*     */       }
/*     */ 
/* 505 */       if (this.enableAngularMotor)
/*     */       {
/* 507 */         Vector3f angularLimit = localStack.get$javax$vecmath$Vector3f();
/* 508 */         angularLimit.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 510 */         Vector3f velrel = localStack.get$javax$vecmath$Vector3f();
/* 511 */         velrel.sub(angVelAroundHingeAxisA, angVelAroundHingeAxisB);
/* 512 */         float projRelVel = velrel.dot(axisA);
/*     */ 
/* 514 */         float desiredMotorVel = this.motorTargetVelocity;
/* 515 */         float motor_relvel = desiredMotorVel - projRelVel;
/*     */ 
/* 517 */         float unclippedMotorImpulse = this.kHinge * motor_relvel;
/*     */ 
/* 519 */         float clippedMotorImpulse = unclippedMotorImpulse > this.maxMotorImpulse ? this.maxMotorImpulse : unclippedMotorImpulse;
/* 520 */         clippedMotorImpulse = clippedMotorImpulse < -this.maxMotorImpulse ? -this.maxMotorImpulse : clippedMotorImpulse;
/* 521 */         Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
/* 522 */         motorImp.scale(clippedMotorImpulse, axisA);
/*     */ 
/* 524 */         tmp.add(motorImp, angularLimit);
/* 525 */         this.rbA.applyTorqueImpulse(tmp);
/*     */ 
/* 527 */         tmp.negate(motorImp);
/* 528 */         tmp.sub(angularLimit);
/* 529 */         this.rbB.applyTorqueImpulse(tmp);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 532 */       .Stack tmp1058_1056 = localStack; tmp1058_1056.pop$com$bulletphysics$linearmath$Transform(); tmp1058_1056.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void updateRHS(float timeStep) {
/*     */   }
/*     */ 
/*     */   public float getHingeAngle() {
/* 538 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 539 */       Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 541 */       Vector3f refAxis0 = localStack.get$javax$vecmath$Vector3f();
/* 542 */       this.rbAFrame.basis.getColumn(0, refAxis0);
/* 543 */       centerOfMassA.basis.transform(refAxis0);
/*     */ 
/* 545 */       Vector3f refAxis1 = localStack.get$javax$vecmath$Vector3f();
/* 546 */       this.rbAFrame.basis.getColumn(1, refAxis1);
/* 547 */       centerOfMassA.basis.transform(refAxis1);
/*     */ 
/* 549 */       Vector3f swingAxis = localStack.get$javax$vecmath$Vector3f();
/* 550 */       this.rbBFrame.basis.getColumn(1, swingAxis);
/* 551 */       centerOfMassB.basis.transform(swingAxis);
/*     */ 
/* 553 */       return ScalarUtil.atan2Fast(swingAxis.dot(refAxis0), swingAxis.dot(refAxis1));
/*     */     }
/*     */     finally
/*     */     {
/* 553 */       .Stack tmp152_150 = localStack; tmp152_150.pop$com$bulletphysics$linearmath$Transform(); tmp152_150.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setAngularOnly(boolean angularOnly) {
/* 557 */     this.angularOnly = angularOnly;
/*     */   }
/*     */ 
/*     */   public void enableAngularMotor(boolean enableMotor, float targetVelocity, float maxMotorImpulse) {
/* 561 */     this.enableAngularMotor = enableMotor;
/* 562 */     this.motorTargetVelocity = targetVelocity;
/* 563 */     this.maxMotorImpulse = maxMotorImpulse;
/*     */   }
/*     */ 
/*     */   public void setLimit(float low, float high) {
/* 567 */     setLimit(low, high, 0.9F, 0.3F, 1.0F);
/*     */   }
/*     */ 
/*     */   public void setLimit(float low, float high, float _softness, float _biasFactor, float _relaxationFactor) {
/* 571 */     this.lowerLimit = low;
/* 572 */     this.upperLimit = high;
/*     */ 
/* 574 */     this.limitSoftness = _softness;
/* 575 */     this.biasFactor = _biasFactor;
/* 576 */     this.relaxationFactor = _relaxationFactor;
/*     */   }
/*     */ 
/*     */   public float getLowerLimit() {
/* 580 */     return this.lowerLimit;
/*     */   }
/*     */ 
/*     */   public float getUpperLimit() {
/* 584 */     return this.upperLimit;
/*     */   }
/*     */ 
/*     */   public Transform getAFrame(Transform out) {
/* 588 */     out.set(this.rbAFrame);
/* 589 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getBFrame(Transform out) {
/* 593 */     out.set(this.rbBFrame);
/* 594 */     return out;
/*     */   }
/*     */ 
/*     */   public boolean getSolveLimit() {
/* 598 */     return this.solveLimit;
/*     */   }
/*     */ 
/*     */   public float getLimitSign() {
/* 602 */     return this.limitSign;
/*     */   }
/*     */ 
/*     */   public boolean getAngularOnly() {
/* 606 */     return this.angularOnly;
/*     */   }
/*     */ 
/*     */   public boolean getEnableAngularMotor() {
/* 610 */     return this.enableAngularMotor;
/*     */   }
/*     */ 
/*     */   public float getMotorTargetVelosity() {
/* 614 */     return this.motorTargetVelocity;
/*     */   }
/*     */ 
/*     */   public float getMaxMotorImpulse() {
/* 618 */     return this.maxMotorImpulse;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.HingeConstraint
 * JD-Core Version:    0.6.2
 */