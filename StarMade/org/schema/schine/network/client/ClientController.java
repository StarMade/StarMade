/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*   4:    */import java.awt.Dimension;
/*   5:    */import java.awt.Toolkit;
/*   6:    */import java.io.PrintStream;
/*   7:    */import javax.swing.JFrame;
/*   8:    */import javax.swing.JOptionPane;
/*   9:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  10:    */import org.schema.schine.network.Header;
/*  11:    */import org.schema.schine.network.IdGen;
/*  12:    */import org.schema.schine.network.LoginFailedException;
/*  13:    */import org.schema.schine.network.NetworkProcessor;
/*  14:    */import org.schema.schine.network.commands.GetNextFreeObjectId;
/*  15:    */import org.schema.schine.network.commands.Login;
/*  16:    */import org.schema.schine.network.commands.RequestServerTime;
/*  17:    */import org.schema.schine.network.commands.RequestSynchronizeAll;
/*  18:    */import org.schema.schine.network.commands.Synchronize;
/*  19:    */import org.schema.schine.network.commands.SynchronizePrivateChannel;
/*  20:    */import org.schema.schine.network.synchronization.SynchronizationReceiver;
/*  21:    */import org.schema.schine.network.synchronization.SynchronizationSender;
/*  22:    */
/*  38:    */public abstract class ClientController
/*  39:    */  implements ClientControllerInterface
/*  40:    */{
/*  41:    */  private ClientToServerConnection connection;
/*  42:    */  private ClientStateInterface state;
/*  43: 43 */  private IntOpenHashSet delHelper = new IntOpenHashSet();
/*  44:    */  private long lastSynchronize;
/*  45:    */  
/*  46: 46 */  public ClientController(ClientStateInterface paramClientStateInterface) { this.state = paramClientStateInterface;
/*  47: 47 */    Runtime.getRuntime().addShutdownHook(new ClientController.1(this));
/*  48:    */  }
/*  49:    */  
/*  61:    */  public abstract void afterFullResynchronize();
/*  62:    */  
/*  74:    */  public void aquireFreeIds()
/*  75:    */  {
/*  76: 76 */    System.err.println("[CLIENT] " + this.state + " asking for new IDS");
/*  77: 77 */    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], GetNextFreeObjectId.class, new Object[] { ClientState.NEW_ID_RANGE });
/*  78:    */    
/*  79: 79 */    System.err.println("[CLIENT] " + this.state + " received new IDS");
/*  80:    */  }
/*  81:    */  
/*  90:    */  public void connect(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
/*  91:    */  {
/*  92: 92 */    setGuiConnectionState("connecting to " + paramString1 + ":" + paramInt);
/*  93: 93 */    this.connection = new ClientToServerConnection(this.state);
/*  94: 94 */    this.connection.connect(paramString1, paramInt);
/*  95:    */    
/*  96: 96 */    login(paramString2, this.state.getVersion(), paramString3, paramString4);
/*  97:    */    
/*  99: 99 */    System.out.println("[CLIENT] logged in as: " + this.state);
/* 100:100 */    onLogin();
/* 101:101 */    System.out.println("[CLIENT] synchronizing ALL " + this.state);
/* 102:102 */    setGuiConnectionState("requesting synchronize...");
/* 103:103 */    requestSynchronizeAll();
/* 104:104 */    setGuiConnectionState("client synchronized...");
/* 105:    */  }
/* 106:    */  
/* 107:107 */  public ClientToServerConnection getConnection() { return this.connection; }
/* 108:    */  
/* 114:    */  public void handleBrokeConnection()
/* 115:    */  {
/* 116:116 */    this.connection.disconnect();
/* 117:117 */    System.err.println("[CLIENT] " + this.state + " CLIENT LOST CONNECTION -> BACK TO login SCREEN");
/* 118:    */  }
/* 119:    */  
/* 121:    */  public void handleErrorDialogException(ErrorDialogException paramErrorDialogException)
/* 122:    */  {
/* 123:123 */    Object[] arrayOfObject = { "Retry", "Exit" };
/* 124:124 */    paramErrorDialogException.printStackTrace();
/* 125:125 */    String str = "Critical Error";
/* 126:    */    JFrame localJFrame;
/* 127:127 */    (localJFrame = new JFrame(str)).setUndecorated(true);
/* 128:    */    
/* 129:129 */    localJFrame.setVisible(true);
/* 130:    */    
/* 131:131 */    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/* 132:132 */    localJFrame.setLocation(localDimension.width / 2, localDimension.height / 2);
/* 133:133 */    switch (JOptionPane.showOptionDialog(localJFrame, paramErrorDialogException.getClass().getSimpleName() + ": " + paramErrorDialogException.getMessage(), str, 1, 0, null, arrayOfObject, arrayOfObject[0]))
/* 134:    */    {
/* 137:    */    case 0: 
/* 138:138 */      break;
/* 139:    */    case 1: 
/* 140:140 */      System.err.println("[GLFrame] Error Message: " + paramErrorDialogException.getMessage());
/* 141:141 */      System.exit(0);
/* 142:    */    }
/* 143:    */    
/* 144:    */    
/* 146:146 */    localJFrame.dispose();
/* 147:    */  }
/* 148:    */  
/* 156:    */  public void login(String paramString1, float paramFloat, String paramString2, String paramString3)
/* 157:    */  {
/* 158:158 */    setGuiConnectionState("logging in as " + paramString1 + "    (Version " + paramFloat + ")");
/* 159:159 */    System.out.println("[CLIENT] logging in now... " + paramString1);
/* 160:160 */    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], Login.class, new Object[] { paramString1, Float.valueOf(paramFloat), paramString2, paramString3 });
/* 161:    */    
/* 162:162 */    if (this.state.getId() < 0) {
/* 163:163 */      ClientState.loginFailed = true;
/* 164:164 */      throw new LoginFailedException(this.state.getId());
/* 165:    */    }
/* 166:    */    
/* 167:167 */    setGuiConnectionState("login successfull...");
/* 168:168 */    this.state.setPlayerName(paramString1);
/* 169:    */  }
/* 170:    */  
/* 173:    */  public void logout(String paramString)
/* 174:    */  {
/* 175:175 */    System.err.println("logout received. exiting");
/* 176:176 */    kick(paramString);
/* 177:    */  }
/* 178:    */  
/* 179:    */  protected abstract void onLogin();
/* 180:    */  
/* 181:    */  protected abstract void onResynchRequest();
/* 182:    */  
/* 183:    */  protected abstract void onShutDown();
/* 184:    */  
/* 185:    */  public void requestServerTime() {
/* 186:186 */    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], RequestServerTime.class, new Object[0]);
/* 187:    */  }
/* 188:    */  
/* 190:    */  public void requestSynchronizeAll()
/* 191:    */  {
/* 192:192 */    this.connection.sendCommand(org.schema.schine.network.NetUtil.RECEIVER_SERVER[0], RequestSynchronizeAll.class, new Object[0]);
/* 193:193 */    this.lastSynchronize = System.currentTimeMillis();
/* 194:    */    
/* 195:195 */    afterFullResynchronize();
/* 196:    */    
/* 197:197 */    this.state.setSynchronized(true);
/* 198:198 */    System.out.println("[CLIENT] RE- synchronized client: " + this.state.getId());
/* 199:    */  }
/* 200:    */  
/* 201:201 */  public void setConnection(ClientToServerConnection paramClientToServerConnection) { this.connection = paramClientToServerConnection; }
/* 202:    */  
/* 204:    */  public abstract void setGuiConnectionState(String paramString);
/* 205:    */  
/* 207:    */  public void synchronize()
/* 208:    */  {
/* 209:209 */    Header localHeader = new Header(Synchronize.class, this.state.getId(), 0, IdGen.getNewPacketId(), (byte)123);
/* 210:210 */    synchronized (this.state.getProcessor().getLock())
/* 211:    */    {
/* 212:212 */      localHeader.write(this.state.getProcessor().getOut());
/* 213:    */      
/* 216:216 */      int i = 0; if (SynchronizationSender.encodeNetworkObjects(this.state.getLocalAndRemoteObjectContainer(), this.state, this.state.getProcessor().getOut(), false) == 1) {
/* 217:217 */        long l1 = System.currentTimeMillis();
/* 218:218 */        i = this.state.getProcessor().getCurrentSize();
/* 219:219 */        this.state.getProcessor().flushDoubleOutBuffer();
/* 220:    */        long l2;
/* 221:221 */        if ((l2 = System.currentTimeMillis() - l1) > 10L) {
/* 222:222 */          System.err.println("[WARNING][CLIENT] SLOW: synchronized flush took " + l2 + " ms, size " + i);
/* 223:    */        }
/* 224:    */      } else {
/* 225:225 */        this.state.getProcessor().resetDoubleOutBuffer();
/* 226:    */      }
/* 227:    */    }
/* 228:228 */    SynchronizationReceiver.handleDeleted(this.state.getLocalAndRemoteObjectContainer(), this.state, this.delHelper);
/* 229:229 */    synchronizePrivate();
/* 230:    */  }
/* 231:    */  
/* 233:    */  public void synchronizePrivate()
/* 234:    */  {
/* 235:235 */    Header localHeader = new Header(SynchronizePrivateChannel.class, this.state.getId(), 0, IdGen.getNewPacketId(), (byte)123);
/* 236:236 */    synchronized (this.state.getProcessor().getLock())
/* 237:    */    {
/* 238:238 */      localHeader.write(this.state.getProcessor().getOut());
/* 239:    */      
/* 242:242 */      if (SynchronizationSender.encodeNetworkObjects(this.state.getPrivateLocalAndRemoteObjectContainer(), this.state, this.state.getProcessor().getOut(), false) == 1) {
/* 243:243 */        this.state.getProcessor().flushDoubleOutBuffer();
/* 244:    */      } else {
/* 245:245 */        this.state.getProcessor().resetDoubleOutBuffer();
/* 246:    */      }
/* 247:    */    }
/* 248:248 */    SynchronizationReceiver.handleDeleted(this.state.getPrivateLocalAndRemoteObjectContainer(), this.state, this.delHelper);
/* 249:    */  }
/* 250:    */  
/* 251:    */  public void updateSynchronization()
/* 252:    */  {
/* 253:253 */    if (!this.state.getProcessor().isAlive()) {
/* 254:254 */      System.err.println("EXIT: PROCESSOR DEAD");
/* 255:255 */      System.exit(0);
/* 256:    */    }
/* 257:257 */    this.state.getProcessor().updatePing();
/* 258:    */    
/* 259:259 */    if (this.state.isSynchronized()) {
/* 260:260 */      if (this.lastSynchronize + 30L < System.currentTimeMillis())
/* 261:    */      {
/* 262:262 */        synchronize();
/* 263:    */        
/* 271:271 */        this.lastSynchronize = System.currentTimeMillis();
/* 272:    */      }
/* 273:    */    }
/* 274:    */    else {
/* 275:275 */      onResynchRequest();
/* 276:    */      
/* 277:277 */      System.err.println("[ERROR] Updating. RESYNCHING WITH SERVER " + this.state);
/* 278:278 */      requestSynchronizeAll();
/* 279:    */    }
/* 280:    */  }
/* 281:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */