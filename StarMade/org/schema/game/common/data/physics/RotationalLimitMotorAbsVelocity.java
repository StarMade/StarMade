/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.dynamics.RigidBody;
/*  4:   */import com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 13:   */public class RotationalLimitMotorAbsVelocity
/* 14:   */  extends RotationalLimitMotor
/* 15:   */{
/* 16:   */  public float solveAngularLimits(float paramFloat1, Vector3f paramVector3f, float paramFloat2, RigidBody paramRigidBody1, RigidBody paramRigidBody2)
/* 17:   */  {
/* 18:18 */    if (!needApplyTorques()) {
/* 19:19 */      return 0.0F;
/* 20:   */    }
/* 21:   */    
/* 22:22 */    float f1 = this.targetVelocity;
/* 23:23 */    float f2 = this.maxMotorForce;
/* 24:   */    
/* 26:26 */    if (this.currentLimit != 0) {
/* 27:27 */      f1 = -this.ERP * this.currentLimitError / paramFloat1;
/* 28:28 */      f2 = this.maxLimitForce;
/* 29:   */    }
/* 30:   */    
/* 31:31 */    f2 *= paramFloat1;
/* 32:   */    
/* 34:34 */    paramFloat1 = paramRigidBody1.getAngularVelocity(new Vector3f());
/* 35:35 */    if (paramRigidBody2 != null) {
/* 36:36 */      paramFloat1.sub(paramRigidBody2.getAngularVelocity(new Vector3f()));
/* 37:   */    }
/* 38:   */    
/* 39:39 */    paramFloat1 = paramVector3f.dot(paramFloat1);
/* 40:   */    
/* 44:44 */    if (((paramFloat1 = this.limitSoftness * (f1 - this.damping * paramFloat1)) < 1.192093E-007F) && (paramFloat1 > -1.192093E-007F)) {
/* 45:45 */      return 0.0F;
/* 46:   */    }
/* 47:   */    
/* 55:55 */    if ((paramFloat1 = (1.0F + this.bounce) * paramFloat1 * paramFloat2) > 0.0F) {
/* 56:56 */      paramFloat1 = paramFloat1 > f2 ? f2 : paramFloat1;
/* 57:   */    }
/* 58:   */    else {
/* 59:59 */      paramFloat1 = paramFloat1 < -f2 ? -f2 : paramFloat1;
/* 60:   */    }
/* 61:   */    
/* 67:67 */    paramFloat1 = (paramFloat2 = this.accumulatedImpulse) + paramFloat1;
/* 68:68 */    this.accumulatedImpulse = (paramFloat1 < -1.0E+030F ? 0.0F : paramFloat1 > 1.0E+030F ? 0.0F : paramFloat1);
/* 69:   */    
/* 70:70 */    paramFloat1 = this.accumulatedImpulse - paramFloat2;
/* 71:   */    
/* 72:72 */    (
/* 73:73 */      paramFloat2 = new Vector3f()).scale(paramFloat1 * 10.0F, paramVector3f);
/* 74:   */    
/* 78:78 */    (paramVector3f = paramRigidBody1.getAngularVelocity(new Vector3f())).x = (Math.signum(paramFloat2.x) == Math.signum(paramVector3f.x) ? paramVector3f.x : paramFloat2.x);
/* 79:79 */    paramVector3f.y = (Math.signum(paramFloat2.y) == Math.signum(paramVector3f.y) ? paramVector3f.y : paramFloat2.y);
/* 80:80 */    paramVector3f.z = (Math.signum(paramFloat2.z) == Math.signum(paramVector3f.z) ? paramVector3f.z : paramFloat2.z);
/* 81:   */    
/* 83:83 */    paramRigidBody1.setAngularVelocity(paramVector3f);
/* 84:84 */    if (paramRigidBody2 != null) {
/* 85:85 */      paramFloat2.negate();
/* 86:   */      
/* 87:87 */      if (!paramRigidBody2.isStaticObject())
/* 88:   */      {
/* 89:89 */        paramRigidBody2.setAngularVelocity(paramVector3f);
/* 90:   */      }
/* 91:   */    }
/* 92:   */    
/* 93:93 */    return paramFloat1;
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RotationalLimitMotorAbsVelocity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */