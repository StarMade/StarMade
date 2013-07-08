/*  1:   */package org.schema.schine.network.udp;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.net.DatagramPacket;
/*  5:   */import java.net.DatagramSocket;
/*  6:   */
/* 19:   */public class UDPProcessor$HostThread
/* 20:   */  extends Thread
/* 21:   */{
/* 22:   */  private DatagramSocket socket;
/* 23:23 */  private boolean running = true;
/* 24:   */  
/* 25:25 */  private byte[] buffer = new byte[65535];
/* 26:   */  
/* 27:   */  public UDPProcessor$HostThread(UDPProcessor paramUDPProcessor)
/* 28:   */  {
/* 29:29 */    setName("UDP Host@" + UDPProcessor.access$000(paramUDPProcessor));
/* 30:30 */    setDaemon(true);
/* 31:   */  }
/* 32:   */  
/* 34:   */  public void close()
/* 35:   */  {
/* 36:36 */    this.running = false;
/* 37:   */    
/* 39:39 */    this.socket.close();
/* 40:   */    
/* 42:42 */    join();
/* 43:   */  }
/* 44:   */  
/* 45:   */  public void connect()
/* 46:   */  {
/* 47:47 */    this.socket = new DatagramSocket(UDPProcessor.access$000(this.this$0));
/* 48:   */  }
/* 49:   */  
/* 51:   */  protected DatagramSocket getSocket()
/* 52:   */  {
/* 53:53 */    return this.socket;
/* 54:   */  }
/* 55:   */  
/* 59:   */  private void reportError(IOException paramIOException) {}
/* 60:   */  
/* 64:   */  public void run()
/* 65:   */  {
/* 66:66 */    while (this.running)
/* 67:   */    {
/* 68:   */      try
/* 69:   */      {
/* 71:71 */        DatagramPacket localDatagramPacket = new DatagramPacket(this.buffer, this.buffer.length);
/* 72:72 */        this.socket.receive(localDatagramPacket);
/* 73:   */        
/* 74:74 */        this.this$0.newData(localDatagramPacket);
/* 75:   */      } catch (IOException localIOException) {
/* 76:76 */        if (!this.running) {
/* 77:77 */          return;
/* 78:   */        }
/* 79:   */        
/* 80:80 */        reportError(localIOException);
/* 81:   */      }
/* 82:   */    }
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.HostThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */