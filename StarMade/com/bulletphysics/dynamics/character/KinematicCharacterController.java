/*     */ package com.bulletphysics.dynamics.character;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*     */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*     */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*     */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
/*     */ import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
/*     */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*     */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.dynamics.ActionInterface;
/*     */ import com.bulletphysics.linearmath.IDebugDraw;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class KinematicCharacterController extends ActionInterface
/*     */ {
/*  55 */   private static Vector3f[] upAxisDirection = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*     */   protected float halfHeight;
/*     */   protected PairCachingGhostObject ghostObject;
/*     */   protected ConvexShape convexShape;
/*     */   protected float verticalVelocity;
/*     */   protected float verticalOffset;
/*     */   protected float fallSpeed;
/*     */   protected float jumpSpeed;
/*     */   protected float maxJumpHeight;
/*     */   protected float maxSlopeRadians;
/*     */   protected float maxSlopeCosine;
/*     */   protected float gravity;
/*     */   protected float turnAngle;
/*     */   protected float stepHeight;
/*     */   protected float addedMargin;
/*  88 */   protected Vector3f walkDirection = new Vector3f();
/*  89 */   protected Vector3f normalizedDirection = new Vector3f();
/*     */ 
/*  92 */   protected Vector3f currentPosition = new Vector3f();
/*     */   protected float currentStepOffset;
/*  94 */   protected Vector3f targetPosition = new Vector3f();
/*     */ 
/*  97 */   ObjectArrayList<PersistentManifold> manifoldArray = new ObjectArrayList();
/*     */   protected boolean touchingContact;
/* 100 */   protected Vector3f touchingNormal = new Vector3f();
/*     */   protected boolean wasOnGround;
/*     */   protected boolean useGhostObjectSweepTest;
/*     */   protected boolean useWalkDirection;
/*     */   protected float velocityTimeInterval;
/*     */   protected int upAxis;
/*     */   protected CollisionObject me;
/*     */ 
/*     */   public KinematicCharacterController(PairCachingGhostObject ghostObject, ConvexShape convexShape, float stepHeight)
/*     */   {
/* 112 */     this(ghostObject, convexShape, stepHeight, 1);
/*     */   }
/*     */ 
/*     */   public KinematicCharacterController(PairCachingGhostObject ghostObject, ConvexShape convexShape, float stepHeight, int upAxis) {
/* 116 */     this.upAxis = upAxis;
/* 117 */     this.addedMargin = 0.02F;
/* 118 */     this.walkDirection.set(0.0F, 0.0F, 0.0F);
/* 119 */     this.useGhostObjectSweepTest = true;
/* 120 */     this.ghostObject = ghostObject;
/* 121 */     this.stepHeight = stepHeight;
/* 122 */     this.turnAngle = 0.0F;
/* 123 */     this.convexShape = convexShape;
/* 124 */     this.useWalkDirection = true;
/* 125 */     this.velocityTimeInterval = 0.0F;
/* 126 */     this.verticalVelocity = 0.0F;
/* 127 */     this.verticalOffset = 0.0F;
/* 128 */     this.gravity = 9.8F;
/* 129 */     this.fallSpeed = 55.0F;
/* 130 */     this.jumpSpeed = 10.0F;
/* 131 */     this.wasOnGround = false;
/* 132 */     setMaxSlope(0.8726647F);
/*     */   }
/*     */ 
/*     */   private PairCachingGhostObject getGhostObject() {
/* 136 */     return this.ghostObject;
/*     */   }
/*     */ 
/*     */   public void updateAction(CollisionWorld collisionWorld, float deltaTime)
/*     */   {
/* 141 */     preStep(collisionWorld);
/* 142 */     playerStep(collisionWorld, deltaTime);
/*     */   }
/*     */ 
/*     */   public void debugDraw(IDebugDraw debugDrawer)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setUpAxis(int axis) {
/* 150 */     if (axis < 0) {
/* 151 */       axis = 0;
/*     */     }
/* 153 */     if (axis > 2) {
/* 154 */       axis = 2;
/*     */     }
/* 156 */     this.upAxis = axis;
/*     */   }
/*     */ 
/*     */   public void setWalkDirection(Vector3f arg1)
/*     */   {
/* 167 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.useWalkDirection = true;
/* 168 */       this.walkDirection.set(walkDirection);
/* 169 */       this.normalizedDirection.set(getNormalizedVector(walkDirection, localStack.get$javax$vecmath$Vector3f()));
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void setVelocityForTimeInterval(Vector3f arg1, float arg2)
/*     */   {
/* 179 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); this.useWalkDirection = false;
/* 180 */       this.walkDirection.set(velocity);
/* 181 */       this.normalizedDirection.set(getNormalizedVector(this.walkDirection, localStack.get$javax$vecmath$Vector3f()));
/* 182 */       this.velocityTimeInterval = timeInterval;
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void reset() {
/*     */   }
/*     */ 
/*     */   public void warp(Vector3f arg1) {
/* 189 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); Transform xform = localStack.get$com$bulletphysics$linearmath$Transform();
/* 190 */       xform.setIdentity();
/* 191 */       xform.origin.set(origin);
/* 192 */       this.ghostObject.setWorldTransform(xform);
/*     */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */   public void preStep(CollisionWorld arg1) {
/* 196 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); int numPenetrationLoops = 0;
/* 197 */       this.touchingContact = false;
/* 198 */       while (recoverFromPenetration(collisionWorld)) {
/* 199 */         numPenetrationLoops++;
/* 200 */         this.touchingContact = true;
/* 201 */         if (numPenetrationLoops > 4)
/*     */         {
/* 203 */           break;
/*     */         }
/* 207 */       }
/*     */ this.currentPosition.set(this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/* 208 */       this.targetPosition.set(this.currentPosition);
/*     */       return; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void playerStep(CollisionWorld arg1, float arg2)
/*     */   {
/* 217 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); if ((!this.useWalkDirection) && (this.velocityTimeInterval <= 0.0F))
/*     */       {
/* 219 */         return;
/*     */       }
/*     */ 
/* 222 */       this.wasOnGround = onGround();
/*     */ 
/* 225 */       this.verticalVelocity -= this.gravity * dt;
/* 226 */       if ((this.verticalVelocity > 0.0D) && (this.verticalVelocity > this.jumpSpeed))
/*     */       {
/* 228 */         this.verticalVelocity = this.jumpSpeed;
/*     */       }
/* 230 */       if ((this.verticalVelocity < 0.0D) && (Math.abs(this.verticalVelocity) > Math.abs(this.fallSpeed)))
/*     */       {
/* 232 */         this.verticalVelocity = (-Math.abs(this.fallSpeed));
/*     */       }
/* 234 */       this.verticalOffset = (this.verticalVelocity * dt);
/*     */ 
/* 236 */       Transform xform = this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/*     */ 
/* 241 */       stepUp(collisionWorld);
/* 242 */       if (this.useWalkDirection)
/*     */       {
/* 244 */         stepForwardAndStrafe(collisionWorld, this.walkDirection);
/*     */       }
/*     */       else {
/* 247 */         System.out.println("playerStep 4");
/*     */ 
/* 251 */         float dtMoving = dt < this.velocityTimeInterval ? dt : this.velocityTimeInterval;
/* 252 */         this.velocityTimeInterval -= dt;
/*     */ 
/* 255 */         Vector3f move = localStack.get$javax$vecmath$Vector3f();
/* 256 */         move.scale(dtMoving, this.walkDirection);
/*     */ 
/* 261 */         stepForwardAndStrafe(collisionWorld, move);
/*     */       }
/* 263 */       stepDown(collisionWorld, dt);
/*     */ 
/* 267 */       xform.origin.set(this.currentPosition);
/* 268 */       this.ghostObject.setWorldTransform(xform);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 269 */       .Stack tmp279_277 = localStack; tmp279_277.pop$com$bulletphysics$linearmath$Transform(); tmp279_277.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void setFallSpeed(float fallSpeed) {
/* 272 */     this.fallSpeed = fallSpeed;
/*     */   }
/*     */ 
/*     */   public void setJumpSpeed(float jumpSpeed) {
/* 276 */     this.jumpSpeed = jumpSpeed;
/*     */   }
/*     */ 
/*     */   public void setMaxJumpHeight(float maxJumpHeight) {
/* 280 */     this.maxJumpHeight = maxJumpHeight;
/*     */   }
/*     */ 
/*     */   public boolean canJump() {
/* 284 */     return onGround();
/*     */   }
/*     */ 
/*     */   public void jump() {
/* 288 */     if (!canJump()) return;
/*     */ 
/* 290 */     this.verticalVelocity = this.jumpSpeed;
/*     */   }
/*     */ 
/*     */   public void setGravity(float gravity)
/*     */   {
/* 304 */     this.gravity = gravity;
/*     */   }
/*     */ 
/*     */   public float getGravity() {
/* 308 */     return this.gravity;
/*     */   }
/*     */ 
/*     */   public void setMaxSlope(float slopeRadians) {
/* 312 */     this.maxSlopeRadians = slopeRadians;
/* 313 */     this.maxSlopeCosine = ((float)Math.cos(slopeRadians));
/*     */   }
/*     */ 
/*     */   public float getMaxSlope() {
/* 317 */     return this.maxSlopeRadians;
/*     */   }
/*     */ 
/*     */   public boolean onGround() {
/* 321 */     return (this.verticalVelocity == 0.0F) && (this.verticalOffset == 0.0F);
/*     */   }
/*     */ 
/*     */   private static Vector3f getNormalizedVector(Vector3f v, Vector3f out)
/*     */   {
/* 326 */     out.set(v);
/* 327 */     out.normalize();
/* 328 */     if (out.length() < 1.192093E-007F) {
/* 329 */       out.set(0.0F, 0.0F, 0.0F);
/*     */     }
/* 331 */     return out;
/*     */   }
/*     */ 
/*     */   protected Vector3f computeReflectionDirection(Vector3f direction, Vector3f normal, Vector3f out)
/*     */   {
/* 342 */     out.set(normal);
/* 343 */     out.scale(-2.0F * direction.dot(normal));
/* 344 */     out.add(direction);
/* 345 */     return out;
/*     */   }
/*     */ 
/*     */   protected Vector3f parallelComponent(Vector3f direction, Vector3f normal, Vector3f out)
/*     */   {
/* 354 */     out.set(normal);
/* 355 */     out.scale(direction.dot(normal));
/* 356 */     return out;
/*     */   }
/*     */ 
/*     */   protected Vector3f perpindicularComponent(Vector3f direction, Vector3f normal, Vector3f out)
/*     */   {
/* 364 */     Vector3f perpendicular = parallelComponent(direction, normal, out);
/* 365 */     perpendicular.scale(-1.0F);
/* 366 */     perpendicular.add(direction);
/* 367 */     return perpendicular;
/*     */   }
/*     */ 
/*     */   protected boolean recoverFromPenetration(CollisionWorld arg1) {
/* 371 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$linearmath$Transform(); boolean penetration = false;
/*     */ 
/* 373 */       collisionWorld.getDispatcher().dispatchAllCollisionPairs(this.ghostObject.getOverlappingPairCache(), collisionWorld.getDispatchInfo(), collisionWorld.getDispatcher());
/*     */ 
/* 376 */       this.currentPosition.set(this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
/*     */ 
/* 378 */       float maxPen = 0.0F;
/* 379 */       for (int i = 0; i < this.ghostObject.getOverlappingPairCache().getNumOverlappingPairs(); i++) {
/* 380 */         this.manifoldArray.clear();
/*     */ 
/* 382 */         BroadphasePair collisionPair = (BroadphasePair)this.ghostObject.getOverlappingPairCache().getOverlappingPairArray().getQuick(i);
/*     */ 
/* 384 */         if (collisionPair.algorithm != null) {
/* 385 */           collisionPair.algorithm.getAllContactManifolds(this.manifoldArray);
/*     */         }
/*     */ 
/* 388 */         for (int j = 0; j < this.manifoldArray.size(); j++) {
/* 389 */           PersistentManifold manifold = (PersistentManifold)this.manifoldArray.getQuick(j);
/* 390 */           float directionSign = manifold.getBody0() == this.ghostObject ? -1.0F : 1.0F;
/* 391 */           for (int p = 0; p < manifold.getNumContacts(); p++) {
/* 392 */             ManifoldPoint pt = manifold.getContactPoint(p);
/*     */ 
/* 394 */             float dist = pt.getDistance();
/* 395 */             if (dist < 0.0F) {
/* 396 */               if (dist < maxPen) {
/* 397 */                 maxPen = dist;
/* 398 */                 this.touchingNormal.set(pt.normalWorldOnB);
/* 399 */                 this.touchingNormal.scale(directionSign);
/*     */               }
/*     */ 
/* 402 */               this.currentPosition.scaleAdd(directionSign * dist * 0.2F, pt.normalWorldOnB, this.currentPosition);
/*     */ 
/* 404 */               penetration = true;
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 415 */       Transform newTrans = this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
/* 416 */       newTrans.origin.set(this.currentPosition);
/* 417 */       this.ghostObject.setWorldTransform(newTrans);
/*     */ 
/* 422 */       return penetration; } finally { localStack.pop$com$bulletphysics$linearmath$Transform(); } throw finally;
/*     */   }
/*     */ 
/*     */   protected void stepUp(CollisionWorld arg1)
/*     */   {
/* 427 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
/* 428 */       Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
/* 429 */       this.targetPosition.scaleAdd(this.stepHeight + (this.verticalOffset > 0.0D ? this.verticalOffset : 0.0F), upAxisDirection[this.upAxis], this.currentPosition);
/*     */ 
/* 431 */       start.setIdentity();
/* 432 */       end.setIdentity();
/*     */ 
/* 435 */       start.origin.scaleAdd(this.convexShape.getMargin() + this.addedMargin, upAxisDirection[this.upAxis], this.currentPosition);
/* 436 */       end.origin.set(this.targetPosition);
/*     */ 
/* 439 */       Vector3f up = localStack.get$javax$vecmath$Vector3f();
/* 440 */       up.scale(-1.0F, upAxisDirection[this.upAxis]);
/* 441 */       KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, up, 0.0F);
/* 442 */       callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
/* 443 */       callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
/*     */ 
/* 445 */       if (this.useGhostObjectSweepTest) {
/* 446 */         this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, world.getDispatchInfo().allowedCcdPenetration);
/*     */       }
/*     */       else {
/* 449 */         world.convexSweepTest(this.convexShape, start, end, callback);
/*     */       }
/*     */ 
/* 452 */       if (callback.hasHit())
/*     */       {
/* 454 */         this.currentStepOffset = (this.stepHeight * callback.closestHitFraction);
/* 455 */         this.currentPosition.interpolate(this.currentPosition, this.targetPosition, callback.closestHitFraction);
/* 456 */         this.verticalVelocity = 0.0F;
/* 457 */         this.verticalOffset = 0.0F;
/*     */       }
/*     */       else {
/* 460 */         this.currentStepOffset = this.stepHeight;
/* 461 */         this.currentPosition.set(this.targetPosition);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 463 */       .Stack tmp317_315 = localStack; tmp317_315.pop$com$bulletphysics$linearmath$Transform(); tmp317_315.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   protected void updateTargetPositionBasedOnCollision(Vector3f hitNormal) {
/* 466 */     updateTargetPositionBasedOnCollision(hitNormal, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   protected void updateTargetPositionBasedOnCollision(Vector3f arg1, float arg2, float arg3) {
/* 470 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f movementDirection = localStack.get$javax$vecmath$Vector3f();
/* 471 */       movementDirection.sub(this.targetPosition, this.currentPosition);
/* 472 */       float movementLength = movementDirection.length();
/* 473 */       if (movementLength > 1.192093E-007F) {
/* 474 */         movementDirection.normalize();
/*     */ 
/* 476 */         Vector3f reflectDir = computeReflectionDirection(movementDirection, hitNormal, localStack.get$javax$vecmath$Vector3f());
/* 477 */         reflectDir.normalize();
/*     */ 
/* 479 */         Vector3f parallelDir = parallelComponent(reflectDir, hitNormal, localStack.get$javax$vecmath$Vector3f());
/* 480 */         Vector3f perpindicularDir = perpindicularComponent(reflectDir, hitNormal, localStack.get$javax$vecmath$Vector3f());
/*     */ 
/* 482 */         this.targetPosition.set(this.currentPosition);
/*     */ 
/* 491 */         if (normalMag != 0.0F) {
/* 492 */           Vector3f perpComponent = localStack.get$javax$vecmath$Vector3f();
/* 493 */           perpComponent.scale(normalMag * movementLength, perpindicularDir);
/*     */ 
/* 495 */           this.targetPosition.add(perpComponent);
/*     */         }
/*     */       }
/*     */       return;
/*     */     } finally
/*     */     {
/* 501 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   protected void stepForwardAndStrafe(CollisionWorld arg1, Vector3f arg2)
/*     */   {
/* 507 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
/* 508 */       Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
/* 509 */       this.targetPosition.add(this.currentPosition, walkMove);
/* 510 */       start.setIdentity();
/* 511 */       end.setIdentity();
/*     */ 
/* 513 */       float fraction = 1.0F;
/* 514 */       Vector3f distance2Vec = localStack.get$javax$vecmath$Vector3f();
/* 515 */       distance2Vec.sub(this.currentPosition, this.targetPosition);
/* 516 */       float distance2 = distance2Vec.lengthSquared();
/*     */ 
/* 525 */       int maxIter = 10;
/*     */ 
/* 527 */       while ((fraction > 0.01F) && (maxIter-- > 0)) {
/* 528 */         start.origin.set(this.currentPosition);
/* 529 */         end.origin.set(this.targetPosition);
/*     */ 
/* 531 */         KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, upAxisDirection[this.upAxis], -1.0F);
/* 532 */         callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
/* 533 */         callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
/*     */ 
/* 535 */         float margin = this.convexShape.getMargin();
/* 536 */         this.convexShape.setMargin(margin + this.addedMargin);
/*     */ 
/* 538 */         if (this.useGhostObjectSweepTest) {
/* 539 */           this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, collisionWorld.getDispatchInfo().allowedCcdPenetration);
/*     */         }
/*     */         else {
/* 542 */           collisionWorld.convexSweepTest(this.convexShape, start, end, callback);
/*     */         }
/*     */ 
/* 545 */         this.convexShape.setMargin(margin);
/*     */ 
/* 547 */         fraction -= callback.closestHitFraction;
/*     */ 
/* 549 */         if (callback.hasHit())
/*     */         {
/* 551 */           Vector3f hitDistanceVec = localStack.get$javax$vecmath$Vector3f();
/* 552 */           hitDistanceVec.sub(callback.hitPointWorld, this.currentPosition);
/*     */ 
/* 561 */           updateTargetPositionBasedOnCollision(callback.hitNormalWorld);
/*     */ 
/* 563 */           Vector3f currentDir = localStack.get$javax$vecmath$Vector3f();
/* 564 */           currentDir.sub(this.targetPosition, this.currentPosition);
/* 565 */           distance2 = currentDir.lengthSquared();
/* 566 */           if (distance2 <= 1.192093E-007F) break;
/* 567 */           currentDir.normalize();
/*     */ 
/* 569 */           if (currentDir.dot(this.normalizedDirection) <= 0.0F)
/*     */           {
/*     */             break;
/*     */           }
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 580 */           this.currentPosition.set(this.targetPosition);
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 586 */       .Stack tmp389_387 = localStack; tmp389_387.pop$com$bulletphysics$linearmath$Transform(); tmp389_387.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   protected void stepDown(CollisionWorld arg1, float arg2) {
/* 589 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
/* 590 */       Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
/*     */ 
/* 593 */       float additionalDownStep = this.wasOnGround ? this.stepHeight : 0.0F;
/* 594 */       Vector3f step_drop = localStack.get$javax$vecmath$Vector3f();
/* 595 */       step_drop.scale(this.currentStepOffset + additionalDownStep, upAxisDirection[this.upAxis]);
/* 596 */       float downVelocity = ((additionalDownStep == 0.0F) && (this.verticalVelocity < 0.0F) ? -this.verticalVelocity : 0.0F) * dt;
/* 597 */       Vector3f gravity_drop = localStack.get$javax$vecmath$Vector3f();
/* 598 */       gravity_drop.scale(downVelocity, upAxisDirection[this.upAxis]);
/* 599 */       this.targetPosition.sub(step_drop);
/* 600 */       this.targetPosition.sub(gravity_drop);
/*     */ 
/* 602 */       start.setIdentity();
/* 603 */       end.setIdentity();
/*     */ 
/* 605 */       start.origin.set(this.currentPosition);
/* 606 */       end.origin.set(this.targetPosition);
/*     */ 
/* 608 */       KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, upAxisDirection[this.upAxis], this.maxSlopeCosine);
/* 609 */       callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
/* 610 */       callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
/*     */ 
/* 612 */       if (this.useGhostObjectSweepTest) {
/* 613 */         this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, collisionWorld.getDispatchInfo().allowedCcdPenetration);
/*     */       }
/*     */       else {
/* 616 */         collisionWorld.convexSweepTest(this.convexShape, start, end, callback);
/*     */       }
/*     */ 
/* 619 */       if (callback.hasHit())
/*     */       {
/* 621 */         this.currentPosition.interpolate(this.currentPosition, this.targetPosition, callback.closestHitFraction);
/* 622 */         this.verticalVelocity = 0.0F;
/* 623 */         this.verticalOffset = 0.0F;
/*     */       }
/*     */       else
/*     */       {
/* 627 */         this.currentPosition.set(this.targetPosition);
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 629 */       .Stack tmp337_335 = localStack; tmp337_335.pop$com$bulletphysics$linearmath$Transform(); tmp337_335.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   private static class KinematicClosestNotMeConvexResultCallback extends CollisionWorld.ClosestConvexResultCallback
/*     */   {
/*     */     protected CollisionObject me;
/*     */     protected final Vector3f up;
/*     */     protected float minSlopeDot;
/*     */ 
/*     */     public KinematicClosestNotMeConvexResultCallback(CollisionObject me, Vector3f up, float minSlopeDot)
/*     */     {
/* 659 */       super(new Vector3f());
/* 660 */       this.me = me;
/* 661 */       this.up = up;
/* 662 */       this.minSlopeDot = minSlopeDot;
/*     */     }
/*     */ 
/*     */     public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
/*     */     {
/* 667 */       .Stack localStack = .Stack.get();
/*     */       try
/*     */       {
/*     */         .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$linearmath$Transform(); tmp7_5.push$javax$vecmath$Vector3f(); if (convexResult.hitCollisionObject == this.me)
/* 668 */           return 1.0F;
/*     */         Vector3f hitNormalWorld;
/*     */         Vector3f hitNormalWorld;
/* 672 */         if (normalInWorldSpace) {
/* 673 */           hitNormalWorld = convexResult.hitNormalLocal;
/*     */         }
/*     */         else {
/* 676 */           hitNormalWorld = localStack.get$javax$vecmath$Vector3f();
/* 677 */           this.hitCollisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(convexResult.hitNormalLocal, hitNormalWorld);
/*     */         }
/*     */ 
/* 680 */         float dotUp = this.up.dot(hitNormalWorld);
/* 681 */         if (dotUp < this.minSlopeDot) {
/* 682 */           return 1.0F;
/*     */         }
/*     */ 
/* 685 */         return super.addSingleResult(convexResult, normalInWorldSpace);
/*     */       }
/*     */       finally
/*     */       {
/* 685 */         .Stack tmp126_124 = localStack; tmp126_124.pop$com$bulletphysics$linearmath$Transform(); tmp126_124.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class KinematicClosestNotMeRayResultCallback extends CollisionWorld.ClosestRayResultCallback
/*     */   {
/*     */     protected CollisionObject me;
/*     */ 
/*     */     public KinematicClosestNotMeRayResultCallback(CollisionObject me)
/*     */     {
/* 637 */       super(new Vector3f());
/* 638 */       this.me = me;
/*     */     }
/*     */ 
/*     */     public float addSingleResult(CollisionWorld.LocalRayResult rayResult, boolean normalInWorldSpace)
/*     */     {
/* 643 */       if (rayResult.collisionObject == this.me) {
/* 644 */         return 1.0F;
/*     */       }
/*     */ 
/* 647 */       return super.addSingleResult(rayResult, normalInWorldSpace);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.character.KinematicCharacterController
 * JD-Core Version:    0.6.2
 */