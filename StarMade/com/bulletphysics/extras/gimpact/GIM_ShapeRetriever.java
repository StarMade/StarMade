/*    */ package com.bulletphysics.extras.gimpact;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.CollisionShape;
/*    */ 
/*    */ class GIM_ShapeRetriever
/*    */ {
/*    */   public GImpactShapeInterface gim_shape;
/* 39 */   public TriangleShapeEx trishape = new TriangleShapeEx();
/* 40 */   public TetrahedronShapeEx tetrashape = new TetrahedronShapeEx();
/*    */ 
/* 42 */   public ChildShapeRetriever child_retriever = new ChildShapeRetriever();
/* 43 */   public TriangleShapeRetriever tri_retriever = new TriangleShapeRetriever();
/* 44 */   public TetraShapeRetriever tetra_retriever = new TetraShapeRetriever();
/*    */   public ChildShapeRetriever current_retriever;
/*    */ 
/*    */   public GIM_ShapeRetriever(GImpactShapeInterface gim_shape)
/*    */   {
/* 48 */     this.gim_shape = gim_shape;
/*    */ 
/* 51 */     if (gim_shape.needsRetrieveTriangles()) {
/* 52 */       this.current_retriever = this.tri_retriever;
/*    */     }
/* 54 */     else if (gim_shape.needsRetrieveTetrahedrons()) {
/* 55 */       this.current_retriever = this.tetra_retriever;
/*    */     }
/*    */     else {
/* 58 */       this.current_retriever = this.child_retriever;
/*    */     }
/*    */ 
/* 61 */     this.current_retriever.parent = this;
/*    */   }
/*    */ 
/*    */   public CollisionShape getChildShape(int index) {
/* 65 */     return this.current_retriever.getChildShape(index);
/*    */   }
/*    */ 
/*    */   public static class TetraShapeRetriever extends GIM_ShapeRetriever.ChildShapeRetriever
/*    */   {
/*    */     public CollisionShape getChildShape(int index)
/*    */     {
/* 89 */       this.parent.gim_shape.getBulletTetrahedron(index, this.parent.tetrashape);
/* 90 */       return this.parent.tetrashape;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static class TriangleShapeRetriever extends GIM_ShapeRetriever.ChildShapeRetriever
/*    */   {
/*    */     public CollisionShape getChildShape(int index)
/*    */     {
/* 81 */       this.parent.gim_shape.getBulletTriangle(index, this.parent.trishape);
/* 82 */       return this.parent.trishape;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static class ChildShapeRetriever
/*    */   {
/*    */     public GIM_ShapeRetriever parent;
/*    */ 
/*    */     public CollisionShape getChildShape(int index)
/*    */     {
/* 74 */       return this.parent.gim_shape.getChildShape(index);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GIM_ShapeRetriever
 * JD-Core Version:    0.6.2
 */