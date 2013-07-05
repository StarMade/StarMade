/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class CapsuleShape extends ConvexInternalShape
/*     */ {
/*     */   protected int upAxis;
/*     */ 
/*     */   CapsuleShape()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CapsuleShape(float radius, float height)
/*     */   {
/*  58 */     this.upAxis = 1;
/*  59 */     this.implicitShapeDimensions.set(radius, 0.5F * height, radius);
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  64 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVec = out;
/*  65 */       supVec.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  67 */       float maxDot = -1.0E+030F;
/*     */ 
/*  69 */       Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
/*  70 */       float lenSqr = vec.lengthSquared();
/*  71 */       if (lenSqr < 1.0E-004F) {
/*  72 */         vec.set(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       else {
/*  75 */         float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/*  76 */         vec.scale(rlen);
/*     */       }
/*     */ 
/*  79 */       Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  82 */       float radius = getRadius();
/*     */ 
/*  84 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  85 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*  86 */       Vector3f pos = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  89 */       pos.set(0.0F, 0.0F, 0.0F);
/*  90 */       VectorUtil.setCoord(pos, getUpAxis(), getHalfHeight());
/*     */ 
/*  92 */       VectorUtil.mul(tmp1, vec, this.localScaling);
/*  93 */       tmp1.scale(radius);
/*  94 */       tmp2.scale(getMargin(), vec);
/*  95 */       vtx.add(pos, tmp1);
/*  96 */       vtx.sub(tmp2);
/*  97 */       float newDot = vec.dot(vtx);
/*  98 */       if (newDot > maxDot) {
/*  99 */         maxDot = newDot;
/* 100 */         supVec.set(vtx);
/*     */       }
/*     */ 
/* 104 */       pos.set(0.0F, 0.0F, 0.0F);
/* 105 */       VectorUtil.setCoord(pos, getUpAxis(), -getHalfHeight());
/*     */ 
/* 107 */       VectorUtil.mul(tmp1, vec, this.localScaling);
/* 108 */       tmp1.scale(radius);
/* 109 */       tmp2.scale(getMargin(), vec);
/* 110 */       vtx.add(pos, tmp1);
/* 111 */       vtx.sub(tmp2);
/* 112 */       newDot = vec.dot(vtx);
/* 113 */       if (newDot > maxDot) {
/* 114 */         maxDot = newDot;
/* 115 */         supVec.set(vtx);
/*     */       }
/*     */ 
/* 119 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*     */   {
/* 125 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 132 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/* 133 */       ident.setIdentity();
/*     */ 
/* 135 */       float radius = getRadius();
/*     */ 
/* 137 */       Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 138 */       halfExtents.set(radius, radius, radius);
/* 139 */       VectorUtil.setCoord(halfExtents, getUpAxis(), radius + getHalfHeight());
/*     */ 
/* 141 */       float margin = 0.04F;
/*     */ 
/* 143 */       float lx = 2.0F * (halfExtents.x + margin);
/* 144 */       float ly = 2.0F * (halfExtents.y + margin);
/* 145 */       float lz = 2.0F * (halfExtents.z + margin);
/* 146 */       float x2 = lx * lx;
/* 147 */       float y2 = ly * ly;
/* 148 */       float z2 = lz * lz;
/* 149 */       float scaledmass = mass * 0.08333333F;
/*     */ 
/* 151 */       inertia.x = (scaledmass * (y2 + z2));
/* 152 */       inertia.y = (scaledmass * (x2 + z2));
/* 153 */       inertia.z = (scaledmass * (x2 + y2));
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 154 */       .Stack tmp179_177 = localStack; tmp179_177.pop$com$bulletphysics$linearmath$Transform(); tmp179_177.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType() {
/* 158 */     return BroadphaseNativeType.CAPSULE_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/* 163 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 165 */       Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 166 */       halfExtents.set(getRadius(), getRadius(), getRadius());
/* 167 */       VectorUtil.setCoord(halfExtents, this.upAxis, getRadius() + getHalfHeight());
/*     */ 
/* 169 */       halfExtents.x += getMargin();
/* 170 */       halfExtents.y += getMargin();
/* 171 */       halfExtents.z += getMargin();
/*     */ 
/* 173 */       Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f();
/* 174 */       abs_b.set(t.basis);
/* 175 */       MatrixUtil.absolute(abs_b);
/*     */ 
/* 177 */       Vector3f center = t.origin;
/* 178 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 180 */       abs_b.getRow(0, tmp);
/* 181 */       extent.x = tmp.dot(halfExtents);
/* 182 */       abs_b.getRow(1, tmp);
/* 183 */       extent.y = tmp.dot(halfExtents);
/* 184 */       abs_b.getRow(2, tmp);
/* 185 */       extent.z = tmp.dot(halfExtents);
/*     */ 
/* 187 */       aabbMin.sub(center, extent);
/* 188 */       aabbMax.add(center, extent);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 189 */       .Stack tmp227_225 = localStack; tmp227_225.pop$javax$vecmath$Vector3f(); tmp227_225.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 193 */     return "CapsuleShape";
/*     */   }
/*     */ 
/*     */   public int getUpAxis() {
/* 197 */     return this.upAxis;
/*     */   }
/*     */ 
/*     */   public float getRadius() {
/* 201 */     int radiusAxis = (this.upAxis + 2) % 3;
/* 202 */     return VectorUtil.getCoord(this.implicitShapeDimensions, radiusAxis);
/*     */   }
/*     */ 
/*     */   public float getHalfHeight() {
/* 206 */     return VectorUtil.getCoord(this.implicitShapeDimensions, this.upAxis);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CapsuleShape
 * JD-Core Version:    0.6.2
 */