package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class Point2PointConstraint
  extends TypedConstraint
{
  private final JacobianEntry[] jac = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  private final Vector3f pivotInA = new Vector3f();
  private final Vector3f pivotInB = new Vector3f();
  public ConstraintSetting setting = new ConstraintSetting();
  
  public Point2PointConstraint()
  {
    super(TypedConstraintType.POINT2POINT_CONSTRAINT_TYPE);
  }
  
  public Point2PointConstraint(RigidBody rbA, RigidBody rbB, Vector3f pivotInA, Vector3f pivotInB)
  {
    super(TypedConstraintType.POINT2POINT_CONSTRAINT_TYPE, rbA, rbB);
    this.pivotInA.set(pivotInA);
    this.pivotInB.set(pivotInB);
  }
  
  public Point2PointConstraint(RigidBody arg1, Vector3f arg2) {}
  
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
      this.appliedImpulse = 0.0F;
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      normal.set(0.0F, 0.0F, 0.0F);
      Matrix3f tmpMat1 = localStack.get$javax$vecmath$Matrix3f();
      Matrix3f tmpMat2 = localStack.get$javax$vecmath$Matrix3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Transform centerOfMassA = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Transform centerOfMassB = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      for (int local_i = 0; local_i < 3; local_i++)
      {
        VectorUtil.setCoord(normal, local_i, 1.0F);
        tmpMat1.transpose(centerOfMassA.basis);
        tmpMat2.transpose(centerOfMassB.basis);
        tmp1.set(this.pivotInA);
        centerOfMassA.transform(tmp1);
        tmp1.sub(this.rbA.getCenterOfMassPosition(tmpVec));
        tmp2.set(this.pivotInB);
        centerOfMassB.transform(tmp2);
        tmp2.sub(this.rbB.getCenterOfMassPosition(tmpVec));
        this.jac[local_i].init(tmpMat1, tmpMat2, tmp1, tmp2, normal, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
        VectorUtil.setCoord(normal, local_i, 0.0F);
      }
      return;
    }
    finally
    {
      .Stack tmp275_273 = localStack;
      tmp275_273.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp279_275 = tmp275_273;
      tmp279_275.pop$javax$vecmath$Vector3f();
      tmp279_275.pop$javax$vecmath$Matrix3f();
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
      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.pivotInA);
      centerOfMassA.transform(pivotAInW);
      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.pivotInB);
      centerOfMassB.transform(pivotBInW);
      Vector3f normal = localStack.get$javax$vecmath$Vector3f();
      normal.set(0.0F, 0.0F, 0.0F);
      for (int local_i = 0; local_i < 3; local_i++)
      {
        VectorUtil.setCoord(normal, local_i, 1.0F);
        float jacDiagABInv = 1.0F / this.jac[local_i].getDiagonal();
        Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
        rel_pos1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
        Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
        rel_pos2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
        Vector3f vel1 = this.rbA.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
        Vector3f vel2 = this.rbB.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
        vel.sub(vel1, vel2);
        float rel_vel = normal.dot(vel);
        tmp.sub(pivotAInW, pivotBInW);
        float depth = -tmp.dot(normal);
        float impulse = depth * this.setting.tau / timeStep * jacDiagABInv - this.setting.damping * rel_vel * jacDiagABInv;
        float impulseClamp = this.setting.impulseClamp;
        if (impulseClamp > 0.0F)
        {
          if (impulse < -impulseClamp) {
            impulse = -impulseClamp;
          }
          if (impulse > impulseClamp) {
            impulse = impulseClamp;
          }
        }
        this.appliedImpulse += impulse;
        Vector3f impulse_vector = localStack.get$javax$vecmath$Vector3f();
        impulse_vector.scale(impulse, normal);
        tmp.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
        this.rbA.applyImpulse(impulse_vector, tmp);
        tmp.negate(impulse_vector);
        tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
        this.rbB.applyImpulse(tmp, tmp2);
        VectorUtil.setCoord(normal, local_i, 0.0F);
      }
      return;
    }
    finally
    {
      .Stack tmp446_444 = localStack;
      tmp446_444.pop$com$bulletphysics$linearmath$Transform();
      tmp446_444.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void updateRHS(float timeStep) {}
  
  public void setPivotA(Vector3f pivotA)
  {
    this.pivotInA.set(pivotA);
  }
  
  public void setPivotB(Vector3f pivotB)
  {
    this.pivotInB.set(pivotB);
  }
  
  public Vector3f getPivotInA(Vector3f out)
  {
    out.set(this.pivotInA);
    return out;
  }
  
  public Vector3f getPivotInB(Vector3f out)
  {
    out.set(this.pivotInB);
    return out;
  }
  
  public static class ConstraintSetting
  {
    public float tau = 0.3F;
    public float damping = 1.0F;
    public float impulseClamp = 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.Point2PointConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */