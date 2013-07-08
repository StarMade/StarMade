/*   1:    */package org.schema.schine.network;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import java.net.InetAddress;
/*   5:    */import java.util.ArrayList;
/*   6:    */import org.schema.schine.network.commands.MessageTo;
/*   7:    */import org.schema.schine.network.server.ServerMessage;
/*   8:    */import org.schema.schine.network.server.ServerProcessor;
/*   9:    */import org.schema.schine.network.server.ServerStateInterface;
/*  10:    */
/*  67:    */public class RegisteredClientOnServer
/*  68:    */  implements Identifiable, Recipient
/*  69:    */{
/*  70:    */  private int id;
/*  71:    */  private String playerName;
/*  72:    */  private ServerProcessor serverProcessor;
/*  73:    */  private boolean connected;
/*  74: 74 */  private short needsSynch = -32768;
/*  75:    */  
/*  76:    */  private final NetworkStateContainer localAndRemoteContainer;
/*  77:    */  
/*  78:    */  private final SynchronizationContainerController synchController;
/*  79:    */  
/*  80:    */  private Object player;
/*  81:    */  
/*  82:    */  public boolean wasFullSynched;
/*  83: 83 */  private final ArrayList wispers = new ArrayList();
/*  84:    */  
/*  90:    */  public RegisteredClientOnServer(int paramInt, String paramString, ServerStateInterface paramServerStateInterface)
/*  91:    */  {
/*  92: 92 */    this.id = paramInt;
/*  93: 93 */    this.playerName = paramString;
/*  94: 94 */    this.connected = true;
/*  95:    */    
/*  96: 96 */    this.localAndRemoteContainer = new NetworkStateContainer(true);
/*  97: 97 */    this.synchController = new SynchronizationContainerController(this.localAndRemoteContainer, paramServerStateInterface, true);
/*  98:    */  }
/*  99:    */  
/* 100:    */  public void flagSynch(short paramShort)
/* 101:    */  {
/* 102:102 */    this.needsSynch = paramShort;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public int getId()
/* 106:    */  {
/* 107:107 */    return this.id;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public String getIp() {
/* 111:    */    try {
/* 112:112 */      return getProcessor().getClientIp().toString().replace("/", "");
/* 113:113 */    } catch (Exception localException) { localException;
/* 114:    */    }
/* 115:115 */    return "0.0.0.0";
/* 116:    */  }
/* 117:    */  
/* 118:    */  public NetworkStateContainer getLocalAndRemoteObjectContainer()
/* 119:    */  {
/* 120:120 */    return this.localAndRemoteContainer;
/* 121:    */  }
/* 122:    */  
/* 127:    */  public String getPlayerName()
/* 128:    */  {
/* 129:129 */    return this.playerName;
/* 130:    */  }
/* 131:    */  
/* 133:    */  public Object getPlayerObject()
/* 134:    */  {
/* 135:135 */    return this.player;
/* 136:    */  }
/* 137:    */  
/* 144:    */  public ServerProcessor getProcessor()
/* 145:    */  {
/* 146:146 */    return this.serverProcessor;
/* 147:    */  }
/* 148:    */  
/* 153:    */  public SynchronizationContainerController getSynchController()
/* 154:    */  {
/* 155:155 */    return this.synchController;
/* 156:    */  }
/* 157:    */  
/* 159:    */  public short getSynchPacketId()
/* 160:    */  {
/* 161:161 */    return this.needsSynch;
/* 162:    */  }
/* 163:    */  
/* 167:    */  public boolean isConnected()
/* 168:    */  {
/* 169:169 */    return this.connected;
/* 170:    */  }
/* 171:    */  
/* 172:172 */  public boolean needsSynch() { return this.needsSynch > -32768; }
/* 173:    */  
/* 176:    */  public void sendCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
/* 177:    */  {
/* 178:178 */    NetUtil.commands.getByClass(paramClass).writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.serverProcessor);
/* 179:    */  }
/* 180:    */  
/* 184:    */  public Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
/* 185:    */  {
/* 186:186 */    throw new IllegalArgumentException("this moethod is only used: client to server for client requests");
/* 187:    */  }
/* 188:    */  
/* 189:    */  public void serverMessage(ServerMessage paramServerMessage) {
/* 190:190 */    System.err.println("[SEND][SERVERMESSAGE] " + paramServerMessage + " to " + this);
/* 191:191 */    sendCommand(getId(), IdGen.getNewPacketId(), MessageTo.class, new Object[] { "SERVER", paramServerMessage.message, Integer.valueOf(paramServerMessage.type) });
/* 192:    */  }
/* 193:    */  
/* 194:194 */  public void serverMessage(String paramString) { System.err.println("[SEND][SERVERMESSAGE] " + paramString + " to " + this);
/* 195:195 */    sendCommand(getId(), IdGen.getNewPacketId(), MessageTo.class, new Object[] { "SERVER", paramString, Integer.valueOf(0) });
/* 196:    */  }
/* 197:    */  
/* 201:    */  public void setConnected(boolean paramBoolean)
/* 202:    */  {
/* 203:203 */    this.connected = false;
/* 204:    */  }
/* 205:    */  
/* 206:    */  public void setId(int paramInt)
/* 207:    */  {
/* 208:208 */    this.id = paramInt;
/* 209:    */  }
/* 210:    */  
/* 214:    */  public void setPlayerName(String paramString)
/* 215:    */  {
/* 216:216 */    this.playerName = paramString;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public void setPlayerObject(Object paramObject) {
/* 220:220 */    this.player = paramObject;
/* 221:    */  }
/* 222:    */  
/* 226:    */  public void setProcessor(ServerProcessor paramServerProcessor)
/* 227:    */  {
/* 228:228 */    this.serverProcessor = paramServerProcessor;
/* 229:    */  }
/* 230:    */  
/* 234:    */  public String toString()
/* 235:    */  {
/* 236:236 */    return "RegisteredClient: " + getPlayerName() + " (" + this.id + ") connected: " + this.connected;
/* 237:    */  }
/* 238:    */  
/* 242:    */  public ArrayList getWispers()
/* 243:    */  {
/* 244:244 */    return this.wispers;
/* 245:    */  }
/* 246:    */  
/* 247:    */  public boolean checkConnection() {
/* 248:248 */    if (!this.connected) {
/* 249:249 */      return false;
/* 250:    */    }
/* 251:251 */    if (!getProcessor().isConnectionAlive()) {
/* 252:252 */      return false;
/* 253:    */    }
/* 254:    */    
/* 255:255 */    return true;
/* 256:    */  }
/* 257:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.RegisteredClientOnServer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */