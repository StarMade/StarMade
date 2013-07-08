package org.schema.game.network.commands;

import class_1041;
import class_48;
import class_670;
import class_748;
import class_900;
import java.io.PrintStream;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.network.Command;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RequestGameMode
  extends Command
{
  public RequestGameMode()
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
    Object localObject;
    if ((localObject = paramServerProcessor.getClient()) != null)
    {
      localObject = (class_748)((RegisteredClientOnServer)localObject).getPlayerObject();
      localObject = paramArrayOfObject.a62().getSector(((class_748)localObject).a44());
      createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramArrayOfObject.a11().toString(), Integer.valueOf(((class_670)localObject).a3()), Integer.valueOf(((class_670)localObject).field_136.field_475), Integer.valueOf(((class_670)localObject).field_136.field_476), Integer.valueOf(((class_670)localObject).field_136.field_477), paramArrayOfObject.a51(), paramArrayOfObject.b5() });
      return;
    }
    System.err.println("[SERVER][REQUESTGAMEMODE] not sending gameMode to invalid client");
  }
  
  public void execute() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.RequestGameMode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */