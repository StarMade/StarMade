/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import org.schema.game.network.StarMadePlayerStats;
/*    */ import org.schema.game.server.controller.GameServerController;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import vg;
/*    */ 
/*    */ public class RequestPlayerStats extends Command
/*    */ {
/*    */   public RequestPlayerStats()
/*    */   {
/* 14 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 20 */     paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/*    */     vg localvg;
/* 30 */     if (!(
/* 30 */       localvg = (vg)paramServerStateInterface)
/* 30 */       .a(paramServerProcessor.getClient().getPlayerName())) {
/* 31 */       localvg.a().sendLogout(paramServerProcessor.getClient().getId(), "Only Admins Allowed"); return;
/*    */     }
/* 33 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, StarMadePlayerStats.encode(localvg, ((Integer)paramArrayOfObject[0]).intValue()));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestPlayerStats
 * JD-Core Version:    0.6.2
 */