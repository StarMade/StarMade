/*  1:   */package org.schema.game.common.data.physics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.dispatch.CollisionObject;
/*  4:   */import com.bulletphysics.linearmath.Transform;
/*  5:   */import org.schema.schine.network.objects.container.PhysicsDataContainer;
/*  6:   */
/*  7:   */public class PairCachingGhostObjectAlignable extends PairCachingGhostObjectExt implements RelativeBody
/*  8:   */{
/*  9:   */  public Transform localWorldTransform;
/* 10:   */  
/* 11:   */  public PairCachingGhostObjectAlignable(PhysicsDataContainer paramPhysicsDataContainer)
/* 12:   */  {
/* 13:13 */    super(paramPhysicsDataContainer);
/* 14:   */  }
/* 15:   */  
/* 20:   */  public boolean checkCollideWith(CollisionObject paramCollisionObject)
/* 21:   */  {
/* 22:22 */    return !(paramCollisionObject instanceof PairCachingGhostObjectAlignable);
/* 23:   */  }
/* 24:   */  
/* 27:   */  public String toString()
/* 28:   */  {
/* 29:29 */    return "PCGhostObjExt(" + getUserPointer() + "->Attached(" + getAttached() + "))@" + hashCode();
/* 30:   */  }
/* 31:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectAlignable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */