/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.SphereShape;
/*  4:   */
/*  5:   */public class ChangableSphereShape extends SphereShape
/*  6:   */{
/*  7:   */  public ChangableSphereShape(float paramFloat) {
/*  8: 8 */    super(paramFloat);
/*  9:   */  }
/* 10:   */  
/* 11:   */  public void setRadius(float paramFloat)
/* 12:   */  {
/* 13:13 */    this.implicitShapeDimensions.x = paramFloat;
/* 14:14 */    this.collisionMargin = paramFloat;
/* 15:   */  }
/* 16:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ChangableSphereShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */