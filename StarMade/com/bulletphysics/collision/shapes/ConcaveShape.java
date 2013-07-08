package com.bulletphysics.collision.shapes;

import javax.vecmath.Vector3f;

public abstract class ConcaveShape
  extends CollisionShape
{
  protected float collisionMargin = 0.0F;
  
  public abstract void processAllTriangles(TriangleCallback paramTriangleCallback, Vector3f paramVector3f1, Vector3f paramVector3f2);
  
  public float getMargin()
  {
    return this.collisionMargin;
  }
  
  public void setMargin(float margin)
  {
    this.collisionMargin = margin;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ConcaveShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */