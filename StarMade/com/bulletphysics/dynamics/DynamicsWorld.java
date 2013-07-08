/*   1:    */package com.bulletphysics.dynamics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*   4:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*   6:    */import com.bulletphysics.collision.dispatch.CollisionWorld;
/*   7:    */import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*   8:    */import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*   9:    */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*  10:    */import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
/*  11:    */import javax.vecmath.Vector3f;
/*  12:    */
/*  42:    */public abstract class DynamicsWorld
/*  43:    */  extends CollisionWorld
/*  44:    */{
/*  45:    */  protected InternalTickCallback internalTickCallback;
/*  46:    */  protected Object worldUserInfo;
/*  47: 47 */  protected final ContactSolverInfo solverInfo = new ContactSolverInfo();
/*  48:    */  
/*  49:    */  public DynamicsWorld(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration) {
/*  50: 50 */    super(dispatcher, broadphasePairCache, collisionConfiguration);
/*  51:    */  }
/*  52:    */  
/*  53:    */  public final int stepSimulation(float timeStep) {
/*  54: 54 */    return stepSimulation(timeStep, 1, 0.01666667F);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public final int stepSimulation(float timeStep, int maxSubSteps) {
/*  58: 58 */    return stepSimulation(timeStep, maxSubSteps, 0.01666667F);
/*  59:    */  }
/*  60:    */  
/*  65:    */  public abstract int stepSimulation(float paramFloat1, int paramInt, float paramFloat2);
/*  66:    */  
/*  71:    */  public abstract void debugDrawWorld();
/*  72:    */  
/*  77:    */  public final void addConstraint(TypedConstraint constraint)
/*  78:    */  {
/*  79: 79 */    addConstraint(constraint, false);
/*  80:    */  }
/*  81:    */  
/*  83:    */  public void addConstraint(TypedConstraint constraint, boolean disableCollisionsBetweenLinkedBodies) {}
/*  84:    */  
/*  86:    */  public void removeConstraint(TypedConstraint constraint) {}
/*  87:    */  
/*  89:    */  public void addAction(ActionInterface action) {}
/*  90:    */  
/*  92:    */  public void removeAction(ActionInterface action) {}
/*  93:    */  
/*  95:    */  public void addVehicle(RaycastVehicle vehicle) {}
/*  96:    */  
/*  98:    */  public void removeVehicle(RaycastVehicle vehicle) {}
/*  99:    */  
/* 101:    */  public abstract void setGravity(Vector3f paramVector3f);
/* 102:    */  
/* 104:    */  public abstract Vector3f getGravity(Vector3f paramVector3f);
/* 105:    */  
/* 107:    */  public abstract void addRigidBody(RigidBody paramRigidBody);
/* 108:    */  
/* 109:    */  public abstract void removeRigidBody(RigidBody paramRigidBody);
/* 110:    */  
/* 111:    */  public abstract void setConstraintSolver(ConstraintSolver paramConstraintSolver);
/* 112:    */  
/* 113:    */  public abstract ConstraintSolver getConstraintSolver();
/* 114:    */  
/* 115:    */  public int getNumConstraints()
/* 116:    */  {
/* 117:117 */    return 0;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public TypedConstraint getConstraint(int index) {
/* 121:121 */    return null;
/* 122:    */  }
/* 123:    */  
/* 124:    */  public int getNumActions()
/* 125:    */  {
/* 126:126 */    return 0;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public ActionInterface getAction(int index)
/* 130:    */  {
/* 131:131 */    return null;
/* 132:    */  }
/* 133:    */  
/* 135:    */  public abstract DynamicsWorldType getWorldType();
/* 136:    */  
/* 138:    */  public abstract void clearForces();
/* 139:    */  
/* 140:    */  public void setInternalTickCallback(InternalTickCallback cb, Object worldUserInfo)
/* 141:    */  {
/* 142:142 */    this.internalTickCallback = cb;
/* 143:143 */    this.worldUserInfo = worldUserInfo;
/* 144:    */  }
/* 145:    */  
/* 146:    */  public void setWorldUserInfo(Object worldUserInfo) {
/* 147:147 */    this.worldUserInfo = worldUserInfo;
/* 148:    */  }
/* 149:    */  
/* 150:    */  public Object getWorldUserInfo() {
/* 151:151 */    return this.worldUserInfo;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public ContactSolverInfo getSolverInfo() {
/* 155:155 */    return this.solverInfo;
/* 156:    */  }
/* 157:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.DynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */