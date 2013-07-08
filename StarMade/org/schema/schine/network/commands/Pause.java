package org.schema.schine.network.commands;

import class_949;
import org.schema.schine.network.Command;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class Pause
  extends Command
{
  public Pause()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    class_949.field_1214.a3(((Boolean)paramArrayOfObject[0]).booleanValue());
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramArrayOfObject = ((Boolean)paramArrayOfObject[0]).booleanValue();
    paramServerStateInterface.setPaused(paramArrayOfObject);
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Boolean.valueOf(paramArrayOfObject) });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.Pause
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */