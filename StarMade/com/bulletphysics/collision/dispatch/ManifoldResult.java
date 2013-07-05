/*     */ package com.bulletphysics.collision.dispatch;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.BulletGlobals;
/*     */ import com.bulletphysics.ContactAddedCallback;
/*     */ import com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface.Result;
/*     */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class ManifoldResult extends DiscreteCollisionDetectorInterface.Result
/*     */ {
/*  43 */   protected final ObjectPool<ManifoldPoint> pointsPool = ObjectPool.get(ManifoldPoint.class);
/*     */   private PersistentManifold manifoldPtr;
/*  48 */   private final Transform rootTransA = new Transform();
/*  49 */   private final Transform rootTransB = new Transform();
/*     */   private CollisionObject body0;
/*     */   private CollisionObject body1;
/*     */   private int partId0;
/*     */   private int partId1;
/*     */   private int index0;
/*     */   private int index1;
/*     */ 
/*     */   public ManifoldResult()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ManifoldResult(CollisionObject body0, CollisionObject body1)
/*     */   {
/*  61 */     init(body0, body1);
/*     */   }
/*     */ 
/*     */   public void init(CollisionObject body0, CollisionObject body1) {
/*  65 */     this.body0 = body0;
/*  66 */     this.body1 = body1;
/*  67 */     body0.getWorldTransform(this.rootTransA);
/*  68 */     body1.getWorldTransform(this.rootTransB);
/*     */   }
/*     */ 
/*     */   public PersistentManifold getPersistentManifold() {
/*  72 */     return this.manifoldPtr;
/*     */   }
/*     */ 
/*     */   public void setPersistentManifold(PersistentManifold manifoldPtr) {
/*  76 */     this.manifoldPtr = manifoldPtr;
/*     */   }
/*     */ 
/*     */   public void setShapeIdentifiers(int partId0, int index0, int partId1, int index1) {
/*  80 */     this.partId0 = partId0;
/*  81 */     this.partId1 = partId1;
/*  82 */     this.index0 = index0;
/*  83 */     this.index1 = index1;
/*     */   }
/*     */ 
/*     */   public void addContactPoint(Vector3f arg1, Vector3f arg2, float arg3) {
/*  87 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); assert (this.manifoldPtr != null);
/*     */ 
/*  90 */       if (depth > this.manifoldPtr.getContactBreakingThreshold()) {
/*  91 */         return;
/*     */       }
/*     */ 
/*  94 */       boolean isSwapped = this.manifoldPtr.getBody0() != this.body0;
/*     */ 
/*  96 */       Vector3f pointA = localStack.get$javax$vecmath$Vector3f();
/*  97 */       pointA.scaleAdd(depth, normalOnBInWorld, pointInWorld);
/*     */ 
/*  99 */       Vector3f localA = localStack.get$javax$vecmath$Vector3f();
/* 100 */       Vector3f localB = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 102 */       if (isSwapped) {
/* 103 */         this.rootTransB.invXform(pointA, localA);
/* 104 */         this.rootTransA.invXform(pointInWorld, localB);
/*     */       }
/*     */       else {
/* 107 */         this.rootTransA.invXform(pointA, localA);
/* 108 */         this.rootTransB.invXform(pointInWorld, localB);
/*     */       }
/*     */ 
/* 111 */       ManifoldPoint newPt = (ManifoldPoint)this.pointsPool.get();
/* 112 */       newPt.init(localA, localB, normalOnBInWorld, depth);
/*     */ 
/* 114 */       newPt.positionWorldOnA.set(pointA);
/* 115 */       newPt.positionWorldOnB.set(pointInWorld);
/*     */ 
/* 117 */       int insertIndex = this.manifoldPtr.getCacheEntry(newPt);
/*     */ 
/* 119 */       newPt.combinedFriction = calculateCombinedFriction(this.body0, this.body1);
/* 120 */       newPt.combinedRestitution = calculateCombinedRestitution(this.body0, this.body1);
/*     */ 
/* 123 */       newPt.partId0 = this.partId0;
/* 124 */       newPt.partId1 = this.partId1;
/* 125 */       newPt.index0 = this.index0;
/* 126 */       newPt.index1 = this.index1;
/*     */ 
/* 129 */       if (insertIndex >= 0)
/*     */       {
/* 131 */         this.manifoldPtr.replaceContactPoint(newPt, insertIndex);
/*     */       }
/*     */       else {
/* 134 */         insertIndex = this.manifoldPtr.addManifoldPoint(newPt);
/*     */       }
/*     */ 
/* 138 */       if ((BulletGlobals.getContactAddedCallback() != null) && (((this.body0.getCollisionFlags() & 0x8) != 0) || ((this.body1.getCollisionFlags() & 0x8) != 0)))
/*     */       {
/* 143 */         CollisionObject obj0 = isSwapped ? this.body1 : this.body0;
/* 144 */         CollisionObject obj1 = isSwapped ? this.body0 : this.body1;
/* 145 */         BulletGlobals.getContactAddedCallback().contactAdded(this.manifoldPtr.getContactPoint(insertIndex), obj0, this.partId0, this.index0, obj1, this.partId1, this.index1);
/* 148 */       }
/*     */ this.pointsPool.release(newPt);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static float calculateCombinedFriction(CollisionObject body0, CollisionObject body1) {
/* 153 */     float friction = body0.getFriction() * body1.getFriction();
/*     */ 
/* 155 */     float MAX_FRICTION = 10.0F;
/* 156 */     if (friction < -MAX_FRICTION) {
/* 157 */       friction = -MAX_FRICTION;
/*     */     }
/* 159 */     if (friction > MAX_FRICTION) {
/* 160 */       friction = MAX_FRICTION;
/*     */     }
/* 162 */     return friction;
/*     */   }
/*     */ 
/*     */   private static float calculateCombinedRestitution(CollisionObject body0, CollisionObject body1) {
/* 166 */     return body0.getRestitution() * body1.getRestitution();
/*     */   }
/*     */ 
/*     */   public void refreshContactPoints() {
/* 170 */     assert (this.manifoldPtr != null);
/* 171 */     if (this.manifoldPtr.getNumContacts() == 0) {
/* 172 */       return;
/*     */     }
/*     */ 
/* 175 */     boolean isSwapped = this.manifoldPtr.getBody0() != this.body0;
/*     */ 
/* 177 */     if (isSwapped) {
/* 178 */       this.manifoldPtr.refreshContactPoints(this.rootTransB, this.rootTransA);
/*     */     }
/*     */     else
/* 181 */       this.manifoldPtr.refreshContactPoints(this.rootTransA, this.rootTransB);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.dispatch.ManifoldResult
 * JD-Core Version:    0.6.2
 */