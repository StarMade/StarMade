/*  1:   */package org.schema.schine.network.commands;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.PrintStream;
/*  5:   */import org.schema.schine.network.Command;
/*  6:   */import org.schema.schine.network.NetworkProcessor;
/*  7:   */import org.schema.schine.network.RegisteredClientOnServer;
/*  8:   */import org.schema.schine.network.client.ClientStateInterface;
/*  9:   */import org.schema.schine.network.server.ServerProcessor;
/* 10:   */import org.schema.schine.network.server.ServerStateInterface;
/* 11:   */import org.schema.schine.network.synchronization.SynchronizationReceiver;
/* 12:   */
/* 17:   */public class SynchronizePrivateChannel
/* 18:   */  extends Command
/* 19:   */{
/* 20:   */  public void clientAnswerProcess(Object[] arg1, ClientStateInterface paramClientStateInterface, short paramShort)
/* 21:   */  {
/* 22:22 */    synchronized (paramClientStateInterface) {
/* 23:23 */      if (paramClientStateInterface.isSynchronized())
/* 24:   */      {
/* 25:25 */        SynchronizationReceiver.update(paramClientStateInterface.getPrivateLocalAndRemoteObjectContainer(), 0, paramClientStateInterface.getProcessor().getIn(), paramClientStateInterface, paramClientStateInterface.isSynchronized(), false, paramShort);
/* 26:   */        
/* 27:27 */        paramClientStateInterface.setSynchronized(true);
/* 28:   */      }
/* 29:   */      else
/* 30:   */      {
/* 31:31 */        System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPING INPUT");
/* 32:   */        
/* 33:33 */        while (paramClientStateInterface.getProcessor().getIn().read() != -1) {}
/* 34:34 */        System.err.println("[SYNCHRONIZE] " + paramClientStateInterface + " IS WAITING TO SYNCH WITH SERVER - SKIPPED INPUT");
/* 35:   */      }
/* 36:   */      
/* 37:37 */      return;
/* 38:   */    }
/* 39:   */  }
/* 40:   */  
/* 45:   */  public void serverProcess(ServerProcessor paramServerProcessor, Object[] arg2, ServerStateInterface paramServerStateInterface, short paramShort)
/* 46:   */  {
/* 47:47 */    synchronized (paramServerStateInterface) {
/* 48:48 */      SynchronizationReceiver.update(paramServerProcessor.getClient().getLocalAndRemoteObjectContainer(), paramServerProcessor.getClient().getId(), paramServerProcessor.getIn(), paramServerStateInterface, true, false, paramShort);
/* 49:   */      
/* 50:50 */      return;
/* 51:   */    }
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.commands.SynchronizePrivateChannel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */