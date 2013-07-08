/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestConvexResultCallback;
/*  4:   */
/*  5:   */public class ClosestConvexResultCallbackExt extends CollisionWorld.ClosestConvexResultCallback
/*  6:   */{
/*  7:   */  public Object userData;
/*  8:   */  public boolean checkHasHitOnly;
/*  9:   */  
/* 10:   */  public ClosestConvexResultCallbackExt(javax.vecmath.Vector3f paramVector3f1, javax.vecmath.Vector3f paramVector3f2)
/* 11:   */  {
/* 12:12 */    super(paramVector3f1, paramVector3f2);
/* 13:   */  }
/* 14:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ClosestConvexResultCallbackExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */