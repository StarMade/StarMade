package org.schema.schine.network;

public class IdGen
{
  private static Integer idPool = new Integer(0);
  private static int independentIdPool;
  private static Short packetIdC = Short.valueOf((short)-32767);
  public static final int SERVER_ID = 0;
  private static int NETWORK_ID_CREATOR = 1;
  
  public static int getFreeObjectId(int paramInt)
  {
    int i = 0;
    synchronized (idPool)
    {
      i = idPool.intValue();
      idPool = Integer.valueOf(idPool.intValue() + paramInt);
    }
    return i;
  }
  
  public static synchronized int getFreeStateId()
  {
    return NETWORK_ID_CREATOR++;
  }
  
  public static synchronized int getIndependentId()
  {
    return independentIdPool++;
  }
  
  public static synchronized short getNewPacketId()
  {
    synchronized (packetIdC)
    {
      if (packetIdC.shortValue() == -32768) {
        packetIdC = Short.valueOf((short)(packetIdC.shortValue() + 1));
      }
      short s = packetIdC.shortValue();
      packetIdC = Short.valueOf((short)(packetIdC.shortValue() + 1));
      return s;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.IdGen
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */