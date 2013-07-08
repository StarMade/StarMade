package org.schema.game.common.data.physics;

import com.bulletphysics.collision.dispatch.CollisionObject;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class PairCachingGhostObjectUncollidable
  extends PairCachingGhostObjectExt
{
  public PairCachingGhostObjectUncollidable(PhysicsDataContainer paramPhysicsDataContainer)
  {
    super(paramPhysicsDataContainer);
  }
  
  public boolean checkCollideWith(CollisionObject paramCollisionObject)
  {
    return !(paramCollisionObject instanceof PairCachingGhostObjectUncollidable);
  }
  
  public String toString()
  {
    return "PCGhostObjUCMissile(" + getUserPointer() + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectUncollidable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */