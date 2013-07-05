/*    */ package com.bulletphysics.dynamics.constraintsolver;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class ConstraintPersistentData
/*    */ {
/* 38 */   public float appliedImpulse = 0.0F;
/* 39 */   public float prevAppliedImpulse = 0.0F;
/* 40 */   public float accumulatedTangentImpulse0 = 0.0F;
/* 41 */   public float accumulatedTangentImpulse1 = 0.0F;
/*    */ 
/* 43 */   public float jacDiagABInv = 0.0F;
/*    */   public float jacDiagABInvTangent0;
/*    */   public float jacDiagABInvTangent1;
/* 46 */   public int persistentLifeTime = 0;
/* 47 */   public float restitution = 0.0F;
/* 48 */   public float friction = 0.0F;
/* 49 */   public float penetration = 0.0F;
/* 50 */   public final Vector3f frictionWorldTangential0 = new Vector3f();
/* 51 */   public final Vector3f frictionWorldTangential1 = new Vector3f();
/*    */ 
/* 53 */   public final Vector3f frictionAngularComponent0A = new Vector3f();
/* 54 */   public final Vector3f frictionAngularComponent0B = new Vector3f();
/* 55 */   public final Vector3f frictionAngularComponent1A = new Vector3f();
/* 56 */   public final Vector3f frictionAngularComponent1B = new Vector3f();
/*    */ 
/* 59 */   public final Vector3f angularComponentA = new Vector3f();
/* 60 */   public final Vector3f angularComponentB = new Vector3f();
/*    */ 
/* 62 */   public ContactSolverFunc contactSolverFunc = null;
/* 63 */   public ContactSolverFunc frictionSolverFunc = null;
/*    */ 
/*    */   public void reset() {
/* 66 */     this.appliedImpulse = 0.0F;
/* 67 */     this.prevAppliedImpulse = 0.0F;
/* 68 */     this.accumulatedTangentImpulse0 = 0.0F;
/* 69 */     this.accumulatedTangentImpulse1 = 0.0F;
/*    */ 
/* 71 */     this.jacDiagABInv = 0.0F;
/* 72 */     this.jacDiagABInvTangent0 = 0.0F;
/* 73 */     this.jacDiagABInvTangent1 = 0.0F;
/* 74 */     this.persistentLifeTime = 0;
/* 75 */     this.restitution = 0.0F;
/* 76 */     this.friction = 0.0F;
/* 77 */     this.penetration = 0.0F;
/* 78 */     this.frictionWorldTangential0.set(0.0F, 0.0F, 0.0F);
/* 79 */     this.frictionWorldTangential1.set(0.0F, 0.0F, 0.0F);
/*    */ 
/* 81 */     this.frictionAngularComponent0A.set(0.0F, 0.0F, 0.0F);
/* 82 */     this.frictionAngularComponent0B.set(0.0F, 0.0F, 0.0F);
/* 83 */     this.frictionAngularComponent1A.set(0.0F, 0.0F, 0.0F);
/* 84 */     this.frictionAngularComponent1B.set(0.0F, 0.0F, 0.0F);
/*    */ 
/* 86 */     this.angularComponentA.set(0.0F, 0.0F, 0.0F);
/* 87 */     this.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*    */ 
/* 89 */     this.contactSolverFunc = null;
/* 90 */     this.frictionSolverFunc = null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConstraintPersistentData
 * JD-Core Version:    0.6.2
 */