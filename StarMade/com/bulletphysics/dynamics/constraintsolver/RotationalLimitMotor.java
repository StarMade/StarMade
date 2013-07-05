/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class RotationalLimitMotor
/*     */ {
/*     */   public float loLimit;
/*     */   public float hiLimit;
/*     */   public float targetVelocity;
/*     */   public float maxMotorForce;
/*     */   public float maxLimitForce;
/*     */   public float damping;
/*     */   public float limitSoftness;
/*     */   public float ERP;
/*     */   public float bounce;
/*     */   public boolean enableMotor;
/*     */   public float currentLimitError;
/*     */   public int currentLimit;
/*     */   public float accumulatedImpulse;
/*     */ 
/*     */   public RotationalLimitMotor()
/*     */   {
/*  64 */     this.accumulatedImpulse = 0.0F;
/*  65 */     this.targetVelocity = 0.0F;
/*  66 */     this.maxMotorForce = 0.1F;
/*  67 */     this.maxLimitForce = 300.0F;
/*  68 */     this.loLimit = -3.402824E+038F;
/*  69 */     this.hiLimit = 3.4028235E+38F;
/*  70 */     this.ERP = 0.5F;
/*  71 */     this.bounce = 0.0F;
/*  72 */     this.damping = 1.0F;
/*  73 */     this.limitSoftness = 0.5F;
/*  74 */     this.currentLimit = 0;
/*  75 */     this.currentLimitError = 0.0F;
/*  76 */     this.enableMotor = false;
/*     */   }
/*     */ 
/*     */   public RotationalLimitMotor(RotationalLimitMotor limot) {
/*  80 */     this.targetVelocity = limot.targetVelocity;
/*  81 */     this.maxMotorForce = limot.maxMotorForce;
/*  82 */     this.limitSoftness = limot.limitSoftness;
/*  83 */     this.loLimit = limot.loLimit;
/*  84 */     this.hiLimit = limot.hiLimit;
/*  85 */     this.ERP = limot.ERP;
/*  86 */     this.bounce = limot.bounce;
/*  87 */     this.currentLimit = limot.currentLimit;
/*  88 */     this.currentLimitError = limot.currentLimitError;
/*  89 */     this.enableMotor = limot.enableMotor;
/*     */   }
/*     */ 
/*     */   public boolean isLimited()
/*     */   {
/*  97 */     if (this.loLimit >= this.hiLimit) return false;
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean needApplyTorques()
/*     */   {
/* 106 */     if ((this.currentLimit == 0) && (!this.enableMotor)) return false;
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   public int testLimitValue(float test_value)
/*     */   {
/* 114 */     if (this.loLimit > this.hiLimit) {
/* 115 */       this.currentLimit = 0;
/* 116 */       return 0;
/*     */     }
/*     */ 
/* 119 */     if (test_value < this.loLimit) {
/* 120 */       this.currentLimit = 1;
/* 121 */       this.currentLimitError = (test_value - this.loLimit);
/* 122 */       return 1;
/*     */     }
/* 124 */     if (test_value > this.hiLimit) {
/* 125 */       this.currentLimit = 2;
/* 126 */       this.currentLimitError = (test_value - this.hiLimit);
/* 127 */       return 2;
/*     */     }
/*     */ 
/* 130 */     this.currentLimit = 0;
/* 131 */     return 0;
/*     */   }
/*     */ 
/*     */   public float solveAngularLimits(float arg1, Vector3f arg2, float arg3, RigidBody arg4, RigidBody arg5)
/*     */   {
/* 139 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (!needApplyTorques()) {
/* 140 */         return 0.0F;
/*     */       }
/*     */ 
/* 143 */       float target_velocity = this.targetVelocity;
/* 144 */       float maxMotorForce = this.maxMotorForce;
/*     */ 
/* 147 */       if (this.currentLimit != 0) {
/* 148 */         target_velocity = -this.ERP * this.currentLimitError / timeStep;
/* 149 */         maxMotorForce = this.maxLimitForce;
/*     */       }
/*     */ 
/* 152 */       maxMotorForce *= timeStep;
/*     */ 
/* 155 */       Vector3f vel_diff = body0.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
/* 156 */       if (body1 != null) {
/* 157 */         vel_diff.sub(body1.getAngularVelocity(localStack.get$javax$vecmath$Vector3f()));
/*     */       }
/*     */ 
/* 160 */       float rel_vel = axis.dot(vel_diff);
/*     */ 
/* 163 */       float motor_relvel = this.limitSoftness * (target_velocity - this.damping * rel_vel);
/*     */ 
/* 165 */       if ((motor_relvel < 1.192093E-007F) && (motor_relvel > -1.192093E-007F)) {
/* 166 */         return 0.0F;
/*     */       }
/*     */ 
/* 170 */       float unclippedMotorImpulse = (1.0F + this.bounce) * motor_relvel * jacDiagABInv;
/*     */       float clippedMotorImpulse;
/* 176 */       if (unclippedMotorImpulse > 0.0F) {
/* 177 */         clippedMotorImpulse = unclippedMotorImpulse > maxMotorForce ? maxMotorForce : unclippedMotorImpulse;
/*     */       }
/*     */       else {
/* 180 */         clippedMotorImpulse = unclippedMotorImpulse < -maxMotorForce ? -maxMotorForce : unclippedMotorImpulse;
/*     */       }
/*     */ 
/* 184 */       float lo = -1.0E+030F;
/* 185 */       float hi = 1.0E+030F;
/*     */ 
/* 187 */       float oldaccumImpulse = this.accumulatedImpulse;
/* 188 */       float sum = oldaccumImpulse + clippedMotorImpulse;
/* 189 */       this.accumulatedImpulse = (sum < lo ? 0.0F : sum > hi ? 0.0F : sum);
/*     */ 
/* 191 */       float clippedMotorImpulse = this.accumulatedImpulse - oldaccumImpulse;
/*     */ 
/* 193 */       Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
/* 194 */       motorImp.scale(clippedMotorImpulse, axis);
/*     */ 
/* 196 */       body0.applyTorqueImpulse(motorImp);
/* 197 */       if (body1 != null) {
/* 198 */         motorImp.negate();
/* 199 */         body1.applyTorqueImpulse(motorImp);
/*     */       }
/*     */ 
/* 202 */       return clippedMotorImpulse; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor
 * JD-Core Version:    0.6.2
 */