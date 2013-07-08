package org.schema.schine.network.client;

import java.io.InputStream;
import java.io.PrintStream;
import org.schema.schine.network.StateInterface;

class ClientProcessor$Pinger
{
  private boolean waitingForPong;
  private long lastPingTime;
  private long lastPong;
  
  private ClientProcessor$Pinger(ClientProcessor paramClientProcessor) {}
  
  public boolean checkPing(InputStream paramInputStream)
  {
    if ((paramInputStream = (byte)paramInputStream.read()) == -2)
    {
      this.waitingForPong = false;
      ClientProcessor.access$000(this.this$0).setPing(System.currentTimeMillis() - this.lastPingTime);
      this.lastPong = System.currentTimeMillis();
      return true;
    }
    if (paramInputStream == -1)
    {
      sendPong();
      return true;
    }
    return false;
  }
  
  public void execute()
  {
    if (ClientProcessor.access$100(this.this$0).getId() > 0) {
      synchronized (ClientProcessor.access$200(this.this$0))
      {
        if ((!this.waitingForPong) && (this.lastPingTime + 1000L < System.currentTimeMillis()) && (sendPing()))
        {
          this.lastPingTime = System.currentTimeMillis();
          this.waitingForPong = true;
        }
        long l1 = System.currentTimeMillis() - this.lastPingTime;
        long l2 = System.currentTimeMillis() - this.lastPong;
        if ((this.waitingForPong) && (l1 > 5000L))
        {
          ((ClientControllerInterface)this.this$0.getState().getController()).alertMessage("WARNING: Server doesn't answer! (" + l2 / 1000L + " sec)\nServer is temporary under high load!.\n\nif this message does not go away\nthe server might be frozen\nPlease tell the admin to send a report!");
          System.err.println("[CLIENTPROCESSOR][WARNING] Ping time of client (" + ClientProcessor.access$000(this.this$0).getId() + ") exceeded time limit: retrying! pending requests: " + this.this$0.getPendingRequests());
          this.waitingForPong = false;
        }
        return;
      }
    }
  }
  
  public boolean sendPing()
  {
    if (ClientProcessor.access$000(this.this$0).getId() < 0)
    {
      System.err.println("[CLIENT] not logged int to server: discarding ping");
      return false;
    }
    ClientProcessor.access$302(this.this$0, true);
    synchronized (ClientProcessor.access$400(this.this$0))
    {
      ClientProcessor.access$400(this.this$0).notify();
    }
    return true;
  }
  
  public void sendPong()
  {
    if (ClientProcessor.access$000(this.this$0).getId() < 0)
    {
      System.err.println("[CLIENT] not logged int to server: discarding pong");
      return;
    }
    ClientProcessor.access$502(this.this$0, true);
    synchronized (ClientProcessor.access$400(this.this$0))
    {
      ClientProcessor.access$400(this.this$0).notify();
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor.Pinger
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */