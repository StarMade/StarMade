/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionAlgorithmCreateFunc;
/*     */ import com.bulletphysics.collision.dispatch.CollisionDispatcher;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.CompoundShape;
/*     */ import com.bulletphysics.collision.shapes.ConcaveShape;
/*     */ import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class GImpactCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/*     */   protected CollisionAlgorithm convex_algorithm;
/*     */   protected PersistentManifold manifoldPtr;
/*     */   protected ManifoldResult resultOut;
/*     */   protected DispatcherInfo dispatchInfo;
/*     */   protected int triface0;
/*     */   protected int part0;
/*     */   protected int triface1;
/*     */   protected int part1;
/*     */   private PairSet tmpPairset;
/*     */ 
/*     */   public GImpactCollisionAlgorithm()
/*     */   {
/*  75 */     this.tmpPairset = new PairSet();
/*     */   }
/*     */   public void init(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1) {
/*  78 */     super.init(ci);
/*  79 */     this.manifoldPtr = null;
/*  80 */     this.convex_algorithm = null;
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  85 */     clearCache();
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*     */   {
/*  90 */     clearCache();
/*     */ 
/*  92 */     this.resultOut = resultOut;
/*  93 */     this.dispatchInfo = dispatchInfo;
/*     */ 
/*  97 */     if (body0.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
/*     */     {
/*  99 */       GImpactShapeInterface gimpactshape0 = (GImpactShapeInterface)body0.getCollisionShape();
/*     */ 
/* 101 */       if (body1.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
/*     */       {
/* 103 */         GImpactShapeInterface gimpactshape1 = (GImpactShapeInterface)body1.getCollisionShape();
/*     */ 
/* 105 */         gimpact_vs_gimpact(body0, body1, gimpactshape0, gimpactshape1);
/*     */       }
/*     */       else
/*     */       {
/* 109 */         gimpact_vs_shape(body0, body1, gimpactshape0, body1.getCollisionShape(), false);
/*     */       }
/*     */ 
/*     */     }
/* 113 */     else if (body1.getCollisionShape().getShapeType() == BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE)
/*     */     {
/* 115 */       GImpactShapeInterface gimpactshape1 = (GImpactShapeInterface)body1.getCollisionShape();
/*     */ 
/* 117 */       gimpact_vs_shape(body1, body0, gimpactshape1, body0.getCollisionShape(), true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void gimpact_vs_gimpact(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, GImpactShapeInterface arg4) {
/* 122 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); if (shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE) {
/* 123 */         GImpactMeshShape meshshape0 = (GImpactMeshShape)shape0;
/* 124 */         this.part0 = meshshape0.getMeshPartCount();
/*     */ 
/* 126 */         while (this.part0-- != 0) {
/* 127 */           gimpact_vs_gimpact(body0, body1, meshshape0.getMeshPart(this.part0), shape1);
/*     */         }
/*     */ 
/* 130 */         return;
/*     */       }
/*     */ 
/* 133 */       if (shape1.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE) {
/* 134 */         GImpactMeshShape meshshape1 = (GImpactMeshShape)shape1;
/* 135 */         this.part1 = meshshape1.getMeshPartCount();
/*     */ 
/* 137 */         while (this.part1-- != 0) {
/* 138 */           gimpact_vs_gimpact(body0, body1, shape0, meshshape1.getMeshPart(this.part1));
/*     */         }
/*     */ 
/* 141 */         return;
/*     */       }
/*     */ 
/* 144 */       Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 145 */       Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 147 */       PairSet pairset = this.tmpPairset;
/* 148 */       pairset.clear();
/*     */ 
/* 150 */       gimpact_vs_gimpact_find_pairs(orgtrans0, orgtrans1, shape0, shape1, pairset);
/*     */ 
/* 152 */       if (pairset.size() == 0) {
/* 153 */         return;
/*     */       }
/* 155 */       if ((shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART) && (shape1.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART))
/*     */       {
/* 158 */         GImpactMeshShapePart shapepart0 = (GImpactMeshShapePart)shape0;
/* 159 */         GImpactMeshShapePart shapepart1 = (GImpactMeshShapePart)shape1;
/*     */ 
/* 165 */         collide_sat_triangles(body0, body1, shapepart0, shapepart1, pairset, pairset.size());
/*     */ 
/* 168 */         return;
/*     */       }
/*     */ 
/* 173 */       shape0.lockChildShapes();
/* 174 */       shape1.lockChildShapes();
/*     */ 
/* 176 */       GIM_ShapeRetriever retriever0 = new GIM_ShapeRetriever(shape0);
/* 177 */       GIM_ShapeRetriever retriever1 = new GIM_ShapeRetriever(shape1);
/*     */ 
/* 179 */       boolean child_has_transform0 = shape0.childrenHasTransform();
/* 180 */       boolean child_has_transform1 = shape1.childrenHasTransform();
/*     */ 
/* 182 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 184 */       int i = pairset.size();
/* 185 */       while (i-- != 0) {
/* 186 */         Pair pair = pairset.get(i);
/* 187 */         this.triface0 = pair.index1;
/* 188 */         this.triface1 = pair.index2;
/* 189 */         CollisionShape colshape0 = retriever0.getChildShape(this.triface0);
/* 190 */         CollisionShape colshape1 = retriever1.getChildShape(this.triface1);
/*     */ 
/* 192 */         if (child_has_transform0) {
/* 193 */           tmpTrans.mul(orgtrans0, shape0.getChildTransform(this.triface0));
/* 194 */           body0.setWorldTransform(tmpTrans);
/*     */         }
/*     */ 
/* 197 */         if (child_has_transform1) {
/* 198 */           tmpTrans.mul(orgtrans1, shape1.getChildTransform(this.triface1));
/* 199 */           body1.setWorldTransform(tmpTrans);
/*     */         }
/*     */ 
/* 203 */         convex_vs_convex_collision(body0, body1, colshape0, colshape1);
/*     */ 
/* 205 */         if (child_has_transform0) {
/* 206 */           body0.setWorldTransform(orgtrans0);
/*     */         }
/*     */ 
/* 209 */         if (child_has_transform1) {
/* 210 */           body1.setWorldTransform(orgtrans1); } 
/*     */ 
/* 215 */       }
/*     */ shape0.unlockChildShapes();
/* 216 */       shape1.unlockChildShapes();
/*     */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   public void gimpact_vs_shape(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, CollisionShape arg4, boolean arg5) {
/* 220 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); if (shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE) {
/* 221 */         GImpactMeshShape meshshape0 = (GImpactMeshShape)shape0;
/* 222 */         this.part0 = meshshape0.getMeshPartCount();
/*     */ 
/* 224 */         while (this.part0-- != 0) {
/* 225 */           gimpact_vs_shape(body0, body1, meshshape0.getMeshPart(this.part0), shape1, swapped);
/*     */         }
/*     */ 
/* 231 */         return;
/*     */       }
/*     */ 
/* 235 */       if ((shape0.getGImpactShapeType() == ShapeType.TRIMESH_SHAPE_PART) && (shape1.getShapeType() == BroadphaseNativeType.STATIC_PLANE_PROXYTYPE))
/*     */       {
/* 237 */         GImpactMeshShapePart shapepart = (GImpactMeshShapePart)shape0;
/* 238 */         StaticPlaneShape planeshape = (StaticPlaneShape)shape1;
/* 239 */         gimpacttrimeshpart_vs_plane_collision(body0, body1, shapepart, planeshape, swapped);
/* 240 */         return;
/*     */       }
/*     */ 
/* 244 */       if (shape1.isCompound()) {
/* 245 */         CompoundShape compoundshape = (CompoundShape)shape1;
/* 246 */         gimpact_vs_compoundshape(body0, body1, shape0, compoundshape, swapped);
/* 247 */         return;
/*     */       }
/* 249 */       if (shape1.isConcave()) {
/* 250 */         ConcaveShape concaveshape = (ConcaveShape)shape1;
/* 251 */         gimpact_vs_concave(body0, body1, shape0, concaveshape, swapped);
/* 252 */         return;
/*     */       }
/*     */ 
/* 255 */       Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 256 */       Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 258 */       IntArrayList collided_results = new IntArrayList();
/*     */ 
/* 260 */       gimpact_vs_shape_find_pairs(orgtrans0, orgtrans1, shape0, shape1, collided_results);
/*     */ 
/* 262 */       if (collided_results.size() == 0) {
/* 263 */         return;
/*     */       }
/* 265 */       shape0.lockChildShapes();
/*     */ 
/* 267 */       GIM_ShapeRetriever retriever0 = new GIM_ShapeRetriever(shape0);
/*     */ 
/* 269 */       boolean child_has_transform0 = shape0.childrenHasTransform();
/*     */ 
/* 271 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 273 */       int i = collided_results.size();
/*     */ 
/* 275 */       while (i-- != 0) {
/* 276 */         int child_index = collided_results.get(i);
/* 277 */         if (swapped) {
/* 278 */           this.triface1 = child_index;
/*     */         }
/*     */         else {
/* 281 */           this.triface0 = child_index;
/*     */         }
/* 283 */         CollisionShape colshape0 = retriever0.getChildShape(child_index);
/*     */ 
/* 285 */         if (child_has_transform0) {
/* 286 */           tmpTrans.mul(orgtrans0, shape0.getChildTransform(child_index));
/* 287 */           body0.setWorldTransform(tmpTrans);
/*     */         }
/*     */ 
/* 291 */         if (swapped) {
/* 292 */           shape_vs_shape_collision(body1, body0, shape1, colshape0);
/*     */         }
/*     */         else {
/* 295 */           shape_vs_shape_collision(body0, body1, colshape0, shape1);
/*     */         }
/*     */ 
/* 299 */         if (child_has_transform0) {
/* 300 */           body0.setWorldTransform(orgtrans0);
/*     */         }
/*     */ 
/* 305 */       }
/*     */ shape0.unlockChildShapes();
/*     */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   public void gimpact_vs_compoundshape(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, CompoundShape arg4, boolean arg5) {
/* 309 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 310 */       Transform childtrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 311 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 313 */       int i = shape1.getNumChildShapes();
/* 314 */       while (i-- != 0) {
/* 315 */         CollisionShape colshape1 = shape1.getChildShape(i);
/* 316 */         childtrans1.mul(orgtrans1, shape1.getChildTransform(i, tmpTrans));
/*     */ 
/* 318 */         body1.setWorldTransform(childtrans1);
/*     */ 
/* 321 */         gimpact_vs_shape(body0, body1, shape0, colshape1, swapped);
/*     */ 
/* 325 */         body1.setWorldTransform(orgtrans1);
/*     */       }return; } finally {
/* 327 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void gimpact_vs_concave(CollisionObject arg1, CollisionObject arg2, GImpactShapeInterface arg3, ConcaveShape arg4, boolean arg5) {
/* 331 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); GImpactTriangleCallback tricallback = new GImpactTriangleCallback();
/* 332 */       tricallback.algorithm = this;
/* 333 */       tricallback.body0 = body0;
/* 334 */       tricallback.body1 = body1;
/* 335 */       tricallback.gimpactshape0 = shape0;
/* 336 */       tricallback.swapped = swapped;
/* 337 */       tricallback.margin = shape1.getMargin();
/*     */ 
/* 340 */       Transform gimpactInConcaveSpace = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 342 */       body1.getWorldTransform(gimpactInConcaveSpace);
/* 343 */       gimpactInConcaveSpace.inverse();
/* 344 */       gimpactInConcaveSpace.mul(body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()));
/*     */ 
/* 346 */       Vector3f minAABB = localStack.get$javax$vecmath$Vector3f(); Vector3f maxAABB = localStack.get$javax$vecmath$Vector3f();
/* 347 */       shape0.getAabb(gimpactInConcaveSpace, minAABB, maxAABB);
/*     */ 
/* 349 */       shape1.processAllTriangles(tricallback, minAABB, maxAABB);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 350 */       .Stack tmp144_142 = localStack; tmp144_142.pop$com$bulletphysics$linearmath$Transform(); tmp144_142.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   protected PersistentManifold newContactManifold(CollisionObject body0, CollisionObject body1)
/*     */   {
/* 356 */     this.manifoldPtr = this.dispatcher.getNewManifold(body0, body1);
/* 357 */     return this.manifoldPtr;
/*     */   }
/*     */ 
/*     */   protected void destroyConvexAlgorithm() {
/* 361 */     if (this.convex_algorithm != null)
/*     */     {
/* 363 */       this.dispatcher.freeCollisionAlgorithm(this.convex_algorithm);
/* 364 */       this.convex_algorithm = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void destroyContactManifolds() {
/* 369 */     if (this.manifoldPtr == null) return;
/* 370 */     this.dispatcher.releaseManifold(this.manifoldPtr);
/* 371 */     this.manifoldPtr = null;
/*     */   }
/*     */ 
/*     */   protected void clearCache() {
/* 375 */     destroyContactManifolds();
/* 376 */     destroyConvexAlgorithm();
/*     */ 
/* 378 */     this.triface0 = -1;
/* 379 */     this.part0 = -1;
/* 380 */     this.triface1 = -1;
/* 381 */     this.part1 = -1;
/*     */   }
/*     */ 
/*     */   protected PersistentManifold getLastManifold() {
/* 385 */     return this.manifoldPtr;
/*     */   }
/*     */ 
/*     */   protected void checkManifold(CollisionObject body0, CollisionObject body1)
/*     */   {
/* 392 */     if (getLastManifold() == null) {
/* 393 */       newContactManifold(body0, body1);
/*     */     }
/*     */ 
/* 396 */     this.resultOut.setPersistentManifold(getLastManifold());
/*     */   }
/*     */ 
/*     */   protected CollisionAlgorithm newAlgorithm(CollisionObject body0, CollisionObject body1)
/*     */   {
/* 403 */     checkManifold(body0, body1);
/*     */ 
/* 405 */     CollisionAlgorithm convex_algorithm = this.dispatcher.findAlgorithm(body0, body1, getLastManifold());
/* 406 */     return convex_algorithm;
/*     */   }
/*     */ 
/*     */   protected void checkConvexAlgorithm(CollisionObject body0, CollisionObject body1)
/*     */   {
/* 413 */     if (this.convex_algorithm != null) return;
/* 414 */     this.convex_algorithm = newAlgorithm(body0, body1);
/*     */   }
/*     */ 
/*     */   protected void addContactPoint(CollisionObject body0, CollisionObject body1, Vector3f point, Vector3f normal, float distance) {
/* 418 */     this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
/* 419 */     checkManifold(body0, body1);
/* 420 */     this.resultOut.addContactPoint(normal, point, distance);
/*     */   }
/*     */ 
/*     */   void collide_sat_triangles(CollisionObject arg1, CollisionObject arg2, GImpactMeshShapePart arg3, GImpactMeshShapePart arg4, PairSet arg5, int arg6)
/*     */   {
/* 429 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/*     */       .Stack tmp15_11 = tmp11_7; tmp15_11.push$javax$vecmath$Vector3f(); tmp15_11.push$com$bulletphysics$extras$gimpact$TriangleContact(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 431 */       Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 432 */       Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 434 */       PrimitiveTriangle ptri0 = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 435 */       PrimitiveTriangle ptri1 = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 436 */       TriangleContact contact_data = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
/*     */ 
/* 438 */       shape0.lockChildShapes();
/* 439 */       shape1.lockChildShapes();
/*     */ 
/* 441 */       int pair_pointer = 0;
/*     */ 
/* 443 */       while (pair_count-- != 0)
/*     */       {
/* 447 */         Pair pair = pairs.get(pair_pointer++);
/* 448 */         this.triface0 = pair.index1;
/* 449 */         this.triface1 = pair.index2;
/*     */ 
/* 451 */         shape0.getPrimitiveTriangle(this.triface0, ptri0);
/* 452 */         shape1.getPrimitiveTriangle(this.triface1, ptri1);
/*     */ 
/* 458 */         ptri0.applyTransform(orgtrans0);
/* 459 */         ptri1.applyTransform(orgtrans1);
/*     */ 
/* 462 */         ptri0.buildTriPlane();
/* 463 */         ptri1.buildTriPlane();
/*     */ 
/* 466 */         if ((ptri0.overlap_test_conservative(ptri1)) && 
/* 467 */           (ptri0.find_triangle_collision_clip_method(ptri1, contact_data)))
/*     */         {
/* 469 */           int j = contact_data.point_count;
/* 470 */           while (j-- != 0) {
/* 471 */             tmp.x = contact_data.separating_normal.x;
/* 472 */             tmp.y = contact_data.separating_normal.y;
/* 473 */             tmp.z = contact_data.separating_normal.z;
/*     */ 
/* 475 */             addContactPoint(body0, body1, contact_data.points[j], tmp, -contact_data.penetration_depth);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 488 */       shape0.unlockChildShapes();
/* 489 */       shape1.unlockChildShapes();
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 490 */       .Stack tmp300_298 = localStack; tmp300_298.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp304_300 = tmp300_298; tmp304_300.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/*     */       .Stack tmp308_304 = tmp304_300; tmp308_304.pop$javax$vecmath$Vector3f(); tmp308_304.pop$com$bulletphysics$extras$gimpact$TriangleContact(); } throw finally;
/*     */   }
/*     */   protected void shape_vs_shape_collision(CollisionObject body0, CollisionObject body1, CollisionShape shape0, CollisionShape shape1) {
/* 493 */     CollisionShape tmpShape0 = body0.getCollisionShape();
/* 494 */     CollisionShape tmpShape1 = body1.getCollisionShape();
/*     */ 
/* 496 */     body0.internalSetTemporaryCollisionShape(shape0);
/* 497 */     body1.internalSetTemporaryCollisionShape(shape1);
/*     */ 
/* 500 */     CollisionAlgorithm algor = newAlgorithm(body0, body1);
/*     */ 
/* 503 */     this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
/*     */ 
/* 505 */     algor.processCollision(body0, body1, this.dispatchInfo, this.resultOut);
/*     */ 
/* 508 */     this.dispatcher.freeCollisionAlgorithm(algor);
/*     */ 
/* 511 */     body0.internalSetTemporaryCollisionShape(tmpShape0);
/* 512 */     body1.internalSetTemporaryCollisionShape(tmpShape1);
/*     */   }
/*     */ 
/*     */   protected void convex_vs_convex_collision(CollisionObject body0, CollisionObject body1, CollisionShape shape0, CollisionShape shape1) {
/* 516 */     CollisionShape tmpShape0 = body0.getCollisionShape();
/* 517 */     CollisionShape tmpShape1 = body1.getCollisionShape();
/*     */ 
/* 519 */     body0.internalSetTemporaryCollisionShape(shape0);
/* 520 */     body1.internalSetTemporaryCollisionShape(shape1);
/*     */ 
/* 522 */     this.resultOut.setShapeIdentifiers(this.part0, this.triface0, this.part1, this.triface1);
/*     */ 
/* 524 */     checkConvexAlgorithm(body0, body1);
/* 525 */     this.convex_algorithm.processCollision(body0, body1, this.dispatchInfo, this.resultOut);
/*     */ 
/* 527 */     body0.internalSetTemporaryCollisionShape(tmpShape0);
/* 528 */     body1.internalSetTemporaryCollisionShape(tmpShape1);
/*     */   }
/*     */ 
/*     */   void gimpact_vs_gimpact_find_pairs(Transform arg1, Transform arg2, GImpactShapeInterface arg3, GImpactShapeInterface arg4, PairSet arg5) {
/* 532 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); if ((shape0.hasBoxSet()) && (shape1.hasBoxSet())) {
/* 533 */         GImpactBvh.find_collision(shape0.getBoxSet(), trans0, shape1.getBoxSet(), trans1, pairset);
/*     */       }
/*     */       else {
/* 536 */         BoxCollision.AABB boxshape0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 537 */         BoxCollision.AABB boxshape1 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 538 */         int i = shape0.getNumChildShapes();
/*     */ 
/* 540 */         while (i-- != 0) {
/* 541 */           shape0.getChildAabb(i, trans0, boxshape0.min, boxshape0.max);
/*     */ 
/* 543 */           int j = shape1.getNumChildShapes();
/* 544 */           while (j-- != 0) {
/* 545 */             shape1.getChildAabb(i, trans1, boxshape1.min, boxshape1.max);
/*     */ 
/* 547 */             if (boxshape1.has_collision(boxshape0))
/* 548 */               pairset.push_pair(i, j);
/*     */           }
/*     */         }
/*     */       }
/*     */       return; } finally {
/* 553 */       localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */   protected void gimpact_vs_shape_find_pairs(Transform arg1, Transform arg2, GImpactShapeInterface arg3, CollisionShape arg4, IntArrayList arg5) {
/* 556 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB boxshape = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/* 558 */       if (shape0.hasBoxSet()) {
/* 559 */         Transform trans1to0 = localStack.get$com$bulletphysics$linearmath$Transform();
/* 560 */         trans1to0.inverse(trans0);
/* 561 */         trans1to0.mul(trans1);
/*     */ 
/* 563 */         shape1.getAabb(trans1to0, boxshape.min, boxshape.max);
/*     */ 
/* 565 */         shape0.getBoxSet().boxQuery(boxshape, collided_primitives);
/*     */       }
/*     */       else {
/* 568 */         shape1.getAabb(trans1, boxshape.min, boxshape.max);
/*     */ 
/* 570 */         BoxCollision.AABB boxshape0 = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 571 */         int i = shape0.getNumChildShapes();
/*     */ 
/* 573 */         while (i-- != 0) {
/* 574 */           shape0.getChildAabb(i, trans0, boxshape0.min, boxshape0.max);
/*     */ 
/* 576 */           if (boxshape.has_collision(boxshape0))
/* 577 */             collided_primitives.add(i);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 581 */       .Stack tmp165_163 = localStack; tmp165_163.pop$com$bulletphysics$linearmath$Transform(); tmp165_163.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */   protected void gimpacttrimeshpart_vs_plane_collision(CollisionObject arg1, CollisionObject arg2, GImpactMeshShapePart arg3, StaticPlaneShape arg4, boolean arg5) {
/* 584 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */       .Stack tmp15_11 = tmp11_7; tmp15_11.push$javax$vecmath$Vector3f(); tmp15_11.push$javax$vecmath$Vector4f(); Transform orgtrans0 = body0.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 585 */       Transform orgtrans1 = body1.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 587 */       StaticPlaneShape planeshape = shape1;
/* 588 */       Vector4f plane = localStack.get$javax$vecmath$Vector4f();
/* 589 */       PlaneShape.get_plane_equation_transformed(planeshape, orgtrans1, plane);
/*     */ 
/* 593 */       BoxCollision.AABB tribox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 594 */       shape0.getAabb(orgtrans0, tribox.min, tribox.max);
/* 595 */       tribox.increment_margin(planeshape.getMargin());
/*     */ 
/* 597 */       if (tribox.plane_classify(plane) != PlaneIntersectionType.COLLIDE_PLANE) {
/* 598 */         return;
/*     */       }
/* 600 */       shape0.lockChildShapes();
/*     */ 
/* 602 */       float margin = shape0.getMargin() + planeshape.getMargin();
/*     */ 
/* 604 */       Vector3f vertex = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 606 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 608 */       int vi = shape0.getVertexCount();
/* 609 */       while (vi-- != 0) {
/* 610 */         shape0.getVertex(vi, vertex);
/* 611 */         orgtrans0.transform(vertex);
/*     */ 
/* 613 */         float distance = VectorUtil.dot3(vertex, plane) - plane.w - margin;
/*     */ 
/* 615 */         if (distance < 0.0F)
/*     */         {
/* 617 */           if (swapped) {
/* 618 */             tmp.set(-plane.x, -plane.y, -plane.z);
/* 619 */             addContactPoint(body1, body0, vertex, tmp, distance);
/*     */           }
/*     */           else {
/* 622 */             tmp.set(plane.x, plane.y, plane.z);
/* 623 */             addContactPoint(body0, body1, vertex, tmp, distance);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 628 */       shape0.unlockChildShapes();
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 629 */       .Stack tmp314_312 = localStack; tmp314_312.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp318_314 = tmp314_312; tmp318_314.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */       .Stack tmp322_318 = tmp318_314; tmp322_318.pop$javax$vecmath$Vector3f(); tmp322_318.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setFace0(int value) {
/* 633 */     this.triface0 = value;
/*     */   }
/*     */ 
/*     */   public int getFace0() {
/* 637 */     return this.triface0;
/*     */   }
/*     */ 
/*     */   public void setFace1(int value) {
/* 641 */     this.triface1 = value;
/*     */   }
/*     */ 
/*     */   public int getFace1() {
/* 645 */     return this.triface1;
/*     */   }
/*     */ 
/*     */   public void setPart0(int value) {
/* 649 */     this.part0 = value;
/*     */   }
/*     */ 
/*     */   public int getPart0() {
/* 653 */     return this.part0;
/*     */   }
/*     */ 
/*     */   public void setPart1(int value) {
/* 657 */     this.part1 = value;
/*     */   }
/*     */ 
/*     */   public int getPart1() {
/* 661 */     return this.part1;
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*     */   {
/* 666 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*     */   {
/* 671 */     if (this.manifoldPtr != null)
/* 672 */       manifoldArray.add(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public static void registerAlgorithm(CollisionDispatcher dispatcher)
/*     */   {
/* 682 */     CreateFunc createFunc = new CreateFunc();
/*     */ 
/* 684 */     for (int i = 0; i < BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal(); i++) {
/* 685 */       dispatcher.registerCollisionCreateFunc(BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE.ordinal(), i, createFunc);
/*     */     }
/*     */ 
/* 688 */     for (int i = 0; i < BroadphaseNativeType.MAX_BROADPHASE_COLLISION_TYPES.ordinal(); i++)
/* 689 */       dispatcher.registerCollisionCreateFunc(i, BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE.ordinal(), createFunc);
/*     */   }
/*     */ 
/*     */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 694 */     private final ObjectPool<GImpactCollisionAlgorithm> pool = ObjectPool.get(GImpactCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 698 */       GImpactCollisionAlgorithm algo = (GImpactCollisionAlgorithm)this.pool.get();
/* 699 */       algo.init(ci, body0, body1);
/* 700 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 705 */       this.pool.release((GImpactCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */