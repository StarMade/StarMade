/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class VoronoiSimplexSolver extends SimplexSolverInterface
/*     */ {
/*     */   protected final ObjectPool<SubSimplexClosestResult> subsimplexResultsPool;
/*     */   private static final int VORONOI_SIMPLEX_MAX_VERTS = 5;
/*     */   private static final int VERTA = 0;
/*     */   private static final int VERTB = 1;
/*     */   private static final int VERTC = 2;
/*     */   private static final int VERTD = 3;
/*     */   public int numVertices;
/*     */   public final Vector3f[] simplexVectorW;
/*     */   public final Vector3f[] simplexPointsP;
/*     */   public final Vector3f[] simplexPointsQ;
/*     */   public final Vector3f cachedP1;
/*     */   public final Vector3f cachedP2;
/*     */   public final Vector3f cachedV;
/*     */   public final Vector3f lastW;
/*     */   public boolean cachedValidClosest;
/*     */   public final SubSimplexClosestResult cachedBC;
/*     */   public boolean needsUpdate;
/*     */ 
/*     */   public VoronoiSimplexSolver()
/*     */   {
/*  42 */     this.subsimplexResultsPool = ObjectPool.get(SubSimplexClosestResult.class);
/*     */ 
/*  53 */     this.simplexVectorW = new Vector3f[5];
/*  54 */     this.simplexPointsP = new Vector3f[5];
/*  55 */     this.simplexPointsQ = new Vector3f[5];
/*     */ 
/*  57 */     this.cachedP1 = new Vector3f();
/*  58 */     this.cachedP2 = new Vector3f();
/*  59 */     this.cachedV = new Vector3f();
/*  60 */     this.lastW = new Vector3f();
/*     */ 
/*  63 */     this.cachedBC = new SubSimplexClosestResult();
/*     */ 
/*  68 */     for (int i = 0; i < 5; i++) {
/*  69 */       this.simplexVectorW[i] = new Vector3f();
/*  70 */       this.simplexPointsP[i] = new Vector3f();
/*  71 */       this.simplexPointsQ[i] = new Vector3f();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeVertex(int index) {
/*  76 */     assert (this.numVertices > 0);
/*  77 */     this.numVertices -= 1;
/*  78 */     this.simplexVectorW[index].set(this.simplexVectorW[this.numVertices]);
/*  79 */     this.simplexPointsP[index].set(this.simplexPointsP[this.numVertices]);
/*  80 */     this.simplexPointsQ[index].set(this.simplexPointsQ[this.numVertices]);
/*     */   }
/*     */ 
/*     */   public void reduceVertices(UsageBitfield usedVerts) {
/*  84 */     if ((numVertices() >= 4) && (!usedVerts.usedVertexD)) {
/*  85 */       removeVertex(3);
/*     */     }
/*  87 */     if ((numVertices() >= 3) && (!usedVerts.usedVertexC)) {
/*  88 */       removeVertex(2);
/*     */     }
/*  90 */     if ((numVertices() >= 2) && (!usedVerts.usedVertexB)) {
/*  91 */       removeVertex(1);
/*     */     }
/*  93 */     if ((numVertices() >= 1) && (!usedVerts.usedVertexA))
/*  94 */       removeVertex(0);
/*     */   }
/*     */ 
/*     */   public boolean updateClosestVectorAndPoints()
/*     */   {
/*  99 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (this.needsUpdate)
/*     */       {
/* 101 */         this.cachedBC.reset();
/*     */ 
/* 103 */         this.needsUpdate = false;
/*     */ 
/* 105 */         switch (numVertices())
/*     */         {
/*     */         case 0:
/* 108 */           this.cachedValidClosest = false;
/* 109 */           break;
/*     */         case 1:
/* 112 */           this.cachedP1.set(this.simplexPointsP[0]);
/* 113 */           this.cachedP2.set(this.simplexPointsQ[0]);
/* 114 */           this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 115 */           this.cachedBC.reset();
/* 116 */           this.cachedBC.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 117 */           this.cachedValidClosest = this.cachedBC.isValid();
/* 118 */           break;
/*     */         case 2:
/* 122 */           Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 125 */           Vector3f from = this.simplexVectorW[0];
/* 126 */           Vector3f to = this.simplexVectorW[1];
/* 127 */           Vector3f nearest = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 129 */           Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 130 */           p.set(0.0F, 0.0F, 0.0F);
/* 131 */           Vector3f diff = localStack.get$javax$vecmath$Vector3f();
/* 132 */           diff.sub(p, from);
/*     */ 
/* 134 */           Vector3f v = localStack.get$javax$vecmath$Vector3f();
/* 135 */           v.sub(to, from);
/*     */ 
/* 137 */           float t = v.dot(diff);
/*     */ 
/* 139 */           if (t > 0.0F) {
/* 140 */             float dotVV = v.dot(v);
/* 141 */             if (t < dotVV) {
/* 142 */               t /= dotVV;
/* 143 */               tmp.scale(t, v);
/* 144 */               diff.sub(tmp);
/* 145 */               this.cachedBC.usedVertices.usedVertexA = true;
/* 146 */               this.cachedBC.usedVertices.usedVertexB = true;
/*     */             } else {
/* 148 */               t = 1.0F;
/* 149 */               diff.sub(v);
/*     */ 
/* 151 */               this.cachedBC.usedVertices.usedVertexB = true;
/*     */             }
/*     */           }
/*     */           else {
/* 155 */             t = 0.0F;
/*     */ 
/* 157 */             this.cachedBC.usedVertices.usedVertexA = true;
/*     */           }
/* 159 */           this.cachedBC.setBarycentricCoordinates(1.0F - t, t, 0.0F, 0.0F);
/*     */ 
/* 161 */           tmp.scale(t, v);
/* 162 */           nearest.add(from, tmp);
/*     */ 
/* 164 */           tmp.sub(this.simplexPointsP[1], this.simplexPointsP[0]);
/* 165 */           tmp.scale(t);
/* 166 */           this.cachedP1.add(this.simplexPointsP[0], tmp);
/*     */ 
/* 168 */           tmp.sub(this.simplexPointsQ[1], this.simplexPointsQ[0]);
/* 169 */           tmp.scale(t);
/* 170 */           this.cachedP2.add(this.simplexPointsQ[0], tmp);
/*     */ 
/* 172 */           this.cachedV.sub(this.cachedP1, this.cachedP2);
/*     */ 
/* 174 */           reduceVertices(this.cachedBC.usedVertices);
/*     */ 
/* 176 */           this.cachedValidClosest = this.cachedBC.isValid();
/* 177 */           break;
/*     */         case 3:
/* 181 */           Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 182 */           Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 183 */           Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 186 */           Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 187 */           p.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 189 */           Vector3f a = this.simplexVectorW[0];
/* 190 */           Vector3f b = this.simplexVectorW[1];
/* 191 */           Vector3f c = this.simplexVectorW[2];
/*     */ 
/* 193 */           closestPtPointTriangle(p, a, b, c, this.cachedBC);
/*     */ 
/* 195 */           tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 196 */           tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 197 */           tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 198 */           VectorUtil.add(this.cachedP1, tmp1, tmp2, tmp3);
/*     */ 
/* 200 */           tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 201 */           tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 202 */           tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 203 */           VectorUtil.add(this.cachedP2, tmp1, tmp2, tmp3);
/*     */ 
/* 205 */           this.cachedV.sub(this.cachedP1, this.cachedP2);
/*     */ 
/* 207 */           reduceVertices(this.cachedBC.usedVertices);
/* 208 */           this.cachedValidClosest = this.cachedBC.isValid();
/*     */ 
/* 210 */           break;
/*     */         case 4:
/* 214 */           Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 215 */           Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 216 */           Vector3f tmp3 = localStack.get$javax$vecmath$Vector3f();
/* 217 */           Vector3f tmp4 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 219 */           Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 220 */           p.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 222 */           Vector3f a = this.simplexVectorW[0];
/* 223 */           Vector3f b = this.simplexVectorW[1];
/* 224 */           Vector3f c = this.simplexVectorW[2];
/* 225 */           Vector3f d = this.simplexVectorW[3];
/*     */ 
/* 227 */           boolean hasSeperation = closestPtPointTetrahedron(p, a, b, c, d, this.cachedBC);
/*     */ 
/* 229 */           if (hasSeperation)
/*     */           {
/* 231 */             tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsP[0]);
/* 232 */             tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsP[1]);
/* 233 */             tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsP[2]);
/* 234 */             tmp4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsP[3]);
/* 235 */             VectorUtil.add(this.cachedP1, tmp1, tmp2, tmp3, tmp4);
/*     */ 
/* 237 */             tmp1.scale(this.cachedBC.barycentricCoords[0], this.simplexPointsQ[0]);
/* 238 */             tmp2.scale(this.cachedBC.barycentricCoords[1], this.simplexPointsQ[1]);
/* 239 */             tmp3.scale(this.cachedBC.barycentricCoords[2], this.simplexPointsQ[2]);
/* 240 */             tmp4.scale(this.cachedBC.barycentricCoords[3], this.simplexPointsQ[3]);
/* 241 */             VectorUtil.add(this.cachedP2, tmp1, tmp2, tmp3, tmp4);
/*     */ 
/* 243 */             this.cachedV.sub(this.cachedP1, this.cachedP2);
/* 244 */             reduceVertices(this.cachedBC.usedVertices);
/*     */           }
/*     */           else
/*     */           {
/* 249 */             if (this.cachedBC.degenerate)
/*     */             {
/* 251 */               this.cachedValidClosest = false; break;
/*     */             }
/*     */ 
/* 254 */             this.cachedValidClosest = true;
/*     */ 
/* 256 */             this.cachedV.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 258 */             break;
/*     */           }
/*     */ 
/* 261 */           this.cachedValidClosest = this.cachedBC.isValid();
/*     */ 
/* 264 */           break;
/*     */         default:
/* 268 */           this.cachedValidClosest = false;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 273 */       return this.cachedValidClosest; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean closestPtPointTriangle(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, SubSimplexClosestResult arg5)
/*     */   {
/* 278 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); result.usedVertices.reset();
/*     */ 
/* 281 */       Vector3f ab = localStack.get$javax$vecmath$Vector3f();
/* 282 */       ab.sub(b, a);
/*     */ 
/* 284 */       Vector3f ac = localStack.get$javax$vecmath$Vector3f();
/* 285 */       ac.sub(c, a);
/*     */ 
/* 287 */       Vector3f ap = localStack.get$javax$vecmath$Vector3f();
/* 288 */       ap.sub(p, a);
/*     */ 
/* 290 */       float d1 = ab.dot(ap);
/* 291 */       float d2 = ac.dot(ap);
/*     */ 
/* 293 */       if ((d1 <= 0.0F) && (d2 <= 0.0F))
/*     */       {
/* 295 */         result.closestPointOnSimplex.set(a);
/* 296 */         result.usedVertices.usedVertexA = true;
/* 297 */         result.setBarycentricCoordinates(1.0F, 0.0F, 0.0F, 0.0F);
/* 298 */         return true;
/*     */       }
/*     */ 
/* 302 */       Vector3f bp = localStack.get$javax$vecmath$Vector3f();
/* 303 */       bp.sub(p, b);
/*     */ 
/* 305 */       float d3 = ab.dot(bp);
/* 306 */       float d4 = ac.dot(bp);
/*     */ 
/* 308 */       if ((d3 >= 0.0F) && (d4 <= d3))
/*     */       {
/* 310 */         result.closestPointOnSimplex.set(b);
/* 311 */         result.usedVertices.usedVertexB = true;
/* 312 */         result.setBarycentricCoordinates(0.0F, 1.0F, 0.0F, 0.0F);
/*     */ 
/* 314 */         return true;
/*     */       }
/*     */ 
/* 318 */       float vc = d1 * d4 - d3 * d2;
/* 319 */       if ((vc <= 0.0F) && (d1 >= 0.0F) && (d3 <= 0.0F)) {
/* 320 */         float v = d1 / (d1 - d3);
/* 321 */         result.closestPointOnSimplex.scaleAdd(v, ab, a);
/* 322 */         result.usedVertices.usedVertexA = true;
/* 323 */         result.usedVertices.usedVertexB = true;
/* 324 */         result.setBarycentricCoordinates(1.0F - v, v, 0.0F, 0.0F);
/* 325 */         return true;
/*     */       }
/*     */ 
/* 330 */       Vector3f cp = localStack.get$javax$vecmath$Vector3f();
/* 331 */       cp.sub(p, c);
/*     */ 
/* 333 */       float d5 = ab.dot(cp);
/* 334 */       float d6 = ac.dot(cp);
/*     */ 
/* 336 */       if ((d6 >= 0.0F) && (d5 <= d6))
/*     */       {
/* 338 */         result.closestPointOnSimplex.set(c);
/* 339 */         result.usedVertices.usedVertexC = true;
/* 340 */         result.setBarycentricCoordinates(0.0F, 0.0F, 1.0F, 0.0F);
/* 341 */         return true;
/*     */       }
/*     */ 
/* 345 */       float vb = d5 * d2 - d1 * d6;
/* 346 */       if ((vb <= 0.0F) && (d2 >= 0.0F) && (d6 <= 0.0F)) {
/* 347 */         float w = d2 / (d2 - d6);
/* 348 */         result.closestPointOnSimplex.scaleAdd(w, ac, a);
/* 349 */         result.usedVertices.usedVertexA = true;
/* 350 */         result.usedVertices.usedVertexC = true;
/* 351 */         result.setBarycentricCoordinates(1.0F - w, 0.0F, w, 0.0F);
/* 352 */         return true;
/*     */       }
/*     */ 
/* 357 */       float va = d3 * d6 - d5 * d4;
/* 358 */       if ((va <= 0.0F) && (d4 - d3 >= 0.0F) && (d5 - d6 >= 0.0F)) {
/* 359 */         float w = (d4 - d3) / (d4 - d3 + (d5 - d6));
/*     */ 
/* 361 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 362 */         tmp.sub(c, b);
/* 363 */         result.closestPointOnSimplex.scaleAdd(w, tmp, b);
/*     */ 
/* 365 */         result.usedVertices.usedVertexB = true;
/* 366 */         result.usedVertices.usedVertexC = true;
/* 367 */         result.setBarycentricCoordinates(0.0F, 1.0F - w, w, 0.0F);
/* 368 */         return true;
/*     */       }
/*     */ 
/* 373 */       float denom = 1.0F / (va + vb + vc);
/* 374 */       float v = vb * denom;
/* 375 */       float w = vc * denom;
/*     */ 
/* 377 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 378 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 380 */       tmp1.scale(v, ab);
/* 381 */       tmp2.scale(w, ac);
/* 382 */       VectorUtil.add(result.closestPointOnSimplex, a, tmp1, tmp2);
/* 383 */       result.usedVertices.usedVertexA = true;
/* 384 */       result.usedVertices.usedVertexB = true;
/* 385 */       result.usedVertices.usedVertexC = true;
/* 386 */       result.setBarycentricCoordinates(1.0F - v - w, v, w, 0.0F);
/*     */ 
/* 388 */       return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static int pointOutsideOfPlane(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4)
/*     */   {
/* 396 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 398 */       Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 399 */       normal.sub(b, a);
/* 400 */       tmp.sub(c, a);
/* 401 */       normal.cross(normal, tmp);
/*     */ 
/* 403 */       tmp.sub(p, a);
/* 404 */       float signp = tmp.dot(normal);
/*     */ 
/* 406 */       tmp.sub(d, a);
/* 407 */       float signd = tmp.dot(normal);
/*     */ 
/* 416 */       if (signd * signd < 9.999999E-009F)
/*     */       {
/* 419 */         return -1;
/*     */       }
/*     */ 
/* 425 */       return signp * signd < 0.0F ? 1 : 0; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally; } 
/*     */   // ERROR //
/*     */   public boolean closestPtPointTetrahedron(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5, SubSimplexClosestResult arg6) { // Byte code:
/*     */     //   0: invokestatic 118	com/bulletphysics/$Stack:get	()Lcom/bulletphysics/$Stack;
/*     */     //   3: astore 17
/*     */     //   5: aload 17
/*     */     //   7: invokevirtual 121	com/bulletphysics/$Stack:push$javax$vecmath$Vector3f	()V
/*     */     //   10: aload_0
/*     */     //   11: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   14: invokevirtual 252	com/bulletphysics/util/ObjectPool:get	()Ljava/lang/Object;
/*     */     //   17: checkcast 7	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult
/*     */     //   20: astore 7
/*     */     //   22: aload 7
/*     */     //   24: invokevirtual 126	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:reset	()V
/*     */     //   27: aload 17
/*     */     //   29: invokevirtual 143	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   32: astore 8
/*     */     //   34: aload 17
/*     */     //   36: invokevirtual 143	com/bulletphysics/$Stack:get$javax$vecmath$Vector3f	()Ljavax/vecmath/Vector3f;
/*     */     //   39: astore 9
/*     */     //   41: aload 6
/*     */     //   43: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   46: aload_1
/*     */     //   47: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   50: aload 6
/*     */     //   52: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   55: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/*     */     //   58: aload 6
/*     */     //   60: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   63: iconst_1
/*     */     //   64: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   67: aload 6
/*     */     //   69: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   72: iconst_1
/*     */     //   73: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   76: aload 6
/*     */     //   78: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   81: iconst_1
/*     */     //   82: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   85: aload 6
/*     */     //   87: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   90: iconst_1
/*     */     //   91: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/*     */     //   94: aload_1
/*     */     //   95: aload_2
/*     */     //   96: aload_3
/*     */     //   97: aload 4
/*     */     //   99: aload 5
/*     */     //   101: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/*     */     //   104: istore 10
/*     */     //   106: aload_1
/*     */     //   107: aload_2
/*     */     //   108: aload 4
/*     */     //   110: aload 5
/*     */     //   112: aload_3
/*     */     //   113: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/*     */     //   116: istore 11
/*     */     //   118: aload_1
/*     */     //   119: aload_2
/*     */     //   120: aload 5
/*     */     //   122: aload_3
/*     */     //   123: aload 4
/*     */     //   125: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/*     */     //   128: istore 12
/*     */     //   130: aload_1
/*     */     //   131: aload_3
/*     */     //   132: aload 5
/*     */     //   134: aload 4
/*     */     //   136: aload_2
/*     */     //   137: invokestatic 254	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:pointOutsideOfPlane	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;)I
/*     */     //   140: istore 13
/*     */     //   142: iload 10
/*     */     //   144: iflt +18 -> 162
/*     */     //   147: iload 11
/*     */     //   149: iflt +13 -> 162
/*     */     //   152: iload 12
/*     */     //   154: iflt +8 -> 162
/*     */     //   157: iload 13
/*     */     //   159: ifge +29 -> 188
/*     */     //   162: aload 6
/*     */     //   164: iconst_1
/*     */     //   165: putfield 190	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:degenerate	Z
/*     */     //   168: iconst_0
/*     */     //   169: istore 14
/*     */     //   171: aload_0
/*     */     //   172: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   175: aload 7
/*     */     //   177: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   180: iload 14
/*     */     //   182: aload 17
/*     */     //   184: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   187: ireturn
/*     */     //   188: iload 10
/*     */     //   190: ifne +38 -> 228
/*     */     //   193: iload 11
/*     */     //   195: ifne +33 -> 228
/*     */     //   198: iload 12
/*     */     //   200: ifne +28 -> 228
/*     */     //   203: iload 13
/*     */     //   205: ifne +23 -> 228
/*     */     //   208: iconst_0
/*     */     //   209: istore 14
/*     */     //   211: aload_0
/*     */     //   212: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   215: aload 7
/*     */     //   217: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   220: iload 14
/*     */     //   222: aload 17
/*     */     //   224: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   227: ireturn
/*     */     //   228: ldc_w 259
/*     */     //   231: fstore 14
/*     */     //   233: iload 10
/*     */     //   235: ifeq +147 -> 382
/*     */     //   238: aload_0
/*     */     //   239: aload_1
/*     */     //   240: aload_2
/*     */     //   241: aload_3
/*     */     //   242: aload 4
/*     */     //   244: aload 7
/*     */     //   246: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/*     */     //   249: pop
/*     */     //   250: aload 9
/*     */     //   252: aload 7
/*     */     //   254: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   257: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   260: aload 8
/*     */     //   262: aload 9
/*     */     //   264: aload_1
/*     */     //   265: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   268: aload 8
/*     */     //   270: aload 8
/*     */     //   272: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */     //   275: fstore 15
/*     */     //   277: fload 15
/*     */     //   279: fload 14
/*     */     //   281: fcmpg
/*     */     //   282: ifge +100 -> 382
/*     */     //   285: fload 15
/*     */     //   287: fstore 14
/*     */     //   289: aload 6
/*     */     //   291: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   294: aload 9
/*     */     //   296: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   299: aload 6
/*     */     //   301: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   304: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/*     */     //   307: aload 6
/*     */     //   309: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   312: aload 7
/*     */     //   314: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   317: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   320: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   323: aload 6
/*     */     //   325: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   328: aload 7
/*     */     //   330: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   333: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   336: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   339: aload 6
/*     */     //   341: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   344: aload 7
/*     */     //   346: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   349: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   352: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   355: aload 6
/*     */     //   357: aload 7
/*     */     //   359: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   362: iconst_0
/*     */     //   363: faload
/*     */     //   364: aload 7
/*     */     //   366: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   369: iconst_1
/*     */     //   370: faload
/*     */     //   371: aload 7
/*     */     //   373: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   376: iconst_2
/*     */     //   377: faload
/*     */     //   378: fconst_0
/*     */     //   379: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/*     */     //   382: iload 11
/*     */     //   384: ifeq +148 -> 532
/*     */     //   387: aload_0
/*     */     //   388: aload_1
/*     */     //   389: aload_2
/*     */     //   390: aload 4
/*     */     //   392: aload 5
/*     */     //   394: aload 7
/*     */     //   396: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/*     */     //   399: pop
/*     */     //   400: aload 9
/*     */     //   402: aload 7
/*     */     //   404: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   407: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   410: aload 8
/*     */     //   412: aload 9
/*     */     //   414: aload_1
/*     */     //   415: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   418: aload 8
/*     */     //   420: aload 8
/*     */     //   422: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */     //   425: fstore 15
/*     */     //   427: fload 15
/*     */     //   429: fload 14
/*     */     //   431: fcmpg
/*     */     //   432: ifge +100 -> 532
/*     */     //   435: fload 15
/*     */     //   437: fstore 14
/*     */     //   439: aload 6
/*     */     //   441: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   444: aload 9
/*     */     //   446: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   449: aload 6
/*     */     //   451: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   454: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/*     */     //   457: aload 6
/*     */     //   459: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   462: aload 7
/*     */     //   464: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   467: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   470: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   473: aload 6
/*     */     //   475: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   478: aload 7
/*     */     //   480: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   483: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   486: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   489: aload 6
/*     */     //   491: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   494: aload 7
/*     */     //   496: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   499: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   502: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/*     */     //   505: aload 6
/*     */     //   507: aload 7
/*     */     //   509: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   512: iconst_0
/*     */     //   513: faload
/*     */     //   514: fconst_0
/*     */     //   515: aload 7
/*     */     //   517: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   520: iconst_1
/*     */     //   521: faload
/*     */     //   522: aload 7
/*     */     //   524: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   527: iconst_2
/*     */     //   528: faload
/*     */     //   529: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/*     */     //   532: iload 12
/*     */     //   534: ifeq +147 -> 681
/*     */     //   537: aload_0
/*     */     //   538: aload_1
/*     */     //   539: aload_2
/*     */     //   540: aload 5
/*     */     //   542: aload_3
/*     */     //   543: aload 7
/*     */     //   545: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/*     */     //   548: pop
/*     */     //   549: aload 9
/*     */     //   551: aload 7
/*     */     //   553: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   556: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   559: aload 8
/*     */     //   561: aload 9
/*     */     //   563: aload_1
/*     */     //   564: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   567: aload 8
/*     */     //   569: aload 8
/*     */     //   571: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */     //   574: fstore 15
/*     */     //   576: fload 15
/*     */     //   578: fload 14
/*     */     //   580: fcmpg
/*     */     //   581: ifge +100 -> 681
/*     */     //   584: fload 15
/*     */     //   586: fstore 14
/*     */     //   588: aload 6
/*     */     //   590: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   593: aload 9
/*     */     //   595: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   598: aload 6
/*     */     //   600: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   603: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/*     */     //   606: aload 6
/*     */     //   608: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   611: aload 7
/*     */     //   613: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   616: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   619: putfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   622: aload 6
/*     */     //   624: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   627: aload 7
/*     */     //   629: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   632: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   635: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   638: aload 6
/*     */     //   640: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   643: aload 7
/*     */     //   645: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   648: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   651: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/*     */     //   654: aload 6
/*     */     //   656: aload 7
/*     */     //   658: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   661: iconst_0
/*     */     //   662: faload
/*     */     //   663: aload 7
/*     */     //   665: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   668: iconst_2
/*     */     //   669: faload
/*     */     //   670: fconst_0
/*     */     //   671: aload 7
/*     */     //   673: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   676: iconst_1
/*     */     //   677: faload
/*     */     //   678: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/*     */     //   681: iload 13
/*     */     //   683: ifeq +148 -> 831
/*     */     //   686: aload_0
/*     */     //   687: aload_1
/*     */     //   688: aload_3
/*     */     //   689: aload 5
/*     */     //   691: aload 4
/*     */     //   693: aload 7
/*     */     //   695: invokevirtual 171	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:closestPtPointTriangle	(Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Ljavax/vecmath/Vector3f;Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult;)Z
/*     */     //   698: pop
/*     */     //   699: aload 9
/*     */     //   701: aload 7
/*     */     //   703: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   706: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   709: aload 8
/*     */     //   711: aload 9
/*     */     //   713: aload_1
/*     */     //   714: invokevirtual 132	javax/vecmath/Vector3f:sub	(Ljavax/vecmath/Tuple3f;Ljavax/vecmath/Tuple3f;)V
/*     */     //   717: aload 8
/*     */     //   719: aload 8
/*     */     //   721: invokevirtual 150	javax/vecmath/Vector3f:dot	(Ljavax/vecmath/Vector3f;)F
/*     */     //   724: fstore 15
/*     */     //   726: fload 15
/*     */     //   728: fload 14
/*     */     //   730: fcmpg
/*     */     //   731: ifge +100 -> 831
/*     */     //   734: fload 15
/*     */     //   736: fstore 14
/*     */     //   738: aload 6
/*     */     //   740: getfield 218	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:closestPointOnSimplex	Ljavax/vecmath/Vector3f;
/*     */     //   743: aload 9
/*     */     //   745: invokevirtual 89	javax/vecmath/Vector3f:set	(Ljavax/vecmath/Tuple3f;)V
/*     */     //   748: aload 6
/*     */     //   750: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   753: invokevirtual 215	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:reset	()V
/*     */     //   756: aload 6
/*     */     //   758: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   761: aload 7
/*     */     //   763: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   766: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   769: putfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   772: aload 6
/*     */     //   774: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   777: aload 7
/*     */     //   779: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   782: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   785: putfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   788: aload 6
/*     */     //   790: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   793: aload 7
/*     */     //   795: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   798: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   801: putfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/*     */     //   804: aload 6
/*     */     //   806: fconst_0
/*     */     //   807: aload 7
/*     */     //   809: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   812: iconst_0
/*     */     //   813: faload
/*     */     //   814: aload 7
/*     */     //   816: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   819: iconst_2
/*     */     //   820: faload
/*     */     //   821: aload 7
/*     */     //   823: getfield 175	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:barycentricCoords	[F
/*     */     //   826: iconst_1
/*     */     //   827: faload
/*     */     //   828: invokevirtual 136	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:setBarycentricCoordinates	(FFFF)V
/*     */     //   831: aload 6
/*     */     //   833: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   836: getfield 109	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexA	Z
/*     */     //   839: ifeq +56 -> 895
/*     */     //   842: aload 6
/*     */     //   844: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   847: getfield 106	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexB	Z
/*     */     //   850: ifeq +45 -> 895
/*     */     //   853: aload 6
/*     */     //   855: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   858: getfield 103	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexC	Z
/*     */     //   861: ifeq +34 -> 895
/*     */     //   864: aload 6
/*     */     //   866: getfield 159	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$SubSimplexClosestResult:usedVertices	Lcom/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield;
/*     */     //   869: getfield 98	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver$UsageBitfield:usedVertexD	Z
/*     */     //   872: ifeq +23 -> 895
/*     */     //   875: iconst_1
/*     */     //   876: istore 15
/*     */     //   878: aload_0
/*     */     //   879: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   882: aload 7
/*     */     //   884: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   887: iload 15
/*     */     //   889: aload 17
/*     */     //   891: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   894: ireturn
/*     */     //   895: iconst_1
/*     */     //   896: istore 15
/*     */     //   898: aload_0
/*     */     //   899: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   902: aload 7
/*     */     //   904: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   907: iload 15
/*     */     //   909: aload 17
/*     */     //   911: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   914: ireturn
/*     */     //   915: astore 16
/*     */     //   917: aload_0
/*     */     //   918: getfield 53	com/bulletphysics/collision/narrowphase/VoronoiSimplexSolver:subsimplexResultsPool	Lcom/bulletphysics/util/ObjectPool;
/*     */     //   921: aload 7
/*     */     //   923: invokevirtual 258	com/bulletphysics/util/ObjectPool:release	(Ljava/lang/Object;)V
/*     */     //   926: aload 16
/*     */     //   928: athrow
/*     */     //   929: aload 17
/*     */     //   931: invokevirtual 193	com/bulletphysics/$Stack:pop$javax$vecmath$Vector3f	()V
/*     */     //   934: athrow
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   27	171	915	finally
/*     */     //   188	211	915	finally
/*     */     //   228	878	915	finally
/*     */     //   895	898	915	finally
/*     */     //   915	917	915	finally
/*     */     //   5	929	929	finally } 
/* 597 */   public void reset() { this.cachedValidClosest = false;
/* 598 */     this.numVertices = 0;
/* 599 */     this.needsUpdate = true;
/* 600 */     this.lastW.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 601 */     this.cachedBC.reset(); }
/*     */ 
/*     */   public void addVertex(Vector3f w, Vector3f p, Vector3f q)
/*     */   {
/* 605 */     this.lastW.set(w);
/* 606 */     this.needsUpdate = true;
/*     */ 
/* 608 */     this.simplexVectorW[this.numVertices].set(w);
/* 609 */     this.simplexPointsP[this.numVertices].set(p);
/* 610 */     this.simplexPointsQ[this.numVertices].set(q);
/*     */ 
/* 612 */     this.numVertices += 1;
/*     */   }
/*     */ 
/*     */   public boolean closest(Vector3f v)
/*     */   {
/* 619 */     boolean succes = updateClosestVectorAndPoints();
/* 620 */     v.set(this.cachedV);
/* 621 */     return succes;
/*     */   }
/*     */ 
/*     */   public float maxVertex() {
/* 625 */     int numverts = numVertices();
/* 626 */     float maxV = 0.0F;
/* 627 */     for (int i = 0; i < numverts; i++) {
/* 628 */       float curLen2 = this.simplexVectorW[i].lengthSquared();
/* 629 */       if (maxV < curLen2) {
/* 630 */         maxV = curLen2;
/*     */       }
/*     */     }
/* 633 */     return maxV;
/*     */   }
/*     */ 
/*     */   public boolean fullSimplex() {
/* 637 */     return this.numVertices == 4;
/*     */   }
/*     */ 
/*     */   public int getSimplex(Vector3f[] pBuf, Vector3f[] qBuf, Vector3f[] yBuf) {
/* 641 */     for (int i = 0; i < numVertices(); i++) {
/* 642 */       yBuf[i].set(this.simplexVectorW[i]);
/* 643 */       pBuf[i].set(this.simplexPointsP[i]);
/* 644 */       qBuf[i].set(this.simplexPointsQ[i]);
/*     */     }
/* 646 */     return numVertices();
/*     */   }
/*     */ 
/*     */   public boolean inSimplex(Vector3f w) {
/* 650 */     boolean found = false;
/* 651 */     int numverts = numVertices();
/*     */ 
/* 655 */     for (int i = 0; i < numverts; i++) {
/* 656 */       if (this.simplexVectorW[i].equals(w)) {
/* 657 */         found = true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 662 */     if (w.equals(this.lastW)) {
/* 663 */       return true;
/*     */     }
/*     */ 
/* 666 */     return found;
/*     */   }
/*     */ 
/*     */   public void backup_closest(Vector3f v) {
/* 670 */     v.set(this.cachedV);
/*     */   }
/*     */ 
/*     */   public boolean emptySimplex() {
/* 674 */     return numVertices() == 0;
/*     */   }
/*     */ 
/*     */   public void compute_points(Vector3f p1, Vector3f p2) {
/* 678 */     updateClosestVectorAndPoints();
/* 679 */     p1.set(this.cachedP1);
/* 680 */     p2.set(this.cachedP2);
/*     */   }
/*     */ 
/*     */   public int numVertices() {
/* 684 */     return this.numVertices;
/*     */   }
/*     */ 
/*     */   public static class SubSimplexClosestResult
/*     */   {
/* 704 */     public final Vector3f closestPointOnSimplex = new Vector3f();
/*     */ 
/* 708 */     public final VoronoiSimplexSolver.UsageBitfield usedVertices = new VoronoiSimplexSolver.UsageBitfield();
/* 709 */     public final float[] barycentricCoords = new float[4];
/*     */     public boolean degenerate;
/*     */ 
/*     */     public void reset()
/*     */     {
/* 713 */       this.degenerate = false;
/* 714 */       setBarycentricCoordinates(0.0F, 0.0F, 0.0F, 0.0F);
/* 715 */       this.usedVertices.reset();
/*     */     }
/*     */ 
/*     */     public boolean isValid() {
/* 719 */       boolean valid = (this.barycentricCoords[0] >= 0.0F) && (this.barycentricCoords[1] >= 0.0F) && (this.barycentricCoords[2] >= 0.0F) && (this.barycentricCoords[3] >= 0.0F);
/*     */ 
/* 723 */       return valid;
/*     */     }
/*     */ 
/*     */     public void setBarycentricCoordinates(float a, float b, float c, float d) {
/* 727 */       this.barycentricCoords[0] = a;
/* 728 */       this.barycentricCoords[1] = b;
/* 729 */       this.barycentricCoords[2] = c;
/* 730 */       this.barycentricCoords[3] = d;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class UsageBitfield
/*     */   {
/*     */     public boolean usedVertexA;
/*     */     public boolean usedVertexB;
/*     */     public boolean usedVertexC;
/*     */     public boolean usedVertexD;
/*     */ 
/*     */     public void reset()
/*     */     {
/* 696 */       this.usedVertexA = false;
/* 697 */       this.usedVertexB = false;
/* 698 */       this.usedVertexC = false;
/* 699 */       this.usedVertexD = false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.VoronoiSimplexSolver
 * JD-Core Version:    0.6.2
 */