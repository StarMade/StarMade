package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.BulletGlobals;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import com.bulletphysics.collision.narrowphase.ConvexCast;
import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
import com.bulletphysics.collision.narrowphase.GjkConvexCast;
import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
import com.bulletphysics.collision.narrowphase.TriangleConvexcastCallback;
import com.bulletphysics.collision.narrowphase.TriangleRaycastCallback;
import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.ConcaveShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.TriangleMeshShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.linearmath.VectorUtil;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class CollisionWorld
{
  protected ObjectArrayList<CollisionObject> collisionObjects = new ObjectArrayList();
  protected Dispatcher dispatcher1;
  protected DispatcherInfo dispatchInfo = new DispatcherInfo();
  protected BroadphaseInterface broadphasePairCache;
  protected IDebugDraw debugDrawer;
  private static boolean updateAabbs_reportMe = true;
  
  public CollisionWorld(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration)
  {
    this.dispatcher1 = dispatcher;
    this.broadphasePairCache = broadphasePairCache;
  }
  
  public void destroy()
  {
    for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
    {
      CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(local_i);
      BroadphaseProxy local_bp = collisionObject.getBroadphaseHandle();
      if (local_bp != null)
      {
        getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(local_bp, this.dispatcher1);
        getBroadphase().destroyProxy(local_bp, this.dispatcher1);
      }
    }
  }
  
  public void addCollisionObject(CollisionObject collisionObject)
  {
    addCollisionObject(collisionObject, (short)1, (short)-1);
  }
  
  public void addCollisionObject(CollisionObject arg1, short arg2, short arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      assert (!this.collisionObjects.contains(collisionObject));
      this.collisionObjects.add(collisionObject);
      Transform trans = collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
      Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
      collisionObject.getCollisionShape().getAabb(trans, minAabb, maxAabb);
      BroadphaseNativeType type = collisionObject.getCollisionShape().getShapeType();
      collisionObject.setBroadphaseHandle(getBroadphase().createProxy(minAabb, maxAabb, type, collisionObject, collisionFilterGroup, collisionFilterMask, this.dispatcher1, null));
      return;
    }
    finally
    {
      .Stack tmp132_130 = localStack;
      tmp132_130.pop$com$bulletphysics$linearmath$Transform();
      tmp132_130.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void performDiscreteCollisionDetection()
  {
    BulletStats.pushProfile("performDiscreteCollisionDetection");
    try
    {
      updateAabbs();
      BulletStats.pushProfile("calculateOverlappingPairs");
      try
      {
        this.broadphasePairCache.calculateOverlappingPairs(this.dispatcher1);
      }
      finally {}
      Dispatcher dispatcher = getDispatcher();
      BulletStats.pushProfile("dispatchAllCollisionPairs");
      try
      {
        if (dispatcher != null) {
          dispatcher.dispatchAllCollisionPairs(this.broadphasePairCache.getOverlappingPairCache(), this.dispatchInfo, this.dispatcher1);
        }
      }
      finally {}
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public void removeCollisionObject(CollisionObject collisionObject)
  {
    BroadphaseProxy local_bp = collisionObject.getBroadphaseHandle();
    if (local_bp != null)
    {
      getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(local_bp, this.dispatcher1);
      getBroadphase().destroyProxy(local_bp, this.dispatcher1);
      collisionObject.setBroadphaseHandle(null);
    }
    this.collisionObjects.remove(collisionObject);
  }
  
  public void setBroadphase(BroadphaseInterface pairCache)
  {
    this.broadphasePairCache = pairCache;
  }
  
  public BroadphaseInterface getBroadphase()
  {
    return this.broadphasePairCache;
  }
  
  public OverlappingPairCache getPairCache()
  {
    return this.broadphasePairCache.getOverlappingPairCache();
  }
  
  public Dispatcher getDispatcher()
  {
    return this.dispatcher1;
  }
  
  public DispatcherInfo getDispatchInfo()
  {
    return this.dispatchInfo;
  }
  
  public void updateSingleAabb(CollisionObject arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
      Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
      Vector3f contactThreshold = localStack.get$javax$vecmath$Vector3f();
      contactThreshold.set(BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold());
      minAabb.sub(contactThreshold);
      maxAabb.add(contactThreshold);
      BroadphaseInterface local_bp = this.broadphasePairCache;
      tmp.sub(maxAabb, minAabb);
      if ((colObj.isStaticObject()) || (tmp.lengthSquared() < 1.0E+012F))
      {
        local_bp.setAabb(colObj.getBroadphaseHandle(), minAabb, maxAabb, this.dispatcher1);
      }
      else
      {
        colObj.setActivationState(5);
        if ((updateAabbs_reportMe) && (this.debugDrawer != null))
        {
          updateAabbs_reportMe = false;
          this.debugDrawer.reportErrorWarning("Overflow in AABB, object removed from simulation");
          this.debugDrawer.reportErrorWarning("If you can reproduce this, please email bugs@continuousphysics.com\n");
          this.debugDrawer.reportErrorWarning("Please include above information, your Platform, version of OS.\n");
          this.debugDrawer.reportErrorWarning("Thanks.\n");
        }
      }
      return;
    }
    finally
    {
      .Stack tmp212_210 = localStack;
      tmp212_210.pop$com$bulletphysics$linearmath$Transform();
      tmp212_210.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void updateAabbs()
  {
    BulletStats.pushProfile("updateAabbs");
    try
    {
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(local_i);
        if (colObj.isActive()) {
          updateSingleAabb(colObj);
        }
      }
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public IDebugDraw getDebugDrawer()
  {
    return this.debugDrawer;
  }
  
  public void setDebugDrawer(IDebugDraw debugDrawer)
  {
    this.debugDrawer = debugDrawer;
  }
  
  public int getNumCollisionObjects()
  {
    return this.collisionObjects.size();
  }
  
  public static void rayTestSingle(Transform arg0, Transform arg1, CollisionObject arg2, CollisionShape arg3, Transform arg4, RayResultCallback arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      SphereShape pointShape = new SphereShape(0.0F);
      pointShape.setMargin(0.0F);
      ConvexShape castShape = pointShape;
      if (collisionShape.isConvex())
      {
        ConvexCast.CastResult castResult = new ConvexCast.CastResult();
        castResult.fraction = resultCallback.closestHitFraction;
        ConvexShape convexShape = (ConvexShape)collisionShape;
        VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
        SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(castShape, convexShape, simplexSolver);
        if ((convexCaster.calcTimeOfImpact(rayFromTrans, rayToTrans, colObjWorldTransform, colObjWorldTransform, castResult)) && (castResult.normal.lengthSquared() > 1.0E-004F) && (castResult.fraction < resultCallback.closestHitFraction))
        {
          rayFromTrans.basis.transform(castResult.normal);
          castResult.normal.normalize();
          LocalRayResult localRayResult = new LocalRayResult(collisionObject, null, castResult.normal, castResult.fraction);
          boolean normalInWorldSpace = true;
          resultCallback.addSingleResult(localRayResult, normalInWorldSpace);
        }
      }
      else if (collisionShape.isConcave())
      {
        if (collisionShape.getShapeType() == BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE)
        {
          BvhTriangleMeshShape castResult = (BvhTriangleMeshShape)collisionShape;
          Transform convexShape = localStack.get$com$bulletphysics$linearmath$Transform();
          convexShape.inverse(colObjWorldTransform);
          Vector3f simplexSolver = localStack.get$javax$vecmath$Vector3f(rayFromTrans.origin);
          convexShape.transform(simplexSolver);
          Vector3f convexCaster = localStack.get$javax$vecmath$Vector3f(rayToTrans.origin);
          convexShape.transform(convexCaster);
          BridgeTriangleRaycastCallback localRayResult = new BridgeTriangleRaycastCallback(simplexSolver, convexCaster, resultCallback, collisionObject, castResult);
          localRayResult.hitFraction = resultCallback.closestHitFraction;
          castResult.performRaycast(localRayResult, simplexSolver, convexCaster);
        }
        else
        {
          ConcaveShape castResult = (ConcaveShape)collisionShape;
          Transform convexShape = localStack.get$com$bulletphysics$linearmath$Transform();
          convexShape.inverse(colObjWorldTransform);
          Vector3f simplexSolver = localStack.get$javax$vecmath$Vector3f(rayFromTrans.origin);
          convexShape.transform(simplexSolver);
          Vector3f convexCaster = localStack.get$javax$vecmath$Vector3f(rayToTrans.origin);
          convexShape.transform(convexCaster);
          BridgeTriangleRaycastCallback localRayResult = new BridgeTriangleRaycastCallback(simplexSolver, convexCaster, resultCallback, collisionObject, castResult);
          localRayResult.hitFraction = resultCallback.closestHitFraction;
          Vector3f normalInWorldSpace = localStack.get$javax$vecmath$Vector3f(simplexSolver);
          VectorUtil.setMin(normalInWorldSpace, convexCaster);
          Vector3f rayAabbMaxLocal = localStack.get$javax$vecmath$Vector3f(simplexSolver);
          VectorUtil.setMax(rayAabbMaxLocal, convexCaster);
          castResult.processAllTriangles(localRayResult, normalInWorldSpace, rayAabbMaxLocal);
        }
      }
      else if (collisionShape.isCompound())
      {
        CompoundShape castResult = (CompoundShape)collisionShape;
        int convexShape = 0;
        Transform simplexSolver = localStack.get$com$bulletphysics$linearmath$Transform();
        for (convexShape = 0; convexShape < castResult.getNumChildShapes(); convexShape++)
        {
          castResult.getChildTransform(convexShape, simplexSolver);
          CollisionShape convexCaster = castResult.getChildShape(convexShape);
          Transform localRayResult = localStack.get$com$bulletphysics$linearmath$Transform(colObjWorldTransform);
          localRayResult.mul(simplexSolver);
          CollisionShape normalInWorldSpace = collisionObject.getCollisionShape();
          collisionObject.internalSetTemporaryCollisionShape(convexCaster);
          rayTestSingle(rayFromTrans, rayToTrans, collisionObject, convexCaster, localRayResult, resultCallback);
          collisionObject.internalSetTemporaryCollisionShape(normalInWorldSpace);
        }
      }
      return;
    }
    finally
    {
      .Stack tmp563_561 = localStack;
      tmp563_561.pop$com$bulletphysics$linearmath$Transform();
      tmp563_561.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static void objectQuerySingle(ConvexShape arg0, Transform arg1, Transform arg2, CollisionObject arg3, CollisionShape arg4, Transform arg5, ConvexResultCallback arg6, float arg7)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Vector3f();
      tmp11_7.push$javax$vecmath$Matrix3f();
      if (collisionShape.isConvex())
      {
        ConvexCast.CastResult castResult = new ConvexCast.CastResult();
        castResult.allowedPenetration = allowedPenetration;
        castResult.fraction = 1.0F;
        ConvexShape convexShape = (ConvexShape)collisionShape;
        VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
        GjkEpaPenetrationDepthSolver gjkEpaPenetrationSolver = new GjkEpaPenetrationDepthSolver();
        GjkConvexCast convexCaster2 = new GjkConvexCast(castShape, convexShape, simplexSolver);
        ConvexCast castPtr = convexCaster2;
        if ((castPtr.calcTimeOfImpact(convexFromTrans, convexToTrans, colObjWorldTransform, colObjWorldTransform, castResult)) && (castResult.normal.lengthSquared() > 1.0E-004F) && (castResult.fraction < resultCallback.closestHitFraction))
        {
          castResult.normal.normalize();
          LocalConvexResult localConvexResult = new LocalConvexResult(collisionObject, null, castResult.normal, castResult.hitPoint, castResult.fraction);
          boolean normalInWorldSpace = true;
          resultCallback.addSingleResult(localConvexResult, normalInWorldSpace);
        }
      }
      else if (collisionShape.isConcave())
      {
        if (collisionShape.getShapeType() == BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE)
        {
          BvhTriangleMeshShape castResult = (BvhTriangleMeshShape)collisionShape;
          Transform convexShape = localStack.get$com$bulletphysics$linearmath$Transform();
          convexShape.inverse(colObjWorldTransform);
          Vector3f simplexSolver = localStack.get$javax$vecmath$Vector3f();
          simplexSolver.set(convexFromTrans.origin);
          convexShape.transform(simplexSolver);
          Vector3f gjkEpaPenetrationSolver = localStack.get$javax$vecmath$Vector3f();
          gjkEpaPenetrationSolver.set(convexToTrans.origin);
          convexShape.transform(gjkEpaPenetrationSolver);
          Transform convexCaster2 = localStack.get$com$bulletphysics$linearmath$Transform();
          Matrix3f castPtr = localStack.get$javax$vecmath$Matrix3f();
          castPtr.mul(convexShape.basis, convexToTrans.basis);
          convexCaster2.set(castPtr);
          BridgeTriangleConvexcastCallback localConvexResult = new BridgeTriangleConvexcastCallback(castShape, convexFromTrans, convexToTrans, resultCallback, collisionObject, castResult, colObjWorldTransform);
          localConvexResult.hitFraction = resultCallback.closestHitFraction;
          localConvexResult.normalInWorldSpace = true;
          Vector3f normalInWorldSpace = localStack.get$javax$vecmath$Vector3f();
          Vector3f boxMaxLocal = localStack.get$javax$vecmath$Vector3f();
          castShape.getAabb(convexCaster2, normalInWorldSpace, boxMaxLocal);
          castResult.performConvexcast(localConvexResult, simplexSolver, gjkEpaPenetrationSolver, normalInWorldSpace, boxMaxLocal);
        }
        else
        {
          BvhTriangleMeshShape castResult = (BvhTriangleMeshShape)collisionShape;
          Transform convexShape = localStack.get$com$bulletphysics$linearmath$Transform();
          convexShape.inverse(colObjWorldTransform);
          Vector3f simplexSolver = localStack.get$javax$vecmath$Vector3f();
          simplexSolver.set(convexFromTrans.origin);
          convexShape.transform(simplexSolver);
          Vector3f gjkEpaPenetrationSolver = localStack.get$javax$vecmath$Vector3f();
          gjkEpaPenetrationSolver.set(convexToTrans.origin);
          convexShape.transform(gjkEpaPenetrationSolver);
          Transform convexCaster2 = localStack.get$com$bulletphysics$linearmath$Transform();
          Matrix3f castPtr = localStack.get$javax$vecmath$Matrix3f();
          castPtr.mul(convexShape.basis, convexToTrans.basis);
          convexCaster2.set(castPtr);
          BridgeTriangleConvexcastCallback localConvexResult = new BridgeTriangleConvexcastCallback(castShape, convexFromTrans, convexToTrans, resultCallback, collisionObject, castResult, colObjWorldTransform);
          localConvexResult.hitFraction = resultCallback.closestHitFraction;
          localConvexResult.normalInWorldSpace = false;
          Vector3f normalInWorldSpace = localStack.get$javax$vecmath$Vector3f();
          Vector3f boxMaxLocal = localStack.get$javax$vecmath$Vector3f();
          castShape.getAabb(convexCaster2, normalInWorldSpace, boxMaxLocal);
          Vector3f rayAabbMinLocal = localStack.get$javax$vecmath$Vector3f(simplexSolver);
          VectorUtil.setMin(rayAabbMinLocal, gjkEpaPenetrationSolver);
          Vector3f rayAabbMaxLocal = localStack.get$javax$vecmath$Vector3f(simplexSolver);
          VectorUtil.setMax(rayAabbMaxLocal, gjkEpaPenetrationSolver);
          rayAabbMinLocal.add(normalInWorldSpace);
          rayAabbMaxLocal.add(boxMaxLocal);
          castResult.processAllTriangles(localConvexResult, rayAabbMinLocal, rayAabbMaxLocal);
        }
      }
      else if (collisionShape.isCompound())
      {
        CompoundShape castResult = (CompoundShape)collisionShape;
        for (int convexShape = 0; convexShape < castResult.getNumChildShapes(); convexShape++)
        {
          Transform simplexSolver = castResult.getChildTransform(convexShape, localStack.get$com$bulletphysics$linearmath$Transform());
          CollisionShape gjkEpaPenetrationSolver = castResult.getChildShape(convexShape);
          Transform convexCaster2 = localStack.get$com$bulletphysics$linearmath$Transform();
          convexCaster2.mul(colObjWorldTransform, simplexSolver);
          CollisionShape castPtr = collisionObject.getCollisionShape();
          collisionObject.internalSetTemporaryCollisionShape(gjkEpaPenetrationSolver);
          objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, gjkEpaPenetrationSolver, convexCaster2, resultCallback, allowedPenetration);
          collisionObject.internalSetTemporaryCollisionShape(castPtr);
        }
      }
      return;
    }
    finally
    {
      .Stack tmp729_727 = localStack;
      tmp729_727.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp733_729 = tmp729_727;
      tmp733_729.pop$javax$vecmath$Vector3f();
      tmp733_729.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void rayTest(Vector3f arg1, Vector3f arg2, RayResultCallback arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform rayFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform rayToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      rayFromTrans.setIdentity();
      rayFromTrans.origin.set(rayFromWorld);
      rayToTrans.setIdentity();
      rayToTrans.origin.set(rayToWorld);
      Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
      float[] hitLambda = new float[1];
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int local_i = 0; (local_i < this.collisionObjects.size()) && (resultCallback.closestHitFraction != 0.0F); local_i++)
      {
        CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(local_i);
        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
        {
          collisionObject.getCollisionShape().getAabb(collisionObject.getWorldTransform(tmpTrans), collisionObjectAabbMin, collisionObjectAabbMax);
          hitLambda[0] = resultCallback.closestHitFraction;
          Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
          if (AabbUtil2.rayAabb(rayFromWorld, rayToWorld, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal)) {
            rayTestSingle(rayFromTrans, rayToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback);
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp225_223 = localStack;
      tmp225_223.pop$com$bulletphysics$linearmath$Transform();
      tmp225_223.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void convexSweepTest(ConvexShape arg1, Transform arg2, Transform arg3, ConvexResultCallback arg4)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Vector3f();
      tmp11_7.push$javax$vecmath$Quat4f();
      Transform convexFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform convexToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      convexFromTrans.set(convexFromWorld);
      convexToTrans.set(convexToWorld);
      Vector3f castShapeAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f castShapeAabbMax = localStack.get$javax$vecmath$Vector3f();
      Vector3f linVel = localStack.get$javax$vecmath$Vector3f();
      Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
      TransformUtil.calculateVelocity(convexFromTrans, convexToTrans, 1.0F, linVel, angVel);
      Transform local_R = localStack.get$com$bulletphysics$linearmath$Transform();
      local_R.setIdentity();
      local_R.setRotation(convexFromTrans.getRotation(localStack.get$javax$vecmath$Quat4f()));
      castShape.calculateTemporalAabb(local_R, linVel, angVel, 1.0F, castShapeAabbMin, castShapeAabbMax);
      Transform linVel = localStack.get$com$bulletphysics$linearmath$Transform();
      Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
      Vector3f local_R = localStack.get$javax$vecmath$Vector3f();
      float[] hitLambda = new float[1];
      for (int local_i = 0; local_i < this.collisionObjects.size(); local_i++)
      {
        CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(local_i);
        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
        {
          collisionObject.getWorldTransform(linVel);
          collisionObject.getCollisionShape().getAabb(linVel, angVel, local_R);
          AabbUtil2.aabbExpand(angVel, local_R, castShapeAabbMin, castShapeAabbMax);
          hitLambda[0] = 1.0F;
          Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
          if (AabbUtil2.rayAabb(convexFromWorld.origin, convexToWorld.origin, angVel, local_R, hitLambda, hitNormal)) {
            objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, collisionObject.getCollisionShape(), linVel, resultCallback, getDispatchInfo().allowedCcdPenetration);
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp309_307 = localStack;
      tmp309_307.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp313_309 = tmp309_307;
      tmp313_309.pop$javax$vecmath$Vector3f();
      tmp313_309.pop$javax$vecmath$Quat4f();
    }
  }
  
  public ObjectArrayList<CollisionObject> getCollisionObjectArray()
  {
    return this.collisionObjects;
  }
  
  private static class BridgeTriangleRaycastCallback
    extends TriangleRaycastCallback
  {
    public CollisionWorld.RayResultCallback resultCallback;
    public CollisionObject collisionObject;
    public ConcaveShape triangleMesh;
    
    public BridgeTriangleRaycastCallback(Vector3f from, Vector3f local_to, CollisionWorld.RayResultCallback resultCallback, CollisionObject collisionObject, ConcaveShape triangleMesh)
    {
      super(local_to);
      this.resultCallback = resultCallback;
      this.collisionObject = collisionObject;
      this.triangleMesh = triangleMesh;
    }
    
    public float reportHit(Vector3f hitNormalLocal, float hitFraction, int partId, int triangleIndex)
    {
      CollisionWorld.LocalShapeInfo shapeInfo = new CollisionWorld.LocalShapeInfo();
      shapeInfo.shapePart = partId;
      shapeInfo.triangleIndex = triangleIndex;
      CollisionWorld.LocalRayResult rayResult = new CollisionWorld.LocalRayResult(this.collisionObject, shapeInfo, hitNormalLocal, hitFraction);
      boolean normalInWorldSpace = false;
      return this.resultCallback.addSingleResult(rayResult, normalInWorldSpace);
    }
  }
  
  public static class ClosestConvexResultCallback
    extends CollisionWorld.ConvexResultCallback
  {
    public final Vector3f convexFromWorld = new Vector3f();
    public final Vector3f convexToWorld = new Vector3f();
    public final Vector3f hitNormalWorld = new Vector3f();
    public final Vector3f hitPointWorld = new Vector3f();
    public CollisionObject hitCollisionObject;
    
    public ClosestConvexResultCallback(Vector3f convexFromWorld, Vector3f convexToWorld)
    {
      this.convexFromWorld.set(convexFromWorld);
      this.convexToWorld.set(convexToWorld);
      this.hitCollisionObject = null;
    }
    
    public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$com$bulletphysics$linearmath$Transform();
        assert (convexResult.hitFraction <= this.closestHitFraction);
        this.closestHitFraction = convexResult.hitFraction;
        this.hitCollisionObject = convexResult.hitCollisionObject;
        if (normalInWorldSpace)
        {
          this.hitNormalWorld.set(convexResult.hitNormalLocal);
          if (this.hitNormalWorld.length() > 2.0F) {
            System.out.println("CollisionWorld.addSingleResult world " + this.hitNormalWorld);
          }
        }
        else
        {
          this.hitNormalWorld.set(convexResult.hitNormalLocal);
          this.hitCollisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(this.hitNormalWorld);
          if (this.hitNormalWorld.length() > 2.0F) {
            System.out.println("CollisionWorld.addSingleResult world " + this.hitNormalWorld);
          }
        }
        this.hitPointWorld.set(convexResult.hitPointLocal);
        return convexResult.hitFraction;
      }
      finally
      {
        localStack.pop$com$bulletphysics$linearmath$Transform();
      }
    }
  }
  
  public static abstract class ConvexResultCallback
  {
    public float closestHitFraction = 1.0F;
    public short collisionFilterGroup = 1;
    public short collisionFilterMask = -1;
    
    public boolean hasHit()
    {
      return this.closestHitFraction < 1.0F;
    }
    
    public boolean needsCollision(BroadphaseProxy proxy0)
    {
      boolean collides = (proxy0.collisionFilterGroup & this.collisionFilterMask & 0xFFFF) != 0;
      collides = (collides) && ((this.collisionFilterGroup & proxy0.collisionFilterMask & 0xFFFF) != 0);
      return collides;
    }
    
    public abstract float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean);
  }
  
  public static class LocalConvexResult
  {
    public CollisionObject hitCollisionObject;
    public CollisionWorld.LocalShapeInfo localShapeInfo;
    public final Vector3f hitNormalLocal = new Vector3f();
    public final Vector3f hitPointLocal = new Vector3f();
    public float hitFraction;
    
    public LocalConvexResult(CollisionObject hitCollisionObject, CollisionWorld.LocalShapeInfo localShapeInfo, Vector3f hitNormalLocal, Vector3f hitPointLocal, float hitFraction)
    {
      this.hitCollisionObject = hitCollisionObject;
      this.localShapeInfo = localShapeInfo;
      this.hitNormalLocal.set(hitNormalLocal);
      this.hitPointLocal.set(hitPointLocal);
      this.hitFraction = hitFraction;
    }
  }
  
  public static class ClosestRayResultCallback
    extends CollisionWorld.RayResultCallback
  {
    public final Vector3f rayFromWorld = new Vector3f();
    public final Vector3f rayToWorld = new Vector3f();
    public final Vector3f hitNormalWorld = new Vector3f();
    public final Vector3f hitPointWorld = new Vector3f();
    
    public ClosestRayResultCallback(Vector3f rayFromWorld, Vector3f rayToWorld)
    {
      this.rayFromWorld.set(rayFromWorld);
      this.rayToWorld.set(rayToWorld);
    }
    
    public float addSingleResult(CollisionWorld.LocalRayResult arg1, boolean arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        localStack.push$com$bulletphysics$linearmath$Transform();
        assert (rayResult.hitFraction <= this.closestHitFraction);
        this.closestHitFraction = rayResult.hitFraction;
        this.collisionObject = rayResult.collisionObject;
        if (normalInWorldSpace)
        {
          this.hitNormalWorld.set(rayResult.hitNormalLocal);
        }
        else
        {
          this.hitNormalWorld.set(rayResult.hitNormalLocal);
          this.collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(this.hitNormalWorld);
        }
        VectorUtil.setInterpolate3(this.hitPointWorld, this.rayFromWorld, this.rayToWorld, rayResult.hitFraction);
        return rayResult.hitFraction;
      }
      finally
      {
        localStack.pop$com$bulletphysics$linearmath$Transform();
      }
    }
  }
  
  public static abstract class RayResultCallback
  {
    public float closestHitFraction = 1.0F;
    public CollisionObject collisionObject;
    public short collisionFilterGroup = 1;
    public short collisionFilterMask = -1;
    
    public boolean hasHit()
    {
      return this.collisionObject != null;
    }
    
    public boolean needsCollision(BroadphaseProxy proxy0)
    {
      boolean collides = (proxy0.collisionFilterGroup & this.collisionFilterMask & 0xFFFF) != 0;
      collides = (collides) && ((this.collisionFilterGroup & proxy0.collisionFilterMask & 0xFFFF) != 0);
      return collides;
    }
    
    public abstract float addSingleResult(CollisionWorld.LocalRayResult paramLocalRayResult, boolean paramBoolean);
  }
  
  public static class LocalRayResult
  {
    public CollisionObject collisionObject;
    public CollisionWorld.LocalShapeInfo localShapeInfo;
    public final Vector3f hitNormalLocal = new Vector3f();
    public float hitFraction;
    
    public LocalRayResult(CollisionObject collisionObject, CollisionWorld.LocalShapeInfo localShapeInfo, Vector3f hitNormalLocal, float hitFraction)
    {
      this.collisionObject = collisionObject;
      this.localShapeInfo = localShapeInfo;
      this.hitNormalLocal.set(hitNormalLocal);
      this.hitFraction = hitFraction;
    }
  }
  
  public static class LocalShapeInfo
  {
    public int shapePart;
    public int triangleIndex;
  }
  
  private static class BridgeTriangleConvexcastCallback
    extends TriangleConvexcastCallback
  {
    public CollisionWorld.ConvexResultCallback resultCallback;
    public CollisionObject collisionObject;
    public TriangleMeshShape triangleMesh;
    public boolean normalInWorldSpace;
    
    public BridgeTriangleConvexcastCallback(ConvexShape castShape, Transform from, Transform local_to, CollisionWorld.ConvexResultCallback resultCallback, CollisionObject collisionObject, TriangleMeshShape triangleMesh, Transform triangleToWorld)
    {
      super(from, local_to, triangleToWorld, triangleMesh.getMargin());
      this.resultCallback = resultCallback;
      this.collisionObject = collisionObject;
      this.triangleMesh = triangleMesh;
    }
    
    public float reportHit(Vector3f hitNormalLocal, Vector3f hitPointLocal, float hitFraction, int partId, int triangleIndex)
    {
      CollisionWorld.LocalShapeInfo shapeInfo = new CollisionWorld.LocalShapeInfo();
      shapeInfo.shapePart = partId;
      shapeInfo.triangleIndex = triangleIndex;
      if (hitFraction <= this.resultCallback.closestHitFraction)
      {
        CollisionWorld.LocalConvexResult convexResult = new CollisionWorld.LocalConvexResult(this.collisionObject, shapeInfo, hitNormalLocal, hitPointLocal, hitFraction);
        return this.resultCallback.addSingleResult(convexResult, this.normalInWorldSpace);
      }
      return hitFraction;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionWorld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */