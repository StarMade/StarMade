/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class CylinderShape extends BoxShape
/*     */ {
/*     */   protected int upAxis;
/*     */ 
/*     */   public CylinderShape(Vector3f halfExtents)
/*     */   {
/*  45 */     super(halfExtents);
/*  46 */     this.upAxis = 1;
/*  47 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   protected CylinderShape(Vector3f halfExtents, boolean unused) {
/*  51 */     super(halfExtents);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/*  56 */     _PolyhedralConvexShape_getAabb(t, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   protected Vector3f cylinderLocalSupportX(Vector3f halfExtents, Vector3f v, Vector3f out) {
/*  60 */     return cylinderLocalSupport(halfExtents, v, 0, 1, 0, 2, out);
/*     */   }
/*     */ 
/*     */   protected Vector3f cylinderLocalSupportY(Vector3f halfExtents, Vector3f v, Vector3f out) {
/*  64 */     return cylinderLocalSupport(halfExtents, v, 1, 0, 1, 2, out);
/*     */   }
/*     */ 
/*     */   protected Vector3f cylinderLocalSupportZ(Vector3f halfExtents, Vector3f v, Vector3f out) {
/*  68 */     return cylinderLocalSupport(halfExtents, v, 2, 0, 2, 1, out);
/*     */   }
/*     */ 
/*     */   private Vector3f cylinderLocalSupport(Vector3f halfExtents, Vector3f v, int cylinderUpAxis, int XX, int YY, int ZZ, Vector3f out)
/*     */   {
/*  75 */     float radius = VectorUtil.getCoord(halfExtents, XX);
/*  76 */     float halfHeight = VectorUtil.getCoord(halfExtents, cylinderUpAxis);
/*     */ 
/*  80 */     float s = (float)Math.sqrt(VectorUtil.getCoord(v, XX) * VectorUtil.getCoord(v, XX) + VectorUtil.getCoord(v, ZZ) * VectorUtil.getCoord(v, ZZ));
/*  81 */     if (s != 0.0F) {
/*  82 */       float d = radius / s;
/*  83 */       VectorUtil.setCoord(out, XX, VectorUtil.getCoord(v, XX) * d);
/*  84 */       VectorUtil.setCoord(out, YY, VectorUtil.getCoord(v, YY) < 0.0F ? -halfHeight : halfHeight);
/*  85 */       VectorUtil.setCoord(out, ZZ, VectorUtil.getCoord(v, ZZ) * d);
/*  86 */       return out;
/*     */     }
/*     */ 
/*  89 */     VectorUtil.setCoord(out, XX, radius);
/*  90 */     VectorUtil.setCoord(out, YY, VectorUtil.getCoord(v, YY) < 0.0F ? -halfHeight : halfHeight);
/*  91 */     VectorUtil.setCoord(out, ZZ, 0.0F);
/*  92 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  98 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); return cylinderLocalSupportY(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vec, out); } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*     */   {
/* 103 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); for (int i = 0; i < numVectors; i++)
/* 104 */         cylinderLocalSupportY(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), vectors[i], supportVerticesOut[i]);
/*     */       return; } finally {
/* 106 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2) {
/* 110 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVertex = out;
/* 111 */       localGetSupportingVertexWithoutMargin(vec, supVertex);
/*     */ 
/* 113 */       if (getMargin() != 0.0F) {
/* 114 */         Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/* 115 */         if (vecnorm.lengthSquared() < 1.421086E-014F) {
/* 116 */           vecnorm.set(-1.0F, -1.0F, -1.0F);
/*     */         }
/* 118 */         vecnorm.normalize();
/* 119 */         supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/*     */       }
/* 121 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 126 */     return BroadphaseNativeType.CYLINDER_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public int getUpAxis() {
/* 130 */     return this.upAxis;
/*     */   }
/*     */ 
/*     */   public float getRadius() {
/* 134 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); return getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f()).x; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 139 */     return "CylinderY";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CylinderShape
 * JD-Core Version:    0.6.2
 */