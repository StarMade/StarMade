package com.bulletphysics.collision.dispatch;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConvexShape;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Vector3f;

public class GhostObject
  extends CollisionObject
{
  protected ObjectArrayList<CollisionObject> overlappingObjects = new ObjectArrayList();
  
  public GhostObject()
  {
    this.internalType = CollisionObjectType.GHOST_OBJECT;
  }
  
  public void addOverlappingObjectInternal(BroadphaseProxy otherProxy, BroadphaseProxy thisProxy)
  {
    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
    assert (otherObject != null);
    int index = this.overlappingObjects.indexOf(otherObject);
    if (index == -1) {
      this.overlappingObjects.add(otherObject);
    }
  }
  
  public void removeOverlappingObjectInternal(BroadphaseProxy otherProxy, Dispatcher dispatcher, BroadphaseProxy thisProxy)
  {
    CollisionObject otherObject = (CollisionObject)otherProxy.clientObject;
    assert (otherObject != null);
    int index = this.overlappingObjects.indexOf(otherObject);
    if (index != -1)
    {
      this.overlappingObjects.set(index, this.overlappingObjects.getQuick(this.overlappingObjects.size() - 1));
      this.overlappingObjects.removeQuick(this.overlappingObjects.size() - 1);
    }
  }
  
  public void convexSweepTest(ConvexShape arg1, Transform arg2, Transform arg3, CollisionWorld.ConvexResultCallback arg4, float arg5)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      .Stack tmp11_7 = tmp7_5;
      tmp11_7.push$javax$vecmath$Vector3f();
      tmp11_7.push$javax$vecmath$Quat4f();
      Transform convexFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      Transform convexToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      convexFromTrans.set(convexFromWorld);
      convexToTrans.set(convexToWorld);
      Vector3f castShapeAabbMin = localStack.get$javax$vecmath$Vector3f();
      Vector3f castShapeAabbMax = localStack.get$javax$vecmath$Vector3f();
      Vector3f linVel = localStack.get$javax$vecmath$Vector3f();
      Vector3f angVel = localStack.get$javax$vecmath$Vector3f();
      TransformUtil.calculateVelocity(convexFromTrans, convexToTrans, 1.0F, linVel, angVel);
      Transform local_R = localStack.get$com$bulletphysics$linearmath$Transform();
      local_R.setIdentity();
      local_R.setRotation(convexFromTrans.getRotation(localStack.get$javax$vecmath$Quat4f()));
      castShape.calculateTemporalAabb(local_R, linVel, angVel, 1.0F, castShapeAabbMin, castShapeAabbMax);
      Transform linVel = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int angVel = 0; angVel < this.overlappingObjects.size(); angVel++)
      {
        CollisionObject local_R = (CollisionObject)this.overlappingObjects.getQuick(angVel);
        if (resultCallback.needsCollision(local_R.getBroadphaseHandle()))
        {
          Vector3f collisionObjectAabbMin = localStack.get$javax$vecmath$Vector3f();
          Vector3f collisionObjectAabbMax = localStack.get$javax$vecmath$Vector3f();
          local_R.getCollisionShape().getAabb(local_R.getWorldTransform(linVel), collisionObjectAabbMin, collisionObjectAabbMax);
          AabbUtil2.aabbExpand(collisionObjectAabbMin, collisionObjectAabbMax, castShapeAabbMin, castShapeAabbMax);
          float[] hitLambda = { 1.0F };
          Vector3f hitNormal = localStack.get$javax$vecmath$Vector3f();
          if (AabbUtil2.rayAabb(convexFromWorld.origin, convexToWorld.origin, collisionObjectAabbMin, collisionObjectAabbMax, hitLambda, hitNormal)) {
            CollisionWorld.objectQuerySingle(castShape, convexFromTrans, convexToTrans, local_R, local_R.getCollisionShape(), local_R.getWorldTransform(linVel), resultCallback, allowedCcdPenetration);
          }
        }
      }
      return;
    }
    finally
    {
      .Stack tmp305_303 = localStack;
      tmp305_303.pop$com$bulletphysics$linearmath$Transform();
      .Stack tmp309_305 = tmp305_303;
      tmp309_305.pop$javax$vecmath$Vector3f();
      tmp309_305.pop$javax$vecmath$Quat4f();
    }
  }
  
  public void rayTest(Vector3f arg1, Vector3f arg2, CollisionWorld.RayResultCallback arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      Transform rayFromTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      rayFromTrans.setIdentity();
      rayFromTrans.origin.set(rayFromWorld);
      Transform rayToTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      rayToTrans.setIdentity();
      rayToTrans.origin.set(rayToWorld);
      Transform tmpTrans = localStack.get$com$bulletphysics$linearmath$Transform();
      for (int local_i = 0; local_i < this.overlappingObjects.size(); local_i++)
      {
        CollisionObject collisionObject = (CollisionObject)this.overlappingObjects.getQuick(local_i);
        if (resultCallback.needsCollision(collisionObject.getBroadphaseHandle())) {
          CollisionWorld.rayTestSingle(rayFromTrans, rayToTrans, collisionObject, collisionObject.getCollisionShape(), collisionObject.getWorldTransform(tmpTrans), resultCallback);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public int getNumOverlappingObjects()
  {
    return this.overlappingObjects.size();
  }
  
  public CollisionObject getOverlappingObject(int index)
  {
    return (CollisionObject)this.overlappingObjects.getQuick(index);
  }
  
  public ObjectArrayList<CollisionObject> getOverlappingPairs()
  {
    return this.overlappingObjects;
  }
  
  public static GhostObject upcast(CollisionObject colObj)
  {
    if (colObj.getInternalType() == CollisionObjectType.GHOST_OBJECT) {
      return (GhostObject)colObj;
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.dispatch.GhostObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */