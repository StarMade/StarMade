package com.bulletphysics.dynamics.constraintsolver;

public class ContactSolverInfo
{
  public float tau = 0.6F;
  public float damping = 1.0F;
  public float friction = 0.3F;
  public float timeStep;
  public float restitution = 0.0F;
  public int numIterations = 10;
  public float maxErrorReduction = 20.0F;
  public float sor = 1.3F;
  public float erp = 0.2F;
  public float erp2 = 0.1F;
  public boolean splitImpulse = false;
  public float splitImpulsePenetrationThreshold = -0.02F;
  public float linearSlop = 0.0F;
  public float warmstartingFactor = 0.85F;
  public int solverMode = 13;
  
  public ContactSolverInfo() {}
  
  public ContactSolverInfo(ContactSolverInfo local_g)
  {
    this.tau = local_g.tau;
    this.damping = local_g.damping;
    this.friction = local_g.friction;
    this.timeStep = local_g.timeStep;
    this.restitution = local_g.restitution;
    this.numIterations = local_g.numIterations;
    this.maxErrorReduction = local_g.maxErrorReduction;
    this.sor = local_g.sor;
    this.erp = local_g.erp;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */