/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class TriangleShape extends PolyhedralConvexShape
/*     */ {
/*  39 */   public final Vector3f[] vertices1 = { new Vector3f(), new Vector3f(), new Vector3f() };
/*     */ 
/*     */   public TriangleShape()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TriangleShape(Vector3f p0, Vector3f p1, Vector3f p2) {
/*  46 */     this.vertices1[0].set(p0);
/*  47 */     this.vertices1[1].set(p1);
/*  48 */     this.vertices1[2].set(p2);
/*     */   }
/*     */ 
/*     */   public void init(Vector3f p0, Vector3f p1, Vector3f p2)
/*     */   {
/*  53 */     this.vertices1[0].set(p0);
/*  54 */     this.vertices1[1].set(p1);
/*  55 */     this.vertices1[2].set(p2);
/*     */   }
/*     */ 
/*     */   public int getNumVertices()
/*     */   {
/*  60 */     return 3;
/*     */   }
/*     */ 
/*     */   public Vector3f getVertexPtr(int index) {
/*  64 */     return this.vertices1[index];
/*     */   }
/*     */ 
/*     */   public void getVertex(int index, Vector3f vert)
/*     */   {
/*  69 */     vert.set(this.vertices1[index]);
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/*  74 */     return BroadphaseNativeType.TRIANGLE_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public int getNumEdges()
/*     */   {
/*  79 */     return 3;
/*     */   }
/*     */ 
/*     */   public void getEdge(int i, Vector3f pa, Vector3f pb)
/*     */   {
/*  84 */     getVertex(i, pa);
/*  85 */     getVertex((i + 1) % 3, pb);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/*  91 */     getAabbSlow(t, aabbMin, aabbMax);
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f arg1, Vector3f arg2)
/*     */   {
/*  96 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f dots = localStack.get$javax$vecmath$Vector3f();
/*  97 */       dots.set(dir.dot(this.vertices1[0]), dir.dot(this.vertices1[1]), dir.dot(this.vertices1[2]));
/*  98 */       out.set(this.vertices1[com.bulletphysics.linearmath.VectorUtil.maxAxis(dots)]);
/*  99 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*     */   {
/* 104 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f dots = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 106 */       for (int i = 0; i < numVectors; i++) {
/* 107 */         Vector3f dir = vectors[i];
/* 108 */         dots.set(dir.dot(this.vertices1[0]), dir.dot(this.vertices1[1]), dir.dot(this.vertices1[2]));
/* 109 */         supportVerticesOut[i].set(this.vertices1[com.bulletphysics.linearmath.VectorUtil.maxAxis(dots)]);
/*     */       }return; } finally {
/* 111 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i) {
/* 115 */     getPlaneEquation(i, planeNormal, planeSupport);
/*     */   }
/*     */ 
/*     */   public int getNumPlanes()
/*     */   {
/* 120 */     return 1;
/*     */   }
/*     */ 
/*     */   public void calcNormal(Vector3f arg1) {
/* 124 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 125 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 127 */       tmp1.sub(this.vertices1[1], this.vertices1[0]);
/* 128 */       tmp2.sub(this.vertices1[2], this.vertices1[0]);
/*     */ 
/* 130 */       normal.cross(tmp1, tmp2);
/* 131 */       normal.normalize();
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void getPlaneEquation(int i, Vector3f planeNormal, Vector3f planeSupport) {
/* 135 */     calcNormal(planeNormal);
/* 136 */     planeSupport.set(this.vertices1[0]);
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*     */   {
/* 141 */     if (!$assertionsDisabled) throw new AssertionError();
/* 142 */     inertia.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public boolean isInside(Vector3f arg1, float arg2)
/*     */   {
/* 147 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/* 148 */       calcNormal(normal);
/*     */ 
/* 150 */       float dist = pt.dot(normal);
/* 151 */       float planeconst = this.vertices1[0].dot(normal);
/* 152 */       dist -= planeconst;
/* 153 */       if ((dist >= -tolerance) && (dist <= tolerance))
/*     */       {
/* 156 */         for (int i = 0; i < 3; i++) {
/* 157 */           Vector3f pa = localStack.get$javax$vecmath$Vector3f(); Vector3f pb = localStack.get$javax$vecmath$Vector3f();
/* 158 */           getEdge(i, pa, pb);
/* 159 */           Vector3f edge = localStack.get$javax$vecmath$Vector3f();
/* 160 */           edge.sub(pb, pa);
/* 161 */           Vector3f edgeNormal = localStack.get$javax$vecmath$Vector3f();
/* 162 */           edgeNormal.cross(edge, normal);
/* 163 */           edgeNormal.normalize();
/* 164 */           dist = pt.dot(edgeNormal);
/* 165 */           float edgeConst = pa.dot(edgeNormal);
/* 166 */           dist -= edgeConst;
/* 167 */           if (dist < -tolerance) {
/* 168 */             return false;
/*     */           }
/*     */         }
/*     */ 
/* 172 */         return true;
/*     */       }
/*     */ 
/* 175 */       return false; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 180 */     return "Triangle";
/*     */   }
/*     */ 
/*     */   public int getNumPreferredPenetrationDirections()
/*     */   {
/* 185 */     return 2;
/*     */   }
/*     */ 
/*     */   public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/*     */   {
/* 190 */     calcNormal(penetrationVector);
/* 191 */     if (index != 0)
/* 192 */       penetrationVector.scale(-1.0F);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.TriangleShape
 * JD-Core Version:    0.6.2
 */