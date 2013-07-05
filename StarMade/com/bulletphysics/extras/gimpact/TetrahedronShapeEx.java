/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.BU_Simplex1to4;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ class TetrahedronShapeEx extends BU_Simplex1to4
/*    */ {
/*    */   public TetrahedronShapeEx()
/*    */   {
/* 41 */     this.numVertices = 4;
/* 42 */     for (int i = 0; i < this.numVertices; i++)
/* 43 */       this.vertices[i] = new Vector3f();
/*    */   }
/*    */ 
/*    */   public void setVertices(Vector3f v0, Vector3f v1, Vector3f v2, Vector3f v3)
/*    */   {
/* 48 */     this.vertices[0].set(v0);
/* 49 */     this.vertices[1].set(v1);
/* 50 */     this.vertices[2].set(v2);
/* 51 */     this.vertices[3].set(v3);
/* 52 */     recalcLocalAabb();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TetrahedronShapeEx
 * JD-Core Version:    0.6.2
 */