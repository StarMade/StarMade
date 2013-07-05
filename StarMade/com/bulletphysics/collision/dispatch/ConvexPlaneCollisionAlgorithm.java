/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.collision.shapes.StaticPlaneShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ConvexPlaneCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/*     */   private boolean ownManifold;
/*     */   private PersistentManifold manifoldPtr;
/*     */   private boolean isSwapped;
/*     */ 
/*     */   public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject col0, CollisionObject col1, boolean isSwapped)
/*     */   {
/*  50 */     super.init(ci);
/*  51 */     this.ownManifold = false;
/*  52 */     this.manifoldPtr = mf;
/*  53 */     this.isSwapped = isSwapped;
/*     */ 
/*  55 */     CollisionObject convexObj = isSwapped ? col1 : col0;
/*  56 */     CollisionObject planeObj = isSwapped ? col0 : col1;
/*     */ 
/*  58 */     if ((this.manifoldPtr == null) && (this.dispatcher.needsCollision(convexObj, planeObj))) {
/*  59 */       this.manifoldPtr = this.dispatcher.getNewManifold(convexObj, planeObj);
/*  60 */       this.ownManifold = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  66 */     if (this.ownManifold) {
/*  67 */       if (this.manifoldPtr != null) {
/*  68 */         this.dispatcher.releaseManifold(this.manifoldPtr);
/*     */       }
/*  70 */       this.manifoldPtr = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*     */   {
/*  76 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); if (this.manifoldPtr == null) {
/*  77 */         return;
/*     */       }
/*     */ 
/*  80 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/*  82 */       CollisionObject convexObj = this.isSwapped ? body1 : body0;
/*  83 */       CollisionObject planeObj = this.isSwapped ? body0 : body1;
/*     */ 
/*  85 */       ConvexShape convexShape = (ConvexShape)convexObj.getCollisionShape();
/*  86 */       StaticPlaneShape planeShape = (StaticPlaneShape)planeObj.getCollisionShape();
/*     */ 
/*  88 */       boolean hasCollision = false;
/*  89 */       Vector3f planeNormal = planeShape.getPlaneNormal(localStack.get$javax$vecmath$Vector3f());
/*  90 */       float planeConstant = planeShape.getPlaneConstant();
/*     */ 
/*  92 */       Transform planeInConvex = localStack.get$com$bulletphysics$linearmath$Transform();
/*  93 */       convexObj.getWorldTransform(planeInConvex);
/*  94 */       planeInConvex.inverse();
/*  95 */       planeInConvex.mul(planeObj.getWorldTransform(tmpTrans));
/*     */ 
/*  97 */       Transform convexInPlaneTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  98 */       convexInPlaneTrans.inverse(planeObj.getWorldTransform(tmpTrans));
/*  99 */       convexInPlaneTrans.mul(convexObj.getWorldTransform(tmpTrans));
/*     */ 
/* 101 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 102 */       tmp.negate(planeNormal);
/* 103 */       planeInConvex.basis.transform(tmp);
/*     */ 
/* 105 */       Vector3f vtx = convexShape.localGetSupportingVertex(tmp, localStack.get$javax$vecmath$Vector3f());
/* 106 */       Vector3f vtxInPlane = localStack.get$javax$vecmath$Vector3f(vtx);
/* 107 */       convexInPlaneTrans.transform(vtxInPlane);
/*     */ 
/* 109 */       float distance = planeNormal.dot(vtxInPlane) - planeConstant;
/*     */ 
/* 111 */       Vector3f vtxInPlaneProjected = localStack.get$javax$vecmath$Vector3f();
/* 112 */       tmp.scale(distance, planeNormal);
/* 113 */       vtxInPlaneProjected.sub(vtxInPlane, tmp);
/*     */ 
/* 115 */       Vector3f vtxInPlaneWorld = localStack.get$javax$vecmath$Vector3f(vtxInPlaneProjected);
/* 116 */       planeObj.getWorldTransform(tmpTrans).transform(vtxInPlaneWorld);
/*     */ 
/* 118 */       hasCollision = distance < this.manifoldPtr.getContactBreakingThreshold();
/* 119 */       resultOut.setPersistentManifold(this.manifoldPtr);
/* 120 */       if (hasCollision)
/*     */       {
/* 122 */         Vector3f normalOnSurfaceB = localStack.get$javax$vecmath$Vector3f(planeNormal);
/* 123 */         planeObj.getWorldTransform(tmpTrans).basis.transform(normalOnSurfaceB);
/*     */ 
/* 125 */         Vector3f pOnB = localStack.get$javax$vecmath$Vector3f(vtxInPlaneWorld);
/* 126 */         resultOut.addContactPoint(normalOnSurfaceB, pOnB, distance);
/*     */       }
/* 128 */       if ((this.ownManifold) && 
/* 129 */         (this.manifoldPtr.getNumContacts() != 0))
/* 130 */         resultOut.refreshContactPoints();
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 133 */       .Stack tmp399_397 = localStack; tmp399_397.pop$com$bulletphysics$linearmath$Transform(); tmp399_397.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut)
/*     */   {
/* 138 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*     */   {
/* 143 */     if ((this.manifoldPtr != null) && (this.ownManifold))
/* 144 */       manifoldArray.add(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 151 */     private final ObjectPool<ConvexPlaneCollisionAlgorithm> pool = ObjectPool.get(ConvexPlaneCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 155 */       ConvexPlaneCollisionAlgorithm algo = (ConvexPlaneCollisionAlgorithm)this.pool.get();
/* 156 */       if (!this.swapped) {
/* 157 */         algo.init(null, ci, body0, body1, false);
/*     */       }
/*     */       else {
/* 160 */         algo.init(null, ci, body0, body1, true);
/*     */       }
/* 162 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 167 */       this.pool.release((ConvexPlaneCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ConvexPlaneCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */