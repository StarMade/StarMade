/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  6:   */import org.schema.schine.network.client.ClientStateInterface;
/*  7:   */import org.schema.schine.network.server.ServerProcessor;
/*  8:   */import org.schema.schine.network.server.ServerStateInterface;
/*  9:   */
/* 63:   */public class MessageTo
/* 64:   */  extends Command
/* 65:   */{
/* 66:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 67:   */  {
/* 68:68 */    paramClientStateInterface.message("[" + paramArrayOfObject[0] + "]: " + paramArrayOfObject[1], (Integer)paramArrayOfObject[2]);
/* 69:   */  }
/* 70:   */  
/* 72:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 73:   */  {
/* 74:74 */    String str = ((RegisteredClientOnServer)paramServerStateInterface.getClients().get(Integer.valueOf(paramServerProcessor.getClient().getId()))).getPlayerName();
/* 75:75 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { str, paramArrayOfObject });
/* 76:   */  }
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.MessageTo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */