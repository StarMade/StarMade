/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class SliderConstraint extends TypedConstraint
/*     */ {
/*     */   public static final float SLIDER_CONSTRAINT_DEF_SOFTNESS = 1.0F;
/*     */   public static final float SLIDER_CONSTRAINT_DEF_DAMPING = 1.0F;
/*     */   public static final float SLIDER_CONSTRAINT_DEF_RESTITUTION = 0.7F;
/*  54 */   protected final Transform frameInA = new Transform();
/*  55 */   protected final Transform frameInB = new Transform();
/*     */   protected boolean useLinearReferenceFrameA;
/*     */   protected float lowerLinLimit;
/*     */   protected float upperLinLimit;
/*     */   protected float lowerAngLimit;
/*     */   protected float upperAngLimit;
/*     */   protected float softnessDirLin;
/*     */   protected float restitutionDirLin;
/*     */   protected float dampingDirLin;
/*     */   protected float softnessDirAng;
/*     */   protected float restitutionDirAng;
/*     */   protected float dampingDirAng;
/*     */   protected float softnessLimLin;
/*     */   protected float restitutionLimLin;
/*     */   protected float dampingLimLin;
/*     */   protected float softnessLimAng;
/*     */   protected float restitutionLimAng;
/*     */   protected float dampingLimAng;
/*     */   protected float softnessOrthoLin;
/*     */   protected float restitutionOrthoLin;
/*     */   protected float dampingOrthoLin;
/*     */   protected float softnessOrthoAng;
/*     */   protected float restitutionOrthoAng;
/*     */   protected float dampingOrthoAng;
/*     */   protected boolean solveLinLim;
/*     */   protected boolean solveAngLim;
/*  93 */   protected JacobianEntry[] jacLin = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  94 */   protected float[] jacLinDiagABInv = new float[3];
/*     */ 
/*  96 */   protected JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*     */   protected float timeStep;
/*  99 */   protected final Transform calculatedTransformA = new Transform();
/* 100 */   protected final Transform calculatedTransformB = new Transform();
/*     */ 
/* 102 */   protected final Vector3f sliderAxis = new Vector3f();
/* 103 */   protected final Vector3f realPivotAInW = new Vector3f();
/* 104 */   protected final Vector3f realPivotBInW = new Vector3f();
/* 105 */   protected final Vector3f projPivotInW = new Vector3f();
/* 106 */   protected final Vector3f delta = new Vector3f();
/* 107 */   protected final Vector3f depth = new Vector3f();
/* 108 */   protected final Vector3f relPosA = new Vector3f();
/* 109 */   protected final Vector3f relPosB = new Vector3f();
/*     */   protected float linPos;
/*     */   protected float angDepth;
/*     */   protected float kAngle;
/*     */   protected boolean poweredLinMotor;
/*     */   protected float targetLinMotorVelocity;
/*     */   protected float maxLinMotorForce;
/*     */   protected float accumulatedLinMotorImpulse;
/*     */   protected boolean poweredAngMotor;
/*     */   protected float targetAngMotorVelocity;
/*     */   protected float maxAngMotorForce;
/*     */   protected float accumulatedAngMotorImpulse;
/*     */ 
/*     */   public SliderConstraint()
/*     */   {
/* 127 */     super(TypedConstraintType.SLIDER_CONSTRAINT_TYPE);
/* 128 */     this.useLinearReferenceFrameA = true;
/* 129 */     initParams();
/*     */   }
/*     */ 
/*     */   public SliderConstraint(RigidBody rbA, RigidBody rbB, Transform frameInA, Transform frameInB, boolean useLinearReferenceFrameA) {
/* 133 */     super(TypedConstraintType.SLIDER_CONSTRAINT_TYPE, rbA, rbB);
/* 134 */     this.frameInA.set(frameInA);
/* 135 */     this.frameInB.set(frameInB);
/* 136 */     this.useLinearReferenceFrameA = useLinearReferenceFrameA;
/* 137 */     initParams();
/*     */   }
/*     */ 
/*     */   protected void initParams() {
/* 141 */     this.lowerLinLimit = 1.0F;
/* 142 */     this.upperLinLimit = -1.0F;
/* 143 */     this.lowerAngLimit = 0.0F;
/* 144 */     this.upperAngLimit = 0.0F;
/* 145 */     this.softnessDirLin = 1.0F;
/* 146 */     this.restitutionDirLin = 0.7F;
/* 147 */     this.dampingDirLin = 0.0F;
/* 148 */     this.softnessDirAng = 1.0F;
/* 149 */     this.restitutionDirAng = 0.7F;
/* 150 */     this.dampingDirAng = 0.0F;
/* 151 */     this.softnessOrthoLin = 1.0F;
/* 152 */     this.restitutionOrthoLin = 0.7F;
/* 153 */     this.dampingOrthoLin = 1.0F;
/* 154 */     this.softnessOrthoAng = 1.0F;
/* 155 */     this.restitutionOrthoAng = 0.7F;
/* 156 */     this.dampingOrthoAng = 1.0F;
/* 157 */     this.softnessLimLin = 1.0F;
/* 158 */     this.restitutionLimLin = 0.7F;
/* 159 */     this.dampingLimLin = 1.0F;
/* 160 */     this.softnessLimAng = 1.0F;
/* 161 */     this.restitutionLimAng = 0.7F;
/* 162 */     this.dampingLimAng = 1.0F;
/*     */ 
/* 164 */     this.poweredLinMotor = false;
/* 165 */     this.targetLinMotorVelocity = 0.0F;
/* 166 */     this.maxLinMotorForce = 0.0F;
/* 167 */     this.accumulatedLinMotorImpulse = 0.0F;
/*     */ 
/* 169 */     this.poweredAngMotor = false;
/* 170 */     this.targetAngMotorVelocity = 0.0F;
/* 171 */     this.maxAngMotorForce = 0.0F;
/* 172 */     this.accumulatedAngMotorImpulse = 0.0F;
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/* 177 */     if (this.useLinearReferenceFrameA) {
/* 178 */       buildJacobianInt(this.rbA, this.rbB, this.frameInA, this.frameInB);
/*     */     }
/*     */     else
/* 181 */       buildJacobianInt(this.rbB, this.rbA, this.frameInB, this.frameInA);
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float timeStep)
/*     */   {
/* 187 */     this.timeStep = timeStep;
/* 188 */     if (this.useLinearReferenceFrameA) {
/* 189 */       solveConstraintInt(this.rbA, this.rbB);
/*     */     }
/*     */     else
/* 192 */       solveConstraintInt(this.rbB, this.rbA);
/*     */   }
/*     */ 
/*     */   public Transform getCalculatedTransformA(Transform out)
/*     */   {
/* 197 */     out.set(this.calculatedTransformA);
/* 198 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getCalculatedTransformB(Transform out) {
/* 202 */     out.set(this.calculatedTransformB);
/* 203 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getFrameOffsetA(Transform out) {
/* 207 */     out.set(this.frameInA);
/* 208 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getFrameOffsetB(Transform out) {
/* 212 */     out.set(this.frameInB);
/* 213 */     return out;
/*     */   }
/*     */ 
/*     */   public float getLowerLinLimit() {
/* 217 */     return this.lowerLinLimit;
/*     */   }
/*     */ 
/*     */   public void setLowerLinLimit(float lowerLimit) {
/* 221 */     this.lowerLinLimit = lowerLimit;
/*     */   }
/*     */ 
/*     */   public float getUpperLinLimit() {
/* 225 */     return this.upperLinLimit;
/*     */   }
/*     */ 
/*     */   public void setUpperLinLimit(float upperLimit) {
/* 229 */     this.upperLinLimit = upperLimit;
/*     */   }
/*     */ 
/*     */   public float getLowerAngLimit() {
/* 233 */     return this.lowerAngLimit;
/*     */   }
/*     */ 
/*     */   public void setLowerAngLimit(float lowerLimit) {
/* 237 */     this.lowerAngLimit = lowerLimit;
/*     */   }
/*     */ 
/*     */   public float getUpperAngLimit() {
/* 241 */     return this.upperAngLimit;
/*     */   }
/*     */ 
/*     */   public void setUpperAngLimit(float upperLimit) {
/* 245 */     this.upperAngLimit = upperLimit;
/*     */   }
/*     */ 
/*     */   public boolean getUseLinearReferenceFrameA() {
/* 249 */     return this.useLinearReferenceFrameA;
/*     */   }
/*     */ 
/*     */   public float getSoftnessDirLin() {
/* 253 */     return this.softnessDirLin;
/*     */   }
/*     */ 
/*     */   public float getRestitutionDirLin() {
/* 257 */     return this.restitutionDirLin;
/*     */   }
/*     */ 
/*     */   public float getDampingDirLin() {
/* 261 */     return this.dampingDirLin;
/*     */   }
/*     */ 
/*     */   public float getSoftnessDirAng() {
/* 265 */     return this.softnessDirAng;
/*     */   }
/*     */ 
/*     */   public float getRestitutionDirAng() {
/* 269 */     return this.restitutionDirAng;
/*     */   }
/*     */ 
/*     */   public float getDampingDirAng() {
/* 273 */     return this.dampingDirAng;
/*     */   }
/*     */ 
/*     */   public float getSoftnessLimLin() {
/* 277 */     return this.softnessLimLin;
/*     */   }
/*     */ 
/*     */   public float getRestitutionLimLin() {
/* 281 */     return this.restitutionLimLin;
/*     */   }
/*     */ 
/*     */   public float getDampingLimLin() {
/* 285 */     return this.dampingLimLin;
/*     */   }
/*     */ 
/*     */   public float getSoftnessLimAng() {
/* 289 */     return this.softnessLimAng;
/*     */   }
/*     */ 
/*     */   public float getRestitutionLimAng() {
/* 293 */     return this.restitutionLimAng;
/*     */   }
/*     */ 
/*     */   public float getDampingLimAng() {
/* 297 */     return this.dampingLimAng;
/*     */   }
/*     */ 
/*     */   public float getSoftnessOrthoLin() {
/* 301 */     return this.softnessOrthoLin;
/*     */   }
/*     */ 
/*     */   public float getRestitutionOrthoLin() {
/* 305 */     return this.restitutionOrthoLin;
/*     */   }
/*     */ 
/*     */   public float getDampingOrthoLin() {
/* 309 */     return this.dampingOrthoLin;
/*     */   }
/*     */ 
/*     */   public float getSoftnessOrthoAng() {
/* 313 */     return this.softnessOrthoAng;
/*     */   }
/*     */ 
/*     */   public float getRestitutionOrthoAng() {
/* 317 */     return this.restitutionOrthoAng;
/*     */   }
/*     */ 
/*     */   public float getDampingOrthoAng() {
/* 321 */     return this.dampingOrthoAng;
/*     */   }
/*     */ 
/*     */   public void setSoftnessDirLin(float softnessDirLin) {
/* 325 */     this.softnessDirLin = softnessDirLin;
/*     */   }
/*     */ 
/*     */   public void setRestitutionDirLin(float restitutionDirLin) {
/* 329 */     this.restitutionDirLin = restitutionDirLin;
/*     */   }
/*     */ 
/*     */   public void setDampingDirLin(float dampingDirLin) {
/* 333 */     this.dampingDirLin = dampingDirLin;
/*     */   }
/*     */ 
/*     */   public void setSoftnessDirAng(float softnessDirAng) {
/* 337 */     this.softnessDirAng = softnessDirAng;
/*     */   }
/*     */ 
/*     */   public void setRestitutionDirAng(float restitutionDirAng) {
/* 341 */     this.restitutionDirAng = restitutionDirAng;
/*     */   }
/*     */ 
/*     */   public void setDampingDirAng(float dampingDirAng) {
/* 345 */     this.dampingDirAng = dampingDirAng;
/*     */   }
/*     */ 
/*     */   public void setSoftnessLimLin(float softnessLimLin) {
/* 349 */     this.softnessLimLin = softnessLimLin;
/*     */   }
/*     */ 
/*     */   public void setRestitutionLimLin(float restitutionLimLin) {
/* 353 */     this.restitutionLimLin = restitutionLimLin;
/*     */   }
/*     */ 
/*     */   public void setDampingLimLin(float dampingLimLin) {
/* 357 */     this.dampingLimLin = dampingLimLin;
/*     */   }
/*     */ 
/*     */   public void setSoftnessLimAng(float softnessLimAng) {
/* 361 */     this.softnessLimAng = softnessLimAng;
/*     */   }
/*     */ 
/*     */   public void setRestitutionLimAng(float restitutionLimAng) {
/* 365 */     this.restitutionLimAng = restitutionLimAng;
/*     */   }
/*     */ 
/*     */   public void setDampingLimAng(float dampingLimAng) {
/* 369 */     this.dampingLimAng = dampingLimAng;
/*     */   }
/*     */ 
/*     */   public void setSoftnessOrthoLin(float softnessOrthoLin) {
/* 373 */     this.softnessOrthoLin = softnessOrthoLin;
/*     */   }
/*     */ 
/*     */   public void setRestitutionOrthoLin(float restitutionOrthoLin) {
/* 377 */     this.restitutionOrthoLin = restitutionOrthoLin;
/*     */   }
/*     */ 
/*     */   public void setDampingOrthoLin(float dampingOrthoLin) {
/* 381 */     this.dampingOrthoLin = dampingOrthoLin;
/*     */   }
/*     */ 
/*     */   public void setSoftnessOrthoAng(float softnessOrthoAng) {
/* 385 */     this.softnessOrthoAng = softnessOrthoAng;
/*     */   }
/*     */ 
/*     */   public void setRestitutionOrthoAng(float restitutionOrthoAng) {
/* 389 */     this.restitutionOrthoAng = restitutionOrthoAng;
/*     */   }
/*     */ 
/*     */   public void setDampingOrthoAng(float dampingOrthoAng) {
/* 393 */     this.dampingOrthoAng = dampingOrthoAng;
/*     */   }
/*     */ 
/*     */   public void setPoweredLinMotor(boolean onOff) {
/* 397 */     this.poweredLinMotor = onOff;
/*     */   }
/*     */ 
/*     */   public boolean getPoweredLinMotor() {
/* 401 */     return this.poweredLinMotor;
/*     */   }
/*     */ 
/*     */   public void setTargetLinMotorVelocity(float targetLinMotorVelocity) {
/* 405 */     this.targetLinMotorVelocity = targetLinMotorVelocity;
/*     */   }
/*     */ 
/*     */   public float getTargetLinMotorVelocity() {
/* 409 */     return this.targetLinMotorVelocity;
/*     */   }
/*     */ 
/*     */   public void setMaxLinMotorForce(float maxLinMotorForce) {
/* 413 */     this.maxLinMotorForce = maxLinMotorForce;
/*     */   }
/*     */ 
/*     */   public float getMaxLinMotorForce() {
/* 417 */     return this.maxLinMotorForce;
/*     */   }
/*     */ 
/*     */   public void setPoweredAngMotor(boolean onOff) {
/* 421 */     this.poweredAngMotor = onOff;
/*     */   }
/*     */ 
/*     */   public boolean getPoweredAngMotor() {
/* 425 */     return this.poweredAngMotor;
/*     */   }
/*     */ 
/*     */   public void setTargetAngMotorVelocity(float targetAngMotorVelocity) {
/* 429 */     this.targetAngMotorVelocity = targetAngMotorVelocity;
/*     */   }
/*     */ 
/*     */   public float getTargetAngMotorVelocity() {
/* 433 */     return this.targetAngMotorVelocity;
/*     */   }
/*     */ 
/*     */   public void setMaxAngMotorForce(float maxAngMotorForce) {
/* 437 */     this.maxAngMotorForce = maxAngMotorForce;
/*     */   }
/*     */ 
/*     */   public float getMaxAngMotorForce() {
/* 441 */     return this.maxAngMotorForce;
/*     */   }
/*     */ 
/*     */   public float getLinearPos() {
/* 445 */     return this.linPos;
/*     */   }
/*     */ 
/*     */   public boolean getSolveLinLimit()
/*     */   {
/* 451 */     return this.solveLinLim;
/*     */   }
/*     */ 
/*     */   public float getLinDepth() {
/* 455 */     return this.depth.x;
/*     */   }
/*     */ 
/*     */   public boolean getSolveAngLimit() {
/* 459 */     return this.solveAngLim;
/*     */   }
/*     */ 
/*     */   public float getAngDepth() {
/* 463 */     return this.angDepth;
/*     */   }
/*     */ 
/*     */   public void buildJacobianInt(RigidBody arg1, RigidBody arg2, Transform arg3, Transform arg4)
/*     */   {
/* 469 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 470 */       Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 471 */       Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 472 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 473 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 476 */       this.calculatedTransformA.mul(rbA.getCenterOfMassTransform(tmpTrans), frameInA);
/* 477 */       this.calculatedTransformB.mul(rbB.getCenterOfMassTransform(tmpTrans), frameInB);
/* 478 */       this.realPivotAInW.set(this.calculatedTransformA.origin);
/* 479 */       this.realPivotBInW.set(this.calculatedTransformB.origin);
/* 480 */       this.calculatedTransformA.basis.getColumn(0, tmp);
/* 481 */       this.sliderAxis.set(tmp);
/* 482 */       this.delta.sub(this.realPivotBInW, this.realPivotAInW);
/* 483 */       this.projPivotInW.scaleAdd(this.sliderAxis.dot(this.delta), this.sliderAxis, this.realPivotAInW);
/* 484 */       this.relPosA.sub(this.projPivotInW, rbA.getCenterOfMassPosition(tmp));
/* 485 */       this.relPosB.sub(this.realPivotBInW, rbB.getCenterOfMassPosition(tmp));
/* 486 */       Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 489 */       for (int i = 0; i < 3; i++) {
/* 490 */         this.calculatedTransformA.basis.getColumn(i, normalWorld);
/*     */ 
/* 492 */         Matrix3f mat1 = rbA.getCenterOfMassTransform(tmpTrans1).basis;
/* 493 */         mat1.transpose();
/*     */ 
/* 495 */         Matrix3f mat2 = rbB.getCenterOfMassTransform(tmpTrans2).basis;
/* 496 */         mat2.transpose();
/*     */ 
/* 498 */         this.jacLin[i].init(mat1, mat2, this.relPosA, this.relPosB, normalWorld, rbA.getInvInertiaDiagLocal(tmp), rbA.getInvMass(), rbB.getInvInertiaDiagLocal(tmp2), rbB.getInvMass());
/*     */ 
/* 508 */         this.jacLinDiagABInv[i] = (1.0F / this.jacLin[i].getDiagonal());
/* 509 */         VectorUtil.setCoord(this.depth, i, this.delta.dot(normalWorld));
/*     */       }
/* 511 */       testLinLimits();
/*     */ 
/* 514 */       for (int i = 0; i < 3; i++) {
/* 515 */         this.calculatedTransformA.basis.getColumn(i, normalWorld);
/*     */ 
/* 517 */         Matrix3f mat1 = rbA.getCenterOfMassTransform(tmpTrans1).basis;
/* 518 */         mat1.transpose();
/*     */ 
/* 520 */         Matrix3f mat2 = rbB.getCenterOfMassTransform(tmpTrans2).basis;
/* 521 */         mat2.transpose();
/*     */ 
/* 523 */         this.jacAng[i].init(normalWorld, mat1, mat2, rbA.getInvInertiaDiagLocal(tmp), rbB.getInvInertiaDiagLocal(tmp2));
/*     */       }
/*     */ 
/* 530 */       testAngLimits();
/*     */ 
/* 532 */       Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 533 */       this.calculatedTransformA.basis.getColumn(0, axisA);
/* 534 */       this.kAngle = (1.0F / (rbA.computeAngularImpulseDenominator(axisA) + rbB.computeAngularImpulseDenominator(axisA)));
/*     */ 
/* 536 */       this.accumulatedLinMotorImpulse = 0.0F;
/* 537 */       this.accumulatedAngMotorImpulse = 0.0F;
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 538 */       .Stack tmp510_508 = localStack; tmp510_508.pop$com$bulletphysics$linearmath$Transform(); tmp510_508.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void solveConstraintInt(RigidBody arg1, RigidBody arg2) {
/* 541 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 544 */       Vector3f velA = rbA.getVelocityInLocalPoint(this.relPosA, localStack.get$javax$vecmath$Vector3f());
/* 545 */       Vector3f velB = rbB.getVelocityInLocalPoint(this.relPosB, localStack.get$javax$vecmath$Vector3f());
/* 546 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 547 */       vel.sub(velA, velB);
/*     */ 
/* 549 */       Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 551 */       for (int i = 0; i < 3; i++) {
/* 552 */         Vector3f normal = this.jacLin[i].linearJointAxis;
/* 553 */         float rel_vel = normal.dot(vel);
/*     */ 
/* 555 */         float depth = VectorUtil.getCoord(this.depth, i);
/*     */ 
/* 557 */         float softness = this.solveLinLim ? this.softnessLimLin : i != 0 ? this.softnessOrthoLin : this.softnessDirLin;
/* 558 */         float restitution = this.solveLinLim ? this.restitutionLimLin : i != 0 ? this.restitutionOrthoLin : this.restitutionDirLin;
/* 559 */         float damping = this.solveLinLim ? this.dampingLimLin : i != 0 ? this.dampingOrthoLin : this.dampingDirLin;
/*     */ 
/* 561 */         float normalImpulse = softness * (restitution * depth / this.timeStep - damping * rel_vel) * this.jacLinDiagABInv[i];
/* 562 */         impulse_vector.scale(normalImpulse, normal);
/* 563 */         rbA.applyImpulse(impulse_vector, this.relPosA);
/* 564 */         tmp.negate(impulse_vector);
/* 565 */         rbB.applyImpulse(tmp, this.relPosB);
/*     */ 
/* 567 */         if ((this.poweredLinMotor) && (i == 0))
/*     */         {
/* 569 */           if (this.accumulatedLinMotorImpulse < this.maxLinMotorForce) {
/* 570 */             float desiredMotorVel = this.targetLinMotorVelocity;
/* 571 */             float motor_relvel = desiredMotorVel + rel_vel;
/* 572 */             normalImpulse = -motor_relvel * this.jacLinDiagABInv[i];
/*     */ 
/* 574 */             float new_acc = this.accumulatedLinMotorImpulse + Math.abs(normalImpulse);
/* 575 */             if (new_acc > this.maxLinMotorForce) {
/* 576 */               new_acc = this.maxLinMotorForce;
/*     */             }
/* 578 */             float del = new_acc - this.accumulatedLinMotorImpulse;
/* 579 */             if (normalImpulse < 0.0F) {
/* 580 */               normalImpulse = -del;
/*     */             }
/*     */             else {
/* 583 */               normalImpulse = del;
/*     */             }
/* 585 */             this.accumulatedLinMotorImpulse = new_acc;
/*     */ 
/* 587 */             impulse_vector.scale(normalImpulse, normal);
/* 588 */             rbA.applyImpulse(impulse_vector, this.relPosA);
/* 589 */             tmp.negate(impulse_vector);
/* 590 */             rbB.applyImpulse(tmp, this.relPosB);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 597 */       Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
/* 598 */       this.calculatedTransformA.basis.getColumn(0, axisA);
/* 599 */       Vector3f axisB = localStack.get$javax$vecmath$Vector3f();
/* 600 */       this.calculatedTransformB.basis.getColumn(0, axisB);
/*     */ 
/* 602 */       Vector3f angVelA = rbA.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 603 */       Vector3f angVelB = rbB.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 605 */       Vector3f angVelAroundAxisA = localStack.get$javax$vecmath$Vector3f();
/* 606 */       angVelAroundAxisA.scale(axisA.dot(angVelA), axisA);
/* 607 */       Vector3f angVelAroundAxisB = localStack.get$javax$vecmath$Vector3f();
/* 608 */       angVelAroundAxisB.scale(axisB.dot(angVelB), axisB);
/*     */ 
/* 610 */       Vector3f angAorthog = localStack.get$javax$vecmath$Vector3f();
/* 611 */       angAorthog.sub(angVelA, angVelAroundAxisA);
/* 612 */       Vector3f angBorthog = localStack.get$javax$vecmath$Vector3f();
/* 613 */       angBorthog.sub(angVelB, angVelAroundAxisB);
/* 614 */       Vector3f velrelOrthog = localStack.get$javax$vecmath$Vector3f();
/* 615 */       velrelOrthog.sub(angAorthog, angBorthog);
/*     */ 
/* 618 */       float len = velrelOrthog.length();
/* 619 */       if (len > 1.0E-005F) {
/* 620 */         Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 621 */         normal.normalize(velrelOrthog);
/* 622 */         float denom = rbA.computeAngularImpulseDenominator(normal) + rbB.computeAngularImpulseDenominator(normal);
/* 623 */         velrelOrthog.scale(1.0F / denom * this.dampingOrthoAng * this.softnessOrthoAng);
/*     */       }
/*     */ 
/* 627 */       Vector3f angularError = localStack.get$javax$vecmath$Vector3f();
/* 628 */       angularError.cross(axisA, axisB);
/* 629 */       angularError.scale(1.0F / this.timeStep);
/* 630 */       float len2 = angularError.length();
/* 631 */       if (len2 > 1.0E-005F) {
/* 632 */         Vector3f normal2 = localStack.get$javax$vecmath$Vector3f();
/* 633 */         normal2.normalize(angularError);
/* 634 */         float denom2 = rbA.computeAngularImpulseDenominator(normal2) + rbB.computeAngularImpulseDenominator(normal2);
/* 635 */         angularError.scale(1.0F / denom2 * this.restitutionOrthoAng * this.softnessOrthoAng);
/*     */       }
/*     */ 
/* 639 */       tmp.negate(velrelOrthog);
/* 640 */       tmp.add(angularError);
/* 641 */       rbA.applyTorqueImpulse(tmp);
/* 642 */       tmp.sub(velrelOrthog, angularError);
/* 643 */       rbB.applyTorqueImpulse(tmp);
/*     */       float impulseMag;
/* 647 */       if (this.solveAngLim) {
/* 648 */         tmp.sub(angVelB, angVelA);
/* 649 */         float impulseMag = tmp.dot(axisA) * this.dampingLimAng + this.angDepth * this.restitutionLimAng / this.timeStep;
/* 650 */         impulseMag *= this.kAngle * this.softnessLimAng;
/*     */       }
/*     */       else {
/* 653 */         tmp.sub(angVelB, angVelA);
/* 654 */         impulseMag = tmp.dot(axisA) * this.dampingDirAng + this.angDepth * this.restitutionDirAng / this.timeStep;
/* 655 */         impulseMag *= this.kAngle * this.softnessDirAng;
/*     */       }
/* 657 */       Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 658 */       impulse.scale(impulseMag, axisA);
/* 659 */       rbA.applyTorqueImpulse(impulse);
/* 660 */       tmp.negate(impulse);
/* 661 */       rbB.applyTorqueImpulse(tmp);
/*     */ 
/* 664 */       if ((this.poweredAngMotor) && 
/* 665 */         (this.accumulatedAngMotorImpulse < this.maxAngMotorForce)) {
/* 666 */         Vector3f velrel = localStack.get$javax$vecmath$Vector3f();
/* 667 */         velrel.sub(angVelAroundAxisA, angVelAroundAxisB);
/* 668 */         float projRelVel = velrel.dot(axisA);
/*     */ 
/* 670 */         float desiredMotorVel = this.targetAngMotorVelocity;
/* 671 */         float motor_relvel = desiredMotorVel - projRelVel;
/*     */ 
/* 673 */         float angImpulse = this.kAngle * motor_relvel;
/*     */ 
/* 675 */         float new_acc = this.accumulatedAngMotorImpulse + Math.abs(angImpulse);
/* 676 */         if (new_acc > this.maxAngMotorForce) {
/* 677 */           new_acc = this.maxAngMotorForce;
/*     */         }
/* 679 */         float del = new_acc - this.accumulatedAngMotorImpulse;
/* 680 */         if (angImpulse < 0.0F)
/* 681 */           angImpulse = -del;
/*     */         else {
/* 683 */           angImpulse = del;
/*     */         }
/* 685 */         this.accumulatedAngMotorImpulse = new_acc;
/*     */ 
/* 688 */         Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
/* 689 */         motorImp.scale(angImpulse, axisA);
/* 690 */         rbA.applyTorqueImpulse(motorImp);
/* 691 */         tmp.negate(motorImp);
/* 692 */         rbB.applyTorqueImpulse(tmp);
/*     */       }
/*     */       return; } finally {
/* 695 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void calculateTransforms()
/*     */   {
/* 700 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 702 */       if (this.useLinearReferenceFrameA) {
/* 703 */         this.calculatedTransformA.mul(this.rbA.getCenterOfMassTransform(tmpTrans), this.frameInA);
/* 704 */         this.calculatedTransformB.mul(this.rbB.getCenterOfMassTransform(tmpTrans), this.frameInB);
/*     */       }
/*     */       else {
/* 707 */         this.calculatedTransformA.mul(this.rbB.getCenterOfMassTransform(tmpTrans), this.frameInB);
/* 708 */         this.calculatedTransformB.mul(this.rbA.getCenterOfMassTransform(tmpTrans), this.frameInA);
/*     */       }
/* 710 */       this.realPivotAInW.set(this.calculatedTransformA.origin);
/* 711 */       this.realPivotBInW.set(this.calculatedTransformB.origin);
/* 712 */       this.calculatedTransformA.basis.getColumn(0, this.sliderAxis);
/* 713 */       this.delta.sub(this.realPivotBInW, this.realPivotAInW);
/* 714 */       this.projPivotInW.scaleAdd(this.sliderAxis.dot(this.delta), this.sliderAxis, this.realPivotAInW);
/* 715 */       Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 717 */       for (int i = 0; i < 3; i++) {
/* 718 */         this.calculatedTransformA.basis.getColumn(i, normalWorld);
/* 719 */         VectorUtil.setCoord(this.depth, i, this.delta.dot(normalWorld));
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 721 */       .Stack tmp249_247 = localStack; tmp249_247.pop$com$bulletphysics$linearmath$Transform(); tmp249_247.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void testLinLimits() {
/* 724 */     this.solveLinLim = false;
/* 725 */     this.linPos = this.depth.x;
/* 726 */     if (this.lowerLinLimit <= this.upperLinLimit) {
/* 727 */       if (this.depth.x > this.upperLinLimit) {
/* 728 */         this.depth.x -= this.upperLinLimit;
/* 729 */         this.solveLinLim = true;
/*     */       }
/* 731 */       else if (this.depth.x < this.lowerLinLimit) {
/* 732 */         this.depth.x -= this.lowerLinLimit;
/* 733 */         this.solveLinLim = true;
/*     */       }
/*     */       else {
/* 736 */         this.depth.x = 0.0F;
/*     */       }
/*     */     }
/*     */     else
/* 740 */       this.depth.x = 0.0F;
/*     */   }
/*     */ 
/*     */   public void testAngLimits()
/*     */   {
/* 745 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.angDepth = 0.0F;
/* 746 */       this.solveAngLim = false;
/* 747 */       if (this.lowerAngLimit <= this.upperAngLimit) {
/* 748 */         Vector3f axisA0 = localStack.get$javax$vecmath$Vector3f();
/* 749 */         this.calculatedTransformA.basis.getColumn(1, axisA0);
/* 750 */         Vector3f axisA1 = localStack.get$javax$vecmath$Vector3f();
/* 751 */         this.calculatedTransformA.basis.getColumn(2, axisA1);
/* 752 */         Vector3f axisB0 = localStack.get$javax$vecmath$Vector3f();
/* 753 */         this.calculatedTransformB.basis.getColumn(1, axisB0);
/*     */ 
/* 755 */         float rot = (float)Math.atan2(axisB0.dot(axisA1), axisB0.dot(axisA0));
/* 756 */         if (rot < this.lowerAngLimit) {
/* 757 */           this.angDepth = (rot - this.lowerAngLimit);
/* 758 */           this.solveAngLim = true;
/*     */         }
/* 760 */         else if (rot > this.upperAngLimit) {
/* 761 */           this.angDepth = (rot - this.upperAngLimit);
/* 762 */           this.solveAngLim = true;
/*     */         }
/*     */       }
/* 765 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f getAncorInA(Vector3f arg1)
/*     */   {
/* 770 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 772 */       Vector3f ancorInA = out;
/* 773 */       ancorInA.scaleAdd((this.lowerLinLimit + this.upperLinLimit) * 0.5F, this.sliderAxis, this.realPivotAInW);
/* 774 */       this.rbA.getCenterOfMassTransform(tmpTrans);
/* 775 */       tmpTrans.inverse();
/* 776 */       tmpTrans.transform(ancorInA);
/* 777 */       return ancorInA; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f getAncorInB(Vector3f out) {
/* 781 */     Vector3f ancorInB = out;
/* 782 */     ancorInB.set(this.frameInB.origin);
/* 783 */     return ancorInB;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SliderConstraint
 * JD-Core Version:    0.6.2
 */