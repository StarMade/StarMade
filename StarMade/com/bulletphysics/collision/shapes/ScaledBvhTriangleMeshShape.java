/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ScaledBvhTriangleMeshShape extends ConcaveShape
/*     */ {
/*  45 */   protected final Vector3f localScaling = new Vector3f();
/*     */   protected BvhTriangleMeshShape bvhTriMeshShape;
/*     */ 
/*     */   public ScaledBvhTriangleMeshShape(BvhTriangleMeshShape childShape, Vector3f localScaling)
/*     */   {
/*  49 */     this.localScaling.set(localScaling);
/*  50 */     this.bvhTriMeshShape = childShape;
/*     */   }
/*     */ 
/*     */   public BvhTriangleMeshShape getChildShape() {
/*  54 */     return this.bvhTriMeshShape;
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  59 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); ScaledTriangleCallback scaledCallback = new ScaledTriangleCallback(callback, this.localScaling);
/*     */ 
/*  61 */       Vector3f invLocalScaling = localStack.get$javax$vecmath$Vector3f();
/*  62 */       invLocalScaling.set(1.0F / this.localScaling.x, 1.0F / this.localScaling.y, 1.0F / this.localScaling.z);
/*     */ 
/*  64 */       Vector3f scaledAabbMin = localStack.get$javax$vecmath$Vector3f();
/*  65 */       Vector3f scaledAabbMax = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  68 */       scaledAabbMin.x = (this.localScaling.x >= 0.0F ? aabbMin.x * invLocalScaling.x : aabbMax.x * invLocalScaling.x);
/*  69 */       scaledAabbMin.y = (this.localScaling.y >= 0.0F ? aabbMin.y * invLocalScaling.y : aabbMax.y * invLocalScaling.y);
/*  70 */       scaledAabbMin.z = (this.localScaling.z >= 0.0F ? aabbMin.z * invLocalScaling.z : aabbMax.z * invLocalScaling.z);
/*     */ 
/*  72 */       scaledAabbMax.x = (this.localScaling.x <= 0.0F ? aabbMin.x * invLocalScaling.x : aabbMax.x * invLocalScaling.x);
/*  73 */       scaledAabbMax.y = (this.localScaling.y <= 0.0F ? aabbMin.y * invLocalScaling.y : aabbMax.y * invLocalScaling.y);
/*  74 */       scaledAabbMax.z = (this.localScaling.z <= 0.0F ? aabbMin.z * invLocalScaling.z : aabbMax.z * invLocalScaling.z);
/*     */ 
/*  76 */       this.bvhTriMeshShape.processAllTriangles(scaledCallback, scaledAabbMin, scaledAabbMax);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3) {
/*  81 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f localAabbMin = this.bvhTriMeshShape.getLocalAabbMin(localStack.get$javax$vecmath$Vector3f());
/*  82 */       Vector3f localAabbMax = this.bvhTriMeshShape.getLocalAabbMax(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/*  84 */       Vector3f tmpLocalAabbMin = localStack.get$javax$vecmath$Vector3f();
/*  85 */       Vector3f tmpLocalAabbMax = localStack.get$javax$vecmath$Vector3f();
/*  86 */       VectorUtil.mul(tmpLocalAabbMin, localAabbMin, this.localScaling);
/*  87 */       VectorUtil.mul(tmpLocalAabbMax, localAabbMax, this.localScaling);
/*     */ 
/*  89 */       localAabbMin.x = (this.localScaling.x >= 0.0F ? tmpLocalAabbMin.x : tmpLocalAabbMax.x);
/*  90 */       localAabbMin.y = (this.localScaling.y >= 0.0F ? tmpLocalAabbMin.y : tmpLocalAabbMax.y);
/*  91 */       localAabbMin.z = (this.localScaling.z >= 0.0F ? tmpLocalAabbMin.z : tmpLocalAabbMax.z);
/*  92 */       localAabbMax.x = (this.localScaling.x <= 0.0F ? tmpLocalAabbMin.x : tmpLocalAabbMax.x);
/*  93 */       localAabbMax.y = (this.localScaling.y <= 0.0F ? tmpLocalAabbMin.y : tmpLocalAabbMax.y);
/*  94 */       localAabbMax.z = (this.localScaling.z <= 0.0F ? tmpLocalAabbMin.z : tmpLocalAabbMax.z);
/*     */ 
/*  96 */       Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/*  97 */       localHalfExtents.sub(localAabbMax, localAabbMin);
/*  98 */       localHalfExtents.scale(0.5F);
/*     */ 
/* 100 */       float margin = this.bvhTriMeshShape.getMargin();
/* 101 */       localHalfExtents.x += margin;
/* 102 */       localHalfExtents.y += margin;
/* 103 */       localHalfExtents.z += margin;
/*     */ 
/* 105 */       Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 106 */       localCenter.add(localAabbMax, localAabbMin);
/* 107 */       localCenter.scale(0.5F);
/*     */ 
/* 109 */       Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 110 */       MatrixUtil.absolute(abs_b);
/*     */ 
/* 112 */       Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 113 */       trans.transform(center);
/*     */ 
/* 115 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 116 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 117 */       abs_b.getRow(0, tmp);
/* 118 */       extent.x = tmp.dot(localHalfExtents);
/* 119 */       abs_b.getRow(1, tmp);
/* 120 */       extent.y = tmp.dot(localHalfExtents);
/* 121 */       abs_b.getRow(2, tmp);
/* 122 */       extent.z = tmp.dot(localHalfExtents);
/*     */ 
/* 124 */       aabbMin.sub(center, extent);
/* 125 */       aabbMax.add(center, extent);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 126 */       .Stack tmp484_482 = localStack; tmp484_482.pop$javax$vecmath$Vector3f(); tmp484_482.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType() {
/* 130 */     return BroadphaseNativeType.SCALED_TRIANGLE_MESH_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/* 135 */     this.localScaling.set(scaling);
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 140 */     out.set(this.localScaling);
/* 141 */     return out;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 150 */     return "SCALEDBVHTRIANGLEMESH";
/*     */   }
/*     */ 
/*     */   private static class ScaledTriangleCallback extends TriangleCallback
/*     */   {
/*     */     private TriangleCallback originalCallback;
/*     */     private Vector3f localScaling;
/* 158 */     private Vector3f[] newTriangle = new Vector3f[3];
/*     */ 
/*     */     public ScaledTriangleCallback(TriangleCallback originalCallback, Vector3f localScaling) {
/* 161 */       this.originalCallback = originalCallback;
/* 162 */       this.localScaling = localScaling;
/*     */ 
/* 164 */       for (int i = 0; i < this.newTriangle.length; i++)
/* 165 */         this.newTriangle[i] = new Vector3f();
/*     */     }
/*     */ 
/*     */     public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
/*     */     {
/* 170 */       VectorUtil.mul(this.newTriangle[0], triangle[0], this.localScaling);
/* 171 */       VectorUtil.mul(this.newTriangle[1], triangle[1], this.localScaling);
/* 172 */       VectorUtil.mul(this.newTriangle[2], triangle[2], this.localScaling);
/* 173 */       this.originalCallback.processTriangle(this.newTriangle, partId, triangleIndex);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ScaledBvhTriangleMeshShape
 * JD-Core Version:    0.6.2
 */