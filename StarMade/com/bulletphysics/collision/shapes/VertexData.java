/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import com.bulletphysics.linearmath.VectorUtil;
/*  4:   */import javax.vecmath.Tuple3f;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 34:   */public abstract class VertexData
/* 35:   */{
/* 36:   */  public abstract int getVertexCount();
/* 37:   */  
/* 38:   */  public abstract int getIndexCount();
/* 39:   */  
/* 40:   */  public abstract <T extends Tuple3f> T getVertex(int paramInt, T paramT);
/* 41:   */  
/* 42:   */  public abstract void setVertex(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3);
/* 43:   */  
/* 44:   */  public void setVertex(int idx, Tuple3f t)
/* 45:   */  {
/* 46:46 */    setVertex(idx, t.x, t.y, t.z);
/* 47:   */  }
/* 48:   */  
/* 49:   */  public abstract int getIndex(int paramInt);
/* 50:   */  
/* 51:   */  public void getTriangle(int firstIndex, Vector3f scale, Vector3f[] triangle) {
/* 52:52 */    for (int i = 0; i < 3; i++) {
/* 53:53 */      getVertex(getIndex(firstIndex + i), triangle[i]);
/* 54:54 */      VectorUtil.mul(triangle[i], triangle[i], scale);
/* 55:   */    }
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.VertexData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */