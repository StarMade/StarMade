package org.schema.game.network.commands;

import class_1041;
import org.schema.game.network.StarMadePlayerStats;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.Command;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestPlayerStats
  extends Command
{
  public RequestPlayerStats()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    class_1041 localclass_1041;
    if (!(localclass_1041 = (class_1041)paramServerStateInterface).a68(paramServerProcessor.getClient().getPlayerName()))
    {
      localclass_1041.a59().sendLogout(paramServerProcessor.getClient().getId(), "Only Admins Allowed");
      return;
    }
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, StarMadePlayerStats.encode(localclass_1041, ((Integer)paramArrayOfObject[0]).intValue()));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestPlayerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */