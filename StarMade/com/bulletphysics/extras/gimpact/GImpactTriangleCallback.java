/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  4:   */import com.bulletphysics.collision.shapes.TriangleCallback;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 36:   */class GImpactTriangleCallback
/* 37:   */  extends TriangleCallback
/* 38:   */{
/* 39:   */  public GImpactCollisionAlgorithm algorithm;
/* 40:   */  public CollisionObject body0;
/* 41:   */  public CollisionObject body1;
/* 42:   */  public GImpactShapeInterface gimpactshape0;
/* 43:   */  public boolean swapped;
/* 44:   */  public float margin;
/* 45:   */  
/* 46:   */  public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
/* 47:   */  {
/* 48:48 */    TriangleShapeEx tri1 = new TriangleShapeEx(triangle[0], triangle[1], triangle[2]);
/* 49:49 */    tri1.setMargin(this.margin);
/* 50:50 */    if (this.swapped) {
/* 51:51 */      this.algorithm.setPart0(partId);
/* 52:52 */      this.algorithm.setFace0(triangleIndex);
/* 53:   */    }
/* 54:   */    else {
/* 55:55 */      this.algorithm.setPart1(partId);
/* 56:56 */      this.algorithm.setFace1(triangleIndex);
/* 57:   */    }
/* 58:58 */    this.algorithm.gimpact_vs_shape(this.body0, this.body1, this.gimpactshape0, tri1, this.swapped);
/* 59:   */  }
/* 60:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactTriangleCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */