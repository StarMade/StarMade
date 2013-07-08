package com.bulletphysics.collision.shapes;

import java.nio.ByteBuffer;
import javax.vecmath.Tuple3f;

public class ByteBufferVertexData
  extends VertexData
{
  public ByteBuffer vertexData;
  public int vertexCount;
  public int vertexStride;
  public ScalarType vertexType;
  public ByteBuffer indexData;
  public int indexCount;
  public int indexStride;
  public ScalarType indexType;
  
  public int getVertexCount()
  {
    return this.vertexCount;
  }
  
  public int getIndexCount()
  {
    return this.indexCount;
  }
  
  public <T extends Tuple3f> T getVertex(int idx, T out)
  {
    int off = idx * this.vertexStride;
    out.field_615 = this.vertexData.getFloat(off + 0);
    out.field_616 = this.vertexData.getFloat(off + 4);
    out.field_617 = this.vertexData.getFloat(off + 8);
    return out;
  }
  
  public void setVertex(int idx, float local_x, float local_y, float local_z)
  {
    int off = idx * this.vertexStride;
    this.vertexData.putFloat(off + 0, local_x);
    this.vertexData.putFloat(off + 4, local_y);
    this.vertexData.putFloat(off + 8, local_z);
  }
  
  public int getIndex(int idx)
  {
    if (this.indexType == ScalarType.SHORT) {
      return this.indexData.getShort(idx * this.indexStride) & 0xFFFF;
    }
    if (this.indexType == ScalarType.INTEGER) {
      return this.indexData.getInt(idx * this.indexStride);
    }
    throw new IllegalStateException("indicies type must be short or integer");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.ByteBufferVertexData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */