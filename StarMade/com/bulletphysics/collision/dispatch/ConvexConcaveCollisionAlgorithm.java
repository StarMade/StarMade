/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexCast.CastResult;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.narrowphase.SubsimplexConvexCast;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.ConcaveShape;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import com.bulletphysics.collision.shapes.TriangleShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ConvexConcaveCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/*     */   private boolean isSwapped;
/*     */   private ConvexTriangleCallback btConvexTriangleCallback;
/*     */ 
/*     */   public void init(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1, boolean isSwapped)
/*     */   {
/*  56 */     super.init(ci);
/*  57 */     this.isSwapped = isSwapped;
/*  58 */     this.btConvexTriangleCallback = new ConvexTriangleCallback(this.dispatcher, body0, body1, isSwapped);
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  63 */     this.btConvexTriangleCallback.destroy();
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*     */   {
/*  68 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); CollisionObject convexBody = this.isSwapped ? body1 : body0;
/*  69 */       CollisionObject triBody = this.isSwapped ? body0 : body1;
/*     */ 
/*  71 */       if (triBody.getCollisionShape().isConcave()) {
/*  72 */         CollisionObject triOb = triBody;
/*  73 */         ConcaveShape concaveShape = (ConcaveShape)triOb.getCollisionShape();
/*     */ 
/*  75 */         if (convexBody.getCollisionShape().isConvex()) {
/*  76 */           float collisionMarginTriangle = concaveShape.getMargin();
/*     */ 
/*  78 */           resultOut.setPersistentManifold(this.btConvexTriangleCallback.manifoldPtr);
/*  79 */           this.btConvexTriangleCallback.setTimeStepAndCounters(collisionMarginTriangle, dispatchInfo, resultOut);
/*     */ 
/*  84 */           this.btConvexTriangleCallback.manifoldPtr.setBodies(convexBody, triBody);
/*     */ 
/*  86 */           concaveShape.processAllTriangles(this.btConvexTriangleCallback, this.btConvexTriangleCallback.getAabbMin(localStack.get$javax$vecmath$Vector3f()), this.btConvexTriangleCallback.getAabbMax(localStack.get$javax$vecmath$Vector3f()));
/*     */ 
/*  91 */           resultOut.refreshContactPoints();
/*     */         }
/*     */       }
/*  94 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4) {
/*  98 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 100 */       CollisionObject convexbody = this.isSwapped ? body1 : body0;
/* 101 */       CollisionObject triBody = this.isSwapped ? body0 : body1;
/*     */ 
/* 107 */       tmp.sub(convexbody.getInterpolationWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin, convexbody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/* 108 */       float squareMot0 = tmp.lengthSquared();
/* 109 */       if (squareMot0 < convexbody.getCcdSquareMotionThreshold()) {
/* 110 */         return 1.0F;
/*     */       }
/*     */ 
/* 113 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 119 */       Transform triInv = triBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 120 */       triInv.inverse();
/*     */ 
/* 122 */       Transform convexFromLocal = localStack.get$com$bulletphysics$linearmath$Transform();
/* 123 */       convexFromLocal.mul(triInv, convexbody.getWorldTransform(tmpTrans));
/*     */ 
/* 125 */       Transform convexToLocal = localStack.get$com$bulletphysics$linearmath$Transform();
/* 126 */       convexToLocal.mul(triInv, convexbody.getInterpolationWorldTransform(tmpTrans));
/*     */ 
/* 128 */       if (triBody.getCollisionShape().isConcave()) {
/* 129 */         Vector3f rayAabbMin = localStack.get$javax$vecmath$Vector3f(convexFromLocal.origin);
/* 130 */         VectorUtil.setMin(rayAabbMin, convexToLocal.origin);
/*     */ 
/* 132 */         Vector3f rayAabbMax = localStack.get$javax$vecmath$Vector3f(convexFromLocal.origin);
/* 133 */         VectorUtil.setMax(rayAabbMax, convexToLocal.origin);
/*     */ 
/* 135 */         float ccdRadius0 = convexbody.getCcdSweptSphereRadius();
/*     */ 
/* 137 */         tmp.set(ccdRadius0, ccdRadius0, ccdRadius0);
/* 138 */         rayAabbMin.sub(tmp);
/* 139 */         rayAabbMax.add(tmp);
/*     */ 
/* 141 */         float curHitFraction = 1.0F;
/* 142 */         LocalTriangleSphereCastCallback raycastCallback = new LocalTriangleSphereCastCallback(convexFromLocal, convexToLocal, convexbody.getCcdSweptSphereRadius(), curHitFraction);
/*     */ 
/* 144 */         raycastCallback.hitFraction = convexbody.getHitFraction();
/*     */ 
/* 146 */         CollisionObject concavebody = triBody;
/*     */ 
/* 148 */         ConcaveShape triangleMesh = (ConcaveShape)concavebody.getCollisionShape();
/*     */ 
/* 150 */         if (triangleMesh != null) {
/* 151 */           triangleMesh.processAllTriangles(raycastCallback, rayAabbMin, rayAabbMax);
/*     */         }
/*     */ 
/* 154 */         if (raycastCallback.hitFraction < convexbody.getHitFraction()) {
/* 155 */           convexbody.setHitFraction(raycastCallback.hitFraction);
/* 156 */           return raycastCallback.hitFraction;
/*     */         }
/*     */       }
/*     */ 
/* 160 */       return 1.0F;
/*     */     }
/*     */     finally
/*     */     {
/* 160 */       .Stack tmp379_377 = localStack; tmp379_377.pop$com$bulletphysics$linearmath$Transform(); tmp379_377.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*     */   {
/* 165 */     if (this.btConvexTriangleCallback.manifoldPtr != null)
/* 166 */       manifoldArray.add(this.btConvexTriangleCallback.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public void clearCache()
/*     */   {
/* 171 */     this.btConvexTriangleCallback.clearCache();
/*     */   }
/*     */ 
/*     */   public static class SwappedCreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 239 */     private final ObjectPool<ConvexConcaveCollisionAlgorithm> pool = ObjectPool.get(ConvexConcaveCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 243 */       ConvexConcaveCollisionAlgorithm algo = (ConvexConcaveCollisionAlgorithm)this.pool.get();
/* 244 */       algo.init(ci, body0, body1, true);
/* 245 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 250 */       this.pool.release((ConvexConcaveCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 223 */     private final ObjectPool<ConvexConcaveCollisionAlgorithm> pool = ObjectPool.get(ConvexConcaveCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 227 */       ConvexConcaveCollisionAlgorithm algo = (ConvexConcaveCollisionAlgorithm)this.pool.get();
/* 228 */       algo.init(ci, body0, body1, false);
/* 229 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 234 */       this.pool.release((ConvexConcaveCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class LocalTriangleSphereCastCallback extends TriangleCallback
/*     */   {
/* 177 */     public final Transform ccdSphereFromTrans = new Transform();
/* 178 */     public final Transform ccdSphereToTrans = new Transform();
/* 179 */     public final Transform meshTransform = new Transform();
/*     */     public float ccdSphereRadius;
/*     */     public float hitFraction;
/* 184 */     private final Transform ident = new Transform();
/*     */ 
/*     */     public LocalTriangleSphereCastCallback(Transform from, Transform to, float ccdSphereRadius, float hitFraction) {
/* 187 */       this.ccdSphereFromTrans.set(from);
/* 188 */       this.ccdSphereToTrans.set(to);
/* 189 */       this.ccdSphereRadius = ccdSphereRadius;
/* 190 */       this.hitFraction = hitFraction;
/*     */ 
/* 193 */       this.ident.setIdentity();
/*     */     }
/*     */ 
/*     */     public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
/*     */     {
/* 202 */       ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 203 */       castResult.fraction = this.hitFraction;
/* 204 */       SphereShape pointShape = new SphereShape(this.ccdSphereRadius);
/* 205 */       TriangleShape triShape = new TriangleShape(triangle[0], triangle[1], triangle[2]);
/* 206 */       VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 207 */       SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(pointShape, triShape, simplexSolver);
/*     */ 
/* 212 */       if ((convexCaster.calcTimeOfImpact(this.ccdSphereFromTrans, this.ccdSphereToTrans, this.ident, this.ident, castResult)) && 
/* 213 */         (this.hitFraction > castResult.fraction))
/* 214 */         this.hitFraction = castResult.fraction;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */