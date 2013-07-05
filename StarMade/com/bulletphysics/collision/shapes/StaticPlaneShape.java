/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class StaticPlaneShape extends ConcaveShape
/*     */ {
/*  40 */   protected final Vector3f localAabbMin = new Vector3f();
/*  41 */   protected final Vector3f localAabbMax = new Vector3f();
/*     */ 
/*  43 */   protected final Vector3f planeNormal = new Vector3f();
/*     */   protected float planeConstant;
/*  45 */   protected final Vector3f localScaling = new Vector3f(0.0F, 0.0F, 0.0F);
/*     */ 
/*     */   public StaticPlaneShape(Vector3f planeNormal, float planeConstant) {
/*  48 */     this.planeNormal.normalize(planeNormal);
/*  49 */     this.planeConstant = planeConstant;
/*     */   }
/*     */ 
/*     */   public Vector3f getPlaneNormal(Vector3f out) {
/*  53 */     out.set(this.planeNormal);
/*  54 */     return out;
/*     */   }
/*     */ 
/*     */   public float getPlaneConstant() {
/*  58 */     return this.planeConstant;
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  63 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*  64 */       Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  65 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  67 */       Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/*  68 */       halfExtents.sub(aabbMax, aabbMin);
/*  69 */       halfExtents.scale(0.5F);
/*     */ 
/*  71 */       float radius = halfExtents.length();
/*  72 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/*  73 */       center.add(aabbMax, aabbMin);
/*  74 */       center.scale(0.5F);
/*     */ 
/*  78 */       Vector3f tangentDir0 = localStack.get$javax$vecmath$Vector3f(); Vector3f tangentDir1 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  81 */       TransformUtil.planeSpace1(this.planeNormal, tangentDir0, tangentDir1);
/*     */ 
/*  83 */       Vector3f supVertex0 = localStack.get$javax$vecmath$Vector3f(); Vector3f supVertex1 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  85 */       Vector3f projectedCenter = localStack.get$javax$vecmath$Vector3f();
/*  86 */       tmp.scale(this.planeNormal.dot(center) - this.planeConstant, this.planeNormal);
/*  87 */       projectedCenter.sub(center, tmp);
/*     */ 
/*  89 */       Vector3f[] triangle = { localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f(), localStack.get$javax$vecmath$Vector3f() };
/*     */ 
/*  91 */       tmp1.scale(radius, tangentDir0);
/*  92 */       tmp2.scale(radius, tangentDir1);
/*  93 */       VectorUtil.add(triangle[0], projectedCenter, tmp1, tmp2);
/*     */ 
/*  95 */       tmp1.scale(radius, tangentDir0);
/*  96 */       tmp2.scale(radius, tangentDir1);
/*  97 */       tmp.sub(tmp1, tmp2);
/*  98 */       VectorUtil.add(triangle[1], projectedCenter, tmp);
/*     */ 
/* 100 */       tmp1.scale(radius, tangentDir0);
/* 101 */       tmp2.scale(radius, tangentDir1);
/* 102 */       tmp.sub(tmp1, tmp2);
/* 103 */       triangle[2].sub(projectedCenter, tmp);
/*     */ 
/* 105 */       callback.processTriangle(triangle, 0, 0);
/*     */ 
/* 107 */       tmp1.scale(radius, tangentDir0);
/* 108 */       tmp2.scale(radius, tangentDir1);
/* 109 */       tmp.sub(tmp1, tmp2);
/* 110 */       triangle[0].sub(projectedCenter, tmp);
/*     */ 
/* 112 */       tmp1.scale(radius, tangentDir0);
/* 113 */       tmp2.scale(radius, tangentDir1);
/* 114 */       tmp.add(tmp1, tmp2);
/* 115 */       triangle[1].sub(projectedCenter, tmp);
/*     */ 
/* 117 */       tmp1.scale(radius, tangentDir0);
/* 118 */       tmp2.scale(radius, tangentDir1);
/* 119 */       VectorUtil.add(triangle[2], projectedCenter, tmp1, tmp2);
/*     */ 
/* 121 */       callback.processTriangle(triangle, 0, 1);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform t, Vector3f aabbMin, Vector3f aabbMax) {
/* 126 */     aabbMin.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/* 127 */     aabbMax.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 132 */     return BroadphaseNativeType.STATIC_PLANE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling)
/*     */   {
/* 137 */     this.localScaling.set(scaling);
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 142 */     out.set(this.localScaling);
/* 143 */     return out;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float mass, Vector3f inertia)
/*     */   {
/* 149 */     inertia.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 154 */     return "STATICPLANE";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.StaticPlaneShape
 * JD-Core Version:    0.6.2
 */