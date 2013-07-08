/*  1:   */package org.schema.schine.network.udp;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.net.DatagramPacket;
/*  5:   */import java.net.DatagramSocket;
/*  6:   */import java.net.SocketAddress;
/*  7:   */
/*  8:   */public class UDPEndpoint
/*  9:   */{
/* 10:   */  private int id;
/* 11:   */  private SocketAddress address;
/* 12:   */  private DatagramSocket socket;
/* 13:13 */  private boolean connected = true;
/* 14:   */  private UDPProcessor processor;
/* 15:   */  
/* 16:   */  public UDPEndpoint(UDPProcessor paramUDPProcessor, int paramInt, SocketAddress paramSocketAddress, DatagramSocket paramDatagramSocket)
/* 17:   */  {
/* 18:18 */    this.id = paramInt;
/* 19:19 */    this.address = paramSocketAddress;
/* 20:20 */    this.socket = paramDatagramSocket;
/* 21:21 */    this.processor = paramUDPProcessor;
/* 22:   */  }
/* 23:   */  
/* 26:   */  public void close()
/* 27:   */  {
/* 28:28 */    close(false);
/* 29:   */  }
/* 30:   */  
/* 34:   */  public void close(boolean paramBoolean)
/* 35:   */  {
/* 36:   */    try
/* 37:   */    {
/* 38:38 */      this.processor.closeEndpoint(this);
/* 39:39 */      this.connected = false;
/* 40:   */      
/* 42:42 */      return;
/* 43:   */    }
/* 44:   */    catch (IOException paramBoolean)
/* 45:   */    {
/* 46:41 */      throw new UDPException("Error closing endpoint for socket:" + this.socket, paramBoolean);
/* 47:   */    }
/* 48:   */  }
/* 49:   */  
/* 50:   */  public String getAddress()
/* 51:   */  {
/* 52:47 */    return String.valueOf(this.address);
/* 53:   */  }
/* 54:   */  
/* 55:   */  public long getId()
/* 56:   */  {
/* 57:52 */    return this.id;
/* 58:   */  }
/* 59:   */  
/* 60:   */  protected SocketAddress getRemoteAddress()
/* 61:   */  {
/* 62:57 */    return this.address;
/* 63:   */  }
/* 64:   */  
/* 67:   */  public boolean isConnected()
/* 68:   */  {
/* 69:64 */    return this.connected;
/* 70:   */  }
/* 71:   */  
/* 72:   */  public void send(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 73:   */  {
/* 74:69 */    if (!isConnected()) {
/* 75:70 */      throw new UDPException("Endpoint is not connected:" + this);
/* 76:   */    }
/* 77:   */    
/* 78:   */    try
/* 79:   */    {
/* 80:75 */      paramArrayOfByte = new DatagramPacket(paramArrayOfByte, paramInt1, paramInt2, this.address);
/* 81:   */      
/* 85:80 */      this.processor.enqueueWrite(this, paramArrayOfByte); return;
/* 86:   */    }
/* 87:   */    catch (IOException paramArrayOfByte)
/* 88:   */    {
/* 89:84 */      throw new UDPException("Error sending datagram to:" + this.address, paramArrayOfByte);
/* 90:   */    }
/* 91:   */  }
/* 92:   */  
/* 94:   */  public String toString()
/* 95:   */  {
/* 96:91 */    return "UdpEndpoint[" + this.id + ", " + this.address + "]";
/* 97:   */  }
/* 98:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPEndpoint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */