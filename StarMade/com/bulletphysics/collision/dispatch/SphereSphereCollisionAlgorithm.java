/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithmConstructionInfo;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.SphereShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class SphereSphereCollisionAlgorithm extends CollisionAlgorithm
/*     */ {
/*     */   private boolean ownManifold;
/*     */   private PersistentManifold manifoldPtr;
/*     */ 
/*     */   public void init(PersistentManifold mf, CollisionAlgorithmConstructionInfo ci, CollisionObject col0, CollisionObject col1)
/*     */   {
/*  49 */     super.init(ci);
/*  50 */     this.manifoldPtr = mf;
/*     */ 
/*  52 */     if (this.manifoldPtr == null) {
/*  53 */       this.manifoldPtr = this.dispatcher.getNewManifold(col0, col1);
/*  54 */       this.ownManifold = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void init(CollisionAlgorithmConstructionInfo ci)
/*     */   {
/*  60 */     super.init(ci);
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  65 */     if (this.ownManifold) {
/*  66 */       if (this.manifoldPtr != null) {
/*  67 */         this.dispatcher.releaseManifold(this.manifoldPtr);
/*     */       }
/*  69 */       this.manifoldPtr = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void processCollision(CollisionObject arg1, CollisionObject arg2, DispatcherInfo arg3, ManifoldResult arg4)
/*     */   {
/*  75 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); if (this.manifoldPtr == null) {
/*  76 */         return;
/*     */       }
/*     */ 
/*  79 */       Transform tmpTrans1 = localStack.get$com$bulletphysics$linearmath$Transform();
/*  80 */       Transform tmpTrans2 = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/*  82 */       resultOut.setPersistentManifold(this.manifoldPtr);
/*     */ 
/*  84 */       SphereShape sphere0 = (SphereShape)col0.getCollisionShape();
/*  85 */       SphereShape sphere1 = (SphereShape)col1.getCollisionShape();
/*     */ 
/*  87 */       Vector3f diff = localStack.get$javax$vecmath$Vector3f();
/*  88 */       diff.sub(col0.getWorldTransform(tmpTrans1).origin, col1.getWorldTransform(tmpTrans2).origin);
/*     */ 
/*  90 */       float len = diff.length();
/*  91 */       float radius0 = sphere0.getRadius();
/*  92 */       float radius1 = sphere1.getRadius();
/*     */ 
/*  99 */       if (len > radius0 + radius1)
/*     */       {
/* 101 */         resultOut.refreshContactPoints();
/*     */ 
/* 103 */         return;
/*     */       }
/*     */ 
/* 106 */       float dist = len - (radius0 + radius1);
/*     */ 
/* 108 */       Vector3f normalOnSurfaceB = localStack.get$javax$vecmath$Vector3f();
/* 109 */       normalOnSurfaceB.set(1.0F, 0.0F, 0.0F);
/* 110 */       if (len > 1.192093E-007F) {
/* 111 */         normalOnSurfaceB.scale(1.0F / len, diff);
/*     */       }
/*     */ 
/* 114 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 117 */       Vector3f pos0 = localStack.get$javax$vecmath$Vector3f();
/* 118 */       tmp.scale(radius0, normalOnSurfaceB);
/* 119 */       pos0.sub(col0.getWorldTransform(tmpTrans1).origin, tmp);
/*     */ 
/* 122 */       Vector3f pos1 = localStack.get$javax$vecmath$Vector3f();
/* 123 */       tmp.scale(radius1, normalOnSurfaceB);
/* 124 */       pos1.add(col1.getWorldTransform(tmpTrans2).origin, tmp);
/*     */ 
/* 127 */       resultOut.addContactPoint(normalOnSurfaceB, pos1, dist);
/*     */ 
/* 130 */       resultOut.refreshContactPoints();
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 132 */       .Stack tmp292_290 = localStack; tmp292_290.pop$com$bulletphysics$linearmath$Transform(); tmp292_290.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public float calculateTimeOfImpact(CollisionObject body0, CollisionObject body1, DispatcherInfo dispatchInfo, ManifoldResult resultOut) {
/* 136 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   public void getAllContactManifolds(ObjectArrayList<PersistentManifold> manifoldArray)
/*     */   {
/* 141 */     if ((this.manifoldPtr != null) && (this.ownManifold))
/* 142 */       manifoldArray.add(this.manifoldPtr);
/*     */   }
/*     */ 
/*     */   public static class CreateFunc extends CollisionAlgorithmCreateFunc
/*     */   {
/* 149 */     private final ObjectPool<SphereSphereCollisionAlgorithm> pool = ObjectPool.get(SphereSphereCollisionAlgorithm.class);
/*     */ 
/*     */     public CollisionAlgorithm createCollisionAlgorithm(CollisionAlgorithmConstructionInfo ci, CollisionObject body0, CollisionObject body1)
/*     */     {
/* 153 */       SphereSphereCollisionAlgorithm algo = (SphereSphereCollisionAlgorithm)this.pool.get();
/* 154 */       algo.init(null, ci, body0, body1);
/* 155 */       return algo;
/*     */     }
/*     */ 
/*     */     public void releaseCollisionAlgorithm(CollisionAlgorithm algo)
/*     */     {
/* 160 */       this.pool.release((SphereSphereCollisionAlgorithm)algo);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.SphereSphereCollisionAlgorithm
 * JD-Core Version:    0.6.2
 */