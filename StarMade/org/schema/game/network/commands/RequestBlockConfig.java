/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import org.schema.schine.network.Command;
/*  4:   */import org.schema.schine.network.client.ClientStateInterface;
/*  5:   */import org.schema.schine.network.server.ServerProcessor;
/*  6:   */import org.schema.schine.network.server.ServerStateInterface;
/*  7:   */import vg;
/*  8:   */
/* 10:   */public class RequestBlockConfig
/* 11:   */  extends Command
/* 12:   */{
/* 13:   */  public RequestBlockConfig()
/* 14:   */  {
/* 15:15 */    this.mode = 1;
/* 16:   */  }
/* 17:   */  
/* 19:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 20:   */  {
/* 21:21 */    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/* 22:   */  }
/* 23:   */  
/* 27:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 28:   */  {
/* 29:29 */    paramArrayOfObject = (vg)paramServerStateInterface;
/* 30:   */    
/* 33:33 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramArrayOfObject.a() });
/* 34:   */  }
/* 35:   */  
/* 36:   */  public void execute() {}
/* 37:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestBlockConfig
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */