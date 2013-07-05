/*      */ package org.schema.game.common.data.physics;
/*      */ 
/*      */ import com.bulletphysics.BulletGlobals;
/*      */ import com.bulletphysics.BulletStats;
/*      */ import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*      */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*      */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*      */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*      */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*      */ import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
/*      */ import com.bulletphysics.collision.dispatch.SimulationIslandManager;
/*      */ import com.bulletphysics.collision.narrowphase.ConvexCast;
/*      */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*      */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*      */ import com.bulletphysics.collision.shapes.CollisionShape;
/*      */ import com.bulletphysics.collision.shapes.CompoundShape;
/*      */ import com.bulletphysics.collision.shapes.ConvexShape;
/*      */ import com.bulletphysics.collision.shapes.SphereShape;
/*      */ import com.bulletphysics.dynamics.ActionInterface;
/*      */ import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
/*      */ import com.bulletphysics.dynamics.RigidBody;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*      */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*      */ import com.bulletphysics.dynamics.vehicle.RaycastVehicle;
/*      */ import com.bulletphysics.linearmath.AabbUtil2;
/*      */ import com.bulletphysics.linearmath.IDebugDraw;
/*      */ import com.bulletphysics.linearmath.MiscUtil;
/*      */ import com.bulletphysics.linearmath.MotionState;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import d;
/*      */ import jL;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Comparator;
/*      */ import javax.vecmath.Quat4f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import org.schema.common.util.linAlg.TransformTools;
/*      */ import org.schema.game.common.data.world.Segment;
/*      */ import xO;
/*      */ import zS;
/*      */ 
/*      */ public class ModifiedDynamicsWorld extends DiscreteDynamicsWorld
/*      */ {
/*   80 */   private final Vector3f minAabb = new Vector3f();
/*   81 */   private final Vector3f maxAabb = new Vector3f();
/*   82 */   private final Vector3f tmpAABBSingle = new Vector3f();
/*   83 */   private final Transform tmpTransAABBSingle = new Transform();
/*   84 */   private final Vector3f contactThreshold = new Vector3f();
/*      */ 
/*  142 */   private final Transform interpolatedTransform = new Transform();
/*      */ 
/*  144 */   private final Transform tmpTrans2 = new Transform();
/*  145 */   private final Vector3f tmpLinVel = new Vector3f();
/*  146 */   private final Vector3f tmpAngVel = new Vector3f();
/*      */ 
/*  148 */   public final Vector3f iAxis = new Vector3f();
/*  149 */   public final Quat4f iDorn = new Quat4f();
/*  150 */   public final Quat4f iorn0 = new Quat4f();
/*  151 */   public final Quat4f iPredictOrn = new Quat4f();
/*  152 */   public final float[] float4Temp = new float[4];
/*      */ 
/*  308 */   Transform tmpTrans = new Transform();
/*  309 */   Transform rayFromTrans = new Transform();
/*  310 */   Transform rayToTrans = new Transform();
/*  311 */   Vector3f collisionObjectAabbMin = new Vector3f();
/*  312 */   Vector3f collisionObjectAabbMax = new Vector3f();
/*      */ 
/*  314 */   Vector3f hitNormal = new Vector3f();
/*      */ 
/*  333 */   Transform convexFromTrans = new Transform();
/*  334 */   Transform convexToTrans = new Transform();
/*  335 */   Vector3f castShapeAabbMin = new Vector3f();
/*  336 */   Vector3f castShapeAabbMax = new Vector3f();
/*  337 */   Vector3f linVel = new Vector3f();
/*  338 */   Vector3f angVel = new Vector3f();
/*  339 */   Transform R = new Transform();
/*  340 */   Quat4f quat = new Quat4f();
/*      */   private zS state;
/*  458 */   private com.bulletphysics.util.ObjectArrayList sortedConstraints = new com.bulletphysics.util.ObjectArrayList();
/*  459 */   private ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt solverCallback = new ModifiedDynamicsWorld.InplaceSolverIslandCallbackExt(null);
/*      */ 
/*  486 */   private static final Comparator sortConstraintOnIslandPredicate = new ModifiedDynamicsWorld.1();
/*      */ 
/*  519 */   private final Transform childWorldTrans = new Transform();
/*  520 */   Transform childTrans = new Transform();
/*      */ 
/*  587 */   float[] hitLambda = new float[1];
/*      */ 
/*  613 */   Vector3f dir = new Vector3f();
/*  614 */   Vector3f closest = new Vector3f();
/*  615 */   Vector3f closestDir = new Vector3f();
/*      */ 
/*  748 */   public it.unimi.dsi.fastutil.objects.ObjectArrayList cache = new it.unimi.dsi.fastutil.objects.ObjectArrayList();
/*  749 */   public boolean cacheValid = false;
/*      */ 
/*  769 */   static Vector3f aabbHalfExtent = new Vector3f();
/*  770 */   static Vector3f aabbCenter = new Vector3f();
/*  771 */   static Vector3f source = new Vector3f();
/*  772 */   static Vector3f target = new Vector3f();
/*  773 */   static Vector3f r = new Vector3f();
/*  774 */   static Vector3f hitNormalTmp = new Vector3f();
/*      */ 
/*      */   private static void handleCompound(ConvexShape paramConvexShape, CompoundShape paramCompoundShape, CollisionObject paramCollisionObject, Transform paramTransform1, float paramFloat, Transform paramTransform2, Transform paramTransform3, CollisionWorld.ConvexResultCallback paramConvexResultCallback)
/*      */   {
/*   60 */     for (int i = 0; i < paramCompoundShape.getNumChildShapes(); i++) {
/*   61 */       Object localObject = paramCompoundShape.getChildTransform(i, new Transform());
/*   62 */       CollisionShape localCollisionShape = paramCompoundShape.getChildShape(i);
/*      */       Transform localTransform;
/*   63 */       (
/*   64 */         localTransform = new Transform())
/*   64 */         .mul(paramTransform1, (Transform)localObject);
/*      */ 
/*   66 */       localObject = paramCollisionObject.getCollisionShape();
/*   67 */       paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape);
/*      */ 
/*   70 */       objectQuerySingle(paramConvexShape, paramTransform2, paramTransform3, paramCollisionObject, localCollisionShape, localTransform, paramConvexResultCallback, paramFloat);
/*      */ 
/*   73 */       paramCollisionObject.internalSetTemporaryCollisionShape((CollisionShape)localObject);
/*      */     }
/*      */   }
/*      */ 
/*   77 */   public final OverlappingPairCache getPairCache() { return this.broadphasePairCache.getOverlappingPairCache(); }
/*      */ 
/*      */ 
/*      */   public void updateSingleAabb(CollisionObject paramCollisionObject)
/*      */   {
/*   88 */     paramCollisionObject.getCollisionShape().getAabb(paramCollisionObject.getWorldTransform(this.tmpTransAABBSingle), this.minAabb, this.maxAabb);
/*      */ 
/*   90 */     this.contactThreshold.set(BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold());
/*   91 */     this.minAabb.sub(this.contactThreshold);
/*   92 */     this.maxAabb.add(this.contactThreshold);
/*      */ 
/*   94 */     BroadphaseInterface localBroadphaseInterface = this.broadphasePairCache;
/*      */ 
/*   97 */     this.tmpAABBSingle.sub(this.maxAabb, this.minAabb);
/*   98 */     if ((paramCollisionObject.isStaticObject()) || (this.tmpAABBSingle.lengthSquared() < 1.0E+012F)) {
/*   99 */       localBroadphaseInterface.setAabb(paramCollisionObject.getBroadphaseHandle(), this.minAabb, this.maxAabb, this.dispatcher1); return;
/*      */     }
/*      */ 
/*  104 */     paramCollisionObject.setActivationState(5);
/*  105 */     System.err.println("Exception!!! Overflow in AABB, object removed from simulation " + paramCollisionObject);
/*  106 */     System.err.println("If you can reproduce this, please email bugs@continuousphysics.com\n");
/*  107 */     System.err.println("Please include above information, your Platform, version of OS.\n");
/*  108 */     System.err.println("Thanks.\n");
/*      */   }
/*      */ 
/*      */   public void updateAabbs()
/*      */   {
/*  119 */     BulletStats.pushProfile("updateAabbs");
/*      */     try {
/*  121 */       for (int i = 0; i < this.collisionObjects.size(); i++)
/*      */       {
/*      */         CollisionObject localCollisionObject;
/*  125 */         if ((
/*  125 */           localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i))
/*  125 */           .isActive())
/*      */         {
/*  129 */           updateSingleAabb(localCollisionObject);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     finally
/*      */     {
/*  139 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void synchronizeMotionStates()
/*      */   {
/*  157 */     for (int i = 0; i < this.collisionObjects.size(); i++)
/*      */     {
/*      */       RigidBody localRigidBody;
/*  160 */       if (((
/*  160 */         localRigidBody = RigidBody.upcast((CollisionObject)this.collisionObjects.getQuick(i))) != null) && 
/*  160 */         (localRigidBody.getMotionState() != null) && (!localRigidBody.isStaticOrKinematicObject()))
/*      */       {
/*  166 */         TransformTools.a(localRigidBody.getInterpolationWorldTransform(this.tmpTrans2), localRigidBody.getInterpolationLinearVelocity(this.tmpLinVel), localRigidBody.getInterpolationAngularVelocity(this.tmpAngVel), this.localTime * localRigidBody.getHitFraction(), this.interpolatedTransform, this.iAxis, this.iDorn, this.iorn0, this.iPredictOrn, this.float4Temp);
/*      */ 
/*  172 */         localRigidBody.getMotionState().setWorldTransform(this.interpolatedTransform);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  177 */     if ((getDebugDrawer() != null) && ((getDebugDrawer().getDebugMode() & 0x1) != 0))
/*  178 */       for (i = 0; i < this.vehicles.size(); i++)
/*  179 */         for (int j = 0; j < ((RaycastVehicle)this.vehicles.getQuick(i)).getNumWheels(); j++)
/*      */         {
/*  181 */           ((RaycastVehicle)this.vehicles.getQuick(i)).updateWheelTransform(j, true);
/*      */         }
/*      */   }
/*      */ 
/*      */   public void performDiscreteCollisionDetection()
/*      */   {
/*  188 */     BulletStats.pushProfile("performDiscreteCollisionDetection");
/*      */     try {
/*  192 */       updateAabbs();
/*      */ 
/*  194 */       BulletStats.pushProfile("calculateOverlappingPairs");
/*      */       Dispatcher localDispatcher;
/*      */       try { this.broadphasePairCache.calculateOverlappingPairs(this.dispatcher1); }
/*      */       finally
/*      */       {
/*      */       }
/*      */ 
/*  217 */       BulletStats.pushProfile("dispatchAllCollisionPairs");
/*      */       try {
/*  219 */         if (localDispatcher != null) {
/*  220 */           localDispatcher.dispatchAllCollisionPairs(this.broadphasePairCache.getOverlappingPairCache(), this.dispatchInfo, this.dispatcher1);
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  229 */       BulletStats.popProfile();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void objectQuerySingle(ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, CollisionObject paramCollisionObject, CollisionShape paramCollisionShape, Transform paramTransform3, CollisionWorld.ConvexResultCallback paramConvexResultCallback, float paramFloat)
/*      */   {
/*  285 */     if ((paramCollisionShape instanceof CubeShape)) {
/*  286 */       (
/*  287 */         paramCollisionShape = new ConvexCast.CastResult()).allowedPenetration = 
/*  287 */         paramFloat;
/*  288 */       paramCollisionShape.fraction = 1.0F;
/*      */ 
/*  290 */       paramFloat = new VoronoiSimplexSolverExt();
/*      */ 
/*  297 */       new SubsimplexCubesCovexCast(paramConvexShape, paramCollisionObject.getCollisionShape(), paramCollisionObject, paramFloat, paramConvexResultCallback, null)
/*  300 */         .calcTimeOfImpact(paramTransform1, paramTransform2, paramTransform3, paramTransform3, paramCollisionShape);
/*  301 */       return; } if (paramCollisionShape.isCompound()) {
/*  302 */       handleCompound(paramConvexShape, (CompoundShape)paramCollisionShape, paramCollisionObject, paramTransform3, paramFloat, paramTransform1, paramTransform2, paramConvexResultCallback); return;
/*      */     }
/*  304 */     CollisionWorld.objectQuerySingle(paramConvexShape, paramTransform1, paramTransform2, paramCollisionObject, paramCollisionShape, paramTransform3, paramConvexResultCallback, paramFloat);
/*      */   }
/*      */ 
/*      */   public ModifiedDynamicsWorld(Dispatcher paramDispatcher, BroadphaseInterface paramBroadphaseInterface, ConstraintSolver paramConstraintSolver, CollisionConfiguration paramCollisionConfiguration, zS paramzS)
/*      */   {
/*  345 */     super(paramDispatcher, paramBroadphaseInterface, paramConstraintSolver, paramCollisionConfiguration);
/*  346 */     this.state = paramzS;
/*  347 */     this.islandManager = new SimulationIslandManagerExt();
/*      */   }
/*      */ 
/*      */   public void removeCollisionObject(CollisionObject paramCollisionObject)
/*      */   {
/*      */     BroadphaseProxy localBroadphaseProxy;
/*  355 */     if ((
/*  355 */       localBroadphaseProxy = paramCollisionObject.getBroadphaseHandle()) != null)
/*      */     {
/*  361 */       getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(localBroadphaseProxy, this.dispatcher1);
/*  362 */       getBroadphase().destroyProxy(localBroadphaseProxy, this.dispatcher1);
/*  363 */       paramCollisionObject.setBroadphaseHandle(null);
/*      */ 
/*  365 */       com.bulletphysics.util.ObjectArrayList localObjectArrayList = this.dispatcher1.getInternalManifoldPointer();
/*  366 */       for (int i = 0; i < localObjectArrayList.size(); i++)
/*      */       {
/*      */         PersistentManifold localPersistentManifold;
/*  368 */         if (((
/*  368 */           localPersistentManifold = (PersistentManifold)localObjectArrayList.get(i))
/*  368 */           .getBody0() == paramCollisionObject) || (localPersistentManifold.getBody1() == paramCollisionObject)) {
/*  369 */           this.dispatcher1.releaseManifold(localPersistentManifold);
/*      */ 
/*  372 */           i = 0;
/*      */         }
/*      */       }
/*      */ 
/*  376 */       assert (checkProdyDestroyed(localBroadphaseProxy));
/*      */     }
/*      */ 
/*  381 */     this.collisionObjects.remove(paramCollisionObject);
/*      */   }
/*      */ 
/*      */   public boolean checkProdyDestroyed(BroadphaseProxy paramBroadphaseProxy) {
/*  385 */     com.bulletphysics.util.ObjectArrayList localObjectArrayList = getBroadphase().getOverlappingPairCache().getOverlappingPairArray();
/*  386 */     for (int i = 0; i < localObjectArrayList.size(); i++)
/*      */     {
/*      */       BroadphasePair localBroadphasePair;
/*  388 */       if (((
/*  388 */         localBroadphasePair = (BroadphasePair)localObjectArrayList.getQuick(i)).pProxy0 == 
/*  388 */         paramBroadphaseProxy.clientObject) || (localBroadphasePair.pProxy1 == paramBroadphaseProxy.clientObject))
/*      */       {
/*  390 */         System.err.println("Exception: Proxy Has NOT been destroyed completely: " + paramBroadphaseProxy.clientObject);
/*  391 */         return false;
/*      */       }
/*      */     }
/*  394 */     return true;
/*      */   }
/*      */ 
/*      */   protected void solveConstraints(ContactSolverInfo paramContactSolverInfo)
/*      */   {
/*  461 */     BulletStats.pushProfile("solveConstraints");
/*      */     try
/*      */     {
/*  464 */       this.sortedConstraints.clear();
/*  465 */       for (int i = 0; i < this.constraints.size(); i++) {
/*  466 */         this.sortedConstraints.add(this.constraints.getQuick(i)); } 
/*      */ MiscUtil.quickSort(this.sortedConstraints, sortConstraintOnIslandPredicate);
/*      */ 
/*  471 */       com.bulletphysics.util.ObjectArrayList localObjectArrayList = getNumConstraints() != 0 ? this.sortedConstraints : null;
/*      */ 
/*  473 */       this.solverCallback.init(paramContactSolverInfo, this.constraintSolver, localObjectArrayList, this.sortedConstraints.size(), this.debugDrawer, this.dispatcher1, this.state);
/*      */ 
/*  475 */       this.constraintSolver.prepareSolve(getCollisionWorld().getNumCollisionObjects(), getCollisionWorld().getDispatcher().getNumManifolds());
/*      */ 
/*  478 */       this.islandManager.buildAndProcessIslands(getCollisionWorld().getDispatcher(), getCollisionWorld().getCollisionObjectArray(), this.solverCallback);
/*      */ 
/*  480 */       this.constraintSolver.allSolved(paramContactSolverInfo, this.debugDrawer);
/*      */       return; } finally { BulletStats.popProfile(); } throw paramContactSolverInfo;
/*      */   }
/*      */ 
/*      */   private static int getConstraintIslandId(TypedConstraint paramTypedConstraint)
/*      */   {
/*  497 */     RigidBody localRigidBody = paramTypedConstraint.getRigidBodyA();
/*  498 */     paramTypedConstraint = paramTypedConstraint.getRigidBodyB();
/*  499 */     if (localRigidBody.getIslandTag() >= 0) return localRigidBody.getIslandTag();
/*  500 */     return paramTypedConstraint.getIslandTag();
/*      */   }
/*      */ 
/*      */   private void handleCompoundRayTest(CompoundShape paramCompoundShape, CollisionObject paramCollisionObject, Vector3f paramVector3f1, Vector3f paramVector3f2, CollisionWorld.RayResultCallback paramRayResultCallback)
/*      */   {
/*  523 */     for (int i = 0; i < paramCompoundShape.getNumChildShapes(); i++) {
/*  524 */       paramCompoundShape.getChildTransform(i, this.childTrans);
/*  525 */       CollisionShape localCollisionShape1 = paramCompoundShape.getChildShape(i);
/*  526 */       this.childWorldTrans.set(paramCollisionObject.getWorldTransform(this.tmpTrans));
/*      */ 
/*  528 */       d.a(this.childWorldTrans, this.childTrans);
/*      */ 
/*  530 */       CollisionShape localCollisionShape2 = paramCollisionObject.getCollisionShape();
/*      */ 
/*  534 */       if (((localCollisionShape1 instanceof CubeShape)) && ((paramRayResultCallback == null) || ((paramRayResultCallback instanceof CubeRayCastResult))))
/*      */       {
/*  536 */         CubeRayCastResult localCubeRayCastResult = (CubeRayCastResult)paramRayResultCallback;
/*      */ 
/*  544 */         CubeShape localCubeShape = (CubeShape)localCollisionShape1;
/*      */ 
/*  548 */         if ((localCubeRayCastResult.getOwner() == null) || (localCubeRayCastResult.getOwner() != localCubeShape.getSegmentBuffer().a()))
/*      */         {
/*  550 */           paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/*      */ 
/*  554 */           rayTestSingleCubeMesh(this.rayFromTrans, this.rayToTrans, paramCollisionObject, localCollisionShape1, this.childWorldTrans, localCubeRayCastResult, localCubeRayCastResult.getLastHitSegment());
/*      */ 
/*  559 */           paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/*      */         }
/*      */       }
/*  562 */       else if ((localCollisionShape1 instanceof CompoundShape)) {
/*  563 */         handleCompoundRayTest((CompoundShape)localCollisionShape1, paramCollisionObject, paramVector3f1, paramVector3f2, paramRayResultCallback);
/*      */       } else {
/*  565 */         paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape1);
/*  566 */         if (!$assertionsDisabled) throw new AssertionError(localCollisionShape1 + ": " + localCollisionShape1.getClass().getSimpleName() + "; " + (paramRayResultCallback != null ? paramRayResultCallback.getClass().getSimpleName() : "null-ResultCallback"));
/*      */ 
/*  568 */         rayTestSingle(this.rayFromTrans, this.rayToTrans, paramCollisionObject, localCollisionShape1, this.childWorldTrans, paramRayResultCallback);
/*      */ 
/*  573 */         paramCollisionObject.internalSetTemporaryCollisionShape(localCollisionShape2);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void rayTest(Vector3f paramVector3f1, Vector3f paramVector3f2, CollisionWorld.RayResultCallback paramRayResultCallback)
/*      */   {
/*  592 */     this.rayFromTrans.setIdentity();
/*  593 */     this.rayFromTrans.origin.set(paramVector3f1);
/*      */ 
/*  595 */     this.rayToTrans.setIdentity();
/*  596 */     this.rayToTrans.origin.set(paramVector3f2);
/*      */ 
/*  599 */     this.hitLambda[0] = 0.0F;
/*      */ 
/*  601 */     Object localObject = (paramRayResultCallback != null) && ((paramRayResultCallback instanceof CubeRayCastResult)) && (((CubeRayCastResult)paramRayResultCallback).getOwner() != null) ? ((CubeRayCastResult)paramRayResultCallback).getOwner() : null;
/*      */ 
/*  605 */     if (this.cacheValid)
/*      */     {
/*  607 */       doRayTestCached(localObject, paramRayResultCallback, paramVector3f1, paramVector3f2); return;
/*      */     }
/*  609 */     doRayTest(localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*      */   }
/*      */ 
/*      */   public void doRayTestCached(Object paramObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*      */   {
/*  619 */     for (int i = 0; i < this.collisionObjects.size(); i++)
/*      */     {
/*  621 */       if (paramRayResultCallback.closestHitFraction == 0.0F) break;
/*  622 */       Object localObject = (xO)this.cache.get(i);
/*      */ 
/*  626 */       this.dir.sub(paramVector3f2, paramVector3f1);
/*  627 */       ((xO)localObject).a(paramVector3f1, this.closest);
/*  628 */       this.closestDir.sub(this.closest, paramVector3f1);
/*  629 */       if (this.dir.lengthSquared() >= this.closestDir.lengthSquared())
/*      */       {
/*  640 */         this.collisionObjectAabbMin.set(((xO)localObject).a);
/*      */ 
/*  644 */         this.collisionObjectAabbMax.set(((xO)localObject).b);
/*      */ 
/*  646 */         localObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*      */ 
/*  648 */         if (paramObject != localObject) {
/*  649 */           BroadphaseProxy localBroadphaseProxy = ((CollisionObject)localObject).getBroadphaseHandle();
/*      */           try
/*      */           {
/*  655 */             if ((localBroadphaseProxy != null) && (paramRayResultCallback.needsCollision(localBroadphaseProxy)))
/*      */             {
/*  657 */               doRayTestNext(localBroadphaseProxy, (CollisionObject)localObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*      */             }
/*      */           }
/*      */           catch (Exception localException) {
/*  661 */             localException.printStackTrace();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  666 */   public void doRayTest(Object paramObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2) { for (int i = 0; i < this.collisionObjects.size(); i++)
/*      */     {
/*  668 */       if (paramRayResultCallback.closestHitFraction == 0.0F) break;
/*  669 */       CollisionObject localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*      */ 
/*  681 */       if (paramObject != localCollisionObject) {
/*  682 */         BroadphaseProxy localBroadphaseProxy = localCollisionObject.getBroadphaseHandle();
/*      */         try
/*      */         {
/*  688 */           if ((localBroadphaseProxy != null) && (paramRayResultCallback.needsCollision(localBroadphaseProxy)))
/*      */           {
/*  690 */             localCollisionObject.getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/*      */ 
/*  692 */             doRayTestNext(localBroadphaseProxy, localCollisionObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*      */           }
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/*  697 */           localException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void doRayTestNext(BroadphaseProxy paramBroadphaseProxy, CollisionObject paramCollisionObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*      */   {
/*  705 */     this.hitLambda[0] = paramRayResultCallback.closestHitFraction;
/*  706 */     if (rayAabb(paramVector3f1, paramVector3f2, this.collisionObjectAabbMin, this.collisionObjectAabbMax, this.hitLambda, this.hitNormal))
/*  707 */       doInnerRayTest(paramCollisionObject, paramRayResultCallback, paramVector3f1, paramVector3f2);
/*      */   }
/*      */ 
/*      */   private void doInnerRayTest(CollisionObject paramCollisionObject, CollisionWorld.RayResultCallback paramRayResultCallback, Vector3f paramVector3f1, Vector3f paramVector3f2)
/*      */   {
/*      */     Object localObject;
/*  715 */     if (paramCollisionObject.getCollisionShape().isCompound()) {
/*  716 */       localObject = (CompoundShape)paramCollisionObject.getCollisionShape();
/*  717 */       handleCompoundRayTest((CompoundShape)localObject, paramCollisionObject, paramVector3f1, paramVector3f2, paramRayResultCallback);
/*      */ 
/*  719 */       return; } if (((paramCollisionObject.getCollisionShape() instanceof CubeShape)) && ((paramRayResultCallback == null) || ((paramRayResultCallback instanceof CubeRayCastResult)))) {
/*  720 */       localObject = (CubeRayCastResult)paramRayResultCallback;
/*  721 */       paramRayResultCallback = (CubeShape)paramCollisionObject.getCollisionShape();
/*      */ 
/*  725 */       if ((((CubeRayCastResult)localObject).getOwner() != null) && (((CubeRayCastResult)localObject).getOwner() == paramRayResultCallback.getSegmentBuffer().a()))
/*      */       {
/*  727 */         return;
/*      */       }
/*      */ 
/*  730 */       rayTestSingleCubeMesh(this.rayFromTrans, this.rayToTrans, paramCollisionObject, paramRayResultCallback, paramCollisionObject.getWorldTransform(this.tmpTrans), (CubeRayCastResult)localObject, ((CubeRayCastResult)localObject).getLastHitSegment());
/*      */ 
/*  735 */       return;
/*  736 */     }if ((!(paramRayResultCallback instanceof CubeRayCastResult)) || (!((CubeRayCastResult)paramRayResultCallback).onlyCubeMeshes))
/*      */     {
/*  740 */       rayTestSingle(this.rayFromTrans, this.rayToTrans, paramCollisionObject, paramCollisionObject.getCollisionShape(), paramCollisionObject.getWorldTransform(this.tmpTrans), paramRayResultCallback);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void buildCache()
/*      */   {
/*  752 */     for (int i = 0; i < this.collisionObjects.size(); i++)
/*      */     {
/*      */       CollisionObject localCollisionObject;
/*  753 */       (
/*  754 */         localCollisionObject = (CollisionObject)this.collisionObjects.getQuick(i))
/*  754 */         .getCollisionShape().getAabb(localCollisionObject.getWorldTransform(this.tmpTrans), this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/*      */ 
/*  756 */       if (!this.cacheValid) {
/*  757 */         if (this.cache.size() <= i) {
/*  758 */           this.cache.add(new xO());
/*      */         }
/*  760 */         ((xO)this.cache.get(i)).b(this.collisionObjectAabbMin, this.collisionObjectAabbMax);
/*      */       }
/*      */     }
/*  763 */     while (this.cache.size() > this.collisionObjects.size()) {
/*  764 */       this.cache.remove(this.cache.size() - 1);
/*      */     }
/*  766 */     this.cacheValid = true;
/*      */   }
/*      */ 
/*      */   public boolean rayAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, float[] paramArrayOfFloat, Vector3f paramVector3f5)
/*      */   {
/*  779 */     aabbHalfExtent.sub(paramVector3f4, paramVector3f3);
/*  780 */     aabbHalfExtent.scale(0.5F);
/*      */ 
/*  782 */     aabbCenter.add(paramVector3f4, paramVector3f3);
/*  783 */     aabbCenter.scale(0.5F);
/*      */ 
/*  785 */     source.sub(paramVector3f1, aabbCenter);
/*  786 */     target.sub(paramVector3f2, aabbCenter);
/*      */ 
/*  788 */     paramVector3f1 = AabbUtil2.outcode(source, aabbHalfExtent);
/*  789 */     paramVector3f2 = AabbUtil2.outcode(target, aabbHalfExtent);
/*  790 */     if ((paramVector3f1 & paramVector3f2) == 0) {
/*  791 */       paramVector3f3 = 0.0F;
/*  792 */       paramVector3f4 = paramArrayOfFloat[0];
/*  793 */       r.sub(target, source);
/*      */ 
/*  795 */       hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*      */       float f;
/*  797 */       if ((paramVector3f1 & 0x1) != 0)
/*      */       {
/*  800 */         f = (-source.x - aabbHalfExtent.x) / r.x;
/*  801 */         if (0.0F <= f) {
/*  802 */           paramVector3f3 = f;
/*  803 */           hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  804 */           hitNormalTmp.x = 1.0F;
/*      */         }
/*      */       }
/*  807 */       else if ((paramVector3f2 & 0x1) != 0) {
/*  808 */         f = (-source.x - aabbHalfExtent.x) / r.x;
/*      */ 
/*  810 */         paramVector3f4 = Math.min(paramVector3f4, f);
/*      */       }
/*  812 */       if ((paramVector3f1 & 0x2) != 0)
/*      */       {
/*  816 */         f = (-source.y - aabbHalfExtent.y) / r.y;
/*  817 */         if (paramVector3f3 <= f) {
/*  818 */           paramVector3f3 = f;
/*  819 */           hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  820 */           hitNormalTmp.y = 1.0F;
/*      */         }
/*      */       }
/*  823 */       else if ((paramVector3f2 & 0x2) != 0) {
/*  824 */         f = (-source.y - aabbHalfExtent.y) / r.y;
/*      */ 
/*  826 */         paramVector3f4 = Math.min(paramVector3f4, f);
/*      */       }
/*  828 */       if ((paramVector3f1 & 0x4) != 0)
/*      */       {
/*  832 */         f = (-source.z - aabbHalfExtent.z) / r.z;
/*  833 */         if (paramVector3f3 <= f) {
/*  834 */           paramVector3f3 = f;
/*  835 */           hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  836 */           hitNormalTmp.z = 1.0F;
/*      */         }
/*      */       }
/*  839 */       else if ((paramVector3f2 & 0x4) != 0) {
/*  840 */         f = (-source.z - aabbHalfExtent.z) / r.z;
/*      */ 
/*  842 */         paramVector3f4 = Math.min(paramVector3f4, f);
/*      */       }
/*      */ 
/*  845 */       if ((paramVector3f1 & 0x8) != 0)
/*      */       {
/*  850 */         f = (-source.x - -aabbHalfExtent.x) / r.x;
/*  851 */         if (paramVector3f3 <= f) {
/*  852 */           paramVector3f3 = f;
/*  853 */           hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  854 */           hitNormalTmp.x = -1.0F;
/*      */         }
/*      */       }
/*  857 */       else if ((paramVector3f2 & 0x8) != 0) {
/*  858 */         f = (-source.x - -aabbHalfExtent.x) / r.x;
/*      */ 
/*  860 */         paramVector3f4 = Math.min(paramVector3f4, f);
/*      */       }
/*  862 */       if ((paramVector3f1 & 0x10) != 0)
/*      */       {
/*  866 */         f = (-source.y - -aabbHalfExtent.y) / r.y;
/*  867 */         if (paramVector3f3 <= f) {
/*  868 */           paramVector3f3 = f;
/*  869 */           hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  870 */           hitNormalTmp.y = -1.0F;
/*      */         }
/*      */       }
/*  873 */       else if ((paramVector3f2 & 0x10) != 0) {
/*  874 */         f = (-source.y - -aabbHalfExtent.y) / r.y;
/*      */ 
/*  876 */         paramVector3f4 = Math.min(paramVector3f4, f);
/*      */       }
/*  878 */       if ((paramVector3f1 & 0x20) != 0)
/*      */       {
/*  882 */         f = (-source.z - -aabbHalfExtent.z) / r.z;
/*  883 */         if (paramVector3f3 <= f) {
/*  884 */           paramVector3f3 = f;
/*  885 */           hitNormalTmp.set(0.0F, 0.0F, 0.0F);
/*  886 */           hitNormalTmp.z = -1.0F;
/*      */         }
/*      */       }
/*  889 */       else if ((paramVector3f2 & 0x20) != 0) {
/*  890 */         f = (-source.z - -aabbHalfExtent.z) / r.z;
/*      */ 
/*  892 */         paramVector3f4 = Math.min(paramVector3f4, f);
/*      */       }
/*  894 */       if (paramVector3f3 <= paramVector3f4)
/*      */       {
/*  898 */         paramArrayOfFloat[0] = paramVector3f3;
/*  899 */         paramVector3f5.set(hitNormalTmp);
/*  900 */         return true;
/*      */       }
/*      */     }
/*  903 */     return false;
/*      */   }
/*      */ 
/*      */   private void rayTestSingleCubeMesh(Transform paramTransform1, Transform paramTransform2, CollisionObject paramCollisionObject, CollisionShape paramCollisionShape, Transform paramTransform3, CubeRayCastResult paramCubeRayCastResult, Segment paramSegment)
/*      */   {
/*  912 */     (
/*  913 */       paramCollisionShape = new SphereShape(0.0F))
/*  913 */       .setMargin(0.0F);
/*  914 */     (
/*  917 */       paramSegment = new ConvexCast.CastResult()).fraction = 
/*  917 */       paramCubeRayCastResult.closestHitFraction;
/*      */ 
/*  919 */     VoronoiSimplexSolverExt localVoronoiSimplexSolverExt = new VoronoiSimplexSolverExt();
/*      */ 
/*  925 */     paramCollisionObject = new SubsimplexRayCubesCovexCast(paramCollisionShape, paramCollisionObject, localVoronoiSimplexSolverExt, paramCubeRayCastResult);
/*      */ 
/*  930 */     System.currentTimeMillis();
/*  931 */     paramCollisionObject.calcTimeOfImpact(paramTransform1, paramTransform2, paramTransform3, paramTransform3, paramSegment);
/*      */   }
/*      */ 
/*      */   public void clean()
/*      */   {
/* 1142 */     ArrayList localArrayList1 = new ArrayList(getCollisionObjectArray().size());
/* 1143 */     for (int i = 0; i < getCollisionObjectArray().size(); i++) {
/* 1144 */       localArrayList1.add(getCollisionObjectArray().getQuick(i));
/*      */     }
/*      */ 
/* 1147 */     ArrayList localArrayList2 = new ArrayList(getNumActions());
/* 1148 */     for (int j = 0; j < getNumActions(); j++) {
/* 1149 */       localArrayList2.add(getAction(j));
/*      */     }
/*      */ 
/* 1152 */     ArrayList localArrayList3 = new ArrayList(this.vehicles.size());
/*      */ 
/* 1154 */     for (int k = 0; k < this.vehicles.size(); k++) {
/* 1155 */       localArrayList3.add(this.vehicles.get(k));
/*      */     }
/*      */ 
/* 1158 */     ArrayList localArrayList4 = new ArrayList(this.constraints.size());
/*      */ 
/* 1160 */     for (int m = 0; m < this.constraints.size(); m++) {
/* 1161 */       localArrayList4.add(this.constraints.get(m));
/*      */     }
/*      */ 
/* 1167 */     for (m = 0; m < localArrayList4.size(); m++) {
/* 1168 */       removeConstraint((TypedConstraint)localArrayList4.get(m));
/*      */     }
/*      */ 
/* 1171 */     for (m = 0; m < localArrayList3.size(); m++) {
/* 1172 */       removeVehicle((RaycastVehicle)localArrayList3.get(m));
/*      */     }
/*      */ 
/* 1175 */     for (m = 0; m < localArrayList1.size(); m++) {
/* 1176 */       removeCollisionObject((CollisionObject)localArrayList1.get(m));
/*      */     }
/*      */ 
/* 1179 */     for (m = 0; m < localArrayList2.size(); m++)
/* 1180 */       removeAction((ActionInterface)localArrayList2.get(m));
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld
 * JD-Core Version:    0.6.2
 */