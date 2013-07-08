package com.bulletphysics.dynamics.vehicle;

import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import javax.vecmath.Vector3f;

public class DefaultVehicleRaycaster
  extends VehicleRaycaster
{
  protected DynamicsWorld dynamicsWorld;
  
  public DefaultVehicleRaycaster(DynamicsWorld world)
  {
    this.dynamicsWorld = world;
  }
  
  public Object castRay(Vector3f from, Vector3f local_to, VehicleRaycasterResult result)
  {
    CollisionWorld.ClosestRayResultCallback rayCallback = new CollisionWorld.ClosestRayResultCallback(from, local_to);
    this.dynamicsWorld.rayTest(from, local_to, rayCallback);
    if (rayCallback.hasHit())
    {
      RigidBody body = RigidBody.upcast(rayCallback.collisionObject);
      if ((body != null) && (body.hasContactResponse()))
      {
        result.hitPointInWorld.set(rayCallback.hitPointWorld);
        result.hitNormalInWorld.set(rayCallback.hitNormalWorld);
        result.hitNormalInWorld.normalize();
        result.distFraction = rayCallback.closestHitFraction;
        return body;
      }
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.DefaultVehicleRaycaster
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */