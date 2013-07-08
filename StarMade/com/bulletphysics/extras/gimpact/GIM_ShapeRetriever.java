package com.bulletphysics.extras.gimpact;

import com.bulletphysics.collision.shapes.CollisionShape;

class GIM_ShapeRetriever
{
  public GImpactShapeInterface gim_shape;
  public TriangleShapeEx trishape = new TriangleShapeEx();
  public TetrahedronShapeEx tetrashape = new TetrahedronShapeEx();
  public ChildShapeRetriever child_retriever = new ChildShapeRetriever();
  public TriangleShapeRetriever tri_retriever = new TriangleShapeRetriever();
  public TetraShapeRetriever tetra_retriever = new TetraShapeRetriever();
  public ChildShapeRetriever current_retriever;
  
  public GIM_ShapeRetriever(GImpactShapeInterface gim_shape)
  {
    this.gim_shape = gim_shape;
    if (gim_shape.needsRetrieveTriangles()) {
      this.current_retriever = this.tri_retriever;
    } else if (gim_shape.needsRetrieveTetrahedrons()) {
      this.current_retriever = this.tetra_retriever;
    } else {
      this.current_retriever = this.child_retriever;
    }
    this.current_retriever.parent = this;
  }
  
  public CollisionShape getChildShape(int index)
  {
    return this.current_retriever.getChildShape(index);
  }
  
  public static class TetraShapeRetriever
    extends GIM_ShapeRetriever.ChildShapeRetriever
  {
    public CollisionShape getChildShape(int index)
    {
      this.parent.gim_shape.getBulletTetrahedron(index, this.parent.tetrashape);
      return this.parent.tetrashape;
    }
  }
  
  public static class TriangleShapeRetriever
    extends GIM_ShapeRetriever.ChildShapeRetriever
  {
    public CollisionShape getChildShape(int index)
    {
      this.parent.gim_shape.getBulletTriangle(index, this.parent.trishape);
      return this.parent.trishape;
    }
  }
  
  public static class ChildShapeRetriever
  {
    public GIM_ShapeRetriever parent;
    
    public CollisionShape getChildShape(int index)
    {
      return this.parent.gim_shape.getChildShape(index);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.GIM_ShapeRetriever
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */