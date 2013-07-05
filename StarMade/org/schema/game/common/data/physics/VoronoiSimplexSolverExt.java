/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.narrowphase.SimplexSolverInterface;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class VoronoiSimplexSolverExt extends SimplexSolverInterface
/*     */ {
/*  41 */   private static ThreadLocal threadLocal = new VoronoiSimplexSolverExt.1();
/*     */   protected final ObjectPool subsimplexResultsPool;
/*     */   private static final int VORONOI_SIMPLEX_MAX_VERTS = 5;
/*     */   private static final int VERTA = 0;
/*     */   private static final int VERTB = 1;
/*     */   private static final int VERTC = 2;
/*     */   private static final int VERTD = 3;
/*     */   private final VoronoiSimplexSolverVariables v;
/*     */   public int numVertices;
/*     */   public final Vector3f[] simplexVectorW;
/*     */   public final Vector3f[] simplexPointsP;
/*     */   public final Vector3f[] simplexPointsQ;
/*     */   public final Vector3f cachedP1;
/*     */   public final Vector3f cachedP2;
/*     */   public final Vector3f cachedV;
/*     */   public final Vector3f lastW;
/*     */   public boolean cachedValidClosest;
/*     */   public final VoronoiSimplexSolverExt.SubSimplexClosestResult cachedBC;
/*     */   public boolean needsUpdate;
/*     */ 
/*     */   public VoronoiSimplexSolverExt()
/*     */   {
/*  52 */     this.subsimplexResultsPool = ObjectPool.get(VoronoiSimplexSolverExt.SubSimplexClosestResult.class);
/*     */ 
/*  65 */     this.simplexVectorW = new Vector3f[5];
/*  66 */     this.simplexPointsP = new Vector3f[5];
/*  67 */     this.simplexPointsQ = new Vector3f[5];
/*     */ 
/*  69 */     this.cachedP1 = new Vector3f();
/*  70 */     this.cachedP2 = new Vector3f();
/*  71 */     this.cachedV = new Vector3f();
/*  72 */     this.lastW = new Vector3f();
/*     */ 
/*  75 */     this.cachedBC = new VoronoiSimplexSolverExt.SubSimplexClosestResult();
/*     */ 
/*  80 */     for (int i = 0; i < 5; i++) {
/*  81 */       this.simplexVectorW[i] = new Vector3f();
/*  82 */       this.simplexPointsP[i] = new Vector3f();
/*  83 */       this.simplexPointsQ[i] = new Vector3f();
/*     */     }
/*  48 */     this.v = ((VoronoiSimplexSolverVariables)threadLocal.get());
/*     */   }
/*     */ 
/*     */   public void removeVertex(int paramInt)
/*     */   {
/*  88 */     assert (this.numVertices > 0);
/*  89 */     this.numVertices -= 1;
/*  90 */     this.simplexVectorW[paramInt].set(this.simplexVectorW[this.numVertices]);
/*  91 */     this.simplexPointsP[paramInt].set(this.simplexPointsP[this.numVertices]);
/*  92 */     this.simplexPointsQ[paramInt].set(this.simplexPointsQ[this.numVertices]);
/*     */   }
/*     */ 
/*     */   public void reduceVertices(VoronoiSimplexSolverExt.UsageBitfield paramUsageBitfield) {
/*  96 */     if ((numVertices() >= 4) && (!paramUsageBitfield.usedVertexD)) {
/*  97 */       removeVertex(3);
/*     */     }
/*  99 */     if ((numVertices() >= 3) && (!paramUsageBitfield.usedVertexC)) {
/* 100 */       removeVertex(2);
/*     */     }
/* 102 */     if ((numVertices() >= 2) && (!paramUsageBitfield.usedVertexB)) {
/* 103 */       removeVertex(1);
/*     */     }
/* 105 */     if ((numVertices() > 0) && (!paramUsageBitfield.usedVertexA))
/* 106 */       removeVertex(0);
/*     */   }
/*     */ 
/*     */   public boolean updateClosestVectorAndPoints() {
/* 110 */     if (this.needsUpdate)
/*     */     {
/* 112 */       this.cachedBC.reset();
/*     */ 
/* 114 */       this.needsUpdate = false;
/*     */       Vector3f localVector3f1;
/*     */       Vector3f localVector3f2;
/*     */       Vector3f localVector3f3;
/*     */       Vector3f localVector3f4;
/*     */       Vector3f localVector3f5;
/*     */       Vector3f localVector3f6;
/*     */       Vector3f localVector3f7;
/* 116 */       switch (numVertices())
/*     */       {
/*     */       case 0:
/* 119 */         this.cachedValidClosest = false;
/* 120 */         break;
/*     */       case 1:
/* 123 */         this.cachedP1.set(this.simplexPointsP[0]);
/* 124 */         this.cachedP2.set(this.simplexPointsQ[0]);
/* 125 */         this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 126 */         this.cachedBC.reset();
/* 127 */         this.cachedBC.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 128 */         this.cachedValidClosest = this.cachedBC.isValid();
/* 129 */         break;
/*     */       case 2:
/* 133 */         localVector3f1 = this.v.tmp;
/*     */ 
/* 136 */         localVector3f2 = this.simplexVectorW[0];
/* 137 */         localVector3f3 = this.simplexVectorW[1];
/* 138 */         localVector3f4 = this.v.nearest;
/*     */ 
/* 140 */         (
/* 141 */           localVector3f5 = this.v.p)
/* 141 */           .set(0.0F, 0.0F, 0.0F);
/* 142 */         (
/* 143 */           localVector3f6 = this.v.diff)
/* 143 */           .sub(localVector3f5, localVector3f2);
/*     */ 
/* 145 */         (
/* 146 */           localVector3f7 = this.v.v)
/* 146 */           .sub(localVector3f3, localVector3f2);
/*     */         float f1;
/* 150 */         if ((
/* 150 */           f1 = localVector3f7.dot(localVector3f6)) > 
/* 150 */           0.0F) {
/* 151 */           float f2 = localVector3f7.dot(localVector3f7);
/* 152 */           if (f1 < f2) {
/* 153 */             f1 /= f2;
/* 154 */             localVector3f1.scale(f1, localVector3f7);
/* 155 */             localVector3f6.sub(localVector3f1);
/* 156 */             this.cachedBC.usedVertices.usedVertexA = true;
/* 157 */             this.cachedBC.usedVertices.usedVertexB = true;
/*     */           } else {
/* 159 */             f1 = 1.0F;
/* 160 */             localVector3f6.sub(localVector3f7);
/*     */ 
/* 162 */             this.cachedBC.usedVertices.usedVertexB = true;
/*     */           }
/*     */         }
/*     */         else {
/* 166 */           f1 = 0.0F;
/*     */ 
/* 168 */           this.cachedBC.usedVertices.usedVertexA = true;
/*     */         }
/* 170 */         this.cachedBC.setBarycentricCoordinates(1.0F - f1, f1, 0.0F, 0.0F);
/*     */ 
/* 172 */         localVector3f1.scale(f1, localVector3f7);
/* 173 */         localVector3f4.add(localVector3f2, localVector3f1);
/*     */ 
/* 175 */         localVector3f1.sub(this.simplexPointsP[1], this.simplexPointsP[0]);
/* 176 */         localVector3f1.scale(f1);
/* 177 */         this.cachedP1.add(this.simplexPointsP[0], localVector3f1);
/*     */ 
/* 179 */         localVector3f1.sub(this.simplexPointsQ[1], this.simplexPointsQ[0]);
/* 180 */         localVector3f1.scale(f1);
/* 181 */         this.cachedP2.add(this.simplexPointsQ[0], localVector3f1);
/*     */ 
/* 183 */         this.cachedV.sub(this.cachedP1, this.cachedP2);
/*     */ 
/* 185 */         reduceVertices(this.cachedBC.usedVertices);
/*     */ 
/* 187 */         this.cachedValidClosest = this.cachedBC.isValid();
/* 188 */         break;
/*     */       case 3:
/* 192 */         localVector3f1 = this.v.tmp1;
/* 193 */         localVector3f2 = this.v.tmp2;
/* 194 */         localVector3f3 = this.v.tmp3;
/*     */ 
/* 197 */         (
/* 198 */           localVector3f4 = this.v.p)
/* 198 */           .set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 200 */         localVector3f5 = this.simplexVectorW[0];
/* 201 */         localVector3f6 = this.simplexVectorW[1];
/* 202 */         localVector3f7 = this.simplexVectorW[2];
/*     */ 
/* 204 */         closestPtPointTriangle(localVector3f4, localVector3f5, localVector3f6, localVector3f7, this.cachedBC);
/*     */ 
/* 206 */         localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 207 */         localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 208 */         localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 209 */         VectorUtil.add(this.cachedP1, localVector3f1, localVector3f2, localVector3f3);
/*     */ 
/* 211 */         localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 212 */         localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 213 */         localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 214 */         VectorUtil.add(this.cachedP2, localVector3f1, localVector3f2, localVector3f3);
/*     */ 
/* 216 */         this.cachedV.sub(this.cachedP1, this.cachedP2);
/*     */ 
/* 218 */         reduceVertices(this.cachedBC.usedVertices);
/* 219 */         this.cachedValidClosest = this.cachedBC.isValid();
/*     */ 
/* 221 */         break;
/*     */       case 4:
/* 225 */         localVector3f1 = this.v.tmp1;
/* 226 */         localVector3f2 = this.v.tmp2;
/* 227 */         localVector3f3 = this.v.tmp3;
/* 228 */         localVector3f4 = this.v.tmp4;
/*     */ 
/* 230 */         (
/* 231 */           localVector3f5 = this.v.p)
/* 231 */           .set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 233 */         localVector3f6 = this.simplexVectorW[0];
/* 234 */         localVector3f7 = this.simplexVectorW[1];
/* 235 */         Vector3f localVector3f8 = this.simplexVectorW[2];
/* 236 */         Vector3f localVector3f9 = this.simplexVectorW[3];
/*     */ 
/* 240 */         if (closestPtPointTetrahedron(localVector3f5, localVector3f6, localVector3f7, localVector3f8, localVector3f9, this.cachedBC))
/*     */         {
/* 242 */           localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 243 */           localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 244 */           localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 245 */           localVector3f4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsP[3]);
/* 246 */           VectorUtil.add(this.cachedP1, localVector3f1, localVector3f2, localVector3f3, localVector3f4);
/*     */ 
/* 248 */           localVector3f1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 249 */           localVector3f2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 250 */           localVector3f3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 251 */           localVector3f4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsQ[3]);
/* 252 */           VectorUtil.add(this.cachedP2, localVector3f1, localVector3f2, localVector3f3, localVector3f4);
/*     */ 
/* 254 */           this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 255 */           reduceVertices(this.cachedBC.usedVertices);
/*     */         }
/*     */         else
/*     */         {
/* 260 */           if (this.cachedBC.degenerate)
/*     */             break;
/* 262 */           this.cachedValidClosest = true;
/*     */ 
/* 267 */           this.cachedV.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 269 */           break label1084;
/*     */         }
/*     */ 
/* 272 */         this.cachedValidClosest = this.cachedBC.isValid();
/*     */ 
/* 275 */         break;
/*     */       }
/*     */ 
/* 279 */       this.cachedValidClosest = false;
/*     */     }
/*     */ 
/* 284 */     label1084: return this.cachedValidClosest;
/*     */   }
/*     */ 
/*     */   public boolean closestPtPointTriangle(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, VoronoiSimplexSolverExt.SubSimplexClosestResult paramSubSimplexClosestResult)
/*     */   {
/* 289 */     paramSubSimplexClosestResult.usedVertices.reset();
/*     */     Vector3f localVector3f1;
/* 292 */     (
/* 293 */       localVector3f1 = this.v.ab)
/* 293 */       .sub(paramVector3f3, paramVector3f2);
/*     */     Vector3f localVector3f2;
/* 295 */     (
/* 296 */       localVector3f2 = this.v.ac)
/* 296 */       .sub(paramVector3f4, paramVector3f2);
/*     */     Vector3f localVector3f3;
/* 298 */     (
/* 299 */       localVector3f3 = this.v.ap)
/* 299 */       .sub(paramVector3f1, paramVector3f2);
/*     */ 
/* 301 */     float f3 = localVector3f1.dot(localVector3f3);
/* 302 */     float f1 = localVector3f2.dot(localVector3f3);
/*     */ 
/* 304 */     if ((f3 <= 0.0F) && (f1 <= 0.0F))
/*     */     {
/* 306 */       paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f2);
/* 307 */       paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 308 */       paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 309 */       return true;
/*     */     }
/*     */     Vector3f localVector3f5;
/* 313 */     (
/* 314 */       localVector3f5 = this.v.bp)
/* 314 */       .sub(paramVector3f1, paramVector3f3);
/*     */ 
/* 316 */     float f5 = localVector3f1.dot(localVector3f5);
/* 317 */     float f4 = localVector3f2.dot(localVector3f5);
/*     */ 
/* 319 */     if ((f5 >= 0.0F) && (f4 <= f5))
/*     */     {
/* 321 */       paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f3);
/* 322 */       paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 323 */       paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 1.0F, 0.0F, 0.0F);
/*     */ 
/* 325 */       return true;
/*     */     }
/*     */     float f6;
/* 330 */     if (((
/* 330 */       f6 = f3 * f4 - f5 * f1) <= 
/* 330 */       0.0F) && (f3 >= 0.0F) && (f5 <= 0.0F)) {
/* 331 */       float f7 = f3 / (f3 - f5);
/* 332 */       paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(f7, localVector3f1, paramVector3f2);
/* 333 */       paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 334 */       paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 335 */       paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f7, f7, 0.0F, 0.0F);
/* 336 */       return true;
/*     */     }
/*     */     Vector3f localVector3f6;
/* 341 */     (
/* 342 */       localVector3f6 = this.v.cp)
/* 342 */       .sub(paramVector3f1, paramVector3f4);
/*     */ 
/* 344 */     paramVector3f1 = localVector3f1.dot(localVector3f6);
/*     */     float f8;
/* 347 */     if (((
/* 347 */       f8 = localVector3f2.dot(localVector3f6)) >= 
/* 347 */       0.0F) && (paramVector3f1 <= f8))
/*     */     {
/* 349 */       paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f4);
/* 350 */       paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 351 */       paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 0.0F, 1.0F, 0.0F);
/* 352 */       return true;
/*     */     }
/*     */ 
/* 357 */     if (((
/* 357 */       f3 = paramVector3f1 * f1 - f3 * f8) <= 
/* 357 */       0.0F) && (f1 >= 0.0F) && (f8 <= 0.0F)) {
/* 358 */       f1 /= (f1 - f8);
/* 359 */       paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(f1, localVector3f2, paramVector3f2);
/* 360 */       paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 361 */       paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 362 */       paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f1, 0.0F, f1, 0.0F);
/* 363 */       return true;
/*     */     }
/*     */     Vector3f localVector3f4;
/* 369 */     if (((
/* 369 */       f1 = f5 * f8 - paramVector3f1 * f4) <= 
/* 369 */       0.0F) && (f4 - f5 >= 0.0F) && (paramVector3f1 - f8 >= 0.0F)) {
/* 370 */       paramVector3f1 = (f4 - f5) / (f4 - f5 + (paramVector3f1 - f8));
/*     */ 
/* 372 */       (
/* 373 */         localVector3f4 = this.v.tmptmp)
/* 373 */         .sub(paramVector3f4, paramVector3f3);
/* 374 */       paramSubSimplexClosestResult.closestPointOnSimplex.scaleAdd(paramVector3f1, localVector3f4, paramVector3f3);
/*     */ 
/* 376 */       paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 377 */       paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 378 */       paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, 1.0F - paramVector3f1, paramVector3f1, 0.0F);
/* 379 */       return true;
/*     */     }
/*     */ 
/* 384 */     paramVector3f1 = 1.0F / (localVector3f4 + f3 + f6);
/* 385 */     float f2 = f3 * paramVector3f1;
/* 386 */     paramVector3f1 = f6 * paramVector3f1;
/*     */ 
/* 388 */     paramVector3f3 = this.v.tmptmp1;
/* 389 */     paramVector3f4 = this.v.tmptmp2;
/*     */ 
/* 391 */     paramVector3f3.scale(f2, localVector3f1);
/* 392 */     paramVector3f4.scale(paramVector3f1, localVector3f2);
/* 393 */     VectorUtil.add(paramSubSimplexClosestResult.closestPointOnSimplex, paramVector3f2, paramVector3f3, paramVector3f4);
/* 394 */     paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 395 */     paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 396 */     paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 397 */     paramSubSimplexClosestResult.setBarycentricCoordinates(1.0F - f2 - paramVector3f1, f2, paramVector3f1, 0.0F);
/*     */ 
/* 399 */     return true;
/*     */   }
/*     */ 
/*     */   public static int pointOutsideOfPlane(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, VoronoiSimplexSolverVariables paramVoronoiSimplexSolverVariables)
/*     */   {
/* 407 */     Vector3f localVector3f = paramVoronoiSimplexSolverVariables.tmpTmpTmp;
/*     */ 
/* 409 */     (
/* 410 */       paramVoronoiSimplexSolverVariables = paramVoronoiSimplexSolverVariables.normal)
/* 410 */       .sub(paramVector3f3, paramVector3f2);
/* 411 */     localVector3f.sub(paramVector3f4, paramVector3f2);
/* 412 */     paramVoronoiSimplexSolverVariables.cross(paramVoronoiSimplexSolverVariables, localVector3f);
/*     */ 
/* 414 */     localVector3f.sub(paramVector3f1, paramVector3f2);
/* 415 */     paramVector3f1 = localVector3f.dot(paramVoronoiSimplexSolverVariables);
/*     */ 
/* 417 */     localVector3f.sub(paramVector3f5, paramVector3f2);
/*     */     float tmp66_63 = localVector3f.dot(paramVoronoiSimplexSolverVariables);
/*     */ 
/* 427 */     if (tmp66_63 * (
/* 427 */       paramVector3f2 = tmp66_63) < 
/* 427 */       9.999999E-009F)
/*     */     {
/* 430 */       return -1;
/*     */     }
/*     */ 
/* 436 */     if (paramVector3f1 * paramVector3f2 < 0.0F) return 1; return 0;
/*     */   }
/*     */ 
/*     */   public boolean closestPtPointTetrahedron(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, Vector3f paramVector3f5, VoronoiSimplexSolverExt.SubSimplexClosestResult paramSubSimplexClosestResult)
/*     */   {
/*     */     VoronoiSimplexSolverExt.SubSimplexClosestResult localSubSimplexClosestResult;
/* 441 */     (
/* 442 */       localSubSimplexClosestResult = (VoronoiSimplexSolverExt.SubSimplexClosestResult)this.subsimplexResultsPool.get())
/* 442 */       .reset();
/*     */     try {
/* 444 */       Vector3f localVector3f1 = this.v.tmptmptmptmp;
/* 445 */       Vector3f localVector3f2 = this.v.q;
/*     */ 
/* 448 */       paramSubSimplexClosestResult.closestPointOnSimplex.set(paramVector3f1);
/* 449 */       paramSubSimplexClosestResult.usedVertices.reset();
/* 450 */       paramSubSimplexClosestResult.usedVertices.usedVertexA = true;
/* 451 */       paramSubSimplexClosestResult.usedVertices.usedVertexB = true;
/* 452 */       paramSubSimplexClosestResult.usedVertices.usedVertexC = true;
/* 453 */       paramSubSimplexClosestResult.usedVertices.usedVertexD = true;
/*     */ 
/* 455 */       int i = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f3, paramVector3f4, paramVector3f5, this.v);
/* 456 */       int j = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f4, paramVector3f5, paramVector3f3, this.v);
/* 457 */       int k = pointOutsideOfPlane(paramVector3f1, paramVector3f2, paramVector3f5, paramVector3f3, paramVector3f4, this.v);
/* 458 */       int m = pointOutsideOfPlane(paramVector3f1, paramVector3f3, paramVector3f5, paramVector3f4, paramVector3f2, this.v);
/*     */ 
/* 460 */       if ((i < 0) || (j < 0) || (k < 0) || (m < 0))
/*     */       {
/* 462 */         paramSubSimplexClosestResult.degenerate = true;
/* 463 */         return false;
/*     */       }
/*     */ 
/* 466 */       if ((i == 0) && (j == 0) && (k == 0) && (m == 0))
/*     */       {
/* 468 */         return false;
/*     */       }
/*     */ 
/* 472 */       float f2 = 3.4028235E+38F;
/*     */       float f1;
/* 474 */       if (i != 0)
/*     */       {
/* 476 */         closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f3, paramVector3f4, localSubSimplexClosestResult);
/* 477 */         localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/*     */ 
/* 479 */         localVector3f1.sub(localVector3f2, paramVector3f1);
/*     */ 
/* 482 */         if ((
/* 482 */           f1 = localVector3f1.dot(localVector3f1)) < 
/* 482 */           3.4028235E+38F) {
/* 483 */           f2 = f1;
/* 484 */           paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/*     */ 
/* 486 */           paramSubSimplexClosestResult.usedVertices.reset();
/* 487 */           paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 488 */           paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 489 */           paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 490 */           paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[1], localSubSimplexClosestResult.barycentricCoords[2], 0.0F);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 502 */       if (j != 0)
/*     */       {
/* 504 */         closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f4, paramVector3f5, localSubSimplexClosestResult);
/* 505 */         localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/*     */ 
/* 508 */         localVector3f1.sub(localVector3f2, paramVector3f1);
/*     */ 
/* 510 */         if ((
/* 510 */           f1 = localVector3f1.dot(localVector3f1)) < 
/* 510 */           f2)
/*     */         {
/* 512 */           f2 = f1;
/* 513 */           paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/* 514 */           paramSubSimplexClosestResult.usedVertices.reset();
/* 515 */           paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
/*     */ 
/* 517 */           paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 518 */           paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 519 */           paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], 0.0F, localSubSimplexClosestResult.barycentricCoords[1], localSubSimplexClosestResult.barycentricCoords[2]);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 531 */       if (k != 0)
/*     */       {
/* 533 */         closestPtPointTriangle(paramVector3f1, paramVector3f2, paramVector3f5, paramVector3f3, localSubSimplexClosestResult);
/* 534 */         localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/*     */ 
/* 537 */         localVector3f1.sub(localVector3f2, paramVector3f1);
/*     */ 
/* 539 */         if ((
/* 539 */           f1 = localVector3f1.dot(localVector3f1)) < 
/* 539 */           f2)
/*     */         {
/* 541 */           f2 = f1;
/* 542 */           paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/* 543 */           paramSubSimplexClosestResult.usedVertices.reset();
/* 544 */           paramSubSimplexClosestResult.usedVertices.usedVertexA = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 545 */           paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexC;
/*     */ 
/* 547 */           paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexB;
/* 548 */           paramSubSimplexClosestResult.setBarycentricCoordinates(localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[2], 0.0F, localSubSimplexClosestResult.barycentricCoords[1]);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 560 */       if (m != 0)
/*     */       {
/* 562 */         closestPtPointTriangle(paramVector3f1, paramVector3f3, paramVector3f5, paramVector3f4, localSubSimplexClosestResult);
/* 563 */         localVector3f2.set(localSubSimplexClosestResult.closestPointOnSimplex);
/*     */ 
/* 565 */         localVector3f1.sub(localVector3f2, paramVector3f1);
/*     */ 
/* 567 */         if (localVector3f1.dot(localVector3f1) < 
/* 567 */           f2)
/*     */         {
/* 569 */           paramSubSimplexClosestResult.closestPointOnSimplex.set(localVector3f2);
/*     */ 
/* 571 */           paramSubSimplexClosestResult.usedVertices.reset();
/*     */ 
/* 573 */           paramSubSimplexClosestResult.usedVertices.usedVertexB = localSubSimplexClosestResult.usedVertices.usedVertexA;
/* 574 */           paramSubSimplexClosestResult.usedVertices.usedVertexC = localSubSimplexClosestResult.usedVertices.usedVertexC;
/* 575 */           paramSubSimplexClosestResult.usedVertices.usedVertexD = localSubSimplexClosestResult.usedVertices.usedVertexB;
/*     */ 
/* 577 */           paramSubSimplexClosestResult.setBarycentricCoordinates(0.0F, localSubSimplexClosestResult.barycentricCoords[0], localSubSimplexClosestResult.barycentricCoords[2], localSubSimplexClosestResult.barycentricCoords[1]);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 589 */       if ((paramSubSimplexClosestResult.usedVertices.usedVertexA) && (paramSubSimplexClosestResult.usedVertices.usedVertexB) && (paramSubSimplexClosestResult.usedVertices.usedVertexC) && (paramSubSimplexClosestResult.usedVertices.usedVertexD))
/*     */       {
/* 594 */         return true;
/*     */       }
/*     */ 
/* 597 */       return true; } finally { this.subsimplexResultsPool.release(localSubSimplexClosestResult); } throw paramVector3f1;
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 608 */     this.cachedValidClosest = false;
/* 609 */     this.numVertices = 0;
/* 610 */     this.needsUpdate = true;
/* 611 */     this.lastW.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 612 */     this.cachedBC.reset();
/*     */   }
/*     */ 
/*     */   public void addVertex(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3) {
/* 616 */     this.lastW.set(paramVector3f1);
/* 617 */     this.needsUpdate = true;
/*     */ 
/* 619 */     this.simplexVectorW[this.numVertices].set(paramVector3f1);
/* 620 */     this.simplexPointsP[this.numVertices].set(paramVector3f2);
/* 621 */     this.simplexPointsQ[this.numVertices].set(paramVector3f3);
/*     */ 
/* 623 */     this.numVertices += 1;
/*     */   }
/*     */ 
/*     */   public boolean closest(Vector3f paramVector3f)
/*     */   {
/* 630 */     boolean bool = updateClosestVectorAndPoints();
/* 631 */     paramVector3f.set(this.cachedV);
/* 632 */     return bool;
/*     */   }
/*     */ 
/*     */   public float maxVertex() {
/* 636 */     int j = numVertices();
/* 637 */     float f1 = 0.0F;
/* 638 */     for (int i = 0; i < j; i++) {
/* 639 */       float f2 = this.simplexVectorW[i].lengthSquared();
/* 640 */       if (f1 < f2) {
/* 641 */         f1 = f2;
/*     */       }
/*     */     }
/* 644 */     return f1;
/*     */   }
/*     */ 
/*     */   public boolean fullSimplex() {
/* 648 */     return this.numVertices == 4;
/*     */   }
/*     */ 
/*     */   public int getSimplex(Vector3f[] paramArrayOfVector3f1, Vector3f[] paramArrayOfVector3f2, Vector3f[] paramArrayOfVector3f3) {
/* 652 */     for (int i = 0; i < numVertices(); i++) {
/* 653 */       paramArrayOfVector3f3[i].set(this.simplexVectorW[i]);
/* 654 */       paramArrayOfVector3f1[i].set(this.simplexPointsP[i]);
/* 655 */       paramArrayOfVector3f2[i].set(this.simplexPointsQ[i]);
/*     */     }
/* 657 */     return numVertices();
/*     */   }
/*     */ 
/*     */   public boolean inSimplex(Vector3f paramVector3f) {
/* 661 */     boolean bool = false;
/* 662 */     int j = numVertices();
/*     */ 
/* 666 */     for (int i = 0; i < j; i++) {
/* 667 */       if (this.simplexVectorW[i].equals(paramVector3f)) {
/* 668 */         bool = true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 673 */     if (paramVector3f.equals(this.lastW)) {
/* 674 */       return true;
/*     */     }
/*     */ 
/* 677 */     return bool;
/*     */   }
/*     */ 
/*     */   public void backup_closest(Vector3f paramVector3f) {
/* 681 */     paramVector3f.set(this.cachedV);
/*     */   }
/*     */ 
/*     */   public boolean emptySimplex() {
/* 685 */     return numVertices() == 0;
/*     */   }
/*     */ 
/*     */   public void compute_points(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 689 */     updateClosestVectorAndPoints();
/* 690 */     paramVector3f1.set(this.cachedP1);
/* 691 */     paramVector3f2.set(this.cachedP2);
/*     */   }
/*     */ 
/*     */   public int numVertices() {
/* 695 */     return this.numVertices;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.VoronoiSimplexSolverExt
 * JD-Core Version:    0.6.2
 */