/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.QuaternionUtil;
/*     */ import com.bulletphysics.linearmath.ScalarUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ConeTwistConstraint extends TypedConstraint
/*     */ {
/*  46 */   private JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
/*     */ 
/*  48 */   private final Transform rbAFrame = new Transform();
/*  49 */   private final Transform rbBFrame = new Transform();
/*     */   private float limitSoftness;
/*     */   private float biasFactor;
/*     */   private float relaxationFactor;
/*     */   private float swingSpan1;
/*     */   private float swingSpan2;
/*     */   private float twistSpan;
/*  59 */   private final Vector3f swingAxis = new Vector3f();
/*  60 */   private final Vector3f twistAxis = new Vector3f();
/*     */   private float kSwing;
/*     */   private float kTwist;
/*     */   private float twistLimitSign;
/*     */   private float swingCorrection;
/*     */   private float twistCorrection;
/*     */   private float accSwingLimitImpulse;
/*     */   private float accTwistLimitImpulse;
/*  72 */   private boolean angularOnly = false;
/*     */   private boolean solveTwistLimit;
/*     */   private boolean solveSwingLimit;
/*     */ 
/*     */   public ConeTwistConstraint()
/*     */   {
/*  77 */     super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE);
/*     */   }
/*     */ 
/*     */   public ConeTwistConstraint(RigidBody rbA, RigidBody rbB, Transform rbAFrame, Transform rbBFrame) {
/*  81 */     super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE, rbA, rbB);
/*  82 */     this.rbAFrame.set(rbAFrame);
/*  83 */     this.rbBFrame.set(rbBFrame);
/*     */ 
/*  85 */     this.swingSpan1 = 1.0E+030F;
/*  86 */     this.swingSpan2 = 1.0E+030F;
/*  87 */     this.twistSpan = 1.0E+030F;
/*  88 */     this.biasFactor = 0.3F;
/*  89 */     this.relaxationFactor = 1.0F;
/*     */ 
/*  91 */     this.solveTwistLimit = false;
/*  92 */     this.solveSwingLimit = false;
/*     */   }
/*     */ 
/*     */   public ConeTwistConstraint(RigidBody rbA, Transform rbAFrame) {
/*  96 */     super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE, rbA);
/*  97 */     this.rbAFrame.set(rbAFrame);
/*  98 */     this.rbBFrame.set(this.rbAFrame);
/*     */ 
/* 100 */     this.swingSpan1 = 1.0E+030F;
/* 101 */     this.swingSpan2 = 1.0E+030F;
/* 102 */     this.twistSpan = 1.0E+030F;
/* 103 */     this.biasFactor = 0.3F;
/* 104 */     this.relaxationFactor = 1.0F;
/*     */ 
/* 106 */     this.solveTwistLimit = false;
/* 107 */     this.solveSwingLimit = false;
/*     */   }
/*     */ 
/*     */   public void buildJacobian()
/*     */   {
/* 112 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Quat4f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 113 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 114 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 116 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 118 */       this.appliedImpulse = 0.0F;
/*     */ 
/* 121 */       this.swingCorrection = 0.0F;
/* 122 */       this.twistLimitSign = 0.0F;
/* 123 */       this.solveTwistLimit = false;
/* 124 */       this.solveSwingLimit = false;
/* 125 */       this.accTwistLimitImpulse = 0.0F;
/* 126 */       this.accSwingLimitImpulse = 0.0F;
/*     */ 
/* 128 */       if (!this.angularOnly) {
/* 129 */         Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 130 */         this.rbA.getCenterOfMassTransform(tmpTrans).transform(pivotAInW);
/*     */ 
/* 132 */         Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 133 */         this.rbB.getCenterOfMassTransform(tmpTrans).transform(pivotBInW);
/*     */ 
/* 135 */         Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
/* 136 */         relPos.sub(pivotBInW, pivotAInW);
/*     */ 
/* 139 */         Vector3f[] normal = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 140 */         if (relPos.lengthSquared() > 1.192093E-007F) {
/* 141 */           normal[0].normalize(relPos);
/*     */         }
/*     */         else {
/* 144 */           normal[0].set(1.0F, 0.0F, 0.0F);
/*     */         }
/*     */ 
/* 147 */         TransformUtil.planeSpace1(normal[0], normal[1], normal[2]);
/*     */ 
/* 149 */         for (int i = 0; i < 3; i++) {
/* 150 */           Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 151 */           mat1.transpose();
/*     */ 
/* 153 */           Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
/* 154 */           mat2.transpose();
/*     */ 
/* 156 */           tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmp));
/* 157 */           tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmp));
/*     */ 
/* 159 */           this.jac[i].init(mat1, mat2, tmp1, tmp2, normal[i], this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 172 */       Vector3f b1Axis1 = localStack.get$javax$vecmath$Vector3f(); Vector3f b1Axis2 = localStack.get$javax$vecmath$Vector3f(); Vector3f b1Axis3 = localStack.get$javax$vecmath$Vector3f();
/* 173 */       Vector3f b2Axis1 = localStack.get$javax$vecmath$Vector3f(); Vector3f b2Axis2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 175 */       this.rbAFrame.basis.getColumn(0, b1Axis1);
/* 176 */       getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(b1Axis1);
/*     */ 
/* 178 */       this.rbBFrame.basis.getColumn(0, b2Axis1);
/* 179 */       getRigidBodyB().getCenterOfMassTransform(tmpTrans).basis.transform(b2Axis1);
/*     */ 
/* 181 */       float swing1 = 0.0F; float swing2 = 0.0F;
/*     */ 
/* 183 */       float swx = 0.0F; float swy = 0.0F;
/* 184 */       float thresh = 10.0F;
/*     */ 
/* 188 */       if (this.swingSpan1 >= 0.05F) {
/* 189 */         this.rbAFrame.basis.getColumn(1, b1Axis2);
/* 190 */         getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(b1Axis2);
/*     */ 
/* 192 */         swx = b2Axis1.dot(b1Axis1);
/* 193 */         swy = b2Axis1.dot(b1Axis2);
/* 194 */         swing1 = ScalarUtil.atan2Fast(swy, swx);
/* 195 */         float fact = (swy * swy + swx * swx) * thresh * thresh;
/* 196 */         fact /= (fact + 1.0F);
/* 197 */         swing1 *= fact;
/*     */       }
/*     */ 
/* 200 */       if (this.swingSpan2 >= 0.05F) {
/* 201 */         this.rbAFrame.basis.getColumn(2, b1Axis3);
/* 202 */         getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(b1Axis3);
/*     */ 
/* 204 */         swx = b2Axis1.dot(b1Axis1);
/* 205 */         swy = b2Axis1.dot(b1Axis3);
/* 206 */         swing2 = ScalarUtil.atan2Fast(swy, swx);
/* 207 */         float fact = (swy * swy + swx * swx) * thresh * thresh;
/* 208 */         fact /= (fact + 1.0F);
/* 209 */         swing2 *= fact;
/*     */       }
/*     */ 
/* 212 */       float RMaxAngle1Sq = 1.0F / (this.swingSpan1 * this.swingSpan1);
/* 213 */       float RMaxAngle2Sq = 1.0F / (this.swingSpan2 * this.swingSpan2);
/* 214 */       float EllipseAngle = Math.abs(swing1 * swing1) * RMaxAngle1Sq + Math.abs(swing2 * swing2) * RMaxAngle2Sq;
/*     */ 
/* 216 */       if (EllipseAngle > 1.0F) {
/* 217 */         this.swingCorrection = (EllipseAngle - 1.0F);
/* 218 */         this.solveSwingLimit = true;
/*     */ 
/* 221 */         tmp1.scale(b2Axis1.dot(b1Axis2), b1Axis2);
/* 222 */         tmp2.scale(b2Axis1.dot(b1Axis3), b1Axis3);
/* 223 */         tmp.add(tmp1, tmp2);
/* 224 */         this.swingAxis.cross(b2Axis1, tmp);
/* 225 */         this.swingAxis.normalize();
/*     */ 
/* 227 */         float swingAxisSign = b2Axis1.dot(b1Axis1) >= 0.0F ? 1.0F : -1.0F;
/* 228 */         this.swingAxis.scale(swingAxisSign);
/*     */ 
/* 230 */         this.kSwing = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.swingAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.swingAxis)));
/*     */       }
/*     */ 
/* 236 */       if (this.twistSpan >= 0.0F)
/*     */       {
/* 238 */         this.rbBFrame.basis.getColumn(1, b2Axis2);
/* 239 */         getRigidBodyB().getCenterOfMassTransform(tmpTrans).basis.transform(b2Axis2);
/*     */ 
/* 241 */         Quat4f rotationArc = QuaternionUtil.shortestArcQuat(b2Axis1, b1Axis1, localStack.get$javax$vecmath$Quat4f());
/* 242 */         Vector3f TwistRef = QuaternionUtil.quatRotate(rotationArc, b2Axis2, localStack.get$javax$vecmath$Vector3f());
/* 243 */         float twist = ScalarUtil.atan2Fast(TwistRef.dot(b1Axis3), TwistRef.dot(b1Axis2));
/*     */ 
/* 245 */         float lockedFreeFactor = this.twistSpan > 0.05F ? this.limitSoftness : 0.0F;
/* 246 */         if (twist <= -this.twistSpan * lockedFreeFactor) {
/* 247 */           this.twistCorrection = (-(twist + this.twistSpan));
/* 248 */           this.solveTwistLimit = true;
/*     */ 
/* 250 */           this.twistAxis.add(b2Axis1, b1Axis1);
/* 251 */           this.twistAxis.scale(0.5F);
/* 252 */           this.twistAxis.normalize();
/* 253 */           this.twistAxis.scale(-1.0F);
/*     */ 
/* 255 */           this.kTwist = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.twistAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.twistAxis)));
/*     */         }
/* 259 */         else if (twist > this.twistSpan * lockedFreeFactor) {
/* 260 */           this.twistCorrection = (twist - this.twistSpan);
/* 261 */           this.solveTwistLimit = true;
/*     */ 
/* 263 */           this.twistAxis.add(b2Axis1, b1Axis1);
/* 264 */           this.twistAxis.scale(0.5F);
/* 265 */           this.twistAxis.normalize();
/*     */ 
/* 267 */           this.kTwist = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.twistAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.twistAxis)));
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 271 */       .Stack tmp1184_1182 = localStack; tmp1184_1182.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp1188_1184 = tmp1184_1182; tmp1188_1184.pop$javax$vecmath$Vector3f(); tmp1188_1184.pop$javax$vecmath$Quat4f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void solveConstraint(float arg1) {
/* 275 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 276 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 278 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/* 279 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 281 */       Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
/* 282 */       this.rbA.getCenterOfMassTransform(tmpTrans).transform(pivotAInW);
/*     */ 
/* 284 */       Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
/* 285 */       this.rbB.getCenterOfMassTransform(tmpTrans).transform(pivotBInW);
/*     */ 
/* 287 */       float tau = 0.3F;
/*     */ 
/* 290 */       if (!this.angularOnly) {
/* 291 */         Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/* 292 */         rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 294 */         Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/* 295 */         rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 297 */         Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 298 */         Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 299 */         Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 300 */         vel.sub(vel1, vel2);
/*     */ 
/* 302 */         for (int i = 0; i < 3; i++) {
/* 303 */           Vector3f normal = this.jac[i].linearJointAxis;
/* 304 */           float jacDiagABInv = 1.0F / this.jac[i].getDiagonal();
/*     */ 
/* 307 */           float rel_vel = normal.dot(vel);
/*     */ 
/* 309 */           tmp.sub(pivotAInW, pivotBInW);
/* 310 */           float depth = -tmp.dot(normal);
/* 311 */           float impulse = depth * tau / timeStep * jacDiagABInv - rel_vel * jacDiagABInv;
/* 312 */           this.appliedImpulse += impulse;
/* 313 */           Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 314 */           impulse_vector.scale(impulse, normal);
/*     */ 
/* 316 */           tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
/* 317 */           this.rbA.applyImpulse(impulse_vector, tmp);
/*     */ 
/* 319 */           tmp.negate(impulse_vector);
/* 320 */           tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
/* 321 */           this.rbB.applyImpulse(tmp, tmp2);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 327 */       Vector3f angVelA = getRigidBodyA().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 328 */       Vector3f angVelB = getRigidBodyB().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 331 */       if (this.solveSwingLimit) {
/* 332 */         tmp.sub(angVelB, angVelA);
/* 333 */         float amplitude = tmp.dot(this.swingAxis) * this.relaxationFactor * this.relaxationFactor + this.swingCorrection * (1.0F / timeStep) * this.biasFactor;
/* 334 */         float impulseMag = amplitude * this.kSwing;
/*     */ 
/* 337 */         float temp = this.accSwingLimitImpulse;
/* 338 */         this.accSwingLimitImpulse = Math.max(this.accSwingLimitImpulse + impulseMag, 0.0F);
/* 339 */         impulseMag = this.accSwingLimitImpulse - temp;
/*     */ 
/* 341 */         Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 342 */         impulse.scale(impulseMag, this.swingAxis);
/*     */ 
/* 344 */         this.rbA.applyTorqueImpulse(impulse);
/*     */ 
/* 346 */         tmp.negate(impulse);
/* 347 */         this.rbB.applyTorqueImpulse(tmp);
/*     */       }
/*     */ 
/* 351 */       if (this.solveTwistLimit) {
/* 352 */         tmp.sub(angVelB, angVelA);
/* 353 */         float amplitude = tmp.dot(this.twistAxis) * this.relaxationFactor * this.relaxationFactor + this.twistCorrection * (1.0F / timeStep) * this.biasFactor;
/* 354 */         float impulseMag = amplitude * this.kTwist;
/*     */ 
/* 357 */         float temp = this.accTwistLimitImpulse;
/* 358 */         this.accTwistLimitImpulse = Math.max(this.accTwistLimitImpulse + impulseMag, 0.0F);
/* 359 */         impulseMag = this.accTwistLimitImpulse - temp;
/*     */ 
/* 361 */         Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
/* 362 */         impulse.scale(impulseMag, this.twistAxis);
/*     */ 
/* 364 */         this.rbA.applyTorqueImpulse(impulse);
/*     */ 
/* 366 */         tmp.negate(impulse);
/* 367 */         this.rbB.applyTorqueImpulse(tmp);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 370 */       .Stack tmp668_666 = localStack; tmp668_666.pop$com$bulletphysics$linearmath$Transform(); tmp668_666.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void updateRHS(float timeStep) {
/*     */   }
/*     */ 
/*     */   public void setAngularOnly(boolean angularOnly) {
/* 376 */     this.angularOnly = angularOnly;
/*     */   }
/*     */ 
/*     */   public void setLimit(float _swingSpan1, float _swingSpan2, float _twistSpan) {
/* 380 */     setLimit(_swingSpan1, _swingSpan2, _twistSpan, 0.8F, 0.3F, 1.0F);
/*     */   }
/*     */ 
/*     */   public void setLimit(float _swingSpan1, float _swingSpan2, float _twistSpan, float _softness, float _biasFactor, float _relaxationFactor) {
/* 384 */     this.swingSpan1 = _swingSpan1;
/* 385 */     this.swingSpan2 = _swingSpan2;
/* 386 */     this.twistSpan = _twistSpan;
/*     */ 
/* 388 */     this.limitSoftness = _softness;
/* 389 */     this.biasFactor = _biasFactor;
/* 390 */     this.relaxationFactor = _relaxationFactor;
/*     */   }
/*     */ 
/*     */   public Transform getAFrame(Transform out) {
/* 394 */     out.set(this.rbAFrame);
/* 395 */     return out;
/*     */   }
/*     */ 
/*     */   public Transform getBFrame(Transform out) {
/* 399 */     out.set(this.rbBFrame);
/* 400 */     return out;
/*     */   }
/*     */ 
/*     */   public boolean getSolveTwistLimit() {
/* 404 */     return this.solveTwistLimit;
/*     */   }
/*     */ 
/*     */   public boolean getSolveSwingLimit() {
/* 408 */     return this.solveTwistLimit;
/*     */   }
/*     */ 
/*     */   public float getTwistLimitSign() {
/* 412 */     return this.twistLimitSign;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConeTwistConstraint
 * JD-Core Version:    0.6.2
 */