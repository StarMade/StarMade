package com.bulletphysics.dynamics.constraintsolver;

import javax.vecmath.Vector3f;

public class SolverConstraint
{
  public final Vector3f relpos1CrossNormal = new Vector3f();
  public final Vector3f contactNormal = new Vector3f();
  public final Vector3f relpos2CrossNormal = new Vector3f();
  public final Vector3f angularComponentA = new Vector3f();
  public final Vector3f angularComponentB = new Vector3f();
  public float appliedPushImpulse;
  public float appliedImpulse;
  public int solverBodyIdA;
  public int solverBodyIdB;
  public float friction;
  public float restitution;
  public float jacDiagABInv;
  public float penetration;
  public SolverConstraintType constraintType;
  public int frictionIndex;
  public Object originalContactPoint;
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SolverConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */