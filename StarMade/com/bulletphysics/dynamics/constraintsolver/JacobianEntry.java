package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class JacobianEntry
{
  public final Vector3f linearJointAxis = new Vector3f();
  public final Vector3f field_1830 = new Vector3f();
  public final Vector3f field_1831 = new Vector3f();
  public final Vector3f m_0MinvJt = new Vector3f();
  public final Vector3f m_1MinvJt = new Vector3f();
  public float Adiag;
  
  public void init(Matrix3f world2A, Matrix3f world2B, Vector3f rel_pos1, Vector3f rel_pos2, Vector3f jointAxis, Vector3f inertiaInvA, float massInvA, Vector3f inertiaInvB, float massInvB)
  {
    this.linearJointAxis.set(jointAxis);
    this.field_1830.cross(rel_pos1, this.linearJointAxis);
    world2A.transform(this.field_1830);
    this.field_1831.set(this.linearJointAxis);
    this.field_1831.negate();
    this.field_1831.cross(rel_pos2, this.field_1831);
    world2B.transform(this.field_1831);
    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.field_1830);
    VectorUtil.mul(this.m_1MinvJt, inertiaInvB, this.field_1831);
    this.Adiag = (massInvA + this.m_0MinvJt.dot(this.field_1830) + massInvB + this.m_1MinvJt.dot(this.field_1831));
    assert (this.Adiag > 0.0F);
  }
  
  public void init(Vector3f jointAxis, Matrix3f world2A, Matrix3f world2B, Vector3f inertiaInvA, Vector3f inertiaInvB)
  {
    this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
    this.field_1830.set(jointAxis);
    world2A.transform(this.field_1830);
    this.field_1831.set(jointAxis);
    this.field_1831.negate();
    world2B.transform(this.field_1831);
    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.field_1830);
    VectorUtil.mul(this.m_1MinvJt, inertiaInvB, this.field_1831);
    this.Adiag = (this.m_0MinvJt.dot(this.field_1830) + this.m_1MinvJt.dot(this.field_1831));
    assert (this.Adiag > 0.0F);
  }
  
  public void init(Vector3f axisInA, Vector3f axisInB, Vector3f inertiaInvA, Vector3f inertiaInvB)
  {
    this.linearJointAxis.set(0.0F, 0.0F, 0.0F);
    this.field_1830.set(axisInA);
    this.field_1831.set(axisInB);
    this.field_1831.negate();
    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.field_1830);
    VectorUtil.mul(this.m_1MinvJt, inertiaInvB, this.field_1831);
    this.Adiag = (this.m_0MinvJt.dot(this.field_1830) + this.m_1MinvJt.dot(this.field_1831));
    assert (this.Adiag > 0.0F);
  }
  
  public void init(Matrix3f world2A, Vector3f rel_pos1, Vector3f rel_pos2, Vector3f jointAxis, Vector3f inertiaInvA, float massInvA)
  {
    this.linearJointAxis.set(jointAxis);
    this.field_1830.cross(rel_pos1, jointAxis);
    world2A.transform(this.field_1830);
    this.field_1831.set(jointAxis);
    this.field_1831.negate();
    this.field_1831.cross(rel_pos2, this.field_1831);
    world2A.transform(this.field_1831);
    VectorUtil.mul(this.m_0MinvJt, inertiaInvA, this.field_1830);
    this.m_1MinvJt.set(0.0F, 0.0F, 0.0F);
    this.Adiag = (massInvA + this.m_0MinvJt.dot(this.field_1830));
    assert (this.Adiag > 0.0F);
  }
  
  public float getDiagonal()
  {
    return this.Adiag;
  }
  
  public float getNonDiagonal(JacobianEntry jacB, float massInvA)
  {
    JacobianEntry jacA = this;
    float lin = massInvA * jacA.linearJointAxis.dot(jacB.linearJointAxis);
    float ang = jacA.m_0MinvJt.dot(jacB.field_1830);
    return lin + ang;
  }
  
  public float getNonDiagonal(JacobianEntry arg1, float arg2, float arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      JacobianEntry jacA = this;
      Vector3f lin = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.mul(lin, jacA.linearJointAxis, jacB.linearJointAxis);
      Vector3f ang0 = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.mul(ang0, jacA.m_0MinvJt, jacB.field_1830);
      Vector3f ang1 = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.mul(ang1, jacA.m_1MinvJt, jacB.field_1831);
      Vector3f lin0 = localStack.get$javax$vecmath$Vector3f();
      lin0.scale(massInvA, lin);
      Vector3f lin1 = localStack.get$javax$vecmath$Vector3f();
      lin1.scale(massInvB, lin);
      Vector3f sum = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.add(sum, ang0, ang1, lin0, lin1);
      return sum.field_615 + sum.field_616 + sum.field_617;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public float getRelativeVelocity(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f linrel = localStack.get$javax$vecmath$Vector3f();
      linrel.sub(linvelA, linvelB);
      Vector3f angvela = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.mul(angvela, angvelA, this.field_1830);
      Vector3f angvelb = localStack.get$javax$vecmath$Vector3f();
      VectorUtil.mul(angvelb, angvelB, this.field_1831);
      VectorUtil.mul(linrel, linrel, this.linearJointAxis);
      angvela.add(angvelb);
      angvela.add(linrel);
      float rel_vel2 = angvela.field_615 + angvela.field_616 + angvela.field_617;
      return rel_vel2 + 1.192093E-007F;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.JacobianEntry
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */