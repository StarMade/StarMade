/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class DbvtAabbMm
/*     */ {
/*  40 */   private final Vector3f mi = new Vector3f();
/*  41 */   private final Vector3f mx = new Vector3f();
/*     */ 
/*     */   public DbvtAabbMm() {
/*     */   }
/*     */ 
/*     */   public DbvtAabbMm(DbvtAabbMm o) {
/*  47 */     set(o);
/*     */   }
/*     */ 
/*     */   public void set(DbvtAabbMm o) {
/*  51 */     this.mi.set(o.mi);
/*  52 */     this.mx.set(o.mx);
/*     */   }
/*     */ 
/*     */   public static void swap(DbvtAabbMm arg0, DbvtAabbMm arg1) {
/*  56 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  58 */       tmp.set(p1.mi);
/*  59 */       p1.mi.set(p2.mi);
/*  60 */       p2.mi.set(tmp);
/*     */ 
/*  62 */       tmp.set(p1.mx);
/*  63 */       p1.mx.set(p2.mx);
/*  64 */       p2.mx.set(tmp);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public Vector3f Center(Vector3f out) {
/*  68 */     out.add(this.mi, this.mx);
/*  69 */     out.scale(0.5F);
/*  70 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f Lengths(Vector3f out) {
/*  74 */     out.sub(this.mx, this.mi);
/*  75 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f Extents(Vector3f out) {
/*  79 */     out.sub(this.mx, this.mi);
/*  80 */     out.scale(0.5F);
/*  81 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f Mins() {
/*  85 */     return this.mi;
/*     */   }
/*     */ 
/*     */   public Vector3f Maxs() {
/*  89 */     return this.mx;
/*     */   }
/*     */ 
/*     */   public static DbvtAabbMm FromCE(Vector3f c, Vector3f e, DbvtAabbMm out) {
/*  93 */     DbvtAabbMm box = out;
/*  94 */     box.mi.sub(c, e);
/*  95 */     box.mx.add(c, e);
/*  96 */     return box;
/*     */   }
/*     */ 
/*     */   public static DbvtAabbMm FromCR(Vector3f arg0, float arg1, DbvtAabbMm arg2) {
/* 100 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 101 */       tmp.set(r, r, r);
/* 102 */       return FromCE(c, tmp, out); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static DbvtAabbMm FromMM(Vector3f mi, Vector3f mx, DbvtAabbMm out) {
/* 106 */     DbvtAabbMm box = out;
/* 107 */     box.mi.set(mi);
/* 108 */     box.mx.set(mx);
/* 109 */     return box;
/*     */   }
/*     */ 
/*     */   public void Expand(Vector3f e)
/*     */   {
/* 116 */     this.mi.sub(e);
/* 117 */     this.mx.add(e);
/*     */   }
/*     */ 
/*     */   public void SignedExpand(Vector3f e) {
/* 121 */     if (e.x > 0.0F) {
/* 122 */       this.mx.x += e.x;
/*     */     }
/*     */     else {
/* 125 */       this.mi.x += e.x;
/*     */     }
/*     */ 
/* 128 */     if (e.y > 0.0F) {
/* 129 */       this.mx.y += e.y;
/*     */     }
/*     */     else {
/* 132 */       this.mi.y += e.y;
/*     */     }
/*     */ 
/* 135 */     if (e.z > 0.0F) {
/* 136 */       this.mx.z += e.z;
/*     */     }
/*     */     else
/* 139 */       this.mi.z += e.z;
/*     */   }
/*     */ 
/*     */   public boolean Contain(DbvtAabbMm a)
/*     */   {
/* 144 */     return (this.mi.x <= a.mi.x) && (this.mi.y <= a.mi.y) && (this.mi.z <= a.mi.z) && (this.mx.x >= a.mx.x) && (this.mx.y >= a.mx.y) && (this.mx.z >= a.mx.z);
/*     */   }
/*     */ 
/*     */   public int Classify(Vector3f arg1, float arg2, int arg3)
/*     */   {
/* 153 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f pi = localStack.get$javax$vecmath$Vector3f();
/* 154 */       Vector3f px = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 156 */       switch (s) {
/*     */       case 0:
/* 158 */         px.set(this.mi.x, this.mi.y, this.mi.z);
/* 159 */         pi.set(this.mx.x, this.mx.y, this.mx.z);
/* 160 */         break;
/*     */       case 1:
/* 162 */         px.set(this.mx.x, this.mi.y, this.mi.z);
/* 163 */         pi.set(this.mi.x, this.mx.y, this.mx.z);
/* 164 */         break;
/*     */       case 2:
/* 166 */         px.set(this.mi.x, this.mx.y, this.mi.z);
/* 167 */         pi.set(this.mx.x, this.mi.y, this.mx.z);
/* 168 */         break;
/*     */       case 3:
/* 170 */         px.set(this.mx.x, this.mx.y, this.mi.z);
/* 171 */         pi.set(this.mi.x, this.mi.y, this.mx.z);
/* 172 */         break;
/*     */       case 4:
/* 174 */         px.set(this.mi.x, this.mi.y, this.mx.z);
/* 175 */         pi.set(this.mx.x, this.mx.y, this.mi.z);
/* 176 */         break;
/*     */       case 5:
/* 178 */         px.set(this.mx.x, this.mi.y, this.mx.z);
/* 179 */         pi.set(this.mi.x, this.mx.y, this.mi.z);
/* 180 */         break;
/*     */       case 6:
/* 182 */         px.set(this.mi.x, this.mx.y, this.mx.z);
/* 183 */         pi.set(this.mx.x, this.mi.y, this.mi.z);
/* 184 */         break;
/*     */       case 7:
/* 186 */         px.set(this.mx.x, this.mx.y, this.mx.z);
/* 187 */         pi.set(this.mi.x, this.mi.y, this.mi.z);
/*     */       }
/*     */ 
/* 191 */       if (n.dot(px) + o < 0.0F) {
/* 192 */         return -1;
/*     */       }
/* 194 */       if (n.dot(pi) + o >= 0.0F) {
/* 195 */         return 1;
/*     */       }
/* 197 */       return 0; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float ProjectMinimum(Vector3f arg1, int arg2) {
/* 201 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f[] b = { this.mx, this.mi };
/* 202 */       Vector3f p = localStack.get$javax$vecmath$Vector3f();
/* 203 */       p.set(b[(signs >> 0 & 0x1)].x, b[(signs >> 1 & 0x1)].y, b[(signs >> 2 & 0x1)].z);
/*     */ 
/* 206 */       return p.dot(v); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static boolean Intersect(DbvtAabbMm a, DbvtAabbMm b) {
/* 210 */     return (a.mi.x <= b.mx.x) && (a.mx.x >= b.mi.x) && (a.mi.y <= b.mx.y) && (a.mx.y >= b.mi.y) && (a.mi.z <= b.mx.z) && (a.mx.z >= b.mi.z);
/*     */   }
/*     */ 
/*     */   public static boolean Intersect(DbvtAabbMm arg0, DbvtAabbMm arg1, Transform arg2)
/*     */   {
/* 219 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f d0 = localStack.get$javax$vecmath$Vector3f();
/* 220 */       Vector3f d1 = localStack.get$javax$vecmath$Vector3f();
/* 221 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 224 */       b.Center(d0);
/* 225 */       xform.transform(d0);
/* 226 */       d0.sub(a.Center(tmp));
/*     */ 
/* 228 */       MatrixUtil.transposeTransform(d1, d0, xform.basis);
/*     */ 
/* 230 */       float[] s0 = { 0.0F, 0.0F };
/* 231 */       float[] s1 = new float[2];
/* 232 */       s1[0] = xform.origin.dot(d0);
/* 233 */       s1[1] = s1[0];
/*     */ 
/* 235 */       a.AddSpan(d0, s0, 0, s0, 1);
/* 236 */       b.AddSpan(d1, s1, 0, s1, 1);
/* 237 */       if (s0[0] > s1[1]) {
/* 238 */         return false;
/*     */       }
/* 240 */       if (s0[1] < s1[0]) {
/* 241 */         return false;
/*     */       }
/* 243 */       return true; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static boolean Intersect(DbvtAabbMm a, Vector3f b) {
/* 247 */     return (b.x >= a.mi.x) && (b.y >= a.mi.y) && (b.z >= a.mi.z) && (b.x <= a.mx.x) && (b.y <= a.mx.y) && (b.z <= a.mx.z);
/*     */   }
/*     */ 
/*     */   public static boolean Intersect(DbvtAabbMm a, Vector3f org, Vector3f invdir, int[] signs)
/*     */   {
/* 256 */     Vector3f[] bounds = { a.mi, a.mx };
/* 257 */     float txmin = (bounds[signs[0]].x - org.x) * invdir.x;
/* 258 */     float txmax = (bounds[(1 - signs[0])].x - org.x) * invdir.x;
/* 259 */     float tymin = (bounds[signs[1]].y - org.y) * invdir.y;
/* 260 */     float tymax = (bounds[(1 - signs[1])].y - org.y) * invdir.y;
/* 261 */     if ((txmin > tymax) || (tymin > txmax)) {
/* 262 */       return false;
/*     */     }
/*     */ 
/* 265 */     if (tymin > txmin) {
/* 266 */       txmin = tymin;
/*     */     }
/* 268 */     if (tymax < txmax) {
/* 269 */       txmax = tymax;
/*     */     }
/* 271 */     float tzmin = (bounds[signs[2]].z - org.z) * invdir.z;
/* 272 */     float tzmax = (bounds[(1 - signs[2])].z - org.z) * invdir.z;
/* 273 */     if ((txmin > tzmax) || (tzmin > txmax)) {
/* 274 */       return false;
/*     */     }
/*     */ 
/* 277 */     if (tzmin > txmin) {
/* 278 */       txmin = tzmin;
/*     */     }
/* 280 */     if (tzmax < txmax) {
/* 281 */       txmax = tzmax;
/*     */     }
/* 283 */     return txmax > 0.0F;
/*     */   }
/*     */ 
/*     */   public static float Proximity(DbvtAabbMm arg0, DbvtAabbMm arg1) {
/* 287 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f d = localStack.get$javax$vecmath$Vector3f();
/* 288 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 290 */       d.add(a.mi, a.mx);
/* 291 */       tmp.add(b.mi, b.mx);
/* 292 */       d.sub(tmp);
/* 293 */       return Math.abs(d.x) + Math.abs(d.y) + Math.abs(d.z); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void Merge(DbvtAabbMm a, DbvtAabbMm b, DbvtAabbMm r) {
/* 297 */     for (int i = 0; i < 3; i++) {
/* 298 */       if (VectorUtil.getCoord(a.mi, i) < VectorUtil.getCoord(b.mi, i)) {
/* 299 */         VectorUtil.setCoord(r.mi, i, VectorUtil.getCoord(a.mi, i));
/*     */       }
/*     */       else {
/* 302 */         VectorUtil.setCoord(r.mi, i, VectorUtil.getCoord(b.mi, i));
/*     */       }
/*     */ 
/* 305 */       if (VectorUtil.getCoord(a.mx, i) > VectorUtil.getCoord(b.mx, i)) {
/* 306 */         VectorUtil.setCoord(r.mx, i, VectorUtil.getCoord(a.mx, i));
/*     */       }
/*     */       else
/* 309 */         VectorUtil.setCoord(r.mx, i, VectorUtil.getCoord(b.mx, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean NotEqual(DbvtAabbMm a, DbvtAabbMm b)
/*     */   {
/* 315 */     return (a.mi.x != b.mi.x) || (a.mi.y != b.mi.y) || (a.mi.z != b.mi.z) || (a.mx.x != b.mx.x) || (a.mx.y != b.mx.y) || (a.mx.z != b.mx.z);
/*     */   }
/*     */ 
/*     */   private void AddSpan(Vector3f d, float[] smi, int smi_idx, float[] smx, int smx_idx)
/*     */   {
/* 324 */     for (int i = 0; i < 3; i++)
/* 325 */       if (VectorUtil.getCoord(d, i) < 0.0F) {
/* 326 */         smi[smi_idx] += VectorUtil.getCoord(this.mx, i) * VectorUtil.getCoord(d, i);
/* 327 */         smx[smx_idx] += VectorUtil.getCoord(this.mi, i) * VectorUtil.getCoord(d, i);
/*     */       }
/*     */       else {
/* 330 */         smi[smi_idx] += VectorUtil.getCoord(this.mi, i) * VectorUtil.getCoord(d, i);
/* 331 */         smx[smx_idx] += VectorUtil.getCoord(this.mx, i) * VectorUtil.getCoord(d, i);
/*     */       }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtAabbMm
 * JD-Core Version:    0.6.2
 */