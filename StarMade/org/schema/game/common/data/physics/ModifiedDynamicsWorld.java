package org.schema.game.common.data.physics;

import class_1407;
import class_29;
import class_886;
import class_988;
import com.bulletphysics.BulletGlobals;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
import com.bulletphysics.collision.dispatch.SimulationIslandManager;
import com.bulletphysics.collision.narrowphase.ConvexCast;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.ActionInterface;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.common.util.linAlg.TransformTools;
import org.schema.game.common.data.world.Segment;

public class ModifiedDynamicsWorld
  extends DiscreteDynamicsWorld
{
  private final Vector3f minAabb = new Vector3f();
  private final Vector3f maxAabb = new Vector3f();
  private final Vector3f tmpAABBSingle = new Vector3f();
  private final Transform tmpTransAABBSingle = new Transform();
  private final Vector3f contactThreshold = new Vector3f();
  private final Transform interpolatedTransform = new Transform();
  private final Transform tmpTrans2 = new Transform();
  private final Vector3f tmpLinVel = new Vector3f();
  private final Vector3f tmpAngVel = new Vector3f();
  public final Vector3f iAxis = new Vector3f();
  public final Quat4f iDorn = new Quat4f();
  public final Quat4f iorn0 = new Quat4f();
  public final Quat4f iPredictOrn = new Quat4f();
  public final float[] float4Temp = new float[4];
  Transform tmpTrans = new Transform();
  Transform rayFromTrans = new Transform();
  Transform rayToTrans = new Transform();
  Vector3f collisionObjectAabbMin = new Vector3f();
  Vector3f collisionObjectAabbMax = new Vector3f();
  Vector3f hitNormal = new Vector3f();
  Transform convexFromTrans = new Transform();
  Transform convexToTrans = new Transform();
  Vector3f castShapeAabbMin = new Vector3f();
  Vector3f castShapeAabbMax = new Vector3f();
  Vector3f linVel = new Vector3f();
  Vector3f angVel = new Vector3f();
  Transform field_340 = new Transform();
  Quat4f quat = new Quat4f();
  private class_1407 state;
  private com.bulletphysics.util.ObjectArrayList sortedConstraints = new com.bulletphysics.util.ObjectArrayList();
  private ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt solverCallback = new ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt(null);
  private static final Comparator sortConstraintOnIslandPredicate = new ModifiedDynamicsWorld.1();
  private final Transform childWorldTrans = new Transform();
  Transform childTrans = new Transform();
  float[] hitLambda = new float[1];
  Vector3f dir = new Vector3f();
  Vector3f closest = new Vector3f();
  Vector3f closestDir = new Vector3f();
  public it.unimi.dsi.fastutil.objects.ObjectArrayList cache = new it.unimi.dsi.fastutil.objects.ObjectArrayList();
  public boolean cacheValid = false;
  static Vector3f aabbHalfExtent = new Vector3f();
  static Vector3f aabbCenter = new Vector3f();
  static Vector3f source = new Vector3f();
  static Vector3f target = new Vector3f();
  static Vector3f field_341 = new Vector3f();
  static Vector3f hitNormalTmp = new Vector3f();
  
  private static void handleCompound(ConvexShape paramConvexShape, CompoundShape paramCompoundShape, CollisionObject paramCollisionObject, Transform paramTransform1, float paramFloat, Transform paramTransform2, Transform paramTransform3, CollisionWorld.ConvexResultCallback paramConvexResultCallback)
  {
    for (int i = 0; i < paramCompoundShape.getNumChildShapes(); i++)
    {
      Object localObject = paramCompoundShape.getChildTransform(i, new Transform());
      CollisionShape localCollisionShape = paramCompoundShape.getChildShape(i);
      Transform localTransform;
      (localTransform = new Transform()).mul(paramTransform1, (Transform)localObject);
      localObject = paramCollisionObject.getCollisionShape();
      paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape);
      objectQuerySingle(paramConvexShape, paramTransform2, paramTransform3, paramCollisionObject, localCollisionShape, localTransform, paramConvexResultCallback, paramFloat);
      paramCollisionObject.internalSetTemporaryCollisionShape((CollisionShape)localObject);
    }
  }
  
  public final OverlappingPairCache getPairCache()
  {
    return this.broadphasePairCache.getOverlappingPairCache();
  }
  
  public void updateSingleAabb(CollisionObject paramCollisionObject)
  {
    paramCollisionObject.getCollisionShape().getAabb(paramCollisionObject.getWorldTransform(this.tmpTransAABBSingle), this.minAabb, this.maxAabb);
    this.contactThreshold.set(BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold());
    this.minAabb.sub(this.contactThreshold);
    this.maxAabb.add(this.contactThreshold);
    BroadphaseInterface localBroadphaseInterface = this.broadphasePairCache;
    this.tmpAABBSingle.sub(this.maxAabb, this.minAabb);
    if ((paramCollisionObject.isStaticObject()) || (this.tmpAABBSingle.lengthSquared() < 1.0E+012F))
    {
      localBroadphaseInterface.setAabb(paramCollisionObject.getBroadphaseHandle(), this.minAabb, this.maxAabb, this.dispatcher1);
      return;
    }
    paramCollisionObject.setActivationState(5);
    System.err.println("Exception!!! Overflow in AABB, object removed from simulation " + paramCollisionObject);
    System.err.println("If you can reproduce this, please email bugs@continuousphysics.com\n");
    System.err.println("Please include above information, your Platform, version of OS.\n");
    System.err.println("Thanks.\n");
  }
  
  public void updateAabbs()
  {
    BulletStats.pushProfile("updateAabbs");
    try
    {
      for (int i = 0; i < this.collisionObjects.size(); i++)
      {
        CollisionObject localCollisionObject;
        if ((localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i)).isActive()) {
          updateSingleAabb(localCollisionObject);
        }
      }
      return;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  protected void synchronizeMotionStates()
  {
    for (int i = 0; i < this.collisionObjects.size(); i++)
    {
      RigidBody localRigidBody;
      if (((localRigidBody = RigidBody.upcast((CollisionObject)this.collisionObjects.getQuick(i))) != null) && (localRigidBody.getMotionState() != null) && (!localRigidBody.isStaticOrKinematicObject()))
      {
        TransformTools.a(localRigidBody.getInterpolationWorldTransform(this.tmpTrans2), localRigidBody.getInterpolationLinearVelocity(this.tmpLinVel), localRigidBody.getInterpolationAngularVelocity(this.tmpAngVel), this.localTime * localRigidBody.getHitFraction(), this.interpolatedTransform, this.iAxis, this.iDorn, this.iorn0, this.iPredictOrn, this.float4Temp);
        localRigidBody.getMotionState().setWorldTransform(this.interpolatedTransform);
      }
    }
    if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0)) {
      for (i = 0; i < this.vehicles.size(); i++) {
        for (int j = 0; j < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); j++) {
          ((RaycastVehicle)this.vehicles.getQuick(i)).updateWheelTransform(j, true);
        }
      }
    }
  }
  
  public void performDiscreteCollisionDetection()
  {
    BulletStats.pushProfile("performDiscreteCollisionDetection");
    try
    {
      updateAabbs();
      BulletStats.pushProfile("calculateOverlappingPairs");
      Dispatcher localDispatcher;
      try
      {
        this.broadphasePairCache.calculateOverlappingPairs(this.dispatcher1);
      }
      finally {}
      BulletStats.pushProfile("dispatchAllCollisionPairs");
      try
      {
        if (localDispatcher != null) {
          localDispatcher.dispatchAllCollisionPairs(this.broadphasePairCache.getOverlappingPairCache(), this.dispatchInfo, this.dispatcher1);
        }
      }
      finally {}
      return;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public static void objectQuerySingle(ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, CollisionObject paramCollisionObject, CollisionShape paramCollisionShape, Transform paramTransform3, CollisionWorld.ConvexResultCallback paramConvexResultCallback, float paramFloat)
  {
    if ((paramCollisionShape instanceof CubeShape))
    {
      (paramCollisionShape = new ConvexCast.CastResult()).allowedPenetration = paramFloat;
      paramCollisionShape.fraction = 1.0F;
      paramFloat = new VoronoiSimplexSolverExt();
      new SubsimplexCubesCovexCast(paramConvexShape, paramCollisionObject.getCollisionShape(), paramCollisionObject, paramFloat, paramConvexResultCallback, null).calcTimeOfImpact(paramTransform1, paramTransform2, paramTransform3, paramTransform3, paramCollisionShape);
      return;
    }
    if (paramCollisionShape.isCompound())
    {
      handleCompound(paramConvexShape, (CompoundShape)paramCollisionShape, paramCollisionObject, paramTransform3, paramFloat, paramTransform1, paramTransform2, paramConvexResultCallback);
      return;
    }
    CollisionWorld.objectQuerySingle(paramConvexShape, paramTransform1, paramTransform2, paramCollisionObject, paramCollisionShape, paramTransform3, paramConvexResultCallback, paramFloat);
  }
  
  public ModifiedDynamicsWorld(Dispatcher paramDispatcher, BroadphaseInterface paramBroadphaseInterface, ConstraintSolver paramConstraintSolver, CollisionConfiguration paramCollisionConfiguration, class_1407 paramclass_1407)
  {
    super(paramDispatcher, paramBroadphaseInterface, paramConstraintSolver, paramCollisionConfiguration);
    this.state = paramclass_1407;
    this.islandManager = new SimulationIslandManagerExt();
  }
  
  public void removeCollisionObject(CollisionObject paramCollisionObject)
  {
    BroadphaseProxy localBroadphaseProxy;
    if ((localBroadphaseProxy = paramCollisionObject.getBroadphaseHandle()) != null)
    {
      getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(localBroadphaseProxy, this.dispatcher1);
      getBroadphase().destroyProxy(localBroadphaseProxy, this.dispatcher1);
      paramCollisionObject.setBroadphaseHandle(null);
      com.bulletphysics.util.ObjectArrayList localObjectArrayList = this.dispatcher1.getInternalManifoldPointer();
      for (int i = 0; i < localObjectArrayList.size(); i++)
      {
        PersistentManifold localPersistentManifold;
        if (((localPersistentManifold = (PersistentManifold)localObjectArrayList.get(i)).getBody0() == paramCollisionObject) || (localPersistentManifold.getBody1() == paramCollisionObject))
        {
          this.dispatcher1.releaseManifold(localPersistentManifold);
          i = 0;
        }
      }
      assert (checkProdyDestroyed(localBroadphaseProxy));
    }
    this.collisionObjects.remove(paramCollisionObject);
  }
  
  public boolean checkProdyDestroyed(BroadphaseProxy paramBroadphaseProxy)
  {
    com.bulletphysics.util.ObjectArrayList localObjectArrayList = getBroadphase().getOverlappingPairCache().getOverlappingPairArray();
    for (int i = 0; i < localObjectArrayList.size(); i++)
    {
      BroadphasePair localBroadphasePair;
      if (((localBroadphasePair = (BroadphasePair)localObjectArrayList.getQuick(i)).pProxy0 == paramBroadphaseProxy.clientObject) || (localBroadphasePair.pProxy1 == paramBroadphaseProxy.clientObject))
      {
        System.err.println("Exception: Proxy Has NOT been destroyed completely: " + paramBroadphaseProxy.clientObject);
        return false;
      }
    }
    return true;
  }
  
  protected void solveConstraints(ContactSolverInfo paramContactSolverInfo)
  {
    BulletStats.pushProfile("solveConstraints");
    try
    {
      this.sortedConstraints.clear();
      for (int i = 0; i < this.constraints.size(); i++) {
        this.sortedConstraints.add(this.constraints.getQuick(i));
      }
      MiscUtil.quickSort(this.sortedConstraints, sortConstraintOnIslandPredicate);
      com.bulletphysics.util.ObjectArrayList localObjectArrayList = getNumConstraints() != 0 ? this.sortedConstraints : null;
      this.solverCallback.init(paramContactSolverInfo, this.constraintSolver, localObjectArrayList, this.sortedConstraints.size(), this.debugDrawer, this.dispatcher1, this.state);
      this.constraintSolver.prepareSolve(getCollisionWorld().getNumCollisionObjects(), getCollisionWorld().getDispatcher().getNumManifolds());
      this.islandManager.buildAndProcessIslands(getCollisionWorld().getDispatcher(), getCollisionWorld().getCollisionObjectArray(), this.solverCallback);
      this.constraintSolver.allSolved(paramContactSolverInfo, this.debugDrawer);
      return;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  private static int getConstraintIslandId(TypedConstraint paramTypedConstraint)
  {
    RigidBody localRigidBody = paramTypedConstraint.getRigidBodyA();
    paramTypedConstraint = paramTypedConstraint.getRigidBodyB();
    if (localRigidBody.getIslandTag() >= 0) {
      return localRigidBody.getIslandTag();
    }
    return paramTypedConstraint.getIslandTag();
  }
  
  private void handleCompoundRayTest(CompoundShape paramCompoundShape, CollisionObject paramCollisionObject, Vector3f paramVector3f1, Vector3f paramVector3f2, CollisionWorld.RayResultCallback paramRayResultCallback)
  {
    for (int i = 0; i < paramCompoundShape.getNumChildShapes(); i++)
    {
      paramCompoundShape.getChildTransform(i, this.childTrans);
      CollisionShape localCollisionShape1 = paramCompoundShape.getChildShape(i);
      this.childWorldTrans.set(paramCollisionObject.getWorldTransform(this.tmpTrans));
      class_29.a1(this.childWorldTrans, this.childTrans);
      CollisionShape localCollisionShape2 = paramCollisionObject.getCollisionShape();
      if (((localCollisionShape1 instanceof CubeShape)) && ((paramRayResultCallback == null) || ((paramRayResultCallback instanceof CubeRayCastResult))))
      {
        CubeRayCastResult localCubeRayCastResult = (CubeRayCastResult)paramRayResultCallback;
        CubeShape localCubeShape = (CubeShape)localCollisionShape1;
        if ((localCubeRayCastResult.getOwner() == null) || (localCubeRayCastResult.getOwner() != localCubeShape.getSegmentBuffer().a12()))
        {
          paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape1);
          rayTestSingleCubeMesh(this.rayFromTrans, this.rayToTrans, paramCollisionObject, localCollisionShape1, this.childWorldTrans, localCubeRayCastResult, localCubeRayCastResult.getLastHitSegment());
          paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape2);
        }
      }
      else if ((localCollisionShape1 instanceof CompoundShape))
      {
        handleCompoundRayTest((CompoundShape)localCollisionShape1, paramCollisionObject, paramVector3f1, paramVector3f2, paramRayResultCallback);
      }
      else
      {
        paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape1);
        if (!$assertionsDisabled) {
          throw new AssertionError(localCollisionShape1 + ": " + localCollisionShape1.getClass().getSimpleName() + "; " + (paramRayResultCallback != null ? paramRayResultCallback.getClass().getSimpleName() : "null-ResultCallback"));
        }
        rayTestSingle(this.rayFromTrans, this.rayToTrans, paramCollisionObject, localCollisionShape1, this.childWorldTrans, paramRayResultCallback);
        paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape2);
      }
    }
  }
  
  public void rayTest(Vector3f paramVector3f1, Vector3f paramVector3f2, CollisionWorld.RayResultCallback paramRayResultCallback)
  {
    this.rayFromTrans.setIdentity();
    this.rayFromTrans.origin.set(paramVector3f1);
    this.rayToTrans.setIdentity();
    this.rayToTrans.origin.set(paramVector3f2);
    this.hitLambda[0] = 0.0F;
    Object localObject = (paramRayResultCallback != null) && ((paramRayResultCallback instanceof CubeRayCastResult)) && (((CubeRayCastResult)paramRayResultCallback).getOwner() != null) ? ((CubeRayCastResult)paramRayResultCallback).getOwner() : null;
    if (this.cacheValid)
    {
      doRayTestCached(localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
      return;
    }
    doRayTest(localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
  }
  
  public void doRayTestCached(Object paramObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    for (int i = 0; (i < this.collisionObjects.size()) && (paramRayResultCallback.closestHitFraction != 0.0F); i++)
    {
      Object localObject = (class_988)this.cache.get(i);
      this.dir.sub(paramVector3f2, paramVector3f1);
      ((class_988)localObject).a4(paramVector3f1, this.closest);
      this.closestDir.sub(this.closest, paramVector3f1);
      if (this.dir.lengthSquared() >= this.closestDir.lengthSquared())
      {
        this.collisionObjectAabbMin.set(((class_988)localObject).field_1273);
        this.collisionObjectAabbMax.set(((class_988)localObject).field_1274);
        localObject = (CollisionObject)this.collisionObjects.getQuick(i);
        if (paramObject != localObject)
        {
          BroadphaseProxy localBroadphaseProxy = ((CollisionObject)localObject).getBroadphaseHandle();
          try
          {
            if ((localBroadphaseProxy != null) && (paramRayResultCallback.needsCollision(localBroadphaseProxy))) {
              doRayTestNext(localBroadphaseProxy, (CollisionObject)localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
            }
          }
          catch (Exception localException)
          {
            localException;
          }
        }
      }
    }
  }
  
  public void doRayTest(Object paramObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    for (int i = 0; (i < this.collisionObjects.size()) && (paramRayResultCallback.closestHitFraction != 0.0F); i++)
    {
      CollisionObject localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
      if (paramObject != localCollisionObject)
      {
        BroadphaseProxy localBroadphaseProxy = localCollisionObject.getBroadphaseHandle();
        try
        {
          if ((localBroadphaseProxy != null) && (paramRayResultCallback.needsCollision(localBroadphaseProxy)))
          {
            localCollisionObject.getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
            doRayTestNext(localBroadphaseProxy, localCollisionObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
          }
        }
        catch (Exception localException)
        {
          localException;
        }
      }
    }
  }
  
  private void doRayTestNext(BroadphaseProxy paramBroadphaseProxy, CollisionObject paramCollisionObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    this.hitLambda[0] = paramRayResultCallback.closestHitFraction;
    if (rayAabb(paramVector3f1, paramVector3f2, this.collisionObjectAabbMin, this.collisionObjectAabbMax, this.hitLambda, this.hitNormal)) {
      doInnerRayTest(paramCollisionObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
    }
  }
  
  private void doInnerRayTest(CollisionObject paramCollisionObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    Object localObject;
    if (paramCollisionObject.getCollisionShape().isCompound())
    {
      localObject = (CompoundShape)paramCollisionObject.getCollisionShape();
      handleCompoundRayTest((CompoundShape)localObject, paramCollisionObject, paramVector3f1, paramVector3f2, paramRayResultCallback);
      return;
    }
    if (((paramCollisionObject.getCollisionShape() instanceof CubeShape)) && ((paramRayResultCallback == null) || ((paramRayResultCallback instanceof CubeRayCastResult))))
    {
      localObject = (CubeRayCastResult)paramRayResultCallback;
      paramRayResultCallback = (CubeShape)paramCollisionObject.getCollisionShape();
      if ((((CubeRayCastResult)localObject).getOwner() != null) && (((CubeRayCastResult)localObject).getOwner() == paramRayResultCallback.getSegmentBuffer().a12())) {
        return;
      }
      rayTestSingleCubeMesh(this.rayFromTrans, this.rayToTrans, paramCollisionObject, paramRayResultCallback, paramCollisionObject.getWorldTransform(this.tmpTrans), (CubeRayCastResult)localObject, ((CubeRayCastResult)localObject).getLastHitSegment());
      return;
    }
    if ((!(paramRayResultCallback instanceof CubeRayCastResult)) || (!((CubeRayCastResult)paramRayResultCallback).onlyCubeMeshes)) {
      rayTestSingle(this.rayFromTrans, this.rayToTrans, paramCollisionObject, paramCollisionObject.getCollisionShape(), paramCollisionObject.getWorldTransform(this.tmpTrans), paramRayResultCallback);
    }
  }
  
  public void buildCache()
  {
    for (int i = 0; i < this.collisionObjects.size(); i++)
    {
      CollisionObject localCollisionObject;
      (localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i)).getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
      if (!this.cacheValid)
      {
        if (this.cache.size() <= i) {
          this.cache.add(new class_988());
        }
        ((class_988)this.cache.get(i)).b2(this.collisionObjectAabbMin, this.collisionObjectAabbMax);
      }
    }
    while (this.cache.size() > this.collisionObjects.size()) {
      this.cache.remove(this.cache.size() - 1);
    }
    this.cacheValid = true;
  }
  
  public boolean rayAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, float[] paramArrayOfFloat, Vector3f paramVector3f5)
  {
    aabbHalfExtent.sub(paramVector3f4, paramVector3f3);
    aabbHalfExtent.scale(0.5F);
    aabbCenter.add(paramVector3f4, paramVector3f3);
    aabbCenter.scale(0.5F);
    source.sub(paramVector3f1, aabbCenter);
    target.sub(paramVector3f2, aabbCenter);
    paramVector3f1 = AabbUtil2.outcode(source, aabbHalfExtent);
    paramVector3f2 = AabbUtil2.outcode(target, aabbHalfExtent);
    if ((paramVector3f1 & paramVector3f2) == 0)
    {
      paramVector3f3 = 0.0F;
      paramVector3f4 = paramArrayOfFloat[0];
      field_341.sub(target, source);
      hitNormalTmp.set(0.0F, 0.0F, 0.0F);
      float f;
      if ((paramVector3f1 & 0x1) != 0)
      {
        f = (-source.field_615 - aabbHalfExtent.field_615) / field_341.field_615;
        if (0.0F <= f)
        {
          paramVector3f3 = f;
          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
          hitNormalTmp.field_615 = 1.0F;
        }
      }
      else if ((paramVector3f2 & 0x1) != 0)
      {
        f = (-source.field_615 - aabbHalfExtent.field_615) / field_341.field_615;
        paramVector3f4 = Math.min(paramVector3f4, f);
      }
      if ((paramVector3f1 & 0x2) != 0)
      {
        f = (-source.field_616 - aabbHalfExtent.field_616) / field_341.field_616;
        if (paramVector3f3 <= f)
        {
          paramVector3f3 = f;
          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
          hitNormalTmp.field_616 = 1.0F;
        }
      }
      else if ((paramVector3f2 & 0x2) != 0)
      {
        f = (-source.field_616 - aabbHalfExtent.field_616) / field_341.field_616;
        paramVector3f4 = Math.min(paramVector3f4, f);
      }
      if ((paramVector3f1 & 0x4) != 0)
      {
        f = (-source.field_617 - aabbHalfExtent.field_617) / field_341.field_617;
        if (paramVector3f3 <= f)
        {
          paramVector3f3 = f;
          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
          hitNormalTmp.field_617 = 1.0F;
        }
      }
      else if ((paramVector3f2 & 0x4) != 0)
      {
        f = (-source.field_617 - aabbHalfExtent.field_617) / field_341.field_617;
        paramVector3f4 = Math.min(paramVector3f4, f);
      }
      if ((paramVector3f1 & 0x8) != 0)
      {
        f = (-source.field_615 - -aabbHalfExtent.field_615) / field_341.field_615;
        if (paramVector3f3 <= f)
        {
          paramVector3f3 = f;
          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
          hitNormalTmp.field_615 = -1.0F;
        }
      }
      else if ((paramVector3f2 & 0x8) != 0)
      {
        f = (-source.field_615 - -aabbHalfExtent.field_615) / field_341.field_615;
        paramVector3f4 = Math.min(paramVector3f4, f);
      }
      if ((paramVector3f1 & 0x10) != 0)
      {
        f = (-source.field_616 - -aabbHalfExtent.field_616) / field_341.field_616;
        if (paramVector3f3 <= f)
        {
          paramVector3f3 = f;
          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
          hitNormalTmp.field_616 = -1.0F;
        }
      }
      else if ((paramVector3f2 & 0x10) != 0)
      {
        f = (-source.field_616 - -aabbHalfExtent.field_616) / field_341.field_616;
        paramVector3f4 = Math.min(paramVector3f4, f);
      }
      if ((paramVector3f1 & 0x20) != 0)
      {
        f = (-source.field_617 - -aabbHalfExtent.field_617) / field_341.field_617;
        if (paramVector3f3 <= f)
        {
          paramVector3f3 = f;
          hitNormalTmp.set(0.0F, 0.0F, 0.0F);
          hitNormalTmp.field_617 = -1.0F;
        }
      }
      else if ((paramVector3f2 & 0x20) != 0)
      {
        f = (-source.field_617 - -aabbHalfExtent.field_617) / field_341.field_617;
        paramVector3f4 = Math.min(paramVector3f4, f);
      }
      if (paramVector3f3 <= paramVector3f4)
      {
        paramArrayOfFloat[0] = paramVector3f3;
        paramVector3f5.set(hitNormalTmp);
        return true;
      }
    }
    return false;
  }
  
  private void rayTestSingleCubeMesh(Transform paramTransform1, Transform paramTransform2, CollisionObject paramCollisionObject, CollisionShape paramCollisionShape, Transform paramTransform3, CubeRayCastResult paramCubeRayCastResult, Segment paramSegment)
  {
    (paramCollisionShape = new SphereShape(0.0F)).setMargin(0.0F);
    (paramSegment = new ConvexCast.CastResult()).fraction = paramCubeRayCastResult.closestHitFraction;
    VoronoiSimplexSolverExt localVoronoiSimplexSolverExt = new VoronoiSimplexSolverExt();
    paramCollisionObject = new SubsimplexRayCubesCovexCast(paramCollisionShape, paramCollisionObject, localVoronoiSimplexSolverExt, paramCubeRayCastResult);
    System.currentTimeMillis();
    paramCollisionObject.calcTimeOfImpact(paramTransform1, paramTransform2, paramTransform3, paramTransform3, paramSegment);
  }
  
  public void clean()
  {
    ArrayList localArrayList1 = new ArrayList(getCollisionObjectArray().size());
    for (int i = 0; i < getCollisionObjectArray().size(); i++) {
      localArrayList1.add(getCollisionObjectArray().getQuick(i));
    }
    ArrayList localArrayList2 = new ArrayList(getNumActions());
    for (int j = 0; j < getNumActions(); j++) {
      localArrayList2.add(getAction(j));
    }
    ArrayList localArrayList3 = new ArrayList(this.vehicles.size());
    for (int k = 0; k < this.vehicles.size(); k++) {
      localArrayList3.add(this.vehicles.get(k));
    }
    ArrayList localArrayList4 = new ArrayList(this.constraints.size());
    for (int m = 0; m < this.constraints.size(); m++) {
      localArrayList4.add(this.constraints.get(m));
    }
    for (m = 0; m < localArrayList4.size(); m++) {
      removeConstraint((TypedConstraint)localArrayList4.get(m));
    }
    for (m = 0; m < localArrayList3.size(); m++) {
      removeVehicle((RaycastVehicle)localArrayList3.get(m));
    }
    for (m = 0; m < localArrayList1.size(); m++) {
      removeCollisionObject((CollisionObject)localArrayList1.get(m));
    }
    for (m = 0; m < localArrayList2.size(); m++) {
      removeAction((ActionInterface)localArrayList2.get(m));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */