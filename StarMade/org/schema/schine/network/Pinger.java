/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  3:   */import java.util.Observable;
/*  4:   */
/*  5:   */public class Pinger extends Observable { private static byte[] pingBytes;
/*  6:   */  private static byte[] pongBytes;
/*  7:   */  
/*  8: 8 */  static { (Pinger.pingBytes = new byte[2])[0] = 23;
/*  9: 9 */    pingBytes[1] = -1;
/* 10:   */    
/* 11:11 */    (
/* 12:   */    
/* 13:13 */      Pinger.pongBytes = new byte[2])[0] = 23;
/* 14:14 */    pongBytes[1] = -2;
/* 15:   */  }
/* 16:   */  
/* 17:17 */  public static byte[] createPing() { return pingBytes; }
/* 18:   */  
/* 21:   */  public static byte[] createPong()
/* 22:   */  {
/* 23:23 */    return pongBytes;
/* 24:   */  }
/* 25:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Pinger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */