package org.schema.schine.network.server;

import class_941;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import org.schema.schine.network.Command;
import org.schema.schine.network.CommandMap;
import org.schema.schine.network.Header;
import org.schema.schine.network.NetUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.commands.LogoutClient;
import org.schema.schine.network.commands.RequestSynchronizeAll;
import org.schema.schine.network.commands.Synchronize;
import org.schema.schine.network.commands.SynchronizePrivateChannel;
import org.schema.schine.network.synchronization.SynchronizationReceiver;
import org.schema.schine.network.synchronization.SynchronizationSender;

public abstract class ServerController
  extends Observable
  implements Runnable, Observer, ServerControllerInterface
{
  private ServerState serverState;
  private ServerListener serverListener;
  private class_941 timer = new class_941();
  private DataOutputStream dataOut;
  private final IntOpenHashSet delHelper = new IntOpenHashSet();
  public static int port = 4242;
  private ByteArrayOutputStream byteOutPut = new ByteArrayOutputStream(1048576);
  protected HashSet toRemoveClients = new HashSet();
  
  public ServerController(ServerState paramServerState)
  {
    setServerState(paramServerState);
    paramServerState.setController(this);
    this.dataOut = new DataOutputStream(this.byteOutPut);
    Runtime.getRuntime().addShutdownHook(new ServerController.1(this));
  }
  
  public void broadcastMessage(String paramString, int paramInt)
  {
    synchronized (this.serverState.getToBroadCastMessages())
    {
      this.serverState.getToBroadCastMessages().add(new ServerMessage(paramString, paramInt));
      return;
    }
  }
  
  public void checkIfClientsNeedResynch(Set paramSet)
  {
    paramSet = paramSet.iterator();
    while (paramSet.hasNext()) {
      synchronized ((localRegisteredClientOnServer = (RegisteredClientOnServer)paramSet.next()).getProcessor().getLock())
      {
        RegisteredClientOnServer localRegisteredClientOnServer;
        if (localRegisteredClientOnServer.getProcessor().isAlive()) {
          try
          {
            if (localRegisteredClientOnServer.needsSynch())
            {
              long l = System.currentTimeMillis();
              System.err.println("[SERVER] (client needs synch) SENDING SYNCHALL TO " + localRegisteredClientOnServer);
              RequestSynchronizeAll.executeSynchAll(this.serverState, localRegisteredClientOnServer.getProcessor());
              localRegisteredClientOnServer.wasFullSynched = true;
              localRegisteredClientOnServer.flagSynch((short)-32768);
              System.err.println("[SERVER] SYNCHALL TO " + localRegisteredClientOnServer + " took: " + (System.currentTimeMillis() - l));
            }
          }
          catch (IOException localIOException)
          {
            localIOException;
          }
        }
      }
    }
  }
  
  public ServerState getServerState()
  {
    return this.serverState;
  }
  
  public class_941 getTimer()
  {
    return this.timer;
  }
  
  public abstract void initializeServerState();
  
  public boolean isBanned(RegisteredClientOnServer paramRegisteredClientOnServer)
  {
    return false;
  }
  
  public boolean isWhiteListed(RegisteredClientOnServer paramRegisteredClientOnServer)
  {
    return false;
  }
  
  public boolean isListenting()
  {
    return this.serverListener.isListening();
  }
  
  public abstract int onLoggedIn(RegisteredClientOnServer paramRegisteredClientOnServer);
  
  public abstract void onLoggedout(RegisteredClientOnServer paramRegisteredClientOnServer);
  
  protected abstract void onShutDown();
  
  public int registerClient(RegisteredClientOnServer paramRegisteredClientOnServer, float paramFloat)
  {
    if (paramFloat != getServerState().getVersion()) {
      return -5;
    }
    if (!isAdmin(paramRegisteredClientOnServer))
    {
      if (this.serverState.getClients().size() >= this.serverState.getMaxClients()) {
        return -4;
      }
      if (isBanned(paramRegisteredClientOnServer))
      {
        System.err.println("[SERVER][LOGIN] Denying banned user: " + paramRegisteredClientOnServer);
        return -6;
      }
      if (!isWhiteListed(paramRegisteredClientOnServer))
      {
        System.err.println("[SERVER][LOGIN] Denying not white listed user: " + paramRegisteredClientOnServer);
        return -8;
      }
    }
    paramFloat = paramRegisteredClientOnServer.getPlayerName();
    synchronized (this.serverState.getClients())
    {
      Iterator localIterator = this.serverState.getClients().values().iterator();
      while (localIterator.hasNext()) {
        if (((RegisteredClientOnServer)localIterator.next()).getPlayerName().equals(paramFloat)) {
          return -2;
        }
      }
    }
    int i;
    if ((i = onLoggedIn(paramRegisteredClientOnServer)) == 0)
    {
      synchronized (getServerState().getClients())
      {
        getServerState().getClients().put(Integer.valueOf(paramRegisteredClientOnServer.getId()), paramRegisteredClientOnServer);
      }
      setChanged();
      notifyObservers();
    }
    return i;
  }
  
  protected abstract boolean isAdmin(RegisteredClientOnServer paramRegisteredClientOnServer);
  
  public void run()
  {
    System.out.println("[SERVERCONTROLLER][INIT] SERVER STARTED UPDATING");
    try
    {
      Thread.sleep(100L);
      this.timer.b();
      for (;;)
      {
        long l1 = System.currentTimeMillis();
        if (!this.serverState.isPaused())
        {
          update(this.timer);
          long l2 = System.currentTimeMillis() - l1;
          long l3;
          if ((l3 = 30L - l2) > 0L) {
            Thread.sleep(l3);
          }
          ServerState.entityCount = getServerState().getLocalAndRemoteObjectContainer().getLocalObjects().size();
        }
        else
        {
          Thread.sleep(500L);
        }
        this.timer.b();
      }
      Object localObject;
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      (localObject = localRuntimeException).printStackTrace();
      System.err.println("Exiting because of exception " + localObject);
      System.exit(0);
      return;
    }
    catch (Exception localException)
    {
      (localObject = localException).printStackTrace();
      System.err.println("Exiting because of exception " + localObject);
      System.exit(0);
    }
  }
  
  public void sendLogout(int paramInt, String paramString)
  {
    if ((paramInt = (RegisteredClientOnServer)this.serverState.getClients().get(Integer.valueOf(paramInt))) != null)
    {
      System.err.println("[SERVER] SENDING ACTIVE LOGOUT TO CLIENT " + paramInt);
      paramInt.getProcessor().serverCommand(NetUtil.commands.getByClass(LogoutClient.class).getId(), 0, new Object[] { paramString });
    }
  }
  
  protected void sendMessages(Set paramSet)
  {
    long l1 = System.currentTimeMillis();
    while (getServerState().getToBroadCastMessages().size() > 0)
    {
      ServerMessage localServerMessage = (ServerMessage)getServerState().getToBroadCastMessages().remove(0);
      try
      {
        Iterator localIterator = paramSet.iterator();
        while (localIterator.hasNext()) {
          ((RegisteredClientOnServer)localIterator.next()).serverMessage(localServerMessage);
        }
      }
      catch (Exception localException)
      {
        localException;
      }
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SERVER][UPDATE] WARNING: sendMessages update took " + l2);
    }
  }
  
  public void setServerState(ServerState paramServerState)
  {
    this.serverState = paramServerState;
  }
  
  public void setTimer(class_941 paramclass_941)
  {
    this.timer = paramclass_941;
  }
  
  public void startServerAndListen()
  {
    this.serverListener = new ServerListener("localhost", port, this.serverState);
    this.serverListener.addObserver(this);
    initializeServerState();
    new Thread(this.serverListener, "ServerListener").start();
    new Thread(this, "ServerController").start();
  }
  
  public void synchronize(Set paramSet)
  {
    checkIfClientsNeedResynch(paramSet);
    if (SynchronizationSender.encodeNetworkObjects(getServerState().getLocalAndRemoteObjectContainer(), this.serverState, this.dataOut, false) == 1)
    {
      Iterator localIterator = paramSet.iterator();
      while (localIterator.hasNext())
      {
        RegisteredClientOnServer localRegisteredClientOnServer;
        if ((localRegisteredClientOnServer = (RegisteredClientOnServer)localIterator.next()).wasFullSynched) {
          localRegisteredClientOnServer.wasFullSynched = false;
        }
        synchronized (localRegisteredClientOnServer.getProcessor().getLock())
        {
          if (localRegisteredClientOnServer.getProcessor().isAlive()) {
            try
            {
              new Header(Synchronize.class, 0, localRegisteredClientOnServer.getId(), (short)-32768, (byte)123).write(localRegisteredClientOnServer.getProcessor().getOut());
              this.byteOutPut.writeTo(localRegisteredClientOnServer.getProcessor().getOut());
              int i = localRegisteredClientOnServer.getProcessor().getCurrentSize();
              long l1 = System.currentTimeMillis();
              localRegisteredClientOnServer.getProcessor().flushDoubleOutBuffer();
              long l2;
              if ((l2 = System.currentTimeMillis() - l1) > 20L) {
                System.err.println("[WARNING][SERVER] Exception: synchronized flush took " + l2 + " ms, size: " + i + " bytes");
              }
            }
            catch (IOException localIOException)
            {
              localIOException.printStackTrace();
              System.err.println("[WARNING] SERVER CANNOT REACH " + localRegisteredClientOnServer + " SKIPPING THIS CLIENT'S UPDATE");
            }
          }
        }
      }
      SynchronizationReceiver.handleDeleted(this.serverState.getLocalAndRemoteObjectContainer(), this.serverState, this.delHelper);
    }
    this.byteOutPut.reset();
    synchronizePrivate(paramSet);
    getServerState().getLocalAndRemoteObjectContainer().checkGhostObjects();
  }
  
  public void synchronizePrivate(Set paramSet)
  {
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      RegisteredClientOnServer localRegisteredClientOnServer;
      if (SynchronizationSender.encodeNetworkObjects((localRegisteredClientOnServer = (RegisteredClientOnServer)paramSet.next()).getLocalAndRemoteObjectContainer(), this.serverState, this.dataOut, false) == 1) {
        synchronized (localRegisteredClientOnServer.getProcessor().getLock())
        {
          if (!localRegisteredClientOnServer.getProcessor().isAlive()) {
            continue;
          }
          try
          {
            new Header(SynchronizePrivateChannel.class, 0, localRegisteredClientOnServer.getId(), (short)-32768, (byte)123).write(localRegisteredClientOnServer.getProcessor().getOut());
            int i = localRegisteredClientOnServer.getProcessor().getCurrentSize();
            long l1 = System.currentTimeMillis();
            this.byteOutPut.writeTo(localRegisteredClientOnServer.getProcessor().getOut());
            localRegisteredClientOnServer.getProcessor().flushDoubleOutBuffer();
            long l2;
            if ((l2 = System.currentTimeMillis() - l1) > 10L) {
              System.err.println("[WARNING][SERVER] Exception: private synchronized flush took " + l2 + " ms, size " + i);
            }
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
            System.err.println("[WARNING] SERVER CANNOT REACH " + localRegisteredClientOnServer + " SKIPPING THIS CLIENT'S UPDATE");
          }
        }
      }
      SynchronizationReceiver.handleDeleted(localRegisteredClientOnServer.getLocalAndRemoteObjectContainer(), this.serverState, this.delHelper);
      this.byteOutPut.reset();
    }
  }
  
  public void unregister(int paramInt)
  {
    synchronized (this.toRemoveClients)
    {
      this.toRemoveClients.add(Integer.valueOf(paramInt));
      return;
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    setChanged();
    notifyObservers(paramObject);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */