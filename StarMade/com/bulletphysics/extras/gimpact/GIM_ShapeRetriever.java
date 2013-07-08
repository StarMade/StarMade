/*  1:   */package com.bulletphysics.extras.gimpact;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.CollisionShape;
/*  4:   */
/* 36:   */class GIM_ShapeRetriever
/* 37:   */{
/* 38:   */  public GImpactShapeInterface gim_shape;
/* 39:39 */  public TriangleShapeEx trishape = new TriangleShapeEx();
/* 40:40 */  public TetrahedronShapeEx tetrashape = new TetrahedronShapeEx();
/* 41:   */  
/* 42:42 */  public ChildShapeRetriever child_retriever = new ChildShapeRetriever();
/* 43:43 */  public TriangleShapeRetriever tri_retriever = new TriangleShapeRetriever();
/* 44:44 */  public TetraShapeRetriever tetra_retriever = new TetraShapeRetriever();
/* 45:   */  public ChildShapeRetriever current_retriever;
/* 46:   */  
/* 47:   */  public GIM_ShapeRetriever(GImpactShapeInterface gim_shape) {
/* 48:48 */    this.gim_shape = gim_shape;
/* 49:   */    
/* 51:51 */    if (gim_shape.needsRetrieveTriangles()) {
/* 52:52 */      this.current_retriever = this.tri_retriever;
/* 53:   */    }
/* 54:54 */    else if (gim_shape.needsRetrieveTetrahedrons()) {
/* 55:55 */      this.current_retriever = this.tetra_retriever;
/* 56:   */    }
/* 57:   */    else {
/* 58:58 */      this.current_retriever = this.child_retriever;
/* 59:   */    }
/* 60:   */    
/* 61:61 */    this.current_retriever.parent = this;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public CollisionShape getChildShape(int index) {
/* 65:65 */    return this.current_retriever.getChildShape(index);
/* 66:   */  }
/* 67:   */  
/* 68:   */  public static class ChildShapeRetriever
/* 69:   */  {
/* 70:   */    public GIM_ShapeRetriever parent;
/* 71:   */    
/* 72:   */    public CollisionShape getChildShape(int index)
/* 73:   */    {
/* 74:74 */      return this.parent.gim_shape.getChildShape(index);
/* 75:   */    }
/* 76:   */  }
/* 77:   */  
/* 78:   */  public static class TriangleShapeRetriever extends GIM_ShapeRetriever.ChildShapeRetriever
/* 79:   */  {
/* 80:   */    public CollisionShape getChildShape(int index) {
/* 81:81 */      this.parent.gim_shape.getBulletTriangle(index, this.parent.trishape);
/* 82:82 */      return this.parent.trishape;
/* 83:   */    }
/* 84:   */  }
/* 85:   */  
/* 86:   */  public static class TetraShapeRetriever extends GIM_ShapeRetriever.ChildShapeRetriever
/* 87:   */  {
/* 88:   */    public CollisionShape getChildShape(int index) {
/* 89:89 */      this.parent.gim_shape.getBulletTetrahedron(index, this.parent.tetrashape);
/* 90:90 */      return this.parent.tetrashape;
/* 91:   */    }
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GIM_ShapeRetriever
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */