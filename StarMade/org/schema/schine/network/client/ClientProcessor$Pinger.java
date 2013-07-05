/*     */ package org.schema.schine.network.client;
/*     */ 
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import org.schema.schine.network.NetworkStatus;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ class ClientProcessor$Pinger
/*     */ {
/*     */   private boolean waitingForPong;
/*     */   private long lastPingTime;
/*     */   private long lastPong;
/*     */ 
/*     */   private ClientProcessor$Pinger(ClientProcessor paramClientProcessor)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean checkPing(InputStream paramInputStream)
/*     */   {
/*  89 */     if ((
/*  89 */       paramInputStream = (byte)paramInputStream.read()) == 
/*  89 */       -2)
/*     */     {
/*  92 */       this.waitingForPong = false;
/*     */ 
/*  94 */       ClientProcessor.access$000(this.this$0).setPing(System.currentTimeMillis() - this.lastPingTime);
/*  95 */       this.lastPong = System.currentTimeMillis();
/*     */ 
/*  99 */       return true;
/* 100 */     }if (paramInputStream == -1)
/*     */     {
/* 103 */       sendPong();
/*     */ 
/* 105 */       return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public void execute()
/*     */   {
/* 112 */     if (ClientProcessor.access$100(this.this$0).getId() > 0)
/* 113 */       synchronized (ClientProcessor.access$200(this.this$0)) {
/* 114 */         if ((!this.waitingForPong) && (this.lastPingTime + 1000L < System.currentTimeMillis()))
/*     */         {
/* 120 */           if (sendPing())
/*     */           {
/* 121 */             this.lastPingTime = System.currentTimeMillis();
/* 122 */             this.waitingForPong = true;
/*     */           }
/*     */         }
/*     */ 
/* 126 */         long l1 = System.currentTimeMillis() - this.lastPingTime;
/* 127 */         long l2 = System.currentTimeMillis() - this.lastPong;
/* 128 */         if ((this.waitingForPong) && (l1 > 5000L)) {
/* 129 */           ((ClientControllerInterface)this.this$0.getState().getController()).alertMessage("WARNING: Server doesn't answer! (" + l2 / 1000L + " sec)\nServer is temporary under high load!.\n\nif this message does not go away\nthe server might be frozen\nPlease tell the admin to send a report!");
/* 130 */           System.err.println("[CLIENTPROCESSOR][WARNING] Ping time of client (" + ClientProcessor.access$000(this.this$0).getId() + ") exceeded time limit: retrying! pending requests: " + this.this$0.getPendingRequests());
/*     */ 
/* 133 */           this.waitingForPong = false;
/*     */         }
/* 135 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public boolean sendPing()
/*     */   {
/* 146 */     if (ClientProcessor.access$000(this.this$0).getId() < 0) {
/* 147 */       System.err.println("[CLIENT] not logged int to server: discarding ping");
/*     */ 
/* 149 */       return false;
/*     */     }
/*     */ 
/* 152 */     byte[] arrayOfByte = ClientProcessor.access$100(this.this$0).createPing();
/* 153 */     System.currentTimeMillis();
/*     */ 
/* 155 */     ClientProcessor.access$300(this.this$0).write(arrayOfByte);
/* 156 */     this.this$0.flushDoubleOutBuffer();
/*     */ 
/* 158 */     ClientProcessor.access$000(this.this$0).getNetworkStatus().addBytesSent(arrayOfByte.length);
/*     */ 
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */   public void sendPong()
/*     */   {
/* 169 */     if (ClientProcessor.access$000(this.this$0).getId() < 0) {
/* 170 */       System.err.println("[CLIENT] not logged int to server: discarding pong");
/*     */ 
/* 172 */       return;
/*     */     }try { byte[] arrayOfByte = ClientProcessor.access$100(this.this$0).createPong();
/* 176 */       System.currentTimeMillis();
/*     */ 
/* 178 */       ClientProcessor.access$300(this.this$0).write(arrayOfByte);
/* 179 */       this.this$0.flushDoubleOutBuffer();
/*     */ 
/* 181 */       ClientProcessor.access$000(this.this$0).getNetworkStatus().addBytesSent(arrayOfByte.length);
/*     */       return; } catch (IOException localIOException) { localIOException.printStackTrace(); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.ClientProcessor.Pinger
 * JD-Core Version:    0.6.2
 */