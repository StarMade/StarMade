package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import javax.vecmath.Vector3f;

public class RotationalLimitMotor
{
  public float loLimit;
  public float hiLimit;
  public float targetVelocity;
  public float maxMotorForce;
  public float maxLimitForce;
  public float damping;
  public float limitSoftness;
  public float ERP;
  public float bounce;
  public boolean enableMotor;
  public float currentLimitError;
  public int currentLimit;
  public float accumulatedImpulse;
  
  public RotationalLimitMotor()
  {
    this.accumulatedImpulse = 0.0F;
    this.targetVelocity = 0.0F;
    this.maxMotorForce = 0.1F;
    this.maxLimitForce = 300.0F;
    this.loLimit = -3.402824E+038F;
    this.hiLimit = 3.4028235E+38F;
    this.ERP = 0.5F;
    this.bounce = 0.0F;
    this.damping = 1.0F;
    this.limitSoftness = 0.5F;
    this.currentLimit = 0;
    this.currentLimitError = 0.0F;
    this.enableMotor = false;
  }
  
  public RotationalLimitMotor(RotationalLimitMotor limot)
  {
    this.targetVelocity = limot.targetVelocity;
    this.maxMotorForce = limot.maxMotorForce;
    this.limitSoftness = limot.limitSoftness;
    this.loLimit = limot.loLimit;
    this.hiLimit = limot.hiLimit;
    this.ERP = limot.ERP;
    this.bounce = limot.bounce;
    this.currentLimit = limot.currentLimit;
    this.currentLimitError = limot.currentLimitError;
    this.enableMotor = limot.enableMotor;
  }
  
  public boolean isLimited()
  {
    return this.loLimit < this.hiLimit;
  }
  
  public boolean needApplyTorques()
  {
    return (this.currentLimit != 0) || (this.enableMotor);
  }
  
  public int testLimitValue(float test_value)
  {
    if (this.loLimit > this.hiLimit)
    {
      this.currentLimit = 0;
      return 0;
    }
    if (test_value < this.loLimit)
    {
      this.currentLimit = 1;
      this.currentLimitError = (test_value - this.loLimit);
      return 1;
    }
    if (test_value > this.hiLimit)
    {
      this.currentLimit = 2;
      this.currentLimitError = (test_value - this.hiLimit);
      return 2;
    }
    this.currentLimit = 0;
    return 0;
  }
  
  public float solveAngularLimits(float arg1, Vector3f arg2, float arg3, RigidBody arg4, RigidBody arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (!needApplyTorques()) {
        return 0.0F;
      }
      float target_velocity = this.targetVelocity;
      float maxMotorForce = this.maxMotorForce;
      if (this.currentLimit != 0)
      {
        target_velocity = -this.ERP * this.currentLimitError / timeStep;
        maxMotorForce = this.maxLimitForce;
      }
      maxMotorForce *= timeStep;
      Vector3f vel_diff = body0.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      if (body1 != null) {
        vel_diff.sub(body1.getAngularVelocity(localStack.get$javax$vecmath$Vector3f()));
      }
      float rel_vel = axis.dot(vel_diff);
      float motor_relvel = this.limitSoftness * (target_velocity - this.damping * rel_vel);
      if ((motor_relvel < 1.192093E-007F) && (motor_relvel > -1.192093E-007F)) {
        return 0.0F;
      }
      float unclippedMotorImpulse = (1.0F + this.bounce) * motor_relvel * jacDiagABInv;
      float clippedMotorImpulse;
      if (unclippedMotorImpulse > 0.0F) {
        clippedMotorImpulse = unclippedMotorImpulse > maxMotorForce ? maxMotorForce : unclippedMotorImpulse;
      } else {
        clippedMotorImpulse = unclippedMotorImpulse < -maxMotorForce ? -maxMotorForce : unclippedMotorImpulse;
      }
      float local_lo = -1.0E+030F;
      float local_hi = 1.0E+030F;
      float oldaccumImpulse = this.accumulatedImpulse;
      float sum = oldaccumImpulse + clippedMotorImpulse;
      this.accumulatedImpulse = (sum < local_lo ? 0.0F : sum > local_hi ? 0.0F : sum);
      float clippedMotorImpulse = this.accumulatedImpulse - oldaccumImpulse;
      Vector3f motorImp = localStack.get$javax$vecmath$Vector3f();
      motorImp.scale(clippedMotorImpulse, axis);
      body0.applyTorqueImpulse(motorImp);
      if (body1 != null)
      {
        motorImp.negate();
        body1.applyTorqueImpulse(motorImp);
      }
      return clippedMotorImpulse;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */