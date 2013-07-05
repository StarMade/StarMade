/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ class BoxCollision
/*     */ {
/*     */   public static final float BOX_PLANE_EPSILON = 1.0E-006F;
/*     */ 
/*     */   public static boolean BT_GREATER(float x, float y)
/*     */   {
/*  47 */     return Math.abs(x) > y;
/*     */   }
/*     */ 
/*     */   public static float BT_MAX3(float a, float b, float c) {
/*  51 */     return Math.max(a, Math.max(b, c));
/*     */   }
/*     */ 
/*     */   public static float BT_MIN3(float a, float b, float c) {
/*  55 */     return Math.min(a, Math.min(b, c));
/*     */   }
/*     */ 
/*     */   public static boolean TEST_CROSS_EDGE_BOX_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend, int i_dir_0, int i_dir_1, int i_comp_0, int i_comp_1) {
/*  59 */     float dir0 = -VectorUtil.getCoord(edge, i_dir_0);
/*  60 */     float dir1 = VectorUtil.getCoord(edge, i_dir_1);
/*  61 */     float pmin = VectorUtil.getCoord(pointa, i_comp_0) * dir0 + VectorUtil.getCoord(pointa, i_comp_1) * dir1;
/*  62 */     float pmax = VectorUtil.getCoord(pointb, i_comp_0) * dir0 + VectorUtil.getCoord(pointb, i_comp_1) * dir1;
/*  63 */     if (pmin > pmax)
/*     */     {
/*  65 */       pmin += pmax;
/*  66 */       pmax = pmin - pmax;
/*  67 */       pmin -= pmax;
/*     */     }
/*  69 */     float abs_dir0 = VectorUtil.getCoord(absolute_edge, i_dir_0);
/*  70 */     float abs_dir1 = VectorUtil.getCoord(absolute_edge, i_dir_1);
/*  71 */     float rad = VectorUtil.getCoord(_extend, i_comp_0) * abs_dir0 + VectorUtil.getCoord(_extend, i_comp_1) * abs_dir1;
/*  72 */     if ((pmin > rad) || (-rad > pmax)) {
/*  73 */       return false;
/*     */     }
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean TEST_CROSS_EDGE_BOX_X_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend) {
/*  79 */     return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 2, 1, 1, 2);
/*     */   }
/*     */ 
/*     */   public static boolean TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend) {
/*  83 */     return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 0, 2, 2, 0);
/*     */   }
/*     */ 
/*     */   public static boolean TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(Vector3f edge, Vector3f absolute_edge, Vector3f pointa, Vector3f pointb, Vector3f _extend) {
/*  87 */     return TEST_CROSS_EDGE_BOX_MCR(edge, absolute_edge, pointa, pointb, _extend, 1, 0, 0, 1);
/*     */   }
/*     */ 
/*     */   public static float bt_mat3_dot_col(Matrix3f mat, Vector3f vec3, int colindex)
/*     */   {
/*  94 */     return vec3.x * mat.getElement(0, colindex) + vec3.y * mat.getElement(1, colindex) + vec3.z * mat.getElement(2, colindex);
/*     */   }
/*     */ 
/*     */   public static boolean compareTransformsEqual(Transform t1, Transform t2)
/*     */   {
/* 101 */     return t1.equals(t2);
/*     */   }
/*     */ 
/*     */   public static class AABB
/*     */   {
/* 179 */     public final Vector3f min = new Vector3f();
/* 180 */     public final Vector3f max = new Vector3f();
/*     */ 
/*     */     public AABB() {
/*     */     }
/*     */ 
/*     */     public AABB(Vector3f V1, Vector3f V2, Vector3f V3) {
/* 186 */       calc_from_triangle(V1, V2, V3);
/*     */     }
/*     */ 
/*     */     public AABB(Vector3f V1, Vector3f V2, Vector3f V3, float margin) {
/* 190 */       calc_from_triangle_margin(V1, V2, V3, margin);
/*     */     }
/*     */ 
/*     */     public AABB(AABB other) {
/* 194 */       set(other);
/*     */     }
/*     */ 
/*     */     public AABB(AABB other, float margin) {
/* 198 */       this(other);
/* 199 */       this.min.x -= margin;
/* 200 */       this.min.y -= margin;
/* 201 */       this.min.z -= margin;
/* 202 */       this.max.x += margin;
/* 203 */       this.max.y += margin;
/* 204 */       this.max.z += margin;
/*     */     }
/*     */ 
/*     */     public void init(Vector3f V1, Vector3f V2, Vector3f V3, float margin) {
/* 208 */       calc_from_triangle_margin(V1, V2, V3, margin);
/*     */     }
/*     */ 
/*     */     public void set(AABB other) {
/* 212 */       this.min.set(other.min);
/* 213 */       this.max.set(other.max);
/*     */     }
/*     */ 
/*     */     public void invalidate() {
/* 217 */       this.min.set(3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F);
/* 218 */       this.max.set(-3.402824E+038F, -3.402824E+038F, -3.402824E+038F);
/*     */     }
/*     */ 
/*     */     public void increment_margin(float margin) {
/* 222 */       this.min.x -= margin;
/* 223 */       this.min.y -= margin;
/* 224 */       this.min.z -= margin;
/* 225 */       this.max.x += margin;
/* 226 */       this.max.y += margin;
/* 227 */       this.max.z += margin;
/*     */     }
/*     */ 
/*     */     public void copy_with_margin(AABB other, float margin) {
/* 231 */       other.min.x -= margin;
/* 232 */       other.min.y -= margin;
/* 233 */       other.min.z -= margin;
/*     */ 
/* 235 */       other.max.x += margin;
/* 236 */       other.max.y += margin;
/* 237 */       other.max.z += margin;
/*     */     }
/*     */ 
/*     */     public void calc_from_triangle(Vector3f V1, Vector3f V2, Vector3f V3) {
/* 241 */       this.min.x = BoxCollision.BT_MIN3(V1.x, V2.x, V3.x);
/* 242 */       this.min.y = BoxCollision.BT_MIN3(V1.y, V2.y, V3.y);
/* 243 */       this.min.z = BoxCollision.BT_MIN3(V1.z, V2.z, V3.z);
/*     */ 
/* 245 */       this.max.x = BoxCollision.BT_MAX3(V1.x, V2.x, V3.x);
/* 246 */       this.max.y = BoxCollision.BT_MAX3(V1.y, V2.y, V3.y);
/* 247 */       this.max.z = BoxCollision.BT_MAX3(V1.z, V2.z, V3.z);
/*     */     }
/*     */ 
/*     */     public void calc_from_triangle_margin(Vector3f V1, Vector3f V2, Vector3f V3, float margin) {
/* 251 */       calc_from_triangle(V1, V2, V3);
/* 252 */       this.min.x -= margin;
/* 253 */       this.min.y -= margin;
/* 254 */       this.min.z -= margin;
/* 255 */       this.max.x += margin;
/* 256 */       this.max.y += margin;
/* 257 */       this.max.z += margin;
/*     */     }
/*     */ 
/*     */     public void appy_transform(Transform arg1)
/*     */     {
/* 264 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 266 */         Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 267 */         center.add(this.max, this.min);
/* 268 */         center.scale(0.5F);
/*     */ 
/* 270 */         Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
/* 271 */         extends_.sub(this.max, center);
/*     */ 
/* 274 */         trans.transform(center);
/*     */ 
/* 276 */         Vector3f textends = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 278 */         trans.basis.getRow(0, tmp);
/* 279 */         tmp.absolute();
/* 280 */         textends.x = extends_.dot(tmp);
/*     */ 
/* 282 */         trans.basis.getRow(1, tmp);
/* 283 */         tmp.absolute();
/* 284 */         textends.y = extends_.dot(tmp);
/*     */ 
/* 286 */         trans.basis.getRow(2, tmp);
/* 287 */         tmp.absolute();
/* 288 */         textends.z = extends_.dot(tmp);
/*     */ 
/* 290 */         this.min.sub(center, textends);
/* 291 */         this.max.add(center, textends);
/*     */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public void appy_transform_trans_cache(BoxCollision.BoxBoxTransformCache arg1)
/*     */     {
/* 298 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 300 */         Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 301 */         center.add(this.max, this.min);
/* 302 */         center.scale(0.5F);
/*     */ 
/* 304 */         Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
/* 305 */         extends_.sub(this.max, center);
/*     */ 
/* 308 */         trans.transform(center, center);
/*     */ 
/* 310 */         Vector3f textends = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 312 */         trans.R1to0.getRow(0, tmp);
/* 313 */         tmp.absolute();
/* 314 */         textends.x = extends_.dot(tmp);
/*     */ 
/* 316 */         trans.R1to0.getRow(1, tmp);
/* 317 */         tmp.absolute();
/* 318 */         textends.y = extends_.dot(tmp);
/*     */ 
/* 320 */         trans.R1to0.getRow(2, tmp);
/* 321 */         tmp.absolute();
/* 322 */         textends.z = extends_.dot(tmp);
/*     */ 
/* 324 */         this.min.sub(center, textends);
/* 325 */         this.max.add(center, textends);
/*     */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public void merge(AABB box)
/*     */     {
/* 332 */       this.min.x = Math.min(this.min.x, box.min.x);
/* 333 */       this.min.y = Math.min(this.min.y, box.min.y);
/* 334 */       this.min.z = Math.min(this.min.z, box.min.z);
/*     */ 
/* 336 */       this.max.x = Math.max(this.max.x, box.max.x);
/* 337 */       this.max.y = Math.max(this.max.y, box.max.y);
/* 338 */       this.max.z = Math.max(this.max.z, box.max.z);
/*     */     }
/*     */ 
/*     */     public void merge_point(Vector3f point)
/*     */     {
/* 345 */       this.min.x = Math.min(this.min.x, point.x);
/* 346 */       this.min.y = Math.min(this.min.y, point.y);
/* 347 */       this.min.z = Math.min(this.min.z, point.z);
/*     */ 
/* 349 */       this.max.x = Math.max(this.max.x, point.x);
/* 350 */       this.max.y = Math.max(this.max.y, point.y);
/* 351 */       this.max.z = Math.max(this.max.z, point.z);
/*     */     }
/*     */ 
/*     */     public void get_center_extend(Vector3f center, Vector3f extend)
/*     */     {
/* 358 */       center.add(this.max, this.min);
/* 359 */       center.scale(0.5F);
/*     */ 
/* 361 */       extend.sub(this.max, center);
/*     */     }
/*     */ 
/*     */     public void find_intersection(AABB other, AABB intersection)
/*     */     {
/* 368 */       intersection.min.x = Math.max(other.min.x, this.min.x);
/* 369 */       intersection.min.y = Math.max(other.min.y, this.min.y);
/* 370 */       intersection.min.z = Math.max(other.min.z, this.min.z);
/*     */ 
/* 372 */       intersection.max.x = Math.min(other.max.x, this.max.x);
/* 373 */       intersection.max.y = Math.min(other.max.y, this.max.y);
/* 374 */       intersection.max.z = Math.min(other.max.z, this.max.z);
/*     */     }
/*     */ 
/*     */     public boolean has_collision(AABB other) {
/* 378 */       if ((this.min.x > other.max.x) || (this.max.x < other.min.x) || (this.min.y > other.max.y) || (this.max.y < other.min.y) || (this.min.z > other.max.z) || (this.max.z < other.min.z))
/*     */       {
/* 384 */         return false;
/*     */       }
/* 386 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean collide_ray(Vector3f arg1, Vector3f arg2)
/*     */     {
/* 397 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f extents = localStack.get$javax$vecmath$Vector3f(); Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 398 */         get_center_extend(center, extents);
/*     */ 
/* 400 */         float Dx = vorigin.x - center.x;
/* 401 */         if ((BoxCollision.BT_GREATER(Dx, extents.x)) && (Dx * vdir.x >= 0.0F)) return false;
/*     */ 
/* 403 */         float Dy = vorigin.y - center.y;
/* 404 */         if ((BoxCollision.BT_GREATER(Dy, extents.y)) && (Dy * vdir.y >= 0.0F)) return false;
/*     */ 
/* 406 */         float Dz = vorigin.z - center.z;
/* 407 */         if ((BoxCollision.BT_GREATER(Dz, extents.z)) && (Dz * vdir.z >= 0.0F)) return false;
/*     */ 
/* 409 */         float f = vdir.y * Dz - vdir.z * Dy;
/* 410 */         if (Math.abs(f) > extents.y * Math.abs(vdir.z) + extents.z * Math.abs(vdir.y)) return false;
/*     */ 
/* 412 */         f = vdir.z * Dx - vdir.x * Dz;
/* 413 */         if (Math.abs(f) > extents.x * Math.abs(vdir.z) + extents.z * Math.abs(vdir.x)) return false;
/*     */ 
/* 415 */         f = vdir.x * Dy - vdir.y * Dx;
/* 416 */         if (Math.abs(f) > extents.x * Math.abs(vdir.y) + extents.y * Math.abs(vdir.x)) return false;
/*     */ 
/* 418 */         return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public void projection_interval(Vector3f arg1, float[] arg2, float[] arg3) {
/* 422 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 424 */         Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 425 */         Vector3f extend = localStack.get$javax$vecmath$Vector3f();
/* 426 */         get_center_extend(center, extend);
/*     */ 
/* 428 */         float _fOrigin = direction.dot(center);
/* 429 */         tmp.absolute(direction);
/* 430 */         float _fMaximumExtent = extend.dot(tmp);
/* 431 */         vmin[0] = (_fOrigin - _fMaximumExtent);
/* 432 */         vmax[0] = (_fOrigin + _fMaximumExtent);
/*     */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */     public PlaneIntersectionType plane_classify(Vector4f arg1) {
/* 436 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 438 */         float[] _fmin = new float[1]; float[] _fmax = new float[1];
/* 439 */         tmp.set(plane.x, plane.y, plane.z);
/* 440 */         projection_interval(tmp, _fmin, _fmax);
/*     */ 
/* 442 */         if (plane.w > _fmax[0] + 1.0E-006F) {
/* 443 */           return PlaneIntersectionType.BACK_PLANE;
/*     */         }
/*     */ 
/* 446 */         if (plane.w + 1.0E-006F >= _fmin[0]) {
/* 447 */           return PlaneIntersectionType.COLLIDE_PLANE;
/*     */         }
/*     */ 
/* 450 */         return PlaneIntersectionType.FRONT_PLANE; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean overlapping_trans_conservative(AABB arg1, Transform arg2) {
/* 454 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); AABB tbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
/* 455 */         tbox.appy_transform(trans1_to_0);
/* 456 */         return has_collision(tbox); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean overlapping_trans_conservative2(AABB arg1, BoxCollision.BoxBoxTransformCache arg2) {
/* 460 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); AABB tbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(box);
/* 461 */         tbox.appy_transform_trans_cache(trans1_to_0);
/* 462 */         return has_collision(tbox); } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean overlapping_trans_cache(AABB arg1, BoxCollision.BoxBoxTransformCache arg2, boolean arg3)
/*     */     {
/* 469 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 472 */         Vector3f ea = localStack.get$javax$vecmath$Vector3f(); Vector3f eb = localStack.get$javax$vecmath$Vector3f();
/* 473 */         Vector3f ca = localStack.get$javax$vecmath$Vector3f(); Vector3f cb = localStack.get$javax$vecmath$Vector3f();
/* 474 */         get_center_extend(ca, ea);
/* 475 */         box.get_center_extend(cb, eb);
/*     */ 
/* 477 */         Vector3f T = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 481 */         for (int i = 0; i < 3; i++) {
/* 482 */           transcache.R1to0.getRow(i, tmp);
/* 483 */           VectorUtil.setCoord(T, i, tmp.dot(cb) + VectorUtil.getCoord(transcache.T1to0, i) - VectorUtil.getCoord(ca, i));
/*     */ 
/* 485 */           transcache.AR.getRow(i, tmp);
/* 486 */           float t = tmp.dot(eb) + VectorUtil.getCoord(ea, i);
/* 487 */           if (BoxCollision.BT_GREATER(VectorUtil.getCoord(T, i), t)) {
/* 488 */             return false;
/*     */           }
/*     */         }
/*     */ 
/* 492 */         for (int i = 0; i < 3; i++) {
/* 493 */           float t = BoxCollision.bt_mat3_dot_col(transcache.R1to0, T, i);
/* 494 */           float t2 = BoxCollision.bt_mat3_dot_col(transcache.AR, ea, i) + VectorUtil.getCoord(eb, i);
/* 495 */           if (BoxCollision.BT_GREATER(t, t2)) {
/* 496 */             return false;
/*     */           }
/*     */         }
/*     */ 
/* 500 */         if (fulltest)
/*     */         {
/* 502 */           for (int i = 0; i < 3; i++) {
/* 503 */             int m = (i + 1) % 3;
/* 504 */             int n = (i + 2) % 3;
/* 505 */             int o = i == 0 ? 1 : 0;
/* 506 */             int p = i == 2 ? 1 : 2;
/* 507 */             for (int j = 0; j < 3; j++) {
/* 508 */               int q = j == 2 ? 1 : 2;
/* 509 */               int r = j == 0 ? 1 : 0;
/* 510 */               float t = VectorUtil.getCoord(T, n) * transcache.R1to0.getElement(m, j) - VectorUtil.getCoord(T, m) * transcache.R1to0.getElement(n, j);
/* 511 */               float t2 = VectorUtil.getCoord(ea, o) * transcache.AR.getElement(p, j) + VectorUtil.getCoord(ea, p) * transcache.AR.getElement(o, j) + VectorUtil.getCoord(eb, r) * transcache.AR.getElement(i, q) + VectorUtil.getCoord(eb, q) * transcache.AR.getElement(i, r);
/*     */ 
/* 513 */               if (BoxCollision.BT_GREATER(t, t2)) {
/* 514 */                 return false;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 519 */         return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */ 
/*     */     public boolean collide_plane(Vector4f plane)
/*     */     {
/* 526 */       PlaneIntersectionType classify = plane_classify(plane);
/* 527 */       return classify == PlaneIntersectionType.COLLIDE_PLANE;
/*     */     }
/*     */ 
/*     */     public boolean collide_triangle_exact(Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector4f arg4)
/*     */     {
/* 534 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); if (!collide_plane(triangle_plane)) {
/* 535 */           return false;
/*     */         }
/* 537 */         Vector3f center = localStack.get$javax$vecmath$Vector3f(); Vector3f extends_ = localStack.get$javax$vecmath$Vector3f();
/* 538 */         get_center_extend(center, extends_);
/*     */ 
/* 540 */         Vector3f v1 = localStack.get$javax$vecmath$Vector3f();
/* 541 */         v1.sub(p1, center);
/* 542 */         Vector3f v2 = localStack.get$javax$vecmath$Vector3f();
/* 543 */         v2.sub(p2, center);
/* 544 */         Vector3f v3 = localStack.get$javax$vecmath$Vector3f();
/* 545 */         v3.sub(p3, center);
/*     */ 
/* 548 */         Vector3f diff = localStack.get$javax$vecmath$Vector3f();
/* 549 */         diff.sub(v2, v1);
/* 550 */         Vector3f abs_diff = localStack.get$javax$vecmath$Vector3f();
/* 551 */         abs_diff.absolute(diff);
/*     */ 
/* 554 */         BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, v1, v3, extends_);
/*     */ 
/* 556 */         BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, v1, v3, extends_);
/*     */ 
/* 558 */         BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, v1, v3, extends_);
/*     */ 
/* 560 */         diff.sub(v3, v2);
/* 561 */         abs_diff.absolute(diff);
/*     */ 
/* 564 */         BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, v2, v1, extends_);
/*     */ 
/* 566 */         BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, v2, v1, extends_);
/*     */ 
/* 568 */         BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, v2, v1, extends_);
/*     */ 
/* 570 */         diff.sub(v1, v3);
/* 571 */         abs_diff.absolute(diff);
/*     */ 
/* 574 */         BoxCollision.TEST_CROSS_EDGE_BOX_X_AXIS_MCR(diff, abs_diff, v3, v2, extends_);
/*     */ 
/* 576 */         BoxCollision.TEST_CROSS_EDGE_BOX_Y_AXIS_MCR(diff, abs_diff, v3, v2, extends_);
/*     */ 
/* 578 */         BoxCollision.TEST_CROSS_EDGE_BOX_Z_AXIS_MCR(diff, abs_diff, v3, v2, extends_);
/*     */ 
/* 580 */         return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class BoxBoxTransformCache
/*     */   {
/* 107 */     public final Vector3f T1to0 = new Vector3f();
/* 108 */     public final Matrix3f R1to0 = new Matrix3f();
/* 109 */     public final Matrix3f AR = new Matrix3f();
/*     */ 
/*     */     public void set(BoxBoxTransformCache cache) {
/* 112 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public void calc_absolute_matrix()
/*     */     {
/* 121 */       for (int i = 0; i < 3; i++)
/* 122 */         for (int j = 0; j < 3; j++)
/* 123 */           this.AR.setElement(i, j, 1.0E-006F + Math.abs(this.R1to0.getElement(i, j)));
/*     */     }
/*     */ 
/*     */     public void calc_from_homogenic(Transform arg1, Transform arg2)
/*     */     {
/* 132 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform temp_trans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 133 */         temp_trans.inverse(trans0);
/* 134 */         temp_trans.mul(trans1);
/*     */ 
/* 136 */         this.T1to0.set(temp_trans.origin);
/* 137 */         this.R1to0.set(temp_trans.basis);
/*     */ 
/* 139 */         calc_absolute_matrix();
/*     */         return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */     }
/*     */ 
/*     */     public void calc_from_full_invert(Transform arg1, Transform arg2)
/*     */     {
/* 146 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); this.R1to0.invert(trans0.basis);
/* 147 */         this.T1to0.negate(trans0.origin);
/* 148 */         this.R1to0.transform(this.T1to0);
/*     */ 
/* 150 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 151 */         tmp.set(trans1.origin);
/* 152 */         this.R1to0.transform(tmp);
/* 153 */         this.T1to0.add(tmp);
/*     */ 
/* 155 */         this.R1to0.mul(trans1.basis);
/*     */ 
/* 157 */         calc_absolute_matrix();
/*     */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */     public Vector3f transform(Vector3f arg1, Vector3f arg2) {
/* 161 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); if (point == out) {
/* 162 */           point = localStack.get$javax$vecmath$Vector3f(point);
/*     */         }
/*     */ 
/* 165 */         Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 166 */         this.R1to0.getRow(0, tmp);
/* 167 */         out.x = (tmp.dot(point) + this.T1to0.x);
/* 168 */         this.R1to0.getRow(1, tmp);
/* 169 */         out.y = (tmp.dot(point) + this.T1to0.y);
/* 170 */         this.R1to0.getRow(2, tmp);
/* 171 */         out.z = (tmp.dot(point) + this.T1to0.z);
/* 172 */         return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BoxCollision
 * JD-Core Version:    0.6.2
 */