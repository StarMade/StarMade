/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import org.schema.schine.network.Command;
/*  4:   */import org.schema.schine.network.client.ClientStateInterface;
/*  5:   */import org.schema.schine.network.server.ServerProcessor;
/*  6:   */import org.schema.schine.network.server.ServerStateInterface;
/*  7:   */
/* 61:   */public class ExecuteAdminCommand
/* 62:   */  extends Command
/* 63:   */{
/* 64:   */  public ExecuteAdminCommand()
/* 65:   */  {
/* 66:66 */    this.mode = 1;
/* 67:   */  }
/* 68:   */  
/* 72:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort) {}
/* 73:   */  
/* 77:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 78:   */  {
/* 79:79 */    String str = (String)paramArrayOfObject[0];
/* 80:80 */    paramArrayOfObject = (String)paramArrayOfObject[1];
/* 81:81 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramServerStateInterface.executeAdminCommand(str, paramArrayOfObject) });
/* 82:   */  }
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.ExecuteAdminCommand
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */