/*      */ package com.bulletphysics.dynamics;
/*      */ 
/*      */ import com.bulletphysics..Stack;
/*      */ import com.bulletphysics.BulletGlobals;
/*      */ import com.bulletphysics.BulletStats;
/*      */ import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*      */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*      */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*      */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*      */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*      */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*      */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*      */ import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*      */ import com.bulletphysics.collision.dispatch.SimulationIslandManager;
/*      */ import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
/*      */ import com.bulletphysics.collision.dispatch.UnionFind;
/*      */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*      */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*      */ import com.bulletphysics.collision.shapes.CollisionShape;
/*      */ import com.bulletphysics.collision.shapes.SphereShape;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*      */ import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
/*      */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*      */ import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
/*      */ import com.bulletphysics.dynamics.vehicle.WheelInfo;
/*      */ import com.bulletphysics.dynamics.vehicle.WheelInfo.RaycastInfo;
/*      */ import com.bulletphysics.linearmath.CProfileManager;
/*      */ import com.bulletphysics.linearmath.IDebugDraw;
/*      */ import com.bulletphysics.linearmath.MiscUtil;
/*      */ import com.bulletphysics.linearmath.MotionState;
/*      */ import com.bulletphysics.linearmath.ScalarUtil;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.linearmath.TransformUtil;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import java.util.Comparator;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ public class DiscreteDynamicsWorld extends DynamicsWorld
/*      */ {
/*      */   protected ConstraintSolver constraintSolver;
/*      */   protected SimulationIslandManager islandManager;
/*   73 */   protected final ObjectArrayList<TypedConstraint> constraints = new ObjectArrayList();
/*   74 */   protected final Vector3f gravity = new Vector3f(0.0F, -10.0F, 0.0F);
/*      */ 
/*   77 */   protected float localTime = 0.01666667F;
/*      */   protected boolean ownsIslandManager;
/*      */   protected boolean ownsConstraintSolver;
/*   83 */   protected ObjectArrayList<RaycastVehicle> vehicles = new ObjectArrayList();
/*      */ 
/*   85 */   protected ObjectArrayList<ActionInterface> actions = new ObjectArrayList();
/*      */ 
/*   87 */   protected int profileTimings = 0;
/*      */ 
/*  614 */   private ObjectArrayList<TypedConstraint> sortedConstraints = new ObjectArrayList();
/*  615 */   private InplaceSolverIslandCallback solverCallback = new InplaceSolverIslandCallback(null);
/*      */ 
/* 1065 */   private static final Comparator<TypedConstraint> sortConstraintOnIslandPredicate = new Comparator()
/*      */   {
/*      */     public int compare(TypedConstraint lhs, TypedConstraint rhs) {
/* 1068 */       int rIslandId0 = DiscreteDynamicsWorld.getConstraintIslandId(rhs);
/* 1069 */       int lIslandId0 = DiscreteDynamicsWorld.getConstraintIslandId(lhs);
/* 1070 */       return lIslandId0 < rIslandId0 ? -1 : 1;
/*      */     }
/* 1065 */   };
/*      */ 
/*      */   public DiscreteDynamicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver, CollisionConfiguration collisionConfiguration)
/*      */   {
/*   90 */     super(dispatcher, pairCache, collisionConfiguration);
/*   91 */     this.constraintSolver = constraintSolver;
/*      */ 
/*   93 */     if (this.constraintSolver == null) {
/*   94 */       this.constraintSolver = new SequentialImpulseConstraintSolver();
/*   95 */       this.ownsConstraintSolver = true;
/*      */     }
/*      */     else {
/*   98 */       this.ownsConstraintSolver = false;
/*      */     }
/*      */ 
/*  102 */     this.islandManager = new SimulationIslandManager();
/*      */ 
/*  105 */     this.ownsIslandManager = true;
/*      */   }
/*      */ 
/*      */   protected void saveKinematicState(float timeStep) {
/*  109 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  110 */       CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  111 */       RigidBody body = RigidBody.upcast(colObj);
/*  112 */       if (body != null)
/*      */       {
/*  114 */         if ((body.getActivationState() != 2) && 
/*  115 */           (body.isKinematicObject()))
/*      */         {
/*  117 */           body.saveKinematicState(timeStep);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void debugDrawWorld()
/*      */   {
/*  126 */     .Stack localStack = .Stack.get();
/*      */     try
/*      */     {
/*      */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x8) != 0)) {
/*  127 */         int numManifolds = getDispatcher().getNumManifolds();
/*  128 */         Vector3f color = localStack.get$javax$vecmath$Vector3f();
/*  129 */         color.set(0.0F, 0.0F, 0.0F);
/*  130 */         for (int i = 0; i < numManifolds; i++) {
/*  131 */           PersistentManifold contactManifold = getDispatcher().getManifoldByIndexInternal(i);
/*      */ 
/*  135 */           int numContacts = contactManifold.getNumContacts();
/*  136 */           for (int j = 0; j < numContacts; j++) {
/*  137 */             ManifoldPoint cp = contactManifold.getContactPoint(j);
/*  138 */             getDebugDrawer().drawContactPoint(cp.positionWorldOnB, cp.normalWorldOnB, cp.getDistance(), cp.getLifeTime(), color);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  143 */       if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x3) != 0))
/*      */       {
/*  146 */         Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  147 */         Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
/*  148 */         Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/*  149 */         Vector3f colorvec = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  152 */         for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  153 */           CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  154 */           if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0)) {
/*  155 */             Vector3f color = localStack.get$javax$vecmath$Vector3f();
/*  156 */             color.set(255.0F, 255.0F, 255.0F);
/*  157 */             switch (colObj.getActivationState()) {
/*      */             case 1:
/*  159 */               color.set(255.0F, 255.0F, 255.0F);
/*  160 */               break;
/*      */             case 2:
/*  162 */               color.set(0.0F, 255.0F, 0.0F);
/*  163 */               break;
/*      */             case 3:
/*  165 */               color.set(0.0F, 255.0F, 255.0F);
/*  166 */               break;
/*      */             case 4:
/*  168 */               color.set(255.0F, 0.0F, 0.0F);
/*  169 */               break;
/*      */             case 5:
/*  171 */               color.set(255.0F, 255.0F, 0.0F);
/*  172 */               break;
/*      */             default:
/*  174 */               color.set(255.0F, 0.0F, 0.0F);
/*      */             }
/*      */ 
/*  178 */             debugDrawObject(colObj.getWorldTransform(tmpTrans), colObj.getCollisionShape(), color);
/*      */           }
/*  180 */           if ((this.debugDrawer != null) && ((this.debugDrawer.getDebugMode() & 0x2) != 0)) {
/*  181 */             colorvec.set(1.0F, 0.0F, 0.0F);
/*  182 */             colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
/*  183 */             this.debugDrawer.drawAabb(minAabb, maxAabb, colorvec);
/*      */           }
/*      */         }
/*      */ 
/*  187 */         Vector3f wheelColor = localStack.get$javax$vecmath$Vector3f();
/*  188 */         Vector3f wheelPosWS = localStack.get$javax$vecmath$Vector3f();
/*  189 */         Vector3f axle = localStack.get$javax$vecmath$Vector3f();
/*  190 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  192 */         for (i = 0; i < this.vehicles.size(); i++) {
/*  193 */           for (int v = 0; v < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); v++) {
/*  194 */             wheelColor.set(0.0F, 255.0F, 255.0F);
/*  195 */             if (((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).raycastInfo.isInContact) {
/*  196 */               wheelColor.set(0.0F, 0.0F, 255.0F);
/*      */             }
/*      */             else {
/*  199 */               wheelColor.set(255.0F, 0.0F, 255.0F);
/*      */             }
/*      */ 
/*  202 */             wheelPosWS.set(((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.origin);
/*      */ 
/*  204 */             axle.set(((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.basis.getElement(0, ((RaycastVehicle)this.vehicles.getQuick(i)).getRightAxis()), ((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.basis.getElement(1, ((RaycastVehicle)this.vehicles.getQuick(i)).getRightAxis()), ((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.basis.getElement(2, ((RaycastVehicle)this.vehicles.getQuick(i)).getRightAxis()));
/*      */ 
/*  212 */             tmp.add(wheelPosWS, axle);
/*  213 */             this.debugDrawer.drawLine(wheelPosWS, tmp, wheelColor);
/*  214 */             this.debugDrawer.drawLine(wheelPosWS, ((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).raycastInfo.contactPointWS, wheelColor);
/*      */           }
/*      */         }
/*      */ 
/*  218 */         if ((getDebugDrawer() != null) && (getDebugDrawer().getDebugMode() != 0))
/*  219 */           for (i = 0; i < this.actions.size(); i++)
/*  220 */             ((ActionInterface)this.actions.getQuick(i)).debugDraw(this.debugDrawer);
/*      */       }
/*      */       return;
/*      */     }
/*      */     finally
/*      */     {
/*  224 */       .Stack tmp841_839 = localStack; tmp841_839.pop$com$bulletphysics$linearmath$Transform(); tmp841_839.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   public void clearForces()
/*      */   {
/*  229 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  230 */       CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*      */ 
/*  232 */       RigidBody body = RigidBody.upcast(colObj);
/*  233 */       if (body != null)
/*  234 */         body.clearForces();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void applyGravity()
/*      */   {
/*  244 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  245 */       CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*      */ 
/*  247 */       RigidBody body = RigidBody.upcast(colObj);
/*  248 */       if ((body != null) && (body.isActive()))
/*  249 */         body.applyGravity();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void synchronizeMotionStates()
/*      */   {
/*  255 */     .Stack localStack = .Stack.get();
/*      */     try
/*      */     {
/*      */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform interpolatedTransform = localStack.get$com$bulletphysics$linearmath$Transform();
/*      */ 
/*  257 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  258 */       Vector3f tmpLinVel = localStack.get$javax$vecmath$Vector3f();
/*  259 */       Vector3f tmpAngVel = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  262 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  263 */         CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*      */ 
/*  265 */         RigidBody body = RigidBody.upcast(colObj);
/*  266 */         if ((body != null) && (body.getMotionState() != null) && (!body.isStaticOrKinematicObject()))
/*      */         {
/*  272 */           TransformUtil.integrateTransform(body.getInterpolationWorldTransform(tmpTrans), body.getInterpolationLinearVelocity(tmpLinVel), body.getInterpolationAngularVelocity(tmpAngVel), this.localTime * body.getHitFraction(), interpolatedTransform);
/*      */ 
/*  277 */           body.getMotionState().setWorldTransform(interpolatedTransform);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  282 */       if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0))
/*  283 */         for (int i = 0; i < this.vehicles.size(); i++)
/*  284 */           for (int v = 0; v < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); v++)
/*      */           {
/*  286 */             ((RaycastVehicle)this.vehicles.getQuick(i)).updateWheelTransform(v, true);
/*      */           }
/*      */       return;
/*      */     }
/*      */     finally
/*      */     {
/*  290 */       .Stack tmp243_241 = localStack; tmp243_241.pop$com$bulletphysics$linearmath$Transform(); tmp243_241.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   public int stepSimulation(float timeStep, int maxSubSteps, float fixedTimeStep) {
/*  294 */     startProfiling(timeStep);
/*      */ 
/*  296 */     long t0 = System.nanoTime();
/*      */ 
/*  298 */     BulletStats.pushProfile("stepSimulation");
/*      */     try {
/*  300 */       int numSimulationSubSteps = 0;
/*      */ 
/*  302 */       if (maxSubSteps != 0)
/*      */       {
/*  304 */         this.localTime += timeStep;
/*  305 */         if (this.localTime >= fixedTimeStep) {
/*  306 */           numSimulationSubSteps = (int)(this.localTime / fixedTimeStep);
/*  307 */           this.localTime -= numSimulationSubSteps * fixedTimeStep;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  312 */         fixedTimeStep = timeStep;
/*  313 */         this.localTime = timeStep;
/*  314 */         if (ScalarUtil.fuzzyZero(timeStep)) {
/*  315 */           numSimulationSubSteps = 0;
/*  316 */           maxSubSteps = 0;
/*      */         }
/*      */         else {
/*  319 */           numSimulationSubSteps = 1;
/*  320 */           maxSubSteps = 1;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  325 */       if (getDebugDrawer() != null)
/*  326 */         BulletGlobals.setDeactivationDisabled((getDebugDrawer().getDebugMode() & 0x10) != 0);
/*      */       int clampedSimulationSteps;
/*  328 */       if (numSimulationSubSteps != 0) {
/*  329 */         saveKinematicState(fixedTimeStep);
/*      */ 
/*  331 */         applyGravity();
/*      */ 
/*  334 */         clampedSimulationSteps = numSimulationSubSteps > maxSubSteps ? maxSubSteps : numSimulationSubSteps;
/*      */ 
/*  336 */         for (int i = 0; i < clampedSimulationSteps; i++) {
/*  337 */           internalSingleStepSimulation(fixedTimeStep);
/*  338 */           synchronizeMotionStates();
/*      */         }
/*      */       }
/*      */ 
/*  342 */       synchronizeMotionStates();
/*      */ 
/*  344 */       clearForces();
/*      */ 
/*  347 */       CProfileManager.incrementFrameCounter();
/*      */ 
/*  350 */       return numSimulationSubSteps;
/*      */     }
/*      */     finally {
/*  353 */       BulletStats.popProfile();
/*      */ 
/*  355 */       BulletStats.stepSimulationTime = (System.nanoTime() - t0) / 1000000L;
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void internalSingleStepSimulation(float timeStep) {
/*  360 */     BulletStats.pushProfile("internalSingleStepSimulation");
/*      */     try
/*      */     {
/*  363 */       predictUnconstraintMotion(timeStep);
/*      */ 
/*  365 */       DispatcherInfo dispatchInfo = getDispatchInfo();
/*      */ 
/*  367 */       dispatchInfo.timeStep = timeStep;
/*  368 */       dispatchInfo.stepCount = 0;
/*  369 */       dispatchInfo.debugDraw = getDebugDrawer();
/*      */ 
/*  372 */       performDiscreteCollisionDetection();
/*      */ 
/*  374 */       calculateSimulationIslands();
/*      */ 
/*  376 */       getSolverInfo().timeStep = timeStep;
/*      */ 
/*  379 */       solveConstraints(getSolverInfo());
/*      */ 
/*  384 */       integrateTransforms(timeStep);
/*      */ 
/*  387 */       updateActions(timeStep);
/*      */ 
/*  390 */       updateVehicles(timeStep);
/*      */ 
/*  392 */       updateActivationState(timeStep);
/*      */ 
/*  394 */       if (this.internalTickCallback != null)
/*  395 */         this.internalTickCallback.internalTick(this, timeStep);
/*      */     }
/*      */     finally
/*      */     {
/*  399 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setGravity(Vector3f gravity)
/*      */   {
/*  405 */     this.gravity.set(gravity);
/*  406 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  407 */       CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  408 */       RigidBody body = RigidBody.upcast(colObj);
/*  409 */       if (body != null)
/*  410 */         body.setGravity(gravity);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Vector3f getGravity(Vector3f out)
/*      */   {
/*  417 */     out.set(this.gravity);
/*  418 */     return out;
/*      */   }
/*      */ 
/*      */   public void removeRigidBody(RigidBody body)
/*      */   {
/*  423 */     removeCollisionObject(body);
/*      */   }
/*      */ 
/*      */   public void addRigidBody(RigidBody body)
/*      */   {
/*  428 */     if (!body.isStaticOrKinematicObject()) {
/*  429 */       body.setGravity(this.gravity);
/*      */     }
/*      */ 
/*  432 */     if (body.getCollisionShape() != null) {
/*  433 */       boolean isDynamic = (!body.isStaticObject()) && (!body.isKinematicObject());
/*  434 */       short collisionFilterGroup = isDynamic ? 1 : 2;
/*  435 */       short collisionFilterMask = isDynamic ? -1 : -3;
/*      */ 
/*  437 */       addCollisionObject(body, collisionFilterGroup, collisionFilterMask);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void addRigidBody(RigidBody body, short group, short mask) {
/*  442 */     if (!body.isStaticOrKinematicObject()) {
/*  443 */       body.setGravity(this.gravity);
/*      */     }
/*      */ 
/*  446 */     if (body.getCollisionShape() != null)
/*  447 */       addCollisionObject(body, group, mask);
/*      */   }
/*      */ 
/*      */   public void updateActions(float timeStep)
/*      */   {
/*  452 */     BulletStats.pushProfile("updateActions");
/*      */     try {
/*  454 */       for (int i = 0; i < this.actions.size(); i++)
/*  455 */         ((ActionInterface)this.actions.getQuick(i)).updateAction(this, timeStep);
/*      */     }
/*      */     finally
/*      */     {
/*  459 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void updateVehicles(float timeStep) {
/*  464 */     BulletStats.pushProfile("updateVehicles");
/*      */     try {
/*  466 */       for (int i = 0; i < this.vehicles.size(); i++) {
/*  467 */         RaycastVehicle vehicle = (RaycastVehicle)this.vehicles.getQuick(i);
/*  468 */         vehicle.updateVehicle(timeStep);
/*      */       }
/*      */     }
/*      */     finally {
/*  472 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void updateActivationState(float arg1) {
/*  477 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); BulletStats.pushProfile("updateActivationState");
/*      */       try {
/*  479 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  481 */         for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  482 */           CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  483 */           RigidBody body = RigidBody.upcast(colObj);
/*  484 */           if (body != null) {
/*  485 */             body.updateDeactivation(timeStep);
/*      */ 
/*  487 */             if (body.wantsSleeping()) {
/*  488 */               if (body.isStaticOrKinematicObject()) {
/*  489 */                 body.setActivationState(2);
/*      */               }
/*      */               else {
/*  492 */                 if (body.getActivationState() == 1) {
/*  493 */                   body.setActivationState(3);
/*      */                 }
/*  495 */                 if (body.getActivationState() == 2) {
/*  496 */                   tmp.set(0.0F, 0.0F, 0.0F);
/*  497 */                   body.setAngularVelocity(tmp);
/*  498 */                   body.setLinearVelocity(tmp);
/*      */                 }
/*      */               }
/*      */ 
/*      */             }
/*  503 */             else if (body.getActivationState() != 4) {
/*  504 */               body.setActivationState(1);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/*  511 */         BulletStats.popProfile();
/*      */       }return; } finally {
/*  513 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   public void addConstraint(TypedConstraint constraint, boolean disableCollisionsBetweenLinkedBodies) {
/*  517 */     this.constraints.add(constraint);
/*  518 */     if (disableCollisionsBetweenLinkedBodies) {
/*  519 */       constraint.getRigidBodyA().addConstraintRef(constraint);
/*  520 */       constraint.getRigidBodyB().addConstraintRef(constraint);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removeConstraint(TypedConstraint constraint)
/*      */   {
/*  526 */     this.constraints.remove(constraint);
/*  527 */     constraint.getRigidBodyA().removeConstraintRef(constraint);
/*  528 */     constraint.getRigidBodyB().removeConstraintRef(constraint);
/*      */   }
/*      */ 
/*      */   public void addAction(ActionInterface action)
/*      */   {
/*  533 */     this.actions.add(action);
/*      */   }
/*      */ 
/*      */   public void removeAction(ActionInterface action)
/*      */   {
/*  538 */     this.actions.remove(action);
/*      */   }
/*      */ 
/*      */   public void addVehicle(RaycastVehicle vehicle)
/*      */   {
/*  543 */     this.vehicles.add(vehicle);
/*      */   }
/*      */ 
/*      */   public void removeVehicle(RaycastVehicle vehicle)
/*      */   {
/*  548 */     this.vehicles.remove(vehicle);
/*      */   }
/*      */ 
/*      */   private static int getConstraintIslandId(TypedConstraint lhs)
/*      */   {
/*  554 */     CollisionObject rcolObj0 = lhs.getRigidBodyA();
/*  555 */     CollisionObject rcolObj1 = lhs.getRigidBodyB();
/*  556 */     int islandId = rcolObj0.getIslandTag() >= 0 ? rcolObj0.getIslandTag() : rcolObj1.getIslandTag();
/*  557 */     return islandId;
/*      */   }
/*      */ 
/*      */   protected void solveConstraints(ContactSolverInfo solverInfo)
/*      */   {
/*  618 */     BulletStats.pushProfile("solveConstraints");
/*      */     try
/*      */     {
/*  621 */       this.sortedConstraints.clear();
/*  622 */       for (int i = 0; i < this.constraints.size(); i++) {
/*  623 */         this.sortedConstraints.add(this.constraints.getQuick(i));
/*      */       }
/*      */ 
/*  626 */       MiscUtil.quickSort(this.sortedConstraints, sortConstraintOnIslandPredicate);
/*      */ 
/*  628 */       ObjectArrayList constraintsPtr = getNumConstraints() != 0 ? this.sortedConstraints : null;
/*      */ 
/*  630 */       this.solverCallback.init(solverInfo, this.constraintSolver, constraintsPtr, this.sortedConstraints.size(), this.debugDrawer, this.dispatcher1);
/*      */ 
/*  632 */       this.constraintSolver.prepareSolve(getCollisionWorld().getNumCollisionObjects(), getCollisionWorld().getDispatcher().getNumManifolds());
/*      */ 
/*  635 */       this.islandManager.buildAndProcessIslands(getCollisionWorld().getDispatcher(), getCollisionWorld().getCollisionObjectArray(), this.solverCallback);
/*      */ 
/*  637 */       this.constraintSolver.allSolved(solverInfo, this.debugDrawer);
/*      */     }
/*      */     finally {
/*  640 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void calculateSimulationIslands() {
/*  645 */     BulletStats.pushProfile("calculateSimulationIslands");
/*      */     try {
/*  647 */       getSimulationIslandManager().updateActivationState(getCollisionWorld(), getCollisionWorld().getDispatcher());
/*      */ 
/*  651 */       int numConstraints = this.constraints.size();
/*  652 */       for (int i = 0; i < numConstraints; i++) {
/*  653 */         TypedConstraint constraint = (TypedConstraint)this.constraints.getQuick(i);
/*      */ 
/*  655 */         RigidBody colObj0 = constraint.getRigidBodyA();
/*  656 */         RigidBody colObj1 = constraint.getRigidBodyB();
/*      */ 
/*  658 */         if ((colObj0 != null) && (!colObj0.isStaticOrKinematicObject()) && (colObj1 != null) && (!colObj1.isStaticOrKinematicObject()))
/*      */         {
/*  661 */           if ((colObj0.isActive()) || (colObj1.isActive())) {
/*  662 */             getSimulationIslandManager().getUnionFind().unite(colObj0.getIslandTag(), colObj1.getIslandTag());
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  669 */       getSimulationIslandManager().storeIslandActivationState(getCollisionWorld());
/*      */     }
/*      */     finally {
/*  672 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void integrateTransforms(float arg1) {
/*  677 */     .Stack localStack = .Stack.get();
/*      */     try
/*      */     {
/*      */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); BulletStats.pushProfile("integrateTransforms");
/*      */       try {
/*  679 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  680 */         Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*      */ 
/*  682 */         Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  683 */         for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  684 */           CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  685 */           RigidBody body = RigidBody.upcast(colObj);
/*  686 */           if (body != null) {
/*  687 */             body.setHitFraction(1.0F);
/*      */ 
/*  689 */             if ((body.isActive()) && (!body.isStaticOrKinematicObject())) {
/*  690 */               body.predictIntegratedTransform(timeStep, predictedTrans);
/*      */ 
/*  692 */               tmp.sub(predictedTrans.origin, body.getWorldTransform(tmpTrans).origin);
/*  693 */               float squareMotion = tmp.lengthSquared();
/*      */ 
/*  695 */               if ((body.getCcdSquareMotionThreshold() != 0.0F) && (body.getCcdSquareMotionThreshold() < squareMotion)) {
/*  696 */                 BulletStats.pushProfile("CCD motion clamping");
/*      */                 try {
/*  698 */                   if (body.getCollisionShape().isConvex()) {
/*  699 */                     BulletStats.gNumClampedCcdMotions += 1;
/*      */ 
/*  701 */                     ClosestNotMeConvexResultCallback sweepResults = new ClosestNotMeConvexResultCallback(body, body.getWorldTransform(tmpTrans).origin, predictedTrans.origin, getBroadphase().getOverlappingPairCache(), getDispatcher());
/*      */ 
/*  703 */                     SphereShape tmpSphere = new SphereShape(body.getCcdSweptSphereRadius());
/*      */ 
/*  705 */                     sweepResults.collisionFilterGroup = body.getBroadphaseProxy().collisionFilterGroup;
/*  706 */                     sweepResults.collisionFilterMask = body.getBroadphaseProxy().collisionFilterMask;
/*      */ 
/*  708 */                     convexSweepTest(tmpSphere, body.getWorldTransform(tmpTrans), predictedTrans, sweepResults);
/*      */ 
/*  710 */                     if ((sweepResults.hasHit()) && (sweepResults.closestHitFraction > 1.0E-004F)) {
/*  711 */                       body.setHitFraction(sweepResults.closestHitFraction);
/*  712 */                       body.predictIntegratedTransform(timeStep * body.getHitFraction(), predictedTrans);
/*  713 */                       body.setHitFraction(0.0F);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */                 finally
/*      */                 {
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/*  723 */               body.proceedToTransform(predictedTrans);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       finally {
/*  729 */         BulletStats.popProfile();
/*      */       }
/*      */       return;
/*      */     }
/*      */     finally
/*      */     {
/*  731 */       .Stack tmp375_373 = localStack; tmp375_373.pop$com$bulletphysics$linearmath$Transform(); tmp375_373.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   protected void predictUnconstraintMotion(float arg1) {
/*  734 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$com$bulletphysics$linearmath$Transform(); BulletStats.pushProfile("predictUnconstraintMotion");
/*      */       try {
/*  736 */         Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*      */ 
/*  738 */         for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  739 */           CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  740 */           RigidBody body = RigidBody.upcast(colObj);
/*  741 */           if ((body != null) && 
/*  742 */             (!body.isStaticOrKinematicObject()) && 
/*  743 */             (body.isActive())) {
/*  744 */             body.integrateVelocities(timeStep);
/*      */ 
/*  746 */             body.applyDamping(timeStep);
/*      */ 
/*  748 */             body.predictIntegratedTransform(timeStep, body.getInterpolationWorldTransform(tmpTrans));
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */       finally
/*      */       {
/*  755 */         BulletStats.popProfile();
/*      */       }return; } finally {
/*  757 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*      */   }
/*      */ 
/*      */   protected void startProfiling(float timeStep) {
/*  761 */     CProfileManager.reset();
/*      */   }
/*      */ 
/*      */   protected void debugDrawSphere(float arg1, Transform arg2, Vector3f arg3)
/*      */   {
/*  766 */     .Stack localStack = .Stack.get();
/*      */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f start = localStack.get$javax$vecmath$Vector3f(transform.origin);
/*      */ 
/*  768 */       Vector3f xoffs = localStack.get$javax$vecmath$Vector3f();
/*  769 */       xoffs.set(radius, 0.0F, 0.0F);
/*  770 */       transform.basis.transform(xoffs);
/*  771 */       Vector3f yoffs = localStack.get$javax$vecmath$Vector3f();
/*  772 */       yoffs.set(0.0F, radius, 0.0F);
/*  773 */       transform.basis.transform(yoffs);
/*  774 */       Vector3f zoffs = localStack.get$javax$vecmath$Vector3f();
/*  775 */       zoffs.set(0.0F, 0.0F, radius);
/*  776 */       transform.basis.transform(zoffs);
/*      */ 
/*  778 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  779 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  782 */       tmp1.sub(start, xoffs);
/*  783 */       tmp2.add(start, yoffs);
/*  784 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  785 */       tmp1.add(start, yoffs);
/*  786 */       tmp2.add(start, xoffs);
/*  787 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  788 */       tmp1.add(start, xoffs);
/*  789 */       tmp2.sub(start, yoffs);
/*  790 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  791 */       tmp1.sub(start, yoffs);
/*  792 */       tmp2.sub(start, xoffs);
/*  793 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*      */ 
/*  796 */       tmp1.sub(start, xoffs);
/*  797 */       tmp2.add(start, zoffs);
/*  798 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  799 */       tmp1.add(start, zoffs);
/*  800 */       tmp2.add(start, xoffs);
/*  801 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  802 */       tmp1.add(start, xoffs);
/*  803 */       tmp2.sub(start, zoffs);
/*  804 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  805 */       tmp1.sub(start, zoffs);
/*  806 */       tmp2.sub(start, xoffs);
/*  807 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*      */ 
/*  810 */       tmp1.sub(start, yoffs);
/*  811 */       tmp2.add(start, zoffs);
/*  812 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  813 */       tmp1.add(start, zoffs);
/*  814 */       tmp2.add(start, yoffs);
/*  815 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  816 */       tmp1.add(start, yoffs);
/*  817 */       tmp2.sub(start, zoffs);
/*  818 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  819 */       tmp1.sub(start, zoffs);
/*  820 */       tmp2.sub(start, yoffs);
/*  821 */       getDebugDrawer().drawLine(tmp1, tmp2, color);
/*      */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */   public void debugDrawObject(Transform arg1, CollisionShape arg2, Vector3f arg3) {
/*  825 */     .Stack localStack = .Stack.get();
/*      */     try
/*      */     {
/*  825 */       localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  826 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*      */ 
/*  830 */       Vector3f start = localStack.get$javax$vecmath$Vector3f(worldTransform.origin);
/*      */ 
/*  832 */       tmp.set(1.0F, 0.0F, 0.0F);
/*  833 */       worldTransform.basis.transform(tmp);
/*  834 */       tmp.add(start);
/*  835 */       tmp2.set(1.0F, 0.0F, 0.0F);
/*  836 */       getDebugDrawer().drawLine(start, tmp, tmp2);
/*      */ 
/*  838 */       tmp.set(0.0F, 1.0F, 0.0F);
/*  839 */       worldTransform.basis.transform(tmp);
/*  840 */       tmp.add(start);
/*  841 */       tmp2.set(0.0F, 1.0F, 0.0F);
/*  842 */       getDebugDrawer().drawLine(start, tmp, tmp2);
/*      */ 
/*  844 */       tmp.set(0.0F, 0.0F, 1.0F);
/*  845 */       worldTransform.basis.transform(tmp);
/*  846 */       tmp.add(start);
/*  847 */       tmp2.set(0.0F, 0.0F, 1.0F);
/*  848 */       getDebugDrawer().drawLine(start, tmp, tmp2);
/*      */       return;
/*      */     }
/*      */     finally
/*      */     {
/* 1009 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */   }
/*      */ 
/*      */   public void setConstraintSolver(ConstraintSolver solver) {
/* 1013 */     if (this.ownsConstraintSolver);
/* 1016 */     this.ownsConstraintSolver = false;
/* 1017 */     this.constraintSolver = solver;
/*      */   }
/*      */ 
/*      */   public ConstraintSolver getConstraintSolver()
/*      */   {
/* 1022 */     return this.constraintSolver;
/*      */   }
/*      */ 
/*      */   public int getNumConstraints()
/*      */   {
/* 1027 */     return this.constraints.size();
/*      */   }
/*      */ 
/*      */   public TypedConstraint getConstraint(int index)
/*      */   {
/* 1032 */     return (TypedConstraint)this.constraints.getQuick(index);
/*      */   }
/*      */ 
/*      */   public int getNumActions()
/*      */   {
/* 1038 */     return this.actions.size();
/*      */   }
/*      */ 
/*      */   public ActionInterface getAction(int index)
/*      */   {
/* 1044 */     return (ActionInterface)this.actions.getQuick(index);
/*      */   }
/*      */ 
/*      */   public SimulationIslandManager getSimulationIslandManager() {
/* 1048 */     return this.islandManager;
/*      */   }
/*      */ 
/*      */   public CollisionWorld getCollisionWorld() {
/* 1052 */     return this;
/*      */   }
/*      */ 
/*      */   public DynamicsWorldType getWorldType()
/*      */   {
/* 1057 */     return DynamicsWorldType.DISCRETE_DYNAMICS_WORLD;
/*      */   }
/*      */ 
/*      */   public void setNumTasks(int numTasks)
/*      */   {
/*      */   }
/*      */ 
/*      */   private static class ClosestNotMeConvexResultCallback extends CollisionWorld.ClosestConvexResultCallback
/*      */   {
/*      */     private CollisionObject me;
/* 1107 */     private float allowedPenetration = 0.0F;
/*      */     private OverlappingPairCache pairCache;
/*      */     private Dispatcher dispatcher;
/*      */ 
/*      */     public ClosestNotMeConvexResultCallback(CollisionObject me, Vector3f fromA, Vector3f toA, OverlappingPairCache pairCache, Dispatcher dispatcher)
/*      */     {
/* 1112 */       super(toA);
/* 1113 */       this.me = me;
/* 1114 */       this.pairCache = pairCache;
/* 1115 */       this.dispatcher = dispatcher;
/*      */     }
/*      */ 
/*      */     public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
/*      */     {
/* 1120 */       .Stack localStack = .Stack.get();
/*      */       try { localStack.push$javax$vecmath$Vector3f(); if (convexResult.hitCollisionObject == this.me) {
/* 1121 */           return 1.0F;
/*      */         }
/*      */ 
/* 1124 */         Vector3f linVelA = localStack.get$javax$vecmath$Vector3f(); Vector3f linVelB = localStack.get$javax$vecmath$Vector3f();
/* 1125 */         linVelA.sub(this.convexToWorld, this.convexFromWorld);
/* 1126 */         linVelB.set(0.0F, 0.0F, 0.0F);
/*      */ 
/* 1128 */         Vector3f relativeVelocity = localStack.get$javax$vecmath$Vector3f();
/* 1129 */         relativeVelocity.sub(linVelA, linVelB);
/*      */ 
/* 1131 */         if (convexResult.hitNormalLocal.dot(relativeVelocity) >= -this.allowedPenetration) {
/* 1132 */           return 1.0F;
/*      */         }
/*      */ 
/* 1135 */         return super.addSingleResult(convexResult, normalInWorldSpace); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*      */     }
/*      */ 
/*      */     public boolean needsCollision(BroadphaseProxy proxy0)
/*      */     {
/* 1141 */       if (proxy0.clientObject == this.me) {
/* 1142 */         return false;
/*      */       }
/*      */ 
/* 1146 */       if (!super.needsCollision(proxy0)) {
/* 1147 */         return false;
/*      */       }
/*      */ 
/* 1150 */       CollisionObject otherObj = (CollisionObject)proxy0.clientObject;
/*      */ 
/* 1153 */       if (this.dispatcher.needsResponse(this.me, otherObj))
/*      */       {
/* 1155 */         ObjectArrayList manifoldArray = new ObjectArrayList();
/* 1156 */         BroadphasePair collisionPair = this.pairCache.findPair(this.me.getBroadphaseHandle(), proxy0);
/* 1157 */         if ((collisionPair != null) && 
/* 1158 */           (collisionPair.algorithm != null))
/*      */         {
/* 1160 */           collisionPair.algorithm.getAllContactManifolds(manifoldArray);
/* 1161 */           for (int j = 0; j < manifoldArray.size(); j++) {
/* 1162 */             PersistentManifold manifold = (PersistentManifold)manifoldArray.getQuick(j);
/* 1163 */             if (manifold.getNumContacts() > 0) {
/* 1164 */               return false;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/* 1170 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class InplaceSolverIslandCallback extends SimulationIslandManager.IslandCallback
/*      */   {
/*      */     public ContactSolverInfo solverInfo;
/*      */     public ConstraintSolver solver;
/*      */     public ObjectArrayList<TypedConstraint> sortedConstraints;
/*      */     public int numConstraints;
/*      */     public IDebugDraw debugDrawer;
/*      */     public Dispatcher dispatcher;
/*      */ 
/*      */     public void init(ContactSolverInfo solverInfo, ConstraintSolver solver, ObjectArrayList<TypedConstraint> sortedConstraints, int numConstraints, IDebugDraw debugDrawer, Dispatcher dispatcher)
/*      */     {
/*  570 */       this.solverInfo = solverInfo;
/*  571 */       this.solver = solver;
/*  572 */       this.sortedConstraints = sortedConstraints;
/*  573 */       this.numConstraints = numConstraints;
/*  574 */       this.debugDrawer = debugDrawer;
/*  575 */       this.dispatcher = dispatcher;
/*      */     }
/*      */ 
/*      */     public void processIsland(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifolds, int manifolds_offset, int numManifolds, int islandId) {
/*  579 */       if (islandId < 0)
/*      */       {
/*  581 */         this.solver.solveGroup(bodies, numBodies, manifolds, manifolds_offset, numManifolds, this.sortedConstraints, 0, this.numConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
/*      */       }
/*      */       else
/*      */       {
/*  586 */         int startConstraint_idx = -1;
/*  587 */         int numCurConstraints = 0;
/*      */ 
/*  591 */         for (int i = 0; i < this.numConstraints; i++) {
/*  592 */           if (DiscreteDynamicsWorld.getConstraintIslandId((TypedConstraint)this.sortedConstraints.getQuick(i)) == islandId)
/*      */           {
/*  595 */             startConstraint_idx = i;
/*  596 */             break;
/*      */           }
/*      */         }
/*      */ 
/*  600 */         for (; i < this.numConstraints; i++) {
/*  601 */           if (DiscreteDynamicsWorld.getConstraintIslandId((TypedConstraint)this.sortedConstraints.getQuick(i)) == islandId) {
/*  602 */             numCurConstraints++;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  607 */         if (numManifolds + numCurConstraints > 0)
/*  608 */           this.solver.solveGroup(bodies, numBodies, manifolds, manifolds_offset, numManifolds, this.sortedConstraints, startConstraint_idx, numCurConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
/*      */       }
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.DiscreteDynamicsWorld
 * JD-Core Version:    0.6.2
 */