package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.QuaternionUtil;
import com.bulletphysics.linearmath.ScalarUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class ConeTwistConstraint
  extends TypedConstraint
{
  private JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  private final Transform rbAFrame = new Transform();
  private final Transform rbBFrame = new Transform();
  private float limitSoftness;
  private float biasFactor;
  private float relaxationFactor;
  private float swingSpan1;
  private float swingSpan2;
  private float twistSpan;
  private final Vector3f swingAxis = new Vector3f();
  private final Vector3f twistAxis = new Vector3f();
  private float kSwing;
  private float kTwist;
  private float twistLimitSign;
  private float swingCorrection;
  private float twistCorrection;
  private float accSwingLimitImpulse;
  private float accTwistLimitImpulse;
  private boolean angularOnly = false;
  private boolean solveTwistLimit;
  private boolean solveSwingLimit;
  
  public ConeTwistConstraint()
  {
    super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE);
  }
  
  public ConeTwistConstraint(RigidBody rbA, RigidBody rbB, Transform rbAFrame, Transform rbBFrame)
  {
    super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE, rbA, rbB);
    this.rbAFrame.set(rbAFrame);
    this.rbBFrame.set(rbBFrame);
    this.swingSpan1 = 1.0E+030F;
    this.swingSpan2 = 1.0E+030F;
    this.twistSpan = 1.0E+030F;
    this.biasFactor = 0.3F;
    this.relaxationFactor = 1.0F;
    this.solveTwistLimit = false;
    this.solveSwingLimit = false;
  }
  
  public ConeTwistConstraint(RigidBody rbA, Transform rbAFrame)
  {
    super(TypedConstraintType.CONETWIST_CONSTRAINT_TYPE, rbA);
    this.rbAFrame.set(rbAFrame);
    this.rbBFrame.set(this.rbAFrame);
    this.swingSpan1 = 1.0E+030F;
    this.swingSpan2 = 1.0E+030F;
    this.twistSpan = 1.0E+030F;
    this.biasFactor = 0.3F;
    this.relaxationFactor = 1.0F;
    this.solveTwistLimit = false;
    this.solveSwingLimit = false;
  }
  
  public void buildJacobian()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Vector3f();
      tmp11_7.push$javax$vecmath$Quat4f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      this.appliedImpulse = 0.0F;
      this.swingCorrection = 0.0F;
      this.twistLimitSign = 0.0F;
      this.solveTwistLimit = false;
      this.solveSwingLimit = false;
      this.accTwistLimitImpulse = 0.0F;
      this.accSwingLimitImpulse = 0.0F;
      if (!this.angularOnly)
      {
        Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
        this.rbA.getCenterOfMassTransform(tmpTrans).transform(pivotAInW);
        Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
        this.rbB.getCenterOfMassTransform(tmpTrans).transform(pivotBInW);
        Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
        relPos.sub(pivotBInW, pivotAInW);
        Vector3f[] normal = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
        if (relPos.lengthSquared() > 1.192093E-007F) {
          normal[0].normalize(relPos);
        } else {
          normal[0].set(1.0F, 0.0F, 0.0F);
        }
        TransformUtil.planeSpace1(normal[0], normal[1], normal[2]);
        for (int local_i = 0; local_i < 3; local_i++)
        {
          Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
          mat1.transpose();
          Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
          mat2.transpose();
          tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmp));
          tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmp));
          this.jac[local_i].init(mat1, mat2, tmp1, tmp2, normal[local_i], this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
        }
      }
      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f();
      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f();
      Vector3f relPos = localStack.get$javax$vecmath$Vector3f();
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      this.rbAFrame.basis.getColumn(0, pivotAInW);
      getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(pivotAInW);
      this.rbBFrame.basis.getColumn(0, normal);
      getRigidBodyB().getCenterOfMassTransform(tmpTrans).basis.transform(normal);
      float mat1 = 0.0F;
      float mat2 = 0.0F;
      float swx = 0.0F;
      float swy = 0.0F;
      float thresh = 10.0F;
      if (this.swingSpan1 >= 0.05F)
      {
        this.rbAFrame.basis.getColumn(1, pivotBInW);
        getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(pivotBInW);
        swx = normal.dot(pivotAInW);
        swy = normal.dot(pivotBInW);
        mat1 = ScalarUtil.atan2Fast(swy, swx);
        float fact = (swy * swy + swx * swx) * thresh * thresh;
        fact /= (fact + 1.0F);
        mat1 *= fact;
      }
      if (this.swingSpan2 >= 0.05F)
      {
        this.rbAFrame.basis.getColumn(2, relPos);
        getRigidBodyA().getCenterOfMassTransform(tmpTrans).basis.transform(relPos);
        swx = normal.dot(pivotAInW);
        swy = normal.dot(relPos);
        mat2 = ScalarUtil.atan2Fast(swy, swx);
        float fact = (swy * swy + swx * swx) * thresh * thresh;
        fact /= (fact + 1.0F);
        mat2 *= fact;
      }
      float RMaxAngle1Sq = 1.0F / (this.swingSpan1 * this.swingSpan1);
      float RMaxAngle2Sq = 1.0F / (this.swingSpan2 * this.swingSpan2);
      float EllipseAngle = Math.abs(mat1 * mat1) * RMaxAngle1Sq + Math.abs(mat2 * mat2) * RMaxAngle2Sq;
      if (EllipseAngle > 1.0F)
      {
        this.swingCorrection = (EllipseAngle - 1.0F);
        this.solveSwingLimit = true;
        tmp1.scale(normal.dot(pivotBInW), pivotBInW);
        tmp2.scale(normal.dot(relPos), relPos);
        tmp.add(tmp1, tmp2);
        this.swingAxis.cross(normal, tmp);
        this.swingAxis.normalize();
        float swingAxisSign = normal.dot(pivotAInW) >= 0.0F ? 1.0F : -1.0F;
        this.swingAxis.scale(swingAxisSign);
        this.kSwing = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.swingAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.swingAxis)));
      }
      if (this.twistSpan >= 0.0F)
      {
        this.rbBFrame.basis.getColumn(1, local_i);
        getRigidBodyB().getCenterOfMassTransform(tmpTrans).basis.transform(local_i);
        Quat4f swingAxisSign = QuaternionUtil.shortestArcQuat(normal, pivotAInW, localStack.get$javax$vecmath$Quat4f());
        Vector3f TwistRef = QuaternionUtil.quatRotate(swingAxisSign, local_i, localStack.get$javax$vecmath$Vector3f());
        float twist = ScalarUtil.atan2Fast(TwistRef.dot(relPos), TwistRef.dot(pivotBInW));
        float lockedFreeFactor = this.twistSpan > 0.05F ? this.limitSoftness : 0.0F;
        if (twist <= -this.twistSpan * lockedFreeFactor)
        {
          this.twistCorrection = (-(twist + this.twistSpan));
          this.solveTwistLimit = true;
          this.twistAxis.add(normal, pivotAInW);
          this.twistAxis.scale(0.5F);
          this.twistAxis.normalize();
          this.twistAxis.scale(-1.0F);
          this.kTwist = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.twistAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.twistAxis)));
        }
        else if (twist > this.twistSpan * lockedFreeFactor)
        {
          this.twistCorrection = (twist - this.twistSpan);
          this.solveTwistLimit = true;
          this.twistAxis.add(normal, pivotAInW);
          this.twistAxis.scale(0.5F);
          this.twistAxis.normalize();
          this.kTwist = (1.0F / (getRigidBodyA().computeAngularImpulseDenominator(this.twistAxis) + getRigidBodyB().computeAngularImpulseDenominator(this.twistAxis)));
        }
      }
      return;
    }
    finally
    {
      .Stack tmp1184_1182 = localStack;
      tmp1184_1182.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp1188_1184 = tmp1184_1182;
      tmp1188_1184.pop$javax$vecmath$Vector3f();
      tmp1188_1184.pop$javax$vecmath$Quat4f();
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
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.rbAFrame.origin);
      this.rbA.getCenterOfMassTransform(tmpTrans).transform(pivotAInW);
      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.rbBFrame.origin);
      this.rbB.getCenterOfMassTransform(tmpTrans).transform(pivotBInW);
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
      Vector3f rel_pos1 = getRigidBodyA().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      Vector3f rel_pos2 = getRigidBodyB().getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      if (this.solveSwingLimit)
      {
        tmp.sub(rel_pos2, rel_pos1);
        float vel1 = tmp.dot(this.swingAxis) * this.relaxationFactor * this.relaxationFactor + this.swingCorrection * (1.0F / timeStep) * this.biasFactor;
        float vel2 = vel1 * this.kSwing;
        float vel = this.accSwingLimitImpulse;
        this.accSwingLimitImpulse = Math.max(this.accSwingLimitImpulse + vel2, 0.0F);
        vel2 = this.accSwingLimitImpulse - vel;
        Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
        local_i.scale(vel2, this.swingAxis);
        this.rbA.applyTorqueImpulse(local_i);
        tmp.negate(local_i);
        this.rbB.applyTorqueImpulse(tmp);
      }
      if (this.solveTwistLimit)
      {
        tmp.sub(rel_pos2, rel_pos1);
        float vel1 = tmp.dot(this.twistAxis) * this.relaxationFactor * this.relaxationFactor + this.twistCorrection * (1.0F / timeStep) * this.biasFactor;
        float vel2 = vel1 * this.kTwist;
        float vel = this.accTwistLimitImpulse;
        this.accTwistLimitImpulse = Math.max(this.accTwistLimitImpulse + vel2, 0.0F);
        vel2 = this.accTwistLimitImpulse - vel;
        Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
        local_i.scale(vel2, this.twistAxis);
        this.rbA.applyTorqueImpulse(local_i);
        tmp.negate(local_i);
        this.rbB.applyTorqueImpulse(tmp);
      }
      return;
    }
    finally
    {
      .Stack tmp668_666 = localStack;
      tmp668_666.pop$com$bulletphysics$linearmath$Transform();
      tmp668_666.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void updateRHS(float timeStep) {}
  
  public void setAngularOnly(boolean angularOnly)
  {
    this.angularOnly = angularOnly;
  }
  
  public void setLimit(float _swingSpan1, float _swingSpan2, float _twistSpan)
  {
    setLimit(_swingSpan1, _swingSpan2, _twistSpan, 0.8F, 0.3F, 1.0F);
  }
  
  public void setLimit(float _swingSpan1, float _swingSpan2, float _twistSpan, float _softness, float _biasFactor, float _relaxationFactor)
  {
    this.swingSpan1 = _swingSpan1;
    this.swingSpan2 = _swingSpan2;
    this.twistSpan = _twistSpan;
    this.limitSoftness = _softness;
    this.biasFactor = _biasFactor;
    this.relaxationFactor = _relaxationFactor;
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
  
  public boolean getSolveTwistLimit()
  {
    return this.solveTwistLimit;
  }
  
  public boolean getSolveSwingLimit()
  {
    return this.solveTwistLimit;
  }
  
  public float getTwistLimitSign()
  {
    return this.twistLimitSign;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConeTwistConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */