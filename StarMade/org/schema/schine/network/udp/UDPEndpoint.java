package org.schema.schine.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class UDPEndpoint
{
  private int field_2168;
  private SocketAddress address;
  private DatagramSocket socket;
  private boolean connected = true;
  private UDPProcessor processor;
  
  public UDPEndpoint(UDPProcessor paramUDPProcessor, int paramInt, SocketAddress paramSocketAddress, DatagramSocket paramDatagramSocket)
  {
    this.field_2168 = paramInt;
    this.address = paramSocketAddress;
    this.socket = paramDatagramSocket;
    this.processor = paramUDPProcessor;
  }
  
  public void close()
  {
    close(false);
  }
  
  public void close(boolean paramBoolean)
  {
    try
    {
      this.processor.closeEndpoint(this);
      this.connected = false;
      return;
    }
    catch (IOException paramBoolean)
    {
      throw new UDPException("Error closing endpoint for socket:" + this.socket, paramBoolean);
    }
  }
  
  public String getAddress()
  {
    return String.valueOf(this.address);
  }
  
  public long getId()
  {
    return this.field_2168;
  }
  
  protected SocketAddress getRemoteAddress()
  {
    return this.address;
  }
  
  public boolean isConnected()
  {
    return this.connected;
  }
  
  public void send(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (!isConnected()) {
      throw new UDPException("Endpoint is not connected:" + this);
    }
    try
    {
      paramArrayOfByte = new DatagramPacket(paramArrayOfByte, paramInt1, paramInt2, this.address);
      this.processor.enqueueWrite(this, paramArrayOfByte);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new UDPException("Error sending datagram to:" + this.address, paramArrayOfByte);
    }
  }
  
  public String toString()
  {
    return "UdpEndpoint[" + this.field_2168 + ", " + this.address + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPEndpoint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */