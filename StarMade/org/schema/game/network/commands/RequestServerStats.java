package org.schema.game.network.commands;

import class_1041;
import org.schema.game.network.StarMadeServerStats;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.Command;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestServerStats
  extends Command
{
  public RequestServerStats()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    if (!(paramArrayOfObject = (class_1041)paramServerStateInterface).a68(paramServerProcessor.getClient().getPlayerName())) {
      paramArrayOfObject.a59().sendLogout(paramServerProcessor.getClient().getId(), "Only Admins Allowed");
    }
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, StarMadeServerStats.encode(paramArrayOfObject));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestServerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */