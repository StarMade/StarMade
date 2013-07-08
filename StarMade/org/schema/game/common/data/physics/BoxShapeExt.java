/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.BoxShape;
/*  4:   */import com.bulletphysics.linearmath.VectorUtil;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/*  8:   */public class BoxShapeExt
/*  9:   */  extends BoxShape
/* 10:   */{
/* 11:11 */  private Vector3f marginV = new Vector3f();
/* 12:12 */  private Vector3f d = new Vector3f();
/* 13:   */  
/* 15:15 */  public BoxShapeExt(Vector3f paramVector3f) { super(paramVector3f); }
/* 16:   */  
/* 17:   */  public void setDimFromBB(Vector3f paramVector3f1, Vector3f paramVector3f2) {
/* 18:18 */    this.marginV.set(getMargin(), getMargin(), getMargin());
/* 19:19 */    this.d.sub(paramVector3f2, paramVector3f1);
/* 20:   */    
/* 21:21 */    VectorUtil.mul(this.implicitShapeDimensions, this.d, this.localScaling);
/* 22:22 */    this.implicitShapeDimensions.sub(this.marginV);
/* 23:   */  }
/* 24:   */  
/* 25:25 */  public void setHalfSize(Vector3f paramVector3f) { this.implicitShapeDimensions.set(paramVector3f); }
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.BoxShapeExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */