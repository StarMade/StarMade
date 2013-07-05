/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class CapsuleShapeX extends CapsuleShape
/*    */ {
/*    */   public CapsuleShapeX(float radius, float height)
/*    */   {
/* 37 */     this.upAxis = 0;
/* 38 */     this.implicitShapeDimensions.set(0.5F * height, radius, radius);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 43 */     return "CapsuleX";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShapeX
 * JD-Core Version:    0.6.2
 */