/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ import org.schema.schine.network.NetworkStatus;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ class ServerProcessor$ServerPing
/*     */   implements Runnable
/*     */ {
/*     */   private static final long PING_WAIT = 1000L;
/*     */   private long lastPingSend;
/*  97 */   private long firstTry = 0L;
/*  98 */   private int NULL_TIMEOUT_IN_MS = 10000;
/*     */ 
/*     */   private ServerProcessor$ServerPing(ServerProcessor paramServerProcessor) {  } 
/* 101 */   public void execute() { if (this.firstTry == 0L) {
/* 102 */       this.firstTry = System.currentTimeMillis();
/*     */     }
/*     */ 
/* 105 */     if (this.this$0.getClient() != null)
/*     */     {
/* 107 */       if ((!this.this$0.waitingForPong.booleanValue()) && (System.currentTimeMillis() - this.lastPingSend > 1000L))
/*     */       {
/* 109 */         synchronized (this.this$0.getLock()) {
/* 110 */           sendPing(ServerProcessor.access$000(this.this$0).getId());
/*     */         }
/* 112 */         if (ServerProcessor.access$100(this.this$0) < 12) {
/* 113 */           System.err.println("[SERVER] Std Ping; retries: " + ServerProcessor.access$100(this.this$0) + " to " + this.this$0.getClient());
/*     */         }
/*     */ 
/* 117 */         ServerProcessor.access$202(this.this$0, System.currentTimeMillis());
/* 118 */         ServerProcessor.access$302(this.this$0, System.currentTimeMillis());
/* 119 */         this.lastPingSend = System.currentTimeMillis();
/* 120 */         this.this$0.waitingForPong = Boolean.valueOf(true); return;
/*     */       }
/*     */ 
/* 123 */       if ((ServerProcessor.access$100(this.this$0) >= 0) && (this.this$0.waitingForPong.booleanValue()) && (System.currentTimeMillis() > ServerProcessor.access$300(this.this$0) + 10000L))
/*     */       {
/* 127 */         System.err.println("[SERVERPROCESSOR][WARNING} PING timeout warning. resending ping to " + this.this$0.getClient() + " Retries left: " + ServerProcessor.access$100(this.this$0) + "; socket connected: " + this.this$0.commandSocket.isConnected() + "; socket closed: " + this.this$0.commandSocket.isClosed() + "; inputShutdown: " + this.this$0.commandSocket.isInputShutdown() + "; outputShutdown: " + this.this$0.commandSocket.isOutputShutdown());
/*     */ 
/* 130 */         synchronized (this.this$0.getLock()) {
/* 131 */           sendPing(ServerProcessor.access$000(this.this$0).getId());
/*     */         }
/* 133 */         this.this$0.waitingForPong = Boolean.valueOf(true);
/* 134 */         System.err.println("[SERVERPROCESSOR][WARNING} PING has been resent to " + ServerProcessor.access$400(this.this$0));
/* 135 */         ServerProcessor.access$302(this.this$0, System.currentTimeMillis());
/*     */ 
/* 137 */         ServerProcessor.access$110(this.this$0);
/*     */ 
/* 139 */         if ((!this.this$0.commandSocket.isConnected()) || (this.this$0.commandSocket.isClosed())) {
/* 140 */           throw new IOException("Connection Closed");
/*     */         }
/*     */       }
/* 143 */       else if (ServerProcessor.access$100(this.this$0) < 12) {
/* 144 */         System.err.println("RETRY STATUS: Retries: " + ServerProcessor.access$100(this.this$0) + "; waiting for pong " + this.this$0.waitingForPong + " (" + System.currentTimeMillis() + "/" + (ServerProcessor.access$300(this.this$0) + 10000L) + ")");
/*     */       }
/*     */ 
/* 148 */       if (ServerProcessor.access$100(this.this$0) < 0) {
/* 149 */         System.out.println("[SERVERPROCESSOR][ERROR] ping timeout (" + (System.currentTimeMillis() - ServerProcessor.access$200(this.this$0)) + ") from client -> DISCONNECT" + this.this$0.getClient().getId());
/*     */ 
/* 156 */         ServerProcessor.access$502(this.this$0, false);
/*     */ 
/* 159 */         ((ServerController)this.this$0.getState().getController()).unregister(ServerProcessor.access$400(this.this$0).getId());
/* 160 */         System.err.println("[SERVER] PING TIMEOUT logged out client " + this.this$0.getClient().getId());
/*     */ 
/* 163 */         if (!this.this$0.commandSocket.isClosed()) {
/* 164 */           this.this$0.commandSocket.close();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/* 171 */     else if (this.firstTry > 0L) {
/* 172 */       System.err.println("not executing server ping for null client yet: " + (System.currentTimeMillis() - this.firstTry) + " / " + this.NULL_TIMEOUT_IN_MS);
/* 173 */       if (System.currentTimeMillis() - this.firstTry > this.NULL_TIMEOUT_IN_MS) {
/* 174 */         System.err.println("[SERVER] NULL CLIENT TIMED OUT: DISCONENCTING");
/* 175 */         ServerProcessor.access$502(this.this$0, false);
/* 176 */         this.this$0.commandSocket.close();
/*     */       }
/*     */     } else {
/* 179 */       System.err.println("Client null and not first try");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 188 */     while (ServerProcessor.access$500(this.this$0))
/*     */       try {
/* 190 */         execute();
/* 191 */         Thread.sleep(1000L);
/*     */       }
/*     */       catch (Exception localException2)
/*     */       {
/*     */         Exception localException1;
/* 192 */         (
/* 193 */           localException1 = 
/* 211 */           localException2).printStackTrace();
/* 194 */         System.out.println("[SERVER] client (ping processor) " + this.this$0.getClient() + " disconnected : " + localException1.getMessage());
/*     */ 
/* 197 */         ServerProcessor.access$502(this.this$0, false);
/* 198 */         if (ServerProcessor.access$400(this.this$0) != null) {
/*     */           try {
/* 200 */             System.err.println("[ERROR] SERVER PING FAILED FOR CLIENT " + ServerProcessor.access$400(this.this$0) + " -> LOGGING OUT CLIENT");
/* 201 */             ((ServerController)this.this$0.getState().getController()).unregister(ServerProcessor.access$400(this.this$0).getId());
/*     */           }
/*     */           catch (Exception localException3) {
/* 204 */             localException3.printStackTrace();
/*     */           }
/*     */         }
/*     */         try
/*     */         {
/* 207 */           ServerProcessor.access$600(this.this$0);
/*     */         }
/*     */         catch (IOException localIOException) {
/* 210 */           localIOException.printStackTrace();
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   private void sendPing(int paramInt)
/*     */   {
/* 216 */     paramInt = this.this$0.createPing();
/*     */ 
/* 218 */     ServerProcessor.access$700(this.this$0).write(paramInt);
/*     */ 
/* 220 */     this.this$0.flushDoubleOutBuffer();
/* 221 */     ServerProcessor.access$000(this.this$0).getNetworkStatus().addBytesSent(paramInt.length);
/*     */   }
/*     */ 
/*     */   public void sendPong()
/*     */   {
/* 226 */     byte[] arrayOfByte = this.this$0.createPong();
/* 227 */     ServerProcessor.access$700(this.this$0).write(arrayOfByte);
/* 228 */     this.this$0.flushDoubleOutBuffer();
/* 229 */     ServerProcessor.access$000(this.this$0).getNetworkStatus().addBytesSent(arrayOfByte.length);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.ServerPing
 * JD-Core Version:    0.6.2
 */