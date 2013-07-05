/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import com.bulletphysics..Stack;
/*    */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class SphereShape extends ConvexInternalShape
/*    */ {
/*    */   public SphereShape(float radius)
/*    */   {
/* 39 */     this.implicitShapeDimensions.x = radius;
/* 40 */     this.collisionMargin = radius;
/*    */   }
/*    */ 
/*    */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*    */   {
/* 45 */     out.set(0.0F, 0.0F, 0.0F);
/* 46 */     return out;
/*    */   }
/*    */ 
/*    */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*    */   {
/* 51 */     for (int i = 0; i < numVectors; i++)
/* 52 */       supportVerticesOut[i].set(0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*    */   {
/* 58 */     .Stack localStack = .Stack.get();
/*    */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f center = t.origin;
/* 59 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 60 */       extent.set(getMargin(), getMargin(), getMargin());
/* 61 */       aabbMin.sub(center, extent);
/* 62 */       aabbMax.add(center, extent);
/*    */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*    */   }
/*    */ 
/*    */   public BroadphaseNativeType getShapeType() {
/* 67 */     return BroadphaseNativeType.SPHERE_SHAPE_PROXYTYPE;
/*    */   }
/*    */ 
/*    */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*    */   {
/* 72 */     float elem = 0.4F * mass * getMargin() * getMargin();
/* 73 */     inertia.set(elem, elem, elem);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 78 */     return "SPHERE";
/*    */   }
/*    */ 
/*    */   public float getRadius() {
/* 82 */     return this.implicitShapeDimensions.x * this.localScaling.x;
/*    */   }
/*    */ 
/*    */   public void setMargin(float margin)
/*    */   {
/* 87 */     super.setMargin(margin);
/*    */   }
/*    */ 
/*    */   public float getMargin()
/*    */   {
/* 94 */     return getRadius();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.SphereShape
 * JD-Core Version:    0.6.2
 */