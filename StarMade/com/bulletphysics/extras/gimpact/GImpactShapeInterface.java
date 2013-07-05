/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.RayResultCallback;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.ConcaveShape;
/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class GImpactShapeInterface extends ConcaveShape
/*     */ {
/*  47 */   protected BoxCollision.AABB localAABB = new BoxCollision.AABB();
/*     */   protected boolean needs_update;
/*  49 */   protected final Vector3f localScaling = new Vector3f();
/*  50 */   GImpactBvh box_set = new GImpactBvh();
/*     */ 
/*     */   public GImpactShapeInterface() {
/*  53 */     this.localAABB.invalidate();
/*  54 */     this.needs_update = true;
/*  55 */     this.localScaling.set(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   public void updateBound()
/*     */   {
/*  68 */     if (!this.needs_update) {
/*  69 */       return;
/*     */     }
/*  71 */     calcLocalAABB();
/*  72 */     this.needs_update = false;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  81 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB transformedbox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB(this.localAABB);
/*  82 */       transformedbox.appy_transform(t);
/*  83 */       aabbMin.set(transformedbox.min);
/*  84 */       aabbMax.set(transformedbox.max);
/*     */       return; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void postUpdate()
/*     */   {
/*  91 */     this.needs_update = true;
/*     */   }
/*     */ 
/*     */   public BoxCollision.AABB getLocalBox(BoxCollision.AABB out)
/*     */   {
/*  98 */     out.set(this.localAABB);
/*  99 */     return out;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 104 */     return BroadphaseNativeType.GIMPACT_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/* 112 */     this.localScaling.set(scaling);
/* 113 */     postUpdate();
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 118 */     out.set(this.localScaling);
/* 119 */     return out;
/*     */   }
/*     */ 
/*     */   public void setMargin(float margin)
/*     */   {
/* 124 */     this.collisionMargin = margin;
/* 125 */     int i = getNumChildShapes();
/* 126 */     while (i-- != 0) {
/* 127 */       CollisionShape child = getChildShape(i);
/* 128 */       child.setMargin(margin);
/*     */     }
/*     */ 
/* 131 */     this.needs_update = true;
/*     */   }
/*     */ 
/*     */   abstract ShapeType getGImpactShapeType();
/*     */ 
/*     */   GImpactBvh getBoxSet()
/*     */   {
/* 140 */     return this.box_set;
/*     */   }
/*     */ 
/*     */   public boolean hasBoxSet()
/*     */   {
/* 147 */     if (this.box_set.getNodeCount() == 0) {
/* 148 */       return false;
/*     */     }
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   abstract PrimitiveManagerBase getPrimitiveManager();
/*     */ 
/*     */   public abstract int getNumChildShapes();
/*     */ 
/*     */   public abstract boolean childrenHasTransform();
/*     */ 
/*     */   public abstract boolean needsRetrieveTriangles();
/*     */ 
/*     */   public abstract boolean needsRetrieveTetrahedrons();
/*     */ 
/*     */   public abstract void getBulletTriangle(int paramInt, TriangleShapeEx paramTriangleShapeEx);
/*     */ 
/*     */   abstract void getBulletTetrahedron(int paramInt, TetrahedronShapeEx paramTetrahedronShapeEx);
/*     */ 
/*     */   public void lockChildShapes()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void unlockChildShapes()
/*     */   {
/*     */   }
/*     */ 
/*     */   void getPrimitiveTriangle(int index, PrimitiveTriangle triangle)
/*     */   {
/* 195 */     getPrimitiveManager().get_primitive_triangle(index, triangle);
/*     */   }
/*     */ 
/*     */   protected void calcLocalAABB()
/*     */   {
/* 202 */     lockChildShapes();
/* 203 */     if (this.box_set.getNodeCount() == 0) {
/* 204 */       this.box_set.buildSet();
/*     */     }
/*     */     else {
/* 207 */       this.box_set.update();
/*     */     }
/* 209 */     unlockChildShapes();
/*     */ 
/* 211 */     this.box_set.getGlobalBox(this.localAABB);
/*     */   }
/*     */ 
/*     */   public void getChildAabb(int arg1, Transform arg2, Vector3f arg3, Vector3f arg4)
/*     */   {
/* 218 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); BoxCollision.AABB child_aabb = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/* 219 */       getPrimitiveManager().get_primitive_box(child_index, child_aabb);
/* 220 */       child_aabb.appy_transform(t);
/* 221 */       aabbMin.set(child_aabb.min);
/* 222 */       aabbMax.set(child_aabb.max);
/*     */       return; } finally { localStack.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); } throw finally;
/*     */   }
/*     */ 
/*     */   public abstract CollisionShape getChildShape(int paramInt);
/*     */ 
/*     */   public abstract Transform getChildTransform(int paramInt);
/*     */ 
/*     */   public abstract void setChildTransform(int paramInt, Transform paramTransform);
/*     */ 
/*     */   public void rayTest(Vector3f rayFrom, Vector3f rayTo, CollisionWorld.RayResultCallback resultCallback)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.GImpactShapeInterface
 * JD-Core Version:    0.6.2
 */