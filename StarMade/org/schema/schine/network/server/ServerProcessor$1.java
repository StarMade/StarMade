package org.schema.schine.network.server;

class ServerProcessor$1
  extends Thread
{
  ServerProcessor$1(ServerProcessor paramServerProcessor) {}
  
  public void run()
  {
    try
    {
      this.this$0.setStopTransmit(true);
      synchronized (ServerProcessor.access$800(this.this$0))
      {
        ServerProcessor.access$800(this.this$0).notify();
      }
      Thread.sleep(3000L);
    }
    catch (InterruptedException localInterruptedException)
    {
      ??? = null;
      localInterruptedException.printStackTrace();
    }
    this.this$0.disconnect();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */