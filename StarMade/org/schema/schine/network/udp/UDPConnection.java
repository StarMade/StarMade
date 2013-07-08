package org.schema.schine.network.udp;

public class UDPConnection
{
  short sequenceNumber = -32768;
  private final short[] ackBitField = new short[32];
  
  public static boolean sequenceMoreRecent(int paramInt1, int paramInt2, int paramInt3)
  {
    return ((paramInt1 > paramInt2) && (paramInt1 - paramInt2 <= paramInt3 / 2)) || ((paramInt2 > paramInt1) && (paramInt2 - paramInt1 > paramInt3 / 2));
  }
  
  public void receive() {}
  
  public void send(byte[] paramArrayOfByte) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */