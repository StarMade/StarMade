/*     */ package org.schema.schine.network.client;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ 
/*     */ public class ClientCommunicator
/*     */ {
/*     */   private ClientProcessor clientProcessor;
/*     */   private ClientStateInterface state;
/*     */   private boolean connected;
/*     */   private Socket connection;
/*     */   private Thread clientProcessorThread;
/*     */ 
/*     */   public ClientCommunicator(ClientStateInterface paramClientStateInterface, ClientToServerConnection paramClientToServerConnection)
/*     */   {
/*  82 */     setState(paramClientStateInterface);
/*  83 */     setConnection(paramClientToServerConnection.getConnection());
/*  84 */     while ((!getConnection().isConnected()) || (!getConnection().isBound()) || (getConnection().isInputShutdown()) || (getConnection().isOutputShutdown())) {
/*  85 */       System.err.println("CLIENT WAITING FOR CONNECTION");
/*     */     }
/*  87 */     System.out.println("CLIENT SOCKET CONNECTED TO SERVER");
/*     */ 
/*  90 */     paramClientToServerConnection.setOutput(new DataOutputStream(new BufferedOutputStream(getConnection().getOutputStream(), 8192)));
/*  91 */     this.clientProcessor = new ClientProcessor(paramClientToServerConnection, paramClientStateInterface);
/*     */ 
/*  93 */     this.clientProcessorThread = new Thread(this.clientProcessor);
/*  94 */     this.clientProcessorThread.setName("client Processor: " + paramClientStateInterface.getId());
/*  95 */     this.clientProcessor.setThread(this.clientProcessorThread);
/*  96 */     this.clientProcessorThread.start();
/*     */ 
/*  98 */     this.connected = true;
/*  99 */     paramClientStateInterface.setClientConnection(this);
/*     */   }
/*     */ 
/*     */   public ClientProcessor getClientProcessor()
/*     */   {
/* 108 */     return this.clientProcessor;
/*     */   }
/*     */ 
/*     */   public Thread getClientProcessorThread() {
/* 112 */     return this.clientProcessorThread;
/*     */   }
/*     */ 
/*     */   public Socket getConnection()
/*     */   {
/* 122 */     return this.connection;
/*     */   }
/*     */ 
/*     */   public ClientStateInterface getState()
/*     */   {
/* 131 */     return this.state;
/*     */   }
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 140 */     return this.connected;
/*     */   }
/*     */ 
/*     */   public void setClientProcessorThread(Thread paramThread) {
/* 144 */     this.clientProcessorThread = paramThread;
/*     */   }
/*     */ 
/*     */   public void setConnection(Socket paramSocket)
/*     */   {
/* 153 */     this.connection = paramSocket;
/*     */   }
/*     */ 
/*     */   public void setState(ClientStateInterface paramClientStateInterface)
/*     */   {
/* 162 */     this.state = paramClientStateInterface;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientCommunicator
 * JD-Core Version:    0.6.2
 */