package com.bulletphysics.dynamics;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public class SimpleDynamicsWorld
  extends DynamicsWorld
{
  protected ConstraintSolver constraintSolver;
  protected boolean ownsConstraintSolver;
  protected final Vector3f gravity = new Vector3f(0.0F, 0.0F, -10.0F);
  
  public SimpleDynamicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver, CollisionConfiguration collisionConfiguration)
  {
    super(dispatcher, pairCache, collisionConfiguration);
    this.constraintSolver = constraintSolver;
    this.ownsConstraintSolver = false;
  }
  
  protected void predictUnconstraintMotion(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
        RigidBody body = RigidBody.upcast(colObj);
        if ((body != null) && (!body.isStaticObject()) && (body.isActive()))
        {
          body.applyGravity();
          body.integrateVelocities(timeStep);
          body.applyDamping(timeStep);
          body.predictIntegratedTransform(timeStep, body.getInterpolationWorldTransform(tmpTrans));
        }
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  protected void integrateTransforms(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
        RigidBody body = RigidBody.upcast(colObj);
        if ((body != null) && (body.isActive()) && (!body.isStaticObject()))
        {
          body.predictIntegratedTransform(timeStep, predictedTrans);
          body.proceedToTransform(predictedTrans);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public int stepSimulation(float timeStep, int maxSubSteps, float fixedTimeStep)
  {
    predictUnconstraintMotion(timeStep);
    DispatcherInfo dispatchInfo = getDispatchInfo();
    dispatchInfo.timeStep = timeStep;
    dispatchInfo.stepCount = 0;
    dispatchInfo.debugDraw = getDebugDrawer();
    performDiscreteCollisionDetection();
    int numManifolds = this.dispatcher1.getNumManifolds();
    if (numManifolds != 0)
    {
      ObjectArrayList<PersistentManifold> manifoldPtr = ((CollisionDispatcher)this.dispatcher1).getInternalManifoldPointer();
      ContactSolverInfo infoGlobal = new ContactSolverInfo();
      infoGlobal.timeStep = timeStep;
      this.constraintSolver.prepareSolve(0, numManifolds);
      this.constraintSolver.solveGroup(null, 0, manifoldPtr, 0, numManifolds, null, 0, 0, infoGlobal, this.debugDrawer, this.dispatcher1);
      this.constraintSolver.allSolved(infoGlobal, this.debugDrawer);
    }
    integrateTransforms(timeStep);
    updateAabbs();
    synchronizeMotionStates();
    clearForces();
    return 1;
  }
  
  public void clearForces()
  {
    for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
    {
      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
      RigidBody body = RigidBody.upcast(colObj);
      if (body != null) {
        body.clearForces();
      }
    }
  }
  
  public void setGravity(Vector3f gravity)
  {
    this.gravity.set(gravity);
    for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
    {
      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
      RigidBody body = RigidBody.upcast(colObj);
      if (body != null) {
        body.setGravity(gravity);
      }
    }
  }
  
  public Vector3f getGravity(Vector3f out)
  {
    out.set(this.gravity);
    return out;
  }
  
  public void addRigidBody(RigidBody body)
  {
    body.setGravity(this.gravity);
    if (body.getCollisionShape() != null) {
      addCollisionObject(body);
    }
  }
  
  public void removeRigidBody(RigidBody body)
  {
    removeCollisionObject(body);
  }
  
  public void updateAabbs()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
      Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
        RigidBody body = RigidBody.upcast(colObj);
        if ((body != null) && (body.isActive()) && (!body.isStaticObject()))
        {
          colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
          BroadphaseInterface local_bp = getBroadphase();
          local_bp.setAabb(body.getBroadphaseHandle(), minAabb, maxAabb, this.dispatcher1);
        }
      }
      return;
    }
    finally
    {
      .Stack tmp154_152 = localStack;
      tmp154_152.pop$com$bulletphysics$linearmath$Transform();
      tmp154_152.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void synchronizeMotionStates()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
        RigidBody body = RigidBody.upcast(colObj);
        if ((body != null) && (body.getMotionState() != null) && (body.getActivationState() != 2)) {
          body.getMotionState().setWorldTransform(body.getWorldTransform(tmpTrans));
        }
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void setConstraintSolver(ConstraintSolver solver)
  {
    if (this.ownsConstraintSolver) {}
    this.ownsConstraintSolver = false;
    this.constraintSolver = solver;
  }
  
  public ConstraintSolver getConstraintSolver()
  {
    return this.constraintSolver;
  }
  
  public void debugDrawWorld() {}
  
  public DynamicsWorldType getWorldType()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.SimpleDynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */