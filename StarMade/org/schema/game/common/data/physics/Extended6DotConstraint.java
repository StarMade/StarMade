/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.dynamics.constraintsolver.Generic6DofConstraint;
/*     */ import com.bulletphysics.dynamics.constraintsolver.JacobianEntry;
/*     */ import com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TranslationalLimitMotor;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ public class Extended6DotConstraint extends Generic6DofConstraint
/*     */ {
/*     */   private StateInterface state;
/*  65 */   protected final RotationalLimitMotorAbsVelocity[] angularLimits = { new RotationalLimitMotorAbsVelocity(), new RotationalLimitMotorAbsVelocity(), new RotationalLimitMotorAbsVelocity() };
/*     */ 
/*  67 */   public final Vector3f linearJointAxis = new Vector3f();
/*     */ 
/*  69 */   public final Vector3f aJ = new Vector3f();
/*  70 */   public final Vector3f bJ = new Vector3f();
/*  71 */   public final Vector3f m_0MinvJt = new Vector3f();
/*  72 */   public final Vector3f m_1MinvJt = new Vector3f();
/*     */ 
/*     */   private static float getMatrixElem(Matrix3f paramMatrix3f, int paramInt)
/*     */   {
/*  20 */     int i = paramInt % 3;
/*  21 */     paramInt /= 3;
/*  22 */     return paramMatrix3f.getElement(i, paramInt);
/*     */   }
/*     */ 
/*     */   private static boolean matrixToEulerXYZ(Matrix3f paramMatrix3f, Vector3f paramVector3f)
/*     */   {
/*  35 */     if (getMatrixElem(paramMatrix3f, 2) < 1.0F) {
/*  36 */       if (getMatrixElem(paramMatrix3f, 2) > -1.0F) {
/*  37 */         paramVector3f.x = ((float)Math.atan2(-getMatrixElem(paramMatrix3f, 5), getMatrixElem(paramMatrix3f, 8)));
/*  38 */         paramVector3f.y = ((float)Math.asin(getMatrixElem(paramMatrix3f, 2)));
/*  39 */         paramVector3f.z = ((float)Math.atan2(-getMatrixElem(paramMatrix3f, 1), getMatrixElem(paramMatrix3f, 0)));
/*  40 */         return true;
/*     */       }
/*     */ 
/*  43 */       System.err.println("WARNING.  Not unique.  XA - ZA = -atan2(r10,r11)");
/*     */ 
/*  45 */       paramVector3f.x = (-(float)Math.atan2(getMatrixElem(paramMatrix3f, 3), getMatrixElem(paramMatrix3f, 4)));
/*  46 */       paramVector3f.y = -1.570796F;
/*  47 */       paramVector3f.z = 0.0F;
/*  48 */       return false;
/*     */     }
/*     */ 
/*  52 */     System.err.println("WARNING.  Not unique.  XAngle + ZAngle = atan2(r10,r11)");
/*     */ 
/*  54 */     paramVector3f.x = ((float)Math.atan2(getMatrixElem(paramMatrix3f, 3), getMatrixElem(paramMatrix3f, 4)));
/*  55 */     paramVector3f.y = 1.570796F;
/*  56 */     paramVector3f.z = 0.0F;
/*     */ 
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public Extended6DotConstraint(StateInterface paramStateInterface, RigidBody paramRigidBody1, RigidBody paramRigidBody2, Transform paramTransform1, Transform paramTransform2, boolean paramBoolean)
/*     */   {
/*  76 */     super(paramRigidBody1, paramRigidBody2, paramTransform1, paramTransform2, paramBoolean);
/*  77 */     this.state = paramStateInterface;
/*     */   }
/*     */ 
/*     */   protected void buildAngularJacobian(int paramInt, Vector3f paramVector3f)
/*     */   {
/*     */     Matrix3f localMatrix3f1;
/*  87 */     (
/*  88 */       localMatrix3f1 = this.rbA.getCenterOfMassTransform(new Transform()).basis)
/*  88 */       .transpose();
/*     */     Matrix3f localMatrix3f2;
/*  90 */     (
/*  91 */       localMatrix3f2 = this.rbB.getCenterOfMassTransform(new Transform()).basis)
/*  91 */       .transpose();
/*  92 */     if (!checkJacobian(paramVector3f, localMatrix3f1, localMatrix3f2, this.rbA.getInvInertiaDiagLocal(new Vector3f()), this.rbB.getInvInertiaDiagLocal(new Vector3f())))
/*     */     {
/*  97 */       System.err.println("Exception: Jacobian Entry init failed!");
/*  98 */       return;
/*     */     }
/*     */ 
/* 102 */     super.buildAngularJacobian(paramInt, paramVector3f);
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/* 107 */     this.linearLimits.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
/* 108 */     for (int i = 0; i < 3; i++) {
/* 109 */       this.angularLimits[i].accumulatedImpulse = 0.0F;
/*     */     }
/*     */ 
/* 113 */     calculateTransforms();
/*     */ 
/* 115 */     new Vector3f();
/*     */ 
/* 118 */     calcAnchorPos();
/* 119 */     Vector3f localVector3f1 = new Vector3f(this.anchorPos);
/* 120 */     Vector3f localVector3f2 = new Vector3f(this.anchorPos);
/*     */ 
/* 126 */     Vector3f localVector3f3 = new Vector3f();
/*     */ 
/* 128 */     for (int j = 0; j < 3; j++) {
/* 129 */       if (this.linearLimits.isLimited(j)) {
/* 130 */         if (this.useLinearReferenceFrameA) {
/* 131 */           this.calculatedTransformA.basis.getColumn(j, localVector3f3);
/*     */         }
/*     */         else {
/* 134 */           this.calculatedTransformB.basis.getColumn(j, localVector3f3);
/*     */         }
/*     */ 
/* 137 */         buildLinearJacobian(j, localVector3f3, localVector3f1, localVector3f2);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 145 */     for (j = 0; j < 3; j++)
/*     */     {
/* 147 */       if (testAngularLimitMotor(j)) {
/* 148 */         getAxis(j, localVector3f3);
/*     */ 
/* 150 */         buildAngularJacobian(j, localVector3f3);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void calculateAngleInfo()
/*     */   {
/* 159 */     Object localObject1 = new Matrix3f();
/*     */ 
/* 161 */     Object localObject2 = new Matrix3f();
/* 162 */     ((Matrix3f)localObject1).set(this.calculatedTransformA.basis);
/* 163 */     MatrixUtil.invert((Matrix3f)localObject1);
/* 164 */     ((Matrix3f)localObject2).mul((Matrix3f)localObject1, this.calculatedTransformB.basis);
/*     */ 
/* 166 */     matrixToEulerXYZ((Matrix3f)localObject2, this.calculatedAxisAngleDiff);
/*     */ 
/* 185 */     localObject1 = new Vector3f();
/* 186 */     this.calculatedTransformB.basis.getColumn(0, (Vector3f)localObject1);
/*     */ 
/* 188 */     localObject2 = new Vector3f();
/* 189 */     this.calculatedTransformA.basis.getColumn(2, (Vector3f)localObject2);
/*     */ 
/* 191 */     this.calculatedAxis[1].cross((Vector3f)localObject2, (Vector3f)localObject1);
/* 192 */     this.calculatedAxis[0].cross(this.calculatedAxis[1], (Vector3f)localObject2);
/* 193 */     this.calculatedAxis[2].cross((Vector3f)localObject1, this.calculatedAxis[1]);
/*     */   }
/*     */ 
/*     */   public boolean checkJacobian(Vector3f paramVector3f1, Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2, Vector3f paramVector3f2, Vector3f paramVector3f3)
/*     */   {
/* 212 */     this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 214 */     this.aJ.set(paramVector3f1);
/* 215 */     paramMatrix3f1.transform(this.aJ);
/*     */ 
/* 217 */     this.bJ.set(paramVector3f1);
/* 218 */     this.bJ.negate();
/* 219 */     paramMatrix3f2.transform(this.bJ);
/*     */ 
/* 221 */     VectorUtil.mul(this.m_0MinvJt, paramVector3f2, this.aJ);
/* 222 */     VectorUtil.mul(this.m_1MinvJt, paramVector3f3, this.bJ);
/*     */ 
/* 225 */     return this.m_0MinvJt.dot(this.aJ) + this.m_1MinvJt.dot(this.bJ) > 
/* 225 */       0.0F;
/*     */   }
/*     */ 
/*     */   public RotationalLimitMotor getRotationalLimitMotor(int paramInt)
/*     */   {
/* 236 */     return this.angularLimits[paramInt];
/*     */   }
/*     */ 
/*     */   public boolean isLimited(int paramInt)
/*     */   {
/* 247 */     if (paramInt < 3) {
/* 248 */       return this.linearLimits.isLimited(paramInt);
/*     */     }
/*     */ 
/* 251 */     return this.angularLimits[(paramInt - 3)].isLimited();
/*     */   }
/*     */ 
/*     */   public void matrixToEuler(Matrix3f paramMatrix3f, Vector3f paramVector3f)
/*     */   {
/*     */     float f1;
/*     */     float f3;
/* 264 */     if (FastMath.a(f2 = (float)Math.cos(f1 = (float)-Math.asin(paramMatrix3f.m02))) > 
/* 264 */       0.005D)
/*     */     {
/* 270 */       f3 = paramMatrix3f.m22 / f2;
/*     */ 
/* 273 */       f4 = (float)Math.atan2(-paramMatrix3f.m12 / f2, 
/* 273 */         f3);
/*     */ 
/* 275 */       f3 = paramMatrix3f.m00 / f2;
/*     */ 
/* 278 */       f2 = (float)Math.atan2(-paramMatrix3f.m01 / f2, 
/* 278 */         f3);
/*     */     }
/*     */     else
/*     */     {
/* 282 */       f4 = 0.0F;
/*     */ 
/* 284 */       f3 = paramMatrix3f.m11;
/*     */ 
/* 287 */       f2 = (float)Math.atan2(paramMatrix3f.m10, 
/* 287 */         f3);
/*     */     }
/*     */ 
/* 290 */     float f4 = FastMath.a(f4, 0.0F, 6.283186F);
/* 291 */     paramMatrix3f = FastMath.a(f1, 0.0F, 6.283186F);
/* 292 */     float f2 = FastMath.a(f2, 0.0F, 6.283186F);
/*     */ 
/* 294 */     paramVector3f.x = f4;
/* 295 */     paramVector3f.y = paramMatrix3f;
/* 296 */     paramVector3f.z = f2;
/*     */ 
/* 298 */     System.err.println("Euler: " + paramVector3f);
/*     */   }
/*     */ 
/*     */   public void setAngularLowerLimit(Vector3f paramVector3f) {
/* 302 */     this.angularLimits[0].loLimit = paramVector3f.x;
/* 303 */     this.angularLimits[1].loLimit = paramVector3f.y;
/* 304 */     this.angularLimits[2].loLimit = paramVector3f.z;
/*     */   }
/*     */ 
/*     */   public void setAngularUpperLimit(Vector3f paramVector3f)
/*     */   {
/* 309 */     this.angularLimits[0].hiLimit = paramVector3f.x;
/* 310 */     this.angularLimits[1].hiLimit = paramVector3f.y;
/* 311 */     this.angularLimits[2].hiLimit = paramVector3f.z;
/*     */   }
/*     */ 
/*     */   public void setLimit(int paramInt, float paramFloat1, float paramFloat2)
/*     */   {
/* 319 */     if (paramInt < 3) {
/* 320 */       VectorUtil.setCoord(this.linearLimits.lowerLimit, paramInt, paramFloat1);
/* 321 */       VectorUtil.setCoord(this.linearLimits.upperLimit, paramInt, paramFloat2); return;
/*     */     }
/*     */ 
/* 324 */     this.angularLimits[(paramInt - 3)].loLimit = paramFloat1;
/* 325 */     this.angularLimits[(paramInt - 3)].hiLimit = paramFloat2;
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float paramFloat)
/*     */   {
/* 333 */     this.timeStep = paramFloat;
/*     */ 
/* 341 */     Vector3f localVector3f1 = new Vector3f(this.calculatedTransformA.origin);
/* 342 */     Vector3f localVector3f2 = new Vector3f(this.calculatedTransformB.origin);
/*     */ 
/* 345 */     Vector3f localVector3f3 = new Vector3f();
/* 346 */     for (paramFloat = 0; paramFloat < 3; paramFloat++) {
/* 347 */       if (this.linearLimits.isLimited(paramFloat)) {
/* 348 */         float f2 = 1.0F / this.jacLinear[paramFloat].getDiagonal();
/*     */ 
/* 350 */         if (this.useLinearReferenceFrameA) {
/* 351 */           this.calculatedTransformA.basis.getColumn(paramFloat, localVector3f3);
/*     */         }
/*     */         else {
/* 354 */           this.calculatedTransformB.basis.getColumn(paramFloat, localVector3f3);
/*     */         }
/*     */ 
/* 357 */         this.linearLimits.solveLinearAxis(this.timeStep, f2, this.rbA, localVector3f1, this.rbB, localVector3f2, paramFloat, localVector3f3, this.anchorPos);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 368 */     localVector3f1 = new Vector3f();
/*     */ 
/* 370 */     for (paramFloat = 0; paramFloat < 3; paramFloat++)
/* 371 */       if (this.angularLimits[paramFloat].needApplyTorques())
/*     */       {
/* 373 */         getAxis(paramFloat, localVector3f1);
/*     */         float f1;
/* 375 */         if (this.jacAng[paramFloat].getDiagonal() == 0.0F)
/* 376 */           f1 = 0.1F;
/*     */         else {
/* 378 */           f1 = 1.0F / this.jacAng[paramFloat].getDiagonal();
/*     */         }
/* 380 */         this.angularLimits[paramFloat].solveAngularLimits(this.timeStep, localVector3f1, f1, this.rbA, this.rbB);
/*     */       }
/*     */   }
/*     */ 
/*     */   public boolean testAngularLimitMotor(int paramInt)
/*     */   {
/* 397 */     float f = VectorUtil.getCoord(this.calculatedAxisAngleDiff, paramInt);
/*     */ 
/* 400 */     this.angularLimits[paramInt].testLimitValue(f);
/* 401 */     return this.angularLimits[paramInt].needApplyTorques();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.Extended6DotConstraint
 * JD-Core Version:    0.6.2
 */