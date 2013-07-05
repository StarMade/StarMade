/*     */ package com.bulletphysics.collision.narrowphase;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.BulletGlobals;
/*     */ import com.bulletphysics.ContactDestroyedCallback;
/*     */ import com.bulletphysics.ContactProcessedCallback;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class PersistentManifold
/*     */ {
/*     */   public static final int MANIFOLD_CACHE_SIZE = 4;
/*  58 */   private final ManifoldPoint[] pointCache = new ManifoldPoint[4];
/*     */   private Object body0;
/*     */   private Object body1;
/*     */   private int cachedPoints;
/*     */   public int index1a;
/*     */ 
/*     */   public PersistentManifold()
/*     */   {
/*  68 */     for (int i = 0; i < this.pointCache.length; i++) this.pointCache[i] = new ManifoldPoint();  } 
/*  68 */   public PersistentManifold(Object body0, Object body1, int bla) { for (int i = 0; i < this.pointCache.length; i++) this.pointCache[i] = new ManifoldPoint();
/*     */ 
/*  75 */     init(body0, body1, bla); }
/*     */ 
/*     */   public void init(Object body0, Object body1, int bla)
/*     */   {
/*  79 */     this.body0 = body0;
/*  80 */     this.body1 = body1;
/*  81 */     this.cachedPoints = 0;
/*  82 */     this.index1a = 0;
/*     */   }
/*     */ 
/*     */   private int sortCachedPoints(ManifoldPoint arg1)
/*     */   {
/*  90 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Vector4f(); int maxPenetrationIndex = -1;
/*     */ 
/*  93 */       float maxPenetration = pt.getDistance();
/*  94 */       for (int i = 0; i < 4; i++) {
/*  95 */         if (this.pointCache[i].getDistance() < maxPenetration) {
/*  96 */           maxPenetrationIndex = i;
/*  97 */           maxPenetration = this.pointCache[i].getDistance();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 102 */       float res0 = 0.0F; float res1 = 0.0F; float res2 = 0.0F; float res3 = 0.0F;
/* 103 */       if (maxPenetrationIndex != 0) {
/* 104 */         Vector3f a0 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 105 */         a0.sub(this.pointCache[1].localPointA);
/*     */ 
/* 107 */         Vector3f b0 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
/* 108 */         b0.sub(this.pointCache[2].localPointA);
/*     */ 
/* 110 */         Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 111 */         cross.cross(a0, b0);
/*     */ 
/* 113 */         res0 = cross.lengthSquared();
/*     */       }
/*     */ 
/* 116 */       if (maxPenetrationIndex != 1) {
/* 117 */         Vector3f a1 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 118 */         a1.sub(this.pointCache[0].localPointA);
/*     */ 
/* 120 */         Vector3f b1 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
/* 121 */         b1.sub(this.pointCache[2].localPointA);
/*     */ 
/* 123 */         Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 124 */         cross.cross(a1, b1);
/* 125 */         res1 = cross.lengthSquared();
/*     */       }
/*     */ 
/* 128 */       if (maxPenetrationIndex != 2) {
/* 129 */         Vector3f a2 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 130 */         a2.sub(this.pointCache[0].localPointA);
/*     */ 
/* 132 */         Vector3f b2 = localStack.get$javax$vecmath$Vector3f(this.pointCache[3].localPointA);
/* 133 */         b2.sub(this.pointCache[1].localPointA);
/*     */ 
/* 135 */         Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 136 */         cross.cross(a2, b2);
/*     */ 
/* 138 */         res2 = cross.lengthSquared();
/*     */       }
/*     */ 
/* 141 */       if (maxPenetrationIndex != 3) {
/* 142 */         Vector3f a3 = localStack.get$javax$vecmath$Vector3f(pt.localPointA);
/* 143 */         a3.sub(this.pointCache[0].localPointA);
/*     */ 
/* 145 */         Vector3f b3 = localStack.get$javax$vecmath$Vector3f(this.pointCache[2].localPointA);
/* 146 */         b3.sub(this.pointCache[1].localPointA);
/*     */ 
/* 148 */         Vector3f cross = localStack.get$javax$vecmath$Vector3f();
/* 149 */         cross.cross(a3, b3);
/* 150 */         res3 = cross.lengthSquared();
/*     */       }
/*     */ 
/* 153 */       Vector4f maxvec = localStack.get$javax$vecmath$Vector4f();
/* 154 */       maxvec.set(res0, res1, res2, res3);
/* 155 */       return VectorUtil.closestAxis4(maxvec);
/*     */     }
/*     */     finally
/*     */     {
/* 156 */       .Stack tmp457_455 = localStack; tmp457_455.pop$javax$vecmath$Vector3f(); tmp457_455.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public Object getBody0()
/*     */   {
/* 162 */     return this.body0;
/*     */   }
/*     */ 
/*     */   public Object getBody1() {
/* 166 */     return this.body1;
/*     */   }
/*     */ 
/*     */   public void setBodies(Object body0, Object body1) {
/* 170 */     this.body0 = body0;
/* 171 */     this.body1 = body1;
/*     */   }
/*     */ 
/*     */   public void clearUserCache(ManifoldPoint pt) {
/* 175 */     Object oldPtr = pt.userPersistentData;
/* 176 */     if (oldPtr != null)
/*     */     {
/* 191 */       if ((pt.userPersistentData != null) && (BulletGlobals.getContactDestroyedCallback() != null)) {
/* 192 */         BulletGlobals.getContactDestroyedCallback().contactDestroyed(pt.userPersistentData);
/* 193 */         pt.userPersistentData = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getNumContacts()
/*     */   {
/* 203 */     return this.cachedPoints;
/*     */   }
/*     */ 
/*     */   public ManifoldPoint getContactPoint(int index) {
/* 207 */     return this.pointCache[index];
/*     */   }
/*     */ 
/*     */   public float getContactBreakingThreshold()
/*     */   {
/* 212 */     return BulletGlobals.getContactBreakingThreshold();
/*     */   }
/*     */ 
/*     */   public int getCacheEntry(ManifoldPoint arg1) {
/* 216 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); float shortestDist = getContactBreakingThreshold() * getContactBreakingThreshold();
/* 217 */       int size = getNumContacts();
/* 218 */       int nearestPoint = -1;
/* 219 */       Vector3f diffA = localStack.get$javax$vecmath$Vector3f();
/* 220 */       for (int i = 0; i < size; i++) {
/* 221 */         ManifoldPoint mp = this.pointCache[i];
/*     */ 
/* 223 */         diffA.sub(mp.localPointA, newPoint.localPointA);
/*     */ 
/* 225 */         float distToManiPoint = diffA.dot(diffA);
/* 226 */         if (distToManiPoint < shortestDist) {
/* 227 */           shortestDist = distToManiPoint;
/* 228 */           nearestPoint = i;
/*     */         }
/*     */       }
/* 231 */       return nearestPoint; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public int addManifoldPoint(ManifoldPoint newPoint) {
/* 235 */     assert (validContactDistance(newPoint));
/*     */ 
/* 237 */     int insertIndex = getNumContacts();
/* 238 */     if (insertIndex == 4)
/*     */     {
/* 242 */       insertIndex = sortCachedPoints(newPoint);
/*     */ 
/* 250 */       clearUserCache(this.pointCache[insertIndex]);
/*     */     }
/*     */     else {
/* 253 */       this.cachedPoints += 1;
/*     */     }
/* 255 */     assert (this.pointCache[insertIndex].userPersistentData == null);
/* 256 */     this.pointCache[insertIndex].set(newPoint);
/* 257 */     return insertIndex;
/*     */   }
/*     */ 
/*     */   public void removeContactPoint(int index) {
/* 261 */     clearUserCache(this.pointCache[index]);
/*     */ 
/* 263 */     int lastUsedIndex = getNumContacts() - 1;
/*     */ 
/* 265 */     if (index != lastUsedIndex)
/*     */     {
/* 267 */       this.pointCache[index].set(this.pointCache[lastUsedIndex]);
/*     */ 
/* 269 */       this.pointCache[lastUsedIndex].userPersistentData = null;
/* 270 */       this.pointCache[lastUsedIndex].appliedImpulse = 0.0F;
/* 271 */       this.pointCache[lastUsedIndex].lateralFrictionInitialized = false;
/* 272 */       this.pointCache[lastUsedIndex].appliedImpulseLateral1 = 0.0F;
/* 273 */       this.pointCache[lastUsedIndex].appliedImpulseLateral2 = 0.0F;
/* 274 */       this.pointCache[lastUsedIndex].lifeTime = 0;
/*     */     }
/*     */ 
/* 277 */     assert (this.pointCache[lastUsedIndex].userPersistentData == null);
/* 278 */     this.cachedPoints -= 1;
/*     */   }
/*     */ 
/*     */   public void replaceContactPoint(ManifoldPoint newPoint, int insertIndex) {
/* 282 */     assert (validContactDistance(newPoint));
/*     */ 
/* 286 */     int lifeTime = this.pointCache[insertIndex].getLifeTime();
/* 287 */     float appliedImpulse = this.pointCache[insertIndex].appliedImpulse;
/* 288 */     float appliedLateralImpulse1 = this.pointCache[insertIndex].appliedImpulseLateral1;
/* 289 */     float appliedLateralImpulse2 = this.pointCache[insertIndex].appliedImpulseLateral2;
/*     */ 
/* 291 */     assert (lifeTime >= 0);
/* 292 */     Object cache = this.pointCache[insertIndex].userPersistentData;
/*     */ 
/* 294 */     this.pointCache[insertIndex].set(newPoint);
/*     */ 
/* 296 */     this.pointCache[insertIndex].userPersistentData = cache;
/* 297 */     this.pointCache[insertIndex].appliedImpulse = appliedImpulse;
/* 298 */     this.pointCache[insertIndex].appliedImpulseLateral1 = appliedLateralImpulse1;
/* 299 */     this.pointCache[insertIndex].appliedImpulseLateral2 = appliedLateralImpulse2;
/*     */ 
/* 301 */     this.pointCache[insertIndex].lifeTime = lifeTime;
/*     */   }
/*     */ 
/*     */   private boolean validContactDistance(ManifoldPoint pt)
/*     */   {
/* 309 */     return pt.distance1 <= getContactBreakingThreshold();
/*     */   }
/*     */ 
/*     */   public void refreshContactPoints(Transform arg1, Transform arg2)
/*     */   {
/* 314 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 326 */       for (int i = getNumContacts() - 1; i >= 0; i--) {
/* 327 */         ManifoldPoint manifoldPoint = this.pointCache[i];
/*     */ 
/* 329 */         manifoldPoint.positionWorldOnA.set(manifoldPoint.localPointA);
/* 330 */         trA.transform(manifoldPoint.positionWorldOnA);
/*     */ 
/* 332 */         manifoldPoint.positionWorldOnB.set(manifoldPoint.localPointB);
/* 333 */         trB.transform(manifoldPoint.positionWorldOnB);
/*     */ 
/* 335 */         tmp.set(manifoldPoint.positionWorldOnA);
/* 336 */         tmp.sub(manifoldPoint.positionWorldOnB);
/* 337 */         manifoldPoint.distance1 = tmp.dot(manifoldPoint.normalWorldOnB);
/*     */ 
/* 339 */         manifoldPoint.lifeTime += 1;
/*     */       }
/*     */ 
/* 344 */       Vector3f projectedDifference = localStack.get$javax$vecmath$Vector3f(); Vector3f projectedPoint = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 346 */       for (i = getNumContacts() - 1; i >= 0; i--)
/*     */       {
/* 348 */         ManifoldPoint manifoldPoint = this.pointCache[i];
/*     */ 
/* 350 */         if (!validContactDistance(manifoldPoint)) {
/* 351 */           removeContactPoint(i);
/*     */         }
/*     */         else
/*     */         {
/* 355 */           tmp.scale(manifoldPoint.distance1, manifoldPoint.normalWorldOnB);
/* 356 */           projectedPoint.sub(manifoldPoint.positionWorldOnA, tmp);
/* 357 */           projectedDifference.sub(manifoldPoint.positionWorldOnB, projectedPoint);
/* 358 */           float distance2d = projectedDifference.dot(projectedDifference);
/* 359 */           if (distance2d > getContactBreakingThreshold() * getContactBreakingThreshold()) {
/* 360 */             removeContactPoint(i);
/*     */           }
/* 364 */           else if (BulletGlobals.getContactProcessedCallback() != null) {
/* 365 */             BulletGlobals.getContactProcessedCallback().contactProcessed(manifoldPoint, this.body0, this.body1);
/*     */           }
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 373 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void clearManifold() {
/* 377 */     for (int i = 0; i < this.cachedPoints; i++) {
/* 378 */       clearUserCache(this.pointCache[i]);
/*     */     }
/* 380 */     this.cachedPoints = 0;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.PersistentManifold
 * JD-Core Version:    0.6.2
 */