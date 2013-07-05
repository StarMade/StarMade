/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class SubsimplexConvexCast extends ConvexCast
/*     */ {
/*     */   private static final int MAX_ITERATIONS = 32;
/*     */   private SimplexSolverInterface simplexSolver;
/*     */   private ConvexShape convexA;
/*     */   private ConvexShape convexB;
/*     */ 
/*     */   public SubsimplexConvexCast(ConvexShape shapeA, ConvexShape shapeB, SimplexSolverInterface simplexSolver)
/*     */   {
/*  61 */     this.convexA = shapeA;
/*  62 */     this.convexB = shapeB;
/*  63 */     this.simplexSolver = simplexSolver;
/*     */   }
/*     */ 
/*     */   public boolean calcTimeOfImpact(Transform arg1, Transform arg2, Transform arg3, Transform arg4, ConvexCast.CastResult arg5) {
/*  67 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  69 */       this.simplexSolver.reset();
/*     */ 
/*  71 */       Vector3f linVelA = localStack.get$javax$vecmath$Vector3f();
/*  72 */       Vector3f linVelB = localStack.get$javax$vecmath$Vector3f();
/*  73 */       linVelA.sub(toA.origin, fromA.origin);
/*  74 */       linVelB.sub(toB.origin, fromB.origin);
/*     */ 
/*  76 */       float lambda = 0.0F;
/*     */ 
/*  78 */       Transform interpolatedTransA = localStack.get$com$bulletphysics$linearmath$Transform(fromA);
/*  79 */       Transform interpolatedTransB = localStack.get$com$bulletphysics$linearmath$Transform(fromB);
/*     */ 
/*  82 */       Vector3f r = localStack.get$javax$vecmath$Vector3f();
/*  83 */       r.sub(linVelA, linVelB);
/*     */ 
/*  85 */       Vector3f v = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  87 */       tmp.negate(r);
/*  88 */       MatrixUtil.transposeTransform(tmp, tmp, fromA.basis);
/*  89 */       Vector3f supVertexA = this.convexA.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
/*  90 */       fromA.transform(supVertexA);
/*     */ 
/*  92 */       MatrixUtil.transposeTransform(tmp, r, fromB.basis);
/*  93 */       Vector3f supVertexB = this.convexB.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
/*  94 */       fromB.transform(supVertexB);
/*     */ 
/*  96 */       v.sub(supVertexA, supVertexB);
/*     */ 
/*  98 */       int maxIter = 32;
/*     */ 
/* 100 */       Vector3f n = localStack.get$javax$vecmath$Vector3f();
/* 101 */       n.set(0.0F, 0.0F, 0.0F);
/* 102 */       boolean hasResult = false;
/* 103 */       Vector3f c = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 105 */       float lastLambda = lambda;
/*     */ 
/* 107 */       float dist2 = v.lengthSquared();
/*     */ 
/* 111 */       float epsilon = 1.0E-004F;
/*     */ 
/* 113 */       Vector3f w = localStack.get$javax$vecmath$Vector3f(); Vector3f p = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 116 */       while ((dist2 > epsilon) && (maxIter-- != 0)) {
/* 117 */         tmp.negate(v);
/* 118 */         MatrixUtil.transposeTransform(tmp, tmp, interpolatedTransA.basis);
/* 119 */         this.convexA.localGetSupportingVertex(tmp, supVertexA);
/* 120 */         interpolatedTransA.transform(supVertexA);
/*     */ 
/* 122 */         MatrixUtil.transposeTransform(tmp, v, interpolatedTransB.basis);
/* 123 */         this.convexB.localGetSupportingVertex(tmp, supVertexB);
/* 124 */         interpolatedTransB.transform(supVertexB);
/*     */ 
/* 126 */         w.sub(supVertexA, supVertexB);
/*     */ 
/* 128 */         float VdotW = v.dot(w);
/*     */ 
/* 130 */         if (lambda > 1.0F) {
/* 131 */           return false;
/*     */         }
/*     */ 
/* 134 */         if (VdotW > 0.0F) {
/* 135 */           float VdotR = v.dot(r);
/*     */ 
/* 137 */           if (VdotR >= -1.421086E-014F) {
/* 138 */             return false;
/*     */           }
/*     */ 
/* 141 */           lambda -= VdotW / VdotR;
/*     */ 
/* 145 */           VectorUtil.setInterpolate3(interpolatedTransA.origin, fromA.origin, toA.origin, lambda);
/* 146 */           VectorUtil.setInterpolate3(interpolatedTransB.origin, fromB.origin, toB.origin, lambda);
/*     */ 
/* 149 */           w.sub(supVertexA, supVertexB);
/* 150 */           lastLambda = lambda;
/* 151 */           n.set(v);
/* 152 */           hasResult = true;
/*     */         }
/*     */ 
/* 155 */         this.simplexSolver.addVertex(w, supVertexA, supVertexB);
/* 156 */         if (this.simplexSolver.closest(v)) {
/* 157 */           dist2 = v.lengthSquared();
/* 158 */           hasResult = true;
/*     */         }
/*     */         else
/*     */         {
/* 166 */           dist2 = 0.0F;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 175 */       result.fraction = lambda;
/* 176 */       if (n.lengthSquared() >= 1.421086E-014F) {
/* 177 */         result.normal.normalize(n);
/*     */       }
/*     */       else {
/* 180 */         result.normal.set(0.0F, 0.0F, 0.0F);
/*     */       }
/*     */ 
/* 184 */       if (result.normal.dot(r) >= -result.allowedPenetration) {
/* 185 */         return false;
/*     */       }
/* 187 */       Vector3f hitA = localStack.get$javax$vecmath$Vector3f();
/* 188 */       Vector3f hitB = localStack.get$javax$vecmath$Vector3f();
/* 189 */       this.simplexSolver.compute_points(hitA, hitB);
/* 190 */       result.hitPoint.set(hitB);
/* 191 */       return true;
/*     */     }
/*     */     finally
/*     */     {
/* 191 */       .Stack tmp644_642 = localStack; tmp644_642.pop$com$bulletphysics$linearmath$Transform(); tmp644_642.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.SubsimplexConvexCast
 * JD-Core Version:    0.6.2
 */