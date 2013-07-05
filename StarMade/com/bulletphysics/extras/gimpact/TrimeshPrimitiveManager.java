/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.StridingMeshInterface;
/*     */ import com.bulletphysics.collision.shapes.VertexData;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class TrimeshPrimitiveManager extends PrimitiveManagerBase
/*     */ {
/*     */   public float margin;
/*     */   public StridingMeshInterface meshInterface;
/*  45 */   public final Vector3f scale = new Vector3f();
/*     */   public int part;
/*     */   public int lock_count;
/*  49 */   private final int[] tmpIndices = new int[3];
/*     */   private VertexData vertexData;
/*     */ 
/*     */   public TrimeshPrimitiveManager()
/*     */   {
/*  54 */     this.meshInterface = null;
/*  55 */     this.part = 0;
/*  56 */     this.margin = 0.01F;
/*  57 */     this.scale.set(1.0F, 1.0F, 1.0F);
/*  58 */     this.lock_count = 0;
/*     */   }
/*     */ 
/*     */   public TrimeshPrimitiveManager(TrimeshPrimitiveManager manager) {
/*  62 */     this.meshInterface = manager.meshInterface;
/*  63 */     this.part = manager.part;
/*  64 */     this.margin = manager.margin;
/*  65 */     this.scale.set(manager.scale);
/*  66 */     this.lock_count = 0;
/*     */   }
/*     */ 
/*     */   public TrimeshPrimitiveManager(StridingMeshInterface meshInterface, int part) {
/*  70 */     this.meshInterface = meshInterface;
/*  71 */     this.part = part;
/*  72 */     this.meshInterface.getScaling(this.scale);
/*  73 */     this.margin = 0.1F;
/*  74 */     this.lock_count = 0;
/*     */   }
/*     */ 
/*     */   public void lock() {
/*  78 */     if (this.lock_count > 0) {
/*  79 */       this.lock_count += 1;
/*  80 */       return;
/*     */     }
/*  82 */     this.vertexData = this.meshInterface.getLockedReadOnlyVertexIndexBase(this.part);
/*     */ 
/*  84 */     this.lock_count = 1;
/*     */   }
/*     */ 
/*     */   public void unlock() {
/*  88 */     if (this.lock_count == 0) {
/*  89 */       return;
/*     */     }
/*  91 */     if (this.lock_count > 1) {
/*  92 */       this.lock_count -= 1;
/*  93 */       return;
/*     */     }
/*  95 */     this.meshInterface.unLockReadOnlyVertexBase(this.part);
/*  96 */     this.vertexData = null;
/*  97 */     this.lock_count = 0;
/*     */   }
/*     */ 
/*     */   public boolean is_trimesh()
/*     */   {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   public int get_primitive_count()
/*     */   {
/* 107 */     return this.vertexData.getIndexCount() / 3;
/*     */   }
/*     */ 
/*     */   public int get_vertex_count() {
/* 111 */     return this.vertexData.getVertexCount();
/*     */   }
/*     */ 
/*     */   public void get_indices(int face_index, int[] out) {
/* 115 */     out[0] = this.vertexData.getIndex(face_index * 3 + 0);
/* 116 */     out[1] = this.vertexData.getIndex(face_index * 3 + 1);
/* 117 */     out[2] = this.vertexData.getIndex(face_index * 3 + 2);
/*     */   }
/*     */ 
/*     */   public void get_vertex(int vertex_index, Vector3f vertex) {
/* 121 */     this.vertexData.getVertex(vertex_index, vertex);
/* 122 */     VectorUtil.mul(vertex, vertex, this.scale);
/*     */   }
/*     */ 
/*     */   public void get_primitive_box(int arg1, BoxCollision.AABB arg2)
/*     */   {
/* 127 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle(); PrimitiveTriangle triangle = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 128 */       get_primitive_triangle(prim_index, triangle);
/* 129 */       primbox.calc_from_triangle_margin(triangle.vertices[0], triangle.vertices[1], triangle.vertices[2], triangle.margin);
/*     */       return; } finally { localStack.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void get_primitive_triangle(int prim_index, PrimitiveTriangle triangle) {
/* 136 */     get_indices(prim_index, this.tmpIndices);
/* 137 */     get_vertex(this.tmpIndices[0], triangle.vertices[0]);
/* 138 */     get_vertex(this.tmpIndices[1], triangle.vertices[1]);
/* 139 */     get_vertex(this.tmpIndices[2], triangle.vertices[2]);
/* 140 */     triangle.margin = this.margin;
/*     */   }
/*     */ 
/*     */   public void get_bullet_triangle(int prim_index, TriangleShapeEx triangle) {
/* 144 */     get_indices(prim_index, this.tmpIndices);
/* 145 */     get_vertex(this.tmpIndices[0], triangle.vertices1[0]);
/* 146 */     get_vertex(this.tmpIndices[1], triangle.vertices1[1]);
/* 147 */     get_vertex(this.tmpIndices[2], triangle.vertices1[2]);
/* 148 */     triangle.setMargin(this.margin);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TrimeshPrimitiveManager
 * JD-Core Version:    0.6.2
 */