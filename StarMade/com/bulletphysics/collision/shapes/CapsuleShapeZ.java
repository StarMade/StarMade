package com.bulletphysics.collision.shapes;

import javax.vecmath.Vector3f;

public class CapsuleShapeZ
  extends CapsuleShape
{
  public CapsuleShapeZ(float radius, float height)
  {
    this.upAxis = 2;
    this.implicitShapeDimensions.set(radius, radius, 0.5F * height);
  }
  
  public String getName()
  {
    return "CapsuleZ";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShapeZ
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */