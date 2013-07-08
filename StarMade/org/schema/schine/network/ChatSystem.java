/*   1:    */package org.schema.schine.network;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   4:    */import java.io.PrintStream;
/*   5:    */import java.util.ArrayList;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.Locale;
/*   9:    */import org.schema.schine.network.client.ClientState;
/*  10:    */import org.schema.schine.network.objects.NetworkChat;
/*  11:    */import org.schema.schine.network.objects.NetworkObject;
/*  12:    */import org.schema.schine.network.objects.Sendable;
/*  13:    */import org.schema.schine.network.objects.remote.RemoteArray;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  15:    */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  17:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  18:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  19:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  20:    */import org.schema.schine.network.objects.remote.Streamable;
/*  21:    */import org.schema.schine.network.server.ServerMessage;
/*  22:    */import org.schema.schine.network.server.ServerState;
/*  23:    */import org.schema.schine.network.server.ServerStateInterface;
/*  24:    */import wB;
/*  25:    */import wC;
/*  26:    */import wx;
/*  27:    */import xp;
/*  28:    */import xq;
/*  29:    */
/*  34:    */public class ChatSystem
/*  35:    */  implements Sendable, wB, wx
/*  36:    */{
/*  37:    */  public static final int CHAT_LIMIT = 128;
/*  38:    */  private final ArrayList chatServerLogToSend;
/*  39:    */  private final ArrayList chatLogToSend;
/*  40:    */  private final ArrayList wisperLogToSend;
/*  41:    */  private NetworkChat networkChat;
/*  42: 42 */  private int id = -434343;
/*  43:    */  
/*  44:    */  private final StateInterface state;
/*  45:    */  
/*  46: 46 */  private int ownerStateId = -1;
/*  47:    */  
/*  48:    */  private boolean markedForDelete;
/*  49:    */  
/*  50:    */  private boolean markedForDeleteSent;
/*  51:    */  private final wC textInput;
/*  52:    */  private final boolean onServer;
/*  53:    */  public static final byte TYPE_PM = 0;
/*  54:    */  public static final byte TYPE_FACTION_PM = 1;
/*  55:    */  
/*  56:    */  public ChatSystem(StateInterface paramStateInterface)
/*  57:    */  {
/*  58: 58 */    this.textInput = new wC(128, this);
/*  59: 59 */    this.chatLogToSend = new ArrayList();
/*  60: 60 */    this.chatServerLogToSend = new ArrayList();
/*  61: 61 */    this.state = paramStateInterface;
/*  62: 62 */    this.wisperLogToSend = new ArrayList();
/*  63: 63 */    this.onServer = (paramStateInterface instanceof ServerStateInterface);
/*  64:    */  }
/*  65:    */  
/*  66:    */  public void addToVisibleChat(String paramString1, String paramString2, boolean paramBoolean) {
/*  67: 67 */    this.state.chat(this, paramString1, paramString2, paramBoolean);
/*  68:    */  }
/*  69:    */  
/*  73:    */  public void cleanUpOnEntityDelete() {}
/*  74:    */  
/*  78:    */  public String[] getCommandPrefixes()
/*  79:    */  {
/*  80: 80 */    return this.state.getCommandPrefixes();
/*  81:    */  }
/*  82:    */  
/*  83:    */  public int getId()
/*  84:    */  {
/*  85: 85 */    return this.id;
/*  86:    */  }
/*  87:    */  
/*  92:    */  public NetworkChat getNetworkChat()
/*  93:    */  {
/*  94: 94 */    return this.networkChat;
/*  95:    */  }
/*  96:    */  
/*  97:    */  public NetworkChat getNetworkObject()
/*  98:    */  {
/*  99: 99 */    return getNetworkChat();
/* 100:    */  }
/* 101:    */  
/* 102:    */  public int getOwnerStateId() {
/* 103:103 */    return this.ownerStateId;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public StateInterface getState()
/* 107:    */  {
/* 108:108 */    return this.state;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public wC getTextInput() {
/* 112:112 */    return this.textInput;
/* 113:    */  }
/* 114:    */  
/* 115:    */  public String handleAutoComplete(String paramString1, wB paramwB, String paramString2)
/* 116:    */  {
/* 117:117 */    return this.state.onAutoComplete(paramString1, this, paramString2);
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void handleKeyEvent()
/* 121:    */  {
/* 122:122 */    getTextInput().handleKeyEvent();
/* 123:    */  }
/* 124:    */  
/* 125:    */  public void handleMouseEvent(xp paramxp) {
/* 126:126 */    getTextInput();wC.c();
/* 127:    */  }
/* 128:    */  
/* 129:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject) {
/* 130:130 */    paramNetworkObject = (NetworkChat)paramNetworkObject;
/* 131:    */    
/* 132:132 */    setId(((Integer)paramNetworkObject.id.get()).intValue());
/* 133:133 */    setOwnerStateId(((Integer)this.networkChat.owner.get()).intValue());
/* 134:    */    
/* 135:135 */    System.err.println("[CHAT] " + getState() + " initializing data from network object " + getId());
/* 136:    */  }
/* 137:    */  
/* 140:    */  public void initialize() {}
/* 141:    */  
/* 143:    */  public boolean isMarkedForDeleteVolatile()
/* 144:    */  {
/* 145:145 */    return this.markedForDelete;
/* 146:    */  }
/* 147:    */  
/* 148:    */  public boolean isMarkedForDeleteVolatileSent() {
/* 149:149 */    return this.markedForDeleteSent;
/* 150:    */  }
/* 151:    */  
/* 154:    */  public boolean isOnServer()
/* 155:    */  {
/* 156:156 */    return this.onServer;
/* 157:    */  }
/* 158:    */  
/* 163:    */  public void newNetworkObject()
/* 164:    */  {
/* 165:165 */    this.networkChat = new NetworkChat(getState());
/* 166:    */  }
/* 167:    */  
/* 169:    */  public void onFailedTextCheck(String paramString) {}
/* 170:    */  
/* 171:    */  public void wisper(String paramString1, String paramString2, boolean paramBoolean, byte paramByte)
/* 172:    */  {
/* 173:    */    try
/* 174:    */    {
/* 175:    */      ChatSystem.Wisper localWisper;
/* 176:176 */      (localWisper = new ChatSystem.Wisper(this, null)).player = paramString1;
/* 177:177 */      localWisper.message = paramString2;
/* 178:178 */      localWisper.type = paramByte;
/* 179:179 */      System.err.println("[CHAT] sending PM: " + this + " --> " + localWisper.player + ": " + localWisper.message);
/* 180:180 */      this.wisperLogToSend.add(localWisper);
/* 181:181 */      if (paramBoolean) {
/* 182:182 */        addToVisibleChat(localWisper.message, "[PM to " + localWisper.player + "]", false);
/* 183:    */      }
/* 184:    */    } catch (Exception localException) {
/* 185:185 */      if (paramBoolean) {
/* 186:186 */        addToVisibleChat(paramString2, "[PM]", false);
/* 187:187 */        addToVisibleChat("PM FAILED: Usage: /pm playername some text", "[ERROR]", false);
/* 188:    */      } else {
/* 189:189 */        System.err.println("[ERROR] PM FAILED: " + paramString2 + "; " + localException.getClass().getSimpleName());
/* 190:    */      }
/* 191:    */    }
/* 192:192 */    if (!isOnServer()) {
/* 193:193 */      ((ClientState)getState()).chatUpdate(this);
/* 194:    */    }
/* 195:    */  }
/* 196:    */  
/* 197:    */  public void onTextEnter(String paramString, boolean paramBoolean) {
/* 198:    */    String[] arrayOfString;
/* 199:199 */    if (paramString.toLowerCase(Locale.ENGLISH).startsWith("/pm "))
/* 200:    */    {
/* 201:201 */      if ((arrayOfString = paramString.split("\\s+", 3)).length == 3) {
/* 202:202 */        wisper(arrayOfString[1], arrayOfString[2], true, (byte)0);return; }
/* 203:203 */      if (!isOnServer()) {
/* 204:204 */        addToVisibleChat("No message", "[ERROR]", true);
/* 205:    */      }
/* 206:206 */      return;
/* 207:    */    }
/* 208:208 */    if (this.state.onChatTextEnterHook(this, paramString, paramBoolean)) {
/* 209:209 */      return;
/* 210:    */    }
/* 211:    */    
/* 213:213 */    if ((arrayOfString = getCommandPrefixes()) != null) {
/* 214:214 */      for (int i = 0; i < arrayOfString.length; i++) {
/* 215:215 */        if (paramString.startsWith(arrayOfString[i])) {
/* 216:216 */          this.state.onStringCommand(paramString.substring(1, paramString.length()), this, arrayOfString[i]);
/* 217:217 */          return;
/* 218:    */        }
/* 219:    */      }
/* 220:    */    }
/* 221:    */    
/* 222:222 */    if (paramBoolean) {
/* 223:223 */      this.chatLogToSend.add(paramString);
/* 224:    */    }
/* 225:225 */    addToVisibleChat(paramString, "[ALL]", true);
/* 226:226 */    if (!isOnServer()) {
/* 227:227 */      ((ClientState)getState()).chatUpdate(this);
/* 228:    */    }
/* 229:    */  }
/* 230:    */  
/* 241:    */  public void setId(int paramInt)
/* 242:    */  {
/* 243:243 */    this.id = paramInt;
/* 244:    */  }
/* 245:    */  
/* 247:    */  public void setMarkedForDeleteVolatile(boolean paramBoolean)
/* 248:    */  {
/* 249:249 */    this.markedForDelete = paramBoolean;
/* 250:    */  }
/* 251:    */  
/* 253:    */  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/* 254:    */  {
/* 255:255 */    this.markedForDeleteSent = paramBoolean;
/* 256:    */  }
/* 257:    */  
/* 261:    */  public void setNetworkChat(NetworkChat paramNetworkChat)
/* 262:    */  {
/* 263:263 */    this.networkChat = paramNetworkChat;
/* 264:    */  }
/* 265:    */  
/* 266:    */  public void setOwnerStateId(int paramInt) {
/* 267:267 */    this.ownerStateId = paramInt;
/* 268:    */  }
/* 269:    */  
/* 270:    */  public String toString()
/* 271:    */  {
/* 272:272 */    return "(" + getId() + ")ChatSystem";
/* 273:    */  }
/* 274:    */  
/* 277:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 278:    */  {
/* 279:279 */    for (Iterator localIterator = (paramNetworkObject = (NetworkChat)paramNetworkObject).chatServerLogBuffer.getReceiveBuffer().iterator(); localIterator.hasNext();) { localObject1 = (RemoteString)localIterator.next();
/* 280:280 */      if (this.ownerStateId == this.state.getId()) {
/* 281:281 */        localObject2 = (String)((RemoteString)localObject1).get();
/* 282:282 */        str1 = "[SERVER]";
/* 283:    */        
/* 284:284 */        if (((String)localObject2).startsWith("[PM]")) {
/* 285:285 */          str1 = "[PM]";
/* 286:286 */          localObject2 = ((String)localObject2).substring(4);
/* 287:287 */        } else if (((String)localObject2).startsWith("[FACTION]")) {
/* 288:288 */          str1 = "[FACTION]";
/* 289:289 */          localObject2 = ((String)localObject2).substring(13);
/* 290:    */        }
/* 291:    */        
/* 292:292 */        addToVisibleChat((String)localObject2, str1, false);
/* 293:    */      } }
/* 294:    */    Object localObject1;
/* 295:    */    Object localObject2;
/* 296:    */    String str1;
/* 297:297 */    if (this.ownerStateId == this.state.getId()) {
/* 298:298 */      return;
/* 299:    */    }
/* 300:    */    
/* 302:302 */    for (localIterator = paramNetworkObject.chatLogBuffer.getReceiveBuffer().iterator(); localIterator.hasNext();) { localObject1 = (RemoteString)localIterator.next();
/* 303:303 */      getTextInput().a().add(((RemoteString)localObject1).get());
/* 304:304 */      addToVisibleChat((String)((RemoteString)localObject1).get(), "[ALL]", true);
/* 305:305 */      if ((this.state instanceof ServerState)) {
/* 306:306 */        this.chatLogToSend.add(((RemoteString)localObject1).get());
/* 307:    */      }
/* 308:    */    }
/* 309:    */    
/* 310:310 */    for (localIterator = paramNetworkObject.chatWisperBuffer.getReceiveBuffer().iterator(); localIterator.hasNext();) { localObject1 = (RemoteStringArray)localIterator.next();
/* 311:    */      
/* 312:312 */      if ((this.state instanceof ServerState))
/* 313:    */      {
/* 314:314 */        localObject2 = (String)((RemoteStringArray)localObject1).get(0).get();
/* 315:315 */        str1 = (String)((RemoteStringArray)localObject1).get(1).get();
/* 316:316 */        System.err.println("RECEIVING WISPER (" + getState() + "): " + (String)localObject2 + ": " + str1);
/* 317:317 */        paramNetworkObject = Byte.parseByte((String)((RemoteStringArray)localObject1).get(2).get());
/* 318:318 */        getTextInput().a().add(str1);
/* 319:319 */        String str2 = paramNetworkObject == 0 ? "PM" : "FACTION";
/* 320:320 */        addToVisibleChat(str1, "[" + str2 + " from " + (String)localObject2 + "]", false);
/* 321:    */        try
/* 322:    */        {
/* 323:323 */          int i = ((ServerState)this.state).getClientIdByName((String)localObject2);
/* 324:    */          
/* 325:    */          RegisteredClientOnServer localRegisteredClientOnServer;
/* 326:326 */          if ((localRegisteredClientOnServer = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(i))) == null) {
/* 327:327 */            if (paramNetworkObject != 1) {
/* 328:328 */              this.chatServerLogToSend.add("ERROR: could not find client " + (String)localObject2);
/* 329:    */            }
/* 330:    */          }
/* 331:    */          else {
/* 332:332 */            (localObject2 = new ServerMessage(str1, 0, getOwnerStateId())).prefix = str2;
/* 333:333 */            localRegisteredClientOnServer.getWispers().add(localObject2);
/* 334:    */          }
/* 335:    */          
/* 337:    */        }
/* 338:    */        catch (ClientIdNotFoundException localClientIdNotFoundException)
/* 339:    */        {
/* 340:340 */          if (paramNetworkObject != 1) {
/* 341:341 */            System.err.println("[CHAT] WARNING: could not find " + localClientIdNotFoundException.getMessage());
/* 342:342 */            this.chatServerLogToSend.add("ERROR: could not find client " + (String)((RemoteStringArray)localObject1).get(0).get());
/* 343:    */          }
/* 344:    */        }
/* 345:    */      }
/* 346:    */    }
/* 347:    */  }
/* 348:    */  
/* 353:    */  public void updateLocal(xq arg1)
/* 354:    */  {
/* 355:355 */    synchronized (getNetworkObject()) {
/* 356:356 */      updateToNetworkObject();
/* 357:357 */      return;
/* 358:    */    }
/* 359:    */  }
/* 360:    */  
/* 361:    */  public void updateToFullNetworkObject() {
/* 362:362 */    this.networkChat.id.set(Integer.valueOf(getId()));
/* 363:363 */    this.networkChat.owner.set(Integer.valueOf(this.ownerStateId));
/* 364:364 */    this.networkChat.setChanged(true);
/* 365:365 */    assert (((this.state instanceof ServerState)) || (this.ownerStateId >= 0));
/* 366:366 */    assert (getState().getId() >= 0);
/* 367:    */  }
/* 368:    */  
/* 370:    */  public void updateToNetworkObject()
/* 371:    */  {
/* 372:    */    Object localObject;
/* 373:    */    
/* 374:374 */    if (((this.state instanceof ServerState)) && (getOwnerStateId() >= 0)) {
/* 375:375 */      for (int i = 0; i < this.chatServerLogToSend.size(); i++) {
/* 376:376 */        localObject = new RemoteString((String)this.chatServerLogToSend.get(i), getNetworkObject());
/* 377:377 */        this.networkChat.chatServerLogBuffer.add((Streamable)localObject);
/* 378:378 */        System.err.println("[CHAT] " + this.state + " (" + this + "): " + (String)((RemoteString)localObject).get());
/* 379:    */      }
/* 380:380 */      this.chatServerLogToSend.clear();
/* 381:    */      
/* 383:    */      RegisteredClientOnServer localRegisteredClientOnServer1;
/* 384:    */      
/* 386:386 */      if ((localRegisteredClientOnServer1 = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(getOwnerStateId()))) != null) {
/* 387:387 */        while (!localRegisteredClientOnServer1.getWispers().isEmpty()) {
/* 388:388 */          localObject = (ServerMessage)localRegisteredClientOnServer1.getWispers().remove(0);
/* 389:389 */          RegisteredClientOnServer localRegisteredClientOnServer2 = (RegisteredClientOnServer)((ServerState)this.state).getClients().get(Integer.valueOf(((ServerMessage)localObject).receiverPlayerId));
/* 390:390 */          localObject = "[" + ((ServerMessage)localObject).prefix + "][" + (localRegisteredClientOnServer2 != null ? localRegisteredClientOnServer2.getPlayerName() : "unknown") + "] " + ((ServerMessage)localObject).message;
/* 391:391 */          localObject = new RemoteString((String)localObject, getNetworkObject());
/* 392:392 */          this.networkChat.chatServerLogBuffer.add((Streamable)localObject);
/* 393:393 */          System.err.println("[CHAT][WISPER] " + this.state + " (" + this + "): " + localObject);
/* 394:    */        }
/* 395:    */      }
/* 396:396 */      System.err.println("[SERVER][ERROR] could not wisper. client not found: " + getOwnerStateId());
/* 397:    */    }
/* 398:    */    
/* 400:400 */    for (int j = 0; j < this.chatLogToSend.size(); j++) {
/* 401:401 */      localObject = new RemoteString((String)this.chatLogToSend.get(j), getNetworkObject());
/* 402:402 */      this.networkChat.chatLogBuffer.add((Streamable)localObject);
/* 403:403 */      System.err.println("[CHAT] " + this.state + " (" + this + "): " + (String)((RemoteString)localObject).get());
/* 404:    */    }
/* 405:405 */    for (j = 0; j < this.wisperLogToSend.size(); j++)
/* 406:    */    {
/* 407:407 */      (localObject = new RemoteStringArray(3, getNetworkObject())).set(0, ((ChatSystem.Wisper)this.wisperLogToSend.get(j)).player);
/* 408:408 */      ((RemoteStringArray)localObject).set(1, ((ChatSystem.Wisper)this.wisperLogToSend.get(j)).message);
/* 409:409 */      ((RemoteStringArray)localObject).set(2, String.valueOf(((ChatSystem.Wisper)this.wisperLogToSend.get(j)).type));
/* 410:    */      
/* 411:411 */      this.networkChat.chatWisperBuffer.add((RemoteArray)localObject);
/* 412:412 */      System.err.println("[CHAT]" + this.state + " (" + this + "): " + localObject);
/* 413:    */    }
/* 414:414 */    this.wisperLogToSend.clear();
/* 415:415 */    this.chatLogToSend.clear();
/* 416:    */  }
/* 417:    */  
/* 420:    */  public void destroyPersistent() {}
/* 421:    */  
/* 424:    */  public boolean isMarkedForPermanentDelete()
/* 425:    */  {
/* 426:426 */    return false;
/* 427:    */  }
/* 428:    */  
/* 431:    */  public void markedForPermanentDelete(boolean paramBoolean) {}
/* 432:    */  
/* 435:    */  public boolean isUpdatable()
/* 436:    */  {
/* 437:437 */    return true;
/* 438:    */  }
/* 439:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.ChatSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */