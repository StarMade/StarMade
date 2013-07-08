package com.bulletphysics.dynamics;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
import javax.vecmath.Vector3f;

public abstract class DynamicsWorld
  extends CollisionWorld
{
  protected InternalTickCallback internalTickCallback;
  protected Object worldUserInfo;
  protected final ContactSolverInfo solverInfo = new ContactSolverInfo();
  
  public DynamicsWorld(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration)
  {
    super(dispatcher, broadphasePairCache, collisionConfiguration);
  }
  
  public final int stepSimulation(float timeStep)
  {
    return stepSimulation(timeStep, 1, 0.01666667F);
  }
  
  public final int stepSimulation(float timeStep, int maxSubSteps)
  {
    return stepSimulation(timeStep, maxSubSteps, 0.01666667F);
  }
  
  public abstract int stepSimulation(float paramFloat1, int paramInt, float paramFloat2);
  
  public abstract void debugDrawWorld();
  
  public final void addConstraint(TypedConstraint constraint)
  {
    addConstraint(constraint, false);
  }
  
  public void addConstraint(TypedConstraint constraint, boolean disableCollisionsBetweenLinkedBodies) {}
  
  public void removeConstraint(TypedConstraint constraint) {}
  
  public void addAction(ActionInterface action) {}
  
  public void removeAction(ActionInterface action) {}
  
  public void addVehicle(RaycastVehicle vehicle) {}
  
  public void removeVehicle(RaycastVehicle vehicle) {}
  
  public abstract void setGravity(Vector3f paramVector3f);
  
  public abstract Vector3f getGravity(Vector3f paramVector3f);
  
  public abstract void addRigidBody(RigidBody paramRigidBody);
  
  public abstract void removeRigidBody(RigidBody paramRigidBody);
  
  public abstract void setConstraintSolver(ConstraintSolver paramConstraintSolver);
  
  public abstract ConstraintSolver getConstraintSolver();
  
  public int getNumConstraints()
  {
    return 0;
  }
  
  public TypedConstraint getConstraint(int index)
  {
    return null;
  }
  
  public int getNumActions()
  {
    return 0;
  }
  
  public ActionInterface getAction(int index)
  {
    return null;
  }
  
  public abstract DynamicsWorldType getWorldType();
  
  public abstract void clearForces();
  
  public void setInternalTickCallback(InternalTickCallback local_cb, Object worldUserInfo)
  {
    this.internalTickCallback = local_cb;
    this.worldUserInfo = worldUserInfo;
  }
  
  public void setWorldUserInfo(Object worldUserInfo)
  {
    this.worldUserInfo = worldUserInfo;
  }
  
  public Object getWorldUserInfo()
  {
    return this.worldUserInfo;
  }
  
  public ContactSolverInfo getSolverInfo()
  {
    return this.solverInfo;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.DynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */