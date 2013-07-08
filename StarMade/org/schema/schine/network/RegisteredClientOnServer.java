package org.schema.schine.network;

import java.io.PrintStream;
import java.net.InetAddress;
import java.util.ArrayList;
import org.schema.schine.network.commands.MessageTo;
import org.schema.schine.network.server.ServerMessage;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class RegisteredClientOnServer
  implements Identifiable, Recipient
{
  private int field_310;
  private String playerName;
  private ServerProcessor serverProcessor;
  private boolean connected;
  private short needsSynch = -32768;
  private final NetworkStateContainer localAndRemoteContainer;
  private final SynchronizationContainerController synchController;
  private Object player;
  public boolean wasFullSynched;
  private final ArrayList wispers = new ArrayList();
  
  public RegisteredClientOnServer(int paramInt, String paramString, ServerStateInterface paramServerStateInterface)
  {
    this.field_310 = paramInt;
    this.playerName = paramString;
    this.connected = true;
    this.localAndRemoteContainer = new NetworkStateContainer(true);
    this.synchController = new SynchronizationContainerController(this.localAndRemoteContainer, paramServerStateInterface, true);
  }
  
  public void flagSynch(short paramShort)
  {
    this.needsSynch = paramShort;
  }
  
  public int getId()
  {
    return this.field_310;
  }
  
  public String getIp()
  {
    try
    {
      return getProcessor().getClientIp().toString().replace("/", "");
    }
    catch (Exception localException)
    {
      localException;
    }
    return "0.0.0.0";
  }
  
  public NetworkStateContainer getLocalAndRemoteObjectContainer()
  {
    return this.localAndRemoteContainer;
  }
  
  public String getPlayerName()
  {
    return this.playerName;
  }
  
  public Object getPlayerObject()
  {
    return this.player;
  }
  
  public ServerProcessor getProcessor()
  {
    return this.serverProcessor;
  }
  
  public SynchronizationContainerController getSynchController()
  {
    return this.synchController;
  }
  
  public short getSynchPacketId()
  {
    return this.needsSynch;
  }
  
  public boolean isConnected()
  {
    return this.connected;
  }
  
  public boolean needsSynch()
  {
    return this.needsSynch > -32768;
  }
  
  public void sendCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
  {
    NetUtil.commands.getByClass(paramClass).writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.serverProcessor);
  }
  
  public Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
  {
    throw new IllegalArgumentException("this moethod is only used: client to server for client requests");
  }
  
  public void serverMessage(ServerMessage paramServerMessage)
  {
    System.err.println("[SEND][SERVERMESSAGE] " + paramServerMessage + " to " + this);
    sendCommand(getId(), IdGen.getNewPacketId(), MessageTo.class, new Object[] { "SERVER", paramServerMessage.message, Integer.valueOf(paramServerMessage.type) });
  }
  
  public void serverMessage(String paramString)
  {
    System.err.println("[SEND][SERVERMESSAGE] " + paramString + " to " + this);
    sendCommand(getId(), IdGen.getNewPacketId(), MessageTo.class, new Object[] { "SERVER", paramString, Integer.valueOf(0) });
  }
  
  public void setConnected(boolean paramBoolean)
  {
    this.connected = false;
  }
  
  public void setId(int paramInt)
  {
    this.field_310 = paramInt;
  }
  
  public void setPlayerName(String paramString)
  {
    this.playerName = paramString;
  }
  
  public void setPlayerObject(Object paramObject)
  {
    this.player = paramObject;
  }
  
  public void setProcessor(ServerProcessor paramServerProcessor)
  {
    this.serverProcessor = paramServerProcessor;
  }
  
  public String toString()
  {
    return "RegisteredClient: " + getPlayerName() + " (" + this.field_310 + ") connected: " + this.connected;
  }
  
  public ArrayList getWispers()
  {
    return this.wispers;
  }
  
  public boolean checkConnection()
  {
    if (!this.connected) {
      return false;
    }
    return getProcessor().isConnectionAlive();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.RegisteredClientOnServer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */