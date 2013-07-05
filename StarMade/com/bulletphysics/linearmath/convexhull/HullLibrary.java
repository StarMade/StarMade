/*     */ package com.bulletphysics.linearmath.convexhull;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.MiscUtil;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class HullLibrary
/*     */ {
/*  46 */   public final IntArrayList vertexIndexMapping = new IntArrayList();
/*     */ 
/*  48 */   private ObjectArrayList<Tri> tris = new ObjectArrayList();
/*     */   private static final float EPSILON = 1.0E-006F;
/*     */ 
/*     */   public boolean createConvexHull(HullDesc arg1, HullResult arg2)
/*     */   {
/*  58 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); boolean ret = false;
/*     */ 
/*  60 */       PHullResult hr = new PHullResult();
/*     */ 
/*  62 */       int vcount = desc.vcount;
/*  63 */       if (vcount < 8) vcount = 8;
/*     */ 
/*  65 */       ObjectArrayList vertexSource = new ObjectArrayList();
/*  66 */       MiscUtil.resize(vertexSource, vcount, Vector3f.class);
/*     */ 
/*  68 */       Vector3f scale = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  70 */       int[] ovcount = new int[1];
/*     */ 
/*  72 */       boolean ok = cleanupVertices(desc.vcount, desc.vertices, desc.vertexStride, ovcount, vertexSource, desc.normalEpsilon, scale);
/*     */ 
/*  74 */       if (ok)
/*     */       {
/*  77 */         for (int i = 0; i < ovcount[0]; i++) {
/*  78 */           Vector3f v = (Vector3f)vertexSource.getQuick(i);
/*  79 */           VectorUtil.mul(v, v, scale);
/*     */         }
/*     */ 
/*  83 */         ok = computeHull(ovcount[0], vertexSource, hr, desc.maxVertices);
/*     */ 
/*  85 */         if (ok)
/*     */         {
/*  87 */           ObjectArrayList vertexScratch = new ObjectArrayList();
/*  88 */           MiscUtil.resize(vertexScratch, hr.vcount, Vector3f.class);
/*     */ 
/*  90 */           bringOutYourDead(hr.vertices, hr.vcount, vertexScratch, ovcount, hr.indices, hr.indexCount);
/*     */ 
/*  92 */           ret = true;
/*     */ 
/*  94 */           if (desc.hasHullFlag(HullFlags.TRIANGLES)) {
/*  95 */             result.polygons = false;
/*  96 */             result.numOutputVertices = ovcount[0];
/*  97 */             MiscUtil.resize(result.outputVertices, ovcount[0], Vector3f.class);
/*  98 */             result.numFaces = hr.faceCount;
/*  99 */             result.numIndices = hr.indexCount;
/*     */ 
/* 101 */             MiscUtil.resize(result.indices, hr.indexCount, 0);
/*     */ 
/* 103 */             for (int i = 0; i < ovcount[0]; i++) {
/* 104 */               ((Vector3f)result.outputVertices.getQuick(i)).set((Tuple3f)vertexScratch.getQuick(i));
/*     */             }
/*     */ 
/* 107 */             if (desc.hasHullFlag(HullFlags.REVERSE_ORDER)) {
/* 108 */               IntArrayList source_ptr = hr.indices;
/* 109 */               int source_idx = 0;
/*     */ 
/* 111 */               IntArrayList dest_ptr = result.indices;
/* 112 */               int dest_idx = 0;
/*     */ 
/* 114 */               for (int i = 0; i < hr.faceCount; i++) {
/* 115 */                 dest_ptr.set(dest_idx + 0, source_ptr.get(source_idx + 2));
/* 116 */                 dest_ptr.set(dest_idx + 1, source_ptr.get(source_idx + 1));
/* 117 */                 dest_ptr.set(dest_idx + 2, source_ptr.get(source_idx + 0));
/* 118 */                 dest_idx += 3;
/* 119 */                 source_idx += 3;
/*     */               }
/*     */             }
/*     */             else {
/* 123 */               for (int i = 0; i < hr.indexCount; i++)
/* 124 */                 result.indices.set(i, hr.indices.get(i));
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 129 */             result.polygons = true;
/* 130 */             result.numOutputVertices = ovcount[0];
/* 131 */             MiscUtil.resize(result.outputVertices, ovcount[0], Vector3f.class);
/* 132 */             result.numFaces = hr.faceCount;
/* 133 */             result.numIndices = (hr.indexCount + hr.faceCount);
/* 134 */             MiscUtil.resize(result.indices, result.numIndices, 0);
/* 135 */             for (int i = 0; i < ovcount[0]; i++) {
/* 136 */               ((Vector3f)result.outputVertices.getQuick(i)).set((Tuple3f)vertexScratch.getQuick(i));
/*     */             }
/*     */ 
/* 141 */             IntArrayList source_ptr = hr.indices;
/* 142 */             int source_idx = 0;
/*     */ 
/* 144 */             IntArrayList dest_ptr = result.indices;
/* 145 */             int dest_idx = 0;
/*     */ 
/* 147 */             for (int i = 0; i < hr.faceCount; i++) {
/* 148 */               dest_ptr.set(dest_idx + 0, 3);
/* 149 */               if (desc.hasHullFlag(HullFlags.REVERSE_ORDER)) {
/* 150 */                 dest_ptr.set(dest_idx + 1, source_ptr.get(source_idx + 2));
/* 151 */                 dest_ptr.set(dest_idx + 2, source_ptr.get(source_idx + 1));
/* 152 */                 dest_ptr.set(dest_idx + 3, source_ptr.get(source_idx + 0));
/*     */               }
/*     */               else {
/* 155 */                 dest_ptr.set(dest_idx + 1, source_ptr.get(source_idx + 0));
/* 156 */                 dest_ptr.set(dest_idx + 2, source_ptr.get(source_idx + 1));
/* 157 */                 dest_ptr.set(dest_idx + 3, source_ptr.get(source_idx + 2));
/*     */               }
/*     */ 
/* 160 */               dest_idx += 4;
/* 161 */               source_idx += 3;
/*     */             }
/*     */           }
/*     */ 
/* 165 */           releaseHull(hr);
/*     */         }
/*     */       }
/*     */ 
/* 169 */       return ret; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean releaseResult(HullResult result)
/*     */   {
/* 176 */     if (result.outputVertices.size() != 0) {
/* 177 */       result.numOutputVertices = 0;
/* 178 */       result.outputVertices.clear();
/*     */     }
/* 180 */     if (result.indices.size() != 0) {
/* 181 */       result.numIndices = 0;
/* 182 */       result.indices.clear();
/*     */     }
/* 184 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean computeHull(int vcount, ObjectArrayList<Vector3f> vertices, PHullResult result, int vlimit) {
/* 188 */     int[] tris_count = new int[1];
/* 189 */     int ret = calchull(vertices, vcount, result.indices, tris_count, vlimit);
/* 190 */     if (ret == 0) return false;
/* 191 */     result.indexCount = (tris_count[0] * 3);
/* 192 */     result.faceCount = tris_count[0];
/* 193 */     result.vertices = vertices;
/* 194 */     result.vcount = vcount;
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   private Tri allocateTriangle(int a, int b, int c) {
/* 199 */     Tri tr = new Tri(a, b, c);
/* 200 */     tr.id = this.tris.size();
/* 201 */     this.tris.add(tr);
/*     */ 
/* 203 */     return tr;
/*     */   }
/*     */ 
/*     */   private void deAllocateTriangle(Tri tri) {
/* 207 */     assert (this.tris.getQuick(tri.id) == tri);
/* 208 */     this.tris.setQuick(tri.id, null);
/*     */   }
/*     */ 
/*     */   private void b2bfix(Tri s, Tri t) {
/* 212 */     for (int i = 0; i < 3; i++) {
/* 213 */       int i1 = (i + 1) % 3;
/* 214 */       int i2 = (i + 2) % 3;
/* 215 */       int a = s.getCoord(i1);
/* 216 */       int b = s.getCoord(i2);
/* 217 */       assert (((Tri)this.tris.getQuick(s.neib(a, b).get())).neib(b, a).get() == s.id);
/* 218 */       assert (((Tri)this.tris.getQuick(t.neib(a, b).get())).neib(b, a).get() == t.id);
/* 219 */       ((Tri)this.tris.getQuick(s.neib(a, b).get())).neib(b, a).set(t.neib(b, a).get());
/* 220 */       ((Tri)this.tris.getQuick(t.neib(b, a).get())).neib(a, b).set(s.neib(a, b).get());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void removeb2b(Tri s, Tri t) {
/* 225 */     b2bfix(s, t);
/* 226 */     deAllocateTriangle(s);
/*     */ 
/* 228 */     deAllocateTriangle(t);
/*     */   }
/*     */ 
/*     */   private void checkit(Tri t) {
/* 232 */     assert (this.tris.getQuick(t.id) == t);
/* 233 */     for (int i = 0; i < 3; i++) {
/* 234 */       int i1 = (i + 1) % 3;
/* 235 */       int i2 = (i + 2) % 3;
/* 236 */       int a = t.getCoord(i1);
/* 237 */       int b = t.getCoord(i2);
/*     */ 
/* 239 */       assert (a != b);
/* 240 */       assert (((Tri)this.tris.getQuick(t.n.getCoord(i))).neib(b, a).get() == t.id);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Tri extrudable(float epsilon) {
/* 245 */     Tri t = null;
/* 246 */     for (int i = 0; i < this.tris.size(); i++) {
/* 247 */       if ((t == null) || ((this.tris.getQuick(i) != null) && (t.rise < ((Tri)this.tris.getQuick(i)).rise))) {
/* 248 */         t = (Tri)this.tris.getQuick(i);
/*     */       }
/*     */     }
/* 251 */     return t.rise > epsilon ? t : null;
/*     */   }
/*     */ 
/*     */   private int calchull(ObjectArrayList<Vector3f> verts, int verts_count, IntArrayList tris_out, int[] tris_count, int vlimit) {
/* 255 */     int rc = calchullgen(verts, verts_count, vlimit);
/* 256 */     if (rc == 0) return 0;
/*     */ 
/* 258 */     IntArrayList ts = new IntArrayList();
/*     */ 
/* 260 */     for (int i = 0; i < this.tris.size(); i++) {
/* 261 */       if (this.tris.getQuick(i) != null) {
/* 262 */         for (int j = 0; j < 3; j++) {
/* 263 */           ts.add(((Tri)this.tris.getQuick(i)).getCoord(j));
/*     */         }
/* 265 */         deAllocateTriangle((Tri)this.tris.getQuick(i));
/*     */       }
/*     */     }
/* 268 */     tris_count[0] = (ts.size() / 3);
/* 269 */     MiscUtil.resize(tris_out, ts.size(), 0);
/*     */ 
/* 271 */     for (int i = 0; i < ts.size(); i++) {
/* 272 */       tris_out.set(i, ts.get(i));
/*     */     }
/* 274 */     MiscUtil.resize(this.tris, 0, Tri.class);
/*     */ 
/* 276 */     return 1;
/*     */   }
/*     */ 
/*     */   private int calchullgen(ObjectArrayList<Vector3f> arg1, int arg2, int arg3) {
/* 280 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (verts_count < 4) return 0;
/*     */ 
/* 282 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 283 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 284 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 286 */       if (vlimit == 0) {
/* 287 */         vlimit = 1000000000;
/*     */       }
/*     */ 
/* 290 */       Vector3f bmin = localStack.get$javax$vecmath$Vector3f((Vector3f)verts.getQuick(0));
/* 291 */       Vector3f bmax = localStack.get$javax$vecmath$Vector3f((Vector3f)verts.getQuick(0));
/* 292 */       IntArrayList isextreme = new IntArrayList();
/*     */ 
/* 294 */       IntArrayList allow = new IntArrayList();
/*     */ 
/* 297 */       for (int j = 0; j < verts_count; j++) {
/* 298 */         allow.add(1);
/* 299 */         isextreme.add(0);
/* 300 */         VectorUtil.setMin(bmin, (Vector3f)verts.getQuick(j));
/* 301 */         VectorUtil.setMax(bmax, (Vector3f)verts.getQuick(j));
/*     */       }
/* 303 */       tmp.sub(bmax, bmin);
/* 304 */       float epsilon = tmp.length() * 0.001F;
/* 305 */       assert (epsilon != 0.0F);
/*     */ 
/* 307 */       Int4 p = findSimplex(verts, verts_count, allow, new Int4());
/* 308 */       if (p.x == -1) {
/* 309 */         return 0;
/*     */       }
/*     */ 
/* 313 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 314 */       VectorUtil.add(center, (Vector3f)verts.getQuick(p.getCoord(0)), (Vector3f)verts.getQuick(p.getCoord(1)), (Vector3f)verts.getQuick(p.getCoord(2)), (Vector3f)verts.getQuick(p.getCoord(3)));
/* 315 */       center.scale(0.25F);
/*     */ 
/* 317 */       Tri t0 = allocateTriangle(p.getCoord(2), p.getCoord(3), p.getCoord(1));
/* 318 */       t0.n.set(2, 3, 1);
/* 319 */       Tri t1 = allocateTriangle(p.getCoord(3), p.getCoord(2), p.getCoord(0));
/* 320 */       t1.n.set(3, 2, 0);
/* 321 */       Tri t2 = allocateTriangle(p.getCoord(0), p.getCoord(1), p.getCoord(3));
/* 322 */       t2.n.set(0, 1, 3);
/* 323 */       Tri t3 = allocateTriangle(p.getCoord(1), p.getCoord(0), p.getCoord(2));
/* 324 */       t3.n.set(1, 0, 2);
/* 325 */       isextreme.set(p.getCoord(0), 1);
/* 326 */       isextreme.set(p.getCoord(1), 1);
/* 327 */       isextreme.set(p.getCoord(2), 1);
/* 328 */       isextreme.set(p.getCoord(3), 1);
/* 329 */       checkit(t0);
/* 330 */       checkit(t1);
/* 331 */       checkit(t2);
/* 332 */       checkit(t3);
/*     */ 
/* 334 */       Vector3f n = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 336 */       for (int j = 0; j < this.tris.size(); j++) {
/* 337 */         Tri t = (Tri)this.tris.getQuick(j);
/* 338 */         assert (t != null);
/* 339 */         assert (t.vmax < 0);
/* 340 */         triNormal((Vector3f)verts.getQuick(t.getCoord(0)), (Vector3f)verts.getQuick(t.getCoord(1)), (Vector3f)verts.getQuick(t.getCoord(2)), n);
/* 341 */         t.vmax = maxdirsterid(verts, verts_count, n, allow);
/* 342 */         tmp.sub((Tuple3f)verts.getQuick(t.vmax), (Tuple3f)verts.getQuick(t.getCoord(0)));
/* 343 */         t.rise = n.dot(tmp);
/*     */       }
/*     */ 
/* 346 */       vlimit -= 4;
/*     */       Tri te;
/* 347 */       while ((vlimit > 0) && ((te = extrudable(epsilon)) != null)) {
/* 348 */         Int3 ti = te;
/* 349 */         int v = te.vmax;
/* 350 */         assert (v != -1);
/* 351 */         assert (isextreme.get(v) == 0);
/* 352 */         isextreme.set(v, 1);
/*     */ 
/* 354 */         int j = this.tris.size();
/* 355 */         while (j-- != 0) {
/* 356 */           if (this.tris.getQuick(j) != null)
/*     */           {
/* 359 */             Int3 t = (Int3)this.tris.getQuick(j);
/* 360 */             if (above(verts, t, (Vector3f)verts.getQuick(v), 0.01F * epsilon)) {
/* 361 */               extrude((Tri)this.tris.getQuick(j), v);
/*     */             }
/*     */           }
/*     */         }
/* 365 */         j = this.tris.size();
/* 366 */         while (j-- != 0)
/* 367 */           if (this.tris.getQuick(j) != null)
/*     */           {
/* 370 */             if (!hasvert((Int3)this.tris.getQuick(j), v)) {
/*     */               break;
/*     */             }
/* 373 */             Int3 nt = (Int3)this.tris.getQuick(j);
/* 374 */             tmp1.sub((Tuple3f)verts.getQuick(nt.getCoord(1)), (Tuple3f)verts.getQuick(nt.getCoord(0)));
/* 375 */             tmp2.sub((Tuple3f)verts.getQuick(nt.getCoord(2)), (Tuple3f)verts.getQuick(nt.getCoord(1)));
/* 376 */             tmp.cross(tmp1, tmp2);
/* 377 */             if ((above(verts, nt, center, 0.01F * epsilon)) || (tmp.length() < epsilon * epsilon * 0.1F)) {
/* 378 */               Tri nb = (Tri)this.tris.getQuick(((Tri)this.tris.getQuick(j)).n.getCoord(0));
/* 379 */               assert (nb != null);
/* 380 */               assert (!hasvert(nb, v));
/* 381 */               assert (nb.id < j);
/* 382 */               extrude(nb, v);
/* 383 */               j = this.tris.size();
/*     */             }
/*     */           }
/* 386 */         j = this.tris.size();
/* 387 */         while (j-- != 0) {
/* 388 */           Tri t = (Tri)this.tris.getQuick(j);
/* 389 */           if (t != null)
/*     */           {
/* 392 */             if (t.vmax >= 0) {
/*     */               break;
/*     */             }
/* 395 */             triNormal((Vector3f)verts.getQuick(t.getCoord(0)), (Vector3f)verts.getQuick(t.getCoord(1)), (Vector3f)verts.getQuick(t.getCoord(2)), n);
/* 396 */             t.vmax = maxdirsterid(verts, verts_count, n, allow);
/* 397 */             if (isextreme.get(t.vmax) != 0) {
/* 398 */               t.vmax = -1;
/*     */             }
/*     */             else {
/* 401 */               tmp.sub((Tuple3f)verts.getQuick(t.vmax), (Tuple3f)verts.getQuick(t.getCoord(0)));
/* 402 */               t.rise = n.dot(tmp);
/*     */             }
/*     */           }
/*     */         }
/* 405 */         vlimit--;
/*     */       }
/* 407 */       return 1; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private Int4 findSimplex(ObjectArrayList<Vector3f> arg1, int arg2, IntArrayList arg3, Int4 arg4) {
/* 411 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 412 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 413 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 415 */       Vector3f[] basis = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/* 416 */       basis[0].set(0.01F, 0.02F, 1.0F);
/* 417 */       int p0 = maxdirsterid(verts, verts_count, basis[0], allow);
/* 418 */       tmp.negate(basis[0]);
/* 419 */       int p1 = maxdirsterid(verts, verts_count, tmp, allow);
/* 420 */       basis[0].sub((Tuple3f)verts.getQuick(p0), (Tuple3f)verts.getQuick(p1));
/* 421 */       if ((p0 == p1) || ((basis[0].x == 0.0F) && (basis[0].y == 0.0F) && (basis[0].z == 0.0F))) {
/* 422 */         out.set(-1, -1, -1, -1);
/* 423 */         return out;
/*     */       }
/* 425 */       tmp.set(1.0F, 0.02F, 0.0F);
/* 426 */       basis[1].cross(tmp, basis[0]);
/* 427 */       tmp.set(-0.02F, 1.0F, 0.0F);
/* 428 */       basis[2].cross(tmp, basis[0]);
/* 429 */       if (basis[1].length() > basis[2].length()) {
/* 430 */         basis[1].normalize();
/*     */       }
/*     */       else {
/* 433 */         basis[1].set(basis[2]);
/* 434 */         basis[1].normalize();
/*     */       }
/* 436 */       int p2 = maxdirsterid(verts, verts_count, basis[1], allow);
/* 437 */       if ((p2 == p0) || (p2 == p1)) {
/* 438 */         tmp.negate(basis[1]);
/* 439 */         p2 = maxdirsterid(verts, verts_count, tmp, allow);
/*     */       }
/* 441 */       if ((p2 == p0) || (p2 == p1)) {
/* 442 */         out.set(-1, -1, -1, -1);
/* 443 */         return out;
/*     */       }
/* 445 */       basis[1].sub((Tuple3f)verts.getQuick(p2), (Tuple3f)verts.getQuick(p0));
/* 446 */       basis[2].cross(basis[1], basis[0]);
/* 447 */       basis[2].normalize();
/* 448 */       int p3 = maxdirsterid(verts, verts_count, basis[2], allow);
/* 449 */       if ((p3 == p0) || (p3 == p1) || (p3 == p2)) {
/* 450 */         tmp.negate(basis[2]);
/* 451 */         p3 = maxdirsterid(verts, verts_count, tmp, allow);
/*     */       }
/* 453 */       if ((p3 == p0) || (p3 == p1) || (p3 == p2)) {
/* 454 */         out.set(-1, -1, -1, -1);
/* 455 */         return out;
/*     */       }
/* 457 */       assert ((p0 != p1) && (p0 != p2) && (p0 != p3) && (p1 != p2) && (p1 != p3) && (p2 != p3));
/*     */ 
/* 459 */       tmp1.sub((Tuple3f)verts.getQuick(p1), (Tuple3f)verts.getQuick(p0));
/* 460 */       tmp2.sub((Tuple3f)verts.getQuick(p2), (Tuple3f)verts.getQuick(p0));
/* 461 */       tmp2.cross(tmp1, tmp2);
/* 462 */       tmp1.sub((Tuple3f)verts.getQuick(p3), (Tuple3f)verts.getQuick(p0));
/* 463 */       if (tmp1.dot(tmp2) < 0.0F) {
/* 464 */         int swap_tmp = p2;
/* 465 */         p2 = p3;
/* 466 */         p3 = swap_tmp;
/*     */       }
/* 468 */       out.set(p0, p1, p2, p3);
/* 469 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private void extrude(Tri t0, int v)
/*     */   {
/* 475 */     Int3 t = new Int3(t0);
/* 476 */     int n = this.tris.size();
/* 477 */     Tri ta = allocateTriangle(v, t.getCoord(1), t.getCoord(2));
/* 478 */     ta.n.set(t0.n.getCoord(0), n + 1, n + 2);
/* 479 */     ((Tri)this.tris.getQuick(t0.n.getCoord(0))).neib(t.getCoord(1), t.getCoord(2)).set(n + 0);
/* 480 */     Tri tb = allocateTriangle(v, t.getCoord(2), t.getCoord(0));
/* 481 */     tb.n.set(t0.n.getCoord(1), n + 2, n + 0);
/* 482 */     ((Tri)this.tris.getQuick(t0.n.getCoord(1))).neib(t.getCoord(2), t.getCoord(0)).set(n + 1);
/* 483 */     Tri tc = allocateTriangle(v, t.getCoord(0), t.getCoord(1));
/* 484 */     tc.n.set(t0.n.getCoord(2), n + 0, n + 1);
/* 485 */     ((Tri)this.tris.getQuick(t0.n.getCoord(2))).neib(t.getCoord(0), t.getCoord(1)).set(n + 2);
/* 486 */     checkit(ta);
/* 487 */     checkit(tb);
/* 488 */     checkit(tc);
/* 489 */     if (hasvert((Int3)this.tris.getQuick(ta.n.getCoord(0)), v)) {
/* 490 */       removeb2b(ta, (Tri)this.tris.getQuick(ta.n.getCoord(0)));
/*     */     }
/* 492 */     if (hasvert((Int3)this.tris.getQuick(tb.n.getCoord(0)), v)) {
/* 493 */       removeb2b(tb, (Tri)this.tris.getQuick(tb.n.getCoord(0)));
/*     */     }
/* 495 */     if (hasvert((Int3)this.tris.getQuick(tc.n.getCoord(0)), v)) {
/* 496 */       removeb2b(tc, (Tri)this.tris.getQuick(tc.n.getCoord(0)));
/*     */     }
/* 498 */     deAllocateTriangle(t0);
/*     */   }
/*     */ 
/*     */   private void bringOutYourDead(ObjectArrayList<Vector3f> verts, int vcount, ObjectArrayList<Vector3f> overts, int[] ocount, IntArrayList indices, int indexcount)
/*     */   {
/* 508 */     IntArrayList tmpIndices = new IntArrayList();
/* 509 */     for (int i = 0; i < this.vertexIndexMapping.size(); i++) {
/* 510 */       tmpIndices.add(this.vertexIndexMapping.size());
/*     */     }
/*     */ 
/* 513 */     IntArrayList usedIndices = new IntArrayList();
/* 514 */     MiscUtil.resize(usedIndices, vcount, 0);
/*     */ 
/* 522 */     ocount[0] = 0;
/*     */ 
/* 524 */     for (int i = 0; i < indexcount; i++) {
/* 525 */       int v = indices.get(i);
/*     */ 
/* 527 */       assert ((v >= 0) && (v < vcount));
/*     */ 
/* 529 */       if (usedIndices.get(v) != 0) {
/* 530 */         indices.set(i, usedIndices.get(v) - 1);
/*     */       }
/*     */       else {
/* 533 */         indices.set(i, ocount[0]);
/*     */ 
/* 535 */         ((Vector3f)overts.getQuick(ocount[0])).set((Tuple3f)verts.getQuick(v));
/*     */ 
/* 537 */         for (int k = 0; k < this.vertexIndexMapping.size(); k++) {
/* 538 */           if (tmpIndices.get(k) == v) {
/* 539 */             this.vertexIndexMapping.set(k, ocount[0]);
/*     */           }
/*     */         }
/*     */ 
/* 543 */         ocount[0] += 1;
/*     */ 
/* 545 */         assert ((ocount[0] >= 0) && (ocount[0] <= vcount));
/*     */ 
/* 547 */         usedIndices.set(v, ocount[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean cleanupVertices(int arg1, ObjectArrayList<Vector3f> arg2, int arg3, int[] arg4, ObjectArrayList<Vector3f> arg5, float arg6, Vector3f arg7)
/*     */   {
/* 562 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); if (svcount == 0) {
/* 563 */         return false;
/*     */       }
/*     */ 
/* 566 */       this.vertexIndexMapping.clear();
/*     */ 
/* 568 */       vcount[0] = 0;
/*     */ 
/* 570 */       float[] recip = new float[3];
/*     */ 
/* 572 */       if (scale != null) {
/* 573 */         scale.set(1.0F, 1.0F, 1.0F);
/*     */       }
/*     */ 
/* 576 */       float[] bmin = { 3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F };
/* 577 */       float[] bmax = { -3.402824E+038F, -3.402824E+038F, -3.402824E+038F };
/*     */ 
/* 579 */       ObjectArrayList vtx_ptr = svertices;
/* 580 */       int vtx_idx = 0;
/*     */ 
/* 584 */       for (int i = 0; i < svcount; i++) {
/* 585 */         Vector3f p = (Vector3f)vtx_ptr.getQuick(vtx_idx);
/*     */ 
/* 587 */         vtx_idx++;
/*     */ 
/* 589 */         for (int j = 0; j < 3; j++) {
/* 590 */           if (VectorUtil.getCoord(p, j) < bmin[j]) {
/* 591 */             bmin[j] = VectorUtil.getCoord(p, j);
/*     */           }
/* 593 */           if (VectorUtil.getCoord(p, j) > bmax[j]) {
/* 594 */             bmax[j] = VectorUtil.getCoord(p, j);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 600 */       float dx = bmax[0] - bmin[0];
/* 601 */       float dy = bmax[1] - bmin[1];
/* 602 */       float dz = bmax[2] - bmin[2];
/*     */ 
/* 604 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 606 */       center.x = (dx * 0.5F + bmin[0]);
/* 607 */       center.y = (dy * 0.5F + bmin[1]);
/* 608 */       center.z = (dz * 0.5F + bmin[2]);
/*     */ 
/* 610 */       if ((dx < 1.0E-006F) || (dy < 1.0E-006F) || (dz < 1.0E-006F) || (svcount < 3))
/*     */       {
/* 612 */         float len = 3.4028235E+38F;
/*     */ 
/* 614 */         if ((dx > 1.0E-006F) && (dx < len)) len = dx;
/* 615 */         if ((dy > 1.0E-006F) && (dy < len)) len = dy;
/* 616 */         if ((dz > 1.0E-006F) && (dz < len)) len = dz;
/*     */ 
/* 618 */         if (len == 3.4028235E+38F) {
/* 619 */           dx = dy = dz = 0.01F;
/*     */         }
/*     */         else {
/* 622 */           if (dx < 1.0E-006F) dx = len * 0.05F;
/* 623 */           if (dy < 1.0E-006F) dy = len * 0.05F;
/* 624 */           if (dz < 1.0E-006F) dz = len * 0.05F;
/*     */         }
/*     */ 
/* 627 */         float x1 = center.x - dx;
/* 628 */         float x2 = center.x + dx;
/*     */ 
/* 630 */         float y1 = center.y - dy;
/* 631 */         float y2 = center.y + dy;
/*     */ 
/* 633 */         float z1 = center.z - dz;
/* 634 */         float z2 = center.z + dz;
/*     */ 
/* 636 */         addPoint(vcount, vertices, x1, y1, z1);
/* 637 */         addPoint(vcount, vertices, x2, y1, z1);
/* 638 */         addPoint(vcount, vertices, x2, y2, z1);
/* 639 */         addPoint(vcount, vertices, x1, y2, z1);
/* 640 */         addPoint(vcount, vertices, x1, y1, z2);
/* 641 */         addPoint(vcount, vertices, x2, y1, z2);
/* 642 */         addPoint(vcount, vertices, x2, y2, z2);
/* 643 */         addPoint(vcount, vertices, x1, y2, z2);
/*     */ 
/* 645 */         return true;
/*     */       }
/*     */ 
/* 648 */       if (scale != null) {
/* 649 */         scale.x = dx;
/* 650 */         scale.y = dy;
/* 651 */         scale.z = dz;
/*     */ 
/* 653 */         recip[0] = (1.0F / dx);
/* 654 */         recip[1] = (1.0F / dy);
/* 655 */         recip[2] = (1.0F / dz);
/*     */ 
/* 657 */         center.x *= recip[0];
/* 658 */         center.y *= recip[1];
/* 659 */         center.z *= recip[2];
/*     */       }
/*     */ 
/* 663 */       vtx_ptr = svertices;
/* 664 */       vtx_idx = 0;
/*     */ 
/* 666 */       for (int i = 0; i < svcount; i++) {
/* 667 */         Vector3f p = (Vector3f)vtx_ptr.getQuick(vtx_idx);
/* 668 */         vtx_idx++;
/*     */ 
/* 670 */         float px = p.x;
/* 671 */         float py = p.y;
/* 672 */         float pz = p.z;
/*     */ 
/* 674 */         if (scale != null) {
/* 675 */           px *= recip[0];
/* 676 */           py *= recip[1];
/* 677 */           pz *= recip[2];
/*     */         }
/*     */ 
/* 684 */         for (int j = 0; j < vcount[0]; j++)
/*     */         {
/* 686 */           Vector3f v = (Vector3f)vertices.getQuick(j);
/*     */ 
/* 688 */           float x = v.x;
/* 689 */           float y = v.y;
/* 690 */           float z = v.z;
/*     */ 
/* 692 */           dx = Math.abs(x - px);
/* 693 */           dy = Math.abs(y - py);
/* 694 */           dz = Math.abs(z - pz);
/*     */ 
/* 696 */           if ((dx < normalepsilon) && (dy < normalepsilon) && (dz < normalepsilon))
/*     */           {
/* 701 */             float dist1 = getDist(px, py, pz, center);
/* 702 */             float dist2 = getDist(v.x, v.y, v.z, center);
/*     */ 
/* 704 */             if (dist1 <= dist2) break;
/* 705 */             v.x = px;
/* 706 */             v.y = py;
/* 707 */             v.z = pz; break;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 714 */         if (j == vcount[0]) {
/* 715 */           Vector3f dest = (Vector3f)vertices.getQuick(vcount[0]);
/* 716 */           dest.x = px;
/* 717 */           dest.y = py;
/* 718 */           dest.z = pz;
/* 719 */           vcount[0] += 1;
/*     */         }
/*     */ 
/* 722 */         this.vertexIndexMapping.add(j);
/*     */       }
/*     */ 
/* 729 */       bmin = new float[] { 3.4028235E+38F, 3.4028235E+38F, 3.4028235E+38F };
/* 730 */       bmax = new float[] { -3.402824E+038F, -3.402824E+038F, -3.402824E+038F };
/*     */ 
/* 732 */       for (int i = 0; i < vcount[0]; i++) {
/* 733 */         Vector3f p = (Vector3f)vertices.getQuick(i);
/* 734 */         for (int j = 0; j < 3; j++) {
/* 735 */           if (VectorUtil.getCoord(p, j) < bmin[j]) {
/* 736 */             bmin[j] = VectorUtil.getCoord(p, j);
/*     */           }
/* 738 */           if (VectorUtil.getCoord(p, j) > bmax[j]) {
/* 739 */             bmax[j] = VectorUtil.getCoord(p, j);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 744 */       dx = bmax[0] - bmin[0];
/* 745 */       dy = bmax[1] - bmin[1];
/* 746 */       dz = bmax[2] - bmin[2];
/*     */ 
/* 748 */       if ((dx < 1.0E-006F) || (dy < 1.0E-006F) || (dz < 1.0E-006F) || (vcount[0] < 3)) {
/* 749 */         float cx = dx * 0.5F + bmin[0];
/* 750 */         float cy = dy * 0.5F + bmin[1];
/* 751 */         float cz = dz * 0.5F + bmin[2];
/*     */ 
/* 753 */         float len = 3.4028235E+38F;
/*     */ 
/* 755 */         if ((dx >= 1.0E-006F) && (dx < len)) len = dx;
/* 756 */         if ((dy >= 1.0E-006F) && (dy < len)) len = dy;
/* 757 */         if ((dz >= 1.0E-006F) && (dz < len)) len = dz;
/*     */ 
/* 759 */         if (len == 3.4028235E+38F) {
/* 760 */           dx = dy = dz = 0.01F;
/*     */         }
/*     */         else {
/* 763 */           if (dx < 1.0E-006F) dx = len * 0.05F;
/* 764 */           if (dy < 1.0E-006F) dy = len * 0.05F;
/* 765 */           if (dz < 1.0E-006F) dz = len * 0.05F;
/*     */         }
/*     */ 
/* 768 */         float x1 = cx - dx;
/* 769 */         float x2 = cx + dx;
/*     */ 
/* 771 */         float y1 = cy - dy;
/* 772 */         float y2 = cy + dy;
/*     */ 
/* 774 */         float z1 = cz - dz;
/* 775 */         float z2 = cz + dz;
/*     */ 
/* 777 */         vcount[0] = 0;
/*     */ 
/* 779 */         addPoint(vcount, vertices, x1, y1, z1);
/* 780 */         addPoint(vcount, vertices, x2, y1, z1);
/* 781 */         addPoint(vcount, vertices, x2, y2, z1);
/* 782 */         addPoint(vcount, vertices, x1, y2, z1);
/* 783 */         addPoint(vcount, vertices, x1, y1, z2);
/* 784 */         addPoint(vcount, vertices, x2, y1, z2);
/* 785 */         addPoint(vcount, vertices, x2, y2, z2);
/* 786 */         addPoint(vcount, vertices, x1, y2, z2);
/*     */ 
/* 788 */         return true;
/*     */       }
/*     */ 
/* 792 */       return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static boolean hasvert(Int3 t, int v)
/*     */   {
/* 798 */     return (t.getCoord(0) == v) || (t.getCoord(1) == v) || (t.getCoord(2) == v);
/*     */   }
/*     */ 
/*     */   private static Vector3f orth(Vector3f arg0, Vector3f arg1) {
/* 802 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f a = localStack.get$javax$vecmath$Vector3f();
/* 803 */       a.set(0.0F, 0.0F, 1.0F);
/* 804 */       a.cross(v, a);
/*     */ 
/* 806 */       Vector3f b = localStack.get$javax$vecmath$Vector3f();
/* 807 */       b.set(0.0F, 1.0F, 0.0F);
/* 808 */       b.cross(v, b);
/*     */ 
/* 810 */       if (a.length() > b.length()) {
/* 811 */         out.normalize(a);
/* 812 */         return out;
/*     */       }
/*     */ 
/* 815 */       out.normalize(b);
/* 816 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static int maxdirfiltered(ObjectArrayList<Vector3f> p, int count, Vector3f dir, IntArrayList allow)
/*     */   {
/* 821 */     assert (count != 0);
/* 822 */     int m = -1;
/* 823 */     for (int i = 0; i < count; i++) {
/* 824 */       if ((allow.get(i) != 0) && (
/* 825 */         (m == -1) || (((Vector3f)p.getQuick(i)).dot(dir) > ((Vector3f)p.getQuick(m)).dot(dir)))) {
/* 826 */         m = i;
/*     */       }
/*     */     }
/*     */ 
/* 830 */     assert (m != -1);
/* 831 */     return m;
/*     */   }
/*     */ 
/*     */   private static int maxdirsterid(ObjectArrayList<Vector3f> arg0, int arg1, Vector3f arg2, IntArrayList arg3) {
/* 835 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 836 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 837 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/* 838 */       Vector3f u = localStack.get$javax$vecmath$Vector3f();
/* 839 */       Vector3f v = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 841 */       int m = -1;
/* 842 */       while (m == -1) {
/* 843 */         m = maxdirfiltered(p, count, dir, allow);
/* 844 */         if (allow.get(m) == 3) {
/* 845 */           return m;
/*     */         }
/* 847 */         orth(dir, u);
/* 848 */         v.cross(u, dir);
/* 849 */         int ma = -1;
/* 850 */         for (float x = 0.0F; x <= 360.0F; x += 45.0F) {
/* 851 */           float s = (float)Math.sin(0.01745329F * x);
/* 852 */           float c = (float)Math.cos(0.01745329F * x);
/*     */ 
/* 854 */           tmp1.scale(s, u);
/* 855 */           tmp2.scale(c, v);
/* 856 */           tmp.add(tmp1, tmp2);
/* 857 */           tmp.scale(0.025F);
/* 858 */           tmp.add(dir);
/* 859 */           int mb = maxdirfiltered(p, count, tmp, allow);
/* 860 */           if ((ma == m) && (mb == m)) {
/* 861 */             allow.set(m, 3);
/* 862 */             return m;
/*     */           }
/* 864 */           if ((ma != -1) && (ma != mb)) {
/* 865 */             int mc = ma;
/* 866 */             for (float xx = x - 40.0F; xx <= x; xx += 5.0F) {
/* 867 */               s = (float)Math.sin(0.01745329F * xx);
/* 868 */               c = (float)Math.cos(0.01745329F * xx);
/*     */ 
/* 870 */               tmp1.scale(s, u);
/* 871 */               tmp2.scale(c, v);
/* 872 */               tmp.add(tmp1, tmp2);
/* 873 */               tmp.scale(0.025F);
/* 874 */               tmp.add(dir);
/*     */ 
/* 876 */               int md = maxdirfiltered(p, count, tmp, allow);
/* 877 */               if ((mc == m) && (md == m)) {
/* 878 */                 allow.set(m, 3);
/* 879 */                 return m;
/*     */               }
/* 881 */               mc = md;
/*     */             }
/*     */           }
/* 884 */           ma = mb;
/*     */         }
/* 886 */         allow.set(m, 0);
/* 887 */         m = -1;
/*     */       }
/* 889 */       if (!$assertionsDisabled) throw new AssertionError();
/* 890 */       return m; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static Vector3f triNormal(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3) {
/* 894 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 895 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 899 */       tmp1.sub(v1, v0);
/* 900 */       tmp2.sub(v2, v1);
/* 901 */       Vector3f cp = localStack.get$javax$vecmath$Vector3f();
/* 902 */       cp.cross(tmp1, tmp2);
/* 903 */       float m = cp.length();
/* 904 */       if (m == 0.0F) {
/* 905 */         out.set(1.0F, 0.0F, 0.0F);
/* 906 */         return out;
/*     */       }
/* 908 */       out.scale(1.0F / m, cp);
/* 909 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static boolean above(ObjectArrayList<Vector3f> arg0, Int3 arg1, Vector3f arg2, float arg3) {
/* 913 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f n = triNormal((Vector3f)vertices.getQuick(t.getCoord(0)), (Vector3f)vertices.getQuick(t.getCoord(1)), (Vector3f)vertices.getQuick(t.getCoord(2)), localStack.get$javax$vecmath$Vector3f());
/* 914 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 915 */       tmp.sub(p, (Tuple3f)vertices.getQuick(t.getCoord(0)));
/* 916 */       return n.dot(tmp) > epsilon; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static void releaseHull(PHullResult result) {
/* 920 */     if (result.indices.size() != 0) {
/* 921 */       result.indices.clear();
/*     */     }
/*     */ 
/* 924 */     result.vcount = 0;
/* 925 */     result.indexCount = 0;
/* 926 */     result.vertices = null;
/*     */   }
/*     */ 
/*     */   private static void addPoint(int[] vcount, ObjectArrayList<Vector3f> p, float x, float y, float z)
/*     */   {
/* 931 */     Vector3f dest = (Vector3f)p.getQuick(vcount[0]);
/* 932 */     dest.x = x;
/* 933 */     dest.y = y;
/* 934 */     dest.z = z;
/* 935 */     vcount[0] += 1;
/*     */   }
/*     */ 
/*     */   private static float getDist(float px, float py, float pz, Vector3f p2) {
/* 939 */     float dx = px - p2.x;
/* 940 */     float dy = py - p2.y;
/* 941 */     float dz = pz - p2.z;
/*     */ 
/* 943 */     return dx * dx + dy * dy + dz * dz;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullLibrary
 * JD-Core Version:    0.6.2
 */