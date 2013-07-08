package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

public class TranslationalLimitMotor
{
  public final Vector3f lowerLimit = new Vector3f();
  public final Vector3f upperLimit = new Vector3f();
  public final Vector3f accumulatedImpulse = new Vector3f();
  public float limitSoftness;
  public float damping;
  public float restitution;
  
  public TranslationalLimitMotor()
  {
    this.lowerLimit.set(0.0F, 0.0F, 0.0F);
    this.upperLimit.set(0.0F, 0.0F, 0.0F);
    this.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
    this.limitSoftness = 0.7F;
    this.damping = 1.0F;
    this.restitution = 0.5F;
  }
  
  public TranslationalLimitMotor(TranslationalLimitMotor other)
  {
    this.lowerLimit.set(other.lowerLimit);
    this.upperLimit.set(other.upperLimit);
    this.accumulatedImpulse.set(other.accumulatedImpulse);
    this.limitSoftness = other.limitSoftness;
    this.damping = other.damping;
    this.restitution = other.restitution;
  }
  
  public boolean isLimited(int limitIndex)
  {
    return VectorUtil.getCoord(this.upperLimit, limitIndex) >= VectorUtil.getCoord(this.lowerLimit, limitIndex);
  }
  
  public float solveLinearAxis(float arg1, float arg2, RigidBody arg3, Vector3f arg4, RigidBody arg5, Vector3f arg6, int arg7, Vector3f arg8, Vector3f arg9)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      rel_pos1.sub(anchorPos, body1.getCenterOfMassPosition(tmpVec));
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      rel_pos2.sub(anchorPos, body2.getCenterOfMassPosition(tmpVec));
      Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.sub(vel1, vel2);
      float rel_vel = axis_normal_on_a.dot(vel);
      tmp.sub(pointInA, pointInB);
      float depth = -tmp.dot(axis_normal_on_a);
      float local_lo = -1.0E+030F;
      float local_hi = 1.0E+030F;
      float minLimit = VectorUtil.getCoord(this.lowerLimit, limit_index);
      float maxLimit = VectorUtil.getCoord(this.upperLimit, limit_index);
      if (minLimit < maxLimit) {
        if (depth > maxLimit)
        {
          depth -= maxLimit;
          local_lo = 0.0F;
        }
        else if (depth < minLimit)
        {
          depth -= minLimit;
          local_hi = 0.0F;
        }
        else
        {
          return 0.0F;
        }
      }
      float normalImpulse = this.limitSoftness * (this.restitution * depth / timeStep - this.damping * rel_vel) * jacDiagABInv;
      float oldNormalImpulse = VectorUtil.getCoord(this.accumulatedImpulse, limit_index);
      float sum = oldNormalImpulse + normalImpulse;
      VectorUtil.setCoord(this.accumulatedImpulse, limit_index, sum < local_lo ? 0.0F : sum > local_hi ? 0.0F : sum);
      normalImpulse = VectorUtil.getCoord(this.accumulatedImpulse, limit_index) - oldNormalImpulse;
      Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
      impulse_vector.scale(normalImpulse, axis_normal_on_a);
      body1.applyImpulse(impulse_vector, rel_pos1);
      tmp.negate(impulse_vector);
      body2.applyImpulse(tmp, rel_pos2);
      return normalImpulse;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.TranslationalLimitMotor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */