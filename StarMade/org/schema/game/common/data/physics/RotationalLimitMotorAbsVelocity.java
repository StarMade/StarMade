/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.dynamics.RigidBody;
/*    */ import com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class RotationalLimitMotorAbsVelocity extends RotationalLimitMotor
/*    */ {
/*    */   public float solveAngularLimits(float paramFloat1, Vector3f paramVector3f, float paramFloat2, RigidBody paramRigidBody1, RigidBody paramRigidBody2)
/*    */   {
/* 18 */     if (!needApplyTorques()) {
/* 19 */       return 0.0F;
/*    */     }
/*    */ 
/* 22 */     float f1 = this.targetVelocity;
/* 23 */     float f2 = this.maxMotorForce;
/*    */ 
/* 26 */     if (this.currentLimit != 0) {
/* 27 */       f1 = -this.ERP * this.currentLimitError / paramFloat1;
/* 28 */       f2 = this.maxLimitForce;
/*    */     }
/*    */ 
/* 31 */     f2 *= paramFloat1;
/*    */ 
/* 34 */     paramFloat1 = paramRigidBody1.getAngularVelocity(new Vector3f());
/* 35 */     if (paramRigidBody2 != null) {
/* 36 */       paramFloat1.sub(paramRigidBody2.getAngularVelocity(new Vector3f()));
/*    */     }
/*    */ 
/* 39 */     paramFloat1 = paramVector3f.dot(paramFloat1);
/*    */ 
/* 44 */     if (((
/* 44 */       paramFloat1 = this.limitSoftness * (f1 - this.damping * paramFloat1)) < 
/* 44 */       1.192093E-007F) && (paramFloat1 > -1.192093E-007F)) {
/* 45 */       return 0.0F;
/*    */     }
/*    */ 
/* 55 */     if ((
/* 55 */       paramFloat1 = (1.0F + this.bounce) * paramFloat1 * paramFloat2) > 
/* 55 */       0.0F) {
/* 56 */       paramFloat1 = paramFloat1 > f2 ? f2 : paramFloat1;
/*    */     }
/*    */     else {
/* 59 */       paramFloat1 = paramFloat1 < -f2 ? -f2 : paramFloat1;
/*    */     }
/*    */ 
/* 67 */     paramFloat1 = (
/* 67 */       paramFloat2 = this.accumulatedImpulse) + 
/* 67 */       paramFloat1;
/* 68 */     this.accumulatedImpulse = (paramFloat1 < -1.0E+030F ? 0.0F : paramFloat1 > 1.0E+030F ? 0.0F : paramFloat1);
/*    */ 
/* 70 */     paramFloat1 = this.accumulatedImpulse - paramFloat2;
/*    */ 
/* 72 */     (
/* 73 */       paramFloat2 = new Vector3f())
/* 73 */       .scale(paramFloat1 * 10.0F, paramVector3f);
/*    */ 
/* 77 */     (
/* 78 */       paramVector3f = paramRigidBody1.getAngularVelocity(new Vector3f())).x = 
/* 78 */       (Math.signum(paramFloat2.x) == Math.signum(paramVector3f.x) ? paramVector3f.x : paramFloat2.x);
/* 79 */     paramVector3f.y = (Math.signum(paramFloat2.y) == Math.signum(paramVector3f.y) ? paramVector3f.y : paramFloat2.y);
/* 80 */     paramVector3f.z = (Math.signum(paramFloat2.z) == Math.signum(paramVector3f.z) ? paramVector3f.z : paramFloat2.z);
/*    */ 
/* 83 */     paramRigidBody1.setAngularVelocity(paramVector3f);
/* 84 */     if (paramRigidBody2 != null) {
/* 85 */       paramFloat2.negate();
/*    */ 
/* 87 */       if (!paramRigidBody2.isStaticObject())
/*    */       {
/* 89 */         paramRigidBody2.setAngularVelocity(paramVector3f);
/*    */       }
/*    */     }
/*    */ 
/* 93 */     return paramFloat1;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RotationalLimitMotorAbsVelocity
 * JD-Core Version:    0.6.2
 */