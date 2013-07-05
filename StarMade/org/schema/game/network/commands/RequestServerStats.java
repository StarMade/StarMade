/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import org.schema.game.network.StarMadeServerStats;
/*    */ import org.schema.game.server.controller.GameServerController;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import vg;
/*    */ 
/*    */ public class RequestServerStats extends Command
/*    */ {
/*    */   public RequestServerStats()
/*    */   {
/* 13 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 19 */     paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 29 */     if (!(
/* 29 */       paramArrayOfObject = (vg)paramServerStateInterface)
/* 29 */       .a(paramServerProcessor.getClient().getPlayerName())) {
/* 30 */       paramArrayOfObject.a().sendLogout(paramServerProcessor.getClient().getId(), "Only Admins Allowed");
/*    */     }
/* 32 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, StarMadeServerStats.encode(paramArrayOfObject));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestServerStats
 * JD-Core Version:    0.6.2
 */