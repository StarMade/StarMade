/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*     */ import com.bulletphysics.collision.dispatch.CollisionConfiguration;
/*     */ import com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm.CreateFunc;
/*     */ import com.bulletphysics.collision.dispatch.ConvexConcaveCollisionAlgorithm.SwappedCreateFunc;
/*     */ import com.bulletphysics.collision.dispatch.ConvexPlaneCollisionAlgorithm.CreateFunc;
/*     */ import com.bulletphysics.collision.dispatch.EmptyAlgorithm.CreateFunc;
/*     */ import com.bulletphysics.collision.dispatch.SphereSphereCollisionAlgorithm.CreateFunc;
/*     */ import com.bulletphysics.collision.narrowphase.GjkEpaPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver;
/*     */ 
/*     */ public class CubeCollissionConfiguration extends CollisionConfiguration
/*     */ {
/*     */   protected VoronoiSimplexSolver simplexSolver;
/*     */   protected GjkEpaPenetrationDepthSolver pdSolver;
/*     */   protected CollisionAlgorithmCreateFunc convexConvexCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc convexConcaveCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc swappedConvexConcaveCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc compoundCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc swappedCompoundCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc emptyCreateFunc;
/*     */   protected CollisionAlgorithmCreateFunc sphereSphereCF;
/*     */   protected CollisionAlgorithmCreateFunc sphereBoxCF;
/*     */   protected CollisionAlgorithmCreateFunc boxSphereCF;
/*     */   protected CollisionAlgorithmCreateFunc cubesCubesCF;
/*     */   protected CollisionAlgorithmCreateFunc sphereTriangleCF;
/*     */   protected CollisionAlgorithmCreateFunc triangleSphereCF;
/*     */   protected CollisionAlgorithmCreateFunc planeConvexCF;
/*     */   protected CollisionAlgorithmCreateFunc convexPlaneCF;
/*     */   private CubeConvexCollisionAlgorithm.CreateFunc cubesConvexCF;
/*     */   private CubeConvexCollisionAlgorithm.CreateFunc convexCubesCF;
/*     */ 
/*     */   public CubeCollissionConfiguration()
/*     */   {
/*  72 */     this.simplexSolver = new VoronoiSimplexSolver();
/*     */ 
/*  76 */     this.pdSolver = new GjkEpaPenetrationDepthSolver();
/*     */ 
/*  84 */     this.convexConvexCreateFunc = new ConvexConvexExtAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/*  85 */     this.convexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.CreateFunc();
/*  86 */     this.swappedConvexConcaveCreateFunc = new ConvexConcaveCollisionAlgorithm.SwappedCreateFunc();
/*     */ 
/*  88 */     this.compoundCreateFunc = new CompoundCollisionAlgorithmExt.CreateFunc();
/*  89 */     this.swappedCompoundCreateFunc = new CompoundCollisionAlgorithmExt.SwappedCreateFunc();
/*     */ 
/*  91 */     this.emptyCreateFunc = new EmptyAlgorithm.CreateFunc();
/*     */ 
/*  93 */     this.sphereSphereCF = new SphereSphereCollisionAlgorithm.CreateFunc();
/*     */ 
/* 106 */     this.cubesCubesCF = new CubeCubeCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/* 107 */     this.cubesConvexCF = new CubeConvexCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/* 108 */     this.convexCubesCF = new CubeConvexCollisionAlgorithm.CreateFunc(this.simplexSolver, this.pdSolver);
/* 109 */     this.convexCubesCF.swapped = true;
/*     */ 
/* 112 */     this.convexPlaneCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/* 113 */     this.planeConvexCF = new ConvexPlaneCollisionAlgorithm.CreateFunc();
/* 114 */     this.planeConvexCF.swapped = true;
/*     */   }
/*     */ 
/*     */   public CollisionAlgorithmCreateFunc getCollisionAlgorithmCreateFunc(BroadphaseNativeType paramBroadphaseNativeType1, BroadphaseNativeType paramBroadphaseNativeType2)
/*     */   {
/* 166 */     if ((paramBroadphaseNativeType1 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE)) {
/* 167 */       return this.sphereSphereCF;
/*     */     }
/* 169 */     if ((paramBroadphaseNativeType1 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
/*     */     {
/* 171 */       return this.cubesCubesCF;
/*     */     }
/* 173 */     if ((paramBroadphaseNativeType1 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE) && (paramBroadphaseNativeType2.isConvex()))
/*     */     {
/* 175 */       return this.cubesConvexCF;
/*     */     }
/* 177 */     if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2 == BroadphaseNativeType.TERRAIN_SHAPE_PROXYTYPE))
/*     */     {
/* 179 */       return this.convexCubesCF;
/*     */     }
/*     */ 
/* 207 */     if ((paramBroadphaseNativeType1 == BroadphaseNativeType.FAST_CONCAVE_MESH_PROXYTYPE) && (paramBroadphaseNativeType2 == BroadphaseNativeType.FAST_CONCAVE_MESH_PROXYTYPE)) {
/* 208 */       return this.compoundCreateFunc;
/*     */     }
/* 210 */     if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/*     */     {
/* 212 */       return this.convexPlaneCF;
/*     */     }
/*     */ 
/* 215 */     if ((paramBroadphaseNativeType2.isConvex()) && (paramBroadphaseNativeType1 == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/*     */     {
/* 217 */       return this.planeConvexCF;
/*     */     }
/*     */ 
/* 220 */     if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2.isConvex())) {
/* 221 */       return this.convexConvexCreateFunc;
/*     */     }
/*     */ 
/* 224 */     if ((paramBroadphaseNativeType1.isConvex()) && (paramBroadphaseNativeType2.isConcave())) {
/* 225 */       return this.convexConcaveCreateFunc;
/*     */     }
/*     */ 
/* 228 */     if ((paramBroadphaseNativeType2.isConvex()) && (paramBroadphaseNativeType1.isConcave())) {
/* 229 */       return this.swappedConvexConcaveCreateFunc;
/*     */     }
/*     */ 
/* 232 */     if (paramBroadphaseNativeType1.isCompound())
/* 233 */       return this.compoundCreateFunc;
/* 234 */     if (paramBroadphaseNativeType2.isCompound()) {
/* 235 */       return this.swappedCompoundCreateFunc;
/*     */     }
/*     */ 
/* 239 */     return this.emptyCreateFunc;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCollissionConfiguration
 * JD-Core Version:    0.6.2
 */