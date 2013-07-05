/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ class ServerProcessor$1 extends Thread
/*     */ {
/*     */   ServerProcessor$1(ServerProcessor paramServerProcessor)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/* 394 */       this.this$0.setStopTransmit(true);
/* 395 */       synchronized (ServerProcessor.access$800(this.this$0)) {
/* 396 */         ServerProcessor.access$800(this.this$0).notify();
/*     */       }
/* 398 */       Thread.sleep(3000L); } catch (InterruptedException localInterruptedException) {
/* 399 */       ??? = null;
/*     */ 
/* 401 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */ 
/* 402 */     this.this$0.disconnect();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.1
 * JD-Core Version:    0.6.2
 */