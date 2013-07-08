package org.schema.schine.network.udp;

import java.io.IOException;
import java.io.PrintStream;

final class UDPProcessor$1
  implements Runnable
{
  public final void run()
  {
    try
    {
      UDPProcessor localUDPProcessor;
      (localUDPProcessor = new UDPProcessor(4242)).initialize();
      for (;;)
      {
        System.err.println("Broadcasting");
        localUDPProcessor.broadcast(new byte[] { 10 }, 0, 1);
        try
        {
          Thread.sleep(1000L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException;
        }
      }
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */