/*     */ package org.schema.schine.network.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Observable;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import org.schema.schine.network.ChatSystem;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.NetworkStatus;
/*     */ import yp;
/*     */ 
/*     */ public abstract class ClientState extends Observable
/*     */   implements ClientStateInterface
/*     */ {
/*     */   public static boolean loginFailed;
/*     */   private final NetworkStateContainer networkStateContainer;
/*     */   private final NetworkStateContainer privateNetworkStateContainer;
/*  27 */   private Map requestAnswers = new HashMap();
/*     */   private long ping;
/*     */   private boolean ready;
/*     */   private boolean synchronizedFlag;
/*     */   private boolean synchronizing;
/*  32 */   private int id = -4242;
/*     */   private NetworkStatus networkStatus;
/*     */   private int idStartRange;
/*  35 */   private Integer objectIdPointer = new Integer(0);
/*  36 */   public static final Integer NEW_ID_RANGE = Integer.valueOf(100);
/*     */   private final ArrayList mouseEvents;
/*     */   private ClientCommunicator clientConnection;
/*  39 */   public Object updateLock = new Object();
/*     */   private yp dragging;
/*     */   private boolean debugBigChunk;
/*  42 */   private final ThreadPoolExecutor theadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
/*     */   private long serverTimeOnLogin;
/*     */   private int serverTimeDifference;
/*     */   private long lastDeactivatedMenu;
/*     */   private short updateNumber;
/*  47 */   private final GUICallbackController guiCallbackController = new GUICallbackController();
/*     */   public static float serverVersion;
/*     */ 
/*     */   public void setServerVersion(float paramFloat)
/*     */   {
/*  55 */     serverVersion = paramFloat;
/*     */   }
/*     */   public ClientState() {
/*  58 */     this.networkStateContainer = new NetworkStateContainer(false);
/*  59 */     this.privateNetworkStateContainer = new NetworkStateContainer(true);
/*  60 */     this.networkStatus = new NetworkStatus();
/*  61 */     this.mouseEvents = new ArrayList();
/*     */   }
/*     */ 
/*     */   public void arrivedReturn(short paramShort, Object[] paramArrayOfObject)
/*     */   {
/*  68 */     this.requestAnswers.put(Short.valueOf(paramShort), paramArrayOfObject);
/*     */   }
/*     */ 
/*     */   public void disconnect() {
/*  72 */     getProcessor().closeSocket();
/*     */   }
/*     */ 
/*     */   public abstract void exit();
/*     */ 
/*     */   public yp getDragging()
/*     */   {
/*  84 */     return this.dragging;
/*     */   }
/*     */ 
/*     */   public GUICallbackController getGuiCallbackController()
/*     */   {
/*  92 */     return this.guiCallbackController;
/*     */   }
/*     */ 
/*     */   public int getId() {
/*  96 */     return this.id;
/*     */   }
/*     */ 
/*     */   public int getIdStartRange()
/*     */   {
/* 104 */     return this.idStartRange;
/*     */   }
/*     */ 
/*     */   public long getLastDeactivatedMenu()
/*     */   {
/* 111 */     return this.lastDeactivatedMenu;
/*     */   }
/*     */ 
/*     */   public NetworkStateContainer getLocalAndRemoteObjectContainer() {
/* 115 */     return this.networkStateContainer;
/*     */   }
/*     */ 
/*     */   public ArrayList getMouseEvents()
/*     */   {
/* 122 */     return this.mouseEvents;
/*     */   }
/*     */ 
/*     */   public NetworkStatus getNetworkStatus()
/*     */   {
/* 130 */     return this.networkStatus;
/*     */   }
/*     */ 
/*     */   public int getNextFreeObjectId()
/*     */   {
/* 138 */     long l = System.currentTimeMillis();
/* 139 */     synchronized (this.objectIdPointer) {
/* 140 */       assert (this.objectIdPointer.intValue() <= this.idStartRange + NEW_ID_RANGE.intValue()) : "[CRITICAL] cannot garanty unique ids";
/* 141 */       if ((this.objectIdPointer.intValue() <= 0) || (this.objectIdPointer.intValue() == this.idStartRange + NEW_ID_RANGE.intValue())) {
/*     */         try {
/* 143 */           getController().aquireFreeIds();
/* 144 */           this.objectIdPointer = Integer.valueOf(this.idStartRange);
/*     */         }
/*     */         catch (IOException localIOException)
/*     */         {
/* 149 */           localIOException.printStackTrace();
/*     */         }
/*     */         catch (InterruptedException localInterruptedException)
/*     */         {
/* 149 */           localInterruptedException.printStackTrace();
/*     */         }
/*     */       }
/*     */ 
/* 151 */       this.objectIdPointer = Integer.valueOf(this.objectIdPointer.intValue() + 1);
/*     */     }
/* 153 */     System.err.println("[CLIENT] ID RANGE AQUIRE TOOK " + (System.currentTimeMillis() - localObject1));
/* 154 */     return this.objectIdPointer.intValue();
/*     */   }
/*     */ 
/*     */   public long getPing()
/*     */   {
/* 161 */     return this.ping;
/*     */   }
/*     */ 
/*     */   public NetworkStateContainer getPrivateLocalAndRemoteObjectContainer()
/*     */   {
/* 168 */     return this.privateNetworkStateContainer;
/*     */   }
/*     */ 
/*     */   public NetworkProcessor getProcessor()
/*     */   {
/* 173 */     return this.clientConnection.getClientProcessor();
/*     */   }
/*     */ 
/*     */   public Object[] getReturn(short paramShort) {
/* 177 */     return (Object[])this.requestAnswers.remove(Short.valueOf(paramShort));
/*     */   }
/*     */ 
/*     */   public int getServerTimeDifference()
/*     */   {
/* 184 */     return this.serverTimeDifference;
/*     */   }
/*     */ 
/*     */   public long getServerTimeOnLogin()
/*     */   {
/* 192 */     return this.serverTimeOnLogin;
/*     */   }
/*     */ 
/*     */   public ThreadPoolExecutor getThreadPool()
/*     */   {
/* 201 */     return this.theadPool;
/*     */   }
/*     */ 
/*     */   public short getUpdateNumber()
/*     */   {
/* 210 */     return this.updateNumber;
/*     */   }
/*     */ 
/*     */   public abstract List getVisibleChatLog();
/*     */ 
/*     */   public abstract List getGeneralChatLog();
/*     */ 
/*     */   public void incUpdateNumber()
/*     */   {
/* 220 */     this.updateNumber = ((short)(this.updateNumber + 1));
/*     */   }
/*     */ 
/*     */   public boolean isReadingBigChunk()
/*     */   {
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isReady()
/*     */   {
/* 239 */     return this.ready;
/*     */   }
/*     */ 
/*     */   public boolean isSynchronized()
/*     */   {
/* 245 */     return this.synchronizedFlag;
/*     */   }
/*     */ 
/*     */   public boolean isSynchronizing()
/*     */   {
/* 252 */     return this.synchronizing;
/*     */   }
/*     */ 
/*     */   public void println(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setClientConnection(ClientCommunicator paramClientCommunicator)
/*     */   {
/* 265 */     this.clientConnection = paramClientCommunicator;
/*     */   }
/*     */ 
/*     */   public void setDebugBigChunk(boolean paramBoolean)
/*     */   {
/* 274 */     this.debugBigChunk = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setDragging(yp paramyp)
/*     */   {
/* 282 */     this.dragging = paramyp;
/*     */   }
/*     */ 
/*     */   public void setId(int paramInt)
/*     */   {
/* 288 */     this.id = paramInt;
/*     */   }
/*     */ 
/*     */   public void setIdStartRange(int paramInt)
/*     */   {
/* 300 */     this.idStartRange = paramInt;
/*     */   }
/*     */ 
/*     */   public void setLastDeactivatedMenu(long paramLong) {
/* 304 */     this.lastDeactivatedMenu = paramLong;
/*     */   }
/*     */ 
/*     */   public void setPing(long paramLong)
/*     */   {
/* 312 */     this.ping = paramLong;
/*     */   }
/*     */ 
/*     */   public void setReady(boolean paramBoolean)
/*     */   {
/* 319 */     this.ready = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setServerTimeOnLogin(long paramLong)
/*     */   {
/* 327 */     this.serverTimeOnLogin = paramLong;
/* 328 */     this.serverTimeDifference = ((int)(paramLong - System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   public void setSynchronized(boolean paramBoolean) {
/* 332 */     this.synchronizedFlag = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setSynchronizing(boolean paramBoolean)
/*     */   {
/* 341 */     this.synchronizing = paramBoolean;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 345 */     return "Client(" + getId() + ")";
/*     */   }
/*     */ 
/*     */   public void chatUpdate(ChatSystem paramChatSystem)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientState
 * JD-Core Version:    0.6.2
 */