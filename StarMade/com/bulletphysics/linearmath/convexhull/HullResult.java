/*    */ package com.bulletphysics.linearmath.convexhull;
/*    */ 
/*    */ import com.bulletphysics.util.IntArrayList;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class HullResult
/*    */ {
/* 42 */   public boolean polygons = true;
/*    */ 
/* 45 */   public int numOutputVertices = 0;
/*    */ 
/* 48 */   public final ObjectArrayList<Vector3f> outputVertices = new ObjectArrayList();
/*    */ 
/* 51 */   public int numFaces = 0;
/*    */ 
/* 54 */   public int numIndices = 0;
/*    */ 
/* 57 */   public final IntArrayList indices = new IntArrayList();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullResult
 * JD-Core Version:    0.6.2
 */