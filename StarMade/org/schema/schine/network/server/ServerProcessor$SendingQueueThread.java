/*     */ package org.schema.schine.network.server;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ class ServerProcessor$SendingQueueThread extends Thread
/*     */ {
/*     */   private ServerProcessor$SendingQueueThread(ServerProcessor paramServerProcessor)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/* 465 */       while ((ServerProcessor.access$500(this.this$0)) && (!this.this$0.isStopTransmit())) {
/* 466 */         setName("SendingQueueThread(" + ServerProcessor.access$400(this.this$0) + ")");
/*     */ 
/* 468 */         synchronized (ServerProcessor.access$800(this.this$0)) {
/* 469 */           while ((ServerProcessor.access$800(this.this$0).isEmpty()) && (ServerProcessor.access$500(this.this$0))) {
/* 470 */             ServerProcessor.access$800(this.this$0).wait(10000L);
/* 471 */             if ((!ServerProcessor.access$500(this.this$0)) || (this.this$0.isStopTransmit())) break;
/* 472 */             if (ServerProcessor.access$800(this.this$0).size() > 700)
/*     */             {
/* 475 */               ServerProcessor.access$502(this.this$0, false);
/*     */             }
/*     */           }
/* 478 */           if ((ServerProcessor.access$500(this.this$0)) && (this.this$0.isStopTransmit())) {
/*     */             break;
/*     */           }
/* 481 */           FastByteArrayOutputStream localFastByteArrayOutputStream1 = (FastByteArrayOutputStream)ServerProcessor.access$800(this.this$0).remove(0);
/* 482 */           ServerProcessor.totalPackagesQueued -= 1;
/*     */         }
/*     */ 
/* 485 */         this.this$0.sendPacket(localFastByteArrayOutputStream2);
/* 486 */         ServerProcessor.releasePacket(localFastByteArrayOutputStream2);
/* 487 */         if (ServerProcessor.access$900(this.this$0))
/* 488 */           this.this$0.disconnect();
/*     */         try
/*     */         {
/* 491 */           sleep(2L);
/*     */         } catch (InterruptedException localInterruptedException) {
/*     */         }
/*     */       }
/* 495 */       ServerProcessor.access$502(this.this$0, false);
/*     */     } catch (Exception localException) {
/* 497 */       System.err.println("SENDING THREAD ENDED of " + ServerProcessor.access$400(this.this$0));
/*     */ 
/* 499 */       localException.printStackTrace();
/*     */     } finally {
/* 501 */       ServerProcessor.access$502(this.this$0, false);
/*     */     }
/*     */ 
/* 504 */     synchronized (ServerProcessor.access$800(this.this$0)) {
/* 505 */       for (??? = ServerProcessor.access$800(this.this$0).iterator(); ((Iterator)???).hasNext(); ) {
/* 506 */         ServerProcessor.releasePacket((FastByteArrayOutputStream)((Iterator)???).next());
/*     */ 
/* 507 */         ServerProcessor.totalPackagesQueued -= 1;
/*     */       }
/* 509 */       ServerProcessor.access$800(this.this$0).clear();
/*     */       return;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.server.ServerProcessor.SendingQueueThread
 * JD-Core Version:    0.6.2
 */