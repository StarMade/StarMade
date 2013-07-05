/*     */ package org.schema.schine.network.udp;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ 
/*     */ final class UDPProcessor$2
/*     */   implements Runnable
/*     */ {
/*     */   public final void run()
/*     */   {
/*     */     try
/*     */     {
/*     */       UDPProcessor localUDPProcessor;
/* 145 */       (
/* 146 */         localUDPProcessor = new UDPProcessor(4244))
/* 146 */         .initialize();
/* 147 */       localUDPProcessor.getEndpoint(new InetSocketAddress(4242), true)
/* 148 */         .send(new byte[13], 0, 1);
/*     */       return;
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 151 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.2
 * JD-Core Version:    0.6.2
 */