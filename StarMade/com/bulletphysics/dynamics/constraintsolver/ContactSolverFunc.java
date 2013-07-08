package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.dynamics.RigidBody;

public abstract class ContactSolverFunc
{
  public abstract float resolveContact(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactSolverFunc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */