package org.schema.schine.network.server;

import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;

public class ServerEntityWriterThread
  extends Thread
{
  private final ObjectArrayFIFOQueue queue = new ObjectArrayFIFOQueue();
  
  public ServerEntityWriterThread()
  {
    super("ServerEntityWriterThread");
  }
  
  public void run()
  {
    for (;;)
    {
      synchronized (this.queue)
      {
        if (this.queue.isEmpty())
        {
          try
          {
            this.queue.wait();
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException;
          }
          continue;
        }
        Runnable localRunnable = (Runnable)this.queue.dequeue();
      }
      localObject1.run();
    }
  }
  
  public void enqueue(Runnable paramRunnable)
  {
    synchronized (this.queue)
    {
      this.queue.enqueue(paramRunnable);
      this.queue.notify();
      return;
    }
  }
  
  public int getActiveCount()
  {
    return this.queue.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerEntityWriterThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */