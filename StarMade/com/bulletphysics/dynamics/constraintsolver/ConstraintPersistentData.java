package com.bulletphysics.dynamics.constraintsolver;

import javax.vecmath.Vector3f;

public class ConstraintPersistentData
{
  public float appliedImpulse = 0.0F;
  public float prevAppliedImpulse = 0.0F;
  public float accumulatedTangentImpulse0 = 0.0F;
  public float accumulatedTangentImpulse1 = 0.0F;
  public float jacDiagABInv = 0.0F;
  public float jacDiagABInvTangent0;
  public float jacDiagABInvTangent1;
  public int persistentLifeTime = 0;
  public float restitution = 0.0F;
  public float friction = 0.0F;
  public float penetration = 0.0F;
  public final Vector3f frictionWorldTangential0 = new Vector3f();
  public final Vector3f frictionWorldTangential1 = new Vector3f();
  public final Vector3f frictionAngularComponent0A = new Vector3f();
  public final Vector3f frictionAngularComponent0B = new Vector3f();
  public final Vector3f frictionAngularComponent1A = new Vector3f();
  public final Vector3f frictionAngularComponent1B = new Vector3f();
  public final Vector3f angularComponentA = new Vector3f();
  public final Vector3f angularComponentB = new Vector3f();
  public ContactSolverFunc contactSolverFunc = null;
  public ContactSolverFunc frictionSolverFunc = null;
  
  public void reset()
  {
    this.appliedImpulse = 0.0F;
    this.prevAppliedImpulse = 0.0F;
    this.accumulatedTangentImpulse0 = 0.0F;
    this.accumulatedTangentImpulse1 = 0.0F;
    this.jacDiagABInv = 0.0F;
    this.jacDiagABInvTangent0 = 0.0F;
    this.jacDiagABInvTangent1 = 0.0F;
    this.persistentLifeTime = 0;
    this.restitution = 0.0F;
    this.friction = 0.0F;
    this.penetration = 0.0F;
    this.frictionWorldTangential0.set(0.0F, 0.0F, 0.0F);
    this.frictionWorldTangential1.set(0.0F, 0.0F, 0.0F);
    this.frictionAngularComponent0A.set(0.0F, 0.0F, 0.0F);
    this.frictionAngularComponent0B.set(0.0F, 0.0F, 0.0F);
    this.frictionAngularComponent1A.set(0.0F, 0.0F, 0.0F);
    this.frictionAngularComponent1B.set(0.0F, 0.0F, 0.0F);
    this.angularComponentA.set(0.0F, 0.0F, 0.0F);
    this.angularComponentB.set(0.0F, 0.0F, 0.0F);
    this.contactSolverFunc = null;
    this.frictionSolverFunc = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ConstraintPersistentData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */