/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.shapes.CollisionShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.TransformUtil;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class GhostObject extends CollisionObject
/*     */ {
/*  46 */   protected ObjectArrayList<CollisionObject> overlappingObjects = new ObjectArrayList();
/*     */ 
/*     */   public GhostObject() {
/*  49 */     this.internalType = CollisionObjectType.GHOST_OBJECT;
/*     */   }
/*     */ 
/*     */   public void addOverlappingObjectInternal(BroadphaseProxy otherProxy, BroadphaseProxy thisProxy)
/*     */   {
/*  56 */     CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/*  57 */     assert (otherObject != null);
/*     */ 
/*  60 */     int index = this.overlappingObjects.indexOf(otherObject);
/*  61 */     if (index == -1)
/*     */     {
/*  63 */       this.overlappingObjects.add(otherObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeOverlappingObjectInternal(BroadphaseProxy otherProxy, Dispatcher dispatcher, BroadphaseProxy thisProxy)
/*     */   {
/*  71 */     CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
/*  72 */     assert (otherObject != null);
/*     */ 
/*  74 */     int index = this.overlappingObjects.indexOf(otherObject);
/*  75 */     if (index != -1) {
/*  76 */       this.overlappingObjects.set(index, this.overlappingObjects.getQuick(this.overlappingObjects.size() - 1));
/*  77 */       this.overlappingObjects.removeQuick(this.overlappingObjects.size() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void convexSweepTest(ConvexShape arg1, Transform arg2, Transform arg3, CollisionWorld.ConvexResultCallback arg4, float arg5) {
/*  82 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp11_7 = tmp7_5; tmp11_7.push$javax$vecmath$Vector3f(); tmp11_7.push$javax$vecmath$Quat4f(); Transform convexFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*  83 */       Transform convexToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/*  85 */       convexFromTrans.set(convexFromWorld);
/*  86 */       convexToTrans.set(convexToWorld);
/*     */ 
/*  88 */       Vector3f castShapeAabbMin = localStack.get$javax$vecmath$Vector3f();
/*  89 */       Vector3f castShapeAabbMax = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  93 */       Vector3f linVel = localStack.get$javax$vecmath$Vector3f();
/*  94 */       Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
/*  95 */       TransformUtil.calculateVelocity(convexFromTrans, convexToTrans, 1.0F, linVel, angVel);
/*  96 */       Transform R = localStack.get$com$bulletphysics$linearmath$Transform();
/*  97 */       R.setIdentity();
/*  98 */       R.setRotation(convexFromTrans.getRotation(localStack.get$javax$vecmath$Quat4f()));
/*  99 */       castShape.calculateTemporalAabb(R, linVel, angVel, 1.0F, castShapeAabbMin, castShapeAabbMax);
/*     */ 
/* 102 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 106 */       for (int i = 0; i < this.overlappingObjects.size(); i++) {
/* 107 */         CollisionObject collisionObject = (CollisionObject)this.overlappingObjects.getQuick(i);
/*     */ 
/* 110 */         if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle()))
/*     */         {
/* 112 */           Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();
/* 113 */           Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
/* 114 */           collisionObject.getCollisionShape().getAabb(collisionObject.getWorldTransform(tmpTrans), collisionObjectAabbMin, collisionObjectAabbMax);
/* 115 */           AabbUtil2.aabbExpand(collisionObjectAabbMin, collisionObjectAabbMax, castShapeAabbMin, castShapeAabbMax);
/* 116 */           float[] hitLambda = { 1.0F };
/* 117 */           Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
/* 118 */           if (AabbUtil2.rayAabb(convexFromWorld.origin, convexToWorld.origin, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal))
/* 119 */             CollisionWorld.objectQuerySingle(castShape, convexFromTrans, convexToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback, allowedCcdPenetration);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 128 */       .Stack tmp305_303 = localStack; tmp305_303.pop$com$bulletphysics$linearmath$Transform();
/*     */       .Stack tmp309_305 = tmp305_303; tmp309_305.pop$javax$vecmath$Vector3f(); tmp309_305.pop$javax$vecmath$Quat4f(); } throw finally;
/*     */   }
/*     */   public void rayTest(Vector3f arg1, Vector3f arg2, CollisionWorld.RayResultCallback arg3) {
/* 131 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform rayFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 132 */       rayFromTrans.setIdentity();
/* 133 */       rayFromTrans.origin.set(rayFromWorld);
/* 134 */       Transform rayToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/* 135 */       rayToTrans.setIdentity();
/* 136 */       rayToTrans.origin.set(rayToWorld);
/*     */ 
/* 138 */       Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 140 */       for (int i = 0; i < this.overlappingObjects.size(); i++) {
/* 141 */         CollisionObject collisionObject = (CollisionObject)this.overlappingObjects.getQuick(i);
/*     */ 
/* 144 */         if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle())) {
/* 145 */           CollisionWorld.rayTestSingle(rayFromTrans, rayToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 152 */       localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   public int getNumOverlappingObjects() {
/* 155 */     return this.overlappingObjects.size();
/*     */   }
/*     */ 
/*     */   public CollisionObject getOverlappingObject(int index) {
/* 159 */     return (CollisionObject)this.overlappingObjects.getQuick(index);
/*     */   }
/*     */ 
/*     */   public ObjectArrayList<CollisionObject> getOverlappingPairs() {
/* 163 */     return this.overlappingObjects;
/*     */   }
/*     */ 
/*     */   public static GhostObject upcast(CollisionObject colObj)
/*     */   {
/* 171 */     if (colObj.getInternalType() == CollisionObjectType.GHOST_OBJECT) {
/* 172 */       return (GhostObject)colObj;
/*     */     }
/*     */ 
/* 175 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.GhostObject
 * JD-Core Version:    0.6.2
 */