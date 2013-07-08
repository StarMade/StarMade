package org.schema.schine.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPProcessor$MessageWriter
  implements Runnable
{
  private UDPEndpoint endpoint;
  private DatagramPacket packet;
  
  public UDPProcessor$MessageWriter(UDPProcessor paramUDPProcessor, UDPEndpoint paramUDPEndpoint, DatagramPacket paramDatagramPacket)
  {
    this.endpoint = paramUDPEndpoint;
    this.packet = paramDatagramPacket;
  }
  
  public void run()
  {
    if (!this.endpoint.isConnected()) {
      return;
    }
    try
    {
      UDPProcessor.access$100(this.this$0).getSocket().send(this.packet);
      return;
    }
    catch (Exception localException)
    {
      new UDPException("Error sending datagram to:" + UDPProcessor.access$000(this.this$0), localException).fillInStackTrace();
      localException.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.MessageWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */