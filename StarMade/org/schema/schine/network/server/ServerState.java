package org.schema.schine.network.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.schema.schine.network.IdGen;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.NetworkStatus;
import org.schema.schine.network.commands.LoginRequest;

public abstract class ServerState
  extends Observable
  implements ServerStateInterface
{
  public static int entityCount;
  public static boolean shutdown;
  private NetworkStateContainer stateContainer = new NetworkStateContainer(false);
  private final HashMap clients = new HashMap();
  private ServerControllerInterface controller;
  private boolean paused;
  private NetworkStatus networkStatus = new NetworkStatus();
  private final ArrayList toBroadCastMessages = new ArrayList();
  private final ThreadPoolExecutor theadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
  private final ServerEntityWriterThread threadQueue = new ServerEntityWriterThread();
  private short updateNumber;
  private boolean debugBigChunk;
  private final ArrayList loginRequests = new ArrayList();
  
  public ServerState()
  {
    this.threadQueue.start();
  }
  
  public void addLoginRequest(LoginRequest paramLoginRequest)
  {
    getLoginRequests().add(paramLoginRequest);
  }
  
  public void handleLoginReuests()
  {
    while (!this.loginRequests.isEmpty())
    {
      LoginRequest localLoginRequest;
      (localLoginRequest = (LoginRequest)this.loginRequests.remove(0)).prepare();
      this.theadPool.execute(localLoginRequest);
    }
  }
  
  public HashMap getClients()
  {
    return this.clients;
  }
  
  public ServerControllerInterface getController()
  {
    return this.controller;
  }
  
  public int getId()
  {
    return 0;
  }
  
  public NetworkStateContainer getLocalAndRemoteObjectContainer()
  {
    return this.stateContainer;
  }
  
  public NetworkStatus getNetworkStatus()
  {
    return this.networkStatus;
  }
  
  public int getNextFreeObjectId()
  {
    return IdGen.getFreeObjectId(1);
  }
  
  public ThreadPoolExecutor getThreadPool()
  {
    return this.theadPool;
  }
  
  public ArrayList getToBroadCastMessages()
  {
    return this.toBroadCastMessages;
  }
  
  public short getUpdateNumber()
  {
    return this.updateNumber;
  }
  
  public void incUpdateNumber()
  {
    this.updateNumber = ((short)(this.updateNumber + 1));
  }
  
  public boolean isPaused()
  {
    return this.paused;
  }
  
  public boolean isReadingBigChunk()
  {
    return this.debugBigChunk;
  }
  
  public boolean isReady()
  {
    return this.controller.isListenting();
  }
  
  public void setController(ServerControllerInterface paramServerControllerInterface)
  {
    this.controller = paramServerControllerInterface;
  }
  
  public void setPaused(boolean paramBoolean)
  {
    this.paused = paramBoolean;
  }
  
  public String toString()
  {
    return "Server(" + getId() + ")";
  }
  
  public abstract int getClientIdByName(String paramString);
  
  public int getServerTimeDifference()
  {
    return 0;
  }
  
  public ServerEntityWriterThread getThreadQueue()
  {
    return this.threadQueue;
  }
  
  public ArrayList getLoginRequests()
  {
    return this.loginRequests;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.server.ServerState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */