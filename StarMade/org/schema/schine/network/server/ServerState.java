/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Observable;
/*   6:    */import java.util.concurrent.Executors;
/*   7:    */import java.util.concurrent.ThreadPoolExecutor;
/*   8:    */import org.schema.schine.network.IdGen;
/*   9:    */import org.schema.schine.network.NetworkStateContainer;
/*  10:    */import org.schema.schine.network.NetworkStatus;
/*  11:    */import org.schema.schine.network.commands.LoginRequest;
/*  12:    */
/*  18:    */public abstract class ServerState
/*  19:    */  extends Observable
/*  20:    */  implements ServerStateInterface
/*  21:    */{
/*  22:    */  public static int entityCount;
/*  23:    */  public static boolean shutdown;
/*  24:    */  private NetworkStateContainer stateContainer;
/*  25:    */  private final HashMap clients;
/*  26:    */  private ServerControllerInterface controller;
/*  27:    */  private boolean paused;
/*  28:    */  private NetworkStatus networkStatus;
/*  29: 29 */  private final ArrayList toBroadCastMessages = new ArrayList();
/*  30:    */  
/*  31: 31 */  private final ThreadPoolExecutor theadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
/*  32: 32 */  private final ServerEntityWriterThread threadQueue = new ServerEntityWriterThread();
/*  33:    */  
/*  34:    */  private short updateNumber;
/*  35:    */  
/*  36:    */  private boolean debugBigChunk;
/*  37: 37 */  private final ArrayList loginRequests = new ArrayList();
/*  38:    */  
/*  39:    */  public ServerState() {
/*  40: 40 */    this.stateContainer = new NetworkStateContainer(false);
/*  41: 41 */    this.networkStatus = new NetworkStatus();
/*  42: 42 */    this.clients = new HashMap();
/*  43: 43 */    this.threadQueue.start();
/*  44:    */  }
/*  45:    */  
/*  52:    */  public void addLoginRequest(LoginRequest paramLoginRequest)
/*  53:    */  {
/*  54: 54 */    getLoginRequests().add(paramLoginRequest);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public void handleLoginReuests()
/*  58:    */  {
/*  59: 59 */    while (!this.loginRequests.isEmpty())
/*  60:    */    {
/*  61:    */      LoginRequest localLoginRequest;
/*  62: 62 */      (localLoginRequest = (LoginRequest)this.loginRequests.remove(0)).prepare();
/*  63:    */      
/*  64: 64 */      this.theadPool.execute(localLoginRequest);
/*  65:    */    }
/*  66:    */  }
/*  67:    */  
/*  73:    */  public HashMap getClients()
/*  74:    */  {
/*  75: 75 */    return this.clients;
/*  76:    */  }
/*  77:    */  
/*  81:    */  public ServerControllerInterface getController()
/*  82:    */  {
/*  83: 83 */    return this.controller;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public int getId()
/*  87:    */  {
/*  88: 88 */    return 0;
/*  89:    */  }
/*  90:    */  
/*  91:    */  public NetworkStateContainer getLocalAndRemoteObjectContainer() {
/*  92: 92 */    return this.stateContainer;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public NetworkStatus getNetworkStatus() {
/*  96: 96 */    return this.networkStatus;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public int getNextFreeObjectId() {
/* 100:100 */    return IdGen.getFreeObjectId(1);
/* 101:    */  }
/* 102:    */  
/* 105:    */  public ThreadPoolExecutor getThreadPool()
/* 106:    */  {
/* 107:107 */    return this.theadPool;
/* 108:    */  }
/* 109:    */  
/* 111:    */  public ArrayList getToBroadCastMessages()
/* 112:    */  {
/* 113:113 */    return this.toBroadCastMessages;
/* 114:    */  }
/* 115:    */  
/* 116:    */  public short getUpdateNumber() {
/* 117:117 */    return this.updateNumber;
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void incUpdateNumber()
/* 121:    */  {
/* 122:122 */    this.updateNumber = ((short)(this.updateNumber + 1));
/* 123:    */  }
/* 124:    */  
/* 125:    */  public boolean isPaused() {
/* 126:126 */    return this.paused;
/* 127:    */  }
/* 128:    */  
/* 133:    */  public boolean isReadingBigChunk()
/* 134:    */  {
/* 135:135 */    return this.debugBigChunk;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public boolean isReady()
/* 139:    */  {
/* 140:140 */    return this.controller.isListenting();
/* 141:    */  }
/* 142:    */  
/* 146:    */  public void setController(ServerControllerInterface paramServerControllerInterface)
/* 147:    */  {
/* 148:148 */    this.controller = paramServerControllerInterface;
/* 149:    */  }
/* 150:    */  
/* 151:    */  public void setPaused(boolean paramBoolean)
/* 152:    */  {
/* 153:153 */    this.paused = paramBoolean;
/* 154:    */  }
/* 155:    */  
/* 157:    */  public String toString()
/* 158:    */  {
/* 159:159 */    return "Server(" + getId() + ")";
/* 160:    */  }
/* 161:    */  
/* 166:    */  public abstract int getClientIdByName(String paramString);
/* 167:    */  
/* 171:    */  public int getServerTimeDifference()
/* 172:    */  {
/* 173:173 */    return 0;
/* 174:    */  }
/* 175:    */  
/* 180:    */  public ServerEntityWriterThread getThreadQueue()
/* 181:    */  {
/* 182:182 */    return this.threadQueue;
/* 183:    */  }
/* 184:    */  
/* 189:    */  public ArrayList getLoginRequests()
/* 190:    */  {
/* 191:191 */    return this.loginRequests;
/* 192:    */  }
/* 193:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */