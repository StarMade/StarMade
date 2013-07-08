/*    1:     */package com.bulletphysics.dynamics;
/*    2:     */
/*    3:     */import com.bulletphysics..Stack;
/*    4:     */import com.bulletphysics.BulletGlobals;
/*    5:     */import com.bulletphysics.BulletStats;
/*    6:     */import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*    7:     */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    8:     */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    9:     */import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*   10:     */import com.bulletphysics.collision.broadphase.Dispatcher;
/*   11:     */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*   12:     */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*   13:     */import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*   14:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   15:     */import com.bulletphysics.collision.dispatch.CollisionWorld;
/*   16:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*   17:     */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*   18:     */import com.bulletphysics.collision.dispatch.SimulationIslandManager;
/*   19:     */import com.bulletphysics.collision.dispatch.SimulationIslandManager.IslandCallback;
/*   20:     */import com.bulletphysics.collision.dispatch.UnionFind;
/*   21:     */import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*   22:     */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   23:     */import com.bulletphysics.collision.shapes.CollisionShape;
/*   24:     */import com.bulletphysics.collision.shapes.SphereShape;
/*   25:     */import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*   26:     */import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*   27:     */import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
/*   28:     */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   29:     */import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
/*   30:     */import com.bulletphysics.dynamics.vehicle.WheelInfo;
/*   31:     */import com.bulletphysics.dynamics.vehicle.WheelInfo.RaycastInfo;
/*   32:     */import com.bulletphysics.linearmath.CProfileManager;
/*   33:     */import com.bulletphysics.linearmath.IDebugDraw;
/*   34:     */import com.bulletphysics.linearmath.MiscUtil;
/*   35:     */import com.bulletphysics.linearmath.MotionState;
/*   36:     */import com.bulletphysics.linearmath.ScalarUtil;
/*   37:     */import com.bulletphysics.linearmath.Transform;
/*   38:     */import com.bulletphysics.linearmath.TransformUtil;
/*   39:     */import com.bulletphysics.util.ObjectArrayList;
/*   40:     */import java.util.Comparator;
/*   41:     */import javax.vecmath.Matrix3f;
/*   42:     */import javax.vecmath.Vector3f;
/*   43:     */
/*   68:     */public class DiscreteDynamicsWorld
/*   69:     */  extends DynamicsWorld
/*   70:     */{
/*   71:     */  protected ConstraintSolver constraintSolver;
/*   72:     */  protected SimulationIslandManager islandManager;
/*   73:  73 */  protected final ObjectArrayList<TypedConstraint> constraints = new ObjectArrayList();
/*   74:  74 */  protected final Vector3f gravity = new Vector3f(0.0F, -10.0F, 0.0F);
/*   75:     */  
/*   77:  77 */  protected float localTime = 0.01666667F;
/*   78:     */  
/*   79:     */  protected boolean ownsIslandManager;
/*   80:     */  
/*   81:     */  protected boolean ownsConstraintSolver;
/*   82:     */  
/*   83:  83 */  protected ObjectArrayList<RaycastVehicle> vehicles = new ObjectArrayList();
/*   84:     */  
/*   85:  85 */  protected ObjectArrayList<ActionInterface> actions = new ObjectArrayList();
/*   86:     */  
/*   87:  87 */  protected int profileTimings = 0;
/*   88:     */  
/*   89:     */  public DiscreteDynamicsWorld(Dispatcher dispatcher, BroadphaseInterface pairCache, ConstraintSolver constraintSolver, CollisionConfiguration collisionConfiguration) {
/*   90:  90 */    super(dispatcher, pairCache, collisionConfiguration);
/*   91:  91 */    this.constraintSolver = constraintSolver;
/*   92:     */    
/*   93:  93 */    if (this.constraintSolver == null) {
/*   94:  94 */      this.constraintSolver = new SequentialImpulseConstraintSolver();
/*   95:  95 */      this.ownsConstraintSolver = true;
/*   96:     */    }
/*   97:     */    else {
/*   98:  98 */      this.ownsConstraintSolver = false;
/*   99:     */    }
/*  100:     */    
/*  102: 102 */    this.islandManager = new SimulationIslandManager();
/*  103:     */    
/*  105: 105 */    this.ownsIslandManager = true;
/*  106:     */  }
/*  107:     */  
/*  108:     */  protected void saveKinematicState(float timeStep) {
/*  109: 109 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  110: 110 */      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  111: 111 */      RigidBody body = RigidBody.upcast(colObj);
/*  112: 112 */      if (body != null)
/*  113:     */      {
/*  114: 114 */        if ((body.getActivationState() != 2) && 
/*  115: 115 */          (body.isKinematicObject()))
/*  116:     */        {
/*  117: 117 */          body.saveKinematicState(timeStep);
/*  118:     */        }
/*  119:     */      }
/*  120:     */    }
/*  121:     */  }
/*  122:     */  
/*  124:     */  public void debugDrawWorld()
/*  125:     */  {
/*  126: 126 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f(); if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x8) != 0)) {
/*  127: 127 */        int numManifolds = getDispatcher().getNumManifolds();
/*  128: 128 */        Vector3f color = localStack.get$javax$vecmath$Vector3f();
/*  129: 129 */        color.set(0.0F, 0.0F, 0.0F);
/*  130: 130 */        for (int i = 0; i < numManifolds; i++) {
/*  131: 131 */          PersistentManifold contactManifold = getDispatcher().getManifoldByIndexInternal(i);
/*  132:     */          
/*  135: 135 */          int numContacts = contactManifold.getNumContacts();
/*  136: 136 */          for (int j = 0; j < numContacts; j++) {
/*  137: 137 */            ManifoldPoint cp = contactManifold.getContactPoint(j);
/*  138: 138 */            getDebugDrawer().drawContactPoint(cp.positionWorldOnB, cp.normalWorldOnB, cp.getDistance(), cp.getLifeTime(), color);
/*  139:     */          }
/*  140:     */        }
/*  141:     */      }
/*  142:     */      
/*  143: 143 */      if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x3) != 0))
/*  144:     */      {
/*  146: 146 */        Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  147: 147 */        Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
/*  148: 148 */        Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/*  149: 149 */        Vector3f colorvec = localStack.get$javax$vecmath$Vector3f();
/*  150:     */        
/*  152: 152 */        for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  153: 153 */          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  154: 154 */          if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0)) {
/*  155: 155 */            Vector3f color = localStack.get$javax$vecmath$Vector3f();
/*  156: 156 */            color.set(255.0F, 255.0F, 255.0F);
/*  157: 157 */            switch (colObj.getActivationState()) {
/*  158:     */            case 1: 
/*  159: 159 */              color.set(255.0F, 255.0F, 255.0F);
/*  160: 160 */              break;
/*  161:     */            case 2: 
/*  162: 162 */              color.set(0.0F, 255.0F, 0.0F);
/*  163: 163 */              break;
/*  164:     */            case 3: 
/*  165: 165 */              color.set(0.0F, 255.0F, 255.0F);
/*  166: 166 */              break;
/*  167:     */            case 4: 
/*  168: 168 */              color.set(255.0F, 0.0F, 0.0F);
/*  169: 169 */              break;
/*  170:     */            case 5: 
/*  171: 171 */              color.set(255.0F, 255.0F, 0.0F);
/*  172: 172 */              break;
/*  173:     */            default: 
/*  174: 174 */              color.set(255.0F, 0.0F, 0.0F);
/*  175:     */            }
/*  176:     */            
/*  177:     */            
/*  178: 178 */            debugDrawObject(colObj.getWorldTransform(tmpTrans), colObj.getCollisionShape(), color);
/*  179:     */          }
/*  180: 180 */          if ((this.debugDrawer != null) && ((this.debugDrawer.getDebugMode() & 0x2) != 0)) {
/*  181: 181 */            colorvec.set(1.0F, 0.0F, 0.0F);
/*  182: 182 */            colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
/*  183: 183 */            this.debugDrawer.drawAabb(minAabb, maxAabb, colorvec);
/*  184:     */          }
/*  185:     */        }
/*  186:     */        
/*  187: 187 */        Vector3f wheelColor = localStack.get$javax$vecmath$Vector3f();
/*  188: 188 */        Vector3f wheelPosWS = localStack.get$javax$vecmath$Vector3f();
/*  189: 189 */        Vector3f axle = localStack.get$javax$vecmath$Vector3f();
/*  190: 190 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  191:     */        
/*  192: 192 */        for (i = 0; i < this.vehicles.size(); i++) {
/*  193: 193 */          for (int v = 0; v < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); v++) {
/*  194: 194 */            wheelColor.set(0.0F, 255.0F, 255.0F);
/*  195: 195 */            if (((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).raycastInfo.isInContact) {
/*  196: 196 */              wheelColor.set(0.0F, 0.0F, 255.0F);
/*  197:     */            }
/*  198:     */            else {
/*  199: 199 */              wheelColor.set(255.0F, 0.0F, 255.0F);
/*  200:     */            }
/*  201:     */            
/*  202: 202 */            wheelPosWS.set(((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.origin);
/*  203:     */            
/*  204: 204 */            axle.set(((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.basis.getElement(0, ((RaycastVehicle)this.vehicles.getQuick(i)).getRightAxis()), ((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.basis.getElement(1, ((RaycastVehicle)this.vehicles.getQuick(i)).getRightAxis()), ((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).worldTransform.basis.getElement(2, ((RaycastVehicle)this.vehicles.getQuick(i)).getRightAxis()));
/*  205:     */            
/*  212: 212 */            tmp.add(wheelPosWS, axle);
/*  213: 213 */            this.debugDrawer.drawLine(wheelPosWS, tmp, wheelColor);
/*  214: 214 */            this.debugDrawer.drawLine(wheelPosWS, ((RaycastVehicle)this.vehicles.getQuick(i)).getWheelInfo(v).raycastInfo.contactPointWS, wheelColor);
/*  215:     */          }
/*  216:     */        }
/*  217:     */        
/*  218: 218 */        if ((getDebugDrawer() != null) && (getDebugDrawer().getDebugMode() != 0)) {
/*  219: 219 */          for (i = 0; i < this.actions.size(); i++)
/*  220: 220 */            ((ActionInterface)this.actions.getQuick(i)).debugDraw(this.debugDrawer);
/*  221:     */        }
/*  222:     */      }
/*  223:     */    } finally {
/*  224: 224 */      .Stack tmp841_839 = localStack;tmp841_839.pop$com$bulletphysics$linearmath$Transform();tmp841_839.pop$javax$vecmath$Vector3f();
/*  225:     */    }
/*  226:     */  }
/*  227:     */  
/*  228:     */  public void clearForces() {
/*  229: 229 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  230: 230 */      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  231:     */      
/*  232: 232 */      RigidBody body = RigidBody.upcast(colObj);
/*  233: 233 */      if (body != null) {
/*  234: 234 */        body.clearForces();
/*  235:     */      }
/*  236:     */    }
/*  237:     */  }
/*  238:     */  
/*  242:     */  public void applyGravity()
/*  243:     */  {
/*  244: 244 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  245: 245 */      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  246:     */      
/*  247: 247 */      RigidBody body = RigidBody.upcast(colObj);
/*  248: 248 */      if ((body != null) && (body.isActive())) {
/*  249: 249 */        body.applyGravity();
/*  250:     */      }
/*  251:     */    }
/*  252:     */  }
/*  253:     */  
/*  254:     */  protected void synchronizeMotionStates() {
/*  255: 255 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();Transform interpolatedTransform = localStack.get$com$bulletphysics$linearmath$Transform();
/*  256:     */      
/*  257: 257 */      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  258: 258 */      Vector3f tmpLinVel = localStack.get$javax$vecmath$Vector3f();
/*  259: 259 */      Vector3f tmpAngVel = localStack.get$javax$vecmath$Vector3f();
/*  260:     */      
/*  262: 262 */      for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  263: 263 */        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  264:     */        
/*  265: 265 */        RigidBody body = RigidBody.upcast(colObj);
/*  266: 266 */        if ((body != null) && (body.getMotionState() != null) && (!body.isStaticOrKinematicObject()))
/*  267:     */        {
/*  272: 272 */          TransformUtil.integrateTransform(body.getInterpolationWorldTransform(tmpTrans), body.getInterpolationLinearVelocity(tmpLinVel), body.getInterpolationAngularVelocity(tmpAngVel), this.localTime * body.getHitFraction(), interpolatedTransform);
/*  273:     */          
/*  277: 277 */          body.getMotionState().setWorldTransform(interpolatedTransform);
/*  278:     */        }
/*  279:     */      }
/*  280:     */      
/*  282: 282 */      if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0)) {
/*  283: 283 */        for (int i = 0; i < this.vehicles.size(); i++) {
/*  284: 284 */          for (int v = 0; v < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); v++)
/*  285:     */          {
/*  286: 286 */            ((RaycastVehicle)this.vehicles.getQuick(i)).updateWheelTransform(v, true); }
/*  287:     */        }
/*  288:     */      }
/*  289:     */    } finally {
/*  290: 290 */      .Stack tmp243_241 = localStack;tmp243_241.pop$com$bulletphysics$linearmath$Transform();tmp243_241.pop$javax$vecmath$Vector3f();
/*  291:     */    }
/*  292:     */  }
/*  293:     */  
/*  294: 294 */  public int stepSimulation(float timeStep, int maxSubSteps, float fixedTimeStep) { startProfiling(timeStep);
/*  295:     */    
/*  296: 296 */    long t0 = System.nanoTime();
/*  297:     */    
/*  298: 298 */    BulletStats.pushProfile("stepSimulation");
/*  299:     */    try {
/*  300: 300 */      int numSimulationSubSteps = 0;
/*  301:     */      
/*  302: 302 */      if (maxSubSteps != 0)
/*  303:     */      {
/*  304: 304 */        this.localTime += timeStep;
/*  305: 305 */        if (this.localTime >= fixedTimeStep) {
/*  306: 306 */          numSimulationSubSteps = (int)(this.localTime / fixedTimeStep);
/*  307: 307 */          this.localTime -= numSimulationSubSteps * fixedTimeStep;
/*  308:     */        }
/*  309:     */      }
/*  310:     */      else
/*  311:     */      {
/*  312: 312 */        fixedTimeStep = timeStep;
/*  313: 313 */        this.localTime = timeStep;
/*  314: 314 */        if (ScalarUtil.fuzzyZero(timeStep)) {
/*  315: 315 */          numSimulationSubSteps = 0;
/*  316: 316 */          maxSubSteps = 0;
/*  317:     */        }
/*  318:     */        else {
/*  319: 319 */          numSimulationSubSteps = 1;
/*  320: 320 */          maxSubSteps = 1;
/*  321:     */        }
/*  322:     */      }
/*  323:     */      
/*  325: 325 */      if (getDebugDrawer() != null)
/*  326: 326 */        BulletGlobals.setDeactivationDisabled((getDebugDrawer().getDebugMode() & 0x10) != 0);
/*  327:     */      int clampedSimulationSteps;
/*  328: 328 */      if (numSimulationSubSteps != 0) {
/*  329: 329 */        saveKinematicState(fixedTimeStep);
/*  330:     */        
/*  331: 331 */        applyGravity();
/*  332:     */        
/*  334: 334 */        clampedSimulationSteps = numSimulationSubSteps > maxSubSteps ? maxSubSteps : numSimulationSubSteps;
/*  335:     */        
/*  336: 336 */        for (int i = 0; i < clampedSimulationSteps; i++) {
/*  337: 337 */          internalSingleStepSimulation(fixedTimeStep);
/*  338: 338 */          synchronizeMotionStates();
/*  339:     */        }
/*  340:     */      }
/*  341:     */      
/*  342: 342 */      synchronizeMotionStates();
/*  343:     */      
/*  344: 344 */      clearForces();
/*  345:     */      
/*  347: 347 */      CProfileManager.incrementFrameCounter();
/*  348:     */      
/*  350: 350 */      return numSimulationSubSteps;
/*  351:     */    }
/*  352:     */    finally {
/*  353: 353 */      BulletStats.popProfile();
/*  354:     */      
/*  355: 355 */      BulletStats.stepSimulationTime = (System.nanoTime() - t0) / 1000000L;
/*  356:     */    }
/*  357:     */  }
/*  358:     */  
/*  359:     */  protected void internalSingleStepSimulation(float timeStep) {
/*  360: 360 */    BulletStats.pushProfile("internalSingleStepSimulation");
/*  361:     */    try
/*  362:     */    {
/*  363: 363 */      predictUnconstraintMotion(timeStep);
/*  364:     */      
/*  365: 365 */      DispatcherInfo dispatchInfo = getDispatchInfo();
/*  366:     */      
/*  367: 367 */      dispatchInfo.timeStep = timeStep;
/*  368: 368 */      dispatchInfo.stepCount = 0;
/*  369: 369 */      dispatchInfo.debugDraw = getDebugDrawer();
/*  370:     */      
/*  372: 372 */      performDiscreteCollisionDetection();
/*  373:     */      
/*  374: 374 */      calculateSimulationIslands();
/*  375:     */      
/*  376: 376 */      getSolverInfo().timeStep = timeStep;
/*  377:     */      
/*  379: 379 */      solveConstraints(getSolverInfo());
/*  380:     */      
/*  384: 384 */      integrateTransforms(timeStep);
/*  385:     */      
/*  387: 387 */      updateActions(timeStep);
/*  388:     */      
/*  390: 390 */      updateVehicles(timeStep);
/*  391:     */      
/*  392: 392 */      updateActivationState(timeStep);
/*  393:     */      
/*  394: 394 */      if (this.internalTickCallback != null) {
/*  395: 395 */        this.internalTickCallback.internalTick(this, timeStep);
/*  396:     */      }
/*  397:     */    }
/*  398:     */    finally {
/*  399: 399 */      BulletStats.popProfile();
/*  400:     */    }
/*  401:     */  }
/*  402:     */  
/*  403:     */  public void setGravity(Vector3f gravity)
/*  404:     */  {
/*  405: 405 */    this.gravity.set(gravity);
/*  406: 406 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  407: 407 */      CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  408: 408 */      RigidBody body = RigidBody.upcast(colObj);
/*  409: 409 */      if (body != null) {
/*  410: 410 */        body.setGravity(gravity);
/*  411:     */      }
/*  412:     */    }
/*  413:     */  }
/*  414:     */  
/*  415:     */  public Vector3f getGravity(Vector3f out)
/*  416:     */  {
/*  417: 417 */    out.set(this.gravity);
/*  418: 418 */    return out;
/*  419:     */  }
/*  420:     */  
/*  421:     */  public void removeRigidBody(RigidBody body)
/*  422:     */  {
/*  423: 423 */    removeCollisionObject(body);
/*  424:     */  }
/*  425:     */  
/*  426:     */  public void addRigidBody(RigidBody body)
/*  427:     */  {
/*  428: 428 */    if (!body.isStaticOrKinematicObject()) {
/*  429: 429 */      body.setGravity(this.gravity);
/*  430:     */    }
/*  431:     */    
/*  432: 432 */    if (body.getCollisionShape() != null) {
/*  433: 433 */      boolean isDynamic = (!body.isStaticObject()) && (!body.isKinematicObject());
/*  434: 434 */      short collisionFilterGroup = isDynamic ? 1 : 2;
/*  435: 435 */      short collisionFilterMask = isDynamic ? -1 : -3;
/*  436:     */      
/*  437: 437 */      addCollisionObject(body, collisionFilterGroup, collisionFilterMask);
/*  438:     */    }
/*  439:     */  }
/*  440:     */  
/*  441:     */  public void addRigidBody(RigidBody body, short group, short mask) {
/*  442: 442 */    if (!body.isStaticOrKinematicObject()) {
/*  443: 443 */      body.setGravity(this.gravity);
/*  444:     */    }
/*  445:     */    
/*  446: 446 */    if (body.getCollisionShape() != null) {
/*  447: 447 */      addCollisionObject(body, group, mask);
/*  448:     */    }
/*  449:     */  }
/*  450:     */  
/*  451:     */  public void updateActions(float timeStep) {
/*  452: 452 */    BulletStats.pushProfile("updateActions");
/*  453:     */    try {
/*  454: 454 */      for (int i = 0; i < this.actions.size(); i++) {
/*  455: 455 */        ((ActionInterface)this.actions.getQuick(i)).updateAction(this, timeStep);
/*  456:     */      }
/*  457:     */    }
/*  458:     */    finally {
/*  459: 459 */      BulletStats.popProfile();
/*  460:     */    }
/*  461:     */  }
/*  462:     */  
/*  463:     */  protected void updateVehicles(float timeStep) {
/*  464: 464 */    BulletStats.pushProfile("updateVehicles");
/*  465:     */    try {
/*  466: 466 */      for (int i = 0; i < this.vehicles.size(); i++) {
/*  467: 467 */        RaycastVehicle vehicle = (RaycastVehicle)this.vehicles.getQuick(i);
/*  468: 468 */        vehicle.updateVehicle(timeStep);
/*  469:     */      }
/*  470:     */    }
/*  471:     */    finally {
/*  472: 472 */      BulletStats.popProfile();
/*  473:     */    }
/*  474:     */  }
/*  475:     */  
/*  476:     */  protected void updateActivationState(float arg1) {
/*  477: 477 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();BulletStats.pushProfile("updateActivationState");
/*  478:     */      try {
/*  479: 479 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  480:     */        
/*  481: 481 */        for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  482: 482 */          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  483: 483 */          RigidBody body = RigidBody.upcast(colObj);
/*  484: 484 */          if (body != null) {
/*  485: 485 */            body.updateDeactivation(timeStep);
/*  486:     */            
/*  487: 487 */            if (body.wantsSleeping()) {
/*  488: 488 */              if (body.isStaticOrKinematicObject()) {
/*  489: 489 */                body.setActivationState(2);
/*  490:     */              }
/*  491:     */              else {
/*  492: 492 */                if (body.getActivationState() == 1) {
/*  493: 493 */                  body.setActivationState(3);
/*  494:     */                }
/*  495: 495 */                if (body.getActivationState() == 2) {
/*  496: 496 */                  tmp.set(0.0F, 0.0F, 0.0F);
/*  497: 497 */                  body.setAngularVelocity(tmp);
/*  498: 498 */                  body.setLinearVelocity(tmp);
/*  499:     */                }
/*  500:     */                
/*  501:     */              }
/*  502:     */            }
/*  503: 503 */            else if (body.getActivationState() != 4) {
/*  504: 504 */              body.setActivationState(1);
/*  505:     */            }
/*  506:     */          }
/*  507:     */        }
/*  508:     */      }
/*  509:     */      finally
/*  510:     */      {
/*  511: 511 */        BulletStats.popProfile();
/*  512:     */      }
/*  513: 513 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  514:     */    }
/*  515:     */  }
/*  516:     */  
/*  517: 517 */  public void addConstraint(TypedConstraint constraint, boolean disableCollisionsBetweenLinkedBodies) { this.constraints.add(constraint);
/*  518: 518 */    if (disableCollisionsBetweenLinkedBodies) {
/*  519: 519 */      constraint.getRigidBodyA().addConstraintRef(constraint);
/*  520: 520 */      constraint.getRigidBodyB().addConstraintRef(constraint);
/*  521:     */    }
/*  522:     */  }
/*  523:     */  
/*  524:     */  public void removeConstraint(TypedConstraint constraint)
/*  525:     */  {
/*  526: 526 */    this.constraints.remove(constraint);
/*  527: 527 */    constraint.getRigidBodyA().removeConstraintRef(constraint);
/*  528: 528 */    constraint.getRigidBodyB().removeConstraintRef(constraint);
/*  529:     */  }
/*  530:     */  
/*  531:     */  public void addAction(ActionInterface action)
/*  532:     */  {
/*  533: 533 */    this.actions.add(action);
/*  534:     */  }
/*  535:     */  
/*  536:     */  public void removeAction(ActionInterface action)
/*  537:     */  {
/*  538: 538 */    this.actions.remove(action);
/*  539:     */  }
/*  540:     */  
/*  541:     */  public void addVehicle(RaycastVehicle vehicle)
/*  542:     */  {
/*  543: 543 */    this.vehicles.add(vehicle);
/*  544:     */  }
/*  545:     */  
/*  546:     */  public void removeVehicle(RaycastVehicle vehicle)
/*  547:     */  {
/*  548: 548 */    this.vehicles.remove(vehicle);
/*  549:     */  }
/*  550:     */  
/*  552:     */  private static int getConstraintIslandId(TypedConstraint lhs)
/*  553:     */  {
/*  554: 554 */    CollisionObject rcolObj0 = lhs.getRigidBodyA();
/*  555: 555 */    CollisionObject rcolObj1 = lhs.getRigidBodyB();
/*  556: 556 */    int islandId = rcolObj0.getIslandTag() >= 0 ? rcolObj0.getIslandTag() : rcolObj1.getIslandTag();
/*  557: 557 */    return islandId;
/*  558:     */  }
/*  559:     */  
/*  560:     */  private static class InplaceSolverIslandCallback extends SimulationIslandManager.IslandCallback
/*  561:     */  {
/*  562:     */    public ContactSolverInfo solverInfo;
/*  563:     */    public ConstraintSolver solver;
/*  564:     */    public ObjectArrayList<TypedConstraint> sortedConstraints;
/*  565:     */    public int numConstraints;
/*  566:     */    public IDebugDraw debugDrawer;
/*  567:     */    public Dispatcher dispatcher;
/*  568:     */    
/*  569:     */    public void init(ContactSolverInfo solverInfo, ConstraintSolver solver, ObjectArrayList<TypedConstraint> sortedConstraints, int numConstraints, IDebugDraw debugDrawer, Dispatcher dispatcher) {
/*  570: 570 */      this.solverInfo = solverInfo;
/*  571: 571 */      this.solver = solver;
/*  572: 572 */      this.sortedConstraints = sortedConstraints;
/*  573: 573 */      this.numConstraints = numConstraints;
/*  574: 574 */      this.debugDrawer = debugDrawer;
/*  575: 575 */      this.dispatcher = dispatcher;
/*  576:     */    }
/*  577:     */    
/*  578:     */    public void processIsland(ObjectArrayList<CollisionObject> bodies, int numBodies, ObjectArrayList<PersistentManifold> manifolds, int manifolds_offset, int numManifolds, int islandId) {
/*  579: 579 */      if (islandId < 0)
/*  580:     */      {
/*  581: 581 */        this.solver.solveGroup(bodies, numBodies, manifolds, manifolds_offset, numManifolds, this.sortedConstraints, 0, this.numConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
/*  583:     */      }
/*  584:     */      else
/*  585:     */      {
/*  586: 586 */        int startConstraint_idx = -1;
/*  587: 587 */        int numCurConstraints = 0;
/*  588:     */        
/*  591: 591 */        for (int i = 0; i < this.numConstraints; i++) {
/*  592: 592 */          if (DiscreteDynamicsWorld.getConstraintIslandId((TypedConstraint)this.sortedConstraints.getQuick(i)) == islandId)
/*  593:     */          {
/*  595: 595 */            startConstraint_idx = i;
/*  596: 596 */            break;
/*  597:     */          }
/*  598:     */        }
/*  599: 600 */        for (; 
/*  600: 600 */            i < this.numConstraints; i++) {
/*  601: 601 */          if (DiscreteDynamicsWorld.getConstraintIslandId((TypedConstraint)this.sortedConstraints.getQuick(i)) == islandId) {
/*  602: 602 */            numCurConstraints++;
/*  603:     */          }
/*  604:     */        }
/*  605:     */        
/*  607: 607 */        if (numManifolds + numCurConstraints > 0) {
/*  608: 608 */          this.solver.solveGroup(bodies, numBodies, manifolds, manifolds_offset, numManifolds, this.sortedConstraints, startConstraint_idx, numCurConstraints, this.solverInfo, this.debugDrawer, this.dispatcher);
/*  609:     */        }
/*  610:     */      }
/*  611:     */    }
/*  612:     */  }
/*  613:     */  
/*  614: 614 */  private ObjectArrayList<TypedConstraint> sortedConstraints = new ObjectArrayList();
/*  615: 615 */  private InplaceSolverIslandCallback solverCallback = new InplaceSolverIslandCallback(null);
/*  616:     */  
/*  617:     */  protected void solveConstraints(ContactSolverInfo solverInfo) {
/*  618: 618 */    BulletStats.pushProfile("solveConstraints");
/*  619:     */    try
/*  620:     */    {
/*  621: 621 */      this.sortedConstraints.clear();
/*  622: 622 */      for (int i = 0; i < this.constraints.size(); i++) {
/*  623: 623 */        this.sortedConstraints.add(this.constraints.getQuick(i));
/*  624:     */      }
/*  625:     */      
/*  626: 626 */      MiscUtil.quickSort(this.sortedConstraints, sortConstraintOnIslandPredicate);
/*  627:     */      
/*  628: 628 */      ObjectArrayList<TypedConstraint> constraintsPtr = getNumConstraints() != 0 ? this.sortedConstraints : null;
/*  629:     */      
/*  630: 630 */      this.solverCallback.init(solverInfo, this.constraintSolver, constraintsPtr, this.sortedConstraints.size(), this.debugDrawer, this.dispatcher1);
/*  631:     */      
/*  632: 632 */      this.constraintSolver.prepareSolve(getCollisionWorld().getNumCollisionObjects(), getCollisionWorld().getDispatcher().getNumManifolds());
/*  633:     */      
/*  635: 635 */      this.islandManager.buildAndProcessIslands(getCollisionWorld().getDispatcher(), getCollisionWorld().getCollisionObjectArray(), this.solverCallback);
/*  636:     */      
/*  637: 637 */      this.constraintSolver.allSolved(solverInfo, this.debugDrawer);
/*  638:     */    }
/*  639:     */    finally {
/*  640: 640 */      BulletStats.popProfile();
/*  641:     */    }
/*  642:     */  }
/*  643:     */  
/*  644:     */  protected void calculateSimulationIslands() {
/*  645: 645 */    BulletStats.pushProfile("calculateSimulationIslands");
/*  646:     */    try {
/*  647: 647 */      getSimulationIslandManager().updateActivationState(getCollisionWorld(), getCollisionWorld().getDispatcher());
/*  648:     */      
/*  651: 651 */      int numConstraints = this.constraints.size();
/*  652: 652 */      for (int i = 0; i < numConstraints; i++) {
/*  653: 653 */        TypedConstraint constraint = (TypedConstraint)this.constraints.getQuick(i);
/*  654:     */        
/*  655: 655 */        RigidBody colObj0 = constraint.getRigidBodyA();
/*  656: 656 */        RigidBody colObj1 = constraint.getRigidBodyB();
/*  657:     */        
/*  658: 658 */        if ((colObj0 != null) && (!colObj0.isStaticOrKinematicObject()) && (colObj1 != null) && (!colObj1.isStaticOrKinematicObject()))
/*  659:     */        {
/*  661: 661 */          if ((colObj0.isActive()) || (colObj1.isActive())) {
/*  662: 662 */            getSimulationIslandManager().getUnionFind().unite(colObj0.getIslandTag(), colObj1.getIslandTag());
/*  663:     */          }
/*  664:     */        }
/*  665:     */      }
/*  666:     */      
/*  669: 669 */      getSimulationIslandManager().storeIslandActivationState(getCollisionWorld());
/*  670:     */    }
/*  671:     */    finally {
/*  672: 672 */      BulletStats.popProfile();
/*  673:     */    }
/*  674:     */  }
/*  675:     */  
/*  676:     */  protected void integrateTransforms(float arg1) {
/*  677: 677 */    .Stack localStack = .Stack.get(); try { .Stack tmp7_5 = localStack;tmp7_5.push$com$bulletphysics$linearmath$Transform();tmp7_5.push$javax$vecmath$Vector3f();BulletStats.pushProfile("integrateTransforms");
/*  678:     */      try {
/*  679: 679 */        Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  680: 680 */        Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  681:     */        
/*  682: 682 */        Transform predictedTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  683: 683 */        for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  684: 684 */          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  685: 685 */          RigidBody body = RigidBody.upcast(colObj);
/*  686: 686 */          if (body != null) {
/*  687: 687 */            body.setHitFraction(1.0F);
/*  688:     */            
/*  689: 689 */            if ((body.isActive()) && (!body.isStaticOrKinematicObject())) {
/*  690: 690 */              body.predictIntegratedTransform(timeStep, predictedTrans);
/*  691:     */              
/*  692: 692 */              tmp.sub(predictedTrans.origin, body.getWorldTransform(tmpTrans).origin);
/*  693: 693 */              float squareMotion = tmp.lengthSquared();
/*  694:     */              
/*  695: 695 */              if ((body.getCcdSquareMotionThreshold() != 0.0F) && (body.getCcdSquareMotionThreshold() < squareMotion)) {
/*  696: 696 */                BulletStats.pushProfile("CCD motion clamping");
/*  697:     */                try {
/*  698: 698 */                  if (body.getCollisionShape().isConvex()) {
/*  699: 699 */                    BulletStats.gNumClampedCcdMotions += 1;
/*  700:     */                    
/*  701: 701 */                    ClosestNotMeConvexResultCallback sweepResults = new ClosestNotMeConvexResultCallback(body, body.getWorldTransform(tmpTrans).origin, predictedTrans.origin, getBroadphase().getOverlappingPairCache(), getDispatcher());
/*  702:     */                    
/*  703: 703 */                    SphereShape tmpSphere = new SphereShape(body.getCcdSweptSphereRadius());
/*  704:     */                    
/*  705: 705 */                    sweepResults.collisionFilterGroup = body.getBroadphaseProxy().collisionFilterGroup;
/*  706: 706 */                    sweepResults.collisionFilterMask = body.getBroadphaseProxy().collisionFilterMask;
/*  707:     */                    
/*  708: 708 */                    convexSweepTest(tmpSphere, body.getWorldTransform(tmpTrans), predictedTrans, sweepResults);
/*  709:     */                    
/*  710: 710 */                    if ((sweepResults.hasHit()) && (sweepResults.closestHitFraction > 1.0E-004F)) {
/*  711: 711 */                      body.setHitFraction(sweepResults.closestHitFraction);
/*  712: 712 */                      body.predictIntegratedTransform(timeStep * body.getHitFraction(), predictedTrans);
/*  713: 713 */                      body.setHitFraction(0.0F);
/*  714:     */                    }
/*  715:     */                  }
/*  716:     */                }
/*  717:     */                finally {}
/*  718:     */              }
/*  719:     */              
/*  723: 723 */              body.proceedToTransform(predictedTrans);
/*  724:     */            }
/*  725:     */          }
/*  726:     */        }
/*  727:     */      }
/*  728:     */      finally {
/*  729: 729 */        BulletStats.popProfile();
/*  730:     */      }
/*  731: 731 */    } finally { .Stack tmp375_373 = localStack;tmp375_373.pop$com$bulletphysics$linearmath$Transform();tmp375_373.pop$javax$vecmath$Vector3f();
/*  732:     */    } }
/*  733:     */  
/*  734: 734 */  protected void predictUnconstraintMotion(float arg1) { .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$linearmath$Transform();BulletStats.pushProfile("predictUnconstraintMotion");
/*  735:     */      try {
/*  736: 736 */        Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  737:     */        
/*  738: 738 */        for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  739: 739 */          CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*  740: 740 */          RigidBody body = RigidBody.upcast(colObj);
/*  741: 741 */          if ((body != null) && 
/*  742: 742 */            (!body.isStaticOrKinematicObject()) && 
/*  743: 743 */            (body.isActive())) {
/*  744: 744 */            body.integrateVelocities(timeStep);
/*  745:     */            
/*  746: 746 */            body.applyDamping(timeStep);
/*  747:     */            
/*  748: 748 */            body.predictIntegratedTransform(timeStep, body.getInterpolationWorldTransform(tmpTrans));
/*  749:     */          }
/*  750:     */          
/*  751:     */        }
/*  752:     */      }
/*  753:     */      finally
/*  754:     */      {
/*  755: 755 */        BulletStats.popProfile();
/*  756:     */      }
/*  757: 757 */    } finally { localStack.pop$com$bulletphysics$linearmath$Transform();
/*  758:     */    }
/*  759:     */  }
/*  760:     */  
/*  762:     */  protected void startProfiling(float timeStep) {}
/*  763:     */  
/*  764:     */  protected void debugDrawSphere(float arg1, Transform arg2, Vector3f arg3)
/*  765:     */  {
/*  766: 766 */    .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f start = localStack.get$javax$vecmath$Vector3f(transform.origin);
/*  767:     */      
/*  768: 768 */      Vector3f xoffs = localStack.get$javax$vecmath$Vector3f();
/*  769: 769 */      xoffs.set(radius, 0.0F, 0.0F);
/*  770: 770 */      transform.basis.transform(xoffs);
/*  771: 771 */      Vector3f yoffs = localStack.get$javax$vecmath$Vector3f();
/*  772: 772 */      yoffs.set(0.0F, radius, 0.0F);
/*  773: 773 */      transform.basis.transform(yoffs);
/*  774: 774 */      Vector3f zoffs = localStack.get$javax$vecmath$Vector3f();
/*  775: 775 */      zoffs.set(0.0F, 0.0F, radius);
/*  776: 776 */      transform.basis.transform(zoffs);
/*  777:     */      
/*  778: 778 */      Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  779: 779 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  780:     */      
/*  782: 782 */      tmp1.sub(start, xoffs);
/*  783: 783 */      tmp2.add(start, yoffs);
/*  784: 784 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  785: 785 */      tmp1.add(start, yoffs);
/*  786: 786 */      tmp2.add(start, xoffs);
/*  787: 787 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  788: 788 */      tmp1.add(start, xoffs);
/*  789: 789 */      tmp2.sub(start, yoffs);
/*  790: 790 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  791: 791 */      tmp1.sub(start, yoffs);
/*  792: 792 */      tmp2.sub(start, xoffs);
/*  793: 793 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  794:     */      
/*  796: 796 */      tmp1.sub(start, xoffs);
/*  797: 797 */      tmp2.add(start, zoffs);
/*  798: 798 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  799: 799 */      tmp1.add(start, zoffs);
/*  800: 800 */      tmp2.add(start, xoffs);
/*  801: 801 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  802: 802 */      tmp1.add(start, xoffs);
/*  803: 803 */      tmp2.sub(start, zoffs);
/*  804: 804 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  805: 805 */      tmp1.sub(start, zoffs);
/*  806: 806 */      tmp2.sub(start, xoffs);
/*  807: 807 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  808:     */      
/*  810: 810 */      tmp1.sub(start, yoffs);
/*  811: 811 */      tmp2.add(start, zoffs);
/*  812: 812 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  813: 813 */      tmp1.add(start, zoffs);
/*  814: 814 */      tmp2.add(start, yoffs);
/*  815: 815 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  816: 816 */      tmp1.add(start, yoffs);
/*  817: 817 */      tmp2.sub(start, zoffs);
/*  818: 818 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  819: 819 */      tmp1.sub(start, zoffs);
/*  820: 820 */      tmp2.sub(start, yoffs);
/*  821: 821 */      getDebugDrawer().drawLine(tmp1, tmp2, color);
/*  822: 822 */    } finally { localStack.pop$javax$vecmath$Vector3f();
/*  823:     */    } }
/*  824:     */  
/*  825: 825 */  public void debugDrawObject(Transform arg1, CollisionShape arg2, Vector3f arg3) { .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f();Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  826: 826 */      Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  827:     */      
/*  830: 830 */      Vector3f start = localStack.get$javax$vecmath$Vector3f(worldTransform.origin);
/*  831:     */      
/*  832: 832 */      tmp.set(1.0F, 0.0F, 0.0F);
/*  833: 833 */      worldTransform.basis.transform(tmp);
/*  834: 834 */      tmp.add(start);
/*  835: 835 */      tmp2.set(1.0F, 0.0F, 0.0F);
/*  836: 836 */      getDebugDrawer().drawLine(start, tmp, tmp2);
/*  837:     */      
/*  838: 838 */      tmp.set(0.0F, 1.0F, 0.0F);
/*  839: 839 */      worldTransform.basis.transform(tmp);
/*  840: 840 */      tmp.add(start);
/*  841: 841 */      tmp2.set(0.0F, 1.0F, 0.0F);
/*  842: 842 */      getDebugDrawer().drawLine(start, tmp, tmp2);
/*  843:     */      
/*  844: 844 */      tmp.set(0.0F, 0.0F, 1.0F);
/*  845: 845 */      worldTransform.basis.transform(tmp);
/*  846: 846 */      tmp.add(start);
/*  847: 847 */      tmp2.set(0.0F, 0.0F, 1.0F);
/*  848: 848 */      getDebugDrawer().drawLine(start, tmp, tmp2);
/*  928:     */    }
/*  929:     */    finally
/*  930:     */    {
/* 1009:1009 */      localStack.pop$javax$vecmath$Vector3f();
/* 1010:     */    }
/* 1011:     */  }
/* 1012:     */  
/* 1013:1013 */  public void setConstraintSolver(ConstraintSolver solver) { if (this.ownsConstraintSolver) {}
/* 1014:     */    
/* 1016:1016 */    this.ownsConstraintSolver = false;
/* 1017:1017 */    this.constraintSolver = solver;
/* 1018:     */  }
/* 1019:     */  
/* 1020:     */  public ConstraintSolver getConstraintSolver()
/* 1021:     */  {
/* 1022:1022 */    return this.constraintSolver;
/* 1023:     */  }
/* 1024:     */  
/* 1025:     */  public int getNumConstraints()
/* 1026:     */  {
/* 1027:1027 */    return this.constraints.size();
/* 1028:     */  }
/* 1029:     */  
/* 1030:     */  public TypedConstraint getConstraint(int index)
/* 1031:     */  {
/* 1032:1032 */    return (TypedConstraint)this.constraints.getQuick(index);
/* 1033:     */  }
/* 1034:     */  
/* 1036:     */  public int getNumActions()
/* 1037:     */  {
/* 1038:1038 */    return this.actions.size();
/* 1039:     */  }
/* 1040:     */  
/* 1042:     */  public ActionInterface getAction(int index)
/* 1043:     */  {
/* 1044:1044 */    return (ActionInterface)this.actions.getQuick(index);
/* 1045:     */  }
/* 1046:     */  
/* 1047:     */  public SimulationIslandManager getSimulationIslandManager() {
/* 1048:1048 */    return this.islandManager;
/* 1049:     */  }
/* 1050:     */  
/* 1051:     */  public CollisionWorld getCollisionWorld() {
/* 1052:1052 */    return this;
/* 1053:     */  }
/* 1054:     */  
/* 1055:     */  public DynamicsWorldType getWorldType()
/* 1056:     */  {
/* 1057:1057 */    return DynamicsWorldType.DISCRETE_DYNAMICS_WORLD;
/* 1058:     */  }
/* 1059:     */  
/* 1065:1065 */  private static final Comparator<TypedConstraint> sortConstraintOnIslandPredicate = new Comparator()
/* 1066:     */  {
/* 1067:     */    public int compare(TypedConstraint lhs, TypedConstraint rhs) {
/* 1068:1068 */      int rIslandId0 = DiscreteDynamicsWorld.getConstraintIslandId(rhs);
/* 1069:1069 */      int lIslandId0 = DiscreteDynamicsWorld.getConstraintIslandId(lhs);
/* 1070:1070 */      return lIslandId0 < rIslandId0 ? -1 : 1;
/* 1071:     */    }
/* 1072:     */  };
/* 1073:     */  
/* 1083:     */  public void setNumTasks(int numTasks) {}
/* 1084:     */  
/* 1094:     */  private static class ClosestNotMeConvexResultCallback
/* 1095:     */    extends CollisionWorld.ClosestConvexResultCallback
/* 1096:     */  {
/* 1097:     */    private CollisionObject me;
/* 1098:     */    
/* 1107:1107 */    private float allowedPenetration = 0.0F;
/* 1108:     */    private OverlappingPairCache pairCache;
/* 1109:     */    private Dispatcher dispatcher;
/* 1110:     */    
/* 1111:     */    public ClosestNotMeConvexResultCallback(CollisionObject me, Vector3f fromA, Vector3f toA, OverlappingPairCache pairCache, Dispatcher dispatcher) {
/* 1112:1112 */      super(toA);
/* 1113:1113 */      this.me = me;
/* 1114:1114 */      this.pairCache = pairCache;
/* 1115:1115 */      this.dispatcher = dispatcher;
/* 1116:     */    }
/* 1117:     */    
/* 1118:     */    public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
/* 1119:     */    {
/* 1120:1120 */      .Stack localStack = .Stack.get(); try { localStack.push$javax$vecmath$Vector3f(); if (convexResult.hitCollisionObject == this.me) {
/* 1121:1121 */          return 1.0F;
/* 1122:     */        }
/* 1123:     */        
/* 1124:1124 */        Vector3f linVelA = localStack.get$javax$vecmath$Vector3f();Vector3f linVelB = localStack.get$javax$vecmath$Vector3f();
/* 1125:1125 */        linVelA.sub(this.convexToWorld, this.convexFromWorld);
/* 1126:1126 */        linVelB.set(0.0F, 0.0F, 0.0F);
/* 1127:     */        
/* 1128:1128 */        Vector3f relativeVelocity = localStack.get$javax$vecmath$Vector3f();
/* 1129:1129 */        relativeVelocity.sub(linVelA, linVelB);
/* 1130:     */        
/* 1131:1131 */        if (convexResult.hitNormalLocal.dot(relativeVelocity) >= -this.allowedPenetration) {
/* 1132:1132 */          return 1.0F;
/* 1133:     */        }
/* 1134:     */        
/* 1135:1135 */        return super.addSingleResult(convexResult, normalInWorldSpace); } finally { localStack.pop$javax$vecmath$Vector3f();
/* 1136:     */      }
/* 1137:     */    }
/* 1138:     */    
/* 1139:     */    public boolean needsCollision(BroadphaseProxy proxy0)
/* 1140:     */    {
/* 1141:1141 */      if (proxy0.clientObject == this.me) {
/* 1142:1142 */        return false;
/* 1143:     */      }
/* 1144:     */      
/* 1146:1146 */      if (!super.needsCollision(proxy0)) {
/* 1147:1147 */        return false;
/* 1148:     */      }
/* 1149:     */      
/* 1150:1150 */      CollisionObject otherObj = (CollisionObject)proxy0.clientObject;
/* 1151:     */      
/* 1153:1153 */      if (this.dispatcher.needsResponse(this.me, otherObj))
/* 1154:     */      {
/* 1155:1155 */        ObjectArrayList<PersistentManifold> manifoldArray = new ObjectArrayList();
/* 1156:1156 */        BroadphasePair collisionPair = this.pairCache.findPair(this.me.getBroadphaseHandle(), proxy0);
/* 1157:1157 */        if ((collisionPair != null) && 
/* 1158:1158 */          (collisionPair.algorithm != null))
/* 1159:     */        {
/* 1160:1160 */          collisionPair.algorithm.getAllContactManifolds(manifoldArray);
/* 1161:1161 */          for (int j = 0; j < manifoldArray.size(); j++) {
/* 1162:1162 */            PersistentManifold manifold = (PersistentManifold)manifoldArray.getQuick(j);
/* 1163:1163 */            if (manifold.getNumContacts() > 0) {
/* 1164:1164 */              return false;
/* 1165:     */            }
/* 1166:     */          }
/* 1167:     */        }
/* 1168:     */      }
/* 1169:     */      
/* 1170:1170 */      return true;
/* 1171:     */    }
/* 1172:     */  }
/* 1173:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.DiscreteDynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */