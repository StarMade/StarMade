package org.schema.game.network.commands;

import class_749;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestInventoriesUnblocked
  extends Command
{
  public RequestInventoriesUnblocked()
  {
    this.mode = 0;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramArrayOfObject = ((Integer)paramArrayOfObject[0]).intValue();
    try
    {
      paramServerProcessor = null;
      if ((paramServerProcessor = (class_749)paramServerProcessor.getClient().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramArrayOfObject)).a48() == null) {
        throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + paramArrayOfObject + ". This CAN happen, when the SegmentController for this SendableSegmentProvider was deleted and the PRIVATE sendable segment controller was still udpating");
      }
      paramServerProcessor.b3();
      return;
    }
    catch (Exception localException)
    {
      localException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestInventoriesUnblocked
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */