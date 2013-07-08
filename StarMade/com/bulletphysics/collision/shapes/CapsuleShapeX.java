package com.bulletphysics.collision.shapes;

import javax.vecmath.Vector3f;

public class CapsuleShapeX
  extends CapsuleShape
{
  public CapsuleShapeX(float radius, float height)
  {
    this.upAxis = 0;
    this.implicitShapeDimensions.set(0.5F * height, radius, radius);
  }
  
  public String getName()
  {
    return "CapsuleX";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShapeX
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */