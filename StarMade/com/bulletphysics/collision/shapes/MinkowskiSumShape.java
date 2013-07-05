/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class MinkowskiSumShape extends ConvexInternalShape
/*     */ {
/*  40 */   private final Transform transA = new Transform();
/*  41 */   private final Transform transB = new Transform();
/*     */   private ConvexShape shapeA;
/*     */   private ConvexShape shapeB;
/*     */ 
/*     */   public MinkowskiSumShape(ConvexShape shapeA, ConvexShape shapeB)
/*     */   {
/*  46 */     this.shapeA = shapeA;
/*  47 */     this.shapeB = shapeB;
/*  48 */     this.transA.setIdentity();
/*  49 */     this.transB.setIdentity();
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  54 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  55 */       Vector3f supVertexA = localStack.get$javax$vecmath$Vector3f();
/*  56 */       Vector3f supVertexB = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  59 */       tmp.negate(vec);
/*  60 */       MatrixUtil.transposeTransform(tmp, tmp, this.transA.basis);
/*  61 */       this.shapeA.localGetSupportingVertexWithoutMargin(tmp, supVertexA);
/*  62 */       this.transA.transform(supVertexA);
/*     */ 
/*  65 */       MatrixUtil.transposeTransform(tmp, vec, this.transB.basis);
/*  66 */       this.shapeB.localGetSupportingVertexWithoutMargin(tmp, supVertexB);
/*  67 */       this.transB.transform(supVertexB);
/*     */ 
/*  70 */       out.sub(supVertexA, supVertexB);
/*  71 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*     */   {
/*  77 */     for (int i = 0; i < numVectors; i++)
/*  78 */       localGetSupportingVertexWithoutMargin(vectors[i], supportVerticesOut[i]);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/*  84 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/*  89 */     return BroadphaseNativeType.MINKOWSKI_SUM_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*     */   {
/*  94 */     if (!$assertionsDisabled) throw new AssertionError();
/*  95 */     inertia.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 100 */     return "MinkowskiSum";
/*     */   }
/*     */ 
/*     */   public float getMargin()
/*     */   {
/* 105 */     return this.shapeA.getMargin() + this.shapeB.getMargin();
/*     */   }
/*     */ 
/*     */   public void setTransformA(Transform transA) {
/* 109 */     this.transA.set(transA);
/*     */   }
/*     */ 
/*     */   public void setTransformB(Transform transB) {
/* 113 */     this.transB.set(transB);
/*     */   }
/*     */ 
/*     */   public void getTransformA(Transform dest) {
/* 117 */     dest.set(this.transA);
/*     */   }
/*     */ 
/*     */   public void getTransformB(Transform dest) {
/* 121 */     dest.set(this.transB);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.MinkowskiSumShape
 * JD-Core Version:    0.6.2
 */