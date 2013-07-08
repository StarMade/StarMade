/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.client.ClientControllerInterface;
/*  6:   */import org.schema.schine.network.client.ClientStateInterface;
/*  7:   */import org.schema.schine.network.server.ServerProcessor;
/*  8:   */import org.schema.schine.network.server.ServerStateInterface;
/*  9:   */
/* 62:   */public class LogoutClient
/* 63:   */  extends Command
/* 64:   */{
/* 65:   */  public LogoutClient()
/* 66:   */  {
/* 67:67 */    this.mode = 0;
/* 68:   */  }
/* 69:   */  
/* 70:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 71:   */  {
/* 72:72 */    if (paramClientStateInterface.isReady()) {
/* 73:73 */      System.err.println("CLIENT RECEIVED LOGOUT COMMAND FROM SERVER");
/* 74:74 */      paramClientStateInterface.getController().logout((String)paramArrayOfObject[0]);
/* 75:   */    }
/* 76:   */  }
/* 77:   */  
/* 78:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort) {}
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.LogoutClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */