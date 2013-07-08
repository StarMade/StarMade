/*  1:   */package org.schema.game.network.commands;
/*  2:   */
/*  3:   */import org.schema.game.network.StarMadePlayerStats;
/*  4:   */import org.schema.game.server.controller.GameServerController;
/*  5:   */import org.schema.schine.network.Command;
/*  6:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  7:   */import org.schema.schine.network.client.ClientStateInterface;
/*  8:   */import org.schema.schine.network.server.ServerStateInterface;
/*  9:   */
/* 10:   */public class RequestPlayerStats extends Command
/* 11:   */{
/* 12:   */  public RequestPlayerStats()
/* 13:   */  {
/* 14:14 */    this.mode = 1;
/* 15:   */  }
/* 16:   */  
/* 18:   */  public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/* 19:   */  {
/* 20:20 */    paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/* 21:   */  }
/* 22:   */  
/* 25:   */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 26:   */  {
/* 27:   */    vg localvg;
/* 28:   */    
/* 30:30 */    if (!(localvg = (vg)paramServerStateInterface).a(paramServerProcessor.getClient().getPlayerName())) {
/* 31:31 */      localvg.a().sendLogout(paramServerProcessor.getClient().getId(), "Only Admins Allowed");return;
/* 32:   */    }
/* 33:33 */    createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, StarMadePlayerStats.encode(localvg, ((Integer)paramArrayOfObject[0]).intValue()));
/* 34:   */  }
/* 35:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestPlayerStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */