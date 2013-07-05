/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import com.bulletphysics.collision.shapes.TriangleShape;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class ConvexTriangleCallback extends TriangleCallback
/*     */ {
/*     */   private CollisionObject convexBody;
/*     */   private CollisionObject triBody;
/*  51 */   private final Vector3f aabbMin = new Vector3f();
/*  52 */   private final Vector3f aabbMax = new Vector3f();
/*     */   private ManifoldResult resultOut;
/*     */   private Dispatcher dispatcher;
/*     */   private DispatcherInfo dispatchInfoPtr;
/*     */   private float collisionMarginTriangle;
/*     */   public int triangleCount;
/*     */   public PersistentManifold manifoldPtr;
/* 106 */   private CollisionAlgorithmConstructionInfo ci = new CollisionAlgorithmConstructionInfo();
/* 107 */   private TriangleShape tm = new TriangleShape();
/*     */ 
/*     */   public ConvexTriangleCallback(Dispatcher dispatcher, CollisionObject body0, CollisionObject body1, boolean isSwapped)
/*     */   {
/*  64 */     this.dispatcher = dispatcher;
/*  65 */     this.dispatchInfoPtr = null;
/*     */ 
/*  67 */     this.convexBody = (isSwapped ? body1 : body0);
/*  68 */     this.triBody = (isSwapped ? body0 : body1);
/*     */ 
/*  73 */     this.manifoldPtr = dispatcher.getNewManifold(this.convexBody, this.triBody);
/*     */ 
/*  75 */     clearCache();
/*     */   }
/*     */ 
/*     */   public void destroy() {
/*  79 */     clearCache();
/*  80 */     this.dispatcher.releaseManifold(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public void setTimeStepAndCounters(float arg1, DispatcherInfo arg2, ManifoldResult arg3) {
/*  84 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); this.dispatchInfoPtr = dispatchInfo;
/*  85 */       this.collisionMarginTriangle = collisionMarginTriangle;
/*  86 */       this.resultOut = resultOut;
/*     */ 
/*  89 */       Transform convexInTriangleSpace = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/*  91 */       this.triBody.getWorldTransform(convexInTriangleSpace);
/*  92 */       convexInTriangleSpace.inverse();
/*  93 */       convexInTriangleSpace.mul(this.convexBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()));
/*     */ 
/*  95 */       CollisionShape convexShape = this.convexBody.getCollisionShape();
/*     */ 
/*  97 */       convexShape.getAabb(convexInTriangleSpace, this.aabbMin, this.aabbMax);
/*  98 */       float extraMargin = collisionMarginTriangle;
/*  99 */       Vector3f extra = localStack.get$javax$vecmath$Vector3f();
/* 100 */       extra.set(extraMargin, extraMargin, extraMargin);
/*     */ 
/* 102 */       this.aabbMax.add(extra);
/* 103 */       this.aabbMin.sub(extra);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 104 */       .Stack tmp143_141 = localStack; tmp143_141.pop$com$bulletphysics$linearmath$Transform(); tmp143_141.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void processTriangle(Vector3f[] arg1, int arg2, int arg3)
/*     */   {
/* 115 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); this.ci.dispatcher1 = this.dispatcher;
/*     */ 
/* 117 */       CollisionObject ob = this.triBody;
/*     */ 
/* 120 */       if ((this.dispatchInfoPtr != null) && (this.dispatchInfoPtr.debugDraw != null) && (this.dispatchInfoPtr.debugDraw.getDebugMode() > 0)) {
/* 121 */         Vector3f color = localStack.get$javax$vecmath$Vector3f();
/* 122 */         color.set(255.0F, 255.0F, 0.0F);
/* 123 */         Transform tr = ob.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 125 */         Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/* 126 */         Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 128 */         tmp1.set(triangle[0]); tr.transform(tmp1);
/* 129 */         tmp2.set(triangle[1]); tr.transform(tmp2);
/* 130 */         this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
/*     */ 
/* 132 */         tmp1.set(triangle[1]); tr.transform(tmp1);
/* 133 */         tmp2.set(triangle[2]); tr.transform(tmp2);
/* 134 */         this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
/*     */ 
/* 136 */         tmp1.set(triangle[2]); tr.transform(tmp1);
/* 137 */         tmp2.set(triangle[0]); tr.transform(tmp2);
/* 138 */         this.dispatchInfoPtr.debugDraw.drawLine(tmp1, tmp2, color);
/*     */       }
/*     */ 
/* 149 */       if (this.convexBody.getCollisionShape().isConvex()) {
/* 150 */         this.tm.init(triangle[0], triangle[1], triangle[2]);
/* 151 */         this.tm.setMargin(this.collisionMarginTriangle);
/*     */ 
/* 153 */         CollisionShape tmpShape = ob.getCollisionShape();
/* 154 */         ob.internalSetTemporaryCollisionShape(this.tm);
/*     */ 
/* 156 */         CollisionAlgorithm colAlgo = this.ci.dispatcher1.findAlgorithm(this.convexBody, this.triBody, this.manifoldPtr);
/*     */ 
/* 160 */         this.resultOut.setShapeIdentifiers(-1, -1, partId, triangleIndex);
/*     */ 
/* 163 */         colAlgo.processCollision(this.convexBody, this.triBody, this.dispatchInfoPtr, this.resultOut);
/*     */ 
/* 165 */         this.ci.dispatcher1.freeCollisionAlgorithm(colAlgo);
/* 166 */         ob.internalSetTemporaryCollisionShape(tmpShape);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 168 */       .Stack tmp385_383 = localStack; tmp385_383.pop$com$bulletphysics$linearmath$Transform(); tmp385_383.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void clearCache() {
/* 171 */     this.dispatcher.clearManifold(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public Vector3f getAabbMin(Vector3f out) {
/* 175 */     out.set(this.aabbMin);
/* 176 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getAabbMax(Vector3f out) {
/* 180 */     out.set(this.aabbMax);
/* 181 */     return out;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexTriangleCallback
 * JD-Core Version:    0.6.2
 */