/*    */ package com.bulletphysics.dynamics.constraintsolver;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class SolverConstraint
/*    */ {
/* 36 */   public final Vector3f relpos1CrossNormal = new Vector3f();
/* 37 */   public final Vector3f contactNormal = new Vector3f();
/*    */ 
/* 39 */   public final Vector3f relpos2CrossNormal = new Vector3f();
/* 40 */   public final Vector3f angularComponentA = new Vector3f();
/*    */ 
/* 42 */   public final Vector3f angularComponentB = new Vector3f();
/*    */   public float appliedPushImpulse;
/*    */   public float appliedImpulse;
/*    */   public int solverBodyIdA;
/*    */   public int solverBodyIdB;
/*    */   public float friction;
/*    */   public float restitution;
/*    */   public float jacDiagABInv;
/*    */   public float penetration;
/*    */   public SolverConstraintType constraintType;
/*    */   public int frictionIndex;
/*    */   public Object originalContactPoint;
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SolverConstraint
 * JD-Core Version:    0.6.2
 */