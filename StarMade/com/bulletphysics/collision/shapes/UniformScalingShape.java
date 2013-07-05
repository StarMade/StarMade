/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class UniformScalingShape extends ConvexShape
/*     */ {
/*     */   private ConvexShape childConvexShape;
/*     */   private float uniformScalingFactor;
/*     */ 
/*     */   public UniformScalingShape(ConvexShape convexChildShape, float uniformScalingFactor)
/*     */   {
/*  44 */     this.childConvexShape = convexChildShape;
/*  45 */     this.uniformScalingFactor = uniformScalingFactor;
/*     */   }
/*     */ 
/*     */   public float getUniformScalingFactor() {
/*  49 */     return this.uniformScalingFactor;
/*     */   }
/*     */ 
/*     */   public ConvexShape getChildShape() {
/*  53 */     return this.childConvexShape;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f vec, Vector3f out)
/*     */   {
/*  58 */     this.childConvexShape.localGetSupportingVertex(vec, out);
/*  59 */     out.scale(this.uniformScalingFactor);
/*  60 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*     */   {
/*  65 */     this.childConvexShape.localGetSupportingVertexWithoutMargin(vec, out);
/*  66 */     out.scale(this.uniformScalingFactor);
/*  67 */     return out;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*     */   {
/*  72 */     this.childConvexShape.batchedUnitVectorGetSupportingVertexWithoutMargin(vectors, supportVerticesOut, numVectors);
/*  73 */     for (int i = 0; i < numVectors; i++)
/*  74 */       supportVerticesOut[i].scale(this.uniformScalingFactor);
/*     */   }
/*     */ 
/*     */   public void getAabbSlow(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  80 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.childConvexShape.getAabbSlow(t, aabbMin, aabbMax);
/*  81 */       Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
/*  82 */       aabbCenter.add(aabbMax, aabbMin);
/*  83 */       aabbCenter.scale(0.5F);
/*     */ 
/*  85 */       Vector3f scaledAabbHalfExtends = localStack.get$javax$vecmath$Vector3f();
/*  86 */       scaledAabbHalfExtends.sub(aabbMax, aabbMin);
/*  87 */       scaledAabbHalfExtends.scale(0.5F * this.uniformScalingFactor);
/*     */ 
/*  89 */       aabbMin.sub(aabbCenter, scaledAabbHalfExtends);
/*  90 */       aabbMax.add(aabbCenter, scaledAabbHalfExtends);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling) {
/*  95 */     this.childConvexShape.setLocalScaling(scaling);
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 100 */     this.childConvexShape.getLocalScaling(out);
/* 101 */     return out;
/*     */   }
/*     */ 
/*     */   public void setMargin(float margin)
/*     */   {
/* 106 */     this.childConvexShape.setMargin(margin);
/*     */   }
/*     */ 
/*     */   public float getMargin()
/*     */   {
/* 111 */     return this.childConvexShape.getMargin() * this.uniformScalingFactor;
/*     */   }
/*     */ 
/*     */   public int getNumPreferredPenetrationDirections()
/*     */   {
/* 116 */     return this.childConvexShape.getNumPreferredPenetrationDirections();
/*     */   }
/*     */ 
/*     */   public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/*     */   {
/* 121 */     this.childConvexShape.getPreferredPenetrationDirection(index, penetrationVector);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/* 126 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.childConvexShape.getAabb(t, aabbMin, aabbMax);
/* 127 */       Vector3f aabbCenter = localStack.get$javax$vecmath$Vector3f();
/* 128 */       aabbCenter.add(aabbMax, aabbMin);
/* 129 */       aabbCenter.scale(0.5F);
/*     */ 
/* 131 */       Vector3f scaledAabbHalfExtends = localStack.get$javax$vecmath$Vector3f();
/* 132 */       scaledAabbHalfExtends.sub(aabbMax, aabbMin);
/* 133 */       scaledAabbHalfExtends.scale(0.5F * this.uniformScalingFactor);
/*     */ 
/* 135 */       aabbMin.sub(aabbCenter, scaledAabbHalfExtends);
/* 136 */       aabbMax.add(aabbCenter, scaledAabbHalfExtends);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType() {
/* 141 */     return BroadphaseNativeType.UNIFORM_SCALING_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*     */   {
/* 147 */     this.childConvexShape.calculateLocalInertia(mass, inertia);
/* 148 */     inertia.scale(this.uniformScalingFactor);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 153 */     return "UniformScalingShape";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.UniformScalingShape
 * JD-Core Version:    0.6.2
 */