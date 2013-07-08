package com.bulletphysics.extras.gimpact;

import com.bulletphysics.collision.shapes.BU_Simplex1to4;
import javax.vecmath.Vector3f;

class TetrahedronShapeEx
  extends BU_Simplex1to4
{
  public TetrahedronShapeEx()
  {
    this.numVertices = 4;
    for (int local_i = 0; local_i < this.numVertices; local_i++) {
      this.vertices[local_i] = new Vector3f();
    }
  }
  
  public void setVertices(Vector3f local_v0, Vector3f local_v1, Vector3f local_v2, Vector3f local_v3)
  {
    this.vertices[0].set(local_v0);
    this.vertices[1].set(local_v1);
    this.vertices[2].set(local_v2);
    this.vertices[3].set(local_v3);
    recalcLocalAabb();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.TetrahedronShapeEx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */