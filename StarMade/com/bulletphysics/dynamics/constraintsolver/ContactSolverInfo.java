/*    */ package com.bulletphysics.dynamics.constraintsolver;
/*    */ 
/*    */ public class ContactSolverInfo
/*    */ {
/* 33 */   public float tau = 0.6F;
/* 34 */   public float damping = 1.0F;
/* 35 */   public float friction = 0.3F;
/*    */   public float timeStep;
/* 37 */   public float restitution = 0.0F;
/* 38 */   public int numIterations = 10;
/* 39 */   public float maxErrorReduction = 20.0F;
/* 40 */   public float sor = 1.3F;
/* 41 */   public float erp = 0.2F;
/* 42 */   public float erp2 = 0.1F;
/* 43 */   public boolean splitImpulse = false;
/* 44 */   public float splitImpulsePenetrationThreshold = -0.02F;
/* 45 */   public float linearSlop = 0.0F;
/* 46 */   public float warmstartingFactor = 0.85F;
/*    */ 
/* 48 */   public int solverMode = 13;
/*    */ 
/*    */   public ContactSolverInfo() {
/*    */   }
/*    */ 
/*    */   public ContactSolverInfo(ContactSolverInfo g) {
/* 54 */     this.tau = g.tau;
/* 55 */     this.damping = g.damping;
/* 56 */     this.friction = g.friction;
/* 57 */     this.timeStep = g.timeStep;
/* 58 */     this.restitution = g.restitution;
/* 59 */     this.numIterations = g.numIterations;
/* 60 */     this.maxErrorReduction = g.maxErrorReduction;
/* 61 */     this.sor = g.sor;
/* 62 */     this.erp = g.erp;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo
 * JD-Core Version:    0.6.2
 */