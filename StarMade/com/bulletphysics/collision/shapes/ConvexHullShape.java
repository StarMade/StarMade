/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ConvexHullShape extends PolyhedralConvexShape
/*     */ {
/*  42 */   private final ObjectArrayList<Vector3f> points = new ObjectArrayList();
/*     */ 
/*     */   public ConvexHullShape(ObjectArrayList<Vector3f> points)
/*     */   {
/*  53 */     for (int i = 0; i < points.size(); i++) {
/*  54 */       this.points.add(new Vector3f((Vector3f)points.getQuick(i)));
/*     */     }
/*     */ 
/*  57 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/*  62 */     this.localScaling.set(scaling);
/*  63 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public void addPoint(Vector3f point) {
/*  67 */     this.points.add(new Vector3f(point));
/*  68 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<Vector3f> getPoints() {
/*  72 */     return this.points;
/*     */   }
/*     */ 
/*     */   public int getNumPoints() {
/*  76 */     return this.points.size();
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  81 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVec = out;
/*  82 */       supVec.set(0.0F, 0.0F, 0.0F);
/*  83 */       float maxDot = -1.0E+030F;
/*     */ 
/*  85 */       Vector3f vec = localStack.get$javax$vecmath$Vector3f(vec0);
/*  86 */       float lenSqr = vec.lengthSquared();
/*  87 */       if (lenSqr < 1.0E-004F) {
/*  88 */         vec.set(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       else {
/*  91 */         float rlen = 1.0F / (float)Math.sqrt(lenSqr);
/*  92 */         vec.scale(rlen);
/*     */       }
/*     */ 
/*  96 */       Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/*  97 */       for (int i = 0; i < this.points.size(); i++) {
/*  98 */         VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(i), this.localScaling);
/*     */ 
/* 100 */         float newDot = vec.dot(vtx);
/* 101 */         if (newDot > maxDot) {
/* 102 */           maxDot = newDot;
/* 103 */           supVec.set(vtx);
/*     */         }
/*     */       }
/* 106 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*     */   {
/* 115 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); float[] wcoords = new float[numVectors];
/*     */ 
/* 119 */       for (int i = 0; i < numVectors; i++)
/*     */       {
/* 121 */         wcoords[i] = -1.0E+030F;
/*     */       }
/*     */ 
/* 124 */       Vector3f vtx = localStack.get$javax$vecmath$Vector3f();
/* 125 */       for (int i = 0; i < this.points.size(); i++) {
/* 126 */         VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(i), this.localScaling);
/*     */ 
/* 128 */         for (int j = 0; j < numVectors; j++) {
/* 129 */           Vector3f vec = vectors[j];
/*     */ 
/* 131 */           float newDot = vec.dot(vtx);
/*     */ 
/* 133 */           if (newDot > wcoords[j])
/*     */           {
/* 135 */             supportVerticesOut[j].set(vtx);
/*     */ 
/* 137 */             wcoords[j] = newDot;
/*     */           }
/*     */         }
/*     */       }
/* 141 */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2) {
/* 145 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVertex = localGetSupportingVertexWithoutMargin(vec, out);
/*     */ 
/* 147 */       if (getMargin() != 0.0F) {
/* 148 */         Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/* 149 */         if (vecnorm.lengthSquared() < 1.421086E-014F) {
/* 150 */           vecnorm.set(-1.0F, -1.0F, -1.0F);
/*     */         }
/* 152 */         vecnorm.normalize();
/* 153 */         supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/*     */       }
/* 155 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public int getNumVertices()
/*     */   {
/* 164 */     return this.points.size();
/*     */   }
/*     */ 
/*     */   public int getNumEdges()
/*     */   {
/* 169 */     return this.points.size();
/*     */   }
/*     */ 
/*     */   public void getEdge(int i, Vector3f pa, Vector3f pb)
/*     */   {
/* 174 */     int index0 = i % this.points.size();
/* 175 */     int index1 = (i + 1) % this.points.size();
/* 176 */     VectorUtil.mul(pa, (Vector3f)this.points.getQuick(index0), this.localScaling);
/* 177 */     VectorUtil.mul(pb, (Vector3f)this.points.getQuick(index1), this.localScaling);
/*     */   }
/*     */ 
/*     */   public void getVertex(int i, Vector3f vtx)
/*     */   {
/* 182 */     VectorUtil.mul(vtx, (Vector3f)this.points.getQuick(i), this.localScaling);
/*     */   }
/*     */ 
/*     */   public int getNumPlanes()
/*     */   {
/* 187 */     return 0;
/*     */   }
/*     */ 
/*     */   public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i)
/*     */   {
/* 192 */     if (!$assertionsDisabled) throw new AssertionError();
/*     */   }
/*     */ 
/*     */   public boolean isInside(Vector3f pt, float tolerance)
/*     */   {
/* 197 */     if (!$assertionsDisabled) throw new AssertionError();
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 203 */     return BroadphaseNativeType.CONVEX_HULL_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 208 */     return "Convex";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConvexHullShape
 * JD-Core Version:    0.6.2
 */