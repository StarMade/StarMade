/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class IDebugDraw
/*     */ {
/*     */   public abstract void drawLine(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3);
/*     */ 
/*     */   public void drawTriangle(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f n0, Vector3f n1, Vector3f n2, Vector3f color, float alpha)
/*     */   {
/*  50 */     drawTriangle(v0, v1, v2, color, alpha);
/*     */   }
/*     */ 
/*     */   public void drawTriangle(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f color, float alpha) {
/*  54 */     drawLine(v0, v1, color);
/*  55 */     drawLine(v1, v2, color);
/*  56 */     drawLine(v2, v0, color);
/*     */   }
/*     */ 
/*     */   public abstract void drawContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt, Vector3f paramVector3f3);
/*     */ 
/*     */   public abstract void reportErrorWarning(String paramString);
/*     */ 
/*     */   public abstract void draw3dText(Vector3f paramVector3f, String paramString);
/*     */ 
/*     */   public abstract void setDebugMode(int paramInt);
/*     */ 
/*     */   public abstract int getDebugMode();
/*     */ 
/*     */   public void drawAabb(Vector3f arg1, Vector3f arg2, Vector3f arg3) {
/*  70 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f(to);
/*  71 */       halfExtents.sub(from);
/*  72 */       halfExtents.scale(0.5F);
/*     */ 
/*  74 */       Vector3f center = localStack.get$javax$vecmath$Vector3f(to);
/*  75 */       center.add(from);
/*  76 */       center.scale(0.5F);
/*     */ 
/*  80 */       Vector3f edgecoord = localStack.get$javax$vecmath$Vector3f();
/*  81 */       edgecoord.set(1.0F, 1.0F, 1.0F);
/*  82 */       Vector3f pa = localStack.get$javax$vecmath$Vector3f(); Vector3f pb = localStack.get$javax$vecmath$Vector3f();
/*  83 */       for (int i = 0; i < 4; i++) {
/*  84 */         for (int j = 0; j < 3; j++) {
/*  85 */           pa.set(edgecoord.x * halfExtents.x, edgecoord.y * halfExtents.y, edgecoord.z * halfExtents.z);
/*  86 */           pa.add(center);
/*     */ 
/*  88 */           int othercoord = j % 3;
/*     */ 
/*  90 */           VectorUtil.mulCoord(edgecoord, othercoord, -1.0F);
/*  91 */           pb.set(edgecoord.x * halfExtents.x, edgecoord.y * halfExtents.y, edgecoord.z * halfExtents.z);
/*  92 */           pb.add(center);
/*     */ 
/*  94 */           drawLine(pa, pb, color);
/*     */         }
/*  96 */         edgecoord.set(-1.0F, -1.0F, -1.0F);
/*  97 */         if (i < 3)
/*  98 */           VectorUtil.mulCoord(edgecoord, i, -1.0F);
/*     */       }
/*     */       return; } finally {
/* 101 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.IDebugDraw
 * JD-Core Version:    0.6.2
 */