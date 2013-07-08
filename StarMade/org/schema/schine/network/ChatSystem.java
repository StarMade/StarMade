package org.schema.schine.network;

import class_1077;
import class_1079;
import class_939;
import class_941;
import class_954;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.NetworkChat;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteArray;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteStringArray;
import org.schema.schine.network.objects.remote.Streamable;
import org.schema.schine.network.server.ServerMessage;
import org.schema.schine.network.server.ServerState;
import org.schema.schine.network.server.ServerStateInterface;

public class ChatSystem
  implements Sendable, class_1079, class_954
{
  public static final int CHAT_LIMIT = 128;
  private final ArrayList chatServerLogToSend = new ArrayList();
  private final ArrayList chatLogToSend = new ArrayList();
  private final ArrayList wisperLogToSend;
  private NetworkChat networkChat;
  private int field_310 = -434343;
  private final StateInterface state;
  private int ownerStateId = -1;
  private boolean markedForDelete;
  private boolean markedForDeleteSent;
  private final class_1077 textInput = new class_1077(128, this);
  private final boolean onServer;
  public static final byte TYPE_PM = 0;
  public static final byte TYPE_FACTION_PM = 1;
  
  public ChatSystem(StateInterface paramStateInterface)
  {
    this.state = paramStateInterface;
    this.wisperLogToSend = new ArrayList();
    this.onServer = (paramStateInterface instanceof ServerStateInterface);
  }
  
  public void addToVisibleChat(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.state.chat(this, paramString1, paramString2, paramBoolean);
  }
  
  public void cleanUpOnEntityDelete() {}
  
  public String[] getCommandPrefixes()
  {
    return this.state.getCommandPrefixes();
  }
  
  public int getId()
  {
    return this.field_310;
  }
  
  public NetworkChat getNetworkChat()
  {
    return this.networkChat;
  }
  
  public NetworkChat getNetworkObject()
  {
    return getNetworkChat();
  }
  
  public int getOwnerStateId()
  {
    return this.ownerStateId;
  }
  
  public StateInterface getState()
  {
    return this.state;
  }
  
  public class_1077 getTextInput()
  {
    return this.textInput;
  }
  
  public String handleAutoComplete(String paramString1, class_1079 paramclass_1079, String paramString2)
  {
    return this.state.onAutoComplete(paramString1, this, paramString2);
  }
  
  public void handleKeyEvent()
  {
    getTextInput().handleKeyEvent();
  }
  
  public void handleMouseEvent(class_939 paramclass_939)
  {
    getTextInput();
    class_1077.c1();
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    paramNetworkObject = (NetworkChat)paramNetworkObject;
    setId(((Integer)paramNetworkObject.field_87.get()).intValue());
    setOwnerStateId(((Integer)this.networkChat.owner.get()).intValue());
    System.err.println("[CHAT] " + getState() + " initializing data from network object " + getId());
  }
  
  public void initialize() {}
  
  public boolean isMarkedForDeleteVolatile()
  {
    return this.markedForDelete;
  }
  
  public boolean isMarkedForDeleteVolatileSent()
  {
    return this.markedForDeleteSent;
  }
  
  public boolean isOnServer()
  {
    return this.onServer;
  }
  
  public void newNetworkObject()
  {
    this.networkChat = new NetworkChat(getState());
  }
  
  public void onFailedTextCheck(String paramString) {}
  
  public void wisper(String paramString1, String paramString2, boolean paramBoolean, byte paramByte)
  {
    try
    {
      ChatSystem.Wisper localWisper;
      (localWisper = new ChatSystem.Wisper(this, null)).player = paramString1;
      localWisper.message = paramString2;
      localWisper.type = paramByte;
      System.err.println("[CHAT] sending PM: " + this + " --> " + localWisper.player + ": " + localWisper.message);
      this.wisperLogToSend.add(localWisper);
      if (paramBoolean) {
        addToVisibleChat(localWisper.message, "[PM to " + localWisper.player + "]", false);
      }
    }
    catch (Exception localException)
    {
      if (paramBoolean)
      {
        addToVisibleChat(paramString2, "[PM]", false);
        addToVisibleChat("PM FAILED: Usage: /pm playername some text", "[ERROR]", false);
      }
      else
      {
        System.err.println("[ERROR] PM FAILED: " + paramString2 + "; " + localException.getClass().getSimpleName());
      }
    }
    if (!isOnServer()) {
      ((ClientState)getState()).chatUpdate(this);
    }
  }
  
  public void onTextEnter(String paramString, boolean paramBoolean)
  {
    String[] arrayOfString;
    if (paramString.toLowerCase(Locale.ENGLISH).startsWith("/pm "))
    {
      if ((arrayOfString = paramString.split("\\s+", 3)).length == 3)
      {
        wisper(arrayOfString[1], arrayOfString[2], true, (byte)0);
        return;
      }
      if (!isOnServer()) {
        addToVisibleChat("No message", "[ERROR]", true);
      }
      return;
    }
    if (this.state.onChatTextEnterHook(this, paramString, paramBoolean)) {
      return;
    }
    if ((arrayOfString = getCommandPrefixes()) != null) {
      for (int i = 0; i < arrayOfString.length; i++) {
        if (paramString.startsWith(arrayOfString[i]))
        {
          this.state.onStringCommand(paramString.substring(1, paramString.length()), this, arrayOfString[i]);
          return;
        }
      }
    }
    if (paramBoolean) {
      this.chatLogToSend.add(paramString);
    }
    addToVisibleChat(paramString, "[ALL]", true);
    if (!isOnServer()) {
      ((ClientState)getState()).chatUpdate(this);
    }
  }
  
  public void setId(int paramInt)
  {
    this.field_310 = paramInt;
  }
  
  public void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.markedForDelete = paramBoolean;
  }
  
  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.markedForDeleteSent = paramBoolean;
  }
  
  public void setNetworkChat(NetworkChat paramNetworkChat)
  {
    this.networkChat = paramNetworkChat;
  }
  
  public void setOwnerStateId(int paramInt)
  {
    this.ownerStateId = paramInt;
  }
  
  public String toString()
  {
    return "(" + getId() + ")ChatSystem";
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    Iterator localIterator = (paramNetworkObject = (NetworkChat)paramNetworkObject).chatServerLogBuffer.getReceiveBuffer().iterator();
    Object localObject1;
    Object localObject2;
    String str1;
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteString)localIterator.next();
      if (this.ownerStateId == this.state.getId())
      {
        localObject2 = (String)((RemoteString)localObject1).get();
        str1 = "[SERVER]";
        if (((String)localObject2).startsWith("[PM]"))
        {
          str1 = "[PM]";
          localObject2 = ((String)localObject2).substring(4);
        }
        else if (((String)localObject2).startsWith("[FACTION]"))
        {
          str1 = "[FACTION]";
          localObject2 = ((String)localObject2).substring(13);
        }
        addToVisibleChat((String)localObject2, str1, false);
      }
    }
    if (this.ownerStateId == this.state.getId()) {
      return;
    }
    localIterator = paramNetworkObject.chatLogBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteString)localIterator.next();
      getTextInput().a82().add(((RemoteString)localObject1).get());
      addToVisibleChat((String)((RemoteString)localObject1).get(), "[ALL]", true);
      if ((this.state instanceof ServerState)) {
        this.chatLogToSend.add(((RemoteString)localObject1).get());
      }
    }
    localIterator = paramNetworkObject.chatWisperBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (RemoteStringArray)localIterator.next();
      if ((this.state instanceof ServerState))
      {
        localObject2 = (String)((RemoteStringArray)localObject1).get(0).get();
        str1 = (String)((RemoteStringArray)localObject1).get(1).get();
        System.err.println("RECEIVING WISPER (" + getState() + "): " + (String)localObject2 + ": " + str1);
        paramNetworkObject = Byte.parseByte((String)((RemoteStringArray)localObject1).get(2).get());
        getTextInput().a82().add(str1);
        String str2 = paramNetworkObject == 0 ? "PM" : "FACTION";
        addToVisibleChat(str1, "[" + str2 + " from " + (String)localObject2 + "]", false);
        try
        {
          int i = ((ServerState)this.state).getClientIdByName((String)localObject2);
          RegisteredClientOnServer localRegisteredClientOnServer;
          if ((localRegisteredClientOnServer = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(i))) == null)
          {
            if (paramNetworkObject != 1) {
              this.chatServerLogToSend.add("ERROR: could not find client " + (String)localObject2);
            }
          }
          else
          {
            (localObject2 = new ServerMessage(str1, 0, getOwnerStateId())).prefix = str2;
            localRegisteredClientOnServer.getWispers().add(localObject2);
          }
        }
        catch (ClientIdNotFoundException localClientIdNotFoundException)
        {
          if (paramNetworkObject != 1)
          {
            System.err.println("[CHAT] WARNING: could not find " + localClientIdNotFoundException.getMessage());
            this.chatServerLogToSend.add("ERROR: could not find client " + (String)((RemoteStringArray)localObject1).get(0).get());
          }
        }
      }
    }
  }
  
  public void updateLocal(class_941 arg1)
  {
    synchronized (getNetworkObject())
    {
      updateToNetworkObject();
      return;
    }
  }
  
  public void updateToFullNetworkObject()
  {
    this.networkChat.field_87.set(Integer.valueOf(getId()));
    this.networkChat.owner.set(Integer.valueOf(this.ownerStateId));
    this.networkChat.setChanged(true);
    assert (((this.state instanceof ServerState)) || (this.ownerStateId >= 0));
    assert (getState().getId() >= 0);
  }
  
  public void updateToNetworkObject()
  {
    Object localObject;
    if (((this.state instanceof ServerState)) && (getOwnerStateId() >= 0))
    {
      for (int i = 0; i < this.chatServerLogToSend.size(); i++)
      {
        localObject = new RemoteString((String)this.chatServerLogToSend.get(i), getNetworkObject());
        this.networkChat.chatServerLogBuffer.add((Streamable)localObject);
        System.err.println("[CHAT] " + this.state + " (" + this + "): " + (String)((RemoteString)localObject).get());
      }
      this.chatServerLogToSend.clear();
      RegisteredClientOnServer localRegisteredClientOnServer1;
      if ((localRegisteredClientOnServer1 = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(getOwnerStateId()))) != null) {
        while (!localRegisteredClientOnServer1.getWispers().isEmpty())
        {
          localObject = (ServerMessage)localRegisteredClientOnServer1.getWispers().remove(0);
          RegisteredClientOnServer localRegisteredClientOnServer2 = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(((ServerMessage)localObject).receiverPlayerId));
          localObject = "[" + ((ServerMessage)localObject).prefix + "][" + (localRegisteredClientOnServer2 != null ? localRegisteredClientOnServer2.getPlayerName() : "unknown") + "] " + ((ServerMessage)localObject).message;
          localObject = new RemoteString((String)localObject, getNetworkObject());
          this.networkChat.chatServerLogBuffer.add((Streamable)localObject);
          System.err.println("[CHAT][WISPER] " + this.state + " (" + this + "): " + localObject);
        }
      }
      System.err.println("[SERVER][ERROR] could not wisper. client not found: " + getOwnerStateId());
    }
    for (int j = 0; j < this.chatLogToSend.size(); j++)
    {
      localObject = new RemoteString((String)this.chatLogToSend.get(j), getNetworkObject());
      this.networkChat.chatLogBuffer.add((Streamable)localObject);
      System.err.println("[CHAT] " + this.state + " (" + this + "): " + (String)((RemoteString)localObject).get());
    }
    for (j = 0; j < this.wisperLogToSend.size(); j++)
    {
      (localObject = new RemoteStringArray(3, getNetworkObject())).set(0, ((ChatSystem.Wisper)this.wisperLogToSend.get(j)).player);
      ((RemoteStringArray)localObject).set(1, ((ChatSystem.Wisper)this.wisperLogToSend.get(j)).message);
      ((RemoteStringArray)localObject).set(2, String.valueOf(((ChatSystem.Wisper)this.wisperLogToSend.get(j)).type));
      this.networkChat.chatWisperBuffer.add((RemoteArray)localObject);
      System.err.println("[CHAT]" + this.state + " (" + this + "): " + localObject);
    }
    this.wisperLogToSend.clear();
    this.chatLogToSend.clear();
  }
  
  public void destroyPersistent() {}
  
  public boolean isMarkedForPermanentDelete()
  {
    return false;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean) {}
  
  public boolean isUpdatable()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.ChatSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */