/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class BvhTriangleMeshShape extends TriangleMeshShape
/*     */ {
/*     */   private OptimizedBvh bvh;
/*     */   private boolean useQuantizedAabbCompression;
/*     */   private boolean ownsBvh;
/*  54 */   private ObjectPool<MyNodeOverlapCallback> myNodeCallbacks = ObjectPool.get(MyNodeOverlapCallback.class);
/*     */ 
/*     */   public BvhTriangleMeshShape() {
/*  57 */     super(null);
/*  58 */     this.bvh = null;
/*  59 */     this.ownsBvh = false;
/*     */   }
/*     */ 
/*     */   public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression) {
/*  63 */     this(meshInterface, useQuantizedAabbCompression, true);
/*     */   }
/*     */ 
/*     */   public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, boolean buildBvh) {
/*  67 */     super(meshInterface);
/*  68 */     this.bvh = null;
/*  69 */     this.useQuantizedAabbCompression = useQuantizedAabbCompression;
/*  70 */     this.ownsBvh = false;
/*     */ 
/*  75 */     Vector3f bvhAabbMin = new Vector3f(); Vector3f bvhAabbMax = new Vector3f();
/*  76 */     meshInterface.calculateAabbBruteForce(bvhAabbMin, bvhAabbMax);
/*     */ 
/*  78 */     if (buildBvh) {
/*  79 */       this.bvh = new OptimizedBvh();
/*  80 */       this.bvh.build(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax);
/*  81 */       this.ownsBvh = true;
/*     */ 
/*  84 */       recalcLocalAabb();
/*     */     }
/*     */   }
/*     */ 
/*     */   public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, Vector3f bvhAabbMin, Vector3f bvhAabbMax)
/*     */   {
/*  94 */     this(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax, true);
/*     */   }
/*     */ 
/*     */   public BvhTriangleMeshShape(StridingMeshInterface meshInterface, boolean useQuantizedAabbCompression, Vector3f bvhAabbMin, Vector3f bvhAabbMax, boolean buildBvh)
/*     */   {
/* 101 */     super(meshInterface);
/*     */ 
/* 103 */     this.bvh = null;
/* 104 */     this.useQuantizedAabbCompression = useQuantizedAabbCompression;
/* 105 */     this.ownsBvh = false;
/*     */ 
/* 110 */     if (buildBvh) {
/* 111 */       this.bvh = new OptimizedBvh();
/*     */ 
/* 113 */       this.bvh.build(meshInterface, useQuantizedAabbCompression, bvhAabbMin, bvhAabbMax);
/* 114 */       this.ownsBvh = true;
/*     */     }
/*     */ 
/* 118 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public boolean getOwnsBvh()
/*     */   {
/* 123 */     return this.ownsBvh;
/*     */   }
/*     */ 
/*     */   public BroadphaseNativeType getShapeType()
/*     */   {
/* 128 */     return BroadphaseNativeType.TRIANGLE_MESH_SHAPE_PROXYTYPE;
/*     */   }
/*     */ 
/*     */   public void performRaycast(TriangleCallback callback, Vector3f raySource, Vector3f rayTarget) {
/* 132 */     MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
/* 133 */     myNodeCallback.init(callback, this.meshInterface);
/*     */ 
/* 135 */     this.bvh.reportRayOverlappingNodex(myNodeCallback, raySource, rayTarget);
/*     */ 
/* 137 */     this.myNodeCallbacks.release(myNodeCallback);
/*     */   }
/*     */ 
/*     */   public void performConvexcast(TriangleCallback callback, Vector3f raySource, Vector3f rayTarget, Vector3f aabbMin, Vector3f aabbMax) {
/* 141 */     MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
/* 142 */     myNodeCallback.init(callback, this.meshInterface);
/*     */ 
/* 144 */     this.bvh.reportBoxCastOverlappingNodex(myNodeCallback, raySource, rayTarget, aabbMin, aabbMax);
/*     */ 
/* 146 */     this.myNodeCallbacks.release(myNodeCallback);
/*     */   }
/*     */ 
/*     */   public void processAllTriangles(TriangleCallback callback, Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 160 */     MyNodeOverlapCallback myNodeCallback = (MyNodeOverlapCallback)this.myNodeCallbacks.get();
/* 161 */     myNodeCallback.init(callback, this.meshInterface);
/*     */ 
/* 163 */     this.bvh.reportAabbOverlappingNodex(myNodeCallback, aabbMin, aabbMax);
/*     */ 
/* 165 */     this.myNodeCallbacks.release(myNodeCallback);
/*     */   }
/*     */ 
/*     */   public void refitTree(Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 172 */     this.bvh.refit(this.meshInterface);
/*     */ 
/* 174 */     recalcLocalAabb();
/*     */   }
/*     */ 
/*     */   public void partialRefitTree(Vector3f aabbMin, Vector3f aabbMax)
/*     */   {
/* 181 */     this.bvh.refitPartial(this.meshInterface, aabbMin, aabbMax);
/*     */ 
/* 183 */     VectorUtil.setMin(this.localAabbMin, aabbMin);
/* 184 */     VectorUtil.setMax(this.localAabbMax, aabbMax);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 189 */     return "BVHTRIANGLEMESH";
/*     */   }
/*     */ 
/*     */   public void setLocalScaling(Vector3f arg1)
/*     */   {
/* 194 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 195 */       tmp.sub(getLocalScaling(localStack.get$javax$vecmath$Vector3f()), scaling);
/*     */ 
/* 197 */       if (tmp.lengthSquared() > 1.192093E-007F) {
/* 198 */         super.setLocalScaling(scaling);
/*     */ 
/* 207 */         this.bvh = new OptimizedBvh();
/*     */ 
/* 209 */         this.bvh.build(this.meshInterface, this.useQuantizedAabbCompression, this.localAabbMin, this.localAabbMax);
/* 210 */         this.ownsBvh = true;
/*     */       }return; } finally {
/* 212 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public OptimizedBvh getOptimizedBvh() {
/* 215 */     return this.bvh;
/*     */   }
/*     */ 
/*     */   public void setOptimizedBvh(OptimizedBvh arg1) {
/* 219 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f scaling = localStack.get$javax$vecmath$Vector3f();
/* 220 */       scaling.set(1.0F, 1.0F, 1.0F);
/* 221 */       setOptimizedBvh(bvh, scaling);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void setOptimizedBvh(OptimizedBvh arg1, Vector3f arg2) {
/* 225 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); assert (this.bvh == null);
/* 226 */       assert (!this.ownsBvh);
/*     */ 
/* 228 */       this.bvh = bvh;
/* 229 */       this.ownsBvh = false;
/*     */ 
/* 232 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 233 */       tmp.sub(getLocalScaling(localStack.get$javax$vecmath$Vector3f()), scaling);
/*     */ 
/* 235 */       if (tmp.lengthSquared() > 1.192093E-007F)
/* 236 */         super.setLocalScaling(scaling);
/*     */       return; } finally {
/* 238 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public boolean usesQuantizedAabbCompression() {
/* 241 */     return this.useQuantizedAabbCompression;
/*     */   }
/*     */ 
/*     */   protected static class MyNodeOverlapCallback extends NodeOverlapCallback
/*     */   {
/*     */     public StridingMeshInterface meshInterface;
/*     */     public TriangleCallback callback;
/* 250 */     private Vector3f[] triangle = { new Vector3f(), new Vector3f(), new Vector3f() };
/*     */ 
/*     */     public void init(TriangleCallback callback, StridingMeshInterface meshInterface)
/*     */     {
/* 256 */       this.meshInterface = meshInterface;
/* 257 */       this.callback = callback;
/*     */     }
/*     */ 
/*     */     public void processNode(int arg1, int arg2) {
/* 261 */       .Stack localStack = .Stack.get();
/*     */       try { localStack.push$javax$vecmath$Vector3f(); VertexData data = this.meshInterface.getLockedReadOnlyVertexIndexBase(nodeSubPart);
/*     */ 
/* 263 */         Vector3f meshScaling = this.meshInterface.getScaling(localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 265 */         data.getTriangle(nodeTriangleIndex * 3, meshScaling, this.triangle);
/*     */ 
/* 268 */         this.callback.processTriangle(this.triangle, nodeSubPart, nodeTriangleIndex);
/*     */ 
/* 270 */         this.meshInterface.unLockReadOnlyVertexBase(nodeSubPart);
/*     */         return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BvhTriangleMeshShape
 * JD-Core Version:    0.6.2
 */