/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.collision.narrowphase.ConvexPenetrationDepthSolver;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.ClosestPointInput;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.Result;
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GjkPairDetectorExt extends DiscreteCollisionDetectorInterface
/*     */ {
/*     */   private static final float REL_ERROR2 = 1.0E-006F;
/*  50 */   private final Vector3f cachedSeparatingAxis = new Vector3f();
/*     */   private ConvexPenetrationDepthSolver penetrationDepthSolver;
/*     */   private SimplexSolverInterface simplexSolver;
/*     */   private ConvexShape minkowskiA;
/*     */   private ConvexShape minkowskiB;
/*     */   private boolean ignoreMargin;
/*     */   public int lastUsedMethod;
/*     */   public int curIter;
/*     */   public int degenerateSimplex;
/*     */   public int catchDegeneracies;
/*     */   private GjkPairDetectorVariables v;
/*     */ 
/*     */   public GjkPairDetectorExt(GjkPairDetectorVariables paramGjkPairDetectorVariables)
/*     */   {
/*  66 */     this.v = paramGjkPairDetectorVariables;
/*     */   }
/*     */ 
/*     */   public void init(ConvexShape paramConvexShape1, ConvexShape paramConvexShape2, SimplexSolverInterface paramSimplexSolverInterface, ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver) {
/*  70 */     this.cachedSeparatingAxis.set(0.0F, 0.0F, 1.0F);
/*  71 */     this.ignoreMargin = false;
/*  72 */     this.lastUsedMethod = -1;
/*  73 */     this.catchDegeneracies = 1;
/*     */ 
/*  75 */     this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
/*  76 */     this.simplexSolver = paramSimplexSolverInterface;
/*  77 */     this.minkowskiA = paramConvexShape1;
/*  78 */     this.minkowskiB = paramConvexShape2;
/*     */   }
/*     */ 
/*     */   public void getClosestPoints(DiscreteCollisionDetectorInterface.ClosestPointInput paramClosestPointInput, DiscreteCollisionDetectorInterface.Result paramResult, IDebugDraw paramIDebugDraw, boolean paramBoolean) {
/*  82 */     paramBoolean = this.v.tmp;
/*     */ 
/*  84 */     float f1 = 0.0F;
/*     */     Vector3f localVector3f1;
/*  85 */     (
/*  86 */       localVector3f1 = this.v.normalInB)
/*  86 */       .set(0.0F, 0.0F, 0.0F);
/*  87 */     Vector3f localVector3f2 = this.v.pointOnA;
/*  88 */     Vector3f localVector3f3 = this.v.pointOnB;
/*  89 */     Transform localTransform1 = this.v.localTransA;
/*  90 */     Transform localTransform2 = this.v.localTransB;
/*  91 */     localTransform1.set(paramClosestPointInput.transformA);
/*  92 */     localTransform2.set(paramClosestPointInput.transformB);
/*     */     Vector3f localVector3f4;
/*  93 */     (
/*  94 */       localVector3f4 = this.v.positionOffset)
/*  94 */       .add(localTransform1.origin, localTransform2.origin);
/*  95 */     localVector3f4.scale(0.5F);
/*  96 */     localTransform1.origin.sub(localVector3f4);
/*  97 */     localTransform2.origin.sub(localVector3f4);
/*     */ 
/*  99 */     float f2 = this.minkowskiA.getMargin();
/* 100 */     float f3 = this.minkowskiB.getMargin();
/*     */ 
/* 102 */     BulletStats.gNumGjkChecks += 1;
/*     */ 
/* 105 */     if (this.ignoreMargin) {
/* 106 */       f2 = 0.0F;
/* 107 */       f3 = 0.0F; } 
/*     */ this.curIter = 0;
/* 111 */     this.cachedSeparatingAxis.set(0.0F, 1.0F, 0.0F);
/*     */ 
/* 114 */     int i = 0;
/* 115 */     int j = 0;
/* 116 */     int k = 1;
/* 117 */     this.degenerateSimplex = 0;
/*     */ 
/* 119 */     this.lastUsedMethod = -1;
/*     */ 
/* 122 */     float f4 = 3.4028235E+38F;
/* 123 */     float f7 = f2 + f3;
/*     */ 
/* 127 */     this.simplexSolver.reset();
/*     */ 
/* 129 */     Vector3f localVector3f5 = this.v.seperatingAxisInA;
/* 130 */     Vector3f localVector3f6 = this.v.seperatingAxisInB;
/*     */ 
/* 132 */     Vector3f localVector3f7 = this.v.pInA;
/* 133 */     Vector3f localVector3f8 = this.v.qInB;
/*     */ 
/* 135 */     Vector3f localVector3f9 = this.v.pWorld;
/* 136 */     Vector3f localVector3f10 = this.v.qWorld;
/* 137 */     Vector3f localVector3f11 = this.v.w;
/*     */ 
/* 139 */     Vector3f localVector3f12 = this.v.tmpPointOnA;
/* 140 */     Vector3f localVector3f13 = this.v.tmpPointOnB;
/* 141 */     Vector3f localVector3f14 = this.v.tmpNormalInB;
/*     */     float f5;
/*     */     float f8;
/*     */     while (true) { localVector3f5.negate(this.cachedSeparatingAxis);
/* 146 */       MatrixUtil.transposeTransform(localVector3f5, localVector3f5, paramClosestPointInput.transformA.basis);
/*     */ 
/* 148 */       localVector3f6.set(this.cachedSeparatingAxis);
/* 149 */       MatrixUtil.transposeTransform(localVector3f6, localVector3f6, paramClosestPointInput.transformB.basis);
/*     */ 
/* 151 */       this.minkowskiA.localGetSupportingVertexWithoutMargin(localVector3f5, localVector3f7);
/* 152 */       this.minkowskiB.localGetSupportingVertexWithoutMargin(localVector3f6, localVector3f8);
/*     */ 
/* 154 */       localVector3f9.set(localVector3f7);
/* 155 */       localTransform1.transform(localVector3f9);
/*     */ 
/* 157 */       localVector3f10.set(localVector3f8);
/* 158 */       localTransform2.transform(localVector3f10);
/*     */ 
/* 160 */       localVector3f11.sub(localVector3f9, localVector3f10);
/*     */ 
/* 165 */       if (((
/* 165 */         f5 = this.cachedSeparatingAxis.dot(localVector3f11)) > 
/* 165 */         0.0F) && (f5 * f5 > f4 * paramClosestPointInput.maximumDistanceSquared)) {
/* 166 */         k = 0;
/* 167 */         break;
/*     */       }
/*     */ 
/* 171 */       if (this.simplexSolver.inSimplex(localVector3f11)) {
/* 172 */         this.degenerateSimplex = 1;
/* 173 */         j = 1;
/* 174 */         break;
/*     */       }
/*     */ 
/* 177 */       f5 = f4 - f5;
/* 178 */       f8 = f4 * 1.0E-006F;
/*     */ 
/* 180 */       if (f5 <= f8) {
/* 181 */         if (f5 <= 0.0F) {
/* 182 */           this.degenerateSimplex = 2;
/*     */         }
/* 184 */         j = 1;
/* 185 */         break;
/*     */       }
/*     */ 
/* 188 */       this.simplexSolver.addVertex(localVector3f11, localVector3f9, localVector3f10);
/*     */ 
/* 191 */       if (!this.simplexSolver.closest(this.cachedSeparatingAxis)) {
/* 192 */         this.degenerateSimplex = 3;
/* 193 */         j = 1;
/* 194 */         break;
/*     */       }
/*     */ 
/* 197 */       if (this.cachedSeparatingAxis.lengthSquared() < 1.0E-006F) {
/* 198 */         this.degenerateSimplex = 6;
/* 199 */         j = 1;
/* 200 */         break;
/*     */       }
/*     */ 
/* 203 */       f5 = f4;
/* 204 */       f4 = this.cachedSeparatingAxis.lengthSquared();
/*     */ 
/* 209 */       if (f5 - f4 <= 1.192093E-007F * f5) {
/* 210 */         this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 211 */         j = 1;
/* 212 */         break;
/*     */       }
/*     */ 
/* 216 */       if (this.curIter++ > 1000)
/*     */       {
/*     */         break;
/*     */       }
/*     */ 
/* 236 */       if ((!this.simplexSolver.fullSimplex() ? 1 : 0) == 0)
/*     */       {
/* 238 */         this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 239 */         break;
/*     */       }
/*     */     }
/* 242 */     if (j != 0) {
/* 243 */       this.simplexSolver.compute_points(localVector3f2, localVector3f3);
/* 244 */       localVector3f1.sub(localVector3f2, localVector3f3);
/*     */ 
/* 247 */       if ((
/* 247 */         f5 = this.cachedSeparatingAxis.lengthSquared()) < 
/* 247 */         1.0E-004F) {
/* 248 */         this.degenerateSimplex = 5;
/*     */       }
/* 250 */       if (f5 > 1.421086E-014F) {
/* 251 */         f8 = 1.0F / (float)Math.sqrt(f5);
/* 252 */         localVector3f1.scale(f8);
/* 253 */         f5 = (float)Math.sqrt(f4);
/*     */ 
/* 255 */         assert (f5 > 0.0F);
/*     */ 
/* 257 */         paramBoolean.scale(f2 / f5, this.cachedSeparatingAxis);
/* 258 */         localVector3f2.sub(paramBoolean);
/*     */ 
/* 260 */         paramBoolean.scale(f3 / f5, this.cachedSeparatingAxis);
/* 261 */         localVector3f3.add(paramBoolean);
/*     */ 
/* 263 */         f1 = 1.0F / f8 - f7;
/* 264 */         i = 1;
/*     */ 
/* 266 */         this.lastUsedMethod = 1;
/*     */       }
/*     */       else {
/* 269 */         this.lastUsedMethod = 2;
/*     */       }
/*     */     }
/*     */ 
/* 273 */     int m = (this.catchDegeneracies != 0) && (this.penetrationDepthSolver != null) && (this.degenerateSimplex != 0) && (f1 + f7 < 0.01F) ? 1 : 0;
/*     */ 
/* 277 */     if ((k != 0) && ((i == 0) || (m != 0)))
/*     */     {
/* 281 */       if (this.penetrationDepthSolver != null)
/*     */       {
/* 283 */         BulletStats.gNumDeepPenetrationChecks += 1;
/*     */ 
/* 292 */         if (this.penetrationDepthSolver.calcPenDepth(this.simplexSolver, this.minkowskiA, this.minkowskiB, localTransform1, localTransform2, this.cachedSeparatingAxis, localVector3f12, localVector3f13, paramIDebugDraw))
/*     */         {
/* 293 */           localVector3f14.sub(localVector3f13, localVector3f12);
/*     */           float f6;
/* 296 */           if ((
/* 296 */             f6 = localVector3f14.lengthSquared()) > 
/* 296 */             1.421086E-014F) {
/* 297 */             localVector3f14.scale(1.0F / (float)Math.sqrt(f6));
/* 298 */             paramBoolean.sub(localVector3f12, localVector3f13);
/* 299 */             f6 = -paramBoolean.length();
/*     */ 
/* 301 */             if ((i == 0) || (f6 < f1)) {
/* 302 */               f1 = f6;
/* 303 */               localVector3f2.set(localVector3f12);
/* 304 */               localVector3f3.set(localVector3f13);
/* 305 */               localVector3f1.set(localVector3f14);
/* 306 */               i = 1;
/* 307 */               this.lastUsedMethod = 3;
/*     */             }
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 315 */             this.lastUsedMethod = 4;
/*     */           }
/*     */         }
/*     */         else {
/* 319 */           this.lastUsedMethod = 5;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 326 */     if (i != 0)
/*     */     {
/* 331 */       paramBoolean.add(localVector3f3, localVector3f4);
/* 332 */       paramResult.addContactPoint(localVector3f1, paramBoolean, f1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setMinkowskiA(ConvexShape paramConvexShape)
/*     */   {
/* 341 */     this.minkowskiA = paramConvexShape;
/*     */   }
/*     */ 
/*     */   public void setMinkowskiB(ConvexShape paramConvexShape) {
/* 345 */     this.minkowskiB = paramConvexShape;
/*     */   }
/*     */ 
/*     */   public void setCachedSeperatingAxis(Vector3f paramVector3f) {
/* 349 */     this.cachedSeparatingAxis.set(paramVector3f);
/*     */   }
/*     */ 
/*     */   public void setPenetrationDepthSolver(ConvexPenetrationDepthSolver paramConvexPenetrationDepthSolver) {
/* 353 */     this.penetrationDepthSolver = paramConvexPenetrationDepthSolver;
/*     */   }
/*     */ 
/*     */   public void setIgnoreMargin(boolean paramBoolean)
/*     */   {
/* 360 */     this.ignoreMargin = paramBoolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.GjkPairDetectorExt
 * JD-Core Version:    0.6.2
 */