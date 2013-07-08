/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.IdGen;
/*  6:   */import org.schema.schine.network.client.ClientState;
/*  7:   */import org.schema.schine.network.client.ClientStateInterface;
/*  8:   */import org.schema.schine.network.server.ServerProcessor;
/*  9:   */import org.schema.schine.network.server.ServerStateInterface;
/* 10:   */
/* 11:   */public class GetNextFreeObjectId
/* 12:   */  extends Command
/* 13:   */{
/* 14:   */  public GetNextFreeObjectId()
/* 15:   */  {
/* 16:16 */    this.mode = 1;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 20:   */  {
/* 21:21 */    System.err.println("Client got new ID range " + paramArrayOfObject[0]);
/* 22:22 */    paramClientStateInterface.setIdStartRange(((Integer)paramArrayOfObject[0]).intValue());
/* 23:   */  }
/* 24:   */  
/* 27:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 28:   */  {
/* 29:29 */    paramArrayOfObject = IdGen.getFreeObjectId(ClientState.NEW_ID_RANGE.intValue());
/* 30:30 */    System.err.println("SENDING new ID RANGE TO CLIENT " + paramArrayOfObject + "; packet " + paramShort);
/* 31:31 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Integer.valueOf(paramArrayOfObject) });
/* 32:   */  }
/* 33:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.GetNextFreeObjectId
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */