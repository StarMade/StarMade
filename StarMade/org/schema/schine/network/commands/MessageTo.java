/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class MessageTo extends Command
/*    */ {
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 68 */     paramClientStateInterface.message("[" + paramArrayOfObject[0] + "]: " + paramArrayOfObject[1], (Integer)paramArrayOfObject[2]);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 74 */     String str = ((RegisteredClientOnServer)paramServerStateInterface.getClients().get(Integer.valueOf(paramServerProcessor.getClient().getId()))).getPlayerName();
/* 75 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { str, paramArrayOfObject });
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.MessageTo
 * JD-Core Version:    0.6.2
 */