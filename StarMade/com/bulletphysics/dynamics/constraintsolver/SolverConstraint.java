/*  1:   */package com.bulletphysics.dynamics.constraintsolver;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 34:   */public class SolverConstraint
/* 35:   */{
/* 36:36 */  public final Vector3f relpos1CrossNormal = new Vector3f();
/* 37:37 */  public final Vector3f contactNormal = new Vector3f();
/* 38:   */  
/* 39:39 */  public final Vector3f relpos2CrossNormal = new Vector3f();
/* 40:40 */  public final Vector3f angularComponentA = new Vector3f();
/* 41:   */  
/* 42:42 */  public final Vector3f angularComponentB = new Vector3f();
/* 43:   */  public float appliedPushImpulse;
/* 44:   */  public float appliedImpulse;
/* 45:   */  public int solverBodyIdA;
/* 46:   */  public int solverBodyIdB;
/* 47:   */  public float friction;
/* 48:   */  public float restitution;
/* 49:   */  public float jacDiagABInv;
/* 50:   */  public float penetration;
/* 51:   */  public SolverConstraintType constraintType;
/* 52:   */  public int frictionIndex;
/* 53:   */  public Object originalContactPoint;
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SolverConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */