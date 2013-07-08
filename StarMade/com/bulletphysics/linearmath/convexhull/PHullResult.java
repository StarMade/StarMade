/*  1:   */package com.bulletphysics.linearmath.convexhull;
/*  2:   */
/*  3:   */import com.bulletphysics.util.IntArrayList;
/*  4:   */import com.bulletphysics.util.ObjectArrayList;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 34:   */class PHullResult
/* 35:   */{
/* 36:36 */  public int vcount = 0;
/* 37:37 */  public int indexCount = 0;
/* 38:38 */  public int faceCount = 0;
/* 39:39 */  public ObjectArrayList<Vector3f> vertices = null;
/* 40:40 */  public IntArrayList indices = new IntArrayList();
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.PHullResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */