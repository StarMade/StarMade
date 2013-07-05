/*    */ package org.schema.game.network;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import vg;
/*    */ 
/*    */ public class StarMadeServerStats
/*    */ {
/*    */   public long totalMemory;
/*    */   public long freeMemory;
/*    */   public long takenMemory;
/*    */   public int totalPackagesQueued;
/*    */   public int lastAllocatedSegmentData;
/*    */   public int playerCount;
/*    */   public long ping;
/*    */ 
/*    */   public static StarMadeServerStats decode(Object[] paramArrayOfObject)
/*    */   {
/*    */     StarMadeServerStats localStarMadeServerStats;
/* 18 */     (
/* 19 */       localStarMadeServerStats = new StarMadeServerStats()).totalMemory = 
/* 19 */       ((Long)paramArrayOfObject[0]).longValue();
/* 20 */     localStarMadeServerStats.freeMemory = ((Long)paramArrayOfObject[1]).longValue();
/* 21 */     localStarMadeServerStats.takenMemory = (localStarMadeServerStats.totalMemory - localStarMadeServerStats.freeMemory);
/*    */ 
/* 23 */     localStarMadeServerStats.totalPackagesQueued = ((Integer)paramArrayOfObject[2]).intValue();
/* 24 */     localStarMadeServerStats.lastAllocatedSegmentData = ((Integer)paramArrayOfObject[3]).intValue();
/* 25 */     localStarMadeServerStats.playerCount = ((Integer)paramArrayOfObject[4]).intValue();
/*    */ 
/* 27 */     return localStarMadeServerStats;
/*    */   }
/*    */ 
/*    */   public static Object[] encode(vg paramvg)
/*    */   {
/* 32 */     long l1 = Runtime.getRuntime().totalMemory();
/* 33 */     long l2 = Runtime.getRuntime().freeMemory();
/* 34 */     return new Object[] { Long.valueOf(l1), Long.valueOf(l2), Integer.valueOf(ServerProcessor.totalPackagesQueued), Integer.valueOf(vg.b), Integer.valueOf(paramvg.b().size()) };
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.StarMadeServerStats
 * JD-Core Version:    0.6.2
 */