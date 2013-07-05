/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.NetworkProcessor;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class RequestServerTime extends Command
/*    */ {
/*    */   private long started;
/*    */ 
/*    */   public RequestServerTime()
/*    */   {
/* 67 */     this.mode = 0;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 74 */     long l = System.currentTimeMillis() - 
/* 74 */       this.started;
/*    */ 
/* 76 */     paramClientStateInterface.setServerTimeOnLogin(((Long)paramArrayOfObject[0]).longValue() + l / 2L);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 83 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Long.valueOf(System.currentTimeMillis()) });
/*    */   }
/*    */ 
/*    */   public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
/*    */   {
/* 89 */     this.started = System.currentTimeMillis();
/* 90 */     super.writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramShort, paramNetworkProcessor);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.RequestServerTime
 * JD-Core Version:    0.6.2
 */