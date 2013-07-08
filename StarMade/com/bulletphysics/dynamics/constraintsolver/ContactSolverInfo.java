/*  1:   */package com.bulletphysics.dynamics.constraintsolver;
/*  2:   */
/* 31:   */public class ContactSolverInfo
/* 32:   */{
/* 33:33 */  public float tau = 0.6F;
/* 34:34 */  public float damping = 1.0F;
/* 35:35 */  public float friction = 0.3F;
/* 36:   */  public float timeStep;
/* 37:37 */  public float restitution = 0.0F;
/* 38:38 */  public int numIterations = 10;
/* 39:39 */  public float maxErrorReduction = 20.0F;
/* 40:40 */  public float sor = 1.3F;
/* 41:41 */  public float erp = 0.2F;
/* 42:42 */  public float erp2 = 0.1F;
/* 43:43 */  public boolean splitImpulse = false;
/* 44:44 */  public float splitImpulsePenetrationThreshold = -0.02F;
/* 45:45 */  public float linearSlop = 0.0F;
/* 46:46 */  public float warmstartingFactor = 0.85F;
/* 47:   */  
/* 48:48 */  public int solverMode = 13;
/* 49:   */  
/* 50:   */  public ContactSolverInfo() {}
/* 51:   */  
/* 52:   */  public ContactSolverInfo(ContactSolverInfo g)
/* 53:   */  {
/* 54:54 */    this.tau = g.tau;
/* 55:55 */    this.damping = g.damping;
/* 56:56 */    this.friction = g.friction;
/* 57:57 */    this.timeStep = g.timeStep;
/* 58:58 */    this.restitution = g.restitution;
/* 59:59 */    this.numIterations = g.numIterations;
/* 60:60 */    this.maxErrorReduction = g.maxErrorReduction;
/* 61:61 */    this.sor = g.sor;
/* 62:62 */    this.erp = g.erp;
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */