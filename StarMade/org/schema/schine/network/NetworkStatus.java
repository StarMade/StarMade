/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  4:   */public class NetworkStatus
/*  5:   */{
/*  6:   */  private long totalBytesSent;
/*  7:   */  
/*  8:   */  private int bytesSent;
/*  9:   */  
/* 10:   */  private int bytesSentPerSecond;
/* 11:   */  
/* 12:   */  private long totalBytesReceived;
/* 13:   */  private int bytesReceived;
/* 14:   */  private int bytesReceivedPerSecond;
/* 15:   */  private long lastSecondSent;
/* 16:   */  private long lastSecondReceived;
/* 17:   */  
/* 18:   */  public void addBytesReceived(int paramInt)
/* 19:   */  {
/* 20:20 */    this.totalBytesReceived += paramInt;
/* 21:21 */    this.bytesReceived += paramInt;
/* 22:22 */    if (this.lastSecondReceived + 1000L < System.currentTimeMillis()) {
/* 23:23 */      this.bytesReceivedPerSecond = this.bytesReceived;
/* 24:24 */      this.bytesReceived = 0;
/* 25:25 */      this.lastSecondReceived = System.currentTimeMillis();
/* 26:   */    }
/* 27:   */  }
/* 28:   */  
/* 31:   */  public void addBytesSent(int paramInt)
/* 32:   */  {
/* 33:33 */    this.totalBytesSent += paramInt;
/* 34:34 */    this.bytesSent += paramInt;
/* 35:35 */    if (this.lastSecondSent + 1000L < System.currentTimeMillis())
/* 36:   */    {
/* 37:37 */      this.bytesSentPerSecond = this.bytesSent;
/* 38:38 */      this.bytesSent = 0;
/* 39:39 */      this.lastSecondSent = System.currentTimeMillis();
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 49:   */  public int getBytesReceivedPerSecond()
/* 50:   */  {
/* 51:51 */    addBytesReceived(0);
/* 52:52 */    return this.bytesReceivedPerSecond;
/* 53:   */  }
/* 54:   */  
/* 57:   */  public int getBytesSentPerSecond()
/* 58:   */  {
/* 59:59 */    addBytesSent(0);
/* 60:60 */    return this.bytesSentPerSecond;
/* 61:   */  }
/* 62:   */  
/* 65:   */  public long getTotalBytesReceived()
/* 66:   */  {
/* 67:67 */    return this.totalBytesReceived;
/* 68:   */  }
/* 69:   */  
/* 72:   */  public long getTotalBytesSent()
/* 73:   */  {
/* 74:74 */    return this.totalBytesSent;
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.NetworkStatus
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */