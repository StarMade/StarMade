/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class CapsuleShapeZ extends CapsuleShape
/*    */ {
/*    */   public CapsuleShapeZ(float radius, float height)
/*    */   {
/* 37 */     this.upAxis = 2;
/* 38 */     this.implicitShapeDimensions.set(radius, radius, 0.5F * height);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 43 */     return "CapsuleZ";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShapeZ
 * JD-Core Version:    0.6.2
 */