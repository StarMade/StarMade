/*     */ package com.bulletphysics.dynamics;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*     */ import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*     */ import com.bulletphysics.linearmath.MotionState;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class SimpleDynamicsWorld extends DynamicsWorld
/*     */ {
/*     */   protected ConstraintSolver constraintSolver;
/*     */   protected boolean ownsConstraintSolver;
/*  51 */   protected final Vector3f gravity = new Vector3f(0.0F, 0.0F, -10.0F);
/*     */ 
/*     */   public SimpleDynamicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver, CollisionConfiguration collisionConfiguration) {
/*  54 */     super(dispatcher, pairCache, collisionConfiguration);
/*  55 */     this.constraintSolver = constraintSolver;
/*  56 */     this.ownsConstraintSolver = false;
/*     */   }
/*     */ 
/*     */   protected void predictUnconstraintMotion(float arg1) {
/*  60 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/*  62 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  63 */         CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  64 */         RigidBody body = RigidBody.upcast(colObj);
/*  65 */         if ((body != null) && 
/*  66 */           (!body.isStaticObject()) && 
/*  67 */           (body.isActive())) {
/*  68 */           body.applyGravity();
/*  69 */           body.integrateVelocities(timeStep);
/*  70 */           body.applyDamping(timeStep);
/*  71 */           body.predictIntegratedTransform(timeStep, body.getInterpolationWorldTransform(tmpTrans));
/*     */         }
/*     */       }
/*     */       return;
/*     */     } finally {
/*  76 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   protected void integrateTransforms(float arg1) {
/*  79 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  80 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  81 */         CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  82 */         RigidBody body = RigidBody.upcast(colObj);
/*  83 */         if ((body != null) && 
/*  84 */           (body.isActive()) && (!body.isStaticObject())) {
/*  85 */           body.predictIntegratedTransform(timeStep, predictedTrans);
/*  86 */           body.proceedToTransform(predictedTrans);
/*     */         }
/*     */       }
/*     */       return; } finally {
/*  90 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public int stepSimulation(float timeStep, int maxSubSteps, float fixedTimeStep)
/*     */   {
/*  98 */     predictUnconstraintMotion(timeStep);
/*     */ 
/* 100 */     DispatcherInfo dispatchInfo = getDispatchInfo();
/* 101 */     dispatchInfo.timeStep = timeStep;
/* 102 */     dispatchInfo.stepCount = 0;
/* 103 */     dispatchInfo.debugDraw = getDebugDrawer();
/*     */ 
/* 106 */     performDiscreteCollisionDetection();
/*     */ 
/* 109 */     int numManifolds = this.dispatcher1.getNumManifolds();
/* 110 */     if (numManifolds != 0)
/*     */     {
/* 112 */       ObjectArrayList manifoldPtr = ((CollisionDispatcher)this.dispatcher1).getInternalManifoldPointer();
/*     */ 
/* 114 */       ContactSolverInfo infoGlobal = new ContactSolverInfo();
/* 115 */       infoGlobal.timeStep = timeStep;
/* 116 */       this.constraintSolver.prepareSolve(0, numManifolds);
/* 117 */       this.constraintSolver.solveGroup(null, 0, manifoldPtr, 0, numManifolds, null, 0, 0, infoGlobal, this.debugDrawer, this.dispatcher1);
/* 118 */       this.constraintSolver.allSolved(infoGlobal, this.debugDrawer);
/*     */     }
/*     */ 
/* 122 */     integrateTransforms(timeStep);
/*     */ 
/* 124 */     updateAabbs();
/*     */ 
/* 126 */     synchronizeMotionStates();
/*     */ 
/* 128 */     clearForces();
/*     */ 
/* 130 */     return 1;
/*     */   }
/*     */ 
/*     */   public void clearForces()
/*     */   {
/* 136 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 137 */       CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*     */ 
/* 139 */       RigidBody body = RigidBody.upcast(colObj);
/* 140 */       if (body != null)
/* 141 */         body.clearForces();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setGravity(Vector3f gravity)
/*     */   {
/* 148 */     this.gravity.set(gravity);
/* 149 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 150 */       CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 151 */       RigidBody body = RigidBody.upcast(colObj);
/* 152 */       if (body != null)
/* 153 */         body.setGravity(gravity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Vector3f getGravity(Vector3f out)
/*     */   {
/* 160 */     out.set(this.gravity);
/* 161 */     return out;
/*     */   }
/*     */ 
/*     */   public void addRigidBody(RigidBody body)
/*     */   {
/* 166 */     body.setGravity(this.gravity);
/*     */ 
/* 168 */     if (body.getCollisionShape() != null)
/* 169 */       addCollisionObject(body);
/*     */   }
/*     */ 
/*     */   public void removeRigidBody(RigidBody body)
/*     */   {
/* 175 */     removeCollisionObject(body);
/*     */   }
/*     */ 
/*     */   public void updateAabbs()
/*     */   {
/* 180 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 181 */       Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 182 */       Vector3f minAabb = localStack.get$javax$vecmath$Vector3f(); Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 184 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 185 */         CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 186 */         RigidBody body = RigidBody.upcast(colObj);
/* 187 */         if ((body != null) && 
/* 188 */           (body.isActive()) && (!body.isStaticObject())) {
/* 189 */           colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
/* 190 */           BroadphaseInterface bp = getBroadphase();
/* 191 */           bp.setAabb(body.getBroadphaseHandle(), minAabb, maxAabb, this.dispatcher1);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 195 */       .Stack tmp154_152 = localStack; tmp154_152.pop$com$bulletphysics$linearmath$Transform(); tmp154_152.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void synchronizeMotionStates() {
/* 198 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 201 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 202 */         CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 203 */         RigidBody body = RigidBody.upcast(colObj);
/* 204 */         if ((body != null) && (body.getMotionState() != null) && 
/* 205 */           (body.getActivationState() != 2))
/* 206 */           body.getMotionState().setWorldTransform(body.getWorldTransform(tmpTrans));
/*     */       }
/*     */       return;
/*     */     } finally {
/* 210 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setConstraintSolver(ConstraintSolver solver) {
/* 214 */     if (this.ownsConstraintSolver);
/* 218 */     this.ownsConstraintSolver = false;
/* 219 */     this.constraintSolver = solver;
/*     */   }
/*     */ 
/*     */   public ConstraintSolver getConstraintSolver()
/*     */   {
/* 224 */     return this.constraintSolver;
/*     */   }
/*     */ 
/*     */   public void debugDrawWorld()
/*     */   {
/*     */   }
/*     */ 
/*     */   public DynamicsWorldType getWorldType()
/*     */   {
/* 234 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.SimpleDynamicsWorld
 * JD-Core Version:    0.6.2
 */