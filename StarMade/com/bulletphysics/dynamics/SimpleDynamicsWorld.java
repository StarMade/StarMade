/*   1:    */package com.bulletphysics.dynamics;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*   5:    */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   6:    */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   7:    */import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*   8:    */import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*   9:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  10:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*  11:    */import com.bulletphysics.collision.shapes.CollisionShape;
/*  12:    */import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*  13:    */import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*  14:    */import com.bulletphysics.linearmath.MotionState;
/*  15:    */import com.bulletphysics.linearmath.Transform;
/*  16:    */import com.bulletphysics.util.ObjectArrayList;
/*  17:    */import javax.vecmath.Vector3f;
/*  18:    */
/*  46:    */public class SimpleDynamicsWorld
/*  47:    */  extends DynamicsWorld
/*  48:    */{
/*  49:    */  protected ConstraintSolver constraintSolver;
/*  50:    */  protected boolean ownsConstraintSolver;
/*  51: 51 */  protected final Vector3f gravity = new Vector3f(0.0F, 0.0F, -10.0F);
/*  52:    */  
/*  53:    */  public SimpleDynamicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver, CollisionConfiguration collisionConfiguration) {
/*  54: 54 */    super(dispatcher, pairCache, collisionConfiguration);
/*  55: 55 */    this.constraintSolver = constraintSolver;
/*  56: 56 */    this.ownsConstraintSolver = false;
/*  57:    */  }
/*  58:    */  
/*  59:    */  protected void predictUnconstraintMotion(float arg1) {
/*  60: 60 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  61:    */      
/*  62: 62 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  63: 63 */        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  64: 64 */        RigidBody body = RigidBody.upcast(colObj);
/*  65: 65 */        if ((body != null) && 
/*  66: 66 */          (!body.isStaticObject()) && 
/*  67: 67 */          (body.isActive())) {
/*  68: 68 */          body.applyGravity();
/*  69: 69 */          body.integrateVelocities(timeStep);
/*  70: 70 */          body.applyDamping(timeStep);
/*  71: 71 */          body.predictIntegratedTransform(timeStep, body.getInterpolationWorldTransform(tmpTrans));
/*  72:    */        }
/*  73:    */      }
/*  74:    */    }
/*  75:    */    finally {
/*  76: 76 */      localStack.pop$com$bulletphysics$linearmath$Transform();
/*  77:    */    } }
/*  78:    */  
/*  79: 79 */  protected void integrateTransforms(float arg1) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  80: 80 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  81: 81 */        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  82: 82 */        RigidBody body = RigidBody.upcast(colObj);
/*  83: 83 */        if ((body != null) && 
/*  84: 84 */          (body.isActive()) && (!body.isStaticObject())) {
/*  85: 85 */          body.predictIntegratedTransform(timeStep, predictedTrans);
/*  86: 86 */          body.proceedToTransform(predictedTrans);
/*  87:    */        }
/*  88:    */      }
/*  89:    */    } finally {
/*  90: 90 */      localStack.pop$com$bulletphysics$linearmath$Transform();
/*  91:    */    }
/*  92:    */  }
/*  93:    */  
/*  96:    */  public int stepSimulation(float timeStep, int maxSubSteps, float fixedTimeStep)
/*  97:    */  {
/*  98: 98 */    predictUnconstraintMotion(timeStep);
/*  99:    */    
/* 100:100 */    DispatcherInfo dispatchInfo = getDispatchInfo();
/* 101:101 */    dispatchInfo.timeStep = timeStep;
/* 102:102 */    dispatchInfo.stepCount = 0;
/* 103:103 */    dispatchInfo.debugDraw = getDebugDrawer();
/* 104:    */    
/* 106:106 */    performDiscreteCollisionDetection();
/* 107:    */    
/* 109:109 */    int numManifolds = this.dispatcher1.getNumManifolds();
/* 110:110 */    if (numManifolds != 0)
/* 111:    */    {
/* 112:112 */      ObjectArrayList<PersistentManifold> manifoldPtr = ((CollisionDispatcher)this.dispatcher1).getInternalManifoldPointer();
/* 113:    */      
/* 114:114 */      ContactSolverInfo infoGlobal = new ContactSolverInfo();
/* 115:115 */      infoGlobal.timeStep = timeStep;
/* 116:116 */      this.constraintSolver.prepareSolve(0, numManifolds);
/* 117:117 */      this.constraintSolver.solveGroup(null, 0, manifoldPtr, 0, numManifolds, null, 0, 0, infoGlobal, this.debugDrawer, this.dispatcher1);
/* 118:118 */      this.constraintSolver.allSolved(infoGlobal, this.debugDrawer);
/* 119:    */    }
/* 120:    */    
/* 122:122 */    integrateTransforms(timeStep);
/* 123:    */    
/* 124:124 */    updateAabbs();
/* 125:    */    
/* 126:126 */    synchronizeMotionStates();
/* 127:    */    
/* 128:128 */    clearForces();
/* 129:    */    
/* 130:130 */    return 1;
/* 131:    */  }
/* 132:    */  
/* 134:    */  public void clearForces()
/* 135:    */  {
/* 136:136 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 137:137 */      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 138:    */      
/* 139:139 */      RigidBody body = RigidBody.upcast(colObj);
/* 140:140 */      if (body != null) {
/* 141:141 */        body.clearForces();
/* 142:    */      }
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 146:    */  public void setGravity(Vector3f gravity)
/* 147:    */  {
/* 148:148 */    this.gravity.set(gravity);
/* 149:149 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 150:150 */      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 151:151 */      RigidBody body = RigidBody.upcast(colObj);
/* 152:152 */      if (body != null) {
/* 153:153 */        body.setGravity(gravity);
/* 154:    */      }
/* 155:    */    }
/* 156:    */  }
/* 157:    */  
/* 158:    */  public Vector3f getGravity(Vector3f out)
/* 159:    */  {
/* 160:160 */    out.set(this.gravity);
/* 161:161 */    return out;
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void addRigidBody(RigidBody body)
/* 165:    */  {
/* 166:166 */    body.setGravity(this.gravity);
/* 167:    */    
/* 168:168 */    if (body.getCollisionShape() != null) {
/* 169:169 */      addCollisionObject(body);
/* 170:    */    }
/* 171:    */  }
/* 172:    */  
/* 173:    */  public void removeRigidBody(RigidBody body)
/* 174:    */  {
/* 175:175 */    removeCollisionObject(body);
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void updateAabbs()
/* 179:    */  {
/* 180:180 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 181:181 */      Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 182:182 */      Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/* 183:    */      
/* 184:184 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 185:185 */        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 186:186 */        RigidBody body = RigidBody.upcast(colObj);
/* 187:187 */        if ((body != null) && 
/* 188:188 */          (body.isActive()) && (!body.isStaticObject())) {
/* 189:189 */          colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
/* 190:190 */          BroadphaseInterface bp = getBroadphase();
/* 191:191 */          bp.setAabb(body.getBroadphaseHandle(), minAabb, maxAabb, this.dispatcher1);
/* 192:    */        }
/* 193:    */      }
/* 194:    */    } finally {
/* 195:195 */      .Stack tmp154_152 = localStack;tmp154_152.pop$com$bulletphysics$linearmath$Transform();tmp154_152.pop$javax$vecmath$Vector3f();
/* 196:    */    } }
/* 197:    */  
/* 198:198 */  public void synchronizeMotionStates() { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 199:    */      
/* 201:201 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 202:202 */        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/* 203:203 */        RigidBody body = RigidBody.upcast(colObj);
/* 204:204 */        if ((body != null) && (body.getMotionState() != null) && 
/* 205:205 */          (body.getActivationState() != 2)) {
/* 206:206 */          body.getMotionState().setWorldTransform(body.getWorldTransform(tmpTrans));
/* 207:    */        }
/* 208:    */      }
/* 209:    */    } finally {
/* 210:210 */      localStack.pop$com$bulletphysics$linearmath$Transform();
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 214:214 */  public void setConstraintSolver(ConstraintSolver solver) { if (this.ownsConstraintSolver) {}
/* 215:    */    
/* 218:218 */    this.ownsConstraintSolver = false;
/* 219:219 */    this.constraintSolver = solver;
/* 220:    */  }
/* 221:    */  
/* 222:    */  public ConstraintSolver getConstraintSolver()
/* 223:    */  {
/* 224:224 */    return this.constraintSolver;
/* 225:    */  }
/* 226:    */  
/* 229:    */  public void debugDrawWorld() {}
/* 230:    */  
/* 232:    */  public DynamicsWorldType getWorldType()
/* 233:    */  {
/* 234:234 */    throw new UnsupportedOperationException("Not supported yet.");
/* 235:    */  }
/* 236:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.SimpleDynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */