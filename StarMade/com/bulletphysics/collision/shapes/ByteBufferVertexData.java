/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import javax.vecmath.Tuple3f;
/*  5:   */
/* 33:   */public class ByteBufferVertexData
/* 34:   */  extends VertexData
/* 35:   */{
/* 36:   */  public ByteBuffer vertexData;
/* 37:   */  public int vertexCount;
/* 38:   */  public int vertexStride;
/* 39:   */  public ScalarType vertexType;
/* 40:   */  public ByteBuffer indexData;
/* 41:   */  public int indexCount;
/* 42:   */  public int indexStride;
/* 43:   */  public ScalarType indexType;
/* 44:   */  
/* 45:   */  public int getVertexCount()
/* 46:   */  {
/* 47:47 */    return this.vertexCount;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public int getIndexCount()
/* 51:   */  {
/* 52:52 */    return this.indexCount;
/* 53:   */  }
/* 54:   */  
/* 55:   */  public <T extends Tuple3f> T getVertex(int idx, T out)
/* 56:   */  {
/* 57:57 */    int off = idx * this.vertexStride;
/* 58:58 */    out.x = this.vertexData.getFloat(off + 0);
/* 59:59 */    out.y = this.vertexData.getFloat(off + 4);
/* 60:60 */    out.z = this.vertexData.getFloat(off + 8);
/* 61:61 */    return out;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public void setVertex(int idx, float x, float y, float z)
/* 65:   */  {
/* 66:66 */    int off = idx * this.vertexStride;
/* 67:67 */    this.vertexData.putFloat(off + 0, x);
/* 68:68 */    this.vertexData.putFloat(off + 4, y);
/* 69:69 */    this.vertexData.putFloat(off + 8, z);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public int getIndex(int idx)
/* 73:   */  {
/* 74:74 */    if (this.indexType == ScalarType.SHORT) {
/* 75:75 */      return this.indexData.getShort(idx * this.indexStride) & 0xFFFF;
/* 76:   */    }
/* 77:77 */    if (this.indexType == ScalarType.INTEGER) {
/* 78:78 */      return this.indexData.getInt(idx * this.indexStride);
/* 79:   */    }
/* 80:   */    
/* 81:81 */    throw new IllegalStateException("indicies type must be short or integer");
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ByteBufferVertexData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */