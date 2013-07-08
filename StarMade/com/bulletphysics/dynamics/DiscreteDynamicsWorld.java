package com.bulletphysics.dynamics;

import com.bulletphysics..Stack;
import com.bulletphysics.BulletGlobals;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
import com.bulletphysics.collision.dispatch.SimulationIslandManager;
import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
import com.bulletphysics.collision.dispatch.UnionFind;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
import com.bulletphysics.dynamics.vehicle.WheelInfo;
import com.bulletphysics.dynamics.vehicle.WheelInfo.RaycastInfo;
import com.bulletphysics.linearmath.CProfileManager;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.ScalarUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.util.Comparator;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class DiscreteDynamicsWorld
  extends DynamicsWorld
{
  protected ConstraintSolver constraintSolver;
  protected SimulationIslandManager islandManager;
  protected final ObjectArrayList<TypedConstraint> constraints = new ObjectArrayList();
  protected final Vector3f gravity = new Vector3f(0.0F, -10.0F, 0.0F);
  protected float localTime = 0.01666667F;
  protected boolean ownsIslandManager;
  protected boolean ownsConstraintSolver;
  protected ObjectArrayList<RaycastVehicle> vehicles = new ObjectArrayList();
  protected ObjectArrayList<ActionInterface> actions = new ObjectArrayList();
  protected int profileTimings = 0;
  private ObjectArrayList<TypedConstraint> sortedConstraints = new ObjectArrayList();
  private InplaceSolverIslandCallback solverCallback = new InplaceSolverIslandCallback(null);
  private static final Comparator<TypedConstraint> sortConstraintOnIslandPredicate = new Comparator()
  {
    public int compare(TypedConstraint lhs, TypedConstraint rhs)
    {
      int rIslandId0 = DiscreteDynamicsWorld.getConstraintIslandId(rhs);
      int lIslandId0 = DiscreteDynamicsWorld.getConstraintIslandId(lhs);
      return lIslandId0 < rIslandId0 ? -1 : 1;
    }
  };
  
  public DiscreteDynamicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver, CollisionConfiguration collisionConfiguration)
  {
    super(dispatcher, pairCache, collisionConfiguration);
    this.constraintSolver = constraintSolver;
    if (this.constraintSolver == null)
    {
      this.constraintSolver = new SequentialImpulseConstraintSolver();
      this.ownsConstraintSolver = true;
    }
    else
    {
      this.ownsConstraintSolver = false;
    }
    this.islandManager = new SimulationIslandManager();
    this.ownsIslandManager = true;
  }
  
  protected void saveKinematicState(float timeStep)
  {
    for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
    {
      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
      RigidBody body = RigidBody.upcast(colObj);
      if ((body != null) && (body.getActivationState() != 2) && (body.isKinematicObject())) {
        body.saveKinematicState(timeStep);
      }
    }
  }
  
  public void debugDrawWorld()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x8) != 0))
      {
        int numManifolds = getDispatcher().getNumManifolds();
        Vector3f color = localStack.get$javax$vecmath$Vector3f();
        color.set(0.0F, 0.0F, 0.0F);
        for (int local_i = 0; local_i < numManifolds; local_i++)
        {
          PersistentManifold contactManifold = getDispatcher().getManifoldByIndexInternal(local_i);
          int numContacts = contactManifold.getNumContacts();
          for (int local_j = 0; local_j < numContacts; local_j++)
          {
            ManifoldPoint local_cp = contactManifold.getContactPoint(local_j);
            getDebugDrawer().drawContactPoint(local_cp.positionWorldOnB, local_cp.normalWorldOnB, local_cp.getDistance(), local_cp.getLifeTime(), color);
          }
        }
      }
      if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x3) != 0))
      {
        Transform color = localStack.get$com$bulletphysics$linearmath$Transform();
        Vector3f local_i = localStack.get$javax$vecmath$Vector3f();
        Vector3f contactManifold = localStack.get$javax$vecmath$Vector3f();
        Vector3f numContacts = localStack.get$javax$vecmath$Vector3f();
        for (int numManifolds = 0; numManifolds < this.collisionObjects.size(); numManifolds++)
        {
          CollisionObject local_j = (CollisionObject)this.collisionObjects.getQuick(numManifolds);
          if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0))
          {
            Vector3f local_cp = localStack.get$javax$vecmath$Vector3f();
            local_cp.set(255.0F, 255.0F, 255.0F);
            switch (local_j.getActivationState())
            {
            case 1: 
              local_cp.set(255.0F, 255.0F, 255.0F);
              break;
            case 2: 
              local_cp.set(0.0F, 255.0F, 0.0F);
              break;
            case 3: 
              local_cp.set(0.0F, 255.0F, 255.0F);
              break;
            case 4: 
              local_cp.set(255.0F, 0.0F, 0.0F);
              break;
            case 5: 
              local_cp.set(255.0F, 255.0F, 0.0F);
              break;
            default: 
              local_cp.set(255.0F, 0.0F, 0.0F);
            }
            debugDrawObject(local_j.getWorldTransform(color), local_j.getCollisionShape(), local_cp);
          }
          if ((this.debugDrawer != null) && ((this.debugDrawer.getDebugMode() & 0x2) != 0))
          {
            numContacts.set(1.0F, 0.0F, 0.0F);
            local_j.getCollisionShape().getAabb(local_j.getWorldTransform(color), local_i, contactManifold);
            this.debugDrawer.drawAabb(local_i, contactManifold, numContacts);
          }
        }
        Vector3f local_j = localStack.get$javax$vecmath$Vector3f();
        Vector3f local_cp = localStack.get$javax$vecmath$Vector3f();
        Vector3f axle = localStack.get$javax$vecmath$Vector3f();
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        for (numManifolds = 0; numManifolds < this.vehicles.size(); numManifolds++) {
          for (int local_v = 0; local_v < ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getNumWheels(); local_v++)
          {
            local_j.set(0.0F, 255.0F, 255.0F);
            if (((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getWheelInfo(local_v).raycastInfo.isInContact) {
              local_j.set(0.0F, 0.0F, 255.0F);
            } else {
              local_j.set(255.0F, 0.0F, 255.0F);
            }
            local_cp.set(((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getWheelInfo(local_v).worldTransform.origin);
            axle.set(((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getWheelInfo(local_v).worldTransform.basis.getElement(0, ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getRightAxis()), ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getWheelInfo(local_v).worldTransform.basis.getElement(1, ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getRightAxis()), ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getWheelInfo(local_v).worldTransform.basis.getElement(2, ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getRightAxis()));
            tmp.add(local_cp, axle);
            this.debugDrawer.drawLine(local_cp, tmp, local_j);
            this.debugDrawer.drawLine(local_cp, ((RaycastVehicle)this.vehicles.getQuick(numManifolds)).getWheelInfo(local_v).raycastInfo.contactPointWS, local_j);
          }
        }
        if ((getDebugDrawer() != null) && (getDebugDrawer().getDebugMode() != 0)) {
          for (numManifolds = 0; numManifolds < this.actions.size(); numManifolds++) {
            ((ActionInterface)this.actions.getQuick(numManifolds)).debugDraw(this.debugDrawer);
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp841_839 = localStack;
      tmp841_839.pop$com$bulletphysics$linearmath$Transform();
      tmp841_839.pop$javax$vecmath$Vector3f();
    }
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
  
  public void applyGravity()
  {
    for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
    {
      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
      RigidBody body = RigidBody.upcast(colObj);
      if ((body != null) && (body.isActive())) {
        body.applyGravity();
      }
    }
  }
  
  protected void synchronizeMotionStates()
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform interpolatedTransform = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Vector3f tmpLinVel = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmpAngVel = localStack.get$javax$vecmath$Vector3f();
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
        RigidBody body = RigidBody.upcast(colObj);
        if ((body != null) && (body.getMotionState() != null) && (!body.isStaticOrKinematicObject()))
        {
          TransformUtil.integrateTransform(body.getInterpolationWorldTransform(tmpTrans), body.getInterpolationLinearVelocity(tmpLinVel), body.getInterpolationAngularVelocity(tmpAngVel), this.localTime * body.getHitFraction(), interpolatedTransform);
          body.getMotionState().setWorldTransform(interpolatedTransform);
        }
      }
      if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0)) {
        for (int local_i = 0; local_i < this.vehicles.size(); local_i++) {
          for (int colObj = 0; colObj < ((RaycastVehicle)this.vehicles.getQuick(local_i)).getNumWheels(); colObj++) {
            ((RaycastVehicle)this.vehicles.getQuick(local_i)).updateWheelTransform(colObj, true);
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp243_241 = localStack;
      tmp243_241.pop$com$bulletphysics$linearmath$Transform();
      tmp243_241.pop$javax$vecmath$Vector3f();
    }
  }
  
  public int stepSimulation(float timeStep, int maxSubSteps, float fixedTimeStep)
  {
    startProfiling(timeStep);
    long local_t0 = System.nanoTime();
    BulletStats.pushProfile("stepSimulation");
    try
    {
      int numSimulationSubSteps = 0;
      if (maxSubSteps != 0)
      {
        this.localTime += timeStep;
        if (this.localTime >= fixedTimeStep)
        {
          numSimulationSubSteps = (int)(this.localTime / fixedTimeStep);
          this.localTime -= numSimulationSubSteps * fixedTimeStep;
        }
      }
      else
      {
        fixedTimeStep = timeStep;
        this.localTime = timeStep;
        if (ScalarUtil.fuzzyZero(timeStep))
        {
          numSimulationSubSteps = 0;
          maxSubSteps = 0;
        }
        else
        {
          numSimulationSubSteps = 1;
          maxSubSteps = 1;
        }
      }
      if (getDebugDrawer() != null) {
        BulletGlobals.setDeactivationDisabled((getDebugDrawer().getDebugMode() & 0x10) != 0);
      }
      if (numSimulationSubSteps != 0)
      {
        saveKinematicState(fixedTimeStep);
        applyGravity();
        clampedSimulationSteps = numSimulationSubSteps > maxSubSteps ? maxSubSteps : numSimulationSubSteps;
        for (int local_i = 0; local_i < clampedSimulationSteps; local_i++)
        {
          internalSingleStepSimulation(fixedTimeStep);
          synchronizeMotionStates();
        }
      }
      synchronizeMotionStates();
      clearForces();
      CProfileManager.incrementFrameCounter();
      int clampedSimulationSteps = numSimulationSubSteps;
      return clampedSimulationSteps;
    }
    finally
    {
      BulletStats.popProfile();
      BulletStats.stepSimulationTime = (System.nanoTime() - local_t0) / 1000000L;
    }
  }
  
  protected void internalSingleStepSimulation(float timeStep)
  {
    BulletStats.pushProfile("internalSingleStepSimulation");
    try
    {
      predictUnconstraintMotion(timeStep);
      DispatcherInfo dispatchInfo = getDispatchInfo();
      dispatchInfo.timeStep = timeStep;
      dispatchInfo.stepCount = 0;
      dispatchInfo.debugDraw = getDebugDrawer();
      performDiscreteCollisionDetection();
      calculateSimulationIslands();
      getSolverInfo().timeStep = timeStep;
      solveConstraints(getSolverInfo());
      integrateTransforms(timeStep);
      updateActions(timeStep);
      updateVehicles(timeStep);
      updateActivationState(timeStep);
      if (this.internalTickCallback != null) {
        this.internalTickCallback.internalTick(this, timeStep);
      }
    }
    finally
    {
      BulletStats.popProfile();
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
  
  public void removeRigidBody(RigidBody body)
  {
    removeCollisionObject(body);
  }
  
  public void addRigidBody(RigidBody body)
  {
    if (!body.isStaticOrKinematicObject()) {
      body.setGravity(this.gravity);
    }
    if (body.getCollisionShape() != null)
    {
      boolean isDynamic = (!body.isStaticObject()) && (!body.isKinematicObject());
      short collisionFilterGroup = isDynamic ? 1 : 2;
      short collisionFilterMask = isDynamic ? -1 : -3;
      addCollisionObject(body, collisionFilterGroup, collisionFilterMask);
    }
  }
  
  public void addRigidBody(RigidBody body, short group, short mask)
  {
    if (!body.isStaticOrKinematicObject()) {
      body.setGravity(this.gravity);
    }
    if (body.getCollisionShape() != null) {
      addCollisionObject(body, group, mask);
    }
  }
  
  public void updateActions(float timeStep)
  {
    BulletStats.pushProfile("updateActions");
    try
    {
      for (int local_i = 0; local_i < this.actions.size(); local_i++) {
        ((ActionInterface)this.actions.getQuick(local_i)).updateAction(this, timeStep);
      }
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  protected void updateVehicles(float timeStep)
  {
    BulletStats.pushProfile("updateVehicles");
    try
    {
      for (int local_i = 0; local_i < this.vehicles.size(); local_i++)
      {
        RaycastVehicle vehicle = (RaycastVehicle)this.vehicles.getQuick(local_i);
        vehicle.updateVehicle(timeStep);
      }
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  protected void updateActivationState(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      BulletStats.pushProfile("updateActivationState");
      try
      {
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
        {
          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
          RigidBody body = RigidBody.upcast(colObj);
          if (body != null)
          {
            body.updateDeactivation(timeStep);
            if (body.wantsSleeping())
            {
              if (body.isStaticOrKinematicObject())
              {
                body.setActivationState(2);
              }
              else
              {
                if (body.getActivationState() == 1) {
                  body.setActivationState(3);
                }
                if (body.getActivationState() == 2)
                {
                  tmp.set(0.0F, 0.0F, 0.0F);
                  body.setAngularVelocity(tmp);
                  body.setLinearVelocity(tmp);
                }
              }
            }
            else if (body.getActivationState() != 4) {
              body.setActivationState(1);
            }
          }
        }
      }
      finally
      {
        BulletStats.popProfile();
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void addConstraint(TypedConstraint constraint, boolean disableCollisionsBetweenLinkedBodies)
  {
    this.constraints.add(constraint);
    if (disableCollisionsBetweenLinkedBodies)
    {
      constraint.getRigidBodyA().addConstraintRef(constraint);
      constraint.getRigidBodyB().addConstraintRef(constraint);
    }
  }
  
  public void removeConstraint(TypedConstraint constraint)
  {
    this.constraints.remove(constraint);
    constraint.getRigidBodyA().removeConstraintRef(constraint);
    constraint.getRigidBodyB().removeConstraintRef(constraint);
  }
  
  public void addAction(ActionInterface action)
  {
    this.actions.add(action);
  }
  
  public void removeAction(ActionInterface action)
  {
    this.actions.remove(action);
  }
  
  public void addVehicle(RaycastVehicle vehicle)
  {
    this.vehicles.add(vehicle);
  }
  
  public void removeVehicle(RaycastVehicle vehicle)
  {
    this.vehicles.remove(vehicle);
  }
  
  private static int getConstraintIslandId(TypedConstraint lhs)
  {
    CollisionObject rcolObj0 = lhs.getRigidBodyA();
    CollisionObject rcolObj1 = lhs.getRigidBodyB();
    int islandId = rcolObj0.getIslandTag() >= 0 ? rcolObj0.getIslandTag() : rcolObj1.getIslandTag();
    return islandId;
  }
  
  protected void solveConstraints(ContactSolverInfo solverInfo)
  {
    BulletStats.pushProfile("solveConstraints");
    try
    {
      this.sortedConstraints.clear();
      for (int local_i = 0; local_i < this.constraints.size(); local_i++) {
        this.sortedConstraints.add(this.constraints.getQuick(local_i));
      }
      MiscUtil.quickSort(this.sortedConstraints, sortConstraintOnIslandPredicate);
      ObjectArrayList<TypedConstraint> local_i = getNumConstraints() != 0 ? this.sortedConstraints : null;
      this.solverCallback.init(solverInfo, this.constraintSolver, local_i, this.sortedConstraints.size(), this.debugDrawer, this.dispatcher1);
      this.constraintSolver.prepareSolve(getCollisionWorld().getNumCollisionObjects(), getCollisionWorld().getDispatcher().getNumManifolds());
      this.islandManager.buildAndProcessIslands(getCollisionWorld().getDispatcher(), getCollisionWorld().getCollisionObjectArray(), this.solverCallback);
      this.constraintSolver.allSolved(solverInfo, this.debugDrawer);
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  protected void calculateSimulationIslands()
  {
    BulletStats.pushProfile("calculateSimulationIslands");
    try
    {
      getSimulationIslandManager().updateActivationState(getCollisionWorld(), getCollisionWorld().getDispatcher());
      int numConstraints = this.constraints.size();
      for (int local_i = 0; local_i < numConstraints; local_i++)
      {
        TypedConstraint constraint = (TypedConstraint)this.constraints.getQuick(local_i);
        RigidBody colObj0 = constraint.getRigidBodyA();
        RigidBody colObj1 = constraint.getRigidBodyB();
        if ((colObj0 != null) && (!colObj0.isStaticOrKinematicObject()) && (colObj1 != null) && (!colObj1.isStaticOrKinematicObject()) && ((colObj0.isActive()) || (colObj1.isActive()))) {
          getSimulationIslandManager().getUnionFind().unite(colObj0.getIslandTag(), colObj1.getIslandTag());
        }
      }
      getSimulationIslandManager().storeIslandActivationState(getCollisionWorld());
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  protected void integrateTransforms(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      BulletStats.pushProfile("integrateTransforms");
      try
      {
        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
        Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
        Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
        for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
        {
          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
          RigidBody body = RigidBody.upcast(colObj);
          if (body != null)
          {
            body.setHitFraction(1.0F);
            if ((body.isActive()) && (!body.isStaticOrKinematicObject()))
            {
              body.predictIntegratedTransform(timeStep, predictedTrans);
              tmp.sub(predictedTrans.origin, body.getWorldTransform(tmpTrans).origin);
              float squareMotion = tmp.lengthSquared();
              if ((body.getCcdSquareMotionThreshold() != 0.0F) && (body.getCcdSquareMotionThreshold() < squareMotion))
              {
                BulletStats.pushProfile("CCD motion clamping");
                try
                {
                  if (body.getCollisionShape().isConvex())
                  {
                    BulletStats.gNumClampedCcdMotions += 1;
                    ClosestNotMeConvexResultCallback sweepResults = new ClosestNotMeConvexResultCallback(body, body.getWorldTransform(tmpTrans).origin, predictedTrans.origin, getBroadphase().getOverlappingPairCache(), getDispatcher());
                    SphereShape tmpSphere = new SphereShape(body.getCcdSweptSphereRadius());
                    sweepResults.collisionFilterGroup = body.getBroadphaseProxy().collisionFilterGroup;
                    sweepResults.collisionFilterMask = body.getBroadphaseProxy().collisionFilterMask;
                    convexSweepTest(tmpSphere, body.getWorldTransform(tmpTrans), predictedTrans, sweepResults);
                    if ((sweepResults.hasHit()) && (sweepResults.closestHitFraction > 1.0E-004F))
                    {
                      body.setHitFraction(sweepResults.closestHitFraction);
                      body.predictIntegratedTransform(timeStep * body.getHitFraction(), predictedTrans);
                      body.setHitFraction(0.0F);
                    }
                  }
                }
                finally {}
              }
              body.proceedToTransform(predictedTrans);
            }
          }
        }
      }
      finally
      {
        BulletStats.popProfile();
      }
      return;
    }
    finally
    {
      .Stack tmp375_373 = localStack;
      tmp375_373.pop$com$bulletphysics$linearmath$Transform();
      tmp375_373.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void predictUnconstraintMotion(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      BulletStats.pushProfile("predictUnconstraintMotion");
      try
      {
        Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
        for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
        {
          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
          RigidBody body = RigidBody.upcast(colObj);
          if ((body != null) && (!body.isStaticOrKinematicObject()) && (body.isActive()))
          {
            body.integrateVelocities(timeStep);
            body.applyDamping(timeStep);
            body.predictIntegratedTransform(timeStep, body.getInterpolationWorldTransform(tmpTrans));
          }
        }
      }
      finally
      {
        BulletStats.popProfile();
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  protected void startProfiling(float timeStep) {}
  
  protected void debugDrawSphere(float arg1, Transform arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f start = localStack.get$javax$vecmath$Vector3f(transform.origin);
      Vector3f xoffs = localStack.get$javax$vecmath$Vector3f();
      xoffs.set(radius, 0.0F, 0.0F);
      transform.basis.transform(xoffs);
      Vector3f yoffs = localStack.get$javax$vecmath$Vector3f();
      yoffs.set(0.0F, radius, 0.0F);
      transform.basis.transform(yoffs);
      Vector3f zoffs = localStack.get$javax$vecmath$Vector3f();
      zoffs.set(0.0F, 0.0F, radius);
      transform.basis.transform(zoffs);
      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      tmp1.sub(start, xoffs);
      tmp2.add(start, yoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.add(start, yoffs);
      tmp2.add(start, xoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.add(start, xoffs);
      tmp2.sub(start, yoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.sub(start, yoffs);
      tmp2.sub(start, xoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.sub(start, xoffs);
      tmp2.add(start, zoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.add(start, zoffs);
      tmp2.add(start, xoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.add(start, xoffs);
      tmp2.sub(start, zoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.sub(start, zoffs);
      tmp2.sub(start, xoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.sub(start, yoffs);
      tmp2.add(start, zoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.add(start, zoffs);
      tmp2.add(start, yoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.add(start, yoffs);
      tmp2.sub(start, zoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      tmp1.sub(start, zoffs);
      tmp2.sub(start, yoffs);
      getDebugDrawer().drawLine(tmp1, tmp2, color);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void debugDrawObject(Transform arg1, CollisionShape arg2, Vector3f arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
      Vector3f start = localStack.get$javax$vecmath$Vector3f(worldTransform.origin);
      tmp.set(1.0F, 0.0F, 0.0F);
      worldTransform.basis.transform(tmp);
      tmp.add(start);
      tmp2.set(1.0F, 0.0F, 0.0F);
      getDebugDrawer().drawLine(start, tmp, tmp2);
      tmp.set(0.0F, 1.0F, 0.0F);
      worldTransform.basis.transform(tmp);
      tmp.add(start);
      tmp2.set(0.0F, 1.0F, 0.0F);
      getDebugDrawer().drawLine(start, tmp, tmp2);
      tmp.set(0.0F, 0.0F, 1.0F);
      worldTransform.basis.transform(tmp);
      tmp.add(start);
      tmp2.set(0.0F, 0.0F, 1.0F);
      getDebugDrawer().drawLine(start, tmp, tmp2);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
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
  
  public int getNumConstraints()
  {
    return this.constraints.size();
  }
  
  public TypedConstraint getConstraint(int index)
  {
    return (TypedConstraint)this.constraints.getQuick(index);
  }
  
  public int getNumActions()
  {
    return this.actions.size();
  }
  
  public ActionInterface getAction(int index)
  {
    return (ActionInterface)this.actions.getQuick(index);
  }
  
  public SimulationIslandManager getSimulationIslandManager()
  {
    return this.islandManager;
  }
  
  public CollisionWorld getCollisionWorld()
  {
    return this;
  }
  
  public DynamicsWorldType getWorldType()
  {
    return DynamicsWorldType.DISCRETE_DYNAMICS_WORLD;
  }
  
  public void setNumTasks(int numTasks) {}
  
  private static class ClosestNotMeConvexResultCallback
    extends CollisionWorld.ClosestConvexResultCallback
  {
    private CollisionObject field_419;
    private float allowedPenetration = 0.0F;
    private OverlappingPairCache pairCache;
    private Dispatcher dispatcher;
    
    public ClosestNotMeConvexResultCallback(CollisionObject local_me, Vector3f fromA, Vector3f toA, OverlappingPairCache pairCache, Dispatcher dispatcher)
    {
      super(toA);
      this.field_419 = local_me;
      this.pairCache = pairCache;
      this.dispatcher = dispatcher;
    }
    
    public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$javax$vecmath$Vector3f();
        if (convexResult.hitCollisionObject == this.field_419) {
          return 1.0F;
        }
        Vector3f linVelA = localStack.get$javax$vecmath$Vector3f();
        Vector3f linVelB = localStack.get$javax$vecmath$Vector3f();
        linVelA.sub(this.convexToWorld, this.convexFromWorld);
        linVelB.set(0.0F, 0.0F, 0.0F);
        Vector3f relativeVelocity = localStack.get$javax$vecmath$Vector3f();
        relativeVelocity.sub(linVelA, linVelB);
        if (convexResult.hitNormalLocal.dot(relativeVelocity) >= -this.allowedPenetration) {
          return 1.0F;
        }
        return super.addSingleResult(convexResult, normalInWorldSpace);
      }
      finally
      {
        localStack.pop$javax$vecmath$Vector3f();
      }
    }
    
    public boolean needsCollision(BroadphaseProxy proxy0)
    {
      if (proxy0.clientObject == this.field_419) {
        return false;
      }
      if (!super.needsCollision(proxy0)) {
        return false;
      }
      CollisionObject otherObj = (CollisionObject)proxy0.clientObject;
      if (this.dispatcher.needsResponse(this.field_419, otherObj))
      {
        ObjectArrayList<PersistentManifold> manifoldArray = new ObjectArrayList();
        BroadphasePair collisionPair = this.pairCache.findPair(this.field_419.getBroadphaseHandle(), proxy0);
        if ((collisionPair != null) && (collisionPair.algorithm != null))
        {
          collisionPair.algorithm.getAllContactManifolds(manifoldArray);
          for (int local_j = 0; local_j < manifoldArray.size(); local_j++)
          {
            PersistentManifold manifold = (PersistentManifold)manifoldArray.getQuick(local_j);
            if (manifold.getNumContacts() > 0) {
              return false;
            }
          }
        }
      }
      return true;
    }
  }
  
  private static class InplaceSolverIslandCallback
    extends SimulationIslandManager.IslandCallback
  {
    public ContactSolverInfo solverInfo;
    public ConstraintSolver solver;
    public ObjectArrayList<TypedConstraint> sortedConstraints;
    public int numConstraints;
    public IDebugDraw debugDrawer;
    public Dispatcher dispatcher;
    
    public void init(ContactSolverInfo solverInfo, ConstraintSolver solver, ObjectArrayList<TypedConstraint> sortedConstraints, int numConstraints, IDebugDraw debugDrawer, Dispatcher dispatcher)
    {
      this.solverInfo = solverInfo;
      this.solver = solver;
      this.sortedConstraints = sortedConstraints;
      this.numConstraints = numConstraints;
      this.debugDrawer = debugDrawer;
      this.dispatcher = dispatcher;
    }
    
    public void processIsland(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifolds, int manifolds_offset, int numManifolds, int islandId)
    {
      if (islandId < 0)
      {
        this.solver.solveGroup(bodies, numBodies, manifolds, manifolds_offset, numManifolds, this.sortedConstraints, 0, this.numConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
      }
      else
      {
        int startConstraint_idx = -1;
        int numCurConstraints = 0;
        for (int local_i = 0; local_i < this.numConstraints; local_i++) {
          if (DiscreteDynamicsWorld.getConstraintIslandId((TypedConstraint)this.sortedConstraints.getQuick(local_i)) == islandId)
          {
            startConstraint_idx = local_i;
            break;
          }
        }
        while (local_i < this.numConstraints)
        {
          if (DiscreteDynamicsWorld.getConstraintIslandId((TypedConstraint)this.sortedConstraints.getQuick(local_i)) == islandId) {
            numCurConstraints++;
          }
          local_i++;
        }
        if (numManifolds + numCurConstraints > 0) {
          this.solver.solveGroup(bodies, numBodies, manifolds, manifolds_offset, numManifolds, this.sortedConstraints, startConstraint_idx, numCurConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.DiscreteDynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */