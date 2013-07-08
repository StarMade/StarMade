package org.schema.schine.network.commands;

import java.io.PrintStream;
import org.schema.schine.network.Command;
import org.schema.schine.network.client.ClientControllerInterface;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class LogoutClient
  extends Command
{
  public LogoutClient()
  {
    this.mode = 0;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    if (paramClientStateInterface.isReady())
    {
      System.err.println("CLIENT RECEIVED LOGOUT COMMAND FROM SERVER");
      paramClientStateInterface.getController().logout((String)paramArrayOfObject[0]);
    }
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.LogoutClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */