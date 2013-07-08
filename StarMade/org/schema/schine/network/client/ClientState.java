/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Map;
/*   9:    */import java.util.Observable;
/*  10:    */import java.util.concurrent.Executors;
/*  11:    */import java.util.concurrent.ThreadPoolExecutor;
/*  12:    */import org.schema.schine.network.ChatSystem;
/*  13:    */import org.schema.schine.network.NetworkProcessor;
/*  14:    */import org.schema.schine.network.NetworkStateContainer;
/*  15:    */import org.schema.schine.network.NetworkStatus;
/*  16:    */import yp;
/*  17:    */
/*  20:    */public abstract class ClientState
/*  21:    */  extends Observable
/*  22:    */  implements ClientStateInterface
/*  23:    */{
/*  24:    */  public static boolean loginFailed;
/*  25:    */  private final NetworkStateContainer networkStateContainer;
/*  26:    */  private final NetworkStateContainer privateNetworkStateContainer;
/*  27: 27 */  private Map requestAnswers = new HashMap();
/*  28:    */  private long ping;
/*  29:    */  private boolean ready;
/*  30:    */  private boolean synchronizedFlag;
/*  31:    */  private boolean synchronizing;
/*  32: 32 */  private int id = -4242;
/*  33:    */  private NetworkStatus networkStatus;
/*  34:    */  private int idStartRange;
/*  35: 35 */  private Integer objectIdPointer = new Integer(0);
/*  36: 36 */  public static final Integer NEW_ID_RANGE = Integer.valueOf(100);
/*  37:    */  private final ArrayList mouseEvents;
/*  38:    */  private ClientCommunicator clientConnection;
/*  39: 39 */  public Object updateLock = new Object();
/*  40:    */  private yp dragging;
/*  41:    */  private boolean debugBigChunk;
/*  42: 42 */  private final ThreadPoolExecutor theadPool = (ThreadPoolExecutor)Executors.newCachedThreadPool();
/*  43:    */  private long serverTimeOnLogin;
/*  44:    */  private int serverTimeDifference;
/*  45:    */  private long lastDeactivatedMenu;
/*  46:    */  private short updateNumber;
/*  47: 47 */  private final GUICallbackController guiCallbackController = new GUICallbackController();
/*  48:    */  
/*  51:    */  public static float serverVersion;
/*  52:    */  
/*  55: 55 */  public void setServerVersion(float paramFloat) { serverVersion = paramFloat; }
/*  56:    */  
/*  57:    */  public ClientState() {
/*  58: 58 */    this.networkStateContainer = new NetworkStateContainer(false);
/*  59: 59 */    this.privateNetworkStateContainer = new NetworkStateContainer(true);
/*  60: 60 */    this.networkStatus = new NetworkStatus();
/*  61: 61 */    this.mouseEvents = new ArrayList();
/*  62:    */  }
/*  63:    */  
/*  66:    */  public void arrivedReturn(short paramShort, Object... paramVarArgs)
/*  67:    */  {
/*  68: 68 */    this.requestAnswers.put(Short.valueOf(paramShort), paramVarArgs);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public void disconnect() {
/*  72: 72 */    getProcessor().closeSocket();
/*  73:    */  }
/*  74:    */  
/*  78:    */  public abstract void exit();
/*  79:    */  
/*  82:    */  public yp getDragging()
/*  83:    */  {
/*  84: 84 */    return this.dragging;
/*  85:    */  }
/*  86:    */  
/*  90:    */  public GUICallbackController getGuiCallbackController()
/*  91:    */  {
/*  92: 92 */    return this.guiCallbackController;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public int getId() {
/*  96: 96 */    return this.id;
/*  97:    */  }
/*  98:    */  
/* 102:    */  public int getIdStartRange()
/* 103:    */  {
/* 104:104 */    return this.idStartRange;
/* 105:    */  }
/* 106:    */  
/* 109:    */  public long getLastDeactivatedMenu()
/* 110:    */  {
/* 111:111 */    return this.lastDeactivatedMenu;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public NetworkStateContainer getLocalAndRemoteObjectContainer() {
/* 115:115 */    return this.networkStateContainer;
/* 116:    */  }
/* 117:    */  
/* 120:    */  public ArrayList getMouseEvents()
/* 121:    */  {
/* 122:122 */    return this.mouseEvents;
/* 123:    */  }
/* 124:    */  
/* 128:    */  public NetworkStatus getNetworkStatus()
/* 129:    */  {
/* 130:130 */    return this.networkStatus;
/* 131:    */  }
/* 132:    */  
/* 136:    */  public int getNextFreeObjectId()
/* 137:    */  {
/* 138:138 */    long l = System.currentTimeMillis();
/* 139:139 */    synchronized (this.objectIdPointer) {
/* 140:140 */      assert (this.objectIdPointer.intValue() <= this.idStartRange + NEW_ID_RANGE.intValue()) : "[CRITICAL] cannot garanty unique ids";
/* 141:141 */      if ((this.objectIdPointer.intValue() <= 0) || (this.objectIdPointer.intValue() == this.idStartRange + NEW_ID_RANGE.intValue())) {
/* 142:    */        try {
/* 143:143 */          getController().aquireFreeIds();
/* 144:144 */          this.objectIdPointer = Integer.valueOf(this.idStartRange);
/* 145:145 */        } catch (IOException localIOException) { 
/* 146:    */          
/* 149:149 */            localIOException;
/* 150:    */        } catch (InterruptedException localInterruptedException) {
/* 151:147 */          
/* 152:    */          
/* 153:149 */            localInterruptedException;
/* 154:    */        }
/* 155:    */      }
/* 156:    */      
/* 157:151 */      this.objectIdPointer = Integer.valueOf(this.objectIdPointer.intValue() + 1);
/* 158:    */    }
/* 159:153 */    System.err.println("[CLIENT] ID RANGE AQUIRE TOOK " + (System.currentTimeMillis() - localObject1));
/* 160:154 */    return this.objectIdPointer.intValue();
/* 161:    */  }
/* 162:    */  
/* 165:    */  public long getPing()
/* 166:    */  {
/* 167:161 */    return this.ping;
/* 168:    */  }
/* 169:    */  
/* 172:    */  public NetworkStateContainer getPrivateLocalAndRemoteObjectContainer()
/* 173:    */  {
/* 174:168 */    return this.privateNetworkStateContainer;
/* 175:    */  }
/* 176:    */  
/* 177:    */  public NetworkProcessor getProcessor()
/* 178:    */  {
/* 179:173 */    return this.clientConnection.getClientProcessor();
/* 180:    */  }
/* 181:    */  
/* 182:    */  public Object[] getReturn(short paramShort) {
/* 183:177 */    return (Object[])this.requestAnswers.remove(Short.valueOf(paramShort));
/* 184:    */  }
/* 185:    */  
/* 188:    */  public int getServerTimeDifference()
/* 189:    */  {
/* 190:184 */    return this.serverTimeDifference;
/* 191:    */  }
/* 192:    */  
/* 196:    */  public long getServerTimeOnLogin()
/* 197:    */  {
/* 198:192 */    return this.serverTimeOnLogin;
/* 199:    */  }
/* 200:    */  
/* 205:    */  public ThreadPoolExecutor getThreadPool()
/* 206:    */  {
/* 207:201 */    return this.theadPool;
/* 208:    */  }
/* 209:    */  
/* 214:    */  public short getUpdateNumber()
/* 215:    */  {
/* 216:210 */    return this.updateNumber;
/* 217:    */  }
/* 218:    */  
/* 220:    */  public abstract List getVisibleChatLog();
/* 221:    */  
/* 222:    */  public abstract List getGeneralChatLog();
/* 223:    */  
/* 224:    */  public void incUpdateNumber()
/* 225:    */  {
/* 226:220 */    this.updateNumber = ((short)(this.updateNumber + 1));
/* 227:    */  }
/* 228:    */  
/* 233:    */  public boolean isReadingBigChunk()
/* 234:    */  {
/* 235:229 */    return false;
/* 236:    */  }
/* 237:    */  
/* 243:    */  public boolean isReady()
/* 244:    */  {
/* 245:239 */    return this.ready;
/* 246:    */  }
/* 247:    */  
/* 249:    */  public boolean isSynchronized()
/* 250:    */  {
/* 251:245 */    return this.synchronizedFlag;
/* 252:    */  }
/* 253:    */  
/* 256:    */  public boolean isSynchronizing()
/* 257:    */  {
/* 258:252 */    return this.synchronizing;
/* 259:    */  }
/* 260:    */  
/* 264:    */  public void println(String paramString) {}
/* 265:    */  
/* 269:    */  public void setClientConnection(ClientCommunicator paramClientCommunicator)
/* 270:    */  {
/* 271:265 */    this.clientConnection = paramClientCommunicator;
/* 272:    */  }
/* 273:    */  
/* 278:    */  public void setDebugBigChunk(boolean paramBoolean)
/* 279:    */  {
/* 280:274 */    this.debugBigChunk = paramBoolean;
/* 281:    */  }
/* 282:    */  
/* 286:    */  public void setDragging(yp paramyp)
/* 287:    */  {
/* 288:282 */    this.dragging = paramyp;
/* 289:    */  }
/* 290:    */  
/* 292:    */  public void setId(int paramInt)
/* 293:    */  {
/* 294:288 */    this.id = paramInt;
/* 295:    */  }
/* 296:    */  
/* 304:    */  public void setIdStartRange(int paramInt)
/* 305:    */  {
/* 306:300 */    this.idStartRange = paramInt;
/* 307:    */  }
/* 308:    */  
/* 309:    */  public void setLastDeactivatedMenu(long paramLong) {
/* 310:304 */    this.lastDeactivatedMenu = paramLong;
/* 311:    */  }
/* 312:    */  
/* 316:    */  public void setPing(long paramLong)
/* 317:    */  {
/* 318:312 */    this.ping = paramLong;
/* 319:    */  }
/* 320:    */  
/* 323:    */  public void setReady(boolean paramBoolean)
/* 324:    */  {
/* 325:319 */    this.ready = paramBoolean;
/* 326:    */  }
/* 327:    */  
/* 331:    */  public void setServerTimeOnLogin(long paramLong)
/* 332:    */  {
/* 333:327 */    this.serverTimeOnLogin = paramLong;
/* 334:328 */    this.serverTimeDifference = ((int)(paramLong - System.currentTimeMillis()));
/* 335:    */  }
/* 336:    */  
/* 337:    */  public void setSynchronized(boolean paramBoolean) {
/* 338:332 */    this.synchronizedFlag = paramBoolean;
/* 339:    */  }
/* 340:    */  
/* 345:    */  public void setSynchronizing(boolean paramBoolean)
/* 346:    */  {
/* 347:341 */    this.synchronizing = paramBoolean;
/* 348:    */  }
/* 349:    */  
/* 350:    */  public String toString() {
/* 351:345 */    return "Client(" + getId() + ")";
/* 352:    */  }
/* 353:    */  
/* 354:    */  public void chatUpdate(ChatSystem paramChatSystem) {}
/* 355:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */