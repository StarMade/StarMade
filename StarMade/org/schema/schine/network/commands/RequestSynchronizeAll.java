package org.schema.schine.network.commands;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import org.schema.schine.network.Command;
import org.schema.schine.network.Header;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;
import org.schema.schine.network.synchronization.SynchronizationReceiver;
import org.schema.schine.network.synchronization.SynchronizationSender;

public class RequestSynchronizeAll
  extends Command
{
  public static void executeSynchAll(StateInterface paramStateInterface, ServerProcessor paramServerProcessor)
  {
    long l = System.currentTimeMillis();
    System.err.println("[SERVER] sending ALLSYNCHRONIZING update to " + paramServerProcessor.getClient().getPlayerName() + " " + paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().size() + ", " + paramStateInterface.getLocalAndRemoteObjectContainer().getRemoteObjects().size());
    new Header(RequestSynchronizeAll.class, paramStateInterface.getId(), paramServerProcessor.getClient().getId(), paramServerProcessor.getClient().getSynchPacketId(), (byte)123).write(paramServerProcessor.getOut());
    if (SynchronizationSender.encodeNetworkObjects(paramStateInterface.getLocalAndRemoteObjectContainer(), paramStateInterface, paramServerProcessor.getOut(), true) == 1)
    {
      paramServerProcessor.flushDoubleOutBuffer();
    }
    else
    {
      System.err.println("[SERVER][WARNING][NOTHING WRITTEN] ");
      paramServerProcessor.resetDoubleOutBuffer();
    }
    System.err.println("[SERVER] ALL-SYNCHRONIZE TO " + paramServerProcessor.getClient().getPlayerName() + " TOOK " + (System.currentTimeMillis() - l) + " ms");
  }
  
  public RequestSynchronizeAll()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    System.err.println("[CLIENT] " + paramClientStateInterface.getId() + " Sync all update received");
    synchronized (paramClientStateInterface)
    {
      SynchronizationReceiver.update(paramClientStateInterface.getLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), true, paramShort);
      paramClientStateInterface.setSynchronized(true);
    }
    System.out.println("[CLIENT] " + paramClientStateInterface.getId() + " executed RequestSynchronizeAll. fetched objects " + paramClientStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().size());
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramServerProcessor.getClient().flagSynch(paramShort);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.RequestSynchronizeAll
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */