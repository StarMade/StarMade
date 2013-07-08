package org.schema.schine.network.client;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.schine.network.Pinger;

class ClientProcessor$SendingQueueThread
  extends Thread
{
  private int totalPackagesQueued;
  
  private ClientProcessor$SendingQueueThread(ClientProcessor paramClientProcessor) {}
  
  public void run()
  {
    try
    {
      while ((this.this$0.listening) && (!ClientProcessor.access$700(this.this$0).isClosed()))
      {
        setName("ClientSendingQueueThread(" + getId() + ")");
        synchronized (ClientProcessor.access$400(this.this$0))
        {
          while ((ClientProcessor.access$400(this.this$0).isEmpty()) && (this.this$0.listening))
          {
            ClientProcessor.access$100(this.this$0).getOutput().flush();
            ClientProcessor.access$400(this.this$0).wait(10000L);
            checkPingPong();
            if (!this.this$0.listening) {
              break;
            }
            if (ClientProcessor.access$400(this.this$0).size() > 700) {
              this.this$0.listening = false;
            }
          }
          if (!this.this$0.listening) {
            break;
          }
          FastByteArrayOutputStream localFastByteArrayOutputStream1 = (FastByteArrayOutputStream)ClientProcessor.access$400(this.this$0).remove(0);
          this.totalPackagesQueued -= 1;
        }
        checkPingPong();
        this.this$0.sendPacket(localFastByteArrayOutputStream2);
        ClientProcessor.releasePacket(localFastByteArrayOutputStream2);
        try
        {
          sleep(2L);
        }
        catch (InterruptedException localInterruptedException) {}
      }
      this.this$0.listening = false;
    }
    catch (Exception localException)
    {
      System.err.println("SENDING THREAD ENDED of " + this);
      localException.printStackTrace();
    }
    finally
    {
      this.this$0.listening = false;
    }
    synchronized (ClientProcessor.access$400(this.this$0))
    {
      ??? = ClientProcessor.access$400(this.this$0).iterator();
      while (((Iterator)???).hasNext())
      {
        ClientProcessor.releasePacket((FastByteArrayOutputStream)((Iterator)???).next());
        this.totalPackagesQueued -= 1;
      }
      ClientProcessor.access$400(this.this$0).clear();
      return;
    }
  }
  
  private void checkPingPong()
  {
    byte[] arrayOfByte;
    if (ClientProcessor.access$300(this.this$0))
    {
      arrayOfByte = Pinger.createPing();
      ClientProcessor.access$100(this.this$0).getOutput().writeInt(arrayOfByte.length);
      ClientProcessor.access$100(this.this$0).getOutput().write(arrayOfByte);
      ClientProcessor.access$302(this.this$0, false);
    }
    if (ClientProcessor.access$500(this.this$0))
    {
      arrayOfByte = Pinger.createPong();
      ClientProcessor.access$100(this.this$0).getOutput().writeInt(arrayOfByte.length);
      ClientProcessor.access$100(this.this$0).getOutput().write(arrayOfByte);
      ClientProcessor.access$502(this.this$0, false);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor.SendingQueueThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */