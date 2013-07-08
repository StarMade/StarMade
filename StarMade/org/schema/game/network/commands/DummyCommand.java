package org.schema.game.network.commands;

import org.schema.schine.network.Command;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class DummyCommand
  extends Command
{
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.DummyCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */