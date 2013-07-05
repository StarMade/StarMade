/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class ConvexInternalShape extends ConvexShape
/*     */ {
/*  41 */   protected final Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
/*  42 */   protected final Vector3f implicitShapeDimensions = new Vector3f();
/*  43 */   protected float collisionMargin = 0.04F;
/*     */ 
/*     */   public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/*  50 */     getAabbSlow(t, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   public void getAabbSlow(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  55 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); float margin = getMargin();
/*  56 */       Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/*  57 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  58 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  60 */       for (int i = 0; i < 3; i++)
/*     */       {
/*  62 */         vec.set(0.0F, 0.0F, 0.0F);
/*  63 */         VectorUtil.setCoord(vec, i, 1.0F);
/*     */ 
/*  65 */         MatrixUtil.transposeTransform(tmp1, vec, trans.basis);
/*  66 */         localGetSupportingVertex(tmp1, tmp2);
/*     */ 
/*  68 */         trans.transform(tmp2);
/*     */ 
/*  70 */         VectorUtil.setCoord(maxAabb, i, VectorUtil.getCoord(tmp2, i) + margin);
/*     */ 
/*  72 */         VectorUtil.setCoord(vec, i, -1.0F);
/*     */ 
/*  74 */         MatrixUtil.transposeTransform(tmp1, vec, trans.basis);
/*  75 */         localGetSupportingVertex(tmp1, tmp2);
/*  76 */         trans.transform(tmp2);
/*     */ 
/*  78 */         VectorUtil.setCoord(minAabb, i, VectorUtil.getCoord(tmp2, i) - margin);
/*     */       }return; } finally {
/*  80 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2) {
/*  84 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVertex = localGetSupportingVertexWithoutMargin(vec, out);
/*     */ 
/*  86 */       if (getMargin() != 0.0F) {
/*  87 */         Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/*  88 */         if (vecnorm.lengthSquared() < 1.421086E-014F) {
/*  89 */           vecnorm.set(-1.0F, -1.0F, -1.0F);
/*     */         }
/*  91 */         vecnorm.normalize();
/*  92 */         supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/*     */       }
/*  94 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling) {
/*  98 */     this.localScaling.absolute(scaling);
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out) {
/* 102 */     out.set(this.localScaling);
/* 103 */     return out;
/*     */   }
/*     */ 
/*     */   public float getMargin() {
/* 107 */     return this.collisionMargin;
/*     */   }
/*     */ 
/*     */   public void setMargin(float margin) {
/* 111 */     this.collisionMargin = margin;
/*     */   }
/*     */ 
/*     */   public int getNumPreferredPenetrationDirections()
/*     */   {
/* 116 */     return 0;
/*     */   }
/*     */ 
/*     */   public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/*     */   {
/* 121 */     throw new InternalError();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexInternalShape
 * JD-Core Version:    0.6.2
 */