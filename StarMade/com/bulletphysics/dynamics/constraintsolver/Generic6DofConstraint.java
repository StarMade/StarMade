package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class Generic6DofConstraint
  extends TypedConstraint
{
  protected final Transform frameInA = new Transform();
  protected final Transform frameInB = new Transform();
  protected final JacobianEntry[] jacLinear = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  protected final JacobianEntry[] jacAng = { new JacobianEntry(), new JacobianEntry(), new JacobianEntry() };
  protected final TranslationalLimitMotor linearLimits = new TranslationalLimitMotor();
  protected final RotationalLimitMotor[] angularLimits = { new RotationalLimitMotor(), new RotationalLimitMotor(), new RotationalLimitMotor() };
  protected float timeStep;
  protected final Transform calculatedTransformA = new Transform();
  protected final Transform calculatedTransformB = new Transform();
  protected final Vector3f calculatedAxisAngleDiff = new Vector3f();
  protected final Vector3f[] calculatedAxis = { new Vector3f(), new Vector3f(), new Vector3f() };
  protected final Vector3f anchorPos = new Vector3f();
  protected boolean useLinearReferenceFrameA;
  
  public Generic6DofConstraint()
  {
    super(TypedConstraintType.D6_CONSTRAINT_TYPE);
    this.useLinearReferenceFrameA = true;
  }
  
  public Generic6DofConstraint(RigidBody rbA, RigidBody rbB, Transform frameInA, Transform frameInB, boolean useLinearReferenceFrameA)
  {
    super(TypedConstraintType.D6_CONSTRAINT_TYPE, rbA, rbB);
    this.frameInA.set(frameInA);
    this.frameInB.set(frameInB);
    this.useLinearReferenceFrameA = useLinearReferenceFrameA;
  }
  
  private static float getMatrixElem(Matrix3f mat, int index)
  {
    int local_i = index % 3;
    int local_j = index / 3;
    return mat.getElement(local_i, local_j);
  }
  
  private static boolean matrixToEulerXYZ(Matrix3f mat, Vector3f xyz)
  {
    if (getMatrixElem(mat, 2) < 1.0F)
    {
      if (getMatrixElem(mat, 2) > -1.0F)
      {
        xyz.field_615 = ((float)Math.atan2(-getMatrixElem(mat, 5), getMatrixElem(mat, 8)));
        xyz.field_616 = ((float)Math.asin(getMatrixElem(mat, 2)));
        xyz.field_617 = ((float)Math.atan2(-getMatrixElem(mat, 1), getMatrixElem(mat, 0)));
        return true;
      }
      xyz.field_615 = (-(float)Math.atan2(getMatrixElem(mat, 3), getMatrixElem(mat, 4)));
      xyz.field_616 = -1.570796F;
      xyz.field_617 = 0.0F;
      return false;
    }
    xyz.field_615 = ((float)Math.atan2(getMatrixElem(mat, 3), getMatrixElem(mat, 4)));
    xyz.field_616 = 1.570796F;
    xyz.field_617 = 0.0F;
    return false;
  }
  
  protected void calculateAngleInfo()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Matrix3f mat = localStack.get$javax$vecmath$Matrix3f();
      Matrix3f relative_frame = localStack.get$javax$vecmath$Matrix3f();
      mat.set(this.calculatedTransformA.basis);
      MatrixUtil.invert(mat);
      relative_frame.mul(mat, this.calculatedTransformB.basis);
      matrixToEulerXYZ(relative_frame, this.calculatedAxisAngleDiff);
      Vector3f axis0 = localStack.get$javax$vecmath$Vector3f();
      this.calculatedTransformB.basis.getColumn(0, axis0);
      Vector3f axis2 = localStack.get$javax$vecmath$Vector3f();
      this.calculatedTransformA.basis.getColumn(2, axis2);
      this.calculatedAxis[1].cross(axis2, axis0);
      this.calculatedAxis[0].cross(this.calculatedAxis[1], axis2);
      this.calculatedAxis[2].cross(axis0, this.calculatedAxis[1]);
      return;
    }
    finally
    {
      .Stack tmp157_155 = localStack;
      tmp157_155.pop$javax$vecmath$Vector3f();
      tmp157_155.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void calculateTransforms()
  {
    this.rbA.getCenterOfMassTransform(this.calculatedTransformA);
    this.calculatedTransformA.mul(this.frameInA);
    this.rbB.getCenterOfMassTransform(this.calculatedTransformB);
    this.calculatedTransformB.mul(this.frameInB);
    calculateAngleInfo();
  }
  
  protected void buildLinearJacobian(int arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
      mat1.transpose();
      Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
      mat2.transpose();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      tmp1.sub(pivotAInW, this.rbA.getCenterOfMassPosition(tmpVec));
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp2.sub(pivotBInW, this.rbB.getCenterOfMassPosition(tmpVec));
      this.jacLinear[jacLinear_index].init(mat1, mat2, tmp1, tmp2, normalWorld, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbA.getInvMass(), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvMass());
      return;
    }
    finally
    {
      .Stack tmp178_176 = localStack;
      tmp178_176.pop$com$bulletphysics$linearmath$Transform();
      tmp178_176.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void buildAngularJacobian(int arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Matrix3f mat1 = this.rbA.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
      mat1.transpose();
      Matrix3f mat2 = this.rbB.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
      mat2.transpose();
      this.jacAng[jacAngular_index].init(jointAxisW, mat1, mat2, this.rbA.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), this.rbB.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()));
      return;
    }
    finally
    {
      .Stack tmp105_103 = localStack;
      tmp105_103.pop$com$bulletphysics$linearmath$Transform();
      tmp105_103.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean testAngularLimitMotor(int axis_index)
  {
    float angle = VectorUtil.getCoord(this.calculatedAxisAngleDiff, axis_index);
    this.angularLimits[axis_index].testLimitValue(angle);
    return this.angularLimits[axis_index].needApplyTorques();
  }
  
  public void buildJacobian()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.linearLimits.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
      for (int local_i = 0; local_i < 3; local_i++) {
        this.angularLimits[local_i].accumulatedImpulse = 0.0F;
      }
      calculateTransforms();
      Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
      calcAnchorPos();
      Vector3f pivotAInW = localStack.get$javax$vecmath$Vector3f(this.anchorPos);
      Vector3f pivotBInW = localStack.get$javax$vecmath$Vector3f(this.anchorPos);
      Vector3f normalWorld = localStack.get$javax$vecmath$Vector3f();
      for (int local_i1 = 0; local_i1 < 3; local_i1++) {
        if (this.linearLimits.isLimited(local_i1))
        {
          if (this.useLinearReferenceFrameA) {
            this.calculatedTransformA.basis.getColumn(local_i1, normalWorld);
          } else {
            this.calculatedTransformB.basis.getColumn(local_i1, normalWorld);
          }
          buildLinearJacobian(local_i1, normalWorld, pivotAInW, pivotBInW);
        }
      }
      for (int local_i1 = 0; local_i1 < 3; local_i1++) {
        if (testAngularLimitMotor(local_i1))
        {
          getAxis(local_i1, normalWorld);
          buildAngularJacobian(local_i1, normalWorld);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void solveConstraint(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.timeStep = timeStep;
      Vector3f pointInA = localStack.get$javax$vecmath$Vector3f(this.calculatedTransformA.origin);
      Vector3f pointInB = localStack.get$javax$vecmath$Vector3f(this.calculatedTransformB.origin);
      Vector3f linear_axis = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < 3; local_i++) {
        if (this.linearLimits.isLimited(local_i))
        {
          float jacDiagABInv = 1.0F / this.jacLinear[local_i].getDiagonal();
          if (this.useLinearReferenceFrameA) {
            this.calculatedTransformA.basis.getColumn(local_i, linear_axis);
          } else {
            this.calculatedTransformB.basis.getColumn(local_i, linear_axis);
          }
          this.linearLimits.solveLinearAxis(this.timeStep, jacDiagABInv, this.rbA, pointInA, this.rbB, pointInB, local_i, linear_axis, this.anchorPos);
        }
      }
      Vector3f angular_axis = localStack.get$javax$vecmath$Vector3f();
      for (local_i = 0; local_i < 3; local_i++) {
        if (this.angularLimits[local_i].needApplyTorques())
        {
          getAxis(local_i, angular_axis);
          float angularJacDiagABInv = 1.0F / this.jacAng[local_i].getDiagonal();
          this.angularLimits[local_i].solveAngularLimits(this.timeStep, angular_axis, angularJacDiagABInv, this.rbA, this.rbB);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void updateRHS(float timeStep) {}
  
  public Vector3f getAxis(int axis_index, Vector3f out)
  {
    out.set(this.calculatedAxis[axis_index]);
    return out;
  }
  
  public float getAngle(int axis_index)
  {
    return VectorUtil.getCoord(this.calculatedAxisAngleDiff, axis_index);
  }
  
  public Transform getCalculatedTransformA(Transform out)
  {
    out.set(this.calculatedTransformA);
    return out;
  }
  
  public Transform getCalculatedTransformB(Transform out)
  {
    out.set(this.calculatedTransformB);
    return out;
  }
  
  public Transform getFrameOffsetA(Transform out)
  {
    out.set(this.frameInA);
    return out;
  }
  
  public Transform getFrameOffsetB(Transform out)
  {
    out.set(this.frameInB);
    return out;
  }
  
  public void setLinearLowerLimit(Vector3f linearLower)
  {
    this.linearLimits.lowerLimit.set(linearLower);
  }
  
  public void setLinearUpperLimit(Vector3f linearUpper)
  {
    this.linearLimits.upperLimit.set(linearUpper);
  }
  
  public void setAngularLowerLimit(Vector3f angularLower)
  {
    this.angularLimits[0].loLimit = angularLower.field_615;
    this.angularLimits[1].loLimit = angularLower.field_616;
    this.angularLimits[2].loLimit = angularLower.field_617;
  }
  
  public void setAngularUpperLimit(Vector3f angularUpper)
  {
    this.angularLimits[0].hiLimit = angularUpper.field_615;
    this.angularLimits[1].hiLimit = angularUpper.field_616;
    this.angularLimits[2].hiLimit = angularUpper.field_617;
  }
  
  public RotationalLimitMotor getRotationalLimitMotor(int index)
  {
    return this.angularLimits[index];
  }
  
  public TranslationalLimitMotor getTranslationalLimitMotor()
  {
    return this.linearLimits;
  }
  
  public void setLimit(int axis, float local_lo, float local_hi)
  {
    if (axis < 3)
    {
      VectorUtil.setCoord(this.linearLimits.lowerLimit, axis, local_lo);
      VectorUtil.setCoord(this.linearLimits.upperLimit, axis, local_hi);
    }
    else
    {
      this.angularLimits[(axis - 3)].loLimit = local_lo;
      this.angularLimits[(axis - 3)].hiLimit = local_hi;
    }
  }
  
  public boolean isLimited(int limitIndex)
  {
    if (limitIndex < 3) {
      return this.linearLimits.isLimited(limitIndex);
    }
    return this.angularLimits[(limitIndex - 3)].isLimited();
  }
  
  public void calcAnchorPos()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      float imA = this.rbA.getInvMass();
      float imB = this.rbB.getInvMass();
      float weight;
      float weight;
      if (imB == 0.0F) {
        weight = 1.0F;
      } else {
        weight = imA / (imA + imB);
      }
      Vector3f local_pA = this.calculatedTransformA.origin;
      Vector3f local_pB = this.calculatedTransformB.origin;
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp1.scale(weight, local_pA);
      tmp2.scale(1.0F - weight, local_pB);
      this.anchorPos.add(tmp1, tmp2);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.Generic6DofConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */