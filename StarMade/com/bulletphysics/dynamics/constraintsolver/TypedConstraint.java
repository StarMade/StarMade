package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics.dynamics.RigidBody;
import javax.vecmath.Vector3f;

public abstract class TypedConstraint
{
  private static RigidBody s_fixed;
  private int userConstraintType = -1;
  private int userConstraintId = -1;
  private TypedConstraintType constraintType;
  protected RigidBody rbA;
  protected RigidBody rbB;
  protected float appliedImpulse = 0.0F;
  
  private static synchronized RigidBody getFixed()
  {
    if (s_fixed == null) {
      s_fixed = new RigidBody(0.0F, null, null);
    }
    return s_fixed;
  }
  
  public TypedConstraint(TypedConstraintType type)
  {
    this(type, getFixed(), getFixed());
  }
  
  public TypedConstraint(TypedConstraintType type, RigidBody rbA)
  {
    this(type, rbA, getFixed());
  }
  
  public TypedConstraint(TypedConstraintType type, RigidBody rbA, RigidBody rbB)
  {
    this.constraintType = type;
    this.rbA = rbA;
    this.rbB = rbB;
    getFixed().setMassProps(0.0F, new Vector3f(0.0F, 0.0F, 0.0F));
  }
  
  public abstract void buildJacobian();
  
  public abstract void solveConstraint(float paramFloat);
  
  public RigidBody getRigidBodyA()
  {
    return this.rbA;
  }
  
  public RigidBody getRigidBodyB()
  {
    return this.rbB;
  }
  
  public int getUserConstraintType()
  {
    return this.userConstraintType;
  }
  
  public void setUserConstraintType(int userConstraintType)
  {
    this.userConstraintType = userConstraintType;
  }
  
  public int getUserConstraintId()
  {
    return this.userConstraintId;
  }
  
  public int getUid()
  {
    return this.userConstraintId;
  }
  
  public void setUserConstraintId(int userConstraintId)
  {
    this.userConstraintId = userConstraintId;
  }
  
  public float getAppliedImpulse()
  {
    return this.appliedImpulse;
  }
  
  public TypedConstraintType getConstraintType()
  {
    return this.constraintType;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.TypedConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */