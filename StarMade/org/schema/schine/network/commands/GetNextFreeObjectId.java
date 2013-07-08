package org.schema.schine.network.commands;

import java.io.PrintStream;
import org.schema.schine.network.Command;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class GetNextFreeObjectId
  extends Command
{
  public GetNextFreeObjectId()
  {
    this.mode = 1;
  }
  
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
  {
    System.err.println("Client got new ID range " + paramArrayOfObject[0]);
    paramClientStateInterface.setIdStartRange(((Integer)paramArrayOfObject[0]).intValue());
  }
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramArrayOfObject = IdGen.getFreeObjectId(ClientState.NEW_ID_RANGE.intValue());
    System.err.println("SENDING new ID RANGE TO CLIENT " + paramArrayOfObject + "; packet " + paramShort);
    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(paramArrayOfObject) });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.commands.GetNextFreeObjectId
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */