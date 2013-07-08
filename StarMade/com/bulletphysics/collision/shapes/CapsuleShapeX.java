/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 32:   */public class CapsuleShapeX
/* 33:   */  extends CapsuleShape
/* 34:   */{
/* 35:   */  public CapsuleShapeX(float radius, float height)
/* 36:   */  {
/* 37:37 */    this.upAxis = 0;
/* 38:38 */    this.implicitShapeDimensions.set(0.5F * height, radius, radius);
/* 39:   */  }
/* 40:   */  
/* 41:   */  public String getName()
/* 42:   */  {
/* 43:43 */    return "CapsuleX";
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShapeX
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */