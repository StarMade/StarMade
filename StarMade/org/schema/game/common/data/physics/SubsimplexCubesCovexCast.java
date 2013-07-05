/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.GjkConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ import com.bulletphysics.collision.shapes.BoxShape;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import dO;
/*     */ import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import ld;
/*     */ import o;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ShieldContainerInterface;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
/*     */ import org.schema.game.common.controller.elements.shield.ShieldUnit;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.physics.octree.ArrayOctree;
/*     */ import org.schema.game.common.data.physics.octree.IntersectionCallback;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import q;
/*     */ import s;
/*     */ import xO;
/*     */ 
/*     */ public class SubsimplexCubesCovexCast extends ConvexCast
/*     */ {
/* 131 */   private static ThreadLocal threadLocal = new SubsimplexCubesCovexCast.1();
/*     */   public static String mode;
/* 138 */   ObjectPool pool = ObjectPool.get(o.class);
/* 139 */   ObjectPool pool4 = ObjectPool.get(s.class);
/*     */ 
/* 143 */   ObjectPool aabbpool = ObjectPool.get(AABBb.class);
/*     */   private CubeConvexCastVariableSet v;
/*     */   CubeRayCastResult rayResult;
/*     */   CollisionWorld.ConvexResultCallback resultCallback;
/*     */   private SubsimplexCubesCovexCast.OuterSegmentHandler outerSegmentHandler;
/*     */   private long time;
/*     */ 
/*     */   public SubsimplexCubesCovexCast(ConvexShape paramConvexShape, CollisionShape paramCollisionShape, CollisionObject paramCollisionObject, SimplexSolverInterface paramSimplexSolverInterface, CollisionWorld.ConvexResultCallback paramConvexResultCallback, CubeRayCastResult paramCubeRayCastResult)
/*     */   {
/* 153 */     this.v = ((CubeConvexCastVariableSet)threadLocal.get());
/* 154 */     this.v.shapeA = paramConvexShape;
/* 155 */     this.v.cubesB = ((CubeShape)paramCollisionShape);
/* 156 */     this.v.cubesObject = paramCollisionObject;
/* 157 */     this.resultCallback = paramConvexResultCallback;
/* 158 */     this.v.simplexSolver = paramSimplexSolverInterface;
/* 159 */     this.rayResult = paramCubeRayCastResult;
/* 160 */     this.outerSegmentHandler = new SubsimplexCubesCovexCast.OuterSegmentHandler(this, null);
/*     */   }
/*     */ 
/*     */   public boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
/*     */   {
/* 172 */     this.v.sorted.clear();
/*     */ 
/* 175 */     this.time = System.currentTimeMillis();
/*     */ 
/* 177 */     this.v.shapeA.getAabb(paramTransform1, this.v.convexFromAABBMin, this.v.convexFromAABBMax);
/* 178 */     this.v.shapeA.getAabb(paramTransform2, this.v.convexToAABBMin, this.v.convexToAABBMax);
/*     */ 
/* 182 */     combineAabb(this.v.convexFromAABBMin, this.v.convexFromAABBMax, this.v.convexToAABBMin, this.v.convexToAABBMax, this.v.castedAABBMin, this.v.castedAABBMax);
/*     */ 
/* 184 */     this.v.castedAABBMin.sub(new Vector3f(0.2F, 0.2F, 0.2F));
/* 185 */     this.v.castedAABBMax.add(new Vector3f(0.2F, 0.2F, 0.2F));
/*     */ 
/* 202 */     SubsimplexCubesCovexCast.OuterSegmentHandler.access$302(this.outerSegmentHandler, paramTransform1);
/* 203 */     SubsimplexCubesCovexCast.OuterSegmentHandler.access$402(this.outerSegmentHandler, paramCastResult);
/* 204 */     SubsimplexCubesCovexCast.OuterSegmentHandler.access$502(this.outerSegmentHandler, paramTransform3);
/* 205 */     SubsimplexCubesCovexCast.OuterSegmentHandler.access$602(this.outerSegmentHandler, paramTransform2);
/* 206 */     SubsimplexCubesCovexCast.OuterSegmentHandler.access$702(this.outerSegmentHandler, false);
/*     */ 
/* 212 */     this.v.cubesB.getAabb(paramTransform3, this.v.outer.a, this.v.outer.b);
/* 213 */     this.v.inner.a.set(this.v.castedAABBMin);
/* 214 */     this.v.inner.b.set(this.v.castedAABBMax);
/*     */ 
/* 217 */     if ((this.v.inner.a(this.v.outer, this.v.outBB) == null) || 
/* 217 */       (!this.v.outBB.b()))
/*     */     {
/* 219 */       return false;
/*     */     }
/* 221 */     this.v.inv.set(paramTransform3);
/* 222 */     this.v.inv.inverse();
/* 223 */     AabbUtil2.transformAabb(new Vector3f(this.v.outBB.a), new Vector3f(this.v.outBB.b), 0.5F, this.v.inv, this.v.outBB.a, this.v.outBB.b);
/*     */ 
/* 228 */     this.v.minIntA.a = (ByteUtil.a((int)(this.v.outBB.a.x - 8.0F)) << 4);
/* 229 */     this.v.minIntA.b = (ByteUtil.a((int)(this.v.outBB.a.y - 8.0F)) << 4);
/* 230 */     this.v.minIntA.c = (ByteUtil.a((int)(this.v.outBB.a.z - 8.0F)) << 4);
/*     */ 
/* 232 */     this.v.maxIntA.a = (FastMath.b((this.v.outBB.b.x + 8.0F) / 16.0F) << 4);
/* 233 */     this.v.maxIntA.b = (FastMath.b((this.v.outBB.b.y + 8.0F) / 16.0F) << 4);
/* 234 */     this.v.maxIntA.c = (FastMath.b((this.v.outBB.b.z + 8.0F) / 16.0F) << 4);
/*     */     long l;
/* 238 */     if ((
/* 238 */       l = (this.v.maxIntA.a - this.v.minIntA.a) * (this.v.maxIntA.b - this.v.minIntA.b) * (this.v.maxIntA.c - this.v.minIntA.c) / 16) > 
/* 238 */       10000L) {
/* 239 */       System.err.println("[SubSimplexConvexCubes][WARNING] more then 10000 segments to test: " + l + " -> intersection [" + this.v.minIntA + ", " + this.v.maxIntA + "]");
/*     */     }
/*     */ 
/* 242 */     this.v.absolute1.set(paramTransform3.basis);
/* 243 */     MatrixUtil.absolute(this.v.absolute1);
/* 244 */     this.v.cubesB.getSegmentBuffer().b(this.outerSegmentHandler, this.v.minIntA, this.v.maxIntA);
/*     */ 
/* 246 */     if (SubsimplexCubesCovexCast.OuterSegmentHandler.access$700(this.outerSegmentHandler))
/*     */     {
/* 248 */       return true;
/*     */     }
/*     */ 
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/*     */   {
/* 262 */     paramDispatcherInfo = 1.0F;
/*     */ 
/* 264 */     this.v.tmp.sub(paramCollisionObject1.getInterpolationWorldTransform(this.v.tmpTrans1).origin, paramCollisionObject1.getWorldTransform(this.v.tmpTrans2).origin);
/* 265 */     paramManifoldResult = this.v.tmp.lengthSquared();
/*     */ 
/* 267 */     this.v.tmp.sub(paramCollisionObject2.getInterpolationWorldTransform(this.v.tmpTrans1).origin, paramCollisionObject2.getWorldTransform(this.v.tmpTrans2).origin);
/* 268 */     float f = this.v.tmp.lengthSquared();
/*     */ 
/* 270 */     if ((paramManifoldResult < paramCollisionObject1.getCcdSquareMotionThreshold()) && (f < paramCollisionObject2.getCcdSquareMotionThreshold()))
/*     */     {
/* 272 */       return 1.0F;
/*     */     }
/*     */ 
/* 275 */     if (this.v.disableCcd) {
/* 276 */       return 1.0F;
/*     */     }
/*     */ 
/* 289 */     paramManifoldResult = (ConvexShape)paramCollisionObject1.getCollisionShape();
/*     */ 
/* 291 */     SphereShape localSphereShape = new SphereShape(paramCollisionObject2.getCcdSweptSphereRadius());
/* 292 */     ConvexCast.CastResult localCastResult = new ConvexCast.CastResult();
/* 293 */     Object localObject = new VoronoiSimplexSolverExt();
/*     */ 
/* 298 */     if (new GjkConvexCast(paramManifoldResult, localSphereShape, (SimplexSolverInterface)localObject)
/* 298 */       .calcTimeOfImpact(paramCollisionObject1.getWorldTransform(this.v.tmpTrans1), paramCollisionObject1.getInterpolationWorldTransform(this.v.tmpTrans2), paramCollisionObject2.getWorldTransform(this.v.tmpTrans3), paramCollisionObject2.getInterpolationWorldTransform(this.v.tmpTrans4), localCastResult))
/*     */     {
/* 302 */       if (paramCollisionObject1.getHitFraction() > localCastResult.fraction) {
/* 303 */         paramCollisionObject1.setHitFraction(localCastResult.fraction);
/*     */       }
/*     */ 
/* 306 */       if (paramCollisionObject2.getHitFraction() > localCastResult.fraction) {
/* 307 */         paramCollisionObject2.setHitFraction(localCastResult.fraction);
/*     */       }
/*     */ 
/* 310 */       if (1.0F > localCastResult.fraction) {
/* 311 */         paramDispatcherInfo = localCastResult.fraction;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 318 */     paramManifoldResult = (ConvexShape)paramCollisionObject2.getCollisionShape();
/*     */ 
/* 320 */     localSphereShape = new SphereShape(paramCollisionObject1.getCcdSweptSphereRadius());
/* 321 */     localCastResult = new ConvexCast.CastResult();
/* 322 */     localObject = new VoronoiSimplexSolver();
/*     */ 
/* 327 */     if (new GjkConvexCast(localSphereShape, paramManifoldResult, (SimplexSolverInterface)localObject)
/* 327 */       .calcTimeOfImpact(paramCollisionObject1.getWorldTransform(this.v.tmpTrans1), paramCollisionObject1.getInterpolationWorldTransform(this.v.tmpTrans2), paramCollisionObject2.getWorldTransform(this.v.tmpTrans3), paramCollisionObject2.getInterpolationWorldTransform(this.v.tmpTrans4), localCastResult))
/*     */     {
/* 331 */       if (paramCollisionObject1.getHitFraction() > localCastResult.fraction) {
/* 332 */         paramCollisionObject1.setHitFraction(localCastResult.fraction);
/*     */       }
/*     */ 
/* 335 */       if (paramCollisionObject2.getHitFraction() > localCastResult.fraction) {
/* 336 */         paramCollisionObject2.setHitFraction(localCastResult.fraction);
/*     */       }
/*     */ 
/* 339 */       if (paramDispatcherInfo > localCastResult.fraction) {
/* 340 */         paramDispatcherInfo = localCastResult.fraction;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 346 */     return paramDispatcherInfo;
/*     */   }
/*     */ 
/*     */   private boolean checkExplicitCollision(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentController paramSegmentController, ConvexCast.CastResult paramCastResult)
/*     */   {
/* 352 */     if (((paramSegmentController instanceof ld)) && ((((ld)paramSegmentController).a() instanceof ShieldContainerInterface)))
/*     */     {
/* 354 */       for (paramSegmentController = ((ShieldContainerInterface)((ld)paramSegmentController).a())
/* 354 */         .getShieldManager().getCollection().iterator(); paramSegmentController.hasNext(); )
/*     */       {
/*     */         ShieldUnit localShieldUnit;
/* 355 */         if (((ShieldUnit)(
/* 355 */           localShieldUnit = (ShieldUnit)paramSegmentController.next()))
/* 355 */           .getShields() <= 0)
/*     */         {
/* 357 */           return false;
/*     */         }
/* 359 */         this.v.tmpTrans.set(paramTransform3);
/* 360 */         localShieldUnit.getOpenGLCenter(this.v.fromHelp);
/*     */ 
/* 362 */         this.v.tmpTrans.basis.transform(this.v.fromHelp);
/*     */ 
/* 364 */         this.v.tmpTrans.origin.add(this.v.fromHelp);
/*     */ 
/* 366 */         this.v.simplexSolver.reset();
/*     */ 
/* 368 */         this.v.sphereShape.setRadius(localShieldUnit.getRadius());
/*     */ 
/* 370 */         SubsimplexConvexCast localSubsimplexConvexCast = new SubsimplexConvexCast(this.v.shapeA, this.v.sphereShape, this.v.simplexSolver);
/* 371 */         this.v.sphereShape.getAabb(this.v.tmpTrans, this.v.outMin, this.v.outMax);
/*     */ 
/* 377 */         if (localSubsimplexConvexCast.calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans, this.v.tmpTrans, paramCastResult))
/*     */         {
/* 381 */           if (this.rayResult != null) {
/* 382 */             this.rayResult
/* 383 */               .setUserData(localShieldUnit);
/*     */           }
/* 385 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 389 */     return false;
/*     */   }
/*     */   private void combineAabb(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, Vector3f paramVector3f6) {
/* 392 */     paramVector3f5.x = Math.min(paramVector3f1.x, paramVector3f3.x);
/* 393 */     paramVector3f5.y = Math.min(paramVector3f1.y, paramVector3f3.y);
/* 394 */     paramVector3f5.z = Math.min(paramVector3f1.z, paramVector3f3.z);
/*     */ 
/* 396 */     paramVector3f6.x = Math.max(paramVector3f2.x, paramVector3f4.x);
/* 397 */     paramVector3f6.y = Math.max(paramVector3f2.y, paramVector3f4.y);
/* 398 */     paramVector3f6.z = Math.max(paramVector3f2.z, paramVector3f4.z);
/*     */   }
/*     */ 
/*     */   private boolean doNarrorTest(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentData paramSegmentData, ConvexCast.CastResult paramCastResult, o paramo1, o paramo2)
/*     */   {
/* 415 */     this.v.posCachePointer = 0;
/* 416 */     for (paramTransform2 = paramo1.a; paramTransform2 < paramo2.a; paramTransform2 = (byte)(paramTransform2 + 1)) {
/* 417 */       for (paramCastResult = paramo1.b; paramCastResult < paramo2.b; paramCastResult = (byte)(paramCastResult + 1)) {
/* 418 */         for (int i = paramo1.c; i < paramo2.c; i = (byte)(i + 1)) {
/* 419 */           this.v.elemA.b((byte)(paramTransform2 + 8), (byte)(paramCastResult + 8), (byte)(i + 8));
/*     */ 
/* 421 */           int j = SegmentData.getInfoIndex(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c);
/*     */           short s;
/*     */           ElementInformation localElementInformation;
/* 427 */           if (((
/* 427 */             s = paramSegmentData.getType(j)) != 0) && 
/* 427 */             ((localElementInformation = ElementKeyMap.getInfo(s)).isPhysical()) && ((s != 122) || (ElementKeyMap.getInfo(s).isPhysical(paramSegmentData.isActive(j)))))
/*     */           {
/* 429 */             s = paramSegmentData.getOrientation(j);
/* 430 */             boolean bool = paramSegmentData.isActive(j);
/*     */ 
/* 441 */             this.v.elemPosA.set(paramTransform2, paramCastResult, i);
/* 442 */             this.v.elemPosA.x += paramSegmentData.getSegment().a.a;
/* 443 */             this.v.elemPosA.y += paramSegmentData.getSegment().a.b;
/* 444 */             this.v.elemPosA.z += paramSegmentData.getSegment().a.c;
/*     */ 
/* 447 */             this.v.nA.set(this.v.elemPosA);
/* 448 */             this.v.tmpTrans.set(paramTransform3);
/* 449 */             this.v.tmpTrans.basis.transform(this.v.nA);
/* 450 */             this.v.tmpTrans.origin.add(this.v.nA);
/*     */ 
/* 452 */             this.v.box0.setMargin(0.3F);
/* 453 */             this.v.box0.getAabb(this.v.tmpTrans, this.v.outMin, this.v.outMax);
/*     */ 
/* 455 */             this.v.normal.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 468 */             if (AabbUtil2.testAabbAgainstAabb2(this.v.outMin, this.v.outMax, this.v.castedAABBMin, this.v.castedAABBMax))
/*     */             {
/*     */               Object localObject;
/* 469 */               if ((this.resultCallback instanceof ClosestConvexResultCallbackExt))
/*     */               {
/* 471 */                 if ((
/* 471 */                   localObject = (ClosestConvexResultCallbackExt)this.resultCallback).checkHasHitOnly)
/*     */                 {
/* 472 */                   ((ClosestConvexResultCallbackExt)localObject).userData = paramSegmentData.getSegmentController();
/* 473 */                   this.resultCallback.closestHitFraction = 0.5F;
/* 474 */                   return false;
/*     */                 }
/*     */ 
/*     */               }
/*     */ 
/* 483 */               this.v.distTest.set(paramTransform1.origin);
/* 484 */               this.v.distTest.sub(this.v.tmpTrans.origin);
/*     */ 
/* 486 */               (
/* 487 */                 localObject = (s)this.pool4.get())
/* 487 */                 .a(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c, s * 10 + localElementInformation.getBlockStyle() + (bool ? 1000 : 0));
/*     */ 
/* 489 */               float f = this.v.distTest.length();
/* 490 */               s = 0;
/* 491 */               while ((this.v.sorted.containsKey(f)) && (s < 100)) {
/* 492 */                 f += 0.1F;
/* 493 */                 s++;
/*     */               }
/* 495 */               if (s >= 100) {
/* 496 */                 System.err.println("[SUBSIMPLEX][WARNING] more than 100 tries in sorted");
/*     */               }
/* 498 */               this.v.sorted.put(f, localObject);
/* 499 */               this.v.posCachePointer += 1;
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 515 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean drawDebug() {
/* 519 */     return (this.v.cubesB.getSegmentBuffer().a().isOnServer()) && (mode.equals("UP")) && (Keyboard.isKeyDown(57)) && (!Keyboard.isKeyDown(29));
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 528 */     return this.v.cubesB.getSegmentBuffer().a().isOnServer();
/*     */   }
/*     */ 
/*     */   private boolean performCastTest(Segment paramSegment, ConvexShape paramConvexShape, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, ConvexCast.CastResult paramCastResult)
/*     */   {
/* 537 */     paramSegment = paramSegment.a();
/*     */ 
/* 539 */     if (!this.v.intersectionCallBack.initialized) {
/* 540 */       this.v.intersectionCallBack.createHitCache(512);
/*     */     }
/* 542 */     this.v.intersectionCallBack.reset();
/*     */ 
/* 545 */     this.v.intersectionCallBack = paramSegment.getOctree().findIntersecting(paramSegment.getOctree().getSet(), this.v.intersectionCallBack, paramSegment.getSegment(), paramTransform3, this.v.absolute1, 0.0F, this.v.castedAABBMin, this.v.castedAABBMax, 1.0F);
/*     */ 
/* 547 */     this.v.sortedAABB.clear();
/* 548 */     this.v.sorted.clear();
/*     */ 
/* 550 */     paramConvexShape = 0;
/*     */ 
/* 552 */     boolean bool1 = true;
/*     */     Object localObject2;
/*     */     Object localObject1;
/*     */     Iterator localIterator1;
/* 553 */     if (this.v.intersectionCallBack.hitCount > 0) {
/* 554 */       for (int i = 0; i < this.v.intersectionCallBack.hitCount; i++) {
/* 555 */         this.v.intersectionCallBack.getHit(i, this.v.minOut, this.v.maxOut, this.v.startOut, this.v.endOut);
/*     */ 
/* 557 */         this.v.dist.set(this.v.maxOut);
/* 558 */         this.v.dist.sub(this.v.minOut);
/* 559 */         float f1 = this.v.dist.length();
/* 560 */         this.v.dist.normalize();
/* 561 */         this.v.dist.scale(f1 / 2.0F);
/* 562 */         this.v.minOut.add(this.v.dist);
/*     */ 
/* 565 */         this.v.dist.sub(this.v.minOut, paramTransform1.origin);
/* 566 */         float f2 = this.v.dist.lengthSquared() * 1000.0F;
/* 567 */         int k = 0;
/* 568 */         while ((this.v.sortedAABB.containsKey(f2)) && (!Float.isNaN(f2)) && (!Float.isInfinite(f2)) && (k < 1000)) {
/* 569 */           f2 += 0.1F;
/* 570 */           k++;
/*     */         }
/* 572 */         if (k > 100) {
/* 573 */           System.err.println("[CubesConvex][WARNING] extended more then 100 AABBs length: " + this.v.sortedAABB.size() + ": " + paramConvexShape);
/*     */         }
/* 575 */         if ((!Float.isNaN(f2)) && (!Float.isInfinite(f2))) {
/* 576 */           if (paramConvexShape > 1000)
/*     */           {
/* 579 */             System.err.println("[CubesConvex][WARNING] testing more then 1000 AABBs: " + this.v.sortedAABB.size() + ": " + paramConvexShape);
/*     */           }
/* 581 */           localObject2 = (AABBb)this.aabbpool.get();
/* 582 */           localObject1 = (o)this.pool.get();
/* 583 */           o localo = (o)this.pool.get();
/* 584 */           ((o)localObject1).b(this.v.startOut);
/* 585 */           localo.b(this.v.endOut);
/* 586 */           ((AABBb)localObject2).min = ((o)localObject1);
/* 587 */           ((AABBb)localObject2).max = localo;
/* 588 */           this.v.sortedAABB.put(f2, localObject2);
/* 589 */           paramConvexShape++;
/*     */         }
/*     */       }
/* 591 */       if (this.v.sortedAABB.size() > 1000) {
/* 592 */         System.err.println("[CubesConvex][WARNING] testing more then 1000 AABBs: " + this.v.sortedAABB.size());
/*     */       }
/* 594 */       for (localIterator1 = this.v.sortedAABB.entrySet().iterator(); localIterator1.hasNext(); ) { localObject1 = (Map.Entry)localIterator1.next();
/* 595 */         if (!bool1) break;
/* 596 */         bool1 = doNarrorTest(paramTransform1, paramTransform2, paramTransform3, paramSegment, paramCastResult, ((AABBb)((Map.Entry)localObject1).getValue()).min, ((AABBb)((Map.Entry)localObject1).getValue()).max);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 609 */     if (!bool1) {
/* 610 */       return true;
/*     */     }
/* 612 */     if (this.v.sorted.size() > 1000) {
/* 613 */       System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "][CubeConvex] DOING " + this.v.sorted.size() + " box tests");
/*     */     }
/* 615 */     boolean bool2 = false;
/*     */ 
/* 617 */     for (Map.Entry localEntry : this.v.sorted.entrySet())
/*     */     {
/* 621 */       this.v.elemA.b((byte)((s)localEntry.getValue()).a, (byte)((s)localEntry.getValue()).b, (byte)((s)localEntry.getValue()).c);
/*     */ 
/* 624 */       (
/* 625 */         localObject2 = new ArrayList())
/* 625 */         .add(this.v.elemA);
/*     */ 
/* 639 */       for (int m = 0; m < ((ArrayList)localObject2).size(); 
/* 674 */         m++)
/*     */       {
/* 682 */         paramConvexShape = ((s)localEntry.getValue()).d % 10;
/* 683 */         bool1 = ((s)localEntry.getValue()).d >= 1000;
/* 684 */         int j = (((s)localEntry.getValue()).d - (bool1 ? 1000 : 0)) / 10;
/*     */ 
/* 687 */         if (m == 0)
/*     */         {
/* 689 */           this.v.elemPosA.set(this.v.elemA.a - 8, this.v.elemA.b - 8, this.v.elemA.c - 8);
/*     */         }
/*     */         else
/*     */         {
/* 695 */           (
/* 696 */             localObject3 = (o)((ArrayList)localObject2).get(m))
/* 696 */             .c(this.v.elemA);
/* 697 */           this.v.elemPosA.set(this.v.elemA.a - 8 + 0.5F * ((o)localObject3).a, this.v.elemA.b - 8 + 0.5F * ((o)localObject3).b, this.v.elemA.c - 8 + 0.5F * ((o)localObject3).c);
/*     */         }
/*     */ 
/* 702 */         this.v.elemPosA.x += paramSegment.getSegment().a.a;
/* 703 */         this.v.elemPosA.y += paramSegment.getSegment().a.b;
/* 704 */         this.v.elemPosA.z += paramSegment.getSegment().a.c;
/*     */ 
/* 706 */         this.v.nA.set(this.v.elemPosA);
/* 707 */         this.v.tmpTrans.set(paramTransform3);
/* 708 */         this.v.tmpTrans.basis.transform(this.v.nA);
/* 709 */         this.v.tmpTrans.origin.add(this.v.nA);
/*     */ 
/* 712 */         this.v.simplexSolver.reset();
/* 713 */         this.v.box0.setMargin(0.005F);
/*     */ 
/* 724 */         Object localObject3 = this.v.box0;
/*     */ 
/* 728 */         if ((paramConvexShape > 0) && (paramConvexShape < 3))
/*     */         {
/* 730 */           localObject3 = dO.a(paramConvexShape, (byte)j, bool1);
/*     */         }
/*     */ 
/* 733 */         paramConvexShape = new ContinuousConvexCollision(this.v.shapeA, (ConvexShape)localObject3, this.v.simplexSolver, this.v.gjkEpaPenetrationDepthSolver);
/*     */         ConvexCast.CastResult localCastResult;
/* 735 */         (
/* 736 */           localCastResult = new ConvexCast.CastResult()).allowedPenetration = 
/* 736 */           paramCastResult.allowedPenetration;
/* 737 */         localCastResult.fraction = 1.0F;
/*     */ 
/* 739 */         if (paramConvexShape.calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans, this.v.tmpTrans, localCastResult, this.v.gjkVar))
/*     */         {
/* 742 */           if (this.resultCallback == null)
/*     */           {
/* 744 */             if (this.rayResult != null) {
/* 745 */               this.rayResult.setSegment(paramSegment.getSegment());
/* 746 */               this.rayResult.cubePos.b(this.v.elemA);
/*     */             }
/* 748 */             return true;
/*     */           }
/*     */ 
/* 752 */           if ((localCastResult.normal.lengthSquared() > 1.0E-004F) && 
/* 753 */             (localCastResult.fraction <= this.resultCallback.closestHitFraction))
/*     */           {
/* 769 */             paramCastResult.normal.normalize();
/* 770 */             paramConvexShape = new CollisionWorld.LocalConvexResult(this.v.cubesObject, null, localCastResult.normal, localCastResult.hitPoint, localCastResult.fraction);
/*     */ 
/* 772 */             if ((this.resultCallback instanceof KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback))
/*     */             {
/* 774 */               ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)this.resultCallback).addSingleResult(paramConvexShape, true, paramSegment.getSegment(), this.v.elemA);
/*     */             }
/* 776 */             else this.resultCallback.addSingleResult(paramConvexShape, true);
/*     */ 
/* 778 */             if ((this.resultCallback instanceof ClosestConvexResultCallbackExt)) {
/* 779 */               ((ClosestConvexResultCallbackExt)this.resultCallback).userData = paramSegment.getSegmentController();
/*     */             }
/* 781 */             paramSegment.getSegment(); Segment.d();
/* 782 */             bool2 = true;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 793 */     return bool2;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexCubesCovexCast
 * JD-Core Version:    0.6.2
 */