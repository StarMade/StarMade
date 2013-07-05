/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class StridingMeshInterface
/*     */ {
/*     */   protected final Vector3f scaling;
/*     */ 
/*     */   public StridingMeshInterface()
/*     */   {
/*  39 */     this.scaling = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   public void internalProcessAllTriangles(InternalTriangleIndexCallback arg1, Vector3f arg2, Vector3f arg3) {
/*  42 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); int graphicssubparts = getNumSubParts();
/*  43 */       Vector3f[] triangle = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/*     */ 
/*  45 */       Vector3f meshScaling = getScaling(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/*  47 */       for (int part = 0; part < graphicssubparts; part++) {
/*  48 */         VertexData data = getLockedReadOnlyVertexIndexBase(part);
/*     */ 
/*  50 */         int i = 0; for (int cnt = data.getIndexCount() / 3; i < cnt; i++) {
/*  51 */           data.getTriangle(i * 3, meshScaling, triangle);
/*  52 */           callback.internalProcessTriangleIndex(triangle, part, i);
/*     */         }
/*     */ 
/*  55 */         unLockReadOnlyVertexBase(part);
/*     */       }return; } finally {
/*  57 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void calculateAabbBruteForce(Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/*  75 */     AabbCalculationCallback aabbCallback = new AabbCalculationCallback(null);
/*  76 */     aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*  77 */     aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  78 */     internalProcessAllTriangles(aabbCallback, aabbMin, aabbMax);
/*     */ 
/*  80 */     aabbMin.set(aabbCallback.aabbMin);
/*  81 */     aabbMax.set(aabbCallback.aabbMax);
/*     */   }
/*     */ 
/*     */   public abstract VertexData getLockedVertexIndexBase(int paramInt);
/*     */ 
/*     */   public abstract VertexData getLockedReadOnlyVertexIndexBase(int paramInt);
/*     */ 
/*     */   public abstract void unLockVertexBase(int paramInt);
/*     */ 
/*     */   public abstract void unLockReadOnlyVertexBase(int paramInt);
/*     */ 
/*     */   public abstract int getNumSubParts();
/*     */ 
/*     */   public abstract void preallocateVertices(int paramInt);
/*     */ 
/*     */   public abstract void preallocateIndices(int paramInt);
/*     */ 
/*     */   public Vector3f getScaling(Vector3f out)
/*     */   {
/* 113 */     out.set(this.scaling);
/* 114 */     return out;
/*     */   }
/*     */ 
/*     */   public void setScaling(Vector3f scaling) {
/* 118 */     this.scaling.set(scaling);
/*     */   }
/*     */ 
/*     */   private static class AabbCalculationCallback extends InternalTriangleIndexCallback
/*     */   {
/*  60 */     public final Vector3f aabbMin = new Vector3f(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  61 */     public final Vector3f aabbMax = new Vector3f(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*     */ 
/*     */     public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex) {
/*  64 */       VectorUtil.setMin(this.aabbMin, triangle[0]);
/*  65 */       VectorUtil.setMax(this.aabbMax, triangle[0]);
/*  66 */       VectorUtil.setMin(this.aabbMin, triangle[1]);
/*  67 */       VectorUtil.setMax(this.aabbMax, triangle[1]);
/*  68 */       VectorUtil.setMin(this.aabbMin, triangle[2]);
/*  69 */       VectorUtil.setMax(this.aabbMax, triangle[2]);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.StridingMeshInterface
 * JD-Core Version:    0.6.2
 */