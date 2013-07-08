package org.schema.game.common.data.physics;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.linearmath.Transform;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class PairCachingGhostObjectAlignable
  extends PairCachingGhostObjectExt
  implements RelativeBody
{
  public Transform localWorldTransform;
  
  public PairCachingGhostObjectAlignable(PhysicsDataContainer paramPhysicsDataContainer)
  {
    super(paramPhysicsDataContainer);
  }
  
  public boolean checkCollideWith(CollisionObject paramCollisionObject)
  {
    return !(paramCollisionObject instanceof PairCachingGhostObjectAlignable);
  }
  
  public String toString()
  {
    return "PCGhostObjExt(" + getUserPointer() + "->Attached(" + getAttached() + "))@" + hashCode();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.PairCachingGhostObjectAlignable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */