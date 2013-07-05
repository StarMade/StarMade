/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ConeShape extends ConvexInternalShape
/*     */ {
/*     */   private float sinAngle;
/*     */   private float radius;
/*     */   private float height;
/*  45 */   private int[] coneIndices = new int[3];
/*     */ 
/*     */   public ConeShape(float radius, float height) {
/*  48 */     this.radius = radius;
/*  49 */     this.height = height;
/*  50 */     setConeUpIndex(1);
/*  51 */     this.sinAngle = (radius / (float)Math.sqrt(this.radius * this.radius + this.height * this.height));
/*     */   }
/*     */ 
/*     */   public float getRadius() {
/*  55 */     return this.radius;
/*     */   }
/*     */ 
/*     */   public float getHeight() {
/*  59 */     return this.height;
/*     */   }
/*     */ 
/*     */   private Vector3f coneLocalSupport(Vector3f v, Vector3f out) {
/*  63 */     float halfHeight = this.height * 0.5F;
/*     */ 
/*  65 */     if (VectorUtil.getCoord(v, this.coneIndices[1]) > v.length() * this.sinAngle) {
/*  66 */       VectorUtil.setCoord(out, this.coneIndices[0], 0.0F);
/*  67 */       VectorUtil.setCoord(out, this.coneIndices[1], halfHeight);
/*  68 */       VectorUtil.setCoord(out, this.coneIndices[2], 0.0F);
/*  69 */       return out;
/*     */     }
/*     */ 
/*  72 */     float v0 = VectorUtil.getCoord(v, this.coneIndices[0]);
/*  73 */     float v2 = VectorUtil.getCoord(v, this.coneIndices[2]);
/*  74 */     float s = (float)Math.sqrt(v0 * v0 + v2 * v2);
/*  75 */     if (s > 1.192093E-007F) {
/*  76 */       float d = this.radius / s;
/*  77 */       VectorUtil.setCoord(out, this.coneIndices[0], VectorUtil.getCoord(v, this.coneIndices[0]) * d);
/*  78 */       VectorUtil.setCoord(out, this.coneIndices[1], -halfHeight);
/*  79 */       VectorUtil.setCoord(out, this.coneIndices[2], VectorUtil.getCoord(v, this.coneIndices[2]) * d);
/*  80 */       return out;
/*     */     }
/*  82 */     VectorUtil.setCoord(out, this.coneIndices[0], 0.0F);
/*  83 */     VectorUtil.setCoord(out, this.coneIndices[1], -halfHeight);
/*  84 */     VectorUtil.setCoord(out, this.coneIndices[2], 0.0F);
/*  85 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertexWithoutMargin(Vector3f vec, Vector3f out)
/*     */   {
/*  92 */     return coneLocalSupport(vec, out);
/*     */   }
/*     */ 
/*     */   public void batchedUnitVectorGetSupportingVertexWithoutMargin(Vector3f[] vectors, Vector3f[] supportVerticesOut, int numVectors)
/*     */   {
/*  97 */     for (int i = 0; i < numVectors; i++) {
/*  98 */       Vector3f vec = vectors[i];
/*  99 */       coneLocalSupport(vec, supportVerticesOut[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Vector3f localGetSupportingVertex(Vector3f arg1, Vector3f arg2)
/*     */   {
/* 105 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f supVertex = coneLocalSupport(vec, out);
/* 106 */       if (getMargin() != 0.0F) {
/* 107 */         Vector3f vecnorm = localStack.get$javax$vecmath$Vector3f(vec);
/* 108 */         if (vecnorm.lengthSquared() < 1.421086E-014F) {
/* 109 */           vecnorm.set(-1.0F, -1.0F, -1.0F);
/*     */         }
/* 111 */         vecnorm.normalize();
/* 112 */         supVertex.scaleAdd(getMargin(), vecnorm, supVertex);
/*     */       }
/* 114 */       return supVertex; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 119 */     return BroadphaseNativeType.CONE_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 124 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform identity = localStack.get$com$bulletphysics$linearmath$Transform();
/* 125 */       identity.setIdentity();
/* 126 */       Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/* 127 */       getAabb(identity, aabbMin, aabbMax);
/*     */ 
/* 129 */       Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 130 */       halfExtents.sub(aabbMax, aabbMin);
/* 131 */       halfExtents.scale(0.5F);
/*     */ 
/* 133 */       float margin = getMargin();
/*     */ 
/* 135 */       float lx = 2.0F * (halfExtents.x + margin);
/* 136 */       float ly = 2.0F * (halfExtents.y + margin);
/* 137 */       float lz = 2.0F * (halfExtents.z + margin);
/* 138 */       float x2 = lx * lx;
/* 139 */       float y2 = ly * ly;
/* 140 */       float z2 = lz * lz;
/* 141 */       float scaledmass = mass * 0.08333333F;
/*     */ 
/* 143 */       inertia.set(y2 + z2, x2 + z2, x2 + y2);
/* 144 */       inertia.scale(scaledmass);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 149 */       .Stack tmp176_174 = localStack; tmp176_174.pop$com$bulletphysics$linearmath$Transform(); tmp176_174.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 153 */     return "Cone";
/*     */   }
/*     */ 
/*     */   protected void setConeUpIndex(int upIndex)
/*     */   {
/* 158 */     switch (upIndex) {
/*     */     case 0:
/* 160 */       this.coneIndices[0] = 1;
/* 161 */       this.coneIndices[1] = 0;
/* 162 */       this.coneIndices[2] = 2;
/* 163 */       break;
/*     */     case 1:
/* 166 */       this.coneIndices[0] = 0;
/* 167 */       this.coneIndices[1] = 1;
/* 168 */       this.coneIndices[2] = 2;
/* 169 */       break;
/*     */     case 2:
/* 172 */       this.coneIndices[0] = 0;
/* 173 */       this.coneIndices[1] = 2;
/* 174 */       this.coneIndices[2] = 1;
/* 175 */       break;
/*     */     default:
/* 178 */       if (!$assertionsDisabled) throw new AssertionError(); break;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getConeUpIndex() {
/* 183 */     return this.coneIndices[1];
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ConeShape
 * JD-Core Version:    0.6.2
 */