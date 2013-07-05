/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.StridingMeshInterface;
/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.IntArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GImpactMeshShapePart extends GImpactShapeInterface
/*     */ {
/*  53 */   TrimeshPrimitiveManager primitive_manager = new TrimeshPrimitiveManager();
/*     */ 
/*  55 */   private final IntArrayList collided = new IntArrayList();
/*     */ 
/*     */   public GImpactMeshShapePart() {
/*  58 */     this.box_set.setPrimitiveManager(this.primitive_manager);
/*     */   }
/*     */ 
/*     */   public GImpactMeshShapePart(StridingMeshInterface meshInterface, int part) {
/*  62 */     this.primitive_manager.meshInterface = meshInterface;
/*  63 */     this.primitive_manager.part = part;
/*  64 */     this.box_set.setPrimitiveManager(this.primitive_manager);
/*     */   }
/*     */ 
/*     */   public boolean childrenHasTransform()
/*     */   {
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public void lockChildShapes()
/*     */   {
/*  74 */     TrimeshPrimitiveManager dummymanager = (TrimeshPrimitiveManager)this.box_set.getPrimitiveManager();
/*  75 */     dummymanager.lock();
/*     */   }
/*     */ 
/*     */   public void unlockChildShapes()
/*     */   {
/*  80 */     TrimeshPrimitiveManager dummymanager = (TrimeshPrimitiveManager)this.box_set.getPrimitiveManager();
/*  81 */     dummymanager.unlock();
/*     */   }
/*     */ 
/*     */   public int getNumChildShapes()
/*     */   {
/*  86 */     return this.primitive_manager.get_primitive_count();
/*     */   }
/*     */ 
/*     */   public CollisionShape getChildShape(int index)
/*     */   {
/*  91 */     if (!$assertionsDisabled) throw new AssertionError();
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   public Transform getChildTransform(int index)
/*     */   {
/*  97 */     if (!$assertionsDisabled) throw new AssertionError();
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   public void setChildTransform(int index, Transform transform)
/*     */   {
/* 103 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   PrimitiveManagerBase getPrimitiveManager()
/*     */   {
/* 108 */     return this.primitive_manager;
/*     */   }
/*     */ 
/*     */   TrimeshPrimitiveManager getTrimeshPrimitiveManager() {
/* 112 */     return this.primitive_manager;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 117 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); lockChildShapes();
/*     */ 
/* 121 */       inertia.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 123 */       int i = getVertexCount();
/* 124 */       float pointmass = mass / i;
/*     */ 
/* 126 */       Vector3f pointintertia = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 128 */       while (i-- != 0) {
/* 129 */         getVertex(i, pointintertia);
/* 130 */         GImpactMassUtil.get_point_inertia(pointintertia, pointmass, pointintertia);
/* 131 */         inertia.add(pointintertia);
/*     */       }
/*     */ 
/* 150 */       unlockChildShapes();
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 151 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 155 */     return "GImpactMeshShapePart";
/*     */   }
/*     */ 
/*     */   ShapeType getGImpactShapeType()
/*     */   {
/* 160 */     return ShapeType.TRIMESH_SHAPE_PART;
/*     */   }
/*     */ 
/*     */   public boolean needsRetrieveTriangles()
/*     */   {
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean needsRetrieveTetrahedrons()
/*     */   {
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */   public void getBulletTriangle(int prim_index, TriangleShapeEx triangle)
/*     */   {
/* 175 */     this.primitive_manager.get_bullet_triangle(prim_index, triangle);
/*     */   }
/*     */ 
/*     */   void getBulletTetrahedron(int prim_index, TetrahedronShapeEx tetrahedron)
/*     */   {
/* 180 */     if (!$assertionsDisabled) throw new AssertionError(); 
/*     */   }
/*     */ 
/*     */   public int getVertexCount()
/*     */   {
/* 184 */     return this.primitive_manager.get_vertex_count();
/*     */   }
/*     */ 
/*     */   public void getVertex(int vertex_index, Vector3f vertex) {
/* 188 */     this.primitive_manager.get_vertex(vertex_index, vertex);
/*     */   }
/*     */ 
/*     */   public void setMargin(float margin)
/*     */   {
/* 193 */     this.primitive_manager.margin = margin;
/* 194 */     postUpdate();
/*     */   }
/*     */ 
/*     */   public float getMargin()
/*     */   {
/* 199 */     return this.primitive_manager.margin;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/* 204 */     this.primitive_manager.scale.set(scaling);
/* 205 */     postUpdate();
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 210 */     out.set(this.primitive_manager.scale);
/* 211 */     return out;
/*     */   }
/*     */ 
/*     */   public int getPart() {
/* 215 */     return this.primitive_manager.part;
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/* 220 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); tmp7_5.push$com$bulletphysics$extras$gimpact$PrimitiveTriangle(); lockChildShapes();
/* 221 */       BoxCollision.AABB box = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 222 */       box.min.set(aabbMin);
/* 223 */       box.max.set(aabbMax);
/*     */ 
/* 225 */       this.collided.clear();
/* 226 */       this.box_set.boxQuery(box, this.collided);
/*     */ 
/* 228 */       if (this.collided.size() == 0) {
/* 229 */         unlockChildShapes();
/* 230 */         return;
/*     */       }
/*     */ 
/* 233 */       int part = getPart();
/* 234 */       PrimitiveTriangle triangle = localStack.get$com$bulletphysics$extras$gimpact$PrimitiveTriangle();
/* 235 */       int i = this.collided.size();
/* 236 */       while (i-- != 0) {
/* 237 */         getPrimitiveTriangle(this.collided.get(i), triangle);
/* 238 */         callback.processTriangle(triangle.vertices, part, this.collided.get(i));
/*     */       }
/* 240 */       unlockChildShapes();
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 241 */       .Stack tmp172_170 = localStack; tmp172_170.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); tmp172_170.pop$com$bulletphysics$extras$gimpact$PrimitiveTriangle(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMeshShapePart
 * JD-Core Version:    0.6.2
 */