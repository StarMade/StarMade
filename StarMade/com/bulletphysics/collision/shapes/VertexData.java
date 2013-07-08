package com.bulletphysics.collision.shapes;

import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

public abstract class VertexData
{
  public abstract int getVertexCount();
  
  public abstract int getIndexCount();
  
  public abstract <T extends Tuple3f> T getVertex(int paramInt, T paramT);
  
  public abstract void setVertex(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3);
  
  public void setVertex(int idx, Tuple3f local_t)
  {
    setVertex(idx, local_t.field_615, local_t.field_616, local_t.field_617);
  }
  
  public abstract int getIndex(int paramInt);
  
  public void getTriangle(int firstIndex, Vector3f scale, Vector3f[] triangle)
  {
    for (int local_i = 0; local_i < 3; local_i++)
    {
      getVertex(getIndex(firstIndex + local_i), triangle[local_i]);
      VectorUtil.mul(triangle[local_i], triangle[local_i], scale);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.VertexData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */