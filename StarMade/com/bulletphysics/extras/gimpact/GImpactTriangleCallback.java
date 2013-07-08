package com.bulletphysics.extras.gimpact;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.TriangleCallback;
import javax.vecmath.Vector3f;

class GImpactTriangleCallback
  extends TriangleCallback
{
  public GImpactCollisionAlgorithm algorithm;
  public CollisionObject body0;
  public CollisionObject body1;
  public GImpactShapeInterface gimpactshape0;
  public boolean swapped;
  public float margin;
  
  public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex)
  {
    TriangleShapeEx tri1 = new TriangleShapeEx(triangle[0], triangle[1], triangle[2]);
    tri1.setMargin(this.margin);
    if (this.swapped)
    {
      this.algorithm.setPart0(partId);
      this.algorithm.setFace0(triangleIndex);
    }
    else
    {
      this.algorithm.setPart1(partId);
      this.algorithm.setFace1(triangleIndex);
    }
    this.algorithm.gimpact_vs_shape(this.body0, this.body1, this.gimpactshape0, tri1, this.swapped);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactTriangleCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */