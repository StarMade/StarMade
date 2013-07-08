package org.schema.schine.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPProcessor$HostThread
  extends Thread
{
  private DatagramSocket socket;
  private boolean running = true;
  private byte[] buffer = new byte[65535];
  
  public UDPProcessor$HostThread(UDPProcessor paramUDPProcessor)
  {
    setName("UDP Host@" + UDPProcessor.access$000(paramUDPProcessor));
    setDaemon(true);
  }
  
  public void close()
  {
    this.running = false;
    this.socket.close();
    join();
  }
  
  public void connect()
  {
    this.socket = new DatagramSocket(UDPProcessor.access$000(this.this$0));
  }
  
  protected DatagramSocket getSocket()
  {
    return this.socket;
  }
  
  private void reportError(IOException paramIOException) {}
  
  public void run()
  {
    while (this.running) {
      try
      {
        DatagramPacket localDatagramPacket = new DatagramPacket(this.buffer, this.buffer.length);
        this.socket.receive(localDatagramPacket);
        this.this$0.newData(localDatagramPacket);
      }
      catch (IOException localIOException)
      {
        if (!this.running) {
          return;
        }
        reportError(localIOException);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.HostThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */