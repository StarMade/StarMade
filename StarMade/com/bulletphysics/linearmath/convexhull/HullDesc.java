/*    */ package com.bulletphysics.linearmath.convexhull;
/*    */ 
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class HullDesc
/*    */ {
/* 37 */   public int flags = HullFlags.DEFAULT;
/*    */ 
/* 40 */   public int vcount = 0;
/*    */   public ObjectArrayList<Vector3f> vertices;
/* 46 */   int vertexStride = 12;
/*    */ 
/* 49 */   public float normalEpsilon = 0.001F;
/*    */ 
/* 52 */   public int maxVertices = 4096;
/*    */ 
/* 55 */   public int maxFaces = 4096;
/*    */ 
/*    */   public HullDesc() {
/*    */   }
/*    */ 
/*    */   public HullDesc(int flag, int vcount, ObjectArrayList<Vector3f> vertices) {
/* 61 */     this(flag, vcount, vertices, 12);
/*    */   }
/*    */ 
/*    */   public HullDesc(int flag, int vcount, ObjectArrayList<Vector3f> vertices, int stride) {
/* 65 */     this.flags = flag;
/* 66 */     this.vcount = vcount;
/* 67 */     this.vertices = vertices;
/* 68 */     this.vertexStride = stride;
/* 69 */     this.normalEpsilon = 0.001F;
/* 70 */     this.maxVertices = 4096;
/*    */   }
/*    */ 
/*    */   public boolean hasHullFlag(int flag) {
/* 74 */     if ((this.flags & flag) != 0) {
/* 75 */       return true;
/*    */     }
/* 77 */     return false;
/*    */   }
/*    */ 
/*    */   public void setHullFlag(int flag) {
/* 81 */     this.flags |= flag;
/*    */   }
/*    */ 
/*    */   public void clearHullFlag(int flag) {
/* 85 */     this.flags &= (flag ^ 0xFFFFFFFF);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.HullDesc
 * JD-Core Version:    0.6.2
 */