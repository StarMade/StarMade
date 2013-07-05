/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public abstract class ConcaveShape extends CollisionShape
/*    */ {
/* 35 */   protected float collisionMargin = 0.0F;
/*    */ 
/*    */   public abstract void processAllTriangles(TriangleCallback paramTriangleCallback, Vector3f paramVector3f1, Vector3f paramVector3f2);
/*    */ 
/*    */   public float getMargin() {
/* 40 */     return this.collisionMargin;
/*    */   }
/*    */ 
/*    */   public void setMargin(float margin) {
/* 44 */     this.collisionMargin = margin;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConcaveShape
 * JD-Core Version:    0.6.2
 */