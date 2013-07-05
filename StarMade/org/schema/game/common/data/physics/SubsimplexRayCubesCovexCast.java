/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.shapes.BoxShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import dO;
/*     */ import it.unimi.dsi.fastutil.floats.Float2ObjectAVLTreeMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import jL;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import ld;
/*     */ import o;
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
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ import q;
/*     */ import s;
/*     */ import xO;
/*     */ 
/*     */ public class SubsimplexRayCubesCovexCast extends ConvexCast
/*     */ {
/*  72 */   private static ThreadLocal threadLocal = new SubsimplexRayCubesCovexCast.1();
/*     */   public static boolean debug;
/*  79 */   private float extraMargin = 0.0F;
/*     */   private CubeRayCastResult rayResult;
/*     */   private CubeRayVariableSet v;
/*  82 */   ObjectPool pool = ObjectPool.get(o.class);
/*  83 */   ObjectPool pool4 = ObjectPool.get(s.class);
/*  84 */   ObjectPool aabbpool = ObjectPool.get(AABBb.class);
/*     */   SubsimplexRayCubesCovexCast.OuterSegmentIterator outerSegmentIterator;
/*  86 */   boolean hit = false;
/*  87 */   int hitboxes = 0;
/*  88 */   int casts = 0;
/*     */   private long currentTime;
/*     */ 
/*     */   public SubsimplexRayCubesCovexCast(ConvexShape paramConvexShape, CollisionObject paramCollisionObject, SimplexSolverInterface paramSimplexSolverInterface, CubeRayCastResult paramCubeRayCastResult)
/*     */   {
/*  94 */     this.v = ((CubeRayVariableSet)threadLocal.get());
/*     */ 
/*  96 */     this.outerSegmentIterator = new SubsimplexRayCubesCovexCast.OuterSegmentIterator(this, null);
/*  97 */     this.v.shapeA = paramConvexShape;
/*  98 */     this.v.cubesB = ((CubeShape)paramCollisionObject.getCollisionShape());
/*  99 */     this.v.cubesCollisionObject = paramCollisionObject;
/* 100 */     this.v.simplexSolver = paramSimplexSolverInterface;
/* 101 */     this.rayResult = paramCubeRayCastResult;
/*     */ 
/* 103 */     this.v.box0.setMargin(0.15F);
/* 104 */     this.v.lastMinDist = -1.0F;
/*     */   }
/*     */ 
/*     */   public boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult)
/*     */   {
/* 126 */     if (debug) {
/* 127 */       System.err.println("TESTING RAY COLLISION:  " + paramTransform1.origin + " -> " + paramTransform2.origin + " on " + this.v.cubesB.getSegmentBuffer().a());
/*     */     }
/*     */ 
/* 130 */     this.currentTime = System.currentTimeMillis();
/* 131 */     this.v.lastHitpointWorld.set((0.0F / 0.0F), 0.0F, 0.0F);
/*     */ 
/* 134 */     if ((this.rayResult.filter != null) && (!this.rayResult.filter.equals(this.v.cubesB.getSegmentBuffer().a()))) {
/* 135 */       return false;
/*     */     }
/*     */ 
/* 138 */     this.v.lastMinDist = -1.0F;
/* 139 */     this.hit = false;
/* 140 */     if (this.v.oSet == null)
/* 141 */       this.v.oSet = ArrayOctree.getSet(this.v.cubesB.getSegmentBuffer().a().getState() instanceof ServerStateInterface);
/*     */     else {
/* 143 */       assert (this.v.oSet == ArrayOctree.getSet(this.v.cubesB.getSegmentBuffer().a().isOnServer()));
/*     */     }
/* 145 */     this.v.absolute.set(paramTransform3.basis);
/* 146 */     MatrixUtil.absolute(this.v.absolute);
/*     */ 
/* 149 */     SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$302(this.outerSegmentIterator, paramTransform1);
/* 150 */     SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$402(this.outerSegmentIterator, false);
/* 151 */     SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$502(this.outerSegmentIterator, paramCastResult);
/* 152 */     SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$602(this.outerSegmentIterator, paramTransform3);
/* 153 */     SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$702(this.outerSegmentIterator, paramTransform2);
/* 154 */     SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$802(this.outerSegmentIterator, paramTransform4);
/*     */ 
/* 157 */     this.v.solve.initialize(paramTransform1.origin, paramTransform2.origin, this.v.cubesB.getSegmentBuffer().a(), paramTransform3);
/* 158 */     if (debug) {
/* 159 */       System.err.println("TRAVERSING ON: " + this.outerSegmentIterator);
/*     */     }
/*     */ 
/* 165 */     this.v.solve.traverseSegmentsOnRay(this.outerSegmentIterator);
/*     */ 
/* 168 */     if (SubsimplexRayCubesCovexCast.OuterSegmentIterator.access$400(this.outerSegmentIterator))
/*     */     {
/* 176 */       return true;
/*     */     }
/*     */ 
/* 191 */     return this.hit;
/*     */   }
/*     */ 
/*     */   private boolean checkExplicitCollision(CollisionObject paramCollisionObject, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentController paramSegmentController, ConvexCast.CastResult paramCastResult)
/*     */   {
/* 199 */     if (((paramSegmentController instanceof ld)) && ((((ld)paramSegmentController).a() instanceof ShieldContainerInterface)))
/*     */     {
/* 201 */       for (paramSegmentController = ((ShieldContainerInterface)((ld)paramSegmentController).a())
/* 201 */         .getShieldManager().getCollection().iterator(); paramSegmentController.hasNext(); )
/*     */       {
/*     */         ShieldUnit localShieldUnit;
/* 202 */         if (((ShieldUnit)(
/* 202 */           localShieldUnit = (ShieldUnit)paramSegmentController.next()))
/* 202 */           .getShields() <= 0) {
/* 203 */           System.err.println("Shields decativated");
/*     */ 
/* 205 */           return false;
/*     */         }
/* 207 */         this.v.tmpTrans3.set(paramTransform3);
/* 208 */         localShieldUnit.getOpenGLCenter(this.v.fromHelp);
/*     */ 
/* 210 */         this.v.tmpTrans3.basis.transform(this.v.fromHelp);
/*     */ 
/* 212 */         this.v.tmpTrans3.origin.add(this.v.fromHelp);
/*     */ 
/* 214 */         this.v.simplexSolver.reset();
/*     */ 
/* 216 */         this.v.sphereShape.setRadius(localShieldUnit.getRadius());
/*     */ 
/* 218 */         ContinuousConvexCollision localContinuousConvexCollision = new ContinuousConvexCollision(this.v.shapeA, this.v.sphereShape, this.v.simplexSolver, this.v.gjkEpaPenetrationDepthSolver);
/*     */ 
/* 220 */         this.v.sphereShape.getAabb(this.v.tmpTrans3, this.v.outMin, this.v.outMax);
/*     */ 
/* 224 */         if ((localContinuousConvexCollision.calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans3, this.v.tmpTrans3, paramCastResult, this.v.gjkVar)) && 
/* 225 */           (paramCastResult.fraction < this.rayResult.closestHitFraction))
/*     */         {
/* 229 */           paramTransform1.basis.transform(paramCastResult.normal);
/*     */ 
/* 232 */           paramCastResult.normal.normalize();
/* 233 */           paramCollisionObject = new CollisionWorld.LocalRayResult(paramCollisionObject, null, paramCastResult.normal, paramCastResult.fraction);
/*     */ 
/* 238 */           this.rayResult.setUserData(localShieldUnit);
/* 239 */           this.rayResult.addSingleResult(paramCollisionObject, true);
/*     */ 
/* 241 */           System.err.println("[RAY] Explicid COLLISION");
/* 242 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 247 */     return false;
/*     */   }
/*     */   private void checkSegment(Segment paramSegment, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, ConvexCast.CastResult paramCastResult) {
/* 250 */     if ((paramSegment.a() == null) || (paramSegment.g()))
/*     */     {
/* 252 */       return;
/*     */     }
/*     */ 
/* 255 */     paramSegment.a(this.v.distToHit);
/* 256 */     this.v.distToHit.sub(paramTransform1.origin);
/*     */ 
/* 258 */     this.v.cubesB.setMargin(this.v.cubesB.getMargin() + this.extraMargin);
/*     */ 
/* 260 */     this.v.cubesB.getSegmentAabb(paramSegment, paramTransform3, this.v.outMin, this.v.outMax, this.v.localMinOut, this.v.localMaxOut, this.v.aabbVarSet);
/* 261 */     this.v.hitLambda[0] = 1.0F;
/* 262 */     this.v.normal.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 264 */     this.v.cubesB.setMargin(this.v.cubesB.getMargin() - this.extraMargin);
/*     */ 
/* 269 */     paramTransform4 = (AabbUtil2.rayAabb(paramTransform1.origin, paramTransform2.origin, this.v.outMin, this.v.outMax, this.v.hitLambda, this.v.normal)) || (xO.a(paramTransform1.origin, this.v.outMin, this.v.outMax)) || (xO.a(paramTransform2.origin, this.v.outMin, this.v.outMax)) ? 1 : 0;
/*     */ 
/* 274 */     if (debug) {
/* 275 */       System.err.println("HIT?: " + paramTransform4);
/*     */     }
/* 277 */     if (paramTransform4 != 0)
/*     */     {
/* 281 */       new Transform()
/* 282 */         .setIdentity();
/*     */ 
/* 290 */       this.hitboxes += 1;
/*     */ 
/* 305 */       paramSegment = performCastTest(this.v.cubesCollisionObject, paramSegment, paramTransform1, paramTransform2, paramTransform3, paramCastResult);
/* 306 */       for (paramTransform1 = this.v.sorted.values().iterator(); paramTransform1.hasNext(); ) { paramTransform2 = (s)paramTransform1.next();
/* 307 */         this.pool4.release(paramTransform2);
/*     */       }
/* 309 */       this.v.sorted.clear();
/*     */ 
/* 320 */       if (paramSegment != 0) {
/* 321 */         this.casts += 1;
/*     */       }
/* 323 */       this.hit |= paramSegment;
/* 324 */       if (paramSegment != 0) {
/* 325 */         if (this.rayResult.hasHit()) {
/* 326 */           return;
/*     */         }
/*     */ 
/* 330 */         this.v.lastHitpointWorld.set(this.rayResult.hitPointWorld);
/* 331 */         if ((this.v.lastMinDist < 0.0F) || (this.v.distToHit.length() < this.v.lastMinDist))
/* 332 */           this.v.lastMinDist = this.v.distToHit.length();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void doNarrorTest(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, SegmentData paramSegmentData, ConvexCast.CastResult paramCastResult, o paramo1, o paramo2)
/*     */   {
/* 365 */     this.v.posCachePointer = 0;
/* 366 */     for (paramTransform2 = paramo1.a; paramTransform2 < paramo2.a; paramTransform2 = (byte)(paramTransform2 + 1))
/* 367 */       for (paramCastResult = paramo1.b; paramCastResult < paramo2.b; paramCastResult = (byte)(paramCastResult + 1))
/* 368 */         for (int i = paramo1.c; i < paramo2.c; i = (byte)(i + 1)) {
/* 369 */           this.v.elemA.b((byte)(paramTransform2 + 8), (byte)(paramCastResult + 8), (byte)(i + 8));
/*     */ 
/* 373 */           int j = SegmentData.getInfoIndex(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c);
/*     */ 
/* 382 */           if ((paramSegmentData.containsFast(j)) && ((this.rayResult == null) || (this.rayResult.isIgnoereNotPhysical()) || (ElementKeyMap.getInfo(paramSegmentData.getType(j)).isPhysical(paramSegmentData.isActive(j))))) {
/* 383 */             ElementInformation localElementInformation = ElementKeyMap.getInfo(paramSegmentData.getType(j));
/* 384 */             int k = paramSegmentData.getOrientation(j);
/* 385 */             boolean bool = paramSegmentData.isActive(j);
/*     */ 
/* 389 */             this.v.elemPosA.set(paramTransform2, paramCastResult, i);
/* 390 */             this.v.elemPosA.x += paramSegmentData.getSegment().a.a;
/* 391 */             this.v.elemPosA.y += paramSegmentData.getSegment().a.b;
/* 392 */             this.v.elemPosA.z += paramSegmentData.getSegment().a.c;
/*     */ 
/* 394 */             this.v.nA.set(this.v.elemPosA);
/* 395 */             this.v.tmpTrans3.set(paramTransform3);
/* 396 */             this.v.tmpTrans3.basis.transform(this.v.nA);
/* 397 */             this.v.tmpTrans3.origin.add(this.v.nA);
/* 398 */             this.v.box0.setMargin(0.2F + this.extraMargin);
/* 399 */             this.v.normal.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 401 */             this.v.distTest.set(paramTransform1.origin);
/* 402 */             this.v.distTest.sub(this.v.tmpTrans3.origin);
/*     */             s locals;
/* 411 */             (
/* 412 */               locals = (s)this.pool4.get())
/* 412 */               .a(this.v.elemA.a, this.v.elemA.b, this.v.elemA.c, k * 10 + localElementInformation.getBlockStyle() + (bool ? 1000 : 0));
/*     */ 
/* 414 */             float f = this.v.distTest.length();
/* 415 */             while (this.v.sorted.containsKey(f)) {
/* 416 */               f += 0.1F;
/*     */             }
/* 418 */             this.v.sorted.put(f, locals);
/* 419 */             this.v.posCachePointer += 1;
/*     */           }
/*     */         }
/*     */   }
/*     */ 
/*     */   private boolean performCastTest(CollisionObject paramCollisionObject, Segment paramSegment, Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, ConvexCast.CastResult paramCastResult)
/*     */   {
/* 433 */     paramSegment = paramSegment.a();
/*     */ 
/* 435 */     if (!this.v.intersectionCallBack.initialized) {
/* 436 */       this.v.intersectionCallBack.createHitCache(512);
/*     */     }
/* 438 */     this.v.intersectionCallBack.reset();
/* 439 */     this.v.intersectionCallBack = paramSegment.getOctree().findIntersectingRay(this.v.oSet, this.v.intersectionCallBack, paramTransform3, this.v.absolute, 0.15F + this.extraMargin, paramSegment.getSegment(), paramTransform1.origin, paramTransform2.origin, 1.0F);
/*     */ 
/* 444 */     this.v.sortedAABB.clear();
/* 445 */     this.v.sorted.clear();
/* 446 */     if (debug)
/* 447 */       System.err.println("CAST TEST: (INSIDE)" + this.v.intersectionCallBack.hitCount);
/*     */     Object localObject2;
/*     */     Object localObject3;
/* 449 */     if (this.v.intersectionCallBack.hitCount > 0)
/*     */     {
/* 451 */       for (int i = 0; i < this.v.intersectionCallBack.hitCount; i++) {
/* 452 */         this.v.intersectionCallBack.getHit(i, this.v.minOut, this.v.maxOut, this.v.startOut, this.v.endOut);
/*     */ 
/* 454 */         (
/* 455 */           localObject1 = new Vector3f(this.v.maxOut))
/* 455 */           .sub(this.v.minOut);
/* 456 */         ((Vector3f)localObject1).scale(0.5F + this.extraMargin);
/*     */ 
/* 459 */         (
/* 460 */           localObject2 = new Vector3f(this.v.minOut))
/* 460 */           .add((Tuple3f)localObject1);
/* 461 */         ((Vector3f)localObject2).sub(paramTransform1.origin);
/* 462 */         float f = ((Vector3f)localObject2).length();
/* 463 */         while (this.v.sortedAABB.containsKey(f)) {
/* 464 */           f += 0.1F;
/*     */         }
/*     */ 
/* 467 */         AABBb localAABBb = (AABBb)this.aabbpool.get();
/* 468 */         localObject2 = (o)this.pool.get();
/* 469 */         localObject3 = (o)this.pool.get();
/* 470 */         ((o)localObject2).b(this.v.startOut);
/* 471 */         ((o)localObject3).b(this.v.endOut);
/* 472 */         localAABBb.min = ((o)localObject2);
/* 473 */         localAABBb.max = ((o)localObject3);
/* 474 */         this.v.sortedAABB.put(f, localAABBb);
/*     */       }
/*     */ 
/* 478 */       for (Iterator localIterator = this.v.sortedAABB.entrySet().iterator(); localIterator.hasNext(); ) { localObject1 = (Map.Entry)localIterator.next();
/* 479 */         doNarrorTest(paramTransform1, paramTransform2, paramTransform3, paramSegment, paramCastResult, ((AABBb)((Map.Entry)localObject1).getValue()).min, ((AABBb)((Map.Entry)localObject1).getValue()).max);
/* 480 */         this.pool.release(((AABBb)((Map.Entry)localObject1).getValue()).min);
/* 481 */         this.pool.release(((AABBb)((Map.Entry)localObject1).getValue()).max);
/* 482 */         this.aabbpool.release(((Map.Entry)localObject1).getValue());
/*     */       }
/* 484 */       this.v.sortedAABB.clear();
/*     */     }
/*     */ 
/* 490 */     for (Object localObject1 = this.v.sorted.entrySet().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (Map.Entry)((Iterator)localObject1).next();
/*     */ 
/* 493 */       this.v.elemA.b((byte)((s)((Map.Entry)localObject2).getValue()).a, (byte)((s)((Map.Entry)localObject2).getValue()).b, (byte)((s)((Map.Entry)localObject2).getValue()).c);
/* 494 */       this.v.elemPosA.set(this.v.elemA.a - 8, this.v.elemA.b - 8, this.v.elemA.c - 8);
/*     */ 
/* 499 */       int k = ((s)((Map.Entry)localObject2).getValue()).d % 10;
/* 500 */       boolean bool = ((s)((Map.Entry)localObject2).getValue()).d >= 1000;
/* 501 */       int j = (((s)((Map.Entry)localObject2).getValue()).d - (bool ? 1000 : 0)) / 10;
/*     */ 
/* 503 */       this.v.elemPosA.x += paramSegment.getSegment().a.a;
/* 504 */       this.v.elemPosA.y += paramSegment.getSegment().a.b;
/* 505 */       this.v.elemPosA.z += paramSegment.getSegment().a.c;
/*     */ 
/* 512 */       this.v.nA.set(this.v.elemPosA);
/* 513 */       this.v.tmpTrans3.set(paramTransform3);
/* 514 */       this.v.tmpTrans3.basis.transform(this.v.nA);
/* 515 */       this.v.tmpTrans3.origin.add(this.v.nA);
/*     */ 
/* 518 */       this.v.simplexSolver.reset();
/* 519 */       this.v.box0.setMargin(0.01F + this.extraMargin);
/*     */ 
/* 521 */       localObject3 = this.v.box0;
/* 522 */       if ((k > 0) && (k < 3))
/*     */       {
/* 524 */         localObject3 = dO.a(k, (byte)j, bool);
/*     */       }
/*     */ 
/* 535 */       if (new ContinuousConvexCollision(this.v.shapeA, (ConvexShape)localObject3, this.v.simplexSolver, this.v.gjkEpaPenetrationDepthSolver)
/* 535 */         .calcTimeOfImpact(paramTransform1, paramTransform2, this.v.tmpTrans3, this.v.tmpTrans3, paramCastResult, this.v.gjkVar))
/*     */       {
/* 537 */         if (paramCastResult.normal.lengthSquared() > 1.0E-006F)
/*     */         {
/* 540 */           if (paramCastResult.fraction < this.rayResult.closestHitFraction) {
/* 541 */             assert (paramSegment.getSegment() != null) : ("SEGMENT NULL OF DATA: " + paramSegment + " ");
/*     */ 
/* 543 */             this.rayResult.setSegment(paramSegment.getSegment());
/* 544 */             this.rayResult.cubePos.b(this.v.elemA);
/*     */ 
/* 547 */             paramTransform1.basis.transform(paramCastResult.normal);
/*     */ 
/* 550 */             paramCastResult.normal.normalize();
/* 551 */             paramCollisionObject = new CollisionWorld.LocalRayResult(paramCollisionObject, null, paramCastResult.normal, paramCastResult.fraction);
/*     */ 
/* 557 */             this.rayResult.addSingleResult(paramCollisionObject, true);
/*     */ 
/* 559 */             paramSegment.getSegment(); Segment.d();
/*     */ 
/* 561 */             assert ((!this.rayResult.hasHit()) || (this.rayResult.getSegment() != null));
/* 562 */             return true;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 580 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SubsimplexRayCubesCovexCast
 * JD-Core Version:    0.6.2
 */