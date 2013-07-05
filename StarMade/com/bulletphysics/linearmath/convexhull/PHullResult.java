/*    */ package com.bulletphysics.linearmath.convexhull;
/*    */ 
/*    */ import com.bulletphysics.util.IntArrayList;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ class PHullResult
/*    */ {
/* 36 */   public int vcount = 0;
/* 37 */   public int indexCount = 0;
/* 38 */   public int faceCount = 0;
/* 39 */   public ObjectArrayList<Vector3f> vertices = null;
/* 40 */   public IntArrayList indices = new IntArrayList();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.PHullResult
 * JD-Core Version:    0.6.2
 */