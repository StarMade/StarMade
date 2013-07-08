package org.schema.game.network;

import class_1041;
import java.util.HashMap;
import org.schema.schine.network.server.ServerProcessor;

public class StarMadeServerStats
{
  public long totalMemory;
  public long freeMemory;
  public long takenMemory;
  public int totalPackagesQueued;
  public int lastAllocatedSegmentData;
  public int playerCount;
  public long ping;
  
  public static StarMadeServerStats decode(Object[] paramArrayOfObject)
  {
    StarMadeServerStats localStarMadeServerStats;
    (localStarMadeServerStats = new StarMadeServerStats()).totalMemory = ((Long)paramArrayOfObject[0]).longValue();
    localStarMadeServerStats.freeMemory = ((Long)paramArrayOfObject[1]).longValue();
    localStarMadeServerStats.takenMemory = (localStarMadeServerStats.totalMemory - localStarMadeServerStats.freeMemory);
    localStarMadeServerStats.totalPackagesQueued = ((Integer)paramArrayOfObject[2]).intValue();
    localStarMadeServerStats.lastAllocatedSegmentData = ((Integer)paramArrayOfObject[3]).intValue();
    localStarMadeServerStats.playerCount = ((Integer)paramArrayOfObject[4]).intValue();
    return localStarMadeServerStats;
  }
  
  public static Object[] encode(class_1041 paramclass_1041)
  {
    long l1 = Runtime.getRuntime().totalMemory();
    long l2 = Runtime.getRuntime().freeMemory();
    return new Object[] { Long.valueOf(l1), Long.valueOf(l2), Integer.valueOf(ServerProcessor.totalPackagesQueued), Integer.valueOf(class_1041.field_145), Integer.valueOf(paramclass_1041.b10().size()) };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.StarMadeServerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */