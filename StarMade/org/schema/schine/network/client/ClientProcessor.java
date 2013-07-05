/*     */ package org.schema.schine.network.client;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.CommandMap;
/*     */ import org.schema.schine.network.Header;
/*     */ import org.schema.schine.network.NetUtil;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.Request;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.exception.DisconnectException;
/*     */ import xm;
/*     */ 
/*     */ public class ClientProcessor
/*     */   implements Runnable, NetworkProcessor
/*     */ {
/*     */   public static final boolean DEBUG_BIG_CHUNKS = false;
/* 192 */   private final Map pendingRequests = new HashMap();
/*     */ 
/* 194 */   private Object lock = new Object();
/*     */   private FastByteArrayOutputStream byteArrayOutDoubleBuffer;
/*     */   private DataOutputStream outDoubleBuffer;
/*     */   private DataInputStream inDoubleBuffer;
/*     */   private Socket receive;
/* 204 */   boolean listening = true;
/*     */   private ClientStateInterface state;
/*     */   private ClientToServerConnection clientToServerConnection;
/*     */   private DataInputStream dataInputStream;
/* 214 */   byte[] receiveBuffer = new byte[1024000];
/*     */   private Header headerTmp;
/*     */   private ClientProcessor.Pinger pinger;
/*     */   private Thread thread;
/*     */   public long lastPacketId;
/*     */   private Command lastCommand;
/*     */   private long serverPacketSentTimestamp;
/*     */ 
/*     */   public ClientProcessor(ClientToServerConnection paramClientToServerConnection, ClientStateInterface paramClientStateInterface)
/*     */   {
/* 239 */     this.outDoubleBuffer = new DataOutputStream(this.byteArrayOutDoubleBuffer = new FastByteArrayOutputStream(307200));
/*     */ 
/* 241 */     this.state = paramClientStateInterface;
/* 242 */     this.clientToServerConnection = paramClientToServerConnection;
/* 243 */     this.receive = paramClientToServerConnection.getConnection();
/*     */   }
/*     */ 
/*     */   public void closeSocket()
/*     */   {
/* 249 */     synchronized (this.lock) {
/* 250 */       sendLogout();
/*     */     }
/* 252 */     System.err.println("[CLIENT] CLOSING SOCKET");
/* 253 */     if (!this.clientToServerConnection.getConnection().isClosed()) {
/* 254 */       System.err.println("[CLIENT] CLOSING SOCKET");
/* 255 */       this.clientToServerConnection.getConnection().close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flushDoubleOutBuffer()
/*     */   {
/* 262 */     assert (this.byteArrayOutDoubleBuffer.position() > 0L);
/*     */ 
/* 266 */     this.clientToServerConnection.getOutput().writeInt((int)this.byteArrayOutDoubleBuffer.position());
/* 267 */     this.clientToServerConnection.getOutput().write(this.byteArrayOutDoubleBuffer.array, 0, (int)this.byteArrayOutDoubleBuffer.position());
/*     */ 
/* 269 */     this.clientToServerConnection.getOutput().flush();
/*     */ 
/* 271 */     resetDoubleOutBuffer();
/*     */   }
/*     */ 
/*     */   public int getCurrentSize()
/*     */   {
/* 276 */     return (int)this.byteArrayOutDoubleBuffer.position();
/*     */   }
/*     */ 
/*     */   public DataInputStream getIn() {
/* 280 */     return this.inDoubleBuffer;
/*     */   }
/*     */ 
/*     */   public InputStream getInRaw()
/*     */   {
/* 285 */     return this.receive.getInputStream();
/*     */   }
/*     */ 
/*     */   public Object getLock()
/*     */   {
/* 290 */     return this.lock;
/*     */   }
/*     */ 
/*     */   public DataOutputStream getOut()
/*     */   {
/* 295 */     return this.outDoubleBuffer;
/*     */   }
/*     */ 
/*     */   public OutputStream getOutRaw()
/*     */   {
/* 300 */     return this.receive.getOutputStream();
/*     */   }
/*     */ 
/*     */   public Map getPendingRequests() {
/* 304 */     return this.pendingRequests;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/* 309 */     return this.state;
/*     */   }
/*     */ 
/*     */   public Thread getThread() {
/* 313 */     return this.thread;
/*     */   }
/*     */ 
/*     */   public boolean isAlive()
/*     */   {
/* 318 */     return this.clientToServerConnection.isAlive();
/*     */   }
/*     */ 
/*     */   public void notifyPacketReceived(short arg1, Command paramCommand)
/*     */   {
/* 323 */     if (paramCommand.getMode() == 1) {
/* 324 */       Request localRequest = null;
/*     */ 
/* 326 */       synchronized (getPendingRequests())
/*     */       {
/* 328 */         localRequest = (Request)getPendingRequests().remove(Short.valueOf(???));
/* 329 */         assert (localRequest != null) : ("Request #" + ??? + " not pending!!");
/* 330 */         assert (paramCommand.getMode() == 1) : ("COMMAND DOES NOT RETURN BUT SENT RETURN VALUE: " + paramCommand);
/*     */ 
/* 333 */         synchronized (localRequest) {
/* 334 */           assert (!getPendingRequests().containsKey(localRequest));
/* 335 */           localRequest.notify();
/*     */         }
/*     */ 
/* 340 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void parseNextPacket()
/*     */   {
/* 353 */     this.headerTmp.read(getIn());
/*     */ 
/* 355 */     Command localCommand = NetUtil.commands.getById(this.headerTmp.getCommandId());
/*     */ 
/* 357 */     assert (localCommand != null) : ("could not find " + this.headerTmp.getCommandId());
/*     */ 
/* 359 */     this.lastCommand = localCommand;
/* 360 */     if (this.headerTmp.getType() == 111)
/*     */     {
/* 364 */       Object[] arrayOfObject = localCommand.readParameters(this.headerTmp, getIn());
/*     */ 
/* 370 */       localCommand.clientAnswerProcess(arrayOfObject, this.state, this.headerTmp.packetId);
/*     */     }
/* 373 */     else if (this.headerTmp.getType() == 123) {
/* 374 */       localCommand.clientAnswerProcess(null, this.state, this.headerTmp.packetId);
/*     */     }
/*     */     else {
/* 377 */       System.err.println("[CRITICAL][ERROR] HEADER TYPE IS UNKNOWN");
/*     */     }
/* 379 */     if (NetUtil.commands.getById(this.headerTmp.getCommandId()) == null) {
/* 380 */       throw new IOException("[CRITICAL][ERROR] Could not find command " + this.headerTmp.getCommandId());
/*     */     }
/*     */ 
/* 385 */     notifyPacketReceived(this.headerTmp.packetId, NetUtil.commands.getById(this.headerTmp.getCommandId()));
/*     */   }
/*     */ 
/*     */   public void resetDoubleOutBuffer()
/*     */   {
/* 392 */     this.outDoubleBuffer.flush();
/* 393 */     this.byteArrayOutDoubleBuffer.reset();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 404 */     setup();
/*     */     try
/*     */     {
/* 407 */       this.dataInputStream = new DataInputStream(new BufferedInputStream(this.receive.getInputStream(), 8192));
/*     */ 
/* 411 */       while (!this.receive.isConnected()) {
/* 412 */         System.err.println("waiting for connection " + this.state);
/*     */       }
/* 414 */       while ((this.listening) && (!this.receive.isClosed())) {
/* 415 */         getThread().setName("client Processor: " + this.state.getId());
/* 416 */         int i = this.dataInputStream.readInt();
/* 417 */         this.serverPacketSentTimestamp = this.dataInputStream.readLong();
/* 418 */         ((ClientState)this.state).setDebugBigChunk(i > 4000);
/* 419 */         if (this.state.isReadingBigChunk()) {
/* 420 */           System.err.println("[CLIENT] WARNING: Received big chunk: " + i + " bytes");
/*     */         }
/*     */ 
/* 423 */         assert (i > 0) : " Empty update!";
/* 424 */         if ((i > this.receiveBuffer.length) || (i < 0)) {
/* 425 */           System.err.println("Exception CRITICAL: received size: " + i + " / " + this.receiveBuffer.length);
/* 426 */           this.receiveBuffer = new byte[i];
/*     */         }
/* 428 */         if (i > 102400) {
/* 429 */           System.err.println("Exception WARNING CRITICAL: received very BIG size: " + i + " / " + this.receiveBuffer.length);
/*     */         }
/* 431 */         this.dataInputStream.readFully(this.receiveBuffer, 0, i);
/* 432 */         synchronized (this.state) {
/* 433 */           synchronized (this.lock) {
/* 434 */             ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.receiveBuffer, 0, i);
/* 435 */             this.inDoubleBuffer = new DataInputStream(localByteArrayInputStream);
/*     */             int j;
/* 437 */             if ((j = getIn().read()) >= 0)
/*     */             {
/* 443 */               assert ((j == 42) || (j == 23)) : ("CLIENT CHECK FAILED: " + j + " " + Arrays.toString(Arrays.copyOf(this.receiveBuffer, i)) + "; available: " + localByteArrayInputStream.available() + ", happend on object 0");
/*     */ 
/* 448 */               if (j == 23) {
/* 449 */                 boolean bool = this.pinger.checkPing(getIn());
/* 450 */                 assert (bool);
/* 451 */               } else if (j == 42) {
/* 452 */                 parseNextPacket();
/*     */               } else {
/* 454 */                 System.err.println("WARNING: FAULTY PACKET " + localByteArrayInputStream.available());
/*     */               }
/*     */ 
/* 457 */               if (this.byteArrayOutDoubleBuffer.position() > 0L) {
/* 458 */                 System.err.println("sending update: " + j);
/* 459 */                 flushDoubleOutBuffer();
/*     */               }
/*     */             }
/* 461 */             if (localByteArrayInputStream.available() > 0)
/*     */             {
/* 464 */               System.err.println("[CLIENT] WARNING: PACKET NOT FULLY READ ( " + localByteArrayInputStream.available() + "). last check: " + j + ": synched: " + this.state.isSynchronized() + "; last command " + this.lastCommand);
/* 465 */               if (!this.state.isSynchronized())
/* 466 */                 System.err.println("[CLIENT] OK. state got out of synch. Already resynching!");
/*     */               else
/* 468 */                 throw new IOException("[CRITICAL ERROR] PARSING PACKET NOT COMPLETED. BUT CLIENT WAS SYNCHRONIZED!");
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (EOFException localEOFException) {
/* 475 */       (
/* 478 */         localObject3 = localEOFException)
/* 478 */         .printStackTrace();
/* 479 */       if (!ClientState.loginFailed)
/* 480 */         xm.a(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
/*     */       else
/* 482 */         ClientState.loginFailed = false;
/*     */     } catch (IOException localIOException) {
/* 484 */       (
/* 487 */         localObject3 = 
/* 500 */         localIOException).printStackTrace();
/* 488 */       if ((localObject3 instanceof SocketException))
/* 489 */         xm.b(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
/*     */       else
/* 491 */         xm.a((Exception)localObject3);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */       Object localObject3;
/* 493 */       (
/* 494 */         localObject3 = 
/* 500 */         localException).printStackTrace();
/* 495 */       if ((localObject3 instanceof SocketException))
/* 496 */         xm.b(new DisconnectException("You have been disconnected from the Server \n(either connection problem or server crash)\nActualException: " + localObject3.getClass().getSimpleName()));
/*     */       else {
/* 498 */         xm.a((Exception)localObject3);
/*     */       }
/*     */     }
/* 501 */     System.out.println("[ClientProcessor] EXIT: Input Stream closed. Terminating Client Processor");
/*     */   }
/*     */ 
/*     */   public void sendLogout() {
/* 505 */     this.outDoubleBuffer.writeByte(65);
/* 506 */     flushDoubleOutBuffer();
/*     */   }
/*     */ 
/*     */   public void setThread(Thread paramThread) {
/* 510 */     this.thread = paramThread;
/*     */   }
/*     */ 
/*     */   private void setup() {
/* 514 */     this.headerTmp = new Header();
/* 515 */     this.pinger = new ClientProcessor.Pinger(this, null);
/*     */   }
/*     */ 
/*     */   public void updatePing()
/*     */   {
/* 520 */     this.pinger.execute();
/*     */   }
/*     */ 
/*     */   public long getServerPacketSentTimestamp()
/*     */   {
/* 527 */     return this.serverPacketSentTimestamp;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor
 * JD-Core Version:    0.6.2
 */