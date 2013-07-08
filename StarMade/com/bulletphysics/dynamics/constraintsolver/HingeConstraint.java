package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.ScalarUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class HingeConstraint
  extends TypedConstraint
{
  private JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  private JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  private final Transform rbAFrame = new Transform();
  private final Transform rbBFrame = new Transform();
  private float motorTargetVelocity;
  private float maxMotorImpulse;
  private float limitSoftness;
  private float biasFactor;
  private float relaxationFactor;
  private float lowerLimit;
  private float upperLimit;
  private float kHinge;
  private float limitSign;
  private float correction;
  private float accLimitImpulse;
  private boolean angularOnly;
  private boolean enableAngularMotor;
  private boolean solveLimit;
  
  public HingeConstraint()
  {
    super(TypedConstraintType.HINGE_CONSTRAINT_TYPE);
    this.enableAngularMotor = false;
  }
  
  public HingeConstraint(RigidBody arg1, RigidBody arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, Vector3f arg6) {}
  
  public HingeConstraint(RigidBody arg1, Vector3f arg2, Vector3f arg3) {}
  
  public HingeConstraint(RigidBody rbA, RigidBody rbB, Transform rbAFrame, Transform rbBFrame)
  {
    super(TypedConstraintType.HINGE_CONSTRAINT_TYPE, rbA, rbB);
    this.rbAFrame.set(rbAFrame);
    this.rbBFrame.set(rbBFrame);
    this.angularOnly = false;
    this.enableAngularMotor = false;
    this.rbBFrame.basis.m02 *= -1.0F;
    this.rbBFrame.basis.m12 *= -1.0F;
    this.rbBFrame.basis.m22 *= -1.0F;
    this.lowerLimit = 1.0E+030F;
    this.upperLimit = -1.0E+030F;
    this.biasFactor = 0.3F;
    this.relaxationFactor = 1.0F;
    this.limitSoftness = 0.9F;
    this.solveLimit = false;
  }
  
  public HingeConstraint(RigidBody arg1, Transform arg2) {}
  
  public void buildJacobian()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Vector3f();
      tmp11_7.push$javax$vecmath$Matrix3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Matrix3f mat1 = localStack.get$javax$vecmath$Matrix3f();
      Matrix3f mat2 = localStack.get$javax$vecmath$Matrix3f();
      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      this.appliedImpulse = 0.0F;
      if (!this.angularOnly)
      {
        Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
        centerOfMassA.transform(pivotAInW);
        Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
        centerOfMassB.transform(pivotBInW);
        Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
        relPos.sub(pivotBInW, pivotAInW);
        Vector3f[] normal = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
        if (relPos.lengthSquared() > 1.192093E-007F)
        {
          normal[0].set(relPos);
          normal[0].normalize();
        }
        else
        {
          normal[0].set(1.0F, 0.0F, 0.0F);
        }
        TransformUtil.planeSpace1(normal[0], normal[1], normal[2]);
        for (int local_i = 0; local_i < 3; local_i++)
        {
          mat1.transpose(centerOfMassA.basis);
          mat2.transpose(centerOfMassB.basis);
          tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
          this.jac[local_i].init(mat1, mat2, tmp1, tmp2, normal[local_i], this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
        }
      }
      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f();
      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(2, tmp);
      TransformUtil.planeSpace1(tmp, pivotAInW, pivotBInW);
      Vector3f relPos = localStack.get$javax$vecmath$Vector3f(pivotAInW);
      centerOfMassA.basis.transform(relPos);
      Vector3f normal = localStack.get$javax$vecmath$Vector3f(pivotBInW);
      centerOfMassA.basis.transform(normal);
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(2, local_i);
      centerOfMassA.basis.transform(local_i);
      mat1.transpose(centerOfMassA.basis);
      mat2.transpose(centerOfMassB.basis);
      this.jacAng[0].init(relPos, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
      this.jacAng[1].init(normal, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
      this.jacAng[2].init(local_i, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
      float hingeAngle = getHingeAngle();
      this.correction = 0.0F;
      this.limitSign = 0.0F;
      this.solveLimit = false;
      this.accLimitImpulse = 0.0F;
      if (this.lowerLimit < this.upperLimit) {
        if (hingeAngle <= this.lowerLimit * this.limitSoftness)
        {
          this.correction = (this.lowerLimit - hingeAngle);
          this.limitSign = 1.0F;
          this.solveLimit = true;
        }
        else if (hingeAngle >= this.upperLimit * this.limitSoftness)
        {
          this.correction = (this.upperLimit - hingeAngle);
          this.limitSign = -1.0F;
          this.solveLimit = true;
        }
      }
      Vector3f axisA = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(2, axisA);
      centerOfMassA.basis.transform(axisA);
      this.kHinge = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(axisA) + getRigidBodyB().computeAngularImpulseDenominator(axisA)));
      return;
    }
    finally
    {
      .Stack tmp792_790 = localStack;
      tmp792_790.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp796_792 = tmp792_790;
      tmp796_792.pop$javax$vecmath$Vector3f();
      tmp796_792.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void solveConstraint(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
      centerOfMassA.transform(pivotAInW);
      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
      centerOfMassB.transform(pivotBInW);
      float tau = 0.3F;
      if (!this.angularOnly)
      {
        Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
        rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
        Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
        rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
        Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
        Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
        vel.sub(vel1, vel2);
        for (int local_i = 0; local_i < 3; local_i++)
        {
          Vector3f normal = this.jac[local_i].linearJointAxis;
          float jacDiagABInv = 1.0F / this.jac[local_i].getDiagonal();
          float rel_vel = normal.dot(vel);
          tmp.sub(pivotAInW, pivotBInW);
          float depth = -tmp.dot(normal);
          float impulse = depth * tau / timeStep * jacDiagABInv - rel_vel * jacDiagABInv;
          this.appliedImpulse += impulse;
          Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
          impulse_vector.scale(impulse, normal);
          tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
          this.rbA.applyImpulse(impulse_vector, tmp);
          tmp.negate(impulse_vector);
          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
          this.rbB.applyImpulse(tmp, tmp2);
        }
      }
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(2, rel_pos1);
      centerOfMassA.basis.transform(rel_pos1);
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      this.rbBFrame.basis.getColumn(2, rel_pos2);
      centerOfMassB.basis.transform(rel_pos2);
      Vector3f vel1 = getRigidBodyA().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      Vector3f vel2 = getRigidBodyB().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.scale(rel_pos1.dot(vel1), rel_pos1);
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      local_i.scale(rel_pos2.dot(vel2), rel_pos2);
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      normal.sub(vel1, vel);
      Vector3f jacDiagABInv = localStack.get$javax$vecmath$Vector3f();
      jacDiagABInv.sub(vel2, local_i);
      Vector3f rel_vel = localStack.get$javax$vecmath$Vector3f();
      rel_vel.sub(normal, jacDiagABInv);
      float depth = 1.0F;
      float impulse = rel_vel.length();
      if (impulse > 1.0E-005F)
      {
        Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
        impulse_vector.normalize(rel_vel);
        float denom = getRigidBodyA().computeAngularImpulseDenominator(impulse_vector) + getRigidBodyB().computeAngularImpulseDenominator(impulse_vector);
        rel_vel.scale(1.0F / denom * this.relaxationFactor);
      }
      Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
      impulse_vector.cross(rel_pos1, rel_pos2);
      impulse_vector.negate();
      impulse_vector.scale(1.0F / timeStep);
      float denom = impulse_vector.length();
      if (denom > 1.0E-005F)
      {
        Vector3f normal2 = localStack.get$javax$vecmath$Vector3f();
        normal2.normalize(impulse_vector);
        float denom2 = getRigidBodyA().computeAngularImpulseDenominator(normal2) + getRigidBodyB().computeAngularImpulseDenominator(normal2);
        impulse_vector.scale(1.0F / denom2 * depth);
      }
      tmp.negate(rel_vel);
      tmp.add(impulse_vector);
      this.rbA.applyTorqueImpulse(tmp);
      tmp.sub(rel_vel, impulse_vector);
      this.rbB.applyTorqueImpulse(tmp);
      if (this.solveLimit)
      {
        tmp.sub(vel2, vel1);
        float normal2 = (tmp.dot(rel_pos1) * this.relaxationFactor + this.correction * (1.0F / timeStep) * this.biasFactor) * this.limitSign;
        float denom2 = normal2 * this.kHinge;
        float temp = this.accLimitImpulse;
        this.accLimitImpulse = Math.max(this.accLimitImpulse + denom2, 0.0F);
        denom2 = this.accLimitImpulse - temp;
        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
        impulse.scale(denom2 * this.limitSign, rel_pos1);
        this.rbA.applyTorqueImpulse(impulse);
        tmp.negate(impulse);
        this.rbB.applyTorqueImpulse(tmp);
      }
      if (this.enableAngularMotor)
      {
        Vector3f depth = localStack.get$javax$vecmath$Vector3f();
        depth.set(0.0F, 0.0F, 0.0F);
        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
        impulse.sub(vel, local_i);
        float impulse_vector = impulse.dot(rel_pos1);
        float denom = this.motorTargetVelocity;
        float normal2 = denom - impulse_vector;
        float denom2 = this.kHinge * normal2;
        float temp = denom2 > this.maxMotorImpulse ? this.maxMotorImpulse : denom2;
        temp = temp < -this.maxMotorImpulse ? -this.maxMotorImpulse : temp;
        Vector3f impulse = localStack.get$javax$vecmath$Vector3f();
        impulse.scale(temp, rel_pos1);
        tmp.add(impulse, depth);
        this.rbA.applyTorqueImpulse(tmp);
        tmp.negate(impulse);
        tmp.sub(depth);
        this.rbB.applyTorqueImpulse(tmp);
      }
      return;
    }
    finally
    {
      .Stack tmp1058_1056 = localStack;
      tmp1058_1056.pop$com$bulletphysics$linearmath$Transform();
      tmp1058_1056.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void updateRHS(float timeStep) {}
  
  public float getHingeAngle()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Vector3f refAxis0 = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(0, refAxis0);
      centerOfMassA.basis.transform(refAxis0);
      Vector3f refAxis1 = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(1, refAxis1);
      centerOfMassA.basis.transform(refAxis1);
      Vector3f swingAxis = localStack.get$javax$vecmath$Vector3f();
      this.rbBFrame.basis.getColumn(1, swingAxis);
      centerOfMassB.basis.transform(swingAxis);
      return ScalarUtil.atan2Fast(swingAxis.dot(refAxis0), swingAxis.dot(refAxis1));
    }
    finally
    {
      .Stack tmp152_150 = localStack;
      tmp152_150.pop$com$bulletphysics$linearmath$Transform();
      tmp152_150.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setAngularOnly(boolean angularOnly)
  {
    this.angularOnly = angularOnly;
  }
  
  public void enableAngularMotor(boolean enableMotor, float targetVelocity, float maxMotorImpulse)
  {
    this.enableAngularMotor = enableMotor;
    this.motorTargetVelocity = targetVelocity;
    this.maxMotorImpulse = maxMotorImpulse;
  }
  
  public void setLimit(float low, float high)
  {
    setLimit(low, high, 0.9F, 0.3F, 1.0F);
  }
  
  public void setLimit(float low, float high, float _softness, float _biasFactor, float _relaxationFactor)
  {
    this.lowerLimit = low;
    this.upperLimit = high;
    this.limitSoftness = _softness;
    this.biasFactor = _biasFactor;
    this.relaxationFactor = _relaxationFactor;
  }
  
  public float getLowerLimit()
  {
    return this.lowerLimit;
  }
  
  public float getUpperLimit()
  {
    return this.upperLimit;
  }
  
  public Transform getAFrame(Transform out)
  {
    out.set(this.rbAFrame);
    return out;
  }
  
  public Transform getBFrame(Transform out)
  {
    out.set(this.rbBFrame);
    return out;
  }
  
  public boolean getSolveLimit()
  {
    return this.solveLimit;
  }
  
  public float getLimitSign()
  {
    return this.limitSign;
  }
  
  public boolean getAngularOnly()
  {
    return this.angularOnly;
  }
  
  public boolean getEnableAngularMotor()
  {
    return this.enableAngularMotor;
  }
  
  public float getMotorTargetVelosity()
  {
    return this.motorTargetVelocity;
  }
  
  public float getMaxMotorImpulse()
  {
    return this.maxMotorImpulse;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.HingeConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */