/*    */ package com.bulletphysics.dynamics.vehicle;
/*    */ 
/*    */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*    */ import com.bulletphysics.dynamics.DynamicsWorld;
/*    */ import com.bulletphysics.dynamics.RigidBody;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class DefaultVehicleRaycaster extends VehicleRaycaster
/*    */ {
/*    */   protected DynamicsWorld dynamicsWorld;
/*    */ 
/*    */   public DefaultVehicleRaycaster(DynamicsWorld world)
/*    */   {
/* 41 */     this.dynamicsWorld = world;
/*    */   }
/*    */ 
/*    */   public Object castRay(Vector3f from, Vector3f to, VehicleRaycasterResult result)
/*    */   {
/* 47 */     CollisionWorld.ClosestRayResultCallback rayCallback = new CollisionWorld.ClosestRayResultCallback(from, to);
/*    */ 
/* 49 */     this.dynamicsWorld.rayTest(from, to, rayCallback);
/*    */ 
/* 51 */     if (rayCallback.hasHit()) {
/* 52 */       RigidBody body = RigidBody.upcast(rayCallback.collisionObject);
/* 53 */       if ((body != null) && (body.hasContactResponse())) {
/* 54 */         result.hitPointInWorld.set(rayCallback.hitPointWorld);
/* 55 */         result.hitNormalInWorld.set(rayCallback.hitNormalWorld);
/* 56 */         result.hitNormalInWorld.normalize();
/* 57 */         result.distFraction = rayCallback.closestHitFraction;
/* 58 */         return body;
/*    */       }
/*    */     }
/* 61 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.vehicle.DefaultVehicleRaycaster
 * JD-Core Version:    0.6.2
 */