package org.schema.game.network.commands;

import class_1041;
import class_1070;
import java.io.PrintStream;
import java.util.ArrayList;
import kd;
import ki;
import kn;
import org.schema.schine.network.Command;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class CreateNewShip
  extends Command
{
  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
  
  public void serverProcess(ServerProcessor arg1, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
  {
    paramShort = null;
    if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Ship")) {
      paramShort = new class_1070(???.getClient().getId(), paramArrayOfObject, kd.class);
    }
    if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Station")) {
      paramShort = new class_1070(???.getClient().getId(), paramArrayOfObject, ki.class);
    }
    if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Vehicle"))
    {
      System.err.println("REQUESTING ON SERVER VEHICLE");
      paramShort = new class_1070(???.getClient().getId(), paramArrayOfObject, kn.class);
    }
    assert (paramShort != null);
    synchronized (((class_1041)paramServerStateInterface).c())
    {
      ((class_1041)paramServerStateInterface).c().add(paramShort);
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.network.commands.CreateNewShip
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */