/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class TriangleIndexVertexArray extends StridingMeshInterface
/*     */ {
/*  40 */   protected ObjectArrayList<IndexedMesh> indexedMeshes = new ObjectArrayList();
/*     */ 
/*  42 */   private ByteBufferVertexData data = new ByteBufferVertexData();
/*     */ 
/*     */   public TriangleIndexVertexArray()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TriangleIndexVertexArray(int numTriangles, ByteBuffer triangleIndexBase, int triangleIndexStride, int numVertices, ByteBuffer vertexBase, int vertexStride)
/*     */   {
/*  51 */     IndexedMesh mesh = new IndexedMesh();
/*     */ 
/*  53 */     mesh.numTriangles = numTriangles;
/*  54 */     mesh.triangleIndexBase = triangleIndexBase;
/*  55 */     mesh.triangleIndexStride = triangleIndexStride;
/*  56 */     mesh.numVertices = numVertices;
/*  57 */     mesh.vertexBase = vertexBase;
/*  58 */     mesh.vertexStride = vertexStride;
/*     */ 
/*  60 */     addIndexedMesh(mesh);
/*     */   }
/*     */ 
/*     */   public void addIndexedMesh(IndexedMesh mesh) {
/*  64 */     addIndexedMesh(mesh, ScalarType.INTEGER);
/*     */   }
/*     */ 
/*     */   public void addIndexedMesh(IndexedMesh mesh, ScalarType indexType) {
/*  68 */     this.indexedMeshes.add(mesh);
/*  69 */     ((IndexedMesh)this.indexedMeshes.getQuick(this.indexedMeshes.size() - 1)).indexType = indexType;
/*     */   }
/*     */ 
/*     */   public VertexData getLockedVertexIndexBase(int subpart)
/*     */   {
/*  74 */     assert (subpart < getNumSubParts());
/*     */ 
/*  76 */     IndexedMesh mesh = (IndexedMesh)this.indexedMeshes.getQuick(subpart);
/*     */ 
/*  78 */     this.data.vertexCount = mesh.numVertices;
/*  79 */     this.data.vertexData = mesh.vertexBase;
/*     */ 
/*  83 */     this.data.vertexType = ScalarType.FLOAT;
/*     */ 
/*  85 */     this.data.vertexStride = mesh.vertexStride;
/*     */ 
/*  87 */     this.data.indexCount = (mesh.numTriangles * 3);
/*     */ 
/*  89 */     this.data.indexData = mesh.triangleIndexBase;
/*  90 */     this.data.indexStride = (mesh.triangleIndexStride / 3);
/*  91 */     this.data.indexType = mesh.indexType;
/*  92 */     return this.data;
/*     */   }
/*     */ 
/*     */   public VertexData getLockedReadOnlyVertexIndexBase(int subpart)
/*     */   {
/*  97 */     return getLockedVertexIndexBase(subpart);
/*     */   }
/*     */ 
/*     */   public void unLockVertexBase(int subpart)
/*     */   {
/* 106 */     this.data.vertexData = null;
/* 107 */     this.data.indexData = null;
/*     */   }
/*     */ 
/*     */   public void unLockReadOnlyVertexBase(int subpart)
/*     */   {
/* 112 */     unLockVertexBase(subpart);
/*     */   }
/*     */ 
/*     */   public int getNumSubParts()
/*     */   {
/* 121 */     return this.indexedMeshes.size();
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<IndexedMesh> getIndexedMeshArray() {
/* 125 */     return this.indexedMeshes;
/*     */   }
/*     */ 
/*     */   public void preallocateVertices(int numverts)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void preallocateIndices(int numindices)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleIndexVertexArray
 * JD-Core Version:    0.6.2
 */