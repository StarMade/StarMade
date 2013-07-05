/*    */ package org.schema.schine.network.udp;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.SocketAddress;
/*    */ 
/*    */ public class UDPEndpoint
/*    */ {
/*    */   private int id;
/*    */   private SocketAddress address;
/*    */   private DatagramSocket socket;
/* 13 */   private boolean connected = true;
/*    */   private UDPProcessor processor;
/*    */ 
/*    */   public UDPEndpoint(UDPProcessor paramUDPProcessor, int paramInt, SocketAddress paramSocketAddress, DatagramSocket paramDatagramSocket)
/*    */   {
/* 18 */     this.id = paramInt;
/* 19 */     this.address = paramSocketAddress;
/* 20 */     this.socket = paramDatagramSocket;
/* 21 */     this.processor = paramUDPProcessor;
/*    */   }
/*    */ 
/*    */   public void close()
/*    */   {
/* 28 */     close(false);
/*    */   }
/*    */ 
/*    */   public void close(boolean paramBoolean) {
/*    */     try {
/* 38 */       this.processor.closeEndpoint(this);
/* 39 */       this.connected = false;
/*    */       return;
/*    */     }
/*    */     catch (IOException paramBoolean) {
/*    */     }
/* 41 */     throw new UDPException("Error closing endpoint for socket:" + this.socket, paramBoolean);
/*    */   }
/*    */ 
/*    */   public String getAddress()
/*    */   {
/* 47 */     return String.valueOf(this.address);
/*    */   }
/*    */ 
/*    */   public long getId()
/*    */   {
/* 52 */     return this.id;
/*    */   }
/*    */ 
/*    */   protected SocketAddress getRemoteAddress()
/*    */   {
/* 57 */     return this.address;
/*    */   }
/*    */ 
/*    */   public boolean isConnected()
/*    */   {
/* 64 */     return this.connected;
/*    */   }
/*    */ 
/*    */   public void send(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*    */   {
/* 69 */     if (!isConnected()) {
/* 70 */       throw new UDPException("Endpoint is not connected:" + this);
/*    */     }
/*    */ 
/*    */     try
/*    */     {
/* 75 */       paramArrayOfByte = new DatagramPacket(paramArrayOfByte, paramInt1, paramInt2, this.address);
/*    */ 
/* 80 */       this.processor.enqueueWrite(this, paramArrayOfByte);
/*    */       return;
/*    */     }
/*    */     catch (IOException paramArrayOfByte) {
/*    */     }
/* 84 */     throw new UDPException("Error sending datagram to:" + this.address, paramArrayOfByte);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 91 */     return "UdpEndpoint[" + this.id + ", " + this.address + "]";
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.udp.UDPEndpoint
 * JD-Core Version:    0.6.2
 */