/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ 
/*     */ public class DefaultCollisionConfiguration extends CollisionConfiguration
/*     */ {
/*     */   protected VoronoiSimplexSolver simplexSolver;
/*     */   protected ConvexPenetrationDepthSolver pdSolver;
/*     */   protected CollisionAlgorithmCreateFunc convexConvexCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc convexConcaveCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc swappedConvexConcaveCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc compoundCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc swappedCompoundCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc emptyCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc sphereSphereCF;
/*     */   protected CollisionAlgorithmCreateFunc sphereBoxCF;
/*     */   protected CollisionAlgorithmCreateFunc boxSphereCF;
/*     */   protected CollisionAlgorithmCreateFunc boxBoxCF;
/*     */   protected CollisionAlgorithmCreateFunc sphereTriangleCF;
/*     */   protected CollisionAlgorithmCreateFunc triangleSphereCF;
/*     */   protected CollisionAlgorithmCreateFunc planeConvexCF;
/*     */   protected CollisionAlgorithmCreateFunc convexPlaneCF;
/*     */ 
/*     */   public DefaultCollisionConfiguration()
/*     */   {
/*  63 */     this.simplexSolver = new VoronoiSimplexSolver();
/*     */ 
/*  67 */     this.pdSolver = new GjkEpaPenetrationDepthSolver();
/*     */ 
/*  75 */     this.convexConvexCreateFunc = new ConvexConvexAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/*  76 */     this.convexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.CreateFunc();
/*  77 */     this.swappedConvexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.SwappedCreateFunc();
/*  78 */     this.compoundCreateFunc = new CompoundCollisionAlgorithm.CreateFunc();
/*  79 */     this.swappedCompoundCreateFunc = new CompoundCollisionAlgorithm.SwappedCreateFunc();
/*  80 */     this.emptyCreateFunc = new EmptyAlgorithm.CreateFunc();
/*     */ 
/*  82 */     this.sphereSphereCF = new SphereSphereCollisionAlgorithm.CreateFunc();
/*     */ 
/*  96 */     this.convexPlaneCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/*  97 */     this.planeConvexCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/*  98 */     this.planeConvexCF.swapped = true;
/*     */   }
/*     */ 
/*     */   public CollisionAlgorithmCreateFunc getCollisionAlgorithmCreateFunc(BroadphaseNativeType proxyType0, BroadphaseNativeType proxyType1)
/*     */   {
/* 148 */     if ((proxyType0 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE) && (proxyType1 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE)) {
/* 149 */       return this.sphereSphereCF;
/*     */     }
/*     */ 
/* 178 */     if ((proxyType0.isConvex()) && (proxyType1 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/*     */     {
/* 180 */       return this.convexPlaneCF;
/*     */     }
/*     */ 
/* 183 */     if ((proxyType1.isConvex()) && (proxyType0 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/*     */     {
/* 185 */       return this.planeConvexCF;
/*     */     }
/*     */ 
/* 188 */     if ((proxyType0.isConvex()) && (proxyType1.isConvex())) {
/* 189 */       return this.convexConvexCreateFunc;
/*     */     }
/*     */ 
/* 192 */     if ((proxyType0.isConvex()) && (proxyType1.isConcave())) {
/* 193 */       return this.convexConcaveCreateFunc;
/*     */     }
/*     */ 
/* 196 */     if ((proxyType1.isConvex()) && (proxyType0.isConcave())) {
/* 197 */       return this.swappedConvexConcaveCreateFunc;
/*     */     }
/*     */ 
/* 200 */     if (proxyType0.isCompound()) {
/* 201 */       return this.compoundCreateFunc;
/*     */     }
/*     */ 
/* 204 */     if (proxyType1.isCompound()) {
/* 205 */       return this.swappedCompoundCreateFunc;
/*     */     }
/*     */ 
/* 210 */     return this.emptyCreateFunc;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration
 * JD-Core Version:    0.6.2
 */