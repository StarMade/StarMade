package org.schema.schine.network;

import java.util.Observable;

public class Pinger
  extends Observable
{
  private static byte[] pingBytes;
  private static byte[] pongBytes;
  
  public static byte[] createPing()
  {
    return pingBytes;
  }
  
  public static byte[] createPong()
  {
    return pongBytes;
  }
  
  static
  {
    (Pinger.pingBytes = new byte[2])[0] = 23;
    pingBytes[1] = -1;
    (Pinger.pongBytes = new byte[2])[0] = 23;
    pongBytes[1] = -2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.Pinger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */