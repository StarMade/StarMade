/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import vg;
/*    */ 
/*    */ public class RequestBlockProperties extends Command
/*    */ {
/*    */   public RequestBlockProperties()
/*    */   {
/* 15 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 21 */     paramClientStateInterface.arrivedReturn(paramShort, paramArrayOfObject);
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 29 */     paramArrayOfObject = (vg)paramServerStateInterface;
/*    */ 
/* 33 */     createReturnToClient(paramServerStateInterface, paramServerProcessor, paramShort, new Object[] { paramArrayOfObject.b() });
/*    */   }
/*    */ 
/*    */   public void execute()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestBlockProperties
 * JD-Core Version:    0.6.2
 */