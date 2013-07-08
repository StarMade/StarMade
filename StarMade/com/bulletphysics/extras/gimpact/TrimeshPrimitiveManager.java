package com.bulletphysics.extras.gimpact;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.shapes.StridingMeshInterface;
import com.bulletphysics.collision.shapes.VertexData;
import com.bulletphysics.linearmath.VectorUtil;
import javax.vecmath.Vector3f;

class TrimeshPrimitiveManager
  extends PrimitiveManagerBase
{
  public float margin;
  public StridingMeshInterface meshInterface;
  public final Vector3f scale = new Vector3f();
  public int part;
  public int lock_count;
  private final int[] tmpIndices = new int[3];
  private VertexData vertexData;
  
  public TrimeshPrimitiveManager()
  {
    this.meshInterface = null;
    this.part = 0;
    this.margin = 0.01F;
    this.scale.set(1.0F, 1.0F, 1.0F);
    this.lock_count = 0;
  }
  
  public TrimeshPrimitiveManager(TrimeshPrimitiveManager manager)
  {
    this.meshInterface = manager.meshInterface;
    this.part = manager.part;
    this.margin = manager.margin;
    this.scale.set(manager.scale);
    this.lock_count = 0;
  }
  
  public TrimeshPrimitiveManager(StridingMeshInterface meshInterface, int part)
  {
    this.meshInterface = meshInterface;
    this.part = part;
    this.meshInterface.getScaling(this.scale);
    this.margin = 0.1F;
    this.lock_count = 0;
  }
  
  public void lock()
  {
    if (this.lock_count > 0)
    {
      this.lock_count += 1;
      return;
    }
    this.vertexData = this.meshInterface.getLockedReadOnlyVertexIndexBase(this.part);
    this.lock_count = 1;
  }
  
  public void unlock()
  {
    if (this.lock_count == 0) {
      return;
    }
    if (this.lock_count > 1)
    {
      this.lock_count -= 1;
      return;
    }
    this.meshInterface.unLockReadOnlyVertexBase(this.part);
    this.vertexData = null;
    this.lock_count = 0;
  }
  
  public boolean is_trimesh()
  {
    return true;
  }
  
  public int get_primitive_count()
  {
    return this.vertexData.getIndexCount() / 3;
  }
  
  public int get_vertex_count()
  {
    return this.vertexData.getVertexCount();
  }
  
  public void get_indices(int face_index, int[] out)
  {
    out[0] = this.vertexData.getIndex(face_index * 3 + 0);
    out[1] = this.vertexData.getIndex(face_index * 3 + 1);
    out[2] = this.vertexData.getIndex(face_index * 3 + 2);
  }
  
  public void get_vertex(int vertex_index, Vector3f vertex)
  {
    this.vertexData.getVertex(vertex_index, vertex);
    VectorUtil.mul(vertex, vertex, this.scale);
  }
  
  public void get_primitive_box(int arg1, BoxCollision.AABB arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      PrimitiveTriangle triangle = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
      get_primitive_triangle(prim_index, triangle);
      primbox.calc_from_triangle_margin(triangle.vertices[0], triangle.vertices[1], triangle.vertices[2], triangle.margin);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
    }
  }
  
  public void get_primitive_triangle(int prim_index, PrimitiveTriangle triangle)
  {
    get_indices(prim_index, this.tmpIndices);
    get_vertex(this.tmpIndices[0], triangle.vertices[0]);
    get_vertex(this.tmpIndices[1], triangle.vertices[1]);
    get_vertex(this.tmpIndices[2], triangle.vertices[2]);
    triangle.margin = this.margin;
  }
  
  public void get_bullet_triangle(int prim_index, TriangleShapeEx triangle)
  {
    get_indices(prim_index, this.tmpIndices);
    get_vertex(this.tmpIndices[0], triangle.vertices1[0]);
    get_vertex(this.tmpIndices[1], triangle.vertices1[1]);
    get_vertex(this.tmpIndices[2], triangle.vertices1[2]);
    triangle.setMargin(this.margin);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.TrimeshPrimitiveManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */