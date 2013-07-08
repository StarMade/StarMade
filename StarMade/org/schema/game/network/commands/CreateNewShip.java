/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */import kd;
/*  6:   */import ki;
/*  7:   */import kn;
/*  8:   */import org.schema.schine.network.Command;
/*  9:   */import org.schema.schine.network.RegisteredClientOnServer;
/* 10:   */import org.schema.schine.network.client.ClientStateInterface;
/* 11:   */import org.schema.schine.network.server.ServerProcessor;
/* 12:   */import org.schema.schine.network.server.ServerStateInterface;
/* 13:   */import ve;
/* 14:   */import vg;
/* 15:   */
/* 21:   */public class CreateNewShip
/* 22:   */  extends Command
/* 23:   */{
/* 24:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 25:   */  
/* 26:   */  public void serverProcess(ServerProcessor arg1, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 27:   */  {
/* 28:28 */    paramShort = null;
/* 29:   */    
/* 30:30 */    if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Ship")) {
/* 31:31 */      paramShort = new ve(???.getClient().getId(), paramArrayOfObject, kd.class);
/* 32:   */    }
/* 33:33 */    if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Station")) {
/* 34:34 */      paramShort = new ve(???.getClient().getId(), paramArrayOfObject, ki.class);
/* 35:   */    }
/* 36:36 */    if (paramArrayOfObject[(paramArrayOfObject.length - 1)].equals("Vehicle")) {
/* 37:37 */      System.err.println("REQUESTING ON SERVER VEHICLE");
/* 38:38 */      paramShort = new ve(???.getClient().getId(), paramArrayOfObject, kn.class);
/* 39:   */    }
/* 40:40 */    assert (paramShort != null);
/* 41:41 */    synchronized (((vg)paramServerStateInterface).c()) {
/* 42:42 */      ((vg)paramServerStateInterface).c().add(paramShort); return;
/* 43:   */    }
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.CreateNewShip
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */