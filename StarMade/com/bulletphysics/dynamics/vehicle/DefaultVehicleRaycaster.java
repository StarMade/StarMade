/*  1:   */package com.bulletphysics.dynamics.vehicle;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*  4:   */import com.bulletphysics.dynamics.DynamicsWorld;
/*  5:   */import com.bulletphysics.dynamics.RigidBody;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */
/* 34:   */public class DefaultVehicleRaycaster
/* 35:   */  extends VehicleRaycaster
/* 36:   */{
/* 37:   */  protected DynamicsWorld dynamicsWorld;
/* 38:   */  
/* 39:   */  public DefaultVehicleRaycaster(DynamicsWorld world)
/* 40:   */  {
/* 41:41 */    this.dynamicsWorld = world;
/* 42:   */  }
/* 43:   */  
/* 45:   */  public Object castRay(Vector3f from, Vector3f to, VehicleRaycasterResult result)
/* 46:   */  {
/* 47:47 */    CollisionWorld.ClosestRayResultCallback rayCallback = new CollisionWorld.ClosestRayResultCallback(from, to);
/* 48:   */    
/* 49:49 */    this.dynamicsWorld.rayTest(from, to, rayCallback);
/* 50:   */    
/* 51:51 */    if (rayCallback.hasHit()) {
/* 52:52 */      RigidBody body = RigidBody.upcast(rayCallback.collisionObject);
/* 53:53 */      if ((body != null) && (body.hasContactResponse())) {
/* 54:54 */        result.hitPointInWorld.set(rayCallback.hitPointWorld);
/* 55:55 */        result.hitNormalInWorld.set(rayCallback.hitNormalWorld);
/* 56:56 */        result.hitNormalInWorld.normalize();
/* 57:57 */        result.distFraction = rayCallback.closestHitFraction;
/* 58:58 */        return body;
/* 59:   */      }
/* 60:   */    }
/* 61:61 */    return null;
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.DefaultVehicleRaycaster
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */