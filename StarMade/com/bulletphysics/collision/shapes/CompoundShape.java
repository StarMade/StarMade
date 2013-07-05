/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.MatrixUtil;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class CompoundShape extends CollisionShape
/*     */ {
/*  45 */   private final ObjectArrayList<CompoundShapeChild> children = new ObjectArrayList();
/*  46 */   private final Vector3f localAabbMin = new Vector3f(1.0E+030F, 1.0E+030F, 1.0E+030F);
/*  47 */   private final Vector3f localAabbMax = new Vector3f(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*     */ 
/*  49 */   private OptimizedBvh aabbTree = null;
/*     */ 
/*  51 */   private float collisionMargin = 0.0F;
/*  52 */   protected final Vector3f localScaling = new Vector3f(1.0F, 1.0F, 1.0F);
/*     */ 
/*     */   public void addChildShape(Transform arg1, CollisionShape arg2)
/*     */   {
/*  57 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); CompoundShapeChild child = new CompoundShapeChild();
/*  58 */       child.transform.set(localTransform);
/*  59 */       child.childShape = shape;
/*  60 */       child.childShapeType = shape.getShapeType();
/*  61 */       child.childMargin = shape.getMargin();
/*     */ 
/*  63 */       this.children.add(child);
/*     */ 
/*  66 */       Vector3f _localAabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f _localAabbMax = localStack.get$javax$vecmath$Vector3f();
/*  67 */       shape.getAabb(localTransform, _localAabbMin, _localAabbMax);
/*     */ 
/*  81 */       VectorUtil.setMin(this.localAabbMin, _localAabbMin);
/*  82 */       VectorUtil.setMax(this.localAabbMax, _localAabbMax);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void removeChildShape(CollisionShape shape)
/*     */   {
/*     */     boolean done_removing;
/*     */     do
/*     */     {
/*  93 */       done_removing = true;
/*     */ 
/*  95 */       for (int i = 0; i < this.children.size(); i++) {
/*  96 */         if (((CompoundShapeChild)this.children.getQuick(i)).childShape == shape) {
/*  97 */           this.children.removeQuick(i);
/*  98 */           done_removing = false;
/*  99 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 103 */     while (!done_removing);
/*     */ 
/* 105 */     recalculateLocalAabb();
/*     */   }
/*     */ 
/*     */   public int getNumChildShapes() {
/* 109 */     return this.children.size();
/*     */   }
/*     */ 
/*     */   public CollisionShape getChildShape(int index) {
/* 113 */     return ((CompoundShapeChild)this.children.getQuick(index)).childShape;
/*     */   }
/*     */ 
/*     */   public Transform getChildTransform(int index, Transform out) {
/* 117 */     out.set(((CompoundShapeChild)this.children.getQuick(index)).transform);
/* 118 */     return out;
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<CompoundShapeChild> getChildList() {
/* 122 */     return this.children;
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/* 130 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); Vector3f localHalfExtents = localStack.get$javax$vecmath$Vector3f();
/* 131 */       localHalfExtents.sub(this.localAabbMax, this.localAabbMin);
/* 132 */       localHalfExtents.scale(0.5F);
/* 133 */       localHalfExtents.x += getMargin();
/* 134 */       localHalfExtents.y += getMargin();
/* 135 */       localHalfExtents.z += getMargin();
/*     */ 
/* 137 */       Vector3f localCenter = localStack.get$javax$vecmath$Vector3f();
/* 138 */       localCenter.add(this.localAabbMax, this.localAabbMin);
/* 139 */       localCenter.scale(0.5F);
/*     */ 
/* 141 */       Matrix3f abs_b = localStack.get$javax$vecmath$Matrix3f(trans.basis);
/* 142 */       MatrixUtil.absolute(abs_b);
/*     */ 
/* 144 */       Vector3f center = localStack.get$javax$vecmath$Vector3f(localCenter);
/* 145 */       trans.transform(center);
/*     */ 
/* 147 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 149 */       Vector3f extent = localStack.get$javax$vecmath$Vector3f();
/* 150 */       abs_b.getRow(0, tmp);
/* 151 */       extent.x = tmp.dot(localHalfExtents);
/* 152 */       abs_b.getRow(1, tmp);
/* 153 */       extent.y = tmp.dot(localHalfExtents);
/* 154 */       abs_b.getRow(2, tmp);
/* 155 */       extent.z = tmp.dot(localHalfExtents);
/*     */ 
/* 157 */       aabbMin.sub(center, extent);
/* 158 */       aabbMax.add(center, extent);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 159 */       .Stack tmp245_243 = localStack; tmp245_243.pop$javax$vecmath$Vector3f(); tmp245_243.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void recalculateLocalAabb()
/*     */   {
/* 168 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.localAabbMin.set(1.0E+030F, 1.0E+030F, 1.0E+030F);
/* 169 */       this.localAabbMax.set(-1.0E+030F, -1.0E+030F, -1.0E+030F);
/*     */ 
/* 171 */       Vector3f tmpLocalAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 172 */       Vector3f tmpLocalAabbMax = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 175 */       for (int j = 0; j < this.children.size(); j++) {
/* 176 */         ((CompoundShapeChild)this.children.getQuick(j)).childShape.getAabb(((CompoundShapeChild)this.children.getQuick(j)).transform, tmpLocalAabbMin, tmpLocalAabbMax);
/*     */ 
/* 178 */         for (int i = 0; i < 3; i++) {
/* 179 */           if (VectorUtil.getCoord(this.localAabbMin, i) > VectorUtil.getCoord(tmpLocalAabbMin, i)) {
/* 180 */             VectorUtil.setCoord(this.localAabbMin, i, VectorUtil.getCoord(tmpLocalAabbMin, i));
/*     */           }
/* 182 */           if (VectorUtil.getCoord(this.localAabbMax, i) < VectorUtil.getCoord(tmpLocalAabbMax, i))
/* 183 */             VectorUtil.setCoord(this.localAabbMax, i, VectorUtil.getCoord(tmpLocalAabbMax, i));
/*     */         }
/*     */       }
/*     */       return; } finally {
/* 187 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f scaling) {
/* 191 */     this.localScaling.set(scaling);
/*     */   }
/*     */ 
/*     */   public Vector3f getLocalScaling(Vector3f out)
/*     */   {
/* 196 */     out.set(this.localScaling);
/* 197 */     return out;
/*     */   }
/*     */ 
/*     */   public void calculateLocalInertia(float arg1, Vector3f arg2)
/*     */   {
/* 203 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform ident = localStack.get$com$bulletphysics$linearmath$Transform();
/* 204 */       ident.setIdentity();
/* 205 */       Vector3f aabbMin = localStack.get$javax$vecmath$Vector3f(); Vector3f aabbMax = localStack.get$javax$vecmath$Vector3f();
/* 206 */       getAabb(ident, aabbMin, aabbMax);
/*     */ 
/* 208 */       Vector3f halfExtents = localStack.get$javax$vecmath$Vector3f();
/* 209 */       halfExtents.sub(aabbMax, aabbMin);
/* 210 */       halfExtents.scale(0.5F);
/*     */ 
/* 212 */       float lx = 2.0F * halfExtents.x;
/* 213 */       float ly = 2.0F * halfExtents.y;
/* 214 */       float lz = 2.0F * halfExtents.z;
/*     */ 
/* 216 */       inertia.x = (mass / 12.0F * (ly * ly + lz * lz));
/* 217 */       inertia.y = (mass / 12.0F * (lx * lx + lz * lz));
/* 218 */       inertia.z = (mass / 12.0F * (lx * lx + ly * ly));
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 219 */       .Stack tmp169_167 = localStack; tmp169_167.pop$com$bulletphysics$linearmath$Transform(); tmp169_167.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType() {
/* 223 */     return BroadphaseNativeType.COMPOUND_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void setMargin(float margin)
/*     */   {
/* 228 */     this.collisionMargin = margin;
/*     */   }
/*     */ 
/*     */   public float getMargin()
/*     */   {
/* 233 */     return this.collisionMargin;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 238 */     return "Compound";
/*     */   }
/*     */ 
/*     */   public OptimizedBvh getAabbTree()
/*     */   {
/* 245 */     return this.aabbTree;
/*     */   }
/*     */ 
/*     */   public void calculatePrincipalAxisTransform(float[] arg1, Transform arg2, Vector3f arg3)
/*     */   {
/* 259 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Matrix3f(); int n = this.children.size();
/*     */ 
/* 261 */       float totalMass = 0.0F;
/* 262 */       Vector3f center = localStack.get$javax$vecmath$Vector3f();
/* 263 */       center.set(0.0F, 0.0F, 0.0F);
/* 264 */       for (int k = 0; k < n; k++) {
/* 265 */         center.scaleAdd(masses[k], ((CompoundShapeChild)this.children.getQuick(k)).transform.origin, center);
/* 266 */         totalMass += masses[k];
/*     */       }
/* 268 */       center.scale(1.0F / totalMass);
/* 269 */       principal.origin.set(center);
/*     */ 
/* 271 */       Matrix3f tensor = localStack.get$javax$vecmath$Matrix3f();
/* 272 */       tensor.setZero();
/*     */ 
/* 274 */       for (int k = 0; k < n; k++) {
/* 275 */         Vector3f i = localStack.get$javax$vecmath$Vector3f();
/* 276 */         ((CompoundShapeChild)this.children.getQuick(k)).childShape.calculateLocalInertia(masses[k], i);
/*     */ 
/* 278 */         Transform t = ((CompoundShapeChild)this.children.getQuick(k)).transform;
/* 279 */         Vector3f o = localStack.get$javax$vecmath$Vector3f();
/* 280 */         o.sub(t.origin, center);
/*     */ 
/* 283 */         Matrix3f j = localStack.get$javax$vecmath$Matrix3f();
/* 284 */         j.transpose(t.basis);
/*     */ 
/* 286 */         j.m00 *= i.x;
/* 287 */         j.m01 *= i.x;
/* 288 */         j.m02 *= i.x;
/* 289 */         j.m10 *= i.y;
/* 290 */         j.m11 *= i.y;
/* 291 */         j.m12 *= i.y;
/* 292 */         j.m20 *= i.z;
/* 293 */         j.m21 *= i.z;
/* 294 */         j.m22 *= i.z;
/*     */ 
/* 296 */         j.mul(t.basis, j);
/*     */ 
/* 299 */         tensor.add(j);
/*     */ 
/* 302 */         float o2 = o.lengthSquared();
/* 303 */         j.setRow(0, o2, 0.0F, 0.0F);
/* 304 */         j.setRow(1, 0.0F, o2, 0.0F);
/* 305 */         j.setRow(2, 0.0F, 0.0F, o2);
/* 306 */         j.m00 += o.x * -o.x;
/* 307 */         j.m01 += o.y * -o.x;
/* 308 */         j.m02 += o.z * -o.x;
/* 309 */         j.m10 += o.x * -o.y;
/* 310 */         j.m11 += o.y * -o.y;
/* 311 */         j.m12 += o.z * -o.y;
/* 312 */         j.m20 += o.x * -o.z;
/* 313 */         j.m21 += o.y * -o.z;
/* 314 */         j.m22 += o.z * -o.z;
/*     */ 
/* 317 */         tensor.m00 += masses[k] * j.m00;
/* 318 */         tensor.m01 += masses[k] * j.m01;
/* 319 */         tensor.m02 += masses[k] * j.m02;
/* 320 */         tensor.m10 += masses[k] * j.m10;
/* 321 */         tensor.m11 += masses[k] * j.m11;
/* 322 */         tensor.m12 += masses[k] * j.m12;
/* 323 */         tensor.m20 += masses[k] * j.m20;
/* 324 */         tensor.m21 += masses[k] * j.m21;
/* 325 */         tensor.m22 += masses[k] * j.m22;
/*     */       }
/*     */ 
/* 328 */       MatrixUtil.diagonalize(tensor, principal.basis, 1.0E-005F, 20);
/*     */ 
/* 330 */       inertia.set(tensor.m00, tensor.m11, tensor.m22);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 331 */       .Stack tmp839_837 = localStack; tmp839_837.pop$javax$vecmath$Vector3f(); tmp839_837.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.CompoundShape
 * JD-Core Version:    0.6.2
 */