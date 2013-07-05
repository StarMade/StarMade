/*    */ package org.schema.schine.network.udp;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ 
/*    */ public class UDPProcessor$HostThread extends Thread
/*    */ {
/*    */   private DatagramSocket socket;
/* 23 */   private boolean running = true;
/*    */ 
/* 25 */   private byte[] buffer = new byte[65535];
/*    */ 
/*    */   public UDPProcessor$HostThread(UDPProcessor paramUDPProcessor)
/*    */   {
/* 29 */     setName("UDP Host@" + UDPProcessor.access$000(paramUDPProcessor));
/* 30 */     setDaemon(true);
/*    */   }
/*    */ 
/*    */   public void close()
/*    */   {
/* 36 */     this.running = false;
/*    */ 
/* 39 */     this.socket.close();
/*    */ 
/* 42 */     join();
/*    */   }
/*    */ 
/*    */   public void connect()
/*    */   {
/* 47 */     this.socket = new DatagramSocket(UDPProcessor.access$000(this.this$0));
/*    */   }
/*    */ 
/*    */   protected DatagramSocket getSocket()
/*    */   {
/* 53 */     return this.socket;
/*    */   }
/*    */ 
/*    */   private void reportError(IOException paramIOException)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 66 */     while (this.running)
/*    */     {
/*    */       try
/*    */       {
/* 71 */         DatagramPacket localDatagramPacket = new DatagramPacket(this.buffer, this.buffer.length);
/* 72 */         this.socket.receive(localDatagramPacket);
/*    */ 
/* 74 */         this.this$0.newData(localDatagramPacket);
/*    */       } catch (IOException localIOException) {
/* 76 */         if (!this.running) {
/* 77 */           return;
/*    */         }
/*    */ 
/* 80 */         reportError(localIOException);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPProcessor.HostThread
 * JD-Core Version:    0.6.2
 */