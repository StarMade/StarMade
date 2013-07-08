package org.schema.schine.network.commands;

import org.schema.schine.network.Command;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class ExecuteAdminCommand
  extends Command
{
  public ExecuteAdminCommand()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    String str = (String)paramArrayOfObject[0];
    paramArrayOfObject = (String)paramArrayOfObject[1];
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramServerStateInterface.executeAdminCommand(str, paramArrayOfObject) });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.ExecuteAdminCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */