/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class ExecuteAdminCommand extends Command
/*    */ {
/*    */   public ExecuteAdminCommand()
/*    */   {
/* 66 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 79 */     String str = (String)paramArrayOfObject[0];
/* 80 */     paramArrayOfObject = (String)paramArrayOfObject[1];
/* 81 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramServerStateInterface.executeAdminCommand(str, paramArrayOfObject) });
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.ExecuteAdminCommand
 * JD-Core Version:    0.6.2
 */