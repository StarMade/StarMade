/*      */ package org.schema.game.common.data.physics;
/*      */ 
/*      */ import com.bulletphysics.collision.broadphase.BroadphaseInterface;
/*      */ import com.bulletphysics.collision.broadphase.BroadphasePair;
/*      */ import com.bulletphysics.collision.broadphase.BroadphaseProxy;
/*      */ import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
/*      */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*      */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*      */ import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.dispatch.CollisionWorld;
/*      */ import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
/*      */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*      */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*      */ import com.bulletphysics.collision.shapes.CapsuleShape;
/*      */ import com.bulletphysics.collision.shapes.CollisionShape;
/*      */ import com.bulletphysics.collision.shapes.ConvexShape;
/*      */ import com.bulletphysics.dynamics.character.KinematicCharacterController;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import ct;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*      */ import jR;
/*      */ import java.io.PrintStream;
/*      */ import java.util.Iterator;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Matrix4f;
/*      */ import javax.vecmath.Vector3f;
/*      */ import lD;
/*      */ import le;
/*      */ import org.schema.game.common.controller.SegmentController;
/*      */ import org.schema.schine.graphicsengine.core.GlUtil;
/*      */ import org.schema.schine.network.NetworkStateContainer;
/*      */ import org.schema.schine.network.StateInterface;
/*      */ import org.schema.schine.network.objects.Sendable;
/*      */ import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*      */ import org.schema.schine.network.server.ServerStateInterface;
/*      */ import xq;
/*      */ 
/*      */ public class KinematicCharacterControllerExt extends KinematicCharacterController
/*      */ {
/*  128 */   private float extendedCharacterHeight = 1.7F;
/*      */ 
/*  130 */   public static Vector3f[] upAxisDirectionDefault = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*      */ 
/*  136 */   public Vector3f[] upAxisDirection = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
/*      */   private lD obj;
/*  142 */   Vector3f move = new Vector3f();
/*  143 */   Transform xform = new Transform();
/*  144 */   Transform start = new Transform();
/*      */ 
/*  146 */   Transform end = new Transform();
/*  147 */   Vector3f distance2Vec = new Vector3f();
/*  148 */   Vector3f hitDistanceVec = new Vector3f();
/*  149 */   Vector3f currentDir = new Vector3f();
/*  150 */   Transform tmp = new Transform();
/*  151 */   Vector3f before = new Vector3f();
/*      */   Vector3f after;
/*      */   int penetrationCounter;
/*      */   private CapsuleShape capsule;
/*      */   private CapsuleShape strafeCapsule;
/*  160 */   ObjectArrayList manifoldArray = new ObjectArrayList();
/*  161 */   Transform ttmp = new Transform();
/*      */ 
/*  163 */   Vector3f normal = new Vector3f();
/*      */   private float localJumpSpeed;
/*      */   private long jumpingStarted;
/*  166 */   private Vector3f velocity = new Vector3f();
/*      */ 
/*      */   public KinematicCharacterControllerExt(lD paramlD, PairCachingGhostObjectExt paramPairCachingGhostObjectExt, ConvexShape paramConvexShape, float paramFloat) {
/*  169 */     super(paramPairCachingGhostObjectExt, paramConvexShape, paramFloat);
/*  170 */     this.obj = paramlD;
/*      */   }
/*      */ 
/*      */   public void setLocalJumpSpeed(float paramFloat)
/*      */   {
/*  175 */     this.localJumpSpeed = paramFloat;
/*      */   }
/*      */ 
/*      */   public void breakJump(xq paramxq)
/*      */   {
/*  182 */     if ((this.verticalVelocity > 0.0F) && (this.jumpingStarted != -1L))
/*      */     {
/*  184 */       if (System.currentTimeMillis() - this.jumpingStarted > 
/*  184 */         80L)
/*      */       {
/*  186 */         this.verticalVelocity = Math.max(0.0F, this.verticalVelocity - paramxq.a() * 50.0F);
/*      */       }
/*      */ 
/*  189 */       return;
/*  190 */     }this.jumpingStarted = -1L;
/*      */   }
/*      */ 
/*      */   public void jump()
/*      */   {
/*  196 */     if (!canJump())
/*      */     {
/*  198 */       return;
/*      */     }
/*      */ 
/*  201 */     this.jumpingStarted = System.currentTimeMillis();
/*  202 */     this.verticalVelocity = this.jumpSpeed;
/*      */   }
/*      */ 
/*      */   public void loadAttached(CollisionWorld paramCollisionWorld)
/*      */   {
/*  218 */     if ((
/*  218 */       paramCollisionWorld = (PairCachingGhostObjectAlignable)this.ghostObject)
/*  218 */       .getAttached() != null)
/*      */     {
/*  220 */       Object localObject = paramCollisionWorld.getAttached().getWorldTransform();
/*      */ 
/*  222 */       if (paramCollisionWorld.getAttached().getPhysicsDataContainer().getObject() != null) {
/*  223 */         paramCollisionWorld.getAttached().getPhysicsDataContainer().getObject().activate(true);
/*      */       }
/*      */ 
/*  233 */       GlUtil.e(this.upAxisDirection[0], (Transform)localObject);
/*  234 */       GlUtil.f(this.upAxisDirection[1], (Transform)localObject);
/*  235 */       GlUtil.c(this.upAxisDirection[2], (Transform)localObject);
/*      */ 
/*  240 */       localObject = ((Transform)localObject).getMatrix(new Matrix4f());
/*  241 */       Matrix4f localMatrix4f = paramCollisionWorld.localWorldTransform.getMatrix(new Matrix4f());
/*      */ 
/*  244 */       (
/*  245 */         localObject = new Matrix4f((Matrix4f)localObject))
/*  245 */         .mul(localMatrix4f);
/*      */ 
/*  247 */       paramCollisionWorld.setWorldTransform(new Transform((Matrix4f)localObject));
/*      */     }
/*      */   }
/*      */ 
/*      */   public void updateAction(CollisionWorld paramCollisionWorld, float paramFloat)
/*      */   {
/*  258 */     preStep(paramCollisionWorld);
/*      */ 
/*  260 */     playerStep(paramCollisionWorld, paramFloat);
/*      */ 
/*  262 */     this.obj.getPhysicsDataContainer().updatePhysical();
/*  263 */     if ((!this.obj.isOnServer()) && (((ct)this.obj.getState()).a() == this.obj))
/*  264 */       this.obj.a = true;
/*      */   }
/*      */ 
/*      */   private void adaptLocalAttached()
/*      */   {
/*      */     PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable;
/*  269 */     if ((
/*  269 */       localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)this.ghostObject)
/*  269 */       .getAttached() != null)
/*      */     {
/*  271 */       Matrix4f localMatrix4f1 = localPairCachingGhostObjectAlignable.getAttached().getWorldTransform().getMatrix(new Matrix4f());
/*  272 */       new Matrix4f(localMatrix4f1);
/*  273 */       localPairCachingGhostObjectAlignable.localWorldTransform.getMatrix(new Matrix4f());
/*      */ 
/*  275 */       (
/*  276 */         localMatrix4f1 = new Matrix4f(localMatrix4f1))
/*  276 */         .invert();
/*      */ 
/*  278 */       Matrix4f localMatrix4f2 = this.xform.getMatrix(new Matrix4f());
/*      */ 
/*  280 */       localMatrix4f1.mul(localMatrix4f2);
/*      */ 
/*  282 */       localPairCachingGhostObjectAlignable.localWorldTransform.set(localMatrix4f1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void preStep(CollisionWorld paramCollisionWorld)
/*      */   {
/*  292 */     loadAttached(paramCollisionWorld);
/*      */ 
/*  294 */     int i = 0;
/*      */ 
/*  296 */     Vector3f localVector3f = this.ghostObject.getWorldTransform(this.ttmp).origin;
/*  297 */     this.currentPosition.set(localVector3f);
/*      */ 
/*  299 */     this.touchingContact = false;
/*      */     do { if (!recoverFromPenetration(paramCollisionWorld)) break;
/*  301 */       i++;
/*  302 */       this.touchingContact = true; }
/*  303 */     while (i <= 4);
/*      */ 
/*  305 */     this.targetPosition.set(this.currentPosition);
/*      */   }
/*      */ 
/*      */   public void playerStep(CollisionWorld paramCollisionWorld, float paramFloat)
/*      */   {
/*  325 */     if ((!this.useWalkDirection) && (this.velocityTimeInterval <= 0.0F))
/*      */     {
/*  330 */       return;
/*      */     }
/*      */ 
/*  333 */     PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)this.ghostObject;
/*      */ 
/*  336 */     this.wasOnGround = onGround();
/*      */ 
/*  339 */     this.verticalVelocity -= this.gravity * paramFloat;
/*      */ 
/*  342 */     if ((this.verticalVelocity > 0.0D) && (this.verticalVelocity > this.jumpSpeed)) {
/*  343 */       this.verticalVelocity = this.jumpSpeed;
/*      */     }
/*  345 */     if ((this.verticalVelocity < 0.0D) && (Math.abs(this.verticalVelocity) > Math.abs(this.fallSpeed)))
/*      */     {
/*  347 */       this.verticalVelocity = (-Math.abs(this.fallSpeed));
/*      */     }
/*  349 */     this.verticalOffset = (this.verticalVelocity * paramFloat);
/*      */ 
/*  354 */     localPairCachingGhostObjectAlignable.getWorldTransform(this.xform);
/*      */ 
/*  356 */     this.velocity.set(this.xform.origin);
/*      */ 
/*  365 */     stepUp(paramCollisionWorld);
/*  366 */     if (this.useWalkDirection)
/*      */     {
/*  368 */       stepForwardAndStrafe(paramCollisionWorld, this.walkDirection);
/*      */     }
/*      */     else
/*      */     {
/*  374 */       float f = paramFloat < this.velocityTimeInterval ? paramFloat : this.velocityTimeInterval;
/*      */ 
/*  376 */       this.velocityTimeInterval -= paramFloat;
/*      */ 
/*  380 */       this.move.scale(f, this.walkDirection);
/*      */ 
/*  385 */       stepForwardAndStrafe(paramCollisionWorld, this.move);
/*      */     }
/*      */ 
/*  392 */     if (this.gravity > 0.0F) {
/*  393 */       stepDown(paramCollisionWorld, paramFloat);
/*      */     }
/*      */ 
/*  398 */     this.xform.origin.set(this.currentPosition);
/*      */ 
/*  400 */     GlUtil.c(this.upAxisDirection[0], this.xform);
/*  401 */     GlUtil.d(this.upAxisDirection[1], this.xform);
/*  402 */     GlUtil.a(this.upAxisDirection[2], this.xform);
/*      */ 
/*  424 */     localPairCachingGhostObjectAlignable.setWorldTransform(this.xform);
/*  425 */     adaptLocalAttached();
/*      */ 
/*  428 */     this.velocity.sub(this.xform.origin);
/*  429 */     this.velocity.negate();
/*  430 */     this.walkDirection.set(0.0F, 0.0F, 0.0F);
/*  431 */     this.velocityTimeInterval = 0.0F;
/*      */   }
/*      */ 
/*      */   protected boolean recoverFromPenetration(CollisionWorld paramCollisionWorld) {
/*  435 */     boolean bool = false;
/*      */ 
/*  444 */     Vector3f localVector3f = new Vector3f();
/*  445 */     Object localObject = new Vector3f();
/*  446 */     this.convexShape.getAabb(this.ghostObject.getWorldTransform(new Transform()), localVector3f, (Vector3f)localObject);
/*  447 */     paramCollisionWorld.getBroadphase().setAabb(this.ghostObject.getBroadphaseHandle(), localVector3f, (Vector3f)localObject, paramCollisionWorld.getDispatcher());
/*      */ 
/*  453 */     paramCollisionWorld.getDispatcher().dispatchAllCollisionPairs(this.ghostObject.getOverlappingPairCache(), paramCollisionWorld.getDispatchInfo(), paramCollisionWorld.getDispatcher());
/*      */ 
/*  456 */     this.currentPosition.set(this.ghostObject.getWorldTransform(this.ttmp).origin);
/*      */ 
/*  458 */     paramCollisionWorld = 0.0F;
/*  459 */     for (int i = 0; i < this.ghostObject.getOverlappingPairCache().getNumOverlappingPairs(); i++) {
/*  460 */       this.manifoldArray.clear();
/*      */ 
/*  464 */       if ((
/*  464 */         localObject = (BroadphasePair)this.ghostObject.getOverlappingPairCache().getOverlappingPairArray().getQuick(i)).algorithm != null)
/*      */       {
/*  465 */         ((BroadphasePair)localObject).algorithm.getAllContactManifolds(this.manifoldArray);
/*      */       }
/*      */ 
/*  468 */       for (int j = 0; j < this.manifoldArray.size(); j++)
/*      */       {
/*      */         PersistentManifold localPersistentManifold;
/*  470 */         float f1 = (
/*  470 */           localPersistentManifold = (PersistentManifold)this.manifoldArray.getQuick(j))
/*  470 */           .getBody0() == this.ghostObject ? -1.0F : 1.0F;
/*  471 */         for (int k = 0; k < localPersistentManifold.getNumContacts(); k++)
/*      */         {
/*      */           ManifoldPoint localManifoldPoint;
/*      */           float f2;
/*  475 */           if ((
/*  475 */             f2 = (
/*  474 */             localManifoldPoint = localPersistentManifold.getContactPoint(k))
/*  474 */             .getDistance()) < 
/*  475 */             0.1F)
/*      */           {
/*  483 */             if (f2 < paramCollisionWorld)
/*      */             {
/*  485 */               paramCollisionWorld = f2;
/*  486 */               this.touchingNormal.set(localManifoldPoint.normalWorldOnB);
/*  487 */               this.touchingNormal.scale(f1);
/*      */             }
/*      */ 
/*  497 */             this.currentPosition.scaleAdd(f1 * f2 * 0.2F, localManifoldPoint.normalWorldOnB, this.currentPosition);
/*      */ 
/*  503 */             bool = true;
/*      */           }
/*      */           else {
/*  506 */             this.obj.getState();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     Transform localTransform;
/*  517 */     (
/*  518 */       localTransform = this.ghostObject.getWorldTransform(this.ttmp)).origin
/*  518 */       .set(this.currentPosition);
/*  519 */     this.ghostObject.setWorldTransform(localTransform);
/*      */ 
/*  524 */     return bool;
/*      */   }
/*      */ 
/*      */   public void setVelocityForTimeIntervalStacked(Vector3f paramVector3f, float paramFloat)
/*      */   {
/*  540 */     this.useWalkDirection = false;
/*  541 */     this.walkDirection.add(paramVector3f);
/*  542 */     this.normal.set(this.walkDirection);
/*  543 */     this.normal.normalize();
/*  544 */     this.normalizedDirection.set(this.normal);
/*  545 */     this.velocityTimeInterval += paramFloat;
/*      */   }
/*      */ 
/*      */   public void setWalkDirectionStacked(Vector3f paramVector3f)
/*      */   {
/*  568 */     this.useWalkDirection = true;
/*  569 */     this.walkDirection.add(paramVector3f);
/*  570 */     this.normal.set(this.walkDirection);
/*  571 */     this.normal.normalize();
/*  572 */     this.normalizedDirection.set(this.normal);
/*      */   }
/*      */ 
/*      */   protected void stepDown(CollisionWorld paramCollisionWorld, float paramFloat)
/*      */   {
/*  578 */     if (this.obj.isOnServer()) {
/*  579 */       SubsimplexCubesCovexCast.mode = "DWN";
/*      */     }
/*  581 */     Transform localTransform1 = new Transform();
/*  582 */     Transform localTransform2 = new Transform();
/*      */ 
/*  585 */     float f1 = this.wasOnGround ? this.stepHeight : 0.0F;
/*      */     Vector3f localVector3f2;
/*  586 */     (
/*  587 */       localVector3f2 = new Vector3f())
/*  587 */       .scale(this.currentStepOffset + f1, this.upAxisDirection[this.upAxis]);
/*      */ 
/*  589 */     paramFloat = ((f1 == 0.0F) && (this.verticalVelocity < 0.0F) ? -this.verticalVelocity : 0.0F) * paramFloat;
/*      */     Vector3f localVector3f1;
/*  591 */     (
/*  593 */       localVector3f1 = new Vector3f())
/*  593 */       .scale(paramFloat, this.upAxisDirection[this.upAxis]);
/*      */ 
/*  595 */     this.targetPosition.sub(localVector3f2);
/*  596 */     this.targetPosition.sub(localVector3f1);
/*      */ 
/*  598 */     localTransform1.setIdentity();
/*  599 */     localTransform2.setIdentity();
/*      */ 
/*  610 */     localTransform1.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  611 */     localTransform2.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*      */ 
/*  613 */     localTransform1.origin.set(this.currentPosition);
/*  614 */     localTransform2.origin.set(this.targetPosition);
/*      */ 
/*  618 */     paramFloat = this.ghostObject.getCollisionShape().getMargin();
/*      */ 
/*  621 */     if (this.capsule == null) {
/*  622 */       float f2 = this.obj.b();
/*  623 */       float f3 = this.extendedCharacterHeight;
/*  624 */       this.capsule = new CapsuleShape(f2, f3);
/*      */ 
/*  626 */       this.capsule.setMargin(0.1F);
/*      */     }
/*      */     KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
/*  629 */     (
/*  630 */       localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], this.maxSlopeCosine)).collisionFilterGroup = 
/*  630 */       this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*  631 */     localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*      */     Vector3f localVector3f3;
/*  633 */     (
/*  634 */       localVector3f3 = new Vector3f())
/*  634 */       .sub(this.targetPosition, this.currentPosition);
/*  635 */     if (localVector3f3.length() == 0.0F)
/*      */     {
/*  639 */       return;
/*      */     }
/*      */ 
/*  642 */     new Vector3f(this.currentPosition);
/*  643 */     (
/*  644 */       localVector3f3 = new Vector3f(localVector3f3))
/*  644 */       .normalize();
/*  645 */     localVector3f3.scale(0.999F);
/*  646 */     localVector3f3.add(this.currentPosition);
/*      */ 
/*  664 */     if (this.useGhostObjectSweepTest) {
/*  665 */       this.ghostObject.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
/*      */     }
/*      */     else {
/*  668 */       paramCollisionWorld.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback);
/*      */     }
/*      */ 
/*  671 */     if (localKinematicClosestNotMeConvexResultCallback.hasHit())
/*      */     {
/*  681 */       this.currentPosition.interpolate(this.currentPosition, this.targetPosition, localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
/*  682 */       this.verticalVelocity = 0.0F;
/*  683 */       this.verticalOffset = 0.0F;
/*      */ 
/*  689 */       if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null)
/*  690 */         this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));
/*      */       else {
/*  692 */         System.err.println("Exception: Kinmatik: NO SEGMENT TO HANDLE AFTER COLLISION (LIFT?)");
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  699 */       if (this.targetPosition.epsilonEquals(this.currentPosition, 1.0E-007F)) {
/*  700 */         this.verticalVelocity = 0.0F;
/*  701 */         this.verticalOffset = 0.0F;
/*      */       }
/*      */ 
/*  705 */       this.currentPosition.set(this.targetPosition);
/*      */     }
/*      */ 
/*  709 */     this.ghostObject.getCollisionShape().setMargin(paramFloat);
/*      */   }
/*      */ 
/*      */   protected void stepForwardAndStrafe(CollisionWorld paramCollisionWorld, Vector3f paramVector3f)
/*      */   {
/*  716 */     if (this.obj.isOnServer())
/*      */     {
/*  719 */       SubsimplexCubesCovexCast.mode = "FW";
/*      */     }
/*      */ 
/*  724 */     this.targetPosition.add(this.currentPosition, paramVector3f);
/*  725 */     this.start.setIdentity();
/*  726 */     this.end.setIdentity();
/*      */ 
/*  734 */     this.start.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  735 */     this.end.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*      */ 
/*  737 */     paramVector3f = 1.0F;
/*      */ 
/*  739 */     this.distance2Vec.sub(this.targetPosition, this.currentPosition);
/*      */ 
/*  745 */     float f = 0.0F; if (this.distance2Vec.lengthSquared() == 
/*  745 */       0.0F) {
/*  746 */       return;
/*      */     }
/*      */ 
/*  769 */     if (this.strafeCapsule == null) {
/*  770 */       f = this.obj.a();
/*      */ 
/*  772 */       this.strafeCapsule = new CapsuleShape(0.3F, f);
/*      */ 
/*  774 */       this.strafeCapsule.setMargin(0.1F);
/*      */     }
/*      */ 
/*  778 */     int i = 10;
/*      */ 
/*  780 */     this.before.set(this.currentPosition);
/*      */ 
/*  783 */     while ((paramVector3f > 0.01F) && (i-- > 0))
/*      */     {
/*  787 */       this.start.origin.set(this.currentPosition);
/*  788 */       this.end.origin.set(this.targetPosition);
/*      */       KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
/*  790 */       (
/*  795 */         localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], -1.0F)).collisionFilterGroup = 
/*  795 */         this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*      */ 
/*  797 */       localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*      */ 
/*  800 */       f = this.convexShape.getMargin();
/*  801 */       this.convexShape.setMargin(f + this.addedMargin);
/*      */ 
/*  804 */       if (this.useGhostObjectSweepTest)
/*      */       {
/*  806 */         this.ghostObject.convexSweepTest(this.strafeCapsule, this.start, this.end, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
/*      */       }
/*      */       else {
/*  809 */         paramCollisionWorld.convexSweepTest(this.convexShape, this.start, this.end, localKinematicClosestNotMeConvexResultCallback);
/*      */       }
/*      */ 
/*  813 */       this.convexShape.setMargin(f);
/*      */ 
/*  815 */       paramVector3f -= localKinematicClosestNotMeConvexResultCallback.closestHitFraction;
/*  816 */       if (localKinematicClosestNotMeConvexResultCallback.hasHit()) {
/*  817 */         this.hitDistanceVec.sub(localKinematicClosestNotMeConvexResultCallback.hitPointWorld, this.currentPosition);
/*      */ 
/*  822 */         this.hitDistanceVec.length();
/*      */ 
/*  830 */         updateTargetPositionBasedOnCollision(localKinematicClosestNotMeConvexResultCallback.hitNormalWorld);
/*      */ 
/*  833 */         this.currentDir.sub(this.targetPosition, this.currentPosition);
/*      */ 
/*  835 */         if (this.currentDir.lengthSquared() > 
/*  835 */           1.192093E-007F) {
/*  836 */           this.currentDir.normalize();
/*      */ 
/*  839 */           if (this.currentDir.dot(this.normalizedDirection) <= 0.0F) {
/*  840 */             if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  841 */               this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback))); break;
/*      */             }
/*  843 */             System.err.println("[KINEMATIC] collision: No Segment To handle");
/*      */ 
/*  845 */             break;
/*      */           }
/*      */         } else {
/*  848 */           if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  849 */             this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback))); break;
/*      */           }
/*  851 */           System.err.println("[KINEMATIC] collision: No Segment To handle");
/*      */ 
/*  854 */           break;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  859 */         this.currentPosition.set(this.targetPosition);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  865 */     this.obj.getState();
/*      */ 
/*  872 */     if (this.after == null) {
/*  873 */       this.after = new Vector3f();
/*      */     }
/*  875 */     this.after.set(this.currentPosition);
/*      */   }
/*      */ 
/*      */   protected void stepUp(CollisionWorld paramCollisionWorld)
/*      */   {
/*  881 */     if (this.obj.isOnServer()) {
/*  882 */       SubsimplexCubesCovexCast.mode = "UP";
/*      */     }
/*      */ 
/*  885 */     Transform localTransform1 = new Transform();
/*  886 */     Transform localTransform2 = new Transform();
/*  887 */     this.targetPosition.scaleAdd(this.stepHeight + (this.verticalOffset > 0.0D ? this.verticalOffset : 0.0F), this.upAxisDirection[this.upAxis], this.currentPosition);
/*      */ 
/*  889 */     if (this.capsule == null) {
/*  890 */       float f1 = this.obj.b();
/*  891 */       float f2 = this.extendedCharacterHeight;
/*  892 */       this.capsule = new CapsuleShape(f1, f2);
/*      */ 
/*  894 */       this.capsule.setMargin(0.1F);
/*      */     }
/*      */ 
/*  897 */     localTransform1.setIdentity();
/*  898 */     localTransform2.setIdentity();
/*  899 */     assert (this.ghostObject.getWorldTransform(new Transform()).getMatrix(new Matrix4f()).determinant() != 0.0F) : this.ghostObject.getWorldTransform(new Transform()).getMatrix(new Matrix4f());
/*  900 */     localTransform1.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*  901 */     localTransform2.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
/*      */ 
/*  904 */     localTransform1.origin.set(this.currentPosition);
/*  905 */     localTransform2.origin.set(this.targetPosition);
/*      */     Vector3f localVector3f;
/*  909 */     (
/*  910 */       localVector3f = new Vector3f())
/*  910 */       .scale(-1.0F, this.upAxisDirection[this.upAxis]);
/*      */     KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
/*  911 */     (
/*  912 */       localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, localVector3f, 0.1F)).collisionFilterGroup = 
/*  912 */       this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*  913 */     localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*      */ 
/*  915 */     if (this.useGhostObjectSweepTest) {
/*  916 */       this.ghostObject.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
/*      */     }
/*      */     else {
/*  919 */       paramCollisionWorld.convexSweepTest(this.convexShape, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback);
/*      */     }
/*      */ 
/*  922 */     if (localKinematicClosestNotMeConvexResultCallback.hasHit())
/*      */     {
/*  926 */       this.currentStepOffset = (this.stepHeight * localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
/*  927 */       this.currentPosition.interpolate(this.currentPosition, this.targetPosition, localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
/*  928 */       this.verticalVelocity = -1.0E-008F;
/*  929 */       this.verticalOffset = 0.0F;
/*  930 */       this.jumpingStarted = -1L;
/*      */ 
/*  933 */       if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
/*  934 */         this.obj.a(new le(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback))); return;
/*      */       }
/*  936 */       System.err.println("[KINEMATIC] collision: No Segment To handle"); return;
/*      */     }
/*      */ 
/*  940 */     this.currentStepOffset = this.stepHeight;
/*  941 */     this.currentPosition.set(this.targetPosition);
/*      */   }
/*      */ 
/*      */   public void stopJump()
/*      */   {
/*  948 */     this.verticalVelocity = 0.0F;
/*      */   }
/*      */ 
/*      */   public void warpOutOfCollision(StateInterface paramStateInterface, CollisionWorld paramCollisionWorld, Transform paramTransform)
/*      */   {
/*  954 */     warp(paramTransform.origin);
/*      */ 
/*  961 */     this.start.setIdentity();
/*  962 */     this.end.setIdentity();
/*      */ 
/*  964 */     this.distance2Vec.sub(paramTransform.origin, this.targetPosition);
/*  965 */     new Vector3f();
/*  966 */     paramCollisionWorld.getBroadphase().calculateOverlappingPairs(paramCollisionWorld.getDispatcher());
/*      */ 
/*  968 */     paramCollisionWorld = 4;
/*      */     Vector3f localVector3f;
/*  972 */     GlUtil.c(localVector3f = new Vector3f(), 
/*  972 */       paramTransform);
/*      */ 
/*  974 */     int j = 0;
/*      */ 
/*  981 */     this.targetPosition.set(paramTransform.origin);
/*      */ 
/*  985 */     boolean bool1 = false;
/*      */ 
/*  990 */     warp(this.targetPosition);
/*      */     Object localObject2;
/*      */     Object localObject3;
/*      */     Object localObject4;
/*      */     Object localObject5;
/*  992 */     synchronized (paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
/*      */     {
/*      */       ObjectCollection localObjectCollection;
/*  994 */       if ((paramStateInterface instanceof ServerStateInterface))
/*  995 */         localObjectCollection = paramStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values();
/*      */       else {
/*  997 */         localObjectCollection = ((ct)paramStateInterface).a().values();
/*      */       }
/*  999 */       for (localObject2 = localObjectCollection.iterator(); ((Iterator)localObject2).hasNext(); ) {
/* 1000 */         if (((
/* 1000 */           localObject3 = (Sendable)((Iterator)localObject2).next()) instanceof SegmentController))
/*      */         {
/* 1002 */           if ((
/* 1002 */             localObject4 = (SegmentController)localObject3)
/* 1002 */             .getSectorId() == this.obj.getSectorId()) {
/* 1003 */             (
/* 1004 */               localObject5 = new Transform())
/* 1004 */               .setIdentity();
/* 1005 */             ((Transform)localObject5).origin.set(this.targetPosition);
/*      */ 
/* 1008 */             if ((
/* 1008 */               bool1 = ((SegmentController)localObject4).getCollisionChecker().a((SegmentController)localObject4, (Transform)localObject5)))
/*      */             {
/*      */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1017 */     if (localObject1 == 0) {
/* 1018 */       System.err.println("[WARPING OT OF COLLISION][CHECK_SINGLE] NOHIT -> can spawn here!! " + this.targetPosition);
/*      */     }
/*      */ 
/* 1024 */     while ((localObject1 != 0) && (j < 10000)) {
/* 1025 */       this.start.origin.set(this.targetPosition);
/*      */ 
/* 1027 */       switch (paramCollisionWorld) { case 4:
/* 1028 */         GlUtil.c(localVector3f, paramTransform); break;
/*      */       case 5:
/* 1029 */         GlUtil.a(localVector3f, paramTransform); break;
/*      */       case 2:
/* 1030 */         GlUtil.f(localVector3f, paramTransform); break;
/*      */       case 3:
/* 1031 */         GlUtil.b(localVector3f, paramTransform); break;
/*      */       case 1:
/* 1032 */         GlUtil.d(localVector3f, paramTransform); break;
/*      */       case 0:
/* 1033 */         GlUtil.e(localVector3f, paramTransform);
/*      */       }
/* 1035 */       paramCollisionWorld = (paramCollisionWorld + 1) % 6;
/*      */ 
/* 1037 */       localVector3f.scale(1.0F + j / 6 * 0.2F);
/*      */ 
/* 1039 */       this.targetPosition.add(paramTransform.origin, localVector3f);
/* 1040 */       this.end.origin.set(this.targetPosition);
/*      */ 
/* 1045 */       ??? = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], -1.0F);
/*      */ 
/* 1048 */       if (this.ghostObject == null) {
/* 1049 */         System.err.println("Exception KinematikCharacter: warp out of collision ghost object " + this.ghostObject + " is null " + this.obj);
/* 1050 */         return;
/*      */       }
/* 1052 */       if (this.ghostObject.getBroadphaseHandle() == null)
/*      */       {
/* 1054 */         System.err.println("Exception KinematikCharacter: warp out of collision broadphase handle of ghost object " + this.ghostObject + " is null " + this.obj);
/* 1055 */         return;
/*      */       }
/* 1057 */       ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)???).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
/*      */ 
/* 1059 */       ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)???).collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
/*      */ 
/* 1063 */       int i = 0;
/*      */ 
/* 1071 */       warp(this.targetPosition);
/*      */       boolean bool2;
/* 1072 */       synchronized (paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
/*      */       {
/* 1074 */         if ((paramStateInterface instanceof ServerStateInterface))
/* 1075 */           localObject2 = paramStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values();
/*      */         else {
/* 1077 */           localObject2 = ((ct)paramStateInterface).a().values();
/*      */         }
/* 1079 */         for (localObject3 = ((ObjectCollection)localObject2).iterator(); ((Iterator)localObject3).hasNext(); ) {
/* 1080 */           if (((
/* 1080 */             localObject4 = (Sendable)((Iterator)localObject3).next()) instanceof SegmentController))
/*      */           {
/* 1082 */             if ((
/* 1082 */               localObject5 = (SegmentController)localObject4)
/* 1082 */               .getSectorId() == this.obj.getSectorId())
/*      */             {
/*      */               Transform localTransform;
/* 1084 */               (
/* 1085 */                 localTransform = new Transform())
/* 1085 */                 .setIdentity();
/* 1086 */               localTransform.origin.set(this.targetPosition);
/*      */ 
/* 1089 */               if ((
/* 1089 */                 bool2 = ((SegmentController)localObject5).getCollisionChecker().a((SegmentController)localObject5, localTransform)))
/*      */               {
/*      */                 break;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 1096 */       if (!bool2) {
/* 1097 */         System.err.println("[WARPING OT OF COLLISION][CHECK WARP] NOHIT -> can spawn here!! " + this.targetPosition);
/*      */       }
/*      */ 
/* 1103 */       j++;
/*      */     }
/* 1105 */     if (j >= 10000)
/*      */       try {
/* 1107 */         throw new RuntimeException("Exceeded warping out of collision!!!!! " + paramTransform.origin + "; " + paramTransform.basis); } catch (Exception localException) { localException
/* 1108 */           .printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   public Vector3f getLinearVelocity(Vector3f paramVector3f)
/*      */   {
/* 1115 */     paramVector3f.set(this.velocity);
/* 1116 */     paramVector3f.scale(1000.0F);
/*      */ 
/* 1118 */     return paramVector3f;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt
 * JD-Core Version:    0.6.2
 */