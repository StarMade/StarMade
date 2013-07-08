package org.schema.schine.network;

public class NetworkStatus
{
  private long totalBytesSent;
  private int bytesSent;
  private int bytesSentPerSecond;
  private long totalBytesReceived;
  private int bytesReceived;
  private int bytesReceivedPerSecond;
  private long lastSecondSent;
  private long lastSecondReceived;
  
  public void addBytesReceived(int paramInt)
  {
    this.totalBytesReceived += paramInt;
    this.bytesReceived += paramInt;
    if (this.lastSecondReceived + 1000L < System.currentTimeMillis())
    {
      this.bytesReceivedPerSecond = this.bytesReceived;
      this.bytesReceived = 0;
      this.lastSecondReceived = System.currentTimeMillis();
    }
  }
  
  public void addBytesSent(int paramInt)
  {
    this.totalBytesSent += paramInt;
    this.bytesSent += paramInt;
    if (this.lastSecondSent + 1000L < System.currentTimeMillis())
    {
      this.bytesSentPerSecond = this.bytesSent;
      this.bytesSent = 0;
      this.lastSecondSent = System.currentTimeMillis();
    }
  }
  
  public int getBytesReceivedPerSecond()
  {
    addBytesReceived(0);
    return this.bytesReceivedPerSecond;
  }
  
  public int getBytesSentPerSecond()
  {
    addBytesSent(0);
    return this.bytesSentPerSecond;
  }
  
  public long getTotalBytesReceived()
  {
    return this.totalBytesReceived;
  }
  
  public long getTotalBytesSent()
  {
    return this.totalBytesSent;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.NetworkStatus
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */