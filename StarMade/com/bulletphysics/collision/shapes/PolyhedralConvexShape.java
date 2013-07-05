/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class PolyhedralConvexShape extends ConvexInternalShape
/*     */ {
/*  39 */   private static Vector3f[] _directions = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F), new Vector3f(0.0F, 0.0F, -1.0F) };
/*     */ 
/*  48 */   private static Vector3f[] _supporting = { new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(0.0F, 0.0F, 0.0F) };
/*     */ 
/*  57 */   protected final Vector3f localAabbMin = new Vector3f(1.0F, 1.0F, 1.0F);
/*  58 */   protected final Vector3f localAabbMax = new Vector3f(-1.0F, -1.0F, -1.0F);
/*  59 */   protected boolean isLocalAabbValid = false;
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  67 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVec = out;
/*  68 */       supVec.set(0.0F, 0.0F, 0.0F);
/*     */ 
/*  70 */       float maxDot = -1.0E+030F;
/*     */ 
/*  72 */       Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
/*  73 */       float lenSqr = vec.lengthSquared();
/*  74 */       if (lenSqr < 1.0E-004F) {
/*  75 */         vec.set(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       else {
/*  78 */         float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/*  79 */         vec.scale(rlen);
/*     */       }
/*     */ 
/*  82 */       Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  85 */       for (int i = 0; i < getNumVertices(); i++) {
/*  86 */         getVertex(i, vtx);
/*  87 */         float newDot = vec.dot(vtx);
/*  88 */         if (newDot > maxDot) {
/*  89 */           maxDot = newDot;
/*  90 */           supVec = vtx;
/*     */         }
/*     */       }
/*     */ 
/*  94 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*     */   {
/* 101 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 106 */       float[] wcoords = new float[numVectors];
/*     */ 
/* 108 */       for (int i = 0; i < numVectors; i++)
/*     */       {
/* 111 */         wcoords[i] = -1.0E+030F;
/*     */       }
/*     */ 
/* 114 */       for (int j = 0; j < numVectors; j++) {
/* 115 */         Vector3f vec = vectors[j];
/*     */ 
/* 117 */         for (i = 0; i < getNumVertices(); i++) {
/* 118 */           getVertex(i, vtx);
/* 119 */           float newDot = vec.dot(vtx);
/*     */ 
/* 121 */           if (newDot > wcoords[j])
/*     */           {
/* 123 */             supportVerticesOut[j].set(vtx);
/*     */ 
/* 125 */             wcoords[j] = newDot;
/*     */           }
/*     */         }
/*     */       }
/* 129 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 135 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); float margin = getMargin();
/*     */ 
/* 137 */       Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/* 138 */       ident.setIdentity();
/* 139 */       Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/* 140 */       getAabb(ident, aabbMin, aabbMax);
/*     */ 
/* 142 */       Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 143 */       halfExtents.sub(aabbMax, aabbMin);
/* 144 */       halfExtents.scale(0.5F);
/*     */ 
/* 146 */       float lx = 2.0F * (halfExtents.x + margin);
/* 147 */       float ly = 2.0F * (halfExtents.y + margin);
/* 148 */       float lz = 2.0F * (halfExtents.z + margin);
/* 149 */       float x2 = lx * lx;
/* 150 */       float y2 = ly * ly;
/* 151 */       float z2 = lz * lz;
/* 152 */       float scaledmass = mass * 0.08333333F;
/*     */ 
/* 154 */       inertia.set(y2 + z2, x2 + z2, x2 + y2);
/* 155 */       inertia.scale(scaledmass);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 156 */       .Stack tmp175_173 = localStack; tmp175_173.pop$com$bulletphysics$linearmath$Transform(); tmp175_173.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private void getNonvirtualAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax, float margin) {
/* 160 */     assert (this.isLocalAabbValid);
/*     */ 
/* 162 */     AabbUtil2.transformAabb(this.localAabbMin, this.localAabbMax, margin, trans, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 167 */     getNonvirtualAabb(trans, aabbMin, aabbMax, getMargin());
/*     */   }
/*     */ 
/*     */   protected final void _PolyhedralConvexShape_getAabb(Transform trans, Vector3f aabbMin, Vector3f aabbMax) {
/* 171 */     getNonvirtualAabb(trans, aabbMin, aabbMax, getMargin());
/*     */   }
/*     */ 
/*     */   public void recalcLocalAabb() {
/* 175 */     this.isLocalAabbValid = true;
/*     */ 
/* 179 */     batchedUnitVectorGetSupportingVertexWithoutMargin(_directions, _supporting, 6);
/*     */ 
/* 181 */     for (int i = 0; i < 3; i++) {
/* 182 */       VectorUtil.setCoord(this.localAabbMax, i, VectorUtil.getCoord(_supporting[i], i) + this.collisionMargin);
/* 183 */       VectorUtil.setCoord(this.localAabbMin, i, VectorUtil.getCoord(_supporting[(i + 3)], i) - this.collisionMargin);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/* 202 */     super.setLocalScaling(scaling);
/* 203 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public abstract int getNumVertices();
/*     */ 
/*     */   public abstract int getNumEdges();
/*     */ 
/*     */   public abstract void getEdge(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2);
/*     */ 
/*     */   public abstract void getVertex(int paramInt, Vector3f paramVector3f);
/*     */ 
/*     */   public abstract int getNumPlanes();
/*     */ 
/*     */   public abstract void getPlane(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt);
/*     */ 
/*     */   public abstract boolean isInside(Vector3f paramVector3f, float paramFloat);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.PolyhedralConvexShape
 * JD-Core Version:    0.6.2
 */