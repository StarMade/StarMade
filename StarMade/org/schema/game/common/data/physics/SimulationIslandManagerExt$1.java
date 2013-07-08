package org.schema.game.common.data.physics;

import com.bulletphysics.collision.narrowphase.PersistentManifold;
import java.util.Comparator;

final class SimulationIslandManagerExt$1
  implements Comparator
{
  public final int compare(PersistentManifold paramPersistentManifold1, PersistentManifold paramPersistentManifold2)
  {
    if (SimulationIslandManagerExt.access$000(paramPersistentManifold1) < SimulationIslandManagerExt.access$000(paramPersistentManifold2)) {
      return -1;
    }
    return 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SimulationIslandManagerExt.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */