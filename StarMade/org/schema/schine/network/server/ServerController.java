/*   1:    */package org.schema.schine.network.server;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   4:    */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*   5:    */import java.io.ByteArrayOutputStream;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */import java.io.IOException;
/*   8:    */import java.util.ArrayList;
/*   9:    */import java.util.Collection;
/*  10:    */import java.util.HashMap;
/*  11:    */import java.util.HashSet;
/*  12:    */import java.util.Iterator;
/*  13:    */import java.util.Observable;
/*  14:    */import java.util.Observer;
/*  15:    */import java.util.Set;
/*  16:    */import org.schema.schine.network.Command;
/*  17:    */import org.schema.schine.network.CommandMap;
/*  18:    */import org.schema.schine.network.Header;
/*  19:    */import org.schema.schine.network.NetUtil;
/*  20:    */import org.schema.schine.network.NetworkStateContainer;
/*  21:    */import org.schema.schine.network.commands.LogoutClient;
/*  22:    */import org.schema.schine.network.commands.RequestSynchronizeAll;
/*  23:    */import org.schema.schine.network.commands.Synchronize;
/*  24:    */import org.schema.schine.network.commands.SynchronizePrivateChannel;
/*  25:    */import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*  26:    */import org.schema.schine.network.synchronization.SynchronizationSender;
/*  27:    */import xq;
/*  28:    */
/*  29:    */public abstract class ServerController extends Observable implements Runnable, Observer, ServerControllerInterface
/*  30:    */{
/*  31:    */  private ServerState serverState;
/*  32:    */  private ServerListener serverListener;
/*  33: 33 */  private xq timer = new xq();
/*  34:    */  private DataOutputStream dataOut;
/*  35: 35 */  private final IntOpenHashSet delHelper = new IntOpenHashSet();
/*  36: 36 */  public static int port = 4242;
/*  37:    */  
/*  38: 38 */  private ByteArrayOutputStream byteOutPut = new ByteArrayOutputStream(1048576);
/*  39:    */  
/*  40: 40 */  protected HashSet toRemoveClients = new HashSet();
/*  41:    */  
/*  42:    */  public ServerController(ServerState paramServerState)
/*  43:    */  {
/*  44: 44 */    setServerState(paramServerState);
/*  45: 45 */    paramServerState.setController(this);
/*  46: 46 */    this.dataOut = new DataOutputStream(this.byteOutPut);
/*  47:    */    
/*  48: 48 */    Runtime.getRuntime().addShutdownHook(new ServerController.1(this));
/*  49:    */  }
/*  50:    */  
/*  64:    */  public void broadcastMessage(String paramString, int paramInt)
/*  65:    */  {
/*  66: 66 */    synchronized (this.serverState.getToBroadCastMessages()) {
/*  67: 67 */      this.serverState.getToBroadCastMessages().add(new ServerMessage(paramString, paramInt));
/*  68: 68 */      return;
/*  69:    */    }
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void checkIfClientsNeedResynch(Set paramSet) {
/*  73: 73 */    for (paramSet = paramSet.iterator(); paramSet.hasNext();) {
/*  74: 74 */      synchronized ((localRegisteredClientOnServer = (org.schema.schine.network.RegisteredClientOnServer)paramSet.next()).getProcessor().getLock()) { org.schema.schine.network.RegisteredClientOnServer localRegisteredClientOnServer;
/*  75: 75 */        if (localRegisteredClientOnServer.getProcessor().isAlive())
/*  76:    */        {
/*  77:    */          try
/*  78:    */          {
/*  80: 80 */            if (localRegisteredClientOnServer.needsSynch()) {
/*  81: 81 */              long l = System.currentTimeMillis();
/*  82: 82 */              System.err.println("[SERVER] (client needs synch) SENDING SYNCHALL TO " + localRegisteredClientOnServer);
/*  83: 83 */              RequestSynchronizeAll.executeSynchAll(this.serverState, localRegisteredClientOnServer.getProcessor());
/*  84:    */              
/*  85: 85 */              localRegisteredClientOnServer.wasFullSynched = true;
/*  86: 86 */              localRegisteredClientOnServer.flagSynch((short)-32768);
/*  87: 87 */              System.err.println("[SERVER] SYNCHALL TO " + localRegisteredClientOnServer + " took: " + (System.currentTimeMillis() - l));
/*  88:    */            }
/*  89: 89 */          } catch (IOException localIOException) { 
/*  90:    */            
/*  91: 91 */              localIOException;
/*  92:    */          }
/*  93:    */        }
/*  94:    */      }
/*  95:    */    }
/*  96:    */  }
/*  97:    */  
/* 100:    */  public ServerState getServerState()
/* 101:    */  {
/* 102:100 */    return this.serverState;
/* 103:    */  }
/* 104:    */  
/* 107:    */  public xq getTimer()
/* 108:    */  {
/* 109:107 */    return this.timer;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public abstract void initializeServerState();
/* 113:    */  
/* 114:    */  public boolean isBanned(org.schema.schine.network.RegisteredClientOnServer paramRegisteredClientOnServer)
/* 115:    */  {
/* 116:114 */    return false;
/* 117:    */  }
/* 118:    */  
/* 119:117 */  public boolean isWhiteListed(org.schema.schine.network.RegisteredClientOnServer paramRegisteredClientOnServer) { return false; }
/* 120:    */  
/* 121:    */  public boolean isListenting()
/* 122:    */  {
/* 123:121 */    return this.serverListener.isListening();
/* 124:    */  }
/* 125:    */  
/* 127:    */  public abstract int onLoggedIn(org.schema.schine.network.RegisteredClientOnServer paramRegisteredClientOnServer);
/* 128:    */  
/* 130:    */  public abstract void onLoggedout(org.schema.schine.network.RegisteredClientOnServer paramRegisteredClientOnServer);
/* 131:    */  
/* 133:    */  protected abstract void onShutDown();
/* 134:    */  
/* 135:    */  public int registerClient(org.schema.schine.network.RegisteredClientOnServer paramRegisteredClientOnServer, float paramFloat)
/* 136:    */  {
/* 137:135 */    if (paramFloat != getServerState().getVersion()) {
/* 138:136 */      return -5;
/* 139:    */    }
/* 140:    */    
/* 142:140 */    if (!isAdmin(paramRegisteredClientOnServer)) {
/* 143:141 */      if (this.serverState.getClients().size() >= this.serverState.getMaxClients()) {
/* 144:142 */        return -4;
/* 145:    */      }
/* 146:144 */      if (isBanned(paramRegisteredClientOnServer)) {
/* 147:145 */        System.err.println("[SERVER][LOGIN] Denying banned user: " + paramRegisteredClientOnServer);
/* 148:146 */        return -6;
/* 149:    */      }
/* 150:148 */      if (!isWhiteListed(paramRegisteredClientOnServer)) {
/* 151:149 */        System.err.println("[SERVER][LOGIN] Denying not white listed user: " + paramRegisteredClientOnServer);
/* 152:150 */        return -8;
/* 153:    */      }
/* 154:    */    }
/* 155:153 */    paramFloat = paramRegisteredClientOnServer.getPlayerName();
/* 156:154 */    Iterator localIterator; synchronized (this.serverState.getClients()) {
/* 157:155 */      for (localIterator = this.serverState.getClients().values().iterator(); localIterator.hasNext();) {
/* 158:156 */        if (((org.schema.schine.network.RegisteredClientOnServer)localIterator.next()).getPlayerName().equals(paramFloat)) {
/* 159:157 */          return -2;
/* 160:    */        }
/* 161:    */      }
/* 162:    */    }
/* 163:    */    int i;
/* 164:162 */    if ((i = onLoggedIn(paramRegisteredClientOnServer)) == 0) {
/* 165:163 */      synchronized (getServerState().getClients()) {
/* 166:164 */        getServerState().getClients().put(Integer.valueOf(paramRegisteredClientOnServer.getId()), paramRegisteredClientOnServer);
/* 167:    */      }
/* 168:166 */      setChanged();
/* 169:167 */      notifyObservers();
/* 170:    */    }
/* 171:169 */    return i;
/* 172:    */  }
/* 173:    */  
/* 176:    */  protected abstract boolean isAdmin(org.schema.schine.network.RegisteredClientOnServer paramRegisteredClientOnServer);
/* 177:    */  
/* 180:    */  public void run()
/* 181:    */  {
/* 182:180 */    System.out.println("[SERVERCONTROLLER][INIT] SERVER STARTED UPDATING");
/* 183:    */    try {
/* 184:182 */      Thread.sleep(100L);
/* 185:183 */      this.timer.b();
/* 186:    */      for (;;) {
/* 187:185 */        long l1 = System.currentTimeMillis();
/* 188:186 */        if (!this.serverState.isPaused()) {
/* 189:187 */          update(this.timer);
/* 190:    */          
/* 193:191 */          long l2 = System.currentTimeMillis() - l1;
/* 194:    */          long l3;
/* 195:193 */          if ((l3 = 30L - l2) > 0L) {
/* 196:194 */            Thread.sleep(l3);
/* 197:    */          }
/* 198:196 */          ServerState.entityCount = getServerState().getLocalAndRemoteObjectContainer().getLocalObjects().size();
/* 199:    */        } else {
/* 200:198 */          Thread.sleep(500L);
/* 201:    */        }
/* 202:200 */        this.timer.b();
/* 203:    */      }
/* 204:    */      Object localObject;
/* 205:    */      return;
/* 206:204 */    } catch (RuntimeException localRuntimeException) { (localObject = localRuntimeException).printStackTrace();
/* 207:205 */      System.err.println("Exiting because of exception " + localObject);
/* 208:206 */      System.exit(0); return;
/* 209:    */    } catch (Exception localException) {
/* 210:208 */      (localObject = 
/* 211:    */      
/* 213:211 */        localException).printStackTrace();System.err.println("Exiting because of exception " + localObject);System.exit(0);
/* 214:    */    }
/* 215:    */  }
/* 216:    */  
/* 224:    */  public void sendLogout(int paramInt, String paramString)
/* 225:    */  {
/* 226:223 */    if ((paramInt = (org.schema.schine.network.RegisteredClientOnServer)this.serverState.getClients().get(Integer.valueOf(paramInt))) != null) {
/* 227:224 */      System.err.println("[SERVER] SENDING ACTIVE LOGOUT TO CLIENT " + paramInt);
/* 228:225 */      paramInt.getProcessor().serverCommand(NetUtil.commands.getByClass(LogoutClient.class).getId(), 0, new Object[] { paramString });
/* 229:    */    }
/* 230:    */  }
/* 231:    */  
/* 237:    */  protected void sendMessages(Set paramSet)
/* 238:    */  {
/* 239:236 */    long l1 = System.currentTimeMillis();
/* 240:237 */    while (getServerState().getToBroadCastMessages().size() > 0)
/* 241:    */    {
/* 242:239 */      ServerMessage localServerMessage = (ServerMessage)getServerState().getToBroadCastMessages().remove(0);
/* 243:    */      try {
/* 244:241 */        for (localIterator = paramSet.iterator(); localIterator.hasNext();)
/* 245:242 */          ((org.schema.schine.network.RegisteredClientOnServer)localIterator.next()).serverMessage(localServerMessage);
/* 246:    */      } catch (Exception localException) { Iterator localIterator;
/* 247:244 */        
/* 248:    */        
/* 249:246 */          localException;
/* 250:    */      }
/* 251:    */    }
/* 252:    */    
/* 254:    */    long l2;
/* 255:    */    
/* 256:251 */    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 257:252 */      System.err.println("[SERVER][UPDATE] WARNING: sendMessages update took " + l2);
/* 258:    */    }
/* 259:    */  }
/* 260:    */  
/* 264:    */  public void setServerState(ServerState paramServerState)
/* 265:    */  {
/* 266:261 */    this.serverState = paramServerState;
/* 267:    */  }
/* 268:    */  
/* 271:    */  public void setTimer(xq paramxq)
/* 272:    */  {
/* 273:268 */    this.timer = paramxq;
/* 274:    */  }
/* 275:    */  
/* 276:    */  public void startServerAndListen() {
/* 277:272 */    this.serverListener = new ServerListener("localhost", port, this.serverState);
/* 278:273 */    this.serverListener.addObserver(this);
/* 279:274 */    initializeServerState();
/* 280:275 */    new Thread(this.serverListener, "ServerListener")
/* 281:276 */      .start();
/* 282:277 */    new Thread(this, "ServerController")
/* 283:278 */      .start();
/* 284:    */  }
/* 285:    */  
/* 287:    */  public void synchronize(Set paramSet)
/* 288:    */  {
/* 289:284 */    checkIfClientsNeedResynch(paramSet);
/* 290:    */    
/* 301:296 */    if (SynchronizationSender.encodeNetworkObjects(getServerState().getLocalAndRemoteObjectContainer(), this.serverState, this.dataOut, false) == 1)
/* 302:    */    {
/* 303:298 */      for (Iterator localIterator = paramSet.iterator(); localIterator.hasNext();) { org.schema.schine.network.RegisteredClientOnServer localRegisteredClientOnServer;
/* 304:299 */        if ((localRegisteredClientOnServer = (org.schema.schine.network.RegisteredClientOnServer)localIterator.next()).wasFullSynched) {
/* 305:300 */          localRegisteredClientOnServer.wasFullSynched = false;
/* 306:    */        }
/* 307:    */        
/* 311:306 */        synchronized (localRegisteredClientOnServer.getProcessor().getLock()) {
/* 312:307 */          if (localRegisteredClientOnServer.getProcessor().isAlive())
/* 313:    */          {
/* 318:    */            try
/* 319:    */            {
/* 324:319 */              new Header(Synchronize.class, 0, localRegisteredClientOnServer.getId(), (short)-32768, (byte)123).write(localRegisteredClientOnServer.getProcessor().getOut());
/* 325:    */              
/* 327:322 */              this.byteOutPut.writeTo(localRegisteredClientOnServer.getProcessor().getOut());
/* 328:323 */              int i = localRegisteredClientOnServer.getProcessor().getCurrentSize();
/* 329:324 */              long l1 = System.currentTimeMillis();
/* 330:    */              
/* 331:326 */              localRegisteredClientOnServer.getProcessor().flushDoubleOutBuffer();
/* 332:    */              long l2;
/* 333:328 */              if ((l2 = System.currentTimeMillis() - l1) > 20L) {
/* 334:329 */                System.err.println("[WARNING][SERVER] Exception: synchronized flush took " + l2 + " ms, size: " + i + " bytes");
/* 335:    */              }
/* 336:    */            } catch (IOException localIOException) {
/* 337:332 */              
/* 338:    */              
/* 341:336 */                localIOException.printStackTrace();System.err.println("[WARNING] SERVER CANNOT REACH " + localRegisteredClientOnServer + " SKIPPING THIS CLIENT'S UPDATE");
/* 342:    */            } }
/* 343:    */        }
/* 344:    */      }
/* 345:340 */      SynchronizationReceiver.handleDeleted(this.serverState.getLocalAndRemoteObjectContainer(), this.serverState, this.delHelper);
/* 346:    */    }
/* 347:    */    
/* 351:346 */    this.byteOutPut.reset();
/* 352:    */    
/* 353:348 */    synchronizePrivate(paramSet);
/* 354:    */    
/* 356:351 */    getServerState().getLocalAndRemoteObjectContainer().checkGhostObjects();
/* 357:    */  }
/* 358:    */  
/* 359:    */  public void synchronizePrivate(Set paramSet)
/* 360:    */  {
/* 361:356 */    for (paramSet = paramSet.iterator(); paramSet.hasNext();)
/* 362:    */    {
/* 363:    */      org.schema.schine.network.RegisteredClientOnServer localRegisteredClientOnServer;
/* 364:    */      
/* 366:361 */      if (SynchronizationSender.encodeNetworkObjects((localRegisteredClientOnServer = (org.schema.schine.network.RegisteredClientOnServer)paramSet.next()).getLocalAndRemoteObjectContainer(), this.serverState, this.dataOut, false) == 1)
/* 367:362 */        synchronized (localRegisteredClientOnServer.getProcessor().getLock()) {
/* 368:363 */          if (!localRegisteredClientOnServer.getProcessor().isAlive()) {
/* 369:    */            continue;
/* 370:    */          }
/* 371:    */          
/* 377:    */          try
/* 378:    */          {
/* 379:374 */            new Header(SynchronizePrivateChannel.class, 0, localRegisteredClientOnServer.getId(), (short)-32768, (byte)123).write(localRegisteredClientOnServer.getProcessor().getOut());
/* 380:    */            
/* 382:377 */            int i = localRegisteredClientOnServer.getProcessor().getCurrentSize();
/* 383:378 */            long l1 = System.currentTimeMillis();
/* 384:    */            
/* 386:381 */            this.byteOutPut.writeTo(localRegisteredClientOnServer.getProcessor().getOut());
/* 387:    */            
/* 389:384 */            localRegisteredClientOnServer.getProcessor().flushDoubleOutBuffer();
/* 390:    */            long l2;
/* 391:386 */            if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 392:387 */              System.err.println("[WARNING][SERVER] Exception: private synchronized flush took " + l2 + " ms, size " + i);
/* 393:    */            }
/* 394:    */          } catch (IOException localIOException) {
/* 395:390 */            
/* 396:    */            
/* 399:394 */              localIOException.printStackTrace();System.err.println("[WARNING] SERVER CANNOT REACH " + localRegisteredClientOnServer + " SKIPPING THIS CLIENT'S UPDATE");
/* 400:    */          }
/* 401:    */        }
/* 402:397 */      SynchronizationReceiver.handleDeleted(localRegisteredClientOnServer.getLocalAndRemoteObjectContainer(), this.serverState, this.delHelper);
/* 403:    */      
/* 410:405 */      this.byteOutPut.reset();
/* 411:    */    }
/* 412:    */  }
/* 413:    */  
/* 414:    */  public void unregister(int paramInt)
/* 415:    */  {
/* 416:411 */    synchronized (this.toRemoveClients) {
/* 417:412 */      this.toRemoveClients.add(Integer.valueOf(paramInt));
/* 418:413 */      return;
/* 419:    */    }
/* 420:    */  }
/* 421:    */  
/* 424:    */  public void update(Observable paramObservable, Object paramObject)
/* 425:    */  {
/* 426:421 */    setChanged();
/* 427:422 */    notifyObservers(paramObject);
/* 428:    */  }
/* 429:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */