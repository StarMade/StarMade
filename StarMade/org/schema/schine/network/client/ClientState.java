package org.schema.schine.network.client;

import class_1418;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.schema.schine.network.ChatSystem;
import org.schema.schine.network.NetworkProcessor;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.NetworkStatus;

public abstract class ClientState
  extends Observable
  implements ClientStateInterface
{
  public static boolean loginFailed;
  private final NetworkStateContainer networkStateContainer = new NetworkStateContainer(false);
  private final NetworkStateContainer privateNetworkStateContainer = new NetworkStateContainer(true);
  private Map requestAnswers = new HashMap();
  private long ping;
  private boolean ready;
  private boolean synchronizedFlag;
  private boolean synchronizing;
  private int field_405 = -4242;
  private NetworkStatus networkStatus = new NetworkStatus();
  private int idStartRange;
  private Integer objectIdPointer = new Integer(0);
  public static final Integer NEW_ID_RANGE = Integer.valueOf(100);
  private final ArrayList mouseEvents = new ArrayList();
  private ClientCommunicator clientConnection;
  public Object updateLock = new Object();
  private class_1418 dragging;
  private boolean debugBigChunk;
  private final ThreadPoolExecutor theadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
  private long serverTimeOnLogin;
  private int serverTimeDifference;
  private long lastDeactivatedMenu;
  private short updateNumber;
  private final GUICallbackController guiCallbackController = new GUICallbackController();
  public static float serverVersion;
  
  public void setServerVersion(float paramFloat)
  {
    serverVersion = paramFloat;
  }
  
  public void arrivedReturn(short paramShort, Object... paramVarArgs)
  {
    this.requestAnswers.put(Short.valueOf(paramShort), paramVarArgs);
  }
  
  public void disconnect()
  {
    getProcessor().closeSocket();
  }
  
  public abstract void exit();
  
  public class_1418 getDragging()
  {
    return this.dragging;
  }
  
  public GUICallbackController getGuiCallbackController()
  {
    return this.guiCallbackController;
  }
  
  public int getId()
  {
    return this.field_405;
  }
  
  public int getIdStartRange()
  {
    return this.idStartRange;
  }
  
  public long getLastDeactivatedMenu()
  {
    return this.lastDeactivatedMenu;
  }
  
  public NetworkStateContainer getLocalAndRemoteObjectContainer()
  {
    return this.networkStateContainer;
  }
  
  public ArrayList getMouseEvents()
  {
    return this.mouseEvents;
  }
  
  public NetworkStatus getNetworkStatus()
  {
    return this.networkStatus;
  }
  
  public int getNextFreeObjectId()
  {
    long l = System.currentTimeMillis();
    synchronized (this.objectIdPointer)
    {
      assert (this.objectIdPointer.intValue() <= this.idStartRange + NEW_ID_RANGE.intValue()) : "[CRITICAL] cannot garanty unique ids";
      if ((this.objectIdPointer.intValue() <= 0) || (this.objectIdPointer.intValue() == this.idStartRange + NEW_ID_RANGE.intValue())) {
        try
        {
          getController().aquireFreeIds();
          this.objectIdPointer = Integer.valueOf(this.idStartRange);
        }
        catch (IOException localIOException)
        {
          localIOException;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException;
        }
      }
      this.objectIdPointer = Integer.valueOf(this.objectIdPointer.intValue() + 1);
    }
    System.err.println("[CLIENT] ID RANGE AQUIRE TOOK " + (System.currentTimeMillis() - localObject1));
    return this.objectIdPointer.intValue();
  }
  
  public long getPing()
  {
    return this.ping;
  }
  
  public NetworkStateContainer getPrivateLocalAndRemoteObjectContainer()
  {
    return this.privateNetworkStateContainer;
  }
  
  public NetworkProcessor getProcessor()
  {
    return this.clientConnection.getClientProcessor();
  }
  
  public Object[] getReturn(short paramShort)
  {
    return (Object[])this.requestAnswers.remove(Short.valueOf(paramShort));
  }
  
  public int getServerTimeDifference()
  {
    return this.serverTimeDifference;
  }
  
  public long getServerTimeOnLogin()
  {
    return this.serverTimeOnLogin;
  }
  
  public ThreadPoolExecutor getThreadPool()
  {
    return this.theadPool;
  }
  
  public short getUpdateNumber()
  {
    return this.updateNumber;
  }
  
  public abstract List getVisibleChatLog();
  
  public abstract List getGeneralChatLog();
  
  public void incUpdateNumber()
  {
    this.updateNumber = ((short)(this.updateNumber + 1));
  }
  
  public boolean isReadingBigChunk()
  {
    return false;
  }
  
  public boolean isReady()
  {
    return this.ready;
  }
  
  public boolean isSynchronized()
  {
    return this.synchronizedFlag;
  }
  
  public boolean isSynchronizing()
  {
    return this.synchronizing;
  }
  
  public void println(String paramString) {}
  
  public void setClientConnection(ClientCommunicator paramClientCommunicator)
  {
    this.clientConnection = paramClientCommunicator;
  }
  
  public void setDebugBigChunk(boolean paramBoolean)
  {
    this.debugBigChunk = paramBoolean;
  }
  
  public void setDragging(class_1418 paramclass_1418)
  {
    this.dragging = paramclass_1418;
  }
  
  public void setId(int paramInt)
  {
    this.field_405 = paramInt;
  }
  
  public void setIdStartRange(int paramInt)
  {
    this.idStartRange = paramInt;
  }
  
  public void setLastDeactivatedMenu(long paramLong)
  {
    this.lastDeactivatedMenu = paramLong;
  }
  
  public void setPing(long paramLong)
  {
    this.ping = paramLong;
  }
  
  public void setReady(boolean paramBoolean)
  {
    this.ready = paramBoolean;
  }
  
  public void setServerTimeOnLogin(long paramLong)
  {
    this.serverTimeOnLogin = paramLong;
    this.serverTimeDifference = ((int)(paramLong - System.currentTimeMillis()));
  }
  
  public void setSynchronized(boolean paramBoolean)
  {
    this.synchronizedFlag = paramBoolean;
  }
  
  public void setSynchronizing(boolean paramBoolean)
  {
    this.synchronizing = paramBoolean;
  }
  
  public String toString()
  {
    return "Client(" + getId() + ")";
  }
  
  public void chatUpdate(ChatSystem paramChatSystem) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.client.ClientState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */