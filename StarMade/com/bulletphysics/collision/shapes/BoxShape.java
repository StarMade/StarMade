/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.ScalarUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class BoxShape extends PolyhedralConvexShape
/*     */ {
/*     */   public BoxShape(Vector3f boxHalfExtents)
/*     */   {
/*  47 */     Vector3f margin = new Vector3f(getMargin(), getMargin(), getMargin());
/*  48 */     VectorUtil.mul(this.implicitShapeDimensions, boxHalfExtents, this.localScaling);
/*  49 */     this.implicitShapeDimensions.sub(margin);
/*     */   }
/*     */ 
/*     */   public Vector3f getHalfExtentsWithMargin(Vector3f arg1) {
/*  53 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
/*  54 */       Vector3f margin = localStack.get$javax$vecmath$Vector3f();
/*  55 */       margin.set(getMargin(), getMargin(), getMargin());
/*  56 */       halfExtents.add(margin);
/*  57 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Vector3f getHalfExtentsWithoutMargin(Vector3f out) {
/*  61 */     out.set(this.implicitShapeDimensions);
/*  62 */     return out;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/*  67 */     return BroadphaseNativeType.BOX_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f vec, Vector3f out)
/*     */   {
/*  72 */     Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
/*     */ 
/*  74 */     float margin = getMargin();
/*  75 */     halfExtents.x += margin;
/*  76 */     halfExtents.y += margin;
/*  77 */     halfExtents.z += margin;
/*     */ 
/*  79 */     out.set(ScalarUtil.fsel(vec.x, halfExtents.x, -halfExtents.x), ScalarUtil.fsel(vec.y, halfExtents.y, -halfExtents.y), ScalarUtil.fsel(vec.z, halfExtents.z, -halfExtents.z));
/*     */ 
/*  83 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*     */   {
/*  88 */     Vector3f halfExtents = getHalfExtentsWithoutMargin(out);
/*     */ 
/*  90 */     out.set(ScalarUtil.fsel(vec.x, halfExtents.x, -halfExtents.x), ScalarUtil.fsel(vec.y, halfExtents.y, -halfExtents.y), ScalarUtil.fsel(vec.z, halfExtents.z, -halfExtents.z));
/*     */ 
/*  94 */     return out;
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] arg1, Vector3f[] arg2, int arg3)
/*     */   {
/*  99 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 101 */       for (int i = 0; i < numVectors; i++) {
/* 102 */         Vector3f vec = vectors[i];
/* 103 */         supportVerticesOut[i].set(ScalarUtil.fsel(vec.x, halfExtents.x, -halfExtents.x), ScalarUtil.fsel(vec.y, halfExtents.y, -halfExtents.y), ScalarUtil.fsel(vec.z, halfExtents.z, -halfExtents.z));
/*     */       }
/*     */       return;
/*     */     } finally {
/* 107 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setMargin(float arg1)
/*     */   {
/* 112 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f oldMargin = localStack.get$javax$vecmath$Vector3f();
/* 113 */       oldMargin.set(getMargin(), getMargin(), getMargin());
/* 114 */       Vector3f implicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 115 */       implicitShapeDimensionsWithMargin.add(this.implicitShapeDimensions, oldMargin);
/*     */ 
/* 117 */       super.setMargin(margin);
/* 118 */       Vector3f newMargin = localStack.get$javax$vecmath$Vector3f();
/* 119 */       newMargin.set(getMargin(), getMargin(), getMargin());
/* 120 */       this.implicitShapeDimensions.sub(implicitShapeDimensionsWithMargin, newMargin);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f arg1) {
/* 125 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f oldMargin = localStack.get$javax$vecmath$Vector3f();
/* 126 */       oldMargin.set(getMargin(), getMargin(), getMargin());
/* 127 */       Vector3f implicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 128 */       implicitShapeDimensionsWithMargin.add(this.implicitShapeDimensions, oldMargin);
/* 129 */       Vector3f unScaledImplicitShapeDimensionsWithMargin = localStack.get$javax$vecmath$Vector3f();
/* 130 */       VectorUtil.div(unScaledImplicitShapeDimensionsWithMargin, implicitShapeDimensionsWithMargin, this.localScaling);
/*     */ 
/* 132 */       super.setLocalScaling(scaling);
/*     */ 
/* 134 */       VectorUtil.mul(this.implicitShapeDimensions, unScaledImplicitShapeDimensionsWithMargin, this.localScaling);
/* 135 */       this.implicitShapeDimensions.sub(oldMargin);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3) {
/* 140 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); AabbUtil2.transformAabb(getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f()), getMargin(), t, aabbMin, aabbMax);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 146 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = getHalfExtentsWithMargin(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 148 */       float lx = 2.0F * halfExtents.x;
/* 149 */       float ly = 2.0F * halfExtents.y;
/* 150 */       float lz = 2.0F * halfExtents.z;
/*     */ 
/* 152 */       inertia.set(mass / 12.0F * (ly * ly + lz * lz), mass / 12.0F * (lx * lx + lz * lz), mass / 12.0F * (lx * lx + ly * ly));
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getPlane(Vector3f arg1, Vector3f arg2, int arg3)
/*     */   {
/* 160 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Vector4f(); Vector4f plane = localStack.get$javax$vecmath$Vector4f();
/* 161 */       getPlaneEquation(plane, i);
/* 162 */       planeNormal.set(plane.x, plane.y, plane.z);
/* 163 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 164 */       tmp.negate(planeNormal);
/* 165 */       localGetSupportingVertex(tmp, planeSupport);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 166 */       .Stack tmp80_78 = localStack; tmp80_78.pop$javax$vecmath$Vector3f(); tmp80_78.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public int getNumPlanes() {
/* 170 */     return 6;
/*     */   }
/*     */ 
/*     */   public int getNumVertices()
/*     */   {
/* 175 */     return 8;
/*     */   }
/*     */ 
/*     */   public int getNumEdges()
/*     */   {
/* 180 */     return 12;
/*     */   }
/*     */ 
/*     */   public void getVertex(int arg1, Vector3f arg2)
/*     */   {
/* 185 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 187 */       vtx.set(halfExtents.x * (1 - (i & 0x1)) - halfExtents.x * (i & 0x1), halfExtents.y * (1 - ((i & 0x2) >> 1)) - halfExtents.y * ((i & 0x2) >> 1), halfExtents.z * (1 - ((i & 0x4) >> 2)) - halfExtents.z * ((i & 0x4) >> 2));
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void getPlaneEquation(Vector4f arg1, int arg2) {
/* 193 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 195 */       switch (i) {
/*     */       case 0:
/* 197 */         plane.set(1.0F, 0.0F, 0.0F, -halfExtents.x);
/* 198 */         break;
/*     */       case 1:
/* 200 */         plane.set(-1.0F, 0.0F, 0.0F, -halfExtents.x);
/* 201 */         break;
/*     */       case 2:
/* 203 */         plane.set(0.0F, 1.0F, 0.0F, -halfExtents.y);
/* 204 */         break;
/*     */       case 3:
/* 206 */         plane.set(0.0F, -1.0F, 0.0F, -halfExtents.y);
/* 207 */         break;
/*     */       case 4:
/* 209 */         plane.set(0.0F, 0.0F, 1.0F, -halfExtents.z);
/* 210 */         break;
/*     */       case 5:
/* 212 */         plane.set(0.0F, 0.0F, -1.0F, -halfExtents.z);
/* 213 */         break;
/*     */       default:
/* 215 */         if (!$assertionsDisabled) throw new AssertionError(); break;
/*     */       }return; } finally {
/* 217 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getEdge(int i, Vector3f pa, Vector3f pb) {
/* 221 */     int edgeVert0 = 0;
/* 222 */     int edgeVert1 = 0;
/*     */ 
/* 224 */     switch (i) {
/*     */     case 0:
/* 226 */       edgeVert0 = 0;
/* 227 */       edgeVert1 = 1;
/* 228 */       break;
/*     */     case 1:
/* 230 */       edgeVert0 = 0;
/* 231 */       edgeVert1 = 2;
/* 232 */       break;
/*     */     case 2:
/* 234 */       edgeVert0 = 1;
/* 235 */       edgeVert1 = 3;
/*     */ 
/* 237 */       break;
/*     */     case 3:
/* 239 */       edgeVert0 = 2;
/* 240 */       edgeVert1 = 3;
/* 241 */       break;
/*     */     case 4:
/* 243 */       edgeVert0 = 0;
/* 244 */       edgeVert1 = 4;
/* 245 */       break;
/*     */     case 5:
/* 247 */       edgeVert0 = 1;
/* 248 */       edgeVert1 = 5;
/*     */ 
/* 250 */       break;
/*     */     case 6:
/* 252 */       edgeVert0 = 2;
/* 253 */       edgeVert1 = 6;
/* 254 */       break;
/*     */     case 7:
/* 256 */       edgeVert0 = 3;
/* 257 */       edgeVert1 = 7;
/* 258 */       break;
/*     */     case 8:
/* 260 */       edgeVert0 = 4;
/* 261 */       edgeVert1 = 5;
/* 262 */       break;
/*     */     case 9:
/* 264 */       edgeVert0 = 4;
/* 265 */       edgeVert1 = 6;
/* 266 */       break;
/*     */     case 10:
/* 268 */       edgeVert0 = 5;
/* 269 */       edgeVert1 = 7;
/* 270 */       break;
/*     */     case 11:
/* 272 */       edgeVert0 = 6;
/* 273 */       edgeVert1 = 7;
/* 274 */       break;
/*     */     default:
/* 276 */       if (!$assertionsDisabled) throw new AssertionError();
/*     */       break;
/*     */     }
/* 279 */     getVertex(edgeVert0, pa);
/* 280 */     getVertex(edgeVert1, pb);
/*     */   }
/*     */ 
/*     */   public boolean isInside(Vector3f arg1, float arg2)
/*     */   {
/* 285 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f halfExtents = getHalfExtentsWithoutMargin(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 289 */       return (pt.x <= halfExtents.x + tolerance) && (pt.x >= -halfExtents.x - tolerance) && (pt.y <= halfExtents.y + tolerance) && (pt.y >= -halfExtents.y - tolerance) && (pt.z <= halfExtents.z + tolerance) && (pt.z >= -halfExtents.z - tolerance);
/*     */     }
/*     */     finally
/*     */     {
/* 297 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 302 */     return "Box";
/*     */   }
/*     */ 
/*     */   public int getNumPreferredPenetrationDirections()
/*     */   {
/* 307 */     return 6;
/*     */   }
/*     */ 
/*     */   public void getPreferredPenetrationDirection(int index, Vector3f penetrationVector)
/*     */   {
/* 312 */     switch (index) {
/*     */     case 0:
/* 314 */       penetrationVector.set(1.0F, 0.0F, 0.0F);
/* 315 */       break;
/*     */     case 1:
/* 317 */       penetrationVector.set(-1.0F, 0.0F, 0.0F);
/* 318 */       break;
/*     */     case 2:
/* 320 */       penetrationVector.set(0.0F, 1.0F, 0.0F);
/* 321 */       break;
/*     */     case 3:
/* 323 */       penetrationVector.set(0.0F, -1.0F, 0.0F);
/* 324 */       break;
/*     */     case 4:
/* 326 */       penetrationVector.set(0.0F, 0.0F, 1.0F);
/* 327 */       break;
/*     */     case 5:
/* 329 */       penetrationVector.set(0.0F, 0.0F, -1.0F);
/* 330 */       break;
/*     */     default:
/* 332 */       if (!$assertionsDisabled) throw new AssertionError();
/*     */       break;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BoxShape
 * JD-Core Version:    0.6.2
 */