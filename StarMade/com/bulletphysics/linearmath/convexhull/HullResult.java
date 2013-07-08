/*  1:   */package com.bulletphysics.linearmath.convexhull;
/*  2:   */
/*  3:   */import com.bulletphysics.util.IntArrayList;
/*  4:   */import com.bulletphysics.util.ObjectArrayList;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */
/* 40:   */public class HullResult
/* 41:   */{
/* 42:42 */  public boolean polygons = true;
/* 43:   */  
/* 45:45 */  public int numOutputVertices = 0;
/* 46:   */  
/* 48:48 */  public final ObjectArrayList<Vector3f> outputVertices = new ObjectArrayList();
/* 49:   */  
/* 51:51 */  public int numFaces = 0;
/* 52:   */  
/* 54:54 */  public int numIndices = 0;
/* 55:   */  
/* 57:57 */  public final IntArrayList indices = new IntArrayList();
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullResult
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */