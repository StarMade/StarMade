/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import xu;
/*    */ 
/*    */ public class Pause extends Command
/*    */ {
/*    */   public Pause()
/*    */   {
/* 66 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 71 */     xu.B.a(((Boolean)paramArrayOfObject[0]).booleanValue());
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 77 */     paramArrayOfObject = ((Boolean)paramArrayOfObject[0]).booleanValue();
/* 78 */     paramServerStateInterface.setPaused(paramArrayOfObject);
/* 79 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { Boolean.valueOf(paramArrayOfObject) });
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.Pause
 * JD-Core Version:    0.6.2
 */