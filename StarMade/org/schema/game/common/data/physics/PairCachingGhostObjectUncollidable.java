/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  4:   */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  5:   */
/*  6:   */public class PairCachingGhostObjectUncollidable extends PairCachingGhostObjectExt
/*  7:   */{
/*  8:   */  public PairCachingGhostObjectUncollidable(PhysicsDataContainer paramPhysicsDataContainer)
/*  9:   */  {
/* 10:10 */    super(paramPhysicsDataContainer);
/* 11:   */  }
/* 12:   */  
/* 17:   */  public boolean checkCollideWith(CollisionObject paramCollisionObject)
/* 18:   */  {
/* 19:19 */    return !(paramCollisionObject instanceof PairCachingGhostObjectUncollidable);
/* 20:   */  }
/* 21:   */  
/* 24:   */  public String toString()
/* 25:   */  {
/* 26:26 */    return "PCGhostObjUCMissile(" + getUserPointer() + ")";
/* 27:   */  }
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */