package org.schema.game.network.commands;

import class_1041;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.Command;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class AdminCommand
  extends Command
{
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    if (((class_1041)paramServerStateInterface).a59().isAdmin(paramServerProcessor.getClient()))
    {
      paramServerProcessor = paramServerProcessor.getClient();
      paramShort = (Integer)paramArrayOfObject[0];
      paramShort = org.schema.game.server.data.admin.AdminCommands.values()[paramShort.intValue()];
      Object[] arrayOfObject = new Object[paramArrayOfObject.length - 1];
      for (int i = 0; i < arrayOfObject.length; i++) {
        arrayOfObject[i] = paramArrayOfObject[(i + 1)];
      }
      ((class_1041)paramServerStateInterface).a59().a40(paramServerProcessor, paramShort, arrayOfObject);
      return;
    }
    paramServerProcessor.getClient().serverMessage("[ADMIN COMMAND] [ERROR] YOU ARE NOT AN ADMIN!");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.AdminCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */