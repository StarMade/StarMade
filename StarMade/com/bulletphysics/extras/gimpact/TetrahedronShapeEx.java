/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.BU_Simplex1to4;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/* 36:   */class TetrahedronShapeEx
/* 37:   */  extends BU_Simplex1to4
/* 38:   */{
/* 39:   */  public TetrahedronShapeEx()
/* 40:   */  {
/* 41:41 */    this.numVertices = 4;
/* 42:42 */    for (int i = 0; i < this.numVertices; i++) {
/* 43:43 */      this.vertices[i] = new Vector3f();
/* 44:   */    }
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void setVertices(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f v3) {
/* 48:48 */    this.vertices[0].set(v0);
/* 49:49 */    this.vertices[1].set(v1);
/* 50:50 */    this.vertices[2].set(v2);
/* 51:51 */    this.vertices[3].set(v3);
/* 52:52 */    recalcLocalAabb();
/* 53:   */  }
/* 54:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TetrahedronShapeEx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */