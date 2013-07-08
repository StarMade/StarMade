/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*  4:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  5:   */import com.bulletphysics.collision.dispatch.ConvexConvexAlgorithm;
/*  6:   */import com.bulletphysics.collision.dispatch.ManifoldResult;
/*  7:   */
/* 37:   */public class ConvexConvexExtAlgorithm
/* 38:   */  extends ConvexConvexAlgorithm
/* 39:   */{
/* 40:   */  public void processCollision(CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, DispatcherInfo paramDispatcherInfo, ManifoldResult paramManifoldResult)
/* 41:   */  {
/* 42:42 */    if (((paramCollisionObject1.getCollisionShape() instanceof CubeShape)) || ((paramCollisionObject2.getCollisionShape() instanceof CubeShape))) {
/* 43:43 */      return;
/* 44:   */    }
/* 45:   */    
/* 47:47 */    super.processCollision(paramCollisionObject1, paramCollisionObject2, paramDispatcherInfo, paramManifoldResult);
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ConvexConvexExtAlgorithm
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */