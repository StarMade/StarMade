/*  1:   */package com.bulletphysics.linearmath.convexhull;
/*  2:   */
/*  3:   */import com.bulletphysics.util.ObjectArrayList;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */
/* 35:   */public class HullDesc
/* 36:   */{
/* 37:37 */  public int flags = HullFlags.DEFAULT;
/* 38:   */  
/* 40:40 */  public int vcount = 0;
/* 41:   */  
/* 43:   */  public ObjectArrayList<Vector3f> vertices;
/* 44:   */  
/* 46:46 */  int vertexStride = 12;
/* 47:   */  
/* 49:49 */  public float normalEpsilon = 0.001F;
/* 50:   */  
/* 52:52 */  public int maxVertices = 4096;
/* 53:   */  
/* 55:55 */  public int maxFaces = 4096;
/* 56:   */  
/* 57:   */  public HullDesc() {}
/* 58:   */  
/* 59:   */  public HullDesc(int flag, int vcount, ObjectArrayList<Vector3f> vertices)
/* 60:   */  {
/* 61:61 */    this(flag, vcount, vertices, 12);
/* 62:   */  }
/* 63:   */  
/* 64:   */  public HullDesc(int flag, int vcount, ObjectArrayList<Vector3f> vertices, int stride) {
/* 65:65 */    this.flags = flag;
/* 66:66 */    this.vcount = vcount;
/* 67:67 */    this.vertices = vertices;
/* 68:68 */    this.vertexStride = stride;
/* 69:69 */    this.normalEpsilon = 0.001F;
/* 70:70 */    this.maxVertices = 4096;
/* 71:   */  }
/* 72:   */  
/* 73:   */  public boolean hasHullFlag(int flag) {
/* 74:74 */    if ((this.flags & flag) != 0) {
/* 75:75 */      return true;
/* 76:   */    }
/* 77:77 */    return false;
/* 78:   */  }
/* 79:   */  
/* 80:   */  public void setHullFlag(int flag) {
/* 81:81 */    this.flags |= flag;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public void clearHullFlag(int flag) {
/* 85:85 */    this.flags &= (flag ^ 0xFFFFFFFF);
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullDesc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */