/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 32:   */public abstract class ConcaveShape
/* 33:   */  extends CollisionShape
/* 34:   */{
/* 35:35 */  protected float collisionMargin = 0.0F;
/* 36:   */  
/* 37:   */  public abstract void processAllTriangles(TriangleCallback paramTriangleCallback, Vector3f paramVector3f1, Vector3f paramVector3f2);
/* 38:   */  
/* 39:   */  public float getMargin() {
/* 40:40 */    return this.collisionMargin;
/* 41:   */  }
/* 42:   */  
/* 43:   */  public void setMargin(float margin) {
/* 44:44 */    this.collisionMargin = margin;
/* 45:   */  }
/* 46:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConcaveShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */