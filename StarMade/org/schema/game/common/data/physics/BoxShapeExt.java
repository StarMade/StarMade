/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.BoxShape;
/*    */ import com.bulletphysics.linearmath.VectorUtil;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class BoxShapeExt extends BoxShape
/*    */ {
/* 11 */   private Vector3f marginV = new Vector3f();
/* 12 */   private Vector3f d = new Vector3f();
/*    */ 
/*    */   public BoxShapeExt(Vector3f paramVector3f) {
/* 15 */     super(paramVector3f);
/*    */   }
/*    */   public void setDimFromBB(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 18 */     this.marginV.set(getMargin(), getMargin(), getMargin());
/* 19 */     this.d.sub(paramVector3f2, paramVector3f1);
/*    */ 
/* 21 */     VectorUtil.mul(this.implicitShapeDimensions, this.d, this.localScaling);
/* 22 */     this.implicitShapeDimensions.sub(this.marginV);
/*    */   }
/*    */   public void setHalfSize(Vector3f paramVector3f) {
/* 25 */     this.implicitShapeDimensions.set(paramVector3f);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.BoxShapeExt
 * JD-Core Version:    0.6.2
 */