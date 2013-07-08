/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  4:   */import org.schema.schine.network.Command;
/*  5:   */import org.schema.schine.network.Header;
/*  6:   */import org.schema.schine.network.NetworkProcessor;
/*  7:   */import org.schema.schine.network.NetworkStateContainer;
/*  8:   */import org.schema.schine.network.server.ServerStateInterface;
/*  9:   */import org.schema.schine.network.synchronization.SynchronizationReceiver;
/* 10:   */import org.schema.schine.network.synchronization.SynchronizationSender;
/* 11:   */
/* 12:   */public class RequestSynchronizeAll extends Command
/* 13:   */{
/* 14:   */  public static void executeSynchAll(org.schema.schine.network.StateInterface paramStateInterface, org.schema.schine.network.server.ServerProcessor paramServerProcessor)
/* 15:   */  {
/* 16:16 */    long l = System.currentTimeMillis();
/* 17:17 */    System.err.println("[SERVER] sending ALLSYNCHRONIZING update to " + paramServerProcessor.getClient().getPlayerName() + " " + paramStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().size() + ", " + paramStateInterface.getLocalAndRemoteObjectContainer().getRemoteObjects().size());
/* 18:   */    
/* 19:19 */    new Header(RequestSynchronizeAll.class, paramStateInterface.getId(), paramServerProcessor.getClient().getId(), paramServerProcessor.getClient().getSynchPacketId(), (byte)123)
/* 20:   */    
/* 22:22 */      .write(paramServerProcessor.getOut());
/* 23:   */    
/* 26:26 */    if (SynchronizationSender.encodeNetworkObjects(paramStateInterface.getLocalAndRemoteObjectContainer(), paramStateInterface, paramServerProcessor.getOut(), true) == 1) {
/* 27:27 */      paramServerProcessor.flushDoubleOutBuffer();
/* 28:   */    } else {
/* 29:29 */      System.err.println("[SERVER][WARNING][NOTHING WRITTEN] ");
/* 30:30 */      paramServerProcessor.resetDoubleOutBuffer();
/* 31:   */    }
/* 32:32 */    System.err.println("[SERVER] ALL-SYNCHRONIZE TO " + paramServerProcessor.getClient().getPlayerName() + " TOOK " + (System.currentTimeMillis() - l) + " ms");
/* 33:   */  }
/* 34:   */  
/* 35:35 */  public RequestSynchronizeAll() { this.mode = 1; }
/* 36:   */  
/* 40:   */  public void clientAnswerProcess(Object[] arg1, org.schema.schine.network.client.ClientStateInterface paramClientStateInterface, short paramShort)
/* 41:   */  {
/* 42:42 */    System.err.println("[CLIENT] " + paramClientStateInterface.getId() + " Sync all update received");
/* 43:43 */    synchronized (paramClientStateInterface) {
/* 44:44 */      SynchronizationReceiver.update(paramClientStateInterface.getLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), true, paramShort);
/* 45:   */      
/* 47:47 */      paramClientStateInterface.setSynchronized(true);
/* 48:   */    }
/* 49:   */    
/* 50:50 */    System.out.println("[CLIENT] " + paramClientStateInterface.getId() + " executed RequestSynchronizeAll. fetched objects " + paramClientStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().size());
/* 51:   */  }
/* 52:   */  
/* 55:   */  public void serverProcess(org.schema.schine.network.server.ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort)
/* 56:   */  {
/* 57:57 */    paramServerProcessor.getClient().flagSynch(paramShort);
/* 58:   */  }
/* 59:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.RequestSynchronizeAll
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */