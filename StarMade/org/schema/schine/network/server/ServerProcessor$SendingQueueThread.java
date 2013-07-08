package org.schema.schine.network.server;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.schine.network.Pinger;

class ServerProcessor$SendingQueueThread
  extends Thread
{
  private ServerProcessor$SendingQueueThread(ServerProcessor paramServerProcessor) {}
  
  public void run()
  {
    try
    {
      while ((ServerProcessor.access$500(this.this$0)) && (!this.this$0.isStopTransmit()))
      {
        setName("SendingQueueThread(" + ServerProcessor.access$400(this.this$0) + ")");
        synchronized (ServerProcessor.access$800(this.this$0))
        {
          while ((ServerProcessor.access$800(this.this$0).isEmpty()) && (ServerProcessor.access$500(this.this$0)))
          {
            ServerProcessor.access$1000(this.this$0).flush();
            ServerProcessor.access$800(this.this$0).wait(10000L);
            checkPingPong();
            if ((!ServerProcessor.access$500(this.this$0)) || (this.this$0.isStopTransmit())) {
              break;
            }
            if (ServerProcessor.access$800(this.this$0).size() > 700) {
              ServerProcessor.access$502(this.this$0, false);
            }
          }
          if ((ServerProcessor.access$500(this.this$0)) && (this.this$0.isStopTransmit())) {
            break;
          }
          FastByteArrayOutputStream localFastByteArrayOutputStream1 = (FastByteArrayOutputStream)ServerProcessor.access$800(this.this$0).remove(0);
          ServerProcessor.totalPackagesQueued -= 1;
        }
        checkPingPong();
        this.this$0.sendPacket(localFastByteArrayOutputStream2);
        ServerProcessor.releasePacket(localFastByteArrayOutputStream2);
        if (ServerProcessor.access$1100(this.this$0)) {
          this.this$0.disconnect();
        }
        try
        {
          sleep(2L);
        }
        catch (InterruptedException localInterruptedException) {}
      }
      ServerProcessor.access$502(this.this$0, false);
    }
    catch (Exception localException)
    {
      System.err.println("SENDING THREAD ENDED of " + ServerProcessor.access$400(this.this$0));
      localException.printStackTrace();
    }
    finally
    {
      ServerProcessor.access$502(this.this$0, false);
    }
    synchronized (ServerProcessor.access$800(this.this$0))
    {
      ??? = ServerProcessor.access$800(this.this$0).iterator();
      while (((Iterator)???).hasNext())
      {
        ServerProcessor.releasePacket((FastByteArrayOutputStream)((Iterator)???).next());
        ServerProcessor.totalPackagesQueued -= 1;
      }
      ServerProcessor.access$800(this.this$0).clear();
      return;
    }
  }
  
  private void checkPingPong()
  {
    byte[] arrayOfByte;
    if (ServerProcessor.access$700(this.this$0))
    {
      arrayOfByte = Pinger.createPing();
      ServerProcessor.access$1000(this.this$0).writeInt(arrayOfByte.length);
      ServerProcessor.access$1000(this.this$0).writeLong(System.currentTimeMillis());
      ServerProcessor.access$1000(this.this$0).write(arrayOfByte);
      if (ServerProcessor.access$000(this.this$0).flushPingImmediately()) {
        ServerProcessor.access$1000(this.this$0).flush();
      }
      ServerProcessor.access$702(this.this$0, false);
    }
    if (ServerProcessor.access$900(this.this$0))
    {
      arrayOfByte = Pinger.createPong();
      ServerProcessor.access$1000(this.this$0).writeInt(arrayOfByte.length);
      ServerProcessor.access$1000(this.this$0).writeLong(System.currentTimeMillis());
      ServerProcessor.access$1000(this.this$0).write(arrayOfByte);
      if (ServerProcessor.access$000(this.this$0).flushPingImmediately()) {
        ServerProcessor.access$1000(this.this$0).flush();
      }
      ServerProcessor.access$902(this.this$0, false);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.SendingQueueThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */