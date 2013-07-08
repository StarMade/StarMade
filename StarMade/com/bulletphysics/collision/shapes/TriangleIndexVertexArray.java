/*   1:    */package com.bulletphysics.collision.shapes;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */
/*  37:    */public class TriangleIndexVertexArray
/*  38:    */  extends StridingMeshInterface
/*  39:    */{
/*  40: 40 */  protected ObjectArrayList<IndexedMesh> indexedMeshes = new ObjectArrayList();
/*  41:    */  
/*  42: 42 */  private ByteBufferVertexData data = new ByteBufferVertexData();
/*  43:    */  
/*  46:    */  public TriangleIndexVertexArray() {}
/*  47:    */  
/*  49:    */  public TriangleIndexVertexArray(int numTriangles, ByteBuffer triangleIndexBase, int triangleIndexStride, int numVertices, ByteBuffer vertexBase, int vertexStride)
/*  50:    */  {
/*  51: 51 */    IndexedMesh mesh = new IndexedMesh();
/*  52:    */    
/*  53: 53 */    mesh.numTriangles = numTriangles;
/*  54: 54 */    mesh.triangleIndexBase = triangleIndexBase;
/*  55: 55 */    mesh.triangleIndexStride = triangleIndexStride;
/*  56: 56 */    mesh.numVertices = numVertices;
/*  57: 57 */    mesh.vertexBase = vertexBase;
/*  58: 58 */    mesh.vertexStride = vertexStride;
/*  59:    */    
/*  60: 60 */    addIndexedMesh(mesh);
/*  61:    */  }
/*  62:    */  
/*  63:    */  public void addIndexedMesh(IndexedMesh mesh) {
/*  64: 64 */    addIndexedMesh(mesh, ScalarType.INTEGER);
/*  65:    */  }
/*  66:    */  
/*  67:    */  public void addIndexedMesh(IndexedMesh mesh, ScalarType indexType) {
/*  68: 68 */    this.indexedMeshes.add(mesh);
/*  69: 69 */    ((IndexedMesh)this.indexedMeshes.getQuick(this.indexedMeshes.size() - 1)).indexType = indexType;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public VertexData getLockedVertexIndexBase(int subpart)
/*  73:    */  {
/*  74: 74 */    assert (subpart < getNumSubParts());
/*  75:    */    
/*  76: 76 */    IndexedMesh mesh = (IndexedMesh)this.indexedMeshes.getQuick(subpart);
/*  77:    */    
/*  78: 78 */    this.data.vertexCount = mesh.numVertices;
/*  79: 79 */    this.data.vertexData = mesh.vertexBase;
/*  80:    */    
/*  83: 83 */    this.data.vertexType = ScalarType.FLOAT;
/*  84:    */    
/*  85: 85 */    this.data.vertexStride = mesh.vertexStride;
/*  86:    */    
/*  87: 87 */    this.data.indexCount = (mesh.numTriangles * 3);
/*  88:    */    
/*  89: 89 */    this.data.indexData = mesh.triangleIndexBase;
/*  90: 90 */    this.data.indexStride = (mesh.triangleIndexStride / 3);
/*  91: 91 */    this.data.indexType = mesh.indexType;
/*  92: 92 */    return this.data;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public VertexData getLockedReadOnlyVertexIndexBase(int subpart)
/*  96:    */  {
/*  97: 97 */    return getLockedVertexIndexBase(subpart);
/*  98:    */  }
/*  99:    */  
/* 104:    */  public void unLockVertexBase(int subpart)
/* 105:    */  {
/* 106:106 */    this.data.vertexData = null;
/* 107:107 */    this.data.indexData = null;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public void unLockReadOnlyVertexBase(int subpart)
/* 111:    */  {
/* 112:112 */    unLockVertexBase(subpart);
/* 113:    */  }
/* 114:    */  
/* 119:    */  public int getNumSubParts()
/* 120:    */  {
/* 121:121 */    return this.indexedMeshes.size();
/* 122:    */  }
/* 123:    */  
/* 124:    */  public ObjectArrayList<IndexedMesh> getIndexedMeshArray() {
/* 125:125 */    return this.indexedMeshes;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public void preallocateVertices(int numverts) {}
/* 129:    */  
/* 130:    */  public void preallocateIndices(int numindices) {}
/* 131:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleIndexVertexArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */