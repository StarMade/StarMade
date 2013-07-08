/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.dispatch.CollisionObject;
/*   4:    */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
/*   5:    */import com.bulletphysics.collision.dispatch.CollisionWorld.LocalRayResult;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */
/* 106:    */class KinematicCharacterControllerExt$KinematicClosestNotMeRayResultCallback
/* 107:    */  extends CollisionWorld.ClosestRayResultCallback
/* 108:    */{
/* 109:    */  protected CollisionObject me;
/* 110:    */  
/* 111:    */  public KinematicCharacterControllerExt$KinematicClosestNotMeRayResultCallback(CollisionObject paramCollisionObject)
/* 112:    */  {
/* 113:113 */    super(new Vector3f(), new Vector3f());
/* 114:114 */    this.me = paramCollisionObject;
/* 115:    */  }
/* 116:    */  
/* 118:    */  public float addSingleResult(CollisionWorld.LocalRayResult paramLocalRayResult, boolean paramBoolean)
/* 119:    */  {
/* 120:120 */    if (paramLocalRayResult.collisionObject == this.me) {
/* 121:121 */      return 1.0F;
/* 122:    */    }
/* 123:    */    
/* 124:124 */    return super.addSingleResult(paramLocalRayResult, paramBoolean);
/* 125:    */  }
/* 126:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.KinematicCharacterControllerExt.KinematicClosestNotMeRayResultCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */