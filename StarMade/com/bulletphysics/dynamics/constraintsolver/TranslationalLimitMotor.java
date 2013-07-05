/*     */ package com.bulletphysics.dynamics.constraintsolver;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.dynamics.RigidBody;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class TranslationalLimitMotor
/*     */ {
/*  47 */   public final Vector3f lowerLimit = new Vector3f();
/*  48 */   public final Vector3f upperLimit = new Vector3f();
/*  49 */   public final Vector3f accumulatedImpulse = new Vector3f();
/*     */   public float limitSoftness;
/*     */   public float damping;
/*     */   public float restitution;
/*     */ 
/*     */   public TranslationalLimitMotor()
/*     */   {
/*  56 */     this.lowerLimit.set(0.0F, 0.0F, 0.0F);
/*  57 */     this.upperLimit.set(0.0F, 0.0F, 0.0F);
/*  58 */     this.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  60 */     this.limitSoftness = 0.7F;
/*  61 */     this.damping = 1.0F;
/*  62 */     this.restitution = 0.5F;
/*     */   }
/*     */ 
/*     */   public TranslationalLimitMotor(TranslationalLimitMotor other) {
/*  66 */     this.lowerLimit.set(other.lowerLimit);
/*  67 */     this.upperLimit.set(other.upperLimit);
/*  68 */     this.accumulatedImpulse.set(other.accumulatedImpulse);
/*     */ 
/*  70 */     this.limitSoftness = other.limitSoftness;
/*  71 */     this.damping = other.damping;
/*  72 */     this.restitution = other.restitution;
/*     */   }
/*     */ 
/*     */   public boolean isLimited(int limitIndex)
/*     */   {
/*  83 */     return VectorUtil.getCoord(this.upperLimit, limitIndex) >= VectorUtil.getCoord(this.lowerLimit, limitIndex);
/*     */   }
/*     */ 
/*     */   public float solveLinearAxis(float arg1, float arg2, RigidBody arg3, Vector3f arg4, RigidBody arg5, Vector3f arg6, int arg7, Vector3f arg8, Vector3f arg9)
/*     */   {
/*  88 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  89 */       Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  92 */       Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  94 */       rel_pos1.sub(anchorPos, body1.getCenterOfMassPosition(tmpVec));
/*     */ 
/*  96 */       Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  98 */       rel_pos2.sub(anchorPos, body2.getCenterOfMassPosition(tmpVec));
/*     */ 
/* 100 */       Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
/* 101 */       Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
/* 102 */       Vector3f vel = localStack.get$javax$vecmath$Vector3f();
/* 103 */       vel.sub(vel1, vel2);
/*     */ 
/* 105 */       float rel_vel = axis_normal_on_a.dot(vel);
/*     */ 
/* 110 */       tmp.sub(pointInA, pointInB);
/* 111 */       float depth = -tmp.dot(axis_normal_on_a);
/* 112 */       float lo = -1.0E+030F;
/* 113 */       float hi = 1.0E+030F;
/*     */ 
/* 115 */       float minLimit = VectorUtil.getCoord(this.lowerLimit, limit_index);
/* 116 */       float maxLimit = VectorUtil.getCoord(this.upperLimit, limit_index);
/*     */ 
/* 119 */       if (minLimit < maxLimit)
/*     */       {
/* 121 */         if (depth > maxLimit) {
/* 122 */           depth -= maxLimit;
/* 123 */           lo = 0.0F;
/*     */         }
/* 127 */         else if (depth < minLimit) {
/* 128 */           depth -= minLimit;
/* 129 */           hi = 0.0F;
/*     */         }
/*     */         else {
/* 132 */           return 0.0F;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 138 */       float normalImpulse = this.limitSoftness * (this.restitution * depth / timeStep - this.damping * rel_vel) * jacDiagABInv;
/*     */ 
/* 140 */       float oldNormalImpulse = VectorUtil.getCoord(this.accumulatedImpulse, limit_index);
/* 141 */       float sum = oldNormalImpulse + normalImpulse;
/* 142 */       VectorUtil.setCoord(this.accumulatedImpulse, limit_index, sum < lo ? 0.0F : sum > hi ? 0.0F : sum);
/* 143 */       normalImpulse = VectorUtil.getCoord(this.accumulatedImpulse, limit_index) - oldNormalImpulse;
/*     */ 
/* 145 */       Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
/* 146 */       impulse_vector.scale(normalImpulse, axis_normal_on_a);
/* 147 */       body1.applyImpulse(impulse_vector, rel_pos1);
/*     */ 
/* 149 */       tmp.negate(impulse_vector);
/* 150 */       body2.applyImpulse(tmp, rel_pos2);
/* 151 */       return normalImpulse; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.TranslationalLimitMotor
 * JD-Core Version:    0.6.2
 */