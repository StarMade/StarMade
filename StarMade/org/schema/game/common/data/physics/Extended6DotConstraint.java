package org.schema.game.common.data.physics;

import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.Generic6DofConstraint;
import com.bulletphysics.dynamics.constraintsolver.JacobianEntry;
import com.bulletphysics.dynamics.constraintsolver.RotationalLimitMotor;
import com.bulletphysics.dynamics.constraintsolver.TranslationalLimitMotor;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.VectorUtil;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.schine.network.StateInterface;

public class Extended6DotConstraint
  extends Generic6DofConstraint
{
  private StateInterface state;
  protected final RotationalLimitMotorAbsVelocity[] angularLimits = { new RotationalLimitMotorAbsVelocity(), new RotationalLimitMotorAbsVelocity(), new RotationalLimitMotorAbsVelocity() };
  public final Vector3f linearJointAxis = new Vector3f();
  public final Vector3f field_77 = new Vector3f();
  public final Vector3f field_78 = new Vector3f();
  public final Vector3f m_0MinvJt = new Vector3f();
  public final Vector3f m_1MinvJt = new Vector3f();
  
  private static float getMatrixElem(Matrix3f paramMatrix3f, int paramInt)
  {
    int i = paramInt % 3;
    paramInt /= 3;
    return paramMatrix3f.getElement(i, paramInt);
  }
  
  private static boolean matrixToEulerXYZ(Matrix3f paramMatrix3f, Vector3f paramVector3f)
  {
    if (getMatrixElem(paramMatrix3f, 2) < 1.0F)
    {
      if (getMatrixElem(paramMatrix3f, 2) > -1.0F)
      {
        paramVector3f.field_615 = ((float)Math.atan2(-getMatrixElem(paramMatrix3f, 5), getMatrixElem(paramMatrix3f, 8)));
        paramVector3f.field_616 = ((float)Math.asin(getMatrixElem(paramMatrix3f, 2)));
        paramVector3f.field_617 = ((float)Math.atan2(-getMatrixElem(paramMatrix3f, 1), getMatrixElem(paramMatrix3f, 0)));
        return true;
      }
      System.err.println("WARNING.  Not unique.  XA - ZA = -atan2(r10,r11)");
      paramVector3f.field_615 = (-(float)Math.atan2(getMatrixElem(paramMatrix3f, 3), getMatrixElem(paramMatrix3f, 4)));
      paramVector3f.field_616 = -1.570796F;
      paramVector3f.field_617 = 0.0F;
      return false;
    }
    System.err.println("WARNING.  Not unique.  XAngle + ZAngle = atan2(r10,r11)");
    paramVector3f.field_615 = ((float)Math.atan2(getMatrixElem(paramMatrix3f, 3), getMatrixElem(paramMatrix3f, 4)));
    paramVector3f.field_616 = 1.570796F;
    paramVector3f.field_617 = 0.0F;
    return false;
  }
  
  public Extended6DotConstraint(StateInterface paramStateInterface, RigidBody paramRigidBody1, RigidBody paramRigidBody2, Transform paramTransform1, Transform paramTransform2, boolean paramBoolean)
  {
    super(paramRigidBody1, paramRigidBody2, paramTransform1, paramTransform2, paramBoolean);
    this.state = paramStateInterface;
  }
  
  protected void buildAngularJacobian(int paramInt, Vector3f paramVector3f)
  {
    Matrix3f localMatrix3f1;
    (localMatrix3f1 = this.rbA.getCenterOfMassTransform(new Transform()).basis).transpose();
    Matrix3f localMatrix3f2;
    (localMatrix3f2 = this.rbB.getCenterOfMassTransform(new Transform()).basis).transpose();
    if (!checkJacobian(paramVector3f, localMatrix3f1, localMatrix3f2, this.rbA.getInvInertiaDiagLocal(new Vector3f()), this.rbB.getInvInertiaDiagLocal(new Vector3f())))
    {
      System.err.println("Exception: Jacobian Entry init failed!");
      return;
    }
    super.buildAngularJacobian(paramInt, paramVector3f);
  }
  
  public void buildJacobian()
  {
    this.linearLimits.accumulatedImpulse.set(0.0F, 0.0F, 0.0F);
    for (int i = 0; i < 3; i++) {
      this.angularLimits[i].accumulatedImpulse = 0.0F;
    }
    calculateTransforms();
    new Vector3f();
    calcAnchorPos();
    Vector3f localVector3f1 = new Vector3f(this.anchorPos);
    Vector3f localVector3f2 = new Vector3f(this.anchorPos);
    Vector3f localVector3f3 = new Vector3f();
    for (int j = 0; j < 3; j++) {
      if (this.linearLimits.isLimited(j))
      {
        if (this.useLinearReferenceFrameA) {
          this.calculatedTransformA.basis.getColumn(j, localVector3f3);
        } else {
          this.calculatedTransformB.basis.getColumn(j, localVector3f3);
        }
        buildLinearJacobian(j, localVector3f3, localVector3f1, localVector3f2);
      }
    }
    for (j = 0; j < 3; j++) {
      if (testAngularLimitMotor(j))
      {
        getAxis(j, localVector3f3);
        buildAngularJacobian(j, localVector3f3);
      }
    }
  }
  
  protected void calculateAngleInfo()
  {
    Object localObject1 = new Matrix3f();
    Object localObject2 = new Matrix3f();
    ((Matrix3f)localObject1).set(this.calculatedTransformA.basis);
    MatrixUtil.invert((Matrix3f)localObject1);
    ((Matrix3f)localObject2).mul((Matrix3f)localObject1, this.calculatedTransformB.basis);
    matrixToEulerXYZ((Matrix3f)localObject2, this.calculatedAxisAngleDiff);
    localObject1 = new Vector3f();
    this.calculatedTransformB.basis.getColumn(0, (Vector3f)localObject1);
    localObject2 = new Vector3f();
    this.calculatedTransformA.basis.getColumn(2, (Vector3f)localObject2);
    this.calculatedAxis[1].cross((Vector3f)localObject2, (Vector3f)localObject1);
    this.calculatedAxis[0].cross(this.calculatedAxis[1], (Vector3f)localObject2);
    this.calculatedAxis[2].cross((Vector3f)localObject1, this.calculatedAxis[1]);
  }
  
  public boolean checkJacobian(Vector3f paramVector3f1, Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2, Vector3f paramVector3f2, Vector3f paramVector3f3)
  {
    this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
    this.field_77.set(paramVector3f1);
    paramMatrix3f1.transform(this.field_77);
    this.field_78.set(paramVector3f1);
    this.field_78.negate();
    paramMatrix3f2.transform(this.field_78);
    VectorUtil.mul(this.m_0MinvJt, paramVector3f2, this.field_77);
    VectorUtil.mul(this.m_1MinvJt, paramVector3f3, this.field_78);
    return this.m_0MinvJt.dot(this.field_77) + this.m_1MinvJt.dot(this.field_78) > 0.0F;
  }
  
  public RotationalLimitMotor getRotationalLimitMotor(int paramInt)
  {
    return this.angularLimits[paramInt];
  }
  
  public boolean isLimited(int paramInt)
  {
    if (paramInt < 3) {
      return this.linearLimits.isLimited(paramInt);
    }
    return this.angularLimits[(paramInt - 3)].isLimited();
  }
  
  public void matrixToEuler(Matrix3f paramMatrix3f, Vector3f paramVector3f)
  {
    float f1;
    float f3;
    if (FastMath.a(f2 = (float)Math.cos(f1 = (float)-Math.asin(paramMatrix3f.m02))) > 0.005D)
    {
      f3 = paramMatrix3f.m22 / f2;
      f4 = (float)Math.atan2(-paramMatrix3f.m12 / f2, f3);
      f3 = paramMatrix3f.m00 / f2;
      f2 = (float)Math.atan2(-paramMatrix3f.m01 / f2, f3);
    }
    else
    {
      f4 = 0.0F;
      f3 = paramMatrix3f.m11;
      f2 = (float)Math.atan2(paramMatrix3f.m10, f3);
    }
    float f4 = FastMath.a5(f4, 0.0F, 6.283186F);
    paramMatrix3f = FastMath.a5(f1, 0.0F, 6.283186F);
    float f2 = FastMath.a5(f2, 0.0F, 6.283186F);
    paramVector3f.field_615 = f4;
    paramVector3f.field_616 = paramMatrix3f;
    paramVector3f.field_617 = f2;
    System.err.println("Euler: " + paramVector3f);
  }
  
  public void setAngularLowerLimit(Vector3f paramVector3f)
  {
    this.angularLimits[0].loLimit = paramVector3f.field_615;
    this.angularLimits[1].loLimit = paramVector3f.field_616;
    this.angularLimits[2].loLimit = paramVector3f.field_617;
  }
  
  public void setAngularUpperLimit(Vector3f paramVector3f)
  {
    this.angularLimits[0].hiLimit = paramVector3f.field_615;
    this.angularLimits[1].hiLimit = paramVector3f.field_616;
    this.angularLimits[2].hiLimit = paramVector3f.field_617;
  }
  
  public void setLimit(int paramInt, float paramFloat1, float paramFloat2)
  {
    if (paramInt < 3)
    {
      VectorUtil.setCoord(this.linearLimits.lowerLimit, paramInt, paramFloat1);
      VectorUtil.setCoord(this.linearLimits.upperLimit, paramInt, paramFloat2);
      return;
    }
    this.angularLimits[(paramInt - 3)].loLimit = paramFloat1;
    this.angularLimits[(paramInt - 3)].hiLimit = paramFloat2;
  }
  
  public void solveConstraint(float paramFloat)
  {
    this.timeStep = paramFloat;
    Vector3f localVector3f1 = new Vector3f(this.calculatedTransformA.origin);
    Vector3f localVector3f2 = new Vector3f(this.calculatedTransformB.origin);
    Vector3f localVector3f3 = new Vector3f();
    for (paramFloat = 0; paramFloat < 3; paramFloat++) {
      if (this.linearLimits.isLimited(paramFloat))
      {
        float f2 = 1.0F / this.jacLinear[paramFloat].getDiagonal();
        if (this.useLinearReferenceFrameA) {
          this.calculatedTransformA.basis.getColumn(paramFloat, localVector3f3);
        } else {
          this.calculatedTransformB.basis.getColumn(paramFloat, localVector3f3);
        }
        this.linearLimits.solveLinearAxis(this.timeStep, f2, this.rbA, localVector3f1, this.rbB, localVector3f2, paramFloat, localVector3f3, this.anchorPos);
      }
    }
    localVector3f1 = new Vector3f();
    for (paramFloat = 0; paramFloat < 3; paramFloat++) {
      if (this.angularLimits[paramFloat].needApplyTorques())
      {
        getAxis(paramFloat, localVector3f1);
        float f1;
        if (this.jacAng[paramFloat].getDiagonal() == 0.0F) {
          f1 = 0.1F;
        } else {
          f1 = 1.0F / this.jacAng[paramFloat].getDiagonal();
        }
        this.angularLimits[paramFloat].solveAngularLimits(this.timeStep, localVector3f1, f1, this.rbA, this.rbB);
      }
    }
  }
  
  public boolean testAngularLimitMotor(int paramInt)
  {
    float f = VectorUtil.getCoord(this.calculatedAxisAngleDiff, paramInt);
    this.angularLimits[paramInt].testLimitValue(f);
    return this.angularLimits[paramInt].needApplyTorques();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.Extended6DotConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */