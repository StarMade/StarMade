/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import org.schema.game.server.controller.GameServerController;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import vg;
/*    */ 
/*    */ public class AdminCommand extends Command
/*    */ {
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 28 */     if (((vg)paramServerStateInterface).a().isAdmin(paramServerProcessor.getClient()))
/*    */     {
/* 30 */       paramServerProcessor = paramServerProcessor.getClient();
/* 31 */       paramShort = (Integer)paramArrayOfObject[0];
/* 32 */       paramShort = org.schema.game.server.data.admin.AdminCommands.values()[paramShort.intValue()];
/* 33 */       Object[] arrayOfObject = new Object[paramArrayOfObject.length - 1];
/*    */ 
/* 35 */       for (int i = 0; i < arrayOfObject.length; i++) {
/* 36 */         arrayOfObject[i] = paramArrayOfObject[(i + 1)];
/*    */       }
/* 38 */       ((vg)paramServerStateInterface).a().a(paramServerProcessor, paramShort, arrayOfObject);
/* 39 */       return;
/* 40 */     }paramServerProcessor.getClient().serverMessage("[ADMIN COMMAND] [ERROR] YOU ARE NOT AN ADMIN!");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.AdminCommand
 * JD-Core Version:    0.6.2
 */