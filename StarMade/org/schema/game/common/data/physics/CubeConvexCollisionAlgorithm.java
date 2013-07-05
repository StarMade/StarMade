/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.shapes.BoxShape;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import dO;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.physics.octree.ArrayOctree;
/*     */ import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import q;
/*     */ import xO;
/*     */ 
/*     */ public class CubeConvexCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/* 132 */   protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*     */   GjkPairDetectorExt gjkPairDetector;
/* 136 */   private static ThreadLocal threadLocal = new CubeConvexCollisionAlgorithm.1();
/*     */   public boolean lowLevelOfDetail;
/*     */   private boolean ownManifold;
/*     */   private static final float margin = 0.05F;
/*     */   private boolean onServer;
/*     */   private PersistentManifold manifoldPtr;
/*     */   private CubeConvexVariableSet v;
/*     */   private CubeConvexCollisionAlgorithm.OuterSegmentHandler outerSegmentHandler;
/*     */   private long currentTime;
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 165 */     System.err.println("CALCULATING CONVEX CUBE TOI");
/* 166 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void doNarrowTest(SegmentData paramSegmentData, CollisionObject paramCollisionObject, o paramo1, o paramo2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 186 */     paramCollisionObject.getCollisionShape().getAabb(this.v.convexShapeTransform, this.v.otherminOut, this.v.othermaxOut);
/*     */ 
/* 188 */     for (paramCollisionObject = paramo1.a; paramCollisionObject < paramo2.a; paramCollisionObject = (byte)(paramCollisionObject + 1))
/* 189 */       for (paramDispatcherInfo = paramo1.b; paramDispatcherInfo < paramo2.b; paramDispatcherInfo = (byte)(paramDispatcherInfo + 1))
/* 190 */         for (paramManifoldResult = paramo1.c; paramManifoldResult < paramo2.c; paramManifoldResult = (byte)(paramManifoldResult + 1)) {
/* 191 */           int i = SegmentData.getInfoIndex((byte)(paramCollisionObject + 8), (byte)(paramDispatcherInfo + 8), (byte)(paramManifoldResult + 8));
/*     */           ElementInformation localElementInformation;
/* 196 */           if ((paramSegmentData.contains(i)) && ((localElementInformation = ElementKeyMap.getInfo(paramSegmentData.getType(i))).isPhysical(paramSegmentData.isActive(i))))
/*     */           {
/* 198 */             int j = paramSegmentData.getOrientation(i);
/*     */ 
/* 200 */             this.v.elemPosA.set(paramCollisionObject, paramDispatcherInfo, paramManifoldResult);
/* 201 */             this.v.elemPosA.x += paramSegmentData.getSegment().a.a;
/* 202 */             this.v.elemPosA.y += paramSegmentData.getSegment().a.b;
/* 203 */             this.v.elemPosA.z += paramSegmentData.getSegment().a.c;
/* 204 */             this.v.min.set(this.v.elemPosA);
/* 205 */             this.v.max.set(this.v.elemPosA);
/*     */ 
/* 207 */             this.v.min.x -= 0.5F;
/* 208 */             this.v.min.y -= 0.5F;
/* 209 */             this.v.min.z -= 0.5F;
/* 210 */             this.v.max.x += 0.5F;
/* 211 */             this.v.max.y += 0.5F;
/* 212 */             this.v.max.z += 0.5F;
/*     */ 
/* 217 */             AabbUtil2.transformAabb(this.v.min, this.v.max, 0.25F, this.v.cubeMeshTransform, this.v.minOut, this.v.maxOut);
/*     */ 
/* 221 */             this.v.box0.setMargin(0.0F);
/*     */ 
/* 228 */             if (AabbUtil2.testAabbAgainstAabb2(this.v.minOut, this.v.maxOut, this.v.otherminOut, this.v.othermaxOut))
/*     */             {
/* 230 */               paramSegmentData.getSegment(); Segment.d();
/* 231 */               this.v.boxTransformation.set(this.v.cubeMeshTransform);
/*     */ 
/* 233 */               this.v.nA.set(this.v.elemPosA);
/* 234 */               this.v.boxTransformation.basis.transform(this.v.nA);
/* 235 */               this.v.boxTransformation.origin.add(this.v.nA);
/*     */ 
/* 237 */               this.v.positionCache.add(new Transform(this.v.boxTransformation));
/* 238 */               this.v.blockInfoCache.add(new q(localElementInformation.getBlockStyle(), j, paramSegmentData.isActive(i) ? 1 : 0));
/*     */             }
/*     */           }
/*     */         }
/*     */   }
/*     */ 
/*     */   private void doRegularCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, PersistentManifold paramPersistentManifold, DispatcherInfo paramDispatcherInfo)
/*     */   {
/*     */     DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
/* 262 */     (
/* 263 */       localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get())
/* 263 */       .init();
/*     */ 
/* 267 */     this.gjkPairDetector.setMinkowskiA(paramConvexShape1);
/* 268 */     this.gjkPairDetector.setMinkowskiB(paramConvexShape2);
/*     */ 
/* 270 */     localClosestPointInput.maximumDistanceSquared = (paramConvexShape1.getMargin() + paramPersistentManifold.getContactBreakingThreshold());
/*     */ 
/* 272 */     localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
/*     */ 
/* 277 */     localClosestPointInput.transformA.set(paramTransform1);
/* 278 */     localClosestPointInput.transformB.set(paramTransform2);
/*     */ 
/* 280 */     this.gjkPairDetector.getClosestPoints(localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw);
/*     */ 
/* 291 */     this.pointInputsPool.release(localClosestPointInput);
/*     */ 
/* 293 */     paramManifoldResult.refreshContactPoints();
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
/*     */   {
/* 303 */     if ((this.manifoldPtr != null) && (this.ownManifold))
/* 304 */       paramObjectArrayList.add(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo)
/*     */   {
/* 312 */     super.init(paramCollisionAlgorithmConstructionInfo);
/*     */   }
/*     */ 
/*     */   public void init(PersistentManifold paramPersistentManifold, CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver, boolean paramBoolean)
/*     */   {
/* 321 */     super.init(paramCollisionAlgorithmConstructionInfo);
/* 322 */     this.manifoldPtr = paramPersistentManifold;
/* 323 */     this.v = ((CubeConvexVariableSet)threadLocal.get());
/*     */ 
/* 325 */     this.gjkPairDetector = new GjkPairDetectorExt(this.v.gjkVars);
/*     */ 
/* 327 */     this.gjkPairDetector.init(null, null, paramSimplexSolverInterface, paramConvexPenetrationDepthSolver);
/* 328 */     if (this.manifoldPtr == null) {
/* 329 */       if (!paramBoolean)
/* 330 */         this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/*     */       else {
/* 332 */         this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject2, paramCollisionObject1);
/*     */       }
/* 334 */       this.ownManifold = true;
/*     */     }
/* 336 */     this.outerSegmentHandler = new CubeConvexCollisionAlgorithm.OuterSegmentHandler(this, null);
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 343 */     if (this.manifoldPtr == null)
/*     */     {
/* 353 */       this.ownManifold = true;
/*     */     }
/*     */ 
/* 356 */     this.manifoldPtr = paramManifoldResult.getPersistentManifold();
/* 357 */     this.ownManifold = true;
/*     */ 
/* 361 */     long l = System.currentTimeMillis();
/* 362 */     this.currentTime = l;
/*     */ 
/* 383 */     if (((
/* 383 */       paramCollisionObject2 = paramCollisionObject2) instanceof PairCachingGhostObjectUncollidable))
/*     */     {
/* 384 */       return;
/*     */     }
/*     */ 
/* 387 */     this.v.positionCache.clear();
/* 388 */     this.v.blockInfoCache.clear();
/* 389 */     paramManifoldResult.setPersistentManifold(this.manifoldPtr);
/* 390 */     Object localObject1 = (CubeShape)paramCollisionObject1.getCollisionShape();
/* 391 */     ConvexShape localConvexShape = (ConvexShape)paramCollisionObject2.getCollisionShape();
/*     */ 
/* 393 */     this.onServer = ((CubeShape)localObject1).getSegmentBuffer().a().isOnServer();
/*     */ 
/* 398 */     this.v.oSet = ArrayOctree.getSet(((CubeShape)localObject1).getSegmentBuffer().a().isOnServer());
/*     */ 
/* 400 */     this.v.cubeMeshTransform = paramCollisionObject1.getWorldTransform(this.v.cubeMeshTransform);
/* 401 */     this.v.convexShapeTransform = paramCollisionObject2.getWorldTransform(this.v.convexShapeTransform);
/*     */ 
/* 403 */     this.v.absolute.set(this.v.cubeMeshTransform.basis);
/* 404 */     MatrixUtil.absolute(this.v.absolute);
/* 405 */     ((CubeShape)localObject1).setMargin(0.05F);
/* 406 */     ((CubeShape)localObject1).getAabb(this.v.cubeMeshTransform, this.v.outMin, this.v.outMax);
/* 407 */     localConvexShape.getAabb(this.v.convexShapeTransform, this.v.shapeMin, this.v.shapeMax);
/*     */ 
/* 409 */     if (!AabbUtil2.testAabbAgainstAabb2(this.v.outMin, this.v.outMax, this.v.shapeMin, this.v.shapeMax)) {
/* 410 */       return;
/*     */     }
/*     */ 
/* 414 */     ((CubeShape)localObject1).setMargin(0.05F);
/*     */ 
/* 416 */     this.outerSegmentHandler.col1 = paramCollisionObject2;
/* 417 */     this.outerSegmentHandler.cubeShape0 = ((CubeShape)localObject1);
/* 418 */     this.outerSegmentHandler.dispatchInfo = paramDispatcherInfo;
/* 419 */     this.outerSegmentHandler.resultOut = paramManifoldResult;
/*     */ 
/* 421 */     this.v.outer.a.set(this.v.outMin);
/* 422 */     this.v.outer.b.set(this.v.outMax);
/* 423 */     this.v.inner.a.set(this.v.shapeMin);
/* 424 */     this.v.inner.b.set(this.v.shapeMax);
/*     */ 
/* 427 */     if ((this.v.inner.a(this.v.outer, this.v.outBB) == null) || 
/* 427 */       (!this.v.outBB.b()))
/*     */     {
/* 429 */       return;
/*     */     }
/*     */ 
/* 433 */     paramCollisionObject1.getWorldTransform(this.v.inv);
/* 434 */     this.v.inv.inverse();
/* 435 */     AabbUtil2.transformAabb(new Vector3f(this.v.outBB.a), new Vector3f(this.v.outBB.b), 0.5F, this.v.inv, this.v.outBB.a, this.v.outBB.b);
/*     */ 
/* 441 */     this.v.minIntA.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/* 442 */     this.v.minIntA.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/* 443 */     this.v.minIntA.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/*     */ 
/* 445 */     this.v.maxIntA.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/* 446 */     this.v.maxIntA.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/* 447 */     this.v.maxIntA.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/*     */ 
/* 449 */     ((CubeShape)localObject1).getSegmentBuffer().b(this.outerSegmentHandler, this.v.minIntA, this.v.maxIntA);
/*     */ 
/* 458 */     paramCollisionObject1 = this.v.positionCache.size();
/*     */ 
/* 462 */     for (paramCollisionObject2 = 0; paramCollisionObject2 < paramCollisionObject1; paramCollisionObject2++) {
/* 463 */       localObject1 = (Transform)this.v.positionCache.get(paramCollisionObject2);
/*     */       q localq;
/* 466 */       int i = (
/* 466 */         localq = (q)this.v.blockInfoCache.get(paramCollisionObject2)).a;
/*     */ 
/* 467 */       int j = localq.b;
/* 468 */       boolean bool = localq.c == 1;
/*     */ 
/* 470 */       Object localObject2 = this.v.box0;
/* 471 */       if ((i > 0) && (i < 3)) {
/* 472 */         localObject2 = dO.a(i, (byte)j, bool);
/*     */       }
/*     */       try
/*     */       {
/* 476 */         doRegularCollision((ConvexShape)localObject2, localConvexShape, (Transform)localObject1, this.v.convexShapeTransform, paramManifoldResult, this.manifoldPtr, paramDispatcherInfo);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 484 */         localException.printStackTrace();
/*     */ 
/* 481 */         System.err.println(this.v.box0 + ", " + localConvexShape + ", " + localObject1 + ", " + this.v.convexShapeTransform + ", " + paramManifoldResult + ", " + paramDispatcherInfo);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 486 */     if (this.ownManifold) {
/* 487 */       paramManifoldResult.refreshContactPoints(); return;
/*     */     }
/* 489 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public void processDistinctCollision(CubeShape paramCubeShape, CollisionObject paramCollisionObject, SegmentData paramSegmentData, Transform paramTransform1, Transform paramTransform2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 522 */     if (!this.v.intersectionCallBackAwithB.initialized) {
/* 523 */       this.v.intersectionCallBackAwithB.createHitCache(512);
/*     */     }
/*     */ 
/* 529 */     paramCollisionObject.getCollisionShape().getAabb(paramTransform2, this.v.othermin, this.v.othermax);
/*     */ 
/* 531 */     this.v.intersectionCallBackAwithB.reset();
/*     */ 
/* 535 */     this.v.intersectionCallBackAwithB = paramSegmentData.getOctree().findIntersecting(this.v.oSet, this.v.intersectionCallBackAwithB, paramSegmentData.getSegment(), paramTransform1, this.v.absolute, 0.05F, this.v.othermin, this.v.othermax, 1.0F);
/*     */ 
/* 542 */     if (this.v.intersectionCallBackAwithB.hitCount == 0) {
/* 543 */       paramManifoldResult.refreshContactPoints();
/* 544 */       return;
/*     */     }
/*     */ 
/* 547 */     for (paramCubeShape = 0; paramCubeShape < this.v.intersectionCallBackAwithB.hitCount; 
/* 548 */       paramCubeShape++)
/*     */     {
/* 550 */       this.v.intersectionCallBackAwithB.getHit(paramCubeShape, this.v.hitMin, this.v.hitMax, this.v.startA, this.v.endA);
/*     */ 
/* 552 */       assert ((this.v.startA.a < this.v.endA.a) && (this.v.startA.b < this.v.endA.b) && (this.v.startA.c < this.v.endA.c));
/*     */ 
/* 554 */       doNarrowTest(paramSegmentData, paramCollisionObject, this.v.startA, this.v.endA, paramDispatcherInfo, paramManifoldResult);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */