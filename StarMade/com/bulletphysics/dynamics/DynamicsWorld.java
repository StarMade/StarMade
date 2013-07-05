/*     */ package com.bulletphysics.dynamics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*     */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*     */ import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class DynamicsWorld extends CollisionWorld
/*     */ {
/*     */   protected InternalTickCallback internalTickCallback;
/*     */   protected Object worldUserInfo;
/*  47 */   protected final ContactSolverInfo solverInfo = new ContactSolverInfo();
/*     */ 
/*     */   public DynamicsWorld(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration) {
/*  50 */     super(dispatcher, broadphasePairCache, collisionConfiguration);
/*     */   }
/*     */ 
/*     */   public final int stepSimulation(float timeStep) {
/*  54 */     return stepSimulation(timeStep, 1, 0.01666667F);
/*     */   }
/*     */ 
/*     */   public final int stepSimulation(float timeStep, int maxSubSteps) {
/*  58 */     return stepSimulation(timeStep, maxSubSteps, 0.01666667F);
/*     */   }
/*     */ 
/*     */   public abstract int stepSimulation(float paramFloat1, int paramInt, float paramFloat2);
/*     */ 
/*     */   public abstract void debugDrawWorld();
/*     */ 
/*     */   public final void addConstraint(TypedConstraint constraint)
/*     */   {
/*  79 */     addConstraint(constraint, false);
/*     */   }
/*     */ 
/*     */   public void addConstraint(TypedConstraint constraint, boolean disableCollisionsBetweenLinkedBodies)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void removeConstraint(TypedConstraint constraint)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void addAction(ActionInterface action)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void removeAction(ActionInterface action)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void addVehicle(RaycastVehicle vehicle) {
/*     */   }
/*     */ 
/*     */   public void removeVehicle(RaycastVehicle vehicle) {
/*     */   }
/*     */ 
/*     */   public abstract void setGravity(Vector3f paramVector3f);
/*     */ 
/*     */   public abstract Vector3f getGravity(Vector3f paramVector3f);
/*     */ 
/*     */   public abstract void addRigidBody(RigidBody paramRigidBody);
/*     */ 
/*     */   public abstract void removeRigidBody(RigidBody paramRigidBody);
/*     */ 
/*     */   public abstract void setConstraintSolver(ConstraintSolver paramConstraintSolver);
/*     */ 
/*     */   public abstract ConstraintSolver getConstraintSolver();
/*     */ 
/*     */   public int getNumConstraints() {
/* 117 */     return 0;
/*     */   }
/*     */ 
/*     */   public TypedConstraint getConstraint(int index) {
/* 121 */     return null;
/*     */   }
/*     */ 
/*     */   public int getNumActions()
/*     */   {
/* 126 */     return 0;
/*     */   }
/*     */ 
/*     */   public ActionInterface getAction(int index)
/*     */   {
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   public abstract DynamicsWorldType getWorldType();
/*     */ 
/*     */   public abstract void clearForces();
/*     */ 
/*     */   public void setInternalTickCallback(InternalTickCallback cb, Object worldUserInfo)
/*     */   {
/* 142 */     this.internalTickCallback = cb;
/* 143 */     this.worldUserInfo = worldUserInfo;
/*     */   }
/*     */ 
/*     */   public void setWorldUserInfo(Object worldUserInfo) {
/* 147 */     this.worldUserInfo = worldUserInfo;
/*     */   }
/*     */ 
/*     */   public Object getWorldUserInfo() {
/* 151 */     return this.worldUserInfo;
/*     */   }
/*     */ 
/*     */   public ContactSolverInfo getSolverInfo() {
/* 155 */     return this.solverInfo;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.DynamicsWorld
 * JD-Core Version:    0.6.2
 */