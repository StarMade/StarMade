/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class AabbUtil2
/*     */ {
/*     */   public static void aabbExpand(Vector3f aabbMin, Vector3f aabbMax, Vector3f expansionMin, Vector3f expansionMax)
/*     */   {
/*  38 */     aabbMin.add(expansionMin);
/*  39 */     aabbMax.add(expansionMax);
/*     */   }
/*     */ 
/*     */   public static int outcode(Vector3f p, Vector3f halfExtent) {
/*  43 */     return (p.x < -halfExtent.x ? 1 : 0) | (p.x > halfExtent.x ? 8 : 0) | (p.y < -halfExtent.y ? 2 : 0) | (p.y > halfExtent.y ? 16 : 0) | (p.z < -halfExtent.z ? 4 : 0) | (p.z > halfExtent.z ? 32 : 0);
/*     */   }
/*     */ 
/*     */   public static boolean rayAabb(Vector3f arg0, Vector3f arg1, Vector3f arg2, Vector3f arg3, float[] arg4, Vector3f arg5)
/*     */   {
/*  52 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f aabbHalfExtent = localStack.get$javax$vecmath$Vector3f();
/*  53 */       Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
/*  54 */       Vector3f source = localStack.get$javax$vecmath$Vector3f();
/*  55 */       Vector3f target = localStack.get$javax$vecmath$Vector3f();
/*  56 */       Vector3f r = localStack.get$javax$vecmath$Vector3f();
/*  57 */       Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  59 */       aabbHalfExtent.sub(aabbMax, aabbMin);
/*  60 */       aabbHalfExtent.scale(0.5F);
/*     */ 
/*  62 */       aabbCenter.add(aabbMax, aabbMin);
/*  63 */       aabbCenter.scale(0.5F);
/*     */ 
/*  65 */       source.sub(rayFrom, aabbCenter);
/*  66 */       target.sub(rayTo, aabbCenter);
/*     */ 
/*  68 */       int sourceOutcode = outcode(source, aabbHalfExtent);
/*  69 */       int targetOutcode = outcode(target, aabbHalfExtent);
/*  70 */       if ((sourceOutcode & targetOutcode) == 0) {
/*  71 */         float lambda_enter = 0.0F;
/*  72 */         float lambda_exit = param[0];
/*  73 */         r.sub(target, source);
/*     */ 
/*  75 */         float normSign = 1.0F;
/*  76 */         hitNormal.set(0.0F, 0.0F, 0.0F);
/*  77 */         int bit = 1;
/*     */ 
/*  79 */         for (int j = 0; j < 2; j++) {
/*  80 */           for (int i = 0; i != 3; i++) {
/*  81 */             if ((sourceOutcode & bit) != 0) {
/*  82 */               float lambda = (-VectorUtil.getCoord(source, i) - VectorUtil.getCoord(aabbHalfExtent, i) * normSign) / VectorUtil.getCoord(r, i);
/*  83 */               if (lambda_enter <= lambda) {
/*  84 */                 lambda_enter = lambda;
/*  85 */                 hitNormal.set(0.0F, 0.0F, 0.0F);
/*  86 */                 VectorUtil.setCoord(hitNormal, i, normSign);
/*     */               }
/*     */             }
/*  89 */             else if ((targetOutcode & bit) != 0) {
/*  90 */               float lambda = (-VectorUtil.getCoord(source, i) - VectorUtil.getCoord(aabbHalfExtent, i) * normSign) / VectorUtil.getCoord(r, i);
/*     */ 
/*  92 */               lambda_exit = Math.min(lambda_exit, lambda);
/*     */             }
/*  94 */             bit <<= 1;
/*     */           }
/*  96 */           normSign = -1.0F;
/*     */         }
/*  98 */         if (lambda_enter <= lambda_exit) {
/*  99 */           param[0] = lambda_enter;
/* 100 */           normal.set(hitNormal);
/* 101 */           return true;
/*     */         }
/*     */       }
/* 104 */       return false; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static boolean testAabbAgainstAabb2(Vector3f aabbMin1, Vector3f aabbMax1, Vector3f aabbMin2, Vector3f aabbMax2)
/*     */   {
/* 111 */     boolean overlap = true;
/* 112 */     overlap = (aabbMin1.x > aabbMax2.x) || (aabbMax1.x < aabbMin2.x) ? false : overlap;
/* 113 */     overlap = (aabbMin1.z > aabbMax2.z) || (aabbMax1.z < aabbMin2.z) ? false : overlap;
/* 114 */     overlap = (aabbMin1.y > aabbMax2.y) || (aabbMax1.y < aabbMin2.y) ? false : overlap;
/* 115 */     return overlap;
/*     */   }
/*     */ 
/*     */   public static boolean testTriangleAgainstAabb2(Vector3f[] vertices, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 122 */     Vector3f p1 = vertices[0];
/* 123 */     Vector3f p2 = vertices[1];
/* 124 */     Vector3f p3 = vertices[2];
/*     */ 
/* 126 */     if (Math.min(Math.min(p1.x, p2.x), p3.x) > aabbMax.x) return false;
/* 127 */     if (Math.max(Math.max(p1.x, p2.x), p3.x) < aabbMin.x) return false;
/*     */ 
/* 129 */     if (Math.min(Math.min(p1.z, p2.z), p3.z) > aabbMax.z) return false;
/* 130 */     if (Math.max(Math.max(p1.z, p2.z), p3.z) < aabbMin.z) return false;
/*     */ 
/* 132 */     if (Math.min(Math.min(p1.y, p2.y), p3.y) > aabbMax.y) return false;
/* 133 */     if (Math.max(Math.max(p1.y, p2.y), p3.y) < aabbMin.y) return false;
/*     */ 
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   public static void transformAabb(Vector3f arg0, float arg1, Transform arg2, Vector3f arg3, Vector3f arg4) {
/* 139 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f halfExtentsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 140 */       halfExtents.x += margin;
/* 141 */       halfExtents.y += margin;
/* 142 */       halfExtents.z += margin;
/*     */ 
/* 144 */       Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(t.basis);
/* 145 */       MatrixUtil.absolute(abs_b);
/*     */ 
/* 147 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 149 */       Vector3f center = localStack.get$javax$vecmath$Vector3f(t.origin);
/* 150 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 151 */       abs_b.getRow(0, tmp);
/* 152 */       extent.x = tmp.dot(halfExtentsWithMargin);
/* 153 */       abs_b.getRow(1, tmp);
/* 154 */       extent.y = tmp.dot(halfExtentsWithMargin);
/* 155 */       abs_b.getRow(2, tmp);
/* 156 */       extent.z = tmp.dot(halfExtentsWithMargin);
/*     */ 
/* 158 */       aabbMinOut.sub(center, extent);
/* 159 */       aabbMaxOut.add(center, extent);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 160 */       .Stack tmp186_184 = localStack; tmp186_184.pop$javax$vecmath$Vector3f(); tmp186_184.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */   public static void transformAabb(Vector3f arg0, Vector3f arg1, float arg2, Transform arg3, Vector3f arg4, Vector3f arg5) {
/* 163 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); assert (localAabbMin.x <= localAabbMax.x);
/* 164 */       assert (localAabbMin.y <= localAabbMax.y);
/* 165 */       assert (localAabbMin.z <= localAabbMax.z);
/*     */ 
/* 167 */       Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/* 168 */       localHalfExtents.sub(localAabbMax, localAabbMin);
/* 169 */       localHalfExtents.scale(0.5F);
/*     */ 
/* 171 */       localHalfExtents.x += margin;
/* 172 */       localHalfExtents.y += margin;
/* 173 */       localHalfExtents.z += margin;
/*     */ 
/* 175 */       Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 176 */       localCenter.add(localAabbMax, localAabbMin);
/* 177 */       localCenter.scale(0.5F);
/*     */ 
/* 179 */       Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 180 */       MatrixUtil.absolute(abs_b);
/*     */ 
/* 182 */       Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 183 */       trans.transform(center);
/*     */ 
/* 185 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 186 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 188 */       abs_b.getRow(0, tmp);
/* 189 */       extent.x = tmp.dot(localHalfExtents);
/* 190 */       abs_b.getRow(1, tmp);
/* 191 */       extent.y = tmp.dot(localHalfExtents);
/* 192 */       abs_b.getRow(2, tmp);
/* 193 */       extent.z = tmp.dot(localHalfExtents);
/*     */ 
/* 195 */       aabbMinOut.sub(center, extent);
/* 196 */       aabbMaxOut.add(center, extent);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 197 */       .Stack tmp304_302 = localStack; tmp304_302.pop$javax$vecmath$Vector3f(); tmp304_302.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.AabbUtil2
 * JD-Core Version:    0.6.2
 */