/*     */ package org.schema.schine.network.udp;
/*     */ 
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ 
/*     */ public class UDPProcessor$MessageWriter
/*     */   implements Runnable
/*     */ {
/*     */   private UDPEndpoint endpoint;
/*     */   private DatagramPacket packet;
/*     */ 
/*     */   public UDPProcessor$MessageWriter(UDPProcessor paramUDPProcessor, UDPEndpoint paramUDPEndpoint, DatagramPacket paramDatagramPacket)
/*     */   {
/*  94 */     this.endpoint = paramUDPEndpoint;
/*  95 */     this.packet = paramDatagramPacket;
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 103 */     if (!this.endpoint.isConnected()) {
/* 104 */       return;
/*     */     }try {
/* 108 */       UDPProcessor.access$100(this.this$0).getSocket().send(this.packet);
/*     */       return;
/*     */     } catch (Exception localException) {
/* 110 */       new UDPException("Error sending datagram to:" + UDPProcessor.access$000(this.this$0), localException)
/* 111 */         .fillInStackTrace();
/* 112 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.MessageWriter
 * JD-Core Version:    0.6.2
 */