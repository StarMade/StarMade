/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import com.bulletphysics.linearmath.VectorUtil;
/*    */ import javax.vecmath.Tuple3f;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public abstract class VertexData
/*    */ {
/*    */   public abstract int getVertexCount();
/*    */ 
/*    */   public abstract int getIndexCount();
/*    */ 
/*    */   public abstract <T extends Tuple3f> T getVertex(int paramInt, T paramT);
/*    */ 
/*    */   public abstract void setVertex(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3);
/*    */ 
/*    */   public void setVertex(int idx, Tuple3f t)
/*    */   {
/* 46 */     setVertex(idx, t.x, t.y, t.z);
/*    */   }
/*    */ 
/*    */   public abstract int getIndex(int paramInt);
/*    */ 
/*    */   public void getTriangle(int firstIndex, Vector3f scale, Vector3f[] triangle) {
/* 52 */     for (int i = 0; i < 3; i++) {
/* 53 */       getVertex(getIndex(firstIndex + i), triangle[i]);
/* 54 */       VectorUtil.mul(triangle[i], triangle[i], scale);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.VertexData
 * JD-Core Version:    0.6.2
 */