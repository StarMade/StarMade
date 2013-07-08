package org.schema.game.common.data.physics;

import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor;
import javax.vecmath.Vector3f;

public class RotationalLimitMotorAbsVelocity
  extends RotationalLimitMotor
{
  public float solveAngularLimits(float paramFloat1, Vector3f paramVector3f, float paramFloat2, RigidBody paramRigidBody1, RigidBody paramRigidBody2)
  {
    if (!needApplyTorques()) {
      return 0.0F;
    }
    float f1 = this.targetVelocity;
    float f2 = this.maxMotorForce;
    if (this.currentLimit != 0)
    {
      f1 = -this.ERP * this.currentLimitError / paramFloat1;
      f2 = this.maxLimitForce;
    }
    f2 *= paramFloat1;
    paramFloat1 = paramRigidBody1.getAngularVelocity(new Vector3f());
    if (paramRigidBody2 != null) {
      paramFloat1.sub(paramRigidBody2.getAngularVelocity(new Vector3f()));
    }
    paramFloat1 = paramVector3f.dot(paramFloat1);
    if (((paramFloat1 = this.limitSoftness * (f1 - this.damping * paramFloat1)) < 1.192093E-007F) && (paramFloat1 > -1.192093E-007F)) {
      return 0.0F;
    }
    if ((paramFloat1 = (1.0F + this.bounce) * paramFloat1 * paramFloat2) > 0.0F) {
      paramFloat1 = paramFloat1 > f2 ? f2 : paramFloat1;
    } else {
      paramFloat1 = paramFloat1 < -f2 ? -f2 : paramFloat1;
    }
    paramFloat1 = (paramFloat2 = this.accumulatedImpulse) + paramFloat1;
    this.accumulatedImpulse = (paramFloat1 < -1.0E+030F ? 0.0F : paramFloat1 > 1.0E+030F ? 0.0F : paramFloat1);
    paramFloat1 = this.accumulatedImpulse - paramFloat2;
    (paramFloat2 = new Vector3f()).scale(paramFloat1 * 10.0F, paramVector3f);
    (paramVector3f = paramRigidBody1.getAngularVelocity(new Vector3f())).field_615 = (Math.signum(paramFloat2.field_615) == Math.signum(paramVector3f.field_615) ? paramVector3f.field_615 : paramFloat2.field_615);
    paramVector3f.field_616 = (Math.signum(paramFloat2.field_616) == Math.signum(paramVector3f.field_616) ? paramVector3f.field_616 : paramFloat2.field_616);
    paramVector3f.field_617 = (Math.signum(paramFloat2.field_617) == Math.signum(paramVector3f.field_617) ? paramVector3f.field_617 : paramFloat2.field_617);
    paramRigidBody1.setAngularVelocity(paramVector3f);
    if (paramRigidBody2 != null)
    {
      paramFloat2.negate();
      if (!paramRigidBody2.isStaticObject()) {
        paramRigidBody2.setAngularVelocity(paramVector3f);
      }
    }
    return paramFloat1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.RotationalLimitMotorAbsVelocity
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */