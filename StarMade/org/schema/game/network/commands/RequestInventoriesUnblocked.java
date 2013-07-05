/*    */ package org.schema.game.network.commands;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import kc;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ 
/*    */ public class RequestInventoriesUnblocked extends Command
/*    */ {
/*    */   public RequestInventoriesUnblocked()
/*    */   {
/* 16 */     this.mode = 0;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 29 */     paramArrayOfObject = ((Integer)paramArrayOfObject[0]).intValue();
/*    */     try
/*    */     {
/* 38 */       paramServerProcessor = null;
/*    */ 
/* 41 */       if ((
/* 39 */         paramServerProcessor = (kc)paramServerProcessor.getClient()
/* 38 */         .getLocalAndRemoteObjectContainer().getLocalObjects().get(paramArrayOfObject))
/* 39 */         .a() == null)
/*    */       {
/* 42 */         throw new IllegalArgumentException("[SERVER] Could NOT find the segment controller ID " + paramArrayOfObject + ". This CAN happen, when the SegmentController for this SendableSegmentProvider was deleted and the PRIVATE sendable segment controller was still udpating");
/* 44 */       }paramServerProcessor.b();
/*    */       return;
/*    */     } catch (Exception localException) {
/* 47 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.commands.RequestInventoriesUnblocked
 * JD-Core Version:    0.6.2
 */