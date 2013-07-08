package org.schema.schine.network;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class MultiBufferedOutputStream
  extends BufferedOutputStream
{
  private ServerStateInterface state;
  
  public MultiBufferedOutputStream(ServerStateInterface paramServerStateInterface)
  {
    super(null);
    this.state = paramServerStateInterface;
  }
  
  public synchronized void flush()
  {
    Iterator localIterator = this.state.getClients().values().iterator();
    while (localIterator.hasNext()) {
      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().flush();
    }
  }
  
  public void write(byte[] paramArrayOfByte)
  {
    Iterator localIterator = this.state.getClients().values().iterator();
    while (localIterator.hasNext()) {
      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().write(paramArrayOfByte);
    }
  }
  
  public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.state.getClients().values().iterator();
    while (localIterator.hasNext()) {
      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().write(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
  
  public synchronized void write(int paramInt)
  {
    Iterator localIterator = this.state.getClients().values().iterator();
    while (localIterator.hasNext()) {
      ((RegisteredClientOnServer)localIterator.next()).getProcessor().getOut().write(paramInt);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.MultiBufferedOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */