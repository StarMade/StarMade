/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.NetworkProcessor;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*    */ 
/*    */ public class SynchronizePrivateChannel extends Command
/*    */ {
/*    */   public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 22 */     synchronized (paramClientStateInterface) {
/* 23 */       if (paramClientStateInterface.isSynchronized())
/*    */       {
/* 25 */         SynchronizationReceiver.update(paramClientStateInterface.getPrivateLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), false, paramShort);
/*    */ 
/* 27 */         paramClientStateInterface.setSynchronized(true);
/*    */       }
/*    */       else
/*    */       {
/* 31 */         System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPING INPUT");
/*    */ 
/* 33 */         while (paramClientStateInterface.getProcessor().getIn().read() != -1);
/* 34 */         System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPED INPUT");
/*    */       }
/*    */ 
/* 37 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 47 */     synchronized (paramServerStateInterface) {
/* 48 */       SynchronizationReceiver.update(paramServerProcessor.getClient().getLocalAndRemoteObjectContainer(), paramServerProcessor.getClient().getId(), paramServerProcessor.getIn(), paramServerStateInterface, true, false, paramShort);
/*    */ 
/* 50 */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.SynchronizePrivateChannel
 * JD-Core Version:    0.6.2
 */