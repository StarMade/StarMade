/*     */ package org.schema.schine.network.client;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ import java.util.Map;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.CommandMap;
/*     */ import org.schema.schine.network.IdGen;
/*     */ import org.schema.schine.network.NetUtil;
/*     */ import org.schema.schine.network.Pinger;
/*     */ import org.schema.schine.network.Recipient;
/*     */ import org.schema.schine.network.Request;
/*     */ import xu;
/*     */ 
/*     */ public class ClientToServerConnection extends Pinger
/*     */   implements Recipient
/*     */ {
/*     */   private String host;
/*     */   private String name;
/*  73 */   private boolean updateStarted = false;
/*     */   private Socket connection;
/*     */   private int port;
/*     */   private ClientCommunicator communicator;
/*     */   private ClientStateInterface state;
/*     */   private DataOutputStream output;
/*  92 */   private Object waitingForPendingLock = new Object();
/*     */ 
/*     */   public ClientToServerConnection(ClientStateInterface paramClientStateInterface)
/*     */   {
/* 100 */     this.state = paramClientStateInterface;
/*     */   }
/*     */ 
/*     */   public void connect(String paramString, int paramInt)
/*     */   {
/* 111 */     setHost(paramString);
/* 112 */     setPort(paramInt);
/*     */ 
/* 114 */     if (this.connection == null) {
/* 115 */       System.out.println("[CLIENT] establishing new socket connection to " + paramString + ":" + paramInt);
/*     */ 
/* 117 */       this.connection = new Socket(paramString, paramInt);
/*     */ 
/* 119 */       this.connection.setTcpNoDelay(true);
/*     */ 
/* 121 */       if (xu.ai.b()) {
/* 122 */         this.connection.setTrafficClass(24);
/*     */       }
/* 124 */       this.connection.setReceiveBufferSize(((Integer)xu.ah.a()).intValue());
/* 125 */       this.connection.setSendBufferSize(((Integer)xu.ah.a()).intValue());
/*     */     }
/*     */ 
/* 139 */     this.communicator = new ClientCommunicator(this.state, this);
/*     */   }
/*     */   public void disconnect() {
/*     */     try {
/* 147 */       this.connection.shutdownInput();
/* 148 */       this.connection.shutdownOutput();
/* 149 */       this.connection.close();
/* 150 */       System.out.println("Client Socket connection has been closed");
/*     */       return;
/*     */     } catch (IOException localIOException) {
/* 153 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Socket getConnection()
/*     */   {
/* 162 */     return this.connection;
/*     */   }
/*     */ 
/*     */   public String getHost()
/*     */   {
/* 170 */     return this.host;
/*     */   }
/*     */ 
/*     */   public int getId() {
/* 174 */     return this.state.getId();
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 191 */     return this.name;
/*     */   }
/*     */ 
/*     */   public DataOutputStream getOutput()
/*     */   {
/* 198 */     return this.output;
/*     */   }
/*     */ 
/*     */   public int getPort()
/*     */   {
/* 207 */     return this.port;
/*     */   }
/*     */ 
/*     */   public boolean isAlive() {
/* 211 */     return this.communicator.getClientProcessorThread().isAlive();
/*     */   }
/*     */ 
/*     */   public boolean isUpdateStarted()
/*     */   {
/* 220 */     return this.updateStarted;
/*     */   }
/*     */ 
/*     */   public void sendCommand(int paramInt, Class paramClass, Object[] paramArrayOfObject)
/*     */   {
/* 234 */     short s = IdGen.getNewPacketId();
/* 235 */     sendCommand(paramInt, s, paramClass, paramArrayOfObject);
/*     */   }
/*     */ 
/*     */   public void sendCommand(int paramInt, short paramShort, Class paramClass, Object[] paramArrayOfObject)
/*     */   {
/*     */     Command localCommand;
/* 245 */     if ((
/* 245 */       localCommand = NetUtil.commands.getByClass(paramClass))
/* 245 */       .getMode() == 1)
/*     */     {
/* 247 */       Request localRequest = new Request(paramShort);
/*     */ 
/* 249 */       synchronized (this.waitingForPendingLock) {
/* 250 */         synchronized (localRequest)
/*     */         {
/* 252 */           synchronized (this.communicator.getClientProcessor().getPendingRequests()) {
/* 253 */             assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : this.communicator.getClientProcessor().getPendingRequests();
/* 254 */             this.communicator.getClientProcessor().getPendingRequests().put(Short.valueOf(paramShort), localRequest);
/* 255 */             localCommand.writeAndCommitParametriziedCommand(paramArrayOfObject, getId(), paramInt, paramShort, this.state.getProcessor());
/*     */           }
/*     */ 
/* 267 */           while (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 268 */             localRequest.wait(60000L);
/*     */ 
/* 270 */             if (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 271 */               System.err.println("[WARNING] PACKET ID: #" + paramShort + " IS STILL PENDING");
/*     */             }
/*     */           }
/*     */ 
/* 275 */           if ((!$assertionsDisabled) && (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort)))) throw new AssertionError("waiting for packet " + paramShort + " on command " + paramClass + ": " + this.communicator.getClientProcessor().getPendingRequests());
/*     */         }
/*     */ 
/* 278 */         return;
/*     */       }
/*     */     }
/* 280 */     localCommand.writeAndCommitParametriziedCommand(paramArrayOfObject, getId(), paramInt, paramShort, this.state.getProcessor());
/*     */   }
/*     */ 
/*     */   public Object[] sendReturnedCommand(int paramInt, short paramShort, Class paramClass, Object[] paramArrayOfObject)
/*     */   {
/* 289 */     Command localCommand = NetUtil.commands.getByClass(paramClass);
/* 290 */     assert (localCommand.getMode() == 1);
/*     */ 
/* 293 */     Request localRequest = new Request(paramShort);
/* 294 */     synchronized (this.waitingForPendingLock) {
/* 295 */       synchronized (localRequest)
/*     */       {
/* 297 */         synchronized (this.communicator.getClientProcessor().getPendingRequests()) {
/* 298 */           assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : this.communicator.getClientProcessor().getPendingRequests();
/* 299 */           this.communicator.getClientProcessor().getPendingRequests().put(Short.valueOf(paramShort), localRequest);
/* 300 */           localCommand.writeAndCommitParametriziedCommand(paramArrayOfObject, getId(), paramInt, paramShort, this.state.getProcessor());
/*     */         }
/*     */ 
/* 313 */         while (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 314 */           localRequest.wait(60000L);
/*     */ 
/* 316 */           if (this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) {
/* 317 */             System.err.println("[WARNING] PACKET ID: #" + paramShort + " IS STILL PENDING");
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 322 */         assert (!this.communicator.getClientProcessor().getPendingRequests().containsKey(Short.valueOf(paramShort))) : ("waiting for packet " + paramShort + " on command " + paramClass + "; " + this.communicator.getClientProcessor().getPendingRequests());
/* 323 */         ??? = this.state.getReturn(paramShort);
/* 324 */         assert (??? != null);
/* 325 */         return ???;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setHost(String paramString)
/*     */   {
/* 337 */     this.host = paramString;
/*     */   }
/*     */ 
/*     */   public void setName(String paramString)
/*     */   {
/* 348 */     this.name = paramString;
/*     */   }
/*     */ 
/*     */   public void setOutput(DataOutputStream paramDataOutputStream)
/*     */   {
/* 355 */     this.output = paramDataOutputStream;
/*     */   }
/*     */ 
/*     */   public void setPort(int paramInt)
/*     */   {
/* 364 */     this.port = paramInt;
/*     */   }
/*     */ 
/*     */   public void setUpdateStarted(boolean paramBoolean)
/*     */   {
/* 373 */     this.updateStarted = paramBoolean;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientToServerConnection
 * JD-Core Version:    0.6.2
 */