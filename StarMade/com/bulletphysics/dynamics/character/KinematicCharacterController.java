package com.bulletphysics.dynamics.character;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphasePair;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.CollisionAlgorithm;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.DispatcherInfo;
import com.bulletphysics.collision.broadphase.HashedOverlappingPairCache;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
import com.bulletphysics.collision.dispatch.PairCachingGhostObject;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.dynamics.ActionInterface;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class KinematicCharacterController
  extends ActionInterface
{
  private static Vector3f[] upAxisDirection = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F) };
  protected float halfHeight;
  protected PairCachingGhostObject ghostObject;
  protected ConvexShape convexShape;
  protected float verticalVelocity;
  protected float verticalOffset;
  protected float fallSpeed;
  protected float jumpSpeed;
  protected float maxJumpHeight;
  protected float maxSlopeRadians;
  protected float maxSlopeCosine;
  protected float gravity;
  protected float turnAngle;
  protected float stepHeight;
  protected float addedMargin;
  protected Vector3f walkDirection = new Vector3f();
  protected Vector3f normalizedDirection = new Vector3f();
  protected Vector3f currentPosition = new Vector3f();
  protected float currentStepOffset;
  protected Vector3f targetPosition = new Vector3f();
  ObjectArrayList<PersistentManifold> manifoldArray = new ObjectArrayList();
  protected boolean touchingContact;
  protected Vector3f touchingNormal = new Vector3f();
  protected boolean wasOnGround;
  protected boolean useGhostObjectSweepTest;
  protected boolean useWalkDirection;
  protected float velocityTimeInterval;
  protected int upAxis;
  protected CollisionObject field_370;
  
  public KinematicCharacterController(PairCachingGhostObject ghostObject, ConvexShape convexShape, float stepHeight)
  {
    this(ghostObject, convexShape, stepHeight, 1);
  }
  
  public KinematicCharacterController(PairCachingGhostObject ghostObject, ConvexShape convexShape, float stepHeight, int upAxis)
  {
    this.upAxis = upAxis;
    this.addedMargin = 0.02F;
    this.walkDirection.set(0.0F, 0.0F, 0.0F);
    this.useGhostObjectSweepTest = true;
    this.ghostObject = ghostObject;
    this.stepHeight = stepHeight;
    this.turnAngle = 0.0F;
    this.convexShape = convexShape;
    this.useWalkDirection = true;
    this.velocityTimeInterval = 0.0F;
    this.verticalVelocity = 0.0F;
    this.verticalOffset = 0.0F;
    this.gravity = 9.8F;
    this.fallSpeed = 55.0F;
    this.jumpSpeed = 10.0F;
    this.wasOnGround = false;
    setMaxSlope(0.8726647F);
  }
  
  private PairCachingGhostObject getGhostObject()
  {
    return this.ghostObject;
  }
  
  public void updateAction(CollisionWorld collisionWorld, float deltaTime)
  {
    preStep(collisionWorld);
    playerStep(collisionWorld, deltaTime);
  }
  
  public void debugDraw(IDebugDraw debugDrawer) {}
  
  public void setUpAxis(int axis)
  {
    if (axis < 0) {
      axis = 0;
    }
    if (axis > 2) {
      axis = 2;
    }
    this.upAxis = axis;
  }
  
  public void setWalkDirection(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.useWalkDirection = true;
      this.walkDirection.set(walkDirection);
      this.normalizedDirection.set(getNormalizedVector(walkDirection, localStack.get$javax$vecmath$Vector3f()));
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setVelocityForTimeInterval(Vector3f arg1, float arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.useWalkDirection = false;
      this.walkDirection.set(velocity);
      this.normalizedDirection.set(getNormalizedVector(this.walkDirection, localStack.get$javax$vecmath$Vector3f()));
      this.velocityTimeInterval = timeInterval;
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void reset() {}
  
  public void warp(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform xform = localStack.get$com$bulletphysics$linearmath$Transform();
      xform.setIdentity();
      xform.origin.set(origin);
      this.ghostObject.setWorldTransform(xform);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void preStep(CollisionWorld arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      int numPenetrationLoops = 0;
      this.touchingContact = false;
      while (recoverFromPenetration(collisionWorld))
      {
        numPenetrationLoops++;
        this.touchingContact = true;
        if (numPenetrationLoops > 4) {
          break;
        }
      }
      this.currentPosition.set(this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
      this.targetPosition.set(this.currentPosition);
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void playerStep(CollisionWorld arg1, float arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      if ((!this.useWalkDirection) && (this.velocityTimeInterval <= 0.0F)) {
        return;
      }
      this.wasOnGround = onGround();
      this.verticalVelocity -= this.gravity * local_dt;
      if ((this.verticalVelocity > 0.0D) && (this.verticalVelocity > this.jumpSpeed)) {
        this.verticalVelocity = this.jumpSpeed;
      }
      if ((this.verticalVelocity < 0.0D) && (Math.abs(this.verticalVelocity) > Math.abs(this.fallSpeed))) {
        this.verticalVelocity = (-Math.abs(this.fallSpeed));
      }
      this.verticalOffset = (this.verticalVelocity * local_dt);
      Transform xform = this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      stepUp(collisionWorld);
      if (this.useWalkDirection)
      {
        stepForwardAndStrafe(collisionWorld, this.walkDirection);
      }
      else
      {
        System.out.println("playerStep 4");
        float dtMoving = local_dt < this.velocityTimeInterval ? local_dt : this.velocityTimeInterval;
        this.velocityTimeInterval -= local_dt;
        Vector3f move = localStack.get$javax$vecmath$Vector3f();
        move.scale(dtMoving, this.walkDirection);
        stepForwardAndStrafe(collisionWorld, move);
      }
      stepDown(collisionWorld, local_dt);
      xform.origin.set(this.currentPosition);
      this.ghostObject.setWorldTransform(xform);
      return;
    }
    finally
    {
      .Stack tmp279_277 = localStack;
      tmp279_277.pop$com$bulletphysics$linearmath$Transform();
      tmp279_277.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setFallSpeed(float fallSpeed)
  {
    this.fallSpeed = fallSpeed;
  }
  
  public void setJumpSpeed(float jumpSpeed)
  {
    this.jumpSpeed = jumpSpeed;
  }
  
  public void setMaxJumpHeight(float maxJumpHeight)
  {
    this.maxJumpHeight = maxJumpHeight;
  }
  
  public boolean canJump()
  {
    return onGround();
  }
  
  public void jump()
  {
    if (!canJump()) {
      return;
    }
    this.verticalVelocity = this.jumpSpeed;
  }
  
  public void setGravity(float gravity)
  {
    this.gravity = gravity;
  }
  
  public float getGravity()
  {
    return this.gravity;
  }
  
  public void setMaxSlope(float slopeRadians)
  {
    this.maxSlopeRadians = slopeRadians;
    this.maxSlopeCosine = ((float)Math.cos(slopeRadians));
  }
  
  public float getMaxSlope()
  {
    return this.maxSlopeRadians;
  }
  
  public boolean onGround()
  {
    return (this.verticalVelocity == 0.0F) && (this.verticalOffset == 0.0F);
  }
  
  private static Vector3f getNormalizedVector(Vector3f local_v, Vector3f out)
  {
    out.set(local_v);
    out.normalize();
    if (out.length() < 1.192093E-007F) {
      out.set(0.0F, 0.0F, 0.0F);
    }
    return out;
  }
  
  protected Vector3f computeReflectionDirection(Vector3f direction, Vector3f normal, Vector3f out)
  {
    out.set(normal);
    out.scale(-2.0F * direction.dot(normal));
    out.add(direction);
    return out;
  }
  
  protected Vector3f parallelComponent(Vector3f direction, Vector3f normal, Vector3f out)
  {
    out.set(normal);
    out.scale(direction.dot(normal));
    return out;
  }
  
  protected Vector3f perpindicularComponent(Vector3f direction, Vector3f normal, Vector3f out)
  {
    Vector3f perpendicular = parallelComponent(direction, normal, out);
    perpendicular.scale(-1.0F);
    perpendicular.add(direction);
    return perpendicular;
  }
  
  protected boolean recoverFromPenetration(CollisionWorld arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      boolean penetration = false;
      collisionWorld.getDispatcher().dispatchAllCollisionPairs(this.ghostObject.getOverlappingPairCache(), collisionWorld.getDispatchInfo(), collisionWorld.getDispatcher());
      this.currentPosition.set(this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).origin);
      float maxPen = 0.0F;
      for (int local_i = 0; local_i < this.ghostObject.getOverlappingPairCache().getNumOverlappingPairs(); local_i++)
      {
        this.manifoldArray.clear();
        BroadphasePair collisionPair = (BroadphasePair)this.ghostObject.getOverlappingPairCache().getOverlappingPairArray().getQuick(local_i);
        if (collisionPair.algorithm != null) {
          collisionPair.algorithm.getAllContactManifolds(this.manifoldArray);
        }
        for (int local_j = 0; local_j < this.manifoldArray.size(); local_j++)
        {
          PersistentManifold manifold = (PersistentManifold)this.manifoldArray.getQuick(local_j);
          float directionSign = manifold.getBody0() == this.ghostObject ? -1.0F : 1.0F;
          for (int local_p = 0; local_p < manifold.getNumContacts(); local_p++)
          {
            ManifoldPoint local_pt = manifold.getContactPoint(local_p);
            float dist = local_pt.getDistance();
            if (dist < 0.0F)
            {
              if (dist < maxPen)
              {
                maxPen = dist;
                this.touchingNormal.set(local_pt.normalWorldOnB);
                this.touchingNormal.scale(directionSign);
              }
              this.currentPosition.scaleAdd(directionSign * dist * 0.2F, local_pt.normalWorldOnB, this.currentPosition);
              penetration = true;
            }
          }
        }
      }
      Transform local_i = this.ghostObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
      local_i.origin.set(this.currentPosition);
      this.ghostObject.setWorldTransform(local_i);
      return penetration;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  protected void stepUp(CollisionWorld arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
      this.targetPosition.scaleAdd(this.stepHeight + (this.verticalOffset > 0.0D ? this.verticalOffset : 0.0F), upAxisDirection[this.upAxis], this.currentPosition);
      start.setIdentity();
      end.setIdentity();
      start.origin.scaleAdd(this.convexShape.getMargin() + this.addedMargin, upAxisDirection[this.upAxis], this.currentPosition);
      end.origin.set(this.targetPosition);
      Vector3f local_up = localStack.get$javax$vecmath$Vector3f();
      local_up.scale(-1.0F, upAxisDirection[this.upAxis]);
      KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, local_up, 0.0F);
      callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
      callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
      if (this.useGhostObjectSweepTest) {
        this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, world.getDispatchInfo().allowedCcdPenetration);
      } else {
        world.convexSweepTest(this.convexShape, start, end, callback);
      }
      if (callback.hasHit())
      {
        this.currentStepOffset = (this.stepHeight * callback.closestHitFraction);
        this.currentPosition.interpolate(this.currentPosition, this.targetPosition, callback.closestHitFraction);
        this.verticalVelocity = 0.0F;
        this.verticalOffset = 0.0F;
      }
      else
      {
        this.currentStepOffset = this.stepHeight;
        this.currentPosition.set(this.targetPosition);
      }
      return;
    }
    finally
    {
      .Stack tmp317_315 = localStack;
      tmp317_315.pop$com$bulletphysics$linearmath$Transform();
      tmp317_315.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void updateTargetPositionBasedOnCollision(Vector3f hitNormal)
  {
    updateTargetPositionBasedOnCollision(hitNormal, 0.0F, 1.0F);
  }
  
  protected void updateTargetPositionBasedOnCollision(Vector3f arg1, float arg2, float arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f movementDirection = localStack.get$javax$vecmath$Vector3f();
      movementDirection.sub(this.targetPosition, this.currentPosition);
      float movementLength = movementDirection.length();
      if (movementLength > 1.192093E-007F)
      {
        movementDirection.normalize();
        Vector3f reflectDir = computeReflectionDirection(movementDirection, hitNormal, localStack.get$javax$vecmath$Vector3f());
        reflectDir.normalize();
        Vector3f parallelDir = parallelComponent(reflectDir, hitNormal, localStack.get$javax$vecmath$Vector3f());
        Vector3f perpindicularDir = perpindicularComponent(reflectDir, hitNormal, localStack.get$javax$vecmath$Vector3f());
        this.targetPosition.set(this.currentPosition);
        if (normalMag != 0.0F)
        {
          Vector3f perpComponent = localStack.get$javax$vecmath$Vector3f();
          perpComponent.scale(normalMag * movementLength, perpindicularDir);
          this.targetPosition.add(perpComponent);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void stepForwardAndStrafe(CollisionWorld arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
      this.targetPosition.add(this.currentPosition, walkMove);
      start.setIdentity();
      end.setIdentity();
      float fraction = 1.0F;
      Vector3f distance2Vec = localStack.get$javax$vecmath$Vector3f();
      distance2Vec.sub(this.currentPosition, this.targetPosition);
      float distance2 = distance2Vec.lengthSquared();
      int maxIter = 10;
      while ((fraction > 0.01F) && (maxIter-- > 0))
      {
        start.origin.set(this.currentPosition);
        end.origin.set(this.targetPosition);
        KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, upAxisDirection[this.upAxis], -1.0F);
        callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
        callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
        float margin = this.convexShape.getMargin();
        this.convexShape.setMargin(margin + this.addedMargin);
        if (this.useGhostObjectSweepTest) {
          this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, collisionWorld.getDispatchInfo().allowedCcdPenetration);
        } else {
          collisionWorld.convexSweepTest(this.convexShape, start, end, callback);
        }
        this.convexShape.setMargin(margin);
        fraction -= callback.closestHitFraction;
        if (callback.hasHit())
        {
          Vector3f hitDistanceVec = localStack.get$javax$vecmath$Vector3f();
          hitDistanceVec.sub(callback.hitPointWorld, this.currentPosition);
          updateTargetPositionBasedOnCollision(callback.hitNormalWorld);
          Vector3f currentDir = localStack.get$javax$vecmath$Vector3f();
          currentDir.sub(this.targetPosition, this.currentPosition);
          distance2 = currentDir.lengthSquared();
          if (distance2 <= 1.192093E-007F) {
            break;
          }
          currentDir.normalize();
          if (currentDir.dot(this.normalizedDirection) <= 0.0F) {
            break;
          }
        }
        else
        {
          this.currentPosition.set(this.targetPosition);
        }
      }
      return;
    }
    finally
    {
      .Stack tmp389_387 = localStack;
      tmp389_387.pop$com$bulletphysics$linearmath$Transform();
      tmp389_387.pop$javax$vecmath$Vector3f();
    }
  }
  
  protected void stepDown(CollisionWorld arg1, float arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      Transform start = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform end = localStack.get$com$bulletphysics$linearmath$Transform();
      float additionalDownStep = this.wasOnGround ? this.stepHeight : 0.0F;
      Vector3f step_drop = localStack.get$javax$vecmath$Vector3f();
      step_drop.scale(this.currentStepOffset + additionalDownStep, upAxisDirection[this.upAxis]);
      float downVelocity = ((additionalDownStep == 0.0F) && (this.verticalVelocity < 0.0F) ? -this.verticalVelocity : 0.0F) * local_dt;
      Vector3f gravity_drop = localStack.get$javax$vecmath$Vector3f();
      gravity_drop.scale(downVelocity, upAxisDirection[this.upAxis]);
      this.targetPosition.sub(step_drop);
      this.targetPosition.sub(gravity_drop);
      start.setIdentity();
      end.setIdentity();
      start.origin.set(this.currentPosition);
      end.origin.set(this.targetPosition);
      KinematicClosestNotMeConvexResultCallback callback = new KinematicClosestNotMeConvexResultCallback(this.ghostObject, upAxisDirection[this.upAxis], this.maxSlopeCosine);
      callback.collisionFilterGroup = getGhostObject().getBroadphaseHandle().collisionFilterGroup;
      callback.collisionFilterMask = getGhostObject().getBroadphaseHandle().collisionFilterMask;
      if (this.useGhostObjectSweepTest) {
        this.ghostObject.convexSweepTest(this.convexShape, start, end, callback, collisionWorld.getDispatchInfo().allowedCcdPenetration);
      } else {
        collisionWorld.convexSweepTest(this.convexShape, start, end, callback);
      }
      if (callback.hasHit())
      {
        this.currentPosition.interpolate(this.currentPosition, this.targetPosition, callback.closestHitFraction);
        this.verticalVelocity = 0.0F;
        this.verticalOffset = 0.0F;
      }
      else
      {
        this.currentPosition.set(this.targetPosition);
      }
      return;
    }
    finally
    {
      .Stack tmp337_335 = localStack;
      tmp337_335.pop$com$bulletphysics$linearmath$Transform();
      tmp337_335.pop$javax$vecmath$Vector3f();
    }
  }
  
  private static class KinematicClosestNotMeConvexResultCallback
    extends CollisionWorld.ClosestConvexResultCallback
  {
    protected CollisionObject field_419;
    protected final Vector3f field_420;
    protected float minSlopeDot;
    
    public KinematicClosestNotMeConvexResultCallback(CollisionObject local_me, Vector3f local_up, float minSlopeDot)
    {
      super(new Vector3f());
      this.field_419 = local_me;
      this.field_420 = local_up;
      this.minSlopeDot = minSlopeDot;
    }
    
    public float addSingleResult(CollisionWorld.LocalConvexResult arg1, boolean arg2)
    {
      .Stack localStack = .Stack.get();
      try
      {
        .Stack tmp7_5 = localStack;
        tmp7_5.push$com$bulletphysics$linearmath$Transform();
        tmp7_5.push$javax$vecmath$Vector3f();
        if (convexResult.hitCollisionObject == this.field_419) {
          return 1.0F;
        }
        Vector3f hitNormalWorld;
        Vector3f hitNormalWorld;
        if (normalInWorldSpace)
        {
          hitNormalWorld = convexResult.hitNormalLocal;
        }
        else
        {
          hitNormalWorld = localStack.get$javax$vecmath$Vector3f();
          this.hitCollisionObject.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis.transform(convexResult.hitNormalLocal, hitNormalWorld);
        }
        float dotUp = this.field_420.dot(hitNormalWorld);
        if (dotUp < this.minSlopeDot) {
          return 1.0F;
        }
        return super.addSingleResult(convexResult, normalInWorldSpace);
      }
      finally
      {
        .Stack tmp126_124 = localStack;
        tmp126_124.pop$com$bulletphysics$linearmath$Transform();
        tmp126_124.pop$javax$vecmath$Vector3f();
      }
    }
  }
  
  private static class KinematicClosestNotMeRayResultCallback
    extends CollisionWorld.ClosestRayResultCallback
  {
    protected CollisionObject field_101;
    
    public KinematicClosestNotMeRayResultCallback(CollisionObject local_me)
    {
      super(new Vector3f());
      this.field_101 = local_me;
    }
    
    public float addSingleResult(CollisionWorld.LocalRayResult rayResult, boolean normalInWorldSpace)
    {
      if (rayResult.collisionObject == this.field_101) {
        return 1.0F;
      }
      return super.addSingleResult(rayResult, normalInWorldSpace);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.character.KinematicCharacterController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */