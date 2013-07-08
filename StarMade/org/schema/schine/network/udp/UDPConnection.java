/*  1:   */package org.schema.schine.network.udp;
/*  2:   */
/*  3:   */public class UDPConnection
/*  4:   */{
/*  5:   */  public static boolean sequenceMoreRecent(int paramInt1, int paramInt2, int paramInt3) {
/*  6: 6 */    return ((paramInt1 > paramInt2) && (paramInt1 - paramInt2 <= paramInt3 / 2)) || ((paramInt2 > paramInt1) && (paramInt2 - paramInt1 > paramInt3 / 2));
/*  7:   */  }
/*  8:   */  
/* 10:10 */  short sequenceNumber = -32768;
/* 11:   */  
/* 12:12 */  private final short[] ackBitField = new short[32];
/* 13:   */  
/* 14:   */  public void receive() {}
/* 15:   */  
/* 16:   */  public void send(byte[] paramArrayOfByte) {}
/* 17:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */