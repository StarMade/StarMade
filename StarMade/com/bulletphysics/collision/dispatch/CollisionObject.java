package com.bulletphysics.collision.dispatch;

import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class CollisionObject
{
  public static final int ACTIVE_TAG = 1;
  public static final int ISLAND_SLEEPING = 2;
  public static final int WANTS_DEACTIVATION = 3;
  public static final int DISABLE_DEACTIVATION = 4;
  public static final int DISABLE_SIMULATION = 5;
  protected Transform worldTransform = new Transform();
  protected final Transform interpolationWorldTransform = new Transform();
  protected final Vector3f interpolationLinearVelocity = new Vector3f();
  protected final Vector3f interpolationAngularVelocity = new Vector3f();
  protected BroadphaseProxy broadphaseHandle;
  protected CollisionShape collisionShape;
  protected CollisionShape rootCollisionShape;
  protected int collisionFlags = 1;
  protected int islandTag1 = -1;
  protected int companionId = -1;
  protected int activationState1 = 1;
  protected float deactivationTime;
  protected float friction = 0.5F;
  protected float restitution;
  protected Object userObjectPointer;
  protected CollisionObjectType internalType = CollisionObjectType.COLLISION_OBJECT;
  protected float hitFraction = 1.0F;
  protected float ccdSweptSphereRadius;
  protected float ccdMotionThreshold = 0.0F;
  protected boolean checkCollideWith;
  
  public boolean checkCollideWithOverride(CollisionObject local_co)
  {
    return true;
  }
  
  public boolean mergesSimulationIslands()
  {
    return (this.collisionFlags & 0x7) == 0;
  }
  
  public boolean isStaticObject()
  {
    return (this.collisionFlags & 0x1) != 0;
  }
  
  public boolean isKinematicObject()
  {
    return (this.collisionFlags & 0x2) != 0;
  }
  
  public boolean isStaticOrKinematicObject()
  {
    return (this.collisionFlags & 0x3) != 0;
  }
  
  public boolean hasContactResponse()
  {
    return (this.collisionFlags & 0x4) == 0;
  }
  
  public CollisionShape getCollisionShape()
  {
    return this.collisionShape;
  }
  
  public void setCollisionShape(CollisionShape collisionShape)
  {
    this.collisionShape = collisionShape;
    this.rootCollisionShape = collisionShape;
  }
  
  public CollisionShape getRootCollisionShape()
  {
    return this.rootCollisionShape;
  }
  
  public void internalSetTemporaryCollisionShape(CollisionShape collisionShape)
  {
    this.collisionShape = collisionShape;
  }
  
  public int getActivationState()
  {
    return this.activationState1;
  }
  
  public void setActivationState(int newState)
  {
    if ((this.activationState1 != 4) && (this.activationState1 != 5)) {
      this.activationState1 = newState;
    }
  }
  
  public float getDeactivationTime()
  {
    return this.deactivationTime;
  }
  
  public void setDeactivationTime(float deactivationTime)
  {
    this.deactivationTime = deactivationTime;
  }
  
  public void forceActivationState(int newState)
  {
    this.activationState1 = newState;
  }
  
  public void activate()
  {
    activate(false);
  }
  
  public void activate(boolean forceActivation)
  {
    if ((forceActivation) || ((this.collisionFlags & 0x3) == 0))
    {
      setActivationState(1);
      this.deactivationTime = 0.0F;
    }
  }
  
  public boolean isActive()
  {
    return (getActivationState() != 2) && (getActivationState() != 5);
  }
  
  public float getRestitution()
  {
    return this.restitution;
  }
  
  public void setRestitution(float restitution)
  {
    this.restitution = restitution;
  }
  
  public float getFriction()
  {
    return this.friction;
  }
  
  public void setFriction(float friction)
  {
    this.friction = friction;
  }
  
  public CollisionObjectType getInternalType()
  {
    return this.internalType;
  }
  
  public Transform getWorldTransform(Transform out)
  {
    out.set(this.worldTransform);
    return out;
  }
  
  public void setWorldTransform(Transform worldTransform)
  {
    this.worldTransform.set(worldTransform);
  }
  
  public BroadphaseProxy getBroadphaseHandle()
  {
    return this.broadphaseHandle;
  }
  
  public void setBroadphaseHandle(BroadphaseProxy broadphaseHandle)
  {
    this.broadphaseHandle = broadphaseHandle;
  }
  
  public Transform getInterpolationWorldTransform(Transform out)
  {
    out.set(this.interpolationWorldTransform);
    return out;
  }
  
  public void setInterpolationWorldTransform(Transform interpolationWorldTransform)
  {
    this.interpolationWorldTransform.set(interpolationWorldTransform);
  }
  
  public void setInterpolationLinearVelocity(Vector3f linvel)
  {
    this.interpolationLinearVelocity.set(linvel);
  }
  
  public void setInterpolationAngularVelocity(Vector3f angvel)
  {
    this.interpolationAngularVelocity.set(angvel);
  }
  
  public Vector3f getInterpolationLinearVelocity(Vector3f out)
  {
    out.set(this.interpolationLinearVelocity);
    return out;
  }
  
  public Vector3f getInterpolationAngularVelocity(Vector3f out)
  {
    out.set(this.interpolationAngularVelocity);
    return out;
  }
  
  public int getIslandTag()
  {
    return this.islandTag1;
  }
  
  public void setIslandTag(int islandTag)
  {
    this.islandTag1 = islandTag;
  }
  
  public int getCompanionId()
  {
    return this.companionId;
  }
  
  public void setCompanionId(int companionId)
  {
    this.companionId = companionId;
  }
  
  public float getHitFraction()
  {
    return this.hitFraction;
  }
  
  public void setHitFraction(float hitFraction)
  {
    this.hitFraction = hitFraction;
  }
  
  public int getCollisionFlags()
  {
    return this.collisionFlags;
  }
  
  public void setCollisionFlags(int collisionFlags)
  {
    this.collisionFlags = collisionFlags;
  }
  
  public float getCcdSweptSphereRadius()
  {
    return this.ccdSweptSphereRadius;
  }
  
  public void setCcdSweptSphereRadius(float ccdSweptSphereRadius)
  {
    this.ccdSweptSphereRadius = ccdSweptSphereRadius;
  }
  
  public float getCcdMotionThreshold()
  {
    return this.ccdMotionThreshold;
  }
  
  public float getCcdSquareMotionThreshold()
  {
    return this.ccdMotionThreshold * this.ccdMotionThreshold;
  }
  
  public void setCcdMotionThreshold(float ccdMotionThreshold)
  {
    this.ccdMotionThreshold = ccdMotionThreshold;
  }
  
  public Object getUserPointer()
  {
    return this.userObjectPointer;
  }
  
  public void setUserPointer(Object userObjectPointer)
  {
    this.userObjectPointer = userObjectPointer;
  }
  
  public boolean checkCollideWith(CollisionObject local_co)
  {
    if (this.checkCollideWith) {
      return checkCollideWithOverride(local_co);
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.CollisionObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */