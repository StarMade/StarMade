/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Observable;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import org.schema.schine.network.IdGen;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.NetworkStatus;
/*     */ import org.schema.schine.network.commands.LoginRequest;
/*     */ 
/*     */ public abstract class ServerState extends Observable
/*     */   implements ServerStateInterface
/*     */ {
/*     */   public static int entityCount;
/*     */   public static boolean shutdown;
/*     */   private NetworkStateContainer stateContainer;
/*     */   private final HashMap clients;
/*     */   private ServerControllerInterface controller;
/*     */   private boolean paused;
/*     */   private NetworkStatus networkStatus;
/*  29 */   private final ArrayList toBroadCastMessages = new ArrayList();
/*     */ 
/*  31 */   private final ThreadPoolExecutor theadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
/*  32 */   private final ServerEntityWriterThread threadQueue = new ServerEntityWriterThread();
/*     */   private short updateNumber;
/*     */   private boolean debugBigChunk;
/*  37 */   private final ArrayList loginRequests = new ArrayList();
/*     */ 
/*     */   public ServerState() {
/*  40 */     this.stateContainer = new NetworkStateContainer(false);
/*  41 */     this.networkStatus = new NetworkStatus();
/*  42 */     this.clients = new HashMap();
/*  43 */     this.threadQueue.start();
/*     */   }
/*     */ 
/*     */   public void addLoginRequest(LoginRequest paramLoginRequest)
/*     */   {
/*  54 */     getLoginRequests().add(paramLoginRequest);
/*     */   }
/*     */ 
/*     */   public void handleLoginReuests()
/*     */   {
/*  59 */     while (!this.loginRequests.isEmpty())
/*     */     {
/*     */       LoginRequest localLoginRequest;
/*  60 */       (
/*  62 */         localLoginRequest = (LoginRequest)this.loginRequests.remove(0))
/*  62 */         .prepare();
/*     */ 
/*  64 */       this.theadPool.execute(localLoginRequest);
/*     */     }
/*     */   }
/*     */ 
/*     */   public HashMap getClients()
/*     */   {
/*  75 */     return this.clients;
/*     */   }
/*     */ 
/*     */   public ServerControllerInterface getController()
/*     */   {
/*  83 */     return this.controller;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  88 */     return 0;
/*     */   }
/*     */ 
/*     */   public NetworkStateContainer getLocalAndRemoteObjectContainer() {
/*  92 */     return this.stateContainer;
/*     */   }
/*     */ 
/*     */   public NetworkStatus getNetworkStatus() {
/*  96 */     return this.networkStatus;
/*     */   }
/*     */ 
/*     */   public int getNextFreeObjectId() {
/* 100 */     return IdGen.getFreeObjectId(1);
/*     */   }
/*     */ 
/*     */   public ThreadPoolExecutor getThreadPool()
/*     */   {
/* 107 */     return this.theadPool;
/*     */   }
/*     */ 
/*     */   public ArrayList getToBroadCastMessages()
/*     */   {
/* 113 */     return this.toBroadCastMessages;
/*     */   }
/*     */ 
/*     */   public short getUpdateNumber() {
/* 117 */     return this.updateNumber;
/*     */   }
/*     */ 
/*     */   public void incUpdateNumber()
/*     */   {
/* 122 */     this.updateNumber = ((short)(this.updateNumber + 1));
/*     */   }
/*     */ 
/*     */   public boolean isPaused() {
/* 126 */     return this.paused;
/*     */   }
/*     */ 
/*     */   public boolean isReadingBigChunk()
/*     */   {
/* 135 */     return this.debugBigChunk;
/*     */   }
/*     */ 
/*     */   public boolean isReady()
/*     */   {
/* 140 */     return this.controller.isListenting();
/*     */   }
/*     */ 
/*     */   public void setController(ServerControllerInterface paramServerControllerInterface)
/*     */   {
/* 148 */     this.controller = paramServerControllerInterface;
/*     */   }
/*     */ 
/*     */   public void setPaused(boolean paramBoolean)
/*     */   {
/* 153 */     this.paused = paramBoolean;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 159 */     return "Server(" + getId() + ")";
/*     */   }
/*     */ 
/*     */   public abstract int getClientIdByName(String paramString);
/*     */ 
/*     */   public int getServerTimeDifference()
/*     */   {
/* 173 */     return 0;
/*     */   }
/*     */ 
/*     */   public ServerEntityWriterThread getThreadQueue()
/*     */   {
/* 182 */     return this.threadQueue;
/*     */   }
/*     */ 
/*     */   public ArrayList getLoginRequests()
/*     */   {
/* 191 */     return this.loginRequests;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerState
 * JD-Core Version:    0.6.2
 */