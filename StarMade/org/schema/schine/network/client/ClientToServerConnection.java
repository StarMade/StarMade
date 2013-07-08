/*   1:    */package org.schema.schine.network.client;
/*   2:    */
/*   3:    */import java.io.DataOutputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.PrintStream;
/*   6:    */import java.net.InetSocketAddress;
/*   7:    */import java.net.Socket;
/*   8:    */import java.util.Map;
/*   9:    */import net.rudp.ReliableSocket;
/*  10:    */import org.schema.schine.network.Command;
/*  11:    */import org.schema.schine.network.CommandMap;
/*  12:    */import org.schema.schine.network.IdGen;
/*  13:    */import org.schema.schine.network.NetUtil;
/*  14:    */import org.schema.schine.network.Pinger;
/*  15:    */import org.schema.schine.network.Recipient;
/*  16:    */import org.schema.schine.network.Request;
/*  17:    */import xu;
/*  18:    */
/*  74:    */public class ClientToServerConnection
/*  75:    */  extends Pinger
/*  76:    */  implements Recipient
/*  77:    */{
/*  78:    */  private String host;
/*  79:    */  private String name;
/*  80: 80 */  private boolean updateStarted = false;
/*  81:    */  
/*  84:    */  private Socket connection;
/*  85:    */  
/*  87:    */  private int port;
/*  88:    */  
/*  90:    */  private ClientCommunicator communicator;
/*  91:    */  
/*  93:    */  private ClientStateInterface state;
/*  94:    */  
/*  96:    */  private DataOutputStream output;
/*  97:    */  
/*  99: 99 */  private Object waitingForPendingLock = new Object();
/* 100:    */  
/* 105:    */  public ClientToServerConnection(ClientStateInterface paramClientStateInterface)
/* 106:    */  {
/* 107:107 */    this.state = paramClientStateInterface;
/* 108:    */  }
/* 109:    */  
/* 116:    */  public void connect(String paramString, int paramInt)
/* 117:    */  {
/* 118:118 */    setHost(paramString);
/* 119:119 */    setPort(paramInt);
/* 120:    */    
/* 121:121 */    if (this.connection == null) {
/* 122:122 */      System.out.println("[CLIENT] establishing new socket connection to " + paramString + ":" + paramInt);
/* 123:    */      
/* 125:125 */      paramString = new InetSocketAddress(paramString, paramInt);
/* 126:    */      
/* 128:    */      try
/* 129:    */      {
/* 130:130 */        System.err.println("[CLIENT] Trying TCP...");
/* 131:131 */        this.connection = new Socket();
/* 132:132 */        if (xu.aj.b()) {
/* 133:133 */          this.connection.setTrafficClass(24);
/* 134:    */        }
/* 135:135 */        this.connection.setReceiveBufferSize(((Integer)xu.ai.a()).intValue());
/* 136:136 */        this.connection.setSendBufferSize(((Integer)xu.ai.a()).intValue());
/* 137:137 */        this.connection.setTcpNoDelay(true);
/* 138:138 */        this.connection.connect(paramString);
/* 139:    */      }
/* 140:    */      catch (Exception paramInt) {
/* 141:141 */        if (this.connection != null) {
/* 142:    */          try {
/* 143:143 */            this.connection.close();
/* 144:144 */          } catch (Exception localException) { 
/* 145:    */            
/* 146:146 */              localException;
/* 147:    */          }
/* 148:    */        }
/* 149:    */        
/* 151:149 */        System.err.println("[CLIENT] TCP connection failed: " + paramInt.getClass().getSimpleName() + "; " + paramInt.getMessage());
/* 152:150 */        System.err.println("[CLIENT] Trying UDP...");
/* 153:    */        
/* 156:154 */        this.connection = new ReliableSocket();
/* 157:155 */        if (xu.aj.b()) {
/* 158:156 */          this.connection.setTrafficClass(24);
/* 159:    */        }
/* 160:158 */        this.connection.setReceiveBufferSize(((Integer)xu.ai.a()).intValue());
/* 161:159 */        this.connection.setSendBufferSize(((Integer)xu.ai.a()).intValue());
/* 162:160 */        this.connection.connect(paramString);
/* 163:161 */        System.err.println("[CLIENT] UDP CONNECTION SUCCESSFULL");
/* 164:    */      }
/* 165:    */    }
/* 166:    */    
/* 184:182 */    this.communicator = new ClientCommunicator(this.state, this);
/* 185:    */  }
/* 186:    */  
/* 188:    */  public void disconnect()
/* 189:    */  {
/* 190:    */    try
/* 191:    */    {
/* 192:190 */      this.connection.shutdownInput();
/* 193:191 */      this.connection.shutdownOutput();
/* 194:192 */      this.connection.close();
/* 195:193 */      System.out.println("Client Socket connection has been closed"); return;
/* 196:194 */    } catch (IOException localIOException) { 
/* 197:    */      
/* 198:196 */        localIOException;
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 207:    */  public Socket getConnection()
/* 208:    */  {
/* 209:205 */    return this.connection;
/* 210:    */  }
/* 211:    */  
/* 215:    */  public String getHost()
/* 216:    */  {
/* 217:213 */    return this.host;
/* 218:    */  }
/* 219:    */  
/* 220:    */  public int getId() {
/* 221:217 */    return this.state.getId();
/* 222:    */  }
/* 223:    */  
/* 236:    */  public String getName()
/* 237:    */  {
/* 238:234 */    return this.name;
/* 239:    */  }
/* 240:    */  
/* 243:    */  public DataOutputStream getOutput()
/* 244:    */  {
/* 245:241 */    return this.output;
/* 246:    */  }
/* 247:    */  
/* 252:    */  public int getPort()
/* 253:    */  {
/* 254:250 */    return this.port;
/* 255:    */  }
/* 256:    */  
/* 257:    */  public boolean isAlive() {
/* 258:254 */    return this.communicator.getClientProcessorThread().isAlive();
/* 259:    */  }
/* 260:    */  
/* 265:    */  public boolean isUpdateStarted()
/* 266:    */  {
/* 267:263 */    return this.updateStarted;
/* 268:    */  }
/* 269:    */  
/* 279:    */  public void sendCommand(int paramInt, Class paramClass, Object... paramVarArgs)
/* 280:    */  {
/* 281:277 */    short s = IdGen.getNewPacketId();
/* 282:278 */    sendCommand(paramInt, s, paramClass, paramVarArgs);
/* 283:    */  }
/* 284:    */  
/* 287:    */  public void sendCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
/* 288:    */  {
/* 289:    */    Command localCommand;
/* 290:    */    
/* 292:288 */    if ((localCommand = NetUtil.commands.getByClass(paramClass)).getMode() == 1)
/* 293:    */    {
/* 294:290 */      Request localRequest = new Request(paramShort);
/* 295:    */      
/* 296:292 */      synchronized (this.waitingForPendingLock) {
/* 297:293 */        synchronized (localRequest)
/* 298:    */        {
/* 299:295 */          synchronized (this.communicator.getClientProcessor().getPendingRequests()) {
/* 300:296 */            assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : this.communicator.getClientProcessor().getPendingRequests();
/* 301:297 */            this.communicator.getClientProcessor().getPendingRequests().put(Short.valueOf(paramShort), localRequest);
/* 302:298 */            localCommand.writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.state.getProcessor());
/* 303:    */          }
/* 304:    */          
/* 314:310 */          while (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 315:311 */            localRequest.wait(60000L);
/* 316:    */            
/* 317:313 */            if (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 318:314 */              System.err.println("[WARNING] PACKET ID: #" + paramShort + " IS STILL PENDING");
/* 319:    */            }
/* 320:    */          }
/* 321:    */          
/* 322:318 */          if ((!$assertionsDisabled) && (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort)))) { throw new AssertionError("waiting for packet " + paramShort + " on command " + paramClass + ": " + this.communicator.getClientProcessor().getPendingRequests());
/* 323:    */          }
/* 324:    */        }
/* 325:321 */        return;
/* 326:    */      } }
/* 327:323 */    localCommand.writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.state.getProcessor());
/* 328:    */  }
/* 329:    */  
/* 334:    */  public Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object... paramVarArgs)
/* 335:    */  {
/* 336:332 */    Command localCommand = NetUtil.commands.getByClass(paramClass);
/* 337:333 */    assert (localCommand.getMode() == 1);
/* 338:    */    
/* 340:336 */    Request localRequest = new Request(paramShort);
/* 341:337 */    synchronized (this.waitingForPendingLock) {
/* 342:338 */      synchronized (localRequest)
/* 343:    */      {
/* 344:340 */        synchronized (this.communicator.getClientProcessor().getPendingRequests()) {
/* 345:341 */          assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : this.communicator.getClientProcessor().getPendingRequests();
/* 346:342 */          this.communicator.getClientProcessor().getPendingRequests().put(Short.valueOf(paramShort), localRequest);
/* 347:343 */          localCommand.writeAndCommitParametriziedCommand(paramVarArgs, getId(), paramInt, paramShort, this.state.getProcessor());
/* 348:    */        }
/* 349:    */        
/* 360:356 */        while (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 361:357 */          localRequest.wait(60000L);
/* 362:    */          
/* 363:359 */          if (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 364:360 */            System.err.println("[WARNING] PACKET ID: #" + paramShort + " IS STILL PENDING");
/* 365:    */          }
/* 366:    */        }
/* 367:    */        
/* 369:365 */        assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : ("waiting for packet " + paramShort + " on command " + paramClass + "; " + this.communicator.getClientProcessor().getPendingRequests());
/* 370:366 */        ??? = this.state.getReturn(paramShort);
/* 371:367 */        assert (??? != null);
/* 372:368 */        return ???;
/* 373:    */      }
/* 374:    */    }
/* 375:    */  }
/* 376:    */  
/* 382:    */  public void setHost(String paramString)
/* 383:    */  {
/* 384:380 */    this.host = paramString;
/* 385:    */  }
/* 386:    */  
/* 393:    */  public void setName(String paramString)
/* 394:    */  {
/* 395:391 */    this.name = paramString;
/* 396:    */  }
/* 397:    */  
/* 400:    */  public void setOutput(DataOutputStream paramDataOutputStream)
/* 401:    */  {
/* 402:398 */    this.output = paramDataOutputStream;
/* 403:    */  }
/* 404:    */  
/* 409:    */  public void setPort(int paramInt)
/* 410:    */  {
/* 411:407 */    this.port = paramInt;
/* 412:    */  }
/* 413:    */  
/* 418:    */  public void setUpdateStarted(boolean paramBoolean)
/* 419:    */  {
/* 420:416 */    this.updateStarted = paramBoolean;
/* 421:    */  }
/* 422:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientToServerConnection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */