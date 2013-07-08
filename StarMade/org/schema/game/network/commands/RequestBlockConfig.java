package org.schema.game.network.commands;

import class_1041;
import org.schema.schine.network.Command;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestBlockConfig
  extends Command
{
  public RequestBlockConfig()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramArrayOfObject = (class_1041)paramServerStateInterface;
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramArrayOfObject.a74() });
  }
  
  public void execute() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestBlockConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */