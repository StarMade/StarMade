/*  1:   */package org.schema.game.network;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import org.schema.schine.network.server.ServerProcessor;
/*  5:   */
/*  6:   */public class StarMadeServerStats
/*  7:   */{
/*  8:   */  public long totalMemory;
/*  9:   */  public long freeMemory;
/* 10:   */  public long takenMemory;
/* 11:   */  public int totalPackagesQueued;
/* 12:   */  public int lastAllocatedSegmentData;
/* 13:   */  public int playerCount;
/* 14:   */  public long ping;
/* 15:   */  
/* 16:   */  public static StarMadeServerStats decode(Object[] paramArrayOfObject)
/* 17:   */  {
/* 18:   */    StarMadeServerStats localStarMadeServerStats;
/* 19:19 */    (localStarMadeServerStats = new StarMadeServerStats()).totalMemory = ((Long)paramArrayOfObject[0]).longValue();
/* 20:20 */    localStarMadeServerStats.freeMemory = ((Long)paramArrayOfObject[1]).longValue();
/* 21:21 */    localStarMadeServerStats.takenMemory = (localStarMadeServerStats.totalMemory - localStarMadeServerStats.freeMemory);
/* 22:   */    
/* 23:23 */    localStarMadeServerStats.totalPackagesQueued = ((Integer)paramArrayOfObject[2]).intValue();
/* 24:24 */    localStarMadeServerStats.lastAllocatedSegmentData = ((Integer)paramArrayOfObject[3]).intValue();
/* 25:25 */    localStarMadeServerStats.playerCount = ((Integer)paramArrayOfObject[4]).intValue();
/* 26:   */    
/* 27:27 */    return localStarMadeServerStats;
/* 28:   */  }
/* 29:   */  
/* 30:   */  public static Object[] encode(vg paramvg)
/* 31:   */  {
/* 32:32 */    long l1 = Runtime.getRuntime().totalMemory();
/* 33:33 */    long l2 = Runtime.getRuntime().freeMemory();
/* 34:34 */    return new Object[] { Long.valueOf(l1), Long.valueOf(l2), Integer.valueOf(ServerProcessor.totalPackagesQueued), Integer.valueOf(vg.b), Integer.valueOf(paramvg.b().size()) };
/* 35:   */  }
/* 36:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.StarMadeServerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */