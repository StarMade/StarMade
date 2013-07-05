/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.BulletStats;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GjkPairDetector extends DiscreteCollisionDetectorInterface
/*     */ {
/*     */   private static final float REL_ERROR2 = 1.0E-006F;
/*  48 */   private final Vector3f cachedSeparatingAxis = new Vector3f();
/*     */   private ConvexPenetrationDepthSolver penetrationDepthSolver;
/*     */   private SimplexSolverInterface simplexSolver;
/*     */   private ConvexShape minkowskiA;
/*     */   private ConvexShape minkowskiB;
/*     */   private boolean ignoreMargin;
/*     */   public int lastUsedMethod;
/*     */   public int curIter;
/*     */   public int degenerateSimplex;
/*     */   public int catchDegeneracies;
/*     */ 
/*     */   public void init(ConvexShape objectA, ConvexShape objectB, SimplexSolverInterface simplexSolver, ConvexPenetrationDepthSolver penetrationDepthSolver)
/*     */   {
/*  62 */     this.cachedSeparatingAxis.set(0.0F, 0.0F, 1.0F);
/*  63 */     this.ignoreMargin = false;
/*  64 */     this.lastUsedMethod = -1;
/*  65 */     this.catchDegeneracies = 1;
/*     */ 
/*  67 */     this.penetrationDepthSolver = penetrationDepthSolver;
/*  68 */     this.simplexSolver = simplexSolver;
/*  69 */     this.minkowskiA = objectA;
/*  70 */     this.minkowskiB = objectB;
/*     */   }
/*     */ 
/*     */   public void getClosestPoints(DiscreteCollisionDetectorInterface.ClosestPointInput arg1, DiscreteCollisionDetectorInterface.Result arg2, IDebugDraw arg3, boolean arg4)
/*     */   {
/*  75 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  77 */       float distance = 0.0F;
/*  78 */       Vector3f normalInB = localStack.get$javax$vecmath$Vector3f();
/*  79 */       normalInB.set(0.0F, 0.0F, 0.0F);
/*  80 */       Vector3f pointOnA = localStack.get$javax$vecmath$Vector3f(); Vector3f pointOnB = localStack.get$javax$vecmath$Vector3f();
/*  81 */       Transform localTransA = localStack.get$com$bulletphysics$linearmath$Transform(input.transformA);
/*  82 */       Transform localTransB = localStack.get$com$bulletphysics$linearmath$Transform(input.transformB);
/*  83 */       Vector3f positionOffset = localStack.get$javax$vecmath$Vector3f();
/*  84 */       positionOffset.add(localTransA.origin, localTransB.origin);
/*  85 */       positionOffset.scale(0.5F);
/*  86 */       localTransA.origin.sub(positionOffset);
/*  87 */       localTransB.origin.sub(positionOffset);
/*     */ 
/*  89 */       float marginA = this.minkowskiA.getMargin();
/*  90 */       float marginB = this.minkowskiB.getMargin();
/*     */ 
/*  92 */       BulletStats.gNumGjkChecks += 1;
/*     */ 
/*  95 */       if (this.ignoreMargin) {
/*  96 */         marginA = 0.0F;
/*  97 */         marginB = 0.0F;
/*     */       }
/*     */ 
/* 100 */       this.curIter = 0;
/* 101 */       int gGjkMaxIter = 1000;
/* 102 */       this.cachedSeparatingAxis.set(0.0F, 1.0F, 0.0F);
/*     */ 
/* 104 */       boolean isValid = false;
/* 105 */       boolean checkSimplex = false;
/* 106 */       boolean checkPenetration = true;
/* 107 */       this.degenerateSimplex = 0;
/*     */ 
/* 109 */       this.lastUsedMethod = -1;
/*     */ 
/* 112 */       float squaredDistance = 3.4028235E+38F;
/* 113 */       float delta = 0.0F;
/*     */ 
/* 115 */       float margin = marginA + marginB;
/*     */ 
/* 117 */       this.simplexSolver.reset();
/*     */ 
/* 119 */       Vector3f seperatingAxisInA = localStack.get$javax$vecmath$Vector3f();
/* 120 */       Vector3f seperatingAxisInB = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 122 */       Vector3f pInA = localStack.get$javax$vecmath$Vector3f();
/* 123 */       Vector3f qInB = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 125 */       Vector3f pWorld = localStack.get$javax$vecmath$Vector3f();
/* 126 */       Vector3f qWorld = localStack.get$javax$vecmath$Vector3f();
/* 127 */       Vector3f w = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 129 */       Vector3f tmpPointOnA = localStack.get$javax$vecmath$Vector3f(); Vector3f tmpPointOnB = localStack.get$javax$vecmath$Vector3f();
/* 130 */       Vector3f tmpNormalInB = localStack.get$javax$vecmath$Vector3f();
/*     */       while (true)
/*     */       {
/* 134 */         seperatingAxisInA.negate(this.cachedSeparatingAxis);
/* 135 */         MatrixUtil.transposeTransform(seperatingAxisInA, seperatingAxisInA, input.transformA.basis);
/*     */ 
/* 137 */         seperatingAxisInB.set(this.cachedSeparatingAxis);
/* 138 */         MatrixUtil.transposeTransform(seperatingAxisInB, seperatingAxisInB, input.transformB.basis);
/*     */ 
/* 140 */         this.minkowskiA.localGetSupportingVertexWithoutMargin(seperatingAxisInA, pInA);
/* 141 */         this.minkowskiB.localGetSupportingVertexWithoutMargin(seperatingAxisInB, qInB);
/*     */ 
/* 143 */         pWorld.set(pInA);
/* 144 */         localTransA.transform(pWorld);
/*     */ 
/* 146 */         qWorld.set(qInB);
/* 147 */         localTransB.transform(qWorld);
/*     */ 
/* 149 */         w.sub(pWorld, qWorld);
/*     */ 
/* 151 */         delta = this.cachedSeparatingAxis.dot(w);
/*     */ 
/* 154 */         if ((delta > 0.0F) && (delta * delta > squaredDistance * input.maximumDistanceSquared)) {
/* 155 */           checkPenetration = false;
/* 156 */           break;
/*     */         }
/*     */ 
/* 160 */         if (this.simplexSolver.inSimplex(w)) {
/* 161 */           this.degenerateSimplex = 1;
/* 162 */           checkSimplex = true;
/* 163 */           break;
/*     */         }
/*     */ 
/* 166 */         float f0 = squaredDistance - delta;
/* 167 */         float f1 = squaredDistance * 1.0E-006F;
/*     */ 
/* 169 */         if (f0 <= f1) {
/* 170 */           if (f0 <= 0.0F) {
/* 171 */             this.degenerateSimplex = 2;
/*     */           }
/* 173 */           checkSimplex = true;
/* 174 */           break;
/*     */         }
/*     */ 
/* 177 */         this.simplexSolver.addVertex(w, pWorld, qWorld);
/*     */ 
/* 180 */         if (!this.simplexSolver.closest(this.cachedSeparatingAxis)) {
/* 181 */           this.degenerateSimplex = 3;
/* 182 */           checkSimplex = true;
/* 183 */           break;
/*     */         }
/*     */ 
/* 186 */         if (this.cachedSeparatingAxis.lengthSquared() < 1.0E-006F) {
/* 187 */           this.degenerateSimplex = 6;
/* 188 */           checkSimplex = true;
/* 189 */           break;
/*     */         }
/*     */ 
/* 192 */         float previousSquaredDistance = squaredDistance;
/* 193 */         squaredDistance = this.cachedSeparatingAxis.lengthSquared();
/*     */ 
/* 198 */         if (previousSquaredDistance - squaredDistance <= 1.192093E-007F * previousSquaredDistance) {
/* 199 */           this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 200 */           checkSimplex = true;
/* 201 */           break;
/*     */         }
/*     */ 
/* 205 */         if (this.curIter++ > gGjkMaxIter)
/*     */         {
/*     */           break;
/*     */         }
/*     */ 
/* 222 */         boolean check = !this.simplexSolver.fullSimplex();
/*     */ 
/* 225 */         if (!check)
/*     */         {
/* 227 */           this.simplexSolver.backup_closest(this.cachedSeparatingAxis);
/* 228 */           break;
/*     */         }
/*     */       }
/*     */ 
/* 232 */       if (checkSimplex) {
/* 233 */         this.simplexSolver.compute_points(pointOnA, pointOnB);
/* 234 */         normalInB.sub(pointOnA, pointOnB);
/* 235 */         float lenSqr = this.cachedSeparatingAxis.lengthSquared();
/*     */ 
/* 237 */         if (lenSqr < 1.0E-004F) {
/* 238 */           this.degenerateSimplex = 5;
/*     */         }
/* 240 */         if (lenSqr > 1.421086E-014F) {
/* 241 */           float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/* 242 */           normalInB.scale(rlen);
/* 243 */           float s = (float)Math.sqrt(squaredDistance);
/*     */ 
/* 245 */           assert (s > 0.0F);
/*     */ 
/* 247 */           tmp.scale(marginA / s, this.cachedSeparatingAxis);
/* 248 */           pointOnA.sub(tmp);
/*     */ 
/* 250 */           tmp.scale(marginB / s, this.cachedSeparatingAxis);
/* 251 */           pointOnB.add(tmp);
/*     */ 
/* 253 */           distance = 1.0F / rlen - margin;
/* 254 */           isValid = true;
/*     */ 
/* 256 */           this.lastUsedMethod = 1;
/*     */         }
/*     */         else {
/* 259 */           this.lastUsedMethod = 2;
/*     */         }
/*     */       }
/*     */ 
/* 263 */       boolean catchDegeneratePenetrationCase = (this.catchDegeneracies != 0) && (this.penetrationDepthSolver != null) && (this.degenerateSimplex != 0) && (distance + margin < 0.01F);
/*     */ 
/* 267 */       if ((checkPenetration) && ((!isValid) || (catchDegeneratePenetrationCase)))
/*     */       {
/* 271 */         if (this.penetrationDepthSolver != null)
/*     */         {
/* 273 */           BulletStats.gNumDeepPenetrationChecks += 1;
/*     */ 
/* 275 */           boolean isValid2 = this.penetrationDepthSolver.calcPenDepth(this.simplexSolver, this.minkowskiA, this.minkowskiB, localTransA, localTransB, this.cachedSeparatingAxis, tmpPointOnA, tmpPointOnB, debugDraw);
/*     */ 
/* 282 */           if (isValid2) {
/* 283 */             tmpNormalInB.sub(tmpPointOnB, tmpPointOnA);
/*     */ 
/* 285 */             float lenSqr = tmpNormalInB.lengthSquared();
/* 286 */             if (lenSqr > 1.421086E-014F) {
/* 287 */               tmpNormalInB.scale(1.0F / (float)Math.sqrt(lenSqr));
/* 288 */               tmp.sub(tmpPointOnA, tmpPointOnB);
/* 289 */               float distance2 = -tmp.length();
/*     */ 
/* 291 */               if ((!isValid) || (distance2 < distance)) {
/* 292 */                 distance = distance2;
/* 293 */                 pointOnA.set(tmpPointOnA);
/* 294 */                 pointOnB.set(tmpPointOnB);
/* 295 */                 normalInB.set(tmpNormalInB);
/* 296 */                 isValid = true;
/* 297 */                 this.lastUsedMethod = 3;
/*     */               }
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 305 */               this.lastUsedMethod = 4;
/*     */             }
/*     */           }
/*     */           else {
/* 309 */             this.lastUsedMethod = 5;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 316 */       if (isValid)
/*     */       {
/* 321 */         tmp.add(pointOnB, positionOffset);
/* 322 */         output.addContactPoint(normalInB, tmp, distance);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 328 */       .Stack tmp1101_1099 = localStack; tmp1101_1099.pop$com$bulletphysics$linearmath$Transform(); tmp1101_1099.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void setMinkowskiA(ConvexShape minkA) {
/* 331 */     this.minkowskiA = minkA;
/*     */   }
/*     */ 
/*     */   public void setMinkowskiB(ConvexShape minkB) {
/* 335 */     this.minkowskiB = minkB;
/*     */   }
/*     */ 
/*     */   public void setCachedSeperatingAxis(Vector3f seperatingAxis) {
/* 339 */     this.cachedSeparatingAxis.set(seperatingAxis);
/*     */   }
/*     */ 
/*     */   public void setPenetrationDepthSolver(ConvexPenetrationDepthSolver penetrationDepthSolver) {
/* 343 */     this.penetrationDepthSolver = penetrationDepthSolver;
/*     */   }
/*     */ 
/*     */   public void setIgnoreMargin(boolean ignoreMargin)
/*     */   {
/* 350 */     this.ignoreMargin = ignoreMargin;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.GjkPairDetector
 * JD-Core Version:    0.6.2
 */