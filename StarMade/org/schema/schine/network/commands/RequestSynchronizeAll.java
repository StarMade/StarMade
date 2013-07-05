/*    */ package org.schema.schine.network.commands;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.PrintStream;
/*    */ import org.schema.schine.network.Command;
/*    */ import org.schema.schine.network.Header;
/*    */ import org.schema.schine.network.NetworkProcessor;
/*    */ import org.schema.schine.network.NetworkStateContainer;
/*    */ import org.schema.schine.network.RegisteredClientOnServer;
/*    */ import org.schema.schine.network.StateInterface;
/*    */ import org.schema.schine.network.client.ClientStateInterface;
/*    */ import org.schema.schine.network.server.ServerProcessor;
/*    */ import org.schema.schine.network.server.ServerStateInterface;
/*    */ import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*    */ import org.schema.schine.network.synchronization.SynchronizationSender;
/*    */ 
/*    */ public class RequestSynchronizeAll extends Command
/*    */ {
/*    */   public static void executeSynchAll(StateInterface paramStateInterface, ServerProcessor paramServerProcessor)
/*    */   {
/* 16 */     long l = System.currentTimeMillis();
/* 17 */     System.err.println("[SERVER] sending ALLSYNCHRONIZING update to " + paramServerProcessor.getClient().getPlayerName() + " " + paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().size() + ", " + paramStateInterface.getLocalAndRemoteObjectContainer().getRemoteObjects().size());
/*    */ 
/* 19 */     new Header(RequestSynchronizeAll.class, paramStateInterface.getId(), paramServerProcessor.getClient().getId(), paramServerProcessor.getClient().getSynchPacketId(), (byte)123)
/* 22 */       .write(paramServerProcessor.getOut());
/*    */ 
/* 26 */     if (SynchronizationSender.encodeNetworkObjects(paramStateInterface.getLocalAndRemoteObjectContainer(), paramStateInterface, paramServerProcessor.getOut(), true) == 
/* 26 */       1) {
/* 27 */       paramServerProcessor.flushDoubleOutBuffer();
/*    */     } else {
/* 29 */       System.err.println("[SERVER][WARNING][NOTHING WRITTEN] ");
/* 30 */       paramServerProcessor.resetDoubleOutBuffer();
/*    */     }
/* 32 */     System.err.println("[SERVER] ALL-SYNCHRONIZE TO " + paramServerProcessor.getClient().getPlayerName() + " TOOK " + (System.currentTimeMillis() - l) + " ms");
/*    */   }
/*    */   public RequestSynchronizeAll() {
/* 35 */     this.mode = 1;
/*    */   }
/*    */ 
/*    */   public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
/*    */   {
/* 42 */     System.err.println("[CLIENT] " + paramClientStateInterface.getId() + " Sync all update received");
/* 43 */     synchronized (paramClientStateInterface) {
/* 44 */       SynchronizationReceiver.update(paramClientStateInterface.getLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), true, paramShort);
/*    */ 
/* 47 */       paramClientStateInterface.setSynchronized(true);
/*    */     }
/*    */ 
/* 50 */     System.out.println("[CLIENT] " + paramClientStateInterface.getId() + " executed RequestSynchronizeAll. fetched objects " + paramClientStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().size());
/*    */   }
/*    */ 
/*    */   public void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/*    */   {
/* 57 */     paramServerProcessor.getClient().flagSynch(paramShort);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.RequestSynchronizeAll
 * JD-Core Version:    0.6.2
 */