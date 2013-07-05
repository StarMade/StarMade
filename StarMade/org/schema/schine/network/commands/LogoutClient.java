/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.client.ClientControllerInterface;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class LogoutClient extends Command
/*    */ {
/*    */   public LogoutClient()
/*    */   {
/* 67 */     this.mode = 0;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 72 */     if (paramClientStateInterface.isReady()) {
/* 73 */       System.err.println("CLIENT RECEIVED LOGOUT COMMAND FROM SERVER");
/* 74 */       paramClientStateInterface.getController().logout((String)paramArrayOfObject[0]);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.LogoutClient
 * JD-Core Version:    0.6.2
 */