/*    */ package org.schema.schine.network;
/*    */ 
/*    */ public class IdGen
/*    */ {
/*  4 */   private static Integer idPool = new Integer(0);
/*    */   private static int independentIdPool;
/*  6 */   private static Short packetIdC = Short.valueOf((short)-32767);
/*    */   public static final int SERVER_ID = 0;
/* 10 */   private static int NETWORK_ID_CREATOR = 1;
/*    */ 
/*    */   public static int getFreeObjectId(int paramInt) {
/* 13 */     int i = 0;
/* 14 */     synchronized (idPool) {
/* 15 */       i = idPool.intValue();
/* 16 */       idPool = Integer.valueOf(idPool.intValue() + paramInt);
/*    */     }
/* 18 */     return i;
/*    */   }
/*    */   public static synchronized int getFreeStateId() {
/* 21 */     return NETWORK_ID_CREATOR++;
/*    */   }
/*    */ 
/*    */   public static synchronized int getIndependentId() {
/* 25 */     return independentIdPool++;
/*    */   }
/*    */ 
/*    */   public static synchronized short getNewPacketId() {
/* 29 */     synchronized (packetIdC) {
/* 30 */       if (packetIdC.shortValue() == -32768) {
/* 31 */         packetIdC = Short.valueOf((short)(packetIdC.shortValue() + 1));
/*    */       }
/* 33 */       short s = packetIdC.shortValue();
/* 34 */       packetIdC = Short.valueOf((short)(packetIdC.shortValue() + 1));
/* 35 */       return s;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.IdGen
 * JD-Core Version:    0.6.2
 */