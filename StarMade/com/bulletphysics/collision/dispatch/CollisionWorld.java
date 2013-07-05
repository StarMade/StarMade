/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.BulletGlobals;
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.GjkConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.TriangleConvexcastCallback;
/*     */ import com.bulletphysics.collision.narrowphase.TriangleRaycastCallback;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ import com.bulletphysics.collision.shapes.BvhTriangleMeshShape;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.collision.shapes.ConcaveShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.collision.shapes.TriangleMeshShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class CollisionWorld
/*     */ {
/*  70 */   protected ObjectArrayList<CollisionObject> collisionObjects = new ObjectArrayList();
/*     */   protected Dispatcher dispatcher1;
/*  72 */   protected DispatcherInfo dispatchInfo = new DispatcherInfo();
/*     */   protected BroadphaseInterface broadphasePairCache;
/*     */   protected IDebugDraw debugDrawer;
/* 202 */   private static boolean updateAabbs_reportMe = true;
/*     */ 
/*     */   public CollisionWorld(Dispatcher dispatcher, BroadphaseInterface broadphasePairCache, CollisionConfiguration collisionConfiguration)
/*     */   {
/*  81 */     this.dispatcher1 = dispatcher;
/*  82 */     this.broadphasePairCache = broadphasePairCache;
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  87 */     for (int i = 0; i < this.collisionObjects.size(); i++) {
/*  88 */       CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*     */ 
/*  90 */       BroadphaseProxy bp = collisionObject.getBroadphaseHandle();
/*  91 */       if (bp != null)
/*     */       {
/*  95 */         getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(bp, this.dispatcher1);
/*  96 */         getBroadphase().destroyProxy(bp, this.dispatcher1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addCollisionObject(CollisionObject collisionObject) {
/* 102 */     addCollisionObject(collisionObject, (short)1, (short)-1);
/*     */   }
/*     */ 
/*     */   public void addCollisionObject(CollisionObject arg1, short arg2, short arg3)
/*     */   {
/* 107 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); assert (!this.collisionObjects.contains(collisionObject));
/*     */ 
/* 109 */       this.collisionObjects.add(collisionObject);
/*     */ 
/* 113 */       Transform trans = collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 115 */       Vector3f minAabb = localStack.get$javax$vecmath$Vector3f();
/* 116 */       Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/* 117 */       collisionObject.getCollisionShape().getAabb(trans, minAabb, maxAabb);
/*     */ 
/* 119 */       BroadphaseNativeType type = collisionObject.getCollisionShape().getShapeType();
/* 120 */       collisionObject.setBroadphaseHandle(getBroadphase().createProxy(minAabb, maxAabb, type, collisionObject, collisionFilterGroup, collisionFilterMask, this.dispatcher1, null));
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 128 */       .Stack tmp132_130 = localStack; tmp132_130.pop$com$bulletphysics$linearmath$Transform(); tmp132_130.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void performDiscreteCollisionDetection() {
/* 131 */     BulletStats.pushProfile("performDiscreteCollisionDetection");
/*     */     try
/*     */     {
/* 135 */       updateAabbs();
/*     */ 
/* 137 */       BulletStats.pushProfile("calculateOverlappingPairs");
/*     */       try {
/* 139 */         this.broadphasePairCache.calculateOverlappingPairs(this.dispatcher1);
/*     */       }
/*     */       finally
/*     */       {
/*     */       }
/*     */ 
/* 145 */       Dispatcher dispatcher = getDispatcher();
/*     */ 
/* 147 */       BulletStats.pushProfile("dispatchAllCollisionPairs");
/*     */       try {
/* 149 */         if (dispatcher != null) {
/* 150 */           dispatcher.dispatchAllCollisionPairs(this.broadphasePairCache.getOverlappingPairCache(), this.dispatchInfo, this.dispatcher1);
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 159 */       BulletStats.popProfile();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeCollisionObject(CollisionObject collisionObject)
/*     */   {
/* 167 */     BroadphaseProxy bp = collisionObject.getBroadphaseHandle();
/* 168 */     if (bp != null)
/*     */     {
/* 172 */       getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(bp, this.dispatcher1);
/* 173 */       getBroadphase().destroyProxy(bp, this.dispatcher1);
/* 174 */       collisionObject.setBroadphaseHandle(null);
/*     */     }
/*     */ 
/* 179 */     this.collisionObjects.remove(collisionObject);
/*     */   }
/*     */ 
/*     */   public void setBroadphase(BroadphaseInterface pairCache) {
/* 183 */     this.broadphasePairCache = pairCache;
/*     */   }
/*     */ 
/*     */   public BroadphaseInterface getBroadphase() {
/* 187 */     return this.broadphasePairCache;
/*     */   }
/*     */ 
/*     */   public OverlappingPairCache getPairCache() {
/* 191 */     return this.broadphasePairCache.getOverlappingPairCache();
/*     */   }
/*     */ 
/*     */   public Dispatcher getDispatcher() {
/* 195 */     return this.dispatcher1;
/*     */   }
/*     */ 
/*     */   public DispatcherInfo getDispatchInfo() {
/* 199 */     return this.dispatchInfo;
/*     */   }
/*     */ 
/*     */   public void updateSingleAabb(CollisionObject arg1)
/*     */   {
/* 206 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f minAabb = localStack.get$javax$vecmath$Vector3f(); Vector3f maxAabb = localStack.get$javax$vecmath$Vector3f();
/* 207 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 208 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 210 */       colObj.getCollisionShape().getAabb(colObj.getWorldTransform(tmpTrans), minAabb, maxAabb);
/*     */ 
/* 212 */       Vector3f contactThreshold = localStack.get$javax$vecmath$Vector3f();
/* 213 */       contactThreshold.set(BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold(), BulletGlobals.getContactBreakingThreshold());
/* 214 */       minAabb.sub(contactThreshold);
/* 215 */       maxAabb.add(contactThreshold);
/*     */ 
/* 217 */       BroadphaseInterface bp = this.broadphasePairCache;
/*     */ 
/* 220 */       tmp.sub(maxAabb, minAabb);
/* 221 */       if ((colObj.isStaticObject()) || (tmp.lengthSquared() < 1.0E+012F)) {
/* 222 */         bp.setAabb(colObj.getBroadphaseHandle(), minAabb, maxAabb, this.dispatcher1);
/*     */       }
/*     */       else
/*     */       {
/* 227 */         colObj.setActivationState(5);
/*     */ 
/* 229 */         if ((updateAabbs_reportMe) && (this.debugDrawer != null)) {
/* 230 */           updateAabbs_reportMe = false;
/* 231 */           this.debugDrawer.reportErrorWarning("Overflow in AABB, object removed from simulation");
/* 232 */           this.debugDrawer.reportErrorWarning("If you can reproduce this, please email bugs@continuousphysics.com\n");
/* 233 */           this.debugDrawer.reportErrorWarning("Please include above information, your Platform, version of OS.\n");
/* 234 */           this.debugDrawer.reportErrorWarning("Thanks.\n");
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 237 */       .Stack tmp212_210 = localStack; tmp212_210.pop$com$bulletphysics$linearmath$Transform(); tmp212_210.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void updateAabbs() {
/* 240 */     BulletStats.pushProfile("updateAabbs");
/*     */     try {
/* 242 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 243 */         CollisionObject colObj = (CollisionObject)this.collisionObjects.getQuick(i);
/*     */ 
/* 246 */         if (colObj.isActive())
/* 247 */           updateSingleAabb(colObj);
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/* 252 */       BulletStats.popProfile();
/*     */     }
/*     */   }
/*     */ 
/*     */   public IDebugDraw getDebugDrawer() {
/* 257 */     return this.debugDrawer;
/*     */   }
/*     */ 
/*     */   public void setDebugDrawer(IDebugDraw debugDrawer) {
/* 261 */     this.debugDrawer = debugDrawer;
/*     */   }
/*     */ 
/*     */   public int getNumCollisionObjects() {
/* 265 */     return this.collisionObjects.size();
/*     */   }
/*     */ 
/*     */   public static void rayTestSingle(Transform arg0, Transform arg1, CollisionObject arg2, CollisionShape arg3, Transform arg4, RayResultCallback arg5)
/*     */   {
/* 274 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); SphereShape pointShape = new SphereShape(0.0F);
/* 275 */       pointShape.setMargin(0.0F);
/* 276 */       ConvexShape castShape = pointShape;
/*     */ 
/* 278 */       if (collisionShape.isConvex()) {
/* 279 */         ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 280 */         castResult.fraction = resultCallback.closestHitFraction;
/*     */ 
/* 282 */         ConvexShape convexShape = (ConvexShape)collisionShape;
/* 283 */         VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/*     */ 
/* 287 */         SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(castShape, convexShape, simplexSolver);
/*     */ 
/* 293 */         if (convexCaster.calcTimeOfImpact(rayFromTrans, rayToTrans, colObjWorldTransform, colObjWorldTransform, castResult))
/*     */         {
/* 295 */           if ((castResult.normal.lengthSquared() > 1.0E-004F) && 
/* 296 */             (castResult.fraction < resultCallback.closestHitFraction))
/*     */           {
/* 299 */             rayFromTrans.basis.transform(castResult.normal);
/*     */ 
/* 302 */             castResult.normal.normalize();
/* 303 */             LocalRayResult localRayResult = new LocalRayResult(collisionObject, null, castResult.normal, castResult.fraction);
/*     */ 
/* 309 */             boolean normalInWorldSpace = true;
/* 310 */             resultCallback.addSingleResult(localRayResult, normalInWorldSpace);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/* 316 */       else if (collisionShape.isConcave()) {
/* 317 */         if (collisionShape.getShapeType() == BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE)
/*     */         {
/* 319 */           BvhTriangleMeshShape triangleMesh = (BvhTriangleMeshShape)collisionShape;
/* 320 */           Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 321 */           worldTocollisionObject.inverse(colObjWorldTransform);
/* 322 */           Vector3f rayFromLocal = localStack.get$javax$vecmath$Vector3f(rayFromTrans.origin);
/* 323 */           worldTocollisionObject.transform(rayFromLocal);
/* 324 */           Vector3f rayToLocal = localStack.get$javax$vecmath$Vector3f(rayToTrans.origin);
/* 325 */           worldTocollisionObject.transform(rayToLocal);
/*     */ 
/* 327 */           BridgeTriangleRaycastCallback rcb = new BridgeTriangleRaycastCallback(rayFromLocal, rayToLocal, resultCallback, collisionObject, triangleMesh);
/* 328 */           rcb.hitFraction = resultCallback.closestHitFraction;
/* 329 */           triangleMesh.performRaycast(rcb, rayFromLocal, rayToLocal);
/*     */         }
/*     */         else {
/* 332 */           ConcaveShape triangleMesh = (ConcaveShape)collisionShape;
/*     */ 
/* 334 */           Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 335 */           worldTocollisionObject.inverse(colObjWorldTransform);
/*     */ 
/* 337 */           Vector3f rayFromLocal = localStack.get$javax$vecmath$Vector3f(rayFromTrans.origin);
/* 338 */           worldTocollisionObject.transform(rayFromLocal);
/* 339 */           Vector3f rayToLocal = localStack.get$javax$vecmath$Vector3f(rayToTrans.origin);
/* 340 */           worldTocollisionObject.transform(rayToLocal);
/*     */ 
/* 342 */           BridgeTriangleRaycastCallback rcb = new BridgeTriangleRaycastCallback(rayFromLocal, rayToLocal, resultCallback, collisionObject, triangleMesh);
/* 343 */           rcb.hitFraction = resultCallback.closestHitFraction;
/*     */ 
/* 345 */           Vector3f rayAabbMinLocal = localStack.get$javax$vecmath$Vector3f(rayFromLocal);
/* 346 */           VectorUtil.setMin(rayAabbMinLocal, rayToLocal);
/* 347 */           Vector3f rayAabbMaxLocal = localStack.get$javax$vecmath$Vector3f(rayFromLocal);
/* 348 */           VectorUtil.setMax(rayAabbMaxLocal, rayToLocal);
/*     */ 
/* 350 */           triangleMesh.processAllTriangles(rcb, rayAabbMinLocal, rayAabbMaxLocal);
/*     */         }
/*     */ 
/*     */       }
/* 355 */       else if (collisionShape.isCompound()) {
/* 356 */         CompoundShape compoundShape = (CompoundShape)collisionShape;
/* 357 */         int i = 0;
/* 358 */         Transform childTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 359 */         for (i = 0; i < compoundShape.getNumChildShapes(); i++) {
/* 360 */           compoundShape.getChildTransform(i, childTrans);
/* 361 */           CollisionShape childCollisionShape = compoundShape.getChildShape(i);
/* 362 */           Transform childWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform(colObjWorldTransform);
/* 363 */           childWorldTrans.mul(childTrans);
/*     */ 
/* 365 */           CollisionShape saveCollisionShape = collisionObject.getCollisionShape();
/* 366 */           collisionObject.internalSetTemporaryCollisionShape(childCollisionShape);
/* 367 */           rayTestSingle(rayFromTrans, rayToTrans, collisionObject, childCollisionShape, childWorldTrans, resultCallback);
/*     */ 
/* 373 */           collisionObject.internalSetTemporaryCollisionShape(saveCollisionShape);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 378 */       .Stack tmp563_561 = localStack; tmp563_561.pop$com$bulletphysics$linearmath$Transform(); tmp563_561.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void objectQuerySingle(ConvexShape arg0, Transform arg1, Transform arg2, CollisionObject arg3, CollisionShape arg4, Transform arg5, ConvexResultCallback arg6, float arg7)
/*     */   {
/* 410 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Matrix3f(); if (collisionShape.isConvex()) {
/* 411 */         ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 412 */         castResult.allowedPenetration = allowedPenetration;
/* 413 */         castResult.fraction = 1.0F;
/*     */ 
/* 415 */         ConvexShape convexShape = (ConvexShape)collisionShape;
/* 416 */         VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 417 */         GjkEpaPenetrationDepthSolver gjkEpaPenetrationSolver = new GjkEpaPenetrationDepthSolver();
/*     */ 
/* 421 */         GjkConvexCast convexCaster2 = new GjkConvexCast(castShape, convexShape, simplexSolver);
/*     */ 
/* 424 */         ConvexCast castPtr = convexCaster2;
/*     */ 
/* 426 */         if (castPtr.calcTimeOfImpact(convexFromTrans, convexToTrans, colObjWorldTransform, colObjWorldTransform, castResult))
/*     */         {
/* 428 */           if ((castResult.normal.lengthSquared() > 1.0E-004F) && 
/* 429 */             (castResult.fraction < resultCallback.closestHitFraction)) {
/* 430 */             castResult.normal.normalize();
/* 431 */             LocalConvexResult localConvexResult = new LocalConvexResult(collisionObject, null, castResult.normal, castResult.hitPoint, castResult.fraction);
/*     */ 
/* 433 */             boolean normalInWorldSpace = true;
/* 434 */             resultCallback.addSingleResult(localConvexResult, normalInWorldSpace);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/* 440 */       else if (collisionShape.isConcave()) {
/* 441 */         if (collisionShape.getShapeType() == BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE) {
/* 442 */           BvhTriangleMeshShape triangleMesh = (BvhTriangleMeshShape)collisionShape;
/* 443 */           Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 444 */           worldTocollisionObject.inverse(colObjWorldTransform);
/*     */ 
/* 446 */           Vector3f convexFromLocal = localStack.get$javax$vecmath$Vector3f();
/* 447 */           convexFromLocal.set(convexFromTrans.origin);
/* 448 */           worldTocollisionObject.transform(convexFromLocal);
/*     */ 
/* 450 */           Vector3f convexToLocal = localStack.get$javax$vecmath$Vector3f();
/* 451 */           convexToLocal.set(convexToTrans.origin);
/* 452 */           worldTocollisionObject.transform(convexToLocal);
/*     */ 
/* 455 */           Transform rotationXform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 456 */           Matrix3f tmpMat = localStack.get$javax$vecmath$Matrix3f();
/* 457 */           tmpMat.mul(worldTocollisionObject.basis, convexToTrans.basis);
/* 458 */           rotationXform.set(tmpMat);
/*     */ 
/* 460 */           BridgeTriangleConvexcastCallback tccb = new BridgeTriangleConvexcastCallback(castShape, convexFromTrans, convexToTrans, resultCallback, collisionObject, triangleMesh, colObjWorldTransform);
/* 461 */           tccb.hitFraction = resultCallback.closestHitFraction;
/* 462 */           tccb.normalInWorldSpace = true;
/*     */ 
/* 464 */           Vector3f boxMinLocal = localStack.get$javax$vecmath$Vector3f();
/* 465 */           Vector3f boxMaxLocal = localStack.get$javax$vecmath$Vector3f();
/* 466 */           castShape.getAabb(rotationXform, boxMinLocal, boxMaxLocal);
/* 467 */           triangleMesh.performConvexcast(tccb, convexFromLocal, convexToLocal, boxMinLocal, boxMaxLocal);
/*     */         }
/*     */         else {
/* 470 */           BvhTriangleMeshShape triangleMesh = (BvhTriangleMeshShape)collisionShape;
/* 471 */           Transform worldTocollisionObject = localStack.get$com$bulletphysics$linearmath$Transform();
/* 472 */           worldTocollisionObject.inverse(colObjWorldTransform);
/*     */ 
/* 474 */           Vector3f convexFromLocal = localStack.get$javax$vecmath$Vector3f();
/* 475 */           convexFromLocal.set(convexFromTrans.origin);
/* 476 */           worldTocollisionObject.transform(convexFromLocal);
/*     */ 
/* 478 */           Vector3f convexToLocal = localStack.get$javax$vecmath$Vector3f();
/* 479 */           convexToLocal.set(convexToTrans.origin);
/* 480 */           worldTocollisionObject.transform(convexToLocal);
/*     */ 
/* 483 */           Transform rotationXform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 484 */           Matrix3f tmpMat = localStack.get$javax$vecmath$Matrix3f();
/* 485 */           tmpMat.mul(worldTocollisionObject.basis, convexToTrans.basis);
/* 486 */           rotationXform.set(tmpMat);
/*     */ 
/* 488 */           BridgeTriangleConvexcastCallback tccb = new BridgeTriangleConvexcastCallback(castShape, convexFromTrans, convexToTrans, resultCallback, collisionObject, triangleMesh, colObjWorldTransform);
/* 489 */           tccb.hitFraction = resultCallback.closestHitFraction;
/* 490 */           tccb.normalInWorldSpace = false;
/* 491 */           Vector3f boxMinLocal = localStack.get$javax$vecmath$Vector3f();
/* 492 */           Vector3f boxMaxLocal = localStack.get$javax$vecmath$Vector3f();
/* 493 */           castShape.getAabb(rotationXform, boxMinLocal, boxMaxLocal);
/*     */ 
/* 495 */           Vector3f rayAabbMinLocal = localStack.get$javax$vecmath$Vector3f(convexFromLocal);
/* 496 */           VectorUtil.setMin(rayAabbMinLocal, convexToLocal);
/* 497 */           Vector3f rayAabbMaxLocal = localStack.get$javax$vecmath$Vector3f(convexFromLocal);
/* 498 */           VectorUtil.setMax(rayAabbMaxLocal, convexToLocal);
/* 499 */           rayAabbMinLocal.add(boxMinLocal);
/* 500 */           rayAabbMaxLocal.add(boxMaxLocal);
/* 501 */           triangleMesh.processAllTriangles(tccb, rayAabbMinLocal, rayAabbMaxLocal);
/*     */         }
/*     */ 
/*     */       }
/* 506 */       else if (collisionShape.isCompound()) {
/* 507 */         CompoundShape compoundShape = (CompoundShape)collisionShape;
/* 508 */         for (int i = 0; i < compoundShape.getNumChildShapes(); i++) {
/* 509 */           Transform childTrans = compoundShape.getChildTransform(i, localStack.get$com$bulletphysics$linearmath$Transform());
/* 510 */           CollisionShape childCollisionShape = compoundShape.getChildShape(i);
/* 511 */           Transform childWorldTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 512 */           childWorldTrans.mul(colObjWorldTransform, childTrans);
/*     */ 
/* 514 */           CollisionShape saveCollisionShape = collisionObject.getCollisionShape();
/* 515 */           collisionObject.internalSetTemporaryCollisionShape(childCollisionShape);
/* 516 */           objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, childCollisionShape, childWorldTrans, resultCallback, allowedPenetration);
/*     */ 
/* 522 */           collisionObject.internalSetTemporaryCollisionShape(saveCollisionShape);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 527 */       .Stack tmp729_727 = localStack; tmp729_727.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp733_729 = tmp729_727; tmp733_729.pop$javax$vecmath$Vector3f(); tmp733_729.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void rayTest(Vector3f arg1, Vector3f arg2, RayResultCallback arg3)
/*     */   {
/* 534 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform rayFromTrans = localStack.get$com$bulletphysics$linearmath$Transform(); Transform rayToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 535 */       rayFromTrans.setIdentity();
/* 536 */       rayFromTrans.origin.set(rayFromWorld);
/* 537 */       rayToTrans.setIdentity();
/*     */ 
/* 539 */       rayToTrans.origin.set(rayToWorld);
/*     */ 
/* 542 */       Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 543 */       float[] hitLambda = new float[1];
/*     */ 
/* 545 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 547 */       for (int i = 0; i < this.collisionObjects.size(); i++)
/*     */       {
/* 549 */         if (resultCallback.closestHitFraction == 0.0F)
/*     */         {
/*     */           break;
/*     */         }
/* 553 */         CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*     */ 
/* 555 */         if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
/*     */         {
/* 557 */           collisionObject.getCollisionShape().getAabb(collisionObject.getWorldTransform(tmpTrans), collisionObjectAabbMin, collisionObjectAabbMax);
/*     */ 
/* 559 */           hitLambda[0] = resultCallback.closestHitFraction;
/* 560 */           Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/* 561 */           if (AabbUtil2.rayAabb(rayFromWorld, rayToWorld, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal))
/* 562 */             rayTestSingle(rayFromTrans, rayToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 571 */       .Stack tmp225_223 = localStack; tmp225_223.pop$com$bulletphysics$linearmath$Transform(); tmp225_223.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void convexSweepTest(ConvexShape arg1, Transform arg2, Transform arg3, ConvexResultCallback arg4)
/*     */   {
/* 578 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Quat4f(); Transform convexFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 579 */       Transform convexToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 581 */       convexFromTrans.set(convexFromWorld);
/* 582 */       convexToTrans.set(convexToWorld);
/*     */ 
/* 584 */       Vector3f castShapeAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 585 */       Vector3f castShapeAabbMax = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 589 */       Vector3f linVel = localStack.get$javax$vecmath$Vector3f();
/* 590 */       Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
/* 591 */       TransformUtil.calculateVelocity(convexFromTrans, convexToTrans, 1.0F, linVel, angVel);
/* 592 */       Transform R = localStack.get$com$bulletphysics$linearmath$Transform();
/* 593 */       R.setIdentity();
/* 594 */       R.setRotation(convexFromTrans.getRotation(localStack.get$javax$vecmath$Quat4f()));
/* 595 */       castShape.calculateTemporalAabb(R, linVel, angVel, 1.0F, castShapeAabbMin, castShapeAabbMax);
/*     */ 
/* 598 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 599 */       Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 600 */       Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 601 */       float[] hitLambda = new float[1];
/*     */ 
/* 605 */       for (int i = 0; i < this.collisionObjects.size(); i++) {
/* 606 */         CollisionObject collisionObject = (CollisionObject)this.collisionObjects.getQuick(i);
/*     */ 
/* 609 */         if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
/*     */         {
/* 611 */           collisionObject.getWorldTransform(tmpTrans);
/* 612 */           collisionObject.getCollisionShape().getAabb(tmpTrans, collisionObjectAabbMin, collisionObjectAabbMax);
/* 613 */           AabbUtil2.aabbExpand(collisionObjectAabbMin, collisionObjectAabbMax, castShapeAabbMin, castShapeAabbMax);
/* 614 */           hitLambda[0] = 1.0F;
/* 615 */           Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/* 616 */           if (AabbUtil2.rayAabb(convexFromWorld.origin, convexToWorld.origin, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal))
/* 617 */             objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, collisionObject.getCollisionShape(), tmpTrans, resultCallback, getDispatchInfo().allowedCcdPenetration);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 626 */       .Stack tmp309_307 = localStack; tmp309_307.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp313_309 = tmp309_307; tmp313_309.pop$javax$vecmath$Vector3f(); tmp313_309.pop$javax$vecmath$Quat4f(); } throw finally;
/*     */   }
/*     */   public ObjectArrayList<CollisionObject> getCollisionObjectArray() {
/* 629 */     return this.collisionObjects;
/*     */   }
/*     */ 
/*     */   private static class BridgeTriangleRaycastCallback extends TriangleRaycastCallback
/*     */   {
/*     */     public CollisionWorld.RayResultCallback resultCallback;
/*     */     public CollisionObject collisionObject;
/*     */     public ConcaveShape triangleMesh;
/*     */ 
/*     */     public BridgeTriangleRaycastCallback(Vector3f from, Vector3f to, CollisionWorld.RayResultCallback resultCallback, CollisionObject collisionObject, ConcaveShape triangleMesh)
/*     */     {
/* 794 */       super(to);
/* 795 */       this.resultCallback = resultCallback;
/* 796 */       this.collisionObject = collisionObject;
/* 797 */       this.triangleMesh = triangleMesh;
/*     */     }
/*     */ 
/*     */     public float reportHit(Vector3f hitNormalLocal, float hitFraction, int partId, int triangleIndex) {
/* 801 */       CollisionWorld.LocalShapeInfo shapeInfo = new CollisionWorld.LocalShapeInfo();
/* 802 */       shapeInfo.shapePart = partId;
/* 803 */       shapeInfo.triangleIndex = triangleIndex;
/*     */ 
/* 805 */       CollisionWorld.LocalRayResult rayResult = new CollisionWorld.LocalRayResult(this.collisionObject, shapeInfo, hitNormalLocal, hitFraction);
/*     */ 
/* 807 */       boolean normalInWorldSpace = false;
/* 808 */       return this.resultCallback.addSingleResult(rayResult, normalInWorldSpace);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class ClosestConvexResultCallback extends CollisionWorld.ConvexResultCallback
/*     */   {
/* 749 */     public final Vector3f convexFromWorld = new Vector3f();
/* 750 */     public final Vector3f convexToWorld = new Vector3f();
/* 751 */     public final Vector3f hitNormalWorld = new Vector3f();
/* 752 */     public final Vector3f hitPointWorld = new Vector3f();
/*     */     public CollisionObject hitCollisionObject;
/*     */ 
/*     */     public ClosestConvexResultCallback(Vector3f convexFromWorld, Vector3f convexToWorld)
/*     */     {
/* 756 */       this.convexFromWorld.set(convexFromWorld);
/* 757 */       this.convexToWorld.set(convexToWorld);
/* 758 */       this.hitCollisionObject = null;
/*     */     }
/*     */ 
/*     */     public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
/*     */     {
/* 764 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$com$bulletphysics$linearmath$Transform(); assert (convexResult.hitFraction <= this.closestHitFraction);
/*     */ 
/* 766 */         this.closestHitFraction = convexResult.hitFraction;
/* 767 */         this.hitCollisionObject = convexResult.hitCollisionObject;
/* 768 */         if (normalInWorldSpace) {
/* 769 */           this.hitNormalWorld.set(convexResult.hitNormalLocal);
/* 770 */           if (this.hitNormalWorld.length() > 2.0F) {
/* 771 */             System.out.println("CollisionWorld.addSingleResult world " + this.hitNormalWorld);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 776 */           this.hitNormalWorld.set(convexResult.hitNormalLocal);
/* 777 */           this.hitCollisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(this.hitNormalWorld);
/* 778 */           if (this.hitNormalWorld.length() > 2.0F) {
/* 779 */             System.out.println("CollisionWorld.addSingleResult world " + this.hitNormalWorld);
/*     */           }
/*     */         }
/*     */ 
/* 783 */         this.hitPointWorld.set(convexResult.hitPointLocal);
/* 784 */         return convexResult.hitFraction; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class ConvexResultCallback
/*     */   {
/* 731 */     public float closestHitFraction = 1.0F;
/* 732 */     public short collisionFilterGroup = 1;
/* 733 */     public short collisionFilterMask = -1;
/*     */ 
/*     */     public boolean hasHit() {
/* 736 */       return this.closestHitFraction < 1.0F;
/*     */     }
/*     */ 
/*     */     public boolean needsCollision(BroadphaseProxy proxy0) {
/* 740 */       boolean collides = (proxy0.collisionFilterGroup & this.collisionFilterMask & 0xFFFF) != 0;
/* 741 */       collides = (collides) && ((this.collisionFilterGroup & proxy0.collisionFilterMask & 0xFFFF) != 0);
/* 742 */       return collides;
/*     */     }
/*     */ 
/*     */     public abstract float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean);
/*     */   }
/*     */ 
/*     */   public static class LocalConvexResult
/*     */   {
/*     */     public CollisionObject hitCollisionObject;
/*     */     public CollisionWorld.LocalShapeInfo localShapeInfo;
/* 717 */     public final Vector3f hitNormalLocal = new Vector3f();
/* 718 */     public final Vector3f hitPointLocal = new Vector3f();
/*     */     public float hitFraction;
/*     */ 
/*     */     public LocalConvexResult(CollisionObject hitCollisionObject, CollisionWorld.LocalShapeInfo localShapeInfo, Vector3f hitNormalLocal, Vector3f hitPointLocal, float hitFraction)
/*     */     {
/* 722 */       this.hitCollisionObject = hitCollisionObject;
/* 723 */       this.localShapeInfo = localShapeInfo;
/* 724 */       this.hitNormalLocal.set(hitNormalLocal);
/* 725 */       this.hitPointLocal.set(hitPointLocal);
/* 726 */       this.hitFraction = hitFraction;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class ClosestRayResultCallback extends CollisionWorld.RayResultCallback
/*     */   {
/* 682 */     public final Vector3f rayFromWorld = new Vector3f();
/* 683 */     public final Vector3f rayToWorld = new Vector3f();
/*     */ 
/* 685 */     public final Vector3f hitNormalWorld = new Vector3f();
/* 686 */     public final Vector3f hitPointWorld = new Vector3f();
/*     */ 
/*     */     public ClosestRayResultCallback(Vector3f rayFromWorld, Vector3f rayToWorld) {
/* 689 */       this.rayFromWorld.set(rayFromWorld);
/* 690 */       this.rayToWorld.set(rayToWorld);
/*     */     }
/*     */ 
/*     */     public float addSingleResult(CollisionWorld.LocalRayResult arg1, boolean arg2)
/*     */     {
/* 696 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$com$bulletphysics$linearmath$Transform(); assert (rayResult.hitFraction <= this.closestHitFraction);
/*     */ 
/* 698 */         this.closestHitFraction = rayResult.hitFraction;
/* 699 */         this.collisionObject = rayResult.collisionObject;
/* 700 */         if (normalInWorldSpace) {
/* 701 */           this.hitNormalWorld.set(rayResult.hitNormalLocal);
/*     */         }
/*     */         else
/*     */         {
/* 705 */           this.hitNormalWorld.set(rayResult.hitNormalLocal);
/* 706 */           this.collisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(this.hitNormalWorld);
/*     */         }
/*     */ 
/* 709 */         VectorUtil.setInterpolate3(this.hitPointWorld, this.rayFromWorld, this.rayToWorld, rayResult.hitFraction);
/* 710 */         return rayResult.hitFraction; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class RayResultCallback
/*     */   {
/* 663 */     public float closestHitFraction = 1.0F;
/*     */     public CollisionObject collisionObject;
/* 665 */     public short collisionFilterGroup = 1;
/* 666 */     public short collisionFilterMask = -1;
/*     */ 
/*     */     public boolean hasHit() {
/* 669 */       return this.collisionObject != null;
/*     */     }
/*     */ 
/*     */     public boolean needsCollision(BroadphaseProxy proxy0) {
/* 673 */       boolean collides = (proxy0.collisionFilterGroup & this.collisionFilterMask & 0xFFFF) != 0;
/* 674 */       collides = (collides) && ((this.collisionFilterGroup & proxy0.collisionFilterMask & 0xFFFF) != 0);
/* 675 */       return collides;
/*     */     }
/*     */ 
/*     */     public abstract float addSingleResult(CollisionWorld.LocalRayResult paramLocalRayResult, boolean paramBoolean);
/*     */   }
/*     */ 
/*     */   public static class LocalRayResult
/*     */   {
/*     */     public CollisionObject collisionObject;
/*     */     public CollisionWorld.LocalShapeInfo localShapeInfo;
/* 648 */     public final Vector3f hitNormalLocal = new Vector3f();
/*     */     public float hitFraction;
/*     */ 
/*     */     public LocalRayResult(CollisionObject collisionObject, CollisionWorld.LocalShapeInfo localShapeInfo, Vector3f hitNormalLocal, float hitFraction)
/*     */     {
/* 652 */       this.collisionObject = collisionObject;
/* 653 */       this.localShapeInfo = localShapeInfo;
/* 654 */       this.hitNormalLocal.set(hitNormalLocal);
/* 655 */       this.hitFraction = hitFraction;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class LocalShapeInfo
/*     */   {
/*     */     public int shapePart;
/*     */     public int triangleIndex;
/*     */   }
/*     */ 
/*     */   private static class BridgeTriangleConvexcastCallback extends TriangleConvexcastCallback
/*     */   {
/*     */     public CollisionWorld.ConvexResultCallback resultCallback;
/*     */     public CollisionObject collisionObject;
/*     */     public TriangleMeshShape triangleMesh;
/*     */     public boolean normalInWorldSpace;
/*     */ 
/*     */     public BridgeTriangleConvexcastCallback(ConvexShape castShape, Transform from, Transform to, CollisionWorld.ConvexResultCallback resultCallback, CollisionObject collisionObject, TriangleMeshShape triangleMesh, Transform triangleToWorld)
/*     */     {
/* 387 */       super(from, to, triangleToWorld, triangleMesh.getMargin());
/* 388 */       this.resultCallback = resultCallback;
/* 389 */       this.collisionObject = collisionObject;
/* 390 */       this.triangleMesh = triangleMesh;
/*     */     }
/*     */ 
/*     */     public float reportHit(Vector3f hitNormalLocal, Vector3f hitPointLocal, float hitFraction, int partId, int triangleIndex)
/*     */     {
/* 395 */       CollisionWorld.LocalShapeInfo shapeInfo = new CollisionWorld.LocalShapeInfo();
/* 396 */       shapeInfo.shapePart = partId;
/* 397 */       shapeInfo.triangleIndex = triangleIndex;
/* 398 */       if (hitFraction <= this.resultCallback.closestHitFraction) {
/* 399 */         CollisionWorld.LocalConvexResult convexResult = new CollisionWorld.LocalConvexResult(this.collisionObject, shapeInfo, hitNormalLocal, hitPointLocal, hitFraction);
/* 400 */         return this.resultCallback.addSingleResult(convexResult, this.normalInWorldSpace);
/*     */       }
/* 402 */       return hitFraction;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionWorld
 * JD-Core Version:    0.6.2
 */