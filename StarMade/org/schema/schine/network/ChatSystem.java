/*     */ package org.schema.schine.network;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.objects.NetworkChat;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteArray;
/*     */ import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteField;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ import org.schema.schine.network.objects.remote.RemoteString;
/*     */ import org.schema.schine.network.objects.remote.RemoteStringArray;
/*     */ import org.schema.schine.network.objects.remote.Streamable;
/*     */ import org.schema.schine.network.server.ServerMessage;
/*     */ import org.schema.schine.network.server.ServerState;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ import wB;
/*     */ import wC;
/*     */ import wx;
/*     */ import xp;
/*     */ import xq;
/*     */ 
/*     */ public class ChatSystem
/*     */   implements Sendable, wB, wx
/*     */ {
/*     */   public static final int CHAT_LIMIT = 128;
/*     */   private final ArrayList chatServerLogToSend;
/*     */   private final ArrayList chatLogToSend;
/*     */   private final ArrayList wisperLogToSend;
/*     */   private NetworkChat networkChat;
/*  42 */   private int id = -434343;
/*     */   private final StateInterface state;
/*  46 */   private int ownerStateId = -1;
/*     */   private boolean markedForDelete;
/*     */   private boolean markedForDeleteSent;
/*     */   private final wC textInput;
/*     */   private final boolean onServer;
/*     */   public static final byte TYPE_PM = 0;
/*     */   public static final byte TYPE_FACTION_PM = 1;
/*     */ 
/*     */   public ChatSystem(StateInterface paramStateInterface)
/*     */   {
/*  58 */     this.textInput = new wC(128, this);
/*  59 */     this.chatLogToSend = new ArrayList();
/*  60 */     this.chatServerLogToSend = new ArrayList();
/*  61 */     this.state = paramStateInterface;
/*  62 */     this.wisperLogToSend = new ArrayList();
/*  63 */     this.onServer = (paramStateInterface instanceof ServerStateInterface);
/*     */   }
/*     */ 
/*     */   public void addToVisibleChat(String paramString1, String paramString2, boolean paramBoolean) {
/*  67 */     this.state.chat(this, paramString1, paramString2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void cleanUpOnEntityDelete()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String[] getCommandPrefixes()
/*     */   {
/*  80 */     return this.state.getCommandPrefixes();
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  85 */     return this.id;
/*     */   }
/*     */ 
/*     */   public NetworkChat getNetworkChat()
/*     */   {
/*  94 */     return this.networkChat;
/*     */   }
/*     */ 
/*     */   public NetworkChat getNetworkObject()
/*     */   {
/*  99 */     return getNetworkChat();
/*     */   }
/*     */ 
/*     */   public int getOwnerStateId() {
/* 103 */     return this.ownerStateId;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/* 108 */     return this.state;
/*     */   }
/*     */ 
/*     */   public wC getTextInput() {
/* 112 */     return this.textInput;
/*     */   }
/*     */ 
/*     */   public String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/*     */   {
/* 117 */     return this.state.onAutoComplete(paramString1, this, paramString2);
/*     */   }
/*     */ 
/*     */   public void handleKeyEvent()
/*     */   {
/* 122 */     getTextInput().handleKeyEvent();
/*     */   }
/*     */ 
/*     */   public void handleMouseEvent(xp paramxp) {
/* 126 */     getTextInput(); wC.c();
/*     */   }
/*     */ 
/*     */   public void initFromNetworkObject(NetworkObject paramNetworkObject) {
/* 130 */     paramNetworkObject = (NetworkChat)paramNetworkObject;
/*     */ 
/* 132 */     setId(((Integer)paramNetworkObject.id.get()).intValue());
/* 133 */     setOwnerStateId(((Integer)this.networkChat.owner.get()).intValue());
/*     */ 
/* 135 */     System.err.println("[CHAT] " + getState() + " initializing data from network object " + getId());
/*     */   }
/*     */ 
/*     */   public void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatile()
/*     */   {
/* 145 */     return this.markedForDelete;
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForDeleteVolatileSent() {
/* 149 */     return this.markedForDeleteSent;
/*     */   }
/*     */ 
/*     */   public boolean isOnServer()
/*     */   {
/* 156 */     return this.onServer;
/*     */   }
/*     */ 
/*     */   public void newNetworkObject()
/*     */   {
/* 165 */     this.networkChat = new NetworkChat(getState());
/*     */   }
/*     */ 
/*     */   public void onFailedTextCheck(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void wisper(String paramString1, String paramString2, boolean paramBoolean, byte paramByte)
/*     */   {
/*     */     try
/*     */     {
/*     */       ChatSystem.Wisper localWisper;
/* 175 */       (
/* 176 */         localWisper = new ChatSystem.Wisper(this, null)).player = 
/* 176 */         paramString1;
/* 177 */       localWisper.message = paramString2;
/* 178 */       localWisper.type = paramByte;
/* 179 */       System.err.println("[CHAT] sending PM: " + this + " --> " + localWisper.player + ": " + localWisper.message);
/* 180 */       this.wisperLogToSend.add(localWisper);
/* 181 */       if (paramBoolean)
/* 182 */         addToVisibleChat(localWisper.message, "[PM to " + localWisper.player + "]", false);
/*     */     }
/*     */     catch (Exception localException) {
/* 185 */       if (paramBoolean) {
/* 186 */         addToVisibleChat(paramString2, "[PM]", false);
/* 187 */         addToVisibleChat("PM FAILED: Usage: /pm playername some text", "[ERROR]", false);
/*     */       } else {
/* 189 */         System.err.println("[ERROR] PM FAILED: " + paramString2 + "; " + localException.getClass().getSimpleName());
/*     */       }
/*     */     }
/* 192 */     if (!isOnServer())
/* 193 */       ((ClientState)getState()).chatUpdate(this);
/*     */   }
/*     */ 
/*     */   public void onTextEnter(String paramString, boolean paramBoolean)
/*     */   {
/*     */     String[] arrayOfString;
/* 199 */     if (paramString.toLowerCase(Locale.ENGLISH).startsWith("/pm "))
/*     */     {
/* 201 */       if ((
/* 201 */         arrayOfString = paramString.split("\\s+", 3)).length == 
/* 201 */         3) {
/* 202 */         wisper(arrayOfString[1], arrayOfString[2], true, (byte)0); return;
/* 203 */       }if (!isOnServer()) {
/* 204 */         addToVisibleChat("No message", "[ERROR]", true);
/*     */       }
/* 206 */       return;
/*     */     }
/* 208 */     if (this.state.onChatTextEnterHook(this, paramString, paramBoolean)) {
/* 209 */       return;
/*     */     }
/*     */ 
/* 213 */     if ((
/* 213 */       arrayOfString = getCommandPrefixes()) != null)
/*     */     {
/* 214 */       for (int i = 0; i < arrayOfString.length; i++) {
/* 215 */         if (paramString.startsWith(arrayOfString[i])) {
/* 216 */           this.state.onStringCommand(paramString.substring(1, paramString.length()), this, arrayOfString[i]);
/* 217 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 222 */     if (paramBoolean) {
/* 223 */       this.chatLogToSend.add(paramString);
/*     */     }
/* 225 */     addToVisibleChat(paramString, "[ALL]", true);
/* 226 */     if (!isOnServer())
/* 227 */       ((ClientState)getState()).chatUpdate(this);
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 243 */     this.id = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatile(boolean paramBoolean)
/*     */   {
/* 249 */     this.markedForDelete = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/*     */   {
/* 255 */     this.markedForDeleteSent = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setNetworkChat(NetworkChat paramNetworkChat)
/*     */   {
/* 263 */     this.networkChat = paramNetworkChat;
/*     */   }
/*     */ 
/*     */   public void setOwnerStateId(int paramInt) {
/* 267 */     this.ownerStateId = paramInt;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 272 */     return "(" + getId() + ")ChatSystem";
/*     */   }
/*     */ 
/*     */   public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/*     */   {
/* 279 */     for (Iterator localIterator = (
/* 279 */       paramNetworkObject = (NetworkChat)paramNetworkObject).chatServerLogBuffer
/* 279 */       .getReceiveBuffer().iterator(); localIterator.hasNext(); ) { localObject1 = (RemoteString)localIterator.next();
/* 280 */       if (this.ownerStateId == this.state.getId()) {
/* 281 */         localObject2 = (String)((RemoteString)localObject1).get();
/* 282 */         str1 = "[SERVER]";
/*     */ 
/* 284 */         if (((String)localObject2).startsWith("[PM]")) {
/* 285 */           str1 = "[PM]";
/* 286 */           localObject2 = ((String)localObject2).substring(4);
/* 287 */         } else if (((String)localObject2).startsWith("[FACTION]")) {
/* 288 */           str1 = "[FACTION]";
/* 289 */           localObject2 = ((String)localObject2).substring(13);
/*     */         }
/*     */ 
/* 292 */         addToVisibleChat((String)localObject2, str1, false);
/*     */       }
/*     */     }
/*     */     Object localObject1;
/*     */     Object localObject2;
/*     */     String str1;
/* 297 */     if (this.ownerStateId == this.state.getId()) {
/* 298 */       return;
/*     */     }
/*     */ 
/* 302 */     for (localIterator = paramNetworkObject.chatLogBuffer.getReceiveBuffer().iterator(); localIterator.hasNext(); ) { localObject1 = (RemoteString)localIterator.next();
/* 303 */       getTextInput().a().add(((RemoteString)localObject1).get());
/* 304 */       addToVisibleChat((String)((RemoteString)localObject1).get(), "[ALL]", true);
/* 305 */       if ((this.state instanceof ServerState)) {
/* 306 */         this.chatLogToSend.add(((RemoteString)localObject1).get());
/*     */       }
/*     */     }
/*     */ 
/* 310 */     for (localIterator = paramNetworkObject.chatWisperBuffer.getReceiveBuffer().iterator(); localIterator.hasNext(); ) { localObject1 = (RemoteStringArray)localIterator.next();
/*     */ 
/* 312 */       if ((this.state instanceof ServerState))
/*     */       {
/* 314 */         localObject2 = (String)((RemoteStringArray)localObject1).get(0).get();
/* 315 */         str1 = (String)((RemoteStringArray)localObject1).get(1).get();
/* 316 */         System.err.println("RECEIVING WISPER (" + getState() + "): " + (String)localObject2 + ": " + str1);
/* 317 */         paramNetworkObject = Byte.parseByte((String)((RemoteStringArray)localObject1).get(2).get());
/* 318 */         getTextInput().a().add(str1);
/* 319 */         String str2 = paramNetworkObject == 0 ? "PM" : "FACTION";
/* 320 */         addToVisibleChat(str1, "[" + str2 + " from " + (String)localObject2 + "]", false);
/*     */         try
/*     */         {
/* 323 */           int i = ((ServerState)this.state).getClientIdByName((String)localObject2);
/*     */           RegisteredClientOnServer localRegisteredClientOnServer;
/* 326 */           if ((
/* 326 */             localRegisteredClientOnServer = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(i))) == null)
/*     */           {
/* 327 */             if (paramNetworkObject != 1)
/* 328 */               this.chatServerLogToSend.add("ERROR: could not find client " + (String)localObject2);
/*     */           }
/*     */           else {
/* 331 */             (
/* 332 */               localObject2 = new ServerMessage(str1, 0, getOwnerStateId())).prefix = 
/* 332 */               str2;
/* 333 */             localRegisteredClientOnServer.getWispers().add(localObject2);
/*     */           }
/*     */ 
/*     */         }
/*     */         catch (ClientIdNotFoundException localClientIdNotFoundException)
/*     */         {
/* 340 */           if (paramNetworkObject != 1) {
/* 341 */             System.err.println("[CHAT] WARNING: could not find " + localClientIdNotFoundException.getMessage());
/* 342 */             this.chatServerLogToSend.add("ERROR: could not find client " + (String)((RemoteStringArray)localObject1).get(0).get());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateLocal(xq arg1)
/*     */   {
/* 355 */     synchronized (getNetworkObject()) {
/* 356 */       updateToNetworkObject();
/* 357 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateToFullNetworkObject() {
/* 362 */     this.networkChat.id.set(Integer.valueOf(getId()));
/* 363 */     this.networkChat.owner.set(Integer.valueOf(this.ownerStateId));
/* 364 */     this.networkChat.setChanged(true);
/* 365 */     assert (((this.state instanceof ServerState)) || (this.ownerStateId >= 0));
/* 366 */     assert (getState().getId() >= 0);
/*     */   }
/*     */ 
/*     */   public void updateToNetworkObject()
/*     */   {
/*     */     Object localObject;
/* 374 */     if (((this.state instanceof ServerState)) && (getOwnerStateId() >= 0)) {
/* 375 */       for (int i = 0; i < this.chatServerLogToSend.size(); i++) {
/* 376 */         localObject = new RemoteString((String)this.chatServerLogToSend.get(i), getNetworkObject());
/* 377 */         this.networkChat.chatServerLogBuffer.add((Streamable)localObject);
/* 378 */         System.err.println("[CHAT] " + this.state + " (" + this + "): " + (String)((RemoteString)localObject).get());
/*     */       }
/* 380 */       this.chatServerLogToSend.clear();
/*     */       RegisteredClientOnServer localRegisteredClientOnServer1;
/* 386 */       if ((
/* 386 */         localRegisteredClientOnServer1 = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(getOwnerStateId()))) != null)
/*     */       {
/* 387 */         while (!localRegisteredClientOnServer1.getWispers().isEmpty()) {
/* 388 */           localObject = (ServerMessage)localRegisteredClientOnServer1.getWispers().remove(0);
/* 389 */           RegisteredClientOnServer localRegisteredClientOnServer2 = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(((ServerMessage)localObject).receiverPlayerId));
/* 390 */           localObject = "[" + ((ServerMessage)localObject).prefix + "][" + (localRegisteredClientOnServer2 != null ? localRegisteredClientOnServer2.getPlayerName() : "unknown") + "] " + ((ServerMessage)localObject).message;
/* 391 */           localObject = new RemoteString((String)localObject, getNetworkObject());
/* 392 */           this.networkChat.chatServerLogBuffer.add((Streamable)localObject);
/* 393 */           System.err.println("[CHAT][WISPER] " + this.state + " (" + this + "): " + localObject);
/*     */         }
/*     */       }
/* 396 */       System.err.println("[SERVER][ERROR] could not wisper. client not found: " + getOwnerStateId());
/*     */     }
/*     */ 
/* 400 */     for (int j = 0; j < this.chatLogToSend.size(); j++) {
/* 401 */       localObject = new RemoteString((String)this.chatLogToSend.get(j), getNetworkObject());
/* 402 */       this.networkChat.chatLogBuffer.add((Streamable)localObject);
/* 403 */       System.err.println("[CHAT] " + this.state + " (" + this + "): " + (String)((RemoteString)localObject).get());
/*     */     }
/* 405 */     for (j = 0; j < this.wisperLogToSend.size(); j++) {
/* 406 */       (
/* 407 */         localObject = new RemoteStringArray(3, getNetworkObject()))
/* 407 */         .set(0, ((ChatSystem.Wisper)this.wisperLogToSend.get(j)).player);
/* 408 */       ((RemoteStringArray)localObject).set(1, ((ChatSystem.Wisper)this.wisperLogToSend.get(j)).message);
/* 409 */       ((RemoteStringArray)localObject).set(2, String.valueOf(((ChatSystem.Wisper)this.wisperLogToSend.get(j)).type));
/*     */ 
/* 411 */       this.networkChat.chatWisperBuffer.add((RemoteArray)localObject);
/* 412 */       System.err.println("[CHAT]" + this.state + " (" + this + "): " + localObject);
/*     */     }
/* 414 */     this.wisperLogToSend.clear();
/* 415 */     this.chatLogToSend.clear();
/*     */   }
/*     */ 
/*     */   public void destroyPersistent()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isMarkedForPermanentDelete()
/*     */   {
/* 426 */     return false;
/*     */   }
/*     */ 
/*     */   public void markedForPermanentDelete(boolean paramBoolean)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isUpdatable()
/*     */   {
/* 437 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.ChatSystem
 * JD-Core Version:    0.6.2
 */