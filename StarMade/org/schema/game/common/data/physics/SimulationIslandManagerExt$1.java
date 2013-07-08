/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*   4:    */import java.util.Comparator;
/*   5:    */
/* 393:    */final class SimulationIslandManagerExt$1
/* 394:    */  implements Comparator
/* 395:    */{
/* 396:    */  public final int compare(PersistentManifold paramPersistentManifold1, PersistentManifold paramPersistentManifold2)
/* 397:    */  {
/* 398:398 */    if (SimulationIslandManagerExt.access$000(paramPersistentManifold1) < SimulationIslandManagerExt.access$000(paramPersistentManifold2)) return -1; return 1;
/* 399:    */  }
/* 400:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SimulationIslandManagerExt.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */