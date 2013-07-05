/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.NetworkProcessor;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.exception.SynchronizationException;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*    */ 
/*    */ public class Synchronize extends Command
/*    */ {
/*    */   public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 23 */     synchronized (paramClientStateInterface) {
/* 24 */       if (paramClientStateInterface.isSynchronized()) {
/*    */         try {
/* 26 */           SynchronizationReceiver.update(paramClientStateInterface.getLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), false, paramShort);
/*    */ 
/* 28 */           paramClientStateInterface.setSynchronized(true);
/*    */         }
/*    */         catch (SynchronizationException localSynchronizationException)
/*    */         {
/* 34 */           localSynchronizationException.printStackTrace();
/*    */ 
/* 32 */           System.err.println("SCHEDULING RESYNCH FOR " + paramClientStateInterface);
/* 33 */           paramClientStateInterface.setSynchronized(false);
/*    */         }
/*    */       } else {
/* 36 */         System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPING INPUT");
/*    */ 
/* 38 */         while (paramClientStateInterface.getProcessor().getIn().read() != -1);
/* 39 */         System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPED INPUT");
/*    */       }
/*    */ 
/* 42 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 52 */     synchronized (paramServerStateInterface) {
/* 53 */       SynchronizationReceiver.update(paramServerStateInterface.getLocalAndRemoteObjectContainer(), paramServerProcessor.getClient().getId(), paramServerProcessor.getIn(), paramServerStateInterface, true, false, paramShort);
/*    */ 
/* 55 */       return;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.Synchronize
 * JD-Core Version:    0.6.2
 */