/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
/*     */ import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import org.schema.schine.network.Command;
/*     */ import org.schema.schine.network.CommandMap;
/*     */ import org.schema.schine.network.Header;
/*     */ import org.schema.schine.network.NetUtil;
/*     */ import org.schema.schine.network.NetworkProcessor;
/*     */ import org.schema.schine.network.Pinger;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ public class ServerProcessor extends Pinger
/*     */   implements Runnable, NetworkProcessor
/*     */ {
/*     */   Socket commandSocket;
/*     */   private ServerStateInterface state;
/*     */   private RegisteredClientOnServer client;
/*     */   public static int connections;
/*     */   private long heartBeatTimeStamp;
/*     */   private long heartBeatTimeStampTimeoutHelper;
/* 293 */   Boolean waitingForPong = Boolean.valueOf(false);
/* 294 */   private Object lock = new Object();
/*     */   private static final int heartbeatTimeOut = 10000;
/*     */   private long connectionStartTime;
/* 304 */   private boolean connected = false;
/*     */   private static final int MAX_PING_RETRYS = 12;
/* 306 */   private int pingRetrys = 12;
/*     */   private long pingTime;
/*     */   private DataInputStream dataInputStream;
/*     */   private DataOutputStream out;
/*     */   private FastByteArrayOutputStream byteArrayOutDoubleBuffer;
/*     */   private DataOutputStream outDoubleBuffer;
/* 319 */   public static int MAX_PACKET_SIZE = 20480;
/*     */   private static final int MAX_PACKET_POOL_SIZE = 1000;
/*     */   private DataInputStream inDoubleBuffer;
/* 324 */   byte[] receiveBuffer = new byte[MAX_PACKET_SIZE];
/*     */   private Header tmpHeader;
/*     */   private Thread thread;
/*     */   private ServerProcessor.ServerPing serverPing;
/*     */   private Thread serverPingThread;
/*     */   private boolean stopTransmit;
/* 424 */   private final ArrayList sendingQueue = new ArrayList();
/*     */   private ServerProcessor.SendingQueueThread sendingQueueThread;
/*     */   private boolean disconnectAfterSent;
/*     */   public static int totalPackagesQueued;
/* 437 */   private static final ArrayList packetPool = new ArrayList();
/*     */ 
/* 528 */   public long p = 0L;
/*     */ 
/*     */   public ServerProcessor(Socket paramSocket, ServerStateInterface paramServerStateInterface)
/*     */   {
/* 345 */     this.commandSocket = paramSocket;
/* 346 */     this.state = paramServerStateInterface;
/* 347 */     this.heartBeatTimeStamp = System.currentTimeMillis();
/* 348 */     this.heartBeatTimeStampTimeoutHelper = System.currentTimeMillis();
/* 349 */     this.connectionStartTime = System.currentTimeMillis();
/*     */ 
/* 352 */     this.outDoubleBuffer = new DataOutputStream(this.byteArrayOutDoubleBuffer = new FastByteArrayOutputStream(102400));
/*     */   }
/*     */ 
/*     */   private boolean checkPing(InputStream paramInputStream)
/*     */   {
/* 359 */     if ((
/* 359 */       paramInputStream = (byte)paramInputStream.read()) == 
/* 359 */       -1)
/*     */     {
/* 361 */       this.serverPing.sendPong();
/*     */ 
/* 363 */       return true;
/*     */     }
/* 365 */     if (paramInputStream == -2)
/*     */     {
/* 367 */       setPingTime(System.currentTimeMillis() - this.heartBeatTimeStamp);
/* 368 */       this.waitingForPong = Boolean.valueOf(false);
/* 369 */       if (this.pingRetrys != 12) {
/* 370 */         System.err.println("[SERVER][WARNING] Recovered Ping for " + this.client + "; Retries left: " + this.pingRetrys + "; retries resetting");
/*     */       }
/* 372 */       this.pingRetrys = 12;
/*     */ 
/* 374 */       setChanged();
/* 375 */       notifyObservers();
/* 376 */       return true;
/*     */     }
/* 378 */     return false;
/*     */   }
/*     */ 
/*     */   public void closeSocket()
/*     */   {
/* 385 */     this.commandSocket.close();
/*     */   }
/*     */ 
/*     */   public void disconnectDelayed() {
/* 389 */     new ServerProcessor.1(this).start();
/*     */   }
/*     */ 
/*     */   public void disconnect()
/*     */   {
/* 410 */     this.connected = false;
/* 411 */     setStopTransmit(true);
/* 412 */     synchronized (this.sendingQueue) {
/* 413 */       this.sendingQueue.notify();
/*     */     }
/*     */     try {
/* 416 */       closeSocket();
/*     */     }
/*     */     catch (IOException localIOException) {
/* 419 */       localIOException.printStackTrace();
/*     */     }
/*     */ 
/* 420 */     deleteObservers();
/*     */   }
/*     */ 
/*     */   public static FastByteArrayOutputStream getNewPacket()
/*     */   {
/* 444 */     synchronized (packetPool) {
/* 445 */       if (!packetPool.isEmpty()) {
/* 446 */         return (FastByteArrayOutputStream)packetPool.remove(0);
/*     */       }
/*     */     }
/* 449 */     return new FastByteArrayOutputStream(MAX_PACKET_SIZE);
/*     */   }
/*     */ 
/*     */   public static void releasePacket(FastByteArrayOutputStream paramFastByteArrayOutputStream) {
/* 453 */     paramFastByteArrayOutputStream.reset();
/* 454 */     synchronized (packetPool) {
/* 455 */       if (packetPool.size() < 1000)
/* 456 */         packetPool.add(paramFastByteArrayOutputStream);
/*     */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flushDoubleOutBuffer()
/*     */   {
/* 515 */     assert (this.byteArrayOutDoubleBuffer.position() > 0L);
/*     */     FastByteArrayOutputStream localFastByteArrayOutputStream;
/* 516 */     (
/* 517 */       localFastByteArrayOutputStream = getNewPacket())
/* 517 */       .write(this.byteArrayOutDoubleBuffer.array, 0, (int)this.byteArrayOutDoubleBuffer.position());
/* 518 */     resetDoubleOutBuffer();
/* 519 */     synchronized (this.sendingQueue) {
/* 520 */       if (this.connected)
/*     */       {
/* 522 */         this.sendingQueue.add(localFastByteArrayOutputStream);
/* 523 */         totalPackagesQueued += 1;
/* 524 */         this.sendingQueue.notify();
/*     */       }return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendPacket(FastByteArrayOutputStream paramFastByteArrayOutputStream) {
/* 530 */     assert (paramFastByteArrayOutputStream.position() > 0L);
/* 531 */     System.currentTimeMillis();
/* 532 */     this.out.writeInt((int)paramFastByteArrayOutputStream.position());
/* 533 */     this.out.writeLong(System.currentTimeMillis());
/* 534 */     this.out.write(paramFastByteArrayOutputStream.array, 0, (int)paramFastByteArrayOutputStream.position());
/*     */ 
/* 537 */     this.out.flush();
/*     */   }
/*     */ 
/*     */   public RegisteredClientOnServer getClient()
/*     */   {
/* 542 */     return this.client;
/*     */   }
/*     */ 
/*     */   public InetAddress getClientIp() {
/* 546 */     return this.commandSocket.getInetAddress();
/*     */   }
/*     */ 
/*     */   public long getConnectionTime()
/*     */   {
/* 553 */     return System.currentTimeMillis() - this.connectionStartTime;
/*     */   }
/*     */ 
/*     */   public int getCurrentSize()
/*     */   {
/* 560 */     return (int)this.byteArrayOutDoubleBuffer.position();
/*     */   }
/*     */ 
/*     */   public DataInputStream getIn() {
/* 564 */     return this.inDoubleBuffer;
/*     */   }
/*     */ 
/*     */   public InputStream getInRaw()
/*     */   {
/* 569 */     return this.commandSocket.getInputStream();
/*     */   }
/*     */ 
/*     */   public String getIp() {
/* 573 */     if (this.commandSocket.isConnected()) {
/* 574 */       return this.commandSocket.getRemoteSocketAddress().toString();
/*     */     }
/* 576 */     return "n/a";
/*     */   }
/*     */ 
/*     */   public Object getLock()
/*     */   {
/* 581 */     return this.lock;
/*     */   }
/*     */ 
/*     */   public DataOutputStream getOut()
/*     */   {
/* 586 */     return this.outDoubleBuffer;
/*     */   }
/*     */ 
/*     */   public OutputStream getOutRaw()
/*     */   {
/* 591 */     return this.commandSocket.getOutputStream();
/*     */   }
/*     */ 
/*     */   public long getPingTime()
/*     */   {
/* 598 */     return this.pingTime;
/*     */   }
/*     */ 
/*     */   public int getPort() {
/* 602 */     if (this.commandSocket.isConnected()) {
/* 603 */       return this.commandSocket.getLocalPort();
/*     */     }
/* 605 */     return -1;
/*     */   }
/*     */ 
/*     */   public StateInterface getState()
/*     */   {
/* 611 */     return this.state;
/*     */   }
/*     */ 
/*     */   public boolean isAlive()
/*     */   {
/* 616 */     return this.thread.isAlive();
/*     */   }
/*     */ 
/*     */   public void notifyPacketReceived(short paramShort, Command paramCommand)
/*     */   {
/*     */   }
/*     */ 
/*     */   private void onError()
/*     */   {
/* 650 */     deleteObservers();
/* 651 */     if (getClient() != null)
/* 652 */       this.state.getController().unregister(getClient().getId());
/*     */     else {
/* 654 */       System.err.println("COULD NOT UNREGISTER CLIENT " + getClient());
/*     */     }
/* 656 */     if (!this.commandSocket.isClosed())
/*     */     {
/* 658 */       this.commandSocket.close();
/*     */     }
/* 660 */     connections -= 1;
/* 661 */     if (getClient() == null) {
/* 662 */       return;
/*     */     }
/* 664 */     System.err.println("[Server] Client <" + getClient().getId() + "> logged out from server. connections: " + connections);
/*     */ 
/* 666 */     if (!this.commandSocket.isClosed())
/* 667 */       System.err.println("[Server] ERROR: socket still open!");
/*     */   }
/*     */ 
/*     */   private void parseNextPacket(DataInputStream paramDataInputStream)
/*     */   {
/* 673 */     if ((getClient() != null) && (!getClient().isConnected())) {
/* 674 */       this.connected = false;
/* 675 */       System.err.println("ERROR: client not connected!");
/* 676 */       onError();
/* 677 */       return;
/*     */     }
/*     */ 
/* 680 */     this.tmpHeader.read(paramDataInputStream);
/*     */ 
/* 687 */     if (this.tmpHeader.getType() == 111)
/*     */     {
/* 690 */       Object[] arrayOfObject = NetUtil.commands.getById(this.tmpHeader.getCommandId()).readParameters(this.tmpHeader, paramDataInputStream);
/*     */ 
/* 692 */       NetUtil.commands.getById(this.tmpHeader.getCommandId()).serverProcess(this, arrayOfObject, this.state, this.tmpHeader.packetId);
/* 693 */     } else if (this.tmpHeader.getType() == 123)
/*     */     {
/* 696 */       NetUtil.commands.getById(this.tmpHeader.getCommandId()).serverProcess(this, null, this.state, this.tmpHeader.packetId);
/*     */     }
/* 698 */     paramDataInputStream.available();
/*     */   }
/*     */ 
/*     */   public void resetDoubleOutBuffer()
/*     */   {
/* 708 */     this.outDoubleBuffer.flush();
/* 709 */     this.byteArrayOutDoubleBuffer.reset();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 718 */     connections += 1;
/* 719 */     while (!this.commandSocket.isConnected()) {
/*     */       try {
/* 721 */         System.err.println("[SERVER] waiting for socket to connect: " + this.commandSocket);
/* 722 */         Thread.sleep(100L);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 726 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 728 */     int i = 0;
/*     */     try
/*     */     {
/* 731 */       this.connected = true;
/*     */ 
/* 734 */       setup();
/* 735 */       System.currentTimeMillis();
/*     */ 
/* 737 */       System.out.println("[SERVER][PROCESSOR] client setup completed. listening for input");
/* 738 */       while (this.connected)
/*     */       {
/* 740 */         this.thread.setName("SERVER-PROCESSOR: " + this.client);
/* 741 */         this.serverPingThread.setName("ServerPing " + this.client);
/*     */         int j;
/* 745 */         if ((
/* 745 */           j = this.dataInputStream.readInt()) > 
/* 745 */           this.receiveBuffer.length) {
/* 746 */           System.err.println("[SERVER] Exception: Unusual big update from client " + getClient() + ": growing receive buffer: " + j);
/* 747 */           this.receiveBuffer = new byte[j];
/*     */         }
/*     */ 
/* 752 */         assert (j > 0) : (" Empty update! " + j);
/*     */         try
/*     */         {
/* 756 */           this.dataInputStream.readFully(this.receiveBuffer, 0, j);
/*     */         } catch (Exception localException2) {
/* 758 */           System.err.println("Exception happened with size " + j);
/* 759 */           throw localException2;
/*     */         }
/* 761 */         FastByteArrayInputStream localFastByteArrayInputStream = new FastByteArrayInputStream(this.receiveBuffer, 0, j);
/*     */ 
/* 763 */         this.inDoubleBuffer = new DataInputStream(localFastByteArrayInputStream);
/*     */         int k;
/* 765 */         if ((k = this.inDoubleBuffer.read()) >= 0)
/*     */         {
/* 773 */           if ((k != 42) && (k != 23) && (k != 100) && (k != 65))
/*     */           {
/* 776 */             throw new IOException("SERVER CHECK FAILED: " + k + " for client " + getClient() + ": Received: " + j + ": " + Arrays.toString(Arrays.copyOf(this.receiveBuffer, j)) + ": available: " + localFastByteArrayInputStream.available());
/*     */           }
/*     */ 
/* 782 */           if (k == 100) {
/* 783 */             this.connected = false;
/* 784 */             i = 1;
/* 785 */             System.err.println("[SERVER] Probe was made CODE (#100). Closing connection!");
/* 786 */             this.out.write(100);
/* 787 */             this.out.flush();
/*     */           }
/* 789 */           else if (k == 65) {
/* 790 */             System.err.println("[SERVER][LOGOUT]#### soft client logout received. Logging out client: " + this.client);
/* 791 */             this.connected = false;
/*     */           }
/*     */           else
/*     */           {
/* 795 */             synchronized (this.state) {
/* 796 */               synchronized (getLock()) {
/* 797 */                 if (k == 23)
/*     */                 {
/* 799 */                   boolean bool = checkPing(getIn());
/* 800 */                   assert (bool);
/*     */                 }
/*     */ 
/* 803 */                 if (k == 42) {
/* 804 */                   parseNextPacket(getIn());
/*     */                 }
/*     */ 
/* 807 */                 if (this.byteArrayOutDoubleBuffer.position() > 0L) {
/* 808 */                   flushDoubleOutBuffer();
/*     */                 }
/*     */               }
/*     */             }
/*     */ 
/* 813 */             if (this.inDoubleBuffer.available() > 0)
/* 814 */               throw new IOException("MORE BYTES AVAILABLE: " + this.inDoubleBuffer.available() + "; total size" + localObject4);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception localException1) {
/* 819 */       if (!this.disconnectAfterSent)
/* 820 */         localException1.printStackTrace();
/*     */       else {
/* 822 */         i = 1;
/*     */       }
/* 824 */       this.connected = false;
/*     */     }
/*     */     finally {
/* 827 */       this.connected = false;
/*     */     }
/*     */ 
/* 830 */     System.err.println("CONNECTION FOR " + this.client + " HAS BEEN DISCONNECTED . PROBE: " + localObject1);
/*     */     try {
/* 832 */       if (localObject1 == 0)
/* 833 */         onError();
/*     */     }
/*     */     catch (IOException localIOException) {
/*     */     }
/*     */     finally {
/* 838 */       deleteObservers();
/* 839 */       if (getClient() != null) {
/* 840 */         System.err.println("UNREGISTER CLIENT " + getClient());
/* 841 */         this.state.getController().unregister(getClient().getId());
/* 842 */         System.err.println("UNREGISTER DONE FOR CLIENT " + getClient());
/*     */       } else {
/* 844 */         System.err.println("COULD NOT UNREGISTER CLIENT " + getClient());
/*     */       }
/*     */ 
/* 847 */       if ((getClient() != null) && 
/* 848 */         (!this.state.filterJoinMessages())) {
/* 849 */         ((ServerControllerInterface)getState().getController()).broadcastMessage(getClient().getPlayerName() + " left the game", 0);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 854 */     if (localObject2 != 0) {
/* 855 */       System.err.println("[SERVER] PROBE SUCCESSFULLY EXECUTED. STOPPING PROCESSOR. (Ping of a Starter to start server)"); return;
/*     */     }
/* 857 */     System.err.println("[SERVER] SERVER PROCESSOR STOPPED FOR " + this.client);
/*     */   }
/*     */ 
/*     */   public void serverCommand(byte paramByte, int paramInt, Object[] paramArrayOfObject)
/*     */   {
/* 872 */     System.err.println("SERVER COMMAND: " + Arrays.toString(paramArrayOfObject));
/*     */ 
/* 874 */     NetUtil.commands.getById(paramByte)
/* 876 */       .writeAndCommitParametriziedCommand(paramArrayOfObject, paramInt, getClient().getId(), (short)-32768, getClient().getProcessor());
/*     */ 
/* 878 */     Command.sendingTime = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public void setClient(RegisteredClientOnServer paramRegisteredClientOnServer)
/*     */   {
/* 883 */     this.client = paramRegisteredClientOnServer;
/*     */   }
/*     */ 
/*     */   public void setPingTime(long paramLong)
/*     */   {
/* 890 */     this.pingTime = paramLong;
/*     */   }
/*     */ 
/*     */   public void setThread(Thread paramThread) {
/* 894 */     this.thread = paramThread;
/*     */   }
/*     */ 
/*     */   private void setup()
/*     */   {
/* 899 */     while ((!this.commandSocket.isConnected()) || (!this.commandSocket.isBound()) || (this.commandSocket.isInputShutdown()) || (this.commandSocket.isOutputShutdown())) {
/* 900 */       System.err.println("Waiting for command socket! ");
/*     */     }
/* 902 */     this.dataInputStream = new DataInputStream(new BufferedInputStream(this.commandSocket.getInputStream(), 8192));
/* 903 */     this.out = new DataOutputStream(new BufferedOutputStream(this.commandSocket.getOutputStream(), 8192));
/*     */ 
/* 908 */     this.serverPing = new ServerProcessor.ServerPing(this, null);
/* 909 */     this.serverPingThread = new Thread(this.serverPing, "ServerPing");
/* 910 */     this.serverPingThread.start();
/* 911 */     this.tmpHeader = new Header();
/*     */ 
/* 914 */     this.sendingQueueThread = new ServerProcessor.SendingQueueThread(this, null);
/* 915 */     this.sendingQueueThread.start();
/*     */   }
/*     */ 
/*     */   public void updatePing()
/*     */   {
/* 921 */     throw new IllegalStateException("METHOD NOT AVAILABLE");
/*     */   }
/*     */ 
/*     */   public boolean isStopTransmit()
/*     */   {
/* 930 */     return this.stopTransmit;
/*     */   }
/*     */ 
/*     */   public void setStopTransmit(boolean paramBoolean)
/*     */   {
/* 938 */     this.stopTransmit = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean isConnectionAlive()
/*     */   {
/* 943 */     return (this.commandSocket != null) && (this.commandSocket.isConnected());
/*     */   }
/*     */ 
/*     */   public void disconnectAfterSent() {
/* 947 */     this.disconnectAfterSent = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor
 * JD-Core Version:    0.6.2
 */