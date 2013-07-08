package org.schema.schine.network.udp;

import java.io.IOException;
import java.net.InetSocketAddress;

final class UDPProcessor$2
  implements Runnable
{
  public final void run()
  {
    try
    {
      UDPProcessor localUDPProcessor;
      (localUDPProcessor = new UDPProcessor(4244)).initialize();
      localUDPProcessor.getEndpoint(new InetSocketAddress(4242), true).send(new byte[13], 0, 1);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */