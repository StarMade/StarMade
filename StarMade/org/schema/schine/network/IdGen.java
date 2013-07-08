/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  3:   */public class IdGen {
/*  4: 4 */  private static Integer idPool = new Integer(0);
/*  5:   */  private static int independentIdPool;
/*  6: 6 */  private static Short packetIdC = Short.valueOf((short)-32767);
/*  7:   */  
/*  8:   */  public static final int SERVER_ID = 0;
/*  9:   */  
/* 10:10 */  private static int NETWORK_ID_CREATOR = 1;
/* 11:   */  
/* 12:   */  public static int getFreeObjectId(int paramInt) {
/* 13:13 */    int i = 0;
/* 14:14 */    synchronized (idPool) {
/* 15:15 */      i = idPool.intValue();
/* 16:16 */      idPool = Integer.valueOf(idPool.intValue() + paramInt);
/* 17:   */    }
/* 18:18 */    return i;
/* 19:   */  }
/* 20:   */  
/* 21:21 */  public static synchronized int getFreeStateId() { return NETWORK_ID_CREATOR++; }
/* 22:   */  
/* 23:   */  public static synchronized int getIndependentId()
/* 24:   */  {
/* 25:25 */    return independentIdPool++;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public static synchronized short getNewPacketId() {
/* 29:29 */    synchronized (packetIdC) {
/* 30:30 */      if (packetIdC.shortValue() == -32768) {
/* 31:31 */        packetIdC = Short.valueOf((short)(packetIdC.shortValue() + 1));
/* 32:   */      }
/* 33:33 */      short s = packetIdC.shortValue();
/* 34:34 */      packetIdC = Short.valueOf((short)(packetIdC.shortValue() + 1));
/* 35:35 */      return s;
/* 36:   */    }
/* 37:   */  }
/* 38:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.IdGen
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */