/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class TriangleMeshShape extends ConcaveShape
/*     */ {
/*  42 */   protected final Vector3f localAabbMin = new Vector3f();
/*  43 */   protected final Vector3f localAabbMax = new Vector3f();
/*     */   protected StridingMeshInterface meshInterface;
/*     */ 
/*     */   protected TriangleMeshShape(StridingMeshInterface meshInterface)
/*     */   {
/*  51 */     this.meshInterface = meshInterface;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  58 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  60 */       Vector3f supportVertex = out;
/*     */ 
/*  62 */       Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/*  63 */       ident.setIdentity();
/*     */ 
/*  65 */       SupportVertexCallback supportCallback = new SupportVertexCallback(vec, ident);
/*     */ 
/*  67 */       Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/*  68 */       aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  69 */       tmp.negate(aabbMax);
/*     */ 
/*  71 */       processAllTriangles(supportCallback, tmp, aabbMax);
/*     */ 
/*  73 */       supportCallback.getSupportVertexLocal(supportVertex);
/*     */ 
/*  75 */       return out;
/*     */     }
/*     */     finally
/*     */     {
/*  75 */       .Stack tmp102_100 = localStack; tmp102_100.pop$com$bulletphysics$linearmath$Transform(); tmp102_100.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out) {
/*  79 */     if (!$assertionsDisabled) throw new AssertionError();
/*  80 */     return localGetSupportingVertex(vec, out);
/*     */   }
/*     */ 
/*     */   public void recalcLocalAabb() {
/*  84 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < 3; i++) {
/*  85 */         Vector3f vec = localStack.get$javax$vecmath$Vector3f();
/*  86 */         vec.set(0.0F, 0.0F, 0.0F);
/*  87 */         VectorUtil.setCoord(vec, i, 1.0F);
/*  88 */         Vector3f tmp = localGetSupportingVertex(vec, localStack.get$javax$vecmath$Vector3f());
/*  89 */         VectorUtil.setCoord(this.localAabbMax, i, VectorUtil.getCoord(tmp, i) + this.collisionMargin);
/*  90 */         VectorUtil.setCoord(vec, i, -1.0F);
/*  91 */         localGetSupportingVertex(vec, tmp);
/*  92 */         VectorUtil.setCoord(this.localAabbMin, i, VectorUtil.getCoord(tmp, i) - this.collisionMargin);
/*     */       }return; } finally {
/*  94 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3) {
/*  98 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 100 */       Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/* 101 */       localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
/* 102 */       localHalfExtents.scale(0.5F);
/*     */ 
/* 104 */       Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 105 */       localCenter.add(this.localAabbMax, this.localAabbMin);
/* 106 */       localCenter.scale(0.5F);
/*     */ 
/* 108 */       Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 109 */       MatrixUtil.absolute(abs_b);
/*     */ 
/* 111 */       Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 112 */       trans.transform(center);
/*     */ 
/* 114 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 115 */       abs_b.getRow(0, tmp);
/* 116 */       extent.x = tmp.dot(localHalfExtents);
/* 117 */       abs_b.getRow(1, tmp);
/* 118 */       extent.y = tmp.dot(localHalfExtents);
/* 119 */       abs_b.getRow(2, tmp);
/* 120 */       extent.z = tmp.dot(localHalfExtents);
/*     */ 
/* 122 */       Vector3f margin = localStack.get$javax$vecmath$Vector3f();
/* 123 */       margin.set(getMargin(), getMargin(), getMargin());
/* 124 */       extent.add(margin);
/*     */ 
/* 126 */       aabbMin.sub(center, extent);
/* 127 */       aabbMax.add(center, extent);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 128 */       .Stack tmp234_232 = localStack; tmp234_232.pop$javax$vecmath$Vector3f(); tmp234_232.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax) {
/* 132 */     FilteredCallback filterCallback = new FilteredCallback(callback, aabbMin, aabbMax);
/*     */ 
/* 134 */     this.meshInterface.internalProcessAllTriangles(filterCallback, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*     */   {
/* 140 */     if (!$assertionsDisabled) throw new AssertionError();
/* 141 */     inertia.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/* 147 */     this.meshInterface.setScaling(scaling);
/* 148 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 153 */     return this.meshInterface.getScaling(out);
/*     */   }
/*     */ 
/*     */   public StridingMeshInterface getMeshInterface() {
/* 157 */     return this.meshInterface;
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalAabbMin(Vector3f out) {
/* 161 */     out.set(this.localAabbMin);
/* 162 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalAabbMax(Vector3f out) {
/* 166 */     out.set(this.localAabbMax);
/* 167 */     return out;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 172 */     return "TRIANGLEMESH";
/*     */   }
/*     */ 
/*     */   private static class FilteredCallback extends InternalTriangleIndexCallback
/*     */   {
/*     */     public TriangleCallback callback;
/* 212 */     public final Vector3f aabbMin = new Vector3f();
/* 213 */     public final Vector3f aabbMax = new Vector3f();
/*     */ 
/*     */     public FilteredCallback(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax) {
/* 216 */       this.callback = callback;
/* 217 */       this.aabbMin.set(aabbMin);
/* 218 */       this.aabbMax.set(aabbMax);
/*     */     }
/*     */ 
/*     */     public void internalProcessTriangleIndex(Vector3f[] triangle, int partId, int triangleIndex) {
/* 222 */       if (AabbUtil2.testTriangleAgainstAabb2(triangle, this.aabbMin, this.aabbMax))
/*     */       {
/* 224 */         this.callback.processTriangle(triangle, partId, triangleIndex);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class SupportVertexCallback extends TriangleCallback
/*     */   {
/* 178 */     private final Vector3f supportVertexLocal = new Vector3f(0.0F, 0.0F, 0.0F);
/* 179 */     public final Transform worldTrans = new Transform();
/* 180 */     public float maxDot = -1.0E+030F;
/* 181 */     public final Vector3f supportVecLocal = new Vector3f();
/*     */ 
/*     */     public SupportVertexCallback(Vector3f supportVecWorld, Transform trans) {
/* 184 */       this.worldTrans.set(trans);
/* 185 */       MatrixUtil.transposeTransform(this.supportVecLocal, supportVecWorld, this.worldTrans.basis);
/*     */     }
/*     */ 
/*     */     public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex) {
/* 189 */       for (int i = 0; i < 3; i++) {
/* 190 */         float dot = this.supportVecLocal.dot(triangle[i]);
/* 191 */         if (dot > this.maxDot) {
/* 192 */           this.maxDot = dot;
/* 193 */           this.supportVertexLocal.set(triangle[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public Vector3f getSupportVertexWorldSpace(Vector3f out) {
/* 199 */       out.set(this.supportVertexLocal);
/* 200 */       this.worldTrans.transform(out);
/* 201 */       return out;
/*     */     }
/*     */ 
/*     */     public Vector3f getSupportVertexLocal(Vector3f out) {
/* 205 */       out.set(this.supportVertexLocal);
/* 206 */       return out;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleMeshShape
 * JD-Core Version:    0.6.2
 */