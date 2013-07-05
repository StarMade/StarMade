/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class TriangleRaycastCallback extends TriangleCallback
/*     */ {
/*  39 */   public final Vector3f from = new Vector3f();
/*  40 */   public final Vector3f to = new Vector3f();
/*     */   public float hitFraction;
/*     */ 
/*     */   public TriangleRaycastCallback(Vector3f from, Vector3f to)
/*     */   {
/*  45 */     this.from.set(from);
/*  46 */     this.to.set(to);
/*  47 */     this.hitFraction = 1.0F;
/*     */   }
/*     */ 
/*     */   public void processTriangle(Vector3f[] arg1, int arg2, int arg3) {
/*  51 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f vert0 = triangle[0];
/*  52 */       Vector3f vert1 = triangle[1];
/*  53 */       Vector3f vert2 = triangle[2];
/*     */ 
/*  55 */       Vector3f v10 = localStack.get$javax$vecmath$Vector3f();
/*  56 */       v10.sub(vert1, vert0);
/*     */ 
/*  58 */       Vector3f v20 = localStack.get$javax$vecmath$Vector3f();
/*  59 */       v20.sub(vert2, vert0);
/*     */ 
/*  61 */       Vector3f triangleNormal = localStack.get$javax$vecmath$Vector3f();
/*  62 */       triangleNormal.cross(v10, v20);
/*     */ 
/*  64 */       float dist = vert0.dot(triangleNormal);
/*  65 */       float dist_a = triangleNormal.dot(this.from);
/*  66 */       dist_a -= dist;
/*  67 */       float dist_b = triangleNormal.dot(this.to);
/*  68 */       dist_b -= dist;
/*     */ 
/*  70 */       if (dist_a * dist_b >= 0.0F) {
/*  71 */         return;
/*     */       }
/*     */ 
/*  74 */       float proj_length = dist_a - dist_b;
/*  75 */       float distance = dist_a / proj_length;
/*     */ 
/*  81 */       if (distance < this.hitFraction) {
/*  82 */         float edge_tolerance = triangleNormal.lengthSquared();
/*  83 */         edge_tolerance *= -1.0E-004F;
/*  84 */         Vector3f point = new Vector3f();
/*  85 */         VectorUtil.setInterpolate3(point, this.from, this.to, distance);
/*     */ 
/*  87 */         Vector3f v0p = localStack.get$javax$vecmath$Vector3f();
/*  88 */         v0p.sub(vert0, point);
/*  89 */         Vector3f v1p = localStack.get$javax$vecmath$Vector3f();
/*  90 */         v1p.sub(vert1, point);
/*  91 */         Vector3f cp0 = localStack.get$javax$vecmath$Vector3f();
/*  92 */         cp0.cross(v0p, v1p);
/*     */ 
/*  94 */         if (cp0.dot(triangleNormal) >= edge_tolerance) {
/*  95 */           Vector3f v2p = localStack.get$javax$vecmath$Vector3f();
/*  96 */           v2p.sub(vert2, point);
/*  97 */           Vector3f cp1 = localStack.get$javax$vecmath$Vector3f();
/*  98 */           cp1.cross(v1p, v2p);
/*  99 */           if (cp1.dot(triangleNormal) >= edge_tolerance) {
/* 100 */             Vector3f cp2 = localStack.get$javax$vecmath$Vector3f();
/* 101 */             cp2.cross(v2p, v0p);
/*     */ 
/* 103 */             if (cp2.dot(triangleNormal) >= edge_tolerance)
/*     */             {
/* 105 */               if (dist_a > 0.0F) {
/* 106 */                 this.hitFraction = reportHit(triangleNormal, distance, partId, triangleIndex);
/*     */               }
/*     */               else {
/* 109 */                 Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 110 */                 tmp.negate(triangleNormal);
/* 111 */                 this.hitFraction = reportHit(tmp, distance, partId, triangleIndex);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       return; } finally {
/* 118 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public abstract float reportHit(Vector3f paramVector3f, float paramFloat, int paramInt1, int paramInt2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.TriangleRaycastCallback
 * JD-Core Version:    0.6.2
 */