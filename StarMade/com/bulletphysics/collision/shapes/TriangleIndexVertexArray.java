package com.bulletphysics.collision.shapes;

import com.bulletphysics.util.ObjectArrayList;
import java.nio.ByteBuffer;

public class TriangleIndexVertexArray
  extends StridingMeshInterface
{
  protected ObjectArrayList<IndexedMesh> indexedMeshes = new ObjectArrayList();
  private ByteBufferVertexData data = new ByteBufferVertexData();
  
  public TriangleIndexVertexArray() {}
  
  public TriangleIndexVertexArray(int numTriangles, ByteBuffer triangleIndexBase, int triangleIndexStride, int numVertices, ByteBuffer vertexBase, int vertexStride)
  {
    IndexedMesh mesh = new IndexedMesh();
    mesh.numTriangles = numTriangles;
    mesh.triangleIndexBase = triangleIndexBase;
    mesh.triangleIndexStride = triangleIndexStride;
    mesh.numVertices = numVertices;
    mesh.vertexBase = vertexBase;
    mesh.vertexStride = vertexStride;
    addIndexedMesh(mesh);
  }
  
  public void addIndexedMesh(IndexedMesh mesh)
  {
    addIndexedMesh(mesh, ScalarType.INTEGER);
  }
  
  public void addIndexedMesh(IndexedMesh mesh, ScalarType indexType)
  {
    this.indexedMeshes.add(mesh);
    ((IndexedMesh)this.indexedMeshes.getQuick(this.indexedMeshes.size() - 1)).indexType = indexType;
  }
  
  public VertexData getLockedVertexIndexBase(int subpart)
  {
    assert (subpart < getNumSubParts());
    IndexedMesh mesh = (IndexedMesh)this.indexedMeshes.getQuick(subpart);
    this.data.vertexCount = mesh.numVertices;
    this.data.vertexData = mesh.vertexBase;
    this.data.vertexType = ScalarType.FLOAT;
    this.data.vertexStride = mesh.vertexStride;
    this.data.indexCount = (mesh.numTriangles * 3);
    this.data.indexData = mesh.triangleIndexBase;
    this.data.indexStride = (mesh.triangleIndexStride / 3);
    this.data.indexType = mesh.indexType;
    return this.data;
  }
  
  public VertexData getLockedReadOnlyVertexIndexBase(int subpart)
  {
    return getLockedVertexIndexBase(subpart);
  }
  
  public void unLockVertexBase(int subpart)
  {
    this.data.vertexData = null;
    this.data.indexData = null;
  }
  
  public void unLockReadOnlyVertexBase(int subpart)
  {
    unLockVertexBase(subpart);
  }
  
  public int getNumSubParts()
  {
    return this.indexedMeshes.size();
  }
  
  public ObjectArrayList<IndexedMesh> getIndexedMeshArray()
  {
    return this.indexedMeshes;
  }
  
  public void preallocateVertices(int numverts) {}
  
  public void preallocateIndices(int numindices) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleIndexVertexArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */