/*   1:    */package com.bulletphysics.extras.gimpact;
/*   2:    */
/*   3:    */import com.bulletphysics..Stack;
/*   4:    */import com.bulletphysics.collision.shapes.StridingMeshInterface;
/*   5:    */import com.bulletphysics.collision.shapes.VertexData;
/*   6:    */import com.bulletphysics.linearmath.VectorUtil;
/*   7:    */import javax.vecmath.Vector3f;
/*   8:    */
/*  40:    */class TrimeshPrimitiveManager
/*  41:    */  extends PrimitiveManagerBase
/*  42:    */{
/*  43:    */  public float margin;
/*  44:    */  public StridingMeshInterface meshInterface;
/*  45: 45 */  public final Vector3f scale = new Vector3f();
/*  46:    */  
/*  47:    */  public int part;
/*  48:    */  public int lock_count;
/*  49: 49 */  private final int[] tmpIndices = new int[3];
/*  50:    */  private VertexData vertexData;
/*  51:    */  
/*  52:    */  public TrimeshPrimitiveManager()
/*  53:    */  {
/*  54: 54 */    this.meshInterface = null;
/*  55: 55 */    this.part = 0;
/*  56: 56 */    this.margin = 0.01F;
/*  57: 57 */    this.scale.set(1.0F, 1.0F, 1.0F);
/*  58: 58 */    this.lock_count = 0;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public TrimeshPrimitiveManager(TrimeshPrimitiveManager manager) {
/*  62: 62 */    this.meshInterface = manager.meshInterface;
/*  63: 63 */    this.part = manager.part;
/*  64: 64 */    this.margin = manager.margin;
/*  65: 65 */    this.scale.set(manager.scale);
/*  66: 66 */    this.lock_count = 0;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public TrimeshPrimitiveManager(StridingMeshInterface meshInterface, int part) {
/*  70: 70 */    this.meshInterface = meshInterface;
/*  71: 71 */    this.part = part;
/*  72: 72 */    this.meshInterface.getScaling(this.scale);
/*  73: 73 */    this.margin = 0.1F;
/*  74: 74 */    this.lock_count = 0;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void lock() {
/*  78: 78 */    if (this.lock_count > 0) {
/*  79: 79 */      this.lock_count += 1;
/*  80: 80 */      return;
/*  81:    */    }
/*  82: 82 */    this.vertexData = this.meshInterface.getLockedReadOnlyVertexIndexBase(this.part);
/*  83:    */    
/*  84: 84 */    this.lock_count = 1;
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void unlock() {
/*  88: 88 */    if (this.lock_count == 0) {
/*  89: 89 */      return;
/*  90:    */    }
/*  91: 91 */    if (this.lock_count > 1) {
/*  92: 92 */      this.lock_count -= 1;
/*  93: 93 */      return;
/*  94:    */    }
/*  95: 95 */    this.meshInterface.unLockReadOnlyVertexBase(this.part);
/*  96: 96 */    this.vertexData = null;
/*  97: 97 */    this.lock_count = 0;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public boolean is_trimesh()
/* 101:    */  {
/* 102:102 */    return true;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public int get_primitive_count()
/* 106:    */  {
/* 107:107 */    return this.vertexData.getIndexCount() / 3;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public int get_vertex_count() {
/* 111:111 */    return this.vertexData.getVertexCount();
/* 112:    */  }
/* 113:    */  
/* 114:    */  public void get_indices(int face_index, int[] out) {
/* 115:115 */    out[0] = this.vertexData.getIndex(face_index * 3 + 0);
/* 116:116 */    out[1] = this.vertexData.getIndex(face_index * 3 + 1);
/* 117:117 */    out[2] = this.vertexData.getIndex(face_index * 3 + 2);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void get_vertex(int vertex_index, Vector3f vertex) {
/* 121:121 */    this.vertexData.getVertex(vertex_index, vertex);
/* 122:122 */    VectorUtil.mul(vertex, vertex, this.scale);
/* 123:    */  }
/* 124:    */  
/* 125:    */  public void get_primitive_box(int arg1, BoxCollision.AABB arg2)
/* 126:    */  {
/* 127:127 */    .Stack localStack = .Stack.get(); try { localStack.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle();PrimitiveTriangle triangle = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 128:128 */      get_primitive_triangle(prim_index, triangle);
/* 129:129 */      primbox.calc_from_triangle_margin(triangle.vertices[0], triangle.vertices[1], triangle.vertices[2], triangle.margin);
/* 130:    */    }
/* 131:    */    finally {
/* 132:132 */      localStack.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 133:    */    }
/* 134:    */  }
/* 135:    */  
/* 136:136 */  public void get_primitive_triangle(int prim_index, PrimitiveTriangle triangle) { get_indices(prim_index, this.tmpIndices);
/* 137:137 */    get_vertex(this.tmpIndices[0], triangle.vertices[0]);
/* 138:138 */    get_vertex(this.tmpIndices[1], triangle.vertices[1]);
/* 139:139 */    get_vertex(this.tmpIndices[2], triangle.vertices[2]);
/* 140:140 */    triangle.margin = this.margin;
/* 141:    */  }
/* 142:    */  
/* 143:    */  public void get_bullet_triangle(int prim_index, TriangleShapeEx triangle) {
/* 144:144 */    get_indices(prim_index, this.tmpIndices);
/* 145:145 */    get_vertex(this.tmpIndices[0], triangle.vertices1[0]);
/* 146:146 */    get_vertex(this.tmpIndices[1], triangle.vertices1[1]);
/* 147:147 */    get_vertex(this.tmpIndices[2], triangle.vertices1[2]);
/* 148:148 */    triangle.setMargin(this.margin);
/* 149:    */  }
/* 150:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TrimeshPrimitiveManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */