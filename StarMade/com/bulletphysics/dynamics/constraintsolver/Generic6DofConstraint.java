/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class Generic6DofConstraint extends TypedConstraint
/*     */ {
/*  91 */   protected final Transform frameInA = new Transform();
/*  92 */   protected final Transform frameInB = new Transform();
/*     */ 
/*  94 */   protected final JacobianEntry[] jacLinear = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*  95 */   protected final JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*     */ 
/*  97 */   protected final TranslationalLimitMotor linearLimits = new TranslationalLimitMotor();
/*     */ 
/*  99 */   protected final RotationalLimitMotor[] angularLimits = { new RotationalLimitMotor(), new RotationalLimitMotor(), new RotationalLimitMotor() };
/*     */   protected float timeStep;
/* 102 */   protected final Transform calculatedTransformA = new Transform();
/* 103 */   protected final Transform calculatedTransformB = new Transform();
/* 104 */   protected final Vector3f calculatedAxisAngleDiff = new Vector3f();
/* 105 */   protected final Vector3f[] calculatedAxis = { new Vector3f(), new Vector3f(), new Vector3f() };
/*     */ 
/* 107 */   protected final Vector3f anchorPos = new Vector3f();
/*     */   protected boolean useLinearReferenceFrameA;
/*     */ 
/*     */   public Generic6DofConstraint()
/*     */   {
/* 112 */     super(TypedConstraintType.D6_CONSTRAINT_TYPE);
/* 113 */     this.useLinearReferenceFrameA = true;
/*     */   }
/*     */ 
/*     */   public Generic6DofConstraint(RigidBody rbA, RigidBody rbB, Transform frameInA, Transform frameInB, boolean useLinearReferenceFrameA) {
/* 117 */     super(TypedConstraintType.D6_CONSTRAINT_TYPE, rbA, rbB);
/* 118 */     this.frameInA.set(frameInA);
/* 119 */     this.frameInB.set(frameInB);
/* 120 */     this.useLinearReferenceFrameA = useLinearReferenceFrameA;
/*     */   }
/*     */ 
/*     */   private static float getMatrixElem(Matrix3f mat, int index) {
/* 124 */     int i = index % 3;
/* 125 */     int j = index / 3;
/* 126 */     return mat.getElement(i, j);
/*     */   }
/*     */ 
/*     */   private static boolean matrixToEulerXYZ(Matrix3f mat, Vector3f xyz)
/*     */   {
/* 138 */     if (getMatrixElem(mat, 2) < 1.0F) {
/* 139 */       if (getMatrixElem(mat, 2) > -1.0F) {
/* 140 */         xyz.x = ((float)Math.atan2(-getMatrixElem(mat, 5), getMatrixElem(mat, 8)));
/* 141 */         xyz.y = ((float)Math.asin(getMatrixElem(mat, 2)));
/* 142 */         xyz.z = ((float)Math.atan2(-getMatrixElem(mat, 1), getMatrixElem(mat, 0)));
/* 143 */         return true;
/*     */       }
/*     */ 
/* 147 */       xyz.x = (-(float)Math.atan2(getMatrixElem(mat, 3), getMatrixElem(mat, 4)));
/* 148 */       xyz.y = -1.570796F;
/* 149 */       xyz.z = 0.0F;
/* 150 */       return false;
/*     */     }
/*     */ 
/* 155 */     xyz.x = ((float)Math.atan2(getMatrixElem(mat, 3), getMatrixElem(mat, 4)));
/* 156 */     xyz.y = 1.570796F;
/* 157 */     xyz.z = 0.0F;
/*     */ 
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   protected void calculateAngleInfo()
/*     */   {
/* 167 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Matrix3f mat = localStack.get$javax$vecmath$Matrix3f();
/*     */ 
/* 169 */       Matrix3f relative_frame = localStack.get$javax$vecmath$Matrix3f();
/* 170 */       mat.set(this.calculatedTransformA.basis);
/* 171 */       MatrixUtil.invert(mat);
/* 172 */       relative_frame.mul(mat, this.calculatedTransformB.basis);
/*     */ 
/* 174 */       matrixToEulerXYZ(relative_frame, this.calculatedAxisAngleDiff);
/*     */ 
/* 191 */       Vector3f axis0 = localStack.get$javax$vecmath$Vector3f();
/* 192 */       this.calculatedTransformB.basis.getColumn(0, axis0);
/*     */ 
/* 194 */       Vector3f axis2 = localStack.get$javax$vecmath$Vector3f();
/* 195 */       this.calculatedTransformA.basis.getColumn(2, axis2);
/*     */ 
/* 197 */       this.calculatedAxis[1].cross(axis2, axis0);
/* 198 */       this.calculatedAxis[0].cross(this.calculatedAxis[1], axis2);
/* 199 */       this.calculatedAxis[2].cross(axis0, this.calculatedAxis[1]);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 211 */       .Stack tmp157_155 = localStack; tmp157_155.pop$javax$vecmath$Vector3f(); tmp157_155.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void calculateTransforms()
/*     */   {
/* 220 */     this.rbA.getCenterOfMassTransform(this.calculatedTransformA);
/* 221 */     this.calculatedTransformA.mul(this.frameInA);
/*     */ 
/* 223 */     this.rbB.getCenterOfMassTransform(this.calculatedTransformB);
/* 224 */     this.calculatedTransformB.mul(this.frameInB);
/*     */ 
/* 226 */     calculateAngleInfo();
/*     */   }
/*     */ 
/*     */   protected void buildLinearJacobian(int arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4) {
/* 230 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 231 */       mat1.transpose();
/*     */ 
/* 233 */       Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 234 */       mat2.transpose();
/*     */ 
/* 236 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 238 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 239 */       tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 241 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 242 */       tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 244 */       this.jacLinear[jacLinear_index].init(mat1, mat2, tmp1, tmp2, normalWorld, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 254 */       .Stack tmp178_176 = localStack; tmp178_176.pop$com$bulletphysics$linearmath$Transform(); tmp178_176.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   protected void buildAngularJacobian(int arg1, Vector3f arg2) {
/* 257 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 258 */       mat1.transpose();
/*     */ 
/* 260 */       Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 261 */       mat2.transpose();
/*     */ 
/* 263 */       this.jacAng[jacAngular_index].init(jointAxisW, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 268 */       .Stack tmp105_103 = localStack; tmp105_103.pop$com$bulletphysics$linearmath$Transform(); tmp105_103.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean testAngularLimitMotor(int axis_index)
/*     */   {
/* 276 */     float angle = VectorUtil.getCoord(this.calculatedAxisAngleDiff, axis_index);
/*     */ 
/* 279 */     this.angularLimits[axis_index].testLimitValue(angle);
/* 280 */     return this.angularLimits[axis_index].needApplyTorques();
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/* 286 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.linearLimits.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
/* 287 */       for (int i = 0; i < 3; i++) {
/* 288 */         this.angularLimits[i].accumulatedImpulse = 0.0F;
/*     */       }
/*     */ 
/* 292 */       calculateTransforms();
/*     */ 
/* 294 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 298 */       calcAnchorPos();
/* 299 */       Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.anchorPos);
/* 300 */       Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.anchorPos);
/*     */ 
/* 306 */       Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 308 */       for (int i = 0; i < 3; i++) {
/* 309 */         if (this.linearLimits.isLimited(i)) {
/* 310 */           if (this.useLinearReferenceFrameA) {
/* 311 */             this.calculatedTransformA.basis.getColumn(i, normalWorld);
/*     */           }
/*     */           else {
/* 314 */             this.calculatedTransformB.basis.getColumn(i, normalWorld);
/*     */           }
/*     */ 
/* 317 */           buildLinearJacobian(i, normalWorld, pivotAInW, pivotBInW);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 325 */       for (int i = 0; i < 3; i++)
/*     */       {
/* 327 */         if (testAngularLimitMotor(i)) {
/* 328 */           getAxis(i, normalWorld);
/*     */ 
/* 330 */           buildAngularJacobian(i, normalWorld);
/*     */         }
/*     */       }
/* 333 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float arg1) {
/* 337 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.timeStep = timeStep;
/*     */ 
/* 345 */       Vector3f pointInA = localStack.get$javax$vecmath$Vector3f(this.calculatedTransformA.origin);
/* 346 */       Vector3f pointInB = localStack.get$javax$vecmath$Vector3f(this.calculatedTransformB.origin);
/*     */ 
/* 349 */       Vector3f linear_axis = localStack.get$javax$vecmath$Vector3f();
/* 350 */       for (int i = 0; i < 3; i++) {
/* 351 */         if (this.linearLimits.isLimited(i)) {
/* 352 */           float jacDiagABInv = 1.0F / this.jacLinear[i].getDiagonal();
/*     */ 
/* 354 */           if (this.useLinearReferenceFrameA) {
/* 355 */             this.calculatedTransformA.basis.getColumn(i, linear_axis);
/*     */           }
/*     */           else {
/* 358 */             this.calculatedTransformB.basis.getColumn(i, linear_axis);
/*     */           }
/*     */ 
/* 361 */           this.linearLimits.solveLinearAxis(this.timeStep, jacDiagABInv, this.rbA, pointInA, this.rbB, pointInB, i, linear_axis, this.anchorPos);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 372 */       Vector3f angular_axis = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 374 */       for (i = 0; i < 3; i++)
/* 375 */         if (this.angularLimits[i].needApplyTorques())
/*     */         {
/* 377 */           getAxis(i, angular_axis);
/*     */ 
/* 379 */           float angularJacDiagABInv = 1.0F / this.jacAng[i].getDiagonal();
/*     */ 
/* 381 */           this.angularLimits[i].solveAngularLimits(this.timeStep, angular_axis, angularJacDiagABInv, this.rbA, this.rbB);
/*     */         }
/*     */       return; } finally {
/* 384 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void updateRHS(float timeStep)
/*     */   {
/*     */   }
/*     */ 
/*     */   public Vector3f getAxis(int axis_index, Vector3f out)
/*     */   {
/* 395 */     out.set(this.calculatedAxis[axis_index]);
/* 396 */     return out;
/*     */   }
/*     */ 
/*     */   public float getAngle(int axis_index)
/*     */   {
/* 404 */     return VectorUtil.getCoord(this.calculatedAxisAngleDiff, axis_index);
/*     */   }
/*     */ 
/*     */   public Transform getCalculatedTransformA(Transform out)
/*     */   {
/* 412 */     out.set(this.calculatedTransformA);
/* 413 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getCalculatedTransformB(Transform out)
/*     */   {
/* 421 */     out.set(this.calculatedTransformB);
/* 422 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getFrameOffsetA(Transform out) {
/* 426 */     out.set(this.frameInA);
/* 427 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getFrameOffsetB(Transform out) {
/* 431 */     out.set(this.frameInB);
/* 432 */     return out;
/*     */   }
/*     */ 
/*     */   public void setLinearLowerLimit(Vector3f linearLower) {
/* 436 */     this.linearLimits.lowerLimit.set(linearLower);
/*     */   }
/*     */ 
/*     */   public void setLinearUpperLimit(Vector3f linearUpper) {
/* 440 */     this.linearLimits.upperLimit.set(linearUpper);
/*     */   }
/*     */ 
/*     */   public void setAngularLowerLimit(Vector3f angularLower) {
/* 444 */     this.angularLimits[0].loLimit = angularLower.x;
/* 445 */     this.angularLimits[1].loLimit = angularLower.y;
/* 446 */     this.angularLimits[2].loLimit = angularLower.z;
/*     */   }
/*     */ 
/*     */   public void setAngularUpperLimit(Vector3f angularUpper) {
/* 450 */     this.angularLimits[0].hiLimit = angularUpper.x;
/* 451 */     this.angularLimits[1].hiLimit = angularUpper.y;
/* 452 */     this.angularLimits[2].hiLimit = angularUpper.z;
/*     */   }
/*     */ 
/*     */   public RotationalLimitMotor getRotationalLimitMotor(int index)
/*     */   {
/* 459 */     return this.angularLimits[index];
/*     */   }
/*     */ 
/*     */   public TranslationalLimitMotor getTranslationalLimitMotor()
/*     */   {
/* 466 */     return this.linearLimits;
/*     */   }
/*     */ 
/*     */   public void setLimit(int axis, float lo, float hi)
/*     */   {
/* 473 */     if (axis < 3) {
/* 474 */       VectorUtil.setCoord(this.linearLimits.lowerLimit, axis, lo);
/* 475 */       VectorUtil.setCoord(this.linearLimits.upperLimit, axis, hi);
/*     */     }
/*     */     else {
/* 478 */       this.angularLimits[(axis - 3)].loLimit = lo;
/* 479 */       this.angularLimits[(axis - 3)].hiLimit = hi;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isLimited(int limitIndex)
/*     */   {
/* 491 */     if (limitIndex < 3) {
/* 492 */       return this.linearLimits.isLimited(limitIndex);
/*     */     }
/*     */ 
/* 495 */     return this.angularLimits[(limitIndex - 3)].isLimited();
/*     */   }
/*     */ 
/*     */   public void calcAnchorPos()
/*     */   {
/* 500 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); float imA = this.rbA.getInvMass();
/* 501 */       float imB = this.rbB.getInvMass();
/*     */       float weight;
/*     */       float weight;
/* 503 */       if (imB == 0.0F) {
/* 504 */         weight = 1.0F;
/*     */       }
/*     */       else {
/* 507 */         weight = imA / (imA + imB); } Vector3f pA = this.calculatedTransformA.origin;
/* 510 */       Vector3f pB = this.calculatedTransformB.origin;
/*     */ 
/* 512 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 513 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 515 */       tmp1.scale(weight, pA);
/* 516 */       tmp2.scale(1.0F - weight, pB);
/* 517 */       this.anchorPos.add(tmp1, tmp2);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.Generic6DofConstraint
 * JD-Core Version:    0.6.2
 */