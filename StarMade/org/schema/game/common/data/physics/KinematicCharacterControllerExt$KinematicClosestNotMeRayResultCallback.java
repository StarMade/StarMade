/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*     */ import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class KinematicCharacterControllerExt$KinematicClosestNotMeRayResultCallback extends CollisionWorld.ClosestRayResultCallback
/*     */ {
/*     */   protected CollisionObject me;
/*     */ 
/*     */   public KinematicCharacterControllerExt$KinematicClosestNotMeRayResultCallback(CollisionObject paramCollisionObject)
/*     */   {
/* 113 */     super(new Vector3f(), new Vector3f());
/* 114 */     this.me = paramCollisionObject;
/*     */   }
/*     */ 
/*     */   public float addSingleResult(CollisionWorld.LocalRayResult paramLocalRayResult, boolean paramBoolean)
/*     */   {
/* 120 */     if (paramLocalRayResult.collisionObject == this.me) {
/* 121 */       return 1.0F;
/*     */     }
/*     */ 
/* 124 */     return super.addSingleResult(paramLocalRayResult, paramBoolean);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt.KinematicClosestNotMeRayResultCallback
 * JD-Core Version:    0.6.2
 */