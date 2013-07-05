/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.StridingMeshInterface;
/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GImpactMeshShape extends GImpactShapeInterface
/*     */ {
/*  46 */   protected ObjectArrayList<GImpactMeshShapePart> mesh_parts = new ObjectArrayList();
/*     */ 
/*     */   public GImpactMeshShape(StridingMeshInterface meshInterface) {
/*  49 */     buildMeshParts(meshInterface);
/*     */   }
/*     */ 
/*     */   public int getMeshPartCount() {
/*  53 */     return this.mesh_parts.size();
/*     */   }
/*     */ 
/*     */   public GImpactMeshShapePart getMeshPart(int index) {
/*  57 */     return (GImpactMeshShapePart)this.mesh_parts.getQuick(index);
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/*  62 */     this.localScaling.set(scaling);
/*     */ 
/*  64 */     int i = this.mesh_parts.size();
/*  65 */     while (i-- != 0) {
/*  66 */       GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(i);
/*  67 */       part.setLocalScaling(scaling);
/*     */     }
/*     */ 
/*  70 */     this.needs_update = true;
/*     */   }
/*     */ 
/*     */   public void setMargin(float margin)
/*     */   {
/*  75 */     this.collisionMargin = margin;
/*     */ 
/*  77 */     int i = this.mesh_parts.size();
/*  78 */     while (i-- != 0) {
/*  79 */       GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(i);
/*  80 */       part.setMargin(margin);
/*     */     }
/*     */ 
/*  83 */     this.needs_update = true;
/*     */   }
/*     */ 
/*     */   public void postUpdate()
/*     */   {
/*  88 */     int i = this.mesh_parts.size();
/*  89 */     while (i-- != 0) {
/*  90 */       GImpactMeshShapePart part = (GImpactMeshShapePart)this.mesh_parts.getQuick(i);
/*  91 */       part.postUpdate();
/*     */     }
/*     */ 
/*  94 */     this.needs_update = true;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 100 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); inertia.set(0.0F, 0.0F, 0.0F);
/*     */ 
/* 102 */       int i = getMeshPartCount();
/* 103 */       float partmass = mass / i;
/*     */ 
/* 105 */       Vector3f partinertia = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 107 */       while (i-- != 0) {
/* 108 */         getMeshPart(i).calculateLocalInertia(partmass, partinertia);
/* 109 */         inertia.add(partinertia);
/*     */       }
/*     */ 
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 126 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   PrimitiveManagerBase getPrimitiveManager() {
/* 130 */     if (!$assertionsDisabled) throw new AssertionError();
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   public int getNumChildShapes()
/*     */   {
/* 136 */     if (!$assertionsDisabled) throw new AssertionError();
/* 137 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean childrenHasTransform()
/*     */   {
/* 142 */     if (!$assertionsDisabled) throw new AssertionError();
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean needsRetrieveTriangles()
/*     */   {
/* 148 */     if (!$assertionsDisabled) throw new AssertionError();
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean needsRetrieveTetrahedrons()
/*     */   {
/* 154 */     if (!$assertionsDisabled) throw new AssertionError();
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   public void getBulletTriangle(int prim_index, TriangleShapeEx triangle)
/*     */   {
/* 160 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   void getBulletTetrahedron(int prim_index, TetrahedronShapeEx tetrahedron)
/*     */   {
/* 165 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public void lockChildShapes()
/*     */   {
/* 170 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public void unlockChildShapes()
/*     */   {
/* 175 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public void getChildAabb(int child_index, Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 180 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public CollisionShape getChildShape(int index)
/*     */   {
/* 185 */     if (!$assertionsDisabled) throw new AssertionError();
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */   public Transform getChildTransform(int index)
/*     */   {
/* 191 */     if (!$assertionsDisabled) throw new AssertionError();
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */   public void setChildTransform(int index, Transform transform)
/*     */   {
/* 197 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   ShapeType getGImpactShapeType()
/*     */   {
/* 202 */     return ShapeType.TRIMESH_SHAPE;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 207 */     return "GImpactMesh";
/*     */   }
/*     */ 
/*     */   public void rayTest(Vector3f rayFrom, Vector3f rayTo, CollisionWorld.RayResultCallback resultCallback)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 216 */     int i = this.mesh_parts.size();
/* 217 */     while (i-- != 0)
/* 218 */       ((GImpactMeshShapePart)this.mesh_parts.getQuick(i)).processAllTriangles(callback, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   protected void buildMeshParts(StridingMeshInterface meshInterface)
/*     */   {
/* 223 */     for (int i = 0; i < meshInterface.getNumSubParts(); i++) {
/* 224 */       GImpactMeshShapePart newpart = new GImpactMeshShapePart(meshInterface, i);
/* 225 */       this.mesh_parts.add(newpart);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void calcLocalAABB()
/*     */   {
/* 231 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB tmpAABB = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*     */ 
/* 233 */       this.localAABB.invalidate();
/* 234 */       int i = this.mesh_parts.size();
/* 235 */       while (i-- != 0) {
/* 236 */         ((GImpactMeshShapePart)this.mesh_parts.getQuick(i)).updateBound();
/* 237 */         this.localAABB.merge(((GImpactMeshShapePart)this.mesh_parts.getQuick(i)).getLocalBox(tmpAABB));
/*     */       }return; } finally {
/* 239 */       localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactMeshShape
 * JD-Core Version:    0.6.2
 */