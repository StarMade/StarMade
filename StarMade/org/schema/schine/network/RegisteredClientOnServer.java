/*     */ package org.schema.schine.network;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.util.ArrayList;
/*     */ import org.schema.schine.network.commands.MessageTo;
/*     */ import org.schema.schine.network.server.ServerMessage;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class RegisteredClientOnServer
/*     */   implements Identifiable, Recipient
/*     */ {
/*     */   private int id;
/*     */   private String playerName;
/*     */   private ServerProcessor serverProcessor;
/*     */   private boolean connected;
/*  74 */   private short needsSynch = -32768;
/*     */   private final NetworkStateContainer localAndRemoteContainer;
/*     */   private final SynchronizationContainerController synchController;
/*     */   private Object player;
/*     */   public boolean wasFullSynched;
/*  83 */   private final ArrayList wispers = new ArrayList();
/*     */ 
/*     */   public RegisteredClientOnServer(int paramInt, String paramString, ServerStateInterface paramServerStateInterface)
/*     */   {
/*  92 */     this.id = paramInt;
/*  93 */     this.playerName = paramString;
/*  94 */     this.connected = true;
/*     */ 
/*  96 */     this.localAndRemoteContainer = new NetworkStateContainer(true);
/*  97 */     this.synchController = new SynchronizationContainerController(this.localAndRemoteContainer, paramServerStateInterface, true);
/*     */   }
/*     */ 
/*     */   public void flagSynch(short paramShort)
/*     */   {
/* 102 */     this.needsSynch = paramShort;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/* 107 */     return this.id;
/*     */   }
/*     */ 
/*     */   public String getIp() {
/* 111 */     return getProcessor().getClientIp().toString().replace("/", "");
/*     */   }
/*     */ 
/*     */   public NetworkStateContainer getLocalAndRemoteObjectContainer() {
/* 115 */     return this.localAndRemoteContainer;
/*     */   }
/*     */ 
/*     */   public String getPlayerName()
/*     */   {
/* 124 */     return this.playerName;
/*     */   }
/*     */ 
/*     */   public Object getPlayerObject()
/*     */   {
/* 130 */     return this.player;
/*     */   }
/*     */ 
/*     */   public ServerProcessor getProcessor()
/*     */   {
/* 141 */     return this.serverProcessor;
/*     */   }
/*     */ 
/*     */   public SynchronizationContainerController getSynchController()
/*     */   {
/* 150 */     return this.synchController;
/*     */   }
/*     */ 
/*     */   public short getSynchPacketId()
/*     */   {
/* 156 */     return this.needsSynch;
/*     */   }
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 164 */     return this.connected;
/*     */   }
/*     */   public boolean needsSynch() {
/* 167 */     return this.needsSynch > -32768;
/*     */   }
/*     */ 
/*     */   public void sendCommand(int paramInt, short paramShort, Class paramClass, Object[] paramArrayOfObject)
/*     */   {
/* 173 */     NetUtil.commands.getByClass(paramClass).writeAndCommitParametriziedCommand(paramArrayOfObject, getId(), paramInt, paramShort, this.serverProcessor);
/*     */   }
/*     */ 
/*     */   public Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object[] paramArrayOfObject)
/*     */   {
/* 181 */     throw new IllegalArgumentException("this moethod is only used: client to server for client requests");
/*     */   }
/*     */ 
/*     */   public void serverMessage(ServerMessage paramServerMessage) {
/* 185 */     System.err.println("[SEND][SERVERMESSAGE] " + paramServerMessage + " to " + this);
/* 186 */     sendCommand(getId(), IdGen.getNewPacketId(), MessageTo.class, new Object[] { "SERVER", paramServerMessage.message, Integer.valueOf(paramServerMessage.type) });
/*     */   }
/*     */   public void serverMessage(String paramString) {
/* 189 */     System.err.println("[SEND][SERVERMESSAGE] " + paramString + " to " + this);
/* 190 */     sendCommand(getId(), IdGen.getNewPacketId(), MessageTo.class, new Object[] { "SERVER", paramString, Integer.valueOf(0) });
/*     */   }
/*     */ 
/*     */   public void setConnected(boolean paramBoolean)
/*     */   {
/* 198 */     this.connected = false;
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 203 */     this.id = paramInt;
/*     */   }
/*     */ 
/*     */   public void setPlayerName(String paramString)
/*     */   {
/* 211 */     this.playerName = paramString;
/*     */   }
/*     */ 
/*     */   public void setPlayerObject(Object paramObject) {
/* 215 */     this.player = paramObject;
/*     */   }
/*     */ 
/*     */   public void setProcessor(ServerProcessor paramServerProcessor)
/*     */   {
/* 223 */     this.serverProcessor = paramServerProcessor;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 231 */     return "RegisteredClient: " + getPlayerName() + " (" + this.id + ") connected: " + this.connected;
/*     */   }
/*     */ 
/*     */   public ArrayList getWispers()
/*     */   {
/* 239 */     return this.wispers;
/*     */   }
/*     */ 
/*     */   public boolean checkConnection() {
/* 243 */     if (!this.connected) {
/* 244 */       return false;
/*     */     }
/* 246 */     if (!getProcessor().isConnectionAlive()) {
/* 247 */       return false;
/*     */     }
/*     */ 
/* 250 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.RegisteredClientOnServer
 * JD-Core Version:    0.6.2
 */