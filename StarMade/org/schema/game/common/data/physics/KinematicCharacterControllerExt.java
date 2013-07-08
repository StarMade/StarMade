package org.schema.game.common.data.physics;

import class_371;
import class_750;
import class_796;
import class_866;
import class_941;
import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.CapsuleShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.dynamics.character.KinematicCharacterController;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.PrintStream;
import java.util.Iterator;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.server.ServerStateInterface;

public class KinematicCharacterControllerExt
  extends KinematicCharacterController
{
  private float extendedCharacterHeight = 1.7F;
  public static Vector3f[] upAxisDirectionDefault = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
  public Vector3f[] upAxisDirection = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
  private class_750 obj;
  Vector3f move = new Vector3f();
  Transform xform = new Transform();
  Transform start = new Transform();
  Transform end = new Transform();
  Vector3f distance2Vec = new Vector3f();
  Vector3f hitDistanceVec = new Vector3f();
  Vector3f currentDir = new Vector3f();
  Transform tmp = new Transform();
  Vector3f before = new Vector3f();
  Vector3f after;
  int penetrationCounter;
  private CapsuleShape capsule;
  private CapsuleShape strafeCapsule;
  ObjectArrayList manifoldArray = new ObjectArrayList();
  Transform ttmp = new Transform();
  Vector3f normal = new Vector3f();
  private float localJumpSpeed;
  private long jumpingStarted;
  private Vector3f velocity = new Vector3f();
  
  public KinematicCharacterControllerExt(class_750 paramclass_750, PairCachingGhostObjectExt paramPairCachingGhostObjectExt, ConvexShape paramConvexShape, float paramFloat)
  {
    super(paramPairCachingGhostObjectExt, paramConvexShape, paramFloat);
    this.obj = paramclass_750;
  }
  
  public void setLocalJumpSpeed(float paramFloat)
  {
    this.localJumpSpeed = paramFloat;
  }
  
  public void breakJump(class_941 paramclass_941)
  {
    if ((this.verticalVelocity > 0.0F) && (this.jumpingStarted != -1L))
    {
      if (System.currentTimeMillis() - this.jumpingStarted > 80L) {
        this.verticalVelocity = Math.max(0.0F, this.verticalVelocity - paramclass_941.a() * 50.0F);
      }
      return;
    }
    this.jumpingStarted = -1L;
  }
  
  public void jump()
  {
    if (!canJump()) {
      return;
    }
    this.jumpingStarted = System.currentTimeMillis();
    this.verticalVelocity = this.jumpSpeed;
  }
  
  public void loadAttached(CollisionWorld paramCollisionWorld)
  {
    if ((paramCollisionWorld = (PairCachingGhostObjectAlignable)this.ghostObject).getAttached() != null)
    {
      Object localObject = paramCollisionWorld.getAttached().getWorldTransform();
      if (paramCollisionWorld.getAttached().getPhysicsDataContainer().getObject() != null) {
        paramCollisionWorld.getAttached().getPhysicsDataContainer().getObject().activate(true);
      }
      GlUtil.e(this.upAxisDirection[0], (Transform)localObject);
      GlUtil.f(this.upAxisDirection[1], (Transform)localObject);
      GlUtil.c(this.upAxisDirection[2], (Transform)localObject);
      localObject = ((Transform)localObject).getMatrix(new Matrix4f());
      Matrix4f localMatrix4f = paramCollisionWorld.localWorldTransform.getMatrix(new Matrix4f());
      (localObject = new Matrix4f((Matrix4f)localObject)).mul(localMatrix4f);
      paramCollisionWorld.setWorldTransform(new Transform((Matrix4f)localObject));
    }
  }
  
  public void updateAction(CollisionWorld paramCollisionWorld, float paramFloat)
  {
    preStep(paramCollisionWorld);
    playerStep(paramCollisionWorld, paramFloat);
    this.obj.getPhysicsDataContainer().updatePhysical();
    if ((!this.obj.isOnServer()) && (((class_371)this.obj.getState()).a3() == this.obj)) {
      this.obj.field_136 = true;
    }
  }
  
  private void adaptLocalAttached()
  {
    PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable;
    if ((localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)this.ghostObject).getAttached() != null)
    {
      Matrix4f localMatrix4f1 = localPairCachingGhostObjectAlignable.getAttached().getWorldTransform().getMatrix(new Matrix4f());
      new Matrix4f(localMatrix4f1);
      localPairCachingGhostObjectAlignable.localWorldTransform.getMatrix(new Matrix4f());
      (localMatrix4f1 = new Matrix4f(localMatrix4f1)).invert();
      Matrix4f localMatrix4f2 = this.xform.getMatrix(new Matrix4f());
      localMatrix4f1.mul(localMatrix4f2);
      localPairCachingGhostObjectAlignable.localWorldTransform.set(localMatrix4f1);
    }
  }
  
  public void preStep(CollisionWorld paramCollisionWorld)
  {
    loadAttached(paramCollisionWorld);
    int i = 0;
    Vector3f localVector3f = this.ghostObject.getWorldTransform(this.ttmp).origin;
    this.currentPosition.set(localVector3f);
    this.touchingContact = false;
    do
    {
      if (!recoverFromPenetration(paramCollisionWorld)) {
        break;
      }
      i++;
      this.touchingContact = true;
    } while (i <= 4);
    this.targetPosition.set(this.currentPosition);
  }
  
  public void playerStep(CollisionWorld paramCollisionWorld, float paramFloat)
  {
    if ((!this.useWalkDirection) && (this.velocityTimeInterval <= 0.0F)) {
      return;
    }
    PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)this.ghostObject;
    this.wasOnGround = onGround();
    this.verticalVelocity -= this.gravity * paramFloat;
    if ((this.verticalVelocity > 0.0D) && (this.verticalVelocity > this.jumpSpeed)) {
      this.verticalVelocity = this.jumpSpeed;
    }
    if ((this.verticalVelocity < 0.0D) && (Math.abs(this.verticalVelocity) > Math.abs(this.fallSpeed))) {
      this.verticalVelocity = (-Math.abs(this.fallSpeed));
    }
    this.verticalOffset = (this.verticalVelocity * paramFloat);
    localPairCachingGhostObjectAlignable.getWorldTransform(this.xform);
    this.velocity.set(this.xform.origin);
    stepUp(paramCollisionWorld);
    if (this.useWalkDirection)
    {
      stepForwardAndStrafe(paramCollisionWorld, this.walkDirection);
    }
    else
    {
      float f = paramFloat < this.velocityTimeInterval ? paramFloat : this.velocityTimeInterval;
      this.velocityTimeInterval -= paramFloat;
      this.move.scale(f, this.walkDirection);
      stepForwardAndStrafe(paramCollisionWorld, this.move);
    }
    if (this.gravity > 0.0F) {
      stepDown(paramCollisionWorld, paramFloat);
    }
    this.xform.origin.set(this.currentPosition);
    GlUtil.c3(this.upAxisDirection[0], this.xform);
    GlUtil.d2(this.upAxisDirection[1], this.xform);
    GlUtil.a30(this.upAxisDirection[2], this.xform);
    localPairCachingGhostObjectAlignable.setWorldTransform(this.xform);
    adaptLocalAttached();
    this.velocity.sub(this.xform.origin);
    this.velocity.negate();
    this.walkDirection.set(0.0F, 0.0F, 0.0F);
    this.velocityTimeInterval = 0.0F;
  }
  
  protected boolean recoverFromPenetration(CollisionWorld paramCollisionWorld)
  {
    boolean bool = false;
    Vector3f localVector3f = new Vector3f();
    Object localObject = new Vector3f();
    this.convexShape.getAabb(this.ghostObject.getWorldTransform(new Transform()), localVector3f, (Vector3f)localObject);
    paramCollisionWorld.getBroadphase().setAabb(this.ghostObject.getBroadphaseHandle(), localVector3f, (Vector3f)localObject, paramCollisionWorld.getDispatcher());
    paramCollisionWorld.getDispatcher().dispatchAllCollisionPairs(this.ghostObject.getOverlappingPairCache(), paramCollisionWorld.getDispatchInfo(), paramCollisionWorld.getDispatcher());
    this.currentPosition.set(this.ghostObject.getWorldTransform(this.ttmp).origin);
    paramCollisionWorld = 0.0F;
    for (int i = 0; i < this.ghostObject.getOverlappingPairCache().getNumOverlappingPairs(); i++)
    {
      this.manifoldArray.clear();
      if ((localObject = (BroadphasePair)this.ghostObject.getOverlappingPairCache().getOverlappingPairArray().getQuick(i)).algorithm != null) {
        ((BroadphasePair)localObject).algorithm.getAllContactManifolds(this.manifoldArray);
      }
      for (int j = 0; j < this.manifoldArray.size(); j++)
      {
        PersistentManifold localPersistentManifold;
        float f1 = (localPersistentManifold = (PersistentManifold)this.manifoldArray.getQuick(j)).getBody0() == this.ghostObject ? -1.0F : 1.0F;
        for (int k = 0; k < localPersistentManifold.getNumContacts(); k++)
        {
          ManifoldPoint localManifoldPoint;
          float f2;
          if ((f2 = (localManifoldPoint = localPersistentManifold.getContactPoint(k)).getDistance()) < 0.1F)
          {
            if (f2 < paramCollisionWorld)
            {
              paramCollisionWorld = f2;
              this.touchingNormal.set(localManifoldPoint.normalWorldOnB);
              this.touchingNormal.scale(f1);
            }
            this.currentPosition.scaleAdd(f1 * f2 * 0.2F, localManifoldPoint.normalWorldOnB, this.currentPosition);
            bool = true;
          }
          else
          {
            this.obj.getState();
          }
        }
      }
    }
    Transform localTransform;
    (localTransform = this.ghostObject.getWorldTransform(this.ttmp)).origin.set(this.currentPosition);
    this.ghostObject.setWorldTransform(localTransform);
    return bool;
  }
  
  public void setVelocityForTimeIntervalStacked(Vector3f paramVector3f, float paramFloat)
  {
    this.useWalkDirection = false;
    this.walkDirection.add(paramVector3f);
    this.normal.set(this.walkDirection);
    this.normal.normalize();
    this.normalizedDirection.set(this.normal);
    this.velocityTimeInterval += paramFloat;
  }
  
  public void setWalkDirectionStacked(Vector3f paramVector3f)
  {
    this.useWalkDirection = true;
    this.walkDirection.add(paramVector3f);
    this.normal.set(this.walkDirection);
    this.normal.normalize();
    this.normalizedDirection.set(this.normal);
  }
  
  protected void stepDown(CollisionWorld paramCollisionWorld, float paramFloat)
  {
    if (this.obj.isOnServer()) {
      SubsimplexCubesCovexCast.mode = "DWN";
    }
    Transform localTransform1 = new Transform();
    Transform localTransform2 = new Transform();
    float f1 = this.wasOnGround ? this.stepHeight : 0.0F;
    Vector3f localVector3f2;
    (localVector3f2 = new Vector3f()).scale(this.currentStepOffset + f1, this.upAxisDirection[this.upAxis]);
    paramFloat = ((f1 == 0.0F) && (this.verticalVelocity < 0.0F) ? -this.verticalVelocity : 0.0F) * paramFloat;
    Vector3f localVector3f1;
    (localVector3f1 = new Vector3f()).scale(paramFloat, this.upAxisDirection[this.upAxis]);
    this.targetPosition.sub(localVector3f2);
    this.targetPosition.sub(localVector3f1);
    localTransform1.setIdentity();
    localTransform2.setIdentity();
    localTransform1.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
    localTransform2.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
    localTransform1.origin.set(this.currentPosition);
    localTransform2.origin.set(this.targetPosition);
    paramFloat = this.ghostObject.getCollisionShape().getMargin();
    if (this.capsule == null)
    {
      float f2 = this.obj.b1();
      float f3 = this.extendedCharacterHeight;
      this.capsule = new CapsuleShape(f2, f3);
      this.capsule.setMargin(0.1F);
    }
    KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
    (localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], this.maxSlopeCosine)).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
    localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
    Vector3f localVector3f3;
    (localVector3f3 = new Vector3f()).sub(this.targetPosition, this.currentPosition);
    if (localVector3f3.length() == 0.0F) {
      return;
    }
    new Vector3f(this.currentPosition);
    (localVector3f3 = new Vector3f(localVector3f3)).normalize();
    localVector3f3.scale(0.999F);
    localVector3f3.add(this.currentPosition);
    if (this.useGhostObjectSweepTest) {
      this.ghostObject.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
    } else {
      paramCollisionWorld.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback);
    }
    if (localKinematicClosestNotMeConvexResultCallback.hasHit())
    {
      this.currentPosition.interpolate(this.currentPosition, this.targetPosition, localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
      this.verticalVelocity = 0.0F;
      this.verticalOffset = 0.0F;
      if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null) {
        this.obj.a133(new class_796(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));
      } else {
        System.err.println("Exception: Kinmatik: NO SEGMENT TO HANDLE AFTER COLLISION (LIFT?)");
      }
    }
    else
    {
      if (this.targetPosition.epsilonEquals(this.currentPosition, 1.0E-007F))
      {
        this.verticalVelocity = 0.0F;
        this.verticalOffset = 0.0F;
      }
      this.currentPosition.set(this.targetPosition);
    }
    this.ghostObject.getCollisionShape().setMargin(paramFloat);
  }
  
  protected void stepForwardAndStrafe(CollisionWorld paramCollisionWorld, Vector3f paramVector3f)
  {
    if (this.obj.isOnServer()) {
      SubsimplexCubesCovexCast.mode = "FW";
    }
    this.targetPosition.add(this.currentPosition, paramVector3f);
    this.start.setIdentity();
    this.end.setIdentity();
    this.start.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
    this.end.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
    paramVector3f = 1.0F;
    this.distance2Vec.sub(this.targetPosition, this.currentPosition);
    float f = 0.0F;
    if (this.distance2Vec.lengthSquared() == 0.0F) {
      return;
    }
    if (this.strafeCapsule == null)
    {
      f = this.obj.a14();
      this.strafeCapsule = new CapsuleShape(0.3F, f);
      this.strafeCapsule.setMargin(0.1F);
    }
    int i = 10;
    this.before.set(this.currentPosition);
    while ((paramVector3f > 0.01F) && (i-- > 0))
    {
      this.start.origin.set(this.currentPosition);
      this.end.origin.set(this.targetPosition);
      KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
      (localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], -1.0F)).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
      localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
      f = this.convexShape.getMargin();
      this.convexShape.setMargin(f + this.addedMargin);
      if (this.useGhostObjectSweepTest) {
        this.ghostObject.convexSweepTest(this.strafeCapsule, this.start, this.end, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
      } else {
        paramCollisionWorld.convexSweepTest(this.convexShape, this.start, this.end, localKinematicClosestNotMeConvexResultCallback);
      }
      this.convexShape.setMargin(f);
      paramVector3f -= localKinematicClosestNotMeConvexResultCallback.closestHitFraction;
      if (localKinematicClosestNotMeConvexResultCallback.hasHit())
      {
        this.hitDistanceVec.sub(localKinematicClosestNotMeConvexResultCallback.hitPointWorld, this.currentPosition);
        this.hitDistanceVec.length();
        updateTargetPositionBasedOnCollision(localKinematicClosestNotMeConvexResultCallback.hitNormalWorld);
        this.currentDir.sub(this.targetPosition, this.currentPosition);
        if (this.currentDir.lengthSquared() > 1.192093E-007F)
        {
          this.currentDir.normalize();
          if (this.currentDir.dot(this.normalizedDirection) <= 0.0F)
          {
            if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null)
            {
              this.obj.a133(new class_796(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));
              break;
            }
            System.err.println("[KINEMATIC] collision: No Segment To handle");
            break;
          }
        }
        else
        {
          if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null)
          {
            this.obj.a133(new class_796(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));
            break;
          }
          System.err.println("[KINEMATIC] collision: No Segment To handle");
          break;
        }
      }
      else
      {
        this.currentPosition.set(this.targetPosition);
      }
    }
    this.obj.getState();
    if (this.after == null) {
      this.after = new Vector3f();
    }
    this.after.set(this.currentPosition);
  }
  
  protected void stepUp(CollisionWorld paramCollisionWorld)
  {
    if (this.obj.isOnServer()) {
      SubsimplexCubesCovexCast.mode = "UP";
    }
    Transform localTransform1 = new Transform();
    Transform localTransform2 = new Transform();
    this.targetPosition.scaleAdd(this.stepHeight + (this.verticalOffset > 0.0D ? this.verticalOffset : 0.0F), this.upAxisDirection[this.upAxis], this.currentPosition);
    if (this.capsule == null)
    {
      float f1 = this.obj.b1();
      float f2 = this.extendedCharacterHeight;
      this.capsule = new CapsuleShape(f1, f2);
      this.capsule.setMargin(0.1F);
    }
    localTransform1.setIdentity();
    localTransform2.setIdentity();
    assert (this.ghostObject.getWorldTransform(new Transform()).getMatrix(new Matrix4f()).determinant() != 0.0F) : this.ghostObject.getWorldTransform(new Transform()).getMatrix(new Matrix4f());
    localTransform1.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
    localTransform2.basis.set(this.ghostObject.getWorldTransform(new Transform()).basis);
    localTransform1.origin.set(this.currentPosition);
    localTransform2.origin.set(this.targetPosition);
    Vector3f localVector3f;
    (localVector3f = new Vector3f()).scale(-1.0F, this.upAxisDirection[this.upAxis]);
    KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback localKinematicClosestNotMeConvexResultCallback;
    (localKinematicClosestNotMeConvexResultCallback = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, localVector3f, 0.1F)).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
    localKinematicClosestNotMeConvexResultCallback.collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
    if (this.useGhostObjectSweepTest) {
      this.ghostObject.convexSweepTest(this.capsule, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback, paramCollisionWorld.getDispatchInfo().allowedCcdPenetration);
    } else {
      paramCollisionWorld.convexSweepTest(this.convexShape, localTransform1, localTransform2, localKinematicClosestNotMeConvexResultCallback);
    }
    if (localKinematicClosestNotMeConvexResultCallback.hasHit())
    {
      this.currentStepOffset = (this.stepHeight * localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
      this.currentPosition.interpolate(this.currentPosition, this.targetPosition, localKinematicClosestNotMeConvexResultCallback.closestHitFraction);
      this.verticalVelocity = -1.0E-008F;
      this.verticalOffset = 0.0F;
      this.jumpingStarted = -1L;
      if (KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback) != null)
      {
        this.obj.a133(new class_796(KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$000(localKinematicClosestNotMeConvexResultCallback), KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback.access$100(localKinematicClosestNotMeConvexResultCallback)));
        return;
      }
      System.err.println("[KINEMATIC] collision: No Segment To handle");
      return;
    }
    this.currentStepOffset = this.stepHeight;
    this.currentPosition.set(this.targetPosition);
  }
  
  public void stopJump()
  {
    this.verticalVelocity = 0.0F;
  }
  
  public void warpOutOfCollision(StateInterface paramStateInterface, CollisionWorld paramCollisionWorld, Transform paramTransform)
  {
    warp(paramTransform.origin);
    this.start.setIdentity();
    this.end.setIdentity();
    this.distance2Vec.sub(paramTransform.origin, this.targetPosition);
    new Vector3f();
    paramCollisionWorld.getBroadphase().calculateOverlappingPairs(paramCollisionWorld.getDispatcher());
    paramCollisionWorld = 4;
    Vector3f localVector3f;
    GlUtil.c(localVector3f = new Vector3f(), paramTransform);
    int j = 0;
    this.targetPosition.set(paramTransform.origin);
    boolean bool1 = false;
    warp(this.targetPosition);
    Object localObject2;
    Object localObject3;
    Object localObject4;
    Object localObject5;
    synchronized (paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      ObjectCollection localObjectCollection;
      if ((paramStateInterface instanceof ServerStateInterface)) {
        localObjectCollection = paramStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values();
      } else {
        localObjectCollection = ((class_371)paramStateInterface).a7().values();
      }
      localObject2 = localObjectCollection.iterator();
      while (((Iterator)localObject2).hasNext()) {
        if ((((localObject3 = (Sendable)((Iterator)localObject2).next()) instanceof SegmentController)) && ((localObject4 = (SegmentController)localObject3).getSectorId() == this.obj.getSectorId()))
        {
          (localObject5 = new Transform()).setIdentity();
          ((Transform)localObject5).origin.set(this.targetPosition);
          if ((bool1 = ((SegmentController)localObject4).getCollisionChecker().a2((SegmentController)localObject4, (Transform)localObject5))) {
            break;
          }
        }
      }
    }
    if (localObject1 == 0) {
      System.err.println("[WARPING OT OF COLLISION][CHECK_SINGLE] NOHIT -> can spawn here!! " + this.targetPosition);
    }
    while ((localObject1 != 0) && (j < 10000))
    {
      this.start.origin.set(this.targetPosition);
      switch (paramCollisionWorld)
      {
      case 4: 
        GlUtil.c(localVector3f, paramTransform);
        break;
      case 5: 
        GlUtil.a4(localVector3f, paramTransform);
        break;
      case 2: 
        GlUtil.f(localVector3f, paramTransform);
        break;
      case 3: 
        GlUtil.b(localVector3f, paramTransform);
        break;
      case 1: 
        GlUtil.d(localVector3f, paramTransform);
        break;
      case 0: 
        GlUtil.e(localVector3f, paramTransform);
      }
      paramCollisionWorld = (paramCollisionWorld + 1) % 6;
      localVector3f.scale(1.0F + j / 6 * 0.2F);
      this.targetPosition.add(paramTransform.origin, localVector3f);
      this.end.origin.set(this.targetPosition);
      ??? = new KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback(this.ghostObject, this.upAxisDirection[this.upAxis], -1.0F);
      if (this.ghostObject == null)
      {
        System.err.println("Exception KinematikCharacter: warp out of collision ghost object " + this.ghostObject + " is null " + this.obj);
        return;
      }
      if (this.ghostObject.getBroadphaseHandle() == null)
      {
        System.err.println("Exception KinematikCharacter: warp out of collision broadphase handle of ghost object " + this.ghostObject + " is null " + this.obj);
        return;
      }
      ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)???).collisionFilterGroup = this.ghostObject.getBroadphaseHandle().collisionFilterGroup;
      ((KinematicCharacterControllerExt.KinematicClosestNotMeConvexResultCallback)???).collisionFilterMask = this.ghostObject.getBroadphaseHandle().collisionFilterMask;
      int i = 0;
      warp(this.targetPosition);
      boolean bool2;
      synchronized (paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        if ((paramStateInterface instanceof ServerStateInterface)) {
          localObject2 = paramStateInterface.getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values();
        } else {
          localObject2 = ((class_371)paramStateInterface).a7().values();
        }
        localObject3 = ((ObjectCollection)localObject2).iterator();
        while (((Iterator)localObject3).hasNext()) {
          if ((((localObject4 = (Sendable)((Iterator)localObject3).next()) instanceof SegmentController)) && ((localObject5 = (SegmentController)localObject4).getSectorId() == this.obj.getSectorId()))
          {
            Transform localTransform;
            (localTransform = new Transform()).setIdentity();
            localTransform.origin.set(this.targetPosition);
            if ((bool2 = ((SegmentController)localObject5).getCollisionChecker().a2((SegmentController)localObject5, localTransform))) {
              break;
            }
          }
        }
      }
      if (!bool2) {
        System.err.println("[WARPING OT OF COLLISION][CHECK WARP] NOHIT -> can spawn here!! " + this.targetPosition);
      }
      j++;
    }
    if (j >= 10000) {
      try
      {
        throw new RuntimeException("Exceeded warping out of collision!!!!! " + paramTransform.origin + "; " + paramTransform.basis);
      }
      catch (Exception localException)
      {
        localException;
      }
    }
  }
  
  public Vector3f getLinearVelocity(Vector3f paramVector3f)
  {
    paramVector3f.set(this.velocity);
    paramVector3f.scale(1000.0F);
    return paramVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */