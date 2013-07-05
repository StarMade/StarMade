/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import java.util.Set;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.CommandMap;
/*     */ import org.schema.schine.network.Header;
/*     */ import org.schema.schine.network.NetUtil;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.commands.LogoutClient;
/*     */ import org.schema.schine.network.commands.RequestSynchronizeAll;
/*     */ import org.schema.schine.network.commands.Synchronize;
/*     */ import org.schema.schine.network.commands.SynchronizePrivateChannel;
/*     */ import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*     */ import org.schema.schine.network.synchronization.SynchronizationSender;
/*     */ import xq;
/*     */ 
/*     */ public abstract class ServerController extends Observable
/*     */   implements Runnable, Observer, ServerControllerInterface
/*     */ {
/*     */   private ServerState serverState;
/*     */   private ServerListener serverListener;
/*  33 */   private xq timer = new xq();
/*     */   private DataOutputStream dataOut;
/*  35 */   private final IntOpenHashSet delHelper = new IntOpenHashSet();
/*  36 */   public static int port = 4242;
/*     */ 
/*  38 */   private ByteArrayOutputStream byteOutPut = new ByteArrayOutputStream(1048576);
/*     */ 
/*  40 */   protected HashSet toRemoveClients = new HashSet();
/*     */ 
/*     */   public ServerController(ServerState paramServerState)
/*     */   {
/*  44 */     setServerState(paramServerState);
/*  45 */     paramServerState.setController(this);
/*  46 */     this.dataOut = new DataOutputStream(this.byteOutPut);
/*     */ 
/*  48 */     Runtime.getRuntime().addShutdownHook(new ServerController.1(this));
/*     */   }
/*     */ 
/*     */   public void broadcastMessage(String paramString, int paramInt)
/*     */   {
/*  66 */     synchronized (this.serverState.getToBroadCastMessages()) {
/*  67 */       this.serverState.getToBroadCastMessages().add(new ServerMessage(paramString, paramInt));
/*  68 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void checkIfClientsNeedResynch(Set paramSet) {
/*  73 */     for (paramSet = paramSet.iterator(); paramSet.hasNext(); )
/*  74 */       synchronized ((
/*  74 */         localRegisteredClientOnServer = (RegisteredClientOnServer)paramSet.next())
/*  74 */         .getProcessor().getLock())
/*     */       {
/*     */         RegisteredClientOnServer localRegisteredClientOnServer;
/*  75 */         if (localRegisteredClientOnServer.getProcessor().isAlive())
/*     */         {
/*     */           try
/*     */           {
/*  80 */             if (localRegisteredClientOnServer.needsSynch()) {
/*  81 */               long l = System.currentTimeMillis();
/*  82 */               System.err.println("[SERVER] (client needs synch) SENDING SYNCHALL TO " + localRegisteredClientOnServer);
/*  83 */               RequestSynchronizeAll.executeSynchAll(this.serverState, localRegisteredClientOnServer.getProcessor());
/*     */ 
/*  85 */               localRegisteredClientOnServer.wasFullSynched = true;
/*  86 */               localRegisteredClientOnServer.flagSynch((short)-32768);
/*  87 */               System.err.println("[SERVER] SYNCHALL TO " + localRegisteredClientOnServer + " took: " + (System.currentTimeMillis() - l));
/*     */             }
/*     */           }
/*     */           catch (IOException localIOException) {
/*  91 */             localIOException.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public ServerState getServerState()
/*     */   {
/* 100 */     return this.serverState;
/*     */   }
/*     */ 
/*     */   public xq getTimer()
/*     */   {
/* 107 */     return this.timer;
/*     */   }
/*     */ 
/*     */   public abstract void initializeServerState();
/*     */ 
/*     */   public boolean isBanned(RegisteredClientOnServer paramRegisteredClientOnServer)
/*     */   {
/* 114 */     return false;
/*     */   }
/*     */   public boolean isWhiteListed(RegisteredClientOnServer paramRegisteredClientOnServer) {
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isListenting() {
/* 121 */     return this.serverListener.isListening();
/*     */   }
/*     */ 
/*     */   public abstract int onLoggedIn(RegisteredClientOnServer paramRegisteredClientOnServer);
/*     */ 
/*     */   public abstract void onLoggedout(RegisteredClientOnServer paramRegisteredClientOnServer);
/*     */ 
/*     */   protected abstract void onShutDown();
/*     */ 
/*     */   public int registerClient(RegisteredClientOnServer paramRegisteredClientOnServer, float paramFloat)
/*     */   {
/* 135 */     if (paramFloat != getServerState().getVersion()) {
/* 136 */       return -5;
/*     */     }
/*     */ 
/* 140 */     if (!isAdmin(paramRegisteredClientOnServer))
/*     */     {
/* 141 */       if (this.serverState.getClients().size() >= this.serverState.getMaxClients()) {
/* 142 */         return -4;
/*     */       }
/* 144 */       if (isBanned(paramRegisteredClientOnServer)) {
/* 145 */         System.err.println("[SERVER][LOGIN] Denying banned user: " + paramRegisteredClientOnServer);
/* 146 */         return -6;
/*     */       }
/* 148 */       if (!isWhiteListed(paramRegisteredClientOnServer)) {
/* 149 */         System.err.println("[SERVER][LOGIN] Denying not white listed user: " + paramRegisteredClientOnServer);
/* 150 */         return -8;
/*     */       }
/*     */     }
/* 153 */     paramFloat = paramRegisteredClientOnServer.getPlayerName();
/*     */     Iterator localIterator;
/* 154 */     synchronized (this.serverState.getClients()) {
/* 155 */       for (localIterator = this.serverState.getClients().values().iterator(); localIterator.hasNext(); )
/* 156 */         if (((RegisteredClientOnServer)localIterator.next())
/* 156 */           .getPlayerName().equals(paramFloat))
/* 157 */           return -2;
/*     */     }
/*     */     int i;
/* 162 */     if ((
/* 162 */       i = onLoggedIn(paramRegisteredClientOnServer)) == 0)
/*     */     {
/* 163 */       synchronized (getServerState().getClients()) {
/* 164 */         getServerState().getClients().put(Integer.valueOf(paramRegisteredClientOnServer.getId()), paramRegisteredClientOnServer);
/*     */       }
/* 166 */       setChanged();
/* 167 */       notifyObservers();
/*     */     }
/* 169 */     return i;
/*     */   }
/*     */ 
/*     */   protected abstract boolean isAdmin(RegisteredClientOnServer paramRegisteredClientOnServer);
/*     */ 
/*     */   public void run()
/*     */   {
/* 180 */     System.out.println("[SERVERCONTROLLER][INIT] SERVER STARTED UPDATING");
/*     */     try {
/* 182 */       Thread.sleep(100L);
/* 183 */       this.timer.b();
/*     */       while (true) {
/* 185 */         long l1 = System.currentTimeMillis();
/* 186 */         if (!this.serverState.isPaused()) {
/* 187 */           update(this.timer);
/*     */ 
/* 191 */           long l2 = System.currentTimeMillis() - l1;
/*     */           long l3;
/* 193 */           if ((
/* 193 */             l3 = 30L - l2) > 
/* 193 */             0L) {
/* 194 */             Thread.sleep(l3);
/*     */           }
/* 196 */           ServerState.entityCount = getServerState().getLocalAndRemoteObjectContainer().getLocalObjects().size();
/*     */         } else {
/* 198 */           Thread.sleep(500L);
/*     */         }
/* 200 */         this.timer.b();
/*     */       }
/*     */     } catch (RuntimeException localRuntimeException) {
/* 203 */       (
/* 204 */         localObject = localRuntimeException)
/* 204 */         .printStackTrace();
/* 205 */       System.err.println("Exiting because of exception " + localObject);
/* 206 */       System.exit(0);
/*     */       return;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */       Object localObject;
/* 207 */       (
/* 208 */         localObject = 
/* 211 */         localException).printStackTrace();
/* 209 */       System.err.println("Exiting because of exception " + localObject);
/* 210 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendLogout(int paramInt, String paramString)
/*     */   {
/* 223 */     if ((
/* 223 */       paramInt = (RegisteredClientOnServer)this.serverState.getClients().get(Integer.valueOf(paramInt))) != null)
/*     */     {
/* 224 */       System.err.println("[SERVER] SENDING ACTIVE LOGOUT TO CLIENT " + paramInt);
/* 225 */       paramInt.getProcessor().serverCommand(NetUtil.commands.getByClass(LogoutClient.class).getId(), 0, new Object[] { paramString });
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void sendMessages(Set paramSet)
/*     */   {
/* 236 */     long l1 = System.currentTimeMillis();
/* 237 */     while (getServerState().getToBroadCastMessages().size() > 0)
/*     */     {
/* 239 */       ServerMessage localServerMessage = (ServerMessage)getServerState().getToBroadCastMessages().remove(0);
/*     */       try {
/* 241 */         for (localIterator = paramSet.iterator(); localIterator.hasNext(); ) ((RegisteredClientOnServer)localIterator.next())
/* 242 */             .serverMessage(localServerMessage);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */         Iterator localIterator;
/* 246 */         localException.printStackTrace();
/*     */       }
/*     */     }
/*     */     long l2;
/* 251 */     if ((
/* 251 */       l2 = System.currentTimeMillis() - l1) > 
/* 251 */       10L)
/* 252 */       System.err.println("[SERVER][UPDATE] WARNING: sendMessages update took " + l2);
/*     */   }
/*     */ 
/*     */   public void setServerState(ServerState paramServerState)
/*     */   {
/* 261 */     this.serverState = paramServerState;
/*     */   }
/*     */ 
/*     */   public void setTimer(xq paramxq)
/*     */   {
/* 268 */     this.timer = paramxq;
/*     */   }
/*     */ 
/*     */   public void startServerAndListen() {
/* 272 */     this.serverListener = new ServerListener("localhost", port, this.serverState);
/* 273 */     this.serverListener.addObserver(this);
/* 274 */     initializeServerState();
/* 275 */     new Thread(this.serverListener, "ServerListener")
/* 276 */       .start();
/* 277 */     new Thread(this, "ServerController")
/* 278 */       .start();
/*     */   }
/*     */ 
/*     */   public void synchronize(Set paramSet)
/*     */   {
/* 284 */     checkIfClientsNeedResynch(paramSet);
/*     */ 
/* 296 */     if (SynchronizationSender.encodeNetworkObjects(getServerState().getLocalAndRemoteObjectContainer(), this.serverState, this.dataOut, false) == 
/* 296 */       1)
/*     */     {
/* 298 */       for (Iterator localIterator = paramSet.iterator(); localIterator.hasNext(); )
/*     */       {
/*     */         RegisteredClientOnServer localRegisteredClientOnServer;
/* 299 */         if ((
/* 299 */           localRegisteredClientOnServer = (RegisteredClientOnServer)localIterator.next()).wasFullSynched)
/*     */         {
/* 300 */           localRegisteredClientOnServer.wasFullSynched = false;
/*     */         }
/*     */ 
/* 306 */         synchronized (localRegisteredClientOnServer.getProcessor().getLock()) {
/* 307 */           if (localRegisteredClientOnServer.getProcessor().isAlive())
/*     */           {
/*     */             try
/*     */             {
/* 313 */               new Header(Synchronize.class, 0, localRegisteredClientOnServer.getId(), (short)-32768, (byte)123)
/* 319 */                 .write(localRegisteredClientOnServer.getProcessor().getOut());
/*     */ 
/* 322 */               this.byteOutPut.writeTo(localRegisteredClientOnServer.getProcessor().getOut());
/* 323 */               int i = localRegisteredClientOnServer.getProcessor().getCurrentSize();
/* 324 */               long l1 = System.currentTimeMillis();
/*     */ 
/* 326 */               localRegisteredClientOnServer.getProcessor().flushDoubleOutBuffer();
/*     */               long l2;
/* 328 */               if ((
/* 328 */                 l2 = System.currentTimeMillis() - l1) > 
/* 328 */                 20L) {
/* 329 */                 System.err.println("[WARNING][SERVER] Exception: synchronized flush took " + l2 + " ms, size: " + i + " bytes");
/*     */               }
/*     */ 
/*     */             }
/*     */             catch (IOException localIOException)
/*     */             {
/* 336 */               localIOException.printStackTrace();
/*     */ 
/* 334 */               System.err.println("[WARNING] SERVER CANNOT REACH " + localRegisteredClientOnServer + " SKIPPING THIS CLIENT'S UPDATE");
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 340 */       SynchronizationReceiver.handleDeleted(this.serverState.getLocalAndRemoteObjectContainer(), this.serverState, this.delHelper);
/*     */     }
/*     */ 
/* 346 */     this.byteOutPut.reset();
/*     */ 
/* 348 */     synchronizePrivate(paramSet);
/*     */ 
/* 351 */     getServerState().getLocalAndRemoteObjectContainer().checkGhostObjects();
/*     */   }
/*     */ 
/*     */   public void synchronizePrivate(Set paramSet)
/*     */   {
/* 356 */     for (paramSet = paramSet.iterator(); paramSet.hasNext(); )
/*     */     {
/*     */       RegisteredClientOnServer localRegisteredClientOnServer;
/* 361 */       if (SynchronizationSender.encodeNetworkObjects((
/* 358 */         localRegisteredClientOnServer = (RegisteredClientOnServer)paramSet.next())
/* 358 */         .getLocalAndRemoteObjectContainer(), this.serverState, this.dataOut, false) == 
/* 361 */         1) {
/* 362 */         synchronized (localRegisteredClientOnServer.getProcessor().getLock()) {
/* 363 */           if (!localRegisteredClientOnServer.getProcessor().isAlive()) {
/*     */             continue;
/*     */           }
/*     */           try
/*     */           {
/* 368 */             new Header(SynchronizePrivateChannel.class, 0, localRegisteredClientOnServer.getId(), (short)-32768, (byte)123)
/* 374 */               .write(localRegisteredClientOnServer.getProcessor().getOut());
/*     */ 
/* 377 */             int i = localRegisteredClientOnServer.getProcessor().getCurrentSize();
/* 378 */             long l1 = System.currentTimeMillis();
/*     */ 
/* 381 */             this.byteOutPut.writeTo(localRegisteredClientOnServer.getProcessor().getOut());
/*     */ 
/* 384 */             localRegisteredClientOnServer.getProcessor().flushDoubleOutBuffer();
/*     */             long l2;
/* 386 */             if ((
/* 386 */               l2 = System.currentTimeMillis() - l1) > 
/* 386 */               10L) {
/* 387 */               System.err.println("[WARNING][SERVER] Exception: private synchronized flush took " + l2 + " ms, size " + i);
/*     */             }
/*     */ 
/*     */           }
/*     */           catch (IOException localIOException)
/*     */           {
/* 394 */             localIOException.printStackTrace();
/*     */ 
/* 392 */             System.err.println("[WARNING] SERVER CANNOT REACH " + localRegisteredClientOnServer + " SKIPPING THIS CLIENT'S UPDATE");
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 397 */       SynchronizationReceiver.handleDeleted(localRegisteredClientOnServer.getLocalAndRemoteObjectContainer(), this.serverState, this.delHelper);
/*     */ 
/* 405 */       this.byteOutPut.reset();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void unregister(int paramInt)
/*     */   {
/* 411 */     synchronized (this.toRemoveClients) {
/* 412 */       this.toRemoveClients.add(Integer.valueOf(paramInt));
/* 413 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 421 */     setChanged();
/* 422 */     notifyObservers(paramObject);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerController
 * JD-Core Version:    0.6.2
 */