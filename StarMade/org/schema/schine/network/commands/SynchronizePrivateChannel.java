package org.schema.schine.network.commands;

import java.io.DataInputStream;
import java.io.PrintStream;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;
import org.schema.schine.network.synchronization.SynchronizationReceiver;

public class SynchronizePrivateChannel
  extends Command
{
  public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    synchronized (paramClientStateInterface)
    {
      if (paramClientStateInterface.isSynchronized())
      {
        SynchronizationReceiver.update(paramClientStateInterface.getPrivateLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), false, paramShort);
        paramClientStateInterface.setSynchronized(true);
      }
      else
      {
        System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPING INPUT");
        while (paramClientStateInterface.getProcessor().getIn().read() != -1) {}
        System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPED INPUT");
      }
      return;
    }
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    synchronized (paramServerStateInterface)
    {
      SynchronizationReceiver.update(paramServerProcessor.getClient().getLocalAndRemoteObjectContainer(), paramServerProcessor.getClient().getId(), paramServerProcessor.getIn(), paramServerStateInterface, true, false, paramShort);
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.SynchronizePrivateChannel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */