/*    1:     */package org.schema.game.common.data.physics;
/*    2:     */
/*    3:     */import com.bulletphysics.BulletGlobals;
/*    4:     */import com.bulletphysics.BulletStats;
/*    5:     */import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*    6:     */import com.bulletphysics.collision.broadphase.BroadphasePair;
/*    7:     */import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*    8:     */import com.bulletphysics.collision.broadphase.Dispatcher;
/*    9:     */import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*   10:     */import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*   11:     */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   12:     */import com.bulletphysics.collision.dispatch.CollisionWorld;
/*   13:     */import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*   14:     */import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
/*   15:     */import com.bulletphysics.collision.dispatch.SimulationIslandManager;
/*   16:     */import com.bulletphysics.collision.narrowphase.ConvexCast;
/*   17:     */import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*   18:     */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   19:     */import com.bulletphysics.collision.shapes.CollisionShape;
/*   20:     */import com.bulletphysics.collision.shapes.CompoundShape;
/*   21:     */import com.bulletphysics.collision.shapes.ConvexShape;
/*   22:     */import com.bulletphysics.collision.shapes.SphereShape;
/*   23:     */import com.bulletphysics.dynamics.ActionInterface;
/*   24:     */import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
/*   25:     */import com.bulletphysics.dynamics.RigidBody;
/*   26:     */import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*   27:     */import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*   28:     */import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*   29:     */import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
/*   30:     */import com.bulletphysics.linearmath.AabbUtil2;
/*   31:     */import com.bulletphysics.linearmath.IDebugDraw;
/*   32:     */import com.bulletphysics.linearmath.MiscUtil;
/*   33:     */import com.bulletphysics.linearmath.MotionState;
/*   34:     */import com.bulletphysics.linearmath.Transform;
/*   35:     */import d;
/*   36:     */import jL;
/*   37:     */import java.io.PrintStream;
/*   38:     */import java.util.ArrayList;
/*   39:     */import java.util.Comparator;
/*   40:     */import javax.vecmath.Quat4f;
/*   41:     */import javax.vecmath.Vector3f;
/*   42:     */import org.schema.common.util.linAlg.TransformTools;
/*   43:     */import org.schema.game.common.data.world.Segment;
/*   44:     */import xO;
/*   45:     */import zW;
/*   46:     */
/*   55:     */public class ModifiedDynamicsWorld
/*   56:     */  extends DiscreteDynamicsWorld
/*   57:     */{
/*   58:     */  private static void handleCompound(ConvexShape paramConvexShape, CompoundShape paramCompoundShape, CollisionObject paramCollisionObject, Transform paramTransform1, float paramFloat, Transform paramTransform2, Transform paramTransform3, CollisionWorld.ConvexResultCallback paramConvexResultCallback)
/*   59:     */  {
/*   60:  60 */    for (int i = 0; i < paramCompoundShape.getNumChildShapes(); i++) {
/*   61:  61 */      Object localObject = paramCompoundShape.getChildTransform(i, new Transform());
/*   62:  62 */      CollisionShape localCollisionShape = paramCompoundShape.getChildShape(i);
/*   63:     */      Transform localTransform;
/*   64:  64 */      (localTransform = new Transform()).mul(paramTransform1, (Transform)localObject);
/*   65:     */      
/*   66:  66 */      localObject = paramCollisionObject.getCollisionShape();
/*   67:  67 */      paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape);
/*   68:     */      
/*   70:  70 */      objectQuerySingle(paramConvexShape, paramTransform2, paramTransform3, paramCollisionObject, localCollisionShape, localTransform, paramConvexResultCallback, paramFloat);
/*   71:     */      
/*   73:  73 */      paramCollisionObject.internalSetTemporaryCollisionShape((CollisionShape)localObject);
/*   74:     */    }
/*   75:     */  }
/*   76:     */  
/*   77:  77 */  public final OverlappingPairCache getPairCache() { return this.broadphasePairCache.getOverlappingPairCache(); }
/*   78:     */  
/*   80:  80 */  private final Vector3f minAabb = new Vector3f();
/*   81:  81 */  private final Vector3f maxAabb = new Vector3f();
/*   82:  82 */  private final Vector3f tmpAABBSingle = new Vector3f();
/*   83:  83 */  private final Transform tmpTransAABBSingle = new Transform();
/*   84:  84 */  private final Vector3f contactThreshold = new Vector3f();
/*   85:     */  
/*   86:     */  public void updateSingleAabb(CollisionObject paramCollisionObject)
/*   87:     */  {
/*   88:  88 */    paramCollisionObject.getCollisionShape().getAabb(paramCollisionObject.getWorldTransform(this.tmpTransAABBSingle), this.minAabb, this.maxAabb);
/*   89:     */    
/*   90:  90 */    this.contactThreshold.set(BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold());
/*   91:  91 */    this.minAabb.sub(this.contactThreshold);
/*   92:  92 */    this.maxAabb.add(this.contactThreshold);
/*   93:     */    
/*   94:  94 */    BroadphaseInterface localBroadphaseInterface = this.broadphasePairCache;
/*   95:     */    
/*   97:  97 */    this.tmpAABBSingle.sub(this.maxAabb, this.minAabb);
/*   98:  98 */    if ((paramCollisionObject.isStaticObject()) || (this.tmpAABBSingle.lengthSquared() < 1.0E+012F)) {
/*   99:  99 */      localBroadphaseInterface.setAabb(paramCollisionObject.getBroadphaseHandle(), this.minAabb, this.maxAabb, this.dispatcher1);return;
/*  100:     */    }
/*  101:     */    
/*  104: 104 */    paramCollisionObject.setActivationState(5);
/*  105: 105 */    System.err.println("Exception!!! Overflow in AABB, object removed from simulation " + paramCollisionObject);
/*  106: 106 */    System.err.println("If you can reproduce this, please email bugs@continuousphysics.com\n");
/*  107: 107 */    System.err.println("Please include above information, your Platform, version of OS.\n");
/*  108: 108 */    System.err.println("Thanks.\n");
/*  109:     */  }
/*  110:     */  
/*  117:     */  public void updateAabbs()
/*  118:     */  {
/*  119: 119 */    BulletStats.pushProfile("updateAabbs");
/*  120:     */    try {
/*  121: 121 */      for (int i = 0; i < this.collisionObjects.size(); i++)
/*  122:     */      {
/*  123:     */        CollisionObject localCollisionObject;
/*  124:     */        
/*  125: 125 */        if ((localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i)).isActive())
/*  126:     */        {
/*  129: 129 */          updateSingleAabb(localCollisionObject);
/*  130:     */        }
/*  131:     */        
/*  132:     */      }
/*  133:     */      
/*  135:     */    }
/*  136:     */    finally
/*  137:     */    {
/*  139: 139 */      BulletStats.popProfile();
/*  140:     */    } }
/*  141:     */  
/*  142: 142 */  private final Transform interpolatedTransform = new Transform();
/*  143:     */  
/*  144: 144 */  private final Transform tmpTrans2 = new Transform();
/*  145: 145 */  private final Vector3f tmpLinVel = new Vector3f();
/*  146: 146 */  private final Vector3f tmpAngVel = new Vector3f();
/*  147:     */  
/*  148: 148 */  public final Vector3f iAxis = new Vector3f();
/*  149: 149 */  public final Quat4f iDorn = new Quat4f();
/*  150: 150 */  public final Quat4f iorn0 = new Quat4f();
/*  151: 151 */  public final Quat4f iPredictOrn = new Quat4f();
/*  152: 152 */  public final float[] float4Temp = new float[4];
/*  153:     */  
/*  155:     */  protected void synchronizeMotionStates()
/*  156:     */  {
/*  157: 157 */    for (int i = 0; i < this.collisionObjects.size(); i++)
/*  158:     */    {
/*  159:     */      RigidBody localRigidBody;
/*  160: 160 */      if (((localRigidBody = RigidBody.upcast((CollisionObject)this.collisionObjects.getQuick(i))) != null) && (localRigidBody.getMotionState() != null) && (!localRigidBody.isStaticOrKinematicObject()))
/*  161:     */      {
/*  166: 166 */        TransformTools.a(localRigidBody.getInterpolationWorldTransform(this.tmpTrans2), localRigidBody.getInterpolationLinearVelocity(this.tmpLinVel), localRigidBody.getInterpolationAngularVelocity(this.tmpAngVel), this.localTime * localRigidBody.getHitFraction(), this.interpolatedTransform, this.iAxis, this.iDorn, this.iorn0, this.iPredictOrn, this.float4Temp);
/*  167:     */        
/*  172: 172 */        localRigidBody.getMotionState().setWorldTransform(this.interpolatedTransform);
/*  173:     */      }
/*  174:     */    }
/*  175:     */    
/*  177: 177 */    if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0)) {
/*  178: 178 */      for (i = 0; i < this.vehicles.size(); i++) {
/*  179: 179 */        for (int j = 0; j < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); j++)
/*  180:     */        {
/*  181: 181 */          ((RaycastVehicle)this.vehicles.getQuick(i)).updateWheelTransform(j, true);
/*  182:     */        }
/*  183:     */      }
/*  184:     */    }
/*  185:     */  }
/*  186:     */  
/*  187:     */  public void performDiscreteCollisionDetection() {
/*  188: 188 */    BulletStats.pushProfile("performDiscreteCollisionDetection");
/*  189:     */    
/*  190:     */    try
/*  191:     */    {
/*  192: 192 */      updateAabbs();
/*  193:     */      
/*  194: 194 */      BulletStats.pushProfile("calculateOverlappingPairs");
/*  195:     */      Dispatcher localDispatcher;
/*  196: 196 */      try { this.broadphasePairCache.calculateOverlappingPairs(this.dispatcher1);
/*  197:     */      }
/*  198:     */      finally {}
/*  199:     */      
/*  217: 217 */      BulletStats.pushProfile("dispatchAllCollisionPairs");
/*  218:     */      try {
/*  219: 219 */        if (localDispatcher != null) {
/*  220: 220 */          localDispatcher.dispatchAllCollisionPairs(this.broadphasePairCache.getOverlappingPairCache(), this.dispatchInfo, this.dispatcher1);
/*  221:     */        }
/*  222:     */        
/*  224:     */      }
/*  225:     */      finally {}
/*  226:     */    }
/*  227:     */    finally
/*  228:     */    {
/*  229: 229 */      BulletStats.popProfile();
/*  230:     */    }
/*  231:     */  }
/*  232:     */  
/*  283:     */  public static void objectQuerySingle(ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, CollisionObject paramCollisionObject, CollisionShape paramCollisionShape, Transform paramTransform3, CollisionWorld.ConvexResultCallback paramConvexResultCallback, float paramFloat)
/*  284:     */  {
/*  285: 285 */    if ((paramCollisionShape instanceof CubeShape))
/*  286:     */    {
/*  287: 287 */      (paramCollisionShape = new ConvexCast.CastResult()).allowedPenetration = paramFloat;
/*  288: 288 */      paramCollisionShape.fraction = 1.0F;
/*  289:     */      
/*  290: 290 */      paramFloat = new VoronoiSimplexSolverExt();
/*  291:     */      
/*  297: 297 */      new SubsimplexCubesCovexCast(paramConvexShape, paramCollisionObject.getCollisionShape(), paramCollisionObject, paramFloat, paramConvexResultCallback, null)
/*  298:     */      
/*  300: 300 */        .calcTimeOfImpact(paramTransform1, paramTransform2, paramTransform3, paramTransform3, paramCollisionShape);
/*  301: 301 */      return; } if (paramCollisionShape.isCompound()) {
/*  302: 302 */      handleCompound(paramConvexShape, (CompoundShape)paramCollisionShape, paramCollisionObject, paramTransform3, paramFloat, paramTransform1, paramTransform2, paramConvexResultCallback);return;
/*  303:     */    }
/*  304: 304 */    CollisionWorld.objectQuerySingle(paramConvexShape, paramTransform1, paramTransform2, paramCollisionObject, paramCollisionShape, paramTransform3, paramConvexResultCallback, paramFloat);
/*  305:     */  }
/*  306:     */  
/*  308: 308 */  Transform tmpTrans = new Transform();
/*  309: 309 */  Transform rayFromTrans = new Transform();
/*  310: 310 */  Transform rayToTrans = new Transform();
/*  311: 311 */  Vector3f collisionObjectAabbMin = new Vector3f();
/*  312: 312 */  Vector3f collisionObjectAabbMax = new Vector3f();
/*  313:     */  
/*  314: 314 */  Vector3f hitNormal = new Vector3f();
/*  315:     */  
/*  333: 333 */  Transform convexFromTrans = new Transform();
/*  334: 334 */  Transform convexToTrans = new Transform();
/*  335: 335 */  Vector3f castShapeAabbMin = new Vector3f();
/*  336: 336 */  Vector3f castShapeAabbMax = new Vector3f();
/*  337: 337 */  Vector3f linVel = new Vector3f();
/*  338: 338 */  Vector3f angVel = new Vector3f();
/*  339: 339 */  Transform R = new Transform();
/*  340: 340 */  Quat4f quat = new Quat4f();
/*  341:     */  private zW state;
/*  342:     */  
/*  343:     */  public ModifiedDynamicsWorld(Dispatcher paramDispatcher, BroadphaseInterface paramBroadphaseInterface, ConstraintSolver paramConstraintSolver, CollisionConfiguration paramCollisionConfiguration, zW paramzW)
/*  344:     */  {
/*  345: 345 */    super(paramDispatcher, paramBroadphaseInterface, paramConstraintSolver, paramCollisionConfiguration);
/*  346: 346 */    this.state = paramzW;
/*  347: 347 */    this.islandManager = new SimulationIslandManagerExt();
/*  348:     */  }
/*  349:     */  
/*  351:     */  public void removeCollisionObject(CollisionObject paramCollisionObject)
/*  352:     */  {
/*  353:     */    BroadphaseProxy localBroadphaseProxy;
/*  354:     */    
/*  355: 355 */    if ((localBroadphaseProxy = paramCollisionObject.getBroadphaseHandle()) != null)
/*  356:     */    {
/*  361: 361 */      getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(localBroadphaseProxy, this.dispatcher1);
/*  362: 362 */      getBroadphase().destroyProxy(localBroadphaseProxy, this.dispatcher1);
/*  363: 363 */      paramCollisionObject.setBroadphaseHandle(null);
/*  364:     */      
/*  365: 365 */      com.bulletphysics.util.ObjectArrayList localObjectArrayList = this.dispatcher1.getInternalManifoldPointer();
/*  366: 366 */      for (int i = 0; i < localObjectArrayList.size(); i++) {
/*  367:     */        PersistentManifold localPersistentManifold;
/*  368: 368 */        if (((localPersistentManifold = (PersistentManifold)localObjectArrayList.get(i)).getBody0() == paramCollisionObject) || (localPersistentManifold.getBody1() == paramCollisionObject)) {
/*  369: 369 */          this.dispatcher1.releaseManifold(localPersistentManifold);
/*  370:     */          
/*  372: 372 */          i = 0;
/*  373:     */        }
/*  374:     */      }
/*  375:     */      
/*  376: 376 */      assert (checkProdyDestroyed(localBroadphaseProxy));
/*  377:     */    }
/*  378:     */    
/*  381: 381 */    this.collisionObjects.remove(paramCollisionObject);
/*  382:     */  }
/*  383:     */  
/*  384:     */  public boolean checkProdyDestroyed(BroadphaseProxy paramBroadphaseProxy) {
/*  385: 385 */    com.bulletphysics.util.ObjectArrayList localObjectArrayList = getBroadphase().getOverlappingPairCache().getOverlappingPairArray();
/*  386: 386 */    for (int i = 0; i < localObjectArrayList.size(); i++) {
/*  387:     */      BroadphasePair localBroadphasePair;
/*  388: 388 */      if (((localBroadphasePair = (BroadphasePair)localObjectArrayList.getQuick(i)).pProxy0 == paramBroadphaseProxy.clientObject) || (localBroadphasePair.pProxy1 == paramBroadphaseProxy.clientObject))
/*  389:     */      {
/*  390: 390 */        System.err.println("Exception: Proxy Has NOT been destroyed completely: " + paramBroadphaseProxy.clientObject);
/*  391: 391 */        return false;
/*  392:     */      }
/*  393:     */    }
/*  394: 394 */    return true;
/*  395:     */  }
/*  396:     */  
/*  458: 458 */  private com.bulletphysics.util.ObjectArrayList sortedConstraints = new com.bulletphysics.util.ObjectArrayList();
/*  459: 459 */  private ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt solverCallback = new ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt(null);
/*  460:     */  
/*  461: 461 */  protected void solveConstraints(ContactSolverInfo paramContactSolverInfo) { BulletStats.pushProfile("solveConstraints");
/*  462:     */    try
/*  463:     */    {
/*  464: 464 */      this.sortedConstraints.clear();
/*  465: 465 */      for (int i = 0; i < this.constraints.size(); i++) {
/*  466: 466 */        this.sortedConstraints.add(this.constraints.getQuick(i));
/*  467:     */      }
/*  468:     */      
/*  469: 469 */      MiscUtil.quickSort(this.sortedConstraints, sortConstraintOnIslandPredicate);
/*  470:     */      
/*  471: 471 */      com.bulletphysics.util.ObjectArrayList localObjectArrayList = getNumConstraints() != 0 ? this.sortedConstraints : null;
/*  472:     */      
/*  473: 473 */      this.solverCallback.init(paramContactSolverInfo, this.constraintSolver, localObjectArrayList, this.sortedConstraints.size(), this.debugDrawer, this.dispatcher1, this.state);
/*  474:     */      
/*  475: 475 */      this.constraintSolver.prepareSolve(getCollisionWorld().getNumCollisionObjects(), getCollisionWorld().getDispatcher().getNumManifolds());
/*  476:     */      
/*  478: 478 */      this.islandManager.buildAndProcessIslands(getCollisionWorld().getDispatcher(), getCollisionWorld().getCollisionObjectArray(), this.solverCallback);
/*  479:     */      
/*  480: 480 */      this.constraintSolver.allSolved(paramContactSolverInfo, this.debugDrawer);
/*  481:     */    }
/*  482:     */    finally {
/*  483: 483 */      BulletStats.popProfile();
/*  484:     */    } }
/*  485:     */  
/*  486: 486 */  private static final Comparator sortConstraintOnIslandPredicate = new ModifiedDynamicsWorld.1();
/*  487:     */  
/*  495:     */  private static int getConstraintIslandId(TypedConstraint paramTypedConstraint)
/*  496:     */  {
/*  497: 497 */    RigidBody localRigidBody = paramTypedConstraint.getRigidBodyA();
/*  498: 498 */    paramTypedConstraint = paramTypedConstraint.getRigidBodyB();
/*  499: 499 */    if (localRigidBody.getIslandTag() >= 0) return localRigidBody.getIslandTag();
/*  500: 500 */    return paramTypedConstraint.getIslandTag();
/*  501:     */  }
/*  502:     */  
/*  519: 519 */  private final Transform childWorldTrans = new Transform();
/*  520: 520 */  Transform childTrans = new Transform();
/*  521:     */  
/*  522:     */  private void handleCompoundRayTest(CompoundShape paramCompoundShape, CollisionObject paramCollisionObject, Vector3f paramVector3f1, Vector3f paramVector3f2, CollisionWorld.RayResultCallback paramRayResultCallback) {
/*  523: 523 */    for (int i = 0; i < paramCompoundShape.getNumChildShapes(); i++) {
/*  524: 524 */      paramCompoundShape.getChildTransform(i, this.childTrans);
/*  525: 525 */      CollisionShape localCollisionShape1 = paramCompoundShape.getChildShape(i);
/*  526: 526 */      this.childWorldTrans.set(paramCollisionObject.getWorldTransform(this.tmpTrans));
/*  527:     */      
/*  528: 528 */      d.a(this.childWorldTrans, this.childTrans);
/*  529:     */      
/*  530: 530 */      CollisionShape localCollisionShape2 = paramCollisionObject.getCollisionShape();
/*  531:     */      
/*  534: 534 */      if (((localCollisionShape1 instanceof CubeShape)) && ((paramRayResultCallback == null) || ((paramRayResultCallback instanceof CubeRayCastResult))))
/*  535:     */      {
/*  536: 536 */        CubeRayCastResult localCubeRayCastResult = (CubeRayCastResult)paramRayResultCallback;
/*  537:     */        
/*  544: 544 */        CubeShape localCubeShape = (CubeShape)localCollisionShape1;
/*  545:     */        
/*  548: 548 */        if ((localCubeRayCastResult.getOwner() == null) || (localCubeRayCastResult.getOwner() != localCubeShape.getSegmentBuffer().a()))
/*  549:     */        {
/*  550: 550 */          paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/*  551:     */          
/*  554: 554 */          rayTestSingleCubeMesh(this.rayFromTrans, this.rayToTrans, paramCollisionObject, localCollisionShape1, this.childWorldTrans, localCubeRayCastResult, localCubeRayCastResult.getLastHitSegment());
/*  555:     */          
/*  559: 559 */          paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/*  560:     */        }
/*  561:     */      }
/*  562: 562 */      else if ((localCollisionShape1 instanceof CompoundShape)) {
/*  563: 563 */        handleCompoundRayTest((CompoundShape)localCollisionShape1, paramCollisionObject, paramVector3f1, paramVector3f2, paramRayResultCallback);
/*  564:     */      } else {
/*  565: 565 */        paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/*  566: 566 */        if (!$assertionsDisabled) { throw new AssertionError(localCollisionShape1 + ": " + localCollisionShape1.getClass().getSimpleName() + "; " + (paramRayResultCallback != null ? paramRayResultCallback.getClass().getSimpleName() : "null-ResultCallback"));
/*  567:     */        }
/*  568: 568 */        rayTestSingle(this.rayFromTrans, this.rayToTrans, paramCollisionObject, localCollisionShape1, this.childWorldTrans, paramRayResultCallback);
/*  569:     */        
/*  573: 573 */        paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/*  574:     */      }
/*  575:     */    }
/*  576:     */  }
/*  577:     */  
/*  587: 587 */  float[] hitLambda = new float[1];
/*  588:     */  
/*  590:     */  public void rayTest(Vector3f paramVector3f1, Vector3f paramVector3f2, CollisionWorld.RayResultCallback paramRayResultCallback)
/*  591:     */  {
/*  592: 592 */    this.rayFromTrans.setIdentity();
/*  593: 593 */    this.rayFromTrans.origin.set(paramVector3f1);
/*  594:     */    
/*  595: 595 */    this.rayToTrans.setIdentity();
/*  596: 596 */    this.rayToTrans.origin.set(paramVector3f2);
/*  597:     */    
/*  599: 599 */    this.hitLambda[0] = 0.0F;
/*  600:     */    
/*  601: 601 */    Object localObject = (paramRayResultCallback != null) && ((paramRayResultCallback instanceof CubeRayCastResult)) && (((CubeRayCastResult)paramRayResultCallback).getOwner() != null) ? ((CubeRayCastResult)paramRayResultCallback).getOwner() : null;
/*  602:     */    
/*  605: 605 */    if (this.cacheValid)
/*  606:     */    {
/*  607: 607 */      doRayTestCached(localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);return;
/*  608:     */    }
/*  609: 609 */    doRayTest(localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*  610:     */  }
/*  611:     */  
/*  613: 613 */  Vector3f dir = new Vector3f();
/*  614: 614 */  Vector3f closest = new Vector3f();
/*  615: 615 */  Vector3f closestDir = new Vector3f();
/*  616:     */  
/*  617:     */  public void doRayTestCached(Object paramObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*  618:     */  {
/*  619: 619 */    for (int i = 0; i < this.collisionObjects.size(); i++)
/*  620:     */    {
/*  621: 621 */      if (paramRayResultCallback.closestHitFraction == 0.0F) break;
/*  622: 622 */      Object localObject = (xO)this.cache.get(i);
/*  623:     */      
/*  626: 626 */      this.dir.sub(paramVector3f2, paramVector3f1);
/*  627: 627 */      ((xO)localObject).a(paramVector3f1, this.closest);
/*  628: 628 */      this.closestDir.sub(this.closest, paramVector3f1);
/*  629: 629 */      if (this.dir.lengthSquared() >= this.closestDir.lengthSquared())
/*  630:     */      {
/*  640: 640 */        this.collisionObjectAabbMin.set(((xO)localObject).a);
/*  641:     */        
/*  644: 644 */        this.collisionObjectAabbMax.set(((xO)localObject).b);
/*  645:     */        
/*  646: 646 */        localObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*  647:     */        
/*  648: 648 */        if (paramObject != localObject) {
/*  649: 649 */          BroadphaseProxy localBroadphaseProxy = ((CollisionObject)localObject).getBroadphaseHandle();
/*  650:     */          
/*  653:     */          try
/*  654:     */          {
/*  655: 655 */            if ((localBroadphaseProxy != null) && (paramRayResultCallback.needsCollision(localBroadphaseProxy)))
/*  656:     */            {
/*  657: 657 */              doRayTestNext(localBroadphaseProxy, (CollisionObject)localObject, paramRayResultCallback, paramVector3f1, paramVector3f2); }
/*  658:     */          } catch (Exception localException) {
/*  659: 659 */            
/*  660:     */            
/*  661: 661 */              localException;
/*  662:     */          }
/*  663:     */        }
/*  664:     */      }
/*  665:     */    }
/*  666:     */  }
/*  667:     */  
/*  668: 666 */  public void doRayTest(Object paramObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2) { for (int i = 0; i < this.collisionObjects.size(); i++)
/*  669:     */    {
/*  670: 668 */      if (paramRayResultCallback.closestHitFraction == 0.0F) break;
/*  671: 669 */      CollisionObject localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*  672:     */      
/*  683: 681 */      if (paramObject != localCollisionObject) {
/*  684: 682 */        BroadphaseProxy localBroadphaseProxy = localCollisionObject.getBroadphaseHandle();
/*  685:     */        
/*  688:     */        try
/*  689:     */        {
/*  690: 688 */          if ((localBroadphaseProxy != null) && (paramRayResultCallback.needsCollision(localBroadphaseProxy)))
/*  691:     */          {
/*  692: 690 */            localCollisionObject.getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/*  693:     */            
/*  694: 692 */            doRayTestNext(localBroadphaseProxy, localCollisionObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*  695:     */          }
/*  696:     */        } catch (Exception localException) {
/*  697: 695 */          
/*  698:     */          
/*  699: 697 */            localException;
/*  700:     */        }
/*  701:     */      }
/*  702:     */    }
/*  703:     */  }
/*  704:     */  
/*  707:     */  private void doRayTestNext(BroadphaseProxy paramBroadphaseProxy, CollisionObject paramCollisionObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*  708:     */  {
/*  709: 705 */    this.hitLambda[0] = paramRayResultCallback.closestHitFraction;
/*  710: 706 */    if (rayAabb(paramVector3f1, paramVector3f2, this.collisionObjectAabbMin, this.collisionObjectAabbMax, this.hitLambda, this.hitNormal)) {
/*  711: 707 */      doInnerRayTest(paramCollisionObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*  712:     */    }
/*  713:     */  }
/*  714:     */  
/*  716:     */  private void doInnerRayTest(CollisionObject paramCollisionObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*  717:     */  {
/*  718:     */    Object localObject;
/*  719: 715 */    if (paramCollisionObject.getCollisionShape().isCompound()) {
/*  720: 716 */      localObject = (CompoundShape)paramCollisionObject.getCollisionShape();
/*  721: 717 */      handleCompoundRayTest((CompoundShape)localObject, paramCollisionObject, paramVector3f1, paramVector3f2, paramRayResultCallback);
/*  722:     */      
/*  723: 719 */      return; } if (((paramCollisionObject.getCollisionShape() instanceof CubeShape)) && ((paramRayResultCallback == null) || ((paramRayResultCallback instanceof CubeRayCastResult)))) {
/*  724: 720 */      localObject = (CubeRayCastResult)paramRayResultCallback;
/*  725: 721 */      paramRayResultCallback = (CubeShape)paramCollisionObject.getCollisionShape();
/*  726:     */      
/*  729: 725 */      if ((((CubeRayCastResult)localObject).getOwner() != null) && (((CubeRayCastResult)localObject).getOwner() == paramRayResultCallback.getSegmentBuffer().a()))
/*  730:     */      {
/*  731: 727 */        return;
/*  732:     */      }
/*  733:     */      
/*  734: 730 */      rayTestSingleCubeMesh(this.rayFromTrans, this.rayToTrans, paramCollisionObject, paramRayResultCallback, paramCollisionObject.getWorldTransform(this.tmpTrans), (CubeRayCastResult)localObject, ((CubeRayCastResult)localObject).getLastHitSegment());
/*  735:     */      
/*  739: 735 */      return; }
/*  740: 736 */    if ((!(paramRayResultCallback instanceof CubeRayCastResult)) || (!((CubeRayCastResult)paramRayResultCallback).onlyCubeMeshes))
/*  741:     */    {
/*  744: 740 */      rayTestSingle(this.rayFromTrans, this.rayToTrans, paramCollisionObject, paramCollisionObject.getCollisionShape(), paramCollisionObject.getWorldTransform(this.tmpTrans), paramRayResultCallback);
/*  745:     */    }
/*  746:     */  }
/*  747:     */  
/*  752: 748 */  public it.unimi.dsi.fastutil.objects.ObjectArrayList cache = new it.unimi.dsi.fastutil.objects.ObjectArrayList();
/*  753: 749 */  public boolean cacheValid = false;
/*  754:     */  
/*  755:     */  public void buildCache() {
/*  756: 752 */    for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  757:     */      CollisionObject localCollisionObject;
/*  758: 754 */      (localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i)).getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/*  759:     */      
/*  760: 756 */      if (!this.cacheValid) {
/*  761: 757 */        if (this.cache.size() <= i) {
/*  762: 758 */          this.cache.add(new xO());
/*  763:     */        }
/*  764: 760 */        ((xO)this.cache.get(i)).b(this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/*  765:     */      }
/*  766:     */    }
/*  767: 763 */    while (this.cache.size() > this.collisionObjects.size()) {
/*  768: 764 */      this.cache.remove(this.cache.size() - 1);
/*  769:     */    }
/*  770: 766 */    this.cacheValid = true;
/*  771:     */  }
/*  772:     */  
/*  773: 769 */  static Vector3f aabbHalfExtent = new Vector3f();
/*  774: 770 */  static Vector3f aabbCenter = new Vector3f();
/*  775: 771 */  static Vector3f source = new Vector3f();
/*  776: 772 */  static Vector3f target = new Vector3f();
/*  777: 773 */  static Vector3f r = new Vector3f();
/*  778: 774 */  static Vector3f hitNormalTmp = new Vector3f();
/*  779:     */  
/*  781:     */  public boolean rayAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, float[] paramArrayOfFloat, Vector3f paramVector3f5)
/*  782:     */  {
/*  783: 779 */    aabbHalfExtent.sub(paramVector3f4, paramVector3f3);
/*  784: 780 */    aabbHalfExtent.scale(0.5F);
/*  785:     */    
/*  786: 782 */    aabbCenter.add(paramVector3f4, paramVector3f3);
/*  787: 783 */    aabbCenter.scale(0.5F);
/*  788:     */    
/*  789: 785 */    source.sub(paramVector3f1, aabbCenter);
/*  790: 786 */    target.sub(paramVector3f2, aabbCenter);
/*  791:     */    
/*  792: 788 */    paramVector3f1 = AabbUtil2.outcode(source, aabbHalfExtent);
/*  793: 789 */    paramVector3f2 = AabbUtil2.outcode(target, aabbHalfExtent);
/*  794: 790 */    if ((paramVector3f1 & paramVector3f2) == 0) {
/*  795: 791 */      paramVector3f3 = 0.0F;
/*  796: 792 */      paramVector3f4 = paramArrayOfFloat[0];
/*  797: 793 */      r.sub(target, source);
/*  798:     */      
/*  799: 795 */      hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  800:     */      float f;
/*  801: 797 */      if ((paramVector3f1 & 0x1) != 0)
/*  802:     */      {
/*  804: 800 */        f = (-source.x - aabbHalfExtent.x) / r.x;
/*  805: 801 */        if (0.0F <= f) {
/*  806: 802 */          paramVector3f3 = f;
/*  807: 803 */          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  808: 804 */          hitNormalTmp.x = 1.0F;
/*  809:     */        }
/*  810:     */      }
/*  811: 807 */      else if ((paramVector3f2 & 0x1) != 0) {
/*  812: 808 */        f = (-source.x - aabbHalfExtent.x) / r.x;
/*  813:     */        
/*  814: 810 */        paramVector3f4 = Math.min(paramVector3f4, f);
/*  815:     */      }
/*  816: 812 */      if ((paramVector3f1 & 0x2) != 0)
/*  817:     */      {
/*  820: 816 */        f = (-source.y - aabbHalfExtent.y) / r.y;
/*  821: 817 */        if (paramVector3f3 <= f) {
/*  822: 818 */          paramVector3f3 = f;
/*  823: 819 */          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  824: 820 */          hitNormalTmp.y = 1.0F;
/*  825:     */        }
/*  826:     */      }
/*  827: 823 */      else if ((paramVector3f2 & 0x2) != 0) {
/*  828: 824 */        f = (-source.y - aabbHalfExtent.y) / r.y;
/*  829:     */        
/*  830: 826 */        paramVector3f4 = Math.min(paramVector3f4, f);
/*  831:     */      }
/*  832: 828 */      if ((paramVector3f1 & 0x4) != 0)
/*  833:     */      {
/*  836: 832 */        f = (-source.z - aabbHalfExtent.z) / r.z;
/*  837: 833 */        if (paramVector3f3 <= f) {
/*  838: 834 */          paramVector3f3 = f;
/*  839: 835 */          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  840: 836 */          hitNormalTmp.z = 1.0F;
/*  841:     */        }
/*  842:     */      }
/*  843: 839 */      else if ((paramVector3f2 & 0x4) != 0) {
/*  844: 840 */        f = (-source.z - aabbHalfExtent.z) / r.z;
/*  845:     */        
/*  846: 842 */        paramVector3f4 = Math.min(paramVector3f4, f);
/*  847:     */      }
/*  848:     */      
/*  849: 845 */      if ((paramVector3f1 & 0x8) != 0)
/*  850:     */      {
/*  854: 850 */        f = (-source.x - -aabbHalfExtent.x) / r.x;
/*  855: 851 */        if (paramVector3f3 <= f) {
/*  856: 852 */          paramVector3f3 = f;
/*  857: 853 */          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  858: 854 */          hitNormalTmp.x = -1.0F;
/*  859:     */        }
/*  860:     */      }
/*  861: 857 */      else if ((paramVector3f2 & 0x8) != 0) {
/*  862: 858 */        f = (-source.x - -aabbHalfExtent.x) / r.x;
/*  863:     */        
/*  864: 860 */        paramVector3f4 = Math.min(paramVector3f4, f);
/*  865:     */      }
/*  866: 862 */      if ((paramVector3f1 & 0x10) != 0)
/*  867:     */      {
/*  870: 866 */        f = (-source.y - -aabbHalfExtent.y) / r.y;
/*  871: 867 */        if (paramVector3f3 <= f) {
/*  872: 868 */          paramVector3f3 = f;
/*  873: 869 */          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  874: 870 */          hitNormalTmp.y = -1.0F;
/*  875:     */        }
/*  876:     */      }
/*  877: 873 */      else if ((paramVector3f2 & 0x10) != 0) {
/*  878: 874 */        f = (-source.y - -aabbHalfExtent.y) / r.y;
/*  879:     */        
/*  880: 876 */        paramVector3f4 = Math.min(paramVector3f4, f);
/*  881:     */      }
/*  882: 878 */      if ((paramVector3f1 & 0x20) != 0)
/*  883:     */      {
/*  886: 882 */        f = (-source.z - -aabbHalfExtent.z) / r.z;
/*  887: 883 */        if (paramVector3f3 <= f) {
/*  888: 884 */          paramVector3f3 = f;
/*  889: 885 */          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  890: 886 */          hitNormalTmp.z = -1.0F;
/*  891:     */        }
/*  892:     */      }
/*  893: 889 */      else if ((paramVector3f2 & 0x20) != 0) {
/*  894: 890 */        f = (-source.z - -aabbHalfExtent.z) / r.z;
/*  895:     */        
/*  896: 892 */        paramVector3f4 = Math.min(paramVector3f4, f);
/*  897:     */      }
/*  898: 894 */      if (paramVector3f3 <= paramVector3f4)
/*  899:     */      {
/*  902: 898 */        paramArrayOfFloat[0] = paramVector3f3;
/*  903: 899 */        paramVector3f5.set(hitNormalTmp);
/*  904: 900 */        return true;
/*  905:     */      }
/*  906:     */    }
/*  907: 903 */    return false;
/*  908:     */  }
/*  909:     */  
/*  915:     */  private void rayTestSingleCubeMesh(Transform paramTransform1, Transform paramTransform2, CollisionObject paramCollisionObject, CollisionShape paramCollisionShape, Transform paramTransform3, CubeRayCastResult paramCubeRayCastResult, Segment paramSegment)
/*  916:     */  {
/*  917: 913 */    (paramCollisionShape = new SphereShape(0.0F)).setMargin(0.0F);
/*  918:     */    
/*  921: 917 */    (paramSegment = new ConvexCast.CastResult()).fraction = paramCubeRayCastResult.closestHitFraction;
/*  922:     */    
/*  923: 919 */    VoronoiSimplexSolverExt localVoronoiSimplexSolverExt = new VoronoiSimplexSolverExt();
/*  924:     */    
/*  929: 925 */    paramCollisionObject = new SubsimplexRayCubesCovexCast(paramCollisionShape, paramCollisionObject, localVoronoiSimplexSolverExt, paramCubeRayCastResult);
/*  930:     */    
/*  934: 930 */    System.currentTimeMillis();
/*  935: 931 */    paramCollisionObject.calcTimeOfImpact(paramTransform1, paramTransform2, paramTransform3, paramTransform3, paramSegment);
/*  936:     */  }
/*  937:     */  
/* 1144:     */  public void clean()
/* 1145:     */  {
/* 1146:1142 */    ArrayList localArrayList1 = new ArrayList(getCollisionObjectArray().size());
/* 1147:1143 */    for (int i = 0; i < getCollisionObjectArray().size(); i++) {
/* 1148:1144 */      localArrayList1.add(getCollisionObjectArray().getQuick(i));
/* 1149:     */    }
/* 1150:     */    
/* 1151:1147 */    ArrayList localArrayList2 = new ArrayList(getNumActions());
/* 1152:1148 */    for (int j = 0; j < getNumActions(); j++) {
/* 1153:1149 */      localArrayList2.add(getAction(j));
/* 1154:     */    }
/* 1155:     */    
/* 1156:1152 */    ArrayList localArrayList3 = new ArrayList(this.vehicles.size());
/* 1157:     */    
/* 1158:1154 */    for (int k = 0; k < this.vehicles.size(); k++) {
/* 1159:1155 */      localArrayList3.add(this.vehicles.get(k));
/* 1160:     */    }
/* 1161:     */    
/* 1162:1158 */    ArrayList localArrayList4 = new ArrayList(this.constraints.size());
/* 1163:     */    
/* 1164:1160 */    for (int m = 0; m < this.constraints.size(); m++) {
/* 1165:1161 */      localArrayList4.add(this.constraints.get(m));
/* 1166:     */    }
/* 1167:     */    
/* 1171:1167 */    for (m = 0; m < localArrayList4.size(); m++) {
/* 1172:1168 */      removeConstraint((TypedConstraint)localArrayList4.get(m));
/* 1173:     */    }
/* 1174:     */    
/* 1175:1171 */    for (m = 0; m < localArrayList3.size(); m++) {
/* 1176:1172 */      removeVehicle((RaycastVehicle)localArrayList3.get(m));
/* 1177:     */    }
/* 1178:     */    
/* 1179:1175 */    for (m = 0; m < localArrayList1.size(); m++) {
/* 1180:1176 */      removeCollisionObject((CollisionObject)localArrayList1.get(m));
/* 1181:     */    }
/* 1182:     */    
/* 1183:1179 */    for (m = 0; m < localArrayList2.size(); m++) {
/* 1184:1180 */      removeAction((ActionInterface)localArrayList2.get(m));
/* 1185:     */    }
/* 1186:     */  }
/* 1187:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */