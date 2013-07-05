/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ class GeometryOperations
/*     */ {
/*     */   public static final float PLANEDIREPSILON = 1.0E-007F;
/*     */   public static final float PARALELENORMALS = 1.0E-006F;
/*     */ 
/*     */   public static final float CLAMP(float number, float minval, float maxval)
/*     */   {
/*  46 */     return number > maxval ? maxval : number < minval ? minval : number;
/*     */   }
/*     */ 
/*     */   public static void edge_plane(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector4f arg3)
/*     */   {
/*  53 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f planenormal = localStack.get$javax$vecmath$Vector3f();
/*  54 */       planenormal.sub(e2, e1);
/*  55 */       planenormal.cross(planenormal, normal);
/*  56 */       planenormal.normalize();
/*     */ 
/*  58 */       plane.set(planenormal);
/*  59 */       plane.w = e2.dot(planenormal);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void closest_point_on_segment(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  66 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f n = localStack.get$javax$vecmath$Vector3f();
/*  67 */       n.sub(e2, e1);
/*  68 */       cp.sub(v, e1);
/*  69 */       float _scalar = cp.dot(n) / n.dot(n);
/*  70 */       if (_scalar < 0.0F) {
/*  71 */         cp = e1;
/*     */       }
/*  73 */       else if (_scalar > 1.0F) {
/*  74 */         cp = e2;
/*     */       }
/*     */       else
/*  77 */         cp.scaleAdd(_scalar, n, e1);
/*     */       return; } finally {
/*  79 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static int line_plane_collision(Vector4f plane, Vector3f vDir, Vector3f vPoint, Vector3f pout, float[] tparam, float tmin, float tmax)
/*     */   {
/*  87 */     float _dotdir = VectorUtil.dot3(vDir, plane);
/*     */ 
/*  89 */     if (Math.abs(_dotdir) < 1.0E-007F) {
/*  90 */       tparam[0] = tmax;
/*  91 */       return 0;
/*     */     }
/*     */ 
/*  94 */     float _dis = ClipPolygon.distance_point_plane(plane, vPoint);
/*  95 */     int returnvalue = _dis < 0.0F ? 2 : 1;
/*  96 */     tparam[0] = (-_dis / _dotdir);
/*     */ 
/*  98 */     if (tparam[0] < tmin) {
/*  99 */       returnvalue = 0;
/* 100 */       tparam[0] = tmin;
/*     */     }
/* 102 */     else if (tparam[0] > tmax) {
/* 103 */       returnvalue = 0;
/* 104 */       tparam[0] = tmax;
/*     */     }
/* 106 */     pout.scaleAdd(tparam[0], vDir, vPoint);
/* 107 */     return returnvalue;
/*     */   }
/*     */ 
/*     */   public static void segment_collision(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, Vector3f arg4, Vector3f arg5)
/*     */   {
/* 114 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Vector4f(); Vector3f AD = localStack.get$javax$vecmath$Vector3f();
/* 115 */       AD.sub(vA2, vA1);
/*     */ 
/* 117 */       Vector3f BD = localStack.get$javax$vecmath$Vector3f();
/* 118 */       BD.sub(vB2, vB1);
/*     */ 
/* 120 */       Vector3f N = localStack.get$javax$vecmath$Vector3f();
/* 121 */       N.cross(AD, BD);
/* 122 */       float[] tp = { N.lengthSquared() };
/*     */ 
/* 124 */       Vector4f _M = localStack.get$javax$vecmath$Vector4f();
/*     */ 
/* 126 */       if (tp[0] < 1.192093E-007F)
/*     */       {
/* 129 */         boolean invert_b_order = false;
/* 130 */         _M.x = vB1.dot(AD);
/* 131 */         _M.y = vB2.dot(AD);
/*     */ 
/* 133 */         if (_M.x > _M.y) {
/* 134 */           invert_b_order = true;
/*     */ 
/* 136 */           _M.x += _M.y;
/* 137 */           _M.y = (_M.x - _M.y);
/* 138 */           _M.x -= _M.y;
/*     */         }
/* 140 */         _M.z = vA1.dot(AD);
/* 141 */         _M.w = vA2.dot(AD);
/*     */ 
/* 143 */         N.x = ((_M.x + _M.y) * 0.5F);
/* 144 */         N.y = ((_M.z + _M.w) * 0.5F);
/*     */ 
/* 146 */         if (N.x < N.y) {
/* 147 */           if (_M.y < _M.z) {
/* 148 */             vPointB = invert_b_order ? vB1 : vB2;
/* 149 */             vPointA = vA1;
/*     */           }
/* 151 */           else if (_M.y < _M.w) {
/* 152 */             vPointB = invert_b_order ? vB1 : vB2;
/* 153 */             closest_point_on_segment(vPointA, vPointB, vA1, vA2);
/*     */           }
/*     */           else {
/* 156 */             vPointA = vA2;
/* 157 */             closest_point_on_segment(vPointB, vPointA, vB1, vB2);
/*     */           }
/*     */ 
/*     */         }
/* 161 */         else if (_M.w < _M.x) {
/* 162 */           vPointB = invert_b_order ? vB2 : vB1;
/* 163 */           vPointA = vA2;
/*     */         }
/* 165 */         else if (_M.w < _M.y) {
/* 166 */           vPointA = vA2;
/* 167 */           closest_point_on_segment(vPointB, vPointA, vB1, vB2);
/*     */         }
/*     */         else {
/* 170 */           vPointB = invert_b_order ? vB1 : vB2;
/* 171 */           closest_point_on_segment(vPointA, vPointB, vA1, vA2);
/*     */         }
/*     */ 
/* 174 */         return;
/*     */       }
/*     */ 
/* 177 */       N.cross(N, BD);
/* 178 */       _M.set(N.x, N.y, N.z, vB1.dot(N));
/*     */ 
/* 181 */       line_plane_collision(_M, AD, vA1, vPointA, tp, 0.0F, 1.0F);
/*     */ 
/* 184 */       vPointB.sub(vPointA, vB1);
/* 185 */       tp[0] = vPointB.dot(BD);
/* 186 */       tp[0] /= BD.dot(BD);
/* 187 */       tp[0] = CLAMP(tp[0], 0.0F, 1.0F);
/*     */ 
/* 189 */       vPointB.scaleAdd(tp[0], BD, vB1);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 190 */       .Stack tmp549_547 = localStack; tmp549_547.pop$javax$vecmath$Vector3f(); tmp549_547.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GeometryOperations
 * JD-Core Version:    0.6.2
 */