package org.schema.game.network.commands;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.schema.schine.network.Command;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class KillCharacter
  extends Command
{
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramServerProcessor = ((Integer)???[0]).intValue();
    synchronized (paramServerStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      if (paramServerStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramServerProcessor)) {
        ((Sendable)paramServerStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramServerProcessor)).setMarkedForDeleteVolatile(true);
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.KillCharacter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */