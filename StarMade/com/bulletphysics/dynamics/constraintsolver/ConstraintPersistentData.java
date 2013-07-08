/*  1:   */package com.bulletphysics.dynamics.constraintsolver;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 36:   */public class ConstraintPersistentData
/* 37:   */{
/* 38:38 */  public float appliedImpulse = 0.0F;
/* 39:39 */  public float prevAppliedImpulse = 0.0F;
/* 40:40 */  public float accumulatedTangentImpulse0 = 0.0F;
/* 41:41 */  public float accumulatedTangentImpulse1 = 0.0F;
/* 42:   */  
/* 43:43 */  public float jacDiagABInv = 0.0F;
/* 44:   */  public float jacDiagABInvTangent0;
/* 45:   */  public float jacDiagABInvTangent1;
/* 46:46 */  public int persistentLifeTime = 0;
/* 47:47 */  public float restitution = 0.0F;
/* 48:48 */  public float friction = 0.0F;
/* 49:49 */  public float penetration = 0.0F;
/* 50:50 */  public final Vector3f frictionWorldTangential0 = new Vector3f();
/* 51:51 */  public final Vector3f frictionWorldTangential1 = new Vector3f();
/* 52:   */  
/* 53:53 */  public final Vector3f frictionAngularComponent0A = new Vector3f();
/* 54:54 */  public final Vector3f frictionAngularComponent0B = new Vector3f();
/* 55:55 */  public final Vector3f frictionAngularComponent1A = new Vector3f();
/* 56:56 */  public final Vector3f frictionAngularComponent1B = new Vector3f();
/* 57:   */  
/* 59:59 */  public final Vector3f angularComponentA = new Vector3f();
/* 60:60 */  public final Vector3f angularComponentB = new Vector3f();
/* 61:   */  
/* 62:62 */  public ContactSolverFunc contactSolverFunc = null;
/* 63:63 */  public ContactSolverFunc frictionSolverFunc = null;
/* 64:   */  
/* 65:   */  public void reset() {
/* 66:66 */    this.appliedImpulse = 0.0F;
/* 67:67 */    this.prevAppliedImpulse = 0.0F;
/* 68:68 */    this.accumulatedTangentImpulse0 = 0.0F;
/* 69:69 */    this.accumulatedTangentImpulse1 = 0.0F;
/* 70:   */    
/* 71:71 */    this.jacDiagABInv = 0.0F;
/* 72:72 */    this.jacDiagABInvTangent0 = 0.0F;
/* 73:73 */    this.jacDiagABInvTangent1 = 0.0F;
/* 74:74 */    this.persistentLifeTime = 0;
/* 75:75 */    this.restitution = 0.0F;
/* 76:76 */    this.friction = 0.0F;
/* 77:77 */    this.penetration = 0.0F;
/* 78:78 */    this.frictionWorldTangential0.set(0.0F, 0.0F, 0.0F);
/* 79:79 */    this.frictionWorldTangential1.set(0.0F, 0.0F, 0.0F);
/* 80:   */    
/* 81:81 */    this.frictionAngularComponent0A.set(0.0F, 0.0F, 0.0F);
/* 82:82 */    this.frictionAngularComponent0B.set(0.0F, 0.0F, 0.0F);
/* 83:83 */    this.frictionAngularComponent1A.set(0.0F, 0.0F, 0.0F);
/* 84:84 */    this.frictionAngularComponent1B.set(0.0F, 0.0F, 0.0F);
/* 85:   */    
/* 86:86 */    this.angularComponentA.set(0.0F, 0.0F, 0.0F);
/* 87:87 */    this.angularComponentB.set(0.0F, 0.0F, 0.0F);
/* 88:   */    
/* 89:89 */    this.contactSolverFunc = null;
/* 90:90 */    this.frictionSolverFunc = null;
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConstraintPersistentData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */