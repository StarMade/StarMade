/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*     */ import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.shapes.BoxShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShapeChild;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import dO;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
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
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*     */ import q;
/*     */ import vo;
/*     */ import xO;
/*     */ import zM;
/*     */ import zU;
/*     */ 
/*     */ public class CubeCubeCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/*  73 */   private int handles = 0;
/*     */ 
/* 199 */   protected final ObjectPool pointInputsPool = ObjectPool.get(DiscreteCollisionDetectorInterface.ClosestPointInput.class);
/*     */   private GjkPairDetectorExt gjkPairDetector;
/* 202 */   private BoxBoxDetector boxboxPairDetector = new BoxBoxDetector();
/*     */ 
/* 204 */   private int slow_threashold = ((Integer)vo.q.a()).intValue();
/*     */   public boolean lowLevelOfDetail;
/*     */   private boolean ownManifold;
/*     */   private PersistentManifold manifoldPtr;
/*     */   private CubeCubeCollisionAlgorithm.OuterSegmentHandler outerSegmentHandler;
/*     */   private CubeCubeCollisionAlgorithm.InnerSegmentHandler innerSegmentHandler;
/* 215 */   static float margin = 0.05F;
/*     */ 
/* 217 */   private static ThreadLocal threadLocal = new CubeCubeCollisionAlgorithm.1();
/*     */   public CubeCubeCollisionVariableSet v;
/*     */   private int aabbBlockOverlapping;
/*     */   private int narrowTime;
/*     */   private long octATreeTime;
/*     */   private int leaftCallsA;
/*     */   private long leafRetrieve;
/*     */   private long leafAABBTest;
/*     */   private long currentTime;
/*     */   public boolean localSwap;
/*     */ 
/*     */   public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 246 */     this.handles = 0;
/*     */ 
/* 287 */     if ((!(paramCollisionObject1.getCollisionShape() instanceof CubeShape)) || (!(paramCollisionObject2.getCollisionShape() instanceof CubeShape)))
/*     */     {
/* 289 */       return;
/*     */     }
/* 291 */     if ((paramCollisionObject1.isStaticObject()) && (paramCollisionObject2.isStaticObject())) {
/* 292 */       return;
/*     */     }
/*     */ 
/* 295 */     if (this.localSwap) {
/* 296 */       System.err.println("LOCAL SWAP IN CUBE CUBE");
/* 297 */       localObject = paramCollisionObject1;
/* 298 */       paramCollisionObject1 = paramCollisionObject2;
/* 299 */       paramCollisionObject2 = (CollisionObject)localObject;
/*     */     }
/*     */ 
/* 302 */     this.currentTime = System.currentTimeMillis();
/* 303 */     this.narrowTime = 0;
/* 304 */     this.octATreeTime = 0L;
/* 305 */     this.aabbBlockOverlapping = 0;
/* 306 */     this.leaftCallsA = 0;
/* 307 */     this.leafRetrieve = 0L;
/* 308 */     this.leafAABBTest = 0L;
/* 309 */     Object localObject = (CubeShape)paramCollisionObject1.getCollisionShape();
/* 310 */     CubeShape localCubeShape = (CubeShape)paramCollisionObject2.getCollisionShape();
/*     */ 
/* 312 */     if (localObject == localCubeShape) {
/* 313 */       System.err.println("EUALCOL " + ((CubeShape)localObject).getSegmentBuffer().a());
/* 314 */       return;
/*     */     }
/*     */ 
/* 319 */     assert (((CubeShape)localObject).getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape == localObject) : (((CubeShape)localObject).getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape + " -- " + localObject);
/*     */ 
/* 322 */     assert (localCubeShape.getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape == localCubeShape) : (localCubeShape.getSegmentBuffer().a().getPhysicsDataContainer().getShapeChild().childShape + " -- " + localCubeShape);
/*     */     PhysicsExt localPhysicsExt;
/* 334 */     if (!(
/* 334 */       localPhysicsExt = ((CubeShape)localObject).getSegmentBuffer().a().getPhysics())
/* 334 */       .getPhysicsExceptions().isEmpty()) {
/* 335 */       for (i = 0; i < localPhysicsExt.getPhysicsExceptions().size(); i++)
/*     */       {
/*     */         zU localzU;
/* 337 */         if ((((
/* 337 */           localzU = (zU)localPhysicsExt.getPhysicsExceptions().get(i)).a == 
/* 337 */           paramCollisionObject1) && (localzU.b == paramCollisionObject2)) || ((localzU.a == paramCollisionObject2) && (localzU.b == paramCollisionObject1))) {
/* 338 */           return;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 345 */     this.v.oSet = ArrayOctree.getSet(((CubeShape)localObject).getSegmentBuffer().a().isOnServer());
/*     */ 
/* 347 */     this.v.tmpTrans1 = paramCollisionObject1.getWorldTransform(this.v.tmpTrans1);
/* 348 */     this.v.tmpTrans2 = paramCollisionObject2.getWorldTransform(this.v.tmpTrans2);
/*     */ 
/* 352 */     this.v.absolute1.set(this.v.tmpTrans1.basis);
/* 353 */     MatrixUtil.absolute(this.v.absolute1);
/* 354 */     this.v.absolute2.set(this.v.tmpTrans2.basis);
/* 355 */     MatrixUtil.absolute(this.v.absolute2);
/*     */ 
/* 359 */     ((CubeShape)localObject).getSegmentBuffer().a().onProximity(localCubeShape.getSegmentBuffer().a());
/*     */ 
/* 365 */     localCubeShape.getSegmentBuffer().a().onProximity(((CubeShape)localObject).getSegmentBuffer().a());
/*     */ 
/* 370 */     CubeCubeCollisionAlgorithm.OuterSegmentHandler.access$402(this.outerSegmentHandler, (CubeShape)localObject);
/*     */ 
/* 374 */     CubeCubeCollisionAlgorithm.OuterSegmentHandler.access$502(this.outerSegmentHandler, localCubeShape);
/*     */ 
/* 376 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$602(this.innerSegmentHandler, (CubeShape)localObject);
/* 377 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$702(this.innerSegmentHandler, localCubeShape);
/* 378 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$802(this.innerSegmentHandler, paramDispatcherInfo);
/* 379 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$902(this.innerSegmentHandler, paramManifoldResult);
/* 380 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1002(this.innerSegmentHandler, 0);
/* 381 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1102(this.innerSegmentHandler, 0L);
/*     */ 
/* 393 */     ((CubeShape)localObject).getAabb(this.v.tmpTrans1, this.v.outer.a, this.v.outer.b);
/* 394 */     localCubeShape.getAabb(this.v.tmpTrans2, this.v.inner.a, this.v.inner.b);
/*     */ 
/* 401 */     if (this.v.inner.a(this.v.outer, this.v.outBB) == null)
/*     */     {
/* 403 */       return;
/*     */     }
/*     */ 
/* 408 */     this.v.outBBCopy.a(this.v.outBB);
/*     */ 
/* 410 */     paramCollisionObject1.getWorldTransform(this.v.wtInv0);
/* 411 */     this.v.wtInv0.inverse();
/* 412 */     AabbUtil2.transformAabb(this.v.outBB.a, this.v.outBB.b, 0.5F, this.v.wtInv0, this.v.outBB.a, this.v.outBB.b);
/*     */ 
/* 419 */     this.v.minIntA.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/* 420 */     this.v.minIntA.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/* 421 */     this.v.minIntA.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/*     */ 
/* 423 */     this.v.maxIntA.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/* 424 */     this.v.maxIntA.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/* 425 */     this.v.maxIntA.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/*     */ 
/* 428 */     this.v.outBB.a(this.v.outBBCopy);
/*     */ 
/* 430 */     paramCollisionObject2.getWorldTransform(this.v.wtInv1);
/* 431 */     this.v.wtInv1.inverse();
/* 432 */     AabbUtil2.transformAabb(this.v.outBB.a, this.v.outBB.b, 0.5F, this.v.wtInv1, this.v.outBB.a, this.v.outBB.b);
/*     */ 
/* 437 */     this.v.minIntB.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/* 438 */     this.v.minIntB.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/* 439 */     this.v.minIntB.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/*     */ 
/* 441 */     this.v.maxIntB.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/* 442 */     this.v.maxIntB.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/* 443 */     this.v.maxIntB.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/*     */ 
/* 447 */     paramDispatcherInfo = Math.abs(ByteUtil.a(this.v.maxIntA.a) - ByteUtil.a(this.v.minIntA.a)) * 
/* 446 */       Math.abs(ByteUtil.a(this.v.maxIntA.b) - ByteUtil.a(this.v.minIntA.b)) * 
/* 447 */       Math.abs(ByteUtil.a(this.v.maxIntA.c) - ByteUtil.a(this.v.minIntA.c));
/*     */ 
/* 451 */     DispatcherInfo localDispatcherInfo = Math.abs(ByteUtil.a(this.v.maxIntB.a) - ByteUtil.a(this.v.minIntB.a)) * 
/* 450 */       Math.abs(ByteUtil.a(this.v.maxIntB.b) - ByteUtil.a(this.v.minIntB.b)) * 
/* 451 */       Math.abs(ByteUtil.a(this.v.maxIntB.c) - ByteUtil.a(this.v.minIntB.c));
/*     */ 
/* 454 */     long l2 = System.currentTimeMillis();
/*     */     try {
/* 456 */       ((CubeShape)localObject).getSegmentBuffer().b(this.outerSegmentHandler, this.v.minIntA, this.v.maxIntA);
/*     */     }
/*     */     catch (Exception localException) {
/* 459 */       localException.printStackTrace();
/*     */     }
/*     */ 
/* 463 */     int i = CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1000(this.innerSegmentHandler);
/* 464 */     long l1 = CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$1100(this.innerSegmentHandler);
/*     */     int j;
/* 472 */     if ((
/* 472 */       j = (int)(System.currentTimeMillis() - l2)) > 
/* 472 */       4) {
/* 473 */       if (this.aabbBlockOverlapping > ((CubeShape)localObject).getSegmentBuffer().a().getTotalElements() << 1) {
/* 474 */         if (paramCollisionObject1.isStaticObject())
/* 475 */           localCubeShape.getSegmentBuffer().a().flagPhysicsSlowdown();
/*     */         else {
/* 477 */           ((CubeShape)localObject).getSegmentBuffer().a().flagPhysicsSlowdown();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 482 */       System.err.println("[CUBECUBE] CUBECUBE COL: " + j + " ms; NarTests: " + i + "; overlappingSingleBlockAABBs: " + this.aabbBlockOverlapping + "; handle: " + this.handles + " of MAX " + paramDispatcherInfo * localDispatcherInfo + "; DistiTime: " + l1 / 1000000L + ";NarrTime: " + this.narrowTime / 1000000 + "; Oct: " + this.octATreeTime / 1000000L + " call(" + this.leaftCallsA + ") retr(" + this.leafRetrieve / 1000000L + ") aabb(" + this.leafAABBTest / 1000000L + "); on " + ((CubeShape)localObject).getSegmentBuffer().a() + ": COUNT " + ((CubeShape)localObject).getSegmentBuffer().c() + "; TOTEl " + ((CubeShape)localObject).getSegmentBuffer().a().getTotalElements() + "; " + localCubeShape.getSegmentBuffer().a() + ": COUNT " + localCubeShape.getSegmentBuffer().c() + "; TOTEl " + localCubeShape.getSegmentBuffer().a().getTotalElements() + " on " + localCubeShape.getSegmentBuffer().a().getState() + ";OverlappingBOX A " + this.v.minIntA + "-" + this.v.maxIntA + "; B " + this.v.minIntB + "-" + this.v.maxIntB);
/*     */ 
/* 490 */       System.err.println("[CC]INFO: " + paramCollisionObject1 + ": " + paramCollisionObject1.getWorldTransform(new Transform()).origin + "; " + paramCollisionObject2 + ": " + paramCollisionObject2.getWorldTransform(new Transform()).origin + " UPDATE#: " + ((CubeShape)localObject).getSegmentBuffer().a().getState().getUpdateNumber());
/*     */     }
/*     */ 
/* 517 */     if (this.ownManifold)
/* 518 */       paramManifoldResult.refreshContactPoints();
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 527 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 532 */     if (this.ownManifold) {
/* 533 */       if (this.manifoldPtr != null) {
/* 534 */         this.dispatcher.releaseManifold(this.manifoldPtr);
/*     */       }
/* 536 */       this.manifoldPtr = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void doExtCubeCubeCollision(BoxShape paramBoxShape1, BoxShape paramBoxShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, DispatcherInfo paramDispatcherInfo)
/*     */   {
/*     */     DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
/* 546 */     (
/* 547 */       localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get())
/* 547 */       .init();
/*     */ 
/* 550 */     localClosestPointInput.maximumDistanceSquared = (paramBoxShape1.getMargin() + paramBoxShape2.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
/*     */ 
/* 552 */     localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
/*     */ 
/* 554 */     localClosestPointInput.transformA.set(paramTransform1);
/* 555 */     localClosestPointInput.transformB.set(paramTransform2);
/* 556 */     this.boxboxPairDetector.GetClosestPoints(paramBoxShape1, paramBoxShape2, localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw, false);
/*     */ 
/* 558 */     this.pointInputsPool.release(localClosestPointInput);
/*     */   }
/*     */ 
/*     */   private void doNonBlockCollision(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, Transform paramTransform1, Transform paramTransform2, ManifoldResult paramManifoldResult, DispatcherInfo paramDispatcherInfo)
/*     */   {
/*     */     DiscreteCollisionDetectorInterface.ClosestPointInput localClosestPointInput;
/* 567 */     (
/* 568 */       localClosestPointInput = (DiscreteCollisionDetectorInterface.ClosestPointInput)this.pointInputsPool.get())
/* 568 */       .init();
/*     */ 
/* 570 */     paramManifoldResult.setPersistentManifold(this.manifoldPtr);
/*     */ 
/* 573 */     this.gjkPairDetector.setMinkowskiA(paramConvexShape1);
/* 574 */     this.gjkPairDetector.setMinkowskiB(paramConvexShape2);
/*     */ 
/* 576 */     localClosestPointInput.maximumDistanceSquared = (paramConvexShape1.getMargin() + paramConvexShape2.getMargin() + this.manifoldPtr.getContactBreakingThreshold());
/*     */ 
/* 578 */     localClosestPointInput.maximumDistanceSquared *= localClosestPointInput.maximumDistanceSquared;
/*     */ 
/* 583 */     localClosestPointInput.transformA.set(paramTransform1);
/* 584 */     localClosestPointInput.transformB.set(paramTransform2);
/* 585 */     this.gjkPairDetector.getClosestPoints(localClosestPointInput, paramManifoldResult, paramDispatcherInfo.debugDraw);
/*     */ 
/* 588 */     this.pointInputsPool.release(localClosestPointInput);
/*     */   }
/*     */ 
/*     */   private void doNarrowTest(SegmentData paramSegmentData1, SegmentData paramSegmentData2, o paramo1, o paramo2, o paramo3, o paramo4, int paramInt1, int paramInt2, xO paramxO, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 639 */     paramo2 = org.schema.game.common.data.physics.octree.ArrayOctreeTraverse.tMap[paramInt1];
/* 640 */     paramo4 = org.schema.game.common.data.physics.octree.ArrayOctreeTraverse.tMap[paramInt2];
/* 641 */     for (paramInt1 = 0; paramInt1 < paramo2.length; paramInt1++)
/*     */     {
/* 643 */       paramInt2 = (byte)(paramo1.a + paramo2[paramInt1][0]);
/* 644 */       int i = (byte)(paramo1.b + paramo2[paramInt1][1]);
/* 645 */       int j = (byte)(paramo1.c + paramo2[paramInt1][2]);
/*     */ 
/* 649 */       int k = SegmentData.getInfoIndex((byte)(paramInt2 + 8), (byte)(i + 8), (byte)(j + 8));
/*     */       ElementInformation localElementInformation1;
/* 656 */       if ((paramSegmentData1.containsFast(k)) && ((localElementInformation1 = ElementKeyMap.getInfo(paramSegmentData1.getType(k))).isPhysical(paramSegmentData1.isActive(k)))) {
/* 657 */         byte b1 = paramSegmentData1.getOrientation(k);
/* 658 */         boolean bool1 = paramSegmentData1.isActive(k);
/*     */ 
/* 660 */         this.v.elemPosA.set(paramInt2 + paramSegmentData1.getSegment().a.a, i + paramSegmentData1.getSegment().a.b, j + paramSegmentData1.getSegment().a.c);
/*     */ 
/* 665 */         this.v.elemPosAAbs.set(this.v.elemPosA);
/* 666 */         this.v.tmpTrans1.transform(this.v.elemPosAAbs);
/* 667 */         this.v.min.set(this.v.elemPosA.x - 0.5F, this.v.elemPosA.y - 0.5F, this.v.elemPosA.z - 0.5F);
/*     */ 
/* 671 */         this.v.max.set(this.v.elemPosA.x + 0.5F, this.v.elemPosA.y + 0.5F, this.v.elemPosA.z + 0.5F);
/*     */ 
/* 677 */         CubeShape.transformAabb(this.v.min, this.v.max, margin, this.v.tmpTrans1, this.v.minOut, this.v.maxOut, this.v.aabbVarSet, this.v.absolute1);
/*     */ 
/* 683 */         if (AabbUtil2.testAabbAgainstAabb2(paramxO.a, paramxO.b, this.v.minOut, this.v.maxOut))
/*     */         {
/* 686 */           for (paramInt2 = 0; paramInt2 < paramo4.length; 
/* 689 */             paramInt2++)
/*     */           {
/* 691 */             i = (byte)(paramo3.a + paramo4[paramInt2][0]);
/* 692 */             j = (byte)(paramo3.b + paramo4[paramInt2][1]);
/* 693 */             int m = (byte)(paramo3.c + paramo4[paramInt2][2]);
/*     */ 
/* 695 */             int n = SegmentData.getInfoIndex((byte)(i + 8), (byte)(j + 8), (byte)(m + 8));
/*     */             ElementInformation localElementInformation2;
/* 702 */             if ((paramSegmentData2.containsFast(n)) && ((localElementInformation2 = ElementKeyMap.getInfo(paramSegmentData2.getType(n))).isPhysical(paramSegmentData2.isActive(n)))) {
/* 703 */               byte b2 = paramSegmentData2.getOrientation(n);
/* 704 */               boolean bool2 = paramSegmentData2.isActive(n);
/*     */ 
/* 706 */               this.v.elemPosB.set(i + paramSegmentData2.getSegment().a.a, j + paramSegmentData2.getSegment().a.b, m + paramSegmentData2.getSegment().a.c);
/*     */ 
/* 711 */               this.v.elemPosBAbs.set(this.v.elemPosB);
/* 712 */               this.v.tmpTrans2.transform(this.v.elemPosBAbs);
/*     */ 
/* 714 */               this.v.elemPosBAbs.sub(this.v.elemPosAAbs);
/*     */ 
/* 716 */               if ((this.v.elemPosBAbs.length() < 1.5F ? 1 : 0) != 0)
/*     */               {
/* 718 */                 this.aabbBlockOverlapping += 1;
/* 719 */                 this.v.tmpTrans3.set(this.v.tmpTrans1);
/* 720 */                 this.v.tmpTrans4.set(this.v.tmpTrans2);
/*     */ 
/* 722 */                 this.v.nA.set(this.v.elemPosA);
/* 723 */                 this.v.nB.set(this.v.elemPosB);
/* 724 */                 this.v.tmpTrans3.basis.transform(this.v.nA);
/* 725 */                 this.v.tmpTrans4.basis.transform(this.v.nB);
/*     */ 
/* 727 */                 this.v.tmpTrans3.origin.add(this.v.nA);
/* 728 */                 this.v.tmpTrans4.origin.add(this.v.nB);
/* 729 */                 paramSegmentData1.getSegment(); Segment.d();
/* 730 */                 paramSegmentData2.getSegment(); Segment.d();
/* 731 */                 this.v.box0.setMargin(margin);
/* 732 */                 this.v.box1.setMargin(margin);
/*     */ 
/* 736 */                 Object localObject1 = this.v.box0;
/* 737 */                 Object localObject2 = this.v.box1;
/* 738 */                 m = 0;
/* 739 */                 if ((localElementInformation1.getBlockStyle() > 0) && (localElementInformation1.getBlockStyle() < 3))
/*     */                 {
/* 741 */                   localObject1 = dO.a(localElementInformation1.getBlockStyle(), b1, bool1);
/* 742 */                   m = 1;
/*     */                 }
/* 744 */                 if ((localElementInformation2.getBlockStyle() > 0) && (localElementInformation2.getBlockStyle() < 3))
/*     */                 {
/* 746 */                   localObject2 = dO.a(localElementInformation2.getBlockStyle(), b2, bool2);
/* 747 */                   m = 1;
/*     */                 }
/*     */ 
/* 751 */                 if (m != 0) {
/* 752 */                   doNonBlockCollision((ConvexShape)localObject1, (ConvexShape)localObject2, this.v.tmpTrans3, this.v.tmpTrans4, paramManifoldResult, paramDispatcherInfo);
/*     */                 }
/*     */                 else
/*     */                 {
/* 759 */                   doExtCubeCubeCollision(this.v.box0, this.v.box1, this.v.tmpTrans3, this.v.tmpTrans4, paramManifoldResult, paramDispatcherInfo);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList paramObjectArrayList)
/*     */   {
/* 871 */     if ((this.manifoldPtr != null) && (this.ownManifold))
/* 872 */       paramObjectArrayList.add(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public void init(CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo)
/*     */   {
/* 880 */     super.init(paramCollisionAlgorithmConstructionInfo);
/*     */   }
/*     */ 
/*     */   public void init(PersistentManifold paramPersistentManifold, CollisionAlgorithmConstructionInfo paramCollisionAlgorithmConstructionInfo, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, SimplexSolverInterface paramSimplexSolverInterface, GjkEpaPenetrationDepthSolver paramGjkEpaPenetrationDepthSolver)
/*     */   {
/* 889 */     super.init(paramCollisionAlgorithmConstructionInfo);
/* 890 */     this.v = ((CubeCubeCollisionVariableSet)threadLocal.get());
/* 891 */     this.gjkPairDetector = new GjkPairDetectorExt(this.v.gjkVar);
/* 892 */     this.outerSegmentHandler = new CubeCubeCollisionAlgorithm.OuterSegmentHandler(this, null);
/* 893 */     this.innerSegmentHandler = new CubeCubeCollisionAlgorithm.InnerSegmentHandler(this, null);
/* 894 */     this.manifoldPtr = paramPersistentManifold;
/* 895 */     this.gjkPairDetector.init(null, null, paramSimplexSolverInterface, paramGjkEpaPenetrationDepthSolver);
/* 896 */     if (this.manifoldPtr == null) {
/* 897 */       this.manifoldPtr = this.dispatcher.getNewManifold(paramCollisionObject1, paramCollisionObject2);
/* 898 */       this.ownManifold = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void processDistinctCollision(CubeShape paramCubeShape1, CubeShape paramCubeShape2, SegmentData paramSegmentData1, SegmentData paramSegmentData2, Transform paramTransform1, Transform paramTransform2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 908 */     if (!this.v.intersectionCallBackAwithB.initialized) {
/* 909 */       this.v.intersectionCallBackAwithB.createHitCache(512);
/* 910 */       this.v.intersectionCallBackBwithA.createHitCache(512);
/*     */     }
/*     */ 
/* 921 */     this.v.intersectionCallBackAwithB.reset();
/* 922 */     this.v.intersectionCallBackBwithA.reset();
/*     */ 
/* 925 */     long l1 = System.nanoTime();
/*     */ 
/* 929 */     this.v.intersectionCallBackAwithB = paramSegmentData1.getOctree().findIntersecting(this.v.oSet, this.v.intersectionCallBackAwithB, paramSegmentData1.getSegment(), paramTransform1, this.v.absolute1, margin, this.v.bbSectorIntersection.a, this.v.bbSectorIntersection.b, 1.0F);
/*     */ 
/* 933 */     this.leafAABBTest += this.v.intersectionCallBackAwithB.aabbTest;
/* 934 */     this.leafRetrieve += this.v.intersectionCallBackAwithB.aabbRetrieve;
/* 935 */     this.leaftCallsA += this.v.intersectionCallBackAwithB.leafCalcs;
/*     */ 
/* 938 */     if (this.v.intersectionCallBackAwithB.hitCount == 0) {
/* 939 */       return;
/*     */     }
/* 941 */     this.v.intersectionCallBackBwithA.reset();
/*     */ 
/* 943 */     this.v.oSet.debug = paramSegmentData2.getSegmentController().isOnServer();
/* 944 */     this.v.intersectionCallBackBwithA = paramSegmentData2.getOctree().findIntersecting(this.v.oSet, this.v.intersectionCallBackBwithA, paramSegmentData2.getSegment(), paramTransform2, this.v.absolute2, margin, this.v.bbSectorIntersection.a, this.v.bbSectorIntersection.b, 1.0F);
/*     */ 
/* 947 */     this.v.oSet.debug = false;
/* 948 */     if (this.v.intersectionCallBackBwithA.hitCount == 0) {
/* 949 */       return;
/*     */     }
/*     */ 
/* 952 */     for (paramCubeShape1 = 0; paramCubeShape1 < this.v.intersectionCallBackAwithB.hitCount; 
/* 953 */       paramCubeShape1++) {
/* 954 */       paramCubeShape2 = this.v.intersectionCallBackAwithB.getHit(paramCubeShape1, this.v.bbOuterOct.a, this.v.bbOuterOct.b, this.v.startA, this.v.endA);
/*     */ 
/* 960 */       for (paramTransform1 = 0; paramTransform1 < this.v.intersectionCallBackBwithA.hitCount; paramTransform1++)
/*     */       {
/* 962 */         paramTransform2 = this.v.intersectionCallBackBwithA.getHit(paramTransform1, this.v.bbInnerOct.a, this.v.bbInnerOct.b, this.v.startB, this.v.endB);
/*     */ 
/* 964 */         if (AabbUtil2.testAabbAgainstAabb2(this.v.bbInnerOct.a, this.v.bbInnerOct.b, this.v.bbOuterOct.a, this.v.bbOuterOct.b)) {
/* 965 */           this.v.bbInnerOct.a(this.v.bbOuterOct, this.v.bbOctIntersection);
/*     */ 
/* 967 */           long l2 = System.nanoTime();
/*     */ 
/* 969 */           doNarrowTest(paramSegmentData1, paramSegmentData2, this.v.startA, this.v.endA, this.v.startB, this.v.endB, paramCubeShape2, paramTransform2, this.v.bbOctIntersection, paramDispatcherInfo, paramManifoldResult);
/*     */ 
/* 971 */           this.narrowTime = ((int)(this.narrowTime + (System.nanoTime() - l2)));
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 977 */     this.octATreeTime += System.nanoTime() - l1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */