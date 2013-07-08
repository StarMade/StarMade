package org.schema.schine.network.commands;

import java.util.HashMap;
import org.schema.schine.network.Command;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class MessageTo
  extends Command
{
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramClientStateInterface.message("[" + paramArrayOfObject[0] + "]: " + paramArrayOfObject[1], (Integer)paramArrayOfObject[2]);
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    String str = ((RegisteredClientOnServer)paramServerStateInterface.getClients().get(Integer.valueOf(paramServerProcessor.getClient().getId()))).getPlayerName();
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { str, paramArrayOfObject });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.MessageTo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */