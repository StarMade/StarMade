/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.SphereShape;
/*    */ 
/*    */ public class ChangableSphereShape extends SphereShape
/*    */ {
/*    */   public ChangableSphereShape(float paramFloat)
/*    */   {
/*  8 */     super(paramFloat);
/*    */   }
/*    */ 
/*    */   public void setRadius(float paramFloat)
/*    */   {
/* 13 */     this.implicitShapeDimensions.x = paramFloat;
/* 14 */     this.collisionMargin = paramFloat;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ChangableSphereShape
 * JD-Core Version:    0.6.2
 */