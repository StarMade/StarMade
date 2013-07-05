/*     */ package org.schema.schine.network.udp;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ final class UDPProcessor$1
/*     */   implements Runnable
/*     */ {
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/*     */       UDPProcessor localUDPProcessor;
/* 123 */       (
/* 124 */         localUDPProcessor = new UDPProcessor(4242))
/* 124 */         .initialize();
/*     */       while (true) {
/* 126 */         System.err.println("Broadcasting");
/* 127 */         localUDPProcessor.broadcast(new byte[] { 10 }, 0, 1);
/*     */         try {
/* 129 */           Thread.sleep(1000L);
/*     */         }
/*     */         catch (InterruptedException localInterruptedException) {
/* 132 */           localInterruptedException.printStackTrace();
/*     */         }
/*     */       } } catch (IOException localIOException) { localIOException
/* 134 */         .printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.1
 * JD-Core Version:    0.6.2
 */