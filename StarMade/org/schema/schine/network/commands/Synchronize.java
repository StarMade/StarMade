/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.PrintStream;
/*  5:   */import org.schema.schine.network.Command;
/*  6:   */import org.schema.schine.network.NetworkProcessor;
/*  7:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  8:   */import org.schema.schine.network.client.ClientStateInterface;
/*  9:   */import org.schema.schine.network.exception.SynchronizationException;
/* 10:   */import org.schema.schine.network.server.ServerProcessor;
/* 11:   */import org.schema.schine.network.server.ServerStateInterface;
/* 12:   */import org.schema.schine.network.synchronization.SynchronizationReceiver;
/* 13:   */
/* 18:   */public class Synchronize
/* 19:   */  extends Command
/* 20:   */{
/* 21:   */  public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
/* 22:   */  {
/* 23:23 */    synchronized (paramClientStateInterface) {
/* 24:24 */      if (paramClientStateInterface.isSynchronized()) {
/* 25:   */        try {
/* 26:26 */          SynchronizationReceiver.update(paramClientStateInterface.getLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), false, paramShort);
/* 27:   */          
/* 28:28 */          paramClientStateInterface.setSynchronized(true);
/* 29:   */        } catch (SynchronizationException localSynchronizationException) {
/* 30:30 */          
/* 31:   */          
/* 34:34 */            localSynchronizationException.printStackTrace();System.err.println("SCHEDULING RESYNCH FOR " + paramClientStateInterface);paramClientStateInterface.setSynchronized(false);
/* 35:   */        }
/* 36:36 */      } else { System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPING INPUT");
/* 37:   */        
/* 38:38 */        while (paramClientStateInterface.getProcessor().getIn().read() != -1) {}
/* 39:39 */        System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPED INPUT");
/* 40:   */      }
/* 41:   */      
/* 42:42 */      return;
/* 43:   */    }
/* 44:   */  }
/* 45:   */  
/* 50:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
/* 51:   */  {
/* 52:52 */    synchronized (paramServerStateInterface) {
/* 53:53 */      SynchronizationReceiver.update(paramServerStateInterface.getLocalAndRemoteObjectContainer(), paramServerProcessor.getClient().getId(), paramServerProcessor.getIn(), paramServerStateInterface, true, false, paramShort);
/* 54:   */      
/* 55:55 */      return;
/* 56:   */    }
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.Synchronize
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */