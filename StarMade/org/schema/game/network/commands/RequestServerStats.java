/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import org.schema.game.network.StarMadeServerStats;
/*  4:   */import org.schema.game.server.controller.GameServerController;
/*  5:   */import org.schema.schine.network.Command;
/*  6:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  7:   */import org.schema.schine.network.server.ServerStateInterface;
/*  8:   */
/*  9:   */public class RequestServerStats extends Command
/* 10:   */{
/* 11:   */  public RequestServerStats()
/* 12:   */  {
/* 13:13 */    this.mode = 1;
/* 14:   */  }
/* 15:   */  
/* 17:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, org.schema.schine.network.client.ClientStateInterface paramClientStateInterface, short paramShort)
/* 18:   */  {
/* 19:19 */    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/* 20:   */  }
/* 21:   */  
/* 27:   */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 28:   */  {
/* 29:29 */    if (!(paramArrayOfObject = (vg)paramServerStateInterface).a(paramServerProcessor.getClient().getPlayerName())) {
/* 30:30 */      paramArrayOfObject.a().sendLogout(paramServerProcessor.getClient().getId(), "Only Admins Allowed");
/* 31:   */    }
/* 32:32 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, StarMadeServerStats.encode(paramArrayOfObject));
/* 33:   */  }
/* 34:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestServerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */